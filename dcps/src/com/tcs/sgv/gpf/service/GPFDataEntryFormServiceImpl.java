/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Nov 29, 2011		Jayraj Chudasama								
 *******************************************************************************
 */
package com.tcs.sgv.gpf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.dao.FinancialYearDAO;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.gpf.dao.GPFDataEntryFormDAO;
import com.tcs.sgv.gpf.dao.GPFDataEntryFormDAOImpl;
import com.tcs.sgv.gpf.dao.GPFRequestProcessDAO;
import com.tcs.sgv.gpf.dao.GPFRequestProcessDAOImpl;
import com.tcs.sgv.gpf.dao.ScheduleGenerationDAO;
import com.tcs.sgv.gpf.dao.ScheduleGenerationDAOImpl;
import com.tcs.sgv.gpf.valueobject.MstEmpGpfAcc;
import com.tcs.sgv.gpf.valueobject.MstGpfAdvance;
import com.tcs.sgv.gpf.valueobject.MstGpfChallanDtls;
import com.tcs.sgv.gpf.valueobject.MstGpfEmpSubscription;
import com.tcs.sgv.gpf.valueobject.MstGpfMonthly;
import com.tcs.sgv.gpf.valueobject.MstGpfReq;
import com.tcs.sgv.gpf.valueobject.MstGpfYearly;
import com.tcs.sgv.gpf.valueobject.TrnEmpGpfAcc;

/**
 * Class Description - 
 *
 *
 * @author Jayraj Chudasama
 * @version 0.1
 * @since JDK 5.0
 * Nov 29, 2011
 */
