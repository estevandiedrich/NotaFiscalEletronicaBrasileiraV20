package br.com.hs.nfe.xslt;

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
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.DpecGerados;
import br.com.hs.nfe.queue.GerarDpec;
import br.com.hs.nfe.util.GeradorChaveAcesso;
import br.com.hs.nfe.util.InsereXjustDhCont;
import br.com.hs.nfe.vo.NFeVO;

public class XsltNFeToDpecThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("XsltNFeToDpecThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	private XsltNFeToTxt xlstNFeToDpec = null;
	private GeradorChaveAcesso geradorChaveAcesso = null;
	public XsltNFeToDpecThread(Estabelecimento config)
	{
		this.xlstNFeToDpec = new XsltNFeToTxt("nfe2Dpec.xsl");
		this.geradorChaveAcesso = new GeradorChaveAcesso();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "XsltNFeToDpecThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread XsltNFeToDpecThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraDpec(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = GerarDpec.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File xml = new File(config.getEnviNFeXML()+File.separatorChar+nfe.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(xml);
						enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
					}
					
					Document doc = DomHelper.toDocument(enviNFeXML.getNfeXMLString().getBytes());
					String chaveAcesso = geradorChaveAcesso.construirChaveAcesso(doc, "4",nfe.getChaveAcesso(),config);
					InsereXjustDhCont.insereXjustDhCont(doc);
					enviNFeXML.setNfeXMLString(DomHelper.docToXML(doc).toString());
					byte[] dpec = xlstNFeToDpec.transformar(enviNFeXML.getNfeXMLString().getBytes());
					
					//A nota de origem não sera mais tratada
					nfe.setTpEmis('4');
					NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
					updateCommand.execute();
					
					nfe.setChaveAcesso(chaveAcesso);
					nfe.setXmlValido('0');
					nfe.setXmlAssinado('0');
					nfe.setDpecGerado('1');
					if(config.getImprimirDanfeDpecReenvio() == '0')
					{
						nfe.setDanfeGerado('1');
						nfe.setDanfeImpresso('1');
					}
					updateCommand = new NFeUpdateCommand(nfe);
					updateCommand.execute();

					enviNFeXML.setChaveAcesso(chaveAcesso);
					enviNFeXML.setDpecXMLString(new String(dpec));
					
					DpecGerados.getInstance().add(enviNFeXML);
					FileManager.getInstance().saveFile(config.getEnviNFeXML()+File.separatorChar+nfe.getChaveAcesso()+".xml",enviNFeXML.getNfeXMLString());
					FileManager.getInstance().saveFile(config.getEnvDpecXML()+File.separatorChar+nfe.getChaveAcesso()+"-dpec.xml", enviNFeXML.getDpecXMLString());
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
				logger.error("Problemas ao interromper a Thread XsltNFeToDpecThread", e);
			}
			finally
			{
				
			}
		}
	}
}
