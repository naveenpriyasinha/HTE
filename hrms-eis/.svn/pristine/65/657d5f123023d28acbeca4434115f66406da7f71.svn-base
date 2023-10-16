package com.tcs.sgv.eis.service;

//Comment By Maruthi For import Organisation.
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.BulkAllowanceDAOImpl;
import com.tcs.sgv.eis.dao.NonGovDeducDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPayslipNonGovt;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDao;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;

@SuppressWarnings( { "unchecked", "deprecation" })
public class NonGovDeducMasterServiceImpl extends ServiceImpl
{
	private static final Log logger = LogFactory.getLog(NonGovDeducMasterServiceImpl.class);
	int msg = 0;

	public ResultObject getNonGovDeducMasterDtls(Map objectArgs)
	{
		/*long startTime=System.currentTimeMillis();
		logger.info("The start time for non-gov is------>"+startTime);
		 */
		final ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("inside getDeducData------------>");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			NonGovDeducDAOImpl nonGovDeducDAOImpl = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class, serv.getSessionFactory());
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			BulkAllowanceDAOImpl bulkAllowanceDAOImpl = new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class, serv.getSessionFactory());
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

			//Added By Kishan
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);

			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			long nonGovDeducType = Long.parseLong(resourceBundle.getString("nonGovDeducType"));
			List nonGovDeducTypes = (List) nonGovDeducDAOImpl.getNonGovDeducType(locId, nonGovDeducType);
			objectArgs.put("nonGovDeducTypes", nonGovDeducTypes);

			List dsgnList = bulkAllowanceDAOImpl.getDesignationList(langId, locId);
			objectArgs.put("dsgnList", dsgnList);

			Date curDate = new Date();
			int curYear;
			int curMonth;

			List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
			Collections.reverse(yearList);
			objectArgs.put("yearList", yearList);
			List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
			objectArgs.put("monthList", monthList);
			//Added bY roshan for CMP validation
			objectArgs.put("BILLFWDED", "NO");
			logger.info("BILLFWDED "+objectArgs.get("BILLFWDED"));
			//Ended bY roshan for CMP validation
			//FromJSP variable is used for avoiding unnecessary select query 
			if (voToService.get("FromJSP") != null && voToService.get("FromJSP").toString().equalsIgnoreCase("y"))
			{
				if (voToService.get("InsertOrUpdate") != null && voToService.get("InsertOrUpdate").toString().equalsIgnoreCase("y"))
				{
					objectArgs = updateOrInsertEmployeeData(objectArgs);
				}
				objectArgs = getEmployeeByDeducType(objectArgs);
				curYear = Integer.parseInt(voToService.get("Year").toString());
				curMonth = Integer.parseInt(voToService.get("Month").toString());
			}
			else
			{
				curYear = curDate.getYear() + 1900;
				curMonth = curDate.getMonth() + 1;
				if (voToService.get("billNo") != null)
					objectArgs.put("billNo", voToService.get("billNo").toString());

				if (voToService.get("selMonth") != null)
					curMonth = Integer.parseInt(voToService.get("selMonth").toString());

				if (voToService.get("selYear") != null)
					curYear = Integer.parseInt(voToService.get("selYear").toString());

				if (voToService.get("billNo") != null)
				{
					long billNo = StringUtility.convertToLong(voToService.get("billNo").toString());
					objectArgs.put("billNo", billNo);
				}
				if (voToService.get("deducType") != null)
				{
					objectArgs.put("deducType", voToService.get("deducType").toString());
				}
				getEmployeeByDeducType(objectArgs);

			}
			objectArgs.put("curYear", curYear);
			objectArgs.put("curMonth", curMonth);
			//Ended By Kishan

			serv = null;
			loginDetailsMap = null;
			voToService = null;
			nonGovDeducDAOImpl = null;
			adminOrgPostDtlDao = null;
			billList = null;
			resourceBundle = null;
			nonGovDeducTypes = null;
			bulkAllowanceDAOImpl = null;
			dsgnList = null;
			curDate = null;
			lookupDAO = null;
			yearList = null;
			monthList = null;

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("nonGovDeducView");
			resultObject.setResultValue(objectArgs);
		}
		catch (Exception e)
		{
			logger.info("Inside Catch.Exception occurs------>");
			logger.error("Error is: " + e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultObject;
	}

	/**
	 * Method name:-updateNonGovDataByExel() Author:- Urvin Shah. Function:-
	 * This method update the records of NonGovernment deduction from the Excel
	 * sheet.
	 * 
	 */
	/*public ResultObject updateNonGovDataByExcel(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("inside Update non Gov.Data------------>");
		long deducAmount = 0;
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			// Added by Urvin Shah
			//long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null && !cmnLocationMst.getLocationCode().trim().equals("")) ? cmnLocationMst.getLocationCode() : "";

			// End   
	//			ResourceBundle rsPayroll = ResourceBundle.getBundle("resources.Payroll");
	//			String fileName = ""; // File which contains the non-Governement deduction data.
			Map nonGovData = new HashMap();
	//			Map voToService = (Map) objectArgs.get("voToServiceMap");
			List lstNonGovAcc = new ArrayList();
			List lstEmpName = new ArrayList();
			List lstNonGovAmount = new ArrayList();
	//			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			NonGovDeducDAO nonGovDao = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class, serv.getSessionFactory());
			//fileName = request.getContextPath();
			//fileName+="d:\\hrms1\\hrms_ws\\hrms\\web\\WEB-INF\\classes\\UploadedFiles\\Non_Gov_deduc.xls";
			ArrayList attachmentList = (ArrayList) objectArgs.get("attachmentList");
			FileItem fileItem = null;
			ReadExcelFile excelData = new ReadExcelFile();
			Workbook workBook;
			List tempNonGov = new ArrayList();
			Date sysDate = new Date();
	//			List lstUpdatedData = new ArrayList();
	//			int flag = 0;
	//			int count = 0;
	//			HrPayNonGovDeduction nonGovDeduction = new HrPayNonGovDeduction();
	//			String nonGovAccNo = "";
			long deducType = (objectArgs.get("deducType").toString() != null && !objectArgs.get("deducType").toString().equals("")) ? Long.parseLong(objectArgs.get("deducType").toString()) : 0;
			logger.info("The Deduction Type is:-" + deducType);
			long month = 0;
			month = Long.parseLong(objectArgs.get("month").toString());
			long year = 0;
			year = Long.parseLong(objectArgs.get("year").toString());
			Calendar calStDate = Calendar.getInstance();
			Calendar calEndDate = Calendar.getInstance();
			calStDate.set((int) year, (int) month - 1, 1);
			calEndDate.set((int) year, (int) month - 1, 1);
			calStDate.set(Calendar.DATE, calStDate.getActualMinimum(Calendar.DATE));
			calEndDate.set(Calendar.DATE, calEndDate.getActualMaximum(Calendar.DATE));
			int cntPaybillUpdated = 0; // Counter to show No of Records under the Paybill.
			int cntPaybillNotUpdated = 0; // Counter to Show that No Of Records are not updated under the paybill.
	//			int cntUpdateNonGov = 0; // Counter to show that No. of Records updated in the hr_pay_non_gov_deduction.
			int cntNotUpdateNonGov = 0; // Counter to show that No. of Records not updated in the hr_pay_non_gov_deduction.

			List lstTempPayslipNonGov = new ArrayList();
	//			long tempTotalAmt = 0;
			List lstUpdatedRecords = null; // This list contians the records Which are updated in the hr_pay_non_gov_deduction while uploade the Excel File.
			List lstNotUpdatedRecords = new ArrayList(); // This list contians the records Which are not updated in the hr_pay_non_gov_deduction while uploade the Excel File.
			List lstUpdatedPaybillRecords = null; // This list contians the records Which are updated in the hr_pay_payslip_non_govt table while uploade the Excel File.
			List lstNotUpdatedPaybillRecords = new ArrayList(); // This list contians the records Which are not updated in the hr_pay_payslip_non_govt table while uploade the Excel File.
	//			long oldSocietyType = Long.parseLong(rsPayroll.getString("societyOld").toString());
	//			long newSocietyType = Long.parseLong(rsPayroll.getString("society").toString());
	//			long tempNGOTypeAmt = 0;
			GenericDaoHibernateImpl payslipNonGovDtlsDao = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
			payslipNonGovDtlsDao.setSessionFactory(serv.getSessionFactory());
			lstUpdatedRecords = new ArrayList();
			lstUpdatedPaybillRecords = new ArrayList();
			lstNotUpdatedPaybillRecords = new ArrayList();
			if (attachmentList != null && attachmentList.size() > 0)
			{
				logger.info("attachment list is not zero" + attachmentList.size());
				Iterator attachmentListIterator = attachmentList.listIterator();
				lstUpdatedRecords.clear();
				while (attachmentListIterator.hasNext())
				{
					fileItem = (FileItem) attachmentListIterator.next();
					logger.info("details here " + fileItem.get());
					logger.info("file name" + fileItem.getName().substring(fileItem.getName().lastIndexOf("\\") + 1, fileItem.getName().length()));
					logger.info("file name" + fileItem.getFieldName());
					workBook = Workbook.getWorkbook(fileItem.getInputStream());
					logger.info("iterating while loop.....check workbook and file iterms " + workBook.getNumberOfSheets());
					nonGovData.clear();
					nonGovData = excelData.readFileContent(workBook);
					lstNonGovAcc.clear();
					lstEmpName.clear();
					cntPaybillUpdated = 0;
					cntPaybillNotUpdated = 0;
	//					cntUpdateNonGov = 0;
					cntNotUpdateNonGov = 0;
					lstNonGovAmount.clear();

					lstNonGovAcc = (List) nonGovData.get("lstNonGovAccountNo");
					lstEmpName = (List) nonGovData.get("lstEmpName");
					lstNonGovAmount = (List) nonGovData.get("lstNonGovAmount");
					if (lstNonGovAcc != null && lstNonGovAcc.size() > 0)
					{
						logger.info("The Size of the NonGov Account is:-" + lstNonGovAcc.size());
						tempNonGov.clear();
						//tempNonGov = nonGovDao.getListForColumnByValues("nonGovDeducAccNo",lstNonGovAcc);

						for (int i = 0; i < lstNonGovAcc.size(); i++)
						{
							HrPayNonGovDeduction deduction = new HrPayNonGovDeduction();
							String accNo = (String) lstNonGovAcc.get(i).toString();
							tempNonGov = nonGovDao.getNonGovDeducDatabyAccno(accNo, deducType, locationCode);
							deducAmount = Long.parseLong(lstNonGovAmount.get(i).toString());
							logger.info("Ac No is " + accNo + " amt is" + deducAmount);
							//tempNonGov = nonGovDao.getListForColumnByValues("nonGovDeducAccNo",lstNonGovAcc);

							logger.info("The Size of the list is:-" + tempNonGov.size());

							if (tempNonGov != null && tempNonGov.size() > 0)
							{
								deduction = (HrPayNonGovDeduction) tempNonGov.get(0);
								logger.info("the Non Gov Deduc Amount is:-" + deducAmount);
								logger.info("the Non Gov Deduc Account is:-" + deduction.getNonGovDeducAccNo());
								logger.info("the Non Gov Deduc Account is:-" + deduction.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
								deduction.setNonGovDeducAmount(deducAmount);
								deduction.setUpdatedDate(sysDate);
								deduction.setNonGovDeducEfftStartDt(calStDate.getTime());
								deduction.setNonGovDeducEfftEndDt(calEndDate.getTime());
								nonGovDao.update(deduction);

								// For updating the HR_PAYSLIP_NONGOV_DTLS
								lstTempPayslipNonGov.clear();

								lstTempPayslipNonGov = nonGovDao.getPayslipNonGovtDataByEmp(deduction.getHrEisEmpMst().getOrgEmpMst().getEmpId(), month, year);
								if (lstTempPayslipNonGov != null && lstTempPayslipNonGov.size() > 0)
								{
									HrPayPayslipNonGovt hrPayPayslipNonGovt = new HrPayPayslipNonGovt();
									hrPayPayslipNonGovt = (HrPayPayslipNonGovt) lstTempPayslipNonGov.get(0);
									//									hrPayPayslipNonGovt.setUpdatedDate(sysDate);
									//									tempTotalAmt = hrPayPayslipNonGovt.getTotalNonGovtDeduc();
									if(deducType == oldSocietyType){
										//tempNGOTypeAmt = hrPayPayslipNonGovt.getSocietyOld();
										tempTotalAmt =tempTotalAmt + (deducAmount-hrPayPayslipNonGovt.getSocietyOld());
										hrPayPayslipNonGovt.setSocietyOld(deducAmount);
										hrPayPayslipNonGovt.setTotalNonGovtDeduc(tempTotalAmt);
									}
									if(deducType == newSocietyType){
										//tempNGOTypeAmt = hrPayPayslipNonGovt.getSocietyOld();
										tempTotalAmt = tempTotalAmt + (deducAmount-hrPayPayslipNonGovt.getSocietyNew());

										hrPayPayslipNonGovt.setSocietyNew(deducAmount);
										hrPayPayslipNonGovt.setTotalNonGovtDeduc(tempTotalAmt);
									}
									payslipNonGovDtlsDao.update(hrPayPayslipNonGovt);
									NonGovCustomVO nonGovCustomVO = new NonGovCustomVO();
									nonGovCustomVO.setAccNumber(accNo);
									nonGovCustomVO.setDeducAmount(deducAmount);
									nonGovCustomVO.setEmpName(lstEmpName.get(i) != null ? lstEmpName.get(i).toString() : "");
									lstUpdatedPaybillRecords.add(nonGovCustomVO);
									cntPaybillUpdated++;
								}
								else
								{ // Listing the Employee which are not updated in the HrPayPayslipNonGovt
									NonGovCustomVO nonGovCustomVO = new NonGovCustomVO();
									nonGovCustomVO.setAccNumber(accNo);
									nonGovCustomVO.setDeducAmount(deducAmount);
									nonGovCustomVO.setEmpName(lstEmpName.get(i) != null ? lstEmpName.get(i).toString() : "");
									lstNotUpdatedPaybillRecords.add(nonGovCustomVO);
									cntPaybillNotUpdated++;
								}
								lstUpdatedRecords.add(deduction);
							}
							else
							{
								NonGovCustomVO nonGovCustomVO = new NonGovCustomVO();
								nonGovCustomVO.setAccNumber(accNo);
								nonGovCustomVO.setDeducAmount(deducAmount);
								nonGovCustomVO.setEmpName(lstEmpName.get(i) != null ? lstEmpName.get(i).toString() : "");
								lstNotUpdatedRecords.add(nonGovCustomVO);
								lstNotUpdatedPaybillRecords.add(nonGovCustomVO);
								cntPaybillNotUpdated++;
								cntNotUpdateNonGov++;
							}
						}
					}
				}
			}
			logger.info("The Size of the List of Non Government Records is :-" + lstNotUpdatedRecords.size());
			//List<HrPayNonGovDeduction> actionList = nonGovDao.getListForColumnByValues("nonGovDeducAccNo",lstUpdatedData);
	//			String empName;
			for (int i = 0; i < lstUpdatedRecords.size(); i++)
			{
				HrPayNonGovDeduction govDeduction = (HrPayNonGovDeduction) lstUpdatedRecords.get(i);
				logger.info("The Deduction Type is:-" + govDeduction.getHrPayDeducTypeMst().getDeducDisplayName());
	//				empName = govDeduction.getHrEisEmpMst().getOrgEmpMst().getEmpFname() + " " + govDeduction.getHrEisEmpMst().getOrgEmpMst().getEmpMname() + " " + govDeduction.getHrEisEmpMst().getOrgEmpMst().getEmpLname();
			}

			objectArgs.put("uploadFlag", 1);
			objectArgs.put("actionList", lstUpdatedRecords);
			objectArgs.put("pageSize", lstUpdatedRecords.size());
			objectArgs.put("lstNotUpdatedRecords", lstNotUpdatedRecords);
			objectArgs.put("notUpdtPageSize", lstNotUpdatedRecords.size());
			objectArgs.put("lstUpdatedPaybillRecords", lstUpdatedPaybillRecords);
			objectArgs.put("lstNotUpdatedPaybillRecords", lstNotUpdatedPaybillRecords);
			objectArgs.put("cntPaybillUpdated", cntPaybillUpdated);
			objectArgs.put("cntPaybillNotUpdated", cntPaybillNotUpdated);
			objectArgs.put("cntUpdateNonGov", lstUpdatedRecords.size());
			objectArgs.put("cntNotUpdateNonGov", cntNotUpdateNonGov);
			attachmentList.clear();
			objectArgs.put("attachmentList", attachmentList);
			String empAllRec = "";
			if(voToService.get("empAllRec")!=null)
			  empAllRec = voToService.get("empAllRec").toString();


			if(!empAllRec.equals("") && empAllRec.equalsIgnoreCase("y"))
			{
				resultObject.setViewName("nonGovDeducViewEmpAllRec");
				//objectArgs.put("empId",empID);
				objectArgs.put("empAllRec", "true");
			}
			else
			{	
				resultObject.setViewName("nonGovDeducView");
				objectArgs.put("empAllRec", "false");
			}
			//objectArgs.put("MESSAGECODE",300006);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("nonGovDeducView");
			resultObject.setResultCode(ErrorConstants.SUCCESS);

		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}*/

	//Added By Kishan
	public Map getEmployeeByDeducType(Map objectArgs)
	{
		logger.info("inside the method:::::::::::: getEmployeeByDeducType");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			NonGovDeducDAOImpl nonGovDeducDAOImpl = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class, serv.getSessionFactory());
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			long deducType = 0;
			long empIdFromURL = 0; //This variable use for employee Id when employee is search
			String empName = "";
			Double amount = 0.0;
			long empId = 0;
			long dsgnId = 0;
			long billNo = 0;
			//Added By Roshan for CMP validation 
			int month=0;
			int year=0;
			//Ended By Roshan for CMP validation 
			//added by vaibhav tyagi: start
			String nonGovType=null;
			List deducList = new ArrayList();
			List empList = new ArrayList();
			if (voToService != null)
			{
				if (voToService.get("deducType") != null)
				{
					deducType = StringUtility.convertToLong(voToService.get("deducType").toString());
				}
				if (voToService.get("dsgnId") != null)
				{
					dsgnId = StringUtility.convertToLong(voToService.get("dsgnId").toString());
				}
				if (voToService.get("billNo") != null)
				{
					billNo = StringUtility.convertToLong(voToService.get("billNo").toString());
				}
				if (voToService.get("empId") != null)
				{
					empIdFromURL = StringUtility.convertToLong(voToService.get("empId").toString());
					if (empIdFromURL > 0)
					{
						objectArgs.put("empIDBySearch", empIdFromURL);
						billNo = nonGovDeducDAOImpl.getBillNoByEmpID(empIdFromURL);
					}
				}
				//Added By Roshan for CMP validation 
				month = Integer.parseInt(voToService.get("Month").toString());
				year = Integer.parseInt(voToService.get("Year").toString());
				//Ended By Roshan for CMP validation 
				}
			//Added By Roshan for CMP validation 
			if(nonGovDeducDAOImpl.isBillFwded(billNo,month,year)){
				objectArgs.put("BILLFWDED", "YES");
			}
			//Ended By Roshan for CMP validation 

			empList = nonGovDeducDAOImpl.getEmployeeByDeductionType(deducType, locId, dsgnId, billNo, empIdFromURL);
			logger.info("Employee list size is -->" + empList.size());

			if (empList != null && !empList.isEmpty())
			{
				int size = empList.size();
				CustomDeduction customDeduction = null;
				Object[] empObj = null;
				for (int i = 0; i < size; i++)
				{
					customDeduction = new CustomDeduction();
					empObj = (Object[]) empList.get(i);
					empId = Long.parseLong(empObj[0].toString());
					empName = empObj[1].toString();
					if (empObj[2].toString().equalsIgnoreCase("123456789012345"))
					{
						logger.info("Inside if");
						amount = 0.0;
						//added by vaibhav tyagi: start
						nonGovType=" ";
						//added by vaibhav tyagi: end
						customDeduction.setInsertFlag("Y");

					}
					else
					{
						amount = Double.parseDouble(empObj[2].toString());
						//added by vaibhav tyagi: start
						if((empObj[3]!=null)&&(!empObj[3].equals("")))
							nonGovType=empObj[3].toString();
						else
							nonGovType="";
						//added by vaibhav tyagi: end
						customDeduction.setInsertFlag("N");
						logger.info("Inside else nonGovType "+nonGovType);
					}
					customDeduction.setEmployeeId(empId);
					customDeduction.setEmployeeName(empName);
					customDeduction.setAmount(amount);
					//added by vaibhav tyagi: start
					customDeduction.setNonGovTypeValue(nonGovType);
					//added by vaibhav tyagi: start
					deducList.add(customDeduction);
					customDeduction = null;
					empObj = null;
				}
			}

			//added by vaibhav tyagi: start
			int deducListSize=deducList.size();
			objectArgs.put("deducListSize", deducListSize);
			//added by vaibhav tyagi: end

			objectArgs.put("deducList", deducList);
			objectArgs.put("deducType", deducType);
			objectArgs.put("dsgnId", dsgnId);
			objectArgs.put("billNo", billNo);
			objectArgs.put("empListSize", empList.size());
			deducList = null;
			empList = null;
			serv = null;
			nonGovDeducDAOImpl = null;
			voToService = null;
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}
		return objectArgs;

	}

	public Map updateOrInsertEmployeeData(Map objectArgs) throws Exception
	{

		logger.info("inside the method:::::::::::: updateOrInsertEmployeeData");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			NonGovDeducDAOImpl nonGovDeducDAOImpl = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class, serv.getSessionFactory());

			GenericDaoHibernateImpl hrPayPaySlipNonGovtDAOImpl = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
			hrPayPaySlipNonGovtDAOImpl.setSessionFactory(serv.getSessionFactory());
			GenericDaoHibernateImpl hrPayPaybillDAOImpl = new GenericDaoHibernateImpl(HrPayPaybill.class);
			hrPayPaybillDAOImpl.setSessionFactory(serv.getSessionFactory());

			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long employeeListSize = StringUtility.convertToLong(voToService.get("empListSize").toString());
			long BillNo = StringUtility.convertToLong(voToService.get("billNo").toString());
			int month = Integer.parseInt(voToService.get("Month").toString());
			int year = Integer.parseInt(voToService.get("Year").toString());
			Date curr_date = new Date();

			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			logger.info("Employee List Size in updateOrInsertEmployeeData-->" + employeeListSize);
			long deducType = 0;
			long empId = 0;
			//added by vaibhav tyagi: start
			String nonGovType = null;
			//added by vaibhav tyagi: end
			String InsertFlag = "";
			long newAmount = 0;
			String empIdForNStr = "";
			Map<String, Long> amountForN = new HashMap();
			//added by vaibhav tyagi: start
			Map<String, String> nonGovTypeForN = new HashMap();
			//added by vaibhav tyagi: end
			String empIdForYStr = "";
			IdGenerator idGen = new IdGenerator();

			deducType = StringUtility.convertToLong(voToService.get("deducType").toString());

			GenericDaoHibernateImpl hrPayDeducTypeMstDAOImpl = new GenericDaoHibernateImpl(HrPayDeducTypeMst.class);
			hrPayDeducTypeMstDAOImpl.setSessionFactory(serv.getSessionFactory());
			HrPayDeducTypeMst hrPayDeducTypeMst = (HrPayDeducTypeMst) hrPayDeducTypeMstDAOImpl.read(deducType);
			hrPayDeducTypeMstDAOImpl = null;

			for (int i = 1; i <= employeeListSize; i++)
			{
				if (!voToService.get("employeeId" + i).equals(""))
				{
					empId = StringUtility.convertToLong(voToService.get("employeeId" + i).toString());
				}
				//added by vaibhav tyagi:start
				if ((!voToService.get("nonGovTypeValue" + i).equals(""))&&(voToService.get("nonGovTypeValue" + i)!=null))
				{
					nonGovType = voToService.get("nonGovTypeValue" + i).toString();
					logger.info("nonGovType****"+nonGovType);
					//added by vaibhav tyagi: start
					nonGovTypeForN.put("" + empId, nonGovType);
					//added by vaibhav tyagi: end
				}
				//added by vaibhav tyagi:end
				if (!voToService.get("InsertFlag" + i).equals(""))
				{
					InsertFlag = voToService.get("InsertFlag" + i).toString();
				}
				if (!voToService.get("exisAmount" + i).equals(""))
				{
					String exisAmtStr = voToService.get("exisAmount" + i).toString();
					if (exisAmtStr.contains("."))
					{
						exisAmtStr = exisAmtStr.substring(0, exisAmtStr.lastIndexOf("."));
					}
					exisAmtStr = null;
				}
				if (!voToService.get("newAmount" + i).equals(""))
				{
					String newAmtStr = voToService.get("newAmount" + i).toString();
					if (newAmtStr.contains("."))
					{
						newAmtStr = newAmtStr.substring(0, newAmtStr.lastIndexOf("."));
					}
					newAmount = StringUtility.convertToLong(newAmtStr);
					amountForN.put("" + empId, newAmount);
					logger.info("Employee Id : " + empId + " and new amoutStr is " + newAmtStr + "new amount " + newAmount);
					newAmtStr = null;
				}

				if (InsertFlag.equalsIgnoreCase("N")) //"N" - for update existing amount
				{
					empIdForNStr = empIdForNStr + empId + ",";
					logger.info("empIdForNStr-->" + empIdForNStr);
				}
				else
					//if (InsertFlag.equalsIgnoreCase("Y")) //"Y" - for inserting data
				{
					empIdForYStr = empIdForYStr + empId + ",";
				}
			}
			if (!empIdForNStr.equalsIgnoreCase(""))
			{
				empIdForNStr = empIdForNStr.substring(0, empIdForNStr.lastIndexOf(','));
				//updating HrPayNonGovDeduction Table
				List hrPayNonGovDeductionList = nonGovDeducDAOImpl.getNonGovDeducDataByEmpId(empIdForNStr, deducType);
				HrPayNonGovDeduction hrPayNonGovDeduction = null;
				if (hrPayNonGovDeductionList.size() > 0)
				{
					for (int i = 0; i < hrPayNonGovDeductionList.size(); i++)
					{
						hrPayNonGovDeduction = (HrPayNonGovDeduction) hrPayNonGovDeductionList.get(i);
						long newamountFromMap = (Long) amountForN.get(Long.toString(hrPayNonGovDeduction.getHrEisEmpMst().getEmpId()));
						//added by vaibhav tyagi: start
						String newNonGovTypeFromMap =  nonGovTypeForN.get(Long.toString(hrPayNonGovDeduction.getHrEisEmpMst().getEmpId()));
						//added by vaibhav tyagi:end
						hrPayNonGovDeduction.setNonGovDeducAmount(newamountFromMap);
						//added by vaibhav tyagi: start
						hrPayNonGovDeduction.setNonGovType(newNonGovTypeFromMap);
						//added by vaibhav tyagi:end
						nonGovDeducDAOImpl.update(hrPayNonGovDeduction);
						hrPayNonGovDeduction = null;
					}
				}

				//updating HrPayPaySlip Table	
				String[] employyIdForNStrArray = empIdForNStr.split(",");
				int lenghtofEmployee = employyIdForNStrArray.length;
				List<Long> employeeIdForNForInsert = new ArrayList<Long>();
				for (int i = 0; i < lenghtofEmployee; i++)
				{
					employeeIdForNForInsert.add(Long.valueOf(employyIdForNStrArray[i]));
				}
				List hrPayPayslipNonGovtList = nonGovDeducDAOImpl.getPayPaySlipDataByEmpId(empIdForNStr, BillNo, month, year, locId, deducType);
				GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
				genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
				HrPayPayslipNonGovt hrPayPayslipNonGovt = null;
				if (!hrPayPayslipNonGovtList.isEmpty())
				{
					int size = hrPayPayslipNonGovtList.size();
					Object[] hrPayPayslipNonGovtObj = null;
					for (int i = 0; i < size; i++)
					{
						hrPayPayslipNonGovtObj = (Object[]) hrPayPayslipNonGovtList.get(i);
						hrPayPayslipNonGovt = (HrPayPayslipNonGovt) hrPayPayslipNonGovtObj[0];
						long empIdFromDB = Long.valueOf(hrPayPayslipNonGovtObj[1].toString());
						long newamountFromMap = (Long) amountForN.get(Long.toString(empIdFromDB));
						//added by vaibhav tyagi: start
						String newNonGovTypeFromMap =  nonGovTypeForN.get(Long.toString(empIdFromDB));
						//added by vaibhav tyagi:end
						employeeIdForNForInsert.remove(empIdFromDB);
						hrPayPayslipNonGovt.setDeducAmount(newamountFromMap);
						//added by vaibhav tyagi: start
						hrPayPayslipNonGovt.setNonGovType(newNonGovTypeFromMap);
						//added by vaibhav tyagi: end
						genericDaoHibernateImpl.update(hrPayPayslipNonGovt);
						hrPayPayslipNonGovt = null;
					}
				}
				if ((hrPayPayslipNonGovtList.size() == 0 || lenghtofEmployee != hrPayPayslipNonGovtList.size()) && !employeeIdForNForInsert.isEmpty())
				{
					boolean isBillGenOrApp = nonGovDeducDAOImpl.isBillGenOrApp(BillNo, month, year);
					if (isBillGenOrApp)
					{
						int size = employeeIdForNForInsert.size();
						String empIdForNForInsert = "";
						for (int j = 0; j < size; j++)
						{
							empIdForNForInsert = empIdForNForInsert + employeeIdForNForInsert.get(j) + ",";
						}
						if (empIdForNForInsert != null && !"".equals(empIdForNForInsert))
							empIdForNForInsert = empIdForNForInsert.substring(0, empIdForNForInsert.lastIndexOf(','));

						List PaybillIdList = nonGovDeducDAOImpl.getPayBillIdByEmpId(empIdForNForInsert, month, year, BillNo, locId);
						int PaybillIdListSize = PaybillIdList.size();
						Object[] paybillIdObj = null;

						HrPayPaybill hrPayPaybill = null;

						for (int i = 0; i < PaybillIdListSize; i++)
						{
							paybillIdObj = (Object[]) PaybillIdList.get(i);
							long paybillId = Long.parseLong(paybillIdObj[0].toString());
							long empIdForInsert = Long.parseLong(paybillIdObj[1].toString());
							long newamountFromMap = (Long) amountForN.get(Long.toString(empIdForInsert));
							//added by vaibhav tyagi: start
							String newNonGovTypeFromMap =  nonGovTypeForN.get(Long.toString(empIdForInsert));
							//added by vaibhav tyagi:end

							hrPayPaybill = (HrPayPaybill) hrPayPaybillDAOImpl.read(paybillId);
							hrPayPayslipNonGovt = new HrPayPayslipNonGovt();
							Long paySlipNonGovId = idGen.PKGenerator("HR_PAY_PAYSLIP_NON_GOVT", objectArgs);
							logger.info("Id Generator for HR_PAY_PAYSLIP_NON_GOVT generated id is -->" + paySlipNonGovId);
							hrPayPayslipNonGovt.setNonGovtId(paySlipNonGovId);
							hrPayPayslipNonGovt.setPaybillID(hrPayPaybill);
							hrPayPayslipNonGovt.setDeducCode(deducType);
							hrPayPayslipNonGovt.setDeducAmount(newamountFromMap);
							//added by vaibhav tyagi: start
							hrPayPayslipNonGovt.setNonGovType(newNonGovTypeFromMap);
							//added by vaibhav tyagi: end
							hrPayPayslipNonGovt.setOrgUserMstByCreatedBy(orgUserMst);
							hrPayPayslipNonGovt.setOrgPostMstByCreatedByPost(orgPostMst);
							hrPayPayslipNonGovt.setCreatedDate(curr_date);
							hrPayPaySlipNonGovtDAOImpl.create(hrPayPayslipNonGovt);
							logger.info("Record Inserted for empId -> " + empId);
							hrPayPaybill = null;
							hrPayPayslipNonGovt = null;
						}
						empIdForNForInsert = null;
						PaybillIdList = null;

					}
				}
				//genericDaoHibernateImpl.getSession().flush();
				hrPayNonGovDeductionList = null;
				employyIdForNStrArray = null;
				employeeIdForNForInsert = null;
				hrPayPayslipNonGovtList = null;
				genericDaoHibernateImpl = null;
			}
			if (!empIdForYStr.equalsIgnoreCase(""))
			{
				empIdForYStr = empIdForYStr.substring(0, empIdForYStr.lastIndexOf(','));
				String[] empIdArrForY = empIdForYStr.split(",");

				//inserting in HrPayNonGovDeduction Table

				List hrEisEmpMstList = nonGovDeducDAOImpl.getHrEisEmpVO(empIdForYStr);
				Map<Long, HrEisEmpMst> hrEisEmpMstMap = new HashMap<Long, HrEisEmpMst>();
				int hrEisEmpMstListSize = hrEisEmpMstList.size();
				HrEisEmpMst hrEisEmpMstObj = null;
				for (int i = 0; i < hrEisEmpMstListSize; i++)
				{
					hrEisEmpMstObj = (HrEisEmpMst) hrEisEmpMstList.get(i);
					hrEisEmpMstMap.put(hrEisEmpMstObj.getEmpId(), hrEisEmpMstObj);
					hrEisEmpMstObj = null;
				}
				logger.info("empIdArrForY size - " + empIdArrForY.length);
				GenericDaoHibernateImpl hrPayNonGovDAOImpl = new GenericDaoHibernateImpl(HrPayNonGovDeduction.class);
				hrPayNonGovDAOImpl.setSessionFactory(serv.getSessionFactory());
				int lengthOfY = empIdArrForY.length;
				HrPayNonGovDeduction hrPayNonGovDeduction = null;
				String startDateOfMonthStr = "01/" + (curr_date.getMonth() + 1) + "/" + (curr_date.getYear() + 1900);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
				Date startDateOfMonth = sdf.parse(startDateOfMonthStr);

				for (int i = 0; i < lengthOfY; i++)
				{
					long newamountFromMap = (Long) amountForN.get(empIdArrForY[i]);
					//added by vaibhav tyagi: start
					String newNonGovTypeFromMap =  nonGovTypeForN.get((empIdArrForY[i]));
					//added by vaibhav tyagi:end
					hrPayNonGovDeduction = new HrPayNonGovDeduction();
					Long nonGovDeducId = idGen.PKGenerator("HR_PAY_NON_GOV_DEDUCTION", objectArgs);
					logger.info("Id Generator for HR_PAY_NON_GOV_DEDUCTION generated id is -->" + nonGovDeducId);
					hrPayNonGovDeduction.setNonGovDeducId(nonGovDeducId);
					hrPayNonGovDeduction.setHrEisEmpMst((HrEisEmpMst) hrEisEmpMstMap.get(Long.parseLong(empIdArrForY[i])));
					hrPayNonGovDeduction.setHrPayDeducTypeMst(hrPayDeducTypeMst);
					hrPayNonGovDeduction.setNonGovDeducAmount(newamountFromMap);
					//added by vaibhav tyagi: start
					hrPayNonGovDeduction.setNonGovType(newNonGovTypeFromMap);
					//added by vaibhav tyagi: end
					logger.info("Starting date of month is -->" + startDateOfMonth);
					hrPayNonGovDeduction.setNonGovDeducEfftStartDt(startDateOfMonth);
					hrPayNonGovDeduction.setCmnLocationMst(cmnLocationMst);
					hrPayNonGovDeduction.setCmnDatabaseMst(cmnDatabaseMst);
					hrPayNonGovDeduction.setOrgPostMstByCreatedByPost(orgPostMst);
					hrPayNonGovDeduction.setCreatedDate(curr_date);
					hrPayNonGovDeduction.setOrgUserMstByCreatedBy(orgUserMst);
					hrPayNonGovDeduction.setTrnCounter(1);
					logger.info("nonGovDeducId - " + nonGovDeducId + " hrPayDeducTypeMst " + hrPayDeducTypeMst + " newamountFromMap " + newamountFromMap + " ");
					hrPayNonGovDAOImpl.create(hrPayNonGovDeduction);

					hrPayNonGovDeduction = null;
				}

				//inserting in HR_PAY_PAYSLIP_NON_GOVT
				List PaybillIdList = nonGovDeducDAOImpl.getPayBillIdByEmpId(empIdForYStr, month, year, BillNo, locId);
				String empIdForUpdate = "";
				String PayBillIdStr = "";
				int PaybillIdListSize = PaybillIdList.size();
				Object[] paybillIdObj = null;
				for (int i = 0; i < PaybillIdListSize; i++)
				{
					paybillIdObj = (Object[]) PaybillIdList.get(i);
					PayBillIdStr = PayBillIdStr + paybillIdObj[0].toString() + ",";
					paybillIdObj = null;
				}

				if (!PayBillIdStr.equalsIgnoreCase(""))
				{
					PayBillIdStr = PayBillIdStr.substring(0, PayBillIdStr.lastIndexOf(","));
				}
				Map<Long, Long> nonGovMap = null;
				if (PayBillIdStr.length() > 0)
				{
					List payBillIdCountList = nonGovDeducDAOImpl.checkPayBillIdCount(PayBillIdStr, deducType);
					nonGovMap = new HashMap<Long, Long>();
					HrPayPayslipNonGovt payBillObj = null;
					for (int i = 0; i < payBillIdCountList.size(); i++)
					{
						payBillObj = (HrPayPayslipNonGovt) payBillIdCountList.get(i);
						nonGovMap.put(payBillObj.getPaybillID().getId(), payBillObj.getNonGovtId());
					}
					payBillIdCountList = null;
				}

				HrPayPaybill hrPayPaybill = null;
				HrPayPayslipNonGovt hrPayPayslipNonGovt = null;

				for (int i = 0; i < PaybillIdListSize; i++)
				{
					paybillIdObj = (Object[]) PaybillIdList.get(i);
					long paybillId = Long.parseLong(paybillIdObj[0].toString());
					long empIdForInsert = Long.parseLong(paybillIdObj[1].toString());
					long newamountFromMap = (Long) amountForN.get("" + empIdForInsert);
					//added by vaibhav tyagi: start
					String newNonGovTypeFromMap =  nonGovTypeForN.get("" + empIdForInsert);
					//added by vaibhav tyagi:end
					//check count if count == 0 then insert otherwise not
					//int count = nonGovDeducDAOImpl.checkPayBillIdCount(paybillId);
					//identifying duplicate or not if not then insert in HR_PAY_PAYSLIP_NON_GOVT other wise updating existing row
					if (nonGovMap != null && !nonGovMap.containsKey(paybillId))
					{
						hrPayPaybill = (HrPayPaybill) hrPayPaybillDAOImpl.read(paybillId);
						hrPayPayslipNonGovt = new HrPayPayslipNonGovt();
						Long paySlipNonGovId = idGen.PKGenerator("HR_PAY_PAYSLIP_NON_GOVT", objectArgs);
						logger.info("Id Generator for HR_PAY_PAYSLIP_NON_GOVT generated id is -->" + paySlipNonGovId);
						hrPayPayslipNonGovt.setNonGovtId(paySlipNonGovId);
						hrPayPayslipNonGovt.setPaybillID(hrPayPaybill);
						hrPayPayslipNonGovt.setDeducCode(deducType);
						hrPayPayslipNonGovt.setDeducAmount(newamountFromMap);
						//added by vaibhav tyagi: start
						hrPayPayslipNonGovt.setNonGovType(newNonGovTypeFromMap);
						//added by vaibhav tyagi: end
						hrPayPayslipNonGovt.setOrgUserMstByCreatedBy(orgUserMst);
						hrPayPayslipNonGovt.setOrgPostMstByCreatedByPost(orgPostMst);
						hrPayPayslipNonGovt.setCreatedDate(curr_date);
						hrPayPaySlipNonGovtDAOImpl.create(hrPayPayslipNonGovt);
						logger.info("Record Inserted for empId -> " + empId);
						hrPayPayslipNonGovt = null;

					}
					else
					{
						empIdForUpdate = empIdForUpdate + empIdForInsert + ",";
					}
					paybillIdObj = null;
				}

				GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
				genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
				if (!empIdForUpdate.equalsIgnoreCase(""))
				{
					//updating HrPayPaySlip Table
					empIdForUpdate = empIdForUpdate.substring(0, empIdForUpdate.lastIndexOf(","));
					List hrPayPayslipNonGovtList = nonGovDeducDAOImpl.getPayPaySlipDataByEmpId(empIdForUpdate, BillNo, month, year, locId, deducType);
					genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
					if (!hrPayPayslipNonGovtList.isEmpty())
					{
						Object[] hrPayPayslipNonGovtObj = null;
						int size = hrPayPayslipNonGovtList.size();
						for (int i = 0; i < size; i++)
						{
							hrPayPayslipNonGovtObj = (Object[]) hrPayPayslipNonGovtList.get(i);
							hrPayPayslipNonGovt = (HrPayPayslipNonGovt) hrPayPayslipNonGovtObj[0];
							long newamountFromMap = (Long) amountForN.get(hrPayPayslipNonGovtObj[1].toString());
							//added by vaibhav tyagi: start
							String newNonGovTypeFromMap =  nonGovTypeForN.get(hrPayPayslipNonGovtObj[1].toString());
							//added by vaibhav tyagi:end
							hrPayPayslipNonGovt.setDeducAmount(newamountFromMap);
							//added by vaibhav tyagi: start
							hrPayPayslipNonGovt.setNonGovType(newNonGovTypeFromMap);
							//added by vaibhav tyagi: end
							genericDaoHibernateImpl.update(hrPayPayslipNonGovt);
							hrPayPayslipNonGovt = null;
						}
					}
				}
				//genericDaoHibernateImpl.getSession().flush();
				empIdForYStr = null;
				empIdArrForY = null;
				hrEisEmpMstList = null;
				hrEisEmpMstMap = null;
				hrPayNonGovDAOImpl = null;
				startDateOfMonthStr = null;
				sdf = null;
				startDateOfMonth = null;
				PaybillIdList = null;
			}
			objectArgs.put("msg", "Record(s) Updated Successfully");

			orgUserMstDaoImpl = null;
			orgPostMstDaoImpl = null;
			cmnDatabaseMstDaoImpl = null;
			cmnLocationMstDaoImpl = null;
			cmnLocationMstDaoImpl = null;
			nonGovDeducDAOImpl = null;
			orgUserMst = null;
			orgPostMst = null;
			cmnDatabaseMst = null;
			cmnLocationMst = null;
			amountForN = null;
			idGen = null;
			curr_date = null;
			hrPayPaySlipNonGovtDAOImpl = null;
			hrPayPaybillDAOImpl = null;

		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return objectArgs;
	}
	//Ended By Kishan

	/*public ResultObject updateNonGovDataByExcel(Map objectArgs){		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		logger.info("inside Update non Gov.Data------------>");
		long deducAmount=0;
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			ResourceBundle rsPayroll = ResourceBundle.getBundle("resources.Payroll");
			String fileName="";		// File which contains the non-Governement deduction data.
			Map nonGovData = new HashMap();
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			List lstNonGovAcc = new ArrayList();
			List lstNonGovAmount = new ArrayList();
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			NonGovDeducDAO nonGovDao = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class,serv.getSessionFactory()); 
		    //fileName = request.getContextPath();
		    //fileName+="d:\\hrms1\\hrms_ws\\hrms\\web\\WEB-INF\\classes\\UploadedFiles\\Non_Gov_deduc.xls";
			ArrayList attachmentList = (ArrayList)objectArgs.get("attachmentList");
			FileItem fileItem = null;
			ReadExcelFile excelData = new ReadExcelFile();
			Workbook workBook;
			List tempNonGov = new ArrayList();
			Date sysDate = new Date();
			List lstUpdatedData = new ArrayList();
			int flag=0;
			int count=0;
			IdGenerator idGen = new IdGenerator();
			HrPayNonGovDeduction nonGovDeduction = new HrPayNonGovDeduction();
			String nonGovAccNo="";


			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

			long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        logger.info("Loc Id is:-->" + dbId + " " + locId);

	        long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);

			long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			CmnLookupMst cmnLookupMst = cmnLookupMstDAOImpl.read(300161l);

			HrPayNonGovDeduction tempNonGovDeduc = new HrPayNonGovDeduction();
			long nonGovDeducId=0;
			Date effectStartDate = new Date();
			Date effectEndDate = new Date();
			Calendar cal = Calendar.getInstance();
			effectStartDate.setDate(cal.getActualMinimum(Calendar.DATE));
			logger.info("The Effective Start Date is:-"+effectStartDate);
			effectEndDate.setDate(cal.getActualMaximum(Calendar.DATE));
			logger.info("The Effective end Date is:-"+effectEndDate);
			long deducType = (objectArgs.get("deducType").toString()!=null && !objectArgs.get("deducType").toString().equals(""))?Long.parseLong(objectArgs.get("deducType").toString()):0;
			long nonFlag = (objectArgs.get("flag") != null && !objectArgs.equals(""))? Long.parseLong(objectArgs.get("flag").toString()):0;

			if(attachmentList!=null && attachmentList.size()>0)
	        {
	        	logger.info("attachment list is not zero"+attachmentList.size());
	        	Iterator attachmentListIterator = attachmentList.listIterator();
	        	while (attachmentListIterator.hasNext())
	            {
	        		fileItem = (FileItem) attachmentListIterator.next();
	                logger.info("details here "+fileItem.get());
	                logger.info("file name"+fileItem.getName().
	                		substring(fileItem.getName().lastIndexOf("\\") + 1, fileItem.getName().length()));
	                logger.info("file name"+fileItem.getFieldName());
	                workBook = Workbook.getWorkbook(fileItem.getInputStream());
	                logger.info("iterating while loop.....check workbook and file iterms "+ workBook.getNumberOfSheets());
	                nonGovData.clear();
	                nonGovData = excelData.readFileContent(workBook);
	                lstNonGovAcc.clear();
	                lstNonGovAmount.clear();
	                lstNonGovAcc = (List) nonGovData.get("lstNonGovAccountNo");

	    			lstNonGovAmount = (List)nonGovData.get("lstNonGovAmount");
	    			if(lstNonGovAcc!=null && lstNonGovAcc.size()>0){
	    				logger.info("The Size of the NonGov Account is:-"+lstNonGovAcc.size());

	    				Iterator amtIt = null;
	    				if(lstNonGovAmount!=null)
	    					amtIt = lstNonGovAmount.iterator();
	    			for(Iterator it = lstNonGovAcc.iterator();it.hasNext();) 
	    			{
	    				String accNo = (String)it.next().toString();
	    				String amt = amtIt.next().toString();
	    				logger.info("Ac No is " + accNo + " amt is" +  amt);
	    				//tempNonGov = nonGovDao.getListForColumnByValues("nonGovDeducAccNo",lstNonGovAcc);
	    				tempNonGov = nonGovDao.getNonGovDeducDatabyAccno(accNo,deducType);

	    				if(tempNonGov!=null && tempNonGov.size() >0) {
	//	    				for(int i=0;i<tempNonGov.size();i++){
		    				HrPayNonGovDeduction tempDeduction = new HrPayNonGovDeduction();
		    				tempDeduction = (HrPayNonGovDeduction)tempNonGov.get(0);
		    				if(lstNonGovAmount.get(i)!=null){
		    					flag=0;
		    					count=0;
		    					for(int j=0;j<lstNonGovAcc.size();j++){
		    						nonGovAccNo="";
		    						nonGovAccNo = lstNonGovAcc.get(j).toString();
		    						if(tempDeduction.getNonGovDeducAccNo().equals(nonGovAccNo)){
		    							flag=1;
		    							break;
		    						}
		    						count++;
		    					}
		    					if(flag==0)
		    						continue;	
		    					nonGovDeducId = idGen.PKGenerator("hr_pay_non_gov_deduction", objectArgs);
		    					logger.info("The PK IS:-"+nonGovDeducId);
		    					deducAmount = Long.parseLong(amt);
		    					HrPayNonGovDeduction deduction = new HrPayNonGovDeduction();
		    					deduction.setNonGovDeducId(nonGovDeducId);
		    					deduction.setCmnDatabaseMst(cmnDatabaseMst);
		    					deduction.setCmnLocationMst(cmnLocationMst);
		    					deduction.setCmnLookupMst(cmnLookupMst);
		    					deduction.setCreatedDate(sysDate);
		    					deduction.setHrEisEmpMst(tempDeduction.getHrEisEmpMst());
		    					deduction.setHrPayDeducTypeMst(tempDeduction.getHrPayDeducTypeMst());
		    					deduction.setNonGovDeducAccNo(tempDeduction.getNonGovDeducAccNo());
		    					deduction.setNonGovDeducAmount(deducAmount);
		    					deduction.setNonGovDeducEfftEndDt(effectEndDate);
		    					deduction.setNonGovDeducEfftStartDt(effectStartDate);
		    					deduction.setOrgPostMstByCreatedByPost(orgPostMst);
		    					deduction.setTrnCounter(1);
		    					deduction.setOrgUserMstByCreatedBy(orgUserMst);



		    					logger.info("The Size of the list is:-"+tempNonGov.size());
				    			tempNonGov.clear();


			    				logger.info("the Non Gov Deduc Amount is:-"+deducAmount);
			    				logger.info("the Non Gov Deduc Account is:-"+deduction.getNonGovDeducAccNo());

			    				deduction.setNonGovDeducAmount(deducAmount);
			    				deduction.setUpdatedDate(sysDate);
				    			logger.info("the Non Gov Deduc Account is:-"+deduction.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+deduction.getHrEisEmpMst().getOrgEmpMst().getEmpFname());
			    				nonGovDao.create(deduction);
			    				lstUpdatedData.add(deduction.getNonGovDeducId());
			    				logger.info("The Record is Updated");
		    				//}
		    			}



	    				}

	    			}
	    			}
	            }
			logger.info("Non Glag is:-"+nonFlag);
			List<HrPayNonGovDeduction> actionList = nonGovDao.getNonGovDataById(lstUpdatedData);
			for(int i=0;i<actionList.size();i++){
				HrPayNonGovDeduction govDeduction =  (HrPayNonGovDeduction)actionList.get(i);
				logger.info("The Deduction Type is:-"+govDeduction.getHrPayDeducTypeMst().getDeducDisplayName());
			}
			objectArgs.put("flag",1);
			objectArgs.put("actionList", actionList);
			String empAllRec = "";
			if(voToService.get("empAllRec")!=null)
	          empAllRec = voToService.get("empAllRec").toString();


	        if(!empAllRec.equals("") && empAllRec.equalsIgnoreCase("y"))
	        {
	        	resultObject.setViewName("nonGovDeducViewEmpAllRec");
	        	//objectArgs.put("empId",empID);
	        	objectArgs.put("empAllRec", "true");
	        }
	        else
	        {	
	        	resultObject.setViewName("nonGovDeducView");
	        	objectArgs.put("empAllRec", "false");
	        }	
			//objectArgs.put("MESSAGECODE",300006);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("nonGovDeducView");


		}
		catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}*/

	/*	public Map readFileContent(Workbook nonGovWorkbook){
			//String file="src/Non_Gov_deduc.xls";

			List lstNonGovAccountNo = new ArrayList();
		    List lstNonGovAmount = new ArrayList();
		    List lstEmpName =  new ArrayList();
		    Map mapExcelData = new HashMap();
		    int columns=0;
		    int rows =0;
			try {
				logger.info("The Function is called");
				//String fName= getServletContextPath(); //getServletContext().getResource("/WEB-INF/classes/UploadedFiles/Non_Gov_deduc.xls").getPath();
				for(int i=0;i<nonGovWorkbook.getNumberOfSheets();i++){
					Sheet sheet = nonGovWorkbook.getSheet(0);
					columns = sheet.getColumns();
					rows = sheet.getRows();
					//	String data;
					logger.info("The Number of columns are :-"+columns);
					logger.info("The Number of Rows are :-"+rows);
					for(int row=2;row<rows;row++){		    	
						//logger.info("The Account Number is:-"+sheet.getCell(1, row).getContents()+" And Amount is:-"+sheet.getCell(3, row).getContents());
						if(sheet.getCell(2, row).getContents()!=null){
							lstNonGovAccountNo.add(sheet.getCell(1, row).getContents());
							lstEmpName.add(sheet.getCell(2, row).getContents());
							lstNonGovAmount.add(sheet.getCell(3, row).getContents());
						}
					}
				}

			    mapExcelData.put("lstNonGovAccountNo",lstNonGovAccountNo);
			    mapExcelData.put("lstEmpName",lstEmpName);
			    mapExcelData.put("lstNonGovAmount",lstNonGovAmount);

			} catch(Exception ioe) {
			    logger.info("Error: " + ioe);
			}
			return mapExcelData;
		}
	 */
}
