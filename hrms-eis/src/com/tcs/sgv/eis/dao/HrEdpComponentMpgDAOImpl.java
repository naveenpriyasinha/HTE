package com.tcs.sgv.eis.dao;

import java.util.HashMap;
import java.util.List;

//import net.sf.hibernate.collection.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;

public class HrEdpComponentMpgDAOImpl extends GenericDaoHibernateImpl<HrPayEdpCompoMpg, Long> implements HrEdpComponentMpgDAO{

	public HrEdpComponentMpgDAOImpl(Class<HrPayEdpCompoMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public List<HrPayEdpCompoMpg> getAllData()
    {
        Criteria objCrt = null;
        List resList =null;
        try
        {
            Session hibSession = getSession();
            String strQuery = "from HrPayEdpCompoMpg edp order by edp.displayOrder";
            Query query = hibSession.createQuery(strQuery);
            resList = query.list();
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return resList;
    }
	
	public HashMap getAllowEdpMap()
    {
        Criteria objCrt = null;
        List resList =null;
        HashMap allowMap = new HashMap();
        try
        {
            Session hibSession = getSession();
            String strQuery = "	SELECT mpg.TYPE_ID,rlt.EDP_CODE,rlt.EDP_SHORT_NAME FROM HR_PAY_EDP_COMPO_MPG mpg,RLT_BILL_TYPE_EDP rlt"+
            		" where rlt.TYPE_EDP_ID = mpg.TYPE_EDP_ID  and rlt.SUBJECT_ID = 3 and  mpg.CMN_LOOKUP_ID = 2500134 and rlt.edp_code is not null";
            Query query = hibSession.createSQLQuery(strQuery);
            resList = query.list();
            if(!resList.isEmpty())
            {
            	for(int i=0;i<resList.size();i++)
            	{
            		Object[] arr= (Object[])resList.get(i);
            		allowMap.put(arr[0].toString(), arr[1].toString()+","+arr[2].toString());
            	}
            }
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return allowMap;
    }
	
	public HashMap getDeducEdpMap()
    {
        Criteria objCrt = null;
        List resList =null;
        HashMap allowMap = new HashMap();
        try
        {
            Session hibSession = getSession();
            String strQuery = "	SELECT mpg.TYPE_ID,rlt.EDP_CODE,rlt.EDP_SHORT_NAME FROM HR_PAY_EDP_COMPO_MPG mpg,RLT_BILL_TYPE_EDP rlt"+
            		" where rlt.TYPE_EDP_ID = mpg.TYPE_EDP_ID  and rlt.SUBJECT_ID = 3 and  mpg.CMN_LOOKUP_ID = 2500135 and rlt.edp_code is not null";
            Query query = hibSession.createSQLQuery(strQuery);
            resList = query.list();
            if(!resList.isEmpty())
            {
            	for(int i=0;i<resList.size();i++)
            	{
            		Object[] arr= (Object[])resList.get(i);
            		allowMap.put(arr[0].toString(), arr[1].toString()+","+arr[2].toString());
            	}
            }
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return allowMap;
    }
	public HashMap getLoanAdvEdpMap()
    {
        Criteria objCrt = null;
        List resList =null;
        HashMap allowMap = new HashMap();
        try
        {
            Session hibSession = getSession();
            String strQuery = "	SELECT mpg.TYPE_ID,rlt.EDP_CODE,rlt.EDP_SHORT_NAME FROM HR_PAY_EDP_COMPO_MPG mpg,RLT_BILL_TYPE_EDP rlt"+
            		" where rlt.TYPE_EDP_ID = mpg.TYPE_EDP_ID  and rlt.SUBJECT_ID = 3 and  mpg.CMN_LOOKUP_ID in (2500136,2500137) and rlt.edp_code is not null";
            Query query = hibSession.createSQLQuery(strQuery);
            resList = query.list();
            if(!resList.isEmpty())
            {
            	for(int i=0;i<resList.size();i++)
            	{
            		Object[] arr= (Object[])resList.get(i);
            		allowMap.put(arr[0].toString(), arr[1].toString()+","+arr[2].toString());
            	}
            }
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return allowMap;
    }
	
	public List<HrPayEdpCompoMpg> getAllDataForOuter()
    {
        Criteria objCrt = null;
        List resList =null;
        try
        {
            Session hibSession = getSession();
            String strQuery = "from HrPayEdpCompoMpg edp order by edp.typeId ";
            Query query = hibSession.createQuery(strQuery);
            resList = query.list();
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return resList;
    }

	public List<HrPayEdpCompoMpg> getDataByEDP(String edpCode)
 	{
 		List<HrPayEdpCompoMpg> loanList = null;
 		
 		Session hibSession = getSession();
 		String query1 = "from HrPayEdpCompoMpg as m where m.rltBillTypeEdp.edpCode='" + edpCode + "'"; 
 		logger.info("Query for EDP Code is " + query1);
 		Query loanListQuery = hibSession.createQuery(query1);
 	    loanList = loanListQuery.list(); 	    
 	    return loanList;
 		
 	}	
	
	public List<HrPayEdpCompoMpg> getDataByEDP(long compoCodeId,long cmnLookupId)
 	{
 		List<HrPayEdpCompoMpg> loanList = null;
 		
 		Session hibSession = getSession();
 		String query1 = "from HrPayEdpCompoMpg as m where m.typeId=" + compoCodeId + " and m.cmnLookupId = "+cmnLookupId; 
 		logger.info("Query for EDP Code is " + query1);
 		Query loanListQuery = hibSession.createQuery(query1);
 	    loanList = loanListQuery.list(); 	    
 	    return loanList;
 		
 	}
	

	public List<HrPayEdpCompoMpg> getDataByEDP(String compoCodeId,String cmnLookupId)
	 	{
	 		List<HrPayEdpCompoMpg> loanList = null;
	 		 
	 		Session hibSession = getSession();
	 		String query1 = "from HrPayEdpCompoMpg as m where m.typeId in(" + compoCodeId + ") and m.cmnLookupId in ("+cmnLookupId+")"; 
	 		logger.info("Query for EDP Code is " + query1);
	 		Query loanListQuery = hibSession.createQuery(query1);
	 	    loanList = loanListQuery.list(); 	    
	 	    return loanList;
	 		
	 	}
	
	/**
	 * This method will return list of HrPayEdpCompoMpg
	 * objects based on given lookup Id. 
	 * @param cmnLookupId
	 * @return
	 */
	public List<HrPayEdpCompoMpg> getDataByLookup(long cmnLookupId)
 	{
 		List<HrPayEdpCompoMpg> loanList = null;
 		
 		Session hibSession = getSession();
 		String query1 = "from HrPayEdpCompoMpg as m where m.cmnLookupId = "+cmnLookupId; 
 		logger.info("Query for HrPayEdpCompoMpg based on lookup id is " + query1.toString());
 		Query loanListQuery = hibSession.createQuery(query1);
 	    loanList = loanListQuery.list(); 	    
 	    return loanList; 		
 	}
	
	/**
	 * This method will return list of HrPayEdpCompoMpg
	 * objects based on given lookup Id. 
	 * @param cmnLookupId
	 * @return
	 */
	public List<HrPayEdpCompoMpg> getDataByLookupIDs(String cmnLookupId)
 	{
 		List<HrPayEdpCompoMpg> loanList = null;
 		
 		Session hibSession = getSession();
 		String query1 = "from HrPayEdpCompoMpg as m where m.cmnLookupId in( "+cmnLookupId + ")"; 
 		 
 		logger.info("Query for HrPayEdpCompoMpg based on lookup id is " + query1.toString());
 		Query loanListQuery = hibSession.createQuery(query1);
 	    loanList = loanListQuery.list(); 	    
 	    return loanList; 		
 	}
	
	//added by manish
	public List<HrPayEdpCompoMpg> getAllData(long locId)
    {
        Criteria objCrt = null;
        List resList =null;
        try
        {
            Session hibSession = getSession();
            String strQuery = "mpg from HrPayEdpCompoMpg as  mpg ,HrPayLocComMpg as locmpg where mpg.typeId = locmpg.compId ";
            strQuery+="and mpg.cmnLookupId=locmpg.cmnLookupMst and locmpg.isactive=1 and locmpg.cmnLocationMst="+locId;
            Query query = hibSession.createQuery(strQuery);
            resList = query.list();
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return resList;
    }
	
	//ended by manish
	public HrPayEdpCompoMpg getDataByTypeEdpId(long typeEdpId)
 	{
		HrPayEdpCompoMpg hrPayEdpCmpo = null;
 		Session hibSession = getSession();
 		String query1 = "from HrPayEdpCompoMpg as m where m.rltBillTypeEdp.typeEdpId="+ typeEdpId; 
 		logger.info("Query for getDataByTypeEdpId  " + query1);
 		Query loanListQuery = hibSession.createQuery(query1);
 		hrPayEdpCmpo =(HrPayEdpCompoMpg)loanListQuery.uniqueResult(); 	    
 	    return hrPayEdpCmpo;
 		
 	}
	public List<HrPayEdpCompoMpg> getAllDataLoanAndAdvcances()
    {
        Criteria objCrt = null;
        List resList =null;
        try
        {
            Session hibSession = getSession();
            String strQuery = "from HrPayEdpCompoMpg edp  where edp.cmnLookupId in (2500136,2500137) order by edp.displayOrder";
            Query query = hibSession.createQuery(strQuery);
            resList = query.list();
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return resList;
    }
}
