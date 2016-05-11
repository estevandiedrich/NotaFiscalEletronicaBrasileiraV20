package br.com.hs.evt.envevento;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.w3c.dom.Document;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Evento;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.EventoCreateCommand;
import br.com.hs.nfe.hb.EventoUpdateCommand;
import br.com.hs.nfe.parser.EnvEventoParser;
import br.com.hs.nfe.queue.XMLGerados;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.util.TrataCaracter;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xpath.XPathUtil;

public class EnvEventoThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnvEventoThread");
	private Estabelecimento config = null;
	public EnvEventoThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvEventoThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvEventoThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{

				File[] envEventoTXTArray = FileManager.getInstance().getTXTFiles(config.getEnvEventoTXT());
				for(File envEventoTXT:envEventoTXTArray)
				{
					String id = "";
					String cOrgao = "";
					Character tpAmb;
					String cnpj = "";
					String chNFe = "";
					Date dhEvento = new Date();
					String tpEvento = "";
					String nSeqEvento = "";
					String verEvento = "";
					String descEvento = "";
					String xCorrecao = "";
					String xCondUso = "";
					String cStat = "";
					String xMotivo = "";
					
					FileReader txt = new FileReader(envEventoTXT);
					Document xml = DomHelper.createEmptyDocument();
					HashMap<String, String> params = new HashMap<String, String>();
					EnvEventoParser.txt2XmlParser(txt,xml,params);
					ByteArrayOutputStream baos = DomHelper.docToXML(xml);
					
					id = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/@Id");
					cOrgao = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:cOrgao");
					tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:tpAmb").charAt(0);
					cnpj = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:CNPJ");
					chNFe = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:chNFe");
					dhEvento = NFeSimpleDateFormat.getInstance().parse(XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:dhEvento"));
					tpEvento = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:tpEvento");
					nSeqEvento = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:nSeqEvento");
					verEvento = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:verEvento");
					descEvento = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:detEvento/:descEvento");
					xCorrecao = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:detEvento/:xCorrecao");
					xCondUso = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:detEvento/:xCondUso");
					cStat = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:cStat");
					xMotivo = XPathUtil.solveXPath(new ByteArrayInputStream(baos.toByteArray()), "//:envEvento/:evento/:infEvento/:xMotivo");
					
					File sefazErpEnviNFe = new File(config.getEnvEventoXML()+File.separatorChar+id+".xml");
					IOUtils.closeQuietly(txt);
					IOUtils.closeQuietly(baos);
					envEventoTXT.delete();
					DomHelper.docToXML(xml, sefazErpEnviNFe);
					
					Evento eventoEntity = new Evento();
					eventoEntity.setId(id);
					eventoEntity.setCorgao(cOrgao);
					eventoEntity.setChNFe(chNFe);
					eventoEntity.setCnpj(cnpj);
					eventoEntity.setCstat(cStat);
					eventoEntity.setDescEvento(descEvento);
					eventoEntity.setDhEvento(dhEvento);
					eventoEntity.setNseqEvento(nSeqEvento);
					eventoEntity.setTpAmb(tpAmb);
					eventoEntity.setTpEvento(tpEvento);
					eventoEntity.setVerEvento(verEvento);
					eventoEntity.setXcondUso(xCondUso);
					eventoEntity.setXcorrecao(xCorrecao);
					eventoEntity.setXmotivo(xMotivo);
					eventoEntity.setTxtValido('1');
					eventoEntity.setXmlAssinado('0');
					eventoEntity.setXmlValido('0');
					eventoEntity.setXmlEnviado('0');
					eventoEntity.setTxtRetornoGerado('0');
					eventoEntity.setPe(config.getPe());
					eventoEntity.setCodEstabelecimento(config.getCodEstabelecimento());
					
					NFeVO nomeConteudoVO = new NFeVO();
					nomeConteudoVO.setEventoXMLString(TrataCaracter.trata(DomHelper.docToXML(xml).toString())); 
					nomeConteudoVO.setChaveAcesso(id);
					XMLGerados.getInstance().add(nomeConteudoVO);
					
					try
					{
						EventoCreateCommand createCommand = new EventoCreateCommand(eventoEntity);
						createCommand.execute();
					}
					catch(ConstraintViolationException e)
					{
						logger.error("! ConstraintViolationException !",e);
						EventoUpdateCommand updateCommand = new EventoUpdateCommand(eventoEntity);
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
				logger.error("Problemas ao interromper a Thread EnvEventoThread", e);
			}
			finally
			{
				
			}
		}
	}
}
