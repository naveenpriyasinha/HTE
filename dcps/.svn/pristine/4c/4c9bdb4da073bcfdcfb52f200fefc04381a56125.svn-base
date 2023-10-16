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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.MstScheme;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoSchemeDAO;
import com.tcs.sgv.dcps.dao.DdoSchemeDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.dcps.valueobject.RltDcpsDdoScheme;
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
public class DdoSchemeServiceImpl extends ServiceImpl implements DdoSchemeService {

	/* Global Variables */
	Long gLngPostId = null;
	private String gStrPostId = null; /* STRING POST ID */
	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");

	private final static Logger gLogger = Logger.getLogger(DdoInfoServiceImpl.class);

	private void setSessionInfo(Map inputMap) throws Exception {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();

		} catch (Exception e) {
			gLogger.error("Error in setSessionInfo of DDOSchemeServiceImpl ", e);
			throw e;
		}
	}

	public ResultObject loadDdoSchemesAndBillGroups(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			DdoSchemeDAO lObjDdoSchemeDAO = new DdoSchemeDAOImpl(RltDcpsDdoScheme.class, serv.getSessionFactory());
			List DcpsDdoSchemeList = null;

			/* Gets DDO Code from Post Id */
			gLogger.info("gLngPostId...."+gLngPostId);;
			//String lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId); commented by saurabh
			String lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);

			
			
			//code to find ddo code by roshan
			Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
	   		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
	   		gLogger.info("locId roshan  "+locId);
	   		PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	   		List<OrgDdoMst> ddoListForScheme = payDAO.getDDOCodeByLoggedInlocId(locId);
	   		OrgDdoMst ddoMst  = null;
	   	
			if(ddoListForScheme!=null && ddoListForScheme.size()>0) {
			 ddoMst = ddoListForScheme.get(0);
	   		}
			
			
			String ddoCode = null;
	   		if(ddoMst!=null)
	   		{
	   			ddoCode = ddoMst.getDdoCode();
	   		}
	   		gLogger.info("hi i am checking DDO CODE whethre it is of ZP institute type" +ddoCode);
			
	   		String Type =ddoCode.substring(0,2);
			gLogger.info("TypeOfSchoolbefore"+Type);
			Long TypeOfSchool=Long.valueOf(Type);
			String typeOfOffice=null;
			if(TypeOfSchool !=2)
	   		{
	   			typeOfOffice="otherThanZp";
	   		}
	   		else 
	   		 {
	   			typeOfOffice="ZP";
	   		}
	   		gLogger.info("ddo code is found as "+ddoCode);
	   		/* Gets list of All schemes operated by the DDO by roshan*/
	   		gLogger.info("hi ddo code found is as "+ddoCode);
	 /*  	//code to find the level by roshan
	   		long level=lObjDdoSchemeDAO.findLevel(ddoCode);
	   		//code to find the user type by roshan
	   		
	   		long countOfDDOCode=lObjDdoSchemeDAO.findUsertype(ddoCode);
	   		String userType=null;
	   		if(countOfDDOCode !=0)
	   		{
	   			userType="reportingDDO";
	   		}
	   		else 
	   		 {
	   			userType="finalDDO";
	   		}
	   		gLogger.info("userType"+userType);
	   		// code To Check whethre new entry is disbale or not
	   		String displayAddNewEntry=null;
	   		if(((typeOfOffice.equalsIgnoreCase("otherThanZp")) && (userType.equalsIgnoreCase("reportingDDO")))||(typeOfOffice.equalsIgnoreCase("ZP"))&&(userType.equalsIgnoreCase("finalDDO"))) 
	   		{
	   			displayAddNewEntry="true";
	   		}
	   		else
	   		{
	   			displayAddNewEntry="false";
	   		}
			gLogger.info("displayAddNewEntry"+displayAddNewEntry);
			*/
	   		/* Gets list of All schemes operated by the DDO */
	   		//modified by saurabh
			//DcpsDdoSchemeList = lObjDdoSchemeDAO.getSchemeListForDDO(gLngPostId.toString());
	   		DcpsDdoSchemeList = lObjDdoSchemeDAO.getAllSchemesForDDO(lStrDDOCode);
			//List ddoList=lObjDcpsCommonDAO.getSubDDOs(SessionHelper.getPostId(inputMap));
	   		
			//code to find the district
	   		String districtID=lObjDcpsCommonDAO.districtName(ddoCode);
	   		gLogger.info("district id found is"+districtID);
	   		//code to find the taluka
	   		List talukaList=lObjDcpsCommonDAO.allTaluka(districtID);
	   		String talukaId=null;
	    	   String ddoSelected=null;
	    	   if((StringUtility.getParameter("taluka", request)!=null)&&(StringUtility.getParameter("taluka", request)!="")&&(Long.parseLong(StringUtility.getParameter("taluka", request))!=-1)){
	    	   talukaId= StringUtility.getParameter("taluka", request);
	    	   }
	    	   
	    	   if((StringUtility.getParameter("ddoCode", request)!=null)&&(StringUtility.getParameter("ddoCode", request)!="")){
	    	   ddoSelected= StringUtility.getParameter("ddoCode", request);
	    	   }
	    	   List ddoList=lObjDcpsCommonDAO.getSubDDOs(SessionHelper.getPostId(inputMap),talukaId,ddoSelected);
				
	    	   
			/* Gets total of all schemes */
			Integer totalRecords = DcpsDdoSchemeList.size();
			List role=lObjDcpsCommonDAO.getpostRole(SessionHelper.getPostId(inputMap));
			Iterator IT = role.iterator();
			Integer o= null;
			String isLvl2= "no";
			while(IT.hasNext()){
				o= (Integer)IT.next();
				if(o.toString().equals("700017") || o.toString().equals("700002"))
					isLvl2="yes";
				
			}
			
			inputMap.put("talukaList", talukaList);
	   		inputMap.put("talukaId", talukaId);
	   		inputMap.put("ddoSelected", ddoSelected);
	   		//inputMap.put("displayAddNewEntry", displayAddNewEntry);
			
			inputMap.put("DDOlist", ddoList);
			inputMap.put("schemelist", DcpsDdoSchemeList);
			inputMap.put("totalRecords", totalRecords);
			inputMap.put("isLvl2", isLvl2);
			
			resObj.setResultValue(inputMap);
			resObj.setViewName("DCPSDdoScheme");

		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

		return resObj;
	}

	public ResultObject displaySchemeNameForCode(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrSchemeCode = null;
		List<MstScheme> lListSchemes = null;
		Integer lNoOfSchemes = 0;

		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DdoSchemeDAO lObjDdoSchemeDAO = new DdoSchemeDAOImpl(RltDcpsDdoScheme.class, serv.getSessionFactory());

			/* Gets SchemeCode from request */
			lStrSchemeCode = StringUtility.getParameter("txtSchemeCode", request);
			
			String asstDdoCode= StringUtility.getParameter("cmbsubDdos", request);
			
			gLogger.info("asstDdoCode::"+asstDdoCode);

			/*
			 * Gets the all the schemeNames whose scheme code starts with given
			 * scheme code and finds total schemes
			 */
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);

			lListSchemes = lObjDdoSchemeDAO.getSchemeNamesFromCode(lStrSchemeCode, lStrDdoCode,asstDdoCode);
			lNoOfSchemes = lListSchemes.size();

			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

		/*
		 * Generates XML response for all schemes found whose scheme code starts
		 * with given scheme code
		 */
		String lSBStatus = getResponseXMLDocToDisplaySchemeName(lNoOfSchemes, lListSchemes).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	public ResultObject addSchemesAndBillGroupsToDdo(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		Boolean lFlagSchemeAddedOrNot = true;
		Boolean lFlagSubSchemeAddedOrNot = true;

		try {
			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DdoSchemeDAO lObjDdoSchemeDAO = new DdoSchemeDAOImpl(RltDcpsDdoScheme.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			RltDcpsDdoScheme lObjDcpsDdoSchemeVO = (RltDcpsDdoScheme) inputMap.get("DcpsDdoScheme");
			MstDcpsBillGroup lObjMstDcpsBillGroupVO = (MstDcpsBillGroup) inputMap.get("dcpsddobillgroup");
			
			RltDcpsDdoScheme[] lObjDcpsDdoSubSchemeVO = (RltDcpsDdoScheme[]) inputMap.get("dcpsddoSubSchme");
			gLogger.info("Inside try lObjDcpsDdoSubSchemeVO "+lObjDcpsDdoSubSchemeVO);
			MstDcpsBillGroup[] lObjMstDcpsBillGroupSubSchemeVO = (MstDcpsBillGroup[]) inputMap.get("dcpsddobillgroupSubScheme");
			lFlagSchemeAddedOrNot = (Boolean) inputMap.get("schemeAddedOrNot");
		
			String lStrDDOCode = StringUtility.getParameter("cmbsubDdos", request);
			List LocIdList = lObjDcpsCommonDAO.getDDODtls(lStrDDOCode);
			
			if (lStrDDOCode != null && (!lStrDDOCode.equals(""))) {
				lObjDcpsDdoSchemeVO.setDcpsDdoCode(lStrDDOCode);
			}
			if (LocIdList != null && (!LocIdList.equals(""))) {
				lObjDcpsDdoSchemeVO.setLocId(Long.parseLong(LocIdList.get(0).toString()));
				lObjMstDcpsBillGroupVO.setLocId(Long.parseLong(LocIdList.get(0).toString()));
			}
			
			//added by saurabh
			if (lObjDcpsDdoSubSchemeVO.length >0 && (lObjDcpsDdoSubSchemeVO!=null))
			{
				gLogger.info("Inside the if of SubSchemeAddedOrNot added or not ");
				lFlagSubSchemeAddedOrNot = (Boolean) inputMap.get("SubSchemeAddedOrNot");
			}
			
			/* Gets next Primary key for RltDcpsDdoScheme VO */
			//Long lDDOSchemesId = IFMSCommonServiceImpl.getNextSeqNum("RLT_DCPS_DDO_SCHEMES", inputMap);

			/* Sets Primary key and creates RltDcpsDdoScheme VO in table */
			//lObjDcpsDdoSchemeVO.setDcpsDdoSchemesId(lDDOSchemesId);
		
			
			if (lFlagSchemeAddedOrNot && lFlagSubSchemeAddedOrNot) {
				
				

				if ((lObjDcpsDdoSubSchemeVO!=null) && lObjDcpsDdoSubSchemeVO.length>0)
				{

					gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO");
					for (int i=0;i<lObjDcpsDdoSubSchemeVO.length;i++)
					{
						gLogger.info(" Inside the for  of lObjDcpsDdoSubSchemeVO");
						//Long lDDOSchemesId = IFMSCommonServiceImpl.getNextSeqNum("RLT_DCPS_DDO_SCHEMES", inputMap);
						//	lObjDcpsDdoSubSchemeVO[i].setDcpsDdoSchemesId(1l);
						lObjDcpsDdoSubSchemeVO[i].setDcpsDdoCode(lStrDDOCode);
						lObjDcpsDdoSubSchemeVO[i].setLocId(Long.parseLong(LocIdList.get(0).toString()));
						lObjDdoSchemeDAO.create(lObjDcpsDdoSubSchemeVO[i]);

						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getDcpsDdoCode());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getDcpsSchemeCode());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getDcpsSubSchemeCode());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getCreatedDate());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getCreatedPostId());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getCreatedUserId());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getDbId());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getDcpsDdoSchemesId());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getLangId());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getLocId());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getUpdatedDate());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getUpdatedPostId());
						gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjDcpsDdoSubSchemeVO[i].getUpdatedUserId());


						lBlFlag = true;
						gLogger.info(" after creation lObjDcpsDdoSubSchemeVO");
					}
				}

				else
				{

					gLogger.info(" Inside the else  of lObjDcpsDdoSubSchemeVO");
					Long lDDOSchemesId = IFMSCommonServiceImpl.getNextSeqNum("RLT_DCPS_DDO_SCHEMES", inputMap);
					lObjDcpsDdoSchemeVO.setDcpsDdoCode(lStrDDOCode);
					lObjDcpsDdoSchemeVO.setLocId(Long.parseLong(LocIdList.get(0).toString()));
					lObjDcpsDdoSchemeVO.setDcpsDdoSchemesId(lDDOSchemesId);
					lObjDdoSchemeDAO.create(lObjDcpsDdoSchemeVO);
					lBlFlag = true;
				}
				
				
				
				
				
				
				
				/*lObjDdoSchemeDAO.create(lObjDcpsDdoSchemeVO);
				lBlFlag = true;*/
			}

			/* Gets next Primary key for MstDcpsBillGroup VO */
			//Long lLngBillGroupId = IFMSCommonServiceImpl.getNextSeqNum("mst_dcps_bill_group", inputMap);

			/* Sets Primary key and creates MstDcpsBillGroup VO in table */
			//lObjMstDcpsBillGroupVO.setDcpsDdoBillGroupId(lLngBillGroupId);
			//lObjMstDcpsBillGroupVO.setSubBGOrNot(0l);
			//lObjMstDcpsBillGroupVO.setDcpsDdoCode(lStrDDOCode);
			
			if (lFlagSchemeAddedOrNot && lFlagSubSchemeAddedOrNot) {
				
				


				if ((lObjMstDcpsBillGroupSubSchemeVO!=null)&&lObjMstDcpsBillGroupSubSchemeVO.length>0)
				{
					gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO");

					for (int j=0;j<lObjMstDcpsBillGroupSubSchemeVO.length;j++)
					{
						gLogger.info(" Inside the for  of lObjMstDcpsBillGroupSubSchemeVO"+j);
						//Long lLngBillGroupId = IFMSCommonServiceImpl.getNextSeqNum("mst_dcps_bill_group", inputMap);
						//lObjMstDcpsBillGroupSubSchemeVO[i].setDcpsDdoBillGroupId(lLngBillGroupId);
						//lObjMstDcpsBillGroupSubSchemeVO[j].setSubBGOrNot(0l);
						lObjMstDcpsBillGroupSubSchemeVO[j].setDcpsDdoCode(lStrDDOCode);
						lObjMstDcpsBillGroupSubSchemeVO[j].setLocId(Long.parseLong(LocIdList.get(0).toString()));
						lObjDdoSchemeDAO.create(lObjMstDcpsBillGroupSubSchemeVO[j]);
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getDcpsDdoBillDescription());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getDcpsDdoBillSchemeName());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getDcpsDdoBillTypeOfPost());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getDcpsDdoCode());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getDcpsDdoSchemeCode());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getDcpsDdoSubSchemeCode());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getDbId());
						//gLogger.info(" Inside the if of lObjDcpsDdoSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j]. getTypeOfBill());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j]. getBillDeleted());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j]. getBillNo());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j]. getCreatedDate());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j]. getDcpsDdoBillGroupId());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j]. getLangId());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j]. getLocId());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getUpdatedDate());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getUpdatedPostId());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getUpdatedUserId());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getCreatedDate());
						gLogger.info(" Inside the if of lObjMstDcpsBillGroupSubSchemeVO"+lObjMstDcpsBillGroupSubSchemeVO[j].getSubBGOrNot());
						gLogger.info(" after creation lObjMstDcpsBillGroupSubSchemeVO");
						lBlFlag = true;

					}
				}

				else
				{
					gLogger.info(" Inside the else  of lObjMstDcpsBillGroupSubSchemeVO"+LocIdList.get(0).toString());
					Long lLngBillGroupId = IFMSCommonServiceImpl.getNextSeqNum("mst_dcps_bill_group", inputMap);
					lObjMstDcpsBillGroupVO.setDcpsDdoBillGroupId(lLngBillGroupId);
					lObjMstDcpsBillGroupVO.setDcpsDdoCode(lStrDDOCode);
					lObjMstDcpsBillGroupVO.setLocId(Long.parseLong(LocIdList.get(0).toString()));
					lObjMstDcpsBillGroupVO.setSubBGOrNot(0l);
					lObjDdoSchemeDAO.create(lObjMstDcpsBillGroupVO);
					lBlFlag = true;
				}
			
				
				
				/*gLogger.error("Error is:" + lObjMstDcpsBillGroupVO);
				lObjDdoSchemeDAO.create(lObjMstDcpsBillGroupVO);
				lBlFlag = true;*/
			}
			gLogger.error("after  the mst_dcps_bill_group:" );
			resObj.setResultValue(inputMap);

		} catch (Exception e) {

			e.printStackTrace();
			gLogger.error("Error is:" + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");

		}

		/*
		 * Generates the XML response and sends the success flag (if a new
		 * scheme added otherwise a flag showing the scheme is already added to
		 * the DDO or not)
		 */
		String lSBStatus = getResponseXMLDocForDdoScheme(lBlFlag, lFlagSchemeAddedOrNot,lFlagSubSchemeAddedOrNot).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	private StringBuilder getResponseXMLDocToDisplaySchemeName(Integer lNoOfSchemes, List lListSchemes) {

		StringBuilder lStrBldXML = new StringBuilder();
		Object[] lObjSchemes = null;
		lStrBldXML.append("<XMLDOC>");

		lStrBldXML.append("<lNoOfSchemes>");
		lStrBldXML.append(lNoOfSchemes);
		lStrBldXML.append("</lNoOfSchemes>");
		
		for (int lInt = 0; lInt < lListSchemes.size(); lInt++) {
			lObjSchemes = (Object[]) lListSchemes.get(lInt);
			lStrBldXML.append("<SchemeName" + lInt + ">");
			lStrBldXML.append("<![CDATA[");
			lStrBldXML.append(lObjSchemes[1].toString().trim());
			lStrBldXML.append("]]>");

			lStrBldXML.append("</SchemeName" + lInt + ">");
			lStrBldXML.append("<SchemeCode" + lInt + ">");
			lStrBldXML.append(lObjSchemes[0].toString().trim());
			lStrBldXML.append("</SchemeCode" + lInt + ">");
		}

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDocForDdoScheme(Boolean lBlFlag, Boolean lFlagSchemeAddedOrNot,Boolean lFlagSubSchemeAddedOrNot) {
		
		

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<lBlFlag>");
		lStrBldXML.append(lBlFlag);
		lStrBldXML.append("</lBlFlag>");
		lStrBldXML.append("<lFlagSchemeAddedOrNot>");
		lStrBldXML.append(lFlagSchemeAddedOrNot);
		lStrBldXML.append("</lFlagSchemeAddedOrNot>");
		lStrBldXML.append("<lFlagSubSchemeAddedOrNot>");
		lStrBldXML.append(lFlagSubSchemeAddedOrNot);
		lStrBldXML.append("</lFlagSubSchemeAddedOrNot>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
	
	//added by saurabh
	public ResultObject displaySubSchemeNameForCode(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrSubSchemeCode = null;
		List<MstScheme> lListSchemes = null;
		Integer lNoOfSchemes = 0;


		gLogger.error("  Inside the  displaySubSchemeNameForCode");
		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DdoSchemeDAO lObjDdoSchemeDAO = new DdoSchemeDAOImpl(RltDcpsDdoScheme.class, serv.getSessionFactory());

			/* Gets SchemeCode from request */
			lStrSubSchemeCode = StringUtility.getParameter("txtSubSchemeCode", request);
			gLogger.error("  Inside the  lStrSubSchemeCode"+lStrSubSchemeCode);
			/*
			 * Gets the all the schemeNames whose scheme code starts with given
			 * scheme code and finds total schemes
			 */
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);

			lListSchemes = lObjDdoSchemeDAO.getSubSchemeNamesFromCode(lStrSubSchemeCode);

			lNoOfSchemes = lListSchemes.size();

			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

		/*
		 * Generates XML response for all schemes found whose scheme code starts
		 * with given scheme code
		 */
		String lSBStatus = getResponseXMLDocToDisplaySchemeName(lNoOfSchemes, lListSchemes).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}
	
//	Added  by Akshay

	public ResultObject CheckSubSchemeExistOrNot(Map<String, Object> inputMap) throws Exception{
		gLogger.error("  iNSIDE THE CheckSubSchemeExistOrNot");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String lStrSchemeCode = null;
		String lStrSubSchemeCode = null;
		try
		{
			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DdoSchemeDAO lObjDdoSchemeDAO = new DdoSchemeDAOImpl(RltDcpsDdoScheme.class, serv.getSessionFactory());

			/* Gets SchemeCode from request */
			lStrSchemeCode = StringUtility.getParameter("schemeCode", request);
			lStrSubSchemeCode = StringUtility.getParameter("subSchemeCode", request);

			gLogger.error(" schemeCode " +lStrSchemeCode);
			gLogger.error(" lStrSubSchemeCode " +lStrSubSchemeCode);
			
			int count=lObjDdoSchemeDAO.CheckSubSchemeExistOrNot(lStrSchemeCode,lStrSubSchemeCode);
			
			System.out.println("Size of newly created scheme list"+count);
			
			
			StringBuffer paybillSBf=new StringBuffer();
			paybillSBf.append("<paybillMapping>");
			paybillSBf.append("<status>").append(count).append("</status>");
			paybillSBf.append("</paybillMapping>"); 
			Map map = inputMap ;
		    String paybillStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();
		     
		    gLogger.info(" the string buffer is :"+count);
		    map.put("ajaxKey", paybillStatus);
		    resObj.setResultCode(ErrorConstants.SUCCESS);
			//logger.info("error");
		    resObj.setResultValue(map);
		    resObj.setViewName("ajaxData");
			gLogger.info(" SERVICE COMPLETE :");
				
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}
		
		return resObj;
		
	}
	
	public ResultObject CheckSubSchemeExist(Map<String, Object> inputMap) throws Exception
	{
		gLogger.error("  iNSIDE THE CheckSubSchemeExistOrNot");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String lStrSchemeCode = null;
		String lStrSubSchemeCode = null;
		try
		{
			/* Sets the Session Information */
			setSessionInfo(inputMap);

			/* Initializes the DAOs and variables */
			DdoSchemeDAO lObjDdoSchemeDAO = new DdoSchemeDAOImpl(RltDcpsDdoScheme.class, serv.getSessionFactory());

			/* Gets SchemeCode from request */
			lStrSchemeCode = StringUtility.getParameter("schemeCode", request);
			//lStrSubSchemeCode = StringUtility.getParameter("subSchemeCode", request);

			gLogger.error(" schemeCode " +lStrSchemeCode);
			//gLogger.error(" lStrSubSchemeCode " +lStrSubSchemeCode);
			
			int count=lObjDdoSchemeDAO.CheckSubSchemeExist(lStrSchemeCode);
			
			System.out.println("Size of newly created scheme list"+count);
			
			
			StringBuffer paybillSBf=new StringBuffer();
			paybillSBf.append("<paybillMapping>");
			paybillSBf.append("<status>").append(count).append("</status>");
			paybillSBf.append("</paybillMapping>"); 
			Map map = inputMap ;
		    String paybillStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();
		     
		    gLogger.info(" the string buffer is :"+count);
		    map.put("ajaxKey", paybillStatus);
		    resObj.setResultCode(ErrorConstants.SUCCESS);
			//logger.info("error");
		    resObj.setResultValue(map);
		    resObj.setViewName("ajaxData");
			gLogger.info(" SERVICE COMPLETE :");
				
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
	}
	return resObj;
}
	
	
	
}
