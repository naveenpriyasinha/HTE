package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.PensionCaseDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionerRivisionDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionerRivisionDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionerSeenDtlsDao;
import com.tcs.sgv.pension.dao.TrnPensionerSeenDtlsDaoImpl;
import com.tcs.sgv.pension.dao.TrnPrvosionalPensionDtlsDao;
import com.tcs.sgv.pension.dao.TrnPrvosionalPensionDtlsDaoImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.MstPensionerNomineeDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionerSeenDtls;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;
import com.tcs.sgv.pension.valueobject.TrnPrvosionalPensionDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionCaseOutwrd;
import com.tcs.sgv.common.constant.DBConstants;

public class PensionCaseVOGenerator extends ServiceImpl implements VOGeneratorService
{
    ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pension/PensionConstants");

    /* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for LangId */
    Long gLngLangId = null;

    /* Global Variable for EmpId */
    Long gLngEmpId = null;

    /* Global Variable for Location Id */
    String gStrLocId = null;

    /* Global Variable for DB Id */
    Long gLngDBId = null;

    /* Global Variable for Location Code */
    String gStrLocationCode = null;

    String gStrAuditorFlag = null;

    String gStrStatus = null;

    Log gLogger = LogFactory.getLog(getClass());


    public ResultObject generateMap(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        TrnPensionerSeenDtls lObjTrnPensionerSeenDtls = null;
        TrnPrvosionalPensionDtls lObjProvisionDtlsVo = null;
        HttpServletRequest request = null;
        MstPensionerHdr mstPensionerHdrVO = new MstPensionerHdr();
        MstPensionerDtls mstPensionerDtlsVO = new MstPensionerDtls();
        TrnPensionRqstHdr trnPensionRqstHdrVO = new TrnPensionRqstHdr();
        ArrayList lArrylstMstPensionerNmDtlsVO = new ArrayList();
        ArrayList lArrylstMstPensionerFMDtlsVO = new ArrayList();
        try
        {
            setSessionInfo(inputMap);
            request = (HttpServletRequest) inputMap.get("requestObj");

            gStrStatus = StringUtility.getParameter("stauts", request);
            String lStrAuditFlag = StringUtility.getParameter("auditFlag", request);
            inputMap.put("AUDITFlag", lStrAuditFlag);
           
            trnPensionRqstHdrVO = (TrnPensionRqstHdr) generateTrnPensionRqstHdrVO(inputMap);

            mstPensionerDtlsVO = (MstPensionerDtls) generateMstPensionerDtlsVO(inputMap);
            mstPensionerHdrVO = (MstPensionerHdr) generateMstPensionerHdrVO(inputMap);
            lArrylstMstPensionerFMDtlsVO = (ArrayList) generateMstPensionerFamilyDtlsVOList(inputMap);
            lArrylstMstPensionerNmDtlsVO = (ArrayList) generateMstPensionerNomineeDtlsVOList(inputMap);

            if (!gStrStatus.equals(gObjRsrcBndle.getString("CMN.TYPEFLAGP")))
            {
                lObjTrnPensionerSeenDtls = generateTrnPensionerSeenDtls(inputMap);
                inputMap.put("TrnPensionerSeenDtls", lObjTrnPensionerSeenDtls);
            }

            if (gStrAuditorFlag != null)
            {
                trnPensionRqstHdrVO.setAuditorFlag(gStrAuditorFlag);
            }
            inputMap.put("TrnPensionRqstHdrVO", trnPensionRqstHdrVO);
            inputMap.put("MstPensionerDtlsVO", mstPensionerDtlsVO);
            inputMap.put("MstPensionerHdrVO", mstPensionerHdrVO);
            inputMap.put("MstPensionerFamilyDtlsVOArrlst", lArrylstMstPensionerFMDtlsVO);
            inputMap.put("MstPensionerNomineeDtlsVOArrlst", lArrylstMstPensionerNmDtlsVO);
            if( ! mstPensionerHdrVO.getLocationCode().equals(gStrLocationCode) )
            {
                ArrayList<TrnPensionCaseOutwrd> lLstTrnPensionCaseOutwrd =  generateOutwardPensionCase(inputMap);
                inputMap.put("TrnPensionCaseOutwrdList", lLstTrnPensionCaseOutwrd);
            }
            inputMap.put("InwdStatus", gStrStatus);
            inputMap.put("AuditFlag", lStrAuditFlag);
            if("Y".equalsIgnoreCase(trnPensionRqstHdrVO.getIsRop()))
            {
            	List<TrnPnsncaseRopRlt>  lLstRopVo = generateRopMap(inputMap) ;
                inputMap.put("ROPList", lLstRopVo);
            }
            TrnPensionerRivisionDtls trnPensionerRivisionDtlsVO = generateRevisionVO(inputMap);
            inputMap.put("TrnPensionerRivisionDtlsVO", trnPensionerRivisionDtlsVO);
            if (request.getParameter("isConverting") != null && request.getParameter("isConverting").length() > 0)
            {
                lObjProvisionDtlsVo = generateTrnProvisionDtlsVo(inputMap);
            }
            inputMap.put("TrnPrvosionalPensionDtls", lObjProvisionDtlsVo);
            resObj.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.info("Error is :" + e, e);
        }
        resObj.setResultValue(inputMap);
        return resObj;
    }


    // Sets session information in the global variables
    private void setSessionInfo(Map inputMap)
    {
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocId = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
    }


