
/*
 * 
 */

package br.com.hs.nfe.ws.ce.homo.nferecepcao2;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.2.1
 * Mon Oct 31 17:33:06 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeRecepcao2", 
                  wsdlLocation = "file:NfeRecepcao2CEHomo.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2") 
public class NfeRecepcao2 extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", "NfeRecepcao2");
    public final static QName NfeRecepcao2Soap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2", "NfeRecepcao2Soap12");
    static {
        URL url = null;
        try {
            url = new URL("file:NfeRecepcao2CEHomo.xml");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:NfeRecepcao2CEHomo.xml");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public NfeRecepcao2(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NfeRecepcao2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NfeRecepcao2() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns NfeRecepcao2Soap
     */
    @WebEndpoint(name = "NfeRecepcao2Soap12")
    public NfeRecepcao2Soap getNfeRecepcao2Soap12() {
        return super.getPort(NfeRecepcao2Soap12, NfeRecepcao2Soap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeRecepcao2Soap
     */
    @WebEndpoint(name = "NfeRecepcao2Soap12")
    public NfeRecepcao2Soap getNfeRecepcao2Soap12(WebServiceFeature... features) {
        return super.getPort(NfeRecepcao2Soap12, NfeRecepcao2Soap.class, features);
    }

}
