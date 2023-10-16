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
import com.tcs.sgv.pension.dao.NewPensionBillDAO;
import com.tcs.sgv.pension.dao.NewPensionBillDAOImpl;
import com.tcs.sgv.pension.valueobject.MonthlyPensionBillVO;
import com.tcs.sgv.pension.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeRate;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeSpecial;
import com.tcs.sgv.pension.valueobject.RltPensionRevisedPayment;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class PensionBillProcessServiceImpl extends ServiceImpl{
	
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
        
        List<TrnPensionArrearDtls> lTrnPaymentLst = null;
        
        MonthlyPensionBillVO lRatePensionBillVO = null;
        
        String lStrMoneyOrder = "MONEY ORDER";
        String lStrArrTI = bundleConst.getString("ARREAR.TI");
        String lStrArrPension = bundleConst.getString("ARREAR.PENSION");
        String lStrArrFP1 = bundleConst.getString("ARREAR.FP1");
        String lStrArrFP2 = bundleConst.getString("ARREAR.FP2");
        String lStrArrOMR = bundleConst.getString("ARREAR.OMR");
        String lStrCutPP = bundleConst.getString("CUT.PP");
        String lStrCutPT = bundleConst.getString("CUT.PT");
        String lStrCutIT = bundleConst.getString("CUT.IT");
        String lStrCutST = bundleConst.getString("CUT.ST");
        String lStrCutPerBen = bundleConst.getString("CMN.PBENEFIT");
        String lStrCutTmpBen = bundleConst.getString("CMN.TBENEFIT");        
        String lStrVoluntary = bundleConst.getString("CMN.VOLUNTARY");
		String lStrFamilyLost = bundleConst.getString("CMN.FAMILYLOST");
		String lStrFamily = bundleConst.getString("CMN.FAMILY");		
		
		List lLstAccNo = null;
        String lStrBank = null;
        String lStrBranch = null;
        String lStrHeadCode = null;
        String lStrDate = null;
        String lDateOfDeathYYYYMM = null;
        String lDayOfDeathDD = null;
        String lStrRcvryFlag =  null;
        
        String lStrScheme = null;
        String lStrPnsnrCode = null;
        String lStrPnsnerName = null;
        String lStrPPONo = null;
        String lStrAccNo = "";
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
        Date lPPODate = null;
        Date lFP1Date = null;
        Date lFP2Date = null;        
		Date lEndDate = null;
		String endDays = null;
		Integer EDays = 0;
        Double OMR = 0D;
        Double lDP = 0D;
        Double lTI = 0D;
        Double lPendingArrear = 0D;
        List lItCutDtls = new ArrayList();
        List lArrearDtls = new ArrayList();
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
        Double lTIArr = 0D;
        Double lOtherArr = 0D;
        Double lReducedPen = 0D;
        Double lCutATI = 0D;
        
        String lStrBillType = null;
        Integer lDODyyyyMM = 0;
        
        String lStrPayTI = null;
    	String lStrPaySTI = null;
    	String lStrPayDP = null;
    	String lStrPayMA = null;
    	String lStrPayIR = null;
        
        MonthlyPensionBillDAO lObjMnthlyBillDAO = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
    	MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
    	
		try{
			setSessionInfo(inputMap);
			lStrBillType = inputMap.get("BillType").toString();
        				
			if(lStrBillType.equalsIgnoreCase("Monthly"))
			{
				lStrRcvryFlag = bundleConst.getString("RECOVERY.MONTHLY"); 
				lStrBank = inputMap.get("Bank").toString();
	        	lStrBranch = inputMap.get("Branch").toString();
	        	lStrHeadCode = inputMap.get("HeadCode").toString();
	        	lStrScheme = inputMap.get("Scheme").toString();	        		        	
			}
			
			lArrComputeFlag = inputMap.get("ArrComputeFlag").toString();
        	        	 
        	lStrDate = inputMap.get("Date").toString();
        	
			lObjTrnPensionRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
			lPendingArrear = Double.parseDouble(inputMap.get("lPendingArrear").toString());
						
			lStrPnsnrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
			lStrPenRqstId = lObjTrnPensionRqstHdrVO.getPensionRequestId();
			
			inputMap.put("PenRqstId", lStrPenRqstId);
			inputMap.put("PenCode", lStrPnsnrCode);
						
			lEndDate = lObjTrnPensionRqstHdrVO.getEndDate();
			if(lEndDate != null){
    			endDate = new SimpleDateFormat("yyyyMM").format(lEndDate);
        		endDays = new SimpleDateFormat("dd").format(lEndDate);
				EDays = Integer.valueOf(endDays);
    		}
			else{
				endDate = "000000";
			}
			//check the last month for which monthly pension or pension paid....
			//if 0 implies no bill generated for this pensioner...not even pension bill....
			//hence should not compute monthly bill for tht pensioner
    		//lLastPaidMonth = lObjMnthlyBillDAO.getLastMonth(lStrPnsnrCode, lStrScheme);
    		lIntDate = Integer.parseInt(lStrDate);
    		inputMap.put("forMonth", lIntDate);
    		
    		/*if((lIntDate%100) == 01){
    			lPrevMonth = lIntDate - 89;
    		}
    		else{
    			lPrevMonth = lIntDate - 1;
    		}*/
			
			//gLogger.info("computing monthly pension for the month of "+lStrDate);
			//pension for current month needs to be computed
			lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsnrCode);
			
			if(lStrBillType.equalsIgnoreCase("Monthly"))
			{
				lLstAccNo = lObjMnthlyBillDAO.getAccountNo(lStrPnsnrCode, lStrBank, lStrBranch);
				
				if(lLstAccNo.size() > 0  && lLstAccNo.get(0) != null)
				{
					lStrAccNo = lLstAccNo.get(0).toString();
				}
				else
				{
					lStrAccNo = "";
				}
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
			/*if(lObjTrnPensionRqstHdrVO.getDpPercent() != null){
				lDPPer = Long.parseLong(lObjTrnPensionRqstHdrVO.getDpPercent().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getTiPercent() != null){
				lTIPer = Long.parseLong(lObjTrnPensionRqstHdrVO.getTiPercent().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getMedicalAllowenceAmount() != null){
				lMAAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getMedicalAllowenceAmount().toString());
			}*/
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
			/*if(lObjTrnPensionRqstHdrVO.getRedAf10560() != null){
				allnAf60 = lObjTrnPensionRqstHdrVO.getRedAf10560();
			}*/
			if(lObjTrnPensionRqstHdrVO.getPersonalPension() != null){
				lPerPension = Double.parseDouble(lObjTrnPensionRqstHdrVO.getPersonalPension().toString());
			}
			if(lObjTrnPensionRqstHdrVO.getCvpMonthlyAmount() != null)
			{
				lCVPMonthlyAmt = Double.valueOf(lObjTrnPensionRqstHdrVO.getCvpMonthlyAmount().toString());
			}
			
			if(lObjTrnPensionRqstHdrVO.getCvpDate()== null)
			{			
				lPPODate = lObjTrnPensionRqstHdrVO.getPpoDate();
				
				if(lPPODate != null)
				{
					// Logic Added For CVP Monthly.
					Integer lPPODtYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lPPODate));
					if(lPPODtYYYYMM != null && lPPODtYYYYMM != 0 && (lPPODtYYYYMM % 100) >= 10 && (lPPODtYYYYMM % 100) <= 12)
					{
						lPPODtYYYYMM = lPPODtYYYYMM + 91;
					}
					else
					{
						lPPODtYYYYMM = lPPODtYYYYMM + 3;
					}
					
					if(lPPODtYYYYMM != null && lPPODtYYYYMM != 0 && lIntDate < lPPODtYYYYMM)
					{
						lCVPMonthlyAmt = 0D;
					}
				}
			}
			
			/*if(lObjTrnPensionRqstHdrVO.getIr() != null){
				lIR = Double.parseDouble(lObjTrnPensionRqstHdrVO.getIr().toString());
			}*/
			lFP1Date = lObjTrnPensionRqstHdrVO.getFp1Date();
			lFP2Date = lObjTrnPensionRqstHdrVO.getFp2Date();

			
			if(lStrBillType.equalsIgnoreCase("Monthly"))
			{
				inputMap.put("SchemeType", lObjTrnPensionRqstHdrVO.getSchemeType());
		        List lLstParameters = new ArrayList();
		        lLstParameters.add("locName");
		        lLstParameters.add("CmnLocationMst");
		        lLstParameters.add("locId");
		        lLstParameters.add(lObjTrnPensionRqstHdrVO.getLocationCode());
		        lLstParameters.add("-1");
		        List lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
		        if(lLstRes != null && lLstRes.size()>0)
		        {
		            lStrTreasuryName = lLstRes.get(0).toString();
		        }
		        inputMap.put("TreasuryName", lStrTreasuryName);
			}
			// Getting the Value from TrnPensionRqstHdr... End...
			
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
				
				 // Check for Basic Pension / FP1 Pension / FP2 Pension... Start....
				lDateOfDeath = lObjMstPensionerHdrVO.getDateOfDeath();
	        	
				if(lDateOfDeath != null && lDateOfDeath.toString().length() > 0)
				{
					//	compute lDateOfDeathYYYYMM
					lDateOfDeathYYYYMM = new SimpleDateFormat("yyyyMM").format(lDateOfDeath);
					lDayOfDeathDD = new SimpleDateFormat("dd").format(lDateOfDeath);
					lDODyyyyMM = Integer.parseInt(lDateOfDeathYYYYMM);
				}
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
						lTIArr += Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					else
					{
						lOtherArr += Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
				}
				lCutATI = lTIArr;				
				//lArrearAmt = lTIArr + lOtherArr;
			}
			
			// Flages Set For maintain a List for transaction Payment.
			inputMap.put("CurntPayTI", lStrPayTI);
			inputMap.put("CurntPaySTI", lStrPaySTI);
			inputMap.put("CurntPayDP", lStrPayDP);
			inputMap.put("CurntPayMA", lStrPayMA);
			inputMap.put("CurntPayIR", lStrPayIR);
			
