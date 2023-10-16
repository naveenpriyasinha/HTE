package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAO;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAOImpl;
import com.tcs.sgv.pension.valueobject.LifeCertificateVO;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;

/**
 * Life Certificate Service Implementation
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */

public class LifeCertificateServiceImpl extends ServiceImpl implements LifeCertificateService
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
    
    /* Global Variable for Location Code */
    String gStrLocCode = null;
    
    /* Global Variable for Lacation ID */
    Long gLocID = null;
    
    public ResultObject showAuditorPage(Map inputMap)
    {
    	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    	
    	try
    	{
	    	CommonPensionService lObjCommonPensionService = new CommonPensionServiceImpl();	    	
	    	resObj = lObjCommonPensionService.getAuditorBankCodeList(inputMap);
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
    /** Generates Life certificate...
     *  
     * @param Map:inputMap
     * @return ResultObject
     */
	public ResultObject generateLifeCertificate(Map inputMap)
	{
    	ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");        
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrPKList = null;
		
		ArrayList<LifeCertificateVO> lObjLifeCertificateVOList = new ArrayList<LifeCertificateVO>();

        try
        {
            String lStrBankCode=StringUtility.getParameter("bankCode",request).trim();       
            String lStrBranchCode=StringUtility.getParameter("branchCode",request).trim();
            
            BigDecimal lBDHeadCode= (new BigDecimal(StringUtility.getParameter("headCode",request).trim()));
            setSessionInfo(inputMap);
    		    		
    		CommonPensionService lObjCommonPensionService = new CommonPensionServiceImpl();
    		
    		lStrPKList = lObjCommonPensionService.getMyCases(inputMap);
    		
    		MstPensionerDtlsDAO lObjMstPensionerDtlsDAO = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class,servLoc.getSessionFactory());
    			
    		lObjLifeCertificateVOList = lObjMstPensionerDtlsDAO.getLifeCertificateVOList(lStrBankCode,lStrBranchCode,lStrPKList,lBDHeadCode,gStrLocCode);
                		    		
    		inputMap.put("PensionerVOList", lObjLifeCertificateVOList);
            resObj.setResultValue(inputMap);
			resObj.setViewName("viewPensionerList");
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
	
	/** Generates Life certificate...
     *  
     * @param Map:inputMap
     * @return ResultObject
     */
	public ResultObject printCertificate(Map inputMap)
	{
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");        
        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        List lArrAuditorAddress = new ArrayList<String>();
        String lStrLocName = null;
        String lStrLocAddr1 = null;
        String lStrLocAddr2 = null;
        String lStrCity = null;
        String lStrDistrict = null;
        String lStrState = null;
        StringBuffer sbLine = new StringBuffer(83);
        StringBuffer sbLineDisplay = new StringBuffer(83);
        StringBuffer sOutput = new StringBuffer();  
        StringBuffer sOutputDisplay = new StringBuffer();  
		ArrayList<LifeCertificateVO> lObjLifeCertificateVOList = new ArrayList<LifeCertificateVO>();
		MstPensionerDtlsDAO lObjMstPensionerDtlsDAO = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class,servLoc.getSessionFactory());		
		try
        {   
        	setSessionInfo(inputMap);
             String lLstPPONo[] = StringUtility.getParameterValues("ppoNo", request);       
             String lLstName[] = StringUtility.getParameterValues("name", request);
             String lLstAcountNo[] = StringUtility.getParameterValues("acountNo", request);
             String lLstPanNo[] = StringUtility.getParameterValues("panNo", request);
             String lLstBankName[] = StringUtility.getParameterValues("bankName", request);
             String lLstBranchName[] = StringUtility.getParameterValues("branchName", request);
             String flag = StringUtility.getParameter("flag", request);
        	for(int i=0;i<lLstPPONo.length;i++)
        	{
        		LifeCertificateVO lObjLifeCertificateVO = new LifeCertificateVO();
        		lObjLifeCertificateVO.setPpoNo(lLstPPONo[i]);        		
        		lObjLifeCertificateVO.setAcountNo(lLstAcountNo[i]);
        		lObjLifeCertificateVO.setName(lLstName[i]);
        		lObjLifeCertificateVO.setPanNo(lLstPanNo[i]);
        		lObjLifeCertificateVO.setBankName(lLstBankName[i]);
        		lObjLifeCertificateVO.setBranchName(lLstBranchName[i]);
        		lObjLifeCertificateVOList.add(lObjLifeCertificateVO);
        	}        	
            inputMap.put("PensionerVOList", lObjLifeCertificateVOList);
            
            //To Print the Life Certificate in DOT MATRIX.....start
            List<String> aryReturnObj = new ArrayList<String>(lObjLifeCertificateVOList.size());
            List<String> aryReturnDisplayObj = new ArrayList<String>(lObjLifeCertificateVOList.size());
            for(int i=0;i<lObjLifeCertificateVOList.size();i++)
            {
            	LifeCertificateVO lObjLifeCertificateVO = new LifeCertificateVO();
            	sbLine = new StringBuffer(83);
            	sOutput = new StringBuffer();      
            	sbLineDisplay = new StringBuffer(83);
            	sOutputDisplay = new StringBuffer(83);  
            	lObjLifeCertificateVO = lObjLifeCertificateVOList.get(i);
            	//To print in DOT MATRIX Printer...
            	for(int j=0;j<9;j++)
            	{
            		sbLine.append("\n");
            	}            	
            	sbLine.append(String.format("%60s", lObjLifeCertificateVO.getName())+" ");
            	sbLine.append("\n");
            	sbLine.append("\n");
            	sbLine.append(String.format("%60s", lObjLifeCertificateVO.getPpoNo())+" ");
            	for(int j=0;j<4;j++)
            	{
            		sbLine.append("\n");
            	}
            	sbLine.append(String.format("%60s", lObjLifeCertificateVO.getBranchName())+" ");
            	sbLine.append("\n");
            	sbLine.append("\n");
            	sbLine.append(String.format("%60s", lObjLifeCertificateVO.getAcountNo())+" ");
            	sbLine.append("\n");
            	sbLine.append("\n");
            	sbLine.append(String.format("%60s", lObjLifeCertificateVO.getPanNo())+" ");            	
            	for(int k=0;k<72;k++)
            	{
            		sbLine.append("\n");
            	}
            	sOutput.append(sbLine.toString());
            	aryReturnObj.add(sOutput.toString());
            	//To display the Pensioner List .....
            	sbLineDisplay.append("\n");
            	sbLineDisplay.append("\n");
            	sbLineDisplay.append("   "+"Name        :    "+lObjLifeCertificateVO.getName());
            	sbLineDisplay.append("\n");
            	sbLineDisplay.append("   "+"PPO No      :    "+lObjLifeCertificateVO.getPpoNo());
            	sbLineDisplay.append("\n");
            	sbLineDisplay.append("   "+"Branch Name :    "+lObjLifeCertificateVO.getBranchName());
            	sbLineDisplay.append("\n");
            	sbLineDisplay.append("   "+"Account No  :    "+lObjLifeCertificateVO.getAcountNo());
            	sbLineDisplay.append("\n");
            	sbLineDisplay.append("   "+"PAN No      :    "+lObjLifeCertificateVO.getPanNo());
            	sbLineDisplay.append("\n");
            	sOutputDisplay.append(sbLineDisplay.toString());
            	aryReturnDisplayObj.add(sOutputDisplay.toString());
            } 
            inputMap.put("PensionerDtlsArray", aryReturnObj);
            inputMap.put("PensionerArrayDisplay", aryReturnDisplayObj);
            //To Print the Life Certificate in DOT MATRIX.....end            
            lArrAuditorAddress = (ArrayList)lObjMstPensionerDtlsDAO.getAuditorAddress(gStrLocCode,gLngLangId);
            if(lArrAuditorAddress.size() > 0)
            {
            	if(lArrAuditorAddress.get(0) != null)
                {
                	lStrLocName = (String) lArrAuditorAddress.get(0);
                }
                if(lArrAuditorAddress.get(1) != null)
                {
                	lStrLocAddr1 = (String) lArrAuditorAddress.get(1);
                }
                if(lArrAuditorAddress.get(2) != null)
                {
                	lStrLocAddr2 = (String) lArrAuditorAddress.get(2);
                }
                if(lArrAuditorAddress.get(3) != null)
                {
                	lStrCity = (String) lArrAuditorAddress.get(3);
                }
                if(lArrAuditorAddress.get(4) != null)
                {
                	lStrDistrict = (String) lArrAuditorAddress.get(4);
                }
                if(lArrAuditorAddress.get(5) != null)
                {
                	lStrState = (String) lArrAuditorAddress.get(5);
                }
            }            
            inputMap.put("AuditorLocName", lStrLocName);
            inputMap.put("AuditorLocAddress1", lStrLocAddr1);
            inputMap.put("AuditorLocAddress2", lStrLocAddr2);
            inputMap.put("AuditorCity", lStrCity);
            inputMap.put("AuditorDistrict", lStrDistrict);
            inputMap.put("AuditorState", lStrState);
            if("VIEW".equals(flag))
            {
            	resObj.setResultValue(inputMap);
            	resObj.setViewName("LifeCertificate");
            }
            if("PRINT".equals(flag))
            {
            	StringBuilder lStrPrint = new StringBuilder();
            	
            	lStrPrint.append(" <XMLDOC> ");            	
	            	for(int u=0;u<aryReturnObj.size();u++)
	            	{
	            		lStrPrint.append(" 		<MESSAGECODE> ");
	            		lStrPrint.append(aryReturnObj.get(u));
	            		lStrPrint.append(" 		</MESSAGECODE> ");    
	            	}            	        	
            	lStrPrint.append(" </XMLDOC> ");
            	String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrPrint.toString()).toString();
				inputMap.put("ajaxKey", lStrResult);
				resObj.setResultValue(inputMap);	
				resObj.setViewName("ajaxData");
            }	
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
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
        gStrLocCode = SessionHelper.getLocationCode(inputMap);
        gLocID = SessionHelper.getLocationId(inputMap);
    }
}
