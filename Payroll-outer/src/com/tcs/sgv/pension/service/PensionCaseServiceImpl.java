package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.billproc.counter.service.PhyBillServiceImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.MonthlyPensionBillDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerFamilyDtlsDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerNomineeDtlsDAOImpl;
import com.tcs.sgv.pension.dao.PensionBillDAOImpl;
import com.tcs.sgv.pension.dao.PensionCaseDAO;
import com.tcs.sgv.pension.dao.PensionCaseDAOImpl;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionCaseMvmntDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionCaseOutwrdDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionerRivisionDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionerRivisionDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionerSeenDtlsDao;
import com.tcs.sgv.pension.dao.TrnPensionerSeenDtlsDaoImpl;
import com.tcs.sgv.pension.dao.TrnPnsncaseRopRltDAO;
import com.tcs.sgv.pension.dao.TrnPrvosionalPensionDtlsDao;
import com.tcs.sgv.pension.dao.TrnPrvosionalPensionDtlsDaoImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.MstPensionerNomineeDtls;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.TrnPensionCaseMvmnt;
import com.tcs.sgv.pension.valueobject.TrnPensionCaseOutwrd;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionerSeenDtls;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;
import com.tcs.sgv.pension.valueobject.TrnPrvosionalPensionDtls;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.appwfinterface.WorkFlowInterfaceImpl;
import com.tcs.sgv.wf.exception.AlternateHierarchyNotfoundException;
import com.tcs.sgv.wf.exception.DocIdNotFoundException;
import com.tcs.sgv.wf.exception.UpperPostNotFoundException;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WfJobMstVO;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

public class PensionCaseServiceImpl extends ServiceImpl implements PensionCaseService
{
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

    Log gLogger = LogFactory.getLog(getClass());

    ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pension/PensionConstants");