//			start...
			inputMap.put("PensionCutAmt", lPensionCutAmt);
			
//			 0 Call For Master Rates
			
			inputMap.put("OldBasicAmt", lBasicPensionAmt);
			inputMap.put("BasicAmtType", "Basic"); // For Identify Pension Type in getNewROPBasicAmt is (BASIC / FP1 / FP2) 
			inputMap.put("IsAlive","Y");
			lRatePensionBillVO = getMasterRatesForMonth(inputMap);
			
			lBasicPensionAmt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
			//lDPPer = Long.parseLong(lRatePensionBillVO.getDpPercent().toString());
			lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
			lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
			lMAAmt = Double.parseDouble(lRatePensionBillVO.getMedicalAllowenceAmount().toString());
			lIR = Double.parseDouble(lRatePensionBillVO.getIr().toString());
			lTIArr = lTIArr + Double.parseDouble(lRatePensionBillVO.getTIArrearsAmount().toString());
			
			//lDPPerAmt = (lBasicPensionAmt - lPensionCutAmt) * lDPPer / 100;
			//lTIPerAmt = (lBasicPensionAmt - lPensionCutAmt + lDPPerAmt) * lTIPer / 100; 
			
	        // All values for normal pension are available
			// now compute for other cases
			
			// compute no of days in this month
			lDays = getDaysOfMonth(lIntDate);
			
			if( lDateOfDeath != null && lDateOfDeath.toString().length() > 0 
					&& lDODyyyyMM.intValue() <= lIntDate.intValue() )
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
				
				//check if date of death in month for which bill generated 
				if(lDateOfDeathYYYYMM.equals(lStrDate))
				{
					//pensioner died in current month
					
					if(lStrBillType != null && lStrBillType.equalsIgnoreCase("Monthly"))
					{
						//	OMR calculation
						
						// 1 Call For Master Rates
						
						inputMap.put("OldBasicAmt", lBasicPensionAmt);
						inputMap.put("BasicAmtType", "Basic");
						inputMap.put("IsAlive","N");
						lRatePensionBillVO = getMasterRatesForMonth(inputMap);
						
						Double lTBasicAmt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
						lDP = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
						lTI = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
						
						//lDP = ((lBasicPensionAmt * lDPPer) / 100 );
						//lTI = (((lBasicPensionAmt + lDP) * lTIPer) / 100 );
						
						OMR = lTBasicAmt + lDP + lTI;
						
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
					}
					else if(lStrBillType != null && lStrBillType.equalsIgnoreCase("PENSION") && 
							lStrPenType != null && !(lStrPenType.equals(lStrFamily) || lStrPenType.equals(lStrFamilyLost)))
					{
						//	OMR calculation
						
						// 1 Call For Master Rates
						
						inputMap.put("OldBasicAmt", lBasicPensionAmt);
						inputMap.put("BasicAmtType", "Basic");
						inputMap.put("IsAlive","N");
						lRatePensionBillVO = getMasterRatesForMonth(inputMap);
						
						Double lTBasicAmt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
						lDP = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
						lTI = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
						
						//lDP = ((lBasicPensionAmt * lDPPer) / 100 );
						//lTI = (((lBasicPensionAmt + lDP) * lTIPer) / 100 );
						
						OMR = lTBasicAmt + lDP + lTI;
						
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
					}
					
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
							
							/*//	2 Call For Master Rates
							
							inputMap.put("OldBasicAmt", lBasicPensionAmt);
							lRatePensionBillVO = getMasterRatesForMonth(inputMap);
							
							lBasicPensionAmt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
							lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
							lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
							lMAAmt = Double.parseDouble(lRatePensionBillVO.getMedicalAllowenceAmount().toString());
							lIR = Double.parseDouble(lRatePensionBillVO.getIr().toString());
							lTIArr = Double.parseDouble(lRatePensionBillVO.getTIArrearsAmount().toString());	
							*/
							
							lBasicPensionAmt = lBasicPensionAmt * EDays / lDays;
		    				lPensionCutAmt = lPensionCutAmt * EDays / lDays;
		    				lCVPMonthlyAmt = lCVPMonthlyAmt * EDays / lDays;
		    				lMAAmt = lMAAmt * EDays / lDays;
		    				lITCutAmt = lITCutAmt * EDays / lDays;
		    				lSpecialCutAmt = lSpecialCutAmt * EDays / lDays;
		    				lOtherBenefits = lOtherBenefits * EDays / lDays;
		    				lPerPension = lPerPension * EDays / lDays;
		    				lIR = lIR * EDays / lDays;
		    				lDPPerAmt = lDPPerAmt * EDays / lDays;
		    				lTIPerAmt = lTIPerAmt * EDays / lDays; 
		    						                	    				
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt + lTIPerAmt +lPerPension + lIR
		    				     - lCVPMonthlyAmt + lMAAmt - lITCutAmt - lSpecialCutAmt + lOtherBenefits 
		    				     - lRecoveryAmt + lArrearAmt - lCutATI; 
		    					
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
							
//							 3 Call For Master Rates							
							
							/*inputMap.put("OldBasicAmt", lBasicPensionAmt);
							lRatePensionBillVO = getMasterRatesForMonth(inputMap);
							
							lBasicPensionAmt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
							lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
							lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
							lMAAmt = Double.parseDouble(lRatePensionBillVO.getMedicalAllowenceAmount().toString());
							lIR = Double.parseDouble(lRatePensionBillVO.getIr().toString());
							lTIArr = Double.parseDouble(lRatePensionBillVO.getTIArrearsAmount().toString());	
							*/
							lBasicPensionAmt = lBasicPensionAmt * date / lDays;
		    				lPensionCutAmt = lPensionCutAmt * date / lDays;
		    				lCVPMonthlyAmt = lCVPMonthlyAmt * date / lDays;
		    				lITCutAmt = lITCutAmt * date / lDays;
		    				lDPPerAmt = lDPPerAmt * date / lDays;
		    				lTIPerAmt = lTIPerAmt * date / lDays; 
		    				
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt + lTIPerAmt 
		    				     - lCVPMonthlyAmt + ((lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits) * date / lDays) - lITCutAmt  
		    				     - lRecoveryAmt + lArrearAmt - lCutATI; 
		    					
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

//		    						 4 Call For Master Rates
		    						
		    						inputMap.put("OldBasicAmt", lFP1Amt);
		    						inputMap.put("BasicAmtType", "FP1");
		    						inputMap.put("IsAlive","N");
		    						lRatePensionBillVO = getMasterRatesForMonth(inputMap);
		    						
		    						lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());		    						
		    						
		    						//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
		    						lBasicPensionAmt = lBasicPensionAmt + (lFP1Amt * (EDays - date) / lDays);
	    	        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString()) * (EDays - date) / lDays);
		    						lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString()) * (EDays - date) / lDays);	
	    	        				if("Y".equals(lArrComputeFlag))
	        	    				{
	    	        					lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP1Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())	//basic + dp amt
	        	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())		//lTIPerAmt 
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

//		    						 5 Call For Master Rates
		    						
		    						inputMap.put("OldBasicAmt", lFP1Amt);
		    						inputMap.put("BasicAmtType", "FP1");
		    						inputMap.put("IsAlive","N");
		    						lRatePensionBillVO = getMasterRatesForMonth(inputMap);
		    						
		    						lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
		    						
		    						//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
		    						lBasicPensionAmt = lBasicPensionAmt + (lFP1Amt * (date1 - date) / lDays);
		    						lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (date1 - date) / lDays;
	        	        			lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())) * (date1 - date) / lDays;	
	        	        			if("Y".equals(lArrComputeFlag))
	        	    				{
	        	    					lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP1Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())	//basic + DP
	        	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())	//lTIPerAmt 
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
	        	        			
//	        	        			 6 Call For Master Rates
	        	        			
	        	        			inputMap.put("OldBasicAmt", lFP2Amt);
	        	        			inputMap.put("BasicAmtType", "FP2");
	        	        			inputMap.put("IsAlive","N");
	        	        			lRatePensionBillVO = getMasterRatesForMonth(inputMap);
	        	        			
	        	        			lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
	        	        			
	        	        			//List divAmt1 = getDividedBasic(lFP2Amt, lDPPer);
	        	        			lBasicPensionAmt = lBasicPensionAmt + (lFP2Amt) * (EDays - date1) / lDays;
	        	        			lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (EDays - date1) / lDays;
	        	        			lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())) * (EDays - date1) / lDays;	
	        	        			if("Y".equals(lArrComputeFlag))
	        	    				{
	        	        				lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP2Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString()) //basic + DP 
	        	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())	//lTIPerAmt 
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
	    	    					
