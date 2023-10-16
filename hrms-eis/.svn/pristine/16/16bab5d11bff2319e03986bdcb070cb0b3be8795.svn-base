package com.tcs.sgv.eis.service;


//Comment By Maruthi For import Organisation.

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.AddDeptDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisDeptMst;

public class AddDeptVOGen extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	
	
	public ResultObject AddDept(Map objectArgs) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
        
        try
        {
        	
            String deptname = StringUtility.getParameter("deptname", request);
        	String deptdesc = StringUtility.getParameter("deptdesc", request);
            String deptshortname = StringUtility.getParameter("deptshortname", request);
    		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    		HrEisDeptMst hrEisDeptMst=new HrEisDeptMst();

            AddDeptDAOImpl addDeptDAOImpl=new AddDeptDAOImpl(HrEisDeptMst.class,serv.getSessionFactory());
            hrEisDeptMst.setDeptName(deptname);
            hrEisDeptMst.setDeptDesc(deptdesc);
            hrEisDeptMst.setDeptShortName(deptshortname);
            
            String updateflag = StringUtility.getParameter("updateflag", request);
            String deptId = StringUtility.getParameter("deptId", request);
    		
            objectArgs.put("updateflag",updateflag);
			objectArgs.put("deptId",deptId);
    		objectArgs.put("AddDept",hrEisDeptMst);
    		retObj.setResultCode(ErrorConstants.SUCCESS);
            retObj.setResultValue(objectArgs);
        }
        catch (Exception e)
        {
            logger.error("Exception in CaseDiaryVOGeneratorImpl.generateMap " + e, e);
            ResultObject retObject = new ResultObject(ErrorConstants.ERROR);
            retObj.setThrowable(e);
            

        }
        return retObj;
	
}
}
