
/*
 * 
 */

package br.com.hs.nfe.ws.pr.homo.nfecancelamento2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 10:16:36 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeCancelamento2", 
                  wsdlLocation = "file:NFeCancelamento2PRHomo.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2") 
public class NfeCancelamento2 extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", "NfeCancelamento2");
    public final static QName NfeCancelamentoServicePort = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2", "NfeCancelamentoServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:NFeCancelamento2PRHomo.xml");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:NFeCancelamento2PRHomo.xml");
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
     *     returns NfeCancelamento2Soap12
     */
    @WebEndpoint(name = "NfeCancelamentoServicePort")
    public NfeCancelamento2Soap12 getNfeCancelamentoServicePort() {
        return super.getPort(NfeCancelamentoServicePort, NfeCancelamento2Soap12.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeCancelamento2Soap12
     */
    @WebEndpoint(name = "NfeCancelamentoServicePort")
    public NfeCancelamento2Soap12 getNfeCancelamentoServicePort(WebServiceFeature... features) {
        return super.getPort(NfeCancelamentoServicePort, NfeCancelamento2Soap12.class, features);
    }

}
