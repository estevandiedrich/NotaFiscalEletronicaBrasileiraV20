package br.com.hs.nfe.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dao.NfeEntradaDAO;
import br.com.hs.nfe.entity.NfeEntrada;
import br.com.hs.nfe.hb.NFeEntradaUpdateCommand;
import br.com.hs.nfe.queue.DanfeEntradaGerados;
import br.com.hs.nfe.queue.XMLEntradaProcessados;
import br.com.hs.nfe.vo.NFeVO;

public class DanfeEntradaThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("DanfeEntradaThread");
	private NfeEntradaDAO nfeDao = new NfeEntradaDAO();
	public void run()
	{
		Thread.currentThread().setName( "DanfeEntradaThread" );
		logger.info("Iniciando thread DanfeEntradaThread");
		while ( super.isRunning() ) 
		{
			try
			{
				List<NfeEntrada> nfeList = nfeDao.procuraNotasProcessadas();
				for(NfeEntrada nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLEntradaProcessados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File nfeProc = new File(PropriedadesSistema.getInstance().getSistema().getXmlEntradaValido()+File.separatorChar+nfe.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(nfeProc);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setNfeProc(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					byte[] danfe = null;
					try
					{
						String formato = DanfeBuilder.DANFE_055_RETRATO;
						File file = new File("../resources/"+formato);
						danfe = new DanfeBuilder().montaDanfe(enviNFeXML.getNfeProc().getBytes(), new FileInputStream("../conf/branco.gif"),new FileInputStream(file),"");
						FileOutputStream fos = new FileOutputStream(PropriedadesSistema.getInstance().getSistema().getDanfeEntrada()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						fos.write(danfe);
						fos.close();
						
						DanfeEntradaGerados.getInstance().add(enviNFeXML);
						logger.info("Salvando arquivo : "+PropriedadesSistema.getInstance().getSistema().getDanfeEntrada()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						
						nfe.setDanfeGerado('1');
						NFeEntradaUpdateCommand updateCommand = new NFeEntradaUpdateCommand(nfe);
						updateCommand.execute();
					}
					catch(StringIndexOutOfBoundsException sioobe)
					{
						
					}
				}
			}
			catch(Throwable t)
			{
				logger.error("Erro n�o capturado", t);
			}
			try 
			{
				Thread.sleep( Integer.parseInt(PropriedadesSistema.getInstance().getSistema().getEmailEntradaThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread DanfeEntradaThread", e);
			}
			finally
			{
				
			}
		}
	}
}
