/**
 * 
 */
package com.tcs.sgv.eis.zp.ReportingDDO.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.ibm.db2.jcc.t4.ob;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.TokenNumberCustomVO;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.dao.CmnDistrictMstDAO;
import com.tcs.sgv.common.dao.CmnDistrictMstDAOImpl;
import com.tcs.sgv.common.dao.CmnTalukaMstDAO;
import com.tcs.sgv.common.dao.CmnTalukaMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.CmnTalukaMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.AddNewDDOConfigDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ConsolidatedBillLvlMpgDaoImpl;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ConsolidatedBillMpgDaoImpl;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ConsolidatedBillMstDao;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ConsolidatedBillMstDaoImpl;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ReportingDDODao;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ReportingDDODaoImpl;
import com.tcs.sgv.eis.zp.ReportingDDO.valueobject.ConsolidatedBillLvlMpgMst;
import com.tcs.sgv.eis.zp.ReportingDDO.valueobject.ConsolidatedBillMpg;
import com.tcs.sgv.eis.zp.ReportingDDO.valueobject.ConsolidatedBillMst;
import com.tcs.sgv.eis.zp.ZpReportingDDO.dao.ZpReportingDAO;
import com.tcs.sgv.eis.zp.ZpReportingDDO.dao.ZpReportingDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeMstDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDdoOffice.dao.ZpDDOOfficeMstDAOImpl;
import com.tcs.sgv.eis.zp.zpDdoOffice.service.ZpDDOOfficeService;
import com.tcs.sgv.eis.zp.zpDdoOffice.service.ZpDDOOfficeServiceImpl;
import com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst;

/**
 * @author 381609
 *
 */

public class ReportingDDOservice extends ServiceImpl
{
	private static Logger logger = Logger.getLogger(ReportingDDOservice.class);
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/eis/zp/zpAdminOffice/ZpAdminOfficeLabels_en_US");

