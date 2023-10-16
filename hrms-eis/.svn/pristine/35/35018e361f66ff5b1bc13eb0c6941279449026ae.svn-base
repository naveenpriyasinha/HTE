package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;

public class GradeMasterVOGEN extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());
	public ResultObject getGradeData(Map objectArgs) 
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		
		try{
		logger.info("GradeMasterVOGEN getGradeData Called");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
				
		OrgGradeMst gradeMstVO = new OrgGradeMst();
		
		String grade_name="";
		String grade_desc="";	
		long gradeid=0;
		
		grade_name = StringUtility.getParameter("grade_name", request);
		grade_desc = StringUtility.getParameter("grade_desc", request);
		String edit= StringUtility.getParameter("edit", request);
				
		if(!edit.equals(null) && !edit.equalsIgnoreCase("Y"))
		{
			logger.info(" insert mode of grade master from vogen ");
			gradeMstVO.setGradeName(grade_name);
			gradeMstVO.setGradeDesc(grade_desc);
			objectArgs.put("edit","N");
		}
		else
		{
			logger.info(" edit mode of grade master from vogen ");
			String grade=StringUtility.getParameter("gradeid", request);
			if(!grade.equals(null))
			{	
				gradeid=Long.parseLong(grade);
			}
			objectArgs.put("gradeid", gradeid);
			objectArgs.put("grade_name", grade_name);
			objectArgs.put("grade_desc", grade_desc);
			objectArgs.put("edit","Y");
			
		}
		logger.info(" ****************************desigMstVO " + gradeMstVO);
		objectArgs.put("GradeMst",gradeMstVO);
		
		
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
