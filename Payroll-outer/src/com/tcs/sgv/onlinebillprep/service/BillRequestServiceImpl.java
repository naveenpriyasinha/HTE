/**
 * package : com.tcs.sgv.onlinebillprep.service 
 * @verion : 1.0
 * @author : Keyur Shah, Nirav Bumia 
 ** 
 */

package com.tcs.sgv.onlinebillprep.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.DDOInfoDAO;
import com.tcs.sgv.common.dao.DDOInfoDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.DDOInfoService;
import com.tcs.sgv.common.service.DDOInfoServiceImpl;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvoDeptMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.BillRequestDAO;
import com.tcs.sgv.onlinebillprep.dao.BillRequestDAOImpl;
import com.tcs.sgv.onlinebillprep.util.ConfigOnlineBillUtil;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;
import com.tcs.sgv.onlinebillprep.valueobject.ConfigOnlineBill;
import com.tcs.sgv.onlinebillprep.valueobject.TrnAprvdRqst;

/**
 * Its a service class for Searching Data required for Creation of new Bills.
 * 
 * @author Keyur Shah, Nirav Bumia
 * @version 1.0
 */
public class BillRequestServiceImpl extends ServiceImpl implements BillRequestService
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

   
    /**
     * It finds Bill Type and Location List for request searching.
     * 
     * @param objectArgs
     *            ServiceMap
     * @return objRes ResultObject
     */
    public ResultObject getBillsAndLocations(Map orgsMap)
    {
        ServiceLocator servLoc = (ServiceLocator) orgsMap.get("serviceLocator");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");

        setSessionInfo(orgsMap);

        BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class, servLoc
                .getSessionFactory());

        DDOInfoService lObjDDOInfoServicImpl = new DDOInfoServiceImpl();
        CmnLookupMstDAO lDAOCmnLkUpMst = new CmnLookupMstDAOImpl(CmnLookupMst.class, servLoc.getSessionFactory());
        
        List lLstBillType = null;

        List<CommonVO> lLstReturn = new ArrayList<CommonVO>();
        CommonVO lObjCmnVO = null;
        Object[] lObj = null;

        List lLstDDOInfoByPost = null;
        String lStrDDOCode = null;
        List lLstLocations = null;
        List lLstEmpType = null;

        try
        {
            
            lLstBillType = lObjCmnSrvcDAOImpl.getBillType(gLngLangId);
            Iterator lItr = lLstBillType.iterator();

            while (lItr.hasNext())
            {
                lObj = (Object[]) lItr.next();
                lObjCmnVO = new CommonVO();

                lObjCmnVO.setCommonId(lObj[0].toString());
                lObjCmnVO.setCommonDesc(lObj[2].toString());

                lLstReturn.add(lObjCmnVO);
            }

            lLstDDOInfoByPost = lObjDDOInfoServicImpl.getDDOInfoByPost(gLngPostId, gLngLangId, gLngDBId, servLoc);
            
            lLstEmpType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang("go_ngo", gLngLangId);

            if (lLstDDOInfoByPost != null && lLstDDOInfoByPost.size() > 0)
            {
                lStrDDOCode = ((OrgDdoMst) lLstDDOInfoByPost.get(0)).getDdoCode();

                lLstLocations = lObjDDOInfoServicImpl.getBillOfficeForDDO(servLoc, lStrDDOCode, gLngLangId, gLngDBId);

            }

            orgsMap.put("ResultList", lLstReturn);
            orgsMap.put("DDOLocList", lLstLocations);
            orgsMap.put("EmpTypeList",lLstEmpType);
            
            objRes.setResultValue(orgsMap);
            objRes.setViewName("srchRqstInit");
        }
        catch (Exception e)
        {
            logger.error("Exception in getBillTypes. Error is : " + e, e);
        }
        return objRes;
    }

    
    /**
     * It Searches for Approved Requests from the Selected Search 
     * Criteria by DDO for Creation of Bill.
     * 
     * @param objectArgs
     *            ServiceMap
     * @return objRes ResultObject
     */
    public ResultObject getApprovedRequest(Map orgsMap)
    {
        ServiceLocator servLoc = (ServiceLocator) orgsMap.get("serviceLocator");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");

        try
        {
            //BillRequestDAO lObjRqstDAO = new BillRequestDAOImpl(TrnAprvdRqst.class, servLoc.getSessionFactory());
            BillRequestDAO lObjRqstDAO = new BillRequestDAOImpl(servLoc.getSessionFactory());
            Map lMapBillCmnServVo = (Map) orgsMap.get("ResultMap");
            
            setSessionInfo(orgsMap);

            Date lStrDtFrmDt = (Date) lMapBillCmnServVo.get("FrmDt");
            Date lStrDtToDt = (Date) lMapBillCmnServVo.get("ToDt");
            Long lLngBillType = Long.parseLong(lMapBillCmnServVo.get("BillType").toString());
            String lStrLocCode = lMapBillCmnServVo.get("Dept").toString();
            String lStrEmpType = (String) lMapBillCmnServVo.get("EmpType");
            String lStrEmpDesgn = (String) lMapBillCmnServVo.get("EmpDesgn");
            List searchList = null;
            DDOInfoService lDDOInfo = new DDOInfoServiceImpl();
            List lListDDO = lDDOInfo.getDDOInfoByPost(gLngPostId, gLngLangId, gLngDBId, servLoc);
          
            if(lListDDO != null)
            {
            	OrgDdoMst lObjVO = (OrgDdoMst) lListDDO.get(0);
            	searchList = lObjRqstDAO.getApprovedRequest(lStrDtFrmDt, lStrDtToDt, lLngBillType, lObjVO.getDdoCode(),gLngDBId, 
            			lStrLocCode, gLngLangId,lStrEmpDesgn,lStrEmpType);
            }
        
            CmnLookupMstDAO lDAOCmnLkUpMst = new CmnLookupMstDAOImpl(CmnLookupMst.class, servLoc.getSessionFactory());
            
            List lLstEmpType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang("go_ngo", gLngLangId);
            
            orgsMap.put("EmpType", lLstEmpType);
            orgsMap.put("ResultMap", searchList);
            objRes.setResultValue(orgsMap);
            objRes.setViewName("srchRqstResult");

        }
        catch (Exception e)
        {
            logger.error("Exception in getApprovedRequest. Error is : " + e, e);
        }

        return objRes;
    }


    /**
     * It gets the Requested Data required for making the Bills.
     * 
     * @param inputMap
     *            InputMap.
     * @return objRes ResultObject
     */
    public ResultObject getRqstData(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
        	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            
            setSessionInfo(inputMap);

            ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
            
            String lStrRqstID = request.getParameter("chkSelected");
            String[] lStrRqstIDArr = lStrRqstID.split("~");
            String[] lStrEmpType = request.getParameterValues("empType");
            
            		// added by jay for challan to show cardex no on Challan page - start
            
            OrgDdoMst lObjOrgDdoMst = null;
            String lStrCardexNo = null;
            DDOInfoServiceImpl lObjDdoInfoSrvc = new DDOInfoServiceImpl();
            List lLstDDOInfo = lObjDdoInfoSrvc.getDDOInfoByPost(gLngPostId, gLngLangId, gLngDBId, serv);
                        
            
            if (lLstDDOInfo != null && lLstDDOInfo.size() > 0)
            {
            	lObjOrgDdoMst = (OrgDdoMst) lLstDDOInfo.get(0);
                //lStrCardexNo = lObjOrgDdoMst.getCardexNo();          
                //System.out.println("\n Cardex No : " + lStrCardexNo + "\n");                
            }
            else
            {
            	lStrCardexNo = "";
            }

            inputMap.put("challanCardex", lStrCardexNo);
            
        			// added by jay for challan to show cardex no on Challan page - end
                        
            
            
            if (lStrRqstID != null && lStrRqstID.length() > 0)
            {
                BillRequestDAO lObjDAO = new BillRequestDAOImpl(serv.getSessionFactory());
                TrnAprvdRqst lAprvRqstVO = lObjDAO.getAprvdRqstDtls(Long.decode(lStrRqstIDArr[0]));
                
                CmnLookupMstDAO cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
                List lListFund = cmnDAO.getAllChildrenByLookUpNameAndLang("Fund", gLngLangId);
				List lListClassOfExp = cmnDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp", gLngLangId);
				List lBudjetType = cmnDAO.getAllChildrenByLookUpNameAndLang("OnlineBillBudgetType", gLngLangId);
				Object[]  lObjTemp = lBudjetType.toArray();
				Object[] lObjBudjetType = new Object[lObjTemp.length];
				for(Object lObj: lObjTemp)
				{
					CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
					lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO; 
				}
				lObjTemp  = null;
				List lEmpType = cmnDAO.getAllChildrenByLookUpNameAndLang("go_ngo", gLngLangId);
				List lBillType = cmnDAO.getAllChildrenByLookUpNameAndLang("TcBillType", gLngLangId);
				List lLstBillCode = BptmCommonServiceImpl.getLookupValues(lObjRsrcBndle.getString("CMN.BillCode"), gLngLangId, inputMap);
				
				inputMap.put("subjectId", lAprvRqstVO.getBillTypeId());
				ResultObject lResultObj = serv.executeService("EDP_DETAILS", inputMap); //com.tcs.sgv.exprcpt.service.VoucherServiceImpl.getEdpDtlsByBillType()
                Map lMap = (Map) lResultObj.getResultValue();
                List rcptObjHds = (List) lMap.get("rcptObjHds");
                List expObjHds = (List) lMap.get("expObjHds");
                List recObjHds = (List) lMap.get("recObjHds");
                List expEdpList = (List) lMap.get("expEdpList");
                              
                inputMap.put("DeptId", lAprvRqstVO.getEmpDeptId());
                inputMap.put("LangId", gLngLangId);
                lResultObj = serv.executeService("GET_DEPT_DTLS", inputMap);
                lMap = (Map) lResultObj.getResultValue();
                Map lResultMap = (Map) lMap.get("ResultMap");
                SgvoDeptMst lDeptMstVO = (SgvoDeptMst) lResultMap.get("DeptMstVO");

                inputMap.put("LangId", gLngLangId);
                lResultObj = serv.executeService("GET_MONTH_DTLS", inputMap);
                lMap = (Map) lResultObj.getResultValue();
                lResultMap = (Map) lMap.get("ResultMap");
                List lMonthList = (List) lResultMap.get("MonthList");
                
                //**************************************************************************************
        		String[] lChkSeleted = null;
        		StringBuilder lStrSelId = 	new StringBuilder();
        	
        		lChkSeleted = request.getParameterValues("chkSelected"); 
        		if(lChkSeleted != null)
        		{
        			for(int i = 0; i < lChkSeleted.length; ++i)
        			{
        				lStrSelId.append(lChkSeleted[i] + ",");	
        			}
        			
        			lStrSelId.deleteCharAt(lStrSelId.length() - 1);
        		}
        		//**************************************************************************************
        		
        		//************************* GET BILL CONTROL NO ****************************************
        		DDOInfoDAO lDDOInfo = new DDOInfoDAOImpl(serv.getSessionFactory());
        		List lListDDO = lDDOInfo.getDDOInfoByPost(OnlineBillUtil.getParentPost(gLngPostId, serv.getSessionFactory()), gLngLangId, gLngDBId);
        		String lStrBillCntrNo = null;
        		
        		if(lListDDO != null && lListDDO.size() > 0)
        		{
        			OrgDdoMst lVoObj = (OrgDdoMst) lListDDO.get(0);
        			inputMap.put("DDOCode", lVoObj.getDdoCode());
        			lStrBillCntrNo = BptmCommonServiceImpl.getBillCntrlNumSeq(inputMap);
                    
                    logger.info("lStrBillCntrNo generated is : "+ lStrBillCntrNo);
        		}
        		//**************************************************************************************
        		
        		// Calling bill specific method to get bill specific data
        		ConfigOnlineBill lObjConfigVO = ConfigOnlineBillUtil.getInstance(lAprvRqstVO.getBillTypeId(), serv);
        		String lStrIsConfigStatus = lObjConfigVO.getIsConfigured();
                
        		if(lObjConfigVO != null && lObjConfigVO.getIsConfigured().equals("Y"))
        		{	
	                String lStrRqstServ = lObjConfigVO.getRqstSrvc();
	                String lStrPagePath = lObjConfigVO.getJspPath();
	                inputMap.put("AprdRqstIdArray", lStrRqstIDArr);
	                lResultObj = serv.executeService(lStrRqstServ, inputMap);
	                Map lMapResult = (Map) lResultObj.getResultValue();
	                
	                inputMap.put("BillData", lMapResult);
	                inputMap.put("PagePath", lStrPagePath);
        		}
        		//**************************************************************************************
	        	//Attchment	
        		if(lAprvRqstVO.getAttachmentId() != null)
        		{
	        		CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
	                CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(lAprvRqstVO.getAttachmentId());
	                inputMap.put("scan", cmnAttachmentMst);
        		}
                //**************************************************************************************
                
        		inputMap.put("BillTypeId", lAprvRqstVO.getBillTypeId());
        		inputMap.put("NoOfRqst", lStrRqstIDArr.length);        		
        		inputMap.put("BillType", lBillType);
        		inputMap.put("EmpType", lEmpType);
        		inputMap.put("EmpTypeSelectedByUser", lStrEmpType);
        		inputMap.put("FundCombo", lListFund);
                inputMap.put("ClassOfExpCombo", lListClassOfExp);
                inputMap.put("BudTypeCombo", lObjBudjetType);
                inputMap.put("BillCodeList",lLstBillCode);
                inputMap.put("rcptObjHds", rcptObjHds);
                inputMap.put("expObjHds", expObjHds);
                inputMap.put("recObjHds", recObjHds);
                inputMap.put("expEdpList", expEdpList);
                inputMap.put("AprvRqst", lAprvRqstVO);
                inputMap.put("DeptVO", lDeptMstVO);
                inputMap.put("MonthList", lMonthList);
                inputMap.put("SelReqId", lStrSelId);
                inputMap.put("BillCntrNo", lStrBillCntrNo); 
                inputMap.put("currRemarks", "");       
                inputMap.put("ShowObjection", "Y"); 
                inputMap.put("isConfigStatus", lStrIsConfigStatus);
                
            }

            objRes.setResultValue(inputMap);
            objRes.setViewName("createOnlineBill");
        }
        catch (Exception e)
        {
            logger.error("Error in getRqstData and Error is : " + e, e);
        }

        return objRes;
    }
    
    /**
     * It gets the Bill Type required for making the Bills from Scratch.
     * 
     * @param inputMap
     *            InputMap.
     * @return objRes ResultObject
     */
    public ResultObject getBillType(Map inputMap)
    {
    	List lLstReturn = new ArrayList();
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            
            setSessionInfo(inputMap);
            
            BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = 
            	new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
            
            List lLstBillType = lObjCmnSrvcDAOImpl.getBillType(gLngLangId);
            Iterator lItr = lLstBillType.iterator();
            
            CommonVO lObjCmnVO = null;
            Object[] lObj = null;
            
            while (lItr.hasNext())
            {
                lObj = (Object[]) lItr.next();
                lObjCmnVO = new CommonVO();
                
                lObjCmnVO.setCommonId(lObj[0].toString());
                if(lObj[1] == null)
                {
                	lObjCmnVO.setCommonDesc(String.valueOf(lObj[2]));
                }
                else
                {
                	lObjCmnVO.setCommonDesc(String.valueOf(lObj[2]) + " (" + String.valueOf(lObj[1]) + ")");
                }
                
                lLstReturn.add(lObjCmnVO);
            }
            
            inputMap.put("BillType", lLstReturn);
            
            objRes.setResultValue(inputMap);
            objRes.setViewName("newBillSelection");
        }
        catch (Exception e)
        {
            logger.error("Error in getBillType and Error is : " + e, e);
        }

        return objRes;
    }
    
    /**
     * It gets the Data required for Creating the Bills from Scratch.
     * 
     * @param inputMap
     *            InputMap.
     * @return objRes ResultObject
     */
    public ResultObject createBillFrmScratch(Map inputMap)
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

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

            	// added by jay for challan to show cardex no on Challan page - start
            
            OrgDdoMst lObjOrgDdoMst = null;
            String lStrCardexNo = null;
            DDOInfoServiceImpl lObjDdoInfoSrvc = new DDOInfoServiceImpl();
            List lLstDDOInfo = lObjDdoInfoSrvc.getDDOInfoByPost(gLngPostId, gLngLangId, gLngDBId, serv);
                        
            
            if (lLstDDOInfo != null && lLstDDOInfo.size() > 0)
            {
            	lObjOrgDdoMst = (OrgDdoMst) lLstDDOInfo.get(0);
                //lStrCardexNo = lObjOrgDdoMst.getCardexNo();          
                //System.out.println("\n Cardex No : " + lStrCardexNo + "\n");                
            }
            else
            {
            	lStrCardexNo = "";
            }

            inputMap.put("challanCardex", lStrCardexNo);
            
        		// added by jay for challan to show cardex no on Challan page - end

            
            if (lStrRqstID != null && lStrRqstID.length > 0)
            {
                BillRequestDAO lObjDAO = new BillRequestDAOImpl(serv.getSessionFactory());
                TrnAprvdRqst lAprvRqstVO = lObjDAO.getAprvdRqstDtls(Long.parseLong(lStrRqstID[0]));
                
                CmnLookupMstDAO cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
                List lListFund = cmnDAO.getAllChildrenByLookUpNameAndLang("Fund", gLngLangId);
				List lListClassOfExp = cmnDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp", gLngLangId);
				List lBudjetType = cmnDAO.getAllChildrenByLookUpNameAndLang("OnlineBillBudgetType", gLngLangId);
				Object[]  lObjTemp = lBudjetType.toArray();
				Object[] lObjBudjetType = new Object[lObjTemp.length];
				for(Object lObj: lObjTemp)
				{
					CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
					lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO; 
				}
				lObjTemp  = null;
				List lEmpType = cmnDAO.getAllChildrenByLookUpNameAndLang("go_ngo", gLngLangId);
				List lBillType = cmnDAO.getAllChildrenByLookUpNameAndLang("TcBillType", gLngLangId);
				List lLstBillCode = BptmCommonServiceImpl.getLookupValues(lObjRsrcBndle.getString("CMN.BillCode"), gLngLangId, inputMap);
				
				inputMap.put("subjectId", request.getParameter("cmbBillType"));
				ResultObject lResultObj = serv.executeService("EDP_DETAILS", inputMap); //com.tcs.sgv.exprcpt.service.VoucherServiceImpl.getEdpDtlsByBillType()
                Map lMap = (Map) lResultObj.getResultValue();
                List rcptObjHds = (List) lMap.get("rcptObjHds");
                List expObjHds = (List) lMap.get("expObjHds");
                List recObjHds = (List) lMap.get("recObjHds");
                List expEdpList = (List) lMap.get("expEdpList");
                
                /*
                inputMap.put("DeptId", lAprvRqstVO.getEmpDeptId());
                inputMap.put("LangId", gLngLangId);
                lResultObj = serv.executeService("GET_DEPT_DTLS", inputMap);
                lMap = (Map) lResultObj.getResultValue();
                Map lResultMap = (Map) lMap.get("ResultMap");
                SgvoDeptMst lDeptMstVO = (SgvoDeptMst) lMap.get("DeptMstVO");
				*/
                SgvoDeptMst lDeptMstVO = new SgvoDeptMst();
                
                inputMap.put("LangId", gLngLangId);
                lResultObj = serv.executeService("GET_MONTH_DTLS", inputMap);
                lMap = (Map) lResultObj.getResultValue();
                Map lResultMap = (Map) lMap.get("ResultMap");
                List lMonthList = (List) lResultMap.get("MonthList");
                
                //**************************************************************************************
        		String[] lChkSeleted = null;
        		StringBuilder lStrSelId = 	new StringBuilder();
        	
        		lChkSeleted = request.getParameterValues("chkSelected"); 
        		if(lChkSeleted != null)
        		{
        			for(int i = 0; i < lChkSeleted.length; ++i)
        			{
        				lStrSelId.append(lChkSeleted[i] + ",");	
        			}
        			
        			lStrSelId.deleteCharAt(lStrSelId.length() - 1);
        		}
        		//**************************************************************************************
        		
        		//************************* GET BILL CONTROL NO ****************************************
        		DDOInfoDAO lDDOInfo = new DDOInfoDAOImpl(serv.getSessionFactory());
        		List lListDDO = lDDOInfo.getDDOInfoByPost(OnlineBillUtil.getParentPost(gLngPostId, serv.getSessionFactory()), gLngLangId, gLngDBId);
        		String lStrBillCntrNo = null;
        		
        		if(lListDDO != null && lListDDO.size() > 0)
        		{
        			OrgDdoMst lVoObj = (OrgDdoMst) lListDDO.get(0);
        			inputMap.put("DDOCode", lVoObj.getDdoCode());
        			lStrBillCntrNo = BptmCommonServiceImpl.getBillCntrlNumSeq(inputMap);
        		}
        		//**************************************************************************************
        		
        		//Calling bill specific method to get bill specific data
        		ConfigOnlineBill lObjConfigVO = ConfigOnlineBillUtil.getInstance(Long.decode(request.getParameter("cmbBillType")), serv);
        		String lStrIsConfigStatus = lObjConfigVO.getIsConfigured();
        		
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
        		
        		inputMap.put("BillTypeId", request.getParameter("cmbBillType"));
        		inputMap.put("BillType", lBillType);
        		inputMap.put("EmpType", lEmpType);
        		inputMap.put("EmpTypeSelectedByUser", lStrEmpType);
        		inputMap.put("FundCombo", lListFund);
                inputMap.put("ClassOfExpCombo", lListClassOfExp);
                inputMap.put("BudTypeCombo",lObjBudjetType);
                inputMap.put("BillCodeList",lLstBillCode);
                inputMap.put("rcptObjHds", rcptObjHds);
                inputMap.put("expObjHds", expObjHds);
                inputMap.put("recObjHds", recObjHds);
                inputMap.put("expEdpList", expEdpList);
                inputMap.put("AprvRqst", lAprvRqstVO);
                inputMap.put("DeptVO", lDeptMstVO);
                inputMap.put("MonthList", lMonthList);
                inputMap.put("SelReqId", lStrSelId);
                inputMap.put("BillCntrNo", lStrBillCntrNo); 
                inputMap.put("currRemarks", "");
                inputMap.put("isConfigStatus", lStrIsConfigStatus);
            }
            
            objRes.setResultValue(inputMap);
            objRes.setViewName("createOnlineBill");
        }
        catch (Exception e)
        {
            logger.error("Error in createBillFrmScratch and Error is : " + e, e);
            e.printStackTrace();
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





// added by  Saurabh S for practise

/*public ResultObject getApprovedRequest(Map orgsMap)
{
    ServiceLocator servLoc = (ServiceLocator) orgsMap.get("serviceLocator");
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");

    try
    {
        //BillRequestDAO lObjRqstDAO = new BillRequestDAOImpl(TrnAprvdRqst.class, servLoc.getSessionFactory());
        BillRequestDAO lObjRqstDAO = new BillRequestDAOImpl(servLoc.getSessionFactory());
        Map lMapBillCmnServVo = (Map) orgsMap.get("ResultMap");
        
        setSessionInfo(orgsMap);

        Date lStrDtFrmDt = (Date) lMapBillCmnServVo.get("FrmDt");
        Date lStrDtToDt = (Date) lMapBillCmnServVo.get("ToDt");
        Long lLngBillType = Long.parseLong(lMapBillCmnServVo.get("BillType").toString());
        String lStrLocCode = lMapBillCmnServVo.get("Dept").toString();
        String lStrEmpType = (String) lMapBillCmnServVo.get("EmpType");
        String lStrEmpDesgn = (String) lMapBillCmnServVo.get("EmpDesgn");
        List searchList = null;
        DDOInfoService lDDOInfo = new DDOInfoServiceImpl();
        List lListDDO = lDDOInfo.getDDOInfoByPost(gLngPostId, gLngLangId, gLngDBId, servLoc);
      
        if(lListDDO != null)
        {
        	OrgDdoMst lObjVO = (OrgDdoMst) lListDDO.get(0);
        	searchList = lObjRqstDAO.getApprovedRequest(lStrDtFrmDt, lStrDtToDt, lLngBillType, lObjVO.getDdoCode(),gLngDBId, 
        			lStrLocCode, gLngLangId,lStrEmpDesgn,lStrEmpType);
        }
    
        CmnLookupMstDAO lDAOCmnLkUpMst = new CmnLookupMstDAOImpl(CmnLookupMst.class, servLoc.getSessionFactory());
        
        List lLstEmpType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang("go_ngo", gLngLangId);
        
        orgsMap.put("EmpType", lLstEmpType);
        orgsMap.put("ResultMap", searchList);
        objRes.setResultValue(orgsMap);
        objRes.setViewName("srchRqstResult");

    }
    catch (Exception e)
    {
        logger.error("Exception in getApprovedRequest. Error is : " + e, e);
    }

    return objRes;
}*/

