
package br.com.hs.nfe.ws.pe.homo.nferecepcao2;

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
 * Tue Nov 01 10:11:50 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class NfeRecepcao2Soap_NfeRecepcao2_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", "NfeRecepcao2");

    private NfeRecepcao2Soap_NfeRecepcao2_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = NfeRecepcao2.WSDL_LOCATION;
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
      
        NfeRecepcao2 ss = new NfeRecepcao2(wsdlURL, SERVICE_NAME);
        NfeRecepcao2Soap port = ss.getNfeRecepcao2();  
        
        {
        System.out.println("Invoking nfeRecepcaoLote2...");
        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeDadosMsgDocument _nfeRecepcaoLote2_nfeDadosMsg = null;
        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsg _nfeRecepcaoLote2_nfeCabecMsgVal = null;
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsg> _nfeRecepcaoLote2_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeCabecMsg>(_nfeRecepcaoLote2_nfeCabecMsgVal);
        br.inf.portalfiscal.nfe.wsdl.nfeRecepcao2.NfeRecepcaoLote2ResultDocument _nfeRecepcaoLote2__return = port.nfeRecepcaoLote2(_nfeRecepcaoLote2_nfeDadosMsg, _nfeRecepcaoLote2_nfeCabecMsg);
        System.out.println("nfeRecepcaoLote2.result=" + _nfeRecepcaoLote2__return);

        System.out.println("nfeRecepcaoLote2._nfeRecepcaoLote2_nfeCabecMsg=" + _nfeRecepcaoLote2_nfeCabecMsg.value);

        }

        System.exit(0);
    }

}
