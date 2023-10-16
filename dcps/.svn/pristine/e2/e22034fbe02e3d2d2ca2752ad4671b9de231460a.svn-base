package com.tcs.sgv.nps.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.nps.service.PerformFileUpload;
import com.tcs.sgv.nps.service.STPWebServicePOJO;

public class NsdlNpsFileRegularSendImpl extends GenericDaoHibernateImpl{

	private Session ghibSession = null;
	private final Logger gLogger = Logger.getLogger(getClass());
	private List <String> fileList;	
	private static ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/nps/NPSConstants");

	public NsdlNpsFileRegularSendImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	public String getNsdlStatusCode(String bhID) {
		// TODO Auto-generated method stub
		List empLst = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT OLD_TRANSACTION_ID FROM NSDL_BH_DTLS where BH_BATCH_FIX_ID='"+bhID+"'  and IS_LEGACY_DATA='N'");

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		empLst = lQuery.list();
		if ((empLst.size() > 0) || (empLst != null)) {
			amountTotal = empLst.get(0).toString();
		}

		return amountTotal;
		
	}


public String getDdoCode(String bhID, String fileno) {
	// TODO Auto-generated method stub
	List ddocode = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT DDO_CODE FROM NSDL_BH_DTLS where BH_BATCH_FIX_ID='"+bhID+"' and FILE_NAME = '"+fileno+"' and IS_LEGACY_DATA='N' ");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	ddocode = lQuery.list();
	if ((ddocode.size() > 0) || (ddocode != null)) {
		amountTotal = ddocode.get(0).toString();
	}

	return amountTotal;
}
	public String getDtouserId(String ddocode) {
		// TODO Auto-generated method stub
		List dtouserId = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();
	   
		Strbld.append(" SELECT distinct User_ID FROM MST_DtO_REG as dto inner join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=dto.DDO_CODE where rlt.REPT_DDO_CODE='" + ddocode + "' ");
	 
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		dtouserId = lQuery.list();
		if ((dtouserId.size() > 0) || (dtouserId != null)) {
			amountTotal = dtouserId.get(0).toString();
		}

		return amountTotal;
	}
	
	public String getDtoReg(String ddocode) {
		// TODO Auto-generated method stub
		List dtouserId = null;
		String dtouserIdvalue= null;
		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();
		Strbld.append(" SELECT distinct dto.DTO_REG_NO FROM ifms.MST_DTO_REG as dto inner join ifms.RLT_ZP_DDO_MAP as rlt on dto.DDO_CODE=rlt.ZP_DDO_CODE  where  rlt.REPT_DDO_CODE = '"+ddocode+"' ");

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for DTO_REG_NO Level2 ---------" + lQuery.toString());

		dtouserId = lQuery.list();
		logger.info("dtouserId  DTO_REG_NO Level2---------" + dtouserId);
		if ((dtouserId.size() > 0) || (dtouserId != null)) {
			dtouserIdvalue = dtouserId.get(0).toString();
		}

		return dtouserIdvalue;
	}
	public  String sendContriFile(HttpServletResponse response, String ddoCode, String batchId, String dtoUserId,String dtoRegNo) throws NoSuchAlgorithmException, IOException 
    {	
        String str ="";	
    	try{	
			System.out.println("creating ws");	
			final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");	
			
			 
			 //System.setProperty("https.protocols", "TLSv1.2");
			System.out.println(System.getProperty("https.protocols"));
		     
		     System.setProperty("https.protocols", "TLSv1.2");
		     System.out.println(System.getProperty("https.protocols"));
			System.out.println("current protocol>>>"+String.join(" ", SSLContext.getDefault().getSupportedSSLParameters().getProtocols()));
		
	        URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;	
	        System.out.println("wsdlURL"+wsdlURL);
	        
	        STPWebServicePOJOService ss1 = new STPWebServicePOJOService();	
	        System.out.println("current protocol>>>");
	        STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL,STPWebServicePOJOService.SERVICE);	
	       System.out.println("creating ws1"+ss);	
	       
	        STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();  	
	      //  System.out.println("http://121.240.64.237/STPWeb/STPWebServicePOJOPort?wsdl");	
	        System.out.println("creating ws2"+System.getProperty("os.name").toLowerCase().split(" ")[0]);	
	        
	        String ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL=null;
	        if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
	        	ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION") ;
					 
				}else 
				{
					ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER");
					 
				}
	        System.out.println("END point is -> "+ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL);
	        BindingProvider bindingProvider = (BindingProvider) port;	
	       System.out.println(port);
	        /*bindingProvider.getRequestContext().put(	
	        	      BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://121.240.64.237/STPWeb/STPWebServicePOJOPort");	*/
	        
