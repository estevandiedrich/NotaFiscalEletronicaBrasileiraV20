package br.com.hs.nfe.ws.ce.homo.cadconsultacadastro2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

/**
 * This class was generated by Apache CXF 2.2.1
 * Mon Oct 31 17:23:56 BRST 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2", name = "CadConsultaCadastro2Soap12")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CadConsultaCadastro2Soap12 {

    @WebResult(name = "consultaCadastro2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2", partName = "consultaCadastro2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2/consultaCadastro2")
    public br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.ConsultaCadastro2ResultDocument consultaCadastro2(
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2")
        br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.NfeDadosMsgDocument nfeDadosMsg,
        @WebParam(partName = "nfeCabecMsg", mode = WebParam.Mode.INOUT, name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2", header = true)
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.NfeCabecMsg> nfeCabecMsg
    );
}
