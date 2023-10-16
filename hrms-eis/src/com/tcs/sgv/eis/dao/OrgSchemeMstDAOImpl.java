package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;

public class OrgSchemeMstDAOImpl extends GenericDaoHibernateImpl<OrgSchemeMstVO, Long> implements  OrgSchemeMstDAO
{

	public OrgSchemeMstDAOImpl(Class<OrgSchemeMstVO> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	// for scheme
	public List getLocIds(long postId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from OrgPostDetailsRlt rlt where rlt.orgPostMst.postId ="  + postId;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("OrgSchemeMstDAOImpl queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public List getBudSubHdId(String subHead,int finYrId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrPayOrderSubHeadMpg head where head.element_code ="  + subHead + "  and head.finYearId=" + finYrId ;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("OrgSchemeMstDAOImpl queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	

	public List getDDoId(long postId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from OrgDdoMst ddo where ddo.postId =" + postId ;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("OrgSchemeMstDAOImpl getDDoId queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	public List getSchemeIds(String budSubHeadId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from OrgSchemeMstVO scheme where scheme.sgvaBudsubhdMst.budsubhdId =" + budSubHeadId ;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("OrgSchemeMstDAOImpl getSchemeIds queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	
	public List getIdsFromHrPayDdoSchemeMpg(long SchemeId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrPayDDOSchemeMpgVO pay where pay.orgSchemeMstVO.schemeId =" + SchemeId ;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("OrgSchemeMstDAOImpl getSchemeIds queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public List getAllData(long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from OrgSchemeMstVO scheme where scheme.cmnLanguageMst.langId ="  + langId;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	/*public List getAllSchemeTypeList(String SchmeType)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from CmnLookupMst cmn where cmn. ="  + SchmeType;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }*/
	
	
	
	public List getSchemeList(long BudSubHeadId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select scheme.SCHEME_CODE,scheme.SCHEME_NAME,sgva.BUDSUBHD_DESC_LONG,scheme.IS_SCHEME_TYPE,scheme.IS_PLAN from ORG_SCHEME_MST scheme ,SGVA_BUDSUBHD_MST sgva where scheme.BUDSUBHD_ID in (select sgva.BUDSUBHD_ID FROM SGVA_BUDSUBHD_MST sgva where sgva.BUDSUBHD_ID= " + BudSubHeadId + " "+") and sgva.BUDSUBHD_ID=" + BudSubHeadId  +" ");
            Query query = hibSession.createSQLQuery(strQuery.toString());
            
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public List getSchemeAndBudList(long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select scheme.SCHEME_CODE, scheme.SCHEME_NAME ,sgva.BUDSUBHD_DESC_LONG,scheme.BUDSUBHD_ID from ORG_SCHEME_MST scheme,SGVA_BUDSUBHD_MST sgva where scheme.BUDSUBHD_ID=sgva.BUDSUBHD_ID and scheme.LANG_ID =  " + langId );
            Query query = hibSession.createSQLQuery(strQuery.toString());
            
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	
	public List getListFromBudSubHdId(String budsubhdId,long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select * from ORG_SCHEME_MST org where org.BUDSUBHD_ID =  " + budsubhdId + " and org.LANG_ID =  " + langId );
            Query query = hibSession.createSQLQuery(strQuery.toString());
            
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public List getLookupIdForCharge(long schemetype)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from CmnLookupMst cmn where cmn.lookupId ="  + schemetype;
            Query query = hibSession.createQuery(strQuery);
            
           logger.info("queryqueryqueryquery" + query);
            list = query.list();
            
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public List getLookupIdForPlan(long plantype)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from CmnLookupMst cmn where cmn.lookupId ="  + plantype;
            Query query = hibSession.createQuery(strQuery);
            
            logger.info("queryqueryqueryquery->" + query);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	/*public List getSchemeList(long[] BudSubHeadId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select scheme.SCHEME_CODE,scheme.SCHEME_NAME,sgva.BUDSUBHD_DESC_LONG from ORG_SCHEME_MST scheme ,SGVA_BUDSUBHD_MST sgva where scheme.BUDSUBHD_ID in (select sgva.BUDSUBHD_ID FROM SGVA_BUDSUBHD_MST sgva where sgva.BUDSUBHD_ID in ( "); 
            		for (int i = 0; i < BudSubHeadId.length-1; i = i++)
            		{ 
            			strQuery.append ( BudSubHeadId[i]).append(",") ;
            		}
            		strQuery.append ( BudSubHeadId[i]);
            		strQuery.append("and sgva.BUDSUBHD_ID=" + BudSubHeadId  +" ");
            Query query = hibSession.createSQLQuery(strQuery.toString());
            
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }*/
	/*public List getSchemeList(long BudSubHeadId) // function added by Ankit Bhatt, used in
	// GeneratePayslipService.
	{
		 List  list = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select scheme.schemeCode,scheme.schemeName,sgva.budsubhdDescLong from OrgSchemeMstVO as scheme,SgvaBudsubhdMst as sgva where scheme.budsubhdId in (select sgva.budsubhdId from SgvaBudsubhdMst as sgva where sgva.budsubhdId = " +BudSubHeadId + " ') and sgva.budsubhdId = " + BudSubHeadId  +" ");
		
		Query query = hibSession.createQuery(strQuery.toString());
		 list = query.list();
         logger.info("List size in DAO:-->" + list.size());
     return list;
	}*/
	//end for scheme
	
	public List getSchemeCodeList(long locationId,long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
          //  StringBuffer strQuery = new StringBuffer("select * from ORG_SCHEME_MST org where org.LOC_ID=" + locationId + " and org.LANG_ID= " + langId + "  order by org.SCHEME_CODE ");
            String strQuery ="from OrgSchemeMstVO org where org.cmnLocationMst.locId=" + locationId + " and org.cmnLanguageMst.langId= " + langId ;
            Query query = hibSession.createQuery(strQuery);
            
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
}
