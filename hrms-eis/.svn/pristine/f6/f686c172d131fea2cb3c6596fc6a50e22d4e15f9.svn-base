package com.tcs.sgv.eis.util;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class CommomnDataObjectFatch {
	
	ServiceLocator serv;
public CommomnDataObjectFatch() 
{
	// TODO Auto-generated constructor stub
}
	/**
	 * @return the serv
	 */
	public ServiceLocator getServ() {
		return serv;
	}



	/**
	 * @param serv the serv to set
	 */
	public void setServ(ServiceLocator serv) {
		this.serv = serv;
	}



	public CommomnDataObjectFatch(ServiceLocator serv) {
		
		this.serv = serv;
	}

	
	
	public OrgUserMst getorgUserMst(long userId)
	{
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		return orgUserMstDaoImpl.read(userId);
	}
	
	public CmnDatabaseMst getCmnDatabaseMst(long dbId)
	{
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		 return cmnDatabaseMstDaoImpl.read(dbId);
	}
	
	public CmnLocationMst getCmnLocationMst(long locId)
	{
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		return cmnLocationMstDaoImpl.read(locId);	
	} 
	public CmnLookupMst getCmnLookupMst(long langId)
	{
	CmnLookupMstDAOImpl cmnLookupMstDao  = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
	return cmnLookupMstDao.getLookUpVOByLookUpNameAndLang("Primary_Post",langId);
	}
	public CmnLanguageMst getCmnLanguageMst(long langId)
	{
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		return cmnLanguageMstDaoImpl.read(langId);
	}
	
	public OrgPostMst getOrgPostMst(long postId)
	{
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		return orgPostMstDaoImpl.read(postId);					
	}
	/*public OrgPostMst getOrgPostMst(long loggedInpostId)
	{
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		return orgPostMstDaoImpl.read(loggedInpostId);				
	}*/

}
