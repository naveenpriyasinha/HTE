package com.tcs.sgv.pdpla.service;

import java.util.ArrayList;
import java.util.Map;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.dao.PdPlaChallanListDAOImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallan;

public class PdPlaChallanListServiceImpl extends ServiceImpl implements PdPlaChallanListService
{

	public ResultObject  ShowChallanList(Map orgsMap) 
	{
		ServiceLocator servLoc = (ServiceLocator) orgsMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try 
		{
			
	PdPlaChallanListDAOImpl ObjChallanList= new PdPlaChallanListDAOImpl(TrnPdChallan.class, servLoc.getSessionFactory());
		
		ArrayList searchList = (ArrayList) ObjChallanList.ListShow();
					
		orgsMap.put("ResultMap", searchList);

		objRes.setResultValue(orgsMap);
		
		
		objRes.setViewName("ShowChallanList");
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		return objRes;
	}
	return objRes;

	}
	
}
