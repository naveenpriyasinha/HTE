package com.tcs.sgv.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class CHBCalculationDAOImpl extends GenericDaoHibernateImpl implements CHBCalculationDAO{

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;
	
	public CHBCalculationDAOImpl(Class type , SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	@Override
	public String getDdoCodeForCHB(Long lngPostId) {

		String lStrDdoCode = null;
		List lLstDdoDtls = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT OD.ddoCode");
			lSBQuery.append(" FROM  OrgDdoMst OD");
			lSBQuery.append(" WHERE OD.postId = :postId ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("postId", lngPostId);
			

			lLstDdoDtls = lQuery.list();
			
			
			if(lLstDdoDtls != null)
			{
				if(lLstDdoDtls.size()!= 0)
				{
					if(lLstDdoDtls.get(0) != null)
					{
						lStrDdoCode = lLstDdoDtls.get(0).toString();
					}
				}
			}

		} catch (Exception e) {
			//e.printStackTrace();
			gLogger.error("Error is :" + e, e);

		}
		logger.info("Query Result is ddo::"+lLstDdoDtls);
		return lStrDdoCode;
	}

	@Override
	public Long getDcpsEmpIdFromSevaarthIdOrName(String strEmpName,
			String strSevarthId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Long dcpsEmpId = 0L;
		Query hqlQuery = null;

		lSBQuery.append(" select dcpsEmpId FROM MstEmp emp where emp.regStatus in (1,2) ");

		if (!"".equals(strEmpName) && strEmpName != null) {
			lSBQuery.append(" AND UPPER(emp.name) = :strEmpName");
		}
		if (!"".equals(strSevarthId) && strSevarthId != null) {
			lSBQuery.append(" AND UPPER(emp.sevarthId) = :strSevarthId");
		}

		hqlQuery = ghibSession.createQuery(lSBQuery.toString());

		if (strEmpName != null && !strEmpName.equals("")) {
			hqlQuery.setParameter("strEmpName", strEmpName.trim());
		}
		if (strSevarthId != null && !strSevarthId.equals("")) {
			hqlQuery.setParameter("strSevarthId", strSevarthId.trim());
		}

		tempList = hqlQuery.list();

		if(tempList != null && tempList.size() != 0)
		{
			if(tempList.get(0) != null)
			{
				if(!"".equals(tempList.get(0).toString()))
				{
					dcpsEmpId = Long.valueOf(tempList.get(0).toString());
				}
			}
		}

		return dcpsEmpId;

	}

	@Override
	public boolean checkMultipleEntryInHstEmpForEmpIdOrNot(Long longDcpsEmpId) {

		StringBuilder lSBQuery = new StringBuilder();
		List<Long> tempList = new ArrayList();
		Boolean flag = false;

		lSBQuery.append(" select hstdcpsId FROM HstEmp WHERE dcpsEmpId = :dcpsEmpId");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("longDcpsEmpId", longDcpsEmpId);

		tempList = lQuery.list();
		if (tempList.size() > 1 ) {
			flag = true;
		}
		return flag;

	}

	@Override
	public List getEmpListForSelection(String strEmpName, String strSevarthId) {

		List lLstEmpSelect = null;
		Query sqlQuery = null;
		StringBuilder lStrQuery = new StringBuilder();
		Date lDtCurrDate = SessionHelper.getCurDate();

		try {

			lStrQuery.append(" SELECT EM.sevarthId , EM.name , EM.ratePerHour ");
			
			lStrQuery.append(" from MstEmp EM  ");

			if (!"".equals(strEmpName) && strEmpName != null) {
				lStrQuery.append(" where UPPER(EM.name) = '" + strEmpName + "'");
			}
			if (!"".equals(strSevarthId) && strSevarthId != null) {
				lStrQuery.append(" where UPPER(EM.sevarthId) = '" + strSevarthId + "'");
			}

			sqlQuery = ghibSession.createQuery(lStrQuery.toString());
			//sqlQuery.setDate("currentDate", lDtCurrDate);

			gLogger.info("Query****"+lStrQuery.toString());
			lLstEmpSelect = sqlQuery.list();

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			//throw e;
		}
		return lLstEmpSelect;
	}

	@Override
	public List getEmpNameForAutoCompleteFORCHB(String upperCase,
			String strSearchType, String strDDOCode, String strSearchBy) {

		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		logger.error("strDDOCode is :" +strDDOCode);
		
		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;
		Date lDtCurrDate = SessionHelper.getCurDate();

		sb
				.append("select name,name from MstEmp where UPPER(name) LIKE :searchKey and regStatus in (1,2) and createdDate>'2012-10-31' ");
		
		if (strDDOCode != null) {
			if (!"".equals(strDDOCode) && strSearchBy.equals("searchFromDDOSelection")) {
				sb.append(" and ddoCode in(select ZP_DDO_CODE from ZpRltDdoMap where (REPT_DDO_CODE= '"+strDDOCode+"') or (FINAL_DDO_CODE ='"+strDDOCode+"') or (ZP_DDO_CODE ='"+strDDOCode+"'))");
			}
		}
		
		
		
		if (strSearchType != null) {
			if (!"".equals(strSearchType)) {
				if (strSearchType.trim().equals("OnlyDCPS")) {
					sb.append(" and dcpsId is not null");
				}
			}
		}
		
		sb.append(" and ( servEndDate is null or servEndDate  >= :currentDate ) and ratePerHour is not null ");

		selectQuery = ghibSession.createQuery(sb.toString());
		selectQuery.setParameter("searchKey", "%" + upperCase + "%");
		selectQuery.setDate("currentDate", lDtCurrDate);

		/*if (strDDOCode != null) {
			if (!"".equals(strDDOCode)) {
				selectQuery.setParameter("ddoCode", strDDOCode.trim());
			}
		}
*/
		List resultList = selectQuery.list();
		
		gLogger.info("Query****"+resultList.size());
		gLogger.info("Query****"+sb.toString());

		cmbVO = new ComboValuesVO();

		if (resultList != null && resultList.size() > 0) {
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) it.next();
				cmbVO.setId(obj[1].toString());
				cmbVO.setDesc(obj[1].toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;

	}

	@Override
	public String getDdoCodeForDDOForCHB(String strLocationCode) {

		String lStrDdoCode = null;
		List lLstDdoDtls = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT OD.ddoCode");
			lSBQuery.append(" FROM  OrgDdoMst OD");
			lSBQuery.append(" WHERE OD.locationCode = :locCode ");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("locCode", strLocationCode);

			lLstDdoDtls = lQuery.list();

			if(lLstDdoDtls != null)
			{
				if(lLstDdoDtls.size()!= 0)
				{
					if(lLstDdoDtls.get(0) != null)
					{
						lStrDdoCode = lLstDdoDtls.get(0).toString();
					}
				}
			}

		} catch (Exception e) {
			//e.printStackTrace();
			gLogger.error("Error is :" + e, e);

		}
		return lStrDdoCode;
	}

	@Override
	public Long getRatePerHoursForCHBCalculation(String sevarthID,String txtHours) {
		
		    List lstRatePerHour = null;
		    Long rateprhr=null;
			Session session = getSession();
			StringBuffer sb = new StringBuffer();
			logger.info("---- getRatePerHoursForCHBCalculation ---");

			sb.append(" select dcp.RATE_PER_HOUR from MST_DCPS_EMP dcp ");
			sb.append(" where dcp.SEVARTH_ID = '"+sevarthID+"' ");
			 
			logger.info("---- getRatePerHoursForCHBCalculation ---"+sb.toString());
			Query query = session.createSQLQuery(sb.toString());
			lstRatePerHour = query.list();
			if(lstRatePerHour!=null && lstRatePerHour.size()>0){
				rateprhr=Long.parseLong(lstRatePerHour.get(0).toString());
			}
			
			
			logger.info("Query Result is::"+lstRatePerHour);
			return rateprhr;
			}

	@Override
	public Long updateRatePerHoursForCHBCalculation(String sevarthID,
			String basicValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getMonthList() {
		List monthList = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- getMonthList ---");

		sb.append(" select MONTH_ID,MONTH_NAME from SGVA_MONTH_MST where LANG_ID='en_US' ");
		
		 
		logger.info("---- getMonthList ---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		monthList = query.list();	
		
		logger.info("Query Result is::"+monthList);
		return monthList;
	}

	@Override
	public List getYearList() {
		List yearList = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- getYearList ---");

		sb.append(" select FIN_YEAR_ID,FIN_YEAR_CODE from SGVC_FIN_YEAR_MST where FIN_YEAR_ID in (25,26,27,28,29) ");
		
		 
		logger.info("---- getYearList ---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		yearList = query.list();	
		
		logger.info("Query Result is::"+yearList);
		return yearList ;
	}

	@Override
	public String getEmpId(String strEmpName) {
		String empId = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- getEmpId---");
		sb.append("select EMP_ID from  ORG_EMP_MST where EMP_FNAME='"+strEmpName+"' ");
		logger.info("---- getEmpId---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		empId = query.uniqueResult().toString();
		logger.info("Query Result is::"+empId);
		return empId;
	}

	@Override
	public Integer checkCHBData(String empId, String month, String year) {
		Integer check = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- checkCHBData---");
		sb.append("select count(1) from  HR_PAY_CHB_DATA where EMP_ID='"+empId+"' and MONTH_ID='"+month+"' and YEAR_ID='"+year+"' ");
		logger.info("---- checkCHBData---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		check = (Integer) query.uniqueResult();
		logger.info("Query Result is::"+check);
		return check;
	}

	@Override
	public void insertCHBDataValue(long chbDataId, String empId, String month,
			String year, String workingHours, String BasicValue,String updatedDate, String createdBy, String updatedBy, String strDdoCode) {
		
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- insertCHBDataValue ---"); 
		Date sysdate = new Date();
		//sb.append("insert into HR_PAY_CHB_DATA (CHB_DATA_ID,EMP_ID,MONTH_ID,YEAR_ID,WORKING_HOURS,BASIC_VALUE,Created_date,Created_by,DDO_code) values ("+chbDataId+" , "+empId+" , "+month+" , "+year+" , "+workingHours+" , "+BasicValue+" , sysdate , "+createdBy+" ,'"+strDdoCode+"')");
		sb.append("insert into HR_PAY_CHB_DATA VALUES ("+chbDataId+" , "+empId+" , "+month+" , "+year+" , "+workingHours+" , "+BasicValue+" , sysdate , "+updatedDate+" , "+createdBy+", "+updatedBy+" , '"+strDdoCode+"')");

		logger.info("---- insertCHBDataValue DAo---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		query.executeUpdate();
		
	}

	
	@Override
	public void updateCHBDataValue(String empId, String month, String year,String workingHours, String BasicValue, String createdDate,
			String createdBy, String updatedBy, String strDdoCode) {
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- insertCHBDataValue ---"); 
		Date sysdate = new Date();
		
		//sb.append("insert into HR_PAY_CHB_DATA VALUES ("+chbDataId+" , "+empId+" , "+month+" , "+year+" , "+workingHours+" , "+BasicValue+" , sysdate , "+updatedDate+" , "+createdBy+", "+updatedBy+" , '"+strDdoCode+"')");
		sb.append("update HR_PAY_CHB_DATA set WORKING_HOURS = "+workingHours+" ,  BASIC_VALUE = "+BasicValue+" , CREATED_DATE = "+createdDate+" , UPDATED_DATE =  sysdate , CREATED_BY = "+createdBy+" , UPDATED_BY = "+updatedBy+" , DDO_CODE = '"+strDdoCode+"'  where EMP_ID = "+empId+" and MONTH_ID = "+month+" and YEAR_ID = "+year+"");
		logger.info("---- insertCHBDataValue DAo---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		query.executeUpdate();
		
	}

	

	
}