	public ResultObject defineReportingDDO(Map objectArgs) throws Exception
	{

		//logger.info("Entering into defineReportingDDO of ReportingDDOservice");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Long langId = 1l;

		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try
		{
			ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
			//CmnDistrictMstDAO districtDao = new CmnDistrictMstDAOImpl(CmnDistrictMst.class,serviceLocator.getSessionFactory());
			ReportingDDODao treasuryDao = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
			List cmntreasuryMstList = null;
			cmntreasuryMstList = treasuryDao.getTreasury();
			logger.info("cmntreasuryMstList:::" + cmntreasuryMstList.size());
			/*ReportingDDODao districtDao=new ReportingDDODaoImpl(CmnDistrictMst.class,serviceLocator.getSessionFactory());
			List cmnDistrictMstList = null;
			String[] distStr={"STATE_ID","LANG_ID","ACTIVATE_FLAG"};
			Object[] distVal={new Long(15L),new Long(1L),new Integer(1)};
			Long state=15L;
			cmnDistrictMstList = districtDao.getDistrict(state);
			//cmnDistrictMstList = districtDao.getListByColumnAndValue(distStr, distVal);
			logger.info("cmnDistrictMstList:::"+cmnDistrictMstList.size());*/
			ZpAdminOfficeMstDAOImpl adminOfficeMst = new ZpAdminOfficeMstDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			List OfcDtls = adminOfficeMst.retriveOfcList("");
			List lLstAdminDept = null;
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serviceLocator.getSessionFactory());
			ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");
			long gLngLangId = 1L;
			lLstAdminDept = lObjDcpsCommonDAO.getAllDepartment(Long.parseLong(gObjRsrcBndle.getString("DCPS.DEPARTMENTID")), gLngLangId);
			objectArgs.put("AdminDept", lLstAdminDept);
			logger.info("defineReportingDDO  OfcDtls:::" + OfcDtls.size());
			objectArgs.put("locId", locId);
			objectArgs.put("ZpAdminOfficeMstList", OfcDtls);
			//objectArgs.put("cmnDistrictMstList", cmnDistrictMstList);
			objectArgs.put("cmntreasuryMstList", cmntreasuryMstList);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
			resObj.setViewName("viewReportingDDO");
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
			resObj.setResultCode(-1);
			resObj.setThrowable(e);
			resObj.setViewName("errorPage");

		}
		return resObj;

	}

	public ResultObject loadTaluka2(Map objectArgs) throws Exception
	{
		//logger.info("Entering into getTaluka of ReportingDDOservice");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Long distId = Long.valueOf(StringUtility.getParameter("selDistrictname", request));
		//CmnTalukaMstDAO talukaDao = new CmnTalukaMstDAOImpl(CmnTalukaMst.class,serviceLocator.getSessionFactory());
		ReportingDDODao talukaDao = new ReportingDDODaoImpl(CmnTalukaMst.class, serviceLocator.getSessionFactory());
		List<CmnTalukaMst> cmntalukaMstList = null;
		String[] talukaStr = { "DISTRICT_ID", "LANG_ID", "ACTIVATE_FLAG" };
		Object[] talukaVal = { new Long(distId), new Long(1L), new Integer(1) };
		cmntalukaMstList = talukaDao.getTaluka(distId);
		//cmntalukaMstList = talukaDao.getListByColumnAndValue(talukaStr, talukaVal);
		logger.info("cmntalukaMstList:::" + cmntalukaMstList.size());
		objectArgs.put("cmntalukaMstList", cmntalukaMstList);
		List<ComboValuesVO> lLstAllDept = new ArrayList<ComboValuesVO>();
		Map result = new HashMap();
		ComboValuesVO cmbVO = new ComboValuesVO();
		cmbVO.setId("-1");
		cmbVO.setDesc("Select");
		lLstAllDept.add(cmbVO);

		if (cmntalukaMstList != null && cmntalukaMstList.size() > 0)
		{
			Iterator IT = cmntalukaMstList.iterator();

			cmbVO = new ComboValuesVO();
			Object[] lObj = null;
			while (IT.hasNext())
			{
				cmbVO = new ComboValuesVO();
				lObj = (Object[]) IT.next();
				cmbVO.setId(lObj[0].toString());
				cmbVO.setDesc(lObj[1].toString());
				lLstAllDept.add(cmbVO);
			}
		}

		String AjaxResult = new AjaxXmlBuilder().addItems(lLstAllDept, "desc", "id").toString();
		result.put("ajaxKey", AjaxResult);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(result);
		objRes.setViewName("ajaxData");
		return objRes;
	}

	public ResultObject loadSubTreasuryOffice(Map objectArgs) throws Exception
	{
		//logger.info("Entering into getTaluka of ReportingDDOservice");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Long treasuryId = Long.valueOf(StringUtility.getParameter("treasuryId", request));

		ReportingDDODao treasuryDao = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		List cmnsubtreasuryMstList = null;
		List treasuryName = treasuryDao.getTreasuryNamefromTreasuryId(treasuryId);
		logger.info("treasuryName:::" + treasuryName.size());

		cmnsubtreasuryMstList = treasuryDao.getSubTreasury(treasuryId);
		logger.info("cmntreasuryMstList:::" + cmnsubtreasuryMstList.size());
		//CmnTalukaMstDAO talukaDao = new CmnTalukaMstDAOImpl(CmnTalukaMst.class,serviceLocator.getSessionFactory());
		/*ReportingDDODao talukaDao=new ReportingDDODaoImpl(CmnTalukaMst.class,serviceLocator.getSessionFactory());
		List<CmnTalukaMst> cmntalukaMstList = null;
		String[] talukaStr={"DISTRICT_ID","LANG_ID","ACTIVATE_FLAG"};
		Object[] talukaVal={new Long(distId),new Long(1L),new Integer(1)};
		cmntalukaMstList = talukaDao. getTaluka(distId);
		//cmntalukaMstList = talukaDao.getListByColumnAndValue(talukaStr, talukaVal);
		logger.info("cmntalukaMstList:::"+cmntalukaMstList.size());
		objectArgs.put("cmntalukaMstList", cmntalukaMstList);*/

		List<ComboValuesVO> lLstAllDept = new ArrayList<ComboValuesVO>();
		Map result = new HashMap();
		ComboValuesVO cmbVO = new ComboValuesVO();
		cmbVO.setId("-1");
		cmbVO.setDesc("Select");
		lLstAllDept.add(cmbVO);

		if (treasuryName != null && treasuryName.size() > 0)	
		{		
			Iterator ITTreasury = treasuryName.iterator();

			cmbVO = new ComboValuesVO();
			Object[] lObjTreasury = null;
			while(ITTreasury.hasNext())
			{
				cmbVO = new ComboValuesVO();
				lObjTreasury = (Object[]) ITTreasury.next();
				cmbVO.setId(lObjTreasury[0].toString());
				cmbVO.setDesc(lObjTreasury[1].toString());
				lLstAllDept.add(cmbVO);
			}

			{

			}
		}


		if (cmnsubtreasuryMstList != null && cmnsubtreasuryMstList.size() > 0)
		{
			Iterator IT = cmnsubtreasuryMstList.iterator();

			cmbVO = new ComboValuesVO();
			Object[] lObj = null;
			while (IT.hasNext())
			{
				cmbVO = new ComboValuesVO();
				lObj = (Object[]) IT.next();
				cmbVO.setId(lObj[0].toString());
				cmbVO.setDesc(lObj[1].toString());
				lLstAllDept.add(cmbVO);
			}
		}

		String AjaxResult = new AjaxXmlBuilder().addItems(lLstAllDept, "desc", "id").toString();
		result.put("ajaxKey", AjaxResult);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(result);
		objRes.setViewName("ajaxData");
		return objRes;
	}

	/*public ResultObject generateReportingDDOCode(Map objectArgs) throws Exception
	{

		logger.info("Entering into defineReportingDDO of ReportingDDOservice");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Long langId = 1l;

		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try
		{
			ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
			resObj.setResultCode(ErrorConstants.SUCCESS);
			ZpDDOOfficeMstDAOImpl check = new ZpDDOOfficeMstDAOImpl(null, serviceLocator.getSessionFactory());
			String adminOffice = StringUtility.getParameter("adminOffice", request).trim();
			if (adminOffice.length() == 1)
				adminOffice = "0" + adminOffice;
			String tSubTCode = StringUtility.getParameter("tSubTCode", request);
			String CreatedDDOCode = adminOffice + tSubTCode.trim();

			//String CreatedDDOCode=adminOffice.trim().charAt(adminOffice.trim().length() - 1)+tSubTCode.trim();
			String newDDOCode = "";
			List getCountCode = check.getCountofDDOCode(CreatedDDOCode);
			String FinalpreFixed= "";
			String suffix="";
			String midfix="";
			if(getCountCode.get(0) != null)
			{
				Long temp=Long.parseLong(getCountCode.get(0).toString())+1;
				suffix=temp.toString();
			}
			if(suffix.length()==1)
				midfix="000";
			else if(suffix.length()==2)
				midfix="00";
			else if(suffix.length()==3)
				midfix="0";

			FinalpreFixed=CreatedDDOCode+midfix+suffix;
			String lSBStatus = getResponseXMLDocSaveData(FinalpreFixed).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			logger.info("********************************************" + lStrResult);

			objectArgs.put("ajaxKey", lStrResult);
			resObj.setResultValue(objectArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setViewName("ajaxData");

		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
			resObj.setResultCode(-1);
			resObj.setThrowable(e);
			resObj.setViewName("errorPage");

		}
		return resObj;

	}*/

	private StringBuilder getResponseXMLDocSaveData(String CreatedDDOCode)
	{

		StringBuilder lStrBldXML = new StringBuilder();
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<CreatedDDOCode>");
		lStrBldXML.append(CreatedDDOCode);
		lStrBldXML.append("</CreatedDDOCode>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject saveReportingDDOCode(Map objectArgs) throws Exception
	{

		//logger.info("Entering into saveReportingDDOCode of ReportingDDOservice");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Long gLngPostId = SessionHelper.getPostId(objectArgs);
		Long gLngUserId = SessionHelper.getUserId(objectArgs);
		String strAdminOfc = StringUtility.getParameter("cmbAdminDept", request).trim();
		String lLngFieldDept = StringUtility.getParameter("cmbFieldDept", request).trim(); // TODO -- It will Change in future
		String lStrDdoCode = StringUtility.getParameter("txtDDOCode", request).trim();
		String tCode = StringUtility.getParameter("tCode", request).trim();
		String subTCode = StringUtility.getParameter("subTCode", request).trim();
		String ddoType =StringUtility.getParameter("cmbAdminOffice", request);
		String ddoCode=StringUtility.getParameter("cmbDdoCode", request);
		String tSubTCode = (subTCode.equals("-1")) ? tCode : subTCode;
		Long lLngDesignID = 1l;
		logger.info(".............................lStrDdoCode............................." + lStrDdoCode);
		Long lLngLocPin = 1l;//Long.parseLong(StringUtility.getParameter("1", request).trim());// TODO -- Need Change Temporary "1" is added.  
		String lLngDistrictCode = StringUtility.getParameter("cmbDistOffice", request).trim();
		//String strTOName=StringUtility.getParameter("txtTreasuryName", request).trim();
		String lLngTreasuryCode = tCode;
		//String strSubTOName=StringUtility.getParameter("txtSubTreasuryName", request).trim();

		String lStrDdoOfficeName = StringUtility.getParameter("txtOfficeName", request).trim();
		String lStrDdoPersonalName = StringUtility.getParameter("txtDDOName", request).trim();

		String lStrGender = "M";
		String lStrDesgnName = "1";
		String lStrDdoName = lStrDdoPersonalName;
		/*
		AddNewDDOConfigDAOImpl lObjAddNewDdoConfig = new AddNewDDOConfigDAOImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		logger.info("..............................Entries Start in Below tables............................");
		logger.info(".........01................Inserting insertLocation............................");
		String lStrLocCode = lObjAddNewDdoConfig.insertLocation(lStrDdoOfficeName, gLngUserId, gLngPostId, Long.valueOf(lLngFieldDept), String.valueOf(lLngLocPin), objectArgs);
		logger.info(".........01................Inserted insertLocation............................");
		logger.info(".........02................Inserting insertUserMst............................");
		Long lLngUserId = lObjAddNewDdoConfig.insertUserMst(lStrDdoCode, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........02................Inserted insertUserMst............................");
		Long lLngPostId = new Long(lLngUserId);
		logger.info(".........03................Inserting insertEmpMst............................");
		lObjAddNewDdoConfig.insertEmpMst(lLngUserId, lStrDdoPersonalName, gLngUserId, gLngPostId, lStrGender, objectArgs);
		logger.info(".........03................Inserting insertEmpMst............................");

		logger.info(".........04................Inserting insertOrgPostMst............................");
		lObjAddNewDdoConfig.insertOrgPostMst(lLngPostId, lStrLocCode, gLngUserId, gLngPostId, lLngDesignID.toString(), objectArgs);
		logger.info(".........04................Inserting insertOrgPostMst............................");

		logger.info(".........05................Inserting insertPostDtlsRlt............................");
		lObjAddNewDdoConfig.insertPostDtlsRlt(lStrLocCode, lLngPostId, lStrDesgnName, lLngDesignID, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........05................Inserting insertPostDtlsRlt............................");

		logger.info(".........06................Inserting insertPostRoleRlt............................");
		lObjAddNewDdoConfig.insertPostRoleRlt(lLngPostId, gLngUserId, gLngPostId, objectArgs, "DDO");
		logger.info(".........06................Inserting insertPostRoleRlt............................");

		logger.info(".........07................Inserting insertUserPostRlt............................");
		lObjAddNewDdoConfig.insertUserPostRlt(lLngPostId, lLngUserId, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........07................Inserting insertUserPostRlt............................");

		logger.info(".........08................Inserting insertOrgDdoMst............................" + ddoType);

		ReportingDDODaoImpl rp = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		rp.insertOrgDdoMst(lStrDdoCode, lStrDdoName, lStrDdoPersonalName, lLngPostId, gLngUserId, lStrLocCode, gLngPostId, strAdminOfc, lLngFieldDept, ddoType, objectArgs);
		logger.info(".........08................Inserting insertOrgDdoMst............................");

		logger.info(".........09................Inserting insertMstDcpsDdoOffice............................");
		lObjAddNewDdoConfig.insertMstDcpsDdoOffice(lStrDdoCode, lStrDdoOfficeName, lLngDistrictCode.toString(), Long.parseLong(lStrLocCode), gLngUserId, gLngPostId, objectArgs);
		logger.info(".........09................Inserting insertMstDcpsDdoOffice............................");

		logger.info(".........10................Inserting insertRltDdoOrg............................");
		lObjAddNewDdoConfig.insertRltDdoOrg(gLngUserId, gLngPostId, lStrDdoCode, lLngTreasuryCode.toString(), objectArgs);
		logger.info(".........10................Inserting insertRltDdoOrg............................");
		//ReportingDDODao rdl=new ReportingDDODaoImpl(CmnLocationMst.class,serviceLocator.getSessionFactory());
		 */
		ReportingDDODaoImpl rp = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		//	rp.getSeqTable(lStrLocCode);
		rp.updateDDoMst(ddoCode, ddoType);
		//logger.info(".........10................Inserting seq table...........................");
		rp.insertRepoDDO(ddoCode, Long.parseLong(ddoType), 'Y');
		//logger.info(".........................Entries Done in above tables............................");
		//logger.info(".........................Inserting in wf_org_post_mpg_mst............................");
		/*rp.insertWfOrgpost(lLngPostId);
		logger.info(".........................Inserting in WF_ORG_USR_MPG_MST---............................");
		rp.insertWfOrgUsr(lLngUserId);
		logger.info(".........................Inserting in WF_ORG_LOC_MPG_MST---............................");
		rp.insertWfOrgLoc(lStrLocCode);*/
		List lvl2DDOlist = null;
		lvl2DDOlist = rp.getLvl2DDo();
		logger.info("cmntreasuryMstList:::" + lvl2DDOlist.size());
		objectArgs.put("lvl2DDO", lvl2DDOlist);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("reportingDDOList");
		return objRes;
	}
	/*
	public ResultObject saveReportingDDOCode(Map objectArgs) throws Exception
	{

		logger.info("Entering into saveReportingDDOCode of ReportingDDOservice");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Long gLngPostId = SessionHelper.getPostId(objectArgs);
		Long gLngUserId = SessionHelper.getUserId(objectArgs);
		String strAdminOfc = StringUtility.getParameter("cmbAdminDept", request).trim();
		String lLngFieldDept = StringUtility.getParameter("cmbFieldDept", request).trim(); // TODO -- It will Change in future
		String lStrDdoCode=null;
		logger.info("hi i m hghgfhp") ;
		if((StringUtility.getParameter("cmbDdoCode", request)!=null)&&(StringUtility.getParameter("cmbDdoCode", request)!="")&&(Long.parseLong(StringUtility.getParameter("cmbDdoCode", request))!=-1)){
			lStrDdoCode = StringUtility.getParameter("cmbDdoCode", request).trim();
			logger.info("hi i m not zp"+lStrDdoCode) ;
		}
		else {
			logger.info("hi i m zpfhfdjfhfhj") ;
			lStrDdoCode = StringUtility.getParameter("txtDDOCode", request).trim();
			logger.info("hi i m zp"+lStrDdoCode) ;
		}
		logger.info("hi i m out"+lStrDdoCode) ;	

		String tCode = StringUtility.getParameter("tCode", request).trim();
		String subTCode = StringUtility.getParameter("subTCode", request).trim();
		String ddoType =StringUtility.getParameter("cmbAdminOffice", request);
		String ddoCode=StringUtility.getParameter("cmbDdoCode", request);
		String tSubTCode = (subTCode.equals("-1")) ? tCode : subTCode;
		Long lLngDesignID = 1l;
		logger.info(".............................lStrDdoCode............................." + lStrDdoCode);
		Long lLngLocPin = 1l;//Long.parseLong(StringUtility.getParameter("1", request).trim());// TODO -- Need Change Temporary "1" is added.  
		String lLngDistrictCode = StringUtility.getParameter("cmbDistOffice", request).trim();
		//String strTOName=StringUtility.getParameter("txtTreasuryName", request).trim();
		String lLngTreasuryCode = tCode;
		//String strSubTOName=StringUtility.getParameter("txtSubTreasuryName", request).trim();

		String lStrDdoOfficeName = StringUtility.getParameter("txtOfficeName", request).trim();

		String lStrDdoPersonalName = StringUtility.getParameter("txtDDOName", request).trim();
        AddNewDDOConfigDAOImpl lObjAddNewDdoConfig = new AddNewDDOConfigDAOImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		//added by Kinjal:start
		//String lStrDdoPersonalName = lObjAddNewDdoConfig.getDDOName(lStrDdoCode);
		//String lStrDdoOfficeName = lObjAddNewDdoConfig.getDDODetails(lStrDdoCode);

		String lStrGender = "M";
		String lStrDesgnName = "1";
		String lStrDdoName = lStrDdoPersonalName;


		logger.info("..............................Entries Start in Below tables............................");
		logger.info(".........01................Inserting insertLocation............................");
		String lStrLocCode = lObjAddNewDdoConfig.insertLocation(lStrDdoOfficeName, gLngUserId, gLngPostId, Long.valueOf(lLngFieldDept), String.valueOf(lLngLocPin), objectArgs);
		String lStrLocCode = lObjAddNewDdoConfig.insertLocation(lStrDdoOfficeName, gLngUserId, gLngPostId, Long.valueOf(lLngFieldDept), String.valueOf(lLngLocPin), objectArgs,lLngDistrictCode);

		logger.info(".........01................Inserted insertLocation............................");
		logger.info(".........02................Inserting insertUserMst............................");
		Long lLngUserId = lObjAddNewDdoConfig.insertUserMst(lStrDdoCode, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........02................Inserted insertUserMst............................");
		Long lLngPostId = new Long(lLngUserId);
		logger.info(".........03................Inserting insertEmpMst............................");
		lObjAddNewDdoConfig.insertEmpMst(lLngUserId, lStrDdoPersonalName, gLngUserId, gLngPostId, lStrGender, objectArgs);
		logger.info(".........03................Inserting insertEmpMst............................");

		logger.info(".........04................Inserting insertOrgPostMst............................");
		lObjAddNewDdoConfig.insertOrgPostMst(lLngPostId, lStrLocCode, gLngUserId, gLngPostId, lLngDesignID.toString(), objectArgs);
		logger.info(".........04................Inserting insertOrgPostMst............................");

		logger.info(".........05................Inserting insertPostDtlsRlt............................");
		lObjAddNewDdoConfig.insertPostDtlsRlt(lStrLocCode, lLngPostId, lStrDesgnName, lLngDesignID, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........05................Inserting insertPostDtlsRlt............................");

		logger.info(".........06................Inserting insertPostRoleRlt............................");
		lObjAddNewDdoConfig.insertPostRoleRlt(lLngPostId, gLngUserId, gLngPostId, objectArgs, "DDO");
		logger.info(".........06................Inserting insertPostRoleRlt............................");

		logger.info(".........07................Inserting insertUserPostRlt............................");
		lObjAddNewDdoConfig.insertUserPostRlt(lLngPostId, lLngUserId, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........07................Inserting insertUserPostRlt............................");

		logger.info(".........08................Inserting insertOrgDdoMst............................" + ddoType);

		ReportingDDODaoImpl rp = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		rp.insertOrgDdoMst(lStrDdoCode, lStrDdoName, lStrDdoPersonalName, lLngPostId, gLngUserId, lStrLocCode, gLngPostId, strAdminOfc, lLngFieldDept, Long.getLong(ddoType), objectArgs);
		logger.info(".........08................Inserting insertOrgDdoMst............................");

		logger.info(".........09................Inserting insertMstDcpsDdoOffice............................");
		lObjAddNewDdoConfig.insertMstDcpsDdoOffice(lStrDdoCode, lStrDdoOfficeName, lLngDistrictCode.toString(), Long.parseLong(lStrLocCode), gLngUserId, gLngPostId, objectArgs);
		logger.info(".........09................Inserting insertMstDcpsDdoOffice............................");

		logger.info(".........10................Inserting insertRltDdoOrg............................");
		lObjAddNewDdoConfig.insertRltDdoOrg(gLngUserId, gLngPostId, lStrDdoCode, lLngTreasuryCode.toString(), objectArgs);
		logger.info(".........10................Inserting insertRltDdoOrg............................");
		//ReportingDDODao rdl=new ReportingDDODaoImpl(CmnLocationMst.class,serviceLocator.getSessionFactory());

		rp = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		Long seqId= rp.getNextSeqNum();
		rp.getSeqTable(lStrLocCode,objectArgs,seqId);
		//commented by sunita
		//rp.updateDDoMst(ddoCode, ddoType);
		//logger.info(".........10................Inserting seq table...........................");
		//rp.insertRepoDDO(ddoCode, Long.parseLong(ddoType), 'Y');

		//added by sunita: start
		rp.updateDDoMst(lStrDdoCode, ddoType);
		logger.info(".........10................Inserting seq table...........................");
		rp.insertRepoDDO(lStrDdoCode, Long.parseLong(ddoType), 'Y');
		//added by sunita: end
		logger.info(".........................Entries Done in above tables............................");
		logger.info(".........................Inserting in wf_org_post_mpg_mst............................");
		rp.insertWfOrgpost(lLngPostId);
		logger.info(".........................Inserting in WF_ORG_USR_MPG_MST---............................");
		rp.insertWfOrgUsr(lLngUserId);
		logger.info(".........................Inserting in WF_ORG_LOC_MPG_MST---............................");
		rp.insertWfOrgLoc(lStrLocCode);
		List lvl2DDOlist = null;
		lvl2DDOlist = rp.getLvl2DDo();
		logger.info("cmntreasuryMstList:::" + lvl2DDOlist.size());
		objectArgs.put("lvl2DDO", lvl2DDOlist);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("reportingDDOList");
		return objRes;
	}
	 */

	public ResultObject loadLevel2DDOScreen(Map objectArgs) throws Exception
	{
		//logger.info("Entering into lloadLevel2DDOScreen");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		ReportingDDODao rdl = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		List lvl2DDOlist = null;
		lvl2DDOlist = rdl.getLvl2DDo();
		logger.info("cmntreasuryMstList:::" + lvl2DDOlist.size());
		objectArgs.put("lvl2DDO", lvl2DDOlist);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("reportingDDOList");

		return objRes;
	}

	public ResultObject consolidateBillGrp(Map objectArgs) throws Exception
	{

		//logger.info("Entering into consolidateBillGrp");
		Map<Long,Long> consolidatedGpfMap = new HashMap<Long,Long>();
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Map<String,String> provFundNormalMap = new HashMap<String,String>();
		String value1 = "";
		Map<String,List<String>> consolidatedLoanMap = new HashMap<String,List<String>>();  //changes by Akshay
		List<String> values = new ArrayList<String>();
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		
		//Added By Naveen for MTR44 
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ReportingDDODaoImpl rdl = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		ConsolidatedBillMstDaoImpl  consBill1 = new ConsolidatedBillMstDaoImpl(ConsolidatedBillMstDaoImpl.class, serviceLocator.getSessionFactory());
		
		ConsolidatedBillMpgDaoImpl consBill = new ConsolidatedBillMpgDaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String schemeCode = StringUtility.getParameter("schemeCode", request).trim();
		String paybillId = StringUtility.getParameter("paybillId", request).trim();
		logger.info("paybillId:::"+paybillId);
		paybillId = (paybillId.equals("")) ? "0" : paybillId;
		String month = StringUtility.getParameter("selyear", request).trim();
		String year = StringUtility.getParameter("selmonth", request).trim();
		ConsolidatedBillMst ConsolidatedBillMstVO = new ConsolidatedBillMst();
		List reptDDODtls=rdl.getReptDdo(postid);

		// schemeCode="22110176";
		// paybillId="99200003251659,992021289278,9920211701";
		
		//Added by saurabh for subscheme code:Start
		String subSchemeCode="";
		if(!StringUtility.getParameter("subSchemecode", request).trim().equals(null) &&
				!StringUtility.getParameter("subSchemecode", request).trim().equals("") &&
				!StringUtility.getParameter("subSchemecode", request).trim().equals("-1"))
		{
			subSchemeCode = StringUtility.getParameter("subSchemecode", request).trim();
		}
		else
		{
			subSchemeCode="-";
		}
		logger.info("subSchemeCode*************:::" + subSchemeCode);
		//Added by saurabh for subscheme code:End

		
		List schemeDtls = null;
		schemeDtls = rdl.getSchemeDtls(schemeCode,subSchemeCode);
		List grossNet = null;
		grossNet = rdl.getGrossNetByPayBillId(paybillId);
		List allowDed = null;
		allowDed = rdl.getAllowDedByPayBillId(paybillId);
		String deptName = "";
		deptName = rdl.getTreasuryByPostId(postid);


		//added by saurabh for loans and advances
		List loansAndAdvAmount = null;
		loansAndAdvAmount = rdl.getLoansAndAdvancesByPayBillId(paybillId);


		//added by saurabh

		List lstGpf = null;
		lstGpf = rdl.getGpfByPayBillId(paybillId);




		objectArgs.put("schemeDtls", schemeDtls);
		objectArgs.put("schemeCode", schemeCode);
		objectArgs.put("subSchemeCode", subSchemeCode);
		objectArgs.put("grossNet", grossNet);
		objectArgs.put("allowDed", allowDed);
		objectArgs.put("month", month);
		objectArgs.put("year", year);
		objectArgs.put("deptName", deptName);
		String[] grossValue=null;
		String netInWrds = "Zero";
		String grossInWrds = "Zero";
		String net = "";
		String gross = "";
		String pt = "";
		String pf = "";
		String gis = "";
		String accPolicy = "";
		String hrr = "";
		Long loanAndAdv = 0l;
		String dcps = "";
		String gpfAbc = "0";
		String gpfD = "0";
		long sgGpfAbc= 0l;
		long sgGpfD= 0l;
		String sumabc= "0";
		String sumD= "0";
		String revenueStamp = "0";
		String npsEmplrDeduc = "0";
		//added by Kinjal
		//String it = "";
		if (allowDed != null && allowDed.size() > 0)
		{
			Iterator IT = allowDed.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				if (lObj[0] == null)
					lObj[0] = "0";
				if (lObj[1] == null)
					lObj[1] = "0";
				if (lObj[2] == null)
					lObj[2] = "0";
				if (lObj[3] == null)
					lObj[3] = "0";
				pt = lObj[0].toString();
				pf = lObj[1].toString();
				logger.info("pf123  = " + pf);
				dcps = lObj[2].toString();
				gis=lObj[3].toString();
				logger.info("GIS   = " + gis);
				hrr=lObj[4].toString();
				accPolicy=lObj[5].toString();
				if(lObj[6] != null)
				npsEmplrDeduc = lObj[6].toString();
				//revenueStamp = lObj[6].toString();
				//it = lObj[2].toString();

			}
		}
		logger.info("pt:::" + pt);
		logger.info("dcps:::" + dcps);
		Long tempNet=0l;


		if (loansAndAdvAmount != null && loansAndAdvAmount.size() > 0)
		{
			Iterator IT = loansAndAdvAmount.iterator();
			//logger.info("helloooo");

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				if (lObj[0] == null)
					lObj[0] = "0";
				values.add(lObj[0].toString());
				values.add("-");
				consolidatedLoanMap.put("CO-OPERATIVE HSG. SOCIETY",values);
				values = new ArrayList<String>();


				if (lObj[1] == null)
					lObj[1] = "0";
				values.add(lObj[1].toString());
				values.add("-");
				consolidatedLoanMap.put("CO-OPERATIVE HSG. SOCIETY(INT)",values);
				values = new ArrayList<String>();

				if (lObj[2] == null)
					lObj[2] = "0";
				values.add(lObj[2].toString());
				values.add("7610504101");
				consolidatedLoanMap.put("COMPUTER ADVANCE",values);
				values = new ArrayList<String>();

				if (lObj[3] == null)
					lObj[3] = "0";
				values.add(lObj[3].toString());
				values.add("-");
				consolidatedLoanMap.put("MCA LAND",values);
				values = new ArrayList<String>();

				if (lObj[4] == null)
					lObj[4] = "0";
				values.add(lObj[4].toString());
				values.add("-");
				consolidatedLoanMap.put("MCA LAND(INT)",values);
				values = new ArrayList<String>();

				if (lObj[5] == null)
					lObj[5] = "0";
				values.add(lObj[5].toString());
				values.add("-");
				consolidatedLoanMap.put("HBA",values);
				values = new ArrayList<String>();

				if (lObj[6] == null)
					lObj[6] = "0";
				values.add(lObj[6].toString());
				values.add("7610501501");
				consolidatedLoanMap.put("HBA AIS",values);
				values = new ArrayList<String>();

				if (lObj[7] == null)
					lObj[7] = "0";
				values.add(lObj[7].toString());
				values.add("0049173801");
				consolidatedLoanMap.put("HBA AIS INT",values);
				values = new ArrayList<String>();

				if (lObj[8] == null)
					lObj[8] = "0";
				values.add(lObj[8].toString());
				values.add("-");
				consolidatedLoanMap.put("HBA CONSTRUCTION",values);
				values = new ArrayList<String>();

				if (lObj[9] == null)
					lObj[9] = "0";
				values.add(lObj[9].toString());
				values.add("-");
				consolidatedLoanMap.put("HBA CONSTRUCTION INT",values);
				values = new ArrayList<String>();

				if (lObj[10] == null)
					lObj[10] = "0";
				values.add(lObj[10].toString());
				values.add("7610501501");
				consolidatedLoanMap.put("HBA HOUSE",values);
				values = new ArrayList<String>();

				if (lObj[11] == null)
					lObj[11] = "0";
				values.add(lObj[11].toString());
				values.add("0049173801");
				consolidatedLoanMap.put("HBA HOUSE INT",values);
				values = new ArrayList<String>();

				if (lObj[12] == null)
					lObj[12] = "0";
				values.add(lObj[12].toString());
				values.add("7610501501");
				consolidatedLoanMap.put("HBA LAND",values);
				values = new ArrayList<String>();

				if (lObj[13] == null)
					lObj[13] = "0";
				values.add(lObj[13].toString());
				values.add("0049173801");
				consolidatedLoanMap.put("HBA LAND INT",values);
				values = new ArrayList<String>();

				if (lObj[14] == null)
					lObj[14] = "0";
				values.add(lObj[14].toString());
				values.add("7610502401");
				consolidatedLoanMap.put("OTHER_VEH_ADV",values);
				values = new ArrayList<String>();

				if (lObj[15] == null)
					lObj[15] = "0";
				values.add(lObj[15].toString());
				values.add("0049174701");
				consolidatedLoanMap.put("OTHER_VEH_ADV_INT",values);
				values = new ArrayList<String>();

				logger.info("Long.parseLong(lObj[0].toString())"+Long.parseLong(lObj[10].toString()));
				loanAndAdv = Long.parseLong(lObj[0].toString()) + Long.parseLong(lObj[1].toString()) + Long.parseLong(lObj[2].toString()) + Long.parseLong(lObj[3].toString()) + Long.parseLong(lObj[4].toString())+ Long.parseLong(lObj[5].toString())+Long.parseLong(lObj[6].toString())+Long.parseLong(lObj[7].toString())+Long.parseLong(lObj[8].toString())+Long.parseLong(lObj[9].toString())+Long.parseLong(lObj[10].toString())+Long.parseLong(lObj[11].toString())+Long.parseLong(lObj[12].toString())+Long.parseLong(lObj[13].toString()) ;
				logger.info("loanAndAdv--------"+loanAndAdv);

			}

		}


		if (grossNet != null && grossNet.size() > 0)
		{
			Iterator IT = grossNet.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				if (lObj[1] == null)
					lObj[1] = "0";
				if (lObj[0] == null)
					lObj[0] = "0";

				//added by roshan
				gross = lObj[0].toString();
				grossValue=gross.split("\\.");
				Long tempGross=0l;

				logger.info("gross:::"+gross);
				logger.info("grossValue.length:::"+grossValue.length);

				if(grossValue.length>1 && grossValue[1]!=null && Long.parseLong(grossValue[1].toString())>0){
					grossInWrds = ConvertNumbersToWord.convert(new Long(Long.parseLong(grossValue[0].toString())+1));
					tempGross= Long.parseLong(grossValue[0].toString())+1;
				}
				else{
					grossInWrds = ConvertNumbersToWord.convert(new Long(grossValue[0]));
					tempGross= Long.parseLong(grossValue[0].toString());
				}



				//ended by roshan
				logger.info("pt:::" + pt);
				logger.info("pf:::" + pf);
				logger.info("dcps:::" + dcps);
				logger.info("tempGross:::" + tempGross);//npsEmplrDeduc


				if(dcps!=null  && npsEmplrDeduc !=null && dcps!="0"  && npsEmplrDeduc !="0" && dcps!=""  && npsEmplrDeduc !="") {
					tempNet= Long.parseLong(tempGross.toString())-(Long.parseLong(pf)+Long.parseLong(gis)+Long.parseLong(hrr)+Long.parseLong(pt)+loanAndAdv +Long.parseLong(accPolicy));
					//tempNet= Long.parseLong(tempGross.toString())-(Long.parseLong(pf)+Long.parseLong(gis)+Long.parseLong(hrr)+Long.parseLong(pt)+Long.parseLong(dcps)+loanAndAdv +Long.parseLong(accPolicy)+Long.parseLong(revenueStamp));
					net = tempNet.toString();
				}else {
					tempNet= Long.parseLong(tempGross.toString())-(Long.parseLong(pf)+Long.parseLong(gis)+Long.parseLong(hrr)+Long.parseLong(pt)+Long.parseLong(dcps)+loanAndAdv +Long.parseLong(accPolicy));
					net = tempNet.toString();
				}
				/*Long.parseLong(accPolicy)+*/
				objectArgs.put("netTotal", net);
				logger.info("Net in number for me is..."+net);
				netInWrds = ConvertNumbersToWord.convert(new Long(net));
				gross = tempGross.toString();
				grossInWrds = ConvertNumbersToWord.convert(new Long(gross));
				logger.info("grossInWrds:::" + grossInWrds);
				logger.info("netInWrds:::" + netInWrds);

			}
		}






		//added by saurabh
		/*	Long tempPf = 0l;
		logger.info("lstGpf.size----"+ lstGpf.size());
		if (lstGpf != null && lstGpf.size() > 0)
		{
			logger.info("In if---");
			Iterator IT = lstGpf.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				if (lObj[0] != null)
				{
					logger.info("In main if----");
					if (lObj[1] == null)
						lObj[1] = "0";
					consolidatedGpfMap.put(Long.parseLong(lObj[0].toString()),Long.parseLong(lObj[1].toString()));
					tempPf = tempPf + Long.parseLong(lObj[1].toString());
					logger.info("tempPf valueeeee----"+ tempPf);
				}

			}
			logger.info("out of while----");
			//pf = pf - tempPf;
			//consolidatedGpfMap.put(Long.parseLong(lObj[0].toString()),Long.parseLong(lObj[1].toString()));
		}



		else
		{

			consolidatedGpfMap.put(0l,Long.getLong(pf));

		}


		 */
		Long provNormAmtLng=0L;
		if (lstGpf != null && lstGpf.size() > 0)
		{
			Iterator IT = lstGpf.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();

				if (lObj[0] != null)
				{
					if (lObj[1] == null)
						lObj[1] = "0";


					/*if((lObj[0].toString()).equals("8336501101"))
					{
						//Code modified by Akshay
						//	if((lObj[0].toString()).equals("8336501101"))
						//	{
						if(lObj[3].toString().equals("100067")){
							sumD = Long.parseLong(lObj[2].toString()); 
							logger.info("In sumD----"+sumD);

						}
						else{
							sumabc = Long.parseLong(lObj[1].toString()); 
							logger.info("In sumabc----"+sumabc);
						//}
					}
					//Ended	
					 */			
					if((lObj[0].toString()).equals("8009501201"))
					{
						sgGpfAbc = Long.parseLong(lObj[1].toString()); 
						logger.info("In sgGpfAbc----"+sgGpfAbc);
					}
					else if((lObj[0].toString()).equals("8009517301"))
					{
						sgGpfD = Long.parseLong(lObj[1].toString()); 
						logger.info("In sgGpfD----"+sgGpfD);
					}
					else
					{
						/*value1=(lObj[1].toString());
						provFundNormalMap.put(lObj[0].toString(),value1);	
						*/
					//	provNormAmtLng =provNormAmtLng+(long) lObj[1];
						provFundNormalMap.put("8336522701/8336821501/8336512901/8336521801/8336510201/8336509401/"+lObj[0].toString(), lObj[1].toString());
					}
				}
			}
		}
		//objectArgs.put("dcps", dcps);

		String strTableConsolidatedBillMst = "CONSOLIDATED_BILL_MST";
		Long ConsolidatedBillMstPkID = IFMSCommonServiceImpl.getNextSeqNum(strTableConsolidatedBillMst, objectArgs);
		ConsolidatedBillMstVO.setConsBillId(ConsolidatedBillMstPkID);
		ConsolidatedBillMstVO.setStatus(0);
		ConsolidatedBillMstVO.setPostId(postid);
		ConsolidatedBillMstVO.setMonth(Long.parseLong(month));
		ConsolidatedBillMstVO.setYear(Long.parseLong(year));
		ConsolidatedBillMstVO.setCreatedDate(new Date());
		ConsolidatedBillMstVO.setUpdatedDate(new Date());
		ConsolidatedBillMstVO.setSchemeCode(schemeCode);
		//added by saurabh
		ConsolidatedBillMstVO.setSubSchemeCode(subSchemeCode);
		ConsolidatedBillMstVO.setGrossAmt(Long.parseLong(gross));
		ConsolidatedBillMstVO.setNetAmt(Long.parseLong(net));
		ConsolidatedBillMstVO.setHrr(Long.parseLong(hrr));
		ConsolidatedBillMstVO.setGis(Long.parseLong(gis));
		ConsolidatedBillMstVO.setPt(Long.parseLong(pt));
		ConsolidatedBillMstVO.setPf(Long.parseLong(pf));
		ConsolidatedBillMstVO.setDcps(Long.parseLong(dcps));
		ConsolidatedBillMstVO.setAccPolicy(Long.parseLong(accPolicy));
		ConsolidatedBillMstVO.setNpsEmplrDed(Long.parseLong(npsEmplrDeduc));
		//ConsolidatedBillMstVO.setRevenueStamp(Long.parseLong(revenueStamp));
		objectArgs.put("consolidatedLoanMap", consolidatedLoanMap);
		objectArgs.put("gpfAbc", gpfAbc);
		objectArgs.put("gpfD", gpfD);
		objectArgs.put("sgGpfAbc", sgGpfAbc);
		objectArgs.put("sgGpfD", sgGpfD); 
		objectArgs.put("gis", gis);
		objectArgs.put("hrr", hrr);
		objectArgs.put("accPolicy", accPolicy);
		objectArgs.put("sumabc", sumabc);
		objectArgs.put("sumD", sumD);
		objectArgs.put("revenueStamp", revenueStamp);
		objectArgs.put("npsEmplrDeduc", npsEmplrDeduc);
		objectArgs.put("dcps", dcps);

		objectArgs.put("consolidatedGpfMap", consolidatedGpfMap);
		objectArgs.put("provFundNormalMap", provFundNormalMap);
		//added by Kinjal: Start
		//ConsolidatedBillMstVO.setIt(Long.parseLong(it));
		//added by Kinjal: End
		consBill.create(ConsolidatedBillMstVO);
		logger.info("Inserted in CONSOLIDATED_BILL_MST :::" );
		String strTableConsolidatedBillMpg = "CONSOLIDATED_BILL_MPG";
		String[] paybillIdArray = paybillId.split(",");
		ConsolidatedBillMpgDaoImpl consBillMpg = new ConsolidatedBillMpgDaoImpl(ConsolidatedBillMpg.class, serviceLocator.getSessionFactory());
		//Long CONSOLIDATED_BILL_MPGPkID = IFMSCommonServiceImpl.getNextSeqNum(strTableConsolidatedBillMpg, objectArgs);


		Long CONSOLIDATED_BILL_MPGPkID =rdl.getNextSeqNum(paybillIdArray.length);


		for (int i = 0; i < paybillIdArray.length; i++)
		{
			if (!paybillIdArray[i].equals("0"))
			{
				ConsolidatedBillMpg consolidatedBillMpgVO = new ConsolidatedBillMpg();
				consolidatedBillMpgVO.setConsBillId(ConsolidatedBillMstPkID);
				consolidatedBillMpgVO.setId(CONSOLIDATED_BILL_MPGPkID);
				consolidatedBillMpgVO.setPaybillId(Long.parseLong(paybillIdArray[i]));
				consolidatedBillMpgVO.setStatus(0);
				consBillMpg.create(consolidatedBillMpgVO);
				CONSOLIDATED_BILL_MPGPkID++;
			}
		}
		//logger.info("Inserted in CONSOLIDATED_BILL_MPG :::" );


		//added by vivek 

		//added by vaibhav tyagi :start
		int updatePaybillHeadMpg= rdl.updatePaybillStatusAfterConsd(paybillId,Integer.parseInt(month),year);
		//added by vaibhav tyagi:end

		Long lvl2PostId=null;
		Long lvl3PostId=null;
		Long lvl4PostId=null;
		Long level=null;
		if (reptDDODtls != null && reptDDODtls.size() > 0)
		{
			Iterator IT = reptDDODtls.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				logger.info("obj len :::" +lObj.length);
				lvl2PostId=Long.parseLong(lObj[0].toString());
				if(lObj[1]!=null)
					lvl3PostId=Long.parseLong(lObj[1].toString());
				if(lObj[2]!=null)
					lvl4PostId=Long.parseLong(lObj[2].toString());
				if(lObj[3]!=null)
					level=Long.parseLong(lObj[3].toString());

			}
		}
		
		
		  /* added by Naveen MTR Ref lekhchand*/
		String DDOofficeName=null;
		String DDOCode=null;
		String DDOPostName=null;
		String TresaryCode=null;
		String TresaryName=null;
		 
		final String ddoCode = consBill1.getDDOCodefromPostId(postid);
        final List DDoOfficeDeta = rdl.getDDOOfficeDataforMTR44(ddoCode);
        logger.info("DDoOfficeDeta --------" + DDoOfficeDeta.toString());
        if (DDoOfficeDeta != null && DDoOfficeDeta.size() > 0)
		{	Iterator IT = DDoOfficeDeta.iterator();
			Object[] lObj = null;
			while (IT.hasNext())
			{	lObj = (Object[]) IT.next();
                if (lObj[0] != null){
                	DDOofficeName = lObj[0].toString();
                }
                if (lObj[1] != null){
                    DDOCode = lObj[1].toString();
                }
                if (lObj[2] != null){
                	DDOPostName = lObj[2].toString();
                }
			}
		}
        final List tresaryofficeList = rdl.getTresaryofficeListforMTR44(ddoCode);
        logger.info("tresaryofficeList --------" + tresaryofficeList.toString());
        if (tresaryofficeList != null && tresaryofficeList.size() > 0)
		{	Iterator IT = tresaryofficeList.iterator();
			Object[] lObj = null;
			while (IT.hasNext())
			{	lObj = (Object[]) IT.next();
                if (lObj[0] != null){
                	TresaryCode = lObj[0].toString();
                }
                if (lObj[1] != null){
                	TresaryName = lObj[1].toString();
                }
               
			}
		}
        
     // adde by lekhchand for MTR report
 		objectArgs.put("TresaryName", TresaryName);
         objectArgs.put("TresaryCode", TresaryCode);
         objectArgs.put("DDOName", DDOofficeName);
         objectArgs.put("DDOCode", DDOCode);
         objectArgs.put("DDOPostName", DDOPostName);
        /* ended by Naveen MTR Ref lekhchand*/
		String a;



		String strTableConsolidatedBillLvlMpg= "CONSOLIDATED_BILL_LEVEL_POST_MPG";
		ConsolidatedBillLvlMpgMst consolidatedBillLvlMpg=new ConsolidatedBillLvlMpgMst();
		ConsolidatedBillLvlMpgDaoImpl lvlMpg=new ConsolidatedBillLvlMpgDaoImpl(ConsolidatedBillLvlMpgMst.class, serviceLocator.getSessionFactory());	
		long ConsolidatedBillLvlMpgPkID = IFMSCommonServiceImpl.getNextSeqNum(strTableConsolidatedBillMst, objectArgs);
		consolidatedBillLvlMpg.setId(ConsolidatedBillLvlMpgPkID);
		consolidatedBillLvlMpg.setStatus(0L);
		consolidatedBillLvlMpg.setBillId(ConsolidatedBillMstPkID);
		consolidatedBillLvlMpg.setLvl2PostId(lvl2PostId);
		consolidatedBillLvlMpg.setLvl3PostId(lvl3PostId);
		consolidatedBillLvlMpg.setLvl4PostId(lvl4PostId);
		consolidatedBillLvlMpg.setLevel(Long.valueOf(2));
		lvlMpg.create(consolidatedBillLvlMpg);
		//logger.info("Inserted in CONSOLIDATED_BILL_LEVEL_POST_MPG :::" );

		objectArgs.put("netPayInWords", netInWrds);
		objectArgs.put("grossPayInWords", grossInWrds);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("consolidateBillGrp");

		return objRes;
	}

	private String arrayToString(String[] a, String separator, String type)
	{
		StringBuffer result = new StringBuffer();

		if (a.length > 0)
		{
			if (type == "string")
				result.append("'");
			result.append(a[0]);
			for (int i = 1; i < a.length; i++)
			{
				result.append(separator);
				result.append(a[i]);
			}
			if (type == "string")
				result.append("'");
		}
		return result.toString();
	}

	public ResultObject isconsBillGenerated(Map objectArgs) throws Exception
	{
		//logger.info("Entering into isconsBillGenerated");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillMpgDaoImpl consBill = new ConsolidatedBillMpgDaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String paybillId = StringUtility.getParameter("paybillId", request).trim();
		paybillId = (paybillId.equals("")) ? "0" : paybillId;
		List genIds = null;
		genIds = consBill.getGeneratedIds(paybillId);
		StringBuilder lStrBldXML = new StringBuilder();
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<isGenerated>");
		if (genIds != null && genIds.size() > 0)
		{

			lStrBldXML.append("yes");

		}
		else
			lStrBldXML.append("no");
		lStrBldXML.append("</isGenerated>");
		lStrBldXML.append("</XMLDOC>");
		String lSBStatus = lStrBldXML.toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		logger.info("********************************************" + lStrResult);
		objectArgs.put("ajaxKey", lStrResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;

	}


	public ResultObject viewConsolidatedBill(Map objectArgs) throws Exception
	{
		//logger.info("Entering into viewConsolidatedBill");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillMstDaoImpl consBill = new ConsolidatedBillMstDaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String schemeCode = StringUtility.getParameter("schemeCode", request).trim();
		String month = StringUtility.getParameter("month", request).trim();
		String year = StringUtility.getParameter("year", request).trim();
		String flag = StringUtility.getParameter("flag", request).trim();
		logger.info("flag" + flag);
		logger.info("year" + year);
		logger.info("month" + month);
		Calendar cal = Calendar.getInstance();
		String currmonth = (new Integer((cal.get(Calendar.MONTH) + 1))).toString();
		String curryear = (new Integer(cal.get(Calendar.YEAR))).toString();
		month = (month.equals("-1") || month == null || month.equals("0") || month.equals("")) ? currmonth : month;
		year = (year.equals("-1") || year == null || year.equals("0") || year.equals("")) ? curryear : year;
		if (flag.equals("") || flag == null)
		{
			month = currmonth;
			year = curryear;
		}
		List list = null;
		if (schemeCode.equals("") || schemeCode == null || schemeCode.equals("-1") || schemeCode.equals("0"))
		{
			list = consBill.getConsBillDtls(month, year, postid.toString());

		}
		else
			list = consBill.getConsBillDtls(schemeCode, month, year, postid.toString());
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serviceLocator.getSessionFactory());
		List<CmnLookupMst> MonthlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Month", langId);
		List<CmnLookupMst> YearLookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Year", langId);
		ZpReportingDAO ZpReportingDAOImpl = new ZpReportingDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		List schemeList = ZpReportingDAOImpl.getSchemeCodeByPost(postid);

		int size=list.size();

		objectArgs.put("list", list);
		objectArgs.put("size", size);
		objectArgs.put("month", MonthlookUpList);
		objectArgs.put("year", YearLookUpList);
		objectArgs.put("scheme", schemeList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("searchConsolidateBill");

		return objRes;
	}

	public ResultObject deleteconsBillGenerated(Map objectArgs) throws Exception
	{
		//logger.info("Entering into deleteconsBillGenerated");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillMstDaoImpl consBill = new ConsolidatedBillMstDaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		logger.info("Bill No is :: "+StringUtility.getParameter("billNo", request));
		String billid=null;
		if(StringUtility.getParameter("billNo", request)!=null||!(StringUtility.getParameter("billNo", request).equals(""))||Long.parseLong(StringUtility.getParameter("billNo", request))!=-1){
			billid = StringUtility.getParameter("billNo", request).trim();
		}
		logger.info("billid"+billid);
		consBill.deleteConsBill(billid);
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serviceLocator.getSessionFactory());
		List<CmnLookupMst> MonthlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Month", langId);
		List<CmnLookupMst> YearLookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Year", langId);
		ZpReportingDAO ZpReportingDAOImpl = new ZpReportingDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		List schemeList = ZpReportingDAOImpl.getSchemeCodeByPost(postid);
		objectArgs.put("month", MonthlookUpList);
		objectArgs.put("year", YearLookUpList);
		objectArgs.put("scheme", schemeList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("searchConsolidateBill");
		return objRes;
	}

	public ResultObject getConsolidatedReport(Map objectArgs) throws Exception
	{

		//logger.info("Entering into getConsolidatedReport");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		Map<String,String> provFundNormalMap = new HashMap<String,String>();
		String value1 = "";
		Map<String,List<String>> consolidatedLoanMap = new HashMap<String,List<String>>();  //changes by Akshay
		List<String> values = new ArrayList<String>();
		//langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ReportingDDODaoImpl rdl = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		ConsolidatedBillMstDaoImpl consBill = new ConsolidatedBillMstDaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String billId = StringUtility.getParameter("billId", request).trim();


		//added by saurabh


		List lstGpf = null;
		lstGpf = consBill.getGpfByHeadActCode(billId);


		//added by saurabh for loans and advances
		List loansAndAdvAmount = null;
		loansAndAdvAmount = rdl.getLoansAndAdvancesByConsBillId(billId);


		// schemeCode="22110176";
		// paybillId="99200003251659,992021289278,9920211701";
		List list = consBill.getBillReport(billId);
		String deptName = "";
		deptName = rdl.getTreasuryByPostId(postid);
		String majorhead = "";
		String minorHead = "";
		String subHead = "";
		String schemeName = "";
		String schemeCode = "";
		String subSchemeCode = "";
		String subSchemeName = "";
		String demandCode = "";
		String netInWrds = "Zero";
		String grossInWrds = "Zero";
		String net = "";
		String gross = "";
		String pt = "";
		String pf = "0";
		String revenueStamp = "0";
		String gis = "";
		String accPolicy = "";
		String hrr = "";
		Long loanAndAdv = 0l;

		long sumabc = 0l;
		long sumD = 0l;
		String dcps = "";
		String npsEmplrDeduc = "";

		String gpfAbc = "0";
		String gpfD = "0";
		long sgGpfAbc= 0l;
		long sgGpfD= 0l;
		String month = "";
		String year = "";
		Map<Long,Long> consolidatedGpfMap = new HashMap<Long,Long>();
		String subMajorhead="";
		String subMinorhead="";

		if (list != null && list.size() > 0)
		{
			Iterator IT = list.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				if(lObj[0]!=null)
					majorhead=lObj[0].toString();
				if(lObj[1]!=null)
					minorHead=lObj[1].toString();
				if(lObj[2]!=null)
					subHead=lObj[2].toString();
				if(lObj[3]!=null)
					schemeName=lObj[3].toString();
				if(lObj[4]!=null)
					demandCode=lObj[4].toString();
				if(lObj[5]!=null)
					schemeCode=lObj[5].toString();
				if(lObj[7]!=null)
					net=lObj[7].toString();
				if(lObj[6]!=null)
					gross=lObj[6].toString();
				if(lObj[8]!=null)
					pt=lObj[8].toString();
				if(lObj[9]!=null)
					pf=lObj[9].toString();
				logger.info("pf valueeeee----"+ pf);
				//added by Kinjal
				/*if(lObj[10]!=null)
				it=lObj[10].toString();*/
				if(lObj[10]!=null)
					month=lObj[10].toString();
				if(lObj[11]!=null)
					year=lObj[11].toString();
				if(lObj[12]!=null)
					dcps=lObj[12].toString();
				//added by samadhan for sub major and minor head in MTR 44
				if(lObj[13]!=null)
					subMajorhead=lObj[12].toString();
				if(lObj[14]!=null)
					subMinorhead=lObj[13].toString();

				if(lObj[15]!=null)
					gis=lObj[15].toString();
				if(lObj[16]!=null)
					hrr=lObj[16].toString();
				if(lObj[17]!=null)
					accPolicy=lObj[17].toString();
				
				if(lObj[18]!=null)
					subSchemeCode=lObj[18].toString();
				if(lObj[19]!=null)
					subSchemeName=lObj[19].toString();
				if(lObj[20]!=null)
					npsEmplrDeduc=lObj[20].toString();
				
				/*if(lObj[20]!=null)
					revenueStamp=lObj[20].toString();*/
			}
		}
Long netAmt = 0l;


if(dcps!=null  && npsEmplrDeduc !=null && dcps!="0"  && npsEmplrDeduc !="0" && dcps!=""  && npsEmplrDeduc !="") {
	
	netAmt = Long.valueOf(Long.parseLong(gross) - ((Long.parseLong(pt) + Long.parseLong(pf) + Long.parseLong(hrr) + Long.parseLong(gis) + Long.parseLong(accPolicy)  + Long.parseLong(revenueStamp))));
	net = netAmt.toString();
}else {
	netAmt = Long.valueOf(Long.parseLong(gross) - ((Long.parseLong(pt) + Long.parseLong(pf) + Long.parseLong(hrr) + Long.parseLong(gis) + Long.parseLong(accPolicy)  + Long.parseLong(revenueStamp) + Long.parseLong(dcps))));
	net = netAmt.toString();
	
}
		/*Long tempPf = 0l;
		logger.info("lstGpf.size----"+ lstGpf.size());
		if (lstGpf != null && lstGpf.size() > 0)
		{
			logger.info("In if---");
			Iterator IT = lstGpf.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				if (lObj[0] != null)
				{
					logger.info("In main if----");
					if (lObj[1] == null)
						lObj[1] = "0";
					consolidatedGpfMap.put(Long.parseLong(lObj[0].toString()),Long.parseLong(lObj[1].toString()));
					tempPf = tempPf + Long.parseLong(lObj[1].toString());
					logger.info("tempPf valueeeee----"+ tempPf);
				}

			}
			logger.info("out of while----");
			//pf = pf - tempPf;
			//consolidatedGpfMap.put(Long.parseLong(lObj[0].toString()),Long.parseLong(lObj[1].toString()));
		}



		else
		{

			consolidatedGpfMap.put(0l,pf);

		}*/







		//added by saurabh
		BigInteger  provNormAmt=new BigInteger("0");
		Long  provNormAmtLng=0L;
		 
		if (lstGpf != null && lstGpf.size() > 0)
		{
			Iterator IT = lstGpf.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();

				if (lObj[0] != null)
				{
					if (lObj[1] == null)
						lObj[1] = "0";


					/*if((lObj[0].toString()).equals("8336501101"))
					{
						//Code modified by Akshay
						//	if((lObj[0].toString()).equals("8336501101"))
						//	{
						if(lObj[3].toString().equals("100067")){
							sumD = Long.parseLong(lObj[2].toString()); 
							logger.info("In sumD----"+sumD);

						}
						else{
							sumabc = Long.parseLong(lObj[1].toString()); 
							logger.info("In sumabc----"+sumabc);
						//}
					}
					//Ended	
					 */			
					if((lObj[0].toString()).equals("8009501201"))
					{
						sgGpfAbc = Long.parseLong(lObj[1].toString()); 
						logger.info("In sgGpfAbc----"+sgGpfAbc);
					}
					else if((lObj[0].toString()).equals("8009517301"))
					{
						sgGpfD = Long.parseLong(lObj[1].toString()); 
						logger.info("In sgGpfD----"+sgGpfD);
					}
					else /*if((lObj[0].toString()).equals("8336501101"))
					{
						provFundNormalMap.put(lObj[0].toString(),lObj[1].toString());	
					}
					else*/
					{
						 
			            provFundNormalMap.put("8336522701/8336821501/8336512901/8336521801/8336510201/8336509401/"+lObj[0].toString(), lObj[1].toString());
					}
				}
			}

		}

		if (loansAndAdvAmount != null && loansAndAdvAmount.size() > 0)
		{
			Iterator IT = loansAndAdvAmount.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				if (lObj[0] == null)
					lObj[0] = "0";
				values.add(lObj[0].toString());
				values.add("-");
				consolidatedLoanMap.put("CO-OPERATIVE HSG. SOCIETY",values);
				values = new ArrayList<String>();


				if (lObj[1] == null)
					lObj[1] = "0";
				values.add(lObj[1].toString());
				values.add("-");
				consolidatedLoanMap.put("CO-OPERATIVE HSG. SOCIETY(INT)",values);
				values = new ArrayList<String>();

				if (lObj[2] == null)
					lObj[2] = "0";
				values.add(lObj[2].toString());
				values.add("7610504101");
				consolidatedLoanMap.put("COMPUTER ADVANCE",values);
				values = new ArrayList<String>();

				if (lObj[3] == null)
					lObj[3] = "0";
				values.add(lObj[3].toString());
				values.add("-");
				consolidatedLoanMap.put("MCA LAND",values);
				values = new ArrayList<String>();

				if (lObj[4] == null)
					lObj[4] = "0";
				values.add(lObj[4].toString());
				values.add("-");
				consolidatedLoanMap.put("MCA LAND(INT)",values);
				values = new ArrayList<String>();

				if (lObj[5] == null)
					lObj[5] = "0";
				values.add(lObj[5].toString());
				values.add("-");
				consolidatedLoanMap.put("HBA",values);
				values = new ArrayList<String>();

				if (lObj[6] == null)
					lObj[6] = "0";
				values.add(lObj[6].toString());
				values.add("7610501501");
				consolidatedLoanMap.put("HBA AIS",values);
				values = new ArrayList<String>();

				if (lObj[7] == null)
					lObj[7] = "0";
				values.add(lObj[7].toString());
				values.add("0049173801");
				consolidatedLoanMap.put("HBA AIS INT",values);
				values = new ArrayList<String>();

				if (lObj[8] == null)
					lObj[8] = "0";
				values.add(lObj[8].toString());
				values.add("-");
				consolidatedLoanMap.put("HBA CONSTRUCTION",values);
				values = new ArrayList<String>();

				if (lObj[9] == null)
					lObj[9] = "0";
				values.add(lObj[9].toString());
				values.add("-");
				consolidatedLoanMap.put("HBA CONSTRUCTION INT",values);
				values = new ArrayList<String>();

				if (lObj[10] == null)
					lObj[10] = "0";
				values.add(lObj[10].toString());
				values.add("7610501501");
				consolidatedLoanMap.put("HBA HOUSE",values);
				values = new ArrayList<String>();

				if (lObj[11] == null)
					lObj[11] = "0";
				values.add(lObj[11].toString());
				values.add("0049173801");
				consolidatedLoanMap.put("HBA HOUSE INT",values);
				values = new ArrayList<String>();

				if (lObj[12] == null)
					lObj[12] = "0";
				values.add(lObj[12].toString());
				values.add("7610501501");
				consolidatedLoanMap.put("HBA LAND",values);
				values = new ArrayList<String>();

				if (lObj[13] == null)
					lObj[13] = "0";
				values.add(lObj[13].toString());
				values.add("0049173801");
				consolidatedLoanMap.put("HBA LAND INT",values);
				values = new ArrayList<String>();

				if (lObj[14] == null)
					lObj[14] = "0";
				values.add(lObj[14].toString());
				values.add("7610502401");
				consolidatedLoanMap.put("OTHER_VEH_ADV",values);
				values = new ArrayList<String>();

				if (lObj[15] == null)
					lObj[15] = "0";
				values.add(lObj[15].toString());
				values.add("0049174701");
				consolidatedLoanMap.put("OTHER_VEH_ADV_INT",values);
				values = new ArrayList<String>();


				logger.info("Long.parseLong(lObj[0].toString())"+Long.parseLong(lObj[0].toString()));
				loanAndAdv = Long.parseLong(lObj[0].toString()) + Long.parseLong(lObj[1].toString()) + Long.parseLong(lObj[2].toString()) + Long.parseLong(lObj[3].toString()) + Long.parseLong(lObj[4].toString()) ;
				logger.info("loanAndAdv--------"+loanAndAdv);

			}

		}


		 /* added by Naveen MTR REF lekhchand*/
	 
		String DDOofficeName =null;
		String DDOCode =null;
		String DDOPostName =null;
		String TresaryCode=null;
		String TresaryName=null;
		 final String ddoCode = consBill.getDDOCodefromPostId(postid);
        final List DDoOfficeDeta = rdl.getDDOOfficeDataforMTR44(ddoCode);
        logger.info("DDoOfficeDeta --------" + DDoOfficeDeta.toString());
        if (DDoOfficeDeta != null && DDoOfficeDeta.size() > 0)
		{	Iterator IT = DDoOfficeDeta.iterator();
			Object[] lObj = null;
			while (IT.hasNext())
			{	lObj = (Object[]) IT.next();
                if (lObj[0] != null){
                	DDOofficeName = lObj[0].toString();
                }
                if (lObj[1] != null){
                    DDOCode = lObj[1].toString();
                }
                if (lObj[2] != null){
                	DDOPostName = lObj[2].toString();
                }
			}
		}
        final List tresaryofficeList = rdl.getTresaryofficeListforMTR44(ddoCode);
        logger.info("tresaryofficeList --------" + tresaryofficeList.toString());
        if (tresaryofficeList != null && tresaryofficeList.size() > 0)
		{	Iterator IT = tresaryofficeList.iterator();
			Object[] lObj = null;
			while (IT.hasNext())
			{	lObj = (Object[]) IT.next();
                if (lObj[0] != null){
                	TresaryCode = lObj[0].toString();
                }
                if (lObj[1] != null){
                	TresaryName = lObj[1].toString();
                }
               
			}
		}
        /* ended by Naveen MTR REF .lekhchand*/

		logger.info("dcps:::" + dcps);
		logger.info("The value of net is..."+net);
		netInWrds = ConvertNumbersToWord.convert(new Long(net));
		grossInWrds = ConvertNumbersToWord.convert(new Long(gross));
		logger.info("grossInWrds:::" + grossInWrds);
		logger.info("netInWrds:::" + netInWrds);

		objectArgs.put("majorhead", majorhead);
		objectArgs.put("minorHead", minorHead);
		objectArgs.put("subHead", subHead);
		objectArgs.put("schemeName", schemeName);
		objectArgs.put("schemeCode", schemeCode);
		objectArgs.put("subSchemeCode", subSchemeCode);
		objectArgs.put("subSchemeName", subSchemeName);
		objectArgs.put("demandCode", demandCode);
		objectArgs.put("netInWrds", netInWrds);
		objectArgs.put("grossInWrds", grossInWrds);
		objectArgs.put("net", net);
		objectArgs.put("gross", gross);
		objectArgs.put("pt", pt);
		objectArgs.put("pf", pf);
		objectArgs.put("dcps", dcps);
		objectArgs.put("npsEmplrDeduc", npsEmplrDeduc);
		objectArgs.put("gpfAbc", gpfAbc);
		objectArgs.put("gpfD", gpfD);
		objectArgs.put("sgGpfAbc", sgGpfAbc);
		objectArgs.put("sgGpfD", sgGpfD); 
		objectArgs.put("gis", gis);
		objectArgs.put("accPolicy", accPolicy);
		objectArgs.put("revenueStamp", revenueStamp);
		objectArgs.put("sumabc", sumabc);
		objectArgs.put("sumD", sumD);
		logger.info("sumabc"+sumabc);
		logger.info("sumD"+sumD);
		objectArgs.put("hrr", hrr);
		objectArgs.put("consolidatedGpfMap", consolidatedGpfMap); 
		objectArgs.put("consolidatedLoanMap", consolidatedLoanMap);

		objectArgs.put("provFundNormalMap", provFundNormalMap);
		//ADDED By Naveen FOR MTR44
	 	objectArgs.put("TresaryName", TresaryName);
        objectArgs.put("TresaryCode", TresaryCode);
        objectArgs.put("DDOName", DDOofficeName);
        objectArgs.put("DDOCode", DDOCode);
        objectArgs.put("DDOPostName", DDOPostName);
        //ENDED BY naveen MTR44
	        
		//added by Kinjal
		//objectArgs.put("it", it);
		objectArgs.put("month", month);
		objectArgs.put("year", year);
		objectArgs.put("deptName", deptName);

		//added by samadhan for sub major and minor head in MTR 44
		objectArgs.put("subMajorhead", subMajorhead);
		objectArgs.put("subMinorhead", subMinorhead);

		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("consolidateBillReport");

		return objRes;
	}

	private void setZero(Object[] object)
	{
		for (int i = 0; i < object.length; i++)
		{
			if (object[i] == null)
				object[i] = 0;
		}

	}


	public ResultObject viewApproveConsolidatedBill(Map objectArgs) throws Exception
	{
		//logger.info("Entering into viewApproveConsolidatedBill");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillLvlMpgDaoImpl consBill = new ConsolidatedBillLvlMpgDaoImpl(ConsolidatedBillLvlMpgMst.class, serviceLocator.getSessionFactory());
		//String billid = StringUtility.getParameter("billid", request).trim();
		List approveList=consBill.getApproveList(postid);
		int size=approveList.size();
		objectArgs.put("size", size);
		objectArgs.put("approveList", approveList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("viewApproveConsolidatedBill");
		return objRes;

	}

	public ResultObject approveConsolidatedBill(Map objectArgs) throws Exception
	{
		//logger.info("Entering into approveConsolidatedBill");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		String billid = StringUtility.getParameter("billNo", request).trim();
		ConsolidatedBillLvlMpgDaoImpl consBill = new ConsolidatedBillLvlMpgDaoImpl(ConsolidatedBillLvlMpgMst.class, serviceLocator.getSessionFactory());
		//String billid = StringUtility.getParameter("billid", request).trim();
		consBill.approveBill(billid);
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serviceLocator.getSessionFactory());
		List<CmnLookupMst> MonthlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Month", langId);
		List<CmnLookupMst> YearLookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Year", langId);
		ZpReportingDAO ZpReportingDAOImpl = new ZpReportingDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		List schemeList = ZpReportingDAOImpl.getSchemeCodeByPost(postid);


		ConsolidatedBillLvlMpgDaoImpl consBillLvl = new ConsolidatedBillLvlMpgDaoImpl(ConsolidatedBillLvlMpgMst.class, serviceLocator.getSessionFactory());
		//String billid = StringUtility.getParameter("billid", request).trim();
		List approveList=consBillLvl.getApproveList(postid);
		int size=approveList.size();
		objectArgs.put("size", size);
		objectArgs.put("approveList", approveList);








		objectArgs.put("month", MonthlookUpList);
		objectArgs.put("year", YearLookUpList);
		objectArgs.put("scheme", schemeList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("viewApproveConsolidatedBill");
		return objRes;

	}

	public ResultObject viewForwardConsolidatedBill(Map objectArgs) throws Exception
	{
		//logger.info("Entering into viewForwardConsolidatedBill");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillLvlMpgDaoImpl consBill = new ConsolidatedBillLvlMpgDaoImpl(ConsolidatedBillLvlMpgMst.class, serviceLocator.getSessionFactory());
		//String billid = StringUtility.getParameter("billid", request).trim();
		List fwdList=consBill.getForwadingList(postid);
		int size=fwdList.size();
		objectArgs.put("size", size);
		objectArgs.put("fwdList", fwdList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("viewForwardConsolidatedBill");
		return objRes;

	}

	public ResultObject forwardConsolidatedBill(Map objectArgs) throws Exception
	{
		//logger.info("Entering into forwardConsolidatedBill");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		String billid = StringUtility.getParameter("billNo", request).trim();
		logger.info("billid"+billid);
		String lstrStatus=StringUtility.getParameter("status", request).trim();
		logger.info("lstrStatus"+lstrStatus);
		long status=Long.parseLong(lstrStatus);

		if(status==0)
			status=2;
		else if(status==2)
			status=3;
		ConsolidatedBillLvlMpgDaoImpl consBill = new ConsolidatedBillLvlMpgDaoImpl(ConsolidatedBillLvlMpgMst.class, serviceLocator.getSessionFactory());
		//String billid = StringUtility.getParameter("billid", request).trim();
		consBill.forwardBill(billid,status);
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serviceLocator.getSessionFactory());
		List<CmnLookupMst> MonthlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Month", langId);
		List<CmnLookupMst> YearLookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Year", langId);
		ZpReportingDAO ZpReportingDAOImpl = new ZpReportingDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		List schemeList = ZpReportingDAOImpl.getSchemeCodeByPost(postid);
		objectArgs.put("month", MonthlookUpList);
		objectArgs.put("year", YearLookUpList);
		objectArgs.put("scheme", schemeList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("searchConsolidateBill");
		return objRes;

	}

	public ResultObject viewRejectConsolidatedBill(Map objectArgs) throws Exception
	{
		//logger.info("Entering into rejectApproveConsolidatedBill");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillLvlMpgDaoImpl consBill = new ConsolidatedBillLvlMpgDaoImpl(ConsolidatedBillLvlMpgMst.class, serviceLocator.getSessionFactory());
		//String billid = StringUtility.getParameter("billid", request).trim();
		List rejectList=consBill.getRejectingList(postid);
		objectArgs.put("rejectList", rejectList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("rejectApproveConsolidatedBill");
		return objRes;

	}

	public ResultObject rejectConsolidatedBill(Map objectArgs) throws Exception
	{
		//logger.info("Entering into rejectConsolidatedBill");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		String billid = StringUtility.getParameter("billid", request).trim();
		ConsolidatedBillLvlMpgDaoImpl consBill = new ConsolidatedBillLvlMpgDaoImpl(ConsolidatedBillLvlMpgMst.class, serviceLocator.getSessionFactory());
		//String billid = StringUtility.getParameter("billid", request).trim();
		consBill.rejectBill(billid);
		CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serviceLocator.getSessionFactory());
		List<CmnLookupMst> MonthlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Month", langId);
		List<CmnLookupMst> YearLookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Year", langId);
		ZpReportingDAO ZpReportingDAOImpl = new ZpReportingDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		List schemeList = ZpReportingDAOImpl.getSchemeCodeByPost(postid);
		objectArgs.put("month", MonthlookUpList);
		objectArgs.put("year", YearLookUpList);
		objectArgs.put("scheme", schemeList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("searchConsolidateBill");
		return objRes;

	}
	public ResultObject isApprovable(Map objectArgs) throws Exception
	{
		//logger.info("Entering into isconsBillGenerated");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillMpgDaoImpl consBill = new ConsolidatedBillMpgDaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String billNo = StringUtility.getParameter("billNo", request).trim();
		String month = StringUtility.getParameter("month", request).trim();
		String year = StringUtility.getParameter("year", request).trim();
		//paybillId = (paybillId.equals("")) ? "0" : paybillId;
		int status = consBill.getStatusOfBill(billNo,month,year);
		StringBuilder lStrBldXML = new StringBuilder();
		String msg="";
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<isGenerated>");
		if(status==1)
			lStrBldXML.append("yes");
		else
			lStrBldXML.append("no");
		lStrBldXML.append("</isGenerated>");
		lStrBldXML.append("</XMLDOC>");
		String lSBStatus = lStrBldXML.toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		logger.info("********************************************" + lStrResult);
		objectArgs.put("ajaxKey", lStrResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;

	}

	public ResultObject isDeletable(Map objectArgs) throws Exception
	{
		//logger.info("Entering into isconsBillGenerated");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillMpgDaoImpl consBill = new ConsolidatedBillMpgDaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String billNo = StringUtility.getParameter("billNo", request).trim();
		String month = StringUtility.getParameter("month", request).trim();
		String year = StringUtility.getParameter("year", request).trim();
		//paybillId = (paybillId.equals("")) ? "0" : paybillId;
		int status = consBill.isDeleteable(billNo,month,year);
		StringBuilder lStrBldXML = new StringBuilder();
		String msg="";
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<isGenerated>");
		if(status==1 )
			lStrBldXML.append("yes");
		else
			lStrBldXML.append("no");
		lStrBldXML.append("</isGenerated>");
		lStrBldXML.append("</XMLDOC>");
		String lSBStatus = lStrBldXML.toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		logger.info("********************************************" + lStrResult);
		objectArgs.put("ajaxKey", lStrResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;

	}
	public ResultObject popUpDtls(Map objectArgs) throws Exception
	{
		//logger.info("Entering into getDDODtls");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		ReportingDDODaoImpl rd = new ReportingDDODaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String ddoCode = StringUtility.getParameter("ddoCode", request).trim();
		String officeName=rd.getDDoDtls(ddoCode);
		String dsgDtl=rd.getDsgDtls(ddoCode);
		if(dsgDtl.equals("1;") || dsgDtl.equals("1")){
			dsgDtl="1";
			Long dsg=Long.valueOf(dsgDtl);
			dsgDtl=(dsg<=2)?"BEO":"Supritendent";
		}
		StringBuilder lStrBldXML = new StringBuilder();
		String msg="";
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<officeName>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(officeName);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</officeName>");
		lStrBldXML.append("<dsgDtl>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(dsgDtl);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</dsgDtl>");
		lStrBldXML.append("</XMLDOC>");
		String lSBStatus = lStrBldXML.toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		logger.info("********************************************" + lStrResult);
		objectArgs.put("ajaxKey", lStrResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;

	}


	public ResultObject generateReportingDDOCode(Map objectArgs) throws Exception
	{
		//logger.info("Entering into getTaluka of ReportingDDOservice");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String strAdminOfc = StringUtility.getParameter("cmbAdminDept", request).trim();
		String lLngFieldDept = StringUtility.getParameter("cmbFieldDept", request).trim(); // TODO -- It will Change in future

		//added by samadhan
		String treasuryId = StringUtility.getParameter("treasuryId", request).trim();

		ReportingDDODao treasuryDao = new ReportingDDODaoImpl(CmnLocationMst.class, serviceLocator.getSessionFactory());
		List ddoCodeList=treasuryDao.getDDounderDept(strAdminOfc,lLngFieldDept,treasuryId);
		logger.info("cmntreasuryMstList:::" + ddoCodeList.size());

		List<ComboValuesVO> lLstAllDept = new ArrayList<ComboValuesVO>();
		Map result = new HashMap();
		ComboValuesVO cmbVO = new ComboValuesVO();
		cmbVO.setId("-1");
		cmbVO.setDesc("Select");
		lLstAllDept.add(cmbVO);

		if (ddoCodeList != null && ddoCodeList.size() > 0)
		{
			Iterator IT = ddoCodeList.iterator();

			cmbVO = new ComboValuesVO();
			Object lObj[] = null;
			while (IT.hasNext())
			{
				cmbVO = new ComboValuesVO();
				lObj = (Object [])IT.next();
				cmbVO.setId(lObj[0].toString());
				cmbVO.setDesc(lObj[0].toString()+" ( "+lObj[1].toString()+" )");
				lLstAllDept.add(cmbVO);
			}
		}

		String AjaxResult = new AjaxXmlBuilder().addItems(lLstAllDept, "desc", "id").toString();
		result.put("ajaxKey", AjaxResult);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(result);
		objRes.setViewName("ajaxData");
		return objRes;
	}

	/*public ResultObject populateDesgOffc(Map objectArgs) throws Exception
	{
		logger.info("Entering into populateDesgOffc");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		ReportingDDODaoImpl rd = new ReportingDDODaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String ddoCode = StringUtility.getParameter("cmbDdoCode", request).trim();
		List ddoDetls=rd.getDesgnOffc(ddoCode);

		String offcName="";
		String DsgnName="";
		if(ddoDetls!=null)
		{

		}
		StringBuilder lStrBldXML = new StringBuilder();
		String msg="";
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<officeName>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(officeName);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</officeName>");
		lStrBldXML.append("<dsgDtl>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(dsgDtl);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</dsgDtl>");
		lStrBldXML.append("</XMLDOC>");
		String lSBStatus = lStrBldXML.toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		logger.info("********************************************" + lStrResult);
		objectArgs.put("ajaxKey", lStrResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;


	}*/

	public ResultObject rejectPaybillByLevelTwo(Map objectArgs) throws Exception
	{
		//logger.info("Entering into rejectPaybillByLevelTwo");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		try{
			Long langId = 1l;
			ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			String billNo = StringUtility.getParameter("paybillId", request).trim();
			long month = Long.parseLong(StringUtility.getParameter("hdMonth", request).trim());
			long year = Long.parseLong(StringUtility.getParameter("hdYear", request).trim());
			String schemeCode = StringUtility.getParameter("schemeCode", request).trim();
			String subSchemeCode = !StringUtility.getParameter("subSchemeCode",request).equalsIgnoreCase("") ? StringUtility.getParameter("subSchemeCode",request) : "-";
			String[] ddoCodeArray  = request.getParameterValues("ddoList");
			String ddoCode="";
			if (ddoCodeArray != null) 
			{
				for (int i = 0; i < ddoCodeArray.length; i++) 
				{
					if(i==0)
						ddoCode=ddoCodeArray[i];
					else
						ddoCode=ddoCode+","+ddoCodeArray[i];
				}
			}

			ZpReportingDAO zpReportingDAOImpl = new ZpReportingDAOImpl(ZpReportingDAOImpl.class,serviceLocator.getSessionFactory());

			int updatePaybillHeadMpg= zpReportingDAOImpl.rejectPaybillByLevelTwo(billNo,month,year);

			List DDOLST = zpReportingDAOImpl.getReptDDOBillDtls(ddoCode, month, year,schemeCode,subSchemeCode);
			if(DDOLST!=null && DDOLST.size() > 0){
				logger.info("IFFFFFF DDOLST::::"+DDOLST.size());
				objectArgs.put("DDOLST",DDOLST);
			}
			logger.info("DDOLST::::"+DDOLST.size());
			String paybillId="0";
			if(DDOLST != null && DDOLST.size() > 0){
				Iterator IT = DDOLST.iterator();

				Object[] lObj = null;
				while(IT.hasNext()){
					lObj = (Object[]) IT.next();
					String temp=lObj[5].toString();
					paybillId=paybillId+","+temp;



				}
			}
			objectArgs.put("paybillId", billNo);
			objectArgs.put("Month", month);
			objectArgs.put("Year", year);
			objectArgs.put("schemecode", schemeCode);

			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ZpReportingDDOSummary");

		}
		catch(Exception e)
		{		
			logger.info("Exception Ocuures...rejectPaybillByLevelTwo");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");		

		}

		return objRes;
	}


	//public ResultObject fwdBillToLvlTwo(Map objectArgs) throws Exception
	public ResultObject forwardBillToLvlTwo(Map objectArgs) throws Exception
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");			
			int msg = 0;
			String billNo = StringUtility.getParameter("billNo", request).trim();
			int month = Integer.parseInt(StringUtility.getParameter("month", request).trim());
			String year = StringUtility.getParameter("year", request).trim();

			ReportingDDODaoImpl reportingDDODaoImpl = new ReportingDDODaoImpl(ReportingDDODaoImpl.class, serv.getSessionFactory());



			//Added By ROshan to check duplicate bill
			Long count=reportingDDODaoImpl.getBillCount(billNo,month,year);
			logger.info("count is ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+count);
			if(count==1) {                
				int updatePaybillHeadMpg= reportingDDODaoImpl.updatePaybillStatus(billNo,month,year);

			}






			String paybillTypeId = rb.getString("paybillTypeId").toString();// paybillType Id from Property File.	

			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			TokenNumberDAOImpl TokenDAO = new TokenNumberDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
			TokenNumberCustomVO customVO = new TokenNumberCustomVO();
			List listedData = new ArrayList();	
			SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
			String billStatus = "";
			if(voToService != null)
			{
				if(voToService.get("billtype")!=null)
					paybillTypeId = (String)voToService.get("billtype").toString();
				if(voToService.get("billStatus")!=null)
					billStatus = (String)voToService.get("billStatus").toString();
			}
			billNo=null;
			logger.info(":::>>>>In service class month = " + month + "  and Year = " + year + " and Bill Number = " + billNo + " & billtypeId = " + paybillTypeId + " billStatus = " + billStatus+"::Loc ID :: "+locId);
			List DataList = TokenDAO.getTokenDataforDisplay(month, year, billNo, paybillTypeId,billStatus,locId);

			long id  = 0;
			String schemeCode ="";
			String schemeName   = "";
			String subSchemeCode ="";
			double billGross  = 0;
			double billNetTotal  = 0;
			long billGrpid      = 0;
			String billDescip ="";
			long appFlag=0;
			long grossTotalAmount=0;
			long netTotalAmount=0;


			if( DataList!=null )
			{
				for (int i = 0; i < DataList.size(); i++) 
				{
					customVO = new TokenNumberCustomVO();
					Object rowList[] = (Object[]) DataList.get(i);
					/*
					if(rowList[0] != null && !(rowList[0].toString().trim()).equals(""))
					{
						id = Long.parseLong(rowList[0].toString());
						customVO.setId(id);
					}*/
					if(rowList[0] != null && !(rowList[0].toString().trim()).equals(""))
					{
						schemeCode = rowList[0].toString();
						customVO.setSchemeCode(schemeCode);
					}
					if(rowList[1] != null && !(rowList[1].toString().trim()).equals(""))
					{
						schemeName = rowList[1].toString();
						customVO.setSchemeName(schemeName);
					}

					if(rowList[2] != null && !(rowList[2].toString().trim()).equals(""))
					{
						grossTotalAmount = Long.parseLong(rowList[2].toString());
						customVO.setGrossTotalAmount(grossTotalAmount);
					}

					if(rowList[3] != null && !(rowList[3].toString().trim()).equals(""))
					{
						netTotalAmount = Long.parseLong(rowList[3].toString());
						customVO.setNetTotalAmount(netTotalAmount);
					}
					if(rowList[4] != null && !(rowList[4].toString().trim()).equals(""))
					{
						billGrpid = Long.parseLong(rowList[4].toString());
						customVO.setBillGrpid(billGrpid);
					}
					if(rowList[5] != null && !(rowList[5].toString().trim()).equals(""))
					{
						billDescip = rowList[5].toString();
						customVO.setBillDescip(billDescip);
					}
					
					//Added by saurabh 
					if(rowList[8] != null && !(rowList[8].toString().trim()).equals(""))
					{
						subSchemeCode = rowList[8].toString();
						customVO.setSubSchemeCode(subSchemeCode);
					}
					//Ended by saurabh 
					
					
					if(rowList[6] != null && !(rowList[6].toString().trim()).equals(""))
						appFlag = Long.parseLong(rowList[6].toString());

					if(appFlag==1)
						customVO.setBillStatus("APPROVED");
					//commented by vaibhav tyagi
					/*else if(appFlag==0)
						customVO.setBillStatus("CREATED");*/
					//added by vaibhav tyagi: start
					else if(appFlag==0){
						if(Long.parseLong(rowList[7].toString())==0){
							customVO.setBillStatus("CREATED");
						}
						else if(Long.parseLong(rowList[7].toString())==4){
							customVO.setBillStatus("CONSOLIDATED");
						}

						else{
							customVO.setBillStatus("FORWARDED");
						}
					}
					else if(appFlag==2)
						customVO.setBillStatus("REJECTED");
					//added by vaibhav tyagi: end
					else
						customVO.setBillStatus(" -- ");



					/*System.out.println("===> 1 id :: "+id);
					System.out.println("===> 2 schemeCode :: "+schemeCode);
					System.out.println("===> 3 SchemeName :: "+schemeName);
					System.out.println("===> 4 billGross :: "+billGross);
					System.out.println("===> 5 billNetTotal :: "+billNetTotal);
					System.out.println("===> 6 billGrpid :: "+billGrpid);
					System.out.println("===> 7 billDescip :: "+billDescip);
					System.out.println("===> 8 customVO.getBillStatus() :: "+customVO.getBillStatus());*/
					listedData.add(customVO);
				}//end for
			}//end if
			

			List billList = new ArrayList(); 
			List<MstDcpsBillGroup> BillList = new ArrayList();		
			billList = TokenDAO.getBillListForDisplay(locId);
			for(Iterator itr=billList.iterator();itr.hasNext();)
			{    			
				Object[] row = (Object[])itr.next();
				MstDcpsBillGroup mstDcps = new MstDcpsBillGroup();	
				mstDcps.setDcpsDdoBillGroupId(Long.parseLong(row[0].toString()));
				mstDcps.setDcpsDdoCode((row[1].toString()));		 	
				BillList.add(mstDcps);

			}   

			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
			List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
			logger.info(" bill number from service in view :" + billNo);
			objectArgs.put("monthList", monthList);
			objectArgs.put("yearList", yearList);
			objectArgs.put("curmonth", month);
			objectArgs.put("curyear", year);
			objectArgs.put("DataList", listedData);
			objectArgs.put("BillList",BillList);
			objectArgs.put("curbill", billNo);
			objectArgs.put("billtype", paybillTypeId);


			if(count==1){
				objectArgs.put("msgFwd", "Paybill Forwarded Successfully.");  
			}
			else{
				objectArgs.put("msgFwd", "Paybill can not be forwarded as there is duplicate paybill with the same bill number."); 
			}


			objectArgs.put("msg", "");
			objectArgs.put("currBillStatus", billStatus);
			resObj.setViewName("ViewTokenNumber");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		} 

		catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.info("Token Number Screen Showing Error"+ex.getMessage());
			//System.out.println("Token Number Screen Showing Error"+ex.getMessage());

			logger.error("Token Number Screen Showing Error", ex);

			resObj.setResultCode(-1);
		}
		return resObj;
	}

	//added by abhishek to view consolidated bill details : start
	public ResultObject viewconsBillGenerated(Map objectArgs) throws Exception
	{
		//logger.info("Entering into viewconsBillDetails");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		ConsolidatedBillMstDaoImpl consBill = new ConsolidatedBillMstDaoImpl(ConsolidatedBillMst.class, serviceLocator.getSessionFactory());
		String billid = StringUtility.getParameter("billid", request).trim();
		logger.info("Entering into billid"+billid);
		List details= consBill.viewConsBillDetails(billid);
		logger.info("Entering into details"+details);
		logger.info("Entering into details"+details.size());
		List totalSum= consBill.viewConsBillDetailsSum(billid);
		logger.info("Entering into totalSum"+totalSum);
		logger.info("Entering into totalSum"+totalSum.size());
		objectArgs.put("details", details);
		objectArgs.put("billid", billid);

		objectArgs.put("totalSum", totalSum);
		totalSum = null;
		billid = null;
		details = null;
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("viewConsolidateBillDetails");
		return objRes;
	}

}
