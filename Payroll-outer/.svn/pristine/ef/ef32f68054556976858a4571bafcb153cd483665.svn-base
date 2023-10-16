package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.MonthlyPensionBillDAO;
import com.tcs.sgv.pension.dao.MonthlyPensionBillDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAO;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.NewPensionBillDAOImpl;
import com.tcs.sgv.pension.valueobject.MonthlyPensionBillVO;
import com.tcs.sgv.pension.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

public class MonthlyCases extends ServiceImpl{

	/* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for LangId */
    Long gLngLangId = null;

    /* Global Variable for DB Id */
    Long gLngDBId = null;
    
    /* Global Variable for Logger Class */
    Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for Current Date */
    Date gDate = null;
	
	public Map computeMonthlyPension(Map inputMap, String PensionerCode) throws Exception
	{
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
               
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
        MstPensionerHdr lObjMstPensionerHdrVO = null;
        List<MonthlyPensionBillVO> lLstMonthlyPensionBillVO = new ArrayList<MonthlyPensionBillVO>();
        MonthlyPensionBillVO lObjMonthlyPensionVO = null;
        List<TrnPensionArrearDtls> lLstInsertArrears = new ArrayList<TrnPensionArrearDtls>();
        TrnPensionArrearDtls lObjArrearVO = null;
                
        String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
        String lStrCaseStatus = bundleConst.getString("STATUS.APPROVED");
        String lStrRcvryFlag = bundleConst.getString("RECOVERY.MONTHLY");        
        String lStrMoneyOrder = "MONEY ORDER";
        String lStrArrTI = bundleConst.getString("ARREAR.TI");
        String lStrArrMA = bundleConst.getString("ARREAR.MA");
        String lStrArrPension = bundleConst.getString("ARREAR.PENSION");
        String lStrArrFP1 = bundleConst.getString("ARREAR.FP1");
        String lStrArrFP2 = bundleConst.getString("ARREAR.FP2");
        String lStrArrOMR = bundleConst.getString("ARREAR.OMR");
        String lStrCutPP = bundleConst.getString("CUT.PP");
        String lStrCutPT = bundleConst.getString("CUT.PT");
        String lStrCutIT = bundleConst.getString("CUT.IT");
        String lStrCutST = bundleConst.getString("CUT.ST");
        String lStrCutCVP = bundleConst.getString("CUT.CVP");
        String lStrCutPerBen = bundleConst.getString("CMN.PBENEFIT");
        String lStrCutTmpBen = bundleConst.getString("CMN.TBENEFIT");        
        //String lStrVoluntary = bundleConst.getString("CMN.VOLUNTARY");
		//String lStrFamilyLost = bundleConst.getString("CMN.FAMILYLOST");
		List lLstAccNo = null;
        String lStrforMonth = null;
        String lStrforYear = null;
        String lStrBank = null;
        String lStrBranch = null;
        String lStrHeadCode = null;
        String lStrDate = null;
        String lDateOfDeathYYYYMM = null;
        String lDayOfDeathDD = null;
        
        String lStrScheme = null;
        String lStrPnsnrCode = null;
        String lStrPnsnerName = null;
        String lStrPPONo = null;
        String lStrAccNo = null;
        Long lDPPer = 0L;
        Long lTIPer = 0L;
        Long lStrPenRqstId = 0L;
        Double lCVPMonthlyAmt = 0D;
        Double lMAAmt = 0D;
        Double lFP1Amt = 0D;
        Double lFP2Amt = 0D;
        Double lDPPerAmt = 0D;
        Double lTIPerAmt = 0D;
        Double lBasicPensionAmt = 0D;
        Double lPensionCutAmt = 0D;
        Double lSpecialCutAmt = 0D;
        Double lITCutAmt = 0D;
        Double lOtherBenefits = 0D;
        Double lRecoveryAmt = 0D;
        Double lArrearAmt = 0D;
        Double lNetPensionAmt = 0D;
        Date lDateOfDeath = null;
        Date lFP1Date = null;
        Date lFP2Date = null;        
		Date lEndDate = null;
		String endDays = null;
		Integer EDays = 0;
		Integer EDate = 0;
        Double OMR = 0D;
     //   Double lDP = 0D;
     //   Double lTI = 0D;
        Double lPendingArrear = 0D;
        Integer lLastPaidMonth = 0;
        List lItCutDtls = new ArrayList();
        List lArrearDtls = new ArrayList();
        Integer lPrevMonth = 0;
        Integer lDays =0;
        Integer date = 0;
        Integer date1 = 0;
        Integer lIntDate = 0;
        String lArrComputeFlag = null;
        List lLstFPmembers = new ArrayList();
        String lStrFPFlag = "N";
        List fpMemberList = null;        
       // Double lTmpNetAmt = 0D;
       // String fp1Date = null;
       // String fp2Date = null;
        String endDate = null;
        Double allnBf56 = 0D;
        Double allnAf56 = 0D;
        Double allnAf60 = 0D;
		Double lPerPension = 0D;
		Double lIR = 0D;
		Double lMOComm = 0D;
		//String lStrTreasuryName = null;
		//CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
		Double lPensionArr = 0D;
        Double lFP1Arr = 0D;
        Double lFP2Arr = 0D;
        Double lTIArr = 0D;
        Double lMAArr = 0D;
        Double lOtherArr = 0D;
        Double lReducedPen = 0D;
        Integer commMonth = 0;
		
		PensionBillProcessServiceImpl lObjPensionBill = new PensionBillProcessServiceImpl();
		NewPensionBillDAOImpl lObjNewPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
		List<TrnPnsncaseRopRlt> lPnsnerROPLst = null;
		TrnPnsncaseRopRlt lPnsncaseRopRltVO = null;
		
		String lStrIsROPApplied = null;
		String lStrROP_1986 = null;
		String lStrROP86_AdptedType = null;
		String lStrROP_1996 = null;

		String lStrROP_1986_PAY = null;
		String lStrROP_1996_PAY = null;
		
		List<MonthlyPensionBillVO> lLstPendingPensionBillVO = null;
		List<TrnPensionArrearDtls> lLstPendingArrearVO = null;
		
		List lLstROPDtls = null;
		String lStrPayROP_1986 = null;
		String lStrPayROP_1996 = null;
			
        try
        {        	
        	setSessionInfo(inputMap);
        	lArrComputeFlag = inputMap.get("ArrComputeFlag").toString();
        	        	 
        	lStrPnsnrCode = PensionerCode;
        	lStrforMonth = inputMap.get("ForMonth").toString();
        	lStrforYear = inputMap.get("ForYear").toString();
        	lStrBank = inputMap.get("Bank").toString();
        	lStrBranch = inputMap.get("Branch").toString();
        	lStrHeadCode = inputMap.get("HeadCode").toString();
        	lStrDate = inputMap.get("Date").toString();
        	lStrScheme = inputMap.get("Scheme").toString();
        	
        	inputMap.put("BillType","Monthly");
        	inputMap.put("forMonth",lStrDate);
        	
        	if(lStrScheme != null && "PSB".equals(lStrScheme))
        	{
        		lStrCaseStatus = "NEW";
        	}
        	        	
        	MonthlyPensionBillDAO lObjMnthlyBillDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
        	MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
        	
        	if(lStrPnsnrCode != null && lStrPnsnrCode.length() > 0 ) 
        	{
        		lObjTrnPensionRqstHdrVO = lObjMnthlyBillDAO.getTrnPensionRqstHdrDtls(lStrPnsnrCode, lStrStatus, lStrHeadCode, lStrCaseStatus);
        		if(lObjTrnPensionRqstHdrVO != null)
            	{
        			if(lStrScheme.equals(lObjTrnPensionRqstHdrVO.getSchemeType()))
        			{
        				lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsnrCode);
        				inputMap.put("MstPensionerHdrVO", lObjMstPensionerHdrVO);
        				
        				lStrIsROPApplied = lObjTrnPensionRqstHdrVO.getIsRop();
        				// Checking ROP's Applied to Pensioner.		Start.        		
    		        	inputMap.put("IsROPApplied", lStrIsROPApplied);
    		        	
    		        	if(lStrIsROPApplied != null && lStrIsROPApplied.equalsIgnoreCase("Y"))
    		        	{
    			        	lPnsnerROPLst = lObjNewPensionBillDAO.getROPAppliedToPensner(lObjTrnPensionRqstHdrVO.getPpoNo());  // Fetch ROP(s) Details.
    			    		if(lPnsnerROPLst.size() > 0)
    			    		{
    			    			for(int i=0;i<lPnsnerROPLst.size();i++)
    			    			{
    			    				lPnsncaseRopRltVO = null;
    			    				lPnsncaseRopRltVO = lPnsnerROPLst.get(i);
    			    				
    			    				if(lPnsncaseRopRltVO.getRop().equalsIgnoreCase("1986"))
    			    				{
    			    					lStrROP_1986 = "Y";
    			    					lStrROP86_AdptedType = lPnsncaseRopRltVO.getAdaptedType();
    			    					lStrROP_1986_PAY = lPnsncaseRopRltVO.getRopPaid();
    			    				}
    			    				else if(lPnsncaseRopRltVO.getRop().equalsIgnoreCase("1996"))
    			    				{
    			    					lStrROP_1996 = "Y";
    			    					lStrROP_1996_PAY = lPnsncaseRopRltVO.getRopPaid();
    			    				}
    			    				/*else if(lPnsncaseRopRltVO.getRop().equalsIgnoreCase("2006"))
    			    				{
    			    					lStrROP_2006 = lPnsncaseRopRltVO.getRop();
    			    				}*/
    			    			}
    			    			inputMap.put("ROP_1986", lStrROP_1986);
    			    			inputMap.put("ROP86_AdptedType", lStrROP86_AdptedType);
    			    			inputMap.put("ROP_1996", lStrROP_1996);
    			    			//inputMap.put("ROP_2006", lStrROP_2006);
    			    			
    			    			//inputMap.put("PayROP1986",lStrROP_1986_PAY);
    			    			//inputMap.put("PayROP1996",lStrROP_1996_PAY);

    			    			inputMap.put("OldBasicAmt", Double.valueOf(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString()));
    			    			
    			    			if(((lStrROP_1986 != null && lStrROP_1986.equalsIgnoreCase("Y")) && (lStrROP_1986_PAY == null || !lStrROP_1986_PAY.equalsIgnoreCase("Y"))) ||
		    					((lStrROP_1996 != null && lStrROP_1996.equalsIgnoreCase("Y")) && (lStrROP_1996_PAY == null || !lStrROP_1996_PAY.equalsIgnoreCase("Y"))))
		    					{
    			    				// For Pension Basic Amt ROP Amt. 
	    			    			Map lRopResMap = new HashMap();
	    			    			inputMap.put("OldBasicAmt", Double.valueOf(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString()));
	    			    			lRopResMap = lObjPensionBill.getNewROPBasicAmt(inputMap);
	    			    			
	    			    			inputMap.put("Basic1986", lRopResMap.get("NewBasic1986"));
	    			    			inputMap.put("Basic1996", lRopResMap.get("NewBasic1996"));
	    			    			
	    			    			//inputMap.put("PayROP1986", lRopResMap.get("PayROP1986"));
	    			    			//inputMap.put("PayROP1996", lRopResMap.get("PayROP1996"));
	    			    			
	    			    			lStrPayROP_1986 = (String)lRopResMap.get("PayROP1986");
	    			    			lStrPayROP_1996 = (String)lRopResMap.get("PayROP1996");
	    			    			
	    			    			//	 For Pension FP1 Amt ROP Amt.		    			
	    			    			if(lObjTrnPensionRqstHdrVO.getFp1Amount() != null)
	    			    			{
	    			    				inputMap.put("OldBasicAmt", Double.valueOf(lObjTrnPensionRqstHdrVO.getFp1Amount().toString()));
	    				    			lRopResMap = lObjPensionBill.getNewROPBasicAmt(inputMap);
	    				    			
	    				    			inputMap.put("FP11986", lRopResMap.get("NewBasic1986"));
	    				    			inputMap.put("FP11996", lRopResMap.get("NewBasic1996"));
	    			    			}
	    			    			
	    			    			//	 For Pension FP2 Amt ROP Amt. 
	    			    			if(lObjTrnPensionRqstHdrVO.getFp2Amount() != null)
	    			    			{
	    				    			inputMap.put("OldBasicAmt", Double.valueOf(lObjTrnPensionRqstHdrVO.getFp2Amount().toString()));
	    				    			lRopResMap = lObjPensionBill.getNewROPBasicAmt(inputMap);
	    				    			
	    				    			inputMap.put("FP21986", lRopResMap.get("NewBasic1986"));
	    				    			inputMap.put("FP21996", lRopResMap.get("NewBasic1996"));
	    			    			}			    			
	    			    			
	    			    			//  set ROP Pay Flage
	    			    			inputMap.put("PayROP1986", lStrPayROP_1986);
	    			    			inputMap.put("PayROP1996", lStrPayROP_1996);
	    			    			
	    			    			if("Y".equals(inputMap.get("PayROP1996").toString()))
	    			    			{
	    			    				lLstROPDtls = new ArrayList();
	    			    				
	    			    				lLstROPDtls.add(lObjTrnPensionRqstHdrVO.getPensionRequestId());
	    			    				lLstROPDtls.add(lObjTrnPensionRqstHdrVO.getPpoNo());
	    			    				lLstROPDtls.add(inputMap.get("PayROP1986").toString());
	    			    				lLstROPDtls.add(inputMap.get("PayROP1996").toString());
	    			    				lLstROPDtls.add(inputMap.get("Basic1996"));
	    			    				lLstROPDtls.add(inputMap.get("FP11996"));
	    			    				lLstROPDtls.add(inputMap.get("FP21996"));
	    			    				
	    			    			}
	    			    			else if("Y".equals(inputMap.get("PayROP1986").toString()))
	    			    			{
	    			    				lLstROPDtls = new ArrayList();
	    			    				
	    			    				lLstROPDtls.add(lObjTrnPensionRqstHdrVO.getPensionRequestId());
	    			    				lLstROPDtls.add(lObjTrnPensionRqstHdrVO.getPpoNo());
	    			    				lLstROPDtls.add(inputMap.get("PayROP1986").toString());
	    			    				lLstROPDtls.add(inputMap.get("PayROP1996").toString());
	    			    				lLstROPDtls.add(inputMap.get("Basic1986"));
	    			    				lLstROPDtls.add(inputMap.get("FP11986"));
	    			    				lLstROPDtls.add(inputMap.get("FP21986"));
	    			    			}
	    			    			
	    			    			inputMap.put("lLstROPDtls", lLstROPDtls);
    			    			}
    			    			else
    			    			{
    			    				inputMap.put("PayROP1986", "N");
	    			    			inputMap.put("PayROP1996", "N");
    			    			}
    			    			
    			    		}
    		        	}
    		        	//	Checking ROP's Applied to Pensioner.		End.
    					
    					
    					
        				//pensioner is a valid pensioner
        				
        				lEndDate = lObjTrnPensionRqstHdrVO.getEndDate();
        				if(lEndDate != null){
    	        			endDate = new SimpleDateFormat("yyyyMM").format(lEndDate);
        	        		endDays = new SimpleDateFormat("dd").format(lEndDate);
    	    				EDays = Integer.valueOf(endDays);
    	        		}
        				else{
        					endDate = "000000";
        				}
        				EDate = Integer.valueOf(endDate);
        				//check the last month for which monthly pension or pension paid....
        				//if 0 implies no bill generated for this pensioner...not even pension bill....
        				//hence should not compute monthly bill for tht pensioner
        	    		lLastPaidMonth = lObjMnthlyBillDAO.getLastMonth(lStrPnsnrCode, lStrScheme);
        	    		lIntDate = Integer.parseInt(lStrDate);
        	    		
        	    		//monthly pension to be computed for pensioner only for the following condn 
        	    		if(lLastPaidMonth != 0 && lLastPaidMonth < lIntDate)
	        	    	{
        	    			//find previous month
	        	    		
	        	    		if((lIntDate%100) == 01){
	        	    			lPrevMonth = lIntDate - 89;
	        	    		}
	        	    		else{
	        	    			lPrevMonth = lIntDate - 1;
	        	    		}
	        				
	        				if(EDate < lIntDate && EDate != 0)
	        				{
	        					//end date was before this month
	        					//therefore do not calculate for this month 
	        					//but check if some previous bills were not paid
	        					
	        					if(!"M".equals(lObjTrnPensionRqstHdrVO.getCalcType()))
	        					{
		        					if(lLastPaidMonth != lPrevMonth && lLastPaidMonth < lPrevMonth && lLastPaidMonth != 0)
		            	    		{
		            	    			MonthlyPensionBillVO lObjPending = new MonthlyPensionBillVO();
		            	    			
		            	    			//monthly pension pending...so compute that
		            	    			//if not paid for any month....call recursive function and get net pension in arrears for this month
		            	    			//inputMap.put("ForMonth", lPrevMonth % 100);
		            	    			//inputMap.put("ForYear", lPrevMonth / 100);
		            	    			inputMap.put("Date", lPrevMonth);
		            	    			inputMap.put("FPFlag", "N");
		                    			inputMap.put("ArrComputeFlag", "Y");
		            	    			Map lMapTemp = computeMonthlyPension(inputMap, lStrPnsnrCode);
		            	    			lLstPendingPensionBillVO = (List<MonthlyPensionBillVO>) lMapTemp.get("lLstMonthlyPensionBillVO");
		            	    	        lLstPendingArrearVO = (List<TrnPensionArrearDtls>) lMapTemp.get("lLstInsertArrears");
		            	    			
		            	    	        if(lLstPendingArrearVO != null && lLstPendingArrearVO.size() > 0)
		            	    	        {
		            	    	        	for(int m = 0; m < lLstPendingArrearVO.size(); m++){
		            	    	        		lLstInsertArrears.add(lLstPendingArrearVO.get(m));
		            	    	        	}
		            	    			}
		            	    	        
		            	    	        if(lLstPendingPensionBillVO != null && lLstPendingPensionBillVO.size() > 0)
		            	    	        {
		            	    	        	for(int m = 0; m < lLstPendingPensionBillVO.size(); m++){
		            	    	        		lObjPending = lLstPendingPensionBillVO.get(0);
		                    	    			lPendingArrear = lPendingArrear + Double.parseDouble(lObjPending.getNetPensionAmount().toString());
		            	    	        	}
		            	    			}
		            	    			if(lPendingArrear != 0D && lPendingArrear > 0D)
		            	    			{
		            	    				//get details like ppo no, pnsnr name and acc no
		            	    				//arrear amt = pending amt
		            	    				//other amts = 0
		            	    				//net pension = arrear
		            	    				lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsnrCode);
		        	    	        		lLstAccNo = lObjMnthlyBillDAO.getAccountNo(lStrPnsnrCode, lStrBank, lStrBranch);
		        	    	        		if(lLstAccNo.get(0) != null)
		        	    	        		{
		        	    	        			lStrAccNo = lLstAccNo.get(0).toString();
		        	    	        		}
		        	    	        		else
		        	    	        		{
		        	    	        			lStrAccNo = "";
		        	    	        		}
		        	    	        		if(lObjMstPensionerHdrVO != null)
		        	        	        	{
		        	        	        		// Getting the Value from MstPensionerHdr... Start...
		        	        					if(lObjMstPensionerHdrVO.getMiddleName() != null){
		        	        						lStrPnsnerName = lObjMstPensionerHdrVO.getFirstName() + ' ' + 
		        	        		 				  				 lObjMstPensionerHdrVO.getMiddleName() + ' ' +
		        	        		 				  				 lObjMstPensionerHdrVO.getLastName();
		        	        					}
		        	        					else{
		        	        						lStrPnsnerName = lObjMstPensionerHdrVO.getFirstName() + ' ' + 
		        	        										 lObjMstPensionerHdrVO.getLastName();
		        	        					}
		        	        		        	lDateOfDeath = lObjMstPensionerHdrVO.getDateOfDeath();
		        	        	        	}
		            	    				
		            	           	    	        		
		        	    	        		if(lStrScheme.equals(lStrMoneyOrder)){
		        	    	    				lMOComm = lNetPensionAmt * 0.05;
		        	    	    			}
		        	    	        		
		        	    	        		if(lDateOfDeath != null)
		        	    	        		{
		        	    	        			//indicates that the pensioner has expired and family details to be found
		        	    	    					//compute details for family pensioner's...
		        	    	    					//could result into multiple family pensioners
		        	    	    					lLstFPmembers = lObjMnthlyBillDAO.getMstPensionerFamilyDtls(lStrPnsnrCode);
		        	    	    					lStrFPFlag = "Y";
		        	    	    				
		        	    	    					fpMemberList = new ArrayList();
		        	    	    					fpMemberList = getFpMemberList(lLstFPmembers);
		        	    	        		}
		        	    	        		
		        	    	        		if("Y".equals(lStrFPFlag) && fpMemberList.size() > 0)
		        	    	    	    	{
		        	    	    	    		for(int y=0; y < (fpMemberList.size()); y+=3)
		        	    	    	    		{
		        	    	    	    			lStrPnsnerName = fpMemberList.get(y+0).toString();
		        	    	    	    			Double prcnt = Double.parseDouble(fpMemberList.get(y+1).toString())/100;
		        	    	    	    			if(fpMemberList.get(y+2) != null){
		        	    	    	    				lStrAccNo = fpMemberList.get(y+2).toString();
		        	    	    	    			}
		        	    	    	    			else{
		        	    	    	    				lStrAccNo = "";
		        	    	    	    			}
	
		        	    	    	    			lObjMonthlyPensionVO = new MonthlyPensionBillVO();
				    	            	        	
				    	            	        	inputMap.put("MnthPpoNo", lObjTrnPensionRqstHdrVO.getPpoNo());
				    	            	        	inputMap.put("MnthPensionerName", lStrPnsnerName);
				    	            	        	inputMap.put("MnthAccountNo", lStrAccNo);
				    	            	        	inputMap.put("MnthBasicPensionAmount", 0);
				    	            	        	inputMap.put("MnthDpPercent", 0);
				    	            	        	inputMap.put("MnthTiPercent", 0);
				    	            	        	inputMap.put("MnthDpPercentAmount", 0);
				    	            	        	inputMap.put("MnthTiPercentAmount", 0);
				    	            	        	inputMap.put("MnthMedicalAllowenceAmount", 0);
				    	            	        	inputMap.put("MnthCvpMonthlyAmount", 0);
				    	            	        	inputMap.put("MnthItCutAmount", 0);
				    	            	        	inputMap.put("MnthSpecialCutAmount", 0);
				    	            	        	inputMap.put("MnthPensionCutAmount", 0);
				    	            	        	inputMap.put("MnthRecoveryAmount", 0);
				    	            	        	long lLngAmount = Math.round(lPendingArrear*prcnt);
				    	            	        	inputMap.put("MnthNetPensionAmount", lLngAmount);
				    	            	        	inputMap.put("MnthAllnBf56", 0);
				    	            	        	inputMap.put("MnthAllnAf56", 0);
				    	            	        	inputMap.put("MnthAllnAf60", 0);
				    	            	        	inputMap.put("MnthPersonalPension", 0);
		        	    	    		        	inputMap.put("MnthIRAmount", 0);
		        	    	    		        	inputMap.put("MnthMOComm", lMOComm*prcnt);
		        	    	    		        	inputMap.put("MnthTIArrearsAmount", 0);
		        	    	    		        	inputMap.put("MnthOtherArrearsAmount", lPendingArrear*prcnt);
		        	    	    		        	inputMap.put("MnthOtherBenefit", 0);
		        	    	    		        	inputMap.put("MnthOMR", 0);
				    	            	        	
				    	            	        	lObjMonthlyPensionVO = insertMonthlyDtls(inputMap);
				    	            	        	lLstMonthlyPensionBillVO.add(lObjMonthlyPensionVO);
		        	    	    	    		}
		        	    	    	    	}
		        	    	    	    	else
		        	    	    	    	{
		        	    	    	        	lObjMonthlyPensionVO = new MonthlyPensionBillVO();
			    	            	        	
			    	            	        	inputMap.put("MnthPpoNo", lObjTrnPensionRqstHdrVO.getPpoNo());
			    	            	        	inputMap.put("MnthPensionerName", lStrPnsnerName);
			    	            	        	inputMap.put("MnthAccountNo", lStrAccNo);
			    	            	        	inputMap.put("MnthBasicPensionAmount", 0);
			    	            	        	inputMap.put("MnthDpPercent", 0);
			    	            	        	inputMap.put("MnthTiPercent", 0);
			    	            	        	inputMap.put("MnthDpPercentAmount", 0);
			    	            	        	inputMap.put("MnthTiPercentAmount", 0);
			    	            	        	inputMap.put("MnthMedicalAllowenceAmount", 0);
			    	            	        	inputMap.put("MnthCvpMonthlyAmount", 0);
			    	            	        	inputMap.put("MnthItCutAmount", 0);
			    	            	        	inputMap.put("MnthSpecialCutAmount", 0);
			    	            	        	inputMap.put("MnthPensionCutAmount", 0);
			    	            	        	inputMap.put("MnthRecoveryAmount", 0);
			    	            	        	long lLngAmount = Math.round(lPendingArrear);
			    	            	        	inputMap.put("MnthNetPensionAmount", lLngAmount);
			    	            	        	inputMap.put("MnthAllnBf56", 0);
			    	            	        	inputMap.put("MnthAllnAf56", 0);
			    	            	        	inputMap.put("MnthAllnAf60", 0);
			    	            	        	inputMap.put("MnthPersonalPension", 0);
	        	    	    		        	inputMap.put("MnthIRAmount", 0);
	        	    	    		        	inputMap.put("MnthMOComm", lMOComm);
	        	    	    		        	inputMap.put("MnthTIArrearsAmount", 0);
	        	    	    		        	inputMap.put("MnthOtherArrearsAmount", lPendingArrear);
	        	    	    		        	inputMap.put("MnthOtherBenefit", 0);
	        	    	    		        	inputMap.put("MnthOMR", 0);
			    	            	        	
			    	            	        	lObjMonthlyPensionVO = insertMonthlyDtls(inputMap);
			    	            	        	lLstMonthlyPensionBillVO.add(lObjMonthlyPensionVO);
		        	    	    	    	}
		            	    			}
		            	       		}
		            	    		inputMap.put("FPFlag", "Y");
		            	    		inputMap.put("Date", lStrDate);
	        					}
	        				}
	        				else
	        				{
	        					
	        					//////////////////////////////////////////////////
	        					if(!"M".equals(lObjTrnPensionRqstHdrVO.getCalcType()))
	        					{
	        						if((lIntDate%100) == 12){
	        							commMonth = lLastPaidMonth + 89;
	    	        	    		}
	    	        	    		else{
	    	        	    			commMonth = lLastPaidMonth + 1;
	    	        	    		}
	        						
	        						for(Integer compMonth = commMonth; compMonth < lIntDate; )
	        						{
	        							if(lLastPaidMonth != compMonth && lLastPaidMonth < compMonth && lLastPaidMonth != 0)
		        						{
		        							MonthlyPensionBillVO lObjPending = new MonthlyPensionBillVO();
		        							
		        							//monthly pension pending...so compute that
		        							//if not paid for any month....call recursive function and get net pension in arrears for this month
		        							//inputMap.put("ForMonth", compMonth % 100);
		        							//inputMap.put("ForYear", compMonth / 100);
		        							inputMap.put("Date", compMonth);
		        							inputMap.put("FPFlag", "N");
		        							inputMap.put("ArrComputeFlag", "Y");
		        							inputMap.put("TrnPensionRqstHdrVO", lObjTrnPensionRqstHdrVO);
		        							inputMap.put("lPendingArrear", 0);
		    	        					//inputMap.put("Date", lStrDate);
		    	        					inputMap = lObjPensionBill.getCurrMonthData(inputMap);
		        							//Map lMapTemp = computeMonthlyPension(inputMap, lStrPnsnrCode);
		        							lLstPendingPensionBillVO = (List<MonthlyPensionBillVO>) inputMap.get("lLstMonthlyPensionBillVO");
		        							lLstPendingArrearVO = (List<TrnPensionArrearDtls>) inputMap.get("lLstInsertArrears");
		        							
		        					        if(lLstPendingArrearVO != null && lLstPendingArrearVO.size() > 0)
		        					        {
		        					        	for(int m = 0; m < lLstPendingArrearVO.size(); m++)
		        					        	{
		        					        		lLstInsertArrears.add(lLstPendingArrearVO.get(m));
		        					        	}
		        							}
		        					        
		        					        if(lLstPendingPensionBillVO != null && lLstPendingPensionBillVO.size() > 0)
		        					        {
		        					        	for(int m = 0; m < lLstPendingPensionBillVO.size(); m++)
		        					        	{
		        					        		lObjPending = lLstPendingPensionBillVO.get(0);
		        					    			lPendingArrear = lPendingArrear + Double.parseDouble(lObjPending.getNetPensionAmount().toString());
		        					        	}
		        							}
		        				   		}
		        						inputMap.put("FPFlag", "Y");
		        						inputMap.put("Date", lStrDate);
		        						
		        						compMonth = (compMonth%100 == 12)?(compMonth + 89):(compMonth + 1);
	        						}
	        				 	
	        					
		        					Long pending = Math.round(lPendingArrear);
		        					lPendingArrear = Double.parseDouble(pending.toString());
		        					//lArrearAmt = lArrearAmt + lPendingArrear;
		        					//////////////////////////////////////////////////
		        					//monthly pension has to be computed for pensioner for this month....
		        					inputMap.put("FPFlag", "Y");
		                			inputMap.put("ArrComputeFlag", "N");
		        					inputMap.put("TrnPensionRqstHdrVO", lObjTrnPensionRqstHdrVO);
		        					inputMap.put("lPendingArrear", lPendingArrear);
		        					inputMap.put("Date", lStrDate);
		        					inputMap = lObjPensionBill.getCurrMonthData(inputMap);
		        					
		        					lLstMonthlyPensionBillVO = (List<MonthlyPensionBillVO>) inputMap.get("lLstMonthlyPensionBillVO");
		        					
		        					lLstPendingArrearVO = (List<TrnPensionArrearDtls>) inputMap.get("lLstInsertArrears");
		        					if(lLstPendingArrearVO != null && lLstPendingArrearVO.size() > 0)
	    					        {
	    					        	for(int m = 0; m < lLstPendingArrearVO.size(); m++)
	    					        	{
	    					        		lLstInsertArrears.add(lLstPendingArrearVO.get(m));
	    					        	}
	    							}
	        					}
	        					else if("M".equals(lObjTrnPensionRqstHdrVO.getCalcType()))
	        					{
	        						lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsnrCode);
	        						
        							lLstAccNo = lObjMnthlyBillDAO.getAccountNo(lStrPnsnrCode, lStrBank, lStrBranch);
        							if(lLstAccNo.size() > 0  && lLstAccNo.get(0) != null){
        								lStrAccNo = lLstAccNo.get(0).toString();
        							}
        							else{
        								lStrAccNo = "";
        							}
	        						
	        						lRecoveryAmt =  lObjMnthlyBillDAO.getRecoveryDtls(lStrPnsnrCode, lStrRcvryFlag, lStrDate);
	        						lItCutDtls = lObjMnthlyBillDAO.getItCutDtls(lStrPnsnrCode, lStrDate);
	        						lArrearDtls = lObjMnthlyBillDAO.getArrearDtls(lStrPnsnrCode, lStrDate);
	        					
	        						// Getting the Value from TrnPensionRqstHdr... Start...
	        						lStrPenRqstId = lObjTrnPensionRqstHdrVO.getPensionRequestId();
	        						if(lObjTrnPensionRqstHdrVO.getPpoNo()!= null){
	        							lStrPPONo = lObjTrnPensionRqstHdrVO.getPpoNo();
	        						}
	        						if(lObjTrnPensionRqstHdrVO.getBasicPensionAmount() != null){
	        							lBasicPensionAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
	        						}
	        						if(lObjTrnPensionRqstHdrVO.getDpPercent() != null){
	        							lDPPer = Long.parseLong(lObjTrnPensionRqstHdrVO.getDpPercent().toString());
	        						}
	        						if(lObjTrnPensionRqstHdrVO.getTiPercent() != null){
	        							lTIPer = Long.parseLong(lObjTrnPensionRqstHdrVO.getTiPercent().toString());
	        						}
	        						if(lObjTrnPensionRqstHdrVO.getMedicalAllowenceAmount() != null){
	        							lMAAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getMedicalAllowenceAmount().toString());
	        						}
	        						/*if(lObjTrnPensionRqstHdrVO.getFp1Amount() != null){
	        							lFP1Amt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getFp1Amount().toString());
	        						}
	        						if(lObjTrnPensionRqstHdrVO.getFp2Amount() != null){
	        							lFP2Amt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getFp2Amount().toString());
	        						}*/
	        						if(lObjTrnPensionRqstHdrVO.getRedBf11156() != null){
	        							allnBf56 = Double.parseDouble(lObjTrnPensionRqstHdrVO.getOrgBf11156().toString());
	        						}
	        						if(lObjTrnPensionRqstHdrVO.getRedAf11156() != null){
	        							allnAf56 = Double.parseDouble(lObjTrnPensionRqstHdrVO.getOrgAf11156().toString());
	        						}
	        						/*if(lObjTrnPensionRqstHdrVO.getRedAf10560() != null){
	        							allnAf60 = lObjTrnPensionRqstHdrVO.getRedAf10560();
	        						}*/
	        						if(lObjTrnPensionRqstHdrVO.getPersonalPension() != null){
	        							lPerPension = Double.parseDouble(lObjTrnPensionRqstHdrVO.getPersonalPension().toString());
	        						}
	        						if(lObjTrnPensionRqstHdrVO.getIr() != null){
	        							lIR = Double.parseDouble(lObjTrnPensionRqstHdrVO.getIr().toString());
	        						}
	        						if(lObjTrnPensionRqstHdrVO.getCvpMonthlyAmount() != null)
	        						{
	        							lCVPMonthlyAmt = Double.valueOf(lObjTrnPensionRqstHdrVO.getCvpMonthlyAmount().toString());
	        						}
	        						
	        						if(lObjMstPensionerHdrVO != null)
	        				    	{
	        							inputMap.put("MstPensionerHdrVO", lObjMstPensionerHdrVO);
	        				    		// Getting the Value from MstPensionerHdr... Start...
	        							if(lObjMstPensionerHdrVO.getMiddleName() != null){
	        								lStrPnsnerName = lObjMstPensionerHdrVO.getFirstName() + ' ' + 
	        				 				  				 lObjMstPensionerHdrVO.getMiddleName() + ' ' +
	        				 				  				 lObjMstPensionerHdrVO.getLastName();
	        							}
	        							else{
	        								lStrPnsnerName = lObjMstPensionerHdrVO.getFirstName() + ' ' + 
	        												 lObjMstPensionerHdrVO.getLastName();
	        							}
	        							
	        							/* // Check for Basic Pension / FP1 Pension / FP2 Pension... Start....
	        							lDateOfDeath = lObjMstPensionerHdrVO.getDateOfDeath();
	        				        	
	        							if(lDateOfDeath != null && lDateOfDeath.toString().length() > 0)
	        							{
	        								//	compute lDateOfDeathYYYYMM
	        								lDateOfDeathYYYYMM = new SimpleDateFormat("yyyyMM").format(lDateOfDeath);
	        								lDayOfDeathDD = new SimpleDateFormat("dd").format(lDateOfDeath);
	        								lDODyyyyMM = Integer.parseInt(lDateOfDeathYYYYMM);
	        							}*/
	        				    	}
	        						
	        						if(lItCutDtls != null && lItCutDtls.size() > 0)
	        						{
	        							lITCutAmt = 0D;
	        							lPensionCutAmt = 0D;
	        							
	        							for(int i=0;i<lItCutDtls.size();i+=2)
	        							{
	        								if(lStrCutIT.equals(lItCutDtls.get(i))){
	        									lITCutAmt = lITCutAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
	        								}
	        								if(lStrCutPT.equals(lItCutDtls.get(i))){
	        									lPensionCutAmt = lPensionCutAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
	        								}
	        								if(lStrCutPP.equals(lItCutDtls.get(i))){
	        									lPensionCutAmt = lPensionCutAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
	        								}
	        								if(lStrCutST.equals(lItCutDtls.get(i))){
	        									lSpecialCutAmt = lSpecialCutAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
	        								}
	        								/*if(lStrCutCVP.equals(lItCutDtls.get(i))){
	        									lCVPMonthlyAmt = lCVPMonthlyAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
	        								}*/
	        								if(lStrCutPerBen.equals(lItCutDtls.get(i))){
	        									lOtherBenefits = lOtherBenefits + Double.parseDouble(lItCutDtls.get(i+1).toString());
	        								}
	        								if(lStrCutTmpBen.equals(lItCutDtls.get(i))){
	        									lOtherBenefits = lOtherBenefits + Double.parseDouble(lItCutDtls.get(i+1).toString());
	        								}
	        							}
	        						}
	        						
	        						if(lArrearDtls != null && lArrearDtls.size() > 0)
	        						{
	        							for(int i=0;i<lArrearDtls.size();i+=2)
	        							{
	        								if(lStrArrTI.equals(lArrearDtls.get(i)))
	        								{
	        									lTIArr = lTIArr + Double.parseDouble(lArrearDtls.get(i+1).toString());
	        								}
	        								if(lStrArrMA.equals(lArrearDtls.get(i)))
	        								{
	        									lMAArr = lMAArr + Double.parseDouble(lArrearDtls.get(i+1).toString());
	        								}
	        								if(lStrArrPension.equals(lArrearDtls.get(i)))
	        								{
	        									lPensionArr = lPensionArr + Double.parseDouble(lArrearDtls.get(i+1).toString());
	        								}
	        								if(lStrArrFP1.equals(lArrearDtls.get(i)))
	        								{
	        									lFP1Arr = lFP1Arr + Double.parseDouble(lArrearDtls.get(i+1).toString());
	        								}
	        								if(lStrArrFP2.equals(lArrearDtls.get(i)))
	        								{
	        									lFP2Arr = lFP2Arr + Double.parseDouble(lArrearDtls.get(i+1).toString());
	        								}
	        							}
	        							//lCutATI = lTIArr;
	        							lOtherArr = lMAArr + lPensionArr + lFP1Arr + lFP2Arr;
	        							lArrearAmt = lTIArr + lOtherArr;
	        						}
	        						
	        							        			        	
//	        						 Net Pension Amount Calculation.
	        				    	lNetPensionAmt = lBasicPensionAmt - lPensionCutAmt;		//	 NetPension = Basic - PensionCut;
	        				    	lDPPerAmt = ((lNetPensionAmt * lDPPer) / 100);
	        				    	lNetPensionAmt = lNetPensionAmt + lDPPerAmt;			// 	 NetPension = NetPension + DPAmt;
	        				    	lTIPerAmt = ((lNetPensionAmt * lTIPer) / 100);
	        				    	lNetPensionAmt = lNetPensionAmt + lTIPerAmt;			// 	 NetPension = NetPension + TIAmt;
	        				    	lNetPensionAmt = lNetPensionAmt + lMAAmt;				// 	 NetPension = NetPension + Medical Allowance;
	        				    	lNetPensionAmt = lNetPensionAmt - lCVPMonthlyAmt;		// 	 NetPension = NetPension - CVPMonthlyAmt ;
	        				    	lNetPensionAmt = lNetPensionAmt - lITCutAmt;			// 	 NetPension = NetPension - IT Cut;
	        				    	lNetPensionAmt = lNetPensionAmt - lSpecialCutAmt;		//   NetPension = NetPension - Special Cut;
	        				    	lNetPensionAmt = lNetPensionAmt + lOtherBenefits;		//   NetPension = NetPension + OtherBenefits;
	        				    	lNetPensionAmt = lNetPensionAmt + OMR;					//   NetPension = NetPension + OMR;
	        				    	lNetPensionAmt = lNetPensionAmt - lRecoveryAmt;			// 	 NetPension = NetPension - Recovery / Deduction;
	        				    	lNetPensionAmt = lNetPensionAmt + lArrearAmt;			//   NetPension = NetPension + ArrearAmt ;
	        				    	lNetPensionAmt = lNetPensionAmt + lPerPension;			//   NetPension = NetPension + PersonalPension ;
	        				    	lNetPensionAmt = lNetPensionAmt + lIR;					//   NetPension = NetPension + IRAmt ;
	        						
	        				    	if(lStrScheme.equals(lStrMoneyOrder)){
	        								lMOComm = lNetPensionAmt * 0.05;
	        						}
        							lNetPensionAmt = lNetPensionAmt - lMOComm;
        							//gLogger.info("lNetPensionAmt for the month of"+lStrDate+"is:"+lNetPensionAmt);
        						
	        						lReducedPen = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt - lCVPMonthlyAmt;
	        						allnAf60 = lReducedPen - allnBf56 - allnAf56;
	        						
	        						lObjMonthlyPensionVO = new MonthlyPensionBillVO();
	        			        	BigDecimal lTmpOthArr = new BigDecimal(lArrearAmt - lTIArr);
	        			        	//gLogger.info("lTmpOthArr"+lTmpOthArr);
	        			    		
	        			        	inputMap.put("MnthPpoNo", lStrPPONo);
	        			        	inputMap.put("MnthPensionerName", lStrPnsnerName);
	        			        	inputMap.put("MnthAccountNo", lStrAccNo);
	        			        	inputMap.put("MnthBasicPensionAmount", lBasicPensionAmt);
	        			        	inputMap.put("MnthDpPercent", lDPPer.toString().length() != 0 ? lDPPer.toString() : "0");
	        			        	inputMap.put("MnthTiPercent", lTIPer.toString().length() != 0 ? lTIPer.toString() : "0");
	        			        	inputMap.put("MnthDpPercentAmount", lDPPerAmt);
	        			        	inputMap.put("MnthTiPercentAmount", lTIPerAmt);
	        			        	inputMap.put("MnthMedicalAllowenceAmount", lMAAmt);
	        			        	inputMap.put("MnthCvpMonthlyAmount", lCVPMonthlyAmt);
	        			        	inputMap.put("MnthTIArrearsAmount", lTIArr);
	        			        	inputMap.put("MnthOtherArrearsAmount", lTmpOthArr);
	        			        	inputMap.put("MnthItCutAmount", lITCutAmt);
	        			        	inputMap.put("MnthSpecialCutAmount", lSpecialCutAmt);
	        			        	inputMap.put("MnthOtherBenefit", lOtherBenefits);
	        			        	inputMap.put("MnthOMR", OMR);
	        			        	inputMap.put("MnthPensionCutAmount", lPensionCutAmt);
	        			        	inputMap.put("MnthRecoveryAmount", lRecoveryAmt);
	        			        	long lLngAmount = Math.round(lNetPensionAmt);
	        			        	inputMap.put("MnthNetPensionAmount", lLngAmount);
	        			        	inputMap.put("MnthAllnBf56", allnBf56);
	        			        	inputMap.put("MnthAllnAf56", allnAf56);
	        			        	inputMap.put("MnthAllnAf60", allnAf60);
	        			        	inputMap.put("MnthPersonalPension", lPerPension);
	        			        	inputMap.put("MnthIRAmount", lIR);
	        			        	inputMap.put("MnthMOComm", lMOComm);
	        			        	inputMap.put("MnthYYYYMM", lIntDate);
	        			        	
	        			        	lObjMonthlyPensionVO = insertMonthlyDtls(inputMap);
	        			        	lLstMonthlyPensionBillVO.add(lObjMonthlyPensionVO);
	        						
	        						//////////////////////////////////////////////////////
	        					}
	        					
	        					
	        					
	        					
	        				}
	        			}
        			}
        		}
        	}
        	lArrComputeFlag = "N";
        	inputMap.put("ArrComputeFlag", lArrComputeFlag);
        	inputMap.put("lLstMonthlyPensionBillVO", lLstMonthlyPensionBillVO);
        	inputMap.put("lLstInsertArrears", lLstInsertArrears);
        }
        catch(Exception e){
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			throw(e);
		}
        return inputMap;
	}

