/**
 * This class is common for insert and update record.
 * It collects the data inserted in the AllowTypeMaster file and set it in to the HRPayAllowTypMst.
 * and return  Resultobject of the AllowTypeMasterServiceImpl class. 
 */

package com.tcs.sgv.allowance.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

public class AllowTypeMasterVOGen extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapForInsertAllowTypeMaster(Map objectArgs){
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
		logger.info("AllowTypeMasterVOGen generateMapForInsertAllowTypeMaster Called");						
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		
		String allowName = StringUtility.getParameter("allowanceName", request);
		String allowDesc = StringUtility.getParameter("allowanceDesc", request);

		String allowDisplayName = "";
		if(StringUtility.getParameter("allowanceDisplayName", request)!=null)
			allowDisplayName=StringUtility.getParameter("allowanceDisplayName", request);

		String editMode = StringUtility.getParameter("edit",request);
		
		HrPayAllowTypeMst allowTypeMstVO = new HrPayAllowTypeMst();		
		logger.info("The Value of Edit is :-"+editMode);
		long allowCode = 0; 
		Date sysDate = new Date();
		if(editMode.equalsIgnoreCase("Y")) {
			logger.info("AllowTypeMasterVOGen in edit mode Called");
			String allowanceCode = StringUtility.getParameter("allowCode", request);
			 if( allowanceCode!=null ){
				allowCode = Long.parseLong(allowanceCode);
			  }
			 allowTypeMstVO.setAllowCode(allowCode);
			 allowTypeMstVO.setUpdatedDate(sysDate);	 
		}
		else {
			allowTypeMstVO.setCreatedDate(sysDate);
		}		
		
		allowTypeMstVO.setAllowName(allowName);
		logger.info("The Allowance name is :-"+allowName);
		allowTypeMstVO.setAllowDesc(allowDesc);	
		long allowTypeId = 1;
		allowTypeMstVO.setAllowTypeCode(allowTypeId);
		
		allowTypeMstVO.setAllowDisplayName(allowDisplayName);
		
		objectArgs.put("allowTypeMst",allowTypeMstVO);
		objectArgs.put("editMode",editMode);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		logger.info("U are out of VOGEN");		
		}
		catch(Exception e){		
			objectArgs.put("MESSAGECODE",3001);
			retObj.setResultValue(objectArgs);			
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");		
			return retObj;			
		}	
		return retObj;
	}
}
