
package br.com.hs.nfe.ws.br.prod.sceconsultarfb;

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
 * Tue Oct 25 10:27:26 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class SCEConsultaRFBSoap_SCEConsultaRFBSoap12_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB", "SCEConsultaRFB");

    private SCEConsultaRFBSoap_SCEConsultaRFBSoap12_Client() {
    }

    public static void main(String args[]) throws Exception {
//        URL wsdlURL = SCEConsultaRFB.WSDL_LOCATION;
//        if (args.length > 0) { 
//            File wsdlFile = new File(args[0]);
//            try {
//                if (wsdlFile.exists()) {
//                    wsdlURL = wsdlFile.toURI().toURL();
//                } else {
//                    wsdlURL = new URL(args[0]);
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//        }
//      
//        SCEConsultaRFB ss = new SCEConsultaRFB(wsdlURL, SERVICE_NAME);
//        SCEConsultaRFBSoap port = ss.getSCEConsultaRFBSoap12();  
//        
//        {
//        System.out.println("Invoking sceConsultaDPEC...");
//        br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceDadosMsgDocument _sceConsultaDPEC_sceDadosMsg = null;
//        br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg _sceConsultaDPEC_sceCabecMsgVal = null;
//        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg> _sceConsultaDPEC_sceCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceCabecMsg>(_sceConsultaDPEC_sceCabecMsgVal);
//        br.inf.portalfiscal.nfe.wsdl.sceConsultaRFB.SceConsultaDPECResultDocument _sceConsultaDPEC__return = port.sceConsultaDPEC(_sceConsultaDPEC_sceDadosMsg, _sceConsultaDPEC_sceCabecMsg);
//        System.out.println("sceConsultaDPEC.result=" + _sceConsultaDPEC__return);
//
//        System.out.println("sceConsultaDPEC._sceConsultaDPEC_sceCabecMsg=" + _sceConsultaDPEC_sceCabecMsg.value);
//
//        }
//
//        System.exit(0);
    }

}
