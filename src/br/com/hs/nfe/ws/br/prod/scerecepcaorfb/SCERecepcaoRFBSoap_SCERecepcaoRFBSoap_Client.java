
package br.com.hs.nfe.ws.br.prod.scerecepcaorfb;

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
 * Tue Oct 25 10:27:20 BRST 2011
 * Generated source version: 2.2.1
 * 
 */

public final class SCERecepcaoRFBSoap_SCERecepcaoRFBSoap_Client {

    private static final QName SERVICE_NAME = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/SCERecepcaoRFB", "SCERecepcaoRFB");

    private SCERecepcaoRFBSoap_SCERecepcaoRFBSoap_Client() {
    }

    public static void main(String args[]) throws Exception {
//        URL wsdlURL = SCERecepcaoRFB.WSDL_LOCATION;
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
//        SCERecepcaoRFB ss = new SCERecepcaoRFB(wsdlURL, SERVICE_NAME);
//        SCERecepcaoRFBSoap port = ss.getSCERecepcaoRFBSoap();  
//        
//        {
//        System.out.println("Invoking sceRecepcaoDPEC...");
//        br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceDadosMsgDocument _sceRecepcaoDPEC_sceDadosMsg = null;
//        br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceCabecMsg _sceRecepcaoDPEC_sceCabecMsgVal = null;
//        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceCabecMsg> _sceRecepcaoDPEC_sceCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceCabecMsg>(_sceRecepcaoDPEC_sceCabecMsgVal);
//        br.inf.portalfiscal.nfe.wsdl.sceRecepcaoRFB.SceRecepcaoDPECResultDocument _sceRecepcaoDPEC__return = port.sceRecepcaoDPEC(_sceRecepcaoDPEC_sceDadosMsg, _sceRecepcaoDPEC_sceCabecMsg);
//        System.out.println("sceRecepcaoDPEC.result=" + _sceRecepcaoDPEC__return);
//
//        System.out.println("sceRecepcaoDPEC._sceRecepcaoDPEC_sceCabecMsg=" + _sceRecepcaoDPEC_sceCabecMsg.value);
//
//        }
//
//        System.exit(0);
    }

}