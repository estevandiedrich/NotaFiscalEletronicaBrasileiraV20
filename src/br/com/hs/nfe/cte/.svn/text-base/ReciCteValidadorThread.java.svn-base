package br.com.hs.nfe.cte;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.entity.Cte;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.CTeCreateCommand;
import br.com.hs.nfe.hb.CTeUpdateCommand;
import br.com.hs.nfe.queue.CTeValidos;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xml.ValidateXML;
import br.com.hs.nfe.xpath.XPathUtil;

public class ReciCteValidadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("ReciCteValidadorThread");
	private ValidateXML validateXML = null;
	private ValidateXML validateCancXML = null;
	public ReciCteValidadorThread()
	{
		this.validateXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getProcCteXSD());
		this.validateCancXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getProcCancCteXSD());
	}
	public void run()
	{
		Thread.currentThread().setName( "ReciCteValidadorThread" );
		logger.info("Iniciando thread ReciCteValidadorThread");
		while ( super.isRunning() ) 
		{
			try
			{
				File[] reciNFeXMLArray = FileManager.getInstance().getXMLFiles(PropriedadesSistema.getInstance().getSistema().getCteEntrada());
				for(File reciNFeXML:reciNFeXMLArray)
				{
					FileInputStream fis = new FileInputStream(reciNFeXML);
					byte[] notaBa = IOUtils.toByteArray(fis);
					IOUtils.closeQuietly(fis);
					String notaString = new String(notaBa);
					String schemaError = this.validateXML.validateXml(new String(notaBa)); 
					if(schemaError.equalsIgnoreCase(""))
					{
						String chaveAcesso = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/@Id");
						String cnpj = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/:emit/:CNPJ");
						String rem = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/:rem/:CNPJ");
						String dest = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/:dest/:CNPJ");
						String serie = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/:ide/:serie");
						String nct = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/:ide/:nCT");
						String cUF = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/:ide/:cUF");
						String tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/:ide/:tpAmb");
						String tpEmis = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:CTe/:infCte/:ide/:tpEmis");
						
//						String nprot = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:nProt");
						String digVal = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:cteProc/:protCTe/:infProt/:digVal");
//					    String cstat = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:cStat");
//					    String xmotivo = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:xMotivo");					    
//					    Date dhRecbto  = NFeSimpleDateFormat.getInstance().parse(XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:nfeProc/:protNFe/:infProt/:dhRecbto"));
					    
					    Cte cte = new Cte();
					    
					    cte.setChaveAcesso(chaveAcesso);
					    cte.setCnpj(cnpj);
					    cte.setCuf(cUF);
					    cte.setDest(dest);
					    cte.setRem(rem);
					    cte.setNct(Integer.parseInt(nct));
					    cte.setSerie(Integer.parseInt(serie));
					    cte.setTpAmb(tpAmb.charAt(0));
					    cte.setTpEmis(tpEmis.charAt(0));
					    cte.setCstat("103");
					    cte.setDhRecbto(new Date());
					    cte.setXmotivo("");
					    cte.setNprot("");
					    cte.setDigVal(digVal);
					    cte.setDacteGerado('0');
					    
					    NFeVO e = new NFeVO();
					    e.setChaveAcesso(chaveAcesso);
					    e.setTpAmb(tpAmb);
					    e.setNfeProc(notaString);
					    
					    CTeValidos.getInstance().add(e);
					    
					    FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getCteEntradaValido()+File.separatorChar+cte.getChaveAcesso()+".xml", notaString);
					    reciNFeXML.delete();
					    
					    try
						{
					    	CTeCreateCommand createCommand = new CTeCreateCommand(cte);
							createCommand.execute();
						}
						catch(ConstraintViolationException e1)
						{
							logger.error("! ConstraintViolationException !",e1);
							CTeUpdateCommand updateCommand = new CTeUpdateCommand(cte);
							updateCommand.execute();
						}
					}
					else
					{
						schemaError = this.validateCancXML.validateXml(new String(notaBa)); 
						if(schemaError.equalsIgnoreCase(""))
						{
							String chaveAcesso = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancCTe/:retCancCTe/:infCanc/:chCTe");
							String tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancCTe/:retCancCTe/:infCanc/:tpAmb");
							String cUF = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancCTe/:retCancCTe/:infCanc/:cUF");
							String cStat = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancCTe/:retCancCTe/:infCanc/:cStat");
							String xMotivo = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancCTe/:retCancCTe/:infCanc/:xMotivo");
							String dhRecbto = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancCTe/:retCancCTe/:infCanc/:dhRecbto");
							String nProt = XPathUtil.solveXPath(new ByteArrayInputStream(notaBa), "//:procCancCTe/:retCancCTe/:infCanc/:nProt");
							
							Cte cte = new Cte();
							cte.setChaveAcesso("CTe"+chaveAcesso);
							cte.setTpAmb(tpAmb.charAt(0));
							cte.setCuf(cUF);
							cte.setCstat(cStat);
							cte.setXmotivo(xMotivo);
							cte.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto));
							cte.setNprot(nProt);
						    
						    FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getCteEntradaValido()+File.separatorChar+cte.getChaveAcesso()+".xml", notaString);
						    reciNFeXML.delete();
						    
						    try
							{
								CTeCreateCommand createCommand = new CTeCreateCommand(cte);
								createCommand.execute();
							}
							catch(ConstraintViolationException e1)
							{
								logger.error("! ConstraintViolationException !",e1);
								CTeUpdateCommand updateCommand = new CTeUpdateCommand(cte);
								updateCommand.execute();
							}
						}
						else
						{
							//String nota = new String(notaBa);
							
							FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getCteEntradaInvalido()+File.separatorChar+File.createTempFile("CTeInvalido", ".xml").getName(), notaString);
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
				Thread.sleep( Integer.parseInt(PropriedadesSistema.getInstance().getSistema().getEmailCteThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread ReciCteValidadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
