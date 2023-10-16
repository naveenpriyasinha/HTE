/**
 * <This class insert/update the application>
 *
 * @class name  	: <ZpAdminOfficeServiceImpl >
 * @author		: <Code Skeleton/Generator Tool - Developed By Durgesh (486841)/Zeal (479123)>
 * @version		: <1.0>
 */
package  com.tcs.sgv.eis.zp.zpAdminOffice.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.util.query.DateUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeDAO;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeMstDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.wf.util.IntimationRule;

public class ZpAdminOfficeServiceImpl extends ServiceImpl implements ZpAdminOfficeService
{
	private static Logger logger = Logger.getLogger( ZpAdminOfficeServiceImpl.class );
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/eis/zp/zpAdminOffice/ZpAdminOfficeLabels_en_US");
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private final Date todayDate = new Date();
	/* Global Variable for PostId */
	private Long POST_ID = null;
	/* Global Variable for UserId */
	private Long USER_ID = null;
	/* Global Variable for LangId */
	private Long LANG_ID = null;
	/* Global Variable for LocationId */
	private String LOC_ID = "";
	//String MESSAGECODE="55";



	/**
	* <This Method is used to Save/Insert the details>
	* 
	* @method name 	  : <saveZpAdminOfficeDtls >
	* @params		  : <objectArgs>
	* @returns 		  : <objRes>
	*/

	public ResultObject saveZpAdminOfficeDtls(Map objectArgs) throws Exception
	{
		logger.info("Entering into saveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");

		ZpAdminOfficeMst zpAdminOfficeMstVO = null;

		if(objectArgs.get("zpAdminOfficeMstVO")!=null){
			zpAdminOfficeMstVO = (ZpAdminOfficeMst) objectArgs.get("zpAdminOfficeMstVO");
		}
		//setSessionInfo(objectArgs);
		String updateFlag = (String) objectArgs.get("updateFlag");
		Map redirectMap = new HashMap();
		redirectMap.put("actionFlag", "getDisplayMessage");

		if(updateFlag.equalsIgnoreCase("false")){
		//Insert Starts............
			/* Insert Into Main Table : Starts.......*/
			ZpAdminOfficeDAO zpAdminOfficeMstDAOImpl = new ZpAdminOfficeDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
			String strTableZpAdminOfficeMst = "ZP_ADMIN_OFFICE_MST";
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String schemeCode=StringUtility.getParameter("txtDcpsShemeCode",request).toString();
			//String grNo=StringUtility.getParameter("txtDcpsGrNo",request).toString();
			String grNo=StringUtility.getParameter("cmbGrNo",request).toString();
			
			String strgrDate=StringUtility.getParameter("txtDcpsGRDate",request).toString();
			
			logger.info("===========================strgrDate====="+strgrDate);
			String officeName=StringUtility.getParameter("txtDcpsOffName",request).toString();
			Long zpAdminOfficeMstPkID = IFMSCommonServiceImpl.getNextSeqNum(strTableZpAdminOfficeMst,objectArgs);
			zpAdminOfficeMstVO.setOfcId(zpAdminOfficeMstPkID);
			zpAdminOfficeMstVO.setLangId(1l);
			zpAdminOfficeMstVO.setCreatedDate(todayDate);
			zpAdminOfficeMstVO.setCreatedBy(USER_ID);
			zpAdminOfficeMstVO.setschemeCode(schemeCode);
			zpAdminOfficeMstVO.setgrNo(grNo);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); 
			if(strgrDate!=null && strgrDate!="")
			{ Date grDate = dateFormat.parse(strgrDate); 
		    logger.info("===========================grDate====="+grDate);
			zpAdminOfficeMstVO.setgrDate(grDate);
			}
			zpAdminOfficeMstVO.setdcpsOffName(officeName);
			zpAdminOfficeMstDAOImpl.create(zpAdminOfficeMstVO);
			/* Insert Into Main Table : Ends.......*/

			redirectMap.put("MESSAGECODE",55L); //Sucess Message
		//Insert Ends............
		}else if(updateFlag.equalsIgnoreCase("true")){
		//Udpate Starts............
			/* Update For Main Table : Starts.......*/
			ZpAdminOfficeDAO zpAdminOfficeMstDAOImpl = new ZpAdminOfficeDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
			zpAdminOfficeMstVO.setUpdatedDate(todayDate);
			zpAdminOfficeMstVO.setUpdatedBy(USER_ID);
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String schemeCode=StringUtility.getParameter("txtDcpsShemeCode",request).toString();
			String grNo=StringUtility.getParameter("cmbGrNo",request).toString();
			String strgrDate=StringUtility.getParameter("txtDcpsGRDate",request).toString();
			logger.info("===========================strgrDate====="+strgrDate);
			String officeName=StringUtility.getParameter("txtDcpsOffName",request).toString();
	
			zpAdminOfficeMstVO.setLangId(1l);
			zpAdminOfficeMstVO.setCreatedDate(todayDate);
			zpAdminOfficeMstVO.setCreatedBy(USER_ID);
			zpAdminOfficeMstVO.setschemeCode(schemeCode);
			zpAdminOfficeMstVO.setgrNo(grNo);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); 
			if(strgrDate!=null && strgrDate!="")
			{ Date grDate = dateFormat.parse(strgrDate); 
		    logger.info("===========================grDate====="+grDate);
		    zpAdminOfficeMstVO.setgrDate(grDate);
			}
			
