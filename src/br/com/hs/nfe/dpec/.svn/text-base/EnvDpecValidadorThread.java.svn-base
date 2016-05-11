package br.com.hs.nfe.dpec;

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
import br.com.hs.nfe.queue.DpecAssinados;
import br.com.hs.nfe.queue.DpecValidados;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xml.ValidateXML;

public class EnvDpecValidadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnvDpecValidadorThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	private ValidateXML validateXML = null;
	public EnvDpecValidadorThread(Estabelecimento config)
	{
		this.validateXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getEnvDpecXSD());
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvDpecValidadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvDpecValidadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraDpecAssinados(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = DpecAssinados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File envDpec = new File(config.getEnvDpecXMLAssinados()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-ass.xml");
						FileInputStream fis = new FileInputStream(envDpec);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setDpecXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					logger.info("Validando XML "+ enviNFeXML.getChaveAcesso() + " com XSD "+PropriedadesSistema.getInstance().getSistema().getEnvDpecXSD());
					String schemaError = validateXML.validateXml(enviNFeXML.getDpecXMLString()); 
					if(schemaError.equalsIgnoreCase(""))
					{
						logger.info("XML "+enviNFeXML.getChaveAcesso()+" valido ");
						FileManager.getInstance().saveFile(config.getEnvDpecXMLValidos()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-dpec-val.xml", enviNFeXML.getDpecXMLString());
						
						DpecValidados.getInstance().add(enviNFeXML);
						
						nfe.setDpecValido('1');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
					}
					else
					{
						logger.info("XML "+enviNFeXML.getChaveAcesso()+" Invalido ");
						FileManager.getInstance().saveFile(config.getEnvDpecXMLInvalidos()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-dpec-inval.xml", enviNFeXML.getDpecXMLString());
						nfe.setDpecValido('2');
						nfe.setXmotivo(schemaError);
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
					}
				}
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
				logger.error("Problemas ao interromper a Thread EnvDpecValidadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
