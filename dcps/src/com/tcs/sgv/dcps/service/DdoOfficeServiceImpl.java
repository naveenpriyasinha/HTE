/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 26, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.derby.tools.sysinfo;
import org.apache.log4j.Logger;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoOfficeDAO;
import com.tcs.sgv.dcps.dao.DdoOfficeDAOImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.ShDDOOfficeMPG;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 26, 2011
 */
public class DdoOfficeServiceImpl extends ServiceImpl implements
		DdoOfficeService {

	/* Global Variable for PostId */
	Long gLngPostId = null;
	String gStrPostId = null;
    Long LocationId =null;
	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

	private final static Logger gLogger = Logger
			.getLogger(DdoOfficeServiceImpl.class);

	private void setSessionInfo(Map inputMap) throws Exception {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLngPostId = SessionHelper.getPostId(inputMap);
			LocationId = SessionHelper.getLocationId(inputMap);
			System.out.println("loca::"
					+LocationId);
			System.out.println("loca::"
					+gLngPostId);
			
			System.out.println("hiiiiiiiiiiiiiii");
			
			
			gStrPostId = gLngPostId.toString();

		} catch (Exception e) {
			gLogger
					.error("Error in setsessionifo of DDOOfficeServiceImpl  ",
							e);
			throw e;
		}
	}

	public ResultObject loadDdoOffice(Map inputMap) throws Exception 
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrDdoOffice = "Yes";

		try {
			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(null, serv
					.getSessionFactory());

			/* Gets List of all states */
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			List lLstDistricts = lObjDcpsCommonDAO.getDistricts(15L);

			/* Gets Office Class List */
			List lstOfficeClass = IFMSCommonServiceImpl.getLookupValues(
					"DCPS_OFFICE_CLASS", SessionHelper.getLangId(inputMap),
					inputMap);

			/* Gets DDO Code from Post Id */
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			
			System.out.println("lStrDdoCode ############################# "+lStrDdoCode);
			
			/* Gets All DDO offices for that DDO */
			List lLstSavedOffices = lObjDdoOfficeDAO.getAllOffices(lStrDdoCode);
			Long offID=null;

			System.out.println("333333333333333333");

			if (lLstSavedOffices != null) {
				for (Integer lInt = 0; lInt < lLstSavedOffices.size(); lInt++) 
				{
					Object[] lObjListDdoOffice = (Object[]) lLstSavedOffices
							.get(lInt);
					if (lObjListDdoOffice[2].equals("Yes")) 
					{
						lStrDdoOffice = "No";
					}
					
					if(lInt==0)
						offID=Long.parseLong(lObjListDdoOffice[0].toString());
				}
			}
			
			

			System.out.println("355555555555555555533");
			
			
			if (lStrDdoOffice.equals("Yes")) 
			{
				String lStrDdoOfficeName = lObjDdoOfficeDAO
						.getDefaultDdoOffice(lStrDdoCode);
				inputMap.put("DefaultOffice", lStrDdoOfficeName);

			}

			/* Puts all above values in the Map 
			inputMap.put("lLstDistricts", lLstDistricts);
			inputMap.put("DdoOfficeOrNot", lStrDdoOffice);
			inputMap.put("STATENAMES", lLstState);
			inputMap.put("OFFICECLASSLIST", lstOfficeClass);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("OfficeList", lLstSavedOffices);
			
			resObj.setResultValue(inputMap);
			resObj.setViewName("DCPSDDOOffice");*/
			
			
			DdoOffice DdoOfficeVO = lObjDdoOfficeDAO
					.getDdoOfficeDtls(offID);
			
			Long lStrCurrState = Long.valueOf(DdoOfficeVO.getDcpsDdoOfficeState());
			Long lStrCurrDst = Long.valueOf(DdoOfficeVO.getDcpsDdoOfficeDistrict());

			/* Gets the List of All districts for given State */
			//	List lLstDistricts = lObjDcpsCommonDAO.getDistricts(lStrCurrState);

			/* Gets All Talukas for given district */
			List lLstTalukas = lObjDcpsCommonDAO.getTaluka(lStrCurrDst);
			List lLstcity=lObjDcpsCommonDAO.getCity(lStrCurrDst);
			inputMap.put("lLstcity", lLstcity);
			String flag = StringUtility.getParameter("flag",request);
			System.out.println("flag::::"+flag);
			
			
			
			
			inputMap.put("STATENAMES", lLstState);
			inputMap.put("lLstDistricts", lLstDistricts);
			inputMap.put("lLstTalukas", lLstTalukas);
			inputMap.put("OFFICECLASSLIST", lstOfficeClass);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("OfficeList", lLstSavedOffices);
			inputMap.put("DdoOfficeVO", DdoOfficeVO);
			
			
		
				resObj.setViewName("DCPSDDOOffice");
		
			
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setViewName("errorPage");
		}
		
		return resObj;
	}
	
	

	public ResultObject saveDdoOffice(Map<String, Object> inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

		Boolean lBFlag = null;

		try {
			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(
					DdoOffice.class, serv.getSessionFactory());
			DdoOfficeDAO shDAOobj = new DdoOfficeDAOImpl(
					ShDDOOfficeMPG.class, serv.getSessionFactory());
			
			DdoOffice dcpsddoofficevo = (DdoOffice) inputMap
					.get("DCPSOfficeData");
			CmnLocationMst CmnLocationMstvo = (CmnLocationMst) inputMap
					.get("CmnLocationMst");
			ShDDOOfficeMPG  shDDOOfficempgVO =new ShDDOOfficeMPG();
			lBFlag = false;

			Integer.parseInt(StringUtility.getParameter("saveOrUpdateFlag",
					request));
			
			/* Creates Office */

			/* Insert the office data in cmn_location_mst */
			Long lLngLocId = IFMSCommonServiceImpl.getNextSeqNumByCount(
					"cmn_location_mst", inputMap);
			String lLngLocCode = lLngLocId.toString();
			CmnLocationMstvo.setLocId(lLngLocId);
			CmnLocationMstvo.setLocationCode(lLngLocCode);
			
			lObjDdoOfficeDAO.create(CmnLocationMstvo);
			/* END:Insert the office data in cmn_location_mst */

			Long lLngOfficeId = IFMSCommonServiceImpl.getNextSeqNum(
					"MST_DCPS_DDO_OFFICE", inputMap);
			dcpsddoofficevo.setDcpsDdoOfficeIdPk(lLngOfficeId);
			
			//added by ketan
			Long pkforSH = IFMSCommonServiceImpl.getNextSeqNum(
					"SH_DDO_OFFICE_RQT_MPG", inputMap);
			
			shDDOOfficempgVO.setIdPk(pkforSH);
			shDDOOfficempgVO.setOfficeId(lLngOfficeId);
			shDDOOfficempgVO.setReportPostid(lObjDdoOfficeDAO.getrltZpDDOPostId((LocationId)));
			shDAOobj.create(shDDOOfficempgVO);
			//ended by ketan 
			/* Inserting the logged in location for payroll */
			Long lLngLoggedLoc = SessionHelper.getLocationId(inputMap);
			dcpsddoofficevo.setLocId(lLngLoggedLoc);
			lObjDdoOfficeDAO.create(dcpsddoofficevo);
			lBFlag = true;

			String lStrDdoOffice = dcpsddoofficevo.getDcpsDdoOfficeDdoFlag();
			String lStrDdoCode = dcpsddoofficevo.getDcpsDdoCode();
			String lStrOfficeName = dcpsddoofficevo.getDcpsDdoOfficeName();
			if (lStrDdoOffice.equalsIgnoreCase("YES")) {
				lObjDdoOfficeDAO.updateDdoOffice(lStrOfficeName, lStrDdoCode);
			}

		} catch (Exception e) {

			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

		/* Generates the XML response and sends the success flag */
		String lSBStatus = getResponseXMLDoc(lBFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	public ResultObject updateDdoOffice(Map<String, Object> inputMap)throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Boolean lBFlag = null;

		try {
			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			
			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(DdoOffice.class, serv.getSessionFactory());
			
			
			DdoOffice dcpsddoofficevo = (DdoOffice) inputMap.get("DCPSOfficeData");
			DdoOfficeDAO shDAOobj = new DdoOfficeDAOImpl(ShDDOOfficeMPG.class, serv.getSessionFactory());
			ShDDOOfficeMPG  shDDOOfficempgVO =new ShDDOOfficeMPG();
			lBFlag = false;
			dcpsddoofficevo.setDcpsDdoOfficeDdoFlag("YES");
			lObjDdoOfficeDAO.update(dcpsddoofficevo);
			
			
			lBFlag = true;

			String lStrDdoOffice = dcpsddoofficevo.getDcpsDdoOfficeDdoFlag();
			String lStrDdoCode = dcpsddoofficevo.getDcpsDdoCode();
			String lStrOfficeName = dcpsddoofficevo.getDcpsDdoOfficeName();
			
			
			// Added by Mayuresh
			Long lstrLocationCode = dcpsddoofficevo.getLocId();
			System.out.println("lstrLocationCode ************"+lstrLocationCode);
			
			
			if (lStrDdoOffice.equalsIgnoreCase("YES"))
			{
				lObjDdoOfficeDAO.updateDdoOffice777(lStrOfficeName, lStrDdoCode, lstrLocationCode);

			}
			
			// Ended
			//for update added by ketan
			Long pkforSH = IFMSCommonServiceImpl.getNextSeqNum("SH_DDO_OFFICE_RQT_MPG", inputMap);
			System.out.println("pk for Dcps update SRVC:::"+dcpsddoofficevo.getDcpsDdoOfficeIdPk());
			System.out.println("gStrPostId for update SRVC:::"+gStrPostId);
			shDDOOfficempgVO.setIdPk(pkforSH);
			shDDOOfficempgVO.setOfficeId(dcpsddoofficevo.getDcpsDdoOfficeIdPk());
			shDDOOfficempgVO.setReportPostid(lObjDdoOfficeDAO.getrltZpDDOPostId((LocationId)));
			shDAOobj.create(shDDOOfficempgVO);
			//ended by ketan 
			
			
		} catch (Exception e) {

			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

		/* Generates the XML response and sends the success flag */
		String lSBStatus = getResponseXMLDoc(lBFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	public ResultObject popUpDdoOfficeDtls(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		Long lLongDdoOfficeId = null;

		try {
			/* Added by ketan Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(
					DdoOffice.class, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(
					DdoOffice.class, serv.getSessionFactory());

			Long lStrCurrState = -1l;
			Long lStrCurrDst = -1l;

			/*
			 * Gets DDO Office Id from request and gets details of that DDO
			 * Office
			 */
			lLongDdoOfficeId = Long.valueOf(StringUtility.getParameter(
					"ddoOfficeId", request));
			DdoOffice DdoOfficeVO = lObjDdoOfficeDAO
					.getDdoOfficeDtls(lLongDdoOfficeId);
			
			

			/* Gets all states */
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);

			/* Gets State Id and district Id from VO */
			lStrCurrState = Long.valueOf(DdoOfficeVO.getDcpsDdoOfficeState());
			lStrCurrDst = Long.valueOf(DdoOfficeVO.getDcpsDdoOfficeDistrict());

			/* Gets the List of All districts for given State */
			List lLstDistricts = lObjDcpsCommonDAO.getDistricts(lStrCurrState);

			/* Gets All Talukas for given district */
			List lLstTalukas = lObjDcpsCommonDAO.getTaluka(lStrCurrDst);

			/* Gets Office Class List */
			List lstOfficeClass = IFMSCommonServiceImpl.getLookupValues(
					"DCPS_OFFICE_CLASS", SessionHelper.getLangId(inputMap),
					inputMap);

			/* Gets the DDO Code from Post Id */
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			//String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			
			System.out.println("gLngPostId **************** "+gLngPostId);
			//System.out.println("lStrDdoCode **************** "+lStrDdoCode);
			
			/* Gets All DDO Offices for that DDO */
			List lLstSavedOffices = lObjDdoOfficeDAO.getAllOffices(lStrDdoCode);
			

			String flag = StringUtility.getParameter("flag",request);
			System.out.println("flag::::"+flag);
			
			
			List lLstcity=lObjDcpsCommonDAO.getCity(lStrCurrDst);
			inputMap.put("lLstcity", lLstcity);
			inputMap.put("STATENAMES", lLstState);
			inputMap.put("lLstDistricts", lLstDistricts);
			inputMap.put("lLstTalukas", lLstTalukas);
			inputMap.put("OFFICECLASSLIST", lstOfficeClass);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("OfficeList", lLstSavedOffices);
			inputMap.put("DdoOfficeVO", DdoOfficeVO);
			
			
			if(flag.equalsIgnoreCase("Approve")){
				inputMap.put("officeId", lLongDdoOfficeId);
				//code to find ddo code by roshan
				
		       	Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
		   		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
		   		gLogger.info("locId roshan  "+locId);
		   		
		   		PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		   		List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
		   		OrgDdoMst ddoMst  = null;
		   		if(ddoList!=null && ddoList.size()>0) {
				 ddoMst = ddoList.get(0);
		   		}
			
		   		String ddoCode = null;
		   		if(ddoMst!=null){
		   			ddoCode = ddoMst.getDdoCode();
		   		}
		   		gLogger.info("ddo code is found as "+ddoCode);
		   		String talukaId=null;
		   		String ddoSelected=null;
	    	   
	    	   
	    	   if((StringUtility.getParameter("taluka", request)!=null)&&(StringUtility.getParameter("taluka", request)!="")&&(Long.parseLong(StringUtility.getParameter("taluka", request))!=-1)){
	    	   talukaId= StringUtility.getParameter("taluka", request);
	    	   }
	    	   
	    	   if((StringUtility.getParameter("ddoCode", request)!=null)&&(StringUtility.getParameter("ddoCode", request)!="")){
	    	   ddoSelected= StringUtility.getParameter("ddoCode", request);
	    	   }
		   		//code to find the district
		   		String districtID=lObjDdoOfficeDAO.districtName(ddoCode);
		   		gLogger.info("district id found is"+districtID);
		   		//code to find the taluka
		   		List talukaList=lObjDdoOfficeDAO.allTaluka(districtID);
		   		inputMap.put("talukaList", talukaList);
		   		inputMap.put("talukaId", talukaId);
		   		inputMap.put("ddoSelected", ddoSelected);
				inputMap.put("officeId", lLongDdoOfficeId);
				List lstapproveDDOOfficeData = lObjDdoOfficeDAO.getApproveDdoOffice(gLngPostId,talukaId,ddoSelected);
				inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
				resObj.setViewName("ApproveDDOOffice");
				
			}else{
				resObj.setViewName("DCPSDDOOffice");
				gLogger.info("Find the jsp ");
			}
			resObj.setResultValue(inputMap);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is:" + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	private StringBuilder getResponseXMLDoc(boolean flag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	
	/*public ResultObject approveDdoOfficeData(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrDdoOffice = "Yes";

		try {
			 Added by ketan Sets the Session Information 
			setSessionInfo(inputMap);
			System.out.println("approveDdoOfficeData::::::::::::method Call:::");
			 Initializes the DAOs and variables 
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(null, serv
					.getSessionFactory());

			 Gets List of all states 
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			List lLstDistricts = lObjDcpsCommonDAO.getDistricts(15L);

			 Gets Office Class List 
			List lstOfficeClass = IFMSCommonServiceImpl.getLookupValues(
					"DCPS_OFFICE_CLASS", SessionHelper.getLangId(inputMap),
					inputMap);

			 Gets DDO Code from Post Id 
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			
			System.out.println("lStrDdoCode **************** "+lStrDdoCode);
			
			System.out.println("login :::post id:::::"+gLngPostId);
			 Gets All DDO offices for that DDO 
			List lLstSavedOffices = lObjDdoOfficeDAO.getAllOffices(lStrDdoCode);

			if (lLstSavedOffices != null) {
				for (Integer lInt = 0; lInt < lLstSavedOffices.size(); lInt++) {
					Object[] lObjListDdoOffice = (Object[]) lLstSavedOffices
							.get(lInt);
					if (lObjListDdoOffice[2].equals("Yes")) {
						lStrDdoOffice = "No";
					}
				}
			}
			
			List lstapproveDDOOfficeData = lObjDdoOfficeDAO.getApproveDdoOffice((gLngPostId));
			if(lstapproveDDOOfficeData!=null && lstapproveDDOOfficeData.size()>0)
			{
				inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			}

			 Puts all above values in the Map 
			inputMap.put("lLstDistricts", lLstDistricts);
			inputMap.put("DdoOfficeOrNot", lStrDdoOffice);
			inputMap.put("STATENAMES", lLstState);
			inputMap.put("OFFICECLASSLIST", lstOfficeClass);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("OfficeList", lLstSavedOffices);
			inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			
			resObj.setResultValue(inputMap);
			resObj.setViewName("ApproveDDOOffice");
			
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setViewName("errorPage");
		}
		
		return resObj;
	}	*/
	//added ny mayuresh
	public ResultObject approveDdoOfficeData(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrDdoOffice = "Yes";

		try {
			/* Added by ketan Sets the Session Information */
			setSessionInfo(inputMap);
			System.out.println("approveDdoOfficeData::::::::::::method Call:::");
			/* Initializes the DAOs and variables */
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(null, serv
					.getSessionFactory());

			/* Gets List of all states */
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			List lLstDistricts = lObjDcpsCommonDAO.getDistricts(15L);

			/* Gets Office Class List */
			List lstOfficeClass = IFMSCommonServiceImpl.getLookupValues(
					"DCPS_OFFICE_CLASS", SessionHelper.getLangId(inputMap),
					inputMap);

			/* Gets DDO Code from Post Id */
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			System.out.println("login :::post id:::::"+gLngPostId);
			/* Gets All DDO offices for that DDO */
			List lLstSavedOffices = lObjDdoOfficeDAO.getAllOffices(lStrDdoCode);

			if (lLstSavedOffices != null) {
				for (Integer lInt = 0; lInt < lLstSavedOffices.size(); lInt++) {
					Object[] lObjListDdoOffice = (Object[]) lLstSavedOffices
							.get(lInt);
					if (lObjListDdoOffice[2].equals("Yes")) {
						lStrDdoOffice = "No";
					}
				}
			}
			//added by roshan
			 //code to find ddo code by roshan
			
	       	Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
	   		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
	   		gLogger.info("locId roshan  "+locId);
	   		
	   		PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	   		List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
	   		OrgDdoMst ddoMst  = null;
	   		if(ddoList!=null && ddoList.size()>0) {
			 ddoMst = ddoList.get(0);
	   		}
		
	   		String ddoCode = null;
	   		if(ddoMst!=null){
	   			ddoCode = ddoMst.getDdoCode();
	   		}
	   		gLogger.info("ddo code is found as "+ddoCode);
	   		String talukaId=null;
	   		String ddoSelected=null;
    	   
    	   
    	   if((StringUtility.getParameter("taluka", request)!=null)&&(StringUtility.getParameter("taluka", request)!="")&&(Long.parseLong(StringUtility.getParameter("taluka", request))!=-1)){
    	   talukaId= StringUtility.getParameter("taluka", request);
    	   }
    	   
    	   if((StringUtility.getParameter("ddoCode", request)!=null)&&(StringUtility.getParameter("ddoCode", request)!="")){
    	   ddoSelected= StringUtility.getParameter("ddoCode", request);
    	   }
	   		//code to find the district
	   		String districtID=lObjDdoOfficeDAO.districtName(ddoCode);
	   		gLogger.info("district id found is"+districtID);
	   		//code to find the taluka
	   		List talukaList=lObjDdoOfficeDAO.allTaluka(districtID);
	   		inputMap.put("talukaList", talukaList);
	   		inputMap.put("talukaId", talukaId);
	   		inputMap.put("ddoSelected", ddoSelected);
			//end by roshaN
			List lstapproveDDOOfficeData = lObjDdoOfficeDAO.getApproveDdoOffice(gLngPostId,talukaId,ddoSelected);
			if(lstapproveDDOOfficeData!=null && lstapproveDDOOfficeData.size()>0)
			{
				inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			}
			//String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
		
			/* Puts all above values in the Map */
			inputMap.put("lLstDistricts", lLstDistricts);
			inputMap.put("DdoOfficeOrNot", lStrDdoOffice);
			inputMap.put("STATENAMES", lLstState);
			inputMap.put("OFFICECLASSLIST", lstOfficeClass);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("OfficeList", lLstSavedOffices);
			inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			
			resObj.setResultValue(inputMap);
			resObj.setViewName("ApproveDDOOffice");
			
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setViewName("errorPage");
		}
		return resObj;
	}	
	

	
	
	public ResultObject ApprovedDDOOffice(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrDdoOffice = "Yes";
		Long lLongDdoOfficeId = null;
		String dcpsDdoOfficeDistrict = null;
		String taluka = null;
		String town = null;
		String village = null;
		String address1 = null;
		Long pin = null;
		String cityclass = null;
		String txtcode = null;
		Long txtTelNo1 = null;
		Long txtTelNo2 = null;
		Long txtMobileNo = null;
		String txtEmail = null;
		String RadioButtonTriableArea = null;
		String grant = null;
		
		
		try {
			/* Added by ketan Sets the Session Information */
			setSessionInfo(inputMap);
			System.out.println("ApprovedDDOOffice::::::::::::method Call:::");
			/* Initializes the DAOs and variables */
			

			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(
								DdoOffice.class, serv.getSessionFactory());	


			lLongDdoOfficeId = Long.valueOf(StringUtility.getParameter("ddoOfficeId", request));
			
			System.out.println("lLongDdoOfficeId::::"+lLongDdoOfficeId);
			DdoOffice DdoOfficeVO = lObjDdoOfficeDAO.getDdoOfficeDtls(lLongDdoOfficeId);
			//code to find ddo code by roshan
			
	       	Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
	   		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
	   		gLogger.info("locId roshan  "+locId);
	   		
	   		PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	   		List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
	   		OrgDdoMst ddoMst  = null;
	   		if(ddoList!=null && ddoList.size()>0) {
			 ddoMst = ddoList.get(0);
	   		}
		
	   		String ddoCode = null;
	   		if(ddoMst!=null){
	   			ddoCode = ddoMst.getDdoCode();
	   		}
	   		gLogger.info("ddo code is found as "+ddoCode);
	   		String talukaId=null;
	   		String ddoSelected=null;
    	   
    	   
    	   if((StringUtility.getParameter("taluka", request)!=null)&&(StringUtility.getParameter("taluka", request)!="")&&(Long.parseLong(StringUtility.getParameter("taluka", request))!=-1)){
    	   talukaId= StringUtility.getParameter("taluka", request);
    	   }
    	   
    	   if((StringUtility.getParameter("ddoCode", request)!=null)&&(StringUtility.getParameter("ddoCode", request)!="")){
    	   ddoSelected= StringUtility.getParameter("ddoCode", request);
    	   }
	   		//code to find the district
	   		String districtID=lObjDdoOfficeDAO.districtName(ddoCode);
	   		gLogger.info("district id found is"+districtID);
	   		//code to find the taluka
	   		List talukaList=lObjDdoOfficeDAO.allTaluka(districtID);
	   		inputMap.put("talukaList", talukaList);
	   		inputMap.put("talukaId", talukaId);
	   		inputMap.put("ddoSelected", ddoSelected);
			//end by roshaN
			List lstapproveDDOOfficeData = lObjDdoOfficeDAO.getApproveDdoOffice(gLngPostId,talukaId,ddoSelected);
			
/*			
			dcpsDdoOfficeDistrict = String.valueOf(StringUtility.getParameter("dcpsDdoOfficeDistrict", request));
			
			taluka = String.valueOf(StringUtility.getParameter("taluka", request));
			town = String.valueOf(StringUtility.getParameter("town", request));
			village = String.valueOf(StringUtility.getParameter("village", request));
			address1 = String.valueOf(StringUtility.getParameter("address1", request));
			pin = Long.valueOf(StringUtility.getParameter("pin", request));
			cityclass = String.valueOf(StringUtility.getParameter("cityclass", request));
			txtcode = String.valueOf(StringUtility.getParameter("txtcode", request));
			txtTelNo1 = Long.valueOf(StringUtility.getParameter("txtTelNo1", request));
			txtTelNo2 = Long.valueOf(StringUtility.getParameter("txtTelNo2", request));
			txtMobileNo = Long.valueOf(StringUtility.getParameter("txtMobileNo", request));
			txtEmail = String.valueOf(StringUtility.getParameter("txtEmail", request));
			RadioButtonTriableArea = String.valueOf(StringUtility.getParameter("RadioButtonTriableArea", request));
			grant = String.valueOf(StringUtility.getParameter("grant", request));
			
			inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			inputMap.put("dcpsDdoOfficeDistrict", dcpsDdoOfficeDistrict);
			inputMap.put("taluka", taluka);
			inputMap.put("town", town);
			inputMap.put("village", village);
			inputMap.put("village", village);
			inputMap.put("address1", address1);
			inputMap.put("pin", pin);
			inputMap.put("cityclass", cityclass);
			inputMap.put("txtcode", txtcode);
			inputMap.put("txtTelNo1", txtTelNo1);
			inputMap.put("txtTelNo2", txtTelNo2);
			inputMap.put("txtMobileNo", txtMobileNo);
			inputMap.put("txtEmail", txtEmail);
			inputMap.put("RadioButtonTriableArea", RadioButtonTriableArea);
			inputMap.put("grant", grant);
			*/
			
			DdoOfficeVO.setStatusFlag(1l);// 1 for approve 
			lObjDdoOfficeDAO.update(DdoOfficeVO);
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			

			/* Gets List of all states */
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			List lLstDistricts = lObjDcpsCommonDAO.getDistricts(15L);

			/* Gets Office Class List */
			List lstOfficeClass = IFMSCommonServiceImpl.getLookupValues(
					"DCPS_OFFICE_CLASS", SessionHelper.getLangId(inputMap),
					inputMap);

			/* Gets DDO Code from Post Id */
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			System.out.println("login :::post id:::::"+gLngPostId);
			/* Gets All DDO offices for that DDO */
			List lLstSavedOffices = lObjDdoOfficeDAO.getAllOffices(lStrDdoCode);

			if (lLstSavedOffices != null) {
				for (Integer lInt = 0; lInt < lLstSavedOffices.size(); lInt++) {
					Object[] lObjListDdoOffice = (Object[]) lLstSavedOffices
							.get(lInt);
					if (lObjListDdoOffice[2].equals("Yes")) {
						lStrDdoOffice = "No";
					}
				}
			}
			
			 lstapproveDDOOfficeData = lObjDdoOfficeDAO.getApproveDdoOffice(gLngPostId,talukaId,ddoSelected);
			if(lstapproveDDOOfficeData!=null && lstapproveDDOOfficeData.size()>0)
			{
				inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			}

			inputMap.put("ApproveFlag",lLongDdoOfficeId);
			inputMap.put("lLstDistricts", lLstDistricts);
			inputMap.put("DdoOfficeOrNot", lStrDdoOffice);
			inputMap.put("STATENAMES", lLstState);
			inputMap.put("OFFICECLASSLIST", lstOfficeClass);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("OfficeList", lLstSavedOffices);
			inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ApproveDDOOffice");
			
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setViewName("errorPage");
		}
		
		return resObj;
	}	
//added by samadhan for displaying city class on organisation/office tab on selcting district
	
	public ResultObject displayCityClass(Map objectArgs)
	{
		gLogger.info("inside displayCityClass");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		String city=null;
		String cityClass=null;
		String lStrResult = null;
		try {
			
			setSessionInfo(objectArgs);
			city=StringUtility.getParameter("city", request).trim();
			gLogger.info("--------city--------:"+city);
			
			
			
			//DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(DdoOffice.class, serv.getSessionFactory());	
			
			//String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			cityClass=lObjDdoOfficeDAO.getCityClass(city);
			
			String status=null;

			if(!cityClass.isEmpty()){
				status=cityClass;
			}
			String lSBStatus = getResponseXMLDocForCityClass(status).toString();
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
	private StringBuilder getResponseXMLDocForCityClass(String status) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(status);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
	
	public ResultObject SaveRejectDdoOffice(Map inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrDdoOffice = "Yes";
		String ddoOfficeName=null;
		Long lLongDdoOfficeId = null;
		String dcpsDdoOfficeDistrict = null;
		String taluka = null;
		String town = null;
		String village = null;
		String address1 = null;
		Long pin = null;
		String cityclass = null;
		String txtcode = null;
		Long txtTelNo1 = null;
		Long txtTelNo2 = null;
		Long txtMobileNo = null;
		String txtEmail = null;
		String RadioButtonTriableArea = null;
		String grant = null;
		String rejectionReason=null;


		try {
			/* Added by ketan Sets the Session Information */
			setSessionInfo(inputMap);
			System.out.println("ApprovedDDOOffice::::::::::::method Call:::");
			/* Initializes the DAOs and variables */


			DdoOfficeDAO lObjDdoOfficeDAO = new DdoOfficeDAOImpl(DdoOffice.class, serv.getSessionFactory());	


			lLongDdoOfficeId = Long.valueOf(StringUtility.getParameter("ddoOfficeId", request));

			/*String telNo2=StringUtility.getParameter("mobileNo", request);
			if(telNo2!=null && !telNo2.equals(""))
			{
				txtTelNo2=Long.valueOf(telNo2);
			}*/


			rejectionReason=StringUtility.getParameter("rejectionReason", request);
			ddoOfficeName=StringUtility.getParameter("ddoOfficeName", request);
			//added by vaibhav tyagi: start
			//ddoOfficeName = StringUtility.getParameter("ddoOfficeName", request);
			//added by vaibhav tyagi: start
			//System.out.println("lLongDdoOfficeId::::"+lLongDdoOfficeId);
			DdoOffice DdoOfficeVO = lObjDdoOfficeDAO.getDdoOfficeDtls(lLongDdoOfficeId);
			//added by vaibhav tyagi: start
			//lObjDdoOfficeDAO.updateLocationMst(lLongDdoOfficeId, ddoOfficeName);
			//added by vaibhav tyagi: start

			//added by roshan on 19 april 
			lObjDdoOfficeDAO.updateName(lLongDdoOfficeId);

			//end by roshan on 20 april
			//code to find ddo code by roshan

			Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			gLogger.info("locId roshan  "+locId);

			PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
			OrgDdoMst ddoMst  = null;
			if(ddoList!=null && ddoList.size()>0) {
				ddoMst = ddoList.get(0);
			}

			String ddoCode = null;
			if(ddoMst!=null){
				ddoCode = ddoMst.getDdoCode();
			}
			gLogger.info("ddo code is found as "+ddoCode);
			String talukaId=null;
			String ddoSelected=null;


			if((StringUtility.getParameter("taluka", request)!=null)&&(StringUtility.getParameter("taluka", request)!="")&&(Long.parseLong(StringUtility.getParameter("taluka", request))!=-1)){
				talukaId= StringUtility.getParameter("taluka", request);
			}

			if((StringUtility.getParameter("ddoCode", request)!=null)&&(StringUtility.getParameter("ddoCode", request)!="")){
				ddoSelected= StringUtility.getParameter("ddoCode", request);
			}
			//code to find the district
			String districtID=lObjDdoOfficeDAO.districtName(ddoCode);
			gLogger.info("district id found is"+districtID);
			//code to find the taluka
			List talukaList=lObjDdoOfficeDAO.allTaluka(districtID);
			inputMap.put("talukaList", talukaList);
			inputMap.put("talukaId", talukaId);
			inputMap.put("ddoSelected", ddoSelected);
			//end by roshaN
			List lstapproveDDOOfficeData = lObjDdoOfficeDAO.getApproveDdoOffice(gLngPostId,talukaId,ddoSelected);

			DdoOfficeVO.setStatusFlag(-1l);// -1 for Reject 
			DdoOfficeVO.setReasonForRejection(rejectionReason);// reason for Reject
			lObjDdoOfficeDAO.update(DdoOfficeVO);


			/*//added by samadhan for sms update for rejected office at level 2
			String mobileNo=null;
			if(txtTelNo2!=null)
			{
				mobileNo=txtTelNo2.toString();
			}
			gLogger.info("*********************mobileNo***************"+mobileNo);
			gLogger.info("*********************rejectionReason****************"+rejectionReason);
			gLogger.info("*********************ddoOfficeName***************"+ddoOfficeName);
			SmsGatewayServiceImpl s= new SmsGatewayServiceImpl();
			String respCodes[]=new String[3];
			String msg="The School :"+ddoOfficeName+" is rejected by Level 2 DDO for following reason: '"+rejectionReason+"' in SHALARTH.";
			gLogger.info("msg to be send "+msg);
			if(mobileNo!=null && mobileNo!="")
			{
				gLogger.info("sending message!");
				respCodes=s.sendSms(mobileNo, msg);
			}
			else
			{
				gLogger.info("message not sending because mobileNo is null! mobileNo: "+mobileNo);
			}*/






			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());


			/* Gets List of all states */
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			List lLstDistricts = lObjDcpsCommonDAO.getDistricts(15L);

			/* Gets Office Class List */
			List lstOfficeClass = IFMSCommonServiceImpl.getLookupValues("DCPS_OFFICE_CLASS", SessionHelper.getLangId(inputMap),inputMap);

			/* Gets DDO Code from Post Id */
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			System.out.println("login :::post id:::::"+gLngPostId);
			/* Gets All DDO offices for that DDO */

			List lLstSavedOffices = lObjDdoOfficeDAO.getAllOffices(lStrDdoCode);

			if (lLstSavedOffices != null) 
			{
				for (Integer lInt = 0; lInt < lLstSavedOffices.size(); lInt++) 
				{
					Object[] lObjListDdoOffice = (Object[]) lLstSavedOffices.get(lInt);
					if (lObjListDdoOffice[2].equals("Yes")) 
					{
						lStrDdoOffice = "No";
					}
				}
			}

			lstapproveDDOOfficeData = lObjDdoOfficeDAO.getApproveDdoOffice(gLngPostId,talukaId,ddoSelected);
			if(lstapproveDDOOfficeData!=null && lstapproveDDOOfficeData.size()>0)
			{
				inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			}

			inputMap.put("ApproveFlag",lLongDdoOfficeId);
			inputMap.put("lLstDistricts", lLstDistricts);
			inputMap.put("DdoOfficeOrNot", lStrDdoOffice);
			inputMap.put("STATENAMES", lLstState);
			inputMap.put("OFFICECLASSLIST", lstOfficeClass);
			inputMap.put("DDOCODE", lStrDdoCode);
			inputMap.put("OfficeList", lLstSavedOffices);
			inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ApproveDDOOffice");

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
}