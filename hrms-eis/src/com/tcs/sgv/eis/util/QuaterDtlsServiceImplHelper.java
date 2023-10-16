package com.tcs.sgv.eis.util;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.util.CommomnDataObjectFatch;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.ComponentMstDAOImpl;
import com.tcs.sgv.allowance.service.Parser;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.QuaterMstDAOImpl;
import com.tcs.sgv.eis.util.RevisedHrrAndHra;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrEisQuaterTypeMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.dao.AddressDAOImpl;
import com.tcs.sgv.hod.common.delegate.AddressDelegate;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class QuaterDtlsServiceImplHelper {
	public QuaterDtlsServiceImplHelper() {
		// TODO Auto-generated constructor stub
	}
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
	CommomnDataObjectFatch commomnDataObjectFatch;
	Log logger = LogFactory.getLog( getClass() );
	public QuaterDtlsServiceImplHelper(ServiceLocator serv)
	{
		this.serv =serv;
		commomnDataObjectFatch=new CommomnDataObjectFatch(serv);
	}

public boolean insertQuaterDtls(int ownHouseFlag,long quarterType,long quarterRateType,HrEisQtrMst quaterMst,Map passMapInsert,CmnAddressMst cmnAddressMst,long locId,long userId,long postId,long dbId,long langId)
{
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	long hrrId = Long.parseLong(resourceBundle.getString("hrrId"));
	long hraId = Long.parseLong(resourceBundle.getString("hraId"));
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	//System.out.println("serv.getSessionFactory()"+serv.getSessionFactory());
	logger.info("serv.getSessionFactory()"+serv.getSessionFactory());
	AddressDAOImpl cmnAddressMstDaoImpl = new AddressDAOImpl(CmnAddressMst.class, serv.getSessionFactory());
	QuaterMstDAOImpl quaterMstDAOImpl = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());
	IdGenerator idgen=new IdGenerator();
	passMapInsert=new HashMap();
	Date sysDate=new Date();
	passMapInsert.put("serviceLocator", serv);
	passMapInsert.put("tablename", "HR_EIS_QTR_EMP_MPG");
	long quarterId=idgen.PKGeneratorWebService("HR_EIS_QTR_EMP_MPG",serv,userId,langId,locId);
	GenericDaoHibernateImpl genericImpl = new GenericDaoHibernateImpl(HrEisQuaterTypeMst.class);
	genericImpl.setSessionFactory(serv.getSessionFactory());
	HrEisQuaterTypeMst hrQuaterTypeMst = (HrEisQuaterTypeMst) genericImpl.read(quarterType);
	CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
	CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
	OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
	OrgUserMst objOrgUserMst=orgUserMstDaoImpl.read(userId);
	CmnLookupMstDAO lookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
	CmnLookupMst rateTypeLookup = lookupMstDAO.read(quarterRateType);
	try{
		quaterMst.setQuarterId(quarterId);
		quaterMst.setHrEisQuaterTypeMst(hrQuaterTypeMst);
		quaterMst.setOrgUserMstByAllocatedTo(objOrgUserMst);				
		quaterMst.setRateTypeLookup(rateTypeLookup);
		logger.info("dbId..............."+dbId);
		
		if(commomnDataObjectFatch.getCmnDatabaseMst(dbId)!=null)
			{//System.out.println("dbId..............."+"object is not null");
			quaterMst.setCmnDatabaseMst(commomnDataObjectFatch.getCmnDatabaseMst(dbId));
			}
		else
		{
			//System.out.println("dbId..............."+"object is null");
		
		}
		
		logger.info("dbId..............."+dbId);
		
		quaterMst.setCmnLocationMstByLocId(cmnLocationMst);
		quaterMst.setOrgPostMstByCreatedByPost(commomnDataObjectFatch.getOrgPostMst(postId));
		quaterMst.setOrgUserMstByCreatedBy(commomnDataObjectFatch.getorgUserMst(userId));
		quaterMst.setCreatedDate(sysDate);
		quaterMst.setTrnCounter(new Integer(1));


		if (cmnAddressMst != null)
		{
			passMapInsert.put("mode", "add");
			passMapInsert.put("serviceLocator", serv);
			passMapInsert.put("tablename", "CMN_ADDRESS_MST");
			cmnAddressMst = AddressDelegate.setAddressObjectFields(cmnAddressMst, passMapInsert);
			long addressID = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", passMapInsert);
			cmnAddressMst.setAddressId(addressID);

			try
			{
				cmnAddressMstDaoImpl.create(cmnAddressMst);
				quaterMst.setCmnAddressMst(cmnAddressMst);

			}
			catch (Exception e)
			{
				//  logger.error("Error in INSERT FairFestivalAddress DETAILS");
				logger.error("Error is: "+ e.getMessage());
				resultObject.setThrowable(e);
				resultObject.setResultCode(ErrorConstants.ERROR);
				resultObject.setViewName("errorPage");
				//return resultObject;
			}


		}
		quaterMstDAOImpl.create(quaterMst);	
	}
	catch(Exception e)
	{
		logger.error("Error is: "+ e.getMessage());
	}
	/////////////////////quaterMstDAOImpl.create(quaterMst);		
	//return resultObject;
	//OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
    EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
	  //long langId=StringUtility.convertToLong(loginMap.get("langId").toString());
  try{
    OrgEmpMst orgEmpMst = orgEmpDao.getEmpFromUser(orgUserMstDaoImpl.read(userId), langId);
	  //System.out.println("EmpId is"+orgEmpMst.getEmpId());
	  HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
	  OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
	  hrEisOtherDtls = otherDetailDAOImpl.getOtherData(orgEmpMst.getEmpId());
	  hrEisOtherDtls.setOwnHouse(ownHouseFlag);
	  hrEisOtherDtls.setUpdatedDate(sysDate);
	  otherDetailDAOImpl.update(hrEisOtherDtls);
  }
  catch(Exception e)
  {
  	logger.error("Error is: "+ e.getMessage());
  }
	  
//Ended By Varun
//added by Samir Joshi for Hra & hrr Update in other detail
List<HrEisOtherDtls> otherDtlVo=new ArrayList<HrEisOtherDtls>();
OtherDetailDAOImpl OtherdetailDao=new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
//userId=Long.parseLong(passMapInsert.get("userId").toString());//obj
	Calendar calGiven = Calendar.getInstance();
	Date givenDate = calGiven.getTime();
	

	
	Calendar calc = Calendar.getInstance();
//System.out.println("userid is=====>"+userId);
otherDtlVo=OtherdetailDao.getAllOtherEmpDataHrr(userId);
//System.out.println("the size of otherdetail vo is=====>"+otherDtlVo.size());


if(otherDtlVo!=null && otherDtlVo.size()>0){
Parser parExpression = new Parser();
	Date sysdate = new Date();


	ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(com.tcs.sgv.allowance.valueobject.HrPayComponentMst.class,serv.getSessionFactory());
	List<com.tcs.sgv.allowance.valueobject.HrPayComponentMst> componentList =componentDAO.getListByColumnAndValue("componentDesc", "DP");
	



	RevisedHrrAndHra revisedHrrAndHra = new RevisedHrrAndHra();
	passMapInsert.put("userIdForQtr",userId);//obj
	int monthGiven=revisedHrrAndHra.monthofDate(givenDate);
	int yearGiven=revisedHrrAndHra.yearofDate(givenDate);
	int maxDay = calGiven.getActualMaximum(5);
	passMapInsert.put("monthGiven",monthGiven);//obj
	passMapInsert.put("yearGiven",yearGiven);//obj
	
	Map hrrAndHraMap = revisedHrrAndHra.calculateHrrAndHra(passMapInsert);//obj
	
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
	List<HrPayDeductionDtls> deducList = null;//empDuducDtlsDAO.getDeductionDtls(otherDtlVo.get(0).getHrEisEmpMst().getOrgEmpMst().getEmpId(), hrrId);
	
	if(deducList!=null && deducList.size()>0)
	{
		HrPayDeductionDtls deducDtlsVo = deducList.get(0);
		
		deducDtlsVo.setEmpDeducAmount(hrr);
		deducDtlsVo.setOrgPostMstByUpdatedByPost(commomnDataObjectFatch.getOrgPostMst(postId));
		deducDtlsVo.setOrgUserMstByUpdatedBy(commomnDataObjectFatch.getorgUserMst(userId));
		deducDtlsVo.setUpdatedDate(sysdate);
		
		logger.info("going to update hrr revised one "+hrr);
		
		//empDuducDtlsDAO.update(deducDtlsVo);
		
		logger.info("updated hrr revised one ");
	}
	EmpAllwMapDAOImpl empAllwMapDao = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
	HrPayEmpallowMpg empAllowMpgVo = empAllwMapDao.getHrPayEmpallowMpg(otherDtlVo.get(0).getHrEisEmpMst().getOrgEmpMst().getEmpId(), hraId);
	if(empAllowMpgVo!=null )
	{
		
		empAllowMpgVo.setEmpAllowAmount(revisedHra);
		
		empAllowMpgVo.setOrgPostMstByUpdatedByPost(commomnDataObjectFatch.getOrgPostMst(postId));
		empAllowMpgVo.setOrgUserMstByUpdatedBy(commomnDataObjectFatch.getorgUserMst(userId));
		empAllowMpgVo.setUpdatedDate(sysdate);
		
		logger.info("going to update hra revised one "+revisedHra);
		
		//empAllwMapDao.update(empAllowMpgVo);
		
		logger.info("updated hra revised one ");
	}
	
}



	return true;
}
	public boolean updateQuaterDtls(CmnLookupMst rateTypeLookup,HrEisQuaterTypeMst hrQuaterTypeMst,long quarterType,long quarterId,long quarterRateType,HrEisQtrMst quaterMst,long postId,Map passMap,long userId,int ownHouseFlag,long langId)
	{	
		//msg=1;
		//HrEisQtrMst quaterMst = (HrEisQtrMst) passMap.get("hrEssQtrMst");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Date sysDate=new Date();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long hrrId = Long.parseLong(resourceBundle.getString("hrrId"));
		long hraId = Long.parseLong(resourceBundle.getString("hraId"));
		
		QuaterMstDAOImpl quaterMstDAOImpl = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());
		HrEisQtrMst hrEssQtrMst = new HrEisQtrMst();
	   	quarterId = Long.parseLong(passMap.get("quarterId").toString());//objargs
    	hrEssQtrMst  = quaterMstDAOImpl.read(quarterId);
    	hrEssQtrMst.setQuarterName(hrQuaterTypeMst.getQuaType());
    	hrEssQtrMst.setHrEisQuaterTypeMst(hrQuaterTypeMst);
    	hrEssQtrMst.setRateTypeLookup(rateTypeLookup);
    	hrEssQtrMst.setAllocationStartDate(quaterMst.getAllocationStartDate());
    	hrEssQtrMst.setPossessionDate(quaterMst.getPossessionDate());
    	hrEssQtrMst.setAllocationEndDate(quaterMst.getAllocationEndDate());
    	hrEssQtrMst.setOrgPostMstByUpdatedByPost(commomnDataObjectFatch.getOrgPostMst(postId));
    	hrEssQtrMst.setOrgUserMstByUpdatedBy(commomnDataObjectFatch.getorgUserMst(userId));
    	hrEssQtrMst.setUpdatedDate(sysDate);
    	
    	AddressDAOImpl cmnAddressMstDaoImpl = new AddressDAOImpl(CmnAddressMst.class, serv.getSessionFactory());
		
		
		/*System.out.println("==== cmnAddressMst ===="+cmnAddressMst.getAddressId());
		System.out.println("==== cmnAddressMst ===="+cmnAddressMst.getStreet());
*/
//		Update Address Sart
    	
    	 
    	if(passMap.get("quaterAddress")!=null){//obj
    		logger.info("Inside the quater update");
    		CmnAddressMst cmnAddressMst = (CmnAddressMst) passMap.get("quaterAddress");//objargs
//    		logger.info("The Value of Address Area is :-"+cmnAddressMst.getArea());
    		if(cmnAddressMst.getAddressId()==0){
    			passMap.put("mode", "add");//obj
    			cmnAddressMst = AddressDelegate.setAddressObjectFields(cmnAddressMst, passMap);//obj
    			try{
    				long addressID = IDGenerateDelegate.getNextId("CMN_ADDRESS_MST", passMap);//obj
    				logger.info("Addressid for Quater : " + addressID);
        			cmnAddressMst.setAddressId(addressID);
    			}catch(Exception e)
    			{
    				logger.error("Error is: "+ e.getMessage());
    			}
    			
    			try
    			{
    				cmnAddressMst.setOrgUserMstByCreatedBy(commomnDataObjectFatch.getorgUserMst(userId));
            		cmnAddressMst.setCreatedDate(sysDate);
            		cmnAddressMst.setOrgPostMstByCreatedByPost(commomnDataObjectFatch.getOrgPostMst(postId));
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
    				//return resultObject;
    			}
    		}
    		else {
    			try{
    			resultObject = serv.executeService("EDIT_ADDRESS_DETAILS", passMap);//obj
    			passMap = (Map) resultObject.getResultValue(); //obj
    			cmnAddressMst = (CmnAddressMst) passMap.get("quaterAddress");//omj
    			//logger.info("The updated Address Is:-"+cmnAddressMst.getArea()+" uii"+cmnAddressMst.getPincode());
    			}catch(Exception e)
    			{
    				logger.error("Error is: "+ e.getMessage());
    			}
    		}
    		
    		//logger.info("The City Name is:-"+hrEssQtrMst.getCmnAddressMst().getAddressId()+" fdfdf:-"+hrEssQtrMst.getCmnAddressMst().getStateName());
    	}
    	//logger.info("The City Name is:-"+hrEssQtrMst.getCmnAddressMst().getAddressId()+" fdfdf:-"+hrEssQtrMst.getCmnAddressMst().getStateName());
    	
		
		//Address End
		
    	   	//quaterMstDAOImpl.update(hrEssQtrMst);
    	/*return true;
	}
	

	public boolean updateHrEisOtherDtls(long userId,long langId,int ownHouseFlag){*/
			
			/*	  return true;
	}
	public boolean updateHraAndHrrDetails(long userId,long postId)
	{*/
