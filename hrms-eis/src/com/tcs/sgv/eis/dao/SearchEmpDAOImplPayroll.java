package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.jtds.util.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class SearchEmpDAOImplPayroll extends GenericDaoHibernateImpl  {
	
	Log logger = LogFactory.getLog(getClass());
	Session ghibSession = null;
	
	public SearchEmpDAOImplPayroll(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}
	
	public List getEmpNameForAutoComplete(String searchKey,String lStrDDOCode) {
		
		logger.info("Inside getEmpNameForAutoComplete****** ");

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;

		sb.append("select empMst.empFname||' '||empMst.empMname||' '||empMst.empLname,empMst.empLname from OrgEmpMst empMst where UPPER(empMst.empFname) LIKE :searchKey");
		if(lStrDDOCode != null)
		{
			if(!"".equals(lStrDDOCode))
			{
				sb.append(" and ddoCode = :ddoCode");
			}
		}

		selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("searchKey", '%' + searchKey + '%');

		if(lStrDDOCode != null)
		{
			if(!"".equals(lStrDDOCode))
			{
				selectQuery.setParameter("ddoCode",lStrDDOCode.trim());
			}
		}

		List resultList = selectQuery.list();

		cmbVO = new ComboValuesVO();

		if (resultList != null && resultList.size() > 0) {
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) it.next();
				logger.info("Inside getEmpNameForAutoComplete List results are--->"+obj[0].toString());
				cmbVO.setId(obj[0].toString());
				cmbVO.setDesc(obj[0].toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}

	public List getEmpNameForAutoCompleteForPayroll(String searchKey,String locId)
	{
		
		logger.info("Inside getEmpNameForAutoComplete****** ");

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;


		logger.info("In getEmpNameForAutoCompletes locId is ************* "+locId);
		logger.info("In getEmpNameForAutoCompletes searchKey is ************* "+searchKey);
		
		sb.append("select empMst.empFname||' '||empMst.empMname||' '||empMst.empLname,empMst.empLname from OrgEmpMst empMst,OrgUserpostRlt us,OrgPostDetailsRlt po where UPPER(empMst.empFname) LIKE :searchKey");
		sb.append(" and  us.orgUserMst.userId=empMst.orgUserMst.userId and us.orgPostMstByPostId.postId=po.orgPostMst.postId and po.cmnLanguageMst.langId=1 and us.activateFlag=1 and  po.cmnLocationMst.locId="+locId+" ");

		selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("searchKey", '%' + searchKey + '%');


		logger.info("query is ****************"+sb.toString());
		List resultList = selectQuery.list();

		cmbVO = new ComboValuesVO();

		if (resultList != null && resultList.size() > 0) {
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) it.next();
				logger.info("Inside getEmpNameForAutoComplete List results are--->"+obj[0].toString());
				cmbVO.setId(obj[0].toString());
				cmbVO.setDesc(obj[0].toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}
	
	public List getEmpNameForAutoCompleteForPayrollFromEmpLname(String searchKey,String locId,long langId)
	{
		
		logger.info("Inside getEmpNameForAutoComplete****** ");

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;


		logger.info("In getEmpNameForAutoCompletes locId is ************* "+locId);
		logger.info("In getEmpNameForAutoCompletes searchKey is ************* "+searchKey);
		
		sb.append("select empMst.empFname||' '||empMst.empMname||' '||empMst.empLname,empMst.empLname from OrgEmpMst empMst,OrgUserpostRlt us,OrgPostDetailsRlt po where UPPER(empMst.empLname) LIKE :searchKey");
		sb.append(" and  us.orgUserMst.userId=empMst.orgUserMst.userId and us.orgPostMstByPostId.postId=po.orgPostMst.postId and po.cmnLanguageMst.langId=1 and us.activateFlag=1 and  po.cmnLocationMst.locId="+locId+" ");

		selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("searchKey", '%' + searchKey + '%');


		logger.info("query is ****************"+sb.toString());
		List resultList = selectQuery.list();

		cmbVO = new ComboValuesVO();

		if (resultList != null && resultList.size() > 0) {
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) it.next();
				logger.info("Inside getEmpNameForAutoComplete List results are--->"+obj[0].toString());
				cmbVO.setId(obj[0].toString());
				cmbVO.setDesc(obj[0].toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}
	public List getEmpNameForAutoCompleteFromSevarthId(String searchKey,String locId,long langId)
	{
		
		logger.info("Inside getEmpNameForAutoCompleteFromSevarthId****** ");

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;


		logger.info("In getEmpNameForAutoCompleteFromSevarthId locId is ************* "+locId);
		logger.info("In getEmpNameForAutoCompleteFromSevarthId searchKey is ************* "+searchKey);
		
		sb.append("select empMst.empFname||' '||empMst.empMname||' '||empMst.empLname,empMst.empLname from OrgEmpMst empMst,OrgUserpostRlt us,OrgPostDetailsRlt po,HrEisEmpMst emp where UPPER(emp.sevarthEmpCode) LIKE :searchKey");
		sb.append(" and empMst.empId=emp.orgEmpMst.empId and  us.orgUserMst.userId=empMst.orgUserMst.userId and us.orgPostMstByPostId.postId=po.orgPostMst.postId and po.cmnLanguageMst.langId=1 and us.activateFlag=1 and  po.cmnLocationMst.locId="+locId+" ");

		selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("searchKey", '%' + searchKey + '%');


		logger.info("query is ****************"+sb.toString());
		List resultList = selectQuery.list();

		cmbVO = new ComboValuesVO();

		if (resultList != null && resultList.size() > 0) {
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) it.next();
				logger.info("Inside getEmpNameForAutoComplete List results are--->"+obj[0].toString());
				cmbVO.setId(obj[0].toString());
				cmbVO.setDesc(obj[0].toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}

}
