package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.tcs.sgv.common.util.reports.IReportConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.billproc.common.service.BillProcConstants;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAO;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
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
import com.tcs.sgv.pension.dao.MonthlyPensionBillDAO;
import com.tcs.sgv.pension.dao.MonthlyPensionBillDAOImpl;
import com.tcs.sgv.pension.dao.NewPensionBillDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionArrearDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionArrearDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPnsncaseRopRltDAO;
import com.tcs.sgv.pension.valueobject.MonthlyPensionBillVO;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeChargable;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

public class MonthlyPensionBillServiceImpl extends ServiceImpl implements MonthlyPensionBillService{

	/* Global Variable for PostId */
    private Long gLngPostId = null;

    /* Global Variable for UserId */
    private Long gLngUserId = null;

    /* Global Variable for LangId */
    private Long gLngLangId = null;
    
    /* Global Variable for Logger Class */
    private Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for Current Date */
    private Date gDate = null;
    
    /* Glonal Variable for Location Code */
	private String gStrLocCode = null;
    
    /**
     * Method to Load the Monthly Pension bill
     * @author Aparna Kansara
     * @param inputMap
     * @return
     */
    public ResultObject loadMonthlyPension(Map inputMap)
	{
    	ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        String lStrLangId=SessionHelper.getLangId(inputMap).toString();
        
        setSessionInfo(inputMap);
        
        BigDecimal lBDUserId = (new BigDecimal(gLngUserId));
        BigDecimal lBDPostId = (new BigDecimal(gLngPostId));
		
		try
		{
			//To populate month combo....
            List<SgvaMonthMst> lObjSgvaMonthMst=new ArrayList<SgvaMonthMst>();
            
            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());            
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		String currDate = sdf.format(gDate);		
    		
    		String currentMonth = currDate.substring(5, 7);
    		String currentYear = currDate.substring(0,4);	
    		
    		inputMap.put("CurrentMonth", currentMonth);
    		inputMap.put("CurrentYear", currentYear);
            
            //Returning VO array...
            lObjSgvaMonthMst=lObjCommonPensionDAO.getSgvaMonthMstVO(lStrLangId);
            
            if(lObjSgvaMonthMst != null)
            {
            	inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMst);
            }
            
        	//To populate year combo....
            List<SgvcFinYearMst> lObjSgvcFinYearMst=new ArrayList<SgvcFinYearMst>();
            
            //Returning VO array...
            lObjSgvcFinYearMst=lObjCommonPensionDAO.getSgvcFinYearMstVO(lStrLangId);
            
