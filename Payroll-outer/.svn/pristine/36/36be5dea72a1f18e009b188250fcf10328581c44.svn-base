package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.billproc.common.service.BillProcConstants;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAO;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.BillVitoDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.BillVitoRegister;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.RltLevelStatus;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.MRCaseDAO;
import com.tcs.sgv.pension.dao.MRCaseDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAO;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.PensionBillDAOImpl;
import com.tcs.sgv.pension.dao.PensionCaseDAO;
import com.tcs.sgv.pension.dao.PensionCaseDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAO;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.appwfinterface.WorkFlowInterfaceImpl;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WfJobMstVO;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

public class MRCaseServiceImpl extends ServiceImpl implements MRCaseService
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
    
    long gLngFinYearId = 0;
    
    /* Global Variable for Current Date */
    Date gDtCurrDt = null;
    

    Log gLogger = LogFactory.getLog(getClass());

    ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pension/PensionConstants");

    //--------------------------------------------MR CASE starts------------------------------------------------->
    
    //for loading MR request page for a fresh request insertion
    public ResultObject loadInwdMRCase(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try
        {
            setSessionInfo(inputMap);
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currDate = sdf.format(gDtCurrDt);
            
            String fin_year = MRCaseDAO.getFinYearDesc(gLngFinYearId);
            
            //for getting current mon year start...
            String currentMonth = currDate.substring(3,5);
            String currentYear = currDate.substring(6,10);
            String monyr = currentMonth + currentYear;
            //for getting current mon year end...
            
            //------------------------------------------------- for generating the 6-digit sequence no. start...
            Long  lLngSeqNo = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_mr_inwrd", inputMap);
            
            String lStrSeqNo = lLngSeqNo.toString();
            String lStrgLngDBId = gLngDBId.toString();
            
            int lengthTotalSeqNo = lStrSeqNo.length();
            int lengthDBId = lStrgLngDBId.length();
            int lengthLocCode = gStrLocationCode.length();
            int totallenDbAndLoc = lengthDBId + lengthLocCode;
            
            String finalSeqNo = "";
            
            String lStrSeqNoOnly = lStrSeqNo.substring(totallenDbAndLoc,lengthTotalSeqNo);
            if(lStrSeqNoOnly.length() < 6)
            {
            	if(lStrSeqNoOnly.length() == 1)
            	{
            		lStrSeqNoOnly = "00000" + lStrSeqNoOnly;
            		finalSeqNo = lStrSeqNoOnly;
            	}
            	if(lStrSeqNoOnly.length() == 2)
            	{
            		lStrSeqNoOnly = "0000" + lStrSeqNoOnly;
            		finalSeqNo = lStrSeqNoOnly;
            	}
            	if(lStrSeqNoOnly.length() == 3)
            	{
            		lStrSeqNoOnly = "000" + lStrSeqNoOnly;
            		finalSeqNo = lStrSeqNoOnly;
            	}
            	if(lStrSeqNoOnly.length() == 4)
            	{
            		lStrSeqNoOnly = "00" + lStrSeqNoOnly;
            		finalSeqNo = lStrSeqNoOnly;
            	}
            	if(lStrSeqNoOnly.length() == 5)
            	{
            		lStrSeqNoOnly = "0" + lStrSeqNoOnly;
            		finalSeqNo = lStrSeqNoOnly;
            	}
            }
            else
            {
            	finalSeqNo = lStrSeqNoOnly;
            }
            //--------------------------------------------------- for generating the 6-digit sequence no. end...
            
            String lStrInwdNo = finalSeqNo + '/'+ gStrLocationCode + '/' + monyr ;
            
            inputMap.put("FinancialYear", fin_year);
            inputMap.put("InwardDate", currDate);
            inputMap.put("InwardNo", lStrInwdNo);
            inputMap.put("FinYrId", gLngFinYearId);
            inputMap.put("auditFlag","N");
            inputMap.put("InitFlag","Y");
            inputMap.put("ApprvdFlag","");
            inputMap.put("MRFromLevel","5");
            
            resObj.setResultValue(inputMap);
            resObj.setViewName("MRRequestPage");
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
    
    //for getting pensioner info by ajax
    public ResultObject getPensionerMRInfo(Map inputMap)
    {
        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,servLoc.getSessionFactory());
        
        List lLstMRDetails = null;
        
        try
        {
        	String lStrName = null;
        	String lStrScheme = null;
        	BigDecimal BDMAAmount = new BigDecimal(0);
        	String lStrBankCode = null;
        	String lStrBranchCode = null;
        	String lStrBranchName = null;
        	BigDecimal BDHeadCode = null;
        	
            setSessionInfo(inputMap);

            String lStrPPONO = request.getParameter("PPONO");
            String lStrCaseStatus = request.getParameter("CASESTATUS");
            lLstMRDetails  = lObjMstPensionerHdrDAO.getMRRelatedInfo(lStrPPONO,lStrCaseStatus,gStrLocationCode);
            
            if (lLstMRDetails!=null && !lLstMRDetails.isEmpty()) 
			{	
				Iterator it = lLstMRDetails.iterator();
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					if( tuple[0] != null)
					{
						lStrScheme = tuple[0].toString();
					}
					else
					{
						lStrScheme = "";
					}
					if( tuple[1] != null)
					{
						BDMAAmount = new BigDecimal(tuple[1].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
					}
					else
					{
						BDMAAmount = new BigDecimal(0);
					}
					if( tuple[2] != null)
					{
						lStrName = tuple[2].toString();
					}
					else
					{
						lStrName = "";
					}
					if( tuple[3] != null)
					{
						lStrBankCode = tuple[3].toString();
					}
					else
					{
						lStrBankCode = "";
					}
					if( tuple[4] != null)
					{
						BDHeadCode = new BigDecimal(tuple[4].toString());
					}
					else
					{
						BDHeadCode = new BigDecimal(0);
					}
					if( tuple[5] != null)
					{
						lStrBranchName = tuple[5].toString();
					}
					else
					{
						lStrBranchName = "";
					}
				}	
			}
            
            StringBuilder lStrGrant = new StringBuilder();
            
            if (lLstMRDetails != null)
            {
                lStrGrant.append(" <PENSIONERMRINFO> ");
                lStrGrant.append(" 		<NAME> ");
                lStrGrant.append(lStrName);
                lStrGrant.append(" 		</NAME> ");
                lStrGrant.append(" 		<SCHEME> ");
                lStrGrant.append(lStrScheme);
                lStrGrant.append(" 		</SCHEME> ");
                lStrGrant.append(" 		<MAAMOUNT> ");
                lStrGrant.append(BDMAAmount);
                lStrGrant.append(" 		</MAAMOUNT> ");
                lStrGrant.append(" 		<BANKCODE> ");
                lStrGrant.append(lStrBankCode);
                lStrGrant.append(" 		</BANKCODE> ");
                lStrGrant.append(" 		<HEADCODE> ");
                lStrGrant.append(BDHeadCode);
                lStrGrant.append(" 		</HEADCODE> ");
                lStrGrant.append(" 		<BRANCHNAME> ");
                lStrGrant.append(lStrBranchName);
                lStrGrant.append(" 		</BRANCHNAME> ");
                lStrGrant.append(" </PENSIONERMRINFO> ");
            }

            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
           // inputMap.put("CurrentDate", currDate);
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
    //Method for saving MR Case
    public ResultObject saveMRCase(Map inputMap)
    {
    	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,serv.getSessionFactory());
    	TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        try
        {
            lObjTrnPensionMedRembrsmnt = (TrnPensionMedRembrsmnt) inputMap.get("lObjTrnPensionMedRembrsmnt");
           
            if(lObjTrnPensionMedRembrsmnt.getMedRembrsmntId() != null)
	        {        
    	        lObjTrnPensionMedRembrsmntDAO.update(lObjTrnPensionMedRembrsmnt);
	        }
            else
            {
            	 
            	lObjTrnPensionMedRembrsmnt.setMedRembrsmntId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_med_rembrsmnt", inputMap));
	        	lObjTrnPensionMedRembrsmntDAO.create(lObjTrnPensionMedRembrsmnt);
	        	
                WorkFlowVO workFlowVO = new WorkFlowVO();
                Connection conn = serv.getSessionFactory().getCurrentSession().connection();
                String subjectName = gObjRsrcBndle.getString("MRCaseSubject");
                workFlowVO.setAppMap(inputMap);
                workFlowVO.setCrtEmpId(SessionHelper.getEmpId(inputMap).toString());
                workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
                workFlowVO.setCrtUsr(SessionHelper.getUserId(inputMap).toString());
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
                workFlowVO.setDocId(Long.parseLong(gObjRsrcBndle.getString("WF.DocId.MRCase")));
                workFlowVO.setJobRefId(lObjTrnPensionMedRembrsmnt.getMedRembrsmntId().toString());
                workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
                workFlowVO.setDbId(SessionHelper.getDbId(inputMap));
                workFlowVO.setJobTitle("MRCase");
                workFlowVO.setHierarchyFlag(1);
                wfUtility.invokeWorkFlow(workFlowVO);
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
    
    public Map insertMRCase(Map inputMap)
    {
    	WorkFlowVO wfVO = null;
        wfVO = (WorkFlowVO) inputMap.get("WorkFlowVO");
        
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        
        TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,serv.getSessionFactory());
    	
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        
        Long lLngTrnPensionMedRembrsmntId = 0L;
        Map HashMap = null;
        
        try
        {  
        	setSessionInfo(inputMap);
        	lObjTrnPensionMedRembrsmnt = (TrnPensionMedRembrsmnt) inputMap.get("lObjTrnPensionMedRembrsmnt");   
        	
        	if (wfVO != null)
            {
        		HashMap = wfVO.getAppMap();
            }
            else
            {
            	HashMap = inputMap;
            }
        	
        	//For updating table trn_pension_med_rembrsmnt if any updates done Start...
	       
	        //For updating table trn_pension_med_rembrsmnt if any changes done End 
	        /*//insert data into trn_pension_med_rembrsmnt
	        else
	        {
	        	lObjTrnPensionMedRembrsmnt.setMedRembrsmntId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_med_rembrsmnt", inputMap));
	        	lObjTrnPensionMedRembrsmntDAO.create(lObjTrnPensionMedRembrsmnt);
	        }
	        //end of inserting data no in trn_pension_med_rembrsmnt*/
            if (wfVO != null)
            {
                wfVO.setJobRefId(lLngTrnPensionMedRembrsmntId.toString());
                wfVO.setAppMap(HashMap);
                inputMap.put("WorkFlowVO", wfVO);
            }          
        }
        catch (Exception e)
        {
        	gLogger.error(" Error is : " + e, e);
        	objRes.setResultValue(null);
        	objRes.setThrowable(e);
        	objRes.setResultCode(ErrorConstants.ERROR);
        	objRes.setViewName("errorPage");
        }
        return inputMap;
    }
    
	//Method to get saved MR cases list
    public ResultObject getSavedMRCases(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        PensionBillServiceImpl lObjPensionBillServiceImpl = new PensionBillServiceImpl();

        String lStrUserId = SessionHelper.getUserId(inputMap).toString();
        String lStrLangId = SessionHelper.getLangId(inputMap).toString();
        String lStrCounterId = gObjRsrcBndle.getString("MRCounterLevelId");
        String lStrSubjectRegular = gObjRsrcBndle.getString("MRCaseSubject");
        String lStrMyMRCases = null;
        
        String lStrApprvdRejcted = request.getParameter("approvedCase");
        
        try
        {
        	setSessionInfo(inputMap);
        	
        	CommonPensionService lObjCommonPensionService = new CommonPensionServiceImpl();
        	lStrMyMRCases = lObjCommonPensionService.getMyMRCases(inputMap);
            PensionBillServiceImpl lobjPensionBillservice = new PensionBillServiceImpl();
            if(! request.getParameter("MRRequestFromlevel").toString().equals("50"))
            {
            	lobjPensionBillservice.getPensionHierarchyUsers(inputMap,gObjRsrcBndle.getString("MRCaseSubject"));
            }
        	
        	MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
            gLngUserId = Long.parseLong(lStrUserId);
            gLngLangId = Long.parseLong(lStrLangId);
            
            Integer totalRecords = 0;
            Map displayTag = IFMSCommonServiceImpl.getDisplayPara(request);
            
            inputMap.put("SavedCases", lStrMyMRCases);
            inputMap.put("approvedCase",lStrApprvdRejcted);
            inputMap.put("gLngPostId",gLngPostId);
            inputMap.put("RqstFrmLevel",request.getParameter("MRRequestFromlevel"));
            
            List savedMRCasesList = MRCaseDAO.getSavedMRCaseDtls(inputMap);
            int totalCount = MRCaseDAO.getSavedMRCaseDtlsCount(inputMap);
            
            //to populate the combo box for next level users
            if(! request.getParameter("MRRequestFromlevel").toString().equals("50"))
            {
            	lObjPensionBillServiceImpl.getPensionHierarchyUsers(inputMap,lStrSubjectRegular);
            }
            
            inputMap.put("totalRecords", totalCount);
            inputMap.put("SavedMRCaseList", savedMRCasesList);
            inputMap.put("userList", inputMap.get("userList"));
            
            if(request.getParameter("MRRequestFromlevel").toString().equals("5"))
            {
            	//for counter
            	inputMap.put("counterStatus",1);
            	inputMap.put("MRRequestFromlevel",5);
            	inputMap.put("status",1); //for showing heading as saved cases or audit cases
            	inputMap.put("approvedCase",lStrApprvdRejcted);
            }
            else
            {
            	inputMap.put("counterStatus",2);
            	inputMap.put("MRRequestFromlevel",request.getParameter("MRRequestFromlevel"));
            	inputMap.put("status",2);
            	inputMap.put("approvedCase",lStrApprvdRejcted);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
        }
        resObj.setResultValue(inputMap);
        resObj.setViewName("SavedMRCases");
        return resObj;
    }
    //Method to fetch the MR request for a particular med_rem_id
    public ResultObject getMRCaseData(Map inputMap)
    {
        setSessionInfo(inputMap);
        
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
        CmnLookupMstDAOImpl cmnLookupMstObj = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());       
        
        String ppoNo = null;
        String finYear = null;
        String branchCode = null;
        String branchName = null;
        String pensionerName = null;
        Long MedRemId = null;
        Long Bill_hdr_id = null;
        BigDecimal rem_amnt = null;
        BigDecimal grant_amnt = null;
        BigDecimal ma_amnt = null;
        String ppo_no = null;
        Integer from_mon = null;
        Integer to_mon = null;
        String inwd_no = null;
        Date inwd_date = null;
        String patient_name = null;
        String relation = null;
        Short age = null;
        String hospital = null;
        BigDecimal ded_amnt = null;
        BigDecimal perm_amnt = null;
        String reason = null;
        BigDecimal ref_num = null;
        String branch_code = null;
        String scheme = null;
        BigDecimal head_code = null;
        String fromMon = null;
        String fromYr = null;
        String toMon = null;
        String toYr = null;
        String from_month = null;
        String to_month = null;
        
       // List lLstMRCase = new ArrayList();
        TrnPensionMedRembrsmnt lLstobjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
        
        try
        {
            Long mstPnsnDtlsID1 = null;
            List listFmlRelation = null;
            String medRembrsmntId = StringUtility.getParameter("medRembrsmntId", request);
            String MRFromLevel = StringUtility.getParameter("MRRequestFromlevel", request);
            String ApprvdFlag = StringUtility.getParameter("ApprvdFlag", request);
            
            if(medRembrsmntId != null && medRembrsmntId.length()>0)
            {
            	Long lLngmedRembrsmntId = Long.parseLong(medRembrsmntId);
            	
            	//ppoNo = MRCaseDAO.getPpoNoFromMedRemId(lLngmedRembrsmntId);
            	//branchCode = MRCaseDAO.getBranchCodeFromMedRemId(lLngmedRembrsmntId);
                listFmlRelation = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("Family_Relation", gLngLangId);
            	finYear = MRCaseDAO.getFinYearDesc(gLngFinYearId);
            	
            	lLstobjTrnPensionMedRembrsmnt = MRCaseDAO.getTrnPensionMedRembrsmntVO(lLngmedRembrsmntId);
            	
            	if((lLstobjTrnPensionMedRembrsmnt != null))
            	{
            		
            		MedRemId = lLstobjTrnPensionMedRembrsmnt.getMedRembrsmntId();
            		Bill_hdr_id = lLstobjTrnPensionMedRembrsmnt.getBillHdrId();
            		rem_amnt = lLstobjTrnPensionMedRembrsmnt.getRemAmnt();
            		grant_amnt = lLstobjTrnPensionMedRembrsmnt.getGrantAmnt();
            		ma_amnt = lLstobjTrnPensionMedRembrsmnt.getMaAmnt();
            		ppo_no = lLstobjTrnPensionMedRembrsmnt.getPpoNo();
            		from_mon = lLstobjTrnPensionMedRembrsmnt.getFromMonth();
            		to_mon = lLstobjTrnPensionMedRembrsmnt.getToMonth();
            		inwd_no = lLstobjTrnPensionMedRembrsmnt.getInwdNo();
            		inwd_date = lLstobjTrnPensionMedRembrsmnt.getInwdDate();
            		patient_name = lLstobjTrnPensionMedRembrsmnt.getPatientName();
            		relation = lLstobjTrnPensionMedRembrsmnt.getRelationship();
            		age = lLstobjTrnPensionMedRembrsmnt.getAge();
            		hospital = lLstobjTrnPensionMedRembrsmnt.getHospitalName();
            		ded_amnt = lLstobjTrnPensionMedRembrsmnt.getDeductionAmnt();
            		perm_amnt = lLstobjTrnPensionMedRembrsmnt.getPermissibleAmnt();
            		reason = lLstobjTrnPensionMedRembrsmnt.getReason();
            		ref_num = lLstobjTrnPensionMedRembrsmnt.getRefNum();
            		branch_code = lLstobjTrnPensionMedRembrsmnt.getBranchCode();
            		scheme = lLstobjTrnPensionMedRembrsmnt.getScheme();
            		head_code = lLstobjTrnPensionMedRembrsmnt.getHeadCode();
            		
                    if(from_mon != null)
                    {
                    	from_month = from_mon.toString();
                    	fromMon = from_month.substring(4,6);
                    	fromYr = from_month.substring(0,4);
                    }
                    if(to_mon != null)
                    {
                    	to_month = to_mon.toString();
                    	toMon = to_month.substring(4,6);
                    	toYr = to_month.substring(0,4);
                    }
            	}
            	branchName = MRCaseDAO.getBranchByBranchId(branch_code,gLngLangId,gStrLocationCode);
            	pensionerName = MRCaseDAO.getPensionerNameFromPPO(ppo_no);
            }

            inputMap.put("listFamilyRlshp", listFmlRelation);
            inputMap.put("PensionerName", pensionerName);
            inputMap.put("FinancialYear", finYear);
            inputMap.put("FinYrId", gLngFinYearId);
            inputMap.put("BranchName",branchName );
            
            inputMap.put("MedRemId", MedRemId);
            inputMap.put("BillHdrId", Bill_hdr_id);
            inputMap.put("RmbrsAmnt", rem_amnt);
            inputMap.put("GrntAmnt", grant_amnt);
            inputMap.put("MAAmount",ma_amnt );
            inputMap.put("PPONo",ppo_no );
            inputMap.put("InwardNo", inwd_no);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currDate = sdf.format(inwd_date);
            
            inputMap.put("InwardDate", currDate);
            inputMap.put("PatientName", patient_name);
            inputMap.put("Relation", relation); 
            inputMap.put("Age", age);
            inputMap.put("Hospital", hospital);
            inputMap.put("DedAmnt", ded_amnt);
            inputMap.put("PermAmnt",perm_amnt );
            inputMap.put("Reason",reason );
            inputMap.put("RefNo",ref_num );
            inputMap.put("BranchCode", branch_code);
            inputMap.put("Scheme",scheme );
            inputMap.put("HdCode", head_code);
            inputMap.put("FrmMon", fromMon);
            inputMap.put("FrmYr", fromYr);
            inputMap.put("ToMon",toMon );
            inputMap.put("ToYr",toYr);
            
            if(MRFromLevel.equals("5"))
            {
            	//for counter
            	inputMap.put("auditFlag","N");
            	inputMap.put("InitFlag","N");
            }
            else
            {
            	inputMap.put("auditFlag","Y");
            	inputMap.put("InitFlag","N");
            }
            inputMap.put("MRFromLevel",MRFromLevel);
            inputMap.put("ApprvdFlag",ApprvdFlag);
            resObj.setResultValue(inputMap);
            resObj.setViewName("MRRequestPagePopup");
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
    //Method to forward MR case to next upper level
    public ResultObject forwardMRCase(Map lInputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) lInputMap.get("requestObj");
        String lStrIsVitoSucced = "N";
        //Long MedRemId = Long.parseLong(request.getParameter("MedRemId"));
        String lStrFromLevel = request.getParameter("MRRequestFromlevel");
        
        try
        {
        	if(request.getParameter("isVito") != null && request.getParameter("isVito").toString().equalsIgnoreCase("Y"))
        	{
        		lStrIsVitoSucced = "N";
        		objRes =  generateMRVito(lInputMap);
        		lInputMap = (Map) objRes.getResultValue();
        		if(lInputMap.get("vitoMessage") != null)
        		{
        			lStrIsVitoSucced = "Y";
        		}
        	}
        	if(lStrIsVitoSucced.equalsIgnoreCase("Y"))
        	{
        		doMRCaseMovement(lInputMap);
        	}
        	if( request.getParameter("isFrwrd") != null && request.getParameter("isFrwrd").equalsIgnoreCase("Y"))
        	{
        		lInputMap.put("isFrwrd", "Y");
        		doMRCaseMovement(lInputMap);
        	}
        	if( request.getParameter("isSendToOthrs") != null && request.getParameter("isSendToOthrs").equalsIgnoreCase("Y"))
        	{
        		doMRCaseMovement(lInputMap);
        	}
        	if( request.getParameter("isAprvd") != null && request.getParameter("isAprvd").equalsIgnoreCase("Y"))
        	{
        		doMRCaseMovement(lInputMap);
        	}
        	if( request.getParameter("isRejected") != null && request.getParameter("isRejected").equalsIgnoreCase("Y"))
        	{
        		doMRCaseMovement(lInputMap);
        	}
        	
        	objRes = getSavedMRCases(lInputMap);
            Map temp = (Map) objRes.getResultValue();
            objRes.setResultValue(temp);
        }
        catch(Exception e)
        {
        	//BptmCommonServiceImpl.setErrorProperties(gLogger, objRes, e,
            //"Error occured while forwarding MR Cases...");
        }
        return objRes;
    }
    //Method to generate MR vito
    public ResultObject generateMRVito(Map objectArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        try
        {
            setSessionInfo(objectArgs);
            String vitoCode = "";
            String vitoMessage = "";
            String printString = "";
            Map printMap = new HashMap();
            
            printMap = genPrintableVitoFormatForMR(objectArgs);
            
            vitoCode = (String) printMap.get("vitoCode");
            if (vitoCode != null)
            {
                vitoMessage = "Vito Code " + vitoCode + " Generated Successfully";
                printString = (String) printMap.get("printString");
            }
                
            //System.out.println(printString);
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
    public Map genPrintableVitoFormatForMR(Map objectArgs) 
    {
        HashMap printMap = new HashMap();
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        String[] arrChkBox;
        String arrSelectedRequests[] = null;
        String arrAuditors[] = null;
        
        String strSelectedRequests=null;
        String strSelectedAuditors=null;
        String printString=null;
        String lStrPrintHdr = null;
        String lStrPrintHead = null;
        String lStrPrintSummaryHead = null;
        String lStrSummaryRecord = null;
        String lStrPrintRecord = null;
        StringBuffer sbLine = new StringBuffer();
        Map vitoMap = new HashMap();
        ArrayList<TrnPensionMedRembrsmnt> lObjTrnPensionMedRembrsmntArray = new ArrayList<TrnPensionMedRembrsmnt>();
        BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(BillVitoRegister.class, serv.getSessionFactory());
        BigDecimal lBDAudPostId = new BigDecimal(0);
        List AuditorNamesAndPosts = null;
        String vitoCode = null; 
        String vitoType = null;
        BillVitoRegister vitoVO = new BillVitoRegister();
        String returnVitoCode = null;
        
        int k=0;
        int count=0;
        BigDecimal sumRemAmnt=new BigDecimal(0);
        int flag = 0;
        
        String[] NameArrayOfAuditors = new String[5000];
        Integer[] totalRequestsForEachAuditorArray = new Integer[5000];
        BigDecimal[] remAmountSum = new BigDecimal[5000]; 
        
        int p = 0;
        BigDecimal sumAllRemAmounts = new BigDecimal(0);
        int countAllRequests = 0;
        
        try
        {
            setSessionInfo(objectArgs);
            arrChkBox = (String[])request.getParameterValues("chkbxTrnPensionRqstHdrId");
            arrSelectedRequests = new String[arrChkBox.length];
            arrAuditors = new String[arrChkBox.length];
            
            for(int j=0;j<arrChkBox.length;j++)
            {
                String[] tempArr = arrChkBox[j].split("~");
                arrSelectedRequests[j] = tempArr[0];
                arrAuditors[j] = tempArr[1];
                if(j == 0)
                {
                	strSelectedRequests = tempArr[0];
                }
                else
                {
                	strSelectedRequests = strSelectedRequests + "," + tempArr[0];
                }
                if(j == 0)
                {
                	strSelectedAuditors = tempArr[1];
                }
                else
                {
                	strSelectedAuditors = strSelectedAuditors + "," + tempArr[1];
                }
            }
            if(strSelectedRequests != null)
            {
            	
            	lObjTrnPensionMedRembrsmntArray = MRCaseDAO.getTrnPensionMedRembrsmntVOArray(strSelectedRequests);
            	AuditorNamesAndPosts = MRCaseDAO.getAuditorNameListFromPostId(strSelectedAuditors);
            	String auditName = new String();
            	
            	//for generating vito code---------------
            	//vitoType = DBConstants.VITO_MRCASE_FOR_AUD;
            	//vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
            	
                for(int i=0;i<arrSelectedRequests.length;i++)
                {                   
                    Long vitoId = IFMSCommonServiceImpl.getNextSeqNum("bill_vito_register", objectArgs);
                    vitoVO = new BillVitoRegister();
                    vitoVO.setVitoId(vitoId);
                    //vitoVO.setVitoCode(vitoCode);
                    vitoVO.setVitoType(vitoType);
                    vitoVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                    vitoVO.setBillNo(Long.parseLong(arrSelectedRequests[i]));
                    vitoVO.setCreatedDate(new Date(System
                            .currentTimeMillis()));
                    vitoVO.setCreatedUserId(gLngUserId);
                    vitoVO.setCreatedPostId(gLngPostId);
                    vitoVO.setDbId(gLngDBId);
                    vitoDaoImpl.create(vitoVO);
                }   
            	//---------------------------------------
                lStrPrintHdr = printMRRecordHdr(objectArgs,vitoCode);
                sbLine.append(lStrPrintHdr);
                
            	for(int i=0;i<AuditorNamesAndPosts.size();i++)
            	{
            		flag = 0;
            		sumRemAmnt = new BigDecimal(0);
					Object[] tuple = (Object[])AuditorNamesAndPosts.get(i);
					if( tuple != null)
					{
						auditName = tuple[0].toString();
						lBDAudPostId = new BigDecimal(tuple[1].toString());
						
						NameArrayOfAuditors[i] = auditName;
					}
					lStrPrintHead = "\n"+getChar(35, " ")+"Auditor Name :";
					lStrPrintHead = lStrPrintHead + auditName;
					lStrPrintHead = lStrPrintHead + "\n";
					lStrPrintHead = lStrPrintHead +getChar(97, "-");
					lStrPrintHead = lStrPrintHead +"\n";
					lStrPrintHead = lStrPrintHead +getChar(2, " ");
					lStrPrintHead = lStrPrintHead +"Reference No";
					lStrPrintHead = lStrPrintHead +getChar(2, " ");
					lStrPrintHead = lStrPrintHead +"PPO No.";
					lStrPrintHead = lStrPrintHead +getChar(15, " ");
					lStrPrintHead = lStrPrintHead +"Pensioner Name";
					lStrPrintHead = lStrPrintHead +getChar(25, " ");
					lStrPrintHead = lStrPrintHead +"Reimbursement Amount";
					lStrPrintHead = lStrPrintHead + "\n";
					lStrPrintHead = lStrPrintHead +getChar(97, "-");
					
					sbLine.append(lStrPrintHead);
					
			        for(k=0;k<lObjTrnPensionMedRembrsmntArray.size();k++)
	            	{
	            		if(lObjTrnPensionMedRembrsmntArray.get(k).getAuditorPostId().equals(lBDAudPostId))
	            		{
	            			flag++;
	            			lStrPrintRecord =  printMRRecord(objectArgs,lObjTrnPensionMedRembrsmntArray.get(k));
	            			TrnPensionMedRembrsmnt trnPensionMedRembrsmntVO = lObjTrnPensionMedRembrsmntArray.get(k);
	            			sumRemAmnt = sumRemAmnt.add(trnPensionMedRembrsmntVO.getRemAmnt());
	            			sbLine.append(lStrPrintRecord);
	            		}
	            	}
			        
			        count = flag;
			        totalRequestsForEachAuditorArray[i] = count;
			        
			        
			        
			        sbLine.append(getChar(97, "-"));
			        sbLine.append("\n");
			        sbLine.append("Total Requests : ");
			        sbLine.append(count);
			        sbLine.append(getChar(35, " "));
			        sbLine.append("Total Reimbursement Amount : ");
			        sbLine.append(getChar(15-(sumRemAmnt.toString().length()), " "));
			        sbLine.append(sumRemAmnt);
			        remAmountSum[i] = sumRemAmnt;
			        sbLine.append("\n");
			        sbLine.append(getChar(97, "-"));
			        sbLine.append("\n");
            	}//end auditor loop
            	
            	//For summary page start---------------------------------------------------------------
            	lStrPrintHdr = printMRRecordHdr(objectArgs,vitoCode);
            	sbLine.append(lStrPrintHdr);
            	sbLine.append("\n");
            	sbLine.append(getChar(35, " "));
            	sbLine.append("Summary Page");
            	sbLine.append("\n");
            	sbLine.append(getChar(97, "-"));
            	//for header
            	lStrPrintSummaryHead = "\n"+getChar(2, " ")+"Serial No. ";
            	lStrPrintSummaryHead = lStrPrintSummaryHead +getChar(2, " ");
            	lStrPrintSummaryHead = lStrPrintSummaryHead +"Auditor Name";
            	lStrPrintSummaryHead = lStrPrintSummaryHead +getChar(20, " ");
            	lStrPrintSummaryHead = lStrPrintSummaryHead +"Total No. of Requests";
            	lStrPrintSummaryHead = lStrPrintSummaryHead +getChar(2, " ");
            	lStrPrintSummaryHead = lStrPrintSummaryHead +"Total Reimbursement Value";
            	lStrPrintSummaryHead = lStrPrintSummaryHead + "\n";
            	lStrPrintSummaryHead = lStrPrintSummaryHead +getChar(97, "-");
            	
            	sbLine.append(lStrPrintSummaryHead);
            	for(p=0;p<AuditorNamesAndPosts.size();p++)
            	{
            		lStrSummaryRecord = "\n"+String.format("%11s",(p+1));
            		lStrSummaryRecord = lStrSummaryRecord + getChar(2, " ");
            		lStrSummaryRecord = lStrSummaryRecord + NameArrayOfAuditors[p];
            		lStrSummaryRecord = lStrSummaryRecord +getChar(32-(NameArrayOfAuditors[p].length()), " ");
            		lStrSummaryRecord = lStrSummaryRecord +getChar(21-(totalRequestsForEachAuditorArray[p].toString().length()), " ");
            		lStrSummaryRecord = lStrSummaryRecord +totalRequestsForEachAuditorArray[p];
            		lStrSummaryRecord = lStrSummaryRecord +getChar(2, " ");
            		lStrSummaryRecord = lStrSummaryRecord +getChar(25-(remAmountSum[p].toString().length()), " ");
            		lStrSummaryRecord = lStrSummaryRecord +remAmountSum[p];
            		
            		countAllRequests = countAllRequests + totalRequestsForEachAuditorArray[p];
            		sumAllRemAmounts = sumAllRemAmounts.add(remAmountSum[p]);
            		sbLine.append(lStrSummaryRecord);
            	}
            	//sbLine.append(lStrSummaryRecord);
            	
            	sbLine.append("\n");
		        sbLine.append(getChar(97, "-"));
		        sbLine.append("\n");
		        sbLine.append("Total ");
		        sbLine.append(getChar(59, " "));
		        sbLine.append(countAllRequests);
		        sbLine.append(getChar(2, " "));
		        sbLine.append(getChar(25-(sumAllRemAmounts.toString().length()), " "));
		        sbLine.append(sumAllRemAmounts);
		        sbLine.append("\n");
		        sbLine.append(getChar(97, "-"));
		        sbLine.append("\n");
            	
            	//-----------------end summary -------------------------------------------------------
            }
            if(vitoCode != null)
            {
                returnVitoCode = vitoCode+"";
            }
            printMap.put("vitoCode", returnVitoCode);
            printMap.put("printString", sbLine.toString());
            //System.out.print(sbLine.toString());
        }
        catch (Exception e)
        {
            gLogger.error("Exception is " + e,e);
        }           
        return printMap;
    }
    //formatting------------------------------------------------------------------------Start
    private String printMRRecordHdr(Map objectArgs,String vitoCode)
    {
    	setSessionInfo(objectArgs);
    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        StringBuffer sbLine = new StringBuffer();
        String lstrName = null;
        ArrayList result1 = new ArrayList(); 
        Object[] tuple;
        
        String lStrInwrdPersonName = null;
        
	    try
	    {
	    	result1 = (ArrayList) MRCaseDAO.getLocationReport(gStrLocationCode,gLngLangId); 
        	if (result1 != null && result1.size() > 0)
            {
                for(int i=0; i< result1.size(); i++)
                {
                	tuple =  (Object[]) result1.get(i); 
                	
                	sbLine.append(getChar(40, " "));
                	sbLine.append(String.valueOf(tuple[0]));
                	sbLine.append(getChar(40, " "));
                	sbLine.append(String.valueOf(tuple[1]));
            	}      
            }
			SimpleDateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa") ;
			Calendar c = Calendar.getInstance();
			String time = formatter.format(c.getTime());
			
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currDate = sdf.format(gDtCurrDt);
			
            lStrInwrdPersonName = MRCaseDAO.getUserName(gLngUserId);
            
            sbLine.append("\n\n");
            sbLine.append(getChar(35, " "));
            sbLine.append("MR Vito For Audit");
            sbLine.append("\n");
			sbLine.append(getChar(10, " "));
			sbLine.append("Print Date : ");  
			sbLine.append(time);
			sbLine.append(getChar(10, " "));
			sbLine.append("Entry By : ");  
			sbLine.append(lStrInwrdPersonName);
			sbLine.append("\r\n");
			sbLine.append(getChar(10, " "));
			sbLine.append("Vito For Date : ");  
			sbLine.append(currDate);
			sbLine.append("\r\n");
			sbLine.append(getChar(40, " "));
			sbLine.append("VitoCode : ");  
			sbLine.append(vitoCode);
			sbLine.append("\n"+getChar(97, "-"));
	    }
	    catch(Exception e)
	    {
	    	gLogger.error("Exception is " + e,e);
	    }
		  return sbLine.toString();
    }
    
    //formatting------------------------------------------------------------------------End
    private String getChar(int count, String ele)
    {
        StringBuffer lSBSpace = new StringBuffer();
        for (int i = 0; i < count; i++)
        {
            lSBSpace.append(ele);
        }
        return lSBSpace.toString();
    }
    //Method to fwd MR case
    private void doMRCaseMovement(Map objectArgs) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        RltLevelStatus lObjRltLvlStatus = new RltLevelStatus();
        
        String[] arrChkBox;
        String arrSelectedRequestsMedId[] = null;
        String arrAuditorPostIds[] = null;
        
        String lStrMedRemId = null;
        String lStrPostId = null;
        String lStrLevelId = null;
        
    	TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,serv.getSessionFactory());
    	TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());

        
        try
        {
        	setSessionInfo(objectArgs);
        	Long postId = SessionHelper.getPostId(objectArgs);
            Long langId = SessionHelper.getLangId(objectArgs);
            String lStrActionVal = request.getParameter("actionVal");
            
            String lStrAudPostId = null;
            String lStrCurrStatus = null;
            String[] chk = null;
            String toPost = null;
            String toPostId = null;
            String toLevel = null;
            String[] MRCasesNo = null;
            BigDecimal BDPostIdOfAuditor = new BigDecimal(0);
            
            String lStrReturnCase = null;
            
            if (request.getParameterValues("chkbxTrnPensionRqstHdrId") != null)
            {
                MRCasesNo = request.getParameterValues("chkbxTrnPensionRqstHdrId");
            }
        	// TO get status based on to level 
            /*if (lStrActionVal != null && lStrActionVal.length() > 0)
            {
            	if(request.getParameter("cmbFrwdUsr") != null)
	        	{
	        		toPost = request.getParameter("cmbFrwdUsr").split("~")[0];
	                toLevel = request.getParameter("cmbFrwdUsr").split("~")[1];
	        	}
            }*/
            
            for (int i = 0; i < MRCasesNo.length; i++)
            {
                chk = MRCasesNo[i].split("~");
                lStrMedRemId = chk[0];
                lStrPostId = chk[1];
                
                //for send to others
                if(request.getParameter("isSendToOthrs") != null && request.getParameter("isSendToOthrs").equalsIgnoreCase("Y") )
            	{
            		if(request.getParameter("hidcmbFrwdUsr") != null && request.getParameter("hidcmbFrwdUsr").length() >0 )
            		{
            			toPost = getToPostAndToLvl(request.getParameter("hidcmbFrwdUsr")).split("~")[0];
                        toLevel = getToPostAndToLvl(request.getParameter("hidcmbFrwdUsr")).split("~")[1];
                        if(toLevel.equals("20"))
                        {
                        	lObjTrnPensionMedRembrsmnt = lObjTrnPensionMedRembrsmntDAO.read(Long.parseLong(lStrMedRemId));
                        	lObjTrnPensionMedRembrsmnt.setAuditorPostId(new BigDecimal(toPost));
                        	lObjTrnPensionMedRembrsmntDAO.update(lObjTrnPensionMedRembrsmnt);
                        }
            		}
            	}
                //for approved cases
                else if(request.getParameter("isAprvd") != null && request.getParameter("isAprvd").equalsIgnoreCase("Y") )
            	{
                	BDPostIdOfAuditor = MRCaseDAO.getAuditorPostIdByMedRemId(Long.parseLong(lStrMedRemId));
                	toPost = BDPostIdOfAuditor.toString();                	
                	toLevel = gObjRsrcBndle.getString("MRAuditorLevelId");
            	}
                //for rejected cases
                else if(request.getParameter("isRejected") != null && request.getParameter("isRejected").equalsIgnoreCase("Y") )
            	{
                	BDPostIdOfAuditor = MRCaseDAO.getAuditorPostIdByMedRemId(Long.parseLong(lStrMedRemId));
                	toPost = BDPostIdOfAuditor.toString();
                	toLevel = gObjRsrcBndle.getString("MRAuditorLevelId");
            	}                
                else
                {
	                //for to post id start..............................................................
	            	if(request.getParameter("cmbFrwdUsr") != null && ! request.getParameter("cmbFrwdUsr").toString().equals("-1"))
	            	{
	            		toPostId = request.getParameter("cmbFrwdUsr").split("~")[0];
	            	}
	            	else
	            	{
	            		toPostId = lStrPostId;
	            	}
	                //for to post id end.................................................................
	                
	                //for to level id start..............................................................
	                if(request.getParameter("cmbFrwdUsr") == null)
	                {
	                	lStrLevelId = gObjRsrcBndle.getString("MRAuditorLevelId");
	                }
	                else
	                {
	                	lStrLevelId = request.getParameter("cmbFrwdUsr").split("~")[1];
	                }
	                if (lStrActionVal != null && lStrActionVal.trim().length() > 0)
	                {
	                	toPost = toPostId;
	                    toLevel = lStrLevelId;
	                }
	                //for to level id end................................................................. 
                }
                
                
            	objectArgs.put("toPost",toPost);
            	objectArgs.put("toLevel",toLevel);
            	objectArgs.put("jobId",lStrMedRemId);
                
                if (lStrReturnCase != null && lStrReturnCase.equals("1"))
                {
                    //objectArgs = (Map) returnBillFromWF(objectArgs).getResultValue();
                }
                else
                {
                    forwardMRCaseFromWF(objectArgs);
                    
                    //updating the status of trn_pension_med_rembrsmnt
                    lObjTrnPensionMedRembrsmnt = lObjTrnPensionMedRembrsmntDAO.read(Long.parseLong(lStrMedRemId));
                    
                    if(request.getParameter("isAprvd") != null && request.getParameter("isAprvd").equalsIgnoreCase("Y") )
                    {
                    	lObjTrnPensionMedRembrsmnt.setStatus(60);
                    	lObjTrnPensionMedRembrsmnt.setAuthoriserPostId(new BigDecimal(gLngPostId));
                    }
                    else if(request.getParameter("isRejected") != null && request.getParameter("isRejected").equalsIgnoreCase("Y") )
                    {
                    	lObjTrnPensionMedRembrsmnt.setStatus(70);
                    	lObjTrnPensionMedRembrsmnt.setAuthoriserPostId(new BigDecimal(gLngPostId));
                    }
                    else
                    {
                    	lObjTrnPensionMedRembrsmnt.setStatus(Integer.parseInt(toLevel));
                    }
                    lObjTrnPensionMedRembrsmntDAO.update(lObjTrnPensionMedRembrsmnt);
                }

            }
        }
        catch (Exception e)
        {
            gLogger.error("Error is " + e, e);
            e.printStackTrace();
            throw e;
        }
    }
    
    private void setSessionInfo(Map inputMap)
    {
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocId = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
        //gLngFinYearId = Long.parseLong(SessionHelper.getFinYrId(inputMap).toString());
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
    public String printMRRecord(Map objectArgs,TrnPensionMedRembrsmnt TrnPensionMedRembrsmntVo)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        StringBuffer sbLine = new StringBuffer();
        String lstrName = null;
        ArrayList result1 = new ArrayList(); 
        Object[] tuple;
        
        try
        {
	        sbLine.append("\n");
	        sbLine.append(getChar(14-(TrnPensionMedRembrsmntVo.getRefNum().toString().length()), " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getRefNum());
	        sbLine.append(getChar(2, " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getPpoNo());
	        sbLine.append(getChar(22-(TrnPensionMedRembrsmntVo.getPpoNo().toString().length()), " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getPensionerName());
	        sbLine.append(getChar(39-(TrnPensionMedRembrsmntVo.getPensionerName().toString().length()), " "));
	        sbLine.append(getChar(20-(TrnPensionMedRembrsmntVo.getRemAmnt().toString().length()), " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getRemAmnt());
	        
	        sbLine.append("\r\n");
        }   
        catch (Exception e)
        {
            gLogger.error("Exception is " + e,e);
        }  
        return sbLine.toString();
    }
    public ResultObject forwardMRCaseFromWF(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        
        try
        {
            setSessionInfo(inputMap);
            
            String lStrMRId = (String) inputMap.get("jobId");
            long lDocId = 0;

            lDocId = Long.parseLong(gObjRsrcBndle.getString("WF.DocId.MRCase"));
            
            WorkFlowVO workFlowVO = getDefaultWorkFlowVO(inputMap);

            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
            WorkFlowInterfaceImpl wfImpl = new WorkFlowInterfaceImpl(workFlowVO);

            if (inputMap.get("toPost") != null && (!inputMap.get("toPost").equals("-1")))
            {
                String toPostId = (String) inputMap.get("toPost");
                String toLevelId = (String) inputMap.get("toLevel");
                workFlowVO.setToPost(toPostId);
                
                if (toLevelId != null && !toLevelId.equals(""))
                {
                    workFlowVO.setToLevelId(Integer.parseInt(toLevelId));
                }
                else
                {
                    List jobMstList = null;//orgUtil.getJobMstByJobRefID(lStrMRId, lDocId, DBConstants.WF_ORDINARY_DOC_TYPE);
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
            workFlowVO.setAppMap(inputMap);

            WorkFlowUtility wfUtility = new WorkFlowUtility();
            workFlowVO.setActId(Long.parseLong("2")); //gObjRsrcBndle.getString("CMN.ForwardActId")
            workFlowVO.setDocId(lDocId);
            workFlowVO.setJobRefId(lStrMRId);
            workFlowVO.setHierarchyFlag(1);
            workFlowVO.setJobTitle("MRCase");
            workFlowVO.setHierarchyFlag(1);
            wfUtility.invokeWorkFlow(workFlowVO);

            inputMap.put("MESSAGECODE", (long) 1038);
            objRes.setResultValue(inputMap);
            objRes.setViewName("ajaxData");

        }
        catch (Exception ex)
        {
            //BptmCommonServiceImpl.setErrorProperties(gLogger, objRes, ex,
                    //"Error whle forwarding MR Case from workflow...");
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
    //for send to others
    public ResultObject getSendToOthrs(Map orgsMap)
    {
    	 int llFromLevelId = 0;
       
         ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
         String[] MRCasesNo = null;
         String[] chk = null;
         ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
         HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");
         PensionBillDAOImpl lObjPensnBilldao = new PensionBillDAOImpl(serv.getSessionFactory());
         Boolean RegFlag = false;
         String lStrOtherType = null;
         String lStrBranchCode = null;
         String brnchCode = null;
         String forwardFlag = "true";
         String AuditorPostId = null;
         
         
         try
         {
        	 setSessionInfo(orgsMap);
        	 if (request.getParameterValues("chkbxTrnPensionRqstHdrId") != null)
             {
        		 MRCasesNo = request.getParameterValues("chkbxTrnPensionRqstHdrId");
             } 
        	 String arrAuditorPostIds[] = new String[MRCasesNo.length];
        	 
        	 if(request.getParameter("FrwdType") != null)
        	 {
        		 lStrOtherType = request.getParameter("FrwdType");
        	 }
        	 WorkFlowVO workFlowVO = getDefaultWorkFlowVO(orgsMap);
             WorkFlowInterfaceImpl wfImpl = new WorkFlowInterfaceImpl(workFlowVO);
             
        	  for (int i = 0; i < MRCasesNo.length; i++)
              {
        		  chk = MRCasesNo[i].split("~"); 
        		  lStrBranchCode = chk[3];
        		  AuditorPostId = chk[1];
        		  arrAuditorPostIds[i] = AuditorPostId;
        		  
        		  WorkFlowVO newworkFlowVO = new WorkFlowVO();
                  Connection conn = serv.getSessionFactory().getCurrentSession().connection();
                  String subjectName = gObjRsrcBndle.getString("MRCaseSubject");                 
                  newworkFlowVO.setConnection(conn);
                  OrgUtilityImpl orgUtil = new OrgUtilityImpl(newworkFlowVO);
                  Map resultMap = orgUtil.getHierarchyByPostIDAndDescription(SessionHelper.getPostId(orgsMap).toString(), subjectName);
                          
                  List resultList = (List) resultMap.get("Result");
                  Long lStrHeirRefId = new Long(0);
                  if (resultList != null && resultList.size() > 0)
                  {
                      lStrHeirRefId = Long.parseLong(resultList.get(0).toString());
                  }
        		  
    			  llFromLevelId = wfImpl.getLevelFromPostMpg(gLngPostId.toString(), Long.valueOf(lStrHeirRefId));
    			  List lLstTemp =  lObjPensnBilldao.getOtherUsersList(String.valueOf(llFromLevelId) ,lStrHeirRefId.toString(),lStrOtherType,gLngPostId.toString(),"MRCASE",gObjRsrcBndle.getString("MRCaseSubject"));
        		 
/*    			  //for restricting sot to auditors start...
    			  for(int p=0;p<lLstTemp.size();p++)
              	  {
  					Object[] tuple = (Object[])lLstTemp.get(p);
  					if( tuple != null)
  					{
  						brnchCode = tuple[4].toString();
  						if(! lStrBranchCode.equals(brnchCode))
  						{
  							forwardFlag = "false";
  						}
  					}
  				  } 
    			  //for restricting sot to auditors end...
 */   			  
    			  if(lLstTemp.size()>0)
        		  {
        			  orgsMap.put("RegList", lLstTemp); 
        			  orgsMap.put("MRFlag", "Y"); 
        		  }
        		  RegFlag = true;
              }
        	  orgsMap.put("arrAuditorPostIds", arrAuditorPostIds); 
        	  //orgsMap.put("forwardFlag",forwardFlag);
        	  lObjResult.setResultValue(orgsMap);
        	  lObjResult.setViewName("MRCaseSelectionFrwd");
         }
         catch (Exception e)
         {
         	gLogger.error(" Error occured while getting upper post... " + e, e);
         }
         return lObjResult;
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
    //to open pop up for vito2
    public ResultObject openPouupForVito2(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");        
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrDocumentId = bundleConst.getString("WF.DocId.MRCase");
        Long lLngDocumentId = Long.parseLong(lStrDocumentId);
        String lStrLocationId = SessionHelper.getLocationId(inputMap).toString();
        
        resObj.setResultValue(inputMap);
        resObj.setViewName("OpenPopupForVito2");
        return resObj;
    }
    //Method to generate MR vito2  ----------------------------------------------------------start
    public ResultObject generateVito2(Map objectArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        try
        {
            setSessionInfo(objectArgs);
            String vitoCode = "";
            String vitoMessage = "";
            String printString = "";
            Map printMap = new HashMap();
            
            printMap = genPrintableVito2FormatForMR(objectArgs);
            
            vitoCode = (String) printMap.get("vitoCode");
            if (vitoCode != null)
            {
                vitoMessage = "Vito Code " + vitoCode + " Generated Successfully";
                printString = (String) printMap.get("printString");
            }
                
            StringBuilder lStrBldXML = new StringBuilder();
            lStrBldXML.append("<XMLDOC>");
            lStrBldXML.append("<vitoMessage>");
            lStrBldXML.append(vitoMessage);
            lStrBldXML.append("</vitoMessage>");
            lStrBldXML.append("<printString>");
            lStrBldXML.append(printString);
            lStrBldXML.append("</printString>");
            lStrBldXML.append("</XMLDOC>");
            
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
            objectArgs.put("ajaxKey", lStrResult);
            objRes.setViewName("ajaxData");
            objRes.setResultValue(objectArgs);
            //System.out.println(printString);
        }
        catch (Exception e)
        {
           gLogger.error("Error is"+e);
        }
        
        return objRes;
    }
    public Map genPrintableVito2FormatForMR(Map objectArgs) 
    {
        HashMap printMap = new HashMap();
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        String strSelectedRequests=null;
        String strSelectedAuditors=null;
        String printString=null;
        String lStrPrintHdr = null;
        String lStrPrintHead = null;
        String lStrPrintSummaryHead = null;
        String lStrSummaryRecord = null;
        String lStrPrintRecord = null;
        StringBuffer sbLine = new StringBuffer();
        Map vitoMap = new HashMap();
        ArrayList<TrnPensionMedRembrsmnt> lObjTrnPensionMedRembrsmntArray = new ArrayList<TrnPensionMedRembrsmnt>();
        BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(BillVitoRegister.class, serv.getSessionFactory());
        BigDecimal lBDAudPostId = new BigDecimal(0);
        List AuditorNamesAndPosts = null;
        String vitoCode = null; 
        String vitoType = null;
        BillVitoRegister vitoVO = new BillVitoRegister();
        String returnVitoCode = null;
        
        int k=0;
        int count=0;
        BigDecimal sumGrantAmnt=new BigDecimal(0);
        int flag = 0;
        
        String[] NameArrayOfAuditors = new String[5000];
        Integer[] totalRequestsForEachAuditorArray = new Integer[5000];
        BigDecimal[] remAmountSum = new BigDecimal[5000]; 
        
        int p = 0;
        BigDecimal sumAllRemAmounts = new BigDecimal(0);
        int countAllRequests = 0;
        //----------------------------------
        int listSize = 0;
        List SelectedRequests = null;
        
        try
        {
            setSessionInfo(objectArgs);
            String fromDate = StringUtility.getParameter("fromDate", request);
            String toDate = StringUtility.getParameter("toDate", request);
            
            List MRCasesListWithinPeriod = MRCaseDAO.getMRCasesListWithinPeriod(fromDate,toDate);
            listSize = MRCasesListWithinPeriod.size();
            
            //BigDecimal BDPostId = new BigDecimal(gLngPostId);
            //AuditorNamesAndPosts = MRCaseDAO.getAuditorNameListWithinPeriod(fromDate,toDate,BDPostId);
            SelectedRequests = MRCaseDAO.getIdsForWithinPeriodRequests(fromDate,toDate);
            
            if(SelectedRequests.size() == 1)
            {
            	strSelectedRequests = SelectedRequests.get(0).toString();
            }
            else
            {
            	for(int i=0;i<SelectedRequests.size();i++)
            	{
            		strSelectedRequests = strSelectedRequests + "," + SelectedRequests.get(i).toString();
            	}
            }
            
            if(MRCasesListWithinPeriod != null)
            {    
            	lObjTrnPensionMedRembrsmntArray = MRCaseDAO.getTrnPensionMedRembrsmntVOArray(strSelectedRequests);
            	String auditName = new String();
            	
            	//for generating vito code---------------
            	//vitoType = DBConstants.VITO_MRCASE_FOR_AUD;
            	//vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
            	
                for(int i=0;i<listSize;i++)
                {                   
                    Long vitoId = IFMSCommonServiceImpl.getNextSeqNum("bill_vito_register", objectArgs);
                    vitoVO = new BillVitoRegister();
                    vitoVO.setVitoId(vitoId);
                    //vitoVO.setVitoCode(vitoCode);
                    vitoVO.setVitoType(vitoType);
                    vitoVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                    vitoVO.setBillNo(Long.parseLong(SelectedRequests.get(i).toString()));
                    vitoVO.setCreatedDate(new Date(System
                            .currentTimeMillis()));
                    vitoVO.setCreatedUserId(gLngUserId);
                    vitoVO.setCreatedPostId(gLngPostId);
                    vitoVO.setDbId(gLngDBId);
                    vitoDaoImpl.create(vitoVO);
                }   
            	//---------------------------------------
                lStrPrintHdr = printMRRecordHdrForVito2(objectArgs,vitoCode);
                sbLine.append(lStrPrintHdr);
                //--------------------------
                lBDAudPostId = new BigDecimal(gLngPostId);
                auditName = MRCaseDAO.getAuditorNameFromPostId(lBDAudPostId);
                //-------------------------
				lStrPrintHead = "\n"+getChar(35, " ")+"Auditor Name :";
				lStrPrintHead = lStrPrintHead + auditName;
				lStrPrintHead = lStrPrintHead + "\n";
				lStrPrintHead = lStrPrintHead +getChar(120, "-");
				lStrPrintHead = lStrPrintHead +"\n";
				lStrPrintHead = lStrPrintHead +getChar(2, " ");
				lStrPrintHead = lStrPrintHead +"Ref No";
				lStrPrintHead = lStrPrintHead +getChar(2, " ");
				lStrPrintHead = lStrPrintHead +"PPO No";
				lStrPrintHead = lStrPrintHead +getChar(15, " ");
				lStrPrintHead = lStrPrintHead +"Pensioner Name";
				lStrPrintHead = lStrPrintHead +getChar(20, " ");
				lStrPrintHead = lStrPrintHead +"Rembrsmnt Amount";
				lStrPrintHead = lStrPrintHead +getChar(2, " ");
				lStrPrintHead = lStrPrintHead +"Granted Amount";
				lStrPrintHead = lStrPrintHead +getChar(2, " ");
				lStrPrintHead = lStrPrintHead +"Authrsd By";
				lStrPrintHead = lStrPrintHead +getChar(2, " ");
				lStrPrintHead = lStrPrintHead +"Status";
				lStrPrintHead = lStrPrintHead + "\n";
				lStrPrintHead = lStrPrintHead +getChar(120, "-");
				
				sbLine.append(lStrPrintHead);
				
		        for(k=0;k<lObjTrnPensionMedRembrsmntArray.size();k++)
            	{
            		if(lObjTrnPensionMedRembrsmntArray.get(k).getAuditorPostId().equals(lBDAudPostId))
            		{
            			flag++;
            			lStrPrintRecord =  printMRRecordForVito2(objectArgs,lObjTrnPensionMedRembrsmntArray.get(k));
            			TrnPensionMedRembrsmnt trnPensionMedRembrsmntVO = lObjTrnPensionMedRembrsmntArray.get(k);
            			if(trnPensionMedRembrsmntVO.getStatus().toString().equals("60"))
            			{
            				sumGrantAmnt = sumGrantAmnt.add(trnPensionMedRembrsmntVO.getGrantAmnt());
            			}
            			sbLine.append(lStrPrintRecord);
            		}
            	}
		        
		        sbLine.append(getChar(120, "-"));
		        sbLine.append("\n");
		        sbLine.append("Total Requests : ");
		        sbLine.append(flag);
		        sbLine.append(getChar(35, " "));
		        sbLine.append("Total Granted Amount : ");
		        sbLine.append(getChar(21-(sumGrantAmnt.toString().length()), " "));
		        sbLine.append(sumGrantAmnt);
		        sbLine.append("\n");
		        sbLine.append(getChar(120, "-"));
		        sbLine.append("\n");
            }
            if(vitoCode != null)
            {
                returnVitoCode = vitoCode+"";
            }
            printMap.put("vitoCode", returnVitoCode);
            printMap.put("printString", sbLine.toString());
            //System.out.print(sbLine.toString());
        }
        catch (Exception e)
        {
            gLogger.error("Exception is " + e,e);
        }           
        return printMap;
    }
    private String printMRRecordHdrForVito2(Map objectArgs,String vitoCode)
    {
    	setSessionInfo(objectArgs);
    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        StringBuffer sbLine = new StringBuffer();
        String lstrName = null;
        ArrayList result1 = new ArrayList(); 
        Object[] tuple;
        
        String lStrInwrdPersonName = null;
        
	    try
	    {
	    	result1 = (ArrayList) MRCaseDAO.getLocationReport(gStrLocationCode,gLngLangId); 
        	if (result1 != null && result1.size() > 0)
            {
                for(int i=0; i< result1.size(); i++)
                {
                	tuple =  (Object[]) result1.get(i); 
                	
                	sbLine.append(getChar(40, " "));
                	sbLine.append(String.valueOf(tuple[0]));
                	sbLine.append(getChar(40, " "));
                	sbLine.append(String.valueOf(tuple[1]));
            	}      
            }
			SimpleDateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa") ;
			Calendar c = Calendar.getInstance();
			String time = formatter.format(c.getTime());
			
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currDate = sdf.format(gDtCurrDt);
			
            lStrInwrdPersonName = MRCaseDAO.getUserName(gLngUserId);
            
            sbLine.append("\n\n");
            sbLine.append(getChar(35, " "));
            sbLine.append("MR Vito For Approved And Rejected Requests");
            sbLine.append("\n");
			sbLine.append(getChar(10, " "));
			sbLine.append("Print Date : ");  
			sbLine.append(time);
			sbLine.append(getChar(10, " "));
			sbLine.append("Entry By : ");  
			sbLine.append(lStrInwrdPersonName);
			sbLine.append("\r\n");
			sbLine.append(getChar(10, " "));
			sbLine.append("Vito For Date : ");  
			sbLine.append(currDate);
			sbLine.append("\r\n");
			sbLine.append(getChar(40, " "));
			sbLine.append("VitoCode : ");  
			sbLine.append(vitoCode);
			sbLine.append("\n"+getChar(120, "-"));
	    }
	    catch(Exception e)
	    {
	    	gLogger.error("Exception is " + e,e);
	    }
		  return sbLine.toString();
    }

    public String printMRRecordForVito2(Map objectArgs,TrnPensionMedRembrsmnt TrnPensionMedRembrsmntVo)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        StringBuffer sbLine = new StringBuffer();
        String lstrName = null;
        ArrayList result1 = new ArrayList(); 
        Object[] tuple;
        
        try
        {
	        sbLine.append("\n");
	        sbLine.append(getChar(8-(TrnPensionMedRembrsmntVo.getRefNum().toString().length()), " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getRefNum());
	        sbLine.append(getChar(2, " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getPpoNo());
	        sbLine.append(getChar(21-(TrnPensionMedRembrsmntVo.getPpoNo().toString().length()), " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getPensionerName());
	        sbLine.append(getChar(33-(TrnPensionMedRembrsmntVo.getPensionerName().toString().length()), " "));
	        sbLine.append(getChar(17-(TrnPensionMedRembrsmntVo.getRemAmnt().toString().length()), " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getRemAmnt());
	        sbLine.append(getChar(2, " "));
	        sbLine.append(getChar(14-(TrnPensionMedRembrsmntVo.getGrantAmnt().toString().length()), " "));
	        sbLine.append(TrnPensionMedRembrsmntVo.getGrantAmnt());
	        sbLine.append(getChar(2, " "));
	        
	        BigDecimal BDAuthoriserPostId = TrnPensionMedRembrsmntVo.getAuthoriserPostId();
	        String lBDAuthoriserName = MRCaseDAO.getAuditorNameFromPostId(BDAuthoriserPostId);
	        
	        sbLine.append(lBDAuthoriserName);
	        sbLine.append(getChar(10-(lBDAuthoriserName.length()), " "));
	        sbLine.append(getChar(2, " "));		
	        sbLine.append(getChar(8-(lBDAuthoriserName.length()), " "));
	        int status = TrnPensionMedRembrsmntVo.getStatus();
	        if(status == 60)	
	        {
	        	sbLine.append("APPROVED");
	        }
	        else if(status == 70)	
	        {
	        	sbLine.append("REJECTED");
	        }
	        
	        sbLine.append("\r\n");
        }   
        catch (Exception e)
        {
            gLogger.error("Exception is " + e,e);
        }  
        return sbLine.toString();
    }
    
    //Method to generate MR vito2  ----------------------------------------------------------end
    
    public ResultObject openPouupForMRBill(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");        
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrDocumentId = bundleConst.getString("WF.DocId.MRCase");
        Long lLngDocumentId = Long.parseLong(lStrDocumentId);
        String lStrLocationId = SessionHelper.getLocationId(inputMap).toString();
        CommonPensionServiceImpl CommonPensionService = new CommonPensionServiceImpl();
        CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        TrnPensionRqstHdr trnPnsnRqstHdrVo = new TrnPensionRqstHdr();
        List listPnsnHeadCode = null;
        String lStrHeadCodeDesc = null;
        setSessionInfo(inputMap);
        BigDecimal lBDUserId = (new BigDecimal(gLngUserId));
        BigDecimal lBDPostId = (new BigDecimal(gLngPostId));
        
        try
        {
	    	CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            List lLstAuditorBankCodeList = lObjCommonPensionDAO.getAuditorBankCodeList(lBDUserId, lBDPostId);
            ArrayList<ComboValuesVO> arrBankCode = new ArrayList<ComboValuesVO>();
            if (lLstAuditorBankCodeList != null && !lLstAuditorBankCodeList.isEmpty())
            {
                Iterator it = lLstAuditorBankCodeList.iterator();
                while (it.hasNext())
                {
                    ComboValuesVO lObjComboValuesVO = new ComboValuesVO();
                    lObjComboValuesVO = (ComboValuesVO) it.next();
                    arrBankCode.add(lObjComboValuesVO);
                }
            }
            inputMap.put("ListAuditorBankCode", arrBankCode);
            List lLstPnsnHeadCode = (List) lObjCommonPensionDAO.getAllHeadCode();
            inputMap.put("listHeadCode", lLstPnsnHeadCode);
	        
	        resObj.setResultValue(inputMap);
	        resObj.setViewName("OpenPopupForMRBill");
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
    
//  --------------------------------------------MR CASE ends------------------------------------------------->
    
    
//  --------------------------------------------MR BILL starts----------------------------------------------->
    public ResultObject createMRBill(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        BigDecimal[] AuthoriserPostId = new BigDecimal[10000];
        String[] AuthoriserName = new String[10000];
        
        List<String> AuthoriserList = new ArrayList<String>();

        try
        {
        	setSessionInfo(inputMap);
        	
        	String BillStatusFlag = StringUtility.getParameter("MRBillStatus", request);//for adjusting the viewing part of a bill
        	
        	String  lStrFromDate = StringUtility.getParameter("fromInwdDate", request);
        	String  lStrToDate = StringUtility.getParameter("toInwdDate", request);
        	String  lStrBranchCode = StringUtility.getParameter("cmbBranchCode", request);
        	String  lStrBankCode = StringUtility.getParameter("cmbBankCode", request);
        	String  lStrHeadCode = StringUtility.getParameter("cmbHeadCode", request);
        	
        	BigDecimal totalGrantAmnt = new BigDecimal(0);
        	
        	List MRBillDetailsList = MRCaseDAO.getMRBillData(lStrFromDate,lStrToDate,lStrBranchCode,new BigDecimal(lStrHeadCode));
        	
        	if (MRBillDetailsList !=null && ! MRBillDetailsList.isEmpty()) 
        	{
	        	for(int i=0;i<MRBillDetailsList.size();i++)
	            {
	                Object[] tuple = (Object[])MRBillDetailsList.get(i);           	
	                
	                if(!(tuple[4].toString()).equals(""))
	                {
	                	totalGrantAmnt = totalGrantAmnt.add(new BigDecimal(tuple[4].toString()));
	                }
	            }
        	}
        	inputMap.put("MRBillTokenNo","");
        	inputMap.put("Bank",lStrBankCode);
        	inputMap.put("Branch",lStrBranchCode);
        	inputMap.put("HeadCode",lStrHeadCode);
        	inputMap.put("TotalGrantAmount",totalGrantAmnt);
        	inputMap.put("BillStatusFlag",BillStatusFlag);
        	
        	inputMap.put("MRBillDetailsList",MRBillDetailsList);
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
        }
        
        resObj.setResultValue(inputMap);
        //resObj.setViewName("MRBills");
        return resObj;
    }
    
    /**
     * Saves Reimbursement Bill Data in  TRN_PENSION_BILL_HDR and TRN_PENSION_MED_REMBRSMNT
     * @param Map:lMapInput
     * @return ResultObject
     */    	
    public ResultObject saveMRBill(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        List<TrnPensionBillDtls> lLstPensionBillDtls = null;
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,serv.getSessionFactory());
        //Long lLngPnsnTokenNo = 0L;
        MRCaseServiceImpl lObjCaseServiceImpl = new MRCaseServiceImpl();
        
        String lStrBillNo = null;
        
    	lStrBillNo = (String) inputMap.get("billNo");
    	Long lPnsnTokenNo = (Long) inputMap.get("PnsnTokenNo");
        
        String lStrBillType="MR";

        try
        {
        	setSessionInfo(inputMap);
        	lObjTrnPensionBillHdr = (TrnPensionBillHdr) inputMap.get("MRTrnPensionBillHdrVO");
        	
        	// Insert date into TrnPensuionBillHdr.
        	lObjTrnPensionBillHdr.setBillType(lStrBillType);
        	lObjTrnPensionBillHdr.setBillNo(lStrBillNo);
            
 /*       	if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
            {
                lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo.toString());
            }
 */           
        	lObjTrnPensionBillHdrDAO.create(lObjTrnPensionBillHdr);
        	
        	//insert multiple records in trn_pension_bill_dtls
        	lLstPensionBillDtls = (List<TrnPensionBillDtls>) inputMap.get("MRTrnPensionBillDtlsVO");
        	TrnPensionBillDtls lObjPensionBillDtls = null;
            Iterator<TrnPensionBillDtls> lItrMR = lLstPensionBillDtls.iterator();
            while (lItrMR.hasNext())
            {
            	lObjPensionBillDtls = lItrMR.next();
            	saveMRDtls(lObjPensionBillDtls, inputMap);
            }
            inputMap.put("MRTrnPensionBillDtlsVO", lLstPensionBillDtls);
            inputMap.put("PensionType", "MR");
            
            String lOBPMBillNo = lObjTrnPensionBillHdr.getBillNo();
            //lPnsnTokenNo = Long.parseLong(lObjTrnPensionBillHdr.getTokenNo());
        	Long lLngPnsnBillNo = Long.parseLong(lOBPMBillNo);
/*        	if(lPnsnTokenNo != null)
        	{
        		OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,serv.getSessionFactory());
               	lObjTokenStatusDAO.updateTokenStatus(lPnsnTokenNo, gStrLocationCode, lLngPnsnBillNo, gLngUserId, gLngPostId,BillProcConstants.INWARD_TYPE_BILL);
        	}
*/        	objRes.setResultValue(inputMap);
        }
        catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
        
        //lObjCaseServiceImpl.getSavedMRCases(inputMap);
        return objRes;
    } 
	private void saveMRDtls(TrnPensionBillDtls lObjPensionBillDtls, Map inputMap) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	
    	try {
    		TrnPensionBillDtlsDAO lObjPensionDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, serv.getSessionFactory());
    		lObjPensionBillDtls.setTrnPensionBillDtlsId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", inputMap)));
    		lObjPensionDtlsDAO.create(lObjPensionBillDtls);
		} 
    	catch (Exception e) {
			gLogger.error(" Error is : " + e, e); 
			throw(e);
		}
    }
	
	//Method to view saved MR Bill
	public ResultObject viewMRBillData(Map inputMap)
	{
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(srvcLoc.getSessionFactory());
        
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        TrnPensionBillDtls lObjTrnPensionBillDtlsVO = null;
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;

        TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,srvcLoc.getSessionFactory());
        TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, srvcLoc.getSessionFactory());
        TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,srvcLoc.getSessionFactory());
        
        String lStrBillType = "MR";
        String lStrCaseStatus ="APPROVED";
        String lStrTokenNo = null;
        Date lStrBillDate = null;
        Long lTrnPensionBillHdrPK = 0L;
        String lStrPPONo = null;
        String lStrPnsnerName = null;
        String lStrInwdNo = null;
        BigDecimal BDReimbrseAmnt = null;
        BigDecimal BDGrantedAmnt = null;
        BigDecimal BDAuthPostId = null;
        String lStrAuthName = null;
        Integer lIntSerialNo = 0;
        BigDecimal totalGrantAmnt = new BigDecimal(0);
        String lStrBankCode = null;
        String lStrBranchCode = null;
        String lStrHeadCode = null;
        String BillStatusFlag = "SavedMRBill";

		try
		{
            setSessionInfo(inputMap);
            String lStrBillNo = inputMap.get("billNo").toString();
            List lLstTrnPensionMedRembrsmnt = null;

            if(lStrBillNo != null && lStrBillNo.length() > 0 ) 
        	{
        		// Getting the ObjectVo of  TrnPensionBillHdrVO
        		lObjTrnPensionBillHdr = (TrnPensionBillHdr) lObjTrnPensionBillHdrDAO.getTrnPensionBillHdr(lStrBillNo,lStrBillType);
        		lStrBillDate = lObjTrnPensionBillHdr.getBillDate();
        		lStrTokenNo = lObjTrnPensionBillHdr.getTokenNo();
        		lTrnPensionBillHdrPK = lObjTrnPensionBillHdr.getTrnPensionBillHdrId();
        		lLstTrnPensionMedRembrsmnt = MRCaseDAO.getTrnPensionMedRembrsmnt(lTrnPensionBillHdrPK);
        	}
            if(lLstTrnPensionMedRembrsmnt != null && !lLstTrnPensionMedRembrsmnt.isEmpty())
        	{
            	for(int i=0;i<lLstTrnPensionMedRembrsmnt.size();i++)
        		{
	                Object[] tuple = (Object[])lLstTrnPensionMedRembrsmnt.get(i);           	
	                
	                if(!(tuple[4].toString()).equals(""))
	                {
	                	totalGrantAmnt = totalGrantAmnt.add(new BigDecimal(tuple[4].toString()));
	                }
        		}
        	}
            List lLstBankBrnchHdCode = MRCaseDAO.getBnkBrnchHdcodeList(lTrnPensionBillHdrPK);
            if(lLstBankBrnchHdCode != null && !lLstBankBrnchHdCode.isEmpty())
        	{
            	for(int i=0;i<lLstBankBrnchHdCode.size();i++)
        		{
	                Object[] tuple = (Object[])lLstBankBrnchHdCode.get(i);           	
	                lStrBankCode = tuple[0].toString();
	                lStrBranchCode = tuple[1].toString();
	                lStrHeadCode = tuple[2].toString();
        		}
        	}
            inputMap.put("MRBillTokenNo",lStrTokenNo);
            inputMap.put("Bank",lStrBankCode);
        	inputMap.put("Branch",lStrBranchCode);
        	inputMap.put("HeadCode",lStrHeadCode);
        	inputMap.put("TotalGrantAmount",totalGrantAmnt);
        	inputMap.put("MRBillDetailsList",lLstTrnPensionMedRembrsmnt);
        	inputMap.put("BillStatusFlag",BillStatusFlag);
		}
        catch (Exception e)
        {
        	gLogger.error(" Error is : " + e, e);
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }
        resObj.setResultValue(inputMap);
		return resObj;
	}
    
//  --------------------------------------------MR BILL ends------------------------------------------------->
}
