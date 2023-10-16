package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.SectionMasterDAOImpl;
import com.tcs.sgv.eis.valueobject.HrItSectionMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class SectionMasterService extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg=0;


	public ResultObject getSectionData(Map objServiceArgs)
	{
		logger.info("---------------inside getSectionData-------------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{			
            ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
                  
            SectionMasterDAOImpl sectionMstDAO = new SectionMasterDAOImpl(HrItSectionMst.class,serv.getSessionFactory());
            HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
            
            
            HttpSession session=request.getSession();		
    		Map loginDetails=(Map)objServiceArgs.get("baseLoginMap");
    		long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
            
    		Map voToService = (Map)objServiceArgs.get("voToServiceMap");
			String editFlag = voToService.get("edit")!=null?(String)voToService.get("edit"):"";
			String sectid="";
            if(editFlag != null && editFlag.equals("Y"))
            {
            	if(voToService.get("sectId")!=null && !voToService.get("sectId").equals(""))
	        	{
            		sectid=voToService.get("sectId").toString();
            		logger.info("The sectid inside update mode of getSectionData is--------->"+sectid);
	        	}
            	 
            	HrItSectionMst sectionList = sectionMstDAO.getSectionDataBySectId(sectid);
            	    
            	CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
            	/*CmnLookupMst cmnLookupMst = new CmnLookupMst();
            	cmnLookupMst = cmnLookupMstDAOImpl.read(sectionList.getActivateFlag());*/
            	List<CmnLookupMst> statusList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("StatusId", langId);
            	logger.info("The size of statusList inside getSectionData is---------->"+statusList.size());
            	logger.info("*****The id of Activate Flag is---------->"+sectionList.getActivateFlag());
            	
            	objServiceArgs.put("sectionList", sectionList);
            	objServiceArgs.put("statusList", statusList);
            	//objServiceArgs.put("cmnLookupMst", cmnLookupMst);
            	
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objServiceArgs);
		        resultObject.setViewName("ITSectionEdit");
            	
            }
            else
            {
            	           	
            	List <HrItSectionMst> sectionList = sectionMstDAO.getSectionDataByLangId(langId);
            	logger.info("list size of sectionList in getSectionData is------------>"+sectionList.size());
            	
            	objServiceArgs.put("sectionList", sectionList);
                resultObject.setResultCode(ErrorConstants.SUCCESS);
                resultObject.setResultValue(objServiceArgs);
                resultObject.setViewName("ITSectionView");	   
                
               
            }
		}
		catch(Exception e){
			
			 logger.info("Exception Ocuures...getSectionData");
			 logger.error("Error is: "+ e.getMessage());
			 objServiceArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objServiceArgs);
			 resultObject.setViewName("errorInsert");
		}
	
		return resultObject;
}


