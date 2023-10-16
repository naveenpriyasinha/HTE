package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;

public class BankDetailsVOGenImpl extends ServiceImpl{
	
	private static final 	Log logger = LogFactory.getLog(BankDetailsVOGenImpl.class);
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject generateMapForInsertBankDetail(Map objectArgs) 
	{
		try{
		logger.info("BankDetailsVOGenImpl generateMapForInsertBankDetail Called");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
		HrEisBankDtls bankDtlsVO;
				
		String editMode = StringUtility.getParameter("edit",request);
		  long bankDtlsId = 0; 
		if(editMode.equalsIgnoreCase("N")) 
		{		
			
			bankDtlsVO = new HrEisBankDtls();			
			objectArgs.put("edit","N");
		}
        else
        {         
		 String BankDtlsId = StringUtility.getParameter("bankDtlsId", request);
		 if( BankDtlsId!=null && !BankDtlsId.equals("")){
			 bankDtlsId = Long.parseLong(BankDtlsId);
		  }
		 bankDtlsVO = new HrEisBankDtls();
		 bankDtlsVO.setBankDtlId(bankDtlsId);
		 objectArgs.put("edit","Y");
        }
		
		long empId = 0;
		long bankId = 0;
		long branchId = 0;
		String accNo = "" ;
		long accType = 0;
		Date bankAcctStartDt = null;
		String FromBasicDtlsNew = "" ;
		
		if(StringUtility.getParameter("cmbEmpName", request)!=null && !StringUtility.getParameter("cmbEmpName", request).equals(""))
		{
			empId = Long.parseLong(StringUtility.getParameter("cmbEmpName", request));
		}
		if(StringUtility.getParameter("cmbBankName", request)!=null && !StringUtility.getParameter("cmbBankName", request).equals(""))
		{
			bankId = Long.parseLong(StringUtility.getParameter("cmbBankName", request));
		}
		
		if(StringUtility.getParameter("txtStartDate", request)!=null && !StringUtility.getParameter("txtStartDate", request).equals(""))
		{
			bankAcctStartDt = StringUtility.convertStringToDate(StringUtility.getParameter("txtStartDate", request));
		}
		
		if(StringUtility.getParameter("cmbBranchName", request)!=null && !StringUtility.getParameter("cmbBranchName", request).equals(""))
		{
			branchId = Long.parseLong(StringUtility.getParameter("cmbBranchName", request));
		}
						
		if(StringUtility.getParameter("txtAccNo", request)!=null && !StringUtility.getParameter("txtAccNo", request).equals(""))
		{
			accNo = (StringUtility.getParameter("txtAccNo", request));
		}
		
		if(StringUtility.getParameter("cmbAccType", request)!=null && !StringUtility.getParameter("cmbAccType", request).equals(""))
		{
			accType = Long.parseLong(StringUtility.getParameter("cmbAccType", request));
		}
		
		//added by ravysh
		if(StringUtility.getParameter("FromBasicDtlsNew", request)!=null && !StringUtility.getParameter("FromBasicDtlsNew", request).equals(""))
		{
			FromBasicDtlsNew = StringUtility.getParameter("FromBasicDtlsNew", request);
		}
		
		HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
		hrEisEmpMst.setEmpId(empId);
		
		
		HrEisBankMst hrEisBankMst = new HrEisBankMst();
		hrEisBankMst.setBankId(bankId);
		
		HrEisBranchMst hrEisBranchMst = new HrEisBranchMst();
		hrEisBranchMst.setBranchId(branchId);
		
		bankDtlsVO.setBankAcctNo(accNo);
		bankDtlsVO.setBankAcctStartDt(bankAcctStartDt);
		bankDtlsVO.setBankAcctType(accType);
		/*bankDtlsVO.setHrEisBankMst(hrEisBankMst);
		bankDtlsVO.setHrEisBranchMst(hrEisBranchMst);*/
		bankDtlsVO.setHrEisEmpMst(hrEisEmpMst);
		logger.info(" ****************************BankMasterVO " + bankDtlsVO);
		
		objectArgs.put("FromBasicDtlsNew",FromBasicDtlsNew);	
		objectArgs.put("bankDtls",bankDtlsVO);		
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);		
		}
		catch(PropertyValueException pe)
		{
			logger.info("Exception in generateMapForInsertBankDetail-----"+pe);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(pe);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			

		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.info("Exception in generateMapForInsertBankDetail-----"+e);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
						
			
		}		
		return retObj;
	}		
}


















   /*  addded by saurabh for practise*/


