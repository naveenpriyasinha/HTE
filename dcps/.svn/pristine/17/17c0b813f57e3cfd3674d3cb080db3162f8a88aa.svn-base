package com.tcs.sgv.pensionproc.service;


import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;
import com.tcs.sgv.pensionproc.dao.PensionerReportDao;
import com.tcs.sgv.pensionproc.dao.PensionerReportDaoImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PensionerViewServiceImpl extends ServiceImpl{
	
	private final Log gLogger = LogFactory.getLog(getClass());
	  private String gStrPostId = null;
	  private String gStrLocationCode = null;
	  private Long gLngPostId = null;
	  private HttpServletRequest request = null;
	  private ServiceLocator serv = null;
	  private HttpSession session = null;
	  private Date gDtCurDate = null;
	  private String gStrUserId = null;
	  Long gLngUserId = null;
	  private Long gLngDBId = null;
	  private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");

	  private void setSessionInfo(Map inputMap)
	  {
	    try
	    {
	      this.request = ((HttpServletRequest)inputMap.get("requestObj"));
	      this.serv = ((ServiceLocator)inputMap.get("serviceLocator"));
	      this.session = this.request.getSession();
	      this.gStrPostId = SessionHelper.getPostId(inputMap).toString();
	      this.gLngPostId = SessionHelper.getPostId(inputMap);
	      this.gStrLocationCode = SessionHelper.getLocationCode(inputMap);
	      this.gLngUserId = SessionHelper.getUserId(inputMap);
	      this.gStrUserId = this.gLngUserId.toString();
	      this.gLngDBId = SessionHelper.getDbId(inputMap);
	      this.gDtCurDate = SessionHelper.getCurDate();
	    } catch (Exception e) {
	      this.gLogger.error(" Error is : " + e, e);
	    }
	  }
	  
	  
	  public ResultObject pensionerReport(Map inputMap) {
		    ResultObject resObj = new ResultObject(0, "FAIL");
		    try
		    {
		      setSessionInfo(inputMap);

		      String hidElementId = StringUtility.getParameter("elementId", 
		        this.request);
		      inputMap.put("hidElementId", hidElementId);

		      PensionerReportDao lObjViewReportDAO = new PensionerReportDaoImpl(TrnDcpsContribution.class, this.serv.getSessionFactory());

		      List treasuries = null;
		      treasuries = lObjViewReportDAO.getTreasury("100003");

		      inputMap.put("TREASURIES", treasuries);
		      resObj.setResultValue(inputMap);
		      resObj.setViewName("PensionerReport");
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		      this.gLogger.error(" Error is : " + e, e);
		      resObj.setResultValue(null);
		      resObj.setThrowable(e);
		      resObj.setResultCode(-1);
		      resObj.setViewName("errorPage");
		    }

		    return resObj;
		  }
	  
	  
	  public ResultObject getBankForTreasury(Map<String, Object> inputMap) throws Exception {
		    this.gLogger.info("Inside getBankFromTreasury");
		    setSessionInfo(inputMap);
		    PensionerReportDao lObjViewReportDAO = new PensionerReportDaoImpl(TrnDcpsContribution.class, this.serv.getSessionFactory());
		    DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());

		    ResultObject resObj = new ResultObject(0, "FAIL");
		    String lStrTreasuryLocCode = this.request.getParameter("cmbTreasuryCode");
		    this.gLogger.info("Treasury code:" + lStrTreasuryLocCode);
		    List banknames = lObjViewReportDAO.getBankForTreasuryDao(lStrTreasuryLocCode);
		    this.gLogger.info("size" + banknames.size());
		    this.gLogger.info("Outside getTreasuryForDDO");
		    inputMap.put("banknames", banknames);
		    this.gLogger.info("AJAXXXXX");
		    String ajaxResult = null;

		    ajaxResult = new AjaxXmlBuilder().addItems(banknames, "desc", "id").toString();
		    this.gLogger.info("Ajax result:" + ajaxResult);
		    inputMap.put("ajaxKey", ajaxResult);
		    resObj.setResultCode(0);
		    resObj.setResultValue(inputMap);
		    resObj.setViewName("ajaxData");

		    return resObj;
		  }

	  public ResultObject getBranchForBank(Map<String, Object> inputMap) throws Exception {
		    this.gLogger.info("Inside getBranchForBank");
		    setSessionInfo(inputMap);
		    PensionerReportDao lObjViewReportDAO = new PensionerReportDaoImpl(TrnDcpsContribution.class, this.serv.getSessionFactory());
		    DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());

		    ResultObject resObj = new ResultObject(0, "FAIL");
		    String lStrTreasuryLocCode = this.request.getParameter("cmbTreasuryCode");
		    String BankCode = this.request.getParameter("cmbBankName");
		    this.gLogger.info("Treasury code:" + lStrTreasuryLocCode);
		    List branchnames = lObjViewReportDAO.getBranchForBankDao(lStrTreasuryLocCode, BankCode);
		    this.gLogger.info("size" + branchnames.size());
		    this.gLogger.info("Outside getTreasuryForDDO");
		    inputMap.put("branchnames", branchnames);
		    this.gLogger.info("AJAXXXXX");
		    String ajaxResult = null;

		    ajaxResult = new AjaxXmlBuilder().addItems(branchnames, "desc", "id").toString();
		    this.gLogger.info("Ajax result:" + ajaxResult);
		    inputMap.put("ajaxKey", ajaxResult);
		    resObj.setResultCode(0);
		    resObj.setResultValue(inputMap);
		    resObj.setViewName("ajaxData");

		    return resObj;
		  }
	  
	  public ResultObject getNameForAutoComplete(Map<String, Object> inputMap)
	    throws Exception
	  {
	    ResultObject objRes = new ResultObject(0, "FAIL");

	    List finalList = new ArrayList();

	    List finalListFromname = new ArrayList();

	    String lStrName = null;
	    String bankId = null;
	    String branchId = null;
	    String locId = null;
	    try
	    {
	      setSessionInfo(inputMap);
	      PensionerReportDao lObjViewReportDAO = new PensionerReportDaoImpl(TrnDcpsContribution.class, this.serv.getSessionFactory());

	      lStrName = StringUtility.getParameter("pension", this.request).toString().trim();
	      bankId = StringUtility.getParameter("bankId", this.request).toString().trim();
	      branchId = StringUtility.getParameter("branchId", this.request).toString().trim();
	      locId = StringUtility.getParameter("cmbTreasuryCode", this.request).toString().trim();
	      Map loginDetailsMap = (Map)inputMap.get("baseLoginMap");

	      finalList = lObjViewReportDAO.getNameForAutoComplete(lStrName.toUpperCase(), bankId, branchId, locId);

	      this.gLogger.info("finalList size is **********" + finalList.size());

	      String lStrTempResult = null;
	      if ((finalList != null) && (finalList.size() > 0))
	        lStrTempResult = new AjaxXmlBuilder().addItems(finalList, "desc", "id",true).toString();

	      this.gLogger.info("Result------------------------" + lStrTempResult);
	      inputMap.put("ajaxKey", lStrTempResult);
	      objRes.setResultValue(inputMap);
	      objRes.setViewName("ajaxData");
	    }
	    catch (Exception ex) {
	      objRes.setResultValue(null);
	      objRes.setThrowable(ex);
	      objRes.setResultCode(-1);
	      objRes.setViewName("errorPage");
	      this.gLogger.error("Error is: " + ex.getMessage());
	      return objRes;
	    }

	    return objRes;
	  }
	  
	  public ResultObject checkAccNo(Map<String, Object> inputMap)
	    throws Exception
	  {
	    setSessionInfo(inputMap);

	    ResultObject objRes = new ResultObject(0, "FAIL");

	    String ppoNo = StringUtility.getParameter("ppoNo", this.request).toString().trim();
	    String bankCode = StringUtility.getParameter("bnkCode", this.request).toString().trim();
	    String branchCode = StringUtility.getParameter("brnCode", this.request).toString().trim();
	    String accNo1 = StringUtility.getParameter("accNo", this.request).toString().trim();
	    String accNo = accNo1.replaceFirst("^0+(?!$)", "");
	    this.gLogger.info("without zero*******" + accNo);
	    this.gLogger.info("with zero*******" + accNo1);
	    String lStrFlag = "N";
	    PensionerReportDao lObjViewReportDAO = new PensionerReportDaoImpl(TrnDcpsContribution.class, this.serv.getSessionFactory());

	    List finalList = lObjViewReportDAO.checkAccNo(ppoNo, bankCode, branchCode);
	    this.gLogger.info("service return result---- " + finalList);
	    if ((!(finalList.isEmpty())) && (finalList.get(0).toString().endsWith(accNo))) {
	      lStrFlag = "Y";
	    }

	    StringBuilder lStrBldXML = new StringBuilder();
	    lStrBldXML.append("<FLAG>");
	    lStrBldXML.append(lStrFlag);
	    lStrBldXML.append("</FLAG>");

	    String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
	    inputMap.put("ajaxKey", lStrResult);
	    objRes.setViewName("ajaxData");
	    objRes.setResultValue(inputMap);

	    return objRes;
	  }
	  

}
