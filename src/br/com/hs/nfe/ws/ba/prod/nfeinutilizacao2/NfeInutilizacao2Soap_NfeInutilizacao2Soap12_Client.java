
package br.com.hs.nfe.ws.ba.prod.nfeinutilizacao2;

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
 * Tue Nov 01 10:46:30 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class NfeInutilizacao2Soap_NfeInutilizacao2Soap12_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", "NfeInutilizacao2");

    private NfeInutilizacao2Soap_NfeInutilizacao2Soap12_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = NfeInutilizacao2.WSDL_LOCATION;
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
      
        NfeInutilizacao2 ss = new NfeInutilizacao2(wsdlURL, SERVICE_NAME);
        NfeInutilizacao2Soap port = ss.getNfeInutilizacao2Soap12();  
        
        {
        System.out.println("Invoking nfeInutilizacaoNF2...");
        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeDadosMsgDocument _nfeInutilizacaoNF2_nfeDadosMsg = null;
        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsg _nfeInutilizacaoNF2_nfeCabecMsgVal = null;
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsg> _nfeInutilizacaoNF2_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeCabecMsg>(_nfeInutilizacaoNF2_nfeCabecMsgVal);
        br.inf.portalfiscal.nfe.wsdl.nfeInutilizacao2.NfeInutilizacaoNF2ResultDocument _nfeInutilizacaoNF2__return = port.nfeInutilizacaoNF2(_nfeInutilizacaoNF2_nfeDadosMsg, _nfeInutilizacaoNF2_nfeCabecMsg);
        System.out.println("nfeInutilizacaoNF2.result=" + _nfeInutilizacaoNF2__return);

        System.out.println("nfeInutilizacaoNF2._nfeInutilizacaoNF2_nfeCabecMsg=" + _nfeInutilizacaoNF2_nfeCabecMsg.value);

        }

        System.exit(0);
    }

}
