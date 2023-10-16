/**
 * 
 */
package com.tcs.sgv.billproc.common.service;

import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;


/** RemarksVOGeneratorImpl
 *  This class is used to generate the VO for remarks that are entered by end-user.
 *  This class takes the remarks from end-user and sets other details accordingly to generate VO for that remarks. *  
 *   
 * 	Date of Creation : 12th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *  
 */
public class RemarksVOGeneratorImpl
		extends ServiceImpl 
		implements VOGeneratorService
{
		Log logger = LogFactory.getLog(getClass());
		ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
		
		/**
		 * Method to generate Value Object for 'trn_bill_remarks'
		 * @param  Map : p_objServiceArgs
		 * 
		 * @return ResultObject
		 */
		public ResultObject generateMap(Map p_objServiceArgs) 
		{				
			ResultObject retObj = new ResultObject();
			
			HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
			Long DbId=Long.parseLong(SessionHelper.getDbId(p_objServiceArgs).toString());
			String lStrLocCode = SessionHelper.getLocationCode(p_objServiceArgs);
			Long UserId=Long.parseLong(SessionHelper.getUserId(request).toString());
			Long postId=Long.parseLong(SessionHelper.getPostId(p_objServiceArgs).toString());			
			
			try
			{
				String remarks = StringUtility.getParameter("txtareaRemarks",request);
				Long lLngLineItemNo = Long.parseLong(lObjRsrcBndle.getString("CMN.One"));
				String lStrRmrksFlag = lObjRsrcBndle.getString("CMN.MyRemarks");

				Date billDate = new Date(System.currentTimeMillis());				
				TrnBillRemarks rbr = new TrnBillRemarks();
								
				rbr.setUserId(UserId);
				rbr.setPostId(postId);
				rbr.setRemarks(remarks);
				rbr.setLineItemNo(lLngLineItemNo);
				rbr.setRmrksFlag(lStrRmrksFlag);
				rbr.setCreatedUserId(UserId);
				rbr.setCreatedPostId(postId);
				rbr.setUpdatedPostId(postId);
				rbr.setCreatedDt(billDate);
				rbr.setUpdatedUserId(UserId);
				rbr.setUpdatedDate(billDate);
				rbr.setLocationCode(lStrLocCode);
				rbr.setDbId(DbId);
				
				p_objServiceArgs.put("TrnBillRemarksVO", rbr);
			
				retObj.setResultCode(ErrorConstants.SUCCESS);
				retObj.setResultValue(p_objServiceArgs);				
			}	
			catch(Exception e)
			{
				retObj.setResultValue(null);
				retObj.setThrowable(e);
				retObj.setResultCode(ErrorConstants.ERROR);
				retObj.setViewName("errorPage");
				logger.error(" Error in generateMap of RemarksVOGeneratorImpl" + e,e);
			}	
			return retObj;
  	  }
}