	        bindingProvider.getRequestContext().put(	
	        	      BindingProvider.ENDPOINT_ADDRESS_PROPERTY	,ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL);
	        	// "https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");	
//	        "http://121.240.64.237/STPWeb/STPWebServicePOJOPort");
	        
	        	   //"https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");	
	         // "http://121.240.64.237/STPWeb/STPWebServicePOJOPort");	
	        
	      
	    
	        System.out.println("creating ws3");	
	        
	        // {	
	   	System.out.println("Invoking performFileUpload...");	
	    	java.util.List<byte[]> _performFileUpload_arg0 = new java.util.ArrayList<byte[]>();	
	    	
	    	String OUTPUT_ZIP_FILE=null;
	    	String	OUTPUT_ZIP_Contri_FILE=null;
	    	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
	    		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN");
	    		OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
			}else 
			{
				OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER");
				OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
			}
	    	
	    	String fileUpload=OUTPUT_ZIP_FILE+"/"+dtoUserId+".sig";
	    	String directoryName =OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv";	
	 	   File directory = new File(directoryName);	
	 		if (!directory.exists()) {	
	 			directory.mkdir();	
	 			// If you require it to make the entire directory path including	
	 			// parents,	
	 			// use directory.mkdirs(); here instead.	
	 		}	
	    	System.out.println("hello every one "+ddoCode );	
	    	String zipFolderName="contri_after_fuv.zip";	
	    	File source = new File(fileUpload);	
	    	File dest = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/"+dtoUserId+".sig");	
	    	FileUtils.copyFile(source, dest);	
	    		
	   	
	    	File sourceFile = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/"+batchId+".fvu");	
	    	File destFile = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/contributionFile.pao");	
	    	
	    	
	    	if (!destFile.exists()) {	
	    		destFile.createNewFile();		
	 		}
	    		
	    	FileUtils.copyFile(sourceFile,destFile); 
	    	
	    	
	    	/*if (sourceFile.renameTo(destFile)) {	
	    	    System.out.println("File moved successfully");	
	    	    	} else {	
	    	    System.out.println("Failed to move file");	
	    	    	}	*/
	    	
	   String outputzipFile = OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/"+zipFolderName;	
       	
       String[] srcFiles = { OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/contributionFile.pao", OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/"+dtoUserId+".sig"};	
       try {	
            	
           // create byte buffer	
           byte[] buffer = new byte[1024];	
           FileOutputStream fos = new FileOutputStream(outputzipFile);	
           ZipOutputStream zos = new ZipOutputStream(fos);	
            	
           for (int i=0; i < srcFiles.length; i++) {	
                	
               File srcFile = new File(srcFiles[i]);	
               FileInputStream fis = new FileInputStream(srcFile);	
               // begin writing a new ZIP entry, positions the stream to the start of the entry data	
               zos.putNextEntry(new ZipEntry(srcFile.getName()));	
                	
               int length;	
               while ((length = fis.read(buffer)) > 0) {	
                   zos.write(buffer, 0, length);	
               }	
               zos.closeEntry();	
               // close the InputStream	
               fis.close();	
                	
           }	
           // close the ZipOutputStream	
           zos.close();	
            	
       }	catch(Exception e){	
       		
       e.printStackTrace()	;	
       }	
	   	
	   	
	   
	    		
	    	System.out.println("--------"+ddoCode);	
	        //String zipFileName = OUTPUT_ZIP_FILE;//"E:/nsdl/"+ddoCode+".zip";	
	        String zipFileName = OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/"+zipFolderName;	
	        PerformFileUpload fileUploadd = new PerformFileUpload();	
	        byte[] fileByte;	
	        File zipFile = new File(outputzipFile);	
	        FileInputStream fis = new FileInputStream(zipFileName);	
	      	
	        fileByte = new byte[(int) zipFile.length()];	
	        fis.read(fileByte);	
	        System.out.println("Hello every one sysdate");	
	        System.out.println("--"+fileByte.toString());	
	        byte[][] _performFileUpload_arg0Val1 = new byte[3][0];	
	        _performFileUpload_arg0Val1[0] = dtoUserId.getBytes();	
	        _performFileUpload_arg0Val1[1] = "Upload SubscriberContribution-DSC".getBytes(); //ye wala	
	        _performFileUpload_arg0Val1[2] = fileByte; // zip file byte	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[0]);	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[1]);	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[2]);	
	       java.lang.String _performFileUpload__return = port.performFileUpload(_performFileUpload_arg0);	
	        System.out.println("performFileUpload.result=" + _performFileUpload__return);	
	         str = _performFileUpload__return.substring(_performFileUpload__return.indexOf("<file-reference-number>") + "<file-reference-number>".length(),	
	        		_performFileUpload__return.indexOf("</file-reference-number>"));
	         str = str+"~"+ _performFileUpload__return.substring(_performFileUpload__return.indexOf("<error-code>") + "<error-code>".length(),	
		        		_performFileUpload__return.indexOf("</error-code>"));	
	         str = str+"~"+ _performFileUpload__return.substring(_performFileUpload__return.indexOf("<error-description>") + "<error-description>".length(),	
		        		_performFileUpload__return.indexOf("</error-description>"));
	     	}finally {	
	     			
	     	}	
    	
    	
    	return str;	
    		
    }	
	public void updatebatchdetaisls(String refCode, String bhID) {
		// TODO Auto-generated method stub
		Session hibSession =getSession();
		StringBuffer str2 = new StringBuffer();
		str2.append("update NSDL_BH_DTLS set FILE_STATUS = 5, status = 5,OLD_TRANSACTION_ID= '"+refCode+"' ,file_upload_created_date=sysdate where BH_BATCH_FIX_ID = '"+bhID+"'  and IS_LEGACY_DATA='N'");
		logger.error("NSDL_BH_DTLS------"+str2.toString());
		Query query3 = hibSession.createSQLQuery(str2.toString());
		query3.executeUpdate();
	}
	public String getContriStatus(HttpServletResponse response, String ddoCode, String refCode, String dtoUserId,
			String batchId, String dtoRegNo) throws IOException, NoSuchAlgorithmException, KeyManagementException, ClassNotFoundException, SQLException
	{
		
	 	//final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");
		final QName SERVICE_NAME = new QName("{https://121.240.64.237/", "STPWebServicePOJOService");
        URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;	
    	System.setProperty("https.protocols", "TLSv1.2");
    	System.out.println("current protocol>>>"+SSLContext.getDefault().getSupportedSSLParameters().getProtocols());
      	STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);	
        System.out.println("creating ws1");	
        STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();  	
