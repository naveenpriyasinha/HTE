package com.tcs.sgv.eis.service;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
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
import com.tcs.sgv.eis.dao.EmployeeValidationDAO;
import com.tcs.sgv.eis.dao.EmployeeValidationDAOImpl;
import com.tcs.sgv.eis.dao.SchoolValidationReportDAO;
import com.tcs.sgv.eis.dao.SchoolValidationReportDAOImpl;

public class SchoolValidationReportServiceImpl extends ServiceImpl{

	private static Logger logger = Logger.getLogger( SchoolValidationReportServiceImpl.class );


	//file added by vaibhav tyagi
	public ResultObject schoolValidationReport(Map objectArgs) throws Exception
	{
		logger.info("Entering into  schoolValidationReport of SchoolValidationReportServiceImpl");
		HttpServletRequest requests = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		SchoolValidationReportDAO schoolValidationReportDAO = new SchoolValidationReportDAOImpl(SchoolValidationReportDAOImpl.class,serviceLocator.getSessionFactory());

		List totalSchoolsConfig=null;
		List approvedSchools=null;
		List pendingSchools=null;
		List rejectedSchools=null;
		List dataEntryInitiated=null;

		try{

			totalSchoolsConfig=schoolValidationReportDAO.getTotalSchoolsConfig();
			approvedSchools=schoolValidationReportDAO.getApprovedSchools();
			pendingSchools=schoolValidationReportDAO.getPendingSchools();
			rejectedSchools=schoolValidationReportDAO.getRejectedSchools();
			dataEntryInitiated=schoolValidationReportDAO.getDataEntryInitiatedSchools();
			
			logger.info("dataEntryInitiated "+dataEntryInitiated);
			
			objectArgs.put("totalSchoolsConfig",totalSchoolsConfig);
			objectArgs.put("approvedSchools",approvedSchools);
			objectArgs.put("pendingSchools",pendingSchools);
			objectArgs.put("rejectedSchools",rejectedSchools);
			objectArgs.put("dataEntryInitiated",dataEntryInitiated);

			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("schoolDistrictValidationReport");
		}
		catch(Exception e)
		{
			logger.info("Null Pointer Exception Ocuures..schoolValidationReport of SchoolValidationReportServiceImpl");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");	
		}
		return objRes;
	}	

