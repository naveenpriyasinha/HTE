package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class PayslipParaVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject generateMapForPayslipGeneration(Map objectArgs) 
	{
		try{
		logger.info("PayslipParaVOGen generateMapForPayslipGeneration Called");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");									
        String month = request.getParameter("selMonth");
        String year = request.getParameter("selYear");
        String billNo = request.getParameter("selBill");
        //String selectedEmpId = request.getParameter("txtEmpId");
        //String selectedDeptId = request.getParameter("deptId");
        
        //objectArgs.put("selectedEmpId", selectedEmpId);
        objectArgs.put("month",month);
        objectArgs.put("year",year);
        objectArgs.put("billNo",billNo);
        
        //Added by Abhilash
        String dsgnId = request.getParameter("dsgnId");
        logger.info("In PayslipParaVOGen dsgnId is ***********"+dsgnId);
        objectArgs.put("dsgnId",dsgnId);
        
        String employeeid = request.getParameter("employeeid");
        logger.info("In PayslipParaVOGen dsgnId is ***********"+employeeid);
        objectArgs.put("employeeid",employeeid);
        
        //Ended by Abhilash
        
        //objectArgs.put("selectedDeptId",selectedDeptId);
        //logger.info("inside the generate PayslipPara VOGEN " + month + "--" + year + "\nemp id is " + selectedEmpId);
        logger.info("monht: year : bill No in generatePayslip VOGEN Is"+month+": "+year+": "+billNo);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);		
		}
	   catch(Exception e){
			ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
			logger.info("Exception Ocuures...approvePayBill");
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());			
			
		}		
		return retObj;
	}		
}
