package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.AttachmentHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;

public class NonGovDeducMasterVOGen extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	
	
	public ResultObject generateMapNonGovDeducMaster(Map objectArgs){
	
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		List deducTypePeriod = new ArrayList();
		List lstDeducType = new ArrayList();
		
		try {
			logger.info("Non Government Deduction Master VOGEN Called");						
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
			String deducStartDate = null;
			String deducStartDateNew=null;
			String deducEndDate = null;
			String deducEndDateNew=null;
			String accno = null;
			String editMode = null;
			long nonGovDeducId;
			int counter;
			Date nonGovDeducStartDate = new Date();
			Date nonGovDeducEndDate = new Date();
			Date nonGovDeducEndDateNew = new Date();
			Date nonGovDeducStartDateNew = new Date();
			
			
			//long empId;
			Long deducTypeId;
			long nonGovDeducAmount;
			Long nonGovDeducPeriod;
			Date sysDate = new Date();
			editMode = StringUtility.getParameter("edit", request);
			logger.info("The Value of Edit is :-"+editMode);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");				
			List lstHrPayNonGovDeduction = new ArrayList();
			List lstEmpId = new ArrayList();
			HrPayNonGovDeduction hrPayNonGovDeduction = null;
			//HrPayDeducTypeMst hrPayDeducTypeMst = null;
			
			if(editMode != null && editMode.equalsIgnoreCase("Y")) {
				hrPayNonGovDeduction = new HrPayNonGovDeduction();
				logger.info("AllowTypeMasterVOGen in edit mode Called");
				String strNonGovDeducId = StringUtility.getParameter("nonGovDeducId", request);
				logger.info("strNonGovDeducId is=============>"+strNonGovDeducId);
				
				if( strNonGovDeducId!=null ){
					nonGovDeducId = Long.parseLong(strNonGovDeducId);			  
					hrPayNonGovDeduction.setNonGovDeducId(nonGovDeducId);
					//hrPayNonGovDeduction.setUpdatedDate(sysDate);
					logger.info("in if strNonGovDeducId is=============>"+strNonGovDeducId);
				}
				
				//deducTypeId = Long.parseLong(StringUtility.getParameter("deductionType", request));
				logger.info("in out of if strNonGovDeducId is=============>"+strNonGovDeducId);
				nonGovDeducAmount = Long.parseLong(StringUtility.getParameter("deducAmount1", request));
				logger.info("in out of if strNonGovDeducId is=============>"+nonGovDeducAmount);
				deducStartDate = StringUtility.getParameter("startDate1", request);
				deducStartDateNew = StringUtility.getParameter("startDate", request);
				
				deducEndDate = StringUtility.getParameter("endDate1", request);
				deducEndDateNew = StringUtility.getParameter("endDate", request);
				accno = StringUtility.getParameter("accno1", request);
				logger.info("in out of if strNonGovDeducId is=============>"+accno);
				nonGovDeducStartDate = sdf.parse(deducStartDate);
				nonGovDeducStartDateNew = sdf.parse(deducStartDateNew);
				//Added By Varun For Non Mandatory End Date
				if(deducEndDate!=null&&!deducEndDate.equals(""))
				{
					nonGovDeducEndDate = sdf.parse(deducEndDate);
				}
				else
				{
					nonGovDeducEndDate = null;
				}
				//Ended By Varun
				if(deducEndDateNew!=null&&!deducEndDateNew.equals(""))
				{
					nonGovDeducEndDateNew = sdf.parse(deducEndDateNew);
				}
				else
				{
					nonGovDeducEndDateNew = null;
				}
				
				
				nonGovDeducPeriod = Long.parseLong(StringUtility.getParameter("deductionPeriod", request)); 
				logger.info("in out of if strNonGovDeducId is=============>"+nonGovDeducPeriod);
				//logger.info("Deduc Type:-"+deducTypeId);
				logger.info("Amount:-"+nonGovDeducAmount);
				logger.info("Start Date:-"+deducStartDate);
				logger.info("End Date:-"+deducEndDate);
				logger.info("Deduction Period:-"+nonGovDeducPeriod);
				logger.info("nonGovDeducEndDateNew Period:-"+nonGovDeducEndDateNew);
				
				//hrPayDeducTypeMst.setDeducCode(deducTypeId);
				//hrPayNonGovDeduction.setHrPayDeducTypeMst(hrPayDeducTypeMst);		
				hrPayNonGovDeduction.setNonGovDeducAmount(nonGovDeducAmount);
				hrPayNonGovDeduction.setNonGovDeducEfftStartDt(nonGovDeducStartDateNew);
				hrPayNonGovDeduction.setNonGovDeducEfftEndDt(nonGovDeducEndDateNew);
				hrPayNonGovDeduction.setNonGovDeducAccNo(accno);
				//hrPayNonGovDeduction.setNonGovDeducPeriod(nonGovDeducPeriod);
				//objectArgs.put("deducTypeId",deducTypeId);
				objectArgs.put("nonGovDeducPeriod",nonGovDeducPeriod);
				objectArgs.put("hrPayNonGovDeduc",hrPayNonGovDeduction);
				objectArgs.put("editMode",editMode);
				retObj.setResultValue(objectArgs);
				retObj.setResultCode(ErrorConstants.SUCCESS);
				logger.info("U are out of VOGEN");
			}//end if
			
			objectArgs.put("lstDeducType",lstDeducType);
			objectArgs.put("deducTypePeriod",deducTypePeriod);
			objectArgs.put("lstEmpId",lstEmpId);
			objectArgs.put("editMode",editMode);
			
			retObj.setViewName("ajaxData");
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("U are out of VOGEN");
	}
	catch(Exception e){
		objectArgs.put("MESSAGECODE",3001);
		logger.info("eoorro is==========>"+e);
		logger.error("Error is: "+ e.getMessage());
		retObj.setResultValue(objectArgs);			
		retObj.setResultCode(-1);
		retObj.setViewName("errorPage");		
		return retObj;			
	}	
	return retObj;
	}
	
	
	public ResultObject updateNonGovDataByExcel(Map objectArgs){
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		
		String dirName="";
		try {
		logger.info("Non Government Deduction Master VOGEN Called");
		HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		AttachmentHelper attachmentHelper = new AttachmentHelper();
		List validationDetsList = new ArrayList(); 
        Map fileItemArrayListMap = attachmentHelper.fileItemArrayListMap;
        logger.info("generate map method fileItemArrayListMap"+fileItemArrayListMap);

        String key = (String) request.getSession().getAttribute("name") +"nonGovData" + request.getSession().getId();
        logger.info("generate map method key"+key);
        long deducType = (request.getParameter("deductionType")!=null && !request.getParameter("deductionType").equals(""))?Long.parseLong(request.getParameter("deductionType").toString()):0;
        
        long month = (request.getParameter("selMonth")!=null && !request.getParameter("selMonth").equals(""))?Long.parseLong(request.getParameter("selMonth").toString()):0;
        long year = (request.getParameter("selYear")!=null && !request.getParameter("selYear").equals(""))?Long.parseLong(request.getParameter("selYear").toString()):0;
        
        
        ArrayList attachmentList = (ArrayList) fileItemArrayListMap.get(key);
        
        logger.info("The size of the attechment is:-"+attachmentList.size());
		//request = (MultipartRequest)objectArgs.get("requestObj");
				
		//UploadedFile file = new UploadedFile(); 
		//String type = request.getContentType();
		
        objectArgs.put("attachmentList", attachmentList);
        objectArgs.put("deducType", deducType);
        objectArgs.put("month", month);
        objectArgs.put("year", year);
        
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		logger.info("U are out of VOGEN");	
		}
	
		catch(Exception e){
			objectArgs.put("MESSAGECODE",3001);
			logger.info("eoorro is==========>"+e);
			logger.error("Error is: "+ e.getMessage());
			retObj.setResultValue(objectArgs);			
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");		
			return retObj;			
		}	
		return retObj;
	}
	
	
	/*
	 * @purpose: 	- voToXml.
	 * 				- To insert multiple non-gov data for single and multiple employee(s)..
	 */
	public ResultObject multipleData( Map objectArgs){
		
		logger.info("varun $harma: multipleData() method called....");
		
		logger.info("objectArgs in multipleData: "+objectArgs.toString());
		
		
		ResultObject resultObject = new ResultObject( ErrorConstants.SUCCESS);
		
		String deducStartDate = null;
		String deducEndDate = null;
		String accno = null;
		Date nonGovDeducStartDate = new Date();
		Date nonGovDeducEndDate = new Date();
		long nonGovDeducAmount;
		Date sysDate = new Date();
		long deductionType;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


		HrPayNonGovDeduction hrPayNonGovDeduction = null;
		
		try{

			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		

			hrPayNonGovDeduction = new HrPayNonGovDeduction();
			
			//For New Entry

			long empId = Long.parseLong(StringUtility.getParameter("Employee_ID_NonGovEmpSearch", request).toString());
			logger.info("empId: " +empId);
			
			deductionType = Long.parseLong(StringUtility.getParameter( "deductionType", request));
			logger.info("deductionType : " + deductionType);
			
			nonGovDeducAmount = Long.parseLong(StringUtility.getParameter("deducAmount", request));
			logger.info("nonGovDeducAmount is: "+nonGovDeducAmount);
			
			deducStartDate = StringUtility.getParameter("startDate", request);
			logger.info("deducStartDate is: "+deducStartDate);
			
			deducEndDate = StringUtility.getParameter("endDate", request);
			logger.info("deducEndDate is: "+deducEndDate);
			
			accno=StringUtility.getParameter("accno", request);
			logger.info("accno is: "+accno);
			
			nonGovDeducStartDate = sdf.parse(deducStartDate);
			logger.info("formatting date deducStartDate: " +nonGovDeducStartDate);

			if(deducEndDate!=null&&!deducEndDate.equals("")){
				nonGovDeducEndDate = sdf.parse(deducEndDate);
				logger.info("formatted  deducEndDate: " +nonGovDeducEndDate);
			}
			else{
				nonGovDeducEndDate = null;
				logger.info("nonGovDeducEndDate set to null, end date was not specified by user");
			}


			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
			
			
			//set employee id
			hrEisEmpMst.setEmpId(empId);
			hrPayNonGovDeduction.setHrEisEmpMst(hrEisEmpMst);
			//set deduction type
			hrPayDeducTypeMst.setDeducCode(deductionType);
			hrPayNonGovDeduction.setHrPayDeducTypeMst(hrPayDeducTypeMst);
			//set created date
			hrPayNonGovDeduction.setCreatedDate(sysDate);
			//set deduction amount
			hrPayNonGovDeduction.setNonGovDeducAmount(nonGovDeducAmount);
			//set start date
			hrPayNonGovDeduction.setNonGovDeducEfftStartDt(nonGovDeducStartDate);
			//set end date
			hrPayNonGovDeduction.setNonGovDeducEfftEndDt(nonGovDeducEndDate);
			//set account number
			hrPayNonGovDeduction.setNonGovDeducAccNo(accno);

			
			//create XML file 
			String xmlFileId = FileUtility.voToXmlFileByXStream(hrPayNonGovDeduction);
	       	objectArgs.put("ajaxKey", xmlFileId);
	       	logger.info(" in multipleData XML file PATH:::"+xmlFileId);

			resultObject.setViewName("ajaxData");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			logger.info("U are out of VOGEN");

			//enable objects for GC
			hrEisEmpMst = null;
			hrPayDeducTypeMst = null;			
			
		}catch( Exception e){
			logger.info("Exception occured: \n" +e.toString());
		}
		
						
		return resultObject;
	}//end method:multipleData()
	
}//end class:NonGovDeducMasterVOGen