//	    	    					 7 Call For Master Rates
	    	    					
	    	    					inputMap.put("OldBasicAmt", lFP1Amt);
	    	    					inputMap.put("BasicAmtType", "FP1");
	    	    					inputMap.put("IsAlive","N");
	    	    					lRatePensionBillVO = getMasterRatesForMonth(inputMap);
	    	    					
	    	    					lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
	    	    					
	    	    					//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
	    	        				lBasicPensionAmt = lBasicPensionAmt + (lFP1Amt * (EDays - date) / lDays);
	    	        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (EDays - date) / lDays;
	    	        				lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())) * (EDays - date) / lDays;	
	    	        				if("Y".equals(lArrComputeFlag))
	        	    				{
	    	        					lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP1Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString()) //basic + DP
	        	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())	//lTIPerAmt 
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
	    	        				
//	    	        				 8 Call For Master Rates
	    	        				
	    	        				inputMap.put("OldBasicAmt", lFP2Amt);
	    	        				inputMap.put("BasicAmtType", "FP2");
	    	        				inputMap.put("IsAlive","N");
	    	        				lRatePensionBillVO = getMasterRatesForMonth(inputMap);
	    	        				
	    	        				lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
	    	        				
	    	        				//List divAmt = getDividedBasic(lFP2Amt, lDPPer);
	    	        				lBasicPensionAmt = lBasicPensionAmt + (lFP2Amt * (EDays - date) / lDays);
	    	        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (EDays - date) / lDays;
	    	        				lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString()))  * (EDays - date) / lDays;	
	    	        				if("Y".equals(lArrComputeFlag))
	        	    				{
	    	        					lTmpNetAmt = 0D;
	        	    					lTmpNetAmt = (lFP2Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())	//basic + DP
	        	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())		//lTIPerAmt 
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
						
//						 9 Call For Master Rates
						
						/*inputMap.put("OldBasicAmt", lBasicPensionAmt);
						inputMap.put("IsAlive","Y");
						lRatePensionBillVO = getMasterRatesForMonth(inputMap);
						*/						
	    				
						lBasicPensionAmt = lBasicPensionAmt * date / lDays;
	    				lPensionCutAmt = lPensionCutAmt * date / lDays;
	    				lCVPMonthlyAmt = lCVPMonthlyAmt * date / lDays;
	    				lITCutAmt = lITCutAmt * date / lDays;
	    				lDPPerAmt = lDPPerAmt * date / lDays;
	    				lTIPerAmt = lTIPerAmt * date / lDays; 
	    				
	    				if("Y".equals(lArrComputeFlag))
	    				{
	    					lTmpNetAmt = 0D;
	    					lTmpNetAmt = lBasicPensionAmt - lPensionCutAmt + lDPPerAmt + lTIPerAmt 
	    				     			 - lCVPMonthlyAmt + ((lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)* date / lDays) - lITCutAmt
	    				     			 - lRecoveryAmt + lArrearAmt - lCutATI; 
	    					
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
	    						
//	    						 10 Call For Master Rates
	    						
	    						inputMap.put("OldBasicAmt", lFP2Amt);
	    						inputMap.put("BasicAmtType", "FP2");
	    						inputMap.put("IsAlive","N");
	    						lRatePensionBillVO = getMasterRatesForMonth(inputMap);
	    						
	    						lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
	    						
	    						//List divAmt = getDividedBasic(lFP2Amt, lDPPer);
	    						lBasicPensionAmt = lBasicPensionAmt + (lFP2Amt * (lDays - date) / lDays);
	    						lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (lDays - date) / lDays;
	    						lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (lDays - date) / lDays;	
		        				if("Y".equals(lArrComputeFlag))
	    	    				{
		        					lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP2Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())	//basic + DP
	    	    								  + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())	//lTIPerAmt 
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
	    						
//	    						11 Call For Master Rates	    						
	    						
	    						inputMap.put("OldBasicAmt", lFP1Amt);
	    						inputMap.put("BasicAmtType", "FP1");
	    						inputMap.put("IsAlive","N");
	    						lRatePensionBillVO = getMasterRatesForMonth(inputMap);
	    						
	    						lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
	    						
	    						//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
	    						lBasicPensionAmt = lBasicPensionAmt + (lFP1Amt * (date1 - date) / lDays);
	    						lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (date1 - date) / lDays;
	    	        			lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())) * (date1 - date) / lDays;	
	    	        			if("Y".equals(lArrComputeFlag))
	    	    				{
	    	        				lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP1Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())	//basic + DP
	    	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())		//lTIPerAmt 
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
	    	        			
//	    	        			12  Call For Master Rates
	    	        			
	    	        			inputMap.put("OldBasicAmt", lFP2Amt);
	    	        			inputMap.put("BasicAmtType", "FP2");
	    	        			inputMap.put("IsAlive","N");
	    	        			lRatePensionBillVO = getMasterRatesForMonth(inputMap);
	    	        			
	    	        			lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
	    	        			
	    	        			//List divAmt1 = getDividedBasic(lFP2Amt, lDPPer);
	    	        			lBasicPensionAmt = lBasicPensionAmt + (lFP2Amt * (lDays - date1) / lDays);
	    	        			lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (lDays - date1) / lDays;
	    	        			lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())) * (lDays - date1) / lDays;	
	    	        			if("Y".equals(lArrComputeFlag))
	    	    				{
	    	        				lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP2Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())	//basic + DP
	    	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())		//lTIPerAmt 
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
		    					
//		    					13 Call For Master Rates
		    					
		    					inputMap.put("OldBasicAmt", lFP1Amt);
		    					inputMap.put("BasicAmtType", "FP1");
		    					inputMap.put("IsAlive","N");
		    					lRatePensionBillVO = getMasterRatesForMonth(inputMap);
		    					
		    					lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
		    					
		    					//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
		        				lBasicPensionAmt = lBasicPensionAmt + (lFP1Amt * (lDays - date) / lDays);
		        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (lDays - date) / lDays;
		        				lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())) * (lDays - date) / lDays;	
		        				if("Y".equals(lArrComputeFlag))
	    	    				{
		        					lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP1Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())	//basic + DP
	    	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())		//lTIPerAmt 
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
		        				
//		        				14 Call For Master Rates
		        				
		        				inputMap.put("OldBasicAmt", lFP2Amt);
		        				inputMap.put("BasicAmtType", "FP2");
		        				inputMap.put("IsAlive","N");
		        				lRatePensionBillVO = getMasterRatesForMonth(inputMap);
		        				
		        				lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
		        				
		        				//List divAmt = getDividedBasic(lFP2Amt, lDPPer);
		        				lBasicPensionAmt = lBasicPensionAmt + (lFP2Amt * (lDays - date) / lDays);
		        				lDPPerAmt = lDPPerAmt + (Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())) * (lDays - date) / lDays;
		        				lTIPerAmt = lTIPerAmt + (Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())) * (lDays - date) / lDays;	
		        				if("Y".equals(lArrComputeFlag))
	    	    				{
		        					lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lFP2Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString()) //basic + DP
	    	    								  + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())		//lTIPerAmt 
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
							
//							15 Call For Master Rates
							
							inputMap.put("OldBasicAmt", lFP1Amt);
							inputMap.put("BasicAmtType", "FP1");
							inputMap.put("IsAlive","N");
							lRatePensionBillVO = getMasterRatesForMonth(inputMap);
							
							lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
							
							//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
							lBasicPensionAmt = lFP1Amt;
							lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
	    					lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
		    				
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
		    								- lRecoveryAmt + lArrearAmt - lCutATI; 
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
							
//							 16 Call For Master Rates
							
							inputMap.put("OldBasicAmt", lFP1Amt);
							inputMap.put("BasicAmtType", "FP1");
							inputMap.put("IsAlive","N");
							lRatePensionBillVO = getMasterRatesForMonth(inputMap);
							
							lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
							
							//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
							lBasicPensionAmt = lFP1Amt * date / lDays;
							lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString()) * date / lDays;
		    				lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString()) * date / lDays; 
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
		    				     			+ ((lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits) * date / lDays)
		    				     			- lRecoveryAmt + lArrearAmt - lCutATI; 
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
		    				
//		    				 17 Call For Master Rates
		    				
		    				inputMap.put("OldBasicAmt", lFP2Amt);
		    				inputMap.put("BasicAmtType", "FP2");
		    				inputMap.put("IsAlive","N");
		    				lRatePensionBillVO = getMasterRatesForMonth(inputMap);
		    				
		    				lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
		    				
		    				//List divAmt1 = getDividedBasic(lFP2Amt, lDPPer);
		    				lBasicPensionAmt = lBasicPensionAmt + (lFP2Amt * (EDays - date) / lDays);
		    				lDPPerAmt = lDPPerAmt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString()) * (EDays - date) / lDays;
		        			lTIPerAmt = lTIPerAmt + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString()) * (EDays - date) / lDays;	
		        			
		        			if("Y".equals(lArrComputeFlag))
		    				{
		        				lTmpNetAmt = 0D;
		    					lTmpNetAmt = (lFP2Amt+ Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())
		    									+ Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString())
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
							
