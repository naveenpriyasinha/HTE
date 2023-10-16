package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class MonthlyPensionBillVOGenerator extends ServiceImpl implements VOGeneratorService{

	/* Global Variable for PostId */
	private Long gLngPostId = null;

	/* Global Variable for UserId */
	private Long gLngUserId = null;	 

    /* Global Variable for Current Date */
	private Date gDtCurrDt = null;

    /* Global Variable for Logger Class */
	private Log gLogger = LogFactory.getLog(getClass());
    
    /**
     * It generates Monthly Pension Bill Specific VOs.
     *  
     * @param objectArgs
     *            ServiceMap
     * @return objRes ResultObject
     */
   
    public ResultObject generateMap(Map objArgs)
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        try{
        	setSessionInfo(objArgs);
            
        	TrnPensionBillHdr lObjPensionBillHdr = generateTrnPensionBillHdr(objArgs);
        	objArgs.put("TrnPensionBillHdrId", lObjPensionBillHdr.getTrnPensionBillHdrId());
        	
            List<TrnPensionBillDtls> lLstPensionBillDtls = generateTrnPensionBillDtls(objArgs);
            List<TrnPensionArrearDtls> lLstArrearDtls = generateTrnPensionArrearDtls(objArgs);
            
            objArgs.put("TrnPensionBillHdr", lObjPensionBillHdr);
            objArgs.put("TrnPensionBillDtls", lLstPensionBillDtls);
            objArgs.put("TrnPensionArrearDtls", lLstArrearDtls);
            objRes.setResultValue(objArgs);
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
    
    private TrnPensionBillHdr generateTrnPensionBillHdr(Map lMapInput) throws Exception
    {
    	TrnPensionBillHdr lObjPensionBillHdr = new TrnPensionBillHdr();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");  
        String lStrTokenNo = null;
        try
        {
    		String lStrBillNo=lMapInput.get("billNo").toString();
    		if(lMapInput.get("PnsnTokenNo") != null)
    		{
    			lStrTokenNo = lMapInput.get("PnsnTokenNo").toString();
    		}
    		String lStrBillType=bundleConst.getString("RECOVERY.MONTHLY");
    		String lStrForMonth = StringUtility.getParameter("hidforMonth", request).trim();
    		String  lStrHeadCode = StringUtility.getParameter("hidHeadCode", request).trim();
    		String  lStrBank = StringUtility.getParameter("hidBank", request).trim();
    		String  lStrBranch = StringUtility.getParameter("hidBranch", request).trim();
    		String  lStrScheme = StringUtility.getParameter("hidScheme", request).trim();
    		
    	   lObjPensionBillHdr.setTrnPensionBillHdrId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", lMapInput)));
           lObjPensionBillHdr.setBillType(lStrBillType);
           lObjPensionBillHdr.setBillNo(lStrBillNo);
           if(lStrTokenNo != null)
           {
        	   lObjPensionBillHdr.setTokenNo(lStrTokenNo);
           }
           lObjPensionBillHdr.setBillDate(gDtCurrDt);
           lObjPensionBillHdr.setCreatedUserId(new BigDecimal(gLngUserId));
           lObjPensionBillHdr.setCreatedPostId(new BigDecimal(gLngPostId));
           lObjPensionBillHdr.setCreatedDate(new Date(System.currentTimeMillis()));
           lObjPensionBillHdr.setHeadCode(new BigDecimal(lStrHeadCode));
           lObjPensionBillHdr.setForMonth(Integer.parseInt(lStrForMonth));
           lObjPensionBillHdr.setBankCode(lStrBank);
           lObjPensionBillHdr.setBranchCode(lStrBranch);
           lObjPensionBillHdr.setScheme(lStrScheme);
           lObjPensionBillHdr.setTrnCounter(1);
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            throw(e);
        }
        return lObjPensionBillHdr;
    }

    /**
     * It generates Pension Bill Specific TrnPensionBillDtls VO.
     *  
     * @param lMapInput
     *            InputMap.
     * @return lLstTrnPensionBillDtls List<TrnPensionBillDtls>
     * @author Aparna
     */
    private List<TrnPensionBillDtls> generateTrnPensionBillDtls(Map lMapInput) throws Exception
    {
        List<TrnPensionBillDtls> lLstTrnPensionBillDtls = new ArrayList<TrnPensionBillDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants"); 
        String lStrStatus = bundleConst.getString("STATUS.APPROVED");
        
        String[] lStrPPONo = null;
        String[] lStrPnsnrName = null;
        String[] lStrAccNo = null;
        String[] lStrBasic = null;
        String[] lStrDPAmt = null;
        String[] lStrTIAmt = null;
        String[] lStrMAAmt = null;
        String[] lStrCVPMonthly = null;
        String[] lStrTIArrears = null;
        String[] lStrOtherArrears = null;
        String[] lStrItCut = null;
        String[] lStrSpecialCut = null;
        String[] lStrOtherBenefit = null;
        String[] lStrOMR = null;
        String[] lStrRecovery = null;
        String[] lStrPensionCut = null;
        String[] lStrNetAmt = null;
        String[] lStrAllnBf1156 = null;
        String[] lStrAllnAf1156 = null;
        String[] lStrAllnAf1560 = null;
        String[] lStrPerPension = null;
        String[] lStrIRAmount = null;
        String[] lStrMOComm = null;
                
        TrnPensionBillDtls lObjPensionBillDtls = null;
        
        String lTrnPensionBillHdrId = lMapInput.get("TrnPensionBillHdrId").toString();
        
        try
        {
        	List lLstPensionCodeId=null; 
        	
        	lStrPPONo = StringUtility.getParameterValues("hidPpoNo", request);
        	lStrPnsnrName = StringUtility.getParameterValues("hidpensionerName", request);
        	lStrAccNo = StringUtility.getParameterValues("hidaccountNo", request);
        	lStrBasic = StringUtility.getParameterValues("hidbasicPensionAmount", request);
        	lStrDPAmt = StringUtility.getParameterValues("hiddpPercentAmount", request);
        	lStrTIAmt = StringUtility.getParameterValues("hidtiPercentAmount", request);
        	lStrMAAmt = StringUtility.getParameterValues("hidmedicalAllowenceAmount", request);
        	lStrCVPMonthly = StringUtility.getParameterValues("hidcvpMonthlyAmount", request);
        	lStrTIArrears = StringUtility.getParameterValues("hidtiarrearsAmount", request);
        	lStrOtherArrears = StringUtility.getParameterValues("hidotherarrearsAmount", request);
        	lStrItCut = StringUtility.getParameterValues("hiditCutAmount", request);
        	lStrSpecialCut = StringUtility.getParameterValues("hidspecialCutAmount", request);
        	lStrOtherBenefit = StringUtility.getParameterValues("hidotherBenefit", request);
        	lStrOMR = StringUtility.getParameterValues("hidOMR", request);
        	lStrRecovery = StringUtility.getParameterValues("hidrecoveryAmount", request);
        	lStrPensionCut = StringUtility.getParameterValues("hidpensionCutAmount", request);
        	lStrNetAmt = StringUtility.getParameterValues("hidnetPensionAmount", request);
        	lStrAllnBf1156 = StringUtility.getParameterValues("hidallnBf11156", request);
        	lStrAllnAf1156 = StringUtility.getParameterValues("hidallnAf11156", request);
        	lStrAllnAf1560 = StringUtility.getParameterValues("hidallnAf10560", request);
        	lStrPerPension = StringUtility.getParameterValues("hidpersonalPension", request);
        	lStrIRAmount = StringUtility.getParameterValues("hidIRAmount", request);
        	lStrMOComm = StringUtility.getParameterValues("hidMOComm", request);
        	
            String lStrPnsnrCode = null;
            Object[] lObjData = null;
            for (Integer x = 0; x < (lStrPPONo.length) ; x++)
            {
            	lObjPensionBillDtls = new TrnPensionBillDtls();
            	
            	TrnPensionRqstHdrDAO lObjPensionBillDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, servLoc.getSessionFactory());
            	lLstPensionCodeId = lObjPensionBillDAO.getPksForTrnPensionRqstHdr(lStrStatus,lStrPPONo[x].trim());
 	            
 	            if(lLstPensionCodeId != null && !lLstPensionCodeId.isEmpty())
 	            {
 	            	lObjData = (Object[]) lLstPensionCodeId.get(0);	            	
 	            	lStrPnsnrCode = lObjData[1].toString();	        	
 	            }
            	
 	           //lObjPensionBillDtls.setPensionRequestId(new Long(lLngPensionReqId));
 	           lObjPensionBillDtls.setPpoNo(lStrPPONo[x].trim());
 	           lObjPensionBillDtls.setPensionerCode(lStrPnsnrCode);
 	           lObjPensionBillDtls.setIncomeTaxCutAmount(new BigDecimal(lStrItCut[x].trim()));
 	           lObjPensionBillDtls.setPensnCutAmount(new BigDecimal(lStrPensionCut[x].trim()));
 	           lObjPensionBillDtls.setSpecialCutAmount(new BigDecimal(lStrSpecialCut[x].trim()));
 	           lObjPensionBillDtls.setCvpMonthAmount(new BigDecimal(lStrCVPMonthly[x].trim()));
 	           lObjPensionBillDtls.setPensionAmount(new BigDecimal(lStrBasic[x].trim()));
 	           lObjPensionBillDtls.setDpAmount(new BigDecimal(lStrDPAmt[x].trim()));
 	           lObjPensionBillDtls.setTiAmount(new BigDecimal(lStrTIAmt[x].trim()));
        	   lObjPensionBillDtls.setMedicalAllowenceAmount(new BigDecimal(lStrMAAmt[x].trim()));
        	   lObjPensionBillDtls.setTiArrearAmount(new BigDecimal(lStrTIArrears[x].trim()));
        	   lObjPensionBillDtls.setArrearAmount(new BigDecimal(lStrOtherArrears[x].trim()));
 	           lObjPensionBillDtls.setRecoveryAmount(new BigDecimal(lStrRecovery[x].trim()));
 	           lObjPensionBillDtls.setReducedPension(new BigDecimal(lStrNetAmt[x].trim()));
 	           lObjPensionBillDtls.setAccountNo(lStrAccNo[x].trim());
 	           lObjPensionBillDtls.setTrnPensionBillHdrId(new Long(lTrnPensionBillHdrId));
 	           lObjPensionBillDtls.setPensionerName(lStrPnsnrName[x].trim());
 	           lObjPensionBillDtls.setAllcationBf11156(new BigDecimal(lStrAllnBf1156[x].trim()));
 	           lObjPensionBillDtls.setAllcationAf11156(new BigDecimal(lStrAllnAf1156[x].trim()));
 	           lObjPensionBillDtls.setAllcationAf10560(new BigDecimal(lStrAllnAf1560[x].trim()));
 	           lObjPensionBillDtls.setPersonalPensionAmount(new BigDecimal(lStrPerPension[x].trim()));
 	           lObjPensionBillDtls.setIrAmount(new BigDecimal(lStrIRAmount[x].trim()));
 	           lObjPensionBillDtls.setMoCommission(new BigDecimal(lStrMOComm[x].trim()));
 	           lObjPensionBillDtls.setOtherBenefits(new BigDecimal(lStrOtherBenefit[x].trim()));
 	           lObjPensionBillDtls.setOmr(new BigDecimal(lStrOMR[x].trim()));
              lObjPensionBillDtls.setTrnCounter(1);
	           lLstTrnPensionBillDtls.add(lObjPensionBillDtls);       	
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            gLogger.error(" Error is : " + e, e);
            throw(e);
        }
        return lLstTrnPensionBillDtls;
    }

    private List<TrnPensionArrearDtls> generateTrnPensionArrearDtls(Map inputMap) throws Exception
    {
    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

        String[] lStrPenReqId = null;
        String[] lStrPenCode = null;
        String[] lStrArrType = null;
        String[] lStrEffTo = null;
        String[] lStrEffFrom = null;
        String[] lStrDiffAmt = null;
        String lStrDate = null;
    	List<TrnPensionArrearDtls> lLstArrearDtls = new ArrayList<TrnPensionArrearDtls>();
    	TrnPensionArrearDtls lObjArrearVO = new TrnPensionArrearDtls();
    	try
    	{
    		lStrPenReqId = StringUtility.getParameterValues("hidPenReqId", request);
    		lStrPenCode = StringUtility.getParameterValues("hidPenCode", request);
    		lStrArrType = StringUtility.getParameterValues("hidArrType", request);
    		lStrEffFrom = StringUtility.getParameterValues("hidEffFrom", request);
    		lStrEffTo = StringUtility.getParameterValues("hidEffTo", request);
        	lStrDiffAmt = StringUtility.getParameterValues("hidDiffAmt", request);
        	lStrDate = StringUtility.getParameter("hidforMonth", request);
        	
             for (Integer x = 0; x < (lStrDiffAmt.length) ; x++)
             {
            	 if(Double.valueOf(lStrDiffAmt[x].trim()) != 0D)
            	 {
            		 lObjArrearVO = new TrnPensionArrearDtls();

     	  			lObjArrearVO.setPensionRequestId(Long.parseLong(lStrPenReqId[x].trim()));
     	  			lObjArrearVO.setPensionerCode(lStrPenCode[x].trim());
     	  			lObjArrearVO.setArrearFieldType(lStrArrType[x].trim());
     	  			lObjArrearVO.setOldAmountPercentage(new BigDecimal("0.00"));
     	  			lObjArrearVO.setNewAmountPercentage(new BigDecimal(lStrDiffAmt[x].trim()));
     	  			lObjArrearVO.setEffectedFromYyyymm(Integer.valueOf(lStrEffFrom[x].trim()));
     	  			lObjArrearVO.setEffectedToYyyymm(Integer.parseInt(lStrEffTo[x].trim()));
     	  			lObjArrearVO.setDifferenceAmount(new BigDecimal(lStrDiffAmt[x].trim()));
     	  			lObjArrearVO.setTotalDifferenceAmt(new BigDecimal(lStrDiffAmt[x].trim()));
     	  			lObjArrearVO.setPaymentFromYyyymm(Integer.parseInt(lStrDate));
     	  			lObjArrearVO.setPaymentToYyyymm(Integer.parseInt(lStrDate));
     	  			lObjArrearVO.setCreatedDate(gDtCurrDt);
     	  			lObjArrearVO.setCreatedPostId(new BigDecimal(gLngPostId));
     	  			lObjArrearVO.setCreatedUserId(new BigDecimal(gLngUserId));
       			
     	  			lLstArrearDtls.add(lObjArrearVO);
            	 }
             }
    	}
    	catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            throw(e);
        }
    	return lLstArrearDtls;
    }
    
    
    private void setSessionInfo(Map inputMap) throws Exception
    {
    	try{
            gLngPostId = SessionHelper.getPostId(inputMap);
            gLngUserId = SessionHelper.getUserId(inputMap);
            gDtCurrDt = DBUtility.getCurrentDateFromDB();
            
    	}
    	catch (Exception e)
        {
        	gLogger.error(" Error is: " + e,e);
        	throw(e);
        }
    }
}
