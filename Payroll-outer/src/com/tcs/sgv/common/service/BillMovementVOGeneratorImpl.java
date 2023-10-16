/**
 * 
 */
package com.tcs.sgv.common.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * BillMovementVOGeneratorImpl 
 * This class is used to generate VO for TrnBillMvmnt as per movement occurs with the bill.
 * 
 * Date of Creation : 6th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * ===================== 
 * Hiral 23-Oct-2007 For making changes for code formating
 */
public class BillMovementVOGeneratorImpl
	extends ServiceImpl 
	implements VOGeneratorService
{
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject generateMap(Map p_objServiceArgs) 
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("\n$$$$ Inside BillMovementVOGeneratorImpl.generateMap Generation $$$$$ ");
			String billDate="";
			HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
			HttpSession session = request.getSession();
			
			ResourceBundle lObjRsrcBndle =ResourceBundle.getBundle("resources/billproc/BillprocConstants");
			
/*			LoginDetails objLoginDetails =(LoginDetails)session.getAttribute("loginDetails");
			CmnLocationMst lObjLocation = new CmnLocationMst();						
			lObjLocation = objLoginDetails.getLocation();
*/			
			Long DbId=Long.parseLong(SessionHelper.getDbId(p_objServiceArgs).toString());
			String lStrLocCode = SessionHelper.getLocationCode(p_objServiceArgs);
			
			
			
			Long createdUserId=SessionHelper.getUserId(request);
			Long createdPostId=SessionHelper.getPostId(p_objServiceArgs);
			Long updatedUserId=SessionHelper.getUserId(request);
			Long updatedPostId=SessionHelper.getPostId(p_objServiceArgs);
		
			String movemntId = "0";
			String tokenNo = StringUtility.getParameter("txtTokenNo", request);
			String hrchyUserId = String.valueOf(createdUserId);
			String hrchyPostId = String.valueOf(createdPostId);
			
			String mvmntStatus = lObjRsrcBndle.getString("STATUS.BillInward");

			String receivedFlag = lObjRsrcBndle.getString("CMN.One");
			Long receivingUserId = Long.parseLong((session.getAttribute("userId").toString()));
			
			
			Date dtTmp=null;
			Date lDtCurDate = new java.util.Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");			
			if ( billDate == "" || billDate==null)
			{
				billDate = fmt.format(new Date(System.currentTimeMillis()));
//				dtTmp = new java.util.Date();
			}
			else
			{
				dtTmp = new SimpleDateFormat("dd/MM/yyyy").parse(billDate);				
				billDate = fmt.format(dtTmp);				
			}
			
			TrnBillMvmnt lObjBillMvmnt = new TrnBillMvmnt();
			
			lObjBillMvmnt.setMovemntId(Long.parseLong(movemntId));
		
			logger.info(" Settting token --------- " + tokenNo);
			lObjBillMvmnt.setStatusUpdtUserid(Long.parseLong(hrchyUserId));
			lObjBillMvmnt.setStatusUpdtDate(dtTmp);
			lObjBillMvmnt.setMvmntStatus(mvmntStatus);
			lObjBillMvmnt.setReceivedFlag(Short.parseShort(receivedFlag));
			lObjBillMvmnt.setReceivingUserId(receivingUserId);
			lObjBillMvmnt.setReceivedDate(new Date(System.currentTimeMillis()));
			lObjBillMvmnt.setCreatedUserId(createdUserId);
			lObjBillMvmnt.setCreatedPostId(createdPostId);
			lObjBillMvmnt.setCreatedDate(lDtCurDate);
			lObjBillMvmnt.setUpdatedUserId(updatedUserId);
			lObjBillMvmnt.setUpdatedPostId(updatedPostId);
			lObjBillMvmnt.setUpdatedDate(dtTmp);
			lObjBillMvmnt.setLocationCode(lStrLocCode);
			lObjBillMvmnt.setDbId(DbId);
			lObjBillMvmnt.setStatusUpdtPostid(Long.parseLong(hrchyPostId));
			lObjBillMvmnt.setStatusUpdtDate(new java.util.Date(System.currentTimeMillis()));
			
			p_objServiceArgs.put("BillMovementVO", lObjBillMvmnt);
						
			retObj.setResultValue(p_objServiceArgs);			
			logger.info("$$$$$$ Result Object in Bill Movement VO Generetor : ===============  : " +retObj);			
		}
		catch(Exception e)
		{
			retObj.setResultValue(null);
			retObj.setThrowable(e);
			retObj.setResultCode(ErrorConstants.ERROR);
			retObj.setViewName("errorPage");
			e.printStackTrace();	
		}		
		return retObj;
	}
}
