package com.tcs.sgv.pensionproc.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

public class ApprovedFilesToAGServiceImpl extends ServiceImpl{


	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for LangId */
	Long gLngLangId = null;

	/* Global Variable for EmpId */
	Long gLngEmpId = null;

	/* Global Variable for Location Id */
	String gStrLocId = null;

	/* Global Variable for DB Id */
	Long gLngDBId = null;

	/* Global Variable for Current Date */
	Date gCurDate = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	Long gLngLocId = null;
	private Date gDate = null;

	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */
	private static final Log logger = LogFactory
			.getLog(ApprovedFilesToAGServiceImpl.class); /* LOGGER */

	private void setSessionInfo(Map inputMap) throws Exception {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			new Locale(SessionHelper.getLocale(request));
			SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			SessionHelper.getCurDate();
			gDate = DBUtility.getCurrentDateFromDB();
			gStrLocId = SessionHelper.getLocationId(inputMap).toString();
			gLngLocId = SessionHelper.getLocationId(inputMap);
		} catch (Exception e) {
			logger.error("Error in setSessionInfo of ApprovedFilesToAGServiceImpl excel for AG ",
					e);
			throw e;
		}
	
	}

	private String adjustSpace(int space){
		String AdjustSpace="";
		//String AdjustSpace=new String(new char[space]).replace('\0', ' ');
		return AdjustSpace;
	}	


