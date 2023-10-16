package com.tcs.sgv.eis.util;

import java.util.Date;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* Created by Japen Pathak for for inserting / updating data in HR_EIS_GD_MPG tables.
*/

public class HrEisGdMpgServiceImplHelper
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

	public HrEisGdMpgServiceImplHelper(ServiceLocator serv) 
	{
		super();
		this.serv = serv;
	}

	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_EIS_GD_MPG table.
	 * @param    ServiceLocator serv,HrEisGdMpg hrEisGdMpg,long postId,long userId,long langId,long locId
	 * @return   void
	 */
	
	//Added by Abhilash
	public void insertGradeDesignationMpg(HrEisGdMpg hrEisGdMpg,long postId,long userId,long langId,long locId)
	{
		
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		
		GenericDaoHibernateImpl daoFortrial = new GenericDaoHibernateImpl(HrEisGdMpg.class);
		daoFortrial.setSessionFactory(serv.getSessionFactory());
		logger.info("HrEisGdMpgServiceImplHelper going to insertGradeDesignationMpg do modularization");
		Date lstrDate =new Date();
		IdGenerator idGen = new IdGenerator();
		long GdMapID = idGen.PKGeneratorWebService("hr_eis_gd_mpg", serv, userId, langId, locId);
		hrEisGdMpg.setOrgUserMstByCreatedBy(orgUserMst);
		hrEisGdMpg.setCreatedDate(lstrDate);
		hrEisGdMpg.setOrgPostMstByCreatedByPost(orgPostMst);
		hrEisGdMpg.setGdMapId(GdMapID);
		//daoFortrial.create(hrEisGdMpg);
	}
	
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will update data in HR_EIS_GD_MPG table.
	 * @param   HrEisGdMpg hrEisGdMpg,long postId,long userId,long GdMapId
	 * @return   void
	 */
	
	public void updateGradeDesignationMpg(HrEisGdMpg hrEisGdMpg,long postId,long userId,long GdMapId)
	{
		logger.info("HrEisGdMpgServiceImplHelper going to updateGradeDesignationMpg do modularization");
		GenericDaoHibernateImpl<HrEisGdMpg, Long> daoFortrial = new GenericDaoHibernateImpl<HrEisGdMpg, Long>(HrEisGdMpg.class);
		 hrEisGdMpg.setGdMapId(GdMapId);
         //daoFortrial.update(hrEisGdMpg);	
	}
	//Ended by Abhilash

}
