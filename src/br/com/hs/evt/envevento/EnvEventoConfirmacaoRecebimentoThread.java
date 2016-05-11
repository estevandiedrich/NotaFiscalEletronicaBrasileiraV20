package br.com.hs.evt.envevento;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dao.NfeEntradaDAO;
import br.com.hs.nfe.dom.builder.EnvEventoDocumentBuilder;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Evento;
import br.com.hs.nfe.entity.NfeEntrada;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.EventoCreateCommand;
import br.com.hs.nfe.hb.EventoUpdateCommand;
import br.com.hs.nfe.hb.NFeEntradaUpdateCommand;
import br.com.hs.nfe.queue.TxtRetornoNFeEntradaGerado;
import br.com.hs.nfe.queue.XMLGerados;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.util.TrataCaracter;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xpath.XPathUtil;

public class EnvEventoConfirmacaoRecebimentoThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnvEventoConfirmacaoRecebimentoThread");
	private Estabelecimento config = null;
	private NfeEntradaDAO nfeEntradaDao = new NfeEntradaDAO();
	public EnvEventoConfirmacaoRecebimentoThread(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvEventoConfirmacaoRecebimentoThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvEventoConfirmacaoRecebimentoThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{

				List<NfeEntrada> nfeEntradaList = nfeEntradaDao.procuraEventosConfirmacaoPendentes(config.getCnpj());
				for(NfeEntrada nfeEntrada:nfeEntradaList)
				{
					NFeVO nfeEntradaXML = TxtRetornoNFeEntradaGerado.getInstance().getNFeVOPorChaveAcesso(nfeEntrada.getChaveAcesso());
					if(nfeEntradaXML == null)
					{
						nfeEntradaXML = new NFeVO();
						File envEvento = new File(PropriedadesSistema.getInstance().getSistema().getXmlEntradaValido()+File.separatorChar+nfeEntrada.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(envEvento);
						nfeEntradaXML.setChaveAcesso(nfeEntrada.getChaveAcesso());
						nfeEntradaXML.setNfeProc(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					
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
					
					String evento = EnvEventoDocumentBuilder.envEventoDocumentBuilder(nfeEntradaXML.getNfeProc());
					
					id = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/@Id");
					cOrgao = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:cOrgao");
					tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:tpAmb").charAt(0);
					cnpj = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:CNPJ");
					chNFe = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:chNFe");
					dhEvento = NFeSimpleDateFormat.getInstance().parse(XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:dhEvento"));
					tpEvento = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:tpEvento");
					nSeqEvento = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:nSeqEvento");
					verEvento = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:verEvento");
					descEvento = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:detEvento/:descEvento");
					xCorrecao = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:detEvento/:xCorrecao");
					xCondUso = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:detEvento/:xCondUso");
					cStat = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:cStat");
					xMotivo = XPathUtil.solveXPath(new ByteArrayInputStream(evento.getBytes()), "//:envEvento/:evento/:infEvento/:xMotivo");
					
					FileManager.getInstance().saveFile(config.getEnvEventoXML()+File.separatorChar+id+".xml", evento);
					
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
					nomeConteudoVO.setEventoXMLString(TrataCaracter.trata(evento)); 
					nomeConteudoVO.setChaveAcesso(id);
					XMLGerados.getInstance().add(nomeConteudoVO);
					
					nfeEntrada.setEventoConfirmacao('1');
					NFeEntradaUpdateCommand entradaUpdateCommand = new NFeEntradaUpdateCommand(nfeEntrada);
					entradaUpdateCommand.execute();
					
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
				logger.error("Problemas ao interromper a Thread EnvEventoConfirmacaoRecebimentoThread", e);
			}
			finally
			{
				
			}
		}
	}
}
