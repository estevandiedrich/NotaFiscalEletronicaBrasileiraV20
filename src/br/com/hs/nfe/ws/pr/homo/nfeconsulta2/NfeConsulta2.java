
/*
 * 
 */

package br.com.hs.nfe.ws.pr.homo.nfeconsulta2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue Nov 01 10:17:04 BRST 2011
 * Generated source version: 2.2.1
 * 
 */


@WebServiceClient(name = "NfeConsulta2", 
                  wsdlLocation = "file:NFeConsulta2PRHomo.xml",
                  targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2") 
public class NfeConsulta2 extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", "NfeConsulta2");
    public final static QName NfeConsultaServicePort = new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2", "NfeConsultaServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:NFeConsulta2PRHomo.xml");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:NFeConsulta2PRHomo.xml");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public NfeConsulta2(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NfeConsulta2(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NfeConsulta2() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns NfeConsulta2Soap12
     */
    @WebEndpoint(name = "NfeConsultaServicePort")
    public NfeConsulta2Soap12 getNfeConsultaServicePort() {
        return super.getPort(NfeConsultaServicePort, NfeConsulta2Soap12.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NfeConsulta2Soap12
     */
    @WebEndpoint(name = "NfeConsultaServicePort")
    public NfeConsulta2Soap12 getNfeConsultaServicePort(WebServiceFeature... features) {
        return super.getPort(NfeConsultaServicePort, NfeConsulta2Soap12.class, features);
    }

}