//      Added By Varun
    	   	OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
	      EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
		  //long langId=StringUtility.convertToLong(loginMap.get("langId").toString());
	    try{
	      OrgEmpMst orgEmpMst = orgEmpDao.getEmpFromUser(orgUserMstDaoImpl.read(userId), langId);
		  //System.out.println("EmpId is"+orgEmpMst.getEmpId());
		  HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
		  OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		  hrEisOtherDtls = otherDetailDAOImpl.getOtherData(orgEmpMst.getEmpId());
		  hrEisOtherDtls.setOwnHouse(ownHouseFlag);
		  hrEisOtherDtls.setUpdatedDate(sysDate);
		  otherDetailDAOImpl.update(hrEisOtherDtls);
	    }
	    catch(Exception e)
	    {
	    	logger.error("Error is: "+ e.getMessage());
	    }
		  
   //Ended By Varun
      //added by Samir Joshi for Hra & hrr Update in other detail
      List<HrEisOtherDtls> otherDtlVo=new ArrayList<HrEisOtherDtls>();
      OtherDetailDAOImpl OtherdetailDao=new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
      userId=Long.parseLong(passMap.get("userId").toString());//obj
		Calendar calGiven = Calendar.getInstance();
		Date givenDate = calGiven.getTime();
		

		
		Calendar calc = Calendar.getInstance();
      //System.out.println("userid is=====>"+userId);
      otherDtlVo=OtherdetailDao.getAllOtherEmpDataHrr(userId);
      //System.out.println("the size of otherdetail vo is=====>"+otherDtlVo.size());
      
  
      if(otherDtlVo!=null && otherDtlVo.size()>0){
  	Parser parExpression = new Parser();
		Date sysdate = new Date();
	

		ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(com.tcs.sgv.allowance.valueobject.HrPayComponentMst.class,serv.getSessionFactory());
		List<com.tcs.sgv.allowance.valueobject.HrPayComponentMst> componentList =componentDAO.getListByColumnAndValue("componentDesc", "DP");
		

      

		RevisedHrrAndHra revisedHrrAndHra = new RevisedHrrAndHra();
		passMap.put("userIdForQtr",userId);//obj
		int monthGiven=revisedHrrAndHra.monthofDate(givenDate);
		int yearGiven=revisedHrrAndHra.yearofDate(givenDate);
		int maxDay = calGiven.getActualMaximum(5);
		passMap.put("monthGiven",monthGiven);//obj
		passMap.put("yearGiven",yearGiven);//obj
		
		Map hrrAndHraMap = revisedHrrAndHra.calculateHrrAndHra(passMap);//obj
		
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
		List<HrPayDeductionDtls> deducList =null;// empDuducDtlsDAO.getDeductionDtls(otherDtlVo.get(0).getHrEisEmpMst().getOrgEmpMst().getEmpId(), hrrId);
		
		if(deducList!=null && deducList.size()>0)
		{
			HrPayDeductionDtls deducDtlsVo = deducList.get(0);
			
			deducDtlsVo.setEmpDeducAmount(hrr);
			deducDtlsVo.setOrgPostMstByUpdatedByPost(commomnDataObjectFatch.getOrgPostMst(postId));
			deducDtlsVo.setOrgUserMstByUpdatedBy(commomnDataObjectFatch.getorgUserMst(userId));
			deducDtlsVo.setUpdatedDate(sysdate);
			
			logger.info("going to update hrr revised one "+hrr);
			
			//empDuducDtlsDAO.update(deducDtlsVo);
			
			logger.info("updated hrr revised one ");
		}
		EmpAllwMapDAOImpl empAllwMapDao = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
		HrPayEmpallowMpg empAllowMpgVo = empAllwMapDao.getHrPayEmpallowMpg(otherDtlVo.get(0).getHrEisEmpMst().getOrgEmpMst().getEmpId(), hraId);
		if(empAllowMpgVo!=null )
		{
			
			empAllowMpgVo.setEmpAllowAmount(revisedHra);
			
			empAllowMpgVo.setOrgPostMstByUpdatedByPost(commomnDataObjectFatch.getOrgPostMst(postId));
			empAllowMpgVo.setOrgUserMstByUpdatedBy(commomnDataObjectFatch.getorgUserMst(userId));
			empAllowMpgVo.setUpdatedDate(sysdate);
			
			logger.info("going to update hra revised one "+revisedHra);
			
			//empAllwMapDao.update(empAllowMpgVo);
			
			logger.info("updated hra revised one ");
		}
		
     }
     
    
      
      
      
      
		////ended by samir joshi
          return true;
	}
	
}
