package br.com.hs.nfe.dpecproc;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.builder.DpecProcDocumentBuilder;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.DpecAutorizados;
import br.com.hs.nfe.queue.DpecDescompactados;
import br.com.hs.nfe.queue.DpecProcessados;
import br.com.hs.nfe.vo.NFeVO;

public class DpecProcThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("DpecProcThread");
	private Estabelecimento config = null;
	private DpecProcDocumentBuilder documentBuilder = null;
	private NFeDAO nfeDao = new NFeDAO();
	public DpecProcThread(Estabelecimento config)
	{
		this.documentBuilder = new DpecProcDocumentBuilder();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "DpecProcThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread DpecProcThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraDpecAutorizados(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = DpecAutorizados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File retDPEC = new File(config.getEnvDpecXMLAutorizados()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-aut.xml");
						FileInputStream fis2 = new FileInputStream(retDPEC);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setRetDpecXMLString(IOUtils.toString(fis2));
						IOUtils.closeQuietly(fis2);
					}
					File enviNFe = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
					FileInputStream fis = new FileInputStream(enviNFe);
					enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
					IOUtils.closeQuietly(fis);
					
					String dpecProc =  documentBuilder.dpecProcDocumentBuilder(enviNFeXML);
					
					enviNFeXML.setDpecProc(dpecProc);
					
					DpecProcessados.getInstance().add(enviNFeXML);
					DpecDescompactados.getInstance().add(enviNFeXML);
					FileManager.getInstance().saveFile(config.getEnvDpecXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-proc.xml", dpecProc);
					
					nfe.setDpecProcessado('1');
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
				logger.error("Problemas ao interromper a Thread DpecProcThread", e);
			}
			finally
			{
				
			}
		}
	}
}
