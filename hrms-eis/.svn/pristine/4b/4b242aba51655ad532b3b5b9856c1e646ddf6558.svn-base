package com.tcs.sgv.eis.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.SimpleDateFormat;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.eis.dao.HrEisGdDAOImpl;
import com.tcs.sgv.eis.dao.HrEisGdMpgDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OtherDetailServiceHelper extends ServiceImpl{
	
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
	
	public OtherDetailServiceHelper(ServiceLocator serv)
	{
	super();
	this.serv=serv;
	}

	
	public void tranferEmployee(long postId,long orgEmpId, long userPostPk,boolean isTransfer, Date WEFDate,OrgEmpMst orgEmpMstEff) throws Exception
	{
		long locId =0;
		long ddoCode=0;
		long billNo=0;
		Long oldBill=null;
		long oldscaleId=1;
		Date sysDate = new Date();
		OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		HrEisGdDAOImpl eisGdMpgDAOImpl = new HrEisGdDAOImpl(HrEisGdMpg.class,serv.getSessionFactory());
		
		HrEisOtherDtls hrEIsOtherDtls = otherDetailDAOImpl.getOtherData(orgEmpId);
		
		if(hrEIsOtherDtls != null && hrEIsOtherDtls.getHrEisSgdMpg() !=null && hrEIsOtherDtls.getHrEisSgdMpg().getHrEisScaleMst() != null)
			oldscaleId = hrEIsOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleId();
		
		PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl  = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		OrgPostDetailsRlt newPostDatils = payBillDAOImpl.getOrgPostDtlsObj(postId);
		
		EmpDAOImpl employeeDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
		
		GenericDaoHibernateImpl gen= new GenericDaoHibernateImpl(OrgPostMst.class);
		gen.setSessionFactory(serv.getSessionFactory());
		com.tcs.sgv.ess.valueobject.OrgPostMst newOrgPostMst = (com.tcs.sgv.ess.valueobject.OrgPostMst)gen.read(postId);
		/*gen= new GenericDaoHibernateImpl(HrPayPsrPostMpg.class);
		gen.setSessionFactory(serv.getSessionFactory());
		*/
		
		
		GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
		GradDesgScaleMapDAO sgdDao = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
		
		List<HrEisGdMpg>  gdList= new ArrayList<HrEisGdMpg>();
		  logger.info("current date is "+sysDate);
		 
		  Calendar c = Calendar.getInstance();
		  
		  c.set(Calendar.YEAR, WEFDate.getYear()+1900);
		  c.set(Calendar.MONTH, WEFDate.getMonth());
		  c.set(Calendar.DATE,1);
		  c.set(Calendar.HOUR_OF_DAY,00);
		  c.set(Calendar.MINUTE,00);
		  c.set(Calendar.SECOND,00);
		  c.set(Calendar.MILLISECOND,0000);
		  
		  Timestamp timestamp = new Timestamp(c.getTime().getTime());
		  logger.info("modified time is  for start date "+timestamp);
		  Calendar d = Calendar.getInstance();
		  if(WEFDate.getMonth()==3 || WEFDate.getMonth()==5 || WEFDate.getMonth()==8 || WEFDate.getMonth()==10)
			  d.set(Calendar.DATE, 31);
		  else if(WEFDate.getMonth() == 2)
		  { if(WEFDate.getYear()%4==0)
			  	d.set(Calendar.DATE, 29);
		  	else
		  		d.set(Calendar.DATE, 28);
		  }
		  else
			  d.set(Calendar.DATE, 30);
		 
		  	 
		  if(WEFDate.getMonth()==0){
			  d.set(Calendar.YEAR,WEFDate.getYear()-1+1900);
			  d.set(Calendar.MONTH,11);
		  }
		  else{
			  d.set(Calendar.YEAR,WEFDate.getYear()+1900);
			  d.set(Calendar.MONTH,WEFDate.getMonth()-1);
		  }
		  logger.info("modified time is  for end  date is "+d.getTime());
		
		List postConigLst = otherDetailDAOImpl.getPostConfiguration(postId);
		
		try{
		for(int i=0;i<postConigLst.size();i++)
		{
			    Object arr[] = (Object[])postConigLst.get(i);
			    billNo = arr[0].toString() != null ? Long.valueOf(arr[0].toString()):0;
			    ddoCode = arr[1].toString() != null ? Long.valueOf(arr[1].toString()):0;
			    locId = arr[2].toString() != null ? Long.valueOf(arr[2].toString()):0;
			    gdList  = gradeDao.getGDMpgByDesigLoc(newPostDatils.getOrgDesignationMst().getDsgnId(),locId);
		}
		
		logger.info("bill no is "+billNo + "ddoCOde is "+ddoCode +" locationId is "+locId);
		//System.out.println("bill no is "+billNo + "ddoCOde is "+ddoCode +" locationId is "+locId);
		
		
		
		OrgUserpostRlt orgUserpostRlt =otherDetailDAOImpl.getEmplConfiguration(orgEmpId,isTransfer);
		if(ddoCode !=0 && locId != 0 && billNo!=0)
		{
			//unmap employee from old post
			if(orgUserpostRlt != null)
			{
				
			  /*HrPayPsrPostMpg hrPayPsrPostMpg =(HrPayPsrPostMpg)gen.read(orgUserpostRlt.getOrgPostMstByPostId().getPostId());
			  hrPayPsrPostMpg.setBillNo(oldBill);
			  gen.update(hrPayPsrPostMpg)*/; //un map old post from bill group 
			  
			  /*HrEisGdMpg hrEisGdMpg = eisGdMpgDAOImpl.getGradeDesigMpg(postId, locId); //to get grade of a new post
			  
			  OrgEmpMst orgEmpMst =(OrgEmpMst)employeeDAOImpl.read(orgEmpId);
			  orgEmpMst.setOrgGradeMst(hrEisGdMpg.getOrgGradeMst());
			  employeeDAOImpl.update(orgEmpMst);*///to update the grade of an employee according to new post's designation 
			  
			  
			  if(isTransfer==false){
			  gen= new GenericDaoHibernateImpl(OrgUserpostRlt.class);
			  gen.setSessionFactory(serv.getSessionFactory());
			  
			  
			  orgUserpostRlt.setActivateFlag(0);
			  orgUserpostRlt.setEndDate(d.getTime());
			  gen.update(orgUserpostRlt); //to make old post vacant
			  logger.info("PK of source orguserpostRlt is "+orgUserpostRlt.getEmpPostId());
			  }
			 
			}
			  HrEisGdMpg hrEisGdMpg = eisGdMpgDAOImpl.getGradeDesigMpg(postId, locId); //to get grade of a new post
			  if(hrEisGdMpg!=null)
			  logger.info("orgEmpId is********** "+orgEmpId);
			  {
			  OrgEmpMst orgEmpMst =(OrgEmpMst)employeeDAOImpl.read(orgEmpId);
			  orgEmpMst.setOrgGradeMst(hrEisGdMpg.getOrgGradeMst());
			  employeeDAOImpl.update(orgEmpMst); //to update the grade of an employee according to new post's designation 
			  }
			  
			//map an employee to new post
			  gen= new GenericDaoHibernateImpl(OrgUserpostRlt.class);
			  gen.setSessionFactory(serv.getSessionFactory());
			  
			//check whether new post is vacant or not
			  OrgUserpostRlt oldOrgUserpostRlt = otherDetailDAOImpl.checkVacant(postId);
			  if(oldOrgUserpostRlt==null) //post is vacant 
			  {
				  OrgUserpostRlt insert = new OrgUserpostRlt();
				  logger.info("Pk for userpostRlt is "+userPostPk);  
				  insert.setEmpPostId(userPostPk);
				  insert.setActivateFlag(1);
				  insert.setStartDate(timestamp);
				  insert.setEndDate(null);
				  insert.setOrgPostMstByPostId(newOrgPostMst);
				  insert.setOrgUserMst(orgEmpMstEff.getOrgUserMst());
				  insert.setOrgPostMstByCreatedByPost(newOrgPostMst);
				  insert.setOrgPostMstByUpdatedByPost(newOrgPostMst);
				  insert.setOrgUserMstByCreatedBy(orgEmpMstEff.getOrgUserMst());
				  insert.setOrgUserMstByUpdatedBy(orgEmpMstEff.getOrgUserMst());
				  insert.setCreatedDate(sysDate);
				  insert.setUpdatedDate(sysDate);
				  insert.setCmnLookupUserPostType((CmnLookupMst)cmnLookupMstDAOImpl.read(new Long(13)));
				  gen.create(insert);
				    
			  }
			  else // post is not vacant
			  {
				  oldOrgUserpostRlt.setOrgPostMstByPostId(newOrgPostMst);
				  oldOrgUserpostRlt.setOrgUserMst(orgEmpMstEff.getOrgUserMst());
				  oldOrgUserpostRlt.setActivateFlag(1);
				  oldOrgUserpostRlt.setStartDate(timestamp);
				  oldOrgUserpostRlt.setEndDate(null);
				  gen.update(oldOrgUserpostRlt);
			  }
			//update other deatails of an employee
			  if(gdList!=null && gdList.size()>0)
			  {
				  HrEisGdMpg eisGdMpg = gdList.get(0);
				  logger.info("GdId is"+eisGdMpg.getGdMapId() + " for new postId "+postId);
				  List<HrEisSgdMpg> sgdMpglist = sgdDao.getScaleGradeDesgMasterData( eisGdMpg.getGdMapId(),oldscaleId,locId);
					if(sgdMpglist!=null && sgdMpglist.size()>0){
						HrEisSgdMpg  sgdMpg = sgdMpglist.get(0);
						otherDetailDAOImpl.update(hrEIsOtherDtls);
						logger.info("EMpSgdId is"+hrEIsOtherDtls.getHrEisSgdMpg().getSgdMapId() + " for otherId "+hrEIsOtherDtls.getOtherId());
					}
			  }
			
			
			
			
			
			
		}
	}catch (Exception e) {
		// TODO: handle exception
		logger.error("Error is: "+ e.getMessage());
		logger.info("Exception occured tranferEmployee method of OtherDetialsServiceHelper Class"+e.getMessage());
		throw e;
	}
	}
	
	
}
