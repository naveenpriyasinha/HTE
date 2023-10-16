package com.webservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestWebService {

	
	@SuppressWarnings("unused")
	public static void main (String args[]) {

		//String urlStr = "http://www.example.com:8080/helloService?wsdl";
		String urlStr = "https://panchayatrajsevarth.maharashtra.gov.in/services/CMPIntegrationService?wsdl";
		//String urlStr = "https://ayurvedarth.maharashtra.gov.in/services/CMPIntegrationService?wsdl";
		//String urlStr = "https://sevaarth.mahakosh.gov.in/services/CMPIntegrationService?wsdl";
		
		//String urlStr = "https://panchayatrajsevarth.maharashtra.gov.in:8443/services/BEAMSIntegrationService.BEAMSIntegrationServiceHttpsSoap11Endpoint"
		
		//String urlStr = "https://sevaarth.mahakosh.gov.in/services/CMPIntegrationService?wsdl";
		/*Beams Testing Url*/
		//String urlStr = "https://panchayatrajsevarth.maharashtra.gov.in/services/BEAMSIntegrationService?wsdl";
		//String urlStr="https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl";
		URL url = null;
		URLConnection urlConnection = null;
		try {
			url = new URL(urlStr);
			urlConnection = url.openConnection();
			
			if(urlConnection.getContent() != null) {
				
				
				System.out.println("GOOD URL");
				System.out.println(urlStr);
			} else {
				System.out.println("BAD URL");
			}
		} catch (MalformedURLException ex) {
			System.out.println("bad URL");
		} catch (IOException ex) {
			System.out.println("Failed opening connection. Perhaps WS is not up?");
		} 
	
		/*
		 String webUrl="https://panchayatrajsevarth.maharashtra.gov.in/services/CMPIntegrationService?wsdl";
		 URL url = null;
		 URLConnection urlConnection = null;
		   try {
			 
			 url = new URL("webUrl");
            urlConnection = url.openConnection();
             if(urlConnection.getContent() != null) {
            	 System.out.println("GOOD");	 
             }else{
            	 System.out.println("OHooooooo!!!");
             }
             
          

	        
		}catch(IOException e) {
			 e.printStackTrace();
			System.out.println("ERRR"+e.getMessage());
		}*/
		
	}
}
