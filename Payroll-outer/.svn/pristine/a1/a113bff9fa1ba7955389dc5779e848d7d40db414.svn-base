package com.tcs.sgv.pdpla.service;
import java.util.ArrayList;
import java.util.Map;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.dao.PdplaShowPaymentDataDaoImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChqDetail;

public class PdplaShowPaymentDataServiceImpl extends ServiceImpl implements PdplaShowPaymentDataService {
	public ResultObject  ShowPaymentData(Map orgsMap) 
	{
		ServiceLocator servLoc = (ServiceLocator) orgsMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try 
		{
			
		////System.out.println("u r in serviceimpl class");
		PdplaShowPaymentDataDaoImpl daoObj= new PdplaShowPaymentDataDaoImpl(TrnPdChqDetail.class, servLoc.getSessionFactory());
		
		ArrayList searchList = (ArrayList) daoObj.PaymentData();
		////System.out.println("------searchList------"+searchList);			
		orgsMap.put("ResultMap", searchList);

		objRes.setResultValue(orgsMap);
		//System.out.println("returning object");
		//System.out.println(searchList);
		objRes.setViewName("ShowPaymentData");
		return objRes;
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		return null;
	}
	}
}
