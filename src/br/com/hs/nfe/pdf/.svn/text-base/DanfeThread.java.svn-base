package br.com.hs.nfe.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dao.NFeFileDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.NFeFile;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.DanfesGerados;
import br.com.hs.nfe.queue.XMLProcessados;
import br.com.hs.nfe.vo.NFeVO;

public class DanfeThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("DanfeThread");
	private NFeDAO nfeDao = new NFeDAO();
	private NFeFileDAO fileDAO = new NFeFileDAO();
	private Estabelecimento config = null;
	private DanfeBuilder danfeBuilder = new DanfeBuilder();
	public DanfeThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "DanfeThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread DanfeThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraNotasProcessadas(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLProcessados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						NFeFile file = fileDAO.findByChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setNfeProc(new String(file.getNfe()));
					}
					byte[] danfe = null;
					try
					{
						String formato = (config.getOrientacaoDanfe()=='R'?DanfeBuilder.DANFE_055_RETRATO:(config.getOrientacaoDanfe()=='P'?DanfeBuilder.DANFE_055_PAISAGEM:DanfeBuilder.DANFE_055_PAISAGEM_50_DUP));
						File file = new File("../resources/"+formato);
						danfe = danfeBuilder.montaDanfe(enviNFeXML.getNfeProc().getBytes(), new FileInputStream("../conf/"+config.getLogoDanfe()),new FileInputStream(file),config.getRazaoSocial());
						FileOutputStream fos = new FileOutputStream(config.getDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						fos.write(danfe);
						fos.close();
						
						DanfesGerados.getInstance().add(enviNFeXML);
						logger.info("Salvando arquivo : "+config.getDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						
						nfe.setDanfeGerado('1');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
					}
					catch(StringIndexOutOfBoundsException sioobe)
					{
						logger.error("Erro ao gerar danfe",sioobe);
						nfe.setXmlProcessado('0');
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
				logger.error("Problemas ao interromper a Thread DanfeThread", e);
			}
			finally
			{
				
			}
		}
	}
}
