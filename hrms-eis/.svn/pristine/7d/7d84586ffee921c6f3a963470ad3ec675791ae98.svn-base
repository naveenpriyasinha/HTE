package com.tcs.sgv.eis.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * Created by Japen Pathak for for inserting / updating data in hr_pay_gpf_details  tables.
 */
public class EmpGpfDtlsServiceHelper 
{
	Log logger = LogFactory.getLog(getClass());
	
ServiceLocator serv;

public EmpGpfDtlsServiceHelper(ServiceLocator serv)
{
	super();
	this.serv=serv;
}

/**
 * @Author	: Abhilash
 * @Date	: 17/02/2011 
 * Function	: This method will insert data in hr_pay_gpf_details table.
 * @param    ServiceLocator serv,HrPayGpfBalanceDtls GpfDtls,long langId,long dbId,long postId,long userId,long locId,OrgGradeMst orgGradeMst
 * @return   void
 */



public void insertGpfDtlsData(HrPayGpfBalanceDtls GpfDtls,long langId,long dbId,long postId,long userId,long locId,OrgGradeMst orgGradeMst)
{
	
	CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
	CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
	CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
	CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
	OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
	OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
	OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
	OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
	CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
	CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
	
	Date sysdate= new Date();
	
	logger.info("EmpGpfDtlsServiceHelper inserting is coming");
	
	GpfDtls.setOrgGradeMst(orgGradeMst);
	//ended
	GpfDtls.setCmnDatabaseMst(cmnDatabaseMst);
	GpfDtls.setOrgPostMstByCreatedByPost(orgPostMst);
	GpfDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
	GpfDtls.setCmnLocationMst(cmnLocationMst);
	GpfDtls.setOrgUserMstByCreatedByUser(orgUserMst);
	GpfDtls.setOrgUserMstByUpdatedByUser(orgUserMst);
	GpfDtls.setCreatedDate(sysdate);
	GpfDtls.setTrnCounter(Integer.valueOf(1));
		
	EmpGpfDtlsDAOImpl gpfDtls = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
	
	try
	{
		gpfDtls.create(GpfDtls);
	}
	catch(Exception e)
	{
		logger.info("Inside Exception Mode"+e);
	}
	
	
}

/**
 * @Author	: Abhilash
 * @Date	: 17/02/2011 
 * Function	: This method will update data in hr_pay_gpf_details table.
 * @param    ServiceLocator serv,HrPayGpfBalanceDtls gpfDetails,long gradeId,long postId,long userId
 * @return   void
 */


public void updateGpfDtlsData(HrPayGpfBalanceDtls gpfDetails,long gradeId,long postId,long userId)
{
	Date sysdate = new Date();
	OrgGradeMstDaoImpl OrgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
	OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
	OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
	OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
	OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
	EmpGpfDtlsDAOImpl gpfDtls = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
	
	logger.info("EmpGpfDtlsServiceHelper updating is coming");
	
	OrgGradeMst orgGradeMst = new OrgGradeMst();
	orgGradeMst = OrgGradeMstDaoImpl.read(gradeId);
	gpfDetails.setOrgGradeMst(orgGradeMst);
	//ended
	gpfDetails.setOrgPostMstByUpdatedByPost(orgPostMst);
	gpfDetails.setOrgUserMstByCreatedByUser(orgUserMst);				
	gpfDetails.setUpdatedDate(sysdate);
	//gpfDtls.update(gpfDetails);
}
//Ended by Abhilash










}