//							18 Call For Master Rates
							
							inputMap.put("OldBasicAmt", lFP1Amt);
							inputMap.put("BasicAmtType", "FP1");
							inputMap.put("IsAlive","N");
							lRatePensionBillVO = getMasterRatesForMonth(inputMap);
							
							lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
							
							//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
							lBasicPensionAmt = lFP1Amt * date / lDays;
							lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString()) * date / lDays;
		    				lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString()) * date / lDays;
		    				if("Y".equals(lArrComputeFlag))
		    				{
		    					lTmpNetAmt = 0D;
		    					lTmpNetAmt = lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
		    				     			+ (lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits) * date / lDays
		    				     			- lRecoveryAmt + lArrearAmt - lCutATI; 
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
		    				
//		    				19 Call For Master Rates
		    				
		    				inputMap.put("OldBasicAmt", lFP2Amt);
		    				inputMap.put("BasicAmtType", "FP2");
		    				inputMap.put("IsAlive","N");
		    				lRatePensionBillVO = getMasterRatesForMonth(inputMap);
		    				
		    				lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
		    				
		    				//List divAmt1 = getDividedBasic(lFP2Amt, lDPPer);
		    				lBasicPensionAmt = lBasicPensionAmt + ((lFP2Amt * (lDays - date) / lDays));
		    				lDPPerAmt = lDPPerAmt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())* (lDays - date) / lDays;
		    				lTIPerAmt = lTIPerAmt + Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString()) * (lDays - date) / lDays;	
		        			if("Y".equals(lArrComputeFlag))
		    				{
		        				lTmpNetAmt = 0D;
		    					lTmpNetAmt = (lFP2Amt + Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString())
		    									+ Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString()) 
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
							
//							20 Call For Master Rates
							
							inputMap.put("OldBasicAmt", lFP1Amt);
							inputMap.put("BasicAmtType", "FP1");
							inputMap.put("IsAlive","N");
							lRatePensionBillVO = getMasterRatesForMonth(inputMap);
							
							lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
							
							//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
							lBasicPensionAmt = lFP1Amt;
							lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
							lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
							
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
		    				     			- lRecoveryAmt + lArrearAmt - lCutATI;
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
							
//							21 Call For Master Rates
							
							inputMap.put("OldBasicAmt", lFP2Amt);
							inputMap.put("BasicAmtType", "FP2");
							inputMap.put("IsAlive","N");
							lRatePensionBillVO = getMasterRatesForMonth(inputMap);
							
							lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
							
							//List divAmt = getDividedBasic(lFP2Amt, lDPPer);
							lBasicPensionAmt = lFP2Amt;
							lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
							lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
							
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
		    				     			- lRecoveryAmt + lArrearAmt - lCutATI;
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
								
//								22 Call For Master Rates
								
								inputMap.put("OldBasicAmt", lFP1Amt);
								inputMap.put("BasicAmtType", "FP1");
								inputMap.put("IsAlive","N");
								lRatePensionBillVO = getMasterRatesForMonth(inputMap);
								
								lFP1Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
								
								//List divAmt = getDividedBasic(lFP1Amt, lDPPer);
	    						lBasicPensionAmt = lFP1Amt;
	    						lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
								lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
								if("Y".equals(lArrComputeFlag))
	    	    				{
		    						lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
	    	    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)
	    	    				     			- lRecoveryAmt + lArrearAmt - lCutATI;
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
		        				
