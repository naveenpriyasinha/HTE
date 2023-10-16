package com.tcs.sgv.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmployeeEntryVOGen extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject basicInfo(Map objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			long UserId = (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) ? Long.parseLong(StringUtility.getParameter("userId",request)):0;
			logger.info("User Id is -------******--------"+UserId);
			objectArgs.put("userId", UserId);
			
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resObj;
	}
	
	
	
	public ResultObject saveEmpInfo (Map objectArgs)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			
			
			logger.info("Inside SaveEmpInfo");
			
			OrgEmpMst orgEmpMstEng = new OrgEmpMst();
			OrgEmpMst orgEmpMstGuj = new OrgEmpMst();
			
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
			
			String userIdStr = StringUtility.getParameter("userId", request) != null ? StringUtility.getParameter("userId", request) : "";
			String prefix = StringUtility.getParameter("Salutation", request) != null ? StringUtility.getParameter("Salutation", request) : "";
			String empFName = StringUtility.getParameter("emp_first_name_eng", request) != null ? StringUtility.getParameter("emp_first_name_eng", request) : "";
			String empMName = StringUtility.getParameter("emp_middle_name_eng", request) != null ? StringUtility.getParameter("emp_middle_name_eng", request) : "";
			String empLName = StringUtility.getParameter("emp_last_name_eng", request) != null ? StringUtility.getParameter("emp_last_name_eng", request) : "";
			String empFNameGuj = StringUtility.getParameter("emp_first_name_guj", request) != null ? StringUtility.getParameter("emp_first_name_guj", request) : "";
			String empMNameGuj = StringUtility.getParameter("emp_middle_name_guj", request) != null ? StringUtility.getParameter("emp_middle_name_guj", request) : "";
			String empLNameGuj = StringUtility.getParameter("emp_last_name_guj", request) != null ? StringUtility.getParameter("emp_last_name_guj", request) : "";
			String gradeCode = StringUtility.getParameter("selClass", request) != null ? StringUtility.getParameter("selClass", request) : "";
			String dateOfBirth = StringUtility.getParameter("DOB", request) != null ? StringUtility.getParameter("DOB", request) : "";
			String dateOfJoin = StringUtility.getParameter("DOJ", request) != null ? StringUtility.getParameter("DOJ", request) : "";
			String dateOfRet = StringUtility.getParameter("DOR", request) != null ? StringUtility.getParameter("DOR", request) : "";
			
			long userId = userIdStr != null ? Long.parseLong(userIdStr) : 0;
			OrgUserMst orgUserMst = new OrgUserMst();
			orgUserMst.setUserId(userId);
			
			OrgGradeMst gradeMst = new OrgGradeMst();
			gradeMst.setGradeCode(gradeCode);
			
			//System.out.println("the grade mst is code is "+gradeCode);
			
			if(empFNameGuj!=null  && !empFNameGuj.equals(""))
				orgEmpMstGuj.setEmpFname(empFNameGuj);
			if(empMNameGuj!=null && !empMNameGuj.equals(""))
				orgEmpMstGuj.setEmpMname(empMNameGuj);
			if(empLNameGuj!=null && !empLNameGuj.equals(""))
				orgEmpMstGuj.setEmpLname(empLNameGuj);
			
			if(gradeMst!=null && gradeMst != null)
				orgEmpMstEng.setOrgGradeMst(gradeMst);
			if(orgUserMst!=null && orgUserMst != null)
				orgEmpMstEng.setOrgUserMst(orgUserMst);
			if(prefix!=null && !prefix.equals(""))
				orgEmpMstEng.setEmpPrefix(prefix);
			if(empFName!=null && !empFName.equals(""))
				orgEmpMstEng.setEmpFname(empFName);
			if(empMName!=null && !empMName.equals(""))
				orgEmpMstEng.setEmpMname(empMName);
			if(empLName!=null && !empLName.equals(""))
				orgEmpMstEng.setEmpLname(empLName);
			if (dateOfBirth!=null && !dateOfBirth.equals(""))
				orgEmpMstEng.setEmpDob(StringUtility.convertStringToDate(dateOfBirth));
			if(dateOfJoin!=null && !dateOfJoin.equals(""))
				orgEmpMstEng.setEmpDoj(StringUtility.convertStringToDate(dateOfJoin));
			if(dateOfRet!=null && !dateOfRet.equals(""))
				orgEmpMstEng.setEmpSrvcExp(StringUtility.convertStringToDate(dateOfRet));
			
		logger.info("orgEmpMstGuj size is *********"+orgEmpMstGuj);
		logger.info("orgEmpMstEng size is *********"+orgEmpMstEng);
		logger.info("dateOfBirth size is *********"+dateOfBirth);
		logger.info("dateOfJoin size is *********"+dateOfJoin);
			objectArgs.put("empInfoGuj", orgEmpMstGuj);
			objectArgs.put("empInfoEng", orgEmpMstEng);
			objectArgs.put("dateOfBirth", dateOfBirth);
			objectArgs.put("dateOfJoin", dateOfJoin);
			
			resObj.setResultValue(objectArgs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resObj;
	}
}
