package com.tcs.sgv.eis.util;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.empPunishmentDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEmpPunishmentDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class empPunishmentServiceHelper 
{

	
	Log logger = LogFactory.getLog(getClass());
	ServiceLocator serv;
	public empPunishmentServiceHelper() 
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




	public empPunishmentServiceHelper(ServiceLocator serv) 
	{
		
		this.serv = serv;
	}

	
	
	
	//Added by Abhilash
	public void updateEmpPunishmentDtls(HrEmpPunishmentDtls hrEmpPunishmentDtls,long postId,long userId) throws Exception
	{
		empPunishmentDAOImpl punishmentDAO = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,serv.getSessionFactory());
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		
		long pmtID = hrEmpPunishmentDtls.getPunishmentId();
		//System.out.println("The PunishmentId inside update mode is------>"+pmtID);
		HrEmpPunishmentDtls hrempPunishmentDtls = punishmentDAO.read(pmtID);
		logger.info("INSIDE UPDATE insertPunishmentData");	
		logger.info("empPunishmentServiceHelper going to updateEmpPunishmentDtls do modularization");
		hrempPunishmentDtls.setReason(hrEmpPunishmentDtls.getReason());
		hrempPunishmentDtls.setStartDate(hrEmpPunishmentDtls.getStartDate());
		logger.info("empPunishmentServiceHelper going to updateEmpPunishmentDtls getEndDate" + hrEmpPunishmentDtls.getEndDate());
		hrempPunishmentDtls.setEndDate(hrEmpPunishmentDtls.getEndDate());
		
		Date sysdate = new Date();
		hrempPunishmentDtls.setUpdatedDate(sysdate);
		hrempPunishmentDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
		hrempPunishmentDtls.setOrgUserMstByUpdatedBy(orgUserMst);
		punishmentDAO.update(hrempPunishmentDtls);
	}

	public void insertEmpPunishmentDtls(long postId,long userId,long langId,long locId,long attachment_Id,HrEmpPunishmentDtls hrEmpPunishmentDtls,String empIdStr) throws Exception
	{
		empPunishmentDAOImpl punishmentDAO = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,serv.getSessionFactory());
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
		logger.info("empPunishmentServiceHelper going to insertEmpPunishmentDtls do modularization");
		IdGenerator idGen = new IdGenerator();
		long punishmentId = idGen.PKGeneratorWebService("hr_emp_punishment_dtls", serv, userId, langId, locId);
		hrEmpPunishmentDtls.setPunishmentId(punishmentId);
		GenericDaoHibernateImpl gImpl = null;
		 
		 HrEisEmpMst hrEisObj=new HrEisEmpMst();            
	     gImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
	     gImpl.setSessionFactory(serv.getSessionFactory());
	    
	     long empId=0;
	     if(empIdStr!=null && !empIdStr.equals(""))
	     {
	    	 empId=Long.parseLong(empIdStr);
	    	 logger.info("the emp id is "+empId);
	     }
	     
	     List<HrEisEmpMst> hrEisMstList = gImpl.getListByColumnAndValue("orgEmpMst.empId", empId);
	     if(hrEisMstList!=null && hrEisMstList.size()>0)
	     {
	    	 hrEisObj = hrEisMstList.get(0);
	     }
	     logger.info("the emp id is "+hrEisObj.getOrgEmpMst().getEmpFname());
	     
		 Date sysdate = new Date();
		 hrEmpPunishmentDtls.setHrEisEmpMst(hrEisObj);
		 hrEmpPunishmentDtls.setOrgPostMstByCreatedByPost(orgPostMst);
		 hrEmpPunishmentDtls.setCmnLocationMst(cmnLocationMst);
		 hrEmpPunishmentDtls.setOrgUserMstByCreatedBy(orgUserMst);
		 hrEmpPunishmentDtls.setCreatedDate(sysdate);				 				 							
		 hrEmpPunishmentDtls.setAttachment_Id(attachment_Id);
		 
		 punishmentDAO.create(hrEmpPunishmentDtls);
	}

	//Ended by Abhilash
	
	
}
