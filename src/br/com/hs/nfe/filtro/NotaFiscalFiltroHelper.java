package br.com.hs.nfe.filtro;

import java.util.Calendar;

import br.com.hs.nfe.entity.Usuario;

public class NotaFiscalFiltroHelper {
	
	public static final int FILTRO_RECEBIDA_NAO_ASSINADA = 1;
	public static final int FILTRO_ASSINADA_NAO_ENVIADA = 2;
	public static final int FILTRO_ENVIADA_NAO_PROCESSADA = 3;
	public static final int FILTRO_PROCESSADA_NAO_DANFE = 4;
	public static final int FILTRO_PROCESSADA_NAO_EMAIL = 5;
	
	public static final int FILTRO_HOJE = 10;
	public static final int FILTRO_SETE_DIAS = 20;
	public static final int FILTRO_ESTE_MES = 30;
	public static final int FILTRO_MES_PASSADO = 40;
	public static final int FILTRO_TODAS = 50;
	
	private NotaFiscalFiltroHelper() {
	}
	
	/**
	 * @param filtro
	 *            Tipo de Filtro, conforme constante
	 * @param usuario
	 *            Usuario
	 * @param emissores
	 *            Se True ira utilizar o usuario para popular o array de Estabelecimentos emissores
	 * @return
	 */
	public static NotaFiscalFiltro getFiltroPadrao(int filtro, Usuario usuario, boolean emissoresPadrao) {
		NotaFiscalFiltro retorno = new NotaFiscalFiltro();
		
		if (filtro == FILTRO_RECEBIDA_NAO_ASSINADA || filtro == FILTRO_ENVIADA_NAO_PROCESSADA
				|| filtro == FILTRO_PROCESSADA_NAO_DANFE || filtro == FILTRO_PROCESSADA_NAO_EMAIL) {
			retorno.setCodProcesso(filtro);
		} else if (filtro == FILTRO_HOJE) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			retorno.setDhRecbtoInicial(c.getTime());
		} else if (filtro == FILTRO_SETE_DIAS) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			c.add(Calendar.DATE, -6);
			retorno.setDhRecbtoInicial(c.getTime());
		} else if (filtro == FILTRO_ESTE_MES) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
			retorno.setDhRecbtoInicial(c.getTime());
		} else if (filtro == FILTRO_MES_PASSADO) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.add(Calendar.MILLISECOND, -1);
			retorno.setDhRecbtoFinal(c.getTime());
			
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
			retorno.setDhRecbtoInicial(c.getTime());
		}
		
		return retorno;
	}
	
}
