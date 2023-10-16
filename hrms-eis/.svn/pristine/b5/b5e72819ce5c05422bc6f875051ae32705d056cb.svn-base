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

public class TokenNumberVOGen extends ServiceImpl
{	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject UpdateTokenNumberVOGenImpl(Map<String, Object> objectArgs) 
	{
		try
		{
			logger.info("UpdatePsrNumberVOGen Psr Numbers is  Called");		
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			long listlength = request.getParameter("listSize") != null ? Long
					.parseLong(StringUtility.getParameter("listSize", request)): 0;
			int Month = (StringUtility.getParameter("month", request).toString() != null) && !(StringUtility.getParameter("month", request).toString().equals("")) ? Integer.parseInt(StringUtility.getParameter("month", request).toString()): 0;
			long Year = (StringUtility.getParameter("year", request).toString() != null) && !(StringUtility.getParameter("year", request).toString().equals("")) ? (Long.parseLong(StringUtility.getParameter("year", request).toString())) : 0;
			String billNum = (StringUtility.getParameter("billNum", request).toString() != null) && !(StringUtility.getParameter("billNum", request).toString().equals("")) ? StringUtility.getParameter("billNum", request).toString() : "";
			String billtype = (StringUtility.getParameter("billtype", request).toString() != null) && !(StringUtility.getParameter("billtype", request).toString().equals("")) ? StringUtility.getParameter("billtype", request).toString() : "";
			String currBillStatus = (StringUtility.getParameter("currBillStatus", request).toString() != null) && !(StringUtility.getParameter("currBillStatus", request).toString().equals("")) ? StringUtility.getParameter("currBillStatus", request).toString() : "";
			String Flag = StringUtility.getParameter("Flag", request);
			List tokenlist = new ArrayList();
			List tokendatelist = new ArrayList();
			List billnolist = new ArrayList();
			
			for(int i = 1; i <= listlength; i++)
			{
				if(!((StringUtility.getParameter("oldtoken" + i, request)).equals(StringUtility.getParameter("tokenname" + i, request))) || !((StringUtility.getParameter("olddate" + i, request)).equals(StringUtility.getParameter("dttokenDate" + i, request))))
				{
						tokenlist.add(StringUtility.getParameter("tokenname" + i, request));
						tokendatelist.add(StringUtility.getParameter("dttokenDate" + i, request));
						billnolist.add(StringUtility.getParameter("billno" + i, request));
				}
			}	
			objectArgs.put("year",Year);
			objectArgs.put("month",Month);
			objectArgs.put("billNum",billNum);
			objectArgs.put("tokenlist",tokenlist);
			objectArgs.put("billnolist",billnolist);
			objectArgs.put("Flag",Flag);
			objectArgs.put("billtype",billtype);
			objectArgs.put("currBillStatus",currBillStatus);
			objectArgs.put("tokendatelist",tokendatelist);

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