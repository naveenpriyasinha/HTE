package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisBranchDtls;




public class HrEisBranchDtlsDAOImpl extends GenericDaoHibernateImpl<HrEisBranchDtls, Long> implements HrEisBranchDtlsDAO
{
    Log logger = LogFactory.getLog(getClass());
  

    public HrEisBranchDtlsDAOImpl(Class<HrEisBranchDtls> type, SessionFactory sessionFactory)
    {
    	 super(type);
    	 setSessionFactory(sessionFactory);
    }
 /* public List insertRecord()
    {
 		logger.info("------inside HrEisBranchDtlsDAOImpl-----------");
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrEisBranchDtls.class);
        
            
            objCrt.setFetchMode("hrEisBranchDtls", FetchMode.JOIN);
            objCrt.createAlias("hrEisBranchDtls", "hrEisBranchDtls");
            objCrt.createAlias("hrEisBranchDtls.orgPostMst", "orgPostMst");
            objCrt.createAlias("hrEisBranchDtls.orgUserMst", "orgUserMst");
            objCrt.createAlias("hrEisBranchDtls.cmnLookupMst", "cmnLookupMst");
            objCrt.createAlias("hrEisBranchDtls.cmnLocationMst", "cmnLocationMst");
            objCrt.createAlias("hrEisBranchDtls.cmnLanguageMst", "cmnLanguageMst");
        }
        catch(Exception e)
        {
        	logger.info("There some error in DAOImpl");
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }*/

}
