package br.com.hs.nfe.ws.sp.homo.nfecancelamento2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 10:22:18 BRST 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", name = "NfeCancelamento2Soap12")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeCancelamento2Soap12 {

    @WebResult(name = "nfeCancelamentoNF2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", partName = "nfeCancelamentoNF2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2/nfeCancelamentoNF2")
    public br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCancelamentoNF2ResultDocument nfeCancelamentoNF2(
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2")
        br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeDadosMsgDocument nfeDadosMsg,
        @WebParam(partName = "nfeCabecMsg", mode = WebParam.Mode.INOUT, name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", header = true)
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCabecMsg> nfeCabecMsg
    );
}
