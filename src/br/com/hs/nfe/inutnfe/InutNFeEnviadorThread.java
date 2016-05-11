package br.com.hs.nfe.inutnfe;

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
import br.com.hs.nfe.dao.InutNFeDAO;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.InutNfe;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.filtro.NotaFiscalFiltro;
import br.com.hs.nfe.hb.InutNFeUpdateCommand;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLAutorizadosInut;
import br.com.hs.nfe.queue.XMLValidadosPedInut;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.vo.NFeVO;

public class InutNFeEnviadorThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("CancNFeEnviadorThread");
	private Estabelecimento config = null;
	private InutNFeDAO inutNFeDao = new InutNFeDAO();
	private br.com.hs.nfe.ws.sc.homo.nfeinutilizacao2.NfeInutilizacao2Helper nfeInutilizacao2HelperHomo = null;
	private br.com.hs.nfe.ws.sc.prod.nfeinutilizacao2.NfeInutilizacao2Helper nfeInutilizacao2HelperProd = null;
	public InutNFeEnviadorThread(Estabelecimento config)
	{
		this.nfeInutilizacao2HelperHomo = new br.com.hs.nfe.ws.sc.homo.nfeinutilizacao2.NfeInutilizacao2Helper(config);
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "InutNFeEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread InutNFeEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
					List<InutNfe> inutNFeList = inutNFeDao.procuraInutilizacaoValida(config.getCnpj(),config.getPe());
					for(InutNfe inutNFe:inutNFeList)
					{
						NFeVO inutNFeXML = XMLValidadosPedInut.getInstance().getNFeVOPorId(inutNFe.getId());
						if(inutNFeXML == null)
						{
							inutNFeXML = new NFeVO();
							File sefazErpInutNFe = new File(config.getInutNFeXMLValidos()+File.separatorChar+inutNFe.getId()+"ped-inut-val.xml");
							FileInputStream fis = new FileInputStream(sefazErpInutNFe);
							inutNFeXML.setId(inutNFe.getId());
							inutNFeXML.setInutXMLString(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						String retInutNFe = "";
						if(inutNFe.getCuf().equalsIgnoreCase("42"))
						{
							if(inutNFe.getTpAmb()=='1')
							{
								retInutNFe = this.nfeInutilizacao2HelperProd.nfeInutilizacao2(inutNFeXML.getId(),inutNFeXML.getInutXMLString());
							}
							if(inutNFe.getTpAmb()=='2')
							{
								retInutNFe = this.nfeInutilizacao2HelperHomo.nfeInutilizacao2(inutNFeXML.getId(),inutNFeXML.getInutXMLString());
							}
						}
						
						//TODO: Ler o nRec e armazenar em banco de dados
						Document retInutNFeDocument = DomHelper.toDocument(retInutNFe.getBytes());
						NodeList infInutNL = retInutNFeDocument.getElementsByTagName("infInut");
						Element infInut = null;
						if(infInutNL.getLength() > 0)
						{
							infInut = (DeferredElementNSImpl)infInutNL.item(0);
						}
						NodeList cStatNL = infInut.getElementsByTagName("cStat");
						Element cStat = null;
						if(cStatNL.getLength() > 0)
						{
							cStat = (DeferredElementNSImpl)cStatNL.item(0);
						}
						NodeList xMotivoNL = infInut.getElementsByTagName("xMotivo");
						Element xMotivo = null;
						if(xMotivoNL.getLength() > 0)
						{
							xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
						}
						NodeList dhRecbtoNL = infInut.getElementsByTagName("dhRecbto");
						Element dhRecbto = null;
						if(dhRecbtoNL.getLength() > 0)
						{
							dhRecbto = (DeferredElementNSImpl)dhRecbtoNL.item(0);
						}
						//TODO: converter data e hora e gravar na base
						NodeList nProtNL = infInut.getElementsByTagName("nProt");
						Element nProt = null;
						if(nProtNL.getLength() > 0)
						{
							nProt = (DeferredElementNSImpl)nProtNL.item(0);
						}
						
						if(cStat.getTextContent().equalsIgnoreCase("102"))
						{
							
							inutNFeXML.setRetInutNFe(retInutNFe);
							
							inutNFe.setCstat("102");
							inutNFe.setXmotivo(xMotivo.getTextContent());
							inutNFe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
							inutNFe.setNprot(nProt.getTextContent());
							inutNFe.setXmlInutilizado('4');
							
							InutNFeUpdateCommand updateCommand = new InutNFeUpdateCommand(inutNFe);
							updateCommand.execute();
							
							XMLAutorizadosInut.getInstance().add(inutNFeXML);
							FileManager.getInstance().saveFile(config.getInutNFeXMLAutorizados()+File.separatorChar+inutNFeXML.getId()+"-retInutNFe.xml", retInutNFe);
							
							NFeDAO nfeDAO = new NFeDAO(); 
							NotaFiscalFiltro nff = new NotaFiscalFiltro();
							nff.setnNFInicial(inutNFe.getNnfIni());
							nff.setnNFFinal(inutNFe.getNnfFin());
							nff.setSerieInicial(inutNFe.getSerie());
							nff.setSerieFinal(inutNFe.getSerie());
							nff.setCnpj(inutNFe.getCnpj());
							List<Nfe> nfeList = nfeDAO.findByParametros(nff);
							NFeUpdateCommand nfeUpdateCommand = null;
							for(Nfe e:nfeList)
							{
								e.setCstat(inutNFe.getCstat());
								e.setXmotivo(inutNFe.getXmotivo());
								nfeUpdateCommand = new NFeUpdateCommand(e);
								nfeUpdateCommand.execute();
							}
						}
						else
						{
							inutNFe.setCstat(cStat.getTextContent());			
							inutNFe.setXmotivo(xMotivo.getTextContent());
							inutNFe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
							inutNFe.setXmlInutilizado('5');
							
							InutNFeUpdateCommand updateCommand = new InutNFeUpdateCommand(inutNFe);
							updateCommand.execute();
							
							FileManager.getInstance().saveFile(config.getInutNFeXMLDenegados()+File.separatorChar+inutNFeXML.getChaveAcesso()+"-retInutNFe.xml", retInutNFe);
						}
					}
				//}
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
				logger.error("Problemas ao interromper a Thread InutNFeEnviadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