//        System.out.println("creating ws2");	
//        System.out.println("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
        
        String ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL=null;
        if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
        	ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION") ;
				 
			}else 
			{
				ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER");
				 
			}
        
        BindingProvider bindingProvider = (BindingProvider) port;	
        bindingProvider.getRequestContext().put(	
        	      BindingProvider.ENDPOINT_ADDRESS_PROPERTY,ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL);
        	     //  "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");	
        // "http://121.240.64.237/STPWeb/STPWebServicePOJOPort");	
       	
        System.out.println("creating ws3");	
 	
    	System.out.println("Invoking perform status enquiry...16-11");	
        java.util.List<byte[]> _performStatusInquiry_arg0 = new java.util.ArrayList<byte[]>();	
        	
       //String zipFileName = "/disk2/disk1/dto_sign/"+ddoCode+".sig";	
    	//String zipFileName="E:/sign/"+ddoCode+".sig";	
        	
    //String zipFileName = "/disk2/disk1/DTO_SIGN/"+ddoCode+".sig";  //tomcat9	
//String zipFileName="/disk1/NPS/DTO_SIGN/"+dtoRegNo+".sig"; //43


        //C:\output\Jalsevaarth\Contribution\7101003892\3333202201001\output\contri_after_fuv
        
        String OUTPUT_ZIP_FILE=null;
    	String	OUTPUT_ZIP_Contri_FILE=null;
    	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
    		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN");
    		OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
		}else 
		{
			OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER");
			OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
		}
    	System.out.println("ref code>>>"+refCode);
        