			zpAdminOfficeMstVO.setdcpsOffName(officeName);
			zpAdminOfficeMstDAOImpl.update(zpAdminOfficeMstVO);
			/* Update For Main Table : Ends.......*/

			/* Update Child or Add Row Table : Starts.......*/
			/* Update Child or Add Row Table : Ends.......*/
			redirectMap.put("MESSAGECODE",55L); //Update Success Message
			//Update Ends............
			zpAdminOfficeMstVO = null;
		}
		logger.info("Exit from saveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
		objectArgs.put("redirectMap", redirectMap);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("redirect view");
		return objRes;
	}

	public ResultObject loadData(Map objectArgs) throws Exception
	{
		logger.info("Entering into loadData of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");

		ZpAdminOfficeMst ZpAdminOfficeMstVO = new ZpAdminOfficeMst();
		ZpAdminOfficeMstDAOImpl hh=new ZpAdminOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		Calendar cal = Calendar.getInstance();
        java.util.Date today = cal.getTime();
        logger.info("===> today :: "+today);
        objectArgs.put("current_date", today);
		List t=hh.retriveMaxOfcCode();
		Long officeCode = null;
		String ofcCode="";
		String tempOfcCode=null;
		if(t.size()>0){
			Object[] o =(Object[])t.get(0);
		//	objectArgs.put("OfcCode", Integer.valueOf(o[1].toString())+1);
			officeCode=Long.valueOf(o[1].toString())+1;
			if(officeCode<10){
				tempOfcCode="0"+officeCode;
				ofcCode=tempOfcCode;
				logger.info("--------------------ofcCode----"+ofcCode);
				objectArgs.put("OfcCode",ofcCode);
			}
			else
			{
				//tempOfcCode="0"+officeCode;
				ofcCode=officeCode.toString();
				logger.info("--------------------ofcCode----"+ofcCode);
				objectArgs.put("OfcCode",ofcCode);
			}
		}
		else{
			String ofcode="02";
			objectArgs.put("OfcCode",ofcode);
		}
		
		 List lstGRNames=hh.retriveGRNo("7");
		 List adminBranchname = hh.getallAdminName();
		 if(adminBranchname!=null){
			 objectArgs.put("adminBranchname",adminBranchname);
		 }
		 logger.info("name for admin:::"+adminBranchname);
		 
		ZpAdminOfficeMstVO.setOfficeCode(ofcCode);
		//ZpAdminOfficeMstVO.setStrOfficeCode(tempOfcCode);
		objectArgs.put("ZpAdminOfficeMstVO",ZpAdminOfficeMstVO);
		objectArgs.put("lstGRNames",lstGRNames);
		
		objRes.setResultValue(objectArgs);
		objRes.setViewName("view-zpAdminOffice");
		return objRes;
	}

	@Override
	public ResultObject retrieveZpAdminOfficeDtls(Map objectArgs) throws Exception
	{
		logger.info("Entering into retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		ZpAdminOfficeMstDAOImpl hh=new ZpAdminOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		List OfcDtls=hh.retriveOfcList("");
		logger.info("Entering into retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl:::"+OfcDtls.size());
		objectArgs.put("ZpAdminOfficeMstList", OfcDtls);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("view-searchZpAdminOffice");
		return objRes;
	}
	public ResultObject search_ZpAdminOffice(Map objectArgs) throws Exception{
		logger.info("ZpAdminOfficeServiceImpl : search_ZpAdminOffice Called");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ZpAdminOfficeMstDAOImpl hh=new ZpAdminOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		String OfcName=StringUtility.getParameter("txtAdminOfficeName",request).toString();
		List SerachedOfcDtls=hh.retriveOfcList(OfcName);
		objectArgs.put("ZpAdminOfficeMstList", SerachedOfcDtls);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("view-searchZpAdminOffice");
		return objRes;
	}

	/**
	* <This Method is used to retrieve the details>
	* 
	* @method name 	  : <retrieveZpAdminOfficeDtls>
	* @params		  : <objectArgs>
	* @returns 		  : <objRes>
	*/
	/*public ResultObject retrieveZpAdminOfficeDtls(Map objectArgs) throws Exception{
		logger.info("Entering into retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");


		/*CmnSearchListVO cmnSearchListVO = null;
		List<CmnSearchListVO> lstCmnSearch = new ArrayList<CmnSearchListVO>();
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		CommonUserVO commonUserVO = (CommonUserVO) loginMap.get("commonUserVO");
		// add dynamic field
		setSessionInfo(objectArgs);
		
		objectArgs = populateCombo(objectArgs);
		ZpAdminOfficeMst zpAdminOfficeMstVO = null;

		if(objectArgs.get("zpAdminOfficeMstVO")!=null){
			zpAdminOfficeMstVO = (ZpAdminOfficeMst) objectArgs.get("zpAdminOfficeMstVO");
		}
		PaginatedListImpl paginateList = (PaginatedListImpl) objectArgs.get("paginateList");
		int startIndex= paginateList.getFirstRecordIndex();
		int pageSize= paginateList.getPageSize();
		ZpAdminOfficeDAO readZpAdminOfficeMstDAOImpl = new ZpAdminOfficeDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		List zpAdminOfficeMstList = readZpAdminOfficeMstDAOImpl.searchZpAdminOfficeDetailsList(zpAdminOfficeMstVO,startIndex,pageSize);
		paginateList.setList(zpAdminOfficeMstList);
		if(zpAdminOfficeMstList !=null && zpAdminOfficeMstList.size()>0){
			Iterator it = zpAdminOfficeMstList.listIterator();
			MstScreenCustVO mstScreenVO = (MstScreenCustVO)it.next();
		paginateList.setTotalNumberOfRows(Integer.parseInt(mstScreenVO.getColumn()[0]));
		}else{
			paginateList.setTotalNumberOfRows(0);
		}
		objectArgs.put("paginateList", paginateList);
		objectArgs.put("ZpAdminOfficeMstList", zpAdminOfficeMstList);
		logger.info("Exit from retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("view-searchZpAdminOffice");
		return objRes;
	}*/
	/** <This Method is used to loadAddData details>
	* 
	* @method name 	  : <loadAddData>
	* @params		  : <objectArgs>
	* @returns 		  : <objRes>
	*/
	/*public ResultObject loadAddData(Map objectArgs) throws Exception{
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		objectArgs = populateCombo(objectArgs);
		objectArgs.put("updateFlag", "false");

		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("view-zpAdminOffice");
		logger.info("ZpAdminOfficeServiceImpl : loadAddData End");
		return objRes;
	}*/
	
	
	public ResultObject loadUpdateData(Map objectArgs) throws Exception{
		logger.info("ZpAdminOfficeServiceImpl : loadUpdatedata Called");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		
		//objectArgs = populateCombo(objectArgs);

		ZpAdminOfficeMst readZpAdminOfficeMstVO = new ZpAdminOfficeMst();
		ZpAdminOfficeMstDAOImpl readZpAdminOfficeMstDAOImpl = new ZpAdminOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		Long zpAdminOfficeMstPkID = Long.valueOf(StringUtility.getParameter("zpAdminOfficeMstPkID",request).toString());
		readZpAdminOfficeMstVO = readZpAdminOfficeMstDAOImpl.read(zpAdminOfficeMstPkID);
		
		
		 List adminBranchname = readZpAdminOfficeMstDAOImpl.getallAdminName();
		 if(adminBranchname!=null){
			 objectArgs.put("adminBranchname",adminBranchname);
		 }
		 logger.info("name for admin:::"+adminBranchname);
		 //List lstGRNames=readZpAdminOfficeMstDAOImpl.retriveGRNo("7");
		 List lstGRNames=readZpAdminOfficeMstDAOImpl.retriveGRNo("6");
		objectArgs.put("ZpAdminOfficeMstVO",readZpAdminOfficeMstVO);
		objectArgs.put("lstGRNames",lstGRNames);
		objectArgs.put("updateFlag", "true");
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("view-zpAdminOffice");
		logger.info("ZpAdminOfficeServiceImpl : loadUpdateData End");
		return objRes;
	}
	/**
	* <This Method is used to populateCombo details>
	* @throws ServiceException
	* @method name 	  : <populateCombo>
	* @params		  : <objectArgs>
	* @returns 		  : <objRes>
	*/
	/*public Map populateCombo(Map objectArgs){
		setSessionInfo(objectArgs);
		return objectArgs;
	}*/

	/**
	* <This Method is used to setSessionInfo details>
	* 
	* @method name 	  : <setSessionInfo>
	* @params		  : <inputMap>
	* @returns 		  : <>
	*/
	/*private void setSessionInfo(Map inputMap){
		Map loginMap = (Map) inputMap.get("baseLoginMap");
		CommonUserVO commonUserVO = (CommonUserVO) loginMap.get("commonUserVO");
		LOC_ID = loginMap.get("locationCode").toString();
		LANG_ID = commonUserVO.getLangId();
		POST_ID = commonUserVO.getLoggedInPostVO().getPostCode();
		USER_ID = commonUserVO.getUserId();
	}*/
	
	public ResultObject checkSchemeCode(Map objectArgs) throws Exception
	{

		logger.info("Entering into defineReportingDDO of ReportingDDOservice");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Long langId =1l;
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try
		{
			ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
			resObj.setResultCode(ErrorConstants.SUCCESS);
			ZpAdminOfficeMstDAOImpl readZpAdminOfficeMstDAOImpl = new ZpAdminOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
			String schemeCode=StringUtility.getParameter("schemeCode",request).toString();
			int isValid=readZpAdminOfficeMstDAOImpl.checkSchemeCode(schemeCode);
			String lSBStatus = getResponseXMLDocSaveData(isValid).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			logger.info("********************************************"+lStrResult);
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
		
	}
	public ResultObject retriveDateFromGRNo(Map objectArgs) throws Exception
	{
		logger.info("Entering into retriveDateFromGRNo of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		ZpAdminOfficeMstDAOImpl hh=new ZpAdminOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		String GRNo=StringUtility.getParameter("cmbGrNo", request).trim();
		List GrDate=hh.retriveDateFromGRNo(GRNo);
		 String convertedDate=new DateUtility().convertToDDMMYYYY(GrDate.get(0).toString().split(" ")[0]);
			
		logger.info("Entering into retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl:::"+GrDate.size());
		objectArgs.put("GRDate",convertedDate);
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", convertedDate).toString();
		logger.info("********************************************"+lStrResult);
		objectArgs.put("ajaxKey", lStrResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		
		//objRes.setViewName("view-zpAdminOffice");
		return objRes;
	}
	private StringBuilder getResponseXMLDocSaveData(int isValid) {

		StringBuilder lStrBldXML = new StringBuilder();
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<isValid>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(isValid);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</isValid>");
		lStrBldXML.append("</XMLDOC>");
		
		return lStrBldXML;
	}
	
}
