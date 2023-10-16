package com.tcs.sgv.eis.service;

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
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;

public class DesigMasterVOGEN  extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());
	public ResultObject getDesigData(Map objectArgs) 
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		
		
		
		try{
		logger.info("DesigMasterVOGEN getDesigData Called");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpSession session=request.getSession();
		
		
		OrgDesignationMst desigMstVO = new OrgDesignationMst();
		
		String desg_name="";
		String desg_short_name="";
		String desg_desc="";	
		long desigid=0;
		
		
		
		desg_name = StringUtility.getParameter("desg_name", request);
		desg_short_name = StringUtility.getParameter("desg_short_name", request);
		String edit= StringUtility.getParameter("edit", request);
		
		if(!edit.equals(null) && !edit.equalsIgnoreCase("Y"))
		{
			logger.info(" insert mode of desig master from vogen ");
			desigMstVO.setDsgnName(desg_name);
			desigMstVO.setDsgnShrtName(desg_short_name);
			objectArgs.put("edit","N");
		}
		else
		{
			logger.info(" edit mode of grade master from vogen ");
			String desig=StringUtility.getParameter("desigid", request);
			if(!desig.equals(null))
			{	
				desigid=Long.parseLong(desig);
			}
			objectArgs.put("desigid", desigid);
			objectArgs.put("desg_name", desg_name);
			objectArgs.put("desg_desc", desg_desc);
			objectArgs.put("desg_short_name", desg_short_name);
			objectArgs.put("edit","Y");
		}
		logger.info(" ****************************desigMstVO " + desigMstVO);
		objectArgs.put("DesigMst",desigMstVO);
		
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		
		}
		catch(Exception e){
			retObj.setResultCode(ErrorConstants.ERROR);
			logger.error("Error is: "+ e.getMessage());
			return retObj;
			
		}
		return retObj;
		
	}

	
}
