package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.NewPensionBillDAO;
import com.tcs.sgv.pension.dao.NewPensionBillDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPnsncaseRopRltDAO;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

/**
 * Pension Bill Specific VO Generator.
 * 
 * @author Sagar Patel
 * @version 1.0
 */
public class NewPensionBillVOGenerator extends ServiceImpl implements VOGeneratorService
{
	/* Global Variable for PostId */
	private Long gLngPostId = null;

	/* Global Variable for UserId */
	private Long gLngUserId = null;
	
    /* Global Variable for Current Date */
	private Date gDtCurrDt = null;

    /* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for ResourceBundle */
    private static ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    
    /* Global Variable for LAT Amount */
    private Double lLtaAmount = 0D;
    
    /**
     * Generates VO for insertion of Pension Bill Data
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject generateMap(Map lMapInput)
    { 
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        String lStrBillType = bundleConst.getString("RECOVERY.PENSION");
        String lStrStatus = bundleConst.getString("STATUS.CRTD");
        NewPensionBillDAO lObPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        TrnPensionBillDtls lObjTrnPensionBillDtls = null;
        RltPensioncaseBill lObjRltPensioncaseBill = null;
        TrnPensionRqstHdr lObjPensionRqstHdr = null;
        List<TrnPensionArrearDtls> lPensionArrearDtlsVoLst = null;
        List<TrnPnsncaseRopRlt> lPnsncaseRopVoLst = null;
        List<TrnPensionArrearDtls> lTrnPaymentLst = null;
        try{
            String lStrPPONo = StringUtility.getParameter("txtPPONO", request).trim();
            String lStrArearFlg = StringUtility.getParameter("txtArrearAmt", request).trim();
            
        	setSessionInfo(lMapInput);
        	if(lStrPPONo != null)
        	{
                // Must be Set First
                lObjTrnPensionBillHdr = generateTrnPensionBillHdrVO(lMapInput);
        		
                //  Must be Set Second
                if(lStrArearFlg != null && lStrArearFlg.length() > 0)
                {
                    lPensionArrearDtlsVoLst = generateTrnPensionArrearDtlsVO(lMapInput);                    
                }
                
                // Must be Set Third                
                lObjTrnPensionBillDtls = generateTrnPensionBillDtlsVO(lMapInput);
                                
        		lObjRltPensioncaseBill = lObPensionBillDAO.getRltPensioncaseBillPK(lStrPPONo,lStrBillType,lStrStatus); 
        		
                /// Fetching & Remove Attribuites From session
                HttpSession session = request.getSession();
            	lTrnPaymentLst = (List<TrnPensionArrearDtls>) session.getAttribute("TrnPaymentLst"); 
            	
            	if(lTrnPaymentLst != null && lTrnPaymentLst.size() > 0)
            	{
            		lTrnPaymentLst = generateTrnPaymentArrearDtlsVO(lTrnPaymentLst);
            	}
            	session.removeAttribute("TrnPaymentLst");
        		
                lMapInput.put("RltPensnBillPK", lObjRltPensioncaseBill.getRltPensioncaseBillId());
                lMapInput.put("TrnPensionBillHdrVO", lObjTrnPensionBillHdr);
        		lMapInput.put("TrnPensionBillDtlsVO", lObjTrnPensionBillDtls);
                lMapInput.put("TrnPensionArrearDtlsVoLst", lPensionArrearDtlsVoLst);
                lMapInput.put("TrnPaymentLst", lTrnPaymentLst);
        	}      	
        	lMapInput.put("PPONO", lStrPPONo);
            objRes.setResultValue(lMapInput);
        }
        catch(Exception e)
        {   
        	gLogger.error("Error is : ", e);
        	objRes.setResultValue(null);
        	objRes.setThrowable(e);
        	objRes.setResultCode(ErrorConstants.ERROR);
        	objRes.setViewName("errorPage");           
        }
        return objRes;
    }
    
    /**
     * Generate TrnPensionBillHdrVO from JSP Data
     * @param lMapInput
     * @return TrnPensionBillHdr
     */
    public TrnPensionBillHdr generateTrnPensionBillHdrVO(Map lMapInput) throws Exception
    {
    	HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        TrnPensionBillHdr lObjTrnPensionBillHdr = new TrnPensionBillHdr();

        try 
        {   
            setSessionInfo(lMapInput);
        
            lObjTrnPensionBillHdr.setBillDate(gDtCurrDt);
            lObjTrnPensionBillHdr.setForMonth(Integer.valueOf(new SimpleDateFormat("yyyyMM").format(gDtCurrDt)));
            
            lObjTrnPensionBillHdr.setCreatedDate(gDtCurrDt);
            lObjTrnPensionBillHdr.setCreatedUserId(new BigDecimal(gLngUserId));
            lObjTrnPensionBillHdr.setCreatedPostId(new BigDecimal(gLngPostId));
            if(StringUtility.getParameter("hidHeadcode", request).trim() != "")
        	{
            	lObjTrnPensionBillHdr.setHeadCode(new BigDecimal(StringUtility.getParameter("hidHeadcode", request).trim()));
        	}
        	if(StringUtility.getParameter("hidScheme", request).trim() != "")
        	{
        		lObjTrnPensionBillHdr.setScheme(StringUtility.getParameter("hidScheme", request).trim());
        	}
            lObjTrnPensionBillHdr.setTrnCounter(1);
        } 
        catch (Exception e) 
        {
            gLogger.error("Error in " + e,e);
            throw(e);
        }
        return lObjTrnPensionBillHdr;
    }
    
