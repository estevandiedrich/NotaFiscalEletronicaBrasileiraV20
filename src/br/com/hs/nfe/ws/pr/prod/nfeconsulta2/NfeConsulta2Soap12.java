package br.com.hs.nfe.ws.pr.prod.nfeconsulta2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 11:44:29 BRST 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", name = "NfeConsulta2Soap12")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeConsulta2Soap12 {

    @WebResult(name = "nfeConsultaNF2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", partName = "nfeConsultaNF2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2/nfeConsultaNF2")
    public br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument nfeConsultaNF2(
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2")
        br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeDadosMsgDocument nfeDadosMsg,
        @WebParam(partName = "nfeCabecMsg", mode = WebParam.Mode.INOUT, name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", header = true)
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeCabecMsg> nfeCabecMsg
    );
}