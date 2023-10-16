package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayOfficeMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;

public class uploadNewEmployeeConfigarationDAOImpl extends GenericDaoHibernateImpl<HrPayOfficeMst, Long> implements  uploadNewEmployeeConfigarationDAO
{
	public uploadNewEmployeeConfigarationDAOImpl(Class<HrPayOfficeMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	// added by abhilash
    public List getOfficeNames(String officeName)
    {
    	Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrPayOfficeMst hrPayOfficeMst where hrPayOfficeMst.officeName ="  + officeName;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("uploadNewEmployeeDataByExcel officeNameListSize" + list.size());
        return list;
    }
    //ended by abhilash
}
