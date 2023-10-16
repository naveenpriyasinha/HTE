package com.tcs.sgv.exprcpt.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.cheque.dao.TrnChequeDAO;
import com.tcs.sgv.billproc.cheque.dao.TrnChequeDAOImpl;
import com.tcs.sgv.billproc.cheque.valueobject.TrnChequeDtls;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.LocationDAO;
import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.exprcpt.dao.ReportDAO;
import com.tcs.sgv.exprcpt.dao.ReportDAOImpl;
import com.tcs.sgv.exprcpt.helper.ReportHelper;
import com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl;
import com.tcs.sgv.exprcpt.valueobject.BudHdAmtVO;
import com.tcs.sgv.exprcpt.valueobject.MstListPayRcpt;

/**
 * A class that implements reports related services 
 * 
 * @author 206819
 */
public class ReportServiceImpl extends ServiceImpl implements ReportService {
	private static Log logger = LogFactory.getLog(ReportServiceImpl.class); 
	
	/**
	 * This method generates list of payment report
	 * 
	 * @param objectArgs
	 * @return ResultObject 
	 */
	public ResultObject getLstOfPay(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			String tsryLocId = null;
			Date fromDate = null;
			Date toDate = null;
			boolean subTsry = false;
			boolean firstList = false;
			
			long sLagId = SessionHelper.getLangId(objectArgs) ;
			
			try {tsryLocId = request.getParameter("cmbTrsy");}catch(Exception ex){}
			try {fromDate = sm.parse(request.getParameter("fromDate"));}catch(Exception ex){}
			try {toDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("toDate"));}catch(Exception ex){}
			if (request.getParameter("cmbSubTrsy")!=null && request.getParameter("cmbSubTrsy").equalsIgnoreCase("yes")) subTsry=true;
			if (request.getParameter("cmbFirstList")!=null && request.getParameter("cmbFirstList").equalsIgnoreCase("yes")) firstList=true;
		
			List<MstListPayRcpt> oPayMtData = getLoPValue(fromDate, toDate, tsryLocId, subTsry, serv, firstList,sLagId);
			List<MstListPayRcpt> oRecMtData = getLoRValue(fromDate, toDate, tsryLocId, subTsry, serv,sLagId);
			
			MstListPayRcpt PayGrandTotal = (MstListPayRcpt)oPayMtData.get(652); 
			MstListPayRcpt RecGrandTotal = (MstListPayRcpt)oRecMtData.get(590);
			
			BigDecimal oRbd = RecGrandTotal.getAmount().subtract(PayGrandTotal.getAmount());
			if(oRbd.longValue() > 0)
			{
				PayGrandTotal.setAmount(PayGrandTotal.getAmount().add(oRbd));
				MstListPayRcpt oRbdRow1 = (MstListPayRcpt)oPayMtData.get(643);
				MstListPayRcpt oRbdRow2 = (MstListPayRcpt)oPayMtData.get(538);
				oRbdRow1.setAmount(oRbd);
				oRbdRow2.setAmount(oRbd);
			}  
				
			LocationDAOImpl cmnLocDAO = new LocationDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
			
			String sLocationName = cmnLocDAO.getDeptNameByLocCode(tsryLocId,"" + 1) ;
			String sParameter = "From " + sm.format(fromDate) +" To " + sm.format(toDate); 
			
