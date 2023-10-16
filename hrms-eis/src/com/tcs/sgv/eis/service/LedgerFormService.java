package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaySlipDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;




public class LedgerFormService extends ServiceImpl{
	Log logger = LogFactory.getLog( getClass() );
	public ResultObject getLedgerFormpara(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getLedgerFormParaPage");		
			 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			 
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
            
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
    		List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
    		List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
				            
			objectArgs.put("monthList", monthList);
			objectArgs.put("yearList", yearList);
			logger.info("**************************"+monthList.size());
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("LedgerFormpara");
			logger.info("getLedgerFormParaPage");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...getLedgerFormParaPage");
			ne.printStackTrace();
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...getLedgerFormParaPage");
			pe.printStackTrace();
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){
			
			logger.info("Exception Ocuures...getLedgerFormParaPage");
			 e.printStackTrace();
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}	
	public ResultObject generateledgerform(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			int monthGiven=-1; //-1 is default for current month 
			int yearGiven=-1; //-1 is default for current year 
			List ledgerformdata=new ArrayList();
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	

			PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			String billNo="";
			if(request.getParameter("month")!=null && request.getParameter("month").toString()!="")
			{
			  monthGiven = Integer.parseInt(request.getParameter("month").toString());
			  yearGiven = Integer.parseInt(request.getParameter("year").toString());
			  billNo=objectArgs.get("billNo")!=null?objectArgs.get("billNo").toString():"";
			 logger.info("Given month and year for approve payslip is " + monthGiven + "--" + yearGiven +"bill no is=====>"+billNo);
			 
			}

			PaySlipDAOImpl paySlipDAO = new PaySlipDAOImpl(HrPayPayslip.class,serv.getSessionFactory());
			int currMonth=0;
			 int currYear=0;
			 

             if(monthGiven!=-1 && yearGiven!=-1)
			 {
				 currMonth=monthGiven;
				 currYear=yearGiven;
			 }
			 else
			 {
				 Date DBDate = DBUtility.getCurrentDateFromDB();
				 currMonth = DBDate.getMonth()+1;
				 currYear=DBDate.getYear()+1900;
				 logger.info("DB Month is " + currMonth + "\nCurrent year is " + currYear + "bill no is" + billNo);				  				  	
			 }
             
            long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
            ledgerformdata = paySlipDAO.getLedgerFormData(currMonth,currYear,locId,billNo);
            logger.info("ledgerformdata?***************"+ledgerformdata);
            objectArgs.put("ledgerformdata", ledgerformdata);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("LedgerFormPage");
			logger.info("LedgerFormPage");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...generateledgerform");
			ne.printStackTrace();
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...generateledgerform");
			pe.printStackTrace();
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){
			
			logger.info("Exception Ocuures...generateledgerform");
			 e.printStackTrace();
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}	
	
	public ResultObject getPayBillNos(Map objectArgs)
	{
		logger.info("IN getPayBillNos Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		
		StringBuffer propertyList = new StringBuffer();		
        try{
        	long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
        	List empNames  =  new ArrayList();
			int monthGiven=-1; //-1 is default for current month 
			int yearGiven=-1; //-1 is default for current year 
			
        	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
    		//long finYearId=Long.parseLong(resourceBundle.getString("finYearId"));
    		
    		PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
    		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			  monthGiven = Integer.parseInt(request.getParameter("givenMonth").toString());
			  yearGiven = Integer.parseInt(request.getParameter("givenYear").toString());

    	    

            
    		Calendar cal = Calendar.getInstance();
    		
    		cal.set(Calendar.YEAR, yearGiven);
            cal.set(Calendar.MONTH, monthGiven-1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            java.util.Date finYrDate = cal.getTime();
            SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId); 
            
            
    		
			PaySlipDAOImpl paySlipDAO = new PaySlipDAOImpl(HrPayPayslip.class,serv.getSessionFactory());
            List billNoList = paySlipDAO.getBillData(finYrMst.getFinYearId(),locId);
            
    		ArrayList billList = new ArrayList();    		
    		for(Iterator itr=billNoList.iterator();itr.hasNext();)
    		{
    			
    			Object[] row = (Object[])itr.next();
    			String SubHead = row[0].toString();
    			String classIds = row[1].toString();
    			String dsgnIds = row[2].toString();
    			String billNo = row[3].toString();
    			propertyList.append("<billList-mapping>");
              	propertyList.append("<bill-Id>").append("<![CDATA[").append(SubHead+"~"+classIds+"~"+dsgnIds).append("]]>").append("</bill-Id>");
                propertyList.append("<bill-No>").append("<![CDATA[").append(billNo).append("]]>").append("</bill-No>");             
                propertyList.append("</billList-mapping>");
                
    		}
    		            
        	logger.info("  Bill Nos are "+propertyList.toString().toString());
        	Map result = new HashMap();
            String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
            result.put("ajaxKey", stateNameIdStr);               
            resultObject.setResultValue(result);
            resultObject.setViewName("ajaxData");                    	   
	}
	catch(Exception e)
	{
		logger.info("Exception occures...");
		e.printStackTrace();
		logger.info("Exception Occures.");
		resultObject.setResultValue(objectArgs);
		resultObject.setThrowable(e);
		resultObject.setResultCode(-1);
		resultObject.setViewName("errorPage");
	}		
		return resultObject;
	}	
	
	
	
	

	
}
