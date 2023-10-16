package com.tcs.sgv.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;

public class CityClassUpdation extends ServiceImpl  {

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


	public ResultObject updateCityClassForEmployee(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{
			gLogger.info("inside City For Employee : updateCityClassForEmployee");
			setSessionInfo(inputMap);

			String sevarthID="";
			String districtCode="";

			String cityClassName="";
			String flag="N";

			
		String dictrictName;//1
		
		String talukaName;
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			districtCode = StringUtility.getParameter("districtCode", request);
			gLogger.info("District Code :"+districtCode);

			cityClassName = StringUtility.getParameter("cityClassName", request);
			gLogger.info("cityClassName :"+cityClassName);

			sevarthID = StringUtility.getParameter("sevarthID", request);
			flag = StringUtility.getParameter("flag", request);
			gLogger.info("update flag : "+flag);
			
			
			
			/*//added by poonam for taluka
			String districtName=StringUtility.getParameter("dictrictName", request);
			gLogger.info("The Value of given district is as follows:"+districtName);
			List Talukas=lObjDcpsCommonDAO.allTaluka(districtName);
			gLogger.info("The Size of talukas for given district is:"+Talukas.size());
			inputMap.put("Taluka", Talukas);
		  	//ended by poonam
*/		  	
			gLogger.info("DDO Code : "+sevarthID);
			

			String arrDistrictCode[]=districtCode.split("~");
			String arrSevarthCode[]=sevarthID.split("~");
			String arrCityClass[]=cityClassName.split("~");
		
			String message=null;
			if(flag.equals("Y"))
			{
				dictrictName = (StringUtility.getParameter("dictrictName", request));
				gLogger.info("Dictrict Name before trim :"+dictrictName);
				talukaName= (StringUtility.getParameter("talukaName", request));//
				String arrdictrictName[]=dictrictName.split("~");
				String arrTalukaName[]=talukaName.split("~");
			
				gLogger.info("Taluka Name forupdation is :"+talukaName);
				gLogger.info("Dictrict Name:"+dictrictName);
				for(int i=0;i<arrSevarthCode.length;i++){
					
					gLogger.info("Inside the for of UpdateCity ClassForEMployee");
					
					lObjDcpsCommonDAO.updateCityClassForEmployee(arrCityClass[i],arrSevarthCode[i],arrdictrictName[i],arrTalukaName[i]);	
				}
				message="Details has been updated successfully.";

			}
			inputMap.put("msg", message);
			List lstDistrict=lObjDcpsCommonDAO.getDistricts(15l);
			List lstTaluka=lObjDcpsCommonDAO.getTalukaForCityClass();
			inputMap.put("lstTaluka", lstTaluka);
			
			
			gLogger.info("lstDistrict: "+lstDistrict);
			inputMap.put("lstDistrict", lstDistrict);
			gLogger.info("lstDistrict: "+lstDistrict.size());

			List lstCityClass=lObjDcpsCommonDAO.getCityClassName();
			gLogger.info("lstCityClass: "+lstCityClass);
			inputMap.put("lstCityClass", lstCityClass);
			gLogger.info("lstCityClass: "+lstCityClass.size());


			String ddoCode=lObjDcpsCommonDAO.getDdoCode(Long.parseLong(gStrPostId));

			gLogger.info("DDOCODE: "+ddoCode);
			List empList = lObjDcpsCommonDAO.getEmpListForCityClassUpdation(ddoCode);
		
			
			
			inputMap.put("empList", empList);
			gLogger.info("empListSize: "+empList.size());
			inputMap.put("empListSize", empList.size());
			resObj.setResultValue(inputMap);
			resObj.setViewName("updateCityClassForEmployee");

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

	public ResultObject getTalukaForCityClass(Map inputMap) throws Exception{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lstTaluka=null;

		try{
			gLogger.info("inside City For Employee : getTalukaForCityClass");
			setSessionInfo(inputMap);


			Long dictrictName;

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			dictrictName = Long.parseLong(StringUtility.getParameter("dictrictName", request));
			gLogger.info("Dictrict Name:"+dictrictName);


			if(dictrictName != null){
				lstTaluka=lObjDcpsCommonDAO.getTaluka(dictrictName);
				gLogger.info("lstTaluka: "+lstTaluka);
				inputMap.put("lstTaluka", lstTaluka);
				gLogger.info("lstTaluka: "+lstTaluka.size());
			}

			String lStrTempResult = null;
			if (lstTaluka != null) {
				lStrTempResult = new AjaxXmlBuilder().addItems(
						lstTaluka, "desc", "id", true).toString();
			}

			inputMap.put("ajaxKey", lStrTempResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
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
