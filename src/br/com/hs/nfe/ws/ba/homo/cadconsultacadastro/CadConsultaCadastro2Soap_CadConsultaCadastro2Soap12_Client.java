
package br.com.hs.nfe.ws.ba.homo.cadconsultacadastro;

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
 * Mon Oct 31 17:15:23 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class CadConsultaCadastro2Soap_CadConsultaCadastro2Soap12_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2", "CadConsultaCadastro2");

    private CadConsultaCadastro2Soap_CadConsultaCadastro2Soap12_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = CadConsultaCadastro2.WSDL_LOCATION;
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
      
        CadConsultaCadastro2 ss = new CadConsultaCadastro2(wsdlURL, SERVICE_NAME);
        CadConsultaCadastro2Soap port = ss.getCadConsultaCadastro2Soap12();  
        
        {
        System.out.println("Invoking consultaCadastro2...");
        br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.NfeDadosMsgDocument _consultaCadastro2_nfeDadosMsg = null;
        br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.NfeCabecMsg _consultaCadastro2_nfeCabecMsgVal = null;
        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.NfeCabecMsg> _consultaCadastro2_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.NfeCabecMsg>(_consultaCadastro2_nfeCabecMsgVal);
        br.inf.portalfiscal.nfe.wsdl.cadConsultaCadastro2.ConsultaCadastro2ResultDocument _consultaCadastro2__return = port.consultaCadastro2(_consultaCadastro2_nfeDadosMsg, _consultaCadastro2_nfeCabecMsg);
        System.out.println("consultaCadastro2.result=" + _consultaCadastro2__return);

        System.out.println("consultaCadastro2._consultaCadastro2_nfeCabecMsg=" + _consultaCadastro2_nfeCabecMsg.value);

        }

        System.exit(0);
    }

}
