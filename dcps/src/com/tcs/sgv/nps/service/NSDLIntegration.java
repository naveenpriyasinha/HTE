package com.tcs.sgv.nps.service;	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.nps.dao.FormS1UpdateDAO;
import com.tcs.sgv.nps.dao.FormS1UpdateDAOImpl;

import au.id.jericho.lib.html.Logger;

public class NSDLIntegration extends ServiceImpl {	
	
	//static String ddoCode2="4070894";	
	private List <String> fileList;	
	 	

	private static String OUTPUT_ZIP_Contri_FILE = "/disk1/NPS/Contribution/";   //TOMCAT9 43
	
	private static ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/nps/NPSConstants");
	
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */
	
	/*String 	driverClass="com.ibm.db2.jcc.DB2Driver";
    String	username="ifms";
    String	password="ifms@786";
    String	dialect="org.hibernate.dialect.DB2Dialect";
    String	DBTYPE="oracle";
    static String connectionUrl="jdbc:db2://10.16.10.10:60000/shalarth";
static Connection connectionDB = null;
*/
	/* Global Variable for PostId */
	Long gLngPostId = null;
	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;
	/* Global Variable for Location Code */
	String gStrLocationCode = null;
	
	public NSDLIntegration() {	
		fileList = new ArrayList<String>();	
	}
	
	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gDtCurrDt = SessionHelper.getCurDate();
		} catch (Exception e) {

		}
	}
	
    public  String sendFile(HttpServletResponse response, String ddoCode, String batchId, String dtoUserId,String dtoRegNo) throws IOException, NoSuchAlgorithmException, KeyManagementException, SQLException, ClassNotFoundException	
    {	
    	
        String str ="";	
    	try{	
			System.out.println("creating ws");	
				
			//final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");	
	        URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;	
	    //    System.setProperty("https.protocols", "TLSv1.2");
	        System.out.println("wsdlURL "+wsdlURL);
	        System.out.println(System.getProperty("https.protocols"));
	        STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);
	        System.out.println("creating ws1");	
	        System.out.println("Testing...");
	        System.out.println("ss "+ss.toString());
	        System.out.println( "Check Port : "+ss.getSTPWebServicePOJOPort());
	        STPWebServicePOJO port = ss.getSTPWebServicePOJOPort(); 
	    
	        
	        System.out.println("https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");
	        System.out.println("creating ws2");	
	        System.out.println("Before setting :"+System.getProperty("https.protocols"));
	        System.setProperty("https.protocols", "TLSv1.2");
	        System.out.println(System.getProperty("https.protocols"));
	        BindingProvider bindingProvider = (BindingProvider) port;	
	        String ENDPOINT_ADDRESS_URL_SERVER_URL=null;
	        if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
	        	ENDPOINT_ADDRESS_URL_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_REG") ;
					 
				}else 
				{
					ENDPOINT_ADDRESS_URL_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_REG_SERVER");
					 
				}
	        bindingProvider.getRequestContext().put(	
	        	      BindingProvider.ENDPOINT_ADDRESS_PROPERTY,ENDPOINT_ADDRESS_URL_SERVER_URL);	 
	      
	        System.out.println("creating ws3");	
	        // {	
	   	System.out.println("Invoking performFileUpload...");	
	    	java.util.List<byte[]> _performFileUpload_arg0 = new java.util.ArrayList<byte[]>();	
	     	String OUTPUT_ZIP_FILE=null;
	    	String DTO_SIGN_PATH=null;
	    	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
					OUTPUT_ZIP_FILE = gObjRsrcBndle.getString("NPS.OUTPUT_FILE") ;	
					DTO_SIGN_PATH = gObjRsrcBndle.getString("NPS.DTO_SIGN") ;	
				}else 
				{
					OUTPUT_ZIP_FILE = gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER") ;	
					DTO_SIGN_PATH = gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER") ;	
				}
	    	
	    	String directoryName =OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/Upload/subreg_after_fuv";	
	 	   File directory = new File(directoryName);	
	 		if (!directory.exists()) {	
	 			directory.mkdir();	
	 			 
	 		}	
	    	System.out.println("hello every one "+ddoCode );	
	    	String zipFolderName="subreg_after_fuv.zip";	
	    	String fileUpload=DTO_SIGN_PATH+dtoUserId+".sig"; //43

	    	File source = new File(fileUpload);	
	    	File dest = new File(OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/Upload/subreg_after_fuv/"+ddoCode+".sig");	
	    	FileUtils.copyFile(source, dest);	
	    		
	   	System.out.println( OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/Upload/subscriberRegNsdl_verified.txt");
	    	File sourceFile = new File(OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/Upload/subscriberRegNsdl_verified.txt");	
	    	
	    	File destFile = new File(OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/Upload/subreg_after_fuv/SubReg_Verified_After_FUV.sub");	
	    		
	    	if (sourceFile.renameTo(destFile)) {	
	    System.out.println("File moved successfully");	
	    	} else {	
	    System.out.println("Failed to move file");	
	    	}	
	    	System.out.println("--------"+ddoCode);
	     	//craFVUpaosubcontr.main(inputParamaeters);	
	    	 // OUTPUT_ZIP_FILE = "E:/Test/"+zipFolderName;	
	         //String SOURCE_FOLDER = "E:/Test/"+ddoCode; // SourceFolder path	
	 //  NSDLIntegration appZip = new NSDLIntegration();	
	   String outputzipFile = OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/"+zipFolderName;	
       	
       String[] srcFiles = { OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/Upload/subreg_after_fuv/SubReg_Verified_After_FUV.sub", OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/Upload/subreg_after_fuv/"+ddoCode+".sig"};	
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
	   	
	   	
	  	
	        String zipFileName = OUTPUT_ZIP_FILE+ddoCode+"/"+batchId+"/"+zipFolderName;	
	        PerformFileUpload fileUploadd = new PerformFileUpload();	
	        byte[] fileByte;	
	        File zipFile = new File(outputzipFile);	
	        FileInputStream fis = new FileInputStream(zipFileName);	
	      	
	        fileByte = new byte[(int) zipFile.length()];	
	        fis.read(fileByte);	
	        System.out.println("Hello every one");	
	        System.out.println("--"+fileByte.toString());	
	        byte[][] _performFileUpload_arg0Val1 = new byte[3][0];	
	        _performFileUpload_arg0Val1[0] = dtoUserId.getBytes();	
	        _performFileUpload_arg0Val1[1] = "Upload SubscriberRegistration-DSC".getBytes(); //ye wala	
	        _performFileUpload_arg0Val1[2] = fileByte; // zip file byte	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[0]);	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[1]);	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[2]);	
	       java.lang.String _performFileUpload__return = port.performFileUpload(_performFileUpload_arg0);	
	        System.out.println("performFileUpload.result=" + _performFileUpload__return);	
	         str = _performFileUpload__return.substring(_performFileUpload__return.indexOf("<file-reference-number>") + "<file-reference-number>".length(),	
	        		_performFileUpload__return.indexOf("</file-reference-number>"));	
	     	}catch(Exception e) {
	     		gLogger.info("Send file to NSDLIntegration Class exception occurr here: \n" +e.getMessage().toString());
	     		e.printStackTrace();
	     	} finally {	
	     		gLogger.info("Send file to NSDL Outer application");	
	     	}	
        	return str;	
    		
    }	
   
    public  String getStatus(HttpServletResponse response, String ddoCode, String refCode, String dtoUserId,Map inputMap, String batchId) throws IOException, NoSuchAlgorithmException, KeyManagementException, SQLException, ClassNotFoundException	
    {	
    		
    	final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");	
        URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;	
        STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);	
        System.out.println("creating ws1");	
        STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();  	
        System.out.println("https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");
        
        System.out.println("creating ws2sssss");	
        
        System.setProperty("https.protocols", "TLSv1.2");
        System.out.println(System.getProperty("https.protocols"));
        String ENDPOINT_ADDRESS_URL_SERVER_URL=null;
        if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
        	ENDPOINT_ADDRESS_URL_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_REG") ;
				 
			}else 
			{
				ENDPOINT_ADDRESS_URL_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_REG_SERVER");
				 
			}
        BindingProvider bindingProvider = (BindingProvider) port;	
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY ,ENDPOINT_ADDRESS_URL_SERVER_URL);	
        
        		
     	        System.out.println("creating ws3");	
 	
    	System.out.println("Invoking perform status enquiry...");	
        java.util.List<byte[]> _performStatusInquiry_arg0 = new java.util.ArrayList<byte[]>();	
        
        String DTO_SIGN_PATH=null;
    	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
				//OUTPUT_ZIP_FILE = gObjRsrcBndle.getString("NPS.OUTPUT_FILE") ;	
				DTO_SIGN_PATH = gObjRsrcBndle.getString("NPS.DTO_SIGN") ;	
			}else 
			{
				//OUTPUT_ZIP_FILE = gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER") ;	
				DTO_SIGN_PATH = gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER") ;	
			}
       
    	String zipFileName=DTO_SIGN_PATH+dtoUserId+".sig"; 
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
         _performStatusInquiry_arg0Val1[4] = "File Status-SubscriberRegistration".getBytes();; // zip file byte	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[0]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[1]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[2]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[3]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[4]);	
         java.lang.String _performStatusInquiry__return = port.performStatusInquiry(_performStatusInquiry_arg0);	
         System.out.println("performStatusInquiry.result=" + _performStatusInquiry__return);	
 	 	 String dataList=null;	

         	try {
         		String error=_performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<error-code>") + "<error-code>".length(),	
               		 _performStatusInquiry__return.indexOf("</error-code>"));	
   System.out.println("ERROR"+error);
         		if(error.length()==0) {
         			String file_status= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<file_status>") + "<file_status>".length(),	
         	        		 _performStatusInquiry__return.indexOf("</file_status>"));			
        String str= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<pran_list>") + "<pran_list>".length(),	
        		 _performStatusInquiry__return.indexOf("</pran_list>"));	
	 	String total= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<total_records>") + "<total_records>".length(),	
	 			_performStatusInquiry__return.indexOf("</total_records>"));	
	 	ArrayList data=new ArrayList<>();	
	    String splitChr = "\\^";	
	    String dcpsId = null;	
	    /***************/
	    setSessionInfo(inputMap);
	    DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
	    FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(MstEmp.class, serv.getSessionFactory());
	    /***************/
	    String[] fmgStrng = str.split(splitChr); 	
    	System.out.println("frmString=="+fmgStrng.length);	
    	int j=11;	
    	String listPranNo=null;
	 	for(int i=0;i<fmgStrng.length;i++){	
	 		if(j<i){	
		 		System.out.println("frmString=="+j+"frmvaluePRAN=="+ fmgStrng[j]+"pran= "+ fmgStrng[j+1]+"\r\n");	
		 		List EmpDcpsIdsList = lObjFormS1UpdateDAO.getEmpDcpsIdByAcknow(fmgStrng[j+1]);
		 		if(EmpDcpsIdsList.size()>=1) {
		 			//dcpsId=(String) EmpDcpsIdList.get(0);
		 			ListIterator dcpsIdsIterator = EmpDcpsIdsList.listIterator();  
		 			while(dcpsIdsIterator.hasNext()){
		 				 try {
		 			    	
		 			    	dcpsId= (String) dcpsIdsIterator.next();
		 			    	
		 			    	/*MstEmp MstEmpObj = null;
		 					MstEmpObj = (MstEmp) lObjFormS1UpdateDAO.read(dcpsId);
		 					MstEmpObj.setPANNo(fmgStrng[j]);
		 					MstEmpObj.setPranActive(1L);
		 					MstEmpObj.setPranRemark(fmgStrng[j+1]);*/
		 			    	
		 			    	gLogger.info("Pran NO:"+fmgStrng[j]+" and  dcpsId : "+dcpsId);
		 			    	if(listPranNo==null) {
		 			    		listPranNo=fmgStrng[j];
		 			    	}else {
		 			    		listPranNo="^"+fmgStrng[j];
		 			    	}
		 			    	
		 					lObjFormS1UpdateDAO.updateMstDcpsEmpPranNo(dcpsId,fmgStrng[j],1);
		 					gLogger.info("Pran NO:"+fmgStrng[j]+" and  Acknowledge no "+fmgStrng[j+1]);
		 					lObjFormS1UpdateDAO.updateFrmDtlsPranNo(dcpsId,fmgStrng[j],fmgStrng[j+1]);
		 			    }catch(Exception e) {
		 			    	gLogger.error("Pran no update exception in table :PRAN10000");
		 			    } finally{
		 			    	gLogger.info("Pran no update Final block");
		 			    }
		 				/*dcpsId= (String) dcpsIdsIterator.next();*/
		 				
		 			}
		 			
		 		}else {
		 			gLogger.info("logged in Acknowledge no "+fmgStrng[j+1] +"is not found");
		 			
		 		}
			j=j+10;	
			 		}	
			 	}		
	 
			dataList=null;
		    String str1= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<response_html>") + "<response_html>".length(),	
	        		 	_performStatusInquiry__return.indexOf("</response_html>"));
	    				System.out.println("length=="+str1.length());
				    	  if(!str1.isEmpty() && str1.length()>16){	
			            StringBuilder htmlStringBuilder=new StringBuilder();	
			            //append html header and title	
			            htmlStringBuilder.append(str1);	
			            String SOURCE_FOLDER;
			            if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
							//if(System.getProperty("os.name")=="win") {
								 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE") + ddoCode + "/" + batchId;
							}else 
							{
								 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER") + ddoCode + "/" + batchId;
							}
			            	System.out.println("Response file+++++"+SOURCE_FOLDER);
			            //	WriteToFile(htmlStringBuilder.toString(),SOURCE_FOLDER+"/Response_getpran.html",ddoCode);
			            	
			            	
			                String tempFile = SOURCE_FOLDER+"/Response_getpran.html";	
			                File file = new File(tempFile);	
			                //write to file with OutputStreamWriter	
			                OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());	
			                Writer writer=new OutputStreamWriter(outputStream);	
			                writer.write(htmlStringBuilder.toString());	
			                writer.close();	
			                dataList="error#"+error;
				    	  }else if (file_status.equals("C")){
				    		  dataList="Success#File is under process#"+listPranNo;	
				    	  }else{
				    		  dataList="Success#"+str1.length()+"#"+listPranNo;	
				    	  }
	  
         		}
         		else
         		{
         			dataList="error#"+error;
         		}
         	} catch(Exception e)
         	{
         		dataList=null;
         		return dataList;
         	}
    return dataList;	
    }	
    	
    public static void WriteToFile(String fileContent, String fileName, String ddoCode) throws IOException {	
    	
    	String OUTPUT_ZIP_FILE=null;
    	
    	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
    		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
		}else 
		{
			OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
		}
		
    	
    	String projectPath = OUTPUT_ZIP_FILE +ddoCode;	
        String tempFile = projectPath + File.separator+fileName;	
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
    public static void WriteToFileTrn(String fileContent, String fileName, String ddoCode, String str, String batchId) throws IOException {	
        String projectPath = OUTPUT_ZIP_Contri_FILE +ddoCode+"/"+batchId;	
        File directory = new File(projectPath);
		if (!directory.exists()) {
			directory.mkdir();
			// If you require it to make the entire directory path including
			// parents,
			// use directory.mkdirs(); here instead.
		}

        String tempFile = projectPath + File.separator+fileName;	
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
	
	
    public void generateFileList(File node, String SOURCE_FOLDER) {	
		// add file only	
		System.out.println("file source folder:- " + node.getAbsolutePath());	
		if (node.isFile()) {	
			fileList.add(generateZipEntry(node.toString(), SOURCE_FOLDER));	
		}	
		if (node.isDirectory()) {	
			String[] subNote = node.list();	
			for (String filename : subNote) {	
				generateFileList(new File(node, filename), SOURCE_FOLDER);	
			}	
		}	
	}	
	private String generateZipEntry(String file, String SOURCE_FOLDER) {	
		String filechange=file.replace("\\", "/");	
		return filechange.substring(SOURCE_FOLDER.length(), filechange.length());	
	}	
	public void zipIt(String zipFile, String SOURCE_FOLDER) {	
		byte[] buffer = new byte[1024];	
		String source = new File(SOURCE_FOLDER).getName();	
		FileOutputStream fos = null;	
		ZipOutputStream zos = null;	
		try {	
			fos = new FileOutputStream(zipFile);	
			zos = new ZipOutputStream(fos);	
			// String relativePath =	
			// httpServlet.getServletContext().getRealPath("");	
			// System.out.println("relativePath = " + relativePath);	
			System.out.println("Output to Zip : " + zipFile);	
			FileInputStream in = null;	
			for (String file : this.fileList) {	
				System.out.println("File Added : " + file);	
				ZipEntry ze = new ZipEntry(source + File.separator + file);	
				zos.putNextEntry(ze);	
				try {	
					in = new FileInputStream(SOURCE_FOLDER + File.separator	
							+ file);	
					int len;	
					while ((len = in.read(buffer)) > 0) {	
						zos.write(buffer, 0, len);	
					}	
				} finally {	
					in.close();	
				}	
			}	
			zos.closeEntry();	
			System.out.println("Folder successfully compressed");	
		} catch (IOException ex) {	
			ex.printStackTrace();	
		} finally {	
			try {	
				zos.close();	
			} catch (IOException e) {	
				e.printStackTrace();	
			}	
		}	
	}	
		
		
		
		
	public  String sendContriFile(HttpServletResponse response, String ddoCode, String batchId, String dtoUserId) throws IOException, NoSuchAlgorithmException, KeyManagementException, ClassNotFoundException, SQLException	
    {	
    	
        String str ="";	
 			
 			
    	try{	
			System.out.println("creating ws");	
				
			final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");	
	        URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;	
	        	
	      	
	        STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);	
	        System.out.println("creating ws1");	
	        STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();  	
	        System.out.println("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");	
	        System.out.println("creating ws2");	
	        
	        String ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL=null;
	        if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
	        	ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION") ;
					 
				}else 
				{
					ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER");
					 
				}
	        
	        BindingProvider bindingProvider = (BindingProvider) port;	
	        bindingProvider.getRequestContext().put(	
	        	      BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL);	
	         
	        System.out.println("creating ws3");	
	        
	        // {	
	   	System.out.println("Invoking performFileUpload...");	
	    	java.util.List<byte[]> _performFileUpload_arg0 = new java.util.ArrayList<byte[]>();	
	    	//String filePath = env.getProperty("jasper.pdffiles.path").concat(path);	
	    	//File downloadFile = new File("E:/Test/subscriberRegistration.txt");	
	    	//File downloadFile = new File("/disk1/nps/subscriberRegistration.txt");	
	    	String directoryName =OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/Output/contri_after_fuv";	
	 	   File directory = new File(directoryName);	
	 		if (!directory.exists()) {	
	 			directory.mkdir();	
	 			// If you require it to make the entire directory path including	
	 			// parents,	
	 			// use directory.mkdirs(); here instead.	
	 		}	
	    	System.out.println("hello every one "+ddoCode );	
	    	String zipFolderName="contri_after_fuv.zip";	
	    //	String fileUpload="F:/Test/sign/"+ddoCode+".sig";	
	    		
	    	//String fileUpload="/disk2/disk1/DTO_SIGN/"+ddoCode+".sig";   //tomcat9	
	  String fileUpload="/disk1/NPS/DTO_SIGN/"+ddoCode+".sig";  //43

	    	//OUTPUT_ZIP_FILE = "/disk1/nps/"+zipFolderName;	
	    	//String SOURCE_FOLDER = "/disk1/nps/"+ddoCode;	
	    	//String[] inputParamaeters = {"/disk1/nps/"+ddoCode};	
	    	File source = new File(fileUpload);	
	    	//File dest = new File("E:/Test/"+ddoCode+"/"+batchId+"/Upload/contri/"+ddoCode+".sig");	
	    	File dest = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/Output/contri_after_fuv/"+ddoCode+".sig");	
	    	FileUtils.copyFile(source, dest);	
	    		
	   	
	    	File sourceFile = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/Output/contributionFile.fvu");	
	    	File destFile = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/Output/contri_after_fuv/contributionFile.pao");	
	    		
	    	if (sourceFile.renameTo(destFile)) {	
	    	    System.out.println("File moved successfully");	
	    	    	} else {	
	    	    System.out.println("Failed to move file");	
	    	    	}	
	    	
	   	
	     	//craFVUpaosubcontr.main(inputParamaeters);	
	    	 // OUTPUT_ZIP_FILE = "E:/Test/"+zipFolderName;	
	         //String SOURCE_FOLDER = "E:/Test/"+ddoCode; // SourceFolder path	
	 //  NSDLIntegration appZip = new NSDLIntegration();	
	   String outputzipFile = OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/"+zipFolderName;	
       	
       String[] srcFiles = { OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/Output/contri_after_fuv/contributionFile.pao", OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/Output/contri_after_fuv/"+ddoCode+".sig"};	
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
	   	
	   	
	   	// OUTPUT_ZIP_FILE = "E:/Test/contri/"+zipFolderName;	
	   	//String SOURCE_FOLDER = "E:/Test/"+ddoCode; // SourceFolder path	
	   	    // appZip.generateFileList(new File(SOURCE_FOLDER),SOURCE_FOLDER);	
	   	    // appZip.zipIt(OUTPUT_ZIP_FILE,SOURCE_FOLDER);	
	         	
	      //  HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");	
	        //ZipUtil.pack(new File("D:\\reports\\january\\"), new File("D:\\reports\\january.zip"));	
	    		
	    	//response.setContentType("APPLICATION/OCTET-STREAM");	
	        	
	        //response.setHeader("Content-disposition","attachment; filename=\"" +zipFolderName+"\""); // Used to name the download file and its format	
	       // File my_file = new File("E://outputtext.txt"); // We are downloading .txt file, in the format of doc with name check - check.doc	
	        	
	      // OutputStream out = response.getOutputStream();	
	        //FileInputStream in = new FileInputStream(OUTPUT_ZIP_FILE);	
	       /* byte[] buffer = new byte[4096];40707061000000399	
	        int length;	
	        while ((length = in.read(buffer)) > 0){	
	           out.write(buffer, 0, length);	
	        }	
	        in.close();	
	        out.flush();	
	    	*///OUTPUT_ZIP_FILE = "E:/Test/"+zipFolderName;	
	    	//File sourceFile = new File("E:/Test/"+ddoCode+"/9910000236/Upload/subscriberRegistration_verified.txt");	
	    	//File destFile = new File("E:/Test/"+ddoCode+"/9910000236/Upload/contri/"+ddoCode+"/subscriberRegistration_verified.sub");	
	    		
	    		
	    	System.out.println("--------"+ddoCode);	
	        //String zipFileName = OUTPUT_ZIP_FILE;//"E:/nsdl/"+ddoCode+".zip";	
	        String zipFileName = OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/"+zipFolderName;	
	        PerformFileUpload fileUploadd = new PerformFileUpload();	
	        byte[] fileByte;	
	        File zipFile = new File(outputzipFile);	
	        FileInputStream fis = new FileInputStream(zipFileName);	
	      	
	        fileByte = new byte[(int) zipFile.length()];	
	        fis.read(fileByte);	
	        System.out.println("Hello every one 16-11-2021");	
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
	     	}finally {	
	     			
	     	}	
    	 /*Class.forName("com.ibm.db2.jcc.DB2Driver");	
  		connectionDB= DriverManager.getConnection(connectionUrl, username, password);	
  		connectionDB.setAutoCommit(false);	*/
  		Date uploDate=new Date();
		 SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	     String stringDate = DateFor.format(uploDate);      
	     System.out.println("-------"+stringDate);   
	
     	/*String sql="UPDATE ifms.TRN_NPS_CONTRI_FILE SET NSDL_STATUS_CODE="+str+",UPLOAD_DATE='"+stringDate+"' WHERE BATCH_FIX_ID='"+batchId+"'";	
     	PreparedStatement statement = connectionDB.prepareStatement(sql);			
 		statement.executeUpdate();	
 		statement.close();
 		connectionDB.commit();*/
     	
    	
    	return str;	
    		
    }	
    public  String getContriStatus(HttpServletResponse response, String ddoCode, String refCode, String dtoUserId, String batchId) throws IOException, NoSuchAlgorithmException, KeyManagementException, ClassNotFoundException, SQLException	
    {	
    		
    	final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");	
        URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;	
        	
      	
        STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);	
        System.out.println("creating ws1");	
        STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();  	
        System.out.println("creating ws2");	
        System.out.println("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
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
        	
        System.out.println("creating ws3");	
 	
    	System.out.println("Invoking perform status enquiry...16-11");	
        java.util.List<byte[]> _performStatusInquiry_arg0 = new java.util.ArrayList<byte[]>();	
        	
       //String zipFileName = "/disk2/disk1/dto_sign/"+ddoCode+".sig";	
    	//String zipFileName="E:/sign/"+ddoCode+".sig";	
        	
    //String zipFileName = "/disk2/disk1/DTO_SIGN/"+ddoCode+".sig";  //tomcat9	
String zipFileName="/disk1/NPS/DTO_SIGN/"+ddoCode+".sig"; //43

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

        
        /*	String total= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<total_records>") + "<total_records>".length(),	
	 			_performStatusInquiry__return.indexOf("</total_records>"));	
	 	ArrayList data=new ArrayList<>();	
	 	ArrayList dataList=new ArrayList<>();	
	    String splitChr = "\\^";	
	    if(!str.isEmpty()){	
	    String[] fmgStrng = str.split(splitChr); 	
System.out.println("frmString=="+fmgStrng.length);	
int j=11;	
	 	for(int i=0;i<fmgStrng.length;i++){	
	 		if(j<i){	
		 		System.out.println("frmString=="+j+"frmvalue=="+ fmgStrng[j]+"\r\n");	
	 		data=new ArrayList<>();	
		data.add(fmgStrng[j]);	
		data.add(fmgStrng[j+1]);	
		dataList.add(data);	
	j=j+10;	
	 		}	
	 	}	
	    }	
	    else	
	    {*/	
        if(!str.isEmpty() && !str.equals("0"))
        {
        	/*Class.forName("com.ibm.db2.jcc.DB2Driver");	
      		connectionDB= DriverManager.getConnection(connectionUrl, username, password);	
      		connectionDB.setAutoCommit(false);	*/
      		Date uploDate=new Date();
    		 SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	     String stringDate = DateFor.format(uploDate);      
    	     System.out.println("-------"+stringDate);   
    	
         	/*String sql="UPDATE ifms.TRN_NPS_CONTRI_FILE SET TRAN_ID= '"+str+"', UPLOAD_DATE='"+stringDate+"', UPDATED_DATE='"+stringDate+"'  WHERE NSDL_STATUS_CODE='"+refCode+"'";	
         	PreparedStatement statement = connectionDB.prepareStatement(sql);			
     		statement.executeUpdate();	
     		statement.close();
     		connectionDB.commit();*/
     		String str1= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<contr_submission_form_html>") + "<contr_submission_form_html>".length(),	
	        		 _performStatusInquiry__return.indexOf("</response_html>"));	
           StringBuilder htmlStringBuilder=new StringBuilder();	
           //append html header and title	
           htmlStringBuilder.append(str1);	
           WriteToFileTrn(htmlStringBuilder.toString(),str+"_Challan.html",ddoCode,str,batchId);	
       
        }
        if(str.isEmpty()){	
        	
        	
        	
        	
	    	String str1= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<response_html>") + "<response_html>".length(),	
	        		 _performStatusInquiry__return.indexOf("</response_html>"));	
            StringBuilder htmlStringBuilder=new StringBuilder();	
            //append html header and title	
            htmlStringBuilder.append(str1);	
            WriteToFile(htmlStringBuilder.toString(),gObjRsrcBndle.getString("")+"testfile.html",ddoCode);	
            
           /* Class.forName("com.ibm.db2.jcc.DB2Driver");	
      		connectionDB= DriverManager.getConnection(connectionUrl, username, password);	
      		connectionDB.setAutoCommit(false);	*/
      		Date updatedDate=new Date();
    		 SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	     String stringDate = DateFor.format(updatedDate);      
    	     System.out.println("-------"+stringDate);   
    	
         	/*String sql="UPDATE ifms.TRN_NPS_CONTRI_FILE SET IS_ERROR =1 ,UPDATED_DATE='"+stringDate+"' WHERE NSDL_STATUS_CODE='"+refCode+"'";	
         	PreparedStatement statement = connectionDB.prepareStatement(sql);			
     		statement.executeUpdate();	
     		statement.close();
     		connectionDB.commit();*/

	    }	
    return str;	
    }	
		
}
