package com.tcs.sgv.stamp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.MstStampGroup;

public class ReportQueryDAOImpl extends GenericDaoHibernateImpl<MstStampGroup,Long>
{
	public ReportQueryDAOImpl(Class<MstStampGroup> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getDenominationCode(String strGroupCode)
	{
		List denominationCode=new ArrayList();
		Session session=getSession();
		String strQuery="select deno.dnm_code from Mst_Stamp_Denomination deno where deno.grp_code="+strGroupCode;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				String strDenominationCode=itResult.next().toString();
				denominationCode.add(strDenominationCode);
			}
		}
		return denominationCode;
	}
	
}
