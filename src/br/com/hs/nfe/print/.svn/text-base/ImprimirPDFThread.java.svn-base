package br.com.hs.nfe.print;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.EmailsEnviados;
import br.com.hs.nfe.vo.NFeVO;

public class ImprimirPDFThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("ImprimirPDFThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	private ImprimirPDF imprimirPDF = new ImprimirPDF();
	public ImprimirPDFThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "ImprimirPDFThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread ImprimirPDFThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{

				
					List<Nfe> nfeList = nfeDao.procuraEmailsEnviados(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						if(config.getImprimirDanfe().equalsIgnoreCase("S"))
						{
							NFeVO enviNFeXML = EmailsEnviados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
							if(enviNFeXML == null)
							{
								enviNFeXML = new NFeVO();
								enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							}
							logger.info("Imprimindo arquivo : "+config.getDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
							InputStream is = new FileInputStream(new File(config.getDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf"));
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							IOUtils.copy(is, baos);
							byte msg[] = baos.toByteArray();
							IOUtils.closeQuietly(is);
							IOUtils.closeQuietly(baos);
							int copias = 1;
							try
							{
								copias = Integer.parseInt(config.getNumeroCopias());
							}
							catch(NumberFormatException e)
							{
								logger.error(e);
							}
							imprimirPDF.imprimir(msg,config.getImpressora(),copias,true);
							logger.info("Terminou");
							
							nfe.setDanfeImpresso('1');
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
						else
						{
							nfe.setDanfeImpresso('1');
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
				logger.error("Problemas ao interromper a Thread ImprimirPDFThread", e);
			}
			finally
			{
				
			}
		}
	}
}
