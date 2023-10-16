package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAO;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.TreasuryTransferCaseDAO;
import com.tcs.sgv.pension.dao.TreasuryTransferCaseDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionTransferDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionTransferDtlsDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionTransferDtls;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

public class TreasuryTransferCaseServiceImpl extends ServiceImpl implements TreasuryTransferCaseService {
	
    /* Global Variable for LangId */
    private Long gLngLangId = null;
       
    /* Global Variable for DB Id */
    private String gStrLocCode = null;
    
    /* Global Variable for PostId */
    private Long gLngPostId = null;

	/* Global Variable for UserId */
    private Long gLngUserId = null;

    /* Global Variable for Current Date */
    private Date gDtCurrDt = null;
    
    /* Global Variable for Logger Class */
    private final Log gLogger = LogFactory.getLog(getClass());
    
    private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

	/**
     * Save Send And Recive Transfer Case
     * 
     * @param inputMap
     * @return objRes ResultObject
     */
	public ResultObject saveTrsryTransferCase(Map inputMap)
    {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        String lStrSaveStatus = inputMap.get("SaveFlage").toString();

        try
        {
        	if(lStrSaveStatus.length() > 0 && lStrSaveStatus.equalsIgnoreCase("SaveSendCase"))
        	{
        		saveSendTrsryTransferCase(inputMap);
                inputMap.put("MESSAGECODE", (long) 2101);
        	}
        	else if(lStrSaveStatus.length() > 0 && lStrSaveStatus.equalsIgnoreCase("SaveReceiveCase"))
        	{
        		saveReceiveTrsryTransferCase(inputMap);
                inputMap.put("MESSAGECODE", (long) 2102);
        	}            

    		resObj.setResultValue(inputMap);
    		resObj.setViewName("ajaxData");
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }

        return resObj;
    }
	
	/**
     * saveSendTrsryTransferCase
     * 
     * @param Map inputMap
     * 
     */
	public void saveSendTrsryTransferCase(Map inputMap) throws Exception
    {
		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        
        TrnPensionRqstHdr lObjPensionRqstHdrVO = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdrVO");
        String lStrNewState = inputMap.get("NewState").toString();
        String lStrNewTreasury = inputMap.get("NewTreasuryCode").toString();
        
        TrnPensionRqstHdrDAOImpl lObjPensionRqstHdrDAOImpl = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, srvcLoc.getSessionFactory());
        TrnPensionTransferDtlsDAO lObjTransferDtlsDAO = new TrnPensionTransferDtlsDAOImpl(TrnPensionTransferDtls.class,srvcLoc.getSessionFactory());
        
        TrnPensionTransferDtls lObjTransferDtlsVO = new TrnPensionTransferDtls();
        
        String lStrStatusGUJ = bundleConst.getString("STATUS.CLOSEFORTRNFGUJ");
        String lStrStatusOTHR = bundleConst.getString("STATUS.CLOSEFORTRNFOTHR");
        String lStrCaseTnfrGUJ = bundleConst.getString("STATUS.CASETRNFGUJ");
        String lStrCaseTnfrOTHR = bundleConst.getString("STATUS.CASETRNFOTHR");
        String lStrDPPFLOCCODE = bundleConst.getString("CONST.DPPFLOCCODE");
        String lStrsetStatus = bundleConst.getString("STATUS.TRANSFERED");
        
        String lStrPPONo = lObjPensionRqstHdrVO.getPpoNo();
        String lStrPnsnerCode = lObjPensionRqstHdrVO.getPensionerCode();
                        
