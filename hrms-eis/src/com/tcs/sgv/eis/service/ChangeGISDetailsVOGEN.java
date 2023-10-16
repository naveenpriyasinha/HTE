package com.tcs.sgv.eis.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

public class ChangeGISDetailsVOGEN extends ServiceImpl{
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);	
	
	@SuppressWarnings("unchecked")
	public ResultObject updateEmpChangedGISData(Map objectArgs){
		try {
			@SuppressWarnings("unused")
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		logger.info("ChangeGISDetailsVOGEN updateEmpChangedGISData Called:::::::::::");						
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		//ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		long empId = Long.parseLong(StringUtility.getParameter("empId", request));
		long empGISId = Long.parseLong(StringUtility.getParameter("empGISId", request));
		long gisRevApplicableId = Long.parseLong(StringUtility.getParameter("cmbGisApplicable", request));
		long gisGroupRevId = Long.parseLong(StringUtility.getParameter("cmbGisGroupRev", request));
		String revMemshipdate = StringUtility.getParameter("RevMemshipdate", request);
		String ordername = StringUtility.getParameter("txtOrder", request);
		String orderDate = StringUtility.getParameter("orderDate", request);
		String remarks = StringUtility.getParameter("remarks", request);
		
		
		logger.info("empId in VOGEN:::::::::::::::::::::" +empId);
		logger.info("empGISId in VOGEN:::::::::::::::::::::" +empGISId);
		logger.info("gisApplicableId in VOGEN:::::::::::::::::::::" +gisRevApplicableId);
		logger.info("gisGroupRevId in VOGEN:::::::::::::::::::::" +gisGroupRevId);
		logger.info("revMemshipdate in VOGEN:::::::::::::::::::::" +revMemshipdate);
		logger.info("ordername in VOGEN:::::::::::::::::::::" +ordername);
		logger.info("orderDate in VOGEN:::::::::::::::::::::" +orderDate);
		logger.info("remarks in VOGEN:::::::::::::::::::::" +remarks);
		
		objectArgs.put("empId",empId);
		objectArgs.put("empGISId",empGISId);
		objectArgs.put("gisRevApplicableId",gisRevApplicableId);
		objectArgs.put("gisGroupRevId",gisGroupRevId);
		objectArgs.put("revMemshipdate",revMemshipdate);
		objectArgs.put("ordername",ordername);
		objectArgs.put("orderDate",orderDate);
		objectArgs.put("remarks",remarks);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);

		}

		catch(Exception e){		
			objectArgs.put("MESSAGECODE",3001);
			retObj.setResultValue(objectArgs);			
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");		
			return retObj;	
	}
		return retObj;
}

	
	
	
	

}
