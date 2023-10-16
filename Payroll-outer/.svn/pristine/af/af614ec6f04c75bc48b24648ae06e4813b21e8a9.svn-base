package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstEdp;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRecoveryDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionRecoveryDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;


/**
 * Recovery Service Implementation.
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public class RecoveryServiceImpl extends ServiceImpl implements RecoveryService
{
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
    Date gDtCurrDt = null;

    /* Global Variable for EmpId */
    Long gLngEmpId = null;

    /* Global Variable for Location Code */
    String gStrLocationCode = null;

    /* Global Variable for FinancialyearId */
    long gLngFinYearId = 0;


    /**
     * Shows Recovery Details
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject getRecoveryDtls(Map inputMap)
    {
        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

       
        String lStrPensionerCode = "";
        String lStrPPONumber = "";
        String lStrRecoveryFromFlagCVP = bundleConst.getString("RECOVERY.CVP");
        String lStrRecoveryFromFlagDCRG = bundleConst.getString("RECOVERY.DCRG");
        String lStrRecoveryFromFlagMonthly = bundleConst.getString("RECOVERY.MONTHLY");
        String lStrRecoveryFromFlagPension = bundleConst.getString("RECOVERY.PENSION");
        String lStrRecoveryFromFlagChallan = bundleConst.getString("RECOVERY.CHALLAN");
        String lStrRECOTYPE = bundleConst.getString("LOOKUP.RECO_TYPE");
        String lStrDEDUCTIONTYPE = bundleConst.getString("LOOKUP.DEDUCTION_TYPE");
        
        Long lLngPensionRequestId = null;

        String lStrLangId = SessionHelper.getLangId(inputMap).toString();
        setSessionInfo(inputMap);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currDate = sdf.format(gDtCurrDt);

        String currentMonth = currDate.substring(5, 7);
        String currentYear = currDate.substring(0, 4);

        inputMap.put("CurrentMonth", currentMonth);
        inputMap.put("CurrentYear", currentYear);
        
        TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
        TrnPensionRecoveryDtlsDAO lObjTrnPensionRecoveryDtlsDAO = new TrnPensionRecoveryDtlsDAOImpl(TrnPensionRecoveryDtls.class,servLoc.getSessionFactory());
        TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class,servLoc.getSessionFactory());
        
        try
        {
            // Retrieving VO for table Trn_Pension_Rqst_Hdr.....
            long lBDPenReqId= Long.parseLong(StringUtility.getParameter("pension_request_id",request).trim());    
            String lStrPnsnrcode = StringUtility.getParameter("pensioner_code",request).trim();
            
            TrnPensionRqstHdr lObjTrnPensionRqstHdr = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrVO(lBDPenReqId,lStrPnsnrcode);

            if (lObjTrnPensionRqstHdr != null)
            {            	
            	BigDecimal lDBDPPercent = lObjTrnPensionRqstHdr.getDpPercent();
            	double lDoubleDPPercent = 0.0;
            	
            	if(lDBDPPercent != null)
            	{
                	 lDoubleDPPercent = Double.parseDouble(lObjTrnPensionRqstHdr.getDpPercent().toString());

            	}

            	double lDBBasicPensionAmount = Double.parseDouble(lObjTrnPensionRqstHdr.getBasicPensionAmount().toString());
            	double lDBPensionAmount = (lDBBasicPensionAmount + ((lDBBasicPensionAmount * lDoubleDPPercent)/100.00));
            	
            	BigDecimal lBDPensionAmount = new BigDecimal(lDBPensionAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
            	
            	inputMap.put("PensionAmount", lBDPensionAmount);
                inputMap.put("TrnPensionRqstHdrVO", lObjTrnPensionRqstHdr);
            }

            lStrPensionerCode = lObjTrnPensionRqstHdr.getPensionerCode();
            lStrPPONumber = lObjTrnPensionRqstHdr.getPpoNo();
            lLngPensionRequestId = lObjTrnPensionRqstHdr.getPensionRequestId();
            
            
			TrnPensionRqstHdr lObjTrnPensionRqstHdrNew = new TrnPensionRqstHdr();
			
			lObjTrnPensionRqstHdrNew = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrVO(lLngPensionRequestId,lStrPensionerCode);
			
			if(lObjTrnPensionRqstHdrNew != null)
			{	
				if("BILLCRTD".equals(lObjTrnPensionRqstHdrNew.getApproveStatus()))
				{
					inputMap.put("Disable","true");
				}
				else
				{
					inputMap.put("Disable","false");
				}
			}

            // Retrieving Arrays of VOs for table Trn_Pension_Recovery_Dtls.....
            ArrayList<TrnPensionRecoveryDtls> lObjTrnPensionRecoveryDtlsArrayForCVP = new ArrayList<TrnPensionRecoveryDtls>();
            ArrayList<TrnPensionRecoveryDtls> lObjTrnPensionRecoveryDtlsArrayForDCRG = new ArrayList<TrnPensionRecoveryDtls>();
            ArrayList<TrnPensionRecoveryDtls> lObjTrnPensionRecoveryDtlsArrayForPension = new ArrayList<TrnPensionRecoveryDtls>();
            ArrayList<TrnPensionRecoveryDtls> lObjTrnPensionRecoveryDtlsArrayForMonthly = new ArrayList<TrnPensionRecoveryDtls>();
            ArrayList<TrnPensionRecoveryDtls> lObjTrnPensionRecoveryDtlsArrayForChallan = new ArrayList<TrnPensionRecoveryDtls>();

            lObjTrnPensionRecoveryDtlsArrayForCVP = lObjTrnPensionRecoveryDtlsDAO.getTrnPensionRecoveryDtlsVOArray(
                    lStrPensionerCode, lStrRecoveryFromFlagCVP);
            lObjTrnPensionRecoveryDtlsArrayForDCRG = lObjTrnPensionRecoveryDtlsDAO.getTrnPensionRecoveryDtlsVOArray(
                    lStrPensionerCode, lStrRecoveryFromFlagDCRG);
            lObjTrnPensionRecoveryDtlsArrayForPension = lObjTrnPensionRecoveryDtlsDAO.getTrnPensionRecoveryDtlsVOArray(
                    lStrPensionerCode, lStrRecoveryFromFlagPension);
            lObjTrnPensionRecoveryDtlsArrayForMonthly = lObjTrnPensionRecoveryDtlsDAO.getTrnPensionRecoveryDtlsVOArray(
                    lStrPensionerCode, lStrRecoveryFromFlagMonthly);
            lObjTrnPensionRecoveryDtlsArrayForChallan = lObjTrnPensionRecoveryDtlsDAO.getTrnPensionRecoveryDtlsVOArray(
                    lStrPensionerCode, lStrRecoveryFromFlagChallan);

            if (lObjTrnPensionRecoveryDtlsArrayForCVP != null)
            {
                inputMap.put("CVPTrnPensionRecoveryDtlsVOArray", lObjTrnPensionRecoveryDtlsArrayForCVP);
            }
            if (lObjTrnPensionRecoveryDtlsArrayForDCRG != null)
            {
                inputMap.put("DCRGTrnPensionRecoveryDtlsVOArray", lObjTrnPensionRecoveryDtlsArrayForDCRG);
            }
            if (lObjTrnPensionRecoveryDtlsArrayForPension != null)
            {
                inputMap.put("PensionTrnPensionRecoveryDtlsVOArray", lObjTrnPensionRecoveryDtlsArrayForPension);
            }
            if (lObjTrnPensionRecoveryDtlsArrayForMonthly != null)
            {
                inputMap.put("MonthlyTrnPensionRecoveryDtlsVOArray", lObjTrnPensionRecoveryDtlsArrayForMonthly);
                int maxOfForMonthListMonthlyPension = 0;
                
                for(int index=0;index < lObjTrnPensionRecoveryDtlsArrayForMonthly.size();index++)
                {
                	/*String lStrBillNo = lObjTrnPensionRecoveryDtlsArrayForMonthly.get(index).getBillNo();               	
                	
                	if(lStrBillNo != null)
                	{*/
                		
	                    maxOfForMonthListMonthlyPension = lObjTrnPensionBillDtlsDAO.getMaxOfForMonth(lStrPensionerCode,lStrPPONumber,lStrRecoveryFromFlagMonthly);
	                    if (maxOfForMonthListMonthlyPension > 0)
	                    {
	                        inputMap.put("MaxOfForMonthForMonthlyPensionBill", maxOfForMonthListMonthlyPension);
	                    }
                	//}

                 }
                ArrayList<Integer> frommonth = new ArrayList<Integer>();
                ArrayList<Integer> fromyear = new ArrayList<Integer>();
                ArrayList<Integer> tomonth = new ArrayList<Integer>();
                ArrayList<Integer> toyear = new ArrayList<Integer>();
                TrnPensionRecoveryDtls ltempTrnPensionRecoveryDtls = null;

                for (int i = 0; i < lObjTrnPensionRecoveryDtlsArrayForMonthly.size(); i++)
                {
                    ltempTrnPensionRecoveryDtls = (TrnPensionRecoveryDtls) lObjTrnPensionRecoveryDtlsArrayForMonthly
                            .get(i);
                    fromyear.add(Integer.parseInt(ltempTrnPensionRecoveryDtls.getFromMonth().toString().substring(0,
                            4)));
                    frommonth.add(Integer.parseInt(ltempTrnPensionRecoveryDtls.getFromMonth().toString().substring(4,
                            6)));
                    toyear.add(Integer.parseInt(ltempTrnPensionRecoveryDtls.getToMonth().toString().substring(0, 4)));
                    tomonth
                            .add(Integer
                                    .parseInt(ltempTrnPensionRecoveryDtls.getToMonth().toString().substring(4, 6)));
                }

                inputMap.put("FromMonth", frommonth);
                inputMap.put("FromYear", fromyear);
                inputMap.put("ToMonth", tomonth);
                inputMap.put("ToYear", toyear);

            }
            if (lObjTrnPensionRecoveryDtlsArrayForChallan != null)
            {
                inputMap.put("ChallanTrnPensionRecoveryDtlsVOArray", lObjTrnPensionRecoveryDtlsArrayForChallan);
            }

            // Retrieving recovery types and deduction types from Cmn_LookUp_Mst
            CmnLookupMstDAO lObjCmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, servLoc
                    .getSessionFactory());

            gLngLangId = Long.parseLong(lStrLangId);
            List lRecoveryType = lObjCmnLookupMstDAO.getAllChildrenByLookUpNameAndLang(lStrRECOTYPE, gLngLangId);
            List lDeductionType = lObjCmnLookupMstDAO
                    .getAllChildrenByLookUpNameAndLang(lStrDEDUCTIONTYPE, gLngLangId);

            if (lRecoveryType != null)
            {
                inputMap.put("RecoveryTypeList", lRecoveryType);
            }
            if (lDeductionType != null)
            {
                inputMap.put("DeductionTypeList", lDeductionType);
            }

            // To populate month combo....
            List lObjSgvaMonthMst = new ArrayList();

            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(servLoc.getSessionFactory());

            // Returning VO array...
            lObjSgvaMonthMst = lObjCommonPensionDAO.getSgvaMonthMstVO(lStrLangId);

            if (lObjSgvaMonthMst != null)
            {
                inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMst);
            }

            // To populate year combo....
            List lObjSgvcFinYearMst = new ArrayList();

            // Returning VO array...
            lObjSgvcFinYearMst = lObjCommonPensionDAO.getSgvcFinYearMstVO(lStrLangId);

            if (lObjSgvcFinYearMst != null)
            {
                inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMst);
            }

            //------------------------------------------------------------------------------------------------------   
            //Retrieving Maximum of formonth field from trn_pension_bill_hdr
     /*     PensionRecoveryInfoDAO PensionRecoveryInfoDAOMonthly = new PensionRecoveryInfoDAOImpl(servLoc.getSessionFactory());
          
            int maxOfForMonthListMonthlyPension = 0;
            maxOfForMonthListMonthlyPension = PensionRecoveryInfoDAOMonthly.getMaxOfForMonth(lBDPensionRequestId,lStrRecoveryFromFlagMonthly);
                    
            if (maxOfForMonthListMonthlyPension > 0)
            {
                gLogger.info("MaxOfMonthList is :--->" + maxOfForMonthListMonthlyPension);
                inputMap.put("MaxOfForMonthForMonthlyPensionBill", maxOfForMonthListMonthlyPension);
            }*/

            //-------------------------------------------------------------------------------------------------
          
            resObj.setResultValue(inputMap);
            resObj.setViewName("recovery");
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


    // Saving data------------------------------------------

    /**
     * Inserts Recovery Dtls
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject saveRecoveryDtls(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

        TrnPensionRecoveryDtls lObjTrnPensionRecoveryDtls = new TrnPensionRecoveryDtls();
        TrnPensionRecoveryDtlsDAO lObjTrnPensionRecoveryDtlsDAO = new TrnPensionRecoveryDtlsDAOImpl(TrnPensionRecoveryDtls.class,serv.getSessionFactory());
        
        List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsCVP = null;
        List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsDCRG = null;
        List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsBasicPension = null;
        List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsMonthly = null;

        Long lLngPensionRequestId = null;
        Long lLngTrnPensionRecoveryDtlsPK = null;

        String lStrPensionerCode = "";        

        try
        {
            lLstTrnPensionRecoveryDtlsCVP = (List<TrnPensionRecoveryDtls>) inputMap
                    .get("lLstTrnPensionRecoveryDtlsVOListCVP");
            lLstTrnPensionRecoveryDtlsDCRG = (List<TrnPensionRecoveryDtls>) inputMap
                    .get("lLstTrnPensionRecoveryDtlsVOListDCRG");
            lLstTrnPensionRecoveryDtlsBasicPension = (List<TrnPensionRecoveryDtls>) inputMap
                    .get("lLstTrnPensionRecoveryDtlsVOListBasicPension");
            lLstTrnPensionRecoveryDtlsMonthly = (List<TrnPensionRecoveryDtls>) inputMap
                    .get("lLstTrnPensionRecoveryDtlsVOListMonthly");

            lLngPensionRequestId = (new Long((inputMap.get("PensionRequestId")).toString()));
            lStrPensionerCode = (inputMap.get("PensionerCode")).toString();

            /*
             * Deleting the previously inserted data.... First get all the pks
             * of existing records , Read the records with their pks Delete
             * those records...
             */          

            // Getting PK s of existing records....for a particular
            // PensionRequestId and PensionerCode ....
            List<Long> lLstTrnPensionRecoveryDtlsPK = lObjTrnPensionRecoveryDtlsDAO
                    .getPKForTableTrnPensionRecoveryDtls(lLngPensionRequestId, lStrPensionerCode);

            if (lLstTrnPensionRecoveryDtlsPK != null && lLstTrnPensionRecoveryDtlsPK.size() > 0)
            {
                for (int i = 0; i < lLstTrnPensionRecoveryDtlsPK.size(); ++i)
                {
                	lLngTrnPensionRecoveryDtlsPK = lLstTrnPensionRecoveryDtlsPK.get(i);
                    lObjTrnPensionRecoveryDtls = lObjTrnPensionRecoveryDtlsDAO.read(lLngTrnPensionRecoveryDtlsPK);
                    lObjTrnPensionRecoveryDtlsDAO.delete(lObjTrnPensionRecoveryDtls);
                }
            }

            /*
             * Now Time for fresh insert...
             */
            Iterator<TrnPensionRecoveryDtls> lItrDtlCVP = lLstTrnPensionRecoveryDtlsCVP.iterator();
            Iterator<TrnPensionRecoveryDtls> lItrDtlDCRG = lLstTrnPensionRecoveryDtlsDCRG.iterator();
            Iterator<TrnPensionRecoveryDtls> lItrDtlPENSION = lLstTrnPensionRecoveryDtlsBasicPension.iterator();
            Iterator<TrnPensionRecoveryDtls> lItrDtlMonthly = lLstTrnPensionRecoveryDtlsMonthly.iterator();

            while (lItrDtlCVP.hasNext())
            {

                lObjTrnPensionRecoveryDtls = lItrDtlCVP.next();
                lObjTrnPensionRecoveryDtls.setPensionRequestId(lLngPensionRequestId);
                lObjTrnPensionRecoveryDtls.setPensionerCode(lStrPensionerCode);

                saveRecoveryData(lObjTrnPensionRecoveryDtls, inputMap);
            }

            inputMap.put("ReturnTrnPensionItCutDtlsVOListCVP", lLstTrnPensionRecoveryDtlsCVP);

            while (lItrDtlDCRG.hasNext())
            {
                lObjTrnPensionRecoveryDtls = lItrDtlDCRG.next();
                lObjTrnPensionRecoveryDtls.setPensionRequestId(lLngPensionRequestId);
                lObjTrnPensionRecoveryDtls.setPensionerCode(lStrPensionerCode);

                saveRecoveryData(lObjTrnPensionRecoveryDtls, inputMap);
            }

            inputMap.put("ReturnTrnPensionItCutDtlsVOListDCRG", lLstTrnPensionRecoveryDtlsDCRG);

            while (lItrDtlPENSION.hasNext())
            {
                lObjTrnPensionRecoveryDtls = lItrDtlPENSION.next();
                lObjTrnPensionRecoveryDtls.setPensionRequestId(lLngPensionRequestId);
                lObjTrnPensionRecoveryDtls.setPensionerCode(lStrPensionerCode);

                saveRecoveryData(lObjTrnPensionRecoveryDtls, inputMap);
            }

            inputMap.put("ReturnTrnPensionItCutDtlsVOListFIRSTPENSION", lLstTrnPensionRecoveryDtlsBasicPension);

            while (lItrDtlMonthly.hasNext())
            {

                lObjTrnPensionRecoveryDtls = lItrDtlMonthly.next();
                lObjTrnPensionRecoveryDtls.setPensionRequestId(lLngPensionRequestId);
                lObjTrnPensionRecoveryDtls.setPensionerCode(lStrPensionerCode);

                saveRecoveryData(lObjTrnPensionRecoveryDtls, inputMap);
            }

            inputMap.put("ReturnTrnPensionItCutDtlsVOListMonthly", lLstTrnPensionRecoveryDtlsMonthly);

            objRes.setResultValue(inputMap);
            objRes.setViewName("recoverybutton");
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            objRes.setResultValue(null);
            objRes.setThrowable(e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");
        }

        return objRes;
    }
    /**
     * Save the Recovery Details of TrnPensionRecoveryDtls VO.
     * 
     * @param TrnPensionRecoveryDtls
     *            lObjTrnPensionRecoveryDtls, Map inputMap
     * @return void
     */
    public void saveRecoveryData(TrnPensionRecoveryDtls lObjTrnPensionRecoveryDtls, Map inputMap) throws Exception
    {
        try
        {
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

            TrnPensionRecoveryDtlsDAO lObjTrnPensionRecoveryDtlsDAO = new TrnPensionRecoveryDtlsDAOImpl(
                    TrnPensionRecoveryDtls.class, serv.getSessionFactory());

            Long lLngRecoveryDtlsId = null;

            // setting PK ....
            lLngRecoveryDtlsId = new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_recovery_dtls",
                    inputMap)); 
            lObjTrnPensionRecoveryDtls.setTrnPensionRecoveryDtlsId(lLngRecoveryDtlsId);

            lObjTrnPensionRecoveryDtlsDAO.create(lObjTrnPensionRecoveryDtls);
        }
        catch (Exception e)
        {
            gLogger.error("Error is " + e, e);
            throw e;
        }
    }
    // For AJAX use for table MstEdp...

    public ResultObject getRecoveryTypeInfo(Map inputMap)
    {

        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");        

        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
        
        try
        {
            setSessionInfo(inputMap);

            String lStrEdpCode = request.getParameter("EDPCODE");

            MstEdp lObjMstEdp = lObjTrnPensionRqstHdrDAO.getMstEdpVO(lStrEdpCode, gLngLangId);

            String lStrEdpcode = lObjMstEdp.getEdpCode();
            String lStrMajorheadcode = lObjMstEdp.getBudmjrhdCode();
            String lStrMinorheadcode = lObjMstEdp.getBudminhdCode();
            String lStrSubMajorheadcode = lObjMstEdp.getBudsubmjrhdCode();
            String lStrSubheadcode = lObjMstEdp.getBudsubhdCode();
            //String lStrEdpCategory = lObjMstEdp.getEdpCategory();

            StringBuilder lStrGrant = new StringBuilder();

            if (lObjMstEdp != null)
            {
                lStrGrant.append(" <RECOVERYINFO> ");
                lStrGrant.append(" 		<EDPCODE> ");
                lStrGrant.append(lStrEdpcode);
                lStrGrant.append(" 		</EDPCODE> ");
                lStrGrant.append(" 		<MAJORHEAD> ");
                lStrGrant.append(lStrMajorheadcode);
                lStrGrant.append(" 		</MAJORHEAD> ");
                lStrGrant.append(" 		<SUBMAJORHEAD> ");
                lStrGrant.append(lStrSubMajorheadcode);
                lStrGrant.append(" 		</SUBMAJORHEAD> ");
                lStrGrant.append(" 		<MINORHEAD> ");
                lStrGrant.append(lStrMinorheadcode);
                lStrGrant.append(" 		</MINORHEAD> ");
                lStrGrant.append(" 		<SUBHEAD> ");
                lStrGrant.append(lStrSubheadcode);
                lStrGrant.append(" 		</SUBHEAD> ");
                lStrGrant.append(" 		<DEDUCTIONTYPE> ");
                //lStrGrant.append(lStrEdpCategory);
                lStrGrant.append(" 		</DEDUCTIONTYPE> ");
                lStrGrant.append(" </RECOVERYINFO> ");
            }

            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
            resObj.setViewName("ajaxData");
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
    private void setSessionInfo(Map inputMap)
    {
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        //gLngFinYearId = SessionHelper.getFinYrId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }

}
