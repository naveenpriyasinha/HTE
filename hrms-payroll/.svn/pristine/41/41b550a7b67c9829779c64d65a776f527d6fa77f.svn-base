package com.tcs.sgv.allowance.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.allowance.valueobject.HrPayExpressionMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;


public class ExpressionMasterDAOImpl extends GenericDaoHibernateImpl<HrPayExpressionMst, Long> implements ExpressionMasterDAO {
	
 	public ExpressionMasterDAOImpl(Class<HrPayExpressionMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
 	
 	long active = Long.parseLong(resourceBundle.getString("active"));
	long inactive = Long.parseLong(resourceBundle.getString("inactive"));
 	long englangId = Long.parseLong(resourceBundle.getString("englangId"));
	public List getAllExprMasterData(long langId)
    {
        Criteria objCrt = null;
        List  list = null;
        
            Session hibSession = getSession();
            logger.info("going to execute query....");
            
            //String strQuery = "from HrPayExpressionMst as hrPayExpressionMst where hrPayExpressionMst.cmnLanguageMst.langId="+langId+" order by hrPayExpressionMst.ruleDescription";
            String strQuery = "from HrPayExpressionMst as hrPayExpressionMst where hrPayExpressionMst.cmnLanguageMst.langId="+langId+" order by hrPayExpressionMst.hrPayAllowTypeMst.sequence_no";
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size of Expr in DAO:-->" + list.size());
        
        return list;
    }
	
	public List getAllExprMasterData(long langId,long payCommonissionId)
    {
        Criteria objCrt = null;
        List  list = null;
        
        //System.out.println("===> For checking payCommonissionId :: "+payCommonissionId);
		
		if(payCommonissionId==2500340 || payCommonissionId==2500346)
		{
			payCommonissionId = 2500340;
		//Javed	
		}
		else
		{
			payCommonissionId = 2500341;
		}
		//System.out.println("===> After For checking payCommonissionId :: "+payCommonissionId);
		
            Session hibSession = getSession();
            logger.info("going to execute query....");
            
            //String strQuery = "from HrPayExpressionMst as hrPayExpressionMst where hrPayExpressionMst.cmnLanguageMst.langId="+langId+" order by hrPayExpressionMst.ruleDescription";
            String strQuery = "from HrPayExpressionMst as hrPayExpressionMst where hrPayExpressionMst.cmnLanguageMst.langId="+langId+" and hrPayCommissionMst.id = "+payCommonissionId+" order by hrPayExpressionMst.hrPayAllowTypeMst.sequence_no";
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size of Expr in DAO:-->" + list.size());
        
        return list;
    }
 	
 	public List getExprData()
    {
        Criteria objCrt = null;
        List  list = null;
        
            Session hibSession = getSession();
            logger.info("going to execute query....");
            
            String strQuery = "from HrPayExpressionMst where isactive = "+active; //0 means active and 1 means inactive
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size of Expr in DAO:-->" + list.size());
        
        return list;
    }
 	
 	public List getExprData(long commonissionId)
    {
        Criteria objCrt = null;
        List  list = null;
        
            Session hibSession = getSession();
            logger.info("going to execute query....");
            
            String strQuery = "from HrPayExpressionMst where isactive = "+active+" and hrPayCommissionMst.id = "+commonissionId; //0 means active and 1 means inactive
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size of Expr in DAO:-->" + list.size());
        
        return list;
    }
 	
	public List checkRuleDesc(String ruleDesc,long langId)
	{
       List ruleDescList=null;
        
        Session hibSession = getSession();
        String query1 = "from HrPayExpressionMst as ruleLookup where lower(ruleLookup.ruleDescription) = '"
                + ruleDesc + "' and cmnLanguageMst.langId="+langId;
        
        Query sqlQuery1 = hibSession.createQuery(query1);
        
        //sqlQuery1.setParameter("param",langId);
        ruleDescList = sqlQuery1.list();
        logger.info("Rule List size is:::::::::" );
    
    return ruleDescList;
	}
	
	public HrPayExpressionMst getRuleIdData(long RuleId)
    {
		HrPayExpressionMst hrExprInfo = new HrPayExpressionMst();
        
            Session hibSession = getSession();
            String query1 = "from HrPayExpressionMst as exprLookup where exprLookup.ruleId = "
                    + RuleId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrExprInfo = (HrPayExpressionMst)sqlQuery1.uniqueResult();
            logger.info("Expr size is:::::::::" );

        
        return hrExprInfo;
    }	
	
	public List getAllAllowanceNames(long langId)
	{
		
        List  list = null;    
        Session hibSession = getSession();
        logger.info("going to execute query....");            
        String strQuery = "from HrPayAllowTypeMst hrPayAllowTypeMst where hrPayAllowTypeMst.cmnLanguageMst.langId ="  + langId+" and hrPayAllowTypeMst.allowCode not in(select hrPayExpressionMst.hrPayAllowTypeMst.allowCode from HrPayExpressionMst hrPayExpressionMst)";
        Query query = hibSession.createQuery(strQuery);
        //Query query = hibSession.createQuery("from HrEisBranchMst");
        list = query.list();
        logger.info("List size in DAO:-->" + list.size());
    
        return list;
	}
	public List getAllAllowances(long langId)
	{	
        List  list = null;    
        Session hibSession = getSession();
        logger.info("going to execute query....");            
        String strQuery = "from HrPayAllowTypeMst hrPayAllowTypeMst where hrPayAllowTypeMst.cmnLanguageMst.langId ="  + langId;
        Query query = hibSession.createQuery(strQuery);
        //Query query = hibSession.createQuery("from HrEisBranchMst");
        list = query.list();
        logger.info("List size in DAO:-->" + list.size());
    
        return list;
	}
 	public List getAllallowCodeData(List AllowcodeList) //Get allowance not mapped in expressions
    {
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrPayAllowTypeMst.class);
            objCrt.add(Restrictions.not(Restrictions.in("allowCode", AllowcodeList)));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return objCrt.list();
    }
 	
	public List getAllallowCode()//get Allowcode of HrPayExpressionMst
    {
 		List list =new ArrayList();
        try
        {
            Session hibSession = getSession();
            Query query= hibSession.createQuery("select hrPayAllowTypeMst.allowCode from HrPayExpressionMst");
            list=query.list();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	public List getAllComponentNames(long langId)
	{
		List  list = null;
    
            Session hibSession = getSession();
            logger.info("going to execute query....");
            
            String strQuery = "from HrPayComponentMst where cmnLanguageMst.langId ="  + langId;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
    
    
        return list;
	}
	
	public List getExpfromAllowCodes(String allowIds)//get Allowcode of HrPayExpressionMst
    {
 		List list =new ArrayList();
        try
        {
            Session hibSession = getSession();
            allowIds=allowIds.substring(1);
            Query query= hibSession.createQuery(" from HrPayExpressionMst exp where exp.cmnLanguageMst.langId= "+englangId+" and exp.isactive= "+active+" and exp.hrPayAllowTypeMst.allowCode in ("+allowIds+") ");
            list=query.list();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	
	public List getExpfromAllowCodes(String allowIds, long payCommonissionId)//get Allowcode of HrPayExpressionMst
    {
 		List list =new ArrayList();
        try
        {
            Session hibSession = getSession();
            allowIds=allowIds.substring(1);
            Query query= hibSession.createQuery(" from HrPayExpressionMst exp where exp.cmnLanguageMst.langId= "+englangId+" and hrPayCommissionMst.id = "+payCommonissionId+" and exp.isactive= "+active+" and exp.hrPayAllowTypeMst.allowCode in ("+allowIds+") ");
            list=query.list();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	
}
