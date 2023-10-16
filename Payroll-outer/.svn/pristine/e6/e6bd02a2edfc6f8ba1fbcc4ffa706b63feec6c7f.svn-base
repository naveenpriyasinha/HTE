package com.tcs.sgv.onlinebillprep.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;

public class ReportDAOImpl extends DefaultReportDataFinder implements
ReportDataFinder {
	
	Log gLogger = LogFactory.getLog(getClass());
	
	private SessionFactory lObjSessionFactory = null;
	private ServiceLocator serviceLocator = null;
	Long langID=null;
	Long postId= null;
	public Collection findReportData(ReportVO report, Object criteria)
			throws ReportException 
	{	
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map sessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
		//System.out.println(" Request map ............. "  + requestAttributes.toString());
		
		
		LoginDetails loginVO = (LoginDetails)sessionAttributes.get("loginDetails");
		
		//System.out.println(" loginVO............. "  + loginVO);
		
		long locID = loginVO.getLocation().getLocId();
		postId = loginVO.getLoggedInPost().getPostId();
		langID=loginVO.getLangId();
		//System.out.println(langID);
		report.setLocId(String.valueOf(locID));
		
		//System.out.println("location Id is:-"+locID);
				
		serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		// get the SessionFactory instance for using it to fetch data...
		lObjSessionFactory = serviceLocator.getSessionFactory();

		List lArrReportData = new ArrayList();
		
		if (report.getReportCode().equals("110001")) // Bill Tracking Report for DDO
		{
			lArrReportData = getBillTrackingReportForDDo(report);
		}
		
		if (report.getReportCode().equals("110002")) // Bill Tracking Report Cash Branch
		{
			lArrReportData = getBillTrackingReportForCashBranch(report);
		}
		
		return lArrReportData;
	}
	
	/* 
	 * Create By Sagar.
	 * 
	 * For Generate Bill Tracking Report For DDO. 
	 */
	
	private List getBillTrackingReportForDDo(ReportVO reportObj)
	{
	    List billRegisterForDDO=new ArrayList();
	    ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.onlinebillprep.report.ReportQueryDAOImpl");
	    rptQueryDAO.setSessionFactory(lObjSessionFactory);
	    try
	    {
		String strFormDate=reportObj.getParameterValue("Datefrom").toString();
		String fstrFormDate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(strFormDate));
		String strToDate=reportObj.getParameterValue("Dateto").toString();
		String fstrToDate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(strToDate));
		Long subjectId = Long.parseLong(reportObj.getParameterValue("billType").toString());
		String locID=reportObj.getLocId();
		String strLocationCode=getLocationCodeFromLocId(Long.parseLong(locID));
						
		billRegisterForDDO=rptQueryDAO.getBillTrackingReportForDDo(fstrFormDate, fstrToDate,subjectId,locID,strLocationCode,langID,postId);
	    }
	    catch(Exception e)
	    {
	    	gLogger.error("Error in getBillTrackingReportForDDo of Class  ReportDAOImpl and Error is : " + e, e);
	    }
	    return billRegisterForDDO;
	}
	
	/* 
	 * Create By Sagar.
	 * 
	 * For Generate Bill Tracking Report For CashBranch. 
	 */
	
	private List getBillTrackingReportForCashBranch(ReportVO reportObj)
	{
	    List billRegisterForDDO=new ArrayList();
	    ReportQueryDAO rptQueryDAO =  (ReportQueryDAO) DAOFactory.Create("com.tcs.sgv.onlinebillprep.report.ReportQueryDAOImpl");
	    rptQueryDAO.setSessionFactory(lObjSessionFactory);
	    try
	    {
		String strFormDate=reportObj.getParameterValue("Datefrom").toString();
		String fstrFormDate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(strFormDate));
		String strToDate=reportObj.getParameterValue("Dateto").toString();
		String fstrToDate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(strToDate));
		Long subjectId = Long.parseLong(reportObj.getParameterValue("billType").toString());
		String locID=reportObj.getLocId();
		String strLocationCode=getLocationCodeFromLocId(Long.parseLong(locID));
		
		Long parent_postId = OnlineBillUtil.getParentPost(postId, lObjSessionFactory);
						
		billRegisterForDDO=rptQueryDAO.getBillTrackingReportForCashBranch(fstrFormDate, fstrToDate,subjectId,locID,strLocationCode,langID,parent_postId);
	    }
	    catch(Exception e)
	    {
	    	gLogger.error("Error in getBillTrackingReportForDDo of Class  ReportDAOImpl and Error is : " + e, e);
	    }
	    return billRegisterForDDO;
	}
	
	
	public static void main(String args[])
	{
		try{
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse("07/06/2007");
			String ffromDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
			//System.out.println(ffromDate);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private String getLocationCodeFromLocId(Long locId)
	{	
		CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		CmnLocationMst cmnLocationMst = locationDAO.read(locId);		
		return cmnLocationMst.getLocationCode();
	}
	
}
