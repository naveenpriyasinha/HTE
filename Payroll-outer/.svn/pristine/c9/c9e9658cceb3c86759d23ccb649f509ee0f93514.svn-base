package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

public class TrnPnsncaseRopRltDAO extends GenericDaoHibernateImpl<TrnPnsncaseRopRlt, Long>
{
	Log gLogger = LogFactory.getLog(getClass());
	public TrnPnsncaseRopRltDAO(Class<TrnPnsncaseRopRlt> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getROPDtlsFromPpoNo(String ppoNo) throws Exception
	{
		Session hiSession = getSession();
		ArrayList RopDtls = new ArrayList();
		StringBuilder strQuery = new StringBuilder();
		Iterator itr;
		Object tuple;
		List resultList;
		try
		{
			strQuery.append(" SELECT pnsncaseRopRltId ");
			strQuery.append(" FROM TrnPnsncaseRopRlt ");
			strQuery.append(" WHERE ppoNo=:ppoNo");
			
			Query hqlQuey = hiSession.createQuery(strQuery.toString());
			hqlQuey.setString("ppoNo", ppoNo);
			
			resultList = hqlQuey.list();
			itr = resultList.iterator();
			if(resultList != null && resultList.size()>0 )
			for(int i =0; i< resultList.size() ; i++)
			{
				tuple = (Object)itr.next();
				RopDtls.add((Long)tuple);
			}
		}catch(Exception e)
		{
			gLogger.error("Error is,"+e,e);
            throw e;
		}
		return RopDtls;
	} 
}
