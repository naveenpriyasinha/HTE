package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPaybillActivationStatusMpg;


public class PayBillActiationStatusDAoImpl extends GenericDaoHibernateImpl<HrPaybillActivationStatusMpg, Long> implements  PayBillActivationStatusDAO
{
	
	public PayBillActiationStatusDAoImpl(Class<com.tcs.sgv.eis.valueobject.HrPaybillActivationStatusMpg> class1, SessionFactory sessionFactory)
    {
        super(class1);
        setSessionFactory(sessionFactory);
    }
	
	public List getActiveData(long langId,long locationCode)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           /*
            
select up.post_id,emp.emp_fname,emp.emp_mname,emp.emp_lname,pdr.post_name,pa.start_date_deactivation,pa.end_date_deactivation
 from org_emp_mst emp
,org_userpost_rlt up
,org_post_details_rlt pdr
 , org_post_mst pm 
left outer join hr_paybill_activation_status pa on 
pm.post_id = pa.post_id
and (pa.end_date_deactivation<sysdate or activation=1)
where 
up.user_id=emp.user_id
and up.post_id=pm.post_id 
and pm.post_id=pdr.post_id
and pm.location_code = pdr.loc_id
and pdr.post_id=pm.post_id
and pm.location_code=300022
and up.activate_flag=1 
and emp.lang_id=1
             */
            //String strQuery = "from HrPayPaybillScheduler schedulr where schedulr.cmnLanguageMst.langId ="  + langId;
            StringBuffer que = new StringBuffer("select up.post_id,emp.emp_fname||' '||emp.emp_mname||' '||emp.emp_lname,pdr.post_name,pa.start_date_deactivation,pa.end_date_deactivation ");
            que.append("from org_emp_mst emp ");
            que.append("left outer join org_userpost_rlt up on up.user_id=emp.user_id ");
            que.append("left outer join org_post_mst pm on pm.location_code="+locationCode);
            que.append(" left outer join org_post_details_rlt pdr on pm.location_code = pdr.loc_id ");
            que.append("left outer join hr_paybill_activation_status pa on pm.post_id = pa.post_id ");
            que.append("where up.post_id=pm.post_id and ");
            que.append("pm.post_id=pdr.post_id and ");
            que.append("up.activate_flag=1 and emp.lang_id=1 ");
            que.append("and ((pa.end_date_deactivation<sysdate or pa.end_date_deactivation is null) or  (pa.activation=1 or pa.activation is null))");
            que .append(" order by up.post_id");
            //Log logger = LogFactory.getLog(getClass());
            //logger.info(que.toString());
            //logger.info();
           // logger.info(que);
            Query query = hibSession.createSQLQuery(que.toString());
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            //logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	 
	public List getDeactiveData(long langId,long locationCode)
	{
		Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            /*
        select up.post_id,emp.emp_fname,emp.emp_mname,emp.emp_lname,pdr.post_name,pa.start_date_deactivation,pa.end_date_deactivation
 		from org_emp_mst emp
		,org_userpost_rlt up
		,org_post_details_rlt pdr
		, org_post_mst pm 
		,hr_paybill_activation_status pa
		where 
		pm.post_id = pa.post_id
		and (pa.end_date_deactivation>sysdate or activation=0)
		and up.user_id=emp.user_id
		and up.post_id=pm.post_id 
		and pm.post_id=pdr.post_id
		and pm.location_code = pdr.loc_id
		and pdr.post_id=pm.post_id
		and pm.location_code=300022
		and up.activate_flag=1 
		and emp.lang_id=1 
             
              */
            logger.info("going to execute dct query");
            StringBuffer que = new StringBuffer("select up.post_id,emp.emp_fname||' '||emp.emp_mname||' '||emp.emp_lname,pdr.post_name,pa.start_date_deactivation,pa.end_date_deactivation");
            que.append(" from org_emp_mst emp");
            que.append(",org_userpost_rlt up");
            que.append(",org_post_details_rlt pdr");
            que.append(",org_post_mst pm ");
            que.append(",hr_paybill_activation_status pa");
            que.append(" where ");
            que.append(" pm.post_id = pa.post_id");
            que.append(" and (pa.end_date_deactivation>sysdate and activation=0) ");
            que.append(" and up.user_id=emp.user_id");
            que.append(" and up.post_id=pm.post_id ");
            que.append(" and pm.location_code = pdr.loc_id");
            que.append(" and pdr.post_id=pm.post_id");
            que.append(" and up.activate_flag=1 ");
            que.append(" and emp.lang_id=");
            que.append(langId+" ");
            que.append(" and pm.location_code=");
            que.append(locationCode);
            que.append( " order by up.post_id");
            logger.info(que);
            Query query = hibSession.createSQLQuery(que.toString());
            list = query.list();
            return list;
            
	}
	
