package br.com.hs.nfe.ws.pr.prod.nfeinutilizacao2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 11:44:55 BRST 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", name = "NfeInutilizacao2Soap12")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeInutilizacao2Soap12 {

    @WebResult(name = "nfeInutilizacaoNF2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", partName = "nfeInutilizacaoNF2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2/nfeInutilizacaoNF2")
    public br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument nfeInutilizacaoNF2(
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2")
        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeDadosMsgDocument nfeDadosMsg,
        @WebParam(partName = "nfeCabecMsg", mode = WebParam.Mode.INOUT, name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", header = true)
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsg> nfeCabecMsg
    );
}