            if(lObjSgvcFinYearMst != null)
            {
            	inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMst);
            }
			
            //To  Populate Bank combo...
        	
            List<String> lLstAuditorBankCodeList = lObjCommonPensionDAO.getAuditorBankCodeList(lBDUserId,lBDPostId);
                  	
        	ArrayList<ComboValuesVO> arrBankCode = new ArrayList<ComboValuesVO>();
			if (lLstAuditorBankCodeList!=null && !lLstAuditorBankCodeList.isEmpty()) 
			{	
				Iterator it = lLstAuditorBankCodeList.iterator();
				while(it.hasNext()) 
				{
					ComboValuesVO lObjComboValuesVO= new ComboValuesVO();
					lObjComboValuesVO = (ComboValuesVO)it.next();
					
					arrBankCode.add(lObjComboValuesVO);
				}	
			}
			
        	inputMap.put("ListAuditorBankCode",arrBankCode);            
            
            //To populate HeadCode combo...
			MonthlyPensionBillDAOImpl lObjMonthlyPensionBill = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());

			List listPnsnHeadCode = (List)lObjCommonPensionDAO.getAllHeadCode();
			
			if(listPnsnHeadCode != null)
			{
				inputMap.put("listHeadCode", listPnsnHeadCode);
			}
			
			//To populate Scheme combo...
			List listPnsnScheme = (List)lObjMonthlyPensionBill.getAllScheme(gLngLangId);
			
			if(listPnsnScheme != null)
			{
				inputMap.put("listScheme", listPnsnScheme);
			}
		}
		catch(Exception e)
		{
            gLogger.error("Error is :" + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
		}
		resObj.setResultValue(inputMap);
		resObj.setViewName("monthlyPension");
		return resObj;
	}
    
    //changes made to give a better report with more details....to be approved by client
   
    public  ResultObject printMonthlyBill(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        StringBuffer sbLine = new StringBuffer();
        int pgCoutner = 1;
        try
        {
            //resObj = serv.executeService("VIEW_SAVED_MONTHLY_BILL", inputMap);
            //inputMap = (Map) resObj.getResultValue();
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            //method calling for Getting header on each new page
           
            List MonthlyPensionList = (List) inputMap.get("MonthlyPensionList");
           
            MonthlyPensionBillVO MonthlyPensionVo = null;
            int k = 0;
            if(MonthlyPensionList != null)
            {
            	 Long[] lLngFinalTotArray = new Long[20];
            	  for(int i=0;i<20;i++)
                  {
            		  lLngFinalTotArray[i] = new Long(0L);
                  } 
            	for (int i = 1; i <= MonthlyPensionList.size(); i++)
                 {
                     MonthlyPensionVo = (MonthlyPensionBillVO) MonthlyPensionList.get(k);
                     if( k % 15 == 0)
                     {
                    	 sbLine.append("<div>");
                    	 sbLine.append("<pre>");
                         sbLine.append(getHeadForMnthlyReport(pgCoutner,inputMap));
                     }
                     Map lTempMap = null;
                     Long[] lLsttot = new Long[20];
                     sbLine.append(printRecord(MonthlyPensionVo,i));
                     if(i!= 1 && i % 15 == 0)
                     {
                    	 lTempMap = getFooterForMnthlyReport(pgCoutner,MonthlyPensionList);
                    	 sbLine.append(lTempMap.get("FooterString"));
                    	 lLsttot =  (Long[]) lTempMap.get("PageTotals");
                    	 for(int tmpCnt =0; tmpCnt<20 ; tmpCnt++)
                    	 {
                    		 lLngFinalTotArray[tmpCnt] = lLngFinalTotArray[tmpCnt]+lLsttot[tmpCnt];
                    	 }
                         pgCoutner = pgCoutner+1;
                         sbLine.append("</pre></div>");
                     }
                     if(i % 15 != 0 && i == MonthlyPensionList.size())
                     {
                    	 lTempMap = getFooterForMnthlyReport(pgCoutner,MonthlyPensionList);
                    	 lLsttot =  (Long[]) lTempMap.get("PageTotals");
                    	 for(int tmpCnt =0; tmpCnt<20 ; tmpCnt++)
                    	 {
                    		 lLngFinalTotArray[tmpCnt] = lLngFinalTotArray[tmpCnt]+lLsttot[tmpCnt];
                    	 }
                    	 sbLine.append(lTempMap.get("FooterString"));
                     }
                     k++;
                 }
            	 if(MonthlyPensionList == null || MonthlyPensionList.size() == 0){
                    sbLine.append(getHeadForMnthlyReport(pgCoutner,inputMap));
                 }
                 sbLine.append(getFinalFooter(lLngFinalTotArray,inputMap));
                 sbLine.append("</pre></div>");
            }
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
           
            inputMap.put("PrintString", sbLine.toString());
            inputMap.put("DisplayString", sbLine.toString().replace("</pre></div>", "").replace("<div><pre>", ""));
            resObj.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }
    public  ResultObject printMonthlyBillForDisplay(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        StringBuffer sbLine = new StringBuffer();
        int pgCoutner = 1;
        try
        {
            //resObj = serv.executeService("VIEW_SAVED_MONTHLY_BILL", inputMap);
            //inputMap = (Map) resObj.getResultValue();
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            //sbLine.append(getChar(132," "));
            //sbLine.append("\r\n");
            //method calling for Getting header on each new page
           
            List MonthlyPensionList = (List) inputMap.get("MonthlyPensionList");
           
            MonthlyPensionBillVO MonthlyPensionVo = null;
            int k = 0;
            if(MonthlyPensionList != null)
            {
            	 Long[] lLngFinalTotArray = new Long[20];
            	  for(int i=0;i<20;i++)
                  {
            		  lLngFinalTotArray[i] = new Long(0L);
                  } 
            	for (int i = 1; i <= MonthlyPensionList.size(); i++)
                 {
                     MonthlyPensionVo = (MonthlyPensionBillVO) MonthlyPensionList.get(k);
                     if( k % 15 == 0)
                     {
                    	 //sbLine.append("<div>");
                    	 //sbLine.append("<pre>");
                         sbLine.append(getHeadForMnthlyReport(pgCoutner,inputMap));
                     }
                     Map lTempMap = null;
                     Long[] lLsttot = new Long[20];
                     sbLine.append(printRecord(MonthlyPensionVo,i));
                     if(i!= 1 && i % 15 == 0)
                     {
                    	 lTempMap = getFooterForMnthlyReport(pgCoutner,MonthlyPensionList);
                    	 sbLine.append(lTempMap.get("FooterString"));
                    	 lLsttot =  (Long[]) lTempMap.get("PageTotals");
                    	 for(int tmpCnt =0; tmpCnt<20 ; tmpCnt++)
                    	 {
                    		 lLngFinalTotArray[tmpCnt] = lLngFinalTotArray[tmpCnt]+lLsttot[tmpCnt];
                    	 }
                         pgCoutner = pgCoutner+1;
                         //sbLine.append("</pre></div>");
                     }
                     if(i % 15 != 0 && i == MonthlyPensionList.size())
                     {
                    	 lTempMap = getFooterForMnthlyReport(pgCoutner,MonthlyPensionList);
                    	 lLsttot =  (Long[]) lTempMap.get("PageTotals");
                    	 for(int tmpCnt =0; tmpCnt<20 ; tmpCnt++)
                    	 {
                    		 lLngFinalTotArray[tmpCnt] = lLngFinalTotArray[tmpCnt]+lLsttot[tmpCnt];
                    	 }
                    	 sbLine.append(lTempMap.get("FooterString"));
                     }
                     k++;
                 }
            	 if(MonthlyPensionList == null || MonthlyPensionList.size() == 0){
                    sbLine.append(getHeadForMnthlyReport(pgCoutner,inputMap));
                 }
                 sbLine.append(getFinalFooter(lLngFinalTotArray,inputMap));
                 //sbLine.append("</pre></div>");
            }
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
           
            inputMap.put("DisplayString", sbLine.toString());
            gLogger.info("DisplayString"+inputMap.get("DisplayString"));
            resObj.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }
    private String getFinalFooter(Long[] lLngFinalTotArray, Map inputMap) throws Exception
    {
    	StringBuffer sbLine = new StringBuffer();
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	try
    	{
	    	String lHeadCode = (String) inputMap.get("HeadCode");
	    	MonthlyPensionBillDAO lObjMonthlyDao = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
	    	RltPensionHeadcodeChargable lObjHeadChargeable = new RltPensionHeadcodeChargable();
	    	lObjHeadChargeable = lObjMonthlyDao.getRltPensionHeadcodeChargable(lHeadCode);
	    	
	    	sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("TOTAL PER HEAD:");
	        sbLine.append(getChar(14," "));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[0]));
	        //sbLine.append(String.format("%8s", lLngFinalTotArray[1]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[2]));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[3]));
	        //sbLine.append(String.format("%9s", lLngFinalTotArray[14]));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[9]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[16]));
	        sbLine.append(String.format("%5s", lLngFinalTotArray[4]));
	        sbLine.append(String.format("%5s", lLngFinalTotArray[5]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[6]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[12]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[17]));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[7]));
	        sbLine.append(String.format("%16s", lLngFinalTotArray[8]));
	        sbLine.append("\r\n");
	        sbLine.append(getChar(31," "));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[1]));
	        sbLine.append(getChar(8," "));
	        //sbLine.append(String.format("%9s", lLngFinalTotArray[9]));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[14]));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[13]));
	        sbLine.append(getChar(8," "));
	        sbLine.append(String.format("%5s", lLngFinalTotArray[10]));
	        //sbLine.append(getChar(5," "));
	        sbLine.append(String.format("%5s", lLngFinalTotArray[18]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[11]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[15]));
	        
	        RuleBasedNumberFormat fomatter = new  RuleBasedNumberFormat(IReportConstants.INDIAN_ENG_RULE_SET);
	        String result = fomatter.format(new com.ibm.icu.math.BigDecimal(lLngFinalTotArray[8]));
	        
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Pay Rs:");
	        sbLine.append(getChar(18," "));
	        sbLine.append(String.format("%18s", lLngFinalTotArray[8]));
	        inputMap.put("FinalAmount", lLngFinalTotArray[8]);
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Rs. In Words:");
	        sbLine.append(getChar(12," "));
	        sbLine.append(String.format("%18s", result));
	        inputMap.put("FinalAmtInWrds", result);
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Demand No:");
	        sbLine.append(getChar(15," "));
	       // sbLine.append(String.format("%18s", lBDFinNetTot));
	        sbLine.append(String.format("%18s", lObjHeadChargeable.getDemandNo()));
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Head Chargeable:");
	        sbLine.append(getChar(9," "));
	       // sbLine.append(String.format("%18s", (lBDFinNetTot)));
	        sbLine.append(String.format("%18s", (lObjHeadChargeable.getMjrhdCode()+lObjHeadChargeable.getSubmjrhdCode()+lObjHeadChargeable.getMinhdCode()+lObjHeadChargeable.getSubhdCode())));
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Signed By");
	        sbLine.append(getChar(15," "));
	        sbLine.append("Pensioner's Clerk");
	        sbLine.append(getChar(15," "));
	        sbLine.append("D.A");
	        sbLine.append(getChar(15," "));
	        sbLine.append("H.A");
	        sbLine.append(getChar(15," "));
	        sbLine.append("Treasury Officer");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(95," "));
	        sbLine.append("Ahmedabad");
    	}
    	catch(Exception e){
  	 		gLogger.error(" Error is : " + e, e);
  	 		throw(e);
  		}
    	
    	return sbLine.toString();
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
    private String printRecord(MonthlyPensionBillVO MonthlyPensionVo,int i)
    {
        StringBuffer sbLine = new StringBuffer();
        String lstrName = null;
        
        //      calculating reduced pension
        Long lRedPen = Long.valueOf(0L);
        if(MonthlyPensionVo.getBasicPensionAmount() != null)
        	lRedPen = Math.round(MonthlyPensionVo.getBasicPensionAmount().doubleValue());
        if(MonthlyPensionVo.getDpPercentAmount() != null)		 
        	lRedPen = lRedPen + Math.round(MonthlyPensionVo.getDpPercentAmount().doubleValue());
        if(MonthlyPensionVo.getCvpMonthlyAmount() != null)
        	lRedPen = lRedPen - Math.round(MonthlyPensionVo.getCvpMonthlyAmount().doubleValue());
        if(MonthlyPensionVo.getPensionCutAmount() != null)
        	lRedPen = lRedPen - Math.round(MonthlyPensionVo.getPensionCutAmount().doubleValue());
        
        Long lOtherArr = 0L;
        if(MonthlyPensionVo.getOMR() != null)
        	lOtherArr = lOtherArr + Math.round(MonthlyPensionVo.getOMR().doubleValue());
        if(MonthlyPensionVo.getOtherArrearsAmount() != null)
        	lOtherArr = lOtherArr + Math.round(MonthlyPensionVo.getOtherArrearsAmount().doubleValue());
        
        Long lGross = Long.valueOf(0L);
        lGross = lGross + lRedPen;
        if(MonthlyPensionVo.getPersonalPension() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getPersonalPension().doubleValue());
        if(MonthlyPensionVo.getIr() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getIr().doubleValue());
        if(MonthlyPensionVo.getMedicalAllowenceAmount() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getMedicalAllowenceAmount().doubleValue());
        if(MonthlyPensionVo.getTiPercentAmount() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getTiPercentAmount().doubleValue());
        if(MonthlyPensionVo.getTIArrearsAmount() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getTIArrearsAmount().doubleValue());
       // if(MonthlyPensionVo.getOtherArrearsAmount() != null)
       // 	lGross = lGross + Math.round(MonthlyPensionVo.getOtherArrearsAmount().doubleValue());/
        lGross = lGross + lOtherArr;
        if(MonthlyPensionVo.getOtherBenefit() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getOtherBenefit().doubleValue());
        
        if(MonthlyPensionVo.getSpecialCutAmount() != null)
        	lGross = lGross - Math.round(MonthlyPensionVo.getSpecialCutAmount().doubleValue());
        
        Long lDeduction = 0L;
        if(MonthlyPensionVo.getRecoveryAmount() != null)
        	lDeduction = lDeduction + Math.round(MonthlyPensionVo.getRecoveryAmount().doubleValue());
        if(MonthlyPensionVo.getItCutAmount() != null)
        	lDeduction = lDeduction + Math.round(MonthlyPensionVo.getItCutAmount().doubleValue());
        
        sbLine.append(String.format("%4s", i));
        sbLine.append(getChar(1, " "));
        sbLine.append(MonthlyPensionVo.getPpoNo());
        sbLine.append(getChar((26 - MonthlyPensionVo.getPpoNo().length())," "));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getAllnBf11156() != null)? MonthlyPensionVo.getAllnBf11156().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        //sbLine.append(String.format("%8s",(MonthlyPensionVo.getAllnAf11156() != null)?MonthlyPensionVo.getAllnAf11156().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getAllnAf10560() != null)? MonthlyPensionVo.getAllnAf10560().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%9s",(MonthlyPensionVo.getBasicPensionAmount() != null)? MonthlyPensionVo.getBasicPensionAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        //sbLine.append(String.format("%9s",(MonthlyPensionVo.getPensionCutAmount() != null)? MonthlyPensionVo.getPensionCutAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%9s",(MonthlyPensionVo.getDpPercentAmount() != null)? MonthlyPensionVo.getDpPercentAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s", lRedPen));
        sbLine.append(String.format("%5s",(MonthlyPensionVo.getPersonalPension() != null)? MonthlyPensionVo.getPersonalPension().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%5s",(MonthlyPensionVo.getMedicalAllowenceAmount() != null)? MonthlyPensionVo.getMedicalAllowenceAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%7s",(MonthlyPensionVo.getTiPercentAmount() != null)? MonthlyPensionVo.getTiPercentAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
       // sbLine.append(String.format("%8s",(MonthlyPensionVo.getOtherArrearsAmount() != null)? MonthlyPensionVo.getOtherArrearsAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s", lOtherArr));
        sbLine.append(String.format("%8s", lGross));
        sbLine.append(String.format("%9s", lDeduction));
        sbLine.append(String.format("%16s",(lGross - lDeduction)));
        //sbLine.append(String.format("%11s", MonthlyPensionVo.getAccountNo()));
        sbLine.append("\r\n");
        sbLine.append(getChar(5," "));
        if(MonthlyPensionVo.getPensionerName() != null)
        {
        	lstrName = MonthlyPensionVo.getPensionerName();
        }
        else
        {
        	lstrName = "";
        }
        if(lstrName.length() > 24)
        { sbLine.append(lstrName.substring(0, 24));
          sbLine.append(getChar(2, " ")); }
        else
        { sbLine.append(lstrName);
        sbLine.append(getChar((26 - lstrName.length()), " ")); }
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getAllnAf11156() != null)?MonthlyPensionVo.getAllnAf11156().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(getChar(8," "));      
        //sbLine.append(String.format("%9s",(MonthlyPensionVo.getDpPercentAmount() != null)? MonthlyPensionVo.getDpPercentAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%9s",(MonthlyPensionVo.getPensionCutAmount() != null)? MonthlyPensionVo.getPensionCutAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%9s",(MonthlyPensionVo.getCvpMonthlyAmount() != null)? MonthlyPensionVo.getCvpMonthlyAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(getChar(8," "));
        sbLine.append(String.format("%5s",(MonthlyPensionVo.getIr() != null)? MonthlyPensionVo.getIr().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        //sbLine.append(getChar(5," "));
        sbLine.append(String.format("%5s",(MonthlyPensionVo.getOtherBenefit() != null)? MonthlyPensionVo.getOtherBenefit().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%7s",(MonthlyPensionVo.getTIArrearsAmount() != null)? MonthlyPensionVo.getTIArrearsAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getSpecialCutAmount() != null)? MonthlyPensionVo.getSpecialCutAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(getChar(17," "));
        sbLine.append(String.format("%16s", MonthlyPensionVo.getAccountNo()));
        sbLine.append("\r\n");
        if(lstrName.length() > 24)
        {  	sbLine.append(getChar(5," "));
            sbLine.append(lstrName.substring(24,lstrName.length())); }
	    sbLine.append("\r\n");
	    return sbLine.toString();
    }
    private String getHeadForMnthlyReport(int pageNum,Map lInputMap) throws Exception
    {
   	    StringBuffer sbLine = new StringBuffer();
   	    String BranchCode = lInputMap.get("BranchCode").toString();
   	    
   	    try
   	    {
   	    
   	    setSessionInfo(lInputMap);
   	    ServiceLocator serv = (ServiceLocator) lInputMap.get("serviceLocator");
   	    MonthlyPensionBillDAOImpl lObjMonthlyDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
   	    List lDtls = lObjMonthlyDAO.getHeaderDtl(BranchCode, gStrLocCode, gLngLangId);
   	    
   	    Object[] lObjTemp = (Object[]) lDtls.get(0);
   	    
   	    String lStrTrsryName = lObjTemp[0].toString();  
   	    String lStrBankName = lObjTemp[1].toString();
   	    String lStrBranchName = lObjTemp[2].toString();
   	    
        //String lStrTrsryName = (String)lInputMap.get("TreasuryName");
   	    //String lStrTrsryName = "Ahmedabad PPO Office"; 
   	   
        SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        int temp = 0;
        temp = (132 - lStrTrsryName.length())/2;
        sbLine.append(getChar(temp," "));
        sbLine.append(lStrTrsryName);
        sbLine.append(getChar(temp," "));
        sbLine.append("\r\n");
        sbLine.append(getChar(5," "));
        sbLine.append("DATE : ");
        sbLine.append(lObjDateFormat.format(DBUtility.getCurrentDateFromDB()));
        
        temp = 70 - ((lStrBankName + " : " + lStrBranchName).length() + 7);
        //temp = 70 - (((String)lInputMap.get("BranchName")).length() + 7);
        sbLine.append(getChar(temp/2," "));
        sbLine.append("Bank : ");
        //sbLine.append((String)lInputMap.get("BranchName"));
        sbLine.append(lStrBankName +" : "+lStrBranchName );
        sbLine.append(getChar(temp - temp/2," "));
        
        sbLine.append("For Month : ");
        sbLine.append((String)lInputMap.get("MonthName")+" "+lInputMap.get("ForYear").toString());
        sbLine.append("\r\n");
        sbLine.append(getChar(5," "));
        sbLine.append("Page : ");
        sbLine.append(pageNum);
        Integer page = pageNum;
        sbLine.append(getChar((19 - (page.toString()).length()), " "));
        
        temp = 70 - (((String)lInputMap.get("HeadCode")).length()+((String)lInputMap.get("HeadCodeDesc")).length()+14);
        sbLine.append(getChar(temp/2," "));
        sbLine.append("Headcode : ");
        sbLine.append((String)lInputMap.get("HeadCode")+" - "+(String)lInputMap.get("HeadCodeDesc"));
        sbLine.append(getChar(temp - temp/2," "));
        
        sbLine.append("Scheme : ");
        sbLine.append((String)lInputMap.get("Scheme"));
        sbLine.append("\r\n");
        sbLine.append(getChar(132,"-"));
        sbLine.append("\r\n");
        sbLine.append(getChar(1," "));
        sbLine.append("SR#");
        sbLine.append(getChar(2," "));
        sbLine.append("PPO No.");
        sbLine.append(getChar(18," "));
        sbLine.append(String.format("%8s", "B.B."));
        //sbLine.append(String.format("%8s", "B.A."));
        sbLine.append(String.format("%8s", "G.A."));
        sbLine.append(String.format("%9s", "Basic"));
        //sbLine.append(String.format("%9s", "Pen Cut"));
        sbLine.append(String.format("%9s", "DA Pen."));
        sbLine.append(String.format("%8s", "Reduced"));
        sbLine.append(String.format("%5s", "P.P"));
        sbLine.append(String.format("%5s", "MA"));
        sbLine.append(String.format("%7s", "TI"));
        sbLine.append(String.format("%8s", "Arrear"));
        sbLine.append(String.format("%8s", "Gross"));
        sbLine.append(String.format("%9s", "Deducton"));
        sbLine.append(String.format("%11s", "Net"));
        //sbLine.append(String.format("%11s", "Acc No."));
        sbLine.append("\r\n");
        //sbLine.append(getChar(6," "));
        //sbLine.append("Pensioner Name");
        //sbLine.append(getChar(7," "));
        sbLine.append(getChar(31," "));
        sbLine.append(String.format("%8s", "1/11/56"));
        //sbLine.append(String.format("%8s", "1/11/56"));
        sbLine.append(String.format("%8s", "1/5/60"));
        sbLine.append(String.format("%9s", "Pension"));
        //sbLine.append(String.format("%9s", "CVP Mnth"));
        sbLine.append(getChar(9," "));
        sbLine.append(String.format("%8s", "Pension"));
        //sbLine.append(String.format("%5s", "IR"));
        //sbLine.append(getChar(5," "));
       // sbLine.append(String.format("%7s", "ATI"));
        //sbLine.append(String.format("%9s", "OtherCut"));
        sbLine.append("\r\n");
        //sbLine.append(getChar(27," "));
        sbLine.append(getChar(6," "));
        sbLine.append("Pensioner Name");
        sbLine.append(getChar(11," "));
        sbLine.append(String.format("%8s", "B.A."));
        sbLine.append(getChar(8," "));
        //sbLine.append(String.format("%9s", "DP 50%"));
        sbLine.append(String.format("%9s", "Pen Cut"));
        //sbLine.append(getChar(9," "));
        sbLine.append(String.format("%9s", "CVP Mnth"));
        sbLine.append(String.format("%8s", "With DP"));
        sbLine.append(String.format("%5s", "IR"));
        sbLine.append(String.format("%6s", "Other"));
        sbLine.append(String.format("%6s", "ATI"));
        sbLine.append(String.format("%9s", "OtherCut"));
        sbLine.append(getChar(17," "));
        sbLine.append(String.format("%11s", "Acc No."));
        sbLine.append("\r\n");
        sbLine.append(getChar(31," "));
        sbLine.append(String.format("%8s", "1/11/56"));
        sbLine.append(getChar(38," "));
        sbLine.append(String.format("%8s", "Benefit"));
        sbLine.append("\r\n");
        sbLine.append(getChar(132,"-"));
        sbLine.append("\r\n");
            	
   	    }
   	    catch (Exception e) {
   	    	gLogger.error("Error is.: " + e,e);
			throw(e);
		}
        return sbLine.toString();
    }
    private Map getFooterForMnthlyReport(int pgCoutner,List MonthlyPensionList)
    {
        StringBuilder sbLine = new StringBuilder();
        Map iInputMap = new HashMap();
        MonthlyPensionBillVO lObjMonthlyVO = new MonthlyPensionBillVO();
        Long[] lBDArray = new Long[20];
        Long lTtlRedPen = Long.valueOf(0L);
        Long lTtlGross = Long.valueOf(0L);
        for(int i=0;i<20;i++)
        {
            lBDArray[i] = new Long(0L);
        }
        sbLine.append("\r\n");
        sbLine.append(getChar(132,"-"));
        sbLine.append("\r\n");
        sbLine.append(getChar(2," "));
        sbLine.append("TOTAL Per Page:");
        sbLine.append(getChar(14," "));
        int counter = pgCoutner * 15;
        for(int i=counter-15;i<MonthlyPensionList.size() && i<counter ;i++)
        {
            lObjMonthlyVO = (MonthlyPensionBillVO) MonthlyPensionList.get(i);
            if(lObjMonthlyVO.getAllnBf11156() != null)
            	lBDArray[0] = lBDArray[0]+Math.round((lObjMonthlyVO.getAllnBf11156().doubleValue()));
            if(lObjMonthlyVO.getAllnAf11156() != null)
            	lBDArray[1] = lBDArray[1]+Math.round((lObjMonthlyVO.getAllnAf11156().doubleValue()));
            if(lObjMonthlyVO.getAllnAf10560() != null)
            	lBDArray[2] = lBDArray[2]+Math.round((lObjMonthlyVO.getAllnAf10560().doubleValue()));
            if(lObjMonthlyVO.getBasicPensionAmount() != null)
            	lBDArray[3] = lBDArray[3]+Math.round((lObjMonthlyVO.getBasicPensionAmount().doubleValue()));
            if(lObjMonthlyVO.getPersonalPension() != null)
            	lBDArray[4] = lBDArray[4]+Math.round((lObjMonthlyVO.getPersonalPension().doubleValue()));
            if(lObjMonthlyVO.getMedicalAllowenceAmount() != null)
            	lBDArray[5] = lBDArray[5]+Math.round((lObjMonthlyVO.getMedicalAllowenceAmount().doubleValue()));
            if(lObjMonthlyVO.getTiPercentAmount() != null)
            	lBDArray[6] = lBDArray[6]+Math.round((lObjMonthlyVO.getTiPercentAmount().doubleValue()));
            if(lObjMonthlyVO.getRecoveryAmount() != null)
            	lBDArray[7] = lBDArray[7]+Math.round((lObjMonthlyVO.getRecoveryAmount().doubleValue()));
            if(lObjMonthlyVO.getItCutAmount() != null)
            	lBDArray[7] = lBDArray[7]+ Math.round(lObjMonthlyVO.getItCutAmount().doubleValue());
           // if(lObjMonthlyVO.getNetPensionAmount() != null)
           // 	lBDArray[8] = lBDArray[8]+Math.round((lObjMonthlyVO.getNetPensionAmount().doubleValue()));
            if(lObjMonthlyVO.getDpPercentAmount() != null)
            	lBDArray[9] = lBDArray[9]+Math.round((lObjMonthlyVO.getDpPercentAmount().doubleValue()));
            if(lObjMonthlyVO.getIr() != null)
            	lBDArray[10] = lBDArray[10]+Math.round((lObjMonthlyVO.getIr().doubleValue()));
            if(lObjMonthlyVO.getTIArrearsAmount() != null)
            	lBDArray[11] = lBDArray[11]+Math.round((lObjMonthlyVO.getTIArrearsAmount().doubleValue()));
            if(lObjMonthlyVO.getOtherArrearsAmount() != null)
            	lBDArray[12] = lBDArray[12]+Math.round((lObjMonthlyVO.getOtherArrearsAmount().doubleValue()));
            if(lObjMonthlyVO.getOMR() != null)
            	lBDArray[12] = lBDArray[12]+Math.round((lObjMonthlyVO.getOMR().doubleValue()));
            //computing sum of cvp monthly for computing reduced pension
            if(lObjMonthlyVO.getCvpMonthlyAmount() != null)
            	lBDArray[13] = lBDArray[13]+Math.round((lObjMonthlyVO.getCvpMonthlyAmount().doubleValue()));
            if(lObjMonthlyVO.getPensionCutAmount() != null)
            	lBDArray[14] = lBDArray[14]+Math.round(lObjMonthlyVO.getPensionCutAmount().doubleValue());
            if(lObjMonthlyVO.getSpecialCutAmount() != null)
            	lBDArray[15] = lBDArray[15]+Math.round(lObjMonthlyVO.getSpecialCutAmount().doubleValue());
            if(lObjMonthlyVO.getOtherBenefit() != null)
            	lBDArray[18] = lBDArray[18]+Math.round(lObjMonthlyVO.getOtherBenefit().doubleValue());
        }
       
        //          reduced pension
        lTtlRedPen = lBDArray[3] + lBDArray[9] - lBDArray[13] - lBDArray[14];
        //			gross
        lTtlGross = lTtlRedPen + lBDArray[4] + lBDArray[10] + lBDArray[5] 
                    + lBDArray[6] + lBDArray[11] + lBDArray[12] - lBDArray[15] + lBDArray[16];
        
        lBDArray[8] = lTtlGross - lBDArray[7];
        
        sbLine.append(String.format("%8s", lBDArray[0]));
        //sbLine.append(String.format("%8s", lBDArray[1]));
        sbLine.append(String.format("%8s", lBDArray[2]));
        sbLine.append(String.format("%9s", lBDArray[3]));
        //sbLine.append(String.format("%9s", lBDArray[14]));
        sbLine.append(String.format("%9s", lBDArray[9]));
        sbLine.append(String.format("%8s", lTtlRedPen));
        sbLine.append(String.format("%5s", lBDArray[4]));
        sbLine.append(String.format("%5s", lBDArray[5]));
        sbLine.append(String.format("%7s", lBDArray[6]));
        sbLine.append(String.format("%8s", lBDArray[12]));
        sbLine.append(String.format("%8s", lTtlGross));
        sbLine.append(String.format("%9s", lBDArray[7]));
        sbLine.append(String.format("%16s", lBDArray[8]));
        sbLine.append("\r\n");
        sbLine.append(getChar(30," "));
        sbLine.append(String.format("%9s", lBDArray[1]));
        sbLine.append(getChar(8," "));
        //sbLine.append(String.format("%9s", lBDArray[9]));
        sbLine.append(String.format("%9s", lBDArray[14]));
        sbLine.append(String.format("%9s", lBDArray[13]));
        sbLine.append(getChar(8," "));
        sbLine.append(String.format("%5s", lBDArray[10]));
        //sbLine.append(getChar(5," "));
        sbLine.append(String.format("%5s", lBDArray[18]));
        sbLine.append(String.format("%7s", lBDArray[11]));
        sbLine.append(String.format("%8s", lBDArray[15]));
        lBDArray[16] = lTtlRedPen;
        lBDArray[17] = lTtlGross;
        sbLine.append("\r\n");
        sbLine.append(getChar(132,"-"));
        sbLine.append("\r\n");
        sbLine.append("\r\n");
        sbLine.append("\r\n");
        
        iInputMap.put("FooterString", sbLine.toString());
        iInputMap.put("PageTotals", lBDArray);
        return iInputMap ;
    }
    
    
    //report made according to client requirements....approved by client
  /*  public  ResultObject printMonthlyBill(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        StringBuffer sbLine = new StringBuffer();
        int pgCoutner = 1;
        try
        {
            //resObj = serv.executeService("VIEW_SAVED_MONTHLY_BILL", inputMap);
            //inputMap = (Map) resObj.getResultValue();
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append("\r\n");
            sbLine.append("\r\n");
            sbLine.append("\r\n");
            //method calling for Getting header on each new page
           
            List MonthlyPensionList = (List) inputMap.get("MonthlyPensionList");
           
            MonthlyPensionBillVO MonthlyPensionVo = null;
            int k = 0;
            if(MonthlyPensionList != null)
            {
            	 Long[] lLngFinalTotArray = new Long[20];
            	  for(int i=0;i<20;i++)
                  {
            		  lLngFinalTotArray[i] = new Long(0L);
                  } 
            	for (int i = 1; i <= MonthlyPensionList.size(); i++)
                 {
                     MonthlyPensionVo = (MonthlyPensionBillVO) MonthlyPensionList.get(k);
                     if( k % 15 == 0)
                     {
                    	 sbLine.append("<div>");
                    	 sbLine.append("<pre>");
                         sbLine.append(getHeadForMnthlyReport(pgCoutner,inputMap));
                     }
                     Map lTempMap = null;
                     Long[] lLsttot = new Long[20];
                     sbLine.append(printRecord(MonthlyPensionVo,i));
                     if(i!= 1 && i % 15 == 0)
                     {
                    	 lTempMap = getFooterForMnthlyReport(pgCoutner,MonthlyPensionList);
                    	 sbLine.append(lTempMap.get("FooterString"));
                    	 lLsttot =  (Long[]) lTempMap.get("PageTotals");
                    	 for(int tmpCnt =0; tmpCnt<20 ; tmpCnt++)
                    	 {
                    		 lLngFinalTotArray[tmpCnt] = lLngFinalTotArray[tmpCnt]+lLsttot[tmpCnt];
                    	 }
                         pgCoutner = pgCoutner+1;
                         sbLine.append("</pre></div>");
                     }
                     if(i % 15 != 0 && i == MonthlyPensionList.size())
                     {
                    	 lTempMap = getFooterForMnthlyReport(pgCoutner,MonthlyPensionList);
                    	 lLsttot =  (Long[]) lTempMap.get("PageTotals");
                    	 for(int tmpCnt =0; tmpCnt<20 ; tmpCnt++)
                    	 {
                    		 lLngFinalTotArray[tmpCnt] = lLngFinalTotArray[tmpCnt]+lLsttot[tmpCnt];
                    	 }
                    	 sbLine.append(lTempMap.get("FooterString"));
                     }
                     k++;
                 }
            	 if(MonthlyPensionList == null || MonthlyPensionList.size() == 0){
                    sbLine.append(getHeadForMnthlyReport(pgCoutner,inputMap));
                 }
                 sbLine.append(getFinalFooter(lLngFinalTotArray,inputMap));
                 sbLine.append("</pre></div>");
            }
            inputMap.put("DisplayString", sbLine.toString().replace("</pre></div>", "").replace("<div><pre>", ""));
            inputMap.put("PrintString", sbLine.toString());
            resObj.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }*/
    
   /* public  ResultObject printMonthlyBillForDisplay(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        StringBuffer sbLine = new StringBuffer();
        int pgCoutner = 1;
        try
        {
            //resObj = serv.executeService("VIEW_SAVED_MONTHLY_BILL", inputMap);
            //inputMap = (Map) resObj.getResultValue();
            sbLine.append(getChar(132," "));
            sbLine.append("\r\n");
            sbLine.append("\r\n");
            sbLine.append("\r\n");
            sbLine.append("\r\n");
            //method calling for Getting header on each new page
           
            List MonthlyPensionList = (List) inputMap.get("MonthlyPensionList");
           
            MonthlyPensionBillVO MonthlyPensionVo = null;
            int k = 0;
            if(MonthlyPensionList != null)
            {
            	 Long[] lLngFinalTotArray = new Long[20];
            	  for(int i=0;i<20;i++)
                  {
            		  lLngFinalTotArray[i] = new Long(0L);
                  } 
            	for (int i = 1; i <= MonthlyPensionList.size(); i++)
                 {
                     MonthlyPensionVo = (MonthlyPensionBillVO) MonthlyPensionList.get(k);
                     if( k % 15 == 0)
                     {
                    	// sbLine.append("<div>");
                    	// sbLine.append("<pre>");
                         sbLine.append(getHeadForMnthlyReport(pgCoutner,inputMap));
                     }
                     Map lTempMap = null;
                     Long[] lLsttot = new Long[20];
                     sbLine.append(printRecord(MonthlyPensionVo,i));
                     if(i!= 1 && i % 15 == 0)
                     {
                    	 lTempMap = getFooterForMnthlyReport(pgCoutner,MonthlyPensionList);
                    	 sbLine.append(lTempMap.get("FooterString"));
                    	 lLsttot =  (Long[]) lTempMap.get("PageTotals");
                    	 for(int tmpCnt =0; tmpCnt<20 ; tmpCnt++)
                    	 {
                    		 lLngFinalTotArray[tmpCnt] = lLngFinalTotArray[tmpCnt]+lLsttot[tmpCnt];
                    	 }
                         pgCoutner = pgCoutner+1;
                        // sbLine.append("</pre></div>");
                     }
                     if(i % 15 != 0 && i == MonthlyPensionList.size())
                     {
                    	 lTempMap = getFooterForMnthlyReport(pgCoutner,MonthlyPensionList);
                    	 lLsttot =  (Long[]) lTempMap.get("PageTotals");
                    	 for(int tmpCnt =0; tmpCnt<20 ; tmpCnt++)
                    	 {
                    		 lLngFinalTotArray[tmpCnt] = lLngFinalTotArray[tmpCnt]+lLsttot[tmpCnt];
                    	 }
                    	 sbLine.append(lTempMap.get("FooterString"));
                     }
                     k++;
                 }
            	 if(MonthlyPensionList == null || MonthlyPensionList.size() == 0){
                    sbLine.append(getHeadForMnthlyReport(pgCoutner,inputMap));
                 }
                 sbLine.append(getFinalFooter(lLngFinalTotArray,inputMap));
                // sbLine.append("</pre></div>");
            }
           
            inputMap.put("DisplayString", sbLine.toString());
            resObj.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
   /* private String getFinalFooter(Long[] lLngFinalTotArray, Map inputMap) throws Exception
    {
    	StringBuffer sbLine = new StringBuffer();
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	try
    	{
	    	String lHeadCode = (String) inputMap.get("HeadCode");
	    	MonthlyPensionBillDAO lObjMonthlyDao = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
	    	RltPensionHeadcodeChargable lObjHeadChargeable = new RltPensionHeadcodeChargable();
	    	lObjHeadChargeable = lObjMonthlyDao.getRltPensionHeadcodeChargable(lHeadCode);
	    	
	    	sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("TOTAL PER HEAD:");
	        sbLine.append(getChar(10," "));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[0]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[1]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[2]));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[3]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[16]));
	        sbLine.append(String.format("%5s", lLngFinalTotArray[4]));
	        sbLine.append(String.format("%5s", lLngFinalTotArray[5]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[6]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[12]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[17]));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[7]));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[8]));
	        sbLine.append("\r\n");
	        sbLine.append(getChar(51," "));
	        sbLine.append(String.format("%9s", lLngFinalTotArray[9]));
	        sbLine.append(getChar(8," "));
	        sbLine.append(String.format("%5s", lLngFinalTotArray[10]));
	        //sbLine.append(getChar(5," "));
	        sbLine.append(String.format("%5s", lLngFinalTotArray[18]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[11]));
	        sbLine.append(String.format("%8s", lLngFinalTotArray[15]));
	        
	        RuleBasedNumberFormat fomatter = new  RuleBasedNumberFormat(IReportConstants.INDIAN_ENG_RULE_SET);
	        String result = fomatter.format(new com.ibm.icu.math.BigDecimal(lLngFinalTotArray[8]));
	        	        
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Pay Rs:");
	        sbLine.append(getChar(18," "));
	        sbLine.append(String.format("%18s", lLngFinalTotArray[8]));
	        inputMap.put("FinalAmount", lLngFinalTotArray[8]);
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Rs. In Words:");
	        sbLine.append(getChar(12," "));
	        
	        sbLine.append(String.format("%18s", result));
	        inputMap.put("FinalAmtInWrds", result);
	        
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Demand No:");
	        sbLine.append(getChar(15," "));
	       // sbLine.append(String.format("%18s", lBDFinNetTot));
	        sbLine.append(String.format("%18s", lObjHeadChargeable.getDemandNo()));
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Head Chargeable:");
	        sbLine.append(getChar(9," "));
	       // sbLine.append(String.format("%18s", (lBDFinNetTot)));
	        sbLine.append(String.format("%18s", (lObjHeadChargeable.getMjrhdCode()+lObjHeadChargeable.getSubmjrhdCode()+lObjHeadChargeable.getMinhdCode()+lObjHeadChargeable.getSubhdCode())));
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(2," "));
	        sbLine.append("Signed By");
	        sbLine.append(getChar(15," "));
	        sbLine.append("Pensioner's Clerk");
	        sbLine.append(getChar(15," "));
	        sbLine.append("D.A");
	        sbLine.append(getChar(15," "));
	        sbLine.append("H.A");
	        sbLine.append(getChar(15," "));
	        sbLine.append("Treasury Officer");
	        sbLine.append("\r\n");
	        sbLine.append(getChar(95," "));
	        sbLine.append("Gandhinagar");
    	}
    	catch(Exception e){
  	 		gLogger.error(" Error is : " + e, e);
  	 		throw(e);
  		}
    	
    	return sbLine.toString();
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
    private String printRecord(MonthlyPensionBillVO MonthlyPensionVo,int i)
    {
        StringBuffer sbLine = new StringBuffer();
        String lstrName = null;
        
        //      calculating reduced pension
        Long lRedPen = Long.valueOf(0L);
        if(MonthlyPensionVo.getBasicPensionAmount() != null)
        	lRedPen = Math.round(MonthlyPensionVo.getBasicPensionAmount().doubleValue());
        if(MonthlyPensionVo.getDpPercentAmount() != null)		 
        	lRedPen = lRedPen + Math.round(MonthlyPensionVo.getDpPercentAmount().doubleValue());
        if(MonthlyPensionVo.getCvpMonthlyAmount() != null)
        	lRedPen = lRedPen - Math.round(MonthlyPensionVo.getCvpMonthlyAmount().doubleValue());
        if(MonthlyPensionVo.getPensionCutAmount() != null)
        	lRedPen = lRedPen - Math.round(MonthlyPensionVo.getPensionCutAmount().doubleValue());
        
        Long lOtherArr = 0L;
        if(MonthlyPensionVo.getOMR() != null)
        	lOtherArr = lOtherArr + Math.round(MonthlyPensionVo.getOMR().doubleValue());
        if(MonthlyPensionVo.getOtherArrearsAmount() != null)
        	lOtherArr = lOtherArr + Math.round(MonthlyPensionVo.getOtherArrearsAmount().doubleValue());
        
        Long lGross = Long.valueOf(0L);
        lGross = lGross + lRedPen;
        if(MonthlyPensionVo.getPersonalPension() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getPersonalPension().doubleValue());
        if(MonthlyPensionVo.getIr() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getIr().doubleValue());
        if(MonthlyPensionVo.getMedicalAllowenceAmount() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getMedicalAllowenceAmount().doubleValue());
        if(MonthlyPensionVo.getTiPercentAmount() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getTiPercentAmount().doubleValue());
        if(MonthlyPensionVo.getTIArrearsAmount() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getTIArrearsAmount().doubleValue());
        
        //if(MonthlyPensionVo.getOtherArrearsAmount() != null)
        //	lGross = lGross + Math.round(MonthlyPensionVo.getOtherArrearsAmount().doubleValue());
        lGross = lGross + lOtherArr;
        
        if(MonthlyPensionVo.getSpecialCutAmount() != null)
        	lGross = lGross - Math.round(MonthlyPensionVo.getSpecialCutAmount().doubleValue());
        
        if(MonthlyPensionVo.getOtherBenefit() != null)
        	lGross = lGross + Math.round(MonthlyPensionVo.getOtherBenefit().doubleValue());
        
        Long lDeduction = 0L;
        if(MonthlyPensionVo.getRecoveryAmount() != null)
        	lDeduction = lDeduction + Math.round(MonthlyPensionVo.getRecoveryAmount().doubleValue());
        if(MonthlyPensionVo.getItCutAmount() != null)
        	lDeduction = lDeduction + Math.round(MonthlyPensionVo.getItCutAmount().doubleValue());
        
        sbLine.append(String.format("%4s", i));
        sbLine.append(getChar(1, " "));
        sbLine.append(MonthlyPensionVo.getPpoNo());
        sbLine.append(getChar((22 - MonthlyPensionVo.getPpoNo().length())," "));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getAllnBf11156() != null)? MonthlyPensionVo.getAllnBf11156().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getAllnAf11156() != null)?MonthlyPensionVo.getAllnAf11156().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getAllnAf10560() != null)? MonthlyPensionVo.getAllnAf10560().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%9s",(MonthlyPensionVo.getBasicPensionAmount() != null)? MonthlyPensionVo.getBasicPensionAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s", lRedPen));
        sbLine.append(String.format("%5s",(MonthlyPensionVo.getPersonalPension() != null)? MonthlyPensionVo.getPersonalPension().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%5s",(MonthlyPensionVo.getMedicalAllowenceAmount() != null)? MonthlyPensionVo.getMedicalAllowenceAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getTiPercentAmount() != null)? MonthlyPensionVo.getTiPercentAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s", lOtherArr));
        sbLine.append(String.format("%8s", lGross));
        sbLine.append(String.format("%9s", lDeduction));
        sbLine.append(String.format("%9s",(lGross - lDeduction)));
        sbLine.append(String.format("%11s", MonthlyPensionVo.getAccountNo()));
        sbLine.append("\r\n");
        sbLine.append(getChar(5," "));
        if(MonthlyPensionVo.getPensionerName() != null)
        {
        	lstrName = MonthlyPensionVo.getPensionerName();
        }
        else
        {
        	lstrName = "";
        }
        if(lstrName.length() > 20)
        { sbLine.append(lstrName.substring(0, 20));
          sbLine.append(getChar(2, " ")); }
        else
        { sbLine.append(lstrName);
        sbLine.append(getChar((22 - lstrName.length()), " ")); }
        sbLine.append(getChar(24," "));      
        sbLine.append(String.format("%9s",(MonthlyPensionVo.getDpPercentAmount() != null)? MonthlyPensionVo.getDpPercentAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(getChar(8," "));
        sbLine.append(String.format("%5s",(MonthlyPensionVo.getIr() != null)? MonthlyPensionVo.getIr().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        //sbLine.append(getChar(5," "));
        sbLine.append(String.format("%5s",(MonthlyPensionVo.getOtherBenefit() != null)? MonthlyPensionVo.getOtherBenefit().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getTIArrearsAmount() != null)? MonthlyPensionVo.getTIArrearsAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append(String.format("%8s",(MonthlyPensionVo.getSpecialCutAmount() != null)? MonthlyPensionVo.getSpecialCutAmount().setScale(0,BigDecimal.ROUND_UP):new BigDecimal(0)));
        sbLine.append("\r\n");
        if(lstrName.length() > 20)
        {  	sbLine.append(getChar(5," "));
            sbLine.append(lstrName.substring(20,lstrName.length())); }
	    sbLine.append("\r\n");
	    return sbLine.toString();
    }
    private String getHeadForMnthlyReport(int pageNum,Map lInputMap)
    {
   	    StringBuffer sbLine = new StringBuffer();
        String lStrTrsryName = (String)lInputMap.get("TreasuryName");
        SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        int temp = (132 - lStrTrsryName.length())/2;
        sbLine.append(getChar(temp," "));
        sbLine.append(lStrTrsryName);
        sbLine.append(getChar(temp," "));
        sbLine.append("\r\n");
        sbLine.append(getChar(5," "));
        sbLine.append("DATE : ");
        sbLine.append(lObjDateFormat.format(DBUtility.getCurrentDateFromDB()));
        
        temp = 70 - (((String)lInputMap.get("BranchName")).length() + 7);
        sbLine.append(getChar(temp/2," "));
        sbLine.append("Bank : ");
        sbLine.append((String)lInputMap.get("BranchName"));
        sbLine.append(getChar(temp - temp/2," "));
        
        sbLine.append("For Month : ");
        sbLine.append((String)lInputMap.get("MonthName")+" "+lInputMap.get("ForYear").toString());
        sbLine.append("\r\n");
        sbLine.append(getChar(5," "));
        sbLine.append("Page : ");
        sbLine.append(pageNum);
        Integer page = pageNum;
        sbLine.append(getChar((19 - (page.toString()).length()), " "));
        
        temp = 70 - (((String)lInputMap.get("HeadCode")).length()+((String)lInputMap.get("HeadCodeDesc")).length()+14);
        sbLine.append(getChar(temp/2," "));
        sbLine.append("Headcode : ");
        sbLine.append((String)lInputMap.get("HeadCode")+" - "+(String)lInputMap.get("HeadCodeDesc"));
        sbLine.append(getChar(temp - temp/2," "));
        
        sbLine.append("Scheme : ");
        sbLine.append((String)lInputMap.get("Scheme"));
        sbLine.append("\r\n");
        sbLine.append(getChar(132,"-"));
        sbLine.append("\r\n");
        sbLine.append(getChar(1," "));
        sbLine.append("SR#");
        sbLine.append(getChar(2," "));
        sbLine.append("PPO No.");
        sbLine.append(getChar(14," "));
        sbLine.append(String.format("%8s", "B.B."));
        sbLine.append(String.format("%8s", "B.A."));
        sbLine.append(String.format("%8s", "G.A."));
        sbLine.append(String.format("%9s", "Ori/Cons"));
        sbLine.append(String.format("%8s", "Reduced"));
        sbLine.append(String.format("%5s", "P.P"));
        sbLine.append(String.format("%5s", "MA"));
        sbLine.append(String.format("%8s", "TI"));
        sbLine.append(String.format("%8s", "Arrear"));
        sbLine.append(String.format("%8s", "Gross"));
        sbLine.append(String.format("%9s", "Deducton"));
        sbLine.append(String.format("%9s", "Net"));
        sbLine.append(String.format("%11s", "Acc No."));
        sbLine.append("\r\n");
        sbLine.append(getChar(6," "));
        sbLine.append("Pensioner Name");
        sbLine.append(getChar(7," "));
        sbLine.append(String.format("%8s", "1/11/56"));
        sbLine.append(String.format("%8s", "1/11/56"));
        sbLine.append(String.format("%8s", "1/5/60"));
        sbLine.append(String.format("%9s", "Pension"));
        sbLine.append(String.format("%8s", "Pension"));
        sbLine.append(String.format("%5s", "IR"));
        //sbLine.append(getChar(5," "));
        sbLine.append(String.format("%6s", "Other"));
        sbLine.append(String.format("%7s", "ATI"));
        sbLine.append(String.format("%9s", "OtherCut"));
        sbLine.append("\r\n");
        sbLine.append(getChar(51," "));
        sbLine.append(String.format("%9s", "DP 50%"));
        sbLine.append(String.format("%8s", "With DP"));
        sbLine.append(getChar(5," "));
        sbLine.append(String.format("%7s", "Benefit"));
        sbLine.append("\r\n");
        sbLine.append(getChar(132,"-"));
        sbLine.append("\r\n");
        return sbLine.toString();
    }
    private Map getFooterForMnthlyReport(int pgCoutner,List MonthlyPensionList)
    {
        StringBuilder sbLine = new StringBuilder();
        Map iInputMap = new HashMap();
        MonthlyPensionBillVO lObjMonthlyVO = new MonthlyPensionBillVO();
        Long[] lBDArray = new Long[20];
        Long lTtlRedPen = Long.valueOf(0L);
        Long lTtlGross = Long.valueOf(0L);
        for(int i=0;i<20;i++)
        {
            lBDArray[i] = new Long(0L);
        }
        sbLine.append("\r\n");
        sbLine.append(getChar(132,"-"));
        sbLine.append("\r\n");
        sbLine.append(getChar(2," "));
        sbLine.append("TOTAL Per Page:");
        sbLine.append(getChar(10," "));
        int counter = pgCoutner * 15;
        for(int i=counter-15;i<MonthlyPensionList.size() && i<counter ;i++)
        {
            lObjMonthlyVO = (MonthlyPensionBillVO) MonthlyPensionList.get(i);
            if(lObjMonthlyVO.getAllnBf11156() != null)
            	lBDArray[0] = lBDArray[0]+Math.round((lObjMonthlyVO.getAllnBf11156().doubleValue()));
            if(lObjMonthlyVO.getAllnAf11156() != null)
            	lBDArray[1] = lBDArray[1]+Math.round((lObjMonthlyVO.getAllnAf11156().doubleValue()));
            if(lObjMonthlyVO.getAllnAf10560() != null)
            	lBDArray[2] = lBDArray[2]+Math.round((lObjMonthlyVO.getAllnAf10560().doubleValue()));
            if(lObjMonthlyVO.getBasicPensionAmount() != null)
            	lBDArray[3] = lBDArray[3]+Math.round((lObjMonthlyVO.getBasicPensionAmount().doubleValue()));
            if(lObjMonthlyVO.getPersonalPension() != null)
            	lBDArray[4] = lBDArray[4]+Math.round((lObjMonthlyVO.getPersonalPension().doubleValue()));
            if(lObjMonthlyVO.getMedicalAllowenceAmount() != null)
            	lBDArray[5] = lBDArray[5]+Math.round((lObjMonthlyVO.getMedicalAllowenceAmount().doubleValue()));
            if(lObjMonthlyVO.getTiPercentAmount() != null)
            	lBDArray[6] = lBDArray[6]+Math.round((lObjMonthlyVO.getTiPercentAmount().doubleValue()));
            if(lObjMonthlyVO.getRecoveryAmount() != null)
            	lBDArray[7] = lBDArray[7]+Math.round((lObjMonthlyVO.getRecoveryAmount().doubleValue()));
            if(lObjMonthlyVO.getItCutAmount() != null)
            	lBDArray[7] = lBDArray[7]+ Math.round(lObjMonthlyVO.getItCutAmount().doubleValue());
            if(lObjMonthlyVO.getNetPensionAmount() != null)
            	lBDArray[8] = lBDArray[8]+Math.round((lObjMonthlyVO.getNetPensionAmount().doubleValue()));
            if(lObjMonthlyVO.getDpPercentAmount() != null)
            	lBDArray[9] = lBDArray[9]+Math.round((lObjMonthlyVO.getDpPercentAmount().doubleValue()));
            if(lObjMonthlyVO.getIr() != null)
            	lBDArray[10] = lBDArray[10]+Math.round((lObjMonthlyVO.getIr().doubleValue()));
            if(lObjMonthlyVO.getTIArrearsAmount() != null)
            	lBDArray[11] = lBDArray[11]+Math.round((lObjMonthlyVO.getTIArrearsAmount().doubleValue()));
            if(lObjMonthlyVO.getOtherArrearsAmount() != null)
            	lBDArray[12] = lBDArray[12]+Math.round((lObjMonthlyVO.getOtherArrearsAmount().doubleValue()));
            if(lObjMonthlyVO.getOMR() != null)
            	lBDArray[12] = lBDArray[12]+Math.round((lObjMonthlyVO.getOMR().doubleValue()));
            //computing sum of cvp monthly for computing reduced pension
            if(lObjMonthlyVO.getCvpMonthlyAmount() != null)
            	lBDArray[13] = lBDArray[13]+Math.round((lObjMonthlyVO.getCvpMonthlyAmount().doubleValue()));
            if(lObjMonthlyVO.getPensionCutAmount() != null)
            	lBDArray[14] = lBDArray[14]+Math.round(lObjMonthlyVO.getPensionCutAmount().doubleValue());
            if(lObjMonthlyVO.getSpecialCutAmount() != null)
            	lBDArray[15] = lBDArray[15]+Math.round(lObjMonthlyVO.getSpecialCutAmount().doubleValue());
            if(lObjMonthlyVO.getOtherBenefit() != null)
            	lBDArray[18] = lBDArray[18]+Math.round(lObjMonthlyVO.getOtherBenefit().doubleValue());
        }
       
        //          reduced pension
        lTtlRedPen = lBDArray[3] + lBDArray[9] - lBDArray[13] - lBDArray[14];
        //			gross
        //gLogger.info(""+);
        lTtlGross = lTtlRedPen + lBDArray[4] + lBDArray[10] + lBDArray[5] 
                    + lBDArray[6] + lBDArray[11] + lBDArray[12] - lBDArray[15] + lBDArray[18];
        
        lBDArray[8] = lTtlGross - lBDArray[7];
        
        sbLine.append(String.format("%8s", lBDArray[0]));
        sbLine.append(String.format("%8s", lBDArray[1]));
        sbLine.append(String.format("%8s", lBDArray[2]));
        sbLine.append(String.format("%9s", lBDArray[3]));
        sbLine.append(String.format("%8s", lTtlRedPen));
        sbLine.append(String.format("%5s", lBDArray[4]));
        sbLine.append(String.format("%5s", lBDArray[5]));
        sbLine.append(String.format("%8s", lBDArray[6]));
        sbLine.append(String.format("%8s", lBDArray[12]));
        sbLine.append(String.format("%8s", lTtlGross));
        sbLine.append(String.format("%9s", lBDArray[7]));
        sbLine.append(String.format("%9s", lBDArray[8]));
        sbLine.append("\r\n");
        sbLine.append(getChar(51," "));
        sbLine.append(String.format("%9s", lBDArray[9]));
        sbLine.append(getChar(8," "));
        sbLine.append(String.format("%5s", lBDArray[10]));
        //sbLine.append(getChar(5," "));
        sbLine.append(String.format("%5s", lBDArray[18]));
        sbLine.append(String.format("%8s", lBDArray[11]));
        sbLine.append(String.format("%8s", lBDArray[15]));
        lBDArray[16] = lTtlRedPen;
        lBDArray[17] = lTtlGross;
        sbLine.append("\r\n");
        sbLine.append(getChar(132,"-"));
        sbLine.append("\r\n");
        sbLine.append("\r\n");
        sbLine.append("\r\n");
        
        iInputMap.put("FooterString", sbLine.toString());
        iInputMap.put("PageTotals", lBDArray);
        return iInputMap ;
    }*/
    
	/**
     * Get Monthly Pension Bill data for displaying in bill
     * 
     * @param inputMap
     * @return objRes ResultObject
     */
	public ResultObject getMonthlyPension(Map inputMap)
    {            
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        MonthlyPensionBillVO lObjMonthlyPensionVO = null;
        List lLstlObjMonthlyPensionVO = null;
        List<TrnPensionArrearDtls> lLstArrearsVO = new ArrayList<TrnPensionArrearDtls>();
        List<TrnPensionArrearDtls> lLstFinalArrearsVO = new ArrayList<TrnPensionArrearDtls>();
        TrnPensionArrearDtls lObjArrearVO = null;
        String lStrBank = null;
        String lStrBranch = null;
        String lStrHeadCode = null;
        String lStrHeadCodeDesc = null;
        String lStrScheme = null;
        String lStrDate = null;
        String lStrMonth = null;
        String lStrYear = null;
        String lStrMyCases = null; 
        String lStrPnsnrCode = null;
        String lStrFlag = null;
        Double lTotalNetAmt = 0.0;
        StringBuffer lStrPnsnCode = new StringBuffer();
        String lArrComputeFlag = null;
        
        List lLstROPData = new ArrayList();
        
        try
        {
            setSessionInfo(inputMap);
            MonthlyPensionBillDAO lObjMonthlyDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
            CommonPensionDAOImpl lObjMonthlyPnsnDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
            lStrMonth = StringUtility.getParameter("cmbForMonth",request).trim();
        	lStrYear = StringUtility.getParameter("cmbForYear",request).trim();
        	lStrBank = StringUtility.getParameter("cmbBank",request).trim();
        	lStrBranch = StringUtility.getParameter("cmbBranch",request).trim();
        	lStrHeadCode = StringUtility.getParameter("cmbHeadCode",request).trim();
        	lStrHeadCodeDesc = StringUtility.getParameter("cmbHeadCodeDesc",request).trim();
        	lStrScheme = StringUtility.getParameter("cmbForScheme",request).trim();
        	if(Integer.parseInt(lStrMonth) < 10){
        		lStrDate = lStrYear+"0"+lStrMonth;
        	}
        	else{
        		lStrDate = lStrYear+lStrMonth;
        	}
        	if(lStrBank.equals("") && lStrBank.length() <= 0)
        	{
        		lStrBank = lObjMonthlyDAO.getBankCode(lStrBranch, gStrLocCode);
        	}
        	if(lStrHeadCodeDesc.equals("") && lStrHeadCodeDesc.length() <= 0)
        	{
        		lStrHeadCodeDesc = lObjMonthlyPnsnDAO.getAllHeadCodeDesc(lStrHeadCode, SessionHelper.getLangId(inputMap));
        	}
        	inputMap.put("ForMonth",lStrMonth);
        	inputMap.put("ForYear",lStrYear);
        	inputMap.put("ForBillYear",lStrYear);
        	inputMap.put("Bank",lStrBank);
        	inputMap.put("Branch",lStrBranch);
        	inputMap.put("HeadCode",lStrHeadCode);
        	inputMap.put("HeadCodeDesc",lStrHeadCodeDesc);
        	inputMap.put("Scheme",lStrScheme);
        	inputMap.put("Date",lStrDate);
        	inputMap.put("BranchCode",lStrBranch);
        	inputMap.put("FPFlag", "Y");			// 'Y' indcates that FP has to be computed in the immediately called computeMonthly
        	
        	//find bank name and branch name
        	String lStrBankName = lObjMonthlyDAO.getBankName(lStrBank);
        	String lStrBranchName = lObjMonthlyDAO.getBranchName(lStrBank, lStrBranch);
        	List lLstMonthDtls = (ArrayList) lObjMonthlyDAO.getMonthDtls(lStrMonth, gLngLangId);
        	
        	inputMap.put("BankName",lStrBankName);
        	inputMap.put("BranchName",lStrBranchName);
        	inputMap.put("MonthName",lLstMonthDtls.get(1).toString());
        	inputMap.put("MonthCode",lLstMonthDtls.get(0).toString());
        	
        	CommonPensionServiceImpl lObjCmnSrvc = new CommonPensionServiceImpl();
        	lStrMyCases = lObjCmnSrvc.getMyCases(inputMap);
        	MonthlyPensionBillDAOImpl lObjMnthlyBillDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
        	List lLstPnsnrCode = lObjMnthlyBillDAO.getValidPensionerCode(lStrBank, lStrBranch, lStrHeadCode, lStrScheme, lStrMyCases);
        	if(lLstPnsnrCode != null && !lLstPnsnrCode.isEmpty())
        	{
        		//combine list of all pensioner code in a string...reqd in budget service
            	Iterator lItr = lLstPnsnrCode.iterator();
            	//lStrPnsnCode.append("(");
                String lStrPnsnCode1;
                while (lItr.hasNext())
                {
                	lStrPnsnCode1 = (String) lItr.next();
                	lStrPnsnCode.append("'" + lStrPnsnCode1 + "'");
                    if (lItr.hasNext()){
                    	lStrPnsnCode.append(",");
                    }
                }
               // lStrPnsnCode.append(")");        	
                inputMap.put("StrLstPnsnCode", lStrPnsnCode.toString());
        	}
        	//have got list of Pensioner Code of all required pensioners
        	//now get all the required details
        	List<TrnPensionArrearDtls> lTrnPaymentLst = new ArrayList<TrnPensionArrearDtls>();
        	inputMap.put("TrnPaymentLst", lTrnPaymentLst);
        	if(lLstPnsnrCode != null && !lLstPnsnrCode.isEmpty())
        	{
        		lLstlObjMonthlyPensionVO = new ArrayList();
        		for(int i=0;i<lLstPnsnrCode.size();i++)
        		{
        			lStrPnsnrCode = (lLstPnsnrCode.get(i)).toString();
        			MonthlyCases lObjMonthlyCases = new MonthlyCases();
        			lArrComputeFlag = "N";
        			inputMap.put("ArrComputeFlag", lArrComputeFlag);
        			inputMap = lObjMonthlyCases.computeMonthlyPension(inputMap, lStrPnsnrCode);
        			List<MonthlyPensionBillVO> lLstMonthlyPensionBillVO = (List<MonthlyPensionBillVO>)inputMap.get("lLstMonthlyPensionBillVO");
        			lLstArrearsVO = (List<TrnPensionArrearDtls>)inputMap.get("lLstInsertArrears");
	        		    
        			if(inputMap.containsKey("lLstROPDtls"))
        			{
	        			List lLstTemp = (ArrayList) inputMap.get("lLstROPDtls");
	        			if(lLstTemp != null && lLstTemp.size() > 0)
	        			{
	        				lLstROPData.add(lLstTemp);
	        			}
        			}
	        			
        			for(int j=0;j<lLstMonthlyPensionBillVO.size();j++)
        			{
        				lObjMonthlyPensionVO = lLstMonthlyPensionBillVO.get(j);
            			if(lObjMonthlyPensionVO != null){
            				lTotalNetAmt = lTotalNetAmt + getInitAmt(lObjMonthlyPensionVO.getNetPensionAmount()).doubleValue();
            				lLstlObjMonthlyPensionVO.add(lObjMonthlyPensionVO);
            			}
        			}
        			for(int k=0;k<lLstArrearsVO.size();k++)
        			{
        				lObjArrearVO = lLstArrearsVO.get(k);
            			if(lObjArrearVO != null){
            				lLstFinalArrearsVO.add(lObjArrearVO);
            			}
        			}
        		}
        	}
        	if(lLstlObjMonthlyPensionVO != null && lLstlObjMonthlyPensionVO.size() > 0){
        		lStrFlag = "Y";
        	}
        	else{
        		lStrFlag = "N";
        	}
        	inputMap.put("Flag", lStrFlag);		//indicates whether there are any records for this bill
        	inputMap.put("MonthlyPensionList", lLstlObjMonthlyPensionVO);
        	inputMap.put("TotalNetAmt", lTotalNetAmt);
        	inputMap.put("ArrearList", lLstFinalArrearsVO);
        	
        	lTrnPaymentLst = (List<TrnPensionArrearDtls>)inputMap.get("TrnPaymentLst");
        	
        	/////////////////////////////////
        	HttpSession session = request.getSession();
        	session.setAttribute("PensionROPLst",lLstROPData);
        	session.setAttribute("MntlyTrnPaymentLst", lTrnPaymentLst);
        	/////////////////////////////////
        	
        	//inputMap.put("TreasuryName", "Gandhinagar Treasury Office");
        	//resObj = srvcLoc.executeService("DISPLAY_MNTHLY_PEN_BILL", inputMap);
        	//inputMap = (Map) resObj.getResultValue();
        	resObj = srvcLoc.executeService("PRINT_MNTHLY_PEN_BILL", inputMap);
        	inputMap = (Map) resObj.getResultValue();
        	//System.out.println(inputMap.get("PrintString").toString());
        	inputMap.put("MonthlyPrint", "Y");
        	resObj.setResultValue(inputMap);
    		resObj.setViewName("printMonthlyBill");
        }
        catch (Exception e)
        {
        	gLogger.error("Error is :" + e, e);
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }
       	return resObj;
    }
    
	/**
	 * Method to save monthly pension
	 * @param inputMap 
	 * @return ResultObject
	 */
    public ResultObject saveMonthlyPension(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        List<TrnPensionBillDtls> lLstPensionBillDtls = null;
        List<TrnPensionArrearDtls> lLstPensionArrearDtls = null;
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        String lPnsnTokenNo = null;
        Long lLngPnsnTokenNo = 0L;
        try
        {
        	setSessionInfo(inputMap);
        	//insert record in trn_pension_bill_hdr
        	lObjTrnPensionBillHdr = (TrnPensionBillHdr)inputMap.get("TrnPensionBillHdr");
        	TrnPensionBillHdrDAO lObjPensionHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class, serv.getSessionFactory());
        	lObjPensionHdrDAO.create(lObjTrnPensionBillHdr);
        	//insert multiple records in trn_pension_bill_dtls
        	lLstPensionBillDtls = (List<TrnPensionBillDtls>) inputMap.get("TrnPensionBillDtls");
        	TrnPensionBillDtls lObjPensionBillDtls = null;
            Iterator<TrnPensionBillDtls> lItrMonthly = lLstPensionBillDtls.iterator();
            while (lItrMonthly.hasNext())
            {
            	lObjPensionBillDtls = lItrMonthly.next();
            	saveMonthlyDtls(lObjPensionBillDtls, inputMap);
            }
            
            Long lLngPnsnBillNo = Long.valueOf(lObjTrnPensionBillHdr.getBillNo());
            
            inputMap.put("TrnPensionBillDtls", lLstPensionBillDtls);
            
            // insert multiple records in trn_pension_arrear_dtls
            lLstPensionArrearDtls = (List<TrnPensionArrearDtls>) inputMap.get("TrnPensionArrearDtls");
            TrnPensionArrearDtls lObjPensionArrearDtls = null;
            Iterator<TrnPensionArrearDtls> lItrArrear = lLstPensionArrearDtls.iterator();
            while (lItrArrear.hasNext())
            {
            	lObjPensionArrearDtls = lItrArrear.next();
            	lObjPensionArrearDtls.setBillNo(lLngPnsnBillNo.toString());
            	saveArrearDtls(lObjPensionArrearDtls, inputMap);
            }
            inputMap.put("TrnPensionArrearDtls", lLstPensionArrearDtls);
            
            //////////////////////////////
            HttpSession session = request.getSession();
        	List lLstROPData = (ArrayList) session.getAttribute("PensionROPLst");
        	List<TrnPensionArrearDtls> lTrnPaymentLst = (List<TrnPensionArrearDtls>) session.getAttribute("MntlyTrnPaymentLst"); 
        	
        	session.removeAttribute("PensionROPLst");
        	session.removeAttribute("MntlyTrnPaymentLst");
        	
        	if(lLstROPData != null && lLstROPData.size() > 0)
        	{
        		Iterator lItrROP = lLstROPData.iterator();
            	List lLstROPRecord = null;
                while (lItrROP.hasNext())
                {
                	lLstROPRecord = new ArrayList();
                	lLstROPRecord = (ArrayList)lItrROP.next();
                	saveROPDtls(lLstROPRecord, inputMap);
                }
        	}
        	
        	if(lTrnPaymentLst != null && lTrnPaymentLst.size() > 0)
        	{
            	TrnPensionArrearDtls lObjArrearVO = null;
            	for(int y=0; y<lTrnPaymentLst.size();y++)
            	{
            		lObjArrearVO = lTrnPaymentLst.get(y);
            		lObjArrearVO = getArrearsVOForTransaction(lObjArrearVO);
            		lObjArrearVO.setBillNo(lLngPnsnBillNo.toString());
            		saveArrearDtls(lObjArrearVO, inputMap);
            	}
        	}
        	
            //////////////////////////////
        	lPnsnTokenNo = lObjTrnPensionBillHdr.getTokenNo();        	
        	if(lPnsnTokenNo != null)
        	{
        		OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,serv.getSessionFactory());
        		lLngPnsnTokenNo = Long.parseLong(lPnsnTokenNo);
            	//lObjTokenStatusDAO.updateTokenStatus(lLngPnsnTokenNo, gStrLocCode, lLngPnsnBillNo, gLngUserId, gLngPostId,BillProcConstants.INWARD_TYPE_BILL);
        	}
        	objRes.setResultValue(inputMap);
        }
        catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
        return objRes;
    }
	
    private TrnPensionArrearDtls getArrearsVOForTransaction(TrnPensionArrearDtls lobjArrVO) throws Exception
    {
    	TrnPensionArrearDtls lObjArrearVO = null;
    	
    	try {
    			lObjArrearVO = new TrnPensionArrearDtls();

	  			lObjArrearVO.setPensionRequestId(lobjArrVO.getPensionRequestId());
	  			lObjArrearVO.setPensionerCode(lobjArrVO.getPensionerCode());
	  			lObjArrearVO.setArrearFieldType(lobjArrVO.getArrearFieldType());
	  			lObjArrearVO.setOldAmountPercentage(new BigDecimal("0.00"));
	  			lObjArrearVO.setNewAmountPercentage(lobjArrVO.getDifferenceAmount());
	  			lObjArrearVO.setEffectedFromYyyymm(lobjArrVO.getEffectedFromYyyymm());
	  			lObjArrearVO.setEffectedToYyyymm(lobjArrVO.getEffectedFromYyyymm());
	  			lObjArrearVO.setDifferenceAmount(lobjArrVO.getDifferenceAmount());
	  			lObjArrearVO.setTotalDifferenceAmt(lobjArrVO.getDifferenceAmount());
	  			lObjArrearVO.setPaymentFromYyyymm(lobjArrVO.getPaymentFromYyyymm());
	  			lObjArrearVO.setPaymentToYyyymm(lobjArrVO.getPaymentFromYyyymm());
	  			lObjArrearVO.setCreatedDate(DBUtility.getCurrentDateFromDB());
	  			lObjArrearVO.setCreatedPostId(new BigDecimal(gLngPostId));
	  			lObjArrearVO.setCreatedUserId(new BigDecimal(gLngUserId));
		} 
    	catch (Exception e) {
			gLogger.error(" Error is : " + e, e); 
			throw(e);
		}
    	return lObjArrearVO;
    }
   
	private void saveMonthlyDtls(TrnPensionBillDtls lObjPensionBillDtls, Map inputMap) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	
    	try {
    		TrnPensionBillDtlsDAO lObjPensionDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, serv.getSessionFactory());
    		lObjPensionBillDtls.setTrnPensionBillDtlsId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", inputMap)));
    		lObjPensionDtlsDAO.create(lObjPensionBillDtls);
		} 
    	catch (Exception e) {
			gLogger.error(" Error is : " + e, e); 
			throw(e);
		}
    }
    
	private void saveArrearDtls(TrnPensionArrearDtls lObjPensionArrearDtls, Map inputMap) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	
    	try {
    		TrnPensionArrearDtlsDAO lObjArrearDtlsDAO = new TrnPensionArrearDtlsDAOImpl(TrnPensionArrearDtls.class,serv.getSessionFactory());
    		lObjPensionArrearDtls.setPensionArrearDtlsId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_arrear_dtls", inputMap)));
    		lObjArrearDtlsDAO.create(lObjPensionArrearDtls);
		} 
    	catch (Exception e) {
			gLogger.error(" Error is : " + e, e); 
			throw(e);
		}
    }
	
	private void saveROPDtls(List lLstROP, Map inputMap) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	
    	try {
    		TrnPensionRqstDtlsDAOImpl lObjrqstHdr = new TrnPensionRqstDtlsDAOImpl(TrnPensionRqstHdr.class,serv.getSessionFactory());
    		
    		TrnPensionRqstHdr lObjRqstHdrVO = new TrnPensionRqstHdr();
    		lObjRqstHdrVO = lObjrqstHdr.read((Long)lLstROP.get(0));
    		lObjRqstHdrVO.setBasicPensionAmount(new BigDecimal(lLstROP.get(4).toString()));
    		lObjRqstHdrVO.setFp1Amount(new BigDecimal(lLstROP.get(5).toString()));
    		lObjRqstHdrVO.setFp2Amount(new BigDecimal(lLstROP.get(6).toString()));
    		lObjrqstHdr.update(lObjRqstHdrVO);
    		
    		//now need to update the rop records
    		NewPensionBillDAOImpl lObjNewPensionDAO = new NewPensionBillDAOImpl(serv.getSessionFactory());
    		Long lPKofROP = 0L;
    		TrnPnsncaseRopRlt lobjROP = null;
    		TrnPnsncaseRopRltDAO lObjROPDAO = new TrnPnsncaseRopRltDAO(TrnPnsncaseRopRlt.class,serv.getSessionFactory());
    		
    		if("Y".equals(lLstROP.get(3).toString()))			//for 1996 updation
    		{
    			lPKofROP = lObjNewPensionDAO.getPKOfPnsncaseROPRlt(lLstROP.get(1).toString(), "1996");
    			lobjROP = lObjROPDAO.read(lPKofROP);
    			lobjROP.setRopPaid("Y");
    			lObjROPDAO.update(lobjROP);
    			
    			if("Y".equals(lLstROP.get(2).toString()))		//for 1986 updation
        		{
        			lPKofROP = lObjNewPensionDAO.getPKOfPnsncaseROPRlt(lLstROP.get(1).toString(), "1986");
        			lobjROP = lObjROPDAO.read(lPKofROP);
        			lobjROP.setRopPaid("Y");
        			lObjROPDAO.update(lobjROP);
        		}
    		}
    		
		} 
    	catch (Exception e) {
			gLogger.error(" Error is : " + e, e); 
			throw(e);
		}
    }
	/**
	 * view saved monthly pension Bill
	 * 
	 */
	
	public ResultObject viewMonthlyBill(Map inputMap)
    {            
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrPnsnerName = null;
        List<TrnPensionBillDtls> lLstObjTrnPensionBillDtls = new ArrayList<TrnPensionBillDtls>();
        TrnPensionBillDtls lObjTrnPensionBillDtls = null;
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        MonthlyPensionBillVO lObjMonthlyPensionVO = null;
        List lLstMonthlyPensionVO = new ArrayList<MonthlyPensionBillVO>();
        Double lDPPerAmt = 0.0;
        Double lTIPerAmt = 0.0;
        String lStrAccNo = null;
        Double lBasic = 0.0;
        Double lPensionCut = 0.0;
        BigDecimal DPPer = BigDecimal.valueOf(0);
        BigDecimal TIPer = BigDecimal.valueOf(0);
        Double lTotalNetAmt = 0.00;
        String lStrTrnPensionBillHdrId = null;
        String lStrBillNo = null;
        try
        {
            setSessionInfo(inputMap);
            if(inputMap.containsKey("BillNo"))
            {  	
            	lStrBillNo = (String) inputMap.get("BillNo").toString();
            }
            else
            {   
            	lStrBillNo = StringUtility.getParameter("billNo", request).trim();
            }
        	MonthlyPensionBillDAOImpl lObjMonthlyBillDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
        	if(lStrBillNo != null && lStrBillNo.length() > 0 ) 
        	{
        		lObjTrnPensionBillHdr = lObjMonthlyBillDAO.getTrnPensionBillHdr(lStrBillNo);
        		if(lObjTrnPensionBillHdr != null)
        		{
        			lStrTrnPensionBillHdrId = lObjTrnPensionBillHdr.getTrnPensionBillHdrId().toString();
            		// Getting the List of ObjectVo of  TrnPensionBillDtlsVO
            		lLstObjTrnPensionBillDtls = lObjMonthlyBillDAO.getTrnPensionBillDtls(lStrTrnPensionBillHdrId);
        		}
        	
	        	if(lLstObjTrnPensionBillDtls != null && !lLstObjTrnPensionBillDtls.isEmpty())
	        	{
	        		for(int i=0;i<lLstObjTrnPensionBillDtls.size();i++)
	        		{
	        			lObjTrnPensionBillDtls = lLstObjTrnPensionBillDtls.get(i);
	        			/*if(lStrPnsrCode != null)
	        			{
	        				lObjTrnPensionRqstHdrVO = lObjMonthlyBillDAO.getTrnPensionRqstHdrDtlsFromPnsnerCode(lStrPnsrCode, lStrStatus);
	        				if(lObjTrnPensionRqstHdrVO != null)
	        				{
	        					lStrPPONo = lObjTrnPensionRqstHdrVO.getPpoNo();
		    					inputMap.put("SchemeType", lObjTrnPensionRqstHdrVO.getSchemeType());
		                        List lLstParameters = new ArrayList();
		                        lLstParameters.add("locName");
		                        lLstParameters.add("CmnLocationMst");
		                        lLstParameters.add("locId ");
		                        lLstParameters.add(lObjTrnPensionRqstHdrVO.getLocationCode());
		                        lLstParameters.add("-1");
		                        List lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
		                        if(lLstRes != null && lLstRes.size()>0){
		                            lStrTreasuryName = lLstRes.get(0).toString();
		                        }
		                          inputMap.put("TreasuryName", lStrTreasuryName);
	        				}
	        		
	        			}*/
	        			
	        			lStrPnsnerName = lObjTrnPensionBillDtls.getPensionerName();
	        			lStrAccNo = lObjTrnPensionBillDtls.getAccountNo();
	        			lBasic = getInitAmt(lObjTrnPensionBillDtls.getPensionAmount()).doubleValue();
	        	        lPensionCut = getInitAmt(lObjTrnPensionBillDtls.getPensnCutAmount()).doubleValue();
	        	        lDPPerAmt = getInitAmt(lObjTrnPensionBillDtls.getDpAmount()).doubleValue();
	        	        lTIPerAmt = Double.valueOf(lObjTrnPensionBillDtls.getTiAmount().toString());
	        			lObjMonthlyPensionVO = new MonthlyPensionBillVO();
		            	lObjMonthlyPensionVO.setPpoNo(lObjTrnPensionBillDtls.getPpoNo().toString());
		            	lObjMonthlyPensionVO.setPensionerName(lStrPnsnerName);
		            	lObjMonthlyPensionVO.setAccountNo(lStrAccNo);
		            	lObjMonthlyPensionVO.setAllnBf11156(getInitAmt(lObjTrnPensionBillDtls.getAllcationBf11156()));
		            	lObjMonthlyPensionVO.setAllnAf11156(getInitAmt(lObjTrnPensionBillDtls.getAllcationAf11156()));
		            	lObjMonthlyPensionVO.setAllnAf10560(getInitAmt(lObjTrnPensionBillDtls.getAllcationAf10560()));
		            	lObjMonthlyPensionVO.setBasicPensionAmount(new BigDecimal(lBasic));
		            	lObjMonthlyPensionVO.setDpPercent(DPPer);
		            	lObjMonthlyPensionVO.setTiPercent(TIPer);
		            	lObjMonthlyPensionVO.setDpPercentAmount(new BigDecimal(lDPPerAmt));
		            	lObjMonthlyPensionVO.setTiPercentAmount(new BigDecimal(lTIPerAmt));
		            	lObjMonthlyPensionVO.setMedicalAllowenceAmount(getInitAmt(lObjTrnPensionBillDtls.getMedicalAllowenceAmount()));
		            	lObjMonthlyPensionVO.setCvpMonthlyAmount(getInitAmt(lObjTrnPensionBillDtls.getCvpMonthAmount()));
		            	lObjMonthlyPensionVO.setTIArrearsAmount(getInitAmt(lObjTrnPensionBillDtls.getTiArrearAmount()));
		            	lObjMonthlyPensionVO.setOtherArrearsAmount(getInitAmt(lObjTrnPensionBillDtls.getArrearAmount()));
		            	lObjMonthlyPensionVO.setItCutAmount(getInitAmt(lObjTrnPensionBillDtls.getIncomeTaxCutAmount()));
		            	lObjMonthlyPensionVO.setSpecialCutAmount(getInitAmt(lObjTrnPensionBillDtls.getSpecialCutAmount()));
		            	lObjMonthlyPensionVO.setOtherBenefit(getInitAmt(lObjTrnPensionBillDtls.getOtherBenefits()));
		            	lObjMonthlyPensionVO.setOMR(getInitAmt(lObjTrnPensionBillDtls.getOmr()));
		            	lObjMonthlyPensionVO.setPensionCutAmount(new BigDecimal(lPensionCut));
		            	lObjMonthlyPensionVO.setRecoveryAmount(getInitAmt(lObjTrnPensionBillDtls.getRecoveryAmount()));
		            	lObjMonthlyPensionVO.setNetPensionAmount(getInitAmt(lObjTrnPensionBillDtls.getReducedPension()));
		            	lObjMonthlyPensionVO.setPersonalPension(getInitAmt(lObjTrnPensionBillDtls.getPersonalPensionAmount()));
		            	lObjMonthlyPensionVO.setIr(getInitAmt(lObjTrnPensionBillDtls.getIrAmount()));
		            	lObjMonthlyPensionVO.setMOComm(getInitAmt(lObjTrnPensionBillDtls.getMoCommission()));
		            	lTotalNetAmt = lTotalNetAmt + getInitAmt(lObjTrnPensionBillDtls.getReducedPension()).doubleValue();
		            	lLstMonthlyPensionVO.add(lObjMonthlyPensionVO);
	        		}
	        	}
                  
	        	inputMap.put("MonthlyPensionList", lLstMonthlyPensionVO);
	        	inputMap.put("TotalNetAmt", lTotalNetAmt);
	        	inputMap.put("Date", lObjTrnPensionBillHdr.getForMonth());
	        	inputMap.put("PnsnHeadCode", lObjTrnPensionBillHdr.getHeadCode());
	        	inputMap.put("PensionType", lObjTrnPensionBillHdr.getBillType());
	        	inputMap.put("PnsnBillTokenNo", lObjTrnPensionBillHdr.getTokenNo());
	        	MonthlyPensionBillDAO lObjMonthlyDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
	        	String lStrHeadcode = lObjTrnPensionBillHdr.getHeadCode().toString();
	        	String lStrBank = lObjTrnPensionBillHdr.getBankCode();
	        	String lStrBranch = lObjTrnPensionBillHdr.getBranchCode();
	        	if(lStrBank == null)
	         	{
	        		lStrBank = lObjMonthlyDAO.getBankCode(lStrBranch, gStrLocCode);
	         	}
	        	inputMap.put("Bank", lStrBank);
	        	inputMap.put("Branch", lStrBranch);
	        	inputMap.put("HeadCode", lStrHeadcode);
	        	CommonPensionDAOImpl lObjMonthlyPnsnDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
	        	String lStrDesc = lObjMonthlyPnsnDAO.getAllHeadCodeDesc(lStrHeadcode, SessionHelper.getLangId(inputMap));
	        	inputMap.put("HeadCodeDesc", lStrDesc);
	        	
	        	Integer date = lObjTrnPensionBillHdr.getForMonth();
	        	Integer month = date % 100;
	        	Integer year = date / 100;
	        	String lStrMonth = month.toString();
	        	String lStrYear = year.toString();
	        	
	//        	find bank name and branch name
	        	/*List lLstBankBranch = lObjCmnDao.getBranchByBranchId(lObjTrnPensionBillHdr.getBranchCode(),gLngLangId);
	        	String lStrBankName = lLstBankBranch.get(2).toString();
	        	String lStrBranchName = lLstBankBranch.get(2).toString();*/
	        	String lStrBankName = lObjMonthlyDAO.getBankName(lStrBank);
	        	String lStrBranchName = lObjMonthlyDAO.getBranchName(lStrBank, lStrBranch);
	        	List lLstMonthDtls = (ArrayList) lObjMonthlyDAO.getMonthDtls(lStrMonth, gLngLangId);
	        	
	        	inputMap.put("BankName",lStrBankName);
	        	inputMap.put("BranchName",lStrBranchName);
	        	inputMap.put("MonthName",lLstMonthDtls.get(1).toString());
	        	inputMap.put("MonthCode",lLstMonthDtls.get(0).toString());
	        	inputMap.put("ForYear",lStrYear);
	        	inputMap.put("ForBillYear",lStrYear);
	        }
        	//inputMap.put("TreasuryName", "Gandhinagar Treasury Office");
        	//resObj = srvcLoc.executeService("DISPLAY_MNTHLY_PEN_BILL", inputMap);
        	//inputMap = (Map) resObj.getResultValue();
        	resObj = srvcLoc.executeService("PRINT_MNTHLY_PEN_BILL", inputMap);
        	inputMap = (Map) resObj.getResultValue();
        	//System.out.println(inputMap.get("PrintString").toString());
        	inputMap.put("MonthlyPrint", "Y");
        	resObj.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            resObj.setThrowable(e);
            resObj.setResultValue(null);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
       return resObj;
    }
	private BigDecimal getInitAmt(BigDecimal lBdAmt)
	{
		BigDecimal lBdRetrnAmt = new BigDecimal(0); 
		if(lBdAmt != null)
		{
			lBdRetrnAmt = lBdAmt;
		}
		return lBdRetrnAmt; 
		
	}
	public ResultObject validateBill(Map inputMap) throws Exception
    {       
		
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        try
        {
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            String lstrForMonth = StringUtility.getParameter("cmbForMonth", request).trim();
            String lstrForYear = StringUtility.getParameter("cmbForYear", request).trim();
            String lstrBank = StringUtility.getParameter("cmbBank", request).trim();
            String lstrBranch = StringUtility.getParameter("cmbBranch", request).trim();
            String lstrHeadCode = StringUtility.getParameter("cmbHeadCode", request).trim();
            String lStrScheme = StringUtility.getParameter("cmbForScheme", request).trim();
            String lStrDate = null;
            MonthlyPensionBillDAOImpl lObjPensionBillDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
            
            if(Integer.parseInt(lstrForMonth) < 10)
        	{
        		lStrDate = lstrForYear+"0"+lstrForMonth;
        	}
        	else
        	{
        		lStrDate = lstrForYear+lstrForMonth;
        	}
        	if(lstrBank.equals("") && lstrBank.length() <= 0)
        	{
        		lstrBank = lObjPensionBillDAO.getBankCode(lstrBranch, gStrLocCode);
        	}
            boolean lValidFlag = lObjPensionBillDAO.isValidBill(lStrDate,lstrBank,lstrBranch,lstrHeadCode,lStrScheme);
            StringBuilder lStrGrant = new StringBuilder();
            lStrGrant.append(" <BILL> ");
            lStrGrant.append(lValidFlag);
            lStrGrant.append(" </BILL> ");
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
            gLogger.info("lStrResult"+lStrResult);
            inputMap.put("ajaxKey", lStrResult);
            objRes.setViewName("ajaxData");
            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw(e);
        }

        return objRes;      
    }
	
	
	public ResultObject loadMonthlyReport(Map inputMap)
	{
    	ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        String lStrLangId=SessionHelper.getLangId(inputMap).toString();
        
        setSessionInfo(inputMap);
        
		try
		{
			//To populate month combo....
            List<SgvaMonthMst> lObjSgvaMonthMst=new ArrayList<SgvaMonthMst>();
            
            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());            
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		String currDate = sdf.format(gDate);		
    		
    		String currentMonth = currDate.substring(5, 7);
    		String currentYear = currDate.substring(0,4);	
    		
    		inputMap.put("CurrentMonth", currentMonth);
    		inputMap.put("CurrentYear", currentYear);
            
            //Returning VO array...
            lObjSgvaMonthMst=lObjCommonPensionDAO.getSgvaMonthMstVO(lStrLangId);
            
            if(lObjSgvaMonthMst != null)
            {
            	inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMst);
            }
            
        	//To populate year combo....
            List<SgvcFinYearMst> lObjSgvcFinYearMst=new ArrayList<SgvcFinYearMst>();
            
            //Returning VO array...
            lObjSgvcFinYearMst=lObjCommonPensionDAO.getSgvcFinYearMstVO(lStrLangId);
            
            if(lObjSgvcFinYearMst != null)
            {
            	inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMst);
            }
			
            //To populate Branch combo...
            MonthlyPensionBillDAOImpl lObjMonthlyPensionBill = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
			List listPnsnBranch = (List)lObjMonthlyPensionBill.getAllBranchPrint(gStrLocCode);
			
			if(listPnsnBranch != null)
			{
				inputMap.put("listBranch", listPnsnBranch);
			}
            
            //To populate HeadCode combo...
			List listPnsnHeadCode = (List)lObjCommonPensionDAO.getAllHeadCode();
			
			if(listPnsnHeadCode != null)
			{
				inputMap.put("listHeadCode", listPnsnHeadCode);
			}
			
			//To populate Scheme combo...
			List listPnsnScheme = (List)lObjMonthlyPensionBill.getAllScheme(gLngLangId);
			
			if(listPnsnScheme != null)
			{
				inputMap.put("listScheme", listPnsnScheme);
			}
			resObj.setResultValue(inputMap);
			resObj.setViewName("printMonthlyPara");
		}
		catch(Exception e)
		{
            gLogger.error("Error is :" + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
		}
		return resObj;
	}
	
	public ResultObject viewMonthlyReport(Map inputMap)
    {            
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
		String lStrMonth = null;
		String lStrYear = null;
		String lStrBank = null;
		String lStrBranch = null;
		String lStrHeadCode = null;
		String lStrScheme = null;
		String lStrDate = null;
        try
        {
        	setSessionInfo(inputMap);
            MonthlyPensionBillDAO lObjMonthlyDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
            lStrMonth = StringUtility.getParameter("cmbForMonth",request).trim();
         	lStrYear = StringUtility.getParameter("cmbForYear",request).trim();
         	lStrBank = StringUtility.getParameter("cmbBank",request).trim();
         	lStrBranch = StringUtility.getParameter("cmbBranch",request).trim();
         	lStrHeadCode = StringUtility.getParameter("cmbHeadCode",request).trim();
         	lStrScheme = StringUtility.getParameter("cmbForScheme",request).trim();
         	inputMap.put("Scheme", lStrScheme);
         	inputMap.put("BranchCode", lStrBranch);
         	if(Integer.parseInt(lStrMonth) < 10){
         		lStrDate = lStrYear+"0"+lStrMonth;
         	}
         	else{
         		lStrDate = lStrYear+lStrMonth;
         	}
         	if(lStrBank.equals("") && lStrBank.length() <= 0)
         	{
         		lStrBank = lObjMonthlyDAO.getBankCode(lStrBranch, gStrLocCode);
         	}
         	
         	//now get bill no for the current bill
         	String lBillNo = lObjMonthlyDAO.getBillNo(lStrDate, lStrBank, lStrBranch, lStrHeadCode, lStrScheme);
        	inputMap.put("BillNo", lBillNo);
        	
        	resObj = viewMonthlyBill(inputMap);
        	inputMap = (Map) resObj.getResultValue();
        	//System.out.println(inputMap.get("PrintString").toString());
        	resObj.setResultValue(inputMap);
    		resObj.setViewName("printMonthlyBill");
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            resObj.setThrowable(e);
            resObj.setResultValue(null);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
       return resObj;
    }
	
	/*public ResultObject viewMonthlyReport(Map inputMap)
    {            
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        List<TrnPensionBillDtls> lLstObjTrnPensionBillDtls = new ArrayList<TrnPensionBillDtls>();
        TrnPensionBillDtls lObjTrnPensionBillDtls = null;
        MonthlyPensionBillVO lObjMonthlyPensionVO = null;
        List<MonthlyPensionBillVO> lLstMonthlyPensionVO = new ArrayList<MonthlyPensionBillVO>();
        
		String lStrMonth = null;
		String lStrYear = null;
		String lStrBank = null;
		String lStrBranch = null;
		String lStrHeadCode = null;
		String lStrScheme = null;
		String lStrDate = null;
		String lStrPnsnerName = null;
		
		Double lDPPerAmt = 0.0;
        Double lTIPerAmt = 0.0;
        String lStrAccNo = null;
        Double lBasic = 0.0;
        Double lPensionCut = 0.0;
        BigDecimal DPPer = BigDecimal.valueOf(0);
        BigDecimal TIPer = BigDecimal.valueOf(0);
        try
        {
        	setSessionInfo(inputMap);
            MonthlyPensionBillDAO lObjMonthlyDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
            lStrMonth = StringUtility.getParameter("cmbForMonth",request).trim();
         	lStrYear = StringUtility.getParameter("cmbForYear",request).trim();
         	lStrBank = StringUtility.getParameter("cmbBank",request).trim();
         	lStrBranch = StringUtility.getParameter("cmbBranch",request).trim();
         	lStrHeadCode = StringUtility.getParameter("cmbHeadCode",request).trim();
         	lStrScheme = StringUtility.getParameter("cmbForScheme",request).trim();
         	inputMap.put("Scheme", lStrScheme);
         	if(Integer.parseInt(lStrMonth) < 10){
         		lStrDate = lStrYear+"0"+lStrMonth;
         	}
         	else{
         		lStrDate = lStrYear+lStrMonth;
         	}
         	if(lStrBank.equals("") && lStrBank.length() <= 0)
         	{
         		lStrBank = lObjMonthlyDAO.getBankCode(lStrBranch);
         	}
         	
         	if(!lStrHeadCode.equals(""))	//user has selected a headcode
         	{
         		//now get bill no for the current bill
             	String lBillNo = lObjMonthlyDAO.getBillNo(lStrDate, lStrBank, lStrBranch, lStrHeadCode, lStrScheme);
            	inputMap.put("BillNo", lBillNo);
            	
            	resObj = viewMonthlyBill(inputMap);
            	inputMap = (Map) resObj.getResultValue();
            	//System.out.println(inputMap.get("PrintString").toString());
            	resObj.setResultValue(inputMap);
        		resObj.setViewName("printMonthlyBill");
         	}
         	else						//get data for all headcode
         	{
         		//////////////////////////////////
             	             
             	MonthlyPensionBillDAOImpl lObjMonthlyBillDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
             	lLstObjTrnPensionBillDtls = lObjMonthlyBillDAO.getTrnPensionBillDtlsReport(lStrDate,lStrBranch,lStrScheme);
    		
    	
    	    	if(lLstObjTrnPensionBillDtls != null && !lLstObjTrnPensionBillDtls.isEmpty())
    	    	{
    	    		for(int i=0;i<lLstObjTrnPensionBillDtls.size();i++)
    	    		{
    	    			lObjTrnPensionBillDtls = lLstObjTrnPensionBillDtls.get(i);
    	    			
    	    			inputMap.put("TreasuryName", "Gandhinagar Treasury Office");
    	    			
    	    			
    	    			lStrPnsnerName = lObjTrnPensionBillDtls.getPensionerName();
    	    			lStrAccNo = lObjTrnPensionBillDtls.getAccountNo();
    	    			lBasic = getInitAmt(lObjTrnPensionBillDtls.getPensionAmount()).doubleValue();
    	    	        lPensionCut = getInitAmt(lObjTrnPensionBillDtls.getPensnCutAmount()).doubleValue();
    	    	        lDPPerAmt = getInitAmt(lObjTrnPensionBillDtls.getDpAmount()).doubleValue();
    	    	        lTIPerAmt = Double.valueOf(lObjTrnPensionBillDtls.getTiAmount().toString());
    	    			lObjMonthlyPensionVO = new MonthlyPensionBillVO();
    	            	lObjMonthlyPensionVO.setPpoNo(lObjTrnPensionBillDtls.getPpoNo().toString());
    	            	lObjMonthlyPensionVO.setPensionerName(lStrPnsnerName);
    	            	lObjMonthlyPensionVO.setAccountNo(lStrAccNo);
    	            	lObjMonthlyPensionVO.setAllnBf11156(getInitAmt(lObjTrnPensionBillDtls.getAllcationBf11156()));
    	            	lObjMonthlyPensionVO.setAllnAf11156(getInitAmt(lObjTrnPensionBillDtls.getAllcationAf11156()));
    	            	lObjMonthlyPensionVO.setAllnAf10560(getInitAmt(lObjTrnPensionBillDtls.getAllcationAf10560()));
    	            	lObjMonthlyPensionVO.setBasicPensionAmount(new BigDecimal(lBasic));
    	            	lObjMonthlyPensionVO.setDpPercent(DPPer);
    	            	lObjMonthlyPensionVO.setTiPercent(TIPer);
    	            	lObjMonthlyPensionVO.setDpPercentAmount(new BigDecimal(lDPPerAmt));
    	            	lObjMonthlyPensionVO.setTiPercentAmount(new BigDecimal(lTIPerAmt));
    	            	lObjMonthlyPensionVO.setMedicalAllowenceAmount(getInitAmt(lObjTrnPensionBillDtls.getMedicalAllowenceAmount()));
    	            	lObjMonthlyPensionVO.setCvpMonthlyAmount(getInitAmt(lObjTrnPensionBillDtls.getCvpMonthAmount()));
    	            	lObjMonthlyPensionVO.setTIArrearsAmount(getInitAmt(lObjTrnPensionBillDtls.getTiArrearAmount()));
    	            	lObjMonthlyPensionVO.setOtherArrearsAmount(getInitAmt(lObjTrnPensionBillDtls.getArrearAmount()));
    	            	lObjMonthlyPensionVO.setItCutAmount(getInitAmt(lObjTrnPensionBillDtls.getIncomeTaxCutAmount()));
    	            	lObjMonthlyPensionVO.setSpecialCutAmount(getInitAmt(lObjTrnPensionBillDtls.getSpecialCutAmount()));
    	            	lObjMonthlyPensionVO.setPensionCutAmount(new BigDecimal(lPensionCut));
    	            	lObjMonthlyPensionVO.setRecoveryAmount(getInitAmt(lObjTrnPensionBillDtls.getRecoveryAmount()));
    	            	lObjMonthlyPensionVO.setNetPensionAmount(getInitAmt(lObjTrnPensionBillDtls.getReducedPension()));
    	            	lObjMonthlyPensionVO.setPersonalPension(getInitAmt(lObjTrnPensionBillDtls.getPersonalPensionAmount()));
    	            	lObjMonthlyPensionVO.setIr(getInitAmt(lObjTrnPensionBillDtls.getIrAmount()));
    	            	lObjMonthlyPensionVO.setMOComm(getInitAmt(lObjTrnPensionBillDtls.getMoCommission()));
    	            	lLstMonthlyPensionVO.add(lObjMonthlyPensionVO);
    	    		}
    	    		
    	    	}
    	    	inputMap.put("MonthlyPensionList", lLstMonthlyPensionVO);
    	    	
    	    	inputMap.put("Bank", lStrBank);
            	inputMap.put("Branch", lStrBranch);
            	inputMap.put("HeadCode", lStrHeadCode);
            	CommonPensionDAOImpl lObjMonthlyPnsnDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
            	String lStrDesc = lObjMonthlyPnsnDAO.getAllHeadCodeDesc(lStrHeadCode, SessionHelper.getLangId(inputMap));
            	inputMap.put("HeadCodeDesc", lStrDesc);
            	
            	
//            	find bank name and branch name
            	List lLstBankBranch = lObjCmnDao.getBranchByBranchId(lObjTrnPensionBillHdr.getBranchCode(),gLngLangId);
            	String lStrBankName = lLstBankBranch.get(2).toString();
            	String lStrBranchName = lLstBankBranch.get(2).toString();
            	String lStrBankName = lObjMonthlyDAO.getBankName(lStrBank);
            	String lStrBranchName = lObjMonthlyDAO.getBranchName(lStrBank, lStrBranch);
            	List lLstMonthDtls = (ArrayList) lObjMonthlyDAO.getMonthDtls(lStrMonth, gLngLangId);
            	
            	inputMap.put("BankName",lStrBankName);
            	inputMap.put("BranchName",lStrBranchName);
            	inputMap.put("MonthName",lLstMonthDtls.get(1).toString());
            	inputMap.put("MonthCode",lLstMonthDtls.get(0).toString());
            	inputMap.put("ForYear",lStrYear);
            	inputMap.put("ForBillYear",lStrYear);

    	    	resObj = srvcLoc.executeService("DISPLAY_MNTHLY_PEN_BILL", inputMap);
    			inputMap = (Map) resObj.getResultValue();
    			resObj = srvcLoc.executeService("PRINT_MNTHLY_PEN_BILL", inputMap);
    			inputMap = (Map) resObj.getResultValue();
    			
            	////////////////////////////////////////
            	//System.out.println(inputMap.get("PrintString").toString());
            	resObj.setResultValue(inputMap);
        		resObj.setViewName("printMonthlyBill");
         	}
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            resObj.setThrowable(e);
            resObj.setResultValue(null);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
       return resObj;
    }*/
	
	   public ResultObject getBranchFromBranchCode(Map inputMap)
	    {        
	        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
	        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
	        String lStrBCBankcode = null;
	        String lStrBCBranchcode = null;
	        String lStrBCBranchName = null;
	        List BankList = null;
	        List BranchList = null;
	        try
	        {
	        	setSessionInfo(inputMap);
	            CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
	            List arrylstBranch = new ArrayList();
	            StringBuilder lStrRes = new StringBuilder();
	            String bankCodeFrmRequest = StringUtility.getParameter("txtbranchCode", request);
	            if (bankCodeFrmRequest.trim().length() > 0)
	            {
	                arrylstBranch = commonPensionDAO.getBranchByBranchId(bankCodeFrmRequest, SessionHelper
	                        .getLangId(inputMap),SessionHelper.getLocationCode(inputMap));
	            }
	            
	            if (arrylstBranch != null && arrylstBranch.size() > 0)
	            {
	                if (arrylstBranch.get(0) != null)
	                {
	                	lStrBCBankcode = arrylstBranch.get(0).toString();
	                }
	                if (arrylstBranch.get(1) != null)
	                {
	                	lStrBCBranchcode = arrylstBranch.get(1).toString();
	                }
	                if (arrylstBranch.get(2) != null)
	                {
	                	lStrBCBranchName = arrylstBranch.get(2).toString();
	                }
	            }
	            
	            lStrRes.append("<XMLDOC>");
	            
	            if(lStrBCBankcode != null && lStrBCBankcode.length() > 0)
	            {
	            	 //get list of banks valid for this auditor
		            BankList = commonPensionDAO.getAuditorBankCodeList(new BigDecimal(gLngUserId),new BigDecimal(gLngPostId));
		            
		            for(int x=0; x<BankList.size(); x++)
		            {
	            		ComboValuesVO lobj = (ComboValuesVO) BankList.get(x);
	            	
		            	if(lStrBCBankcode.equals(lobj.getId()))
		            	{
		            		//bank code is valid for this pensioner
		            		if(lStrBCBranchcode != null)
		            		{
//		            			if bank is valid then check if this branch is valid for this auditor
			            		//so now check for branch
					            BranchList = commonPensionDAO.getAuditorBranchCodeList(new BigDecimal(gLngUserId),new BigDecimal(gLngPostId),lStrBCBankcode,gStrLocCode);
					            
					            for(int y=0; y<BranchList.size(); y++)
					            {
				            		ComboValuesVO lobj2 = (ComboValuesVO) BranchList.get(y);
					            	if(lStrBCBranchcode.equals(lobj2.getId()))
					            	{
					            		//both bank and branch are valid for this auditor
					            		//hence we will display them 
					            		
					    	           
			    	                    lStrRes.append("<BANKCODE>" + lStrBCBankcode);
			    	                    lStrRes.append("</BANKCODE>");
			    	               
			    	                    lStrRes.append("<BRANCHCODE>" + lStrBCBranchcode);
			    	                    lStrRes.append("</BRANCHCODE>");
			    	              
			    	                    lStrRes.append("<BRANCHNAME>" + lStrBCBranchName);
			    	                    lStrRes.append("</BRANCHNAME>");
					            	}
					            }
		            		}
		            	}
		            }
	            }
	            lStrRes.append("</XMLDOC>");
	           
	            
	            
	            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrRes.toString()).toString();
	            inputMap.put("ajaxKey", lStrResult);
	            resObj.setResultValue(inputMap);
	            resObj.setViewName("ajaxData");
	        }
	        catch (Exception e)
	        {
	            gLogger.error(" Error is : " + e, e);
	            resObj.setResultValue(null);
	            resObj.setThrowable(e);
	            resObj.setResultCode(ErrorConstants.ERROR);
	            resObj.setViewName("errorPage");
	        }
	        return resObj;
	    }
	
    /**
     * Method to set Session variables
     * @param inputMap
     */
    private void setSessionInfo(Map inputMap)
    {
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocCode = SessionHelper.getLocationCode(inputMap);
        gDate = DBUtility.getCurrentDateFromDB();
    }
}
