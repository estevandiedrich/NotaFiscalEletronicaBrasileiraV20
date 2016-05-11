package br.com.hs.nfe.cert;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.email.EnviarEmail;
import br.com.hs.nfe.entity.Estabelecimento;

public class VerificaValidadeCertificadoDigitalA1Thread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("VerificaValidadeCertificadoDigitalA1Thread");
	private Estabelecimento config = null;
	private ValidadeCertificadoDigitalA1 validadeCertificadoDigitalA1 = null;
	private int tempoDia = 1000 * 60 * 60 * 24;
	public VerificaValidadeCertificadoDigitalA1Thread(Estabelecimento config)
	{
		this.config = config;
		this.validadeCertificadoDigitalA1 = new ValidadeCertificadoDigitalA1(this.config);
	}
	public void run()
	{
		Thread.currentThread().setName( "VerificaValidadeCertificadoDigitalA1Thread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread VerificaValidadeCertificadoDigitalA1Thread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				if("A1".equalsIgnoreCase(config.getTipoCertificado()))
				{
					Calendar hoje = Calendar.getInstance();
					Calendar validade = Calendar.getInstance();
					Date date = this.validadeCertificadoDigitalA1.verificaValidadeCertificadoDigitalA1();
					validade.setTime(date);
					long diasValidade = validade.getTimeInMillis() / tempoDia;
					diasValidade -= 30; 
					long diasHoje = hoje.getTimeInMillis() / tempoDia;
					
					long diferenca = diasValidade - diasHoje;
					if(diferenca == 0)
					{
						//venceu
						String[] cc = new String[1];
						cc[0] = PropriedadesSistema.getInstance().getSistema().getEmailAdministrador();
						String assuntoCertificadoVencido = PropriedadesSistema.getInstance().getSistema().getAssuntoCertificadoVencido();
						if(assuntoCertificadoVencido == null || assuntoCertificadoVencido.equalsIgnoreCase(""))
						{
							assuntoCertificadoVencido = "Certificado vencido";
						}
						String textoCertificadoVencido = PropriedadesSistema.getInstance().getSistema().getTextoCertificadoVencido();
						if(textoCertificadoVencido == null || textoCertificadoVencido.equalsIgnoreCase(""))
						{
							textoCertificadoVencido = "Atenção, o certificado do estabelecimento " + config.getRazaoSocial() + " / " + config.getPe() + " expirará em 30 dias.";
						}
						EnviarEmail.enviar(config.getAssuntoEmail(),config.getCorpoEmail(),config,cc);
					}
				}
			}
			catch(Throwable t)
			{
				logger.error("Erro não capturado", t);
			}
			try 
			{
				Thread.sleep( tempoDia );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread VerificaValidadeCertificadoDigitalA1Thread", e);
			}
			finally
			{
				
			}
		}
	}
}
