/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 16, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.InterestCalculationDAO;
import com.tcs.sgv.dcps.dao.InterestCalculationDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsContributionYearly;
import com.tcs.sgv.dcps.valueobject.MstDcpsSixPCInterestYearly;
import com.tcs.sgv.dcps.valueobject.MstEmp;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Jun 16, 2011
 */
public class InterestCalculationServiceImpl extends ServiceImpl implements
		InterestCalculationService {

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

	private Locale gLclLocale = null; /* LOCALE */
	private String gStrPostId = null; /* STRING POST ID */
	private String gStrUserId = null; /* STRING USER ID */
	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */
	private HttpSession session = null; /* SESSION */
	private String gStrLocale = null; /* STRING LOCALE */
	private Date gDtCurDate = null; /* CURRENT DATE */

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

	private final static Logger gLogger = Logger
			.getLogger(DdoInfoServiceImpl.class);

	private static final Log logger = LogFactory
			.getLog(DdoInfoServiceImpl.class); /* LOGGER */

	private void setSessionInfo(Map inputMap) throws Exception {

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
			gStrLocId = SessionHelper.getLocationId(inputMap).toString();

		} catch (Exception e) {
			throw e;
		}
	}

	public ResultObject loadInterestCalculation(Map<String, Object> inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lLstYears = null;
		List lLstTreasuries = null;
		String selectedTreasury = null;
		String selectedDDO = null;
		List lListDdoList = null;
		List lListEmpList = null;
		
		try {

			setSessionInfo(inputMap);

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			InterestCalculationDAO lObjInterestCalculationDAO = new InterestCalculationDAOImpl(
					null, serv.getSessionFactory());
			String lStrViewPageFor = StringUtility.getParameter("viewPageFor", request).trim();
			String lStrYear = StringUtility.getParameter("yearId", request).trim();
			String lStrGetEmps = StringUtility.getParameter("getEmpsForDDO",request).trim();
			
			lLstYears = lObjDcpsCommonDAO.getFinyears();
			inputMap.put("YEARS", lLstYears);
			
			inputMap.put("selectedYear", lStrYear);

			if(!"".equals(lStrViewPageFor))
			{
				lLstTreasuries = lObjDcpsCommonDAO.getAllTreasuries();
				inputMap.put("TREASURIES", lLstTreasuries);
				
			}
			
			if(lStrViewPageFor.equals("Treasury"))
			{
				inputMap.put("TreasuryChecked", "Y");
				inputMap.put("DDOChecked", "N");
				inputMap.put("EMPChecked", "N");
			}
			
			if(lStrViewPageFor.equals("DDO"))
			{
				inputMap.put("TreasuryChecked", "N");
				inputMap.put("DDOChecked", "Y");
				inputMap.put("EMPChecked", "N");
			}
			
			if(lStrViewPageFor.equals("Emp"))
			{
				inputMap.put("TreasuryChecked", "N");
				inputMap.put("DDOChecked", "N");
				inputMap.put("EMPChecked", "Y");
				
				if(lStrGetEmps.equals("Yes"))
				{
					selectedTreasury = StringUtility.getParameter("treasuryCode", request);
					inputMap.put("selectedTreasury", selectedTreasury);
					lListDdoList = lObjDcpsCommonDAO.getAllDDOForTreasury(selectedTreasury);
					selectedDDO = StringUtility.getParameter("ddoCode", request).trim();
					inputMap.put("DDOList", lListDdoList);
					inputMap.put("selectedDDO", selectedDDO);
					lListEmpList = lObjInterestCalculationDAO.getAllEmpsUnderDDO(selectedDDO);
					inputMap.put("EmpList", lListEmpList);
				}
			}

			resObj.setResultValue(inputMap);
			resObj.setViewName("InterestCalculation");

		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error in getDigiSig " + e, e);
		}

		return resObj;

	}

	public ResultObject calculateDCPSYearlyInterest(Map<String, Object> inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		Boolean lBlFlag = false;
		List listAllEmpsForIntrstCalc = new ArrayList();
		List listAllEmpsForIntrstCalcSixPC = new ArrayList();
		Long dcpsEmpId = null;
		String dcpsId = null;
		Long yearId = null;
		Long previousFinYearId = null;
		Long YearIdForVoucherDate = null;
		Long treasuryCode = null;
		List listContriDtlsForGivenEmp = null;
		String lStrVoucherDate = null;
		Double lDoubleVoucherContriForMonth = 0d;
		Double lDoubleVoucherContriForMonthEmplr = 0d;
		Date lDateContriToDate = null;
		Date lDateContriFromDate = null;
		Date lDateFinYearEndDate = null;
		Integer DaysLeftTillYearEnd = null;
		Double InterestRate = 0d;
		//Double interestForCurrentYear = 0d;
		//Double interestForCurrentYearEmplr = 0d;
		Double interestForCurrentMonth = 0d;
		Double interestForCurrentMonthEmplr = 0d;
		Double totalOfAllMonthlyContributionsForYear = 0d;
		Double totalOfAllMonthlyContributionsForYearEmplr = 0d;
		String lStrFinYearStartDate = null;
		String lStrFinYearEndDate = null;
		SgvcFinYearMst lObjSgvcFinYearMst = null;
		SgvcFinYearMst lObjSgvcFinYearMstForVoucher = null;
		MstEmp lObjMstEmp  = null;
		String lStrDdoCodeForEmp = null;
		String lStrArrearsLimitDate = null;
		Date lDateArrearsLimitDate = null;

		Long dcpsContributionYearlyId = null;
		MstDcpsContributionYearly lObjMstDcpsContributionYearly = null;
		MstDcpsContributionYearly MstDcpsContributionYearlyForInsertion = null;
		MstDcpsContributionYearly lObjMstDcpsContributionYearlyForCurrentYear = null;
		Integer NumberOfDaysInGivenYear = null;
		String lStrYearCodeForVoucher = null;
		
		Double openEmployee = 0d;
		Double openEmployer = 0d;
		Double openNet = 0d;
		Double openInt = 0d;
		Double openIntForYear = 0d;
		Double closeEmployee = 0d;
		Double closeEmployer = 0d;
		Double openIntPreviousYear = 0d;
		Double interestForCurrentYearEmployee = 0d;
		Double interestForCurrentYearEmployer = 0d;
		Double contributionForCurrentYearEmployee = 0d;
		Double contributionForCurrentYearEmployer = 0d;
		Double closeNet = 0d;
		
		String lStrPostEmpContriDoneOrNot = null;
		
		//variables added for tier 2
		List listArrearsDtlsForGivenEmp = null;
		Integer lIntDaysLeftForSixPCIntrstCalc = 0;
		Double lDoubleOpenBalanceTier2 = 0d;
		Double lDoubleCloseBalanceTier2 = 0d ;
		Double lArrearAmountTier2 = 0d ;
		Double lDoubleInterestTier2 = 0d ;
		Object[] lObjArrearsDtls = null;

		try {

			setSessionInfo(inputMap);

			InterestCalculationDAO lObjInterestCalculationDAO = new InterestCalculationDAOImpl(
					MstDcpsContributionYearly.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(
					SgvcFinYearMst.class, serv.getSessionFactory());
			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class, serv.getSessionFactory());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

			if (!StringUtility.getParameter("yearId", request)
					.equalsIgnoreCase("")) {
				yearId = Long.valueOf(StringUtility.getParameter("yearId",
						request).trim());
			}
			if (!StringUtility.getParameter("treasuryCode", request)
					.equalsIgnoreCase("")) {
				treasuryCode = Long.valueOf(StringUtility.getParameter(
						"treasuryCode", request));
			}
			
			String ddoCode = StringUtility.getParameter("ddoCode", request);

			lObjSgvcFinYearMst = (SgvcFinYearMst) lObjDcpsCommonDAO
					.read(yearId);
			lStrFinYearStartDate = lObjSgvcFinYearMst.getFromDate().toString()
					.trim();
			lStrFinYearEndDate = lObjSgvcFinYearMst.getToDate().toString()
					.trim();
			lDateFinYearEndDate = lObjSgvcFinYearMst.getToDate();
			lStrArrearsLimitDate =  gObjRsrcBndle.getString("DCPS.ArrearsLimitDateForInterestCalc");
			lStrArrearsLimitDate = lStrArrearsLimitDate.trim().concat(lObjSgvcFinYearMst.getFinYearCode()).trim();
			lDateArrearsLimitDate = sdf1.parse(lStrArrearsLimitDate);
			
			previousFinYearId = lObjSgvcFinYearMst.getPrevFinYearId();
			InterestRate = lObjInterestCalculationDAO
					.getInterestRateForGivenYear(lStrFinYearStartDate);
			
			GregorianCalendar grgcal = new GregorianCalendar();
			
			if (grgcal.isLeapYear(Integer.parseInt(lObjSgvcFinYearMst
					.getFinYearCode()) + 1)) {
				NumberOfDaysInGivenYear = 366;
			} else {
				NumberOfDaysInGivenYear = 365;
			}
			
			String lStrInterestFor = StringUtility.getParameter("interestFor",request).trim();
			if(lStrInterestFor.equals("Treasury") || lStrInterestFor.equals("DDO"))
			{
				listAllEmpsForIntrstCalc = lObjInterestCalculationDAO
					.getAllDCPSEmployeesForIntrstCalc(treasuryCode,ddoCode,
							lStrFinYearStartDate, lStrFinYearEndDate);
			}
			else if(lStrInterestFor.equals("Emp"))
			{
				String lStrEmpIds = StringUtility.getParameter("empIds", request).trim();
				String[] lStrArrdcpsEmpIds = lStrEmpIds.split("~");
				Long[] lLongArrdcpsEmpIds = new Long[lStrArrdcpsEmpIds.length];
				for (Integer lInt = 0; lInt < lStrArrdcpsEmpIds.length; lInt++) {
					lLongArrdcpsEmpIds[lInt] = Long.valueOf(lStrArrdcpsEmpIds[lInt]);
					if(lObjInterestCalculationDAO.checkEmployeeEligibleForIntrstCalc(treasuryCode, ddoCode, lLongArrdcpsEmpIds[lInt], lStrFinYearStartDate, lStrFinYearEndDate))
					{
						listAllEmpsForIntrstCalc.add(lLongArrdcpsEmpIds[lInt]);
					}
				}
			}

			Iterator ItrEmps = listAllEmpsForIntrstCalc.iterator();

			while (ItrEmps.hasNext()) {

				dcpsEmpId = Long.valueOf(ItrEmps.next().toString());
				dcpsId = lObjInterestCalculationDAO
						.getDcpsIdForEmpId(dcpsEmpId);
				lObjMstEmp = (MstEmp) lObjNewRegDdoDAO.read(dcpsEmpId) ;
				
				if(lObjMstEmp == null)
				{
					continue;
				}
				
				lStrDdoCodeForEmp = lObjMstEmp.getDdoCode();
				
				lObjMstDcpsContributionYearly = lObjInterestCalculationDAO
						.getContriYearlyVOForYear(dcpsEmpId,
								previousFinYearId);

				if (lObjMstDcpsContributionYearly != null) {
					
					openEmployee = lObjMstDcpsContributionYearly.getCloseEmp();
					openEmployer = lObjMstDcpsContributionYearly.getCloseEmplr();
					openIntPreviousYear = lObjMstDcpsContributionYearly.getOpenInt();
					lDoubleOpenBalanceTier2 = lObjMstDcpsContributionYearly.getCloseTier2();
					
				} else {
					
					openEmployee = 0d;
					openEmployer = 0d;
					openIntPreviousYear = 0d;
					lDoubleOpenBalanceTier2 = 0d;
				}

				openNet = openEmployee + openEmployer ;
				openIntForYear = (openNet + openIntPreviousYear) * InterestRate ;
				openInt = openIntForYear + openIntPreviousYear  ;

				lObjMstDcpsContributionYearlyForCurrentYear = lObjInterestCalculationDAO
						.getContriYearlyVOForYear(dcpsEmpId, yearId);

				// deleted from here

					listContriDtlsForGivenEmp = lObjInterestCalculationDAO
							.getContriDtlsForGivenEmployee(treasuryCode,
									lStrFinYearStartDate, lStrFinYearEndDate,
									dcpsEmpId);

					Iterator ItrContriDtlsForEmp = listContriDtlsForGivenEmp
							.iterator();

					totalOfAllMonthlyContributionsForYear = 0d;
					totalOfAllMonthlyContributionsForYearEmplr = 0d;
					interestForCurrentYearEmployee = 0d;
					interestForCurrentYearEmployer = 0d;
					
					while (ItrContriDtlsForEmp.hasNext()) {

						Object[] lObjVoucherDtls = (Object[]) ItrContriDtlsForEmp
								.next();

						lStrVoucherDate = lObjVoucherDtls[0].toString();
						lDoubleVoucherContriForMonth = Double.valueOf(lObjVoucherDtls[1].toString());
						
						if(lObjVoucherDtls[4] != null)
						{
							lStrPostEmpContriDoneOrNot = lObjVoucherDtls[4].toString();
						}
						if(null != lStrPostEmpContriDoneOrNot)
						{
							if(lStrPostEmpContriDoneOrNot.equals("Y"))
							{
								lDoubleVoucherContriForMonthEmplr = lDoubleVoucherContriForMonth;
							}
						}
						
						lDateContriFromDate = sdf.parse(lStrVoucherDate.trim());

					/*	lStrYearCodeForVoucher = sdf1.format(lDateContriFromDate).substring(6, 10);
						YearIdForVoucherDate = lObjInterestCalculationDAO.getYearIdForYearCode(lStrYearCodeForVoucher);
						lObjSgvcFinYearMstForVoucher = (SgvcFinYearMst) lObjDcpsCommonDAO.read(YearIdForVoucherDate);
						lDateContriToDate = sdf2.parse(lObjSgvcFinYearMstForVoucher.getToDate().toString().trim());
					*/
						
						DaysLeftTillYearEnd = daysBetween(lDateContriFromDate,lDateFinYearEndDate) + 1;
						
						//lDoubleVoucherContriForMonth = lDoubleVoucherContriForMonth +lDoubleVoucherContriForMonth ;

						interestForCurrentMonth = DaysLeftTillYearEnd
								* InterestRate * lDoubleVoucherContriForMonth
								* 0.01 / NumberOfDaysInGivenYear;
						
						interestForCurrentMonthEmplr = DaysLeftTillYearEnd
								* InterestRate * lDoubleVoucherContriForMonthEmplr
								* 0.01 / NumberOfDaysInGivenYear;

						//interestForCurrentYear = interestForCurrentYear + interestForCurrentMonth;
						
						interestForCurrentYearEmployee = interestForCurrentYearEmployee + interestForCurrentMonth ;
						interestForCurrentYearEmployer = interestForCurrentYearEmployer + interestForCurrentMonthEmplr ;

						totalOfAllMonthlyContributionsForYear = totalOfAllMonthlyContributionsForYear + lDoubleVoucherContriForMonth;
						totalOfAllMonthlyContributionsForYearEmplr = totalOfAllMonthlyContributionsForYearEmplr + lDoubleVoucherContriForMonthEmplr;

					}
					
					//interestForCurrentYearEmployee = interestForCurrentYear/2 ;
					//interestForCurrentYearEmployer = interestForCurrentYear/2 ;
					//contributionForCurrentYearEmployee = totalOfAllMonthlyContributionsForYear/2 ;
					//contributionForCurrentYearEmployer = totalOfAllMonthlyContributionsForYear/2 ;
					
					contributionForCurrentYearEmployee = totalOfAllMonthlyContributionsForYear;
					contributionForCurrentYearEmployer = totalOfAllMonthlyContributionsForYearEmplr;

					closeEmployee = openEmployee + interestForCurrentYearEmployee + contributionForCurrentYearEmployee ;
					closeEmployer = openEmployer + interestForCurrentYearEmployer + contributionForCurrentYearEmployer ;
					closeNet = closeEmployee + closeEmployer + openIntForYear;

					if (lObjMstDcpsContributionYearlyForCurrentYear == null) {
						
						MstDcpsContributionYearlyForInsertion = new MstDcpsContributionYearly();
						dcpsContributionYearlyId = IFMSCommonServiceImpl.getNextSeqNum("mst_dcps_contribution_yearly",inputMap);
						MstDcpsContributionYearlyForInsertion.setDcpsContributionYearlyId(dcpsContributionYearlyId);
					}
					else
					{
						//dcpsContributionYearlyId = lObjMstDcpsContributionYearlyForCurrentYear.getDcpsContributionYearlyId();
						//MstDcpsContributionYearlyForInsertion = (MstDcpsContributionYearly) lObjInterestCalculationDAO.read(dcpsContributionYearlyId);
						
						MstDcpsContributionYearlyForInsertion = lObjMstDcpsContributionYearlyForCurrentYear;
					}

					MstDcpsContributionYearlyForInsertion.setDcpsId(dcpsId);
					MstDcpsContributionYearlyForInsertion.setOpenNet(Round(openNet,2));
					MstDcpsContributionYearlyForInsertion.setOpenEmp(Round(openEmployee,2));
					MstDcpsContributionYearlyForInsertion.setOpenEmplr(Round(openEmployer,2));
					MstDcpsContributionYearlyForInsertion.setIntContribEmp(Round(interestForCurrentYearEmployee,2));
					MstDcpsContributionYearlyForInsertion.setIntContribEmplr(Round(interestForCurrentYearEmployer,2));
					MstDcpsContributionYearlyForInsertion.setContribEmp(Round(contributionForCurrentYearEmployee,2));
					MstDcpsContributionYearlyForInsertion.setContribEmplr(Round(contributionForCurrentYearEmployer,2));
					MstDcpsContributionYearlyForInsertion.setCloseEmp(Round(closeEmployee,2));
					MstDcpsContributionYearlyForInsertion.setCloseEmplr(Round(closeEmployer,2));
					MstDcpsContributionYearlyForInsertion.setCloseNet(Round(closeNet,2));
					MstDcpsContributionYearlyForInsertion.setOpenInt(Round(openInt,2));
					
					MstDcpsContributionYearlyForInsertion.setYearId(yearId);
					MstDcpsContributionYearlyForInsertion.setCurTreasuryCD(treasuryCode);
					MstDcpsContributionYearlyForInsertion.setCurDdoCD(lStrDdoCodeForEmp);
					
					// Code for Six PC Starts
					
					listArrearsDtlsForGivenEmp = lObjInterestCalculationDAO.getArrearsDtlsForGivenEmployee(dcpsEmpId,yearId);
					
					if(listArrearsDtlsForGivenEmp != null && listArrearsDtlsForGivenEmp.size()!= 0)
					{
						lObjArrearsDtls = (Object[]) listArrearsDtlsForGivenEmp.get(0);
						lArrearAmountTier2 = Double.parseDouble(lObjArrearsDtls[0].toString());
						lIntDaysLeftForSixPCIntrstCalc = daysBetween(lDateArrearsLimitDate,lDateFinYearEndDate) + 1;
						lDoubleInterestTier2 = lIntDaysLeftForSixPCIntrstCalc * InterestRate * lArrearAmountTier2 * 0.01 / NumberOfDaysInGivenYear;
						lDoubleCloseBalanceTier2 = lDoubleOpenBalanceTier2 + lArrearAmountTier2 + lDoubleInterestTier2 ;
					}
					
					MstDcpsContributionYearlyForInsertion.setOpenTier2(Round(lDoubleOpenBalanceTier2,2));
					MstDcpsContributionYearlyForInsertion.setContribTier2(Round(lArrearAmountTier2,2));
					MstDcpsContributionYearlyForInsertion.setIntContribTier2(Round(lDoubleInterestTier2,2));
					MstDcpsContributionYearlyForInsertion.setCloseTier2(Round(lDoubleCloseBalanceTier2,2));
					
					// Code for Six PC ends
					if (lObjMstDcpsContributionYearlyForCurrentYear == null) {
						
						MstDcpsContributionYearlyForInsertion.setCreatedDate(gDtCurDate);
						MstDcpsContributionYearlyForInsertion.setCreatedPostId(gLngPostId);
						MstDcpsContributionYearlyForInsertion.setCreatedUserId(gLngUserId);
						lObjInterestCalculationDAO.create(MstDcpsContributionYearlyForInsertion);
					}
					else
					{
						MstDcpsContributionYearlyForInsertion.setUpdatedDate(gDtCurDate);
						MstDcpsContributionYearlyForInsertion.setUpdatedPostId(gLngPostId);
						MstDcpsContributionYearlyForInsertion.setUpdatedUserId(gLngUserId);
						lObjInterestCalculationDAO.update(MstDcpsContributionYearlyForInsertion);
					}
					
			}
			
			/*
			//Code Added for SixPC Interest Calculation Previously added
			 *  MstDcpsSixPCInterestYearly lObjMstDcpsSixPCInterestYearlyForCurrentYear = null;
				MstDcpsSixPCInterestYearly lObjMstDcpsSixPCInterestYearlyforInsertion = null;
				Long dcpsSixPCInterestYearlyId = null;
			 * 
			 * 
			if(lStrInterestFor.equals("Treasury") || lStrInterestFor.equals("DDO"))
			{
				listAllEmpsForIntrstCalcSixPC = lObjInterestCalculationDAO.getAllDCPSEmployeesForIntrstCalcSixPC(treasuryCode,ddoCode);
			}
			else if(lStrInterestFor.equals("Emp"))
			{
				String lStrEmpIds = StringUtility.getParameter("empIds", request);
				String[] lStrArrdcpsEmpIds = lStrEmpIds.split("~");
				Long[] lLongArrdcpsEmpIds = new Long[lStrArrdcpsEmpIds.length];
				for (Integer lInt = 0; lInt < lStrArrdcpsEmpIds.length; lInt++) {
					lLongArrdcpsEmpIds[lInt] = Long.valueOf(lStrArrdcpsEmpIds[lInt]);
					listAllEmpsForIntrstCalcSixPC.add(lLongArrdcpsEmpIds[lInt]);
				}
			}
			Iterator ItrEmpsSixPC = listAllEmpsForIntrstCalcSixPC.iterator();
			MstDcpsSixPCInterestYearly lObjMstDcpsSixPCInterestYearly = null;
			Double lDoubleOpenInterest = 0d ;
			
			while (ItrEmpsSixPC.hasNext()) {

				dcpsEmpId = Long.valueOf(ItrEmpsSixPC.next().toString());
				dcpsId = lObjInterestCalculationDAO
						.getDcpsIdForEmpId(dcpsEmpId);
				lObjMstEmp = (MstEmp) lObjNewRegDdoDAO.read(dcpsEmpId) ;
				lStrDdoCodeForEmp = lObjMstEmp.getDdoCode();
				
				lObjMstDcpsSixPCInterestYearly = lObjInterestCalculationDAO
						.getSixPCYearlyInterestVOForYear(dcpsEmpId,
								previousFinYearId);

				if (lObjMstDcpsSixPCInterestYearly != null) {
					
					lDoubleOpenBalanceTier2 = lObjMstDcpsSixPCInterestYearly.getCloseBalance();
					
				} else {
					
					lDoubleOpenBalanceTier2 = 0d;
				}
				
				lObjMstDcpsSixPCInterestYearlyForCurrentYear = lObjInterestCalculationDAO.getSixPCYearlyInterestVOForYear(dcpsEmpId,yearId);
				
				if(lObjMstDcpsSixPCInterestYearlyForCurrentYear == null)
				{
						listArrearsDtlsForGivenEmp = lObjInterestCalculationDAO.getArrearsDtlsForGivenEmployee(dcpsEmpId,yearId);
		
						if(listArrearsDtlsForGivenEmp != null && listArrearsDtlsForGivenEmp.size()!=0)
						{
							lObjArrearsDtls = (Object[]) listArrearsDtlsForGivenEmp.get(0);
							lArrearAmountTier2 = Double.parseDouble(lObjArrearsDtls[0].toString());
							lDoubleOpenInterest = InterestRate * lDoubleOpenBalanceTier2  / 100;
							
							lIntDaysLeftForSixPCIntrstCalc = daysBetween(lDateArrearsLimitDate,lDateFinYearEndDate);
							
							lDoubleInterestTier2 = lIntDaysLeftForSixPCIntrstCalc
							* InterestRate * lArrearAmountTier2
							* 0.01 / NumberOfDaysInGivenYear;
							
							// lDoubleOpenInterest wont be there.
							
							lDoubleCloseBalanceTier2 = lDoubleOpenBalanceTier2 + lArrearAmountTier2 + lDoubleInterestTier2 + lDoubleOpenInterest ;
							
							dcpsSixPCInterestYearlyId = IFMSCommonServiceImpl.getNextSeqNum("mst_dcps_sixpc_interest_yearly",inputMap);
							lObjMstDcpsSixPCInterestYearlyforInsertion = new  MstDcpsSixPCInterestYearly();
							lObjMstDcpsSixPCInterestYearlyforInsertion.setDcpsSixPCInterestYearlyId(dcpsSixPCInterestYearlyId);
							lObjMstDcpsSixPCInterestYearlyforInsertion.setDcpsId(dcpsId);
							lObjMstDcpsSixPCInterestYearlyforInsertion.setYearId(yearId);
							lObjMstDcpsSixPCInterestYearlyforInsertion.setCurTreasuryCD(treasuryCode);
							lObjMstDcpsSixPCInterestYearlyforInsertion.setCurDdoCD(lStrDdoCodeForEmp);
							lObjMstDcpsSixPCInterestYearlyforInsertion.setOpenBalance(Round(lDoubleOpenBalanceTier2,2));
							lObjMstDcpsSixPCInterestYearlyforInsertion.setArrearAmount(Round(lArrearAmountTier2,2));
							lObjMstDcpsSixPCInterestYearlyforInsertion.setInterest(Round(lDoubleInterestTier2,2));
							lObjMstDcpsSixPCInterestYearlyforInsertion.setOpenInterest(Round(lDoubleOpenInterest,2));
							lObjMstDcpsSixPCInterestYearlyforInsertion.setCloseBalance(Round(lDoubleCloseBalanceTier2,2));
							
							lObjMstDcpsSixPCInterestYearlyforInsertion.setCreatedDate(gDtCurDate);
							lObjMstDcpsSixPCInterestYearlyforInsertion.setCreatedPostId(gLngPostId);
							lObjMstDcpsSixPCInterestYearlyforInsertion.setCreatedUserId(gLngUserId);
	
							lObjInterestCalculationDAO.create(lObjMstDcpsSixPCInterestYearlyforInsertion);
						}
				}
				
			}
			
			//Code for SixPC Interest Calculation Ends Previously added.
			*/

			lBlFlag = true;

			String lSBStatus = getResponseXMLDoc(lBlFlag,lStrInterestFor).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");

			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error in getDigiSig " + e, e);
		}

		return resObj;

	}

	public static Integer daysBetween(Date sPassedDate, Date ePassedDate) {

		Calendar sDate = Calendar.getInstance();
		sDate.setTime(sPassedDate);
		Calendar eDate = Calendar.getInstance();
		eDate.setTime(ePassedDate);

		Calendar d = (Calendar) sDate.clone();
		Integer dBetween = 0;
		while (d.before(eDate)) {
			d.add(Calendar.DAY_OF_MONTH, 1);
			dBetween++;
		}
		return dBetween;
	}

	private StringBuilder getResponseXMLDoc(Boolean flag,String lStrInterestFor) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("<InterestFor>");
		lStrBldXML.append(lStrInterestFor);
		lStrBldXML.append("</InterestFor>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
	
	public static double Round(double Rval, int Rpl) {
		  double p = (double)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  double tmp = Math.round(Rval);
		  return (double)tmp/p;
		  }

}
