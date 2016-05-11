package br.com.hs.nfe.xslt;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLCanc2Txt;
import br.com.hs.nfe.vo.NFeVO;

public class XsltCancToTxtThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("XsltCancToTxtThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	private XsltNFeToTxt xlstNFeToTxt = null;
	private SimpleDateFormat sdf = null;
	private SimpleDateFormat stf = null;
	public XsltCancToTxtThread(Estabelecimento config)
	{
		this.xlstNFeToTxt = new XsltNFeToTxt("retcanc.to.txt.xsl");
		this.config = config;
		this.sdf = new SimpleDateFormat("yyyyMMdd");
		this.stf = new SimpleDateFormat("HHmmss");
	}
	public void run()
	{
		Thread.currentThread().setName( "XlstCancToTxtThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread XlstCancToTxtThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				//for(ConfigVO config:Config.getInstance().configs)
				//{
					List<Nfe> nfeList = nfeDao.procuraCancelamentoRetornoPendente(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO cancNFeXML = XMLCanc2Txt.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(cancNFeXML == null)
						{
							cancNFeXML = new NFeVO();
							File retCancNFe = new File(config.getCancNFeXMLAutorizados()+File.separatorChar+nfe.getChaveAcesso()+"-retCancNFe.xml");
							FileInputStream fis = new FileInputStream(retCancNFe);
							cancNFeXML.setRetCancNFe(IOUtils.toString(fis));
							cancNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						}
						byte[] txt = xlstNFeToTxt.transformar(cancNFeXML.getRetCancNFe().getBytes());
						
						nfe.setXmlCancelado('6');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
						
						FileManager.getInstance().saveFile(config.getRetCancNFeTXT()+File.separatorChar+cancNFeXML.getChaveAcesso()+"_"+sdf.format(nfe.getDhRecbto())+"_"+stf.format(nfe.getDhRecbto())+"_canc.txt", new String(txt));
						cancNFeXML = null;
					}
				//}
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
