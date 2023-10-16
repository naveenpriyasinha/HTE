package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class EmployeeTypeDAOImpl extends GenericDaoHibernateImpl<CmnLookupMst, Long> implements  EmployeeTypeDAO
{
	public EmployeeTypeDAOImpl(Class<CmnLookupMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }

	// added by ABHILASH FOR MULTIPLE NEW EMPLOYEE CONFIGURATION
	public List getAllEmployyTypeData(String EmployeeType)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
           String strQuery = "from CmnLookupMst cmnLookupMst where cmnLookupMst.lookupName = '"  +EmployeeType +  "' and cmnLookupMst.cmnLanguageMst.langId = " + 1;
           // String strQuery="select cmnLookupMst.lookupId from CmnLookupMst cmnLookupMst where cmnLookupMst.lookupName='EmployeeType' and cmnLookupMst.cmnLanguageMst.langId=1";
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List getAllEmployyTypeData size in DAO:-->" + list.size());
        return list;
    }
	
	public List getVacantPostList(long locId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
           //String strQuery = "select * from Org_Post_Details_Rlt  rlt left outer join Org_Userpost_Rlt rt on rlt.post_Id=rt.post_Id where rlt.loc_Id="+locId+" and (rt.post_Id is null or rt.activate_Flag="+0+") ";
           String strQuery = "select * from Org_Post_Details_Rlt  rlt left outer join Org_Userpost_Rlt rt on rlt.post_Id=rt.post_Id where rlt.loc_Id="+locId+" and rt.activate_Flag="+1+" ";
           // String strQuery="select cmnLookupMst.lookupId from CmnLookupMst cmnLookupMst where cmnLookupMst.lookupName='EmployeeType' and cmnLookupMst.cmnLanguageMst.langId=1";
            Query query = hibSession.createSQLQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List getAllEmployyTypeData size in DAO:-->" + list.size());
        return list;
    }
	
	public List getPsrNoList(long locId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrPayGpfBalanceDtls hrPayGpfBalanceDtls where hrPayGpfBalanceDtls.cmnLocationMst.locId = "+locId+" ";
           // String strQuery="select cmnLookupMst.lookupId from CmnLookupMst cmnLookupMst where cmnLookupMst.lookupName='EmployeeType' and cmnLookupMst.cmnLanguageMst.langId=1";
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List getAllEmployyTypeData size in DAO:-->" + list.size());
        return list;
    }
	
	public List getBankAccountNoList()
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrEisBankDtls hrEisBankDtls  ";
           // String strQuery="select cmnLookupMst.lookupId from CmnLookupMst cmnLookupMst where cmnLookupMst.lookupName='EmployeeType' and cmnLookupMst.cmnLanguageMst.langId=1";
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List getAllEmployyTypeData size in DAO:-->" + list.size());
        return list;
    }
	
}
