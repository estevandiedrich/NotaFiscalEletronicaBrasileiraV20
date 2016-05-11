
package br.com.hs.nfe.ws.sc.prod.nfecancelamento2;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.xmlbeans.XmlBeansDataBinding;
import org.apache.log4j.Logger;

import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.util.ClientConfig;

/**
 * This class was generated by Apache CXF 2.2.1
 * Tue May 31 22:10:50 BRT 2011
 * Generated source version: 2.2.1
 * 
 */

public final class NfeCancelamento2Helper {

	private static final Logger logger = Logger.getLogger("NfeCancelamento2Helper");
    private ClientConfig clientConfig = null;

    public NfeCancelamento2Helper(Estabelecimento config) {
    	clientConfig = new ClientConfig(config);
    }

    public String nfeCancelamento2(String nome,String xml)
    {
    	TLSClientParameters tls = new TLSClientParameters();
        try
        {
        	this.clientConfig.configTLS(tls);
        }
        catch(Exception e)
        {
        	logger.error("error ",e);
        }
      
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(NfeCancelamento2Soap12.class);
        factory.setAddress("https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx");
        factory.setWsdlURL(NfeCancelamento2.WSDL_LOCATION.toString());
        factory.setServiceName(NfeCancelamento2.SERVICE);
        factory.setEndpointName(NfeCancelamento2.NfeCancelamento2Soap12);
        factory.getServiceFactory().setDataBinding(new XmlBeansDataBinding());
        NfeCancelamento2Soap12 port = (NfeCancelamento2Soap12)factory.create();
        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit)client.getConduit();
        http.setTlsClientParameters(tls);
        br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCancelamentoNF2ResultDocument _nfeCancelamento2__return = null;
        try{
	        logger.info("Invoking nfeCancelamento2...");
	        xml = xml.substring(xml.indexOf("?>")+2);
	        xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2\">"+xml+"</nfeDadosMsg>";
	        br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeDadosMsgDocument _nfeCancelamento2_nfeDadosMsg = br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeDadosMsgDocument.Factory.parse(xml);
	        br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCabecMsgDocument nfeCancelamento2_nfeCabecMsg = br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCabecMsgDocument.Factory.newInstance();
	        br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCabecMsg _nfeCancelamento2_nfeCabecMsgVal = br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCabecMsg.Factory.newInstance();
	        _nfeCancelamento2_nfeCabecMsgVal.setVersaoDados("2.00");
	        _nfeCancelamento2_nfeCabecMsgVal.setCUF("42");
	        nfeCancelamento2_nfeCabecMsg.setNfeCabecMsg(_nfeCancelamento2_nfeCabecMsgVal);
	        javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCabecMsgDocument> _nfeCancelamento2_nfeCabecMsg = new javax.xml.ws.Holder<br.inf.portalfiscal.nfe.wsdl.nfeCancelamento2.NfeCabecMsgDocument>(nfeCancelamento2_nfeCabecMsg);
	        _nfeCancelamento2__return = port.nfeCancelamentoNF2(_nfeCancelamento2_nfeDadosMsg, _nfeCancelamento2_nfeCabecMsg);
	        logger.info("nfeCancelamento2.result=" + _nfeCancelamento2__return);
	
	        logger.info("nfeCancelamento2._nfeCancelamento2_nfeCabecMsg=" + _nfeCancelamento2_nfeCabecMsg.value);
        
	    }
	    catch(Exception e)
	    {
	    	logger.error("error ",e);
	    }
	    return _nfeCancelamento2__return.toString();
    }

}
