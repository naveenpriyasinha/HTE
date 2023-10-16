package com.tcs.sgv.deduction.service;
//Comment By Maruthi For import Organisation.

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducExpMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;

public class DeducExpMasterVOGen extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapForAddUpdateDeducExpMaster(Map objectArgs) {
		try{
		logger.info("ExpressionMasterVOGenImpl generateMapForInsertBranchMaster Called");
		//System.out.println("ExpressionMasterVOGenImpl generateMapForInsertBranchMaster Called");
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
		
		HrDeducExpMst deducExpMstVO = new HrDeducExpMst();
		String editMode = StringUtility.getParameter("edit",request);
		// Code for getting the langId and UserId from login page by Urvin shah
		
		HttpSession session=request.getSession();
		//LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
		//long langId=loginDetails.getLangId();		      
		Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
		
		
		// End Urvin shah
		HrDeducExpMst deducExpMst = null;
		long deducRuleId = 0; 
		if(editMode.equalsIgnoreCase("N")) {			
			deducExpMst = new HrDeducExpMst();		
			Date sysdate = new Date();
			deducExpMst.setCreatedDate(sysdate);	 
		}
        else {       	 
		 String deducRuleID = StringUtility.getParameter("txtRuleID", request);
		 //System.out.println("RuleID in VOGen " + deducRuleID);
		 
		 if( deducRuleID!=null ){
			 deducRuleId = Long.parseLong(deducRuleID);
		  }
		 deducExpMst = new HrDeducExpMst();
		 DeducExpMasterDAOImpl deducExpDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
		 deducExpMst = deducExpDAO.read(deducRuleId);
		 deducExpMst.setDeducRuleId(deducRuleId);
        }	
		
		 String ruleDesc = StringUtility.getParameter("txtRuleDesc", request);		 
		 String expr = StringUtility.getParameter("txtExp", request);		
		 String value = StringUtility.getParameter("txtVal", request);
		 String str_rule_stdate = StringUtility.getParameter("txtRuleSDate", request);
		 String str_rule_enddate = StringUtility.getParameter("txtRuleEDate", request);		 
		 Date rule_stdate = StringUtility.convertStringToDate(str_rule_stdate);
		 Date rule_enddate = StringUtility.convertStringToDate(str_rule_enddate);		 
		 //HrPayDeducTypeMst hrPayDeducObj = new HrPayDeducTypeMst();
		 long deducCode=Long.parseLong(StringUtility.getParameter("deductionType", request));
		 
		 long flag=-1;
		 String strflag = StringUtility.getParameter("flag", request);
		 if(!strflag.equals("")) {		  
			  flag = Long.parseLong(strflag);
		 }
		 long deducRuleValue=0;
		 if(!value.equals("")) {
		 deducRuleValue=Long.parseLong(value);
		 }
		 String deducRuleRelated="Deduction";		 
		 deducExpMst.setDeducExpIsActive(flag);
		 deducExpMst.setDeducRuleDesc(ruleDesc);
		 deducExpMst.setDeducRuleExp(expr);
		 
		 deducExpMst.setDeducRuleValue(deducRuleValue);
		 //deducExpMst.setHrPayDeducTypeMst(hrPayDeducObj);		 
		 deducExpMst.setDeducExpEffStartDt(rule_stdate);
		 deducExpMst.setDeducExpEffEndDt(rule_enddate);
		 		 
		 Date sysdate = new Date();															
		 deducExpMst.setUpdatedDate(sysdate);
		 						
		logger.info(" ****************************ExprMasterVO " + deducExpMst);
		objectArgs.put("editMode",editMode);
		objectArgs.put("deducExpMst",deducExpMst);
		objectArgs.put("deducCode",deducCode);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		return retObj;
		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			e.printStackTrace();
			return retObj;			
		}		
	}
}
