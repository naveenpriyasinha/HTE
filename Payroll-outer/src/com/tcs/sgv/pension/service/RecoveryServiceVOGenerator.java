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
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * TrnPensionRecoveryDtls and TrnPensionRqstHdr VO generator class
 * implementation
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public class RecoveryServiceVOGenerator extends ServiceImpl implements VOGeneratorService
{
    /* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for LangId */
    Long gLngLangId = null;

    /* Global Variable for EmpId */
    Long gLngEmpId = null;

    /* Global Variable for Location Code */
    String gStrLocationCode = null;

    /* Global Variable for DB Id */
    Long gLngDBId = null;

    /* Global Variable for Current Date */
    Date gDtCurrDt = null;

    /* Global Variable for Logger Class */
    Log gLogger = LogFactory.getLog(getClass());


    /**
     * It generates TrnPensionRqstHdr and TrnPensionRecoveryDtls VOs for
     * different types of Cuts.
     * 
     * @param Map
     *            objectArgs
     * @return ResultObject objRes
     */
    public ResultObject generateMap(Map objArgs)
    {

        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objArgs.get("requestObj");
        
        String lStrPensionerCode = null;
        String lStrPPONumber = null;
        Long lLngPensionRequestId = null;

        try
        {
            setSessionInfo(objArgs);
            
            lLngPensionRequestId = Long.parseLong(StringUtility.getParameter("pension_request_id", request).trim());
            lStrPensionerCode =StringUtility.getParameter("pensioner_code", request).trim();
            lStrPPONumber = StringUtility.getParameter("hidPPONo", request).trim();
            
            generateTrnPensionRqstHdrVO(objArgs,lLngPensionRequestId,lStrPensionerCode,lStrPPONumber);
            List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsCVP = generateTrnPensionRecoveryDtlsVOListForCVP(objArgs);
            List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsDCRG = generateTrnPensionRecoveryDtlsVOListForDCRG(objArgs);
            List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsBasicPension = generateTrnPensionRecoveryDtlsVOListForBasicPension(objArgs);
            List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtlsMonthly = generateTrnPensionRecoveryDtlsVOListForMonthly(objArgs);

            objArgs.put("lLstTrnPensionRecoveryDtlsVOListCVP", lLstTrnPensionRecoveryDtlsCVP);
            objArgs.put("lLstTrnPensionRecoveryDtlsVOListDCRG", lLstTrnPensionRecoveryDtlsDCRG);
            objArgs.put("lLstTrnPensionRecoveryDtlsVOListBasicPension", lLstTrnPensionRecoveryDtlsBasicPension);
            objArgs.put("lLstTrnPensionRecoveryDtlsVOListMonthly", lLstTrnPensionRecoveryDtlsMonthly);
            objArgs.put("PPONumber", lStrPPONumber);
            objArgs.put("PensionRequestId", lLngPensionRequestId);
            objArgs.put("PensionerCode", lStrPensionerCode);

            objRes.setResultValue(objArgs);
        }
        catch (Exception e)
        {
            gLogger.error("Exception occurred in generateMap() method of RecoveryServiceVOGenerator Class " + e, e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setThrowable(e);
        }
        return objRes;
    }


    /*
     * CVP===========
     */
    private List<TrnPensionRecoveryDtls> generateTrnPensionRecoveryDtlsVOListForCVP(Map lMapInput) throws Exception
    {
        List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtls = new ArrayList<TrnPensionRecoveryDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        String lStrRecoveryFromFlagCVP = bundleConst.getString("RECOVERY.CVP");


        String[] lStrCmbRecoveryType = null;
        String[] lStrCmbDeductionType = null;
        String[] lStrAccountNo = null;
        String[] lStrEdpCode = null;
        String[] lStrMajorHead = null;
        String[] lStrSubMajorHead = null;
        String[] lStrMinorHead = null;
        String[] lStrSubHead = null;
        String[] lStrAmount = null;
        String[] lStrBillNo = null;


        TrnPensionRecoveryDtls lObjTrnPensionRecoveryDtls = null;

        try
        {
            lStrCmbRecoveryType =StringUtility.getParameterValues("cmbrecotype2", request);
            lStrCmbDeductionType = StringUtility.getParameterValues("cmbdeductiontype2", request);
            lStrAccountNo = StringUtility.getParameterValues("txtacnno2", request);
            lStrEdpCode = StringUtility.getParameterValues("txtedpcode2", request);
            lStrMajorHead = StringUtility.getParameterValues("txtmajhead2", request);
            lStrSubMajorHead = StringUtility.getParameterValues("txtsubmajhead2", request);
            lStrMinorHead = StringUtility.getParameterValues("txtminhead2", request);
            lStrSubHead = StringUtility.getParameterValues("txtsubhead2", request);
            lStrAmount = StringUtility.getParameterValues("txtamount2", request);
            lStrBillNo = StringUtility.getParameterValues("bill_noCVP", request);

            for (int lIntCnt = 0; lIntCnt < lStrAccountNo.length; lIntCnt++)
            {
                lObjTrnPensionRecoveryDtls = new TrnPensionRecoveryDtls();
                if( ! lStrCmbRecoveryType[lIntCnt].toString().equalsIgnoreCase("-1") && ! lStrCmbDeductionType[lIntCnt].toString().equalsIgnoreCase("-1"))
                {
                    lObjTrnPensionRecoveryDtls.setRecoveryFromFlag(lStrRecoveryFromFlagCVP);
                    lObjTrnPensionRecoveryDtls.setRecoveryType(lStrCmbRecoveryType[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setDeductionType(lStrCmbDeductionType[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setAccountNumber(lStrAccountNo[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setEdpCode(lStrEdpCode[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setMjrhdCode(lStrMajorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setSubmjrhdCode(lStrSubMajorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setMinhdCode(lStrMinorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setSubhdCode(lStrSubHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setAmount(new BigDecimal(lStrAmount[lIntCnt]));
                    lObjTrnPensionRecoveryDtls.setTrnCounter(1);
                    if(lIntCnt <= lStrBillNo.length)
                    {
                    	lObjTrnPensionRecoveryDtls.setBillNo(lStrBillNo[lIntCnt]);
                    }
                    lObjTrnPensionRecoveryDtls.setCreatedDate(gDtCurrDt);
                    lObjTrnPensionRecoveryDtls.setCreatedPostId(new BigDecimal(gLngPostId));
                    lObjTrnPensionRecoveryDtls.setCreatedUserId(new BigDecimal(gLngUserId));

                    lLstTrnPensionRecoveryDtls.add(lObjTrnPensionRecoveryDtls);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lLstTrnPensionRecoveryDtls;
    }


    /*
     * DCRG===========
     */
    private List<TrnPensionRecoveryDtls> generateTrnPensionRecoveryDtlsVOListForDCRG(Map lMapInput) throws Exception
    {
        List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtls = new ArrayList<TrnPensionRecoveryDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        String lStrRecoveryFromFlagDCRG = bundleConst.getString("RECOVERY.DCRG");
        String[] lStrCmbRecoveryType = null;
        String[] lStrCmbDeductionType = null;
        String[] lStrAccountNo = null;
        String[] lStrEdpCode = null;
        String[] lStrMajorHead = null;
        String[] lStrSubMajorHead = null;
        String[] lStrMinorHead = null;
        String[] lStrSubHead = null;
        String[] lStrAmount = null;
        String[] lStrBillNo = null;
        TrnPensionRecoveryDtls lObjTrnPensionRecoveryDtls = null;
        try
        {
            lStrCmbRecoveryType = StringUtility.getParameterValues("cmbrecotype1", request);
            lStrCmbDeductionType = StringUtility.getParameterValues("cmbdeductiontype1", request);
            lStrAccountNo = StringUtility.getParameterValues("txtacnno1", request);
            lStrEdpCode = StringUtility.getParameterValues("txtedpcode1", request);
            lStrMajorHead = StringUtility.getParameterValues("txtmajhead1", request);
            lStrSubMajorHead = StringUtility.getParameterValues("txtsubmajhead1", request);
            lStrMinorHead = StringUtility.getParameterValues("txtminhead1", request);
            lStrSubHead = StringUtility.getParameterValues("txtsubhead1", request);
            lStrAmount = StringUtility.getParameterValues("txtamount1", request);
            lStrBillNo = StringUtility.getParameterValues("bill_noDCRG", request);

            for (int lIntCnt = 0; lIntCnt < lStrAccountNo.length; lIntCnt++)
            {
                lObjTrnPensionRecoveryDtls = new TrnPensionRecoveryDtls();

                // setting values to VO...
                if( ! lStrCmbRecoveryType[lIntCnt].toString().equalsIgnoreCase("-1") && ! lStrCmbDeductionType[lIntCnt].toString().equalsIgnoreCase("-1"))
                {
                    lObjTrnPensionRecoveryDtls.setRecoveryFromFlag(lStrRecoveryFromFlagDCRG);
                    lObjTrnPensionRecoveryDtls.setRecoveryType(lStrCmbRecoveryType[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setDeductionType(lStrCmbDeductionType[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setAccountNumber(lStrAccountNo[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setEdpCode(lStrEdpCode[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setMjrhdCode(lStrMajorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setSubmjrhdCode(lStrSubMajorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setMinhdCode(lStrMinorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setSubhdCode(lStrSubHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setAmount(new BigDecimal(lStrAmount[lIntCnt]));
                    lObjTrnPensionRecoveryDtls.setTrnCounter(1);
                    if(lIntCnt <= lStrBillNo.length)
                    {
                    	lObjTrnPensionRecoveryDtls.setBillNo(lStrBillNo[lIntCnt]);
                    }
                    lObjTrnPensionRecoveryDtls.setCreatedDate(gDtCurrDt);
                    lObjTrnPensionRecoveryDtls.setCreatedPostId(new BigDecimal(gLngPostId));
                    lObjTrnPensionRecoveryDtls.setCreatedUserId(new BigDecimal(gLngUserId));
                    
                    lLstTrnPensionRecoveryDtls.add(lObjTrnPensionRecoveryDtls);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lLstTrnPensionRecoveryDtls;
    }

    /*
     * First Pension===========
     */
    private List<TrnPensionRecoveryDtls> generateTrnPensionRecoveryDtlsVOListForBasicPension(Map lMapInput)
            throws Exception
    {
        List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtls = new ArrayList<TrnPensionRecoveryDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        String lStrRecoveryFromFlagPension = bundleConst.getString("RECOVERY.PENSION");
        String[] lStrCmbRecoveryType = null;
        String[] lStrCmbDeductionType = null;
        String[] lStrAccountNo = null;
        String[] lStrEdpCode = null;
        String[] lStrMajorHead = null;
        String[] lStrSubMajorHead = null;
        String[] lStrMinorHead = null;
        String[] lStrSubHead = null;
        String[] lStrAmount = null;
        //String[] lStrBillNo = null;
        TrnPensionRecoveryDtls lObjTrnPensionRecoveryDtls = null;
        try
        {
            lStrCmbRecoveryType = StringUtility.getParameterValues("cmbrecotype3", request);
            lStrCmbDeductionType = StringUtility.getParameterValues("cmbdeductiontype3", request);
            lStrAccountNo = StringUtility.getParameterValues("txtacnno3", request);
            lStrEdpCode = StringUtility.getParameterValues("txtedpcode3", request);
            lStrMajorHead = StringUtility.getParameterValues("txtmajhead3", request);
            lStrSubMajorHead = StringUtility.getParameterValues("txtsubmajhead3", request);
            lStrMinorHead = StringUtility.getParameterValues("txtminhead3", request);
            lStrSubHead = StringUtility.getParameterValues("txtsubhead3", request);
            lStrAmount = StringUtility.getParameterValues("txtamount3", request);
            //lStrBillNo = StringUtility.getParameterValues("bill_noPENSION", request);
            for (int lIntCnt = 0; lIntCnt < lStrAccountNo.length; lIntCnt++)
            {
                lObjTrnPensionRecoveryDtls = new TrnPensionRecoveryDtls();

                // setting values to VO...
                if( ! lStrCmbRecoveryType[lIntCnt].toString().equalsIgnoreCase("-1") && ! lStrCmbDeductionType[lIntCnt].toString().equalsIgnoreCase("-1"))
                {
                    lObjTrnPensionRecoveryDtls.setRecoveryFromFlag(lStrRecoveryFromFlagPension);
                    lObjTrnPensionRecoveryDtls.setRecoveryType(lStrCmbRecoveryType[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setDeductionType(lStrCmbDeductionType[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setAccountNumber(lStrAccountNo[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setEdpCode(lStrEdpCode[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setMjrhdCode(lStrMajorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setSubmjrhdCode(lStrSubMajorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setMinhdCode(lStrMinorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setSubhdCode(lStrSubHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setAmount(new BigDecimal(lStrAmount[lIntCnt]));
                    lObjTrnPensionRecoveryDtls.setTrnCounter(1);
                    /*if(lIntCnt <= lStrBillNo.length)
                    {
                    	lObjTrnPensionRecoveryDtls.setBillNo(lStrBillNo[lIntCnt]);
                    }*/
                    lObjTrnPensionRecoveryDtls.setCreatedDate(gDtCurrDt);
                    lObjTrnPensionRecoveryDtls.setCreatedPostId(new BigDecimal(gLngPostId));
                    lObjTrnPensionRecoveryDtls.setCreatedUserId(new BigDecimal(gLngUserId));

                    lLstTrnPensionRecoveryDtls.add(lObjTrnPensionRecoveryDtls);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lLstTrnPensionRecoveryDtls;
    }


    /*
     * Monthly Pension===========
     */
    private List<TrnPensionRecoveryDtls> generateTrnPensionRecoveryDtlsVOListForMonthly(Map lMapInput)
            throws Exception
    {
        List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtls = new ArrayList<TrnPensionRecoveryDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        String lStrRecoveryFromFlagMonthly = bundleConst.getString("RECOVERY.MONTHLY");
        String[] lStrCmbFromMonth = null;
        String[] lStrCmbDeductionType = null;
        String[] lStrCmbFromYear = null;
        String[] lStrCmbToMonth = null;
        String[] lStrCmbToYear = null;
        String[] lStrCmbRecoveryType = null;
        String[] lStrAccountNo = null;
        String[] lStrEdpCode = null;
        String[] lStrMajorHead = null;
        String[] lStrSubMajorHead = null;
        String[] lStrMinorHead = null;
        String[] lStrSubHead = null;
        String[] lStrAmount = null;
        //String[] lStrBillNo = null;

        String lStrFromDate = null;
        String lStrToDate = null;
        String lStrFromMonthval = null;
        String lStrToMonthval = null;

        TrnPensionRecoveryDtls lObjTrnPensionRecoveryDtls = null;

        try
        {
            lStrCmbFromMonth = StringUtility.getParameterValues("cmbfrmmon", request);
            lStrCmbFromYear = StringUtility.getParameterValues("cmbfrmyr", request);
            lStrCmbToMonth = StringUtility.getParameterValues("cmbtomon", request);
            lStrCmbToYear = StringUtility.getParameterValues("cmbtoyr", request);

            lStrCmbRecoveryType = StringUtility.getParameterValues("cmbrecotype4", request);
            lStrCmbDeductionType = StringUtility.getParameterValues("cmbdeductiontype4", request);
            lStrAccountNo = StringUtility.getParameterValues("txtacnno4", request);
            lStrEdpCode = StringUtility.getParameterValues("txtedpcode4", request);
            lStrMajorHead = StringUtility.getParameterValues("txtmajhead4", request);
            lStrSubMajorHead = StringUtility.getParameterValues("txtsubmajhead4", request);
            lStrMinorHead = StringUtility.getParameterValues("txtminhead4", request);
            lStrSubHead = StringUtility.getParameterValues("txtsubhead4", request);
            lStrAmount = StringUtility.getParameterValues("txtamount4", request);
            //lStrBillNo = StringUtility.getParameterValues("bill_noMonthly", request);
            for (int lIntCnt = 0; lIntCnt < lStrAccountNo.length; lIntCnt++)
            {
                // generating Two digit month_no properly to insert into
                // TrnPensionItCutDtls...
                if (Integer.parseInt(lStrCmbFromMonth[lIntCnt]) < 10)
                {
                    lStrFromMonthval = "0" + lStrCmbFromMonth[lIntCnt];
                }
                else
                {
                    lStrFromMonthval = lStrCmbFromMonth[lIntCnt];
                }
                if (Integer.parseInt(lStrCmbToMonth[lIntCnt]) < 10)
                {
                    lStrToMonthval = "0" + lStrCmbToMonth[lIntCnt];
                }
                else
                {
                    lStrToMonthval = lStrCmbToMonth[lIntCnt];
                }
                // generating From and To date combining Month and Year...
                lStrFromDate = lStrCmbFromYear[lIntCnt] + lStrFromMonthval;
                lStrToDate = lStrCmbToYear[lIntCnt] + lStrToMonthval;
                lObjTrnPensionRecoveryDtls = new TrnPensionRecoveryDtls();
                // setting values to VO...
                if( ! lStrCmbRecoveryType[lIntCnt].toString().equalsIgnoreCase("-1") && ! lStrCmbDeductionType[lIntCnt].toString().equalsIgnoreCase("-1"))
                {
                    lObjTrnPensionRecoveryDtls.setFromMonth(Integer.parseInt(lStrFromDate));
                    lObjTrnPensionRecoveryDtls.setToMonth(Integer.parseInt(lStrToDate));
                    lObjTrnPensionRecoveryDtls.setRecoveryFromFlag(lStrRecoveryFromFlagMonthly);
                    lObjTrnPensionRecoveryDtls.setRecoveryType(lStrCmbRecoveryType[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setDeductionType(lStrCmbDeductionType[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setAccountNumber(lStrAccountNo[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setEdpCode(lStrEdpCode[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setMjrhdCode(lStrMajorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setSubmjrhdCode(lStrSubMajorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setMinhdCode(lStrMinorHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setSubhdCode(lStrSubHead[lIntCnt]);
                    lObjTrnPensionRecoveryDtls.setAmount(new BigDecimal(lStrAmount[lIntCnt]));
                    lObjTrnPensionRecoveryDtls.setTrnCounter(1);
                    /*if(lIntCnt <= lStrBillNo.length)
                    {
                    	lObjTrnPensionRecoveryDtls.setBillNo(lStrBillNo[lIntCnt]);
                    }*/
                    lObjTrnPensionRecoveryDtls.setCreatedDate(gDtCurrDt);
                    lObjTrnPensionRecoveryDtls.setCreatedPostId(new BigDecimal(gLngPostId));
                    lObjTrnPensionRecoveryDtls.setCreatedUserId(new BigDecimal(gLngUserId));

                    lLstTrnPensionRecoveryDtls.add(lObjTrnPensionRecoveryDtls);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lLstTrnPensionRecoveryDtls;
    }


    private void generateTrnPensionRqstHdrVO(Map lMapInput, Long lLngPensionRequestId, String lStrPensionerCode,String lStrPPONumber)
    {

        TrnPensionRqstHdr lObjTrnPensionRqstHdr = new TrnPensionRqstHdr();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");

        TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, servLoc
                .getSessionFactory());
        String lStrTotalRecovery = null;
        String lStrPendingRecovery = null;
        String lStrTotalCVPAmount = null;
        String lStrTotalDCRGAmount = null;
        

        try
        {
            lStrTotalCVPAmount = StringUtility.getParameter("txttotcvpamnt", request).trim();
            lStrTotalDCRGAmount = StringUtility.getParameter("txtdcrgamount", request).trim();
            //lStrTotalPensionAmount = StringUtility.getParameter("txtpensnamnt", request);
            lStrTotalRecovery = StringUtility.getParameter("txtrecovery", request).trim();
            lStrPendingRecovery = StringUtility.getParameter("txtpending", request).trim();
            lObjTrnPensionRqstHdr = lObjTrnPensionRqstHdrDAO.read(lLngPensionRequestId);
            // setting values of VO...
            lObjTrnPensionRqstHdr.setPensionRequestId(lLngPensionRequestId);
            lObjTrnPensionRqstHdr.setPensionerCode(lStrPensionerCode);
            lObjTrnPensionRqstHdr.setPpoNo(lStrPPONumber);
            
            if(lStrTotalCVPAmount != "")
            {	
            	lObjTrnPensionRqstHdr.setCvpAmount(new BigDecimal(lStrTotalCVPAmount));
            }
            if(lStrTotalDCRGAmount != "")
            {
            	lObjTrnPensionRqstHdr.setDcrgAmount(new BigDecimal(lStrTotalDCRGAmount));
            }
          //  lObjTrnPensionRqstHdr.setBasicPensionAmount(new BigDecimal(lStrTotalPensionAmount));
            lObjTrnPensionRqstHdr.setTotalRecovery(new BigDecimal(lStrTotalRecovery));
            lObjTrnPensionRqstHdr.setPendingRecovery(new BigDecimal(lStrPendingRecovery));
   
            lObjTrnPensionRqstHdrDAO.update(lObjTrnPensionRqstHdr);
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
        }

    }

    private void setSessionInfo(Map inputMap)
    {
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
}