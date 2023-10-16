package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEmpTraMpg;


public class HrEmpTraMpgDaoImpl extends GenericDaoHibernateImpl<HrEmpTraMpg, Long> {
	
	public HrEmpTraMpgDaoImpl(Class<HrEmpTraMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }

	public List getHrEmpTraData(long empId)
	{
		List resultList = new ArrayList();
		
		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrEmpTraMpg.class);
            
        objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
        objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
        
        objCrt.add(Restrictions.eq("hrEisEmpMst.empId",empId));
        objCrt.addOrder( Property.forName("trnCounter").asc() );

        
        resultList = objCrt.list();
		
		
		return resultList;
	}
	
	public List getHrEmpTraDataHst(long empId)
	{
		List resultList = new ArrayList();
		
		//Criteria objCrt = null;
        Session hibSession = getSession();
      /*  objCrt = hibSession.createCriteria(HrEmpTraMpgHst.class);
            
        objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
        objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
        
        objCrt.add(Restrictions.eq("hrEisEmpMst.empId",empId));*/
        
        String hqlQuery="from HrEmpTraMpgHst tra where tra.hrEisEmpMst.empId = "+empId+" order by tra.id.trnCounter ";
        
        Query objCrt = hibSession.createQuery(hqlQuery);
        
        
        resultList = objCrt.list();
		
		
		return resultList;
	}
	
	/**
	 * Method will return Employee list by location who has 
	 * availed the Vehicle.
	 * @param locId
	 * @return List of Employees who has availed the vehicle
	 */
	public List getEmpListByLocation(long locId,long billNo,long langId){
		Session hibernateSession = getSession();
		StringBuffer traQryBuffer = new StringBuffer();
		traQryBuffer.append("select distinct(eisemp.emp_id),up.post_id from hr_emp_tra_mpg tra ");
		traQryBuffer.append("inner join hr_eis_other_dtls od on od.emp_id = tra.emp_id ");
		traQryBuffer.append("inner join hr_eis_emp_mst eisemp on eisemp.emp_id = od.emp_id ");
		traQryBuffer.append("inner join org_emp_mst orgemp on orgemp.emp_id = eisemp.emp_mpg_id ");
		traQryBuffer.append("inner join org_userpost_rlt up on up.user_id = orgemp.user_id ");
		
		if(billNo!=0){
			traQryBuffer.append("inner join hr_pay_post_psr_mpg psr on psr.post_id = up.post_id ");
			traQryBuffer.append("inner join mst_dcps_bill_group mpg on mpg.bill_group_id = psr.bill_no ");
		}
		traQryBuffer.append("where tra.vehical_availed = 'TRUE' and up.activate_flag=1 and tra.loc_id=");
		traQryBuffer.append(locId); 		
		if(billNo!=0){
			traQryBuffer.append(" and mpg.bill_group_id=");
			traQryBuffer.append(billNo);
			traQryBuffer.append(" and mpg.loc_id=");
			traQryBuffer.append(locId);			
		}
		traQryBuffer.append(" and orgemp.LANG_ID=");
		traQryBuffer.append(langId);
		Query traQuery = hibernateSession.createSQLQuery(traQryBuffer.toString());
		return traQuery.list();				
	}
}
