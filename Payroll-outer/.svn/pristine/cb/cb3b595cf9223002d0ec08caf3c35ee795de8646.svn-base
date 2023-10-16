package com.tcs.sgv.pension.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.util.DAOFactory;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class PensionReportDAOImpl extends DefaultReportDataFinder implements ReportDataFinder
{
	private SessionFactory lObjSessionFactory = null;
	PensionReportQueryDAOImpl lObjRptQueryDAO = null;
	ServiceLocator serv=null;
	PensionReportQueryDAOImpl queryDAO=null;
	Long langID=null;
	Log gLogger = LogFactory.getLog(getClass());	
	
	public Collection findReportData(ReportVO report, Object criteria) throws ReportException
	{
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map sessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
		
		LoginDetails loginVO = (LoginDetails)sessionAttributes.get("loginDetails");
		serv = (ServiceLocator) requestAttributes.get("serviceLocator");
		
		lObjSessionFactory = serv.getSessionFactory();
		lObjRptQueryDAO = new PensionReportQueryDAOImpl(TrnPensionRqstHdr.class,serv.getSessionFactory());
		queryDAO = new PensionReportQueryDAOImpl(TrnPensionRqstHdr.class,serv.getSessionFactory());
		//commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		long langId = loginVO.getLangId();
		long locID = loginVO.getLocation().getLocId();
		String locCode = loginVO.getLocation().getLocationCode();
		long langID=langId;
		String locationCode = loginVO.getLocation().getLocationCode();
		List lArrReportData = new ArrayList();
		
		if (report.getReportCode().equals("210001")){			
			lArrReportData = getInwardPensionCase(report,locID);
		}
		if (report.getReportCode().equals("210002")){			
			lArrReportData = getPSBMonthlyData(report,locationCode,"normalReport");
		}
		if (report.getReportCode().equals("210003")){			
			lArrReportData = getPSBMonthlySubData(report,locationCode,"normalReport");
		}
		if (report.getReportCode().equals("210004")){			
			lArrReportData = getPSBMonthlyData(report,locationCode,"voucherReport");
		}
		if (report.getReportCode().equals("210005")){			
			lArrReportData = getPSBMonthlySubData(report,locationCode,"voucherReport");
		}			
		if(report.getReportCode().equals("210006")){
			lArrReportData = getPSBVoucherData(report,locationCode);
		}
		if (report.getReportCode().equals("210050")){
			lArrReportData = getLastSeenPensionCase(report,locID,langID);
		}
		 if (report.getReportCode().equals("210020"))
		 {           
	            lArrReportData = getProvisionalPensionCase(report,locID);
	     }
		 if (report.getReportCode().equals("210051"))
		 {           
	            lArrReportData = getPensionerDetails(report,locCode,langID);
	     }
		 if (report.getReportCode().equals("210052"))
		 {           
	            lArrReportData = getPayableDetails(report,locID);	            
	     }
		 if (report.getReportCode().equals("210054"))
		 {
	            lArrReportData = getRemarks(report,locID);
	     }
		 if (report.getReportCode().equals("210055"))
		 {
	            lArrReportData = getMonthlyIncomeHeading(report,locCode,langID);
	     }
		 if (report.getReportCode().equals("210056"))
		 {
	            lArrReportData = getMonthlyIncomeDetailswithDate(report,locID);
	     }
		 if (report.getReportCode().equals("210057"))
		 {
	            lArrReportData = getMonthlyIncomeDetails(report,locID,locCode);
	     }
		 if (report.getReportCode().equals("210058"))
		 {
	            lArrReportData = getMonthlyIncomeAmountDetails(report,locID,locCode);
	     }
		 if (report.getReportCode().equals("210021"))
		 {			
			lArrReportData = getPensionSchemeCase(report,locID,langId);
		 }
		 if (report.getReportCode().equals("210022"))
		 {			
			lArrReportData = getPensionerHeadWise(report,locID);
		 }
		 if (report.getReportCode().equals("210023"))
		 {			
			lArrReportData = getAmountWisePension(report,locID);
		 }
        
         /* First Payment Report.. */
         if (report.getReportCode().equals("210041"))
         {           
                lArrReportData = getFirstPayment(report,locID);
         }
         
         /* Treasury Transfer Report.. */
         if (report.getReportCode().equals("210070"))
         {           
                lArrReportData = getTransferCaseDetails(report,locID,langID);
         }
         
         
         return lArrReportData;
	}
	public List getPSBMonthlyData(ReportVO report,String locationCode,String reportType)		
	{
		String fromMonth=null;
		String fromYear=null;
		String toMonth=null;
		String toYear=null;
		String headCode=null;
		String branchName=null;
		ArrayList arrPPOList = new ArrayList();
		try
		{ 						 
			PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
				.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
			lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);			 
			fromMonth = report.getParameterValue("fromMonth").toString();
			fromYear = report.getParameterValue("fromYear").toString();
			toMonth = report.getParameterValue("toMonth").toString();
			toYear = report.getParameterValue("toYear").toString();
			headCode = report.getParameterValue("headCode").toString();
			branchName = report.getParameterValue("branchName").toString();
			arrPPOList = (ArrayList) lObjPensionReportQueryDAOImpl.getPSBPaymentReportData(fromMonth,fromYear,toMonth,toYear, headCode, branchName,locationCode,reportType);
			
		}
		catch (Exception e) {
			e.printStackTrace();				
		}
			return arrPPOList;
		 
	}
	public List getPSBMonthlySubData(ReportVO report,String locationCode,String reportType)		
	{
		String fromMonth=null;
		String fromYear=null;
		String toMonth=null;
		String toYear=null;
		String headCode=null;
		String branchName=null;
		ArrayList arrPPOList = new ArrayList();
		try
		{ 						 
			PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
				.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
			lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);			 
			fromMonth = report.getParameterValue("fromMonth").toString();
			fromYear = report.getParameterValue("fromYear").toString();
			toMonth = report.getParameterValue("toMonth").toString();
			toYear = report.getParameterValue("toYear").toString();
			headCode = report.getParameterValue("headCode").toString();
			branchName = report.getParameterValue("branchName").toString();
			arrPPOList = (ArrayList) lObjPensionReportQueryDAOImpl.getPSBPaymentSubReportData(fromMonth,fromYear,toMonth,toYear, headCode, branchName,locationCode,reportType);
			
		}
		catch (Exception e) {
			e.printStackTrace();				
		}
			return arrPPOList;
		 
	}
	public List getPSBVoucherData(ReportVO report,String locationCode)
	{
		String fromMonth=null;
		String fromYear=null;
		String toMonth=null;
		String toYear=null;
		String headCode="-1";
		String branchName="-1";
		String fromMonthYear="-1";
		String toMonthYear="-1";
		ArrayList arrOuterList = new ArrayList();
		ArrayList arrInnerList = new ArrayList();
		
		try
		{ 						 
			PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
				.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
			lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);	
			
			fromMonth = report.getParameterValue("fromMonth").toString();
			fromYear = report.getParameterValue("fromYear").toString();
			toMonth = report.getParameterValue("toMonth").toString();
			toYear = report.getParameterValue("toYear").toString();
			headCode = report.getParameterValue("headCode").toString();
			branchName = report.getParameterValue("branchName").toString();
			
			
			if(!fromMonth.equals("-1") && !fromYear.equals("-1"))
			{
				if(Integer.parseInt(fromMonth) < 10 )
				{
					fromMonth= "0"+fromMonth;
				}
				fromMonthYear = fromYear+fromMonth;									
			}
			if(!toMonth.equals("-1") && !toYear.equals("-1"))
			{
				if(Integer.parseInt(toMonth) < 10 )
				{
					toMonth= "0"+toMonth;
				}
				toMonthYear = toYear+toMonth;									
			}
			
			
			arrInnerList.add("Generate Voucher");				
			arrInnerList.add(fromMonthYear);
			arrInnerList.add(toMonthYear);
			arrInnerList.add(headCode);
			arrInnerList.add(branchName);			
						
			arrOuterList.add(arrInnerList);	
			
		}
		catch (Exception e) {
			e.printStackTrace();				
		}
			return arrOuterList;
	}
	public List getInwardPensionCase(ReportVO report, long locID)
	{
		ArrayList result = new ArrayList();
		
		String fromDate=null;
		String toDate=null;
		String fromDate1=null;
		String toDate1=null;
		String inwardMode=null;
		String ppoNo=null;
		String pensionType=null;
		String[] counter=null;
		String lOption = null;
		
		try{
			
				fromDate1 = report.getParameterValue("InwardDatefrom").toString();
				if(fromDate1.length() != 0)
				{
					fromDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
				}
				
				toDate1 = report.getParameterValue("InwardDateto").toString();
				if(toDate1.length() != 0)
				{
					toDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
				}
				else
				{
					toDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
				}
				//convert to date and from date from string to date
				//Date DtFromDate = getDate(fromDate1);
				//Date DtToDate = getDate(toDate1);
				////System.out.println("DtFromDate"+DtFromDate);
				
				if(report.getParameterValue("InwardMode")!=null)
				{
					inwardMode = report.getParameterValue("InwardMode").toString();
				}
				
				if(report.getParameterValue("PPONo")!=null)
				{
					ppoNo = report.getParameterValue("PPONo").toString();
				}
				
				if(report.getParameterValue("PensionCaseType")!=null)
				{
					pensionType = report.getParameterValue("PensionCaseType").toString();
				}
				
				/*if(report.getParameterValue("PageBreakup")!=null)
				{
					lOption = report.getParameterValue("PageBreakup").toString();
				}*/

				if(report.getParameterValue("Counter")!= null)
				{
					counter = (String[]) report.getParameterValue("Counter");
				}
				
				result =  (ArrayList) lObjRptQueryDAO.getInwardPensionCaseReport(toDate,fromDate,inwardMode,ppoNo,pensionType,counter);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List getLastSeenPensionCase(ReportVO report, long locID, long langID)
	{
		ArrayList result = new ArrayList();
		
		String fromDate=null;
		String toDate=null;
		String fromDate1=null;
		String toDate1=null;		
		try{
				fromDate1 = report.getParameterValue("datefrom").toString();
				if(fromDate1.length() != 0)
				{
					fromDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
				}
				toDate1 = report.getParameterValue("dateto").toString();
				if(toDate1.length() != 0)
				{
					toDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
				}
				else
				{
					toDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
				}
				
				result =  (ArrayList) lObjRptQueryDAO.getLastSeenPensionCaseReport(toDate,fromDate,langID);			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*private Date getDate(String date)
	{
		Date DtDate = null;
		try
		{
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			String lDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(date));
			//System.out.println("lDate"+lDate);
			String day = null;
			String month = null;
			String year = null;
			String[] lName = null;
				
			lName = new String[5];
			lName = lDate.split("/");
			day = lName[0];
			month = lName[1];
			year = lName[2];
			
			//System.out.println("day"+day+"month"+month+"year"+year);
			
			Calendar myCal = Calendar.getInstance();
			myCal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
	        myCal.set(Calendar.YEAR, Integer.parseInt(year));
	        myCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day)); 
	        
	        DtDate = myCal.getTime();
	        
	        //System.out.println("date is::"+DtDate);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return DtDate;
	}*/
    public List getProvisionalPensionCase(ReportVO report, long locID)
    {
        ArrayList result = new ArrayList();
        
        String fromDate=null;
        String toDate=null;
        String fromDate1=null;
        String toDate1=null;
        
        String ppoNo=null;
        
        try
        {
            fromDate1 = report.getParameterValue("Datefrom").toString();
            fromDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
               
            toDate1 = report.getParameterValue("Dateto").toString();
            toDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
        
            if((report.getParameterValue("PPONo") != null) && ( ! report.getParameterValue("PPONo").equals("")))
            {
                ppoNo = report.getParameterValue("PPONo").toString();
            }
            result =  (ArrayList) lObjRptQueryDAO.getProvisionalPensionCaseReport(toDate,fromDate,ppoNo);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return result;
    }
    public List getPensionerDetails(ReportVO report, String locCode,long langID)
    {
    	ArrayList result = new ArrayList();
    	ArrayList result1 = new ArrayList();
    	ArrayList resultNew = new ArrayList();
    	ArrayList lArrinner_row = null;    	
    	Object[] tuple;
    	String lStrPPONo = null;
    	String lStrForMonth = null;
    	String lStrForYear = null;
    	
    	try
        {
    		PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)
    				DAOFactory.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
    		
    		lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);    		
    				
    		lStrPPONo = report.getParameterValue("PPONo").toString();
    		lStrForMonth = report.getParameterValue("forMonth").toString();		
			lStrForYear = report.getParameterValue("forYear").toString();
    		    		
            if(! lStrPPONo.equals(""))
            {
            	result =  (ArrayList) lObjPensionReportQueryDAOImpl.getPensionerDetailsReport(lStrPPONo);
            	result1 = (ArrayList) lObjPensionReportQueryDAOImpl.getLocationReport(locCode,langID); 
            	
            	if (result1 != null && result1.size() > 0)
                {
                    for(int i=0; i< result1.size(); i++)
                    {
                    	tuple =  (Object[]) result1.get(i); 
                    	
                     //--------First Row------------------------------------------------------------------
                		lArrinner_row = new ArrayList();
                		lArrinner_row.add("Name of Office     :  ");
                		lArrinner_row.add(String.valueOf(tuple[0]));                    		
                		resultNew.add(lArrinner_row);
                		
                		
                	//--------Second Row------------------------------------------------------------------
                		lArrinner_row = new ArrayList();
                		lArrinner_row.add("Address     :  ");
                		lArrinner_row.add(String.valueOf(tuple[1]));                    		
                		resultNew.add(lArrinner_row);                		
                	}                    
                }
            	if (result != null && result.size() > 0)
                {
            		    tuple =  (Object[]) result.get(0);
                    		
                    	//--------Third Row------------------------------------------------------------------
                    	    lArrinner_row = new ArrayList();
                    		lArrinner_row.add("Pensioner's Name     :  ");
                    		if(tuple[0] != null)
                    		{
                    			lArrinner_row.add(tuple[0].toString());  
                    		}
                    		else
                    		{
                    			lArrinner_row.add("");
                    		}
                    		resultNew.add(lArrinner_row); 		
                    		                    		
                    	//--------Fourth Row------------------------------------------------------------------
                    		lArrinner_row = new ArrayList();
                    		lArrinner_row.add("Address     :  ");
                    		if(tuple[1] != null)
                    		{
                    			lArrinner_row.add(tuple[1].toString());  
                    		}
                    		else
                    		{
                    			lArrinner_row.add("");
                    		}
                    		resultNew.add(lArrinner_row); 	
                    		                    	
                    	//--------Fifth Row------------------------------------------------------------------
                    		lArrinner_row = new ArrayList();
                    		lArrinner_row.add("PAN No.     :  ");
                    		if(tuple[2] != null)
                    		{
                    			lArrinner_row.add(tuple[2].toString());  
                    		}
                    		else
                    		{
                    			lArrinner_row.add("");
                    		}                  		
                    		resultNew.add(lArrinner_row); 
                    		                    		                    		
                    	//--------Sixth Row------------------------------------------------------------------
                    		lArrinner_row = new ArrayList();
                    		lArrinner_row.add("Bank Name     :  ");
                    		if(tuple[3] != null)
                    		{
                    			lArrinner_row.add(tuple[3].toString());  
                    		}
                    		else
                    		{
                    			lArrinner_row.add("");
                    		}                  		
                    		resultNew.add(lArrinner_row);     
                    		
                    	//--------Seventh Row------------------------------------------------------------------
                    		lArrinner_row = new ArrayList();
                    		lArrinner_row.add("Branch     :  ");
                    		if(tuple[4] != null)
                    		{
                    			lArrinner_row.add(tuple[4].toString());  
                    		}
                    		else
                    		{
                    			lArrinner_row.add("");
                    		}                  		
                    		resultNew.add(lArrinner_row); 
                    		
                    	//--------Eighth Row------------------------------------------------------------------
                    		lArrinner_row = new ArrayList();
                    		lArrinner_row.add("Account No.     :  ");
                    		if(tuple[5] != null)
                    		{
                    			lArrinner_row.add(tuple[5].toString());  
                    		}
                    		else
                    		{
                    			lArrinner_row.add("");
                    		}               		
                    		resultNew.add(lArrinner_row); 
                    		
                    	//--------Nineth Row------------------------------------------------------------------
                    		lArrinner_row = new ArrayList();
                    		lArrinner_row.add("P.P.O. No.     :  ");
                    		lArrinner_row.add(lStrPPONo);                    		
                    		resultNew.add(lArrinner_row); 
                    		
                    	//--------Tenth Row------------------------------------------------------------------
                    		lArrinner_row = new ArrayList();
                    		lArrinner_row.add("Details of Pension payment For the Month of     :  ");
                    		
                    		int month = Integer.parseInt(lStrForMonth);
                    		String monthName = null;
                    		switch(month)
                    		{
                    		case 1:monthName = "January";break;
                    		case 2:monthName = "February";break;
                    		case 3:monthName = "March";break;
                    		case 4:monthName = "April";break;
                    		case 5:monthName = "May";break;
                    		case 6:monthName = "June";break;
                    		case 7:monthName = "July";break;
                    		case 8:monthName = "August";break;
                    		case 9:monthName = "September";break;
                    		case 10:monthName = "October";break;
                    		case 11:monthName = "November";break;
                    		case 12:monthName = "December";break;
                    		}
                    		lArrinner_row.add(monthName+", "+lStrForYear);                    		
                    		resultNew.add(lArrinner_row);
                     }
                 }
            	else
            	{
            		//--------Third Row------------------------------------------------------------------
	            	    lArrinner_row = new ArrayList();
	            		lArrinner_row.add("Pensioner's Name     :  ");
	            		lArrinner_row.add("");
	            		resultNew.add(lArrinner_row); 		
            		                    		
	            	//--------Fourth Row------------------------------------------------------------------
	            		lArrinner_row = new ArrayList();
	            		lArrinner_row.add("Address     :  ");
	            		lArrinner_row.add("");
	            		resultNew.add(lArrinner_row); 	
	            		                    	
	            	//--------Fifth Row------------------------------------------------------------------
	            		lArrinner_row = new ArrayList();
	            		lArrinner_row.add("PAN No.     :  ");
	            		lArrinner_row.add("");
	            		resultNew.add(lArrinner_row); 
	            		                    		                    		
	            	//--------Sixth Row------------------------------------------------------------------
	            		lArrinner_row = new ArrayList();
	            		lArrinner_row.add("Bank Name     :  ");
	            		lArrinner_row.add("");
	            		resultNew.add(lArrinner_row);     
            		
	            	//--------Seventh Row------------------------------------------------------------------
	            		lArrinner_row = new ArrayList();
	            		lArrinner_row.add("Branch     :  ");
	            		lArrinner_row.add("");
	            		resultNew.add(lArrinner_row); 
	            		
	            	//--------Eighth Row------------------------------------------------------------------
	            		lArrinner_row = new ArrayList();
	            		lArrinner_row.add("Account No.     :  ");
	            		lArrinner_row.add("");
	            		resultNew.add(lArrinner_row); 
            		
	            	//--------Nineth Row------------------------------------------------------------------
	            		lArrinner_row = new ArrayList();
	            		lArrinner_row.add("P.P.O. No.     :  ");
	            		lArrinner_row.add(lStrPPONo);                    		
	            		resultNew.add(lArrinner_row); 
	            		
	            	//--------Tenth Row------------------------------------------------------------------
	            		lArrinner_row = new ArrayList();
	            		lArrinner_row.add("Details of Pension payment For the Month of     :  ");
            		
	            		int month = Integer.parseInt(lStrForMonth);
	            		String monthName = null;
	            		switch(month)
	            		{
	            		case 1:monthName = "January";break;
	            		case 2:monthName = "February";break;
	            		case 3:monthName = "March";break;
	            		case 4:monthName = "April";break;
	            		case 5:monthName = "May";break;
	            		case 6:monthName = "June";break;
	            		case 7:monthName = "July";break;
	            		case 8:monthName = "August";break;
	            		case 9:monthName = "September";break;
	            		case 10:monthName = "October";break;
	            		case 11:monthName = "November";break;
	            		case 12:monthName = "December";break;
	            		}
	            		lArrinner_row.add(monthName+", "+lStrForYear);                    		
	            		resultNew.add(lArrinner_row);
            	}
         }
        catch (Exception e) 
        {
        	gLogger.error(e);
            e.printStackTrace();
            
        }
        return resultNew;    	
    }
    
    public List getPayableDetails(ReportVO report, long locID)
    {
    	ArrayList resultPay = new ArrayList();
    	ArrayList resultDeduc = new ArrayList();
    	List resultFinal = null;
    	List ReturnList = new ArrayList();
    	
    	String lStrPPONo = null;
    	String lStrForMonth = null;
    	String lStrForYear = null;
    	String lStrForMonthYear = null;
    	
    	BigDecimal basicPension = null;
    	BigDecimal cvpMonth = null;
    	BigDecimal reducedPension = null;
    	BigDecimal pensionCut = null;
    	BigDecimal dp = null;
    	BigDecimal ti = null;
    	BigDecimal ma = null;
    	BigDecimal dcrg = null;
    	BigDecimal cvp = null;
    	BigDecimal other = null;
    	BigDecimal tiArrear = null;
    	BigDecimal specialCut = null;
    	BigDecimal totalPay = null;
    	
    	BigDecimal hba = null;
    	BigDecimal mca = null;
    	BigDecimal rent = null;
    	BigDecimal it = null;
    	BigDecimal others = null;
    	BigDecimal totalDeduc = null;
    	
    	try
        {
    		PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
			.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
    		lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);
    		
			lStrPPONo = report.getParameterValue("PPONo").toString();		
			lStrForMonth = report.getParameterValue("forMonth").toString();		
			lStrForYear = report.getParameterValue("forYear").toString();
			
    		if(! lStrForMonth.equals(""))
    		{
    			if( Integer.parseInt(lStrForMonth) < 10 )
	    		{
	    			lStrForMonth = "0" + lStrForMonth;
	    		}
    		}
    		lStrForMonthYear = lStrForYear + lStrForMonth;
    		
    		//Pay Details.....
            if(! lStrPPONo.equals("") )
            {
            	resultPay =  (ArrayList) lObjPensionReportQueryDAOImpl.getPayableDetailsReport(lStrPPONo,lStrForMonthYear);            	
            }            
           	if (resultPay!=null && !resultPay.isEmpty()) 
            {   
                for(int i=0;i<resultPay.size();i++)
                {
                    List tuple = (ArrayList)resultPay.get(i);
                    
                    basicPension = new BigDecimal(tuple.get(0).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    cvpMonth = new BigDecimal(tuple.get(1).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    reducedPension = new BigDecimal(tuple.get(2).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    pensionCut = new BigDecimal(tuple.get(3).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    dp = new BigDecimal(tuple.get(4).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    ti = new BigDecimal(tuple.get(5).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    ma = new BigDecimal(tuple.get(6).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    dcrg = new BigDecimal(tuple.get(7).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    cvp = new BigDecimal(tuple.get(8).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    other = new BigDecimal(tuple.get(9).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    tiArrear = new BigDecimal(tuple.get(10).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    specialCut = new BigDecimal(tuple.get(11).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    totalPay = new BigDecimal(tuple.get(12).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);                    
                }
            }
           	
           	//Deduction details....
           	if(! lStrPPONo.equals("") )
            {
           		resultDeduc =  (ArrayList) lObjPensionReportQueryDAOImpl.getDeductionDetailsReport(lStrPPONo,lStrForMonthYear);
            }
           	if( resultDeduc != null && !resultDeduc.isEmpty() )
            {
            	List tupp  =  (ArrayList)resultDeduc.get(0); 
            	
            	hba = new BigDecimal(tupp.get(0).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
            	mca = new BigDecimal(tupp.get(1).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
            	rent = new BigDecimal(tupp.get(2).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
            	it = new BigDecimal(tupp.get(3).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
            	others = new BigDecimal(tupp.get(4).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
            	totalDeduc = new BigDecimal(tupp.get(5).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);            	
            }
           	//zeroth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("-------------------");
           	resultFinal.add("	");
           	resultFinal.add("-------------------");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);
           	
           	//first row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("PAYABLE	");
           	resultFinal.add("	");
           	resultFinal.add("DEDUCTIONS		");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);
           	
           	//1A row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("-------------------");
           	resultFinal.add("	");
           	resultFinal.add("-------------------");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);
           	
           	
           	//second row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("Basic Pension (Rs.):");
           	resultFinal.add(basicPension);
           	resultFinal.add("HBA (Rs.):");
           	resultFinal.add(hba);
           	ReturnList.add(resultFinal);
           	
           	//third row-------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("(-)CVP (Rs.):");
           	resultFinal.add(cvpMonth);
           	resultFinal.add("MCA (Rs.):");
           	resultFinal.add(mca);
           	ReturnList.add(resultFinal);
           	
           	//fourth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("Reduced Pension (Rs.):");
           	resultFinal.add(reducedPension);
           	resultFinal.add("Rent Of Building (Rs.):");
           	resultFinal.add(rent);
           	ReturnList.add(resultFinal);
           	
           	//fifth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("Pension Cut (Rs.):");
           	resultFinal.add(pensionCut);
           	resultFinal.add("Income Tax (Rs.):");
           	resultFinal.add(it);
           	ReturnList.add(resultFinal);
           	
           	//sixth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("50% D.A. Merger (Rs.):");
           	resultFinal.add(dp);
           	resultFinal.add("Others(Specify) (Rs.):");
           	resultFinal.add(others);
           	ReturnList.add(resultFinal);
           	
           	//seventh row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("Temporary Increases (Rs.):");
           	resultFinal.add(ti);
           	resultFinal.add("	");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);
           	
           	//eigth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("Medical Allowance (Rs.):");
           	resultFinal.add(ma);
           	resultFinal.add("	");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);
           	
           	//nineth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("D.C.R.G. Difference (Rs.):");
           	resultFinal.add(dcrg);
           	resultFinal.add("	");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);
           	
           	//tenth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("C.V.P. Difference (Rs.):");
           	resultFinal.add(cvp);
           	resultFinal.add("	");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);
           	
           	//eleventh row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("Others(Specify) (Rs.):");
           	resultFinal.add(other);
           	resultFinal.add("	");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);

           	//twelvth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("TI Arrears (Rs.):");
           	resultFinal.add(tiArrear);
           	resultFinal.add("	");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);

           	//thirteenth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("Other Cut (Rs.):");
           	resultFinal.add(specialCut);
           	resultFinal.add("	");
           	resultFinal.add("	");
           	ReturnList.add(resultFinal);
           	
           	//13A row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("-------");
           	resultFinal.add("-------");
           	resultFinal.add("-------");
           	resultFinal.add("-------");
           	ReturnList.add(resultFinal);

           	//fourteenth row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("Total (Rs):");
           	resultFinal.add(totalPay);
           	resultFinal.add("Total (Rs):");
           	resultFinal.add(totalDeduc);
           	ReturnList.add(resultFinal);   
           	
           	//14A row------------------------------------
           	resultFinal = new ArrayList();
           	
           	resultFinal.add("-------");
           	resultFinal.add("-------");
           	resultFinal.add("-------");
           	resultFinal.add("-------");
           	ReturnList.add(resultFinal);	
        }
        catch (Exception e) 
        {
        	gLogger.error(e);
            e.printStackTrace();
        }       
        return ReturnList;  
    } 
    public List getRemarks(ReportVO report, long locID)
    {
    	ArrayList result = new ArrayList();
    	ArrayList lArrinner_row = new ArrayList();
    	ArrayList resultList = new ArrayList();
    	Double tuple = 0.00;
    	String lStrPPONo = "";
    	String lStrForMonth = "";
    	String month = "";
    	String lStrForYear = "";
    	String lStrForMonthYear = "";
    	
    	try
        {
    		PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
			.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
    		lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);    		
    		
    		lStrPPONo = report.getParameterValue("PPONo").toString();		
			lStrForMonth = report.getParameterValue("forMonth").toString();		
			lStrForYear = report.getParameterValue("forYear").toString();
    		    		
    		if(! lStrForMonth.equals(""))
    		{
    			if( Integer.parseInt(lStrForMonth) < 10)
	    		{
	    			month = '0'+lStrForMonth;
	    		}
	    		else
	    		{
	    			month = lStrForMonth;
	    		}
    		}
    		
    		lStrForMonthYear = lStrForYear+month;
    		
            if(! lStrPPONo.equals("") )
            {
            	result =  (ArrayList) lObjPensionReportQueryDAOImpl.getRemarksReport(lStrPPONo,lStrForMonthYear);
            	if (result != null && result.size() > 0)
                {
            		for(int i=0; i< result.size(); i++)
                    {
                    	//tuple =  Double.parseDouble(result.get(i).toString());
                    	BigDecimal tuple11 = new BigDecimal(result.get(i).toString()).setScale(2,BigDecimal.ROUND_UP);
    		    		
						//--------First Row------------------------------------------------------------------
				        	lArrinner_row = new ArrayList();
				    		lArrinner_row.add("Net(PAYABLE - DEDUCTIONS)     :  ");
				    		lArrinner_row.add(tuple11);
				    		resultList.add(lArrinner_row); 
			        	
			    		
			    	    //--------Second Row------------------------------------------------------------------
				    		
				    		//Double amountTotal = tuple;
				    		String s = "";
				    		
				    		if(tuple11.intValue() > 0)
				            {
				            	if(tuple11 != null)
				            	{
				            		//AmountInWords lObjAmountInWords = new AmountInWords();
				            		//s = lObjAmountInWords.getFinalWordsForAmt(tuple11);
				            		RuleBasedNumberFormat fomatter = new  RuleBasedNumberFormat(IReportConstants.INDIAN_ENG_RULE_SET);
				         	        s = fomatter.format(new com.ibm.icu.math.BigDecimal(tuple11));
				            	}
				            }
				    		
				    		
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("Rupees     :  ");
				    		lArrinner_row.add(s);                    		
				    		resultList.add(lArrinner_row);  
			    	
			    	
			    	    //--------Third Row------------------------------------------------------------------
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("Remarks     : 																											 ");
				    		lArrinner_row.add(" ");                   		
				    		resultList.add(lArrinner_row);  
				    		
				    	//--------Fourth Row------------------------------------------------------------------
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("		");
				    		lArrinner_row.add("		");                   		
				    		resultList.add(lArrinner_row);  
				    		
				    	//--------Fifth Row------------------------------------------------------------------
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("		");
				    		lArrinner_row.add("		");                   		
				    		resultList.add(lArrinner_row);  				    		

					    //--------Sixth Row------------------------------------------------------------------
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("		");
				    		lArrinner_row.add("		");                   		
				    		resultList.add(lArrinner_row);  
				    		
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("		");
				    		lArrinner_row.add("		");                   		
				    		resultList.add(lArrinner_row); 
				    		
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("		");
				    		lArrinner_row.add("		");                   		
				    		resultList.add(lArrinner_row); 
				    		
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("		");
				    		lArrinner_row.add("		");                   		
				    		resultList.add(lArrinner_row); 
				    		
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("		");
				    		lArrinner_row.add("		");                   		
				    		resultList.add(lArrinner_row); 
				    		
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("		");
				    		lArrinner_row.add("		");                   		
				    		resultList.add(lArrinner_row); 
				    		
					    //--------Seventh Row------------------------------------------------------------------
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("This is a computer generated statement");
				    		lArrinner_row.add("hence no signature is required.Next");                   		
				    		resultList.add(lArrinner_row); 
				    		
				    		lArrinner_row = new ArrayList();
				    		lArrinner_row.add("Slip of Pension Payment will be issued");
				    		lArrinner_row.add("in the total emoluments.");                   		
				    		resultList.add(lArrinner_row); 
                    }
                }
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return resultList;  
    }
    
    //METHOD 1
    public List getMonthlyIncomeHeading(ReportVO report, String locCode,long langID)
    {
    	ArrayList result1 = new ArrayList();
    	ArrayList resultNew = new ArrayList();
    	ArrayList lArrinner_row = null;    	
    	Object[] tuple;
    	String lStrPPONo = null;
    	String lStrForMonth = null;
    	String lStrForYear = null;
    	Iterator itr;
    	
    	try
        {
    		PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
			.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
    		lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);
    		
			lStrPPONo = report.getParameterValue("PPONo").toString();		
			lStrForMonth = report.getParameterValue("forMonth").toString();		
			lStrForYear = report.getParameterValue("forYear").toString();
			
            if(! lStrPPONo.equals(""))
            {
            	result1 = (ArrayList) lObjPensionReportQueryDAOImpl.getLocationReport(locCode,langID); 
            	
            	report.setReportName("");
            	
            	if (result1 != null && result1.size() > 0)
                {
                    for(int i=0; i< result1.size(); i++)
                    {
                    	tuple =  (Object[]) result1.get(i); 
                    	
                     //--------First Row------------------------------------------------------------------
                		lArrinner_row = new ArrayList();
                		lArrinner_row.add("");
                		lArrinner_row.add(String.valueOf(tuple[0])); 
                		resultNew.add(lArrinner_row);
                		
                	//--------Second Row------------------------------------------------------------------
                		lArrinner_row = new ArrayList();
                		lArrinner_row.add("");
                		lArrinner_row.add(String.valueOf(tuple[1]));  
                		resultNew.add(lArrinner_row);   
                	}      
                }
            }
           
         }
        catch (Exception e) 
        {
        	gLogger.error(e);
            e.printStackTrace();
            
        }
        return resultNew;    	
    }
    
    //METHOD 2
    public List getMonthlyIncomeDetailswithDate(ReportVO report, long locID)
    {
    	ArrayList resultNew = new ArrayList();
    	ArrayList lArrinner_row = null;   
    	
    	String lStrPPONo = null;
    	String lStrForMonth = null;
    	String lStrForYear = null;
    	Iterator itr;
    	
    	try
        {
    		PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
			.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
    		lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);
    		
			lStrPPONo = report.getParameterValue("PPONo").toString();		
			lStrForMonth = report.getParameterValue("forMonth").toString();		
			lStrForYear = report.getParameterValue("forYear").toString();
			
			//for date and time------------------
			SimpleDateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa") ;
			Calendar c = Calendar.getInstance();
			String time = formatter.format(c.getTime());
			//-----------------------------------
			
            if(! lStrPPONo.equals(""))
            {
                 //--------First Row------------------------------------------------------------------
            		lArrinner_row = new ArrayList();
            		lArrinner_row.add("            Date : "+time);     
            		lArrinner_row.add("            Statement No.15");
            		resultNew.add(lArrinner_row);
            }
           
         }
        catch (Exception e) 
        {
        	gLogger.error(e);
            e.printStackTrace();
            
        }
        return resultNew;    	
    }
    //METHOD 3
    public List getMonthlyIncomeDetails(ReportVO report,long locID,String locCode)
    {
    	ArrayList result = new ArrayList();
    	ArrayList resultNew = new ArrayList();
    	ArrayList lArrinner_row = null;    	
    	
    	Object[] tuple;
    	
    	String lStrPPONo = null;
    	String lStrForMonth = null;
    	String lStrForYear = null;
    	String lStrForMonthYear = "";
    	
    	Iterator itr;
    	
    	try
        {
    		PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
			.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
    		lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);
    		
			lStrPPONo = report.getParameterValue("PPONo").toString();		
			lStrForMonth = report.getParameterValue("forMonth").toString();		
			lStrForYear = report.getParameterValue("forYear").toString();
			
//			for displaying calculation month
    		
    		if(! lStrForMonth.equals(""))
    		{
    			if( Integer.parseInt(lStrForMonth) < 10 )
	    		{
	    			lStrForMonth = "0" + lStrForMonth;
	    		}
    		}
    		lStrForMonthYear = lStrForYear + lStrForMonth;

			//Other pensioner info
            if(! lStrPPONo.equals(""))
            {
            	result =  (ArrayList) lObjPensionReportQueryDAOImpl.getPensionerDetailsForMonthly(lStrPPONo,lStrForMonthYear,locCode);
            	
            	if (result != null && result.size() > 0)
                {
                    for(int i=0; i< result.size(); i++)
                    {
                    	tuple =  (Object[]) result.get(i); 
                    	
						//--------First Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("PPO No.     ");
			    		lArrinner_row.add(tuple[0].toString());
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
                    	
						//--------Second Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Name    ");
			    		if(tuple[1] != null)
			    		{
			    			lArrinner_row.add(tuple[1].toString());
			    		}
			    		else
			    		{
			    			lArrinner_row.add("");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
                    	
						//--------Third Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Scheme     ");
			    		if(tuple[2] != null)
			    		{
			    			lArrinner_row.add(tuple[2].toString());
			    		}
			    		else
			    		{
			    			lArrinner_row.add("");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------Fourth Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("PPO Type    ");
			    		if(tuple[3] != null)
			    		{
			    			lArrinner_row.add(tuple[3].toString());
			    		}
			    		else
			    		{
			    			lArrinner_row.add("");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
			    		
						//--------Fifth Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Bank Name & Branch    ");
			    		if((tuple[4] != null) && (tuple[5] != null))
			    		{
			    			lArrinner_row.add(tuple[4].toString()+" ,"+tuple[5].toString());
			    		}
			    		else
			    		{
			    			lArrinner_row.add("");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
			    		
						//--------Sixth Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Account No.    ");
			    		if(tuple[6] != null)
			    		{
			    			lArrinner_row.add(tuple[6].toString());
			    		}
			    		else
			    		{
			    			lArrinner_row.add("");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------Seventh Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Calculation Month    ");
			    		lArrinner_row.add(lStrForMonth+" /"+lStrForYear);
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
                    }
                }
            }		
        }
        catch (Exception e) 
        {
        	gLogger.error(e);
            e.printStackTrace();
            
        }
        return resultNew;    	
    }    	
    //METHOD 4
    public List getMonthlyIncomeAmountDetails(ReportVO report,long locID,String locCode)
    {
    	ArrayList result = new ArrayList();
    	ArrayList resultNew = new ArrayList();
    	ArrayList lArrinner_row = null;    	
    	
    	BigDecimal resultRecovery = null;
    	BigDecimal PreviousmonthAmount = null;
    	
    	Object[] tuple;
    	
    	String lStrPPONo = null;
    	String lStrForMonth = null;
    	String lStrForYear = null;
    	String lStrForMonthYear = "";
    	
    	BigDecimal reduced_pension = new BigDecimal(0);
    	BigDecimal ti = new BigDecimal(0);
    	BigDecimal ati = new BigDecimal(0);
    	BigDecimal ir = new BigDecimal(0);
    	BigDecimal ma = new BigDecimal(0);
    	BigDecimal personal_pension = new BigDecimal(0);
    	BigDecimal mo_commission = new BigDecimal(0);
    	BigDecimal it_deduction = new BigDecimal(0);
    	BigDecimal other_deduction = new BigDecimal(0);	
    	
    	Iterator itr;
    	
    	try
        {
    		PensionReportQueryDAOImpl lObjPensionReportQueryDAOImpl = (PensionReportQueryDAOImpl)DAOFactory
			.Create("com.tcs.sgv.pension.report.PensionReportQueryDAOImpl");
    		lObjPensionReportQueryDAOImpl.setSessionFactory(lObjSessionFactory);
    		
			lStrPPONo = report.getParameterValue("PPONo").toString();		
			lStrForMonth = report.getParameterValue("forMonth").toString();		
			lStrForYear = report.getParameterValue("forYear").toString();
			
//			for displaying calculation month
    		
    		if(! lStrForMonth.equals(""))
    		{
    			if( Integer.parseInt(lStrForMonth) < 10 )
	    		{
	    			lStrForMonth = "0" + lStrForMonth;
	    		}
    		}
    		lStrForMonthYear = lStrForYear + lStrForMonth;

    		//For Recovery
           	if(! lStrPPONo.equals("") )
            {
           		resultRecovery = lObjPensionReportQueryDAOImpl.getRecoveryDeduction(lStrPPONo,lStrForMonthYear);
            }
           	
			//For previous month Amount
           	if(! lStrPPONo.equals("") )
            {
           		PreviousmonthAmount =  lObjPensionReportQueryDAOImpl.getPreviousmonthAmount(lStrPPONo,lStrForMonthYear);
            }
           	
			//Other pensioner info
            if(! lStrPPONo.equals(""))
            {
            	result =  (ArrayList) lObjPensionReportQueryDAOImpl.getPensionerDetailsForMonthly(lStrPPONo,lStrForMonthYear,locCode);
            	
            	if (result != null && result.size() > 0)
                {
                    for(int i=0; i< result.size(); i++)
                    {
                    	tuple =  (Object[]) result.get(i); 
			    		
						//--------1st Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Reduced Pension    ");
			    		if(tuple[7] != null)
			    		{
			    			reduced_pension = new BigDecimal(tuple[7].toString()).setScale(2,BigDecimal.ROUND_UP);
			    			lArrinner_row.add(reduced_pension);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
			
						//--------2nd Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("TI    ");
			    		if(tuple[8] != null)
			    		{
			    			ti = new BigDecimal(tuple[8].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(ti);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
			    		
						//--------3rd Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("ATI    ");
			    		if(tuple[9] != null)
			    		{
			    			ati = new BigDecimal(tuple[9].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(ati);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------4th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("IR    ");
			    		if(tuple[10] != null)
			    		{
			    			ir = new BigDecimal(tuple[10].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(ir);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------5th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("MA    ");
			    		if(tuple[11] != null)
			    		{
			    			ma = new BigDecimal(tuple[11].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(ma);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------6th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Personal Pension     ");
			    		if(tuple[12] != null)
			    		{
			    			personal_pension = new BigDecimal(tuple[12].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(personal_pension);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------7th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Mo Commission     ");
			    		if(tuple[13] != null)
			    		{
			    			mo_commission = new BigDecimal(tuple[13].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(mo_commission);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------8th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("IT Deduction    ");
			    		if(tuple[14] != null)
			    		{
			    			it_deduction = new BigDecimal(tuple[14].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(it_deduction);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------9th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Other Deduction    ");
			    		if(resultRecovery != null)
			    		{
			    			other_deduction = new BigDecimal(resultRecovery.toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(other_deduction);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------10th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("MR Amount   ");
			    		lArrinner_row.add("  ");
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 

						//--------11th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("MR Description     ");
			    		lArrinner_row.add("  ");
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
			    		
						//--------12th Row------------------------------------------------------------------
			    		
			    		BigDecimal netAmount = reduced_pension.add(ti).subtract(ati).add(ir).add(ma).add(personal_pension).subtract(mo_commission).subtract(it_deduction).subtract(other_deduction);
			        	
			    		lArrinner_row = new ArrayList();
			    		lArrinner_row.add("This Month Payable    ");
			    		if(netAmount.intValue() > 0)
			    		{
			    			lArrinner_row.add(netAmount);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
			    		
						//--------13th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Prev Month Payable    ");
			    		if(PreviousmonthAmount != null)
			    		{
			    			PreviousmonthAmount = PreviousmonthAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
			    			lArrinner_row.add(PreviousmonthAmount);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
			    		
						//--------14th Row------------------------------------------------------------------
			        	lArrinner_row = new ArrayList();
			    		lArrinner_row.add("Payable Pension    ");
			    		if(netAmount.intValue() > 0)
			    		{			    		
			    			lArrinner_row.add(netAmount);
			    		}
			    		else
			    		{
			    			lArrinner_row.add("0.00");
			    		}
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
			    		
			    		//--------15th Row------------------------------------------------------------------
			    		String s = "";
			    		
			    		if(netAmount.intValue() > 0)
			            {
			            	if(netAmount != null)
			            	{
			            		//AmountInWords lObjAmountInWords = new AmountInWords();			            		
			            		//s = lObjAmountInWords.getFinalWordsForAmt(netAmount);
			            		RuleBasedNumberFormat fomatter = new  RuleBasedNumberFormat(IReportConstants.INDIAN_ENG_RULE_SET);
			         	        s = fomatter.format(new com.ibm.icu.math.BigDecimal(netAmount));
			            	}
			            }
			    		
			    		lArrinner_row = new ArrayList();
			    		lArrinner_row.add("  ");
			    		lArrinner_row.add(s+" Only"); 
			    		lArrinner_row.add("");
			    		resultNew.add(lArrinner_row); 
                    }
                }
            }
        }
        catch (Exception e) 
        {
        	gLogger.error(e);
            e.printStackTrace();
            
        }
        return resultNew;    	
    }    
    
    public List getPensionSchemeCase(ReportVO report, long locID,long langId)
	{
		ArrayList result = new ArrayList();
		
		String fromDate=null;
		String toDate=null;
		String fromDate1=null;
		String toDate1=null;
		String classPension = "";
		String scheme = new String();
		
		try
		{
			fromDate1 = report.getParameterValue("Datefrom").toString();
			if(fromDate1.length() != 0)
			{
				fromDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
			}
			
			toDate1 = report.getParameterValue("Dateto").toString();
			if(toDate1.length() != 0)
			{
				toDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
			}
			else
			{
				toDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
			}
			if(report.getParameterValue("Scheme")!= null)
			{
				scheme =  report.getParameterValue("Scheme").toString();
			}
			if(! report.getParameterValue("Class").toString().equals("-1"))
			{
				classPension = report.getParameterValue("Class").toString();
			}
			
			result =  (ArrayList) lObjRptQueryDAO.getPensionCaseReport(toDate,fromDate,scheme,classPension,langId);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
    
    public List getPensionerHeadWise(ReportVO report, long locID)
	{
		ArrayList result = new ArrayList();
		
		String fromDate=null;
		String toDate=null;
		String fromDate1=null;
		String toDate1=null;
		Long headCode=null;
		String headCode1=null;
		String headCode2;
		String scheme =null;
		try
		{
			fromDate1 = report.getParameterValue("Datefrom").toString();
			if(fromDate1.length() != 0)
			{
				fromDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
				
			}
			
			toDate1 = report.getParameterValue("Dateto").toString();
			if(toDate1.length() != 0)
			{
				toDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
			}
			else
			{
				toDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
			}
			if(report.getParameterValue("Scheme").toString().equalsIgnoreCase("-1"))
			{
				scheme = null;
				
			}
			else
			{
				scheme =  report.getParameterValue("Scheme").toString();
			}
				
			
			if(report.getParameterValue("HeadCode").toString().equalsIgnoreCase("-1"))
			{
				headCode = null;
				
			}
			else
			{
				headCode1 = report.getParameterValue("HeadCode").toString();
				headCode2 = headCode1.substring(0,2).trim();
				headCode = Long.parseLong(headCode2);
				
			}
			result =  (ArrayList) lObjRptQueryDAO.getPensionerHeadWiseSummary(toDate,fromDate,headCode,scheme);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	
	}
    
    public List getAmountWisePension(ReportVO report, long locID)
	{
		ArrayList result = new ArrayList();
		
		String fromDate=null;
		String toDate=null;
		String fromDate1=null;
		String toDate1=null;
		try
		{
			fromDate1 = report.getParameterValue("Datefrom").toString();
			if(fromDate1.length() != 0)
			{
				fromDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
			}
			
			toDate1 = report.getParameterValue("Dateto").toString();
			if(toDate1.length() != 0)
			{
				toDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
			}
			else
			{
				toDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
			}
			String classPension = "";
			String scheme = "";
			if(! report.getParameterValue("Scheme").toString().equals("-1"))
			{
				scheme =  report.getParameterValue("Scheme").toString();
			}
			if(! report.getParameterValue("Class").toString().equals("-1"))
			{
				classPension = report.getParameterValue("Class").toString();
			}
			result =  (ArrayList) lObjRptQueryDAO.getAmntWiseReport(toDate,fromDate,scheme,classPension);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	
	}
    
    
    /**
     * First Payment Report
     * @author Sagar Patel
     * @param report
     * @param locID
     * @return
     */
    public List getFirstPayment(ReportVO report, long locID)
    {
        ArrayList result = new ArrayList();
        
        String fromDate=null;
        String toDate=null;
        String fromDate1=null;
        String toDate1=null;
        
        try
        {
            fromDate1 = report.getParameterValue("Datefrom").toString();
            if(fromDate1.length() != 0)
            {
                fromDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
            }
            
            toDate1 = report.getParameterValue("Dateto").toString();
            if(toDate1.length() != 0)
            {
                toDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
            }
            else
            {
                toDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
            }
            
            result =  (ArrayList) lObjRptQueryDAO.getFirstPaymentReport(toDate,fromDate);
        
        }
        catch (Exception e) 
        {
        	gLogger.error(" Erroor  In >> "+e, e);
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * Transfer Case Details Report
     * @author Sagar Patel
     * @param report
     * @param locID
     * @return List
     */
    public List getTransferCaseDetails(ReportVO report, long locID, long langID)
    {
        ArrayList result = new ArrayList();
        
        String fromDate=null;
        String toDate=null;
        String fromDate1=null;
        String toDate1=null;
        
        String lStrCaseType = null;
        String lStrTrsry = null;
        String lStrSubTrsry = null;
        
        try
        {
            fromDate1 = report.getParameterValue("Datefrom").toString();
            lStrCaseType = report.getParameterValue("CaseType").toString();
            lStrTrsry = report.getParameterValue("Treasury").toString();
            lStrSubTrsry = report.getParameterValue("SubTreasury").toString();
            
            if(fromDate1.length() != 0)
            {
                fromDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(fromDate1));
            }
            
            toDate1 = report.getParameterValue("Dateto").toString();
            if(toDate1.length() != 0)
            {
                toDate=new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(toDate1));
            }
            else
            {
                toDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
            }
            
            if(!(lStrSubTrsry.equalsIgnoreCase("-1")))
            {
            	lStrTrsry = report.getParameterValue("SubTreasury").toString();
            }
            
            result =  (ArrayList) lObjRptQueryDAO.getTransferCaseData(toDate,fromDate,lStrCaseType,lStrTrsry,locID,langID);
        
        }
        catch (Exception e) 
        {
        	gLogger.error(" Erroor  In >> "+e, e);
            e.printStackTrace();
        }
        return result;
    }
    

}
