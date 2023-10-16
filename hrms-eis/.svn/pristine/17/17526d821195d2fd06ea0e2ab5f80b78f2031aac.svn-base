package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnCityMstDAO;
import com.tcs.sgv.common.dao.CmnCityMstDAOImpl;
import com.tcs.sgv.common.dao.CmnDistrictMstDAO;
import com.tcs.sgv.common.dao.CmnDistrictMstDAOImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDao;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.CmnStateMstDAO;
import com.tcs.sgv.common.dao.CmnStateMstDAOImpl;
import com.tcs.sgv.common.dao.CmnTalukaMstDAO;
import com.tcs.sgv.common.dao.CmnTalukaMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.AttachmentHelper;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.CmnStateMst;
import com.tcs.sgv.common.valueobject.CmnTalukaMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.HRMSCommonDAO;
import com.tcs.sgv.eis.dao.HRMSCommonDAOImpl;
import com.tcs.sgv.eis.employeeInfo.dao.GeneralEmpInfoDaoImpl;
import com.tcs.sgv.eis.employeeInfo.valueobject.GeneralEmpInfoVO;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModMst;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDao;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDao;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.UserPostDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpDsgnMpg;
import com.tcs.sgv.ess.valueobject.OrgEmpInfo;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.fms.dao.FmsCorrMstDao;
import com.tcs.sgv.fms.dao.FmsCorrMstDaoImpl;
import com.tcs.sgv.fms.dao.FmsFileMstDAO;
import com.tcs.sgv.fms.dao.FmsFileMstDAOImpl;
import com.tcs.sgv.fms.valueobject.CorrespondenceVO;
import com.tcs.sgv.fms.valueobject.FmsCorrMst;
import com.tcs.sgv.fms.valueobject.FmsFileMst;

public class HrmsCommonMessageServImpl {
	 
	public final static String BASELOGINMAP="baseLoginMap";
	public final static String LANG_ID="langId";
	public final static String USER_ID="userId";
	public final static String PRIMARY_POST_ID="primaryPostId";
	public final static String DB_ID = "dbId";
	public final static String LOC_ID = "locationId";
	
	 private static volatile HrmsCommonMessageServImpl INSTANCE;
	 private static final Log logger = LogFactory.getLog(HrmsCommonMessageServImpl.class);
	 
	 /** default constructor */
	   protected HrmsCommonMessageServImpl() {}
	   
	   public static HrmsCommonMessageServImpl getInstance() 
	   {
	      synchronized(HrmsCommonMessageServImpl.class)
	      {
		      if (INSTANCE == null)
		      {
		    	  INSTANCE = new HrmsCommonMessageServImpl();
		      }
	      }
	      return INSTANCE;
	   }