public class GPFDataEntryFormServiceImpl extends ServiceImpl implements GPFDataEntryFormService
{
	private final Log gLogger = LogFactory.getLog(getClass());

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */
	

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;		

	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");			
			serv = (ServiceLocator) inputMap.get("serviceLocator");									
			gLngPostId = SessionHelper.getPostId(inputMap);			
			gLngUserId = SessionHelper.getUserId(inputMap);											
		} catch (Exception e) {
			gLogger.error("Error is: "+ e, e);
		}

	}
	
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/gpf/GPFConstants");
	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.gpf.service.GPFDataEntryFormService#loadGPFDataEntryForm(java.util.Map)
	 */
	public ResultObject loadGPFDataEntryForm(Map<String, Object> inputMap) 
	{
		setSessionInfo(inputMap);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"Fail");
		List lLstMonths = null;
		List lLstYears = null;
		List lLstPurposeCatRA = null;
		List lLstPurposeCatNRA = null;
		
		String lStrUserType="";	
		String lStrDdoCode="";
		List lLstEmpList = null;
		
		try
		{
			GPFDataEntryFormDAO lObjDataEntryFormDAO = new GPFDataEntryFormDAOImpl(TrnEmpGpfAcc.class, serv.getSessionFactory());
			lStrUserType = StringUtility.getParameter("userType", request);		
			inputMap.put("userType", lStrUserType);
			
			if(lStrUserType.equals("DEO"))
			{
				DcpsCommonDAO lObjCommomDao = new DcpsCommonDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
				lLstMonths = lObjCommomDao.getMonths();
				lLstYears = lObjCommomDao.getYears();
				lLstPurposeCatRA = IFMSCommonServiceImpl.getLookupValues("Purpose Category", SessionHelper
						.getLangId(inputMap), inputMap);
				lLstPurposeCatNRA = IFMSCommonServiceImpl.getLookupValues("Purpose Category NRA", SessionHelper
						.getLangId(inputMap), inputMap);
				inputMap.put("lstPurposeCatNRA", lLstPurposeCatNRA);
				inputMap.put("lstPurposeCatRA", lLstPurposeCatRA);
				inputMap.put("lLstMonths", lLstMonths);
				inputMap.put("lLstYears", lLstYears);
				resObj.setResultValue(inputMap);
				resObj.setViewName("GPFDataEntryForm");
			}
			
			else if(lStrUserType.equals("DDO"))
			{
				lStrDdoCode = lObjDataEntryFormDAO.getDdoCodeForDDO(gLngPostId);
				lLstEmpList = lObjDataEntryFormDAO.getEmpListForVerification(lStrDdoCode);
				inputMap.put("EmpList", lLstEmpList);
				inputMap.put("userType", "DDO");
				resObj.setResultValue(inputMap);
				resObj.setViewName("GPFDataEntryWorklist");
			}
		}
		catch(Exception e){
			IFMSCommonServiceImpl.setErrorProperties(gLogger, resObj, e, "Error in loadGPFDataEntryForm");
		}
		return resObj;
	}
	
	public ResultObject loadDataEntryDraftRequest(Map<String, Object> inputMap) 
	{
		setSessionInfo(inputMap);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"Fail");
		
		List lLstEmpList = null;
		
		try{
			GPFDataEntryFormDAO lObjDataEntryFormDAO = new GPFDataEntryFormDAOImpl(TrnEmpGpfAcc.class, serv.getSessionFactory());
			lLstEmpList = lObjDataEntryFormDAO.getEmpListForDraftReq();
			inputMap.put("EmpList", lLstEmpList);
			inputMap.put("userType", "DEO");
			resObj.setResultValue(inputMap);
			resObj.setViewName("GPFDataEntryWorklist");
		}
		catch(Exception e){
			IFMSCommonServiceImpl.setErrorProperties(gLogger, resObj, e, "Error in loadDataEntryDraftRequest");			
		}
		return resObj;
	}
	
	public ResultObject loadDataEntryCase(Map<String, Object> inputMap)
	{	
		setSessionInfo(inputMap);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"Fail");
		
		List lLstMonths = null;
		List lLstYears = null;
		List lLstRADetailsHis = null;
		List lLstRADetailsCur = null;
		List lLstNRADetails = null;
		List lLstSubDetails = null;
		List lLstPurposeCatRA = null;
		List lLstPurposeCatNRA = null;
		List lLstScheduleDtls = null;
		Integer lIntScheduleSize = 0;
		Object[] obj = null;
		
		String lStrGpfAccNo="";
		String lStrUserType="";
		String lStrSevaarthId="";
		String lStrName="";
		String lStrMonthlySub="";
		String lStrOpeningBalc="";
		String lStrDeoRemark="";		
		String lStrReqType="";
		String lStrTrnAccPk="";
		
		
		try
		{
			GPFDataEntryFormDAO lObjDataEntryFormDAO = new GPFDataEntryFormDAOImpl(TrnEmpGpfAcc.class, serv.getSessionFactory());
			DcpsCommonDAO lObjCommomDao = new DcpsCommonDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			
			lStrUserType = StringUtility.getParameter("userType", request);		
			inputMap.put("userType", lStrUserType);
			lStrSevaarthId = StringUtility.getParameter("sevaarthId", request);
			lStrName = StringUtility.getParameter("name", request);
			lStrMonthlySub = StringUtility.getParameter("montlySub", request);
			lStrOpeningBalc = StringUtility.getParameter("openingBalc", request);
			lStrDeoRemark = StringUtility.getParameter("deoRemark", request);			
			
			lStrGpfAccNo = lObjDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);
			
			lLstMonths = lObjCommomDao.getMonths();
			lLstYears = lObjCommomDao.getYears();
			
			if(lStrUserType.equals("DEO")){
				lStrReqType = "Draft";
				lStrTrnAccPk = lObjDataEntryFormDAO.getTrnEmpGpfAccID(lStrSevaarthId,lStrReqType);
				lLstRADetailsHis = lObjDataEntryFormDAO.getRADetailsHis(lStrGpfAccNo,lStrReqType);
				lLstRADetailsCur = lObjDataEntryFormDAO.getRADetailsCur(lStrGpfAccNo,lStrReqType);
				lLstNRADetails = lObjDataEntryFormDAO.getNRADetails(lStrGpfAccNo,lStrReqType);
				lLstSubDetails = lObjDataEntryFormDAO.getSubscriptionDetails(lStrGpfAccNo,lStrReqType);
				lLstScheduleDtls = lObjDataEntryFormDAO.getScheduleDetails(lStrGpfAccNo,lStrReqType);
				if(lLstScheduleDtls != null && lLstScheduleDtls.size() > 0){
					lIntScheduleSize = lLstScheduleDtls.size();
				}
			}
			else if(lStrUserType.equals("DDO")){
				lStrReqType = "Forward";
				lStrTrnAccPk = lObjDataEntryFormDAO.getTrnEmpGpfAccID(lStrSevaarthId,lStrReqType);
				lLstRADetailsHis = lObjDataEntryFormDAO.getRADetailsHis(lStrGpfAccNo,lStrReqType);
				lLstRADetailsCur = lObjDataEntryFormDAO.getRADetailsCur(lStrGpfAccNo,lStrReqType);
				lLstNRADetails = lObjDataEntryFormDAO.getNRADetails(lStrGpfAccNo,lStrReqType);
				lLstSubDetails = lObjDataEntryFormDAO.getSubscriptionDetails(lStrGpfAccNo,lStrReqType);
				lLstScheduleDtls = lObjDataEntryFormDAO.getScheduleDetails(lStrGpfAccNo,lStrReqType);
				if(lLstScheduleDtls != null && lLstScheduleDtls.size() > 0){
					lIntScheduleSize = lLstScheduleDtls.size();
				}
			}
			
			
			lLstPurposeCatRA = IFMSCommonServiceImpl.getLookupValues("Purpose Category", SessionHelper
					.getLangId(inputMap), inputMap);
			lLstPurposeCatNRA = IFMSCommonServiceImpl.getLookupValues("Purpose Category NRA", SessionHelper
					.getLangId(inputMap), inputMap);
			
			if(!lLstRADetailsCur.isEmpty()){
				obj = (Object[]) lLstRADetailsCur.get(0);
			}
			
			inputMap.put("SevaarthId", lStrSevaarthId);
			inputMap.put("Name",lStrName);
			inputMap.put("MonthlySub",lStrMonthlySub);
			inputMap.put("TrnAccPk",lStrTrnAccPk);
			inputMap.put("OpeningBalc",lStrOpeningBalc);
			inputMap.put("RAHistory",lLstRADetailsHis);
			inputMap.put("ojbCur", obj);
			inputMap.put("NRADtls",lLstNRADetails);
			inputMap.put("SubDtls",lLstSubDetails);
			inputMap.put("DeoRemark",lStrDeoRemark);
			inputMap.put("lstPurposeCatRA", lLstPurposeCatRA);
			inputMap.put("lstPurposeCatNRA", lLstPurposeCatNRA);
			inputMap.put("lLstMonths", lLstMonths);
			inputMap.put("lLstYears", lLstYears);
			inputMap.put("ScheduleDtls", lLstScheduleDtls);
			inputMap.put("scheduleSize",lIntScheduleSize);
			
			resObj.setResultValue(inputMap);
			resObj.setViewName("GPFDataEntryForm");
		}
		catch(Exception e){
			IFMSCommonServiceImpl.setErrorProperties(gLogger, resObj, e, "Error in loadDataEntryCase");			
		}
		
		return resObj;		
	}
	
	public ResultObject getEmployeeNameFromEmpCode(Map<String, Object> inputMap) {
		setSessionInfo(inputMap);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		
		String lStrName = "";
		Double lDblBasicPay = null;
		String lStrEmpCode="";
		String lSBStatus="";
		List lLstEmpDtls = null;
		
		GPFDataEntryFormDAO lObjGpfDataEntryFormDAO = new GPFDataEntryFormDAOImpl(MstEmp.class, serv.getSessionFactory());
		
		try{
			lStrEmpCode = StringUtility.getParameter("EmpCode", request);
			
			if(lStrEmpCode != ""){
				lLstEmpDtls = lObjGpfDataEntryFormDAO.getEmployeeNameAndPay(lStrEmpCode);
				if(lLstEmpDtls != null && lLstEmpDtls.size() > 0){
					Object [] lObj = (Object[]) lLstEmpDtls.get(0);
					lStrName = lObj[0].toString();
					lDblBasicPay = (Double) lObj[1];
				}else{
					lStrName = "Invalid";
				}
			}
		}
		catch(Exception e){
			IFMSCommonServiceImpl.setErrorProperties(gLogger, resObj, e, "Error in getEmployeeCode");			
		}
		
			lSBStatus = getResponseXMLDocEmployeeCode(lStrName,lDblBasicPay).toString();			
		
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			
			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
			return resObj;
	}
	
	private StringBuilder getResponseXMLDocEmployeeCode(String lStrEmployeeCode, Double lDblBasicPay) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<EMPCODE>");
		lStrBldXML.append(lStrEmployeeCode);
		lStrBldXML.append("</EMPCODE>");
		lStrBldXML.append("<BASICPAY>");
		lStrBldXML.append(lDblBasicPay);
		lStrBldXML.append("</BASICPAY>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
	
	public ResultObject saveData(Map<String, Object> inputMap)
	{
		setSessionInfo(inputMap);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		Map<String, List> lScheduleVOMap = new HashMap<String, List>();
		List<MstGpfAdvance> lLstAdvanceDetailsNRA = null;
		List<MstGpfAdvance> lLstAdvanceDetailsRA = null;		
		List<MstGpfEmpSubscription> lLstSubscriptionDetails = null;
		List<MstGpfMonthly> lLstVoucherDetails = null;
		List<MstGpfChallanDtls> lLstChallanDetails = null;		
		MstGpfAdvance lObjMstGpfAdvance = null;
		MstGpfEmpSubscription lObjMstGpfEmpSubscription = null;
		MstGpfMonthly lObjMstGpfMonthly = null;
		MstGpfChallanDtls lObjMstGpfChallanDtls = null;
		TrnEmpGpfAcc lObjTrnEmpGpfAcc = null;
		MstGpfReq lObjMstGpfReq = null;
		Long lLngAdvanceId = null;
		Long lLngEmpSubId = null;
		Long lLngTrnEmpGpfId = null;
		Long lLngMonthlyId = null;
		Long lLngChallanDtlsId = null;
		Long lLngBillGrpId = null;
		Long lLngGpfReqId = null;
		String[] iSaveOrUpdate = null;
		String lStrTransactionId ="";
		String lStrMonthlySub="";
		String lStrFlag = "false";		
		String lStrSevaarthId="";
		String lStrGpfAccNo="";		
		String lStrDEORemarks="";
		String lStrOpeningBalc="";
		String lStrEmpName="";
		String lStrSaveOrUpdate="";		
		String lStrSaveOrUpdateCur="";
		String lStrSaveOrUpdateVchr="";
		String lStrSaveOrUpdateChlln="";
		String lStrTrnAccPk="";
		
		try{
			GPFDataEntryFormDAO lObjGpfDataEntryFormDAO = new GPFDataEntryFormDAOImpl(MstGpfAdvance.class, serv.getSessionFactory());
			GPFRequestProcessDAO lObjRequestProcessDAO = new GPFRequestProcessDAOImpl(MstGpfReq.class, serv.getSessionFactory());
			
			lLstAdvanceDetailsNRA = (List<MstGpfAdvance>) inputMap.get("AdvanceDetailsNRA");
			lLstAdvanceDetailsRA = (List<MstGpfAdvance>) inputMap.get("AdvanceDetailsRA");
			lLstSubscriptionDetails = (List<MstGpfEmpSubscription>) inputMap.get("SubscriptionDetails");
			lScheduleVOMap = (Map<String, List>) inputMap.get("ScheduleDtls");
			MstGpfAdvance lObjMstGpfAdvanceCur = new MstGpfAdvance();
			lObjMstGpfAdvanceCur = (MstGpfAdvance) inputMap.get("AdvanceDetailsRACur");
			
			lLstChallanDetails = lScheduleVOMap.get("lLstChallanDtls");
			lLstVoucherDetails = lScheduleVOMap.get("lLstVoucherDtls");
						
			lStrSevaarthId = StringUtility.getParameter("txtEmployeeCode", request);
			lStrDEORemarks = StringUtility.getParameter("txtDeoRemarks", request);				
			lStrOpeningBalc = StringUtility.getParameter("txtAmount",request);
			lStrEmpName = StringUtility.getParameter("txtEmployeeName", request);			
			lStrTrnAccPk = StringUtility.getParameter("hidTrnAccId", request);	
			
			if (lLstAdvanceDetailsNRA != null && !lLstAdvanceDetailsNRA.isEmpty()) 
			{
				lStrSaveOrUpdate = (String) inputMap.get("isSaveOrUpdateNRA");
				iSaveOrUpdate = lStrSaveOrUpdate.split(",");
				for (int lIntCnt = 0; lIntCnt < lLstAdvanceDetailsNRA.size(); lIntCnt++) {
					lObjMstGpfAdvance = new MstGpfAdvance();
					lObjMstGpfAdvance = lLstAdvanceDetailsNRA.get(lIntCnt);					
					
					lObjMstGpfAdvance.setStatusFlag("D");
					
					if(iSaveOrUpdate[lIntCnt].equals("1")){
						lLngAdvanceId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_ADVANCE", inputMap);
						lObjMstGpfAdvance.setMstGpfAdvanceId(lLngAdvanceId);
						lStrTransactionId = lObjRequestProcessDAO.getNewTransactionId(lStrSevaarthId);
						lObjMstGpfAdvance.setTransactionId(lStrTransactionId);
						lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);					
						lObjMstGpfAdvance.setGpfAccNo(lStrGpfAccNo);
						lObjGpfDataEntryFormDAO.create(lObjMstGpfAdvance);
						
						lObjMstGpfReq = new MstGpfReq();
						lLngGpfReqId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_REQ", inputMap);
						lObjMstGpfReq.setMstGpfReqId(lLngGpfReqId);
						lObjMstGpfReq.setTransactionId(lStrTransactionId);
						lObjMstGpfReq.setReqDtlId(lObjMstGpfAdvance.getMstGpfAdvanceId());
						lObjMstGpfReq.setReqType(lObjMstGpfAdvance.getAdvanceType());
						lObjRequestProcessDAO.create(lObjMstGpfReq);
						
					}else if(iSaveOrUpdate[lIntCnt].equals("2")){
						lObjGpfDataEntryFormDAO.update(lObjMstGpfAdvance);
					}					
				}
			}			
				 
			if (lLstAdvanceDetailsRA != null && !lLstAdvanceDetailsRA.isEmpty()) 
			{
				lStrSaveOrUpdate = (String) inputMap.get("isSaveOrUpdateRAHis");				
				iSaveOrUpdate = lStrSaveOrUpdate.split(",");
				for (int lIntCnt = 0; lIntCnt < lLstAdvanceDetailsRA.size(); lIntCnt++) {
					lObjMstGpfAdvance = new MstGpfAdvance();
					lObjMstGpfAdvance = lLstAdvanceDetailsRA.get(lIntCnt);
																
					lObjMstGpfAdvance.setStatusFlag("D");
					
					if(iSaveOrUpdate[lIntCnt].equals("1")){
						lLngAdvanceId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_ADVANCE", inputMap);
						lObjMstGpfAdvance.setMstGpfAdvanceId(lLngAdvanceId);
						lStrTransactionId = lObjRequestProcessDAO.getNewTransactionId(lStrSevaarthId);
						lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);					
						lObjMstGpfAdvance.setGpfAccNo(lStrGpfAccNo);
						lObjMstGpfAdvance.setTransactionId(lStrTransactionId);
						lObjGpfDataEntryFormDAO.create(lObjMstGpfAdvance);	
						
						lObjMstGpfReq = new MstGpfReq();
						lLngGpfReqId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_REQ", inputMap);
						lObjMstGpfReq.setMstGpfReqId(lLngGpfReqId);
						lObjMstGpfReq.setTransactionId(lStrTransactionId);
						lObjMstGpfReq.setReqDtlId(lObjMstGpfAdvance.getMstGpfAdvanceId());
						lObjMstGpfReq.setReqType(lObjMstGpfAdvance.getAdvanceType());
						lObjRequestProcessDAO.create(lObjMstGpfReq);
					}else if(iSaveOrUpdate[lIntCnt].equals("2")){
						lObjGpfDataEntryFormDAO.update(lObjMstGpfAdvance);	
					}																					
				}
			}
			
			if(lObjMstGpfAdvanceCur != null)
			{
				lStrSaveOrUpdateCur = (String) inputMap.get("isSaveOrUpdateRACur");
				lObjMstGpfAdvance = new MstGpfAdvance();
				lObjMstGpfAdvance = lObjMstGpfAdvanceCur;
				
				lObjMstGpfAdvance.setStatusFlag("DC");
				
				if(lStrSaveOrUpdateCur.equals("1")){
					lLngAdvanceId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_ADVANCE", inputMap);
					lObjMstGpfAdvance.setMstGpfAdvanceId(lLngAdvanceId);
					lStrTransactionId = lObjRequestProcessDAO.getNewTransactionId(lStrSevaarthId);
					lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);					
					lObjMstGpfAdvance.setGpfAccNo(lStrGpfAccNo);
					lObjMstGpfAdvance.setTransactionId(lStrTransactionId);
					lObjGpfDataEntryFormDAO.create(lObjMstGpfAdvance);
					
					lObjMstGpfReq = new MstGpfReq();
					lLngGpfReqId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_REQ", inputMap);
					lObjMstGpfReq.setMstGpfReqId(lLngGpfReqId);
					lObjMstGpfReq.setTransactionId(lStrTransactionId);
					lObjMstGpfReq.setReqDtlId(lObjMstGpfAdvance.getMstGpfAdvanceId());
					lObjMstGpfReq.setReqType(lObjMstGpfAdvance.getAdvanceType());
					lObjRequestProcessDAO.create(lObjMstGpfReq);
				}else if(lStrSaveOrUpdateCur.equals("2")){
					lObjGpfDataEntryFormDAO.update(lObjMstGpfAdvance);
				}
			}
			
			if(lLstVoucherDetails != null && !lLstVoucherDetails.isEmpty()){
				for (int lIntCnt = 0; lIntCnt < lLstVoucherDetails.size(); lIntCnt++) 
				{
					lStrSaveOrUpdateVchr = (String) inputMap.get("isSaveOrUpdateVchr");
					iSaveOrUpdate = lStrSaveOrUpdateVchr.split(",");
					lObjMstGpfMonthly = new MstGpfMonthly();
					lObjMstGpfMonthly = lLstVoucherDetails.get(lIntCnt);											
					
					if(iSaveOrUpdate[lIntCnt].equals("1")){
						lLngMonthlyId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_MONTHLY", inputMap);
						lObjMstGpfMonthly.setMstGpfMonthlyId(lLngMonthlyId);
						lLngBillGrpId = Long.parseLong(lObjGpfDataEntryFormDAO.getBillGroupId(lStrSevaarthId));
						lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);					
						lStrTransactionId = lObjGpfDataEntryFormDAO.getTranIdForRAAdvance(lStrGpfAccNo,"Draft");					
						lObjMstGpfMonthly.setBillgroupId(lLngBillGrpId);
						lObjMstGpfMonthly.setGpfAccNo(lStrGpfAccNo);
						lObjMstGpfMonthly.setAdvanceTrnId(lStrTransactionId);
						lObjGpfDataEntryFormDAO.create(lObjMstGpfMonthly);
					}else if(iSaveOrUpdate[lIntCnt].equals("2")){
						lObjGpfDataEntryFormDAO.update(lObjMstGpfMonthly);
					}						
				}
			}
			
			if(lLstChallanDetails != null && !lLstChallanDetails.isEmpty())
			{
				for(int lIntCnt=0; lIntCnt < lLstChallanDetails.size(); lIntCnt++)
				{
					lStrSaveOrUpdateChlln = (String) inputMap.get("isSaveOrUpdateChln");
					iSaveOrUpdate = lStrSaveOrUpdateChlln.split(",");
					lObjMstGpfChallanDtls = new MstGpfChallanDtls();
					lObjMstGpfChallanDtls = lLstChallanDetails.get(lIntCnt);					
					
					lObjMstGpfChallanDtls.setStatusFlag("D");
					
					if(iSaveOrUpdate[lIntCnt].equals("1")){
						lLngChallanDtlsId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_CHALLAN_DTLS", inputMap);
						lObjMstGpfChallanDtls.setMstGpfChallanDtlsId(lLngChallanDtlsId);
						lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);
						lStrTransactionId = lObjGpfDataEntryFormDAO.getTranIdForRAAdvance(lStrGpfAccNo,"Draft");					
						lObjMstGpfChallanDtls.setGpfAccNo(lStrGpfAccNo);
						lObjMstGpfChallanDtls.setAdvanceTrnId(lStrTransactionId);
						lObjGpfDataEntryFormDAO.create(lObjMstGpfChallanDtls);
					}else if(iSaveOrUpdate[lIntCnt].equals("2")){
						lObjGpfDataEntryFormDAO.update(lObjMstGpfChallanDtls);
					}						
				}
			}
				
			if(lLstSubscriptionDetails != null && !lLstSubscriptionDetails.isEmpty())
			{
				lStrSaveOrUpdate = (String) inputMap.get("isSaveOrUpdateSub");
				iSaveOrUpdate = lStrSaveOrUpdate.split(",");
				for(int lIntCnt = 0;lIntCnt < lLstSubscriptionDetails.size();lIntCnt++)
				{
					lObjMstGpfEmpSubscription = new MstGpfEmpSubscription();
					lObjMstGpfEmpSubscription = lLstSubscriptionDetails.get(lIntCnt);						
					
					lObjMstGpfEmpSubscription.setStatusFlag("D");
					
					if(iSaveOrUpdate[lIntCnt].equals("1")){
						lLngEmpSubId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_EMP_SUBSCRIPTION", inputMap);
						lObjMstGpfEmpSubscription.setGpfEmpSubscriptionId(lLngEmpSubId);
						lStrTransactionId = lObjRequestProcessDAO.getNewTransactionId(lStrSevaarthId);
						lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);						
						lObjMstGpfEmpSubscription.setGpfAccNo(lStrGpfAccNo);
						lObjMstGpfEmpSubscription.setTransactionId(lStrTransactionId);
						lObjGpfDataEntryFormDAO.create(lObjMstGpfEmpSubscription);
						
						lObjMstGpfReq = new MstGpfReq();
						lLngGpfReqId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_REQ", inputMap);
						lObjMstGpfReq.setMstGpfReqId(lLngGpfReqId);
						lObjMstGpfReq.setTransactionId(lStrTransactionId);
						lObjMstGpfReq.setReqDtlId(lObjMstGpfEmpSubscription.getGpfEmpSubscriptionId());
						lObjMstGpfReq.setReqType("CS");
						lObjRequestProcessDAO.create(lObjMstGpfReq);
					}else if(iSaveOrUpdate[lIntCnt].equals("2")){						
						lObjGpfDataEntryFormDAO.update(lObjMstGpfEmpSubscription);
					}						
				}
			}				
				
			if(lStrTrnAccPk.equals("")){
				lObjTrnEmpGpfAcc = new TrnEmpGpfAcc();
				lLngTrnEmpGpfId = IFMSCommonServiceImpl.getNextSeqNum("TRN_EMP_GPF_ACC", inputMap);
				lObjTrnEmpGpfAcc.setTrnEmpGpfAccId(lLngTrnEmpGpfId);
				lObjTrnEmpGpfAcc.setSevaarthId(lStrSevaarthId);
				lObjTrnEmpGpfAcc.setGpfAccNo(lStrGpfAccNo);
				lObjTrnEmpGpfAcc.setName(lStrEmpName);	
				lObjTrnEmpGpfAcc.setStatusFlag("D");					
				lObjTrnEmpGpfAcc.setDeoRemarks(lStrDEORemarks);
				lObjGpfDataEntryFormDAO.create(lObjTrnEmpGpfAcc);
			}
			else{
				lObjGpfDataEntryFormDAO.updateTrnEmpGpfAcc(lStrTrnAccPk, lStrDEORemarks, "", "Save");
			}
				
			lStrMonthlySub = StringUtility.getParameter("txtPrevSubAmt",request);
				
			GPFDataEntryFormDAO lObjGpfDataEntryFormDAOAcc = new GPFDataEntryFormDAOImpl(MstEmpGpfAcc.class, serv.getSessionFactory());			
			
			lObjGpfDataEntryFormDAOAcc.updateMstEmpGpfAcc(lStrSevaarthId, lStrOpeningBalc, lStrMonthlySub);
				
			lStrFlag = "true";
	
			String lSBStatus = getResponseXMLDocSaveData(lStrFlag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			
			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
			return resObj;
		}
		catch(Exception e)
		{
			IFMSCommonServiceImpl.setErrorProperties(gLogger, resObj, e, "Error in saveData");			
		}
		return resObj;
	}
	
	public ResultObject forwardData(Map<String, Object> inputMap)
	{
		setSessionInfo(inputMap);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		Map<String, List> lScheduleVOMap = new HashMap<String, List>();
		List<MstGpfAdvance> lLstAdvanceDetailsNRA = null;
		List<MstGpfAdvance> lLstAdvanceDetailsRA = null;		
		List<MstGpfEmpSubscription> lLstSubscriptionDetails = null;
		List<MstGpfMonthly> lLstVoucherDetails = null;
		List<MstGpfChallanDtls> lLstChallanDetails = null;
		List lLstPrvsData = null;		
		MstGpfAdvance lObjMstGpfAdvance = null;
		MstGpfEmpSubscription lObjMstGpfEmpSubscription = null;
		MstGpfMonthly lObjMstGpfMonthly = null;
		MstGpfChallanDtls lObjMstGpfChallanDtls = null;
		TrnEmpGpfAcc lObjTrnEmpGpfAcc = null;
		MstGpfReq lObjMstGpfReq = null;
		Long lLngAdvanceId = null;
		Long lLngEmpSubId = null;
		Long lLngTrnEmpGpfId = null;
		Long lLngMonthlyId = null;
		Long lLngChallanDtlsId = null;
		Long lLngBillGrpId = null;
		Long lLngGpfReqId = null;
		String[] iSaveOrUpdate = null;
		String lStrTransactionId ="";
		String lStrMonthlySub="";
		String lStrFlag = "false";		
		String lStrSevaarthId="";
		String lStrGpfAccNo="";		
		String lStrDEORemarks="";
		String lStrOpeningBalc="";
		String lStrEmpName="";
		String lStrSaveOrUpdate="";		
		String lStrSaveOrUpdateCur="";
		String lStrSaveOrUpdateVchr="";
		String lStrSaveOrUpdateChlln="";
		String lStrTrnAccPk="";
		
		try{
			GPFDataEntryFormDAO lObjGpfDataEntryFormDAO = new GPFDataEntryFormDAOImpl(MstGpfAdvance.class, serv.getSessionFactory());
			GPFRequestProcessDAO lObjRequestProcessDAO = new GPFRequestProcessDAOImpl(MstGpfReq.class, serv.getSessionFactory());
			
			lLstAdvanceDetailsNRA = (List<MstGpfAdvance>) inputMap.get("AdvanceDetailsNRA");
			lLstAdvanceDetailsRA = (List<MstGpfAdvance>) inputMap.get("AdvanceDetailsRA");
			lLstSubscriptionDetails = (List<MstGpfEmpSubscription>) inputMap.get("SubscriptionDetails");
			lScheduleVOMap = (Map<String, List>) inputMap.get("ScheduleDtls");
			MstGpfAdvance lObjMstGpfAdvanceCur = new MstGpfAdvance();
			lObjMstGpfAdvanceCur = (MstGpfAdvance) inputMap.get("AdvanceDetailsRACur");
			
			lLstChallanDetails = lScheduleVOMap.get("lLstChallanDtls");
			lLstVoucherDetails = lScheduleVOMap.get("lLstVoucherDtls");
						
			lStrSevaarthId = StringUtility.getParameter("txtEmployeeCode", request);
			lStrDEORemarks = StringUtility.getParameter("txtDeoRemarks", request);				
			lStrOpeningBalc = StringUtility.getParameter("txtAmount",request);
			lStrEmpName = StringUtility.getParameter("txtEmployeeName", request);			
			lStrTrnAccPk = StringUtility.getParameter("hidTrnAccId", request);
									
			lLstPrvsData = lObjGpfDataEntryFormDAO.chkIfEmpExist(lStrGpfAccNo);
			if(lLstPrvsData != null && !lLstPrvsData.isEmpty())
			{
				lStrFlag = "Exist";
			}
			else{
			
				if (lLstAdvanceDetailsNRA != null && !lLstAdvanceDetailsNRA.isEmpty()) 
				{
					lStrSaveOrUpdate = (String) inputMap.get("isSaveOrUpdateNRA");
					iSaveOrUpdate = lStrSaveOrUpdate.split(",");
					for (int lIntCnt = 0; lIntCnt < lLstAdvanceDetailsNRA.size(); lIntCnt++) {
						lObjMstGpfAdvance = new MstGpfAdvance();
						lObjMstGpfAdvance = lLstAdvanceDetailsNRA.get(lIntCnt);					
						
						lObjMstGpfAdvance.setStatusFlag("F");
						
						if(iSaveOrUpdate[lIntCnt].equals("1")){
							lLngAdvanceId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_ADVANCE", inputMap);
							lObjMstGpfAdvance.setMstGpfAdvanceId(lLngAdvanceId);
							lStrTransactionId = lObjRequestProcessDAO.getNewTransactionId(lStrSevaarthId);
							lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);					
							lObjMstGpfAdvance.setGpfAccNo(lStrGpfAccNo);
							lObjMstGpfAdvance.setTransactionId(lStrTransactionId);
							lObjGpfDataEntryFormDAO.create(lObjMstGpfAdvance);
							
							lObjMstGpfReq = new MstGpfReq();
							lLngGpfReqId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_REQ", inputMap);
							lObjMstGpfReq.setMstGpfReqId(lLngGpfReqId);
							lObjMstGpfReq.setTransactionId(lStrTransactionId);
							lObjMstGpfReq.setReqDtlId(lObjMstGpfAdvance.getMstGpfAdvanceId());
							lObjMstGpfReq.setReqType(lObjMstGpfAdvance.getAdvanceType());
							lObjRequestProcessDAO.create(lObjMstGpfReq);
							
						}else if(iSaveOrUpdate[lIntCnt].equals("2")){
							lObjGpfDataEntryFormDAO.update(lObjMstGpfAdvance);
						}
						
					}
				}			
					 
				if (lLstAdvanceDetailsRA != null && !lLstAdvanceDetailsRA.isEmpty()) 
				{
					lStrSaveOrUpdate = (String) inputMap.get("isSaveOrUpdateRAHis");				
					iSaveOrUpdate = lStrSaveOrUpdate.split(",");
					for (int lIntCnt = 0; lIntCnt < lLstAdvanceDetailsRA.size(); lIntCnt++) {
						lObjMstGpfAdvance = new MstGpfAdvance();
						lObjMstGpfAdvance = lLstAdvanceDetailsRA.get(lIntCnt);
																	
						lObjMstGpfAdvance.setStatusFlag("F");
						
						if(iSaveOrUpdate[lIntCnt].equals("1")){
							lLngAdvanceId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_ADVANCE", inputMap);
							lObjMstGpfAdvance.setMstGpfAdvanceId(lLngAdvanceId);
							lStrTransactionId = lObjRequestProcessDAO.getNewTransactionId(lStrSevaarthId);
							lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);					
							lObjMstGpfAdvance.setGpfAccNo(lStrGpfAccNo);
							lObjMstGpfAdvance.setTransactionId(lStrTransactionId);
							lObjGpfDataEntryFormDAO.create(lObjMstGpfAdvance);	
							
							lObjMstGpfReq = new MstGpfReq();
							lLngGpfReqId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_REQ", inputMap);
							lObjMstGpfReq.setMstGpfReqId(lLngGpfReqId);
							lObjMstGpfReq.setTransactionId(lStrTransactionId);
							lObjMstGpfReq.setReqDtlId(lObjMstGpfAdvance.getMstGpfAdvanceId());
							lObjMstGpfReq.setReqType(lObjMstGpfAdvance.getAdvanceType());
							lObjRequestProcessDAO.create(lObjMstGpfReq);
							
						}else if(iSaveOrUpdate[lIntCnt].equals("2")){
							lObjGpfDataEntryFormDAO.update(lObjMstGpfAdvance);	
						}																					
					}
				}
				
				if(lObjMstGpfAdvanceCur != null)
				{
					lStrSaveOrUpdateCur = (String) inputMap.get("isSaveOrUpdateRACur");
					lObjMstGpfAdvance = new MstGpfAdvance();
					lObjMstGpfAdvance = lObjMstGpfAdvanceCur;
					
					lObjMstGpfAdvance.setStatusFlag("FC");
					
					if(lStrSaveOrUpdateCur.equals("1")){
						lLngAdvanceId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_ADVANCE", inputMap);
						lObjMstGpfAdvance.setMstGpfAdvanceId(lLngAdvanceId);
						lStrTransactionId = lObjRequestProcessDAO.getNewTransactionId(lStrSevaarthId);
						lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);					
						lObjMstGpfAdvance.setGpfAccNo(lStrGpfAccNo);
						lObjMstGpfAdvance.setTransactionId(lStrTransactionId);
						lObjGpfDataEntryFormDAO.create(lObjMstGpfAdvance);
						
						lObjMstGpfReq = new MstGpfReq();
						lLngGpfReqId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_REQ", inputMap);
						lObjMstGpfReq.setMstGpfReqId(lLngGpfReqId);
						lObjMstGpfReq.setTransactionId(lStrTransactionId);
						lObjMstGpfReq.setReqDtlId(lObjMstGpfAdvance.getMstGpfAdvanceId());
						lObjMstGpfReq.setReqType(lObjMstGpfAdvance.getAdvanceType());
						lObjRequestProcessDAO.create(lObjMstGpfReq);
						
					}else if(lStrSaveOrUpdateCur.equals("2")){
						lObjGpfDataEntryFormDAO.update(lObjMstGpfAdvance);
					}
				}
								
				if(lLstVoucherDetails != null && !lLstVoucherDetails.isEmpty()){
					for (int lIntCnt = 0; lIntCnt < lLstVoucherDetails.size(); lIntCnt++) 
					{
						lStrSaveOrUpdateVchr = (String) inputMap.get("isSaveOrUpdateVchr");
						iSaveOrUpdate = lStrSaveOrUpdateVchr.split(",");
						lObjMstGpfMonthly = new MstGpfMonthly();
						lObjMstGpfMonthly = lLstVoucherDetails.get(lIntCnt);											
						
						if(iSaveOrUpdate[lIntCnt].equals("1")){
							lLngMonthlyId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_MONTHLY", inputMap);
							lObjMstGpfMonthly.setMstGpfMonthlyId(lLngMonthlyId);
							lLngBillGrpId = Long.parseLong(lObjGpfDataEntryFormDAO.getBillGroupId(lStrSevaarthId));
							lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);					
							lStrTransactionId = lObjGpfDataEntryFormDAO.getTranIdForRAAdvance(lStrGpfAccNo,"Forward");						
							lObjMstGpfMonthly.setBillgroupId(lLngBillGrpId);
							lObjMstGpfMonthly.setGpfAccNo(lStrGpfAccNo);
							lObjMstGpfMonthly.setAdvanceTrnId(lStrTransactionId);
							lObjGpfDataEntryFormDAO.create(lObjMstGpfMonthly);
						}else if(iSaveOrUpdate[lIntCnt].equals("2")){
							lObjGpfDataEntryFormDAO.update(lObjMstGpfMonthly);
						}						
					}
				}
				
				if(lLstChallanDetails != null && !lLstChallanDetails.isEmpty())
				{
					for(int lIntCnt=0; lIntCnt < lLstChallanDetails.size(); lIntCnt++)
					{
						lStrSaveOrUpdateChlln = (String) inputMap.get("isSaveOrUpdateChln");
						iSaveOrUpdate = lStrSaveOrUpdateChlln.split(",");
						lObjMstGpfChallanDtls = new MstGpfChallanDtls();
						lObjMstGpfChallanDtls = lLstChallanDetails.get(lIntCnt);						
						
						lObjMstGpfChallanDtls.setStatusFlag("F");
						
						if(iSaveOrUpdate[lIntCnt].equals("1")){
							lLngChallanDtlsId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_CHALLAN_DTLS", inputMap);
							lObjMstGpfChallanDtls.setMstGpfChallanDtlsId(lLngChallanDtlsId);
							lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);
							lStrTransactionId = lObjGpfDataEntryFormDAO.getTranIdForRAAdvance(lStrGpfAccNo,"Forward");
							lObjMstGpfChallanDtls.setGpfAccNo(lStrGpfAccNo);
							lObjMstGpfChallanDtls.setAdvanceTrnId(lStrTransactionId);
							lObjGpfDataEntryFormDAO.create(lObjMstGpfChallanDtls);
						}else if(iSaveOrUpdate[lIntCnt].equals("2")){
							lObjGpfDataEntryFormDAO.update(lObjMstGpfChallanDtls);
						}						
					}
				}
				
				if(lLstSubscriptionDetails != null && !lLstSubscriptionDetails.isEmpty())
				{
					lStrSaveOrUpdate = (String) inputMap.get("isSaveOrUpdateSub");
					iSaveOrUpdate = lStrSaveOrUpdate.split(",");
					for(int lIntCnt = 0;lIntCnt < lLstSubscriptionDetails.size();lIntCnt++)
					{
						lObjMstGpfEmpSubscription = new MstGpfEmpSubscription();
						lObjMstGpfEmpSubscription = lLstSubscriptionDetails.get(lIntCnt);						
						
						lObjMstGpfEmpSubscription.setStatusFlag("F");
						
						if(iSaveOrUpdate[lIntCnt].equals("1")){
							lLngEmpSubId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_EMP_SUBSCRIPTION", inputMap);
							lObjMstGpfEmpSubscription.setGpfEmpSubscriptionId(lLngEmpSubId);
							lStrTransactionId = lObjRequestProcessDAO.getNewTransactionId(lStrSevaarthId);
							lStrGpfAccNo = lObjGpfDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);						
							lObjMstGpfEmpSubscription.setGpfAccNo(lStrGpfAccNo);
							lObjMstGpfEmpSubscription.setTransactionId(lStrTransactionId);
							lObjGpfDataEntryFormDAO.create(lObjMstGpfEmpSubscription);
							
							lObjMstGpfReq = new MstGpfReq();
							lLngGpfReqId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_REQ", inputMap);
							lObjMstGpfReq.setMstGpfReqId(lLngGpfReqId);
							lObjMstGpfReq.setTransactionId(lStrTransactionId);
							lObjMstGpfReq.setReqDtlId(lObjMstGpfEmpSubscription.getGpfEmpSubscriptionId());
							lObjMstGpfReq.setReqType("CS");
							lObjRequestProcessDAO.create(lObjMstGpfReq);
							
						}else if(iSaveOrUpdate[lIntCnt].equals("2")){							
							lObjGpfDataEntryFormDAO.update(lObjMstGpfEmpSubscription);
						}						
					}
				}				
						
				if(lStrTrnAccPk.equals("")){
					lObjTrnEmpGpfAcc = new TrnEmpGpfAcc();
					lLngTrnEmpGpfId = IFMSCommonServiceImpl.getNextSeqNum("TRN_EMP_GPF_ACC", inputMap);
					lObjTrnEmpGpfAcc.setTrnEmpGpfAccId(lLngTrnEmpGpfId);
					lObjTrnEmpGpfAcc.setSevaarthId(lStrSevaarthId);
					lObjTrnEmpGpfAcc.setGpfAccNo(lStrGpfAccNo);
					lObjTrnEmpGpfAcc.setName(lStrEmpName);						
					lObjTrnEmpGpfAcc.setStatusFlag("F");					
					lObjTrnEmpGpfAcc.setDeoRemarks(lStrDEORemarks);
					lObjGpfDataEntryFormDAO.create(lObjTrnEmpGpfAcc);
				}
				else{					
					lObjGpfDataEntryFormDAO.updateTrnEmpGpfAcc(lStrTrnAccPk, lStrDEORemarks, "", "Forward");
				}
				
				
				lStrMonthlySub = StringUtility.getParameter("txtPrevSubAmt",request);
				
				lObjGpfDataEntryFormDAO.updateMstEmpGpfAcc(lStrSevaarthId, lStrOpeningBalc, lStrMonthlySub);
				lStrFlag = "true";
			}
			String lSBStatus = getResponseXMLDocSaveData(lStrFlag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			
			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
			return resObj;
		}
		catch(Exception e)
		{
			IFMSCommonServiceImpl.setErrorProperties(gLogger, resObj, e, "Error in forwardData");			
		}
		return resObj;
	}
	
	public ResultObject approveRequest(Map<String, Object> inputMap)
	{
		setSessionInfo(inputMap);
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
				
		List lLstSubscription = null;
		List lLstAdvance = null;
		List lLstMonthlyDtls = null;
		List lLstChallanDtls = null;
		Object[] obj = null;
		String lStrSevaarthId="";
		String lStrDdoRemarks="";
		String lStrFlag="";
		String lStrGpfAccNo="";
		String lStrOpeningBalc="";		
		String lStrFinCode = "";
		Long lLngMstGpfYearlyId = null;
		Long lLngMonthlyId = null;
		Long lLngBillGrpId = null;			
		Integer lIntNoOfMonth = null;
		Double lDblRegularSubscription = null;
		Double lDblOpeningBalance = null;
		Double lDblClosingBalance = null;
		Double lDblAdvanceSanctioned = null;
		Double lDblPrePayOfAdvance = null;
		Integer lIntFinYear=null;		
		MstGpfYearly lObjMstGpfYearly = null;		
		MstGpfAdvance lObjMstGpfAdvance = null;		
		MstGpfMonthly lObjMstGpfMonthly = null;
		Date lObjDate = new Date();
		
		try{
			GPFDataEntryFormDAO lObjDataEntryFormDAO = new GPFDataEntryFormDAOImpl(TrnEmpGpfAcc.class, serv.getSessionFactory());
			ScheduleGenerationDAO lObjScheduleGenerationDAO = new ScheduleGenerationDAOImpl(MstGpfMonthly.class, serv.getSessionFactory());
			lStrSevaarthId = StringUtility.getParameter("txtEmployeeCode", request);
			lStrDdoRemarks = StringUtility.getParameter("txtDdoRemarks", request);
			lStrOpeningBalc = StringUtility.getParameter("txtAmount", request);
			lStrGpfAccNo = lObjDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);
			
			lObjDataEntryFormDAO.updateTrnEmpGpfAcc(lStrSevaarthId, "", lStrDdoRemarks, "Approve");
			
			
			FinancialYearDAO lObjFinancialYearDAO = new FinancialYearDAOImpl(SgvcFinYearMst.class, serv.getSessionFactory());
			lObjMstGpfYearly = new MstGpfYearly();
			lLngMstGpfYearlyId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_YEARLY", inputMap);
			lStrGpfAccNo = lObjDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);
			lIntFinYear = lObjFinancialYearDAO.getFinYearIdByCurDate();
			lObjMstGpfYearly.setMstGpfYearlyId(lLngMstGpfYearlyId);
			lObjMstGpfYearly.setGpfAccNo(lStrGpfAccNo);
			lObjMstGpfYearly.setOpeningBalance(Double.parseDouble(lStrOpeningBalc));
			lObjMstGpfYearly.setClosingBalance(Double.parseDouble(lStrOpeningBalc));
			lObjMstGpfYearly.setFinYearId((lIntFinYear.longValue())-1);
			lObjDataEntryFormDAO.create(lObjMstGpfYearly);
						
			lLstSubscription = lObjDataEntryFormDAO.getSubscriptionDetails(lStrGpfAccNo, "Forward");
			if(lLstSubscription != null && !lLstSubscription.isEmpty())
			{
				for(int lIntCnt=0;lIntCnt<lLstSubscription.size();lIntCnt++)
				{					
					obj = (Object[]) lLstSubscription.get(lIntCnt);					
					lObjDataEntryFormDAO.updateEmpSubscription(obj[3].toString(),"Approve");
				}
			}
					
			lLstAdvance = lObjDataEntryFormDAO.getAdvanceDetails(lStrGpfAccNo);
			if(lLstAdvance != null && !lLstAdvance.isEmpty())
			{
				for(int lIntCnt=0;lIntCnt<lLstAdvance.size();lIntCnt++)
				{
					lObjMstGpfAdvance = new MstGpfAdvance();
					lObjMstGpfAdvance = (MstGpfAdvance) lLstAdvance.get(lIntCnt);
					lObjMstGpfAdvance.setStatusFlag("A");
					lObjDataEntryFormDAO.update(lObjMstGpfAdvance);
				}
			}
			
			lLstMonthlyDtls = lObjDataEntryFormDAO.getScheduleDetails(lStrGpfAccNo, "Forward");
			if(lLstMonthlyDtls != null && !lLstMonthlyDtls.isEmpty())
			{
				for(int lIntCnt=0;lIntCnt<lLstMonthlyDtls.size();lIntCnt++)
				{					
					obj = (Object[]) lLstMonthlyDtls.get(lIntCnt);
					if(obj[0].toString().equals("Voucher")){
						lObjDataEntryFormDAO.updateGpfMonthly(obj[7].toString(),Integer.parseInt(obj[8].toString()),Long.parseLong(obj[9].toString()),lStrGpfAccNo);						
					}
				}
			}
			
			Integer lIntMon = 4;						
			Integer lIntCurMonth = lObjDate.getMonth()+1;
			Integer lIntFinancialYear = lObjFinancialYearDAO.getFinYearIdByCurDate();
			if(lIntCurMonth >= 4 && lIntCurMonth <= 12){
				lIntNoOfMonth = lIntCurMonth - 4;
			}else{
				lIntNoOfMonth = lIntCurMonth + 8;
			}
			
			for(int lIntCnt=0;lIntCnt<lIntNoOfMonth;lIntCnt++)
			{
				lLngMonthlyId = lObjDataEntryFormDAO.getMonthlyIDForMonth(lStrGpfAccNo,lIntMon,lIntFinancialYear.longValue());
				if(lLngMonthlyId == null){
					lObjMstGpfMonthly = new MstGpfMonthly();
					lLngMonthlyId = IFMSCommonServiceImpl.getNextSeqNum("MST_GPF_MONTHLY", inputMap);
					lObjMstGpfMonthly.setMstGpfMonthlyId(lLngMonthlyId);
					lLngBillGrpId = Long.parseLong(lObjDataEntryFormDAO.getBillGroupId(lStrSevaarthId));
					lStrGpfAccNo = lObjDataEntryFormDAO.getGpfAccNo(lStrSevaarthId);										
					lObjMstGpfMonthly.setBillgroupId(lLngBillGrpId);
					lObjMstGpfMonthly.setGpfAccNo(lStrGpfAccNo);
					lDblRegularSubscription = lObjDataEntryFormDAO.getMonthlySubscription(lIntMon, lIntFinancialYear.longValue(), lStrGpfAccNo);
					lObjMstGpfMonthly.setRegularSubscription(lDblRegularSubscription);
					lObjMstGpfMonthly.setMonthId(lIntMon.longValue());
					lObjMstGpfMonthly.setFinYearId(lIntFinancialYear.longValue());
					lObjMstGpfMonthly.setStatus("A");
					lObjDataEntryFormDAO.create(lObjMstGpfMonthly);
				}
				
				lObjMstGpfMonthly = new MstGpfMonthly();
				lObjMstGpfMonthly = (MstGpfMonthly) lObjScheduleGenerationDAO.read(lLngMonthlyId);
				lStrFinCode = lObjScheduleGenerationDAO.getFinYearCodeForFinYearId(lIntFinancialYear.longValue());
				lDblOpeningBalance = lObjScheduleGenerationDAO.getOpeningBalForCurrMonth(lStrGpfAccNo, lIntMon.longValue(),lIntFinancialYear.longValue());
				lDblAdvanceSanctioned = lObjScheduleGenerationDAO.getAdvanceSanctionedForMonth(lStrGpfAccNo, lIntMon, lStrFinCode);
				lDblPrePayOfAdvance = lObjDataEntryFormDAO.getChallanPaidForMonth(lStrGpfAccNo, lIntMon, lStrFinCode, Long.parseLong(gObjRsrcBndle.getString("GPF.PREPAYOFADVANCE")));
				lDblClosingBalance = lDblOpeningBalance + lDblPrePayOfAdvance + lObjMstGpfMonthly.getRegularSubscription() - lDblAdvanceSanctioned;
				if(lObjMstGpfMonthly.getAdvanceRecovery()!= null){
					lDblClosingBalance += lObjMstGpfMonthly.getAdvanceRecovery();
				}
				
				lObjMstGpfMonthly.setOpeningBalance(lDblOpeningBalance);				
				lObjMstGpfMonthly.setAdvanceSanctioned(lDblAdvanceSanctioned);
				lObjMstGpfMonthly.setPrePayOfAdvance(lDblPrePayOfAdvance);
				lObjMstGpfMonthly.setClosingBalance(lDblClosingBalance);
				lObjScheduleGenerationDAO.update(lObjMstGpfMonthly);
				
				lIntMon++;
				if(lIntMon == 13){
					lIntMon = 1;
					lIntFinancialYear++;
				}
			}
			
			lLstChallanDtls = lObjDataEntryFormDAO.getScheduleDetails(lStrGpfAccNo, "Forward");
			if(lLstChallanDtls != null && !lLstChallanDtls.isEmpty())
			{
				for(int lIntCnt=0;lIntCnt<lLstChallanDtls.size();lIntCnt++)
				{					
					obj = (Object[]) lLstChallanDtls.get(lIntCnt);
					if(obj[0].toString().equals("Challan")){
						lObjDataEntryFormDAO.updateChallanDetails(obj[6].toString());						
					}					
				}
			}
			
			lStrFlag = "true";
			
			String lSBStatus = getResponseXMLDocSaveData(lStrFlag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			
			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
			return resObj;
		}
		catch(Exception e)
		{
			IFMSCommonServiceImpl.setErrorProperties(gLogger, resObj, e, "Error in approveRequest");			
		}
		return resObj;
	}
	
	private StringBuilder getResponseXMLDocSaveData(String lStrFlag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<FLAG>");
		lStrBldXML.append(lStrFlag);
		lStrBldXML.append("</FLAG>");		
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
}
