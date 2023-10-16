/**
 * This is the AllowTypeMasterServiceImpl class which implements the AllowTypeMasterService interface which contains the
 * service for Allowance Type Master.
 * Made By:- Urvin shah
 * Date:- 14/07/2007
 */

package com.tcs.sgv.deduction.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.allowance.service.IdGenerator;
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
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class DeducMasterService extends ServiceImpl {
	Log logger = LogFactory.getLog( getClass() );
	
	int msg=0;
	
	public ResultObject checkDeducAvailability(Map objectArgs){		
		logger.info("inside checkDeducAvailability");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		//HttpSession session=request.getSession();
        //Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
        StringBuffer deducNameBf=new StringBuffer();
        String check;
        Map voToService = (Map)objectArgs.get("voToServiceMap");
		//long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			DeducTypeMasterDAOImpl deducTypeMasterDAO = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
            //String newDeducName = request.getParameter("newDeducName");  
            String newDeducName = (String)voToService.get("newDeducName");
            //logger.info("The Deduction name is--------********>"+newDeducName);
            
            newDeducName=newDeducName.toLowerCase();
            
            List <HrPayDeducTypeMst> actionList = deducTypeMasterDAO.checkDeducNameAvailability(newDeducName);
           
            
            if(actionList.size()==0){
            	check="false";
            	deducNameBf.append("<deducNameMapping>");
            	deducNameBf.append("<availability>").append(check).append("</availability>");
            	deducNameBf.append("</deducNameMapping>");            	
            }
            else {
            	check="true";
            	deducNameBf.append("<deducNameMapping>");
            	deducNameBf.append("<availability>").append(check).append("</availability>");
            	deducNameBf.append("</deducNameMapping>");
            }
            Map map = new HashMap();
            String deducNameMapping = new AjaxXmlBuilder().addItem("ajax_key", deducNameBf.toString()).toString();
	         
             map.put("ajaxKey", deducNameMapping);
	         resultObject.setResultCode(ErrorConstants.SUCCESS); 
	         resultObject.setResultValue(map);
	         resultObject.setViewName("ajaxData");    
	        
	                     
	         //resultObject.setViewName("allowTypeMasterView");			
		}
		catch(Exception e){
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			e.printStackTrace();
		}
		return resultObject;
	}
	
	
	

	
	
	public ResultObject insertDeducTypeMasterDtls(Map objectArgs) {
		
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
		HttpSession session=request.getSession();
        /*Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
		long langId=Long.parseLong(loginDetailsMap.get("langId").toString());*/
		Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");
		
		try{
			
			HrPayDeducTypeMst deducTypeMst = (HrPayDeducTypeMst)objectArgs.get("deducTypeMst");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			//logger.info("Deduction Description is-------->"+deducTypeMst.getDeducDesc());
			
			DeducTypeMasterDAOImpl deducTypeMasterDAO = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			
			
			String editFromVO = objectArgs.get("edit").toString();
            logger.info("editFromVO " + editFromVO);
            
            long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        logger.info("Loc Id is:-->" + dbId + " " + locId);
	       
	        langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			Date sysdate = new Date();
            
			//if(request.getParameter("edit")!= null && request.getParameter("edit").equals("Y"))
            if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))
			{							
            	long deducCode= deducTypeMst.getDeducCode();
            	logger.info("inside edit mode of deduction--------->");
            	
            	HrPayDeducTypeMst hrPayDeducTypeMst = deducTypeMasterDAO.read(deducCode);
            	hrPayDeducTypeMst.setDeducName(deducTypeMst.getDeducName());
            	hrPayDeducTypeMst.setDeducDisplayName(deducTypeMst.getDeducDisplayName());
            	hrPayDeducTypeMst.setDeducDesc(deducTypeMst.getDeducDesc());
            	hrPayDeducTypeMst.setDeducType(deducTypeMst.getDeducType());
            	hrPayDeducTypeMst.setOrgPostMstByUpdatedByPost(orgPostMst);
            	hrPayDeducTypeMst.setOrgUserMstByUpdatedBy(orgUserMst);
            	hrPayDeducTypeMst.setUpdatedDate(sysdate);
				deducTypeMasterDAO.update(hrPayDeducTypeMst);
				msg=1;
			}
            else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
			{													
				IdGenerator idGen = new IdGenerator();
				Long id= idGen.PKGeneratorWODBLOC("hr_pay_deduc_type_mst",objectArgs);				
				deducTypeMst.setDeducCode(id);
				
				
				deducTypeMst.setCmnLanguageMst(cmnLanguageMst);
				deducTypeMst.setCmnDatabaseMst(cmnDatabaseMst);
				deducTypeMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				deducTypeMst.setOrgPostMstByCreatedByPost(orgPostMst);
				deducTypeMst.setCmnLocationMst(cmnLocationMst);
				deducTypeMst.setOrgUserMstByUpdatedBy(orgUserMst);
				deducTypeMst.setOrgUserMstByCreatedBy(orgUserMst);
				deducTypeMst.setCreatedDate(sysdate);				 				 							
				
				
				
				try
				{
					deducTypeMasterDAO.create(deducTypeMst);					
					logger.info("There is some error after insert");
				}
				catch(Exception e)
				{
					e.printStackTrace();				
				}
			}
            
            else
			{
				throw new NullPointerException();
			}
			
            //added by Ankit Bhatt for redirectMap
			/*DeducTypeMasterDAOImpl allowTypeDAO = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class, serv.getSessionFactory());
			List <HrPayDeducTypeMst> actionList = allowTypeDAO.getDeducTypeMasterData(langId);	
			for (int i=0;i<actionList.size();i++)
            {
            	HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
            	hrPayDeducTypeMst = actionList.get(i);
            	String x = hrPayDeducTypeMst.getDeducName()+hrPayDeducTypeMst.getDeducDesc();               
            }
			objectArgs.put("actionList",actionList);*/
            
            Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getDeducData");
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			objectArgs.put("redirectMap", redirectMap);			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			//resultObject.setViewName("redirect view");
			if(msg==1)
				resultObject.setViewName("deducEditList");
			else
				resultObject.setViewName("newDeducMaster");
			
			logger.info(" insertGradeDataDetail Coming into the Service method");
			
						
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			/*resultObject.setResultValue(objectArgs);
			resultObject.setViewName("deducViewList");*/
