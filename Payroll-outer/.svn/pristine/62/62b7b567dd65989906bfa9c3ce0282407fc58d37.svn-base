package com.tcs.sgv.lcm.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.dao.BudgetHdDtlsDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.SgvaBudbpnMapping;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptPaymentDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;
import com.tcs.sgv.lcm.dao.LcAdviceReceiptDAOImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcBudgetPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDtlPosting;

public class LCAdviceReceivedUpdateServiceImpl extends ServiceImpl
{
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	Log glogger = LogFactory.getLog(getClass());
	private SessionFactory lObjSessionFactory = null;
	
	public ResultObject getLcFormB(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		glogger.info("...Inside the  getLcFormBDtls in LcFormBServiceImpl...");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			//LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
			LCReportQueryDAOImpl lObjRptDAO = new LCReportQueryDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			//lObjRptDAO.setSessionFactory(serv.getSessionFactory());
			
			HttpSession hs = request.getSession();
			
            String lStrLcNo="";
			
           HashMap lHashdivInformation =new HashMap();
			
		if(request.getParameter("LcNo") != null)
			{
				lStrLcNo=(String)request.getParameter("LcNo");
				glogger.info("---------LcNo in REPORT SRVC--------------"+lStrLcNo);
			}
			 
				lHashdivInformation= lObjRptDAO.LcFormBLetterOfCredit(lStrLcNo);		
                request.setAttribute("divInformationVO", lHashdivInformation.get("divInformationVO"));
                request.setAttribute("DistrictNames", lHashdivInformation.get("District Name"));
                request.setAttribute("LocationShortName",lHashdivInformation.get("LocShortName"));
				objRes.setResultValue(objectArgs);
			
				objRes.setViewName("LcFormB");
				glogger.info("------------------end of getLcFormB---------");
			
			
		}
			
