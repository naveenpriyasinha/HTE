/**
 * This class is common for insert and update record.
 * It collects the data inserted in the AllowTypeMaster file and set it in to the HRPayAllowTypMst.
 * and return  Resultobject of the AllowTypeMasterServiceImpl class. 
 */

package com.tcs.sgv.payslip.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class commonPayslipVOGen extends ServiceImpl {
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
	Log logger = LogFactory.getLog(getClass());
	public ResultObject getcommonPayslipData(Map objectArgs){
		try{
			logger.info("commonPayslipVOGen  Called");		
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");									
	        String month = request.getParameter("selMonth")!=null?request.getParameter("selMonth"):"0";
	        String year = request.getParameter("selYear")!=null?request.getParameter("selYear"):"0";
	        String selectedEmpId = (request.getParameter("txtEmpId")!=null&&!request.getParameter("txtEmpId").equals(""))?request.getParameter("txtEmpId"):"0";
	        String selectedDeptId = (request.getParameter("deptId")!=null&&!request.getParameter("deptId").equals(""))?request.getParameter("deptId"):"0";
	        String billNo = (request.getParameter("billNo")!=null&&!request.getParameter("billNo").equals(""))?request.getParameter("billNo"):"";
	        String classArray=(request.getParameter("classArray")!=null&&!request.getParameter("classArray").equals(""))?request.getParameter("classArray"):"";
	        //System.out.println("the class array is:>>"+classArray);
	        String dsgnArray=(request.getParameter("dsgnIdArray")!=null&&!request.getParameter("dsgnIdArray").equals(""))?request.getParameter("dsgnIdArray"):"";
	        //System.out.println("the designation array is:>>"+dsgnArray);
	       
	        objectArgs.put("selectedEmpId", selectedEmpId);
	        objectArgs.put("month",month);
	        objectArgs.put("year",year);
	        objectArgs.put("selectedDeptId",selectedDeptId);
	        objectArgs.put("billNo",billNo);
	        objectArgs.put("classArray",classArray);
	        objectArgs.put("dsgnArray",dsgnArray);
	        
	        
	        logger.info("inside the generate PayslipPara VOGEN " + month + "--" + year + "\nemp id is " + selectedEmpId);
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);		
			}
		   catch(Exception e){
				ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);						
				logger.info("Exception Ocuures...commonPayslipVOGen");
				 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
				 resultObject.setResultValue(objectArgs);
				 resultObject.setResultCode(-1);
				 resultObject.setViewName("errorInsert");		
				e.printStackTrace();			
				
			}		
			return retObj;
		}
}
