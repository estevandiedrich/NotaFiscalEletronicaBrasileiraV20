package br.com.hs.nfe.xslt;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.EventoDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Evento;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.EventoUpdateCommand;
import br.com.hs.nfe.queue.XML2Txt;
import br.com.hs.nfe.vo.NFeVO;

public class XsltEventoToTxtThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("XsltEventoToTxtThread");
	private EventoDAO eventoDao = new EventoDAO();
	private Estabelecimento config = null;
	private XsltNFeToTxt xlstNFeToTxt = null;
	private SimpleDateFormat sdf = null;
	private SimpleDateFormat stf = null;
	public XsltEventoToTxtThread(Estabelecimento config)
	{
		this.xlstNFeToTxt = new XsltNFeToTxt("evento.to.txt.xsl");
		this.sdf = new SimpleDateFormat("yyyyMMdd");
		this.stf = new SimpleDateFormat("HHmmss");
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "XlstNFeToTxtThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread XlstNFeToTxtThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Evento> eventoList = eventoDao.procuraTxtRetornoPendente(config.getCnpj(),config.getPe());
				for(Evento evento:eventoList)
				{
					NFeVO envEventoXML = XML2Txt.getInstance().getNFeVOPorChaveAcesso(evento.getId());
					if(envEventoXML == null)
					{
						envEventoXML = new NFeVO();
						File retEnvEvento = new File(config.getEnvEventoXMLVinculado()+File.separatorChar+evento.getId()+".xml");
						FileInputStream fis = new FileInputStream(retEnvEvento);
						envEventoXML.setRetEnvEvento(IOUtils.toString(fis));
						envEventoXML.setChaveAcesso(evento.getId());
						IOUtils.closeQuietly(fis);
					}
					byte[] txt = xlstNFeToTxt.transformar(envEventoXML.getRetEnvEvento().getBytes());
					if(txt.length>0)
					{
						FileManager.getInstance().saveFile(config.getRetEnvEventoTXT()+File.separatorChar+envEventoXML.getChaveAcesso()+"_"+sdf.format(evento.getDhEvento())+"_"+stf.format(evento.getDhEvento())+"_envi.txt", new String(txt));
						
						evento.setTxtRetornoGerado('1');
					}
					else
					{
						evento.setTxtRetornoGerado('2');
					}
					EventoUpdateCommand updateCommand = new EventoUpdateCommand(evento);
					updateCommand.execute();
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
				logger.error("Problemas ao interromper a Thread XsltEventoToTxtThread", e);
			}
			finally
			{
				
			}
		}
	}
}