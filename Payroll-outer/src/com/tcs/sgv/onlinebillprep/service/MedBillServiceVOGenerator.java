/**
 * package : com.tcs.sgv.onlinebillprep.service 
 * @verion : 1.0
 * @author : Keyur Shah, Nirav Bumia 
 ** 
 */
package com.tcs.sgv.onlinebillprep.service;

import static com.tcs.sgv.onlinebillprep.util.OnlineBillUtil.requestArraySetter;
import static com.tcs.sgv.onlinebillprep.util.OnlineBillUtil.requestSetter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.TrnMedblHdrDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnMedblHdrDAOImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblAprvdDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblHdr;

/**
 * It generates Med Bill Specific VOs(TrnMedblHdr,TrnMedblDtls,TrnMedblAprvdDtls).
 * 
 * @author Keyur Shah, Nirav Bumia
 * @version 1.0
 */
public class MedBillServiceVOGenerator extends ServiceImpl implements VOGeneratorService
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

    /* Global Variable for Logger Class */
    Log gLogger = LogFactory.getLog(getClass());


    /**
     * It generates Med Bill Specific VOs(TrnMedblHdr,TrnMedblDtls,TrnMedblAprvdDtls).
     *  
     * @param objectArgs
     *            ServiceMap
     * @return objRes ResultObject
     */
    public ResultObject generateMap(Map objArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        setSessionInfo(objArgs);

        TrnMedblHdr lObjMedHdr = generateMedblHdrVO(objArgs);
        List<TrnMedblDtls> lLstMedDtls = generateTrnMedblDtls(objArgs);
        List<TrnMedblAprvdDtls> lLstAprvd = generateTrnMedblAprvdDtls(objArgs);

        objArgs.put("MedBillHdr", lObjMedHdr);
        objArgs.put("MedBillDtls", lLstMedDtls);
        objArgs.put("MedBillAprvdDtls", lLstAprvd);

        objRes.setResultValue(objArgs);

        return objRes;
    }


    /**
     * It generates Med Bill Specific TrnMedblHdr VO.
     *  
     * @param inputMap
     *            InputMap.
     * @return lObjHdrVO TrnMedblHdr
     */
    private TrnMedblHdr generateMedblHdrVO(Map lMapInput)
    {
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");

        TrnMedblHdr lObjHdrVO = new TrnMedblHdr();
        TrnMedblHdrDAO lObjMedBillDAO = new TrnMedblHdrDAOImpl(TrnMedblHdr.class, servLoc.getSessionFactory());
        String lStrMedBillHdrId = requestSetter("hidTrnMedBlHdrId", request);
       
        Long lLngMedBillHdrId = null;

        try
        {
            if (lStrMedBillHdrId.trim().length() > 0)
            {
                lLngMedBillHdrId = Long.parseLong(requestSetter("hidTrnMedBlHdrId", request));
                lObjHdrVO = lObjMedBillDAO.read(lLngMedBillHdrId);
                lObjHdrVO.setUpdatedDate(new Date(System.currentTimeMillis()));
                lObjHdrVO.setUpdatedPostId(gLngPostId);
                lObjHdrVO.setUpdatedUserId(gLngUserId);
            }
            else
            {
                lObjHdrVO.setCreatedUserId(gLngUserId);
                lObjHdrVO.setCreatedPostId(gLngPostId);
                lObjHdrVO.setCreatedDate(new Date(System.currentTimeMillis()));
                lObjHdrVO.setTrnCounter(1);
            }

            BigDecimal lObjBillAmt = new BigDecimal(0);

            if (requestSetter("txtBillPassedAmt", request).trim().length() > 0)
            {
                lObjBillAmt = new BigDecimal(requestSetter("txtBillPassedAmt", request));
            }

            lObjHdrVO.setTrnMedblHdrId(lLngMedBillHdrId);
            lObjHdrVO.setEmpName(requestSetter("txtEmpName", request));
            lObjHdrVO.setEmpDesgn(requestSetter("txtEmpDsg", request));
            lObjHdrVO.setMonthCode(requestSetter("cmbForMonth", request));

            lObjHdrVO.setBillPassedAmt(lObjBillAmt);
            lObjHdrVO.setDeptName(requestSetter("txtEmpEstblsmnt", request));
            lObjHdrVO.setLocationCode(gStrLocationCode);
            lObjHdrVO.setDbId(gLngDBId);

            Date dtBillPassedDt = null;
            String lStrBillPassedDt = null;
            DateFormat lObjSdFormat = new SimpleDateFormat("dd/MM/yyyy");

            if (requestSetter("txtBillPassedDate", request).trim().length() > 0)
            {
                lStrBillPassedDt = requestSetter("txtBillPassedDate", request);
                dtBillPassedDt = lObjSdFormat.parse(lStrBillPassedDt);

                lObjHdrVO.setBillPassedDate(dtBillPassedDt);
            }
        }
        catch (ParseException pe)
        {
            gLogger.error("Error in Parsing the Date in generateMedblHdrVO. Error is : " + pe, pe);
        }
        catch (Exception e)
        {
            gLogger.error("Error in generateMedblHdrVO. Error is : " + e, e);
        }

        return lObjHdrVO;
    }


    /**
     * It generates Med Bill Specific TrnMedblDtls VO.
     *  
     * @param inputMap
     *            InputMap.
     * @return lLstDtlVO List<TrnMedblDtls>
     */
    private List<TrnMedblDtls> generateTrnMedblDtls(Map lMapInput)
    {
        List<TrnMedblDtls> lLstDtlVO = new ArrayList<TrnMedblDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");

        String[] lStrPtntName = null;
        String[] lStrRel = null;
        String[] lStrTrtmnt = null;
        String[] lStrAmt = null;
        String[] lStrRmks = null;

        TrnMedblDtls lObjMedBillDtls = null;

        try
        {
            lStrPtntName = requestArraySetter("txtPatientName", request); 
            lStrRel = requestArraySetter("txtRelation", request);
            lStrTrtmnt = requestArraySetter("txtTreatmentTime", request);
            lStrAmt = requestArraySetter("txtAmtOne", request);
            lStrRmks = requestArraySetter("txtRemarks", request);
            

            for (int lIntCnt = 0; lIntCnt < lStrPtntName.length; lIntCnt++)
            {
                lObjMedBillDtls = new TrnMedblDtls();
                lObjMedBillDtls.setCreatedUserId(gLngUserId);
                lObjMedBillDtls.setCreatedPostId(gLngPostId);
                lObjMedBillDtls.setCreatedDate(new Date(System.currentTimeMillis()));
                lObjMedBillDtls.setTrnCounter(1);

                lObjMedBillDtls.setPtntName(lStrPtntName[lIntCnt]);
                lObjMedBillDtls.setRltnshp(lStrRel[lIntCnt]);
                lObjMedBillDtls.setClaimAmt(new BigDecimal(lStrAmt[lIntCnt].length() != 0 ? lStrAmt[lIntCnt] : "0"));
                lObjMedBillDtls.setTrtmtTime(lStrTrtmnt[lIntCnt]);
               
                if(lStrRmks[lIntCnt].length() == 0)
                {
                	lObjMedBillDtls.setRemarks(" ");
                }
                else
                {
                	lObjMedBillDtls.setRemarks(lStrRmks[lIntCnt]);
                }
                lObjMedBillDtls.setLocationCode(gStrLocationCode);
                lObjMedBillDtls.setDbId(gLngDBId);

                gLogger.info("Patient Name is : " + lObjMedBillDtls.getPtntName() + " For Cnt : " + lIntCnt);

                lLstDtlVO.add(lObjMedBillDtls);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error in generateTrnMedblDtls and Error is : " + e, e);
        }
        return lLstDtlVO;
    }


    /**
     * It generates Med Bill Specific TrnMedblAprvdDtls VO.
     *  
     * @param inputMap
     *            InputMap.
     * @return lLstAprvdDtls List<TrnMedblAprvdDtls>
     */
    private List<TrnMedblAprvdDtls> generateTrnMedblAprvdDtls(Map lMapInput)
    {
        List<TrnMedblAprvdDtls> lLstAprvdDtls = new ArrayList<TrnMedblAprvdDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");

        String[] lStrSection = null;
        String[] lStrClaimPeriod = null;
        String[] lStrAmt = null;
        TrnMedblAprvdDtls lObjAprvdDtl = null;

        try
        {
            lStrSection = requestArraySetter("txtEstablishmentTwo", request);
            lStrClaimPeriod = requestArraySetter("txtClaimPeriod", request);
            lStrAmt = requestArraySetter("txtAmtTwo", request);

            for (int lIntCnt = 0; lIntCnt < lStrSection.length; lIntCnt++)
            {
                lObjAprvdDtl = new TrnMedblAprvdDtls();
                lObjAprvdDtl.setCreatedUserId(gLngUserId);
                lObjAprvdDtl.setCreatedPostId(gLngPostId);
                lObjAprvdDtl.setCreatedDate(new Date(System.currentTimeMillis()));
                lObjAprvdDtl.setTrnCounter(1);

                lObjAprvdDtl.setSctnEstblshmnt(lStrSection[lIntCnt]);
                lObjAprvdDtl.setClaimAmt(new BigDecimal(lStrAmt[lIntCnt].length() != 0 ? lStrAmt[lIntCnt] : "0"));
                lObjAprvdDtl.setTrtmtTime(lStrClaimPeriod[lIntCnt]);
                lObjAprvdDtl.setLocationCode(gStrLocationCode);
                lObjAprvdDtl.setDbId(gLngDBId);

                lLstAprvdDtls.add(lObjAprvdDtl);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error in generateTrnMedblAprvdDtls and Error is : " + e, e);
        }

        return lLstAprvdDtls;
    }


    private void setSessionInfo(Map inputMap)
    {
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(request);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
    }
}