public ResultObject downloadZipDtls(Map inputMap){
	HttpServletResponse response = (HttpServletResponse) inputMap
	.get("responseObj");
	OutputStream lOutStream = null;

	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	System.out.println("make dir in temp");
	String tempFolderpath = System.getenv("TEMP");
	boolean newFile=new File(tempFolderpath+"\\Files").mkdir();
	System.out.println("writing of files to Temp/files folder---------------------------------- ");
	excelForMainDtls(inputMap);
	excelForFlyDtls(inputMap);
	excelForNomiDtls(inputMap);
	excelForpnsnBrkDtls(inputMap);
	System.out.println("writing complete------------------------------------------------------ ");
	try {
		setSessionInfo(inputMap);
		lOutStream = response.getOutputStream();
		String agCircle = StringUtility.getParameter("agCircle", request);
		String sevaarthId = StringUtility.getParameter("sevaarthId", request);
		
		String output_file=tempFolderpath+"\\"+sevaarthId+".zip";
		//response.setContentType("text/plain;charset=UTF-8");

	//	response.setCharacterEncoding("UTF-8");
		logger.error("logger 7)after write");
		/*byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
				.getBytes();
		lOutStream.write(allBytesInBlob);
		lOutStream.flush();
		
		*/
		
		//write files to zip folder
		//zipFile(output_file,sevaarthId,response);
	
  
        try{
  		response.setContentType("application/zip");
		response.addHeader("Content-disposition","attachment; filename=" + sevaarthId+".zip");
		
		
         FileOutputStream fileOutputStream = new FileOutputStream(output_file);  
         ZipOutputStream zipOutputStream = new ZipOutputStream((response.getOutputStream()) );  
        //String tempFolderpath = System.getenv("TEMP");
      	String input_file=tempFolderpath+"\\Files";
      	
      	File Temp1=new File(input_file+"\\MainDetails_"+sevaarthId+".txt");
      	File Temp2=new File(input_file+"\\FamilyDetails_"+sevaarthId+".txt");
      	File Temp3=new File(input_file+"\\NomineeDetails_"+sevaarthId+".txt");
    	File Temp4=new File(input_file+"\\PnsnBrkDetails_"+sevaarthId+".txt");
        
          File[] f1={Temp1,Temp2,Temp3,Temp4} ;
    
      for(int i=0;i<4;i++){
           	 
   	 	ZipEntry zipEntry = new ZipEntry(f1[i].getName());
            ( zipOutputStream).putNextEntry(zipEntry);
            
            
          FileInputStream fileInputStream = new FileInputStream(f1[i]);  
           byte[] buf = new byte[1024];  
            int bytesRead;  
            // Read the input file by chucks of 1024 bytes  
            // and write the read bytes to the zip stream  
           while ((bytesRead = fileInputStream.read(buf)) > 0) {  
               zipOutputStream.write(buf, 0, bytesRead);  
       }  
           
            
     }
           // close ZipEntry to store the stream to the file  
            ((ZipOutputStream) zipOutputStream).closeEntry();  
	             zipOutputStream.close();  
            fileOutputStream.close();  

   System.out.println("zip complete---------------------------------------------------------------");
   

       } catch (IOException e) {  
System.out.println("exception"+e);
            e.printStackTrace();  

       }  
	} catch (Exception e) {
		System.out.println("exception"+e);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return resObj;
}
public  void zipFile( String zipFilePath,String sevaarthId,HttpServletResponse response) {  

    try {  
        
  		response.setContentType("application/zip");
		response.addHeader("Content-disposition","attachment; filename=" + sevaarthId+".zip");
		
		
         FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);  
         ZipOutputStream zipOutputStream = new ZipOutputStream((response.getOutputStream()) );  
        String tempFolderpath = System.getenv("TEMP");
      	String input_file=tempFolderpath+"\\Files";
      	
      	File Temp1=new File(input_file+"\\MainDetails_"+sevaarthId+".txt");
      	File Temp2=new File(input_file+"\\FamilyDetails_"+sevaarthId+".txt");
      	File Temp3=new File(input_file+"\\NomineeDetails_"+sevaarthId+".txt");
    	File Temp4=new File(input_file+"\\PnsnBrkDetails_"+sevaarthId+".txt");
        
          File[] f1={Temp1,Temp2,Temp3,Temp4} ;
    
      for(int i=0;i<4;i++){
           	 
   	 	ZipEntry zipEntry = new ZipEntry(f1[i].getName());
            ( zipOutputStream).putNextEntry(zipEntry);
            
            
          FileInputStream fileInputStream = new FileInputStream(f1[i]);  
           byte[] buf = new byte[1024];  
            int bytesRead;  
            // Read the input file by chucks of 1024 bytes  
            // and write the read bytes to the zip stream  
           while ((bytesRead = fileInputStream.read(buf)) > 0) {  
               zipOutputStream.write(buf, 0, bytesRead);  
       }  
           
            
     }
           // close ZipEntry to store the stream to the file  
            ((ZipOutputStream) zipOutputStream).closeEntry();  
	             zipOutputStream.close();  
            fileOutputStream.close();  

   System.out.println("zip complete---------------------------------------------------------------");
   

       } catch (IOException e) {  

            e.printStackTrace();  

       }  

  

    }  
	public void excelForMainDtls(Map inputMap) {

		String status = null;
		String circleCode=null;
		logger.error("logger 1) inside excelForMainDtls");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		String SEVAARTH_ID=null,INWARD_PENSION_ID=null,PNSNR_NAME=null,CASE_TYPE=null,DDO_CODE=null,DDO_NAME=null,OFFICE_CODE=null;
		String DDO_OFFICE=null,DEPARTMENT_ID=null,BIRTH_DATE=null,PENSION_TYPE=null,DESIGNATION=null,DSGN_NAME=null,PENSIONER_TYPE=null,PNSNR_ADDR=null;
		String DISTRICT_NAME=null,STATE_NAME=null,PNSNR_ADDR_PINCODE=null,PNSNR_ADDR_MOBILENO=null,PNSNR_ADDR_LANDLINENO=null,PNSNR_ADDR_RET=null;
		String PNSNR_ADDR_RET_PINCODE=null,	PNSNR_ADDR_RET_MOBILENO=null,PNSNR_ADDR_RET_LANDLINENO=null,PNSNR_ADDR_EMAILADDR=null,LOC_NAME=null,BANK_NAME=null;
		String BRANCH_NAME=null,BANK_ACCOUNT_NO=null,BANK_ADDRESS=null,JOINING_DATE=null,DEATH_DATE=null,FIR_DATE=null;
		String RETIREMENT_DATE=null,NonQualifyingServiceYears=null,NonQualifyingServiceMonth=null,NonQualifyingServiceDay=null,NetQualifyingServiceYears=null;
		String NetQualifyingServiceMonth=null,NetQualifyingServiceDay=null,LAST_PAY_SCALE=null,GRADE_PAY=null,AVERAGE_PAY=null,DP_RATE=null,PENSION_TOTAL_AMOUNT=null;
		String DeathcumRetirementGratuity=null,CVP_AMOUNT=null,COMMUTE_VAL=null,FamilyPension=null,EnhanceFamilyPension=null,GENDER_FLAG=null,RELIGION_NAME=null; 
		
		try {
			setSessionInfo(inputMap);
			String agCircle = StringUtility.getParameter("agCircle", request);
			String sevaarthId = StringUtility.getParameter("sevaarthId", request);
		
System.out.println("agCircle::"+agCircle);

			List mainList = new ArrayList();
			DownloadExcelForAGDaoImpl mainDao = new DownloadExcelForAGDaoImpl(null, serv.getSessionFactory());
			if(agCircle.equals("AG MUMBAI"))
				circleCode="8101";
			else if(agCircle.equals("DEMO AG"))
				circleCode="8301";
			else circleCode="8201";
			System.out.println("circleCode:"+circleCode);
			List MainResult = mainDao.excelForMainDtls(circleCode, sevaarthId);
			
			logger.error("logger 2)MainResult size" + MainResult.size());
			

			String space = new String(new char[1]).replace('\0', '|');
			String text = null,firstLine=null;
			if (MainResult != null && MainResult.size() > 0) {
				OutputStream lOutStream = null;
			
					try {
						Iterator it = MainResult.iterator();
						while (it.hasNext()) {
							Object[] rowList = (Object[]) it.next();
							mainList.add(rowList);

						}
						logger.error("logger 4)before outputstream");
						HttpServletResponse response = (HttpServletResponse) inputMap
								.get("responseObj");
						lOutStream = response.getOutputStream();

						StringBuilder lStrBldXML1 = new StringBuilder();
						
						
						
						//new line 
						String lineSeperator ="\r\n"; 
						//System.getProperty("line.separator");.....not working


						String os = System.getProperty("os.name");

						System.out.println("os  "+os);
						if (os.toLowerCase().indexOf("unix") > 0){
							lineSeperator="\n";

						} else if (os.toLowerCase().indexOf("windows") > 0){
							lineSeperator ="\r\n"; 

						} else {

						}
						
						
						

						
						

					

						
					
						
						// first line
					
						firstLine="SP_SEVARTH_ID"+adjustSpace(9)+space
						+"SP_TRNS_ID"+adjustSpace(3)+space
						+"SP_INS_DATE"+adjustSpace(90)+space
						+"SP_SALUTE"+adjustSpace(1)+space
						+"SP_FNAME"+adjustSpace(12)+space
						+"SP_MNAME"+adjustSpace(54)+space
						+"SP_LNAME"+adjustSpace(9)+space
						+"SP_CASE_TYPE"+adjustSpace(50)+space
						+"SP_DDO_CODE"+adjustSpace(7)+space
						+"SP_DDO_NAME"+adjustSpace(10)+space
						+"SP_FWD_OFF_CODE"+adjustSpace(9)+space
						+"SP_FWD_OFF_NAME"+adjustSpace(9)+space
						+"SP_DEPT_CODE"+adjustSpace(51)+space
						+"SP_DEPT_NAME"+adjustSpace(6)+space
						+"SP_DOB"+adjustSpace(50)+space
						+"SP_PNSN_CLASS"+adjustSpace(7)+space
						+"SP_DESG_CODE"+adjustSpace(10)+space
						+"SP_DESG_NAME"+adjustSpace(2)+space
						+"SP_GROUP"+adjustSpace(1)+space
						+"SP_BR_ADDR1"+adjustSpace(3)+space
						+"SP_BR_ADDR2"+adjustSpace(45)+space
						+"SP_BR_ADDR3"+adjustSpace(3)+space
						+"SP_BR_CITY"+adjustSpace(2)+space
						+"SP_BR_STATE"+space
						+"SP_BR_PHONE"+adjustSpace(40)+space
						+"SP_BR_MOB"+adjustSpace(42)+space
						+"SP_AR_ADDR1"+adjustSpace(51)+space
						+"SP_AR_ADDR2"+adjustSpace(49)+space
						+"SP_AR_ADDR3"+adjustSpace(5)+space
						+"SP_AR_CITY"+adjustSpace(88)+space
						+"SP_AR_STATE"+adjustSpace(13)+space
						+"SP_AR_PHONE"+adjustSpace(10)+space
						+"SP_AR_MOB"+adjustSpace(15)+space
						+"SP_EMAIL"+adjustSpace(17)+space
						+"SP_RET_PLACE"+adjustSpace(13)+space
						+"SP_TO_PENSION"+adjustSpace(10)+space
						+"SP_TO_GRTY"+adjustSpace(25)+space
						+"SP_BANK"+adjustSpace(25)+space
						+"SP_BANK_BRANCH"+adjustSpace(27)+space
						+"SP_ACC_NO"+adjustSpace(25)+space
						+"SP_BANK_CITY"+adjustSpace(25)+space
						+"SP_BANK_STATE"+adjustSpace(27)+space
						+"SP_DOA"+adjustSpace(6)+space
						+"SP_DOR"+adjustSpace(11)+space
						+"SP_DOD"+adjustSpace(9)+space
						+"SP_DOFIR"+adjustSpace(13)+space
						+"SP_MLTRY_FROM"+space
						+"SP_MLTRY_TO"+adjustSpace(1)+space
						+"SP_GSRVC_FROM"+adjustSpace(10)+space
						+"SP_GSRVC_TO"+adjustSpace(9)+space
						+"SP_NQS_YEARS"+adjustSpace(6)+space
						+"SP_NQS_MNTHS"+adjustSpace(3)+space
						+"SP_NQS_DAYS"+adjustSpace(9)+space
						+"SP_WTGE_YEARS"+space
						+"SP_WTGE_MNTHS"+space
						+"SP_WTGE_DAYS"+space
						+"SP_NET_QS_YEARS"+space
						+"SP_NET_QS_MNTHS"+space
						+"SP_NET_QS_DAYS"+space
						+"SP_LAST_PAY"+space
						+"SP_GRADE_PAY"+space
						+"SP_AE"+space
						+"SP_DP"+space
						+"SP_DA"+space
						+"SP_NPA"+space
						+"SP_OTHR"+space
						+"SP_PENSION"+space
						+"SP_RG_AMT"+space
						+"SP_DG_AMT"+space
						+"SP_CVP"+space
						+"SP_COMM_RATIO"+space
						+"SP_FP"+space
						+"SP_EFP"+space
						+"SP_PERMANENT"+space
						+"SP_CATG"+space
						+"SP_RLGN"+space
						+"SP_GENDER"+space
						+"SP_SPOUSE_NAME"+space
						+"SP_SPOUSE_RLTN"+space
						+"SP_DEPT_ENQ_CMPL_DATE"+space
						+"SP_COMM_ACK_DATE"+space
						+"SP_MCERT_DATE"+space
						+"SP_AGE_LOADING"+space
						+"SP_COMM_TYPE"+space
						+"SP_COMM_VALUE"+space
						+"SP_APPLN_PK"+space;
						
						
						logger.error("String firstLine:" + firstLine);
						
						lStrBldXML1.append(firstLine);

						lStrBldXML1.append(lineSeperator);
						logger.error("logger 6)mainList size" + mainList.size());
						for (int i = 0; i < mainList.size(); i++) {

							Object[] obj1 = (Object[]) mainList.get(i);

							
							SEVAARTH_ID=(String)obj1[0];
							if(obj1[1]==null)INWARD_PENSION_ID=" ";
							else
							INWARD_PENSION_ID=((BigInteger)obj1[1]).toString();
							
							if(obj1[2]==null)PNSNR_NAME=" ";
							else
							PNSNR_NAME=obj1[2].toString();
							
							if(obj1[3]==null)CASE_TYPE=" ";
							else
							CASE_TYPE=(String)obj1[3];
							
							if(obj1[4]==null)DDO_CODE=" ";
							else
							DDO_CODE=((BigInteger)obj1[4]).toString();
							
							if(obj1[5]==null)DDO_NAME=" ";
							else
							DDO_NAME=(String)obj1[5].toString();
							
							if(obj1[6]==null)OFFICE_CODE=" ";
							else
							OFFICE_CODE=(String)obj1[6];
							
							if(obj1[7]==null)DDO_OFFICE=" ";
							else
							DDO_OFFICE=(String)obj1[7].toString();
							
							if(obj1[8]==null)DEPARTMENT_ID=" ";
							else
							DEPARTMENT_ID=((BigInteger)obj1[8]).toString();
						
							if(obj1[9]==null)BIRTH_DATE=" ";
							else
							BIRTH_DATE=((String)obj1[9]).substring(0, 10);
							
							if(obj1[10]==null)PENSION_TYPE=" ";
							else
							PENSION_TYPE=(String)obj1[10];
							
							if(obj1[11]==null)DESIGNATION=" ";
							else
							DESIGNATION=(String)obj1[11];
						
							if(obj1[12]==null)DSGN_NAME=" ";
							else
							DSGN_NAME=(String)obj1[12].toString();
							
							if(obj1[13]==null)PENSIONER_TYPE=" ";
							else
							PENSIONER_TYPE=(String)obj1[13].toString();
							
							if(obj1[14]==null)PNSNR_ADDR=" ";
							else
							PNSNR_ADDR=(String)obj1[14].toString();
							
							if(obj1[15]==null)DISTRICT_NAME=" ";
							else
							DISTRICT_NAME=(String)obj1[15];
							
							if(obj1[16]==null)STATE_NAME=" ";
							else
							STATE_NAME=(String)obj1[16];
							
							if(obj1[17]==null)PNSNR_ADDR_PINCODE=" ";
							else
							PNSNR_ADDR_PINCODE=((Integer)obj1[17]).toString();
							
							if(obj1[18]==null)PNSNR_ADDR_MOBILENO=" ";
							else
							PNSNR_ADDR_MOBILENO=(String)obj1[18];
							
							if(obj1[19]==null)PNSNR_ADDR_LANDLINENO=" ";
							else
							PNSNR_ADDR_LANDLINENO=(String)obj1[19];
							
							if(obj1[20]==null)PNSNR_ADDR_RET=" ";
							else
							PNSNR_ADDR_RET=(String)obj1[20];
							
							if( obj1[21]==null)PNSNR_ADDR_RET_PINCODE=" ";
							else
							PNSNR_ADDR_RET_PINCODE=((Integer)obj1[21]).toString();
							
							if(obj1[22]==null)PNSNR_ADDR_RET_MOBILENO=" ";
							else
							PNSNR_ADDR_RET_MOBILENO=(String)obj1[22];
							
							if(obj1[23]==null)PNSNR_ADDR_RET_LANDLINENO=" ";
							else
							PNSNR_ADDR_RET_LANDLINENO=(String)obj1[23];
							
							if(obj1[24]==null)PNSNR_ADDR_EMAILADDR=" ";
							else
							PNSNR_ADDR_EMAILADDR=(String)obj1[24];
							
							if(obj1[25]==null)LOC_NAME=" ";
							else
							LOC_NAME=(String)obj1[25].toString();
							
							if(obj1[26]==null)BANK_NAME=" ";
							else
							BANK_NAME=(String)obj1[26].toString();
							
							if(obj1[27]==null)BRANCH_NAME=" ";
							else
							BRANCH_NAME=(String)obj1[27].toString();
							
							if(obj1[28]==null)BANK_ACCOUNT_NO=" ";
							else
							BANK_ACCOUNT_NO=(String)obj1[28];
							
							if(obj1[29]==null)BANK_ADDRESS=" ";
							else
							BANK_ADDRESS=(String)obj1[29];
							
							if(obj1[30]==null)JOINING_DATE=" ";
							else
							JOINING_DATE=((String)obj1[30]).toString().substring(0, 10);
							
							if(obj1[31]==null)RETIREMENT_DATE=" ";
							else
							RETIREMENT_DATE=((String)obj1[31]).toString().substring(0, 10);
							
							if(obj1[32]==null)DEATH_DATE=" ";
							else
							DEATH_DATE=((String)obj1[32]).toString().substring(0, 10);
							
							if(obj1[33]==null)FIR_DATE=" ";
							else
							FIR_DATE=((String)obj1[33]).toString().substring(0, 10);
							
							if(obj1[34]==null)JOINING_DATE=" ";
							else
							JOINING_DATE=((String)obj1[34]).toString().substring(0, 10);
							
							if(obj1[35]==null)RETIREMENT_DATE=" ";
							else
							RETIREMENT_DATE=((String)obj1[35]).toString().substring(0, 10);
							
							if(obj1[36]==null)NonQualifyingServiceYears=" ";
							else
							NonQualifyingServiceYears=((Short)obj1[36]).toString();
							
							if(obj1[37]==null)NonQualifyingServiceMonth=" ";
							else
							NonQualifyingServiceMonth=((Short)obj1[37]).toString();
							
							if(obj1[38]==null)NonQualifyingServiceDay=" ";
							else
							NonQualifyingServiceDay=((Short)obj1[38]).toString();
							
							if(obj1[39]==null)NetQualifyingServiceYears=" ";
							else
							NetQualifyingServiceYears=((Short)obj1[39]).toString();
							
							if(obj1[40]==null)NetQualifyingServiceMonth=" ";
							else
							NetQualifyingServiceMonth=((Short)obj1[40]).toString();
							
							if(obj1[41]==null)NetQualifyingServiceDay=" ";
							else
							NetQualifyingServiceDay=((Short)obj1[41]).toString();
							
							if(obj1[17]==null)LAST_PAY_SCALE=" ";
							else
							LAST_PAY_SCALE=((BigInteger)obj1[42]).toString();
							
							if(obj1[43]==null)GRADE_PAY=" ";
							else
							GRADE_PAY=((BigDecimal)obj1[43]).toString();
						
							if(obj1[44]==null)AVERAGE_PAY=" ";
							else
							AVERAGE_PAY=((BigDecimal)obj1[44]).toString();
							
							if(obj1[45]==null)DP_RATE=" ";
							else
							DP_RATE=((BigDecimal)obj1[45]).toString();
							
							if(obj1[46]==null)PENSION_TOTAL_AMOUNT=" ";
							else
							PENSION_TOTAL_AMOUNT=((BigDecimal)obj1[46]).toString();
							
							if(obj1[47]==null)DeathcumRetirementGratuity=" ";
							else
							DeathcumRetirementGratuity=((BigDecimal)obj1[47]).toString();
							
							if(obj1[48]==null)CVP_AMOUNT=" ";
							else
							CVP_AMOUNT=((BigDecimal)obj1[48]).toString();
							
							if(obj1[49]==null)COMMUTE_VAL=" ";
							else
							COMMUTE_VAL=((Double)obj1[49]).toString();
						
							if(obj1[50]==null)FamilyPension=" ";
							else
							FamilyPension=((BigDecimal)obj1[50]).toString();
							
							if(obj1[51]==null)EnhanceFamilyPension=" ";
							else
							EnhanceFamilyPension=((BigDecimal)obj1[51]).toString();
							
							if(obj1[52]==null)GENDER_FLAG=" ";
							else
							GENDER_FLAG=(( Character)obj1[52]).toString();
							
							if(obj1[53]==null)RELIGION_NAME=" ";
							else
							RELIGION_NAME=(( String)obj1[53]).toString();
							
		
							text=SEVAARTH_ID+space
							+INWARD_PENSION_ID+space
							+space
							//+space
							+PNSNR_NAME+space+space+space
							+CASE_TYPE+space
							+DDO_CODE+space
							+DDO_NAME+space
							+OFFICE_CODE+space
							+DDO_OFFICE+space
							+DEPARTMENT_ID+space
							+space
							+BIRTH_DATE+space
							+PENSION_TYPE+space
							+DESIGNATION+space
							+DSGN_NAME+space
							+PENSIONER_TYPE+space
							+PNSNR_ADDR+space
							+space
							+space
							+DISTRICT_NAME+space
							+STATE_NAME+space
							+PNSNR_ADDR_PINCODE+space
							+PNSNR_ADDR_MOBILENO+space
							+PNSNR_ADDR_LANDLINENO+space
							
							+PNSNR_ADDR_RET+space
							+space
							+space
							+PNSNR_ADDR_RET_PINCODE+space
							+PNSNR_ADDR_RET_MOBILENO+space
							+PNSNR_ADDR_RET_LANDLINENO+space
							+PNSNR_ADDR_EMAILADDR+space
							+LOC_NAME+space+space+space
							+BANK_NAME+space
							+BRANCH_NAME+space
							+BANK_ACCOUNT_NO+space
							+space
						
							+BANK_ADDRESS+space
							
							+JOINING_DATE+space
							+RETIREMENT_DATE+space
							
							+(DEATH_DATE!=" "?DEATH_DATE:space)+space
							
							+(FIR_DATE!=" "?FIR_DATE:space)+space
							+JOINING_DATE+space
							+space
							+space
							+space
							+space
							+NonQualifyingServiceYears+space 
							+NonQualifyingServiceMonth+space
							+NonQualifyingServiceDay+space
							+space
							+space
							+space							
							+ NetQualifyingServiceYears+space
							+ NetQualifyingServiceMonth+space
							+NetQualifyingServiceDay+space
							+LAST_PAY_SCALE+space
							+GRADE_PAY+space
							+AVERAGE_PAY+space
							+DP_RATE+space
							+space
							+space
							+space
							+PENSION_TOTAL_AMOUNT+space
							+DeathcumRetirementGratuity+space
							+space
							+CVP_AMOUNT+space
							+COMMUTE_VAL+space
							+FamilyPension+space
							+ EnhanceFamilyPension+space
							+space
							+space+space
							+GENDER_FLAG+space
							+(RELIGION_NAME!=" "?RELIGION_NAME:space) 
							+space
							+space
							+space
							+space
							+space
							+space
							+space
							+space
							+space;
							inputMap.put("mainList", MainResult);

							
							// ----------------------------------------------------------

							lStrBldXML1.append(text);
							lStrBldXML1.append(lineSeperator);
						}

						

						/*String lStrFileName = "MainDetails_"+sevaarthId;
						String fileName = " ";
						if (lStrFileName != null && lStrFileName.length() > 0) {
							fileName = lStrFileName + ".txt";
						}
						response.setContentType("text/plain;charset=UTF-8");

						response.addHeader("Content-disposition",
								"attachment; filename=" + fileName);
						response.setCharacterEncoding("UTF-8");
						logger.error("logger 7)after write");
						byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
								.getBytes();
						lOutStream.write(allBytesInBlob);
						lOutStream.flush();*/
						
						
						String tempFolderpath = System.getenv("TEMP");
						System.out.println("temp:"+tempFolderpath);
						 String temppath = tempFolderpath + "\\Files\\" + "MainDetails_"+sevaarthId;
						File file = new File(tempFolderpath+"\\Files"+"\\MainDetails_"+sevaarthId+".txt");
						 
						// if file doesnt exists, then create it
						if (!file.exists()) {
							file.createNewFile();
						}
			 
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(lStrBldXML1.toString());
						bw.close();
						
						logger.error("logger 8)done");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						lOutStream.close();
					}
				}
			else
			{
				OutputStream lOutStream1 = null;
				try{
					
					HttpServletResponse response = (HttpServletResponse) inputMap
					.get("responseObj");
					lOutStream1 = response.getOutputStream();
					StringBuilder lStrBldXML1 = new StringBuilder();
					
					// first line
					
					firstLine="SP_SEVARTH_ID"+adjustSpace(9)+space
					+"SP_TRNS_ID"+adjustSpace(3)+space
					+"SP_INS_DATE"+adjustSpace(90)+space
					+"SP_SALUTE"+adjustSpace(1)+space
					+"SP_FNAME"+adjustSpace(12)+space
					+"SP_MNAME"+adjustSpace(54)+space
					+"SP_LNAME"+adjustSpace(9)+space
					+"SP_CASE_TYPE"+adjustSpace(50)+space
					+"SP_DDO_CODE"+adjustSpace(7)+space
					+"SP_DDO_NAME"+adjustSpace(10)+space
					+"SP_FWD_OFF_CODE"+adjustSpace(9)+space
					+"SP_FWD_OFF_NAME"+adjustSpace(9)+space
					+"SP_DEPT_CODE"+adjustSpace(51)+space
					+"SP_DEPT_NAME"+adjustSpace(6)+space
					+"SP_DOB"+adjustSpace(50)+space
					+"SP_PNSN_CLASS"+adjustSpace(7)+space
					+"SP_DESG_CODE"+adjustSpace(10)+space
					+"SP_DESG_NAME"+adjustSpace(2)+space
					+"SP_GROUP"+adjustSpace(1)+space
					+"SP_BR_ADDR1"+adjustSpace(3)+space
					+"SP_BR_ADDR2"+adjustSpace(45)+space
					+"SP_BR_ADDR3"+adjustSpace(3)+space
					+"SP_BR_CITY"+adjustSpace(2)+space
					+"SP_BR_STATE"+space
					+"SP_BR_PHONE"+adjustSpace(40)+space
					+"SP_BR_MOB"+adjustSpace(42)+space
					+"SP_AR_ADDR1"+adjustSpace(51)+space
					+"SP_AR_ADDR2"+adjustSpace(49)+space
					+"SP_AR_ADDR3"+adjustSpace(5)+space
					+"SP_AR_CITY"+adjustSpace(88)+space
					+"SP_AR_STATE"+adjustSpace(13)+space
					+"SP_AR_PHONE"+adjustSpace(10)+space
					+"SP_AR_MOB"+adjustSpace(15)+space
					+"SP_EMAIL"+adjustSpace(17)+space
					+"SP_RET_PLACE"+adjustSpace(13)+space
					+"SP_TO_PENSION"+adjustSpace(10)+space
					+"SP_TO_GRTY"+adjustSpace(25)+space
					+"SP_BANK"+adjustSpace(25)+space
					+"SP_BANK_BRANCH"+adjustSpace(27)+space
					+"SP_ACC_NO"+adjustSpace(25)+space
					+"SP_BANK_CITY"+adjustSpace(25)+space
					+"SP_BANK_STATE"+adjustSpace(27)+space
					+"SP_DOA"+adjustSpace(6)+space
					+"SP_DOR"+adjustSpace(11)+space
					+"SP_DOD"+adjustSpace(9)+space
					+"SP_DOFIR"+adjustSpace(13)+space
					+"SP_MLTRY_FROM"+space
					+"SP_MLTRY_TO"+adjustSpace(1)+space
					+"SP_GSRVC_FROM"+adjustSpace(10)+space
					+"SP_GSRVC_TO"+adjustSpace(9)+space
					+"SP_NQS_YEARS"+adjustSpace(6)+space
					+"SP_NQS_MNTHS"+adjustSpace(3)+space
					+"SP_NQS_DAYS"+adjustSpace(9)+space
					+"SP_WTGE_YEARS"+space
					+"SP_WTGE_MNTHS"+space
					+"SP_WTGE_DAYS"+space
					+"SP_NET_QS_YEARS"+space
					+"SP_NET_QS_MNTHS"+space
					+"SP_NET_QS_DAYS"+space
					+"SP_LAST_PAY"+space
					+"SP_GRADE_PAY"+space
					+"SP_AE"+space
					+"SP_DP"+space
					+"SP_DA"+space
					+"SP_NPA"+space
					+"SP_OTHR"+space
					+"SP_PENSION"+space
					+"SP_RG_AMT"+space
					+"SP_DG_AMT"+space
					+"SP_CVP"+space
					+"SP_COMM_RATIO"+space
					+"SP_FP"+space
					+"SP_EFP"+space
					+"SP_PERMANENT"+space
					+"SP_CATG"+space
					+"SP_RLGN"+space
					+"SP_GENDER"+space
					+"SP_SPOUSE_NAME"+space
					+"SP_SPOUSE_RLTN"+space
					+"SP_DEPT_ENQ_CMPL_DATE"+space
					+"SP_COMM_ACK_DATE"+space
					+"SP_MCERT_DATE"+space
					+"SP_AGE_LOADING"+space
					+"SP_COMM_TYPE"+space
					+"SP_COMM_VALUE"+space
					+"SP_APPLN_PK"+space;
					
					
					logger.error("String firstLine:" + firstLine);
					
					lStrBldXML1.append(firstLine);
					
					
			/*		String lStrFileName = "MainDetails_"+sevaarthId;
					String fileName = " ";
					if (lStrFileName != null && lStrFileName.length() > 0) {
						fileName = lStrFileName + ".txt";
					}
					response.setContentType("text/plain;charset=UTF-8");

					response.addHeader("Content-disposition",
							"attachment; filename=" + fileName);
					response.setCharacterEncoding("UTF-8");
					logger.error("logger 7)after write");
					byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
							.getBytes();
					lOutStream1.write(allBytesInBlob);
					lOutStream1.flush();*/
					String tempFolderpath = System.getenv("TEMP");
					System.out.println("temp:"+tempFolderpath);
					 String temppath = tempFolderpath + "\\Files\\" + "MainDetails_"+sevaarthId;
					File file = new File(tempFolderpath+"\\Files"+"\\MainDetails_"+sevaarthId+".txt");
					 
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
		 
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(lStrBldXML1.toString());
					bw.close();
					
					
					
					
					
					logger.error("logger 8)done");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lOutStream1.close();
				}
				
				
				
				
			}
			
			
				status = "true";
				
			
		
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		

		//return resObj;

	}

	public void excelForFlyDtls(Map inputMap) {

		String status = null;
		String circleCode=null;
		logger.error("logger 1) inside excelForFlyDtls");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		String PENSIONERDTL_ID=null,NAME=null,BIRTH_DATE=null,RELATION=null,HANDICAPE_FLAG=null,GUARDIAN_NAME=null;
		
		try {
			setSessionInfo(inputMap);
			String agCircle = StringUtility.getParameter("agCircle", request);
			String sevaarthId = StringUtility.getParameter("sevaarthId", request);
		


			List mainList = new ArrayList();
			DownloadExcelForAGDaoImpl mainDao = new DownloadExcelForAGDaoImpl(null, serv.getSessionFactory());
			if(agCircle.equals("AG MUMBAI"))
				circleCode="8101";
			else circleCode="8201";
			List MainResult = mainDao.excelForFlyDtls(circleCode, sevaarthId);
			
			logger.error("logger 2)MainResult size" + MainResult.size());
			
			String space = new String(new char[1]).replace('\0', '|');
			HttpServletResponse response = (HttpServletResponse) inputMap
			.get("responseObj");
			OutputStream lOutStream = null;
	lOutStream = response.getOutputStream();

	StringBuilder lStrBldXML1 = new StringBuilder();
			
			String text = null,firstLine=null;
			if (MainResult != null && MainResult.size() > 0) {
				
			
					try {
						Iterator it = MainResult.iterator();
						while (it.hasNext()) {
							Object[] rowList = (Object[]) it.next();
							mainList.add(rowList);

						}
						logger.error("logger 4)before outputstream");
					
					/*	String lineSeperator = System
								.getProperty("line.separator");*/
						//new line 
						String lineSeperator ="\r\n"; 
						//System.getProperty("line.separator");.....not working


						String os = System.getProperty("os.name");

						System.out.println("os  "+os);
						if (os.toLowerCase().indexOf("unix") > 0){
							lineSeperator="\n";

						} else if (os.toLowerCase().indexOf("windows") > 0){
							lineSeperator ="\r\n"; 

						} else {

						}
						
						
						
						// first line
					
						firstLine="SPF_SP_TRNS_ID"+adjustSpace(5)+space+"SPF_NAME"+adjustSpace(46)+space
						+"SPF_DOB"+adjustSpace(20)+space+"SPF_RELATION"+adjustSpace(2)+space+"SPF_HANDICAP"+adjustSpace(20)+space+"SPF_GUARDIAN"+adjustSpace(20)+space+"SPF_GRDN_RLTN";
						
						logger.error("String firstLine:" + firstLine);
						
						lStrBldXML1.append(firstLine);

						lStrBldXML1.append(lineSeperator);
						logger.error("logger 6)mainList size" + mainList.size());
						for (int i = 0; i < mainList.size(); i++) {

							Object[] obj1 = (Object[]) mainList.get(i);

							//PENSIONERDTL_ID=(BigInteger)obj1[0]+space;
							if(obj1[0]==null)PENSIONERDTL_ID=" ";
							else
								PENSIONERDTL_ID=((BigInteger)obj1[0]).toString();
						
							//NAME=(String)obj1[1]+space;
							if(obj1[1]==null)NAME=" ";
							else
								NAME=((String)obj1[1]).toString();
							
							//BIRTH_DATE=(Date)obj1[2]+space;
							if(obj1[2]==null)BIRTH_DATE=" ";
							else
								BIRTH_DATE=((Date)obj1[2]).toString().substring(0, 10);
							
							//RELATION=(String)obj1[3]+space;
							if(obj1[3]==null)RELATION=" ";
							else
								RELATION=(obj1[3]).toString();
							
							//HANDICAPE_FLAG=(String)obj1[4]+space;
							if(obj1[4]==null)HANDICAPE_FLAG=" ";
							else
								HANDICAPE_FLAG=(obj1[4]).toString();
							
							//GUARDIAN_NAME=(String)obj1[5]+space;
							if(obj1[5]==null)GUARDIAN_NAME=" ";
							else
								GUARDIAN_NAME=(obj1[5]).toString();
							
						
		
							text=PENSIONERDTL_ID+adjustSpace(20-PENSIONERDTL_ID.length())+space+NAME+adjustSpace(50-NAME.length())+space+BIRTH_DATE+adjustSpace(30-BIRTH_DATE.length())+space
							+RELATION+adjustSpace(10-RELATION.length())+space+HANDICAPE_FLAG+adjustSpace(30)+space+GUARDIAN_NAME+'|';
							inputMap.put("mainList", MainResult);

							
							// ----------------------------------------------------------

							lStrBldXML1.append(text);
							lStrBldXML1.append(lineSeperator);
						}

						

					/*	String lStrFileName = "FamilyDetails_"+sevaarthId;
						String fileName = " ";
						if (lStrFileName != null && lStrFileName.length() > 0) {
							fileName = lStrFileName + ".txt";
						}
						response.setContentType("text/plain;charset=UTF-8");
						//response.setContentType("application/pdf");
						
						response.addHeader("Content-disposition",
								"attachment; filename=" + fileName);
						response.setCharacterEncoding("UTF-8");
						logger.error("logger 7)after write");
						byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
								.getBytes();
						lOutStream.write(allBytesInBlob);
						lOutStream.flush();*/

						String tempFolderpath = System.getenv("TEMP");
						System.out.println("temp:"+tempFolderpath);
						 String temppath = tempFolderpath + "\\Files\\" + "FamilyDetails_"+sevaarthId;
						File file = new File(tempFolderpath+"\\Files"+"\\FamilyDetails_"+sevaarthId+".txt");
						 
						// if file doesnt exists, then create it
						if (!file.exists()) {
							file.createNewFile();
						}
			 
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(lStrBldXML1.toString());
						bw.close();
						
						
						/* Document document=new Document();
						 PdfWriter.getInstance(document,new FileOutputStream("D:\\myfile.pdf"));
						 document.open();
						 document.add(new Paragraph("This is my file"));
						 document.close();
						*/
						
						
						logger.error("logger 8)done");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						lOutStream.close();
					}
				}
			else
			{
				try{
					firstLine="SPN_SP_TRNS_ID"+adjustSpace(5)+space+"SPN_SALUTE"+adjustSpace(46)+space+"SPN_NAME"+adjustSpace(12)+space+"SPN_RELATION"+space+"SPN_SHARE_TYPE"+adjustSpace(2)+space+"SPN_SHARE"+adjustSpace(2)+space+"SPN_GUARDIAN"+adjustSpace(2)+space+"SPN_GRDN_RLTN";
						logger.error("String firstLine:" + firstLine);
					
					lStrBldXML1.append(firstLine);

			/*		
				String lStrFileName = "FamilyDetails_"+sevaarthId;
				String fileName = " ";
				if (lStrFileName != null && lStrFileName.length() > 0) {
					fileName = lStrFileName + ".txt";
				}
				response.setContentType("text/plain;charset=UTF-8");
			//	response.setContentType("application/pdf");
				
				response.addHeader("Content-disposition",
						"attachment; filename=" + fileName);
				response.setCharacterEncoding("UTF-8");
				logger.error("logger 7)after write");
				byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
						.getBytes();
				lOutStream.write(allBytesInBlob);
				lOutStream.flush();*/
					
					String tempFolderpath = System.getenv("TEMP");
					System.out.println("temp:"+tempFolderpath);
					 String temppath = tempFolderpath + "\\Files\\" + "FamilyDetails_"+sevaarthId;
					File file = new File(tempFolderpath+"\\Files"+"\\FamilyDetails_"+sevaarthId+".txt");
					 
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
		 
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(lStrBldXML1.toString());
					bw.close();
					
				logger.error("logger 8)done");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lOutStream.close();
			}
				
			}
			
			
			
				status = "true";
				
			
		
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		

	//	return resObj;

	}

	
	
	public void excelForNomiDtls(Map inputMap) {

		String status = null;
		String circleCode=null;
		logger.error("logger 1) inside excelForNomiDtls");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		String PENSIONERDTL_ID=null,NAME=null,RELATION=null,PERCENTAGE=null,HANDICAPE_FLAG=null,AMT_OF_SHARE_PERCN=null,ALTERNATE_NOMINEE=null;
		
		try {
			setSessionInfo(inputMap);
			String agCircle = StringUtility.getParameter("agCircle", request);
			String sevaarthId = StringUtility.getParameter("sevaarthId", request);
		


			List mainList = new ArrayList();
			DownloadExcelForAGDaoImpl mainDao = new DownloadExcelForAGDaoImpl(null, serv.getSessionFactory());
			if(agCircle.equals("AG MUMBAI"))
				circleCode="8101";
			else circleCode="8201";
			List MainResult = mainDao.excelForNomiDtls(circleCode, sevaarthId);
			
			logger.error("logger 2)MainResult size" + MainResult.size());
			String space = new String(new char[1]).replace('\0', '|');

			String text = null,firstLine=null;
			HttpServletResponse response = (HttpServletResponse) inputMap
			.get("responseObj");
			OutputStream lOutStream = null;
			lOutStream = response.getOutputStream();

			StringBuilder lStrBldXML1 = new StringBuilder();
			if (MainResult != null && MainResult.size() > 0) {
			
			
					try {
						Iterator it = MainResult.iterator();
						while (it.hasNext()) {
							Object[] rowList = (Object[]) it.next();
							mainList.add(rowList);

						}
						logger.error("logger 4)before outputstream");
					
						/*String lineSeperator = System
								.getProperty("line.separator");*/
						//new line 
						String lineSeperator ="\r\n"; 
						//System.getProperty("line.separator");.....not working


						String os = System.getProperty("os.name");

						System.out.println("os  "+os);
						if (os.toLowerCase().indexOf("unix") > 0){
							lineSeperator="\n";

						} else if (os.toLowerCase().indexOf("windows") > 0){
							lineSeperator ="\r\n"; 

						} else {

						}
						
					
						
						// first line
					
						firstLine="SPN_SP_TRNS_ID"+adjustSpace(5)+space+"SPN_SALUTE"+adjustSpace(46)+space+"SPN_NAME"+adjustSpace(12)+space+"SPN_RELATION"+space+"SPN_SHARE_TYPE"+adjustSpace(2)+space+"SPN_SHARE"+adjustSpace(2)+space+"SPN_GUARDIAN"+adjustSpace(2)+space+"SPN_GRDN_RLTN";
						
						logger.error("String firstLine:" + firstLine);
						
						lStrBldXML1.append(firstLine);

						lStrBldXML1.append(lineSeperator);
						logger.error("logger 6)mainList size" + mainList.size());
						for (int i = 0; i < mainList.size(); i++) {

							Object[] obj1 = (Object[]) mainList.get(i);

							
							if(obj1[0]==null)PENSIONERDTL_ID=" ";
							else
								PENSIONERDTL_ID=((BigInteger)obj1[0]).toString();
						
							
							if(obj1[1]==null)NAME=" ";
							else
								NAME=((String)obj1[1]).toString();
							
							
							if(obj1[2]==null)RELATION=" ";
							else
								RELATION=((String)obj1[2]).toString();
							
							
							if(obj1[3]==null)PERCENTAGE=" ";
							else
								PERCENTAGE=((BigDecimal)obj1[3]).toString();
							
						
							if(obj1[4]==null)AMT_OF_SHARE_PERCN=" ";
							else
								AMT_OF_SHARE_PERCN=((BigDecimal)obj1[4]).toString();
							
							
							if(obj1[5]==null)ALTERNATE_NOMINEE=" ";
							else
								ALTERNATE_NOMINEE=((String)obj1[5]).toString();
							
						
		
							text=PENSIONERDTL_ID+adjustSpace(20-PENSIONERDTL_ID.length())+space+space+NAME+adjustSpace(50-NAME.length())+space+
							RELATION+adjustSpace(20-RELATION.length())+space+space+PERCENTAGE+adjustSpace(10-PERCENTAGE.length())+space
						//	+AMT_OF_SHARE_PERCN+adjustSpace(20-AMT_OF_SHARE_PERCN.length())+space
							+ALTERNATE_NOMINEE+'|';
							inputMap.put("mainList", MainResult);

							
							// ----------------------------------------------------------

							lStrBldXML1.append(text);
							lStrBldXML1.append(lineSeperator);
						}

						

					/*	String lStrFileName = "NomineeDetails_"+sevaarthId;
						String fileName = " ";
						if (lStrFileName != null && lStrFileName.length() > 0) {
							fileName = lStrFileName + ".txt";
						}
						response.setContentType("text/plain;charset=UTF-8");

						response.addHeader("Content-disposition",
								"attachment; filename=" + fileName);
						response.setCharacterEncoding("UTF-8");
						logger.error("logger 7)after write");
						byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
								.getBytes();
						lOutStream.write(allBytesInBlob);
						lOutStream.flush();*/
						
						
						String tempFolderpath = System.getenv("TEMP");
						System.out.println("temp:"+tempFolderpath);
						 String temppath = tempFolderpath + "\\Files\\" + "NomineeDetails_"+sevaarthId;
						File file = new File(tempFolderpath+"\\Files"+"\\NomineeDetails_"+sevaarthId+".txt");
						 
						// if file doesnt exists, then create it
						if (!file.exists()) {
							file.createNewFile();
						}
			 
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(lStrBldXML1.toString());
						bw.close();
						
						logger.error("logger 8)done");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						lOutStream.close();
					}
				}
			
			else
			{
				try{
					firstLine="SPN_SP_TRNS_ID"+adjustSpace(5)+space+"SPN_SALUTE"+adjustSpace(46)+space+"SPN_NAME"+adjustSpace(12)+space+"SPN_RELATION"+space+"SPN_SHARE_TYPE"+adjustSpace(2)+space+"SPN_SHARE"+adjustSpace(2)+space+"SPN_GUARDIAN"+adjustSpace(2)+space+"SPN_GRDN_RLTN";
					
					logger.error("String firstLine:" + firstLine);
					
					lStrBldXML1.append(firstLine);

					/*String lStrFileName = "NomineeDetails_"+sevaarthId;
					String fileName = " ";
					if (lStrFileName != null && lStrFileName.length() > 0) {
						fileName = lStrFileName + ".txt";
					}
					response.setContentType("text/plain;charset=UTF-8");

					response.addHeader("Content-disposition",
							"attachment; filename=" + fileName);
					response.setCharacterEncoding("UTF-8");
					logger.error("logger 7)after write");
					byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
							.getBytes();
					lOutStream.write(allBytesInBlob);
					lOutStream.flush();*/
					
					String tempFolderpath = System.getenv("TEMP");
					System.out.println("temp:"+tempFolderpath);
					 String temppath = tempFolderpath + "\\Files\\" + "NomineeDetails_"+sevaarthId;
					File file = new File(tempFolderpath+"\\Files"+"\\NomineeDetails_"+sevaarthId+".txt");
					 
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
		 
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(lStrBldXML1.toString());
					bw.close();
					
					
					logger.error("logger 8)done");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lOutStream.close();
				}
			}
				status = "true";
				
			
		
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		

		//return resObj;

	}


	public void excelForpnsnBrkDtls(Map inputMap) {

		String status = null;
		String circleCode=null;
		logger.error("logger 1) inside excelForpnsnBrkDtls");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		String PENSIONERDTL_ID=null,SERVICE_BREAKTYPE_LOOKUPID=null;
		
		try {
			setSessionInfo(inputMap);
			String agCircle = StringUtility.getParameter("agCircle", request);
			String sevaarthId = StringUtility.getParameter("sevaarthId", request);
		


			List mainList = new ArrayList();
			DownloadExcelForAGDaoImpl mainDao = new DownloadExcelForAGDaoImpl(null, serv.getSessionFactory());
			if(agCircle.equals("AG MUMBAI"))
				circleCode="8101";
			else circleCode="8201";
			List MainResult = mainDao.excelForpnsnBrkDtls(circleCode, sevaarthId);
			
			logger.error("logger 2)MainResult size" + MainResult.size());
			String space = new String(new char[1]).replace('\0', '|');

			String text = null,firstLine=null;
			HttpServletResponse response = (HttpServletResponse) inputMap
			.get("responseObj");
			OutputStream lOutStream = null;
			lOutStream = response.getOutputStream();
			
			StringBuilder lStrBldXML1 = new StringBuilder();
		
			if (MainResult != null && MainResult.size() > 0) {
				
			
					try {
						Iterator it = MainResult.iterator();
						while (it.hasNext()) {
							Object[] rowList = (Object[]) it.next();
							mainList.add(rowList);

						}
						logger.error("logger 4)before outputstream");
					
						/*String lineSeperator = System
								.getProperty("line.separator");
						*/
						//new line 
						String lineSeperator ="\r\n"; 
						//System.getProperty("line.separator");.....not working


						String os = System.getProperty("os.name");

						System.out.println("os  "+os);
						if (os.toLowerCase().indexOf("unix") > 0){
							lineSeperator="\n";

						} else if (os.toLowerCase().indexOf("windows") > 0){
							lineSeperator ="\r\n"; 

						} else {

						}
						
						
						
						// first line
					
						firstLine="PENSIONERDTL_ID"+adjustSpace(5)+space+"SERVICE_BREAKTYPE_LOOKUPID"+space;
						
						logger.error("String firstLine:" + firstLine);
						
						lStrBldXML1.append(firstLine);

						lStrBldXML1.append(lineSeperator);
						logger.error("logger 6)mainList size" + mainList.size());
						for (int i = 0; i < mainList.size(); i++) {

							Object[] obj1 = (Object[]) mainList.get(i);

							
							if(obj1[0]==null)PENSIONERDTL_ID=" ";
							else
								PENSIONERDTL_ID=((BigInteger)obj1[0]).toString();
						
							
							if(obj1[1]==null)SERVICE_BREAKTYPE_LOOKUPID=" ";
							else
								SERVICE_BREAKTYPE_LOOKUPID=((BigInteger)obj1[1]).toString();
							
							
							
		
							text=PENSIONERDTL_ID+adjustSpace(20-PENSIONERDTL_ID.length())+space+SERVICE_BREAKTYPE_LOOKUPID;
							inputMap.put("mainList", MainResult);

							
							// ----------------------------------------------------------

							lStrBldXML1.append(text);
							lStrBldXML1.append(lineSeperator);
						}

						
/*
						String lStrFileName = "PnsnBrkDetails_"+sevaarthId;
						String fileName = " ";
						if (lStrFileName != null && lStrFileName.length() > 0) {
							fileName = lStrFileName + ".txt";
						}
						response.setContentType("text/plain;charset=UTF-8");

						response.addHeader("Content-disposition",
								"attachment; filename=" + fileName);
						response.setCharacterEncoding("UTF-8");
						logger.error("logger 7)after write");
						byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
								.getBytes();
						lOutStream.write(allBytesInBlob);
						lOutStream.flush();*/
						
						String tempFolderpath = System.getenv("TEMP");
						System.out.println("temp:"+tempFolderpath);
						 String temppath = tempFolderpath + "\\Files\\" + "PnsnBrkDetails_"+sevaarthId;
						File file = new File(tempFolderpath+"\\Files"+"\\PnsnBrkDetails_"+sevaarthId+".txt");
						 
						// if file doesnt exists, then create it
						if (!file.exists()) {
							file.createNewFile();
						}
			 
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(lStrBldXML1.toString());
						bw.close();
						logger.error("logger 8)done");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						lOutStream.close();
					}
				}
			
			else
			{
				try{
					
					// first line
				
					firstLine="PENSIONERDTL_ID"+adjustSpace(5)+space+"SERVICE_BREAKTYPE_LOOKUPID"+space;
					
					logger.error("String firstLine:" + firstLine);
					
					lStrBldXML1.append(firstLine);

					/*String lStrFileName = "PnsnBrkDetails_"+sevaarthId;
				String fileName = " ";
				if (lStrFileName != null && lStrFileName.length() > 0) {
					fileName = lStrFileName + ".txt";
				}
				response.setContentType("text/plain;charset=UTF-8");

				response.addHeader("Content-disposition",
						"attachment; filename=" + fileName);
				response.setCharacterEncoding("UTF-8");
				logger.error("logger 7)after write");
				byte[] allBytesInBlob = (byte[]) lStrBldXML1.toString()
						.getBytes();
				lOutStream.write(allBytesInBlob);
				lOutStream.flush();*/
					
					
					String tempFolderpath = System.getenv("TEMP");
					System.out.println("temp:"+tempFolderpath);
					 String temppath = tempFolderpath + "\\Files\\" + "PnsnBrkDetails_"+sevaarthId;
					File file = new File(tempFolderpath+"\\Files"+"\\PnsnBrkDetails_"+sevaarthId+".txt");
					 
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
		 
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(lStrBldXML1.toString());
					bw.close();
				logger.error("logger 8)done");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lOutStream.close();
			}
				
			}
			
				status = "true";
				
			
		
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		

		//return resObj;

	}
	
}
