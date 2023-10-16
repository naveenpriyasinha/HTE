package com.tcs.sgv.common.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.BudgetHdDtlsDAO;
import com.tcs.sgv.common.dao.BudgetHdDtlsDAOImpl;
import com.tcs.sgv.common.dao.DDOInfoDAO;
import com.tcs.sgv.common.dao.DDOInfoDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;

/**
 * 
 * @author 602399
 * 
 */

public class BudgetHdDtlsServiceImpl extends ServiceImpl implements
        BudgetHdDtlsService
{
    /* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	/* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for LangId */
    Long gLngLangId = null;

    /* Global Variable for DB Id */
    Long gLngDBId = null;

    
    public ResultObject validateBudgetHeads(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
            ServiceLocator serv = (ServiceLocator) inputMap
                    .get("serviceLocator");

            Map lMapResultMap = (Map) inputMap.get("ResultMap");

            BudgetHdDtlsDAO lBudHdDtls = (BudgetHdDtlsDAO) new BudgetHdDtlsDAOImpl((Long) lMapResultMap.get("finYrId"),serv.getSessionFactory());

            boolean lBoolIsValid = lBudHdDtls.isValidBudgetHeads(
                        lMapResultMap.get("DemandCode").toString(), 
                        lMapResultMap.get("MajorHead").toString(),
                        lMapResultMap.get("SubMajorHead").toString(), 
                        lMapResultMap.get("MinorHead").toString(),
                        lMapResultMap.get("SubHead").toString(), 
                        lMapResultMap.get("DetailHead").toString(),
                        (Long) lMapResultMap.get("LangId"),"");

            inputMap.put("isValidHead", lBoolIsValid);

            objRes.setResultValue(inputMap);
            objRes.setViewName("budDetails");
        }
        catch (Exception e)
        {
            logger.error("Error in validateBudgetHeads and Error is : " + e, e);
        }

        return objRes;
    }


    public boolean validateBudgetHeads(ServiceLocator serv, Long lLngFinYrId, String lStrDemandCode,
            String lStrBudMjrHdCode, String lStrBudSubMjrHdCode,
            String lStrBudMinHdCode, String lStrBudSubHdCode,
            String lStrDtlsHd, Long lLongLangId, String lStrMjrHdType)
    {
        boolean lBoolIsValid = false;

        try
        {
            BudgetHdDtlsDAO lBudHdDtls = new BudgetHdDtlsDAOImpl(lLngFinYrId,serv.getSessionFactory());
            lBoolIsValid = lBudHdDtls.isValidBudgetHeads(lStrDemandCode,
                    lStrBudMjrHdCode, lStrBudSubMjrHdCode, lStrBudMinHdCode,
                    lStrBudSubHdCode, lStrDtlsHd, lLongLangId,lStrMjrHdType);
        }
        catch (Exception e)
        {
            logger.error("Error in validateBudgetHeads and Error is : " + e, e);
        }

        return lBoolIsValid;
    }
    
    public ResultObject getGrantHeadsForDDO(Map inputMap)
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        try
        {
        	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            
            setSessionInfo(inputMap);
            
            String lStrPLNPL = request.getParameter("cmbBudType");
            
			lStrPLNPL = lStrPLNPL.trim();            
            
            BudgetHdDtlsDAO lDDOHDDtls = new BudgetHdDtlsDAOImpl(serv.getSessionFactory());
            
            BillCommonDAOImpl lObjBillDAO = new BillCommonDAOImpl(serv.getSessionFactory());
            SgvcFinYearMst lFinYrVO = lObjBillDAO.getFinYrInfo(Calendar.getInstance().getTime(), gLngLangId);                
			Long lLngFinYrId = lFinYrVO.getFinYearId();
			String lStrGrantBudType = "";
			
			if(lStrPLNPL.equals("State_Nonplan") || lStrPLNPL.equals("Other_Agencies") || lStrPLNPL.equals("PlanBud_80"))
			{
                lStrGrantBudType = "NP";
			}
			else if(lStrPLNPL.equals("State_Plan") || lStrPLNPL.equals("PlanBud_15") || lStrPLNPL.equals("StatePlan_5"))
			{
                lStrGrantBudType = "PL";
			}
			else if(lStrPLNPL.equals("Central_Plan") || lStrPLNPL.equals("Fully_Centrally") || lStrPLNPL.equals("Partly_Centrally"))
			{
                lStrGrantBudType = "CSS";
			}
            
            DDOInfoDAO lDDOInfo = new DDOInfoDAOImpl(serv.getSessionFactory());
            String lStrDdoCode = "";
            List lListDDO = null;
            if(request.getParameter("DDOCode")!=null)
            	lStrDdoCode = request.getParameter("DDOCode");            	            
            else
            	lListDDO = lDDOInfo.getDDOInfoByPost(gLngPostId, gLngLangId, gLngDBId);
            List lListDDOHd = null;

            if (lListDDO != null && lListDDO.size() > 0 && lStrDdoCode.equals(""))
            {
                OrgDdoMst lObjVO = (OrgDdoMst) lListDDO.get(0);
    			
                lListDDOHd = lDDOHDDtls.getGrantHeadsForDDO(lObjVO.getDdoCode(), lStrGrantBudType.trim(), "ACTIVE", lLngFinYrId, gLngLangId);
            }
            else
            {
            	logger.info("Value of DDO Code : " +lStrDdoCode);            	
            	lListDDOHd = lDDOHDDtls.getGrantHeadsForDDO(lStrDdoCode, lStrGrantBudType.trim(), "ACTIVE", lLngFinYrId, gLngLangId);
            }
            inputMap.put("DDOHeadDtls", lListDDOHd);
            objRes.setResultValue(inputMap);  
            if(request.getParameter("billFlag")!=null)
            {
            	String lStrBillFlag = request.getParameter("billFlag");
            	if(lStrBillFlag.equals("1"))
            		objRes.setViewName("DdoHeadPopup");
            }
            else
            	objRes.setViewName("ddoHeadDtlsPopup");
        }
        catch (Exception e)
        {
            logger.error("Error in getGrantHeadsForDDO and Error is : " + e, e);
        }

        return objRes;
    }
    
    
    public ResultObject getDDOHeadBySchemeNo(Map inputMap)
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        try
        {
        	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            
            setSessionInfo(inputMap);
            
            String lStrSchemeNo = request.getParameter("schemeNo").trim();
            logger.info("Value of Scheme No : " +lStrSchemeNo);
            
            BillCommonDAOImpl lObjBillDAO = new BillCommonDAOImpl(serv.getSessionFactory());
            SgvcFinYearMst lFinYrVO = lObjBillDAO.getFinYrInfo(Calendar.getInstance().getTime(), gLngLangId);
            Long lLngFinYrId = lFinYrVO.getFinYearId();
			
            BudgetHdDtlsDAO lBudHdDAO = new BudgetHdDtlsDAOImpl(serv.getSessionFactory());
            List lLstGrantHD = lBudHdDAO.getHeadsForDDOBySchemeNo(lStrSchemeNo, "Active", lLngFinYrId, gLngLangId);
            
            StringBuilder lStrDDOHD = new StringBuilder();
            
            if(lLstGrantHD != null && lLstGrantHD.size() > 0)
            {
            	Object[] lObj = (Object[]) lLstGrantHD.get(0);
            	
	            lStrDDOHD.append(" <DDOGrantHead> ");
	            lStrDDOHD.append(" 		<DemandCode> ");
	            lStrDDOHD.append(String.valueOf(lObj[0]));
	            lStrDDOHD.append(" 		</DemandCode> ");
	            lStrDDOHD.append(" 		<MajorHead> ");
	            lStrDDOHD.append(String.valueOf(lObj[1]));
	            lStrDDOHD.append(" 		</MajorHead> ");
	            lStrDDOHD.append(" 		<SubMajorHead > ");
	            lStrDDOHD.append(String.valueOf(lObj[2]));
	            lStrDDOHD.append(" 		</SubMajorHead> ");
	            lStrDDOHD.append(" 		<MinorHead> ");
	            lStrDDOHD.append(String.valueOf(lObj[3]));
	            lStrDDOHD.append(" 		</MinorHead> ");
	            lStrDDOHD.append(" 		<SubHead> ");
	            lStrDDOHD.append(String.valueOf(lObj[4]));
	            lStrDDOHD.append(" 		</SubHead> ");
				lStrDDOHD.append(" </DDOGrantHead> ");
            }
            else
            {
            	lStrDDOHD.append(" <DDOGrantHead> ");
	            lStrDDOHD.append(" 		<DemandCode> ");
	            lStrDDOHD.append("");
	            lStrDDOHD.append(" 		</DemandCode> ");
	            lStrDDOHD.append(" 		<MajorHead> ");
	            lStrDDOHD.append("");
	            lStrDDOHD.append(" 		</MajorHead> ");
	            lStrDDOHD.append(" 		<SubMajorHead > ");
	            lStrDDOHD.append("");
	            lStrDDOHD.append(" 		</SubMajorHead> ");
	            lStrDDOHD.append(" 		<MinorHead> ");
	            lStrDDOHD.append("");
	            lStrDDOHD.append(" 		</MinorHead> ");
	            lStrDDOHD.append(" 		<SubHead> ");
	            lStrDDOHD.append("");
	            lStrDDOHD.append(" 		</SubHead> ");
				lStrDDOHD.append(" </DDOGrantHead> ");
            }
            
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrDDOHD.toString()).toString();
			logger.info("Value of lStrResult in getDDOHeadBySchemeNo : " +lStrResult);
			inputMap.put("ajaxKey", lStrResult);
			
			objRes.setViewName("ajaxData");
            objRes.setResultValue(inputMap);  
            
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOHeadBySchemeNo and Error is : " + e, e);
        }

        return objRes;
    }
    
    
    public ResultObject getSchemeNoByDDOGrantHead(Map inputMap)
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        try
        {
        	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            
            setSessionInfo(inputMap);
            
            String lStrDemandCode = request.getParameter("demandCode").trim();
            String lStrMajorHead = request.getParameter("majorHead").trim();
            String lStrSubMajorHead = request.getParameter("subMajorHead").trim();
            String lStrMinorHead = request.getParameter("minorHead").trim();
            String lStrSubHead = request.getParameter("subHead").trim();
            
            BillCommonDAOImpl lObjBillDAO = new BillCommonDAOImpl(serv.getSessionFactory());
            SgvcFinYearMst lFinYrVO = lObjBillDAO.getFinYrInfo(Calendar.getInstance().getTime(), gLngLangId);
            Long lLngFinYrId = lFinYrVO.getFinYearId();
			
            BudgetHdDtlsDAO lBudHdDAO = new BudgetHdDtlsDAOImpl(serv.getSessionFactory());
            List lLstGrantHD = lBudHdDAO.getSchemeNoByDDOGrantHead(lStrDemandCode, lStrMajorHead, lStrSubMajorHead, lStrMinorHead, lStrSubHead , "Active", lLngFinYrId, gLngLangId);
            
            StringBuilder lStrDDOHD = new StringBuilder();
            
            if(lLstGrantHD != null && lLstGrantHD.size() > 0)
            {
            	Object lObj = (Object) lLstGrantHD.get(0);
            	
	            lStrDDOHD.append(" <DDOGrantHead> ");
	            lStrDDOHD.append(" 		<SchemeNo> ");
	            lStrDDOHD.append(String.valueOf(lObj));
	            lStrDDOHD.append(" 		</SchemeNo> ");
	            lStrDDOHD.append(" </DDOGrantHead> ");
            }
            else
            {
            	lStrDDOHD.append(" <DDOGrantHead> ");
	            lStrDDOHD.append(" 		<SchemeNo> ");
	            lStrDDOHD.append("");
	            lStrDDOHD.append(" 		</SchemeNo> ");
	            lStrDDOHD.append(" </DDOGrantHead> ");
            }
            
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrDDOHD.toString()).toString();
			logger.info("Value of AJAX data in getSchemeNoByDDOGrantHead : " +lStrResult);
			inputMap.put("ajaxKey", lStrResult);
			
			objRes.setViewName("ajaxData");
            objRes.setResultValue(inputMap);  
            
            
        }
        catch (Exception e)
        {
            logger.error("Error in getSchemeNoByDDOGrantHead and Error is : " + e, e);
        }

        return objRes;
    }
    
    
    private void setSessionInfo(Map inputMap)
    {
    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
    	
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(request);
        gLngDBId = SessionHelper.getDbId(inputMap);
    }
}