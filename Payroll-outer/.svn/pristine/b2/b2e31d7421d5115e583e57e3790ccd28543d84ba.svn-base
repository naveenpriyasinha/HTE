package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerFamilyDtls;

public class MstPensionerFamilyDtlsDAOImpl extends GenericDaoHibernateImpl<MstPensionerFamilyDtls, Long>{

	public MstPensionerFamilyDtlsDAOImpl(Class<MstPensionerFamilyDtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
    Log gLogger = LogFactory.getLog(getClass());
	public List getFamilyDtlsFromPensionerCode(String PensionerCode,long langId) throws Exception
	{
		Session hiSession = getSession();
		ArrayList arrFmDtls = new ArrayList();
		StringBuilder strQuery = new StringBuilder();
		Iterator itr;
		Object tuple;
		List resultList;
		try
		{
			strQuery.append(" SELECT familyMemberId ");
			strQuery.append(" FROM MstPensionerFamilyDtls ");
			strQuery.append(" WHERE pensionerCode=:pensionerCode");
			
			Query hqlQuey = hiSession.createQuery(strQuery.toString());
			hqlQuey.setString("pensionerCode", PensionerCode);
			
			resultList = hqlQuey.list();
			
			if(resultList != null && resultList.size()>0 )
			for(int i =0; i< resultList.size() ; i++)
			{
				itr = resultList.iterator();
				tuple = (Object)itr.next();
				arrFmDtls.add((Long)tuple);
			}
		}catch(Exception e)
		{
			gLogger.error("Error is,"+e,e);
            throw e;
		}
		return arrFmDtls;
	}
	
	public List getListOfFamily(String pensionerCode,long langId) throws Exception
	{
		
		Session hiSession = getSession();
		StringBuilder strQuery = new StringBuilder();
		Iterator itr;
		Object[] tuple;
		List resultList;
		ArrayList arrlstPnsnrFDtls = null;
		try
		{
			strQuery.append(" SELECT name, relationship, dateOfBirth, dateOfDeath, majorFlag,salary, handicapeFlag,guardianName");
			strQuery.append(" FROM MstPensionerFamilyDtls ");
			strQuery.append(" WHERE pensionerCode=:pensoinerCode ");
				
			Query hqlQuery = hiSession.createQuery(strQuery.toString());
			hqlQuery.setParameter("pensoinerCode", pensionerCode);
			resultList = hqlQuery.list();
				if(resultList != null && resultList.size() > 0)
				{
					arrlstPnsnrFDtls = new ArrayList();
					
						itr = resultList.iterator();
						while(itr.hasNext())
						{
							MstPensionerFamilyDtls mstPensionerFamilyDtls = new MstPensionerFamilyDtls();
							tuple = (Object[])itr.next();
							if(tuple[0] != null)
								mstPensionerFamilyDtls.setName(tuple[0].toString());
							if(tuple[1] != null)
								mstPensionerFamilyDtls.setRelationship(tuple[1].toString());
							if(tuple[2] != null)
								mstPensionerFamilyDtls.setDateOfBirth((Date)tuple[2]);
							if(tuple[3] != null)
								mstPensionerFamilyDtls.setDateOfDeath((Date)tuple[3]);
							if(tuple[4] != null)
								mstPensionerFamilyDtls.setMajorFlag(tuple[4].toString());
							if(tuple[5] != null)
								mstPensionerFamilyDtls.setSalary(new BigDecimal(tuple[5].toString()));
							if(tuple[6] != null)
								mstPensionerFamilyDtls.setHandicapeFlag(tuple[6].toString());
							if(tuple[7] != null)
								mstPensionerFamilyDtls.setGuardianName(tuple[7].toString());
							arrlstPnsnrFDtls.add(mstPensionerFamilyDtls);
						}
				}				
		}catch(Exception e)
		{
            gLogger.info("error"+e,e);
			throw e;
		}
		return arrlstPnsnrFDtls;
	}
    public List getFamilyDtlsPks(String pensionerCode,long langId) throws Exception
    {
        Session hiSession = getSession();
        StringBuilder strQuery = new StringBuilder();
        List resultList = null;
        try
        {
            strQuery.append(" SELECT familyMemberId ");
            strQuery.append(" FROM MstPensionerFamilyDtls ");
            strQuery.append(" WHERE pensionerCode=:pensoinerCode ");
            Query hqlQuery = hiSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("pensoinerCode", pensionerCode);
            resultList = hqlQuery.list();
        }
        catch(Exception e)
        {
            gLogger.info("error"+e,e);
        }
        return resultList;
        
    }
}