//		        				23  Call For Master Rates
		        				
								inputMap.put("OldBasicAmt", lFP2Amt);
								inputMap.put("BasicAmtType", "FP2");
								inputMap.put("IsAlive","N");
								lRatePensionBillVO = getMasterRatesForMonth(inputMap);
								
								lFP2Amt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
								
		        				//List divAmt = getDividedBasic(lFP2Amt, lDPPer);
	    						lBasicPensionAmt = lFP2Amt;
	    						lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
								lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
								if("Y".equals(lArrComputeFlag))
	    	    				{
		    						lTmpNetAmt = 0D;
	    	    					lTmpNetAmt = (lBasicPensionAmt + lDPPerAmt + lTIPerAmt 
	    	    				     			+ lPerPension + lIR + lMAAmt - lSpecialCutAmt + lOtherBenefits)
	    	    				     			- lRecoveryAmt + lArrearAmt - lCutATI;
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
					
//					 24 Call For Master Rates
					
					/*inputMap.put("OldBasicAmt", lBasicPensionAmt);
					lRatePensionBillVO = getMasterRatesForMonth(inputMap);
					
					lBasicPensionAmt = Double.parseDouble(lRatePensionBillVO.getBasicPensionAmount().toString());
					lDPPerAmt = Double.parseDouble(lRatePensionBillVO.getDpPercentAmount().toString());
					lTIPerAmt = Double.parseDouble(lRatePensionBillVO.getTiPercentAmount().toString());
					lMAAmt = Double.parseDouble(lRatePensionBillVO.getMedicalAllowenceAmount().toString());
					lIR = Double.parseDouble(lRatePensionBillVO.getIr().toString());
					lTIArr = Double.parseDouble(lRatePensionBillVO.getTIArrearsAmount().toString());
					*/
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
					     - lCVPMonthlyAmt + lPerPension + lIR + lMAAmt - lITCutAmt - lSpecialCutAmt 
					     + lOtherBenefits - lRecoveryAmt + lArrearAmt - lCutATI; 
						
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
					     - lCVPMonthlyAmt + lPerPension + lIR + lMAAmt - lITCutAmt - lSpecialCutAmt 
					     + lOtherBenefits - lRecoveryAmt + lArrearAmt - lCutATI; 
						
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

			/// All Final values Are Received.
			
			// Getting Transaction Data to be Paid in Current Month... Start
			lTrnPaymentLst = (List)inputMap.get("TrnPaymentLst");
			
			if(lTrnPaymentLst != null)
			{
				for(int t=0;t<lTrnPaymentLst.size();t++)
				{
					lObjArrearVO = lTrnPaymentLst.get(t);
					
					if(lObjArrearVO.getPaymentFromYyyymm() == lIntDate.intValue() && lObjArrearVO.getPensionerCode().equals(lStrPnsnrCode))
					{
						if(lObjArrearVO.getArrearFieldType().equalsIgnoreCase("TI"))
						{
							lTIArr += Double.valueOf(lObjArrearVO.getDifferenceAmount().toString()); 
						}
						else
						{
							lOtherArr += Double.valueOf(lObjArrearVO.getDifferenceAmount().toString()); 
						} 
					}
				}
			}
			// Getting Transaction Data to be Paid in Current Month... End
			
			lArrearAmt = lTIArr + lOtherArr;
			//for adding arrears of earlier month
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
			
	    	if("Monthly".equalsIgnoreCase(lStrBillType))
	    	{
		    	if(lStrScheme.equals(lStrMoneyOrder)){
					lMOComm = lNetPensionAmt * 0.05;
				}
				lNetPensionAmt = lNetPensionAmt - lMOComm;
				//gLogger.info("lNetPensionAmt for the month of"+lStrDate+"is:"+lNetPensionAmt);
	    	}
			
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
		        	inputMap.put("MnthYYYYMM", lIntDate);
		        	
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
	        	inputMap.put("MnthYYYYMM", lIntDate);
	        	
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
	
	private List getDividedBasic(Double OrigBasicAmt, Double dpPer) throws Exception
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
        	lObjMonthlyPensionVO.setForMonth(new Integer(inputMap.get("MnthYYYYMM").toString()));
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
     * By this Service you Getting a ROP Basic, Dp Amt, TI Amt, ATI Amt, MA Amt, IR Amt 
     * according to HeadCode and for duration . 
     * @param inputMap
     * @return {@link MonthlyPensionBillVO}
     * @throws Exception
     */
    public MonthlyPensionBillVO getMasterRatesForMonth(Map inputMap) throws Exception
    {
    	MonthlyPensionBillVO lMonthlyPensionBillVO = new MonthlyPensionBillVO();

    	ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
    	SimpleDateFormat lObjSmplDateFrm = new SimpleDateFormat("dd/MM/yyyy");
    	
    	Double lOldBasicAmt = 0D;
    	Double lNewBasicAmt = 0D;
    	Double lPensionCutAmt = 0D;
    	Double lDPAmt = 0D;
    	Double lTIAmt = 0D;
    	Double lATIAmt = 0D;
    	Double lIRAmt = 0D;
    	Double lMAAmt = 0D;
    	
    	Double lDPPer = 0D;        
        Double lTIPer = 0D;
        Double lZeroAmt = 0D;
        
        Double lOthMAArrAmt = 0D;
        Double lOthIRArrAmt = 0D;
        Double lOthDPArrAmt = 0D;
        Double lOthTIArrAmt = 0D;
        Double lOthSPArrAmt = 0D;
    	
    	String lStrIsAlive = (String) inputMap.get("IsAlive");
    	String lStrIsROPApplied = null;
    	String lStrROP_1986 = null;
    	String lStrROP_1996 = null;
    	String lStrIsSpecialCase = null;
    	String lStrROPPay1986 = null;
    	String lStrROPPay1996 = null;
    	
    	String lStrPayTI = null;
    	String lStrPaySTI = null;
    	String lStrPayDP = null;
    	String lStrPayMA = null;
    	String lStrPayIR = null;
    	
    	Long lHeadCode = 0L; 
    	
    	String ForMonth = inputMap.get("Date").toString();
    	
    	Date lForDate = null;
    	Date lPrvForDate = null;
    	String lBasicAmtType = null;

    	TrnPensionRqstHdr lObjPensionRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
    	MstPensionerHdr lObjMstPensionerHdrVO = (MstPensionerHdr) inputMap.get("MstPensionerHdrVO");
    	NewPensionBillDAO lPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
    	RltPensionHeadcodeRate lPensionHeadcodeRateVO = null;
    	RltPensionHeadcodeRate ltPensionHeadcodeRateVO = null;
    	RltPensionHeadcodeSpecial lPensionHeadcodeSpecialVO = null;
    	RltPensionRevisedPayment lObjRevisedPaymentVO = null;
    	List<RltPensionRevisedPayment> lRevisedPaymentLst = null;
    	
    	List<TrnPensionArrearDtls> lTrnPaymentLst = null;
    	TrnPensionArrearDtls lTrnPaymetVo = null;
    	
    	try 
    	{
    		// Flages get For maintain a List transaction Payment.
			lStrPayTI = (String)inputMap.get("CurntPayTI");
			lStrPaySTI = (String)inputMap.get("CurntPaySTI");
			lStrPayDP = (String)inputMap.get("CurntPayDP");
			lStrPayMA = (String)inputMap.get("CurntPayMA");
			lStrPayIR = (String)inputMap.get("CurntPayIR");
			
			lTrnPaymentLst = (List) inputMap.get("TrnPaymentLst");
    		
    		lForDate = lObjSmplDateFrm.parse("01/"+ForMonth.substring(4,6)+"/"+ForMonth.substring(0,4));
    		/*lPrvForDate = lObjSmplDateFrm.parse("01/"+ForMonth.substring(4,6)+"/"+ForMonth.substring(0,4));
    		lPrvForDate = getPrevDate(lPrvForDate);  		*/
    		lHeadCode = Long.parseLong(lObjPensionRqstHdrVO.getHeadCode().toString());
    		lStrIsROPApplied = lObjPensionRqstHdrVO.getIsRop();
    		lStrIsSpecialCase = lObjPensionRqstHdrVO.getSpecialCase();
    		
    		lStrROP_1986 = (String) inputMap.get("ROP_1986");
    		lStrROP_1996 = (String) inputMap.get("ROP_1996");
    		
    		lStrROPPay1986 = (String) inputMap.get("PayROP1986");
    		lStrROPPay1996 = (String) inputMap.get("PayROP1996");
    		
    		
    		// If Pensioner Alive flag is Y,then Pension Cut is calculate. 
    		if(lStrIsAlive != null && lStrIsAlive.equalsIgnoreCase("Y"))
    		{
    			lPensionCutAmt = (Double) inputMap.get("PensionCutAmt");
    		}    		
    		
    		// Old Case Basic (Basic/FP1/FP2)
    		lOldBasicAmt = (Double) inputMap.get("OldBasicAmt");
    		lBasicAmtType = inputMap.get("BasicAmtType").toString();
    		
			// Getting MA Amt For HeadCode
    		
    		if(lObjPensionRqstHdrVO.getMedicalAllowenceAmount() != null && 
    				Double.valueOf(lObjPensionRqstHdrVO.getMedicalAllowenceAmount().toString()) > 0)
    		{
				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "MA", lZeroAmt, lForDate);
				if(lPensionHeadcodeRateVO != null)
				{
					// Getting Revised Payment Vo for Checking Transaction Payment. 
					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "MA", ForMonth);
					if(lObjRevisedPaymentVO != null) // MA Transection Payment Logic.
					{	
						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
						ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "MA", lZeroAmt, lPrvForDate);
						if(ltPensionHeadcodeRateVO != null)
						{
							lMAAmt = Double.valueOf(lPensionHeadcodeRateVO.getMinAmount().toString());
							if(lStrPayMA == null)
							{
								lOthMAArrAmt = Double.valueOf(lPensionHeadcodeRateVO.getMinAmount().toString()) - lMAAmt;
								
								inputMap.put("CurntPayMA","Y"); // Set Flag for indicate Payment of MA Done.
								inputMap.put("TrnForMonth",ForMonth);
								inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
								inputMap.put("DiffAmt",lOthMAArrAmt);
								inputMap.put("FieldType","MA");
								
								lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
								lTrnPaymentLst.add(lTrnPaymetVo);
							}
							
						}
					}
					else
					{
						lMAAmt = Double.valueOf(lPensionHeadcodeRateVO.getMinAmount().toString());
					}
					
				}
    		}
    		// HeadCode is not equals to FreedomFoghter && Navniraman.
    		if(lHeadCode != 16 && lHeadCode != 18 && lHeadCode != 19)
    		{
    			// For ROP 1986.
    			if(lForDate != null && lForDate.before(lObjSmplDateFrm.parse("01/01/1996"))
    					&& lForDate.after(lObjSmplDateFrm.parse("31/12/1985")) ) 
    			{
    				if(lStrIsROPApplied != null && lStrIsROPApplied.equalsIgnoreCase("Y") && 
    						lStrROP_1986 != null && lStrROP_1986.equalsIgnoreCase("Y") &&
    						!lStrROPPay1986.equalsIgnoreCase("N"))
    				{
    					if(lBasicAmtType.equalsIgnoreCase("Basic") && inputMap.get("Basic1986") != null)
    					{
    						lNewBasicAmt = (Double) inputMap.get("Basic1986");
    					}
    					else if(lBasicAmtType.equalsIgnoreCase("FP1") && inputMap.get("FP11986") != null)
    					{ 
    						lNewBasicAmt = (Double) inputMap.get("FP11986");
    					}
    					else if(lBasicAmtType.equalsIgnoreCase("FP2") && inputMap.get("FP21986") != null)
    					{ 
    						lNewBasicAmt = (Double) inputMap.get("FP21986");
    					}
    				}
    				else
    				{
    					lNewBasicAmt = lOldBasicAmt;
    				}
    				
    				lDPAmt = 0D;
    				
    				Double lOldTiAmt = 0D;
    				Double lNewTiAmt = 0D;
    				Double lNewAdsTiAmt = 0D;
    				Double lMinAmt = 0D;
    				
    				// Calculate TI Amt For OLD Basic
    				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lOldBasicAmt, lForDate);
    				
    				// Getting Revised Payment Vo for Checking Transaction Payment.
					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "TI", ForMonth);
					
    				if(lPensionHeadcodeRateVO != null)
    				{	
    					if(lObjRevisedPaymentVO != null) // TI Transection Payment Logic.
    					{
    						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
        					ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lOldBasicAmt, lPrvForDate);
        					if(ltPensionHeadcodeRateVO != null)
            				{
        						lOldTiAmt = getGreaterTIAmt(ltPensionHeadcodeRateVO, lOldBasicAmt, lPensionCutAmt);								
            				}
    					}
    					else
    					{
    						lOldTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lOldBasicAmt, lPensionCutAmt);
    					}
    					
    				}
    				
    				//	Calculate TI Amt For NEW Basic
    				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lNewBasicAmt, lForDate);
    				
    				if(lPensionHeadcodeRateVO != null)
    				{
    					if(lObjRevisedPaymentVO != null) // TI Transection Payment Logic.
    					{
    						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
        					ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lNewBasicAmt, lPrvForDate);
        					if(ltPensionHeadcodeRateVO != null)
            				{
        						lNewTiAmt = getGreaterTIAmt(ltPensionHeadcodeRateVO, lNewBasicAmt, lPensionCutAmt);
        						lNewAdsTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lNewBasicAmt, lPensionCutAmt);
        						
        						lOthTIArrAmt = lNewAdsTiAmt - lNewTiAmt; // TI Arrear For TI itself change
        						
        						inputMap.put("CurntPayTI","Y"); // Set Flag for indicate Payment of MA Done.
								inputMap.put("TrnForMonth",ForMonth);
								inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
								inputMap.put("DiffAmt",lOthTIArrAmt);
								inputMap.put("FieldType","TI");
								
								lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
								lTrnPaymentLst.add(lTrnPaymetVo);
            				}
    					}
    					else
    					{
    						lNewTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lNewBasicAmt, lPensionCutAmt);
    					}
    				}
    				
    				lTIAmt = lNewTiAmt;
    				lATIAmt = lNewTiAmt - lOldTiAmt; // TI Arrear For Basic change
    			}
    			// For ROP 1996 onwards.
    			else if(lForDate != null && lForDate.after(lObjSmplDateFrm.parse("31/12/1995")))
    			{
    				if(lStrIsROPApplied != null && lStrIsROPApplied.equalsIgnoreCase("Y") &&
    						lStrROP_1996 != null && lStrROP_1996.equalsIgnoreCase("Y") && 
    						!lStrROPPay1996.equalsIgnoreCase("N"))
    				{
    					if(lBasicAmtType.equalsIgnoreCase("Basic") && inputMap.get("Basic1996") != null )
    					{
    						lNewBasicAmt = (Double) inputMap.get("Basic1996");
    					}
    					else if(lBasicAmtType.equalsIgnoreCase("FP1") && inputMap.get("FP11996") != null)
    					{ 
    						lNewBasicAmt = (Double) inputMap.get("FP11996");
    					}
    					else if(lBasicAmtType.equalsIgnoreCase("FP2") && inputMap.get("FP21996") != null)
    					{ 
    						lNewBasicAmt = (Double) inputMap.get("FP21996");
    					}
    					
    					if(inputMap.containsKey("Basic1986"))
    					{
    						if(lBasicAmtType.equalsIgnoreCase("Basic") && inputMap.get("Basic1986") != null)
        					{
    							lOldBasicAmt = (Double) inputMap.get("Basic1986");
        					}
        					else if(lBasicAmtType.equalsIgnoreCase("FP1") && inputMap.get("FP11986") != null )
        					{ 
        						lOldBasicAmt = (Double) inputMap.get("FP11986");
        					}
        					else if(lBasicAmtType.equalsIgnoreCase("FP2") && inputMap.get("FP21986") != null)
        					{ 
        						lOldBasicAmt = (Double) inputMap.get("FP21986");
        					}    						
    					}
    				}
    				else
    				{
    					lNewBasicAmt = lOldBasicAmt;
    				}
    				
    				Double lNewTIPer = 0D;
    				Double lOldTiAmt = 0D;
    				Double lNewTiAmt = 0D;
    				Double lNewAdsTiAmt = 0D;
    				
    				// ROP = Y and SpecialCase = Y 
    				if(lStrIsROPApplied != null && lStrIsSpecialCase != null 
    						&& lStrIsROPApplied.equalsIgnoreCase("Y") && lStrIsSpecialCase.equalsIgnoreCase("Y"))
    				{
    					lDPAmt = 0D;
    					// Getting RTI Amt from Duration.
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "RTI", lZeroAmt, lForDate);
        				
        				if(lPensionHeadcodeRateVO != null)
        				{
        					// Getting Revised Payment Vo for Checking Transaction Payment.
        					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "TI", ForMonth);
        					if(lObjRevisedPaymentVO != null) // TI Transection Payment Logic.
        					{
        						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
        						ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "RTI", lZeroAmt, lPrvForDate);
        					    if(ltPensionHeadcodeRateVO != null)
        					    {
        					    	lTIPer = Double.valueOf(ltPensionHeadcodeRateVO.getRate().toString());
        					    	lNewTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
        					    	lNewTiAmt = ((lNewBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
        					    	lNewAdsTiAmt = ((lNewBasicAmt - lPensionCutAmt) * ((lNewTIPer) / 100 ));
        					    	
        					    	lOthTIArrAmt = lNewAdsTiAmt - lNewTiAmt;
        					    	
        					    	inputMap.put("CurntPayTI","Y"); // Set Flag for indicate Payment of TI Done.
    								inputMap.put("TrnForMonth",ForMonth);
    								inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
    								inputMap.put("DiffAmt",lOthTIArrAmt);
    								inputMap.put("FieldType","TI");
    								
    								lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
    								lTrnPaymentLst.add(lTrnPaymetVo);
        					    }
        					}
        					else
        					{
        						lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
        					}
        					lOldTiAmt = ((lOldBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
        					lNewTiAmt = ((lNewBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
        				}
    				}
    				// ROP = Y and SpecialCase = N 
    				else if(lStrIsROPApplied != null && lStrIsSpecialCase != null 
    						&& lStrIsROPApplied.equalsIgnoreCase("Y") && lStrIsSpecialCase.equalsIgnoreCase("N"))
    				{
    					lDPAmt = 0D;
    					if(lForDate.after(lObjSmplDateFrm.parse("31/12/2003")))  // fordate > 1/1/2004. DP Mrg.
        				{
        					lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "DP", lZeroAmt, lForDate);
        					if(lPensionHeadcodeRateVO != null)
            				{
            					// Getting Revised Payment Vo for Checking Transaction Payment.
            					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "DP", ForMonth);
            					if(lObjRevisedPaymentVO != null) // DP Transection Payment Logic.
            					{
            						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
        						    ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "DP", lZeroAmt, lPrvForDate);
        						    if(ltPensionHeadcodeRateVO != null)
        						    {
        						    	lDPPer = Double.valueOf(ltPensionHeadcodeRateVO.getRate().toString());
        						    	Double lNewDPPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
        						    	lOthDPArrAmt = ((lNewBasicAmt - lPensionCutAmt) * ((lNewDPPer) / 100 )) - lDPPer;
        						    	
        						    	inputMap.put("CurntPayDP","Y"); // Set Flag for indicate Payment of DP Done.
        								inputMap.put("TrnForMonth",ForMonth);
        								inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
        								inputMap.put("DiffAmt",lOthDPArrAmt);
        								inputMap.put("FieldType","PENSION");
        								
        								lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
        								lTrnPaymentLst.add(lTrnPaymetVo);
        						    }
        						}
        						else        						    
	        					{
        							lDPPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
        							lDPAmt = ((lNewBasicAmt - lPensionCutAmt) * ((lDPPer) / 100 ));
	        					}
            				}
        				}     					
    					// Getting STI Amt from Duration.
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "STI", lZeroAmt, lForDate);
        				if(lPensionHeadcodeRateVO != null)
        				{
        					// Getting Revised Payment Vo for Checking Transaction Payment.
        					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "TI", ForMonth);
        					if(lObjRevisedPaymentVO != null) // TI Transection Payment Logic.
        					{
        						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
        						ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "STI", lZeroAmt, lPrvForDate);
        					    if(ltPensionHeadcodeRateVO != null)
        					    {
        					    	lTIPer = Double.valueOf(ltPensionHeadcodeRateVO.getRate().toString());
        					    	lNewTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
        					    	lNewTiAmt = (((lNewBasicAmt - lPensionCutAmt)+lDPAmt) * ((lTIPer) / 100 ));
        					    	lNewAdsTiAmt = (((lNewBasicAmt - lPensionCutAmt)+lDPAmt) * ((lNewTIPer) / 100 ));        					    	
        					    	lOthTIArrAmt = lNewAdsTiAmt - lNewTiAmt;
        					    	
        					    	inputMap.put("CurntPayTI","Y"); // Set Flag for indicate Payment of DP Done.
    								inputMap.put("TrnForMonth",ForMonth);
    								inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
    								inputMap.put("DiffAmt",lOthTIArrAmt);
    								inputMap.put("FieldType","TI");
    								
    								lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
    								lTrnPaymentLst.add(lTrnPaymetVo);
        					    }
        					}
        					else
        					{
        						lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
        					}
        					lOldTiAmt = (((lOldBasicAmt - lPensionCutAmt)+lDPAmt) * ((lTIPer) / 100 ));
        					lNewTiAmt = (((lNewBasicAmt - lPensionCutAmt)+lDPAmt) * ((lTIPer) / 100 ));
        				}       				
    				}
    				// ROP = N 
    				else if(lStrIsROPApplied != null && lStrIsROPApplied.equalsIgnoreCase("N"))
    				{
    					lDPAmt = 0D;
    					// Calculate TI Amt For OLD Basic
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lOldBasicAmt, lForDate);

    					// Getting Revised Payment Vo for Checking Transaction Payment.
    					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "TI", ForMonth);

        				if(lPensionHeadcodeRateVO != null)
        				{
        					if(lObjRevisedPaymentVO != null) // TI Transection Payment Logic.
        					{
        						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
        					    ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lOldBasicAmt, lPrvForDate);
        					    if(ltPensionHeadcodeRateVO != null)
        					    {
        					    	lOldTiAmt = getGreaterTIAmt(ltPensionHeadcodeRateVO, lOldBasicAmt, lPensionCutAmt);        					    	
        					    }
        					}
        					else
        					{
        						lOldTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lOldBasicAmt, lPensionCutAmt);
        					}        					
        				}
    					// Calculate TI Amt For NEW Basic
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lNewBasicAmt, lForDate);
        				if(lPensionHeadcodeRateVO != null)
        				{
        					if(lObjRevisedPaymentVO != null) // TI Transection Payment Logic.
        					{
        						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
        					    ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lNewBasicAmt, lPrvForDate);
        					    if(ltPensionHeadcodeRateVO != null)
        					    {
        					    	lNewTiAmt = getGreaterTIAmt(ltPensionHeadcodeRateVO, lNewBasicAmt, lPensionCutAmt);
        					    	lNewAdsTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lNewBasicAmt, lPensionCutAmt);
        					    	
        					    	lOthTIArrAmt = lNewAdsTiAmt - lNewTiAmt;
        					    	
        					    	inputMap.put("CurntPayTI","Y"); // Set Flag for indicate Payment of MA Done.
    								inputMap.put("TrnForMonth",ForMonth);
    								inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
    								inputMap.put("DiffAmt",lOthTIArrAmt);
    								inputMap.put("FieldType","TI");
    								
    								lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
    								lTrnPaymentLst.add(lTrnPaymetVo);
        					    }
        					}
        					else
        					{
        						lNewTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lNewBasicAmt, lPensionCutAmt);
        					}
        				}
        				
        				// Getting IR Amt from Duration.
        				if(lForDate != null && lForDate.after(lObjSmplDateFrm.parse("31/03/1995")))
        				{
	        				if(lForDate.equals(lObjSmplDateFrm.parse("01/04/1995"))) // Fixed Rs. 50 For 01/04/1995
	        				{
	        					lIRAmt = 50.0;
	        				}
        					
	        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "IR", lZeroAmt, lForDate);
	        				if(lPensionHeadcodeRateVO != null)
	        				{
	        					// Getting Revised Payment Vo for Checking Transaction Payment.
	        					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "TI", ForMonth);
	        					if(lObjRevisedPaymentVO != null) // IR Transection Payment Logic.
	        					{
	        						lPrvForDate = getPrevDate(lPensionHeadcodeRateVO.getEffectiveFromDate());
	        					    ltPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "IR", lZeroAmt, lPrvForDate);
	        					    if(ltPensionHeadcodeRateVO != null)
	        					    {
	        					    	Double lOldIRAmt = getGreaterIRAmt(ltPensionHeadcodeRateVO, lNewBasicAmt);
	        					    	Double lNewIRAmt = getGreaterIRAmt(lPensionHeadcodeRateVO, lNewBasicAmt);
	        					    	lOthIRArrAmt = lNewIRAmt - lOldIRAmt;
	        					    	lIRAmt += lOthIRArrAmt;

	        					    	inputMap.put("CurntPayIR","Y"); // Set Flag for indicate Payment of IR Done.
	    								inputMap.put("TrnForMonth",ForMonth);
	    								inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
	    								inputMap.put("DiffAmt",lOthIRArrAmt);
	    								inputMap.put("FieldType","PENSION");
	    								
	    								lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
	    								lTrnPaymentLst.add(lTrnPaymetVo);
	        					    }
	        					}
	        					else
	        					{
	        						lIRAmt += getGreaterIRAmt(lPensionHeadcodeRateVO, lNewBasicAmt);
	        					}
	        				}
        				}
    				}
    				
    				lTIAmt = lNewTiAmt;
    				lATIAmt = lNewTiAmt - lOldTiAmt; // TI Arrear For Basic change
    			}
    			else
    			{
    				lNewBasicAmt = lOldBasicAmt;
    			}
    		}
    		// HeadCode is equals to Navniraman.
    		else if(lHeadCode != 18)
    		{
    			if(lForDate != null && lForDate.before(lObjSmplDateFrm.parse("01/10/2001")))
    			{
    				lNewBasicAmt = lOldBasicAmt;
    			}
    			else
    			{
					lPensionHeadcodeSpecialVO = lPensionBillDAO.getBasicFromHeadcodeSpecialRlt(lHeadCode, lOldBasicAmt, lForDate);
    				if(lPensionHeadcodeSpecialVO != null)
    				{
    					// Getting Revised Payment Vo for Checking Transaction Payment.
    					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "STI", ForMonth);

    					if(lObjRevisedPaymentVO != null)
        				{
    						lNewBasicAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getOldAmount().toString());
    						lOthSPArrAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getNewAmount().toString()) - lNewBasicAmt;
    						
					    	inputMap.put("CurntPaySTI","Y"); // Set Flag for indicate Payment of STI Done.
							inputMap.put("TrnForMonth",ForMonth);
							inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
							inputMap.put("DiffAmt",lOthSPArrAmt);
							inputMap.put("FieldType","PENSION");
							
							lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
							lTrnPaymentLst.add(lTrnPaymetVo);
        				}
    					else
    					{
    						lNewBasicAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getNewAmount().toString());
    					}
    					
    				}    				
    			}
    		}
    		//	HeadCode is equals to FreedomFoghter
    		else if(lHeadCode != 16 && lHeadCode != 19)
    		{
    			if(lForDate != null && lForDate.before(lObjSmplDateFrm.parse("01/07/1971")))
    			{
    				lNewBasicAmt = lOldBasicAmt;
    			}
    			else if(lForDate != null && lForDate.before(lObjSmplDateFrm.parse("01/08/1998")))
    			{
    				lPensionHeadcodeSpecialVO = lPensionBillDAO.getBasicFromHeadcodeSpecialRlt(lHeadCode, lOldBasicAmt, lForDate);
    				if(lPensionHeadcodeSpecialVO != null)
    				{
    					// Getting Revised Payment Vo for Checking Transaction Payment.
    					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "STI", ForMonth);
    					
    					if(lObjRevisedPaymentVO != null)
        				{
    						lNewBasicAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getOldAmount().toString());
    						lOthSPArrAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getNewAmount().toString()) - lNewBasicAmt;
    						
    						inputMap.put("CurntPaySTI","Y"); // Set Flag for indicate Payment of STI Done.
							inputMap.put("TrnForMonth",ForMonth);
							inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
							inputMap.put("DiffAmt",lOthSPArrAmt);
							inputMap.put("FieldType","PENSION");
							
							lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
							lTrnPaymentLst.add(lTrnPaymetVo);
        				}
    					else
    					{
    						lNewBasicAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getNewAmount().toString());
    					}
    				}
    			}
    			else if(lForDate != null && lForDate.after(lObjSmplDateFrm.parse("31/07/1998"))
    					&& lForDate.before(lObjSmplDateFrm.parse("31/07/2007")))
    			{
    				lNewBasicAmt = 1500.00;  // Fix Basic Amount for conditional Period. 
    				lDPAmt = 0D;
    				
    				// Getting TI For Duration.
    				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lZeroAmt, lForDate);
    				if(lPensionHeadcodeRateVO != null)
    				{
    					lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
    				}
    				
    				lTIAmt = ((lNewBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
    			}
    			else if(lForDate != null && lForDate.after(lObjSmplDateFrm.parse("01/08/2007")))
    			{
    				lPensionHeadcodeSpecialVO = lPensionBillDAO.getBasicFromHeadcodeSpecialRlt(lHeadCode, lOldBasicAmt, lForDate);
    				if(lPensionHeadcodeSpecialVO != null)
    				{
    					// Getting Revised Payment Vo for Checking Transaction Payment.
    					lObjRevisedPaymentVO = lPensionBillDAO.getPaymentMnthDtls(lPensionHeadcodeRateVO.getPensionHeadcodeRateId(), "STI", ForMonth);

    					if(lObjRevisedPaymentVO != null)
        				{
    						lNewBasicAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getOldAmount().toString());
    						lOthSPArrAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getNewAmount().toString()) - lNewBasicAmt;
    						
    						inputMap.put("CurntPaySTI","Y"); // Set Flag for indicate Payment of STI Done.
							inputMap.put("TrnForMonth",ForMonth);
							inputMap.put("TrnPayMonth",lObjRevisedPaymentVO.getPayInMonth());
							inputMap.put("DiffAmt",lOthSPArrAmt);
							inputMap.put("FieldType","PENSION");
							
							lTrnPaymetVo = insertTrnPaymentDtls(inputMap);
							lTrnPaymentLst.add(lTrnPaymetVo);
        				}
    					else
    					{
    						lNewBasicAmt = Double.valueOf(lPensionHeadcodeSpecialVO.getNewAmount().toString());
    					} 
    				}
    			}
    			else
    			{
    				lNewBasicAmt = lOldBasicAmt;
    			}
    		}
    		
    		if(lStrIsAlive.equalsIgnoreCase("N") && lDPPer > 0 && lObjMstPensionerHdrVO.getDateOfRetirement() != null)
    		{
    			// Check For Splite FPBasic with DP Per. 
    			if(lObjMstPensionerHdrVO.getDateOfRetirement().after(lObjSmplDateFrm.parse("31/12/2003")))
    			{
	    			List lresLst = getDividedBasic(lNewBasicAmt, Double.valueOf(lDPPer.toString()));
	    			lNewBasicAmt = Double.valueOf(lresLst.get(0).toString());
	    			lDPAmt = Double.valueOf(lresLst.get(1).toString());
    			}
    			else
    			{
    				lDPAmt = lNewBasicAmt * (lDPPer / 100);
    			}
    		}
    		
    		inputMap.put("TrnPaymentLst", lTrnPaymentLst);
    		
    		// Set Basic/DP/TI/MA/IR/ATI Amount for Month in Custom MothlyVo.    		
    		lMonthlyPensionBillVO.setBasicPensionAmount(new BigDecimal(lNewBasicAmt != 0 ? lNewBasicAmt.toString() : "0"));
    		lMonthlyPensionBillVO.setDpPercent(new BigDecimal(lDPPer != 0 ? lDPPer.toString() : "0"));
    		lMonthlyPensionBillVO.setDpPercentAmount(new BigDecimal(lDPAmt != 0 ? lDPAmt.toString() : "0"));
    		lMonthlyPensionBillVO.setTiPercent(new BigDecimal(lTIPer != 0 ? lTIPer.toString() : "0"));
    		lMonthlyPensionBillVO.setTiPercentAmount(new BigDecimal(lTIAmt != 0 ? lTIAmt.toString() : "0"));
    		lMonthlyPensionBillVO.setMedicalAllowenceAmount(new BigDecimal(lMAAmt != 0 ? lMAAmt.toString() : "0"));
    		lMonthlyPensionBillVO.setTIArrearsAmount(new BigDecimal(lATIAmt != 0 ? lATIAmt.toString() : "0"));
    		lMonthlyPensionBillVO.setIr(new BigDecimal(lIRAmt != 0 ? lIRAmt.toString() : "0"));
    		
		}
    	catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }
    	
    	return lMonthlyPensionBillVO;
    }
    
    /**
     * Calculate Greater TI Amount by Per. and Minimum amount.
     * @param lPensionHeadcodeRateVO
     * @param lOldBasicAmt
     * @param lPensionCutAmt
     * @throws Exception
     * @author Sagar
     */
    private Double getGreaterTIAmt(RltPensionHeadcodeRate lPensionHeadcodeRateVO,Double lBasicAmt,Double lPensionCutAmt) throws Exception
    {
    	Double lGreaterTIAmt = 0D;
 
		Double lMinAmt = 0D;
		Double lTIPer = 0D;
    	
    	try
    	{
    		if(lPensionHeadcodeRateVO != null)
			{
    			lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
				lGreaterTIAmt = ((lBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
				if(lPensionHeadcodeRateVO.getMinAmount() != null) // Checking for greater TI Amount from (Per||MinAmt)
				{
					lMinAmt = Double.valueOf(lPensionHeadcodeRateVO.getMinAmount().toString());
					//lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
					//lGreaterTIAmt = ((lBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
					
					if(lMinAmt > lGreaterTIAmt)
					{
						lGreaterTIAmt = lMinAmt;
					}
				}
				/*else
				{
					lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
					lGreaterTIAmt = ((lBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
				}*/
			}
    	}	
		catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }
		
    	return lGreaterTIAmt;
    }

    /**
     * Calculate Greater IR Amount by Per. and Minimum amount.
     * @param lPensionHeadcodeRateVO
     * @param lOldBasicAmt
     * @throws Exception
     * @author Sagar
     */
    private Double getGreaterIRAmt(RltPensionHeadcodeRate lPensionHeadcodeRateVO,Double lBasicAmt) throws Exception
    {
    	Double lGreaterIRAmt = 0D;
 
		Double lMinAmt = 0D;
		Double lIRPer = 0D;
    	
    	try
    	{
    		if(lPensionHeadcodeRateVO != null)
			{
    			lIRPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
    			lGreaterIRAmt = ((lBasicAmt) * ((lIRPer) / 100 ));
				if(lPensionHeadcodeRateVO.getMinAmount() != null) // Checking for greater IR Amount from (Per||MinAmt)
				{
					lMinAmt = Double.valueOf(lPensionHeadcodeRateVO.getMinAmount().toString());
				
					if(lMinAmt > lGreaterIRAmt)
					{
						lGreaterIRAmt = lMinAmt;
					}
				}
			}
    	}	
		catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }
		
    	return lGreaterIRAmt;
    }

    
    /**
     * Determine a New Basic value according to current basic, Applide ROP, Retirment Date and
     * adapted type of rop86. 
     * @param inputMap
     * @return {@link Double}
     * @throws Exception
     */
    public Map getNewROPBasicAmt(Map inputMap)throws Exception
    {
    	Map lRopResMap = new HashMap();
    	
    	Double lDNewBasicAmt = 0D;
    	Double lDOldBasicAmt = 0D;
    	
    	ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
    	NewPensionBillDAO lObjPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
    	
    	lDOldBasicAmt = (Double) inputMap.get("OldBasicAmt");
    	MstPensionerHdr lPensionerHdrVO = (MstPensionerHdr) inputMap.get("MstPensionerHdrVO");   	
    	
    	Date lDtOfRtmnt = lPensionerHdrVO.getDateOfRetirement();
    	SimpleDateFormat lObjSmplDateFrm = new SimpleDateFormat("dd/MM/yyyy");   	
    	
    	String lStrROP_1986 = null;
    	String lStrROP86_AdptedType = null;
    	String lStrROP_1996 = null;
    	//String lStrROP_2006 = null;
    	
    	String lStrPayROP1986 = "N";
    	String lStrPayROP1996 = "N";
    	//String lStrPayROP2006 = "N";
    	
    	try 
    	{
    		lStrROP_1986 = (String) inputMap.get("ROP_1986");
    		lStrROP86_AdptedType = (String) inputMap.get("ROP86_AdptedType");
    		lStrROP_1996 = (String) inputMap.get("ROP_1996");
    		//lStrROP_2006 = (String) inputMap.get("ROP_2006");
    		
    		if(inputMap.containsKey("PayROP1986"))
    		{
    			lStrPayROP1986 = (inputMap.get("PayROP1986") != null) ? inputMap.get("PayROP1986").toString() : "N"; 
    		}
    		
    		if(inputMap.containsKey("PayROP1996"))
    		{
    			lStrPayROP1996 = (inputMap.get("PayROP1996") != null) ? inputMap.get("PayROP1996").toString() : "N"; 
    		}

    		
    		// For ROP 1986....    		
    		if(lStrROP_1986 != null && lStrROP_1986.equalsIgnoreCase("Y") && !lStrPayROP1986.equalsIgnoreCase("Y"))
    		{	
    			if(lDtOfRtmnt != null && lDtOfRtmnt.before(lObjSmplDateFrm.parse("30/09/1977")))  /// 1
    			{
    				lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1986", "2", lDOldBasicAmt); /// Column 2 from ROP86.
    			}
    			else if(lDtOfRtmnt != null && lDtOfRtmnt.after(lObjSmplDateFrm.parse("29/09/1977")) // 2
    					&& lDtOfRtmnt.before(lObjSmplDateFrm.parse("01/05/1979")))
    			{
    				if(lStrROP86_AdptedType != null  && lStrROP86_AdptedType.equalsIgnoreCase("DAN"))
    				{
    					lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1986", "2", lDOldBasicAmt); /// Column 2 from ROP86.
    				}
    				else if(lStrROP86_AdptedType != null  && lStrROP86_AdptedType.equalsIgnoreCase("DAY"))
    				{
    					lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1986", "3", lDOldBasicAmt); /// Column 3 from ROP86.
    				}
    			}
    			else if(lDtOfRtmnt != null && lDtOfRtmnt.after(lObjSmplDateFrm.parse("30/04/1979")) // 3
    					&& lDtOfRtmnt.before(lObjSmplDateFrm.parse("31/01/1982")))
    			{
    				lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1986", "3", lDOldBasicAmt); /// Column 3 from ROP86.
    			}
				else if(lDtOfRtmnt != null && lDtOfRtmnt.after(lObjSmplDateFrm.parse("30/01/1982")) // 4
    					&& lDtOfRtmnt.before(lObjSmplDateFrm.parse("31/03/1985")))
    			{
    				if(lStrROP86_AdptedType != null  && lStrROP86_AdptedType.equalsIgnoreCase("ADAN"))
    				{
    					lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1986", "3", lDOldBasicAmt); /// Column 3 from ROP86.
    				}
    				else if(lStrROP86_AdptedType != null  && lStrROP86_AdptedType.equalsIgnoreCase("ADAY"))
    				{
    					lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1986", "4", lDOldBasicAmt); /// Column 4 from ROP86.
    				}
    			}
				else if(lDtOfRtmnt != null && lDtOfRtmnt.after(lObjSmplDateFrm.parse("30/03/1985")) // 5
    					&& lDtOfRtmnt.before(lObjSmplDateFrm.parse("01/01/1986")))
    			{
					lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1986", "5", lDOldBasicAmt); /// Column 5 from ROP86.
    			}    	
    		
    			if(lDNewBasicAmt != null && lDNewBasicAmt > 0)
    			{
    				lDOldBasicAmt = lDNewBasicAmt;
    			}
    			
    			lRopResMap.put("NewBasic1986", lDNewBasicAmt);    		
    			lStrPayROP1986 = "Y";
    		}       		
    		//	For ROP 1986....    		
    		if(lStrROP_1996 != null && lStrROP_1996.equalsIgnoreCase("Y") && !lStrPayROP1996.equalsIgnoreCase("Y")) // ROP 1996
    		{
    			lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1996", "0", lDOldBasicAmt);
    			
    			lRopResMap.put("NewBasic1996", lDNewBasicAmt);
    			lStrPayROP1996 = "Y";
    		}
    		/*	For ROP 2006....    		
    		else if(lStrROP_2006 != null && lStrROP_2006.equalsIgnoreCase("Y")) // ROP 1996
    		{
    			lDNewBasicAmt = 0.0;
    		}*/
    		
    		lRopResMap.put("PayROP1986", lStrPayROP1986);
    		lRopResMap.put("PayROP1996", lStrPayROP1996);
    		
    	}
        catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }
        
        return lRopResMap;
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
    
    /**
     * Return No of Day for the given month.
     * @param lYYYYMM
     * @return {@link Integer}
     */
    private Integer getDaysOfMonth(Integer lYYYYMM)
    {
        Integer YYYY = Integer.parseInt(lYYYYMM.toString().substring(0,4));
        Integer MM = Integer.parseInt(lYYYYMM.toString().substring(4,6));
        Calendar cal = new GregorianCalendar(YYYY, (MM-1), 1);
        Integer days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        return days;
    }  
    
    
    /**
     * Return subtract my Date by one day 
     * @param lDate
     * @return {@link Date}
     */
    private Date getPrevDate(Date lDate)throws Exception
    {
    	try
    	{	
	    	SimpleDateFormat lObjSmplDateFrm = new SimpleDateFormat("dd/MM/yyyy");
		
			//System.out.println("Date currently is " + lObjSmplDateFrm.format(lDate));
			long dateMillis = lDate.getTime(); 
			long dayInMillis = 1000 * 60 * 60 *24;// subtract a day 
			dateMillis = dateMillis - dayInMillis; 
			lDate.setTime(dateMillis);// set myDate to new time
    	}
    	catch (Exception e){
            gLogger.info("Error is :: " + e,e);
            throw(e);
        } 
		return lDate;
    }
    
    
    /**
     * Generate A Vo of Transaction Payment.
     * @param inputMap
     * @return {@link TrnPensionArrearDtls}
     * @throws Exception
     */
	private TrnPensionArrearDtls insertTrnPaymentDtls(Map inputMap) throws Exception
    {
       TrnPensionArrearDtls lObjArrearDtlsVo = new TrnPensionArrearDtls();
      
        try{
			lObjArrearDtlsVo.setPensionRequestId((Long)inputMap.get("PenRqstId"));
			lObjArrearDtlsVo.setPensionerCode(inputMap.get("PenCode").toString());
            lObjArrearDtlsVo.setArrearFieldType(inputMap.get("FieldType").toString());
            lObjArrearDtlsVo.setEffectedFromYyyymm(Integer.parseInt(inputMap.get("TrnForMonth").toString()));
            lObjArrearDtlsVo.setDifferenceAmount(new BigDecimal(inputMap.get("DiffAmt").toString()));
            lObjArrearDtlsVo.setPaymentFromYyyymm(Integer.parseInt(inputMap.get("TrnPayMonth").toString()));
        }
        catch (Exception e){
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }        
        return lObjArrearDtlsVo;
    }

}
