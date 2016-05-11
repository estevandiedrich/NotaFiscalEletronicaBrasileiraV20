package br.com.hs.nfe.cancnfe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.parser.CancNFeParser;
import br.com.hs.nfe.queue.XMLGeradosPedCanc;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xpath.XPathUtil;

public class CancNFeThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("CancNFeThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDAO = null;
	public CancNFeThread(Estabelecimento config)
	{
		this.nfeDAO = new NFeDAO(); 
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "CancNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread CancNFeThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				File[] cancNFeTXTArray = FileManager.getInstance().getTXTFiles(config.getCancNFeTXT());
				for(File cancNFeTXT:cancNFeTXTArray)
				{
					FileReader txt = new FileReader(cancNFeTXT);
					Document xml = DomHelper.createEmptyDocument();
					CancNFeParser.txt2XmlParser(txt,xml);
					ByteArrayOutputStream baos = DomHelper.docToXML(xml);
					String chaveAcesso = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:cancNFe/:infCanc/:chNFe");
					String xjust = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:cancNFe/:infCanc/:xJust");
					File sefazErpCancNFe = new File(config.getCancNFeXML()+File.separatorChar+"NFe"+chaveAcesso+"ped-canc.xml");
					IOUtils.closeQuietly(txt);
					IOUtils.closeQuietly(baos);
					DomHelper.docToXML(xml, sefazErpCancNFe);
					cancNFeTXT.delete();
					
					Nfe nfe = nfeDAO.findByChaveAcesso("NFe"+chaveAcesso);
					nfe.setXjust(xjust);
					nfe.setXmlCancelado('0');
					
					NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
					updateCommand.execute();
					
					NFeVO nomeConteudoVO = new NFeVO();
					nomeConteudoVO.setCancXMLString(DomHelper.docToXML(xml).toString()); 
					nomeConteudoVO.setChaveAcesso("NFe"+chaveAcesso);
					XMLGeradosPedCanc.getInstance().add(nomeConteudoVO);
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
				logger.error("Problemas ao interromper a Thread CancNFeThread", e);
			}
			finally
			{
				
			}
		}
	}
}
