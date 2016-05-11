
/*
 * 
 */

package br.com.hs.nfe.ws.sc.prod.recepcaoevento;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.1
 * Mon Oct 24 15:23:01 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "RecepcaoEvento", 
                  wsdlLocation = "file:recepcaoevento.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento") 
public class RecepcaoEvento extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento", "RecepcaoEvento");
    public final static QName RecepcaoEventoSoap12 = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento", "RecepcaoEventoSoap12");
    static {
        URL url = null;
        try {
            url = new URL("file:recepcaoevento.xml");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:recepcaoevento.xml");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public RecepcaoEvento(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RecepcaoEvento(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RecepcaoEvento() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns RecepcaoEventoSoap12
     */
    @WebEndpoint(name = "RecepcaoEventoSoap12")
    public RecepcaoEventoSoap12 getRecepcaoEventoSoap12() {
        return super.getPort(RecepcaoEventoSoap12, RecepcaoEventoSoap12.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RecepcaoEventoSoap12
     */
    @WebEndpoint(name = "RecepcaoEventoSoap12")
    public RecepcaoEventoSoap12 getRecepcaoEventoSoap12(WebServiceFeature... features) {
        return super.getPort(RecepcaoEventoSoap12, RecepcaoEventoSoap12.class, features);
    }

}
