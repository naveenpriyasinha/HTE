package com.tcs.sgv.eis.service;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.util.query.DateUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.helper.ColumnVo;
import com.tcs.sgv.common.helper.ExcelExportHelper;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpStatisticsDDOwiseDAOImpl;
import com.tcs.sgv.eis.dao.ForwardEmpStatisticsDAO;
import com.tcs.sgv.eis.dao.ForwardEmpStatisticsDAOImpl;

public class ForwardEmployeeStatisticsServiceImpl extends ServiceImpl{

	private static Logger logger = Logger.getLogger( ForwardEmployeeStatisticsServiceImpl.class );


	//file added by vaibhav tyagi
	public ResultObject forwardEmpStatistics(Map objectArgs) throws Exception
	{
		logger.info("Entering into  forwardEmpStatistics of ForwardEmployeeStatisticsServiceImpl");
		HttpServletRequest requests = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		String locId=loginDetailsMap.get("locationId").toString();

		logger.info("locId "+locId);
		
		ForwardEmpStatisticsDAO forwardEmpStatisticsDAO = new ForwardEmpStatisticsDAOImpl(ForwardEmpStatisticsDAOImpl.class,serviceLocator.getSessionFactory());

		String ddoCode="";
		List noOfFwdEmpConfig=null;

		try{

			ddoCode=forwardEmpStatisticsDAO.getDDOCodeByLocId(locId);
			noOfFwdEmpConfig=forwardEmpStatisticsDAO.getFwdEmpList(ddoCode);
			
			logger.info("noOfFwdEmpConfig "+noOfFwdEmpConfig);
			objectArgs.put("noOfFwdEmpConfig",noOfFwdEmpConfig);

			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("forwardEmpStatictics");
		}
		catch(Exception e)
		{
			logger.info("Null Pointer Exception Ocuures..forwardEmpStatistics of ForwardEmployeeStatisticsServiceImpl");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");	
		}
		return objRes;
	}	

