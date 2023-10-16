package com.tcs.sgv.pdpla.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.dao.PDReceiptDAOImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallanDetail;


public class PDReceiptServiceImpl extends ServiceImpl implements PDReceiptService 
{
Log gLogger = LogFactory.getLog(getClass());
	
	public ResultObject addPDReceiptDet(Map orgsMap)
	{
		
		ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
				
		try
		{
			TrnPdChallanDetail lPdChDetlVO = new TrnPdChallanDetail();
			lPdChDetlVO = (TrnPdChallanDetail) orgsMap.get("TrnPdChallanDetail");
			
			PDReceiptDAOImpl lobjDAOImpl = new PDReceiptDAOImpl(TrnPdChallanDetail.class, serv.getSessionFactory());
			
			long lreceipId= BptmCommonServiceImpl.getNextSeqNum("trn_pd_challan_detail", orgsMap);
			lPdChDetlVO.setReceipId(lreceipId);
			lPdChDetlVO.toString();
			lobjDAOImpl.create(lPdChDetlVO);
			
			objRes.setResultValue(orgsMap);
			objRes.setViewName("successful");
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return objRes;
	}
}
