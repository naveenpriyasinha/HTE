package com.tcs.sgv.user.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class EmployeeEntryDAO extends GenericDaoHibernateImpl<OrgUserMst, Long> {
	Log logger = LogFactory.getLog(getClass());
	public EmployeeEntryDAO(Class<OrgUserMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
public List getAlldsgnData(long langId)
	{
		List dsgnList = null;
		Session session = getSession();

		String strQuery = "select distinct(o.dsgn_id),o.dsgn_name from  org_designation_mst o where o.dsgn_Id in(select b.dsgn_id from org_post_details_rlt b where b.lang_id='"+langId+"')";
        Query query = session.createSQLQuery(strQuery);
        dsgnList = query.list();
		
		return dsgnList;
	}	



}
