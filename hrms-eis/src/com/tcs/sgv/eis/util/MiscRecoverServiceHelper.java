package com.tcs.sgv.eis.util;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.MiscRecoverDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrMiscRecoverDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
  Created by Japen Pathak for for inserting / updating data in HR_MISC_RECOVER_DTLS tables.
 */



public class MiscRecoverServiceHelper 
{
Log logger = LogFactory.getLog(getClass());
ServiceLocator serv;

public MiscRecoverServiceHelper(ServiceLocator serv) 
{
	super();
	this.serv = serv;
}

//Added by Abhilash

/**
		 * @Author	: 
		 * @Date	: 17/02/2011 
		 * Function	: This method will update data in HR_MISC_RECOVER_DTLS table.
		 * @param    HrMiscRecoverDtls hrMiscRecvDtls,long postId,long userId
		 * @return   void
		 */


public void updateMiscellaneousDtls(HrMiscRecoverDtls hrMiscRecvDtls,long postId,long userId) throws Exception
{
	
	MiscRecoverDAOImpl miscRecoverDAO = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
	OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
	OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
	OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
	OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
	
	long miscid = hrMiscRecvDtls.getMiscId();
	//System.out.println("The miscid inside update mode is------>"+miscid);
	HrMiscRecoverDtls hrmiscRecoverDtls = miscRecoverDAO.read(miscid);
	logger.info("INSIDE UPDATE insertMiscData");	
	logger.info("The reason is----->"+hrMiscRecvDtls.getReason());
	logger.info("The amount is----->"+hrMiscRecvDtls.getRecoverAmt());
	logger.info("The date is------->"+hrMiscRecvDtls.getRecoverDate());

	logger.info("MiscRecoverServiceHelper going to updateMiscellaneousDtls do modularization");
	
	hrmiscRecoverDtls.setReason(hrMiscRecvDtls.getReason());
	hrmiscRecoverDtls.setRecoverAmt(hrMiscRecvDtls.getRecoverAmt());
	hrmiscRecoverDtls.setRecoverDate(hrMiscRecvDtls.getRecoverDate());
	hrmiscRecoverDtls.setRecoverEndDate(hrMiscRecvDtls.getRecoverEndDate());
	hrmiscRecoverDtls.setInstallment(hrMiscRecvDtls.getInstallment());
	hrmiscRecoverDtls.setEdpTypeId(hrMiscRecvDtls.getEdpTypeId());
	hrmiscRecoverDtls.setTotal_amount(hrMiscRecvDtls.getTotal_amount());
	hrmiscRecoverDtls.setMiscActivateFlag(hrMiscRecvDtls.getMiscActivateFlag());
	Date sysdate = new Date();
	hrmiscRecoverDtls.setUpdatedDate(sysdate);
	hrmiscRecoverDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
	hrmiscRecoverDtls.setOrgUserMstByUpdatedBy(orgUserMst);
	miscRecoverDAO.update(hrmiscRecoverDtls);
}


/**
		 * @Author	: 
		 * @Date	: 17/02/2011 
		 * Function	: This method will insert data in HR_MISC_RECOVER_DTLS table.
		 * @param    long attachment_Id,long userId,long langId,long locId,long dbId,long postId,HrMiscRecoverDtls hrMiscRecvDtls,String empIdStr
		 * @return   void
		 */

public void insertMiscellaneousDtls(long attachment_Id,long userId,long langId,long locId,long dbId,long postId,HrMiscRecoverDtls hrMiscRecvDtls,String empIdStr) throws Exception
{
	OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
	OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
	OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
	OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
	CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
	CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
	CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
	CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
	CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
	CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
	MiscRecoverDAOImpl miscRecoverDAO = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
	
	logger.info("MiscRecoverServiceHelper going to insertMiscellaneousDtls do modularization");
	
	IdGenerator idGen = new IdGenerator();
	long miscId = idGen.PKGeneratorWebService("hr_misc_recover_dtls", serv, userId, langId, locId);
	hrMiscRecvDtls.setMiscId(miscId);
    
	 
	 HrEisEmpMst hrEisObj=new HrEisEmpMst(); 
	 GenericDaoHibernateImpl gImpl = null;
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
	 hrMiscRecvDtls.setHrEisEmpMst(hrEisObj);
	 hrMiscRecvDtls.setCmnLanguageMst(cmnLanguageMst);
	 hrMiscRecvDtls.setCmnDatabaseMst(cmnDatabaseMst);
	 //hrMiscRecvDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
	 hrMiscRecvDtls.setOrgPostMstByCreatedByPost(orgPostMst);
	 hrMiscRecvDtls.setCmnLocationMst(cmnLocationMst);
	 //hrMiscRecvDtls.setOrgUserMstByUpdatedBy(orgUserMst);
	 hrMiscRecvDtls.setOrgUserMstByCreatedBy(orgUserMst);
	 hrMiscRecvDtls.setCreatedDate(sysdate);				 				 							
	 hrMiscRecvDtls.setElementCode(miscId);
	 hrMiscRecvDtls.setAttachment_Id(attachment_Id);
	 hrMiscRecvDtls.setMiscActivateFlag(new Integer(1));
	 hrMiscRecvDtls.setTrnCounter(new Integer(1));
	 miscRecoverDAO.create(hrMiscRecvDtls);	
}


//Ended by Abhilash

}
