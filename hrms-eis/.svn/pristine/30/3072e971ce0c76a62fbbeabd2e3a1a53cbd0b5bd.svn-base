package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;

public class OrderMasterVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject generateMapForOrderMaster(Map objectArgs) 
	{
		try{
			

			/// Code for attachment	
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				//ResultObject resultObj = serv.executeService("FILE_UPLOAD_VOGEN",objectArgs);
			ResultObject resultObj = new ResultObject(ErrorConstants.SUCCESS);
				Map resultMap = (Map) resultObj.getResultValue();			
				
				///////////////////

		
		logger.info("OrderMasterVOGen generateMapForOrderMaster Called");		
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
		HrPayOrderMst hrPayOrderMst = new HrPayOrderMst();
				
		String editMode = StringUtility.getParameter("edit",request);
		 
		Date orderDate = null;		//Order Date.
		// Added By Urvin shah.
		Date endDate = null;		//Order End Date.
		// Ended By Urvin shah.
		String orderName;			// Name of the Order.
		String locationCode;		// Location Code for the Order.
		
		if(editMode.equalsIgnoreCase("Y")) 
		{
			long orderId = 0;		//Primary Key Field Value
			logger.info("Update Mode"); 	
			String OrderId = StringUtility.getParameter("orderId", request);
			if( OrderId!=null && !OrderId.equals("")){
				 orderId = Long.parseLong(OrderId);
			}
			logger.info("Order Id is:-"+orderId);
			hrPayOrderMst.setOrderId(orderId);			
		}    			
		orderName = StringUtility.getParameter("orderName", request);
		locationCode = StringUtility.getParameter("cmbDept", request);
				
		if(locationCode==null)
			locationCode=" ";
		
		hrPayOrderMst.setLocationCode(locationCode);
		hrPayOrderMst.setOrderName(orderName);	
		logger.info(" ****************************OrderMasterVO " + hrPayOrderMst);		
		if(StringUtility.getParameter("txtStartDate", request)!=null && !StringUtility.getParameter("txtStartDate", request).equals(""))
		{
			orderDate = StringUtility.convertStringToDate(StringUtility.getParameter("txtStartDate", request));
		}
		
		if(StringUtility.getParameter("txtEndDate", request)!=null && !StringUtility.getParameter("txtEndDate", request).equals(""))
		{
			endDate = StringUtility.convertStringToDate(StringUtility.getParameter("txtEndDate", request));
		}
			
		
		



		
		hrPayOrderMst.setOrderDate(orderDate);
		hrPayOrderMst.setEndDate(endDate);
		
		
		objectArgs.put("edit",editMode);
		objectArgs.put("orderMst",hrPayOrderMst);
		resultMap = objectArgs;
		//retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		retObj.setResultValue(resultMap);
		
		logger.info("U are out of VOGen");
		
		
		
		}
		catch(PropertyValueException pe)
		{
			logger.info("Exception in generateMapForOrderMaster-----"+pe);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(pe);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			logger.error("Error is: "+ pe.getMessage());

		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.info("Exception in generateMapForOrderMaster-----"+e);
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
	//Added By Mrugesh
	public ResultObject multipleAddOrderData(Map objectArgs)
	{
		logger.info("getExtCommMembersMultiAdd is  called");
		logger.info("VOGEN getExtCommMembersMultiAdd Starts"); 		
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			//SessionFactory sessionFactory = serv.getSessionFactory();
	        Map   baseLoginMap = (Map) objectArgs.get("baseLoginMap");
			
	        long langId = Long.parseLong(baseLoginMap.get("langId").toString());
	        String orderName;	
			String locationCode;
			Date orderDate = null;	
			Date endDate = null;
			
			orderName = StringUtility.getParameter("orderName", request);
			
			
			HrPayOrderMst hrPayOrderMst = new HrPayOrderMst();
			locationCode = StringUtility.getParameter("cmbDept", request);
			
			if(locationCode==null)
				locationCode=" ";
			
			if(StringUtility.getParameter("txtStartDate", request)!=null && !StringUtility.getParameter("txtStartDate", request).equals(""))
			{
				orderDate = StringUtility.convertStringToDate(StringUtility.getParameter("txtStartDate", request));
			}
			
			if(StringUtility.getParameter("txtEndDate", request)!=null && !StringUtility.getParameter("txtEndDate", request).equals(""))
			{
				endDate = StringUtility.convertStringToDate(StringUtility.getParameter("txtEndDate", request));
			}
			
			hrPayOrderMst.setLocationCode(locationCode);
			hrPayOrderMst.setEndDate(endDate);
			hrPayOrderMst.setOrderDate(orderDate);
			hrPayOrderMst.setOrderName(orderName);
			
			
			String xmlFileId = FileUtility.voToXmlFileByXStream(hrPayOrderMst);
	       	objectArgs.put("ajaxKey", xmlFileId);
	       	
	       	logger.info("XML file PATH:::"+xmlFileId);
	       	 
	       	objRes.setViewName("ajaxData");
	       	objRes.setResultValue(objectArgs);
			
		}catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");
			logger.error("Exception occurred in <CommitteeFormationVOGEN>::<getExtCommMembersMultiAdd>",e);
		}
		return objRes;
	}
	//Ended By Mrugesh
}
