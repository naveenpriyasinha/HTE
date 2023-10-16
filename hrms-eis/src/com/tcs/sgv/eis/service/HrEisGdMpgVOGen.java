package com.tcs.sgv.eis.service;




import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;

public class HrEisGdMpgVOGen extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	
	
	public ResultObject AddGradeDesignationmpg(Map objectArgs) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
        
        try
        {
            logger.info("********************Inside AddGradeDesignationmpg");
            String deptshortname = StringUtility.getParameter("deptshortname", request);
			int flag = 1;
            HrEisGdMpg hrEisGdMpg=new HrEisGdMpg();
			long GdMapId=0;
			if( request.getParameter("GdMapId")!=null)
				GdMapId = Long.parseLong((request.getParameter("GdMapId")));
            String updateflag = StringUtility.getParameter("updateflag", request);
            Long gradeId =Long.parseLong( StringUtility.getParameter("grade", request));
            Long desigId = Long.parseLong( StringUtility.getParameter("desig", request));
            objectArgs.put("hrEisGdMpg",hrEisGdMpg);
            objectArgs.put("updateflag",updateflag);
            objectArgs.put("GdMapId",GdMapId);
            objectArgs.put("gradeId",gradeId);
            objectArgs.put("desigId",desigId);
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
