package br.com.hs.nfe.recinfe;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.entity.NfeEntrada;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeEntradaCreateCommand;
import br.com.hs.nfe.hb.NFeEntradaUpdateCommand;
import br.com.hs.nfe.queue.XMLEntradaValido;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xml.ValidateXML;
import br.com.hs.nfe.xpath.XPathUtil;

public class ReciNFeValidadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("ReciNFeValidadorThread");
	private ValidateXML validateXML = null;
	private ValidateXML validateCancXML = null;
	public ReciNFeValidadorThread()
	{
		this.validateXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getProcNFeXSD());
		this.validateCancXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getProcCancXSD());
	}
	public void run()
	{
		Thread.currentThread().setName( "ReciNFeValidadorThread" );
		logger.info("Iniciando thread ReciNFeValidadorThread");
		while ( super.isRunning() ) 
		{
			try
			{
				File[] reciNFeXMLArray = FileManager.getInstance().getXMLFiles(PropriedadesSistema.getInstance().getSistema().getXmlEntrada());
				for(File reciNFeXML:reciNFeXMLArray)
				{
					FileInputStream fis = new FileInputStream(reciNFeXML);
					byte[] notaBa = IOUtils.toByteArray(fis);
					IOUtils.closeQuietly(fis);
					String notaString = new String(notaBa);
					String schemaError = "";
					if(notaString.contains("nfeProc"))
					{
						schemaError = this.validateXML.validateXml(new String(notaBa));
						if(schemaError.equalsIgnoreCase(""))
						{
							String chaveAcesso = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:NFe/:infNFe/@Id");
							String cnpj = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:NFe/:infNFe/:emit/:CNPJ");
							String dest = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:NFe/:infNFe/:dest/:CNPJ");
							String serie = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:NFe/:infNFe/:ide/:serie");
							String nnf = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:NFe/:infNFe/:ide/:nNF");
							String cUF = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:NFe/:infNFe/:ide/:cUF");
							String tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:NFe/:infNFe/:ide/:tpAmb");
							String tpEmis = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:NFe/:infNFe/:ide/:tpEmis");
							
	//						String nprot = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:nProt");
							String digVal = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:digVal");
	//					    String cstat = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:cStat");
	//					    String xmotivo = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:xMotivo");					    
	//					    Date dhRecbto  = NFeSimpleDateFormat.getInstance().parse(XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:dhRecbto"));
						    
						    NfeEntrada nfe = new NfeEntrada();
						    
						    nfe.setChaveAcesso(chaveAcesso);
						    nfe.setCnpj(cnpj);
						    nfe.setCuf(cUF);
						    nfe.setDest(dest);
						    nfe.setNnf(Integer.parseInt(nnf));
						    nfe.setSerie(Integer.parseInt(serie));
						    nfe.setTpAmb(tpAmb.charAt(0));
						    nfe.setTpEmis(tpEmis.charAt(0));
						    nfe.setCstat("103");
						    nfe.setDhRecbto(new Date());
						    nfe.setXmotivo("");
						    nfe.setNprot("");
						    nfe.setDigVal(digVal);
						    nfe.setDanfeGerado('0');
						    nfe.setTxtGerado('0');
						    nfe.setConsultaEventos('0');
						    nfe.setEventoConfirmacao('0');
						    
						    NFeVO e = new NFeVO();
						    e.setChaveAcesso(chaveAcesso);
						    e.setTpAmb(tpAmb);
						    e.setNfeProc(notaString);
						    
						    XMLEntradaValido.getInstance().add(e);
						    
						    FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getXmlEntradaValido()+File.separatorChar+nfe.getChaveAcesso()+".xml", notaString);
						    reciNFeXML.delete();
						    
						    try
							{
								NFeEntradaCreateCommand createCommand = new NFeEntradaCreateCommand(nfe);
								createCommand.execute();
							}
							catch(ConstraintViolationException e1)
							{
								logger.error("! ConstraintViolationException !",e1);
								NFeEntradaUpdateCommand updateCommand = new NFeEntradaUpdateCommand(nfe);
								updateCommand.execute();
							}
						}
						else
						{
							int inicioChaveAcesso = notaString.indexOf("Id=\"");
							String chaveAcesso =  notaString.substring(inicioChaveAcesso+4,inicioChaveAcesso+47+4);
							String tpAmb = notaString.substring(notaString.indexOf("<tpAmb>")+7,notaString.indexOf("</tpAmb>"));
							String tpEmis = notaString.substring(notaString.indexOf("<tpEmis>")+8, notaString.indexOf("</tpEmis>"));
							String cUF = notaString.substring(notaString.indexOf("<cUF>")+5, notaString.indexOf("</cUF>"));
							String serie = notaString.substring(notaString.indexOf("<serie>")+7, notaString.indexOf("</serie>"));
							String nnf = notaString.substring(notaString.indexOf("<nNF>")+5, notaString.indexOf("</nNF>"));
							String cnpj = notaString.substring(notaString.indexOf("<CNPJ>")+6,notaString.indexOf("</CNPJ>"));
							String dest = notaString.substring(notaString.indexOf("<dest>")+6,notaString.indexOf("</dest>"));
							dest = dest.substring(dest.indexOf("<CNPJ>")+6, dest.indexOf("</CNPJ>"));
							
							NfeEntrada nf = new NfeEntrada();
							nf.setChaveAcesso(chaveAcesso);
							nf.setTpAmb(tpAmb.charAt(0));
							nf.setTpEmis(tpEmis.charAt(0));
							nf.setCnpj(cnpj);
							nf.setDest(dest);
							nf.setCuf(cUF);
							nf.setSerie(Integer.valueOf(serie));
							nf.setNnf(Integer.valueOf(nnf));
							nf.setXmotivo(schemaError);
							nf.setCstat("215");
						    nf.setDhRecbto(new Date());
						    nf.setNprot("");
						    nf.setDanfeGerado('0');
						    nf.setTxtGerado('0');
							try
							{
								NFeEntradaCreateCommand createCommand = new NFeEntradaCreateCommand(nf);
								createCommand.execute();
							}
							catch(ConstraintViolationException e1)
							{
								logger.error("! ConstraintViolationException !",e1);
								NFeEntradaUpdateCommand updateCommand = new NFeEntradaUpdateCommand(nf);
								updateCommand.execute();
							}
							FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getXmlEntradaInvalido()+File.separatorChar+File.createTempFile("EntradaInvalida", ".xml").getName(), notaString);
						    reciNFeXML.delete();
						}
					}
					else if(notaString.contains("procCancNFe"))
					{
						schemaError = this.validateCancXML.validateXml(new String(notaBa)); 
						if(schemaError.equalsIgnoreCase(""))
						{
							String chaveAcesso = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancNFe/:retCancNFe/:infCanc/:chNFe");
							String tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancNFe/:retCancNFe/:infCanc/:tpAmb");
							String cUF = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancNFe/:retCancNFe/:infCanc/:cUF");
							String cStat = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancNFe/:retCancNFe/:infCanc/:cStat");
							String xMotivo = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancNFe/:retCancNFe/:infCanc/:xMotivo");
							String dhRecbto = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancNFe/:retCancNFe/:infCanc/:dhRecbto");
							String nProt = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancNFe/:retCancNFe/:infCanc/:nProt");
							
							NfeEntrada nfe = new NfeEntrada();
						    nfe.setChaveAcesso("NFe"+chaveAcesso);
						    nfe.setTpAmb(tpAmb.charAt(0));
						    nfe.setCuf(cUF);
						    nfe.setCstat(cStat);
						    nfe.setXmotivo(xMotivo);
						    nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto));
						    nfe.setNprot(nProt);
						    
						    FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getXmlEntradaValido()+File.separatorChar+nfe.getChaveAcesso()+".xml", notaString);
						    reciNFeXML.delete();
						    
						    try
							{
								NFeEntradaCreateCommand createCommand = new NFeEntradaCreateCommand(nfe);
								createCommand.execute();
							}
							catch(ConstraintViolationException e1)
							{
								logger.error("! ConstraintViolationException !",e1);
								NFeEntradaUpdateCommand updateCommand = new NFeEntradaUpdateCommand(nfe);
								updateCommand.execute();
							}
						}
						else
						{
							int inicioChaveAcesso = notaString.indexOf("Id=\"");
							String chaveAcesso =  notaString.substring(inicioChaveAcesso+4,inicioChaveAcesso+47+4);
							String tpAmb = notaString.substring(notaString.indexOf("<tpAmb>")+7,notaString.indexOf("</tpAmb>"));
							String tpEmis = notaString.substring(notaString.indexOf("<tpEmis>")+8, notaString.indexOf("</tpEmis>"));
							String cUF = notaString.substring(notaString.indexOf("<cUF>")+5, notaString.indexOf("</cUF>"));
							String serie = notaString.substring(notaString.indexOf("<serie>")+7, notaString.indexOf("</serie>"));
							String nnf = notaString.substring(notaString.indexOf("<nNF>")+5, notaString.indexOf("</nNF>"));
							String cnpj = notaString.substring(notaString.indexOf("<CNPJ>")+6,notaString.indexOf("</CNPJ>"));
							String dest = notaString.substring(notaString.indexOf("<dest>")+6,notaString.indexOf("</dest>"));
							dest = dest.substring(dest.indexOf("<CNPJ>")+6, dest.indexOf("</CNPJ>"));
							
							NfeEntrada nf = new NfeEntrada();
							nf.setChaveAcesso(chaveAcesso);
							nf.setTpAmb(tpAmb.charAt(0));
							nf.setTpEmis(tpEmis.charAt(0));
							nf.setCnpj(cnpj);
							nf.setDest(dest);
							nf.setCuf(cUF);
							nf.setSerie(Integer.valueOf(serie));
							nf.setNnf(Integer.valueOf(nnf));
							nf.setXmotivo(schemaError);
							nf.setCstat("215");
						    nf.setDhRecbto(new Date());
						    nf.setNprot("");
						    nf.setDanfeGerado('0');
						    nf.setTxtGerado('0');
							try
							{
								NFeEntradaCreateCommand createCommand = new NFeEntradaCreateCommand(nf);
								createCommand.execute();
							}
							catch(ConstraintViolationException e1)
							{
								logger.error("! ConstraintViolationException !",e1);
								NFeEntradaUpdateCommand updateCommand = new NFeEntradaUpdateCommand(nf);
								updateCommand.execute();
							}
							FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getXmlEntradaInvalido()+File.separatorChar+File.createTempFile("EntradaInvalida", ".xml").getName(), notaString);
						    reciNFeXML.delete();
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
				Thread.sleep( Integer.parseInt(PropriedadesSistema.getInstance().getSistema().getEmailEntradaThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread ReciNFeValidadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