		public static  ResultObject getForwardToDetail(ServiceLocator serv,Map objectArgs,String DocId,long pk)
		{ 
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			try
			{
			objectArgs.put("docId", DocId); 
			objectArgs.put("pkval", pk);       
			
			Map loginMap = (Map) objectArgs.get(BASELOGINMAP); 
			ResultObject resultObj = serv.executeService("GET_CURRENT_JOB_OWNER_POST", objectArgs); 
			Map resultMap=(Map)resultObj.getResultValue(); 

			UserPostDaoImpl userpostdaoimpl= new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());
			
			List orguserpostrltLst = userpostdaoimpl.getActiveUserFromPost(new Long(resultMap.get("CurrentOwnerPostId").toString()).longValue()); 
			StringBuffer EmpName=new StringBuffer(); 
			for (Iterator<OrgUserpostRlt> i = orguserpostrltLst.iterator(); i.hasNext();)
				{ 
					OrgUserpostRlt userpostrlt =i.next(); 
					OrgUserMst  orgusermst= userpostrlt.getOrgUserMst(); 
					Set empMstSet= orgusermst.getOrgEmpMsts(); 
	
					for (Iterator<OrgEmpMst> j = empMstSet.iterator(); j.hasNext(); )
					{ 
						OrgEmpMst orgempmst=j.next(); 
	
						if(orgempmst.getCmnLanguageMst().getLangId()== Long.parseLong(loginMap.get("langId").toString()))
						{
							EmpName.append((orgempmst.getEmpFname() != null && !orgempmst.getEmpFname().trim().equals("") ? orgempmst.getEmpFname()+" " : "") + 
							(orgempmst.getEmpMname() != null && !orgempmst.getEmpMname().trim().equals("") ? orgempmst.getEmpMname()+" " : "")  + 
							(orgempmst.getEmpLname() != null && !orgempmst.getEmpLname().trim().equals("") ? orgempmst.getEmpLname() : "")); 
						} 
					} 
					OrgPostDetailsRlt orgpostdetailrlt=getPostDetail(userpostrlt.getOrgPostMstByPostId().getPostId(),serv,objectArgs); 
					if (orgpostdetailrlt != null)
					{
						EmpName.append("/" +orgpostdetailrlt.getPostName());
					}
				} 

			objectArgs.put("fwdTo",EmpName); 
			
			if(objectArgs.get("fileNo")!=null)
			{
				objectArgs.put("appReqId", objectArgs.get("fileNo"));
			}
			else if(objectArgs.get("corrNo")!=null)
			{
				objectArgs.put("appReqId", objectArgs.get("corrNo"));
			}
			}catch(Exception e)
			{
				logger.error("Error in HrmsCommonMessageServImpl getForwardToDetail::::", e);
			}
			resultObject.setViewName("messageView");
			resultObject.setResultValue(objectArgs);	
			return resultObject; 
			}

			private static OrgPostDetailsRlt getPostDetail(long postid,ServiceLocator serv, Map ObjectArgs) 
			{
				OrgPostDetailsRltDaoImpl orgpostdtlrlt = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
				OrgPostDetailsRlt orgpostdetailrlt = orgpostdtlrlt.getPostDetailsRltByPostIdAndLangId(postid, getLangId(ObjectArgs));
				return orgpostdetailrlt;
			}

			private static long getLangId(Map objectArgs) 
			{

				Map loginMap = (Map) objectArgs.get(BASELOGINMAP);
				long langId = Long.parseLong(loginMap.get("langId").toString());
				return langId;
			}
			
			public static Map createModEmpRltTuple(ServiceLocator serv,Map objectArgs, long userId)
			{
				long modId =0L, appReqId=0L, langId=0L, userIdLogin=0L, postId=0L;
				Map loginMap = (Map) objectArgs.get(BASELOGINMAP);
				HrModEmpRlt hrModEmpRlt = null;
				OrgDesignationMst desid = null;
				GeneralEmpInfoVO EmpDetailsVO = null;
				HrEisScaleMst eisScaleMst=null;
				GeneralEmpInfoDaoImpl GemEmpinfo = null;
				
				try
				{
					GemEmpinfo = new GeneralEmpInfoDaoImpl(OrgEmpMst.class, serv.getSessionFactory());
					modId=Long.parseLong(objectArgs.get("modId").toString());
					appReqId=Long.parseLong(objectArgs.get("appReqId").toString());
					langId = Long.parseLong(loginMap.get(LANG_ID).toString());
					userIdLogin = StringUtility.convertToLong(loginMap.get(USER_ID).toString());
					postId = Long.parseLong(loginMap.get(PRIMARY_POST_ID).toString());
						
					EmpDetailsVO = new GeneralEmpInfoVO();
					EmpDetailsVO = GemEmpinfo.findByEmpIDOtherDetail(userId, langId);
					
					hrModEmpRlt = new HrModEmpRlt();
					hrModEmpRlt.setHrModMst(new HrModMst(modId));
					hrModEmpRlt.setReqId(appReqId);
					hrModEmpRlt.setUserId(userId);
					hrModEmpRlt.setBasicSal(EmpDetailsVO.getSalary());
	
					desid = new OrgDesignationMst();
					long desigid = EmpDetailsVO.getDesigid();
					desid.setDsgnId(desigid);
					hrModEmpRlt.setDsgnId(desid);
					hrModEmpRlt.setPostId(EmpDetailsVO.getPostId());
					
					eisScaleMst=new HrEisScaleMst();
					
					if(EmpDetailsVO.getScaleId()!=null)
					{
						long scaleId=EmpDetailsVO.getScaleId();
						eisScaleMst.setScaleId(scaleId);
						hrModEmpRlt.setScaleId(eisScaleMst);
					}
					
					hrModEmpRlt.setCreatedBy(userIdLogin);
					hrModEmpRlt.setCreatedByPost(postId);
					hrModEmpRlt.setCreatedDate(new java.sql.Timestamp((new java.util.Date()).getTime()));
	
					GenericDaoHibernateImpl<HrModEmpRlt, Long> daoModEmpRlt = new GenericDaoHibernateImpl<HrModEmpRlt, Long>(HrModEmpRlt.class);
					daoModEmpRlt.setSessionFactory(serv.getSessionFactory());
					daoModEmpRlt.create(hrModEmpRlt);
				}
				catch(Exception e)
				{
					logger.error("Error in HrmsCommonMessageServImpl createModEmpRltTuple::::", e);
				}
				
				return objectArgs;
			}
		
	public static long createCorr(Map objectArgs, ServiceLocator serv, String docId, String... strPara)
	{
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		long langId = 0L, userId=0L, postId=0L, locId=0L, corrId = 0, branchid =0L; 
		Map loginMap = (Map) objectArgs.get(BASELOGINMAP);
		CorrespondenceVO correspondenceVO = null;
		OrgPostDetailsRltDaoImpl orgPostMstDaoImpl = null;
		OrgPostDetailsRlt orgpostMst = null;
		OrgPostMstDaoImpl orgPostMstDaoParentImpl = null;
		Date date = null;
		String sysDate = "";
		CmnLookupMstDAO cmnLookupMstDAO = null;
		CmnLookupMst lookupForCorrCategory = null;
		CmnLookupMst lookupForCorrType = null;
		OrgPostMst orgPostMst = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Map map =null;
		
		try
		{
			langId = Long.parseLong(loginMap.get(LANG_ID).toString());
			userId = Long.parseLong(loginMap.get(USER_ID).toString());
			postId = Long.parseLong(loginMap.get(PRIMARY_POST_ID).toString());
			locId = Long.parseLong(loginMap.get(LOC_ID).toString());
			
			correspondenceVO = new CorrespondenceVO();
			correspondenceVO.setLocId(String.valueOf(locId));
			correspondenceVO.setCrtUser(String.valueOf(userId));
			correspondenceVO.setCrtPost(String.valueOf(postId));
			correspondenceVO.setDocId(Long.parseLong(docId));
			
			orgPostMstDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
			orgpostMst = new OrgPostDetailsRlt();
			orgpostMst = orgPostMstDaoImpl.getPostDetailsRltByPostIdAndLangId(postId, langId);
			
			if(orgpostMst.getCmnBranchMst()!=null)
			{
				branchid= orgpostMst.getCmnBranchMst().getBranchId();
				correspondenceVO.setBranchId(branchid);
			}
			
			date=new Date();
			sysDate = simpleDateFormat.format(date);
			correspondenceVO.setCorrDated(sysDate);
	
			cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			lookupForCorrCategory = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("wf_Tappal", langId);
			correspondenceVO.setCorrCategory(lookupForCorrCategory.getLookupId());
			correspondenceVO.setCorrDesc(strPara[0]);
	
			lookupForCorrType = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("I", 1L);
			correspondenceVO.setCorrTypeId(lookupForCorrType.getLookupName()); 
			correspondenceVO.setCorrTypeRefId(locId); // location of user who
			orgPostMstDaoParentImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			orgPostMst = new OrgPostMst();
			orgPostMst = orgPostMstDaoParentImpl.read(postId);
			
			if(strPara.length > 1)
			{
				correspondenceVO.setPostIdToForward(strPara[1]);
			}
			else
			{
				if (orgPostMst.getParentPostId() != -1) 
				{
					correspondenceVO.setPostIdToForward(String.valueOf(orgPostMst.getParentPostId()));
					correspondenceVO.setKeepinCreatorInbox(false);
				}
				else
				{
					correspondenceVO.setKeepinCreatorInbox(true);
				}
				
			}
			
			objectArgs.put("CorrVO", correspondenceVO);
			
			resultObject = serv.executeService("CREATE_CORRESPONDENCE",	objectArgs);
			map =new HashMap();
			map = (HashMap) resultObject.getResultValue();
			corrId = (Long) map.get("corrId");
			objectArgs.put("corrId", corrId);
		}
		catch(Exception e)
		{
			logger.error("Error in HrmsCommonMessageServImpl createCorr::::", e);
			resultObject.setResultCode(ErrorConstants.ERROR);
		}
		return corrId;
	}
			
	public static void removeAttachmentFromSession(HttpSession session, String strAttachName, int rowNumber)
	{
		String key = (String) session.getAttribute("name")+strAttachName + session.getId() + rowNumber;
		AttachmentHelper.fileItemArrayListMap.remove(key);
	}
	public static Map getCommonDataForDraft(Map objectArgs,long langId)
	{
		Map returnMap=null;
		try
		{	
			String fileRefNo="";
			String approverAddress="";
			String apprvrSignedAdr="";
			String apprvrSignedName="";
			String apprvrPostName="";
			Date apprvdDate=null;
			String corrRefNo="";
			String strDistrictName="";
			
			
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			long fileId = objectArgs.get("fileId") != null ? Long.parseLong(objectArgs.get("fileId").toString()) :  0L;
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			//CmnLocationMst cmnLocationMst = (CmnLocationMst)loginDetailsMap.get("locationVO");
			//Added to get Loction in specific lang
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDao cmnLocationMstDao=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMstObj=cmnLocationMstDao.read(locId);
			CmnLocationMst cmnLocationMst = null;
			if(cmnLocationMstObj!=null)
			{
				cmnLocationMst=cmnLocationMstDao.getLocationVOByLocationCodeAndLangId(cmnLocationMstObj.getLocationCode(), langId);
			}
			
			//End 
			
			long empId = loginDetailsMap.get("empId")!=null ? Long.parseLong(loginDetailsMap.get("empId").toString()) : 0l;
			//long langId = Long.parseLong(loginDetailsMap.get("langId").toString()); 
			
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			
			FmsFileMstDAO fmsFileMstDAO = new FmsFileMstDAOImpl(FmsFileMst.class,serv.getSessionFactory());
			FmsFileMst fmsFileMst = fmsFileMstDAO.read(fileId);
			if(fmsFileMst!=null)
			{
				logger.info("File No=="+fmsFileMst.getFileNo());
				fileRefNo = fmsFileMst.getFileNo();
			}
			/** Added By Krunal For Corrospondonece */
			long corrId = objectArgs.get("corrId") != null ? Long.parseLong(objectArgs.get("corrId").toString()) :  0L;
			
			FmsCorrMstDao fmsCorrMstDao = new FmsCorrMstDaoImpl(FmsCorrMst.class,serv.getSessionFactory());
			FmsCorrMst fmsCorrMst = fmsCorrMstDao.read(corrId);
			if(fmsCorrMst!=null)
			{
				logger.info("============Corrs No===="+fmsCorrMst.getCorrNo());
				corrRefNo = fmsCorrMst.getCorrNo();
			}
			
			/** End ------->*/
			if(cmnLocationMst != null)
			{
				Map addressMap = getAddressUsingLocation(serv,cmnLocationMst);
				approverAddress = addressMap.get("withLineAdr").toString();
				apprvrSignedAdr = addressMap.get("withoutLineAdr").toString();
				strDistrictName = addressMap.get("strDistrictName").toString();
			}
			OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
			EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			OrgEmpMst orgEmpMst = empDAO.read(empId);
			if(orgEmpMst != null)
			{
				if(orgEmpMst.getEmpMname()!=null && !orgEmpMst.getEmpMname().equals(""))
				{
					apprvrSignedName = "("+orgEmpMst.getEmpFname()+" "+orgEmpMst.getEmpMname()+" "+orgEmpMst.getEmpLname()+")";
				}
				else
				{
					apprvrSignedName = "("+orgEmpMst.getEmpFname()+" "+orgEmpMst.getEmpLname()+")";
				}
				logger.info("signedName==="+apprvrSignedName);
			}
			OrgPostDetailsRlt orgPostDetailsRlt = orgPostDetailsRltDao.getPostDetailsRltByPostIdAndLangId(postId, langId);
			if(orgPostDetailsRlt!=null)
			{
				logger.info("orgPostDetailsRlt.getPostName()=="+orgPostDetailsRlt.getPostName());
				apprvrPostName = orgPostDetailsRlt.getPostName();
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			apprvdDate=new java.util.Date();
			
			returnMap = new HashMap();
			returnMap.put("CORR_REF_NO", corrRefNo);
			
			returnMap.put("DISTRICT_NAME",strDistrictName);
			returnMap.put("LOCATION_NAME", cmnLocationMst.getLocName());
			returnMap.put("FILE_REF_NO", fileRefNo);
			returnMap.put("APPROVER_ADDRESS",approverAddress);
			returnMap.put("APPRVR_SIGNED_ADR",apprvrSignedAdr);
			returnMap.put("APPRVR_SIGNED_NAME",apprvrSignedName);
			returnMap.put("APPRVR_POST_NAME",apprvrPostName);
			returnMap.put("APPRVD_DATE",sdf.format(apprvdDate));
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return returnMap;
	}
	public static Map getBasicDataUsingUserIdAndDate(Map objectArgs, long userId, Date appliedDate,long langId)
	{
		Map returnMap = new HashMap();
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			//long langId = Long.parseLong(loginDetailsMap.get("langId").toString()); 
			/*long langId =(Long)objectArgs.get("APPLICATION_LANG");
			logger.info("==langId==="+langId);*/
			long empId=0l;
			
			CmnLocationMst cmnLocationMst=null;
			String empName="";
			String postName="";
			String dsgnName="";
			String strAddress="";
			String empPrefix="";
			String withoutLineAdr="";
			//String cityName="";
			String locName="";
			String strDistrictName="";
			
			OrgDepartmentMstDao departmentMstDao = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());
			EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			OrgEmpInfo orgEmpInfo = empDAO.getEmpMstRecordByUserIdandLangIdandCrtdDate(userId, langId, appliedDate);
			if(orgEmpInfo!=null)
			{
				if(orgEmpInfo.getEmpMname()!=null && !orgEmpInfo.getEmpMname().equals(""))
				{
					empName = orgEmpInfo.getEmpFname()+" "+orgEmpInfo.getEmpMname()+" "+orgEmpInfo.getEmpLname();
				}
				else
				{
					empName = orgEmpInfo.getEmpFname()+" "+orgEmpInfo.getEmpLname();
				}
				
				empId = orgEmpInfo.getEmpId();
				empPrefix = orgEmpInfo.getEmpPrefix();
			}
			OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
			HRMSCommonDAO commonDAO = new HRMSCommonDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
			CmnLocationMstDao cmnLocationMstDao = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			OrgUserpostRlt orgUserpostRlt = commonDAO.getOrgUserPostRltByUserIdAndDate(userId, appliedDate);
			if(orgUserpostRlt!=null && orgUserpostRlt.getOrgPostMstByPostId()!= null)
			{
				OrgPostDetailsRlt orgPostDetailsRlt = orgPostDetailsRltDao.getPostDetailsRltByPostIdAndLangId(orgUserpostRlt.getOrgPostMstByPostId().getPostId(), langId);
				if(orgPostDetailsRlt!=null)
				{
					postName = orgPostDetailsRlt.getPostName();
					if(orgPostDetailsRlt.getCmnLocationMst()!=null)
					{
						cmnLocationMst = cmnLocationMstDao.getLocationVOByLocationCodeAndLangId(orgPostDetailsRlt.getCmnLocationMst().getLocationCode(), langId);
					}
				}
			}
			
			OrgEmpDsgnMpg orgEmpDsgnMpg = commonDAO.getEmpDesignationByUserIdAndDate(empId, appliedDate);
			if(orgEmpDsgnMpg!=null && orgEmpDsgnMpg.getOrgDesignationMst()!=null)
			{
				dsgnName = orgEmpDsgnMpg.getOrgDesignationMst().getDsgnName();
			}
			
			if(cmnLocationMst!=null)
			{
				Map addressMap = getAddressUsingLocation(serv,cmnLocationMst);
				strAddress = addressMap.get("withLineAdr").toString();
				withoutLineAdr = addressMap.get("withoutLineAdr").toString();
				//cityName = addressMap.get("locName").toString();
				strDistrictName=addressMap.get("strDistrictName").toString();
				if(!strAddress.equals(""))
				{
					strAddress = strAddress.replace("<br>", ", ");
				}
				OrgDepartmentMst orgDepartmentMst = departmentMstDao.read(cmnLocationMst.getDepartmentId());
				if(orgDepartmentMst!=null)
				{
					orgDepartmentMst = departmentMstDao.getDepartmentVOByDepartmentCodeAndLangId(orgDepartmentMst.getDepCode(), langId);
					withoutLineAdr = orgDepartmentMst.getDepName()+", "+withoutLineAdr;
				}
				
				locName= cmnLocationMst.getLocName();
				
				logger.info("=====LocName======"+locName);
			}
			returnMap.put("LOCATION_NAME", locName);
			returnMap.put("DISTRICT_NAME",strDistrictName);
			returnMap.put("THROUGH_ADDRESS", withoutLineAdr);
			returnMap.put("OFFICE_ADDRESS", strAddress);
			returnMap.put("EMP_NAME", empName);
			returnMap.put("POST_NAME", postName);
			returnMap.put("DSGN_NAME", dsgnName);
			returnMap.put("EMP_PREFIX", empPrefix);
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return returnMap;
	}
	private static Map getAddressUsingLocation(ServiceLocator serv,CmnLocationMst cmnLocationMst)
	{
		Map returnMap = new HashMap();
		String strOfficeAddress = "";
		String strOfcAdrWithoutLine="";
		String strDistrict="";
		String strDistrictName =""; 
		
		if(cmnLocationMst!=null)
		{
			String strLocAddr1 = cmnLocationMst.getLocAddr1();
			String strLocAddr2 = cmnLocationMst.getLocAddr2();
			Long lngCityId = cmnLocationMst.getLocCityId();
			Long lngTalukaId =cmnLocationMst.getLocTalukaId();
			Long lngDistrictId =cmnLocationMst.getLocDistrictId();
			Long lngStateId = cmnLocationMst.getLocStateId();
			//String strPinNo = cmnLocationMst.getLocPin();
			String strCityName= "";
			String strTalukaName="";
			String strStateName="";
			
			if(lngCityId!=null)
			{
				CmnCityMstDAO cityMstDAO = new CmnCityMstDAOImpl(CmnCityMst.class,serv.getSessionFactory());
			    CmnCityMst cityMst = cityMstDAO.read(lngCityId);
			    if(cityMst!=null)
			    {
			    	strCityName= cityMst.getCityName();
			    }
			}
			if(lngDistrictId!=null)
			{
				CmnDistrictMstDAO cmnDistrictMstDAO = new CmnDistrictMstDAOImpl(CmnDistrictMst.class,serv.getSessionFactory());
				CmnDistrictMst cmnDistrictMst = cmnDistrictMstDAO.read(lngDistrictId);
				if(cmnDistrictMst!=null)
				{
					strDistrictName = cmnDistrictMst.getDistrictName();
				}
			
			}
			if(lngTalukaId!=null)
			{   
				CmnTalukaMstDAO cmnTalukaMstDAO = new CmnTalukaMstDAOImpl(CmnTalukaMst.class,serv.getSessionFactory());
				CmnTalukaMst cmnTalukaMst = cmnTalukaMstDAO.read(lngTalukaId);
			  	if(cmnTalukaMst!=null)
			  	{
			  		strTalukaName = cmnTalukaMst.getTalukaName();
			  	}
			}
			if(lngStateId!=null)
			{
				CmnStateMstDAO cmnStateMstDAO = new CmnStateMstDAOImpl(CmnStateMst.class,serv.getSessionFactory());
				CmnStateMst cmnStateMst = cmnStateMstDAO.read(lngStateId);
				if(cmnStateMst != null)
				{
					strStateName = cmnStateMst.getStateName();
				}
			}
			
			if(strLocAddr1!=null)
			{
				strOfficeAddress =strLocAddr1;
			}
			if(strLocAddr2!=null)
			{  
				if(strOfficeAddress=="")
				{
					strOfficeAddress=strLocAddr2;
				}
				else
				{
					strOfficeAddress =strOfficeAddress+"<br>"+strLocAddr2;
				}
			}
			
			if(strTalukaName!="" && strTalukaName!=null)
			{
				if(strOfficeAddress=="")
				{
					strOfficeAddress=strLocAddr2;
				}
				else
				{
					strOfficeAddress =strOfficeAddress+"<br>"+strTalukaName;
				}
			}
			
			if(strCityName!="" && strCityName!=null)
			{
				if(strOfficeAddress=="")
				{
					strOfficeAddress=strCityName;
				}
				else
				{
					strOfficeAddress =strOfficeAddress+"<br>"+strCityName;
				}
				
				if(strOfcAdrWithoutLine=="")
				{
					strOfcAdrWithoutLine=strCityName;
				}
				else
				{
					strOfcAdrWithoutLine = strOfcAdrWithoutLine +", "+strCityName;
				}
			}
			
			if(strDistrictName!="" && strDistrictName!=null)
			{
				if(strOfficeAddress=="")
				{
					strOfficeAddress=strDistrictName;
				}
				else
				{
					strOfficeAddress =strOfficeAddress+"<br>"+strDistrictName;
				}
				
				if(strOfcAdrWithoutLine=="")
				{
					strOfcAdrWithoutLine=strDistrictName;
				}
			}
			
			if(strStateName!="" && strStateName!=null)
			{
				if(strOfficeAddress=="")
				{
					strOfficeAddress=strStateName;
				}
				else
				{
					strOfficeAddress =strOfficeAddress+"<br>"+strStateName;
				}
				
				if(strOfcAdrWithoutLine=="")
				{
					strOfcAdrWithoutLine=strStateName;
				}
				else
				{
					strOfcAdrWithoutLine = strOfcAdrWithoutLine +", "+strStateName;
				}
			}
			if(strDistrictName!=null && strDistrictName!="")
			{
				strDistrict = strDistrictName;
			}
			else if(strCityName!=null && strCityName!="")
			{
				strDistrict = strCityName;
			}	
			else if(strTalukaName!=null && strTalukaName!="")
			{
				strDistrict = strTalukaName;
			}
			else if(strStateName!="" && strStateName!=null)
			{
				strDistrict = strStateName;
			}
		}
		returnMap.put("strDistrictName", strDistrict);
		//returnMap.put("locName", locName);
		logger.info("strOfficeAddress==="+strOfficeAddress);
		logger.info("strOfcAdrWithoutLine==="+strOfcAdrWithoutLine);
		returnMap.put("withLineAdr", strOfficeAddress);
		returnMap.put("withoutLineAdr", strOfcAdrWithoutLine);
		return returnMap;
	}
		
		
}
