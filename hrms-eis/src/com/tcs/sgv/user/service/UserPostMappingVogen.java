package com.tcs.sgv.user.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class UserPostMappingVogen extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());

	public ResultObject getUserPostMappingData(Map objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{logger.info("inside getUserPostMappingData ");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long lUserPostRltId = (request.getParameter("userPostRltId") != null && !request.getParameter("userPostRltId").equals("")) ? Long.parseLong(StringUtility.getParameter("userPostRltId",request)):0;
			long lUserId = (request.getParameter("userid") != null && !request.getParameter("userid").equals("")) ? Long.parseLong(StringUtility.getParameter("userid",request)):0;
			logger.info("User ID is:--------->"+lUserId);
			objectArgs.put("lUserPostRltId", lUserPostRltId);
		
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setResultValue(objectArgs);
			logger.info("Exception Ocuures...getUserPostMappingData In UserPostMappingVogen");
		}
		return resObj;
	}

	public ResultObject getUserPostMappingVOGen(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

		try 
		{
			OrgUserpostRlt orgUserpostRlt = new OrgUserpostRlt();
			String selDesg = StringUtility.getParameter("selDesignation", request) != null ? StringUtility.getParameter("selDesignation", request) : "";
			logger.info("Designation Name---------->>"+selDesg);
			String selPost = StringUtility.getParameter("selPost", request) != null ? StringUtility.getParameter("selPost", request) : "";
			//String activateFlag = StringUtility.getParameter("flag", request) != null ? StringUtility.getParameter("flag", request) : "";
			String dtStartDate = StringUtility.getParameter("dtStartDate", request) != null ? StringUtility.getParameter("dtStartDate", request) : "";
			String dtEndDate = StringUtility.getParameter("dtEndDate", request) != null ? StringUtility.getParameter("dtEndDate", request) : ""; 
			String strOfficeType = request.getParameter("selOfficeType") != null ? StringUtility.getParameter("selOfficeType", request) : "";
			String strOfficeName = request.getParameter("selOfficeName") != null ? StringUtility.getParameter("selOfficeName", request) : "";
			String lUserPostRltId = (StringUtility.getParameter("hdnUserId", request) != null && !StringUtility.getParameter("hdnUserId", request).trim().equals("")) ? StringUtility.getParameter("hdnUserId", request) : "0";
			logger.info("The Check id is:-"+lUserPostRltId);
			long checkId = Long.parseLong(lUserPostRltId);
			logger.info("checking id is:---->"+checkId);
			if (checkId == 0)
			{
			
				orgUserpostRlt.setActivateFlag(1l);
				
			}
			else{
				logger.info("inside else");
				String ActivateFlag = StringUtility.getParameter("flag", request) != null ? StringUtility.getParameter("flag", request) : "";
				logger.info("Activate Flag is -------->"+ActivateFlag);
				
				long Activateflag = Long.parseLong(ActivateFlag);
				logger.info("Activate flag is -------->"+Activateflag);
				orgUserpostRlt.setActivateFlag(Activateflag);
				
			}
				
			
			
			String userIdStr;
			if(StringUtility.convertToLong(lUserPostRltId)==0)
				 userIdStr = StringUtility.getParameter("selUserName", request) != null ? StringUtility.getParameter("selUserName", request) : "";
			else
			{
				logger.info("Inside else-------------->");
				userIdStr = StringUtility.getParameter("hiddenUserId", request) != null ? StringUtility.getParameter("hiddenUserId", request) : "";
				logger.info("The hiddenUserId id is----->"+userIdStr);
				
			}
			long SelecteduserId = StringUtility.convertToLong(userIdStr);
			OrgUserMst orgUserMst = new OrgUserMst();
			orgUserMst.setUserId(SelecteduserId);
			orgUserpostRlt.setOrgUserMst(orgUserMst);
			OrgPostMst orgPostMst = new OrgPostMst();

			long postId = userIdStr != null ? Long.parseLong(selPost) : 0;
			orgPostMst.setPostId(postId) ;
			orgUserpostRlt.setOrgPostMstByPostId(orgPostMst);

			orgUserpostRlt.setEndDate(StringUtility.convertStringToDate(dtEndDate));
			orgUserpostRlt.setStartDate(StringUtility.convertStringToDate(dtStartDate));

			objectArgs.put("selDesg", selDesg);
			objectArgs.put("strOfficeType", strOfficeType);
			objectArgs.put("strOfficeName", strOfficeName);
			objectArgs.put("lUserPostRltId", lUserPostRltId);
			objectArgs.put("orgUserpostRlt", orgUserpostRlt);
			objectArgs.put("postId", selPost);//added by mansih
			objectArgs.put("userId", userIdStr);//added by manish
		
			

			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);

		}	
		catch (Exception e) 
		{
			e.printStackTrace();
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.info("Exception Ocuures...getUserPostMappingVOGen In UserPostMappingVogen");
		}
		return objRes;
	}

	public ResultObject generateOfficeNameMap(Map<String, Comparable> objServiceArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
				try 
				{
					if (objRes == null || objServiceArgs == null)
					{
						objRes.setResultCode(-1);
						return objRes;
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				String strFlag= request.getParameter("flag") != null ? StringUtility.getParameter("flag", request) : "";
				String strRecType= request.getParameter("recType") != null ? StringUtility.getParameter("recType", request) : "";
				String strOfficeNameCode = request.getParameter("departmentCode") != null ? StringUtility.getParameter("departmentCode", request) : "";
				String strLocationName = request.getParameter("locationFieldName") != null ? StringUtility.getParameter("locationFieldName", request) : "";				
				String strLocationCode = request.getParameter("locationCode") != null ? StringUtility.getParameter("locationCode", request) : "";
				String strDesignationCode = request.getParameter("designationCode") != null ? StringUtility.getParameter("designationCode", request) : "";
				String strPostFieldName = request.getParameter("postFieldName") != null ? StringUtility.getParameter("postFieldName", request) : "";
				String strPostCode = request.getParameter("PostCode") != null ? StringUtility.getParameter("PostCode", request) : "";
				
				Date dtStartDate=null;
				
				if(request.getParameter("StartDate")!=null)
				{
					dtStartDate=StringUtility.convertStringToDate(StringUtility.getParameter("StartDate",request));
				}
				
				
				objServiceArgs.put("dtStartDate", dtStartDate);
				objServiceArgs.put("strRecType", strRecType);
				objServiceArgs.put("strPostCode", strPostCode);
				objServiceArgs.put("strLocationCode", strLocationCode);
				objServiceArgs.put("strOfficeNameCode", strOfficeNameCode);
				objServiceArgs.put("strFlag", strFlag);
				objServiceArgs.put("strLocationName", strLocationName);
				objServiceArgs.put("strDesignationCode", strDesignationCode);
				objServiceArgs.put("strPostFieldName", strPostFieldName);
				
				objRes.setResultValue(objServiceArgs);
		}		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objRes;
	}

}