	public List getSpecificDataAct(String name,long billNo,long langId,long locId)
	{
		name = name.toLowerCase();
		Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            
            StringBuffer que = new StringBuffer("select up.post_id,emp.emp_fname||' '||emp.emp_mname||' '||emp.emp_lname,pdr.post_name,pa.start_date_deactivation,pa.end_date_deactivation ");
            que.append("from org_emp_mst emp ");
            que.append("left outer join org_userpost_rlt up on up.user_id=emp.user_id ");
            que.append("left outer join org_post_mst pm on pm.location_code="+locId);
            que.append(" left outer join org_post_details_rlt pdr on pm.location_code = pdr.loc_id ");
            que.append("left outer join hr_paybill_activation_status pa on pm.post_id = pa.post_id ");
            que.append("left outer join hr_pay_post_psr_mpg psr on psr.post_id = pm.post_id ");
            que.append("where up.post_id=pm.post_id and ");
            que.append("pm.post_id=pdr.post_id and ");
            que.append("up.activate_flag=1 and emp.lang_id="+langId);
            que.append(" and ((pa.end_date_deactivation<sysdate or pa.end_date_deactivation is null) or  (pa.activation=1 or pa.activation is null))");
            if(name!=null && (!name.equals("")))
            {
            	que.append(" and lower(emp.emp_fname||' '||emp.emp_mname||''||emp.emp_lname) like '%"+name+"%' ");
            	if(billNo<=0)
            	{
            		
            	}
            }
            if( billNo>0)
            {
            	que.append(" and psr.bill_No="+billNo);
            	
            	}
            que.append(" order by up.post_id ");
            logger.info(que);
            Query query = hibSession.createSQLQuery(que.toString());
            list = query.list();
            return list;
            
            	
	}

	
	public List getSpecificDataDct(String name,long billNo,long langId,long locId)
	{
		Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            
            StringBuffer que = new StringBuffer("select up.post_id,emp.emp_fname||' '||emp.emp_mname||''||emp.emp_lname,pdr.post_name,pa.start_date_deactivation,pa.end_date_deactivation");
            que.append(" from org_emp_mst emp");
            que.append(",org_userpost_rlt up");
            que.append(",org_post_details_rlt pdr");
            que.append(",org_post_mst pm ");
            que.append(",hr_pay_post_psr_mpg psr ");
            que.append(",hr_paybill_activation_status pa");
            que.append(" where ");
            que.append(" pm.post_id = pa.post_id");
            que.append(" and (pa.end_date_deactivation>sysdate and activation=0) ");
            que.append(" and up.user_id=emp.user_id");
            que.append(" and up.post_id=pm.post_id ");
            que.append(" and pm.location_code = pdr.loc_id");
            que.append(" and pm.post_id = psr.post_id");
            que.append(" and pdr.post_id=pm.post_id");
            que.append(" and up.activate_flag=1 ");
            que.append(" and emp.lang_id=");
            que.append(langId+" ");
            que.append(" and pm.location_code=");
            que.append(locId);
            if(name!=null && (!name.equals("")))
            {
            	que.append(" and lower(emp.emp_fname||' '||emp.emp_mname||''||emp.emp_lname) like '%"+name+"%'");
            }
            if(billNo>0)
            {
            	que.append(" and psr.bill_No="+billNo);
            }
            que.append(" order by up.post_id");
            Query query = hibSession.createSQLQuery(que.toString());
            list = query.list();
            return list;
            
            	
	}

	
	
	public HrPaybillActivationStatusMpg searchPost(long postId) {
		
		Session hibSession = getSession();
		 List  list = null;
			
        String query1 = "from HrPaybillActivationStatusMpg a where a.orgPostMpgPostId.postId="+ postId;
        Query sqlQuery1 = hibSession.createQuery(query1);
       list = sqlQuery1.list();
		if(list.size()>0)
		{
			return (HrPaybillActivationStatusMpg)list.get(0);
		}
		else
		{
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	
	public List getCurrentDeactiveData()
	{
		List list = null;
		 Session hibSession = getSession();
		 
		 StringBuffer que = new StringBuffer("SELECT  A.post_id");
		 que.append(" FROM hr_paybill_activation_status A");
		 que.append(" WHERE ");
	
		 que.append(" (");
		 que.append(" (start_date_deactivation<sysdate and sysdate<end_date_deactivation)");
		 que.append(" or (extract(month from sysdate)=extract(month from start_date_deactivation)  and extract(year from sysdate)= extract(year from start_date_deactivation))");
		 que.append(" or (extract(month from sysdate)=extract(month from end_date_deactivation)  and extract(year from sysdate)= extract(year from end_date_deactivation))");
		 que.append(" ) AND activation=0");

		 Query query = hibSession.createSQLQuery(que.toString());
         list = query.list();
         return list;
		
	}

}

