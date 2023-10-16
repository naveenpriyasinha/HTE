package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvaMonthMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.PensionCaseDAOImpl;
import com.tcs.sgv.pension.report.CommonReportDAO;
import com.tcs.sgv.pension.report.CommonReportDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * Common Dynamic Report Implementation
 * 
 * @author Sagar Patel
 * @version 1.0
 */
public class CommonReportServiceImpl extends ServiceImpl implements CommonReportService
{  
    /* Global Variable for Logger Class */
    private final Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for LangId */
    private Long gLngLangId = null;
    
    /* Glonal Variable for Location Code */
    private String gStrLocCode = null;
    
    /* Global Variable for Current Date */
    Date gDtCurrDt = null;
    
    public ResultObject getPensionPaymentPara(Map inputMap)
    {
    	gLogger.info("in service getPensionPaymentPara");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        String lStrLangId = SessionHelper.getLangId(inputMap).toString();
        Date gDtCurrDt       = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currDate      = sdf.format(gDtCurrDt);
		String currentYear   = currDate.substring(0,4);
		inputMap.put("CurrentYear", currentYear);
		CommonPensionDAO  lObjCommonPensionDAO  = new CommonPensionDAOImpl(serv.getSessionFactory());
        try
        {	        	                      
    		//To populate year combo....
            List<SgvcFinYearMst> lObjSgvcFinYearMst = null;
            //Returning VO array for Year...
            lObjSgvcFinYearMst = lObjCommonPensionDAO.getSgvcFinYearMstVO(lStrLangId);
            if(lObjSgvcFinYearMst != null)
            {
            	inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMst);
            }
            resObj.setResultValue(inputMap);
            resObj.setViewName("pensionPayment");
        }
        catch (Exception e) 
        {
            gLogger.error("Error in getHeadCodeDetail  and Error is : " + e, e);
        }
        return resObj;
    }
 
 public ResultObject generatePensionPaymentReport(Map inputMap)
    {
	 gLogger.info("in method generatePensionPaymentReport");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrLangId = SessionHelper.getLangId(inputMap).toString();
        
        		
		CommonPensionDAO  lObjCommonPensionDAO  = new CommonPensionDAOImpl(serv.getSessionFactory());
		
        try
        {	  
          	String ppoNo = StringUtility.getParameter("txtPpoNo", request).toString().trim(); 	
		    Integer year = Integer.parseInt(StringUtility.getParameter("YYYY", request).toString().trim());
		    
            String fName = null;
            String mName = null;
            String lName = null;
            String pnsnAddrs = null;
            BigDecimal basic = null; 
            BigDecimal reducedPnsn = null;
             
            Integer forMonth = null;
            BigDecimal paidAmount = null;
            BigDecimal itCutAmount = null;
            BigDecimal arrearAmount = null;
            
            BigDecimal totalRupees = new BigDecimal(0);
            BigDecimal incmeTaxDetd = new BigDecimal(0);
            BigDecimal netRs = new BigDecimal(0);
                                   
            //---------------------To get month ..........
            List<SgvaMonthMst> lObjSgvaMonthMst     = null;
            lObjSgvaMonthMst   = lObjCommonPensionDAO .getSgvaMonthMstVO(lStrLangId); 
      	            	            
            if(lObjSgvaMonthMst != null)
            {
            	inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMst);
            }
            
            CommonReportDAOImpl lObjPensionPaymentDAO = new CommonReportDAOImpl(serv.getSessionFactory());
            List arrLst1 = lObjPensionPaymentDAO.getPensionPaymentData1(ppoNo);
            
            if(arrLst1 != null)
            {
            	fName = (arrLst1.get(1) != null)?arrLst1.get(1).toString():"";
            	mName = (arrLst1.get(2) != null)?arrLst1.get(2).toString():"";
            	lName = (arrLst1.get(3) != null)?arrLst1.get(3).toString():"";
            	pnsnAddrs = (arrLst1.get(4) != null)?arrLst1.get(4).toString():"";
            	basic = (arrLst1.get(5) != null)?new BigDecimal(arrLst1.get(5).toString()):new BigDecimal(0);
            	reducedPnsn = (arrLst1.get(6) != null)?new BigDecimal(arrLst1.get(6).toString()):new BigDecimal(0);
            } 
            else
            {
            	fName = "";
            	mName = "";
            	lName = "";
            	pnsnAddrs = "";
            	basic = new BigDecimal(0);
            	reducedPnsn = new BigDecimal(0);
            }
            
            inputMap.put("ppoNo", ppoNo);
            inputMap.put("name", fName+" "+mName+" "+lName);
            inputMap.put("pnsnAddrs", pnsnAddrs);
            inputMap.put("basic", basic);
            inputMap.put("reducedPnsn", reducedPnsn);
          
            List arrLst2 = lObjPensionPaymentDAO.getPensionPaymentData2(ppoNo, year);
            
            List list2 = new ArrayList();
            Integer lMonth = new Integer(3);
          
            ArrayList larr = new ArrayList();
            int flag1 = 1;
            String lMonthFin = null;
          
            if(arrLst2 != null)
            {
            	for(int i = 0; i < 12; i++)
            	{
            		for(int j = 0; j < arrLst2.size(); j++)
            		{
	        	    	  larr = (ArrayList) arrLst2.get(j);
	        	          
	        	    	  forMonth = new Integer(0);
	        	    	  paidAmount = new BigDecimal(0);
	        	    	  arrearAmount = new BigDecimal(0);
	        	    	  
	        	    	  forMonth = Integer.parseInt(larr.get(0).toString());
	        	    	  paidAmount = new BigDecimal( larr.get(1).toString());
	        	    	  itCutAmount = new BigDecimal(larr.get(2).toString());
	        	    	  arrearAmount = new BigDecimal(larr.get(3).toString());
	        	    	      
	        	    	  String lStrForMonth = null;
	                      String lStrMM = null;
	      	            
	      	              if(forMonth != null)
	      	              { 
	      	            	lStrForMonth = forMonth.toString();
	      	                if(lStrForMonth != "")
	      	                {
	      	                	lStrMM = lStrForMonth.substring(4,6);
	      	                }
	      	              }
	      	      	                   	             	      	             
	      	             if(!(lMonth.toString().length() >1))
	      	             {
	      	            	 lMonthFin = "0"+lMonth.toString();
	       	             }
	      	             else
	      	             {
	      	            	 lMonthFin = lMonth.toString();
	      	             }
	      	              
	      	           	if(lStrMM.equals(lMonthFin) && flag1==1)
	      	                {
	      	                   list2.add(lMonthFin);
	        	               list2.add(paidAmount);
	        	    	       list2.add(arrearAmount);
	        	    	  
	        	    	       totalRupees = totalRupees.add(paidAmount);
	        	    	       incmeTaxDetd = incmeTaxDetd.add(itCutAmount);
	        	    	       flag1=0;	        	    	       
	      	                }   	
	      	  
	        	      }
	        	       if(flag1 == 1)
	            	   {
	                   list2.add(lMonthFin);
	            	   list2.add(new BigDecimal(0));
	            	   list2.add(new BigDecimal(0)); 
	            	   flag1=0;
	            	   }
	        	  
		        	  if(lMonth == 12)
		              {
		            	lMonth=1;
		              }
		              else 
		              {
		            	lMonth++;
		              }
		        	  flag1 = 1;
            	}
            }
            else
            {
            	for(int m=3;m<=12;m++)
            	{
            		list2.add((m<10)?"0"+m:m);
            		list2.add(new BigDecimal(0));
            		list2.add(new BigDecimal(0)); 
            	}
            	for(int m=1;m<=2;m++)
            	{
            		 list2.add("0"+m);
                	 list2.add(new BigDecimal(0));
                	 list2.add(new BigDecimal(0)); 
            	}
            }
          
            netRs = netRs.add(totalRupees);
            netRs = netRs.subtract(incmeTaxDetd);   
          
            inputMap.put("totalRupees", totalRupees);
            inputMap.put("incmeTaxDetd", incmeTaxDetd);
            inputMap.put("netRs", netRs);
         
            RuleBasedNumberFormat fomatter = new  RuleBasedNumberFormat(IReportConstants.INDIAN_ENG_RULE_SET);
	        String AmtInwds = fomatter.format(new com.ibm.icu.math.BigDecimal(netRs));
            
            inputMap.put("year",year);
            inputMap.put("list2", list2);
            inputMap.put("AmtInwds", AmtInwds);
            
            //print string starts here
            StringBuffer sbLine = new StringBuffer();
            if(arrLst1 != null && arrLst2 != null)
            {
                 for(int x=0; x<10; x++)
                 {
                 	sbLine.append("\n");
                 }
                 //table 1
                 sbLine.append(getChar(36, " "));
                 sbLine.append(netRs);
                 sbLine.append("\n\n");
                 sbLine.append(getChar(36, " "));
                 sbLine.append(AmtInwds);
                 sbLine.append("\n\n");
                 sbLine.append(getChar(36, " "));
                 sbLine.append(fName + " " + mName + " " + lName);
                 sbLine.append("\n\n");
                 sbLine.append(getChar(36, " "));
                 sbLine.append(ppoNo);
                 sbLine.append("\n\n");
                 sbLine.append(getChar(36, " "));
                 sbLine.append(year + "-" + (year+1));
                 sbLine.append("\n\n\n\n\n");
                 //table 2
                 sbLine.append(getChar(54, " "));
                 sbLine.append(basic);
                 sbLine.append("\n\n");
                 sbLine.append(getChar(54, " "));
                 sbLine.append(reducedPnsn);
                 sbLine.append("\n\n\n\n");
                 //table 3
                 sbLine.append("\n\n");
                 for(int y=0; y<36; y+=3)
                 {
                 	sbLine.append(getChar(40, " "));
     	            sbLine.append(String.format("%14s", list2.get(y + 1)));
     	            sbLine.append(String.format("%10s", list2.get(y + 2)));
     	            sbLine.append("\n\n");
                 }
                 //table 4
                 sbLine.append(getChar(40, " "));
                 sbLine.append(String.format("%14s", totalRupees));
                 sbLine.append("\n\n");
                 sbLine.append(getChar(40, " "));
                 sbLine.append(String.format("%14s", incmeTaxDetd));
                 sbLine.append("\n\n");
                 sbLine.append(getChar(40, " "));
                 sbLine.append(String.format("%14s", netRs));
                 //footer
                 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                 sbLine.append("\n\n\n\n");
                 sbLine.append(getChar(17, " "));
                 sbLine.append(sdf.format(DBUtility.getCurrentDateFromDB()));
                 sbLine.append("\n");
                 sbLine.append(getChar(17, " "));
                 sbLine.append(fName + " " + mName + " " + lName);
                 sbLine.append("\n");
                 sbLine.append(getChar(17, " "));
                 sbLine.append(pnsnAddrs);
                 sbLine.append("\n\n");
            }
           
            String printString = sbLine.toString();
            //System.out.println(printString);
            //print string ends here
            inputMap.put("PrintString", printString);
            	                   
            resObj.setResultValue(inputMap);
            resObj.setViewName("pensionPaymentRep");
        }
        catch (Exception e) 
        {
            gLogger.error("Error in generatePensionPaymentReport and Error is : " + e, e);
        }
                   
        return resObj;
    }
 	private String getChar(int count, String ele)
    {
        StringBuffer lSBSpace = new StringBuffer();
        for (int i = 0; i < count; i++)
        {
            lSBSpace.append(ele);
        }
        return lSBSpace.toString();
    }
 
     
    
    /**
     * Dyanamic Report For Pension Ledger Generation. 
     * @author Sagar
     * @param inputMap
     * @return ResultObject
     */
    public ResultObject getPensionLedgerReportData(Map inputMap)
    {
        gLogger.info("Come To in method getPensionLedgerReportData....");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        StringBuffer lStrRptHdrStr = new StringBuffer();
        String lStrPpoNo = null;
        
        List lRptHeaderLst = null;
        List lRptDtlsLst = null;
        List louterLst = new ArrayList();
        
        String lFrmMM = null;
        String lToMM = null;
        String lFrmYYYY = null;
        String lToYYYY = null;
        
        Double lGrossAmt = 0D;
        Double lTotDedAmt = 0D;
        Double lNetAmt = 0D;
        
        Integer lFromMonth = 0;
        Integer lToMonth = 0;
        
        try
        {
            
            setSessionInfo(inputMap);
            
            lStrPpoNo = StringUtility.getParameter("txtPnsnerPPONo", request);
            
            lFrmMM = StringUtility.getParameter("txtfromMM", request);
            lFrmYYYY = StringUtility.getParameter("txtfromYYYY", request);
            
            lToMM = StringUtility.getParameter("txtToMM", request);
            lToYYYY = StringUtility.getParameter("txtToYYYY", request);
            
            if(lFrmYYYY != null && lFrmYYYY.length() > 0 && lFrmMM != null && lFrmMM.length() > 0)
            {
            	lFromMonth = Integer.parseInt(lFrmYYYY+""+lFrmMM);
            }
            
            if(lToYYYY != null && lToYYYY.length() > 0 && lToMM != null && lToMM.length() > 0)
            {
            	lToMonth = Integer.parseInt(lToYYYY+""+lToMM);
            }
            
            CommonReportDAO lObjReportDAO = new CommonReportDAOImpl(serv.getSessionFactory());
            
            /// Getting Report Header Details
            lRptHeaderLst = lObjReportDAO.getLedgerRptHeaderDtl(lStrPpoNo, gStrLocCode, gLngLangId);
            
            // Getting Pensioners Ledger Report Details
            lRptDtlsLst = lObjReportDAO.getLedgerRptDtl(lStrPpoNo,lFromMonth,lToMonth);
            
            List linnerLst = null;            
            Double ldRcvryAmt = 0D;
            
            Integer loldMonth = 0;
            Integer lnxtwMonth = 0;
            int lreuse = 0;
            String lStrBillType = null;
            
            if(lRptHeaderLst != null && lRptHeaderLst.size() > 0) // If PPO Case is not Approved
            {
	            for(int i=0;i<lRptDtlsLst.size();i++)
	            {
	                Object ltempobj[] =  (Object[]) lRptDtlsLst.get(i);
	                
	                lStrBillType = ltempobj[0].toString().trim();
	                lnxtwMonth = Integer.valueOf(ltempobj[1].toString().trim());
	                
	                if(i==0)
	                {
	                    linnerLst = new ArrayList();
	                }
	                else if(loldMonth.intValue() != lnxtwMonth.intValue())
	                {
	                    linnerLst.add(17,Math.round(Float.parseFloat(lGrossAmt.toString())));
	                    linnerLst.add(18,Math.round(Float.parseFloat(lTotDedAmt.toString())));
	                    lNetAmt = lGrossAmt - lTotDedAmt;
	                    linnerLst.add(19,Math.round(Float.parseFloat(lNetAmt.toString())));
	                    louterLst.add(linnerLst);
	                    ldRcvryAmt = 0D;
	                    lreuse = 0;
	                    linnerLst = new ArrayList();
	                }
	                else if(loldMonth.intValue() == lnxtwMonth.intValue())
	                {
	                    lreuse = 1;
	                }
	                
	                if(lreuse == 0)
	                {
	                    lGrossAmt = 0D;
	                    linnerLst.add(0, (ltempobj[1] != null ? ltempobj[1] : "0")); // For Month
	                    
	                    linnerLst.add(1, (ltempobj[2] != null ? Math.round(Float.parseFloat(ltempobj[2].toString())) : "0")); // Basic
	                    lGrossAmt = Double.valueOf(linnerLst.get(1).toString()); // Basic
	                    
	                    linnerLst.add(2, (ltempobj[3] != null ? Math.round(Float.parseFloat(ltempobj[3].toString())) : "0")); // pcut
	                    lGrossAmt -= Double.valueOf(linnerLst.get(2).toString()); // pcut
	                    
	                    linnerLst.add(3, (ltempobj[4] != null ? Math.round(Float.parseFloat(ltempobj[4].toString())) : "0")); // DP Amt
	                    lGrossAmt += Double.valueOf(linnerLst.get(3).toString()); // DP Amt
	                    
	                    linnerLst.add(4, (ltempobj[5] != null ? Math.round(Float.parseFloat(ltempobj[5].toString())) : "0")); // TI Amt
	                    lGrossAmt += Double.valueOf(linnerLst.get(4).toString()); // TI Amt
	                    
	                    linnerLst.add(5, (ltempobj[6] != null ? Math.round(Float.parseFloat(ltempobj[6].toString())) : "0")); //MA Amt
	                    lGrossAmt += Double.valueOf(linnerLst.get(5).toString()); // MA Amt
	                    
	                    linnerLst.add(6, (ltempobj[7] != null ? Math.round(Float.parseFloat(ltempobj[7].toString())) : "0")); // CVP Monthly
	                    lGrossAmt -= Double.valueOf(linnerLst.get(6).toString()); // CVP Monthly
	                    
	                    linnerLst.add(7, (ltempobj[8] != null ? Math.round(Float.parseFloat(ltempobj[8].toString())) : "0")); // PP
	                    lGrossAmt += Double.valueOf(linnerLst.get(7).toString()); // PP Amt 
	                    
	                    linnerLst.add(8, (ltempobj[9] != null ? Math.round(Float.parseFloat(ltempobj[9].toString())) : "0")); // IR
	                    lGrossAmt += Double.valueOf(linnerLst.get(8).toString()); // IR Amt
	                    
	                    linnerLst.add(9, (ltempobj[10] != null ? Math.round(Float.parseFloat(ltempobj[10].toString())) : "0")); // Arrear Amt
	                    lGrossAmt += Double.valueOf(linnerLst.get(9).toString()); // Arrear Amt
	                    
	                    linnerLst.add(10, (ltempobj[11] != null ? Math.round(Float.parseFloat(ltempobj[11].toString())) : "0")); // TI Arrear
	                    lGrossAmt += Double.valueOf(linnerLst.get(10).toString()); // Ti Arrear
	                    
	                    linnerLst.add(11, (ltempobj[12] != null ? Math.round(Float.parseFloat(ltempobj[12].toString())) : "0")); // SP Cut
	                    lGrossAmt -= Double.valueOf(linnerLst.get(11).toString()); // SP Cut Amt
	                    
	                    linnerLst.add(12, (ltempobj[13] != null && lStrBillType == "CVP" ? Math.round(Float.parseFloat(ltempobj[13].toString())) : "0")); // CVP Bill Amt
	                    lGrossAmt += Double.valueOf(linnerLst.get(12).toString()); // CVP Bill Amt
	                    
	                    linnerLst.add(13, (ltempobj[13] != null && lStrBillType == "DCRG" ? Math.round(Float.parseFloat(ltempobj[13].toString())) : "0")); // DCRG Bill Amt
	                    lGrossAmt += Double.valueOf(linnerLst.get(13).toString()); // DCRG Bill Amt
	                    
	                    linnerLst.add(14, (ltempobj[13] != null && lStrBillType == "MR" ? Math.round(Float.parseFloat(ltempobj[13].toString())) : "0")); // MR Bill Amt
	                    lGrossAmt += Double.valueOf(linnerLst.get(14).toString()); // MR Bill Amt
	                    
	                    if(lreuse == 0)
	                    {
	                        if(ltempobj[14] != null)
	                        {
	                            linnerLst.add(15,Math.round(Float.parseFloat(ltempobj[14].toString()))); // IT Cut
	                        }
	                        else
	                        {
	                            linnerLst.add(15,"0");
	                        }
	                        lTotDedAmt += Double.valueOf(linnerLst.get(15).toString()); // IT Cut
	                    }
	                    
	                    if(ltempobj[15] != null) // Other Recovery Amt
	                    {
	                        ldRcvryAmt += Double.valueOf(ltempobj[15].toString());
	                        linnerLst.add(16, Math.round(Float.parseFloat(ldRcvryAmt.toString())));
	                    }
	                    else
	                    {
	                        linnerLst.add(16, Math.round(Float.parseFloat(ldRcvryAmt.toString())));
	                    }
	                    lTotDedAmt += Double.valueOf(linnerLst.get(16).toString()); // Other Recovery Amt
	                }
	                
	                if(lreuse == 1)
	                {
	                    if(ltempobj[13] != null && lStrBillType.equalsIgnoreCase("CVP"))
	                    {
	                        linnerLst.set(12,Math.round(Float.parseFloat(ltempobj[13].toString())));
	                        lGrossAmt += Double.valueOf(linnerLst.get(12).toString()); // CVP Bill Amt
	                    }
	                                        
	                    if(ltempobj[13] != null && lStrBillType.equalsIgnoreCase("DCRG"))
	                    {
	                        linnerLst.set(13,Math.round(Float.parseFloat(ltempobj[13].toString())));
	                        lGrossAmt += Double.valueOf(linnerLst.get(13).toString()); // DCRG Bill Amt
	                    }
	                    
	                    
	                    if(ltempobj[13] != null && lStrBillType.equalsIgnoreCase("MR"))
	                    {
	                        linnerLst.set(14,Math.round(Float.parseFloat(ltempobj[13].toString())));
	                        lGrossAmt += Double.valueOf(linnerLst.get(14).toString()); // MR Bill Amt
	                    }
	                    
	                    if(ltempobj[15] != null) // Other Recovery Amt
	                    {
	                        ldRcvryAmt += Double.valueOf(ltempobj[15].toString());
	                        linnerLst.set(16, Math.round(Float.parseFloat(ldRcvryAmt.toString())));
	                    }
	                    else
	                    {
	                        linnerLst.set(16, Math.round(Float.parseFloat(ldRcvryAmt.toString())));
	                    }
	                    lTotDedAmt += Double.valueOf(linnerLst.get(16).toString()); // Other Recovery Amt
	                }
	                                
	                loldMonth = Integer.parseInt(linnerLst.get(0).toString().trim());
	                
	                if(i==((lRptDtlsLst.size())-1))
	                {
	                    linnerLst.add(17,Math.round(Float.parseFloat(lGrossAmt.toString())));
	                    linnerLst.add(18,Math.round(Float.parseFloat(lTotDedAmt.toString())));
	                    lNetAmt = lGrossAmt - lTotDedAmt;
	                    linnerLst.add(19,Math.round(Float.parseFloat(lNetAmt.toString())));
	                    louterLst.add(linnerLst);                    
	                }
	                
	            }
	            
            }
            
            for(int i=0;i<louterLst.size();i++)
            {
                //System.out.println(" Value [" + i + "]" + louterLst.get(i));
            }
            
            /// Pagination And Reports Details String Creation.
            for(int i=0;i<louterLst.size();)
            {
                /// Report Header Creation.
                lStrRptHdrStr.append(setLedgerRptHdr(lRptHeaderLst));
                
                lStrRptHdrStr.append("\n");
                lStrRptHdrStr.append(getChar(10," "));
                lStrRptHdrStr.append(" Month");
                lStrRptHdrStr.append(getChar(14," "));
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(5," "));
                    
                    // Format Month & Year As MM/YYYY
                    String lStrMMyyyy = lObjinner.get(0).toString().trim();
                    
                    if(lStrMMyyyy.length() == 5)
                    {
                        lStrRptHdrStr.append("0"+lStrMMyyyy.substring(4, 5));
                    }
                    else
                    {
                        lStrRptHdrStr.append(lStrMMyyyy.substring(4, 6));
                    }
                    lStrRptHdrStr.append("/"+lStrMMyyyy.substring(0, 4));
                }
                
                lStrRptHdrStr.append("\n");
                lStrRptHdrStr.append(getChar(10," "));
                lStrRptHdrStr.append(getChar(165, "-"));
                lStrRptHdrStr.append(getChar(15," "));
                
                //              Basic Pension
                lStrRptHdrStr.append("\n");
                lStrRptHdrStr.append(getChar(10," ")+" Basic Pension  (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(1).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(1));
                    lStrRptHdrStr.append(" ");
                }
                
                //              Pension Cut
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" Pension Cut    (-) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(2).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(2));
                    lStrRptHdrStr.append(" ");
                }
                
                //              DP Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" DP Amt.        (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(3).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(3));
                    lStrRptHdrStr.append(" ");
                }
                
                //              TI Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" TI Amt.        (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(4).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(4));
                    lStrRptHdrStr.append(" ");
                }

                //              MA Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" MA Amt.        (-) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(5).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(5));
                    lStrRptHdrStr.append(" ");
                }

                //              CVP Monthly Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" CVP Monthly    (-) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(6).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(6));
                    lStrRptHdrStr.append(" ");
                }

                //              PP Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" PP Amt.        (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(7).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(7));
                    lStrRptHdrStr.append(" ");
                }

                //              IR Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" IR Amt.        (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(8).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(8));
                    lStrRptHdrStr.append(" ");
                }

                //              Other Benefit Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" Other Benefit  (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    //List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(10," ")+"0");  
                    //lStrRptHdrStr.append(lObjinner.get(14));
                    lStrRptHdrStr.append(" ");
                }
                
                //              Arrear Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" Arrear Amt.    (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(9).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(9));
                    lStrRptHdrStr.append(" ");
                }
                
                //              TI Arrear Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" ATI Amt.       (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(10).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(10));
                    lStrRptHdrStr.append(" ");
                }

                //              LTA Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" LTA Amt.       (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    //List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(10," ")+"0");  
                    //lStrRptHdrStr.append(lObjinner.get(14));
                    lStrRptHdrStr.append(" ");
                }

                //              OMR Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" OMR Amt.       (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    //List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(10," ")+"0");  
                    //lStrRptHdrStr.append(lObjinner.get(14));
                    lStrRptHdrStr.append(" ");
                }

                //              SP Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" Special Cut    (-) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(11).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(11));
                    lStrRptHdrStr.append(" ");
                }
                
                //              CVP BILL Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" CVP BILL Amt.  (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(12).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(12));
                    lStrRptHdrStr.append(" ");
                }
                
                //              DCRG BILL Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" DCRG BILL Amt. (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(13).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(13));
                    lStrRptHdrStr.append(" ");
                }                
                
                //              MR Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" MR BILL Amt.   (+) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(14).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(14));
                    lStrRptHdrStr.append(" ");
                }
                
                //              Gross Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" Gross Amount - (A) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(17).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(17));
                    lStrRptHdrStr.append(" ");
                }

                //              IT Deduction
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" IT Ded. Amt.   (-) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(15).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(15));
                    lStrRptHdrStr.append(" ");
                }
                
                //              Other Deduction
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" Other Ded. Amt.(-) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(16).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(16));
                    lStrRptHdrStr.append(" ");
                }
                
                //              Total Deduction Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" Total Ded.   - (B) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(18).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(18));
                    lStrRptHdrStr.append(" ");
                }
                
                lStrRptHdrStr.append("\n");
                lStrRptHdrStr.append(getChar(10," "));
                lStrRptHdrStr.append(getChar(165, "-"));
                lStrRptHdrStr.append(getChar(15," "));
                
                //              Net Amount
                lStrRptHdrStr.append("\n\n");
                lStrRptHdrStr.append(getChar(10," ")+" Net Amount (A - B) ");
                
                for(int j=0;j<louterLst.size();j++)
                {
                    List lObjinner = (List) louterLst.get(j);
                    lStrRptHdrStr.append(getChar(11-(lObjinner.get(19).toString().length())," "));  
                    lStrRptHdrStr.append(lObjinner.get(19));
                    lStrRptHdrStr.append(" ");
                }
                
                
                // End Report line.
                lStrRptHdrStr.append("\n");
                lStrRptHdrStr.append(getChar(10," "));
                lStrRptHdrStr.append(getChar(165, "-"));
                lStrRptHdrStr.append(getChar(15," "));
                
                i = i + 12;  // Go to Next Page After 12 Colomn Display.
            }                   
            
            //        Report  Footer .
            
            if(louterLst.size() > 0 )
            {
	            lStrRptHdrStr.append("\n\n\n\n\n\n");
	            lStrRptHdrStr.append(getChar(20," "));
	            lStrRptHdrStr.append("Total Amount Paid ( Without CVP and Graduity ) Rs.");
	            lStrRptHdrStr.append("\n\n");
	            lStrRptHdrStr.append(getChar(20," "));
	            lStrRptHdrStr.append("Ruppes ");
	            
	            Object lObject[] = (Object[]) lRptHeaderLst.get(0);            
	            lStrRptHdrStr.append(getChar((150-(lObject[0].toString().length()))," "));
	            lStrRptHdrStr.append(lObject[0]);
            }
            else
            {
            	lStrRptHdrStr.append("No Data Found. . .");
            }
	            
            inputMap.put("HeaderStr", lStrRptHdrStr.toString());
            resObj.setResultValue(inputMap);
            resObj.setViewName("pensionLedgerRptResult");
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }        
        gLogger.info("Come To in method getPensionLedgerReportData....");
        return resObj;        
    }
    
    /**
     * Pension Ledger Report Header Creation. 
     * @param lRptHeaderLst
     * @return
     */
    private String setLedgerRptHdr(List lRptHeaderLst)
    {
        StringBuffer lStrRptHdrStr = new StringBuffer();
        
        if(lRptHeaderLst != null && lRptHeaderLst.size() > 0)
        {
            Object lObject[] = (Object[]) lRptHeaderLst.get(0);
            lStrRptHdrStr.append("\n\n\n\n");
            
            // Date
            lStrRptHdrStr.append(getChar(10," "));
            lStrRptHdrStr.append("Date :    ");
            lStrRptHdrStr.append(new SimpleDateFormat("dd/MM/yyyy").format(gDtCurrDt));
            
            // Header
            lStrRptHdrStr.append(getChar(40," "));
            lStrRptHdrStr.append(lObject[0]);
            lStrRptHdrStr.append(getChar((80-(lObject[0].toString().length()))," "));
            lStrRptHdrStr.append("\n");
            
            // Page
            lStrRptHdrStr.append(getChar(10," "));
            lStrRptHdrStr.append("Page :    ");
            lStrRptHdrStr.append("1\n");
            lStrRptHdrStr.append(getChar(120," "));
            
            // Add 1
            lStrRptHdrStr.append(getChar(50," "));
            if(lObject[1] != null && lObject[1].toString().length() > 0)
            {
                lStrRptHdrStr.append(lObject[1]);
            }
            else
            {
                lStrRptHdrStr.append("\n");
            }
            
            // Add 2
            lStrRptHdrStr.append(getChar(50," "));
            if(lObject[2] != null && lObject[2].toString().length() > 0)
            {
                lStrRptHdrStr.append(lObject[2]);
            }
            else
            {
                lStrRptHdrStr.append("\n");
            }
            
            // Time & Report Title
            lStrRptHdrStr.append(getChar(10," "));
            lStrRptHdrStr.append("Time :    ");
            lStrRptHdrStr.append(new SimpleDateFormat("hh:mm:ss aaa").format(gDtCurrDt));
            lStrRptHdrStr.append(getChar(45," "));
            lStrRptHdrStr.append("Ledger Report");
            lStrRptHdrStr.append(getChar(65," "));
            lStrRptHdrStr.append("(All Amount In Rs.)");
                            
            lStrRptHdrStr.append("\n");
            lStrRptHdrStr.append(getChar(10," "));
            lStrRptHdrStr.append(getChar(165, "-"));
            lStrRptHdrStr.append(getChar(15," "));
            
            lStrRptHdrStr.append("\n");
            lStrRptHdrStr.append(getChar(10," "));
            lStrRptHdrStr.append(" Pensioner's Name");
            lStrRptHdrStr.append(getChar(9," "));
            lStrRptHdrStr.append(lObject[3].toString());
            lStrRptHdrStr.append(getChar((50-(lObject[3].toString().length()))," "));
            lStrRptHdrStr.append("Bank Name");
            lStrRptHdrStr.append(getChar(13," "));
            
            if(lObject[6] != null && lObject[6].toString().length() > 0)
            {
            	lStrRptHdrStr.append(lObject[6]);
            }
            
            lStrRptHdrStr.append("\n");
            
            lStrRptHdrStr.append("\n");
            lStrRptHdrStr.append(getChar(10," "));
            lStrRptHdrStr.append(" PPO No.");
            lStrRptHdrStr.append(getChar(18," "));
            lStrRptHdrStr.append(lObject[4]);
            lStrRptHdrStr.append(getChar((50-(lObject[4].toString().length()))," "));
            lStrRptHdrStr.append("Branch Name");
            lStrRptHdrStr.append(getChar(11," "));
            
            if(lObject[7] != null && lObject[7].toString().length() > 0)
            {
            	lStrRptHdrStr.append(lObject[7]);
            }
            
            lStrRptHdrStr.append("\n");
            
            lStrRptHdrStr.append("\n");
            lStrRptHdrStr.append(getChar(10," "));
            lStrRptHdrStr.append(" Scheme");
            lStrRptHdrStr.append(getChar(19," "));
            
            if(lObject[5] != null && lObject[5].toString().length() > 0)
            {
            	lStrRptHdrStr.append(lObject[5]);
            }
            
            lStrRptHdrStr.append(getChar((50-(lObject[5].toString().length()))," "));
            lStrRptHdrStr.append("Account No.");
            lStrRptHdrStr.append(getChar(11," "));
            
            if(lObject[8] != null && lObject[8].toString().length() > 0)
            {
            	lStrRptHdrStr.append(lObject[8]);
            }
            
            lStrRptHdrStr.append("\n");
        }       
        
        return lStrRptHdrStr.toString();
    }
    
    public ResultObject getStickerReport(Map inputMap)
    {
    	gLogger.info("IN SERVICE getStickerReport >>>>>>>>>>>>>>>>>>>>");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrPenCode = null;
        String lName = null;
        String lForMonth = null;
        Double lRedPen = 0D;
        Double lPensionCut = 0D;
        String lStrFromDate1 = null;
    	String lStrToDate1 = null;
    	String lStrFromDate = null;
    	String lStrToDate = null;
        StringBuffer sbFinal = new StringBuffer();
        try{
        	
        	//this report will be for a particular range of dates 
        	//where we have to track data for paid date in those dates....
        	
        	lStrFromDate1 = StringUtility.getParameter("txtFromDate", request);
        	lStrToDate1 = StringUtility.getParameter("txtToDate", request);
        	
        	if(!"".equals(lStrFromDate1.trim()) && lStrFromDate1.trim().length() > 0)
        	{
        		lStrFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(lStrFromDate1));
        	}

        	if(!"".equals(lStrToDate1.trim()) && lStrToDate1.trim().length() > 0)
        	{
        		lStrToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(lStrToDate1));
        	}
        	else
        	{
        		lStrToDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
        	}
        	
        	// get VOs of rqst hdr where paid date between thes dates
        	CommonReportDAOImpl lObjCommon = new CommonReportDAOImpl(serv.getSessionFactory());
        	List<TrnPensionRqstHdr> lLstRqstHdr = lObjCommon.getPaidRqstList(lStrFromDate,lStrToDate);
        	gLogger.info("lLstRqstHdr.size()"+lLstRqstHdr.size());
        	int temp = 0;
        	for(int i=0;i<lLstRqstHdr.size();i++)
        	{
        		TrnPensionRqstHdr lObjRqstHdr = lLstRqstHdr.get(i);        	
        		
        		//do all computation for this pensioner...even prepare the printstring
        		lStrPenCode = lObjRqstHdr.getPensionerCode();
        		MstPensionerHdrDAOImpl lObjMstDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
        		lName = lObjMstDAO.getName(lStrPenCode);
        		
        		ArrayList lArrBillDtls = lObjCommon.getBilldtls(lStrPenCode);
        		if(lArrBillDtls != null && lArrBillDtls.size()>0)
        		{
        			lRedPen = Double.parseDouble(lArrBillDtls.get(0).toString());
            		lForMonth = lArrBillDtls.get(1).toString();
        		}
        		
        		lPensionCut = lObjCommon.getPensionCut(lStrPenCode, lForMonth);
        		
        		if(i%2 == 0)
        		{
        			//start of a new page
        			sbFinal.append("<div><pre>");
        			sbFinal.append(printRecordForStickerRpt(lObjRqstHdr,lName,lRedPen,lForMonth,lPensionCut));
        		}
        		else
        		{
        			//end of page 
        			sbFinal.append(printRecordForStickerRpt(lObjRqstHdr,lName,lRedPen,lForMonth, lPensionCut));
        			sbFinal.append("</pre></div>");
        		}
        		temp=i;
        	}
        	//System.out.println("temp"+temp);
        	if(temp%2 == 0)
        	{
        		sbFinal.append("</pre></div>");
        	}
        	
        	//inputMap.put("DisplayString", sbFinal);
        	inputMap.put("PrintString", sbFinal.toString());
        	inputMap.put("DisplayString", sbFinal.toString().replace("</pre></div>", "").replace("<div><pre>", ""));
        	//System.out.println("THE FINAL STRING IS:");
        	//System.out.println(sbFinal);
        	
        	resObj.setResultValue(inputMap);
            resObj.setViewName("stickerReport");
        	
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }        
        return resObj;        
    }
    
    public String printRecordForStickerRpt(TrnPensionRqstHdr lObjRqstHdr, String lName, Double lRedPen, String lForMonth, Double lPensionCut) throws Exception
    {
    	StringBuffer sbRecord = new StringBuffer();
    	String lCommDate = null;
    	String lRestDate = null;
    	String lPaidDate = null;
    	Double lCalcRed = 0D;
    	Double lDPAmt = 0D;
    	Double lTIAmt = 0D;
    	Double lTotal = 0D;
    	Double lBasic = 0D;
    	Double lCVPMnth = 0D;
    	Double lMA = 0D;
    	Double lDPPer = 0D;
    	Double lTIPer = 0D;
		try
		{
			DecimalFormat formatter = new DecimalFormat("##0.00");
			formatter.setDecimalSeparatorAlwaysShown(true);
			
			// compute no of days in the formonth
			String lStrforMonth = lForMonth.substring(4, 6);
			String lStrforYear = lForMonth.substring(0, 4);
			Calendar myCal     = Calendar.getInstance();
			myCal.set(Calendar.MONTH, Integer.parseInt(lStrforMonth) - 1);
	        myCal.set(Calendar.YEAR, Integer.parseInt(lStrforYear));
	        myCal.set(Calendar.DAY_OF_MONTH, 1); 
			int lDays = myCal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String lDate = lDays+"/"+lStrforMonth+"/"+lStrforYear;
			String finaldate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("dd/MM/yyy").parse(lDate));
			
			sbRecord.append("\r\n");
			
			sbRecord.append("  PPO No. ");
			sbRecord.append(lObjRqstHdr.getPpoNo());
			sbRecord.append(getChar((29 - lObjRqstHdr.getPpoNo().length())," "));
			sbRecord.append(" !");
			sbRecord.append("  PPO No. ");
			sbRecord.append(lObjRqstHdr.getPpoNo());
			sbRecord.append(getChar((29 - lObjRqstHdr.getPpoNo().length())," "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  Name : ");
			sbRecord.append(lName);
			sbRecord.append(getChar((30 - lName.length())," "));
			sbRecord.append(" !");
			sbRecord.append("  Name : ");
			sbRecord.append(lName);
			sbRecord.append(getChar((30 - lName.length())," "));
			sbRecord.append("\r\n");
			
			lCommDate = new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getCommensionDate());
			sbRecord.append("  Pension St. Dt.: ");
			sbRecord.append(lCommDate);
			sbRecord.append(getChar((20 - lCommDate.length())," "));
			sbRecord.append(" !");
			sbRecord.append("  Pension St. Dt.:  ");
			sbRecord.append(lCommDate);
			sbRecord.append(getChar((20 - lCommDate.length())," "));
			sbRecord.append("\r\n");
			
			lBasic = Double.parseDouble(lObjRqstHdr.getBasicPensionAmount().toString());
			sbRecord.append(String.format("%11s", formatter.format(lBasic)));
			sbRecord.append("  Basic Pension ");
			sbRecord.append(getChar(12," "));
			sbRecord.append(" !");
			sbRecord.append(String.format("%11s", formatter.format(lBasic)));
			sbRecord.append("  Basic Pension ");
			sbRecord.append(getChar(12," "));
			sbRecord.append("\r\n");
			
			lCVPMnth = Double.parseDouble(lObjRqstHdr.getCvpMonthlyAmount().toString());
			sbRecord.append(String.format("%11s", formatter.format(lCVPMnth)));
			sbRecord.append("  Monthly CVP ");
			sbRecord.append(getChar(14," "));
			sbRecord.append(" !");
			sbRecord.append(String.format("%11s", formatter.format(lCVPMnth)));
			sbRecord.append("  Monthly CVP ");
			sbRecord.append(getChar(14," "));
			sbRecord.append("\r\n");
			
			lDPPer = Double.parseDouble(lObjRqstHdr.getDpPercent().toString());
			lDPAmt = lBasic * lDPPer / 100;
			
			lCalcRed = lBasic - lPensionCut + lDPAmt - lCVPMnth;
			sbRecord.append(String.format("%11s", formatter.format(lCalcRed)));
			sbRecord.append("  Red. Pen. WEF 01/11/2005");
			sbRecord.append(getChar(2," "));
			sbRecord.append(" !");
			sbRecord.append(String.format("%11s", formatter.format(lCalcRed)));
			sbRecord.append("  Red. Pen. WEF 01/11/2005");
			sbRecord.append(getChar(2," "));
			sbRecord.append("\r\n");
			
			//lDPPer = Double.parseDouble(lObjRqstHdr.getDpPercent().toString());
			//lDPAmt = lBasic * lDPPer / 100;
			sbRecord.append(String.format("%11s", formatter.format(lDPAmt)));
			sbRecord.append("  DA. Pension ");
			sbRecord.append(getChar(14," "));
			sbRecord.append(" !");
			sbRecord.append(String.format("%11s", formatter.format(lDPAmt)));
			sbRecord.append("  DA. Pension ");
			sbRecord.append(getChar(14," "));
			sbRecord.append("\r\n");
			
			lTIPer = Double.parseDouble(lObjRqstHdr.getTiPercent().toString());
			lTIAmt = (lBasic + lDPAmt) * lTIPer / 100;
			sbRecord.append(String.format("%11s", formatter.format(lTIAmt)));
			sbRecord.append("  TI ("+lObjRqstHdr.getTiPercent()+" %) ");
			sbRecord.append(getChar(16," "));
			sbRecord.append(" !");
			sbRecord.append(String.format("%11s", formatter.format(lTIAmt)));
			sbRecord.append("  TI ("+lObjRqstHdr.getTiPercent()+" %) ");
			sbRecord.append(getChar(16," "));
			sbRecord.append("\r\n");
			
			lMA = Double.parseDouble(lObjRqstHdr.getMedicalAllowenceAmount().toString());
			sbRecord.append(String.format("%11s", formatter.format(lMA)));
			sbRecord.append("  MA ");
			sbRecord.append(getChar(23," "));
			sbRecord.append(" !");
			sbRecord.append(String.format("%11s", formatter.format(lMA)));
			sbRecord.append("  MA ");
			sbRecord.append(getChar(23," "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  -----------------");
			sbRecord.append(getChar(20, " "));
			sbRecord.append(" !");
			sbRecord.append("  -----------------");
			sbRecord.append(getChar(20, " "));
			sbRecord.append("\r\n");
			
			lTotal = lCalcRed + lTIAmt + lMA;
			sbRecord.append(String.format("%11s", formatter.format(lTotal)));
			sbRecord.append("  Total ");
			sbRecord.append(getChar(20," "));
			sbRecord.append(" !");
			sbRecord.append(String.format("%11s", formatter.format(lTotal)));
			sbRecord.append("  Total ");
			sbRecord.append(getChar(20," "));
			sbRecord.append("\r\n");
			
			lRestDate = new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getCvpRestorationDate());
			sbRecord.append("  Restoration Date  "+lRestDate);
			sbRecord.append(getChar((19 - lRestDate.length())," "));
			sbRecord.append(" !");
			sbRecord.append("  Restoration Date  "+lRestDate);
			sbRecord.append(getChar((19 - lRestDate.length())," "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  First Payment made as under : ");
			sbRecord.append(getChar(7," "));
			sbRecord.append(" !");
			sbRecord.append("  First Payment made as under : ");
			sbRecord.append(getChar(7," "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  Pension WEF "+lCommDate+" To "+finaldate);
			sbRecord.append(getChar(1," "));
			sbRecord.append(" !");
			sbRecord.append("  Pension WEF "+lCommDate+" To "+finaldate);
			sbRecord.append(getChar(1," "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  Pay Rs.: ");
			sbRecord.append(String.format("%18s", formatter.format(lRedPen)));
			sbRecord.append(getChar(10," "));
			sbRecord.append(" !");
			sbRecord.append("  Pay Rs.: ");
			sbRecord.append(String.format("%18s", formatter.format(lRedPen)));
			sbRecord.append(getChar(10," "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  By T.C.: ");
			sbRecord.append(String.format("%18s", formatter.format(0.00)));
			sbRecord.append(getChar(10," "));
			sbRecord.append(" !");
			sbRecord.append("  By T.C.: ");
			sbRecord.append(String.format("%18s", formatter.format(0.00)));
			sbRecord.append(getChar(10," "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  By T.C.: ");
			sbRecord.append(String.format("%18s", formatter.format(0.00)));
			sbRecord.append(getChar(10," "));
			sbRecord.append(" !");
			sbRecord.append("  By T.C.: ");
			sbRecord.append(String.format("%18s", formatter.format(0.00)));
			sbRecord.append(getChar(10," "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  Total Rs.: ");
			sbRecord.append(String.format("%16s", formatter.format(lRedPen)));
			sbRecord.append(getChar(10," "));
			sbRecord.append(" !");
			sbRecord.append("  Total Rs.: ");
			sbRecord.append(String.format("%16s", formatter.format(lRedPen)));
			sbRecord.append(getChar(10," "));
			sbRecord.append("\r\n");
			
			sbRecord.append(getChar(39, " "));
			sbRecord.append(" !");
			sbRecord.append(getChar(39, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  Comm. Pension ");
			sbRecord.append(String.format("%13s", formatter.format((lObjRqstHdr.getCvpAmount() != null)?lObjRqstHdr.getCvpAmount():0)));
			sbRecord.append(getChar(10, " "));
			sbRecord.append(" !");
			sbRecord.append("  Comm. Pension ");
			sbRecord.append(String.format("%13s", formatter.format((lObjRqstHdr.getCvpAmount() != null)?lObjRqstHdr.getCvpAmount():0)));
			sbRecord.append(getChar(10, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  Comm. (By TC) ");
			sbRecord.append(getChar(23, " "));
			sbRecord.append(" !");
			sbRecord.append("  Comm. (By TC) ");
			sbRecord.append(getChar(23, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  Total Commu.  ");
			sbRecord.append(String.format("%13s", formatter.format((lObjRqstHdr.getCvpAmount() != null)?lObjRqstHdr.getCvpAmount():0)));
			sbRecord.append(getChar(10, " "));
			sbRecord.append(" !");
			sbRecord.append("  Total Commu.  ");
			sbRecord.append(String.format("%13s", formatter.format((lObjRqstHdr.getCvpAmount() != null)?lObjRqstHdr.getCvpAmount():0)));
			sbRecord.append(getChar(10, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append(getChar(39, " "));
			sbRecord.append(" !");
			sbRecord.append(getChar(39, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  DCRG (Cash) ");
			sbRecord.append(String.format("%15s", formatter.format((lObjRqstHdr.getDcrgAmount() != null)?lObjRqstHdr.getDcrgAmount():0)));
			sbRecord.append(getChar(10, " "));
			sbRecord.append(" !");
			sbRecord.append("  DCRG (Cash) ");
			sbRecord.append(String.format("%15s", formatter.format((lObjRqstHdr.getDcrgAmount() != null)?lObjRqstHdr.getDcrgAmount():0)));
			sbRecord.append(getChar(10, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  DCRG (By TC) ");
			sbRecord.append(getChar(24, " "));
			sbRecord.append(" !");
			sbRecord.append("  DCRG (By TC) ");
			sbRecord.append(getChar(24, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  DCRG (By TC) ");
			sbRecord.append(getChar(24, " "));
			sbRecord.append(" !");
			sbRecord.append("  DCRG (By TC) ");
			sbRecord.append(getChar(24, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  Total DCRG Rs. ");
			sbRecord.append(String.format("%12s", formatter.format((lObjRqstHdr.getDcrgAmount() != null)?lObjRqstHdr.getDcrgAmount():0)));
			sbRecord.append(getChar(10, " "));
			sbRecord.append(" !");
			sbRecord.append("  Total DCRG Rs. ");
			sbRecord.append(String.format("%12s", formatter.format((lObjRqstHdr.getDcrgAmount() != null)?lObjRqstHdr.getDcrgAmount():0)));
			sbRecord.append(getChar(10, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append(getChar(39, " "));
			sbRecord.append(" !");
			sbRecord.append(getChar(39, " "));
			sbRecord.append("\r\n");
			
			lPaidDate = new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getPaidDate());
			sbRecord.append("  Paid on : "+lPaidDate);
			sbRecord.append(getChar((27- lPaidDate.toString().length())," "));
			sbRecord.append(" !");
			sbRecord.append("  Paid on : "+lPaidDate);
			sbRecord.append(getChar((27- lPaidDate.length())," "));
			sbRecord.append("\r\n");
			
			sbRecord.append(getChar(39, " "));
			sbRecord.append(" !");
			sbRecord.append(getChar(39, " "));
			sbRecord.append("\r\n");
			
			sbRecord.append("  D.A.(F.P.)       T.O. (Pen)Ahmedabad ");
			sbRecord.append(" !");
			sbRecord.append("  D.A.(F.P.)       T.O. (Pen)Ahmedabad ");
			sbRecord.append("\r\n");
			
			sbRecord.append(getChar(80, "-"));
			sbRecord.append("\r\n");
		}
		catch(Exception e)
		{
			gLogger.error("Error is.:"+e, e);
			throw(e);
		}
		return sbRecord.toString();
    }
    
    public ResultObject getPPORegisterReport(Map inputMap)
    {
    	
    	gLogger.info(">>>>>>>>>>>>>>>>>>>>> in method getPPORegisterReport");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrPenCode = null;
        String lName = null;
        String lDesgName = null;
        String lForMonth = null;
        Double lRedPen = 0D;
        Double lPensionCut = 0D;
        Date lDtOfDeath = null;
        String lStrFromDate1 = null;
    	String lStrToDate1 = null;
    	String lStrFromDate = null;
    	String lStrToDate = null;
        StringBuffer sbFinal = new StringBuffer();
        try{
        	
        	//this report will be for a particular range of dates 
        	//where we have to track data for paid date in those dates....
        	
        	lStrFromDate1 = StringUtility.getParameter("txtFromDate", request);
        	lStrToDate1 = StringUtility.getParameter("txtToDate", request);
        	
        	if(!"".equals(lStrFromDate1.trim()) && lStrFromDate1.trim().length() > 0)
        	{
        		lStrFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(lStrFromDate1));
        	}

        	if(!"".equals(lStrToDate1.trim()) && lStrToDate1.trim().length() > 0)
        	{
        		lStrToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(lStrToDate1));
        	}
        	else
        	{
        		lStrToDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
        	}
        	
        	// get VOs of rqst hdr where paid date between thes dates
        	CommonReportDAOImpl lObjCommon = new CommonReportDAOImpl(serv.getSessionFactory());
        	List<TrnPensionRqstHdr> lLstRqstHdr = lObjCommon.getPaidRqstList(lStrFromDate,lStrToDate);
        	gLogger.info("lLstRqstHdr.size()"+lLstRqstHdr.size());
        	for(int i=0;i<lLstRqstHdr.size();i++)
        	{
        		TrnPensionRqstHdr lObjRqstHdr = lLstRqstHdr.get(i);        	
        		
        		//do all computation for this pensioner...even prepare the printstring
        		lStrPenCode = lObjRqstHdr.getPensionerCode();
        		MstPensionerHdrDAOImpl lObjMstDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
        		MstPensionerHdr lObjMstHdr = new MstPensionerHdr();
        		lObjMstHdr = lObjMstDAO.getMstPensionerHdrDtls(lStrPenCode);
        		if(lObjMstHdr.getMiddleName() != null)
        		{
        			lName = lObjMstHdr.getFirstName() + " " + lObjMstHdr.getMiddleName()+ " " + lObjMstHdr.getLastName();
        		}
        		else
        		{
        			lName = lObjMstHdr.getFirstName() + " " + lObjMstHdr.getLastName();
        		}
        		lDesgName = lObjMstDAO.getDesigName(Long.parseLong(lObjMstHdr.getDesignation()));
        		lDtOfDeath = lObjMstHdr.getDateOfDeath();
        		
        		ArrayList lArrBillDtls = lObjCommon.getBilldtls(lStrPenCode);
        		if(lArrBillDtls != null && lArrBillDtls.size()>0)
        		{
        			lRedPen = Double.parseDouble(lArrBillDtls.get(0).toString());
            		lForMonth = lArrBillDtls.get(1).toString();
        		}
        		
        		lPensionCut = lObjCommon.getPensionCut(lStrPenCode, lForMonth);
        		
        		List lLstFamDet = lObjCommon.getFamilyDtls(lStrPenCode);
        		
        		sbFinal.append("<div><pre>");
        		sbFinal.append(printRecordForPPORegisterRpt(lObjRqstHdr,lName,lRedPen,lForMonth,lPensionCut,lDesgName,lDtOfDeath,lLstFamDet));
        		sbFinal.append("</pre></div>");
        	}
        	
        	inputMap.put("PrintString", sbFinal.toString());
        	inputMap.put("DisplayString", sbFinal.toString().replace("</pre></div>", "").replace("<div><pre>", ""));
        	//System.out.println("THE FINAL STRING IS:");
        	//System.out.println(sbFinal);
        	
        	resObj.setResultValue(inputMap);
            resObj.setViewName("PPORegisterReport");
        	
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }        
        return resObj;        
    }
    
    public String printRecordForPPORegisterRpt(TrnPensionRqstHdr lObjRqstHdr, String lName, Double lRedPen, String lForMonth, Double lPensionCut, String lDesg, Date lDtOfDeath, List lLstFamDet) throws Exception
    {
    	StringBuffer sbRecord = new StringBuffer();
    	String lCommDate = null;
    	String lPaidDate = null;
    	Double lCalcRed = 0D;
    	Double lDPAmt = 0D;
    	Double lBasic = 0D;
    	Double lCVPMnth = 0D;
    	Double lMA = 0D;
    	Double lDPPer = 0D;
    	
		try
		{
			DecimalFormat formatter = new DecimalFormat("##0.00");
			formatter.setDecimalSeparatorAlwaysShown(true);
			
			// compute no of days in the formonth
			String lStrforMonth = lForMonth.substring(4, 6);
			String lStrforYear = lForMonth.substring(0, 4);
			Calendar myCal     = Calendar.getInstance();
			myCal.set(Calendar.MONTH, Integer.parseInt(lStrforMonth) - 1);
	        myCal.set(Calendar.YEAR, Integer.parseInt(lStrforYear));
	        myCal.set(Calendar.DAY_OF_MONTH, 1); 
			int lDays = myCal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String lDate = lDays+"/"+lStrforMonth+"/"+lStrforYear;
			String finaldate = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("dd/MM/yyy").parse(lDate));
			
			sbRecord.append("\r\n\n");
			
			sbRecord.append(getChar(30, " "));
			sbRecord.append("First Payment Details");
			sbRecord.append(getChar(30, " "));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    PPO No.: ");
			sbRecord.append(lObjRqstHdr.getPpoNo());
			sbRecord.append(getChar((20 - lObjRqstHdr.getPpoNo().length())," "));
			sbRecord.append(" Ent. Reg. No.: ");
			sbRecord.append((lObjRqstHdr.getCaseRegisterNo()!= null)?lObjRqstHdr.getCaseRegisterNo():"      ");	
			//find out wht to be put here
			sbRecord.append(" DT."+new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getPaidDate()));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Sanc Let. No. ");
			sbRecord.append((lObjRqstHdr.getSanctionLetterNo()!= null)?lObjRqstHdr.getSanctionLetterNo():"     ");
			sbRecord.append("  DT.");
			sbRecord.append((lObjRqstHdr.getPpoDate() != null)?new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getPpoDate()):"     ");  
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Name : ");
			sbRecord.append(lName);
			sbRecord.append(getChar((41 - lName.length())," "));
			sbRecord.append("  Desig : ");
			sbRecord.append(lDesg);
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Name of Family Pensioner : ");
			sbRecord.append(lLstFamDet.get(0));
			if(lLstFamDet.size() > 1)
			{
				for(int i=1;i<lLstFamDet.size();i++)
				{
					sbRecord.append("\r\n\n");
					sbRecord.append(getChar(31, " "));
					sbRecord.append(lLstFamDet.get(i));
				}
			}
			sbRecord.append("\r\n\n");
			
			lBasic = Double.parseDouble(lObjRqstHdr.getBasicPensionAmount().toString());
			sbRecord.append("    Basic Pension ");
			sbRecord.append(getChar(12," "));
			sbRecord.append(String.format("%11s", formatter.format(lBasic)));
			lCommDate = new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getCommensionDate());
			sbRecord.append("    Pension Start Dt.: ");
			sbRecord.append(lCommDate);
			sbRecord.append("\r\n\n");
			
			lCVPMnth = Double.parseDouble(lObjRqstHdr.getCvpMonthlyAmount().toString());
			sbRecord.append("    Monthly CVP ");
			sbRecord.append(getChar(14," "));
			sbRecord.append(String.format("%11s", formatter.format(lCVPMnth)));
			sbRecord.append("    Pension Type: ");
			sbRecord.append(lObjRqstHdr.getPensionType());
			sbRecord.append("\r\n\n");
			
			lDPPer = Double.parseDouble(lObjRqstHdr.getDpPercent().toString());
			lDPAmt = lBasic * lDPPer / 100;
			
			lCalcRed = lBasic - lPensionCut + lDPAmt - lCVPMnth;
			sbRecord.append("    Reduced Pension:");
			sbRecord.append(getChar(10," "));
			sbRecord.append(String.format("%11s", formatter.format(lCalcRed)));
			sbRecord.append("    Red. Pen. WEF.: ");
			sbRecord.append((lObjRqstHdr.getCvpDate() != null)?new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getCvpDate()):"     ");
			//get this date value
			sbRecord.append(getChar(2," "));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    DA. Pension ");
			sbRecord.append(getChar(14," "));
			sbRecord.append(String.format("%11s", formatter.format(lDPAmt)));
			sbRecord.append("\r\n\n");
			
			lMA = Double.parseDouble(lObjRqstHdr.getMedicalAllowenceAmount().toString());
			sbRecord.append("    MA ");
			sbRecord.append(getChar(23," "));
			sbRecord.append(String.format("%11s", formatter.format(lMA)));
			sbRecord.append("    F.P. Start Dt.: ");
			if(lDtOfDeath != null)
			{
				sbRecord.append(new SimpleDateFormat("dd/MM/yyyy").format(lDtOfDeath));
			}
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    FP1 Amount: ");
			sbRecord.append(getChar(14," "));
			sbRecord.append(String.format("%11s", formatter.format(lObjRqstHdr.getFp1Amount())));
			sbRecord.append("    F.P. I Upto Date: ");
			sbRecord.append(new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getFp1Date()));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    FP2 Amount: ");
			sbRecord.append(getChar(14," "));
			sbRecord.append(String.format("%11s", formatter.format(lObjRqstHdr.getFp2Amount())));
			sbRecord.append("    F.P. II Upto Date: ");
			sbRecord.append(new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getFp2Date()));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Pension W.E.F.:            "+lCommDate+" To "+finaldate);
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Pay Rs.: ");
			sbRecord.append(getChar(16," "));
			sbRecord.append(String.format("%12s", formatter.format(lRedPen)));
			sbRecord.append("\r\n");
			
			sbRecord.append("    By T.C.: ");
			sbRecord.append(getChar(16," "));
			sbRecord.append(String.format("%12s", formatter.format(0.00)));
			sbRecord.append("\r\n");

			sbRecord.append(getChar(18," "));
			sbRecord.append(String.format("%23s", formatter.format(0.00)));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Total Rs.: ");
			sbRecord.append(getChar(16," "));
			sbRecord.append(String.format("%10s", formatter.format(lRedPen)));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Commuted Pay Rs.: ");
			sbRecord.append(getChar(8," "));
			sbRecord.append(String.format("%11s", formatter.format((lObjRqstHdr.getCvpAmount() != null)?lObjRqstHdr.getCvpAmount():0)));
			sbRecord.append("\r\n");
			
			sbRecord.append("    By T.C.: ");
			sbRecord.append(getChar(16," "));
			sbRecord.append(String.format("%12s", formatter.format(0.00)));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Total Rs.: ");
			sbRecord.append(getChar(14," "));
			sbRecord.append(String.format("%12s", formatter.format((lObjRqstHdr.getCvpAmount() != null)?lObjRqstHdr.getCvpAmount():0)));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    DCRG Pay Rs.: ");
			sbRecord.append(getChar(11," "));
			sbRecord.append(String.format("%12s", formatter.format((lObjRqstHdr.getDcrgAmount() != null)?lObjRqstHdr.getDcrgAmount():0)));
			sbRecord.append("\r\n");
			
			sbRecord.append("    By T.C.: ");
			sbRecord.append(getChar(16," "));
			sbRecord.append(String.format("%12s", formatter.format(0.00)));
			sbRecord.append("\r\n");

			sbRecord.append(getChar(29," "));
			sbRecord.append(String.format("%12s", formatter.format(0.00)));
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    Total DCRG Rs.: ");
			sbRecord.append(getChar(9," "));
			sbRecord.append(String.format("%12s", formatter.format((lObjRqstHdr.getDcrgAmount() != null)?lObjRqstHdr.getDcrgAmount():0)));
			sbRecord.append("\r\n\n");


			lPaidDate = new SimpleDateFormat("dd/MM/yyyy").format(lObjRqstHdr.getPaidDate());
			sbRecord.append("    Paid on : "+lPaidDate);
			sbRecord.append("\r\n\n");
			
			
			sbRecord.append("       D.A.(F.P.) ");
			sbRecord.append(getChar(30, " "));
			sbRecord.append("  Treasury Officer (Pension) ");
			sbRecord.append("\r\n");
			
			sbRecord.append(getChar(55, " "));
			sbRecord.append("  Ahmedabad ");
			sbRecord.append("\r\n\n");
			
			sbRecord.append("    No Event Certificate Received From DY. DIR. ");
			sbRecord.append("\r\n\n");
			sbRecord.append("    F. S. L. A'BAD -----------------------------------  Date ");
			if(lObjRqstHdr.getForm22IssuedDate() != null)
			{
				sbRecord.append(new SimpleDateFormat("dd-MMM-yyyy").format(lObjRqstHdr.getForm22IssuedDate()));
			}
			sbRecord.append("\r\n\n");
			sbRecord.append("    L.P.C. Received From O.S ");
			sbRecord.append("\r\n\n");
			sbRecord.append("    F. S. L. A'BAD -----------------------------------  Date ");
			if(lObjRqstHdr.getLpcIssuedDate() != null)
			{
				sbRecord.append(new SimpleDateFormat("dd-MMM-yyyy").format(lObjRqstHdr.getLpcIssuedDate()));
			}
			sbRecord.append("\r\n\n");
			sbRecord.append("    Non-Remark. Cert. By                                Date ");
			sbRecord.append("\r\n\n");
			sbRecord.append("\r\n");
			
			sbRecord.append("       D.A.(F.P.) ");
			sbRecord.append(getChar(30, " "));
			sbRecord.append("  Treasury Officer (Pension) ");
			sbRecord.append("\r\n");
			
			sbRecord.append(getChar(55, " "));
			sbRecord.append("  Ahmedabad ");
			sbRecord.append("\r\n\n");
			
		}
		catch(Exception e)
		{
			gLogger.error("Error is.:"+e, e);
			throw(e);
		}
		return sbRecord.toString();
    }
    
    /**
     * Pension Case History For Case Forth Tab after Case is Approved.
     * @author Sagar Patel
     * @param inputMap
     * @return ResultObject
     */
    public ResultObject getPensionCaseHistoryRpt(Map inputMap)
    {
    	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        List lRptDtlsLst = null;
        
        String lStrPpoNo = null;
        Integer lFromMonth = 0;
        Integer lToMonth = 0;
        
        
        try
        {
        	lStrPpoNo = StringUtility.getParameter("txtPpoNo", request);
        	        	
            CommonReportDAO lObjReportDAO = new CommonReportDAOImpl(serv.getSessionFactory());
            
            // Getting Pensioners Ledger Report Details
            lRptDtlsLst = lObjReportDAO.getLedgerRptDtl(lStrPpoNo,lFromMonth,lToMonth);
            
            StringBuilder lStrGrant = new StringBuilder();
			
			for(int i=1;i<= lRptDtlsLst.size();i++)
			{
				Object[] lObjectsAry = (Object[]) lRptDtlsLst.get(i-1);
				String lBillCrtDate = null;
				
				if(lObjectsAry[16] != null && lObjectsAry[16].toString().length() > 0)
				{
					lBillCrtDate =  new SimpleDateFormat("dd/MM/yyyy").format(lObjectsAry[16]);
				}
				
				lStrGrant.append("<XMLDOC>");
				lStrGrant.append("<SRNO>");
				lStrGrant.append(i);
				lStrGrant.append("</SRNO>");

				lStrGrant.append("<BILLMNTH>");
				lStrGrant.append(lBillCrtDate);
				lStrGrant.append("</BILLMNTH>");				
				
				lStrGrant.append("<BILLTYPE>");
				lStrGrant.append(lObjectsAry[0].toString());
				lStrGrant.append("</BILLTYPE>");
				
				lStrGrant.append("<BILLAMT>");
				lStrGrant.append(lObjectsAry[13].toString());
				lStrGrant.append("</BILLAMT>");
				lStrGrant.append("</XMLDOC>");
			}			
			
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
			
			//System.out.println("Result   " + lStrResult);
			
			inputMap.put("ajaxKey", lStrResult);
			
            resObj.setViewName("ajaxData");
            resObj.setResultValue(inputMap);
        	
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }        
        return resObj;        
    }
    
    public ResultObject getRecvoerChallanReport(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrPenCode = null;
        String lName = null;
        String lForMonth = null;
        Double lRedPen = 0D;
        Double lPensionCut = 0D;
        String lStrFromDate1 = null;
    	String lStrToDate1 = null;
    	String lStrFromDate = null;
    	String lStrToDate = null;
        StringBuffer sbFinal = new StringBuffer();
        try{
        	
        	lStrFromDate1 = StringUtility.getParameter("txtFromDate", request);
        	lStrToDate1 = StringUtility.getParameter("txtToDate", request);
        	if(!"".equals(lStrFromDate1.trim()) && lStrFromDate1.trim().length() > 0)
        	{
        		lStrFromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(lStrFromDate1));
        		inputMap.put("FromDate",lStrFromDate);
        	}
        	if(!"".equals(lStrToDate1.trim()) && lStrToDate1.trim().length() > 0)
        	{
        		lStrToDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(lStrToDate1));
        	}
        	else
        	{
        		lStrToDate = new SimpleDateFormat("dd-MMM-yyyy").format(DBUtility.getCurrentDateFromDB());
        	}
        	
        	inputMap.put("ToDate",lStrToDate);
        	 String lStrPPONO = "";
        	if(request.getParameter("txtPPONo") != null)
        	{
        		lStrPPONO = request.getParameter("txtPPONo").toString();
        		inputMap.put("ppoNo", lStrPPONO);
        	}
        	
        	CommonReportDAOImpl lObjCommon = new CommonReportDAOImpl(serv.getSessionFactory());
        	Map lReturnMap = lObjCommon.getRecoverChallans(inputMap);
        	PensionCaseDAOImpl lObjCaseDao = new PensionCaseDAOImpl(MstPensionerHdr.class, serv.getSessionFactory());
        	lStrPenCode = lObjCaseDao.getPensionerCodefromPpoNo(lStrPPONO, "APPROVED");
        	MstPensionerHdrDAOImpl lObjMstDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
    		lName = lObjMstDAO.getName(lStrPenCode);
    		String lStrPrint = "";
    		StringBuffer lStrPrintBuffer = new StringBuffer();
    		StringBuffer lstrFooterString = new StringBuffer();
    		lstrFooterString.append("\n");
    		lstrFooterString.append(getChar(2," ")+"DDO Name : \n");
    		lstrFooterString.append(getChar(80," ")+"\n");
    		lstrFooterString.append(getChar(5," ")+"HA"+getChar(14," "));
    		lstrFooterString.append(getChar(5," ")+"ATO"+getChar(13," "));
    		lstrFooterString.append(getChar(5," ")+"TO"+getChar(18," "));
    		lstrFooterString.append("\n");
    		lstrFooterString.append(getChar(3," ")+"Signature"+getChar(8," "));
    		lstrFooterString.append(getChar(3," ")+"Signature"+getChar(10," "));
    		lstrFooterString.append(getChar(3," ")+"Signature"+getChar(10," "));
    		lstrFooterString.append("\n");
    		lstrFooterString.append(getChar(80,"-")+"\n");
    		if(lReturnMap != null)
    		{
    			List<TrnPensionRecoveryDtls> lLstRcvryMCA = (List<TrnPensionRecoveryDtls>) lReturnMap.get("MCA");
    			if(lLstRcvryMCA != null && lLstRcvryMCA.size() > 0)
    			{
    				lStrPrint = PrintChalan(lStrPPONO,lName,lLstRcvryMCA,"MCA");
    				lStrPrintBuffer.append(lStrPrint);
    				lStrPrintBuffer.append(lstrFooterString.toString());
    			}
    			List<TrnPensionRecoveryDtls> lLstRcvryHBA = (List<TrnPensionRecoveryDtls>) lReturnMap.get("HBA");
    			if(lLstRcvryHBA != null && lLstRcvryHBA.size() > 0)
    			{
    				lStrPrint = PrintChalan(lStrPPONO,lName,lLstRcvryHBA,"HBA");
    				lStrPrintBuffer.append(lStrPrint);
    				lStrPrintBuffer.append(lstrFooterString.toString());
    				
    			}
    			List<TrnPensionRecoveryDtls> lLstRcvryOTHRS = (List<TrnPensionRecoveryDtls>) lReturnMap.get("OTHERS");
    			if(lLstRcvryOTHRS != null && lLstRcvryOTHRS.size() > 0)
    			{
    				lStrPrint = PrintChalan(lStrPPONO,lName,lLstRcvryOTHRS,"OTHERS");
    				lStrPrintBuffer.append(lStrPrint);
    				lStrPrintBuffer.append(lstrFooterString.toString());
    			}
    		}
    	
    		//System.out.println(lStrPrintBuffer.toString());
    		inputMap.put("PrintString",lStrPrintBuffer.toString());
        	resObj.setResultValue(inputMap);
            resObj.setViewName("RecoverChalanReport");
        	
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }        
        return resObj;        
    }
    public String PrintChalan(String ppoNo,String Name,List<TrnPensionRecoveryDtls> lObjVoList,String lStrRecoveryType)
    {
    	StringBuffer lSTBufferPrint = new StringBuffer();
    	try
    	{
    		lSTBufferPrint.append(getChar(80,"-")+"\n");
    		lSTBufferPrint.append(getChar(80," ")+"\n");
    		lSTBufferPrint.append(getChar(80," ")+"\n");
    		lSTBufferPrint.append(getChar(2," ")+"PPO Number     :  "+ppoNo+"\n");
    		lSTBufferPrint.append(getChar(2," ")+"Name           :  "+Name+"\n");
    		lSTBufferPrint.append(getChar(2," ")+"Recovery Type  :   "+lStrRecoveryType+"\n");
    		lSTBufferPrint.append(getChar(2," ")+"Account Number :   ");
    		for(int i=0;i<lObjVoList.size();i++)
    		{
    			TrnPensionRecoveryDtls lObjRecvry = lObjVoList.get(i);
    			lSTBufferPrint.append(lObjRecvry.getAccountNumber()+" ");
    			lSTBufferPrint.append(",");
    		}
    		lSTBufferPrint.append("\n");
    		lSTBufferPrint.append(getChar(2," ")+"Head Chargable  "+"\n");
    		lSTBufferPrint.append(getChar(2," ")+"EDP Code"+getChar(2," "));
    		lSTBufferPrint.append(getChar(2," ")+"Major Head"+getChar(2," "));
    		lSTBufferPrint.append(getChar(2," ")+"SubMajor Head"+getChar(2," "));
    		lSTBufferPrint.append(getChar(2," ")+"Minor Head"+getChar(2," "));
    		lSTBufferPrint.append(getChar(2," ")+"Sub Head"+getChar(2," "));
    		lSTBufferPrint.append(getChar(2," ")+"Amount"+"\n");
    		lSTBufferPrint.append(getChar(80," ")+"\n");
    		for(int i=0;i<lObjVoList.size();i++)
    		{
    			TrnPensionRecoveryDtls lObjRecvry = lObjVoList.get(i);
    			lSTBufferPrint.append(getChar(4," ")+lObjRecvry.getEdpCode()+getChar(4," "));
    			lSTBufferPrint.append(getChar(4," ")+lObjRecvry.getMjrhdCode()+getChar(5," "));
    			lSTBufferPrint.append(getChar(6," ")+lObjRecvry.getSubmjrhdCode()+getChar(7," "));
    			lSTBufferPrint.append(getChar(6," ")+lObjRecvry.getMinhdCode()+getChar(6," "));
    			lSTBufferPrint.append(getChar(6," ")+lObjRecvry.getSubhdCode()+getChar(5," "));
    			lSTBufferPrint.append(getChar(4," ")+lObjRecvry.getAmount());
    			lSTBufferPrint.append("\n");
    		}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return lSTBufferPrint.toString();
    }
    private void setSessionInfo(Map inputMap)
    {      
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
        gLngLangId = SessionHelper.getLangId(inputMap);
        gStrLocCode = SessionHelper.getLocationCode(inputMap);
    }  
    
}