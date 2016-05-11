package br.com.hs.nfe.dpec;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.apache.xerces.dom.ElementImpl;
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
import br.com.hs.nfe.queue.Dpec2Txt;
import br.com.hs.nfe.queue.DpecAutorizados;
import br.com.hs.nfe.queue.DpecEnviados;
import br.com.hs.nfe.queue.DpecValidados;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.vo.NFeVO;

public class EnvDpecEnviadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnvDpecEnviadorThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	private br.com.hs.nfe.ws.br.homo.scerecepcaorfb.SCERecepcaoRFBHelper sceRecepcaoDpecHelperHomo;
	private br.com.hs.nfe.ws.br.prod.scerecepcaorfb.SCERecepcaoRFBHelper sceRecepcaoDpecHelperProd;
	public EnvDpecEnviadorThread(Estabelecimento config)
	{
		this.sceRecepcaoDpecHelperHomo = new br.com.hs.nfe.ws.br.homo.scerecepcaorfb.SCERecepcaoRFBHelper(config);
		this.sceRecepcaoDpecHelperProd = new br.com.hs.nfe.ws.br.prod.scerecepcaorfb.SCERecepcaoRFBHelper(config);
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvDpecEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvDpecEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraDpecValidos(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = DpecValidados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File envDpec = new File(config.getEnvDpecXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-val.xml");
						FileInputStream fis = new FileInputStream(envDpec);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setDpecXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					String retEnvDpec = "";
					try
					{
						if(nfe.getTpAmb()=='1')
						{
							retEnvDpec = sceRecepcaoDpecHelperProd.sceRecepcaoDPEC(enviNFeXML.getChaveAcesso(),enviNFeXML.getDpecXMLString());
						}
						if(nfe.getTpAmb()=='2')
						{
							retEnvDpec = sceRecepcaoDpecHelperHomo.sceRecepcaoDPEC(enviNFeXML.getChaveAcesso(),enviNFeXML.getDpecXMLString());
						}
					}
					catch(Exception e)
					{
						logger.error("Erro ao enviar dpec",e);
						if(config.getAtivarFsdaAutomatico() == '1')
						{
							nfe.setXmlEnviado('3');
							nfe.setXmotivo("FSDA");
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
						else
						{
							
						}
					}
					//TODO: Ler o nRec e armazenar em banco de dados
					Document retDPECDoc = DomHelper.toDocument(retEnvDpec.getBytes());
					NodeList nRegDPECNL = retDPECDoc.getElementsByTagName("nRegDPEC");
					NodeList dhRegDPECNL = retDPECDoc.getElementsByTagName("dhRegDPEC");
					NodeList tpAmbNL = retDPECDoc.getElementsByTagName("tpAmb");
					NodeList cStatNL = retDPECDoc.getElementsByTagName("cStat");
					NodeList xMotivoNL = retDPECDoc.getElementsByTagName("xMotivo");
					Element dhRegDPEC = null;
					if(dhRegDPECNL.getLength() > 0)
					{
						dhRegDPEC = (DeferredElementNSImpl)dhRegDPECNL.item(0);
					}
					Element nRegDPEC = null;
					if(nRegDPECNL.getLength() > 0)
					{
						nRegDPEC = (DeferredElementNSImpl)nRegDPECNL.item(0);
					}
					Element tpAmb = null;
					if(tpAmbNL.getLength() > 0)
					{
						tpAmb = (ElementImpl)tpAmbNL.item(0);
					}
					Element cStat = null;
					if(cStatNL.getLength() > 0)
					{
						cStat = (ElementImpl)cStatNL.item(0);
					}
					Element xMotivo = null;
					if(xMotivoNL.getLength() > 0)
					{
						xMotivo = (ElementImpl)xMotivoNL.item(0);
					}
					if(nRegDPEC != null)
					{
						enviNFeXML.setNRec(nRegDPEC.getTextContent());
						nfe.setNrec(enviNFeXML.getNRec());
					}
					if(dhRegDPEC != null)
						nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRegDPEC.getTextContent()));
					
					enviNFeXML.setTpAmb(tpAmb.getTextContent());

					nfe.setCstat(cStat.getTextContent());
					nfe.setXmotivo(xMotivo.getTextContent());
					nfe.setDpecEnviado('1');
					
					if(cStat.getTextContent().equalsIgnoreCase("124"))
					{
						enviNFeXML.setRetDpecXMLString(retEnvDpec);
						DpecAutorizados.getInstance().add(enviNFeXML);
						Dpec2Txt.getInstance().add(enviNFeXML);
						FileManager.getInstance().saveFile(config.getEnvDpecXMLAutorizados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-dpec-aut.xml", retEnvDpec);
					}
					else
					{
						DpecEnviados.getInstance().add(enviNFeXML);
						FileManager.getInstance().saveFile(config.getEnvDpecXMLEnviados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-ret-dpec.xml", retEnvDpec);
					}
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
				logger.error("Problemas ao interromper a Thread EnvDpecEnviadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
