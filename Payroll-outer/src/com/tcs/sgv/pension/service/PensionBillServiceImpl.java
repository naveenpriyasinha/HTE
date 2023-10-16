package com.tcs.sgv.pension.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.audit.dao.AuditorObjectionDAO;
import com.tcs.sgv.billproc.audit.dao.AuditorObjectionDAOImpl;
import com.tcs.sgv.billproc.audit.service.AuditorObjectionServiceImpl;
import com.tcs.sgv.billproc.common.service.BillProcConstants;
import com.tcs.sgv.billproc.common.valueobject.TrnShowBill;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAO;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.billproc.report.ReportQueryDAOImpl;
import com.tcs.sgv.billproc.report.ReportServiceImpl;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.BillMovementDAOImpl;
import com.tcs.sgv.common.dao.BillVitoDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.RltBillPartyDAO;
import com.tcs.sgv.common.dao.RltBillPartyDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BillMovementServiceImpl;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.BillVitoRegister;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.common.valueobject.RltBillObjection;
import com.tcs.sgv.common.valueobject.RltBillParty;
import com.tcs.sgv.common.valueobject.RltLevelStatus;
import com.tcs.sgv.common.valueobject.SgvoDeptMst;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.OnlineBillDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnOnlinebillEmpDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnOnlinebillEmpDAOImpl;
import com.tcs.sgv.onlinebillprep.service.OnlineBillEDPService;
import com.tcs.sgv.onlinebillprep.service.OnlineBillEDPServiceImpl;
import com.tcs.sgv.onlinebillprep.service.OnlineBillServiceImpl;
import com.tcs.sgv.onlinebillprep.util.ConfigOnlineBillUtil;
import com.tcs.sgv.onlinebillprep.valueobject.ConfigOnlineBill;
import com.tcs.sgv.onlinebillprep.valueobject.TrnOnlinebillEmp;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.PensionBillDAO;
import com.tcs.sgv.pension.dao.PensionBillDAOImpl;
import com.tcs.sgv.pension.dao.PensionCaseDAOImpl;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAO;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.SavedPensionBillVO;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.appwfinterface.WorkFlowInterfaceImpl;
import com.tcs.sgv.wf.exception.AlternateHierarchyNotfoundException;
import com.tcs.sgv.wf.exception.UpperPostNotFoundException;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WfDocMvmntMstVO;
import com.tcs.sgv.wf.valueobject.WfJobMstVO;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;
 

public class PensionBillServiceImpl extends ServiceImpl implements PensionBillService
{
	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	/* Global Variable for Location Id */
    private String gStrLocId = null;

	/* Global Variable for PostId */
    private Long gLngPostId = null;

    /* Global Variable for UserId */
    private Long gLngUserId = null;

    /* Global Variable for LangId */
    private Long gLngLangId = null;

    /* Global Variable for DB Id */
    private Long gLngDBId = null;
    
    /* Global Variable for Location Code */
    private Long gLocId = null;
    
    /* Global Variable for current Date */
    private Date gDtCurDate = null;
    
