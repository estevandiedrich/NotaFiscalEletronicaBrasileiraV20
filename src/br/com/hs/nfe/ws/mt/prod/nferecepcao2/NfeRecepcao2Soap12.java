package br.com.hs.nfe.ws.mt.prod.nferecepcao2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 11:36:44 BRST 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", name = "NfeRecepcao2Soap12")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeRecepcao2Soap12 {

    @WebResult(name = "nfeRecepcaoLote2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", partName = "nfeRecepcaoLote2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2/nfeRecepcaoLote2")
    public br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeRecepcaoLote2ResultDocument nfeRecepcaoLote2(
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2")
        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeDadosMsgDocument nfeDadosMsg,
        @WebParam(partName = "nfeCabecMsg", mode = WebParam.Mode.INOUT, name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", header = true)
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsg> nfeCabecMsg
    );
}
