package br.com.hs.nfe.ws.svan.prod.nfestatusservico2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 11:53:41 BRST 2011
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2", name = "NfeStatusServico2Soap")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NfeStatusServico2Soap {

    @WebResult(name = "nfeStatusServicoNF2Result", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2", partName = "nfeStatusServicoNF2Result")
    @WebMethod(action = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2/nfeStatusServicoNF2")
    public br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument nfeStatusServicoNF2(
        @WebParam(partName = "nfeDadosMsg", name = "nfeDadosMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2")
        br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeDadosMsgDocument nfeDadosMsg,
        @WebParam(partName = "nfeCabecMsg", name = "nfeCabecMsg", targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2", header = true)
        br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeCabecMsg nfeCabecMsg
    );
}
