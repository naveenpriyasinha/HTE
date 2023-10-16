package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.BillEdpDAO;
import com.tcs.sgv.common.dao.BillEdpDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.SgvaMonthMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.BudgetDtlsDAO;
import com.tcs.sgv.pension.dao.BudgetDtlsDAOImpl;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionPsbDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionPsbDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.MonthlyPensionBillVO;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeChargable;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionPsbDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class PSBMonthlyServiceImpl extends ServiceImpl implements PSBMonthlyService
{
	
	/* Global variable for Logger class */	
	private Log gLogger = LogFactory.getLog(getClass());

	/* Global Variable for PostId */
	private Long gLngPostId = null;
	
	/* Global Variable for UserId */
	private Long gLngUserId = null;

	/* Global Variable for LangId */
	private Long gLngLangId = null;

	/* Global Variable for DB Id */
	private Long gLngDBId = null;

	/* Global Variable for Location Code */
	private String gStrLocationCode = null;
	
	/* Global Variable for Current Date */
    Date gDtCurrDt = null;
	
	public ResultObject displayPSBMonthly(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		String lStrSelectedBranchCode = "";
		String lStrSelectedMonth = "";
		String lStrSelectedYear = "";
		String detialsFlag = "false";
		try
		{
			String lStrLangId = SessionHelper.getLangId(inputMap).toString();
			String lStrLocationtioncode = SessionHelper.getLocationCode(inputMap);			
			CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(servLoc.getSessionFactory());			
			//To Populate Branch combo
			List branchList = lObjCommonPensionDAO.getAllBranchsForLocation(lStrLangId,lStrLocationtioncode);
			inputMap.put("BranchNamesVOArray", branchList);			
			lStrSelectedBranchCode = (String)StringUtility.getParameter("cmbBankandBranchName",request);
			inputMap.put("BranchNamesVOArray", branchList);
			
			if(lStrSelectedBranchCode != "-1")
			{
				inputMap.put("selectedBranchCode", lStrSelectedBranchCode);
			}
			// To Populate Month combo
			setSessionInfo(inputMap);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String currDate = sdf.format(gDtCurrDt);		
			
			String currentMonth = currDate.substring(5, 7);
			String currentYear = currDate.substring(0,4);	
			
			inputMap.put("CurrentMonth", currentMonth);
			inputMap.put("CurrentYear", currentYear);
			
			List<SgvaMonthMst> lObjSgvaMonthMstList=null;						
			lObjSgvaMonthMstList = lObjCommonPensionDAO.getSgvaMonthMstVO(lStrLangId);	
			if(lObjSgvaMonthMstList != null)
			{
            	inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMstList);
            }
			lStrSelectedMonth = (String)StringUtility.getParameter("cmbMonth",request);
			inputMap.put("selectedMonth", lStrSelectedMonth);
			
			// To Populate Year combo
			List<SgvcFinYearMst> lObjSgvcFinYearMstList=null;			
			lObjSgvcFinYearMstList = lObjCommonPensionDAO.getSgvcFinYearMstVO(lStrLangId);	
			if(lObjSgvcFinYearMstList != null)
			{
            	inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMstList);
            }
			lStrSelectedYear = (String)StringUtility.getParameter("cmbYear",request);
			inputMap.put("selectedYear", lStrSelectedYear);
			inputMap.put("detialsFlag", detialsFlag);
			resObj.setResultValue(inputMap);
			resObj.setViewName("displayPsbMonthly");
		}
		catch (Exception e)
		{
        	gLogger.error(" Error is : " + e, e);
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }  
		return resObj;
	}
	
	public ResultObject getPSBPaymentDetails(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		String lStrPPONO = "";
		String lStrBranchCode="";
		String lStrbankCode = "";
		String errorMsg = "";		
		String pensionerName = "";
		String headCode = "";
		String lStrHeadDesc = "";
		String lStrMonth = "";
		String lStrYear = "";
		String lStrDate = "";
		String detialsFlag = "false";	
		String lStrPensionerCode = "";
		long lLngPensionReqId = 0;
		ArrayList arrPaymentDetails = new ArrayList();
		ArrayList arrActualPaymentDetails = new ArrayList();
		ArrayList arrMonthYear = new ArrayList();
		ArrayList<Integer> month =  new ArrayList<Integer>(); 
		ArrayList<Integer> year =  new ArrayList<Integer>(); 
		
		Double lStrArrearAmt = 0.00;
		Double lStrBPAmt = 0.00;
		Double lStrDPAmt = 0.00;
		Double lStrITCutAmt = 0.00;
		Double lStrMAAmt = 0.00;
		Double lStrPensionCutAmt = 0.00;
		Double lStrRecoveryAmt = 0.00;
		Double lStrSpecialCutAmt = 0.00;
		Double lStrTIAmt = 0.00;
		Double lStrCVPMonthly = 0.00;
		Double lStrNetAmount = 0.00;
		Double lStrPersonalPension = 0.00;
		Double lStrIR = 0.00;
		
		Double lStrActualAmount = 0.00;
		try
		{			
			lStrPPONO = StringUtility.getParameter("txtPpono", request);
			lStrBranchCode = StringUtility.getParameter("cmbBankandBranchName", request);	
			lStrMonth = StringUtility.getParameter("cmbMonth", request);
			if(Integer.parseInt(lStrMonth) < 10 )
			{
				lStrMonth= "0"+lStrMonth;
			}
			lStrYear = StringUtility.getParameter("cmbYear", request);
			
			if(lStrPPONO != "" && lStrBranchCode != "-1")
			{
				CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(servLoc.getSessionFactory());
				
				TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
				TrnPensionPsbDtlsDAO lObjTrnPensionPsbDtlsDAO = new TrnPensionPsbDtlsDAOImpl(TrnPensionPsbDtls.class,servLoc.getSessionFactory());
				
				errorMsg = lObjTrnPensionRqstHdrDAO.validatePPONumberForScheme(lStrPPONO);			
				if(errorMsg.equals(""))
				{
					errorMsg = lObjTrnPensionRqstHdrDAO.validatePPONumberForBank(lStrPPONO,lStrBranchCode);			
					if(errorMsg.equals(""))
					{
						errorMsg = lObjTrnPensionPsbDtlsDAO.validatePPONumberForMonth(lStrPPONO,Integer.parseInt(lStrYear+lStrMonth));
						if(errorMsg.equals(""))
						{					
							Map resultValueMap = lObjTrnPensionRqstHdrDAO.getPaymentDetails(lStrPPONO);
														
							List lTemp = lObjTrnPensionRqstHdrDAO.getPensionRqstHdrCodeId(lStrPPONO,"Continue");
							Object[] lObjData = null;
							
							if(lTemp != null && lTemp.size() > 0)
				            {
				            	lObjData = (Object[]) lTemp.get(0);	        	
				            	lLngPensionReqId = Long.parseLong(lObjData[0].toString());
				            	lStrPensionerCode = lObjData[1].toString();	        	
				            }
							
							Map resultValueMap1 = lObjTrnPensionRqstHdrDAO.getActualPaymentDetails(lLngPensionReqId,lStrPensionerCode);
														
							pensionerName = (String)resultValueMap.get("pensionerName");
							headCode = resultValueMap.get("headCode").toString();
							lStrHeadDesc = (String)resultValueMap.get("headCodeDesc");
							arrPaymentDetails = (ArrayList)resultValueMap.get("paymentDetailsList");
							arrActualPaymentDetails = (ArrayList)resultValueMap1.get("ActualPaymentDetailsList");
							detialsFlag = "true";
							MonthlyCases lObjMonthlyCases = new MonthlyCases();
							
							lStrPensionerCode = lObjCommonPensionDAO.getPensionerCodefromPpoNo(lStrPPONO);
							
							lStrbankCode = lObjCommonPensionDAO.getBankCodeForPensioner(lStrPensionerCode,lStrBranchCode);
							
							inputMap.put("Bank",lStrbankCode);
				        	inputMap.put("Branch",lStrBranchCode);
				        	inputMap.put("HeadCode",headCode);
				        	inputMap.put("Month",lStrMonth);
				        	inputMap.put("Year",lStrYear);
				        	inputMap.put("Scheme","PSB");
				        	inputMap.put("ForMonth",lStrMonth);
				        	inputMap.put("ForYear",lStrYear);
				        	
				        	inputMap.put("HeadDesc",lStrHeadDesc);
				        	
				        	arrMonthYear = (ArrayList)resultValueMap.get("MonthYearList");
				        	
				        	for(int i=0;i<arrPaymentDetails.size();i++)
			        		{
				        		lStrDate = arrMonthYear.get(i).toString();
				        		year.add(Integer.parseInt(lStrDate.substring(0,4)));
								month.add(Integer.parseInt(lStrDate.substring(4,6)));
			        		}
				        	
				        	inputMap.put("YearList", year);
				        	inputMap.put("MonthList", month);
				        
				        	lStrDate = lStrYear+lStrMonth;
				        	inputMap.put("Date",lStrDate);
				        	inputMap.put("ArrComputeFlag","N");
				        	inputMap.put("FPFlag", "N");
				        	
				        	inputMap = lObjMonthlyCases.computeMonthlyPension(inputMap, lStrPensionerCode);
                            List<MonthlyPensionBillVO> lObjMonthlyPensionVO = (List<MonthlyPensionBillVO>)inputMap.get("lLstMonthlyPensionBillVO");
                                                       
                            
                            if(lObjMonthlyPensionVO.size() > 0 && lObjMonthlyPensionVO != null)
				        	{
                            	lStrActualAmount = Double.valueOf(lObjMonthlyPensionVO.get(0).getBasicPensionAmount().toString());
                            	lStrArrearAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getTIArrearsAmount().toString())+Double.valueOf(lObjMonthlyPensionVO.get(0).getOtherArrearsAmount().toString());
                            	lStrBPAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getBasicPensionAmount().toString());
                            	lStrDPAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getDpPercentAmount().toString());
                            	lStrITCutAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getItCutAmount().toString());
                            	lStrMAAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getMedicalAllowenceAmount().toString());
                            	lStrPensionCutAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getPensionCutAmount().toString());
                            	lStrRecoveryAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getRecoveryAmount().toString());
                            	lStrSpecialCutAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getSpecialCutAmount().toString());
                            	lStrTIAmt = Double.valueOf(lObjMonthlyPensionVO.get(0).getTiPercentAmount().toString());
                            	lStrCVPMonthly = Double.valueOf(lObjMonthlyPensionVO.get(0).getCvpMonthlyAmount().toString());
                            	lStrPersonalPension = Double.valueOf(lObjMonthlyPensionVO.get(0).getPersonalPension().toString());
                            	lStrIR = Double.valueOf(lObjMonthlyPensionVO.get(0).getIr().toString());                            	
                            	lStrNetAmount = Double.valueOf(lObjMonthlyPensionVO.get(0).getNetPensionAmount().toString());
				        	}                            
				        	inputMap.put("ActualAmount",lStrActualAmount);
				        	inputMap.put("ArrearAmount",lStrArrearAmt);
				        	inputMap.put("BasicPensionAmount",lStrBPAmt);
				        	inputMap.put("DPAmount",lStrDPAmt);
				        	inputMap.put("ITCutAmount",lStrITCutAmt);
				        	inputMap.put("MAAmount",lStrMAAmt);
				        	inputMap.put("PensionCutAmount",lStrPensionCutAmt);
				        	inputMap.put("RecoveryAmount",lStrRecoveryAmt);
				        	inputMap.put("SpecialCutAmount",lStrSpecialCutAmt);
				        	inputMap.put("TIAmount",lStrTIAmt);
				        	inputMap.put("CVPMonthly",lStrCVPMonthly);
				        	inputMap.put("PersonalPensionAmount",lStrPersonalPension);
				        	inputMap.put("IRAmount",lStrIR);
				        	inputMap.put("NetAmount",lStrNetAmount);
						}
					}
				}		
			}
			inputMap.put("errorMsg", errorMsg);
			inputMap.put("pensionerName", pensionerName);
			inputMap.put("headCode", headCode);
			inputMap.put("ppoNo",lStrPPONO);
			inputMap.put("paymentDetailsList", arrPaymentDetails);
			inputMap.put("ActualPaymentDetailsList", arrActualPaymentDetails);
			inputMap.put("detialsFlag", detialsFlag);
			resObj.setResultValue(inputMap);
			resObj.setViewName("displayPsbMonthly");			
		}
		catch (Exception e)
		{
        	gLogger.error(" Error is : " + e, e);
        	e.printStackTrace();
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }  
		return resObj;
	}
	
	public ResultObject savePSBPaymentDetails(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		String lStrPPONO = "";
		String lStrBranchCode="";
		String lStrMonth = "";
		String lStrYear = "";
		String errorMsg = "";	
		BigDecimal lActualPayment = null;
		BigDecimal lBankPayment = null;
		BigDecimal LessPayment = null;
		BigDecimal ExcessPayment = null;
		BigDecimal lDifferenceAmount = null;
		
		BigDecimal lBasicPension = null;
		BigDecimal lBankITCut = null;
		BigDecimal lBankPensionCut = null;
		BigDecimal lBankSpecialCut = null;
		BigDecimal lBankDP = null;
		BigDecimal lBankTI = null;
		BigDecimal lBankMA = null;
		BigDecimal lBankArrear = null;
		BigDecimal lBankRecovery = null;
		BigDecimal lBankCVPMonthly = null;
		BigDecimal lActualPaymentPSB = null;
		BigDecimal lBankPersonalPension = null;
		BigDecimal lBankIR = null;
		
		BigDecimal lActITCut = null;
		BigDecimal lActPensionCut = null;
		BigDecimal lActSpecialCut = null;
		BigDecimal lActDP = null;
		BigDecimal lActTI = null;
		BigDecimal lActMA = null;
		BigDecimal lActArrear = null;
		BigDecimal lActRecovery = null;
		BigDecimal lActPersonalPension = null;
		BigDecimal lActIR = null;		
		
		String lStrPensionerCode = "";
		
		String lStrPensionerName = "";
		BigDecimal lBDHeadCode = null;
		BigDecimal lUserId=null;
		BigDecimal lPostId= null;
		BigDecimal lDbId = null;
		String lStrLocationcode= "";
		BigDecimal lVoucherNo = new BigDecimal(0);
		TrnPensionPsbDtls lObjTrnPensionPsbDtls;
		TrnPensionBillDtls lObjTrnPensionBillDtls;
		TrnPensionBillHdr lObjTrnPensionBillHdr;
		TrnPensionPsbDtlsDAO lObjTrnPensionPsbDtlsDAO = new TrnPensionPsbDtlsDAOImpl(TrnPensionPsbDtls.class,servLoc.getSessionFactory());
		TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class,servLoc.getSessionFactory());
		TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,servLoc.getSessionFactory());
		try
		{
			lStrPPONO = StringUtility.getParameter("txtPpono",request);
			lStrBranchCode = StringUtility.getParameter("cmbBankandBranchName",request);
			lStrMonth = StringUtility.getParameter("txtMonthNo",request);
			
			if(Integer.parseInt(lStrMonth) < 10 )
			{
				lStrMonth= "0"+lStrMonth;
			}
			
			lStrYear = StringUtility.getParameter("txtYearCode", request);
			
			String lStrActualPayment = StringUtility.getParameter("ActualPayment", request);			
			if(lStrActualPayment != null && lStrActualPayment.trim().length() > 0)
			{
				lActualPayment = new BigDecimal(lStrActualPayment.trim());
			}
			else
			{
				lActualPayment = new BigDecimal(0);
			}			
			
			String lStrActualPaymentPSB = StringUtility.getParameter("NetAmount", request);			
			if(lStrActualPaymentPSB != null && lStrActualPaymentPSB.trim().length() > 0)
			{
				lActualPaymentPSB = new BigDecimal(lStrActualPaymentPSB.trim());
			}
			else
			{
				lActualPaymentPSB = new BigDecimal(0);
			}
			
			String lStrBankPayment = StringUtility.getParameter("BankTotal",request);
			if(lStrBankPayment != null && lStrBankPayment.trim().length() > 0)
			{
				lBankPayment = new BigDecimal(lStrBankPayment.trim());
			}
			else
			{
				lBankPayment = new BigDecimal(0);
			}
			
			String lStrLessPayment = StringUtility.getParameter("LessPayment", request);
			if(lStrLessPayment != null && lStrLessPayment.trim().length() > 0)
			{
				LessPayment = new BigDecimal(lStrLessPayment.trim());
			}
			else
			{
				LessPayment = new BigDecimal(0);
			}
			
			String lStrExcessPayment = StringUtility.getParameter("ExcessPayment", request);
			if(lStrExcessPayment != null && lStrExcessPayment.trim().length() > 0)
			{
				ExcessPayment = new BigDecimal(lStrExcessPayment.trim());
			}
			else
			{
				ExcessPayment = new BigDecimal(0);
			}
			
			String lStrDifferenceAmount = StringUtility.getParameter("DifferenceAmount", request);
			if(lStrDifferenceAmount != null && lStrDifferenceAmount.trim().length() > 0)
			{
				lDifferenceAmount = new BigDecimal(lStrDifferenceAmount.trim());
			}
			else
			{
				lDifferenceAmount = new BigDecimal(0);
			}
			
			String lStrBasicPension = StringUtility.getParameter("txtBankPayment", request);
			if(lStrBasicPension != null && lStrBasicPension.trim().length() > 0)
			{
				lBasicPension = new BigDecimal(lStrBasicPension.trim());
			}
			else
			{
				lBasicPension = new BigDecimal(0);
			}
			
			String lStrBankITCut = StringUtility.getParameter("BankITCut", request);
			if(lStrBankITCut != null && lStrBankITCut.trim().length() > 0)
			{
				lBankITCut = new BigDecimal(lStrBankITCut.trim());
			}
			else
			{
				lBankITCut = new BigDecimal(0);
			}
			
			String lStrBankPensionCut = StringUtility.getParameter("BankPensionCut", request);
			if(lStrBankPensionCut != null && lStrBankPensionCut.trim().length() > 0)
			{
				lBankPensionCut = new BigDecimal(lStrBankPensionCut.trim());
			}
			else
			{
				lBankPensionCut = new BigDecimal(0);
			}
			
			String lStrBankCVPMonthly = StringUtility.getParameter("BankCVPMonthly",request);
			if(lStrBankCVPMonthly != null && lStrBankCVPMonthly.trim().length() > 0)
			{
				lBankCVPMonthly = new BigDecimal(lStrBankCVPMonthly.trim());
			}
			else
			{
				lBankCVPMonthly = new BigDecimal(0);
			}
			
			String lStrBankSpecialCut = StringUtility.getParameter("BankSpecialCut", request);
			if(lStrBankSpecialCut != null && lStrBankSpecialCut.trim().length() > 0)
			{
				lBankSpecialCut = new BigDecimal(lStrBankSpecialCut.trim());
			}
			else
			{
				lBankSpecialCut = new BigDecimal(0);
			}
			
			String lStrBankDP = StringUtility.getParameter("BankDP", request);
			if(lStrBankDP != null && lStrBankDP.trim().length() > 0)
			{
				lBankDP = new BigDecimal(lStrBankDP.trim());
			}
			else
			{
				lBankDP = new BigDecimal(0);
			}
			
			String lStrBankTI = StringUtility.getParameter("BankTI", request);
			if(lStrBankTI != null && lStrBankTI.trim().length() > 0)
			{
				lBankTI = new BigDecimal(lStrBankTI.trim());
			}
			else
			{
				lBankTI = new BigDecimal(0);
			}
			
			String lStrBankMA = StringUtility.getParameter("BankMA", request);
			if(lStrBankMA != null && lStrBankMA.trim().length() > 0)
			{
				lBankMA = new BigDecimal(lStrBankMA.trim());
			}
			else
			{
				lBankMA = new BigDecimal(0);
			}
			
			String lStrBankArrear = StringUtility.getParameter("BankArrear", request);
			if(lStrBankArrear != null && lStrBankArrear.trim().length() > 0)
			{
				lBankArrear = new BigDecimal(lStrBankArrear.trim());
			}
			else
			{
				lBankArrear = new BigDecimal(0);
			}
			
			String lStrBankRecovery = StringUtility.getParameter("BankRecovery", request);
			if(lStrBankRecovery != null && lStrBankRecovery.trim().length() > 0)
			{
				lBankRecovery = new BigDecimal(lStrBankRecovery.trim());
			}
			else
			{
				lBankRecovery = new BigDecimal(0);
			}
			
			String lStrBankPersonalPension = StringUtility.getParameter("BankPersonalPensionAmount", request);
			if(lStrBankPersonalPension != null && lStrBankPersonalPension.trim().length() > 0)
			{
				lBankPersonalPension = new BigDecimal(lStrBankPersonalPension.trim());
			}
			else
			{
				lBankPersonalPension = new BigDecimal(0);
			}
			
			String lStrBankIR = StringUtility.getParameter("BankIRAmount", request);
			if(lStrBankIR != null && lStrBankIR.trim().length() > 0)
			{
				lBankIR = new BigDecimal(lStrBankIR.trim());
			}
			else
			{
				lBankIR = new BigDecimal(0);
			}
			
			String lStrActITCut = StringUtility.getParameter("ActualITCutAmount", request);
			if(lStrActITCut != null && lStrActITCut.trim().length() > 0)
			{
				lActITCut = new BigDecimal(lStrActITCut.trim());
			}
			else
			{
				lActITCut = new BigDecimal(0);
			}
			
			String lStrActPensionCut = StringUtility.getParameter("ActualPensionCutAmount", request);
			if(lStrActPensionCut != null && lStrActPensionCut.trim().length() > 0)
			{
				lActPensionCut = new BigDecimal(lStrActPensionCut.trim());
			}
			else
			{
				lActPensionCut = new BigDecimal(0);
			}
			
			String lStrActSpecialCut = StringUtility.getParameter("ActualSpecialCutAmount", request);
			if(lStrActSpecialCut != null && lStrActSpecialCut.trim().length() > 0)
			{
				lActSpecialCut = new BigDecimal(lStrActSpecialCut.trim());
			}
			else
			{
				lActSpecialCut = new BigDecimal(0);
			}
			
			String lStrActDP = StringUtility.getParameter("ActualDPAmount", request);
			if(lStrActDP != null && lStrActDP.trim().length() > 0)
			{
				lActDP = new BigDecimal(lStrActDP.trim());
			}
			else
			{
				lActDP = new BigDecimal(0);
			}
			
			String lStrActTI = StringUtility.getParameter("ActualTIAmount", request);
			if(lStrActTI != null && lStrActTI.trim().length() > 0)
			{
				lActTI = new BigDecimal(lStrActTI.trim());
			}
			else
			{
				lActTI = new BigDecimal(0);
			}
			
			String lStrActMA = StringUtility.getParameter("ActualMAAmount", request);
			if(lStrActMA != null && lStrActMA.trim().length() > 0)
			{
				lActMA = new BigDecimal(lStrActMA.trim());
			}
			else
			{
				lActMA = new BigDecimal(0);
			}
			
			String lStrActArrear = StringUtility.getParameter("ActualArrearAmount", request);
			if(lStrActArrear != null && lStrActArrear.trim().length() > 0)
			{
				lActArrear = new BigDecimal(lStrActArrear.trim());
			}
			else
			{
				lActArrear = new BigDecimal(0);
			}
			
			String lStrActRecovery = StringUtility.getParameter("ActualRecoveryAmount", request);
			if(lStrActRecovery != null && lStrActRecovery.trim().length() > 0)
			{
				lActRecovery = new BigDecimal(lStrActRecovery.trim());
			}
			else
			{
				lActRecovery = new BigDecimal(0);
			}
			
			String lStrActPersonalPension = StringUtility.getParameter("ActPersonalPensionAmount", request);
			if(lStrActPersonalPension != null && lStrActPersonalPension.trim().length() > 0)
			{
				lActPersonalPension = new BigDecimal(lStrActPersonalPension.trim());
			}
			else
			{
				lActPersonalPension = new BigDecimal(0);
			}
			
			String lStrActIR = StringUtility.getParameter("ActIRAmount", request);
			if(lStrActIR != null && lStrActIR.trim().length() > 0)
			{
				lActIR = new BigDecimal(lStrActIR.trim());
			}
			else
			{
				lActIR = new BigDecimal(0);
			}
			
			
			lUserId = new BigDecimal(SessionHelper.getUserId(inputMap)); 
			lPostId = new BigDecimal(SessionHelper.getPostId(inputMap));
			lDbId = new BigDecimal(SessionHelper.getDbId(inputMap));
			lStrLocationcode = SessionHelper.getLocationCode(inputMap);
			
			String lStrHeadCode = StringUtility.getParameter("headCode", request);
			if(lStrHeadCode != null && lStrHeadCode.trim().length() > 0)
			{
				lBDHeadCode = new BigDecimal(lStrHeadCode.trim());
			}
			else
			{
				lBDHeadCode = new BigDecimal(0);
			}
			
			lObjTrnPensionPsbDtls = new TrnPensionPsbDtls();
			lObjTrnPensionPsbDtls.setActualPayment(lActualPaymentPSB);
			lObjTrnPensionPsbDtls.setBankCode(lStrBranchCode);
			lObjTrnPensionPsbDtls.setBankPayment(lBankPayment);
			lObjTrnPensionPsbDtls.setCreatedDate(new Date());
			lObjTrnPensionPsbDtls.setCreatedPostId(lPostId);
			lObjTrnPensionPsbDtls.setCreatedUserId(lUserId);
			lObjTrnPensionPsbDtls.setDbId(lDbId);
			lObjTrnPensionPsbDtls.setExcessPayment(ExcessPayment);
			lObjTrnPensionPsbDtls.setLessPayment(LessPayment);
			lObjTrnPensionPsbDtls.setDifferenceAmount(lDifferenceAmount);
			lObjTrnPensionPsbDtls.setLocationCode(lStrLocationcode);
			lObjTrnPensionPsbDtls.setMonthYear(Integer.parseInt(lStrYear+lStrMonth));
			lObjTrnPensionPsbDtls.setPpoNo(lStrPPONO);
			lObjTrnPensionPsbDtls.setBasicPension(lBasicPension);
			lObjTrnPensionPsbDtls.setArrearAmount(lBankArrear);
			lObjTrnPensionPsbDtls.setDpAmount(lBankDP);
			lObjTrnPensionPsbDtls.setIncomeTaxCutAmount(lBankITCut);
			lObjTrnPensionPsbDtls.setMedicalAllowenceAmount(lBankMA);
			lObjTrnPensionPsbDtls.setPensnCutAmount(lBankPensionCut);
			lObjTrnPensionPsbDtls.setRecoveryAmount(lBankRecovery);
			lObjTrnPensionPsbDtls.setSpecialCutAmount(lBankSpecialCut);
			lObjTrnPensionPsbDtls.setTiAmount(lBankTI);
			lObjTrnPensionPsbDtls.setCvpMonthlyAmount(lBankCVPMonthly);
			lObjTrnPensionPsbDtls.setPersonalPensionAmount(lBankPersonalPension);
			lObjTrnPensionPsbDtls.setIrAmount(lBankIR);
			Long templ = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_psb_dtls", inputMap);
			lObjTrnPensionPsbDtls.setPsbDtlId(templ);
			lObjTrnPensionPsbDtls.setVoucherNo(new BigDecimal(0));
			
			lObjTrnPensionPsbDtlsDAO.create(lObjTrnPensionPsbDtls);
			
			lObjTrnPensionBillHdr = new TrnPensionBillHdr();
			lObjTrnPensionBillHdr.setBillDate(new Date());
			lObjTrnPensionBillHdr.setBillType("PENSION");
			lObjTrnPensionBillHdr.setBranchCode(lStrBranchCode);
			lObjTrnPensionBillHdr.setForMonth(Integer.parseInt(lStrYear+lStrMonth));
			lObjTrnPensionBillHdr.setHeadCode(lBDHeadCode);
			lObjTrnPensionBillHdr.setScheme("PSB");
			lObjTrnPensionBillHdr.setCreatedDate(new Date());
			lObjTrnPensionBillHdr.setCreatedPostId(lPostId);
			lObjTrnPensionBillHdr.setCreatedUserId(lUserId);
			Long templ1 = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", inputMap);
			lObjTrnPensionBillHdr.setTrnPensionBillHdrId(templ1);
			
			lObjTrnPensionBillHdrDAO.create(lObjTrnPensionBillHdr);
			
			
			lObjTrnPensionBillDtls = new TrnPensionBillDtls();
			lObjTrnPensionBillDtls.setArrearAmount(lActArrear);
			lObjTrnPensionBillDtls.setDpAmount(lActDP);
			lObjTrnPensionBillDtls.setIncomeTaxCutAmount(lActITCut);
			lObjTrnPensionBillDtls.setMedicalAllowenceAmount(lActMA);
			lObjTrnPensionBillDtls.setPensnCutAmount(lActPensionCut);
			lObjTrnPensionBillDtls.setSpecialCutAmount(lActSpecialCut);
			lObjTrnPensionBillDtls.setRecoveryAmount(lActRecovery);
			lObjTrnPensionBillDtls.setPensionAmount(lActualPayment);
			lObjTrnPensionBillDtls.setTiAmount(lActTI);
			lObjTrnPensionBillDtls.setPersonalPensionAmount(lActPersonalPension);
			lObjTrnPensionBillDtls.setIrAmount(lActIR);
			lObjTrnPensionBillDtls.setTrnPensionBillHdrId(templ1);
			TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
			List lTemp = lObjTrnPensionRqstHdrDAO.getPensionRqstHdrCodeId(lStrPPONO,"Continue");
			Object[] lObjData = null;
			
			if(lTemp != null && lTemp.size() > 0)
            {
            	lObjData = (Object[]) lTemp.get(0);
            	lStrPensionerCode = lObjData[1].toString();	        	
            }
			
			lObjTrnPensionBillDtls.setPensionerCode(lStrPensionerCode);
			lObjTrnPensionBillDtls.setPpoNo(lStrPPONO);
			Long templ2 = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", inputMap);
			lObjTrnPensionBillDtls.setTrnPensionBillDtlsId(templ2);
			
			lObjTrnPensionBillDtlsDAO.create(lObjTrnPensionBillDtls);
			
			inputMap.put("errorMsg", errorMsg);
			inputMap.put("pensionerName", lStrPensionerName);
			inputMap.put("headCode", lBDHeadCode);
			lStrPPONO="";
			inputMap.put("ppoNo",lStrPPONO);
			resObj.setResultValue(inputMap);
			resObj.setViewName("displayPsbMonthly");			
		}
		catch (Exception e)
		{
        	gLogger.error(" Error is : " + e, e);
        	e.printStackTrace();
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }  
		return resObj;
	}
	
	public ResultObject displayHeadVoucherScreen(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		String fromMonthYear="-1";		
		String toMonthYear="-1";		
		String headCode="-1";
		String branchCode="-1";
		ArrayList arrHeadVouchers = new ArrayList();	
		TrnPensionPsbDtlsDAO lObjTrnPensionPsbDtlsDAO = new TrnPensionPsbDtlsDAOImpl(TrnPensionPsbDtls.class,servLoc.getSessionFactory());
		
		
		try
		{	
			fromMonthYear = StringUtility.getParameter("fromMonthYear",request);		
			toMonthYear = StringUtility.getParameter("toMonthYear",request);	
			headCode = StringUtility.getParameter("headCode",request);
			branchCode = StringUtility.getParameter("bankCode",request);
			String locationCode = SessionHelper.getLocationCode(inputMap);
			arrHeadVouchers = (ArrayList) lObjTrnPensionPsbDtlsDAO.getHeadVouchers(fromMonthYear, toMonthYear, headCode, branchCode, locationCode);
			inputMap.put("headVouchersList", arrHeadVouchers);
			inputMap.put("fromMonthYear", fromMonthYear);
			inputMap.put("toMonthYear", toMonthYear);
			inputMap.put("headCode", headCode);
			inputMap.put("branchCode", branchCode);
			inputMap.put("Flag","false");
			resObj.setResultValue(inputMap);
			resObj.setViewName("displayHeadVoucherScreen");			
		}
		catch (Exception e)
		{
        	gLogger.error(" Error is : " + e, e);
        	e.printStackTrace();
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }  
		return resObj;
	}
	
	public ResultObject displayPSBPostedVoucher(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		ArrayList arrPSBPostedVouchers = new ArrayList();
		ArrayList voucherNoList = new ArrayList();
		ArrayList headCodeList = new ArrayList();
		ArrayList headDescList = new ArrayList();
		ArrayList amountList = new ArrayList();
		TrnPensionPsbDtlsDAO lObjTrnPensionPsbDtlsDAO = new TrnPensionPsbDtlsDAOImpl(TrnPensionPsbDtls.class,servLoc.getSessionFactory());
		
		try
		{
			String locationCode = SessionHelper.getLocationCode(inputMap);
			arrPSBPostedVouchers = (ArrayList) lObjTrnPensionPsbDtlsDAO.getPSBPostedVoucher(locationCode);
			
			for(int i=0;i<arrPSBPostedVouchers.size();i++)
			{
				Object cols[] = (Object[]) arrPSBPostedVouchers.get(i);
				voucherNoList.add(cols[0].toString());
				headCodeList.add(cols[1].toString());
				headDescList.add(cols[2].toString());
				amountList.add(cols[3].toString());
			}
			inputMap.put("voucherNoList",voucherNoList);
			inputMap.put("headCodeList",headCodeList);
			inputMap.put("headDescList",headDescList);
			inputMap.put("amountList",amountList);
			inputMap.put("Flag","true");
			resObj.setResultValue(inputMap);
			resObj.setViewName("displayPSBPostedVoucherScreen");			
		}
		catch (Exception e)
		{
        	gLogger.error(" Error is : " + e, e);
        	e.printStackTrace();
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }  
		return resObj;
	}
	
	public ResultObject getPostedPSBVoucherFormDtls(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");		
		BigDecimal dtlPostHeadcode;
		BigDecimal headCode;
		BudgetDtlsDAO lObjBudgetDtlsDAO = new BudgetDtlsDAOImpl(servLoc.getSessionFactory());
		TrnPensionPsbDtlsDAO lObjTrnPensionPsbDtlsDAO = new TrnPensionPsbDtlsDAOImpl(TrnPensionPsbDtls.class,servLoc.getSessionFactory());
		
		try
		{	
			setSessionInfo(inputMap);
			CmnLookupMstDAO cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,servLoc.getSessionFactory());
			List fund = cmnDAO.getAllChildrenByLookUpNameAndLang("Fund",gLngLangId);
			inputMap.put("fund",fund);
			List ClassOfExp = cmnDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp",gLngLangId);
			inputMap.put("ClassOfExp",ClassOfExp);
			List lBudjetType = cmnDAO.getAllChildrenByLookUpNameAndLang("OnlineBillBudgetType",gLngLangId);
			Object[] lObjTemp = lBudjetType.toArray();
			Object[] lObjBudjetType = new Object[lObjTemp.length];
			for (Object lObj : lObjTemp)
			{
				CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
				lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
			}
			lObjTemp = null;
				
			inputMap.put("BudjetType",lObjBudjetType);
			
			String lStrDtlPostHeadcode = StringUtility.getParameter("dtlPostHeadcode", request);
			if(lStrDtlPostHeadcode != null && lStrDtlPostHeadcode.trim().length() > 0)
			{
				dtlPostHeadcode = new BigDecimal(lStrDtlPostHeadcode.trim());
			}
			else
			{
				dtlPostHeadcode = new BigDecimal(0);
			}
			
			String lStrHeadCode = StringUtility.getParameter("headCode", request);
			if(lStrHeadCode != null && lStrHeadCode.trim().length() > 0)
			{
				headCode = new BigDecimal(lStrHeadCode.trim());
			}
			else
			{
				headCode = new BigDecimal(0);
			}
			String actualAmount = StringUtility.getParameter("amount",request);
			String flag =StringUtility.getParameter("flag",request);
			RltPensionHeadcodeChargable budjetDtlVo= lObjBudgetDtlsDAO.getMstPensionHeadcodeDtls(dtlPostHeadcode.toString(),"PENSION");		
						
			
			String lLngVoucherNo = StringUtility.getParameter("voucherNo",request);
			
			inputMap.put("VoucherNo",lLngVoucherNo);	
			
			Date lStrBillDate = lObjTrnPensionPsbDtlsDAO.getBillDateFromVoucher(lLngVoucherNo.toString());
						
			if( lStrBillDate != null)
			{
				inputMap.put("BillDate", (new SimpleDateFormat("dd/MM/yyyy").format(lStrBillDate)));
			}			
			
			inputMap.put("defaultBudgetType", "State Non-Plan");
			inputMap.put("SchemeNo", "000000");
			String headChrg = budjetDtlVo.getMjrhdCode() + budjetDtlVo.getSubmjrhdCode() + budjetDtlVo.getMinhdCode() + 
							budjetDtlVo.getSubhdCode() + budjetDtlVo.getDtlhdCode();
			inputMap.put("HeadChrg", headChrg);
			inputMap.put("budjetDtlVo",budjetDtlVo);
			Map edpMap = lObjBudgetDtlsDAO.getEdpDetails(dtlPostHeadcode);
			inputMap.put("edpVO",edpMap);
			inputMap.put("actualAmount",actualAmount);
			inputMap.put("fromMonthYear", 0);
			inputMap.put("toMonthYear", 0);
			inputMap.put("branchCode",0);
			inputMap.put("dtlPostHeadcode", dtlPostHeadcode);
			inputMap.put("headCode", headCode);
			inputMap.put("flag", flag);
			resObj.setResultValue(inputMap);
			resObj.setViewName("psbVoucherDtlsPost");			
		}
		catch (Exception e)
		{
        	gLogger.error(" Error is : " + e, e);
        	e.printStackTrace();
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }  
		return resObj;
	}
	
	public ResultObject getPSBVoucherFormDtls(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		
		String dtlPostHeadcode = null;
		BigDecimal headCode;
		TrnPensionPsbDtlsDAO lObjTrnPensionPsbDtlsDAO = new TrnPensionPsbDtlsDAOImpl(TrnPensionPsbDtls.class,servLoc.getSessionFactory());
		BudgetDtlsDAO lObjBudgetDtlsDAO = new BudgetDtlsDAOImpl(servLoc.getSessionFactory());
		
		try
		{	
			setSessionInfo(inputMap);
			CmnLookupMstDAO cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,servLoc.getSessionFactory());
			List fund = cmnDAO.getAllChildrenByLookUpNameAndLang("Fund",gLngLangId);
			inputMap.put("fund",fund);
			List ClassOfExp = cmnDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp",gLngLangId);
			inputMap.put("ClassOfExp",ClassOfExp);
			List lBudjetType = cmnDAO.getAllChildrenByLookUpNameAndLang("OnlineBillBudgetType",gLngLangId);
			Object[] lObjTemp = lBudjetType.toArray();
			Object[] lObjBudjetType = new Object[lObjTemp.length];
			for (Object lObj : lObjTemp)
			{
				CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
				lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
			}
			lObjTemp = null;
				
			inputMap.put("BudjetType",lObjBudjetType);
			
			String lStrDtlPostHeadcode = StringUtility.getParameter("dtlPostHeadcode", request);
			if(lStrDtlPostHeadcode != null && lStrDtlPostHeadcode.trim().length() > 0)
			{
				dtlPostHeadcode = lStrDtlPostHeadcode.trim();
			}
			else
			{
				dtlPostHeadcode = "0";
			}
			
			String lStrHeadcode = StringUtility.getParameter("headCode", request);
			if(lStrHeadcode != null && lStrHeadcode.trim().length() > 0)
			{
				headCode = new BigDecimal(lStrHeadcode.trim());
			}
			else
			{
				headCode = new BigDecimal(0);
			}
			String actualAmount = StringUtility.getParameter("amount",request);
			String fromMonthYear = StringUtility.getParameter("fromMonthYear",request);
			String toMonthYear =StringUtility.getParameter("toMonthYear",request);
			String branchCode =StringUtility.getParameter("branchCode",request);
			
			String flag =StringUtility.getParameter("flag",request);
			RltPensionHeadcodeChargable budjetDtlVo= lObjBudgetDtlsDAO.getMstPensionHeadcodeDtls(dtlPostHeadcode,"PENSION");
			
			String lStrMjrHdCode = budjetDtlVo.getMjrhdCode();
			
			long lLngVoucherNo = lObjTrnPensionPsbDtlsDAO.getNextVoucherMjrHdWiseForPSB(gStrLocationCode,lStrMjrHdCode);
						
			inputMap.put("VoucherNo",lLngVoucherNo);			
			inputMap.put("defaultBudgetType", "State Non-Plan");
			inputMap.put("SchemeNo", "000000");
			String headChrg = budjetDtlVo.getMjrhdCode() + budjetDtlVo.getSubmjrhdCode() + budjetDtlVo.getMinhdCode()+ 
							budjetDtlVo.getSubhdCode()+ budjetDtlVo.getDtlhdCode();
			inputMap.put("HeadChrg", headChrg);
			inputMap.put("budjetDtlVo",budjetDtlVo);
			Map edpMap = lObjBudgetDtlsDAO.getEdpDetails(new BigDecimal(dtlPostHeadcode));
			inputMap.put("edpVO",edpMap);
			inputMap.put("actualAmount",actualAmount);
			inputMap.put("fromMonthYear", fromMonthYear);
			inputMap.put("toMonthYear", toMonthYear);
			inputMap.put("branchCode", branchCode);
			inputMap.put("dtlPostHeadcode", dtlPostHeadcode);
			inputMap.put("headCode", headCode);
			inputMap.put("flag", flag);
			
			resObj.setResultValue(inputMap);
			resObj.setViewName("psbVoucherDtlsPost");			
		}
		catch (Exception e)
		{
        	gLogger.error(" Error is : " + e, e);
        	e.printStackTrace();
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }  
		return resObj;
	}
	public ResultObject saveVoucherDtls(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		TrnPensionPsbDtlsDAO lObjTrnPensionPsbDtlsDAO = new TrnPensionPsbDtlsDAOImpl(TrnPensionPsbDtls.class,servLoc.getSessionFactory());
		
		try
		{
			setSessionInfo(inputMap);			
			 PhyBillDAOImpl phyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class,servLoc.getSessionFactory());		     
		     BillEdpDAO billEdpImpl = new BillEdpDAOImpl(TrnBillEdpDtls.class,servLoc.getSessionFactory());
		     
		     TrnBillRegister lobjBillRegister = (TrnBillRegister)inputMap.get("billRegVO");		     
		     TrnBillEdpDtls lObjTrnBillEdpDtls = (TrnBillEdpDtls) inputMap.get("edpDtlsVO");
		     
		     String flag = (String)inputMap.get("flag");
		     
		     String fromMonthYear = StringUtility.getParameter("fromMonthYear",request);
			 String toMonthYear = StringUtility.getParameter("toMonthYear",request);
			 String branchCode = StringUtility.getParameter("branchCode",request);
			 String dtlPostHeadcode = StringUtility.getParameter("dtlPostHeadcode",request);
			 String voucherNo = inputMap.get("voucherNo").toString();
			 String voucherDate = inputMap.get("voucherDate").toString();
			 
			 PhyBillDAOImpl lObjTrnBillRegisterDAO = new PhyBillDAOImpl(TrnBillRegister.class, servLoc.getSessionFactory());
			 ArrayList ppoDetailsList = (ArrayList) lObjTrnPensionPsbDtlsDAO.updatePPODtlsForDtlPost(fromMonthYear, toMonthYear, dtlPostHeadcode, branchCode, voucherNo, gStrLocationCode);
			 int size = ppoDetailsList.size();
			 int counter=0;
			 TrnPensionPsbDtls lObjTrnPensionPsbDtls = new TrnPensionPsbDtls();
			 for(counter=0;counter<size;counter++)
			 {
				 lObjTrnPensionPsbDtls = lObjTrnPensionPsbDtlsDAO.read(Long.parseLong(ppoDetailsList.get(counter).toString()));
				 lObjTrnPensionPsbDtls.setVoucherNo(new BigDecimal(voucherNo));
				 lObjTrnPensionPsbDtlsDAO.update(lObjTrnPensionPsbDtls);
			 }
			 
			 if("false".equals(flag))
			 {
				 phyBillDAOImpl.create(lobjBillRegister);
			     //billEdpImpl.create(lObjTrnBillEdpDtls);
			 }
			 if("true".equals(flag))
			 {
				 TrnBillRegister lObjTrnBillRegister = new TrnBillRegister();
				 String VoucherNo = inputMap.get("VoucherNo").toString();
				 long billNo = lObjTrnPensionPsbDtlsDAO.getPKForVoucher(VoucherNo);
				 
				 if(billNo > 0)
				 {
					 lObjTrnBillRegister = lObjTrnBillRegisterDAO.read(billNo);	
				 }	
				 lObjTrnBillRegister.setPhyBill(Short.parseShort("1"));
				 lObjTrnBillRegister.setBillNo(billNo);
				 lObjTrnBillRegister.setSubjectId(Long.parseLong("9"));
				 lObjTrnBillRegister.setCreatedPostId(gLngPostId);
				 lObjTrnBillRegister.setCreatedUserId(gLngUserId);
				 lObjTrnBillRegister.setCreatedDate(new Date());
				 lObjTrnBillRegister.setLocationCode(gStrLocationCode);
				 if(voucherDate != null)
				 {
					 lObjTrnBillRegister.setBillDate(new SimpleDateFormat("dd/MM/yyyy").parse(voucherDate)); 
				 }				 
				 lObjTrnBillRegisterDAO.update(lObjTrnBillRegister);
			 }		     
		     inputMap.put("voucherDate",voucherDate);
		     
		     resObj.setResultValue(inputMap);
			 resObj.setViewName("displayHeadVoucherScreen");		
		}
		catch(Exception e)
		{
			gLogger.error(" Error is : " + e, e);
        	e.printStackTrace();
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
		}
		return resObj;
	}
	public ResultObject generatePSBMonthlyVO(Map inputMap)
	{
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject (ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,servLoc.getSessionFactory());
		TrnPensionPsbDtlsDAO lObjTrnPensionPsbDtlsDAO = new TrnPensionPsbDtlsDAOImpl(TrnPensionPsbDtls.class,servLoc.getSessionFactory());
		
		try
		{
			setSessionInfo(inputMap);
			String voucherDate = StringUtility.getParameter("txtVoucherDate",request);
			String principleAmount = StringUtility.getParameter("txtPrinciple",request);
			String netAmount = StringUtility.getParameter("txtNetAmount",request);
			String edpCode =StringUtility.getParameter("txtEdpCode",request);
			String expAmt =StringUtility.getParameter("txtAmt",request);
			String classExp = StringUtility.getParameter("cmbClassExp",request);
			String fund =StringUtility.getParameter("cmbFund",request);
			String typeOfBgt =StringUtility.getParameter("cmbTypeOfBgt",request);
			String schemeCode =StringUtility.getParameter("txtSchemeCode",request);
			String demand =StringUtility.getParameter("txtDemand",request);
			String majorHead =StringUtility.getParameter("txtMajorHead",request);
			String subMajorHead = StringUtility.getParameter("txtSubMajorHead",request);
			String minorHead =StringUtility.getParameter("txtMinorHead",request);
			String subHead = StringUtility.getParameter("txtSubHead",request);
			String detailHead =StringUtility.getParameter("txtDetailHead",request);
			String headChar =StringUtility.getParameter("txtHeadChar",request);	
			String VoucherNo = StringUtility.getParameter("VoucherNo",request);	
			
			String flag = StringUtility.getParameter("flag",request);
			
			inputMap.put("flag", flag);		
			
			TrnBillRegister lObjBillRegister=null;
			Long lngBillNO = null;
			lngBillNO = IFMSCommonServiceImpl.getNextSeqNum("trn_bill_register", inputMap);
	        lObjBillRegister=new TrnBillRegister();
	        lObjBillRegister.setBillNo(lngBillNO);
	        lObjBillRegister.setTrnCounter(new Integer(1));
	        lObjBillRegister.setBillDate(new SimpleDateFormat("dd/MM/yyyy").parse(voucherDate));
	        lObjBillRegister.setSubjectId(Long.parseLong("9"));
	        lObjBillRegister.setTcBill("Regular");
	        lObjBillRegister.setPhyBill(Short.parseShort("1"));
	        lObjBillRegister.setDemandCode(demand);
	        lObjBillRegister.setBudmjrHd(majorHead);
	        lObjBillRegister.setBillNetAmount(new BigDecimal(netAmount));
	        lObjBillRegister.setBillGrossAmount(new BigDecimal(principleAmount));
	        String lStrDeptCode = BptmCommonServiceImpl.getDeptByDemand(demand, gLngLangId, gLngDBId, servLoc);	       
	        if(lStrDeptCode!=null)
	        {
	        	lObjBillRegister.setDeptCode(lStrDeptCode);
	        }
	        lObjBillRegister.setCurrBillStatus(DBConstants.ST_DTL_PSTNG_DONE);
	        lObjBillRegister.setCreatedUserId(gLngUserId);
	        lObjBillRegister.setCreatedPostId(gLngPostId);
	        lObjBillRegister.setCreatedDate(new Date());
	        lObjBillRegister.setLocationCode(gStrLocationCode);
	        lObjBillRegister.setDbId(gLngDBId);
	        Integer inFinYearId = lObjFinYearDAOImpl.getFinYearIdByCurDate();
	        lObjBillRegister.setFinYearId(inFinYearId.toString());	        
	        /*lObjBillRegister.setClsExp(classExp);
	        lObjBillRegister.setBudType(Short.parseShort(typeOfBgt));
	        lObjBillRegister.setFund(fund);
	        lObjBillRegister.setBudmjrHd(majorHead);
	        lObjBillRegister.setDemandCode(demand);
	        lObjBillRegister.setBudSubmjrHd(subMajorHead);
	        lObjBillRegister.setSchemeNo(schemeCode);
	        lObjBillRegister.setBudMinHd(minorHead);
	        lObjBillRegister.setHeadChrg(headChar);
	        lObjBillRegister.setBudSubHd(subHead);
	        lObjBillRegister.setBudDtlHd(detailHead);*/
	        lObjBillRegister.setPhyBill(Short.parseShort("1"));	        
	        long voucherNo = lObjTrnPensionPsbDtlsDAO.getNextVoucherMjrHdWiseForPSB(gStrLocationCode, majorHead);	        
	       // lObjBillRegister.setVoucherNo(voucherNo);
	        SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");
	        //lObjBillRegister.setVoucherDate(lObjSmplDtFmt.parse(voucherDate));
	        
	        inputMap.put("billRegVO",lObjBillRegister);
	        inputMap.put("BillNo",lngBillNO);
	        inputMap.put("voucherDate",voucherDate);
	        inputMap.put("voucherNo", voucherNo);
	        inputMap.put("VoucherNo", VoucherNo);

	        TrnBillEdpDtls EdpVO=new TrnBillEdpDtls();
	        long lngEdpId = IFMSCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", inputMap);
	        EdpVO.setBillEdpId(lngEdpId);
	        EdpVO.setTrnCounter(Integer.valueOf(1));
	        EdpVO.setBillNo(lngBillNO);
	        EdpVO.setEdpAmt(new BigDecimal(expAmt));
	        EdpVO.setLocationCode(gStrLocationCode);
	        EdpVO.setDbId(gLngDBId);
	        EdpVO.setEdpCode(edpCode);
	        EdpVO.setExpRcpRec("EXP");
	        EdpVO.setAutoAdd("Y");
	        EdpVO.setCreatedDate(new Date());
	        EdpVO.setCreatedUserId(gLngUserId);
	        EdpVO.setCreatedPostId(gLngPostId);
	        
	        inputMap.put("edpDtlsVO",EdpVO);
		}
		catch(Exception e)
		{
			gLogger.error(" Error is : " + e, e);
        	e.printStackTrace();
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
		}
		return resObj;
	}
	 private void setSessionInfo(Map inputMap)
	  {
	      HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
	      gLngLangId = SessionHelper.getLangId(inputMap);
	      gLngPostId = SessionHelper.getPostId(inputMap);
	      gLngUserId = SessionHelper.getUserId(inputMap);
	      gLngDBId = SessionHelper.getDbId(inputMap);
	      gStrLocationCode = SessionHelper.getLocationCode(inputMap);
	      gDtCurrDt = DBUtility.getCurrentDateFromDB();
	  }
}