//			ended by Ankit Bhatt.
			logger.info("INSERTED SUCCESSFULLY");
			logger.info("The insert operation is successfully completed");
		}
	     catch(ConstraintViolationException ex)
	     {
	    	 logger.info("TransactionSystemException occurs...");
	     }
		catch(Exception e){
			logger.info("The error is:-");
			logger.info("There is some error at editting or inserting time");
			//Map result = new HashMap();
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			e.printStackTrace();
		}
		return resultObject;
	}
	
	public ResultObject getDeductionTypes(Map objectArgs){
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			
			logger.info("inside getDeducData------------>");
			try{
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				DeducTypeMasterDAOImpl deducTypeMasterDAO = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
	           	Map loginMap = (Map) objectArgs.get("baseLoginMap");
				long langId=Long.parseLong(loginMap.get("langId").toString());	            
	            List actionList = deducTypeMasterDAO.getDeductionTypes(langId);
	            //CmnLookupMst cmnLookupMst=new CmnLookupMst();
	            logger.info("The Size of List "+actionList.size());            	
            	objectArgs.put("actionList", actionList);	            	
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objectArgs);
		        resultObject.setViewName("newDeducMaster"); 	           
			}
			catch(Exception e){
				logger.info("Inside Catch.Exception occurs------>");
				e.printStackTrace();
			}
			return resultObject;
		}

	
	
	public ResultObject getDeducData(Map objectArgs){
		
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		logger.info("inside getDeducData------------>");
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			DeducTypeMasterDAOImpl deducTypeMasterDAO = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());        	
	       
           	Map loginMap = (Map) objectArgs.get("baseLoginMap");

			long langId=Long.parseLong(loginMap.get("langId").toString());
           
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String editFlag = (String)voToService.get("edit");
			
			//if(request.getParameter("edit")!= null && request.getParameter("edit").equals("Y"))
			if(editFlag != null && editFlag.equals("Y"))
            {
            	//String dCode=request.getParameter("deducCode");
				String dCode=(String)voToService.get("deducCode");
				String dName=(String)voToService.get("deducName");
            	String dDesc=(String)voToService.get("deducDesc");
            	
            	long deducCode=Long.parseLong(dCode);
            	HrPayDeducTypeMst actionList = deducTypeMasterDAO.getDeducTypeMasterDataByCode(langId,deducCode);
            	List deducTypeList = deducTypeMasterDAO.getDeductionTypes(langId);
            	         	
            	
            	objectArgs.put("actionList", actionList);
            	objectArgs.put("deducTypeList", deducTypeList);
            	
            	
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objectArgs);
		        resultObject.setViewName("deducEditList");
            	
            }
			else
			{
				List  <HrPayDeducTypeMst> actionList = deducTypeMasterDAO.getDeducTypeMasterData(langId);
				for (int i=0;i<actionList.size();i++)
	            {
	            	HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
	            	hrPayDeducTypeMst = actionList.get(i);
	            	//String x = hrPayDeducTypeMst.getDeducName()+hrPayDeducTypeMst.getDeducDesc();               
	            }
				logger.info("The Size of List is:-"+actionList.size());
				
				objectArgs.put("actionList", actionList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("deducViewList");
				logger.info("Mrugesh----------->");
			}
		}
		catch(Exception e){
			logger.info("Inside Catch.Exception occurs------>");
			e.printStackTrace();
		}
		return resultObject;
	}
		
		
		
		
	}
	

