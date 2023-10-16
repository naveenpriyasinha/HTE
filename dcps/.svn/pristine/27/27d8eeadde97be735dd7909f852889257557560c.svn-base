package com.tcs.sgv.pensionproc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ChangeDdoCodeDaoImpl extends GenericDaoHibernateImpl {
	private final Log gLogger = LogFactory.getLog(getClass());
Session ghibSession = null;


public ChangeDdoCodeDaoImpl(Class type, SessionFactory sessionFactory) {
	super(type);
	this.ghibSession = sessionFactory.getCurrentSession();
	setSessionFactory(sessionFactory);
}

public Object[] getPensionerDtls(String sevarthId){
	
	Object[] lstDetails = null;
	
	try{
				StringBuilder sb = new StringBuilder(); 
				
				sb.append("SELECT dtls.PNSNR_NAME,to_char(dtls.RETIREMENT_DATE,'dd-mm-yyyy'),inwrd.DDO_CODE,dtls.DDO_OFFICE_NAME FROM "); 
				sb.append("TRN_PNSNPROC_INWARDPENSION inwrd ");
				sb.append("join TRN_PNSNPROC_PNSNRDTLS dtls on inwrd.INWARD_PENSION_ID=dtls.INWARD_PENSION_ID ");
				sb.append("where inwrd.SEVAARTH_ID=:sevarthId and inwrd.CASE_STATUS in ('DRAFT','FWDBYDEO') ");
				
				Query lQuery = this.ghibSession.createSQLQuery(sb.toString());
				logger.info("Query is**********"+sb.toString());
				lQuery.setParameter("sevarthId",sevarthId);
				
				if(lQuery.list().size()>0)
					lstDetails = (Object[]) lQuery.list().get(0);
				
	}
	catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return lstDetails;
	
	}

public int updateDdoCode(String sevarthid,String ddoCode,String prevDdoCode,String remark){
	
	int result= 0;
	int tempResult1=0;
	List tempResult= null;
	List finalList=null;
	
	
	try
	{
		StringBuilder sb = new StringBuilder(); 	
		sb.append("SELECT distinct DDO_CODE FROM TRN_PNSNPROC_INWARDPENSION where SEVAARTH_ID=:sevarthId ");
		Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
		selectQuery.setParameter("sevarthId",sevarthid);
		logger.info("Query is**********"+sb.toString());
		tempResult=selectQuery.list();
		
		System.out.println("tempresult**"+tempResult.get(0).toString());
		StringBuilder sb1 = new StringBuilder();
		sb1.append("update TRN_PNSNPROC_INWARDPENSION set prev_ddo_code=:tempResult,ddo_code=:ddoCode where SEVAARTH_ID=:sevarthId ");
		Query selectQuery1 = this.ghibSession.createSQLQuery(sb1.toString());
		selectQuery1.setParameter("sevarthId",sevarthid);
		selectQuery1.setParameter("ddoCode",ddoCode);
		selectQuery1.setParameter("tempResult", tempResult.get(0).toString());
		logger.info("Query is**********"+sb1.toString());
		tempResult1=selectQuery1.executeUpdate();
		
		if (tempResult1 >0){
			
		StringBuilder sb2 = new StringBuilder();
		sb2.append("insert into trn_pnsnproc_ddo_dtls ");
		sb2.append("values(:sevarthId,:ddoCode,:prevDdoCode,:remark,sysdate) ");
		Query selectQuery2 = this.ghibSession.createSQLQuery(sb2.toString());
		selectQuery2.setParameter("sevarthId",sevarthid);
		selectQuery2.setParameter("ddoCode",ddoCode);
		selectQuery2.setParameter("prevDdoCode",prevDdoCode);
		selectQuery2.setParameter("remark",remark);
		logger.info("Query is**********"+sb2.toString());
		result=selectQuery2.executeUpdate();
	
		
		}
}
	catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	
}
 return tempResult1;

}

public Object[] getNewDdoOfc(String ddoCode){
	
	Object[] lstDetails = null;
	
	try{
				StringBuilder sb = new StringBuilder(); 
				sb.append("SELECT OFF_NAME,ddo_code FROM MST_DCPS_DDO_OFFICE WHERE DDO_CODE=:ddoCode ");
				Query lQuery = this.ghibSession.createSQLQuery(sb.toString());
				logger.info("Query is**********"+sb.toString());
				lQuery.setParameter("ddoCode",ddoCode);
				
				if(lQuery.list().size()>0)
					lstDetails = (Object[]) lQuery.list().get(0);
				
	}
	catch (Exception e) {
		this.logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return lstDetails;
	
	}





}


