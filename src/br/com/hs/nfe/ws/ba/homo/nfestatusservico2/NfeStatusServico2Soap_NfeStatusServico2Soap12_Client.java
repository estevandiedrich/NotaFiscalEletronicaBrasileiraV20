
package br.com.hs.nfe.ws.ba.homo.nfestatusservico2;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

/**
 * This class was generated by Apache CXF 2.2.1
 * Mon Oct 31 17:20:27 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class NfeStatusServico2Soap_NfeStatusServico2Soap12_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2", "NfeStatusServico2");

    private NfeStatusServico2Soap_NfeStatusServico2Soap12_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = NfeStatusServico2.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        NfeStatusServico2 ss = new NfeStatusServico2(wsdlURL, SERVICE_NAME);
        NfeStatusServico2Soap port = ss.getNfeStatusServico2Soap12();  
        
        {
        System.out.println("Invoking nfeStatusServicoNF2...");
        br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeDadosMsgDocument _nfeStatusServicoNF2_nfeDadosMsg = null;
        br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeCabecMsg _nfeStatusServicoNF2_nfeCabecMsgVal = null;
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeCabecMsg> _nfeStatusServicoNF2_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeCabecMsg>(_nfeStatusServicoNF2_nfeCabecMsgVal);
        br.inf.portalfiscal.nfe.wsdl.nfeStatusServico2.NfeStatusServicoNF2ResultDocument _nfeStatusServicoNF2__return = port.nfeStatusServicoNF2(_nfeStatusServicoNF2_nfeDadosMsg, _nfeStatusServicoNF2_nfeCabecMsg);
        System.out.println("nfeStatusServicoNF2.result=" + _nfeStatusServicoNF2__return);

        System.out.println("nfeStatusServicoNF2._nfeStatusServicoNF2_nfeCabecMsg=" + _nfeStatusServicoNF2_nfeCabecMsg.value);

        }

        System.exit(0);
    }

}
