/**
 * package : com.tcs.sgv.onlinebillprep.service 
 * @verion : 1.0
 * @author : Keyur Shah, Nirav Bumia 
 ** 
 */
package com.tcs.sgv.onlinebillprep.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.common.dao.RemarksDAOImpl;
import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.billproc.counter.dao.BdgtHeadDtlsDAOImpl;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.common.dao.BillMovementDAOImpl;
import com.tcs.sgv.common.dao.BudgetHdDtlsDAO;
import com.tcs.sgv.common.dao.BudgetHdDtlsDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.LocationDAO;
import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.DDOInfoServiceImpl;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.RltBillParty;
import com.tcs.sgv.common.valueobject.RltLocationDepartment;
import com.tcs.sgv.common.valueobject.SgvaBudbpnMapping;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnRcptBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataService;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.exprcpt.dao.RcptBudDtlsDAOImpl;
import com.tcs.sgv.exprcpt.dao.ReceiptDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAO;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnChallanPartyDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnChallanPartyDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnOnlinebillEmpDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnOnlinebillEmpDAOImpl;
import com.tcs.sgv.onlinebillprep.util.ConfigOnlineBillUtil;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;
import com.tcs.sgv.onlinebillprep.valueobject.ConfigOnlineBill;
import com.tcs.sgv.onlinebillprep.valueobject.RltBillRqst;
import com.tcs.sgv.onlinebillprep.valueobject.TrnChallanParty;
import com.tcs.sgv.onlinebillprep.valueobject.TrnOnlinebillEmp;