    /* Global Variable for ResourceBundle */
    private static ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    
	public ResultObject createPensionSpecificBill(Map inputMap){
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ResultObject lResultObj = null;
        Map lMap = null;
        List rcptObjHds = null;
        List expObjHds = null;
        List expEdpList = null;     
        
		try
        {
        	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            
            setSessionInfo(inputMap);
            ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
            String[] lStrRqstID = new String[1];
            lStrRqstID[0] = "-1";
            String[] lStrEmpType = new String[1];
            lStrEmpType[0] = "-1";
            CmnLookupMstDAO cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

            String lStrFund = bundleConst.getString("Lookup.Fund");
            String lStrClassOfExp = bundleConst.getString("Lookup.ClassOfExpenditure");
            String lStrTcBill = bundleConst.getString("Lookup.TCBillType");
            String lStrBudgetType = bundleConst.getString("Lookup.BudgetType");
            String lStrGONGO = bundleConst.getString("Lookup.GO_NGO");
            
            List lListFund = cmnDAO.getAllChildrenByLookUpNameAndLang(lStrFund, gLngLangId);
			List lListClassOfExp = cmnDAO.getAllChildrenByLookUpNameAndLang(lStrClassOfExp, gLngLangId);
			List lEmpType = cmnDAO.getAllChildrenByLookUpNameAndLang(lStrGONGO, gLngLangId);
			List lBillType = cmnDAO.getAllChildrenByLookUpNameAndLang(lStrTcBill, gLngLangId);
						
			List lBudjetType = cmnDAO.getAllChildrenByLookUpNameAndLang(lStrBudgetType, gLngLangId);
			Object[] lObjBudjetTypeArray = null;
			if(lBudjetType != null && !lBudjetType.isEmpty())
			{
				Object[] lObjTemp = lBudjetType.toArray();
				lObjBudjetTypeArray = new Object[lObjTemp.length];
				for(Object lObj: lObjTemp)
				{
					CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
					lObjBudjetTypeArray[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO; 
				}
			}					
			List lLstBillCode = IFMSCommonServiceImpl.getLookupValues(lObjRsrcBndle.getString("CMN.BillCode"), gLngLangId, inputMap);
			Object[] lObjBillCodeArray = null;
			if(lLstBillCode != null && !lLstBillCode.isEmpty())
			{
				Object[] lObjTemp = lLstBillCode.toArray();
				lObjBillCodeArray = new Object[lObjTemp.length];
				for(Object lObj: lObjTemp)
				{
					CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
					lObjBillCodeArray[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
				}
			}
			inputMap.put("PPONo", StringUtility.getParameter("PPONo", request));
			inputMap.put("subjectId", request.getParameter("subjectId"));
			inputMap.put("PnsnHeadCode", request.getParameter("PnsnHeadCode"));
			inputMap.put("PensionType", StringUtility.getParameter("PensionType", request));
			inputMap.put("PensionScheme", StringUtility.getParameter("Scheme", request));
			
            String lStrIsConfigStatus = null;
//          Calling bill specific method to get bill specific data
            if("Monthly".equals( StringUtility.getParameter("PensionType", request)))
            {
            	String lStrRqstServ = "MONTHLY_PENSION_BILL";
            	String lStrPagePath = "/WEB-INF/jsp/pension/monthlyPensionBill.jsp";
                //String lStrFetchServ = lObjConfigVO.getFetchBillDataSrvc();                
            	lStrIsConfigStatus="Y";
                lResultObj = serv.executeService(lStrRqstServ, inputMap);
                Map lMapResult = (Map) lResultObj.getResultValue();
                inputMap.put("MonthlyPrint", "Y");
                inputMap.put("BillData", lMapResult);
                inputMap.put("PagePath", lStrPagePath);
            }
            else
            {
	    		ConfigOnlineBill lObjConfigVO = ConfigOnlineBillUtil.getInstance(Long.decode(request.getParameter("subjectId")), serv);
	    		lStrIsConfigStatus = lObjConfigVO.getIsConfigured();
	    		
	    		if(lObjConfigVO != null && lObjConfigVO.getIsConfigured().equals("Y"))
	    		{	
	    			String lStrRqstServ = lObjConfigVO.getRqstSrvc();
	                String lStrPagePath = lObjConfigVO.getJspPath();
	                inputMap.put("AprdRqstIdArray", lStrRqstID);
	                lResultObj = serv.executeService(lStrRqstServ, inputMap);
	                Map lMapResult = (Map) lResultObj.getResultValue();
	                inputMap.put("BillData", lMapResult);
	                inputMap.put("PagePath", lStrPagePath);
	    		}
            }
          //  if(! "MR".equals( StringUtility.getParameter("PensionType", request)))
            {
            	lResultObj = serv.executeService("PENSION_BUD_EDP_DETAILS", inputMap);
            	
            	lMap = (Map) lResultObj.getResultValue();
                rcptObjHds = (List) lMap.get("rcptObjHds");
                expObjHds = (List) lMap.get("expObjHds");
                //List recObjHds = (List) lMap.get("recObjHds");
                expEdpList = (List) lMap.get("expEdpList");
            }  
            
            SgvoDeptMst lDeptMstVO = new SgvoDeptMst();
            
            inputMap.put("LangId", gLngLangId);
            lResultObj = serv.executeService("GET_MONTH_DTLS", inputMap);
            lMap = (Map) lResultObj.getResultValue();
            Map lResultMap = (Map) lMap.get("ResultMap");
            List lMonthList = (List) lResultMap.get("MonthList");
    		/* Claimant DDO Details by post.. Start..   */
            
            List lEmpDtlsList = new ArrayList();
            
            lEmpDtlsList = getClaimantInfo(inputMap);
            
            String lStrEmpName = null;
            String lStrEmpDesgn = null;
            String lStrDeptName = null;
            String lStrDDOCode = null;
            String lStrCardexNo = null;
            if(lEmpDtlsList != null)
            {
	            Object ObjEmp[] = null;
	            
	            ObjEmp = (Object[]) lEmpDtlsList.get(0);
	            
	            lStrEmpName = ObjEmp[0].toString();
	            lStrEmpDesgn = ObjEmp[1].toString();
	            lStrDeptName = ObjEmp[2].toString();
	            lStrDDOCode = ObjEmp[3].toString();
	            lStrCardexNo = ObjEmp[4].toString();
            }            
            inputMap.put("EmpName", lStrEmpName);
            inputMap.put("EmpDesgn", lStrEmpDesgn);
            inputMap.put("DeptName", lStrDeptName);
            inputMap.put("DDOCode", lStrDDOCode);
            inputMap.put("cardexNo",lStrCardexNo );
            /* Claimant DDO Details by post.. End..   */
            /* Party Information For Specific Bill... Start... */
            PensionBillDAO lObjPensionBillDAO = new PensionBillDAOImpl(serv.getSessionFactory()); 
            List<RltBillParty> lLstRltBillPartyVO = null;
            
         //   if(! "MR".equals( StringUtility.getParameter("PensionType", request)))
            {
	            lLstRltBillPartyVO = lObjPensionBillDAO.getPensionerPartyDtls(inputMap);
	            inputMap.put("RltBillParty", lLstRltBillPartyVO);
            }
            /* Party Information For Specific Bill... End... */
            /* Hard Coded Budget Details.. Start.. */
            Map lMapExpClsDtls = null;
            Map lMapFundDtls = null;
            Map lMapBudTypeDtls = null;
            Map lMapBillTypeDtls = null;
            
            lMapExpClsDtls = getCmnLookUpValueAndDescription(cmnDAO, "Voted");
            lMapFundDtls = getCmnLookUpValueAndDescription(cmnDAO, "Consolidated");
            //lMapBudTypeDtls = getCmnLookUpValueAndDescription(cmnDAO, "State_Nonplan"); //Deep
            lMapBillTypeDtls = getCmnLookUpValueAndDescription(cmnDAO, "Regular");

            inputMap.put("ClassOfExp", (String) lMapExpClsDtls.get("Label"));
            //inputMap.put("TypeOfBudget", (String) lMapBudTypeDtls.get("Label"));
            inputMap.put("Selected_ExpCls", lMapExpClsDtls);
            inputMap.put("Selected_Fund", lMapFundDtls);
            inputMap.put("Selected_BudType", lMapBudTypeDtls);
            inputMap.put("Selected_BillType", lMapBillTypeDtls);
            /* Hard Coded Budget Details.. End.. */
    		inputMap.put("BillTypeId", request.getParameter("subjectId"));
    		inputMap.put("BillType", lBillType);
    		inputMap.put("EmpType", lEmpType);
    		inputMap.put("EmpTypeSelectedByUser", lStrEmpType);
    		inputMap.put("FundCombo", lListFund);
            inputMap.put("ClassOfExpCombo", lListClassOfExp);
            inputMap.put("BudTypeCombo",lObjBudjetTypeArray);
            inputMap.put("BillCodeArray",lObjBillCodeArray);
            inputMap.put("rcptObjHdsAdded", rcptObjHds);    
            inputMap.put("expObjHds", expObjHds);
            inputMap.put("expEdpList", expEdpList);
            inputMap.put("DeptVO", lDeptMstVO);
            inputMap.put("MonthList", lMonthList);
            inputMap.put("currRemarks", "");
            inputMap.put("isConfigStatus", lStrIsConfigStatus);
            inputMap.put("isShowObjection", "N");
           	
	        objRes.setResultValue(inputMap);
	        objRes.setViewName("createPensionBills");
	    }
	    catch (Exception e)
	    {
	    	gLogger.error("Error is : ", e);
	        objRes.setResultValue(null);
	        objRes.setThrowable(e);
	        objRes.setResultCode(ErrorConstants.ERROR);
	        objRes.setViewName("errorPage");
	    }

	    return objRes;
	}
	
	private void setSessionInfo(Map inputMap)
	{
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocId = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gLocId = SessionHelper.getLocationId(inputMap);
        gDtCurDate = SessionHelper.getCurDate();
    }
	
	private Map getCmnLookUpValueAndDescription(CmnLookupMstDAO lDAOCmnLkUpMst, String lStrLookupName)
    {
        CmnLookupMst lStrCmnLookUpDescVO = lDAOCmnLkUpMst.getLookUpVOByLookUpNameAndLang(lStrLookupName, gLngLangId);
        String lStrCmnLookUpDesc = lStrCmnLookUpDescVO.getLookupDesc();
        String lStrCmnLookUpShrtName = lStrCmnLookUpDescVO.getLookupShortName();

        Map lMapValueAndDesc = new HashMap();
        lMapValueAndDesc.put("Value", lStrLookupName);
        lMapValueAndDesc.put("Desc", lStrCmnLookUpDesc);
        lMapValueAndDesc.put("Label", lStrCmnLookUpShrtName);

        return lMapValueAndDesc;
    }
    public ResultObject isAllSpcificBillsCrtd(Map orgsMap)
    {
        ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");
        StringBuilder lStrRes = new StringBuilder();
        try
        {
            PensionBillDAOImpl lObjPnsnBillDao  = new PensionBillDAOImpl(serv.getSessionFactory());
            String lStrppoNo = request.getParameter("ppoNo").toString();
            String lStrReqStr = request.getParameter("ppoNo").toString();
            String[] chk = null;
            chk = lStrReqStr.split("~");
            lStrppoNo = chk[2];
            lStrRes.append(lObjPnsnBillDao.isAllBillsCreated(lStrppoNo));
            orgsMap.put("ajaxKey", lStrRes.toString());
            lObjResult.setResultValue(orgsMap);
            lObjResult.setViewName("ajaxData");
        }
        catch(Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            lObjResult.setResultValue(null);
            lObjResult.setThrowable(e);
            lObjResult.setResultCode(ErrorConstants.ERROR);
            lObjResult.setViewName("errorPage");
        }
        return lObjResult;
    }
    public ResultObject savePensionSpecificBills(Map inputMap)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrSubject = bundleConst.getString("OnlineBillSubject");
        String lStrAction = null;
        StringBuilder lStrBldXML = null;
        TrnBillRegister lObjBillReg = (TrnBillRegister) inputMap.get("BillRegVO");
        TrnBillMvmnt lObjBilMvnt =   (TrnBillMvmnt) inputMap.get("BillMvmntVO");
        List  lMvntVo = new ArrayList();
        TrnBillMvmnt lObjBilMvnt1 =   new TrnBillMvmnt();
        Map lMapInsertOutput = null;
        try
        {
            setSessionInfo(inputMap);
            if (lObjBillReg.getBillNo() == 0) // Call the workflow at time of
            // Create Bill ONLY
            {
            	inputMap.put("DocId", bundleConst.getString("WF.DocId.PensionBill"));
            	inputMap.put("subjectType", lStrSubject);

            	BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
            	Long[] lObjRef = null;//lObjCmnSrvcDAOImpl.getMaxReferenceNo(gStrLocId,gLngPostId,true);
            	lObjBillReg.setRefNum(lObjRef[0]);

            	ResultObject objOnlineRes = serv.executeService("CREATE_ONLINEBILL", inputMap); //createOnlineBillFromWF
            	lMapInsertOutput = (Map) objOnlineRes.getResultValue();
            }
            else
            // Save the data
            {
                OnlineBillServiceImpl lObjOnlineBill = new OnlineBillServiceImpl();
            	//temp for reading pk  bill movnt pk
            	lMapInsertOutput = updatePensionBill(inputMap);
            	
            	OnlineBillDAOImpl lObjBillDao = new OnlineBillDAOImpl(serv.getSessionFactory());
            	/*lMvntVo  = lObjBillDao.getBillMvmntVO(lObjBillReg.getBillNo(), lObjBillReg.getCurrBillStatus());
            	lObjBilMvnt1 = (TrnBillMvmnt) lMvntVo.get(0);
            	lObjBilMvnt.setBillMvmtId(lObjBilMvnt1.getBillMvmtId());
            	lObjBilMvnt.setMvmntStatus(lObjBillReg.getCurrBillStatus());
            	lObjBilMvnt1.setBillRemarks(lObjBilMvnt.getBillRemarks());
            	lObjBilMvnt1.setObjRemarks(lObjBilMvnt.getObjRemarks());*/
            	lObjOnlineBill.saveBillMovement(lObjBilMvnt1,inputMap);
            }
            
            ResultObject lResObj = serv.executeService("OBJ_VOGEN", inputMap);
            Map lMapService = (Map) lResObj.getResultValue();
            List lList = (List) lMapService.get("auditObjList");

            AuditorObjectionDAO lObjAdtObjDAO = new AuditorObjectionDAOImpl(RltBillObjection.class, serv.getSessionFactory());
            lObjAdtObjDAO.updateObjection(lList, String.valueOf(lObjBillReg.getBillNo()), SessionHelper.getUserId(inputMap).toString(), lMapService);

            lStrAction = StringUtility.getParameter("userAction", request).trim();

            if ("save".equals(lStrAction))
            {
                lMapInsertOutput.put("isEditable", "Y");
            }
            else if ("approve".equals(lStrAction))
            {
                lMapInsertOutput.put("isEditable", "N");
            }
            //Prepares an XML String containing PKs of all the common and billspecific tables
            lStrBldXML = getResponseXMLDoc(lMapInsertOutput);
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",lStrBldXML.toString()).toString();
            lMapInsertOutput.put("ajaxKey", lStrResult);
            objRes.setResultCode(ErrorConstants.SUCCESS);
            objRes.setResultValue(lMapInsertOutput);
            objRes.setViewName("ajaxData");
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            objRes.setThrowable(e);
            objRes.setResultValue(null);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");
        }

        return objRes;
    }
	
	/**
	 * Insert PensionSpecifics Bills...
	 * 
	 * @param objectArgs
	 * @return
	 */
	public Map insertPensionBillWF(Map objectArgs) throws Exception
	{
        WorkFlowVO wfVO = null;
        Map lMapService = null;

        boolean lBoolIsNewBill = false;

        if (objectArgs.get("WorkFlowVO") != null) // Will be called only in
        // case of Workflow
        {
            wfVO = (WorkFlowVO) objectArgs.get("WorkFlowVO");
            lMapService = wfVO.getAppMap();
            lBoolIsNewBill = true;
        }
        else
        // Else Update the data
        {
            lMapService = objectArgs;
        }

        lMapService.put("IsNewBill", lBoolIsNewBill);

        Long lLngBillNo = 0L;
        
        ServiceLocator srvcLoc = (ServiceLocator) lMapService.get("serviceLocator");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) lMapService.get("requestObj");

        TrnBillRegister lObjBillReg = (TrnBillRegister) lMapService.get("BillRegVO");
        TrnOnlinebillEmp lObjEmpDtlsVO = (TrnOnlinebillEmp) lMapService.get("EmpDtlsVO");
        //TrnBillBudheadDtls lObjBudHeadVO = (TrnBillBudheadDtls) lMapService.get("BillHeadVO");
        TrnBillMvmnt lObjBillMvmnt = (TrnBillMvmnt) lMapService.get("BillMvmntVO");
        //TrnBillRemarks lObjBillRmrks = (TrnBillRemarks) lMapService.get("BillRmrksVO");
        RltBillParty lObjBillParty = null;
        List<RltBillParty> lLstBillParty = null;
        RltBillPartyDAO lObjBillPartyDAO = new RltBillPartyDAOImpl(RltBillParty.class, srvcLoc.getSessionFactory());
        try
        {
            saveBillRegister(lObjBillReg, lMapService);
            lLngBillNo = lObjBillReg.getBillNo();
            // In case of TC bill & multiple challans save bill-no
            // & receipt-Ids of all challans into rlt_bill_challan
            Long billNoOBPM = (Long) lLngBillNo;
            lMapService.put("billNoOBPM", billNoOBPM);            
            
            if (lBoolIsNewBill) // Insert in bill movement only at time of bill creation
            {	
                lObjBillMvmnt.setBillNo(lLngBillNo);
                lObjBillMvmnt.setMovemntId(Long.parseLong("1"));
            	//lObjBillMvmnt.setMvmntStatus(DBConstants.ST_BAPRVD_DDO);
            	BptmCommonServiceImpl.insertMovement(lObjBillMvmnt, lMapService);
            	
            	/*TrnBillMvmnt trnBillMvmnt = new TrnBillMvmnt();
            	trnBillMvmnt.setLocationCode(SessionHelper.getLocationCode(lMapService));
            	trnBillMvmnt.setCreatedDate(new Date());
            	trnBillMvmnt.setCreatedPostId(SessionHelper.getPostId(lMapService));
            	trnBillMvmnt.setCreatedUserId(SessionHelper.getUserId(lMapService));
            	trnBillMvmnt.setBillNo(lLngBillNo);
            	trnBillMvmnt.setReceivedDate(lObjBillMvmnt.getReceivedDate());
            	//trnBillMvmnt.setReceivedFlag(lObjBillMvmnt.getReceivedFlag());
            	trnBillMvmnt.setReceivingUserId(lObjBillMvmnt.getReceivingUserId());
            	trnBillMvmnt.setDbId(lObjBillMvmnt.getDbId());
            	trnBillMvmnt.setStatusUpdtDate(lObjBillMvmnt.getStatusUpdtDate());
            	trnBillMvmnt.setStatusUpdtPostid(lObjBillMvmnt.getStatusUpdtPostid());
            	trnBillMvmnt.setStatusUpdtUserid(lObjBillMvmnt.getStatusUpdtUserid());
            	trnBillMvmnt.setMovemntId(Long.parseLong("2"));
            	trnBillMvmnt.setMvmntStatus(Short.parseShort("60"));
            	BptmCommonServiceImpl.insertMovement(trnBillMvmnt, lMapService);*/
            } //Deep

            lObjEmpDtlsVO.setBillNo(lLngBillNo);
            saveEmployeeDtls(lObjEmpDtlsVO, lMapService);

            lMapService.put("TrnEmpId", lObjEmpDtlsVO.getTrnOnlinebillEmpId());
            /*lObjBudHeadVO.setBillNo(lLngBillNo);
            saveBillBudHd(lObjBudHeadVO, lMapService);*/ //Deep

            /*lObjBillRmrks.setBillNo(lLngBillNo);
            saveBillRemarks(lObjBillRmrks, lMapService);*/ //Deep

            lMapService.put("BillNo", lLngBillNo);

            // Saving the DSS Data
            //lMapDSSOutput = saveDSSData(lMapService);
            srvcLoc.executeService("SAVE_DSS_DATA", lMapService);

            ResultObject lResObj = srvcLoc.executeService("OBJ_VOGEN", lMapService);
            lMapService = (Map) lResObj.getResultValue();
            List lList = (List) lMapService.get("auditObjList");

            AuditorObjectionDAO lObjAdtObjDAO = new AuditorObjectionDAOImpl(RltBillObjection.class, srvcLoc
                    .getSessionFactory());
            lObjAdtObjDAO.updateObjection(lList, String.valueOf(lLngBillNo), String.valueOf(gLngUserId), lMapService);

            // Deleting previous EDP Data
            srvcLoc.executeService("DELETE_EXT_EDP_CODE", lMapService);

            lMapService.put("billNo", String.valueOf(lLngBillNo));
            ResultObject lRsBillEDPDtls = srvcLoc.executeService("GENERATE_EDP_VO", lMapService);
           // lMapBillEDPDtls = (Map) lRsBillEDPDtls.getResultValue();

            // Inserting New EDP Data
              //srvcLoc.executeService("INSERT_EDP_DTLS", lMapBillEDPDtls);
            
            lMapService.put("billNo", String.valueOf(lLngBillNo));
            OnlineBillEDPService lObjOnlineBillEDPService = new OnlineBillEDPServiceImpl();
            lObjOnlineBillEDPService.generateEdpDtlsMap(lMapService);
            lObjOnlineBillEDPService.insertEdpDtls(lMapService);

            // Deleting previous party Info
            //lLstBillParty = lObjBillPartyDAO.getPartyByBill(lLngBillNo);
            Iterator<RltBillParty> lItrBillParty = lLstBillParty.iterator();
            while (lItrBillParty.hasNext())
            {
                lObjBillPartyDAO.delete(lItrBillParty.next());
            }

            // Saving the Bill Party Details
            if (lMapService.get("BillPartyDtls") != null)
            {
                lLstBillParty = (List<RltBillParty>) lMapService.get("BillPartyDtls");
                lItrBillParty = lLstBillParty.iterator();

                while (lItrBillParty.hasNext())
                {
                    lObjBillParty = lItrBillParty.next();
                    lObjBillParty.setBillNo(lLngBillNo);
                    saveBillParty(lObjBillParty, lMapService);
                }
            }
            if("Monthly".equals( StringUtility.getParameter("PensionType", request)))
            {
                lMapService.put("PensionType", StringUtility.getParameter("PensionType", request));
            	lMapService.put("HeadCode", StringUtility.getParameter("PnsnHeadCode", request));
            	objRes = srvcLoc.executeService("GET_MAP_FOR_MONTHLY", lMapService);
            	objRes = srvcLoc.executeService("SAVE_MONTHLY_PENSION_BILL", lMapService);
            }
            else
            {
            	ConfigOnlineBill lObjConfigBill = ConfigOnlineBillUtil.getInstance(lObjBillReg.getSubjectId(), srvcLoc);
                objRes = srvcLoc.executeService(lObjConfigBill.getSaveBillSrvc(), lMapService);
            }
            
            lMapService.put("jobId", lLngBillNo + "");
            lMapService.put("postId", gLngPostId);
            lMapService.put("BillMovementVO", lObjBillMvmnt);

            lMapService.put("StateMsg", "Bill Saved Successfully");

             if (wfVO != null)
            {
                wfVO.setJobRefId(lLngBillNo + "");
                lMapService.put("WorkFlowVO", wfVO);
                wfVO.setAppMap(lMapService);
            }
           
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            throw(e);
        }
        return lMapService;
    }
	
