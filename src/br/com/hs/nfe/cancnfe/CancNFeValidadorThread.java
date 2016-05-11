package br.com.hs.nfe.cancnfe;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLAssinadosPedCanc;
import br.com.hs.nfe.queue.XMLValidadosPedCanc;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xml.ValidateXML;

public class CancNFeValidadorThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("CancNFeValidadorThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	private ValidateXML validateXML = null;
	public CancNFeValidadorThread(Estabelecimento config)
	{
		this.validateXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getCancNFeXSD());
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "CancNFeValidadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread CancNFeValidadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
					List<Nfe> nfeList = nfeDao.procuraCancelamentoAssinado(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO cancNFeXML = XMLAssinadosPedCanc.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(cancNFeXML == null)
						{
							cancNFeXML = new NFeVO();
							File sefazErpEnviNFe = new File(config.getCancNFeXMLAssinados()+File.separatorChar+nfe.getChaveAcesso()+"ped-canc-ass.xml");
							FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
							cancNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							cancNFeXML.setCancXMLString(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						logger.info("Validando XML "+ cancNFeXML.getChaveAcesso() + " com XSD "+PropriedadesSistema.getInstance().getSistema().getCancNFeXSD());
						String schemaError = validateXML.validateXml(cancNFeXML.getCancXMLString());
						if(schemaError.equalsIgnoreCase(""))
						{
							logger.info("XML "+cancNFeXML.getChaveAcesso()+" valido ");
							FileManager.getInstance().saveFile(config.getCancNFeXMLValidos()+File.separatorChar+cancNFeXML.getChaveAcesso()+"ped-canc-val.xml", cancNFeXML.getCancXMLString());
							//NFeVO e = new NFeVO();
							//e.setCancXMLString(cancNFeXML.getCancXMLString());
							//e.setChaveAcesso(cancNFeXML.getChaveAcesso());
							
							nfe.setXmlCancelado('2');
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
							
							XMLValidadosPedCanc.getInstance().add(cancNFeXML);
						}
						else
						{
							logger.info("XML "+cancNFeXML.getChaveAcesso()+" Invalido ");
							nfe.setXmlValido('3');
							nfe.setXmotivo(schemaError);
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
							FileManager.getInstance().saveFile(config.getCancNFeXMLInvalidos()+File.separatorChar+cancNFeXML.getChaveAcesso()+"ped-canc-inval.xml", cancNFeXML.getCancXMLString());
						}
					}
				//}
			}
			catch(Throwable t)
			{
				logger.error("Erro não capturado", t);
			}
			try 
			{
				Thread.sleep( Integer.parseInt(config.getEnviNFeThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread EnviNFeValidadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
