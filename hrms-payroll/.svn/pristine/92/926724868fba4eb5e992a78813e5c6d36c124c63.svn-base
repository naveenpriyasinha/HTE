package com.tcs.sgv.deduction.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.allowance.dao.ExpressionMasterDAOImpl;
import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.allowance.valueobject.HrPayExpressionMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducExpMasterDAOImpl;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class DeducExpMasterServiceImpl extends ServiceImpl{
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	public ResultObject addUpdateDeducExpMaster(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);	
		long elementCode;
		try{
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			long deductionId;
			
			logger.info("Deduction Expression Master Service:-");
			
			
			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        logger.info("Loc Id is:-->" + dbId + " " + locId);
	        
	        long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	
		    
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());			
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			
			// Setter of HrPay
			Date sysDate = new Date();
			
			
			logger.info("Coming into the Service method");
			HrDeducExpMst deducExpMst = (HrDeducExpMst)objectArgs.get("deducExpMst");
			HrDeducExpMst hrDeducExpMst = null;
			logger.info( "The BranchMaster VO is " + deducExpMst);
			
			DeducExpMasterDAOImpl deducExpMasterDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
		    String editMode=objectArgs.get("editMode").toString();
		    // Added By Urvin shah for deduc_type_code.
		    deductionId=Long.parseLong(objectArgs.get("deducCode").toString());
		    HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
		    DeducTypeMasterDAOImpl deducTypeMasterDAOImpl = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			if(langId!=1){	
				long languageId=1;
				logger.info("Y are in Gujarati Language");
				hrPayDeducTypeMst = deducTypeMasterDAOImpl.read(deductionId);
				elementCode = hrPayDeducTypeMst.getDeducTypeCode();
				hrPayDeducTypeMst = deducTypeMasterDAOImpl.getDeducTypeMasterByCode(elementCode, languageId);				
				logger.info("The DeductionId is :-"+hrPayDeducTypeMst.getDeducCode());
				deducExpMst.setHrPayDeducTypeMst(hrPayDeducTypeMst);
				
			}
			else{
				hrPayDeducTypeMst = deducTypeMasterDAOImpl.read(deductionId);
				deducExpMst.setHrPayDeducTypeMst(hrPayDeducTypeMst);
			}
		    
		    // End.
			if(editMode.equalsIgnoreCase("Y")) {
				logger.info("INSIDE UPDATE");
				hrDeducExpMst = deducExpMasterDAO.read(deducExpMst.getDeducRuleId());
				hrDeducExpMst.setDeducExpEffEndDt(deducExpMst.getDeducExpEffEndDt());
				hrDeducExpMst.setDeducExpEffStartDt(deducExpMst.getDeducExpEffStartDt());
				hrDeducExpMst.setDeducExpIsActive(deducExpMst.getDeducExpIsActive());
				hrDeducExpMst.setDeducRuleDesc(deducExpMst.getDeducRuleDesc());
				hrDeducExpMst.setDeducRuleExp(deducExpMst.getDeducRuleExp());
				hrDeducExpMst.setDeducRuleValue(deducExpMst.getDeducRuleValue());
				hrDeducExpMst.setHrPayDeducTypeMst(deducExpMst.getHrPayDeducTypeMst());
				hrDeducExpMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrDeducExpMst.setUpdatedDate(sysDate);
				hrDeducExpMst.setOrgUserMstByUpdatedBy(orgUserMst);
				
				deducExpMasterDAO.update(hrDeducExpMst);
				msg=1;
				objectArgs.put("msg", "Record Updated Successfully");
			}
			else {
				//System.out.println("INSIDE CREATE");
				IdGenerator idGen = new IdGenerator();
				long deducRuleId = idGen.PKGeneratorWODBLOC("hr_deduc_exp_mst", objectArgs);
				logger.info("The Deduction Rule Id is:-"+deducRuleId);				
				deducExpMst.setDeducRuleId(deducRuleId);
				deducExpMst.setCmnDatabaseMst(cmnDatabaseMst);
				deducExpMst.setCmnLocationMst(cmnLocationMst);
				deducExpMst.setCmnLanguageMst(cmnLanguageMst);
				deducExpMst.setCreatedDate(sysDate);
				deducExpMst.setOrgPostMstByCreatedByPost(orgPostMst);
				deducExpMst.setOrgUserMstByCreatedBy(orgUserMst);				
				deducExpMasterDAO.create(deducExpMst);
				objectArgs.put("msg", "Record Inserted Successfully");
			}
			
			List <HrDeducExpMst> actionList = deducExpMasterDAO.getDeducExpMasterData(langId);
			objectArgs.put("actionList", actionList);
			logger.info("Coming into the Service method" + actionList);
			
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			//resultObject.setViewName("deducExpMasterView");
			if(msg==1)
				resultObject.setViewName("updateDeducExpMaster");
			else
				resultObject.setViewName("insertDeducExpMaster");
			logger.info("INSERTED SUCCESSFULLY");
		}
		catch(ConstraintViolationException ex)
		{
			 //System.out.println("Constraint violation Ocuures...");
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		catch(Exception e)
		{
			
			//System.out.println("Exception Ocuures...");
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		return resultObject;	
	}
	public ResultObject getDeducComponentData(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		
		Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");
		long langId=Long.parseLong(loginDetailsMap.get("langId").toString());		
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");			
			logger.info("Y are in Insertion Mode :-");
			DeducTypeMasterDAOImpl deducTypeMasterDAOImpl = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			Long deducType=Long.valueOf(300159);	
			ExpressionMasterDAOImpl expressionMaster = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());	           
	        List componentMstList = expressionMaster.getAllComponentNames(langId);
	        logger.info("Size of Component List is:-"+componentMstList.size());
			DeducExpMasterDAOImpl deducExpMasterDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());	           
	        //List <HrPayDeducTypeMst> deducTypeList = deducExpMasterDAO.getAllDeductionNames(langId);
			/*if(langId!=1){
				langId=1;
			}*/
			List <HrPayDeducTypeMst> deducTypeList = deducTypeMasterDAOImpl.getDeductionTypeWithAllChilderns(deducType,langId);
	        //System.out.println("query executed"+deducTypeList.size());
	        
	        
	        objectArgs.put("deducTypeList", deducTypeList);
	        objectArgs.put("componentMstList", componentMstList);
	        logger.info("Y are Out of Insertion Mode :-");
	        resultObject.setResultCode(ErrorConstants.SUCCESS);
	        resultObject.setResultValue(objectArgs);
	        resultObject.setViewName("insertDeducExpMaster");
		}
		catch(Exception e){
			
		}
		return resultObject;
	}
	
	public ResultObject getDeducExpMasterDtls(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");
		long langId=Long.parseLong(loginDetailsMap.get("langId").toString());		
		try{			 
			//System.out.println("U r in Fetch Code Part"+langId);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			DeducExpMasterDAOImpl deducExpMasterDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());	           
	        List <HrDeducExpMst> actionList = (List)deducExpMasterDAO.getDeducExpMasterData(langId);	        
	        logger.info("The Size of List is:-"+actionList.size());	        
	        objectArgs.put("actionList", actionList);
	        resultObject.setResultCode(ErrorConstants.SUCCESS);
	        resultObject.setResultValue(objectArgs);
	        resultObject.setViewName("deducExpMasterView");
		}			
		catch(Exception e){				
			e.printStackTrace();
		}
		return resultObject;		
	}
	/**
	 * Function:- This method will fetch perticular Deduction Rule Id related Data as well as Component Master and 
	 *            Deduction Type Master Data.
	 * Author:-   Urvin shah
	 * Created On:- 27th July 2007.
	 * @param objectArgs
	 * @return resultObject.
	 */
	
	
	
	public ResultObject getDeducRuleData(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");
		long langId=Long.parseLong(loginDetailsMap.get("langId").toString());		
		try{
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			DeducExpMasterDAOImpl deducExpMasterDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
			
			
			
	        List <HrPayDeducTypeMst> deducTypeList = deducExpMasterDAO.getAllDeductionNames(langId);
	        logger.info("The Deduction Name are been fetched and size is:-"+deducTypeList.size());
	        ExpressionMasterDAOImpl expressionMaster = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());	           
	        List componentMstList = expressionMaster.getAllComponentNames(langId);
	        logger.info("The Components are been fetched and size is:-"+componentMstList.size());
	        long deducRuleId = Long.parseLong(voToService.get("deducRuleId").toString());
	        HrDeducExpMst hrDeducExpMst = deducExpMasterDAO.read(deducRuleId);
	        DeducTypeMasterDAOImpl deducTypeMasterDAOImpl = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
	        HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();        
	        hrPayDeducTypeMst=deducTypeMasterDAOImpl.getDeducTypeMasterByCode(hrDeducExpMst.getHrPayDeducTypeMst().getDeducTypeCode(),langId);
	        logger.info("The Deduction Name is:-"+hrDeducExpMst.getHrPayDeducTypeMst().getDeducName());
	        logger.info("The Components are been fetched and size is:-"+componentMstList.size());
	        //System.out.println("query executed"+deducTypeList.size());
	        //System.out.println("query executed"+componentMstList.size());	       
	        objectArgs.put("deducTypeList", deducTypeList);
	        objectArgs.put("componentMstList", componentMstList);
	        objectArgs.put("hrDeducExpMst", hrDeducExpMst);
	        objectArgs.put("hrPayDeducTypeMst", hrPayDeducTypeMst);
	        resultObject.setResultCode(ErrorConstants.SUCCESS);
	        resultObject.setResultValue(objectArgs);
	        resultObject.setViewName("updateDeducExpMaster");			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultObject;
		
	}
	public ResultObject chkDeducRuleDesc(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");
        StringBuffer allowNameBf=new StringBuffer();
        String check; 
		long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			DeducExpMasterDAOImpl deducExpMasterDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
            String newDeducRuleDesc = request.getParameter("deducRuleDesc");  
           // System.out.println("Before The Changed Deduction Name is:-"+newDeducRuleDesc);
            newDeducRuleDesc=newDeducRuleDesc.toLowerCase();
           // System.out.println("After The Changed Deduction Name is:-"+newDeducRuleDesc);
            List <HrPayAllowTypeMst> actionList = deducExpMasterDAO.checkDeducRule(newDeducRuleDesc,langId);
           // System.out.println("U r in checking Part");
           // System.out.println("The Size of List is:-"+actionList.size());
            
            if(actionList.size()==0){
            	check="false";
            	allowNameBf.append("<deducNameMapping>");
            	allowNameBf.append("<availability>").append(check).append("</availability>");
            	allowNameBf.append("</deducNameMapping>");            	
            }
            else {
            	check="true";
            	allowNameBf.append("<deducNameMapping>");
            	allowNameBf.append("<availability>").append(check).append("</availability>");
            	allowNameBf.append("</deducNameMapping>");
            }            
            String deducNameMapping = new AjaxXmlBuilder().addItem("ajax_key", allowNameBf.toString()).toString();
	         
            logger.info(" the string buffer is :"+deducNameMapping);
            objectArgs.put("ajaxKey", deducNameMapping);
	         resultObject.setResultCode(ErrorConstants.SUCCESS); 
	         resultObject.setResultValue(objectArgs);
	         resultObject.setViewName("ajaxData");    			
		}
		catch(Exception e){
			logger.error("Exception while generating VO to XML ", e);
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			retObj.setViewName("ajaxError");
			objectArgs.put("ajaxKey", "ERROR");
			retObj.setResultValue(objectArgs);
			retObj.setThrowable(e);
			return retObj;

		}
		return resultObject;
	}

}
