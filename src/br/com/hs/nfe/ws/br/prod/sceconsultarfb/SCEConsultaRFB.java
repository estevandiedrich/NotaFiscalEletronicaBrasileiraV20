
/*
 * 
 */

package br.com.hs.nfe.ws.br.prod.sceconsultarfb;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Oct 25 10:27:26 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "SCEConsultaRFB", 
                  wsdlLocation = "file:SCEConsultaRFB.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB") 
public class SCEConsultaRFB extends Service {

    public final static URL WSDL_LOCATION = Thread.currentThread().getContextClassLoader().getResource("wsdl/br/prod/SCEConsultaRFB.wsdl");
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB", "SCEConsultaRFB");
    public final static QName SCEConsultaRFBSoap = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB", "SCEConsultaRFBSoap");
    public final static QName SCEConsultaRFBSoap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/SCEConsultaRFB", "SCEConsultaRFBSoap12");

    public SCEConsultaRFB(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SCEConsultaRFB(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SCEConsultaRFB() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SCEConsultaRFBSoap
     */
    @WebEndpoint(name = "SCEConsultaRFBSoap")
    public SCEConsultaRFBSoap getSCEConsultaRFBSoap() {
        return super.getPort(SCEConsultaRFBSoap, SCEConsultaRFBSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SCEConsultaRFBSoap
     */
    @WebEndpoint(name = "SCEConsultaRFBSoap")
    public SCEConsultaRFBSoap getSCEConsultaRFBSoap(WebServiceFeature... features) {
        return super.getPort(SCEConsultaRFBSoap, SCEConsultaRFBSoap.class, features);
    }
    /**
     * 
     * @return
     *     returns SCEConsultaRFBSoap
     */
    @WebEndpoint(name = "SCEConsultaRFBSoap12")
    public SCEConsultaRFBSoap getSCEConsultaRFBSoap12() {
        return super.getPort(SCEConsultaRFBSoap12, SCEConsultaRFBSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SCEConsultaRFBSoap
     */
    @WebEndpoint(name = "SCEConsultaRFBSoap12")
    public SCEConsultaRFBSoap getSCEConsultaRFBSoap12(WebServiceFeature... features) {
        return super.getPort(SCEConsultaRFBSoap12, SCEConsultaRFBSoap.class, features);
    }

}