        try
        {	
        	setSessionInfo(inputMap);
        	
        	if(lStrNewState != null && lStrNewState.equalsIgnoreCase("1"))
        	{
	        	lObjPensionRqstHdrVO.setStatus(lStrStatusGUJ);
	        	TrnPensionRqstHdr CloneobjPensionRqstHdr = (TrnPensionRqstHdr) lObjPensionRqstHdrVO.clone(); 
	        	lObjPensionRqstHdrDAOImpl.update(lObjPensionRqstHdrVO);
	        	
	        	CloneobjPensionRqstHdr.setPensionRequestId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_rqst_hdr", inputMap));
	        	CloneobjPensionRqstHdr.setLocationCode(lStrNewTreasury);
	        	CloneobjPensionRqstHdr.setStatus(lStrCaseTnfrGUJ);	        	
	        	CloneobjPensionRqstHdr.setCreatedUserId(new BigDecimal(gLngUserId));
	        	CloneobjPensionRqstHdr.setCreatedPostId(new BigDecimal(gLngPostId));
	        	CloneobjPensionRqstHdr.setCreatedDate(gDtCurrDt);
	        	lObjPensionRqstHdrDAOImpl.create(CloneobjPensionRqstHdr);
        	}
        	else
        	{
        		lObjPensionRqstHdrVO.setStatus(lStrStatusOTHR);
        		TrnPensionRqstHdr CloneobjPensionRqstHdr = (TrnPensionRqstHdr) lObjPensionRqstHdrVO.clone(); 
	        	lObjPensionRqstHdrDAOImpl.update(lObjPensionRqstHdrVO);
	        		        	
	        	CloneobjPensionRqstHdr.setPensionRequestId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_rqst_hdr", inputMap));
	        	CloneobjPensionRqstHdr.setLocationCode(lStrDPPFLOCCODE);
	        	CloneobjPensionRqstHdr.setStatus(lStrCaseTnfrOTHR);
	        	lObjPensionRqstHdrDAOImpl.create(CloneobjPensionRqstHdr);
        	}
        	
        	
        	// Insert Data into Transfer Detail Table.
        	
        	lObjTransferDtlsVO.setTransferDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_transfer_dtls", inputMap));
        	lObjTransferDtlsVO.setPpoNo(lStrPPONo);
        	lObjTransferDtlsVO.setPensionerCode(lStrPnsnerCode);
        	lObjTransferDtlsVO.setTransferDate(gDtCurrDt);
        	lObjTransferDtlsVO.setFromLocation(lObjPensionRqstHdrVO.getLocationCode().toString());
        	lObjTransferDtlsVO.setStatus(lStrsetStatus);
        	lObjTransferDtlsVO.setTransferPostId(new BigDecimal(gLngPostId));
        	lObjTransferDtlsVO.setCreatedUserId(new BigDecimal(gLngUserId));
        	lObjTransferDtlsVO.setCreatedPostId(new BigDecimal(gLngPostId));
        	lObjTransferDtlsVO.setCreatedDate(gDtCurrDt);
            
        	lObjTransferDtlsDAO.create(lObjTransferDtlsVO);
        	
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw(e);
        }
    }
    
	/**
     * saveReceiveTrsryTransferCase
     * 
     * @param Map inputMap
     *
     */
	public void saveReceiveTrsryTransferCase(Map inputMap) throws Exception
	{
		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        
        String lStrNewTreasury = null;
        String lStrPensionerCode = null;
        String lStrPPONO = null;
        String lStrNewAccountNo = null;
        String lStrNewBank = null;
        String lStrNewBranch = null;
        
        String[] lStrPPONOAry = (String[])inputMap.get("TrsryPPONOAry");
        
        String[] lStrACNoAry = (String[])inputMap.get("NewACNoAry");
    	String[] lStrBankAry = (String[]) inputMap.get("NewBankAry");
    	String[] lStrBranchAry = (String[]) inputMap.get("NewBranchAry");
    	        
        TrnPensionRqstHdrDAOImpl lObjTrnPensionRqstHdrDAOImpl = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, srvcLoc.getSessionFactory());
        MstPensionerHdrDAOImpl lObjMstPensionerHdrDAOImpl = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory());
        MstPensionerDtlsDAOImpl lObjMstPensionerDtlsDAOImpl = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class,srvcLoc.getSessionFactory());
        TrnPensionTransferDtlsDAO lObjTransferDtlsDAO = new TrnPensionTransferDtlsDAOImpl(TrnPensionTransferDtls.class,srvcLoc.getSessionFactory());
        
        TreasuryTransferCaseDAO lobjTransferCaseDAO = new TreasuryTransferCaseDAOImpl(srvcLoc.getSessionFactory());
               
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
        MstPensionerHdr lObjMstPensionerHdrVO = null;
        MstPensionerDtls lObjMstPensionerDtlsVO = null;
        TrnPensionTransferDtls lObjTransferDtlsVO = null;
        
        Long lTrnsfrDtlsPK = null;
                
        String lStrStatusContinue = bundleConst.getString("STATUS.CONTINUE");
        String lStrStatusNO = bundleConst.getString("ACTV.NO");
        String lStrgetStatus = bundleConst.getString("STATUS.TRANSFERED");
        String lStrsetStatus = bundleConst.getString("STATUS.RECEIVED");
        String lStrOldLocId = null;
        
        try
        {
            setSessionInfo(inputMap);
            
            for(int i=0; i < lStrPPONOAry.length ; i++)
            {
                if(lStrACNoAry[i] != null && lStrACNoAry[i].length() > 0 && 
                        lStrBankAry[i] != null && lStrBankAry[i] != "-1" &&  
                        lStrBranchAry[i] != null && lStrBranchAry[i] != "-1" )
                {
                    lStrPPONO = lStrPPONOAry[i].trim();
                    lStrNewAccountNo = lStrACNoAry[i].trim();
                    lStrNewBank = lStrBankAry[i].trim();
                    lStrNewBranch = lStrBranchAry[i].trim();
                    
                    lObjTrnPensionRqstHdrVO = lobjTransferCaseDAO.getTrnPensionRqstHdrForReceive(lStrPPONO,gStrLocCode);
                    lStrPensionerCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
                    lObjMstPensionerHdrVO = lObjMstPensionerHdrDAOImpl.getMstPensionerHdrDtls(lStrPensionerCode);
                    lObjMstPensionerDtlsVO = lobjTransferCaseDAO.getMstPensionerDtls(lStrPensionerCode);                        
                         
                    lStrOldLocId = lObjMstPensionerHdrVO.getLocationCode(); // Old Location Code.
                    
                    if(lObjTrnPensionRqstHdrVO != null)
                    {
                        lStrNewTreasury = lObjTrnPensionRqstHdrVO.getLocationCode();            
                    }
            
                	///// UPDATE  TrnPensionRqstHdr
                	
            		lObjTrnPensionRqstHdrVO.setStatus(lStrStatusContinue);
            		lObjTrnPensionRqstHdrVO.setUpdatedUserId(new BigDecimal(gLngUserId));
            		lObjTrnPensionRqstHdrVO.setUpdatedPostId(new BigDecimal(gLngPostId));
            		lObjTrnPensionRqstHdrVO.setUpdatedDate(gDtCurrDt);
            		
            		lObjTrnPensionRqstHdrDAOImpl.update(lObjTrnPensionRqstHdrVO);
            		inputMap.put("PensionRequestPK", lObjTrnPensionRqstHdrVO.getPensionRequestId());
            		
            		///// UPDATE MstPensionerHdr
            		
            		lObjMstPensionerHdrVO.setLocationCode(lStrNewTreasury);
            		lObjMstPensionerHdrVO.setUpdatedUserId(new BigDecimal(gLngUserId));
            		lObjMstPensionerHdrVO.setUpdatedPostId(new BigDecimal(gLngPostId));
            		lObjMstPensionerHdrVO.setUpdatedDate(gDtCurrDt);
            		
            		lObjMstPensionerHdrDAOImpl.update(lObjMstPensionerHdrVO);
            		
            		///// CREATE New Entry And UPDATE Old Entry
            		
            		MstPensionerDtls cloneObjMstPensionerDtls = (MstPensionerDtls) lObjMstPensionerDtlsVO.clone();
            		
            		lObjMstPensionerDtlsVO.setActiveFlag(lStrStatusNO);
            		lObjMstPensionerDtlsVO.setUpdatedUserId(new BigDecimal(gLngUserId));
            		lObjMstPensionerDtlsVO.setUpdatedPostId(new BigDecimal(gLngPostId));
            		lObjMstPensionerDtlsVO.setUpdatedDate(gDtCurrDt);
            		
            		lObjMstPensionerDtlsDAOImpl.update(lObjMstPensionerDtlsVO);
        
            		cloneObjMstPensionerDtls.setPensionerDtlsId(IFMSCommonServiceImpl.getNextSeqNum("mst_pensioner_dtls", inputMap));
                	cloneObjMstPensionerDtls.setAcountNo(lStrNewAccountNo);
                	cloneObjMstPensionerDtls.setBankCode(lStrNewBank);
                	cloneObjMstPensionerDtls.setBranchCode(lStrNewBranch);
                	cloneObjMstPensionerDtls.setLocationCode(lStrNewTreasury);
                	cloneObjMstPensionerDtls.setCreatedUserId(new BigDecimal(gLngUserId));
                	cloneObjMstPensionerDtls.setCreatedPostId(new BigDecimal(gLngPostId));
                	cloneObjMstPensionerDtls.setCreatedDate(gDtCurrDt);
                	cloneObjMstPensionerDtls.setUpdatedUserId(null);
                	cloneObjMstPensionerDtls.setUpdatedPostId(null);
                	cloneObjMstPensionerDtls.setUpdatedDate(null);
            		
                	lObjMstPensionerDtlsDAOImpl.create(cloneObjMstPensionerDtls);
                	
                	///// Update Transfer Detail Table.
                	
                	lTrnsfrDtlsPK = lObjTransferDtlsDAO.getTrnsferDtlsPk(lStrPPONO, lStrgetStatus, lStrOldLocId);
                	
                	lObjTransferDtlsVO = lObjTransferDtlsDAO.read(lTrnsfrDtlsPK);
                	
                	lObjTransferDtlsVO.setReceiveDate(gDtCurrDt);
                	lObjTransferDtlsVO.setToLocation(lStrNewTreasury);
                	lObjTransferDtlsVO.setStatus(lStrsetStatus);
                	lObjTransferDtlsVO.setReceivePostId(new BigDecimal(gLngPostId));
                	lObjTransferDtlsVO.setUpdatedPostId(new BigDecimal(gLngPostId));
                	lObjTransferDtlsVO.setUpdatedUserId(new BigDecimal(gLngUserId));
                	lObjTransferDtlsVO.setUpdatedDate(gDtCurrDt);
                	
                	lObjTransferDtlsDAO.update(lObjTransferDtlsVO);
                	
                	insertIntoWorkFlow(inputMap);
                }
            }   
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw(e);
        }
	}
	
	private void insertIntoWorkFlow(Map inputMap) throws Exception
	{
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		Connection conn = serv.getSessionFactory().getCurrentSession().connection();
		String subjectName = bundleConst.getString("PENSION.CASESUBJECT");
		
		try 
		{
			WorkFlowVO workFlowVO = new WorkFlowVO();
			inputMap.put("receiveCase", "Y");
			
	        workFlowVO.setAppMap(inputMap);
	        workFlowVO.setConnection(conn);
	        workFlowVO.setCrtEmpId(SessionHelper.getEmpId(inputMap).toString());
	        workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
	        workFlowVO.setCrtUsr(SessionHelper.getUserId(inputMap).toString());
	        workFlowVO.setFromPost(SessionHelper.getPostId(inputMap).toString());
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
	
	        workFlowVO.setActId(1);
	        workFlowVO.setDocId(Long.parseLong(bundleConst.getString("PENSION.CASEDOCUMENTID")));
	        workFlowVO.setJobRefId(inputMap.get("PensionRequestPK").toString());
	        workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
	        workFlowVO.setDbId(SessionHelper.getDbId(inputMap));
	        workFlowVO.setJobTitle("PENSIONCASE");
	        workFlowVO.setHierarchyFlag(1);
	        //Map returnMap = wfUtility.invokeWorkFlow(workFlowVO);
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
            throw(e);
		}
	}
	
	
	/**
     * getSendedTrsryTransferCases
     * 
     * @param Map
     *            inputMap
     * @return ResultObject
     */

    public ResultObject getSendedTrsryTransferCases(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        List lLstSendedCases = null;
        List listBank = null;
              
        try
        {
            setSessionInfo(inputMap);
            
            TreasuryTransferCaseDAO lobjTransferCaseDAO = new TreasuryTransferCaseDAOImpl(serv.getSessionFactory());
            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            listBank = lObjCommonPensionDAO.getAllBank(gLngLangId,gStrLocCode);
            lLstSendedCases = (List) lobjTransferCaseDAO.getTrasferCases(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error is : ", e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
        }

        inputMap.put("BankList", listBank);
        inputMap.put("SendedTransferCases", lLstSendedCases);
        resObj.setResultValue(inputMap);
        resObj.setViewName("viewTransferCases");

        return resObj;
    }
	
	
	/**
     * Get Treasury List For Transfer Case
     * 
     * @param inputMap
     * @return objRes ResultObject
     */
	public ResultObject getTransferTreasuryList(Map inputMap){
				
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{	
			ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
			setSessionInfo(inputMap);
			
			TreasuryTransferCaseDAOImpl objTransferCaseDAOImpl = new TreasuryTransferCaseDAOImpl(serv.getSessionFactory());
			
			List lstTreasury = objTransferCaseDAOImpl.getAllTreasuryLst();
			
			List lstState = objTransferCaseDAOImpl.getAllStateLst(gLngLangId);

			inputMap.put("TrsryList", lstTreasury);
			inputMap.put("StateList", lstState);
			
		}catch(Exception e)
		{
            gLogger.error("Error is : ", e);
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
		}
		
		resObj.setResultValue(inputMap);
		resObj.setViewName("trsryTransfer");
		
		return resObj;		
	}
	
	/**
     * It Gets the Pensioner Data like name and old trsury 
     * call when PPO No is entred.
     * 
     * @param inputMap
     * @return objRes ResultObject
     */
    public ResultObject getPensionerInfo(Map inputMap)
    {    	
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            
            setSessionInfo(inputMap);
            
            String strPPONO = request.getParameter("PPONO");
            String lStrTrsryID = null;
            String lStrSubTrsryID = null;
            
            TreasuryTransferCaseDAO objTransferCaseDAOImpl = new TreasuryTransferCaseDAOImpl(serv.getSessionFactory());
            
            List lobjPensionerDtl =  objTransferCaseDAOImpl.getPensionerDtl(strPPONO, gLngLangId, gStrLocCode);
            StringBuilder lStrGrant = new StringBuilder();
            
            
            if(lobjPensionerDtl.size() > 0){
            	Object[] lArrObj  = (Object[])lobjPensionerDtl.get(0);
            	
            	if(lArrObj[3].toString().equalsIgnoreCase("100051")){
            		lStrTrsryID = lArrObj[2].toString();
            		lStrSubTrsryID = "";
            	}else {
            		lStrTrsryID = lArrObj[4].toString();
            		lStrSubTrsryID = lArrObj[2].toString();
            	}
	            
				lStrGrant.append(" <PPOINFO> ");
				lStrGrant.append(" 		<PPONAME> ");
				lStrGrant.append(String.valueOf(lArrObj[0].toString()));
				lStrGrant.append(" 		</PPONAME> ");
				lStrGrant.append(" 		<PPOSTATE> ");
				lStrGrant.append(String.valueOf(lArrObj[1]));
				lStrGrant.append(" 		</PPOSTATE> ");
				lStrGrant.append(" 		<PPOTRSRY> ");
				lStrGrant.append(lStrTrsryID);
				lStrGrant.append(" 		</PPOTRSRY> ");
				lStrGrant.append(" 		<PPOSUBTRSRY> ");
				lStrGrant.append(lStrSubTrsryID);
				lStrGrant.append(" 		</PPOSUBTRSRY> ");
				lStrGrant.append(" </PPOINFO> ");
            } else {
            	            
				lStrGrant.append(" <PPOINFO> ");
				lStrGrant.append(" 		<PPONAME> ");
				lStrGrant.append("1");
				lStrGrant.append(" 		</PPONAME> ");
				lStrGrant.append(" 		<PPOSTATE> ");
				lStrGrant.append("");
				lStrGrant.append(" 		</PPOSTATE> ");
				lStrGrant.append(" 		<PPOTRSRY> ");
				lStrGrant.append("");
				lStrGrant.append(" 		</PPOTRSRY> ");
				lStrGrant.append(" 		<PPOSUBTRSRY> ");
				lStrGrant.append("");
				lStrGrant.append(" 		</PPOSUBTRSRY> ");
				lStrGrant.append(" </PPOINFO> ");
            }
            	
	            
            			
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);
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
    
    /**
     * Reject a Trnsfer Case By new Treasury....
     * 
     * @param inputMap
     * @return objRes ResultObject
     */
    public ResultObject rejectTransferCase(Map inputMap)
    {    	
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        
        
        String lStrPPONo = request.getParameter("PPONO");
        String lStrStatusContinue = bundleConst.getString("STATUS.CONTINUE");
        String lStrgetStatus = bundleConst.getString("STATUS.TRANSFERED");
        String lStrsetStatus = bundleConst.getString("STATUS.REJECTED");
        String lStrOldLocId = null;

        Long lTrnPensionRqstHdrPK = null;
        Long lTrnsfrDtlsPK = null;
        
        TrnPensionRqstHdr lTrnPensionRqstHdrVO = null;
        MstPensionerHdr lMstPensionerHdr = null;
        TrnPensionTransferDtls lObjTransferDtlsVO = null;
        
        TreasuryTransferCaseDAO lObjTransferCaseDAOImpl = new TreasuryTransferCaseDAOImpl(serv.getSessionFactory());
        MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,serv.getSessionFactory());
    	TrnPensionTransferDtlsDAO lObjTransferDtlsDAO = new TrnPensionTransferDtlsDAOImpl(TrnPensionTransferDtls.class,serv.getSessionFactory());
        
        try
        {
            
        	setSessionInfo(inputMap);
            
            /// Getting a pk of TrnPensionRqstHdr
            lTrnPensionRqstHdrPK =  lObjTransferCaseDAOImpl.getRejectedTransferCasePK(lStrPPONo, gStrLocCode);
            
            TrnPensionRqstHdrDAOImpl lObjTrnPensionRqstHdrDAOImpl = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,serv.getSessionFactory());
            
            // getting a Vo of TrnPensionRqstHdr
            lTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAOImpl.read(lTrnPensionRqstHdrPK);
            
            // getting a Vo of MstPensionerHdr.
            lMstPensionerHdr = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lTrnPensionRqstHdrVO.getPensionerCode());
            
            lStrOldLocId = lMstPensionerHdr.getLocationCode();
            
            // Update a TrnPensionRqstHdr.
            lTrnPensionRqstHdrVO.setStatus(lStrStatusContinue);
            lTrnPensionRqstHdrVO.setLocationCode(lStrOldLocId);
            
            lObjTrnPensionRqstHdrDAOImpl.update(lTrnPensionRqstHdrVO);
            
            ///// Update Transfer Detail Table.
                	
        	lTrnsfrDtlsPK = lObjTransferDtlsDAO.getTrnsferDtlsPk(lStrPPONo, lStrgetStatus, lStrOldLocId);
        	
        	lObjTransferDtlsVO = lObjTransferDtlsDAO.read(lTrnsfrDtlsPK);
        	
        	lObjTransferDtlsVO.setReceiveDate(gDtCurrDt);
        	lObjTransferDtlsVO.setToLocation(gStrLocCode);
        	lObjTransferDtlsVO.setStatus(lStrsetStatus);
        	lObjTransferDtlsVO.setReceivePostId(new BigDecimal(gLngPostId));
        	lObjTransferDtlsVO.setUpdatedPostId(new BigDecimal(gLngPostId));
        	lObjTransferDtlsVO.setUpdatedUserId(new BigDecimal(gLngUserId));
        	lObjTransferDtlsVO.setUpdatedDate(gDtCurrDt);
        	
        	lObjTransferDtlsDAO.update(lObjTransferDtlsVO);
            
            inputMap.put("MESSAGECODE", (long) 2103);
            objRes.setResultValue(inputMap);
            objRes.setViewName("ajaxData");			
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
        gStrLocCode = SessionHelper.getLocationCode(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
        
    }
}
