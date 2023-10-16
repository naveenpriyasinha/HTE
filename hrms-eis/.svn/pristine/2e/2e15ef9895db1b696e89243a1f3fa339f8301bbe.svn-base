package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ScaleMasterDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
//import com.tcs.sgv.eis.valueobject.ScaleCommissionMpg;



/**
 * This class is to Set the VO to fetch and
 * insert the data into Scale Master table
 */


public class ScaleMasterVOGen extends ServiceImpl 
{
	
	Log logger = LogFactory.getLog(getClass());
	//static int i;
	
	
	public ResultObject generateScaleMaster(Map objServiceArgs) 
	{
		logger.info("Inside ScaleMAsterVOGen------");
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
			
			HrEisScaleMst scaleMasterVO = new HrEisScaleMst();

			int scaleStartAmt;
			int scaleIncAmt;
			int scaleEndAmt;
			String scaleEffFromDate=null;
			String scaleEffToDate=null;
			Date scaleEFD=null;
			Date scaleETD=null;
			String scaleIncDat=null;
			Date incrementDate=null;
			//Added by Mrugesh for Bigger scale value
			int higherIncrAmt;
			int higherEndAmt;
			//Ended by Mrugesh
			long gradePay=0;
			HrPayCommissionMst hrPayCommissionMst = new HrPayCommissionMst();
			
			long payCommission = Long.parseLong( request.getParameter("payCommissionCmbBx") );
			
			logger.info("payCommission is************"+payCommission);
			
			//set pay commission lookup id
			hrPayCommissionMst.setId(payCommission);
			//set pay band lookup id
			
			
			scaleStartAmt=Integer.parseInt(((StringUtility.getParameter("scaleStartAmt",request)!=null&&!(StringUtility.getParameter("scaleStartAmt",request).equals(""))?(StringUtility.getParameter("scaleStartAmt",request)):0).toString()));
			scaleIncAmt=(StringUtility.getParameter("scaleIncrAmt",request)!=null&&!(StringUtility.getParameter("scaleIncrAmt",request).equals(""))?Integer.parseInt(StringUtility.getParameter("scaleIncrAmt",request)):0);
			scaleEndAmt=(StringUtility.getParameter("scaleEndAmt",request)!=null&&!(StringUtility.getParameter("scaleEndAmt",request).equals(""))?Integer.parseInt(StringUtility.getParameter("scaleEndAmt",request)):0);
			scaleIncDat =(StringUtility.getParameter("scaleIncDat",request)!=null&&!(StringUtility.getParameter("scaleIncDat",request).equals(""))?(StringUtility.getParameter("scaleIncDat",request)):" ").toString();
			scaleEffFromDate =(StringUtility.getParameter("scaleEFD",request)!=null&&!(StringUtility.getParameter("scaleEFD",request).equals(""))?(StringUtility.getParameter("scaleEFD",request)):" ").toString();
			
			logger.info("scaleEffFromDate is************"+scaleEffFromDate);
			logger.info("scaleIncDat is************"+scaleIncDat);
			logger.info("Date----"+scaleEffFromDate);
			logger.info("scaleIncDat::::::::::::::::::::::::::::::::: " +scaleIncDat);
			//Added by Mrugesh for Bigger scale vale
			higherIncrAmt=(StringUtility.getParameter("scaleHigherIncrementAmt",request)!=null&&!(StringUtility.getParameter("scaleHigherIncrementAmt",request).equals(""))?Integer.parseInt(StringUtility.getParameter("scaleHigherIncrementAmt",request)):0);
			higherEndAmt=(StringUtility.getParameter("scaleHigherEndAmt",request)!=null&&!(StringUtility.getParameter("scaleHigherEndAmt",request).equals(""))?Integer.parseInt(StringUtility.getParameter("scaleHigherEndAmt",request)):0);
			int secondIncrAmt = (StringUtility.getParameter("scaleSecondHigherIncrementAmt",request)!=null&&!(StringUtility.getParameter("scaleSecondHigherIncrementAmt",request).equals(""))?Integer.parseInt(StringUtility.getParameter("scaleSecondHigherIncrementAmt",request)):0);
			int secondEndAmt = (StringUtility.getParameter("scaleSecondHigherEndAmt",request)!=null&&!(StringUtility.getParameter("scaleSecondHigherEndAmt",request).equals(""))?Integer.parseInt(StringUtility.getParameter("scaleSecondHigherEndAmt",request)):0);
			
			int thirdIncrAmt = (StringUtility.getParameter("scaleThirdHigherIncrementAmt",request)!=null&&!(StringUtility.getParameter("scaleThirdHigherIncrementAmt",request).equals(""))?Integer.parseInt(StringUtility.getParameter("scaleThirdHigherIncrementAmt",request)):0);
			int thirdEndAmt = (StringUtility.getParameter("scaleThirdHigherEndAmt",request)!=null&&!(StringUtility.getParameter("scaleThirdHigherEndAmt",request).equals(""))?Integer.parseInt(StringUtility.getParameter("scaleThirdHigherEndAmt",request)):0);
			gradePay=(StringUtility.getParameter("gradePay",request)!=null&&!(StringUtility.getParameter("gradePay",request).equals(""))?Integer.parseInt(StringUtility.getParameter("gradePay",request)):0);
			logger.info("gradePay gradePay :"+gradePay);
			
			//Ended
			
			//Added by Muneendra for payScaleType
			CmnLookupMst cmnLookupMst = new CmnLookupMst();
			long payScaleType = Long.parseLong( request.getParameter("payScaleTypeCmbBx") );
			//set pay scale type lookup id
			
			logger.info("payscale type is ************" + payScaleType);
			cmnLookupMst.setLookupId(payScaleType);
			//ended by Muneendra for payScaleType
			
			if(scaleIncDat!=null && !scaleIncDat.equals(" "))
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				incrementDate = sdf.parse(scaleIncDat);
				logger.info("incrementDate::::::::::::::::::::::: " + incrementDate);
			}
			
			
			if(scaleEffFromDate!=null && !scaleEffFromDate.equals(" "))
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				scaleEFD = sdf.parse(scaleEffFromDate);
			}
			
	        scaleEffToDate =(StringUtility.getParameter("scaleETD",request)!=null&&!(StringUtility.getParameter("scaleETD",request).equals(""))?(StringUtility.getParameter("scaleETD",request)):" ").toString();
	        if(scaleEffToDate!=null && !scaleEffToDate.equals(" "))
	        {
	        	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	        	scaleETD=sdf1.parse(scaleEffToDate);
	        }
			
	        
			String editMode = StringUtility.getParameter("edit",request);	
			long scaleId=0;
			
			if(editMode.equalsIgnoreCase("N")) 
			{		
				objServiceArgs.put("edit","N");
			}
			else
			{
	
				logger.info("going to update mode*************");
				
				
				String scaleid=(StringUtility.getParameter("scaleid", request)!=null&&!(StringUtility.getParameter("scaleid", request).equals(""))?(StringUtility.getParameter("scaleid", request)):0).toString();
				logger.info("The scaleId from request is------->"+scaleid);
	        	if(scaleid!=null)
	        	{
	        		scaleId=Long.parseLong(scaleid);
	        	}
	        	scaleMasterVO.setScaleId(scaleId);
	        	objServiceArgs.put("edit","Y");
	        	
			}
			
			scaleMasterVO.setHrPayCommissionMst(hrPayCommissionMst);
			
			scaleMasterVO.setScaleStartAmt(scaleStartAmt);
			scaleMasterVO.setScaleIncrAmt(scaleIncAmt);
			scaleMasterVO.setScaleEndAmt(scaleEndAmt);
			scaleMasterVO.setScaleEffFromDt(scaleEFD);
			scaleMasterVO.setScaleEffToDt(scaleETD);
			scaleMasterVO.setIncrementDate(incrementDate);
			//Added by Mrugesh for Bigger scale value
			scaleMasterVO.setScaleHigherIncrAmt(higherIncrAmt);
			scaleMasterVO.setScaleHigherEndAmt(higherEndAmt);
			scaleMasterVO.setScaleSecondHigherIncrAmt(secondIncrAmt);
			scaleMasterVO.setScaleSecondHigherEndAmt(secondIncrAmt);
			scaleMasterVO.setScaleThirdHigherIncrAmt(thirdIncrAmt);
			scaleMasterVO.setScaleThirdHigherEndAmt(thirdEndAmt);
			//Ended by Mrugesh
			scaleMasterVO.setScaleGradePay(gradePay);
			scaleMasterVO.setPayScaleType(cmnLookupMst); 
			logger.info("scaleMasterVO getScaleGradePay :"+scaleMasterVO.getScaleGradePay());
			logger.info("Scale VO :"+scaleMasterVO.getScaleStartAmt());
			
			
			

			/** ScaleCommissionMpg Details */
			/* fetch pay band and pay commission */
