package com.tcs.sgv.address.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;

public class AddressApproveVOGenerator extends ServiceImpl implements	VOGeneratorService{
	public final String REQUEST_OBJECT="requestObj";
	public final String REQUEST_PARAMETER="reqId";
	public final String REQUEST_ID="requestParam";
	private static final Log logger = LogFactory.getLog(AddressApproveVOGenerator.class);	
	public ResultObject generateMap(Map objectArgs) {
		String requestID="";
		ResultObject resultObject=new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest  request=(HttpServletRequest)objectArgs.get(REQUEST_OBJECT);
		try{
			HttpSession session = request.getSession();
			
			if(request.getParameter("corrId")!=null){
				requestID=request.getParameter("corrId");
			}else{
				requestID=session.getAttribute("WorkFlowCorrespondence_CorrId").toString();
			}
			
			logger.info("the Request Id is ::>>"+requestID);
		}catch(Exception e){
			logger.error(e);
		}
		objectArgs.put(REQUEST_ID, requestID);
		resultObject.setResultValue(objectArgs);
		return resultObject;
	}

}
