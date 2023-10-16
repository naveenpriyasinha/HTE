package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.NetMismatchDAO;
import com.tcs.sgv.eis.dao.NetMismatchDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.NetMismatchCustomVO;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDao;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;

public class NetMismatchService extends ServiceImpl 
{

	Log logger = LogFactory.getLog(getClass());
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ResultObject getNetMismatchDtls(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("inside getNetMismatchDtls------------>");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		NetMismatchDAO mismatchDAO = new NetMismatchDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		String coloumn_names = "";
		String condition_coloumn_names = "(";
		NetMismatchCustomVO netMismatchCustomVO = null;
		Date todayDate = new Date();
		int month = todayDate.getMonth()+1;
		int year = todayDate.getYear()+1900;
		try
		{
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
	        Map voToService = (Map)objectArgs.get("voToServiceMap");
	        long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
	        long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
	        
	        AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			
			List employeeList = mismatchDAO.getAllEmployeeByLocId(locId);
			List empList = new ArrayList();
			for(int i=0;i<employeeList.size();i++)
			{
				Object[] empObj = (Object[]) employeeList.get(i);
				String empName = empObj[0].toString();
				long empId = Long.parseLong(empObj[1].toString());
				netMismatchCustomVO = new NetMismatchCustomVO();
				netMismatchCustomVO.setEmpName(empName);
				netMismatchCustomVO.setEmpId(empId);
				empList.add(netMismatchCustomVO);
			}
			objectArgs.put("empList", empList);
	        
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
    		Collections.reverse(yearList);
    		objectArgs.put("yearList", yearList);
    		List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
    		objectArgs.put("monthList", monthList);
	        
        	if(voToService.get("FromJSP") !=null  && voToService.get("EmpId") !=null && voToService.get("FromJSP").toString().equalsIgnoreCase("y"))
        	{
    			long empId = Long.parseLong(voToService.get("EmpId").toString());
    			List empCmpMpgList = mismatchDAO.getEmpCmpMpgData(empId,locId);
    			String allowanceCompoStr = "";
    			String deductCompoStr = "";
    			
    			for(int i = 0; i<empCmpMpgList.size();i++)
    			{
    				HrEisEmpCompMpg hrEisEmpCompMpg = (HrEisEmpCompMpg) empCmpMpgList.get(i);
    				if(hrEisEmpCompMpg.getCmnLookupMst().getLookupId() == 2500134)
    				{
    					allowanceCompoStr = allowanceCompoStr + hrEisEmpCompMpg.getCompId() + ",";
    				}
    				if(hrEisEmpCompMpg.getCmnLookupMst().getLookupId() == 2500135)
    				{
    					deductCompoStr = deductCompoStr + hrEisEmpCompMpg.getCompId() + ",";
    				}
    			}
    			if(allowanceCompoStr.length() > 0)
    			{
    				allowanceCompoStr = allowanceCompoStr.substring(0, allowanceCompoStr.lastIndexOf(","));
    			}
    			if(deductCompoStr.length() > 0)
    			{
    				deductCompoStr = deductCompoStr.substring(0, deductCompoStr.lastIndexOf(","));
    			}
        		
        		List paybillColumns = new ArrayList();
        		paybillColumns.add("Employee Name");
        		paybillColumns.addAll(mismatchDAO.getPayBillColumns(locId,allowanceCompoStr,deductCompoStr));
		        objectArgs.put("paybillColumns",paybillColumns);
		        
		        
		        for(int i=1; i<paybillColumns.size(); i++)
		        {
		        	coloumn_names = coloumn_names + "pb." +paybillColumns.get(i).toString()+",";
		        	condition_coloumn_names = condition_coloumn_names + "pb." + paybillColumns.get(i).toString() + ">0 OR ";
		        }
		        if(coloumn_names.length() > 0 )
		        {
		        	coloumn_names = coloumn_names.substring(0,coloumn_names.lastIndexOf(","));
		        }
		        if(condition_coloumn_names.length() > 0)
		        {
		        	condition_coloumn_names = condition_coloumn_names.substring(0,condition_coloumn_names.lastIndexOf(" OR "));
		        	condition_coloumn_names = condition_coloumn_names + ")";
		        }
		        
		        month = Integer.parseInt(voToService.get("Month").toString());
				year = Integer.parseInt(voToService.get("Year").toString());
				List searchData = mismatchDAO.getTotalHeadValue(empId,month,year,coloumn_names,condition_coloumn_names);
				objectArgs.put("searchData", searchData);
				objectArgs.put("paybillColumnSize",paybillColumns.size());
				objectArgs.put("Month", month);
				objectArgs.put("Year", year);
				objectArgs.put("empId", empId);
				logger.info("PayBillColoumns size is "+paybillColumns.size());
				logger.info("searchData size is "+searchData.size());
        	}
	        
	        resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("netMismatchView");
        	resultObject.setResultValue(objectArgs);
		}
		catch(Exception e)
		{
			logger.info("Inside Catch.Exception occurs------>");
			logger.error("Error is: "+ e.getMessage());
			logger.error(e);
		}
		return resultObject;
	}
}
