package com.tcs.sgv.ess.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.dao.GeneralEmpInfoDaoImpl;
import com.tcs.sgv.ess.valueobject.GeneralEmpInfoVO;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public class GeneralEmpInfo extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	
	
	public ResultObject ShowEmpDetail(Map objectArgs) {

	   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	   
	    System.out.println("hi i am in emp service");
	   
	    /* login code */
	 Map   loginMap = (Map) objectArgs.get("baseLoginMap");
			 long userID = Long.parseLong(loginMap.get("userId").toString());
			 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			 long dbId = Long.parseLong(loginMap.get("dbId").toString());
			 long locId =  Long.parseLong(loginMap.get("locationId").toString());
			 long langId = Long.parseLong(loginMap.get("langId").toString());

	   	try
	    {
	        if (objRes != null && objectArgs != null)
	        {
	        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	        	GeneralEmpInfoDaoImpl GemEmpinfo = new GeneralEmpInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
	        	System.out.println("Lang ID ====----------"+langId);
                         GeneralEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(userID);
                        
	            
	                 	objRes.setResultCode(ErrorConstants.SUCCESS);
	                 	objectArgs.put("EmpDet", EmpDetailsVO);
		                logger.info("The objectArgs is : "+objectArgs);
		                objRes.setResultValue(objectArgs);
		               objRes.setViewName("EmployeeInfo");
	                 
	            }
	        
	    }
		      		  
		      		  catch (Exception e)
			            {
			                
			            	e.printStackTrace();
		      			  logger.info("\n Error occur in insertion of Leave details");
			                 Map result = new HashMap();
			                 objRes.setResultValue(result);
			                 objRes.setThrowable(e);
			                 objRes.setResultCode(ErrorConstants.ERROR);
			                 objRes.setThrowable(e);
			                 objRes.setViewName("errorPage");
			            }

		      		return objRes;
		     	  
	            }	
}





//added by Saurabh S

/*public ResultObject ShowEmpDetail(Map objectArgs) {

   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
   
    System.out.println("hi i am in emp service");
   
    /* login code */
 /*Map   loginMap = (Map) objectArgs.get("baseLoginMap");
		 long userID = Long.parseLong(loginMap.get("userId").toString());
		 long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
		 long dbId = Long.parseLong(loginMap.get("dbId").toString());
		 long locId =  Long.parseLong(loginMap.get("locationId").toString());
		 long langId = Long.parseLong(loginMap.get("langId").toString());

   	try
    {
        if (objRes != null && objectArgs != null)
        {
        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        	GeneralEmpInfoDaoImpl GemEmpinfo = new GeneralEmpInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
        	System.out.println("Lang ID ====----------"+langId);
                     GeneralEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(userID);
                    
            
                 	objRes.setResultCode(ErrorConstants.SUCCESS);
                 	objectArgs.put("EmpDet", EmpDetailsVO);
	                logger.info("The objectArgs is : "+objectArgs);
	                objRes.setResultValue(objectArgs);
	               objRes.setViewName("EmployeeInfo");
                 
            }
        
    }
	      		  
	      		  catch (Exception e)
		            {
		                
		            	e.printStackTrace();
	      			  logger.info("\n Error occur in insertion of Leave details");
		                 Map result = new HashMap();
		                 objRes.setResultValue(result);
		                 objRes.setThrowable(e);
		                 objRes.setResultCode(ErrorConstants.ERROR);
		                 objRes.setThrowable(e);
		                 objRes.setViewName("neww");
		            }

	      		return objRes;
	     	  
            }	
}*/


