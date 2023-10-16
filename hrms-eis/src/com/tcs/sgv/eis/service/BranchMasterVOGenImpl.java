package com.tcs.sgv.eis.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.common.valueobject.RltBankBranchPay;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchDtls;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.CmnBranchlocMpg;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class BranchMasterVOGenImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapForInsertBranchMaster(Map objectArgs) 
	{
		try{
		logger.info("BranchMasterVOGenImpl generateMapForInsertBranchMaster Called");
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");			
		
		Map mp = objectArgs;
		RltBankBranchPay branchMstVO;
		String editMode = StringUtility.getParameter("edit",request);
		long branchId = 0; 
		if(editMode.equalsIgnoreCase("N")) 
		{	
			branchMstVO = new RltBankBranchPay();
			objectArgs.put("edit","N");
		}
        else
        {       	 
		 String BranchId = StringUtility.getParameter("txtBranchID", request);
		 
		 if( BranchId!=null )
		 {
			branchId = Long.parseLong(BranchId);
		  }
		 branchMstVO = new RltBankBranchPay();
		 branchMstVO.setBranchId(branchId);
		 objectArgs.put("edit","Y");		 
        }
				
		 String bankID = StringUtility.getParameter("cmbBankName", request);
		 
		 logger.info("bankid************"+bankID);
		 
		 String branchName = StringUtility.getParameter("txtBranchName", request);
		 String branchCode = StringUtility.getParameter("txtBranchCode", request);
		 String branchAddress = StringUtility.getParameter("txtBranchAdd", request);
		 String micrCode = StringUtility.getParameter("txtMicrCode", request);
		 
		 logger.info("The Bank Id is:-"+branchName);
		 
		 logger.info("branch code in vogen" + branchCode);
		 MstBankPay bankMst = new MstBankPay();
		 
		 bankMst.setBankId(Long.parseLong(bankID));
		 
		 Date sysdate = new Date();
		
		branchMstVO.setBranchName(branchName);
		branchMstVO.setBranchCode(branchId);
		
		branchMstVO.setBranchAddress(branchAddress);
		branchMstVO.setMicrCode(micrCode);
		branchMstVO.setBsrCode(micrCode);
		
		
		logger.info(" ****************************BranchMasterVO " + branchMstVO.getBranchCode());
		mp.put("bankMst",bankMst);
		mp.put("branchCode",branchCode);
		mp.put("branchMst",branchMstVO);
		retObj.setResultValue(mp);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		return retObj;
		
		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			Map errorMap = new HashMap();
			errorMap.put("msg", "There is some problem. Please Try Again Later.");
			retObj.setResultCode(-1);
			retObj.setResultValue(errorMap);
			retObj.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return retObj;
		}
		
	}
	
	public ResultObject insertBranchDtls(Map objectArgs)
	{
		Map dtls=new HashMap();
		Calendar calTime = Calendar.getInstance();
		logger.info("The Inintial time for the Other Details' Service is:-"+calTime.getTimeInMillis());
		logger.info("IN insertBranchData of OTHER DETAIL SERVICE ");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		try
		{
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			
			String cmbBankName = StringUtility.getParameter("cmbBankName", request);
			String branchname = StringUtility.getParameter("txtddoName", request);
			String branchcode = StringUtility.getParameter("txt1", request);
			String branchdesc = StringUtility.getParameter("txt2", request);
			String address = StringUtility.getParameter("txt3", request);
			//long active = Long.parseLong(StringUtility.getParameter("txt4", request));
			String active = StringUtility.getParameter("txt4", request);
			long branchTypeLookup =Long.parseLong(StringUtility.getParameter("txt5", request));
			
		//	CmnBranchlocMpg mpg = new CmnBranchlocMpg();
								
			dtls.put("cmbBankName", cmbBankName);
			dtls.put("branchname", branchname);
			dtls.put("branchcode", branchcode);
			dtls.put("branchdesc", branchdesc);
			dtls.put("address",address);
			dtls.put("active", active);
			dtls.put("branchTypeLookup", branchTypeLookup);
			
			
			
			
			logger.info(" insertBranchData inside if vogen-------branchname---------------"+branchname);
			logger.info(" insertBranchData inside if vogen-------branchcode---------------"+branchcode);
			logger.info(" insertBranchData inside if vogen-------branchdesc---------------"+branchdesc);
			logger.info(" insertBranchData inside if vogen-------address---------------"+address);
			logger.info(" insertBranchData inside if vogen-------active---------------"+active);
			logger.info(" insertBranchData inside if vogen-------branchTypeLookup---------------"+branchTypeLookup);
			
			
			objectArgs.put("dtls",dtls);
			
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			return resultObject;
		}
			

		catch(Exception e)
		{			
			logger.error("Error is: "+ e.getMessage());
			
			Map result = new HashMap();
			result.put("MESSAGECODE",1007);
			resultObject.setResultValue(result);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			
			
		}
		
		return resultObject;
	}
	
	
	
}
