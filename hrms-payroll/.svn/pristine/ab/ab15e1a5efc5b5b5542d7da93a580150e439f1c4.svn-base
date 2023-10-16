package com.tcs.sgv.allowance.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.allowance.dao.AllowTypeMasterDAOImpl;
import com.tcs.sgv.allowance.dao.ExpressionMasterDAO;
import com.tcs.sgv.allowance.dao.ExpressionMasterDAOImpl;
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
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ExpressionMasterServiceImpl extends ServiceImpl{
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	public ResultObject insertExpressionMasterDtls(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");		

		try{
			logger.info("Coming into the Service method");
			HrPayExpressionMst exprMst = (HrPayExpressionMst)objectArgs.get("exprMst");
			logger.info( "The BranchMaster VO is " + exprMst);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			ExpressionMasterDAOImpl exprMasterDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());			
			AllowTypeMasterDAOImpl allowTypeMasterDaoImpl = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());
			HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
			long allowanceId;			
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());

			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());			
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
				
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	
			
			String editFromVO = objectArgs.get("edit").toString();
			allowanceId=Long.parseLong(objectArgs.get("allowCode").toString());
			if(langId!=1){	
				
				hrPayAllowTypeMst = allowTypeMasterDaoImpl.getAllowTypeMasterByCode(allowanceId, langId);				
				logger.info("The AllowanceId is :-"+hrPayAllowTypeMst.getAllowCode());
				exprMst.setHrPayAllowTypeMst(hrPayAllowTypeMst);								
			}
			else{
				hrPayAllowTypeMst = allowTypeMasterDaoImpl.read(allowanceId);
				exprMst.setHrPayAllowTypeMst(hrPayAllowTypeMst);
			}
			if(editFromVO!=null && editFromVO.equals("Y")) {
				logger.info("INSIDE UPDATE");
				ExpressionMasterDAOImpl exprDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
				HrPayExpressionMst exprMstObj = exprDAO.read(exprMst.getRuleId());
				
				exprMstObj.setIsactive(exprMst.getIsactive());
				exprMstObj.setRuleDescription(exprMst.getRuleDescription());
				exprMstObj.setRuleExpression(exprMst.getRuleExpression());
				exprMstObj.setRuleValue(exprMst.getRuleValue());
				exprMstObj.setHrPayAllowTypeMst(exprMst.getHrPayAllowTypeMst());
				exprMstObj.setOrgUserMstByUpdatedBy(orgUserMst);
				exprMstObj.setRuleEffStartDate(exprMst.getRuleEffStartDate());
				exprMstObj.setRuleEffEndDate(exprMst.getRuleEffEndDate());
				exprMstObj.setOrgPostMstByUpdatedByPost(orgPostMst);
				Date sysdate = new Date();															
				exprMstObj.setUpdatedDate(sysdate);
				 
				exprMasterDAO.update(exprMstObj);
				objectArgs.put("msg", "Record Updated Successfully");
				msg=1;
			}
			else if(editFromVO!=null && editFromVO.equals("N")) {				
				logger.info("INSIDE CREATE");
				IdGenerator idGen = new IdGenerator();
				long ruleId = idGen.PKGeneratorWODBLOC("hr_pay_expression_mst", objectArgs);
								
				exprMst.setRuleId(ruleId);
				
				long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
		        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
		        logger.info("Loc Id is:-->" + dbId + " " + locId);
		       		        				
				

				Date sysdate = new Date();
				exprMst.setCmnLanguageMst(cmnLanguageMst);
				exprMst.setCreatedDate(sysdate);						
				exprMst.setCmnDatabaseMst(cmnDatabaseMst);
				exprMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				exprMst.setOrgPostMstByCreatedByPost(orgPostMst);
				exprMst.setOrgUserMstByCreatedBy(orgUserMst);
				exprMst.setCmnLocationMst(cmnLocationMst);
				
				exprMasterDAO.create(exprMst);
				objectArgs.put("msg", "Record Inserted Successfully");
			}
			else
			{
				throw new NullPointerException();
			}
			
			//added by Ankit Bhatt for "redirectMap"
			/*List <HrPayExpressionMst> actionList = exprMasterDAO.getAllExprMasterData(langId);
			objectArgs.put("actionList", actionList);
			logger.info("Coming into the Service method" + actionList.size());
			
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("exprMasterView");*/
			
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getExprView");
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			objectArgs.put("redirectMap", redirectMap);			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			if(msg==1)
				resultObject.setViewName("exprEditList");
			else
				resultObject.setViewName("exprMaster");
			//resultObject.setViewName("redirect view");
			
			
			//ended by Ankit Bhatt.
			logger.info("INSERTED SUCCESSFULLY");
		}
	
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertExpressionMasterDtls");
			 ne.printStackTrace();
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		catch(ConstraintViolationException ex)
		{
			 logger.info("Constraint violation Ocuures...insertExpressionMasterDtls");
			 ex.printStackTrace();
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		catch(Exception e)
		{
			
			logger.info("Exception Ocuures...insertExpressionMasterDtls");
			e.printStackTrace();
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		return resultObject;
	}
	
	public ResultObject checkRuleDesc(Map objectArgs)
	{
		
		logger.info("IN checkRuleDesc Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        try{
        	ExpressionMasterDAOImpl exprMasterDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
	        Map voToService = (Map)objectArgs.get("voToServiceMap");
	        long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	        String strruleDesc = (String)voToService.get("ruleDesc");
	        String oldname = (String)voToService.get("oldname");
	                
	        if(oldname!= null && (!oldname.equals(""))) {				
	        	oldname= oldname.trim().toLowerCase();
			}	
	        StringBuffer propertyList = new StringBuffer();
	        strruleDesc = strruleDesc.trim().toLowerCase();
	        if(!strruleDesc.equals(oldname)) {
	           List bankName = exprMasterDAO.checkRuleDesc(strruleDesc,langId);	         
		       propertyList.append("<rule-desc>");   	
		       propertyList.append("<list-size>").append(bankName.size()).append("</list-size>");
		       propertyList.append("</rule-desc>"); 
		       logger.info("Rule Desc size " + bankName.size());
	        }
	        else {
	        	propertyList.append("<rule-desc>");   	
	            propertyList.append("<list-size>").append(0).append("</list-size>");
	            propertyList.append("</rule-desc>");
	        }
	        
	        Map result = new HashMap();
	        String ruleDescIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
	        
	        result.put("ajaxKey", ruleDescIdStr);
	           
	        resultObject.setResultValue(result);
	        resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
	        logger.info("After Service Called.........\n");
		}
		catch(Exception e)
		{
			logger.info("Exception Ocuures...insertExpressionMasterDtls");
			e.printStackTrace();
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
			e.printStackTrace();
		}
		return resultObject;
	}
	
	
		
	public ResultObject getExpressionMasterDtls(Map objectArgs)
	{
		logger.info("IN Rule Master Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");		
		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            ExpressionMasterDAO exprMasterDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	            List <HrPayExpressionMst> actionList = exprMasterDAO.getAllExprMasterData(langId);	        
	            Map map = new HashMap();
	            map.put("actionList", actionList);
	            map.put("listSize",actionList.size());
	            logger.info("List size of Expr:--->" + actionList.size());
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	            resultObject.setViewName("exprMasterView");
		}
		catch(Exception e)
		{	
			logger.info("Exception Ocuures...insertExpressionMasterDtls");
			e.printStackTrace();
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
			e.printStackTrace();
		}	
		return resultObject;
	}

	
	public ResultObject getRuleData(Map objectArgs)
	{
		logger.info("IN Expression Edit Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		 Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
		try
		{
			
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            ExpressionMasterDAO exprMasterDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
	            
        		//Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");	           
    			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
    			    		
    			Map voToService = (Map)objectArgs.get("voToServiceMap");
    			
	            if(voToService.get("edit")!= null && voToService.get("edit").equals("Y"))
	            {
	            		logger.info("I m in edit mode ");
	            		long rule_id = Long.parseLong((String)voToService.get("ruleid"));
	            		
	            		
	            		HrPayExpressionMst actionList = (HrPayExpressionMst)exprMasterDAO.getRuleIdData(rule_id);
	            		//if(langId!=1){
	            			AllowTypeMasterDAOImpl allowTypeMasterDaoImpl = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());
	            			HrPayAllowTypeMst hrAllowTypeMst = new HrPayAllowTypeMst();
	            			hrAllowTypeMst = allowTypeMasterDaoImpl.getAllowTypeMasterData(actionList.getHrPayAllowTypeMst().getAllowCode(), langId);
	            			objectArgs.put("hrAllowTypeMst", hrAllowTypeMst);
	            			
	            		//}
	            		
	            		 
	            		List allowNames = exprMasterDAO.getAllAllowances(langId);            	
	        			List componentNames = exprMasterDAO.getAllComponentNames(langId);
	        			//Map map = new HashMap();            
	        				
	                    //map.put("countryListSize",countryList.size());            
	        			objectArgs.put("allowNames", allowNames);
	        			
	        			objectArgs.put("componentNames", componentNames);	        			            	   
	        			objectArgs.put("actionList", actionList);
				        
				        resultObject.setResultCode(ErrorConstants.SUCCESS);
				        resultObject.setResultValue(objectArgs);
				        resultObject.setViewName("exprEditList");
	            }
	            else
	            {
			            List <HrPayExpressionMst> actionList = exprMasterDAO.getAllExprMasterData(langId);
			            Map map = new HashMap();
			            map.put("actionList", actionList);
			            resultObject.setResultCode(ErrorConstants.SUCCESS);
			            resultObject.setResultValue(map);
			            resultObject.setViewName("exprMasterView");
	            }			            			
		}
		catch(Exception e)
		{		
			logger.info("Exception Ocuures...insertExpressionMasterDtls");
			e.printStackTrace();
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
			e.printStackTrace();
		}	
		return resultObject;
	}	
	
	public ResultObject getExpressionMaster(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try{
			
			long langId= StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			logger.info("LangID in getExpressionMaster " + langId);
			//long langId = 1;
			
			ExpressionMasterDAO exprMasterDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
			
			//List AllowcodeList= exprMasterDAO.getAllallowCode();
			//List allowNames = exprMasterDAO.getAllallowCodeData();
			List allowNames = exprMasterDAO.getAllAllowanceNames(langId);
			List componentNames = exprMasterDAO.getAllComponentNames(langId);
			Map map = new HashMap();            
            //map.put("countryListSize",countryList.size());            
			map.put("allowNames", allowNames);
			map.put("componentNames", componentNames);
            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(map);
            resultObject.setViewName("exprMaster");
	}
	catch(Exception e)
	{		
		logger.info("Exception Ocuures...insertExpressionMasterDtls");
		e.printStackTrace();
		 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
		 resultObject.setResultValue(objectArgs);
		 resultObject.setViewName("errorInsert");
		e.printStackTrace();
	}
	return resultObject;
 }	
	
	
}
