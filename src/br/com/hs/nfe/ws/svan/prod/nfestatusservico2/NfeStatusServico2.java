
/*
 * 
 */

package br.com.hs.nfe.ws.svan.prod.nfestatusservico2;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 11:53:41 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeStatusServico2", 
                  wsdlLocation = "file:NfeStatusServico2SVANProd.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2") 
public class NfeStatusServico2 extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2", "NfeStatusServico2");
    public final static QName NfeStatusServico2Soap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2", "NfeStatusServico2Soap12");
    public final static QName NfeStatusServico2Soap = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2", "NfeStatusServico2Soap");
    static {
        URL url = null;
        try {
            url = new URL("file:NfeStatusServico2SVANProd.xml");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:NfeStatusServico2SVANProd.xml");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public NfeStatusServico2(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NfeStatusServico2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NfeStatusServico2() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns NfeStatusServico2Soap
     */
    @WebEndpoint(name = "NfeStatusServico2Soap12")
    public NfeStatusServico2Soap getNfeStatusServico2Soap12() {
        return super.getPort(NfeStatusServico2Soap12, NfeStatusServico2Soap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeStatusServico2Soap
     */
    @WebEndpoint(name = "NfeStatusServico2Soap12")
    public NfeStatusServico2Soap getNfeStatusServico2Soap12(WebServiceFeature... features) {
        return super.getPort(NfeStatusServico2Soap12, NfeStatusServico2Soap.class, features);
    }
    /**
     * 
     * @return
     *     returns NfeStatusServico2Soap
     */
    @WebEndpoint(name = "NfeStatusServico2Soap")
    public NfeStatusServico2Soap getNfeStatusServico2Soap() {
        return super.getPort(NfeStatusServico2Soap, NfeStatusServico2Soap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeStatusServico2Soap
     */
    @WebEndpoint(name = "NfeStatusServico2Soap")
    public NfeStatusServico2Soap getNfeStatusServico2Soap(WebServiceFeature... features) {
        return super.getPort(NfeStatusServico2Soap, NfeStatusServico2Soap.class, features);
    }

}
