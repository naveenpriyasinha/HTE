package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.BatchITGpfDetailsUpdateDaoImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.service.GenerateBillService;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class BatchITGpfDetailsUpdateServiceImplHelper
{
	Log logger = LogFactory.getLog(getClass());
	ServiceLocator serv;
	
	public BatchITGpfDetailsUpdateServiceImplHelper() 
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

	public BatchITGpfDetailsUpdateServiceImplHelper(ServiceLocator serv) 
	{
		
		this.serv = serv;
	}
	
	//Added byAbhilash 
	public void updateBatchITDetails(List pkITids,long deductionDetailCode,int i,List ITlist,long loggedInUser,long loggedInpostId) throws Exception
	{
		Date sysDate= new Date();
		
		BatchITGpfDetailsUpdateDaoImpl BatchITDetailsUpdateDao = new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
		deductionDetailCode = Long.parseLong(pkITids.get(i).toString());
		logger.info("INSIDE UPDATE updateBatchITDetails   BatchITGpfDetailsUpdateServiceImplHelper");
		HrPayDeductionDtls updatingIT = BatchITDetailsUpdateDao.read(deductionDetailCode);
		if((ITlist.get(i).toString())!=null && !(ITlist.get(i).toString()).equals(""))
		{
			updatingIT.setEmpDeducAmount(Long.parseLong(ITlist.get(i).toString()));
			updatingIT.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
			updatingIT.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
			updatingIT.setUpdatedDate(sysDate);
			BatchITDetailsUpdateDao.update(updatingIT);
			 //to check for the proper number of records were updated or not
		}
	}
	
	public void updateInHrEisOtherDtls(List ITlist,int i,HrEisEmpMst hrEisEmpMst) throws Exception
	{
		
		logger.info("INSIDE UPDATE updateInHrEisOtherDtls   BatchITGpfDetailsUpdateServiceImplHelper");
		OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		HrEisOtherDtls otherdtls = new HrEisOtherDtls();
		List<HrEisOtherDtls> hrEisOtherDtls =  otherDao.getListByColumnAndValue("hrEisEmpMst", hrEisEmpMst);	
		otherdtls = hrEisOtherDtls.get(0);
		if(otherdtls!=null)
		{
		otherdtls.setIncomeTax(Long.parseLong(ITlist.get(i).toString()));
		otherDao.update(otherdtls);
		}
	}
	
	public void insertBatchITDetails(ServiceLocator serv,CmnLocationMst cmnLocationMst,int i,List ITlist,HrEisEmpMst hrEisEmpMst,long loggedInUser,long loggedInpostId,long deductionDetailCode,long dbId,HrPayDeductionDtls  hrPayDeductionDtls,long userId,long langId,long locId,long IncomeTax )
	{
		
		logger.info("INSIDE UPDATE insertBatchITDetails   BatchITGpfDetailsUpdateServiceImplHelper");
		Date sysDate= new Date();
		IdGenerator idGen = new IdGenerator();
		deductionDetailCode =  idGen.PKGeneratorWebService("hr_pay_deduction_dtls", serv, userId, langId, locId);
		logger.info("::::::::::::::::::::  deduction Detail Code Id ---> " + deductionDetailCode);
		GenericDaoHibernateImpl hrPayDeducTypeMstDaoImpl = new GenericDaoHibernateImpl(HrPayDeducTypeMst.class);
		hrPayDeducTypeMstDaoImpl.setSessionFactory(serv.getSessionFactory());
		HrPayDeducTypeMst hrPayDeducTypeMst =  (HrPayDeducTypeMst) hrPayDeducTypeMstDaoImpl.read(IncomeTax);
		CmnDatabaseMstDaoImpl cmnDatabaseMstDao = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst =  cmnDatabaseMstDao.read(dbId);
		BatchITGpfDetailsUpdateDaoImpl BatchITDetailsUpdateDao = new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
		
		hrPayDeductionDtls.setDeducDtlsCode(deductionDetailCode);
		hrPayDeductionDtls.setHrEisEmpMst(hrEisEmpMst);
		hrPayDeductionDtls.setCmnDatabaseMst(cmnDatabaseMst);
		hrPayDeductionDtls.setHrPayDeducTypeMst(hrPayDeducTypeMst);
		hrPayDeductionDtls.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
		hrPayDeductionDtls.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
		hrPayDeductionDtls.setCreatedDate(sysDate);
		hrPayDeductionDtls.setCmnLocationMst(cmnLocationMst);
		hrPayDeductionDtls.setEmpDeducAmount(Long.parseLong(ITlist.get(i).toString()));
		hrPayDeductionDtls.setTrnCounter(1);
		hrPayDeductionDtls.setEmpCurrentStatus(1L);
		//BatchITDetailsUpdateDao.create(hrPayDeductionDtls);
		  //to check for the proper number of records were updated or not
	}
	
	public void updateGPFDtls(ServiceLocator serv,List pkGpfids,int i,List Gpflist,long payVpfId,long loggedInUser,long loggedInpostId)
	{
		
		logger.info("INSIDE UPDATE updateGPFDtls   BatchITGpfDetailsUpdateServiceImplHelper");
		Date sysDate = new Date();
		GenericDaoHibernateImpl BatchGpfDetailsUpdateDao = new GenericDaoHibernateImpl(HrPayVpfDtls.class);
		BatchGpfDetailsUpdateDao.setSessionFactory(serv.getSessionFactory());
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
		
		if((pkGpfids.get(i))!=null && !(pkGpfids.get(i).equals("")))
		{
			payVpfId = Long.parseLong(pkGpfids.get(i).toString());
		}
		HrPayVpfDtls updatingGpf = (HrPayVpfDtls) BatchGpfDetailsUpdateDao.read(payVpfId);
		if((Gpflist.get(i).toString())!=null && !(Gpflist.get(i).toString()).equals(""))
		{
			updatingGpf.setVpfAmt(Long.parseLong(Gpflist.get(i).toString()));
			updatingGpf.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
			updatingGpf.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
			updatingGpf.setUpdatedDate(sysDate);
			//BatchGpfDetailsUpdateDao.update(updatingGpf);
			 //to check for the proper number of records were updated or not
		}
	}
	
	public void insertGPFDtls(ServiceLocator serv,long userId, long langId,long locId,List empIds,int i,HrPayVpfDtls hrPayVpfDtls,List Gpflist,CmnLocationMst cmnLocationMst,long loggedInUser,long loggedInpostId )
	{
		
		
		logger.info("INSIDE UPDATE insertGPFDtls   BatchITGpfDetailsUpdateServiceImplHelper");
		Date sysDate=new Date();
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
		EmpInfoDAOImpl empDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
		GenericDaoHibernateImpl BatchGpfDetailsUpdateDao = new GenericDaoHibernateImpl(HrPayVpfDtls.class);
		IdGenerator idgen = new IdGenerator();
		long payVpfId = idgen.PKGeneratorWebService("hr_pay_vpf_dtls", serv, userId, langId, locId);
		logger.info("::::::::::::::::::::  pay Vpf Id ---> " + payVpfId);
		HrEisEmpMst hrEisEmpMst = empDao.read(Long.parseLong(empIds.get(i).toString()));

		hrPayVpfDtls.setHrEisEmpMst(hrEisEmpMst);
		hrPayVpfDtls.setPayVpfId(payVpfId);
		hrPayVpfDtls.setVpfAmt(Long.parseLong(Gpflist.get(i).toString()));
		hrPayVpfDtls.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
		hrPayVpfDtls.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
		hrPayVpfDtls.setCreatedDate(sysDate);
		hrPayVpfDtls.setCmnLocationMst(cmnLocationMst);
		hrPayVpfDtls.setZerovpfMonths(3);
		//BatchGpfDetailsUpdateDao.create(hrPayVpfDtls);
		//to check for the proper number of records were updated or not
	}
	
	public List getBillList(ServiceLocator serv,long locId)
	{
		BatchITGpfDetailsUpdateDaoImpl BatchITDetailsUpdateDao = new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
		List billList = new ArrayList(); 
		List<HrPayBillHeadMpg> BillList = new ArrayList();		
		billList = BatchITDetailsUpdateDao.getBillListForDisplay(locId);
		for(Iterator itr=billList.iterator();itr.hasNext();)
		{    			
			Object[] row = (Object[])itr.next();
			HrPayBillHeadMpg hrPayBillHeadMpg = new HrPayBillHeadMpg();					
			hrPayBillHeadMpg.setBillId(Long.parseLong(row[0].toString()));
			hrPayBillHeadMpg.setBillHeadId(Long.parseLong(row[1].toString()));		 	
			BillList.add(hrPayBillHeadMpg);
		}   
		return BillList;
	}
	
	//Ended by Abhilash
	
	
	
	
	public void insertChangedRecord(HrEisEmpMst hrEisEmpMst,String BillNo, long locId,long dbId,long userId,long langId) throws Exception
	{
		BatchITGpfDetailsUpdateDaoImpl batchITGpfDetailsUpdateDaoImpl=new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
		long empID=hrEisEmpMst.getOrgEmpMst().getEmpId();
		List postIdUserID =batchITGpfDetailsUpdateDaoImpl.getPostIdUserId(empID);
		CmnDatabaseMstDaoImpl cmnDatabaseMstDao = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDao.read(dbId);
		GenericDaoHibernateImpl hrPayPayBillDao = new GenericDaoHibernateImpl(HrPayPaybill.class);
		hrPayPayBillDao.setSessionFactory(serv.getSessionFactory());
		
		Date sysDate = new Date();
		int month=sysDate.getMonth()+1;
		int year=sysDate.getYear()+1900;
		
		Object[] rowArr = (Object[]) postIdUserID.iterator().next();
		long postIdNew=Long.valueOf(rowArr[0].toString());
		long userIdNew=Long.valueOf(rowArr[1].toString());
		/*for(Iterator itr=postIdUserID.iterator();itr.hasNext();)
		{    
			Object[] row = (Object[])itr.next();
			postIdNew=Long.valueOf(row[0].toString());
			userIdNew=Long.valueOf(row[1].toString());
			break;
		}*/
		List resList=batchITGpfDetailsUpdateDaoImpl.getITGpfDataforUpdatingHrPayPayBill(BillNo,locId,empID);
		 //System.out.println("resList::::::::::::::size"+resList.size());
		 
		 HrPayPaybill hrPayPaybill=new GenerateBillService().readPaybillObjet(postIdNew,month,year,4,serv);
		 //System.out.println("hrPayPaybill    ::::::::::"+hrPayPaybill);
		 if(resList.size()>0 && hrPayPaybill!=null  )
		{
			
			//System.out.println(";;;;;;;;;;in if part;;;;;;;;;"+resList.size());
			logger.info(":::::::::::::::::in if part::::::::::::::::::::");
			Object[] row=(Object[])resList.iterator().next();
			
			logger.info("hrPayPaybill object;;;;;;;;;"+hrPayPaybill);
			logger.info("post Id is "+postIdNew);
			logger.info("emp ID is:"+empID);
			logger.info("user Id is "+userIdNew);
			
				double it=Long.parseLong(row[0].toString());
			 
				logger.info("it:::::::"+it);
				double pf=Long.parseLong(row[1].toString());
				logger.info("pf::::::::"+pf);
				String classType=row[2].toString();
				logger.info("classType:::::::::::::"+classType);
				hrPayPaybill.setIt(it);
				if(classType.equals("Class 4"))
				{
					hrPayPaybill.setDeduc9531(pf); 
				}
				else
				{
					hrPayPaybill.setDeduc9670(pf);
				}
				hrPayPayBillDao.update(hrPayPaybill);
	
			
		}
		else 
		{
			//System.out.println(";;;;;;;;;;in else part;;;;;;;;;;");
			logger.info(":::::::::::::::::in else part::::::::::::::::::::");
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMstNew = orgPostMstDaoImpl.read(postIdNew);
			OrgUserMst orgUserMstNew=orgUserMstDaoImpl.read(userIdNew);
			Map newMap=new HashMap();
			
			newMap.put("changedEmpId",empID);
			newMap.put("locId",locId);
			newMap.put("serviceLocator",serv);
			newMap.put("changedPostId",postIdNew);
			newMap.put("OrgPostMst",orgPostMstNew);
		    newMap.put("OrgUserMst",orgUserMstNew);
			newMap.put("cmnDatabaseMst",cmnDatabaseMst);
			CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
	    	 
	    	   
	    	
	    	   Map baseLoginMap = new HashMap();
	    	   newMap.put("singleTran","Y");
	    	   baseLoginMap.put("userId", userId);
	    	   baseLoginMap.put("langId", langId);
	    	   baseLoginMap.put("locationVO", cmn.getCmnLocationMst(locId));
	    	   newMap.put("baseLoginMap", baseLoginMap);
	    	   newMap.put("CmnLocationMstDst", cmn.getCmnLocationMst(locId));
			GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
			long changedRecordPK=generateBillServiceHelper.insertChangedRecord(newMap);
			//System.out.println("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
		
		}

	}
	
	
	public void insertChangedRecordGPF( HrEisEmpMst eisEmpMst,String BillNo, long locId,long dbId,long userId,long langId) throws Exception
	{
		BatchITGpfDetailsUpdateDaoImpl batchITGpfDetailsUpdateDaoImpl=new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
		long empID=eisEmpMst.getOrgEmpMst().getEmpId();
		List postIdUserID =batchITGpfDetailsUpdateDaoImpl.getPostIdUserId(empID);
		GenericDaoHibernateImpl hrPayPayBillDao = new GenericDaoHibernateImpl(HrPayPaybill.class);
		hrPayPayBillDao.setSessionFactory(serv.getSessionFactory());
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		CmnDatabaseMstDaoImpl cmnDatabaseMstDao = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDao.read(dbId);
		
		Date sysDate = new Date();
		int month=sysDate.getMonth()+1;
		int year=sysDate.getYear()+1900;
		 Object[] row =  (Object[])postIdUserID.iterator().next();
		   
			 
		long postIdNew=Long.valueOf(row[0].toString());
		long userIdNew=Long.valueOf(row[1].toString());
		 
		 
		 List resList=batchITGpfDetailsUpdateDaoImpl.getITGpfDataforUpdatingHrPayPayBill(BillNo,locId,empID);
		 HrPayPaybill hrPayPaybill=new GenerateBillService().readPaybillObjet(postIdNew,month,year,4,serv);
		if(resList.size()>0 && hrPayPaybill!=null)
		{
			//System.out.println(";;;;;;;;;;in if part;;;;;;;;;"+resList.size());
			logger.info(":::::::::::::::::in if part::::::::::::::::::::");
			
			    			
				//System.out.println(";;;;;;;;;;in for loop;;;;;;;;;;; ");
				 
				 row = (Object[])resList.iterator().next();
			
				
				
		
		
				 logger.info("hrPayPaybill object;;;;;;;;;"+hrPayPaybill);
				 logger.info("post Id is "+postIdNew);
				 logger.info("emp ID is:"+empID);
				 logger.info("user Id is "+userIdNew);
		
			double it=Long.parseLong(row[0].toString());
			//System.out.println("it:::::::"+it);
			logger.info("it:::::::"+it);
			double pf=Long.parseLong(row[1].toString());
			//System.out.println("pf::::::::"+pf);
			logger.info("pf::::::::"+pf);
			String classType=row[2].toString();
			//System.out.println("classType:::::::::::::"+classType);
			logger.info("classType:::::::::::::"+classType);
			//double gpf_c=Long.parseLong(row[1].toString());
			//double gpf_iv=Long.parseLong(row[2].toString());
				
				hrPayPaybill.setIt(it);
				if(classType.equals("Class 4"))
				{
					hrPayPaybill.setDeduc9531(pf); 
				}
				else
				{
					hrPayPaybill.setDeduc9670(pf);
				}
				hrPayPayBillDao.update(hrPayPaybill);
	
			
		}
		else
		{

			//System.out.println(";;;;;;;;;;in else part;;;;;;;;;;");
			logger.info(":::::::::::::::::in else part::::::::::::::::::::");
			OrgPostMst orgPostMstNew = orgPostMstDaoImpl.read(postIdNew);
			OrgUserMst orgUserMstNew=orgUserMstDaoImpl.read(userIdNew);
			Map newMap=new HashMap();
			
			newMap.put("changedEmpId",empID);
			newMap.put("locId",locId);
			newMap.put("serviceLocator",serv);
			newMap.put("changedPostId",postIdNew);
			newMap.put("OrgPostMst",orgPostMstNew);
		    newMap.put("OrgUserMst",orgUserMstNew);
			newMap.put("cmnDatabaseMst",cmnDatabaseMst);
			
			CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
	    	 
	    	   Map baseLoginMap = new HashMap();
	    	   newMap.put("singleTran","Y");
	    	   baseLoginMap.put("userId", userId);
	    	   baseLoginMap.put("langId", langId);
	    	   baseLoginMap.put("locationVO", cmn.getCmnLocationMst(locId));
	    	   newMap.put("baseLoginMap", baseLoginMap);
	    	   newMap.put("CmnLocationMstDst", cmn.getCmnLocationMst(locId));
			
			GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
			long changedRecordPK=generateBillServiceHelper.insertChangedRecord(newMap);
			//System.out.println("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
		}
		

	}
	
	
	
}
