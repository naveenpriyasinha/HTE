package com.tcs.sgv.common.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.MstParty;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
/**
 * This class is used to get party details
 * @author 157045
 * @version 1.0
 *
 */

public class PartyMstDAOImpl extends GenericDaoHibernateImpl<MstParty,Integer> implements PartyMstDAO
{
	Log logger = LogFactory.getLog(getClass());
	public PartyMstDAOImpl(Class<MstParty> type, SessionFactory sessionFac)
	{
		super(type);
		setSessionFactory(sessionFac);
	}	
	/**
	 * To get all parties
	 * @return List
	 */
	public List getAllParty( )
	{
		Criteria objCrt = null;
		try 
		{
			Session hibSession = getSession( ); 
			objCrt = hibSession.createCriteria(MstParty.class);
		}
		catch(Exception e)
		{
			//System.out.println("Errr thr.................................");
			e.printStackTrace();
		}
		////System.out.println("\n\n--------- returning party list ---- " + objCrt.list());
		return objCrt.list();
	}
	/**
	 * To get all parties by Location 
	 * 
	 * @param locCode
	 * @return List
	 */
	
	public List getAllPartyByLocation(String locCode )
	{
		List resList =null;
		try 
		{
			Session hibSession = getSession();
			Query sqlQueryMstParty=hibSession.createQuery(" from  MstParty where locationCode='"+locCode+"'");
			resList = sqlQueryMstParty.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//System.out.println("\n\n--------- returning party list ---- " + resList);
		return resList;
	}
	
	/**
	 * To get all banks to be shown in party list
	 * 
	 * @param locCode
	 * @return List
	 */
	public List getAllBankPartyByLocation(String locCode )
	{
		List resList =null;
		try 
		{
			Session hibSession = getSession();
			Query sqlQueryMstParty=hibSession.createQuery(" from  RltBankBranch where locationCode='"+locCode+"'");
			resList = sqlQueryMstParty.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//System.out.println("\n\n--------- returning party list ---- " + resList);
		return resList;
	}
	
	
}