	/**
	 * Update PensionSpecifics Bills Remarks & Objection...
	 * 
	 * @param objectArgs
	 * @return
	 */
	public Map updatePensionBill(Map objectArgs) throws Exception
	{
        WorkFlowVO wfVO = null;
        Map lMapService = null;

        boolean lBoolIsNewBill = false;

        if (objectArgs.get("WorkFlowVO") != null) // Will be called only in
        // case of Workflow
        {
            wfVO = (WorkFlowVO) objectArgs.get("WorkFlowVO");
            lMapService = wfVO.getAppMap();
            lBoolIsNewBill = true;
        }
        else
        // Else Update the data
        {
            lMapService = objectArgs;
        }

        lMapService.put("IsNewBill", lBoolIsNewBill);

        Long lLngBillNo = 0L;
        ServiceLocator srvcLoc = (ServiceLocator) lMapService.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapService.get("requestObj");
        
        TrnBillRegister lObjBillReg = (TrnBillRegister) lMapService.get("BillRegVO");

        PhyBillDAOImpl phyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class,srvcLoc.getSessionFactory());
        String lPnsnTokenNo = StringUtility.getParameter("txtPnsnTokenNo", request);
        String lStrTokenFlg = StringUtility.getParameter("hidTokenFlg", request);
        
