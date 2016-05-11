
/*
 * 
 */

package br.com.hs.nfe.ws.sc.homo.nfeinutilizacao2;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue May 31 22:19:19 BRT 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeInutilizacao2", 
                  wsdlLocation = "file:nfeinutilizacao2.asmx.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2") 
public class NfeInutilizacao2 extends Service {

    public final static URL WSDL_LOCATION = Thread.currentThread().getContextClassLoader().getResource("wsdl/sc/homo/nfeinutilizacao2HomoSC.wsdl");
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", "NfeInutilizacao2");
    public final static QName NfeInutilizacao2Soap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2", "NfeInutilizacao2Soap12");

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
    @WebEndpoint(name = "NfeInutilizacao2Soap12")
    public NfeInutilizacao2Soap12 getNfeInutilizacao2Soap12() {
        return super.getPort(NfeInutilizacao2Soap12, NfeInutilizacao2Soap12.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeInutilizacao2Soap12
     */
    @WebEndpoint(name = "NfeInutilizacao2Soap12")
    public NfeInutilizacao2Soap12 getNfeInutilizacao2Soap12(WebServiceFeature... features) {
        return super.getPort(NfeInutilizacao2Soap12, NfeInutilizacao2Soap12.class, features);
    }

}
