package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.*;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import antlr.collections.List;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class BulkAllowancesVOGEN extends ServiceImpl {
	

	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapForInsertEmpBulkAllowances(Map objectArgs) 
	{
		logger.info("inside Bulk Allowances VOGEN");
		logger.info("object args is"+objectArgs);
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try{
			
					
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			logger.info("request object is "+request);
			long size=0;
			long empId=0;
			long hrEmpId=0;
			double eAmount=0;
			double nAmount=0;
			double difference=0;
			long type=0;
			long compoId=0;
			long dsgn=0;
			//String empIdStr="";
			String edit="";
			boolean empFlag=false;
			long empID=0;
			
			
			if(!StringUtility.getParameter("size", request).equals(""))
     		{     			
				size = Long.valueOf(StringUtility.getParameter("size", request).toString());
     		}	
			
			if(!StringUtility.getParameter("dsgn", request).equals(""))
     		{     			
				dsgn = Long.valueOf(StringUtility.getParameter("dsgn", request).toString());
     		}	
			logger.info("size in vogen is"+size);
			
			if(!StringUtility.getParameter("type", request).equals(""))
     		{     			
				type = Long.valueOf(StringUtility.getParameter("type", request).toString());
     		}
			
			/*if(!StringUtility.getParameter("empID", request).equals(""))
     		{     			
				empID = Long.valueOf(StringUtility.getParameter("empID", request).toString());
     		}
			logger.info("empID in "+empID);*/
			if(!StringUtility.getParameter("hiddenEmpId", request).equals(""))
     		{     			
				empID = Long.valueOf(StringUtility.getParameter("hiddenEmpId", request).toString());
     		}
			//long lodo=Long.valueOf(StringUtility.getParameter("hiddenEmpId", request).toString());
			//logger.info(" hiddenEmpId" + StringUtility.getParameter("hiddenEmpId", request).toString());
			logger.info("in vogen empID is"+empID);
				long eCode=0;
				if(!StringUtility.getParameter("eCode", request).equals(""))
	     		{     			
					eCode = Long.valueOf(StringUtility.getParameter("eCode", request).toString());
	     		}
				
			logger.info("empFlag in vogen is "+empFlag);
			logger.info("in vogen eCode is "+eCode);
			if(!StringUtility.getParameter("compoId", request).equals(""))
     		{     			
				compoId = Long.valueOf(StringUtility.getParameter("compoId", request).toString());
     		}
		/*	
			logger.info(StringUtility.getParameter("emp1", request).toString());
			logger.info(StringUtility.getParameter("emp2", request).toString());
			logger.info(StringUtility.getParameter("emp3", request).toString());
			*/

			
			for(int i=1;i<=size;i++)
			{
			
				if(!StringUtility.getParameter("emp"+i, request).equals(""))
	     		{     			
					
					empId = Long.valueOf(StringUtility.getParameter("emp"+i, request).toString());
					//empIdStr =(StringUtility.getParameter("emp"+i, request).toString());
	     		}
				if(!StringUtility.getParameter("hrEmpId"+empId, request).equals(""))
	     		{     			
					hrEmpId = Long.valueOf(StringUtility.getParameter("hrEmpId"+empId, request).toString());
				}
				logger.info("empId in vogen is"+empId);
				if(!StringUtility.getParameter("ExistAmount"+empId, request).equals(""))
	     		{     			
					eAmount = Double.valueOf(StringUtility.getParameter("ExistAmount"+empId, request).toString());
	     		}
				if(!StringUtility.getParameter("newAmount"+empId, request).equals(""))
	     		{     			
					nAmount = Double.valueOf(StringUtility.getParameter("newAmount"+empId, request).toString());
	     		}
				if(!StringUtility.getParameter("difference"+empId, request).equals(""))
	     		{     			
					difference = Double.valueOf(StringUtility.getParameter("difference"+empId, request).toString());
	     		}
				
				//logger.info("exsiting amount is "+eAmount);
				
				if(!StringUtility.getParameter("edit"+empId, request).equals(""))
	     		{     			
					edit = StringUtility.getParameter("edit"+empId, request).toString();
	     		}
				
				/*if(edit.equals("Y"))
					logger.info("Edit is Y in VOGEN");
				*/
				CustomBulkAllowances bulkAllowances =new CustomBulkAllowances();
				
				bulkAllowances.setEmpId(empId);
				bulkAllowances.setHrEisEmpId(hrEmpId);
				bulkAllowances.setEmployeeName("--");
				logger.info("in vogen edit is"+edit);
				bulkAllowances.setEdit(edit);
				bulkAllowances.setExistingAmount(eAmount);
				logger.info("New amount is " + nAmount);
				/*if(nAmount== 0 && eAmount>0 && size>1)
				{
					//logger.info("inside required if");
					nAmount=eAmount;
				}*/
				//bulkAllowances.setNewAmount(nAmount);
				//added by abhilash

				if(nAmount>=0)
				{
				bulkAllowances.setNewAmount(nAmount);
				}
				else
				{
				bulkAllowances.setNewAmount(eAmount);
				}
				//ended by abhilash
				bulkAllowances.setDifference(difference);
				
				objectArgs.put("bulkAllowances"+i, bulkAllowances);
				
			}
			CustomBulkAllowances bulkAllowances=new CustomBulkAllowances();
			bulkAllowances.setEmpId(empID);
			
			if(!StringUtility.getParameter("ExistAmount"+empID, request).equals(""))
     		{     			
				eAmount = Double.valueOf(StringUtility.getParameter("ExistAmount"+empID, request).toString());
				logger.info("original exixting amount is  "+eAmount);
     		}
			if(!StringUtility.getParameter("newAmount"+empID, request).equals(""))
     		{     			
				nAmount = Double.valueOf(StringUtility.getParameter("newAmount"+empID, request).toString());
				logger.info("original new amount is  "+nAmount);
     		}
			if(!StringUtility.getParameter("difference"+empID, request).equals(""))
     		{     			
				difference = Double.valueOf(StringUtility.getParameter("difference"+empID, request).toString());
				logger.info("original difference is "+difference);
     		}
			/*if(nAmount== 0 && eAmount>0)
			{
				//logger.info("inside required if");
				nAmount=eAmount;
			}
			*/
			bulkAllowances.setExistingAmount(eAmount);
			bulkAllowances.setNewAmount(nAmount);
			bulkAllowances.setDifference(difference);
			if(difference==0)
				bulkAllowances.setEdit("N");
			else
				bulkAllowances.setEdit("Y");
			
			logger.info("difference is "+difference);
			logger.info("edir is "+bulkAllowances.getEdit());
			objectArgs.put("size", size);
			objectArgs.put("type", type);
			objectArgs.put("dsgn", dsgn);
			objectArgs.put("empFlag", empFlag);
			objectArgs.put("eCode", eCode);
			logger.info("compoId in vogen is"+compoId);
			objectArgs.put("compoId", compoId);
			objectArgs.put("empID", empID);
			objectArgs.put("bulkAllowances", bulkAllowances);
			
			//added by abhilash
			
			long billGroupid=0;
			if(!StringUtility.getParameter("billGroupid", request).equals(""))
     		{     			
				billGroupid = Long.valueOf(StringUtility.getParameter("billGroupid", request).toString());
     		}
			objectArgs.put("billGroupid",billGroupid);
			//ended by abhilash
			
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return retObj;
	}
	
}