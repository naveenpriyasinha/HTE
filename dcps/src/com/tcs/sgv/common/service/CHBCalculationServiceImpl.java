package com.tcs.sgv.common.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.acl.valueobject.AclPostroleRlt;
import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.common.dao.CHBCalculationDAO;
import com.tcs.sgv.common.dao.CHBCalculationDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.Qualification;
import com.tcs.sgv.common.dao.QualificationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.helper.WorkFlowHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.AttachmentHelper;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoBillGroupDAO;
import com.tcs.sgv.dcps.dao.DdoBillGroupDAOImpl;
import com.tcs.sgv.dcps.dao.DdoProfileDAO;
import com.tcs.sgv.dcps.dao.DdoProfileDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAO;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAOImpl;
import com.tcs.sgv.dcps.dao.OfflineContriDAO;
import com.tcs.sgv.dcps.dao.OfflineContriDAOImpl;
import com.tcs.sgv.dcps.dao.SearchEmployeeDAO;
import com.tcs.sgv.dcps.dao.SearchEmployeeDAOImpl;
import com.tcs.sgv.dcps.service.DDOProfileServiceImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.DcpsCadreMst;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.HstEmp;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDdoAsst;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.fms.valueobject.WfHierachyPostMpg;
import com.tcs.sgv.wf.delegate.WorkFlowDelegate;