public class OnlineBillVOGenerator extends ServiceImpl
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

    private static ResourceBundle bundleConst = ResourceBundle
            .getBundle("resources/onlinebillprep/OnlineBillConstants");

    /* Global Variable for Logger Class */
    Log gLogger = LogFactory.getLog(getClass());


    /**
     * 
     *  
     * @param inputMap
     *            InputMap
     * @return objRes ResultObject
     */
    public ResultObject generateMap(Map lMapInput)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");

        try
        {
            HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
            setSessionInfo(lMapInput);

            gLogger.info("request.getParameterMap() is : " + request.getParameterMap());

            String lStrBillNo = OnlineBillUtil.requestSetter("hidBillNo", request);

            BillCommonDAO lObjBillDAO = new BillCommonDAOImpl(srvcLoc.getSessionFactory());

            SgvcFinYearMst lFinYrVO = lObjBillDAO.getFinYrInfo(Calendar.getInstance().getTime(), gLngLangId);
            BudgetHdDtlsDAO lObjBudHdDtls = new BudgetHdDtlsDAOImpl(Long.parseLong(lFinYrVO.getFinYearId() + ""),
                    srvcLoc.getSessionFactory());
            SgvaBudbpnMapping lObjBPN = lObjBudHdDtls.getBPNInfoFrmDmd(OnlineBillUtil.requestSetter("txtDmd", request),
                    gLngLangId);

            lMapInput.put("FinYrId", lFinYrVO.getFinYearId());
            lMapInput.put("BPNVO", lObjBPN);

            TrnBillRegister lObjBillReg = generateBillRegVO(lMapInput);
            
            
            TrnBillBudheadDtls lObjBudHead = generateBudHeadVO(lMapInput);

            //commented by Ankit Bhatt
            /*TrnOnlinebillEmp lObjOnlineBillEmp = generateOnlineBillEmp(lMapInput); //Vipul
            TrnBillMvmnt lObjBillMvmnt = generateBillMvmntVO(lMapInput);
            TrnBillRemarks lObjBillRmrks = generateBillRmrksVO(lMapInput);

            List<RltBillRqst> lLstBillRqst = generateBillRqstVO(lMapInput);
            List<RltBillParty> lLstBillParty = generateBillPartyVO(lMapInput);*/
            //comment ended
            
            RptExpenditureDtls lObjRptHdr = getDSSRptHdrVO(lMapInput);
            ConfigOnlineBill lObjConfigBill = ConfigOnlineBillUtil.getInstance(lObjBillReg.getSubjectId(), srvcLoc);
            //System.out.println("lObjConfigBill.getVogen() ankit " + lObjConfigBill);
            lMapInput.put("BillRegVO", lObjBillReg);            
            lMapInput.put("BillHeadVO", lObjBudHead);
            
            //commented by Ankit Bhatt
            /*lMapInput.put("EmpDtlsVO", lObjOnlineBillEmp); //Vipul
            lMapInput.put("BillMvmntVO", lObjBillMvmnt);
            lMapInput.put("BillRmrksVO", lObjBillRmrks);*/
            //comment ended

            gLogger.info("lStrBillNo in OnlineBillVOGEN is : " + lStrBillNo);

            
            //commented by Ankit Bhatt
            /*if (lStrBillNo.trim().length() == 0) // Insert data in BillRqst
            // only at time of bill crt
            {
                lMapInput.put("BillRqstDtls", lLstBillRqst);
            }

            if (lLstBillParty != null)
            {
                lMapInput.put("BillPartyDtls", lLstBillParty);
            }*/
            //comment ended
            
            lMapInput.put("DSSRptHdr", lObjRptHdr);

            // Calling Bill Specific Service.
            ResultObject lRsBillResult = srvcLoc.executeService(lObjConfigBill.getVogen(),lMapInput);
            Map lMapBill = (Map) lRsBillResult.getResultValue();
            
//          added by Ankit Bhatt for setting Paybill Parameter's value.
            gLogger.info("Grade selection in OnlineVO is " + OnlineBillUtil.requestSetter("txtGradeId",request));
            
            lMapInput.put("month", OnlineBillUtil.requestSetter("selMonth",request));
            lMapInput.put("year", OnlineBillUtil.requestSetter("selYear",request));
            lMapInput.put("grade", OnlineBillUtil.requestSetter("txtGradeId",request));
            lMapInput.put("deptNo", OnlineBillUtil.requestSetter("cmbDept",request));            
            //ended by Ankit Bhatt.

            
            lMapInput.put("BillMap", lMapBill);

            objRes.setResultValue(lMapInput);
        }
        catch (Exception e)
        {
            gLogger.error("Error in OnlineBillVOGenerator and Error is : " + e, e);
            e.printStackTrace();
        }
        return objRes;
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
        gDtCurrDt = new Date(System.currentTimeMillis());
    }


    private TrnBillBudheadDtls generateBudHeadVO(Map lMapInput)
    {
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        TrnBillBudheadDtls lObjBudHead = new TrnBillBudheadDtls();
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");

        BdgtHeadDtlsDAOImpl lObjBdgtHeadDtlsDAOImpl = new BdgtHeadDtlsDAOImpl(TrnBillBudheadDtls.class, srvcLoc
                .getSessionFactory());

        SgvaBudbpnMapping lObjBPN = (SgvaBudbpnMapping) lMapInput.get("BPNVO");

        String lStrBillBudHdId = OnlineBillUtil.requestSetter("hidBillBudHdId", request);

        if (lStrBillBudHdId.trim().length() > 0)
        {
            lObjBudHead = lObjBdgtHeadDtlsDAOImpl.read(Long.parseLong(lStrBillBudHdId));
            lObjBudHead.setUpdatedDate(gDtCurrDt);
            lObjBudHead.setUpdatedUserId(gLngUserId);
            lObjBudHead.setUpdatedPostId(gLngPostId);
        }
        else
        {
            lObjBudHead.setCreatedUserId(gLngUserId);
            lObjBudHead.setCreatedPostId(gLngPostId);
            lObjBudHead.setCreatedDate(gDtCurrDt);
            lObjBudHead.setTrnCounter(1);
        }

        String lStrFundType = OnlineBillUtil.requestSetter("cmbFund", request);
        String lStrBudType = OnlineBillUtil.requestSetter("cmbBudType", request);
        String lStrClsOfExp = OnlineBillUtil.requestSetter("cmbClsOfExp", request);

        if (lStrFundType.length() > 0)
        {
            lObjBudHead.setFund(lStrFundType);
        }

        lObjBudHead.setSchemeNo(OnlineBillUtil.requestSetter("txtSchemeNo", request));
        lObjBudHead.setDmndNo(OnlineBillUtil.requestSetter("txtDmd", request));

        if (lObjBPN != null)
        {
            lObjBudHead.setBpnNo(lObjBPN.getBpncode());
        }

        lObjBudHead.setBudMjrHd(OnlineBillUtil.requestSetter("txtMjrHd", request));
        lObjBudHead.setBudSubmjrHd(OnlineBillUtil.requestSetter("txtSbMjrHd", request));
        lObjBudHead.setBudMinHd(OnlineBillUtil.requestSetter("txtMnrHd", request));
        lObjBudHead.setBudSubHd(OnlineBillUtil.requestSetter("txtSbHd", request));
        lObjBudHead.setBudDtlHd(OnlineBillUtil.requestSetter("txtDtldHd", request));
        lObjBudHead.setVersionId(0L);
        lObjBudHead.setLocationCode(gStrLocationCode);
        lObjBudHead.setDbId(gLngDBId);

        if (lStrBudType.length() > 0)
        {
            lObjBudHead.setBudType(lStrBudType);
        }

        if (lStrClsOfExp.length() > 0)
        {
            lObjBudHead.setClsExp(lStrClsOfExp);
        }

        lObjBudHead.setSchemeNo(OnlineBillUtil.requestSetter("txtSchemeNo", request));
        lObjBudHead.setHeadChrg(OnlineBillUtil.requestSetter("txtHdChrgble", request));
        lObjBudHead.setFinYearId(lMapInput.get("FinYrId") + "");

        return lObjBudHead;
    }


    private TrnBillRegister generateBillRegVO(Map lMapInput)
    {
        TrnBillRegister lObjBillRegister = new TrnBillRegister();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        HttpSession session = request.getSession();
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        DDOInfoServiceImpl lObjDdoInfoSrvc = new DDOInfoServiceImpl();
        List lArrDDOInfo = null;
        OrgDdoMst lObjDDO = null;
        String lStrDDOCode = null;

        String lStrBillNo = OnlineBillUtil.requestSetter("hidBillNo", request);
        String lStrSubjectId = OnlineBillUtil.requestSetter("hidBillTypeId", request);
        String lStrBillCntrlNo = OnlineBillUtil.requestSetter("hidBillCntrlNo", request);
        String lStrIsNewFromRejected = OnlineBillUtil.requestSetter("isNewFromRejected", request);
        String lStrPrevBillNo = OnlineBillUtil.requestSetter("prevBillNo", request);
        PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class, srvcLoc.getSessionFactory());

        SgvaBudbpnMapping lObjBPN = (SgvaBudbpnMapping) lMapInput.get("BPNVO");

        Double lDblExpAmt = 0.0;
        Double lDblRecAmt = 0.0;
        Double lDblDedA = 0.0;
        Double lDblDedB = 0.0;
        Double lDblTotalDed = 0.0;
        Double lDblChallanValue = 0.0;
        
        List<RltLocationDepartment> lLstRltLocDept = null;
        LocationDAO lObjLocDAO = new LocationDAOImpl(CmnLocationMst.class,srvcLoc.getSessionFactory());
        
        try
        {
            if (lStrBillNo.trim().length() > 0)
            {
                lObjBillRegister = lObjPhyBillDAOImpl.read(Long.parseLong(lStrBillNo));

                lObjBillRegister.setUpdatedDate(gDtCurrDt);
                lObjBillRegister.setUpdatedPostId(gLngPostId);
                lObjBillRegister.setUpdatedUserId(gLngUserId);
                
            }
            else
            {
                lObjBillRegister.setCreatedDate(gDtCurrDt);
                lObjBillRegister.setCreatedUserId(gLngUserId);
                lObjBillRegister.setCreatedPostId(gLngPostId);
                lObjBillRegister.setTrnCounter(1);

                lArrDDOInfo = lObjDdoInfoSrvc.getDDOInfoByPost(OnlineBillUtil.getParentPost(gLngPostId, srvcLoc.getSessionFactory()), gLngLangId, gLngDBId, srvcLoc);

                if (lArrDDOInfo != null && lArrDDOInfo.size() > 0)
                {
                    lObjDDO = (OrgDdoMst) lArrDDOInfo.get(0);
                    lStrDDOCode = lObjDDO.getDdoCode();
                }
                else
                {
                    lStrDDOCode = "";
                }

                lObjBillRegister.setDdoCode(lStrDDOCode);
            }
            
            
            if(lStrIsNewFromRejected.length() > 0 && lStrIsNewFromRejected.equalsIgnoreCase("Y"))
            {
                /*In case of new bill from rejected bill, always a new entry is going to be created in
                trn_bill_register, hence we will always get a ddocode in this case so no need for null check
                */
                lMapInput.put("DDOCode", lObjBillRegister.getDdoCode());
                lStrBillCntrlNo = BptmCommonServiceImpl.getBillCntrlNumSeq(lMapInput);
            }
            
            lObjBillRegister.setBillCntrlNo(lStrBillCntrlNo);
            
            if(lStrPrevBillNo.length() > 0)
            {
                lObjBillRegister.setPrevBillNo(Long.parseLong(lStrPrevBillNo));
            }
            
            lObjBillRegister.setBillDate(gDtCurrDt);

            if (lStrSubjectId.length() > 0)
            {
                lObjBillRegister.setSubjectId(Long.parseLong(lStrSubjectId));
            }

            if (OnlineBillUtil.requestSetter("cmbBillType", request).length() > 0)
            {
                lObjBillRegister.setTcBill(OnlineBillUtil.requestSetter("cmbBillType", request));
            }

            lObjBillRegister.setPhyBill(Short.parseShort(bundleConst.getString("CMN.OnlineBillType")));
            lObjBillRegister.setDemandCode(OnlineBillUtil.requestSetter("txtDmd", request));
            lObjBillRegister.setBudmjrHd(OnlineBillUtil.requestSetter("txtMjrHd", request));
            lObjBillRegister.setDdoDeptId(gStrLocationCode);
            lObjBillRegister.setInwardDt(gDtCurrDt);

            if (OnlineBillUtil.requestSetter("txtExpenditure", request).length() > 0)
            {
                lDblExpAmt = Double.parseDouble(OnlineBillUtil.requestSetter("txtExpenditure", request));
            }

            if (OnlineBillUtil.requestSetter("txtRecovery", request).length() > 0)
            {
                lDblRecAmt = Double.parseDouble(OnlineBillUtil.requestSetter("txtRecovery", request));
            }

            lObjBillRegister.setBillGrossAmount(new BigDecimal(lDblExpAmt - lDblRecAmt));

            if (OnlineBillUtil.requestSetter("DeductionA", request).length() > 0)
            {
                lDblDedA = Double.parseDouble(OnlineBillUtil.requestSetter("DeductionA", request));
            }

            if (OnlineBillUtil.requestSetter("DeductionB", request).length() > 0)
            {
                lDblDedB = Double.parseDouble(OnlineBillUtil.requestSetter("DeductionB", request));
            }
            
            lDblTotalDed = lDblDedA + lDblDedB;
            
            if(OnlineBillUtil.requestSetter("cmbBillType", request).equalsIgnoreCase("TC")
                    && lDblTotalDed.doubleValue() == 0.0)                
            {
                lDblChallanValue = Double.parseDouble(OnlineBillUtil.requestSetter("chChlnValue", request));
                lObjBillRegister.setBillNetAmount(new BigDecimal(lDblExpAmt - lDblRecAmt - lDblChallanValue));
            }
            else
            {
                lObjBillRegister.setBillNetAmount(new BigDecimal(lDblExpAmt - lDblRecAmt - lDblTotalDed));
            }           

            lObjBillRegister.setVersionId(1L);
            lObjBillRegister.setExempted("Y");

            if (OnlineBillUtil.requestSetter("cmbEmpType", request).length() > 0)
            {
                lObjBillRegister.setGoNgo(OnlineBillUtil.requestSetter("cmbEmpType", request));
            }

            lObjBillRegister.setCurrBillStatusDate(gDtCurrDt);

            String lStrAction = OnlineBillUtil.requestSetter("userAction", request);

            if (lStrAction.length() > 0 && lStrAction.equalsIgnoreCase("approve"))
            {
                lObjBillRegister.setCurrBillStatus(bundleConst.getString("STATUS.BillApproved"));
            }
            else if (lObjBillRegister.getCurrBillStatus() == null)
            {
                lObjBillRegister.setCurrBillStatus(bundleConst.getString("STATUS.BillCreated"));
            }
            else
            {
                // Leave the status as it is
            }

            lObjBillRegister.setFinYearId(lMapInput.get("FinYrId").toString());

            lObjBillRegister.setLocationCode(gStrLocationCode);
            lObjBillRegister.setDbId(gLngDBId);
            
            // Getting the location Id from Dept Code.
            lLstRltLocDept = lObjLocDAO.getLocByDept(lObjBPN.getDeptId(), gLngLangId, 1000L);
            
            if(lLstRltLocDept != null && lLstRltLocDept.size() > 0)
            {
                lObjBillRegister.setDeptCode(lLstRltLocDept.get(0).getLocCode());    
            }

            if (OnlineBillUtil.requestSetter("txtGrantAmt", request).length() > 0)
            {
                lObjBillRegister.setGrantAmount(new BigDecimal(OnlineBillUtil.requestSetter("txtGrantAmt", request)));
            }
            else
            {
                lObjBillRegister.setGrantAmount(new BigDecimal(0));
            }

            if(OnlineBillUtil.requestSetter("radExempted", request).trim().equals("Y"))
            {
            	lObjBillRegister.setExempted("Y");
            	lObjBillRegister.setBillCode(OnlineBillUtil.requestSetter("cmbBillCode", request));
            }
            else
            {
            	lObjBillRegister.setExempted("N");
            	lObjBillRegister.setBillCode("");
            }
            
            // Saving the attachment
            if (session.getAttribute("AttachID") != null && (session.getAttribute("AttachID").toString().length() > 0))
            {
                lObjBillRegister.setAttachmentId((Long) session.getAttribute("AttachID"));
                session.removeAttribute("AttachID");
            }
            /*
            // Saving Scanned Attachment
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
            objRes = serv.executeService("FILE_UPLOAD_VOGEN", lMapInput);
            Map resultMap = (Map) objRes.getResultValue();
			objRes = serv.executeService("FILE_UPLOAD_SRVC", lMapInput);
			Map attachMap = (Map) objRes.getResultValue();	
			
			if(attachMap.get("AttachmentId_scan") != null)
			{	
				Long lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_scan")));
				lObjBillRegister.setScannedDocId(lLngAttachId);
			}
       */
        }
        catch (Exception e)
        {
            gLogger.error("Error in populateBillRegVO and Error is : " + e, e);
        }

        return lObjBillRegister;
    }


    private TrnBillMvmnt generateBillMvmntVO(Map lMapInput)
    {
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        String lStrBillMvmntId = OnlineBillUtil.requestSetter("hidBillMvmntId", request);
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        TrnBillMvmnt lObjBillMvmnt = new TrnBillMvmnt();
        BillMovementDAOImpl lObjMvmntDAO = new BillMovementDAOImpl(TrnBillMvmnt.class, srvcLoc.getSessionFactory());

        if (lStrBillMvmntId.trim().length() > 0)
        {
            lObjBillMvmnt = lObjMvmntDAO.read(Long.parseLong(lStrBillMvmntId));
            lObjBillMvmnt.setUpdatedDate(gDtCurrDt);
            lObjBillMvmnt.setUpdatedPostId(gLngPostId);
            lObjBillMvmnt.setUpdatedUserId(gLngUserId);
        }
        else
        {
            lObjBillMvmnt.setCreatedDate(gDtCurrDt);
            lObjBillMvmnt.setCreatedPostId(gLngPostId);
            lObjBillMvmnt.setCreatedUserId(gLngUserId);
            lObjBillMvmnt.setMovemntId(1L);
        }

        lObjBillMvmnt.setReceivedFlag(new Short("1"));
        lObjBillMvmnt.setReceivingUserId(gLngUserId);
        lObjBillMvmnt.setReceivedDate(gDtCurrDt);
        lObjBillMvmnt.setStatusUpdtDate(gDtCurrDt);
        lObjBillMvmnt.setStatusUpdtPostid(gLngPostId); // next
        lObjBillMvmnt.setStatusUpdtUserid(gLngUserId);

        String lStrAction = OnlineBillUtil.requestSetter("action", request);

        if (lStrAction.length() > 0 && lStrAction.equalsIgnoreCase("approve"))
        {
            lObjBillMvmnt.setMvmntStatus(bundleConst.getString("STATUS.BillApproved"));
        }
        else
        {
            lObjBillMvmnt.setMvmntStatus(bundleConst.getString("STATUS.BillCreated"));
        }
        lObjBillMvmnt.setDbId(gLngDBId);
        lObjBillMvmnt.setLocationCode(gStrLocationCode);

        return lObjBillMvmnt;
    }


    private TrnBillRemarks generateBillRmrksVO(Map lMapInput)
    {
        TrnBillRemarks lObjBillRmrks = new TrnBillRemarks();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        String lStrBillRemarksId = OnlineBillUtil.requestSetter("hidBillRemarksId", request);
        RemarksDAOImpl lObjRemarksDAOImpl = new RemarksDAOImpl(TrnBillRemarks.class, srvcLoc.getSessionFactory());

        if (lStrBillRemarksId.trim().length() > 0)
        {
            lObjBillRmrks = lObjRemarksDAOImpl.read(Long.parseLong(lStrBillRemarksId));
            lObjBillRmrks.setUpdatedPostId(gLngPostId);
            lObjBillRmrks.setUpdatedUserId(gLngUserId);
            lObjBillRmrks.setUpdatedDate(gDtCurrDt);
        }
        else
        {
            lObjBillRmrks.setCreatedUserId(gLngUserId);
            lObjBillRmrks.setCreatedPostId(gLngPostId);
            lObjBillRmrks.setCreatedDt(gDtCurrDt);
            lObjBillRmrks.setRmrksFlag("MR");
        }

        lObjBillRmrks.setUserId(gLngUserId);
        lObjBillRmrks.setPostId(gLngPostId);
        lObjBillRmrks.setRemarks(OnlineBillUtil.requestSetter("txtareaRemarks", request));
        lObjBillRmrks.setLineItemNo(1L);
        lObjBillRmrks.setLocationCode(gStrLocationCode);
        lObjBillRmrks.setDbId(gLngDBId);

        return lObjBillRmrks;
    }


    private List<RltBillRqst> generateBillRqstVO(Map lMapInput)
    {
        List<RltBillRqst> lLstRqstBill = new ArrayList<RltBillRqst>();
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        RltBillRqst lObjRltBillRqst = null;
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");

        CmnLookupMstDAOImpl lObjLookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, srvcLoc.getSessionFactory());

        String lStrRqst = null;

        try
        {
            lStrRqst = OnlineBillUtil.requestSetter("hidSelRqstId", request);
            StringTokenizer lStRqst = new StringTokenizer(lStrRqst, "~");

            CmnLookupMst lObjLookupVO = lObjLookupDAO.getLookUpVOByLookUpNameAndLang(bundleConst
                    .getString("Lookup.ACTIVE"), gLngLangId);

            while (lStRqst.hasMoreElements())
            {
                lObjRltBillRqst = new RltBillRqst();

                lObjRltBillRqst.setRqstId(Long.parseLong(lStRqst.nextElement().toString()));
                lObjRltBillRqst.setTrnCounter(1L);
                lObjRltBillRqst.setCreatedDate(gDtCurrDt);
                lObjRltBillRqst.setCreatedPostId(gLngPostId);
                lObjRltBillRqst.setCreatedUserId(gLngUserId);
                lObjRltBillRqst.setLocationCode(gStrLocationCode);
                lObjRltBillRqst.setDbId(gLngDBId);
                lObjRltBillRqst.setStatus(lObjLookupVO.getLookupId());

                lLstRqstBill.add(lObjRltBillRqst);
            }

        }
        catch (Exception e)
        {
            gLogger.error("Error in generateBillRqstVO and Error is : " + e, e);
        }
        return lLstRqstBill;
    }


    public List<RltBillParty> generateBillPartyVO(Map lMapInput)
    {
        List<RltBillParty> lLstBillParty = new ArrayList<RltBillParty>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");

        RltBillParty lObjBillParty = null;

        try
        {
        	if(OnlineBillUtil.requestSetter("hidPartiCounter", request).trim().length() != 0)
        	{
	        	int  lIntPartys = Integer.parseInt(OnlineBillUtil.requestSetter("hidPartiCounter", request).trim());
	            
	        	for (int lIntCnt = 0; lIntCnt <= lIntPartys; lIntCnt++)
	            {
	                lObjBillParty = new RltBillParty();
	
	                lObjBillParty.setPartyName(OnlineBillUtil.requestSetter("txtPartyName" + lIntCnt, request).trim());
	                lObjBillParty.setPartyAddr(OnlineBillUtil.requestSetter("txtAddress" + lIntCnt, request).trim());
	                lObjBillParty.setAccntNo(OnlineBillUtil.requestSetter("txtAccountNo" + lIntCnt, request).trim());
	                
	                if (OnlineBillUtil.requestSetter("txtChkAmt" + lIntCnt, request).trim().length() > 0)
	                {
	                    lObjBillParty.setPartyAmt(new BigDecimal(OnlineBillUtil.requestSetter("txtChkAmt" + lIntCnt, request).trim()));
	                }
	                else
	                {
	                    lObjBillParty.setPartyAmt(new BigDecimal(0));
	                }
	
	                lObjBillParty.setCreatedDate(gDtCurrDt);
	                lObjBillParty.setCreatedPostId(gLngPostId);
	                lObjBillParty.setCreatedUserId(gLngUserId);
	
	                lObjBillParty.setLocationCode(gStrLocationCode);
	                lObjBillParty.setDbId(gLngDBId);
	
	                lLstBillParty.add(lObjBillParty);
	            }
        	}
        }
        catch (Exception e)
        {
            gLogger.error("Error in generateBillPartyVO and Error is : " + e, e);
        }

        return lLstBillParty;
    }


    public RptExpenditureDtls getDSSRptHdrVO(Map lMapInput)
    {
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        
        SgvaBudbpnMapping lObjBPN = (SgvaBudbpnMapping) lMapInput.get("BPNVO");

        DDOInfoServiceImpl lObjDdoInfoSrvc = new DDOInfoServiceImpl();
        RptExpenditureDtls lObjRptHdrVO = new RptExpenditureDtls();
        DssDataService lObjDSSDataService = new DssDataServiceImpl();
        LocationDAO lObjLocDAO = new LocationDAOImpl(CmnLocationMst.class,srvcLoc.getSessionFactory());
        
        OrgDdoMst lObjDDO = null;
        String lStrDDOCode = null;
        String lStrAction = null;
        List lArrDDOInfo = null;
        String lStrBillNo = null;
        List<RltLocationDepartment> lLstRltLocDept = null;
        lStrAction = OnlineBillUtil.requestSetter("action", request);

        Map lMapDSS = new HashMap();

        try
        {
            lArrDDOInfo = lObjDdoInfoSrvc.getDDOInfoByPost(gLngPostId, gLngLangId, gLngDBId, srvcLoc);

            if (lArrDDOInfo != null && lArrDDOInfo.size() > 0)
            {
                lObjDDO = (OrgDdoMst) lArrDDOInfo.get(0);
                lStrDDOCode = lObjDDO.getDdoCode();
            }
            else
            {
                lStrDDOCode = "";
            }
            
            lLstRltLocDept = lObjLocDAO.getLocByDept(lObjBPN.getDeptId(), gLngLangId, 1000L);

            lStrBillNo = OnlineBillUtil.requestSetter("hidBillNo", request);

            if (lStrBillNo.trim().length() > 0) // Update the DSS
            {
                lMapDSS.put("map", lMapInput);
                lMapDSS.put("expNo", lStrBillNo);
                lMapDSS.put("expTypeCode", "Bill");

                lObjRptHdrVO = lObjDSSDataService.getExpData(lMapDSS);

            }
            else // Insert the data
            {
                // Setting up DSS VO
            	lObjRptHdrVO.setDeptCode(lLstRltLocDept.get(0).getLocCode());
                lObjRptHdrVO.setOfficeCode(gStrLocationCode); 
                lObjRptHdrVO.setDdoCode(lStrDDOCode);
                lObjRptHdrVO.setExpTypeCode("Bill"); // Read it from constants

                if (lStrBillNo.trim().length() > 0)
                {
                    lObjRptHdrVO.setExpNo(new BigDecimal(lStrBillNo));
                }

                lObjRptHdrVO.setBillGrpCode("");
                lObjRptHdrVO.setClsExpCode(OnlineBillUtil.requestSetter("cmbClsOfExp", request));
                lObjRptHdrVO.setFundTypeCode(OnlineBillUtil.requestSetter("cmbFund", request));
                lObjRptHdrVO.setBudTypeCode(OnlineBillUtil.requestSetter("cmbBudType", request));
                lObjRptHdrVO.setDemandNo(OnlineBillUtil.requestSetter("txtDmd", request));
                lObjRptHdrVO.setScheme(Integer.parseInt(OnlineBillUtil.requestSetter("txtSchemeNo", request)));
                lObjRptHdrVO.setMjrHd(OnlineBillUtil.requestSetter("txtMjrHd", request));
                lObjRptHdrVO.setSubMjrHd(OnlineBillUtil.requestSetter("txtSbMjrHd", request));
                lObjRptHdrVO.setMinHd(OnlineBillUtil.requestSetter("txtMnrHd", request));
                lObjRptHdrVO.setSubHd(OnlineBillUtil.requestSetter("txtSbHd", request));
                lObjRptHdrVO.setDtlHd(OnlineBillUtil.requestSetter("txtDtldHd", request));
                lObjRptHdrVO.setFinYrId(new BigDecimal(lMapInput.get("FinYrId").toString()));
                lObjRptHdrVO.setExpStatusCode(bundleConst.getString("STATUS.BillCreated"));
                lObjRptHdrVO.setExpStatusDt(gDtCurrDt);
                lObjRptHdrVO.setExpCrtDt(gDtCurrDt);
            }
            if (lStrAction.equals("approve"))
           	{
            	lObjRptHdrVO.setExpStatusCode(bundleConst.getString("STATUS.BillApproved"));
            	lObjRptHdrVO.setExpDt(gDtCurrDt);
            	lObjRptHdrVO.setExpStatusDt(gDtCurrDt);
       		}
            
        }
        catch (Exception e)
        {
            gLogger.error("Error in getDSSRptHdrVO. Error is : " + e, e);
        }
        return lObjRptHdrVO;
    }
    
    
    private TrnOnlinebillEmp generateOnlineBillEmp(Map lMapInput)
    {
    	TrnOnlinebillEmp lObjOnlineBillEmpVO = new TrnOnlinebillEmp();
    	
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        
        TrnOnlinebillEmpDAO lObjOnlineBillEmpDAO = new TrnOnlinebillEmpDAOImpl(TrnOnlinebillEmp.class, srvcLoc.getSessionFactory());
        String lStrBillNo = OnlineBillUtil.requestSetter("hidBillNo", request).trim();
        String lStrTrnOnlineBillEmpPK = OnlineBillUtil.requestSetter("hidTrnOnlineBillEmpPk", request).trim();
       
        try
        {
	        if (lStrTrnOnlineBillEmpPK.length() > 0)
	        {
	        	lObjOnlineBillEmpVO = lObjOnlineBillEmpDAO.read(Long.parseLong(lStrTrnOnlineBillEmpPK));
	        	lObjOnlineBillEmpVO.setBillNo(Long.decode(lStrBillNo));
	        	lObjOnlineBillEmpVO.setUpdatedPostId(gLngPostId);
	        	lObjOnlineBillEmpVO.setUpdatedUserId(gLngUserId);
	        	lObjOnlineBillEmpVO.setUpdatedDate(gDtCurrDt);
	        }
	        else
	        {
	        	lObjOnlineBillEmpVO.setTrnCounter(1);
	        	lObjOnlineBillEmpVO.setCreatedUserId(gLngUserId);
	        	lObjOnlineBillEmpVO.setCreatedPostId(gLngPostId);
	        	lObjOnlineBillEmpVO.setCreatedDate(gDtCurrDt);
	        }
	
	        lObjOnlineBillEmpVO.setEmpName(OnlineBillUtil.requestSetter("txtEmpName", request));
	        lObjOnlineBillEmpVO.setEmpDesgn(OnlineBillUtil.requestSetter("txtEmpDsg", request));
	        lObjOnlineBillEmpVO.setMonthCode(OnlineBillUtil.requestSetter("cmbForMonth", request));
	        lObjOnlineBillEmpVO.setDeptName(OnlineBillUtil.requestSetter("txtEmpEstblsmnt", request));
	        lObjOnlineBillEmpVO.setLocationCode(gStrLocationCode);
	        lObjOnlineBillEmpVO.setDbId(gLngDBId);
        }
        catch(Exception e)
        {
        	 gLogger.error("Error in generateOnlineBillEmp. Error is : " + e, e);
        }
        
        return lObjOnlineBillEmpVO;
    }
    
    // Following Code added by Jay for challan - START
    
    public ResultObject generateChallanMap(Map lMapInput)
    {
    	gLogger.info("Inside generateChallanMap() of OnlineBillVOGenerator : start");
    	
    	ServiceLocator serv = (ServiceLocator)lMapInput.get("serviceLocator");
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    	    	
    	TrnReceiptDetails lObjTrnReceiptDetails = null;
    	List<TrnRcptBudheadDtls> lLstTrnRcptBudheadDtls = new ArrayList<TrnRcptBudheadDtls>(); 
    	List<TrnChallanParty> lLstTrnChallanParty = new ArrayList<TrnChallanParty>();
    	

    	try{
    		
        	HttpServletRequest req = (HttpServletRequest)lMapInput.get("requestObj");  
        	setSessionInfo(lMapInput);
        	
        	lObjTrnReceiptDetails = generateRcptDetailsVO(lMapInput);
        	lLstTrnRcptBudheadDtls = generateRcptBudHdVOList(lMapInput);
        	lLstTrnChallanParty = generateChallanPartyVOList(lMapInput);
        	
        	lMapInput.put("trnReceiptDetailsVO", lObjTrnReceiptDetails);
        	lMapInput.put("trnRcptBudheadDtlsVOLst", lLstTrnRcptBudheadDtls);
        	lMapInput.put("trnChallanPartyVOLst", lLstTrnChallanParty);
        	
    	}
    	catch(Exception e)
    	{
    		//System.out.println("\n Exceptiop inside generateChallanMap() of OnlineBillVOGenerator \n");
    		gLogger.error("\n Exceptiop inside generateChallanMap() of OnlineBillVOGenerator \n", e);
    	}

    	objRes.setResultValue(lMapInput);
    	
    	gLogger.info("Inside generateReceiptVO() of generateChallanMap : end");
    	
    	return objRes;
    }  
    
    
    private TrnReceiptDetails generateRcptDetailsVO(Map lMapInput)
    {
    
    	gLogger.info("Inside generateRcptDetailsVO() of OnlineBillVOGenerator : start");
    	ServiceLocator serv = (ServiceLocator)lMapInput.get("serviceLocator");
    	TrnReceiptDetails lObjTrnReceiptDetails = null;
    	ReceiptDAOImpl rDAO = null;
    	
    	Long receiptDetailId = null;
    	String lStrReceiptId = null;
    	String majorHead = null;
    	String challanDate = null;
    	String cardexNo = null;
    	String schemeNo = null;
    	String tc = null;
    	String challanValue = null;
    	    	    	
    	try
    	{
    		HttpServletRequest req = (HttpServletRequest)lMapInput.get("requestObj");
        	lObjTrnReceiptDetails = new TrnReceiptDetails();
        	rDAO = new ReceiptDAOImpl(TrnReceiptDetails.class, serv.getSessionFactory());   
        	
    		
    		lStrReceiptId = OnlineBillUtil.requestSetter("hidReceiptId", req);
    		majorHead = OnlineBillUtil.requestSetter("chMajorHead", req);
    		challanDate = OnlineBillUtil.requestSetter("chChlnDate", req);
    		cardexNo = OnlineBillUtil.requestSetter("chCardexNo", req);
    		schemeNo = OnlineBillUtil.requestSetter("chScheme", req);
    		tc = OnlineBillUtil.requestSetter("cmbChTC", req);
    		challanValue = OnlineBillUtil.requestSetter("chChlnValue", req);
    		
    		if(lStrReceiptId.length() > 0)
    		{
				receiptDetailId = Long.parseLong(lStrReceiptId);    			
    			lObjTrnReceiptDetails = rDAO.read(receiptDetailId);
    			lObjTrnReceiptDetails.setUpdatedUserId(gLngUserId);
    			lObjTrnReceiptDetails.setUpdatedPostId(gLngPostId);
    			lObjTrnReceiptDetails.setUpdatedDate(gDtCurrDt);
    			lObjTrnReceiptDetails.setReceiptDetailId(receiptDetailId);
    		}
    		else
    		{
    			lObjTrnReceiptDetails.setCreatedUserId(gLngUserId);
    			lObjTrnReceiptDetails.setCreatedPostId(gLngPostId);
    			lObjTrnReceiptDetails.setCreatedDate(gDtCurrDt);
    			lObjTrnReceiptDetails.setTrnCounter(1);    			
    		}    		
    		
    		lObjTrnReceiptDetails.setMajorHead(majorHead);

    		if(challanValue.length() > 0)
    			lObjTrnReceiptDetails.setAmount(new BigDecimal(challanValue));
    		
    		if(challanDate.length() > 0)
    			lObjTrnReceiptDetails.setReceiptDate(new SimpleDateFormat("dd/MM/yyyy").parse(challanDate));
    		
    		lObjTrnReceiptDetails.setCardexNo(cardexNo);
    		lObjTrnReceiptDetails.setSchemeNo(schemeNo);
    		
    		if(tc.length() > 0)
    			lObjTrnReceiptDetails.setTc(Integer.parseInt(tc));
    		
    		lObjTrnReceiptDetails.setLocationCode(gStrLocationCode);
    		lObjTrnReceiptDetails.setDbId(gLngDBId);
    		                
    	}
    	catch(Exception e)
    	{
    		gLogger.error("Error inside generateRcptDetailsVO() of OnlineBillVOGenerator ", e);
    	}
    	    	    	
    	gLogger.info("Inside generateRcptDetailsVO() of OnlineBillVOGenerator : end");
    	
    	return lObjTrnReceiptDetails;
    }
    
    
    private List<TrnRcptBudheadDtls> generateRcptBudHdVOList(Map lMapInput)
    {
    	gLogger.info("Inside generateRcptBudHdVOList() of OnlineBillVOGenerator : start");
    	ServiceLocator serv = (ServiceLocator)lMapInput.get("serviceLocator");
    	
    	List<TrnRcptBudheadDtls> lLstTrnRcptBudheadDtls = null;
    	RcptBudDtlsDAOImpl rDAO = null;
    	
    	Long lLongRBudDtlsId = null;
    	String[] lStrRcptBudHdId = null; 
    	String[] subMajorHead = null;
    	String[] minorHead = null;
    	String[] subHead = null;
    	String[] amount = null;
    	
    	try
    	{
    		HttpServletRequest req = (HttpServletRequest)lMapInput.get("requestObj");
        	lLstTrnRcptBudheadDtls = new ArrayList<TrnRcptBudheadDtls>();
        	rDAO = new RcptBudDtlsDAOImpl(TrnRcptBudheadDtls.class, serv.getSessionFactory());
        	
        	lStrRcptBudHdId = OnlineBillUtil.requestArraySetter("hidRcptBudHdId", req);
        	subMajorHead = OnlineBillUtil.requestArraySetter("chSubMajorHead", req);
        	minorHead = OnlineBillUtil.requestArraySetter("chMinorHead", req);
        	subHead = OnlineBillUtil.requestArraySetter("chSubHead", req);
        	amount = OnlineBillUtil.requestArraySetter("chAmount", req);
        	
        	//System.out.println("\n No of RcptBudHd Entries : " + subMajorHead.length + "\n");
        	
        	for(int i=0; i<subMajorHead.length; i++)
        	{        		        	        		
            	TrnRcptBudheadDtls lObjTrnRcptBudheadDtls = new TrnRcptBudheadDtls();
            	
        		if(lStrRcptBudHdId[i].length() > 0)
        		{
        			//lLongRBudDtlsId = Long.parseLong(lStrRcptBudHdId[i]);
        			//lObjTrnRcptBudheadDtls =  rDAO.read(lLongRBudDtlsId);
        			lObjTrnRcptBudheadDtls.setUpdatedUserId(gLngUserId);
        			lObjTrnRcptBudheadDtls.setUpdatedPostId(gLngPostId);
        			lObjTrnRcptBudheadDtls.setUpdatedDate(gDtCurrDt);
        		}
        		else
        		{
        			//lObjTrnRcptBudheadDtls.setCreatedUserId(gLngUserId);
        			//lObjTrnRcptBudheadDtls.setCreatedPostId(gLngPostId);
        			//lObjTrnRcptBudheadDtls.setCreatedDate(gDtCurrDt);
        		}
        		
        		lObjTrnRcptBudheadDtls.setCreatedUserId(gLngUserId);
    			lObjTrnRcptBudheadDtls.setCreatedPostId(gLngPostId);
    			lObjTrnRcptBudheadDtls.setCreatedDate(gDtCurrDt);
        		
        		lObjTrnRcptBudheadDtls.setBudSubmjrHead(subMajorHead[i]);
        		lObjTrnRcptBudheadDtls.setBudMinHead(minorHead[i]);
        		lObjTrnRcptBudheadDtls.setBudSubHead(subHead[i]);
        		
        		if(amount[i].length() > 0)
        			lObjTrnRcptBudheadDtls.setAmount(new BigDecimal(amount[i]));
        		
        		lObjTrnRcptBudheadDtls.setDbId(gLngDBId);
        		
        		lLstTrnRcptBudheadDtls.add(lObjTrnRcptBudheadDtls);
        	}
        	        	
    	}
    	catch(Exception e)
    	{
    		gLogger.error("Error in generateRcptBudHdVOList() of OnlineBillVOGenerator ", e);
    	}
    	
    	gLogger.info("Inside generateRcptBudHdVOList() of OnlineBillVOGenerator : end");
    	
    	return lLstTrnRcptBudheadDtls;
    }
    
    private HashSet<TrnRcptBudheadDtls> generateRcptBudHdVOSet(Map lMapInput)
    {
    	gLogger.info("Inside generateRcptBudHdVOSet() of OnlineBillVOGenerator : start");
    	ServiceLocator serv = (ServiceLocator)lMapInput.get("serviceLocator");
    	
    	HashSet<TrnRcptBudheadDtls> lLstTrnRcptBudheadDtls = null;
    	RcptBudDtlsDAOImpl rDAO = null;
    	
    	Long lLongRBudDtlsId = null;
    	String[] lStrRcptBudHdId = null; 
    	String[] subMajorHead = null;
    	String[] minorHead = null;
    	String[] subHead = null;
    	String[] amount = null;
    	
    	try
    	{
    		HttpServletRequest req = (HttpServletRequest)lMapInput.get("requestObj");
        	lLstTrnRcptBudheadDtls = new HashSet<TrnRcptBudheadDtls>();
        	rDAO = new RcptBudDtlsDAOImpl(TrnRcptBudheadDtls.class, serv.getSessionFactory());
        	
        	lStrRcptBudHdId = OnlineBillUtil.requestArraySetter("hidRcptBudHdId", req);
        	subMajorHead = OnlineBillUtil.requestArraySetter("chSubMajorHead", req);
        	minorHead = OnlineBillUtil.requestArraySetter("chMinorHead", req);
        	subHead = OnlineBillUtil.requestArraySetter("chSubHead", req);
        	amount = OnlineBillUtil.requestArraySetter("chAmount", req);
        	
        	//System.out.println("\n No of RcptBudHd Entries : " + subMajorHead.length + "\n");
        	
        	for(int i=0; i<subMajorHead.length; i++)
        	{        		        	        		
            	TrnRcptBudheadDtls lObjTrnRcptBudheadDtls = new TrnRcptBudheadDtls();
            	
        		if(lStrRcptBudHdId[i].length() > 0)
        		{
        			//lLongRBudDtlsId = Long.parseLong(lStrRcptBudHdId[i]);
        			//lObjTrnRcptBudheadDtls =  rDAO.read(lLongRBudDtlsId);
        			lObjTrnRcptBudheadDtls.setUpdatedUserId(gLngUserId);
        			lObjTrnRcptBudheadDtls.setUpdatedPostId(gLngPostId);
        			lObjTrnRcptBudheadDtls.setUpdatedDate(gDtCurrDt);
        		}
        		else
        		{
        			//lObjTrnRcptBudheadDtls.setCreatedUserId(gLngUserId);
        			//lObjTrnRcptBudheadDtls.setCreatedPostId(gLngPostId);
        			//lObjTrnRcptBudheadDtls.setCreatedDate(gDtCurrDt);
        		}
        		
        		lObjTrnRcptBudheadDtls.setCreatedUserId(gLngUserId);
    			lObjTrnRcptBudheadDtls.setCreatedPostId(gLngPostId);
    			lObjTrnRcptBudheadDtls.setCreatedDate(gDtCurrDt);
        		
        		lObjTrnRcptBudheadDtls.setBudSubmjrHead(subMajorHead[i]);
        		lObjTrnRcptBudheadDtls.setBudMinHead(minorHead[i]);
        		lObjTrnRcptBudheadDtls.setBudSubHead(subHead[i]);
        		
        		if(amount[i].length() > 0)
        			lObjTrnRcptBudheadDtls.setAmount(new BigDecimal(amount[i]));
        		
        		lObjTrnRcptBudheadDtls.setDbId(gLngDBId);
        		
        		lLstTrnRcptBudheadDtls.add(lObjTrnRcptBudheadDtls);
        	}
        	        	
    	}
    	catch(Exception e)
    	{
    		gLogger.error("Error in generateRcptBudHdVOSet() of OnlineBillVOGenerator ", e);
    	}
    	
    	gLogger.info("Inside generateRcptBudHdVOSet() of OnlineBillVOGenerator : end");
    	
    	return lLstTrnRcptBudheadDtls;
    }
    
    
    private List<TrnChallanParty> generateChallanPartyVOList(Map lMapInput)
    {
    	gLogger.info("Inside generateChallanPartyVOList() of OnlineBillVOGenerator : start");
    	
    	ServiceLocator serv = (ServiceLocator)lMapInput.get("serviceLocator");
    	List<TrnChallanParty> lLstTrnChallanParty = null;
    	TrnChallanPartyDAO cpDAO = null; 
    	
    	Long lLongChallanPartyId = null;
    	
    	String[] lStrChallanPartyId = null;
    	String[] lStrPartyName = null;
    	String[] lStrPartyAmount = null;
    	
    	try
    	{
    		HttpServletRequest req = (HttpServletRequest)lMapInput.get("requestObj");
    		lLstTrnChallanParty = new ArrayList<TrnChallanParty>();//here
    		cpDAO = new TrnChallanPartyDAOImpl(TrnChallanParty.class, serv.getSessionFactory());
    		
    		lStrChallanPartyId = OnlineBillUtil.requestArraySetter("hidChallanPartyId", req);
    		lStrPartyName = OnlineBillUtil.requestArraySetter("chPartyName", req);
    		lStrPartyAmount = OnlineBillUtil.requestArraySetter("chPartyAmount", req);
    		
    		//System.out.println("\n No of ChallanParty Entries : " + lStrPartyName.length + "\n");
    		
    		for(int i=0; i<lStrPartyName.length; i++)
    		{
    			TrnChallanParty lObjTrnChallanParty = new TrnChallanParty();
    			
    			if(lStrChallanPartyId[i].length() > 0)
    			{
    				//lLongChallanPartyId = Long.parseLong(lStrChallanPartyId[i]);
    				//lObjTrnChallanParty = cpDAO.read(lLongChallanPartyId);
    				lObjTrnChallanParty.setUpdatedUserId(gLngUserId);
    				lObjTrnChallanParty.setUpdatedPostId(gLngPostId);
    				lObjTrnChallanParty.setUpdatedDate(gDtCurrDt);
    			}
    			else
    			{
    				//lObjTrnChallanParty.setCreatedUserId(gLngUserId);
    				//lObjTrnChallanParty.setCreatedPostId(gLngPostId);
    				//lObjTrnChallanParty.setCreatedDate(gDtCurrDt);    				
    			}
    			
    			lObjTrnChallanParty.setCreatedUserId(gLngUserId);
				lObjTrnChallanParty.setCreatedPostId(gLngPostId);
				lObjTrnChallanParty.setCreatedDate(gDtCurrDt);  
    			
    			lObjTrnChallanParty.setTrnCounter(1);
    		
    			lObjTrnChallanParty.setPartyName(lStrPartyName[i]);
    			
    			if(lStrPartyAmount[i].length() > 0)
    				lObjTrnChallanParty.setPartyAmount(new BigDecimal(lStrPartyAmount[i]));
    			
    			lObjTrnChallanParty.setDbId(gLngDBId);
    			lObjTrnChallanParty.setLocationCode(gStrLocationCode);
    			
    			lLstTrnChallanParty.add(lObjTrnChallanParty);
    		}
    		
    	}
    	catch(Exception e)
    	{
    		gLogger.error("Error inside generateChallanPartyVOList() of OnlineBillVOGenerator", e);
    	}
    	 
    	
    	gLogger.info("Inside generateChallanPartyVOList() of OnlineBillVOGenerator : end");
    	
    	return lLstTrnChallanParty;
    }
    
    
    private HashSet<TrnChallanParty> generateChallanPartyVOSet(Map lMapInput)
    {
    	gLogger.info("Inside generateChallanPartyVOSet() of OnlineBillVOGenerator : start");
    	
    	ServiceLocator serv = (ServiceLocator)lMapInput.get("serviceLocator");
    	HashSet<TrnChallanParty> lLstTrnChallanParty = null;
    	TrnChallanPartyDAO cpDAO = null; 
    	
    	Long lLongChallanPartyId = null;
    	
    	String[] lStrChallanPartyId = null;
    	String[] lStrPartyName = null;
    	String[] lStrPartyAmount = null;
    	
    	try
    	{
    		HttpServletRequest req = (HttpServletRequest)lMapInput.get("requestObj");
    		lLstTrnChallanParty = new HashSet<TrnChallanParty>();//here
    		cpDAO = new TrnChallanPartyDAOImpl(TrnChallanParty.class, serv.getSessionFactory());
    		
    		lStrChallanPartyId = OnlineBillUtil.requestArraySetter("hidChallanPartyId", req);
    		lStrPartyName = OnlineBillUtil.requestArraySetter("chPartyName", req);
    		lStrPartyAmount = OnlineBillUtil.requestArraySetter("chPartyAmount", req);
    		
    		//System.out.println("\n No of ChallanParty Entries : " + lStrPartyName.length + "\n");
    		
    		for(int i=0; i<lStrPartyName.length; i++)
    		{
    			TrnChallanParty lObjTrnChallanParty = new TrnChallanParty();
    			
    			if(lStrChallanPartyId[i].length() > 0)
    			{
    				//lLongChallanPartyId = Long.parseLong(lStrChallanPartyId[i]);
    				//lObjTrnChallanParty = cpDAO.read(lLongChallanPartyId);
    				lObjTrnChallanParty.setUpdatedUserId(gLngUserId);
    				lObjTrnChallanParty.setUpdatedPostId(gLngPostId);
    				lObjTrnChallanParty.setUpdatedDate(gDtCurrDt);
    			}
    			else
    			{
    				//lObjTrnChallanParty.setCreatedUserId(gLngUserId);
    				//lObjTrnChallanParty.setCreatedPostId(gLngPostId);
    				//lObjTrnChallanParty.setCreatedDate(gDtCurrDt);    				
    			}
    			
    			lObjTrnChallanParty.setCreatedUserId(gLngUserId);
				lObjTrnChallanParty.setCreatedPostId(gLngPostId);
				lObjTrnChallanParty.setCreatedDate(gDtCurrDt);  
    			
    			lObjTrnChallanParty.setTrnCounter(1);
    		
    			lObjTrnChallanParty.setPartyName(lStrPartyName[i]);
    			
    			if(lStrPartyAmount[i].length() > 0)
    				lObjTrnChallanParty.setPartyAmount(new BigDecimal(lStrPartyAmount[i]));
    			
    			lObjTrnChallanParty.setDbId(gLngDBId);
    			lObjTrnChallanParty.setLocationCode(gStrLocationCode);
    			
    			lLstTrnChallanParty.add(lObjTrnChallanParty);
    		}
    		
    	}
    	catch(Exception e)
    	{
    		gLogger.error("Error inside generateChallanPartyVOSet() of OnlineBillVOGenerator", e);
    	}
    	 
    	
    	gLogger.info("Inside generateChallanPartyVOSet() of OnlineBillVOGenerator : end");
    	
    	return lLstTrnChallanParty;
    }
    //Code added by Jay for challan - END    
}