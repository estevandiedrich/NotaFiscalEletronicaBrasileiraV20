package br.com.hs.nfe.cancnfe;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;

public class GerarPedidoCancelamentoThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("GerarPedidoCancelamentoThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	public GerarPedidoCancelamentoThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "GerarPedidoCancelamentoThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread GerarPedidoCancelamentoThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
					List<Nfe> nfeList = nfeDao.procuraNotasCancelamento(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						String solicitacaoCancelamento = "0000;2.00;CANCELAMENTO;\n";
						solicitacaoCancelamento += "1000;ID"+nfe.getChaveAcesso().substring(3)+";\n";
						solicitacaoCancelamento += "1100;"+nfe.getTpAmb()+";CANCELAR;"+nfe.getChaveAcesso().substring(3)+";"+nfe.getNprot()+";Erro de impressao\n";
						
						FileManager.getInstance().saveFile(config.getCancNFeTXT()+File.separatorChar+nfe.getChaveAcesso()+"ped-canc.txt", solicitacaoCancelamento);
						
						nfe.setCancelarNota('0');
//						try
//						{
//							NFeCreateCommand createCommand = new NFeCreateCommand(nfe);
//							createCommand.execute();
//						}
//						catch(ConstraintViolationException e)
//						{
//							logger.error("! ConstraintViolationException !",e);
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
//						}
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
				logger.error("Problemas ao interromper a Thread GerarPedidoCancelamentoThread", e);
			}
			finally
			{
				
			}
		}
	}
}