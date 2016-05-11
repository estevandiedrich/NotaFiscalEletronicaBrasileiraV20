package br.com.hs.nfe.email;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.DpecDanfeGerados;
import br.com.hs.nfe.queue.DpecEmailsEnviados;
import br.com.hs.nfe.vo.NFeVO;

public class EmailDpecThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("EmailDpecThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	public EmailDpecThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EmailDpecThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EmailDpecThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{

				List<Nfe> nfeList = nfeDao.procuraDpecEmailPendente(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = DpecDanfeGerados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
					}
					String[] cc = null;
					if(nfe.getEmail() != null && !nfe.getEmail().equalsIgnoreCase(""))
						cc = nfe.getEmail().split(";");
					else
						cc = new String[0];
					EnviarEmail.enviar(config.getAssuntoEmail(),config.getCorpoEmail(),config.getEnvDpecDanfe()+File.separatorChar+enviNFeXML.getChaveAcesso()+".pdf",config.getEnvDpecXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-proc.xml",config,cc);
					DpecEmailsEnviados.getInstance().add(enviNFeXML);
					
					nfe.setDpecEmailEnviado('1');
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
				logger.error("Problemas ao interromper a Thread EmailDpecThread", e);
			}
			finally
			{
				
			}
		}
	}
}
