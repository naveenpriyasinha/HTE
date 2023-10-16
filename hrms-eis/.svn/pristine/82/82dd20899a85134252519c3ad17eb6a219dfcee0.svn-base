package com.tcs.sgv.eis.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class SGDMpgServiceImplHelper 
{
	public final Log logger = LogFactory.getLog(getClass());
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

	public SGDMpgServiceImplHelper(ServiceLocator serv) 
	{
		super();
		this.serv = serv;
	}
	
	//Added by Abhilash
	public void insertGradeDesingnScaleMpg(HrEisSgdMpg hrEisSgdMpg,long postId,long userId,long langId,long locId) throws Exception
	{
		Date lstrDate = new Date();
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		
		GradDesgScaleMapDAO gradDesgScaleMapDAO=new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
		logger.info("SGDMpgServiceImplHelper going to insertGradeDesingnScaleMpg do modularization");
		IdGenerator idGen = new IdGenerator();
    	//SgdMapID = idGen.PKGeneratorWODBLOC("hr_eis_sgd_mpg", objectArgs);
    	long SgdMapID = idGen.PKGeneratorWebService("hr_eis_sgd_mpg", serv, userId, langId, locId);
        hrEisSgdMpg.setOrgUserMstByCreatedBy(orgUserMst);
        hrEisSgdMpg.setCreatedDate(lstrDate);
        hrEisSgdMpg.setOrgPostMstByCreatedByPost(orgPostMst);
    	hrEisSgdMpg.setSgdMapId(SgdMapID);
        gradDesgScaleMapDAO.create(hrEisSgdMpg);
		
	}
	public void updateGradeDesingnScaleMpg(HrEisSgdMpg hrEisSgdMpg,long SgdMapId) throws Exception
	{
		logger.info("SGDMpgServiceImplHelper going to updateGradeDesingnScaleMpg do modularization");
		GradDesgScaleMapDAO gradDesgScaleMapDAO=new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
		hrEisSgdMpg.setSgdMapId(SgdMapId);
		gradDesgScaleMapDAO.update(hrEisSgdMpg);
	}
	//Ended by Abhilash

}
