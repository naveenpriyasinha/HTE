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
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrInvestTypeMst;
import com.tcs.sgv.eis.valueobject.HrItSectionMst;

public class InvestTypeMstVOGenImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	
	HttpServletRequest request = null;
	ServiceLocator serv = null;
	
	HrInvestTypeMst hrInvestTypeMst = null;
	Date sysDate = null;
	String investStartDate = null;
	String investEndDate = null;
	String editMode = null;				
	
	long statusCode;
	Date dtInvestStartDate = null;
	Date dtInvestEndDate = null;
	Long sectionId;
	String investName;
	String investDesc;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public ResultObject generateInvestTypeMap(Map objectArgs){
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		

		
			try {
				logger.info("Invest Type Master VOGEN Called");	
				
				request = (HttpServletRequest) objectArgs.get("requestObj");
				serv = (ServiceLocator)objectArgs.get("serviceLocator");		
				
				sysDate = new Date();
				dtInvestStartDate = new Date();
				dtInvestEndDate = new Date();
				editMode = StringUtility.getParameter("edit", request);
				logger.info("The Value of Edit is :-"+editMode);

				long investTypeId=0;
				
				
				if(editMode.equalsIgnoreCase("Y")) {

					hrInvestTypeMst = new HrInvestTypeMst();
					logger.info("Investment Type Master in edit mode Called");
					String strInvestTypeId = StringUtility.getParameter("investTypeId", request);
					
					if( strInvestTypeId!=null ){
						investTypeId = Long.parseLong(strInvestTypeId);			  

					}
					
					sectionId = Long.parseLong(StringUtility.getParameter("sectId", request));
					investName = StringUtility.getParameter("investName", request);				
					investDesc = StringUtility.getParameter("investDesc", request);
					statusCode = Long.parseLong(StringUtility.getParameter("statusFlag", request));
					investStartDate = StringUtility.getParameter("startDate", request);
					investEndDate = StringUtility.getParameter("endDate", request);				
					dtInvestStartDate = sdf.parse(investStartDate);
					
					if(!(investEndDate.equals("") || investEndDate == null)){
						dtInvestEndDate = sdf.parse(investEndDate);
					}
					else {
						dtInvestEndDate = null;
					}
					
					logger.info("The Start Date is:-"+dtInvestStartDate);
					logger.info("The End Date is:-"+dtInvestEndDate);
					hrInvestTypeMst.setInvestName(investName);
					hrInvestTypeMst.setInvestDesc(investDesc);
					hrInvestTypeMst.setActivateFlag(statusCode);
					hrInvestTypeMst.setStartDate(dtInvestStartDate);
					hrInvestTypeMst.setEndDate(dtInvestEndDate);			
					
					
					objectArgs.put("hrInvestType",hrInvestTypeMst);
					objectArgs.put("sectionId",sectionId);
					objectArgs.put("investTypeId",investTypeId);
					objectArgs.put("editMode",editMode);
					
					retObj.setResultValue(objectArgs);
					retObj.setResultCode(ErrorConstants.SUCCESS);
					
					logger.info("U are out of VOGEN");
			}//end if ( for edit mode )
				

				objectArgs.put("editMode",editMode);
				retObj.setResultValue(objectArgs);
				retObj.setResultCode(ErrorConstants.SUCCESS);
				logger.info("U are out of Investment Type VOGEN");	
			
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
	
	/*
	 * @purpose: 	- voToXml.
	 * 				- To insert multiple Investment types
	 */
	public ResultObject addMultipleInvestmentType(Map objectArgs){
		ResultObject resultObject = new ResultObject();
		
			try{
				logger.info("inside addMultipleInvestmentType()");
				request = (HttpServletRequest) objectArgs.get("requestObj");
				
				
				/* GET HrInvestTypeMst related information  */
				
				hrInvestTypeMst = new HrInvestTypeMst();
				
				//Investment name
				investName = StringUtility.getParameter("investName", request);
				
				//Investment description
				investDesc = StringUtility.getParameter("investDesc", request);
				
				//Investment section id 
				sectionId = Long.parseLong(StringUtility.getParameter("sectId", request));
				
				//Investment start date
				investStartDate = StringUtility.getParameter("startDate", request);
				
				//Investment end date
				investEndDate = StringUtility.getParameter("endDate", request);
				
				dtInvestStartDate = sdf.parse(investStartDate);
					if(!(investEndDate.equals("") || investEndDate == null)){
						dtInvestEndDate = sdf.parse(investEndDate);
					}
					else {
						dtInvestEndDate = null;
					}
					
				//Investment activate flag
				statusCode = Long.parseLong(StringUtility.getParameter("statusFlag", request));
				
				
				
				
				
				logger.info("***********************************************************"+'\n');
				logger.info("Investment Name: "+investName);
				logger.info("Start Date is: "+investStartDate);
				logger.info("investDesc is: "+investDesc);
				logger.info("dtInvestEndDate is: "+dtInvestEndDate);
				logger.info("sectionId is: "+sectionId);
				logger.info("***********************************************************"+'\n');
				
				
				
				/* SET HrInvestTypeMst related information  */
				
				//Investment name
				hrInvestTypeMst.setInvestName(investName);
				
				//Investment description
				hrInvestTypeMst.setInvestDesc(investDesc);
				
				//Investment section id
				HrItSectionMst hrItSectionMst = new HrItSectionMst();
				hrItSectionMst.setSectId(sectionId);
				hrInvestTypeMst.setHrItSectionMst(hrItSectionMst);
				
				//Investment start date
				hrInvestTypeMst.setStartDate(dtInvestStartDate);
				
				//Investment end date
				hrInvestTypeMst.setEndDate(dtInvestEndDate);
				
				//Investment activate flag
				hrInvestTypeMst.setActivateFlag(statusCode);
				
				
				
				//create XML file 
				String xmlFileId = FileUtility.voToXmlFileByXStream(hrInvestTypeMst);
		       	objectArgs.put("ajaxKey", xmlFileId);
		       	logger.info("XML file PATH:::"+xmlFileId);
				
				
				resultObject.setViewName("ajaxData");
				resultObject.setResultValue(objectArgs);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				
				
				//enable objects for GC
				hrItSectionMst = null;
				
				logger.info("moving out of voGen...");
			}catch( Exception e ){
				logger.info("Exception in VoGEN... InvestTypeMstVoGen@addMultipleInvestmentType()");
				logger.error("Error is: "+ e.getMessage());
				
				objectArgs.put("MESSAGECODE",3001);
				resultObject.setResultValue(objectArgs);			
				resultObject.setResultCode(-1);
				resultObject.setViewName("errorPage");		
				
			 return resultObject;
			}
		return resultObject;
	}//end method addMultipleInvestmenType
	
}//end class: InvestTypeMstVOGenImpl
