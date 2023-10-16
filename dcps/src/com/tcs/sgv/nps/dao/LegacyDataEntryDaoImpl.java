package com.tcs.sgv.nps.dao;

import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp; 

//9833581084 tripati

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class LegacyDataEntryDaoImpl extends GenericDaoHibernateImpl implements
LegacyDataEntryDao {
	
	
private final Log gLogger = LogFactory.getLog(getClass());
Session ghibSession = null;

/**
* 
* @param type
* @param sessionFactory
*/
public LegacyDataEntryDaoImpl(Class type, SessionFactory sessionFactory) {

super(type);
ghibSession = sessionFactory.getCurrentSession();
setSessionFactory(sessionFactory);

}

@Override
public String getDdoCode(Long gLngPostId){
	logger.info("---- insert ingetDdoCode ---");
	Session session=getSession();
	String lStrDdoCode = null;
	StringBuilder lSBQuery = new StringBuilder();
	lSBQuery.append(" SELECT distinct REPT_DDO_CODE");
	lSBQuery.append(" FROM rlt_zp_ddo_map where REPT_DDO_POST_ID='"+gLngPostId+"' ");

	Query query = session.createSQLQuery(lSBQuery.toString());
	List lLstCodeList = query.list();
	logger.info("---- insert in getDdoCode---"+lSBQuery.toString());
	if(lLstCodeList != null)
	{
		if(lLstCodeList.size() != 0)
		{
			if(lLstCodeList.get(0) != null)
			{
				lStrDdoCode = lLstCodeList.get(0).toString();
			}
		}
	}

	return lStrDdoCode;
}


public List getAllEmp(String lStrSevaarthId ,String lStrEmployeeName, String lStrDcpsId,String ddoCode){
	//logger.info("lStrUser...."+strDdoCode);
	StringBuilder lSBQuery = new StringBuilder();
	List lLstEmpDeselect = null;
	Query lQuery = null;

	logger.info("inside get All Emp");
	lSBQuery.append("select emp.dcps_id,emp.emp_name,emp.sevarth_id,to_char(emp.DOJ,'DD/MM/YYYY')as doj,to_char(emp.SUPER_ANN_DATE,'DD/MM/YYYY')as SUPER_ANN_DATE,cast(24*(0.12*((BASIC_PAY) + (1.19*BASIC_PAY) + (0.05*(1.19*BASIC_PAY)))) as bigint),cast (12*(0.12*((BASIC_PAY) + (1.19*BASIC_PAY) + (0.05*(1.19*BASIC_PAY))))as bigint),emp.pran_no,to_char(emp.EMP_SERVEND_DT,'DD/MM/YYYY')as EMP_SERVEND_DT,emp.PRAN_REMARK from mst_dcps_emp emp inner join rlt_zp_ddo_map rlt on emp.ddo_code=rlt.zp_ddo_code ");
	lSBQuery.append("where emp.PRAN_NO is not null and emp.zp_status=10 and emp.DCPS_OR_GPF='Y'  and rlt.REPT_DDO_CODE='"+ddoCode+"' and (emp.SEVARTH_ID='"+lStrSevaarthId+"' or emp.EMP_NAME='"+lStrEmployeeName+"' or emp.DCPS_ID='"+lStrDcpsId+"'  )");
	lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		logger.info("query  getEmployee for Legact data entry"+lQuery.toString());

	
		lLstEmpDeselect = lQuery.list();
	logger.info("size is "+lLstEmpDeselect.size());

	return lLstEmpDeselect;	
}

@Override
public int saveData(String lStrSevaarthId, String lStrDcpsId, long period, double empContri, double employerContri,
		double empInterest, double employerInterest, double total, String contriStartDt, String contriEndtDt,
		Map objectArgs,String remark) {
	Session session=getSession();
	
	final Map loginMap = (Map) objectArgs.get("baseLoginMap");
    final long lLongLoggedInLocation = Long.valueOf(loginMap.get("locationId").toString()).longValue();
   Long userId;
   Long primaryPostId;
   Long npsId= 0l;
   int count=0;
try {
	userId = StringUtility.convertToLong(loginMap.get("userId").toString()).longValue();
	primaryPostId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString()).longValue();
 
	

	
	 npsId = IFMSCommonServiceImpl.getNextSeqNum("DCPS_LEGACY_DATA", objectArgs);
	logger.info("npsId******"+npsId);;
	logger.info("----insert in DCPS_LEGACY_DATA ---123");
	StringBuffer sb = new StringBuffer();
	sb.append(" insert into DCPS_LEGACY_DATA(NPS_ID,DDO_CODE,SEVARTH_ID,DCPS_ID,DCPS_EMP_ID,EMP_CONTRI,EMPLOYER_CONTRI,EMP_INT,EMPLOYER_INT, ");
	sb.append(" TOTAL,YEAR,MONTH,STATUS,CREATED_DATE,CREATED_POST_ID,UPDATED_DATE,UPDATED_POST_ID,APPROVAL_DATE,REMARKS,PERIOD,CONTRI_START_DATE,CONTRI_END_DATE,BATCH_ID) ");
	sb.append(" VALUES ('"+npsId+"',(select ddo_code from mst_dcps_emp where  SEVARTH_ID='"+lStrSevaarthId+"'),'"+lStrSevaarthId+"','"+lStrDcpsId+"',(select DCPS_EMP_ID from mst_dcps_emp where SEVARTH_ID='"+lStrSevaarthId+"'),"+empContri+","+employerContri+","+empInterest+","+employerInterest+","+total+",0,0,2,sysdate,'"+primaryPostId+"',null,null,null,'"+remark+"','"+period+"','"+contriStartDt+"','"+contriEndtDt+"',null) ");
	
	
	
	logger.info("---- insert in DCPS_LEGACY_DATA ---");
	Query query = session.createSQLQuery(sb.toString());
	logger.info("---- query---"+sb);
	count=query.executeUpdate();
	
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return count;
}
@Override
public int updateForRejected(String lStrSevaarthId, long period, double empContri, double employerContri,
		double empInterest, double employerInterest, double total,String remark) {
	// TODO Auto-generated method stub
	int row;
	logger.info("----  updateForRejected ---");
	Session session=getSession();
	String lStrDdoCode = null;
	Query lQuery = null;
	StringBuffer sb = new StringBuffer();
	sb.append(" update dcps_legacy_data set emp_contri="+empContri+",employer_contri="+employerContri+", emp_int="+empInterest+", employer_int="+employerInterest+",status='2',TOTAL='"+total+"', ");
	sb.append("UPDATED_DATE=sysdate,remarks = '"+remark+"' where sevarth_id='"+lStrSevaarthId+"' and period='"+period+"' and status='0' ");

	lQuery = session.createSQLQuery(sb.toString());
	logger.info("query  updateForRejected for Legact data entry"+sb.toString());
	
	row=lQuery.executeUpdate();
	logger.info("countofrow123"+row);
	
	return row;
}

@Override
public int validationOfPeriodWithStatus(String lStrSevaarthId, String period) {

		Session session=getSession();
		logger.info("lStrUser...."+lStrSevaarthId);
		logger.info("period...."+period);
		List statuslist = null;
		int strStatus=0;
		try{
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
		logger.info("hi i am in new reg ddo dao");
		 sb.append("select cast(status as integer)  from dcps_legacy_data  where sevarth_id='"+lStrSevaarthId+"' and period='"+period+"' ");
		lQuery = session.createSQLQuery(sb.toString());
		logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			 
		statuslist=lQuery.list();
			
		if(statuslist.size()>0) {
			
			strStatus=(int) statuslist.get(0);
		}
		else {
			strStatus=10;
		}
			logger.info("Legacydata status of Period"+statuslist.size());
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		
		return strStatus;
	}


@Override
public String validationOfPeriod(String lStrSevaarthId, String period) {

		Session session=getSession();
		logger.info("lStrUser...."+lStrSevaarthId);
		logger.info("period...."+period);
		List<MstEmp> Countlist = null;
		String strCount="";
		try{
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
	
		logger.info("hi i am in new reg ddo dao");
		
		 if(period.equalsIgnoreCase("10001198258")){
			sb.append("select * from dcps_legacy_data  where sevarth_id='"+lStrSevaarthId+"' and period='"+period+"' and contri_start_date between '2011-04-01' "
					+ "and '2021-03-31' and CONTRI_END_DATE <= '2021-03-31' and status <> 0 ");
			
			lQuery = session.createSQLQuery(sb.toString());
			logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			
			Countlist=lQuery.list();
			if(Countlist.size()>0)
			{
				 strCount="failure";
			
			}
						
		} else if(period.equalsIgnoreCase("10001198259")){
			sb.append("select * from dcps_legacy_data  where sevarth_id='"+lStrSevaarthId+"' and period='"+period+"' and contri_start_date between '2021-04-01' "
					+ "and '2022-03-31' and CONTRI_END_DATE <= '2022-03-31' and status <> 0 ");
			
			lQuery = session.createSQLQuery(sb.toString());
			logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			
			Countlist=lQuery.list();
			if(Countlist.size()>0)
			{
				 strCount="failure";
			
			}
						
		} else if(period.equalsIgnoreCase("10001198260")){
			sb.append("select * from dcps_legacy_data  where sevarth_id='"+lStrSevaarthId+"' and period='"+period+"' and contri_start_date between '2022-04-01' "
					+ "and '2023-03-31' and CONTRI_END_DATE <= '2023-03-31' and status <> 0 ");
			
			lQuery = session.createSQLQuery(sb.toString());
			logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			
			Countlist=lQuery.list();
			if(Countlist.size()>0)
			{
				 strCount="failure";
			
			}
						
		}else if(period.equalsIgnoreCase("10001198261")){
			sb.append("select * from dcps_legacy_data  where sevarth_id='"+lStrSevaarthId+"' and period='"+period+"' and contri_start_date between '2006-04-01' "
					+ "and '2011-03-31' and CONTRI_END_DATE <= '2011-03-31' and status <> 0 ");
			
			lQuery = session.createSQLQuery(sb.toString());
			logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			
			Countlist=lQuery.list();
			if(Countlist.size()>0)
			{
				 strCount="failure";
			
			}
						
		}
	
			logger.info("Count of Period"+strCount);
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		
		return strCount;
	}

@Override
public List viewLegacyDataEntry(String lStrSevaarthId, String lStrEmpName, String lStrDcpsId, String ddoCode) {
	// TODO Auto-generated method stub
	StringBuilder lSBQuery = new StringBuilder();
	Query lQuery = null;
	List<MstEmp> EmpList = null;
	//logger.info("hi i am    in view legacy data");
	lSBQuery.append(" select emp.emp_name,emp.dcps_id,emp.sevarth_id,dcps.period,dcps.emp_contri,dcps.employer_contri,dcps.emp_int,dcps.employer_int,dcps.TOTAL,dcps.status from mst_dcps_emp emp inner join DCPS_LEGACY_DATA dcps " +
			" on emp.dcps_id=dcps.dcps_id where  dcps.status='2' ");
	lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		logger.info("query  viewdata for Legact data entry"+lQuery.toString());
		
	EmpList = lQuery.list();
	logger.info("size is "+EmpList.size());
	return EmpList;	
}

@Override
public int verifySavedData(String lStrSevaarthId, String period) {
	// TODO Auto-generated method stub
		Session session=getSession();
		logger.info("lStrUser...."+lStrSevaarthId);
		logger.info("period....12345"+period);
		int count=0;
		try{
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
	
		logger.info("hi i am    in new reg ddo dao");
		sb.append(" update dcps_legacy_data set status='1',UPDATED_DATE=sysdate ,APPROVAL_DATE=sysdate where sevarth_id='"+lStrSevaarthId+"' and period='"+period+"' and status=2 "); 
		lQuery = session.createSQLQuery(sb.toString());
			logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			
			count=lQuery.executeUpdate();
			logger.info("countofrow"+count);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

@Override
public List getAllTreasuries() {
	// TODO Auto-generated method stub
		List lstAis = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("  SELECT LOOKUP_ID,LOOKUP_NAME FROM CMN_LOOKUP_MST where PARENT_LOOKUP_ID = 10001198257 ");
			Query LsQuery = ghibSession.createSQLQuery(sb.toString());

			logger.info("Script is ----------------" + LsQuery.toString());
			List lLstResult = LsQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			if ((lLstResult != null) && (lLstResult.size() != 0)) {
				lstAis = new ArrayList();

				for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
					Object[] obj = (Object[]) lLstResult.get(liCtr);
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(obj[0].toString());
					lObjComboValuesVO.setDesc(obj[1].toString());
					lstAis.add(lObjComboValuesVO);
				}
			} else {
				lstAis = new ArrayList();
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId("-1");
				lObjComboValuesVO.setDesc("--ALL--");
				lstAis.add(lObjComboValuesVO);
			}
		} catch (Exception e) {
			logger.info("Error found in getAllTreasuries ----------" + e);
		}
		return lstAis;
	
}

@Override
public int rejectSavedData(String lStrSevaarthId, String period, String remarks) {
	// TODO Auto-generated method stub
		Session session=getSession();
		logger.info("lStrUser...."+lStrSevaarthId);
		logger.info("period...."+period);
		int count=0;
		try{
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
	
		//logger.info("hi i am    in new reg ddo dao");
		sb.append(" update dcps_legacy_data set status='0', UPDATED_DATE=sysdate,REJECTION_DATE=sysdate,remarks='"+remarks+"' where sevarth_id='"+lStrSevaarthId+"' and period='"+period+"' and status=2  "); 
		lQuery = session.createSQLQuery(sb.toString());
			logger.info("query  approveSavedData for Legact data entry"+sb.toString());
			
			count=	lQuery.executeUpdate();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
}
	
	
	