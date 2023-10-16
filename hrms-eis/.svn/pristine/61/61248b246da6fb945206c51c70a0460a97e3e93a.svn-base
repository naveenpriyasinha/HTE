package com.tcs.sgv.address.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.address.dao.AddressDao;
import com.tcs.sgv.address.dao.AddressDaoImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl_WF;
import com.tcs.sgv.eis.dao.EmpInfoDAO_WF;
import com.tcs.sgv.eis.service.HrmsCommonMessageServImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxn;
import com.tcs.sgv.eis.valueobject.HrEisEmpDtlTxnId;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.delegate.AddressDelegate;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class AddressServiceImpl extends ServiceImpl implements Address{
	public final String SERVICE_LOCATOR="serviceLocator";
	public final String BASE_LOGIN_MAP="baseLoginMap";
	public final String USER_ID="userId";
	public final String EIS_EMP_TRN_KEY="EisEmpTrnKey";
	public final String DB_ID="dbId";
	public final String LOCATION_ID="locationId";
	public final String LANG_ID="langId";
	public final String PRIMARY_POST_ID="primaryPostId";
	public final String TABLE_NAME="tablename";
	public final String CMN_ADDRSS_MST="CMN_ADDRESS_MST";
	public final String GENERATE_ID_SRVC="GENERATE_ID_SRVC";
	public final String VIEW_NAME="empEditList1";
	public final String BIRTH_PLACE_ADDRESS="birthPlaceAddress";
	public final String NATIVE_PLACE_ADDRESS="nativePlaceAddress";
	public final String PERMANENT_PLACE_ADDRESS="permanentPlaceAddress";
	public final String CURRENT_PLACE_ADDRESS="currentPlaceAddress";
	public final String UPDATE_FLAG="updateFlag";
	public final static long CHANGE_ADDRESS_MODID=300021;
	public final static String DOC_ID="300017";
	public final static String CORR_DESC="Change Employee Address Correspondence";
	
	
	com.tcs.sgv.hod.common.service.AddressServiceImpl a = new com.tcs.sgv.hod.common.service.AddressServiceImpl();
	
	private static final Log logger = LogFactory.getLog(AddressServiceImpl.class);	
	public ResultObject saveAddress(Map objectArgs) 
	{
			ResultObject resultObject=new ResultObject(ErrorConstants.SUCCESS);
			ServiceLocator serviceLocator=(ServiceLocator)objectArgs.get(SERVICE_LOCATOR);
			Map loginMap=(Map)objectArgs.get(BASE_LOGIN_MAP);
			String str_per = objectArgs.get("per_key").toString();
			String str_cur = objectArgs.get("cur_key").toString();
			long userId=Long.parseLong(loginMap.get(USER_ID).toString());
			long dbId=Long.parseLong(loginMap.get(DB_ID).toString());
			long locationID=Long.parseLong(loginMap.get(LOCATION_ID).toString());
			long primaryPostId=Long.parseLong(loginMap.get(PRIMARY_POST_ID).toString());

			HrEisEmpDtlTxn eisEmpTrn=null;
			String updateFlag=(String)objectArgs.get(UPDATE_FLAG);
			
			if(!(objectArgs.get(UPDATE_FLAG).equals(""))){
				logger.info("The updateFlag is not Null");
				updateFlag=(String)objectArgs.get(UPDATE_FLAG);
			}else{
				updateFlag="";
			}
			
			logger.info("======updateFlag===="+ updateFlag);
			ArrayList listOfVO=(ArrayList)objectArgs.get(EIS_EMP_TRN_KEY);
			long addressId=0;
			long reqId=0;
			AddressDao addressDao=new AddressDaoImpl(HrEisEmpDtlTxn.class,serviceLocator.getSessionFactory());

			/** Start of Getting the OrgUserMst instance*/
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serviceLocator.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			/** End of Getting the OrgUserMst instance*/
			
			/**Start of Getting the OrgEmpMst instance*/
			EmpInfoDAO_WF empInfoDAO_WF = new EmpInfoDAOImpl_WF(OrgEmpMst.class,serviceLocator.getSessionFactory());
			OrgEmpMst empMst=empInfoDAO_WF.getEmployee(orgUserMst, 1l);
			/**End of Getting the OrgEmpMst instance*/
			
			/**Start of Getting Cmn Data Base Mst*/
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serviceLocator.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			/**End of Getting Cmn Data Base Mst*/
			
			/**Start of Getting the Cmn Location Mst instance*/
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serviceLocator.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locationID);
			/**End of Getting the Cmn Location Mst instance*/
			
			/**Start of Getting the Org Post master instance*/
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serviceLocator.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(primaryPostId);
			/**End of Getting the Org Post master instance*/
			
			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			HrEisEmpDtlTxnId eisEmpTrnId=new HrEisEmpDtlTxnId();
			
			eisEmpTrn=(HrEisEmpDtlTxn)listOfVO.get(0);
			eisEmpTrn.setCmnLocationMst(cmnLocationMst);
			eisEmpTrn.setCmnDatabaseMst(cmnDatabaseMst);
			eisEmpTrn.setOrgUserMstByCreatedBy(orgUserMst);
			eisEmpTrn.setOrgPostMstByCreatedByPost(orgPostMst);
			eisEmpTrn.setCreatedDate(new Date());
			eisEmpTrn.setActionFlag("P");
			
			reqId=HrmsCommonMessageServImpl.createCorr(objectArgs, serviceLocator, DOC_ID,CORR_DESC);//For Creating Corrospondance Id
			logger.info("==========corr_id== of Change Address===="+reqId);
			
			eisEmpTrnId.setReqId(reqId);
			eisEmpTrnId.setLangId(1l); //For inserting record in english only
			eisEmpTrn.setId(eisEmpTrnId);
			eisEmpTrn.setOrgEmpMst(empMst);
			
			CmnAddressMst cmm=new CmnAddressMst();//For Native Address
			
			AddressDaoImpl impl=new AddressDaoImpl(HrEisEmpMst.class,serviceLocator.getSessionFactory());
			List<HrEisEmpMst> listOfUser=impl.getUser(userId);	
			if (eisEmpTrn.getCmnAddressMstByEmpBirthPlaceAddressId() != null && updateFlag.contains("BA"))
			{
				CmnAddressMst cm = eisEmpTrn.getCmnAddressMstByEmpBirthPlaceAddressId();
				addressId = getNextAddressId(objectArgs);
				objectArgs.put("mode", "ADD");
				eisEmpTrn.setAddressTypeFlag(updateFlag);
				cm.setAddressId(addressId);
				cm = AddressDelegate.setAddressObjectFields(cm, objectArgs);
				eisEmpTrn.setCmnAddressMstByEmpBirthPlaceAddressId(cm);
				//addressDao.create(cm);
			} 
			if (eisEmpTrn.getCmnAddressMstByEmpNativePlaceAddressId() != null && updateFlag.contains("NA"))
			{
				addressId = getNextAddressId(objectArgs);
				cmm = eisEmpTrn.getCmnAddressMstByEmpNativePlaceAddressId();
				objectArgs.put("mode", "ADD");
				eisEmpTrn.setAddressTypeFlag(updateFlag);
				cmm.setAddressId(addressId);
				cmm = AddressDelegate.setAddressObjectFields(cmm, objectArgs);
				eisEmpTrn.setCmnAddressMstByEmpNativePlaceAddressId(cmm);
				//addressDao.create(cmm);
			}  
			
			if (eisEmpTrn.getCmnAddressMstByEmpPermanentAddressId() != null && str_per.equalsIgnoreCase("N") && updateFlag.contains("PA")) 
			{
					CmnAddressMst cm = eisEmpTrn.getCmnAddressMstByEmpPermanentAddressId();
					addressId = getNextAddressId(objectArgs);
					objectArgs.put("mode", "ADD");				
					eisEmpTrn.setAddressTypeFlag(updateFlag);
					cm.setAddressId(addressId);
					cm = AddressDelegate.setAddressObjectFields(cm, objectArgs);
					eisEmpTrn.setCmnAddressMstByEmpPermanentAddressId(cm);
					//addressDao.create(cm);
			}
			else
			{
				if(!listOfUser.isEmpty() && updateFlag.contains("PA"))
				{
					if( cmm!=null && cmm.getAddressId()!=0 && updateFlag.contains("NA"))
					{
							eisEmpTrn.setCmnAddressMstByEmpPermanentAddressId(cmm);
							eisEmpTrn.setAddressTypeFlag(updateFlag);
					}
					else
					{
						if(listOfUser.get(0)!=null)
						{
							hrEisEmpMst = listOfUser.get(0);
							CmnAddressMst cm = hrEisEmpMst.getCmnAddressMstByEmpNativePlaceAddressId(); 
							eisEmpTrn.setCmnAddressMstByEmpPermanentAddressId(cm);
							eisEmpTrn.setAddressTypeFlag(updateFlag);
						}
					}
				}
			}
			
			if (eisEmpTrn.getCmnAddressMstByEmpCurrentAddressId() != null && str_cur.equalsIgnoreCase("N") && updateFlag.contains("CA")) 
			{
					
					CmnAddressMst cm = eisEmpTrn.getCmnAddressMstByEmpCurrentAddressId();
					addressId = getNextAddressId(objectArgs);
					objectArgs.put("mode", "ADD");
					eisEmpTrn.setAddressTypeFlag(updateFlag);
					cm.setAddressId(addressId);
					cm = AddressDelegate.setAddressObjectFields(cm, objectArgs);
					eisEmpTrn.setCmnAddressMstByEmpCurrentAddressId(cm);
					//addressDao.create(cm);
			}
			else
			{
				if(!listOfUser.isEmpty() && updateFlag.contains("CA"))
				{
					if(listOfUser.get(0)!=null)
					{
						if(updateFlag.contains("PA"))
						{
							
							CmnAddressMst cm = eisEmpTrn.getCmnAddressMstByEmpPermanentAddressId();
							eisEmpTrn.setCmnAddressMstByEmpCurrentAddressId(cm);
							eisEmpTrn.setAddressTypeFlag(updateFlag);
						}
						else
						{
							hrEisEmpMst = listOfUser.get(0);
							CmnAddressMst cm = hrEisEmpMst.getCmnAddressMstByEmpPermanentAddressId(); 
							eisEmpTrn.setCmnAddressMstByEmpCurrentAddressId(cm);
							eisEmpTrn.setAddressTypeFlag(updateFlag);
						}
					}
				}
			}
			//addressDao.create(eisEmpTrn);
			try
			{ 
				objectArgs.put("modId", CHANGE_ADDRESS_MODID);
				objectArgs.put("appReqId", reqId);
				HrmsCommonMessageServImpl.createModEmpRltTuple(serviceLocator, objectArgs, userId);
				resultObject=HrmsCommonMessageServImpl.getForwardToDetail(serviceLocator, objectArgs, DOC_ID, reqId);   
				String msg="EMP_CHANGE_ADD_REQUEST";
				objectArgs.put("status", "PENDING"); 
				objectArgs.put("msg", msg);  
			}catch(Exception e){
				logger.error(e);
			}
			resultObject.setResultCode(ErrorConstants.SUCCESS);			 			 
			resultObject.setResultValue(objectArgs);
			return resultObject;
	}

	private long getNextAddressId(Map objectRes) 
	{
		long newAddressId = 0;
		try 
		{
			newAddressId = IDGenerateDelegate.getNextId(CMN_ADDRSS_MST,objectRes);
			logger.info("CmnAddressMst PK===="+newAddressId);
		} 
		catch (Exception e)
		{
			logger.error("Error While Generating CmnAddressMst PK",e);	
		}
		return newAddressId;
	}
	
	public ResultObject getAddress(Map objectArgs)
	{
		logger.info("Inside the getAddress Method");
		
		String birthFlag="false";
		String nativeFlag="false";
		String permanentFlag="false";
		String currentFlag="false";
		String allEmpAddressXmlFile="";
		ResultObject resultObject=new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serviceLocator=(ServiceLocator)objectArgs.get(SERVICE_LOCATOR);
		AddressDao addressDao=new AddressDaoImpl(HrEisEmpDtlTxn.class,serviceLocator.getSessionFactory());
		Map mapOfVO=null;
		Map loginMap=(Map)objectArgs.get(BASE_LOGIN_MAP);
		long userId=Long.parseLong(loginMap.get(USER_ID).toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serviceLocator.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
		try
		{
			EmpInfoDAO_WF getAllEmpAddressDtls = new EmpInfoDAOImpl_WF(HrEisEmpMst.class, serviceLocator.getSessionFactory());
			HrEisEmpMst hrEisEmpMst = null;
			hrEisEmpMst=(HrEisEmpMst)getAllEmpAddressDtls.getHrEmpVOOnUserId(userId,1l);
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serviceLocator.getSessionFactory());
			FrmServiceMst servDetailsOfPopulate = servDetailsImpl.readService("POPULATE_ADDRESS");
			
			mapOfVO=addressDao.getAllAddress(userId);
			Map mapOfAddress=(HashMap)mapOfVO.get("listOfAddress");
			
			if(mapOfAddress.get(BIRTH_PLACE_ADDRESS)!=null)
			{
				objectArgs.put("addrName", "birthPlaceAddressReadOnly");
				objectArgs.put("addressObject", hrEisEmpMst.getCmnAddressMstByEmpBirthPlaceAddressId());
				resultObject = serviceLocator.executeService(servDetailsOfPopulate, objectArgs);
				birthFlag="true";
			}
			if(mapOfAddress.get(CURRENT_PLACE_ADDRESS)!=null)
			{
				objectArgs.put("addrName", "currentPlaceAddressReadOnly");
				objectArgs.put("addressObject", hrEisEmpMst.getCmnAddressMstByEmpCurrentAddressId());
				resultObject = serviceLocator.executeService(servDetailsOfPopulate, objectArgs);
				currentFlag="true";

				Long cm1 = (Long) mapOfAddress.get(PERMANENT_PLACE_ADDRESS);
				Long cm2 = (Long) mapOfAddress.get(CURRENT_PLACE_ADDRESS);
				if(cm1!=null &&  cm2!=null)
				{
					logger.info("==============current========="+ cm1 +"==="+ cm2);
					if(cm1.equals(cm2)) 
					{
						objectArgs.put("cur_key","Y");
					}
					else
					{
						objectArgs.put("cur_key","N");
					}
				}
			}
			else
			{
				objectArgs.put("cur_key","N");
			}

			if(mapOfAddress.get(NATIVE_PLACE_ADDRESS)!=null)
			{
				objectArgs.put("addrName", "nativePlaceAddressReadOnly");
				objectArgs.put("addressObject", hrEisEmpMst.getCmnAddressMstByEmpNativePlaceAddressId());
				resultObject = serviceLocator.executeService(servDetailsOfPopulate, objectArgs);
				nativeFlag="true";
			}
			if(mapOfAddress.get(PERMANENT_PLACE_ADDRESS)!=null)
			{
				objectArgs.put("addrName", "permanentAddressReadOnly");
				objectArgs.put("addressObject", hrEisEmpMst.getCmnAddressMstByEmpPermanentAddressId());
				resultObject = serviceLocator.executeService(servDetailsOfPopulate, objectArgs);
				permanentFlag="true";

				Long cm1 = (Long) mapOfAddress.get(PERMANENT_PLACE_ADDRESS);
				Long cm2 = (Long) mapOfAddress.get(NATIVE_PLACE_ADDRESS);
				if(cm1!=null &&  cm2!=null)
				{
					logger.info("==============pare========="+ cm1 +"==="+ cm2);
					if(cm1.equals(cm2)) 
					{
						objectArgs.put("per_key","Y");
					}
					else
					{
						objectArgs.put("per_key","N");
					}
				}
			}
			else
			{
				objectArgs.put("per_key","N");
			}
			
			if(hrEisEmpMst!=null)
			{
				allEmpAddressXmlFile = FileUtility.voToXmlFileByXStream(hrEisEmpMst);
			}
			logger.info("allEmpAddressXmlFile========"+allEmpAddressXmlFile);
			objectArgs.put("allEmpAddressXmlFile", allEmpAddressXmlFile);
			
			resultObject = serviceLocator.executeService("SHOW_EMP_DETAIL", objectArgs); // For
		}catch (Exception e){
			logger.error(e);
		}
		Set set=objectArgs.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			logger.info("The Key are::>>"+element);
		}
		
		/** for checking HrEisEmp Record For English */
		
		EmpInfoDAO_WF empInfoDAO_WF = new EmpInfoDAOImpl_WF(OrgEmpMst.class, serviceLocator.getSessionFactory());
		OrgEmpMst empMst = empInfoDAO_WF.getEmployee(orgUserMst, 1l);
		
		if(empMst!=null)
		{
			List hrEisEmpMstLst = empInfoDAO_WF.getEmpIdData(empMst.getEmpId());
			if(hrEisEmpMstLst!=null && !hrEisEmpMstLst.isEmpty())
			{
				objectArgs.put("empFlag", true);
			}
		}
		
		objectArgs.put("birthFlag", birthFlag);
		objectArgs.put("currentFlag", currentFlag);
		objectArgs.put("permanentFlag", permanentFlag);
		objectArgs.put("nativeFlag", nativeFlag);
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName(VIEW_NAME);
		return resultObject;
	}
}
