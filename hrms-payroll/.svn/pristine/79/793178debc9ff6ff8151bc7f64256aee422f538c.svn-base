/**
 *  implements the DeducTypeMasterDAO
 * Made By:- Mrugesh Rajda.
 * Date:- 26-07-2007.
 */
package com.tcs.sgv.deduction.dao;
//Comment By Maruthi For import Organisation.

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;

public class DeducTypeMasterDAOImpl extends GenericDaoHibernateImpl<HrPayDeducTypeMst, Long> implements DeducTypeMasterDAO {
	
	/**
	 * This method will collect all the data of hr_pay_allow_type_mst.
	 * @param HrPayAllowTypeMst
	 * @param sessionFactory
	 * @return an object of List.
	 */
	Log logger = LogFactory.getLog( getClass() );
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

	
 	public DeducTypeMasterDAOImpl(Class<HrPayDeducTypeMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getDeducTypeMasterData(long langId) {
		
        Criteria objCrt = null;
        //List  list = null;        
            Session hibSession = getSession();            
            //Query query = hibSession.createQuery(" from HrPayDeducTypeMst as a where a.cmnLanguageMst.langId = "+langId+" order by a.deducName");            
            //list = query.list();
            objCrt=hibSession.createCriteria(HrPayDeducTypeMst.class);
            objCrt.addOrder(Order.asc("deducName"));
            
        return objCrt.list();
    }
	
	
	
	public HrPayDeducTypeMst getDeducTypeMasterDataByCode(long langid,long deducCode)  {
		HrPayDeducTypeMst objPayDeducTypeMst = new HrPayDeducTypeMst();
        Criteria objCrt = null;
        List  list = null;        
            Session hibSession = getSession();            
            Query query = hibSession.createQuery("from HrPayDeducTypeMst p where p.cmnLanguageMst.langId = " + langid + " and p.deducCode=" +deducCode);
            objPayDeducTypeMst = (HrPayDeducTypeMst)query.uniqueResult();        
        return objPayDeducTypeMst;
    }
	
	public List checkDeducNameAvailability(String newDeducName) {
		List  list = null;        
        Session hibSession = getSession();   
        
        Query query = hibSession.createQuery("from HrPayDeducTypeMst as a where lower(a.deducName)= lower('"+newDeducName+"')");        
        list = query.list();
        //System.out.println("The Size od list is:-"+list.size());
    return list;
	}
	
	public HrPayDeducTypeMst getDeducTypeCode(long langid,long deducTypeCode)  {
		HrPayDeducTypeMst objPayDeducTypeMst = new HrPayDeducTypeMst();
        Criteria objCrt = null;
        List  list = null;        
            Session hibSession = getSession();            
            Query query = hibSession.createQuery("from HrPayDeducTypeMst p where p.cmnLanguageMst.langId= " + langid + " and p.deducTypeCode=" +deducTypeCode);
            objPayDeducTypeMst = (HrPayDeducTypeMst)query.uniqueResult();        
        return objPayDeducTypeMst;
    }
	
	public List getDeductionTypes(long langId){
		
		String deductionTypeIds = resourceBundle.getString("DEDUCTION_TYPE_IDS");
		List  listDeducTypes = null;
		Session hibSession = getSession();        
        Query query = hibSession.createQuery("from CmnLookupMst as cmnLookupMst where cmnLookupMst.parentLookupId="+deductionTypeIds+" ");        
        listDeducTypes = query.list();
        logger.info("The Size od list is:-"+listDeducTypes.size());
        return listDeducTypes;
	}
	
	// This Method will Fetch Deduction Types for Expression Master.
	
	public List getDeductionTypeWithAllChilderns(Long deducType,long langId){
		List  deducTypesLst = null;
		List exprDeducTypeList = null;
		String queryString;
		Session hibSession = getSession();		
		queryString = "select hrDeducCode.deducTypeCode from HrPayDeducTypeMst hrDeducCode where hrDeducCode.deducCode in (select hrDeducExpMst.hrPayDeducTypeMst.deducCode from HrDeducExpMst hrDeducExpMst where hrDeducExpMst.cmnLanguageMst.langId = "+langId+")";		
		Query queryDeducType = hibSession.createQuery(queryString);
		exprDeducTypeList = queryDeducType.list();				
		logger.info("The DeducType Code in Expression Master"+exprDeducTypeList.size());
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(langId);		
		Criteria crtDeductType = hibSession.createCriteria(HrPayDeducTypeMst.class);
		crtDeductType.add(Restrictions.like("deducType",deducType));
		crtDeductType.add(Restrictions.not(Restrictions.in("deducTypeCode", exprDeducTypeList)));
		crtDeductType.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		deducTypesLst=crtDeductType.list();
		logger.info("The Size of Deduction Type List is:-"+deducTypesLst.size());
		
		return deducTypesLst;
	}
	
	
	
	
	public HrPayDeducTypeMst getDeducTypeMasterByCode(long elementCode,long langId)  {
		HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();                
        Session hibSession = getSession();        
        Query query = hibSession.createQuery("from HrPayDeducTypeMst hrPayDeducTypeMst where hrPayDeducTypeMst.deducTypeCode="+elementCode+" and hrPayDeducTypeMst.cmnLanguageMst.langId="+langId);
        hrPayDeducTypeMst= (HrPayDeducTypeMst)query.uniqueResult();
        logger.info("The Deduction Id :-"+elementCode);
        return hrPayDeducTypeMst;
    }
	
	public HrPayDeducTypeMst getDeducTypeMasterData(long deducCode,long langId){
		HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
        //Criteria objCrt = null;
        //List  list = null;
        long elementCode;
        Session hibSession = getSession();          
        Query query = hibSession.createQuery(" from HrPayDeducTypeMst hrPayDeducTypeMst where hrPayDeducTypeMst.deducCode="+deducCode);
        hrPayDeducTypeMst = (HrPayDeducTypeMst)query.uniqueResult();
        elementCode= hrPayDeducTypeMst.getDeducTypeCode();        
        query = hibSession.createQuery(" from HrPayDeducTypeMst hrPayDeducTypeMst where hrPayDeducTypeMst.deducTypeCode="+elementCode+" and hrPayDeducTypeMst.cmnLanguageMst.langId="+langId);
        hrPayDeducTypeMst= (HrPayDeducTypeMst)query.uniqueResult();       
        return hrPayDeducTypeMst;
	}
}
	
	

