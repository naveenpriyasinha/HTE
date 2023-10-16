package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class UpdateBatchITGpfDetailsVOGen extends ServiceImpl
{	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject UpdateBatchITGpfDetails(Map<String, Object> objectArgs) 
	{
		try
		{
			logger.info("Update Batch IT Gpf Details Psr Numbers is  Called");		
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			long listlength = request.getParameter("listSize") != null ? Long
					.parseLong(StringUtility.getParameter("listSize", request)): 0;
			long billNo = request.getParameter("billNo") != null ? Long
					.parseLong(StringUtility.getParameter("billNo", request)): 0;
					
			List ITlist = new ArrayList();
			List PFlist = new ArrayList();
			List Gpflist = new ArrayList();
			List pkITids = new ArrayList();
			List pkPFids = new ArrayList();
			List pkGpfids = new ArrayList();
			List empIds = new ArrayList();
			logger.info("list length is:-->>>>>" + listlength);
			
			for(int i = 1; i <= listlength; i++)
			{
				if((StringUtility.getParameter("txtIT" + i, request) != StringUtility.getParameter("oldIT" + i, request)) && !(StringUtility.getParameter("txtIT" + i, request).equals(StringUtility.getParameter("oldIT" + i, request))) && (StringUtility.getParameter("txtIT" + i, request) != StringUtility.getParameter("oldIT" + i, request)) || !(StringUtility.getParameter("txtGpf" + i, request).equals(StringUtility.getParameter("oldGpf" + i, request))))
				{
					ITlist.add(StringUtility.getParameter("txtIT" + i, request));
					PFlist.add(StringUtility.getParameter("txtPF" + i, request));
					Gpflist.add(StringUtility.getParameter("txtGpf" + i, request));
					pkITids.add(StringUtility.getParameter("pkITid" + i, request));
					pkPFids.add(StringUtility.getParameter("pkPFid" + i, request));
					pkGpfids.add(StringUtility.getParameter("pkGpfid" + i, request));
					empIds.add(StringUtility.getParameter("empId" + i, request));
				}
			}
			logger.info(":::::::::::::::::::: VOGEN  billNo : " + billNo);
			objectArgs.put("billNo",billNo);
			objectArgs.put("empIds",empIds);
			objectArgs.put("pkITids",pkITids);
			objectArgs.put("pkPFids",pkPFids);
			objectArgs.put("pkGpfids",pkGpfids);
			objectArgs.put("ITlist",ITlist);
			objectArgs.put("PFlist",PFlist);
			objectArgs.put("Gpflist",Gpflist);
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...//**//**//**");
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());		
		}		
		return retObj;
	}
}
	
	