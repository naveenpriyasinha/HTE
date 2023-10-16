package com.tcs.sgv.eis.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * Created by Japen Pathak for for inserting / updating data in org_designation_mst tables.
 */

public class DesigMasterServiceImplHelper 
{
	Log logger = LogFactory.getLog(getClass());
	
	ServiceLocator serv;
	
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

	public DesigMasterServiceImplHelper(ServiceLocator serv)
	{
	super();
	this.serv=serv;
	}

// Added by Abhilash
/**
		 * @Author	: Abhilash
		 * @Date	: 17/02/2011 
		 * Function	: This method will insert data in org_designation_mst table.
		 * @param    long userId,long langId,long locId,OrgDesignationMst desigMst,long postId
		 * @return   void
		 */
	
	public void insertDesignationMasterDtls(long userId,long langId,long locId,OrgDesignationMst desigMst,long postId)
	{
		Date sysdate = new Date();
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
		
		DesigMasterDAO desinDAO = new DesigMasterDAO(OrgDesignationMst.class, serv.getSessionFactory());
		logger.info("********insertDesignationMasterDtls of DesigMasterServiceImplHelper" );
		
		IdGenerator idGen = new IdGenerator();
		Long id= idGen.PKGeneratorWebService("org_designation_mst", serv, userId, langId, locId);
		
		logger.info("****************************the id generated is "+id);
		CmnLanguageMst cmnLanguageen=new CmnLanguageMst();//hardcoded
		cmnLanguageen.setLangId(1);
		desigMst.setDsgnId(id);
		desigMst.setDsgnCode(""+id);
		desigMst.setCmnLanguageMst(cmnLanguageen);
		desigMst.setCreatedDate(sysdate);
		desigMst.setOrgUserMstByCreatedBy(orgUserMst);
		desigMst.setOrgUserMstByUpdatedBy(orgUserMst);
		desigMst.setOrgPostMstByCreatedByPost(orgPostMst);
		desigMst.setOrgPostMstByUpdatedByPost(orgPostMst);
		desigMst.setUpdatedDate(sysdate);
		
		//desinDAO.create(desigMst);
	}

/**
		 * @Author	: Abhilash
		 * @Date	: 17/02/2011 
		 * Function	: This method will update data in org_designation_mst table.
		 * @param    long postId,OrgDesignationMst desigMst,long desigid,String desgName,String desgShortName
		 * @return   void
		 */

	
	public void updateDesignationMasterDtls(long postId,OrgDesignationMst desigMst,long desigid,String desgName,String desgShortName)
	{
		Date sysdate = new Date();
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
		DesigMasterDAO desinDAO = new DesigMasterDAO(OrgDesignationMst.class, serv.getSessionFactory());
		logger.info("********updateDesignationMasterDtls of DesigMasterServiceImplHelper" );
		desigMst=desinDAO.read(desigid);
		desigMst.setDsgnName(desgName);
		desigMst.setDsgnShrtName(desgShortName);
		desigMst.setOrgPostMstByUpdatedByPost(orgPostMst);
		desigMst.setUpdatedDate(sysdate);
		//desinDAO.update(desigMst);
	}
	
	// Ended by Abhilash
}
