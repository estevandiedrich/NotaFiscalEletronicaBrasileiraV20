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
import br.com.hs.nfe.queue.DpecDanfeGerados;
import br.com.hs.nfe.queue.DpecProcessados;
import br.com.hs.nfe.vo.NFeVO;

public class DanfeDpecThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("DanfeDpecThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	public DanfeDpecThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "DanfeDpecThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread DanfeDpecThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{

				if(config.getGerarDanfe().equalsIgnoreCase("S"))
				{
					List<Nfe> nfeList = nfeDao.procuraDpecProcessados(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO enviNFeXML = DpecProcessados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(enviNFeXML == null)
						{
							enviNFeXML = new NFeVO();
							File nfeProc = new File(config.getEnvDpecXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-proc.xml");
							FileInputStream fis = new FileInputStream(nfeProc);
							File enviNFe = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
							FileInputStream fis2 = new FileInputStream(enviNFe);
							enviNFeXML.setNfeXMLString(IOUtils.toString(fis2));
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setDpecProc(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
							IOUtils.closeQuietly(fis2);
						}
						byte[] danfe = null;
						try
						{
							String formato = config.getOrientacaoDanfe()=='R'?DanfeBuilder.DANFE_055_RETRATO:DanfeBuilder.DANFE_055_PAISAGEM;
							File file = new File("../resources/"+formato);
							danfe = new DanfeBuilder().montaDanfe(enviNFeXML.getDpecProc().getBytes(), new FileInputStream("../conf/"+config.getLogoDanfe()),new FileInputStream(file),config.getRazaoSocial());
						}
						catch(StringIndexOutOfBoundsException sioobe)
						{
							logger.error("Erro ao gerar danfe do dpec",sioobe);
							nfe.setDpecProcessado('0');
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
						FileOutputStream fos = new FileOutputStream(config.getEnvDpecDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						fos.write(danfe);
						fos.close();
						
						DpecDanfeGerados.getInstance().add(enviNFeXML);
						logger.info("Salvando arquivo : "+config.getDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						
						nfe.setDpecDanfeGerado('1');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
					}
				}
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
				logger.error("Problemas ao interromper a Thread DanfeDpecThread", e);
			}
			finally
			{
				
			}
		}
	}
}