    /**
     * Generate TrnPensionBillDtlsVO from JSP Data
     * @param lMapInput
     * @return TrnPensionBillDtls
     */
    public TrnPensionBillDtls generateTrnPensionBillDtlsVO(Map lMapInput) throws Exception
    {
    	HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        
        TrnPensionBillDtls lObjTrnPensionBillDtls = new TrnPensionBillDtls();
                
        String lStrPPONo = StringUtility.getParameter("hidDPPONO", request).trim();
        String lStrPensionerCode = StringUtility.getParameter("hidPensionerCode", request).trim();
        String lStrAliveFlg = StringUtility.getParameter("hidAliveFlg", request).trim();
                
        String lStrBasicPension = StringUtility.getParameter("txtBasicPension", request).trim();
        String lStrITCutAmt = StringUtility.getParameter("txtITCutAmt", request).trim();
        String lStrPensionCut = StringUtility.getParameter("txtPensionCut", request).trim();
        //String lStrDPPercent = StringUtility.getParameter("hidDPPercent", request).trim();
        //String lStrTIPercent = StringUtility.getParameter("hidTIPercent", request).trim();
        String lStrDPAmt = StringUtility.getParameter("txtDPPercentAmt", request).trim();
        String lStrTIAmt = StringUtility.getParameter("txtTIPercentAmt", request).trim();
        String lStrMAAmt = StringUtility.getParameter("txtMAAmt", request).trim();
        String lStrPersonalPnsnAmt = StringUtility.getParameter("txtPersonalPnsnAmt", request).trim();
        String lStrIRAmt = StringUtility.getParameter("txtIRAmt", request).trim();
        String lStrOtherBenefitAmt = StringUtility.getParameter("txtOtherBenefitAmt", request).trim();
        String lStrSpecialCutAmt = StringUtility.getParameter("txtSpecialCutAmt", request).trim();
        String lStrArearAmt =StringUtility.getParameter("txtArrearAmt", request); 
        String lStrRecovery = StringUtility.getParameter("txtPnsnRecovery", request).trim();
        String lStrNetPensionAmt = StringUtility.getParameter("txtNetPensionAmt", request).trim();
        String lStrCVPMonthly = StringUtility.getParameter("txtCvpMonthlyAmt", request).trim();
        
        String lStrOMRAmt = StringUtility.getParameter("hidOMRAmt", request).trim();
        String lStrTIArrearAmt = StringUtility.getParameter("txtTIArrearAmt", request).trim();
        String lStrPrvTIArrearAmt = StringUtility.getParameter("hidTotPrvTIArrearAmt", request).trim();
        
        Double lTIArreaAmt = 0D;
        
        try 
        {	
        	setSessionInfo(lMapInput);
        
        	lObjTrnPensionBillDtls.setPpoNo(lStrPPONo);
        	lObjTrnPensionBillDtls.setPensionerCode(lStrPensionerCode);
        	
        	lObjTrnPensionBillDtls.setPensionAmount(new BigDecimal(lStrBasicPension.length() != 0 ? lStrBasicPension : "0"));
        	lObjTrnPensionBillDtls.setIncomeTaxCutAmount(new BigDecimal(lStrITCutAmt.length() != 0 ? lStrITCutAmt : "0"));
        	lObjTrnPensionBillDtls.setPensnCutAmount(new BigDecimal(lStrPensionCut.length() != 0 ? lStrPensionCut : "0"));
            lObjTrnPensionBillDtls.setSpecialCutAmount(new BigDecimal(lStrSpecialCutAmt.length() != 0 ? lStrSpecialCutAmt : "0"));
            lObjTrnPensionBillDtls.setCvpMonthAmount(new BigDecimal(lStrCVPMonthly.length() != 0 ? lStrCVPMonthly : "0"));
        	lObjTrnPensionBillDtls.setDpAmount(new BigDecimal(lStrDPAmt.length() != 0 ? lStrDPAmt : "0"));
        	lObjTrnPensionBillDtls.setTiAmount(new BigDecimal(lStrTIAmt.length() != 0 ? lStrTIAmt: "0"));
        	lObjTrnPensionBillDtls.setMedicalAllowenceAmount(new BigDecimal(lStrMAAmt.length() != 0 ? lStrMAAmt : "0"));
            lObjTrnPensionBillDtls.setPersonalPensionAmount(new BigDecimal(lStrPersonalPnsnAmt.length() != 0 ? lStrPersonalPnsnAmt : "0"));
            lObjTrnPensionBillDtls.setIrAmount(new BigDecimal(lStrIRAmt.length() != 0 ? lStrIRAmt : "0"));
            lObjTrnPensionBillDtls.setOtherBenefits(new BigDecimal(lStrOtherBenefitAmt.length() != 0 ? lStrOtherBenefitAmt : "0"));
            lObjTrnPensionBillDtls.setArrearAmount(new BigDecimal(lStrArearAmt.length() != 0 ? lStrArearAmt : "0"));
        	lObjTrnPensionBillDtls.setRecoveryAmount(new BigDecimal(lStrRecovery.length() != 0 ? lStrRecovery : "0"));
        	lObjTrnPensionBillDtls.setReducedPension(new BigDecimal(lStrNetPensionAmt.length() != 0 ? lStrNetPensionAmt : "0"));
            
            // Set OMR Amount
            lObjTrnPensionBillDtls.setOmr(new BigDecimal(lStrOMRAmt.length() != 0 ? lStrOMRAmt : "0"));
            
            // Set TI Arrear Amount.
            if(lStrTIArrearAmt != null && lStrTIArrearAmt.length() > 0 )
            {
                lTIArreaAmt += Double.valueOf(lStrTIArrearAmt);
            }
            if(lStrPrvTIArrearAmt != null && lStrPrvTIArrearAmt.length() > 0 )
            {
                lTIArreaAmt += Double.valueOf(lStrPrvTIArrearAmt);
            }
            lObjTrnPensionBillDtls.setTiArrearAmount(new BigDecimal(lTIArreaAmt != 0 ? lTIArreaAmt.toString() : "0"));
            
            // set LTA Amount.
            if(lStrAliveFlg != null && lStrAliveFlg.equalsIgnoreCase("N"))
            {
                lObjTrnPensionBillDtls.setLta(new BigDecimal(lLtaAmount != 0 ? lLtaAmount.toString() : "0"));
            }
            lObjTrnPensionBillDtls.setTrnCounter(1);
            
		} 
        catch (Exception e) 
		{
        	gLogger.error("Error in" + e,e);
            throw(e);
		}
    	
    	return lObjTrnPensionBillDtls;
    }
    
