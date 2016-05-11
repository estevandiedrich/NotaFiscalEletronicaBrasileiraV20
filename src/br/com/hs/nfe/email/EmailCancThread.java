package br.com.hs.nfe.email;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLProcessadosCanc;
import br.com.hs.nfe.vo.NFeVO;

public class EmailCancThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("EmailCancThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	public EmailCancThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EmailCancThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EmailCancThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraCancelamentosProcessados(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO cancNFeXML = XMLProcessadosCanc.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(cancNFeXML == null)
					{
						cancNFeXML = new NFeVO();
						cancNFeXML.setChaveAcesso(nfe.getChaveAcesso());
					}
					String[] cc = null;
					if(nfe.getEmail() != null && !nfe.getEmail().equalsIgnoreCase(""))
						cc = nfe.getEmail().split(";");
					EnviarEmail.enviar(config.getAssuntoEmailCancelamento(),config.getCorpoEmailCancelamento(),config.getCancNFeXMLProcessados()+File.separatorChar+nfe.getChaveAcesso()+"-cancProc.xml","",config,cc);
					//EmailsEnviados.getInstance().add(cancNFeXML);
					
					nfe.setXmlCancelado('8');
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
				logger.error("Problemas ao interromper a Thread EmailCancThread", e);
			}
			finally
			{
				
			}
		}
	}
}
