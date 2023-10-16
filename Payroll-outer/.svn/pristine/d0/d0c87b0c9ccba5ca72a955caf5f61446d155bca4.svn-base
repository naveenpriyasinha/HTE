package com.tcs.sgv.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.BillEdpDAO;
import com.tcs.sgv.common.dao.BillEdpDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;


/**
 * 
 * @author 602399
 * 
 */

public class EDPDetailsServiceImpl extends ServiceImpl  
		implements EDPDetailsService
{
	Log logger = LogFactory.getLog(getClass());	
	
	public ResultObject getEDPDetails(Map inputMap)
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		try
		{
			ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
			
			Long lLngBillTypeId = (Long) inputMap.get("BillTypeId");
									
			BillEdpDAO lEdpDtls = new BillEdpDAOImpl(TrnBillEdpDtls.class, serv.getSessionFactory());
			//add 2 parameter lang Id and location code By Bhavesh (602409)
			List lListExp = lEdpDtls.getExpObjHdDtlByBillType(lLngBillTypeId, null,SessionHelper.getLangId(inputMap),SessionHelper.getLocationCode(inputMap));
			List lListRecpt = lEdpDtls.getRecObjHdDtlByBillType(lLngBillTypeId, null,SessionHelper.getLangId(inputMap),SessionHelper.getLocationCode(inputMap));
			
			Map lMap = new HashMap();
			lMap.put("EdpExpDtls", lListExp);
			lMap.put("EdpRcptDtls", lListRecpt);
			
			inputMap.put("ResultMap", lMap);
			objRes.setResultValue(inputMap);				
		}
		catch(Exception e)
		{
			logger.error("Error in getEDPDetails and Error is : " + e, e);
		}
		
		return objRes;		
	}
}