public class CHBCalculationServiceImpl extends ServiceImpl{
	
	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");

	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap)
	{

		try
		{
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gDtCurrDt = SessionHelper.getCurDate();
		}
		catch (Exception e)
		{

		}

	}
	

	public ResultObject basicClaculationForCHB(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			CHBCalculationDAO chbCalculationDAO = null;
			List lLstReasons = null;
			List lLstEmpSelect = null;
			List lLstYearSelect = null;
			List lLstMonthSelect = null;
			List lLstOffices = null;
			String lStrDdoCode = null;
			String lStrSevarthId = null;
			String lStrEmpName = null;
			String empId = null;
			Boolean lBlMultipleEntriesInHstEmpForEmpId = false;
			Long lLongDcpsEmpId = null;

			try {
				setSessionInfo(inputMap);

				chbCalculationDAO = new CHBCalculationDAOImpl(CHBCalculationServiceImpl.class,
						serv.getSessionFactory());
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
						.getSessionFactory());

				lLstReasons = IFMSCommonServiceImpl.getLookupValues(
						"Reason For Deslection", gLngLangId, inputMap);


				lStrDdoCode = chbCalculationDAO.getDdoCodeForCHB(gLngPostId);

				String lStrRequestForSearch = StringUtility.getParameter(
						"requestForSearch", request).trim();


				if (lStrRequestForSearch.equals("Yes")) {

					lStrSevarthId = StringUtility.getParameter("txtSevaarthId",request).trim().toUpperCase();
					lStrEmpName = StringUtility.getParameter("txtEmployeeName",request).trim().toUpperCase();
					
					lLstEmpSelect = chbCalculationDAO.getEmpListForSelection(
							lStrEmpName, lStrSevarthId);
					
					lLstMonthSelect = chbCalculationDAO.getMonthList();
					lLstYearSelect = chbCalculationDAO.getYearList();
					empId = chbCalculationDAO.getEmpId(lStrEmpName);
					
				} 
		
				
				inputMap.put("SELECTEMPLIST", lLstEmpSelect);
				inputMap.put("lLstMonthSelect", lLstMonthSelect);
				inputMap.put("lLstYearSelect", lLstYearSelect);
				inputMap.put("empId", empId);
				objRes.setResultValue(inputMap);
				objRes.setViewName("basicClaculationForCHB");

			}

			catch (Exception e) {
				//e.printStackTrace();
				gLogger.error("Exception is " + e, e);
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
			}

			return objRes;
		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	
	public ResultObject getEmpNameForAutoCompleteCHB(Map<String, Object> inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		List finalList = null;
		String lStrEmpName = null;
		String lStrSearchBy = null;
		String lStrDDOCode = null;
		String lStrSearchType = null;

		try {
			setSessionInfo(inputMap);
			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			
			CHBCalculationDAO chbCalculationDAO = new CHBCalculationDAOImpl(null, serv
					.getSessionFactory());

			lStrEmpName = StringUtility.getParameter("searchKey", request)
					.trim();
			gLogger.info("lStrEmpName*******"+lStrEmpName);

			lStrSearchBy = StringUtility.getParameter("searchBy", request)
					.trim();

			gLogger.info("lStrSearchBy*******"+lStrSearchBy);
			lStrSearchType = StringUtility.getParameter("searchType", request);

			if (lStrSearchBy.equals("searchByDDOAsst")) {

				lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			}
			if (lStrSearchBy.equals("searchFromDDOSelection")
					|| lStrSearchBy.equals("searchByDDO")) {
				
				lStrDDOCode = chbCalculationDAO.getDdoCodeForDDOForCHB(gStrLocationCode);
				gLogger.info("lStrDDOCode*******"+lStrDDOCode);
			}
			
			gLogger.info("gLngPostId*****"+gLngPostId);
			if (lStrSearchBy.equals("searchBySRKA")) {
				//commented by vaibhav tyagi
				//lStrDDOCode = null;
				//added by vaibhav tyagi : start
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
				//added by vaibhav tyagi : end
			}
			gLogger.info("lStrDDOCode*******"+lStrDDOCode);

			finalList = chbCalculationDAO.getEmpNameForAutoCompleteFORCHB(
					lStrEmpName.toUpperCase(), lStrSearchType, lStrDDOCode,lStrSearchBy);

			String lStrTempResult = null;
			if (finalList != null) {
				lStrTempResult = new AjaxXmlBuilder().addItems(finalList,
						"desc", "id", true).toString();

			}
			inputMap.put("ajaxKey", lStrTempResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");

		} catch (Exception ex) {
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			//ex.printStackTrace();
			return objRes;
		}

		return objRes;

	}
	
	
	public ResultObject claculateBasicValueForCHB(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			CHBCalculationDAO chbCalculationDAO = null;
			List lLstReasons = null;
			Long lLstEmpSelect = null;
			//List lLstOffices = null;
			String lStrDdoCode = null;
			String sevarthID = null;
			String txtHours = null;
			Long basicValue=null;
			List lLstEmpSelect1 = null;
			
			String lStrEmpName = null;
			//Boolean lBlMultipleEntriesInHstEmpForEmpId = false;
			//Long lLongDcpsEmpId = null;

			try {
				setSessionInfo(inputMap);

				chbCalculationDAO = new CHBCalculationDAOImpl(CHBCalculationServiceImpl.class,
						serv.getSessionFactory());
				//DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

				lLstReasons = IFMSCommonServiceImpl.getLookupValues(
						"Reason For Deslection", gLngLangId, inputMap);


				lStrDdoCode = chbCalculationDAO.getDdoCodeForCHB(gLngPostId);

				String lStrRequestForSearch = StringUtility.getParameter(
						"requestForSearch", request).trim();
				
				
				sevarthID = StringUtility.getParameter("sevarthID",request).trim().toUpperCase();
				lStrEmpName = StringUtility.getParameter("txtEmployeeName",request).trim().toUpperCase();
				
				lLstEmpSelect1 = chbCalculationDAO.getEmpListForSelection(
						lStrEmpName, sevarthID);


					sevarthID = StringUtility.getParameter("sevarthID",request).trim().toUpperCase();
					txtHours = StringUtility.getParameter("txtHours",request).trim().toUpperCase();
					
					String arrsevarthID[]=sevarthID.split("~");
					String arrtxtHours[]=txtHours.split("~");
					for(int i=0;i<arrsevarthID.length;i++){
						lLstEmpSelect = chbCalculationDAO.getRatePerHoursForCHBCalculation(arrsevarthID[i],arrtxtHours[i]);	
					}
					basicValue=Long.parseLong(txtHours)*lLstEmpSelect;
					gLogger.info("basicValue*******"+basicValue);
				 
		
				inputMap.put("lLstEmpSelect", lLstEmpSelect);
				inputMap.put("basicValue", basicValue);
				
				objRes.setResultValue(inputMap);
				objRes.setViewName("basicClaculationForCHB");

			}

			catch (Exception e) {
				//e.printStackTrace();
				gLogger.error("Exception is " + e, e);
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
				
			}

			return objRes;
		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	
	public ResultObject updateCHBCalculationValue(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			CHBCalculationDAO chbCalculationDAO = null;
			List lLstReasons = null;
			String lLstEmpSelect = null;
			//List lLstOffices = null;
			String lStrDdoCode = null;
			String sevarthID = null;
			String basicValue = null;
			String empId = null;
			String empName = null;
			String month = null;
			String year = null;
			String hours = null;
			Integer checkChb=null;
			
			List lLstEmpSelect1 = null;
			
			String lStrEmpName = null;
			//Boolean lBlMultipleEntriesInHstEmpForEmpId = false;
			//Long lLongDcpsEmpId = null;

			try {
				setSessionInfo(inputMap);

				chbCalculationDAO = new CHBCalculationDAOImpl(CHBCalculationServiceImpl.class,
						serv.getSessionFactory());
				//DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

				lLstReasons = IFMSCommonServiceImpl.getLookupValues(
						"Reason For Deslection", gLngLangId, inputMap);


				//lStrDdoCode = chbCalculationDAO.getDdoCodeForCHB(gLngPostId);
				lStrDdoCode = chbCalculationDAO.getDdoCodeForDDOForCHB(gStrLocationCode);
				gLogger.info("lStrDDOCode*******"+lStrDdoCode);

				String lStrRequestForSearch = StringUtility.getParameter(
						"requestForSearch", request).trim();
				
				
				sevarthID = StringUtility.getParameter("sevarthID",request).trim().toUpperCase();
				empName = StringUtility.getParameter("empName",request).trim().toUpperCase();
				empId = StringUtility.getParameter("empId",request).trim().toUpperCase();
				month = StringUtility.getParameter("month",request).trim().toUpperCase();
				year = StringUtility.getParameter("year",request).trim().toUpperCase();
				hours = StringUtility.getParameter("hours",request).trim().toUpperCase();
				basicValue = StringUtility.getParameter("txtValue",request).trim().toUpperCase();
				
				
				
									
					String arrsevarthID[]=sevarthID.split("~");
					String arrEmpName[]=empName.split("~");
					String arrEmpId[]=empId.split("~");
					String arrMonth[]=month.split("~");
					String arrYear[]=year.split("~");
					String arrHours[]=hours.split("~");
					String arrtxtValue[]=basicValue.split("~");
					gLogger.info("***********");
					
					 
					
					
					
					IdGenerator idGen = new IdGenerator();
					long chbDataId = idGen.PKGenerator("HR_PAY_CHB_DATA", inputMap);
					
					for(int i=0;i<arrsevarthID.length;i++){
						gLogger.info("chbDataId-"+chbDataId+"arrEmpId-"+arrEmpId[i]+"arrMonth-"+arrMonth[i]+"arrYear-"+arrYear[i]+"arrHours-"+arrHours[i]+"arrtxtValue-"+arrtxtValue[i]+"gStrUserId "+gStrUserId +"lStrDdoCode-"+lStrDdoCode);
						checkChb = chbCalculationDAO.checkCHBData(arrEmpId[i],arrMonth[i],arrYear[i]);
						gLogger.info("checkChb  "+checkChb);
						if(checkChb==0){
							chbCalculationDAO.insertCHBDataValue(chbDataId,arrEmpId[i],arrMonth[i],arrYear[i],arrHours[i],arrtxtValue[i],"null",gStrUserId,"null",lStrDdoCode);
						}else{
							chbCalculationDAO.updateCHBDataValue(arrEmpId[i],arrMonth[i],arrYear[i],arrHours[i],arrtxtValue[i],"null","null",gStrUserId,lStrDdoCode);
						}
						//lLstEmpSelect =	chbCalculationDAO.insertCHBDataValue(chbDataId,arrEmpId[i],arrMonth[i],arrYear[i],arrHours[i],arrtxtValue[i],sysdate,"null",gStrUserId,"null",lStrDdoCode);
					}
				 
		
				//inputMap.put("lLstEmpSelect", lLstEmpSelect);
				
				objRes.setResultValue(inputMap);
				objRes.setViewName("basicClaculationForCHB");

			}

			catch (Exception e) {
				//e.printStackTrace();
				gLogger.error("Exception is " + e, e);
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
				
			}

			return objRes;
		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	

	
	
}
