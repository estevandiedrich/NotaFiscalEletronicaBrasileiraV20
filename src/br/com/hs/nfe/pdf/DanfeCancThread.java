package br.com.hs.nfe.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.DanfesGerados;
import br.com.hs.nfe.queue.XMLProcessadosCanc;
import br.com.hs.nfe.vo.NFeVO;

public class DanfeCancThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("DanfeCancThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	public DanfeCancThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "DanfeCancThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread DanfeCancThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				//for(ConfigVO config:Config.getInstance().configs)
				//{
					if(config.getGerarDanfe().equalsIgnoreCase("S"))
					{
						List<Nfe> nfeList = nfeDao.procuraCancelamentosProcessados(config.getCnpj(),config.getPe());
						for(Nfe nfe:nfeList)
						{
							NFeVO cancNFeXML = XMLProcessadosCanc.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
							if(cancNFeXML == null)
							{
								cancNFeXML = new NFeVO();
								File cancProc = new File(config.getCancNFeXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-nfeProc.xml");
								FileInputStream fis = new FileInputStream(cancProc);
								cancNFeXML.setChaveAcesso(nfe.getChaveAcesso());
								cancNFeXML.setCancProc(IOUtils.toString(fis));
								IOUtils.closeQuietly(fis);
							}
							String formato = config.getOrientacaoDanfe()=='R'?DanfeBuilder.DANFE_055_RETRATO:DanfeBuilder.DANFE_055_PAISAGEM;
							File file = new File("../resources/"+formato);
							byte[] danfe = new DanfeBuilder().montaDanfe(cancNFeXML.getCancProc().getBytes(), new FileInputStream("../conf/"+config.getLogoDanfe()),new FileInputStream(file),config.getRazaoSocial());
							FileOutputStream fos = new FileOutputStream(config.getDanfe()+File.separatorChar+cancNFeXML.getChaveAcesso()+".pdf");
							fos.write(danfe);
							fos.close();
							
							nfe.setDanfeGerado('1');
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
							
							DanfesGerados.getInstance().add(cancNFeXML);
							logger.info("Salvando arquivo : "+config.getDanfe()+File.separatorChar+cancNFeXML.getChaveAcesso()+".pdf");
						}
					}
				//}
			}
			catch(Throwable t)
			{
				logger.error("Erro n�o capturado", t);
			}
			try 
			{
				Thread.sleep( Integer.parseInt(config.getEnviNFeThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread DanfeCancThread", e);
			}
			finally
			{
				
			}
		}
	}
}
