package br.com.hs.nfe.envinfe;

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
import br.com.hs.nfe.queue.XMLAssinados;
import br.com.hs.nfe.queue.XMLGerados;
import br.com.hs.nfe.signer.AssinarXML;
import br.com.hs.nfe.vo.NFeVO;

public class EnviNFeAssinadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnviNFeAssinadorThread");
	private Estabelecimento config = null;
	private AssinarXML assinarXML = null;
	private NFeDAO nfeDao = null;
	public EnviNFeAssinadorThread(Estabelecimento config)
	{
		this.assinarXML = new AssinarXML(config,"//:NFe/:infNFe/@Id");
		this.nfeDao = new NFeDAO();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnviNFeAssinadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnviNFeAssinadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraNotasGeradas(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLGerados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					//TODO: Caso n�o exista essa nota na memoria � necessario procura-la no disco (pasta)
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File sefazErpEnviNFe = new File(config.getEnviNFeXML()+File.separatorChar+nfe.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					Document doc = DomHelper.toDocument(enviNFeXML.getNfeXMLString().getBytes());
					
					if("A3".equalsIgnoreCase(config.getTipoCertificado()))
					{
						doc = assinarXML.assinarXMLA3RemovendoEnviNFe(doc);
					}
					else
					{
						doc = assinarXML.assinarXMLRemovendoEnviNFe(doc);
					}
					
					enviNFeXML.setNfeXMLString(DomHelper.docToXML(doc).toString());
					
					XMLAssinados.getInstance().add(enviNFeXML);
					DomHelper.docToXML(doc,new File(config.getEnviNFeXMLAssinados()+File.separatorChar+enviNFeXML.getChaveAcesso()+".xml"));
					
					nfe.setXmlAssinado('1');
					NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
					updateCommand.execute();
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
				logger.error("Problemas ao interromper a Thread EnviNFeAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
