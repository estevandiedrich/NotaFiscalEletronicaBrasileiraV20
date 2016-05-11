
package br.com.hs.nfe.ws.pe.prod.recepcaoevento;

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
 * Tue Nov 01 11:42:04 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class RecepcaoEventoSoap12_RecepcaoEventoSoap12_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento", "RecepcaoEvento");

    private RecepcaoEventoSoap12_RecepcaoEventoSoap12_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = RecepcaoEvento.WSDL_LOCATION;
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
      
        RecepcaoEvento ss = new RecepcaoEvento(wsdlURL, SERVICE_NAME);
        RecepcaoEventoSoap12 port = ss.getRecepcaoEventoSoap12();  
        
        {
        System.out.println("Invoking nfeRecepcaoEvento...");
        br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeDadosMsgDocument _nfeRecepcaoEvento_nfeDadosMsg = null;
        br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeCabecMsg _nfeRecepcaoEvento_nfeCabecMsgVal = null;
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeCabecMsg> _nfeRecepcaoEvento_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeCabecMsg>(_nfeRecepcaoEvento_nfeCabecMsgVal);
        br.inf.portalfiscal.nfe.wsdl.recepcaoEvento.NfeRecepcaoEventoResultDocument _nfeRecepcaoEvento__return = port.nfeRecepcaoEvento(_nfeRecepcaoEvento_nfeDadosMsg, _nfeRecepcaoEvento_nfeCabecMsg);
        System.out.println("nfeRecepcaoEvento.result=" + _nfeRecepcaoEvento__return);

        System.out.println("nfeRecepcaoEvento._nfeRecepcaoEvento_nfeCabecMsg=" + _nfeRecepcaoEvento_nfeCabecMsg.value);

        }

        System.exit(0);
    }

}
