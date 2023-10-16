package com.tcs.sgv.pdpla.service;

import java.util.ArrayList;
import java.util.Map;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.dao.PdPlaChqListDAOImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChq;

public class PdPlaChqListServiceImpl extends ServiceImpl implements PdPlaChqListService
{

	public ResultObject  ShowChqList(Map orgsMap) 
	{
		ServiceLocator servLoc = (ServiceLocator) orgsMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try 
		{
			
	PdPlaChqListDAOImpl ObjChqList= new PdPlaChqListDAOImpl(TrnPdChq.class, servLoc.getSessionFactory());
		
		ArrayList searchList = (ArrayList) ObjChqList.ChqListShow();
					
		orgsMap.put("ResultMap", searchList);

		objRes.setResultValue(orgsMap);
		
		
		objRes.setViewName("ShowChqsList");
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		return objRes;
	}
	return objRes;

	}
	
}

