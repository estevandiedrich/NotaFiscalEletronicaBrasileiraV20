package br.com.hs.nfe.pe.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.FsdaZipNaoEnviados;
import br.com.hs.nfe.vo.NFeVO;

public class FsdaManualSenderThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("FsdaManualSenderThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = null;
	private FileSender fileSender;
	public FsdaManualSenderThread(Estabelecimento config)
	{
		this.config = config;
		if(!config.getUrlRMI().equalsIgnoreCase(""))
		{
			this.fileSender = new FileSender(this.config);
		}
		this.nfeDao = new NFeDAO();
		
	}
	public void run()
	{
		Thread.currentThread().setName( "FsdaManualSenderThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread FsdaManualSenderThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraZipFsdaEnvioManual(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = FsdaZipNaoEnviados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML==null)
					{
						enviNFeXML = new NFeVO();
						File zip = new File(config.getEnvFsdaXMLCompactados()+File.separatorChar+nfe.getChaveAcesso()+".zip");
						FileInputStream fis = new FileInputStream(zip);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setZip(IOUtils.toByteArray(fis));
						IOUtils.closeQuietly(fis);
					}
					if(!config.getUrlRMI().equalsIgnoreCase(""))
					{
						Character ret = fileSender.sendFileFSDA(enviNFeXML, nfe);
						nfe.setZipEnviado(ret);
					}
					else
					{
						nfe.setZipEnviado('1');
					}
					NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
					updateCommand.execute();
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
				logger.error("Problemas ao interromper a Thread FsdaManualSenderThread", e);
			}
			finally
			{
				
			}
		}
	}
}
