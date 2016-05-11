package br.com.hs.nfe.dpec;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.DpecAssinados;
import br.com.hs.nfe.queue.DpecGerados;
import br.com.hs.nfe.signer.AssinarXML;
import br.com.hs.nfe.vo.NFeVO;

public class EnvDpecAssinadorThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("EnvDpecAssinadorThread");
	private Estabelecimento config = null;
	private AssinarXML assinarXML = null;
	private NFeDAO nfeDao = null;
	public EnvDpecAssinadorThread(Estabelecimento config)
	{
		this.assinarXML = new AssinarXML(config,"//:envDPEC/:infDPEC/@Id");
		this.nfeDao = new NFeDAO();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvDpecAssinadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvDpecAssinadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraDpecGerados(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = DpecGerados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					//TODO: Caso não exista essa nota na memoria é necessario procura-la no disco (pasta)
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File envDpec = new File(config.getEnvDpecXML()+File.separatorChar+nfe.getChaveAcesso()+"-dpec.xml");
						FileInputStream fis = new FileInputStream(envDpec);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setDpecXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					Document doc = DomHelper.toDocument(enviNFeXML.getDpecXMLString().getBytes());
					
					doc = assinarXML.assinarXML(doc);
					
					enviNFeXML.setDpecXMLString(DomHelper.docToXML(doc).toString());
					
					DpecAssinados.getInstance().add(enviNFeXML);
					DomHelper.docToXML(doc,new File(config.getEnvDpecXMLAssinados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-dpec-ass.xml"));
					
					nfe.setDpecAssinado('1');
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
				logger.error("Problemas ao interromper a Thread EnvDpecAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
