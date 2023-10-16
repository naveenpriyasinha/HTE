/**
 * This is a class which implements the AllowTypeMasterDAO and contains the methods for hr_pay_allow_type_mst table.
 * Made By:- Urvin shah.
 * Date:- 14/07/2007.
 */
package com.tcs.sgv.allowance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class AllowTypeMasterDAOImpl extends GenericDaoHibernateImpl<HrPayAllowTypeMst, Long> implements AllowTypeMasterDAO {
	
	public AllowTypeMasterDAOImpl(Class<HrPayAllowTypeMst> type, SessionFactory sessionFactory) {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	/**
	 * This method will collect all the data of hr_pay_allow_type_mst.
	 * @param HrPayAllowTypeMst
	 * @param sessionFactory
	 * @return an object of List.
	 */
	public List getAllowTypeMasterData(long langId) {
		
        Criteria objCrt = null;
        List  list = null;        
            Session hibSession = getSession();            
            Query query = hibSession.createQuery("from HrPayAllowTypeMst a where a.cmnLanguageMst.langId="+langId+" order by a.allowName");            
            list = query.list();
        return list;
    }
	
	/**
	 * This method will collect all the data of hr_pay_allow_type_mst for perticular allowance Name.
	 * @param HrPayAllowTypeMst
	 * @param sessionFactory
	 * @return List of HrPayAllowTypeMst
	 */	
	
	
	public List checkAllowNameAvailability(String newAllowName,long langId) {
		List  list = null;        
        Session hibSession = getSession();   
        
        Query query = hibSession.createQuery("from HrPayAllowTypeMst as a where a.cmnLanguageMst.langId="+langId+" and lower(a.allowName)= '"+newAllowName+"'");        
        list = query.list();
        //System.out.println("The Size od list is:-"+list.size());
    return list;
	}
	
	/**
	 * This method will collect all the data of hr_pay_allow_type_mst for perticular allowance code.
	 * @param HrPayAllowTypeMst
	 * @param sessionFactory
	 * @return HrPayAllowTypeMst
	 */	
	
	public HrPayAllowTypeMst getAllowTypeMasterDataByCode(long allowTypeCode,long langId)  {
		HrPayAllowTypeMst objPayAllowTypeMst = new HrPayAllowTypeMst();
        Criteria objCrt = null;
        List  list = null;
        
            Session hibSession = getSession();            
            Query query = hibSession.createQuery("from HrPayAllowTypeMst hrPayAllowTypeMst where hrPayAllowTypeMst.allowCode="+allowTypeCode+" and hrPayAllowTypeMst.cmnLanguageMst.langId="+langId);
            objPayAllowTypeMst = (HrPayAllowTypeMst)query.uniqueResult();
        
        return objPayAllowTypeMst;
    }
	public HrPayAllowTypeMst getAllowTypeMasterByCode(long allowTypeCode,long langId)  {
		HrPayAllowTypeMst objPayAllowTypeMst = new HrPayAllowTypeMst();
        Criteria objCrt = null;
        List  list = null;
        long elementCode;
        Session hibSession = getSession();            
        Query query = hibSession.createQuery("from HrPayAllowTypeMst hrPayAllowTypeMst where hrPayAllowTypeMst.allowCode="+allowTypeCode+" and hrPayAllowTypeMst.cmnLanguageMst.langId="+langId);
        objPayAllowTypeMst = (HrPayAllowTypeMst)query.uniqueResult();
        elementCode= objPayAllowTypeMst.getAllowTypeCode();
        langId=1;
        query = hibSession.createQuery("from HrPayAllowTypeMst hrPayAllowTypeMst where hrPayAllowTypeMst.allowTypeCode="+elementCode+" and hrPayAllowTypeMst.cmnLanguageMst.langId="+langId);
        objPayAllowTypeMst= (HrPayAllowTypeMst)query.uniqueResult();       
        return objPayAllowTypeMst;
    }
	public HrPayAllowTypeMst getAllowTypeMasterData(long allowCode,long langId){
		HrPayAllowTypeMst objPayAllowTypeMst = new HrPayAllowTypeMst();
        Criteria objCrt = null;
        List  list = null;
        long elementCode;
        Session hibSession = getSession();            
        Query query = hibSession.createQuery(" from HrPayAllowTypeMst hrPayAllowTypeMst where hrPayAllowTypeMst.allowCode="+allowCode);
        objPayAllowTypeMst = (HrPayAllowTypeMst)query.uniqueResult();
        elementCode= objPayAllowTypeMst.getAllowTypeCode();        
        query = hibSession.createQuery(" from HrPayAllowTypeMst hrPayAllowTypeMst where hrPayAllowTypeMst.allowTypeCode="+elementCode+" and hrPayAllowTypeMst.cmnLanguageMst.langId="+langId);
        objPayAllowTypeMst= (HrPayAllowTypeMst)query.uniqueResult();       
        return objPayAllowTypeMst;
	}
	
	//added by ankit Bhatt for getting all the Allowances which have no Expressions
	public List getNonDefaultAllowances(){
        List  list = null;
            Session hibSession = getSession();            
            Query query = hibSession.createQuery("from HrPayAllowTypeMst hrPayAllowTypeMst where hrPayAllowTypeMst.allowCode not in(select e.hrPayAllowTypeMst.allowCode from HrPayExpressionMst as e)");
            list = query.list();
            logger.info("Query for getting non-default Allowances");
        return list;
    }
	
}
	
	

