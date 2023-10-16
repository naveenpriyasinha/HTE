package com.tcs.sgv.pensionpay.service;

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
import com.tcs.sgv.common.valueobject.MstBank;
import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.common.valueobject.RltBankBranch;
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
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");			
		
		Map mp = objectArgs;
		RltBankBranch branchMstVO;
		String editMode = StringUtility.getParameter("edit",request);
		long branchId = 0; 
		String bsrCode = "00000";
		if(editMode.equalsIgnoreCase("N")) 
		{	
			branchMstVO = new RltBankBranch();
			objectArgs.put("edit","N");
		}
        else
        {       	 
		 String BranchId = StringUtility.getParameter("txtBranchID", request);
		 
		 if( BranchId!=null )
		 {
			branchId = Long.parseLong(BranchId);
		  }
		 branchMstVO = new RltBankBranch();
		 branchMstVO.setBranchId(branchId);
		 objectArgs.put("edit","Y");		 
        }
				
		 String bankID = StringUtility.getParameter("cmbBankName", request);		 
		 
		 String branchName = StringUtility.getParameter("txtBranchName", request);
		 //String branchCode = StringUtility.getParameter("txtBranchCode", request);
		 String branchAddress = StringUtility.getParameter("txtBranchAdd", request);
		 String micrCode = StringUtility.getParameter("txtMicrCode", request);
		 String ifscCode = StringUtility.getParameter("txtIfscCode", request);
		 String contact = StringUtility.getParameter("txtContactNo", request);
		 String locationCode = StringUtility.getParameter("cmbTreasury", request);
		 		 
		 MstBank bankMst = new MstBank();
		 
		 bankMst.setBankId(Long.parseLong(bankID));
		 
		 Date sysdate = new Date();
		
		branchMstVO.setBranchName(branchName);
		//branchMstVO.setBranchCode(Long.parseLong(branchCode));
		branchMstVO.setBranchAddress(branchAddress);
		branchMstVO.setMicrCode(Long.parseLong(micrCode));
		branchMstVO.setBsrCode(bsrCode);
		branchMstVO.setIfscCode(ifscCode);
		branchMstVO.setContact(contact);
		branchMstVO.setLocationCode(locationCode);
		
		
		logger.info(" ****************************BranchMasterVO " + branchMstVO.getBranchCode());
		mp.put("bankMst",bankMst);
		//mp.put("branchCode",branchCode);
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
			//e.printStackTrace();
			return retObj;
		}
		
	}
	
}
