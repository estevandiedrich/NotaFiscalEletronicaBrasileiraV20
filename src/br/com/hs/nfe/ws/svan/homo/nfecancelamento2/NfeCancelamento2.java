
/*
 * 
 */

package br.com.hs.nfe.ws.svan.homo.nfecancelamento2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 10:29:31 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeCancelamento2", 
                  wsdlLocation = "file:NfeCancelamento2SVANHomo.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2") 
public class NfeCancelamento2 extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", "NfeCancelamento2");
    public final static QName NfeCancelamento2Soap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", "NfeCancelamento2Soap12");
    public final static QName NfeCancelamento2Soap = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", "NfeCancelamento2Soap");
    static {
        URL url = null;
        try {
            url = new URL("file:NfeCancelamento2SVANHomo.xml");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:NfeCancelamento2SVANHomo.xml");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

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
