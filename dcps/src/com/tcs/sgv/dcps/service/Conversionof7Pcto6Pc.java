package com.tcs.sgv.dcps.service;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.AnytoAnyPcDAOImpl;
import com.tcs.sgv.dcps.dao.AnytoanyPcDAO;
import com.tcs.sgv.dcps.dao.LedgerReportDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;








public class Conversionof7Pcto6Pc
  extends ServiceImpl
{
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
  
  public Conversionof7Pcto6Pc() {}
  
  private void setSessionInfo(Map inputMap) {
    try { request = ((HttpServletRequest)inputMap.get("requestObj"));
      serv = ((ServiceLocator)inputMap.get("serviceLocator"));
      session = request.getSession();
      gStrPostId = SessionHelper.getPostId(inputMap).toString();
      gLngPostId = SessionHelper.getPostId(inputMap);
      gStrLocationCode = SessionHelper.getLocationCode(inputMap);
      gLngUserId = SessionHelper.getUserId(inputMap);
      gStrUserId = gLngUserId.toString();
      gLngDBId = SessionHelper.getDbId(inputMap);
      gDtCurDate = SessionHelper.getCurDate();
    } catch (Exception e) {
      gLogger.error(" Error is : " + e, e);
    }
  }
  
  public ResultObject loadEmpDetails(Map objectArgs)
    throws Exception
  {
    gLogger.info("inside validateAndPopulateastDDO");
    ResultObject objRes = new ResultObject(0, "FAIL");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
    Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
    
    long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString()).longValue();
    String empUserName = null;
    String empName = null;
    String astDDOFlag = null;
    String finalCheckFlag = "No";
    AnytoanyPcDAO anytoanypcDAO = null;
    String finalCheckmsg = "";
    int chkPostAndSerExp = 0;
    String lStrResult = null;
    try {
      setSessionInfo(objectArgs);
      

      DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
      LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
      gLogger.info("gLngPostId: " + gLngPostId);
      String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
      gLogger.info("lStrDdoCode: " + lStrDdoCode);
      anytoanypcDAO = new AnytoAnyPcDAOImpl(null, this.serv.getSessionFactory());
      
      String ddoCode = dao.getDdoCode(locId);
      if ((StringUtility.getParameter("empUserName", request) != null) && (StringUtility.getParameter("empUserName", request) != "")) {
        empUserName = StringUtility.getParameter("empUserName", request).trim();
        gLogger.info("--------empUserName--------:" + empUserName);
        chkPostAndSerExp = dao.chkPostAndSerExp(empUserName, locId, "Yes");
        gLogger.info("chkPostAndSerExp1:" + chkPostAndSerExp);
        if (chkPostAndSerExp > 0) {
          gLogger.info("chkPostAndSer   111 :" + chkPostAndSerExp);
          finalCheckFlag = anytoanypcDAO.getEmpDetails(empUserName, lStrDdoCode);
          if (finalCheckFlag.equals("No"))
          {
            finalCheckmsg = "6PC conversion is applicable for only 7PC State Employee ";
          }
        }
        else
        {
          gLogger.info("chkPostAndSerExp   22222:" + chkPostAndSerExp);
          finalCheckmsg = "Kindly check the post and service Expire date of the Employee ";
        }
      }
      
      String lSBStatus = getResponseXMLDocForValidateAstDDO(finalCheckmsg).toString();
      lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
      
      objectArgs.put("ajaxKey", lStrResult);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("ajaxData");
    }
    catch (Exception e) {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(-1);
      objRes.setViewName("errorPage");
    }
    return objRes;
  }
  
  private Object getResponseXMLDocForValidateAstDDO(String status) { StringBuilder lStrBldXML = new StringBuilder();
    
    lStrBldXML.append("<XMLDOC>");
    lStrBldXML.append("<Flag>");
    lStrBldXML.append(status);
    lStrBldXML.append("</Flag>");
    lStrBldXML.append("</XMLDOC>");
    
    return lStrBldXML;
  }
  
  public ResultObject save6PCDetails(Map inputMap) throws Exception
  {
    gLogger.info("executing in save6PCDetails");
    ResultObject resObj = new ResultObject(0, "FAIL");
    HttpServletRequest request = (HttpServletRequest)inputMap.get("requestObj");
    ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
    AnytoanyPcDAO anytoanypcDAO = null;
    
    String Flag = "Fail";
    SimpleDateFormat lObjSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    try
    {
      setSessionInfo(inputMap);
      anytoanypcDAO = new AnytoAnyPcDAOImpl(null, serv.getSessionFactory());
      String lStrSevaarthId = StringUtility.getParameter("SevarthId", request);
      long empid = anytoanypcDAO.getEmpId(lStrSevaarthId);
      if (lStrSevaarthId != null)
      {
        anytoanypcDAO.savePCDetails(lStrSevaarthId, empid);
      }
      
      Flag = "Success";
      
      StringBuilder lStrBldXML = new StringBuilder();
      lStrBldXML.append("<XMLDOC>");
      lStrBldXML.append("<Success>");
      
      lStrBldXML.append("<![CDATA[");
      lStrBldXML.append(Flag);
      lStrBldXML.append("]]>");
      
      lStrBldXML.append("</Success>");
      lStrBldXML.append("</XMLDOC>");
      String lStrTempResult = null;
      lStrTempResult = new AjaxXmlBuilder().addItem("ajax_Key", lStrBldXML.toString()).toString();
      
      inputMap.put("ajaxKey", lStrTempResult);
      gLogger.info("saved.." + lStrTempResult);
      resObj.setResultCode(0);
      resObj.setResultValue(inputMap);
      resObj.setViewName("ajaxData");
    }
    catch (Exception ex)
    {
      resObj.setResultValue(null);
      resObj.setThrowable(ex);
      resObj.setResultCode(-1);
      resObj.setViewName("errorPage");
      
      gLogger.error(" Error is : " + ex, ex);
      return resObj;
    }
    return resObj;
  }
}