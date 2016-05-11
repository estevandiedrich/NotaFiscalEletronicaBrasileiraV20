package br.com.hs.nfe.inutnfe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.InutNfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.InutNFeCreateCommand;
import br.com.hs.nfe.parser.InutNFeParser;
import br.com.hs.nfe.queue.XMLGeradosPedInut;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xpath.XPathUtil;

public class InutNFeThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("InutNFeThread");
	private Estabelecimento config = null;
	public InutNFeThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "InutNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread InutNFeThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				File[] inutNFeTXTArray = FileManager.getInstance().getTXTFiles(config.getInutNFeTXT());
				for(File inutNFeTXT:inutNFeTXTArray)
				{
					FileReader txt = new FileReader(inutNFeTXT);
					Document xml = DomHelper.createEmptyDocument();
					InutNFeParser.txt2XmlParser(txt,xml);
					ByteArrayOutputStream baos = DomHelper.docToXML(xml);
					String id = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/@Id");
					File sefazErpInutNFe = new File(config.getInutNFeXML()+File.separatorChar+id+"ped-inut.xml");
					IOUtils.closeQuietly(txt);
					IOUtils.closeQuietly(baos);
					DomHelper.docToXML(xml, sefazErpInutNFe);
					inutNFeTXT.delete();
					
					InutNfe inutNfe = new InutNfe();
					inutNfe.setId(id);
					inutNfe.setAno(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/:ano"));
					inutNfe.setCnpj(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/:CNPJ"));
					inutNfe.setCstat("0");
					inutNfe.setDhRecbto(new Date());
					inutNfe.setNnfFin(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/:nNFFin"));
					inutNfe.setNnfIni(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/:nNFIni"));
					inutNfe.setNprot("000000000000000");
					inutNfe.setPe(config.getPe());
					inutNfe.setSerie(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/:serie"));
					inutNfe.setXjust(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/:xJust"));
					inutNfe.setXmotivo("");
					inutNfe.setXmlInutilizado('0');
					inutNfe.setCodEstabelecimento(config.getCodEstabelecimento());
					inutNfe.setCuf(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/:cUF"));
					inutNfe.setTpAmb(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:inutNFe/:infInut/:tpAmb").charAt(0));
					inutNfe.setTpEmis('1');
					
					InutNFeCreateCommand createCommand = new InutNFeCreateCommand(inutNfe);
					createCommand.execute();
					
					NFeVO nomeConteudoVO = new NFeVO();
					nomeConteudoVO.setInutXMLString(DomHelper.docToXML(xml).toString()); 
					nomeConteudoVO.setId(id);
					XMLGeradosPedInut.getInstance().add(nomeConteudoVO);
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
				logger.error("Problemas ao interromper a Thread InutNFeThread", e);
			}
			finally
			{
				
			}
		}
	}
}