String zipFileName=OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/"+dtoUserId+".sig";
System.out.println("PATH:"+zipFileName); 
	

        PerformFileUpload fileUpload = new PerformFileUpload();	
        byte[] fileByte;	
        File zipFile = new File(zipFileName);	
        FileInputStream fis = new FileInputStream(zipFileName);	
        fileByte = new byte[(int) zipFile.length()];	
        fis.read(fileByte);	
        String statusRefCode=refCode;//347602	
        System.out.println(fileByte.toString());	
        String userId=dtoUserId.substring(0, dtoUserId.length() - 2);	
    	byte[][] _performStatusInquiry_arg0Val1 = new byte[5][0];	
    	 _performStatusInquiry_arg0Val1[0] = dtoUserId.getBytes();	
    	 _performStatusInquiry_arg0Val1[1] = statusRefCode.getBytes();	
    	 _performStatusInquiry_arg0Val1[2] = userId.getBytes(); // zip file byte	
    	 _performStatusInquiry_arg0Val1[3] = fileByte; // zip file byte	
         _performStatusInquiry_arg0Val1[4] = "File Status-SubscriberContribution".getBytes();; // zip file byte	
         	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[0]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[1]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[2]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[3]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[4]);	
         java.lang.String _performStatusInquiry__return = port.performStatusInquiry(_performStatusInquiry_arg0);	
         System.out.println("performStatusInquiry.result=" + _performStatusInquiry__return);	
         	
        String str= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<transaction_id>") + "<transaction_id>".length(),	
        		 _performStatusInquiry__return.indexOf("</transaction_id>"));
        String fileStatus= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<file_status>") + "<file_status>".length(),	
       		 _performStatusInquiry__return.indexOf("</file_status>"));
       if(!str.isEmpty() && !str.equals("0"))
        {
     		String str1= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<contr_submission_form_html>") + "<contr_submission_form_html>".length(),	
	        		 _performStatusInquiry__return.indexOf("</response_html>"));	
           StringBuilder htmlStringBuilder=new StringBuilder();	
           //append html header and title	
           htmlStringBuilder.append(str1);	
      WriteToFileTrn(response,htmlStringBuilder.toString(),str+"_Challan.html",ddoCode,str,batchId);	
           
           
       
        }System.out.println("Error existes==="+fileStatus);
        if(str.isEmpty()  ){	 System.out.println("Error existes==="+fileStatus);
        	if(fileStatus=="1") {
        		//For delete record issue resolve here.
        		
        		System.out.println("Error existes");
        		
        	}else { 
        		System.out.println("Error existes=saasasas=="+fileStatus);
        		
		    	String str1= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<response_html>") + "<response_html>".length(),	
		        		 _performStatusInquiry__return.indexOf("</response_html>"));	
	            StringBuilder htmlStringBuilder=new StringBuilder();	
	            //append html header and title	
	            htmlStringBuilder.append(str1);	
	            this.updateErrorChallandetails(str1, batchId);
	            str =  WriteToFile(response,htmlStringBuilder.toString(),"testfile.html",ddoCode,batchId);	
            
        	}
            
            
	    }
    return str;	
    }	


