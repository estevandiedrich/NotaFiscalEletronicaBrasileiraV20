
/*
 * 
 */

package br.com.hs.nfe.ws.sp.homo.nferetrecepcao2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 10:23:59 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeRetRecepcao2", 
                  wsdlLocation = "file:NfeRetRecepcao2SPHomo.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2") 
public class NfeRetRecepcao2 extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2", "NfeRetRecepcao2");
    public final static QName NfeRetRecepcao2Soap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetRecepcao2", "NfeRetRecepcao2Soap12");
    static {
        URL url = null;
        try {
            url = new URL("file:NfeRetRecepcao2SPHomo.xml");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:NfeRetRecepcao2SPHomo.xml");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public NfeRetRecepcao2(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NfeRetRecepcao2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NfeRetRecepcao2() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns NfeRetRecepcao2Soap12
     */
    @WebEndpoint(name = "NfeRetRecepcao2Soap12")
    public NfeRetRecepcao2Soap12 getNfeRetRecepcao2Soap12() {
        return super.getPort(NfeRetRecepcao2Soap12, NfeRetRecepcao2Soap12.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeRetRecepcao2Soap12
     */
    @WebEndpoint(name = "NfeRetRecepcao2Soap12")
    public NfeRetRecepcao2Soap12 getNfeRetRecepcao2Soap12(WebServiceFeature... features) {
        return super.getPort(NfeRetRecepcao2Soap12, NfeRetRecepcao2Soap12.class, features);
    }

}
