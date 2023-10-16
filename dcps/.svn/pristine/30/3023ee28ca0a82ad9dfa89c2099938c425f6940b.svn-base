package com.tcs.sgv.nps.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.helper.ColumnVo;
import com.tcs.sgv.common.helper.ExcelExportHelper;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.util.query.DateUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;

import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.nps.dao.FormS1UpdateDAO;
import com.tcs.sgv.nps.dao.FormS1UpdateDAOImpl;

public class NpsFileGenerateExport extends ServiceImpl  {
	/* Global Variable for Logger Class */
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
	//private static String OUTPUT_FILE = "/opt/servers/HTE/jboss-eap-6.2/standalone/deployments/ROOT.war/outputnps/";
	//private static String OUTPUT_FILE = "D:/outputnps/";
	//IMAGE file COPY file path
	//private static String OUTPUT_FILE_PHOTO="/opt/servers/HTE/jboss-eap-6.2/standalone/deployments/ROOT.war/outputnps/images/";
	//private static String OUTPUT_FILE_PHOTO="D:/outputnps/images/"; 
	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	/* Resource bundle for the constants */
	// ResourceBundle.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");
	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/nps/NPSConstants");
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

			
	
	 public ResultObject generateNpsFileStatusExcel(Map objectArgs) throws Exception {
	    {
	    	gLogger.info("Inside Get loadEmpDtlsDdoWise");
	    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	     	Long lLngLocId = null;
	    	String txtSearch = null;
	    	String strDDOCode = null;
	    	String lFlag = null;
	    	List lstEmpForFileGenerate=null;
	    	
	    	try{
	    		setSessionInfo(objectArgs);
				DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
				lFlag = StringUtility.getParameter("flag", request);
				
				if (lFlag != null && lFlag.equals("sevarthId")) {
					gLogger.info("Search Value is ***" + txtSearch);
					txtSearch = StringUtility.getParameter("searchTxt", request);
				}else {
					txtSearch = "";
				}
				strDDOCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
				gLogger.info("logged in ddo code for employee File generate report: " + strDDOCode);

				lstEmpForFileGenerate = lObjFormS1UpdateDAO.getEmpListForFileGenerate(strDDOCode, txtSearch,lFlag);
				gLogger.info("lstEmpForFileGenerate is ***" + lstEmpForFileGenerate.size());
				objectArgs.put("empList", lstEmpForFileGenerate);

				objectArgs.put("DDOCode", strDDOCode);
				// inputMap.put("empDesigList", empDesigList);
				resultObject.setResultValue(objectArgs);
	    		PayBillDAOImpl lObjBillDAO = new PayBillDAOImpl(null,serv.getSessionFactory());    
	    		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	    		String locId=loginDetailsMap.get("locationId").toString();
	    		List empDDONameDesg  = lObjFormS1UpdateDAO.getDDOOfcAddDesgName(locId); 
	    		Object[] obj1 = (Object[])empDDONameDesg.get(0);
	    		 ReportExportHelper objExport = new ReportExportHelper();
				 	List columnvalue = new ArrayList();
					List mainValue=new ArrayList();
					Map output = new HashMap();
					String lSBOut = "";
					String exportTo=DBConstants.DIS_EXCELFILE;
					String lineSeperator = System.getProperty("line.separator");
				 	List<Object> lLstInnerList = new ArrayList<Object>();
					List<Object> lLstArrOuter = new ArrayList<Object>();
					Object Obj[];
					if (lstEmpForFileGenerate != null && !lstEmpForFileGenerate.isEmpty()) {
						Iterator it = lstEmpForFileGenerate.iterator();
						int i=1;
						while (it.hasNext()) {
							Obj = (Object[]) it.next();
							lLstInnerList = new ArrayList<Object>();
							
							lLstInnerList.add(i++);//SID
							
							lLstInnerList.add(Obj[0]!=null ? Obj[0] : "-");//SevarthID
							 
							lLstInnerList.add(Obj[1]!=null ? Obj[1] : "-");//Employee Name
							
							lLstInnerList.add(Obj[2]!=null ? Obj[2] : "-");//Date of Joining
							
							lLstInnerList.add(Obj[3]!=null ? Obj[3] : "-");//Designation Name
							
							lLstInnerList.add(Obj[4]!=null ? Obj[4] : "-");//DDO Code Level1
							lLstInnerList.add(Obj[14]!=null ? Obj[14] : "-");//DTO Reg No
							lLstInnerList.add(Obj[15]!=null ? Obj[15] : "-");//DDO  Reg No
							lLstInnerList.add(Obj[5]!=null ? Obj[5] : "-");//Office Name
							lLstInnerList.add(Obj[6]!=null ? Obj[6] : "-");//DCPS ID
							
							if(!Obj[7].toString().trim().isEmpty() && !Obj[8].toString().trim().isEmpty()) {
							lLstInnerList.add("Text file Created" );//File Creation
							} else  {
								lLstInnerList.add("Text file not Created" );//File Creation
							}
							if(Obj[9].toString().trim().isEmpty() && !Obj[7].toString().trim().isEmpty() && !Obj[8].toString().trim().isEmpty()) {
								lLstInnerList.add("Validate" );//Verification status
								} else if(!Obj[9].toString().trim().isEmpty()) {
									lLstInnerList.add("verified" );//Verification status
								} else {
									lLstInnerList.add("Not verified" );//Verification status
									
								}
							//File upload status
							if(Obj[10].toString().trim().isEmpty() && !Obj[9].toString().trim().isEmpty()) {
								lLstInnerList.add("Upload File" );
								} else if(Obj[10].toString().trim().isEmpty() && Obj[9].toString().trim().isEmpty()) {
									lLstInnerList.add("File not uploaded" );
								} else {
									lLstInnerList.add(Obj[10].toString() ); 
									
								}
							//NSDL STATUS
							if(!Obj[11].toString().trim().isEmpty() ) {
								lLstInnerList.add(Obj[11].toString() ); 
								}  else {
									lLstInnerList.add("-"); 
									
								} 
							if(!Obj[12].toString().trim().isEmpty() ) {
								lLstInnerList.add(Obj[12].toString() ); 
								}  else {
									lLstInnerList.add("-"); 
									
								}
							lLstArrOuter.add(lLstInnerList);
						}
					}
					ColumnVo[] excelBillReportHeading=new ColumnVo[20];
					  excelBillReportHeading[0]=new ColumnVo("Sr. No", 2, 5, 0,false,true); 
			          excelBillReportHeading[1]=new ColumnVo("Sevaarth Id", 2, 13, 0,false,true); 
			          excelBillReportHeading[2]=new ColumnVo("Employee Name",2,13,0,true,true);
			          excelBillReportHeading[3]=new ColumnVo("Date of Joining",2,10,0,true,true);
			          excelBillReportHeading[4]=new ColumnVo("Designation Name",2,30,0,true,true);
			          excelBillReportHeading[5]=new ColumnVo("DDO Code",2,15,0,true,true);
			          excelBillReportHeading[6]=new ColumnVo("DTO Reg No",2,8,0,true,true);
			          excelBillReportHeading[7]=new ColumnVo("DDO Reg No",2,9,0,true,true);
			          excelBillReportHeading[8]=new ColumnVo("Office Name",2,40,0,true,true);
			          excelBillReportHeading[9]=new ColumnVo("DCPS ID",2,25,0,true,true);
			          excelBillReportHeading[10]=new ColumnVo("Date of Joining",2,11,0,true,true);
			          excelBillReportHeading[11]=new ColumnVo("File Creation",2,10,0,true,true);
			          excelBillReportHeading[12]=new ColumnVo("File Verification",2,16,0,true,true);
			          excelBillReportHeading[13]=new ColumnVo("File upload status",2,10,0,true,true);
			          excelBillReportHeading[14]=new ColumnVo("NSDL STATUS",2,10,0,true,true);
			          excelBillReportHeading[15]=new ColumnVo("PRAN NO",2,10,0,true,true);
			          excelBillReportHeading[16]=new ColumnVo("NSDL STATUS",2,10,0,true,true);
			          mainValue.add(lLstArrOuter);
			     	 
			          StringBuffer lSbHeader= new StringBuffer();
			          lSbHeader.append("Table showing details of Employees");
			          lSbHeader.append("\n");
			          lSbHeader.append("of\n");
			          if(obj1[2]!= null)
			        	  lSbHeader.append(obj1[2].toString()+",\n");
				  		  else
				  			lSbHeader.append("N.A,\n");
			          if(obj1[0]!= null)
			          lSbHeader.append(obj1[0].toString()+",\n");
			          else lSbHeader.append("N.A."+",\n");
			          if(obj1[4]!= null && !obj1[4].toString().equals("Not Applicable")  )
			        	  lSbHeader.append("Taluka : "+obj1[4].toString()+",\n");
				  	  if(obj1[5]!= null && !obj1[5].toString().equals("Not Applicable"))
				  		lSbHeader.append("District : "+obj1[5].toString()+",\n");
				  	if(obj1[6]!= null)
				  	  lSbHeader = lSbHeader.append(obj1[6].toString());
				  	 gLogger.info("lSbHeader "+lSbHeader.toString());
			          String lStrFooter= "";
			          int nMode= 131;
			          
			          ExcelExportHelper exph = new ExcelExportHelper();
			          byte[] aryOut = null;
			          String[] param = new String[1];
			  		  List Headerdata = new ArrayList();
			  		  List footerdata = new ArrayList();
			  		  param[0] = "";

			  		  Headerdata.add(lSbHeader.toString());
			  		  StringBuffer footer =new StringBuffer();
			  		  footer = footer.append("This is to certify that the information of employees of ");
			  		  if(obj1[2]!= null)
			  		  footer = footer.append(obj1[2].toString());
			  		  else
			  			  footer = footer.append("N.A");
			  		  footer = footer.append(" entered in the system and shown above is correct and accurate as per the records of this Institution. I understand that I am responsible for the accuracy and correctness of the above information");
			  		  footer = footer.append("\n");
			  		  
			  		if(obj1[3]!= null)
			  			footer = footer.append(obj1[3].toString()+",");
				  		  else
				  			  footer = footer.append("N.A");
			  		  
			  		  
			  		  footer = footer.append("\n");
			  		  
			  		if(obj1[0]!= null)
			  			footer = footer.append(obj1[0].toString()+",\n");
				  		  else
				  			  footer = footer.append("N.A");
			  		  
			  		  
			  		  if(obj1[4]!= null && !obj1[4].toString().equals("Not Applicable"))
			  		  footer = footer.append("Taluka : "+obj1[4].toString()+",\n");
			  		  if(obj1[5]!= null && !obj1[5].toString().equals("Not Applicable"))
			  		  footer = footer.append("District : "+obj1[5].toString()+",\n");
			  		  footer = footer.append(obj1[6].toString());
			  		  //footerdata.add("This is to certify that the information of employees of "+obj[2].toString()+" (here name of institution should be fetched from Institution details) entered in the system and shown above is correct and accurate as per the records of this Institution. I understand that I am responsible for the accuracy and correctness of the above information");
			  		  footerdata.add(footer.toString());
			  		  
			  		  aryOut = exph.getExcelReportPrintFormat(mainValue, columnvalue, param, Headerdata, footerdata);
			  		String lStrExportTo = DBConstants.DIS_EXCELFILE;
			  		Map lDetailMap = new HashMap();
					if ((DBConstants.DIS_ONSCREEN).equals(lStrExportTo)) {
						lDetailMap.put(DBConstants.DIS_ONSCREEN, aryOut);
					} else if ((DBConstants.DIS_EXCELFILE).equals(lStrExportTo)) {
						lDetailMap.put(DBConstants.DIS_EXCELFILE, aryOut);
					}
					objectArgs.put("FileName", "Employee Statistics");
					objExport.getExportData(resultObject, lStrExportTo, objectArgs, lDetailMap, false);
					resultObject.setResultValue(objectArgs);
					
			          
	    	}
	    	catch(Exception e){
	    		resultObject = new ResultObject(ErrorConstants.ERROR);
	    		resultObject.setResultCode(-1);
				resultObject.setViewName("errorPage");
				gLogger.info(e);
	    	}
	    	return resultObject;
	    }
	    
	    
}
}