    /**
     * Generate TrnPensionBillDtlsVO from JSP Data
     * @param lMapInput
     * @return TrnPensionBillDtls
     */
    public List<TrnPensionArrearDtls> generateTrnPensionArrearDtlsVO(Map lMapInput) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        
        List<TrnPensionArrearDtls> lPensionArrearDtlsVoLst = new ArrayList<TrnPensionArrearDtls>();
        TrnPensionArrearDtls lArrearDtlsVo = null;
        
        String[] lStrArrearFieldType = StringUtility.getParameterValues("hidArrFieldType", request);
        String[] lStrArrearWEF = StringUtility.getParameterValues("hidArrWEF", request);
        String[] lStrArrearDiffAmt = StringUtility.getParameterValues("hidArrDiffAmt", request);
        String[] lStrPrvArrAmt = StringUtility.getParameterValues("hidPrvArrAmt", request);

        String lStrTrnPnsnRqstID = StringUtility.getParameter("hidTrnPensionRqstID", request).trim();
        String lStrPnsnerCode = StringUtility.getParameter("hidPensionerCode", request).trim();
        
        String lStrArrPnsn = bundleConst.getString("ARREAR.PENSION");
        
        try 
        {   
            setSessionInfo(lMapInput);
            for(int i=0;i<lStrArrearFieldType.length;i++)
            {
                // Arrear For Pension
                lArrearDtlsVo = new TrnPensionArrearDtls();
                
                lArrearDtlsVo.setPensionRequestId(Long.parseLong(lStrTrnPnsnRqstID));
                lArrearDtlsVo.setPensionerCode(lStrPnsnerCode);
                lArrearDtlsVo.setArrearFieldType(lStrArrearFieldType[i]);
                
                lArrearDtlsVo.setOldAmountPercentage(new BigDecimal("0.00"));
                lArrearDtlsVo.setNewAmountPercentage(new BigDecimal(lStrArrearDiffAmt[i].length() != 0 ? lStrArrearDiffAmt[i] : "0.00"));
                                    
                lArrearDtlsVo.setEffectedFromYyyymm(Integer.parseInt(lStrArrearWEF[i]));
                lArrearDtlsVo.setEffectedToYyyymm(Integer.parseInt(lStrArrearWEF[i]));
                
                lArrearDtlsVo.setDifferenceAmount(new BigDecimal(lStrArrearDiffAmt[i].length() != 0 ? lStrArrearDiffAmt[i] : "0.00"));
                lArrearDtlsVo.setTotalDifferenceAmt(new BigDecimal(lStrArrearDiffAmt[i].length() != 0 ? lStrArrearDiffAmt[i] : "0.00"));
                
                lArrearDtlsVo.setPaymentFromYyyymm(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(gDtCurrDt)));
                lArrearDtlsVo.setPaymentToYyyymm(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(gDtCurrDt)));
                
