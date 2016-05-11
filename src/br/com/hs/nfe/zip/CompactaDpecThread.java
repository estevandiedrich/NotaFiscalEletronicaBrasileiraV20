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
import br.com.hs.nfe.queue.DpecDescompactados;
import br.com.hs.nfe.queue.DpecZipNaoEnviados;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.vo.ZipChecksumVO;

public class CompactaDpecThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("CompactaDpecThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	public CompactaDpecThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "CompactaDpecThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread CompactaDpecThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				if(config.getCompactarXML().equalsIgnoreCase("S"))
				{
					List<Nfe> nfeList = nfeDao.procuraCompactacaoPendenteDpec(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO enviNFeXML = DpecDescompactados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(enviNFeXML == null)
						{
							enviNFeXML = new NFeVO();
							File dpecProc = new File(config.getEnvDpecXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-proc.xml");
							FileInputStream fis = new FileInputStream(dpecProc);
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setDpecProc(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						
						ZipChecksumVO zipChecksumVO = Zip.compacta(enviNFeXML.getDpecProc(),enviNFeXML.getChaveAcesso()+"-dpec-proc.xml",config.getEnvDpecXMLCompactados()+File.separatorChar+enviNFeXML.getChaveAcesso()+".zip");
						
						enviNFeXML.setZip(zipChecksumVO.getZip());
						DpecZipNaoEnviados.getInstance().add(enviNFeXML);
						
						nfe.setChecksum(zipChecksumVO.getChecksum());
						nfe.setDpecCompactado('1');
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
				logger.error("Problemas ao interromper a Thread CompactaDpecThread", e);
			}
			finally
			{
				
			}
		}
	}
}
