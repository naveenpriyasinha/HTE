package com.tcs.sgv.eis.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.valuebeans.reports.DefaultComboItem;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.util.DBConnection;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.reports.dao.ReportParameterDAO;



public class TreeViewReports extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	
	
	public ResultObject getBillMonthYear(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

		try

		{	
		logger.info("in AllEmpRec Service");
		//System.out.println("jay hanuman");
		long locId=0;
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        
		CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", 1);
		List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", 1);
        objectArgs.put("monthList",monthList);
        objectArgs.put("yearList",yearList);
        
        
        TokenNumberDAOImpl TokenDAO = new TokenNumberDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
    	List billList = new ArrayList(); 
		List<HrPayBillHeadMpg> BillList = new ArrayList();		
		locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
		billList = TokenDAO.getBillListForDisplay(locId);
		for(Iterator itr=billList.iterator();itr.hasNext();)
		{    			
			Object[] row = (Object[])itr.next();
			HrPayBillHeadMpg hrPayBillHeadMpg = new HrPayBillHeadMpg();					
			hrPayBillHeadMpg.setBillId(Long.parseLong(row[0].toString()));
			hrPayBillHeadMpg.setBillHeadId(Long.parseLong(row[1].toString()));		 	
			BillList.add(hrPayBillHeadMpg);
		}   
		
        
        
        
        //objectArgs.put("billNoList",getBillNos("1","300022"));
        objectArgs.put("billNoList",BillList);
        objectArgs.put("deptId",locId);
        //System.out.println("deptId in service impl is"+locId);
		
		resultObject.setResultCode(ErrorConstants.SUCCESS);
        resultObject.setResultValue(objectArgs);
      
        resultObject.setViewName("TreeViewREports");
      //  resultObject.setViewName("Paramaeter");
     
       // System.out.println((Map)objectArgs.get("voTOServiceMap"));
        
    
	}
		catch (Exception e) {

			logger.info("Exception Ocuures...showGroupManagement Service");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg","There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");

		}
		return resultObject;
      	
     
	} 
	
	
	
	
}
//end of to be changed

