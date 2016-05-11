package br.com.hs.nfe.zip;

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
import br.com.hs.nfe.queue.FsdaDescompactados;
import br.com.hs.nfe.queue.FsdaZipNaoEnviados;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.vo.ZipChecksumVO;

public class CompactaFsdaThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("CompactaFsdaThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	public CompactaFsdaThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "CompactaFsdaThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread CompactaFsdaThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				if(config.getCompactarXML().equalsIgnoreCase("S"))
				{
					List<Nfe> nfeList = nfeDao.procuraCompactacaoPendenteFsda(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO enviNFeXML = FsdaDescompactados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(enviNFeXML == null)
						{
							enviNFeXML = new NFeVO();
							File fsdaProc = new File(config.getEnvFsdaXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-fsda-proc.xml");
							FileInputStream fis = new FileInputStream(fsdaProc);
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setFsdaProc(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						
						ZipChecksumVO zipChecksumVO = Zip.compacta(enviNFeXML.getFsdaProc(),enviNFeXML.getChaveAcesso()+"-fsda-proc.xml",config.getEnvFsdaXMLCompactados()+File.separatorChar+enviNFeXML.getChaveAcesso()+".zip");
						
						enviNFeXML.setZip(zipChecksumVO.getZip());
						FsdaZipNaoEnviados.getInstance().add(enviNFeXML);
						
						nfe.setChecksum(zipChecksumVO.getChecksum());
						nfe.setFsdaCompactado('1');
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
				logger.error("Problemas ao interromper a Thread CompactaFsdaThread", e);
			}
			finally
			{
				
			}
		}
	}
}