/*	
	public Map getCurrMonthData(Map inputMap) throws Exception
	{
		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
               
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
        MstPensionerHdr lObjMstPensionerHdrVO = null;
        List<MonthlyPensionBillVO> lLstMonthlyPensionBillVO = new ArrayList<MonthlyPensionBillVO>();
        MonthlyPensionBillVO lObjMonthlyPensionVO = null;
        List<TrnPensionArrearDtls> lLstInsertArrears = new ArrayList<TrnPensionArrearDtls>();
        TrnPensionArrearDtls lObjArrearVO = null;
                
        String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
        String lStrCaseStatus = bundleConst.getString("STATUS.APPROVED");
        String lStrRcvryFlag = bundleConst.getString("RECOVERY.MONTHLY");        
        String lStrMoneyOrder = "MONEY ORDER";
        String lStrArrTI = bundleConst.getString("ARREAR.TI");
        String lStrArrMA = bundleConst.getString("ARREAR.MA");
        String lStrArrPension = bundleConst.getString("ARREAR.PENSION");
        String lStrArrFP1 = bundleConst.getString("ARREAR.FP1");
        String lStrArrFP2 = bundleConst.getString("ARREAR.FP2");
        String lStrArrOMR = bundleConst.getString("ARREAR.OMR");
        String lStrCutPP = bundleConst.getString("CUT.PP");
        String lStrCutPT = bundleConst.getString("CUT.PT");
        String lStrCutIT = bundleConst.getString("CUT.IT");
        String lStrCutST = bundleConst.getString("CUT.ST");
        String lStrCutCVP = bundleConst.getString("CUT.CVP");
        String lStrCutPerBen = bundleConst.getString("CMN.PBENEFIT");
        String lStrCutTmpBen = bundleConst.getString("CMN.TBENEFIT");        
        String lStrVoluntary = bundleConst.getString("CMN.VOLUNTARY");
		String lStrFamilyLost = bundleConst.getString("CMN.FAMILYLOST");
		List lLstAccNo = null;
        String lStrforMonth = null;
        String lStrforYear = null;
        String lStrBank = null;
        String lStrBranch = null;
        String lStrHeadCode = null;
        String lStrDate = null;
        String lDateOfDeathYYYYMM = null;
        String lDayOfDeathDD = null;
        
        String lStrScheme = null;
        String lStrPnsnrCode = null;
        String lStrPnsnerName = null;
        String lStrPPONo = null;
        String lStrAccNo = null;
        Long lDPPer = 0L;
        Long lTIPer = 0L;
        Long lStrPenRqstId = 0L;
        Double lCVPMonthlyAmt = 0D;
        Double lMAAmt = 0D;
        Double lFP1Amt = 0D;
        Double lFP2Amt = 0D;
        Double lDPPerAmt = 0D;
        Double lTIPerAmt = 0D;
        Double lBasicPensionAmt = 0D;
        Double lPensionCutAmt = 0D;
        Double lSpecialCutAmt = 0D;
        Double lITCutAmt = 0D;
        Double lOtherBenefits = 0D;
        Double lRecoveryAmt = 0D;
        Double lArrearAmt = 0D;
        Double lNetPensionAmt = 0D;
        Date lDateOfDeath = null;
        Date lFP1Date = null;
        Date lFP2Date = null;        
		Date lEndDate = null;
		String endDays = null;
		Integer EDays = 0;
		Integer EDate = 0;
        Double OMR = 0D;
        Double lDP = 0D;
        Double lTI = 0D;
        Double lPendingArrear = 0D;
        Integer lLastPaidMonth = 0;
        List lItCutDtls = new ArrayList();
        List lArrearDtls = new ArrayList();
        Integer lPrevMonth = 0;
        Integer lDays =0;
        Integer date = 0;
        Integer date1 = 0;
        Integer lIntDate = 0;
        String lArrComputeFlag = null;
        List lLstFPmembers = new ArrayList();
        String lStrFPFlag = "N";
        List fpMemberList = null;        
        Double lTmpNetAmt = 0D;
        String fp1Date = null;
        String fp2Date = null;
        String endDate = null;
        Double allnBf56 = 0D;
        Double allnAf56 = 0D;
		Double allnAf60 = 0D;
		Double lPerPension = 0D;
		Double lIR = 0D;
		Double lMOComm = 0D;
		String lStrTreasuryName = null;
		CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
		Double lPensionArr = 0D;
        Double lFP1Arr = 0D;
        Double lFP2Arr = 0D;
        Double lTIArr = 0D;
        Double lMAArr = 0D;
        Double lOtherArr = 0D;
        Double lReducedPen = 0D;
        
        MonthlyPensionBillDAO lObjMnthlyBillDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
    	MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
    	
		try{
			setSessionInfo(inputMap);
        	lArrComputeFlag = inputMap.get("ArrComputeFlag").toString();
        	        	 
        	lStrforMonth = inputMap.get("ForMonth").toString();
        	lStrforYear = inputMap.get("ForYear").toString();
        	lStrBank = inputMap.get("Bank").toString();
        	lStrBranch = inputMap.get("Branch").toString();
        	lStrHeadCode = inputMap.get("HeadCode").toString();
        	lStrDate = inputMap.get("Date").toString();
        	lStrScheme = inputMap.get("Scheme").toString();
        	
			lObjTrnPensionRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
			lStrDate = inputMap.get("Date").toString();
			
			lStrPnsnrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
			
			lEndDate = lObjTrnPensionRqstHdrVO.getEndDate();
			if(lEndDate != null){
    			endDate = new SimpleDateFormat("yyyyMM").format(lEndDate);
        		endDays = new SimpleDateFormat("dd").format(lEndDate);
				EDays = Integer.valueOf(endDays);
    		}
			else{
				endDate = "000000";
			}
			EDate = Integer.valueOf(endDate);
			//check the last month for which monthly pension or pension paid....
			//if 0 implies no bill generated for this pensioner...not even pension bill....
			//hence should not compute monthly bill for tht pensioner
    		lLastPaidMonth = lObjMnthlyBillDAO.getLastMonth(lStrPnsnrCode, lStrScheme);
    		lIntDate = Integer.parseInt(lStrDate);
    		
    		if((lIntDate%100) == 01){
    			lPrevMonth = lIntDate - 89;
    		}
    		else{
    			lPrevMonth = lIntDate - 1;
    		}
			
			//gLogger.info("computing monthly pension for the month of "+lStrDate);
			//pension for current month needs to be computed
			lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsnrCode);
			lLstAccNo = lObjMnthlyBillDAO.getAccountNo(lStrPnsnrCode, lStrBank, lStrBranch);
			if(lLstAccNo.get(0) != null)
			{
				lStrAccNo = lLstAccNo.get(0).toString();
			}
			else
			{
				lStrAccNo = "";
			}
			lRecoveryAmt =  lObjMnthlyBillDAO.getRecoveryDtls(lStrPnsnrCode, lStrRcvryFlag, lStrDate);
			lItCutDtls = lObjMnthlyBillDAO.getItCutDtls(lStrPnsnrCode, lStrDate);
			lArrearDtls = lObjMnthlyBillDAO.getArrearDtls(lStrPnsnrCode, lStrDate);
		
			// Getting the Value from TrnPensionRqstHdr... Start...
			lStrPenRqstId = lObjTrnPensionRqstHdrVO.getPensionRequestId();
			if(lObjTrnPensionRqstHdrVO.getPpoNo()!= null){
				lStrPPONo = lObjTrnPensionRqstHdrVO.getPpoNo();
			}
			if(lObjTrnPensionRqstHdrVO.getBasicPensionAmount() != null){
				lBasicPensionAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getDpPercent() != null){
				lDPPer = Long.parseLong(lObjTrnPensionRqstHdrVO.getDpPercent().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getTiPercent() != null){
				lTIPer = Long.parseLong(lObjTrnPensionRqstHdrVO.getTiPercent().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getMedicalAllowenceAmount() != null){
				lMAAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getMedicalAllowenceAmount().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getFp1Amount() != null){
				lFP1Amt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getFp1Amount().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getFp2Amount() != null){
				lFP2Amt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getFp2Amount().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getRedBf11156() != null){
				allnBf56 = Double.parseDouble(lObjTrnPensionRqstHdrVO.getOrgBf11156().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getRedAf11156() != null){
				allnAf56 = Double.parseDouble(lObjTrnPensionRqstHdrVO.getOrgAf11156().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getRedAf10560() != null){
				allnAf60 = lObjTrnPensionRqstHdrVO.getRedAf10560();
			}
			if(lObjTrnPensionRqstHdrVO.getPersonalPension() != null){
				lPerPension = Double.parseDouble(lObjTrnPensionRqstHdrVO.getPersonalPension().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getIr() != null){
				lIR = Double.parseDouble(lObjTrnPensionRqstHdrVO.getIr().toString());
			}
			lFP1Date = lObjTrnPensionRqstHdrVO.getFp1Date();
			lFP2Date = lObjTrnPensionRqstHdrVO.getFp2Date();
			
			inputMap.put("SchemeType", lObjTrnPensionRqstHdrVO.getSchemeType());
	        List lLstParameters = new ArrayList();
	        lLstParameters.add("locName");
	        lLstParameters.add("CmnLocationMst");
	        lLstParameters.add("locId ");
	        lLstParameters.add(lObjTrnPensionRqstHdrVO.getLocationCode());
	        lLstParameters.add("-1");
	        List lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
	        if(lLstRes != null && lLstRes.size()>0)
	        {
	            lStrTreasuryName = lLstRes.get(0).toString();
	        }
	        inputMap.put("TreasuryName", lStrTreasuryName);
			// Getting the Value from TrnPensionRqstHdr... End...
			
			if(lObjMstPensionerHdrVO != null)
	    	{
	    		// Getting the Value from MstPensionerHdr... Start...
				if(lObjMstPensionerHdrVO.getMiddleName() != null){
					lStrPnsnerName = lObjMstPensionerHdrVO.getFirstName() + ' ' + 
	 				  				 lObjMstPensionerHdrVO.getMiddleName() + ' ' +
	 				  				 lObjMstPensionerHdrVO.getLastName();
				}
				else{
					lStrPnsnerName = lObjMstPensionerHdrVO.getFirstName() + ' ' + 
									 lObjMstPensionerHdrVO.getLastName();
				}
				
				 // Check for Basic Pension / FP1 Pension / FP2 Pension... Start....
	        		lDateOfDeath = lObjMstPensionerHdrVO.getDateOfDeath();
	    	}
			
			if(lItCutDtls != null && lItCutDtls.size() > 0)
			{
				lITCutAmt = 0D;
				lPensionCutAmt = 0D;
				lCVPMonthlyAmt = 0D;
				
				for(int i=0;i<lItCutDtls.size();i+=2)
				{
					if(lStrCutIT.equals(lItCutDtls.get(i))){
						lITCutAmt = lITCutAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
					}
					if(lStrCutPT.equals(lItCutDtls.get(i))){
						lPensionCutAmt = lPensionCutAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
					}
					if(lStrCutPP.equals(lItCutDtls.get(i))){
						lPensionCutAmt = lPensionCutAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
					}
					if(lStrCutST.equals(lItCutDtls.get(i))){
						lSpecialCutAmt = lSpecialCutAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
					}
					if(lStrCutCVP.equals(lItCutDtls.get(i))){
						lCVPMonthlyAmt = lCVPMonthlyAmt + Double.parseDouble(lItCutDtls.get(i+1).toString());
					}
					if(lStrCutPerBen.equals(lItCutDtls.get(i))){
						lOtherBenefits = lOtherBenefits + Double.parseDouble(lItCutDtls.get(i+1).toString());
					}
					if(lStrCutTmpBen.equals(lItCutDtls.get(i))){
						lOtherBenefits = lOtherBenefits + Double.parseDouble(lItCutDtls.get(i+1).toString());
					}
				}
			}
			
			if(lArrearDtls != null && lArrearDtls.size() > 0)
			{
				for(int i=0;i<lArrearDtls.size();i+=2)
				{
					if(lStrArrTI.equals(lArrearDtls.get(i)))
					{
						lTIArr = lTIArr + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					if(lStrArrMA.equals(lArrearDtls.get(i)))
					{
						lMAArr = lMAArr + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					if(lStrArrPension.equals(lArrearDtls.get(i)))
					{
						lPensionArr = lPensionArr + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					if(lStrArrFP1.equals(lArrearDtls.get(i)))
					{
						lFP1Arr = lFP1Arr + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					if(lStrArrFP2.equals(lArrearDtls.get(i)))
					{
						lFP2Arr = lFP2Arr + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
				}
				lOtherArr = lMAArr + lPensionArr + lFP1Arr + lFP2Arr;
				lArrearAmt = lTIArr + lOtherArr;
			}
			lDPPerAmt = (lBasicPensionAmt - lPensionCutAmt) * lDPPer / 100;
			lTIPerAmt = (lBasicPensionAmt - lPensionCutAmt + lDPPerAmt) * lTIPer / 100; 
			
	        // All values for normal pension are available
			// now compute for other cases
			
			// compute no of days in this month
			Calendar myCal     = Calendar.getInstance();
			myCal.set(Calendar.MONTH, Integer.parseInt(lStrforMonth) - 1);
	        myCal.set(Calendar.YEAR, Integer.parseInt(lStrforYear));
	        myCal.set(Calendar.DAY_OF_MONTH, 1); 
			lDays = myCal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			if( lDateOfDeath != null && lDateOfDeath.toString().length() > 0 )
			{
				//pensioner has expired
				
				String lStrPenType = lObjTrnPensionRqstHdrVO.getPensionType();
				if(lStrPenType.equals(lStrVoluntary) || lStrPenType.equals(lStrFamilyLost)){
					//compute correct FP1  n fp2 date ---- temp = DoD + 7 yrs
					List arrFPlist = getDatePlus7yrs(lDateOfDeath,lFP1Date,lFP2Date);
					lFP1Date = (Date)arrFPlist.get(0);
					lFP2Date = (Date)arrFPlist.get(1);
				}
				if(lFP1Date != null){
	    			fp1Date = new SimpleDateFormat("yyyyMM").format(lFP1Date);
	    		}
	    		if(lFP2Date != null){
	    			fp2Date = new SimpleDateFormat("yyyyMM").format(lFP2Date);
	    		}
				//compute lDateOfDeathYYYYMM
				lDateOfDeathYYYYMM = new SimpleDateFormat("yyyyMM").format(lDateOfDeath);
				lDayOfDeathDD = new SimpleDateFormat("dd").format(lDateOfDeath);
				//check if date of death in month for which bill generated 
				if(lDateOfDeathYYYYMM.equals(lStrDate))
				{
					//pensioner died in current month
					
					//	OMR calculation
					lDP = ((lBasicPensionAmt * lDPPer) / 100 );
					lTI = (((lBasicPensionAmt + lDP) * lTIPer) / 100 );
					OMR = lBasicPensionAmt + lDP + lTI;
					
					inputMap.put("PenRqstId", lStrPenRqstId);
					inputMap.put("PenCode", lStrPnsnrCode);
					inputMap.put("ArrearType", lStrArrOMR);
					inputMap.put("EffFrom", lStrDate);
					inputMap.put("EffTo", lStrDate);
					inputMap.put("DiffAmt", Math.round(OMR));
					
					lObjArrearVO = new TrnPensionArrearDtls();
					lObjArrearVO = insertArrearDtls(inputMap);
					lLstInsertArrears.add(lObjArrearVO);
					//gLogger.info("OMR"+OMR);
					
					//lPendingArrear = lPendingArrear + Math.round(OMR);	-- OMR is now not included on other arrear
					
					
					//compute complex calculaton for this month
					date = Integer.parseInt(lDayOfDeathDD);
					
					if(lStrDate.equals(endDate))
					{
						//end date is in this month itself
						//check if end date before date of death
						if(lEndDate.before(lDateOfDeath) || lEndDate.equals(lDateOfDeath))
						{
							//compute till end date according to basic
							lBasicPensionAmt = lBasicPensionAmt * EDays / lDays;
		    				lPensionCutAmt = lPensionCutAmt * EDays / lDays;
		    				lCVPMonthlyAmt = lCVPMonthlyAmt * EDays / lDays;
		    				lMAAmt = lMAAmt * EDays / lDays;
		    				lITCutAmt = lITCutAmt * EDays / lDays;
		    				lSpecialCutAmt = lSpecialCutAmt * EDays / lDays;
		    				lOtherBenefits = lOtherBenefits * EDays / lDays;
		    				lPerPension = lPerPension * EDays / lDays;
		    				lIR = lIR * EDays / lDays;
		    				lDPPerAmt = (lBasicPensionAmt - lPensionCutAmt) * lDPPer / 100;
		    				lTIPerAmt = (lBasicPensionAmt - lPensionCutAmt + lDPPerAmt) * lTIPer / 100; 
		    						                	    				
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt + lTIPerAmt +lPerPension + lIR
		    				     - lCVPMonthlyAmt + lMAAmt - lITCutAmt - lSpecialCutAmt + lOtherBenefits - lRecoveryAmt + lArrearAmt; 
		    					
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    	    				inputMap.put("ArrearType", lStrArrPension);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
						}
						else
						{
							//compute till DoD according to basic and after DoD to end date according to fp1 or/and fp2 applicable
							lBasicPensionAmt = lBasicPensionAmt * date / lDays;
		    				lPensionCutAmt = lPensionCutAmt * date / lDays;
		    				lCVPMonthlyAmt = lCVPMonthlyAmt * date / lDays;
		    				lITCutAmt = lITCutAmt * date / lDays;
		    				lDPPerAmt = (lBasicPensionAmt - lPensionCutAmt) * lDPPer / 100;
		    				lTIPerAmt = (lBasicPensionAmt - lPensionCutAmt + lDPPerAmt) * lTIPer / 100; 
		    				
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt + lTIPerAmt 
		    				     - lCVPMonthlyAmt + ((lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits) * date / lDays) - lITCutAmt  - lRecoveryAmt + lArrearAmt; 
		    					
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    				        inputMap.put("ArrearType", lStrArrPension);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
		    				
		    				//check for fp1 n fp2 in the remaining days
		    				if(fp1Date.equals(lStrDate) && fp2Date.equals(lStrDate))
	    					{
		    					//both fp1 n fp2 date in remaining days...so need to handle both situations
		    					if(lEndDate.before(lFP1Date) || lEndDate.equals(lFP1Date))
		    					{
		    						//give for remaining days...i.e. till end date accordng to fp1
		    						List divAmt = getDividedBasic(lFP1Amt, lDPPer);
		    						lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt.get(0).toString()) * (EDays - date) / lDays);
	    	        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt.get(1).toString()) * (EDays - date) / lDays);
		    						lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer * (EDays - date) / (lDays * 100));	
	    	        				if("Y".equals(lArrComputeFlag))
	        	    				{
	    	        					lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP1Amt 							//basic + dp amt
	        	    								  + (lFP1Amt * lTIPer / 100)		//lTIPerAmt 
	        	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (EDays - date) / lDays;
	        	    					inputMap.put("PenRqstId", lStrPenRqstId);
		        	    				inputMap.put("PenCode", lStrPnsnrCode);
	    	    				        inputMap.put("ArrearType", lStrArrFP1);
	    	    				        inputMap.put("EffFrom", lStrDate);
	    	    				        inputMap.put("EffTo", lStrDate);
	    	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    	    				        
	    	    				        lObjArrearVO = new TrnPensionArrearDtls();
		        	    				lObjArrearVO = insertArrearDtls(inputMap);
		        	    				lLstInsertArrears.add(lObjArrearVO);
	        	    				}
		    					}
		    					else
		    					{
		    						//end date after fp2 date
		    						//for remaining days give according to fp1 till fp1 date then give according to fp2 till end date
		    						//give for remaining days... till fp1 date according to fp1, rest according to fp2
		    						date1 = Integer.parseInt(new SimpleDateFormat("dd").format(lFP1Date));
		    						//1 - date....according to basic
		    						//date - date1 ...according to fp1
		    						//date1 - Edays ...according to fp2
		    						List divAmt = getDividedBasic(lFP1Amt, lDPPer);
		    						lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt.get(0).toString()) * (date1 - date) / lDays);
		    						lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt.get(1).toString())) * (date1 - date) / lDays;
	        	        			lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer * (date1 - date) / (lDays * 100));	
	        	        			if("Y".equals(lArrComputeFlag))
	        	    				{
	        	    					lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP1Amt 							//basic
	        	    								  + (lFP1Amt * lTIPer / 100)		//lTIPerAmt 
	        	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (date1 - date) / lDays;
	        	    					inputMap.put("PenRqstId", lStrPenRqstId);
		        	    				inputMap.put("PenCode", lStrPnsnrCode);
	    	    				        inputMap.put("ArrearType", lStrArrFP1);
	    	    				        inputMap.put("EffFrom", lStrDate);
	    	    				        inputMap.put("EffTo", lStrDate);
	    	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    	    				        
	    	    				        lObjArrearVO = new TrnPensionArrearDtls();
		        	    				lObjArrearVO = insertArrearDtls(inputMap);
		        	    				lLstInsertArrears.add(lObjArrearVO);
	        	    				}
	        	        			List divAmt1 = getDividedBasic(lFP2Amt, lDPPer);
	        	        			lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt1.get(0).toString()) * (EDays - date1) / lDays);
	        	        			lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt1.get(1).toString())) * (EDays - date1) / lDays;
	        	        			lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt1.get(0).toString()) + Double.parseDouble(divAmt1.get(1).toString())) * lTIPer * (EDays - date1) / (lDays * 100));	
	        	        			if("Y".equals(lArrComputeFlag))
	        	    				{
	        	        				lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP2Amt 							//basic
	        	    								  + (lFP2Amt * lTIPer / 100)		//lTIPerAmt 
	        	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (EDays - date1) / lDays;
	        	    					inputMap.put("PenRqstId", lStrPenRqstId);
		        	    				inputMap.put("PenCode", lStrPnsnrCode);
	    	    				        inputMap.put("ArrearType", lStrArrFP2);
	    	    				        inputMap.put("EffFrom", lStrDate);
	    	    				        inputMap.put("EffTo", lStrDate);
	    	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    	    				        
	    	    				        lObjArrearVO = new TrnPensionArrearDtls();
		        	    				lObjArrearVO = insertArrearDtls(inputMap);
		        	    				lLstInsertArrears.add(lObjArrearVO);
	        	    				}
		    					}
	    					}
		    				else
		    				{
		    					//give according to fp1 or fp2 whichever is applicable till end date
	    	    				if(lDateOfDeath.before(lFP1Date) || lDateOfDeath.equals(lFP1Date))
	    	    				{
	    	    					List divAmt = getDividedBasic(lFP1Amt, lDPPer);
	    	        				lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt.get(0).toString()) * (EDays - date) / lDays);
	    	        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt.get(1).toString())) * (EDays - date) / lDays;
	    	        				lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer * (EDays - date) / (lDays * 100));	
	    	        				if("Y".equals(lArrComputeFlag))
	        	    				{
	    	        					lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP1Amt 							//basic
	        	    								  + (lFP1Amt * lTIPer / 100)		//lTIPerAmt 
	        	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (EDays - date) / lDays;
	        	    					inputMap.put("PenRqstId", lStrPenRqstId);
		        	    				inputMap.put("PenCode", lStrPnsnrCode);
	    	    				        inputMap.put("ArrearType", lStrArrFP1);
	    	    				        inputMap.put("EffFrom", lStrDate);
	    	    				        inputMap.put("EffTo", lStrDate);
	    	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    	    				        
	    	    				        lObjArrearVO = new TrnPensionArrearDtls();
		        	    				lObjArrearVO = insertArrearDtls(inputMap);
		        	    				lLstInsertArrears.add(lObjArrearVO);
	        	    				}
	    	        			}
	    	        			else if(lDateOfDeath.after(lFP2Date) || lDateOfDeath.equals(lFP2Date))
	    	        			{
	    	        				List divAmt = getDividedBasic(lFP2Amt, lDPPer);
	    	        				lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt.get(0).toString()) * (EDays - date) / lDays);
	    	        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt.get(1).toString())) * (EDays - date) / lDays;
	    	        				lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer * (EDays - date) / (lDays * 100));	
	    	        				if("Y".equals(lArrComputeFlag))
	        	    				{
	    	        					lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP2Amt 							//basic
	        	    								  + (lFP2Amt * lTIPer / 100)		//lTIPerAmt 
	        	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (EDays - date) / lDays;
	        	    					inputMap.put("PenRqstId", lStrPenRqstId);
		        	    				inputMap.put("PenCode", lStrPnsnrCode);
	    	    				        inputMap.put("ArrearType", lStrArrFP2);
	    	    				        inputMap.put("EffFrom", lStrDate);
	    	    				        inputMap.put("EffTo", lStrDate);
	    	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    	    				        
	    	    				        lObjArrearVO = new TrnPensionArrearDtls();
		        	    				lObjArrearVO = insertArrearDtls(inputMap);
		        	    				lLstInsertArrears.add(lObjArrearVO);
	        	    				}
	    	        			}
		    				}
		    				lMAAmt = lMAAmt * EDays / lDays;
		    				lSpecialCutAmt = lSpecialCutAmt * EDays / lDays;
		    				lOtherBenefits = lOtherBenefits * EDays / lDays;
		    				lPerPension = lPerPension * EDays / lDays;
		    				lIR = lIR * EDays / lDays;
						}
					}
					else
					{
						//end date not in this month
						//compute reduced amount for the days alive 
	    				lBasicPensionAmt = lBasicPensionAmt * date / lDays;
	    				lPensionCutAmt = lPensionCutAmt * date / lDays;
	    				lCVPMonthlyAmt = lCVPMonthlyAmt * date / lDays;
	    				lITCutAmt = lITCutAmt * date / lDays;
	    				lDPPerAmt = (lBasicPensionAmt - lPensionCutAmt) * lDPPer / 100;
	    				lTIPerAmt = (lBasicPensionAmt - lPensionCutAmt + lDPPerAmt) * lTIPer / 100; 
	    				
	    				if("Y".equals(lArrComputeFlag))
	    				{
	    					lTmpNetAmt = 0D;
	    					lTmpNetAmt = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt + lTIPerAmt 
	    				     			 - lCVPMonthlyAmt + ((lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* date / lDays) - lITCutAmt
	    				     			 - lRecoveryAmt + lArrearAmt; 
	    					
	    					inputMap.put("PenRqstId", lStrPenRqstId);
		    				inputMap.put("PenCode", lStrPnsnrCode);
					        inputMap.put("ArrearType", lStrArrPension);
					        inputMap.put("EffFrom", lStrDate);
					        inputMap.put("EffTo", lStrDate);
					        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
					        
					        lObjArrearVO = new TrnPensionArrearDtls();
		    				lObjArrearVO = insertArrearDtls(inputMap);
		    				lLstInsertArrears.add(lObjArrearVO);
	    				}
	    				
	    				//check for fp1 n fp2 in the remaining days
	    				if(fp1Date.equals(lStrDate) && fp2Date.equals(lStrDate))
						{
	    					if(lDateOfDeath.equals(lFP2Date) || lDateOfDeath.after(lFP2Date))
	    					{
	    						// given according to basic till date of death
	    						// now for remaining days give according to fp2
	    						// coz death after fp1 date
	    						List divAmt = getDividedBasic(lFP2Amt, lDPPer);
	    						lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt.get(0).toString()) * (lDays - date) / lDays);
	    						lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt.get(1).toString())) * (lDays - date) / lDays;
	    						lTIPerAmt = lTIPerAmt + (((Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer * (lDays - date) / (lDays * 100)));	
		        				if("Y".equals(lArrComputeFlag))
	    	    				{
		        					lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP2Amt 							//basic
	    	    								  + (lFP2Amt * lTIPer / 100)		//lTIPerAmt 
	    	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (lDays - date) / lDays;
	    	    					inputMap.put("PenRqstId", lStrPenRqstId);
	        	    				inputMap.put("PenCode", lStrPnsnrCode);
		    				        inputMap.put("ArrearType", lStrArrFP2);
		    				        inputMap.put("EffFrom", lStrDate);
		    				        inputMap.put("EffTo", lStrDate);
		    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
		    				        
		    				        lObjArrearVO = new TrnPensionArrearDtls();
	        	    				lObjArrearVO = insertArrearDtls(inputMap);
	        	    				lLstInsertArrears.add(lObjArrearVO);
	    	    				}
	    						
	    					}
	    					else
	    					{
	//	            	    						give for remaining days... till fp1 date according to fp1, rest according to fp2
	    						date1 = Integer.parseInt(new SimpleDateFormat("dd").format(lFP1Date));
	    						//1 - date....according to basic
	    						//date - date1 ...according to fp1
	    						//date1 - ldays ...according to fp2
	    						List divAmt = getDividedBasic(lFP1Amt, lDPPer);
	    						lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt.get(0).toString()) * (date1 - date) / lDays);
	    						lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt.get(1).toString())) * (date1 - date) / lDays;
	    	        			lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer * (date1 - date) / (lDays * 100));	
	    	        			if("Y".equals(lArrComputeFlag))
	    	    				{
	    	        				lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP1Amt 							//basic
	    	    								  + (lFP1Amt * lTIPer / 100)		//lTIPerAmt 
	    	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (date1 - date) / lDays;
	    	    					inputMap.put("PenRqstId", lStrPenRqstId);
	        	    				inputMap.put("PenCode", lStrPnsnrCode);
		    				        inputMap.put("ArrearType", lStrArrFP1);
		    				        inputMap.put("EffFrom", lStrDate);
		    				        inputMap.put("EffTo", lStrDate);
		    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
		    				        
		    				        lObjArrearVO = new TrnPensionArrearDtls();
	        	    				lObjArrearVO = insertArrearDtls(inputMap);
	        	    				lLstInsertArrears.add(lObjArrearVO);
	    	    				}
	    	        			List divAmt1 = getDividedBasic(lFP2Amt, lDPPer);
	    	        			lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt1.get(0).toString()) * (lDays - date1) / lDays);
	    	        			lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt1.get(1).toString())) * (lDays - date1) / lDays;
	    	        			lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt1.get(0).toString()) + Double.parseDouble(divAmt1.get(1).toString())) * lTIPer * (lDays - date1) / (lDays * 100));	
	    	        			if("Y".equals(lArrComputeFlag))
	    	    				{
	    	        				lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP2Amt 							//basic
	    	    								  + (lFP2Amt * lTIPer / 100)		//lTIPerAmt 
	    	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (lDays - date1) / lDays;
	    	    					inputMap.put("PenRqstId", lStrPenRqstId);
	        	    				inputMap.put("PenCode", lStrPnsnrCode);
		    				        inputMap.put("ArrearType", lStrArrFP2);
		    				        inputMap.put("EffFrom", lStrDate);
		    				        inputMap.put("EffTo", lStrDate);
		    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
		    				        
		    				        lObjArrearVO = new TrnPensionArrearDtls();
	        	    				lObjArrearVO = insertArrearDtls(inputMap);
	        	    				lLstInsertArrears.add(lObjArrearVO);
	    	    				}
	    					}
						}
	    				else
	    				{
	    					//give according to fp1 or fp2 whichever is applicable
		    				if(lDateOfDeath.before(lFP1Date) || lDateOfDeath.equals(lFP1Date))
		    				{
		    					List divAmt = getDividedBasic(lFP1Amt, lDPPer);
		        				lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt.get(0).toString()) * (lDays - date) / lDays);
		        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt.get(1).toString())) * (lDays - date) / lDays;
		        				lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer * (lDays - date) / (lDays * 100));	
		        				if("Y".equals(lArrComputeFlag))
	    	    				{
		        					lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP1Amt 							//basic
	    	    								  + (lFP1Amt * lTIPer / 100)		//lTIPerAmt 
	    	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (lDays - date) / lDays;
	    	    					inputMap.put("PenRqstId", lStrPenRqstId);
	        	    				inputMap.put("PenCode", lStrPnsnrCode);
		    				        inputMap.put("ArrearType", lStrArrFP1);
		    				        inputMap.put("EffFrom", lStrDate);
		    				        inputMap.put("EffTo", lStrDate);
		    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
		    				        
		    				        lObjArrearVO = new TrnPensionArrearDtls();
	        	    				lObjArrearVO = insertArrearDtls(inputMap);
	        	    				lLstInsertArrears.add(lObjArrearVO);
	    	    				}
		        			}
		        			else if(lDateOfDeath.after(lFP2Date) || lDateOfDeath.equals(lFP2Date))
		        			{
		        				List divAmt = getDividedBasic(lFP2Amt, lDPPer);
		        				lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt.get(0).toString()) * (lDays - date) / lDays);
		        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(divAmt.get(1).toString())) * (lDays - date) / lDays;
		        				lTIPerAmt = lTIPerAmt + (((Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer * (lDays - date) / (lDays * 100)));	
		        				if("Y".equals(lArrComputeFlag))
	    	    				{
		        					lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP2Amt 							//basic
	    	    								  + (lFP2Amt * lTIPer / 100)		//lTIPerAmt 
	    	    								  + lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* (lDays - date) / lDays;
	    	    					inputMap.put("PenRqstId", lStrPenRqstId);
	        	    				inputMap.put("PenCode", lStrPnsnrCode);
		    				        inputMap.put("ArrearType", lStrArrFP2);
		    				        inputMap.put("EffFrom", lStrDate);
		    				        inputMap.put("EffTo", lStrDate);
		    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
		    				        
		    				        lObjArrearVO = new TrnPensionArrearDtls();
	        	    				lObjArrearVO = insertArrearDtls(inputMap);
	        	    				lLstInsertArrears.add(lObjArrearVO);
	    	    				}
		        			}
	    				}
					}
				}
				else
				{
					//pensioner is dead but has not expired in current month
					//gLogger.info("*************pensioner is dead but has not expired in current month**********");
					if(fp1Date.equals(lStrDate) && fp2Date.equals(lStrDate) && endDate.equals(lStrDate))
					{
						//all three fp1 n fp2 n end date in curr month...compute division for them
						if(lEndDate.before(lFP1Date) || lEndDate.equals(lFP1Date))
						{	//end date is before or equal to fp1 date
							//give according to fp1 till end date
							//gLogger.info("end date is before or equal to fp1 date, give according to fp1 till end date");
							List divAmt = getDividedBasic(lFP1Amt, lDPPer);
							lBasicPensionAmt = Double.parseDouble(divAmt.get(0).toString());
							lDPPerAmt = Double.parseDouble(divAmt.get(1).toString());
	    					lTIPerAmt = (Double.parseDouble(divAmt.get(0).toString()) + Double.parseDouble(divAmt.get(1).toString())) * lTIPer / 100;
		    				
	    					lBasicPensionAmt = lBasicPensionAmt * EDays / lDays;
	    					lDPPerAmt = lDPPerAmt * EDays / lDays;
		    				lTIPerAmt = lTIPerAmt * EDays / lDays;
		    				lMAAmt = lMAAmt * EDays / lDays;
		    				lSpecialCutAmt = lSpecialCutAmt * EDays / lDays;
		    				lOtherBenefits = lOtherBenefits * EDays / lDays;
		    				lPerPension = lPerPension * EDays / lDays;
		    				lIR = lIR * EDays / lDays;
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = (lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
		    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)
		    								- lRecoveryAmt + lArrearAmt; 
		    								//- lPensionCutAmt + lDPPerAmt - lCVPMonthlyAmt - lITCutAmt 
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    				        inputMap.put("ArrearType", lStrArrFP1);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
						}
						else
						{
							//give upto fp1 date according to fp1
							//after tht give according to fp2 till end date
							//gLogger.info("give till fp1 date according to fp1, then according to fp2 till end date");
							date = Integer.parseInt(new SimpleDateFormat("dd").format(lFP1Date));
							
							List divAmt = getDividedBasic(lFP1Amt, lDPPer);
							lBasicPensionAmt = Double.parseDouble(divAmt.get(0).toString()) * date / lDays;
							lDPPerAmt = Double.parseDouble(divAmt.get(1).toString()) * date / lDays;
		    				lTIPerAmt = ((lBasicPensionAmt + lDPPerAmt) * lTIPer / 100); 
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
		    				     			+ ((lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits) * date / lDays)
		    				     			- lRecoveryAmt + lArrearAmt; 
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    				        inputMap.put("ArrearType", lStrArrFP1);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
		    				List divAmt1 = getDividedBasic(lFP2Amt, lDPPer);
		    				lBasicPensionAmt = lBasicPensionAmt + (Double.parseDouble(divAmt1.get(0).toString()) * (EDays - date) / lDays);
		    				lDPPerAmt = lDPPerAmt + Double.parseDouble(divAmt1.get(1).toString()) * (EDays - date) / lDays;
		        			lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt1.get(0).toString()) + Double.parseDouble(divAmt1.get(1).toString())) * lTIPer / 100 * ((EDays - date) / lDays));	
		        			
		        			if("Y".equals(lArrComputeFlag))
		    				{
		        				lTmpNetAmt = 0D;
		    					lTmpNetAmt = (lFP2Amt + (lFP2Amt * lTIPer / 100) 
		    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits) * (EDays - date) / lDays;
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    				        inputMap.put("ArrearType", lStrArrFP2);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
		        			lMAAmt = lMAAmt * EDays / lDays;
		        			lSpecialCutAmt = lSpecialCutAmt * EDays / lDays;
		        			lOtherBenefits = lOtherBenefits * EDays / lDays;
		        			lPerPension = lPerPension * EDays / lDays;
		        			lIR = lIR * EDays / lDays;
						}
					}
					else 
					{
						//all three fp1 fp2 n end date not in this month...
						if(fp1Date.equals(lStrDate) && fp2Date.equals(lStrDate))
						{
							//give till fp1 date according to fp1, rest according to fp2
							//gLogger.info("give till fp1 date according to fp1, rest according to fp2");
							date = Integer.parseInt(new SimpleDateFormat("dd").format(lFP1Date));
							
							List divAmt = getDividedBasic(lFP1Amt, lDPPer);
							lBasicPensionAmt = Double.parseDouble(divAmt.get(0).toString()) * date / lDays;
							lDPPerAmt = Double.parseDouble(divAmt.get(1).toString()) * date / lDays;
		    				lTIPerAmt = (lBasicPensionAmt + lDPPerAmt) * lTIPer / 100; 
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
		    				     			+ (lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits) * date / lDays
		    				     			- lRecoveryAmt + lArrearAmt; 
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    				        inputMap.put("ArrearType", lStrArrFP1);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
		    				List divAmt1 = getDividedBasic(lFP2Amt, lDPPer);
		    				lBasicPensionAmt = lBasicPensionAmt + ((Double.parseDouble(divAmt1.get(0).toString()) * (lDays - date) / lDays));
		    				lDPPerAmt = lDPPerAmt + Double.parseDouble(divAmt1.get(1).toString())* (lDays - date) / lDays;
		    				lTIPerAmt = lTIPerAmt + ((Double.parseDouble(divAmt1.get(0).toString()) + Double.parseDouble(divAmt1.get(1).toString())) * lTIPer / 100 * ((lDays - date) / lDays));	
		        			if("Y".equals(lArrComputeFlag))
		    				{
		        				lTmpNetAmt = 0D;
		    					lTmpNetAmt = (lFP2Amt + (lFP2Amt * lTIPer / 100) 
		    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits) * (lDays - date) / lDays;
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    				        inputMap.put("ArrearType", lStrArrFP2);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
						}
						else if(Integer.valueOf(fp1Date) >= Integer.valueOf(lStrDate) && endDate.equals(lStrDate))
						{
							//give according to fp1 till end date
							//gLogger.info("give according to fp1 till end date");
							List divAmt = getDividedBasic(lFP1Amt, lDPPer);
							lBasicPensionAmt = Double.parseDouble(divAmt.get(0).toString());
							lDPPerAmt = Double.parseDouble(divAmt.get(1).toString());
	    					lTIPerAmt = (lBasicPensionAmt + lDPPerAmt) * lTIPer / 100;
		    				
	    					lBasicPensionAmt = lBasicPensionAmt * EDays / lDays;
	    					lDPPerAmt = lDPPerAmt * EDays / lDays;
		    				lTIPerAmt = lTIPerAmt * EDays / lDays;
		    				lMAAmt = lMAAmt * EDays / lDays;
		    				lSpecialCutAmt = lSpecialCutAmt * EDays / lDays;
		    				lOtherBenefits = lOtherBenefits * EDays / lDays;
		    				lPerPension = lPerPension * EDays / lDays;
		    				lIR = lIR * EDays / lDays;
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = (lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
		    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)
		    				     			- lRecoveryAmt + lArrearAmt;
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    				        inputMap.put("ArrearType", lStrArrFP1);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
						}
						else if(Integer.valueOf(fp2Date)<=Integer.valueOf(lStrDate) && endDate.equals(lStrDate))
						{
							//give according to fp2 till end date
							//gLogger.info("give according to fp2 till end date");
							List divAmt = getDividedBasic(lFP2Amt, lDPPer);
							lBasicPensionAmt = Double.parseDouble(divAmt.get(0).toString());
							lDPPerAmt = Double.parseDouble(divAmt.get(1).toString());
	    					lTIPerAmt = (lBasicPensionAmt + lDPPerAmt) * lTIPer / 100;
	    					
	    					lBasicPensionAmt = lBasicPensionAmt * EDays / lDays;
	    					lDPPerAmt = lDPPerAmt * EDays / lDays;
		    				lTIPerAmt = lTIPerAmt * EDays / lDays;
		    				lMAAmt = lMAAmt * EDays / lDays;
		    				lSpecialCutAmt = lSpecialCutAmt * EDays / lDays;
		    				lOtherBenefits = lOtherBenefits * EDays / lDays;
		    				lPerPension = lPerPension * EDays / lDays;
		    				lIR = lIR * EDays / lDays;
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = (lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
		    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)
		    				     			- lRecoveryAmt + lArrearAmt;
		    					inputMap.put("PenRqstId", lStrPenRqstId);
	    	    				inputMap.put("PenCode", lStrPnsnrCode);
	    				        inputMap.put("ArrearType", lStrArrFP2);
	    				        inputMap.put("EffFrom", lStrDate);
	    				        inputMap.put("EffTo", lStrDate);
	    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
	    				        
	    				        lObjArrearVO = new TrnPensionArrearDtls();
	    	    				lObjArrearVO = insertArrearDtls(inputMap);
	    	    				lLstInsertArrears.add(lObjArrearVO);
		    				}
						}
						else
						{
							//only 1 either fp1 or fp2 applicable for whole month
							if(Integer.valueOf(lStrDate) <= Integer.valueOf(fp1Date))
		    				{
								//gLogger.info("in condition give fp1 for entire month");
								List divAmt = getDividedBasic(lFP1Amt, lDPPer);
	    						lBasicPensionAmt = Double.parseDouble(divAmt.get(0).toString());
	    						lDPPerAmt = Double.parseDouble(divAmt.get(1).toString());
		    					lTIPerAmt = (lBasicPensionAmt + lDPPerAmt) * lTIPer / 100;
		    					if("Y".equals(lArrComputeFlag))
	    	    				{
		    						lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
	    	    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)
	    	    				     			- lRecoveryAmt + lArrearAmt;
	    	    					inputMap.put("PenRqstId", lStrPenRqstId);
	        	    				inputMap.put("PenCode", lStrPnsnrCode);
		    				        inputMap.put("ArrearType", lStrArrFP1);
		    				        inputMap.put("EffFrom", lStrDate);
		    				        inputMap.put("EffTo", lStrDate);
		    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
		    				        
		    				        lObjArrearVO = new TrnPensionArrearDtls();
	        	    				lObjArrearVO = insertArrearDtls(inputMap);
	        	    				lLstInsertArrears.add(lObjArrearVO);
	    	    				}
		        			}
		        			else if(Integer.valueOf(lStrDate) >= Integer.valueOf(fp2Date))
		        			{
		        				//gLogger.info("in condition give fp2 for entire month");
		        				List divAmt = getDividedBasic(lFP2Amt, lDPPer);
	    						lBasicPensionAmt = Double.parseDouble(divAmt.get(0).toString());
	    						lDPPerAmt = Double.parseDouble(divAmt.get(1).toString());
		    					lTIPerAmt = (lBasicPensionAmt + lDPPerAmt) * lTIPer / 100;
		    					if("Y".equals(lArrComputeFlag))
	    	    				{
		    						lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
	    	    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)
	    	    				     			- lRecoveryAmt + lArrearAmt;
	    	    					inputMap.put("PenRqstId", lStrPenRqstId);
	        	    				inputMap.put("PenCode", lStrPnsnrCode);
		    				        inputMap.put("ArrearType", lStrArrFP2);
		    				        inputMap.put("EffFrom", lStrDate);
		    				        inputMap.put("EffTo", lStrDate);
		    				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
		    				        
		    				        lObjArrearVO = new TrnPensionArrearDtls();
	        	    				lObjArrearVO = insertArrearDtls(inputMap);
	        	    				lLstInsertArrears.add(lObjArrearVO);
	    	    				}
		        			}
						}
					}
				}
					
				lCVPMonthlyAmt = 0.0;
				lPensionCutAmt = 0.0;
				lITCutAmt = 0.0;
				
				if("Y".equals(inputMap.get("FPFlag")))
				{
					//compute details for family pensioner's...
					//could result into multiple family pensioners
					lLstFPmembers = lObjMnthlyBillDAO.getMstPensionerFamilyDtls(lStrPnsnrCode);
					lStrFPFlag = "Y";
				
					fpMemberList = new ArrayList();
					fpMemberList = getFpMemberList(lLstFPmembers);
				}
			}
			else
			{
				//pensioner is alive....but check if end date in curr mnth 
				if(endDate.equals(lStrDate))
				{
					//end date in curr month...so give him pension only till end date
					lBasicPensionAmt = lBasicPensionAmt * EDays / lDays;
					lDPPerAmt = lDPPerAmt * EDays / lDays;
					lTIPerAmt = lTIPerAmt * EDays / lDays;
					lMAAmt = lMAAmt * EDays / lDays;
					lCVPMonthlyAmt = lCVPMonthlyAmt * EDays / lDays;
					lPensionCutAmt = lPensionCutAmt * EDays / lDays;
					lITCutAmt = lITCutAmt * EDays / lDays;
					lSpecialCutAmt = lSpecialCutAmt * EDays / lDays;
					lOtherBenefits = lOtherBenefits * EDays / lDays;
					lPerPension = lPerPension * EDays / lDays;
					lIR = lIR * EDays / lDays;
					
					if("Y".equals(lArrComputeFlag))
					{
						lTmpNetAmt = 0D;
						lTmpNetAmt = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt + lTIPerAmt 
					     - lCVPMonthlyAmt + lPerPension + lIR + lMAAmt - lITCutAmt - lSpecialCutAmt + lOtherBenefits - lRecoveryAmt + lArrearAmt; 
						
						inputMap.put("PenRqstId", lStrPenRqstId);
	    				inputMap.put("PenCode", lStrPnsnrCode);
				        inputMap.put("ArrearType", lStrArrPension);
				        inputMap.put("EffFrom", lStrDate);
				        inputMap.put("EffTo", lStrDate);
				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
				        
				        lObjArrearVO = new TrnPensionArrearDtls();
	    				lObjArrearVO = insertArrearDtls(inputMap);
	    				lLstInsertArrears.add(lObjArrearVO);
					}
				}
				else
				{
					//pensioner is alive and end date not in this month...i.e normal case
					//check if arrear to be inserted 
					if("Y".equals(lArrComputeFlag))
					{
						lTmpNetAmt = 0D;
						lTmpNetAmt = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt + lTIPerAmt 
					     - lCVPMonthlyAmt + lPerPension + lIR + lMAAmt - lITCutAmt - lSpecialCutAmt + lOtherBenefits - lRecoveryAmt + lArrearAmt; 
						
						inputMap.put("PenRqstId", lStrPenRqstId);
	    				inputMap.put("PenCode", lStrPnsnrCode);
				        inputMap.put("ArrearType", lStrArrPension);
				        inputMap.put("EffFrom", lStrDate);
				        inputMap.put("EffTo", lStrDate);
				        inputMap.put("DiffAmt", Math.round(lTmpNetAmt));
				        
				        lObjArrearVO = new TrnPensionArrearDtls();
	    				lObjArrearVO = insertArrearDtls(inputMap);
	    				lLstInsertArrears.add(lObjArrearVO);
					}
				}
			}
			
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//add condition to check calculation type.....if manual i.e. M then the arrears should not be computed for the previous month
			//if lLastPaidMonth != lPrevMonth then call recursive function for last month
		
		 	if(!"M".equals(lObjTrnPensionRqstHdrVO.getCalcType()))
			{
				if(lLastPaidMonth != lPrevMonth && lLastPaidMonth < lPrevMonth && lLastPaidMonth != 0)
				{
					MonthlyPensionBillVO lObjPending = new MonthlyPensionBillVO();
					
					//monthly pension pending...so compute that
					//if not paid for any month....call recursive function and get net pension in arrears for this month
					inputMap.put("ForMonth", lPrevMonth % 100);
					inputMap.put("ForYear", lPrevMonth / 100);
					inputMap.put("Date", lPrevMonth);
					inputMap.put("FPFlag", "N");
					inputMap.put("ArrComputeFlag", "Y");
					Map lMapTemp = computeMonthlyPension(inputMap, lStrPnsnrCode);
					List<MonthlyPensionBillVO> lLstPendingPensionBillVO = (List<MonthlyPensionBillVO>) lMapTemp.get("lLstMonthlyPensionBillVO");
			        List<TrnPensionArrearDtls> lLstPendingArrearVO = (List<TrnPensionArrearDtls>) lMapTemp.get("lLstInsertArrears");
					
			        if(lLstPendingArrearVO != null && lLstPendingArrearVO.size() > 0)
			        {
			        	for(int m = 0; m < lLstPendingArrearVO.size(); m++)
			        	{
			        		lLstInsertArrears.add(lLstPendingArrearVO.get(m));
			        	}
					}
			        
			        if(lLstPendingPensionBillVO != null && lLstPendingPensionBillVO.size() > 0)
			        {
			        	for(int m = 0; m < lLstPendingPensionBillVO.size(); m++)
			        	{
			        		lObjPending = lLstPendingPensionBillVO.get(0);
			    			lPendingArrear = lPendingArrear + Double.parseDouble(lObjPending.getNetPensionAmount().toString());
			        	}
					}
		   		}
				inputMap.put("FPFlag", "Y");
				inputMap.put("Date", lStrDate);
		 	}
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			Long pending = Math.round(lPendingArrear);
			lPendingArrear = Double.parseDouble(pending.toString());
			lArrearAmt = lArrearAmt + lPendingArrear;
	
			// Net Pension Amount Calculation.
	    	lNetPensionAmt = lBasicPensionAmt - lPensionCutAmt;		//	 NetPension = Basic - PensionCut;
	    	lNetPensionAmt = lNetPensionAmt + lDPPerAmt;			// 	 NetPension = NetPension + DPAmt;
	    	lNetPensionAmt = lNetPensionAmt + lTIPerAmt;			// 	 NetPension = NetPension + TIAmt;
	    	lNetPensionAmt = lNetPensionAmt + lMAAmt;				// 	 NetPension = NetPension + Medical Allowance;
	    	lNetPensionAmt = lNetPensionAmt - lITCutAmt;			// 	 NetPension = NetPension - IT Cut;
	    	lNetPensionAmt = lNetPensionAmt - lSpecialCutAmt;		//   NetPension = NetPension - Special Cut;
	    	lNetPensionAmt = lNetPensionAmt + lOtherBenefits;		//   NetPension = NetPension + OtherBenefits;
	    	lNetPensionAmt = lNetPensionAmt + OMR;					//   NetPension = NetPension + OMR;
	    	lNetPensionAmt = lNetPensionAmt - lRecoveryAmt;			// 	 NetPension = NetPension - Recovery / Deduction;
	    	lNetPensionAmt = lNetPensionAmt - lCVPMonthlyAmt;		// 	 NetPension = NetPension - CVPMonthlyAmt ;
	    	lNetPensionAmt = lNetPensionAmt + lArrearAmt;			//   NetPension = NetPension + ArrearAmt ;
	    	lNetPensionAmt = lNetPensionAmt + lPerPension;			//   NetPension = NetPension + PersonalPension ;
	    	lNetPensionAmt = lNetPensionAmt + lIR;					//   NetPension = NetPension + IRAmt ;
			if(lStrScheme.equals(lStrMoneyOrder)){
				lMOComm = lNetPensionAmt * 0.05;
			}
			lNetPensionAmt = lNetPensionAmt - lMOComm;
			//gLogger.info("lNetPensionAmt for the month of"+lStrDate+"is:"+lNetPensionAmt);
			
			lReducedPen = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt - lCVPMonthlyAmt;
			allnAf60 = lReducedPen - allnBf56 - allnAf56;
			
	    	if("Y".equals(lStrFPFlag) && fpMemberList.size() > 0)
	    	{
	    		for(int y=0; y < (fpMemberList.size()); y+=3)
	    		{
	    			lStrPnsnerName = fpMemberList.get(y+0).toString();
	    			Double prcnt = Double.parseDouble(fpMemberList.get(y+1).toString())/100;
	    			if(fpMemberList.get(y+2) != null)
	    			{
	    				lStrAccNo = fpMemberList.get(y+2).toString();
	    			}
	    			else
	    			{
	    				lStrAccNo = "";
	    			}
	    			
		        	lObjMonthlyPensionVO = new MonthlyPensionBillVO();
		        	BigDecimal lTmpOthArr = new BigDecimal((lArrearAmt - lTIArr)*prcnt);
		        	//gLogger.info("lTmpOthArr"+lTmpOthArr);
		        	
		        	inputMap.put("MnthPpoNo", lStrPPONo);
		        	inputMap.put("MnthPensionerName", lStrPnsnerName);
		        	inputMap.put("MnthAccountNo", lStrAccNo);
		        	inputMap.put("MnthBasicPensionAmount", lBasicPensionAmt*prcnt);
		        	inputMap.put("MnthDpPercent", lDPPer.toString().length() != 0 ? lDPPer.toString() : "0");
		        	inputMap.put("MnthTiPercent", lTIPer.toString().length() != 0 ? lTIPer.toString() : "0");
		        	inputMap.put("MnthDpPercentAmount", lDPPerAmt*prcnt);
		        	inputMap.put("MnthTiPercentAmount", lTIPerAmt*prcnt);
		        	inputMap.put("MnthMedicalAllowenceAmount", lMAAmt*prcnt);
		        	inputMap.put("MnthCvpMonthlyAmount", lCVPMonthlyAmt*prcnt);
		        	inputMap.put("MnthTIArrearsAmount", lTIArr*prcnt);
		        	inputMap.put("MnthOtherArrearsAmount", lTmpOthArr);
		        	inputMap.put("MnthItCutAmount", lITCutAmt*prcnt);
		        	inputMap.put("MnthSpecialCutAmount", lSpecialCutAmt*prcnt);
		        	inputMap.put("MnthOtherBenefit", lOtherBenefits*prcnt);
		        	inputMap.put("MnthOMR", OMR*prcnt);
		        	inputMap.put("MnthPensionCutAmount", lPensionCutAmt*prcnt);
		        	inputMap.put("MnthRecoveryAmount", lRecoveryAmt*prcnt);
		        	long lLngAmount = Math.round(lNetPensionAmt*prcnt);
		        	inputMap.put("MnthNetPensionAmount", lLngAmount);
		        	inputMap.put("MnthAllnBf56", allnBf56*prcnt);
		        	inputMap.put("MnthAllnAf56", allnAf56*prcnt);
		        	inputMap.put("MnthAllnAf60", allnAf60*prcnt);
		        	inputMap.put("MnthPersonalPension", lPerPension*prcnt);
		        	inputMap.put("MnthIRAmount", lIR*prcnt);
		        	inputMap.put("MnthMOComm", lMOComm*prcnt);
		        	
		        	lObjMonthlyPensionVO = insertMonthlyDtls(inputMap);
		        	lLstMonthlyPensionBillVO.add(lObjMonthlyPensionVO);
	    		}
	    	}
	    	else
	    	{
	    		lObjMonthlyPensionVO = new MonthlyPensionBillVO();
	        	BigDecimal lTmpOthArr = new BigDecimal(lArrearAmt - lTIArr);
	        	//gLogger.info("lTmpOthArr"+lTmpOthArr);
	    		
	        	inputMap.put("MnthPpoNo", lStrPPONo);
	        	inputMap.put("MnthPensionerName", lStrPnsnerName);
	        	inputMap.put("MnthAccountNo", lStrAccNo);
	        	inputMap.put("MnthBasicPensionAmount", lBasicPensionAmt);
	        	inputMap.put("MnthDpPercent", lDPPer.toString().length() != 0 ? lDPPer.toString() : "0");
	        	inputMap.put("MnthTiPercent", lTIPer.toString().length() != 0 ? lTIPer.toString() : "0");
	        	inputMap.put("MnthDpPercentAmount", lDPPerAmt);
	        	inputMap.put("MnthTiPercentAmount", lTIPerAmt);
	        	inputMap.put("MnthMedicalAllowenceAmount", lMAAmt);
	        	inputMap.put("MnthCvpMonthlyAmount", lCVPMonthlyAmt);
	        	inputMap.put("MnthTIArrearsAmount", lTIArr);
	        	inputMap.put("MnthOtherArrearsAmount", lTmpOthArr);
	        	inputMap.put("MnthItCutAmount", lITCutAmt);
	        	inputMap.put("MnthSpecialCutAmount", lSpecialCutAmt);
	        	inputMap.put("MnthOtherBenefit", lOtherBenefits);
	        	inputMap.put("MnthOMR", OMR);
	        	inputMap.put("MnthPensionCutAmount", lPensionCutAmt);
	        	inputMap.put("MnthRecoveryAmount", lRecoveryAmt);
	        	long lLngAmount = Math.round(lNetPensionAmt);
	        	inputMap.put("MnthNetPensionAmount", lLngAmount);
	        	inputMap.put("MnthAllnBf56", allnBf56);
	        	inputMap.put("MnthAllnAf56", allnAf56);
	        	inputMap.put("MnthAllnAf60", allnAf60);
	        	inputMap.put("MnthPersonalPension", lPerPension);
	        	inputMap.put("MnthIRAmount", lIR);
	        	inputMap.put("MnthMOComm", lMOComm);
	        	
	        	lObjMonthlyPensionVO = insertMonthlyDtls(inputMap);
	        	lLstMonthlyPensionBillVO.add(lObjMonthlyPensionVO);
	    	}
	    	
	    	//inputMap.put("ArrComputeFlag", lArrComputeFlag);
	    	inputMap.put("lLstMonthlyPensionBillVO", lLstMonthlyPensionBillVO);
      	  	inputMap.put("lLstInsertArrears", lLstInsertArrears);
		}
    	catch(Exception e){
			gLogger.error(" Error is : " + e, e);
			throw(e);
		}
    	return inputMap;
	}
	
	private List getDividedBasic(Double OrigBasicAmt, Long dpPer) throws Exception
	{
		List arrData = new ArrayList();
		Double lFinalBasic = 0D;
		Double lDPAmt = 0D;
		try{
			lFinalBasic = (OrigBasicAmt * 100)/(100 + dpPer);
			lDPAmt = OrigBasicAmt - lFinalBasic;
	
			arrData.add(lFinalBasic);
			arrData.add(lDPAmt);
		}
		catch(Exception e){
			gLogger.error(" Error is : " + e, e);
			throw(e);
		}
		return arrData;
	}
	
	private List getDatePlus7yrs(Date lDateOfDeath,Date lFP1Date,Date lFP2Date) throws Exception
	{
		List arrFP = new ArrayList();
		try{
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(lDateOfDeath);
			
        	String DATE_FORMAT = "yyyy-MM-dd";
        	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        	Calendar cal = Calendar.getInstance();
        	cal.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		    
		    cal.add(Calendar.YEAR, 7);
		    cal.add(Calendar.DATE, -1);
		    
		    if(cal.before(lFP1Date)){
		    	java.sql.Date jsqlD =  new java.sql.Date(cal.getTime().getTime() );
		    	lFP1Date = jsqlD;
		    	cal.add(Calendar.DATE, 1);
		    	java.sql.Date jsqlD2 =  new java.sql.Date(cal.getTime().getTime() );
		    	lFP2Date = jsqlD2;
		    }
		    arrFP.add(lFP1Date);
		    arrFP.add(lFP2Date);
		}
		catch(Exception e){
			gLogger.error(" Error is : " + e, e);
			throw(e);
		}
		return arrFP;
	}
*/
	private List getFpMemberList(List<MstPensionerFamilyDtls> lLstFPmembers) throws Exception
	{
		List fpMemberList = new ArrayList();
		Double salary = 0D;
		try
		{
			for(int x=0; x<lLstFPmembers.size(); x++)
			{
				if(lLstFPmembers.get(x) != null)
				{
					MstPensionerFamilyDtls lObjMstPensionerFamilyDtlsVO = (MstPensionerFamilyDtls) lLstFPmembers.get(x);
					
					if(lObjMstPensionerFamilyDtlsVO.getDateOfDeath() == null && lObjMstPensionerFamilyDtlsVO.getPercentage() != 0)
					{
						if("Spouse".equals(lObjMstPensionerFamilyDtlsVO.getRelationship()) || "Mother".equals(lObjMstPensionerFamilyDtlsVO.getRelationship()) || "Father".equals(lObjMstPensionerFamilyDtlsVO.getRelationship()))
						{
							fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getName());
							fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getPercentage());
							fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getAccntNo());
						}
						else
						{
							if("N".equals(lObjMstPensionerFamilyDtlsVO.getMarriedFlag()))
							{
								if(lObjMstPensionerFamilyDtlsVO.getSalary() != null)
								{
									salary = Double.parseDouble(lObjMstPensionerFamilyDtlsVO.getSalary().toString());
								}
								else
								{
									salary = 0.00;
								}
								
								if(salary <= 2550)
								{
									if("N".equals(lObjMstPensionerFamilyDtlsVO.getMajorFlag()))
									{
										if(lObjMstPensionerFamilyDtlsVO.getGuardianName() != null)
										{
											fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getGuardianName());
										}
										else
										{
											fpMemberList.add(" ");
										}
	    	    						fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getPercentage());
	    	    						fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getAccntNo());
									}
									else
									{
										if("M".equals(lObjMstPensionerFamilyDtlsVO.getHandicapeFlag()))
										{
											if(lObjMstPensionerFamilyDtlsVO.getGuardianName() != null)
											{
												fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getGuardianName());
											}
											else
											{
												fpMemberList.add(" ");
											}
										}
										else
										{
											fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getName());
										}
	    	    						fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getPercentage());
	    	    						fpMemberList.add(lObjMstPensionerFamilyDtlsVO.getAccntNo());
									}
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e){
            gLogger.info("Error is :: " + e,e);
            throw(e);
	    }        
	       return  fpMemberList;
	}
	
	private MonthlyPensionBillVO insertMonthlyDtls(Map inputMap) throws Exception
    {
       MonthlyPensionBillVO lObjMonthlyPensionVO = new MonthlyPensionBillVO();
      
        try{
        	lObjMonthlyPensionVO = new MonthlyPensionBillVO();
        	
			lObjMonthlyPensionVO.setPpoNo(inputMap.get("MnthPpoNo").toString());
        	lObjMonthlyPensionVO.setPensionerName(inputMap.get("MnthPensionerName").toString());
        	lObjMonthlyPensionVO.setAccountNo(inputMap.get("MnthAccountNo").toString());
        	lObjMonthlyPensionVO.setBasicPensionAmount(new BigDecimal(inputMap.get("MnthBasicPensionAmount").toString()));
        	lObjMonthlyPensionVO.setDpPercent(new BigDecimal(inputMap.get("MnthDpPercent").toString()));
        	lObjMonthlyPensionVO.setTiPercent(new BigDecimal(inputMap.get("MnthTiPercent").toString()));
        	lObjMonthlyPensionVO.setDpPercentAmount(new BigDecimal(inputMap.get("MnthDpPercentAmount").toString()));
        	lObjMonthlyPensionVO.setTiPercentAmount(new BigDecimal(inputMap.get("MnthTiPercentAmount").toString()));
        	lObjMonthlyPensionVO.setMedicalAllowenceAmount(new BigDecimal(inputMap.get("MnthMedicalAllowenceAmount").toString()));
        	lObjMonthlyPensionVO.setCvpMonthlyAmount(new BigDecimal(inputMap.get("MnthCvpMonthlyAmount").toString()));
        	lObjMonthlyPensionVO.setTIArrearsAmount(new BigDecimal(inputMap.get("MnthTIArrearsAmount").toString()));
        	lObjMonthlyPensionVO.setOtherArrearsAmount(new BigDecimal(inputMap.get("MnthOtherArrearsAmount").toString()));
        	lObjMonthlyPensionVO.setItCutAmount(new BigDecimal(inputMap.get("MnthItCutAmount").toString()));
        	lObjMonthlyPensionVO.setSpecialCutAmount(new BigDecimal(inputMap.get("MnthSpecialCutAmount").toString()));
        	lObjMonthlyPensionVO.setOtherBenefit(new BigDecimal(inputMap.get("MnthOtherBenefit").toString()));
        	lObjMonthlyPensionVO.setOMR(new BigDecimal(inputMap.get("MnthOMR").toString()));
        	lObjMonthlyPensionVO.setPensionCutAmount(new BigDecimal(inputMap.get("MnthPensionCutAmount").toString()));
        	lObjMonthlyPensionVO.setRecoveryAmount(new BigDecimal(inputMap.get("MnthRecoveryAmount").toString()));
        	lObjMonthlyPensionVO.setNetPensionAmount(new BigDecimal(inputMap.get("MnthNetPensionAmount").toString()));
        	lObjMonthlyPensionVO.setAllnBf11156(new BigDecimal(inputMap.get("MnthAllnBf56").toString()));
        	lObjMonthlyPensionVO.setAllnAf11156(new BigDecimal(inputMap.get("MnthAllnAf56").toString()));
        	lObjMonthlyPensionVO.setAllnAf10560(new BigDecimal(inputMap.get("MnthAllnAf60").toString()));
        	lObjMonthlyPensionVO.setPersonalPension(new BigDecimal(inputMap.get("MnthPersonalPension").toString()));
        	lObjMonthlyPensionVO.setIr(new BigDecimal(inputMap.get("MnthIRAmount").toString()));
        	lObjMonthlyPensionVO.setMOComm(new BigDecimal(inputMap.get("MnthMOComm").toString()));
        }
        catch (Exception e){
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }        
        
        return lObjMonthlyPensionVO;
    }
	
	private TrnPensionArrearDtls insertArrearDtls(Map inputMap) throws Exception
    {
       TrnPensionArrearDtls lObjArrearDtlsVo = new TrnPensionArrearDtls();
      
        try{
			lObjArrearDtlsVo.setPensionRequestId((Long)inputMap.get("PenRqstId"));
			lObjArrearDtlsVo.setPensionerCode(inputMap.get("PenCode").toString());
            lObjArrearDtlsVo.setArrearFieldType(inputMap.get("ArrearType").toString());
            lObjArrearDtlsVo.setEffectedFromYyyymm(Integer.parseInt(inputMap.get("EffFrom").toString()));
            lObjArrearDtlsVo.setEffectedToYyyymm(Integer.parseInt(inputMap.get("EffTo").toString()));
            lObjArrearDtlsVo.setDifferenceAmount(new BigDecimal(inputMap.get("DiffAmt").toString()));
        }
        catch (Exception e){
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }        
        return lObjArrearDtlsVo;
    }
	
	 /**
     * Method to set Session variables
     * @param inputMap
     */
    private void setSessionInfo(Map inputMap)
    {
    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
    	
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gDate = DBUtility.getCurrentDateFromDB();
    }
}
