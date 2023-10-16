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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAO;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAO;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.NewPensionBillDAO;
import com.tcs.sgv.pension.dao.NewPensionBillDAOImpl;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAO;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionArrearDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionArrearDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPnsncaseRopRltDAO;
import com.tcs.sgv.pension.valueobject.MonthlyPensionBillVO;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

/**
 * Pension Bill Specific Srvice Impl.
 * 
 * @author Sagar Patel
 * @version 1.0
 */
public class NewPensionBillServiceImpl extends ServiceImpl implements NewPensionBillService {

	/* Global Variable for PostId */
	private Long gLngPostId = null;

    /* Global Variable for UserId */
	private Long gLngUserId = null;
    
    /* Glonal Variable for Location Code */
	private String gStrLocCode = null;
    
    /* Global Variable for Logger Class */
    private final Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for Current Date */
    private Date gDate = null;
    
    private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    

	/**
     * Get Pension Bill Data From Pension Case
     * 
     * @param inputMap
     * @return objRes ResultObject
     */
	public ResultObject getNewPensionBillData(Map inputMap)
    {
		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
               
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
        MstPensionerHdr lObjMstPensionerHdrVO = null;
        TrnPensionArrearDtls lObjArrearDtlsVo = null;
        List<TrnPensionArrearDtls> lInnerArrearDtlVoLst = null;
        List<TrnPensionArrearDtls> lOuterArrearDtlVoLst = new ArrayList<TrnPensionArrearDtls>();
        
        MonthlyPensionBillVO lPensionBillVO = null;        
        List<MonthlyPensionBillVO> lPensionBillVoLst = new ArrayList<MonthlyPensionBillVO>();
        List<MonthlyPensionBillVO> lMonthlyArrearVoLst = null;
        
        List<TrnPnsncaseRopRlt> lPnsnerROPLst = null;
        TrnPnsncaseRopRlt lPnsncaseRopRltVO = null;
        
        String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
        String lStrPensionFlag = bundleConst.getString("RECOVERY.PENSION");
        String lStrPnsrBasic = bundleConst.getString("NETPNSN.BASIC");
        
        String lStrPPONo = null;
        String lStrPnsrCode = null;
        String lStrPnsnerName = null;
        String lStrBasicPnsnFlage = null;
        String lStrFirstName = null;
        String lStrMiddleName = "";
        String lStrLastName = null;
        String lStrAliveFlg = "Y";
        
        Double lPensionBillAmt = 0D;
        Double lBasicPensionAmt = 0D;
        Double lPensionCutAmt = 0D;
        Double lOtherBenefitAmt = 0D;
        Double lSpecialCutAmt = 0D; 
        Double lNetPensionAmt = 0D;
        Double lDPPerAmt = 0D;
        Double lTIPerAmt = 0D;
        Double lMAAmt = 0D;
        Double lPersonalPnsnAmt = 0D;
        Double lIRAmt = 0D;
        Double lITCutAmt = 0D;
        Double lOMRAmt = 0D;
        Double lCvpMonthlyAmt = 0D;
        
        Double lArrearAmt = 0D;
        Double lOldArrearAmt = 0D;
        Double lPrvArrearAmt = 0D;
        Double lTotPrvArrearAmt = 0D;
        Double lArrearValue = 0D; 
        
        Double lTIArrearAmt = 0D;
        Double lTotPrvTIArrearAmt = 0D;
        
        Double lPnsnRecoveryAmt = 0D;
        Double lCrntMonthRecoveryAmt = 0D;
        Double lTotalCrntRecoveryAmt = 0D;
        Double lArrearRecoveryAmt = 0D;
        Double lTotalArrearRecoveryAmt = 0D;
        Double lNetBasicPensionAmt = 0D;
        Double lNetFamilyPensionAmt = 0D;
        Double lDPPer = 0.0;        
        Double lTIPer = 0.0;

        Date lDateOfDeath = null;
        Date lFP1Date = null;
        Date lFP2Date = null;
        Date lCommDate = null;
        Date lEndDate = null;
                
        Integer lNoOfYear = 0;
        Integer lcuryyyyMM = 0;
        Integer lCommyyyyMM = 0;
        Integer lDaysOfMonth = 0;
        Integer lDaysOfDOD = 0;
        Integer lDaysOfComm = 0;
        Integer lDaysOfCurnt = 0;
        Integer lPnsnerDOD = 0;
        Integer lEndYYYYMM = 0;
        Integer lEndDateDay = 0;
        long lRondedNetPensionAmt = 0L;
        
        String lHeadcode = null;
        String lScheme = null;
        
        String lStrIsROPApplied = null;
        String lStrROP_1986 = null;
        String lStrROP86_AdptedType = null;
        String lStrROP_1996 = null;
        String lStrROP_2006 = null;
        
        String lStrPayROP_1986 = null;
        String lStrPayROP_1996 = null;
        
        String lStrCalcType = null; 
                
        try
        {
        	setSessionInfo(inputMap);
        	
        	lStrPPONo = inputMap.get("PPONo").toString();
        	
        	inputMap.put("BillType", lStrPensionFlag);
        	
        	lStrBasicPnsnFlage = lStrPnsrBasic;
            lcuryyyyMM = Integer.valueOf(new SimpleDateFormat("yyyyMM").format(gDate));
            lDaysOfCurnt = getDaysOfMonth(lcuryyyyMM);
            
            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
          	
            NewPensionBillDAO lObjNewPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
            MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
            PensionBillProcessServiceImpl lObjProcessServiceImpl = new PensionBillProcessServiceImpl();
            TrnPensionArrearDtlsDAO lObjArrearDtlsDAO = new TrnPensionArrearDtlsDAOImpl(TrnPensionArrearDtls.class,srvcLoc.getSessionFactory());
            
            List<TrnPensionArrearDtls> lTrnPaymentLst = new ArrayList<TrnPensionArrearDtls>();
        	TrnPensionArrearDtls lTrnPaymetVo = null;
            
        	if(lStrPPONo != null && lStrPPONo.length() > 0 ) 
        	{
        		// Getting the ObjectVo of  TrnPensionRqstHdr
        		lObjTrnPensionRqstHdrVO = lObjCommonPensionDAO.getTrnPensionRqstHdrDtls(lStrPPONo, lStrStatus);
        	}

        	if(lObjTrnPensionRqstHdrVO != null)
        	{
        		lStrPnsrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
        		lStrCalcType = lObjTrnPensionRqstHdrVO.getCalcType();
        		        		
                lCommDate = lObjTrnPensionRqstHdrVO.getCommensionDate();
                lCommyyyyMM = Integer.valueOf(new SimpleDateFormat("yyyyMM").format(lCommDate));
                lDaysOfComm = Integer.valueOf(new SimpleDateFormat("dd").format(lCommDate));
                
                if(lObjTrnPensionRqstHdrVO.getEndDate() != null)
                {
                    lEndDate = lObjTrnPensionRqstHdrVO.getEndDate();
                    lEndYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lEndDate));
                    lEndDateDay = Integer.parseInt(new SimpleDateFormat("dd").format(lEndDate));
                    
                    if(lcuryyyyMM >= lEndYYYYMM) // checking EndDate before Current month or not.  
                    {
                        lcuryyyyMM = lEndYYYYMM;
                        lDaysOfCurnt = lEndDateDay;
                    }
                }
                lHeadcode = lObjTrnPensionRqstHdrVO.getHeadCode().toString();
        		lScheme = lObjTrnPensionRqstHdrVO.getSchemeType();
                lStrIsROPApplied = lObjTrnPensionRqstHdrVO.getIsRop();
        		
                inputMap.put("TrnPensionRqstHdrVO", lObjTrnPensionRqstHdrVO);
                inputMap.put("TrnPensionRqstID", lObjTrnPensionRqstHdrVO.getPensionRequestId().toString()); 
                
        		// Getting the Value from TrnPensionRqstHdr... End...
        	}
        	
        	if(lStrPnsrCode != null && lStrPnsrCode.length() > 0)
        	{
        	    // Getting the ObjectVo of  MstPensionerHdr... Start...
        		lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsrCode);
                lStrFirstName = lObjMstPensionerHdrVO.getFirstName();
                lStrLastName = lObjMstPensionerHdrVO.getLastName();
                lStrMiddleName = (lObjMstPensionerHdrVO.getMiddleName() != null) ? lObjMstPensionerHdrVO.getMiddleName() + " " : "";
                lStrPnsnerName = lStrFirstName + ' ' + lStrMiddleName + lStrLastName;
                