                lArrearDtlsVo.setCreatedUserId(new BigDecimal(gLngUserId));
                lArrearDtlsVo.setCreatedPostId(new BigDecimal(gLngPostId));
                lArrearDtlsVo.setCreatedDate(gDtCurrDt);
                
                /* Logic add For Calculate a LTA */
                if(lStrArrearFieldType[i].equalsIgnoreCase(lStrArrPnsn))
                {
                    lLtaAmount += Double.valueOf(lStrArrearDiffAmt[i].toString()) + Double.valueOf((lStrPrvArrAmt[i].length() != 0 ? lStrPrvArrAmt[i].toString() : "0.00"));
                                /*   Month vise NetAmount paid as Arrear + That Month having any other Arrear */ 
                }
                
                lPensionArrearDtlsVoLst.add(lArrearDtlsVo);
            }
            
        } 
        catch (Exception e) 
        {
            gLogger.error("Error in" + e,e);
            throw(e);
        }
        return lPensionArrearDtlsVoLst;
    }
    
    /**
     * Generate TrnPensionBillDtlsVO from JSP Data
     * @param lMapInput
     * @return TrnPensionBillDtls
     */
    public List<TrnPensionArrearDtls> generateTrnPaymentArrearDtlsVO(List<TrnPensionArrearDtls> lTrnPaymentLst) throws Exception
    {
      
        List<TrnPensionArrearDtls> lPensionArrearDtlsVoLst = new ArrayList<TrnPensionArrearDtls>();
        TrnPensionArrearDtls lArrearDtlsVo = null;
        
        Double lDiifAmt = 0D;
        Integer lTrnForMnth = 0;
        Integer lTrnPayMnth = 0;
        
        try 
        {   
            for(int i=0;i<lTrnPaymentLst.size();i++)
            {
                // Arrear For Pension
                lArrearDtlsVo = new TrnPensionArrearDtls();
                lArrearDtlsVo = lTrnPaymentLst.get(i);
                
                lDiifAmt = Double.valueOf(lArrearDtlsVo.getDifferenceAmount().toString());
                lTrnForMnth = lArrearDtlsVo.getEffectedFromYyyymm();
                lTrnPayMnth = lArrearDtlsVo.getPaymentFromYyyymm();
                
                lArrearDtlsVo.setOldAmountPercentage(new BigDecimal("0.00"));
                lArrearDtlsVo.setNewAmountPercentage(new BigDecimal(lDiifAmt != 0 ? lDiifAmt.toString() : "0.00"));
                lArrearDtlsVo.setEffectedToYyyymm(lTrnForMnth);
                lArrearDtlsVo.setDifferenceAmount(new BigDecimal(lDiifAmt != 0 ? lDiifAmt.toString() : "0.00"));
                lArrearDtlsVo.setTotalDifferenceAmt(new BigDecimal(lDiifAmt != 0 ? lDiifAmt.toString() : "0.00"));
                lArrearDtlsVo.setPaymentToYyyymm(lTrnPayMnth);

                lPensionArrearDtlsVoLst.add(lArrearDtlsVo);
            }
            
        } 
        catch (Exception e) 
        {
            gLogger.error("Error in" + e,e);
            throw(e);
        }
        return lPensionArrearDtlsVoLst;
    }

    
    /**
     * Generate TrnPensionRqstHdrVO
     * @param lMapInput
     * @return TrnPensionRqstHdr
     */
    public TrnPensionRqstHdr generatePensionRqstHdrVO(Map lMapInput) throws Exception
    {
    	HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
    	ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        
    	TrnPensionRqstHdrDAO lObjPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,srvcLoc.getSessionFactory());
        TrnPensionRqstHdr lObjPensionRqstHdrVo = null;
    	
    	String lStrTrnPnsnRqstID = StringUtility.getParameter("hidTrnPensionRqstID", request).trim();
    	
    	String lNewROPBasic = StringUtility.getParameter("hidNewROPBasic", request).trim();
    	String lNewROPFP1 = StringUtility.getParameter("hidNewROPFP1", request).trim();
    	String lNewROPFP2 = StringUtility.getParameter("hidNewROPFP2", request).trim();

        try 
        {   
            lObjPensionRqstHdrVo = lObjPensionRqstHdrDAO.read(Long.valueOf(lStrTrnPnsnRqstID));
            
            lObjPensionRqstHdrVo.setBasicPensionAmount(new BigDecimal(lNewROPBasic.length() != 0 ? lNewROPBasic : "0.00"));
            lObjPensionRqstHdrVo.setFp1Amount(new BigDecimal(lNewROPFP1.length() != 0 ? lNewROPFP1 : "0.00"));
            lObjPensionRqstHdrVo.setFp2Amount(new BigDecimal(lNewROPFP2.length() != 0 ? lNewROPFP2 : "0.00"));
                        
        } 
        catch (Exception e) 
        {
            gLogger.error("Error in " + e,e);
            throw(e);
        }
        return lObjPensionRqstHdrVo;
    }
    
    private void setSessionInfo(Map lMapInput)
    {
        gLngPostId = SessionHelper.getPostId(lMapInput);
        gLngUserId = SessionHelper.getUserId(lMapInput);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
    
}