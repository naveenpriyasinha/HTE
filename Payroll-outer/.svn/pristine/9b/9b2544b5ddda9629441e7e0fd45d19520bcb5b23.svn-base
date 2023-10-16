package com.tcs.sgv.eis.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.common.business.reports.ChartReportDataFinder;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.reports.CommonReportDAOImpl;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.core.service.ServiceLocator;

public class ReportDAOImpl extends DefaultReportDataFinder implements ChartReportDataFinder,
ReportDataFinder {
private static final Logger glogger=Logger.getLogger(CommonReportDAOImpl.class);
	
	private SessionFactory lObjSessionFactory = null;
	
	public Collection findReportData(ReportVO report, Object criteria)
			throws ReportException 
	{	
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map sessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
		glogger.info(" Request map ............. "  + requestAttributes.toString());
		LoginDetails loginVO = (LoginDetails)sessionAttributes.get("loginDetails");
		glogger.info(" loginVO............. "  + loginVO);
		long locID = loginVO.getLocation().getLocId();
		glogger.info("location Id is:-"+locID);
		ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		// get the SessionFactory instance for using it to fetch data...
		lObjSessionFactory = serviceLocator.getSessionFactory();
		List lArrReportData = new ArrayList();
		if (report.getReportCode().equals("101")) // location master report
		{
			glogger.info("-------In the 101");

			lArrReportData = getLocationReport(report);
		}
		else if (report.getReportCode().equals("103")) // Employee List report
		{
			glogger.info("-------In the 103");
			String str = report.getParameterValue("locName").toString();
			report.setReportName(str+" Employee Details");
			lArrReportData = getEmployees(report);
		}
		else if (report.getReportCode().equals("105")) // Employee details Report
		{
			glogger.info("-------In the 105");
			lArrReportData = getEmployeeWiseTracReport(report);
		}
		else if (report.getReportCode().equals("107")) // post details Report
		{
			glogger.info("-------In the 107");
			lArrReportData = getPostReport(report);
		}
		else if (report.getReportCode().equals("109")) // post history Report
		{
			glogger.info("-------In the 109");
			lArrReportData = getPostWiseTracReport(report);
		}
		else if (report.getReportCode().equals("111")) // post history Report
		{
			glogger.info("-------In the 111");
			 lArrReportData = getLocationReport(report);
		}
		
		return lArrReportData;
	}
	private List getLocationReport(ReportVO lObjReport)
	{
		glogger.info("In the location report");
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.eis.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		glogger.info("I nlocation maaaaaaaaaaain class");
		try
		{
			String loffice=lObjReport.getParameterValue("location").toString();
			String lSortBy = lObjReport.getParameterValue("recurciveOption").toString();
			glogger.info("Location name ->>>>" +loffice );
			glogger.info("Option name:->>>." + lSortBy);
			lArrReturn =  rptQueryDAO.getLocationDtlRpt(loffice,lSortBy);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
	private List getEmployees(ReportVO lObjReport)
	{
		glogger.info("In the Employee info..");
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.eis.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String lLocation=lObjReport.getParameterValue("locId").toString();
			lArrReturn =  rptQueryDAO.getEmplDtlRpt(lLocation);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
	private List getEmployeeWiseTracReport(ReportVO lObjReport)
	{
		glogger.info("Inside employee wise Tracking Report");
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.eis.report.ReportQueryDAOImpl");
		glogger.info("DAO generated");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		glogger.info("before getting employe id");
		String sempId =  lObjReport.getParameterValue("empId").toString();
		//System.out.println(sempId);
		long empId = Long.parseLong(sempId);
		lArrReturn =  rptQueryDAO.getEmployeeTrackDtls(empId);
		glogger.info(" Emp Id is :-"+empId);
		glogger.info("Out Side The Array List");
		return lArrReturn;
	}
	
	private List getPostReport(ReportVO lObjReport)
	{
		glogger.info("----------- Start the post method");
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.eis.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		try
		{
			String lLocation=lObjReport.getParameterValue("locId").toString();
			glogger.info("----------------------------------------------Office name is :"+lLocation);
			lArrReturn =  rptQueryDAO.getPostDtlRpt(lLocation);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return lArrReturn;
	}
	
	private List getPostWiseTracReport(ReportVO lObjReport)
	{
		glogger.info("Inside post wise Tracking Report");
		List lArrReturn = new ArrayList();
		ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.eis.report.ReportQueryDAOImpl");
		rptQueryDAO.setSessionFactory(lObjSessionFactory);
		Long postId = Long.parseLong(lObjReport.getParameterValue("postId").toString());
		glogger.info(" post Id is :-"+postId);
		lArrReturn =  rptQueryDAO.getPostTrackDtls(postId);
		glogger.info("Out Side The Array List");
		return lArrReturn;
	}

}
