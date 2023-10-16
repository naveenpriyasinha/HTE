
package com.tcs.sgv.address.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.address.dao.AddressApproveDao;
import com.tcs.sgv.address.dao.AddressApproveDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl_WF;
import com.tcs.sgv.eis.dao.EmpInfoDAO_WF;
import com.tcs.sgv.eis.dao.HrEisEmpTrnDaoImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxn;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxnId;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMstHst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class AddressApproveServiceImpl extends ServiceImpl implements AddressApprove
{
	
	public final String SERVICE_LOCATOR="serviceLocator";
	public final String REQUEST_ID="requestParam";
	public final String VIEW_NAME_APPROVEREJECT="approveOrRejectRequest";
	public final String ADDR_NAME="addrName";
	public final String ADDRESS_ID="addressId";
	public final String BIRTH_PLACE_ADDRESS="birthPlaceAddress";
	public final String NATIVE_PLACE_ADDRESS="nativePlaceAddress";
	public final String PERMANENT_PLACE_ADDRESS="permanentPlaceAddress";
	public final String CURRENT_PLACE_ADDRESS="currentPlaceAddress";
	public final String ADDRESS_VO_GENERATOR="ADDRESS_VOGENERATOR";
	public final String REQUEST_PARAMETER="reqId";
	public final String BIRTH_PLACE_ADDRESS_UPDATE="birthPlaceAddressUpdate";
	public final String NATIVE_PLACE_ADDRESS_UPDATE="nativePlaceAddressUpdate";
	public final String PERMANENT_PLACE_ADDRESS_UPDATE="permanentPlaceAddressUpdate";
	public final String CURRENT_PLACE_ADDRESS_UPDATE="currentPlaceAddressUpdate";
	public final String FALSE="false";
	public final String TRUE="true";
	public final String BIRTH_UPDATE_FLAG="birthUpdateFlag";
	public final String CURRENT_UPDATE_FLAG="currentUpdateFlag";
	public final String NATIVE_UPDATE_FLAG="nativeUpdateFlag";
	public final String PERMANENT_UPDATE_FLAG="permanentUpdateFlag";
	public final String BIRTH_FLAG="birthFlag";
	public final String CURRENT_FLAG="currentFlag";
	public final String NATIVE_FLAG="nativeFlag";
	public final String PERMANENT_FLAG="permanentFlag";
	public final String REJECT="R";
	public final String APPROVE="A";
	public final String CITY="City";
	public final String MODE="mode";
	public final String ADD="ADD";
	public final String HR_EIS_EMP_MST="HrEisEmpMst";
	public final String HR_EIS_EMP_TRN="HrEisEmpDtlTxn";
	public final String GET_ADDRESS_VO="GET_ADDRESS_VO"; 
	public final String XML_FILE_ONE="xml";
	public final String XML_FILE_TWO="xml1";
	public final String VIEW_NAME="approvedRejected";
	public final String ACTION_FLAG="actionFlag";
	private static final Log logger = LogFactory.getLog(AddressApproveServiceImpl.class);
	
	public ResultObject approveAddress(Map<String, Object> objectArgs)
	{
		String updateAddressFlag="";
		String actionFlag = "A";
		
		ResultObject resultObject=new ResultObject(ErrorConstants.SUCCESS);
		Map<String, Object> mapOfFinal=null;
		List<HrEisEmpDtlTxn> listOfAddress=null;
		
		HrEisEmpDtlTxn eisEmpTrn=null;
		
		ServiceLocator serviceLocator=(ServiceLocator)objectArgs.get(SERVICE_LOCATOR);
		AddressApproveDao approveDao=new AddressApproveDaoImpl(HrEisEmpDtlTxn.class,serviceLocator.getSessionFactory());
		String requestId=(String)objectArgs.get(REQUEST_ID);
		mapOfFinal=approveDao.getAddressToApprove(Long.parseLong(requestId)); 
		listOfAddress=(ArrayList)mapOfFinal.get(HR_EIS_EMP_TRN);
		try
		{
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serviceLocator.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl.readService("POPULATE_ADDRESS");
			
			Date crtdDt=null;
			long enEmpId=0;
				if(listOfAddress != null && !listOfAddress.isEmpty())
				{
					eisEmpTrn=listOfAddress.get(0);
					if (eisEmpTrn != null)
					{
						crtdDt = eisEmpTrn.getCreatedDate();
						enEmpId = eisEmpTrn.getOrgEmpMst().getEmpId();
						
						long requserId =eisEmpTrn.getOrgUserMstByCreatedBy().getUserId();
						/*ServiceLocator servLoc = (ServiceLocator) objectArgs.get(SERVICE_LOCATOR);
						GeneralEmpInfoDaoImpl GemEmpinfo = new GeneralEmpInfoDaoImpl(OrgEmpMst.class,servLoc.getSessionFactory()); 
						
						Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
						long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
						
				        GeneralEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(requserId,langId);
				        resultObject.setResultCode(ErrorConstants.SUCCESS);
				        
					    try
					    {
				        	if(EmpDetailsVO!=null && EmpDetailsVO.getAddressId()!=0)
							{
					            AddressServiceImpl addressServiceImpl = new AddressServiceImpl();  
					            AddressDAO addressDAO = new AddressDAOImpl(CmnAddressMst.class,servLoc.getSessionFactory());
					            CmnAddressMst cmnAddressMst =  addressDAO.read(EmpDetailsVO.getAddressId());
				            	String stringAddress = addressServiceImpl.getAddressDescription(cmnAddressMst,objectArgs);
				            	objectArgs.put("address", stringAddress);
							}
						}
						catch(Exception e)
						{
							e.getMessage();
						}
				        
				        objectArgs.put("EmpDet", EmpDetailsVO);*/
						
						objectArgs.put("modId",300021l);
						objectArgs.put("requestId", Long.valueOf(requestId));
						objectArgs.put("forUserId", requserId);
						resultObject = serviceLocator.executeService("SHOW_INBOX_EMP_DETAIL", objectArgs);
						
						actionFlag = eisEmpTrn.getActionFlag();
						updateAddressFlag = eisEmpTrn.getAddressTypeFlag();
						if(actionFlag.equalsIgnoreCase("P")==false){actionFlag="A";}
						
						if(eisEmpTrn.getCmnAddressMstByEmpBirthPlaceAddressId() !=null)
						{
							objectArgs.put(ADDR_NAME, BIRTH_PLACE_ADDRESS_UPDATE);
							objectArgs.put("addressObject", eisEmpTrn.getCmnAddressMstByEmpBirthPlaceAddressId());
							resultObject = serviceLocator.executeService(servDetails, objectArgs);			
						}
						
						if(eisEmpTrn.getCmnAddressMstByEmpNativePlaceAddressId() !=null)
						{
							objectArgs.put(ADDR_NAME, NATIVE_PLACE_ADDRESS_UPDATE);
							objectArgs.put("addressObject", eisEmpTrn.getCmnAddressMstByEmpNativePlaceAddressId());
							resultObject = serviceLocator.executeService(servDetails, objectArgs);	
						}
						
						if(eisEmpTrn.getCmnAddressMstByEmpPermanentAddressId() !=null)
						{
							objectArgs.put(ADDR_NAME, PERMANENT_PLACE_ADDRESS_UPDATE);
							objectArgs.put("addressObject", eisEmpTrn.getCmnAddressMstByEmpPermanentAddressId());
							resultObject = serviceLocator.executeService(servDetails, objectArgs);
						}
						
						if(eisEmpTrn.getCmnAddressMstByEmpCurrentAddressId() !=null)
						{
							objectArgs.put(ADDR_NAME, CURRENT_PLACE_ADDRESS_UPDATE);
							objectArgs.put("addressObject", eisEmpTrn.getCmnAddressMstByEmpCurrentAddressId());
							resultObject = serviceLocator.executeService(servDetails, objectArgs);
						}
				}
			}
			
			EmpInfoDAO_WF empInfoDAO_WF = new EmpInfoDAOImpl_WF(HrEisEmpMst.class,serviceLocator.getSessionFactory());
			HrEisEmpMstHst eisEmpMstHst = empInfoDAO_WF.getHrEisEmpHstDataByEmpIdandDate(enEmpId, crtdDt);
			
			
			if (eisEmpMstHst != null)
			{
				if (eisEmpMstHst.getCmnAddressMstByEmpBirthPlaceAddressId() != null) 
				{
					objectArgs.put(ADDR_NAME, BIRTH_PLACE_ADDRESS);
					objectArgs.put("addressObject", eisEmpMstHst.getCmnAddressMstByEmpBirthPlaceAddressId());
					resultObject = serviceLocator.executeService(servDetails, objectArgs);
				}
				
				if (eisEmpMstHst.getCmnAddressMstByEmpCurrentAddressId() != null) 
				{
					objectArgs.put(ADDR_NAME, CURRENT_PLACE_ADDRESS);
					objectArgs.put("addressObject", eisEmpMstHst.getCmnAddressMstByEmpCurrentAddressId());
					resultObject = serviceLocator.executeService(servDetails, objectArgs);
				}
				if (eisEmpMstHst.getCmnAddressMstByEmpNativePlaceAddressId() != null) 
				{
					objectArgs.put(ADDR_NAME, NATIVE_PLACE_ADDRESS);
					objectArgs.put("addressObject", eisEmpMstHst.getCmnAddressMstByEmpNativePlaceAddressId());
					resultObject = serviceLocator.executeService(servDetails, objectArgs);
				}
				if (eisEmpMstHst.getCmnAddressMstByEmpPermanentAddressId() != null) 
				{
					objectArgs.put(ADDR_NAME, PERMANENT_PLACE_ADDRESS);
					objectArgs.put("addressObject", eisEmpMstHst.getCmnAddressMstByEmpPermanentAddressId());
					resultObject = serviceLocator.executeService(servDetails, objectArgs);
				}
			}
			
			objectArgs.put(REQUEST_PARAMETER, requestId);
			if(updateAddressFlag.contains("CA")==true)
			{
				objectArgs.put(CURRENT_FLAG, true);
				objectArgs.put(CURRENT_UPDATE_FLAG, true);
			}
			if(updateAddressFlag.contains("NA")==true)
			{
				objectArgs.put(NATIVE_FLAG, true);
				objectArgs.put(NATIVE_UPDATE_FLAG, true);
			}
			if(updateAddressFlag.contains("PA")==true)
			{
				objectArgs.put(PERMANENT_FLAG, true);
				objectArgs.put(PERMANENT_UPDATE_FLAG, true);
			}
			if(updateAddressFlag.contains("BA")==true)
			{
				objectArgs.put(BIRTH_FLAG, true);
				objectArgs.put(BIRTH_UPDATE_FLAG, true);
			}														
		
		}catch(Exception e){			
			logger.error(e);
		}
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName(VIEW_NAME_APPROVEREJECT);
		resultObject.setResultCode(ErrorConstants.SUCCESS);		
		return resultObject;
	}

	public ResultObject approvedAddress(Map objectArgs)
	{
		ResultObject resultObject=new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			ServiceLocator servLoc = (ServiceLocator) objectArgs.get(SERVICE_LOCATOR);
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());	
			OrgPostMstDaoImpl orgDaoImpl  = new OrgPostMstDaoImpl(OrgPostMst.class,servLoc.getSessionFactory());
			OrgPostMst orgPostMstVoFrom =  orgDaoImpl.getPrimaryPost(StringUtility.convertToLong(loginMap.get("userId").toString()), langId);

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, servLoc.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);

			List<HrEisEmpDtlTxn> listOfTrnObject=null;
			List<HrEisEmpDtlTxn> listOfHrEisEmpMstObj=null;
			List<HrEisEmpMst> listOfObject=null;
			List<Long> listOfId=new ArrayList<Long>();

			AddressApproveDao addressApproveDao=new AddressApproveDaoImpl(HrEisEmpMst.class,servLoc.getSessionFactory());
			long  reqId =Long.parseLong(objectArgs.get("corrId").toString()); 
			logger.info("corrs_Id======reqId=== IN approvedAddress==="+reqId);
			listOfHrEisEmpMstObj=addressApproveDao.getHrEisEmPMst(reqId);

			HrEisEmpDtlTxn eisEmpTrn=null;

			for (Iterator iter = listOfHrEisEmpMstObj.iterator(); iter.hasNext();)
			{
				HrEisEmpDtlTxn element = (HrEisEmpDtlTxn) iter.next();
				listOfId.add(element.getOrgEmpMst().getEmpId());
			}
			listOfObject=addressApproveDao.getObjectForHrEisEmpMst(listOfId);
			int index=0;
			Date date = new Date();
			for (Iterator iter = listOfObject.iterator(); iter.hasNext();) 
			{
				HrEisEmpMst element = (HrEisEmpMst) iter.next();
				eisEmpTrn=listOfHrEisEmpMstObj.get(index);
				if(eisEmpTrn.getCmnAddressMstByEmpBirthPlaceAddressId()!=null)
				{
					element.setCmnAddressMstByEmpBirthPlaceAddressId(eisEmpTrn.getCmnAddressMstByEmpBirthPlaceAddressId());
				}
				if(eisEmpTrn.getCmnAddressMstByEmpCurrentAddressId()!=null)
				{
					element.setCmnAddressMstByEmpCurrentAddressId(eisEmpTrn.getCmnAddressMstByEmpCurrentAddressId());
				}
				if(eisEmpTrn.getCmnAddressMstByEmpNativePlaceAddressId()!=null)
				{
					element.setCmnAddressMstByEmpNativePlaceAddressId(eisEmpTrn.getCmnAddressMstByEmpNativePlaceAddressId());
				}
				if(eisEmpTrn.getCmnAddressMstByEmpPermanentAddressId()!=null)
				{
					element.setCmnAddressMstByEmpPermanentAddressId(eisEmpTrn.getCmnAddressMstByEmpPermanentAddressId());
				}
				element.setUpdatedDate(date);			
				element.setOrgUserMstByUpdatedBy(orgUserMst);   
				addressApproveDao.update(element);
			}

			listOfTrnObject=addressApproveDao.getHrEisEmPMst(reqId);
			for (Iterator iter = listOfTrnObject.iterator(); iter.hasNext();) 
			{
				HrEisEmpDtlTxn element = (HrEisEmpDtlTxn) iter.next();
				element.setActionFlag(APPROVE);
				element.setUpdatedDate(date);
				element.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
				element.setOrgUserMstByUpdatedBy(orgUserMst);
				addressApproveDao.update(element);
			}
		}
		catch (Exception e) 
		{
			logger.error("Error While Approval of Address Request",e);
		}
		return resultObject;
	}
	
	public ResultObject rejectedRequest(Map objectArgs) 
	{
		long reqId=0;
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get(SERVICE_LOCATOR);
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());	
			OrgPostMstDaoImpl orgDaoImpl  = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMstVoFrom =  orgDaoImpl.getPrimaryPost(StringUtility.convertToLong(loginMap.get("userId").toString()), langId);

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			
			reqId =Long.parseLong(objectArgs.get("corrId").toString());  
			logger.info("corrs_Id======reqId= In AddressApproveServiceImpl - Rejection====="+reqId);
			

			HrEisEmpTrnDaoImpl hrEisEmpTrnDaoImpl = new HrEisEmpTrnDaoImpl(HrEisEmpDtlTxn.class,serv.getSessionFactory());

			HrEisEmpDtlTxn eisEmpTrn_en = new HrEisEmpDtlTxn(); 
			HrEisEmpDtlTxnId eisEmpTrnId_en = new HrEisEmpDtlTxnId(); 
			eisEmpTrnId_en.setReqId(reqId);
			eisEmpTrnId_en.setLangId(1l);
			
			eisEmpTrn_en=(HrEisEmpDtlTxn) hrEisEmpTrnDaoImpl.read(eisEmpTrnId_en);
			eisEmpTrn_en.setActionFlag(REJECT);
			
			eisEmpTrn_en.setUpdatedDate(new java.sql.Timestamp((new java.util.Date()).getTime()));
			eisEmpTrn_en.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
			eisEmpTrn_en.setOrgUserMstByUpdatedBy(orgUserMst);
			
			hrEisEmpTrnDaoImpl.update(eisEmpTrn_en);
		
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
		}
		catch (Exception e) 
		{
			logger.error("Error While Rejection of Address Request",e);
		}
		return  resultObject;
	}
	
	public ResultObject cancleAddressRequestSRVC(Map<String, Object> objectArgs) 
	{
		long reqId=0;
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get(SERVICE_LOCATOR);
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());	
			OrgPostMstDaoImpl orgDaoImpl  = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMstVoFrom =  orgDaoImpl.getPrimaryPost(StringUtility.convertToLong(loginMap.get("userId").toString()), langId);

			long userID = Long.parseLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			
			reqId =Long.parseLong(objectArgs.get("corrId").toString());  
			logger.info("corrs_Id======reqId= In AddressApproveServiceImpl - cancel====="+reqId);
			
			HrEisEmpTrnDaoImpl hrEisEmpTrnDaoImpl = new HrEisEmpTrnDaoImpl(HrEisEmpDtlTxn.class,serv.getSessionFactory());

			HrEisEmpDtlTxn eisEmpTrn_en = new HrEisEmpDtlTxn(); 
			HrEisEmpDtlTxnId eisEmpTrnId_en = new HrEisEmpDtlTxnId(); 
			eisEmpTrnId_en.setReqId(reqId);
			eisEmpTrnId_en.setLangId(1l);
			
			eisEmpTrn_en=(HrEisEmpDtlTxn) hrEisEmpTrnDaoImpl.read(eisEmpTrnId_en);
			eisEmpTrn_en.setActionFlag("C");
			
			eisEmpTrn_en.setUpdatedDate(new java.sql.Timestamp((new java.util.Date()).getTime()));
			eisEmpTrn_en.setOrgPostMstByUpdatedByPost(orgPostMstVoFrom);
			eisEmpTrn_en.setOrgUserMstByUpdatedBy(orgUserMst);
			
			hrEisEmpTrnDaoImpl.update(eisEmpTrn_en);
		
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
		} catch (Exception e) 
		{
			logger.error("Error in cancel AddressDtlsRequest method  "+e);
		}
		return resultObject;
	}
}
