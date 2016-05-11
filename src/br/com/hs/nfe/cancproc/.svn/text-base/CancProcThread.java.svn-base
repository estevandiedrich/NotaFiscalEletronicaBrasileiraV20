package br.com.hs.nfe.cancproc;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.builder.CancProcDocumentBuilder;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLAutorizadosCanc;
import br.com.hs.nfe.queue.XMLProcessadosCanc;
import br.com.hs.nfe.vo.NFeVO;

public class CancProcThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("CancProcThread");
	private Estabelecimento config = null;
	private CancProcDocumentBuilder documentBuilder = null;  
	private NFeDAO nfeDao = new NFeDAO();
	public CancProcThread(Estabelecimento config)
	{
		this.documentBuilder = new CancProcDocumentBuilder();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "CancProcThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread CancProcThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				//for(ConfigVO config:Config.getInstance().configs)
				//{
					List<Nfe> nfeList = nfeDao.procuraCancelamentoAutorizado(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO cancNFeXML = XMLAutorizadosCanc.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(cancNFeXML == null)
						{
							cancNFeXML = new NFeVO();
							File sefazErpCancNFe = new File(config.getCancNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+"ped-canc-val.xml");
							File retCancNFe = new File(config.getCancNFeXMLAutorizados()+File.separatorChar+nfe.getChaveAcesso()+"-retCancNFe.xml");
							FileInputStream fis = new FileInputStream(sefazErpCancNFe);
							FileInputStream fis2 = new FileInputStream(retCancNFe);
							cancNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							cancNFeXML.setCancXMLString(IOUtils.toString(fis));
							cancNFeXML.setRetCancNFe(IOUtils.toString(fis2));
							IOUtils.closeQuietly(fis);
							IOUtils.closeQuietly(fis2);
						}
						String cancProc =  documentBuilder.cancProcDocumentBuilder(cancNFeXML);
						//NFeVO e = new NFeVO();
						//e.setChaveAcesso(cancNFeXML.getChaveAcesso());
						cancNFeXML.setCancProc(cancProc);
						
						nfe.setXmlCancelado('7');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
						
						XMLProcessadosCanc.getInstance().add(cancNFeXML);
						//XMLDescompactadosCanc.getInstance().add(e);
						FileManager.getInstance().saveFile(config.getCancNFeXMLProcessados()+File.separatorChar+cancNFeXML.getChaveAcesso()+"-cancProc.xml", cancProc);
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
				logger.error("Problemas ao interromper a Thread CancProcThread", e);
			}
			finally
			{
				
			}
		}
	}
}