			catch(Exception e)
			{
				glogger.error("Error in getLcFormBDtls of LcFormBServiceImpl ", e);
				glogger.info("Error in getLcFormBDtls of LcFormBServiceImpl ");
				e.printStackTrace();
			}
		return objRes;
	}

	
	public ResultObject getChequeCertificateData(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		glogger.info("...Inside the  Cheque Certificate issue method in LcFormBServiceImpl...");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			//LCReportQueryDAO lObjRptDAO =  (LCReportQueryDAO) DAOFactory.Create("com.tcs.sgv.lcm.reports.LCReportQueryDAOImpl");
			LCReportQueryDAOImpl lObjRptDAO = new LCReportQueryDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			//lObjRptDAO.setSessionFactory(serv.getSessionFactory());
			
			HttpSession hs = request.getSession();
			
            String lStrMon="";
            String lStrYear="";
            String lStrDivCode="";
			
           HashMap lHashdivInformation =new HashMap();
			
		if(request.getParameter("month") != null)
		{
			lStrMon=(String)request.getParameter("month");
				glogger.info("---------Month Code in REPORT SRVC--------------"+lStrMon);
		}
		if(request.getParameter("finYear") != null)
		{
			lStrYear=(String)request.getParameter("finYear");
				glogger.info("---------Year Code in REPORT SRVC--------------"+lStrYear);
		}
		if(request.getParameter("divCode") != null)
		{
			lStrDivCode=(String)request.getParameter("divCode");
				glogger.info("---------Division Code in REPORT SRVC--------------"+lStrDivCode);
		}
		
		
			 
				lHashdivInformation= lObjRptDAO.getChequeCertiData(lStrMon,lStrYear,lStrDivCode);		
                request.setAttribute("divInformationVO", lHashdivInformation.get("divInformationVO"));
                request.setAttribute("DistrictNames", lHashdivInformation.get("District Name"));
                request.setAttribute("LocationShortName",lHashdivInformation.get("LocShortName"));
				objRes.setResultValue(objectArgs);
			
				objRes.setViewName("LcFormB");
				glogger.info("------------------end of getLcFormB---------");
			
			
		}
			
			catch(Exception e)
			{
				glogger.error("Error in getLcFormBDtls of LcFormBServiceImpl ", e);
				glogger.info("Error in getLcFormBDtls of LcFormBServiceImpl ");
				e.printStackTrace();
			}
		return objRes;
	}
	
	
	public ResultObject getAdviceReceivedDtlsForUpdate(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		glogger.info("...Inside the  getAdviceReceivedDtlsForUpdate in LCAdviceReceivedUpdateServiceImpl...");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		try
		{
			
			LCReportQueryDAOImpl lcReportQueryDao = new LCReportQueryDAOImpl();
			
			HttpSession session = request.getSession();
			Long lLngLangId = SessionHelper.getLangId(objectArgs);			
			String lStrLangId = lLngLangId.toString();			
			
			String lStrLcExpId="";
			
			TrnLcDtlPosting lcDtlPostingVo=null;
			LcDivisionInformationVO divInformationVO =null;
			ArrayList lArrChqPosting=null;
			ArrayList lArrBudPosting=null;
			ArrayList lArrDedPosting=null;
			ArrayList lArrMonths=null;
			
			//==============================
			
			//LcDivisionAccMstDAOImpl lLcDivAccDaoImpl = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
			LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			//BudHdDAOImpl lObjBudDaoImpl = new BudHdDAOImpl(serv.getSessionFactory()); 
			//BudExpEstDmdVO lObjBudExpEstDmdVO = null;
			
			String lStrLocId = bundleConst.getString("LC.Loc1");
			
			FinancialYearDAOImpl lObjFinYrDAO = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			int liFinYr = lObjFinYrDAO.getFinYearIdByCurDate();
			
			String lStrOldLangId = lObjLcAdvRcptDAOImpl.getStringLangId(lLngLangId);
			

			//ArrayList lArrDeduction=new ArrayList();
			
			//ArrayList lArrDemandNo=new ArrayList();
			//ArrayList lArrMjrHd=new ArrayList();
			
			HashMap lHmpDemMjrHd = null;
			HashMap lHmpSubMjrHd = null;
			HashMap lHmpMinHd = null;
			HashMap lHmpSubHd = null;
			

			lHmpDemMjrHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllMjrHds(lStrOldLangId, lStrLocId,liFinYr);
			request.setAttribute("MjrHdMap", lHmpDemMjrHd.get("mjrmap"));
			request.setAttribute("demandlist",lHmpDemMjrHd.get("demandlist"));

			glogger.info("==============after calling getAllMjrHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl" );
			glogger.info(" size of lHmpDemMjrHd is :: "+lHmpDemMjrHd.size());
			
			
			lHmpSubMjrHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllSubMjrHds(lStrOldLangId, lStrLocId,liFinYr);
			request.setAttribute("SubMjrHd", lHmpSubMjrHd);
			glogger.info("==============after calling getAllSubMjrHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl ");
			glogger.info("size of lHmpSubMjrHd is ::  "+lHmpSubMjrHd.size());
	
			lHmpMinHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllMinHds(lStrOldLangId, lStrLocId,liFinYr);
			request.setAttribute("MinHd", lHmpMinHd);
			glogger.info("==============after calling getAllMinHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl" );
			glogger.info(" size of lHmpMinHd is ::  "+lHmpMinHd.size());
	
			lHmpSubHd = (HashMap)lObjLcAdvRcptDAOImpl.getAllSubHds(lStrOldLangId, lStrLocId,liFinYr);
			request.setAttribute("SubHd", lHmpSubHd);
			glogger.info("==============after calling getAllSubHds of LcAdviceReceiptDAOImpl from getInitAdviceDtls of LcAdviceReceiptServiceImpl ");
			glogger.info("size of lHmpSubHd is ::  "+lHmpSubHd.size());
			
			
			//===============================
			
			if(request.getParameter("LcExpId") != null)
			{
				lStrLcExpId=(String)request.getParameter("LcExpId");
				glogger.info("---------LcExpId in REPORT SRVC--------------"+lStrLcExpId);
			}
			//=============================================================================================
			
			String lStrYear="";
			String lStrMonthCode="";
			String lStrBankCode="";
			String lStrAdviceCode="";
			String lStrLcFromDt="";
			String lStrLcToDt="";
			String lStrEntryFromDt="";
			String lStrEntryToDt="";
			String lStrStatusCode="";
			
			//----START 4 APPROVE UPDATE-------
			//String lStrLcExpId="";
			String lStrDivId="";
			//String lStrAdviceCode="";
			//String lStrMonthCode="";
			//String lStrBankCode="";			
			String lStrDept="";
			String lStrApproved="";
			String lStrFromDt="";
			String lStrToDt="";		
			//-------END--------------
			
			if(request.getParameter("Year") != null)
			{
				lStrYear=(String)request.getParameter("Year");
				glogger.info("---------lStrYear in REPORT SRVC at UPADATE--------------"+lStrYear);
			}
			if(request.getParameter("MonthCode") != null)
			{
				lStrMonthCode=(String)request.getParameter("MonthCode");
				glogger.info("---------lStrMonthCode in REPORT SRVC at UPADATE--------------"+lStrMonthCode);
			}
			if(request.getParameter("BankCode") != null)
			{
				lStrBankCode=(String)request.getParameter("BankCode");
				glogger.info("---------lStrBankCode in REPORT SRVC at UPADATE--------------"+lStrBankCode);
			}
			if(request.getParameter("AdviceCode") != null)
			{
				lStrAdviceCode=(String)request.getParameter("AdviceCode");
				glogger.info("---------lStrAdviceCode in REPORT SRVC at UPADATE--------------"+lStrAdviceCode);
			}
			if(request.getParameter("LcFromDate") != null)
			{
				lStrLcFromDt=(String)request.getParameter("LcFromDate");
				glogger.info("---------lStrLcFromDt in REPORT SRVC at UPADATE--------------"+lStrLcFromDt);
			}
			if(request.getParameter("LcToDate") != null)
			{
				lStrLcToDt=(String)request.getParameter("LcToDate");
				glogger.info("---------lStrLcToDt in REPORT SRVC at UPADATE--------------"+lStrLcToDt);
			}
			if(request.getParameter("EntryFromDate") != null)
			{
				lStrEntryFromDt=(String)request.getParameter("EntryFromDate");
				glogger.info("---------lStrEntryFromDt in REPORT SRVC at UPADATE--------------"+lStrEntryFromDt);
			}
			if(request.getParameter("EntryToDate") != null)
			{
				lStrEntryToDt=(String)request.getParameter("EntryToDate");
				glogger.info("---------lStrEntryToDt in REPORT SRVC at UPADATE--------------"+lStrEntryToDt);
			}
			if(request.getParameter("StatusCode") != null)
			{
				lStrStatusCode=(String)request.getParameter("StatusCode");
				glogger.info("---------lStrStatusCode in REPORT SRVC at UPADATE--------------"+lStrStatusCode);
			}
			
			//-----START 4 APPROVE UPDATE-----------------
			if(request.getParameter("DivisionId") != null)
			{
				lStrDivId=(String)request.getParameter("DivisionId");
				glogger.info("---------lStrDivId in REPORT SRVC at APPROVED UPADATE--------------"+lStrDivId);
			}
			if(request.getParameter("DepartmentId") != null)
			{
				lStrDept=(String)request.getParameter("DepartmentId");
				glogger.info("---------lStrDept in REPORT SRVC at APPROVED UPADATE--------------"+lStrDept);
			}
			if(request.getParameter("ApprovedId") != null)
			{
				lStrApproved=(String)request.getParameter("ApprovedId");
				glogger.info("---------lStrApproved in REPORT SRVC at APPROVED UPADATE--------------"+lStrApproved);
			}
			if(request.getParameter("FromDate") != null)
			{
				lStrFromDt=(String)request.getParameter("FromDate");
				glogger.info("---------lStrFromDt in REPORT SRVC at APPROVED UPADATE--------------"+lStrFromDt);
			}
			if(request.getParameter("ToDate") != null)
			{
				lStrToDt=(String)request.getParameter("ToDate");
				glogger.info("---------lStrToDt in REPORT SRVC at APPROVED UPADATE--------------"+lStrToDt);
			}
			
			//-----------END-------------------------
			
			session.setAttribute("lStrLcExpId", lStrLcExpId);
			session.setAttribute("lStrYear", lStrYear);
			session.setAttribute("lStrMonthCode", lStrMonthCode);
			session.setAttribute("lStrBankCode", lStrBankCode);
			session.setAttribute("lStrAdviceCode", lStrAdviceCode);
			session.setAttribute("lStrLcFromDt", lStrLcFromDt);
			session.setAttribute("lStrLcToDt", lStrLcToDt);
			session.setAttribute("lStrEntryFromDt", lStrEntryFromDt);
			session.setAttribute("lStrEntryToDt", lStrEntryToDt);
			session.setAttribute("lStrStatusCode", lStrStatusCode);
			
			//-----START 4 APPROVE UPDATE-----------------
			session.setAttribute("lStrDivId", lStrDivId);
			session.setAttribute("lStrDept", lStrDept);
			session.setAttribute("lStrApproved", lStrApproved);
			session.setAttribute("lStrFromDt", lStrFromDt);
			session.setAttribute("lStrToDt", lStrToDt);
			//-----------END-------------------------
			
			//===============================================================================================
			lcDtlPostingVo=(TrnLcDtlPosting)lcReportQueryDao.getDtlPostingDtlsForUpdate(lStrLcExpId);
			glogger.info("------SIZE OF lcDtlPostingVo---------"+lcDtlPostingVo.toString());
			//after this i am getting remaining info of division by division id
			String lStrDivCode="";
			if(lcDtlPostingVo.getDivisionCode() != null)
				lStrDivCode=lcDtlPostingVo.getDivisionCode().toString();
			
			divInformationVO=(LcDivisionInformationVO)lcReportQueryDao.getDivisionDtlsForUpdate(lStrDivCode,lStrLangId);
			glogger.info("------SIZE OF divInformationVO---------"+divInformationVO.toString());
			//......................................................
			
			lArrChqPosting=(ArrayList)lcReportQueryDao.getChqPostingDtlsForUpdate(lStrLcExpId);
			glogger.info("------SIZE OF lArrChqPosting---------"+lArrChqPosting.size());
			
			lArrBudPosting=(ArrayList)lcReportQueryDao.getBudgetPostingDtlsForUpdate(lStrLcExpId);
			glogger.info("------SIZE OF lArrBudPosting---------"+lArrBudPosting.size());
			
			lArrDedPosting=(ArrayList)lcReportQueryDao.getDeductionPostingDtlsForUpdate(lStrLcExpId);
			glogger.info("------SIZE OF lArrDedPosting---------"+lArrDedPosting.size());
			
			lArrMonths=(ArrayList)lcReportQueryDao.getMonths(lStrOldLangId, lStrLocId);
			glogger.info("------SIZE OF lArrMonths---------"+lArrMonths.size());
			
			request.setAttribute("lcDtlPostingVo", lcDtlPostingVo);
			request.setAttribute("divInformationVO", divInformationVO);
			request.setAttribute("lArrChqPosting", lArrChqPosting);			
			request.setAttribute("lArrBudPosting", lArrBudPosting);
			request.setAttribute("lArrDedPosting", lArrDedPosting);
			request.setAttribute("lArrMonths", lArrMonths);
			
			//objectArgs.put("lArrChqPosting", lArrChqPosting);
			
			objRes.setResultValue(objectArgs);
			
			objRes.setViewName("lcAdviceReceivedUpdate");
			glogger.info("------------------end of getAdviceReceivedDtlsForUpdate---------");
			
		}
		catch(Exception e)
		{
			glogger.error("Error in getInitAdviceDtls of LCAdviceReceivedUpdateServiceImpl ", e);
			glogger.info("Error in getInitAdviceDtls of LCAdviceReceivedUpdateServiceImpl ");
			e.printStackTrace();
		}
	return objRes;
   }
	
	public ResultObject deleteAdviceReceivedDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		glogger.info("..........Inside the  deleteAdviceReceivedDtls in LCAdviceReceivedUpdateServiceImpl..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		try
		{
			
			LCReportQueryDAOImpl lcReportQueryDao = new LCReportQueryDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			
			HttpSession hs = request.getSession();
			//Long lLngLangId = SessionHelper.getLangId(request);
			//String lStrLangId = lLngLangId.toString();
			//int lILangId = Integer.parseInt(lStrLangId);
			
			String lStrLcExpId="";
			String lStrYear="";
			String lStrMonthCode="";
			String lStrBankCode="";
			String lStrAdviceCode="";
			String lStrLcFromDt="";
			String lStrLcToDt="";
			String lStrEntryFromDt="";
			String lStrEntryToDt="";			
			String lStrStatusCode="";
			
			if(request.getParameter("LcExpId") != null)
			{
				lStrLcExpId=(String)request.getParameter("LcExpId");
				glogger.info("---------LcExpId in REPORT SRVC at DELETE--------------"+lStrLcExpId);
			}
			if(request.getParameter("Year") != null)
			{
				lStrYear=(String)request.getParameter("Year");
				glogger.info("---------lStrYear in REPORT SRVC at DELETE--------------"+lStrYear);
			}
			if(request.getParameter("MonthCode") != null)
			{
				lStrMonthCode=(String)request.getParameter("MonthCode");
				glogger.info("---------lStrMonthCode in REPORT SRVC at DELETE--------------"+lStrMonthCode);
			}
			if(request.getParameter("BankCode") != null)
			{
				lStrBankCode=(String)request.getParameter("BankCode");
				glogger.info("---------lStrBankCode in REPORT SRVC at DELETE--------------"+lStrBankCode);
			}
			if(request.getParameter("AdviceCode") != null)
			{
				lStrAdviceCode=(String)request.getParameter("AdviceCode");
				glogger.info("---------lStrAdviceCode in REPORT SRVC at DELETE--------------"+lStrAdviceCode);
			}
			if(request.getParameter("LcFromDate") != null)
			{
				lStrLcFromDt=(String)request.getParameter("LcFromDate");
				glogger.info("---------lStrLcFromDt in REPORT SRVC at DELETE--------------"+lStrLcFromDt);
			}
			if(request.getParameter("LcToDate") != null)
			{
				lStrLcToDt=(String)request.getParameter("LcToDate");
				glogger.info("---------lStrLcToDt in REPORT SRVC at DELETE--------------"+lStrLcToDt);
			}
			if(request.getParameter("EntryFromDate") != null)
			{
				lStrEntryFromDt=(String)request.getParameter("EntryFromDate");
				glogger.info("---------lStrEntryFromDt in REPORT SRVC at DELETE--------------"+lStrEntryFromDt);
			}
			if(request.getParameter("EntryToDate") != null)
			{
				lStrEntryToDt=(String)request.getParameter("EntryToDate");
				glogger.info("---------lStrEntryToDt in REPORT SRVC at DELETE--------------"+lStrEntryToDt);
			}
			if(request.getParameter("StatusCode") != null)
			{
				lStrStatusCode=(String)request.getParameter("StatusCode");
				glogger.info("---------lStrStatusCode in REPORT SRVC at DELETE--------------"+lStrStatusCode);
			}
			
			boolean dtlDeleteResult=false;
			boolean chqDeleteResult=false;
			boolean budDeleteResult=false;
			boolean dedDeleteResult=false;
			long lILcExpId=0;
			if(!lStrLcExpId.equals(""))
			     lILcExpId=Long.parseLong(lStrLcExpId);
			
			//----UPDATE LC EXP AMT IN PROGRESIVE AMT------------
			boolean lBExpUpdateResult=false;
			String lStrApprovedStatus="";
			String lStrSign="+";
			lStrApprovedStatus=lcReportQueryDao.getLcAdviceApprovedStatus(lILcExpId);
			if(lStrApprovedStatus.equals("0"))
			      lBExpUpdateResult=(boolean)lcReportQueryDao.updateApprovedLcAmount(lILcExpId,lStrSign);
			glogger.info("-----PROGRASSIVE AMT UPDATE RESULT AT DELETE------"+lBExpUpdateResult);
			
			//---------------------------------------------------
			
			dtlDeleteResult=(boolean)lcReportQueryDao.deleteDtlPosting(lStrLcExpId);
			glogger.info("--------DTL DELETE RESULT IN DELETE SEVC------------------"+dtlDeleteResult);
			
			if(dtlDeleteResult==true)
			{
				chqDeleteResult=(boolean)lcReportQueryDao.deleteChequePosting(lILcExpId,0);
				glogger.info("--------CHEQUE DELETE RESULT IN DELETE SEVC------------------"+chqDeleteResult);
				
				budDeleteResult=(boolean)lcReportQueryDao.deleteBudgetPosting(lILcExpId,0);
				glogger.info("--------BUDGET DELETE RESULT IN DELETE SEVC------------------"+budDeleteResult);
				
				dedDeleteResult=(boolean)lcReportQueryDao.deleteDeductionPosting(lStrLcExpId);
				glogger.info("--------DEDUCTION DELETE RESULT IN DELETE SEVC------------------"+dedDeleteResult);
			}
			
            //---LOGIC FOR DSS SATA SERVICE----------------------
			
			ArrayList lArrBudId=lcReportQueryDao.getLcBudIdByLcExpId(lILcExpId);
			ArrayList lArrDedId=lcReportQueryDao.getLcDedIdByLcExpId(lILcExpId);
			glogger.info("-------SIZE OF ARR LST IN APPROVE lArrBudId--------------"+lArrBudId.size());
			glogger.info("-------SIZE OF ARR LST IN APPROVE lArrDedId--------------"+lArrDedId.size());
			String lStrLcTypeCode = bundleConst.getString("LC.EXP_TYPE_CODE");
			DssDataServiceImpl dssServiceObj = null;
			HashMap dssInputMap =null;
			for(int i=0;i<lArrBudId.size();i++)
			{
				String lStrLcBudId=(String)lArrBudId.get(i);
				
				dssServiceObj = new DssDataServiceImpl();
				dssInputMap = new HashMap();
				
				glogger.info("----------BUD ID FOR DSS-----------"+lStrLcBudId);
				glogger.info("----------EXP TYPE FOR DSS-----------"+lStrLcTypeCode);
				glogger.info("-------1111111111&&&&&&&&&&& 33---------");
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("expNo", lStrLcBudId);
				dssInputMap.put("expTypeCode", lStrLcTypeCode);
				glogger.info("-------1111111111&&&&&&&&&&& 44---------");
				dssServiceObj.deleteExpData(dssInputMap);
				glogger.info("-------1111111111&&&&&&&&&&& 55---------");
				
			}
			//logic for delete dumy row from dss data service
			dssServiceObj = new DssDataServiceImpl();
			dssInputMap = new HashMap();
			dssInputMap.put("map", objectArgs);
			dssInputMap.put("expNo", lILcExpId);
			dssInputMap.put("expTypeCode", lStrLcTypeCode);
			glogger.info("-------1111111111&&&&&&&&&&& 44---------");
			dssServiceObj.deleteExpData(dssInputMap);
			glogger.info("-------1111111111&&&&&&&&&&& 55---------");
			//----------------
			String lStrOthers=bundleConst.getString("LC.CHALAN_CODE");
			for(int i=0;i<lArrDedId.size();i++)
			{
				String lStrLcDedId=(String)lArrDedId.get(i);
				
				dssServiceObj = new DssDataServiceImpl();
				dssInputMap = new HashMap();
				
				glogger.info("----------lStrLcDedId ID FOR DSS-----------"+lStrLcDedId);
				glogger.info("----------EXP TYPE FOR DSS-----------"+lStrLcTypeCode+"====="+lStrOthers);
				glogger.info("-------1111111111&&&&&&&&&&& 33---------");
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("rcptNo", lILcExpId);
				dssInputMap.put("rcptTypeCode", lStrLcTypeCode);
				dssInputMap.put("challanCatgCode", lStrOthers);
				dssInputMap.put("trnReceiptId", lStrLcDedId);
				
				glogger.info("-------1111111111&&&&&&&&&&& 44---------");
				dssServiceObj.deleteReceiptData(dssInputMap);
				glogger.info("-------1111111111&&&&&&&&&&& 55---------");
				
			}
			//---------------------------------------------------
			
			
			request.setAttribute("lStrYear", lStrYear);
			request.setAttribute("lStrMonthCode", lStrMonthCode);
			request.setAttribute("lStrBankCode", lStrBankCode);
			request.setAttribute("lStrAdviceCode", lStrAdviceCode);
			request.setAttribute("lStrLcFromDt", lStrLcFromDt);
			request.setAttribute("lStrLcToDt", lStrLcToDt);
			request.setAttribute("lStrEntryFromDt", lStrEntryFromDt);
			request.setAttribute("lStrEntryToDt", lStrEntryToDt);
			request.setAttribute("lStrStatusCode", lStrStatusCode);
			
			//objectArgs.put("lArrChqPosting", lArrChqPosting);
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("LcIntermediateForDelete");
			
			glogger.info("------------------end of deleteAdviceReceivedDtls---------");
			
		}
		catch(Exception e)
		{
			glogger.error("Error in getInitAdviceDtls of LCAdviceReceivedUpdateServiceImpl ", e);
			glogger.info("Error in getInitAdviceDtls of LCAdviceReceivedUpdateServiceImpl ");
			e.printStackTrace();
		}
	return objRes;
   }
	
	public ResultObject updateAdviceReceivedDtls(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		glogger.info("..........Inside the  updateAdviceReceivedDtls in LCAdviceReceivedUpdateServiceImpl..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		try
		{			
			HttpSession session = request.getSession();
			Long lLngLangId = SessionHelper.getLangId(objectArgs);	
			String lStrLocCode=SessionHelper.getLocationCode(objectArgs);
			
			LCReportQueryDAOImpl lcReportQueryDao = new LCReportQueryDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			LcAdviceReceiptDAOImpl lObjLcAdvRcptDAOImpl = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			
			TrnLcDtlPosting lcDtlPostingVo=null;
			TrnLcChequePosting lcChequePostingVo=null;
			TrnLcBudgetPosting lcBudPostingVo=null;
			TrnLcDeductionPosting lcDedPostingVo=null;
			
			ArrayList lArrDeleteChqPosting=null;
			ArrayList lArrUpdateChqPosting=null;
			ArrayList lArrInsertChqPosting=null;
			ArrayList lArrDeleteBudPosting=null;
			ArrayList lArrUpdateBudPosting=null;
			ArrayList lArrInsertBudPosting=null;
			ArrayList lArrDedPosting=null;
			
			boolean lBUpdateDtlPosting=false;
			boolean lBDeleteChqPosting=false;
			boolean lBUpdateChqResult=false;
			boolean lBInsertChqResult=false;
			boolean lBDeleteBudPosting=false;
			boolean lBUpdateBudResult=false;
			boolean lBInsertBudResult=false;
			boolean lBUpdateDedResult=false;
			
			long lILcExpId=0;
			long lILcChqId=0;
			long lILcBudId=0;
			
			if(objectArgs.get("lObjLcDtlPostingVo") != null)
				lcDtlPostingVo=(TrnLcDtlPosting)objectArgs.get("lObjLcDtlPostingVo");
			
			if(objectArgs.get("lArrDeleteChequePosting") != null)
				lArrDeleteChqPosting=(ArrayList)objectArgs.get("lArrDeleteChequePosting");
			if(objectArgs.get("lArrUpdateChequePosting") != null)
				lArrUpdateChqPosting=(ArrayList)objectArgs.get("lArrUpdateChequePosting");
			if(objectArgs.get("lArrInsertChequePosting") != null)
				lArrInsertChqPosting=(ArrayList)objectArgs.get("lArrInsertChequePosting");
			
			if(objectArgs.get("lArrDeleteBudgetPosting") != null)
				lArrDeleteBudPosting=(ArrayList)objectArgs.get("lArrDeleteBudgetPosting");
			if(objectArgs.get("lArrUpdateBudgetPosting") != null)
				lArrUpdateBudPosting=(ArrayList)objectArgs.get("lArrUpdateBudgetPosting");
			if(objectArgs.get("lArrInsertBudgetPosting") != null)
				lArrInsertBudPosting=(ArrayList)objectArgs.get("lArrInsertBudgetPosting");
			
			if(objectArgs.get("lArrDeductionPosting") != null)
				lArrDedPosting=(ArrayList)objectArgs.get("lArrDeductionPosting");
			
			String lStrAdvApproved=lcDtlPostingVo.getAdviceApproved().toString();
			String lStrTsryCode=lObjLcAdvRcptDAOImpl.getLoggedInTsryCode(lStrLocCode, lLngLangId);
			
			glogger.info("-----IN SRVC AT UPDATE ADV APPROVED STATUS-------"+lStrAdvApproved);
			if(lcDtlPostingVo != null)
			{
				lBUpdateDtlPosting=(boolean)lcReportQueryDao.updateDtlPosting(lcDtlPostingVo);
				glogger.info("-------UPDATE DTL POSTING RESULT--------------"+lBUpdateDtlPosting);
			}
			String lStrBankId=lcDtlPostingVo.getBankCode().toString();
			
			glogger.info("-------DELETE CHEQUE POSTING SIZE--------"+lArrDeleteChqPosting.size());
			for(int i=0;i<lArrDeleteChqPosting.size();i++)
			{				
				lcChequePostingVo=(TrnLcChequePosting)lArrDeleteChqPosting.get(i);
				lILcExpId=Long.parseLong(lcChequePostingVo.getLcExpId().toString());
				lILcChqId=Long.parseLong(lcChequePostingVo.getLcChqId().toString());
				lBDeleteChqPosting=(boolean)lcReportQueryDao.deleteChequePosting(lILcExpId, lILcChqId);
				
				//--------DSS------------------------
				DssDataServiceImpl dssOnj=new DssDataServiceImpl();
				RptPaymentDtls paymentDss=null;
				Map inputMap=new HashMap();
				String chqTypeCode=bundleConst.getString("LC.CHQ_TYPE_CODE");
				
				
				inputMap.put("map", objectArgs);
				inputMap.put("chqTypeCode", chqTypeCode);
				inputMap.put("chqNo", lcChequePostingVo.getChequeNo());
				
				dssOnj.deletePaymentData(inputMap);
				
				//-----------------------------------
				
				glogger.info("-------DELETE CHEQUE POSTING RESULT--------"+lBDeleteChqPosting);
			}
			glogger.info("-------UPDATE CHEQUE POSTING SIZE--------"+lArrUpdateChqPosting.size());
			for(int i=0;i<lArrUpdateChqPosting.size();i++)
			{				
				lcChequePostingVo=(TrnLcChequePosting)lArrUpdateChqPosting.get(i);
				lBUpdateChqResult=(boolean)lcReportQueryDao.updateChequePosting(lcChequePostingVo);
				
				//-----DSS---------------------
				DssDataServiceImpl dssOnj=new DssDataServiceImpl();
				RptPaymentDtls paymentDss=null;
				Map inputMap=new HashMap();
				String chqTypeCode=bundleConst.getString("LC.CHQ_TYPE_CODE");
				
				glogger.info("---------paymentDss---"+chqTypeCode+"-------"+lcChequePostingVo.getChequeNo());
				inputMap.put("map", objectArgs);
				inputMap.put("chqTypeCode", chqTypeCode);
				inputMap.put("chqNo", lcChequePostingVo.getChequeNo());
				
				paymentDss=dssOnj.getPaymentData(inputMap);
				glogger.info("---------paymentDss-----------"+paymentDss);
				
				paymentDss.setChqNo(lcChequePostingVo.getChequeNo());
				paymentDss.setAmount(lcChequePostingVo.getChequeAmt());
				paymentDss.setPartyName(lcChequePostingVo.getPartyName());
				
				inputMap=new HashMap();
				
				inputMap.put("map", objectArgs);
				inputMap.put("RptPaymentVO", paymentDss);
				
				dssOnj.updatePaymentData(inputMap);
				
				
				//-----------------------------
				glogger.info("-------UPDATE CHEQUE POSTING RESULT--------"+lBUpdateChqResult);
			}
			glogger.info("-------INSERT CHEQUE POSTING SIZE--------"+lArrInsertChqPosting.size());
			for(int i=0;i<lArrInsertChqPosting.size();i++)
			{				
				lcChequePostingVo=(TrnLcChequePosting)lArrInsertChqPosting.get(i);
				lBInsertChqResult=(boolean)lObjLcAdvRcptDAOImpl.saveLcChequePosting(lcChequePostingVo);
				
				insertDSSChqData(lcChequePostingVo,serv,objectArgs,lStrTsryCode,lStrBankId);
				glogger.info("-------INSERT CHEQUE POSTING RESULT--------"+lBInsertChqResult);
			}
			
			
			glogger.info("-------DELETE BUDGET POSTING SIZE--------"+lArrDeleteBudPosting.size());
			double lDblLcExpTotal=0.0;
			String lStrDivCode="";
			String lStrSign="+";
			boolean lBExpUpdateResult=false;
			if(lcDtlPostingVo.getDivisionCode() != null)
				lStrDivCode=lcDtlPostingVo.getDivisionCode().toString();
			glogger.info("-------lIDivisionId ****---------"+lStrDivCode);
			
			for(int i=0;i<lArrDeleteBudPosting.size();i++)
			{				
				glogger.info("-------1111111111&&&&&&&&&&& 11---------");
				lcBudPostingVo=(TrnLcBudgetPosting)lArrDeleteBudPosting.get(i);
				lILcExpId=Long.parseLong(lcBudPostingVo.getLcExpId().toString());
				lILcBudId=Long.parseLong(lcBudPostingVo.getLcBudId().toString());
				glogger.info("-------1111111111&&&&&&&&&&& 22---------");
				
				//--------LOGIC FOR DSS DATA SERVICE-------------------------------
				DssDataServiceImpl dssServiceObj = new DssDataServiceImpl();
				HashMap dssInputMap = new HashMap();
				String lStrLcTypeCode = bundleConst.getString("LC.EXP_TYPE_CODE");
				glogger.info("----------BUD ID FOR DSS-----------"+lILcBudId);
				glogger.info("----------EXP TYPE FOR DSS-----------"+lStrLcTypeCode);
				glogger.info("-------1111111111&&&&&&&&&&& 33---------");
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("expNo", lILcBudId);
				dssInputMap.put("expTypeCode", lStrLcTypeCode);
				glogger.info("-------1111111111&&&&&&&&&&& 44---------");
				dssServiceObj.deleteExpData(dssInputMap);
				glogger.info("-------1111111111&&&&&&&&&&& 55---------");
				
				//-----------------------------------------------------------------
				lDblLcExpTotal += (double)lcReportQueryDao.getLcExpAmount(lILcBudId, lILcExpId);
				glogger.info("-------1111111111&&&&&&&&&&&---------");
				lBDeleteBudPosting=(boolean)lcReportQueryDao.deleteBudgetPosting(lILcExpId, lILcBudId);
				glogger.info("-------DELETE BUDGET POSTING RESULT--------"+lBDeleteBudPosting);
			}
			glogger.info("-------lDblLcExpTotal---------"+lDblLcExpTotal);
			if(lStrAdvApproved.equals("0"))
			     lBExpUpdateResult=(boolean)lcReportQueryDao.updateApprovedLcAmount(lStrDivCode, lDblLcExpTotal, lStrSign);
			glogger.info("-----EXP UPDATE RESULT AT DELETE------"+lBExpUpdateResult);
			
			
			
			glogger.info("-------UPDATE BUDGET POSTING SIZE--------"+lArrUpdateBudPosting.size());
			double lDblCurrUpdateTotal=0.0;
			lDblLcExpTotal=0.0;
			lBExpUpdateResult=false;
			for(int i=0;i<lArrUpdateBudPosting.size();i++)
			{				
				glogger.info("----------1111111111111-----------");
				lcBudPostingVo=(TrnLcBudgetPosting)lArrUpdateBudPosting.get(i);
				lILcExpId=Long.parseLong(lcBudPostingVo.getLcExpId().toString());
				lILcBudId=Long.parseLong(lcBudPostingVo.getLcBudId().toString());
              
				//--------LOGIC FOR DSS DATA SERVICE-------------------------------
				glogger.info("----------2222222222-----------");
				DssDataServiceImpl dssServiceObj = new DssDataServiceImpl();
				HashMap dssInputMap = new HashMap();
				String lStrLcTypeCode = bundleConst.getString("LC.EXP_TYPE_CODE");
				glogger.info("----------BUD ID FOR DSS-----------"+lILcBudId);
				glogger.info("----------EXP TYPE FOR DSS-----------"+lStrLcTypeCode);
				glogger.info("----------3333333333----------");
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("expNo", lILcBudId);
				dssInputMap.put("expTypeCode", lStrLcTypeCode);
				RptExpenditureDtls rptDssObj=null;
				rptDssObj=(RptExpenditureDtls)dssServiceObj.getExpData(dssInputMap);
				glogger.info("----------44444444444-----------");
				double lDLcExpAmt=Double.parseDouble(lcBudPostingVo.getExpAmt().toString());
				glogger.info("----------55555 AMT-----------"+lDLcExpAmt);
				HashMap lHmpAmt = new HashMap();
				lHmpAmt.put("amount", new BigDecimal(lDLcExpAmt));
				lHmpAmt.put("calculate", "false");
				rptDssObj.setGrossAmnt(lHmpAmt);
				//lObjExpVO.setExpStatusCode(expStatusCode);
				lHmpAmt = new HashMap();
				lHmpAmt.put("amount", new BigDecimal(lDLcExpAmt));
				rptDssObj.setNetAmt(lHmpAmt);
				
				lHmpAmt = new HashMap();
				lHmpAmt.put("amount", new BigDecimal(lDLcExpAmt));
				rptDssObj.setExpAmt(lHmpAmt);
				
				dssInputMap = new HashMap();
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("RptExpenditureVO", rptDssObj);
				
				dssServiceObj.updateExpData(dssInputMap);
				
				//-----------------------------------------------------------------
				lDblCurrUpdateTotal += Double.parseDouble(lcBudPostingVo.getExpAmt().toString());
				
				lDblLcExpTotal += (double)lcReportQueryDao.getLcExpAmount(lILcBudId, lILcExpId);
				
				lBUpdateBudResult=(boolean)lcReportQueryDao.updateBudgetPosting(lcBudPostingVo);
				glogger.info("-------UPDATE BUDGET POSTING RESULT--------"+lBUpdateBudResult);
			}
			double lDblFinalUpdatedTotal= lDblLcExpTotal - lDblCurrUpdateTotal;
			if(lStrAdvApproved.equals("0"))
			     lBExpUpdateResult=(boolean)lcReportQueryDao.updateApprovedLcAmount(lStrDivCode, lDblFinalUpdatedTotal, lStrSign);
			glogger.info("-----EXP UPDATE RESULT AT UPDATE------"+lBExpUpdateResult);
			
			
			
			glogger.info("-------INSERT BUDGET POSTING SIZE--------"+lArrInsertBudPosting.size());
			lStrSign="-";
			lDblLcExpTotal=0.0;
			lBExpUpdateResult=false;
			for(int i=0;i<lArrInsertBudPosting.size();i++)
			{				
				lcBudPostingVo=(TrnLcBudgetPosting)lArrInsertBudPosting.get(i);
				
				//--------LOGIC FOR DSS DATA SERVICE-------------------------------
				//String lStrTsryCode=lObjLcAdvRcptDAOImpl.getLoggedInTsryCode(lStrLocCode, lLngLangId);
				insertDSSBudData(lcBudPostingVo,serv,(String)objectArgs.get("distcode"),objectArgs,lStrAdvApproved,lStrTsryCode, lLngLangId);
				
				//-----------------------------------------------------------------
				
				lDblLcExpTotal += Double.parseDouble(lcBudPostingVo.getExpAmt().toString());
				
				lBInsertBudResult=(boolean)lObjLcAdvRcptDAOImpl.saveLcBudgetPosting(lcBudPostingVo);
				glogger.info("-------INSERT BUDGET POSTING RESULT--------"+lBInsertBudResult);
			}
			if(lStrAdvApproved.equals("0"))
			     lBExpUpdateResult=(boolean)lcReportQueryDao.updateApprovedLcAmount(lStrDivCode, lDblLcExpTotal, lStrSign);
			glogger.info("-----EXP UPDATE RESULT AT UPDATE------"+lBExpUpdateResult);
			
			
			
			//Calling Deduction Posting Update
			double lDDedTotalAmt=0;
			glogger.info("-------UPDATE DEDUCTION POSTING SIZE--------"+lArrDedPosting.size());
			for(int i=0;i<lArrDedPosting.size();i++)
			{				
				lcDedPostingVo=(TrnLcDeductionPosting)lArrDedPosting.get(i);
				
				lDDedTotalAmt +=Double.parseDouble(lcDedPostingVo.getAmount().toString()); 
				
				lBUpdateDedResult=(boolean)lcReportQueryDao.updateDeductionPosting(lcDedPostingVo);
				glogger.info("-------UPDATE DEDUCTION POSTING RESULT--------"+lBUpdateDedResult);
				
				//--------LOGIC FOR DSS DATA SERVICE-------------------------------
				glogger.info("----------2222222222-----------");
				DssDataServiceImpl dssServiceObj = new DssDataServiceImpl();
				HashMap dssInputMap = new HashMap();
				String lStrLcTypeCode = bundleConst.getString("LC.EXP_TYPE_CODE");				
				glogger.info("----------EXP TYPE FOR DSS AT DEDUCTION-----------"+lStrLcTypeCode);
				glogger.info("--3333333333---"+lStrLcTypeCode+"======="+lcDedPostingVo.getLcExpId().toString()+"==="+lcDedPostingVo.getLcDedId().toString());
				
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("rcptNo", lcDedPostingVo.getLcExpId().toString());
				dssInputMap.put("rcptTypeCode", lStrLcTypeCode);
				dssInputMap.put("challanCatgCode", "Others");
				dssInputMap.put("trnReceiptId",lcDedPostingVo.getLcDedId().toString());
				
				RptReceiptDtls rptDssObj= new RptReceiptDtls();
				rptDssObj=(RptReceiptDtls)dssServiceObj.getReceiptData(dssInputMap);
				glogger.info("this is in LcAdsviceREceipved and chjangn the VO of refceot");
				rptDssObj.toString();
				glogger.info("---------------------------------------------------------------------------------------");
				
				
				glogger.info("----------44444444444-----------"+rptDssObj.toString());
				double lDLcDedAmt=Double.parseDouble(lcDedPostingVo.getAmount().toString());
				glogger.info("----------44444444444-----------"+lDLcDedAmt);
				rptDssObj.setAmount(new BigDecimal(lDLcDedAmt));				
				
				HashMap rptDssMap = new HashMap();
				rptDssMap.put("map", objectArgs);
				rptDssMap.put("RptReceiptVO", rptDssObj);
				glogger.info("----------44444444444-----------"+lDLcDedAmt);
				dssServiceObj.LcUpdateVO(rptDssMap);
				glogger.info("----------5555555555-----------");
				//-----------------------------------------------------------------
			
				
			}
			
			//-----------LOGIC FOR DSS -------------------
			
			DssDataServiceImpl dssServiceObj = new DssDataServiceImpl();
			HashMap dssInputMap = new HashMap();
			String lStrLcTypeCode = bundleConst.getString("LC.EXP_TYPE_CODE");
			glogger.info("----------BUD ID FOR DSS-----------"+lILcBudId);
			glogger.info("----------EXP TYPE FOR DSS-----------"+lStrLcTypeCode);
			
			dssInputMap.put("map", objectArgs);
			dssInputMap.put("expNo", lILcExpId);
			dssInputMap.put("expTypeCode", lStrLcTypeCode);
			RptExpenditureDtls rptDssObj=null;
			rptDssObj=(RptExpenditureDtls)dssServiceObj.getExpData(dssInputMap);
			
			dssInputMap = new HashMap();
			dssInputMap.put("amount", (0-lDDedTotalAmt));
			rptDssObj.setNetAmt(dssInputMap);
			
			dssInputMap = new HashMap();
			dssInputMap.put("map", objectArgs);
			dssInputMap.put("RptExpenditureVO", rptDssObj);
			
			dssServiceObj.updateExpData(dssInputMap);
			//-------------------------------------------
			//--------------END-----------------------		
			
			
			//---CODE TO REFRESH THE REPORT WITH CHANGES------------
			
			String lStrLcExpId="";
			String lStrYear="";
			String lStrMonthCode="";
			String lStrBankCode="";
			String lStrAdviceCode="";
			String lStrLcFromDt="";
			String lStrLcToDt="";
			String lStrEntryFromDt="";
			String lStrEntryToDt="";		
			String lStrStatusCode="";
			
			//----START 4 APPROVE UPDATE-------
			//String lStrLcExpId="";
			String lStrDivId="";
			//String lStrAdviceCode="";
			//String lStrMonthCode="";
			//String lStrBankCode="";			
			String lStrDept="";
			String lStrApproved="";
			String lStrFromDt="";
			String lStrToDt="";		
			//-------END--------------
			glogger.info("---------------BEFORE SESSION AT SAVED----------------------");
			if(session.getAttribute("lStrLcExpId") != null)
			{
				lStrLcExpId=(String)session.getAttribute("lStrLcExpId");
				glogger.info("---------LcExpId in REPORT SRVC at SAVED--------------"+lStrLcExpId);
			}
			if(session.getAttribute("lStrYear") != null)
			{
				lStrYear=(String)session.getAttribute("lStrYear");
				glogger.info("---------lStrYear in REPORT SRVC at SAVED--------------"+lStrYear);
			}
			if(session.getAttribute("lStrMonthCode") != null)
			{
				lStrMonthCode=(String)session.getAttribute("lStrMonthCode");
				glogger.info("---------lStrMonthCode in REPORT SRVC at SAVED--------------"+lStrMonthCode);
			}
			if(session.getAttribute("lStrBankCode") != null)
			{
				lStrBankCode=(String)session.getAttribute("lStrBankCode");
				glogger.info("---------lStrBankCode in REPORT SRVC at SAVED--------------"+lStrBankCode);
			}
			if(session.getAttribute("lStrAdviceCode") != null)
			{
				lStrAdviceCode=(String)session.getAttribute("lStrAdviceCode");
				glogger.info("---------lStrAdviceCode in REPORT SRVC at SAVED--------------"+lStrAdviceCode);
			}
			if(session.getAttribute("lStrLcFromDt") != null)
			{
				lStrLcFromDt=(String)session.getAttribute("lStrLcFromDt");
				glogger.info("---------lStrLcFromDt in REPORT SRVC at SAVED--------------"+lStrLcFromDt);
			}
			if(session.getAttribute("lStrLcToDt") != null)
			{
				lStrLcToDt=(String)session.getAttribute("lStrLcToDt");
				glogger.info("---------lStrLcToDt in REPORT SRVC at SAVED--------------"+lStrLcToDt);
			}
			if(session.getAttribute("lStrEntryFromDt") != null)
			{
				lStrEntryFromDt=(String)session.getAttribute("lStrEntryFromDt");
				glogger.info("---------lStrEntryFromDt in REPORT SRVC at SAVED--------------"+lStrEntryFromDt);
			}
			if(session.getAttribute("lStrEntryToDt") != null)
			{
				lStrEntryToDt=(String)session.getAttribute("lStrEntryToDt");
				glogger.info("---------lStrEntryToDt in REPORT SRVC at SAVED--------------"+lStrEntryToDt);
			}
			if(session.getAttribute("lStrStatusCode") != null)
			{
				lStrStatusCode=(String)session.getAttribute("lStrStatusCode");
				glogger.info("---------lStrStatusCode in REPORT SRVC at SAVED--------------"+lStrStatusCode);
			}
						
			//-----START 4 APPROVE UPDATE-----------------
			if(session.getAttribute("lStrDivId") != null)
			{
				lStrDivId=(String)session.getAttribute("lStrDivId");
				glogger.info("---------lStrDivId in REPORT SRVC at APPROVED SAVED--------------"+lStrDivId);
			}
			if(session.getAttribute("lStrDept") != null)
			{
				lStrDept=(String)session.getAttribute("lStrDept");
				glogger.info("---------lStrDept in REPORT SRVC at APPROVED SAVED--------------"+lStrDept);
			}
			if(session.getAttribute("lStrApproved") != null)
			{
				lStrApproved=(String)session.getAttribute("lStrApproved");
				glogger.info("---------lStrApproved in REPORT SRVC at APPROVED SAVED--------------"+lStrApproved);
			}
			if(session.getAttribute("lStrFromDt") != null)
			{
				lStrFromDt=(String)session.getAttribute("lStrFromDt");
				glogger.info("---------lStrFromDt in REPORT SRVC at APPROVED SAVED--------------"+lStrFromDt);
			}
			if(session.getAttribute("lStrToDt") != null)
			{
				lStrToDt=(String)session.getAttribute("lStrToDt");
				glogger.info("---------lStrToDt in REPORT SRVC at APPROVED SAVED--------------"+lStrToDt);
			}
			
			//-----------END-------------------------
			
			request.setAttribute("lStrYear", lStrYear);
			request.setAttribute("lStrMonthCode", lStrMonthCode);
			request.setAttribute("lStrBankCode", lStrBankCode);
			request.setAttribute("lStrAdviceCode", lStrAdviceCode);
			request.setAttribute("lStrLcFromDt", lStrLcFromDt);
			request.setAttribute("lStrLcToDt", lStrLcToDt);
			request.setAttribute("lStrEntryFromDt", lStrEntryFromDt);
			request.setAttribute("lStrEntryToDt", lStrEntryToDt);
			request.setAttribute("lStrStatusCode", lStrStatusCode);
			//-----------START 4 APPROVE------------------
			request.setAttribute("lStrDivId", lStrDivId);
			request.setAttribute("lStrDept", lStrDept);
			request.setAttribute("lStrApproved", lStrApproved);
			request.setAttribute("lStrFromDt", lStrFromDt);
			request.setAttribute("lStrToDt", lStrToDt);
			//-----------END------------------------------
			glogger.info("---------------BEFORE SESSION REMOVED AT SAVED----------------------");
			session.removeAttribute("lStrLcExpId");
			session.removeAttribute("lStrYear");
			session.removeAttribute("lStrMonthCode");
			session.removeAttribute("lStrBankCode");
			session.removeAttribute("lStrAdviceCode");
			session.removeAttribute("lStrLcFromDt");
			session.removeAttribute("lStrLcToDt");
			session.removeAttribute("lStrEntryFromDt");
			session.removeAttribute("lStrEntryToDt");	
			session.removeAttribute("lStrStatusCode");
			//------START 4 APPROVE-----------------------
			session.removeAttribute("lStrDivId");
			session.removeAttribute("lStrDept");
			session.removeAttribute("lStrApproved");
			session.removeAttribute("lStrFromDt");
			session.removeAttribute("lStrToDt");	
			//-------END----------------------------------
			
			if(lStrDivId.equals("") && !lStrYear.equals(""))
			{
				glogger.info("----------------DELETE INTERMEDIATE RETURN FROM SRVC----------");
				objRes.setResultValue(objectArgs);
				objRes.setViewName("LcIntermediateForDelete");
			}
			else
			{
				glogger.info("----------------APPROVED INTERMEDIATE RETURN FROM SRVC--------");
				objRes.setResultValue(objectArgs);
				objRes.setViewName("LcIntermediateForApproved");
			}
			
			
			//objectArgs.put("lArrChqPosting", lArrChqPosting);
		    	
			
			
			//-------------------END OF REFRESH REPORT CHANGE CODE---------------			
			
		}
		catch(Exception e)
		{
			glogger.error("Error in updateAdviceReceivedDtls of LCAdviceReceivedUpdateServiceImpl ", e);
			glogger.info("Error in updateAdviceReceivedDtls of LCAdviceReceivedUpdateServiceImpl ");
			e.printStackTrace();
		}
	    return objRes;
		
	}
	
	//-----METHOD FOR LC TSRY REPORT FOR VERIFICATION-----------------
	public ResultObject approveAdviceReceived(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		glogger.info("..........Inside the  approveAdviceReceived in LCAdviceReceivedUpdateServiceImpl..............");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		try
		{
			
			LCReportQueryDAOImpl lcReportQueryDao = new LCReportQueryDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
			
			HttpSession hs = request.getSession();
			//Long lLngLangId = SessionHelper.getLangId(request);
			//String lStrLangId = lLngLangId.toString();
			//int lILangId = Integer.parseInt(lStrLangId);
			
			String lStrLcExpId="";
			String lStrDivId="";
			String lStrAdviceCode="";
			String lStrMonthCode="";
			String lStrBankCode="";			
			String lStrDept="";
			String lStrApproved="";
			String lStrFromDt="";
			String lStrToDt="";			
			
			if(request.getParameter("LcExpId") != null)
			{
				lStrLcExpId=(String)request.getParameter("LcExpId");
				glogger.info("---------LcExpId in REPORT SRVC at APPROVED--------------"+lStrLcExpId);
			}
			if(request.getParameter("DivisionId") != null)
			{
				lStrDivId=(String)request.getParameter("DivisionId");
				glogger.info("---------lStrDivId in REPORT SRVC at APPROVED--------------"+lStrDivId);
			}
			if(request.getParameter("AdviceCode") != null)
			{
				lStrAdviceCode=(String)request.getParameter("AdviceCode");
				glogger.info("---------lStrAdviceCode in REPORT SRVC at APPROVED--------------"+lStrAdviceCode);
			}
			if(request.getParameter("MonthCode") != null)
			{
				lStrMonthCode=(String)request.getParameter("MonthCode");
				glogger.info("---------lStrMonthCode in REPORT SRVC at APPROVED--------------"+lStrMonthCode);
			}
			if(request.getParameter("BankCode") != null)
			{
				lStrBankCode=(String)request.getParameter("BankCode");
				glogger.info("---------lStrBankCode in REPORT SRVC at APPROVED--------------"+lStrBankCode);
			}
			
			if(request.getParameter("DepartmentId") != null)
			{
				lStrDept=(String)request.getParameter("DepartmentId");
				glogger.info("---------lStrDept in REPORT SRVC at APPROVED--------------"+lStrDept);
			}
			if(request.getParameter("ApprovedId") != null)
			{
				lStrApproved=(String)request.getParameter("ApprovedId");
				glogger.info("---------lStrApproved in REPORT SRVC at APPROVED--------------"+lStrApproved);
			}
			if(request.getParameter("FromDate") != null)
			{
				lStrFromDt=(String)request.getParameter("FromDate");
				glogger.info("---------lStrFromDt in REPORT SRVC at APPROVED--------------"+lStrFromDt);
			}
			if(request.getParameter("ToDate") != null)
			{
				lStrToDt=(String)request.getParameter("ToDate");
				glogger.info("---------lStrToDt in REPORT SRVC at APPROVED--------------"+lStrToDt);
			}
			
			boolean approveAdviceResult=false;
			boolean updateApproveAmtResult=false;
			long lILcExpId=0;
			String lStrSign="-";
			if(!lStrLcExpId.equals(""))
			   lILcExpId=Long.parseLong(lStrLcExpId);
			
			approveAdviceResult=(boolean)lcReportQueryDao.approveAdviceReceived(lILcExpId);
			glogger.info("--------ADVICE APPROVE RESULT IN APPROVE SEVC------------------"+approveAdviceResult);
			
			if(approveAdviceResult==true)
			{
				updateApproveAmtResult=(boolean)lcReportQueryDao.updateApprovedLcAmount(lILcExpId,lStrSign);
				glogger.info("---UPDATE LC APPROVED AMT RESULT IN APPROVE SEVC------"+updateApproveAmtResult);
			}
			
			//---LOGIC FOR DSS SATA SERVICE----------------------
			
			ArrayList lArrBudId=lcReportQueryDao.getLcBudIdByLcExpId(lILcExpId);
			ArrayList lArrDedId=lcReportQueryDao.getLcDedIdByLcExpId(lILcExpId);
			glogger.info("-------SIZE OF ARR LST IN APPROVE--------------"+lArrBudId.size());
			glogger.info("-------SIZE OF ARR LST IN APPROVE lArrDedId--------------"+lArrDedId.size());
			for(int i=0;i<lArrBudId.size();i++)
			{
				String lStrLcBudId=(String)lArrBudId.get(i);
				
				glogger.info("----------2222222222-----------");
				DssDataServiceImpl dssServiceObj = new DssDataServiceImpl();
				HashMap dssInputMap = new HashMap();
				String lStrLcTypeCode = bundleConst.getString("LC.EXP_TYPE_CODE");
				glogger.info("----------BUD ID FOR DSS-----------"+lStrLcBudId);
				glogger.info("----------EXP TYPE FOR DSS-----------"+lStrLcTypeCode);
				glogger.info("----------3333333333----------");
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("expNo", lStrLcBudId);
				dssInputMap.put("expTypeCode", lStrLcTypeCode);
				RptExpenditureDtls rptDssObj=null;
				rptDssObj=(RptExpenditureDtls)dssServiceObj.getExpData(dssInputMap);
				glogger.info("----------44444444444-----------");
				
				rptDssObj.setExpDt(new Date());
				rptDssObj.setExpStatusCode(bundleConst.getString("LC.AUDIT_APPROVED"));
				rptDssObj.setExpStatusDt(new Date());
				
				glogger.info("----------5555555555----------");
				dssInputMap = new HashMap();
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("RptExpenditureVO", rptDssObj);
				glogger.info("------CNTR 1------------");
				dssServiceObj.updateExpData(dssInputMap);
				glogger.info("------CNTR------------");
			}
			glogger.info("------AFTER LOOP------------");
			DssDataServiceImpl dssServiceObj = new DssDataServiceImpl();
			HashMap dssInputMap = new HashMap();
			glogger.info("------AFTER LOOP 2------------");
			String lStrLcTypeCode = bundleConst.getString("LC.EXP_TYPE_CODE");
			
			glogger.info("----------lILcExpId ID FOR DSS-----------"+lILcExpId);
			glogger.info("----------EXP TYPE FOR DSS-----------"+lStrLcTypeCode);
			glogger.info("----------6666666666----------");
			dssInputMap.put("map", objectArgs);
			dssInputMap.put("expNo", lILcExpId);
			dssInputMap.put("expTypeCode", lStrLcTypeCode);
			RptExpenditureDtls rptDssObj=null;
			rptDssObj=(RptExpenditureDtls)dssServiceObj.getExpData(dssInputMap);
			glogger.info("----------77777777777777777-----------");
			
			rptDssObj.setExpDt(new Date());
			rptDssObj.setExpStatusCode(bundleConst.getString("LC.AUDIT_APPROVED"));
			rptDssObj.setExpStatusDt(new Date());
			
			dssInputMap = new HashMap();
			dssInputMap.put("map", objectArgs);
			dssInputMap.put("RptExpenditureVO", rptDssObj);
			
			dssServiceObj.updateExpData(dssInputMap);
			
			String lStrOthers=bundleConst.getString("LC.CHALAN_CODE");
			for(int i=0;i<lArrDedId.size();i++)
			{
				String lStrLcDedId=(String)lArrDedId.get(i);
				
				dssServiceObj = new DssDataServiceImpl();
				dssInputMap = new HashMap();
				
				glogger.info("----------lStrLcDedId ID FOR DSS-----------"+lStrLcDedId);
				glogger.info("----------EXP TYPE FOR DSS-----------"+lStrLcTypeCode+"====="+lStrOthers);
				glogger.info("-------88888888888888---------");
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("rcptNo", lILcExpId);
				dssInputMap.put("rcptTypeCode", lStrLcTypeCode);
				dssInputMap.put("challanCatgCode", lStrOthers);
				dssInputMap.put("trnReceiptId", lStrLcDedId);
				RptReceiptDtls dssRcptObj=null;
				glogger.info("-------999999999999999---------");
				dssRcptObj=dssServiceObj.getReceiptData(dssInputMap);
				glogger.info("-------999999999999999&&&&&&&&&---------");
				dssRcptObj.setRevenueDt(new Date());
				dssRcptObj.setRcptStatusCode(bundleConst.getString("LC.APPROVED"));
				dssRcptObj.setRcptStatusDate(new Date());
				
				dssInputMap = new HashMap();
				
				dssInputMap.put("map", objectArgs);
				dssInputMap.put("RptReceiptVO", dssRcptObj);
				
				dssServiceObj.LcUpdateVO(dssInputMap);
				glogger.info("-------1111111111&&&&&&&&&&&111111111111---------");
				
			}
			//---------------------------------------------------
			
			glogger.info("-------1111111111&&&&&&&&&&&22222222222---------");
			request.setAttribute("lStrDivId", lStrDivId);
			request.setAttribute("lStrAdviceCode", lStrAdviceCode);
			request.setAttribute("lStrMonthCode", lStrMonthCode);
			request.setAttribute("lStrBankCode", lStrBankCode);			
			request.setAttribute("lStrDept", lStrDept);
			request.setAttribute("lStrApproved", lStrApproved);
			request.setAttribute("lStrFromDt", lStrFromDt);
			request.setAttribute("lStrToDt", lStrToDt);
			
			//objectArgs.put("lArrChqPosting", lArrChqPosting);
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("LcIntermediateForApproved");
			
			glogger.info("------------------end of APPROVEAdviceReceivedDtls---------");
			
		}
		catch(Exception e)
		{
			glogger.error("Error in getInitAdviceDtls of LCAdviceReceivedUpdateServiceImpl ", e);
			glogger.info("Error in getInitAdviceDtls of LCAdviceReceivedUpdateServiceImpl ");
			e.printStackTrace();
		}
	return objRes;
   }
	
	public void insertDSSChqData(TrnLcChequePosting chqVO,ServiceLocator serv,Map objArgs,String lStrTsryCode,String lStrBankId)
	{
		RptPaymentDtls paymentVo =new RptPaymentDtls();
		DssDataServiceImpl lObjDSS = new DssDataServiceImpl();
		
		String chqTypeCode=bundleConst.getString("LC.CHQ_TYPE_CODE");
		String chqStatusCode=bundleConst.getString("LC.CHQ_STATUS_CODE_UNPAID");
		
		paymentVo.setTsryCode(lStrTsryCode);
		paymentVo.setChqNo(chqVO.getChequeNo());
		paymentVo.setAmount(chqVO.getChequeAmt());
		paymentVo.setChqTypeCode(chqTypeCode);
		paymentVo.setFinYrId(new BigDecimal(chqVO.getFinYearId()));
		paymentVo.setPartyName(chqVO.getPartyName());
		paymentVo.setChqStatusDt(new Date());
		paymentVo.setChqStatusCode(chqStatusCode);
		paymentVo.setBankCode(lStrBankId);
		
		Map inputMap =new HashMap();
		inputMap.put("map", objArgs);
		inputMap.put("RptPaymentVO", paymentVo);
		
		lObjDSS.insertPaymentData(inputMap);
		
		
	}
	
	
	public void insertDSSBudData(TrnLcBudgetPosting budVO,ServiceLocator serv, String lStrDistCode, Map objArgs,String lStrAdvApproved,String lStrTsryCode,long lLngLangId)
	{
		DssDataServiceImpl lObjDSS = new DssDataServiceImpl();
		RptExpenditureDtls lObjExpVO = new RptExpenditureDtls();
		LcAdviceReceiptDAOImpl lObjLC = new LcAdviceReceiptDAOImpl(TrnLcDeductionPosting.class,serv.getSessionFactory());
		SgvaBudbpnMapping lObjBudBpnVO = new SgvaBudbpnMapping();
		BudgetHdDtlsDAOImpl lObjBudDAOImpl = new BudgetHdDtlsDAOImpl(budVO.getFinYearId(),serv.getSessionFactory());
		
		
		String lStrDeptId="";
		
		lObjBudBpnVO=lObjBudDAOImpl.getBPNInfoFrmDmd(budVO.getDemandNo(), lLngLangId);
		lStrDeptId=lObjBudBpnVO.getDeptId();
		String lStrDeptCode = lObjLC.getDeptCode(lStrDeptId, lLngLangId);
		
		HashMap lHmpAmt=null;
		String lStrCls="";
		String lStrBud="";
		String lStrFundType="";
		
		String lStrLcTypeCode=bundleConst.getString("LC.EXP_TYPE_CODE");
		
		//lObjExpVO.setDeptCode(lStrDeptCode);
		//lObjExpVO.setDistrictCode(lStrDistCode);
		lObjExpVO.setDeptCode(lStrDeptCode);
		lObjExpVO.setDistrictCode(lStrDistCode);
		lObjExpVO.setDdoCode(budVO.getDrwOff());
		lObjExpVO.setTsryCode(lStrTsryCode);
		lObjExpVO.setExpNo(budVO.getLcBudId());
		lObjExpVO.setExpTypeCode(lStrLcTypeCode);
		lStrCls ="LC.CLASS_DSS"+budVO.getClassOfExp().toString();
		lStrBud="LC.BUD_DSS"+budVO.getBudgetType().toString();
		lObjExpVO.setClsExpCode((String)bundleConst.getString(lStrCls));
		
		lStrFundType=bundleConst.getString("LC.FUND_DSS"+budVO.getFund().toString());
		
		lObjExpVO.setFundTypeCode(lStrFundType);
		lObjExpVO.setBudTypeCode((String)bundleConst.getString(lStrBud));
		lObjExpVO.setDemandNo(budVO.getDemandNo());
		lObjExpVO.setScheme(budVO.getSchemeNo());
		lObjExpVO.setMjrHd(budVO.getMjrHd().toString());
		lObjExpVO.setMinHd(budVO.getMinHd().toString());
		lObjExpVO.setSubMjrHd(budVO.getSubMjrHd().toString());
		lObjExpVO.setSubHd(budVO.getSubHd().toString());
		lObjExpVO.setDtlHd(budVO.getDtlHd().toString());
		lObjExpVO.setFinYrId(new BigDecimal(budVO.getFinYearId()));
		
		if(lStrAdvApproved.equals("0"))
		{
			lObjExpVO.setExpDt(new Date());
			lObjExpVO.setExpStatusCode(bundleConst.getString("LC.AUDIT_APPROVED"));
			lObjExpVO.setExpStatusDt(new Date());
		}
		else
		{
			lObjExpVO.setExpStatusCode(bundleConst.getString("LC.AUDIT_PENDING"));
			lObjExpVO.setExpStatusDt(new Date());
		}
		
		/*lHmpAmt = new HashMap();
		lHmpAmt.put("amount", budVO.getExpAmt().toString());
		lHmpAmt.put("calculate", "false");
		lObjExpVO.setGrossAmnt(lHmpAmt);
		//lObjExpVO.setExpStatusCode(expStatusCode);
		lHmpAmt = new HashMap();
		lHmpAmt.put("amount", budVO.getExpAmt().toString());
		lObjExpVO.setNetAmt(lHmpAmt);
		
		lHmpAmt = new HashMap();
		lHmpAmt.put("amount", budVO.getExpAmt().toString());
		lObjExpVO.setExpAmt(lHmpAmt);*/
		
		RptExpEdpDtls edpDssObj=new RptExpEdpDtls();
		
		edpDssObj.setAmount(budVO.getExpAmt());
		edpDssObj.setObjHd(budVO.getObjHd());
		edpDssObj.setEdpType('0');
		edpDssObj.setTrnExpEdpId(budVO.getLcBudId());
		
		ArrayList lArrLst=new ArrayList();
		lArrLst.add(edpDssObj);
		
		lHmpAmt = new HashMap();
		
		lHmpAmt.put("map", objArgs);
		lHmpAmt.put("RptExpenditureVO", lObjExpVO);
		lHmpAmt.put("RptExpEdpVOArrLst", lArrLst);
		
		HashMap lHmpReturnMap = lObjDSS.insertExpData(lHmpAmt);
		//--->>need to 
		glogger.info("=======================return map after calling DSS insertExpData service is  :: "+lHmpReturnMap);
		
		
		
		
		
	}	
	
	//----END--------------------------
	
}
