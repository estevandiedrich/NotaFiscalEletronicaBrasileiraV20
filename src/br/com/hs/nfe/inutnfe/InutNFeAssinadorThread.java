package br.com.hs.nfe.inutnfe;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.InutNFeDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.InutNfe;
import br.com.hs.nfe.hb.InutNFeUpdateCommand;
import br.com.hs.nfe.queue.XMLAssinadosPedInut;
import br.com.hs.nfe.queue.XMLGeradosPedInut;
import br.com.hs.nfe.signer.AssinarXML;
import br.com.hs.nfe.vo.NFeVO;

public class InutNFeAssinadorThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("InutNFeAssinadorThread");
	private Estabelecimento config = null;
	private AssinarXML assinarXML = null;
	private InutNFeDAO inutNFeDao = null;
	public InutNFeAssinadorThread(Estabelecimento config)
	{
		this.assinarXML = new AssinarXML(config,"//:inutNFe/:infInut/@Id");
		this.inutNFeDao = new InutNFeDAO();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "InutNFeAssinadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread InutNFeAssinadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<InutNfe> inutNFeList = inutNFeDao.procuraInutilizacoesGeradas(config.getCnpj(),config.getPe());
				for(InutNfe inutNFe:inutNFeList)
				{
					NFeVO inutNFeXML = XMLGeradosPedInut.getInstance().getNFeVOPorId(inutNFe.getId());
					//TODO: Caso não exista essa nota na memoria é necessario procura-la no disco (pasta)
					if(inutNFeXML == null)
					{
						inutNFeXML = new NFeVO();
						File sefazErpInutNFe = new File(config.getInutNFeXML()+File.separatorChar+inutNFe.getId()+"ped-inut.xml");
						FileInputStream fis = new FileInputStream(sefazErpInutNFe);
						inutNFeXML.setId(inutNFe.getId());
						inutNFeXML.setInutXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					Document doc = DomHelper.toDocument(inutNFeXML.getInutXMLString().getBytes());
					
					doc = assinarXML.assinarXML(doc);
					
					inutNFeXML.setInutXMLString(DomHelper.docToXML(doc).toString());
					
					inutNFe.setXmlInutilizado('1');
					InutNFeUpdateCommand updateCommand = new InutNFeUpdateCommand(inutNFe);
					updateCommand.execute();
					
					DomHelper.docToXML(doc,new File(config.getInutNFeXMLAssinados()+File.separatorChar+inutNFeXML.getId()+"ped-inut-ass.xml"));
					XMLAssinadosPedInut.getInstance().add(inutNFeXML);
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
				logger.error("Problemas ao interromper a Thread InutNFeAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
