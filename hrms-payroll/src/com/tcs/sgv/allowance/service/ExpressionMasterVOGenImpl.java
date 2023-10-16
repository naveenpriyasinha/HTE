package com.tcs.sgv.allowance.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.allowance.valueobject.HrPayExpressionMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class ExpressionMasterVOGenImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapForInsertExprMaster(Map objectArgs) 
	{
		try{
		logger.info("ExpressionMasterVOGenImpl generateMapForInsertBranchMaster Called");		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

		HrPayExpressionMst exprMstVO = new HrPayExpressionMst();
		String editMode = StringUtility.getParameter("edit",request);
		long allowCode;				
		long ruleId = 0; 
		if(editMode.equalsIgnoreCase("N")) 
		{							 					
			exprMstVO.setCreatedDate(new Date());
			objectArgs.put("edit", "N");
			 
		}
        else
        {       	 
		 String RuleId = StringUtility.getParameter("txtRuleID", request);
		 logger.info("RuleID in VOGen " + RuleId);
		 
		 if( RuleId!=null ){
			ruleId = Long.parseLong(RuleId);
		  }		 
		 exprMstVO.setRuleId(ruleId);
		 objectArgs.put("edit", "Y");
        }
		
		
		 String ruleDesc = StringUtility.getParameter("txtRuleDesc", request);
		 
		 String amount = StringUtility.getParameter("txtAmount", request);
		 String component = StringUtility.getParameter("components", request);
		 String expr = StringUtility.getParameter("txtExp", request);
		
		 String value = StringUtility.getParameter("txtVal", request);
		 String str_rule_stdate = StringUtility.getParameter("txtRuleSDate", request);
		 String str_rule_enddate = StringUtility.getParameter("txtRuleEDate", request);
		 
		 Date rule_stdate = StringUtility.convertStringToDate(str_rule_stdate);
		 Date rule_enddate = StringUtility.convertStringToDate(str_rule_enddate);
		 
		 HrPayAllowTypeMst hrPayAllowObj = new HrPayAllowTypeMst();
		 allowCode = Long.parseLong(StringUtility.getParameter("allowance", request));
		/* if(!StringUtility.getParameter("allowance", request).equals(""))
		 {			  
		  hrPayAllowObj.setAllowCode(Long.parseLong(StringUtility.getParameter("allowance", request)));
		 }*/
		 long flag=-1;
		 
		 if(!StringUtility.getParameter("flag", request).equals(""))
		 {			 
			 String strflag = StringUtility.getParameter("flag", request); 
			  flag = Long.parseLong(strflag);
		 }
		 		 
		 exprMstVO.setRuleDescription(ruleDesc);
		 exprMstVO.setRuleExpression(expr);
		 if(!value.equals("")){
			 exprMstVO.setRuleValue(Long.parseLong(value));
		 }
		 else
			 exprMstVO.setRuleValue(null);
		 //exprMstVO.setRuleValue(value);
		 exprMstVO.setHrPayAllowTypeMst(hrPayAllowObj);
         exprMstVO.setIsactive(flag);
		 exprMstVO.setRuleEffStartDate(rule_stdate);
		 exprMstVO.setRuleEffEndDate(rule_enddate);
		 		 		 		 						
		logger.info(" ****************************ExprMasterVO " + exprMstVO);
		objectArgs.put("exprMst",exprMstVO);
		objectArgs.put("allowCode",allowCode);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		return retObj;
		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 retObj.setResultValue(objectArgs);
			 retObj.setViewName("errorInsert");
				e.printStackTrace();
			return retObj;
			
		}
		
	}

}
