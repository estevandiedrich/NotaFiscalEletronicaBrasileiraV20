package br.com.hs.nfe.consdpec;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.DpecAutorizados;
import br.com.hs.nfe.queue.DpecEnviados;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.vo.NFeVO;

public class ConsDpecThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("ConsDpecThread");
	private Estabelecimento config = null;
	private br.com.hs.nfe.ws.br.homo.sceconsultarfb.SCEConsultaRFBHelper sceConsultaDpecHelperHomo = null;
	private br.com.hs.nfe.ws.br.prod.sceconsultarfb.SCEConsultaRFBHelper sceConsultaDpecHelperProd = null;
	private NFeDAO nfeDao = new NFeDAO();
	public ConsDpecThread(Estabelecimento config)
	{
		this.sceConsultaDpecHelperHomo = new br.com.hs.nfe.ws.br.homo.sceconsultarfb.SCEConsultaRFBHelper(config);
		this.sceConsultaDpecHelperProd = new br.com.hs.nfe.ws.br.prod.sceconsultarfb.SCEConsultaRFBHelper(config);
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "ConsDpecThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread ConsDpecThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraDpecEnviados(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = DpecEnviados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File envDpec = new File(config.getEnvDpecXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-val.xml");
						FileInputStream fis = new FileInputStream(envDpec);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setDpecXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					String consDpec = ConsDpecDocumentBuilder.consDpecDocumentBuilder(nfe.getChaveAcesso(),nfe.getTpAmb().toString(),"101");
					String retConsDpec = "";
					if(nfe.getCuf().equalsIgnoreCase("42"))
					{
						if(nfe.getTpAmb() == '1')
						{
							retConsDpec = this.sceConsultaDpecHelperProd.sceConsultaRFB(nfe.getChaveAcesso(), consDpec);
						}
						else
						{
							retConsDpec = this.sceConsultaDpecHelperHomo.sceConsultaRFB(nfe.getChaveAcesso(), consDpec);
						}
					}
					Document retConsDpecDocument = DomHelper.toDocument(retConsDpec.getBytes());
					NodeList retDPECNL = retConsDpecDocument.getElementsByTagName("retDPEC");
					Element retDPEC = null;
					if(retDPECNL.getLength() > 0)
					{
						retDPEC = (DeferredElementNSImpl)retDPECNL.item(0);
					}
					NodeList cStatNL = retDPEC.getElementsByTagName("cStat");
					Element cStat = null;
					if(cStatNL.getLength() > 0)
					{
						cStat = (DeferredElementNSImpl)cStatNL.item(0);
					}
					NodeList xMotivoNL = retDPEC.getElementsByTagName("xMotivo");
					Element xMotivo = null;
					if(xMotivoNL.getLength() > 0)
					{
						xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
					}
					NodeList dhRegDPECNL = retDPEC.getElementsByTagName("dhRegDPEC");
					Element dhRegDPEC = null;
					if(dhRegDPECNL.getLength() > 0)
					{
						dhRegDPEC = (DeferredElementNSImpl)dhRegDPECNL.item(0);
					}
					//TODO: converter data e hora e gravar na base
					NodeList nRegDPECNL = retDPEC.getElementsByTagName("nRegDPEC");
					Element nRegDPEC = null;
					if(nRegDPECNL.getLength() > 0)
					{
						nRegDPEC = (DeferredElementNSImpl)nRegDPECNL.item(0);
					}
					if(cStat.getTextContent().equalsIgnoreCase("124"))
					{
						enviNFeXML.setRetDpecXMLString(retConsDpec);
						
						nfe.setCstat("124");
						nfe.setXmotivo(xMotivo.getTextContent());
						nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRegDPEC.getTextContent()));
						if(nRegDPEC != null)
							nfe.setNrec(nRegDPEC.getTextContent());
						
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
						
						DpecAutorizados.getInstance().add(enviNFeXML);
						FileManager.getInstance().saveFile(config.getEnvDpecXMLAutorizados()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-aut.xml", retConsDpec);
					}
					else
					{
						nfe.setCstat(cStat.getTextContent());			
						nfe.setXmotivo(xMotivo.getTextContent());
						nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRegDPEC.getTextContent()));
						
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
						
						FileManager.getInstance().saveFile(config.getEnvDpecXMLDenegados()+File.separatorChar+nfe.getChaveAcesso()+"-ret-cons-dpec.xml", retConsDpec);
					}
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
				logger.error("Problemas ao interromper a Thread ConsDpecThread", e);
			}
			finally
			{
				
			}
		}
	}
}
