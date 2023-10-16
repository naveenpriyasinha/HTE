package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jasper.compiler.ServletWriter;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisGrpMangMst;
import com.tcs.sgv.eis.valueobject.HrPaySignatureDtls;


public class GroupManagementVOGENImpl extends ServiceImpl  {

	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);	
	
	public ResultObject showGroupManagementVOGENImpl(Map<String, Object> objectArgs) 
	{
		try
		{
			logger.info("GroupManagementVOGENImpl >>>>>  showGroupManagement is  Called !!! ");		
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);	 
                
		}
		catch(Exception e)
		{
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...showGroupManagement");
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());		
		}		
		return retObj;
	}
	
	public ResultObject saveGroupManagementVOGENImpl(Map<String,Object> objectArgs)

	{
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			long groupManagementId = 0;
			String editMode = StringUtility.getParameter("edit",request);
			logger.info("editMode ::::::::: " +editMode );
			HrEisGrpMangMst hrEisGrpMangMst;
			if(editMode.equalsIgnoreCase("Y")) 
			{	
				 String groupManagementId1 = StringUtility.getParameter("groupManagementId", request);
				 logger.info("This is VOGEN schedulerId::::::::::::::::::::::" + groupManagementId1);
				 //logger.info("This is VOGEN schedulerId::::::::::::::::::::::" + schedulerId);
				 if( groupManagementId1!=null ){
					 groupManagementId = Long.parseLong(groupManagementId1);
				  }
				 hrEisGrpMangMst = new HrEisGrpMangMst();
				 objectArgs.put("edit",editMode);
				 objectArgs.put("groupManagementId",groupManagementId);
				 
			}
	        else
	        {  
	        logger.info("This is VOGEN editMode::::::::::::::::::::::" + editMode);
	        hrEisGrpMangMst = new HrEisGrpMangMst();
			objectArgs.put("edit","N");    	 
			 		 
	        }
			
			
			
			
			
			logger.info("::::::::::::::  saveGroupManagementVOGENImpl is  Called");		
			
			String typeId =StringUtility.getParameter("TypeId", request);
			logger.info("typeId::::::::: " + typeId);
			
			String componentId =StringUtility.getParameter("Component", request);
			logger.info("componentId::::::::: " + componentId);
			
			String startDt =StringUtility.getParameter("startDt", request);
			logger.info("startDt::::::::: " + startDt);
			
			String endDt =StringUtility.getParameter("endDt", request);
			logger.info("endDt::::::::: " + endDt);
			
			String amount =StringUtility.getParameter("Amount", request);
			logger.info("amount::::::::: " + amount);
			
			String classId =StringUtility.getParameter("Class", request);
			logger.info("classId::::::::: " + classId);
			
			String desigList =StringUtility.getParameter("desigList", request);
			logger.info( "desigList as in JSP::::::::::::: "+ desigList);
			logger.info("desigList::::::::: " + Long.parseLong(desigList));
			String desgn =StringUtility.getParameter("txtDesig", request);
			logger.info("desgn:::::::::::::::::::::::::::::::::::"+desgn);
			logger.info("desgn length is:::::: " + desgn.length());
			String designation = "";
			if(editMode.equalsIgnoreCase("Y")|| Long.parseLong(desigList) != -1)
			{
				if(desgn.substring(0).equalsIgnoreCase(","))
				{
				designation = desgn.substring(1, desgn.length());
				logger.info("designation in edit mode::::::::: " + designation);
				}
				else
				{
					designation = desgn;
				}
			}
			else
			{
			designation = desgn.substring(0, desgn.length()-1);
			logger.info("designation::::::::: " + designation);
			}
			
			
			objectArgs.put("TypeId",typeId);
			objectArgs.put("ComponentId",componentId);
			objectArgs.put("StartDt",startDt);
			objectArgs.put("EndDt",endDt);
			objectArgs.put("Amount",amount);
			objectArgs.put("ClassId",classId);
			objectArgs.put("designation",designation);
						
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...//**//**//**");
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());		
		}		
		return retObj;
	}

}










