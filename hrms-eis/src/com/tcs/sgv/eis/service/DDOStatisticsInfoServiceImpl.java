package com.tcs.sgv.eis.service;
import java.util.ArrayList;
import java.util.Map;

import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DemoViewDAOImpl;
import com.tcs.sgv.eis.valueobject.DemoViewVO;

public class DDOStatisticsInfoServiceImpl {
	
	Log logger = LogFactory.getLog( getClass() );
	
	public ResultObject getCmbDistricts(Map objArgs)
	{
		logger.info("Within the getCmbDistricts of DDOStatisticsInfoServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator =  (ServiceLocator) objArgs.get("serviceLocator");
		Map loginMap = (Map)objArgs.get("baseLoginmap");
		DemoViewDAOImpl demoViewDaoImpl = new DemoViewDAOImpl(DemoViewVO.class,serviceLocator.getSessionFactory());
			try{
				List demoViewLst = new ArrayList();
				demoViewLst = demoViewDaoImpl.getDemoDisplayValues();
				logger.info("Size of Demo Value"+demoViewLst.size());
				
				objArgs.put("demoViewLst", demoViewLst);
				objRes.setResultValue(objArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
		}catch(Exception e)
		{
			logger.info("Exception in DDOStatisticsInfoServiceImpl");
			logger.error("Error is "+e.getMessage());
		}
		return objRes;
	}
}
