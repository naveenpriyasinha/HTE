package com.tcs.sgv.common.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.GrantDtlDAO;
import com.tcs.sgv.common.dao.GrantDtlDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAO;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;


/**
 * 
 * @author 602399
 * 
 */

public class GrantDtlServiceImpl extends ServiceImpl 
							implements GrantDtlService
{
    Log logger = LogFactory.getLog(getClass());

    public ResultObject getGrantAmtForDDO(Map inputMap)
	{		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		try
		{
			HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
			ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
			
			Long lLngPostId = SessionHelper.getPostId(inputMap);
			Long lLngDBId = SessionHelper.getDbId(inputMap);
			Long lLngLandId = SessionHelper.getLangId(inputMap);
                     
			BillCommonDAO lObjBillDAO = new BillCommonDAOImpl(serv.getSessionFactory());
            
            SgvcFinYearMst lFinYrVO = lObjBillDAO.getFinYrInfo(Calendar.getInstance()
                    .getTime(), lLngLandId);
                
			Long lLngFinYrId = lFinYrVO.getFinYearId();
			String lStrPLNPL = request.getParameter("cmbBudType");
			lStrPLNPL = lStrPLNPL.trim();
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
			
			DDOInfoService lDDOInfo = (DDOInfoService) new DDOInfoServiceImpl();
			List lListDDO = lDDOInfo.getDDOInfoByPost(lLngPostId, lLngLandId, lLngDBId, serv);
			String lStrDDOCode = null;
			if(lListDDO != null && lListDDO.size() > 0)
			{
				OrgDdoMst lObjVO = (OrgDdoMst) lListDDO.get(0);
				lStrDDOCode = lObjVO.getDdoCode(); 
			}
			
			String lStrDemandCode = request.getParameter("txtDmd").trim();
			String lStrBudMjrHd = request.getParameter("txtMjrHd").trim();
			String lStrBudSubMjrHd = request.getParameter("txtSbMjrHd").trim();
			String lStrBudMinHd = request.getParameter("txtMnrHd").trim();
			String lStrBudSubHd = request.getParameter("txtSbHd").trim();
			String lStrDtldHd = request.getParameter("txtDtldHd").trim();
			
			BudgetHdDtlsService lBudHeadObj = new BudgetHdDtlsServiceImpl();
			boolean isValidBudHd = lBudHeadObj.validateBudgetHeads(serv, lLngFinYrId, lStrDemandCode, 
											lStrBudMjrHd, lStrBudSubMjrHd, 
											lStrBudMinHd, lStrBudSubHd, lStrDtldHd, 
											lLngLandId,"");
			if(isValidBudHd)
			{
				GrantDtlDAO lGrantDAO = (GrantDtlDAO) new GrantDtlDAOImpl(serv.getSessionFactory());
				BigDecimal lGrantAmt = lGrantDAO.getGrantAmtForDDO(lLngFinYrId, lStrGrantBudType, 
									lStrDDOCode, lStrDemandCode, lStrBudMjrHd, lStrBudSubMjrHd, 
									lStrBudMinHd, lStrBudSubHd);    		
	
				//***********	GET TOTAL EXP. FOR DDO **************************************
				inputMap.put("demandCode", lStrDemandCode);
				inputMap.put("majorHead", lStrBudMjrHd);
				inputMap.put("subMajorHead", lStrBudSubMjrHd);
				inputMap.put("minorHead", lStrBudMinHd);
				inputMap.put("subHead", lStrBudSubHd);
				inputMap.put("budgetType", lStrPLNPL);
				inputMap.put("ddoCode", lStrDDOCode);
				
				ResultObject lObjResult = serv.executeService("GET_DDO_EXP", inputMap);
				
				Map lResultMap = (Map) lObjResult.getResultValue();
				BigDecimal lDDOExp = BigDecimal.valueOf(Double.parseDouble(lResultMap.get("TotalDDOExp").toString()));
				//****************************************************************************
				
				StringBuilder lStrGrant = new StringBuilder();
				lStrGrant.append(" <Grant> ");
				lStrGrant.append(" 		<GrntAmt> ");
				lStrGrant.append(String.valueOf(lGrantAmt));
				lStrGrant.append(" 		</GrntAmt> ");
				lStrGrant.append(" 		<DDOExpAmt> ");
				lStrGrant.append(String.valueOf(lDDOExp));
				lStrGrant.append("		 </DDOExpAmt> ");
				lStrGrant.append(" 		<BudHdValidFlag> ");
				lStrGrant.append("true");
				lStrGrant.append(" 		</BudHdValidFlag> ");
				lStrGrant.append(" </Grant> ");
				
				String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
				inputMap.put("ajaxKey", lStrResult);
			}
			else
			{
				StringBuilder lStrGrant = new StringBuilder();
				lStrGrant.append(" <Grant> ");
				lStrGrant.append(" 		<GrntAmt> ");
				lStrGrant.append("");
				lStrGrant.append(" 		</GrntAmt> ");
				lStrGrant.append(" <DDOExpAmt> ");
				lStrGrant.append("");
				lStrGrant.append(" </DDOExpAmt> ");
				lStrGrant.append(" 		<BudHdValidFlag> ");
				lStrGrant.append("false");
				lStrGrant.append(" 		</BudHdValidFlag> ");
				lStrGrant.append(" </Grant> ");
				
				String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
				inputMap.put("ajaxKey", lStrResult);
			}
									
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);			
		}
		catch(Exception e)
		{
			logger.error("Error in getGrantAmountForDDO and Error is : " + e, e);
		}
		
		return objRes;
	}
}	