package br.com.hs.nfe.ws.sp.homo.nferetrecepcao2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 10:23:59 BRST 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2", name = "NfeRetRecepcao2Soap12")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeRetRecepcao2Soap12 {

    @WebResult(name = "nfeRetRecepcao2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2", partName = "nfeRetRecepcao2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2/nfeRetRecepcao2")
    public br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument nfeRetRecepcao2(
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2")
        br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeDadosMsgDocument nfeDadosMsg,
        @WebParam(partName = "nfeCabecMsg", mode = WebParam.Mode.INOUT, name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2", header = true)
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeCabecMsg> nfeCabecMsg
    );
}
