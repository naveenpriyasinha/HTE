package com.tcs.sgv.exprcpt.report;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.reports.CommonReportDAOImpl;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.service.ServiceLocator;

public class ReportDAOImpl extends DefaultReportDataFinder implements
ReportDataFinder {
private static final Logger glogger=Logger.getLogger(CommonReportDAOImpl.class);
	
	private SessionFactory lObjSessionFactory = null;
	
	public Collection findReportData(ReportVO report, Object criteria)
			throws ReportException 
	{	
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map sessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
		//System.out.println(" Request map ............. "  + requestAttributes.toString());
		
		
		LoginDetails loginVO = (LoginDetails)sessionAttributes.get("loginDetails");
		
	
		String locID = loginVO.getLocation().getLocationCode();
		Long langId=loginVO.getLangId();
		report.setLocId(locID);
		
		ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		lObjSessionFactory = serviceLocator.getSessionFactory();
			
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		
		report.setLangId(rptQueryDAO.getLangName(langId));
		List lArrReportData = new ArrayList();
		
		if (report.getReportCode().equals("117")) 
		{
			
			lArrReportData = getDateWiseReceipt(report);
		}
		if (report.getReportCode().equals("119")) 
		{
		  
			lArrReportData = getHeadWiseChallan(report);
		}
		
		if (report.getReportCode().equals("121")) 
		{
		  
			lArrReportData = getBankWiseChallan(report);
		}  
		
		if (report.getReportCode().equals("123")) 
		{
			
			lArrReportData = getReceiptSubHead(report);
		}
		if (report.getReportCode().equals("125")) 
		{
			
			lArrReportData = getPaymentSubHead(report);
		}
		if (report.getReportCode().equals("145")) 
		{
			lArrReportData = getPaidCheque(report);
		}
		if (report.getReportCode().equals("129")) 
		{	
			lArrReportData = getPaidChequeSummary(report);
		}
		if (report.getReportCode().equals("131")) 
		{	
			lArrReportData = getCancelledlapsedTC(report);
		}
		if (report.getReportCode().equals("133")) 
		{	
			lArrReportData = getUnPaidChequeSummary(report);
		}
		if (report.getReportCode().equals("135")) 
		{	
			lArrReportData = getRBD(report);
		}
		if (report.getReportCode().equals("137")) 
		{	
			lArrReportData = getTCMemoReg(report);
		}
		if (report.getReportCode().equals("139")) 
		{	
			//System.out.println("gettting tc sub rpt");
			lArrReportData = getSubTCMemoReg(report);
		}
		if(report.getReportCode().equals("147"))
		{
			lArrReportData = getOutstandingBalance(report);
		}
		if(report.getReportCode().equals("153"))
		{
			lArrReportData = getTCMemRegPay(report);
		}
		if(report.getReportCode().equals("155"))
		{
			lArrReportData = getSubTCMemRegPay(report);
		}
		
		return lArrReportData;
	}
	
	
	private List getPaymentSubHead(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
	
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String fromDate=lObjReport.getParameterValue("Datefrom").toString();
			String toDate=lObjReport.getParameterValue("Dateto").toString();
			String fMjrHead =(String)lObjReport.getParameterValue("FromMajorHead");
			String tMjrHead =(String)lObjReport.getParameterValue("ToMajorHead");
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getPaymentSubHeadRpt( fMjrHead, tMjrHead, fromDate, toDate, locId,langId );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return lArrReturn;
	}
	
	
	
	private List getDateWiseReceipt(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		//System.out.println("0 nd");
		try
		{
			//System.out.println("1 nd");
			String fMjrHead =(String)lObjReport.getParameterValue("FromMajorHead");
			String tMjrHead =(String)lObjReport.getParameterValue("ToMajorHead");
			String fromDate=lObjReport.getParameterValue("Datefrom").toString();
			String toDate=lObjReport.getParameterValue("Dateto").toString();
			String locId =  (String)lObjReport.getLocId();
			//System.out.println("2 nd");
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getDateWiseReceiptRpt( fMjrHead, tMjrHead, fromDate, toDate, locId, langId );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
	private List getHeadWiseChallan(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			Date fromDate= new SimpleDateFormat("dd/MM/yyyy").parse(lObjReport.getParameterValue("Datefrom").toString());
			Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(lObjReport.getParameterValue("Dateto").toString());
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getHeadWiseChallanRpt( fromDate, toDate, locId, langId );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
	
	private List getBankWiseChallan(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		
		try
		{
			
			String fMjrHead =(String)lObjReport.getParameterValue("FromMajorHead");
			String tMjrHead =(String)lObjReport.getParameterValue("ToMajorHead");
			Date fromDate= new SimpleDateFormat("dd/MM/yyyy").parse(lObjReport.getParameterValue("Datefrom").toString());
			Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(lObjReport.getParameterValue("Dateto").toString());
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getBankWiseChallanRpt( fMjrHead, tMjrHead, fromDate, toDate, locId, langId );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
	private List getReceiptSubHead(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		
		try
		{
			Date fromDate= new SimpleDateFormat("dd/MM/yyyy").parse(lObjReport.getParameterValue("Datefrom").toString());
			Date toDate=new SimpleDateFormat("dd/MM/yyyy").parse(lObjReport.getParameterValue("Dateto").toString());
			String fMjrHead =(String)lObjReport.getParameterValue("FromMajorHead");
			String tMjrHead =(String)lObjReport.getParameterValue("ToMajorHead");
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getReceiptSubHeadRpt( fMjrHead, tMjrHead, fromDate, toDate, locId, langId);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return lArrReturn;
	}
	
	private List getPaidChequeSummary(ReportVO lObjReport )
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String fromDate=lObjReport.getParameterValue("Datefrom").toString();
			String toDate=lObjReport.getParameterValue("Dateto").toString();
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getPaidChequeSummaryRpt( fromDate, toDate, locId, langId);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
    private List getPaidCheque(ReportVO lObjReport)
	{
		
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
	
		try
		{  
			
			String fromDate = lObjReport.getParameterValue("Datefrom").toString();
			String toDate = lObjReport.getParameterValue("Dateto").toString();
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getPaidChequeRpt( fromDate, toDate, locId, langId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return lArrReturn;
	}
	
	private List getUnPaidChequeSummary(ReportVO lObjReport )
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String fromDate=lObjReport.getParameterValue("Datefrom").toString();
			String toDate=lObjReport.getParameterValue("Dateto").toString();
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getUnPaidChequeSummaryRpt( fromDate, toDate, locId, langId);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
	private List getRBD(ReportVO lObjReport )
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String fromDate=lObjReport.getParameterValue("Datefrom").toString();
			String toDate=lObjReport.getParameterValue("Dateto").toString();
			Long challanTypeId = null;
			
			CmnLookupMstDAOImpl cmnLookupDAO =   new CmnLookupMstDAOImpl(CmnLookupMst.class ,this.lObjSessionFactory);
			List cmnLookupMstList = cmnLookupDAO.getAllChildren("Rcpt_Type");
			
			if (cmnLookupMstList!=null) {
				for (int i = 0; i < cmnLookupMstList.size(); i++) {
					CmnLookupMst cmnLookupMst = (CmnLookupMst)cmnLookupMstList.get(i);
					if (cmnLookupMst!=null && cmnLookupMst.getLookupName()!=null && cmnLookupMst.getLookupName().equalsIgnoreCase(DBConstants.RPT_RCPT_TYPE_CHALLAN))
					{
						challanTypeId= cmnLookupMst.getLookupId();
					}
				} 
			}

			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getRBDRpt( fromDate, toDate, locId, langId);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
	private List getCancelledlapsedTC(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String fromDate=lObjReport.getParameterValue("Datefrom").toString();
			String toDate=lObjReport.getParameterValue("Dateto").toString();
			String locId = (String)lObjReport.getLocId();
			Long chequeTypeId = null;
			CmnLookupMstDAOImpl cmnLookupDAO =   new CmnLookupMstDAOImpl(CmnLookupMst.class ,this.lObjSessionFactory);
			List cmnLookupMstList = cmnLookupDAO.getAllChildren(DBConstants.CHEQ_TYPE);
			
			if (cmnLookupMstList!=null) {
				for (int i = 0; i < cmnLookupMstList.size(); i++) {
					CmnLookupMst cmnLookupMst = (CmnLookupMst)cmnLookupMstList.get(i);
					if (cmnLookupMst!=null && cmnLookupMst.getLookupName()!=null && cmnLookupMst.getLookupName().equalsIgnoreCase(DBConstants.CHEQ_TYPE_TC))
					{
						chequeTypeId= cmnLookupMst.getLookupId();
					}
				} 
			}
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getCancelledlapsedTCRpt( fromDate, toDate, locId, langId, chequeTypeId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return lArrReturn;
	}
	
	private List getTCMemoReg(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		
		try
		{
			String fromDate=lObjReport.getParameterValue("Datefrom").toString();
			String toDate=lObjReport.getParameterValue("Dateto").toString();
			String fMjrHead =(String)lObjReport.getParameterValue("FromMajorHead");
			String tMjrHead =(String)lObjReport.getParameterValue("ToMajorHead");
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			
			lArrReturn =  rptQueryDAO.getTCMemoRegRpt( fMjrHead, tMjrHead, fromDate, toDate, locId, langId);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return lArrReturn;
	}
	
	private List getSubTCMemoReg(ReportVO lObjReport)
	{
		//System.out.println("Out Side The Array List");
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		String majorHead = lObjReport.getParameterValue("MajoHead").toString();
		String fromDate=lObjReport.getParameterValue("startDate").toString();
		String toDate=lObjReport.getParameterValue("endDate").toString();
		String locId =  (String)lObjReport.getLocId();
		String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
		lArrReturn =  rptQueryDAO.getSubTCMemoRpt(fromDate,toDate, majorHead, locId, langId);
		
		return lArrReturn;
	}
	
	private List getOutstandingBalance(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		String sYear = lObjReport.getParameterValue("year").toString();
		String sMonth =  lObjReport.getParameterValue("month").toString();
		String sLocation_code = lObjReport.getLocId();
		lArrReturn = rptQueryDAO.getOutstandingBalanceReport(sYear, sMonth, sLocation_code);
		
		return lArrReturn;
		
	}
	private List getTCMemRegPay(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String fromDate=lObjReport.getParameterValue("Datefrom").toString();
			String toDate=lObjReport.getParameterValue("Dateto").toString();
			String fMjrHead =(String)lObjReport.getParameterValue("FromMajorHead");
			String tMjrHead =(String)lObjReport.getParameterValue("ToMajorHead");
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			//System.out.println("Session Location:" +locId);
			//System.out.println("Session lang:" +langId);
			
			lArrReturn =  rptQueryDAO.getTCMemoRegPayRpt( fMjrHead, tMjrHead, fromDate, toDate, locId, langId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	private List  getSubTCMemRegPay(ReportVO lObjReport)
	{
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String majorHead = lObjReport.getParameterValue("MajoHead").toString();
			String fromDate=lObjReport.getParameterValue("startDate").toString();
			String toDate=lObjReport.getParameterValue("endDate").toString();
			String locId =  (String)lObjReport.getLocId();
			String langId = rptQueryDAO.getLangId(lObjReport.getLangId()).toString();
			lArrReturn =  rptQueryDAO.getSubTCMemoPayRpt(fromDate,toDate, majorHead, locId, langId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return lArrReturn;
	}
	
	public static void main(String args[])
	{
		try
		
		{
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse("07/06/2007");
			String ffromDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
			//System.out.println(ffromDate);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	
}
