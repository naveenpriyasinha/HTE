package com.tcs.sgv.eis.service;

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
import com.tcs.sgv.eis.valueobject.HrEisBankMst;

public class SchemeVOGenImpl extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject getSchemeDtls(Map objectArgs) 
	{
		try
		{
			logger.info("SchemeVOGenImpl getSchemeDtls Called");		
			//System.out.println("SchemeVOGenImpl getSchemeDtls Called");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
			
			
			 String schemeCode="";
			 String schemeName="";
			if(StringUtility.getParameter("schemeCode", request)!=null)
			  schemeCode = StringUtility.getParameter("schemeCode", request);
			
			if(StringUtility.getParameter("schemeName", request)!=null)
				schemeName = StringUtility.getParameter("schemeName", request);
			
			
			String cmbDept =StringUtility.getParameter("cmbDept", request);
			String cmbDemand =StringUtility.getParameter("cmbDemand", request);
			String cmbMjrHead =StringUtility.getParameter("cmbMjrHead", request);
			String cmbSubMjrHead =StringUtility.getParameter("cmbSubMjrHead", request);
			String cmbMnrHead =StringUtility.getParameter("cmbMnrHead", request);
			String cmbSubHead =StringUtility.getParameter("cmbSubHead", request);
			String cmbDtlHead =StringUtility.getParameter("cmbDtlHead", request);
			
			/*System.out.println("SchemeVOGenImpl schemeCode" + schemeCode);
			System.out.println("SchemeVOGenImpl schemeName" + schemeName);
			System.out.println("SchemeVOGenImpl cmbDept" + cmbDept);
			System.out.println("SchemeVOGenImpl cmbDemand" + cmbDemand);
			System.out.println("SchemeVOGenImpl cmbMjrHead" + cmbMjrHead);
			System.out.println("SchemeVOGenImpl cmbSubMjrHead" + cmbSubMjrHead);
			System.out.println("SchemeVOGenImpl cmbMnrHead" + cmbMnrHead);
			System.out.println("SchemeVOGenImpl cmbSubHead" + cmbSubHead);
			System.out.println("SchemeVOGenImpl cmbDtlHead" + cmbDtlHead);*/
			
			
			
			
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			return retObj;
			}
		catch(PropertyValueException pe)
		{
			logger.info("Exception in generateMapForInsertBankMaster-----"+pe);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(pe);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			logger.error("Error is: "+ pe.getMessage());

		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.info("Exception in generateMapForInsertBankMaster-----"+e);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());			
			
		}		
		return retObj;
	}	
}		
	
	
	
	

