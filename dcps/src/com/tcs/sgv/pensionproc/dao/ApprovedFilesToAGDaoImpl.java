package com.tcs.sgv.pensionproc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ApprovedFilesToAGDaoImpl  extends GenericDaoHibernateImpl  {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger.getLogger(ApprovedFilesToAGDaoImpl.class);

	private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionproc/PensionCaseConstants");

	public ApprovedFilesToAGDaoImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	
	
	public Integer getNameRequestCount(Long lLngPostId,	String lStrLocationCode) throws Exception {
		// TODO Auto-generated method stub
		List lLstResult = new ArrayList();
		Integer lIntCount = 0;

		System.out.println("lStrLocationCode"+lStrLocationCode);
		System.out.println("lLngPostId"+lLngPostId);
		
		try {
			Session ghibSession = getSession();
			StringBuffer lSBQuery = new StringBuffer();

			
			
			
			lSBQuery.append("SELECT count(1) FROM TRN_PNSNPROC_INWARDPENSION tpi ");
			lSBQuery.append("join CMN_LOCATION_MST clm on clm.LOCATION_CODE=tpi.TRSRY_ID_PENSION and clm.DEPARTMENT_ID=100003 ");
			lSBQuery.append("where clm.OFFICE_CODE in (:locationCode) and  CASE_STATUS ='FRWDTOAG' and DRAFT_FLAG='F' ");
			
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setString("locationCode", lStrLocationCode);			
			lLstResult = lQuery.list();
			
			
			System.out.println("lLstResult"+lLstResult.size());
			lIntCount = Integer.parseInt(lLstResult.get(0).toString());
			System.out.println("lIntCount"+lIntCount);

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			throw (e);
		}

		return lIntCount;
	}
	
	public List getNameRequestList(Map displayTag,
			Long lLngPostId, String lStrLocationCode) throws Exception {
		// TODO Auto-generated method stub
		List lLstTrnPensionNameDtls = new ArrayList();

		try {
			Session ghibSession = getSession();
			StringBuffer lSBQuery = new StringBuffer();

		
		/*	lSBQuery.append("SELECT  FROM TRN_PNSNPROC_INWARDPENSION tpi ");
			lSBQuery.append("join CMN_LOCATION_MST clm on clm.LOCATION_CODE=tpi.TRSRY_ID_PENSION and clm.DEPARTMENT_ID=100003 ");
			lSBQuery.append("where clm.OFFICE_CODE in (:locationCode) and  CASE_STATUS ='FRWDTOAG' and DRAFT_FLAG='F' ");
			*/
			
			lSBQuery.append("SELECT tpi.INWARD_NO,tpi.SEVAARTH_ID,tpp.PNSNR_NAME,clm1.loc_Name,to_char(tpp.RETIREMENT_DATE,'dd/mm/yyyy'),tpi.INWARD_PENSION_ID FROM TRN_PNSNPROC_INWARDPENSION tpi "); 
			lSBQuery.append("join CMN_LOCATION_MST clm on clm.LOCATION_CODE=tpi.TRSRY_ID_PENSION and clm.DEPARTMENT_ID=100003 ");
			lSBQuery.append("join TRN_PNSNPROC_PNSNRDTLS tpp on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
			lSBQuery.append("join CMN_LOCATION_MST clm1 on clm1.LOC_ID=tpp.DEPARTMENT_ID "); 
			lSBQuery.append("where clm.OFFICE_CODE in  (:locationCode) and  CASE_STATUS ='FRWDTOAG' and DRAFT_FLAG='F' ");
			
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag.get(Constants.KEY_PAGE_NO) : 1);
			lQuery.setFirstResult((pageNo.intValue() - 1) * Constants.PAGE_SIZE);
			lQuery.setMaxResults(Constants.PAGE_SIZE);
		
			lQuery.setString("locationCode", lStrLocationCode);
			
			lLstTrnPensionNameDtls = lQuery.list();

			
			System.out.println("LstTrnPensionNameDtls"+lLstTrnPensionNameDtls.size());
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			throw (e);
		}

		return lLstTrnPensionNameDtls;
	}

	public List getNameRequestListBySevaarthID(Map displayTag, Long gLngPostId,
			String gStrLocationCode, String sevaarthId)throws Exception {
		List lLstTrnPensionNameDtls = new ArrayList();

		try {
			Session ghibSession = getSession();
			StringBuffer lSBQuery = new StringBuffer();

	
			lSBQuery.append("SELECT tpi.INWARD_NO,tpi.SEVAARTH_ID,tpp.PNSNR_NAME,clm1.loc_Name,to_char(tpp.RETIREMENT_DATE,'dd/mm/yyyy'),tpi.INWARD_PENSION_ID FROM TRN_PNSNPROC_INWARDPENSION tpi "); 
			lSBQuery.append("join CMN_LOCATION_MST clm on clm.LOCATION_CODE=tpi.TRSRY_ID_PENSION and clm.DEPARTMENT_ID=100003 ");
			lSBQuery.append("join TRN_PNSNPROC_PNSNRDTLS tpp on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
			lSBQuery.append("join CMN_LOCATION_MST clm1 on clm1.LOC_ID=tpp.DEPARTMENT_ID "); 
			lSBQuery.append("where clm.OFFICE_CODE in  (:locationCode) and  CASE_STATUS ='FRWDTOAG' and DRAFT_FLAG='F' and SEVAARTH_ID=:sevaarthId ");
			
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

			Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO) ? (Integer) displayTag.get(Constants.KEY_PAGE_NO) : 1);
			lQuery.setFirstResult((pageNo.intValue() - 1) * Constants.PAGE_SIZE);
			lQuery.setMaxResults(Constants.PAGE_SIZE);
		
			lQuery.setString("locationCode", gStrLocationCode);
			lQuery.setString("sevaarthId", sevaarthId);
			
			lLstTrnPensionNameDtls = lQuery.list();
			
			System.out.println("LstTrnPensionNameDtls"+lLstTrnPensionNameDtls.size());
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
			throw (e);
		}

		return lLstTrnPensionNameDtls;
		
	}
	
	
	
}
