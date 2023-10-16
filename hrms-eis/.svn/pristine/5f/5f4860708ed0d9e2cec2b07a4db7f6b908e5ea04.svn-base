package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ResetSchedulerDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;

public class ResetSchedulerServiceImpl extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject resetScheduler(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try{
			
			Enumeration paraNames = request.getParameterNames();
			while(paraNames.hasMoreElements())
	        {
	       	String paraName = (String)paraNames.nextElement();
	       	String value = StringUtility.getParameter(paraName,request);
	       	logger.info("Value in Map is from vo to service method" + paraName + ":--->" + value);
	       }
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			CmnLookupMstDAOImpl lookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			ResetSchedulerDAOImpl resetSchedulerDAOImpl = new ResetSchedulerDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			Date lDate = new Date();
			List monthList = lookupDao.getAllChildrenByLookUpNameAndLang("Month", langId);
			List yearList = lookupDao.getAllChildrenByLookUpNameAndLang("Year", langId);
			List minuteList = new ArrayList<Integer>();
			List hourList= new ArrayList<Integer>();
			List dayList= new ArrayList<Integer>();
			int lIntYear = lDate.getYear();
			int lIntMonth = lDate.getMonth();
			for(int lIntMinIndex=1;lIntMinIndex<=60;lIntMinIndex++)
				minuteList.add(new Integer(lIntMinIndex));
			for(int lIntHourIndex=1;lIntHourIndex<=24;lIntHourIndex++)
				hourList.add(new Integer(lIntHourIndex));
			for(int lIntDayIndex=1;lIntDayIndex<=31;lIntDayIndex++)
				dayList.add(new Integer(lIntDayIndex));
			long forMonth=0;
			long forYear=0;
			long forHour=0;
			long forMinute=0;
			long startThread=0;
			long endThread=0;
			long counterVal=0;
			long day = 0;
			Map schMap = new HashMap();
			
			String typeOfReset = (StringUtility.getParameter("forMonth",request)!=null&&!(StringUtility.getParameter("typeOfReset",request).equals(""))?StringUtility.getParameter("typeOfReset",request):"");
			forMonth = (StringUtility.getParameter("forMonth",request)!=null&&!(StringUtility.getParameter("forMonth",request).equals(""))?Long.parseLong(StringUtility.getParameter("forMonth",request)):0);
			forYear = (StringUtility.getParameter("forYear",request)!=null&&!(StringUtility.getParameter("forYear",request).equals(""))?Long.parseLong(StringUtility.getParameter("forYear",request)):0);
			forHour = (StringUtility.getParameter("forHour",request)!=null&&!(StringUtility.getParameter("forHour",request).equals(""))?Long.parseLong(StringUtility.getParameter("forHour",request)):0);
			forMinute = (StringUtility.getParameter("forMinute",request)!=null&&!(StringUtility.getParameter("forMinute",request).equals(""))?Long.parseLong(StringUtility.getParameter("forMinute",request)):0);
			startThread = (StringUtility.getParameter("startThread",request)!=null&&!(StringUtility.getParameter("startThread",request).equals(""))?Long.parseLong(StringUtility.getParameter("startThread",request)):0);
			endThread = (StringUtility.getParameter("endThread",request)!=null&&!(StringUtility.getParameter("endThread",request).equals(""))?Long.parseLong(StringUtility.getParameter("endThread",request)):0);
			counterVal = (StringUtility.getParameter("counterVal",request)!=null&&!(StringUtility.getParameter("counterVal",request).equals(""))?Long.parseLong(StringUtility.getParameter("counterVal",request)):0);
			day = (StringUtility.getParameter("forDay",request)!=null&&!(StringUtility.getParameter("forDay",request).equals(""))?Long.parseLong(StringUtility.getParameter("forDay",request)):0);
			schMap.put("forHour", forHour);
			schMap.put("forMinute", forMinute);
			schMap.put("startThread", startThread);
			schMap.put("endThread", endThread);
			schMap.put("counterVal", counterVal);
			schMap.put("forMonth", forMonth);
			schMap.put("forYear", forYear);
			schMap.put("typeOfReset", typeOfReset);
			schMap.put("day", day);
			if(typeOfReset != null && typeOfReset!= "" && typeOfReset.length()>1 ){
				if (typeOfReset.equalsIgnoreCase("bothReset")){
					resetSchedulerDAOImpl.truncateForMonthYear(schMap);
					resetSchedulerDAOImpl.resetTimerAndCounter(schMap);
					resetSchedulerDAOImpl.resetJobs(schMap);
				}
				if (typeOfReset.equalsIgnoreCase("timerCounterReset")){
					resetSchedulerDAOImpl.resetTimerAndCounter(schMap);
				}
				if (typeOfReset.equalsIgnoreCase("jobReset")){
					resetSchedulerDAOImpl.truncateForMonthYear(schMap);
					resetSchedulerDAOImpl.resetJobs(schMap);
				}
				objectArgs.put("msg", "Successfully Updated-)"+typeOfReset);
			}

			objectArgs.put("monthList", monthList);
			objectArgs.put("yearList", yearList);
			objectArgs.put("dayList", dayList);
			objectArgs.put("currMonth", lIntMonth);
			objectArgs.put("currYear", lIntYear);
			objectArgs.put("minuteList", minuteList);
			objectArgs.put("hourList", hourList);
			resultObject.setViewName("ResetScheduler");
			resultObject.setResultValue(objectArgs);

		}catch(Exception e){
			e.printStackTrace();
		}
		return resultObject;
	}
}
