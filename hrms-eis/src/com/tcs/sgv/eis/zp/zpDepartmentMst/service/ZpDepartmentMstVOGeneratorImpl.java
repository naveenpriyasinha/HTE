/**
 * <This class get details from application >
 *
 * @class name  : <ZpAdminOfficeVOGeneratorImpl >
 * @author		 : <Code Skeleton/Generator Tool - Developed By Durgesh (486841)/Zeal (479123)>
 * @version	 : <1.0>
*/

package com.tcs.sgv.eis.zp.zpDepartmentMst.service;
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

import com.tcs.sgv.eis.zp.zpDistrictOffice.dao.ZpDistrictOfficeDAO;
import com.tcs.sgv.eis.zp.zpDistrictOffice.dao.ZpDistrictOfficeDAOImpl;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;

public class ZpDepartmentMstVOGeneratorImpl extends ServiceImpl implements  ZpDepartmentMstVOGenerator
{
	private static final Logger logger = Logger.getLogger(ZpDepartmentMstVOGeneratorImpl.class);

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

		logger.info("Entered into generateMap of Department Mst");
		Long seladminDept = Long.valueOf(!StringUtility.getParameter("seladminDept",request).equalsIgnoreCase("") ? StringUtility.getParameter("seladminDept",request) : "0");
		String txtdeptName = !StringUtility.getParameter("txtdeptName",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtdeptName",request) : "";
		Long txtCode = Long.valueOf(!StringUtility.getParameter("txtCode",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtCode",request) : "0");
		logger.info("seladminDept:::"+seladminDept);
		logger.info("txtdeptName:::"+txtdeptName);
		logger.info("txtCode:::"+txtCode);
		
		/* Setting Values into VO */

		/*if(updateFlag.equalsIgnoreCase("true")){
			ZpDistrictOfficeDAO readZpAdminOfficeMstDAOImpl = new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class,serviceLocator.getSessionFactory());
			zpAdminOfficeMstVO = (ZpDistrictOfficeMst) readZpAdminOfficeMstDAOImpl.read(zpAdminOfficeMstID);
		}*/


		//zpAdminOfficeMstVO.setOfficeName(strTxtAdminOfficeName);
		//zpAdminOfficeMstVO.setOfficeCode(lTxtCode);

		objectArgs.put("seladminDept", seladminDept);
		objectArgs.put("txtdeptName", txtdeptName);
		objectArgs.put("txtCode", txtCode);

	
		validateZpAdminOfficeDetail(objectArgs);
		logger.info("Exit from generateMap of Zp");
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		return objRes;
	}

	
	public ResultObject retrieveZpAdminOfficeDtls(Map objectArgs) throws Exception{
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");

		logger.info("Entered into retrieve of ZpAdminOfficeVOGeneratorImpl");
		ZpDistrictOfficeMst zpAdminOfficeMstVO = new ZpDistrictOfficeMst();

		String strTxtAdminOfficeName = !StringUtility.getParameter("txtAdminOfficeName",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtAdminOfficeName",request) : "";
		Long lTxtCode = Long.valueOf(!StringUtility.getParameter("txtCode",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtCode",request) : "0");
		Long zpAdminOfficeMstID = Long.valueOf(!StringUtility.getParameter("hdnOfcId",request).equalsIgnoreCase("") ? StringUtility.getParameter("hdnOfcId",request) : "0");


		

		objectArgs.put("zpAdminOfficeMstVO", zpAdminOfficeMstVO);

	
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
