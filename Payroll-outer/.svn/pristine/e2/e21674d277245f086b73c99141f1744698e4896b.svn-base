package com.tcs.sgv.pdpla.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.dao.PDPaymentDAOImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChqDetail;

public class PDPaymentServiceImpl extends ServiceImpl implements PDPaymentService 
{
Log gLogger = LogFactory.getLog(getClass());
	
	public ResultObject addPDPaymentDet(Map orgsMap)
	{
		
		ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
				
		try
		{
			TrnPdChqDetail lPdChqDetlVO = new TrnPdChqDetail();
			lPdChqDetlVO = (TrnPdChqDetail) orgsMap.get("TrnPdChqDetail");
			
			PDPaymentDAOImpl lobjDAOImpl = new PDPaymentDAOImpl(TrnPdChqDetail.class, serv.getSessionFactory());
			
			long lpaymentId= BptmCommonServiceImpl.getNextSeqNum("trn_pd_chq_detail", orgsMap);
			lPdChqDetlVO.setPaymentId(lpaymentId);
			
			lobjDAOImpl.create(lPdChqDetlVO);
			
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