    public TrnPensionerRivisionDtls generateRevisionVO(Map inputMap) throws Exception
    {
        TrnPensionRqstHdr trnPnsnRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
        TrnPensionerRivisionDtls lObjTrnPensionerRivisionDtls = null;
        HttpServletRequest request = null;
        try
        {
            request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            lObjTrnPensionerRivisionDtls = new TrnPensionerRivisionDtls();
            String pensionerCode = StringUtility.getParameter("hidpensionerCode", request).trim();

            if (pensionerCode != null && pensionerCode.length() > 0)
            {
                TrnPensionerRivisionDtlsDAO lObjRivisionDao = new TrnPensionerRivisionDtlsDAOImpl(
                        TrnPensionerRivisionDtls.class, serv.getSessionFactory());
                Long lLngPk = lObjRivisionDao.getPKForRevisionDtls(pensionerCode);
                lObjTrnPensionerRivisionDtls = lObjRivisionDao.read(lLngPk);
                if(lObjTrnPensionerRivisionDtls != null)
                {
                	  lObjTrnPensionerRivisionDtls.setUpdatedDate(new Date());
                      lObjTrnPensionerRivisionDtls.setUpdatedPostId(new BigDecimal(gLngPostId));
                      lObjTrnPensionerRivisionDtls.setUpdatedUserId(new BigDecimal(gLngUserId));
                }
                else
                {
                	lObjTrnPensionerRivisionDtls = new TrnPensionerRivisionDtls(); 
                	lObjTrnPensionerRivisionDtls.setCreatedUserId(new BigDecimal(gLngUserId));
                    lObjTrnPensionerRivisionDtls.setCreatedPostId(new BigDecimal(gLngPostId));
                    lObjTrnPensionerRivisionDtls.setCreatedDate(new Date());
                    lObjTrnPensionerRivisionDtls.setRevisionCounter(new Byte("0"));
                }
            }
            else
            {
                lObjTrnPensionerRivisionDtls.setCreatedUserId(new BigDecimal(gLngUserId));
                lObjTrnPensionerRivisionDtls.setCreatedPostId(new BigDecimal(gLngPostId));
                lObjTrnPensionerRivisionDtls.setCreatedDate(new Date());
                lObjTrnPensionerRivisionDtls.setRevisionCounter(new Byte("0"));

            }

            lObjTrnPensionerRivisionDtls.setRivisionDate(new Date());
            lObjTrnPensionerRivisionDtls.setBasicPension(trnPnsnRqstHdrVO.getBasicPensionAmount());
            lObjTrnPensionerRivisionDtls.setCvpAmount(trnPnsnRqstHdrVO.getCvpAmount());
            lObjTrnPensionerRivisionDtls.setDcrgAmount(trnPnsnRqstHdrVO.getDcrgAmount());
            lObjTrnPensionerRivisionDtls.setCvpMonthlyAmount(trnPnsnRqstHdrVO.getCvpMonthlyAmount());
            lObjTrnPensionerRivisionDtls.setFp1Amount(trnPnsnRqstHdrVO.getFp1Amount());
            lObjTrnPensionerRivisionDtls.setFp2Amount(trnPnsnRqstHdrVO.getFp2Amount());
            lObjTrnPensionerRivisionDtls.setDpPercent(trnPnsnRqstHdrVO.getDpPercent());
            lObjTrnPensionerRivisionDtls.setTiPercent(trnPnsnRqstHdrVO.getTiPercent());
            lObjTrnPensionerRivisionDtls.setOrgBf11156(trnPnsnRqstHdrVO.getOrgBf11156());
            lObjTrnPensionerRivisionDtls.setRedBf11156(trnPnsnRqstHdrVO.getRedAf11156());
            lObjTrnPensionerRivisionDtls.setOrgAf11156(trnPnsnRqstHdrVO.getOrgAf11156());
            lObjTrnPensionerRivisionDtls.setRedAf11156(trnPnsnRqstHdrVO.getRedAf11156());
            lObjTrnPensionerRivisionDtls.setOrgAf10560(trnPnsnRqstHdrVO.getOrgAf10560());
            lObjTrnPensionerRivisionDtls.setRedAf10560(trnPnsnRqstHdrVO.getRedAf10560());
            lObjTrnPensionerRivisionDtls.setMedicalAllowenceAmount(trnPnsnRqstHdrVO.getMedicalAllowenceAmount());
            if (lObjTrnPensionerRivisionDtls.getActiveFlag() == null)
            {
                lObjTrnPensionerRivisionDtls.setActiveFlag(gObjRsrcBndle.getString("MNTH.Y"));
            }
            lObjTrnPensionerRivisionDtls.setDbId(new BigDecimal(gLngDBId));
            lObjTrnPensionerRivisionDtls.setLocationCode(gStrLocationCode);
        }
        catch (Exception e)
        {
            gLogger.info("Error is :" + e, e);
            throw e;
        }
        return lObjTrnPensionerRivisionDtls;
    }


