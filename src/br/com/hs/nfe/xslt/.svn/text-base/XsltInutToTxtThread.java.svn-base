package br.com.hs.nfe.xslt;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.InutNFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.InutNfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.InutNFeUpdateCommand;
import br.com.hs.nfe.queue.XMLAutorizadosInut;
import br.com.hs.nfe.vo.NFeVO;

public class XsltInutToTxtThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("XsltInutToTxtThread");
	private InutNFeDAO inutNFeDao = new InutNFeDAO();
	private Estabelecimento config = null;
	private XsltNFeToTxt xlstNFeToTxt = null;
	private SimpleDateFormat sdf = null;
	private SimpleDateFormat stf = null;
	public XsltInutToTxtThread(Estabelecimento config)
	{
		this.xlstNFeToTxt = new XsltNFeToTxt("retinut.to.txt.xsl");
		this.config = config;
		this.sdf = new SimpleDateFormat("yyyyMMdd");
		this.stf = new SimpleDateFormat("HHmmss");
	}
	public void run()
	{
		Thread.currentThread().setName( "XlstInutToTxtThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread XlstInutToTxtThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{

				List<InutNfe> inutNFeList = inutNFeDao.procuraInutilizacaoRetornoPendente(config.getCnpj(),config.getPe());
				for(InutNfe inutNFe:inutNFeList)
				{
					NFeVO inutNFeXML = XMLAutorizadosInut.getInstance().getNFeVOPorId(inutNFe.getId());
					if(inutNFeXML == null)
					{
						inutNFeXML = new NFeVO();
						File retInutNFe = new File(config.getInutNFeXMLAutorizados()+File.separatorChar+inutNFe.getId()+"-retInutNFe.xml");
						FileInputStream fis = new FileInputStream(retInutNFe);
						inutNFeXML.setRetInutNFe(IOUtils.toString(fis));
						inutNFeXML.setId(inutNFe.getId());
					}
					byte[] txt = xlstNFeToTxt.transformar(inutNFeXML.getRetInutNFe().getBytes());
					
					inutNFe.setXmlInutilizado('6');
					InutNFeUpdateCommand updateCommand = new InutNFeUpdateCommand(inutNFe);
					updateCommand.execute();
					
					FileManager.getInstance().saveFile(config.getRetInutNFeTXT()+File.separatorChar+inutNFeXML.getId()+"_"+sdf.format(inutNFe.getDhRecbto())+"_"+stf.format(inutNFe.getDhRecbto())+"_inut.txt", new String(txt));
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
				logger.error("Problemas ao interromper a Thread XsltNFeToTxtThread", e);
			}
			finally
			{
				
			}
		}
	}
}
