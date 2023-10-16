package com.tcs.sgv.common.dao;

/*
 * Created by Sathya on 21-07-2007, for WorkList population
 */
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.WfDocMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class WorkListDAOImpl extends GenericDaoHibernateImpl<WfDocMst, Long>
		implements WorkListDAO {

	public WorkListDAOImpl(Class<WfDocMst> type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getDocList(String postName) {
		List docList = new ArrayList();
		// Map resultMap = new HashMap();
		try {
			Session hibSession = getSession();
			String query1 = "from WfDocMst w where w.crtUsr='"+postName+"' ) ";
			Query sqlQuery1 = hibSession.createQuery(query1);
			docList = sqlQuery1.list();
			//System.out.println("Sathya, the docList size is:::::::::"+ docList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return docList;

	}
}
