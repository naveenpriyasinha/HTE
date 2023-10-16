package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayDDOSchemeMpgVO;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;

public class HrPayDdoSchemeMpgDAOImpl extends GenericDaoHibernateImpl<HrPayDDOSchemeMpgVO, Long> implements  HrPayDdoSchemeMpgDAO
{
	public HrPayDdoSchemeMpgDAOImpl(Class<HrPayDDOSchemeMpgVO> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public List getAllData(long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query in HrPayDdoSchemeMpgDAOImpl...");
           
            String strQuery = "from HrPayDDOSchemeMpgVO ddo where ddo.cmnLanguageMst.langId ="  + langId;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
}