public void updateErrorChallandetails(String errordata, String bhID) {
	// TODO Auto-generated method stub
	Session hibSession =getSession();
	StringBuffer str2 = new StringBuffer();
	str2.append("update NSDL_BH_DTLS set ERROR_DATA="+errordata+",FILE_STATUS='3' where BH_BATCH_FIX_ID = '"+bhID+"'  and IS_LEGACY_DATA='N'");
	logger.error("NSDL_BH_DTLS------"+str2.toString());
	Query query3 = hibSession.createSQLQuery(str2.toString());
	query3.executeUpdate();
}

public static String WriteToFile(HttpServletResponse response,String fileContent, String fileName, String ddoCode,String BatchID) throws IOException {	
	
	   String OUTPUT_ZIP_FILE=null;
   	String	OUTPUT_ZIP_Contri_FILE=null;
   	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
   		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN");
   		OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
		}else 
		{
			OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER");
			OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
		}
       
    String projectPath = OUTPUT_ZIP_Contri_FILE +ddoCode+"/"+BatchID;	
    
    
    File file1 = new File(projectPath);	
    if (!file1.exists()) {	
    	file1.mkdir();	
    }
     
    
    String tempFile = projectPath + File.separator+"backup_"+fileName;	
  //  String temporaryFile = fileName;	
    File file = new File(tempFile);	
    // if file does exists, then delete and create a new file	
    if (file.exists()) {	
        try {	
            File newFileName = new File(projectPath + File.separator+ "backup_"+fileName);	
            file.renameTo(newFileName);	
            file.createNewFile();	
        } catch (IOException e) {	
            e.printStackTrace();	
        }	
    }	
    //write to file with OutputStreamWriter	
   OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());	
    Writer writer=new OutputStreamWriter(outputStream);	
    writer.write(fileContent);	
    writer.close();	
  /*   PrintWriter outputfile = response.getWriter();
    try {
    	String fileN = "ErrorFile" + ".html";
			response.setContentType("text/plain;charset=UTF-8");

			response.addHeader("Content-disposition",
					"attachment; filename=" + fileN);
			response.setCharacterEncoding("UTF-8");

			outputfile.write(fileContent);
			outputfile.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//logger.info("All fine 7 is *********");
			if (outputfile != null)
				outputfile.close();
		}*/
     
       
    
    return tempFile;
}	


public static void WriteToFileTrn(HttpServletResponse response,String fileContent, String fileName, String ddoCode, String str, String batchId) throws IOException {	
	
	   String OUTPUT_ZIP_FILE=null;
   	String	OUTPUT_ZIP_Contri_FILE=null;
   	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
   		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN");
   		OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
		}else 
		{
			OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER");
			OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
		}
       
	
    String projectPath = OUTPUT_ZIP_Contri_FILE +ddoCode+"/"+batchId;	
    File directory = new File(projectPath);
	if (!directory.exists()) {
		directory.mkdir();
		// If you require it to make the entire directory path including
		// parents,
		// use directory.mkdirs(); here instead.
	}

    String tempFile = projectPath + File.separator+"backup_"+fileName;	
    String temporaryFile = fileName;
    File file = new File(tempFile);	
    // if file does exists, then delete and create a new file	
    if (file.exists()) {	
        try {	
            File newFileName = new File(projectPath + File.separator+ "backup_"+fileName);	
            file.renameTo(newFileName);	
            file.createNewFile();	
        } catch (IOException e) {	
            e.printStackTrace();	
        }	
    }	
    //write to file with OutputStreamWriter	
    OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());	
    Writer writer=new OutputStreamWriter(outputStream);	
    writer.write(fileContent);	
    writer.close();	
    
  
}

}
