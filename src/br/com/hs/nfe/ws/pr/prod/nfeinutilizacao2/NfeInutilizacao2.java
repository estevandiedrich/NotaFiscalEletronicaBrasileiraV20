
/*
 * 
 */

package br.com.hs.nfe.ws.pr.prod.nfeinutilizacao2;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 11:44:55 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeInutilizacao2", 
                  wsdlLocation = "file:NFeInutilizacao2PRProd.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2") 
public class NfeInutilizacao2 extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", "NfeInutilizacao2");
    public final static QName NfeInutilizacaoServicePort = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", "NfeInutilizacaoServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:NFeInutilizacao2PRProd.xml");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:NFeInutilizacao2PRProd.xml");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public NfeInutilizacao2(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NfeInutilizacao2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NfeInutilizacao2() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns NfeInutilizacao2Soap12
     */
    @WebEndpoint(name = "NfeInutilizacaoServicePort")
    public NfeInutilizacao2Soap12 getNfeInutilizacaoServicePort() {
        return super.getPort(NfeInutilizacaoServicePort, NfeInutilizacao2Soap12.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeInutilizacao2Soap12
     */
    @WebEndpoint(name = "NfeInutilizacaoServicePort")
    public NfeInutilizacao2Soap12 getNfeInutilizacaoServicePort(WebServiceFeature... features) {
        return super.getPort(NfeInutilizacaoServicePort, NfeInutilizacao2Soap12.class, features);
    }

}
