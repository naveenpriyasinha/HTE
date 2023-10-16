package com.tcs.sgv.eis.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;



public class empAllRecService extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	
	
	public ResultObject getempAllRec(Map objectArgs)
	{
		long empId=0;
		logger.info("in AllEmpRec Service");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		
		String actionName = (String)voToService.get("actionName");
		if(actionName.trim().equalsIgnoreCase("empView"))
		{
			if(voToService.get("empId")!=null)
			  empId=Long.parseLong(voToService.get("empId").toString());
		}
		EmpInfoDAOImpl empInfo = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
		HrEisEmpMst hrEisEmpMst=null;
		if(empId!=0)
		 hrEisEmpMst = empInfo.read(empId);
		
		logger.info("EmpId in getEmpAllRec is " + empId);
		objectArgs.put("empId",empId);
		objectArgs.put("hrEisEmpMst",hrEisEmpMst);	   
			            		
        resultObject.setResultCode(ErrorConstants.SUCCESS);
        resultObject.setResultValue(objectArgs);
        //resultObject.setViewName("empEditList");
        resultObject.setViewName("empAllRec");
        
      	return resultObject;
	} 
}
//end of to be changed
