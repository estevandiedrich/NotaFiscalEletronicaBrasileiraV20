package br.com.hs.nfe.percxml;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.NFeFile;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeFileCreateCommand;
import br.com.hs.nfe.hb.NFeFileUpdateCommand;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLDescompactados;
import br.com.hs.nfe.queue.XMLNaoPersistidos;
import br.com.hs.nfe.vo.NFeVO;

public class PercXMLThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("PercXMLThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	public PercXMLThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "PercXMLThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread PercXMLThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraPersistenciaPendente(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLNaoPersistidos.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File nfeProc = new File(config.getEnviNFeXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-nfeProc.xml");
						FileInputStream fis = new FileInputStream(nfeProc);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setNfeProc(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					
					NFeFile file = new NFeFile();
					file.setChaveAcesso(enviNFeXML.getChaveAcesso());
					file.setNfe(enviNFeXML.getNfeProc().getBytes());
					try
					{
						NFeFileCreateCommand fileCreateCommand = new NFeFileCreateCommand(file);
						fileCreateCommand.execute();
					}
					catch(ConstraintViolationException e)
					{
						logger.error("! ConstraintViolationException !",e);
						NFeFileUpdateCommand fileUpdateCommand = new NFeFileUpdateCommand(file);
						fileUpdateCommand.execute();
					}
					
					
					//XMLZipNaoEnviados.getInstance().add(enviNFeXML);
					XMLDescompactados.getInstance().add(enviNFeXML);
					nfe.setXmlPersistido('1');
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
				logger.error("Problemas ao interromper a Thread CompactaNFeThread", e);
			}
			finally
			{
				
			}
		}
	}
}
