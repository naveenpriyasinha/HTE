package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerNomineeDtls;

public class MstPensionerNomineeDtlsDAOImpl extends GenericDaoHibernateImpl<MstPensionerNomineeDtls, Long>{

    Log gLogger = LogFactory.getLog(getClass());
    
    public MstPensionerNomineeDtlsDAOImpl(Class <MstPensionerNomineeDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	public List getListOfNominee(String pensionerCode, long langId) throws Exception
	{	
		Session hiSession = getSession();
		StringBuilder strQuery = new StringBuilder();
		List resultList;
		Object[] tuple;
		Iterator itr;
		ArrayList arrlstPnsnrNmdtls = null;
		try
		{
			strQuery.append(" SELECT nomineeId,name,percent");
			strQuery.append(" FROM MstPensionerNomineeDtls ");
			strQuery.append(" where pensionerCode =:pensionerCode ");
			
			Query hqlQuery = hiSession.createQuery(strQuery.toString());
			hqlQuery.setString("pensionerCode", pensionerCode);
			resultList = hqlQuery.list();
			
				if(resultList != null && resultList.size() > 0)
				{
					arrlstPnsnrNmdtls = new ArrayList();
					itr = resultList.iterator();
					while(itr.hasNext())
					{
						tuple = (Object[])itr.next();
						MstPensionerNomineeDtls  mstPensionerNomineeDtlsVo = new MstPensionerNomineeDtls();
						mstPensionerNomineeDtlsVo.setNomineeId(Long.valueOf(tuple[0].toString()));
						if(tuple[1] != null)
							mstPensionerNomineeDtlsVo.setName(tuple[1].toString());
						if(tuple[2] != null)
						mstPensionerNomineeDtlsVo.setPercent(new BigDecimal(tuple[2].toString()));
						arrlstPnsnrNmdtls.add(mstPensionerNomineeDtlsVo);
					}
				}
		}catch(Exception e)
		{
            gLogger.error("Error is"+e,e);
            throw e;
		}
		return arrlstPnsnrNmdtls;
	}
	
	public List getPenionNomineeDtlsFromPensionCode(String strPnsnCode,long langId) throws Exception
	{
		Session hiSession = getSession();
		ArrayList arrPnsnNmDtls = new ArrayList();
		StringBuilder strQuery = new StringBuilder();
		List resultList;
		Object tuple;
		Iterator itr;
		try
		{
			strQuery.append(" SELECT n.nomineeId ");
			strQuery.append(" FROM MstPensionerNomineeDtls n ");
			strQuery.append(" WHERE n.pensionerCode =:pensionerCode");
			
			Query hqlQuery = hiSession.createQuery(strQuery.toString());
			hqlQuery.setString("pensionerCode", strPnsnCode);
			
			resultList = hqlQuery.list();
			
			if(resultList != null && resultList.size() > 0)
			{
				itr = resultList.iterator();
				while(itr.hasNext())
				{
					tuple = (Object)itr.next();
					arrPnsnNmDtls.add((Long)tuple);
				}
			}
		}catch(Exception e)
		{
            gLogger.error("Error is"+e,e);
			throw e;
			
		}
		return arrPnsnNmDtls;
	}
}
