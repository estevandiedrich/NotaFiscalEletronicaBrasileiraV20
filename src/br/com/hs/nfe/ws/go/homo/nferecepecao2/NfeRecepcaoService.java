package br.com.hs.nfe.ws.go.homo.nferecepecao2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.2.1
 * Mon Oct 31 17:43:06 BRST 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", name = "NfeRecepcaoService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeRecepcaoService {

    @WebResult(name = "nfeRecepcaoLote2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", partName = "nfeRecepcaoLote2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2/nfeRecepcaoLote2")
    public br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeRecepcaoLote2ResultDocument nfeRecepcaoLote2(
        @WebParam(partName = "nfeCabecMsg", name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", header = true)
        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsg nfeCabecMsg,
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2")
        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeDadosMsgDocument nfeDadosMsg
    );
}