	public ResultObject generateExcel(Map objectArgs)
	{
		logger.info("Inside Get generateExcel");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ForwardEmpStatisticsDAO forwardEmpStatisticsDAO = new ForwardEmpStatisticsDAOImpl(ForwardEmpStatisticsDAOImpl.class,serviceLocator.getSessionFactory());

		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		String locId=loginDetailsMap.get("locationId").toString();

		logger.info("locId "+locId);
		List noOfFwdEmpConfig=null;

		try{
			String ddoCode="";
			ddoCode=forwardEmpStatisticsDAO.getDDOCodeByLocId(locId);
			List lLstEmpStatistics = null;
	    	
			EmpStatisticsDDOwiseDAOImpl lObjEmpStatisticsDDOwiseDAO = new EmpStatisticsDDOwiseDAOImpl(null ,serviceLocator.getSessionFactory());    	
			
			List empDDONameDesg  = lObjEmpStatisticsDDOwiseDAO.getDDOOfcAddDesgName(locId,ddoCode); 
			Object[] obj1 = (Object[])empDDONameDesg.get(0);
			logger.info("obj size.."+obj1.length);
			noOfFwdEmpConfig=forwardEmpStatisticsDAO.getFwdEmpList(ddoCode);
			
			ReportExportHelper objExport = new ReportExportHelper();
			List columnvalue = new ArrayList();
			List mainValue=new ArrayList();
			Map output = new HashMap();
			String lSBOut = "";
			String exportTo=DBConstants.DIS_EXCELFILE;
			String lineSeperator = System.getProperty("line.separator");
			List<Object> noOfFwdEmpConf = new ArrayList<Object>();
			List<Object> noOfFwdEmpConfList = new ArrayList<Object>();
			Object objFwdTotal[] = null;
			
			if (noOfFwdEmpConfig != null && !noOfFwdEmpConfig.isEmpty()) {
				Iterator itTotal = noOfFwdEmpConfig.iterator();
				while (itTotal.hasNext()) {
					objFwdTotal = (Object[]) itTotal.next();
					noOfFwdEmpConf = new ArrayList();
					
					noOfFwdEmpConf.add(objFwdTotal[0]!=null ? objFwdTotal[0] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[1]!=null ? objFwdTotal[1] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[2]!=null ? objFwdTotal[2] : "-");
					
					String dob = null;
					if(objFwdTotal[3]!=null){
						dob = new DateUtility().convertToDDMMYYYY(objFwdTotal[3].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[3]!=null ? dob : "-");

					noOfFwdEmpConf.add(objFwdTotal[4]!=null ? objFwdTotal[4] : "-");
					
					String doj = null;
					if(objFwdTotal[5]!=null){
						doj = new DateUtility().convertToDDMMYYYY(objFwdTotal[5].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[5]!=null ? doj : "-");
					
					String phy=null;
					if((objFwdTotal[6]).toString().equalsIgnoreCase("false")){
						phy="-";
					}
					else if((objFwdTotal[6]).toString().equalsIgnoreCase("true")){
						phy="Yes";
					}
					noOfFwdEmpConf.add(objFwdTotal[6]!=null ? phy : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[7]!=null ? objFwdTotal[7] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[8]!=null ? objFwdTotal[8] : "-");
					
					String superAnnAge = null;
					if(objFwdTotal[9]!=null){
						superAnnAge = new DateUtility().convertToDDMMYYYY(objFwdTotal[9].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[9]!=null ? superAnnAge : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[10]!=null ? objFwdTotal[10] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[11]!=null ? objFwdTotal[11] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[12]!=null ? objFwdTotal[12] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[13]!=null ? objFwdTotal[13] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[14]!=null ? objFwdTotal[14] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[15]!=null ? objFwdTotal[15] : "-");
					
					String postStartdate = null;
					if(objFwdTotal[16]!=null){
						postStartdate = new DateUtility().convertToDDMMYYYY(objFwdTotal[16].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[16]!=null ? postStartdate : "-");
					
					String postEndDate = null;
					if(objFwdTotal[17]!=null){
						postEndDate = new DateUtility().convertToDDMMYYYY(objFwdTotal[17].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[17]!=null ? postEndDate : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[18]!=null ? objFwdTotal[18] : "-");
					
					String DIA = null;
					if(objFwdTotal[19]!=null){
						DIA = new DateUtility().convertToDDMMYYYY(objFwdTotal[19].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[19]!=null ? DIA : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[20]!=null ? objFwdTotal[20] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[21]!=null ? objFwdTotal[21] : "-");
					
					String IAD = null;
					if(objFwdTotal[22]!=null){
						IAD = new DateUtility().convertToDDMMYYYY(objFwdTotal[22].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[22]!=null ? IAD : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[23]!=null ? objFwdTotal[23] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[24]!=null ? objFwdTotal[24] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[25]!=null ? objFwdTotal[25] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[26]!=null ? objFwdTotal[26] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[27]!=null ? objFwdTotal[27] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[28]!=null ? objFwdTotal[28] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[29]!=null ? objFwdTotal[29] : "-");
					
					String GIS = null;
					if(objFwdTotal[30]!=null){
						GIS = new DateUtility().convertToDDMMYYYY(objFwdTotal[30].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[30]!=null ? GIS : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[31]!=null ? objFwdTotal[31] : "-");
					
					String nDOB = null;
					if(objFwdTotal[32]!=null){
						nDOB = new DateUtility().convertToDDMMYYYY(objFwdTotal[32].toString().split(" ")[0]);
					}
					noOfFwdEmpConf.add(objFwdTotal[32]!=null ? nDOB : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[33]!=null ? objFwdTotal[33] : "-");
					
					noOfFwdEmpConf.add(objFwdTotal[34]!=null ? objFwdTotal[34] : "-");
					
					noOfFwdEmpConfList.add(noOfFwdEmpConf);
				}
			}
			
			ColumnVo[] excelBillReportHeading=new ColumnVo[35];
			excelBillReportHeading[0]=new ColumnVo("UID No.", 2, 13, 0,false,true); 
			excelBillReportHeading[1]=new ColumnVo("EID No.",2,13,0,true,true);
			excelBillReportHeading[2]=new ColumnVo("Employee Name",2,13,0,true,true);
			excelBillReportHeading[3]=new ColumnVo("Date Of Birth",2,13,0,true,true);
			excelBillReportHeading[4]=new ColumnVo("Gender",2,13,0,true,true);
			excelBillReportHeading[5]=new ColumnVo("Date Of Joining",2,13,0,true,true);
			excelBillReportHeading[6]=new ColumnVo("Physically Challenged",2,13,0,true,true);
			excelBillReportHeading[7]=new ColumnVo("PAN No.",2,13,0,true,true);
			excelBillReportHeading[8]=new ColumnVo("Cadre",2,13,0,true,true);
			excelBillReportHeading[9]=new ColumnVo("Super Annuation Date",2,13,0,true,true);
			excelBillReportHeading[10]=new ColumnVo("Pay Commission",2,13,0,true,true);
			excelBillReportHeading[11]=new ColumnVo("Pay Scale Description",2,13,0,true,true);
			excelBillReportHeading[12]=new ColumnVo("Pay in Pay Band",2,13,0,true,true);
			excelBillReportHeading[13]=new ColumnVo("Grade Pay",2,13,0,true,true);
			excelBillReportHeading[14]=new ColumnVo("Basic Pay",2,13,0,true,true);
			excelBillReportHeading[15]=new ColumnVo("Current Post",2,13,0,true,true);
			excelBillReportHeading[16]=new ColumnVo("Post Sanction Date",2,13,0,true,true);
			excelBillReportHeading[17]=new ColumnVo("Post End Date",2,13,0,true,true);
			excelBillReportHeading[18]=new ColumnVo("Current Institute",2,13,0,true,true);
			excelBillReportHeading[19]=new ColumnVo("Date Of Initial Appointment in Parent Institute",2,13,0,true,true);
			excelBillReportHeading[20]=new ColumnVo("Name Of Post/Designation at First Appointment",2,13,0,true,true);
			excelBillReportHeading[21]=new ColumnVo("Individual Approval Order No.",2,13,0,true,true);
			excelBillReportHeading[22]=new ColumnVo("Individual Approval Order Date",2,13,0,true,true);
			excelBillReportHeading[23]=new ColumnVo("Employee Type",2,13,0,true,true);
			excelBillReportHeading[24]=new ColumnVo("Bank Name",2,13,0,true,true);
			excelBillReportHeading[25]=new ColumnVo("Branch Name",2,13,0,true,true);
			excelBillReportHeading[26]=new ColumnVo("Account No",2,13,0,true,true);
			excelBillReportHeading[27]=new ColumnVo("IFS Code",2,13,0,true,true);
			excelBillReportHeading[28]=new ColumnVo("Pay Commission",2,13,0,true,true);
			excelBillReportHeading[29]=new ColumnVo("PF A/C No",2,13,0,true,true);
			excelBillReportHeading[30]=new ColumnVo("GIS Menbership Date",2,13,0,true,true);
			excelBillReportHeading[31]=new ColumnVo("Nominee Name",2,13,0,true,true);
			excelBillReportHeading[32]=new ColumnVo("Nominee Date Of Birth",2,13,0,true,true);
			excelBillReportHeading[33]=new ColumnVo("Nominee Relationship",2,13,0,true,true);
			excelBillReportHeading[34]=new ColumnVo("Percentage Share",2,13,0,true,true);

			columnvalue.add(excelBillReportHeading);
			mainValue.add(noOfFwdEmpConfList);

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
	  		  footer = footer.append("This is to certify that the information of forwarded employees of ");
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
			objectArgs.put("FileName", "Forwarded Employee Report");
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