public ResultObject insertSectionData(Map objectArgs) {
	
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	try{
		logger.info("************Inside insertSectionData*****************");
		HrItSectionMst hrItSectionMst = (HrItSectionMst)objectArgs.get("sectionData");
		logger.info( "The HrItSectionMst VO is " + hrItSectionMst);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		SectionMasterDAOImpl sectionMstDAO = new SectionMasterDAOImpl(HrItSectionMst.class,serv.getSessionFactory());
		GenericDaoHibernateImpl gImpl = null;
		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		
		
        String editFromVO = objectArgs.get("edit").toString();
        logger.info("editFromVO insertBankMasterDtls" + editFromVO);
        
        
        
        long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
		
		long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
		
		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
        
       
        langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(1L);//Hard Coded
		
		//for gujarati language
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImplGuj=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMstGuj=cmnLanguageMstDaoImpl.read(2L);//Hard Coded
		
		long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
		 
		 
        
        if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
		{
        	
        	long sectid = hrItSectionMst.getSectId();
        	logger.info("The sectid inside update mode of insertSectionData is------>"+sectid);
        	HrItSectionMst hritsectionmst = sectionMstDAO.read(sectid);
			logger.info("INSIDE UPDATE insertSectionData");	
			logger.info("The Section Code is----->"+hrItSectionMst.getSectCode());
			logger.info("The Section Name is----->"+hrItSectionMst.getSectName());
			logger.info("The Section Description is------>"+hrItSectionMst.getSectDesc());
			logger.info("The Section Start Date is------->"+hrItSectionMst.getStartDate());
			logger.info("The Section End Date is--------->"+hrItSectionMst.getEndDate());
			logger.info("The Actavite Flag id is------->"+hrItSectionMst.getActivateFlag());
			
			hritsectionmst.setSectCode(hrItSectionMst.getSectCode());
			hritsectionmst.setSectName(hrItSectionMst.getSectName());
			hritsectionmst.setSectDesc(hrItSectionMst.getSectDesc());
			hritsectionmst.setStartDate(hrItSectionMst.getStartDate());
			hritsectionmst.setEndDate(hrItSectionMst.getEndDate());
			hritsectionmst.setActivateFlag(hrItSectionMst.getActivateFlag());
			
			Date sysdate = new Date();
			hritsectionmst.setUpdatedDate(sysdate);
			hritsectionmst.setOrgPostMstByUpdatedByPost(orgPostMst);
			hritsectionmst.setOrgUserMstByUpdatedBy(orgUserMst);
			hritsectionmst.setUpdatedDate(sysdate);
			
			sectionMstDAO.update(hritsectionmst);
			msg=1;
			logger.info("Updated successfully................");
		}
        else
		{
			logger.info("************INSIDE CREATE  insertSectionData*******************");
			IdGenerator idGen = new IdGenerator();
			long sectId = idGen.PKGeneratorWODBLOC("hr_it_section_mst", objectArgs);
			hrItSectionMst.setSectId(sectId);
	        
			 
			 
			 Date sysdate = new Date();
			 
			 hrItSectionMst.setCmnLanguageMst(cmnLanguageMst);
			 hrItSectionMst.setCmnDatabaseMst(cmnDatabaseMst);
			 //hrItSectionMst.setOrgPostMstByUpdatedByPost(orgPostMst);
			 hrItSectionMst.setOrgPostMstByCreatedByPost(orgPostMst);
			 //hrItSectionMst.setOrgUserMstByUpdatedBy(orgUserMst);
			 hrItSectionMst.setOrgUserMstByCreatedBy(orgUserMst);
			 hrItSectionMst.setCreatedDate(sysdate);				 				 							
			 hrItSectionMst.setSectTypeCode(sectId);
			 
			 sectionMstDAO.create(hrItSectionMst);
			 
			 
			 //for inserting (gujarati language) lang_id=2
			 HrItSectionMst hrItSectionMstGuj = new HrItSectionMst();
			 
			 long sectIdGuj = idGen.PKGeneratorWODBLOC("hr_it_section_mst", objectArgs);
			 
			 
			 hrItSectionMstGuj.setSectId(sectIdGuj);
			 hrItSectionMstGuj.setSectCode(hrItSectionMst.getSectCode());
			 hrItSectionMstGuj.setSectName(hrItSectionMst.getSectName());
			 hrItSectionMstGuj.setSectDesc(hrItSectionMst.getSectDesc());
			 hrItSectionMstGuj.setStartDate(hrItSectionMst.getStartDate());
			 hrItSectionMstGuj.setEndDate(hrItSectionMst.getEndDate());
			 hrItSectionMstGuj.setActivateFlag(hrItSectionMst.getActivateFlag());
			 hrItSectionMstGuj.setCmnLanguageMst(cmnLanguageMstGuj);
			 hrItSectionMstGuj.setCmnDatabaseMst(cmnDatabaseMst);
			 hrItSectionMstGuj.setOrgPostMstByCreatedByPost(orgPostMst);
			 hrItSectionMstGuj.setOrgUserMstByCreatedBy(orgUserMst);
			 hrItSectionMstGuj.setCreatedDate(sysdate);				 				 							
			 hrItSectionMstGuj.setSectTypeCode(sectId);
			 
			 sectionMstDAO.create(hrItSectionMstGuj);
			 //end
			 logger.info("Inserted successfully................");
		}
        
		
        if(msg==1)
			objectArgs.put("MESSAGECODE",300006);
		else
			objectArgs.put("MESSAGECODE",300005);
					
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		
		resultObject.setResultValue(objectArgs);
		if(msg==1)
			resultObject.setViewName("ITSectionEdit");
		else
			resultObject.setViewName("ITSectionMaster");
		
	}
	catch(NullPointerException ne)
	{
		logger.info("Null Pointer Exception Ocuures...insertSectionData");
		logger.error("Error is: "+ ne.getMessage());
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("errorInsert");			
	}
	
	catch(Exception e){
		
		 logger.info("Exception Ocuures...insertSectionData");
		 logger.error("Error is: "+ e.getMessage());
		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		 resultObject.setResultValue(objectArgs);
		 resultObject.setViewName("errorInsert");
	}
	return resultObject;
}


public ResultObject fillSectionCombo(Map objectArgs)
{
	logger.info("---------------inside fillSectionCombo-------------------");
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	
	try
	{			
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
              
       
		Map loginDetails=(Map)objectArgs.get("baseLoginMap");
		long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
		
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
    	List<CmnLookupMst> statusList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("StatusId", langId);
    	logger.info("The size of statusList is---------->"+statusList.size());
        
    	objectArgs.put("statusList", statusList);
    	resultObject.setResultCode(ErrorConstants.SUCCESS);
        resultObject.setResultValue(objectArgs);
        resultObject.setViewName("ITSectionMaster");
	}
	catch(Exception e)
	{
		 logger.info("Exception Ocuures...fillSectionCombo");
		 logger.error("Error is: "+ e.getMessage());
		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		 resultObject.setResultValue(objectArgs);
		 resultObject.setViewName("errorInsert");
	}
	return resultObject;
}

}