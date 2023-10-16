package com.tcs.sgv.eis.service;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class CommomnDataObjectFatch {
	
	ServiceLocator serv;

	public CommomnDataObjectFatch(ServiceLocator serv) {
		super();
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
	 

}