    public TrnPensionRqstHdr generateTrnPensionRqstHdrVO(Map inputMap)
    {
        TrnPensionRqstHdr trnPnsnRqstHdrVO = new TrnPensionRqstHdr();
        String lStrDeptOrdNo = null;
        String lStrCvpOrderNo = null;
        Date lCvpDate = null;
        Date lCvpRestorianDate = null;
        BigDecimal lBgdcCvpAmnt = null;
        String lStrDcrgOrderNo = null;
        BigDecimal lShrtDpPrct = null;
        BigDecimal lShrtTiPrct = null;
        BigDecimal lBgdcMaAmnt = null;
        BigDecimal lBgdcDppfAmt = null;
        BigDecimal lBgdcPensionableAmnt = null;
        BigDecimal lBgdcHeadcode = null;
        String lStrCvpFlag = null;
        String lStrDcrgFlag = null;
        BigDecimal lBgdcCVPMonthlyAmnt = null;
        Date lPpoDate = null;
        String lStrsanctionLetterNo = null;
        String lStrsecondPpoNo = null;
        String lStrauthority = null;
        Date lDtClosedDate = null;
        String lStrSpecialCase = null;
        Date lDtEndDate = null;
        BigDecimal ir = null;
        BigDecimal personalPension = null;
        BigDecimal orgAf10560 = null;
        BigDecimal orgAf11156 = null;
        BigDecimal orgBf11156 = null;
        BigDecimal redAf10560 = null;
        BigDecimal redAf11156 = null;
        BigDecimal redBf11156 = null;
        String lStrForm22Issued = null;
        String lStrLpcIssued = null;
        Long lLngRqstId = 0L;
        try
        {
            setSessionInfo(inputMap);
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");

            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

            String lStrPpoNo = (StringUtility.getParameter("txtPpoNo", request).length() > 0) ? StringUtility
                    .getParameter("txtPpoNo", request) : null;
            String lStrPensionStatus = (StringUtility.getParameter("cmbPnsnStatus", request).length() > 0) ? StringUtility
                    .getParameter("cmbPnsnStatus", request)
                    : null;
            Date lPpoInwdDate = (StringUtility.getParameter("txtPpoInwdDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtPpoInwdDate", request)) : null;

            Date lCommensionDate = (StringUtility.getParameter("txtCommensionDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtCommensionDate", request))
                    : null;
            String lStrSchmeType = (StringUtility.getParameter("cmbSchemeType", request).length() > 0) ? StringUtility
                    .getParameter("cmbSchemeType", request)
                    : null;
            Date lDcrgDate = (StringUtility.getParameter("txtDcrgDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtDcrgDate", request)) : null;
            BigDecimal lBgdcDcrgAmnt = (StringUtility.getParameter("txtDcrgAmnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtDcrgAmnt", request))
                    : new BigDecimal(0);
            Date lFp1Date = (StringUtility.getParameter("txtFp1Date", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtFp1Date", request)) : null;
            BigDecimal lBgdcFp1Amnt = (StringUtility.getParameter("txtFp1Amnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtFp1Amnt", request))
                    : new BigDecimal(0);
            Date lFp2Date = (StringUtility.getParameter("txtFp2Date", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtFp2Date", request)) : null;
            BigDecimal lBgdcFp2Amnt = (StringUtility.getParameter("txtFp2Amnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtFp2Amnt", request))
                    : new BigDecimal(0);

            String lStrPensionType = (StringUtility.getParameter("cmbPnsnType", request).length() > 0) ? StringUtility
                    .getParameter("cmbPnsnType", request)
                    : null;
            BigDecimal lBgdcBasicAmnt = (StringUtility.getParameter("txtBasicPensionAmnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtBasicPensionAmnt", request))
                    : new BigDecimal(0);
            lStrDeptOrdNo = StringUtility.getParameter("txtDeptOdrNo", request).trim();
            String lStrTotSrvc = StringUtility.getParameter("hidTotSrvc", request).trim();
            lStrCvpOrderNo = (StringUtility.getParameter("txtCvpOrderNo", request).length() > 0) ? StringUtility
                    .getParameter("txtCvpOrderNo", request) : null;
            lCvpDate = (StringUtility.getParameter("txtCvpDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtCvpDate", request)) : null;
            lCvpRestorianDate = (StringUtility.getParameter("txtCvpRestorianDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtCvpRestorianDate", request))
                    : null;
            lBgdcCvpAmnt = (StringUtility.getParameter("txtCvpAmnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtCvpAmnt", request)) : null;
            lStrDcrgOrderNo = (StringUtility.getParameter("txtDcrgOrderNo", request).length() > 0) ? StringUtility
                    .getParameter("txtDcrgOrderNo", request) : null;
            lPpoDate = (StringUtility.getParameter("txtPpoDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtPpoDate", request)) : null;
            lShrtDpPrct = (StringUtility.getParameter("txtDpPrct", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtDpPrct", request)) : null;
            lShrtTiPrct = (StringUtility.getParameter("txtTiPrct", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtTiPrct", request)) : null;
            lBgdcMaAmnt = (StringUtility.getParameter("txtMaAmnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtMaAmnt", request)) : new BigDecimal(0);
            lBgdcDppfAmt = (StringUtility.getParameter("txtPensionableAmnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtPensionableAmnt", request)) : new BigDecimal(0);
            lBgdcPensionableAmnt = (StringUtility.getParameter("txtPensionableAmnt2", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtPensionableAmnt2", request))
                    : new BigDecimal(0);
            lBgdcHeadcode = (StringUtility.getParameter("cmbHeadCode", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("cmbHeadCode", request)) : null;
            lStrCvpFlag = (StringUtility.getParameter("radioCVP", request).length() > 0) ? StringUtility
                    .getParameter("radioCVP", request) : null;
            lStrDcrgFlag = (StringUtility.getParameter("radioDCRG", request).length() > 0) ? StringUtility
                    .getParameter("radioDCRG", request) : null;
            lBgdcCVPMonthlyAmnt = (StringUtility.getParameter("txtCvpMonthlyAmnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtCvpMonthlyAmnt", request))
                    : new BigDecimal(0);
            lStrsanctionLetterNo = (StringUtility.getParameter("SanAuthLeterNum", request).length() > 0) ? StringUtility
            .getParameter("SanAuthLeterNum", request)
            : null;
            lStrsecondPpoNo = (StringUtility.getParameter("txtSecndPPONum", request).length() > 0) ? StringUtility
                    .getParameter("txtSecndPPONum", request) : null;
            lStrauthority = (StringUtility.getParameter("sancAuthCmb", request).length() > 0) ? StringUtility
                    .getParameter("sancAuthCmb", request) : null;
            if(lStrauthority == null || lStrauthority.equalsIgnoreCase("-1"))
            {
            	lStrauthority = (StringUtility.getParameter("txtSancAuth", request).length() > 0) ? StringUtility
                        .getParameter("txtSancAuth", request) : null;
            }
            lDtClosedDate = (StringUtility.getParameter("txtPpoClsDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtPpoClsDate", request)) : null;
            lStrSpecialCase = (StringUtility.getParameter("radiosplCase", request).length() > 0) ? StringUtility
                    .getParameter("radiosplCase", request) : null;
            lDtEndDate = (StringUtility.getParameter("txtPPOEndDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtPPOEndDate", request)) : null;
            ir = (StringUtility.getParameter("txtIr", request).length() > 0) ? new BigDecimal(StringUtility
                    .getParameter("txtIr", request)) : new BigDecimal(0);
            personalPension = (StringUtility.getParameter("txtPersonalPension", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtPersonalPension", request))
                    : new BigDecimal(0);
            orgAf10560 = (StringUtility.getParameter("origPensn3", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("origPensn3", request)) : new BigDecimal(0);
            orgAf11156 = (StringUtility.getParameter("origPensn2", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("origPensn2", request)) : new BigDecimal(0);
            orgBf11156 = (StringUtility.getParameter("origPensn1", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("origPensn1", request)) : new BigDecimal(0);
            redAf10560 = (StringUtility.getParameter("rdcdPensn3", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("rdcdPensn3", request)) : new BigDecimal(0);
            redAf11156 = (StringUtility.getParameter("rdcdPensn2", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("rdcdPensn2", request)) : new BigDecimal(0);
            redBf11156 = (StringUtility.getParameter("rdcdPensn1", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("rdcdPensn1", request)) : new BigDecimal(0);
            String ppoNo = StringUtility.getParameter("hidPPONo", request);
            lStrLpcIssued = (StringUtility.getParameter("CmbLPC", request).length() > 0) ? StringUtility
                    .getParameter("CmbLPC", request) : null;
            lStrForm22Issued = (StringUtility.getParameter("CmbFrom22", request).length() > 0) ? StringUtility
                    .getParameter("CmbFrom22", request) : null;
            String lStrRemark = (StringUtility.getParameter("txtareaRemarks", request).length() > 0) ? StringUtility
                    .getParameter("txtareaRemarks", request) : null;
            String lstrProvPPo =  (StringUtility.getParameter("txtProvNum", request).length() > 0) ? StringUtility
                    .getParameter("txtProvNum", request) : null;
            String lStrCaseRegNum = (StringUtility.getParameter("txtRegisterNo", request).length() > 0) ? StringUtility
                    .getParameter("txtRegisterNo", request) : null;
            BigDecimal lBDDAPrcnt = (StringUtility.getParameter("txtDAPrcnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtDAPrcnt", request)):new BigDecimal(0);   
            String lStrisRop = (StringUtility.getParameter("cmbRop", request).length() > 0) ? 
                    StringUtility.getParameter("cmbRop", request):null; 
            String lStrCalcType = (StringUtility.getParameter("CmbCalcType", request).length() > 0) ? 
                    StringUtility.getParameter("CmbCalcType", request):null; 
          Date CaseRegDate =   (StringUtility.getParameter("txtRegDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtRegDate", request)) : null;        
                    
	        if(lstrProvPPo != null && lstrProvPPo.length()>0)
	        {
	        	ppoNo = lstrProvPPo;
	        }
	        TrnPensionRqstHdrDAOImpl lObjRqstHdrDao = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, serv
                    .getSessionFactory());
            if (ppoNo != null && ppoNo.length() > 0)
            {
                
                PensionCaseDAOImpl pensionCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv
                        .getSessionFactory());
                lLngRqstId = pensionCaseDAOImpl.getCaseIdfromPpoNo(ppoNo, gObjRsrcBndle.getString("CMN.NEW"));
                if (lLngRqstId <= 0)
                {
                    lLngRqstId = pensionCaseDAOImpl.getCaseIdfromPpoNo(ppoNo, gObjRsrcBndle
                            .getString("STATUS.APPROVED"));
                }
                trnPnsnRqstHdrVO = lObjRqstHdrDao.read(Long.valueOf(lLngRqstId));
                trnPnsnRqstHdrVO.setUpdatedDate(new Date());
                trnPnsnRqstHdrVO.setUpdatedPostId(new BigDecimal(gLngPostId));
                trnPnsnRqstHdrVO.setUpdatedUserId(new BigDecimal(gLngUserId));
            }
            else
            {
            	trnPnsnRqstHdrVO.setCaseStatus(gObjRsrcBndle.getString("CMN.NEW"));
               // trnPnsnRqstHdrVO.setcurrCaseStatus(Integer.parseInt(DBConstants.ST_BCRTD.toString()));
                trnPnsnRqstHdrVO.setApproveStatus(gObjRsrcBndle.getString("CMN.CASEINWD"));
                trnPnsnRqstHdrVO.setInwardMode(gObjRsrcBndle.getString("CMN.INWARDMODEPHYSICAL"));
                trnPnsnRqstHdrVO.setCreatedDate(new Date());
                trnPnsnRqstHdrVO.setCreatedPostId(new BigDecimal(gLngPostId));
                trnPnsnRqstHdrVO.setCreatedUserId(new BigDecimal(gLngUserId));
                trnPnsnRqstHdrVO.setTrnCounter(1);
                inputMap.put("isWorkFlow", "YES");
            }
            String lStrAFlag = (String) inputMap.get("AUDITFlag");
            if (lStrAFlag.equalsIgnoreCase("Y")
                    && trnPnsnRqstHdrVO.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVED")))
            {
            	trnPnsnRqstHdrVO.setcurrCaseStatus(Integer.parseInt(DBConstants.ST_BRJCT_AUD.toString()));
            	lObjRqstHdrDao.update(trnPnsnRqstHdrVO);
            	BigDecimal lBDCaseOwner = trnPnsnRqstHdrVO.getCaseOwner();
                trnPnsnRqstHdrVO = new TrnPensionRqstHdr();
                trnPnsnRqstHdrVO.setCaseStatus(gObjRsrcBndle.getString("CMN.NEW"));
                trnPnsnRqstHdrVO.setApproveStatus(gObjRsrcBndle.getString("STATUS.APPROVED"));
                trnPnsnRqstHdrVO.setCreatedDate(new Date());
                trnPnsnRqstHdrVO.setCreatedPostId(new BigDecimal(gLngPostId));
                trnPnsnRqstHdrVO.setCreatedUserId(new BigDecimal(gLngUserId));
                trnPnsnRqstHdrVO.setTrnCounter(1);
                trnPnsnRqstHdrVO.setCaseOwner(lBDCaseOwner);
                trnPnsnRqstHdrVO.setInwardMode(gObjRsrcBndle.getString("CMN.INWARDMODEPHYSICAL"));
                trnPnsnRqstHdrVO.setcurrCaseStatus(Integer.parseInt(DBConstants.ST_BAUD.toString()));
                inputMap.put("isWorkFlow", "YES");
            }
            trnPnsnRqstHdrVO.setPpoDate(lPpoDate);
            trnPnsnRqstHdrVO.setPensionType(lStrPensionType);
            trnPnsnRqstHdrVO.setHeadCode(lBgdcHeadcode);
            trnPnsnRqstHdrVO.setCvpOrderNo(lStrCvpOrderNo);
            trnPnsnRqstHdrVO.setCvpDate(lCvpDate);
            trnPnsnRqstHdrVO.setCvpRestorationDate(lCvpRestorianDate);
            trnPnsnRqstHdrVO.setCvpAmount(lBgdcCvpAmnt);
            trnPnsnRqstHdrVO.setDcrgOrderNo(lStrDcrgOrderNo);
            trnPnsnRqstHdrVO.setDppfAmount(lBgdcDppfAmt);
            trnPnsnRqstHdrVO.setPensionableAmount(lBgdcPensionableAmnt);
            trnPnsnRqstHdrVO.setMedicalAllowenceAmount(lBgdcMaAmnt);
            trnPnsnRqstHdrVO.setDpPercent(lShrtDpPrct);
            trnPnsnRqstHdrVO.setTiPercent(lShrtTiPrct);
            trnPnsnRqstHdrVO.setCvpMonthlyAmount(lBgdcCVPMonthlyAmnt);
            trnPnsnRqstHdrVO.setCvpPaidFlag(lStrCvpFlag);
            trnPnsnRqstHdrVO.setDcrgPaidFlag(lStrDcrgFlag);
            if (gStrStatus.toString().equals("P"))
            {
                trnPnsnRqstHdrVO.setDeptOrdNo(lStrDeptOrdNo);
                trnPnsnRqstHdrVO.setTypeFlag(gObjRsrcBndle.getString("CMN.TYPEFLAGP"));
            }
            trnPnsnRqstHdrVO.setTotalSrvc(lStrTotSrvc);
            trnPnsnRqstHdrVO.setPpoNo(lStrPpoNo);
            trnPnsnRqstHdrVO.setPpoInwardDate(lPpoInwdDate);
            trnPnsnRqstHdrVO.setStatus(lStrPensionStatus);
            trnPnsnRqstHdrVO.setSchemeType(lStrSchmeType);
            if (gStrStatus.equals("R"))
            {
                trnPnsnRqstHdrVO.setTypeFlag(gObjRsrcBndle.getString("CMN.TYPEFLAGR"));
            }

            if (gStrStatus.equals("C"))
            {
                trnPnsnRqstHdrVO.setTypeFlag(gObjRsrcBndle.getString("CMN.CONVERTTFLAG"));
            }
            trnPnsnRqstHdrVO.setSanctionLetterNo(lStrsanctionLetterNo);
            trnPnsnRqstHdrVO.setSecondPpoNo(lStrsecondPpoNo);
            trnPnsnRqstHdrVO.setAuthority(lStrauthority);
            trnPnsnRqstHdrVO.setClosedDate(lDtClosedDate);
            if (lStrSpecialCase != null && lStrSpecialCase.equals("Y"))
            {
                trnPnsnRqstHdrVO.setSpecialCase(lStrSpecialCase);
            }
            else
            {
                trnPnsnRqstHdrVO.setSpecialCase("N");
            }

            trnPnsnRqstHdrVO.setEndDate(lDtEndDate);
            trnPnsnRqstHdrVO.setForm22Issued(lStrForm22Issued);
            if (lStrForm22Issued != null && lStrForm22Issued.equalsIgnoreCase("Y"))
            {
                String lStrAuth = (StringUtility.getParameter("txtform22Auth", request).length() > 0) ? StringUtility
                        .getParameter("txtform22Auth", request) : null;
                String lStrDate = (StringUtility.getParameter("txtFrm22Date", request).length() > 0) ? StringUtility
                        .getParameter("txtFrm22Date", request) : null;
                trnPnsnRqstHdrVO.setForm22IssuedAuth(lStrAuth);
                trnPnsnRqstHdrVO.setForm22IssuedDate(lObjSmplDtFmt.parse(lStrDate));
            }

            trnPnsnRqstHdrVO.setLpcIssued(lStrLpcIssued);
            if (lStrLpcIssued != null && lStrLpcIssued.equalsIgnoreCase("Y"))
            {
                String lStrAuth = (StringUtility.getParameter("txtLPCAuth", request).length() > 0) ? StringUtility
                        .getParameter("txtLPCAuth", request) : null;
                String lStrDate = (StringUtility.getParameter("txtLPCDate", request).length() > 0) ? StringUtility
                        .getParameter("txtLPCDate", request) : null;
                trnPnsnRqstHdrVO.setLpcIssuedAuth(lStrAuth);
                trnPnsnRqstHdrVO.setLpcIssuedDate(lObjSmplDtFmt.parse(lStrDate));
            }
            trnPnsnRqstHdrVO.setRemark(lStrRemark);
            trnPnsnRqstHdrVO.setIr(ir);
            trnPnsnRqstHdrVO.setPersonalPension(personalPension);
            trnPnsnRqstHdrVO.setOrgAf10560(orgAf10560);
            trnPnsnRqstHdrVO.setOrgAf11156(orgAf11156);
            trnPnsnRqstHdrVO.setOrgBf11156(orgBf11156);
            trnPnsnRqstHdrVO.setRedAf10560(redAf10560);
            trnPnsnRqstHdrVO.setRedAf11156(redAf11156);
            trnPnsnRqstHdrVO.setRedBf11156(redBf11156);
            trnPnsnRqstHdrVO.setDcrgDate(lDcrgDate);
            trnPnsnRqstHdrVO.setDcrgAmount(lBgdcDcrgAmnt);
            trnPnsnRqstHdrVO.setFp1Date(lFp1Date);
            trnPnsnRqstHdrVO.setFp1Amount(lBgdcFp1Amnt);
            trnPnsnRqstHdrVO.setFp2Date(lFp2Date);
            trnPnsnRqstHdrVO.setFp2Amount(lBgdcFp2Amnt);
            trnPnsnRqstHdrVO.setBasicPensionAmount(lBgdcBasicAmnt);
            trnPnsnRqstHdrVO.setCommensionDate(lCommensionDate);
            trnPnsnRqstHdrVO.setDbId(new BigDecimal(gLngDBId));
            trnPnsnRqstHdrVO.setLocationCode(gStrLocId);
            trnPnsnRqstHdrVO.setDaPercent(lBDDAPrcnt);
            trnPnsnRqstHdrVO.setCaseRegisterNo(lStrCaseRegNum);
            trnPnsnRqstHdrVO.setIsRop(lStrisRop);
           if(lStrCalcType != null && lStrCalcType.equalsIgnoreCase("M"))
           {
        	   trnPnsnRqstHdrVO.setCalcType("M");
           }
           else
           {
        	   trnPnsnRqstHdrVO.setCalcType("A");
           }
           trnPnsnRqstHdrVO.setCaseRegDate(CaseRegDate);
        }
        catch (Exception e)
        {
            gLogger.info("Error is :" + e, e);
        }
        return trnPnsnRqstHdrVO;
    }


    public MstPensionerHdr generateMstPensionerHdrVO(Map inputMap)
    {
        MstPensionerHdr mstPensionerHdrVO = new MstPensionerHdr();
        try
        {
            setSessionInfo(inputMap);
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            Date lDateOfBirth = null;
            Date lDateOfJoin = null;
            Date lDateOfRetirement = null;
            String lStrPayScale = null;
            BigDecimal lBgdcLastPay = null;

            lDateOfBirth = (StringUtility.getParameter("txtDateOfBirth", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtDateOfBirth", request)) : null;
            lDateOfJoin = (StringUtility.getParameter("txtDateOfJoin", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtDateOfJoin", request)) : null;
            lDateOfRetirement = (StringUtility.getParameter("txtDateOfRetirement", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtDateOfRetirement", request))
                    : null;
            lStrPayScale = (StringUtility.getParameter("txtPayScale", request).length() > 0) ? StringUtility
                    .getParameter("txtPayScale", request) : null;
            lBgdcLastPay = (StringUtility.getParameter("txtLastPay", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtLastPay", request)) : null;
            String lStrFirstName = (StringUtility.getParameter("txtFirstName", request).length() > 0) ? StringUtility
                    .getParameter("txtFirstName", request) : null;
            String lStrPrefix = (StringUtility.getParameter("prefixName", request).length() > 0) ? StringUtility
                    .getParameter("prefixName", request) : null;
            String lStrMiddleName = (StringUtility.getParameter("txtMiddleName", request).length() > 0) ? StringUtility
                    .getParameter("txtMiddleName", request)
                    : null;
            String lStrLastName = (StringUtility.getParameter("txtLastName", request).length() > 0) ? StringUtility
                    .getParameter("txtLastName", request) : null;
            String lStrPensionerAddr = (StringUtility.getParameter("txtAddress", request).length() > 0) ? StringUtility
                    .getParameter("txtAddress", request)
                    : null;
            String lStrTeleNo = (StringUtility.getParameter("txtTelephoneNo", request).length() > 0) ? StringUtility
                    .getParameter("txtTelephoneNo", request) : null;
            String lStrAlive = (StringUtility.getParameter("radioAlive", request).length() > 0) ? StringUtility
                    .getParameter("radioAlive", request) : null;
            Date lDateOfDeath = (StringUtility.getParameter("txtDateOfDeath", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtDateOfDeath", request)) : null;
            String lStrPanNo = (StringUtility.getParameter("txtPanNo", request).length() > 0) ? StringUtility
                    .getParameter("txtPanNo", request) : null;
            String lStrGender = (StringUtility.getParameter("cmbGender", request).length() > 0) ? StringUtility
                    .getParameter("cmbGender", request) : null;
            String lStrOffAddr = (StringUtility.getParameter("txtOffAddress", request).length() > 0) ? StringUtility
                    .getParameter("txtOffAddress", request) : null;
            String lStrStateCode = (StringUtility.getParameter("cmbState", request).length() > 0) ? StringUtility
                    .getParameter("cmbState", request) : null;
            String lStrDiscrictCode = (StringUtility.getParameter("cmbDistrict", request).length() > 0) ? StringUtility
                    .getParameter("cmbDistrict", request)
                    : null;
            String lStrDepartment = (StringUtility.getParameter("cmbDepartment", request).length() > 0) ? StringUtility
                    .getParameter("cmbDepartment", request)
                    : null;
            String lStrHod = (StringUtility.getParameter("cmbHOD", request).length() > 0) ? StringUtility
                    .getParameter("cmbHOD", request) : null;
            String lStrClass = (StringUtility.getParameter("cmbClass", request).length() > 0) ? StringUtility
                    .getParameter("cmbClass", request) : null;
            String lStrCadre = (StringUtility.getParameter("cmbCadre", request).length() > 0) ? StringUtility
                    .getParameter("cmbCadre", request) : null;
            String lStrDesignation = (StringUtility.getParameter("cmbDesignation", request).length() > 0) ? StringUtility
                    .getParameter("cmbDesignation", request)
                    : null;
            String identityMark = (StringUtility.getParameter("txtidnMark", request).length() > 0) ? StringUtility
                    .getParameter("txtidnMark", request) : null;
            String height = (StringUtility.getParameter("txtHieght", request).length() > 0) ? 
                    StringUtility.getParameter("txtHieght", request) : null;
            String pensionerCode = StringUtility.getParameter("hidpensionerCode", request);
            String pnsnrEmailId = (StringUtility.getParameter("txtPnsnrEmailId", request).length() > 0) ? 
                    StringUtility.getParameter("txtPnsnrEmailId", request) : null;
            String officeEmailId = (StringUtility.getParameter("txtOffcEmailId", request).length() > 0) ? 
                    StringUtility.getParameter("txtOffcEmailId", request) : null;
            if (pensionerCode != null && pensionerCode.length() > 0)
            {
                MstPensionerHdrDAOImpl lObjMstHdrDao = new MstPensionerHdrDAOImpl(MstPensionerHdr.class, serv
                        .getSessionFactory());
                Long lLngPnsnrId = lObjMstHdrDao.getPensionerIdfromPensnrCode(pensionerCode, gObjRsrcBndle
                        .getString("STATUS.NEW"));
                if (lLngPnsnrId <= 0)
                {
                    lLngPnsnrId = lObjMstHdrDao.getPensionerIdfromPensnrCode(pensionerCode, gObjRsrcBndle
                            .getString("STATUS.APPROVED"));
                }
                mstPensionerHdrVO = lObjMstHdrDao.read(lLngPnsnrId);
                mstPensionerHdrVO.setUpdatedDate(new Date());
                mstPensionerHdrVO.setUpdatedPostId(new BigDecimal(gLngPostId));
                mstPensionerHdrVO.setUpdatedUserId(new BigDecimal(gLngUserId));
            }
            else
            {
                mstPensionerHdrVO.setCreatedDate(new Date());
                mstPensionerHdrVO.setCreatedPostId(new BigDecimal(gLngPostId));
                mstPensionerHdrVO.setCreatedUserId(new BigDecimal(gLngUserId));
                mstPensionerHdrVO.setCaseStatus(gObjRsrcBndle.getString("STATUS.NEW"));
            }
            String lStrAuditFlag = inputMap.get("AUDITFlag").toString();
            if (lStrAuditFlag.equalsIgnoreCase("Y")
                    && mstPensionerHdrVO.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVED")))
            {
                String lstrPensnrCode = mstPensionerHdrVO.getPensionerCode();
                mstPensionerHdrVO = new MstPensionerHdr();
                mstPensionerHdrVO.setCreatedDate(new Date());
                mstPensionerHdrVO.setCreatedPostId(new BigDecimal(gLngPostId));
                mstPensionerHdrVO.setCreatedUserId(new BigDecimal(gLngUserId));
                mstPensionerHdrVO.setCaseStatus(gObjRsrcBndle.getString("STATUS.NEW"));
                mstPensionerHdrVO.setPensionerCode(lstrPensnrCode);
            }
            mstPensionerHdrVO.setDateOfBirth(lDateOfBirth);
            mstPensionerHdrVO.setDateOfJoin(lDateOfJoin);
            mstPensionerHdrVO.setPayScale(lStrPayScale);
            mstPensionerHdrVO.setLastPay(lBgdcLastPay);
            mstPensionerHdrVO.setFirstName(lStrFirstName);
            if(lStrPrefix != null && ! "-1".equals(lStrPrefix))
            {
            	 mstPensionerHdrVO.setPnsnrPrefix(lStrPrefix);
            }
            mstPensionerHdrVO.setMiddleName(lStrMiddleName);
            mstPensionerHdrVO.setLastName(lStrLastName);
            mstPensionerHdrVO.setPensnerAddr(lStrPensionerAddr);
            mstPensionerHdrVO.setTeleNo(lStrTeleNo);
            mstPensionerHdrVO.setAliveFlag(lStrAlive);
            mstPensionerHdrVO.setDateOfDeath(lDateOfDeath);
            mstPensionerHdrVO.setDateOfRetirement(lDateOfRetirement);
            mstPensionerHdrVO.setPanNo(lStrPanNo);
            mstPensionerHdrVO.setGender(lStrGender);
            mstPensionerHdrVO.setOfficeAddr(lStrOffAddr);
            mstPensionerHdrVO.setStateCode(new BigDecimal(lStrStateCode));
            mstPensionerHdrVO.setDistrictCode(new BigDecimal(lStrDiscrictCode));
            mstPensionerHdrVO.setDeptCode(new BigDecimal(lStrDepartment));
            mstPensionerHdrVO.setHodCode(new BigDecimal(lStrHod));
            mstPensionerHdrVO.setClassType(lStrClass);
            mstPensionerHdrVO.setCadreType(lStrCadre);
            mstPensionerHdrVO.setDesignation(lStrDesignation);
            mstPensionerHdrVO.setIdentityMark(identityMark);
            mstPensionerHdrVO.setHeight(height);
            mstPensionerHdrVO.setOfficeEmailId(officeEmailId);
            mstPensionerHdrVO.setPnsnrEmailId(pnsnrEmailId);
//          Saving Scanned Attachment
                        
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            
            objRes = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);

            objRes = serv.executeService("FILE_UPLOAD_SRVC", inputMap);
            Map attachMap = (Map) objRes.getResultValue();

            if (attachMap.get("AttachmentId_scan") != null)
            {
                Long lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_scan")));
                mstPensionerHdrVO.setAttachmentPhotoId(new BigDecimal(lLngAttachId));
            }
            if (StringUtility.getParameter("cmbSubTreasury", request).length() > 0 && ! StringUtility.getParameter("cmbSubTreasury", request).toString().equalsIgnoreCase("-1")  && ! StringUtility.getParameter("cmbSubTreasury", request).equals(gStrLocationCode) )
            {
                 mstPensionerHdrVO.setLocationCode(StringUtility.getParameter("cmbSubTreasury", request));
            }
            else
            {
                   mstPensionerHdrVO.setLocationCode(gStrLocationCode);
            }
        }
        catch (Exception e)
        {
            gLogger.info("Error is :" + e, e);
        }
        return mstPensionerHdrVO;
    }


    public MstPensionerDtls generateMstPensionerDtlsVO(Map inputMap)
    {
        MstPensionerDtls mstPensionDtlsVO = new MstPensionerDtls();
        HttpServletRequest request = null;
        ServiceLocator serv = null;
        Long lLngDtlsId = null;
        try
        {
            setSessionInfo(inputMap);
            request = (HttpServletRequest) inputMap.get("requestObj");
            serv = (ServiceLocator) inputMap.get("serviceLocator");
            MstPensionerDtlsDAOImpl lObjPensionerDtlsDao = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class, serv
                    .getSessionFactory());
            String pensionerCode = StringUtility.getParameter("hidpensionerCode", request);
            request = (HttpServletRequest) inputMap.get("requestObj");
            String lStrBankCode = (StringUtility.getParameter("cmbBank", request).length() > 0) ? StringUtility
                    .getParameter("cmbBank", request) : null;
            String lStrAcNo = (StringUtility.getParameter("txtACNo", request).length() > 0) ? StringUtility
                    .getParameter("txtACNo", request) : null;
            String lStrBranchCode = (StringUtility.getParameter("cmbBranch", request).length() > 0) ? StringUtility
                    .getParameter("cmbBranch", request) : null;
            String lStrLocationcode = (StringUtility.getParameter("cmbTreasury", request).length() > 0) ? StringUtility
                    .getParameter("cmbTreasury", request)
                    : null;
            String bankEmailId = (StringUtility.getParameter("txtBankEmailId", request).length() > 0) ? StringUtility
                    .getParameter("txtBankEmailId", request)
                    : null;
            if (pensionerCode != null && pensionerCode.length() > 0)
            {
                lLngDtlsId = lObjPensionerDtlsDao.getPnsionerDtlsIdFromPensionerCode(pensionerCode, gObjRsrcBndle
                        .getString("STATUS.NEW"));
                if (lLngDtlsId <= 0)
                {
                    lLngDtlsId = lObjPensionerDtlsDao.getPnsionerDtlsIdFromPensionerCode(pensionerCode, gObjRsrcBndle
                            .getString("STATUS.APPROVED"));
                }
                mstPensionDtlsVO = lObjPensionerDtlsDao.read(lLngDtlsId);
                if(mstPensionDtlsVO.getBankCode()!= null && mstPensionDtlsVO.getBankCode().equals(lStrBankCode))
                {
                    if (mstPensionDtlsVO.getBranchCode().toString().equals(lStrBranchCode))
                    {
                        gStrAuditorFlag = "0";
                    }
                    else
                    {
                        gStrAuditorFlag = "1";
                    }
                }

                mstPensionDtlsVO.setUpdatedDate(new Date());
                mstPensionDtlsVO.setUpdatedPostId(new BigDecimal(gLngPostId));
                mstPensionDtlsVO.setUpdatedUserId(new BigDecimal(gLngUserId));
            }
            else
            {
                mstPensionDtlsVO.setCreatedDate(new Date());
                mstPensionDtlsVO.setCreatedPostId(new BigDecimal(gLngPostId));
                mstPensionDtlsVO.setCreatedUserId(new BigDecimal(gLngUserId));
                mstPensionDtlsVO.setCaseStatus(gObjRsrcBndle.getString("STATUS.NEW"));
            }
            String lStrAuditFlag = inputMap.get("AUDITFlag").toString();
            if (lStrAuditFlag.equalsIgnoreCase(gObjRsrcBndle.getString("CMN.Y"))
                    && mstPensionDtlsVO.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVED")))
            {
                mstPensionDtlsVO = new MstPensionerDtls();
                mstPensionDtlsVO.setCreatedDate(new Date());
                mstPensionDtlsVO.setCreatedPostId(new BigDecimal(gLngPostId));
                mstPensionDtlsVO.setCreatedUserId(new BigDecimal(gLngUserId));
                mstPensionDtlsVO.setCaseStatus(gObjRsrcBndle.getString("STATUS.NEW"));
            }
            mstPensionDtlsVO.setAcountNo(lStrAcNo);
            mstPensionDtlsVO.setBankCode(lStrBankCode);
            mstPensionDtlsVO.setBranchCode(lStrBranchCode);
            mstPensionDtlsVO.setLocationCode(lStrLocationcode);
            mstPensionDtlsVO.setBankEmailId(bankEmailId);
            mstPensionDtlsVO.setActiveFlag(gObjRsrcBndle.getString("CMN.Y"));

        }
        catch (Exception e)
        {
            gLogger.info("Error is :" + e, e);
        }
        return mstPensionDtlsVO;
    }


    public ArrayList<MstPensionerFamilyDtls> generateMstPensionerFamilyDtlsVOList(Map inputMap)
    {
        SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");
        HttpServletRequest request = null;
        ArrayList lArrylstMstPensionerFMDtlsVO = new ArrayList();
        try
        {
            setSessionInfo(inputMap);
            request = (HttpServletRequest) inputMap.get("requestObj");
            String[] lStrFmName = StringUtility.getParameterValues("txtFmName", request);
            String[] lStrRelation = null;
            String[] lStrFMPercent = null;
            String[] lStrBankCode = null;
            String[] lStrBranchCode = null;
            String[] lStrFMACCNo = null;
            String[] lStrDateOfBirth = null;
            String[] lStrFMSalary = null;
            String[] lStrHandiCap = null;
            String[] lStrGurdianName = null;
            String[] lStrFMDateOfDeath = null;
            lStrRelation = StringUtility.getParameterValues("cmbRelation", request);
            lStrFMPercent = StringUtility.getParameterValues("txtFMPercent", request);
            lStrBankCode = StringUtility.getParameterValues("cmbBankFd", request);
            lStrBranchCode = StringUtility.getParameterValues("cmbBranchFd", request);
            lStrFMACCNo = StringUtility.getParameterValues("txtFMAcntNo", request);
            lStrDateOfBirth = StringUtility.getParameterValues("txtFMDateOfBirth", request);
            lStrFMSalary = StringUtility.getParameterValues("txtFMSalary", request);
            lStrHandiCap = StringUtility.getParameterValues("chkbxHandiCap", request);
            lStrGurdianName = StringUtility.getParameterValues("txtFMGardianName", request);
            lStrFMDateOfDeath = StringUtility.getParameterValues("txtFMDateOfDeath", request);
            for (int i = 0; i < lStrFmName.length; i++)
            {
                MstPensionerFamilyDtls lObjMstPensionerFamilyDtls = new MstPensionerFamilyDtls();
                 if (lStrFmName[i].length() > 0)
                    {
                        lObjMstPensionerFamilyDtls.setName(lStrFmName[i]);
                        if (lStrRelation[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setRelationship(lStrRelation[i]);
                        }
                        
                        if (lStrFMACCNo[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setAccntNo(lStrFMACCNo[i]);
                        }
                        if (lStrFMPercent[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setPercentage(Long.valueOf(lStrFMPercent[i]));
                        }
                        if (lStrBankCode[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setBankCode(lStrBankCode[i]);
                        }
                        if (lStrBranchCode[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setBranchCode(lStrBranchCode[i]);
                        }
                        if (lStrDateOfBirth[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setDateOfBirth(lObjSmplDtFmt.parse(lStrDateOfBirth[i]));
                        }
                        if(request.getParameter("chkbxFMMajorMinor"+i) != null )
                        {
                            lObjMstPensionerFamilyDtls.setMajorFlag("N");
                        }
                        else
                        {
                            lObjMstPensionerFamilyDtls.setMajorFlag("Y");
                        }
                       
                        if (request.getParameter("chkbxFMMarried"+i) != null)
                        {
                                lObjMstPensionerFamilyDtls.setMarriedFlag("Y");
                        }
                        else
                        {
                            lObjMstPensionerFamilyDtls.setMarriedFlag("N");
                        }
                        if (lStrFMSalary[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setSalary(BigDecimal.valueOf(Double.valueOf(lStrFMSalary[i])));
                        }
                        if (lStrHandiCap[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setHandicapeFlag(lStrHandiCap[i]);
                        }
                        else
                        {
                            lObjMstPensionerFamilyDtls.setHandicapeFlag("N");
                        }
                        if (lStrGurdianName[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setGuardianName(lStrGurdianName[i]);
                        }
                        if (lStrFMDateOfDeath[i].length() > 0)
                        {
                            lObjMstPensionerFamilyDtls.setDateOfDeath(lObjSmplDtFmt.parse(lStrFMDateOfDeath[i]));
                        }
                        lObjMstPensionerFamilyDtls.setCreatedUserId(gLngUserId);
                        lObjMstPensionerFamilyDtls.setCreatedPostId(gLngPostId);
                        lObjMstPensionerFamilyDtls.setCreatedDate(new Date(System.currentTimeMillis()));
                        lObjMstPensionerFamilyDtls.setTrnCounter(1);
                        lArrylstMstPensionerFMDtlsVO.add(lObjMstPensionerFamilyDtls);
                    }
                }
        }
        catch (Exception e)
        {
            gLogger.info("Error" + e, e);
        }
        return lArrylstMstPensionerFMDtlsVO;
    }


    public ArrayList<MstPensionerNomineeDtls> generateMstPensionerNomineeDtlsVOList(Map inputMap)
    {
        ArrayList lArrylstMstPnsnrNmDtlsVO = new ArrayList();
        try
        {
            String[] lStrNmName = null;
            String[] lStrPrct = null;
            setSessionInfo(inputMap);
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            lStrNmName = StringUtility.getParameterValues("txtNMName", request);
            lStrPrct = StringUtility.getParameterValues("txtNMPercentage", request);
            for (int lIntCnt = 0; lIntCnt < lStrNmName.length; lIntCnt++)
            {
                MstPensionerNomineeDtls mstPnsnrNmDtls = new MstPensionerNomineeDtls();

                if (lStrNmName[lIntCnt] != null && lStrNmName[lIntCnt].length() > 0)
                {
                    mstPnsnrNmDtls.setName(lStrNmName[lIntCnt]);

                    if (lStrPrct[lIntCnt] != null && lStrPrct[lIntCnt].length() > 0)
                    {
                        mstPnsnrNmDtls.setPercent(new BigDecimal(lStrPrct[lIntCnt]));
                    }
                    mstPnsnrNmDtls.setCreatedDate(new Date());
                    mstPnsnrNmDtls.setCreatedPostId(new BigDecimal(gLngPostId));
                    mstPnsnrNmDtls.setCreatedUserId(new BigDecimal(gLngUserId));
                    mstPnsnrNmDtls.setTrnCounter(1);
                    lArrylstMstPnsnrNmDtlsVO.add(mstPnsnrNmDtls);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.info("Error is :" + e, e);
        }
        return lArrylstMstPnsnrNmDtlsVO;
    }


    public TrnPensionerSeenDtls generateTrnPensionerSeenDtls(Map inputMap)
    {
        Date lDtSeenDate = null;
        HttpServletRequest request = null;
        TrnPensionerSeenDtls lObjVo = null;
        SimpleDateFormat lObjDtFmt = new SimpleDateFormat("dd/MM/yyyy");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        try
        {
            request = (HttpServletRequest) inputMap.get("requestObj");
            lObjVo = new TrnPensionerSeenDtls();
            String lStrDate = StringUtility.getParameter("txtSeenDate", request);
            String lStrSeenFlag = (StringUtility.getParameter("radioSeen", request).length() > 0) ? StringUtility
                    .getParameter("radioSeen", request) : null;
            String lStrPk = StringUtility.getParameter("hidpensionerSeenDtlsId", request);

            if (lStrPk != null && lStrPk.length() > 0)
            {
                TrnPensionerSeenDtlsDao lObjSeenDtls = new TrnPensionerSeenDtlsDaoImpl(TrnPensionerSeenDtls.class,
                        serv.getSessionFactory());
                lObjVo = lObjSeenDtls.read(Long.valueOf(lStrPk));
                lObjVo.setUpdatedDate(new Date());
                lObjVo.setUpdatedPostId(BigDecimal.valueOf(Long.valueOf(gLngPostId)));
                lObjVo.setUpdatedUserId(BigDecimal.valueOf(Long.valueOf(gLngUserId)));
            }
            else
            {
                lObjVo.setCreatedDate(new Date());
                lObjVo.setCreatedPostId(new BigDecimal(gLngPostId));
                lObjVo.setCreatedUserId(new BigDecimal(gLngUserId));
            }
            if (lStrDate.length() > 0)
            {
                lDtSeenDate = lObjDtFmt.parse(lStrDate);
                lObjVo.setSeenDate(lDtSeenDate);
            }
            lObjVo.setSeenFlage(lStrSeenFlag);
        }
        catch (Exception e)
        {
            gLogger.info("Error is :" + e, e);
        }
        return lObjVo;
    }


    public TrnPrvosionalPensionDtls generateTrnProvisionDtlsVo(Map inputMap)
    {

        TrnPrvosionalPensionDtls lObjVo = new TrnPrvosionalPensionDtls();
        HttpServletRequest request = null;
        SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        try
        {
            request = (HttpServletRequest) inputMap.get("requestObj");
            Date lDcrgDate = (StringUtility.getParameter("txtDcrgDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtDcrgDate", request)) : null;
            BigDecimal lBgdcDcrgAmnt = (StringUtility.getParameter("txtDcrgAmnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtDcrgAmnt", request))
                    : null;
            Date lFp1Date = (StringUtility.getParameter("txtFp1Date", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtFp1Date", request)) : null;
            BigDecimal lBgdcFp1Amnt = (StringUtility.getParameter("txtFp1Amnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtFp1Amnt", request))
                    : null;
            Date lFp2Date = (StringUtility.getParameter("txtFp2Date", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtFp2Date", request)) : null;
            BigDecimal lBgdcFp2Amnt = (StringUtility.getParameter("txtFp2Amnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtFp2Amnt", request))
                    : null;
            BigDecimal lBgdcBasicAmnt = (StringUtility.getParameter("txtBasicPensionAmnt", request).length() > 0) ? new BigDecimal(
                    StringUtility.getParameter("txtBasicPensionAmnt", request))
                    : null;
            String lStrPpoNo = (StringUtility.getParameter("txtPpoNo", request).length() > 0) ? StringUtility
                    .getParameter("txtPpoNo", request) : null;
            Date lCommensionDate = (StringUtility.getParameter("txtCommensionDate", request).length() > 0) ? lObjSmplDtFmt
                    .parse(StringUtility.getParameter("txtCommensionDate", request))
                    : null;
            String pensionerCode = StringUtility.getParameter("hidpensionerCode", request);
            TrnPrvosionalPensionDtlsDao lObjDaoProvDtls = new TrnPrvosionalPensionDtlsDaoImpl(
                    TrnPrvosionalPensionDtls.class, serv.getSessionFactory());
            Long lBDProvisionalId = lObjDaoProvDtls.getPrvosionalPensionDtlsIdByPenCode(pensionerCode);
            if (lBDProvisionalId != null)
            {
                lObjVo = lObjDaoProvDtls.read(lBDProvisionalId);
                lObjVo.setUpdatedDate(new Date());
                lObjVo.setUpdatedPostId(BigDecimal.valueOf(Long.valueOf(gLngPostId)));
                lObjVo.setUpdatedUserId(BigDecimal.valueOf(Long.valueOf(gLngUserId)));
            }
            else
            {
                lObjVo.setCreatedUserId(new BigDecimal(gLngUserId));
                lObjVo.setCreatedPostId(new BigDecimal(gLngPostId));
                lObjVo.setCreatedDate(new Date());
                lObjVo.setTrnCounter(BigDecimal.valueOf(1));
            }
            lObjVo.setPpoNo(lStrPpoNo);
            lObjVo.setDcrgDate(lDcrgDate);
            lObjVo.setFp1Amount(lBgdcFp1Amnt);
            lObjVo.setFp2Amount(lBgdcFp2Amnt);
            lObjVo.setFp1Date(lFp1Date);
            lObjVo.setFp2Date(lFp2Date);
            lObjVo.setBasicPensionAmount(lBgdcBasicAmnt);
            lObjVo.setCommensionDate(lCommensionDate);
            lObjVo.setDcrgAmount(lBgdcDcrgAmnt);
        }
        catch (Exception e)
        {
            gLogger.error("Error is" + e, e);
        }
        return lObjVo;
    }
    private ArrayList<TrnPensionCaseOutwrd> generateOutwardPensionCase(Map inputMap)
    {
        HttpServletRequest request = null;
        SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");
        TrnPensionCaseOutwrd lObjPnsnCaseOtwrd = null;
        ArrayList<TrnPensionCaseOutwrd> lLstCaseOutward = new ArrayList<TrnPensionCaseOutwrd>();
        try
        {
             request = (HttpServletRequest) inputMap.get("requestObj");
             String[] lStrInwrdDate = StringUtility.getParameterValues("txtOutWrdRemarks", request);
             if(lStrInwrdDate != null && lStrInwrdDate.length >0)
             {
                 for(int i=0;i<lStrInwrdDate.length;i++)
                 {
                     lObjPnsnCaseOtwrd = new TrnPensionCaseOutwrd();
                     Date lDtOutwardDate = (request.getParameter("txtOutwardDt"+i)!= null && request.getParameter("txtOutwardDt"+i).length() > 0) ? lObjSmplDtFmt
                             .parse(request.getParameter("txtOutwardDt"+i)) : null;
                     Date lDtInwardDate =  (request.getParameter("txtInwardDt"+i)!= null && request.getParameter("txtInwardDt"+i).length() > 0) ? lObjSmplDtFmt
                             .parse(request.getParameter("txtInwardDt"+i)) : null;
                     String  lStrLocCode = (StringUtility.getParameter("cmbSubTreasury", request).length() > 0) ? 
                             (StringUtility.getParameter("cmbSubTreasury",request)) : null;        
                     String lStrStatus = (StringUtility.getParameter("inWardStatus"+i, request).length() > 0) ? 
                             (StringUtility.getParameter("inWardStatus"+i, request)) : null;        
                     
                     if(lStrInwrdDate[i] != null && lStrInwrdDate[i].length() > 0)
                     {
                         lObjPnsnCaseOtwrd.setRemarks(lStrInwrdDate[i]);
                     }
                     lObjPnsnCaseOtwrd.setOutwrdDate(lDtOutwardDate);
                     lObjPnsnCaseOtwrd.setInwrdDate(lDtInwardDate);
                     lObjPnsnCaseOtwrd.setSubtrsryCode(lStrLocCode);
                     lObjPnsnCaseOtwrd.setStatus(lStrStatus);
                     lObjPnsnCaseOtwrd.setCreatedUserId(new BigDecimal(gLngUserId));
                     lObjPnsnCaseOtwrd.setCreatedPostId(new BigDecimal(gLngPostId));
                     lObjPnsnCaseOtwrd.setCreatedDate(new Date());
                     lObjPnsnCaseOtwrd.setTrnCounter(1);
                     if(lDtOutwardDate != null && lDtOutwardDate.toString().length() > 0)
                     {
                         lLstCaseOutward.add(lObjPnsnCaseOtwrd);
                     }
                 }
             }
        }
        catch(Exception e)
        {
            gLogger.error("Error is" + e, e);
        }
        return lLstCaseOutward;
    }
    public List<TrnPnsncaseRopRlt> generateRopMap(Map orgsMap) 
	{
		HttpServletRequest request = null;
        setSessionInfo(orgsMap);
        List<TrnPnsncaseRopRlt> lLstRopVo = new ArrayList<TrnPnsncaseRopRlt>();
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        request = (HttpServletRequest) orgsMap.get("requestObj");
        try
        {
        	String[]  lStrropChkBox = StringUtility.getParameterValues("ropChkBox", request);
        	String lStrPPONo = StringUtility.getParameter("hidPPONo", request);
        	for(int i=0;i<lStrropChkBox.length;i++)
        	{
        		TrnPnsncaseRopRlt lObjRopVo = new TrnPnsncaseRopRlt();
        		if(lStrropChkBox[i].equals("1986"))
        		{
        			String lstrAdaptType = StringUtility.getParameter("hidAdptedType", request);
        			String lstrAdaptFlag = StringUtility.getParameter("hidAdptedFlag", request);
        			lObjRopVo.setAdaptedType(lstrAdaptType+lstrAdaptFlag);
        		}
        		lObjRopVo.setRop(lStrropChkBox[i]);
        		lObjRopVo.setPpoNo(lStrPPONo);
        		lObjRopVo.setCreatedDate(new Date());
        		lObjRopVo.setCreatedPostId(new BigDecimal(gLngPostId));
        		lObjRopVo.setCreatedUserId(new BigDecimal(gLngUserId));
        		lLstRopVo.add(lObjRopVo);
        	}
        	resObj.setResultValue(orgsMap);
        }
        catch(Exception e)
        {
        	gLogger.error("Error is"+e,e);
        }
		return lLstRopVo;
	}
}