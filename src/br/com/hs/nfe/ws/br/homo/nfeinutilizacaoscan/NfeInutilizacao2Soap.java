package br.com.hs.nfe.ws.br.homo.nfeinutilizacaoscan;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.2.1
 * Mon Sep 05 15:04:02 BRT 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", name = "NfeInutilizacao2Soap")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeInutilizacao2Soap {

    @WebResult(name = "nfeInutilizacaoNF2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", partName = "nfeInutilizacaoNF2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2/nfeInutilizacaoNF2")
    public br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument nfeInutilizacaoNF2(
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2")
        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeDadosMsgDocument nfeDadosMsg,
        @WebParam(partName = "nfeCabecMsg", name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", header = true)
        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsg nfeCabecMsg
    );
}
