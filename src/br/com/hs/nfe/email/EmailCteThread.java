package br.com.hs.nfe.email;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.file.FileManager;

public class EmailCteThread extends ThreadBase{
private static final Logger logger = Logger.getLogger("EmailCteThread");
	
	public EmailCteThread()
	{
	}
	public void run()
	{
		Thread.currentThread().setName( "EmailCteThread" );
		logger.info("Iniciando thread EmailCteThread");
		while ( super.isRunning() ) 
		{
			try
			{
				InputStream[] notas = ReceberEmail.receber(PropriedadesSistema.getInstance().getSistema().getServidorEmailCte(),PropriedadesSistema.getInstance().getSistema().getPortaEmailCte(),PropriedadesSistema.getInstance().getSistema().getUsuarioEmailCte(),PropriedadesSistema.getInstance().getSistema().getSenhaEmailCte());
				for(InputStream nota:notas)
				{
					byte[] notaBa = IOUtils.toByteArray(nota);
					String notaString = new String(notaBa);
					FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getCteEntrada()+File.separatorChar+File.createTempFile("EntradaCTE", ".xml").getName(), notaString);					
				}
			}
			catch(Throwable t)
			{
				logger.error("Erro não capturado", t);
			}
			try 
			{
				Thread.sleep( Integer.parseInt(PropriedadesSistema.getInstance().getSistema().getEmailCteThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread EmailCteThread", e);
			}
			finally
			{
				
			}
		}
	}
}
