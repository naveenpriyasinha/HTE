package com.tcs.sgv.pension.service;


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
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.RevisionDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionerRivisionDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionerRivisionDtlsDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;

public class RevisionServiceImpl extends ServiceImpl implements RevisionService
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
    Date gDate = null;
	
	/**
	 * Method to create the pop up window for revision of pension, 
	 * i.e., load the revision page
	 * @param inputMap Map
	 * @return resObj ResultObject
	 * @author Aparna Kansara
	 */
    public ResultObject createRevision(Map inputMap) 
	{
		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
        List lLstObjTrnPensionerRivisionDtls = null;        
        
        String lStrNewStatus = bundleConst.getString("CMN.NEW");
        String lStrAppStatus = bundleConst.getString("STATUS.APPROVED");
        
        String lStrPPONo = null;
        String lStrPnsrCode = null;
        String lStrHeadCode = null;
        String lStrCommDate = null;
        String tiPer = null;
        try
        {
            setSessionInfo(inputMap);
            
            lStrPPONo = StringUtility.getParameter("PPONo", request);
            lStrHeadCode = StringUtility.getParameter("headCode", request);
            lStrCommDate = StringUtility.getParameter("commDate", request);
        	         	
        	RevisionDAOImpl lObjRevisionDAO = new RevisionDAOImpl(TrnPensionerRivisionDtls.class,srvcLoc.getSessionFactory());
        	
        	if(lStrPPONo != null && lStrPPONo.length() > 0) 
        	{
            	Long rqstId = lObjRevisionDAO.getCaseIdfromPpoNo(lStrPPONo, lStrNewStatus);
            	if(rqstId == 0L)
            	{
            		rqstId = lObjRevisionDAO.getCaseIdfromPpoNo(lStrPPONo, lStrAppStatus);
            	}           	
            	
            	TrnPensionRqstHdrDAO lObjRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,srvcLoc.getSessionFactory());
            	lObjTrnPensionRqstHdrVO = lObjRqstHdrDAO.read(rqstId);
        	}
        	
        	if(lObjTrnPensionRqstHdrVO != null)
        	{
        		// Getting the Value from TrnPensionRqstHdr... Start...
        		if(lObjTrnPensionRqstHdrVO.getPensionerCode() != null)
        		{
        			lStrPnsrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
        		}
        	}
        	
        	// Getting the ObjectVo of  TrnPensionerRivisionDtls... Start...
        	lLstObjTrnPensionerRivisionDtls = lObjRevisionDAO.getTrnPensionerRivisionDtls(lStrPnsrCode);
        	
        	if( lLstObjTrnPensionerRivisionDtls != null && !lLstObjTrnPensionerRivisionDtls.isEmpty())
        	{
        		inputMap.put("RevisionList", lLstObjTrnPensionerRivisionDtls);
        	}
        	
        	//get the da% that needs to be autopopulated for the new record
        	//tiPer = lObjRevisionDAO.getTiPercent(lStrHeadCode, lStrCommDate);
        	//gLogger.info("tiPer"+tiPer);
    		
        	inputMap.put("PPONo", lStrPPONo);
        	inputMap.put("PensionerCode", lStrPnsrCode);
        	//inputMap.put("TiPercent", tiPer);
        	inputMap.put("currDate", DBUtility.getCurrentDateFromDB());
        	
        	resObj.setResultValue(inputMap);
        	resObj.setViewName("createRevision");
        }
        catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		return resObj;
	}
    
    /**
     * Method to save the revision details
     * @param Map inputMap
     * @return ResultObject objRes
     * @author Aparna
     * 
     */
    public ResultObject saveRevision(Map inputMap) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        String lStrNewStatus = bundleConst.getString("CMN.NEW");
        
        
        TrnPensionerRivisionDtls lObjTrnPensionerRivisionDtls = null;
        TrnPensionerRivisionDtls lObjRivisionDtls = null;
        
        TrnPensionRqstHdr lObjTrnPensionRqstHdr = null;
        
        RevisionDAOImpl lObjRevisionDAO = new RevisionDAOImpl(TrnPensionerRivisionDtls.class,serv.getSessionFactory());
        StringBuilder lStrBldXML = null;
        String revCntr = null;
        String lStrPenCode = null;
        
        try
        {
        	setSessionInfo(inputMap);
        	//update rqst hdr record
        	//first find if a new record exists...if yes update it 
        	//if no...add a new record in trn_pension_rqst_hdr
        	String lPPONo = StringUtility.getParameter("txtPPONo", request);
        	
        	TrnPensionRqstHdrDAO lObjRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,serv.getSessionFactory());
        	
        	Long rqstId = lObjRevisionDAO.getCaseIdfromPpoNo(lPPONo, lStrNewStatus);
        	
        	//if(rqstId <= 0L && "Y".equals(lStrAppFlag))
        	if(rqstId <= 0L)
        	{
        		//insert new row
        		lObjTrnPensionRqstHdr = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdr");
        		if(lObjTrnPensionRqstHdr != null)
            	{
	        		lObjTrnPensionRqstHdr.setPensionRequestId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_rqst_hdr", inputMap));
	        		lObjRqstHdrDAO.create(lObjTrnPensionRqstHdr);
            	}
        		rqstId = lObjTrnPensionRqstHdr.getPensionRequestId();
        	}
        	else
        	{
        		//update existing record
        		lObjTrnPensionRqstHdr = (TrnPensionRqstHdr) inputMap.get("TrnPensionRqstHdr");
        		if(lObjTrnPensionRqstHdr != null)
            	{
            		lObjRqstHdrDAO.update(lObjTrnPensionRqstHdr);
            	}
        	}
        	
        	//save in revision table
        	lObjTrnPensionerRivisionDtls = (TrnPensionerRivisionDtls) inputMap.get("TrnPensionerRivisionDtls");
        	
        	if(lObjTrnPensionerRivisionDtls != null)
        	{
        		lStrPenCode = (lObjTrnPensionerRivisionDtls.getPensionerCode()).toString();
        //		lRevCntr = lObjTrnPensionerRivisionDtls.getRevisionCounter();
        	}
        	
        	TrnPensionerRivisionDtlsDAO lObjRivisionDtlsDAO = new TrnPensionerRivisionDtlsDAOImpl(TrnPensionerRivisionDtls.class,serv.getSessionFactory());
        	Long lLngRevisionPK = lObjRivisionDtlsDAO.getPKForRevisionDtls(lStrPenCode);
        	
        	if(lLngRevisionPK != 0L)
        	{
        		lObjRivisionDtls = new TrnPensionerRivisionDtls();
        		lObjRivisionDtls = lObjRivisionDtlsDAO.read(lLngRevisionPK);
        		lObjRivisionDtlsDAO.delete(lObjRivisionDtls);
        	}
        	lObjTrnPensionerRivisionDtls.setPensionerCode(lStrPenCode);
        	lObjTrnPensionerRivisionDtls.setPensionRequestId(rqstId);
        	saveRevisionDtls(lObjTrnPensionerRivisionDtls, inputMap);
            
        	TrnPensionerRivisionDtls lObjNewRecord = (TrnPensionerRivisionDtls)inputMap.get("NewRecord");
        	lObjNewRecord.setPensionerCode(lStrPenCode);
        	lObjNewRecord.setPensionRequestId(rqstId);
        	saveRevisionDtls(lObjNewRecord, inputMap);
        	revCntr = lObjNewRecord.getRevisionCounter().toString();
            
            inputMap.put("BasicAmount", lObjNewRecord.getBasicPension());
            inputMap.put("DPPercent", lObjNewRecord.getDpPercent());
            inputMap.put("CVPMonthlyAmount", lObjNewRecord.getCvpMonthlyAmount());
            inputMap.put("CVPAmount", lObjNewRecord.getCvpAmount());
            inputMap.put("DCRGAmount", lObjNewRecord.getDcrgAmount());
            inputMap.put("FP1Amount", lObjNewRecord.getFp1Amount());
            inputMap.put("FP2Amount", lObjNewRecord.getFp2Amount());
            inputMap.put("ORG_BF56", lObjNewRecord.getOrgBf11156());            
            inputMap.put("RED_BF56", lObjNewRecord.getRedBf11156());            
            inputMap.put("ORG_AF56", lObjNewRecord.getOrgAf11156());            
            inputMap.put("RED_AF56", lObjNewRecord.getRedAf11156()); 
            inputMap.put("ORG_AF60", lObjNewRecord.getOrgAf10560());                        
            inputMap.put("RED_AF60", lObjNewRecord.getRedAf10560());    
            inputMap.put("DAPercent", lObjNewRecord.getDaPercent());
            
            inputMap.put("revCntr", revCntr);
          
            lStrBldXML = getResponseXMLDoc(inputMap);
            
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
                    lStrBldXML.toString()).toString();
            
            inputMap.put("ajaxKey", lStrResult);
            
            objRes.setResultValue(inputMap);
            objRes.setViewName("ajaxData");
            
        }
        catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
        return objRes;
    }
	
    /**
     * Save the Revision Details of TrnPensionerRivisionDtls VO.
     * 
     * @param TrnPensionerRivisionDtls, Map
     *            lObjTrnPensionerRivisionDtls, inputMap
     * @return void
     * @author Aparna
     */ 
	public void saveRevisionDtls(TrnPensionerRivisionDtls lObjTrnPensionerRivisionDtls, Map inputMap) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	try {
    		
    		TrnPensionerRivisionDtlsDAO lObjRivisionDtls = new TrnPensionerRivisionDtlsDAOImpl(TrnPensionerRivisionDtls.class, serv.getSessionFactory());
    		Long lLngRevisionDtlId = null;

            lLngRevisionDtlId = IFMSCommonServiceImpl.getNextSeqNum("trn_pensioner_rivision_dtls", inputMap);
            lObjTrnPensionerRivisionDtls.setRivisionDtlsId(new Long(lLngRevisionDtlId));
            lObjRivisionDtls.create(lObjTrnPensionerRivisionDtls);
		} 
    	catch (Exception e) 
    	{
			gLogger.error(" Error is : " + e, e); 
			throw(e);
		}
    }
    
	private StringBuilder getResponseXMLDoc(Map lMapInput) throws Exception
    {		
        StringBuilder lStrBldHidPKs = new StringBuilder();
        
        try{
        	String revNo = lMapInput.get("revCntr").toString();

        	lStrBldHidPKs.append("<XMLDOC>");
            lStrBldHidPKs.append("<RevisionNo>");
            lStrBldHidPKs.append(revNo);
            lStrBldHidPKs.append("</RevisionNo>");
            lStrBldHidPKs.append("<Basic>");
            lStrBldHidPKs.append(lMapInput.get("BasicAmount").toString());
            lStrBldHidPKs.append("</Basic>");
            lStrBldHidPKs.append("<DPPer>");
            lStrBldHidPKs.append(lMapInput.get("DPPercent").toString());
            lStrBldHidPKs.append("</DPPer>");
            lStrBldHidPKs.append("<CVPMonthly>");
            lStrBldHidPKs.append(lMapInput.get("CVPMonthlyAmount").toString());
            lStrBldHidPKs.append("</CVPMonthly>");
            lStrBldHidPKs.append("<CVP>");
            lStrBldHidPKs.append(lMapInput.get("CVPAmount").toString());
            lStrBldHidPKs.append("</CVP>");
            lStrBldHidPKs.append("<DCRG>");
            lStrBldHidPKs.append(lMapInput.get("DCRGAmount").toString());
            lStrBldHidPKs.append("</DCRG>");
            lStrBldHidPKs.append("<FP1>");
            lStrBldHidPKs.append(lMapInput.get("FP1Amount").toString());
            lStrBldHidPKs.append("</FP1>");
            lStrBldHidPKs.append("<FP2>");
            lStrBldHidPKs.append(lMapInput.get("FP2Amount").toString());
            lStrBldHidPKs.append("</FP2>");
            lStrBldHidPKs.append("<ORGBF56>");
            lStrBldHidPKs.append(lMapInput.get("ORG_BF56").toString());
            lStrBldHidPKs.append("</ORGBF56>");
            lStrBldHidPKs.append("<REDBF56>");
            lStrBldHidPKs.append(lMapInput.get("RED_BF56").toString());
            lStrBldHidPKs.append("</REDBF56>");
            lStrBldHidPKs.append("<ORGAF56>");
            lStrBldHidPKs.append(lMapInput.get("ORG_AF56").toString());
            lStrBldHidPKs.append("</ORGAF56>");
            lStrBldHidPKs.append("<REDAF56>");
            lStrBldHidPKs.append(lMapInput.get("RED_AF56").toString());
            lStrBldHidPKs.append("</REDAF56>");
            lStrBldHidPKs.append("<ORGAF60>");
            lStrBldHidPKs.append(lMapInput.get("ORG_AF60").toString());
            lStrBldHidPKs.append("</ORGAF60>");
            lStrBldHidPKs.append("<REDAF60>");
            lStrBldHidPKs.append(lMapInput.get("RED_AF60").toString());
            lStrBldHidPKs.append("</REDAF60>");
            lStrBldHidPKs.append("<DAPer>");
            lStrBldHidPKs.append(lMapInput.get("DAPercent").toString());
            lStrBldHidPKs.append("</DAPer>");
            lStrBldHidPKs.append("<MESSAGE>");
            lStrBldHidPKs.append("Revision saved successfully. ");
            lStrBldHidPKs.append("</MESSAGE>");
            lStrBldHidPKs.append("</XMLDOC>");  
        }
        catch (Exception e) {
 			gLogger.error("Error is :" + e, e);
 			throw (e);
 		}
        return lStrBldHidPKs;
    }
	
    /**
     * Method to set session information
     * 
     */
	private void setSessionInfo(Map inputMap) throws Exception
    {
		try{
	        gLngLangId = SessionHelper.getLangId(inputMap);
	        gLngPostId = SessionHelper.getPostId(inputMap);
	        gLngUserId = SessionHelper.getUserId(inputMap);
	        gLngDBId = SessionHelper.getDbId(inputMap);
	        gDate = DBUtility.getCurrentDateFromDB();
		}
		catch (Exception e) {
 			gLogger.error("Error is :" + e, e);
 			throw (e);
 		}
    	
    }
}
	

