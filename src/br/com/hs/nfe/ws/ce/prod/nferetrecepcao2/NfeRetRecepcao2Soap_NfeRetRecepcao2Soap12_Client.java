
package br.com.hs.nfe.ws.ce.prod.nferetrecepcao2;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 11:06:52 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class NfeRetRecepcao2Soap_NfeRetRecepcao2Soap12_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2", "NfeRetRecepcao2");

    private NfeRetRecepcao2Soap_NfeRetRecepcao2Soap12_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = NfeRetRecepcao2.WSDL_LOCATION;
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
      
        NfeRetRecepcao2 ss = new NfeRetRecepcao2(wsdlURL, SERVICE_NAME);
        NfeRetRecepcao2Soap port = ss.getNfeRetRecepcao2Soap12();  
        
        {
        System.out.println("Invoking nfeRetRecepcao2...");
        br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeDadosMsgDocument _nfeRetRecepcao2_nfeDadosMsg = null;
        br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeCabecMsg _nfeRetRecepcao2_nfeCabecMsgVal = null;
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeCabecMsg> _nfeRetRecepcao2_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeCabecMsg>(_nfeRetRecepcao2_nfeCabecMsgVal);
        br.inf.portalfiscal.nfe.wsdl.nfeRetRecepcao2.NfeRetRecepcao2ResultDocument _nfeRetRecepcao2__return = port.nfeRetRecepcao2(_nfeRetRecepcao2_nfeDadosMsg, _nfeRetRecepcao2_nfeCabecMsg);
        System.out.println("nfeRetRecepcao2.result=" + _nfeRetRecepcao2__return);

        System.out.println("nfeRetRecepcao2._nfeRetRecepcao2_nfeCabecMsg=" + _nfeRetRecepcao2_nfeCabecMsg.value);

        }

        System.exit(0);
    }

}
