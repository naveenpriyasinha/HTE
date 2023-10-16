package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

public class EmployeeValidationServiceImpl extends ServiceImpl {

	private static Logger logger = Logger.getLogger(EmployeeValidationServiceImpl.class);

	// file added by vaibhav tyagi
	public ResultObject employeeValidationReport(Map objectArgs) throws Exception// gayathri
	{
		logger.info("Entering into  employeeValidationReport of EmployeeValidationServiceImpl");
		HttpServletRequest requests = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		EmployeeValidationDAO employeeValidationDAO = new EmployeeValidationDAOImpl(EmployeeValidationDAOImpl.class,
				serviceLocator.getSessionFactory());

		List noOfEmpConfig = null;
		List noOfDraftForms = null;
		List noOfFwdForms = null;
		List noOfAppvdForms = null;
		List noOfRejectForms = null;

		try {

			noOfEmpConfig = employeeValidationDAO.getTotalEmpConfig();
			noOfDraftForms = employeeValidationDAO.getDraftForms();
			noOfFwdForms = employeeValidationDAO.getForwardedForms();
			noOfAppvdForms = employeeValidationDAO.getApprovedForms();

			noOfRejectForms = employeeValidationDAO.getRejectedForms();
			logger.info("noOfRejectForms " + noOfRejectForms);
			objectArgs.put("noOfEmpConfig", noOfEmpConfig);
			objectArgs.put("noOfDraftForms", noOfDraftForms);
			objectArgs.put("noOfFwdForms", noOfFwdForms);
			objectArgs.put("noOfAppvdForms", noOfAppvdForms);
			objectArgs.put("noOfRejectForms", noOfRejectForms);

			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("employeeDistrictValidationReport");
		} catch (Exception e) {
			logger.info("Null Pointer Exception Ocuures..employeeValidationReport of EmployeeValidationServiceImpl");
			logger.error("Error is: " + e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");
		}
		return objRes;
	}

	public ResultObject generateExcel(Map objectArgs) {
		logger.info("Inside Get loadEmpDtlsDdoWise");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		EmployeeValidationDAO employeeValidationDAO = new EmployeeValidationDAOImpl(EmployeeValidationDAOImpl.class,
				serviceLocator.getSessionFactory());

		List noOfEmpConfig = null;
		List noOfDraftForms = null;
		List noOfFwdForms = null;
		List noOfAppvdForms = null;
		List noOfRejectForms = null;

		try {
			noOfEmpConfig = employeeValidationDAO.getTotalEmpConfig();
			noOfDraftForms = employeeValidationDAO.getDraftForms();
			noOfFwdForms = employeeValidationDAO.getForwardedForms();
			noOfAppvdForms = employeeValidationDAO.getApprovedForms();
			noOfRejectForms = employeeValidationDAO.getRejectedForms();

			ReportExportHelper objExport = new ReportExportHelper();
			List columnvalue = new ArrayList();
			List mainValue = new ArrayList();
			Map output = new HashMap();
			String lSBOut = "";
			String exportTo = DBConstants.DIS_EXCELFILE;
			String lineSeperator = System.getProperty("line.separator");
			List<Object> noOfEmpConf = new ArrayList<Object>();
			List<Object> noOfEmpConfList = new ArrayList<Object>();
			Object objTotal[];
			Object objDraft[];
			Object objForwarded[];
			Object objApproved[];
			Object objRejected[];

			Long netDraft = 0l;
			Long netFwd = 0l;
			Long netApp = 0l;
			Long netRej = 0l;

			if (noOfEmpConfig != null && !noOfEmpConfig.isEmpty()) {
				Iterator itTotal = noOfEmpConfig.iterator();
				while (itTotal.hasNext()) {
					objTotal = (Object[]) itTotal.next();
					noOfEmpConf = new ArrayList();

					noOfEmpConf.add(objTotal[1] != null ? objTotal[1] : "-");

					noOfEmpConf.add(objTotal[3] != null ? objTotal[3] : "-");

					noOfEmpConf.add(objTotal[4] != null ? objTotal[4] : "-");

					noOfEmpConf.add(objTotal[5] != null ? objTotal[5] : "-");//

					if (noOfDraftForms != null && !noOfDraftForms.isEmpty()) {
						Iterator itDraft = noOfDraftForms.iterator();
						while (itDraft.hasNext()) {
							objDraft = (Object[]) itDraft.next();

							if ((objDraft[3].equals(objTotal[6])) && (objDraft[0].equals(objTotal[0]))
									&& (objDraft[1].equals(objTotal[2]))) {
								netDraft = Long.valueOf(objDraft[2].toString());
								// noOfEmpConf.add(objDraft[2]);//Save As Draft
								break;
							}

							else {
								netDraft = 0l;
							}
						}

					}

					noOfEmpConf.add(netDraft);// Save As Draft

					if (noOfFwdForms != null && !noOfFwdForms.isEmpty()) {
						Iterator itFwd = noOfFwdForms.iterator();
						while (itFwd.hasNext()) {
							objForwarded = (Object[]) itFwd.next();
							if ((objForwarded[3].equals(objTotal[6])) && (objForwarded[0].equals(objTotal[0]))
									&& (objForwarded[1].equals(objTotal[2]))) {
								netFwd = Long.valueOf(objForwarded[2].toString());
								// noOfEmpConf.add(objForwarded[2]);//Forwarded Forms
								break;
							}

							else {
								netFwd = 0l;
							}
						}
					}

					noOfEmpConf.add(netFwd);// Forwarded Forms

					if (noOfAppvdForms != null && !noOfAppvdForms.isEmpty()) {
						Iterator itApprove = noOfAppvdForms.iterator();
						while (itApprove.hasNext()) {
							objApproved = (Object[]) itApprove.next();
							if ((objApproved[3].equals(objTotal[6])) && (objApproved[0].equals(objTotal[0]))
									&& (objApproved[1].equals(objTotal[2]))) {
								netApp = Long.valueOf(objApproved[2].toString());
								// noOfEmpConf.add(objApproved[2]);//Approved Forms
								break;
							}

							else {
								netApp = 0l;
							}
						}
					}

					noOfEmpConf.add(netApp);// Approved Forms

					if (noOfRejectForms != null && !noOfRejectForms.isEmpty()) {
						Iterator itReject = noOfRejectForms.iterator();
						while (itReject.hasNext()) {
							objRejected = (Object[]) itReject.next();
							if ((objRejected[3].equals(objTotal[6])) && (objRejected[0].equals(objTotal[0]))
									&& (objRejected[1].equals(objTotal[2]))) {
								netRej = Long.valueOf(objRejected[2].toString());
								// noOfEmpConf.add(objRejected[2]);//Rejected Forms
								break;
							}

							else {
								netRej = 0l;
							}
						}
					}

					noOfEmpConf.add(netRej);// Rejected Forms

					noOfEmpConfList.add(noOfEmpConf);
				}
			}

			ColumnVo[] excelBillReportHeading = new ColumnVo[8];
			excelBillReportHeading[0] = new ColumnVo("District Name", 2, 13, 0, false, true);
			excelBillReportHeading[1] = new ColumnVo("Type Of School", 2, 13, 0, true, true);
			excelBillReportHeading[2] = new ColumnVo("Institution Name", 2, 13, 0, true, true);
			excelBillReportHeading[3] = new ColumnVo("Total No Of Employees Configured", 2, 13, 0, true, true);
			excelBillReportHeading[4] = new ColumnVo("No Of Forms Saved As Draft", 2, 13, 0, true, true);
			excelBillReportHeading[5] = new ColumnVo("No Of Forms Forwarded To Level 2/ Level 3", 2, 13, 0, true, true);
			excelBillReportHeading[6] = new ColumnVo("No Of Forms Approved By Level 2/ Level 3", 2, 13, 0, true, true);
			excelBillReportHeading[7] = new ColumnVo("No Of Forms Rejected", 2, 3, 0, true, true);

			columnvalue.add(excelBillReportHeading);
			mainValue.add(noOfEmpConfList);

			StringBuffer lSbHeader = new StringBuffer();
			lSbHeader.append("Table showing details of Employees");
			lSbHeader.append("\n");
			lSbHeader.append("\n");

			logger.info("lSbHeader " + lSbHeader.toString());
			String lStrFooter = "";
			int nMode = 131;

			ExcelExportHelper exph = new ExcelExportHelper();
			byte[] aryOut = null;
			String[] param = new String[1];
			List Headerdata = new ArrayList();
			List footerdata = new ArrayList();
			param[0] = "";

			Headerdata.add(lSbHeader.toString());
			StringBuffer footer = new StringBuffer();
			footer = footer.append("");

			// footerdata.add("This is to certify that the information of employees of
			// "+obj[2].toString()+" (here name of institution should be fetched from
			// Institution details) entered in the system and shown above is correct and
			// accurate as per the records of this Institution. I understand that I am
			// responsible for the accuracy and correctness of the above information");
			footerdata.add(footer.toString());
			aryOut = exph.getExcelReportPrintFormat(mainValue, columnvalue, param, Headerdata, footerdata);
			String lStrExportTo = DBConstants.DIS_EXCELFILE;
			Map lDetailMap = new HashMap();
			if ((DBConstants.DIS_ONSCREEN).equals(lStrExportTo)) {
				lDetailMap.put(DBConstants.DIS_ONSCREEN, aryOut);
			} else if ((DBConstants.DIS_EXCELFILE).equals(lStrExportTo)) {
				lDetailMap.put(DBConstants.DIS_EXCELFILE, aryOut);
			}
			objectArgs.put("FileName", "Employee Validation Report");
			objExport.getExportData(resultObject, lStrExportTo, objectArgs, lDetailMap, false);
			resultObject.setResultValue(objectArgs);

		} catch (Exception e) {
			resultObject = new ResultObject(ErrorConstants.ERROR);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.info(e);
		}
		return resultObject;
	}
}