package com.tcs.sgv.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrPayOfficeMst;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;

public class AdminOrgPostDtlVogenImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantsBundle = ResourceBundle
			.getBundle("resources.Payroll");

	public final int DEPT_ID = Integer.parseInt(constantsBundle
			.getString("GAD"));

	public ResultObject submitAdminOrgPostDtlVogen(
			Map<String, Object> objectArgs) {
		long psrId = 0;

		String postName = "";
		String flag1 = "add";
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {

			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			String flag = StringUtility.getParameter("flag", request);
			logger.info("Flag is:-->>" + flag);
			//String lStrPostId = StringUtility.getParameter("postId", request);
			// Added By Varun
			
			
		
			
			/*if (locId == DEPT_ID || locId == 300024) {
				
				
				HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				logger.info("Inside If Condition GAD");
				long psrpostId = 0;

				psrId = request.getParameter("psr") != null ? Long
						.parseLong(StringUtility.getParameter("psr", request))
						: 0;				
				
				if (flag.equals(flag1)) {
					logger.info("Inside If");
					psrpostId = 0;// request.getParameter("psrpostId") != null ?
									// Long.parseLong(StringUtility.getParameter("psrpostId",
									// request)) : 0;
				} else {
					logger.info("Inside else");
					psrpostId = request.getParameter("psrpostId") != null ? Long
							.parseLong(StringUtility.getParameter("psrpostId",
									request))
							: 0;
				}

				logger.info("Psr Number is:-->>>>>" + psrId);
				
				logger.info("psrpostId Number is:-->>>>>" + psrpostId);
				hrPayPsrPostMpg.setPsrId(psrId);
				hrPayPsrPostMpg.setPsrPostId(psrpostId);
				
				hrPayPsrPostMpgList.add(hrPayPsrPostMpg);
				objectArgs.put("psrId", psrId);
				//objectArgs.put("hrPayPsrPostMpg", hrPayPsrPostMpg);
			}
			objectArgs.put("hrPayPsrPostMpgList", hrPayPsrPostMpgList);
			logger.info("=====> hrPayPsrPostMpgList.size() :: "+hrPayPsrPostMpgList.size()); */

			// Ended By Varun For Psr-Post Mpg

			
		
			/*
			 * String activePostFlag =
			 * StringUtility.getParameter("radActiveBtn", request); // commented
			 * by divya if(activePostFlag.equals("")==false) {
			 * if(activePostFlag.equalsIgnoreCase("1")==true)
			 * {orgPostMst.setActivateFlag(1);} else
			 * {orgPostMst.setActivateFlag(2);} } else {
			 * orgPostMst.setActivateFlag(2); }
			 */
					
		
			
			//Added By Javed
			long officeCmb = request.getParameter("officeCmb") != null ? Long.parseLong(StringUtility.getParameter("officeCmb",request)): 0;  
			logger.info("==> The officeCmb is:-" + officeCmb);
			objectArgs.put("officeCmb",officeCmb);
			
//			long schemecmb = request.getParameter("schemecmb") != null ? Long.parseLong(StringUtility.getParameter("schemecmb",request)): 0; 
//			logger.info("==> The schemecmb is:-" + schemecmb);
//			objectArgs.put("schemecmb",schemecmb);
			
			long billCmb = request.getParameter("billCmb") != null ? Long.parseLong(StringUtility.getParameter("billCmb",request)): 0;
			objectArgs.put("billCmb",billCmb);
			
			long noofpost = request.getParameter("postNumber") != null ? Long.parseLong(StringUtility.getParameter("postNumber",request)): 0; 
			logger.info("==> The noofpost is:-" + noofpost);
			objectArgs.put("noofpost",noofpost);
			
			logger.info("====> officeCmb in VOgen :: "+officeCmb);
			logger.info("====> billCmb in voGen:: "+billCmb);
			logger.info("====> noofpost in VoGen :: "+noofpost);
			//Added By Javed
			String designationCmb = request.getParameter("designationCmb") != null ? StringUtility.getParameter("designationCmb",request): "";
			objectArgs.put("designationCmb",designationCmb);
			
			long orderCmb =0;
			if(request.getParameter("orderCmb") != null && !request.getParameter("orderCmb").equals("") )
			{
			orderCmb = request.getParameter("orderCmb") != null ? Long.parseLong(StringUtility.getParameter("orderCmb",request)): 0;
			objectArgs.put("orderCmb",orderCmb);
			}
			// added by khushal
			String orderDatelStr = StringUtility.getParameter("OrderDate",request)!=null?StringUtility.getParameter("OrderDate",request):"";
			String remarksStr = StringUtility.getParameter("Remarks",request)!=null?StringUtility.getParameter("Remarks",request):"";
			String tempTypPostStr = StringUtility.getParameter("purposeCmbBox",request)!=null?StringUtility.getParameter("purposeCmbBox",request):"";
			long postTypecmb = request.getParameter("postTypeCmbBox") != null ? Long.parseLong(StringUtility.getParameter("postTypeCmbBox",request)): 0;
			String oldOrderDate = StringUtility.getParameter("OriginalOrderDate",request)!=null?StringUtility.getParameter("OriginalOrderDate",request):"";
			String oldOrderCmb = StringUtility.getParameter("OriginalorderCmb",request)!=null?StringUtility.getParameter("OriginalorderCmb",request):"";
			String newDatelStr = StringUtility.getParameter("RenewalOrderDate",request)!=null?StringUtility.getParameter("RenewalOrderDate",request):"";
			String newOrderCmb = StringUtility.getParameter("RenewalorderCmb",request)!=null?StringUtility.getParameter("RenewalorderCmb",request):"";
			
			//Added by khushal
			long Permenant = request.getParameter("Permenant") != null ? Long.parseLong(StringUtility.getParameter("Permenant",request)): 0;
			objectArgs.put("Permenant",Permenant);
			
			//Ended
			
			objectArgs.put("orderDate",orderDatelStr);
			objectArgs.put("remarks",remarksStr);
			objectArgs.put("postType",postTypecmb);
			objectArgs.put("tempTypePost",tempTypPostStr);
			objectArgs.put("oldOrderDate",oldOrderDate);
			objectArgs.put("oldOrderCmb",oldOrderCmb);
			objectArgs.put("newDate",newDatelStr);
			objectArgs.put("newOrderCmb",newOrderCmb);
			
			
			logger.info("postTypecmb-voGen"+postTypecmb);
			logger.info("oldOrderDate-VO"+oldOrderDate);
			logger.info("oldOrderCmb-vo"+oldOrderCmb);
			logger.info("newDatelStr-vo"+newDatelStr);
			logger.info("newOrderCmb-vo"+newOrderCmb);
			logger.info("remarks-voGen"+remarksStr);
			
             // ended by khushal
			String startDatelStr = StringUtility.getParameter("startDate",request)!=null?StringUtility.getParameter("startDate",request):"";
			String endDatelStr = StringUtility.getParameter("endDate", request)!=null?StringUtility.getParameter("endDate", request):"";
			String tempEndDatelStr = StringUtility.getParameter("tempEndDate", request)!=null?StringUtility.getParameter("tempEndDate", request):"";
			objectArgs.put("startDate",startDatelStr);
			objectArgs.put("endDate",endDatelStr);
			objectArgs.put("tempEndDate",tempEndDatelStr);
			
			
			logger.info("====> designationCmb vo:: "+designationCmb);
			logger.info("====> orderCmbvo :: "+orderCmb);
			logger.info("====> startDatelStrvo :: "+startDatelStr);
			logger.info("====> endDatelStrvo :: "+endDatelStr);


			objectArgs.put("flag", flag);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.error(
					"Admin Screen Creating Admin Screen Post Error In VOgen",
					ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}

	public ResultObject editAdminOrgPostDtlVogen(Map<String, Object> objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			Long reqId = Long.valueOf(StringUtility.getParameter("reqId",
					request));

			// Added By Varun
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginDetailsMap.get(
					"locationId").toString());		
			if (locId == DEPT_ID || locId == 300024) {
				HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				logger.info("Inside If Condition GAD");
				long psrId = request.getParameter("psr") != null ? Long
						.parseLong(StringUtility.getParameter("psr", request))
						: 0;

				logger.info("Psr Number is:-->>>>>" + psrId);

				hrPayPsrPostMpg.setPsrId(psrId);
				objectArgs.put("hrPayPsrPostMpg", hrPayPsrPostMpg);
			}

			// Ended By Varun For Psr-Post Mpg

			objectArgs.put("reqId", reqId);
			objectArgs.put("flag", "edit");
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger
					.error(
							"Admin Screen Editing Admin Screen Post Error In VOgen",
							ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}

	public ResultObject deleteAdminOrgPostDtlVogen(
			Map<String, Object> objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			String[] deleteScreenIdArray = null;
			try {
				deleteScreenIdArray = (String[]) StringUtility
						.getParameterValues("deletedata", request);
			} catch (Exception e) {
			}
			objectArgs.put("deletedata", deleteScreenIdArray);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.error(
					"Admin Screen Deleteing Admin Screen Post Error In VOgen",
					ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
}
