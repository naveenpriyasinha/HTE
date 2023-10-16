package com.tcs.sgv.loan.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.loan.valueobject.HrEssLoanAdvMst;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;


public class LoanAdvMstVOGenImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapForInsertLoanAdvMaster(Map objectArgs) 
	{
		try{
		logger.info("LoanAdvMstVOGenImpl generateMapForInsertLoanAdvMaster Called");
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
				
		Map mp = objectArgs;		
		String editMode = StringUtility.getParameter("edit",request);

		HrLoanAdvMst loanAdvMst = null;
		HrEssLoanAdvMst loanEssMst=null;		
		if(editMode.equalsIgnoreCase("N")) 
		{			
			loanAdvMst = new HrLoanAdvMst();
			loanEssMst = new HrEssLoanAdvMst();
			loanAdvMst.setCreatedDate(new Date());
			loanEssMst.setCreatedDate(new Date());
			
			 objectArgs.put("edit","N");
		}
        else
        { 
        	long loanId = 0; 
        	
        	if(!StringUtility.getParameter("txtLoanId", request).equals(""))
     		{
     			
        		loanId = Long.parseLong(StringUtility.getParameter("txtLoanId", request));
     		}	        	
        	logger.info("txtLoanId is " + StringUtility.getParameter("txtLoanId", request));
           // System.out.println("txtLoanId is " + StringUtility.getParameter("txtLoanId", request));    		        			 		 		
            loanAdvMst = new HrLoanAdvMst();            
            loanAdvMst.setLoanAdvId(loanId);
            loanEssMst = new HrEssLoanAdvMst();            
            loanEssMst.setLoanAdvId(loanId);
            objectArgs.put("edit","Y");
        }
		String loanName="";
		long noOfAmount=0;
		long noOfInstall=0;
		long minNoOfInstall=0;
		long displayGroup=0;
     		
		if(!StringUtility.getParameter("loanName", request).equals(""))
		{
			
			loanName = StringUtility.getParameter("loanName", request);
		}
		
		if(!StringUtility.getParameter("txtMaxNoAmount", request).equals(""))
		{
			
			noOfAmount = Long.parseLong(StringUtility.getParameter("txtMaxNoAmount", request).trim());
		}
		if(!StringUtility.getParameter("txtMaxNoInstall", request).equals(""))
		{
			
			noOfInstall = Long.parseLong(StringUtility.getParameter("txtMaxNoInstall", request).trim());
		}    
		if(!StringUtility.getParameter("txtMinNoInstall", request).equals(""))
		{
			minNoOfInstall = Long.parseLong(StringUtility.getParameter("txtMinNoInstall", request).trim());
			
		}  
		
		if(!StringUtility.getParameter("group1", request).equals(""))
		{
			displayGroup = Long.parseLong(StringUtility.getParameter("group1", request).trim());
		//	displayGroup = request.getParameter("group1");
			
		}  
		//System.out.println("displayGroup is >"+displayGroup);
		
		 objectArgs.put("displayGroup", displayGroup);
		 loanAdvMst.setLoanAdvName(loanName);
		 loanAdvMst.setMaxNoInstallAmt(noOfAmount);
		 loanAdvMst.setMaxNoInstInterest(noOfInstall);	
		 loanAdvMst.setMinNoInstInterest(minNoOfInstall);
		 loanEssMst.setLoanAdvName(loanName);
		 loanEssMst.setMaxNoInstallAmt(noOfAmount);
		 loanEssMst.setMaxNoInstInterest(noOfInstall);	
		 loanEssMst.setMinNoInstInterest(minNoOfInstall);
	    							
		logger.info(" ****************************empAllwMpg " + loanAdvMst);
		logger.info(" ****************************empAllwMpg " + loanEssMst);
		mp.put("loanAdvMst",loanAdvMst);
		mp.put("EssLoanAdvMst",loanEssMst);
		retObj.setResultValue(mp);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		return retObj;
		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.info("Null Pointer Exception Ocuures...");
			 e.printStackTrace();
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 retObj.setResultValue(objectArgs);
			 retObj.setViewName("errorInsert");
			e.printStackTrace();
			return retObj;
			
		}
		
	}		
}
