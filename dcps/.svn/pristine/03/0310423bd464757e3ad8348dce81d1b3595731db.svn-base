package com.tcs.sgv.nps.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.nps.dao.DCPSLegacyTransferNSDLDAOImpl;

public class DCPSLegacyTransferNSDLServiceImpl extends DefaultReportDataFinder implements ReportDataFinder {
	
	
	private static final Logger gLogger = Logger.getLogger("PensionpayReports");
	private DCPSLegacyTransferNSDLDAOImpl gObjRptQueryDAO = null;
	ServiceLocator serviceLocator = null;
	SessionFactory gObjSessionFactory = null;
	String gStrLocCode = null;
	String gStrDdoCode = null;
	Long gLngLangId = null;
	Long gPostId=null;
	Map lMapSeriesHeadCode = null;
	Session ghibSession = null;

	
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionpay/PensionCaseConstants");

	private ResourceBundle bundleConst = ResourceBundle.getBundle(("resources/pensionpay/PensionConstants"));

	public Collection findReportData(ReportVO lObjReport, Object criteria) throws ReportException {

		List lLstDataList = new ArrayList();
		Map lMapRequestAttributes = null;
		Map lMapSessionAttributes = null;
		LoginDetails lObjLoginVO = null;
		try {
			lMapRequestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			lMapSessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
			lObjLoginVO = (LoginDetails) lMapSessionAttributes.get("loginDetails");
			serviceLocator = (ServiceLocator) lMapRequestAttributes.get("serviceLocator");
			gObjSessionFactory = serviceLocator.getSessionFactorySlave();
			gStrLocCode = lObjLoginVO.getLocation().getLocationCode();
			//gStrDdoCode= lObjLoginVO.getDdo().getDdoCode();
			gLngLangId = lObjLoginVO.getLangId();
			gPostId=lObjLoginVO.getLoggedInPost().getPostId();
			ghibSession = gObjSessionFactory.getCurrentSession();
			gObjRptQueryDAO = new DCPSLegacyTransferNSDLDAOImpl(null, gObjSessionFactory);

			gObjRptQueryDAO.setReportHeaderAndFooter(lObjReport, lObjLoginVO);
			
			//String ddocode = gObjRptQueryDAO.getDdoCode(gStrLocCode);
			//Added by ---DTA & DTO Registration Details Report
			if (lObjReport.getReportCode().equals("80000948")) {
				lLstDataList = getDCPSLegacyTransferNSDL(lObjReport, gStrLocCode);
			}
			
			
		}
		catch (Exception e) {
			gLogger.info("findReportData(): Exception is" + e, e);
		}
		return lLstDataList;
	}

	private List getDCPSLegacyTransferNSDL(ReportVO lObjReport, String lStrLocCode) {
		List lArrReturn = new ArrayList();
		
		DCPSLegacyTransferNSDLDAOImpl gObjRptQueryDAO = new DCPSLegacyTransferNSDLDAOImpl(null, gObjSessionFactory);
		
		try {
			//String header1 = "<p style=\"text-align:center;\"><b> DCPS Legacy Transfer NSDL</b> </p>";
			//lObjReport.setAdditionalHeader(header1);
			lArrReturn=gObjRptQueryDAO.getDCPSLegacyTransferNSDL(lStrLocCode);
		}
		
		catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return lArrReturn;
	}


}
