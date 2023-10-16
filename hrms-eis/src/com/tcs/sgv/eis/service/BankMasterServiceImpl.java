package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankMasterDAO;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.util.BankMasterServiceImplHelper;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class BankMasterServiceImpl extends ServiceImpl implements BankMasterService {
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	public ResultObject insertBankMasterDtls(Map objectArgs) 
	{
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Coming into the Service method insertBankMasterDtls");
			MstBankPay bankMst = (MstBankPay)objectArgs.get("bankMst");
			logger.info( "The BankMaster VO is " + bankMst);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			String editFromVO = objectArgs.get("edit").toString();
			
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			String locId=loginDetailsMap.get("locationId").toString();
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			
			long postttId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString()); 
			logger.info("loggedInPost loggedInPost" + postttId);
			
			//BankMasterDAOImpl bankMasterDAO = new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
			
            logger.info("editFromVO insertBankMasterDtls" + editFromVO);
	        logger.info("insertBankMasterDtls Loc Id is:-->" + dbId + " " + locId);
            
	        
	        BankMasterServiceImplHelper helper = new BankMasterServiceImplHelper(serv);
	        
	        
	        
	        
            if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
			{
            	logger.info("INSIDE UPDATE");
            	helper.updateBankMasterDtls(bankMst, postId, userId);
            	
				objectArgs.put("msg", "Record Updated Successfully");
				msg=1;
			}
			else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
			{
				logger.info("INSIDE CREATE insertBankMasterDtls ********************************* "+objectArgs);
				IdGenerator idGen = new IdGenerator();
				long bankId = idGen.PKGeneratorWODBLOC("MST_BANK_PAY", objectArgs);
				//long bankId = IDGenerateDelegate.getNextId("MST_BANK_PAY", objectArgs);
				logger.info("INSIDE INSERT BankMasterServiceImplHelper bankId" + bankId);	
				helper.inserBankMasteDtls(bankMst,bankId,langId,dbId,postId,userId,locId);
				objectArgs.put("msg", "Record Inserted Successfully");
			}
            else
			{
				throw new NullPointerException();
			}
			
           
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getBankView");
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			objectArgs.put("redirectMap", redirectMap);			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("bankMaster");
			
			logger.info("INSERTED SUCCESSFULLY insertBankMasterDtls");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			

		}
		catch(Exception e){
			
			logger.info("Exception Ocuures...insertBankMasterDtls");
			 logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
			

		}
		return resultObject;
	}
	
	
	
	public ResultObject checkBankName(Map objectArgs)
	{
		
		logger.info("IN checkBankName Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Map voToService = (Map)objectArgs.get("voToServiceMap");
        
        try{
        	
        	BankMasterDAOImpl bankMasterDAO = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());
        
        	String strbankName = (String)voToService.get("bankName");
            String oldname = (String)voToService.get("oldname");
            logger.info("checkBankName  Old name in service is " + oldname);
            if(oldname!= null && (!oldname.equals("")))        
	  	    {			
        	  oldname= oldname.trim().toLowerCase();
		    }	
           StringBuffer propertyList = new StringBuffer();
           strbankName = strbankName.trim().toLowerCase();
           if(!strbankName.equals(oldname))
           {
        	   Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        	   long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
        	   List bankName = bankMasterDAO.checkBankName(strbankName,langId);
         
            propertyList.append("<bank-name>");   	
            propertyList.append("<list-size>").append(bankName.size()).append("</list-size>");
            propertyList.append("</bank-name>"); 
            logger.info("Bank Name size " + bankName.size());
           }
          else
          {
        	propertyList.append("<bank-name>");   	
            propertyList.append("<list-size>").append(0).append("</list-size>");
            propertyList.append("</bank-name>");
          }
        
          Map result = new HashMap();
          String bankNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
          result=objectArgs;
          result.put("ajaxKey", bankNameIdStr);
          
          resultObject.setResultValue(result);
          resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
          logger.info("checkBankName After Service Called.........\n");
		}
		catch(Exception e)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			/*
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			resultObject.setResultValue(result);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());*/
		}
		return resultObject;
	}
	
		
	public ResultObject getBankMasterDtls(Map objectArgs)
	{
		logger.info("getBankMasterDtls IN Bank Master Data getBankMasterDtls, by Ankit, in integrated WS");						
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
   		
		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            BankMasterDAO bankMasterDAO = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	            
	            List <MstBankPay> actionList = bankMasterDAO.getAllBankMasterData(langId);	            	           
	            Map map = new HashMap();
	            map=objectArgs;
	            map.put("actionList", actionList);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	           resultObject.setViewName("bankMasterView");
	            //resultObject.setViewName("OtherEditListMergedNew");
	            //resultObject.setViewName("BulkAllowances");
		}
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			/*
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			resultObject.setResultValue(result);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());*/
		}
	
		return resultObject;
	}

	
	public ResultObject getBankData(Map objectArgs)
	{
		logger.info("getBankData IN Bank Master Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		
		try
		{
			
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
	            Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				String editFlag = (String)voToService.get("edit");
				
		        logger.info("editFromVO in BankService getBankData" + voToService.get("edit"));
				
	            BankMasterDAO bankMasterDAO = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());
	            if(editFlag != null && editFlag.equals("Y"))
	            {
	            		logger.info("getBankData I m in edit mode ");
	            		long Bankid = Long.parseLong((String)voToService.get("bankid"));
	            		MstBankPay actionList = (MstBankPay)bankMasterDAO.getBankIdData(Bankid,langId);	            		     	   
	            		logger.info("getBankData Bank Name is " + actionList.getBankName());
	            		
	            		Map map = new HashMap();
	            	    
	            	    map=objectArgs;
				        map.put("actionList", actionList);
				        resultObject.setResultCode(ErrorConstants.SUCCESS);
				        resultObject.setResultValue(map);
				        //resultObject.setViewName("bankEditList");
				        resultObject.setViewName("bankMaster");
	            }
	            else
	            {
			            List <HrEisEmpMst> actionList = bankMasterDAO.getAllBankMasterData(langId);
			            Map map = new HashMap();
			           map=objectArgs;
			            map.put("actionList", actionList);
			            resultObject.setResultCode(ErrorConstants.SUCCESS);
			            resultObject.setResultValue(map);
			            resultObject.setViewName("bankMasterView");
	            }			            			
		}
		catch(PropertyValueException pe)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			/*
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			resultObject.setResultValue(result);
			resultObject.setThrowable(pe);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			plogger.error("Error is: "+ e.getMessage());*/

		}
		catch(Exception e)
		{	
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			/*
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			resultObject.setResultValue(result);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());*/
		}	
		return resultObject;
	}						
}
