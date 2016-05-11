package br.com.hs.nfe.cte;

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
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dao.CteDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Cte;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.CTeUpdateCommand;
import br.com.hs.nfe.queue.CTeValidos;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.util.TrataCaracter;
import br.com.hs.nfe.vo.NFeVO;

public class ConsSitCteThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("ConsSitCteThread");
	private Estabelecimento config = null;
	private br.com.hs.cte.ws.sc.homo.cteconsulta.CteConsultaHelper cteConsultaHelperHomo = null;
	private br.com.hs.cte.ws.sc.prod.cteconsulta.CteConsultaHelper cteConsultaHelperProd = null;
	private CteDAO cteDao = new CteDAO();
	public ConsSitCteThread(Estabelecimento config)
	{
		this.config = config;
		this.cteConsultaHelperHomo = new br.com.hs.cte.ws.sc.homo.cteconsulta.CteConsultaHelper(config);
		this.cteConsultaHelperProd = new br.com.hs.cte.ws.sc.prod.cteconsulta.CteConsultaHelper(config);
	}
	public void run()
	{
		Thread.currentThread().setName( "ConsSitCteThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread ConsSitCteThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Cte> cteList = cteDao.procuraCTes(config.getCnpj());
				for(Cte cte:cteList)
				{
					NFeVO enviNFeXML = CTeValidos.getInstance().getNFeVOPorChaveAcesso(cte.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File sefazErpEnviNFe = new File(PropriedadesSistema.getInstance().getSistema().getCteEntradaValido()+File.separatorChar+cte.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
						enviNFeXML.setChaveAcesso(cte.getChaveAcesso());
						byte[] ba = IOUtils.toByteArray(fis);
						enviNFeXML.setTpAmb(cte.getTpAmb().toString());
						enviNFeXML.setNfeXMLString(new String(ba));
						IOUtils.closeQuietly(fis);
					}
					String consSitCTe = ConsSitCteDocumentBuilder.consSitCteDocumentBuilder(enviNFeXML);
					String retConsSitCTe = "";
					if(cte.getCuf().equalsIgnoreCase("42"))
					{
						if(cte.getTpEmis() == '1')
						{
							if(cte.getTpAmb() == '1')
							{
								retConsSitCTe = TrataCaracter.trata(this.cteConsultaHelperProd.cteConsultaCT(consSitCTe));
							}
							else
							{
								retConsSitCTe = TrataCaracter.trata(this.cteConsultaHelperHomo.cteConsultaCT(consSitCTe));
							}
						}
						
					}
					Document retConsSitCTeDocument = DomHelper.toDocument(retConsSitCTe.getBytes());
					NodeList protCTeNL = retConsSitCTeDocument.getElementsByTagName("protCTe");
					Element protCTe = null;
					if(protCTeNL.getLength() > 0)
					{
						protCTe = (DeferredElementNSImpl)protCTeNL.item(0);
					}
					NodeList cStatNL = protCTe.getElementsByTagName("cStat");
					Element cStat = null;
					if(cStatNL.getLength() > 0)
					{
						cStat = (DeferredElementNSImpl)cStatNL.item(0);
					}
					NodeList xMotivoNL = protCTe.getElementsByTagName("xMotivo");
					Element xMotivo = null;
					if(xMotivoNL.getLength() > 0)
					{
						xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
					}
					NodeList dhRecbtoNL = protCTe.getElementsByTagName("dhRecbto");
					Element dhRecbto = null;
					if(dhRecbtoNL.getLength() > 0)
					{
						dhRecbto = (DeferredElementNSImpl)dhRecbtoNL.item(0);
					}
					//TODO: converter data e hora e gravar na base
					NodeList nProtNL = protCTe.getElementsByTagName("nProt");
					Element nProt = null;
					if(nProtNL.getLength() > 0)
					{
						nProt = (DeferredElementNSImpl)nProtNL.item(0);
					}
					NodeList digValNL = protCTe.getElementsByTagName("digVal");
					Element digVal = null;
					if(digValNL.getLength() > 0)
					{
						digVal = (DeferredElementNSImpl)digValNL.item(0);
					}
					if(cStat.getTextContent().equalsIgnoreCase("100"))
					{
						if(cte.getDigVal().equalsIgnoreCase(digVal.getTextContent()))
						{
							NFeVO e = new NFeVO();
							e.setNfeXMLString(enviNFeXML.getNfeXMLString());
							e.setChaveAcesso(enviNFeXML.getChaveAcesso());
							e.setRetConsReciNFe(retConsSitCTe);
							
							cte.setCstat("100");
							cte.setXmotivo(xMotivo.getTextContent());
							cte.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
							cte.setNprot(nProt.getTextContent());
							
							//XMLAutorizados.getInstance().add(e);
							//XML2Txt.getInstance().add(e);
							FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getCteEntradaAutorizado()+File.separatorChar+cte.getChaveAcesso()+".xml", retConsSitCTe);
							
							CTeUpdateCommand updateCommand = new CTeUpdateCommand(cte);
							updateCommand.execute();
						}
						else
						{
							
						}
					}
					else
					{
						cte.setCstat(cStat.getTextContent());
						cte.setXmotivo(xMotivo.getTextContent());
						cte.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
						
						FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getCteEntradaDenegado()+File.separatorChar+cte.getChaveAcesso()+".xml", retConsSitCTe);
						
						CTeUpdateCommand updateCommand = new CTeUpdateCommand(cte);
						updateCommand.execute();
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
				logger.error("Problemas ao interromper a Thread ConsSitCteThread", e);
			}
			finally
			{
				
			}
		}
	}
}