    public ResultObject loadInwdPensionCase(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try
        {
            setSessionInfo(inputMap);
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            CmnLookupMstDAOImpl cmnLookupMstObj = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
            CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            List lLstRop = new ArrayList();
            List listPnsnType = null;
            List listPnsnStatus = null;
            List listGender = null;
            List listClass = null;
            List listSchemeType = null;
            List listState = null;
            List listBank = null;
            List listDepartment = null;
            List listDesignation = null;
            List listTreasury = null;
            List listPnsnHeadCode = null;
            List listCadre = null;
            List listFmlRelation = null;
            Date lDtcurDate = SessionHelper.getCurDate();
            List listDistricts = null;
            List lLstCmbHandiCp = null;
            List lLstCmbSanAuth = null;
            List lLstYesNo = null;
            List lLstPnsnClass = null;
            List lLstPayScale = null;
            listPnsnType = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("PensionType", gLngLangId);
            listPnsnStatus = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("PensionStatus", gLngLangId);
            listGender = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("Gender", gLngLangId);
            listClass = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("Grade of Emp", gLngLangId);
            listSchemeType = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("SchemeType", gLngLangId);
            listCadre = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("CadreType", gLngLangId);
            listFmlRelation = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("Family_Relation", gLngLangId);
            lLstYesNo = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("YesNo", gLngLangId);
            lLstPnsnClass = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("PensionClass",gLngLangId);
            listDistricts = CommonPensionDAO.getDistrictsOfState(BigDecimal.valueOf(1L), gLngLangId);
            Long lLngLocCode = CommonPensionDAO.getLocCodeFromRltPPOTrsryMapping(Long.parseLong(gStrLocId));
            lLstRop = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("ROPPPO", gLngLangId);
            String lStrlocID = null;
            lStrlocID = gStrLocId;
            if(lLngLocCode != null && lLngLocCode > 0)
            {
            	lStrlocID = lLngLocCode.toString();
            }
          
            List lLstSubTrsry =  CommonPensionDAO.getSubTreasurysOfTreasury(lStrlocID,gLngLangId);
            listPnsnHeadCode = (List) CommonPensionDAO.getAllHeadCode();
            listState = CommonPensionDAO.getAllState(gLngLangId);
            listBank = CommonPensionDAO.getAllBank(gLngLangId,SessionHelper.getLocationCode(inputMap));
            listDepartment = CommonPensionDAO.getAllDepartment(gLngLangId);
            listDesignation = CommonPensionDAO.getAllDesignation(gLngLangId);
            listTreasury = CommonPensionDAO.getAllTreasury(gLngLangId);
            lLstCmbHandiCp = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("HandiCapType", gLngLangId);
            List lLstPrefix = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("PrefixSelect", gLngLangId);
            List lLStCalcType = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("Calculation Type", gLngLangId);
            lLstCmbSanAuth = CommonPensionDAO.getSanctionAuthPrefix(gLngLangId);
            lLstPayScale = CommonPensionDAO.getPayScaleList(inputMap, gLngLangId);
            inputMap.put("listLocId", gStrLocId);
            inputMap.put("listPensionType", listPnsnType);
            inputMap.put("listHeadCode", listPnsnHeadCode);
            inputMap.put("PensionStatus", listPnsnStatus);
            inputMap.put("listGender", listGender);
            inputMap.put("listClass", listClass);
            inputMap.put("listSchemeType", listSchemeType);
            inputMap.put("listDesignation", listDesignation);
            inputMap.put("listDepartment", listDepartment);
            inputMap.put("listState", listState);
            inputMap.put("listBank", listBank);
            inputMap.put("listTreasury", listTreasury);
            inputMap.put("listCadre", listCadre);
            inputMap.put("listFamilyRlshp", listFmlRelation);
            inputMap.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
            inputMap.put("InwardStatus", request.getParameter("status"));
            inputMap.put("listDistricts", listDistricts);
            inputMap.put("insert", "insert");
            inputMap.put("SancAuthList", lLstCmbSanAuth);
            inputMap.put("PHCmbo",lLstCmbHandiCp);
            inputMap.put("lLstYesNo", lLstYesNo);
            inputMap.put("PnsnClass", lLstPnsnClass);
            inputMap.put("listSubTreasury",lLstSubTrsry);
            inputMap.put("listPayScale",lLstPayScale);
            inputMap.put("prefixList", lLstPrefix);
            inputMap.put("CalcType", lLStCalcType);
            inputMap.put("ROPList", lLstRop);
            resObj.setResultValue(inputMap);
            resObj.setViewName("HeaderMenu");
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }


    public Map insertPensionCase(Map ArgsMap)
    {
        WorkFlowVO wfVO = null;
        wfVO = (WorkFlowVO) ArgsMap.get("WorkFlowVO");
        ServiceLocator serv = null;
        HttpServletRequest request = null;
        MstPensionerHdr mstPensionerHdrVO = null;
        TrnPensionerSeenDtls lObjPensionerSeenDtls = null;
        TrnPensionRqstHdr trnPensionRqstHdrVO = null;
        MstPensionerDtls mstPensionerDtlsVO = null;
        TrnPensionerRivisionDtls trnPensionRivisionDtlVO = null;
        ArrayList lArrylstMstPnsnrNmDtlsVO = null;
        ArrayList lArrylstMstPnsnrFMDtlsVO = null;
        Long mstPensionerHdrId = null;
        TrnPrvosionalPensionDtls lObjProvisionDtlsVo = null;
        Map inputMap = null;
        Long trnPensionRqstHdrID = 0L;
        try
        {
        	if( ! (ArgsMap.containsKey("receiveCase") && "Y".equalsIgnoreCase(ArgsMap.get("receiveCase").toString())))
        	{
	        	if (wfVO != null)
	            {
	                inputMap = wfVO.getAppMap();
	            }
	            else
	            {
	                inputMap = ArgsMap;
	            }
	            setSessionInfo(inputMap);
	            serv = (ServiceLocator) inputMap.get("serviceLocator");
	            request = (HttpServletRequest) inputMap.get("requestObj");
	            String pensionerCode = StringUtility.getParameter("hidpensionerCode", request);
	            mstPensionerHdrVO = (MstPensionerHdr) inputMap.get("MstPensionerHdrVO");
	            MstPensionerHdrDAOImpl mstPensionerHdrDAOImpl = new MstPensionerHdrDAOImpl(MstPensionerHdr.class, serv
	                    .getSessionFactory());
	            if (mstPensionerHdrVO.getPensionerId() != null)
	            {
	                mstPensionerHdrDAOImpl.update(mstPensionerHdrVO);
	                mstPensionerHdrId = Long.valueOf(mstPensionerHdrVO.getPensionerCode());
	                inputMap.put("MstPensionerHdrVO", mstPensionerHdrVO);
	            }
	            else
	            {
	                mstPensionerHdrId = IFMSCommonServiceImpl.getNextSeqNum("mst_pensioner_hdr", inputMap);
	                if (mstPensionerHdrId != null)
	                {
	                    mstPensionerHdrVO.setPensionerId(mstPensionerHdrId);
	                }
	                if(mstPensionerHdrVO.getPensionerCode() == null)
	                {
	                	mstPensionerHdrVO.setPensionerCode(mstPensionerHdrId.toString());
	                }
	                mstPensionerHdrDAOImpl.create(mstPensionerHdrVO);
	            }
	            inputMap.put("mstPensionerHdrId", mstPensionerHdrVO.getPensionerCode());
	            lObjPensionerSeenDtls = (TrnPensionerSeenDtls) inputMap.get("TrnPensionerSeenDtls");
	            trnPensionRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
	            mstPensionerDtlsVO = (MstPensionerDtls) inputMap.get("MstPensionerDtlsVO");
	            trnPensionRivisionDtlVO = (TrnPensionerRivisionDtls) inputMap.get("TrnPensionerRivisionDtlsVO");
	            lArrylstMstPnsnrNmDtlsVO = (ArrayList) inputMap.get("MstPensionerNomineeDtlsVOArrlst");
	            lArrylstMstPnsnrFMDtlsVO = (ArrayList) inputMap.get("MstPensionerFamilyDtlsVOArrlst");
	            lObjProvisionDtlsVo = (TrnPrvosionalPensionDtls) inputMap.get("TrnPrvosionalPensionDtls");
	            savePrvosionalPensionDtls(lObjProvisionDtlsVo, inputMap);
	            Long mstPensionerFMDtlsID = null;
	            MstPensionerFamilyDtlsDAOImpl mstPnsnrFMDtlsDAOImpl = new MstPensionerFamilyDtlsDAOImpl(
	                    MstPensionerFamilyDtls.class, serv.getSessionFactory());
	            List listFmDtls = new ArrayList();
	            if (pensionerCode != null && pensionerCode.length() > 0)
	            {
	                listFmDtls = mstPnsnrFMDtlsDAOImpl.getFamilyDtlsPks(pensionerCode, gLngLangId);
	                if (listFmDtls != null && listFmDtls.size() > 0)
	                {
	                    for (int i = 0; i < listFmDtls.size(); i++)
	                    {
	                        MstPensionerFamilyDtls mstPnsnrFMDtlsDelete = new MstPensionerFamilyDtls();
	                        mstPnsnrFMDtlsDelete = mstPnsnrFMDtlsDAOImpl.read((Long) (listFmDtls.get(i)));
	                        if (mstPnsnrFMDtlsDelete != null)
	                            mstPnsnrFMDtlsDAOImpl.delete(mstPnsnrFMDtlsDelete);
	                    }
	                }
	            }
	            if (lArrylstMstPnsnrFMDtlsVO != null && lArrylstMstPnsnrFMDtlsVO.size() > 0)
	            {
	                for (int i = 0; i < lArrylstMstPnsnrFMDtlsVO.size(); i++)
	                {
	                    MstPensionerFamilyDtls mstPensionerFMDtlsVO = (MstPensionerFamilyDtls) lArrylstMstPnsnrFMDtlsVO
	                            .get(i);
	                    if (mstPensionerFMDtlsVO != null)
	                    {
	                        mstPensionerFMDtlsID = IFMSCommonServiceImpl.getNextSeqNum("mst_pensioner_family_dtls",
	                                inputMap);
	                        if (mstPensionerFMDtlsID != null)
	                        {
	                            mstPensionerFMDtlsVO.setFamilyMemberId(mstPensionerFMDtlsID);
	                            if (mstPensionerHdrVO.getPensionerCode() != null)
	                            {
	                                mstPensionerFMDtlsVO.setPensionerCode(mstPensionerHdrVO.getPensionerCode());
	                            }
	                            else
	                            {
	                                mstPensionerFMDtlsVO.setPensionerCode(mstPensionerHdrId.toString());
	                            }
	                            mstPnsnrFMDtlsDAOImpl.create(mstPensionerFMDtlsVO);
	                        }
	                    }
	                }
	            }
	            MstPensionerNomineeDtlsDAOImpl mstPnsnrDtlsDAOImpl = new MstPensionerNomineeDtlsDAOImpl(
	                    MstPensionerNomineeDtls.class, serv.getSessionFactory());
	            List listNmDtls = new ArrayList();
	            if (pensionerCode != null && pensionerCode.length() > 0)
	                listNmDtls = mstPnsnrDtlsDAOImpl.getPenionNomineeDtlsFromPensionCode(pensionerCode, gLngLangId);
	            MstPensionerNomineeDtls mstPnsnrNmDtlsDelete = new MstPensionerNomineeDtls();
	
	            if (listNmDtls != null && listNmDtls.size() > 0)
	            {
	                for (int i = 0; i < listNmDtls.size(); i++)
	                {
	                    mstPnsnrNmDtlsDelete = mstPnsnrDtlsDAOImpl.read((Long)(listNmDtls.get(i)));
	                    mstPnsnrDtlsDAOImpl.delete(mstPnsnrNmDtlsDelete);
	                }
	            }
	            Long mstPensionerNMDtlsID = null;
	            if (lArrylstMstPnsnrNmDtlsVO != null && lArrylstMstPnsnrNmDtlsVO.size() > 0)
	            {
	                for (int i = 0; i < lArrylstMstPnsnrNmDtlsVO.size(); i++)
	                {
	
	                    MstPensionerNomineeDtls mstPensionerNmDtlsVO = (MstPensionerNomineeDtls) lArrylstMstPnsnrNmDtlsVO
	                            .get(i);
	                    if (mstPensionerNmDtlsVO != null)
	                    {
	                        mstPensionerNMDtlsID = IDGenerateDelegate.getNextId("mst_pensioner_nominee_dtls", inputMap);
	                        if (mstPensionerNMDtlsID != null)
	                        {
	                            mstPensionerNmDtlsVO.setNomineeId(mstPensionerNMDtlsID);
	                            if (mstPensionerHdrVO.getPensionerCode() != null)
	                            {
	                                mstPensionerNmDtlsVO.setPensionerCode(mstPensionerHdrVO.getPensionerCode());
	                            }
	                            else
	                            {
	                                mstPensionerNmDtlsVO.setPensionerCode(mstPensionerHdrId.toString());
	                            }
	                            mstPnsnrDtlsDAOImpl.create(mstPensionerNmDtlsVO);
	                        }
	                    }
	                }
	            }
	            if(inputMap.containsKey("TrnPensionCaseOutwrdList"))
	            {
	                ArrayList<TrnPensionCaseOutwrd> lLstCaseOutward = (ArrayList<TrnPensionCaseOutwrd>) inputMap.get("TrnPensionCaseOutwrdList");
	                PensionCaseDAOImpl PensionCaseDAO = new PensionCaseDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
	                List lLstPk = PensionCaseDAO.getPkListForPnsnCaseOutwardDtls(trnPensionRqstHdrVO.getPpoNo());
	                TrnPensionCaseOutwrd lObjCaseOutwrd = null;
	                TrnPensionCaseOutwrdDAO lObjOutwrdDao = new TrnPensionCaseOutwrdDAO(TrnPensionCaseOutwrd.class,serv.getSessionFactory());
	                if(lLstPk != null && lLstPk.size() > 0)
	                {
	                    for(int i=0;i<lLstPk.size();i++)
	                    {
	                        lObjCaseOutwrd = new TrnPensionCaseOutwrd();
	                        lObjCaseOutwrd = lObjOutwrdDao.read((Long) lLstPk.get(i));
	                        lObjOutwrdDao.delete(lObjCaseOutwrd);
	                    }
	                }
	                if(lLstCaseOutward != null && lLstCaseOutward.size() > 0)
	                {
	                    for(int i = 0;i<lLstCaseOutward.size();i++)
	                    {
	                        lObjCaseOutwrd = new TrnPensionCaseOutwrd();
	                        lObjCaseOutwrd = lLstCaseOutward.get(i);
	                        
	                        lObjCaseOutwrd.setPpoNo(trnPensionRqstHdrVO.getPpoNo());
	                        Long lLngOutwrdId = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_case_outwrd", inputMap);
	                        lObjCaseOutwrd.setCaseOutwrdId(Long.valueOf(lLngOutwrdId));
	                        lObjOutwrdDao.create(lObjCaseOutwrd);
	                    }
	                }
	            }
	            saveMstPensionerDtls(mstPensionerDtlsVO, inputMap);
	
	            trnPensionRqstHdrID = savePensionerRqstHdrDtls(trnPensionRqstHdrVO, inputMap);
	            if("Y".equalsIgnoreCase(trnPensionRqstHdrVO.getIsRop()))
	            {
	            	  saveROPDtls(inputMap,trnPensionRqstHdrVO.getPpoNo());
	            }
	          
	            inputMap.put("trnPensionRqstHdrID", trnPensionRqstHdrID);
	            if (!trnPensionRqstHdrVO.getTypeFlag().equals(gObjRsrcBndle.getString("CMN.TYPEFLAGP")) && lObjPensionerSeenDtls != null)
	            {
	                saveTrnPensionerSeenDtls(lObjPensionerSeenDtls, inputMap);
	            }
	            if(trnPensionRivisionDtlVO != null)
	            {
	            	saveMstPensionerRvsnDtls(trnPensionRivisionDtlVO,inputMap);
	            }
	            
	            TrnPensionCaseMvmnt trnPensionCaseMvmnt = new TrnPensionCaseMvmnt();
	            if (pensionerCode == null || pensionerCode.length() == 0)
	            {
	                trnPensionCaseMvmnt.setCreatedDate(new Date());
	                trnPensionCaseMvmnt.setCreatedPostId(new BigDecimal(gLngPostId));
	                trnPensionCaseMvmnt.setCreatedUserId(new BigDecimal(gLngUserId));
	                trnPensionCaseMvmnt.setCurrentPostId(new BigDecimal(gLngPostId));
	                trnPensionCaseMvmnt.setCurrentUserId(new BigDecimal(gLngUserId));
	                trnPensionCaseMvmnt.setMovementId(Short.parseShort("0"));
	                trnPensionCaseMvmnt.setMovementStatus(gObjRsrcBndle.getString("CMN.CASEINWD"));
	                trnPensionCaseMvmnt.setPensionRequestId(trnPensionRqstHdrID);
	                trnPensionCaseMvmnt.setPpoNo(trnPensionRqstHdrVO.getPpoNo());
	                trnPensionCaseMvmnt.setStatus("CONTINUE");
	                Long ltrnPensionCaseMvmntId = IDGenerateDelegate.getNextId("trn_pension_case_mvmnt", inputMap);
	                trnPensionCaseMvmnt.setTrnPenionCaseMvmntId(ltrnPensionCaseMvmntId);
	                TrnPensionCaseMvmntDAOImpl trnPensionCaseMvmntDAOImpl = new TrnPensionCaseMvmntDAOImpl(
	                        TrnPensionCaseMvmnt.class, serv.getSessionFactory());
	                trnPensionCaseMvmntDAOImpl.create(trnPensionCaseMvmnt);
	                if (gLngPostId == 100527)
	                {
	                    trnPensionCaseMvmnt = new TrnPensionCaseMvmnt();
	                    trnPensionCaseMvmnt.setCreatedDate(new Date());
	                    trnPensionCaseMvmnt.setCreatedPostId(new BigDecimal(gLngPostId));
	                    trnPensionCaseMvmnt.setCreatedUserId(new BigDecimal(gLngUserId));
	                    trnPensionCaseMvmnt.setCurrentPostId(new BigDecimal(gLngPostId));
	                    trnPensionCaseMvmnt.setCurrentUserId(new BigDecimal(gLngUserId));
	                    trnPensionCaseMvmnt.setMovementId(Short.parseShort("1"));
	                    trnPensionCaseMvmnt.setMovementStatus("CASEAUD");
	                    trnPensionCaseMvmnt.setPensionRequestId(trnPensionRqstHdrVO.getPensionRequestId());
	                    trnPensionCaseMvmnt.setPpoNo(trnPensionRqstHdrVO.getPpoNo());
	                    trnPensionCaseMvmnt.setStatus("CONTINUE");
	                    ltrnPensionCaseMvmntId = IDGenerateDelegate.getNextId("trn_pension_case_mvmnt", inputMap);
	                    trnPensionCaseMvmnt.setTrnPenionCaseMvmntId(ltrnPensionCaseMvmntId);
	                    trnPensionCaseMvmntDAOImpl = new TrnPensionCaseMvmntDAOImpl(TrnPensionCaseMvmnt.class, serv
	                            .getSessionFactory());
	                    trnPensionCaseMvmntDAOImpl.create(trnPensionCaseMvmnt);
	                }
	            }
	            if (wfVO != null)
	            {
	                wfVO.setJobRefId(trnPensionRqstHdrID.toString());
	                wfVO.setAppMap(inputMap);
	                ArgsMap.put("WorkFlowVO", wfVO);
	            }
        	}

        }
        catch (Exception e)
        {
            gLogger.error("Error is :" + e, e);
        }
        return ArgsMap;
    }


    public ResultObject insertPensionCaseDtls(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try
        {
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            String isWorkFlow = null;
            if(inputMap.containsKey("isWorkFlow"))
                isWorkFlow = inputMap.get("isWorkFlow").toString();
            //if (pensionerCode != null && pensionerCode.length() > 0 && ! inputMap.get("AUDITFlag").toString().equalsIgnoreCase("Y"))

            if(! "YES".equalsIgnoreCase(isWorkFlow))
            {
                inputMap = insertPensionCase(inputMap);
            }
            else
            {
                WorkFlowVO workFlowVO = new WorkFlowVO();
                Connection conn = serv.getSessionFactory().getCurrentSession().connection();
                String subjectName = gObjRsrcBndle.getString("PENSION.CASESUBJECT");
                workFlowVO.setAppMap(inputMap);
                workFlowVO.setCrtEmpId(SessionHelper.getEmpId(inputMap).toString());
                workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
                workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
                workFlowVO.setFromPost(SessionHelper.getPostId(inputMap).toString());
                workFlowVO.setConnection(conn);
                OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
                Map resultMap = orgUtil.getHierarchyByPostIDAndDescription(
                        SessionHelper.getPostId(inputMap).toString(), subjectName);
                List resultList = (List) resultMap.get("Result");
                Long lStrHeirRefId = new Long(0);
                if (resultList != null && resultList.size() > 0)
                {
                    lStrHeirRefId = Long.parseLong(resultList.get(0).toString());
                }
                workFlowVO.setHirRefId(lStrHeirRefId);
                WorkFlowUtility wfUtility = new WorkFlowUtility();
                workFlowVO.setActId(1);
                workFlowVO.setDocId(Long.parseLong(gObjRsrcBndle.getString("PENSION.CASEDOCUMENTID")));
                workFlowVO.setJobRefId("1");
                workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
                workFlowVO.setDbId(SessionHelper.getDbId(inputMap));
                workFlowVO.setJobTitle("PENSIONCASE");
                workFlowVO.setHierarchyFlag(1);
                wfUtility.invokeWorkFlow(workFlowVO);
            }
            // Map mp = wrkFlw.getAppMap();
            // inputMap.put("requestObj", mp.get("requestObj"));
            // resObj.setResultValue(inputMap);
            // resObj = loadInwdPensionCase(inputMap);
            StringBuilder lStrBldXML = getResponseXMLDoc(inputMap);
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
            if (request.getParameter("isConverting") != null && request.getParameter("isConverting").length() > 0)
            {
                TrnPensionRqstHdr trnPnsnRqstHdrVo = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
                TrnPensionRqstHdr objTrnPnsnRqstHdrVo = new TrnPensionRqstHdr();
                objTrnPnsnRqstHdrVo.setTotalSrvc(trnPnsnRqstHdrVo.getTotalSrvc());
                objTrnPnsnRqstHdrVo.setStatus(trnPnsnRqstHdrVo.getStatus());
                objTrnPnsnRqstHdrVo.setSchemeType(trnPnsnRqstHdrVo.getSchemeType());
                objTrnPnsnRqstHdrVo.setTypeFlag(gObjRsrcBndle.getString("CMN.CONVERTTFLAG"));
                resObj = serv.executeService("LOAD_PENSION_CASE", inputMap);
                Map inputMap1 = (Map) resObj.getResultValue();
                inputMap.put("TrnPensionRqstHdrVO", objTrnPnsnRqstHdrVo);
                resObj.setResultValue(inputMap1);
                resObj.setViewName("HeaderMenuPopup");
            }
            else
            {
                resObj.setViewName("ajaxData");
            }
            resObj.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }

    public ResultObject getSavedCases(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrMyCases = null;
        try
        {
        	setSessionInfo(inputMap);
            CommonPensionService lObjCommonPensionService = new CommonPensionServiceImpl();
            lStrMyCases = lObjCommonPensionService.getMyCases(inputMap);
            PensionBillServiceImpl lobjPensionBillservice = new PensionBillServiceImpl();
            lobjPensionBillservice.getPensionHierarchyUsers(inputMap,gObjRsrcBndle.getString("PENSION.CASESUBJECT"));
            int lIntFrmLevel = 0;
            String lStrFrmLevel = "";
            if(request.getParameter("caseFromlevel") != null)
            {
            	lStrFrmLevel =  request.getParameter("caseFromlevel").toString();
            	inputMap.put("caseFromlevel", lStrFrmLevel);
            }
            if(lStrFrmLevel != "")
            {
            	lIntFrmLevel = Integer.parseInt(lStrFrmLevel);
            }
            if(lIntFrmLevel != 0 && lIntFrmLevel < 20)
            {
            	inputMap.put("isShowHirchy", "Y");
            }
            // lStrMyCases = getMyCases(inputMap);
            inputMap.put("SavedCases", lStrMyCases);
            if(request.getParameter("cmbSearch") != null && ! request.getParameter("cmbSearch").toString().equals("-1"))
            {
                inputMap.put("srchStr",request.getParameter("cmbSearch").toString() );
            }
            if(request.getParameter("status") != null)
            {
                String status = StringUtility.getParameter("status", request);
                inputMap.put("status", status);
            }
            PensionCaseDAOImpl pnsnCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv.getSessionFactory());
            List savedCaseList = pnsnCaseDAOImpl.getSavedCaseDtls(inputMap);
           // Long totalCount = pnsnCaseDAOImpl.getSavedCaseDtlsCount(inputMap);
            if(savedCaseList != null && savedCaseList.size()>0)
            {
                inputMap.put("SavedCaseList", savedCaseList);
            }
           // inputMap.put("totalRecords",totalCount);
            inputMap.put("userList", inputMap.get("userList"));
            resObj.setResultValue(inputMap);
            if(lStrFrmLevel != null && "10".equals(lStrFrmLevel) || (StringUtility.getParameter("isCaseCrdx",request) != null && "Y".equalsIgnoreCase(StringUtility.getParameter("isCaseCrdx",request))))
            {
            	resObj.setViewName("CaseCardex");
            }
            else
            {
            	resObj.setViewName("savedCases");
            }
            
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }
    public ResultObject getSendToOthrs(Map orgsMap)
    {
    	 int llFromLevelId = 0;
         ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
         ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
         String lStrHirarchyRefId = null;
         String lStrOtherType = null;
         HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");
         PensionBillDAOImpl lObjPensnBilldao = new PensionBillDAOImpl(serv.getSessionFactory());
         try
         {
        	 setSessionInfo(orgsMap);
        	 WorkFlowVO workFlowVO = getDefaultWorkFlowVO(orgsMap);
             OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
             Map resultMap = orgUtil.getHierarchyByPostIDAndDescription(
                     SessionHelper.getPostId(orgsMap).toString(), gObjRsrcBndle.getString("PENSION.CASESUBJECT"));
             List resultList = (List) resultMap.get("Result");
             Long lStrHeirRefId = new Long(0);
             if (resultList != null && resultList.size() > 0)
             {
                 lStrHeirRefId = Long.parseLong(resultList.get(0).toString());
             }
             lStrHirarchyRefId = lStrHeirRefId.toString();
             if(request.getParameter("actionVal") != null)
        	 {
        		 lStrOtherType = request.getParameter("actionVal");
        	 }
             String lStrFromLevel = StringUtility.getParameter("caseFromlevel", request);
             if(lStrFromLevel != null && lStrFromLevel.length() >0)
             {
            	 llFromLevelId = Integer.parseInt(lStrFromLevel);           	 
             }
	   		  if(lStrOtherType != null && lStrOtherType.length()>0)
	   		  { 
	   			  List lLstTemp =  lObjPensnBilldao.getOtherUsersList(String.valueOf(llFromLevelId) ,lStrHirarchyRefId,lStrOtherType,gLngPostId.toString(),"PPO",gObjRsrcBndle.getString("PENSION.CASESUBJECT"));
		   		  if(lLstTemp.size()>0)
		   		  {
		   			  orgsMap.put("RegList", lLstTemp); 
		   		  }
	   		  }
   		  lObjResult.setResultValue(orgsMap);
   		  lObjResult.setViewName("cmnCaseSelectionFrwd");
         }
         catch(Exception e)
         {
        	  gLogger.error("Error is;" + e, e);
        	  lObjResult.setResultValue(null);
        	  lObjResult.setThrowable(e);
        	  lObjResult.setResultCode(ErrorConstants.ERROR);
        	  lObjResult.setViewName("errorPage");
         }
    	return lObjResult;
    }
       public ResultObject frwdPensionCase(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        String lStrFrmLevel = "";
        String jobRefId = null;
        String[] lStrPPONo = null;
        String lStrReturnCase = null;
        String lStrFlag = "";
        short lIntCaseStatus = 0;
        try
        {
            // Workflow Code starts here
            WorkFlowVO workFlowVO = new WorkFlowVO();
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            Connection conn = serv.getSessionFactory().getCurrentSession().connection();
            PensionBillServiceImpl lobjPensionBillservice = new PensionBillServiceImpl();
            TrnPensionRqstHdrDAO lObjRqstHdr = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,serv.getSessionFactory());
            String curPostId = SessionHelper.getPostId(inputMap).toString();
            if(request.getParameter("status") != null)
            {
                inputMap.put("status",request.getParameter("status").toString());
            }
            if(request.getParameter("actionVal") != null)
            lStrFlag= request.getParameter("actionVal");
            if(lStrFlag != "" && (lStrFlag.equals("SP") || lStrFlag.equals("SOT")))
            {
            	objRes = getSendToOthrs(inputMap);
            }
            else
            {
            	 inputMap.put("actionVal", lStrFlag);
                 
                 int lIntFrmLevel = 0;
                 if(request.getParameter("caseFromlevel") != null)
                 {
                 	lStrFrmLevel =  request.getParameter("caseFromlevel").toString();
                 }
                 if(lStrFrmLevel != "")
                 {
                 	lIntFrmLevel = Integer.parseInt(lStrFrmLevel);
                 }
                 if (StringUtility.getParameterValues("chkbxTrnPensionRqstHdrId", request).length > 0) // hv
                     // to chng pnsncaseId with PPONO
                 lStrPPONo = StringUtility.getParameterValues("chkbxTrnPensionRqstHdrId",request);
                 if (inputMap.containsKey("PnsnCaseID"))
                 {
                 	lStrPPONo = new String[1];
                 	lStrPPONo[0] = (String) (inputMap.get("PnsnCaseID"));
                 }
                 PensionCaseDAOImpl pensionCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv
                         .getSessionFactory());

                 if(lStrPPONo != null && lStrPPONo.length > 0)
                 {
                 	for(int i=0;i<lStrPPONo.length;i++)
                 	{
                 
     		            Long lbgdcCaseId = (Long) pensionCaseDAOImpl.getCaseIdfromPpoNo(lStrPPONo[i],gObjRsrcBndle.getString("CMN.NEW"));
     		            if(lbgdcCaseId <= 0)
     		            {
     		            	lbgdcCaseId = pensionCaseDAOImpl.getCaseIdfromPpoNo(lStrPPONo[i], gObjRsrcBndle.getString("STATUS.APPROVED"));
     		            }
     		            jobRefId = lbgdcCaseId.toString();
     		            inputMap.put("PnsnCaseID", lStrPPONo[i]);
     		            inputMap.put("PensionCaseID", jobRefId);
     		            // long lDocId=210001;
     		            long lDocId = Long.parseLong(gObjRsrcBndle.getString("PENSION.CASEDOCUMENTID"));
     		            workFlowVO.setAppMap(inputMap);
     		            workFlowVO.setCrtEmpId(SessionHelper.getUserId(request).toString());
     		            workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
     		            workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
     		            workFlowVO.setFromPost(curPostId);
     		            workFlowVO.setConnection(conn);
     		            String toPost = null;
     		            String toLevel = null;
     		            if (request.getParameter("toPostCase") != null && request.getParameter("toPostCase").length() > 0)
     		            { // when bill and case are seperately forwarded or case is alone
     		                // forwarded
     		                String[] lArray = null;
     		                if(lStrFlag.equalsIgnoreCase("approve") || lStrFlag.equalsIgnoreCase("reject"))
     		                {
     		                	toPost = lObjRqstHdr.read(lbgdcCaseId).getCaseOwner().toString();
     		                    toLevel = gObjRsrcBndle.getString("WFPENSION.AUDITORLEVEL").toString();
     		                    if(lStrFlag.equalsIgnoreCase("approve"))
     		                    lIntCaseStatus =  75;
     		                    if(lStrFlag.equalsIgnoreCase("reject"))
     		                    {
     		                    	lIntCaseStatus = 85;
     		                    }
     		                }
     		                else
     		                {
     		                	lArray = request.getParameter("toPostCase").toString().split("~");
     		                    toPost = lArray[0];
     		                    toLevel = lArray[1];
     		                    if(toLevel.equals("5"))
     		                    {
     		                    	lIntCaseStatus = 1;
     		                    }
     		                    if(toLevel.equals("10"))
     		                    {
     		                    	lIntCaseStatus = 3;
     		                    }
     		                    if(toLevel.equals("20"))
     		                    {
     		                    	lIntCaseStatus = 5;
     		                    }
     		                    if(Integer.parseInt(toLevel)>40)
     		                    {
     		                    	lIntCaseStatus = lobjPensionBillservice.getStatusByLevel(Short.valueOf(toLevel));
     		                    }
     							workFlowVO.setToLevelId(Integer.parseInt(toLevel));
     						}
     		            }
     		            else if (inputMap.containsKey("toPost"))
     		            { // when case is forwarded with bill topost is selected from bill popup
     		            	
     		                toPost = (String)inputMap.get("toPost");
     		                toLevel = (String)inputMap.get("toLevel");
     		            }
     		            
     		            workFlowVO.setToPost(toPost);
     		            workFlowVO.setToLevelId(Integer.parseInt(toLevel));
     		            inputMap.put("toPost", toPost);
     		            inputMap.put("CurrCaseStatus", String.valueOf(lIntCaseStatus));
     		            workFlowVO.setAppMap(inputMap);
     		            WorkFlowUtility wfUtility = new WorkFlowUtility();
     		            workFlowVO.setActId(2);
     		            workFlowVO.setDocId(lDocId);
     		            if (request.getParameter("returnCase") != null && request.getParameter("returnCase").length() > 0) 
     		                lStrReturnCase = (String) request.getParameter("returnCase");
     		            if (lStrReturnCase != null && lStrReturnCase.equals("1")) 
     		            {
     		                workFlowVO.setActId(3);
     		            }
     		            workFlowVO.setJobRefId(jobRefId);
     		            workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
     		            workFlowVO.setDbId(SessionHelper.getDbId(inputMap));
     		            workFlowVO.setHierarchyFlag(1);
     		            workFlowVO.setJobTitle("PENSIONCASE");
     		            workFlowVO.setHierarchyFlag(1);
     		            wfUtility.invokeWorkFlow(workFlowVO);
                 	}
                 }
                 objRes.setResultValue(inputMap);
                 if((request.getParameter("status") != null && "1".equals(request.getParameter("status").toString())) || lIntFrmLevel != 0 && lIntFrmLevel < 20 || "0".equals(request.getParameter("status")))
                 {
                 	objRes = getSavedCases(inputMap);
                 	 if(lStrFrmLevel != null && "10".equals(lStrFrmLevel) )
                      {
                 		 objRes.setViewName("CaseCardex");
                      }
                 	 else
                 	 {
                 		 objRes.setViewName("savedCases");
                 	 }
                 }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            objRes.setResultValue(null);
            objRes.setThrowable(e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");
        }
        return objRes;
    }

    public ResultObject forwardPensionCaseToAuditor(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        try
        {
            WorkFlowVO workFlowVO = new WorkFlowVO();
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            Connection conn = serv.getSessionFactory().getCurrentSession().connection();
            String jobRefId = null;
            String ppoNo = null;
            String toPost = null;
            String toLevelId = gObjRsrcBndle.getString("WFPENSION.AUDITORLEVEL");
            TrnPensionRqstHdr trnPensionRqstHdr = null;
            TrnPensionRqstHdrDAOImpl trnPensionRqstHdrDAOImpl = null;
            PensionCaseDAOImpl pensionCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv
                    .getSessionFactory());
            String[] lCaseArray = null;
            workFlowVO.setAppMap(inputMap);
            workFlowVO.setConnection(conn);
            workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
            workFlowVO.setDbId(SessionHelper.getDbId(inputMap));
            workFlowVO.setCrtEmpId(SessionHelper.getUserId(request).toString());
            workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
            workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
            workFlowVO.setFromPost(SessionHelper.getPostId(inputMap).toString());
            workFlowVO.setToLevelId(Integer.parseInt(toLevelId));
            workFlowVO.setActId(Integer.parseInt(gObjRsrcBndle.getString("WFPENSION.FORWARDACTION")));
            workFlowVO.setDocId(Long.parseLong(gObjRsrcBndle.getString("PENSION.CASEDOCUMENTID")));
            workFlowVO.setHierarchyFlag(1);
            WorkFlowUtility wfUtility = new WorkFlowUtility();
            if (request.getParameter("toPostCase") != null && request.getParameter("toPostCase").length() > 0)
            {
                lCaseArray = request.getParameter("toPostCase").toString().split(":");
                for (int cntr = 0; cntr < lCaseArray.length; cntr++)
                {
                    if(lCaseArray[cntr] != null && lCaseArray[cntr].length()>0)
                    {
                        String[] lArray = lCaseArray[cntr].split("~");
                        toPost = lArray[1];
                        ppoNo = lArray[0];
                        Long lbgdcCaseId = (Long) pensionCaseDAOImpl.getCaseIdfromPpoNo(ppoNo,gObjRsrcBndle.getString("CMN.NEW"));
                        if(lbgdcCaseId <= 0)
                        {
                            lbgdcCaseId = (Long) pensionCaseDAOImpl.getCaseIdfromPpoNo(ppoNo,gObjRsrcBndle.getString("STATUS.APPROVED"));
                        }
                        jobRefId = lbgdcCaseId.toString();
                        inputMap.put("PnsnCaseID", ppoNo);
                        inputMap.put("PensionCaseID", jobRefId);
                        inputMap.put("toPost", toPost);
                        inputMap.put("CaseStatus", "FORWARD");
                        workFlowVO.setToPost(toPost);
                        workFlowVO.setJobRefId(jobRefId);
                        workFlowVO.setJobTitle("PENSIONCASE");
                        workFlowVO.setHierarchyFlag(1);
                        wfUtility.invokeWorkFlow(workFlowVO);
                        trnPensionRqstHdr = new TrnPensionRqstHdr();
                        trnPensionRqstHdrDAOImpl = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, serv
                                .getSessionFactory());
                        trnPensionRqstHdr = trnPensionRqstHdrDAOImpl.read(Long.valueOf(jobRefId));
                        trnPensionRqstHdr.setCaseOwner(new BigDecimal(toPost));
                        trnPensionRqstHdr.setcurrCaseStatus(75);
                        trnPensionRqstHdrDAOImpl.update(trnPensionRqstHdr);
                    }
                }
            }
            objRes = auditorCaseDisribute(inputMap);
            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            objRes.setResultValue(null);
            objRes.setThrowable(e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");
        }
        return objRes;
    }

    public Map forwardPensionCase(Map objectArgs)
    {
        try
        {
            String caseStatus = null;
            WorkFlowVO wfVO = (WorkFlowVO) objectArgs.get("WorkFlowVO");
            Map servicemap = wfVO.getAppMap();
            setSessionInfo(servicemap);
            ServiceLocator serv = (ServiceLocator) servicemap.get("serviceLocator");
            PensionCaseDAOImpl pensionCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv
                    .getSessionFactory());
            String caseId = (String) servicemap.get("PensionCaseID");
            String lStrRejFlag = (String) servicemap.get("actionVal");
           
            if(lStrRejFlag != null && lStrRejFlag.equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVE")))
            {
                caseStatus = "APPROVED";
            }
            else if(lStrRejFlag != null && lStrRejFlag.equalsIgnoreCase("reject"))
            {
                caseStatus = "REJECTED";
            }
            else
            {
                caseStatus = "FORWARD";
            }
            // CaseStatus
            TrnPensionCaseMvmnt trnPensionCaseMvmnt = new TrnPensionCaseMvmnt();
            trnPensionCaseMvmnt.setCreatedDate(new Date());
            BigDecimal lLbdtoPost =  new BigDecimal(servicemap.get("toPost").toString());
            trnPensionCaseMvmnt.setCreatedPostId(new BigDecimal(gLngPostId));
            trnPensionCaseMvmnt.setCreatedUserId(new BigDecimal(gLngUserId));
            trnPensionCaseMvmnt.setCurrentPostId(lLbdtoPost);
            trnPensionCaseMvmnt.setCurrentUserId(lLbdtoPost);
            trnPensionCaseMvmnt.setMovementStatus(caseStatus);
            if (caseId != null && caseId.length() > 0)
            {
                trnPensionCaseMvmnt.setPensionRequestId(Long.valueOf(caseId));
                trnPensionCaseMvmnt.setPpoNo((String) servicemap.get("PnsnCaseID"));
                servicemap.put("ppoNo", servicemap.get("PnsnCaseID"));
                Integer movementId = pensionCaseDAOImpl.getNextMovementId(servicemap);
                movementId += 1;
                trnPensionCaseMvmnt.setMovementId(Short.parseShort(movementId.toString().trim()));
            }
            
            trnPensionCaseMvmnt.setStatus("CONTINUE");
           
            Long ltrnPensionCaseMvmntId = IDGenerateDelegate.getNextId("trn_pension_case_mvmnt", servicemap);
            trnPensionCaseMvmnt.setTrnPenionCaseMvmntId(ltrnPensionCaseMvmntId);
            TrnPensionCaseMvmntDAOImpl trnPensionCaseMvmntDAOImpl = new TrnPensionCaseMvmntDAOImpl(
                    TrnPensionCaseMvmnt.class, serv.getSessionFactory());
            trnPensionCaseMvmntDAOImpl.create(trnPensionCaseMvmnt);
            //
            if (caseId != null && caseId.length() > 0)
            {
	            String lIntCurrCaseStatus = "";
	            if(servicemap.containsKey("CurrCaseStatus"))
	            {
	            	lIntCurrCaseStatus = (String) servicemap.get("CurrCaseStatus");
	            }
	            if(! lIntCurrCaseStatus.equals("0"))
	            {
	            	TrnPensionRqstHdrDAOImpl trnPensionRqstHdrDAOImpl = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, serv
		                    .getSessionFactory());
		            TrnPensionRqstHdr trnPensionRqstHdr = trnPensionRqstHdrDAOImpl.read(Long.valueOf(caseId));
		            if(caseStatus.equalsIgnoreCase("REJECTED"))
		            {
		            	lIntCurrCaseStatus = "80";
		            }
		            trnPensionRqstHdr.setcurrCaseStatus(Integer.valueOf(lIntCurrCaseStatus));
		            trnPensionRqstHdrDAOImpl.update(trnPensionRqstHdr);	
	            }
            }
            
            //Following method called for setting case status swaping for Audit Trail
            if(lStrRejFlag != null && (lStrRejFlag.equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVE"))||lStrRejFlag.equalsIgnoreCase("reject")))
            {
            	//serv.executeService("GET_REVISED_ARREAR_DTLS",servicemap);
                List<TrnPensionCaseMvmnt> lLstCaseMvmntVo =  trnPensionCaseMvmntDAOImpl.getCaseMvmntDtlsByStatusAndPPONo((String) servicemap.get("PnsnCaseID"),"CONTINUE");
            	if(lLstCaseMvmntVo != null && ! lLstCaseMvmntVo.isEmpty())
                {
                    Iterator<TrnPensionCaseMvmnt> itr = lLstCaseMvmntVo.iterator();
                    TrnPensionCaseMvmnt lOnbjMvmntVo = null;
                    while(itr.hasNext())
                    {
                        lOnbjMvmntVo = itr.next();
                        lOnbjMvmntVo.setStatus("CLOSE");
                        trnPensionCaseMvmntDAOImpl.update(lOnbjMvmntVo);
                    }
                }
                setTrnpensionRqstHdrCaseStatus((String) servicemap.get("PnsnCaseID"),servicemap);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
        }
        return objectArgs;
    }

    public ResultObject getPensionCaseData(Map inputMap)
    {
        setSessionInfo(inputMap);
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        TrnPensionerSeenDtls lObjSeenDtlsVo = null;
        List<MstPensionerFamilyDtls> lLstFamilyDtlsVo = null;
        Double lDblSpclCutAmt = 0.00;
        Double lDblOtherBenefitAmt = 0.00;
        Double lDblITCutAmt = 0.00;
        Double lDblPnsnCutAmt = 0.00;
        Double lDblArrerCutAmt = 0.00;
        Double lDblRcvryAmt = 0.00;
        String lStrRevDisable = "";
        String lStrArrTI = gObjRsrcBndle.getString("ARREAR.TI");
        String lStrArrMA = gObjRsrcBndle.getString("ARREAR.MA");
        String lStrArrPension = gObjRsrcBndle.getString("ARREAR.PENSION");
        String lStrArrFP1 = gObjRsrcBndle.getString("ARREAR.FP1");
        String lStrArrFP2 = gObjRsrcBndle.getString("ARREAR.FP2");
        try
        {
            Long mstPnsnDtlsID1 = null;
            String ppoNo = StringUtility.getParameter("ppoNo", request);
            CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            CommonPensionServiceImpl CommonPensionService = new CommonPensionServiceImpl();
            PensionCaseDAOImpl pnsnCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv.getSessionFactory());
            Long pnsnCaseNo = 0L;
            Long lLngNewPnsnCaseNo = 0L;
            Long lLngAprvdPnsnCaseNo = 0L;
            String pnsnCode = "";
            if(ppoNo != null && ppoNo.length()>0)
            {
            	pnsnCaseNo = pnsnCaseDAOImpl.getCaseIdfromPpoNo(ppoNo, gObjRsrcBndle.getString("CMN.NEW"));
            	lLngNewPnsnCaseNo = pnsnCaseNo;
            	lStrRevDisable = "disabled";
            }
            if(pnsnCaseNo <= 0 || (StringUtility.getParameter("auditFlag",request) != null && "Y".equalsIgnoreCase(StringUtility.getParameter("auditFlag",request).toString())))
            {
            	pnsnCaseNo = pnsnCaseDAOImpl.getCaseIdfromPpoNo(ppoNo, gObjRsrcBndle.getString("STATUS.APPROVED"));
            	lLngAprvdPnsnCaseNo = pnsnCaseNo;
            	if(lLngNewPnsnCaseNo > 0)
            	{
            		pnsnCaseNo = lLngNewPnsnCaseNo;
            	}
            	lStrRevDisable = "";
            }
            inputMap.put("RevDisable", lStrRevDisable);
            MstPensionerHdrDAOImpl mstPnsnHdrDAOImpl = new MstPensionerHdrDAOImpl(MstPensionerHdr.class, serv
                    .getSessionFactory());
            
        	pnsnCode = pnsnCaseDAOImpl.getPensionerCodefromPpoNo(ppoNo,gObjRsrcBndle.getString("CMN.NEW")) ;
        	if(pnsnCode.length() <= 0)
        	{
        		pnsnCode = pnsnCaseDAOImpl.getPensionerCodefromPpoNo(ppoNo,gObjRsrcBndle.getString("STATUS.APPROVED")) ;
        	}
           
           
            Long lLngPnsnrId = 0L;
            MstPensionerHdr mstPnsnHdrVo = new MstPensionerHdr();
             
            lLngPnsnrId = mstPnsnHdrDAOImpl.getPensionerIdfromPensnrCode(pnsnCode, gObjRsrcBndle.getString("CMN.NEW"));
            if(lLngPnsnrId <=0)
            {
            	lLngPnsnrId = mstPnsnHdrDAOImpl.getPensionerIdfromPensnrCode(pnsnCode, gObjRsrcBndle.getString("STATUS.APPROVED"));
            }
            
            if(lLngPnsnrId > 0)
            {
                mstPnsnHdrVo = mstPnsnHdrDAOImpl.read(Long.valueOf(lLngPnsnrId));
            }
          
            TrnPensionRqstHdrDAOImpl trnPnsnRqstHdrDAOImpl = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, serv
                    .getSessionFactory());
            TrnPensionRqstHdr trnPnsnRqstHdrVo = new TrnPensionRqstHdr();
            if (pnsnCaseNo != null &&  pnsnCaseNo > 0)
            {
                trnPnsnRqstHdrVo = trnPnsnRqstHdrDAOImpl.read(pnsnCaseNo);
            }
            if(ppoNo != null)
            {
            	 TrnPensionRqstHdr lObjChngdRqstHdr = new TrnPensionRqstHdr();
             	lObjChngdRqstHdr = pnsnCaseDAOImpl.getChangedRqstHdrVo(ppoNo);
             	inputMap.put("ChangedRqsHdrVO", lObjChngdRqstHdr);
            }
           
            if(lLngNewPnsnCaseNo > 0 && lLngAprvdPnsnCaseNo > 0 )
            {
            	inputMap.put("POPUP", "YES");
            }
            else if(lLngAprvdPnsnCaseNo > 0)
            {
            	inputMap.put("POPUP", "NO");
            	TrnPensionRqstHdr lObjAprvdRqstHdr = new TrnPensionRqstHdr();
            	lObjAprvdRqstHdr = trnPnsnRqstHdrDAOImpl.read(lLngAprvdPnsnCaseNo);
              	inputMap.put("ApprovedRqsHdrVO", lObjAprvdRqstHdr);
            }
            else
            {
            	inputMap.put("POPUP", "NO");
            }

            //resObj = loadInwdPensionCase(inputMap);
            
            if (mstPnsnHdrVo.getStateCode() != null)
            {
                inputMap.put("state", mstPnsnHdrVo.getStateCode());

                resObj = CommonPensionService.getDistricts(inputMap);
            }
            MstPensionerDtls mstPnsnrDtlsVo = new MstPensionerDtls();
            MstPensionerDtlsDAOImpl mstPnsnrDtlsDAOimpl = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class, serv
                    .getSessionFactory());
            if (pnsnCode != null && pnsnCode.length() > 0)
            {
                mstPnsnDtlsID1 = mstPnsnrDtlsDAOimpl.getPnsionerDtlsIdFromPensionerCode(pnsnCode,gObjRsrcBndle.getString("CMN.NEW"));
                if (mstPnsnDtlsID1 <= 0)
                {
                	mstPnsnDtlsID1 = mstPnsnrDtlsDAOimpl.getPnsionerDtlsIdFromPensionerCode(pnsnCode,gObjRsrcBndle.getString("STATUS.APPROVED"));
                }
               	mstPnsnrDtlsVo = mstPnsnrDtlsDAOimpl.read(mstPnsnDtlsID1);

            }
            if (mstPnsnHdrVo != null && mstPnsnrDtlsVo.getBankCode() != null)
            {
                inputMap.put("bank", mstPnsnrDtlsVo.getBankCode());
                resObj = CommonPensionService.getBranch(inputMap);
                Map lMapBranch = (Map) resObj.getResultValue();
                List llistBranch = (List) lMapBranch.get("listBranch");
                inputMap.put("listBranch", llistBranch);
            }
            if (mstPnsnHdrVo != null && mstPnsnHdrVo.getDeptCode() != null)
            {
                inputMap.put("department", mstPnsnHdrVo.getDeptCode());
                resObj = CommonPensionService.getHOD(inputMap);
                Map lHodMap = (Map) resObj.getResultValue();
                ArrayList lLstHod = (ArrayList) lHodMap.get("listHod");
                inputMap.put("listHod", lLstHod);
            }
            if (pnsnCode != null && pnsnCode.length() > 0)
            {

                MstPensionerNomineeDtlsDAOImpl mstPensionerNomineeDtlsDAOImpl = new MstPensionerNomineeDtlsDAOImpl(
                        MstPensionerNomineeDtls.class, serv.getSessionFactory());
                ArrayList listNmDtls = (ArrayList) mstPensionerNomineeDtlsDAOImpl
                        .getListOfNominee(pnsnCode, gLngLangId);
                if (listNmDtls != null && listNmDtls.size() > 0)
                {
                    inputMap.put("PensionerNomineeDtlsList", listNmDtls);
                }
                MstPensionerFamilyDtlsDAOImpl mstPensionerFamilyDtlsDAOImpl = new MstPensionerFamilyDtlsDAOImpl(
                        MstPensionerFamilyDtls.class, serv.getSessionFactory());
                List lLstFamilyPk = mstPensionerFamilyDtlsDAOImpl.getFamilyDtlsPks(pnsnCode, gLngLangId);
                if (lLstFamilyPk != null || !lLstFamilyPk.isEmpty())
                {
                    lLstFamilyDtlsVo = new ArrayList<MstPensionerFamilyDtls>();
                    Iterator lItr = lLstFamilyPk.iterator();
                    while (lItr.hasNext())
                    {
                        Long lLngPk = (Long) lItr.next();
                        MstPensionerFamilyDtls loBjVo = mstPensionerFamilyDtlsDAOImpl.read(lLngPk);
                        lLstFamilyDtlsVo.add(loBjVo);
                    }
                }
                inputMap.put("PensionerFamilyDtlsList", lLstFamilyDtlsVo);
            }
            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            String lStrHeadCodeDesc = null;
            if (trnPnsnRqstHdrVo.getHeadCode() != null)
            {
                lStrHeadCodeDesc = lObjCommonPensionDAO.getAllHeadCodeDesc(trnPnsnRqstHdrVo.getHeadCode().toString(),
                        SessionHelper.getLangId(inputMap));
            }
            TrnPensionerSeenDtlsDao lObjSeenDtlsDao = new TrnPensionerSeenDtlsDaoImpl(TrnPensionerSeenDtls.class, serv
                    .getSessionFactory());
            Long lBDSeenDtlsPk = lObjSeenDtlsDao.getTrnSeenDtlsPk(pnsnCode, gLngLangId);
            if (lBDSeenDtlsPk != null)
            {
                lObjSeenDtlsVo = lObjSeenDtlsDao.read(lBDSeenDtlsPk);
                inputMap.put("TrnPensionerSeenDtls", lObjSeenDtlsVo);
            }
            TrnPrvosionalPensionDtls lObjVo = null;
            if (trnPnsnRqstHdrVo.getPensionerCode() != null && trnPnsnRqstHdrVo.getTypeFlag().equals("C"))
            {
                TrnPrvosionalPensionDtlsDao lObjProvDao = new TrnPrvosionalPensionDtlsDaoImpl(
                        TrnPrvosionalPensionDtls.class, serv.getSessionFactory());

                Long lBDProvId = null;
                lBDProvId = lObjProvDao.getPrvosionalPensionDtlsIdByPenCode(trnPnsnRqstHdrVo.getPensionerCode());
                if (lBDProvId != null)
                {
                    lObjVo = lObjProvDao.read(lBDProvId);
                }
            }
            if(trnPnsnRqstHdrVo != null && "Y".equalsIgnoreCase(trnPnsnRqstHdrVo.getIsRop()))
            {
        	  TrnPnsncaseRopRltDAO lObjROPDao = new TrnPnsncaseRopRltDAO(TrnPnsncaseRopRlt.class,serv.getSessionFactory());
        	  List lLStROPPks = lObjROPDao.getROPDtlsFromPpoNo(trnPnsnRqstHdrVo.getPpoNo());
        	  List<TrnPnsncaseRopRlt> lLstRopVo = new ArrayList<TrnPnsncaseRopRlt>(); 
        	  TrnPnsncaseRopRlt lObjVoRop = null;
        	  for(int i=0;i<lLStROPPks.size();i++)
        	  {
        		  lObjVoRop = new TrnPnsncaseRopRlt();
        		  lObjVoRop = lObjROPDao.read((Long) lLStROPPks.get(i));
        		  lLstRopVo.add(lObjVoRop);
        	  }
        	  inputMap.put("SavedRop", lLstRopVo);
            }
            if (lObjVo != null)
            {
                inputMap.put("TrnPrvosionalPensionDtls", lObjVo);
            }
            String lStrDCRGFlag = "N";
            String lStrCVPFlag = "N";
            String lStrPensionFlag = "N";
            
            Calendar cal = new GregorianCalendar();
            SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date lDate = cal.getTime();
            StringBuilder lStrCmprDate = new StringBuilder();
            int lIntCmpDate ;
            lStrCmprDate.append(lObjDateFormat.format(lDate).toString().substring(6, 10));
            lStrCmprDate.append(lObjDateFormat.format(lDate).toString().substring(3, 5));
            lIntCmpDate = Integer.valueOf(lStrCmprDate.toString());
            
            
            MonthlyPensionBillDAOImpl lObjMnthlyPnsn = new MonthlyPensionBillDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
            List lLst = lObjMnthlyPnsn.getItCutDtls(pnsnCode, String.valueOf(lIntCmpDate));
            if(lLst != null && lLst.size() > 0)
            {
            	for(int i=0;i<lLst.size();i+=2)
				{
					if("IT".equals(lLst.get(i))){
						lDblITCutAmt = lDblITCutAmt + Double.parseDouble(lLst.get(i+1).toString());
					}
					if("PT".equals(lLst.get(i))){
						lDblPnsnCutAmt = lDblPnsnCutAmt + Double.parseDouble(lLst.get(i+1).toString());
					}
					if("PP".equals(lLst.get(i))){
						lDblPnsnCutAmt = lDblPnsnCutAmt + Double.parseDouble(lLst.get(i+1).toString());
					}
					if("ST".equals(lLst.get(i))){
						lDblSpclCutAmt = lDblSpclCutAmt + Double.parseDouble(lLst.get(i+1).toString());
					}
					if("PermanentBenefit".equals(lLst.get(i))){
						lDblOtherBenefitAmt = lDblOtherBenefitAmt + Double.parseDouble(lLst.get(i+1).toString());
					}
					if("TemporaryBenefit".equals(lLst.get(i))){
						lDblOtherBenefitAmt = lDblOtherBenefitAmt + Double.parseDouble(lLst.get(i+1).toString());
					}
				}
				//gLogger.info("lPensionCutAmt"+lPensionCutAmt);
            }
            List lArrearDtls = lObjMnthlyPnsn.getArrearDtls(pnsnCode, String.valueOf(lIntCmpDate));
            if(lArrearDtls != null && lArrearDtls.size() > 0)
			{
				for(int i=0;i<lArrearDtls.size();i+=2)
				{
					if(lStrArrTI.equals(lArrearDtls.get(i)))
					{
						lDblArrerCutAmt = lDblArrerCutAmt + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					if(lStrArrMA.equals(lArrearDtls.get(i)))
					{
						lDblArrerCutAmt = lDblArrerCutAmt + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					if(lStrArrPension.equals(lArrearDtls.get(i)))
					{
						lDblArrerCutAmt = lDblArrerCutAmt + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					if(lStrArrFP1.equals(lArrearDtls.get(i)))
					{
						lDblArrerCutAmt = lDblArrerCutAmt + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
					if(lStrArrFP2.equals(lArrearDtls.get(i)))
					{
						lDblArrerCutAmt = lDblArrerCutAmt + Double.parseDouble(lArrearDtls.get(i+1).toString());
					}
				}
			}  
            
            lDblRcvryAmt =  lObjMnthlyPnsn.getRecoveryDtls(pnsnCode, "-1", String.valueOf(lIntCmpDate));
            
            List lLstBillType = commonPensionDAO.getBillTypeFromHeadCode(trnPnsnRqstHdrVo.getHeadCode());

            if (lLstBillType.contains("DCRG"))
            {
                lStrDCRGFlag = "Y";
            }
            if (lLstBillType.contains("PENSION"))
            {
                lStrPensionFlag = "Y";
            }
            if (lLstBillType.contains("CVP"))
            {
                lStrCVPFlag = "Y";
            }
            if(mstPnsnHdrVo.getAttachmentPhotoId() != null)
            {
                CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv
                        .getSessionFactory());
                CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(Long.valueOf(mstPnsnHdrVo.getAttachmentPhotoId().toString()));
                inputMap.put("scan", cmnAttachmentMst);
            }
            if(trnPnsnRqstHdrVo != null && mstPnsnHdrVo.getLocationCode() != gStrLocationCode )
            {
                List<TrnPensionCaseOutwrd> voList = new ArrayList<TrnPensionCaseOutwrd>();
                TrnPensionCaseOutwrdDAO lObjoutwardDao = new  TrnPensionCaseOutwrdDAO(TrnPensionCaseOutwrd.class , serv.getSessionFactory());
                List<TrnPensionCaseOutwrd> voList2 =  lObjoutwardDao.getPensionCaseOutwardDtls(trnPnsnRqstHdrVo.getPpoNo());
                if(voList2 != null && voList2.size() >1)
                {
                   for(int i=0;i<voList2.size()-1;i++)
                   {
                       voList.add(voList2.get(i));
                   }
                    inputMap.put("TrnPensionCaseOutwrdLst", voList);
                }
                if(voList != null && voList2.size()>0)
                {
                    inputMap.put("TrnPensionCaseOutwrdLastRow", voList2.get(voList2.size()-1));
                }
            }
            if(request.getParameter("CaseDisable") != null && "Y".equalsIgnoreCase(request.getParameter("CaseDisable").toString()))
            {
         	   inputMap.put("CaseDisable", request.getParameter("CaseDisable").toString());
            }
            inputMap.put("DCRG", lStrDCRGFlag);
            inputMap.put("CVP", lStrCVPFlag);
            inputMap.put("Pension", lStrPensionFlag);
            inputMap.put("RecvrAmt", lDblRcvryAmt);
            inputMap.put("ArrerCutAmt", lDblArrerCutAmt);
            inputMap.put("spclCut", lDblSpclCutAmt);
            inputMap.put("itCut", lDblITCutAmt);
            inputMap.put("pnsnCut", lDblPnsnCutAmt);
            inputMap.put("OtherBenefit", lDblOtherBenefitAmt);
            inputMap.put("HeadCodeDesc", lStrHeadCodeDesc);
            inputMap.put("PensionerCode", pnsnCode);
            inputMap.put("CaseNo", pnsnCaseNo.toString());
            inputMap.put("PensionerDtlsId", mstPnsnDtlsID1);
            inputMap.put("TrnPensionRqstHdrVO", trnPnsnRqstHdrVo);
            inputMap.put("MstPensionerHdrVO", mstPnsnHdrVo);
            inputMap.put("MstPensionerDtlsVO", mstPnsnrDtlsVo);
            inputMap.put("AUDITFlag", StringUtility.getParameter("auditFlag",request));
            resObj = loadInwdPensionCase(inputMap);
            inputMap.put("insert", "update");
            resObj.setResultValue(inputMap);
            resObj.setViewName("HeaderMenuPopup");
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }

    //for tracking the pension cases
    //Rupsa
    public ResultObject getSentCaseDtls(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        String lStrUserId = SessionHelper.getUserId(request).toString();
        String lStrLangId = SessionHelper.getLangId(inputMap).toString();
        try
        {
            PensionCaseDAO pnsnCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv.getSessionFactory());
            gLngUserId = Long.parseLong(lStrUserId);
            gLngLangId = Long.parseLong(lStrLangId);
            List sentCaseList = pnsnCaseDAOImpl.getSentCaseDtls(inputMap,gLngUserId,gLngLangId);
            int totalCount = pnsnCaseDAOImpl.getSentCaseDtlsCount(inputMap,gLngUserId,gLngLangId);
            inputMap.put("totalRecords", totalCount);
            inputMap.put("SentCaseList", sentCaseList);
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
        }
        resObj.setResultValue(inputMap);
        resObj.setViewName("sentCases");
        return resObj;
    }
    
    //graphical view of sent case tracking
    //Rupsa
    public ResultObject trackPensionCase(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");        
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrDocumentId = bundleConst.getString("PENSION.CASEDOCUMENTID");
        Long lLngDocumentId = Long.parseLong(lStrDocumentId);
        String lStrLocationId = SessionHelper.getLocationId(inputMap).toString();
        try
        {
            String lStrPPONo =  StringUtility.getParameter("ppoNo", request).trim().toString();
            String lStrLyingWithName = StringUtility.getParameter("lyingWithName", request);
            BigDecimal lBDCurrentPostId = new BigDecimal(StringUtility.getParameter("currentPostId", request)); 
            gStrLocId = lStrLocationId.toString();
            inputMap.put("PPONumber", lStrPPONo);
            inputMap.put("LyingWithName", lStrLyingWithName);
            inputMap.put("PostId", lBDCurrentPostId);
            PensionCaseDAO pnsnCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv.getSessionFactory());
            List lLstDesignationDetails = pnsnCaseDAOImpl.getDesignationList(gStrLocId,lLngDocumentId);
            BigDecimal lBDPostId = pnsnCaseDAOImpl.getPostIdOfPresentAuditor(gStrLocId,lLngDocumentId,lStrPPONo);
            inputMap.put("PresentAuditorPostId", lBDPostId);
            inputMap.put("DesignationList", lLstDesignationDetails);
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
        }
        resObj.setResultValue(inputMap);
        resObj.setViewName("TrackSentCasePicture");
        return resObj;
    }

    // updated for new concept
    public ResultObject generateBillCase(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrDcrgFlag = null;
        String lStrCVPFlag = null;
        String lStrPensionFlag = null;
        setSessionInfo(inputMap);
        try
        {
            int totalBills = Integer.parseInt(StringUtility.getParameter("totalBills", request));
            PensionCaseDAOImpl pensionCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv
                    .getSessionFactory());
            String ppoNo = StringUtility.getParameter("ppoNo", request);
            inputMap.put("ppoNo", ppoNo);
            inputMap.put("BillGenerated", "0");
            inputMap = pensionCaseDAOImpl.getBillCaseDtls(inputMap);
            lStrDcrgFlag = StringUtility.getParameter("DCRGFlag", request);
            lStrCVPFlag = StringUtility.getParameter("CVPFlag", request);
            lStrPensionFlag = StringUtility.getParameter("PensionFlag", request);
            if (inputMap.containsKey("BillGenerated"))
            {
                if (inputMap.get("BillGenerated").equals("1"))
                {
                    ArrayList arrBillCaseDtls = new ArrayList();
                    arrBillCaseDtls = (ArrayList) inputMap.get("BillCaseDtlsList");
                    RltPensioncaseBill rltPensioncaseBill = new RltPensioncaseBill();
                    RltPensioncaseBillDAOImpl lObjRltPnsnCaseBillDao = new RltPensioncaseBillDAOImpl(RltPensioncaseBill.class,serv.getSessionFactory());
                    rltPensioncaseBill = (RltPensioncaseBill) arrBillCaseDtls.get(0);
                    Long lRltPensioncaseBillId = 0L;
                    inputMap.put("isUpdating","N" );
                    	for (int i = 0; i < arrBillCaseDtls.size(); i++)
                        {
                            rltPensioncaseBill = new RltPensioncaseBill();
                            rltPensioncaseBill = (RltPensioncaseBill) arrBillCaseDtls.get(i);
                            if (rltPensioncaseBill != null)
                            {
                            	if(request.getParameter("auditFlag") != null && request.getParameter("auditFlag").toString().equalsIgnoreCase("Y"))
                                {
                            		if(rltPensioncaseBill.getTokenNo() != null)
                            		{
                            			rltPensioncaseBill.setStatus("N");
                                		lObjRltPnsnCaseBillDao.update(rltPensioncaseBill);
                                		inputMap.put("isUpdating","Y" );
                            		}
                                }
                            	else
                            	{
                            		if (rltPensioncaseBill.getBillType().equals("CVP"))
                                    {
                                        inputMap.put("CVPDtls", rltPensioncaseBill);
                                    }
                                    if (rltPensioncaseBill.getBillType().equals("DCRG"))
                                    {
                                        inputMap.put("DCRGDtls", rltPensioncaseBill);
                                    }
                                    if (rltPensioncaseBill.getBillType().equals("PENSION"))
                                    {
                                        inputMap.put("PensionDtls", rltPensioncaseBill);
                                    }
                             }
                         }
                    }
                	 if(request.getParameter("auditFlag") != null && request.getParameter("auditFlag").toString().equalsIgnoreCase("Y"))
                     {
                		 Map orgsMap = new HashMap();
                		 if(request.getParameter("DCRGAmt") != null && request.getParameter("DCRGAmt").length() > 0)
                		 {
                			 inputMap.put("DCRGAmt", Double.valueOf(request.getParameter("DCRGAmt").toString()));
                		 }
                		 inputMap.put("DCRGFlag", lStrDcrgFlag);
                     	 if(inputMap.containsKey("isUpdating") && !"Y".equalsIgnoreCase(inputMap.get("isUpdating").toString()))
	                     {
                     		 orgsMap = pensionCaseDAOImpl.getBillCaseDtls(inputMap);
    	                	 arrBillCaseDtls = (ArrayList) orgsMap.get("BillCaseDtlsList");
    	                	 if(arrBillCaseDtls != null && arrBillCaseDtls.size() > 0)
    	                	 {
    	                		for(int i=0;i<arrBillCaseDtls.size();i++)
    	                		{
    	                			rltPensioncaseBill = (RltPensioncaseBill) arrBillCaseDtls.get(i);
    	                			if (rltPensioncaseBill.getBillType().equals("CVP"))
    	                             {
    	                				orgsMap.put("CVPDtls", rltPensioncaseBill);
    	                             }
    	                             if (rltPensioncaseBill.getBillType().equals("DCRG"))
    	                             {
    	                            	 orgsMap.put("DCRGDtls", rltPensioncaseBill);
    	                             }
    	                		}	
	                     	 }
	                	 }
	                	 if(! orgsMap.containsKey("DCRGDtls"))
	                	 {
	                		 if(request.getParameter("CVPFlag") != null && "Y".equalsIgnoreCase(request.getParameter("CVPFlag").toString()))
	                         {
	                             if (totalBills == 1 || totalBills == 3)
	                             {
	                            	 lRltPensioncaseBillId = IFMSCommonServiceImpl.getNextSeqNum("rlt_pensioncase_bill", inputMap);
	                            	 rltPensioncaseBill = new RltPensioncaseBill();
	                            	 rltPensioncaseBill.setCreatedDate(new Date());
	                            	 rltPensioncaseBill.setCreatedPostId(new BigDecimal(gLngPostId));
	                            	 rltPensioncaseBill.setCreatedUserId(new BigDecimal(gLngUserId));
	                            	 rltPensioncaseBill.setLocationCode(gStrLocationCode);
	                                 rltPensioncaseBill.setBillType("CVP");
	                                 rltPensioncaseBill.setPpoNo(ppoNo);
	                                 rltPensioncaseBill.setStatus("Y");
	                                 rltPensioncaseBill.setRltPensioncaseBillId(lRltPensioncaseBillId);
	                                 lObjRltPnsnCaseBillDao.create(rltPensioncaseBill);
	                             }
	                         }
	                	 }
	                	 if(! orgsMap.containsKey("CVPDtls"))
	                	{
	                		if(request.getParameter("DCRGFlag") != null && "Y".equalsIgnoreCase(request.getParameter("DCRGFlag").toString()))
	                        {
	                            if (totalBills == 2 || totalBills == 3)
	                            {
		                           	 rltPensioncaseBill = new RltPensioncaseBill();
		                           	 rltPensioncaseBill.setCreatedDate(new Date());
		                           	 rltPensioncaseBill.setCreatedPostId(new BigDecimal(gLngPostId));
		                           	 rltPensioncaseBill.setCreatedUserId(new BigDecimal(gLngUserId));
		                           	 rltPensioncaseBill.setLocationCode(gStrLocationCode);
		                           	 rltPensioncaseBill.setBillType("DCRG");
		                           	 rltPensioncaseBill.setPpoNo(ppoNo);
		                           	 rltPensioncaseBill.setStatus("Y");
		                           	 lRltPensioncaseBillId = IFMSCommonServiceImpl.getNextSeqNum("rlt_pensioncase_bill", inputMap);
		                           	 rltPensioncaseBill.setRltPensioncaseBillId(lRltPensioncaseBillId);
		                           	 lObjRltPnsnCaseBillDao.create(rltPensioncaseBill);
	                            }
	                        }
	                	}
                     }
                }
                else
                {
                    TrnPensionRqstHdrDAO trnPensionRqstHdrDAOImpl = new TrnPensionRqstHdrDAOImpl(
                            TrnPensionRqstHdr.class, serv.getSessionFactory());
                    RltPensioncaseBillDAOImpl rltPensioncaseBillDAOImpl = new RltPensioncaseBillDAOImpl(
                            RltPensioncaseBill.class, serv.getSessionFactory());
                    RltPensioncaseBill rltPensioncaseBill = new RltPensioncaseBill();
                    rltPensioncaseBill.setCreatedDate(new Date());
                    rltPensioncaseBill.setCreatedPostId(new BigDecimal(gLngPostId));
                    rltPensioncaseBill.setCreatedUserId(new BigDecimal(gLngUserId));
                    rltPensioncaseBill.setLocationCode(gStrLocationCode);
                    rltPensioncaseBill.setBillType("PENSION");
                    rltPensioncaseBill.setPpoNo(ppoNo);
                    rltPensioncaseBill.setStatus("Y");
                    Long lRltPensioncaseBillId = IDGenerateDelegate.getNextId("rlt_pensioncase_bill", inputMap);
                    rltPensioncaseBill.setRltPensioncaseBillId(lRltPensioncaseBillId);
                    rltPensioncaseBillDAOImpl.create(rltPensioncaseBill);
                    if(request.getParameter("CVPFlag") != null && "Y".equalsIgnoreCase(request.getParameter("CVPFlag").toString()))
                    {
                        if (totalBills == 1 || totalBills == 3)
                        {
                            rltPensioncaseBill = new RltPensioncaseBill();
                            rltPensioncaseBill.setCreatedDate(new Date());
                            rltPensioncaseBill.setCreatedPostId(new BigDecimal(gLngPostId));
                            rltPensioncaseBill.setCreatedUserId(new BigDecimal(gLngUserId));
                            rltPensioncaseBill.setLocationCode(gStrLocationCode);
                            rltPensioncaseBill.setBillType("CVP");
                            rltPensioncaseBill.setPpoNo(ppoNo);
                            rltPensioncaseBill.setStatus("Y");
                            lRltPensioncaseBillId = IDGenerateDelegate.getNextId("rlt_pensioncase_bill", inputMap);
                            rltPensioncaseBill.setRltPensioncaseBillId(lRltPensioncaseBillId);
                            rltPensioncaseBillDAOImpl.create(rltPensioncaseBill);
                        }
                    }
                    if(request.getParameter("DCRGFlag") != null && "Y".equalsIgnoreCase(request.getParameter("DCRGFlag").toString()))
                    {
                        if (totalBills == 2 || totalBills == 3)
                        {
                            rltPensioncaseBill = new RltPensioncaseBill();
                            rltPensioncaseBill.setCreatedDate(new Date());
                            rltPensioncaseBill.setCreatedPostId(new BigDecimal(gLngPostId));
                            rltPensioncaseBill.setCreatedUserId(new BigDecimal(gLngUserId));
                            rltPensioncaseBill.setLocationCode(gStrLocationCode);
                            rltPensioncaseBill.setBillType("DCRG");
                            rltPensioncaseBill.setPpoNo(ppoNo);
                            rltPensioncaseBill.setStatus("Y");
                            lRltPensioncaseBillId = IDGenerateDelegate.getNextId("rlt_pensioncase_bill", inputMap);
                            rltPensioncaseBill.setRltPensioncaseBillId(lRltPensioncaseBillId);
                            rltPensioncaseBillDAOImpl.create(rltPensioncaseBill);
                        }
                    }
                   
                    TrnPensionRqstHdr trnPensionRqstHdrUpdated = new TrnPensionRqstHdr();
                    Long caseId = null;
                    caseId = pensionCaseDAOImpl.getCaseIdfromPpoNo(ppoNo, gObjRsrcBndle.getString("CMN.NEW"));
                    if(caseId <= 0)
                    {
                    	caseId = pensionCaseDAOImpl.getCaseIdfromPpoNo(ppoNo, gObjRsrcBndle.getString("STATUS.APPROVED"));
                    }

                    trnPensionRqstHdrUpdated = trnPensionRqstHdrDAOImpl.read(caseId);
                    trnPensionRqstHdrUpdated.setApproveStatus("BILLCRTD");
                    trnPensionRqstHdrUpdated.setAuditorFlag("0");
                    trnPensionRqstHdrUpdated.setCaseOwner(new BigDecimal(SessionHelper.getPostId(inputMap)));
                    trnPensionRqstHdrDAOImpl.update(trnPensionRqstHdrUpdated);
                    inputMap.put("totalBills", totalBills);
                }
            }
            inputMap.put("DCRGFlag", lStrDcrgFlag);
            inputMap.put("CVPFlag", lStrCVPFlag);
           	inputMap.put("PensionFlag",lStrPensionFlag);
            inputMap.put("totalBills", totalBills);
            resObj.setResultValue(inputMap);
            resObj.setViewName("billSelection");
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }


    private void setSessionInfo(Map inputMap)
    {
    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
    	gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(request);
        gStrLocId = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
    }

    public ResultObject getHyrarchyUserOfCase(Map objectArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

        try
        {
            setSessionInfo(objectArgs);
            String lStrSubject = "";
            // Hierarchy Problem
            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
            HttpSession hs = request.getSession();
            ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv
                    .getSessionFactory());
            String lStrPpoNo = (String) request.getParameter("PnsnCaseID");
             PensionCaseDAOImpl pensionCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
           // CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
           // List pensionerDtls = commonPensionDAO.getPensionerDtlsfromPpoNo(lStrPpoNo, gLngLangId);
            String caseBillFlage = StringUtility.getParameter("caseBillFlag", request);
            Long lbddcCaseId = null;
            lbddcCaseId = pensionCaseDAOImpl.getCaseIdfromPpoNo(lStrPpoNo, gObjRsrcBndle.getString("CMN.NEW"));
            if(lbddcCaseId <= 0)
            {
            	 lbddcCaseId = pensionCaseDAOImpl.getCaseIdfromPpoNo(lStrPpoNo, gObjRsrcBndle.getString("STATUS.APPROVED"));
            }
            //if (pensionerDtls != null && pensionerDtls.size() > 0)
            //    lbddcCaseId = (Long) //pensionerDtls.get(0);
            // String lStrHPFlag = "H";
            Long lStrHeirRefId = null;
            long lDocId = Long.parseLong(gObjRsrcBndle.getString("PENSION.CASEDOCUMENTID"));
            String lStrRejFlag = (String) request.getParameter("actionVal");
            if (lStrRejFlag == null || lStrRejFlag.equals(""))
            {
                lStrRejFlag = " ";
            }
            Long postId = SessionHelper.getPostId(objectArgs);
            Long langId = SessionHelper.getLangId(objectArgs);
            Connection conn = serv.getSessionFactory().getCurrentSession().connection();
            WorkFlowVO workFlowVO = new WorkFlowVO();
            workFlowVO.setAppMap(objectArgs);
            workFlowVO.setConnection(conn);
            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
            List resultList1 = null;
            List resultList = null;
            Map resultMap = null;
            Map resultMap1 = null;
            /* code for getting heirarchy ref id - Start */
            // lStrSubject = "Pension Case";
            lStrSubject = gObjRsrcBndle.getString("PENSION.CASESUBJECT");
            resultMap = orgUtil.getHierarchyByPostIDAndDescription(postId.toString(), lStrSubject);
            resultList = (List) resultMap.get("Result");
            lStrHeirRefId = Long.parseLong(resultList.get(0).toString());
            /* code for getting heirarchy ref id - End */
            if (lStrRejFlag.equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVE")))
            {
                resultList1 = orgUtil.getToNodeListFromAlterHir(postId.toString(), lStrHeirRefId, "Approve");
            }
            else if (lStrRejFlag.equalsIgnoreCase(gObjRsrcBndle.getString("CMN.REJECT")))
            {
                resultList1 = orgUtil.getToNodeListFromAlterHir(postId.toString(), lStrHeirRefId, "Reject");
            }
            else
            {
                WfJobMstVO jobMst = orgUtil.getJobMstByJobRefIDAndFromPost(lbddcCaseId.toString(), postId.toString(),
                        lDocId);
                resultMap1 = orgUtil.getUpperPost(postId.toString(), lStrHeirRefId, jobMst.getLevelId());
                resultList1 = (List) resultMap1.get("Result");
            }
            String postString[] = new String[resultList1.size()];
            String levelString[] = new String[resultList1.size()];
            for (int i = 0; i < resultList1.size(); i++)
            {
                Object[] lStrNextPost = (Object[]) resultList1.get(i);
                postString[i] = lStrNextPost[0].toString();
                levelString[i] = lStrNextPost[1].toString();
            }
            List listSameLvlUser = null;
            if (resultList1.size() > 0)
            {
                listSameLvlUser = lObjCmnSrvcDAOImpl.getUserFromPost(postString, levelString, langId);
            }
            if (lStrRejFlag.equals(gObjRsrcBndle.getString("STATUS.APPROVE")))
            {
                TrnPensionRqstHdrDAOImpl trnPensionRqstHdrDAOImpl = new TrnPensionRqstHdrDAOImpl(
                        TrnPensionRqstHdr.class, serv.getSessionFactory());
                TrnPensionRqstHdr trnPensionRqstHdrVO = new TrnPensionRqstHdr();
                trnPensionRqstHdrVO = trnPensionRqstHdrDAOImpl.read(lbddcCaseId);
                String auditorFlag = (String) trnPensionRqstHdrVO.getAuditorFlag();
                if (auditorFlag.equals("0"))
                {
                    BigDecimal sendPostId = (BigDecimal) trnPensionRqstHdrVO.getCaseOwner();
                    String postString1[] = new String[1];
                    String levelString1[] = new String[1];
                    postString1[0] = sendPostId.toString();
                    levelString1[0] = gObjRsrcBndle.getString("WFPENSION.AUDITORLEVEL"); // auditor/FP
                    // level ID
                    listSameLvlUser = lObjCmnSrvcDAOImpl.getUserFromPost(postString1, levelString1, langId);
                }
            }
            objectArgs.put("userList12", listSameLvlUser);
            objectArgs.put("currentUserPost", SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("caseBillFlag", caseBillFlage);
            objectArgs.put("actionVal", lStrRejFlag);
            PhyBillServiceImpl phyBillServiceImpl = new PhyBillServiceImpl();
            if (caseBillFlage.length() > 0 && caseBillFlage.equals("1")) // only
                // for approve of case and bill together
                objRes = phyBillServiceImpl.getHyrarchyUser(objectArgs);
            objRes.setViewName("cmnCaseSelectionFrwd");
            objRes.setResultValue(objectArgs);
        }
        catch (AlternateHierarchyNotfoundException e)
        {
            objRes.setViewName("cmnCaseSelectionFrwd");
            objRes.setResultValue(objectArgs);
        }
        catch (UpperPostNotFoundException e)
        {
            objRes.setViewName("cmnCaseSelectionFrwd");
            objRes.setResultValue(objectArgs);
        }
        catch (DocIdNotFoundException e)
        {
            objRes.setViewName("cmnCaseSelectionFrwd");
            objRes.setResultValue(objectArgs);
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            objRes.setResultValue(null);
            objRes.setThrowable(e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");
        }
        return objRes;
    }


    public ResultObject validatePPONo(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        try
        {
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            String lstrPPONo = StringUtility.getParameter("PPONo", request).trim();
            PensionCaseDAO lObjPensionCaseDAO = new PensionCaseDAOImpl(MstPensionerHdr.class, serv.getSessionFactory());
            boolean lValidFlag = lObjPensionCaseDAO.isValidPPONo(lstrPPONo);
            StringBuilder lStrGrant = new StringBuilder();
            lStrGrant.append(" <PPONO> ");
            lStrGrant.append(lValidFlag);
            lStrGrant.append(" </PPONO> ");
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
            objRes.setViewName("ajaxData");
            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
        }
        return objRes;
    }


    // ResponseXML consists of primary keys of tables used during the
    // transaction
    private StringBuilder getResponseXMLDoc(Map lMapInput)
    {
        MstPensionerHdr lObjMstPensionerHdr = (MstPensionerHdr) lMapInput.get("MstPensionerHdrVO");
        MstPensionerDtls lObjMstPensionerDtls = (MstPensionerDtls) lMapInput.get("MstPensionerDtlsVO");
        TrnPensionRqstHdr lObjTrnPensionRqstHdr = (TrnPensionRqstHdr) lMapInput.get("TrnPensionRqstHdrVO");
        StringBuilder lStrHidPKs = new StringBuilder();
        lStrHidPKs.append("<XMLDOC>");
        lStrHidPKs.append("<MstPensionerHdr>" + lObjMstPensionerHdr.getPensionerCode());
        lStrHidPKs.append("</MstPensionerHdr>");
        lStrHidPKs.append("<MstPensionerDtls>" + lObjMstPensionerDtls.getPensionerDtlsId());
        lStrHidPKs.append("</MstPensionerDtls>");
        lStrHidPKs.append("<TrnPensionRqstHdr>" + lObjTrnPensionRqstHdr.getPensionRequestId());
        lStrHidPKs.append("</TrnPensionRqstHdr>");
        lStrHidPKs.append("<PPONO>" + lObjTrnPensionRqstHdr.getPpoNo());
        lStrHidPKs.append("</PPONO>");
        lStrHidPKs.append("<MESSAGECODE>");
        lStrHidPKs.append("Saved Successfully.");
        lStrHidPKs.append("</MESSAGECODE>");
        lStrHidPKs.append("<LOCATIONCODE>");
        lStrHidPKs.append(lObjMstPensionerHdr.getLocationCode());
        lStrHidPKs.append("</LOCATIONCODE>");
        lStrHidPKs.append("</XMLDOC>");
        return lStrHidPKs;
    }


    public ResultObject auditorCaseDisribute(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        List listBank = null;
        try
        {
            setSessionInfo(inputMap);
            PensionCaseDAOImpl pnsnCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class, serv.getSessionFactory());
            CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            CommonPensionServiceImpl commonPensionServiceImpl = new CommonPensionServiceImpl();
            Map displayTag = IFMSCommonServiceImpl.getDisplayPara(request);
            CommonPensionService lObjCommonPensionService = new CommonPensionServiceImpl();
            String strCaseId = commonPensionServiceImpl.getMyCases(inputMap);
            inputMap.put("caseIdString", strCaseId);
            listBank = CommonPensionDAO.getAllBank(gLngLangId,SessionHelper.getLocationCode(inputMap));
            List caseAuditorList = pnsnCaseDAOImpl.getCaseAuditorDtls(inputMap);
            int lIntPageCount = pnsnCaseDAOImpl.getCaseAuditorDtlsCount(inputMap);
            inputMap.put("listBank", listBank);
            if(caseAuditorList != null && caseAuditorList.size()>0)
            {
                inputMap.put("CaseAuditorList", caseAuditorList);
            }
            inputMap.put("totalRecords", caseAuditorList.size());
            resObj.setResultValue(inputMap);
            resObj.setViewName("caseAudtior");
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }
        return resObj;
    }
    //Added chandu for Audit trail approved changes 
    public void setTrnpensionRqstHdrCaseStatus(String ppoNo,Map inputMap) throws Exception
    {
    	 ServiceLocator serv = null;
         try
         {
             serv = (ServiceLocator) inputMap.get("serviceLocator");
             String lStrRejFlag = (String) inputMap.get("actionVal");
             //Added By chandu for Updating TrnRqstHdr CaseStatus to appove Starts
             PensionCaseDAOImpl pensionCaseDAOImpl = new PensionCaseDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
             Long lLngPkAprvd  = pensionCaseDAOImpl.getCaseIdfromPpoNo(ppoNo,gObjRsrcBndle.getString("STATUS.APPROVED"));
             Long lLngPkNew = pensionCaseDAOImpl.getCaseIdfromPpoNo(ppoNo,gObjRsrcBndle.getString("CMN.NEW"));
             TrnPensionRqstHdrDAO lObjDao = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, serv.getSessionFactory());
             TrnPensionRqstHdr lObjHdr = new TrnPensionRqstHdr();
             if(lLngPkNew != null && lLngPkNew >0)
             {
                 if(lLngPkAprvd != null && lLngPkAprvd >0)
                 {
                     if(lStrRejFlag != null && ! lStrRejFlag.equalsIgnoreCase("reject"))
                     {
                         lObjHdr = lObjDao.read(lLngPkAprvd);
                         lObjHdr.setCaseStatus(gObjRsrcBndle.getString("STATUS.CHANGE"));
                         lObjDao.update(lObjHdr);
                     }
                 }
                 lObjHdr = new TrnPensionRqstHdr();
                 lObjHdr = lObjDao.read(lLngPkNew);
                 if(lStrRejFlag != null && lStrRejFlag.equalsIgnoreCase("reject"))
                 {
                     lObjHdr.setCaseStatus(gObjRsrcBndle.getString("CMN.REJECT"));
                     lObjHdr.setApproveStatus(gObjRsrcBndle.getString("CMN.REJECT"));
                 }
                 else  if(lStrRejFlag != null && lStrRejFlag.equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVE")))
                 {
                     lObjHdr.setCaseStatus(gObjRsrcBndle.getString("STATUS.APPROVED"));
                     lObjHdr.setApproveStatus(gObjRsrcBndle.getString("STATUS.APPROVED"));
                 }
                 lObjDao.update(lObjHdr);
             }
            //Added By chandu for updating TrnRqstHdr CaseStatus to appove End
             if(lObjHdr != null && lObjHdr.getPensionerCode() != null)
             {
                 //Added By chandu for updating MstPensionerHdr CaseStatus to appove Start
                 MstPensionerHdrDAOImpl lObjMstPnsnrHdr = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
                 Long lLngPnsnrIdNew = 0L;
                 Long lLngPnsnrIdAprvd = 0L;
                 lLngPnsnrIdNew =  lObjMstPnsnrHdr.getPensionerIdfromPensnrCode(lObjHdr.getPensionerCode(),gObjRsrcBndle.getString("CMN.NEW"));
                 lLngPnsnrIdAprvd = lObjMstPnsnrHdr.getPensionerIdfromPensnrCode(lObjHdr.getPensionerCode(),gObjRsrcBndle.getString("STATUS.APPROVED"));
                 MstPensionerHdr lObjMstHdr = new MstPensionerHdr();
                 if(lLngPnsnrIdNew != null && lLngPnsnrIdNew > 0)
                 {
                     if(lLngPnsnrIdAprvd != null && lLngPnsnrIdAprvd > 0)
                     {
                         if(lStrRejFlag != null && ! lStrRejFlag.equalsIgnoreCase("reject"))
                         {
                             lObjMstHdr = lObjMstPnsnrHdr.read(lLngPnsnrIdAprvd);
                             lObjMstHdr.setCaseStatus(gObjRsrcBndle.getString("STATUS.CHANGE"));
                             lObjMstPnsnrHdr.update(lObjMstHdr);
                         }
                     }
                     lObjMstHdr = lObjMstPnsnrHdr.read(lLngPnsnrIdNew);
                     if(lStrRejFlag != null && lStrRejFlag.equalsIgnoreCase("reject"))
                     {
                         lObjMstHdr.setCaseStatus(gObjRsrcBndle.getString("CMN.REJECT"));
                     }
                     else  if(lStrRejFlag != null && lStrRejFlag.equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVE")))
                     {
                         lObjMstHdr.setCaseStatus(gObjRsrcBndle.getString("STATUS.APPROVED"));
                     }
                   lObjMstPnsnrHdr.update(lObjMstHdr);
                 }
                 //AddedBy Chandu for Updating MstPensionerHdr CaseStatus to appove End
                //AddedBy Chandu for Updating MstPensionerDtls CaseStatus to appove Start
                 MstPensionerDtlsDAOImpl lObjMstPnsnrDtls = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class, serv.getSessionFactory());
                 Long lLngPnsnrDtlsIdNew = 0L;
                 Long lLngPnsnrDtlsIdOld = 0L;
                 lLngPnsnrDtlsIdNew = lObjMstPnsnrDtls.getPnsionerDtlsIdFromPensionerCode(lObjHdr.getPensionerCode(), gObjRsrcBndle.getString("CMN.NEW"));
                 lLngPnsnrDtlsIdOld = lObjMstPnsnrDtls.getPnsionerDtlsIdFromPensionerCode(lObjHdr.getPensionerCode(), gObjRsrcBndle.getString("STATUS.APPROVED"));
                 MstPensionerDtls lObjMstPnsnrDtlsVo = new MstPensionerDtls();
                 if(lLngPnsnrDtlsIdNew != null && lLngPnsnrDtlsIdNew > 0)
                 {
                     if(lLngPnsnrDtlsIdOld != null && lLngPnsnrDtlsIdOld > 0)
                     {
                         if(lStrRejFlag != null && ! lStrRejFlag.equalsIgnoreCase("reject"))
                         {
                             lObjMstPnsnrDtlsVo = lObjMstPnsnrDtls.read(lLngPnsnrDtlsIdOld);
                             lObjMstPnsnrDtlsVo.setCaseStatus(gObjRsrcBndle.getString("STATUS.CHANGE"));
                             lObjMstPnsnrDtls.update(lObjMstPnsnrDtlsVo);
                         }
                     }
                     lObjMstPnsnrDtlsVo = lObjMstPnsnrDtls.read(lLngPnsnrDtlsIdNew);
                     if(lStrRejFlag != null && lStrRejFlag.equalsIgnoreCase("reject"))
                     {
                         lObjMstPnsnrDtlsVo.setCaseStatus(gObjRsrcBndle.getString("CMN.REJECT"));
                     }
                     else  if(lStrRejFlag != null && lStrRejFlag.equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVE")))
                     {
                         lObjMstPnsnrDtlsVo.setCaseStatus(gObjRsrcBndle.getString("STATUS.APPROVED"));
                     }
                     lObjMstPnsnrDtls.update(lObjMstPnsnrDtlsVo);
                 }
                 // AddedBy Chandu for Updating MstPensionerDtls CaseStatus to appove Start
             }
         }
         catch(Exception e)
         {
        	 gLogger.error("Error is"+e,e);
        	 throw e;
         }
    }

    public void saveMstPensionerDtls(MstPensionerDtls mstPensionerDtlsVO, Map inputMap) throws Exception
    {
        ServiceLocator serv = null;
        Long mstPensionerDtlsId = null;
        Long mstPensionerHdrId = null;
        try
        {
            serv = (ServiceLocator) inputMap.get("serviceLocator");

            MstPensionerDtlsDAOImpl lObjPensionerDtlsDao = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class, serv
                    .getSessionFactory());
            if (mstPensionerDtlsVO.getPensionerCode() != null)
            {
                lObjPensionerDtlsDao.update(mstPensionerDtlsVO);
            }
            else
            {
                mstPensionerDtlsId = IFMSCommonServiceImpl.getNextSeqNum("mst_pensioner_dtls", inputMap);
                mstPensionerHdrId = Long.valueOf((String) inputMap.get("mstPensionerHdrId"));
                mstPensionerDtlsVO.setPensionerCode(mstPensionerHdrId.toString());
                mstPensionerDtlsVO.setPensionerDtlsId(mstPensionerDtlsId);
                lObjPensionerDtlsDao.create(mstPensionerDtlsVO);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is " + e, e);
        }
    }


    public void saveMstPensionerFamilyDtls(MstPensionerFamilyDtls mstPensionerFMDtlsVO, Map inputMap) throws Exception
    {
        Long mstPensionerFMDtlsID = null;
        ServiceLocator serv = null;
        Long mstPensionerHdrId = null;
        try
        {
            mstPensionerFMDtlsID = IDGenerateDelegate.getNextId("mst_pensioner_family_dtls", inputMap);
            serv = (ServiceLocator) inputMap.get("serviceLocator");
            mstPensionerHdrId = Long.valueOf((String) inputMap.get("mstPensionerHdrId"));
            MstPensionerFamilyDtlsDAOImpl mstPnsnrFMDtlsDAOImpl = new MstPensionerFamilyDtlsDAOImpl(
                    MstPensionerFamilyDtls.class, serv.getSessionFactory());
            if (mstPensionerFMDtlsID != null)
            {
                mstPensionerFMDtlsVO.setFamilyMemberId(Long.valueOf(mstPensionerFMDtlsID));
                mstPensionerFMDtlsVO.setPensionerCode(mstPensionerHdrId.toString());
                mstPnsnrFMDtlsDAOImpl.create(mstPensionerFMDtlsVO);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is :" + e, e);
            throw e;
        }
    }


    public void saveMstPensionerNomineeDtls(MstPensionerNomineeDtls nomineeDtlsVo, Map inputMap) throws Exception
    {
        ServiceLocator serv = null;
        Long mstPensionerHdrId = null;
        Long lLngNomineeDtlsId = null;
        try
        {
            serv = (ServiceLocator) inputMap.get("serviceLocator");
            mstPensionerHdrId = Long.valueOf((String) inputMap.get("mstPensionerHdrId"));
            lLngNomineeDtlsId = IDGenerateDelegate.getNextId("mst_pensioner_nominee_dtls", inputMap);
            MstPensionerNomineeDtlsDAOImpl PnsnrNomineDtlsDAOImpl = new MstPensionerNomineeDtlsDAOImpl(
                    MstPensionerNomineeDtls.class, serv.getSessionFactory());
            nomineeDtlsVo.setPensionerCode(mstPensionerHdrId.toString());
            if (lLngNomineeDtlsId != null)
            {
                nomineeDtlsVo.setNomineeId(lLngNomineeDtlsId);
                PnsnrNomineDtlsDAOImpl.create(nomineeDtlsVo);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is :" + e, e);
            throw e;
        }
    }


    public void saveMstPensionerRvsnDtls(TrnPensionerRivisionDtls trnPensionRivisionDtlVO, Map inputMap)
            throws Exception
    {
        ServiceLocator serv = null;
        Long mstPensionerHdrId = null;
        Long mstRvsnDtlsId = null;
        try
        {
            serv = (ServiceLocator) inputMap.get("serviceLocator");
            TrnPensionerRivisionDtlsDAO lObjDao = new TrnPensionerRivisionDtlsDAOImpl(TrnPensionerRivisionDtls.class,
                    serv.getSessionFactory());

            if (trnPensionRivisionDtlVO.getPensionerCode() != null)
            {
                lObjDao.update(trnPensionRivisionDtlVO);
            }
            else
            {
                mstRvsnDtlsId = IFMSCommonServiceImpl.getNextSeqNum("trn_pensioner_rivision_dtls", inputMap);
                mstPensionerHdrId = Long.valueOf((String) inputMap.get("mstPensionerHdrId"));
                trnPensionRivisionDtlVO.setPensionRequestId(Long.valueOf(inputMap.get("trnPensionRqstHdrID").toString()));
                trnPensionRivisionDtlVO.setPensionerCode(mstPensionerHdrId.toString());
                trnPensionRivisionDtlVO.setRivisionDtlsId(mstRvsnDtlsId);
                lObjDao.create(trnPensionRivisionDtlVO);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is :" + e, e);
            throw e;
        }
    }


    public void saveTrnPensionerSeenDtls(TrnPensionerSeenDtls lObjPensionerSeenDtls, Map inputMap) throws Exception
    {
        ServiceLocator serv = null;
        Long mstPensionerHdrId = null;
        Long lLngseenDtlsId = null;
        try
        {
            serv = (ServiceLocator) inputMap.get("serviceLocator");
            TrnPensionerSeenDtlsDao lObjDao = new TrnPensionerSeenDtlsDaoImpl(TrnPensionerSeenDtls.class, serv
                    .getSessionFactory());

            if (lObjPensionerSeenDtls.getPensionerCode() != null)
            {
                lObjDao.update(lObjPensionerSeenDtls);
            }
            else
            {
                lLngseenDtlsId = IFMSCommonServiceImpl.getNextSeqNum("trn_pensioner_rivision_dtls", inputMap);
                mstPensionerHdrId = Long.valueOf((String) inputMap.get("mstPensionerHdrId"));
                lObjPensionerSeenDtls.setPensionerCode(mstPensionerHdrId.toString());
                lObjPensionerSeenDtls.setSeenDtlsId(lLngseenDtlsId);
                lObjDao.create(lObjPensionerSeenDtls);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is :" + e, e);
            throw e;
        }
    }


    public Long savePensionerRqstHdrDtls(TrnPensionRqstHdr trnPensionRqstHdrVO, Map inputMap) throws Exception
    {
        ServiceLocator serv = null;
        Long mstPensionerHdrId = null;
        Long rqstHdrId = null;
        try
        {
            serv = (ServiceLocator) inputMap.get("serviceLocator");
            TrnPensionRqstHdrDAO lObjDao = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, serv
                    .getSessionFactory());

            if (trnPensionRqstHdrVO.getPensionerCode() != null)
            {
                lObjDao.update(trnPensionRqstHdrVO);
                rqstHdrId = Long.valueOf(trnPensionRqstHdrVO.getPensionRequestId().toString());
            }
            else
            {
                rqstHdrId = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_rqst_hdr", inputMap);
                mstPensionerHdrId = Long.valueOf((String) inputMap.get("mstPensionerHdrId"));
                trnPensionRqstHdrVO.setPensionerCode(mstPensionerHdrId.toString());
                trnPensionRqstHdrVO.setPensionRequestId(rqstHdrId);
                lObjDao.create(trnPensionRqstHdrVO);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is :" + e, e);
            throw e;
        }
        return rqstHdrId;
    }


    public void savePrvosionalPensionDtls(TrnPrvosionalPensionDtls lObjVo, Map inputMap) throws Exception
    {
        ServiceLocator serv = null;
        Long mstPensionerHdrId = null;
        Long rqstHdrId = null;
        try
        {
            serv = (ServiceLocator) inputMap.get("serviceLocator");
            TrnPrvosionalPensionDtlsDao lObjTrnPrvosionalPensionDtlsDao = new TrnPrvosionalPensionDtlsDaoImpl(
                    TrnPrvosionalPensionDtls.class, serv.getSessionFactory());
            if (lObjVo != null)
            {
                if (lObjVo.getPrvosionalPensionDtlsId() != null)
                {
                    lObjTrnPrvosionalPensionDtlsDao.update(lObjVo);
                }
                else
                {
                    rqstHdrId = IFMSCommonServiceImpl.getNextSeqNum("trn_prvosional_pension_dtls", inputMap);
                    mstPensionerHdrId = Long.valueOf((String) inputMap.get("mstPensionerHdrId"));
                    lObjVo.setPrvosionalPensionDtlsId(rqstHdrId);
                    lObjVo.setPensionerCode(mstPensionerHdrId.toString());
                    lObjVo.setPensionRequestId(BigDecimal.valueOf(mstPensionerHdrId));
                    lObjTrnPrvosionalPensionDtlsDao.create(lObjVo);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is" + e, e);
            throw (e);
        }
    }
    public ResultObject showAuditDiff(Map inputMap)
    {
    	 ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
    	 ServiceLocator serv = null;
    	 String lStrDistNew = null;
    	 String lStrDistAprvd = null;
    	 String lStrStateNew = null;
    	 String lStrStateAprvd = null;
    	 String lStrDesigNew = null;
    	 String lStrDesigAprvd = null;
    	 String lStrDeptNew = null;
    	 String lStrDeptAprvd = null;
    	 String lStrHodNew = null;
    	 String lStrHodAprvd = null;
    	 String lStrclassNew = null;
    	 String lStrclassAprvd = null;
         try
         {
        	 HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        	 serv = (ServiceLocator) inputMap.get("serviceLocator");
        	 String ppoNo = StringUtility.getParameter("ppoNo", request);
        	 CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
        	 List<TrnPensionRqstHdr> lLstHdrVos = CommonPensionDAO.getPensionerDtlsDiffFromPpoNo(ppoNo);
        	 TrnPensionRqstHdr lObj1 = null;
        	 MstPensionerHdr lObjMstHdr = null;
        	 TrnPensionRqstHdr lObjApproved = new TrnPensionRqstHdr();
        	 TrnPensionRqstHdr lObjNEW = new TrnPensionRqstHdr();
        	 if(lLstHdrVos != null)
        	 {
        		 Iterator itr = lLstHdrVos.iterator();
        		 while(itr.hasNext())
        		 {
        			 lObj1 = (TrnPensionRqstHdr) itr.next();
        			 if(lObj1.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVED")))
        			 {
        				 lObjApproved = lObj1;
        			 }
        			 else if(lObj1.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("CMN.NEW")))
        			 {
        				 lObjNEW = lObj1;
        			 }
        		 }
        	 }
        	 MstPensionerHdrDAOImpl lObjMstHdrDao = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
        	 List<MstPensionerHdr> lLstMstPnsnrHdr  = lObjMstHdrDao.getMstPensionerHdrDiff(lObjApproved.getPensionerCode());
        	 MstPensionerHdr lObjNewMstHdr = new MstPensionerHdr();
        	 MstPensionerHdr lObjAprvdMstHdr = new MstPensionerHdr();
        	 if(lLstMstPnsnrHdr != null)
        	 {
        		 Iterator itr = lLstMstPnsnrHdr.iterator();
        		 while(itr.hasNext())
        		 {
        			 lObjMstHdr = (MstPensionerHdr) itr.next();
        			 if(lObjMstHdr.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVED")))
        			 {
        				 lObjAprvdMstHdr = lObjMstHdr;
        			 }
        			 else if(lObjMstHdr.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("CMN.NEW")))
        			 {
        				 lObjNewMstHdr = lObjMstHdr;
        			 }
        		 }
        	 }
        	 MstPensionerDtlsDAOImpl lObjMstDtlsDao = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class,serv.getSessionFactory());
        	 List<MstPensionerDtls> lLstMstPnsnrDtls = lObjMstDtlsDao.getMstPensionerDtlsDiff(lObjApproved.getPensionerCode());
        	 MstPensionerDtls lObjMstDtls = null;
        	 MstPensionerDtls lObjMstDtlsAprvd = null;
        	 MstPensionerDtls lObjMstDtlsNew = null;
        	 if(lLstMstPnsnrDtls != null)
        	 {
        		 Iterator itr = lLstMstPnsnrDtls.iterator();
        		 while(itr.hasNext())
        		 {
        			 lObjMstDtls = (MstPensionerDtls) itr.next();
        			 if(lObjMstDtls.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("STATUS.APPROVED")))
        			 {
        				 lObjMstDtlsAprvd = lObjMstDtls;
        			 }
        			 else if(lObjMstDtls.getCaseStatus().equalsIgnoreCase(gObjRsrcBndle.getString("CMN.NEW")))
        			 {
        				 lObjMstDtlsNew = lObjMstDtls;
        			 }
        		 }
        	 }
        	 List lLstRes = new ArrayList();
        	 if(lObjNewMstHdr != null && lObjNewMstHdr.getDistrictCode() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("districtName");
        		 lLstParameters.add("CmnDistrictMst");
        		 lLstParameters.add("districtId");
        		 lLstParameters.add(lObjNewMstHdr.getDistrictCode());
        		 lLstParameters.add("cmnLanguageMst.langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap).toString());
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrDistNew = lLstRes.get(0).toString();
        		 }
        	 }
        	 if(lObjAprvdMstHdr != null && lObjAprvdMstHdr.getDistrictCode() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("districtName");
        		 lLstParameters.add("CmnDistrictMst");
        		 lLstParameters.add("districtId");
        		 lLstParameters.add(lObjAprvdMstHdr.getDistrictCode());
        		 lLstParameters.add("cmnLanguageMst.langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap));
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrDistAprvd = lLstRes.get(0).toString();
        		 }
        	 }
        	 lLstRes = new ArrayList();
        	 if(lObjNewMstHdr != null && lObjNewMstHdr.getStateCode() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("stateName");
        		 lLstParameters.add("CmnStateMst");
        		 lLstParameters.add("stateId");
        		 lLstParameters.add(lObjNewMstHdr.getStateCode());
        		 lLstParameters.add("cmnLanguageMst.langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap).toString());
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrStateNew = lLstRes.get(0).toString();
        		 }
        	 }
        	 if(lObjAprvdMstHdr != null && lObjAprvdMstHdr.getStateCode() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("stateName");
        		 lLstParameters.add("CmnStateMst");
        		 lLstParameters.add("stateId");
        		 lLstParameters.add(lObjAprvdMstHdr.getStateCode());
        		 lLstParameters.add("cmnLanguageMst.langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap));
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrStateAprvd = lLstRes.get(0).toString();
        		 }
        	 }
        	 lLstRes = new ArrayList();
        	 if(lObjNewMstHdr != null && lObjNewMstHdr.getDesignation() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("dsgnName");
        		 lLstParameters.add("OrgDesignationMst");
        		 lLstParameters.add("dsgnCode");
        		 lLstParameters.add("'"+lObjNewMstHdr.getDesignation()+"'");
        		 lLstParameters.add("cmnLanguageMst.langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap).toString());
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrDesigNew = lLstRes.get(0).toString();
        		 }
        	 }
        	 if(lObjAprvdMstHdr != null && lObjAprvdMstHdr.getDesignation() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("dsgnName");
        		 lLstParameters.add("OrgDesignationMst");
        		 lLstParameters.add("dsgnCode");
        		 lLstParameters.add("'"+lObjAprvdMstHdr.getDesignation()+"'");
        		 lLstParameters.add("cmnLanguageMst.langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap));
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrDesigAprvd = lLstRes.get(0).toString();
        		 }
        	 }
        	 
        	 lLstRes = new ArrayList();
        	 if(lObjNewMstHdr != null && lObjNewMstHdr.getDesignation() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("deptName");
        		 lLstParameters.add("MstPensionDept");
        		 lLstParameters.add("deptIdentifier = 'DEPT' AND deptId");
        		 lLstParameters.add(lObjNewMstHdr.getDeptCode());
        		 lLstParameters.add("langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap).toString());
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrDeptNew = lLstRes.get(0).toString();
        		 }
        	 }
        	 if(lObjAprvdMstHdr != null && lObjAprvdMstHdr.getStateCode() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("deptName");
        		 lLstParameters.add("MstPensionDept");
        		 lLstParameters.add("deptIdentifier = 'DEPT' AND deptId");
        		 lLstParameters.add(lObjAprvdMstHdr.getDeptCode());
        		 lLstParameters.add("langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap));
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrDeptAprvd = lLstRes.get(0).toString();
        		 }
        	 }
        	 
        	 lLstRes = new ArrayList();
        	 if(lObjNewMstHdr != null && lObjNewMstHdr.getHodCode() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("deptName");
        		 lLstParameters.add("MstPensionDept");
        		 lLstParameters.add("deptIdentifier = 'HOD' AND deptId");
        		 lLstParameters.add(lObjNewMstHdr.getHodCode());
        		 lLstParameters.add("langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap).toString());
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrHodNew = lLstRes.get(0).toString();
        		 }
        	 }
        	 if(lObjAprvdMstHdr != null && lObjAprvdMstHdr.getHodCode() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("deptName");
        		 lLstParameters.add("MstPensionDept");
        		 lLstParameters.add("deptIdentifier = 'HOD'AND deptId");
        		 lLstParameters.add(lObjAprvdMstHdr.getHodCode());
        		 lLstParameters.add("langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap));
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrHodAprvd = lLstRes.get(0).toString();
        		 }
        	 }
        	 
        	/* lLstRes = new ArrayList();
        	 if(lObjNewMstHdr != null && lObjNewMstHdr.getClassType() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("lookupName");
        		 lLstParameters.add("CmnLookupMst");
        		 lLstParameters.add("lookupId ");
        		 lLstParameters.add(lObjNewMstHdr.getClassType());
        		 lLstParameters.add("cmnLanguageMst.langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap).toString());
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrclassNew = lLstRes.get(0).toString();
        		 }
        	 }
        	 if(lObjAprvdMstHdr != null && lObjAprvdMstHdr.getClassType() != null)
        	 {
        		 List lLstParameters = new ArrayList();
        		 lLstParameters.add("lookupName");
        		 lLstParameters.add("CmnLookupMst");
        		 lLstParameters.add("lookupId ");
        		 lLstParameters.add(lObjAprvdMstHdr.getClassType());
        		 lLstParameters.add("cmnLanguageMst.langId");
        		 lLstParameters.add(SessionHelper.getLangId(inputMap));
        		 lLstRes = CommonPensionDAO.getDescFromAnyTablesByCode(lLstParameters);
        		 if(lLstRes != null && lLstRes.size()>0)
        		 {
        			 lStrclassAprvd = lLstRes.get(0).toString();
        		 }
        	 }*/
        	 inputMap.put("NewDist",lStrDistNew);
        	 inputMap.put("AprovedDist",lStrDistAprvd);
        	 inputMap.put("NewState",lStrStateNew);
        	 inputMap.put("AprovedState",lStrStateAprvd);
        	 inputMap.put("NewDesig",lStrDesigNew);
        	 inputMap.put("AprovedDesig",lStrDesigAprvd);
        	 inputMap.put("AprovedDept",lStrDeptAprvd);
        	 inputMap.put("NewDept",lStrDeptNew);
        	 inputMap.put("NewHOD",lStrHodNew);
        	 inputMap.put("AprovedHOD",lStrHodAprvd);
        	 //inputMap.put("NewClass",lStrclassNew);
        	 //inputMap.put("AprovedClass",lStrclassAprvd);
        	 inputMap.put("AprovedDtlsObj", lObjMstDtlsAprvd);
        	 inputMap.put("NewDtlsObj", lObjMstDtlsNew);
        	 inputMap.put("AproveHdrObj", lObjApproved);
        	 inputMap.put("NewHdrObj", lObjNEW);
        	 inputMap.put("AprovedMstHdr",lObjAprvdMstHdr);
        	 inputMap.put("NewMstHdr",lObjNewMstHdr);
        	 resObj.setResultValue(inputMap);
             resObj.setViewName("getAuditPopUp");
         }
         catch(Exception e)
         {
        	  gLogger.error("Error is;" + e, e);
              resObj.setResultValue(null);
              resObj.setThrowable(e);
              resObj.setResultCode(ErrorConstants.ERROR);
              resObj.setViewName("errorPage");
         }
    	
    	return resObj;
    }
    public void saveROPDtls(Map orgsMap,String lStrPPONo)
    {
    	 ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
         try
         {
             ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
             List<TrnPnsncaseRopRlt> lLstRopVo = new ArrayList<TrnPnsncaseRopRlt>();
             TrnPnsncaseRopRltDAO lObjROPDao = new TrnPnsncaseRopRltDAO(TrnPnsncaseRopRlt.class,serv.getSessionFactory());
             List lLStROPPks = lObjROPDao.getROPDtlsFromPpoNo(lStrPPONo);
             for(int i=0;i<lLStROPPks.size();i++)
             {
            	 TrnPnsncaseRopRlt lObjVo = new TrnPnsncaseRopRlt();
            	 Long lLngRopId = (Long) lLStROPPks.get(i);
            	 lObjVo = lObjROPDao.read(lLngRopId);
            	 lObjROPDao.delete(lObjVo);
             }
             lLstRopVo = (List<TrnPnsncaseRopRlt>) orgsMap.get("ROPList");
             for(int i=0;i<lLstRopVo.size();i++)
             {
            	 TrnPnsncaseRopRlt lObjVo = lLstRopVo.get(i);
            	 lObjVo.setPpoNo(lStrPPONo);
            	 insertRopDtls(lObjVo,orgsMap);
             }
            
         }
         catch(Exception e)
         {
        	 gLogger.error("Error is :" + e, e);
         }
    }
	public void insertRopDtls(TrnPnsncaseRopRlt PnsncaseRopRltVO, Map inputMap) throws Exception
	{
	    Long RopDtlsId = null;
	    ServiceLocator serv = null;
	    try
	    {
	    	RopDtlsId = IDGenerateDelegate.getNextId("trn_pnsncase_rop_rlt", inputMap);
	        serv = (ServiceLocator) inputMap.get("serviceLocator");
	        TrnPnsncaseRopRltDAO mstPnsnrFMDtlsDAOImpl = new TrnPnsncaseRopRltDAO(
	        		TrnPnsncaseRopRlt.class, serv.getSessionFactory());
	        if (RopDtlsId != null)
	        {
	        	PnsncaseRopRltVO.setPnsncaseRopRltId(RopDtlsId);
	        	mstPnsnrFMDtlsDAOImpl.create(PnsncaseRopRltVO);
	        }
	    }
	    catch (Exception e)
	    {
	        gLogger.error("Error is :" + e, e);
	        throw e;
	    }
	}
	 private WorkFlowVO getDefaultWorkFlowVO(Map inputMap)
	    {
	    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
	        Connection conn = serv.getSessionFactory().getCurrentSession().connection();

	        WorkFlowVO workFlowVO = new WorkFlowVO();
	        workFlowVO.setAppMap(inputMap);
	        workFlowVO.setCrtEmpId(gLngUserId.toString());
	        workFlowVO.setCrtPost(gLngPostId.toString());
	        workFlowVO.setFromPost(gLngPostId.toString());
	        workFlowVO.setCrtUsr(gLngUserId.toString());
	        workFlowVO.setConnection(conn);
	        workFlowVO.setLocID(gStrLocId);
	        workFlowVO.setDbId(gLngDBId);

	        return workFlowVO;
	    }
}