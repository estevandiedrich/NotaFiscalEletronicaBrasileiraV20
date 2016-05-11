package br.com.hs.nfe.inutnfe;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dao.InutNFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.InutNfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.InutNFeUpdateCommand;
import br.com.hs.nfe.queue.XMLAssinadosPedInut;
import br.com.hs.nfe.queue.XMLValidadosPedInut;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xml.ValidateXML;

public class InutNFeValidadorThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("InutNFeValidadorThread");
	private Estabelecimento config = null;
	private InutNFeDAO inutNFeDao = new InutNFeDAO();
	private ValidateXML validateXML = null;
	public InutNFeValidadorThread(Estabelecimento config)
	{
		this.validateXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getInutNFeXSD());
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "InutNFeValidadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread InutNFeValidadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
					List<InutNfe> inutNFeList = inutNFeDao.procuraInutilizacaoAssinada(config.getCnpj(),config.getPe());
					for(InutNfe inutNFe:inutNFeList)
					{
						NFeVO inutNFeXML = XMLAssinadosPedInut.getInstance().getNFeVOPorId(inutNFe.getId());
						if(inutNFeXML == null)
						{
							inutNFeXML = new NFeVO();
							File sefazErpInutNFe = new File(config.getInutNFeXMLAssinados()+File.separatorChar+inutNFe.getId()+"ped-inut-ass.xml");
							FileInputStream fis = new FileInputStream(sefazErpInutNFe);
							inutNFeXML.setId(inutNFe.getId());
							inutNFeXML.setInutXMLString(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						logger.info("Validando XML "+ inutNFeXML.getId() + " com XSD "+PropriedadesSistema.getInstance().getSistema().getInutNFeXSD());
						String schemaError = validateXML.validateXml(inutNFeXML.getInutXMLString()); 
						if(schemaError.equalsIgnoreCase(""))
						{
							logger.info("XML "+inutNFeXML.getId()+" valido ");
							FileManager.getInstance().saveFile(config.getInutNFeXMLValidos()+File.separatorChar+inutNFeXML.getId()+"ped-inut-val.xml", inutNFeXML.getInutXMLString());
							
							inutNFe.setXmlInutilizado('2');
							
							InutNFeUpdateCommand updateCommand = new InutNFeUpdateCommand(inutNFe);
							updateCommand.execute();
							
							XMLValidadosPedInut.getInstance().add(inutNFeXML);
						}
						else
						{
							logger.info("XML "+inutNFeXML.getId()+" Invalido ");
							
							inutNFe.setXmlInutilizado('3');
							inutNFe.setXmotivo(schemaError);
							InutNFeUpdateCommand updateCommand = new InutNFeUpdateCommand(inutNFe);
							updateCommand.execute();
							FileManager.getInstance().saveFile(config.getInutNFeXMLInvalidos()+File.separatorChar+inutNFeXML.getId()+"ped-inut-inval.xml", inutNFeXML.getInutXMLString());
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
				logger.error("Problemas ao interromper a Thread InutNFeValidadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