/*
			String payBand = null;
			String payCommission = null;
				

			payBand = request.getParameter("payCommissionCmbBx");
			if( payBand == null || payBand.trim().equals(""))
			{
				payBand = "-1";
			}
			payCommission = request.getParameter("payBandCmbBx");
			if( payCommission == null || payCommission.trim().equals(""))
			{
				payCommission="-1";
			}*/

				
			
			logger.info("pay commission in vogen is: "+payCommission);				

			/*objServiceArgs.put("payBand",payBand);
			objServiceArgs.put("payCommission",payCommission);*/
			objServiceArgs.put("scaleMstVO",scaleMasterVO);
			objServiceArgs.put("payCommission",payCommission);
			objServiceArgs.put("payScaleType",payScaleType);

			retObj.setResultValue(objServiceArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			
		}catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}
	
	public ResultObject generateMap(Map arg0) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	/**
	 * 
	 * @return scaleList basis paycommission && payband
	 */
/*
	public ResultObject getScales(Map objectArgs)
	{
		logger.info("inside getScales");
			ResultObject resultObject = new ResultObject();
		try{	
			ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
			ScaleMasterDAOImpl scaleMasterDAOImpl = new ScaleMasterDAOImpl(HrEisScaleMst.class,serviceLocator.getSessionFactory());
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			String payCommission = request.getParameter("payCommission");
			String payBand = request.getParameter("payBand");
			
			logger.info("Pay Commission is: " + payCommission);
			logger.info("Pay Band is: " + payBand);
	
				List scaleList = scaleMasterDAOImpl.getScaleList(payCommission, payBand);
				logger.info("scaleList size is: " + scaleList.size());
	
				StringBuffer scaleNameBf = new StringBuffer();
	
				
	            if(scaleList==null || scaleList.size()==0){
	            	
	            	for( int i=0; i<scaleList.size(); i++)
	            	{
	            		scaleNameBf.append("<scaleList>");
	            		scaleNameBf.append("<scaleId>").append(scaleList.get(0)).append("</scaleId>");
	            		scaleNameBf.append("<scaleStartAmt>").append(scaleList.get(0)).append("</scaleStartAmt>");
	            		scaleNameBf.append("<scaleIncAmt>").append(scaleList.get(0)).append("</scaleIncAmt>");
	            		scaleNameBf.append("<scaleEndAmt>").append(scaleList.get(0)).append("</scaleEndAmt>");
	            		scaleNameBf.append("<scaleHigherIncrAmt>").append(scaleList.get(0)).append("</scaleHigherIncrAmt>");
	            		scaleNameBf.append("<scaleHigherEndAmt>").append(scaleList.get(0)).append("</scaleHigherEndAmt>");
	            		scaleNameBf.append("</scaleList>");
	            	}//end for
	
	            }//end if
	            
	            
	            
	            
	            String scaleNameMapping = new AjaxXmlBuilder().addItem( "ajax_key", scaleNameBf.toString() ).toString();
		        
	            
	            objectArgs.put("ajaxKey", scaleNameMapping);
	            
		        resultObject.setResultCode(ErrorConstants.SUCCESS); 
		        resultObject.setResultValue(objectArgs);
		        resultObject.setViewName("ajaxData");    		
	
		        return resultObject;
		}catch(Exception e){
			logger.info("Exception :"+e);
			logger.error("Error is: "+ e.getMessage());
			return resultObject;
		}
		
	}//end method
*/
	
}//end class
