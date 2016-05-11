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
import br.com.hs.nfe.queue.DpecZipNaoEnviados;
import br.com.hs.nfe.vo.NFeVO;

public class DpecSenderThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("DpecSenderThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = null;
	private FileSender fileSender = null;
	public DpecSenderThread(Estabelecimento config)
	{
		this.config = config;
		this.nfeDao = new NFeDAO();
		if(!config.getUrlRMI().equalsIgnoreCase(""))
		{
			this.fileSender = new FileSender(this.config);
		}
	}
	public void run()
	{
		Thread.currentThread().setName( "DpecSenderThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread DpecSenderThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraZipDpecNaoEnviados(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = DpecZipNaoEnviados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML==null)
					{
						enviNFeXML = new NFeVO();
						File zip = new File(config.getEnvDpecXMLCompactados()+File.separatorChar+nfe.getChaveAcesso()+".zip");
						FileInputStream fis = new FileInputStream(zip);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setZip(IOUtils.toByteArray(fis));
						IOUtils.closeQuietly(fis);
					}
					if(!config.getUrlRMI().equalsIgnoreCase(""))
					{
						Character ret = fileSender.sendFile(enviNFeXML, nfe);
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
				logger.error("Problemas ao interromper a Thread DpecSenderThread", e);
			}
			finally
			{
				
			}
		}
	}
}
