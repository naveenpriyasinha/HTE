package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.InvestmentTypeMstDAOImpl;
import com.tcs.sgv.eis.dao.SectionMasterDAOImpl;
import com.tcs.sgv.eis.valueobject.HrInvestTypeMst;
import com.tcs.sgv.eis.valueobject.HrItSectionMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class InvestTypeMstService extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	public ResultObject getAllInvestTypes(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		try {
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			InvestmentTypeMstDAOImpl investmentTypeMstDAOImpl = new InvestmentTypeMstDAOImpl(HrInvestTypeMst.class,serv.getSessionFactory());
			HrInvestTypeMst hrInvestTypeMst = new HrInvestTypeMst();
			List lstInvestTypeMst = investmentTypeMstDAOImpl.getInvestmentTypes(cmnLanguageMst);
			for(int i=0;i<lstInvestTypeMst.size();i++){
				hrInvestTypeMst = (HrInvestTypeMst)lstInvestTypeMst.get(i);
				logger.info("The Investment Type Code:-"+hrInvestTypeMst.getHrItSectionMst().getSectCode());
				
			}
			objectArgs.put("lstInvestTypeMst", lstInvestTypeMst);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);
            resultObject.setViewName("investTypeMasterView");						
		}
		catch(Exception e){
			logger.info("There is some error in fetching VPF Data");
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return resultObject;
		}
		return resultObject;	
	}
	
	public ResultObject getInvestmentTypeData(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		try {
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			InvestmentTypeMstDAOImpl investmentTypeMstDAOImpl = new InvestmentTypeMstDAOImpl(HrInvestTypeMst.class,serv.getSessionFactory());
			List lstSection = investmentTypeMstDAOImpl.getSectionList(cmnLanguageMst);
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List lstStatus = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("StatusId", langId);
			objectArgs.put("lstSection", lstSection);
			objectArgs.put("lstStatus", lstStatus);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);
            resultObject.setViewName("addInvestTypeMst");						
		}
		
		catch(Exception e){
			logger.info("There is some error in fetching VPF Data");
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return resultObject;
		}
		return resultObject;		
	}
	public ResultObject insertUpdateInvestmentType(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		
		HrPayDeducTypeMst hrPayDeducTypeMst=null;
		DeducTypeMasterDAOImpl deducTypeMasterDAOImpl = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
		
		try{
			
			Date sysDate = new Date();
			String editMode=objectArgs.get("editMode").toString();
			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);			
	       
	        long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			CmnLanguageMst engCmnLanguageMst=cmnLanguageMstDaoImpl.read(1L);
			CmnLanguageMst gujCmnLanguageMst=cmnLanguageMstDaoImpl.read(2L);
			
			long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			//CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			//CmnLookupMst cmnLookupMst = null;
			       
			HrInvestTypeMst hrInvestTypeMst = null;			
			logger.info("Coming into the insert Service method insertInvestmentTypeDataDetail");
			InvestmentTypeMstDAOImpl investmentTypeMstDAOImpl = new InvestmentTypeMstDAOImpl(HrInvestTypeMst.class,serv.getSessionFactory());
			SectionMasterDAOImpl sectionMasterDAOImpl = new SectionMasterDAOImpl(HrItSectionMst.class,serv.getSessionFactory());
			HrItSectionMst hrItSectionMst = null;	
			HrInvestTypeMst objHrInvestTypeMst = new HrInvestTypeMst();
			long investTypeId;
			logger.info("The Value of editMode is:-"+editMode);
			if(editMode.equalsIgnoreCase("Y")) {
				long sectionId=0;
				hrInvestTypeMst = (HrInvestTypeMst)objectArgs.get("hrInvestType");
				investTypeId = Long.parseLong(objectArgs.get("investTypeId").toString());
				objHrInvestTypeMst = investmentTypeMstDAOImpl.read(investTypeId);
				
				sectionId = Long.parseLong(objectArgs.get("sectionId").toString());
				hrItSectionMst = sectionMasterDAOImpl.read(sectionId);
				objHrInvestTypeMst.setActivateFlag(hrInvestTypeMst.getActivateFlag());
				objHrInvestTypeMst.setEndDate(hrInvestTypeMst.getStartDate());
				objHrInvestTypeMst.setEndDate(hrInvestTypeMst.getEndDate());
				objHrInvestTypeMst.setHrItSectionMst(hrItSectionMst);
				objHrInvestTypeMst.setInvestName(hrInvestTypeMst.getInvestName());
				objHrInvestTypeMst.setInvestDesc(hrInvestTypeMst.getInvestDesc());			
				objHrInvestTypeMst.setUpdatedDate(sysDate);			
				objHrInvestTypeMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				objHrInvestTypeMst.setOrgUserMstByUpdatedBy(orgUserMst);								
				investmentTypeMstDAOImpl.update(objHrInvestTypeMst);				
				msg=1;
			}
			else{
				logger.info("Inside investTypeMstService.. new entry");
				
				/**********************************
				 *  For new entry(s): non-edit mode *
				 **********************************/
				HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
				hrInvestTypeMst = new HrInvestTypeMst();
				

				/* For every entry made in Jsp, there will be corresponding xml file for each entry.
				 * In other words, for each row dynamically created in jsp (i.e in table) will have different XML.
				 * We can iterate through each of them one by one by encXML, which contains array of path of all XML.  
				 */
				String encXML[] = StringUtility.getParameterValues("encXML",request); 
				
				logger.info("encXML: XML file path is: " +encXML.toString());
				
				if(encXML!=null && encXML.length>0)
		    	{
					List result = FileUtility.xmlFilesToVoByXStream(encXML);
					logger.info("result.size(): "+result.size());
					
					/*List lstSectionId = (List)objectArgs.get("lstSectionId");
					List lstHrInvestTypeMst = (List)objectArgs.get("lstHrInvestTypeMst");*/			

					IdGenerator idGen = new IdGenerator();
					Iterator iterator = result.iterator();
					long sectionCode=0;
					long investTypeCode=0;

					while(iterator.hasNext()){

						// ----- FOR ENGLISH ( langId = 1 ) ----- //
						hrInvestTypeMst = (HrInvestTypeMst)iterator.next();

						//set InvestTypeId
						investTypeId = idGen.PKGeneratorWODBLOC("hr_invest_type_mst",objectArgs);
						logger.info("****************************the id generated is "+investTypeId);
						hrInvestTypeMst.setInvestTypeId(investTypeId);
						
						
						logger.info("********************************************************************************");
						logger.info("For english...");
						logger.info("Invest type id: " +hrInvestTypeMst.getInvestTypeId());
						logger.info("Investment Name: " + hrInvestTypeMst.getInvestName());
						logger.info("Investment Desc: " + hrInvestTypeMst.getInvestDesc());
						logger.info("Investment SecId: " + hrInvestTypeMst.getHrItSectionMst().getSectId());
						logger.info("Investment StartDate: " +hrInvestTypeMst.getStartDate());
						logger.info("Investment EndDate: " + hrInvestTypeMst.getEndDate());
						logger.info("Investment activateFlg: " +hrInvestTypeMst.getActivateFlag());


						//set InvestTypeCode					
						investTypeCode = investTypeId;
						hrInvestTypeMst.setInvestTypeCode(investTypeCode);
						logger.info("investTypeCode: " +hrInvestTypeMst.getInvestTypeCode());
						
						//set LangId
						hrInvestTypeMst.setCmnLanguageMst(cmnLanguageMst);
						logger.info("langId: " +cmnLanguageMst.getLangId());
						
						//set DatabaseId
						hrInvestTypeMst.setCmnDatabaseMst(cmnDatabaseMst);
						logger.info("dbId: " +cmnDatabaseMst.getDbId());
						
						//set CreatedBy
						hrInvestTypeMst.setOrgUserMstByCreatedBy(orgUserMst);
						logger.info("userId: " +orgUserMst.getUserId());
						
						//set CreatedByPost
						hrInvestTypeMst.setOrgPostMstByCreatedByPost(orgPostMst);
						logger.info("created by post: " +orgPostMst.getPostId());
						
						//set CreatedDate
						hrInvestTypeMst.setCreatedDate(sysDate);
						logger.info("created date: " +hrInvestTypeMst.getCreatedDate());
						
						logger.info("********************************************************************************");


						try{
							//write to database
							investmentTypeMstDAOImpl.create(hrInvestTypeMst);
						}catch(Exception e){
							logger.info("Exception in writing to database: ");
							logger.error("Error is: "+ e.getMessage());
						}
						
						
						
						// ---- FOR GUJARATI ( langId = 2 )  ----- //
						objHrInvestTypeMst = null;
						objHrInvestTypeMst = new HrInvestTypeMst();
						
						
						
						//set InvestTypeId
						investTypeId= idGen.PKGeneratorWODBLOC("hr_invest_type_mst",objectArgs);
						objHrInvestTypeMst.setInvestTypeId(investTypeId);

						//set InvestTypeCode	
						objHrInvestTypeMst.setInvestTypeCode(investTypeCode);
						
						//set lang id
						objHrInvestTypeMst.setCmnLanguageMst(gujCmnLanguageMst);
						
						//set Investmet Name
						objHrInvestTypeMst.setInvestName(hrInvestTypeMst.getInvestName());
						
						//Investment Desc
						objHrInvestTypeMst.setInvestDesc(hrInvestTypeMst.getInvestDesc());
						
						//Investment SecId
						objHrInvestTypeMst.setHrItSectionMst(hrInvestTypeMst.getHrItSectionMst());
						
						//Investment StartDate
						objHrInvestTypeMst.setStartDate(hrInvestTypeMst.getStartDate());
						
						//Investment EndDate
						objHrInvestTypeMst.setEndDate(hrInvestTypeMst.getEndDate());
						
						//Investment activteFlg
						objHrInvestTypeMst.setActivateFlag(hrInvestTypeMst.getActivateFlag());
						
						//dbId
						objHrInvestTypeMst.setCmnDatabaseMst(cmnDatabaseMst);
						
						//set CreatedBy
						objHrInvestTypeMst.setOrgUserMstByCreatedBy(orgUserMst);
						
						//set CreatedByPost
						objHrInvestTypeMst.setOrgPostMstByCreatedByPost(orgPostMst);
						
						//set CreatedDate
						objHrInvestTypeMst.setCreatedDate(sysDate);
						

						
						logger.info("For gujarati...");
						logger.info("********************************************************************************");
						logger.info("Invest type id: " +objHrInvestTypeMst.getInvestTypeId());
						logger.info("Investment Name: " + objHrInvestTypeMst.getInvestName());
						logger.info("Investment Desc: " + objHrInvestTypeMst.getInvestDesc());
						logger.info("Investment SecId: " + objHrInvestTypeMst.getHrItSectionMst().getSectId());
						logger.info("Investment StartDate: " +objHrInvestTypeMst.getStartDate());
						logger.info("Investment EndDate: " + objHrInvestTypeMst.getEndDate());
						logger.info("Investment activateFlg: " +objHrInvestTypeMst.getActivateFlag());
						logger.info("investTypeCode: " +objHrInvestTypeMst.getInvestTypeCode());
						logger.info("langId: " +objHrInvestTypeMst.getCmnLanguageMst().getLangId());
						logger.info("dbId: " +objHrInvestTypeMst.getCmnDatabaseMst().getDbId());
						logger.info("userId: " +objHrInvestTypeMst.getOrgUserMstByCreatedBy().getUserId());
						logger.info("created by post: " +objHrInvestTypeMst.getOrgPostMstByCreatedByPost().getPostId());
						logger.info("created date: " +objHrInvestTypeMst.getCreatedDate());
						logger.info("********************************************************************************");
						
						
						
						try{
							//write to database
							investmentTypeMstDAOImpl.create(objHrInvestTypeMst);					
						}catch(Exception e){
							logger.info("Exception in writing to database: ");
							logger.error("Error is: "+ e.getMessage());
						}
					
					}//end while

		    	}//end if

			}//end non-edit mode
			
			
			
			logger.info("The Message Code:-"+msg);
			List <HrInvestTypeMst> lstInvestTypeMst = investmentTypeMstDAOImpl.getInvestmentTypes(cmnLanguageMst);				
			logger.info("size of  list is "+lstInvestTypeMst.size());	
			objectArgs.put("lstInvestTypeMst", lstInvestTypeMst);
			
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			
				resultObject.setResultCode(ErrorConstants.SUCCESS);			
				resultObject.setResultValue(objectArgs);	
				
			if(msg==1)
				resultObject.setViewName("editInvestTypeMst");
			else
				resultObject.setViewName("addInvestTypeMst");	
			
			logger.info("INSERTED SUCCESSFULLY");
			
			
		}
	     catch(ConstraintViolationException ex)  {
	    	 logger.info("TransactionSystemException occurs...");
	     }
		catch(Exception e){			
			logger.info("There is some error at editting or inserting time");			
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;		
	}
	
	public ResultObject getInvestmentTypeDataById(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		try {
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			long investTypeId = Long.parseLong(voToService.get("investTypeId").toString());
			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			HrInvestTypeMst hrInvestTypeMst = new HrInvestTypeMst();			
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			InvestmentTypeMstDAOImpl investmentTypeMstDAOImpl = new InvestmentTypeMstDAOImpl(HrInvestTypeMst.class,serv.getSessionFactory());
			List lstSection = investmentTypeMstDAOImpl.getSectionList(cmnLanguageMst);			
			List lstStatus = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("StatusId", langId);
			hrInvestTypeMst = investmentTypeMstDAOImpl.read(investTypeId);
			objectArgs.put("lstSection", lstSection);
			objectArgs.put("lstStatus", lstStatus);
			objectArgs.put("hrInvestTypeMst", hrInvestTypeMst);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);
            resultObject.setViewName("editInvestTypeMst");						
		}
		catch(Exception e){
			logger.info("There is some error in fetching VPF Data");
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return resultObject;
		}
		return resultObject;
	}
}
