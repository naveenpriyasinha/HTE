package com.tcs.sgv.nps.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import com.tcs.sgv.nps.service.STPWebServicePOJO;
/**
 * This class was generated by Apache CXF 3.0.1
 * 2016-03-15T19:54:18.065+05:30
 * Generated source version: 3.0.1
 * 
 */

@WebServiceClient(name = "STPWebServicePOJOService", 
	wsdlLocation = "/data/wildfly-10.0.0.Final/standalone/deployments/ROOT.war/WEB-INF/STPWebServicePOJOPort.wsdl",
	targetNamespace = "https://webservice.core.stp.cra.com/")
public class STPWebServicePOJOService extends Service {

    public final static URL WSDL_LOCATION;
	private static ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/nps/NPSConstants");
    public final static QName SERVICE = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");
    public final static QName STPWebServicePOJOPort = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOPort");
    static {
        URL url = null;
        
        String ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL=null;
        if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
        	ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION") ;
				 
			}else 
			{
				ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER");
				 
			}
        
        try {
    	url = new URL(ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL);
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(STPWebServicePOJOService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:STPWebServicePOJOPort.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public STPWebServicePOJOService(URL wsdlLocation) {
    	
        super(wsdlLocation, SERVICE);
    }

    public STPWebServicePOJOService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public STPWebServicePOJOService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public STPWebServicePOJOService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public STPWebServicePOJOService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public STPWebServicePOJOService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns STPWebServicePOJO
     */
    @WebEndpoint(name = "STPWebServicePOJOPort")
    public STPWebServicePOJO getSTPWebServicePOJOPort() {
    	//System.out.println("STPWebServicePOJOPort "+STPWebServicePOJOPort.toString());
    	return super.getPort(STPWebServicePOJOPort, STPWebServicePOJO.class);
        
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns STPWebServicePOJO
     */
    @WebEndpoint(name = "STPWebServicePOJOPort")
    public STPWebServicePOJO getSTPWebServicePOJOPort(WebServiceFeature... features) {
        return super.getPort(STPWebServicePOJOPort, STPWebServicePOJO.class, features);
    }

}
