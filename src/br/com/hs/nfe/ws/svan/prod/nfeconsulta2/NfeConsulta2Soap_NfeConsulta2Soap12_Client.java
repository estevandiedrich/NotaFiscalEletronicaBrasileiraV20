
package br.com.hs.nfe.ws.svan.prod.nfeconsulta2;

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
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 11:51:43 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class NfeConsulta2Soap_NfeConsulta2Soap12_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", "NfeConsulta2");

    private NfeConsulta2Soap_NfeConsulta2Soap12_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = NfeConsulta2.WSDL_LOCATION;
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
      
        NfeConsulta2 ss = new NfeConsulta2(wsdlURL, SERVICE_NAME);
        NfeConsulta2Soap port = ss.getNfeConsulta2Soap12();  
        
        {
        System.out.println("Invoking nfeConsultaNF2...");
        br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeDadosMsgDocument _nfeConsultaNF2_nfeDadosMsg = null;
        br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeCabecMsg _nfeConsultaNF2_nfeCabecMsg = null;
        br.inf.portalfiscal.nfe.wsdl.nfeConsulta2.NfeConsultaNF2ResultDocument _nfeConsultaNF2__return = port.nfeConsultaNF2(_nfeConsultaNF2_nfeDadosMsg, _nfeConsultaNF2_nfeCabecMsg);
        System.out.println("nfeConsultaNF2.result=" + _nfeConsultaNF2__return);


        }

        System.exit(0);
    }

}
