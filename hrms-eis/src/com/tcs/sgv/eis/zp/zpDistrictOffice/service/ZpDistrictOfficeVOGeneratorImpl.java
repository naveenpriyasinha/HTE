package com.tcs.sgv.eis.zp.zpDistrictOffice.service;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.displaytable.PaginatedListImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class ZpDistrictOfficeVOGeneratorImpl extends ServiceImpl
  implements ZpDistrictOfficeVOGenerator
{
  private static final Logger logger = Logger.getLogger(ZpDistrictOfficeVOGeneratorImpl.class);
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
  private final Date todayDate = new Date();
  private Long POST_ID = null;
  private Long USER_ID = null;
  private Long LANG_ID = null;
  private String LOC_ID = "";

  public ResultObject generateMap(Map objectArgs)
    throws Exception
  {
    ResultObject objRes = new ResultObject(-1);
    HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
    ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");

    logger.info("Entered into generateMap of ZpAdminOfficeVOGeneratorImpl");

    String selDistrictname = (!(StringUtility.getParameter("selDistrictname", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("selDistrictname", request) : "";

    Long selAdminOffice = Long.valueOf((!(StringUtility.getParameter("selAdminOffice", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("selAdminOffice", request) : "0");
    String strTxtAdminOfficeName = (!(StringUtility.getParameter("txtdistrictOfficename", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("txtdistrictOfficename", request) : "";

    logger.info("strTxtAdminOfficeName:" + selDistrictname);
    logger.info("strTxtAdminOfficeName:" + selAdminOffice);
    logger.info("strTxtAdminOfficeName:" + strTxtAdminOfficeName);

    objectArgs.put("selDistrictname", selDistrictname);
    objectArgs.put("selAdminOffice", selAdminOffice);
    objectArgs.put("strTxtAdminOfficeName", strTxtAdminOfficeName);

    strTxtAdminOfficeName = null;

    validateZpAdminOfficeDetail(objectArgs);
    logger.info("Exit from generateMap of ZpAdminOfficeVOGeneratorImpl");
    objRes.setResultCode(0);
    objRes.setResultValue(objectArgs);
    return objRes;
  }

  public ResultObject retrieveZpAdminOfficeDtls(Map objectArgs)
    throws Exception
  {
    ResultObject objRes = new ResultObject(-1);
    HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
    ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");

    logger.info("Entered into retrieve of ZpAdminOfficeVOGeneratorImpl");
    ZpDistrictOfficeMst zpAdminOfficeMstVO = new ZpDistrictOfficeMst();

    String strTxtAdminOfficeName = (!(StringUtility.getParameter("txtAdminOfficeName", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("txtAdminOfficeName", request) : "";
    Long lTxtCode = Long.valueOf((!(StringUtility.getParameter("txtCode", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("txtCode", request) : "0");
    Long zpAdminOfficeMstID = Long.valueOf((!(StringUtility.getParameter("hdnOfcId", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("hdnOfcId", request) : "0");

    objectArgs.put("zpAdminOfficeMstVO", zpAdminOfficeMstVO);

    PaginatedListImpl paginateList = new PaginatedListImpl();
    paginateList.setRequest(request);
    String page = (request.getParameter("page") != null) ? request.getParameter("page") : "1";
    paginateList = paginateList.getPaginatedListObject(15, page);
    objectArgs.put("paginateList", paginateList);

    strTxtAdminOfficeName = null;
    lTxtCode = null;
    logger.info("Exit from retrieve of ZpAdminOfficeVOGeneratorImpl");
    objRes.setResultCode(0);
    objRes.setResultValue(objectArgs);
    return objRes;
  }

  public ResultObject validateZpAdminOfficeDetail(Map objectArgs)
    throws Exception
  {
    ResultObject objRes = new ResultObject(-1);
    logger.info("Entered into validate of ZpAdminOfficeVOGENImpl");
    logger.info("Exit from validate of ZpAdminOfficeVOGENImpl");
    objRes.setResultCode(0);
    objRes.setResultValue(objectArgs);
    return objRes; }

  public ResultObject loadUpdateData(Map objectArgs) throws Exception {
    logger.info("ZpAdminOfficeVOGeneratorImpl : loadUpdatedata Called");
    ResultObject retObj = new ResultObject(-1);
    HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
    Long zpAdminOfficeMstPkID = Long.valueOf((!(StringUtility.getParameter("zpAdminOfficeMstPkID", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("zpAdminOfficeMstPkID", request) : "0");
    logger.info("zpAdminOfficeMstPkID" + zpAdminOfficeMstPkID);
    objectArgs.put("zpAdminOfficeMstPkID", zpAdminOfficeMstPkID);
    retObj.setResultCode(0);
    retObj.setResultValue(objectArgs);
    logger.info("ZpAdminOfficeVOGeneratorImpl : loadUpdatedata End");
    return retObj;
  }
}