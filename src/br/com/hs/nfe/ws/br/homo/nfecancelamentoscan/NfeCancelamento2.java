
/*
 * 
 */

package br.com.hs.nfe.ws.br.homo.nfecancelamentoscan;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.1
 * Mon Sep 05 15:03:47 BRT 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeCancelamento2", 
                  wsdlLocation = "file:NfeCancelamento2.wsdl",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2") 
public class NfeCancelamento2 extends Service {

    public final static URL WSDL_LOCATION  = Thread.currentThread().getContextClassLoader().getResource("wsdl/br/homo/NfeCancelamento2.wsdl");
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", "NfeCancelamento2");
    public final static QName NfeCancelamento2Soap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", "NfeCancelamento2Soap12");
    public final static QName NfeCancelamento2Soap = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", "NfeCancelamento2Soap");

    public NfeCancelamento2(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NfeCancelamento2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NfeCancelamento2() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns NfeCancelamento2Soap
     */
    @WebEndpoint(name = "NfeCancelamento2Soap12")
    public NfeCancelamento2Soap getNfeCancelamento2Soap12() {
        return super.getPort(NfeCancelamento2Soap12, NfeCancelamento2Soap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeCancelamento2Soap
     */
    @WebEndpoint(name = "NfeCancelamento2Soap12")
    public NfeCancelamento2Soap getNfeCancelamento2Soap12(WebServiceFeature... features) {
        return super.getPort(NfeCancelamento2Soap12, NfeCancelamento2Soap.class, features);
    }
    /**
     * 
     * @return
     *     returns NfeCancelamento2Soap
     */
    @WebEndpoint(name = "NfeCancelamento2Soap")
    public NfeCancelamento2Soap getNfeCancelamento2Soap() {
        return super.getPort(NfeCancelamento2Soap, NfeCancelamento2Soap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeCancelamento2Soap
     */
    @WebEndpoint(name = "NfeCancelamento2Soap")
    public NfeCancelamento2Soap getNfeCancelamento2Soap(WebServiceFeature... features) {
        return super.getPort(NfeCancelamento2Soap, NfeCancelamento2Soap.class, features);
    }

}