package com.tcs.sgv.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * DDOServiceImpl
 * This class is used to get Details of DDO and their department 
 * based on Cardex Number provided by end-user
 * 
 * Date of Creation : 11th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * ===================== 
 * Hiral 23-Oct-2007 For making changes for code formating
 */
public class DDOServiceImpl extends ServiceImpl implements DDOService {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	public ResultObject getDDOData(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			List lLstTemp = new ArrayList();
			List lLstDept = new ArrayList();
			int i = 0;
			//System.out.println("Value of Cardex in DDO SERvice : "					+ request.getParameter("CardexNo"));
			String lStrCardexNo = (String) request.getParameter("CardexNo");
			String lStrDdoNo = null;
			String lStrDdoName = null;
			String lStrDdoCode = null;
			String lStrBillCntrlNum = null;
			String lStrAjaxResult = null;

			Long lLngLangId = SessionHelper.getLangId(inputMap);
			Long lLngDBId = SessionHelper.getDbId(inputMap);
			String lStrLocCode = SessionHelper.getLocationCode(inputMap);
			OrgDdoMst lObjOrgDdoMst = new OrgDdoMst();

			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			DDOInfoServiceImpl lObjDDOInfoSrvcImpl = new DDOInfoServiceImpl();

			//System.out.println(" CardexNo--" + lStrCardexNo);
			List lLstDDOInfo = lObjDDOInfoSrvcImpl.getDDOInfoByCardex(
					lStrCardexNo, lStrLocCode, lLngLangId, lLngDBId, serv);
			//System.out.println("List from DDOInfoSERviceImpl == "					+ lLstDDOInfo.toString());

			if (lLstDDOInfo != null && lLstDDOInfo.size() > 0) {
				lObjOrgDdoMst = (OrgDdoMst) lLstDDOInfo.get(0);
				if (lObjOrgDdoMst != null) {
					//lStrDdoNo = lObjOrgDdoMst.getDdoNo();
					lStrDdoName = lObjOrgDdoMst.getDdoName();
					if (lStrDdoName != null)
						lStrDdoName = lStrDdoName.replaceAll("&", "&amp;");
					//System.out.println("Value of DDO Name : " + lStrDdoName);
					lStrDdoCode = lObjOrgDdoMst.getDdoCode();
					lLstDept = lObjDDOInfoSrvcImpl.getBillOfficeForDDO(serv,
							lStrDdoCode, lLngLangId, lLngDBId);
					CommonVO lCmnVo = new CommonVO();
					lCmnVo.setCommonDesc("--Select--");
					lCmnVo.setCommonId("-1");

	/*				System.out							.println("Value of List Department before add All : "
									+ lLstDept.toString()
									+ " and SIZE is : "
									+ lLstDept.size());*/
					lLstTemp.addAll(lLstDept);
/*					System.out
							.println("Value of Temp List in DDO after Add All : "
									+ lLstTemp.toString()
									+ " and SIZE IS : "
									+ lLstTemp.size());*/

					lLstDept.add(0, lCmnVo);
					for (i = 1; i < lLstTemp.size() - 1; i++) {
						//System.out.println("Inside loop : " + i								+ " from List : " + lLstTemp.get(i - 1));
						lLstDept.add(i, lLstTemp.get(i - 1));
					}
					//System.out.println("Value of Temp List in DDO : "							+ lLstDept.toString());
					inputMap.put("DDOCode", lStrDdoCode);
					inputMap.put("DDONo", lStrDdoNo);
					//System.out.println("Value of DDO Code :: " + lStrDdoCode);
					// //System.out.println("\n\n`````````` " + lStrDdoDept);
					lStrBillCntrlNum = BptmCommonServiceImpl
							.getBillControlNumber(inputMap);
					//System.out.println("\n\n^^^^^^^^^^^^^^^^^^ "							+ lStrBillCntrlNum);
					try {
						String dept = request.getParameter("dept");
						//System.out.println("DEPARTMENT : " + dept);
						if (dept != null && dept.equalsIgnoreCase("yes")) {
							lStrAjaxResult = new AjaxXmlBuilder().addItems(
									lLstDept, "commonDesc", "commonId")
									.toString();
						} else {
							lStrAjaxResult = new AjaxXmlBuilder().addItem(
									"DDONo", lStrDdoNo).addItem("DDOName",
									lStrDdoName)
									.addItem("DDOCode", lStrDdoCode).addItem(
											"id_BillControlNo",
											lStrBillCntrlNum).toString();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					String dept = request.getParameter("dept");
					//System.out.println("DEPARTMENT : " + dept);
					if (dept != null && dept.equalsIgnoreCase("yes")) {
						lStrAjaxResult = new AjaxXmlBuilder().addItems(
								lLstDept, "commonDesc", "commonId").toString();
					} else {
						lStrAjaxResult = new AjaxXmlBuilder().addItem("DDONo",
								lStrDdoNo).addItem("DDOName", lStrDdoName)
								.addItem("DDOCode", lStrDdoCode).addItem(
										"id_BillControlNo", lStrBillCntrlNum)
								.toString();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//System.out.println("Value of AJAX for getting DDO Details : "					+ lStrAjaxResult);
			inputMap.put("ajaxKey", lStrAjaxResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
		}
		return objRes;
	}

	/*
	 * public ResultObject getDdoHead(Map inputMap) { ResultObject objRes = new
	 * ResultObject(ErrorConstants.SUCCESS); try { ServiceLocator serv =
	 * (ServiceLocator)inputMap.get("serviceLocator"); HttpServletRequest
	 * request = (HttpServletRequest) inputMap.get("requestObj");
	 * FinancialYearDAOImpl lObjFinYearDAOImpl = new
	 * FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory()); Long
	 * lLngFinYrId =
	 * Long.valueOf(String.valueOf(lObjFinYearDAOImpl.getFinYearIdByCurDate()));
	 * Long lLngLangId = SessionHelper.getLangId(inputMap);
	 * //System.out.println("Value of DDO CODE from Request : "
	 * +request.getParameter("DDOCode")); String lStrDdoCode =
	 * request.getParameter("DDOCode")!=null?(String)request.getParameter("DDOCode"):null;
	 * logger.info("DDO Code : " +lStrDdoCode); String lStrPLNPL =
	 * request.getParameter("cmbBudType")!=null?(String)request.getParameter("cmbBudType"):null;
	 * 
	 * String lStrGrantBudType = "";
	 * 
	 * if(lStrPLNPL.equals("State_Nonplan") ||
	 * lStrPLNPL.equals("Other_Agencies") || lStrPLNPL.equals("PlanBud_80")) {
	 * lStrGrantBudType = "NP"; } else if(lStrPLNPL.equals("State_Plan") ||
	 * lStrPLNPL.equals("PlanBud_15") || lStrPLNPL.equals("StatePlan_5")) {
	 * lStrGrantBudType = "PL"; } else if(lStrPLNPL.equals("Central_Plan") ||
	 * lStrPLNPL.equals("Fully_Centrally") ||
	 * lStrPLNPL.equals("Partly_Centrally")) { lStrGrantBudType = "CSS"; }
	 * 
	 * 
	 * logger.info("Budget Type : " +lStrGrantBudType); String lStrStatus =
	 * gObjRsrcBndle.getString("CMN.ActiveStatus"); List lListHeads = null;
	 * 
	 * BudgetHdDtlsDAO lObjBudHdDtlsDao = new
	 * BudgetHdDtlsDAOImpl(lLngFinYrId,serv.getSessionFactory()); lListHeads =
	 * lObjBudHdDtlsDao.getGrantHeadsForDDO(lStrDdoCode, lStrGrantBudType,
	 * lStrStatus, lLngFinYrId, lLngLangId);
	 * 
	 * inputMap.put("DDOHeadDtls", lListHeads);
	 * objRes.setViewName("DdoHeadPopup"); objRes.setResultValue(inputMap); }
	 * catch(Exception e) { objRes.setResultValue(null); objRes.setThrowable(e);
	 * objRes.setResultCode(ErrorConstants.ERROR);
	 * objRes.setViewName("errorPage"); e.printStackTrace(); } return objRes; }
	 */
}
