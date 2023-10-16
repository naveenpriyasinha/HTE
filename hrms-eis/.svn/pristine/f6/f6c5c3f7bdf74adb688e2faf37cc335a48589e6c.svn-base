package com.tcs.sgv.eis.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class PaybillGenerationVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public synchronized ResultObject generateMapForPaybillGeneration(Map objectArgs) 
	{
		try{
		logger.info("PaybillGenerationVOGen generateMapForPaybillGeneration Called");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");									
        String month = request.getParameter("selMonth");
        String year = request.getParameter("selYear");
		 String deptNo = (String)request.getParameter("cmbDept");
	     String demandNo = (String)request.getParameter("cmbDemand");
	     String mjrHead = (String)request.getParameter("cmbMjrHead");
	     String subMjrHead =  (String)request.getParameter("cmbSubMjrHead");
	     String mnrHead = (String)request.getParameter("cmbMnrHead");
	     String subHead = (String)request.getParameter("cmbSubHead");
	     String dtlHead = (String)request.getParameter("cmbDtlHead");
	     String cmbSelGrade = (String)request.getParameter("txtGradeId");
	     
	     logger.info("month********"+month);
	     logger.info("year********"+year);
	     
	     logger.info("demandNo********"+demandNo);
	     logger.info("mjrHead********"+mjrHead);
	     logger.info("subMjrHead********"+subMjrHead);
	     logger.info("mnrHead********"+mnrHead);
	     logger.info("subHead********"+subHead);
	     
	     
//	   Added By Urvin Shah.
	     String paybillNo = request.getParameter("billNo").toString();
	     // End.
	     String ctrlNo= request.getParameter("ctrlNo")!=null?(String)request.getParameter("ctrlNo"):"";
	     
	     String billTypeCmb ="";
	     
	     if(request.getParameter("billTypeCmb")!=null && !request.getParameter("billTypeCmb").equals("") && !request.getParameter("billTypeCmb").equals("-1") ) 
	     {	 
	    	 billTypeCmb=request.getParameter("billTypeCmb");
	     }
	     else
	     {
	    	 billTypeCmb="paybill";
	     }
	     logger.info("in paybill vogen for testing ctrlNo "+ ctrlNo+" .............and billType is "+billTypeCmb);
	     
	     //Added by Paurav for getting designations for paybill generation
	     String designations = (String)request.getParameter("designations");
	     String gradeId1 = (String)request.getParameter("gradeId1");
	     
	     //Ended by Paurav
	     
	     //added by ravysh
	     String from_Month = request.getParameter("selFromMonth");
	     String from_Year = request.getParameter("selFromYear");
	    
	     objectArgs.put("from_Month",from_Month);
	     objectArgs.put("from_Year",from_Year);
	      //ended by ravysh  
	     
	    String arrearType = request.getParameter("arrearType")!=null?request.getParameter("arrearType").toString():"";
	     
        objectArgs.put("month",month);
        objectArgs.put("year",year);
        objectArgs.put("deptNo",deptNo);
        objectArgs.put("demandNo",demandNo);
        objectArgs.put("mjrHead",mjrHead);
        objectArgs.put("subMjrHead",subMjrHead);
        objectArgs.put("mnrHead",mnrHead);
        objectArgs.put("subHead",subHead);
        objectArgs.put("dtlHead",dtlHead);
        objectArgs.put("cmbSelGrade",cmbSelGrade);
        objectArgs.put("designations",designations);
        objectArgs.put("gradeId1",gradeId1);
//      Added By Urvin Shah.
        objectArgs.put("paybillNo",paybillNo);
        // End
        objectArgs.put("ctrlNo",ctrlNo);
       
        objectArgs.put("billTypeCmb",billTypeCmb);
        objectArgs.put("arrearType",arrearType);
        
        logger.info("inside the generate Paybill VOGEN " + month + "--" + year);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);		
		}
	   catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.info("Exception in generateMapForInsertBankMaster-----"+e);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());			
			
		}		
		return retObj;
	}		
	
	
	public ResultObject paybillview(Map objectArgs) 
	{
		try{
		logger.info("PaybillGenerationVOGen generateMapForPaybillGeneration Called");		
		
		HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");									
        String month = request.getParameter("selMonth");
        String year = request.getParameter("selYear");
	     
        objectArgs.put("month",month);
        objectArgs.put("year",year);


        logger.info("inside the generate Paybill VOGEN " + month + "--" + year);
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);		
		}
	   catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.info("Exception in generateMapForInsertBankMaster-----"+e);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());			
			
		}		
		return retObj;
	}		
}
