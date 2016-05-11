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
import br.com.hs.nfe.queue.FsdaDanfeGerados;
import br.com.hs.nfe.queue.FsdaImpressos;
import br.com.hs.nfe.vo.NFeVO;

public class ImprimirFsdaThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("ImprimirFsdaThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	private ImprimirPDF imprimirPDF = new ImprimirPDF();
	public ImprimirFsdaThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "ImprimirFsdaThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread ImprimirFsdaThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{

				if(config.getImprimirDanfe().equalsIgnoreCase("S"))
				{
					List<Nfe> nfeList = nfeDao.procuraFsdaDanfeGerados(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO enviNFeXML = FsdaDanfeGerados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(enviNFeXML == null)
						{
							enviNFeXML = new NFeVO();
							File sefazErpEnviNFe = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
							FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						logger.info("Imprimindo arquivo : "+config.getEnvFsdaDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf");
						InputStream is = new FileInputStream(new File(config.getEnvFsdaDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf"));
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						IOUtils.copy(is, baos);
						byte msg[] = baos.toByteArray();
						IOUtils.closeQuietly(is);
						IOUtils.closeQuietly(baos);
						int copias = 2;
						try
						{
							copias = Integer.parseInt(config.getNumeroCopias());
						}
						catch(NumberFormatException e)
						{
							logger.error(e);
						}
						imprimirPDF.imprimir(msg,config.getImpressoraFsda(),copias,true);
						logger.info("Terminou");
						
						FsdaImpressos.getInstance().add(enviNFeXML);
						
						nfe.setFsdaImpresso('1');
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
				logger.error("Problemas ao interromper a Thread ImprimirFsdaThread", e);
			}
			finally
			{
				
			}
		}
	}
}
