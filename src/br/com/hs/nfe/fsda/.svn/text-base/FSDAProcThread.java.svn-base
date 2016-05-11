package br.com.hs.nfe.fsda;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.builder.FsdaProcDocumentBuilder;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.FsdaDescompactados;
import br.com.hs.nfe.queue.FsdaProcessados;
import br.com.hs.nfe.vo.NFeVO;

public class FSDAProcThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("FSDAProcThread");
	private Estabelecimento config = null;
	private FsdaProcDocumentBuilder documentBuilder = null;
	private NFeDAO nfeDao = new NFeDAO();
	public FSDAProcThread(Estabelecimento config)
	{
		this.documentBuilder = new FsdaProcDocumentBuilder();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "FSDAProcThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread FSDAProcThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{

				List<Nfe> nfeList = nfeDao.procuraFsdaPreparados(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = new NFeVO();
					File xml = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
					FileInputStream fis = new FileInputStream(xml);
					enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
					enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
					IOUtils.closeQuietly(fis);
					
					String fsdaProc =  documentBuilder.fsdaProcDocumentBuilder(enviNFeXML);
					
					enviNFeXML.setFsdaProc(fsdaProc);
					
					FsdaProcessados.getInstance().add(enviNFeXML);
					FsdaDescompactados.getInstance().add(enviNFeXML);
					FileManager.getInstance().saveFile(config.getEnvFsdaXMLProcessados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-fsda-proc.xml", fsdaProc);
					
					nfe.setFsdaProcessado('1');
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
				logger.error("Problemas ao interromper a Thread FSDAProcThread", e);
			}
			finally
			{
				
			}
		}
	}
}
