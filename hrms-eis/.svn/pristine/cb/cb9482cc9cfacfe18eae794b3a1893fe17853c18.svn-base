package com.tcs.sgv.eis.service;




import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;

public class SGDMpgVOGen extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	
	
	public ResultObject GradeDesignationScaleMaster(Map objectArgs) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
        
        try
        {
            logger.info("********************Inside Add/Update GradeDesignationScaleMaster");
        	long gdMapId = 0;
        	if(StringUtility.getParameter("gdMapId", request)!=null)
        		gdMapId=Long.parseLong(StringUtility.getParameter("gdMapId", request));
        	logger.info("gdMapId:---> " + gdMapId);
    		objectArgs.put("gdMapId",gdMapId);
        	long scaleId = 0;
        	if(StringUtility.getParameter("scale", request)!=null)
        		scaleId=Long.parseLong(StringUtility.getParameter("scale", request));
    		objectArgs.put("scaleId",scaleId);
        	HttpSession session=request.getSession();		
    		LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
    		long langId=loginDetails.getLangId();
    		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	        
			HrEisSgdMpg hrEisSgdMpg=new HrEisSgdMpg();

            String updateflag = StringUtility.getParameter("updateflag", request);
            long SgdMapID=0;
            long  SgdMapId =0;
            if(request.getParameter("SgdMapId")!=null && !request.getParameter("SgdMapId").equals(""))
            	SgdMapId = Long.parseLong(request.getParameter("SgdMapId"));
    		objectArgs.put("updateflag",updateflag);
    		objectArgs.put("SgdMapId",SgdMapId);

      		objectArgs.put("hrEisSgdMpg",hrEisSgdMpg);
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
