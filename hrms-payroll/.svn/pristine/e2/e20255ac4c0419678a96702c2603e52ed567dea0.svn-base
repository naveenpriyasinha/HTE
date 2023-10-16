package com.tcs.sgv.deduction.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;


public class DeducExpMasterDAOImpl extends GenericDaoHibernateImpl<HrDeducExpMst, Long> implements DeducExpMasterDAO {
	
	/**
	 * This method will collect all the data of hr_pay_allow_type_mst.
	 * @param HrPayDeducTypeMst
	 * @param sessionFactory
	 * @return an object of List.
	 */
	
 	public DeducExpMasterDAOImpl(Class<HrDeducExpMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
 	
 	long active = Long.parseLong(resourceBundle.getString("active"));
	long inactive = Long.parseLong(resourceBundle.getString("inactive"));
 	long englangId = Long.parseLong(resourceBundle.getString("englangId"));
 	
public List getDeducExpData() {
		
        Criteria objCrt = null;
        List  list = new ArrayList();        
            Session hibSession = getSession();        
            logger.info("Y r in dao");
            Query query = hibSession.createQuery(" from HrDeducExpMst where deducExpIsActive = "+active);//0 means active and 1 means inactive
            logger.info("after query");
            list = query.list();       
            logger.info("Y r in dao"+list.size());
        return list;
    }

public List getDeducExpData(long payCommonissionId) {
	
    Criteria objCrt = null;
    List  list = new ArrayList();        
        Session hibSession = getSession();        
        logger.info("Y r in dao");
        Query query = hibSession.createQuery(" from HrDeducExpMst where deducExpIsActive = "+active+" and hrPayCommissionMst.id = "+payCommonissionId);//0 means active and 1 means inactive
        logger.info("after query");
        list = query.list();       
        logger.info("Y r in dao"+list.size());
    return list;
}

	public List getDeducExpMasterData(long langId) {
		
        Criteria objCrt = null;
        List  list = new ArrayList();        
            Session hibSession = getSession();        
            logger.info("Y r in dao");
            //Query query = hibSession.createQuery(" from HrDeducExpMst hrDeducExpMst where hrDeducExpMst.cmnLanguageMst.langId = "+langId+" order by hrDeducExpMst.deducRuleDesc");
            Query query = hibSession.createQuery(" from HrDeducExpMst hrDeducExpMst where hrDeducExpMst.cmnLanguageMst.langId = "+langId+" order by hrDeducExpMst.hrPayDeducTypeMst.sequence_no");
            logger.info("after query");
            list = query.list();       
            logger.info("Y r in dao"+list.size());
        return list;
    }
	
	
	
public List getDeducExpMasterData(long langId,long commId) {
		
        Criteria objCrt = null;
        List  list = new ArrayList();        
            Session hibSession = getSession();        
            logger.info("Y r in dao");
            //Query query = hibSession.createQuery(" from HrDeducExpMst hrDeducExpMst where hrDeducExpMst.cmnLanguageMst.langId = "+langId+" order by hrDeducExpMst.deducRuleDesc");
            Query query = hibSession.createQuery(" from HrDeducExpMst hrDeducExpMst where hrDeducExpMst.cmnLanguageMst.langId = "+langId+" and hrDeducExpMst.hrPayCommissionMst.id= "+commId+" order by hrDeducExpMst.hrPayDeducTypeMst.sequence_no");
            logger.info("after query");
            list = query.list();       
            logger.info("Y r in dao"+list.size());
        return list;
    }
	
	
	/**
	 * This method will collect all the data of hr_pay_Deduc_type_mst for perticular allowance code.
	 * @param HrPayAllowTypeMst
	 * @param sessionFactory
	 * @return HrPayDeducTypeMst
	 */
	
	public HrDeducExpMst getDeducExpMasterByCode(long deducRuleId)  {
		HrDeducExpMst objPayAllowTypeMst = new HrDeducExpMst();
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();            
            Query query = hibSession.createQuery("from HrDeducExpMst as deductionExpMst where deductionExpMst.deducRuleId="+deducRuleId);
            objPayAllowTypeMst = (HrDeducExpMst)query.uniqueResult();
        
        return objPayAllowTypeMst;
    }
	
	public List checkDeducRule(String newDeducName,long langId) {
		List  list = null;        
        Session hibSession = getSession();   
        
        Query query = hibSession.createQuery("from HrDeducExpMst as a where hrPayDeducTypeMst.cmnLanguageMst.langId = "+langId+" and lower(a.deducRuleDesc)= '"+newDeducName+"'");        
        list = query.list();
        logger.info("The Size od list is:-"+list.size());
    return list;
	}
	
	public List getAllDeductionNames(long langId) {		
        List  list = null;    
        Session hibSession = getSession();
        logger.info("going to execute query....");            
        String strQuery = "from HrPayDeducTypeMst hrPayDeducTypeMst where hrPayDeducTypeMst.cmnLanguageMst.langId = "+langId+" and hrPayDeducTypeMst.deducCode not in (select hrDeducExpMst.hrPayDeducTypeMst.deducCode from HrDeducExpMst hrDeducExpMst)";
        Query query = hibSession.createQuery(strQuery);            
        list = query.list();
        logger.info("List size in DAO:-->" + list.size());    
        return list;
	}
	
	public List getExpfromDeducCodes(String deducIds)//get Allowcode of HrPayExpressionMst
    {
 		List list =new ArrayList();
        try
        {
            Session hibSession = getSession();
            deducIds=deducIds.substring(1);
            Query query= hibSession.createQuery(" from HrDeducExpMst exp where exp.cmnLanguageMst.langId="+englangId+" and exp.deducExpIsActive="+active+" and exp.hrPayDeducTypeMst.deducCode in ("+deducIds+") ");
            list=query.list();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	
	public List getExpfromDeducCodesNew(String deducIds)//get Allowcode of HrPayExpressionMst
    {
 		List list =new ArrayList();
        try
        {
            Session hibSession = getSession();
            
            Query query= hibSession.createQuery(" from HrDeducExpMst exp where exp.cmnLanguageMst.langId="+englangId+" and exp.deducExpIsActive="+active+" and exp.hrPayDeducTypeMst.deducCode in ("+deducIds+") ");
            list=query.list();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	
	
	public List getExpfromDeducCodes(String deducIds,long payCommonissionId)//get Allowcode of HrPayExpressionMst
    {
 		List list =new ArrayList();
        try
        {
            Session hibSession = getSession();
            deducIds=deducIds.substring(1);
            Query query= hibSession.createQuery(" from HrDeducExpMst exp where exp.cmnLanguageMst.langId="+englangId+" and exp.deducExpIsActive="+active+" and hrPayCommissionMst.id = "+payCommonissionId+" and exp.hrPayDeducTypeMst.deducCode in ("+deducIds+") ");
            list=query.list();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	
}
