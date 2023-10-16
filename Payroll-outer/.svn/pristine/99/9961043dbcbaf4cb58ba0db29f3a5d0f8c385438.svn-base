package com.tcs.sgv.common.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class FinancialYearDAOImpl extends GenericDaoHibernateImpl<SgvcFinYearMst,Long> implements FinancialYearDAO {

	Log logger = LogFactory.getLog(SgvcFinYearMst.class);
	
	public FinancialYearDAOImpl(Class<SgvcFinYearMst> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public int getFinYearId(String date) 
	{			
		int FinYearId = 0;
		
		try 
		{
			Session hibSession = getSession();
			Query sqlQuery=hibSession.createQuery("select fym.finYearId from SgvcFinYearMst fym where fym.fromDate<='"+date+"' and fym.toDate>='"+date+"'");			
									
			List resList=sqlQuery.list();
			if (resList!=null && resList.size()>0) 
			{
				
				Iterator it = resList.iterator();
				while(it.hasNext()) 
				{
					CmnLocationMst vo=new CmnLocationMst();
					Object tuple = (Object)it.next();
					FinYearId=Integer.parseInt(tuple.toString());
					logger.info("----Financial Year Id is :----"+tuple+"---");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception occured in VoucherDAOImpl.getVouchersListForDet # \n"+e);
		}
		return FinYearId;
	}
	
	public int getFinYearId(Date date) 
	{			
		int FinYearId = 0;
		try 
		{
			Session hibSession = getSession();
			Query sqlQuery=hibSession.createQuery("select fym.finYearId from SgvcFinYearMst fym where fym.fromDate<=:fromdate and fym.toDate>=:todate");			
			sqlQuery.setParameter("fromdate",date);
			sqlQuery.setParameter("todate",date);
			
			
			List resList=sqlQuery.list();
			if (resList!=null && resList.size()>0) 
			{
				
				Iterator it = resList.iterator();
				while(it.hasNext()) 
				{
					CmnLocationMst vo=new CmnLocationMst();
					Object tuple = (Object)it.next();
					FinYearId=Integer.parseInt(tuple.toString());
					logger.info("----Financial Year Id is :----"+tuple+"---");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception occured in VoucherDAOImpl.getVouchersListForDet # \n"+e);
		}
		return FinYearId;
	}
	
	public int getFinYearIdByCurDate() 
	{			
		int FinYearId = 0;
		try 
		{
			
			Session hibSession = getSession();
			Query sqlQuery=hibSession.createQuery("select fym.finYearId from SgvcFinYearMst fym where fym.fromDate<= sysdate and fym.toDate>=sysdate");			
									
			List resList=sqlQuery.list();
			if (resList!=null && resList.size()>0) 
			{
				
				Iterator it = resList.iterator();
				while(it.hasNext()) 
				{
					CmnLocationMst vo=new CmnLocationMst();
					Object tuple = (Object)it.next();
					FinYearId=Integer.parseInt(tuple.toString());
					logger.info("----Financial Year Id is :----"+tuple+"---");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception occured in VoucherDAOImpl.getVouchersListForDet # \n"+e);
		}
		return FinYearId;
	}
	

}
