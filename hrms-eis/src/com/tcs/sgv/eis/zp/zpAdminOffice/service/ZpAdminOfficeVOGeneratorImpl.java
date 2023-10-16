/**
 * <This class get details from application >
 *
 * @class name  : <ZpAdminOfficeVOGeneratorImpl >
 * @author		 : <Code Skeleton/Generator Tool - Developed By Durgesh (486841)/Zeal (479123)>
 * @version	 : <1.0>
*/

package com.tcs.sgv.eis.zp.zpAdminOffice.service;
import java.text.SimpleDateFormat;import java.util.Date;import java.util.Iterator;import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.common.utils.displaytable.PaginatedListImpl;

import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeDAO;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeMstDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;

public class ZpAdminOfficeVOGeneratorImpl extends ServiceImpl implements  ZpAdminOfficeVOGenerator
{
	private static final Logger logger = Logger.getLogger(ZpAdminOfficeVOGeneratorImpl.class);

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

	/**
	* <This Method is used to generateMap to Prepare ValueObject>
	* 
	* @method name 	  : <generateMap>
	* @params		  : <objectArgs>
	* @returns 		  : <objRes>
	*/
	public ResultObject generateMap(Map objectArgs) throws Exception
	{
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");

		logger.info("Entered into generateMap of ZpAdminOfficeVOGeneratorImpl");
		String updateFlag = !StringUtility.getParameter("updateFlag", request).equalsIgnoreCase("") ? StringUtility.getParameter("updateFlag", request) : "false";
		objectArgs.put("updateFlag",updateFlag);

		ZpAdminOfficeMst zpAdminOfficeMstVO = new ZpAdminOfficeMst();

		String strTxtAdminOfficeName = !StringUtility.getParameter("txtAdminOfficeName",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtAdminOfficeName",request) : "";
		String lTxtCode =(!StringUtility.getParameter("txtCode",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtCode",request) : "0");
		String lTxtCode1 = (!StringUtility.getParameter("txtCode",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtCode",request) : "0");
		Long zpAdminOfficeMstID = Long.valueOf(!StringUtility.getParameter("hdnOfcId",request).equalsIgnoreCase("") ? StringUtility.getParameter("hdnOfcId",request) : "0");
		Long OFC_Code;
		ZpAdminOfficeMstDAOImpl hh=new ZpAdminOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		
		ZpAdminOfficeMst zpAdminOfficeMstVO1 = new ZpAdminOfficeMst();
		List t=hh.retriveMaxOfcCode();
		if(t.size()>0){
			
		
		
			Object[] o =(Object[])t.get(0);
		
			zpAdminOfficeMstVO1.setOfficeCode((o[1].toString()));
		}
		
		//zpAdminOfficeMstVO1=hh.retriveMaxOfcCode();
		if(zpAdminOfficeMstVO1.getOfficeCode()!=null){
			Long tem=Long.valueOf(zpAdminOfficeMstVO1.getOfficeCode())+1;
			if(tem<10)
				lTxtCode="0"+tem.toString();
			else
				lTxtCode=tem.toString();
		}
		else
		{
			lTxtCode="02";	
		}
	
		
		/* Setting Values into VO */

		if(updateFlag.equalsIgnoreCase("true")){
			ZpAdminOfficeDAO readZpAdminOfficeMstDAOImpl = new ZpAdminOfficeDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
			zpAdminOfficeMstVO = (ZpAdminOfficeMst) readZpAdminOfficeMstDAOImpl.read(zpAdminOfficeMstID);
		}


		zpAdminOfficeMstVO.setOfficeName(strTxtAdminOfficeName);
		zpAdminOfficeMstVO.setOfficeCode(lTxtCode1);

		objectArgs.put("zpAdminOfficeMstVO", zpAdminOfficeMstVO);

		strTxtAdminOfficeName = null;
		lTxtCode = null;



		validateZpAdminOfficeDetail(objectArgs);
		logger.info("Exit from generateMap of ZpAdminOfficeVOGeneratorImpl");
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		return objRes;
	}

	/**
	* <This Method is used to retrieveZpAdminOfficeDtls>
	* 
	* @method name 	  : <retrieveZpAdminOfficeDtls>
	* @params		  : <objectArgs>
	* @returns 		  : <objRes>
	*/
	public ResultObject retrieveZpAdminOfficeDtls(Map objectArgs) throws Exception{
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");

		logger.info("Entered into retrieve of ZpAdminOfficeVOGeneratorImpl");
		ZpAdminOfficeMst zpAdminOfficeMstVO = new ZpAdminOfficeMst();

		String strTxtAdminOfficeName = !StringUtility.getParameter("txtAdminOfficeName",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtAdminOfficeName",request) : "";
		String lTxtCode = (!StringUtility.getParameter("txtCode",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtCode",request) : "0");
		Long zpAdminOfficeMstID = Long.valueOf(!StringUtility.getParameter("hdnOfcId",request).equalsIgnoreCase("") ? StringUtility.getParameter("hdnOfcId",request) : "0");


		zpAdminOfficeMstVO.setOfficeName(strTxtAdminOfficeName);
		zpAdminOfficeMstVO.setOfficeCode(lTxtCode);

		objectArgs.put("zpAdminOfficeMstVO", zpAdminOfficeMstVO);

		//----For Pagination---- Start
		PaginatedListImpl paginateList = new PaginatedListImpl();
		paginateList.setRequest(request);
		String page = request.getParameter("page") != null ? request.getParameter("page") : "1";
		paginateList = paginateList.getPaginatedListObject(15, page);
		objectArgs.put("paginateList", paginateList);
		//----For Pagination---- End

		strTxtAdminOfficeName = null;
		lTxtCode = null;
		logger.info("Exit from retrieve of ZpAdminOfficeVOGeneratorImpl");
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		return objRes;
	}

	/**
	* <This Method is used for validating the fields>
	* 
	* @method name 	  : <validateZpAdminOfficeDetail>
	* @params		  : <objectArgs>
	* @returns 		  : <String>
	*/
	public ResultObject validateZpAdminOfficeDetail(Map objectArgs) throws Exception
	{
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		logger.info("Entered into validate of ZpAdminOfficeVOGENImpl");
		logger.info("Exit from validate of ZpAdminOfficeVOGENImpl");
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		return objRes;
	}
	public ResultObject loadUpdateData(Map objectArgs) throws Exception{
		logger.info("ZpAdminOfficeVOGeneratorImpl : loadUpdatedata Called");
		ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Long zpAdminOfficeMstPkID  =Long.valueOf((!StringUtility.getParameter("zpAdminOfficeMstPkID", request).equalsIgnoreCase("")?StringUtility.getParameter("zpAdminOfficeMstPkID", request):"0"));
		logger.info("zpAdminOfficeMstPkID"+zpAdminOfficeMstPkID);
		objectArgs.put("zpAdminOfficeMstPkID",zpAdminOfficeMstPkID);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		retObj.setResultValue(objectArgs);
		logger.info("ZpAdminOfficeVOGeneratorImpl : loadUpdatedata End");
		return retObj;
	}
}
