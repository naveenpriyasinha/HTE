package com.tcs.sgv.address.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxn;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class AddressVOGenerator extends ServiceImpl implements	VOGeneratorService,AddressVOGEN {
	public final String REQUEST_OBJECT="requestObj";
	public final String SERVICE_LOCATOR="serviceLocator";
	public final String ADDRESS_VO_GENERATOR="ADDRESS_VOGENERATOR";
	public final String BIRTH_PLACE_ADDRESS="birthPlaceAddress";
	public final String NATIVE_PLACE_ADDRESS="nativePlaceAddress";
	public final String PERMANENT_PLACE_ADDRESS="permanentPlaceAddress";
	public final String CURRENT_PLACE_ADDRESS="currentPlaceAddress";
	public final String EIS_EMP_TRN_KEY="EisEmpTrnKey";
	public List listOfVO=new ArrayList();
	public final String UPDATE_FLAG="updateflag";
	
	private static final Log logger = LogFactory.getLog(AddressVOGenerator.class);	
	public ResultObject generateMap(Map objectArgs) {
		
		ResultObject resultObject=new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest  request=(HttpServletRequest)objectArgs.get(REQUEST_OBJECT);
		ServiceLocator servLoc = (ServiceLocator) objectArgs.get(SERVICE_LOCATOR);
		
		String flag=null;
		FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, servLoc.getSessionFactory());
		FrmServiceMst servDetails = servDetailsImpl.readService(ADDRESS_VO_GENERATOR);
		try{ 
			if(StringUtility.getParameter(UPDATE_FLAG, request)!=null){
				flag=StringUtility.getParameter(UPDATE_FLAG, request);
				resultObject = servLoc.executeService(servDetails,objectArgs);	
			}else{
				flag="";
			}
			
		String str_Permanent = StringUtility.getParameter("radPermanentAddress", request);
		String str_Current = StringUtility.getParameter("radCurrentAddress", request);
		objectArgs.put("per_key",str_Permanent);
		objectArgs.put("cur_key",str_Current);
		HrEisEmpDtlTxn eisEmpTrn=new  HrEisEmpDtlTxn();
		if(objectArgs.containsKey(BIRTH_PLACE_ADDRESS)){
			CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get(BIRTH_PLACE_ADDRESS);
			eisEmpTrn.setCmnAddressMstByEmpBirthPlaceAddressId(cmnAddressMst);
			logger.info("The index value in Birth Place Address is ::::>>"+listOfVO.size());
		}
		
		if(objectArgs.containsKey(NATIVE_PLACE_ADDRESS)) {
			CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get(NATIVE_PLACE_ADDRESS);
			eisEmpTrn.setCmnAddressMstByEmpNativePlaceAddressId(cmnAddressMst);
			logger.info("The index value in Native Place Address is ::::>>"+listOfVO.size());
		}else{logger.info("asdfasdfsad NATIVE  1231231231231231312 VOGEN::");}
		if(objectArgs.containsKey(PERMANENT_PLACE_ADDRESS)){
			
			CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get(PERMANENT_PLACE_ADDRESS);
			eisEmpTrn.setCmnAddressMstByEmpPermanentAddressId(cmnAddressMst);
			logger.info("The index value in Permanent Place Address is ::::>>"+listOfVO.size());
		}
		if(objectArgs.containsKey(CURRENT_PLACE_ADDRESS)){
			
			CmnAddressMst cmnAddressMst=(CmnAddressMst)objectArgs.get(CURRENT_PLACE_ADDRESS);
			eisEmpTrn.setCmnAddressMstByEmpCurrentAddressId(cmnAddressMst);
			logger.info("The index value in Current Place Address is ::::>>"+listOfVO.size());
		}
	
		listOfVO.add(eisEmpTrn);
		objectArgs.put(EIS_EMP_TRN_KEY, listOfVO);
		objectArgs.put("updateFlag",flag);
		resultObject.setResultValue(objectArgs);
		
		
	}catch(Exception e){
		logger.error(e);
	}
	return resultObject;
	}		

}