/*
public ResultObject generateMapForInsertBankDetail(Map objectArgs) 
{
	try{
	logger.info("BankDetailsVOGenImpl generateMapForInsertBankDetail Called");		
	
	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
	HrEisBankDtls bankDtlsVO;
			
	String editMode = StringUtility.getParameter("edit",request);
	  long bankDtlsId = 0; 
	if(editMode.equalsIgnoreCase("N")) 
	{		
		
		bankDtlsVO = new HrEisBankDtls();			
		objectArgs.put("edit","N");
	}
    else
    {         
	 String BankDtlsId = StringUtility.getParameter("bankDtlsId", request);
	 if( BankDtlsId!=null && !BankDtlsId.equals("")){
		 bankDtlsId = Long.parseLong(BankDtlsId);
	  }
	 bankDtlsVO = new HrEisBankDtls();
	 bankDtlsVO.setBankDtlId(bankDtlsId);
	 objectArgs.put("edit","Y");
    }
	
	long empId = 0;
	long bankId = 0;
	long branchId = 0;
	String accNo = "" ;
	long accType = 0;
	Date bankAcctStartDt = null;
	String FromBasicDtlsNew = "" ;
	
	if(StringUtility.getParameter("cmbEmpName", request)!=null && !StringUtility.getParameter("cmbEmpName", request).equals(""))
	{
		empId = Long.parseLong(StringUtility.getParameter("cmbEmpName", request));
	}
	if(StringUtility.getParameter("cmbBankName", request)!=null && !StringUtility.getParameter("cmbBankName", request).equals(""))
	{
		bankId = Long.parseLong(StringUtility.getParameter("cmbBankName", request));
	}
	
	if(StringUtility.getParameter("txtStartDate", request)!=null && !StringUtility.getParameter("txtStartDate", request).equals(""))
	{
		bankAcctStartDt = StringUtility.convertStringToDate(StringUtility.getParameter("txtStartDate", request));
	}
	
	if(StringUtility.getParameter("cmbBranchName", request)!=null && !StringUtility.getParameter("cmbBranchName", request).equals(""))
	{
		branchId = Long.parseLong(StringUtility.getParameter("cmbBranchName", request));
	}
					
	if(StringUtility.getParameter("txtAccNo", request)!=null && !StringUtility.getParameter("txtAccNo", request).equals(""))
	{
		accNo = (StringUtility.getParameter("txtAccNo", request));
	}
	
	if(StringUtility.getParameter("cmbAccType", request)!=null && !StringUtility.getParameter("cmbAccType", request).equals(""))
	{
		accType = Long.parseLong(StringUtility.getParameter("cmbAccType", request));
	}
	
	//added by ravysh
	if(StringUtility.getParameter("FromBasicDtlsNew", request)!=null && !StringUtility.getParameter("FromBasicDtlsNew", request).equals(""))
	{
		FromBasicDtlsNew = StringUtility.getParameter("FromBasicDtlsNew", request);
	}
	
	HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
	hrEisEmpMst.setEmpId(empId);
	
	
	HrEisBankMst hrEisBankMst = new HrEisBankMst();
	hrEisBankMst.setBankId(bankId);
	
	HrEisBranchMst hrEisBranchMst = new HrEisBranchMst();
	hrEisBranchMst.setBranchId(branchId);
	
	bankDtlsVO.setBankAcctNo(accNo);
	bankDtlsVO.setBankAcctStartDt(bankAcctStartDt);
	bankDtlsVO.setBankAcctType(accType);
	/*bankDtlsVO.setHrEisBankMst(hrEisBankMst);
	bankDtlsVO.setHrEisBranchMst(hrEisBranchMst);*/
/*	 bankDtlsVO.setHrEisEmpMst(hrEisEmpMst);
	logger.info(" ****************************BankMasterVO " + bankDtlsVO);
	
	objectArgs.put("FromBasicDtlsNew",FromBasicDtlsNew);	
	objectArgs.put("bankDtls",bankDtlsVO);		
	retObj.setResultValue(objectArgs);
	retObj.setResultCode(ErrorConstants.SUCCESS);		
	}
	catch(PropertyValueException pe)
	{
		logger.info("Exception in generateMapForInsertBankDetail-----"+pe);
		logger.info("setViewName-->errorPage");
		Map result = new HashMap();
		result.put("MESSAGECODE",300004);
		retObj.setResultValue(result);
		retObj.setThrowable(pe);
		retObj.setResultCode(-1);
		retObj.setViewName("errorPage");
		

	}
	catch(Exception e){
		ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
		logger.info("Exception in generateMapForInsertBankDetail-----"+e);
		logger.info("setViewName-->errorPage");
		Map result = new HashMap();
		result.put("MESSAGECODE",300004);
		retObj.setResultValue(result);
		retObj.setThrowable(e);
		retObj.setResultCode(-1);
		retObj.setViewName("demo");
					
		
	}		
	return retObj;
}		
}*/

