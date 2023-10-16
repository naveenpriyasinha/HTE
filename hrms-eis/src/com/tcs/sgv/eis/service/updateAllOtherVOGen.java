package com.tcs.sgv.eis.service;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;




public class updateAllOtherVOGen extends ServiceImpl 
	implements VOGeneratorService{
	
	Log logger = LogFactory.getLog(getClass());
	//static int i;
	
	
	public ResultObject updateAllOther(Map objServiceArgs) 
	{
		logger.info("Inside ALLOtherDetailVOGen--------->");		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			HrEisOtherDtls otherDtlsVO = new HrEisOtherDtls();
			
			
			String allowId="";
			String deducId="";
			
			
			if(StringUtility.getParameter("allowId",request)!= null && !StringUtility.getParameter("allowId",request).equals(""))
			{
				allowId=(StringUtility.getParameter("allowId",request));
			}
			
			if(StringUtility.getParameter("deducId",request)!= null && !StringUtility.getParameter("deducId",request).equals(""))
			{
				deducId=(StringUtility.getParameter("deducId",request));
			}
			logger.info(deducId+"deducId ******************Inside ALLOtherDetailVOGen***************** allowId"+allowId);
			objServiceArgs.put("allowId",allowId);
			objServiceArgs.put("deducId",deducId);
			
			retObj.setResultValue(objServiceArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			Calendar endCalTime = Calendar.getInstance();
			logger.info("The End  for the updateAllOther Details' VOGEN is:-");
		}catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}
	
	public ResultObject generateMap(Map arg0) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	//public ResultObject 
}
