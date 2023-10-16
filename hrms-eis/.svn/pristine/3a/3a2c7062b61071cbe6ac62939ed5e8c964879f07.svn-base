package com.tcs.sgv.eis.service;




import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;

public class OrderHeadMasterVOGen extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	
	
	public ResultObject generateMapForOrderHeadMaster(Map objectArgs) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
        
        try
        {
        	
        	
            logger.info("********************Inside Add/Update OrderHead*********************");
        	long orderId = 0;
        	
        	if(StringUtility.getParameter("order", request)!=null)
        		orderId=Long.parseLong(StringUtility.getParameter("order", request));
        	logger.info("order:---> " + orderId);
    		objectArgs.put("order",orderId);
        	long headId = 0;
        	if(StringUtility.getParameter("head", request)!=null)
        		headId=Long.parseLong(StringUtility.getParameter("head", request));
    		objectArgs.put("head",headId);
        	HttpSession session=request.getSession();		
    		//LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
    		//long langId=loginDetails.getLangId();
    		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	        
    		HrPayOrderHeadMpg hrPayOrderHeadMpg=new HrPayOrderHeadMpg();
    		hrPayOrderHeadMpg.setOrderId(orderId);
    		//hrPayOrderHeadMpg.setSubheadId(headId);
    		
            String updateflag = StringUtility.getParameter("updateflag", request);
            long ohMapID=0;
            long  ohMapId =0;
            if(request.getParameter("ohMapId")!=null && !request.getParameter("ohMapId").equals(""))
            	ohMapId = Long.parseLong(request.getParameter("ohMapId"));

    		hrPayOrderHeadMpg.setOrderHeadId(ohMapId);
            
            objectArgs.put("updateflag",updateflag);
    		objectArgs.put("ohMapId",ohMapId);

      		objectArgs.put("hrPayOrderHeadMpg",hrPayOrderHeadMpg);
    		retObj.setResultCode(ErrorConstants.SUCCESS);
            retObj.setResultValue(objectArgs);
            

        }
        catch (Exception e)
        {
            logger.error("Exception in VOGeneratorImpl.generateMap " + e, e);
            ResultObject retObject = new ResultObject(ErrorConstants.ERROR);
            retObj.setThrowable(e);
            

        }
        return retObj;
	
}
}
