package com.tcs.sgv.common.dao;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.TrnRcptMvmnt;

public class ChallanMovementDAOImpl extends GenericDaoHibernateImpl<TrnRcptMvmnt,Integer> implements ChallanMovementDAO {

	public ChallanMovementDAOImpl(Class<TrnRcptMvmnt> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	public long getmaxMovementId(long rcptDetailId)
	{
		//System.out.println(" came in ........ dao for mvmnt");
		Session hibSession = getSession();
		Query sqlQuery = hibSession.createSQLQuery(" SELECT max(t.movemnt_id)  FROM trn_rcpt_mvmnt t  where t.receipt_detail_id = "+rcptDetailId);
		Iterator iterator = sqlQuery.list().iterator();
		long maxMvmntId = 0;
		while(iterator.hasNext())
		{
			Object obj = iterator.next();
			if(obj != null)
			maxMvmntId = Long.parseLong(obj.toString());
		}
		maxMvmntId = maxMvmntId + 1;
		//System.out.println(" \n Returning ID "  + maxMvmntId);
		return maxMvmntId;
	}
	
	
}
