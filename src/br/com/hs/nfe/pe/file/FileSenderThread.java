package br.com.hs.nfe.pe.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dao.NFeFileDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.NFeFile;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLZipNaoEnviados;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.vo.ZipChecksumVO;
import br.com.hs.nfe.zip.Zip;

public class FileSenderThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("FileSenderThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = null;
	private NFeFileDAO nfeFileDAO = null;
	private FileSender fileSender = null;
	public FileSenderThread(Estabelecimento config)
	{
		this.config = config;
		this.nfeDao = new NFeDAO();
		this.nfeFileDAO = new NFeFileDAO();
		if(!config.getUrlRMI().equalsIgnoreCase(""))
		{
			this.fileSender = new FileSender(this.config);
		}
	}
	public void run()
	{
		Thread.currentThread().setName( "FileSenderThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread FileSenderThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraZipNaoEnviados(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLZipNaoEnviados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML==null)
					{
						enviNFeXML = new NFeVO();
						try
						{
							File zip = new File(config.getXmlCompactado()+File.separatorChar+nfe.getChaveAcesso()+".zip");
							FileInputStream fis = new FileInputStream(zip);
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setZip(IOUtils.toByteArray(fis));
							IOUtils.closeQuietly(fis);
						}
						catch(FileNotFoundException fnfe)
						{
							NFeFile nfeFile = nfeFileDAO.findByChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							ZipChecksumVO zipChecksumVO = Zip.compacta(new String(nfeFile.getNfe()),nfe.getChaveAcesso()+"-nfeProc.xml",config.getXmlCompactado()+File.separatorChar+nfe.getChaveAcesso()+".zip");
							enviNFeXML.setZip(zipChecksumVO.getZip());
						}
					}
					if(!config.getUrlRMI().equalsIgnoreCase(""))
					{
						Character ret = fileSender.sendFile(enviNFeXML, nfe);
						ret = (ret==' '||ret==null)?'0':ret;
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
				logger.error("Problemas ao interromper a Thread FileSenderThread", e);
			}
			finally
			{
				
			}
		}
	}
}
