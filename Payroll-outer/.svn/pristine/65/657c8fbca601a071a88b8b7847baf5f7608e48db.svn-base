package com.tcs.sgv.common.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class BillMovementDAOImpl extends GenericDaoHibernateImpl<TrnBillMvmnt,Long> implements BillMovementDAO 
{
	Log logger = LogFactory.getLog(getClass());	
	public BillMovementDAOImpl(Class<TrnBillMvmnt> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	public long getmaxMovementId(long billNo )
	{
			logger.info(" came in ........ dao for mvmnt");
		Session hibSession = getSession();
		Query sqlQuery = hibSession.createSQLQuery(" SELECT MAX(T.MOVEMNT_ID)  FROM TRN_BILL_MVMNT T  WHERE T.BILL_NO= "+billNo);
		Iterator iterator = sqlQuery.list().iterator();
		long maxMvmntId = 0;
		while(iterator.hasNext())
		{
			Object obj = iterator.next();
			if(obj != null)
			maxMvmntId = Long.parseLong(obj.toString());
		}
		maxMvmntId = maxMvmntId + 1;
		logger.info(" \n Returning ID "  + maxMvmntId);
		return maxMvmntId;
	}
	
	public int updateMvmntIdInRmrks(Long billNo, Long billMvmntId, Long userId)
	{
		Session hibSession = getSession();
		Query query = hibSession.createSQLQuery(" UPDATE TRN_BILL_REMARKS SET BILL_MVMNT_ID = " +billMvmntId +" WHERE BILL_NO = " +billNo +" AND USER_ID = " +userId);
		logger.info("Query to update BIll Movement Id in remarks : " +query);
		int updateStatus = query.executeUpdate();
		return updateStatus;
	}
	
	public int updateRmrksFlag(Long billNo)
	{
		Session hibSession = getSession();
		Query query = hibSession.createSQLQuery("UPDATE TRN_BILL_REMARKS SET RMRKS_FLAG = 'PR' WHERE BILL_NO = " +billNo);// +" and user_id = " +userId);
		logger.info("Query to update Remarks Flag in remarks : " +query);
		int updateStatus = query.executeUpdate();
		return updateStatus;
	}
	
	public int updateObjectionFlag(Long billNo)
	{
		Session hibSession = getSession();
		Query query = hibSession.createSQLQuery("UPDATE RLT_BILL_OBJECTION SET OBJ_FLAG = 'PO' WHERE BILL_NO = " +billNo);// +" and user_id = " +userId);
		logger.info("Query to update Objection Flag in Bill Objection : " +query);
		int updateStatus = query.executeUpdate();
		return updateStatus;
	}
	
	public TrnBillMvmnt getMvmntVOByStatus(String lStrStatus, long userId,String lStrBillNo)
	{
		TrnBillMvmnt trnBillMvmnt = null;
		Session hibSession = getSession();
		//Query query = hibSession.createQuery("from TrnBillMvmnt where mvmntStatus ='"+lStrStatus +"' and statusUpdtUserid= "+userId + " and billNo="+lStrBillNo);
		Query query = hibSession.createQuery("from TrnBillMvmnt where billNo="+lStrBillNo +" and movemntId in (select max(movemntId) from TrnBillMvmnt where billNo ="+lStrBillNo+") ");
		List resultList = query.list();
		if(resultList!= null && resultList.size() >0)
		{
			trnBillMvmnt= (TrnBillMvmnt)resultList.get(0);
		}
		return trnBillMvmnt;
	}
	
}
