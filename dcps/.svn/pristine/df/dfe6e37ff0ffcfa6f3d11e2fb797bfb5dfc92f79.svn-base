/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Feb 24, 2011		Kapil Devani								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

/**
 * Class Description - 
 *
 *
 * @author Kapil Devani
 * @version 0.1
 * @since JDK 5.0
 * Feb 24, 2011
 */
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import au.id.jericho.lib.html.Logger;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoOfficeDAO;
import com.tcs.sgv.dcps.dao.DdoOfficeDAOImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;

public class DDOOfficeVOGenerator extends ServiceImpl implements
		VOGeneratorService {

	public ResultObject generateMap(Map inputMap) {

		// TODO Auto-generated method stub

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		inputMap.get("requestObj");
		DdoOffice dcpsddoofficevo = null;
		CmnLocationMst lObjCmnLocationMst = new CmnLocationMst();

		try {

			dcpsddoofficevo = generateVOMap(inputMap);
			inputMap.put("DCPSOfficeData", dcpsddoofficevo);

			lObjCmnLocationMst = generateLocVOMap(inputMap);
			inputMap.put("CmnLocationMst", lObjCmnLocationMst);

			objRes.setResultValue(inputMap);

		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
		}
		return objRes;
	}

	public DdoOffice generateVOMap(Map inputMap) {

		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");

		DdoOffice dcpsddoofficevo = new DdoOffice();

		try {
			Long lLngTelNo1 = null;
			Long lLngTelNo2 = null;
			Long lLngFax = null;
			Long LangId = SessionHelper.getLangId(inputMap);
			SessionHelper.getLocationId(inputMap);
			Long DbId = SessionHelper.getDbId(inputMap);
			Long CreatedPostId = SessionHelper.getPostId(inputMap);
			System.out.println("post id:::"+CreatedPostId);
			Long CreatedUserId = SessionHelper.getUserId(inputMap);
			Date CreatedDate = DBUtility.getCurrentDateFromDB();
			Long lLongDdoOfficeId = null;
			ServiceLocator servLoc = (ServiceLocator) inputMap
					.get("serviceLocator");
			DdoOfficeDAO ddoOfficeDAO = new DdoOfficeDAOImpl(DdoOffice.class,
					servLoc.getSessionFactory());

			Integer lIntSaveorUpdateFlag = Integer.parseInt(StringUtility
					.getParameter("saveOrUpdateFlag", request));
			if (lIntSaveorUpdateFlag == 1) {
				if (dcpsddoofficevo == null) {
					dcpsddoofficevo = new DdoOffice();
				}
			}

			if (lIntSaveorUpdateFlag == 2) {

				lLongDdoOfficeId = Long.parseLong(StringUtility.getParameter(
						"ddoOfficeId", request));
				dcpsddoofficevo = (DdoOffice) ddoOfficeDAO
						.read(lLongDdoOfficeId);

			}

			// =============================
			String dcpsDdoCode = StringUtility.getParameter("txtDdoCode",
					request);
			String lStrNameOffice = StringUtility.getParameter(
					"txtNameOfOffice", request);
			String lStrDdoFlag = StringUtility.getParameter("radioButtonDDO",
					request);
			String lStrState = StringUtility.getParameter("cmbState", request);
			String lStrDistrict = StringUtility
					.getParameter("cmbDist", request).trim();

			String lStrTaluka = StringUtility
					.getParameter("cmbTaluka", request);
			String lStrTown = StringUtility.getParameter("txtTown", request);
			String cmbcity = StringUtility.getParameter("cmbcity", request);
			String lStrGrant = StringUtility.getParameter("txtGrant", request);
			
			String lStrVillage = StringUtility.getParameter("txtVillage",
					request);
			String lStrAddress1 = StringUtility.getParameter("txtAddress1",
					request);
			Long lLngPin = Long.parseLong(StringUtility.getParameter("txtPin",
					request));

			String lStrOfficeCityClass = StringUtility.getParameter(
					"cmbOfficeCityClass", request);
			String lStrDicecode = StringUtility.getParameter(
					"txtdiceCode", request);    // added by ketan
			System.out.println("lStrDicecode:::"+lStrDicecode);

			String lStrTelNo1 = StringUtility
					.getParameter("txtTelNo1", request);
			if (lStrTelNo1 != null && !"".equals(lStrTelNo1)) {
				lLngTelNo1 = Long.parseLong(lStrTelNo1);
			}

			String lStrTelNo2 = StringUtility
					.getParameter("txtTelNo2", request).toString();
			if (!("".equals(lStrTelNo2))) {
				lLngTelNo2 = Long.parseLong(lStrTelNo2);
			}

			String lStrFax = StringUtility.getParameter("txtMobileNo", request);
			if (!("".equals(lStrFax))) {
				lLngFax = Long.parseLong(lStrFax);
			}

			String lStrEmail = StringUtility.getParameter("txtEmail", request);
			String lStrTriableArea = StringUtility.getParameter(
					"RadioButtonTriableArea", request);
			String lStrHillyArea = StringUtility.getParameter(
					"RadioButtonHillyArea", request);
			String lStrNaxaliteArea = StringUtility.getParameter(
					"RadioButtonNaxaliteArea", request);
			String UpdateFlag = StringUtility.getParameter(
					"UpdateFlag", request);
			System.out.println("UpdateFlag::::"+UpdateFlag);
			
			
			
			if (UpdateFlag.equalsIgnoreCase("true")) {
				dcpsddoofficevo.setStatusFlag(0l);
			}

			if (lStrNameOffice != null) {
				dcpsddoofficevo.setDcpsDdoOfficeName(lStrNameOffice);
			}
			if (lStrDdoFlag != null) {
				dcpsddoofficevo.setDcpsDdoOfficeDdoFlag(lStrDdoFlag);
			}

			if (lStrState != null) {
				dcpsddoofficevo.setDcpsDdoOfficeState(lStrState);
			}
			if (lStrDistrict != null) {
				dcpsddoofficevo.setDcpsDdoOfficeDistrict(lStrDistrict);
			}
			if (lStrTaluka != null) {
				dcpsddoofficevo.setDcpsDdoOfficeTaluka(lStrTaluka);
			}
			
			System.out.println("setting city in town!"+cmbcity);
			
			if (cmbcity != null) {
				dcpsddoofficevo.setDcpsDdoOfficeTown(cmbcity);
			}
			
			if (lStrGrant != null) {
				dcpsddoofficevo.setDcpsDdoOfficeGrant(lStrGrant);
			}
			
			
			if (lStrVillage != null) {
				dcpsddoofficevo.setDcpsDdoOfficeVillage(lStrVillage);
			}
			if (lStrAddress1 != null) {
				dcpsddoofficevo.setDcpsDdoOfficeAddress1(lStrAddress1);
			}

			if (lLngPin != null) {
				dcpsddoofficevo.setDcpsDdoOfficePin(lLngPin);
			}

			if (lStrOfficeCityClass != null) {
				dcpsddoofficevo.setDcpsDdoOfficeCityClass(lStrOfficeCityClass);
			}
			if (lStrDicecode != null) {
				dcpsddoofficevo.setDiceCode(Long.parseLong(lStrDicecode));  // added by ketan 
			}
			
			if (lLngTelNo1 != null) {
				dcpsddoofficevo.setDcpsDdoOfficeTelNo1(lLngTelNo1);
			}
			if (lLngTelNo2 != null) {
				dcpsddoofficevo.setDcpsDdoOfficeTelNo2(lLngTelNo2);
			}
			if (lLngFax != null) {
				dcpsddoofficevo.setDcpsDdoOfficeFax(lLngFax);
			}
			if (lStrEmail != null) {
				dcpsddoofficevo.setDcpsDdoOfficeEmail(lStrEmail);
			}
			if (lStrTriableArea != null) {
				dcpsddoofficevo.setDcpsDdoOfficeTribalFlag(lStrTriableArea);
			}
			if (lStrHillyArea != null) {
				dcpsddoofficevo.setDcpsDdoOfficeHillyFlag(lStrHillyArea);
			}
			if (lStrNaxaliteArea != null) {
				dcpsddoofficevo
						.setDcpsDdoOfficeNaxaliteAreaFlag(lStrNaxaliteArea);
			}
			
			if (lStrNaxaliteArea != null) {
				dcpsddoofficevo
						.setDcpsDdoOfficeNaxaliteAreaFlag(lStrNaxaliteArea);
			}
			dcpsddoofficevo.setDcpsDdoCode(dcpsDdoCode);
			dcpsddoofficevo.setLangId(LangId);
			
			//lIntSaveorUpdateFlag
			
			Long statusflag = dcpsddoofficevo.getStatusFlag();
			System.out.println("set::update::"+statusflag);
			dcpsddoofficevo.setStatusFlag(0l); 
			 // added by ketan 
			dcpsddoofficevo.setDbId(DbId);
			dcpsddoofficevo.setPostId(CreatedPostId);
			dcpsddoofficevo.setUserId(CreatedUserId);
			dcpsddoofficevo.setCreatedDate(CreatedDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dcpsddoofficevo;
	}

	public CmnLocationMst generateLocVOMap(Map inputMap) {

		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ResourceBundle lObjRsrcBndle = ResourceBundle
				.getBundle("resources/dcps/DCPSConstants");

		CmnLocationMst lObjCmnLocationMstvo = new CmnLocationMst();

		try {
			Long LangId = SessionHelper.getLangId(inputMap);
			Long LocId = SessionHelper.getLocationId(inputMap);
			SessionHelper.getDbId(inputMap);
			Long CreatedPostId = SessionHelper.getPostId(inputMap);
			Long CreatedUserId = SessionHelper.getUserId(inputMap);
			Date CreatedDate = DBUtility.getCurrentDateFromDB();

			StringUtility.getParameter("txtDdoCode", request);
			String lStrNameOffice = StringUtility.getParameter(
					"txtNameOfOffice", request);
			StringUtility.getParameter("radioButtonDDO", request);
			String lStrState = StringUtility.getParameter("cmbState", request);
			String lStrDistrict = StringUtility
					.getParameter("cmbDist", request).trim();

			String lStrTaluka = StringUtility
					.getParameter("cmbTaluka", request);
			StringUtility.getParameter("txtTown", request);
			
			StringUtility.getParameter("txtGrant", request);
			
			StringUtility.getParameter("txtVillage", request);
			String lStrAddress1 = StringUtility.getParameter("txtAddress1",
					request);
			Long lLngPin = Long.parseLong(StringUtility.getParameter("txtPin",
					request));

			StringUtility.getParameter("cmbOfficeCityClass", request);

			String lStrTelNo2 = StringUtility
					.getParameter("txtTelNo2", request).toString();
			String lStrFax = StringUtility.getParameter("txtMobileNo", request);
			if (!(lStrTelNo2.equals(""))) {
				Long.parseLong(lStrTelNo2);
			}
			if (!(lStrFax.equals(""))) {
				Long.parseLong(lStrFax);
			}
			StringUtility.getParameter("txtEmail", request);
			StringUtility.getParameter("RadioButtonTriableArea", request);
			StringUtility.getParameter("RadioButtonHillyArea", request);
			StringUtility.getParameter("RadioButtonNaxaliteArea", request);

			if (lStrNameOffice != null) {

				lObjCmnLocationMstvo.setLocName(lStrNameOffice);
				lObjCmnLocationMstvo.setLocShortName(lStrNameOffice.substring(
						0, 3).toUpperCase());
			}

			if (lStrState != null) {

				lObjCmnLocationMstvo.setLocStateId(Long.parseLong(lStrState));
			}
			if (lStrDistrict != null) {

				lObjCmnLocationMstvo.setLocDistrictId(Long
						.parseLong(lStrDistrict));
			}
			if (lStrTaluka != null) {

				lObjCmnLocationMstvo.setLocTalukaId(Long.parseLong(lStrTaluka));
			}

			if (lStrAddress1 != null) {
				lObjCmnLocationMstvo.setLocAddr1(lStrAddress1);
			}

			if (lLngPin != null) {

				lObjCmnLocationMstvo.setLocPin(lLngPin.toString());
			}

			CmnLanguageMst lObjCmnLanguageMst = new CmnLanguageMst();
			lObjCmnLanguageMst.setLangId(LangId);
			lObjCmnLocationMstvo.setCmnLanguageMst(lObjCmnLanguageMst);

			lObjCmnLocationMstvo.setParentLocId(LocId);

			Long lLngDepartmentId = Long.valueOf(lObjRsrcBndle
					.getString("DCPS.DDODEPARTMENTID"));
			Long lLngLookupId = Long.valueOf(lObjRsrcBndle
					.getString("DCPS.DDOLOOKUPID"));

			CmnLookupMst lObjCmnLookupMst = new CmnLookupMst();
			lObjCmnLookupMst.setLookupId(lLngLookupId);

			lObjCmnLocationMstvo.setCmnLookupMst(lObjCmnLookupMst);
			lObjCmnLocationMstvo.setDepartmentId(lLngDepartmentId);
			lObjCmnLocationMstvo.setStartDate(CreatedDate);
			lObjCmnLocationMstvo.setActivateFlag(1L);
			lObjCmnLocationMstvo.setCreatedByPost(CreatedPostId);
			lObjCmnLocationMstvo.setCreatedBy(CreatedUserId);
			lObjCmnLocationMstvo.setCreatedDate(CreatedDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lObjCmnLocationMstvo;
	}

}