	public ResultObject generateExcel(Map objectArgs)
	{
		logger.info("Inside Get schoolValidationReport generate excel");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		SchoolValidationReportDAO schoolValidationReportDAO = new SchoolValidationReportDAOImpl(SchoolValidationReportDAOImpl.class,serviceLocator.getSessionFactory());

		List totalSchoolsConfig=null;
		List approvedSchools=null;
		List pendingSchools=null;
		List rejectedSchools=null;
		List dataEntryInitiated=null;

		try{
			totalSchoolsConfig=schoolValidationReportDAO.getTotalSchoolsConfig();
			approvedSchools=schoolValidationReportDAO.getApprovedSchools();
			pendingSchools=schoolValidationReportDAO.getPendingSchools();
			rejectedSchools=schoolValidationReportDAO.getRejectedSchools();
			dataEntryInitiated=schoolValidationReportDAO.getDataEntryInitiatedSchools();
			
			logger.info("dataEntryInitiated "+dataEntryInitiated);
			
			ReportExportHelper objExport = new ReportExportHelper();
			List columnvalue = new ArrayList();
			List mainValue=new ArrayList();
			Map output = new HashMap();
			String lSBOut = "";
			String exportTo=DBConstants.DIS_EXCELFILE;
			String lineSeperator = System.getProperty("line.separator");
			List<Object> noOfSchoolsConf = new ArrayList<Object>();
			List<Object> noOfSchoolsConfList = new ArrayList<Object>();
			Object objTotal[];
			Object objApproved[];
			Object objPending[];
			Object objRejected[];
			Object objDataEntryStarted[];
			
			Long approved=0l;
			Long pending=0l;
			Long rejected=0l;
			Long dataEntryStarted=0l;
			
			if (totalSchoolsConfig != null && !totalSchoolsConfig.isEmpty()) {
				Iterator itTotal = totalSchoolsConfig.iterator();
				while (itTotal.hasNext()) {
					objTotal = (Object[]) itTotal.next();
					noOfSchoolsConf = new ArrayList();
					
					String district=null;
					if((objTotal[0]).toString().equalsIgnoreCase("-1")){
						district="No District Available";
					}
					
					else{
						district=(objTotal[1]).toString();
					}
					
					noOfSchoolsConf.add(objTotal[1]!=null ? district : "-");
				
					noOfSchoolsConf.add(objTotal[3]!=null ? objTotal[3] : "-");

					noOfSchoolsConf.add(objTotal[4]!=null ? objTotal[4] : "-");

					if (approvedSchools != null && !approvedSchools.isEmpty()) {
						Iterator itApproved = approvedSchools.iterator();
						while (itApproved.hasNext()) {
							objApproved = (Object[]) itApproved.next();
							
							if((objApproved[0].equals(objTotal[0]))&&(objApproved[1].equals(objTotal[2]))){
								approved=Long.valueOf(objApproved[2].toString());
								break;
							}
							
							else{
								approved=0l;
							}
						}
						
					}
					
					noOfSchoolsConf.add(approved);
					
					if (pendingSchools != null && !pendingSchools.isEmpty()) {
						Iterator itPending = pendingSchools.iterator();
						while (itPending.hasNext()) {
							objPending = (Object[]) itPending.next();
							
							if((objPending[0].equals(objTotal[0]))&&(objPending[1].equals(objTotal[2]))){
								pending=Long.valueOf(objPending[2].toString());
								break;
							}
							
							else{
								pending=0l;
							}
						}
						
					}
					
					noOfSchoolsConf.add(pending);
					
					if (rejectedSchools != null && !rejectedSchools.isEmpty()) {
						Iterator itRejected = rejectedSchools.iterator();
						while (itRejected.hasNext()) {
							objRejected = (Object[]) itRejected.next();
							
							if((objRejected[0].equals(objTotal[0]))&&(objRejected[1].equals(objTotal[2]))){
								rejected=Long.valueOf(objRejected[2].toString());
								break;
							}
							
							else{
								rejected=0l;
							}
						}
						
					}
					
					noOfSchoolsConf.add(rejected);
					
					if (dataEntryInitiated != null && !dataEntryInitiated.isEmpty()) {
						Iterator itDataEntryInit = dataEntryInitiated.iterator();
						while (itDataEntryInit.hasNext()) {
							objDataEntryStarted = (Object[]) itDataEntryInit.next();
							
							if((objDataEntryStarted[0].equals(objTotal[0]))&&(objDataEntryStarted[1].equals(objTotal[2]))){
								dataEntryStarted=Long.valueOf(objDataEntryStarted[2].toString());
								break;
							}
							
							else{
								dataEntryStarted=0l;
							}
						}
						
					}
					
					noOfSchoolsConf.add(dataEntryStarted);
					
					noOfSchoolsConfList.add(noOfSchoolsConf);
				}
			}
			
			ColumnVo[] excelBillReportHeading=new ColumnVo[7];
			excelBillReportHeading[0]=new ColumnVo("District Name", 2, 13, 0,false,true); 
			excelBillReportHeading[1]=new ColumnVo("Type Of School",2,13,0,true,true);
			excelBillReportHeading[2]=new ColumnVo("Total School Configured",2,13,0,true,true);
			excelBillReportHeading[3]=new ColumnVo("No. of Schools Approved",2,13,0,true,true);
			excelBillReportHeading[4]=new ColumnVo("No. of Schools Pending",2,13,0,true,true);
			excelBillReportHeading[5]=new ColumnVo("No. of Schools Rejected",2,13,0,true,true);
			excelBillReportHeading[6]=new ColumnVo("Schools with Data Entry Initiated",2,13,0,true,true);

			columnvalue.add(excelBillReportHeading);
			mainValue.add(noOfSchoolsConfList);

			StringBuffer lSbHeader= new StringBuffer();
	          lSbHeader.append("Table showing details of Schools");
	          lSbHeader.append("\n");
	          lSbHeader.append("\n");
	       
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
	  		  footer = footer.append("");
	  		
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
			objectArgs.put("FileName", "School Validation Report");
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