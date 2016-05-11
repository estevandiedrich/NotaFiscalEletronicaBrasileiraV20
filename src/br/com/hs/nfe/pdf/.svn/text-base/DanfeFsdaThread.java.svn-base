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
import br.com.hs.nfe.queue.FsdaDanfeGerados;
import br.com.hs.nfe.queue.FsdaProcessados;
import br.com.hs.nfe.util.GeradorChaveAcesso;
import br.com.hs.nfe.vo.NFeVO;

public class DanfeFsdaThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("DanfeFsdaThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	private GeradorChaveAcesso geradorChaveAcesso = new GeradorChaveAcesso();
	public DanfeFsdaThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "DanfeFsdaThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread DanfeFsdaThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{

				if(config.getGerarDanfe().equalsIgnoreCase("S"))
				{
					List<Nfe> nfeList = nfeDao.procuraFsdaProcessados(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO enviNFeXML = FsdaProcessados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(enviNFeXML == null)
						{
							enviNFeXML = new NFeVO();
							File fsdaProc = new File(config.getEnvFsdaXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-fsda-proc.xml");
							FileInputStream fis = new FileInputStream(fsdaProc);
							File enviNFe = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
							FileInputStream fis2 = new FileInputStream(enviNFe);
							enviNFeXML.setNfeXMLString(IOUtils.toString(fis2));
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setFsdaProc(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
							IOUtils.closeQuietly(fis2);
						}
						byte[] danfe = null;
						try
						{
							String orientacao = config.getOrientacaoDanfe()=='R'?DanfeBuilder.DANFE_055_RETRATO_FSDA:DanfeBuilder.DANFE_055_PAISAGEM;
							File file = new File("../resources/"+orientacao);
							String chaveDados = geradorChaveAcesso.construirChaveDados(enviNFeXML.getFsdaProc());
							danfe = new DanfeBuilder().montaDanfe(enviNFeXML.getFsdaProc().getBytes(), new FileInputStream("../conf/"+config.getLogoDanfe()),new FileInputStream(file),config.getRazaoSocial(),chaveDados);
						}
						catch(StringIndexOutOfBoundsException sioobe)
						{
							logger.error("Erro ao gerar danfe do fsda",sioobe);
							nfe.setFsdaProcessado('0');
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
						FileOutputStream fos = new FileOutputStream(config.getEnvFsdaDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						fos.write(danfe);
						fos.close();
						
						logger.info("Salvando arquivo : "+config.getDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						FsdaDanfeGerados.getInstance().add(enviNFeXML);
						
						nfe.setFsdaDanfeGerado('1');
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
				logger.error("Problemas ao interromper a Thread DanfeFsdaThread", e);
			}
			finally
			{
				
			}
		}
	}
}
