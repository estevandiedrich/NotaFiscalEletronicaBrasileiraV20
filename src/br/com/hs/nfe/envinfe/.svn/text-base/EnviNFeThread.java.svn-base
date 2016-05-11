package br.com.hs.nfe.envinfe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeCreateCommand;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.parser.EnviNFeParser;
import br.com.hs.nfe.parser.EnviNFeParserA3;
import br.com.hs.nfe.queue.XMLGerados;
import br.com.hs.nfe.util.GeradorChaveAcesso;
import br.com.hs.nfe.util.TrataCaracter;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xpath.XPathUtil;

public class EnviNFeThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnviNFeThread");
	private static final GeradorChaveAcesso geradorChaveAcesso = new GeradorChaveAcesso();
	private Estabelecimento config = null;
	public EnviNFeThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnviNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnviNFeThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				if(PropriedadesSistema.getInstance().getSistema().getGerarXMLApartirDeTXT().equalsIgnoreCase("S"))
				{
					File[] enviNFeTXTArray = FileManager.getInstance().getTXTFiles(config.getEnviNFeTXT());
					for(File enviNFeTXT:enviNFeTXTArray)
					{
						String chaveAcesso = "";
						String cnpj = "";
						String dest = "";
						String serie = "";
						String nnf = "";
						String tpAmb = "";
						String cUF = "";
						String tpEmis = "";
						FileReader txt = new FileReader(enviNFeTXT);
						Document xml = DomHelper.createEmptyDocument();
						HashMap<String, String> params = new HashMap<String, String>();
						if(PropriedadesSistema.getInstance().getSistema().getFormatoNDD().equalsIgnoreCase("S"))
						{
							EnviNFeParser.txt2XmlParser(txt,xml,params);
							NodeList enviNFeNodeList = xml.getElementsByTagName("enviNFe");
							if(enviNFeNodeList.getLength() > 0)
							{
								Element enviNFe = (Element)enviNFeNodeList.item(0);
								String id = enviNFe.getAttribute("Id");
								if(id.equalsIgnoreCase(""))
								{
									geradorChaveAcesso.construirChaveAcesso(xml);
								}
							}
						}
						else
						{
							EnviNFeParserA3.txt2XmlParser(enviNFeTXT,xml,params);
						}
						ByteArrayOutputStream baos = DomHelper.docToXML(xml);
						chaveAcesso = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/@Id");
						logger.info("processando nota chave : "+chaveAcesso);
						cnpj = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:emit/:CNPJ");
						dest = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:dest/:CNPJ");
						serie = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:serie");
						nnf = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:nNF");
						tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:tpAmb");
						tpEmis = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:tpEmis");
						cUF = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:cUF");
						File sefazErpEnviNFe = new File(config.getEnviNFeXML()+File.separatorChar+chaveAcesso+".xml");
						IOUtils.closeQuietly(txt);
						IOUtils.closeQuietly(baos);
						enviNFeTXT.delete();
						DomHelper.docToXML(xml, sefazErpEnviNFe);
						
						Nfe nfeEntity = new Nfe();
						nfeEntity.setChaveAcesso(chaveAcesso);
						nfeEntity.setSerie(Integer.parseInt(serie));
						nfeEntity.setNnf(Integer.parseInt(nnf));
						nfeEntity.setCnpj(cnpj);
						nfeEntity.setDest(dest);
						nfeEntity.setPe(config.getPe());
						nfeEntity.setCstat("0");
						nfeEntity.setDanfeGerado('0');
						nfeEntity.setDanfeImpresso('0');
						nfeEntity.setDhRecbto(new Date());
						nfeEntity.setEmailEnviado('0');
						nfeEntity.setNprot("000000000000000");
						nfeEntity.setNrec("000000000000000");
						nfeEntity.setTxtRetornoGerado('0');
						nfeEntity.setTxtValido('1');
						nfeEntity.setXmlAssinado('0');
						if(tpEmis.equalsIgnoreCase("4"))
						{
							nfeEntity.setXmlEnviado('2');
						}
						else
						{
							nfeEntity.setXmlEnviado('0');
						}
						nfeEntity.setXmlProcessado('0');
						nfeEntity.setTxtRecebido('1');
						nfeEntity.setXmlValido('0');
						nfeEntity.setXmlCompactado('0');
						nfeEntity.setXmotivo("");
						nfeEntity.setXmlCancelado(' ');
						nfeEntity.setCodEstabelecimento(config.getCodEstabelecimento());
						nfeEntity.setEmail(params.get("email"));
						nfeEntity.setNumeroCopias(params.get("PrintNumber"));
						nfeEntity.setTpEmis(tpEmis.charAt(0));
						nfeEntity.setDpecGerado('0');
						nfeEntity.setDpecAssinado('0');
						nfeEntity.setDpecValido('0');
						nfeEntity.setDpecEnviado('0');
						nfeEntity.setDpecProcessado('0');
						nfeEntity.setTpAmb(tpAmb.charAt(0));
						nfeEntity.setDpecCompactado('0');
						nfeEntity.setDpecDanfeGerado('0');
						nfeEntity.setDanfeGerado('0');
						nfeEntity.setFsdaPreparado('0');
						nfeEntity.setFsdaCompactado('0');
						nfeEntity.setFsdaProcessado('0');
						nfeEntity.setFsdaDanfeGerado('0');
						nfeEntity.setFsdaImpresso('0');
						nfeEntity.setTxtRetornoDpecGerado('0');
						nfeEntity.setDpecEmailEnviado('0');
						nfeEntity.setCuf(cUF);
						nfeEntity.setZipEnviado('0');
						nfeEntity.setConsultaEventos('0');
						nfeEntity.setXmlPersistido('0');
						nfeEntity.setCancelarNota('0');
						
						NFeVO nomeConteudoVO = new NFeVO();
						nomeConteudoVO.setNfeXMLString(TrataCaracter.trata(DomHelper.docToXML(xml).toString())); 
						nomeConteudoVO.setChaveAcesso(chaveAcesso);
						XMLGerados.getInstance().add(nomeConteudoVO);
						
	//					Message msg = new Message(); 
	//					msg.setFrom("EnviNFeThread_"+config.getCnpj()+"_"+config.getPe());
	//					msg.setTo("EnviNFeAssinadorThread_"+config.getCnpj()+"_"+config.getPe());
	//					msg.setMessage(nomeConteudoVO);
	//					MessagesChannel.getInstance().add(msg);
						
						try
						{
							NFeCreateCommand createCommand = new NFeCreateCommand(nfeEntity);
							createCommand.execute();
						}
						catch(ConstraintViolationException e)
						{
							logger.error("! ConstraintViolationException !",e);
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfeEntity);
							updateCommand.execute();
						}
					}
				}
				else
				{
					File[] enviNFeXMLArray = FileManager.getInstance().getXMLFiles(config.getEnviNFeTXT());
					for(File enviNFeXML:enviNFeXMLArray)
					{
						String chaveAcesso = "";
						String cnpj = "";
						String dest = "";
						String serie = "";
						String nnf = "";
						String tpAmb = "";
						String cUF = "";
						String tpEmis = "";
						HashMap<String, String> params = new HashMap<String, String>();
						FileInputStream fis = new FileInputStream(enviNFeXML);
						Document xml = DomHelper.createDocument(fis);
						ByteArrayOutputStream baos = DomHelper.docToXML(xml);
						chaveAcesso = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/@Id");
						logger.info("processando nota chave : "+chaveAcesso);
						cnpj = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:emit/:CNPJ");
						dest = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:dest/:CNPJ");
						serie = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:serie");
						nnf = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:nNF");
						tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:tpAmb");
						tpEmis = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:tpEmis");
						cUF = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:enviNFe/:NFe/:infNFe/:ide/:cUF");
						File sefazErpEnviNFe = new File(config.getEnviNFeXML()+File.separatorChar+chaveAcesso+".xml");
						IOUtils.closeQuietly(fis);
						IOUtils.closeQuietly(baos);
						enviNFeXML.delete();
						DomHelper.docToXML(xml, sefazErpEnviNFe);
						
						Nfe nfeEntity = new Nfe();
						nfeEntity.setChaveAcesso(chaveAcesso);
						nfeEntity.setSerie(Integer.parseInt(serie));
						nfeEntity.setNnf(Integer.parseInt(nnf));
						nfeEntity.setCnpj(cnpj);
						nfeEntity.setDest(dest);
						nfeEntity.setPe(config.getPe());
						nfeEntity.setCstat("0");
						nfeEntity.setDanfeGerado('0');
						nfeEntity.setDanfeImpresso('0');
						nfeEntity.setDhRecbto(new Date());
						nfeEntity.setEmailEnviado('0');
						nfeEntity.setNprot("000000000000000");
						nfeEntity.setNrec("000000000000000");
						nfeEntity.setTxtRetornoGerado('0');
						nfeEntity.setTxtValido('1');
						nfeEntity.setXmlAssinado('0');
						if(tpEmis.equalsIgnoreCase("4"))
						{
							nfeEntity.setXmlEnviado('2');
						}
						else
						{
							nfeEntity.setXmlEnviado('0');
						}
						nfeEntity.setXmlProcessado('0');
						nfeEntity.setTxtRecebido('1');
						nfeEntity.setXmlValido('0');
						nfeEntity.setXmlCompactado('0');
						nfeEntity.setXmotivo("");
						nfeEntity.setXmlCancelado(' ');
						nfeEntity.setCodEstabelecimento(config.getCodEstabelecimento());
						nfeEntity.setEmail(params.get("email"));
						nfeEntity.setNumeroCopias(params.get("PrintNumber"));
						nfeEntity.setTpEmis(tpEmis.charAt(0));
						nfeEntity.setDpecGerado('0');
						nfeEntity.setDpecAssinado('0');
						nfeEntity.setDpecValido('0');
						nfeEntity.setDpecEnviado('0');
						nfeEntity.setDpecProcessado('0');
						nfeEntity.setTpAmb(tpAmb.charAt(0));
						nfeEntity.setDpecCompactado('0');
						nfeEntity.setDpecDanfeGerado('0');
						nfeEntity.setDanfeGerado('0');
						nfeEntity.setFsdaPreparado('0');
						nfeEntity.setFsdaCompactado('0');
						nfeEntity.setFsdaProcessado('0');
						nfeEntity.setFsdaDanfeGerado('0');
						nfeEntity.setFsdaImpresso('0');
						nfeEntity.setTxtRetornoDpecGerado('0');
						nfeEntity.setDpecEmailEnviado('0');
						nfeEntity.setCuf(cUF);
						nfeEntity.setZipEnviado('0');
						nfeEntity.setConsultaEventos('0');
						nfeEntity.setXmlPersistido('0');
						nfeEntity.setCancelarNota('0');
						
						NFeVO nomeConteudoVO = new NFeVO();
						nomeConteudoVO.setNfeXMLString(TrataCaracter.trata(DomHelper.docToXML(xml).toString())); 
						nomeConteudoVO.setChaveAcesso(chaveAcesso);
						XMLGerados.getInstance().add(nomeConteudoVO);
						
	//					Message msg = new Message(); 
	//					msg.setFrom("EnviNFeThread_"+config.getCnpj()+"_"+config.getPe());
	//					msg.setTo("EnviNFeAssinadorThread_"+config.getCnpj()+"_"+config.getPe());
	//					msg.setMessage(nomeConteudoVO);
	//					MessagesChannel.getInstance().add(msg);
						
						try
						{
							NFeCreateCommand createCommand = new NFeCreateCommand(nfeEntity);
							createCommand.execute();
						}
						catch(ConstraintViolationException e)
						{
							logger.error("! ConstraintViolationException !",e);
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfeEntity);
							updateCommand.execute();
						}
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
				logger.error("Problemas ao interromper a Thread EnviNFeThread", e);
			}
			finally
			{
				
			}
		}
	}
}
