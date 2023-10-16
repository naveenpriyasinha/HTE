package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDao;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.QuaterAprDAOImpl;
import com.tcs.sgv.eis.dao.QuaterMstDAOImpl;
import com.tcs.sgv.eis.valueobject.HrCustodianTypeMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrEisQuaterTypeMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PayrollCustomVO;
import com.tcs.sgv.eis.valueobject.QuarterApproval;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDao;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.dao.AddressDAOImpl;
import com.tcs.sgv.hod.common.delegate.AddressDelegate;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;



public class QuaterDtlsServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog( getClass() );
	int msg=0;		// Message code for the Insert or Update.
	/**
	 * Method Name:-	insertUpdateQuaterDtls.
	 * Author Name:-	Urvin Shah.
	 * Date:-			28/01/2008.
	 * Function:-		This method is used for Inserting or Updating the QuaterMaster Table.	
	 * @param objectArgs
	 * @return resultObject
	 */
	
	//Added by Demolisher
	public ResultObject loadQuaterAprData(Map objectArgs) throws Exception
	{
		logger.info("Entering into loadData of ZpAdminOfficeServiceImpl");
		Map loginMap = (Map) objectArgs.get("baseLoginMap"); 
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		//int postId1=Integer.parseInt(loginMap.get("primaryPostId").toString());
		long postId1 = postId-1;
		logger.info("Post ID:"+postId1);
		 QuaterAprDAOImpl  quaterAprDAOImpl = new QuaterAprDAOImpl(QuarterApproval.class,serviceLocator.getSessionFactory());
		 try {	
			
				long ddoCode=quaterAprDAOImpl.getDDOpostId(postId1);
			   List<QuarterApproval> quaterApprovalLst = quaterAprDAOImpl.getAllDDOOfficeDtlsData();
			   logger.info("zpdistrictOfficelst::"+quaterApprovalLst.size());
			   
				objectArgs.put("quaterApprovalLst",quaterApprovalLst);
				objRes.setResultValue(objectArgs);
				objRes.setViewName("staffAccomodationView");
				
				
			   }
				catch(Exception e)
				{		
					logger.info("Null Pointer Exception Ocuures...insertDistrictMPGDtls");
					logger.error("Error is: "+ e.getMessage());
					objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
					objRes.setResultValue(objectArgs);
					objRes.setViewName("errorInsert");		
				
				}
			
		
		return objRes;
	}	
	
	
	public ResultObject insertUpdateQuaterDtls(Map objectArgs) {
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		String editMode;
		long quarterType;
		long custodianTypeId;
		long quarterId;	// Quarter Id (Primary Key).
		long quarterIdu;	// Quarter Id (Primary Key).
		long userId;		// User Id of am Employee.
		long quarterRateType;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		
		
//		added by Ankit Bhatt for integrating with Outer
		 
		long hrrId = Long.parseLong(resourceBundle.getString("hrrId"));
		long hraId = Long.parseLong(resourceBundle.getString("hraId"));
		try{
			logger.info("You are in the Quater Details Service");
			Map loginMap = (Map) objectArgs.get("baseLoginMap"); 
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			           
            
            editMode = objectArgs.get("editMode").toString();
            GenericDaoHibernateImpl genericImpl = null;
            genericImpl = new GenericDaoHibernateImpl(HrEisQuaterTypeMst.class);
            genericImpl.setSessionFactory(serv.getSessionFactory());
            
            GenericDaoHibernateImpl genericImplForCustodian = null;
            genericImplForCustodian = new GenericDaoHibernateImpl(HrCustodianTypeMst.class);
            genericImplForCustodian.setSessionFactory(serv.getSessionFactory());
            
            HrEisQtrMst quaterMst = (HrEisQtrMst) objectArgs.get("hrEssQtrMst");	// The Object Of VOGen.
            //Added by Demolisher
            QuarterApproval quarterApproval = new QuarterApproval();
            //Added By varun For quarter rule
            int ownHouseFlag=Integer.parseInt(objectArgs.get("ownHouse").toString());
            logger.info("ownHouse Flag"+ownHouseFlag);
            //Ended By varun For quarter rule
            HrEisQtrMst hrEssQtrMst = null;
            QuaterMstDAOImpl quaterMstDAOImpl = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());
            
          //added by ravysh
			String FromBasicDtlsNew=objectArgs.get("FromBasicDtlsNew")!=null?(String)objectArgs.get("FromBasicDtlsNew"):"";
			String otherId=objectArgs.get("otherId")!=null?(String)objectArgs.get("otherId"):"";
			
			
			objectArgs.put("FromBasicDtlsNew",FromBasicDtlsNew);
			objectArgs.put("otherId",otherId);
            
            Date sysDate = new Date();
            
            userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			userId = Long.parseLong(objectArgs.get("userId").toString());
			logger.info("the user id of intilization"+userId);
			OrgUserMst objOrgUserMst=orgUserMstDaoImpl.read(userId);		// The User Id of the Person who is allocatin or vacating the Quarter.
    		
			long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long ddoCode;
			QuaterAprDAOImpl quarterMstDaoImpl = new QuaterAprDAOImpl(QuarterApproval.class,serv.getSessionFactory());
			logger.info("loginMap.get(locationId).toString() "+loginMap.get("locationId").toString());
			long locId=StringUtility.convertToLong(loginMap.get("locationId").toString());
			logger.info("after conver...");
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
		
			
			
			
			long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			//int postId1=Integer.parseInt(loginMap.get("primaryPostId").toString());
			//Added by Demolisher
			long postId1 = postId-1;
			logger.info("Post ID:"+postId1);
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			ddoCode=quarterMstDaoImpl.getDDOpostId(postId1);
			
            
            quarterType = Long.parseLong(objectArgs.get("quarterType").toString());
            custodianTypeId = Long.parseLong(objectArgs.get("custodianType").toString());
            HrEisQuaterTypeMst hrQuaterTypeMst = (HrEisQuaterTypeMst) genericImpl.read(quarterType);
            
            logger.info("custodianTypeId is***********"+custodianTypeId);
            HrCustodianTypeMst hrEisCustodianTypeMst = (HrCustodianTypeMst) genericImplForCustodian.read(custodianTypeId);
            
            String rdotypeOfAccom = objectArgs.get("rdotypeOfAccom").toString();   
            String quarterAllocatedToType = objectArgs.get("quarterAllocatedToType").toString(); 
            CmnLookupMstDAO lookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
            logger.info("===> for radio value :: "+rdotypeOfAccom+"===> quarterAllocatedToType ::"+quarterAllocatedToType);

            List<CmnLookupMst>  rdotypeOfAccomList =lookupMstDAO.getListByColumnAndValue("lookupName", rdotypeOfAccom);
            logger.info("rdotypeOfAccomList size is"+rdotypeOfAccomList.size());
            List<CmnLookupMst>  quarterAllocatedToTypeList = lookupMstDAO.getListByColumnAndValue("lookupName", quarterAllocatedToType);
          long rdotypeOfAccomlookupId =0;
          long quarterAllocatedToTypelookupId=0;
          if(rdotypeOfAccomList!=null && rdotypeOfAccomList.size()>0)
        	  rdotypeOfAccomlookupId = rdotypeOfAccomList.get(0).getLookupId();
          logger.info("rdotypeOfAccomlookupId  is"+rdotypeOfAccomlookupId);
          if(quarterAllocatedToTypeList!=null && quarterAllocatedToTypeList.size()>0)
          quarterAllocatedToTypelookupId = quarterAllocatedToTypeList.get(0).getLookupId();
          
          CmnLookupMst cmnLookupMst;
          CmnLookupMst cmnLookupMst1;
          cmnLookupMst = lookupMstDAO.read(rdotypeOfAccomlookupId);
          cmnLookupMst1 = lookupMstDAO.read(quarterAllocatedToTypelookupId);
            
          logger.info("==== rdotypeOfAccomlookupId====" + rdotypeOfAccomlookupId);
          logger.info("====quarterAllocatedToTypelookupId====" +quarterAllocatedToTypelookupId);
          
          
          logger.info("====setVacantOrderDate====" +quaterMst.getVacantOrderDate());
          
            if(editMode.equalsIgnoreCase("Y")&&editMode!=null)
            {
            	
            	quaterMst.setQuarterGovtType(cmnLookupMst);
            	quaterMst.setQuarterAllocatedToType(cmnLookupMst1);
            	
            	msg=1;
            	quarterId = Long.parseLong(objectArgs.get("quarterId").toString());
            	hrEssQtrMst  = quaterMstDAOImpl.read(quarterId);
            	hrEssQtrMst.setQuarterName(hrQuaterTypeMst.getQuaType());
            	
            	//Added by Abhilash for custodian
            	if(hrEisCustodianTypeMst!=null)
				{
            		logger.info("custodian id for updation is***********"+hrEisCustodianTypeMst.getCustodianId());
            		hrEssQtrMst.setHrCustodianTypeMst(hrEisCustodianTypeMst);
				}
            	
            	//Ended by Abhilash for custodian
            	
            	hrEssQtrMst.setHrEisQuaterTypeMst(hrQuaterTypeMst);
            	//if(quaterMst.getAllocationEndDate()!=null)
            	 hrEssQtrMst.setAllocationEndDate(quaterMst.getAllocationEndDate());
            	
            	if(quaterMst.getVacantLetterNo()!=null)
            		hrEssQtrMst.setVacantLetterNo(quaterMst.getVacantLetterNo());
            	
            	
                hrEssQtrMst.setVacantOrderDate(quaterMst.getVacantOrderDate());
            	hrEssQtrMst.setQuarterGovtType(quaterMst.getQuarterGovtType());
            	hrEssQtrMst.setQuarterAllocatedToType(quaterMst.getQuarterAllocatedToType());
				
				
            	hrEssQtrMst.setQuarterRent(quaterMst.getQuarterRent());
            	hrEssQtrMst.setServiceCharge(quaterMst.getServiceCharge());
            	hrEssQtrMst.setGarageCharge(quaterMst.getGarageCharge());
            	hrEssQtrMst.setOrgPostMstByUpdatedByPost(orgPostMst);
            	hrEssQtrMst.setOrgUserMstByUpdatedBy(orgUserMst);
            	hrEssQtrMst.setUpdatedDate(sysDate);
            	
            	AddressDAOImpl cmnAddressMstDaoImpl = new AddressDAOImpl(CmnAddressMst.class, serv.getSessionFactory());
    			
    			
//    			Update Address Sart
            	
            	 
            	if(objectArgs.get("quaterAddress")!=null)
            	{
            		logger.info("Inside the quater update");
            		CmnAddressMst cmnAddressMst = (CmnAddressMst) objectArgs.get("quaterAddress");
            		if(cmnAddressMst.getAddressId()==0)
            		{
            			objectArgs.put("mode", "add");
            			cmnAddressMst = AddressDelegate.setAddressObjectFields(cmnAddressMst, objectArgs);
            			long addressID = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
            			logger.info("Addressid for Quater : " + addressID);
            			cmnAddressMst.setAddressId(addressID);
            			try
            			{
            				cmnAddressMst.setOrgUserMstByCreatedBy(orgUserMst);
                    		cmnAddressMst.setCreatedDate(sysDate);
                    		cmnAddressMst.setOrgPostMstByCreatedByPost(orgPostMst);
            				cmnAddressMstDaoImpl.create(cmnAddressMst);
            				hrEssQtrMst.setCmnAddressMst(cmnAddressMst);
            			}
            			catch (Exception e)
            			{
            				logger.error("Error in INSERT FairFestivalAddress DETAILS");
            				logger.error("Error is: "+ e.getMessage());
            				resultObject.setThrowable(e);
            				resultObject.setResultCode(ErrorConstants.ERROR);
            				resultObject.setViewName("errorPage");
            				return resultObject;
            			}
            		}
            		else {
            			resultObject = serv.executeService("EDIT_ADDRESS_DETAILS", objectArgs);
            			objectArgs = (Map) resultObject.getResultValue(); 
            			cmnAddressMst = (CmnAddressMst) objectArgs.get("quaterAddress");
            			//logger.info("The updated Address Is:-"+cmnAddressMst.getArea()+" uii"+cmnAddressMst.getPincode());
            		}
            		
            		//logger.info("The City Name is:-"+hrEssQtrMst.getCmnAddressMst().getAddressId()+" fdfdf:-"+hrEssQtrMst.getCmnAddressMst().getStateName());
            	}
            	//logger.info("The City Name is:-"+hrEssQtrMst.getCmnAddressMst().getAddressId()+" fdfdf:-"+hrEssQtrMst.getCmnAddressMst().getStateName());
            	
    			
    			//Address End
    			
            	   	quaterMstDAOImpl.update(hrEssQtrMst);
            	   	
            	   	objectArgs.put("hrEssQtrMst", hrEssQtrMst);
            }
            else 
            {
            	
               
            	IdGenerator idgen=new IdGenerator();
				quarterId=idgen.PKGenerator("HR_EIS_QTR_EMP_MPG", objectArgs);
            	//quarterId=IDGenerateDelegate.getNextId("HR_EIS_QTR_EMP_MPG", objectArgs);
				quaterMst.setQuarterId(quarterId);								
				quaterMst.setHrEisQuaterTypeMst(hrQuaterTypeMst);
				//Added by Demolisher
				IdGenerator idgenu=new IdGenerator();
				quarterIdu=idgenu.PKGenerator("QTR_APR", objectArgs);
				quarterApproval.setSno(quarterIdu);
				
				
				//Added by Abhilash for custodian
				
				if(hrEisCustodianTypeMst!=null)
				{
					logger.info("custodian id for insertion is***********"+hrEisCustodianTypeMst.getCustodianId());
					quaterMst.setHrCustodianTypeMst(hrEisCustodianTypeMst);
				}
				
				//Ended by Abhialsh for custodian
				
				quaterMst.setOrgUserMstByAllocatedTo(objOrgUserMst);	
				logger.info("quarter type id is"+cmnLookupMst.getLookupName());
				quaterMst.setQuarterGovtType(cmnLookupMst);
				quaterMst.setQuarterAllocatedToType(cmnLookupMst1);
				quaterMst.setCmnDatabaseMst(cmnDatabaseMst);
				quaterMst.setCmnLocationMstByLocId(cmnLocationMst);
				quaterMst.setOrgPostMstByCreatedByPost(orgPostMst);
				quaterMst.setOrgUserMstByCreatedBy(orgUserMst);
				quaterMst.setCreatedDate(sysDate);
				quaterMst.setTrnCounter(new Integer(1));
				
				//Added by Demolisher
				quarterApproval.setEmpId(userId);
				quarterApproval.setQuarterId(quarterApproval.getSno());
				quarterApproval.setRltDDO(ddoCode);
				quarterApproval.setStatusFlag('P');
				quarterMstDaoImpl.create(quarterApproval);
				
               // Address Start
    			AddressDAOImpl cmnAddressMstDaoImpl = new AddressDAOImpl(CmnAddressMst.class, serv.getSessionFactory());
    			CmnAddressMst cmnAddressMst = (CmnAddressMst) objectArgs.get("quaterAddress");
    			logger.info("==== cmnAddressMst ===="+cmnAddressMst);

    			if (cmnAddressMst != null)
    			  {
    				  logger.info("==== cmnAddressMst is not null====");
    				  objectArgs.put("mode", "add");
    				  cmnAddressMst = AddressDelegate.setAddressObjectFields(cmnAddressMst, objectArgs);

    				  long addressID = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", objectArgs);
    				  logger.info("Addressid for Quater : " + addressID);
    				  cmnAddressMst.setAddressId(addressID);

    	            try
    	            {
    	            	cmnAddressMstDaoImpl.create(cmnAddressMst);
    	            	quaterMst.setCmnAddressMst(cmnAddressMst);

    	            }
    	            catch (Exception e)
    	            {
    	                logger.error("Error in INSERT FairFestivalAddress DETAILS");
    	                logger.error("Error is: "+ e.getMessage());
    	                resultObject.setThrowable(e);
    	                resultObject.setResultCode(ErrorConstants.ERROR);
    	                resultObject.setViewName("errorPage");
    	                return resultObject;
    	            }
    			  }
    			//Address End
    			//10 jan 2012 allocation date to be set of cuurent month
    			Calendar calGiven = Calendar.getInstance();

    			calGiven.set(Calendar.DAY_OF_MONTH, 1);
    			calGiven.set(Calendar.HOUR_OF_DAY, 0);
    			calGiven.set(Calendar.MINUTE, 0);
    			calGiven.set(Calendar.SECOND, 0);
    			calGiven.set(Calendar.MILLISECOND, 0);
    			
    			/*Date quarterStartDate =  calGiven.getTime();
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");*/
    			//quaterMst.setAllocationStartDate(quarterStartDate);
    			quaterMst.setAllocationStartDate((Date)objectArgs.get("dtAllocStartDate"));
    			//end 10 jan 2012
				quaterMstDAOImpl.create(quaterMst);				
            }
            
//          Added By Varun
		 	  
		      EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			  long langId=StringUtility.convertToLong(loginMap.get("langId").toString());
		      OrgEmpMst orgEmpMst = orgEmpDao.getEmpFromUser(orgUserMstDaoImpl.read(userId), langId);
			  logger.info("EmpId is"+orgEmpMst.getEmpId());
			  HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
			  OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			  hrEisOtherDtls = otherDetailDAOImpl.getOtherData(orgEmpMst.getEmpId());
			 // hrEisOtherDtls.setOwnHouse(ownHouseFlag);
			  hrEisOtherDtls.setUpdatedDate(sysDate);
			  otherDetailDAOImpl.update(hrEisOtherDtls);
			  
	     //Ended By Varun
            //added by Samir Joshi for Hra & hrr Update in other detail
           // List<HrEisOtherDtls> otherDtlVo=new ArrayList<HrEisOtherDtls>();
            //OtherDetailDAOImpl OtherdetailDao=new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
            userId=Long.parseLong(objectArgs.get("userId").toString());
    		//Calendar calGiven = Calendar.getInstance();
    		//Date givenDate = calGiven.getTime();
    		

    		
    		//Calendar calc = Calendar.getInstance();
            logger.info("userid is=====>"+userId);
            //logger.info("Service  Charge for Emp-->"+hrEisOtherDtls.getHrEisEmpMst().getEmpId()+"     is--->Rs"+quaterMst.getServiceCharge() );
            
            //Added by Amish to Enter Service Charge in Deduction Table through Quarter Screen
            DeductionDtlsDAOImpl deductionDtlsDAOImpl = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
            if(hrEisOtherDtls != null && quaterMst != null){
            	HrPayDeductionDtls hrEmpMpg = deductionDtlsDAOImpl.getHrPayDeductionDtls(hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId(),80L);
            	if(hrEmpMpg != null && hrEmpMpg.getHrEisEmpMst() != null){
            		hrEmpMpg.setEmpDeducAmount(quaterMst.getServiceCharge());
            		logger.info("hrEmpMpg.getAmount--"+hrEmpMpg.getEmpDeducAmount());
            		deductionDtlsDAOImpl.update(hrEmpMpg);
            	}
            	else{
            		GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(HrPayDeducTypeMst.class);
            		genDAO.setSessionFactory(serv.getSessionFactory());
            		hrEmpMpg= new HrPayDeductionDtls();
            		IdGenerator idGen = new IdGenerator();

            		long deducCode = idGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objectArgs);
            		//allowCode = IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG", objectArgs);
            		logger.info("deducCode:::"+deducCode);
            		hrEmpMpg.setDeducDtlsCode(deducCode);
            		hrEmpMpg.setEmpDeducAmount(quaterMst.getServiceCharge());
            		hrEmpMpg.setHrPayDeducTypeMst((HrPayDeducTypeMst)genDAO.read(80L));
            		hrEmpMpg.setHrEisEmpMst(hrEisOtherDtls.getHrEisEmpMst());
            		hrEmpMpg.setEmpCurrentStatus(1);
            		hrEmpMpg.setTrnCounter(1);
            		hrEmpMpg.setCmnDatabaseMst(cmnDatabaseMst);
            		hrEmpMpg.setCmnLocationMst(cmnLocationMst);
            		hrEmpMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
            		hrEmpMpg.setOrgUserMstByCreatedBy(orgUserMst);
            		hrEmpMpg.setOrgPostMstByCreatedByPost(orgPostMst);
            		hrEmpMpg.setOrgUserMstByUpdatedBy(orgUserMst);
            		hrEmpMpg.setMonth(-1);
            		hrEmpMpg.setYear(-1);
            		hrEmpMpg.setCreatedDate(new Date());
            		hrEmpMpg.setUpdatedDate(new Date());
            		logger.info("hrEmpMpg.getAmount in create--"+hrEmpMpg.getEmpDeducAmount());
            		deductionDtlsDAOImpl.create(hrEmpMpg);
            	}
            }
            //Ended By Amish
            //otherDtlVo=OtherdetailDao.getAllOtherEmpDataHrr(userId);
            //logger.info("the size of otherdetail vo is=====>"+otherDtlVo.size());
            
        
            /* if(otherDtlVo!=null && otherDtlVo.size()>0){
        	Parser parExpression = new Parser();
    		Date sysdate = new Date();
    	

    		ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(com.tcs.sgv.allowance.valueobject.HrPayComponentMst.class,serv.getSessionFactory());
    		List<com.tcs.sgv.allowance.valueobject.HrPayComponentMst> componentList =componentDAO.getListByColumnAndValue("componentDesc", "DP");
    		

            

    		RevisedHrrAndHra revisedHrrAndHra = new RevisedHrrAndHra();
    		objectArgs.put("userIdForQtr",userId);
    		int monthGiven=revisedHrrAndHra.monthofDate(givenDate);
    		int yearGiven=revisedHrrAndHra.yearofDate(givenDate);
    		int maxDay = calGiven.getActualMaximum(5);
    		objectArgs.put("monthGiven",monthGiven);
    		objectArgs.put("yearGiven",yearGiven);
    		
    		Map hrrAndHraMap = revisedHrrAndHra.calculateHrrAndHra(objectArgs);
    		
    		List quaterIdList = (List)hrrAndHraMap.get("quaterIdList");
    		List quaterDaysList = (List)hrrAndHraMap.get("quaterDaysList");
    		List lstQuarterRate = (List)hrrAndHraMap.get("lstQuarterRate");
            
    		hrrAndHraMap.put("otherDtlsVO", otherDtlVo.get(0));
    		String dpRule = componentList.get(0).getExpression();
			
			double dpValue =0;
			
			if(!dpRule.equals(""))
			{
				dpRule = dpRule.replaceAll("BASIC",String.valueOf(otherDtlVo.get(0).getotherCurrentBasic()));
				
				logger.info(" the dpRule is "+dpRule);
				
				dpValue = parExpression.stringParse(dpRule);
				
				logger.info("the dpValue is "+dpValue);
			}
			
			hrrAndHraMap.put("dpValue", dpValue);
			
			Map resultHrrAndHra = revisedHrrAndHra.calculateRevisedHraAndHra(hrrAndHraMap);
			
			double hrr =(Double)resultHrrAndHra.get("hrr");
			
			//Ended By Varun
			double oldHra=(Double)resultHrrAndHra.get("oldHra");
			double revisedHra=(Double)resultHrrAndHra.get("revisedHra");

			DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			HrPayDeductionDtls empDeducDtls = new HrPayDeductionDtls();			
			List<HrPayDeductionDtls> deducList = empDuducDtlsDAO.getDeductionDtls(otherDtlVo.get(0).getHrEisEmpMst().getOrgEmpMst().getEmpId(), hrrId);
			
			if(deducList!=null && deducList.size()>0)
			{
				HrPayDeductionDtls deducDtlsVo = deducList.get(0);
				
				deducDtlsVo.setEmpDeducAmount(hrr);
				deducDtlsVo.setOrgPostMstByUpdatedByPost(orgPostMst);
				deducDtlsVo.setOrgUserMstByUpdatedBy(orgUserMst);
				deducDtlsVo.setUpdatedDate(sysdate);
				
				logger.info("going to update hrr revised one "+hrr);
				
				empDuducDtlsDAO.update(deducDtlsVo);
				
				logger.info("updated hrr revised one ");
			}
			EmpAllwMapDAOImpl empAllwMapDao = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			HrPayEmpallowMpg empAllowMpgVo = empAllwMapDao.getHrPayEmpallowMpg(otherDtlVo.get(0).getHrEisEmpMst().getOrgEmpMst().getEmpId(), hraId);
			if(empAllowMpgVo!=null )
			{
				
				empAllowMpgVo.setEmpAllowAmount(revisedHra);
				
				empAllowMpgVo.setOrgPostMstByUpdatedByPost(orgPostMst);
				empAllowMpgVo.setOrgUserMstByUpdatedBy(orgUserMst);
				empAllowMpgVo.setUpdatedDate(sysdate);
				
				logger.info("going to update hra revised one "+revisedHra);
				
				empAllwMapDao.update(empAllowMpgVo);
				
				logger.info("updated hra revised one ");
			}
			
           }*/
           
          
            
            
            
            
    		////ended by samir joshi
            //List quarterDtlsList = quaterMstDAOImpl.list();
            //List quarterDtlsList = quaterMstDAOImpl.getAllQuarterDetailsByLocationId(0, locId,langId);
            List quarterDtlsList = new ArrayList();
			objectArgs.put("quarterDtlsList", quarterDtlsList);
			
			//Added By Abhilash
			
			String ddocode ="";
			String TanNo="";
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
			OrgDdoMst ddoMst = null; 
    		if(ddoCodeList!=null && ddoCodeList.size()>0){
    			ddoMst = ddoCodeList.get(0);
    		}
    		
    		if(ddoMst!=null) {
    			ddocode=ddoMst.getDdoCode();
    			TanNo=ddoMst.getTanNo();
    		}
    		logger.info("custodian DDO Code is " + ddocode);
    		
    		
			/*String TresuryName ="";
			String TresuryCode ="";
			List TreasuryList = payBillDAO.getTreasuryCodeAndTreasuryName(ddocode);
			
			if(TreasuryList!=null && TreasuryList.size()!=0)
			{
				for(Iterator itr=TreasuryList.iterator();itr.hasNext();)
				{

					Object[] rowList = (Object[])itr.next();

					if(rowList[0] != null)
					{
						TresuryName = rowList[0].toString().trim();
					}
					if(rowList[1] != null)
					{
						TresuryCode = rowList[1].toString().trim();
					}

				}
			}
			
			logger.info("custodian TresuryCode *********"+TresuryCode);
			logger.info("custodian TresuryName *********"+TresuryName);
			*/
			List custodianList = quaterMstDAOImpl.getCustodianList(null);
			logger.info("custodian list size is *********"+custodianList.size());
			objectArgs.put("custodianList",custodianList);
			objectArgs.put("custodianListSize",custodianList.size());
			//Ended By Abhilash
			
            resultObject.setResultCode(ErrorConstants.SUCCESS);		
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			resultObject.setResultValue(objectArgs);
			//resultObject.setViewName("empViewList");
			if(msg==1)
				resultObject.setViewName("quarterDetailsMaster");
			else
				resultObject.setViewName("quarterDetailsMaster");
			logger.info("INSETED SUCCESSFULLY");
           
		}
		catch(Exception e) {
			logger.error("Error is: "+ e.getMessage());			
			objectArgs.put("MESSAGECODE",1007);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}
	
	/**
	 * Method Name:-		getQuarterDtls.
	 * Author Name:-		Urvin Shah.
	 * Date:-				28-01-2008
	 * Function:-			This method is fetching the quater Details in the View,Add and Update mode.
	 * @param objectArgs
	 * @return resultObject
	 */
	
	public ResultObject getQuarterDtls(Map objectArgs) {
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		String editMode;
		long quarterType;
		long quarterId;		// Quarter Id (Primary Key).
		long userId=0;		// User Id of am Employee.
	
		try{
			logger.info("You are in the Quater Details Service");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			editMode = ((voToService.get("edit").toString()!=null )?voToService.get("edit").toString():"");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locationId = Long.parseLong(loginMap.get("locationId").toString());
			logger.info("The Lcation Id is:-"+locationId); 
			CmnLocationMstDao cmnLocationMstDao = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDao.read(locationId);
			
			String empname="";
			if((voToService.get("Employee_srchNameText_EmpQtrSearch")!=null&&!voToService.get("Employee_srchNameText_EmpQtrSearch").equals("")))
			empname=(String)voToService.get("Employee_srchNameText_EmpQtrSearch").toString();
			
			//added by ravi	      			
			String editFlag = (String)voToService.get("edit");
				
			String FromBasicDtlsNew = voToService.get("FromBasicDtlsNew")!=null?(String)voToService.get("FromBasicDtlsNew"):"";
			
			String otherId = voToService.get("otherId")!=null?(String)voToService.get("otherId"):"";
			
			
			
			logger.info("editFromVO in quarter service " + voToService.get("edit"));
			long UserId =0;
			QuaterMstDAOImpl quaterMstDAOImpl = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());
            
			if(editFlag != null && editFlag.equals("Y")) 
			{
            	long QuarterId = Long.parseLong((String)voToService.get("quarterId"));
            	List<HrEisQtrMst> actionList = (List)quaterMstDAOImpl.getQuarterDtlsFromQuarterId(QuarterId);
            	HrEisQtrMst hrEssQtrMst = new HrEisQtrMst();
            	String empName="";
            	List quaterRateTypeList = new ArrayList();
            	
            	
            	if(actionList.get(0)!=null && actionList.size()>0) {
        			hrEssQtrMst = actionList.get(0); 
        			logger.info("quarter Name is " + hrEssQtrMst.getQuarterName());            	
        			/*String Quartername= hrEssQtrMst.getQuarterName();
        			Date QuarterAllocStartDate = hrEssQtrMst.getAllocationStartDate();
        			Date QuarterAllocEndDate = hrEssQtrMst.getAllocationEndDate();
            		long QuarterType = hrEssQtrMst.getHrQuaterTypeMst().getQuaId();
            		long RateType= hrEssQtrMst.getRateTypeLookup().getLookupId();*/
            		UserId = hrEssQtrMst.getOrgUserMstByAllocatedTo().getUserId();
            		EmpDAOImpl empdao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
            		empName = empdao.getAllEmployees(UserId,langId).get(0).getEmpFname() +" "+  (empdao.getAllEmployees(UserId,langId).get(0).getEmpMname()!=null?empdao.getAllEmployees(UserId,langId).get(0).getEmpMname():"") +" "+ empdao.getAllEmployees(UserId,langId).get(0).getEmpLname(); 
            		/*String EmpFname = empdao.getAllEmployees(UserId).get(0).getEmpFname();
            		String EmpMname= empdao.getAllEmployees(UserId).get(0).getEmpMname();
            		String EmpLname= empdao.getAllEmployees(UserId).get(0).getEmpLname();*/
            		CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
            		quaterRateTypeList = cmnLookupMstDAO.getAllChildrenByLookUpNameAndLang("QUARTER_RATE_TYPE",langId);
            		
            		
                    //Added By Varun
            		
            		HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
            		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
            		EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
            		OrgEmpMst orgEmpMst = orgEmpDao.getEmpFromUser(orgUserMstDaoImpl.read(UserId), langId);
  				    logger.info("EmpId is"+orgEmpMst.getEmpId());
            		OtherDetailDAOImpl otherDetailDAOImpl = new  OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
            		hrEisOtherDtls = otherDetailDAOImpl.getOtherData(orgEmpMst.getEmpId());
            		
            		String sevarthId = hrEisOtherDtls.getHrEisEmpMst().getSevarthEmpCode();
            		logger.info("sevarthId in Quater:::::::::::::::::"+sevarthId);
            		
            		//int ownHouseFlag = hrEisOtherDtls.getOwnHouse();
            		//logger.info("Flag is"+ownHouseFlag);
            		           		
            		//Ended By varun
            		// Added By Urvin For Address Componenet
            		AddressDAOImpl ldAdddaoimpl=new AddressDAOImpl(CmnAddressMst.class,serv.getSessionFactory());

        			if(actionList.get(0).getCmnAddressMst() != null)
        			{
        				CmnAddressMst cmnAddressMst =ldAdddaoimpl.getAddress(actionList.get(0).getCmnAddressMst().getAddressId());
        				logger.info("Address id is  QuaterAddress==========" + cmnAddressMst.getAddressId());
        				long addressId = cmnAddressMst.getAddressId();
        				objectArgs.put("addressId",addressId);
        				objectArgs.put("addrName", "quaterAddress");
        				resultObject=serv.executeService("GET_ADDRESS_VO", objectArgs);
        			}
        			//objectArgs.put("ownHouseFlag", ownHouseFlag);//Added By Varun For quarter rule
        			long empId=orgEmpMst.getEmpId();
        			objectArgs.put("empId", empId);
        			objectArgs.put("sevarthId", sevarthId);
        			       			
            	}	
        		    // End.		
        	   
    			objectArgs.put("hrEssQtrMst", hrEssQtrMst);
    			
    			objectArgs.put("UserId", UserId);
    			objectArgs.put("empName", empName);
    			
    			objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
    			objectArgs.put("otherId", otherId);
    			objectArgs.put("QuarterId", QuarterId);
    			/*objectArgs.put("Quartername", Quartername);
    			objectArgs.put("QuarterAllocStartDate",QuarterAllocStartDate );
    			objectArgs.put("QuarterAllocEndDate", QuarterAllocEndDate);
    			objectArgs.put("QuarterType", QuarterType);
    			objectArgs.put("RateType", RateType);
    			objectArgs.put("UserId", UserId);
    			objectArgs.put("QuarterId", QuarterId);
    			objectArgs.put("EmpFname",EmpFname) ;
    			
    			objectArgs.put("EmpMname", EmpMname);
    			objectArgs.put("EmpLname", EmpLname);*/
    			objectArgs.put("quaterRateTypeList",quaterRateTypeList);
    			
    			//Added By Abhilash
    			String ddocode ="";
				String TanNo="";
				PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
    			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locationId);
    			OrgDdoMst ddoMst = null; 
	    		if(ddoCodeList!=null && ddoCodeList.size()>0){
	    			ddoMst = ddoCodeList.get(0);
	    		}
	    		
	    		if(ddoMst!=null) {
	    			ddocode=ddoMst.getDdoCode();
	    			TanNo=ddoMst.getTanNo();
	    		}
	    		logger.info("custodian DDO Code is " + ddocode);
	    		
	    		
				/*String TresuryName ="";
				String TresuryCode ="";
				List TreasuryList = payBillDAO.getTreasuryCodeAndTreasuryName(ddocode);
				
				if(TreasuryList!=null && TreasuryList.size()!=0)
				{
					for(Iterator itr=TreasuryList.iterator();itr.hasNext();)
					{

						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							TresuryName = rowList[0].toString().trim();
						}
						if(rowList[1] != null)
						{
							TresuryCode = rowList[1].toString().trim();
						}

					}
				}
				
				logger.info("custodian TresuryCode *********"+TresuryCode);
				logger.info("custodian TresuryName *********"+TresuryName);*/
    			List custodianList = quaterMstDAOImpl.getCustodianList(null);
    			logger.info("custodian list size is *********"+custodianList.size());
    			objectArgs.put("custodianList",custodianList);
    			objectArgs.put("custodianListSize",custodianList.size());
    			
    			//Ended By Abhilash
    			
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objectArgs);
		       
		      //added by ravysh
		        if(FromBasicDtlsNew.equals("YES"))
					resultObject.setViewName("quarterDetailsEditMasterBasicDtls");
				else
		        resultObject.setViewName("quarterDetailsEditMaster");
		     //ended by ravi            		  
        }
		
			

			//GenericDaoHibernateImpl genericDao = new GenericDaoHibernateImpl(HrQuaterTypeMst.class);
			OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
    		long languageId=1;
    		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(languageId);
			StringBuffer empNameBf=new StringBuffer();
			String strEmpId="";
			String strUserId ="";
			String[] strArgList = {"orgUserMstByAllocatedTo","cmnLocationMstByLocId"};
			
			
			strUserId = (String)(voToService.get("USER_ID_EmpQtrSearch")!=null?voToService.get("USER_ID_EmpQtrSearch"):"");
			logger.info("The Value of the strUserId is :-"+strUserId);
			if(!strUserId.equals(""))
			{
				HrEisQtrMst hrEssQtrMst = new HrEisQtrMst();
				OrgUserMstDao orgUserMstDao = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				logger.info("user id is:-"+strUserId);
				OrgUserMst orgUserMst = orgUserMstDao.read(Long.parseLong(strUserId));
				Object[] objArgList = {orgUserMst,cmnLocationMst};
				//List<HrEssQtrMst> lstQuarterDtls = quaterMstDAOImpl.getListByColumnAndValue("orgUserMstByAllocatedTo",orgUserMst);
				//List<HrEssQtrMst> lstQuarterDtls = quaterMstDAOImpl.getListByColumnAndValue(strArgList,objArgList);
				List<HrEisQtrMst> lstQuarterDtls = quaterMstDAOImpl.getAllQuarterDetailsByLocationId(orgUserMst.getUserId(), locationId,langId);
				List payrollCustomVOList = new ArrayList();
				PayrollCustomVO payrollCustomVO;		
				//logger.info("user id is:-"+hrEssQtrMst.getOrgUserMstByAllocatedTo().getUserId());
				Iterator itrQuarter = lstQuarterDtls.iterator();
				//Object[] rowList = new Object() ;
				while(itrQuarter.hasNext()){
					payrollCustomVO = new PayrollCustomVO();
					Object[] rowList = (Object[]) itrQuarter.next();
					payrollCustomVO.setHrEssQtrMst((HrEisQtrMst)rowList[0]);
					payrollCustomVO.setOrgEmpMst((OrgEmpMst)rowList[1]);
					logger.info("The Quarter name is:-"+payrollCustomVO.getHrEssQtrMst().getQuarterName()+" and Quarter Type is :-"+payrollCustomVO.getHrEssQtrMst().getHrQuaterTypeMst().getQuaType());
					logger.info("Employee Name:-"+payrollCustomVO.getOrgEmpMst().getEmpFname()+" "+payrollCustomVO.getOrgEmpMst().getEmpMname()+" "+payrollCustomVO.getOrgEmpMst().getEmpLname());
					payrollCustomVOList.add(payrollCustomVO);
				}
				/*for (Object o : lstQuarterDtls){
					hrEssQtrMst = (HrEssQtrMst)o;
					payrollCustomVO = new PayrollCustomVO();
					OrgEmpMst orgEmpMst = empDAOImpl.getEmpFromUser(hrEssQtrMst.getOrgUserMstByAllocatedTo(),langId);
					payrollCustomVO.setHrEssQtrMst(hrEssQtrMst);
					payrollCustomVO.setOrgEmpMst(orgEmpMst);				
					logger.info("Quarter Type name is:-"+payrollCustomVO.getHrEssQtrMst().getHrQuaterTypeMst().getQuaType());
					payrollCustomVOList.add(payrollCustomVO);
				}*/
				logger.info("Custom VO size is:-"+payrollCustomVOList.size());
				objectArgs.put("quarterDtlsList", payrollCustomVOList);
				objectArgs.put("empName",empname);
				objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
				objectArgs.put("otherId", otherId);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				
				
				//added by ravysh
				if(FromBasicDtlsNew.equals("YES"))
					resultObject.setViewName("quarterDetailsViewBasicDtls");
				else
				resultObject.setViewName("quarterDetailsView");
			}
			else if(editMode.equalsIgnoreCase("Y")&&editMode!= null)
			{			
				quarterId = Long.parseLong(voToService.get("quarterId").toString());
				HrEisQtrMst hrEssQtrMst = quaterMstDAOImpl.read(quarterId);				
				List quaterTypeList = otherDetailDAOImpl.getQuaterType(langId);
				userId= hrEssQtrMst.getOrgUserMstByAllocatedTo().getUserId();
				OrgEmpMst orgEmpMst = empDAOImpl.getEmpFromUser(hrEssQtrMst.getOrgUserMstByAllocatedTo(), langId);				
				
				objectArgs.put("orgEmpMst", orgEmpMst);
				objectArgs.put("hrEssQtrMst", hrEssQtrMst);
				objectArgs.put("quaterTypeList", quaterTypeList);
				
				//Added By Abhilash
				String ddocode ="";
				String TanNo="";
				PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locationId);
				OrgDdoMst ddoMst = null; 
	    		if(ddoCodeList!=null && ddoCodeList.size()>0){
	    			ddoMst = ddoCodeList.get(0);
	    		}
	    		
	    		if(ddoMst!=null) {
	    			ddocode=ddoMst.getDdoCode();
	    			TanNo=ddoMst.getTanNo();
	    		}
	    		logger.info("custodian DDO Code is " + ddocode);
	    		
	    		
				/*String TresuryName ="";
				String TresuryCode ="";
				List TreasuryList = payBillDAO.getTreasuryCodeAndTreasuryName(ddocode);*/
				
				/*if(TreasuryList!=null && TreasuryList.size()!=0)
				{
					for(Iterator itr=TreasuryList.iterator();itr.hasNext();)
					{

						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							TresuryName = rowList[0].toString().trim();
						}
						if(rowList[1] != null)
						{
							TresuryCode = rowList[1].toString().trim();
						}

					}
				}
				
				logger.info("custodian TresuryCode *********"+TresuryCode);
				logger.info("custodian TresuryName *********"+TresuryName);*/
    			List custodianList = quaterMstDAOImpl.getCustodianList(null);
    			logger.info("custodian list size is *********"+custodianList.size());
    			objectArgs.put("custodianList",custodianList);
    			//Ended By Abhilash
    			
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				//resultObject.setViewName("quarterDetailsEditMaster");
				
			}
			else if(editMode.equalsIgnoreCase("N")&&editMode!= null)
			{
				if(voToService!=null&&voToService.get("userId")!=null)
				{
				 strEmpId = voToService.get("userId").toString();}
				if(!strEmpId.equals(null) && !strEmpId.equals(""))
					userId=Long.parseLong(strEmpId);		
				logger.info("The Employee Id is:-"+userId);
				long empType=0;
				if(userId!=0)
				{
					EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
	                List<HrEisEmpMst> empList =  empinfoDao.getEmpIdData(userId);
	                for(int j=0;j<empList.size();j++)
	                {
	                	
	                	HrEisEmpMst hreisEmpMst =new HrEisEmpMst();
	                	HrEisEmpMst hrEisempMstgu =new HrEisEmpMst();            	
	                	hreisEmpMst=empList.get(j);
	                	if(hreisEmpMst.getEmpId()==userId)
	                	{
	                	empType=hreisEmpMst.getEmpType();
	                	break;
	                	}
	                }
                
	                
	                Date 	endDate = null;	
	                List<HrEisQtrMst> empQtrList=quaterMstDAOImpl.getQuarterDtls(userId);
	                logger.info("Size of the List of Quarters for Emp is ***"+empQtrList.size());
	               
	               
	                long empIdTemp=quaterMstDAOImpl.getEmpIdFromUserId(userId);
	                
	                HrEisOtherDtls hrEisOtherDtls=otherDetailDAOImpl.getOtherDataByHrEis(empIdTemp);
	                
	                logger.info("Employee Id from UserId is:---"+empIdTemp);
	                
	                int isAvailedHRA=hrEisOtherDtls.getIsAvailedHRA();
	                empNameBf.append("<empNameMapping>");
	                empNameBf.append("<empType>").append(empType).append("</empType>");
	                if(isAvailedHRA==0)
	                {
	                	empNameBf.append("<availedHRA>").append(0).append("</availedHRA>");
	                	
	                	
	                	 if(empQtrList != null && empQtrList.size()>0)
	 	                	endDate= empQtrList.get(0).getAllocationEndDate();
	                	if(empQtrList!=null && empQtrList.size()>0)
	                	{
	                		empNameBf.append("<qtrExist>").append(true).append("</qtrExist>");
	                		empNameBf.append("<endDate>").append(endDate).append("</endDate>");
	                	}
	                	else
	                	{
	                		empNameBf.append("<qtrExist>").append(false).append("</qtrExist>");
	                	}
	                	empNameBf.append("</empNameMapping>");   
	                	
	                	Map map = new HashMap();
	                	String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();
			         
	                	logger.info(" the string buffer is :"+empNameMapping+"---"+isAvailedHRA);
	                	map.put("ajaxKey", empNameMapping);
	                	resultObject.setResultCode(ErrorConstants.SUCCESS);
	                	resultObject.setResultValue(map);
	                	resultObject.setViewName("ajaxData");
	                	logger.info(" SERVICE COMPLETE :");
	                	//hrEisempMstgu.setEmpId(hreisEmpMst.getEmpId());
	                }
	                else
	                {
	                	empNameBf.append("<availedHRA>").append(1).append("</availedHRA>");
	                	empNameBf.append("</empNameMapping>");   
	                	Map map = new HashMap();
	                	String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();
			         
	                	logger.info(" the string buffer is :"+empNameMapping+"---"+isAvailedHRA);
	                	map.put("ajaxKey", empNameMapping);
	                	resultObject.setResultCode(ErrorConstants.SUCCESS);
	                	resultObject.setResultValue(map);
	                	resultObject.setViewName("ajaxData");
	                	logger.info(" SERVICE COMPLETE :");
	                }
	                
	                

                }	
				else
				{
				List quaterTypeList = otherDetailDAOImpl.getQuaterType();
				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				List<CmnLookupMst> quaterRateTypeList = cmnLookupMstDAO.getAllChildrenByLookUpNameAndLang("QUARTER_RATE_TYPE",langId);
				logger.info("The Size of List is:-"+quaterRateTypeList.size());
				
				//added by Ankit bhatt for Maha Payroll
				//List<CmnLookupMst> accomodationTypeList = cmnLookupMstDAO.getAllChildrenByLookUpNameAndLang("Accomodation Type",langId);
				//List<CmnLookupMst> allocationTypeList = cmnLookupMstDAO.getAllChildrenByLookUpNameAndLang("Quarter_Alloted_To",langId);
				//ended
				
				 String empName = "";
				 if((voToService.get("empName")!=null&&!voToService.get("empName").equals("")))
				 empName=voToService.get("empName").toString();

				/*CmnLookupMst cmnLookupMst = new CmnLookupMst();
				for(Object o : quaterRateTypeList){
					cmnLookupMst =(CmnLookupMst) o;
					logger.info("The LookupId is:-"+cmnLookupMst.getLookupId()+" and Lookup Desc is:-" + cmnLookupMst.getLookupDesc());
				}*/
				//CmnLookupMst c = new CmnLookupMst();
				//c.getL
				objectArgs.put("quaterTypeList",quaterTypeList);
				objectArgs.put("quaterRateTypeList",quaterRateTypeList);
				objectArgs.put("empName",empName);
				//added by ravysh
				
				objectArgs.put("FromBasicDtlsNew",FromBasicDtlsNew);
				objectArgs.put("otherId", otherId);
				
				//added by Ankit bhatt for Maha Payroll
				//objectArgs.put("accomodationTypeList", accomodationTypeList);
				//objectArgs.put("allocationTypeList", allocationTypeList);
				//ended
				
				//Added By Abhilash
				String ddocode ="";
				String TanNo="";
				PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locationId);
				OrgDdoMst ddoMst = null; 
	    		if(ddoCodeList!=null && ddoCodeList.size()>0){
	    			ddoMst = ddoCodeList.get(0);
	    		}
	    		
	    		if(ddoMst!=null) {
	    			ddocode=ddoMst.getDdoCode();
	    			TanNo=ddoMst.getTanNo();
	    		}
	    		logger.info("custodian DDO Code is " + ddocode);
	    		
	    		
				/*String TresuryName ="";
				String TresuryCode ="";
				List TreasuryList = payBillDAO.getTreasuryCodeAndTreasuryName(ddocode);
				
				if(TreasuryList!=null && TreasuryList.size()!=0)
				{
					for(Iterator itr=TreasuryList.iterator();itr.hasNext();)
					{

						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							TresuryName = rowList[0].toString().trim();
						}
						if(rowList[1] != null)
						{
							TresuryCode = rowList[1].toString().trim();
						}

					}
				}*/
				
				/*logger.info("custodian TresuryCode *********"+TresuryCode);
				logger.info("custodian TresuryName *********"+TresuryName);*/
    			List custodianList = quaterMstDAOImpl.getCustodianList(null);
    			logger.info("custodian list size is *********"+custodianList.size());
    			objectArgs.put("custodianList",custodianList);
    			//Ended By Abhilash
    			
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				//added by ravysh
				if(FromBasicDtlsNew.equals("YES"))
					resultObject.setViewName("quarterDetailsMasterBasicDtls");
				else
				resultObject.setViewName("quarterDetailsMaster");
				}
				
				
			}
			else {
				String quaterType;
				//List quarterDtlsList = quaterMstDAOImpl.getListByColumnAndValue("cmnLocationMstByLocId", cmnLocationMst);
				List quarterDtlsList = quaterMstDAOImpl.getAllQuarterDetailsByLocationId(0, locationId,langId);
				List payrollCustomVOList = new ArrayList();
				PayrollCustomVO payrollCustomVO;				
				logger.info("The size of Quarter List is in view:-"+quarterDtlsList.size());
				//OrgEmpMst orgEmpMst = new OrgEmpMst();
				Iterator itrQuarter = quarterDtlsList.iterator();
				//Object[] rowList = new Object() ;
				while(itrQuarter.hasNext()){
					payrollCustomVO = new PayrollCustomVO();
					Object[] rowList = (Object[]) itrQuarter.next();
					payrollCustomVO.setHrEssQtrMst((HrEisQtrMst)rowList[0]);
					logger.info("The Quarter name is:-"+payrollCustomVO.getHrEssQtrMst().getQuarterName()+" and Quarter Type is :-"+payrollCustomVO.getHrEssQtrMst().getHrQuaterTypeMst().getQuaType());
					payrollCustomVO.setOrgEmpMst((OrgEmpMst)rowList[1]);
					logger.info("Employee Name:-"+payrollCustomVO.getOrgEmpMst().getEmpFname()+" "+payrollCustomVO.getOrgEmpMst().getEmpMname()+" "+payrollCustomVO.getOrgEmpMst().getEmpLname());
					payrollCustomVOList.add(payrollCustomVO);
				}
				/*HrEssQtrMst hrEssQtrMst = new HrEssQtrMst();
				//hrEssQtrMst.setCmnLocationMstByLocId(arg0);
				for(int i=0;i<quarterDtlsList.size();i++) {
					hrEssQtrMst = (HrEssQtrMst)quarterDtlsList.get(i);
					payrollCustomVO = new PayrollCustomVO();
					orgEmpMst = empDAOImpl.getEmpFromUser(hrEssQtrMst.getOrgUserMstByAllocatedTo(),langId); 
					logger.info("Emp Name:-"+orgEmpMst.getEmpFname()+" "+ orgEmpMst.getEmpMname() + orgEmpMst.getEmpLname());
					payrollCustomVO.setHrEssQtrMst(hrEssQtrMst);
					logger.info("Quarter Type name is:-"+payrollCustomVO.getHrEssQtrMst().getHrQuaterTypeMst().getQuaType());
					payrollCustomVO.setOrgEmpMst(orgEmpMst);
					payrollCustomVOList.add(payrollCustomVO);
				}*/
				objectArgs.put("quarterDtlsList", payrollCustomVOList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("quarterDetailsView");
				
				
			}
		}
		catch (Exception e){
			logger.error("Error is: "+ e.getMessage());			
			objectArgs.put("MESSAGECODE",1007);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}
	
	/**
	 * Method Name:-	isAvailQuarterFromSalaryConfig.
	 * Author Name:-	Amish Parikh.
	 * Date:-			28/01/2008.
	 * Function:-		This method is used for cross checking quarter existence before calculating HRA.	
	 * @param objectArgs
	 * @return ajax mapping
	 */
	
	public ResultObject isAvailQuarterFromSalaryConfig(Map objectArgs) {
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
		long empId;		// User Id of am Employee.
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		
		
//		added by Ankit Bhatt for integrating with Outer
		 
		long hrrId = Long.parseLong(resourceBundle.getString("hrrId"));
		long hraId = Long.parseLong(resourceBundle.getString("hraId"));
		try{
			logger.info("You are in isAvailQuarterFromSalaryConfig Service");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");            
            
            
            Date sysDate = new Date();
            empId=0;
            String empIdStr =StringUtility.getParameter("empId", request);
            empId=Long.parseLong(empIdStr);
            
			logger.info("Emloyee of intilization"+empId);
			
    		
			long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			
			long locId=StringUtility.convertToLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	     
					
			long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
            
			QuaterMstDAOImpl quaterMstDAOImpl=new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());

			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			if(empId!=0)
				hrEisEmpMst=empInfoDAOImpl.read(empId);
			
			long orgEmpId=0;
			if(hrEisEmpMst!=null && hrEisEmpMst.getOrgEmpMst()!=null)
				orgEmpId=hrEisEmpMst.getOrgEmpMst().getEmpId();
			logger.info("ORG EMP ID"+orgEmpId);
			
			OrgEmpMst orgEmpMst=new OrgEmpMst();
			EmpDAOImpl empDAOImpl=new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			if(orgEmpId!=0)
				orgEmpMst=empDAOImpl.read(orgEmpId);
			
			long orgUserId=0;
			if(orgEmpMst != null && orgEmpMst.getOrgUserMst()!= null)
				orgUserId=orgEmpMst.getOrgUserMst().getUserId();
			logger.info("ORG USER ID"+orgUserId);
			
			boolean qtrExistence=false;
			if(orgUserId!=0)
				qtrExistence=quaterMstDAOImpl.checkQuarterExistence(orgUserId,locId);
						
			logger.info("QTR EXISTENCE FLAG after checkQuarterExistence"+qtrExistence);
			
			StringBuffer empNameBf = new StringBuffer();
			 
			empNameBf.append("<empQtrMapping>"); 
			if(qtrExistence == true)
			{
				empNameBf.append("<qtrExist>").append(qtrExistence).append("</qtrExist>");
			}
			else
			{
				empNameBf.append("<qtrExist>").append(qtrExistence).append("</qtrExist>");
			}
            
			empNameBf.append("</empQtrMapping>");
            
            long isAvailedQuarter=0;
           
            Map map = new HashMap();
      		String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();
       
      		logger.info(" the string buffer is :"+empNameMapping+"---"+isAvailedQuarter);
      		map.put("ajaxKey", empNameMapping);
      		resultObject.setResultCode(ErrorConstants.SUCCESS);
      		resultObject.setResultValue(map);
      		resultObject.setViewName("ajaxData");
      		logger.info(" SERVICE COMPLETE :");
           
		}
		catch(Exception e) {
			logger.error("Error is: "+ e.getMessage());			
			objectArgs.put("MESSAGECODE",1007);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}
	
	//Added by Demolisher
	
	public ResultObject changeStatusToApprove(Map objectArgs) throws Exception
	{
		logger.info("Entering into loadData of ZpAdminOfficeServiceImpl");
		
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

	 try {	
		Map loginMap = (Map) objectArgs.get("baseLoginMap"); 
		 QuaterAprDAOImpl  quaterAprDAOImpl = new QuaterAprDAOImpl(QuarterApproval.class,serviceLocator.getSessionFactory());
	          
	     long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
	     long postId1 = postId-1;
		 logger.info("Post ID:"+postId1);
		 String empIdStr =StringUtility.getParameter("empId", request);
		 long empId=Long.parseLong(empIdStr);
		 logger.info("Emp ID:"+empId);
		 quaterAprDAOImpl.statusApprove(empId);
		 logger.info("After Status Approve");
		 
		 List<QuarterApproval> quaterApprovalLst = quaterAprDAOImpl.getAllDDOOfficeDtlsData();
		   logger.info("quaterApprovalLst::"+quaterApprovalLst.size());
			objectArgs.put("quaterApprovalLst",quaterApprovalLst);
			logger.info("quaterApprovalLst::"+quaterApprovalLst.size());
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			logger.info("objRes::"+objRes);
			objRes.setViewName("staffAccomodationView");
			logger.info("objRes::"+objRes);
		
		
		 
	   }
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...changeStatusToApprove");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");		
		
		}
		return objRes;
	}
	public ResultObject changeStatusToReject(Map objectArgs) throws Exception
	{
		logger.info("Entering into loadData of ZpAdminOfficeServiceImpl");
		
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

	 try {	
		 QuaterAprDAOImpl  quaterAprDAOImpl = new QuaterAprDAOImpl(QuarterApproval.class,serviceLocator.getSessionFactory());
	     
	     
		 Map loginMap = (Map) objectArgs.get("baseLoginMap"); 
	     long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
	     long postId1 = postId-1;
		 logger.info("Post ID:"+postId1);
		 
		 String empIdStr =StringUtility.getParameter("empId", request);
         long empId=Long.parseLong(empIdStr);
         logger.info("Emp ID:"+empId);
		  quaterAprDAOImpl.statusReject(empId);
		  
		  List<QuarterApproval> quaterApprovalLst = quaterAprDAOImpl.getAllDDOOfficeDtlsData();
		   logger.info("quaterApprovalLst::"+quaterApprovalLst.size());
		   objRes.setResultCode(ErrorConstants.SUCCESS);
						objectArgs.put("quaterApprovalLst",quaterApprovalLst);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("staffAccomodationView");
		 
		
	   }
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...changeStatusToReject");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");		
		
		}
		return objRes;
	}
	public ResultObject viewPendingRejectedDetails(Map objectArgs) throws Exception
	{
		logger.info("Entering into loadData of ZpAdminOfficeServiceImpl");
		
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap"); 

	 try {	
		 QuaterAprDAOImpl  quaterAprDAOImpl = new QuaterAprDAOImpl(QuarterApproval.class,serviceLocator.getSessionFactory());
		 long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			//int postId1=Integer.parseInt(loginMap.get("primaryPostId").toString());
			long ddoCode;
		    long postId1 = postId-1;
			logger.info("Post ID:"+postId1);
			ddoCode=quaterAprDAOImpl.getDDOpostId(postId1);
		 
	   List<QuarterApproval> quaterApprovalLst = quaterAprDAOImpl.viewPRdetails(ddoCode);
	   logger.info("zpdistrictOfficelst::"+quaterApprovalLst.size());
		
		objectArgs.put("quaterApprovalLst",quaterApprovalLst);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("staffAccomodationViewAST");
		
		
	   }
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...insertDistrictMPGDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");		
		
		}
		return objRes;
	}	
}
