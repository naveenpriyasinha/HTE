package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.constant.DBConstants;

import com.tcs.sgv.common.helper.ColumnVo;
import com.tcs.sgv.common.helper.ExcelExportHelper;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.util.query.DateUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpStatisticsDDOwiseDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDdoOffice.dao.ZpDDOOfficeMstDAOImpl;
import com.tcs.sgv.eis.zp.zpDdoOffice.valueobject.ZpRltDdoMap;

public class BillStatisticsServiceImpl extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());	
    ResourceBundle constantsBundle = ResourceBundle.getBundle("../resources/Payroll");
    int msg;
    public ResultObject loadBillStatisticsData(Map objectArgs)
    {
    	logger.info("Inside Get Bill Statistics");
    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    	msg = 0;
    	try{
    		
    		Map loginMap = (Map) objectArgs.get("baseLoginMap");
    		
    		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
    		logger.info("locId in BillStatisticsServiceImpl is: "+locId);
    		
    		PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
    		
    		long billGroupIdPassed = 0;
    		
    		if(StringUtility.getParameter("billNoPassed",request)!=null)
    			billGroupIdPassed = (StringUtility.getParameter("billGroupIdPassed",request)!=null&&!(StringUtility.getParameter("billGroupIdPassed",request).equals(""))?Long.parseLong(StringUtility.getParameter("billGroupIdPassed",request)):0);
			
    		logger.info("Bill Group Id Passed is -->"+billGroupIdPassed);
    		if(billGroupIdPassed != 0){    			
    			Long lPsrPostCount = paybillHeadMpgDAOImpl.getUserPostCountForBill(billGroupIdPassed, "psrPostCount");
    			logger.info("lPsrPostCount"+lPsrPostCount);
    			objectArgs.put("lPsrPostCount", lPsrPostCount);
    			Long lBillPostCount = paybillHeadMpgDAOImpl.getUserPostCountForBill(billGroupIdPassed, "billPostCount");
    			logger.info("lBillPostCount"+lBillPostCount);
    			objectArgs.put("lBillPostCount", lBillPostCount);
    			Long lUserPostCount = paybillHeadMpgDAOImpl.getUserPostCountForBill(billGroupIdPassed, "userCount");
    			logger.info("lUserPostCount"+lUserPostCount);
    			objectArgs.put("lUserPostCount", lUserPostCount);
    			Long lBillUserCount = paybillHeadMpgDAOImpl.getUserPostCountForBill(billGroupIdPassed, "billUserCount");
    			logger.info("lBillUserCount"+lBillUserCount);
    			objectArgs.put("lBillUserCount", lBillUserCount);
    			Long lPostEndCount = paybillHeadMpgDAOImpl.getUserPostCountForBill(billGroupIdPassed, "postEndCount");
    			logger.info("lPostEndCount"+lPostEndCount);
    			objectArgs.put("lPostEndCount", lPostEndCount);
    			Long lUserServEndCount = paybillHeadMpgDAOImpl.getUserPostCountForBill(billGroupIdPassed, "userServEndCount");
    			logger.info("lUserServEndCount"+lUserServEndCount);
    			objectArgs.put("lUserServEndCount", lUserServEndCount);
    			
    			List lPostCountList = paybillHeadMpgDAOImpl.getDesigwisePostListMst(billGroupIdPassed);
    			objectArgs.put("lPostCountList", lPostCountList);
    			objectArgs.put("totalRecordsMst", lPostCountList.size());
    			
    			List lBillPostCountList = paybillHeadMpgDAOImpl.getDesigwisePostListInBill(billGroupIdPassed);
    			objectArgs.put("lBillPostCountList", lBillPostCountList);
    			objectArgs.put("totalRecordsBill", lBillPostCountList.size());
    			
    		}
    		
    		List listBillNos = paybillHeadMpgDAOImpl.getGeneratedBillList(locId);
    		if(listBillNos != null)
    			logger.info("Size of the List Fetched is -->"+listBillNos.size());
    		
    		objectArgs.put("billGroupIdPassed", billGroupIdPassed);
    		objectArgs.put("billNoList", listBillNos);
    		objectArgs.put("totalRecords", listBillNos.size());    		
    		resultObject.setResultCode(ErrorConstants.SUCCESS);
    		resultObject.setResultValue(objectArgs);//put in result object
    		resultObject.setViewName("DisplayBillStatistics");//set view name
    	}
    	catch(Exception e){
    		resultObject = new ResultObject(ErrorConstants.ERROR);
    		resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.info(e);
    	}
    	return resultObject;
    }
   
    public ResultObject generateExcel(Map objectArgs)
    {
    	logger.info("Inside Get loadEmpDtlsDdoWise");
    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
     	Long lLngLocId = null;
    	OrgDdoMst lObjDdoMst = null;
    	String lStrDdocode = null;
    	String lFlag = null;
    	
    	try{
    		PayBillDAOImpl lObjBillDAO = new PayBillDAOImpl(null,serv.getSessionFactory());    
    		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
    		String locId=loginDetailsMap.get("locationId").toString();
    		//String locId="4000535";
    		
    		logger.info("locId "+locId);
    		
    		lFlag = StringUtility.getParameter("empStat",request);
    		if(!"".equals(locId)){
    			lLngLocId = Long.parseLong(locId);
    		}
    		logger.info("lLngLocId "+lLngLocId);
    		if(lFlag.equals("Y")){
		    	List<OrgDdoMst> lLstDDOList = lObjBillDAO.getDDOCodeByLoggedInlocId(lLngLocId);
		    	if(!lLstDDOList.isEmpty() && lLstDDOList != null){
		    		lObjDdoMst = lLstDDOList.get(0);
		    		lStrDdocode = lObjDdoMst.getDdoCode();
		    	}
    		}else if(lFlag.equals("N")){
    			lStrDdocode = StringUtility.getParameter("Ddocode",request);
    		}
    		if(lLngLocId.equals(380002L)) {
    			lStrDdocode = StringUtility.getParameter("Ddocode",request);
    		}
    		
    		logger.info("DDO Code is "+lStrDdocode);
    		List lLstEmpStatistics = null;
    	
    		EmpStatisticsDDOwiseDAOImpl lObjEmpStatisticsDDOwiseDAO = new EmpStatisticsDDOwiseDAOImpl(null ,serv.getSessionFactory());    	
    		lLstEmpStatistics = lObjEmpStatisticsDDOwiseDAO.getEmpStatisticsDDOwise(lStrDdocode);
    		
    		List empDDONameDesg  = lObjEmpStatisticsDDOwiseDAO.getDDOOfcAddDesgName(locId,lStrDdocode); 
    		Object[] obj1 = (Object[])empDDONameDesg.get(0);
    		logger.info("obj size.."+obj1.length);
    		logger.info("This is to certify that the information of employees of "+obj1[2].toString()+"--------------------------------------------------------------------(here name of institution should be fetched from Institution details) entered in the system and shown above is correct and accurate as per the records of this Institution. I understand that I am responsible for the accuracy and correctness of the above information");
    		logger.info("List Size..........."+lLstEmpStatistics.size());
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
				if (lLstEmpStatistics != null && !lLstEmpStatistics.isEmpty()) {
					Iterator it = lLstEmpStatistics.iterator();
					while (it.hasNext()) {
						Obj = (Object[]) it.next();
						lLstInnerList = new ArrayList<Object>();
						
						lLstInnerList.add(Obj[0]!=null ? Obj[0] : "-");//SID
						
						lLstInnerList.add(Obj[1]!=null ? Obj[1] : "-");//EName
						String date = null;
						if(Obj[15]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[15].toString().split(" ")[0]);
						}
						
						logger.info("Date.........................." + date);
						lLstInnerList.add(Obj[15]!=null ? date : "-");//DOB
						
						lLstInnerList.add(Obj[16]!=null ? Obj[16] : "-");//Employee Type
						
						lLstInnerList.add(Obj[11]!=null ? Obj[11] : "-");//Cadre
						
						lLstInnerList.add(Obj[3]!=null ? Obj[3] : "-");//Post Name
						//Post Type
						if (Obj[14] != null) {
							logger.info(Obj[14].toString());
							if(Obj[14].toString().equals("10001198130")){
								lLstInnerList.add("Temporary");	
							}
							if(Obj[14].toString().equals("10001198129")){
								lLstInnerList.add("Permanent");	
							}
							//lLstInnerList.add(Obj[14]);
						} else {
							lLstInnerList.add("-");
						}
						if(Obj[4]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[4].toString().split(" ")[0]);
						}
						lLstInnerList.add(Obj[4]!=null ? date : "-");//Post Start Date
						
						if(Obj[5]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[5].toString().split(" ")[0]);
						}
						lLstInnerList.add(Obj[5]!=null ? date : "-");//Post End Date
						
						if(Obj[6]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[6].toString().split(" ")[0]);
						}
						lLstInnerList.add(Obj[6]!=null ? date : "-");//Date of Joining
						
						if(Obj[7]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[7].toString().split(" ")[0]);
						}
						lLstInnerList.add(Obj[7]!=null ? date : "-");//Date of Service Expiry 
						
						lLstInnerList.add(Obj[12]!=null ? Obj[12] : "-");//Scale Description
						
						lLstInnerList.add(Obj[2]!=null ? Obj[2] : "-");//PF Series//Basic Salary
						
						lLstInnerList.add(Obj[9]!=null ? Obj[9] : "-");//PF Series
						
						lLstInnerList.add(Obj[10]!=null ? Obj[10] : "-"); //GPF/DCPS Account No.
						
						lLstInnerList.add(Obj[11]!=null ? Obj[11] : "-"); //GIS Group
						
						//lLstInnerList.add(Obj[8]!=null ? "" : "-");// GIS Value
												
					//	lLstInnerList.add(Obj[8]!=null ? Obj[8] : "-"); //Bill Group Name

						lLstInnerList.add(Obj[13]!=null ? (Obj[13].toString().equals("FALSE") ? "No" : "Yes"):"-");// Physically Handicapped
						lLstInnerList.add(Obj[18]!=null ? Obj[18] : "-");
						lLstInnerList.add(Obj[19]!=null ? Obj[20] : "-");
						lLstInnerList.add(Obj[20]!=null ? Obj[19] : "-");
						lLstInnerList.add(Obj[23]!=null ? Obj[23] : "-");
						
						lLstArrOuter.add(lLstInnerList);
					}
				}
				ColumnVo[] excelBillReportHeading=new ColumnVo[21];
		          excelBillReportHeading[0]=new ColumnVo("Sevaarth Id", 2, 13, 0,false,true); 
		          excelBillReportHeading[1]=new ColumnVo("Employee Name",2,13,0,true,true);
		          excelBillReportHeading[2]=new ColumnVo("Date of Birth",2,13,0,true,true);
		          excelBillReportHeading[3]=new ColumnVo("Employee Type",2,13,0,true,true);
		          excelBillReportHeading[4]=new ColumnVo("Cadre",2,8,0,true,true);
		          excelBillReportHeading[5]=new ColumnVo("Post Name",2,9,0,true,true);
		          excelBillReportHeading[6]=new ColumnVo("Post Type",2,9,0,true,true);
		          excelBillReportHeading[7]=new ColumnVo("Post Start Date",2,9,0,true,true);
		          excelBillReportHeading[8]=new ColumnVo("Post End Date",2,9,0,true,true);
		          excelBillReportHeading[9]=new ColumnVo("Date of Joining",2,11,0,true,true);
		          excelBillReportHeading[10]=new ColumnVo("Date of Service Expiry",2,10,0,true,true);
		          excelBillReportHeading[11]=new ColumnVo("Scale Description",2,16,0,true,true);
		          excelBillReportHeading[12]=new ColumnVo("Basic Salary",2,10,0,true,true);
		          excelBillReportHeading[13]=new ColumnVo("PF Series",2,9,0,true,true);
		          excelBillReportHeading[14]=new ColumnVo("GPF/DCPS Account No.",2,13,0,true,true);
		          excelBillReportHeading[15]=new ColumnVo("GIS Group",2,9,0,true,true);
		        //  excelBillReportHeading[16]=new ColumnVo("GIS Value",2,9,0,true,true);
		         // excelBillReportHeading[16]=new ColumnVo("Bill Group Name",2,10,0,true,true);
		          excelBillReportHeading[16]=new ColumnVo("Physically Handicapped",2,17,0,true,true);
		          excelBillReportHeading[17]=new ColumnVo("Bank Name",2,17,0,true,true);
		          excelBillReportHeading[18]=new ColumnVo("Branch Name",2,17,0,true,true);
		          excelBillReportHeading[19]=new ColumnVo("Account Number",2,17,0,true,true);
		          excelBillReportHeading[20]=new ColumnVo("Pran No",2,17,0,true,true);
		         /* excelBillReportHeading[5]=new ColumnVo("Net Amount",2,4,0,true,true); 
		          excelBillReportHeading[6]=new ColumnVo("Emp Count",2,4,0,true,true);*/
		          
		          columnvalue.add(excelBillReportHeading);
		          mainValue.add(lLstArrOuter);
		     	/* logger.info("hello"+lSBOut.toString());   
		          logger.info("hello"+exportTo);  */ 
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
			  	  logger.info("lSbHeader "+lSbHeader.toString());
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
			logger.info(e);
    	}
    	return resultObject;
    }
    
    
    public ResultObject loadEmpDtlsDdoWise(Map objectArgs)
    {
    	logger.info("Inside Get loadEmpDtlsDdoWise");
    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    	Long lLngLocId = null;
    	OrgDdoMst lObjDdoMst = null;
    	String lStrDdocode = null;
    	String lFlag = null;
    	try{
    		PayBillDAOImpl lObjBillDAO = new PayBillDAOImpl(null,serv.getSessionFactory());    		
    		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
    		String locId=loginDetailsMap.get("locationId").toString();
    		
    		lFlag = StringUtility.getParameter("empStat",request);
    		if(!"".equals(locId)){
    			lLngLocId = Long.parseLong(locId);
    		}
    		if(lFlag.equals("Y")){
		    	List<OrgDdoMst> lLstDDOList = lObjBillDAO.getDDOCodeByLoggedInlocId(lLngLocId);
		    	if(!lLstDDOList.isEmpty() && lLstDDOList != null){
		    		lObjDdoMst = lLstDDOList.get(0);
		    		lStrDdocode = lObjDdoMst.getDdoCode();
		    	}
    		}else if(lFlag.equals("N")){
    			lStrDdocode = StringUtility.getParameter("Ddocode",request);
    		}
    		
    		logger.info("DDO Code is "+lStrDdocode);
    		List lLstEmpStatistics = null;
    	
    		EmpStatisticsDDOwiseDAOImpl lObjEmpStatisticsDDOwiseDAO = new EmpStatisticsDDOwiseDAOImpl(null ,serv.getSessionFactory());    	
    		lLstEmpStatistics = lObjEmpStatisticsDDOwiseDAO.getEmpStatisticsDDOwise(lStrDdocode);
    	
	    	objectArgs.put("EmpStatistics", lLstEmpStatistics);
	    	objectArgs.put("Ddocode", lStrDdocode);
	    	objectArgs.put("lFlag", lFlag);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);//put in result object
			resultObject.setViewName("EmpStatistics");//set view name
			
    	}catch(Exception e){
    		resultObject = new ResultObject(ErrorConstants.ERROR);
    		resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error in loadEmpDtlsDdoWise "+ e);
    	}
    	return resultObject;
    }
    public ResultObject getDdoCodeForAutoComplete(Map<String, Object> inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		List finalList = null;
		String lStrEmpName = null;
		try {
	    	ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
	    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
	    	
			lStrEmpName = StringUtility.getParameter("searchKey", request).trim();
			EmpStatisticsDDOwiseDAOImpl lObjEmpStatisticsDDOwiseDAO = new EmpStatisticsDDOwiseDAOImpl(null ,serv.getSessionFactory());    	
			finalList = lObjEmpStatisticsDDOwiseDAO.getDdoCodeForAutoComplete(lStrEmpName);
    		
			String lStrTempResult = null;
			if (!finalList.isEmpty()) {
				lStrTempResult = new AjaxXmlBuilder().addItems(finalList, "desc", "id", true).toString();
			}
			inputMap.put("ajaxKey", lStrTempResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");

		} catch (Exception e) {
			IFMSCommonServiceImpl.setErrorProperties(logger, resObj, e, "Error is : ");
		}

		return resObj;

	}
    
    //Added by Demolisher
    public ResultObject generateDDOExcel(Map objectArgs)
    {
    	logger.info("Inside Get generateDDOExcel");
    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
     	Long lLngLocId = null;
    	OrgDdoMst lObjDdoMst = null;
    	String lStrDdocode = null;
    	String lFlag = null;
    	
    	try{
    		PayBillDAOImpl lObjBillDAO = new PayBillDAOImpl(null,serv.getSessionFactory());    
    		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
    		String locId=loginDetailsMap.get("locationId").toString();
    		
    		 //Added by Demolisher
    		Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			logger.info("The post ID is :"+gLngPostId);

			logger.info("locId "+locId);
    		
    		/*lFlag = StringUtility.getParameter("empStat",request);
    		if(!"".equals(locId)){
    			lLngLocId = Long.parseLong(locId);
    		}
    		if(lFlag.equals("Y")){
		    	List<OrgDdoMst> lLstDDOList = lObjBillDAO.getDDOCodeByLoggedInlocId(lLngLocId);
		    	if(!lLstDDOList.isEmpty() && lLstDDOList != null){
		    		lObjDdoMst = lLstDDOList.get(0);
		    		lStrDdocode = lObjDdoMst.getDdoCode();
		    	}
    		}else if(lFlag.equals("N")){
    			lStrDdocode = StringUtility.getParameter("Ddocode",request);
    		}*/
    		
    		logger.info("DDO Code is "+lStrDdocode);
    		List lLstEmpStatistics = null;
    	
    		/*EmpStatisticsDDOwiseDAOImpl lObjEmpStatisticsDDOwiseDAO = new EmpStatisticsDDOwiseDAOImpl(null ,serv.getSessionFactory());    	
    		lLstEmpStatistics = lObjEmpStatisticsDDOwiseDAO.getEmpStatisticsDDOwise(lStrDdocode);*/
    		
    		String flg = StringUtility.getParameter("flag",request);
    		int flag = Integer.parseInt(flg);
    		logger.info("Flag :::::::::::"+flag);
    		ZpDDOOfficeMstDAOImpl  zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(ZpAdminOfficeMst.class,serv.getSessionFactory());
    		List<ZpRltDdoMap> zpDDOOfficelst;
    		
    		if(flag!=1)
    		{	
    			zpDDOOfficelst = zpDDOOfficeMstDAO.viewAllDDOApproveOfficeDtlsData(gLngPostId);
    		}
    		else
    		{
    			zpDDOOfficelst = zpDDOOfficeMstDAO.viewAllDDORejectOfficeDtlsData(gLngPostId);
    		}
    		//List empDDONameDesg  = lObjEmpStatisticsDDOwiseDAO.getDDOOfcAddDesgName(locId); 
    		//Object[] obj1 = (Object[])empDDONameDesg.get(0);
    		//logger.info("obj size.."+obj1.length);
    		//logger.info("This is to certify that the information of employees of "+obj1[2].toString()+"--------------------------------------------------------------------(here name of institution should be fetched from Institution details) entered in the system and shown above is correct and accurate as per the records of this Institution. I understand that I am responsible for the accuracy and correctness of the above information");
    		logger.info("List Size..........."+zpDDOOfficelst.size());
    		 ReportExportHelper objExport = new ReportExportHelper();
			 	List columnvalue = new ArrayList();
				List mainValue=new ArrayList();
				Map output = new HashMap();
				String lSBOut = "";
				String exportTo=DBConstants.DIS_EXCELFILE;
				String lineSeperator = System.getProperty("line.separator");
				logger.info("----------------------Over Here---------------------------");
			 	List<Object> lLstInnerList = new ArrayList<Object>();
				List<Object> lLstArrOuter = new ArrayList<Object>();
				Object Obj[];
				if (zpDDOOfficelst != null && !zpDDOOfficelst.isEmpty()) {
					Iterator it = zpDDOOfficelst.iterator();
					while (it.hasNext()) {
						Obj = (Object[]) it.next();
						lLstInnerList = new ArrayList<Object>();
						String ddooffice = zpDDOOfficeMstDAO.getDDOOfficelvlDtlsTestDataExcel(Obj[5].toString());
						logger.info("DDO Office ::::::::::::::::"+ddooffice);
						logger.info(Obj[5].toString()+ddooffice);
						
						String ddolvloffice = zpDDOOfficeMstDAO.getDDOOfficelvlDtlsTestDataExcel(Obj[6].toString());
						logger.info("DDO Office ::::::::::::::::"+ddolvloffice);
						logger.info(Obj[6].toString()+ddolvloffice);
						
						
						
						lLstInnerList.add((Obj[5].toString()+ddooffice)!=null ? (Obj[5].toString()+ddooffice) : "-");//SID
						logger.info("----------------------Over Here---------------------------");
						
						lLstInnerList.add((Obj[6].toString()+ddolvloffice)!=null ? (Obj[6].toString()+ddolvloffice) : "-");//EName
						/*String date = null;
						if(Obj[15]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[15].toString().split(" ")[0]);
						}
						
						logger.info("Date.........................." + date);
						lLstInnerList.add(Obj[15]!=null ? date : "-");//DOB
*/						
						lLstInnerList.add(Obj[7]!=null ? Obj[7] : "-");//Employee Type
						logger.info("----------------------Over Here---------------------------");
						lLstInnerList.add(Obj[8]!=null ? Obj[8] : "-");//Cadre
						logger.info("----------------------Over Here---------------------------");
						lLstInnerList.add(Obj[16]!=null ? Obj[16] : "-");//Post Name
						
						/*//Post Type
						if (Obj[14] != null) {
							logger.info(Obj[14].toString());
							if(Obj[14].toString().equals("10001198130")){
								lLstInnerList.add("Temporary");	
							}
							if(Obj[14].toString().equals("10001198129")){
								lLstInnerList.add("Permanent");	
							}
							//lLstInnerList.add(Obj[14]);
						} else {
							lLstInnerList.add("-");
						}
						if(Obj[4]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[4].toString().split(" ")[0]);
						}
						lLstInnerList.add(Obj[4]!=null ? date : "-");//Post Start Date
						
						if(Obj[5]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[5].toString().split(" ")[0]);
						}
						lLstInnerList.add(Obj[5]!=null ? date : "-");//Post End Date
						
						if(Obj[6]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[6].toString().split(" ")[0]);
						}
						lLstInnerList.add(Obj[6]!=null ? date : "-");//Date of Joining
						
						if(Obj[7]!=null){
							date = new DateUtility().convertToDDMMYYYY(Obj[7].toString().split(" ")[0]);
						}
						lLstInnerList.add(Obj[7]!=null ? date : "-");//Date of Service Expiry 
						
						lLstInnerList.add(Obj[12]!=null ? Obj[12] : "-");//Scale Description
						
						lLstInnerList.add(Obj[2]!=null ? Obj[2] : "-");//PF Series//Basic Salary
						
						lLstInnerList.add(Obj[9]!=null ? Obj[9] : "-");//PF Series
						
						lLstInnerList.add(Obj[10]!=null ? Obj[10] : "-"); //GPF/DCPS Account No.
						
						lLstInnerList.add(Obj[11]!=null ? Obj[11] : "-"); //GIS Group
						
						//lLstInnerList.add(Obj[8]!=null ? "" : "-");// GIS Value
												
					//	lLstInnerList.add(Obj[8]!=null ? Obj[8] : "-"); //Bill Group Name

						lLstInnerList.add(Obj[13]!=null ? (Obj[13].toString().equals("FALSE") ? "No" : "Yes"):"-");// Physically Handicapped
						lLstInnerList.add(Obj[18]!=null ? Obj[18] : "-");
						lLstInnerList.add(Obj[19]!=null ? Obj[20] : "-");
						lLstInnerList.add(Obj[20]!=null ? Obj[19] : "-");*/
						logger.info("----------------------Over Here---------------------------");
						lLstArrOuter.add(lLstInnerList);
						logger.info("----------------------Over Here---------------------------");
					}
				}
				ColumnVo[] excelBillReportHeading=new ColumnVo[5];
		          /*excelBillReportHeading[0]=new ColumnVo("Sevaarth Id", 2, 13, 0,false,true); 
		          excelBillReportHeading[1]=new ColumnVo("Employee Name",2,13,0,true,true);
		          excelBillReportHeading[2]=new ColumnVo("Date of Birth",2,13,0,true,true);
		          excelBillReportHeading[3]=new ColumnVo("Employee Type",2,13,0,true,true);
		          excelBillReportHeading[4]=new ColumnVo("Cadre",2,8,0,true,true);*/
		          excelBillReportHeading[0]=new ColumnVo("DDO Code",2,9,0,true,true);
		          excelBillReportHeading[1]=new ColumnVo("Level 2 DDO Code",2,9,0,true,true);
		          excelBillReportHeading[2]=new ColumnVo("Level 3 DDO Code",2,9,0,true,true);
		          excelBillReportHeading[3]=new ColumnVo("Level 4 DDO Code",2,9,0,true,true);
		         /* excelBillReportHeading[9]=new ColumnVo("Date of Joining",2,11,0,true,true);
		          excelBillReportHeading[10]=new ColumnVo("Date of Service Expiry",2,10,0,true,true);
		          excelBillReportHeading[11]=new ColumnVo("Scale Description",2,16,0,true,true);
		          excelBillReportHeading[12]=new ColumnVo("Basic Salary",2,10,0,true,true);
		          excelBillReportHeading[13]=new ColumnVo("PF Series",2,9,0,true,true);
		          excelBillReportHeading[14]=new ColumnVo("GPF/DCPS Account No.",2,13,0,true,true);
		          excelBillReportHeading[15]=new ColumnVo("GIS Group",2,9,0,true,true);
*/		          excelBillReportHeading[4]=new ColumnVo("Hierarchy",2,9,0,true,true);
		         // excelBillReportHeading[16]=new ColumnVo("Bill Group Name",2,10,0,true,true);
		         /* excelBillReportHeading[16]=new ColumnVo("Physically Handicapped",2,17,0,true,true);
		          excelBillReportHeading[17]=new ColumnVo("Bank Name",2,17,0,true,true);
		          excelBillReportHeading[18]=new ColumnVo("Branch Name",2,17,0,true,true);
		          excelBillReportHeading[19]=new ColumnVo("Account Number",2,17,0,true,true);*/
		         /* excelBillReportHeading[5]=new ColumnVo("Net Amount",2,4,0,true,true); 
		          excelBillReportHeading[6]=new ColumnVo("Emp Count",2,4,0,true,true);*/
		          
		          columnvalue.add(excelBillReportHeading);
		          mainValue.add(lLstArrOuter);
		     	/* logger.info("hello"+lSBOut.toString());   
		          logger.info("hello"+exportTo);  */ 
		          StringBuffer lSbHeader= new StringBuffer();
		          lSbHeader.append("Table showing details of Approved Employees");
		         /* lSbHeader.append("\n");
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
			  	  lSbHeader = lSbHeader.append(obj1[6].toString());*/
			  	  logger.info("lSbHeader "+lSbHeader.toString());
		          String lStrFooter= "";
		          int nMode= 131;
		          
		          ExcelExportHelper exph = new ExcelExportHelper();
		          byte[] aryOut = null;
		          String[] param = new String[1];
		  		  List Headerdata = new ArrayList();
		  		  List footerdata = new ArrayList();
		  		  param[0] = "";

		  		  Headerdata.add(lSbHeader.toString());
		  		  
		  		  /*StringBuffer footer =new StringBuffer();
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
		  		  footerdata.add(footer.toString());*/
		  		  aryOut = exph.getExcelReportPrintFormat(mainValue, columnvalue, param, Headerdata, footerdata);
		  		String lStrExportTo = DBConstants.DIS_EXCELFILE;
		  		Map lDetailMap = new HashMap();
				if ((DBConstants.DIS_ONSCREEN).equals(lStrExportTo)) {
					lDetailMap.put(DBConstants.DIS_ONSCREEN, aryOut);
				} else if ((DBConstants.DIS_EXCELFILE).equals(lStrExportTo)) {
					lDetailMap.put(DBConstants.DIS_EXCELFILE, aryOut);
				}
				objectArgs.put("FileName", "Approve_DDO_Office_Details");
				objExport.getExportData(resultObject, lStrExportTo, objectArgs, lDetailMap, false);
				resultObject.setResultValue(objectArgs);
				
		          
    	}
    	catch(Exception e){
    		resultObject = new ResultObject(ErrorConstants.ERROR);
    		resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.info(e);
    	}
    	return resultObject;
    }
    

}
