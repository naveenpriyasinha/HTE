package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisCadreMst;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;

public class HrEisCadreMstDAOImpl extends GenericDaoHibernateImpl<HrEisCadreMst, Long> implements  HrEisCadreMstDAO 
{

	public HrEisCadreMstDAOImpl(Class<HrEisCadreMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public List getCadreList(long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrEisCadreMst cadre where cadre.cmnLanguageMst.langId ="  + langId;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("OrgSchemeMstDAOImpl queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	

	public long getGroupId(long cadreId)
    {
        Criteria objCrt = null;
        List  list = null;
        long GroupId = 0L;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select groupp.GROUP_ID  from HR_EIS_CADRE_GROUP_MPG groupp where groupp.CADRE_ID =" + cadreId );
            Query query = hibSession.createSQLQuery(strQuery.toString());
            logger.info("HrEisCadreMstDAOImpl getGroupId" + query.toString());
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            GroupId = Long.parseLong(list.get(0).toString());
        return GroupId;
    }
	
	// This is for to get groupcode according to cadreid
	public String getGroupCode(long GroupId)
    {
        Criteria objCrt = null;
        List  list = null;
        String GroupCode = "";
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select g.GROUP_CODE from HR_EIS_GROUP_MST g where GROUP_ID =" + GroupId );
            Query query = hibSession.createSQLQuery(strQuery.toString());
            logger.info("HrEisCadreMstDAOImpl getGroupCode" + query.toString());
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            GroupCode = list.get(0).toString();
        return GroupCode;
    }
	
	
	//This is for to get grade name from hr_eis_group_cadre_desig
	
	public String getGradeName(String GroupCode)
    {
        Criteria objCrt = null;
        List  list = null;
        String GradeName = "";
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select desig.CLASS_NAME FROM HR_EIS_GROUP_CADRE_DESIGN desig where desig.GROUP_CODE ='" + GroupCode + "' ");
            Query query = hibSession.createSQLQuery(strQuery.toString());
            logger.info("HrEisCadreMstDAOImpl getGroupCode" + query.toString());
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            GradeName = list.get(0).toString();
        return GradeName;
    }
	
	

	//This is for to get grade id from org_grade_mst
	
	public long getGradeId(String  GradeName)
    {
        Criteria objCrt = null;
        List  list = null;
        long GradeId =0L;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("SELECT GRADE_ID FROM ORG_GRADE_MST where GRADE_NAME ='" + GradeName +"' " );
            Query query = hibSession.createSQLQuery(strQuery.toString());
            logger.info("HrEisCadreMstDAOImpl getGroupCode" + query.toString());
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            GradeId = Long.parseLong(list.get(0).toString());
        return GradeId;
    }
	//This is for Current Office
	public long getBranchId(long loggedInpostId,long locationId)
    {
        Criteria objCrt = null;
        List  list = null;
        long BranchId = 0L;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select mpg.BRANCH_ID from ORG_POST_DETAILS_RLT mpg where mpg.POST_ID =" + loggedInpostId + " and mpg.LOC_ID=" + locationId );
            Query query = hibSession.createSQLQuery(strQuery.toString());
            logger.info("HrEisCadreMstDAOImpl getBranchId" + query.toString());
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            BranchId = Long.parseLong(list.get(0).toString());
        return BranchId;
    }
	
	public List getBranchName(long BranchId,long langId)
    {
        Criteria objCrt = null;
        List  list = null;
       String BranchName = "";
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
           // StringBuffer strQuery = new StringBuffer("select branch.BRANCH_NAME from CMN_BRANCH_MST branch where branch.BRANCH_ID =" + BranchId+ " and branch.LANG_ID= "+ langId );
            //StringBuffer strQuery = "from CmnBranchMst branch where branch.branchId =" + BranchId+ " and branch.cmnLanguageMst.langId= "+ langId ;
          //  StringBuffer strQuery = new StringBuffer("select * from CMN_BRANCH_MST branch where branch.BRANCH_ID =" + BranchId+ " and branch.LANG_ID= "+ langId );
           // Query query = hibSession.createQuery(strQuery.toString());
            
            
            String strQuery = "from CmnBranchMst branch where branch.branchId =" + BranchId+ " and branch.cmnLanguageMst.langId= "+ langId ;
            Query query = hibSession.createQuery(strQuery);
            
            logger.info("getBranchName getBranchName" + query.toString());
           // Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
           // logger.info("List size in DAO:-->" + list.size());
           // BranchName = list.get(0).toString();
        return list;
    }
	
	public List getCurrentCadreInParentDept(long loggedInpostId,long locationId,long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select cadre.CADRE_NAME from HR_EIS_CADRE_MST cadre where cadre.CREATED_BY_POST=" + loggedInpostId+ " and cadre.LOC_ID= "+ locationId + " and cadre.LANG_ID=" + langId);
            Query query = hibSession.createSQLQuery(strQuery.toString());
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("getCurrentCadreInParentDept queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	
	public List getGisApplicable()
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("SELECT mst.LOOKUP_NAME FROM CMN_LOOKUP_MST mst WHERE mst.LOOKUP_ID IN (100113,100112) ");
            Query query = hibSession.createSQLQuery(strQuery.toString());
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("getGisApplicable queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public List getCityClass(long CityId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select city.CATEGORY_ID from CMN_CITY_CATEGORY_MST city where city.CITY_ID = "+ CityId );
            
            //String strQuery = "from CmnCityCategoryMst city where branch.branchId =" + BranchId+ " and branch.cmnLanguageMst.langId= "+ langId ;
            Query query = hibSession.createSQLQuery(strQuery.toString());
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("getCityClass queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public long getCurrentCadre(long loggedInpostId,long locationId)
    {
        Criteria objCrt = null;
        List  list = null;
        long userId =0L;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select post.USER_ID from ORG_USERPOST_RLT post where post.POST_ID in (select rlt.POST_ID from ORG_POST_DETAILS_RLT rlt where rlt.POST_ID="+loggedInpostId+" and rlt.LOC_ID=" + locationId+" "+") ");
            Query query = hibSession.createSQLQuery(strQuery.toString());
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("getCityClass queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            userId = Long.parseLong(list.get(0).toString());
            return userId;
            
      
    }
	//Ended
	public List getCurrentCadreFromUserId(long userId)
    {
        Criteria objCrt = null;
        List  list = null;
       
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            StringBuffer strQuery = new StringBuffer("select * from HR_EIS_EMP_MST empmst where empmst.EMP_MPG_ID in (select emp.emp_id from ORG_EMP_MST emp where emp.USER_ID="+ userId +" "+") ");
            Query query = hibSession.createSQLQuery(strQuery.toString());
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("getCurrentCadreFromUserId queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            return list;
            
      
    }
	
	public List getCadreName(long cadreId)
    {
        Criteria objCrt = null;
        List  list = null;
       
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
           // StringBuffer strQuery = new StringBuffer("select cadre.CADRE_NAME from HR_EIS_CADRE_MST cadre where cadre.CADRE_ID="+ cadreId );
           // StringBuffer strQuery = "from  HrEisCadreMst cadre where cadre.cadreId= "+ cadreId ;
            
            String strQuery = "from  HrEisCadreMst cadre where cadre.cadreId= "+ cadreId ;
            Query query = hibSession.createQuery(strQuery);
            
           // Query query = hibSession.createSQLQuery(strQuery.toString());
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("getCadreName queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            return list;
            
      
    }
}
