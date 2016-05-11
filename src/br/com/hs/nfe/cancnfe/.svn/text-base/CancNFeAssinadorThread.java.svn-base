package br.com.hs.nfe.cancnfe;

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
import br.com.hs.nfe.queue.XMLAssinadosPedCanc;
import br.com.hs.nfe.queue.XMLGeradosPedCanc;
import br.com.hs.nfe.signer.AssinarXML;
import br.com.hs.nfe.vo.NFeVO;

public class CancNFeAssinadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("CancNFeThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = null;
	private AssinarXML assinarXML = null;
	public CancNFeAssinadorThread(Estabelecimento config)
	{
		this.assinarXML = new AssinarXML(config,"//:cancNFe/:infCanc/@Id");
		this.nfeDao = new NFeDAO(); 
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "CancNFeAssinadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread CancNFeAssinadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraCancelamentoPendente(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO cancNFeXML = XMLGeradosPedCanc.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					//TODO: Caso não exista essa nota na memoria é necessario procura-la no disco (pasta)
					if(cancNFeXML == null)
					{
						cancNFeXML = new NFeVO();
		
						File sefazErpCancNFe = new File(config.getCancNFeXML()+File.separatorChar+nfe.getChaveAcesso()+"ped-canc.xml");
						FileInputStream fis = new FileInputStream(sefazErpCancNFe);
						cancNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						cancNFeXML.setCancXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					Document doc = DomHelper.toDocument(cancNFeXML.getCancXMLString().getBytes());
					if("A3".equalsIgnoreCase(config.getTipoCertificado()))
					{
						doc = assinarXML.assinarXMLA3(doc);
					}
					else
					{
						doc = assinarXML.assinarXML(doc);
					}
					//NFeVO e = new NFeVO();
					cancNFeXML.setCancXMLString(DomHelper.docToXML(doc).toString());
					//e.setChaveAcesso(cancNFeXML.getChaveAcesso());
					
					nfe.setXmlCancelado('1');
					NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
					updateCommand.execute();
					
					XMLAssinadosPedCanc.getInstance().add(cancNFeXML);
					DomHelper.docToXML(doc,new File(config.getCancNFeXMLAssinados()+File.separatorChar+cancNFeXML.getChaveAcesso()+"ped-canc-ass.xml"));
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
				logger.error("Problemas ao interromper a Thread CancNFeAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
