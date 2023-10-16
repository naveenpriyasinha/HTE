package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionCardexSigndtlsDao;
import com.tcs.sgv.pension.valueobject.MstPensionCardexSigndtls;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeRate;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeSpecial;
import com.tcs.sgv.pension.valueobject.SavedCardexSignVo;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.valueobject.WfDocMvmntMstVO;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

/**
 * Common Pension Service Implementation
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */
public class CommonPensionServiceImpl extends ServiceImpl implements CommonPensionService
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
    
    /* Glonal Variable for Location Code */
	String gStrLocCode = null;

    private static ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    /**
     * Shows Auditor Page....in which he/she can select specific details &
     * generate the certificate
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject getAuditorBankCodeList(Map inputMap)
    {
        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        setSessionInfo(inputMap);
        BigDecimal lBDUserId = (new BigDecimal(gLngUserId));
        BigDecimal lBDPostId = (new BigDecimal(gLngPostId));
        try
        {
            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(servLoc.getSessionFactory());
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
            resObj.setViewName("lifecertificate");
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


    /**
     * Returns Branch Codes for one bank....(On AJAX)
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject getAuditorBranchInfo(Map inputMap)
    {
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        String lStrAuditorBankCode = null;
        setSessionInfo(inputMap);
        BigDecimal lBDUserId = (new BigDecimal(gLngUserId));
        BigDecimal lBDPostId = (new BigDecimal(gLngPostId));
        try
        {
            lStrAuditorBankCode = StringUtility.getParameter("AuditorBankCode", request);
            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            List lLstAuditorBranchCodeList = lObjCommonPensionDAO.getAuditorBranchCodeList(lBDUserId, lBDPostId,
                    lStrAuditorBankCode, gStrLocCode);
            ArrayList<ComboValuesVO> arrBranchCode = new ArrayList<ComboValuesVO>();
            ComboValuesVO vo = new ComboValuesVO();
            if (lLstAuditorBranchCodeList == null && !lLstAuditorBranchCodeList.isEmpty())
            {
                vo.setId("-1");
                vo.setDesc("--Select--");
                arrBranchCode.add(vo);
            }
            if (lLstAuditorBranchCodeList != null && lLstAuditorBranchCodeList.size() > 0)
            {
                Iterator it = lLstAuditorBranchCodeList.iterator();
                while (it.hasNext())
                {
                    vo = new ComboValuesVO();
                    vo = (ComboValuesVO) it.next();
                    arrBranchCode.add(vo);
                }
            }
            String lStrAjaxResult = new AjaxXmlBuilder().addItems(arrBranchCode, "desc", "id").toString();
            inputMap.put("ajaxKey", lStrAjaxResult);
            resObj.setResultValue(inputMap);
            resObj.setViewName("ajaxData");
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


    /**
     * Returns All Distinct Head Code descriptions ...(On AJAX)
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject getHeadCodeDesc(Map inputMap)
    {
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        String lStrHeadCode = null;
        String lStrIRFlag = null;
        
        try
        {
            lStrHeadCode = StringUtility.getParameter("HeadCode", request);
            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            // ArrayList PnsnHeadCodeDesc = (ArrayList)
            // lObjCommonPensionDAO.getAllHeadCodeDesc(lStrHeadCode,
            // SessionHelper.getLangId(inputMap));
            String strPnsnHeadCodeDesc = (String) lObjCommonPensionDAO.getAllHeadCodeDesc(lStrHeadCode, SessionHelper
                    .getLangId(inputMap));
            if (strPnsnHeadCodeDesc != null)
            {
                strPnsnHeadCodeDesc = strPnsnHeadCodeDesc.replace('&', '~');
            }
            List lLstRes = lObjCommonPensionDAO.getBillTypeFromHeadCode(new BigDecimal(lStrHeadCode));
            String lStrSpclCase = null;
            String lStrRop = null;
            String lStrBasic = null;
            String lstrRetDate = null;
            String lStrTIFlag = null;
            String lStrDPFlag = null;
            String lStrMAFlag = null;
            BigDecimal lLngBasicAmt = new BigDecimal(0);
            BigDecimal TiAmnt = new BigDecimal(0);
            BigDecimal IRAmnt= new BigDecimal(0); 
            //String lStrIRFlag = null;
            if(StringUtility.getParameter("ComnDate",request) != null && StringUtility.getParameter("ComnDate",request).length() > 0)
            {
            	lstrRetDate = StringUtility.getParameter("ComnDate",request).toString();
            }
        	 if(StringUtility.getParameter("isRop",request) != null && StringUtility.getParameter("isRop",request).length() > 0)
             {
             	if(! request.getParameter("isRop").equals("-1"))
             	{
             		lStrRop = StringUtility.getParameter("isRop",request).toString();
             	}
             }
             if(StringUtility.getParameter("SpecialCase",request) != null && StringUtility.getParameter("SpecialCase",request).length() > 0)
             {
             	lStrSpclCase = StringUtility.getParameter("SpecialCase",request).toString();
             }
                 
             if(StringUtility.getParameter("BasicPension",request) != null && StringUtility.getParameter("BasicPension",request).length() > 0)
             {
             		 lStrBasic = StringUtility.getParameter("BasicPension",request);
             		 lLngBasicAmt = new BigDecimal(lStrBasic);
             		 
             		 if(lLngBasicAmt.doubleValue() > 0 && lLngBasicAmt.doubleValue() <= 1750)
             		 {
             			lLngBasicAmt = new BigDecimal(1750);
             		 }
             		 else if(lLngBasicAmt.doubleValue() > 1750 && lLngBasicAmt.doubleValue() <= 3000 )
             		 {
             			lLngBasicAmt = new BigDecimal(3000);
             		 }
             		 else
             		 {
             			lLngBasicAmt = new BigDecimal(999999);
             		 }
             }
            if(lstrRetDate != null)
            {
            	lstrRetDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(lstrRetDate));	
            	 lStrMAFlag = "MA";
            	 lStrIRFlag = "IR";
                 if(lStrSpclCase != null && lStrRop != null)
                 {
                 	if(lStrRop.equalsIgnoreCase("Y") && lStrSpclCase.equalsIgnoreCase("N"))
                 	{
                 		lStrTIFlag = "STI";
                 		lStrDPFlag = "DP";
                 	}
                 	if(lStrRop.equalsIgnoreCase("Y") && lStrSpclCase.equalsIgnoreCase("Y"))
                 	{
                 		lStrTIFlag = "RTI";
                 	}
                 	if(lStrRop.equalsIgnoreCase("N")&& lStrBasic!= null)
                	{
                		lStrTIFlag = "TI";
                	}
                 }
            }
            StringBuilder lStrData = new StringBuilder();
            lStrData.append("<XMLDOC>");
            lStrData.append("<HEADDESC>");
            lStrData.append(strPnsnHeadCodeDesc);
            lStrData.append("</HEADDESC>");
            lStrData.append("<DCRG>");
            if (lLstRes.contains("DCRG"))
            {
                lStrData.append("Y");
            }
            else
            {
                lStrData.append("N");
            }
            lStrData.append("</DCRG>");
            lStrData.append("<Pension>");
            if (lLstRes.contains("PENSION"))
            {
                lStrData.append("Y");
            }
            else
            {
                lStrData.append("N");
            }
            lStrData.append("</Pension>");
            lStrData.append("<CVP>");
            if (lLstRes.contains("CVP"))
            {
                lStrData.append("Y");
            }
            else
            {
                lStrData.append("N");
            }
            lStrData.append("</CVP>");
           // List lLstResValue = lObjCommonPensionDAO.getTiAndMdeAllowFromByHeadCode(lStrHeadCode);
            BigDecimal TiRate = new BigDecimal(0);
            if(Integer.parseInt(lStrHeadCode) == 16 || Integer.parseInt(lStrHeadCode) == 17 || Integer.parseInt(lStrHeadCode) == 19 || Integer.parseInt(lStrHeadCode) == 18)
            {
            	lStrBasic = StringUtility.getParameter("BasicPension",request);
                RltPensionHeadcodeSpecial lObjTIVo = null;
                lObjTIVo = lObjCommonPensionDAO.getTiRateForSpecialHeadCode(lStrHeadCode,lstrRetDate,lStrBasic) ;
                if(lObjTIVo != null)
                {
                	if(lObjTIVo.getNewAmount() != null )
                	{
                		TiAmnt = lObjTIVo.getNewAmount();
                	}
                }
                lStrData.append("<TIRate>");
        		lStrData.append(0);
        		lStrData.append("</TIRate>");
        		lStrData.append("<TIAmt>");
        		lStrData.append(TiAmnt);
        		lStrData.append("</TIAmt>");
            }
            else
            {
                if(lStrTIFlag != null)
                {
                	
                	RltPensionHeadcodeRate lObjTIVo = null;
                	if(lStrTIFlag.equals("TI"))
                	{
                		lObjTIVo = lObjCommonPensionDAO.getTiAndMdeAllowFromByHeadCode(lStrHeadCode,lstrRetDate,lStrTIFlag,lLngBasicAmt.toString()) ;
                	}
                	else
                	{
                		lObjTIVo = lObjCommonPensionDAO.getTiAndMdeAllowFromByHeadCode(lStrHeadCode,lstrRetDate,lStrTIFlag,null) ;
                	}
                	
                	if(lObjTIVo != null)
                	{
                		if(lObjTIVo.getRate() != null)
                		{
                			TiRate = lObjTIVo.getRate();
                		}
                		if(lObjTIVo.getMinAmount() != null )
                		{
                			TiAmnt = lObjTIVo.getMinAmount();
                		}
                		
                	}
                }
                lStrData.append("<TIRate>");
        		lStrData.append(TiRate);
        		lStrData.append("</TIRate>");
        		lStrData.append("<TIAmt>");
        		lStrData.append(TiAmnt);
        		lStrData.append("</TIAmt>");
        		BigDecimal MAAmnt = new BigDecimal(0);
                if(lStrMAFlag != null)
                {
                	RltPensionHeadcodeRate lObjMAVo = lObjCommonPensionDAO.getTiAndMdeAllowFromByHeadCode(lStrHeadCode,lstrRetDate,lStrMAFlag,null);
                	if(lObjMAVo != null)
                	{
                		 if(lObjMAVo.getMinAmount() != null)
                		{
                			MAAmnt = lObjMAVo.getMinAmount();
                		}
                	}
                }
                lStrData.append("<MAAmt>");
        		lStrData.append(MAAmnt);
        		lStrData.append("</MAAmt>");
        		BigDecimal DpRate = new BigDecimal(0);
                if(lStrDPFlag != null)
                {
                	RltPensionHeadcodeRate lObjDPVo = lObjCommonPensionDAO.getTiAndMdeAllowFromByHeadCode(lStrHeadCode,lstrRetDate,lStrDPFlag,null) ;
                	
                	if(lObjDPVo != null)
                	{
                		if(lObjDPVo.getRate() != null)
                		{
                			DpRate = lObjDPVo.getRate();
                		}
                	}
                }
                lStrData.append("<DPRate>");
        		lStrData.append(DpRate);
        		lStrData.append("</DPRate>");
        		BigDecimal IRRate = new BigDecimal(0);
        		if(lStrIRFlag != null)
        		{
        			RltPensionHeadcodeRate lObjMAVo = lObjCommonPensionDAO.getTiAndMdeAllowFromByHeadCode(lStrHeadCode,lstrRetDate,lStrIRFlag,null);
                	if(lObjMAVo != null)
                	{
                		 if(lObjMAVo.getMinAmount() != null)
                		{
                			 IRAmnt = lObjMAVo.getMinAmount();
                		}
                		if(lObjMAVo.getRate() != null)
                		{
                			IRRate = lObjMAVo.getRate();
                		}
                	}
        		}
        		
        		lStrData.append("<IRRate>");
        		lStrData.append(IRRate);
        		lStrData.append("</IRRate>");
        		lStrData.append("<IRAmt>");
        		lStrData.append(IRAmnt);
        		lStrData.append("</IRAmt>");
            }

            lStrData.append("</XMLDOC>");

            String lStrAjaxResult = new AjaxXmlBuilder().addItem("ajax_key", lStrData.toString()).toString();
           
            //System.out.println("-------------------------");
            //System.out.println(lStrAjaxResult.toString());
            //System.out.println("-------------------------");
            inputMap.put("ajaxKey", lStrAjaxResult);
            resObj.setResultValue(inputMap);
            resObj.setViewName("ajaxData");
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


    public ResultObject getDistricts(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        try
        {
            setSessionInfo(inputMap);
            ArrayList arrylst = new ArrayList();
            String strStateId = (String) StringUtility.getParameter("state", request);
            BigDecimal stateId = (BigDecimal) inputMap.get("state");
            String strAjaxResult = null;
            CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());

            if (strStateId != null && strStateId.length() > 0)
            {
                arrylst = (ArrayList) commonPensionDAO.getDistrictsOfState(new BigDecimal(strStateId), gLngLangId);
                strAjaxResult = new AjaxXmlBuilder().addItems(arrylst, "desc", "id").toString();
                inputMap.put("ajaxKey", strAjaxResult);
                resObj.setViewName("ajaxData");
            }
            else if (stateId != null)
            {
                arrylst = (ArrayList) commonPensionDAO.getDistrictsOfState(stateId, gLngLangId);
                inputMap.put("listDistricts", arrylst);
            }
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


    public ResultObject getBranch(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        try
        {
            setSessionInfo(inputMap);
            CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            ArrayList arrylstBranch = new ArrayList();
            String bankCodeFrmRequest = StringUtility.getParameter("bank", request);
            String bankCodeFrmMap = (String) inputMap.get("bank");

            if (bankCodeFrmRequest != null && bankCodeFrmRequest.length() > 0)
            {
                arrylstBranch = (ArrayList) commonPensionDAO.getBranchsOfBank(bankCodeFrmRequest, gLngLangId,SessionHelper.getLocationCode(inputMap));
                String strAjaxResult = new AjaxXmlBuilder().addItems(arrylstBranch, "desc", "id").toString();
                inputMap.put("ajaxKey", strAjaxResult);
                resObj.setViewName("ajaxData");
            }
            else if (bankCodeFrmMap != null && bankCodeFrmMap.length() > 0)
            {
                arrylstBranch = (ArrayList) commonPensionDAO.getBranchsOfBank(bankCodeFrmMap, gLngLangId,SessionHelper.getLocationCode(inputMap));
                inputMap.put("listBranch", arrylstBranch);
            }
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


    public ResultObject getBranchAdd(Map inputMap)
    {        
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        try
        {
            CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            List arrylstBranch = new ArrayList();
            StringBuilder lStrRes = new StringBuilder();
            String bankCodeFrmRequest = StringUtility.getParameter("txtbranchCode", request);
            if (bankCodeFrmRequest.trim().length() > 0)
            {
                arrylstBranch = commonPensionDAO.getBranchByBranchId(bankCodeFrmRequest, SessionHelper
                        .getLangId(inputMap),SessionHelper.getLocationCode(inputMap));
            }
            lStrRes.append("<XMLDOC>");
            if (arrylstBranch != null && arrylstBranch.size() > 0)
            {
                if (arrylstBranch.get(0) != null)
                {
                    lStrRes.append("<BANKCODE>" + arrylstBranch.get(0).toString());
                    lStrRes.append("</BANKCODE>");
                }
                if (arrylstBranch.get(1) != null)
                {
                    lStrRes.append("<BRANCHCODE>" + arrylstBranch.get(1).toString());
                    lStrRes.append("</BRANCHCODE>");
                }
                if (arrylstBranch.get(2) != null)
                {
                    lStrRes.append("<BRANCHNAME>" + arrylstBranch.get(2).toString());
                    lStrRes.append("</BRANCHNAME>");
                }
            }
            lStrRes.append("</XMLDOC>");
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrRes.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
            resObj.setResultValue(inputMap);
            resObj.setViewName("ajaxData");
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


    public ResultObject getSubTreasury(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try
        {
            setSessionInfo(inputMap);
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            String strlocID = StringUtility.getParameter("treasury", request);
            Long lLngLocCode = CommonPensionDAO.getLocCodeFromRltPPOTrsryMapping(Long.parseLong(strlocID));
            if(lLngLocCode != null && lLngLocCode > 0)
            {
            	strlocID = lLngLocCode.toString();
            }
            ArrayList ArrLstSubTreausry = (ArrayList) CommonPensionDAO
                    .getSubTreasurysOfTreasury(strlocID, gLngLangId);
            String strAjaxResult = new AjaxXmlBuilder().addItems(ArrLstSubTreausry, "desc", "id").toString();
            inputMap.put("ajaxKey", strAjaxResult);
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


    public ResultObject getHOD(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        try
        {
            setSessionInfo(inputMap);
            CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            String strDepCodeFrmRequest = StringUtility.getParameter("department", request);
            BigDecimal deptCodeFrmMap = (BigDecimal) inputMap.get("department");
            ArrayList arrHod = null;
            String strAjaxResult = null;
            if (strDepCodeFrmRequest != null && strDepCodeFrmRequest.length() > 0)
            {
                BigDecimal lBdDeptCode = new BigDecimal(strDepCodeFrmRequest);
                arrHod = (ArrayList) commonPensionDAO.getHodFromDepartmet(lBdDeptCode, gLngLangId);
                strAjaxResult = new AjaxXmlBuilder().addItems(arrHod, "desc", "id").toString();
                inputMap.put("ajaxKey", strAjaxResult);
                resObj.setViewName("ajaxData");
            }
            else if (deptCodeFrmMap != null)
            {
                arrHod = (ArrayList) commonPensionDAO.getHodFromDepartmet(deptCodeFrmMap, gLngLangId);
                inputMap.put("listHod", arrHod);
            }
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


    public String getMyCases(Map inputMap) throws Exception
    {
        setSessionInfo(inputMap);
        WorkFlowVO workFlowVO = new WorkFlowVO();
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();
        String gStrLocId = SessionHelper.getLocationCode(inputMap);
        Long gLngDBId = SessionHelper.getDbId(inputMap);
        List list = new ArrayList();
        List resultLst = null;
        List caseList = new ArrayList();
        String lStrFinalString  = new String();
        try
        {
            workFlowVO.setAppMap(inputMap);
            workFlowVO.setCrtEmpId(SessionHelper.getUserId(inputMap).toString());
            workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
            workFlowVO.setCrtUsr(SessionHelper.getUserId(inputMap).toString());
            workFlowVO.setConnection(conn);
            //workFlowVO.setActId(DBConstants.WF_ActionId_Create);
            workFlowVO.setDocId(Long.parseLong(bundleConst.getString("PENSION.CASEDOCUMENTID")));
            workFlowVO.setLocID(gStrLocId);
            workFlowVO.setDbId(gLngDBId);
            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
            // adding the posts
            list.add(gLngPostId.toString());
            resultLst = orgUtil.getDocList(list);
            if (resultLst != null && resultLst.size() > 0)
            {
                for (int lCount = 0; lCount < resultLst.size(); lCount++)
                {
                    WfDocMvmntMstVO lObjDocMvmntMst = (WfDocMvmntMstVO) resultLst.get(lCount);
                    if (lObjDocMvmntMst.getDocId() == Long.parseLong(bundleConst.getString("PENSION.CASEDOCUMENTID")))
                    {
                    	   caseList.add(lObjDocMvmntMst.getJobRefId());
                    }
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
    			lStrnew = lStrnew+caseList.get(k)+",";
        	}
            if(lSb != null && lStrnew!= null)
    		{
    			lSb.append("("+lStrnew+")");
    		}
            lStrFinalString = lSb.toString().replace(",)", ")");
            
        }
        catch (Exception e)
        {
            gLogger.info("Error is : " + e, e);
            throw (e);
        }
        return lStrFinalString;
    }


    private void setSessionInfo(Map inputMap)
    {
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
        gStrLocCode = SessionHelper.getLocationCode(inputMap);
    }


    public ResultObject getSancAuthPrefix(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        try
        {
            setSessionInfo(inputMap);
            CommonPensionDAO commonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
            String lStrSancAuthCode = StringUtility.getParameter("sancAuthCmb", request);
            String lStrPrifix = "";
            StringBuilder lStrRes = new StringBuilder();
            lStrRes.append("<XMLDOC>");
            lStrRes.append("<PreFix>");
            if (lStrSancAuthCode != null && lStrSancAuthCode.length() > 0)
            {
                lStrPrifix = commonPensionDAO.getPrifixSancAuth(Long.valueOf(lStrSancAuthCode), SessionHelper.getLangId(inputMap));
                lStrRes.append(lStrPrifix);
            }
            lStrRes.append("</PreFix>");
            lStrRes.append("</XMLDOC>");
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrRes.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
            resObj.setViewName("ajaxData");
            resObj.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.info("Error is : " + e, e);
        }
        return resObj;
    }
    public String getMyMRCases(Map inputMap) throws Exception
    {
        setSessionInfo(inputMap);
        WorkFlowVO workFlowVO = new WorkFlowVO();
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();
        StringBuffer lStrMyCases = new StringBuffer();
        String gStrLocId = SessionHelper.getLocationCode(inputMap);
        Long gLngDBId = SessionHelper.getDbId(inputMap);
        List list = new ArrayList();
        List resultLst = null;
        List caseList = new ArrayList();
        try
        {
            workFlowVO.setAppMap(inputMap);
            workFlowVO.setCrtEmpId(SessionHelper.getUserId(inputMap).toString());
            workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
            workFlowVO.setCrtUsr(SessionHelper.getUserId(inputMap).toString());
            workFlowVO.setConnection(conn);
            //workFlowVO.setActId(DBConstants.WF_ActionId_Create);
            workFlowVO.setDocId(Long.parseLong(bundleConst.getString("WF.DocId.MRCase")));
            workFlowVO.setLocID(gStrLocId);
            workFlowVO.setDbId(gLngDBId);
            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
            // adding the posts
            list.add(gLngPostId.toString());
            resultLst = orgUtil.getDocList(list);
            if (resultLst != null && resultLst.size() > 0)
            {
                for (int lCount = 0; lCount < resultLst.size(); lCount++)
                {
                    WfDocMvmntMstVO lObjDocMvmntMst = (WfDocMvmntMstVO) resultLst.get(lCount);
                    if (lObjDocMvmntMst.getDocId() == Long.parseLong(bundleConst.getString("WF.DocId.MRCase")))
                    {
                        caseList.add(lObjDocMvmntMst.getJobRefId());
                    }
                }
            }
            Iterator lItr = caseList.iterator();
            lStrMyCases.append("(");
            String lStrMyCases1;
            while (lItr.hasNext())
            {
                lStrMyCases1 = (String) lItr.next();

                lStrMyCases.append("'" + lStrMyCases1 + "'");

                if (lItr.hasNext())
                {
                    lStrMyCases.append(",");
                }
            }
            lStrMyCases.append(")");
        }
        catch (Exception e)
        {
            gLogger.info("Error is : " + e, e);
            throw (e);
        }
        return lStrMyCases.toString();
    }
    
    public ResultObject loadCrdxMasterScreen(Map argsMap)
    {
    	  ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
    	  List listDesignation = null;
    	  List lLstCmbSanAuth = null;
          try
          {
        	  setSessionInfo(argsMap);
              ServiceLocator serv = (ServiceLocator) argsMap.get("serviceLocator");
              HttpServletRequest request = (HttpServletRequest) argsMap.get("requestObj");
              CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
              if(request.getParameter("srNo") != null && request.getParameter("srNo") .length() > 0)
              {
            	  argsMap.put("srNo",request.getParameter("srNo"));
              }
              if(StringUtility.getParameter("stauts", request) != null && "save".equalsIgnoreCase(StringUtility.getParameter("stauts", request) ))
              {
            	  savePensionCardexSign(argsMap);
            	  resObj.setResultValue(argsMap);
              }
              else if(StringUtility.getParameter("stauts", request) != null && "View".equalsIgnoreCase(StringUtility.getParameter("stauts", request) ))
              {
            	  MstPensionCardexSigndtlsDao lObjDao = new  MstPensionCardexSigndtlsDao(MstPensionCardexSigndtls.class,serv.getSessionFactory());
            	  String lStrIsDeactivate = StringUtility.getParameter("hidisActive", request);
            	  if(lStrIsDeactivate != null && lStrIsDeactivate.length() > 0)
            	  {
            		  savePensionCardexSign(argsMap);
            	  }
            	  List<SavedCardexSignVo> lLstSigns = lObjDao.getSavedCrdxSigns(argsMap);
            	  argsMap.put("SignLsit", lLstSigns);
            	  resObj.setViewName("SavedCadxSigns");
            	  resObj.setResultValue(argsMap);
              }
              else if(StringUtility.getParameter("stauts", request) != null && "upDateMstScreen".equalsIgnoreCase(StringUtility.getParameter("stauts", request) ))
              {
            	  listDesignation = CommonPensionDAO.getAllDesignation(gLngLangId);
                  lLstCmbSanAuth = CommonPensionDAO.getSanctionAuthPrefix(gLngLangId);
                  argsMap.put("SancAuthList", lLstCmbSanAuth);
                  argsMap.put("listDesignation", listDesignation);
                  String lStrId = StringUtility.getParameter("hidcrdxDtlsId", request);
                  if(lStrId != null && lStrId.length() > 0)
                  {
                	  MstPensionCardexSigndtlsDao lObjDao = new  MstPensionCardexSigndtlsDao(MstPensionCardexSigndtls.class,serv.getSessionFactory());
                	  MstPensionCardexSigndtls lObjCrdxNewVo = new MstPensionCardexSigndtls(); 
                	  lObjCrdxNewVo = lObjDao.read(Long.parseLong(lStrId));
                	  CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv
                              .getSessionFactory());
                      CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(lObjCrdxNewVo.getCardexSignId());
                      
                      argsMap.put("scan", cmnAttachmentMst);
                	  argsMap.put("MstPensionCardexSign", lObjCrdxNewVo);
                  }
                  resObj.setViewName("CrdxMStScreenPopup");
                  resObj.setResultValue(argsMap);
              }
              else if(StringUtility.getParameter("stauts", request) != null && "getNamesCmb".equalsIgnoreCase(StringUtility.getParameter("stauts", request) ))
              {
            	  MstPensionCardexSigndtlsDao lObjDao = new  MstPensionCardexSigndtlsDao(MstPensionCardexSigndtls.class,serv.getSessionFactory());
            	  String lStrRes = new String();
            	  ComboValuesVO lOjCmbVo = null;
            	  String lStrAuthCode = StringUtility.getParameter("authCode", request);
            	  if(lStrAuthCode != null && lStrAuthCode.length() > 0)
            	  {
            		  List<ComboValuesVO> lLstRes = lObjDao.getCrdxNames(lStrAuthCode);
                	  
                	  if(lLstRes != null && ! lLstRes.isEmpty())
                	  {
                		  Iterator itr = lLstRes.iterator();
                		  while(itr.hasNext())
                		  {
                			  lOjCmbVo = (ComboValuesVO) itr.next();
                			  lStrRes = lStrRes + "<option value = "+ lOjCmbVo.getId() +" >"+ lOjCmbVo.getDesc() +"</option>";
                		  }
                	  }
            	  }
            	  argsMap.put("ajaxKey", lStrRes);
                  resObj.setViewName("ajaxData");
                  resObj.setResultValue(argsMap);
              }
            	  
              else
              {
            	  listDesignation = CommonPensionDAO.getAllDesignation(gLngLangId);
                  lLstCmbSanAuth = CommonPensionDAO.getSanctionAuthPrefix(gLngLangId);
                  argsMap.put("SancAuthList", lLstCmbSanAuth);
                  argsMap.put("listDesignation", listDesignation);
                  resObj.setViewName("CrdxMStScreenPopup");
                  resObj.setResultValue(argsMap);
              }
             
              
          }
          catch(Exception e)
          {
        	  gLogger.info("Error is : " + e, e);
          }
    	return resObj;
    }
    private   void savePensionCardexSign(Map argsMap)
    {
    	 try
    	 {
    		 ServiceLocator serv = (ServiceLocator) argsMap.get("serviceLocator");
    		  MstPensionCardexSigndtls lObjCrdxNewVo = new MstPensionCardexSigndtls(); 
             MstPensionCardexSigndtlsDao lObjDao = new  MstPensionCardexSigndtlsDao(MstPensionCardexSigndtls.class,serv.getSessionFactory());
             lObjCrdxNewVo =  getSignDtlsVo(argsMap);
             if(lObjCrdxNewVo != null && lObjCrdxNewVo.getCardexSigndtlsId() != null )
             {
            	 lObjDao.update(lObjCrdxNewVo);
             }
             else
             {
            	 Long lLngPk = IDGenerateDelegate.getNextId("mst_pension_cardex_signdtls", argsMap);
            	 lObjCrdxNewVo.setCardexSigndtlsId(lLngPk);
                 lObjDao.create(lObjCrdxNewVo);
             }
    	 }
    	catch(Exception e)
    	{
    		 gLogger.info("Error is : " + e, e);
    	}
    }
    private MstPensionCardexSigndtls getSignDtlsVo(Map argsMap)
    {
    	 ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    	 ServiceLocator serv = (ServiceLocator) argsMap.get("serviceLocator");
    	 MstPensionCardexSigndtls lObjCrdxVo = new MstPensionCardexSigndtls();
    	try
    	{
    		 HttpServletRequest request = (HttpServletRequest) argsMap.get("requestObj");
    		 String lstrIsActiveFlag = StringUtility.getParameter("hidisActive", request);
    		 
       	 	Long lLngAuth = (StringUtility.getParameter("sancAuthCmb", request).length() > 0) ? Long.parseLong(StringUtility
                    .getParameter("sancAuthCmb", request) ): null;
       	 	String lStrDesigCode = (StringUtility.getParameter("cmbDesignation", request).length() > 0) ?StringUtility
                    .getParameter("cmbDesignation", request) : null;
           String lStrName = (StringUtility.getParameter("AuthName", request).length() > 0) ? StringUtility
                    .getParameter("AuthName", request) : null;
            String lStrHidSignId = StringUtility.getParameter("hidcardexSigndtlsId", request);
            
            if(lStrHidSignId != null && lStrHidSignId.length() > 0)
            {
            	MstPensionCardexSigndtlsDao lObjDao = new  MstPensionCardexSigndtlsDao(MstPensionCardexSigndtls.class,serv.getSessionFactory());
            	lObjCrdxVo = lObjDao.read(Long.parseLong(lStrHidSignId));
            	lObjCrdxVo.setUpdatedDate(gDtCurrDt);
            	lObjCrdxVo.setUpdatedPostId(BigDecimal.valueOf(gLngPostId));
            	lObjCrdxVo.setUpdatedUserId(BigDecimal.valueOf(gLngUserId));
            }
            else 
            {
            	lObjCrdxVo.setCreatedDate(gDtCurrDt);
            	lObjCrdxVo.setCreatedPostId(BigDecimal.valueOf(gLngPostId));
            	lObjCrdxVo.setCreatedUserId(BigDecimal.valueOf(gLngUserId));
            }
            if(lLngAuth != null)
            {
            	 lObjCrdxVo.setAuthCode(lLngAuth);
            }
            if(lStrName != null)
            {
            	 lObjCrdxVo.setAuthPersonName(lStrName);
            }
            if(lStrDesigCode != null)
            {
            	 lObjCrdxVo.setDesigCode(lStrDesigCode);
            }
            if(lstrIsActiveFlag != null && lstrIsActiveFlag.length() > 0)
            {
            	 lObjCrdxVo.setIsActive(lstrIsActiveFlag);
            }
            else 
            {
            	lObjCrdxVo.setIsActive("Y");
            }
            objRes = serv.executeService("FILE_UPLOAD_VOGEN", argsMap);
            objRes = serv.executeService("FILE_UPLOAD_SRVC", argsMap);
            Map attachMap = (Map) objRes.getResultValue();
           
            if (attachMap.get("AttachmentId_scan") != null)
            {
                Long lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_scan")));
                if(lLngAttachId != null)
                {
                	 lObjCrdxVo.setCardexSignId(lLngAttachId);	
                }
            }
                   
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return lObjCrdxVo;
    }
}