        try
        {
            setSessionInfo(lMapService);
            lLngBillNo = lObjBillReg.getBillNo();
            
            if(lStrTokenFlg != null && lStrTokenFlg.equalsIgnoreCase("N"))
            {
                if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
                {
                    CommonPensionDAOImpl lObjCommonPensionDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
                    Long lLngCaseId = lObjCommonPensionDAO.getPKForRltpensionCaseBill(lObjBillReg.getBillNo());
                    Long lLngHdrId = lObjCommonPensionDAO.getPKForBillHdr(lObjBillReg.getBillNo());
                    // Update Token No Into Bill Register. 
                    
                    lObjBillReg.setTokenNum(Long.valueOf(lPnsnTokenNo));
                    phyBillDAOImpl.update(lObjBillReg);
                    
                    // Update Token No Into TrnPensionBillHdr.
                    
                    TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,srvcLoc.getSessionFactory());
                    TrnPensionBillHdr lObjTrnPensionBillHdr = null;
                    if(lLngHdrId != null && lLngHdrId != 0L)
                    {
                    	  lObjTrnPensionBillHdr = lObjTrnPensionBillHdrDAO.read(lLngHdrId);
                          lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo);
                          lObjTrnPensionBillHdrDAO.update(lObjTrnPensionBillHdr);
                    }
                    // Update Token No Into RltPensioncaseBill
                    RltPensioncaseBillDAO lObjRltPensioncaseBillDAO = new RltPensioncaseBillDAOImpl(RltPensioncaseBill.class,srvcLoc.getSessionFactory());
                    RltPensioncaseBill lObjRltPensioncaseBill = null;
                    if(lLngCaseId != null && lLngCaseId != 0L)
                    {
                    	 lObjRltPensioncaseBill = lObjRltPensioncaseBillDAO.read(lLngCaseId);
                         lObjRltPensioncaseBill.setTokenNo(Long.valueOf(lPnsnTokenNo));
                         lObjRltPensioncaseBillDAO.update(lObjRltPensioncaseBill);
                    }
                    // Update Token Status.
                    OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,srvcLoc.getSessionFactory());
                    //lObjTokenStatusDAO.updateTokenStatus(Long.valueOf(lPnsnTokenNo), gStrLocId, lLngBillNo, gLngUserId, gLngPostId,BillProcConstants.INWARD_TYPE_BILL);
                }                
            }
            
            lMapService.put("BillNo", lLngBillNo);            
            lMapService.put("jobId", lLngBillNo + "");
            lMapService.put("postId", gLngPostId);
            
            lMapService.put("StateMsg", "Bill Saved Successfully");

            if (wfVO != null)
            {
                wfVO.setJobRefId(lLngBillNo + "");
                lMapService.put("WorkFlowVO", wfVO);
                wfVO.setAppMap(lMapService);
            }
           
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            throw(e);
        }
        return lMapService;
    }
	
    /**
     * Saves the Bills data in TrnBillRegister. In case of Bill Creation it
     * inserts the data else it updates the data.
     * 
     * @param lObjTrnBillRegVO
     *            TrnBillRegisterVO
     * @param inputMap
     *            InputMap.
     * @throws Exception
     *             Exception
     */
    public void saveBillRegister(TrnBillRegister lObjTrnBillRegVO, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class, srvcLoc.getSessionFactory());
        try
        {
            if (lObjTrnBillRegVO.getBillNo() > 0)
            {
                lObjPhyBillDAOImpl.update(lObjTrnBillRegVO);

                // delete entry in case of TC bill & multiple challans & if any
                // record is deleted from jsp - start
                if (lObjTrnBillRegVO.getTcBill().equalsIgnoreCase("TC"))
                {
                    inputMap.put("billNoOBPM", lObjTrnBillRegVO.getBillNo());
                    // deleteChallanRecord(inputMap);
                }
            }
            else
            {
                synchronized (this)
                {
                    inputMap.put("DDOCode", lObjTrnBillRegVO.getDdoCode());
                    Long lngHeirRefId = (Long) inputMap.get("HeirRefId");
                    
                    String lStrBillCntrlNo = BptmCommonServiceImpl.getBillControlNumber(inputMap);  //getBillCntrlNumSeq
                    lObjTrnBillRegVO.setBillCntrlNo(lStrBillCntrlNo);
                    lObjTrnBillRegVO.setAudPostId(SessionHelper.getPostId(inputMap));
                    //lObjTrnBillRegVO.setHierarchyRefId(lngHeirRefId);			//Deep
                    BptmCommonServiceImpl.insertBillRegister(lObjTrnBillRegVO, inputMap);
                }
            }

            inputMap.put("billNo", lObjTrnBillRegVO.getBillNo());
            inputMap.put("PnsnBillCntrlNo",lObjTrnBillRegVO.getRefNum().toString());
            if(lObjTrnBillRegVO.getTokenNum() != null && lObjTrnBillRegVO.getTokenNum().toString().length() > 0)
            {
                inputMap.put("PnsnTokenNo", lObjTrnBillRegVO.getTokenNum());
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw e;
        }
    }

    /**
     * Saves the Party data associated with the Bills in RltBillParty. the data.
     * 
     * @param RltBillParty
     *            lObjBillParty, Map InputMap.
     * @throws Exception
     *             Exception
     */

    public void saveBillParty(RltBillParty lObjBillParty, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        RltBillPartyDAO lObjBillPartyDAO = new RltBillPartyDAOImpl(RltBillParty.class, srvcLoc.getSessionFactory());
        Long lLngBillPartyId = null;

        try
        {
            lLngBillPartyId = IFMSCommonServiceImpl.getNextSeqNum("rlt_bill_party", inputMap);
            lObjBillParty.setBillPartyId(lLngBillPartyId);
            lObjBillPartyDAO.create(lObjBillParty);
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw e;
        }
    }


    /**
     * Saves the Bill Movement Data in TrnBillMvmnt. This method will be called
     * only at time of Bill Creation.
     * 
     * @param lObjBillMvmntVO TrnBillMvmntVO
     * @param inputMap InputMap.
     * @throws Exception Exception
     */
    public void saveBillMovement(TrnBillMvmnt lObjBillMvmntVO, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        BillMovementDAOImpl lObjMvmntDAO = new BillMovementDAOImpl(TrnBillMvmnt.class, srvcLoc.getSessionFactory());

        try
        {
            if (lObjBillMvmntVO.getBillMvmtId() > 0)
            {
                lObjMvmntDAO.update(lObjBillMvmntVO);
            }
            else
            {
            	BptmCommonServiceImpl.insertMovement(lObjBillMvmntVO, inputMap);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw e;
        }
    }


    /**
     * Saves the Bill Remarks Data in TrnBillRemarks. In case of Bill Creation
     * it inserts the data else it updates the data.
     * 
     * @param lObjBillRmk TrnBillRemarksVO
     * @param inputMap InputMap.
     * @throws Exception Exception
     */
   /* public void saveBillRemarks(TrnBillRemarks lObjBillRmk, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        RemarksDAO lObjRmksDAO = new RemarksDAOImpl(TrnBillRemarks.class, srvcLoc.getSessionFactory());

        try
        {
            if (lObjBillRmk.getRmrksId() > 0)
            {
                inputMap.put("TrnBillRemarksVO", lObjBillRmk);
                lObjRmksDAO.updateRemarks(inputMap);
            }
            else
            {
                BptmCommonServiceImpl.insertRemarks(lObjBillRmk, inputMap);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw e;
        }
    }*/


    /**
     * Saves the Bill Budget Heads Data in TrnBillBudheadDtls. In case of Bill
     * Creation it inserts the data else it updates the data.
     * 
     * @param lObjBillBudHdVO
     *            TrnBillBudheadDtlsVO.
     * @param inputMap
     *            InputMap.
     * @throws Exception
     *             Exception
     */

   /* public void saveBillBudHd(TrnBillBudheadDtls lObjBillBudHdVO, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        BdgtHeadDtlsDAOImpl lObjBdgtHeadDtlsDAOImpl = new BdgtHeadDtlsDAOImpl(TrnBillBudheadDtls.class, srvcLoc
                .getSessionFactory());

        try
        {
            if (lObjBillBudHdVO.getBillBudId() > 0)
            {
                lObjBdgtHeadDtlsDAOImpl.update(lObjBillBudHdVO);
            }
            else
            {
                BptmCommonServiceImpl.insertBudHeadDtls(lObjBillBudHdVO, inputMap);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw e;
        }
    }*/
    
    public void saveEmployeeDtls(TrnOnlinebillEmp lObjTrnOnlineBillEmpVO, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        TrnOnlinebillEmpDAO lObjTrnOnlinebillEmpDAO = new TrnOnlinebillEmpDAOImpl(TrnOnlinebillEmp.class, srvcLoc
                .getSessionFactory());

        try
        {
            if (lObjTrnOnlineBillEmpVO.getTrnOnlinebillEmpId() == null)
            {
                lObjTrnOnlineBillEmpVO.setTrnOnlinebillEmpId(IFMSCommonServiceImpl.getNextSeqNum("trn_onlinebill_emp",
                        inputMap));
                lObjTrnOnlinebillEmpDAO.create(lObjTrnOnlineBillEmpVO);
            }
            else
            {
                lObjTrnOnlinebillEmpDAO.update(lObjTrnOnlineBillEmpVO);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw e;
        }
    }
    
    /**
     * Saves data in DSS tables
     * 
     * @param Map
     *            lMapInput
     * @return ResultObject
     */
    /*public Map saveDSSData(Map lMapService)
    {
        Map lMapDSSOutput = null;
        Map lMapDSSValidation = null;
        Map lMapDSSInput = new HashMap();

        DssDataService lObjDSSDataService = new DssDataServiceImpl();

        boolean lBoolIsNewBill = (Boolean) lMapService.get("IsNewBill");
        Long lLngBillNO = (Long) lMapService.get("BillNo");
        RptExpenditureDtls lObjRptHdrVO = (RptExpenditureDtls) lMapService.get("DSSRptHdr");

        try
        {
            lObjRptHdrVO.setExpNo(new BigDecimal(lLngBillNO));
            lMapDSSValidation = lObjRptHdrVO.validateData();

            gLogger.info("Flag from the Validation is : " + lMapDSSValidation.get("flag"));
            gLogger.info("Status from the Validation is : " + lMapDSSValidation.get("Status"));

            lMapDSSInput.put("map", lMapService);
            lMapDSSInput.put("RptExpenditureVO", lObjRptHdrVO);

            TrnBillBudheadDtls budHeadVo = (TrnBillBudheadDtls) lMapService.get("BillHeadVO");
            if(budHeadVo.getDmndNo().equals("999") && Long.parseLong(budHeadVo.getBudMjrHd()) > 8000)
            {
            	RptReceiptDtls lObjRptRcptDtls = new RptReceiptDtls();
            	//lObjRptRcptDtls.
            	if(lBoolIsNewBill)
	            {
            		lMapDSSOutput = lObjDSSDataService.insertReceiptData(lMapDSSInput);
	            }
	            else
	            {
	            	lMapDSSOutput = lObjDSSDataService.updateReceiptData(lMapDSSInput);
	            }
            }
            else
            {
	            if(lBoolIsNewBill)
	            {
	                lMapDSSOutput = lObjDSSDataService.insertExpData(lMapDSSInput);
	            }
	            else
	            {
	                lMapDSSOutput = lObjDSSDataService.updateExpData(lMapDSSInput);
	            }
            }
            
            gLogger.info("DSS Output is : " + lMapDSSOutput);
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
        }
        return lMapDSSOutput;
    }*/
    
//  generates a response XML to be sent back while saving or updating Bill
    //ResponseXML consists of primary keys of tables used during the transaction
    private StringBuilder getResponseXMLDoc(Map lMapInput)
    {
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        StringBuilder lStrBldHidPKs = new StringBuilder();
        try{
        boolean lNewBillFlg = (Boolean) lMapInput.get("IsNewBill"); 
        
        String lStrTypeFlag = null;
        if(lMapInput.containsKey("PensionType"))
    	{
    		lStrTypeFlag = lMapInput.get("PensionType").toString();
    	}
        lStrBldHidPKs.append("<XMLDOC>");
        lStrBldHidPKs.append(getCmnTabPKs(lMapInput));
                
        lStrBldHidPKs.append("<MESSAGECODE>");
        String lStrAction = StringUtility.getParameter("userAction", request).trim();

        if (lStrAction.equalsIgnoreCase("save"))
        {
            lStrBldHidPKs.append("Bill Saved Successfully");
        }
        else if (lStrAction.equalsIgnoreCase("approve"))
        {
            lStrBldHidPKs.append("Bill Approved Successfully");
        }
        
        lStrBldHidPKs.append("</MESSAGECODE>");
        lStrBldHidPKs.append("<NEWBILLFLAGE>");
        
        if(lNewBillFlg == false || "Monthly".equalsIgnoreCase(lStrTypeFlag) || "MR".equalsIgnoreCase(lStrTypeFlag))
        {
        	gLogger.info("come in XML :: pension :: false ");
        	lStrBldHidPKs.append("false");
        }else  
        {
        	lStrBldHidPKs.append("true");
        }
        
        lStrBldHidPKs.append("</NEWBILLFLAGE>");
        lStrBldHidPKs.append("</XMLDOC>");  
        }
        catch(Exception e)
        {
            gLogger.error("Error is:"+e,e);
        }
        return lStrBldHidPKs;
    }
    
    private StringBuilder getCmnTabPKs(Map lMapInput)
    {
        StringBuilder lStrBldHidPKs = new StringBuilder();
        
        try
        {    
            TrnBillRegister lObjTrnBillRegister = (TrnBillRegister)lMapInput.get("BillRegVO");
            
            lStrBldHidPKs.append("<TrnBillRegister>" + lObjTrnBillRegister.getBillNo());
            lStrBldHidPKs.append("</TrnBillRegister>");  
            
            lStrBldHidPKs.append("<BillControlNumber>" + lObjTrnBillRegister.getRefNum());
            lStrBldHidPKs.append("</BillControlNumber>");
            
            lStrBldHidPKs.append("<BillTokenNumber>" + lObjTrnBillRegister.getTokenNum());
            lStrBldHidPKs.append("</BillTokenNumber>");
        }
        catch (Exception e)
        {
            gLogger.error("Error in execution of getHiddenPKsForUpdate. Error is : " + e, e);
        }         
        
        return lStrBldHidPKs;
    }
    
    /**
     * Fetches the saved Data for editing/viewing the Bill
     * 
     * @param Map lMapInput
     * @return lObjResult ResultObject
     */
    public ResultObject getBillData(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        //String lStrBillSpecSrvcName = null;
        //TrnBillRegister lObjTrnBillRegister = null;
        //TrnBillBudheadDtls lObjTrnBudHdDtls = null;
        //List<RltBillParty> lLstRltBillPartyVO = null;
        //BptmCommonServicesDAOImpl lObjTrnBillRegisterDAO = null;
        //OnlineBillDAO lObjOnlineBillDao = null;
        //ShowBillDAOImpl lObjShowBillDaoImpl = null;
        String lStrCurrUsrRmrks = null;
        String lStrCurrObjRemarks = null;
        /*Object[] lObjArrPK = null;
        List lLstPK = null;
        ResultObject lResultObj = null;
        Map lMap = null;
        Map lResultMap = null;
        BigDecimal lLngDDOExp = null;
        BigDecimal lLngBalance = null;
        String lStrBillCntrNo = null;
        List lMonthList = null;
        String lStrPagePath = null;
        List rcptObjHds = null;
        List expObjHds = null;
        List recObjHds = null;
        List expEdpList = null;
        Long lLngBillType = null;
        String lStrSubjectId = null;
        String lStrBillNo = null;
        String lStrBillStatus = null;
        String lStrIsEditable = null;
        Long lLngSubjectId = null;*/
        Long lLngBillNo = null;
        /*Map lMapExpClsDtls = null;
        Map lMapFundDtls = null;
        Map lMapBudTypeDtls = null;
        Map lMapBillTypeDtls = null;
        Map lMapEmpTypeDtls = null;
        String lStrMonthName = null;
        String lStrIsNewFromRejected = null;
        String lStrGONGO = null;
        String lStrFund = null;
        String lStrClassOfExp = null;
        String lStrTcBill = null;
        String lStrBudgetType = null;
        String lStrPPONo = null;
        String lStrIsConfigStatus = null;*/

        try
        {
        	
        	ResultObject objOnlineRes = serv.executeService("GET_BILL_DATA", inputMap);
        	inputMap = (Map) objOnlineRes.getResultValue();
        	if(inputMap.containsKey("TrnBillRegister") && inputMap.get("TrnBillRegister") != null)
        	{
        		TrnBillRegister lObjTrnBillReg = new TrnBillRegister(); 
        		lObjTrnBillReg = (TrnBillRegister) inputMap.get("TrnBillRegister");
        		if(inputMap.containsKey("ShowBillVO") && inputMap.get("ShowBillVO") != null)
            	{
            		 TrnShowBill lObjTrnShowBill = new TrnShowBill(); 
            		 lObjTrnShowBill = (TrnShowBill) inputMap.get("ShowBillVO");
            		 lObjTrnShowBill.setStatus(lObjTrnBillReg.getCurrBillStatus());
            		 inputMap.put("ShowBillVO", lObjTrnShowBill);
            	}
        	}
        	
        	/*
        	setSessionInfo(inputMap);
            lStrSubjectId = StringUtility.getParameter("hidBillTypeId", request).trim();
            lStrBillNo = StringUtility.getParameter("billNo", request).trim();
            lStrBillStatus = StringUtility.getParameter("billStatus", request).trim();
            if (lStrBillNo.length() == 0)
            {
                lStrBillNo = (String) inputMap.get("jobId");
            }
            inputMap.put("billNo", lStrBillNo);
            if (lStrBillNo != null && lStrBillNo.length() > 0)
                lLngBillNo = Long.parseLong(lStrBillNo);

            if (lStrSubjectId.length() > 0)
                lLngSubjectId = Long.parseLong(lStrSubjectId);
            // Once the bill is approved, it cannot be edited
            if (lStrBillStatus.length() > 0
                    && lStrBillStatus.equalsIgnoreCase(bundleConst.getString("STATUS.BillCreated")))
            {
                lStrIsEditable = "Y";
            }
            else if (lStrBillStatus.length() == 0)// print case
            {
                lStrIsEditable = null;
            }
            else
            {
                lStrIsEditable = "N";
            }
            // get Employee Details From Trn_onlinebill_emp
            TrnOnlinebillEmpDAO lObjDAO = new TrnOnlinebillEmpDAOImpl(TrnOnlinebillEmp.class, serv.getSessionFactory());
            String[] lStrBillNoArr = new String[1];
            lStrBillNoArr[0] = lStrBillNo;
            List lLstEmpDtlsVO = lObjDAO.getEmpDtlsByBillNo(lStrBillNoArr);
            // End
            ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
            List lLstBillCode = IFMSCommonServiceImpl.getLookupValues(lObjRsrcBndle.getString("CMN.BillCode"),
                    gLngLangId, inputMap);
            Object[] lObjBillCodeArray = null;
            if (lLstBillCode != null && lLstBillCode.size() > 0)
            {
                Object[] lObjTemp = lLstBillCode.toArray();
                lObjBillCodeArray = new Object[lObjTemp.length];
                for (Object lObj : lObjTemp)
                {
                    CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
                    lObjBillCodeArray[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
                }
                lObjTemp = null;
            }
            lObjOnlineBillDao = new OnlineBillDAOImpl(serv.getSessionFactory());
            lLstPK = lObjOnlineBillDao.getPKForTable(lLngBillNo);
            if (lLstPK != null && lLstPK.size() > 0)
            {
                lObjArrPK = (Object[]) lLstPK.get(0);

                lObjTrnBillRegisterDAO = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
                lObjTrnBillRegister = (TrnBillRegister) lObjTrnBillRegisterDAO.read(lLngBillNo);

                lObjTrnBudHdDtlsDAO = new BdgtHeadDtlsDAOImpl(TrnBillBudheadDtls.class, serv.getSessionFactory());
                lObjTrnBudHdDtls = (TrnBillBudheadDtls) lObjTrnBudHdDtlsDAO.read((Long) lObjArrPK[1]);
            }
            lObjTrnBillRmrks = lObjOnlineBillDao.getRmrksForCurrUser(lLngBillNo, gLngUserId);
            lLstRltBillPartyVO = lObjOnlineBillDao.getBillPartyRltInfo(lLngBillNo);
            if (lLngSubjectId != null)
            {
                lLngBillType = lLngSubjectId;
            }
            if (lLngBillType == null)
            {
                lLngBillType = lObjTrnBillRegister.getSubjectId();
                lStrPPONo = lObjTrnBillRegister.getPpoNo();
            }
            inputMap.put("PPONo", lStrPPONo);
            inputMap.put("subjectId", lLngBillType); 
            ConfigOnlineBill lObjConfigBill = ConfigOnlineBillUtil.getInstance(lObjTrnBillRegister.getSubjectId(), serv);
            if(lStrPPONo == null)
            {
            	lStrIsConfigStatus = "Y"; 
                lStrPagePath = "/WEB-INF/jsp/pension/monthlyPensionBill.jsp" ;
                lStrBillSpecSrvcName = "VIEW_SAVED_MONTHLY_BILL"; 
            }
            else
            {
            	lStrIsConfigStatus = lObjConfigBill.getIsConfigured();
                lStrPagePath = lObjConfigBill.getJspPath();
                lStrBillSpecSrvcName = lObjConfigBill.getFetchBillDataSrvc();
            }
//          Calling bill specific method to get bill specific data
            ResultObject resultObj = serv.executeService(lStrBillSpecSrvcName, inputMap);
            inputMap.put("BillDtls", (Map) resultObj.getResultValue());            
            lResultObj = serv.executeService("EDP_DETAILS", inputMap);  //PENSION_BUD_EDP_DETAILS
            lMap = (Map) lResultObj.getResultValue();
            rcptObjHds = (List) lMap.get("rcptObjHdsAdded");
            expObjHds = (List) lMap.get("expObjHds");
            expEdpList = (List) lMap.get("expEdpList");
            inputMap.put("LangId", gLngLangId);
            lResultObj = serv.executeService("GET_MONTH_DTLS", inputMap);
            lMap = (Map) lResultObj.getResultValue();
            lResultMap = (Map) lMap.get("ResultMap");
            lMonthList = (List) lResultMap.get("MonthList");
            
            lStrGONGO = bundleConst.getString("Lookup.GO_NGO");
            lStrFund = bundleConst.getString("Lookup.Fund");
            lStrClassOfExp = bundleConst.getString("Lookup.ClassOfExpenditure");
            lStrTcBill = bundleConst.getString("Lookup.TCBillType");
            lStrBudgetType = bundleConst.getString("Lookup.BudgetType");

            CmnLookupMstDAO lDAOCmnLkUpMst = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
            List lListFund = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang(lStrFund, gLngLangId);
            List lListClassOfExp = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang(lStrClassOfExp, gLngLangId);
            List lBudjetType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang(lStrBudgetType, gLngLangId);

            Object[] lObjBudjetTypeArray = null;
            if (lBudjetType != null && lBudjetType.size() > 0)
            {
                Object[] lObjTemp = lBudjetType.toArray();
                lObjBudjetTypeArray = new Object[lObjTemp.length];

                for (Object lObj : lObjTemp)
                {
                    CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
                    lObjBudjetTypeArray[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
                }
                lObjTemp = null;
            }
            List lEmpType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang(lStrGONGO, gLngLangId);
            List lBillType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang(lStrTcBill, gLngLangId);
            if (lObjTrnBillRegister != null && lObjTrnBudHdDtls != null)
            {
                inputMap.put("demandCode", lObjTrnBillRegister.getDemandCode());
                inputMap.put("majorHead", lObjTrnBillRegister.getBudmjrHd());
                inputMap.put("subMajorHead", lObjTrnBudHdDtls.getBudSubmjrHd());
                inputMap.put("minorHead", lObjTrnBudHdDtls.getBudMinHd());
                inputMap.put("subHead", lObjTrnBudHdDtls.getBudSubHd());
                inputMap.put("budgetType", lObjTrnBudHdDtls.getBudType() + "");
                inputMap.put("ddoCode", lObjTrnBillRegister.getDdoCode());
                
                ResultObject lObjResult = serv.executeService("GET_DDO_EXP", inputMap);
                
                lResultMap = (Map) lObjResult.getResultValue();
                lLngDDOExp = (BigDecimal) lResultMap.get("TotalDDOExp");
                BigDecimal lBDGrntAmt = lObjTrnBillRegister.getGrantAmount();
                BigDecimal lBDNetAmt = lObjTrnBillRegister.getBillNetAmount();
                if (lLngDDOExp != null && lBDNetAmt != null && lBDGrntAmt != null)
                {
                    lLngDDOExp = lLngDDOExp.add(lBDNetAmt);
                    lLngBalance = lBDGrntAmt.subtract(lLngDDOExp);
                }
                lStrBillCntrNo = lObjTrnBillRegister.getBillCntrlNo();
                BillCommonDAOImpl lObj = new BillCommonDAOImpl(serv.getSessionFactory());
                lStrMonthName = lObj.getMonthName(gLngLangId, lLngBillNo);
                lMapExpClsDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBudHdDtls.getClsExp());
                lMapFundDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBudHdDtls.getFund());
                lMapBudTypeDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBudHdDtls.getBudType());
                if (lObjTrnBillRegister.getTcBill() != null)
                {
                    lMapBillTypeDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBillRegister.getTcBill());
                }
                if (lObjTrnBillRegister.getGoNgo() != null && !lObjTrnBillRegister.getGoNgo().equals("-1"))
                {
                    lMapEmpTypeDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBillRegister.getGoNgo());
                }
                if (lObjTrnBillRegister.getScannedDocId() != null)
                {
                    CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv
                            .getSessionFactory());
                    CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(lObjTrnBillRegister
                            .getScannedDocId());
                    inputMap.put("scan", cmnAttachmentMst);
                }
            }
            lObjShowBillDaoImpl = new ShowBillDAOImpl(TrnShowBill.class, serv.getSessionFactory());
            lStrCurrUsrRmrks = lObjShowBillDaoImpl.setLastRemarks(lLngBillNo.toString(), gLngUserId);
            // Objection
            TrnShowBill lObjTrnShowBill = new TrnShowBill();
            List lLstObj = lObjShowBillDaoImpl.getSelectedObj(lStrBillNo, gLngUserId);
            String lStrObj = "~";
            for (int i = 0; i < lLstObj.size(); i++)
            {
                lStrObj = lStrObj + "~" + lLstObj.get(i);
            }
            lObjTrnShowBill.setObjections(lStrObj);
            PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
            lObjTrnShowBill.setObjCount(lObjPhyBillDAOImpl.getObjectionsForUser("", Long.parseLong(lStrBillNo)));
            inputMap.put("ShowBillVO", lObjTrnShowBill);
            // End of Objection
            // Changed : 12/11/2007/ - Amit - To Get DDO NO(Drawing Officer) and
            // Treasury Information On GTR Format
            String lStrTrsyCode = lObjTrnBillRegister.getTsryOfficeCode();
            CmnLocationMstDAO lObjCmnLocationMstDAO = new CmnLocationMstDAO();
            if (lStrTrsyCode != null)
            {
                CmnLocationMst lObjCmnLocationMstVO = lObjCmnLocationMstDAO.getLocationVOByLocationCodeAndLangId(
                        lStrTrsyCode, gLngLangId);

                String lStrTrsyName = lObjCmnLocationMstVO.getLocName();
                String lStrDistCode = lObjCmnLocationMstVO.getLocShortName();
                inputMap.put("DistCode", lStrDistCode);
                inputMap.put("TrsyName", lStrTrsyName);
            }
            DDOInfoDAO lDDOInfo = new DDOInfoDAOImpl(serv.getSessionFactory());
            List lListDDO = lDDOInfo.getDDOInfo(lObjTrnBillRegister.getDdoCode(), gLngLangId, gLngDBId);
            VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class, serv.getSessionFactory());
            String lStrStatus = bundleConst.getString("STATUS.BillApprovedByAuditor");
            if (lListDDO != null && lListDDO.size() > 0)
            {
                OrgDdoMst lOrgDdoMstVO = (OrgDdoMst) lListDDO.get(0);
                inputMap.put("OrgDdoMstVO", lOrgDdoMstVO);
            }
            if (lObjTrnBillRegister.getCurrBillStatus().equalsIgnoreCase(lStrStatus))

            {
                Long lLngTrsyPassedAmt = lObjTrnBillRegister.getBillNetAmount().longValue();
                Date lDtChkDispDt = lObjTrnBillRegister.getChequeDispDt();
                inputMap.put("ChkDispDt", lDtChkDispDt);
                inputMap.put("TrsyPassedAmt", lLngTrsyPassedAmt);
            }
            List lLstVoucherVO = voucherImpl.getVoucherVO(Long.parseLong(lStrBillNo), gStrLocId);
            if (lLstVoucherVO != null && lLstVoucherVO.size() > 0)
            {
                TrnVoucherDetails lTrnVoucherDetailsVO = (TrnVoucherDetails) lLstVoucherVO.get(0);
                Long lLngVoucherNo = lTrnVoucherDetailsVO.getVoucherNo();
                SimpleDateFormat lSdf = new SimpleDateFormat("yyyy");
                String lStrVoucherYr = lSdf.format(lTrnVoucherDetailsVO.getVoucherDate());
                lSdf = new SimpleDateFormat("MMMMM");
                String lStrVoucherMonth = lSdf.format(lTrnVoucherDetailsVO.getVoucherDate());
                inputMap.put("VoucherNo", lLngVoucherNo);
                inputMap.put("VoucherMonth", lStrVoucherMonth);
                inputMap.put("VoucherYr", lStrVoucherYr);
            }
            OnlineBillDAOImpl lObjOnlineBillDAOImpl = new OnlineBillDAOImpl(serv.getSessionFactory());
            List lLstMvmntVOForAudAprvdDt = lObjOnlineBillDAOImpl.getBillMvmntVO(Long.parseLong(lStrBillNo),
                    bundleConst.getString("STATUS.BillApprovedByAuditor"));
            List lLstMvmntVOForTrsyInwdDt = lObjOnlineBillDAOImpl.getBillMvmntVO(Long.parseLong(lStrBillNo),
                    bundleConst.getString("STATUS.BillInwarded"));

            if (lLstMvmntVOForAudAprvdDt != null && lLstMvmntVOForAudAprvdDt.size() > 0)
            {
                TrnBillMvmnt lObjTrnBillMvmntVO = (TrnBillMvmnt) lLstMvmntVOForAudAprvdDt.get(0);
                Date lDtTrsyBillAprvddt = lObjTrnBillMvmntVO.getStatusUpdtDate();

                inputMap.put("TrsyBillAprvddt", lDtTrsyBillAprvddt);
            }
            if (lLstMvmntVOForTrsyInwdDt != null && lLstMvmntVOForTrsyInwdDt.size() > 0)
            {
                TrnBillMvmnt lObjTrnBillMvmntVO = (TrnBillMvmnt) lLstMvmntVOForTrsyInwdDt.get(0);
                Date lDtTrsyBillInwddt = lObjTrnBillMvmntVO.getStatusUpdtDate();

                inputMap.put("TrsyBillInwrdDate", lDtTrsyBillInwddt);
            }
    		String lStrCurrentBillStatus = lObjTrnBillRegister.getCurrBillStatus();
    		String lStrIsShowObjection = null;
    		
    		lObjRsrcBndle = ResourceBundle.getBundle("resources/onlinebillprep/OnlineBillConstants");
    		String lStrBillCrtd = lObjRsrcBndle.getString("STATUS.BillCreated");
    		String lStrBillAprvdDDO = lObjRsrcBndle.getString("STATUS.BillApproved");
    		String lStrBillDiscarded = lObjRsrcBndle.getString("STATUS.BillDiscarded");
    		String lStrBillSentToTO = lObjRsrcBndle.getString("STATUS.BillSentToTO");
    		
    		if(lStrCurrentBillStatus != null && !lStrCurrentBillStatus.equals(lStrBillCrtd) 
    				&& !lStrCurrentBillStatus.equals(lStrBillAprvdDDO)
    				&& !lStrCurrentBillStatus.equals(lStrBillDiscarded) 
    				&& !lStrCurrentBillStatus.equals(lStrBillSentToTO))
    		{
    			lStrIsShowObjection = "Y";
    		}
    		else
    		{
    			lStrIsShowObjection = "N";
    		}
            inputMap.put("EmpDtlsList", lLstEmpDtlsVO);
            inputMap.put("TrnBillRegister", lObjTrnBillRegister);
            inputMap.put("TrnBillBudheadDtls", lObjTrnBudHdDtls);
            inputMap.put("TrnBillRemarks", lObjTrnBillRmrks);
            inputMap.put("RltBillParty", lLstRltBillPartyVO);
            inputMap.put("billStatus", lObjTrnBillRegister.getCurrBillStatus());
            inputMap.put("currRemarks", lStrCurrUsrRmrks);
            inputMap.put("BillNo", lLngBillNo);
            inputMap.put("MonthList", lMonthList);
            inputMap.put("PagePath", lStrPagePath);
            inputMap.put("FundCombo", lListFund);
            inputMap.put("ClassOfExpCombo", lListClassOfExp);
            inputMap.put("BudTypeCombo", lObjBudjetTypeArray);
            inputMap.put("TotalExpenditure", lLngDDOExp);
            inputMap.put("AvailableBalance", lLngBalance);
            inputMap.put("rcptObjHdsAdded", rcptObjHds);
            inputMap.put("expObjHds", expObjHds);
            inputMap.put("expEdpList", expEdpList);
            inputMap.put("BillCntrNo", lStrBillCntrNo);
            inputMap.put("EditBill", lStrIsEditable);
            inputMap.put("MonthName", lStrMonthName);
            inputMap.put("ClassOfExp", (String) lMapExpClsDtls.get("Label"));
            inputMap.put("TypeOfBudget", (String) lMapBudTypeDtls.get("Label"));
            inputMap.put("BillCodeArray", lObjBillCodeArray);
            inputMap.put("Selected_ExpCls", lMapExpClsDtls);
            inputMap.put("Selected_Fund", lMapFundDtls);
            inputMap.put("Selected_BudType", lMapBudTypeDtls);
            inputMap.put("Selected_BillType", lMapBillTypeDtls);
            inputMap.put("Selected_EmpType", lMapEmpTypeDtls);
            inputMap.put("BillTypeId", lLngBillType);
            inputMap.put("BillType", lBillType);
            inputMap.put("EmpType", lEmpType);
            inputMap.put("isNewFromRejected", lStrIsNewFromRejected);
            inputMap.put("SelReqId", StringUtility.getParameter("hidSelRqstId", request).trim());
            inputMap.put("isConfigStatus", lStrIsConfigStatus);
            inputMap.put("isFromChqPrep", StringUtility.getParameter("isChq", request).trim());
            inputMap.put("isShowObjection", lStrIsShowObjection);
            
            Object prevBillNo = (Object) inputMap.get("prevBillNo");
            inputMap.put("oldBillCntrlNo", prevBillNo != null ? lObjPhyBillDAOImpl.read(
                    Long.parseLong(prevBillNo.toString())).getBillCntrlNo() : null);
            request.getSession().setAttribute("VerifyMap", inputMap);*/  //Deep
        
        	lLngBillNo = Long.valueOf((String) inputMap.get("billNo"));
        	
        	CommonPensionDAOImpl lObjCommonDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
        	TrnBillMvmnt lObjTrnBillMvmt = lObjCommonDAO.getBillMvmntVo(lLngBillNo);
            
            if(lObjTrnBillMvmt != null)
            {
                //lStrCurrUsrRmrks = lObjTrnBillMvmt.getBillRemarks();
                //lStrCurrObjRemarks = lObjTrnBillMvmt.getObjRemarks();
            }
            inputMap.put("currRemarks", lStrCurrUsrRmrks);
            inputMap.put("currObjRemarks", lStrCurrObjRemarks);
            AuditorObjectionServiceImpl lObjObjectionServ = new AuditorObjectionServiceImpl(); 
           ResultObject lRes = null;//lObjObjectionServ.getObjDetails(inputMap);
            Map lMapRes   = (Map) lRes.getResultValue();
              List lLstObjection = (List) lMapRes.get("ObjectionList");
        	objRes.setViewName("createPensionBills");
            /*if (lStrIsEditable != null)
            {
                //inputMap = getSelectedMonthDtls(inputMap);
                objRes.setViewName("createPensionBills");
            }
            else
            {
                objRes.setViewName(lObjConfigBill.getGtrFormatView());
            }*/ //Deep
        	inputMap.put("isShowObjection","Y");
        	inputMap.put("ObjectionList", lLstObjection);
            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
        }

        return objRes;
    }
    
    /**
     *  Getting a Calimant info regarding to current location and post.
     *  
     *  @author Sagar Patel.
     * 
     */
    public List getClaimantInfo(Map inputMap) throws Exception
    {    	
    	List lLstReturn = new ArrayList();
        try
        {
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            setSessionInfo(inputMap);
            Long lClaimantpostID = gLocId;
            PensionBillDAO objPensionBillDAO = new PensionBillDAOImpl(serv.getSessionFactory());
            lLstReturn =  objPensionBillDAO.getClaimantDDOInfo(lClaimantpostID);
        }
        catch (Exception e)
        {
        	gLogger.error("Error is : ", e);
            throw(e);
        }

        return lLstReturn;    	
    }
    
    /**
     * Validate CVP , DCRG , Pension Bills Token No.
     * 
     * @author sagar.
     *  
     */
    
    public ResultObject validatePensionBillsTokenNo(Map inputMap)
    { 
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        try
        {
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            setSessionInfo(inputMap);
            String lPnsnBillTokenNo = StringUtility.getParameter("pnsnBillToken", request).trim();
            OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,serv.getSessionFactory());
            Long lLngTokenNo = Long.parseLong(lPnsnBillTokenNo);
            boolean tokenFlag = lObjTokenStatusDAO.isValidateToken(lLngTokenNo, gStrLocId);
            StringBuilder lStrGrant = new StringBuilder();
			lStrGrant.append(" <TOKENRES> ");
			lStrGrant.append(tokenFlag);
			lStrGrant.append(" </TOKENRES> ");
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
    public ResultObject getSavedPensionBills(Map lInputMap)
    {
        ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) lInputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lInputMap.get("requestObj");
        String lStrMyBills = null;
        String lStrSubjectRegular = bundleConst.getString("OnlineBillSubject");
        try
        {
            setSessionInfo(lInputMap);

        	getPensionHierarchyUsers(lInputMap,lStrSubjectRegular);
        	Short lStrCurStatus = getStatusByLevel(Short.valueOf(lInputMap.get("svdBillCurLevel").toString()));
            lInputMap.put("savedBillsStatus", lStrCurStatus);
          
            lStrMyBills = getSavedPnsnBills(lInputMap);
            lObjResult.setResultValue(lInputMap);
            PensionBillDAOImpl lObjPnsnBillDao  = new PensionBillDAOImpl(serv.getSessionFactory());
           
            if((request.getParameter("isCountr") != null && request.getParameter("isCountr").equalsIgnoreCase("Y")) || (lInputMap.get("isCountr") != null && lInputMap.get("isCountr").equals("Y")))
            {
                lInputMap.put("isCountr","Y");
            }
            if((request.getParameter("isAudit") != null && request.getParameter("isAudit").equalsIgnoreCase("Y")) || (lInputMap.get("isCountr") != null && lInputMap.get("isCountr").equals("Y")))
            {
                lInputMap.put("isAudit","Y");
            }
            if((request.getParameter("AprvdBills") != null && request.getParameter("AprvdBills").equalsIgnoreCase("Y")) || (lInputMap.get("AprvdBills") != null && lInputMap.get("AprvdBills").equals("Y")))
            {
            	// lInputMap.put("savedBillsStatus", DBConstants.ST_BAPRV_AUD);
            }
            List<SavedPensionBillVO> dataList = lObjPnsnBillDao.getMyBills(gLngUserId,gLngLangId,lStrMyBills,lInputMap);
            if (request.getParameter("cmbSearch") != null)
            {
                lInputMap.put("srchStr", request.getParameter("cmbSearch"));
            }
            lInputMap.put("userList", lInputMap.get("userList"));
            if(dataList.size() > 0)
            {
                lInputMap.put("savedBills", dataList);
                lInputMap.put("ListSize", dataList.size());
            }
            lObjResult.setResultValue(lInputMap);
            if(lInputMap.get("svdBillCurLevel") != null && Integer.parseInt(lInputMap.get("svdBillCurLevel").toString()) == 30 )
            {
                lObjResult.setViewName("savedPensionBillsCntr");
            }
            else
            {
                lObjResult.setViewName("savedPensionBills");
            }
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
    private String getSavedPnsnBills(Map inputMap) throws Exception
    {
        WorkFlowVO workFlowVO = new WorkFlowVO();
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();
        String lStrBillNo = null;
        WfDocMvmntMstVO lWfDocMvmntVO = null;
        StringBuffer lStrMyBills = new StringBuffer();

        List list = new ArrayList();
        List resultLst = null;
        List caseList = new ArrayList();
        String lStrFinalString  = new String();
        try
        {
            workFlowVO.setAppMap(inputMap);
            workFlowVO.setCrtEmpId(gLngUserId.toString());
            workFlowVO.setCrtPost(gLngPostId.toString());
            workFlowVO.setCrtUsr(gLngUserId.toString());
            workFlowVO.setConnection(conn);
           // workFlowVO.setActId(DBConstants.WF_ActionId_Create);
           // workFlowVO.setDocId(Long.parseLong(DBConstants.WF_DocId_OnlineBill));
            workFlowVO.setJobRefId("1");
            workFlowVO.setLocID(gStrLocId);
            workFlowVO.setDbId(gLngDBId);

            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
            // adding the posts
            list.add(gLngPostId.toString());
            resultLst = orgUtil.getDocList(list); // returns list of documents
            // based on post
            if (resultLst != null && resultLst.size() > 0)
            {
                for (int lCount = 0; lCount < resultLst.size(); lCount++)
                {
                    WfDocMvmntMstVO lObjDocMvmntMst = (WfDocMvmntMstVO) resultLst.get(lCount);
                    caseList.add(lObjDocMvmntMst.getJobRefId());
                }
            }
            int ChangedListSize = caseList.size();
            int j = 999;
            String lStrnew = new String();
            int LsitSize = caseList.size();
    		StringBuffer lSb = new StringBuffer();
            for(int k = 0; k < LsitSize;k++)
        	{
            	if(k >= j && ChangedListSize >= 999)
    			{
    				lSb.append("("+lStrnew+")");
    				lSb.append("~");
    				lStrnew = new String();
    				if(j+999 <= LsitSize)
    				{
    					j = j+999;
    				}
    				ChangedListSize = ChangedListSize - 999;
    			}
    			lStrnew = lStrnew+"'"+caseList.get(k)+"',";
        	}
            if(lSb != null && lStrnew!= null)
    		{
    			lSb.append("("+lStrnew+")");
    		}
            lStrFinalString = lSb.toString().replace("',)", "')");
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lStrFinalString;
    }
    
    public void getPensionHierarchyUsers(Map objectArgs,String lStrSubjectRegular) throws Exception
    {
        int llFromLevelId = 0;
        String lStrFromLevel = "";
        List resultList = null;
        List rsltLstReg = null;
        List nillUsrList = null;
        List listSameLvlUser = null;
        List NillBillresultList = null;
        List nillBillUsrLsit = null;
        Long lLngHeirRefId = null;
        String MRFlag = "";

        //String lStrSubjectRegular = bundleConst.getString("OnlineBillSubject");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        try
        {
            setSessionInfo(objectArgs);   
            WorkFlowVO workFlowVO = new WorkFlowVO();
            workFlowVO.setAppMap(objectArgs);
            workFlowVO.setConnection(conn);
            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
            resultList = (List) orgUtil.getHierarchyByPostIDAndDescription(gLngPostId.toString(), lStrSubjectRegular).get(
                    "Result");
            lLngHeirRefId = Long.parseLong(resultList.get(0).toString());
            if(request.getParameter("caseFromlevel") != null)
            {
            	lStrFromLevel = request.getParameter("caseFromlevel") .toString();
            }
            else if(request.getParameter("BillFromlevel") != null)
            {
            	lStrFromLevel = request.getParameter("BillFromlevel") .toString();
            }
            else if(request.getParameter("MRRequestFromlevel") != null)
            {
            	lStrFromLevel = request.getParameter("MRRequestFromlevel") .toString();
            }
            if(lStrFromLevel.length()>0)
            {
            	 llFromLevelId = Integer.parseInt(lStrFromLevel);
            }
            if(request.getParameter("AprvdBills")!= null && request.getParameter("AprvdBills").equals("Y"))
            {
            	rsltLstReg = orgUtil.getToNodeListFromAlterHir(gLngPostId.toString(), lLngHeirRefId, "Approve");
            	llFromLevelId = 70;
            	
            	NillBillresultList = (List) orgUtil.getHierarchyByPostIDAndDescription(gLngPostId.toString(), "Nill PensionBill Processing").get(
                "Result");
            	nillUsrList = orgUtil.getToNodeListFromAlterHir(gLngPostId.toString(), Long.parseLong(NillBillresultList.get(0).toString()), "Approve");
            	nillBillUsrLsit = getUserList(nillUsrList,serv,objectArgs);
            	objectArgs.put("AprvdBills", request.getParameter("AprvdBills").toString());
            }
            else
            {
            	 rsltLstReg = (List) orgUtil.getUpperPost(gLngPostId.toString(), lLngHeirRefId, llFromLevelId).get("Result");
            }
            objectArgs.put("svdBillCurLevel", llFromLevelId);
            if(lStrSubjectRegular.equalsIgnoreCase(bundleConst.getString("OnlineBillSubject").toString()))
            {
            	 listSameLvlUser = getUserList(rsltLstReg, serv, objectArgs);
            }
            else
            {
            	if(request.getParameter("MRRequestFromlevel") != null)
            	{
            		 MRFlag = "Y";
            	}
            	else
            	{
            		MRFlag = "N";
            	}
            	listSameLvlUser = getCaseUsersList(rsltLstReg, serv, objectArgs,MRFlag);
            }
           
            objectArgs.put("CurrentLevel",((Object[])rsltLstReg.get(0))[1]);
            objectArgs.put("userList", listSameLvlUser);
            objectArgs.put("currentUserPost", gLngPostId.toString());
            if(nillBillUsrLsit!= null && nillBillUsrLsit .size() > 0)
            {
            	objectArgs.put("NillBillusersList", nillBillUsrLsit);
            }
        }
        catch (AlternateHierarchyNotfoundException e)
        {
            gLogger.error(" Error occured while getting alternate hierarchy ..." + e, e);
            throw e;
        }
        catch (UpperPostNotFoundException e)
        {
        	gLogger.error(" Error occured while getting upper post... " + e, e);
            throw e;
        }
        catch (Exception e)
        {
        	gLogger.error(" Error occured while getting upper post... " + e, e);
            throw e;
        }
    }
    public ResultObject getSendToOthrs(Map orgsMap)
    {
    	 int llFromLevelId = 0;
         ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
         String lStrHirarchyRefId = null;
         ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
         HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");
         PensionBillDAOImpl lObjPensnBilldao = new PensionBillDAOImpl(serv.getSessionFactory());
         String strBillType = null;
         String lStrOtherType = null;
         try
         {
        	 setSessionInfo(orgsMap);
        	 if(request.getParameter("FrwdType") != null)
        	 {
        		 lStrOtherType = request.getParameter("FrwdType");
        	 }
        	 WorkFlowVO workFlowVO = getDefaultWorkFlowVO(orgsMap);
             WorkFlowInterfaceImpl wfImpl = new WorkFlowInterfaceImpl(workFlowVO);
        		  
        	  OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
        	  Map resultMap = orgUtil.getHierarchyByPostIDAndDescription(
                      SessionHelper.getPostId(orgsMap).toString(), bundleConst.getString("PENSION.BILLSUBJECT"));
              List resultList = (List) resultMap.get("Result");
              
              Long lLngHeirRefId = new Long(0);
              if (resultList != null && resultList.size() > 0)
              {
            	  lLngHeirRefId = Long.parseLong(resultList.get(0).toString());
              }
              lStrHirarchyRefId = lLngHeirRefId.toString();
    		  String lStrFromLevel = StringUtility.getParameter("FromLevel", request);
    		  if(lStrFromLevel != null && lStrFromLevel.length()>0)
    		  {
    			  llFromLevelId = Integer.parseInt(lStrFromLevel);
    		  }
    		  if(lStrOtherType != null && lStrOtherType.length()>0)
    		  {
    			  List lLstTemp =  lObjPensnBilldao.getOtherUsersList(String.valueOf(llFromLevelId) ,lStrHirarchyRefId,lStrOtherType,gLngPostId.toString(),"PPO",bundleConst.getString("PENSION.BILLSUBJECT"));
                  if(lLstTemp.size()>0)
                  {
                	 orgsMap.put("RegList", lLstTemp); 
                   }
    		  }
        	  lObjResult.setResultValue(orgsMap);
        	  lObjResult.setViewName("cmnCaseSelectionFrwd");
         }
         catch (Exception e)
         {
         	gLogger.error(" Error occured while getting upper post... " + e, e);
         }
         return lObjResult;
    }
    private List getCaseUsersList(List rsltList,ServiceLocator serv,Map orgsMap,String MRFlag) throws Exception
    {
    	List listUsers = null;
        try
        {
        	PensionCaseDAOImpl lObjPnsnCaseDao = new PensionCaseDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
            if (rsltList != null)
            {
                String[] postString = new String[rsltList.size()];
                String[] levelString = new String[rsltList.size()];
                Object[] lObjNextPost = null;
                for (int i = 0; i < rsltList.size(); i++)
                {
                    lObjNextPost = (Object[]) rsltList.get(i);
                    postString[i] = lObjNextPost[0].toString();
                    levelString[i] = lObjNextPost[1].toString();
                }
                if (rsltList.size() > 0)
                {
                    listUsers = lObjPnsnCaseDao.getCaseUsersLsitgetUserFromPost(postString,levelString,gLngLangId,orgsMap,MRFlag);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error(" Error is " + e, e);
            throw e;
        }
        return listUsers;
    }
    private List getUserList(List rsltList,ServiceLocator serv,Map objectArgs) throws Exception
    {
        List listUsers = null;
        String MRFlag = "N";
        try
        {
        	PensionCaseDAOImpl lObjPnsnCaseDao = new PensionCaseDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
            if (rsltList != null)
            {
                String[] postString = new String[rsltList.size()];
                String[] levelString = new String[rsltList.size()];
                Object[] lObjNextPost = null;
                for (int i = 0; i < rsltList.size(); i++)
                {
                    lObjNextPost = (Object[]) rsltList.get(i);
                    postString[i] = lObjNextPost[0].toString();
                    levelString[i] = lObjNextPost[1].toString();
                }

                if (rsltList.size() > 0)
                {
                    listUsers = lObjPnsnCaseDao.getCaseUsersLsitgetUserFromPost(postString, levelString, gLngLangId,objectArgs,MRFlag);
                }
            }
        }
        catch (Exception e)
        {
            gLogger.error(" Error is " + e, e);
            throw e;
        }
        return listUsers;
    }
    
    public ResultObject forwardPensionBills(Map lInputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) lInputMap.get("requestObj");
        String lStrIsVitoSucced = "Y";
        try{
        	if( request.getParameter("AprvdBills") != null && request.getParameter("AprvdBills").equalsIgnoreCase("Y"))
        	{
        		lInputMap.put("AprvdBills", "Y");
        	}
        	if( request.getParameter("RejectBills") != null && request.getParameter("RejectBills").equalsIgnoreCase("Y"))
        	{
        		lInputMap.put("RejectBills", "Y");
        	}
            if(request.getParameter("isAudit") != null && request.getParameter("isAudit").equalsIgnoreCase("Y"))
            {
                  lInputMap.put("isAudit","Y");
            }
            if(request.getParameter("isCountr") != null && request.getParameter("isCountr").equalsIgnoreCase("Y"))
            {
                lInputMap.put("isCountr","Y");
            }
        	if(request.getParameter("isVito") != null && request.getParameter("isVito").toString().equalsIgnoreCase("Y"))
        	{
        		lStrIsVitoSucced = "N";
        		objRes =  generatePensionVito(lInputMap);
        		lInputMap = (Map) objRes.getResultValue();
        		if(lInputMap.get("vitoMessage") != null)
        		{
        			lStrIsVitoSucced = "Y";
        		}
        	}
        	if(lStrIsVitoSucced.equalsIgnoreCase("Y"))
        	doBillMovement(lInputMap);
        	
        	objRes = getSavedPensionBills(lInputMap);
            Map temp = (Map) objRes.getResultValue();
            objRes.setResultValue(temp);
        }
        catch(Exception e)
        {
        	//BptmCommonServiceImpl.setErrorProperties(gLogger, objRes, e,
            //"Error occured while forwarding pension bills...");
        }
        return objRes;
    }
    public ResultObject generatePensionVito(Map objectArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        try
        {
            setSessionInfo(objectArgs);
            ReportServiceImpl reportServiceImpl = new ReportServiceImpl();
            String vitoCode = "";
            String vitoMessage = "";
            String printString = "";
            Map printMap = new HashMap();
            boolean isCrdxFwd = false;
            if(request.getParameter("forAudit") != null)
            {
                if("Y".equalsIgnoreCase(request.getParameter("forAudit").toString()))
                {
                    
                    printMap = genBillTranRegForAudit(objectArgs);
                    vitoCode = (String) printMap.get("vitoCode");
                    if (vitoCode != null) 
                    {
                        vitoMessage = "Vito Code " + vitoCode + " Generated Successfully";
                        printString = (String) printMap.get("printString");
                    }
                }
            }
            else
            {
                //printMap = reportServiceImpl.genBillTranRegAfterAudit(objectArgs);
                vitoCode = (String) printMap.get("vitoCode");
                if (vitoCode != null)
                {
                    vitoMessage = "Vito Code " + vitoCode + " Generated Successfully";
                    printString = (String) printMap.get("printString");
                }
            }
            objectArgs.put("vitoMessage", vitoMessage);
            objectArgs.put("printString", printString);
            objRes.setResultValue(objectArgs);
        }
        catch (Exception e)
        {
           gLogger.error("Error is"+e);
        }
        return objRes;
    }
    public Map genBillTranRegForAudit(Map objectArgs) 
    {       
        HashMap printMap = new HashMap();
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(DDODetailsVO.class, serv.getSessionFactory());
        BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(BillVitoRegister.class, serv.getSessionFactory());
        HttpSession session = request.getSession();
        String arrSelectedBills[] = null;
        String strSelectedBills=null;
        String vitoType = null;
        ArrayList vitoSummaryList = new ArrayList();
        String vitoCode = null;     
        Map vitoMap = new HashMap();
        String returnVitoCode = null;
        String printString = "";
        int i,j=0;
        BillVitoRegister vitoVO = new BillVitoRegister();
        String[] arrChkBox;
        Map billPostMap = new HashMap();
        Map audPostMap = new HashMap();
        String strPostIds = "";
        TrnBillRegister trnBillReg = new TrnBillRegister();
        try
        {
            setSessionInfo(objectArgs);
            arrChkBox = (String[])request.getParameterValues("chkbox");
            arrSelectedBills = new String[arrChkBox.length];
            PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
            for(j=0;j<arrChkBox.length;j++)
            {
                String[] tempArr = arrChkBox[j].split("~");
                arrSelectedBills[j] = tempArr[0];
                if(j == 0){strSelectedBills = tempArr[0];}
                else{strSelectedBills = strSelectedBills + "," + tempArr[0];}
                
                String billAudPost = request.getParameter("cmb_"+arrSelectedBills[j]);
                if(strPostIds.equals("")){
                    strPostIds = billAudPost;
                }else{
                    strPostIds = strPostIds + "," + billAudPost;
                }
                billPostMap.put(arrSelectedBills[j], billAudPost);   
                if( objectArgs.get("isCountr") != null && "Y".equalsIgnoreCase(objectArgs.get("isCountr").toString()))
                {
                    trnBillReg = pbDaoImpl.read(Long.valueOf(arrSelectedBills[j].toString()));
                    String lStrBillNo2 = String.valueOf(trnBillReg.getBillNo());
                    if(trnBillReg.getTokenNum() == null || trnBillReg.getTokenNum() == 0 )
                    {
                        if(request.getParameter(lStrBillNo2) != null && request.getParameter(lStrBillNo2).toString().length() >0 )
                        {
                            trnBillReg.setTokenNum(Long.valueOf(request.getParameter(lStrBillNo2).toString()));
                        } 
                        pbDaoImpl.update(trnBillReg);
                        OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,serv.getSessionFactory());
                        Long lLngPnsnBillNo = trnBillReg.getBillNo();
                        //lObjTokenStatusDAO.updateTokenStatus(trnBillReg.getTokenNum(), SessionHelper.getLocationCode(objectArgs), lLngPnsnBillNo, SessionHelper.getUserId(objectArgs), SessionHelper.getPostId(objectArgs),BillProcConstants.INWARD_TYPE_BILL);
                        CommonPensionDAOImpl lObjCommon = new CommonPensionDAOImpl(serv.getSessionFactory());
                        Long lLngCaseId = lObjCommon.getPKForRltpensionCaseBill(trnBillReg.getBillNo());
                        Long lLngHdrId = lObjCommon.getPKForBillHdr(trnBillReg.getBillNo());
                        if(lLngCaseId != null && lLngCaseId != 0L )
                        {
                     	   RltPensioncaseBillDAO LobjBillcase = new  RltPensioncaseBillDAOImpl(RltPensioncaseBill.class,serv.getSessionFactory());
                     	   RltPensioncaseBill lObjRltPensioncaseBill = LobjBillcase.read(Long.valueOf(lLngCaseId));
                            lObjRltPensioncaseBill.setTokenNo(trnBillReg.getTokenNum());
                            LobjBillcase.update(lObjRltPensioncaseBill);
                        }
                        if(lLngHdrId != null && lLngHdrId != 0L )
                        {
                            TrnPensionBillHdrDAO lObjBillHdr = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,serv.getSessionFactory());
                            TrnPensionBillHdr lObjTrnPensionBillHdr = lObjBillHdr.read( Long.valueOf(lLngHdrId));
                            lObjTrnPensionBillHdr.setTokenNo(trnBillReg.getTokenNum().toString());
                            lObjBillHdr.update(lObjTrnPensionBillHdr);
                        }
                    }
                }
            }                   
            if(strPostIds != null && !strPostIds.equals("")){
                //audPostMap = reportImpl.getAudNames(strPostIds, gLngLangId.toString());
            }
            
            //vitoType = DBConstants.VITO_BILL_TRANSREG_FOR_AUD;
            if(strSelectedBills != null)
            {
                PensionBillDAOImpl lObjPnsnBillDao  = new PensionBillDAOImpl(serv.getSessionFactory());
                vitoMap = lObjPnsnBillDao.genBillTranRegForAudit(gLngUserId, vitoType,strSelectedBills,SessionHelper.getLocationCode(objectArgs),gLngLangId, gLngPostId,billPostMap,audPostMap);
                vitoSummaryList = (ArrayList)vitoMap.get("summaryList");
                vitoMap.remove("summaryList");              
                List vitoDataList = (ArrayList)vitoMap.get("vitoDataList");
                String userName = null;//reportImpl.getUserNameforID(gLngUserId);
               // vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
                String districtName = null;//reportImpl.getDistrictFromLocation(SessionHelper.getLocationCode(objectArgs), gLngLangId.toString());
               // printString = reportImpl.printUtilityForAudit(vitoDataList,vitoSummaryList,userName,vitoCode,districtName);                           
                //gLogger.info("print String before audit vito : "+printString);
                
                i=0;
                for(i=0;i<arrSelectedBills.length;i++)
                {                   
                    Long vitoId = IFMSCommonServiceImpl.getNextSeqNum("bill_vito_register", objectArgs);
                    vitoVO = new BillVitoRegister();
                    vitoVO.setVitoId(vitoId);
                    //vitoVO.setVitoCode(vitoCode);
                    vitoVO.setVitoType(vitoType);
                    vitoVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                    vitoVO.setBillNo(Long.parseLong(arrSelectedBills[i]));
                    vitoVO.setCreatedDate(new Date(System
                            .currentTimeMillis()));
                    vitoVO.setCreatedUserId(gLngUserId);
                    vitoVO.setCreatedPostId(gLngPostId);
                    vitoVO.setDbId(gLngDBId);
                    vitoDaoImpl.create(vitoVO);
                }               
            }
            if(vitoCode != null)
            {
                returnVitoCode = vitoCode+"";
            }
            printMap.put("vitoCode", returnVitoCode);
            printMap.put("printString", printString);
        }       
        catch (Exception e)
        {
            gLogger.error("Exception occured in ReportServiceImpl.genBillTranRegForAudit # \n"   + e,e);
        }           
        return printMap;
    }
     public short getStatusByLevel(Short lShrtlevel)
    {
    	short lShortUpdStatus = Short.valueOf("-1");
        if(lShrtlevel == 20)
        {
           // lShortUpdStatus = DBConstants.ST_BAPRVD_DDO;
        }
        else if(lShrtlevel == 30)
        {
            //lShortUpdStatus = DBConstants.ST_BS_TO;
        }
        else if(lShrtlevel == 40)
        {
            //lShortUpdStatus = DBConstants.ST_BCRDX;
        }
        else if(lShrtlevel == 50)
        {
        	//lShortUpdStatus = DBConstants.ST_BAUD;
        }
    	else if(lShrtlevel == 60)
         {
         	//lShortUpdStatus = DBConstants.ST_BAUD_ACC;
         }
    	
         else  if(lShrtlevel == 70)
         {
         	//lShortUpdStatus = DBConstants.ST_BAUD_ATO;
         }
         else if(lShrtlevel == 80)
         {
         	//lShortUpdStatus = DBConstants.ST_BAUD_TO;
         }
         else if(lShrtlevel == 90)
         {
         	//lShortUpdStatus = DBConstants.ST_CHQ_WRTN;
         }
         else if(lShrtlevel == 100)
         {
         	//lShortUpdStatus = DBConstants.ST_CHQ_PRNT;
         }
         else if(lShrtlevel == 110)
         {
         	//lShortUpdStatus = DBConstants.ST_CHQ_CSTDN;
         }
         else if(lShrtlevel == 120)
         {
         	//lShortUpdStatus = DBConstants.ST_CHQ_CNTR;
         }
         else if(lShrtlevel == 130)
         {
         	//lShortUpdStatus = DBConstants.ST_CHQ_DSPTCH_DDO;
         }
         else if(lShrtlevel == 140)
         {
         	//lShortUpdStatus = DBConstants.ST_VCHR_GEN;
         }
    	return lShortUpdStatus;
    }
    private void doBillMovement(Map objectArgs) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        RltLevelStatus lObjRltLvlStatus = new RltLevelStatus();
        try
        {
        	setSessionInfo(objectArgs);
        	Long postId = SessionHelper.getPostId(objectArgs);
            Long langId = SessionHelper.getLangId(objectArgs);
            Short lShortUpdStatus = null;
            Short lShortNillUpdtStatus = null;
            String lStrActionVal = request.getParameter("actionVal");
            String lStrReturnCase = null;
            String lStrBillNo = null;
            String lStrTokenNo = null;
            String lStrBudMjrHd = null;
            String lStrPpoNo = null;
            String lStrAudPostId = null;
            String lStrCurrStatus = null;
            String lStrIsPhyBill = null;
            String lStrIsTcBill = null;
            String lStrNillToPost = null;
            String lStrNillTolevel = null;
            String[] chk = null;
            String toPost = null;
            String toLevel = null;
            String[] billsNo = null;
            String lStrTxtSearch = "";
            String lStrCmbSearch = "";
            String lStrRepeatPPONo = "";

            BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv
                    .getSessionFactory());
            PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
            BillMovementDAOImpl billMvmntDAO = new BillMovementDAOImpl(TrnBillMvmnt.class, serv.getSessionFactory());
            PensionBillDAO lObjPensnBilldao = new PensionBillDAOImpl(serv.getSessionFactory());
            
            if (request.getParameterValues("chkbox") != null)
            {
                billsNo = request.getParameterValues("chkbox");
            }
            if (lStrActionVal != null && lStrActionVal.length() > 0)
            {
                /*if (lStrActionVal.equalsIgnoreCase("Approve"))
                {
                    lShortUpdStatus = DBConstants.ST_BAPRV_AUD;
                }
                else if (lStrActionVal.equalsIgnoreCase("Reject"))
                {
                    lShortUpdStatus = DBConstants.ST_BRJCT_AUD;
                }
                else 
                {
                	// TO get status based on to level 
                	if(request.getParameter("cmbFrwdUsr") != null && ! request.getParameter("cmbFrwdUsr").toString().equals("-1"))
                	{
                		toPost = getToPostAndToLvl(request.getParameter("cmbFrwdUsr")).split("~")[0];
                        toLevel = getToPostAndToLvl(request.getParameter("cmbFrwdUsr")).split("~")[1];
                        lShortUpdStatus =  getStatusByLevel(Short.valueOf(toLevel));
                	}
                	if(request.getParameter("nilCmbFrwdUsr") != null && ! request.getParameter("nilCmbFrwdUsr").toString().equals("-1"))
                	{
                		lStrNillToPost = getToPostAndToLvl(request.getParameter("nilCmbFrwdUsr")).split("~")[0];
                        lStrNillTolevel = getToPostAndToLvl(request.getParameter("nilCmbFrwdUsr")).split("~")[1];
                        lShortNillUpdtStatus = getStatusByLevel(Short.valueOf(lStrNillTolevel));
                	}
                	if( request.getParameter("AprvdBills") != null && request.getParameter("AprvdBills").equalsIgnoreCase("Y"))
                	{
                		lShortUpdStatus = DBConstants.ST_CHQ_WITH_WRTR;
                		lShortNillUpdtStatus = 	DBConstants.ST_CHQ_DSPTCH_DDO;
                	}
                   
                	if(request.getParameter("isSendToOthrs") != null && request.getParameter("isSendToOthrs").equalsIgnoreCase("Y") )
                	{
                		if(request.getParameter("hidcmbFrwdUsr") != null && request.getParameter("hidcmbFrwdUsr").length() >0 )
                		{
                			toPost = getToPostAndToLvl(request.getParameter("hidcmbFrwdUsr")).split("~")[0];
                            toLevel = getToPostAndToLvl(request.getParameter("hidcmbFrwdUsr")).split("~")[1];
                            lShortUpdStatus =  getStatusByLevel(Short.valueOf(toLevel));
                		}
                		if(request.getParameter("hidcmbNillFrwdUsr") != null && request.getParameter("hidcmbNillFrwdUsr").length() >0)
                		{
                			lStrNillToPost = getToPostAndToLvl(request.getParameter("hidcmbNillFrwdUsr")).split("~")[0];
                            lStrNillTolevel = getToPostAndToLvl(request.getParameter("hidcmbNillFrwdUsr")).split("~")[1];
                            lShortNillUpdtStatus = getStatusByLevel(Short.valueOf(lStrNillTolevel));
                		}*/
                		
                	}
                
            

            String combos[] = new String[billsNo.length];
            TrnBillMvmnt lObjBillMvmntVO = null;
            TrnBillRegister trnBillReg = null;
            boolean isPhyBill = false;
            String strAprvdBills = "";
            
            for (int i = 0; i < billsNo.length; i++)
            {
                chk = billsNo[i].split("~");
                lStrBillNo = chk[0];
                lStrTokenNo = chk[1];
                lStrPpoNo = chk[2];
                lStrAudPostId = chk[3];
                lStrIsTcBill = chk[4];
                if (lStrActionVal != null && lStrActionVal.trim().length() > 0 &&
                		(lStrActionVal.equalsIgnoreCase("Approve") || lStrActionVal.equalsIgnoreCase("Reject")))
                {
                    toPost = lStrAudPostId;
                    toLevel = BillProcConstants.AUDITOR_LEVEL;
                }
                
                if(objectArgs.containsKey("AprvdBills"))
                	strAprvdBills = objectArgs.get("AprvdBills").toString();
                
               if (lStrPpoNo != null && lStrPpoNo.length() > 0 && !lStrPpoNo.equals("-1") && ! "Y".equalsIgnoreCase(strAprvdBills) )
                {
            	   if (! lStrRepeatPPONo.equals(lStrPpoNo) && chk[7].equals("9"))
                    {
                    	lStrRepeatPPONo = lStrPpoNo;
                    	objectArgs.put("PnsnCaseID",lStrPpoNo);
                    	objectArgs.put("toPost",toPost);
                    	objectArgs.put("toLevel",toLevel);
                        objectArgs.put("isFrmBills", "Y");
                        objectArgs.put("CurrCaseStatus", lShortUpdStatus.toString());
                    	PensionCaseServiceImpl objPensionServc = new PensionCaseServiceImpl();
                        ResultObject objRes = objPensionServc.frwdPensionCase(objectArgs);
                    }
                }
                lObjBillMvmntVO = new TrnBillMvmnt();
                trnBillReg = pbDaoImpl.read(Long.parseLong(lStrBillNo));
               /* if(! strAprvdBills.equalsIgnoreCase("Y"))
                {
                	objectArgs.put("toPost",toPost);
                	objectArgs.put("toLevel",toLevel);
                	lObjBillMvmntVO.setMvmntStatus(lShortUpdStatus);
                }
                else if(lStrIsTcBill.equalsIgnoreCase("Regular")&& trnBillReg.getCurrBillStatus().equals(DBConstants.ST_BAPRV_AUD))
                {
                	objectArgs.put("toPost",toPost);
                	objectArgs.put("toLevel",toLevel);
                	lObjBillMvmntVO.setMvmntStatus(lShortUpdStatus);
                }
                else
                {
                	objectArgs.put("toPost",lStrNillToPost);
                	objectArgs.put("toLevel",lStrNillTolevel);
                	if(trnBillReg.getCurrBillStatus().equals(DBConstants.ST_BRJCT_AUD))
                	{
                		lShortUpdStatus = DBConstants.ST_CHQ_CNTR;
                	}
                	lShortNillUpdtStatus = DBConstants.ST_CHQ_CNTR;
                	lObjBillMvmntVO.setMvmntStatus(lShortNillUpdtStatus);
                }
                */
                lObjBillMvmntVO.setBillNo(Long.parseLong(lStrBillNo));
                lObjBillMvmntVO.setCreatedDate(SessionHelper.getCurDate());
                lObjBillMvmntVO.setCreatedPostId(gLngPostId);
                lObjBillMvmntVO.setCreatedUserId(gLngUserId);
                lObjBillMvmntVO.setDbId(SessionHelper.getDbId(objectArgs));
                lObjBillMvmntVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                
                lObjBillMvmntVO.setReceivingUserId(SessionHelper.getUserId(objectArgs));
                lObjBillMvmntVO.setStatusUpdtDate(SessionHelper.getCurDate());

                if (!toPost.equals("-1"))
                {
                    // next user's post id
                    lObjBillMvmntVO.setStatusUpdtPostid(Long.parseLong(toPost));
                    lObjBillMvmntVO.setStatusUpdtUserid(Long.parseLong(lObjCmnSrvcDAOImpl.getUserIdFromPost(
                            toPost, gLngLangId)));
                }

                

              /*  if (lShortUpdStatus.equals(DBConstants.ST_BAPRV_AUD))
                {
                    String billCtgry = trnBillReg.getTcBill();
                    if (billCtgry != null && !billCtgry.equals("Regular"))
                    {
                        lObjBillMvmntVO.setReceivedDate(SessionHelper.getCurDate());
                    }
                }
*/
                objectArgs.put("BillMovementVO", lObjBillMvmntVO);
                StringTokenizer st = new StringTokenizer(billsNo[i], "~");
                String lStrBillNum = st.nextToken().toString();
                combos[i] = request.getParameter("cmb_" + lStrBillNum);
                objectArgs.put("jobId", lStrBillNum);
                objectArgs.put("postId", combos[i]);

                if (lStrReturnCase != null && lStrReturnCase.equals("1"))
                {
                    //objectArgs = (Map) returnBillFromWF(objectArgs).getResultValue();
                }
                else
                {
                    forwardBillFromWF(objectArgs);
                }

                // Updating TRN bill Register for current status
               /* if(lStrIsTcBill.equalsIgnoreCase("Regular") || ! strAprvdBills.equalsIgnoreCase("Y") )
                {
                	trnBillReg.setCurrBillStatus(lShortUpdStatus);
                }
                else
                {
                	trnBillReg.setCurrBillStatus(lShortNillUpdtStatus);
                }*/

                trnBillReg.setCurrBillStatusDate(gDtCurDate);

               /* if (combos[i] != null && combos[i].trim().length() > 0)
                {
                    trnBillReg.setAudPostId(Long.parseLong(combos[i]));
                }

                if (lShortUpdStatus == DBConstants.ST_BAPRV_AUD || lShortUpdStatus == DBConstants.ST_BRJCT_AUD)
                {
                    trnBillReg.setAuditDate(gDtCurDate);
                }
              
                if(lShortUpdStatus == DBConstants.ST_BAPRV_AUD)
                {
                	trnBillReg.setAuditStatus(DBConstants.BILL_AUD_APPR);
                }
                if(lShortUpdStatus == DBConstants.ST_BRJCT_AUD)
                {
                	trnBillReg.setAuditStatus(DBConstants.BILL_AUD_RJCT);
                }*/
                trnBillReg.setUpdatedUserId(gLngUserId);
                trnBillReg.setUpdatedPostId(gLngPostId);
                trnBillReg.setUpdatedDate(gDtCurDate);
                pbDaoImpl.update(trnBillReg);
                pbDaoImpl.updateRptExpDtls(objectArgs, Long.parseLong(lStrBillNo), trnBillReg);
    			
            }
            objectArgs.put("postId", gLngPostId);
            objectArgs.put("recFlag", gLngUserId);

        }
        catch (Exception e)
        {
            gLogger.error("Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
    }

    public ResultObject forwardBillFromWF(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        OnlineBillServiceImpl lObjOBService = new OnlineBillServiceImpl();
        try
        {
            setSessionInfo(inputMap);

            String lStrBillNum = (String) inputMap.get("jobId");
            TrnBillMvmnt lObjBillMvmntVO = new TrnBillMvmnt();
            BillMovementServiceImpl lObjBillMvmntSrvcImpl = new BillMovementServiceImpl();
            ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
            BillMovementDAOImpl lObjMvmntDAO = new BillMovementDAOImpl(TrnBillMvmnt.class, srvcLoc.getSessionFactory());
            
            lObjBillMvmntVO = lObjBillMvmntSrvcImpl.getMovementInstance(
                    (TrnBillMvmnt) inputMap.get("BillMovementVO"), inputMap);
            
           // lObjOBService.saveBillMovement(lObjBillMvmntVO,inputMap);     
            
            PhyBillDAOImpl phyDAO = new PhyBillDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
            BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv
                    .getSessionFactory());

            long lDocId = 0;
            TrnBillRegister lObjBillRegister = phyDAO.read(Long.parseLong(lStrBillNum));

            lDocId = Long.parseLong(bundleConst.getString("WF.DocId.PensionBill"));
            
            WorkFlowVO workFlowVO = getDefaultWorkFlowVO(inputMap);

            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
            WorkFlowInterfaceImpl wfImpl = new WorkFlowInterfaceImpl(workFlowVO);

            if (inputMap.get("toPost") != null && (!inputMap.get("toPost").equals("-1")))
            {
                String toPostId = (String) inputMap.get("toPost");
                String toLevelId = (String) inputMap.get("toLevel");
                workFlowVO.setToPost(toPostId);
                lObjBillMvmntVO.setStatusUpdtPostid(Long.parseLong(toPostId));
                lObjBillMvmntVO.setStatusUpdtUserid(Long.parseLong(lObjCmnSrvcDAOImpl.getUserIdFromPost(toPostId,
                        gLngLangId)));

                if (toLevelId != null && !toLevelId.equals(""))
                {
                    workFlowVO.setToLevelId(Integer.parseInt(toLevelId));
                }
                else
                {
                    List jobMstList = null;//orgUtil.getJobMstByJobRefID(lStrBillNum, lDocId, DBConstants.WF_ORDINARY_DOC_TYPE);
                    if (jobMstList != null && jobMstList.size() > 0)
                    {
                        WfJobMstVO jobMstVO = (WfJobMstVO) jobMstList.get(0);
                        Map upperUserMap = orgUtil.getUpperPost(gLngPostId.toString(), jobMstVO.getHierachyRefId(), jobMstVO
                                .getLevelId());
                        if (upperUserMap != null)
                        {
                            List resultList = (List) upperUserMap.get("Result");
                            Object[] obj = (Object[]) resultList.get(0);
                            toLevelId = obj[1].toString();
                            workFlowVO.setToLevelId(Integer.parseInt(toLevelId));
                        }
                    }
                    jobMstList = null;
                }
            }
            inputMap.put("BillMovementVO", lObjBillMvmntVO);
            workFlowVO.setAppMap(inputMap);

            WorkFlowUtility wfUtility = new WorkFlowUtility();
            workFlowVO.setActId(Long.parseLong("2")); //gObjRsrcBndle.getString("CMN.ForwardActId")
            workFlowVO.setDocId(lDocId);
            workFlowVO.setJobRefId(lStrBillNum);
            workFlowVO.setHierarchyFlag(1);
            workFlowVO.setJobTitle("OnlineBill");
            workFlowVO.setHierarchyFlag(1);
            wfUtility.invokeWorkFlow(workFlowVO);

            inputMap.put("MESSAGECODE", (long) 1038);
            objRes.setResultValue(inputMap);
            objRes.setViewName("ajaxData");

        }
        catch (Exception ex)
        {
           /* BptmCommonServiceImpl.setErrorProperties(gLogger, objRes, ex,
                    "Error whle forwarding bill from workflow...");*/
        }

        return objRes;
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
    
    private String getToPostAndToLvl(String reqParaToPost) throws Exception
    {
        String toPost = null;
        String toLevel = null;
        String returnStr = null;

        try
        {
            if (reqParaToPost != null && !reqParaToPost.equals("-1"))
            {
                String[] lArray = reqParaToPost.split("~");
                toPost = lArray[0];

                if (lArray.length == 1)
                {
                    toLevel = "-1";
                }
                else
                {
                    toLevel = lArray[1];
                }
                returnStr = toPost + "~" + toLevel;
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is " + e, e);
            throw e;
        }
        return returnStr;
    }
}