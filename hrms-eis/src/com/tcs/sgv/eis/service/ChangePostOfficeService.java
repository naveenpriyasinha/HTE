package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ChangePostOfficeDAO;
import com.tcs.sgv.eis.dao.ChangePostOfficeDAOImpl;
import com.tcs.sgv.eis.valueobject.ChangePostOfficeCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;

@SuppressWarnings({"unchecked" , "unused"})
public class ChangePostOfficeService extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());

	
	public ResultObject getPostOfficeInitialData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("inside getPostOfficeInitialData------------>");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			ChangePostOfficeDAO changePostOfficeDAO = new ChangePostOfficeDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());

			List officeList = changePostOfficeDAO.getOfficeList(locId);
			objectArgs.put("currentOffList", officeList);
			objectArgs.put("newOffList", officeList);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ChangePostOffice");
			resultObject.setResultValue(objectArgs);
		}
		catch (Exception e)
		{
			logger.info("Inside Catch.Exception occurs------>");
			logger.error("Error is: "+ e.getMessage());
			logger.error(e);
		}
		return resultObject;
	}

	public ResultObject getEmployeeList(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("inside getEmployeeList------------>");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			ChangePostOfficeDAO changePostOfficeDAO = new ChangePostOfficeDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());
			long currentOffId = -1;
			long newOffId = -1 ;
			if (voToService.get("currentOffId") != null)
			{
				currentOffId = Long.valueOf(voToService.get("currentOffId").toString());
			}
			if (voToService.get("newOffId") != null)
			{
				newOffId = Long.valueOf(voToService.get("newOffId").toString());
			}

			List employeeList = changePostOfficeDAO.getEmployeeListByOfficeId(currentOffId);
			int employeeListSize = employeeList.size();
			List<ChangePostOfficeCustomVO> employeeListForJSP = new ArrayList<ChangePostOfficeCustomVO>();
			for (int i = 0; i < employeeListSize; i++)
			{
				ChangePostOfficeCustomVO changePostOfficeCustomVO = new ChangePostOfficeCustomVO();
				Object[] obj = (Object[]) employeeList.get(i);
				String empFname = "";
				String empMname = "";
				String empLname = "";
				String empFullName = "";
				long userId = 0;
				long postId = 0;
				if (obj[0] != null)
					empFname = obj[0].toString() + " ";
				if (obj[1] != null)
					empMname = obj[1].toString() + " ";
				if (obj[2] != null)
					empLname = obj[2].toString();
				if (obj[3] != null)
					userId = Long.valueOf(obj[3].toString());
				if (obj[4] != null)
					postId = Long.valueOf(obj[4].toString());

				empFullName = empFname + empMname + empLname;
				changePostOfficeCustomVO.setEmpFullName(empFullName);
				changePostOfficeCustomVO.setUserId(userId);
				changePostOfficeCustomVO.setPostId(postId);
				employeeListForJSP.add(changePostOfficeCustomVO);
			}
			List officeList = changePostOfficeDAO.getOfficeList(locId);
			
			objectArgs.put("currentOffList", officeList);
			objectArgs.put("newOffList", officeList);
			
			objectArgs.put("employeeListForJSP", employeeListForJSP);
			objectArgs.put("employeeListSize", employeeListSize);
			objectArgs.put("currentOffId", currentOffId);
			objectArgs.put("newOffId", newOffId);
			
			//getPostOfficeInitialData(objectArgs);			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ChangePostOffice");
			resultObject.setResultValue(objectArgs);
		}
		catch (Exception e)
		{
			logger.info("Inside Catch. Exception occurs------>");
			logger.error("Error is: "+ e.getMessage());
			logger.error(e);
		}
		return resultObject;
	}
	
	public ResultObject saveOfficeData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("inside getEmployeeList------------>");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			ChangePostOfficeDAOImpl changePostOfficeDAOImpl = new ChangePostOfficeDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());
			long currentOffId = -1;
			long newOffId = -1 ;
			String allPostId = "";
			if (voToService.get("currentOffId") != null)
			{
				currentOffId = Long.valueOf(voToService.get("currentOffId").toString());
			}
			if (voToService.get("newOffId") != null)
			{
				newOffId = Long.valueOf(voToService.get("newOffId").toString());
			}
			if (voToService.get("allPostId") != null)
			{
				allPostId = voToService.get("allPostId").toString();
				allPostId = allPostId.substring(0,allPostId.lastIndexOf(","));
			}
			
			changePostOfficeDAOImpl.updateHrPayOfficePostMpg(allPostId, currentOffId, newOffId);
			changePostOfficeDAOImpl.updaateMstDcpsEmp(allPostId, currentOffId, newOffId);
			//changePostOfficeDAOImpl.getSession().flush();
			objectArgs.put("msg", "Post Office is successfully updated.");
			objectArgs.put("currentOffId", currentOffId);
			objectArgs.put("newOffId", newOffId);
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ChangePostOffice");
			resultObject.setResultValue(objectArgs);
		}
		catch (Exception e)
		{
			logger.info("Inside Catch. Exception occurs------>");
			logger.error("Error is: "+ e.getMessage());
			logger.error(e);
		}
		return resultObject;
	}
}
