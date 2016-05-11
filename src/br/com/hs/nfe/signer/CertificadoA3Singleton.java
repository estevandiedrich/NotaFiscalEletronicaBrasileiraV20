package br.com.hs.nfe.signer;

import java.util.ArrayList;
import java.util.List;

import br.com.hs.nfe.vo.CertificadosVO;

public class CertificadoA3Singleton {
	private CertificadoA3Singleton()
	{
		
	}
	private static CertificadoA3Singleton me;
	private List<CertificadosVO> certificados = new ArrayList<CertificadosVO>();
	public static CertificadoA3Singleton getInstance()
	{
		if(me == null)
			me = new CertificadoA3Singleton();
		return me;
	}
	public List<CertificadosVO> getCertificados() {
		return certificados;
	}
	public void setCertificados(List<CertificadosVO> certificados) {
		this.certificados = certificados;
	}
	public CertificadosVO getPorCnpj(String cnpj)
	{
		for(CertificadosVO certificadosVO:certificados)
		{
			if(certificadosVO.getCnpj().equalsIgnoreCase(cnpj))
			{
				return certificadosVO;
			}
		}
		return null;
	}
}