			objectArgs.put("lstOfPay", getPrintableFormat(oPayMtData,sLocationName,"LIST OF PAYMENT",sParameter));
			objectArgs.put("lstOfPayForPrint", getPrintableFormatForDotMatrix(oPayMtData,sLocationName,"LIST OF PAYMENTS",sParameter));
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lstOfPayFile");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
		}
		return objRes;

	}
	
	
	
	private List getLoRValue(Date fromDate,Date toDate,String tsryLocId,boolean subTsry, ServiceLocator serv,long langId)
	{
		ReportDAO rptDAO = new ReportDAOImpl(serv.getSessionFactory());
		List<BudHdAmtVO> receiptList = new ArrayList<BudHdAmtVO>();
		receiptList = rptDAO.getReceiptByBudHds(tsryLocId, fromDate, toDate, subTsry,langId);
		//System.out.println("Receipt List :    " + receiptList.size() );
		List receiptMtData = rptDAO.getRptMetaData(ReportDAOImpl.LIST_OF_RCPT);			
	
		/** Calculate total amount of paid cheques (TC) between fromDate and to Date ..*/ 
		List<String> chqTypes = new ArrayList<String>();
		CmnLookupMstDAOImpl cmnLookupDAO =  new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		/** get treasury cheques data .. */
		TrnChequeDAO chequeDAO = new TrnChequeDAOImpl(TrnChequeDtls.class,serv.getSessionFactory());
		BudHdAmtVO VO = new BudHdAmtVO();
		chqTypes.add(DBConstants.CHEQ_TYPE_TC);
		VO.setAmount(chequeDAO.getUnpaidChequesSum(fromDate, toDate, chqTypes,tsryLocId));
		VO.setFormula("867000104");
		//System.out.println(" Receipt Cheque Total : " + VO.getAmount());

		receiptList.add(VO);
		ReportHelper.procLstOfPayRcpt(receiptList, receiptMtData);
		return receiptMtData;
	}
	
	private List getLoPValue(Date fromDate,Date toDate,String tsryLocId,boolean subTsry, ServiceLocator serv,boolean firstList,long sLangId)
	{
		ReportDAO rptDAO = new ReportDAOImpl(serv.getSessionFactory());
		List<BudHdAmtVO> payList = new ArrayList<BudHdAmtVO>();
		
		payList = rptDAO.getPayByBudHds(tsryLocId, fromDate, toDate, subTsry,sLangId);
		//System.out.println("payList :    " + payList.size() );

		List payMtData = rptDAO.getRptMetaData(ReportDAOImpl.LIST_OF_PAY);			
			
		/** Calculate total amount of paid cheques (TC) between fromDate and to Date ..*/ 
		
		/*Disburment amount*/
		List<BudHdAmtVO> disAmts = null;
		
		disAmts = rptDAO.getPaymentAsDisbursment(tsryLocId, fromDate, toDate, subTsry,sLangId);
		payList.addAll(disAmts);
		
		
		/** get capital heads data if its first list .. */
		List<BudHdAmtVO> captHds = null;
		if (!firstList) 
		{
			//System.out.println("Inside first list .. ");
			captHds = rptDAO.getRcptByBudHd(tsryLocId, fromDate, toDate, subTsry, "4046","5959",ReportDAOImpl.OP_NEG,sLangId);
			//System.out.println("capt Hds :   " + captHds.size());
			payList.addAll(captHds);
			//System.out.println("payList :    " + payList.size());
		
		}
		/** get treasury cheques data .. */
		  TrnChequeDAO chequeDAO = new TrnChequeDAOImpl(TrnChequeDtls.class,serv.getSessionFactory());
		  List<String> chqTypes = new ArrayList<String>();
		  chqTypes.add(DBConstants.CHEQ_TYPE_TC);
    	  BudHdAmtVO VO = new BudHdAmtVO();
		  if(!firstList)
		  {
			  VO.setAmount(chequeDAO.getPaidChequesSum(fromDate, toDate, chqTypes,tsryLocId));
			  //System.out.println("setting VO amount" + VO.getAmount());
		  }
		  else
		  {
			  VO.setAmount(new BigDecimal(0.0));
			  //System.out.println("setting Vo amount");
		  }
		  VO.setFormula("867000104");
		  payList.add(VO);
			
		ReportHelper.procLstOfPayRcpt(payList, payMtData);
			
		return payMtData;
	}
	/**
	 * This method generates list of payment report
	 * 
	 * @param objectArgs
	 * @return ResultObject 
	 */
	public ResultObject getLstOfPayRcptFrm(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		
		try
		{
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}			
		
			LocationDAO locDAO = new LocationDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
			List trsyList = locDAO.getLocByDept(DBConstants.DEPT_TREASURY_OFFICE);
			//System.out.println("Treasury List : " + trsyList.size());
			objectArgs.put("ReportType",request.getParameter("type"));
			objectArgs.put("trsyList", trsyList);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lstOfPayRcptFrm");
			
			
			
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
		}
		return objRes;

	}
	
	public ResultObject getMajHdWiseStatRptFrm(Map<String,Object> objectArgs)
	{
		//System.out.println("into the getMajorHeadWiseStatusRpt");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		try
		{
			Long LangId = SessionHelper.getLangId(objectArgs);
			String lstrLocId = (SessionHelper.getLocationId(objectArgs)).toString();
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			String sLocation ="";
			if(request.getParameter("cmbTrsy")!=null)
				sLocation = request.getParameter("cmbTrsy");
			ReportQueryDAOImpl reportQueryDAO = new ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
			if(!sLocation.equals(""))
			{
				//List subTresury = reportQueryDAO.getSubTsry(sLocation);
				//objectArgs.put("SubTresuryList", subTresury);
			}
			
			CmnLookupMstDAOImpl cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			 
			List type = cmnDAO.getAllChildrenByLookUpNameAndLang("Type",SessionHelper.getLangId(objectArgs));
			objectArgs.put("type",type);
			LocationDAO locDAO = new LocationDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
			List trsyList = locDAO.getLocByDept(DBConstants.DEPT_TREASURY_OFFICE);
			
			String lStrLangId = reportQueryDAO.getLangName(LangId);
			List mjrheadList = reportQueryDAO.getMajorHead(lStrLangId, lstrLocId);
			//System.out.println("MajorHead List:" +mjrheadList.size());
			objectArgs.put("mjrheadList", mjrheadList);
			objectArgs.put("trsyList", trsyList);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("MajorHeadWiseStatusRptFrm");
		    
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
		}
		return objRes;
	}
	
	public ResultObject getMajHdWisePayRcptRpt(Map objectArgs)
	{
		//System.out.println("Inside calling jspppppppppp");	
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		//System.out.println("Inside calling jspppppppppp");
		String getMajHeadStatusRpt = null;
		ReportDAOImpl rptDAO = new ReportDAOImpl(serv.getSessionFactory());
		ReportQueryDAOImpl reportQueryDAO = new ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
		
		try
		{
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			String tsryLocId = null;
			Date fromDate = null;
			Date toDate = null;
			String subtsryLocId = null;
			String[] majorHead = null;
			String type = null;
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			Long sLangId = SessionHelper.getLangId(objectArgs);
			Long sLocId = SessionHelper.getLocationId(objectArgs);
			try {tsryLocId =request.getParameter("cmbTrsy");}catch(Exception ex){}
			try{subtsryLocId = request.getParameter("cmbSubTrsy");}catch(Exception ex){}
			try {fromDate = sm.parse(request.getParameter("fromDate"));}catch(Exception ex){}
			try {toDate = sm.parse(request.getParameter("toDate"));}catch(Exception ex){}
			try{majorHead=request.getParameterValues("MajorHead");}catch(Exception ex){}
			try{type=request.getParameter("type");}catch(Exception ex){}
			String lStrLangId = reportQueryDAO.getLangName(sLangId);
			//System.out.println(tsryLocId);
			//System.out.println(subtsryLocId);
			//System.out.println("Type is:" +type);
			//System.out.println("langId:" +lStrLangId);
			//System.out.println("locId:" +sLocId);
			if(type.equals("Payment"))
			{
				
					
					getMajHeadStatusRpt = rptDAO.getMajHdStatusPayRpt(fromDate, toDate, tsryLocId, subtsryLocId,majorHead, type, lStrLangId, sLocId);
					
				
			}
			else
			{
				
				    getMajHeadStatusRpt = rptDAO.getMajHdStatusRcptRpt(fromDate, toDate, tsryLocId, subtsryLocId,majorHead, type, lStrLangId, sLocId);
				
			}
			objectArgs.put("getMajHeadStatusRpt", getMajHeadStatusRpt);
			
			objRes.setResultValue(objectArgs);
			//System.out.println("Inside calling jsp");
			objRes.setViewName("MajorHeadWisePayRcptFrm");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
		}
		return objRes;
	}

	
	public ResultObject getListOfReceipt(Map<String, Object> objectArgs) 
	{
		
		//System.out.println("--------------------------------------------");
		//System.out.println("LOR");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		try
		{
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}

			String tsryLocId = null;
			Date fromDate = null;
			Date toDate = null;
			boolean subTsry = false;
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			
			try {tsryLocId =request.getParameter("cmbTrsy");}catch(Exception ex){}
			try {fromDate = sm.parse(request.getParameter("fromDate"));}catch(Exception ex){}
			try {toDate = sm.parse(request.getParameter("toDate"));}catch(Exception ex){}
			if (request.getParameter("cmbSubTrsy")!=null && request.getParameter("cmbSubTrsy").equalsIgnoreCase("yes")) subTsry=true;
			
			long sLangId = SessionHelper.getLangId(objectArgs) ;
			
			List ReceiptList = getLoRValue(fromDate, toDate, tsryLocId, subTsry, serv,sLangId);
			List PaymentList = getLoPValue(fromDate, toDate, tsryLocId, subTsry, serv,false,sLangId);
			
			MstListPayRcpt PayGrandTotal = (MstListPayRcpt)PaymentList.get(652); 
			MstListPayRcpt RecGrandTotal = (MstListPayRcpt)ReceiptList.get(590); 
			
			BigDecimal oRbd = RecGrandTotal.getAmount().subtract(PayGrandTotal.getAmount());
			if(oRbd.longValue() < 0)
			{
				oRbd = oRbd.multiply(new BigDecimal(-1.00));
				
				RecGrandTotal.setAmount(PayGrandTotal.getAmount());
				MstListPayRcpt oRbdRow1 = (MstListPayRcpt)ReceiptList.get(584);
				MstListPayRcpt oRbdRow2 = (MstListPayRcpt)ReceiptList.get(474);
				oRbdRow1.setAmount(oRbd);
				oRbdRow2.setAmount(oRbd);
			}  
			LocationDAOImpl cmnLocDAO = new LocationDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
			String sLocation =  cmnLocDAO.getDeptNameByLocCode(tsryLocId,"" + 1);
			String sParameter = "From " + sm.format(fromDate) +" To " + sm.format(toDate);
			
			objectArgs.put("lstOfReceipt",getPrintableFormat(ReceiptList,sLocation,"CASH ACCOUNT",sParameter));
			objectArgs.put("lstOfPayForPrint", getPrintableFormatForDotMatrix(ReceiptList,sLocation,"CASH ACCOUNT",sParameter));
			objRes.setResultValue(objectArgs);
			objRes.setViewName("lstOfPayFile");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
		}
		return objRes;

	}
	/***************************************************************************
	  * Author : Jignesh Sakhiya
	  * Service to Generate CashBook Payment
	 **************************************************************************/
	public ResultObject getCashBookPayment(Map<String, Object> objectArgs) 
	{
		//System.out.println("Cashbook Payment");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		try
		{
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			String tsryLocId = null;
			Date fromDate = null;
			Date toDate = null;
			boolean subTsry = false;
			List<Date> DateRange = null;
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat smShortDate = new SimpleDateFormat("dd/MM/");
			ArrayList<String> Headers = new ArrayList<String>();
			TreeMap<String,List> tm = new TreeMap<String,List>();
			
			try {tsryLocId = request.getParameter("cmbTrsy");}catch(Exception ex){}
			try {fromDate = sm.parse(request.getParameter("fromDate"));}catch(Exception ex){}
			try {toDate =   sm.parse(request.getParameter("toDate"));}catch(Exception ex){}
			if (request.getParameter("cmbSubTrsy")!=null && request.getParameter("cmbSubTrsy").equalsIgnoreCase("yes")) subTsry=true;
			LocationDAOImpl cmnLocDAO = new LocationDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
			Headers.add("Head of Expenditure");
			DateRange = getDateList(fromDate, toDate);
			long sLangId = SessionHelper.getLangId(objectArgs);
			for (Date oDate : DateRange) 
			{
				
				List oPayMtData = getLoPValue(oDate,oDate, tsryLocId, subTsry, serv,false,sLangId);
				List oRecMtData = getLoRValue(oDate, oDate, tsryLocId, subTsry, serv,sLangId);
				
				MstListPayRcpt PayGrandTotal = (MstListPayRcpt)oPayMtData.get(652); 
				MstListPayRcpt RecGrandTotal = (MstListPayRcpt)oRecMtData.get(590);
				
				BigDecimal oRbd = RecGrandTotal.getAmount().subtract(PayGrandTotal.getAmount());
				if(oRbd.longValue() > 0)
				{
					PayGrandTotal.setAmount(PayGrandTotal.getAmount().add(oRbd));
					MstListPayRcpt oRbdRow1 = (MstListPayRcpt)oPayMtData.get(643);
					MstListPayRcpt oRbdRow2 = (MstListPayRcpt)oPayMtData.get(538);
					oRbdRow1.setAmount(oRbd);
					oRbdRow2.setAmount(oRbd);
				}
				tm.put(sm.format(oDate),oPayMtData);
				Headers.add(smShortDate.format(oDate));
			}
			
			Headers.add("Total");
			String sLocationName = cmnLocDAO.getDeptNameByLocCode(tsryLocId,"1");
			String sParameter = "From " + sm.format(fromDate) +" To " + sm.format(toDate);
			getTotal(tm);
			objectArgs.put("CashBookPayData",getPrintableFormat(tm, Headers,sLocationName,"CashBook Payment",sParameter) );
			objectArgs.put("PrintableFormat",getPrintableFormatForDotMatrix(tm, Headers,sLocationName,"CashBook Payment",sParameter) );
			objRes.setResultValue(objectArgs);
			objRes.setViewName("CashBookPayRec");
			//System.out.println("Over CashBook Payment");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
		}
		return objRes;

	}
    
		private List<Date> getDateList(Date oFromDate,Date oToDate)
		{
			Calendar cln = Calendar.getInstance();
			ArrayList<Date> dateRange = new ArrayList();
			Date StartDate = oFromDate;
			cln.setTime(StartDate);
			while(StartDate.compareTo(oToDate)<=0)
			{
				dateRange.add(StartDate);
				cln.add(Calendar.DAY_OF_MONTH, 1);
				StartDate = cln.getTime(); 
			}
			return dateRange;
		}	

    /**
     * @author 219480 Jignesh Sakhiya on 09-Aug
     * @param objectArgs 
     * @return ResultObject
     */
	
	public ResultObject getCashBookReceipt(Map<String, Object> objectArgs) 
	{
		//System.out.println("Cashbook Receipt");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		try
		{
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			String tsryLocId = null;
			Date fromDate = null;
			Date toDate = null;
			boolean subTsry = false;
			List<Date> DateRange = null;
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat smShortDate = new SimpleDateFormat("dd/MM/");
			ArrayList<String> Headers = new ArrayList<String>();
			TreeMap<String,List> tm = new TreeMap<String,List>();
			
			try {tsryLocId = request.getParameter("cmbTrsy");}catch(Exception ex){}
			try {fromDate = sm.parse(request.getParameter("fromDate"));}catch(Exception ex){}
			try {toDate =   sm.parse(request.getParameter("toDate"));}catch(Exception ex){}
			if (request.getParameter("cmbSubTrsy")!=null && request.getParameter("cmbSubTrsy").equalsIgnoreCase("yes")) subTsry=true;
			LocationDAOImpl cmnLocDAO = new  LocationDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
			
			Long sLangId = SessionHelper.getLangId(objectArgs);
			Headers.add("Head of Income");
			DateRange = getDateList(fromDate, toDate);
		
			for (Date oDate : DateRange) 
			{
				
				List ReceiptList = getLoRValue(oDate, oDate, tsryLocId, subTsry, serv,sLangId);
				List PaymentList = getLoPValue(oDate, oDate, tsryLocId, subTsry, serv,true,sLangId);
				
				MstListPayRcpt PayGrandTotal = (MstListPayRcpt)PaymentList.get(652); 
				MstListPayRcpt RecGrandTotal = (MstListPayRcpt)ReceiptList.get(590); 
				
				BigDecimal oRbd = RecGrandTotal.getAmount().subtract(PayGrandTotal.getAmount());
				if(oRbd.longValue() < 0)
				{
					oRbd = oRbd.multiply(new BigDecimal(-1.00));
					
					RecGrandTotal.setAmount(PayGrandTotal.getAmount().add(oRbd));
					MstListPayRcpt oRbdRow1 = (MstListPayRcpt)ReceiptList.get(584);
					MstListPayRcpt oRbdRow2 = (MstListPayRcpt)ReceiptList.get(474);
					oRbdRow1.setAmount(oRbd);
					oRbdRow2.setAmount(oRbd);
				}  
				tm.put(sm.format(oDate),ReceiptList);
				Headers.add(smShortDate.format(oDate));
			}
			
			Headers.add("Total");
			String sLocationName = cmnLocDAO.getDeptNameByLocCode(tsryLocId,"1");
			String sParameter = "From " + sm.format(fromDate) +" To " + sm.format(toDate);
			getTotal(tm);
			objectArgs.put("CashBookReceiptData",getPrintableFormat(tm,Headers,sLocationName,"Cash Book Receipt",sParameter));
			objectArgs.put("PrintableFormat",getPrintableFormatForDotMatrix(tm,Headers,sLocationName,"Cash Book Receipt",sParameter));
			objRes.setResultValue(objectArgs);
			objRes.setViewName("CashBookPayRec");
			//System.out.println("after......");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
		}
		return objRes;

	}
 
	private void getTotal(TreeMap ts)
	{
		Set s = ts.keySet();
		ArrayList[] al = new ArrayList[s.size()];
		ArrayList lTotal = new ArrayList();
		int counter =0;
		for (Object key : s) {
			al[counter++]= (ArrayList)ts.get(key);
		}
		
		if(s.size() == 1) return;
		for(int i=0; i < al[0].size();i++)
		{
			MstListPayRcpt totalObj = new MstListPayRcpt();
			BigDecimal totalAmt = new BigDecimal(0.0);
			boolean bTotalFlag =false;
			for(int j=0;j<al.length;j++)
			{
				MstListPayRcpt lObj =  (MstListPayRcpt)al[j].get(i);
				if(lObj.getIsFormula() !=null && lObj.getAmount() !=null)
				{
					totalAmt = totalAmt.add(lObj.getAmount());
					bTotalFlag = true;
				}
			}
			
			if(bTotalFlag)
			{
				totalObj.setText("Y");
				totalObj.setIsFormula('Y');
				totalObj.setFormula("Total");
			}
			totalObj.setAmount(totalAmt);
			lTotal.add(totalObj);
		}
		ts.put("Total", lTotal);
	}
	private String getPrintableFormat(List<MstListPayRcpt> oValueList,String sLocationName,String sReportName,
			String sParameter)
	{
		StringBuffer sb = new StringBuffer();
		StringBuffer sbHead = new StringBuffer();
		StringBuffer sbLine = new StringBuffer(83);
		for(int i=0;i<83;i++)sbLine.append('-');
		sbLine.append("\n");
		
		sbHead.append(getCenterAlign(sLocationName, 83));
		sbHead.append("\r\n");
		sbHead.append(getCenterAlign(sReportName , 83) );
		sbHead.append("\r\n");
		sbHead.append(String.format("%-83s",new SimpleDateFormat("dd-MMM-yy h:mm a").format(new Date())));
		sbHead.append("\r\n");
		sbHead.append(getCenterAlign(sParameter , 83) );
		sbHead.append("\r\n");
		sbHead.append(sbLine.toString());
		if(sReportName.equals("LIST OF PAYMENT"))
		    sbHead.append(String.format("%-63s","Heads Of Expenditure"));
		else
			sbHead.append(String.format("%-63s","Heads Of Income"));	
		
		sbHead.append(String.format("%20s","Total"));
		sbHead.append("\r\n");
		sbHead.append(sbLine.toString());
		sb.append(sbHead.toString());
		
		for (MstListPayRcpt rcpt : oValueList) 
		{
			if(rcpt != null)
			{
				String sText = rcpt.getText();
			BigDecimal oValue =rcpt.getAmount();
			if(sText != null)
			  sb.append(String.format("%-63s", sText));
			if( sText == null && rcpt.getFormula()!=null)sb.append(String.format("%83s",rcpt.getFormula()));			
		
			if(rcpt.getIsFormula() != null && rcpt.getIsFormula() == 'Y' && oValue !=null)
			{
				sb.append(String.format("%20.2f",oValue));
			}
			sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	private String getPrintableFormatForDotMatrix(List<MstListPayRcpt> oValueList,String sLocationName,String sReportName,
			String sParameter)
	{
		StringBuffer sb = new StringBuffer();
		StringBuffer sbHead = new StringBuffer();
		StringBuffer sbLine = new StringBuffer(83);
		for(int i=0;i<83;i++)sbLine.append('-');
		sbLine.append("\n");
		
		sb.append(getTitlePage(sParameter, sReportName, sLocationName));
		
		sbHead.append(getCenterAlign(sLocationName, 83));
		sbHead.append("\r\n");
		sbHead.append(getCenterAlign(sReportName , 83) );
		sbHead.append("\r\n");
		sbHead.append(String.format("%-76s",new SimpleDateFormat("dd-MMM-yy h:mm a").format(new Date())));
		sbHead.append("Page");
		sbHead.append("\r\n");
		sbHead.append(getCenterAlign(sParameter , 83) );
		sbHead.append("\r\n");
		sbHead.append(sbLine.toString());
		
		if(sReportName.equals("LIST OF PAYMENTS"))
		    sbHead.append(String.format("%-63s","Heads Of Expenditure"));
		else
			sbHead.append(String.format("%-63s","Heads Of Income"));	
		
		sbHead.append(String.format("%20s","Total"));
		sbHead.append("\r\n");
		sbHead.append(sbLine.toString());
		
		sb.append("<div>");
		sb.append("<pre>");
		
		sb.append(sbHead.toString());
		int counter = 0;
		int pageNo = 1;
		for (MstListPayRcpt rcpt : oValueList) 
		{
			
			if(rcpt != null)
			{
				String sText = rcpt.getText();
				BigDecimal oValue =rcpt.getAmount();
				if(sText != null)
					sb.append(String.format("%-63s", sText));
				if( sText == null && rcpt.getFormula()!=null)sb.append(String.format("%83s",rcpt.getFormula()));			
				if(rcpt.getIsFormula() != null && rcpt.getIsFormula() == 'Y' && oValue !=null)
				{
					sb.append(String.format("%20.2f",oValue));
				}
				sb.append("\n\n");
				
				if(	counter > 0 && counter % 27 == 0 ) 
				{	
					sb.append("</pre></div>");
					sb.append("<div>");
					sb.append("<pre>");
					int pos = sbHead.indexOf("Page");
					pageNo++;
					StringBuffer sHeadDup =new StringBuffer(sbHead.toString()); 
					sHeadDup.replace(pos,pos+4,String.valueOf(pageNo));
					sb.append(sHeadDup.toString());
					
				}
				counter++;
			}
		}
		return sb.toString();
	}
	
	private String getCenterAlign(String sValue,int length)
	{
	    StringBuffer sb = new StringBuffer(length);
	    int nStartPos = (length - sValue.length())/2;
	   	for(int i = 0; i < nStartPos ; i++) sb.append(" ");
	    sb.append(sValue);
	    for(int i = sb.length(); i < length ; i++) sb.append(" ");
	    return sb.toString();
	}
	private String getPrintableFormat(Map oValueList,List<String> Headings,String LocationName,String sReportName,
			String sParameter)
	{
		List oColumn[] = null;
		List<String> keyList = new ArrayList<String>();
		boolean first = true;
		boolean multiPart = false;
		StringBuffer sb = new StringBuffer();
		StringBuffer sbLine = new StringBuffer(345);
		StringBuffer Header = new StringBuffer(345);
	
		for(int i=0;i<345;i++)sbLine.append('-');
		sbLine.append("\n");
		Iterator<String> s = oValueList.keySet().iterator();
		while(s.hasNext())keyList.add(s.next());
		
		oColumn = new List[keyList.size()];
		
		
			Header.append(getCenterAlign(LocationName, 83));
			Header.append("\r\n");
			Header.append(getCenterAlign(sReportName , 83) );
			Header.append("\r\n");
			Header.append(String.format("%-83s",new SimpleDateFormat("dd-MMM-yy h:mm a").format(new Date())));
			Header.append("\r\n");
			Header.append(getCenterAlign(sParameter , 83) );
			Header.append("\r\n");
			
			
			Header.append(sbLine.toString());
			for(String token:Headings)
			{
				if(first){
					Header.append(String.format("%-63s",token) + " ");
				 first = false;
				}else
					Header.append(String.format("%15s",token) + " ");
			}
			Header.append("\n");
			Header.append(sbLine.toString());
		 
		
		 sb.append(Header.toString());
		
		for (int i=0 ; i< keyList.size();i++) 
		{
			oColumn[i] = (List)oValueList.get(keyList.get(i));
		}
		if(oColumn.length == 0 ) return null;
		
			for (int nRow =0;  nRow <  oColumn[0].size(); nRow ++) 
			{
		 		String sText = "";
				for(int nCol = 0 ; nCol < keyList.size(); nCol ++)
				{
					List oVals = oColumn[nCol]; 
					MstListPayRcpt objListPayRec = (MstListPayRcpt)oVals.get(nRow);
					sText  = objListPayRec.getText();
					if(nCol ==  0 && sText !=null )
						sb.append(String.format("%-63s", sText));

					if(objListPayRec.getIsFormula() != null && objListPayRec.getIsFormula() == 'Y' )
					{	
						BigDecimal oValue = new BigDecimal(0.0);
						if( objListPayRec.getAmount() !=null) oValue = objListPayRec.getAmount();
	   	   				sb.append(String.format("%15.2f",oValue) + " ");
					}
				 }
				sb.append("\n");
			 }
		return sb.toString();
	}
	private String getPrintableFormatForDotMatrix(Map oValueList,List<String> Headings,String LocationName,String sReportName,
			String sParameter)
	{
		List oColumn[] = null;
		List<String> keyList = new ArrayList<String>();
		boolean first = true;
		boolean multiPart = false;
		StringBuffer sb = new StringBuffer();
		StringBuffer sbLine = new StringBuffer(345);
		StringBuffer Header = new StringBuffer(345);
		StringBuffer Header1 = new StringBuffer(345);
		StringBuffer Header2 = new StringBuffer(345);
		
		sb.append(getTitlePage(sParameter, sReportName, LocationName));
		
		for(int i=0;i<345;i++)sbLine.append('-');
		sbLine.append("\n");
		Iterator<String> s = oValueList.keySet().iterator();
		while(s.hasNext())keyList.add(s.next());
		
		oColumn = new List[keyList.size()];
		
		if(keyList.size() > 14)multiPart = true;
		if(!multiPart)
		{
			Header.append(getCenterAlign(LocationName, 83));
			Header.append("\r\n");
			Header.append(getCenterAlign(sReportName , 83) );
			Header.append("\r\n");
			Header.append(String.format("%-83s",new SimpleDateFormat("dd-MMM-yy h:mm a").format(new Date())));
			Header.append("Page");
			Header.append("\r\n");
			Header.append(getCenterAlign(sParameter , 83) );
			Header.append("\r\n");
			
			
			Header.append(sbLine.toString());
			for(String token:Headings)
			{
				if(first){
					Header.append(String.format("%-63s",token) + " ");
				 first = false;
				}else
					Header.append(String.format("%15s",token) + " ");
			}
			Header.append("\n");
			Header.append(sbLine.toString());
		 }else{
			
			    Header1.append(getCenterAlign(LocationName, 83));
				Header1.append("\r\n");
				Header1.append(getCenterAlign(sReportName , 83) );
				Header1.append("\r\n");
				Header1.append(String.format("%-83s",new SimpleDateFormat("dd-MMM-yy h:mm a").format(new Date())));
				Header1.append("Page");
				Header1.append("\r\n");
				Header1.append(getCenterAlign(sParameter , 83) );
				Header1.append("\r\n");
				Header1.append(sbLine.toString());
				
			for(int i=0 ; i <= 15;i++)
			{
				 if(i==0)Header1.append(String.format("%-63s",Headings.get(i)) + " ");
				else
					Header1.append(String.format("%15s",Headings.get(i)) + " ");
			}
				Header1.append("\n");
				Header1.append(sbLine.toString());
				
				    Header2.append(getCenterAlign(LocationName, 83));
					Header2.append("\r\n");
					Header2.append(getCenterAlign(sReportName , 83) );
					Header2.append("\r\n");
					Header2.append(String.format("%-83s",new SimpleDateFormat("dd-MMM-yy h:mm a").format(new Date())));
					Header2.append("Page");
					Header2.append("\r\n");
					Header2.append(getCenterAlign(sParameter , 83) );
					Header2.append("\r\n");
					Header2.append(sbLine.toString());
					
				
				for(int i=16 ; i < Headings.size();i++)
				{
					 if(i==16)Header2.append(String.format("%-63s",Headings.get(0)) + " ");
						Header2.append(String.format("%15s",Headings.get(i)) + " ");
				}
					Header2.append("\n");
					Header2.append(sbLine.toString());
		     }
		
		sb.append("<div>");
		sb.append("<pre>");
	 
		if(multiPart)
		     sb.append(Header1.toString());
		else sb.append(Header.toString());
		 	
			
			int counter = 0;
			int pageNo = 1;
		

		for (int i=0 ; i< keyList.size();i++) 
		{
			oColumn[i] = (List)oValueList.get(keyList.get(i));
		}
		if(oColumn.length == 0 ) return null;
		
		if(multiPart)
		{
			int nColStart = 0,nColEnd = 14;
			int nRowStart = 0,nRowEnd = 27;
			Header = Header1; 
			while(nRowEnd < oColumn[0].size())
			{
				for (int nRow =nRowStart;  nRow <= nRowEnd; nRow ++) 
				{
					String sText = "";
				
					for(int nCol = nColStart ; nCol <= nColEnd; nCol ++)
					{
						List oVals = oColumn[nCol]; 
						MstListPayRcpt objListPayRec = (MstListPayRcpt)oVals.get(nRow);
						sText  = objListPayRec.getText();
						if( (nCol ==  0 || nCol == 15 ) && sText !=null )
							sb.append(String.format("%-63s", sText));

						if(objListPayRec.getIsFormula() != null && objListPayRec.getIsFormula() == 'Y' &&  objListPayRec.getAmount() !=null)
						{	
							BigDecimal oValue = objListPayRec.getAmount();
		   	   				sb.append(String.format("%15.2f",oValue) + " ");
						}
					}	
					sb.append("\n\n");
					if(	counter > 0 && counter % 27 == 0 ) 
					{	
						sb.append("</pre></div>");
						sb.append("<div>");
						sb.append("<pre>");
						int pos = Header.indexOf("Page");
						pageNo++;
						StringBuffer sHeadDup =new StringBuffer(Header.toString()); 
						sHeadDup.replace(pos,pos+4,String.valueOf(pageNo));
						sb.append(sHeadDup.toString());
					}
					counter++;
					
				}
				if(nColEnd==14)
				{
					nColStart = 15;
					nColEnd = keyList.size()-1;
					Header = Header2;
				}
				else
				{
					nRowStart = nRowEnd + 1;
					nRowEnd = nRowEnd + 26;
					nColStart =0;
					nColEnd = 14;
					Header = Header1;
				}
			} //while
			
		} // if mulitpart
		else
		{
			for (int nRow =0;  nRow <  oColumn[0].size(); nRow ++) 
			{
		 		String sText = "";
				for(int nCol = 0 ; nCol < keyList.size(); nCol ++)
				{
					List oVals = oColumn[nCol]; 
					MstListPayRcpt objListPayRec = (MstListPayRcpt)oVals.get(nRow);
					sText  = objListPayRec.getText();
					if(nCol ==  0 && sText !=null )
						sb.append(String.format("%-63s", sText));

					if(objListPayRec.getIsFormula() != null && objListPayRec.getIsFormula() == 'Y' )
					{	
						BigDecimal oValue = new BigDecimal(0.0);
						if( objListPayRec.getAmount() !=null) oValue = objListPayRec.getAmount();
	   	   				sb.append(String.format("%15.2f",oValue) + " ");
					}
				 }
				sb.append("\n\n");
				if(	counter > 0 && counter % 27 == 0 ) 
				{	
					sb.append("</pre></div>");
					sb.append("<div>");
					sb.append("<pre>");
					int pos = Header.indexOf("Page");
					pageNo++;
					StringBuffer sHeadDup =new StringBuffer(Header.toString()); 
					sHeadDup.replace(pos,pos+4,String.valueOf(pageNo));
					sb.append(sHeadDup.toString());
				}
				counter++;
			 }
		 }
		return sb.toString();
	}
	private String getTitlePage(String sParameter,String sTitle,String sLocation)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<div><pre>");
		sb.append("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n"); 
		sb.append( getCenterAlign("--------------------------------------------------------",132));
		sb.append("\r\n");
		sb.append( getCenterAlign(sTitle,132));
		sb.append("\r\n");
		sb.append( getCenterAlign(sLocation,132));
		sb.append("\r\n");
		sb.append( getCenterAlign(sParameter,132));
		sb.append("\r\n");
		sb.append( getCenterAlign("--------------------------------------------------------",132));
		sb.append("</pre></div>\n\n");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		BigDecimal b = new BigDecimal(20020020020020330l);
		//System.out.println(String.format("%15.2f",b));
	}
	
	public ResultObject getSubTsry(Map objectArgs)
	{
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		String treasury = request.getParameter("cmbTrsy").toString();
		
		
		//System.out.println(treasury);
		
		String ajaxString=null;
		try
		{
				ReportQueryDAOImpl reportQueryDAO = new ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
				List resList = new ArrayList();
				String locCode = SessionHelper.getLocationCode(objectArgs);
				if(treasury != null)
				{				
					resList = reportQueryDAO.getSubTreasury(treasury);
					
					ajaxString= new AjaxXmlBuilder().addItems(resList,"locName","locationCode").toString();
				}
				
				//System.out.println(ajaxString);
				objectArgs.put("ajaxKey", ajaxString);
				resObj.setResultValue(objectArgs);
				resObj.setViewName("ajaxData");
		}
		catch(Exception ex)
		{
			resObj.setThrowable(ex);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
			logger.error("Exception occured in ReceiptServiceImpl.getDdoNames # \n"+ex);
			ex.printStackTrace();
		}
		return resObj;
	}
	public ResultObject getPaymentSubsidery(Map objectArgs)
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			String locCode = SessionHelper.getLocationCode(objectArgs);
			Long nLangId = SessionHelper.getLangId(objectArgs);
			Date oFromDate ,oToDate ;
			
			String sFromMajorHead, sToMajorHead ;
			oFromDate = sm.parse(request.getParameter("fromDate"));
			oToDate = sm.parse(request.getParameter("toDate"));
			sFromMajorHead = request.getParameter("fromMajorHead");
			sToMajorHead = request.getParameter("toMajorHead");
			ReportDAOImpl reportQueryDAO = new ReportDAOImpl(serv.getSessionFactory());
			
			String sOutput[] = reportQueryDAO.getPaymentSubsideryRegister(oFromDate,oToDate,locCode,sFromMajorHead,sToMajorHead
					,nLangId);
				objectArgs.put("sOutput", sOutput[1]);
				objectArgs.put("sOutput4Print",sOutput[0]);
				
				resObj.setResultValue(objectArgs);
				resObj.setViewName("PaymentSubsideryRegister");
		}
		catch(Exception ex)
		{
			resObj.setThrowable(ex);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
			logger.error("Exception occured in ReceiptServiceImpl.getDdoNames # \n"+ex);
			ex.printStackTrace();
		}
		return resObj;
		
	}
	public ResultObject getPaymentSubsideryFrm(Map<String, Object> objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try
		{
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}			
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			ReportQueryDAOImpl oQueryDao = new ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
			String sLocId = SessionHelper.getLocationCode(objectArgs);
			String sLangId = oQueryDao.getLangName(SessionHelper.getLangId(objectArgs));
			List MajorHeads =  oQueryDao.getMajorHead(sLangId);
			objectArgs.put("MajorHeadList",MajorHeads );
			objRes.setResultValue(objectArgs);
			objRes.setViewName("PaymentSubsideryRegiInput");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
		}
		return objRes;

	}
	public ResultObject getListLOPLOR(Map<String,Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			ReportDAO rptDAO = new ReportDAOImpl(serv.getSessionFactory());
			List receiptMtData = rptDAO.getRptMetaData(ReportDAOImpl.LIST_OF_RCPT);
			List paymentMtData = rptDAO.getRptMetaData(ReportDAOImpl.LIST_OF_PAY);
			
			objectArgs.put("receiptMtData",receiptMtData );
			objectArgs.put("paymentMtData",paymentMtData );
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ConfigLOPLOR");
			
		}
		catch(Exception e)
		{
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
		}
		
		return objRes;
	}
	public ResultObject getBankWiseSummary(Map<String,Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		try
		{
			Long LangId = SessionHelper.getLangId(objectArgs);
			String lstrLocId = (SessionHelper.getLocationId(objectArgs)).toString();
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			
			ReportQueryDAOImpl reportQueryDAO = new ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
			
			String lStrLangId = reportQueryDAO.getLangName(LangId);
			List mjrheadList = reportQueryDAO.getMajorHead(lStrLangId, lstrLocId);
			//System.out.println("MajorHead List:" +mjrheadList.size());
			objectArgs.put("mjrheadList", mjrheadList);
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("bankWiseSummary");
		    	
		}
		catch(Exception e)
		{
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}
	public ResultObject getBankWiseSummaryOutput(Map<String,Object> objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ReportDAOImpl rptDAO = new ReportDAOImpl(serv.getSessionFactory());
		ReportQueryDAOImpl reportQueryDAO = new ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());
		try
		{
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			
			Date fromDate = null;
			Date toDate = null;
			String majorHead = null;
			String type = null;
			
			SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
			Long sLangId = SessionHelper.getLangId(objectArgs);
			String sLocId = SessionHelper.getLocationCode(objectArgs);
			
			try {fromDate = sm.parse(request.getParameter("fromDate"));}catch(Exception ex){}
			try {toDate = sm.parse(request.getParameter("toDate"));}catch(Exception ex){}
			try{majorHead=request.getParameter("MajorHead");}catch(Exception ex){}
			try{type=request.getParameter("type");}catch(Exception ex){}
			String lStrLangId = reportQueryDAO.getLangName(sLangId);
			String output = "";
      if(type.equals("0"))
			  output = rptDAO.getBankWiseChallanSummary(sLocId,fromDate,toDate,sLangId,majorHead);
			else
			  output = rptDAO.getBranchWiseChallanSummary(sLocId,fromDate,toDate,sLangId,majorHead);
			
      objectArgs.put("output", output);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("bankWiseSummaryOutput");
			
		}
		catch(Exception e)
		{
			logger.error("Exception occured  #\n"+e);
			e.printStackTrace();
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");	
		}
		return objRes;
	}
}
