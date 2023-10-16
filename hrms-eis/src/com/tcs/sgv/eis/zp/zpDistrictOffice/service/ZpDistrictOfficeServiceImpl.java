package com.tcs.sgv.eis.zp.zpDistrictOffice.service;

import com.tcs.sgv.common.dao.CmnDistrictMstDAO;
import com.tcs.sgv.common.dao.CmnDistrictMstDAOImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDistrictOffice.dao.ZpDistrictOfficeDAO;
import com.tcs.sgv.eis.zp.zpDistrictOffice.dao.ZpDistrictOfficeDAOImpl;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class ZpDistrictOfficeServiceImpl extends ServiceImpl
  implements ZpDistrictOfficeService
{
  private static Logger logger = Logger.getLogger(ZpDistrictOfficeServiceImpl.class);
  private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/eis/eisLables_en_US");
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
  private final Date todayDate = new Date();
  private Long POST_ID = null;
  private Long USER_ID = null;
  private Long LANG_ID = null;
  private String LOC_ID = "";

  public ResultObject saveZpDistrictOfficeDtls(Map objectArgs)
    throws Exception
  {
    ZpDistrictOfficeDAO zpDistrictOfficeMstDAOImpl;
    logger.info("Entering into saveZpDistrictOfficeDtls of saveZpDistrictOfficeDtls");
    ResultObject objRes = new ResultObject(-1);
    HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
    ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
    Map loginMap = (Map)objectArgs.get("baseLoginMap");

    ZpDistrictOfficeMst zpDistrictOfficeMstVO = new ZpDistrictOfficeMst();

    String selDistrictname = (!(StringUtility.getParameter("selDistrictname", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("selDistrictname", request) : "";
    String selAdminOffice =((!(StringUtility.getParameter("selAdminOffice", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("selAdminOffice", request) : "0");
    String txtdistrictOfficename = (!(StringUtility.getParameter("txtdistrictOfficename", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("txtdistrictOfficename", request) : "";

    logger.info("strTxtAdminOfficeName:::" + selDistrictname);
    logger.info("strTxtAdminOfficeName:::" + selAdminOffice);
    logger.info("strTxtAdminOfficeName:::" + txtdistrictOfficename);

    Long distId = Long.valueOf((!(StringUtility.getParameter("distId", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("distId", request) : "0");
    String editFlag = (!(StringUtility.getParameter("edit", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("edit", request) : "";
    logger.info("distId::" + distId);
    logger.info("editFlag::" + editFlag);

    String updateFlag = (String)objectArgs.get("updateFlag");
    Map redirectMap = new HashMap();
    redirectMap.put("actionFlag", "getDisplayMessage");

    if ((editFlag != null) && (editFlag.equals("Y")))
    {
      logger.info("in edit mode::::::");
      zpDistrictOfficeMstDAOImpl = new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class, serviceLocator.getSessionFactory());
      ZpDistrictOfficeDAO DstrictOfficelst = new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class, serviceLocator.getSessionFactory());
      ZpDistrictOfficeMst zpDistOfficeedit = DstrictOfficelst.getDistOfficeDtls(distId.longValue());

      zpDistOfficeedit.setDistCode(selDistrictname);
      zpDistOfficeedit.setAdminOfficeCode(selAdminOffice);
      zpDistOfficeedit.setDistMstOfficeName(txtdistrictOfficename);
      zpDistOfficeedit.setUpdatedBy(Long.valueOf(1L));
      zpDistOfficeedit.setUpdatedByPost(Long.valueOf(1L));
      zpDistOfficeedit.setUpdatedDate(new Date());
      zpDistrictOfficeMstDAOImpl.update(zpDistOfficeedit);
      redirectMap.put("MESSAGECODE", Long.valueOf(300006L));
    }
    else
    {
      zpDistrictOfficeMstDAOImpl = new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class, serviceLocator.getSessionFactory());
      ZpDistrictOfficeDAO adminofficelstPK = new ZpDistrictOfficeDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
      List zpadminOfficepk = null;
      long adminpk = 0L;
      zpadminOfficepk = adminofficelstPK.getAdminOfficePK(selAdminOffice);
      logger.info("zpadminOfficepk::" + zpadminOfficepk.size());
      adminpk = ((ZpAdminOfficeMst)zpadminOfficepk.get(0)).getOfcId();
      logger.info("adminpk:::" + adminpk);
      Long lLngInwardPensionId = IFMSCommonServiceImpl.getNextSeqNum("ZP_ADMIN_OFFICE_DISTRICT_MPG", objectArgs);

      zpDistrictOfficeMstVO.setDistId(lLngInwardPensionId.longValue());
      zpDistrictOfficeMstVO.setOfcId(adminpk);
      zpDistrictOfficeMstVO.setDistCode(selDistrictname);
      zpDistrictOfficeMstVO.setAdminOfficeCode(selAdminOffice);
      zpDistrictOfficeMstVO.setDistMstOfficeName(txtdistrictOfficename);
      zpDistrictOfficeMstVO.setLangId(Long.valueOf(1L));
      zpDistrictOfficeMstVO.setCreatedDate(this.todayDate);
      zpDistrictOfficeMstVO.setCreatedBy(Long.valueOf(1L));
      zpDistrictOfficeMstDAOImpl.create(zpDistrictOfficeMstVO);
      redirectMap.put("MESSAGECODE", Long.valueOf(56L));
    }

    logger.info("Exit from saveZpDistrictOfficeDtls of saveZpDistrictOfficeDtls");
    objectArgs.put("redirectMap", redirectMap);
    objRes.setResultCode(0);
    objRes.setResultValue(objectArgs);
    objRes.setViewName("redirect view");
    return objRes;
  }

  public ResultObject loadDistrictScreen(Map objectArgs)
    throws Exception
  {
    logger.info("Entering into loadData of ZpDistrictOfficeServiceImpl,by ketan");
    HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
    ResultObject objRes = new ResultObject(-1);
    Long langId = Long.valueOf(1L);
    ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
    Map loginMap = (Map)objectArgs.get("baseLoginMap");
    langId = StringUtility.convertToLong(loginMap.get("langId").toString());
    CmnDistrictMstDAO districtDao = new CmnDistrictMstDAOImpl(CmnDistrictMst.class, serviceLocator.getSessionFactory());
    ZpDistrictOfficeDAO adminofficelst = new ZpDistrictOfficeDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
    ZpDistrictOfficeDAO DstrictOfficelst = new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class, serviceLocator.getSessionFactory());

    List cmnDistrictMstList = null;
    List zpadminOffice = null;
    Long distId = Long.valueOf((!(StringUtility.getParameter("distId", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("distId", request) : "0");
    String editFlag = (!(StringUtility.getParameter("edit", request).equalsIgnoreCase(""))) ? StringUtility.getParameter("edit", request) : "";
    logger.info("distId::" + distId);
    logger.info("editFlag::" + editFlag);
    Long stateId = Long.valueOf(15L);
    ZpDistrictOfficeMst zpDistOfficeedit = DstrictOfficelst.getDistOfficeDtls(distId.longValue());

    cmnDistrictMstList = DstrictOfficelst.getDistrict(stateId.longValue());
    logger.info("cmnDistrictMstList::::" + cmnDistrictMstList.size());
    zpadminOffice = adminofficelst.getAdminOffice();

    objectArgs.put("zpadminOffice", zpadminOffice);
    objectArgs.put("zpadminOfficeedit", zpDistOfficeedit);
    objectArgs.put("cmnDistrictMstList", cmnDistrictMstList);
    objectArgs.put("distId", distId);
    objectArgs.put("editFlag", editFlag);
    objRes.setResultValue(objectArgs);
    objRes.setResultCode(0);
    objRes.setViewName("DistrictOffice");
    return objRes;
  }

  public ResultObject getDistrictOfficeDtls(Map objectArgs)
  {
    logger.info("getDistrictOfficeDtls IN District Office Data , by ketan..");
    ResultObject resultObject = new ResultObject(0);

    Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
    try
    {
      ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
      ZpDistrictOfficeDAO zpdistrictOfficeDAO = new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class, serv.getSessionFactory());
      long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString()).longValue();

      List zpdistrictOfficelst = zpdistrictOfficeDAO.getAllDistrictOfficeDtlsData(langId);
      logger.info("zpdistrictOfficelst::" + zpdistrictOfficelst);
      Map map = new HashMap();
      map = objectArgs;
      map.put("zpdistrictOfficelst", zpdistrictOfficelst);
      resultObject.setResultCode(0);
      resultObject.setResultValue(map);
      resultObject.setViewName("DistrictOfficeView");
    }
    catch (Exception e)
    {
      logger.info("Null Pointer Exception Ocuures...insertDistrictMPGDtls");
      logger.error("Error is: " + e.getMessage());
      objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
      resultObject.setResultValue(objectArgs);
      resultObject.setViewName("errorInsert");
    }

    return resultObject;
  }
  //START for no similar dist office
  public ResultObject validateDistrictOffice(Map objectArgs)
	{
		logger.info("inside validateDistrictOffice");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		String distCode=null;
		String adminOfficeCode=null;
		Long distOfficeCount=null;
		String lStrResult = null;
		try {
			
			if(StringUtility.getParameter("distCode", request)!=null && !StringUtility.getParameter("distCode", request).equals(""))
			distCode=StringUtility.getParameter("distCode", request).trim();
			logger.info("--------distCode--------:"+distCode);
			if(StringUtility.getParameter("adminOfficeCode", request)!=null && !StringUtility.getParameter("adminOfficeCode", request).equals(""))
				adminOfficeCode=StringUtility.getParameter("adminOfficeCode", request).trim();
			logger.info("--------dcpsEmpId--------:"+adminOfficeCode);
			
			ZpDistrictOfficeDAO zpdistrictOfficeDAO = new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class, serv.getSessionFactory());
			
			
			distOfficeCount=zpdistrictOfficeDAO.checkDistOfficeCount(distCode,adminOfficeCode);
			
			String status=null;
			if(distOfficeCount>0){
				status="wrong";
			}

			else{
				status="correct";
			}
			String lSBStatus = getResponseJSONDocForDistOffice(status).toString();
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			
			objectArgs.put("ajaxKey", lStrResult);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
			
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}
  private StringBuilder getResponseJSONDocForDistOffice(String status) throws JSONException {

		StringBuilder lStrBldXML = new StringBuilder();
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(status);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");
		logger.info("############### json for district office object: "+lStrBldXML);

		return lStrBldXML;
	}
  //END for no similar dist office
}