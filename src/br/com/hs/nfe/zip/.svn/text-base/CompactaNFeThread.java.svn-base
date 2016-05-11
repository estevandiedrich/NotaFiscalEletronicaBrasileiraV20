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
import br.com.hs.nfe.queue.XMLDescompactados;
import br.com.hs.nfe.queue.XMLZipNaoEnviados;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.vo.ZipChecksumVO;

public class CompactaNFeThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("CompactaNFeThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	public CompactaNFeThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "CompactaNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread CompactaNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraCompactacaoPendente(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLDescompactados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File nfeProc = new File(config.getEnviNFeXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-nfeProc.xml");
						FileInputStream fis = new FileInputStream(nfeProc);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setNfeProc(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					
					ZipChecksumVO zipChecksumVO = Zip.compacta(enviNFeXML.getNfeProc(),enviNFeXML.getChaveAcesso()+"-nfeProc.xml",config.getXmlCompactado()+File.separatorChar+enviNFeXML.getChaveAcesso()+".zip");
					
					//XMLNaoPersistidos.getInstance().add(enviNFeXML);
					enviNFeXML.setZip(zipChecksumVO.getZip());
					XMLZipNaoEnviados.getInstance().add(enviNFeXML);
					nfe.setChecksum(zipChecksumVO.getChecksum());
					nfe.setXmlCompactado('1');
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