                if(lObjMstPensionerHdrVO.getAliveFlag() != null)
                {
                	lStrAliveFlg = lObjMstPensionerHdrVO.getAliveFlag();
                }
                inputMap.put("MstPensionerHdrVO", lObjMstPensionerHdrVO);
        	}
        	
        	lPnsnRecoveryAmt = lObjNewPensionBillDAO.getTrnPensionRecoveryDtls(lStrPnsrCode, lStrPensionFlag);
        	
        	// Case Calculation Type is Manual.
        	if(lStrCalcType != null && lStrCalcType.equalsIgnoreCase("M")) 
            {
        		// Getting the Value from TrnPensionRqstHdr... Start...
    			if(lObjTrnPensionRqstHdrVO.getBasicPensionAmount() != null){
    				lBasicPensionAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
    			}
    			
    			if(lObjTrnPensionRqstHdrVO.getDpPercent() != null){
    				lDPPer = Double.valueOf(lObjTrnPensionRqstHdrVO.getDpPercent().toString());
    			}
    			if(lObjTrnPensionRqstHdrVO.getTiPercent() != null){
    				lTIPer = Double.valueOf(lObjTrnPensionRqstHdrVO.getTiPercent().toString());
    			}
    			if(lObjTrnPensionRqstHdrVO.getMedicalAllowenceAmount() != null){
    				lMAAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getMedicalAllowenceAmount().toString());
    			}
    			if(lObjTrnPensionRqstHdrVO.getPersonalPension() != null){
    				lPersonalPnsnAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getPersonalPension().toString());
    			}
    			if(lObjTrnPensionRqstHdrVO.getCvpMonthlyAmount() != null)
    			{
    				lCvpMonthlyAmt = Double.valueOf(lObjTrnPensionRqstHdrVO.getCvpMonthlyAmount().toString());
    			}
    			if(lObjTrnPensionRqstHdrVO.getIr() != null)
    			{
    				lIRAmt = Double.valueOf(lObjTrnPensionRqstHdrVO.getIr().toString());
    			}
    			
    			lPensionCutAmt = lObjNewPensionBillDAO.getTotalPensionCutForMonth(lStrPnsrCode, lcuryyyyMM);
    			lSpecialCutAmt = lObjNewPensionBillDAO.getTotalSpecialCutForMonth(lStrPnsrCode, lcuryyyyMM);
    			lOtherBenefitAmt = lObjNewPensionBillDAO.getTotalOtherBenefitsForMonth(lStrPnsrCode, lcuryyyyMM);
    			lITCutAmt = lObjNewPensionBillDAO.getTotalITCutForMonth(lStrPnsrCode, lcuryyyyMM);
    			
    			lInnerArrearDtlVoLst = lObjArrearDtlsDAO.getArrearDtls(lStrPnsrCode, lcuryyyyMM);
    			
    			if(lInnerArrearDtlVoLst != null)
    			{
    				for(int i=0;i<lInnerArrearDtlVoLst.size();i++)
    				{
    					Object lObjArr = lInnerArrearDtlVoLst.get(i);
    					Object[] lObjTemp = (Object[]) lObjArr;
    					
    					if(lObjTemp[0] != null && lObjTemp[0].toString().equalsIgnoreCase("TI"))
    					{
    						lTIArrearAmt += Double.valueOf(lObjTemp[1].toString());
    					}
    					else
    					{
    						lArrearValue += Double.valueOf(lObjTemp[1].toString()); 
    					}
    				}
    			}
    			
    			lNetPensionAmt = (lBasicPensionAmt - lPensionCutAmt);
    			lDPPerAmt = ((lNetPensionAmt * lDPPer) / 100);
    			lNetPensionAmt = lNetPensionAmt + lDPPerAmt;
    			lTIPerAmt = ((lNetPensionAmt * lTIPer) / 100);
    			lNetPensionAmt = lNetPensionAmt + lTIPerAmt;
    			lNetPensionAmt = lNetPensionAmt + lMAAmt;
	        	lNetPensionAmt = lNetPensionAmt - lCvpMonthlyAmt;
	        	lNetPensionAmt = lNetPensionAmt + lPersonalPnsnAmt + lIRAmt + lOtherBenefitAmt;
	        	lNetPensionAmt = (lNetPensionAmt - (lSpecialCutAmt + lITCutAmt + lPnsnRecoveryAmt ) );
	        	
	        	lPensionBillAmt = lNetPensionAmt;
	        	
	        	lArrearAmt = lArrearValue + lTIArrearAmt;
	        	
	        	lNetPensionAmt += lArrearAmt;
    			
            }
        	// If Case Calculation Type is Auto.
        	else 
        	{        	
        		lMonthlyArrearVoLst = new ArrayList<MonthlyPensionBillVO>();
	        	//	Checking ROP's Applied to Pensioner.		Start.        		
	        	inputMap.put("IsROPApplied", lStrIsROPApplied);
	        	inputMap.put("TrnPaymentLst", lTrnPaymentLst);
	        	
	        	if(lStrIsROPApplied != null && lStrIsROPApplied.equalsIgnoreCase("Y"))
	        	{
		        	lPnsnerROPLst = lObjNewPensionBillDAO.getROPAppliedToPensner(lStrPPONo);  // Fetch ROP(s) Details.
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
		    				}
		    				else if(lPnsncaseRopRltVO.getRop().equalsIgnoreCase("1996"))
		    				{
		    					lStrROP_1996 = "Y";
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
		    			
		    			// For Pension Basic Amt ROP Amt. 
		    			Map lRopResMap = new HashMap();
		    			inputMap.put("OldBasicAmt", Double.valueOf(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString()));
		    			lRopResMap = lObjProcessServiceImpl.getNewROPBasicAmt(inputMap);
		    			
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
			    			lRopResMap = lObjProcessServiceImpl.getNewROPBasicAmt(inputMap);
			    			
			    			inputMap.put("FP11986", lRopResMap.get("NewBasic1986"));
			    			inputMap.put("FP11996", lRopResMap.get("NewBasic1996"));
		    			}
		    			
		    			//	 For Pension FP2 Amt ROP Amt. 
		    			if(lObjTrnPensionRqstHdrVO.getFp2Amount() != null)
		    			{
			    			inputMap.put("OldBasicAmt", Double.valueOf(lObjTrnPensionRqstHdrVO.getFp2Amount().toString()));
			    			lRopResMap = lObjProcessServiceImpl.getNewROPBasicAmt(inputMap);
			    			
			    			inputMap.put("FP21986", lRopResMap.get("NewBasic1986"));
			    			inputMap.put("FP21996", lRopResMap.get("NewBasic1996"));
		    			}			    			
		    			
		    			//  set ROP Pay Flage
		    			inputMap.put("PayROP1986", lStrPayROP_1986);
		    			inputMap.put("PayROP1996", lStrPayROP_1996);
		    			
		    			
		    		}
	        	}
	        	//	Checking ROP's Applied to Pensioner.		End.
	
	        	Map lNewMap = new HashMap();
	        	inputMap.put("lPendingArrear", 0);
	        	inputMap.put("FPFlag","N");   // Set For Not to Split VO Into Family Members 
	        	
	        	for(Integer i = lCommyyyyMM; i <= lcuryyyyMM;)
	            {
	        		
	        		inputMap.put("Date", i.intValue());
	        		if(i.intValue() != lcuryyyyMM.intValue())
	        		{
	        			inputMap.put("ArrComputeFlag","Y");
	        		}
	        		else
	        		{
	        			inputMap.put("ArrComputeFlag","N");
	        		}
	        		
	        		lNewMap = lObjProcessServiceImpl.getCurrMonthData(inputMap); 
	        		
	        		// For Month Data.
	        		lPensionBillVoLst = (List) lNewMap.get("lLstMonthlyPensionBillVO");
	          	   	
	          	   	if(lPensionBillVoLst != null && lPensionBillVoLst.size() > 0)
	          	   	{
	          	   		lPensionBillVO = lPensionBillVoLst.get(0);	
	          	   	}
	          	   	
	          	   	lBasicPensionAmt = Double.parseDouble(lPensionBillVO.getBasicPensionAmount().toString());
	    	   		lPensionCutAmt = Double.parseDouble(lPensionBillVO.getPensionCutAmount().toString());
	    	   		lDPPerAmt = Double.parseDouble(lPensionBillVO.getDpPercentAmount().toString());
	    	   		lTIPerAmt = Double.parseDouble(lPensionBillVO.getTiPercentAmount().toString());
	    	   		lMAAmt = Double.parseDouble(lPensionBillVO.getMedicalAllowenceAmount().toString());
	    	   		lSpecialCutAmt = Double.parseDouble(lPensionBillVO.getSpecialCutAmount().toString()); 
	    	   		lPersonalPnsnAmt = Double.parseDouble(lPensionBillVO.getPersonalPension().toString());
	    	   		lIRAmt = Double.parseDouble(lPensionBillVO.getIr().toString());
	    	   		lOtherBenefitAmt = Double.parseDouble(lPensionBillVO.getOtherBenefit().toString());
	    	   		lCrntMonthRecoveryAmt = Double.parseDouble(lPensionBillVO.getRecoveryAmount().toString());
	    	   		lITCutAmt = Double.parseDouble(lPensionBillVO.getItCutAmount().toString());
	    	   		lTIArrearAmt = Double.parseDouble(lPensionBillVO.getTIArrearsAmount().toString());
	    	   		lCvpMonthlyAmt = Double.parseDouble(lPensionBillVO.getCvpMonthlyAmount().toString());
	    	   		    	   		
	        		if(i.intValue() == lCommyyyyMM.intValue())
	        		{
	        			lDaysOfMonth = getDaysOfMonth(i);
	        			lBasicPensionAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lBasicPensionAmt / lDaysOfMonth));
	        			lPensionCutAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lPensionCutAmt / lDaysOfMonth));
	        			lDPPerAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lDPPerAmt / lDaysOfMonth));
	        			lTIPerAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lTIPerAmt / lDaysOfMonth));
	        			lMAAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lMAAmt / lDaysOfMonth));
	        			lSpecialCutAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lSpecialCutAmt / lDaysOfMonth));
	        			lPersonalPnsnAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lPersonalPnsnAmt / lDaysOfMonth));
	        			lIRAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lIRAmt / lDaysOfMonth));
	        			lOtherBenefitAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lOtherBenefitAmt / lDaysOfMonth));
	        			lCrntMonthRecoveryAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lCrntMonthRecoveryAmt / lDaysOfMonth));
	        			lITCutAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lITCutAmt / lDaysOfMonth));
	        			lTIArrearAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lTIArrearAmt / lDaysOfMonth));
	        			lCvpMonthlyAmt = ((lDaysOfMonth-(lDaysOfComm-1)) * (lCvpMonthlyAmt / lDaysOfMonth));
	        		}
	        		
	        		if(i.intValue() != lcuryyyyMM.intValue())
		        	{
	        			lMonthlyArrearVoLst.add(lPensionBillVO);
	        			lTotalArrearRecoveryAmt += lCrntMonthRecoveryAmt;
		        	}
	        		
	        		// Arrears Data.
	        		lInnerArrearDtlVoLst =  (List) lNewMap.get("lLstInsertArrears");
	        		if(i.intValue() == lCommyyyyMM.intValue())
		        	{	
	        			lDaysOfMonth = getDaysOfMonth(i);
	        			for(int j=0;j<lInnerArrearDtlVoLst.size();j++)
		        	   	{	
		          	   		lObjArrearDtlsVo = lInnerArrearDtlVoLst.get(j);
	 	          	   		lArrearValue = Double.valueOf(lObjArrearDtlsVo.getDifferenceAmount().toString());
		          	   				          	   	
	 	          	   		if(lObjArrearDtlsVo.getArrearFieldType().equalsIgnoreCase("OMR"))
		          	   		{
			          	   		lOMRAmt = Double.valueOf(lObjArrearDtlsVo.getDifferenceAmount().toString());
		          	   		}
			          	   	else
			          	   	{	
			          	   		lArrearValue = ((lDaysOfMonth-(lDaysOfComm-1)) * (lArrearValue / lDaysOfMonth));
	          	   				lArrearAmt += lArrearValue;
	          	   				lObjArrearDtlsVo.setDifferenceAmount(new BigDecimal(lArrearValue));
			          	   	}
		          	   		
	 	          	   		lOuterArrearDtlVoLst.add(lObjArrearDtlsVo);
		          	   	}
		        	}
	        		else
	        		{
	        			for(int j=0;j<lInnerArrearDtlVoLst.size();j++)
		        	   	{
		          	   		lObjArrearDtlsVo = lInnerArrearDtlVoLst.get(j);
		          	   		lArrearValue = Double.valueOf(lObjArrearDtlsVo.getDifferenceAmount().toString());
		          	   		
			          	   	if(lObjArrearDtlsVo.getArrearFieldType().equalsIgnoreCase("OMR"))
		          	   		{
			          	   		lOMRAmt = Double.valueOf(lObjArrearDtlsVo.getDifferenceAmount().toString());
		          	   		}
			          	   	else
			          	   	{	
			          	   		lArrearAmt += lArrearValue;
		      	   				lObjArrearDtlsVo.setDifferenceAmount(new BigDecimal(lArrearValue));
			          	   	}
		          	   		
			          	   	lOuterArrearDtlVoLst.add(lObjArrearDtlsVo);
		          	   	}
	        		}
	        		
	        		i += ((Integer.parseInt((i.toString().substring(4,6)))) == 12 ) ? 89 : 1;
	            }       	
	        	
	        	lTotalCrntRecoveryAmt = lPnsnRecoveryAmt + lCrntMonthRecoveryAmt;
	        	
	        	lNetPensionAmt = (lBasicPensionAmt - lPensionCutAmt);
	        	lNetPensionAmt = lNetPensionAmt + lDPPerAmt + lTIPerAmt + lMAAmt;
	        	lNetPensionAmt = lNetPensionAmt - lCvpMonthlyAmt;
	        	lNetPensionAmt = lNetPensionAmt + lPersonalPnsnAmt + lIRAmt + lOtherBenefitAmt;
	        	lNetPensionAmt = (lNetPensionAmt - (lSpecialCutAmt + lITCutAmt + lTotalCrntRecoveryAmt ) );
	        	
	        	lPensionBillAmt = lNetPensionAmt;
	        	
	        	lNetPensionAmt = lNetPensionAmt + lOMRAmt + lArrearAmt;
	        	
        	}        	
        	
        	/////////////////////////////////
        	HttpSession session = request.getSession();
        	session.setAttribute("TrnPaymentLst", lTrnPaymentLst);
        	/////////////////////////////////
        	
        	inputMap.put("PnsrCrntDate", gDate);
        	inputMap.put("CrntMonth", new SimpleDateFormat("MMMMM - yyyy").format(gDate));
            inputMap.put("AliveFlg", lStrAliveFlg);
            inputMap.put("PensionerCode", lStrPnsrCode);
        	inputMap.put("PnsnerName", lStrPnsnerName);
        	inputMap.put("BasicPnsnFlage", lStrBasicPnsnFlage);
        	inputMap.put("BasicPensionAmt", lBasicPensionAmt.toString());
        	inputMap.put("PensionCutAmt", lPensionCutAmt.toString());
            inputMap.put("SpecialCutAmt", lSpecialCutAmt.toString());
        	inputMap.put("DPPercentAmt", lDPPerAmt.toString());
        	inputMap.put("TIPercentAmt", lTIPerAmt.toString());
        	inputMap.put("CvpMonthlyAmt", lCvpMonthlyAmt);
        	inputMap.put("MAAmt", lMAAmt.toString());
            inputMap.put("PersonalPnsnAmt", lPersonalPnsnAmt.toString());
            inputMap.put("IRAmt", lIRAmt.toString());
            inputMap.put("OtherBenefitAmt", lOtherBenefitAmt.toString());
        	inputMap.put("ITCutAmt", lITCutAmt.toString());
        	inputMap.put("RecoveryAmt", lTotalCrntRecoveryAmt.toString());
            inputMap.put("PensionBillAmt", Math.round(lPensionBillAmt));
        	inputMap.put("lNetPensionAmt", Math.round(lNetPensionAmt));
        	inputMap.put("OMRAmt", Math.round(lOMRAmt));
            inputMap.put("TIArrearAmt", Math.round(lTIArrearAmt));
        	inputMap.put("ArrearAmt", Math.round(lArrearAmt));
            inputMap.put("ArrearDtlVoLst", lOuterArrearDtlVoLst);
            inputMap.put("MonthlyArrearVoLst", lMonthlyArrearVoLst);
            inputMap.put("TotalArrearRecovery", lTotalArrearRecoveryAmt);
            inputMap.put("Headcode", lHeadcode);
        	inputMap.put("Scheme", lScheme);
            
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
	
	/*private String checkFromToMoth(TrnPensionItCutDtls lObjPensionItCutDtls)
	{
		Integer lFromYYYYMM = 0;
		Integer lToYYYYMM = 0;
		
		Integer lCurYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(gDate));
			
		lFromYYYYMM = lObjPensionItCutDtls.getFromMonth();
		lToYYYYMM = lObjPensionItCutDtls.getToMonth();
		
		if( lCurYYYYMM >= lFromYYYYMM && lCurYYYYMM <= lToYYYYMM )
		{
			return "true";
		}	
		
		return "false";
	}*/
	
	/**
	 * Save Pension Bill Dtls Integero TrnPensionBillDtls Table
	 * @param lMapInput
	 * @return
	 */
	public ResultObject saveNewPensionBillDtls(Map lMapInput) 
	{
		ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        
        NewPensionBillDAO lObPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
        
        TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,srvcLoc.getSessionFactory());
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;

        TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class,srvcLoc.getSessionFactory());
        TrnPensionBillDtls lObjTrnPensionBillDtls = null;
        
        TrnPensionArrearDtlsDAO lObjArrearDtlsDAO = new TrnPensionArrearDtlsDAOImpl(TrnPensionArrearDtls.class,srvcLoc.getSessionFactory());
        TrnPensionArrearDtls lObjArrearDtlsVo = null;
        List<TrnPensionArrearDtls> lPensionArrearDtlsVOLst = null;
        
        RltPensioncaseBillDAO lObjRltPensioncaseBillDAO = new RltPensioncaseBillDAOImpl(RltPensioncaseBill.class,srvcLoc.getSessionFactory());
        RltPensioncaseBill lObjRltPensioncaseBill = null;
        
        TrnPensionRqstHdrDAO lObjPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,srvcLoc.getSessionFactory());
        TrnPensionRqstHdr lObjPensionRqstHdrVo = null;
        
        TrnPnsncaseRopRltDAO lObjRopRltDAO = new TrnPnsncaseRopRltDAO(TrnPnsncaseRopRlt.class,srvcLoc.getSessionFactory());
        TrnPnsncaseRopRlt lObjPnsncaseRopRltVo = null;
        
        List<TrnPensionArrearDtls> lTrnPaymentLst = null;
        
        String lStrPension = bundleConst.getString("RECOVERY.PENSION");
        String lStrBillStatus = bundleConst.getString("STATUS.SAVED");
        String lOBPMBillNo = (String) lMapInput.get("billNo");
        String lStrBillCntrlNo = (String) lMapInput.get("PnsnBillCntrlNo");
        Long lPnsnTokenNo = (Long) lMapInput.get("PnsnTokenNo");
        Long lPnsnROPRltPk = 0L;        
        
        try 
        {
        	setSessionInfo(lMapInput);        	
        	
        	String lStrTrnPnsnRqstID = StringUtility.getParameter("hidTrnPensionRqstID", request).trim();
        	String lStrPPONo = StringUtility.getParameter("txtPPONO", request).trim();
            String lStrPayROP1986 = StringUtility.getParameter("hidPayROP1986", request).trim();
            String lStrPayROP1996 = StringUtility.getParameter("hidPayROP1996", request).trim();
            String lNewROPBasic = StringUtility.getParameter("hidNewROPBasic", request).trim();
        	String lNewROPFP1 = StringUtility.getParameter("hidNewROPFP1", request).trim();
        	String lNewROPFP2 = StringUtility.getParameter("hidNewROPFP2", request).trim();
        	        	
            // insert Date TrnPensionBillHdr Table.
            
            lObjTrnPensionBillHdr = (TrnPensionBillHdr) lMapInput.get("TrnPensionBillHdrVO");
            lObjTrnPensionBillHdr.setBillNo(lOBPMBillNo);
            lObjTrnPensionBillHdr.setBillType(lStrPension);
            
            if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
            {
                lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo.toString());
            }
            
            lObjTrnPensionBillHdr.setTrnPensionBillHdrId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", lMapInput));
            
            lObjTrnPensionBillHdrDAO.create(lObjTrnPensionBillHdr);
            
            //  insert Date TrnPensionBillDtls Table.
        	lObjTrnPensionBillDtls =  (TrnPensionBillDtls) lMapInput.get("TrnPensionBillDtlsVO");
            lObjTrnPensionBillDtls.setTrnPensionBillHdrId(lObjTrnPensionBillHdr.getTrnPensionBillHdrId());
        	lObjTrnPensionBillDtls.setTrnPensionBillDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", lMapInput));
        	
        	lObjTrnPensionBillDtlsDAO.create(lObjTrnPensionBillDtls);
        	
            // Insert Arrear Details into TrnPensionArrearDtls if Any Arrear is there in first Payment.
            lPensionArrearDtlsVOLst = (List) lMapInput.get("TrnPensionArrearDtlsVoLst");            
            if(lPensionArrearDtlsVOLst != null)
            {
                for(int i=0;i<lPensionArrearDtlsVOLst.size();i++)
                {
                    lObjArrearDtlsVo = lPensionArrearDtlsVOLst.get(i);
                    
                    lObjArrearDtlsVo.setPensionArrearDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_arrear_dtls", lMapInput));
                    lObjArrearDtlsVo.setBillNo(lOBPMBillNo);
                    
                    lObjArrearDtlsDAO.create(lObjArrearDtlsVo);
                }
            }
            
        	// update RltPensioncaseBill Table.
            Long lRltPensioncaseBillPK = (Long) lMapInput.get("RltPensnBillPK");
        	
        	if(lRltPensioncaseBillPK != null)
        	{
        		lObjRltPensioncaseBill = lObjRltPensioncaseBillDAO.read(lRltPensioncaseBillPK);
        		lObjRltPensioncaseBill.setBillNo(new BigDecimal(lOBPMBillNo));
        		lObjRltPensioncaseBill.setBillCntrlNo(lStrBillCntrlNo);
                if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
                {
                    lObjRltPensioncaseBill.setTokenNo(lPnsnTokenNo);
                }
        		lObjRltPensioncaseBillDAO.update(lObjRltPensioncaseBill);
        	}
        	
            if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
            {
            	// update RltPensioncaseBill Table.
            	OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,srvcLoc.getSessionFactory());
            	
            	Long lLngPnsnBillNo = Long.parseLong(lOBPMBillNo);
            	//lObjTokenStatusDAO.updateTokenStatus(lPnsnTokenNo, gStrLocCode, lLngPnsnBillNo, gLngUserId, gLngPostId,BillProcConstants.INWARD_TYPE_BILL);
            }	
            
            // Update Pension Rqst For New ROP Basic. if it is ?
            
            if(lStrPayROP1996 != null && lStrPayROP1996.equalsIgnoreCase("Y"))
            {
	            lObjPensionRqstHdrVo = lObjPensionRqstHdrDAO.read(Long.valueOf(lStrTrnPnsnRqstID));
	            
	            lObjPensionRqstHdrVo.setBasicPensionAmount(new BigDecimal(lNewROPBasic.length() != 0 ? lNewROPBasic : "0.00"));
	            lObjPensionRqstHdrVo.setFp1Amount(new BigDecimal(lNewROPFP1.length() != 0 ? lNewROPFP1 : "0.00"));
	            lObjPensionRqstHdrVo.setFp2Amount(new BigDecimal(lNewROPFP2.length() != 0 ? lNewROPFP2 : "0.00"));
	            
	            lObjPensionRqstHdrDAO.update(lObjPensionRqstHdrVo);
	            
	            // Update PnsnROPRlt for Setting ROP
	            lPnsnROPRltPk = lObPensionBillDAO.getPKOfPnsncaseROPRlt(lStrPPONo, "1996");
	            
	            lObjPnsncaseRopRltVo = lObjRopRltDAO.read(lPnsnROPRltPk);
	            lObjPnsncaseRopRltVo.setRopPaid("Y");
	            
	            lObjRopRltDAO.update(lObjPnsncaseRopRltVo);
	            
	            if(lStrPayROP1986 != null && lStrPayROP1986.equalsIgnoreCase("Y"))
	            {
	            	// 	 Update PnsnROPRlt for Setting ROP
		            lPnsnROPRltPk = lObPensionBillDAO.getPKOfPnsncaseROPRlt(lStrPPONo, "1986");
		            
		            lObjPnsncaseRopRltVo = lObjRopRltDAO.read(lPnsnROPRltPk);
		            lObjPnsncaseRopRltVo.setRopPaid("Y");
		            
		            lObjRopRltDAO.update(lObjPnsncaseRopRltVo);
	            }
            } 
            
            // Insert Transaction Payment information into Arreare Table.            
            lTrnPaymentLst = (List)lMapInput.get("TrnPaymentLst");            
            if(lTrnPaymentLst != null)
            {
                for(int i=0;i<lTrnPaymentLst.size();i++)
                {
                    lObjArrearDtlsVo = lTrnPaymentLst.get(i);
                    
                    lObjArrearDtlsVo.setPensionArrearDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_arrear_dtls", lMapInput));
                    lObjArrearDtlsVo.setBillNo(lOBPMBillNo);                    
                    lObjArrearDtlsVo.setCreatedUserId(new BigDecimal(gLngUserId));
                    lObjArrearDtlsVo.setCreatedPostId(new BigDecimal(gLngPostId));
                    lObjArrearDtlsVo.setCreatedDate(gDate);
                    
                    lObjArrearDtlsDAO.create(lObjArrearDtlsVo);
                }
            }
            
            
            resObj.setResultValue(lMapInput);
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
	
	
	/**
     * Get Saved Pension Bill Data For View the Pension Bill. 
     * 
     * @param inputMap
     * @return objRes ResultObject
     */
    public ResultObject getSavedNewPensionBillData(Map inputMap)
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
               
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
        TrnPensionBillHdr lObjTrnPensionBillHdrVO = null;
        TrnPensionBillDtls lObjTrnPensionBillDtls = null;
        TrnPensionArrearDtls lObjArrearDtlsVO = null;
        MstPensionerHdr lObjMstPensionerHdrVO = null;
        
        List<TrnPensionArrearDtls> lObjArrearDtlsVOLst = null;         


        String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
        String lStrPnsrBasic = bundleConst.getString("NETPNSN.BASIC");
        String lStrPnsrFP1 = bundleConst.getString("NETPNSN.FP1");
        String lStrPnsrFP2 = bundleConst.getString("NETPNSN.FP2");
        String lStrArrearOMR = bundleConst.getString("ARREAR.OMR");

        
        String lStrPPONo = null;
        String lStrBillNo = null;
        String lStrBillType = null;
        String lStrPnsnerName = null;
        String lStrPnsrCode = null;
        String lStrBasicPnsnFlage = null;
        String lStrFirstName = null;
        String lStrMiddleName = "";
        String lStrLastName = null;
        
        String lStrTokenNo = null;
        
        
        Double lBasicPensionAmt = 0D;
        Double lPensionCutAmt = 0D;
        Double lNetPensionAmt = 0D;
        Double lDPPerAmt = 0D;
        Double lTIPerAmt = 0D;
        Double lMAAmt = 0D;
        Double lPersonalPnsnAmt = 0D;
        Double lIRAmt = 0D;
        Double lITCutAmt = 0D;
        Double lSpecialCutAmt = 0D;
        Double lRecoveryAmt = 0D;
        Double lOMRAmt = 0D;
        Double lArrearAmt= 0D;
        Double lCrntMnthArrearAmt= 0D;
        
        Double lATIAmt = 0D;
        Double lOtherBenefitAmt = 0D;
        
        //Double lCVPMonthlyAmt =0.00;
        Double lPensionBillAmt = 0D;

        
        Long lTrnPensionBillHdrPK = null;
        
        Long lDPPer = 0L;        
        Long lTIPer = 0L;        
        
        Date lDateOfDeath = null;
        Date lFP1Date = null;
        Date lFP2Date = null;
        Date lStrBillCrtDate = null;
        
        Integer lNoOfYear = 0;
        Integer lForMonth = 0;
        
        try
        {
            setSessionInfo(inputMap);
            
            lStrBillNo = inputMap.get("billNo").toString();
            
            lStrBillType = bundleConst.getString("RECOVERY.PENSION");
            
            lStrBasicPnsnFlage = lStrPnsrBasic;
            
            NewPensionBillDAO lObjNewPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
            TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,srvcLoc.getSessionFactory());
            MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
                    	
            if(lStrBillNo != null && lStrBillNo.length() > 0 ) 
            {
                // Getting the ObjectVo of  TrnPensionBillHdr
                lObjTrnPensionBillHdrVO = lObjTrnPensionBillHdrDAO.getTrnPensionBillHdr(lStrBillNo, lStrBillType);
                lStrTokenNo = lObjTrnPensionBillHdrVO.getTokenNo();
                lStrBillCrtDate = lObjTrnPensionBillHdrVO.getBillDate();
                
                // Getting the ObjectVo of  TrnPensionBillDtls
                lTrnPensionBillHdrPK = lObjTrnPensionBillHdrVO.getTrnPensionBillHdrId();
                lObjTrnPensionBillDtls = lObjNewPensionBillDAO.getTrnPensionBillDtls(lTrnPensionBillHdrPK);
                
                // Getting the ObjectVo of  TrnPensionArrearDtls
                lObjArrearDtlsVOLst = lObjNewPensionBillDAO.getTrnPensionArrearDtls(lObjTrnPensionBillDtls.getPensionerCode(), lStrBillNo);
            }
            
            if(lObjTrnPensionBillDtls != null)
            {
                // Getting the Value from TrnPensionRqstHdr... Start...
                lStrPnsrCode = lObjTrnPensionBillDtls.getPensionerCode();
                lBasicPensionAmt = Double.parseDouble(lObjTrnPensionBillDtls.getPensionAmount().toString());
                //lDPPer = Long.parseLong(lObjTrnPensionBillDtls.getDpPercent().toString());
                //lTIPer = Long.parseLong(lObjTrnPensionBillDtls.getTiPercent().toString());
                lDPPerAmt = Double.valueOf(lObjTrnPensionBillDtls.getDpAmount().toString());
                lTIPerAmt = Double.valueOf(lObjTrnPensionBillDtls.getTiAmount().toString());
                lMAAmt = Double.parseDouble(lObjTrnPensionBillDtls.getMedicalAllowenceAmount().toString());
                lPersonalPnsnAmt = Double.parseDouble(lObjTrnPensionBillDtls.getPersonalPensionAmount().toString());
                lIRAmt = Double.parseDouble(lObjTrnPensionBillDtls.getIrAmount().toString());
                lPensionCutAmt = Double.parseDouble(lObjTrnPensionBillDtls.getPensnCutAmount().toString());
                lITCutAmt = Double.parseDouble(lObjTrnPensionBillDtls.getIncomeTaxCutAmount().toString());
                lSpecialCutAmt = Double.parseDouble(lObjTrnPensionBillDtls.getSpecialCutAmount().toString());
                lOtherBenefitAmt = Double.parseDouble(lObjTrnPensionBillDtls.getOtherBenefits().toString());
                lATIAmt = Double.parseDouble(lObjTrnPensionBillDtls.getTiArrearAmount().toString());
                lArrearAmt = Double.parseDouble(lObjTrnPensionBillDtls.getArrearAmount().toString());
                lRecoveryAmt = Double.parseDouble(lObjTrnPensionBillDtls.getRecoveryAmount().toString());
                lNetPensionAmt = Double.parseDouble(lObjTrnPensionBillDtls.getReducedPension().toString());
                
                // Getting the Value from TrnPensionRqstHdr... End...
            }
            
            if(lStrPnsrCode != null && lStrPnsrCode.length() > 0)
            {
                // Getting the ObjectVo of  TrnPensionRqstHdr... Start...
                lObjTrnPensionRqstHdrVO = lObjNewPensionBillDAO.getPPONoFromPnsnerCode(lStrPnsrCode, lStrStatus);
                lStrPPONo = lObjTrnPensionRqstHdrVO.getPpoNo();
                // Getting the ObjectVo of  TrnPensionRqstHdr... Start...
                
                
                // Getting the ObjectVo of  MstPensionerHdr... Start...
                lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsrCode);
                lStrFirstName = lObjMstPensionerHdrVO.getFirstName();
                lStrLastName = lObjMstPensionerHdrVO.getLastName();
                lStrMiddleName = (lObjMstPensionerHdrVO.getMiddleName() != null) ? lObjMstPensionerHdrVO.getMiddleName() + " " : "";
                lStrPnsnerName = lStrFirstName + ' ' + lStrMiddleName + lStrLastName;
                
                    // Check for Basic Pension / FP1 Pension / FP2 Pension... Start....
                    lDateOfDeath = lObjMstPensionerHdrVO.getDateOfDeath();
                    lFP1Date = lObjTrnPensionRqstHdrVO.getFp1Date();
                    lFP2Date = lObjTrnPensionRqstHdrVO.getFp2Date();
                                    
                    if( lDateOfDeath != null && lDateOfDeath.toString().length() > 0 )
                    {
                        if(lDateOfDeath.before(lFP1Date) || lDateOfDeath.equals(lFP1Date)){
                            lStrBasicPnsnFlage = lStrPnsrFP1;
                        }
                        else if(lDateOfDeath.after(lFP2Date) || lDateOfDeath.equals(lFP2Date)){
                            lStrBasicPnsnFlage = lStrPnsrFP2;
                        }
                    }
                    // Check for Basic Pension / FP1 Pension / FP2 Pension... End....
                    
                //  Getting the ObjectVo of  MstPensionerHdr... End...
                
            }
            
            // Getting a Total Arrear & OMR if it is.. 
            
            //TrnPensionArrearDtlsDAO lObjArrearDtlsDAO = new TrnPensionArrearDtlsDAOImpl(TrnPensionArrearDtls.class,srvcLoc.getSessionFactory());
            //lForMonth = Integer.valueOf(new SimpleDateFormat("yyyyMM").format(lStrBillCrtDate));
            //lCrntMnthArrearAmt = lObjArrearDtlsDAO.getArrearForMonth(lForMonth, lStrPnsrCode);
            
            if(lObjArrearDtlsVOLst != null)
            {
                for(int i=0;i<lObjArrearDtlsVOLst.size();i++)
                {
                    lObjArrearDtlsVO = lObjArrearDtlsVOLst.get(i);
                    if(lObjArrearDtlsVO.getArrearFieldType().equalsIgnoreCase(lStrArrearOMR))
                    {
                        lOMRAmt = Double.valueOf(lObjArrearDtlsVO.getDifferenceAmount().toString());
                    }
                    else
                    {
                        //lArrearAmt += Double.valueOf(lObjArrearDtlsVO.getDifferenceAmount().toString());
                    }
                }
            }
            
            lPensionBillAmt = (((lBasicPensionAmt - lPensionCutAmt) + lDPPerAmt) + lTIPerAmt + lMAAmt) - (lITCutAmt + lRecoveryAmt);
            
            inputMap.put("PPONo", lStrPPONo);
            inputMap.put("CrntMonth",new SimpleDateFormat("MMMMM-yyyy").format(lStrBillCrtDate));
            
            inputMap.put("PnsnBillTokenNo", lStrTokenNo);
            inputMap.put("PnsrCrntDate", lStrBillCrtDate);
            
            inputMap.put("PnsnerName", lStrPnsnerName);
            inputMap.put("lNoOfYear", lNoOfYear);
            inputMap.put("BasicPnsnFlage", lStrBasicPnsnFlage);
            inputMap.put("BasicPensionAmt", lBasicPensionAmt.toString());
            inputMap.put("PensionCutAmt", lPensionCutAmt.toString());
            inputMap.put("DPPercent", lDPPer.toString());
            inputMap.put("DPPercentAmt", lDPPerAmt.toString());
            inputMap.put("TIPercent", lTIPer.toString());
            inputMap.put("TIPercentAmt", lTIPerAmt.toString());
            inputMap.put("MAAmt", lMAAmt.toString());
            inputMap.put("PersonalPnsnAmt", lPersonalPnsnAmt.toString());
            inputMap.put("IRAmt", lIRAmt.toString());
            inputMap.put("ITCutAmt", lITCutAmt.toString());
            inputMap.put("SpecialCutAmt", lSpecialCutAmt.toString());
            inputMap.put("RecoveryAmt", lRecoveryAmt.toString());
            //inputMap.put("CVPMonthlyAmt", lCVPMonthlyAmt.toString());
            inputMap.put("PensionBillAmt", Math.round(lPensionBillAmt));
            inputMap.put("lNetPensionAmt", lNetPensionAmt.toString());
            
            inputMap.put("OtherBenefitAmt", lOtherBenefitAmt);
            inputMap.put("TIArrearAmt", lATIAmt);
            
            inputMap.put("CrntMnthArrearAmt", Math.round(lCrntMnthArrearAmt));
            
            inputMap.put("OMRAmt", Math.round(lOMRAmt));
            inputMap.put("ArrearAmt", Math.round(lArrearAmt));
            inputMap.put("ArrearDtlVoLst", lObjArrearDtlsVOLst);

            
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
	
	private void setSessionInfo(Map inputMap)
    {
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocCode = SessionHelper.getLocationCode(inputMap);
        gDate = DBUtility.getCurrentDateFromDB();
    }
    
    private Integer getDaysOfMonth(Integer lYYYYMM)
    {
        Integer YYYY = Integer.parseInt(lYYYYMM.toString().substring(0,4));
        Integer MM = Integer.parseInt(lYYYYMM.toString().substring(4,6));
        Calendar cal = new GregorianCalendar(YYYY, (MM-1), 1);
        Integer days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        return days;
    }
    
    private TrnPensionArrearDtls insertArrearDtls(Map inputMap) throws Exception
    {
       TrnPensionArrearDtls lObjArrearDtlsVo = new TrnPensionArrearDtls();
        try
        {
            lObjArrearDtlsVo.setArrearFieldType(inputMap.get("ArrearType").toString());
            lObjArrearDtlsVo.setDifferenceAmount(new BigDecimal(inputMap.get("ArrearValue").toString()));
            lObjArrearDtlsVo.setTotalDifferenceAmt(new BigDecimal(inputMap.get("PrvArrearValue").toString()));
            lObjArrearDtlsVo.setEffectedFromYyyymm(Integer.parseInt(inputMap.get("WEFYYYYMM").toString()));
        }
        catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }        
        
        return lObjArrearDtlsVo;
    }
    
    /**
     * Calculate Net Pension Amount for the Given Month.
     * @author Sagar
     * @param inputMap
     * @return 
     * @throws Exception
   */
    /*
    private Double getNetBasicPensionForMonth(Map inputMap) throws Exception
    {
    	ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        Double lNetBasicPensionAmt = 0D;
        
        String lStrPnsrCode = null;
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
        MonthlyPensionBillVO lObjMonthlyPensionBillVO = null;
        int ForMonth = Integer.parseInt(inputMap.get("forMonth").toString());
        
        Double lBasicPensionAmt = 0D;
        Double lPensionCutAmt = 0D;
        Double lDPPerAmt = 0D;
        Double lTIPerAmt = 0D;
        Double lMAAmt = 0D;
        Double lPersonalPnsnAmt = 0D;
        Double lIRAmt = 0D;
        Double lITCutAmt = 0D;
        Double lSpecialCutAmt = 0D;
        Double lOtherBenefitAmt = 0D;
        Double lNetPensionAmt = 0D;
        
        try
        {
            if(lObjTrnPensionRqstHdrVO != null)
            {
                // Getting the Value from TrnPensionRqstHdr... Start...
                if(lObjTrnPensionRqstHdrVO.getPensionerCode() != null){
                    lStrPnsrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
                }
                if(lObjTrnPensionRqstHdrVO.getBasicPensionAmount() != null){
                    lBasicPensionAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
                }
                if(lObjTrnPensionRqstHdrVO.getPersonalPension() != null){
                    lPersonalPnsnAmt = Double.valueOf(lObjTrnPensionRqstHdrVO.getPersonalPension().toString());
                }
            }
            
            NewPensionBillDAO lObjPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
            
            lPensionCutAmt = lObjPensionBillDAO.getTotalPensionCutForMonth(lStrPnsrCode, ForMonth);           
            lOtherBenefitAmt = lObjPensionBillDAO.getTotalOtherBenefitsForMonth(lStrPnsrCode, ForMonth);
            lITCutAmt = lObjPensionBillDAO.getTotalITCutForMonth(lStrPnsrCode, ForMonth);
            lSpecialCutAmt = lObjPensionBillDAO.getTotalSpecialCutForMonth(lStrPnsrCode, ForMonth);
            
            //	Set For Use in ROP
            inputMap.put("OldBasicAmt", lBasicPensionAmt);
            inputMap.put("PensionCutAmt", lPensionCutAmt);
            inputMap.put("IsAlive", "Y");
            
            // Getting Basic / DP / TI / MA / IR / ATI / for the given month. 
            lObjMonthlyPensionBillVO = getMasterRatesForMonth(inputMap);
            
            if(lObjMonthlyPensionBillVO != null)
            {
                if(lObjMonthlyPensionBillVO.getBasicPensionAmount() != null){
                    lBasicPensionAmt = Double.parseDouble(lObjMonthlyPensionBillVO.getBasicPensionAmount().toString());
                }
               if(lObjMonthlyPensionBillVO.getDpPercentAmount() != null){
            	   lDPPerAmt = Double.parseDouble(lObjMonthlyPensionBillVO.getDpPercentAmount().toString());
                }
                if(lObjMonthlyPensionBillVO.getTiPercent() != null){
                	lTIPerAmt = Double.parseDouble(lObjMonthlyPensionBillVO.getTiPercentAmount().toString());
                }
                if(lObjMonthlyPensionBillVO.getMedicalAllowenceAmount() != null){
                    lMAAmt = Double.valueOf(lObjMonthlyPensionBillVO.getMedicalAllowenceAmount().toString());
                }
                if(lObjMonthlyPensionBillVO.getPersonalPension() != null){
                    lPersonalPnsnAmt = Double.valueOf(lObjMonthlyPensionBillVO.getPersonalPension().toString());
                }
                if(lObjMonthlyPensionBillVO.getIr() != null){
                    lIRAmt = Double.valueOf(lObjMonthlyPensionBillVO.getIr().toString());
                }                
            }            
            
            // Net Pension Amount Calculation.
            lNetPensionAmt = (lBasicPensionAmt - lPensionCutAmt);
            lNetPensionAmt = lNetPensionAmt + lDPPerAmt + lTIPerAmt + lMAAmt + lPersonalPnsnAmt;
            lNetPensionAmt = lNetPensionAmt + lIRAmt + lOtherBenefitAmt;
            lNetPensionAmt = lNetPensionAmt - lITCutAmt;
            lNetPensionAmt = lNetPensionAmt - lSpecialCutAmt;
                        
            lNetBasicPensionAmt = lNetPensionAmt;
        }
        catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }        
        return lNetBasicPensionAmt;
    }*/
    
    
    /**
     * Calculate Net Family Pension Amount for the Given Month.
     * @author Sagar
     * @param inputMap
     * @return 
     * @throws Exception
  	*/
    /*
    private Double getNetFamilyPensionForMonth(Map inputMap) throws Exception
    {
    	ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        Double lNetFamilyPensionAmt = 0D;
        
        String lStrPnsrCode = null;
        
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
        MonthlyPensionBillVO lObjMonthlyPensionBillVO = null;
        int ForMonth = Integer.parseInt(inputMap.get("forMonth").toString());
        Date lDateOfDeath = (Date) inputMap.get("DateOfDeath");
        
        Double lBasicPensionAmt = 0D;
        Double lNetPensionAmt = 0D;
        Double lTIPerAmt = 0D;
        Double lMAAmt = 0D;
        Double lPersonalPnsnAmt = 0D;
        Double lIRAmt = 0D;
        Double lSpecialCutAmt = 0D;
        Double lOtherBenefitAmt = 0D;
        
        Date lFP1Date = null;
        Date lFP2Date = null;             
        
        try
        {
            if(lObjTrnPensionRqstHdrVO != null)
            {
                // Getting the Value from TrnPensionRqstHdr... Start...
                
                if(lObjTrnPensionRqstHdrVO.getPensionerCode() != null){
                    lStrPnsrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
                }
                if(lObjTrnPensionRqstHdrVO.getPersonalPension() != null){
                    lPersonalPnsnAmt = Double.valueOf(lObjTrnPensionRqstHdrVO.getPersonalPension().toString());
                }
                
                lFP1Date = lObjTrnPensionRqstHdrVO.getFp1Date();
                lFP2Date = lObjTrnPensionRqstHdrVO.getFp2Date();
                                
                if( lDateOfDeath != null && lDateOfDeath.toString().length() > 0 )
                {
                    if(lDateOfDeath.before(lFP1Date) || lDateOfDeath.equals(lFP1Date)){
                        lBasicPensionAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getFp1Amount().toString());
                    }
                    else if(lDateOfDeath.after(lFP2Date) || lDateOfDeath.equals(lFP2Date)){
                        lBasicPensionAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getFp2Amount().toString());
                    }
                }
            }
            
            // Set For Use in ROP
            inputMap.put("OldBasicAmt", lBasicPensionAmt);
            inputMap.put("IsAlive", "N");
            
            NewPensionBillDAO lObjPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
            
            lSpecialCutAmt = lObjPensionBillDAO.getTotalSpecialCutForMonth(lStrPnsrCode, ForMonth);
            lOtherBenefitAmt = lObjPensionBillDAO.getTotalOtherBenefitsForMonth(lStrPnsrCode, ForMonth);

            // Getting Basic / DP / TI / MA / IR / ATI / for the given month. 
            lObjMonthlyPensionBillVO = getMasterRatesForMonth(inputMap);
            
            if(lObjMonthlyPensionBillVO != null)
            {
                if(lObjMonthlyPensionBillVO.getBasicPensionAmount() != null){
                    lBasicPensionAmt = Double.parseDouble(lObjMonthlyPensionBillVO.getBasicPensionAmount().toString());
                }
                if(lObjMonthlyPensionBillVO.getTiPercent() != null){
                	lTIPerAmt = Double.parseDouble(lObjMonthlyPensionBillVO.getTiPercentAmount().toString());
                }
                if(lObjMonthlyPensionBillVO.getMedicalAllowenceAmount() != null){
                    lMAAmt = Double.valueOf(lObjMonthlyPensionBillVO.getMedicalAllowenceAmount().toString());
                }
                if(lObjMonthlyPensionBillVO.getPersonalPension() != null){
                    lPersonalPnsnAmt = Double.valueOf(lObjMonthlyPensionBillVO.getPersonalPension().toString());
                }
                if(lObjMonthlyPensionBillVO.getIr() != null){
                    lIRAmt = Double.valueOf(lObjMonthlyPensionBillVO.getIr().toString());
                }                
            }
                        
            // Net Pension Amount Calculation.
            lNetPensionAmt = lBasicPensionAmt;
            lNetPensionAmt = lNetPensionAmt + lTIPerAmt + lMAAmt + lPersonalPnsnAmt;
            lNetPensionAmt = lNetPensionAmt + lOtherBenefitAmt + lIRAmt;
            lNetPensionAmt = lNetPensionAmt - lSpecialCutAmt;
            
            lNetFamilyPensionAmt = lNetPensionAmt;
        }
        catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }        
        return lNetFamilyPensionAmt;
    }*/
    
    
    /**
     * By this Service you Getting a ROP Basic, Dp Amt, TI Amt, ATI Amt, MA Amt, IR Amt 
     * according to HeadCode and for duration . 
     * @param inputMap
     * @return {@link MonthlyPensionBillVO}
     * @throws Exception
     */
    /*
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
    	
    	String lStrIsAlive = (String) inputMap.get("IsAlive");
    	String lStrIsROPApplied = null;
    	String lStrIsSpecialCase = null;
    	
    	Long lHeadCode = 0L; 
    	
    	String ForMonth = inputMap.get("forMonth").toString();
    	
    	Date lStrForDate = null;

    	TrnPensionRqstHdr lObjPensionRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
    	NewPensionBillDAO lPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
    	RltPensionHeadcodeRate lPensionHeadcodeRateVO = null;
    	RltPensionHeadcodeSpecial lPensionHeadcodeSpecialVO = null;
    	
    	try 
    	{
    		
    		lStrForDate = lObjSmplDateFrm.parse("01/"+ForMonth.substring(4,2)+"/"+ForMonth.substring(0,4));
    		lHeadCode = Long.parseLong(lObjPensionRqstHdrVO.getHeadCode().toString());
    		lStrIsROPApplied = lObjPensionRqstHdrVO.getIsRop();
    		lStrIsSpecialCase = lObjPensionRqstHdrVO.getSpecialCase();
    		
    		// If Pensioner Alive flag is Y,then Pension Cut is calculate. 
    		if(lStrIsAlive != null && lStrIsAlive.equalsIgnoreCase("Y"))
    		{
    			lPensionCutAmt = (Double) inputMap.get("PensionCutAmt");
    		}    		
    		
    		// Old Case Basic (Basic/FP1/FP2)
    		lOldBasicAmt = (Double) inputMap.get("OldBasicAmt");
    		
			// Getting MA Amt For HeadCode
			lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "MA", lZeroAmt, lStrForDate);
			if(lPensionHeadcodeRateVO != null)
			{
				lMAAmt = Double.valueOf(lPensionHeadcodeRateVO.getMinAmount().toString());
			}
    		
    		// HeadCode is not equals to FreedomFoghter && Navniraman.
    		if(lHeadCode != 16 && lHeadCode != 18 && lHeadCode != 19)
    		{
    			// For ROP 1986.
    			if(lStrForDate != null && lStrForDate.before(lObjSmplDateFrm.parse("01/01/1996"))
    					&& lStrForDate.after(lObjSmplDateFrm.parse("01/01/1996")) ) 
    			{
    				if(lStrIsROPApplied != null && lStrIsROPApplied.equalsIgnoreCase("Y"))
    				{
    					lNewBasicAmt = getNewROPBasicAmt(inputMap);
    				}
    				else
    				{
    					lNewBasicAmt = lOldBasicAmt;
    				}
    				
    				lDPAmt = 0D;
    				
    				Double lOldTiAmt = 0D;
    				Double lNewTiAmt = 0D;
    				Double lMinAmt = 0D;
    				
    				// Calculate TI Amt For OLD Basic
    				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lOldBasicAmt, lStrForDate);
    				
    				if(lPensionHeadcodeRateVO != null)
    				{
    					lOldTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lOldBasicAmt, lPensionCutAmt);
    				}
    				
    				//	Calculate TI Amt For NEW Basic
    				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lNewBasicAmt, lStrForDate);
    				
    				if(lPensionHeadcodeRateVO != null)
    				{
    					lNewTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lNewBasicAmt, lPensionCutAmt);
    				}
    				
    				lTIAmt = lNewTiAmt;
    				lATIAmt = lNewTiAmt - lOldBasicAmt;    				
    			}
    			// For ROP 1996 onwards.
    			else if(lStrForDate != null && lStrForDate.after(lObjSmplDateFrm.parse("31/12/1996")))
    			{
    				if(lStrIsROPApplied != null && lStrIsROPApplied.equalsIgnoreCase("Y"))
    				{
    					lNewBasicAmt = getNewROPBasicAmt(inputMap);
    				}
    				else
    				{
    					lNewBasicAmt = lOldBasicAmt;
    				}
    				
    				Double lOldTiAmt = 0D;
    				Double lNewTiAmt = 0D;
    				
    				// ROP = Y and SpecialCase = Y 
    				if(lStrIsROPApplied != null && lStrIsSpecialCase != null 
    						&& lStrIsROPApplied.equalsIgnoreCase("Y") && lStrIsSpecialCase.equalsIgnoreCase("Y"))
    				{
    					lDPAmt = 0D;
    					// Getting RTI Amt from Duration.
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "RTI", lZeroAmt, lStrForDate);
        				
        				if(lPensionHeadcodeRateVO != null)
        				{
        					lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
        					lOldTiAmt = ((lOldBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
        					lNewTiAmt = ((lNewBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
        				}
    				}
    				// ROP = Y and SpecialCase = N 
    				else if(lStrIsROPApplied != null && lStrIsSpecialCase != null 
    						&& lStrIsROPApplied.equalsIgnoreCase("Y") && lStrIsSpecialCase.equalsIgnoreCase("Y"))
    				{
    					lDPAmt = 0D;
    					if(lStrForDate.after(lObjSmplDateFrm.parse("31/12/2003")))  // fordate > 1/1/2004. DP Mrg.
        				{
        					lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "DP", lZeroAmt, lStrForDate);
            				lDPPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
            				lDPAmt = ((lOldBasicAmt - lPensionCutAmt) * ((lDPPer) / 100 ));
        				}     					
    					// Getting STI Amt from Duration.
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "STI", lZeroAmt, lStrForDate);
        				if(lPensionHeadcodeRateVO != null)
        				{
        					lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
        					lOldTiAmt = (((lOldBasicAmt - lPensionCutAmt)+lDPAmt) * ((lTIPer) / 100 ));
        					lNewTiAmt = (((lNewBasicAmt - lPensionCutAmt)+lDPAmt) * ((lTIPer) / 100 ));
        				}       				
    				}
    				// ROP = N 
    				else if(lStrIsROPApplied != null && lStrIsROPApplied.equalsIgnoreCase("Y"))
    				{
    					lDPAmt = 0D;
    					// Calculate TI Amt For OLD Basic
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lOldBasicAmt, lStrForDate);
        				if(lPensionHeadcodeRateVO != null)
        				{
        					lOldTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lOldBasicAmt, lPensionCutAmt);
        				}
    					// Calculate TI Amt For NEW Basic
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lNewBasicAmt, lStrForDate);
        				if(lPensionHeadcodeRateVO != null)
        				{
        					lNewTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lNewBasicAmt, lPensionCutAmt);
        				}
        				
        				// Getting IR Amt from Duration.
        				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "IR", lZeroAmt, lStrForDate);
        				if(lPensionHeadcodeRateVO != null)
        				{
        					lOldTiAmt = getGreaterTIAmt(lPensionHeadcodeRateVO, lOldBasicAmt, lPensionCutAmt);
        				}
    				}
    				
    				lTIAmt = lNewTiAmt;
    				lATIAmt = lNewTiAmt - lOldBasicAmt;    				
    			}
    		}
    		// HeadCode is equals to Navniraman.
    		else if(lHeadCode != 18)
    		{
    			if(lStrForDate != null && lStrForDate.before(lObjSmplDateFrm.parse("01/10/2001")))
    			{
    				lNewBasicAmt = lOldBasicAmt;
    			}
    			else
    			{
    				lNewBasicAmt = lPensionBillDAO.getBasicFromHeadcodeSpecialRlt(lHeadCode, lOldBasicAmt, lStrForDate);
    			}
    		}
    		//	HeadCode is equals to FreedomFoghter
    		else if(lHeadCode != 16 && lHeadCode != 19)
    		{
    			if(lStrForDate != null && lStrForDate.before(lObjSmplDateFrm.parse("01/07/1971")))
    			{
    				lNewBasicAmt = lOldBasicAmt;
    			}
    			else if(lStrForDate != null && lStrForDate.before(lObjSmplDateFrm.parse("01/08/1998")))
    			{
    				lNewBasicAmt = lPensionBillDAO.getBasicFromHeadcodeSpecialRlt(lHeadCode, lOldBasicAmt, lStrForDate);
    			}
    			else if(lStrForDate != null && lStrForDate.after(lObjSmplDateFrm.parse("31/07/1998"))
    					&& lStrForDate.before(lObjSmplDateFrm.parse("31/07/2007")))
    			{
    				lNewBasicAmt = 1500.00;  // Fix Basic Amount for conditional Period. 
    				lDPAmt = 0D;
    				
    				// Getting TI For Duration.
    				lPensionHeadcodeRateVO = lPensionBillDAO.getRateFromHeadcodeRateRlt(lHeadCode, "TI", lZeroAmt, lStrForDate);
    				if(lPensionHeadcodeRateVO != null)
    				{
    					lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
    				}
    				
    				lTIAmt = ((lNewBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
    			}
    			else if(lStrForDate != null && lStrForDate.after(lObjSmplDateFrm.parse("01/08/2007")))
    			{
    				lNewBasicAmt = lPensionBillDAO.getBasicFromHeadcodeSpecialRlt(lHeadCode, lOldBasicAmt, lStrForDate);
    			}
    		}
    		
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
    }*/
    
    /**
     * Calculate Greater TI Amount by Per. and Minimum amount.
     * @param lPensionHeadcodeRateVO
     * @param lOldBasicAmt
     * @param lPensionCutAmt
     * @throws Exception
     * @author Sagar
	*/
    /*
    private Double getGreaterTIAmt(RltPensionHeadcodeRate lPensionHeadcodeRateVO,Double lBasicAmt,Double lPensionCutAmt) throws Exception
    {
    	Double lGreaterTIAmt = 0D;
 
		Double lMinAmt = 0D;
		Double lTIPer = 0D;
    	
    	try
    	{
    		if(lPensionHeadcodeRateVO != null)
			{
				if(lPensionHeadcodeRateVO.getMinAmount() != null) // Checking for greater TI Amount from (Per||MinAmt)
				{
					lMinAmt = Double.valueOf(lPensionHeadcodeRateVO.getMinAmount().toString());
					lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
					lGreaterTIAmt = ((lBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
					
					if(lMinAmt > lGreaterTIAmt)
					{
						lGreaterTIAmt = lMinAmt;
					}
				}
				else
				{
					lTIPer = Double.valueOf(lPensionHeadcodeRateVO.getRate().toString());
					lGreaterTIAmt = ((lBasicAmt - lPensionCutAmt) * ((lTIPer) / 100 ));
				}
			}
    	}	
		catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }
		
    	return lGreaterTIAmt;
    }*/
    
    
    /**
     * Determine a New Basic value according to current basic, Applide ROP, Retirment Date and
     * adapted type of rop86. 
     * @param inputMap
     * @return {@link Double}
     * @throws Exception
     */
    /*private Double getNewROPBasicAmt(Map inputMap)throws Exception
    {
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
    	
    	try 
    	{
    		lStrROP_1986 = (String) inputMap.get("ROP_1986");
    		lStrROP86_AdptedType = (String) inputMap.get("ROP86_AdptedType");
    		lStrROP_1996 = (String) inputMap.get("ROP_1996");
    		//lStrROP_2006 = (String) inputMap.get("ROP_2006");
    		
    		// For ROP 1986....    		
    		if(lStrROP_1986 != null && lStrROP_1986.equalsIgnoreCase("Y"))
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
    		}       		
    		//	For ROP 1986....    		
    		else if(lStrROP_1996 != null && lStrROP_1996.equalsIgnoreCase("Y")) // ROP 1996
    		{
    			lDNewBasicAmt = lObjPensionBillDAO.getNewBasicFromROPMst("1996", "0", lDOldBasicAmt);
    		}
    		/*	For ROP 2006....    		
    		else if(lStrROP_2006 != null && lStrROP_2006.equalsIgnoreCase("Y")) // ROP 1996
    		{
    			lDNewBasicAmt = 0.0;
    		}*/
    	/*
    		
    	}
        catch (Exception e)
        {
            gLogger.info("Error is :: " + e,e);
            throw(e);
        }
        
        return lDNewBasicAmt;
    }*/
    
}
