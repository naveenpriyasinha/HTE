

package com.tcs.sgv.deduction.service;

import java.util.HashMap;
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
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;

public class DeducTypeMasterVOGen extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject generateMapForInsertDeducTypeMaster(Map objectArgs){
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
		logger.info("inside generateMapForInsertDeducTypeMaster-------");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpSession session=request.getSession();
		
		 
		Map mp = objectArgs;
		HrPayDeducTypeMst deducTypeMstVO = new HrPayDeducTypeMst();
		String editMode = StringUtility.getParameter("edit",request);
		logger.info("The Value of Edit is :-"+editMode);
		long deducCode = 0; 
		
		if(editMode.equalsIgnoreCase("N")) 
		{		
			
			objectArgs.put("edit","N");
		}
		else
		{
			String DeducCode = (StringUtility.getParameter("deducCode", request)!=null&&!(StringUtility.getParameter("deducCode", request).equals(""))?(StringUtility.getParameter("deducCode", request)):0).toString();
			 if( DeducCode!=null ){
				 deducCode = Long.parseLong(DeducCode);
			  }
			 deducTypeMstVO.setDeducCode(deducCode);
			 objectArgs.put("edit","Y");
			 logger.info("Edit parameter in VO ");
		}
		
		String deducName = (StringUtility.getParameter("deductionName",request)!=null&&!(StringUtility.getParameter("deductionName",request).equals(""))?(StringUtility.getParameter("deductionName",request)):" ").toString();
		String deducDispalyName = (StringUtility.getParameter("deductionDisplayName",request)!=null&&!(StringUtility.getParameter("deductionDisplayName",request).equals(""))?(StringUtility.getParameter("deductionDisplayName",request)):" ").toString();
		String deducDesc = (StringUtility.getParameter("deductionDesc",request)!=null&&!(StringUtility.getParameter("deductionDesc",request).equals(""))?(StringUtility.getParameter("deductionDesc",request)):" ").toString();
		Long deducType = Long.parseLong((StringUtility.getParameter("deductionType",request)!=null&&!(StringUtility.getParameter("deductionType",request).equals(""))?(StringUtility.getParameter("deductionType",request)):" "));
		deducTypeMstVO.setDeducName(deducName);
		deducTypeMstVO.setDeducDisplayName(deducDispalyName);
		deducTypeMstVO.setDeducType(deducType);
		logger.info("The Deduction name is :-"+deducName);
		deducTypeMstVO.setDeducDesc(deducDesc);
		
		
		logger.info(" ****************************deducTypeMasterVO " + deducTypeMstVO);
		mp.put("deducTypeMst",deducTypeMstVO);
		retObj.setResultValue(mp);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		logger.info("U are out of VOGEN");		
		}
		catch(Exception e){
			logger.info("U r in Exception + VOGEN");
			e.printStackTrace();
			Map result = new HashMap();
			result.put("MESSAGECODE",3001);
			retObj.setResultValue(result);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;			
		}	
		return retObj;
	}
}
