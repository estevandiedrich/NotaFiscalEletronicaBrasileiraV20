package br.com.hs.nfe.email;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.file.FileManager;

public class EmailEntradaThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EmailEntradaThread");
	
	public EmailEntradaThread()
	{
	}
	public void run()
	{
		Thread.currentThread().setName( "EmailEntradaThread" );
		logger.info("Iniciando thread EmailEntradaThread");
		while ( super.isRunning() ) 
		{
			try
			{
				InputStream[] notas = ReceberEmail.receber(PropriedadesSistema.getInstance().getSistema().getServidorEmailEntrada(),PropriedadesSistema.getInstance().getSistema().getPortaEmailEntrada(),PropriedadesSistema.getInstance().getSistema().getUsuarioEmailEntrada(),PropriedadesSistema.getInstance().getSistema().getSenhaEmailEntrada());
				for(InputStream nota:notas)
				{
					byte[] notaBa = IOUtils.toByteArray(nota);
					String notaString = new String(notaBa);
					FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getXmlEntrada()+File.separatorChar+File.createTempFile("Entrada", ".xml").getName(), notaString);					
				}
			}
			catch(Throwable t)
			{
				logger.error("Erro não capturado", t);
			}
			try 
			{
				Thread.sleep( Integer.parseInt(PropriedadesSistema.getInstance().getSistema().getEmailEntradaThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread EmailEntradaThread", e);
			}
			finally
			{
				
			}
		}
	}
}
