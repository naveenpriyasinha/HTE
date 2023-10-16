package com.tcs.sgv.dcps.service;

//com.tcs.sgv.dcps.service.DcpsDdoSchemeVOGenerator

import java.util.Date;
import common.Logger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoSchemeDAO;
import com.tcs.sgv.dcps.dao.DdoSchemeDAOImpl;
import com.tcs.sgv.dcps.valueobject.RltDcpsDdoScheme;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
public class DdoSchemeVOGenerator extends ServiceImpl implements
		VOGeneratorService

{
	private final static Logger gLogger = Logger.getLogger(DdoBillGroupVOGenerator.class);
	public ResultObject generateMap(Map inputMap) {
		// TODO Auto-generated method stub

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		// inputMap.get("requestObj");
		RltDcpsDdoScheme dcpsddoscheme = null;

		RltDcpsDdoScheme[] dcpsddoSubSchme = null;
		if (dcpsddoscheme == null) {
			dcpsddoscheme = new RltDcpsDdoScheme();
		}

		try {

			inputMap.put("DcpsDdoScheme", dcpsddoscheme);
			dcpsddoscheme = generateSchemeVOMap(inputMap);
			
			dcpsddoSubSchme= generateSubSchemeVoMAp(inputMap);//added for sub scheme mapping 
			inputMap.put("DcpsDdoScheme", dcpsddoscheme);
			inputMap.put("dcpsddoSubSchme", dcpsddoSubSchme);
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

	private RltDcpsDdoScheme generateSchemeVOMap(Map inputMap) throws Exception {

		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

		DdoSchemeDAO lObjDdoSchemeDAO = new DdoSchemeDAOImpl(
				RltDcpsDdoScheme.class, serv.getSessionFactory());

		DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
				.getSessionFactory());
		String lStrSchemeCode = null;
		String lStrSubSchemeCode = null;


		if ((StringUtility.getParameter("txtSchemeCode", request) != null)
				&& (!StringUtility.getParameter("txtSchemeCode", request)
						.equals(""))) {
			lStrSchemeCode = (StringUtility.getParameter("txtSchemeCode",
					request));
		}

		//Code stareted by saurabh  for addition of subScheme code
		if ((StringUtility.getParameter("txtSubSchemeCode", request) != null)
				&& (!StringUtility.getParameter("txtSubSchemeCode", request)
						.equals(""))) {
			lStrSubSchemeCode = (StringUtility.getParameter("txtSubSchemeCode",
					request));
		}
		//Code ended
		
		
		RltDcpsDdoScheme dcpsddoscheme = new RltDcpsDdoScheme();

		try {

			Long LangId = SessionHelper.getLangId(inputMap);
			Long LocId = SessionHelper.getLocationId(inputMap);
			Long DbId = SessionHelper.getDbId(inputMap);
			Long PostId = SessionHelper.getPostId(inputMap);
			Long UserId = SessionHelper.getUserId(inputMap);
			Date CreatedDate = DBUtility.getCurrentDateFromDB();

			Long UpdatedPostId = SessionHelper.getPostId(inputMap);
			Long UpdatedUserId = SessionHelper.getUserId(inputMap);
			Date UpdatedDate = DBUtility.getCurrentDateFromDB();
			String lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(PostId);
			Boolean passFlag =true;

			if ((StringUtility.getParameter("txtSchemeCode", request) != null)
					&& (!StringUtility.getParameter("txtSchemeCode", request)
							.equals(""))) {
				dcpsddoscheme.setDcpsSchemeCode(lStrSchemeCode);
			}

			
			//Code started by saurabh  for addition of subScheme code
			if ((StringUtility.getParameter("txtSubSchemeCode", request) != null)
					&& (!StringUtility.getParameter("txtSubSchemeCode", request)
							.equals(""))) {
				dcpsddoscheme.setDcpsSubSchemeCode(lStrSubSchemeCode);
				//passFlag =false;
				gLogger.info("passFlag.."+passFlag);
			}
			//Code ended
			Boolean schemeAddedOrNot = true;

			List lListSchemeCodeAndNames = lObjDdoSchemeDAO
					.getSchemeListForDDO(lStrDDOCode,0,lStrSchemeCode);

			for (int lInt = 0; lInt < lListSchemeCodeAndNames.size(); lInt++) {
				Object[] lObjTemp = (Object[]) lListSchemeCodeAndNames
						.get(lInt);
				String lStrSchemeCodeFromDB = (String) lObjTemp[0];
				if (lStrSchemeCode.equals(lStrSchemeCodeFromDB)) {
					schemeAddedOrNot = false;
				}
			}

			inputMap.put("schemeAddedOrNot", schemeAddedOrNot);

			//dcpsddoscheme.setDcpsDdoCode(lStrDDOCode);
			dcpsddoscheme.setLangId(LangId);
			//dcpsddoscheme.setLocId(LocId);
			dcpsddoscheme.setDbId(DbId);
			dcpsddoscheme.setCreatedPostId(PostId);
			dcpsddoscheme.setCreatedUserId(UserId);
			dcpsddoscheme.setCreatedDate(CreatedDate);

			dcpsddoscheme.setUpdatedPostId(UpdatedPostId);
			dcpsddoscheme.setUpdatedUserId(UpdatedUserId);
			dcpsddoscheme.setUpdatedDate(UpdatedDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dcpsddoscheme;
	}
	
	//added by saurabh for subscheme
	
		private RltDcpsDdoScheme[] generateSubSchemeVoMAp(Map inputMap) throws Exception {


			HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
			serv = (ServiceLocator) inputMap.get("serviceLocator");	
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			long empId=0;
			long hrEmpId=0;
			long id=0;		
			Date sysdate = new Date();
			String lStrSchemeCode="";
			String lStrSubSchemeCode="";
			Long LangId = SessionHelper.getLangId(inputMap);
			Long LocId = SessionHelper.getLocationId(inputMap);
			Long DbId = SessionHelper.getDbId(inputMap);
			Long PostId = SessionHelper.getPostId(inputMap);
			Long UserId = SessionHelper.getUserId(inputMap);
			Date CreatedDate = DBUtility.getCurrentDateFromDB();

			Long UpdatedPostId = SessionHelper.getPostId(inputMap);
			Long UpdatedUserId = SessionHelper.getUserId(inputMap);
			Date UpdatedDate = DBUtility.getCurrentDateFromDB();
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			String lStrDDOCode="";
			if ((StringUtility.getParameter("cmbsubDdos", request) != null)
					&& (!StringUtility.getParameter("cmbsubDdos", request)
							.equals(""))) {
				lStrDDOCode = StringUtility.getParameter("cmbsubDdos", request);
			}
			gLogger.info("lStrDDOCode---in VO"+lStrDDOCode);
			//String lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(PostId);

			if ((StringUtility.getParameter("txtSchemeCode", request) != null)
					&& (!StringUtility.getParameter("txtSchemeCode", request)
							.equals(""))) {
				lStrSchemeCode=StringUtility.getParameter("txtSchemeCode", request);


			}


			if ((StringUtility.getParameter("txtSubSchemeCode", request) != null)
					&& (!StringUtility.getParameter("txtSubSchemeCode", request)
							.equals(""))) {
				lStrSubSchemeCode = (StringUtility.getParameter("txtSubSchemeCode",
						request));
			}
			//RltDcpsDdoScheme[] dcpsddoscheme = new RltDcpsDdoScheme[lStrSubSchemeCode1.length()];

			String[] lStrSubSchemeCodeArr = lStrSubSchemeCode.split("~");






			RltDcpsDdoScheme[] RltDcpsDdoSchemeArr = new RltDcpsDdoScheme[lStrSubSchemeCodeArr.length];

			for (Integer lInt = 0; lInt < lStrSubSchemeCodeArr.length; lInt++) {

			gLogger.info("LENGTH OF"+lStrSubSchemeCodeArr.length);
			//id =id+lInt;
			RltDcpsDdoScheme RltDcpsDdoScheme = new RltDcpsDdoScheme();
			//id=idGen.PKGeneratorWODBLOC("hr_gpf_multiple_Arrear_dtls",objectArgs);
			//logger.info("id for hr_gpf_multiple_Arrear_dtls:"+id);
			//lObjHrGPFMultipleArrDtls.setId(id);
			Long lDDOSchemesId = IFMSCommonServiceImpl.getNextSeqNum("RLT_DCPS_DDO_SCHEMES", inputMap);
			gLogger.info("lDDOSchemesId in ddo scheme:"+lDDOSchemesId);
			gLogger.info("lStrSchemeCode in ddo scheme:"+lStrSchemeCode);
			//id =id+lInt;
			RltDcpsDdoScheme.setDcpsSchemeCode(lStrSchemeCode);
			RltDcpsDdoScheme.setDcpsDdoSchemesId(lDDOSchemesId);
			//gLogger.info("lStrDDOCode in ddo scheme:"+lStrDDOCode);
			//RltDcpsDdoScheme.setDcpsDdoCode(lStrDDOCode);
			gLogger.info("LangId in ddo scheme:"+LangId);
			RltDcpsDdoScheme.setLangId(LangId);
			gLogger.info("LocId in ddo scheme:"+LocId);
			RltDcpsDdoScheme.setLocId(LocId);
			gLogger.info("DbId in ddo scheme:"+DbId);
			RltDcpsDdoScheme.setDbId(DbId);
			gLogger.info("PostId in ddo scheme:"+PostId);
			RltDcpsDdoScheme.setCreatedPostId(PostId);
			gLogger.info("UserId in ddo scheme:"+UserId);
			RltDcpsDdoScheme.setCreatedUserId(UserId);
			gLogger.info("CreatedDate in ddo scheme:"+CreatedDate);
			RltDcpsDdoScheme.setCreatedDate(CreatedDate);
			gLogger.info("UpdatedPostId in ddo scheme:"+UpdatedPostId);
			RltDcpsDdoScheme.setUpdatedPostId(UpdatedPostId);
			gLogger.info("UpdatedUserId in ddo scheme:"+UpdatedUserId);
			RltDcpsDdoScheme.setUpdatedUserId(UpdatedUserId);
			gLogger.info("UpdatedDate in ddo scheme:"+UpdatedDate);
			RltDcpsDdoScheme.setUpdatedDate(UpdatedDate);
			gLogger.info("lStrSubSchemeCodeArr in ddo scheme:"+lStrSubSchemeCodeArr[lInt]);
			String lStrSubSchemeCodetmp=lStrSubSchemeCodeArr[lInt];
			gLogger.info("lStrSubSchemeCodetmp in ddo scheme:"+lStrSubSchemeCodetmp);
			RltDcpsDdoScheme.setDcpsSubSchemeCode(lStrSubSchemeCodetmp);

				DdoSchemeDAO lObjDdoSchemeDAO = new DdoSchemeDAOImpl(
						RltDcpsDdoScheme.class, serv.getSessionFactory());

				Boolean SubSchemeAddedOrNot = true;
				//Boolean SubSchemeAddedOrNot=true;
				List lListSchemeCodeAndNames = lObjDdoSchemeDAO
				.getSchemeListForDDO(lStrDDOCode,1,lStrSchemeCode);

				for (int lInt1 = 0; lInt1 < lListSchemeCodeAndNames.size(); lInt1++) {
					Object[] lObjTemp = (Object[]) lListSchemeCodeAndNames.get(lInt1);
					if (lObjTemp[2]!=null && !(lObjTemp[2].equals("")))
					{
						String lStrSchemeCodeFromDB = (String) lObjTemp[2];
						if (lStrSubSchemeCodetmp.equals(lStrSchemeCodeFromDB)) {
							SubSchemeAddedOrNot = false;
						}
					}
				}

				inputMap.put("SubSchemeAddedOrNot", SubSchemeAddedOrNot);
				gLogger.info("SubSchemeAddedOrNot***********"+SubSchemeAddedOrNot);

				RltDcpsDdoSchemeArr[lInt] = RltDcpsDdoScheme;
			}
			return RltDcpsDdoSchemeArr;

		}

}
