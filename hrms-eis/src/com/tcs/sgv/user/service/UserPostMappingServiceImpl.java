package com.tcs.sgv.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.SimpleDateFormat;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDao;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDao;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.RLTPaybandGPState7PC;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDao;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgDesignationMstDao;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDao;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.dao.UserPostDao;
import com.tcs.sgv.ess.dao.UserPostDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.user.dao.UserPostDAO;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;

public class UserPostMappingServiceImpl extends ServiceImpl implements UserPostMappingService
{
	private static final Object BilNo = null;
	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	public ResultObject showUserMappingData(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Coming into the Service method showUserMappingData");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLocationMst cmnLocationMst= (CmnLocationMst) loginDetailsMap.get("locationVO");
			String locationCode = cmnLocationMst.getLocationCode();
			List userpostList = new ArrayList();
			 UserPostDAO userPostDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
			 Map voToService = (Map)objectArgs.get("voToServiceMap");
			 String edit = "";
			 if(voToService.get("edit")!=null)
				{
				  edit = (String)voToService.get("edit").toString();
				  logger.info("edit "+edit);
				}
			 
			 // modified for search by post d search by name by hemant(307727)		
			String lPostname="";
			String Psr="";
			String Bill="";
			String Dsgn="";
			if(voToService != null)
			{
				if(voToService.get("Post")!=null)
					lPostname=(String)voToService.get("Post").toString();
				if(voToService.get("PsrNo")!=null)
					Psr=(String)voToService.get("PsrNo").toString();
				if(voToService.get("BillNo")!=null)
					Bill=(String)voToService.get("BillNo").toString();
				if(voToService.get("Dsgn")!=null)
					Dsgn=(String)voToService.get("Dsgn").toString();
			}
			
			logger.info("Post :" + lPostname + " PsrNo : "  + Psr + "  BillNo : " + Bill +  "  Dsgn :  " + Dsgn);
			
			 List postList = new ArrayList();
			 List postList2 = new ArrayList();
			 long empId = 0;
			 String empName = "";
			 if((voToService.get("Employee_srchNameText_UserPostSearch")!=null&&!voToService.get("Employee_srchNameText_UserPostSearch").equals("")))
				 empName=(String)voToService.get("Employee_srchNameText_UserPostSearch").toString();
			 
			 String empIdStr = (String)voToService.get("Employee_ID_UserPostSearch");
			 if(empIdStr != null && !empIdStr.equals(""))
				 empId = Long.parseLong(empIdStr);
			 
				logger.info("the emp id from search employee "+empId);
				 
				 UserPostCustomVO userpostcustomvo = new UserPostCustomVO();
				 	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
					String empFullName = "";
					String PsrNo = " X ";
					String BillNo = " X ";
					UserPostCustomVO userpostcustomVO = new UserPostCustomVO();
		    		postList = new ArrayList();
		    		String PostName = "";
					String	dsgn = "";
					if(lPostname != null && !lPostname.equals(""))
						PostName = lPostname.trim();		
					if(Dsgn != null && !Dsgn.equals(""))
						dsgn = Dsgn.trim();

					List userPostList=userPostDAO.getAllEmpDataFromUserId(langId,locationCode, PostName,Psr, Bill, dsgn, empId);

					logger.info("The size of userPostList is-------------->"+userPostList.size());
		        	for(int i=0;i<userPostList.size();i++)
		    		 {
		        		userpostcustomVO=new UserPostCustomVO();
			            Object[] rowList = (Object[]) userPostList.get(i);
			            
			            if(rowList[0] != null){
				            empId = Long.parseLong(rowList[0].toString());
				            userpostcustomVO.setEmpId(empId);
				            }
			            if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
				            empFullName = rowList[1].toString();
				            userpostcustomVO.setEmpFullName(empFullName);
				            }
						if(rowList[2] != null){ 	
				            long userPostId =Long.parseLong(rowList[2].toString()) ;
				            userpostcustomVO.setUserPostId(userPostId);
							}
		     
			            if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
				            String postName = rowList[3].toString();
				            userpostcustomVO.setPostname(postName);
				            }
			            
			            if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
				            String dsgnName = rowList[6].toString();
				            userpostcustomVO.setDsgnname(dsgnName);
				            }
			            if(rowList[7] != null){
				            long userId =Long.parseLong(rowList[7].toString()) ;
				       	    userpostcustomVO.setUserId(userId);
				            }
			            if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){
				            PsrNo =rowList[8].toString() ;
				       	    userpostcustomVO.setPsrNo(PsrNo);
				            }
			            if(rowList[9] != null && !(rowList[8].toString().trim()).equals("")){
				            BillNo =rowList[9].toString() ;
				       	    userpostcustomVO.setBillNo(BillNo);
				            }
			            if(rowList[10] != null && !(rowList[10].toString().trim()).equals("")){	
				            String StartDate = rowList[10].toString();
				            userpostcustomVO.setStartDate(sdf.format(sdfParse.parse(StartDate)));
				            }
			            if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
				            String EndDate = rowList[11].toString();
				            userpostcustomVO.setEndDate(sdf.format(sdfParse.parse(EndDate)));
				            }
			            
			       	    postList.add(userpostcustomVO);
		    		 }
		    objectArgs.put("userPostList", postList);
		    objectArgs.put("empName", empName);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("adminShowUserPostMapping");
			logger.info("showUserMappingData");
		}
		catch(Exception e){

			logger.info("Exception Ocuures...showUserMappingData In UserPostMappingServiceImpl");
			e.printStackTrace();
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setResultValue(objectArgs);
		}
		return resultObject;
	}
	
	public ResultObject getUserPostMappingData(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		try 
		{
			logger.info(" Inside getUserPostMappingData ");
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);
				
				long lUserPostRltId = Long.parseLong(objectArgs.get("lUserPostRltId").toString());
				logger.info("User Post ID is:--------->"+lUserPostRltId);
				objRes = getDropdownDetails(objectArgs, serv, cmnLanguageMst);
				if (lUserPostRltId != 0)
				{
					UserPostDao userPostDao = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());
					OrgUserpostRlt orgUserpostRlt = userPostDao.read(lUserPostRltId);
					OrgPostDetailsRltDao orgPostDetailsRltDaoImpl =new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
					CmnLocationMstDao cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
					OrgDepartmentMstDao objOrgDepartmentMstDaoImpl =new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());
					UserPostDAO userDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
					long userId = orgUserpostRlt.getOrgUserMst().getUserId() ;	
					logger.info("user id is ----------------->>>>>"+userId);
					List empmst = userDAO.getAllEmpDatabyUserId(langId, userId);
					logger.info("List Size is "+empmst.size());
					
					UserPostCustomVO userpostcustomVO = new UserPostCustomVO();

		    		List empList = new ArrayList();
		    		
		    		
		        	for(int i=0;i<empmst.size();i++)
		    		 {	    
		        		userpostcustomVO=new UserPostCustomVO();
			            Object[] rowList = (Object[]) empmst.get(i);
			            long empId = Long.parseLong(rowList[0].toString());
			        logger.info("+++++ Employee Id+++++"+empId);
			            userpostcustomVO.setEmpId(empId);
			            
			            String empFname = rowList[1].toString();
			          logger.info("+++++ Emp F Name+++++"+empFname);
			            userpostcustomVO.setEmpFname(empFname);
			            objectArgs.put("empFname",empFname);
			            String empLname = rowList[3].toString();
			          logger.info("+++++ Emp L Name+++++"+empLname);
			            userpostcustomVO.setEmpLname(empLname);
			            objectArgs.put("empLname",empLname);
			         
				           
			            
			            empList.add(userpostcustomVO);
			            
		    		 }
					
					logger.info("Activate Flag is-------->>>"+orgUserpostRlt.getActivateFlag());
					OrgPostDetailsRlt objToPostDetailsRlt= orgPostDetailsRltDaoImpl.getPostDetailsRltByPostIdAndLangId(orgUserpostRlt.getOrgPostMstByPostId().getPostId(),langId);
					
				   if(objToPostDetailsRlt!=null && objToPostDetailsRlt.getCmnLocationMst().getLocationCode()!=null )
				   {
					   CmnLocationMst objToCmnLocationMst=cmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId(objToPostDetailsRlt.getCmnLocationMst().getLocationCode(),langId);
					   
					   if(objToCmnLocationMst!=null && objToCmnLocationMst.getDepartmentId()!=0)
					   {   
						   String locCode= objToCmnLocationMst.getLocationCode();
						   
						   logger.info("==============locCode==========="+ locCode);
						   
						   objectArgs.put("locCode", locCode);
						   
						   OrgDepartmentMst ToOrgDepartmentId = objOrgDepartmentMstDaoImpl.read(objToCmnLocationMst.getDepartmentId());
						   
						   if(ToOrgDepartmentId!=null && ToOrgDepartmentId.getDepartmentId()!=0)
						   {
							  objectArgs.put("depCode", ToOrgDepartmentId.getDepCode());
						   } 
					   } 
					   
					   objectArgs.put("dsgnCode", objToPostDetailsRlt.getOrgDesignationMst().getDsgnCode());
				   }
				   
				   
				   objectArgs.put("userID", orgUserpostRlt.getOrgUserMst().getUserId());
				   objectArgs.put("startDate", orgUserpostRlt.getStartDate());
				   objectArgs.put("empList",empList);
				   objectArgs.put("endDate", orgUserpostRlt.getEndDate());
				   objectArgs.put("postId", orgUserpostRlt.getOrgPostMstByPostId().getPostId());
				   objectArgs.put("activateFlagId", orgUserpostRlt.getActivateFlag());
				   
				}
				
				objectArgs.put("lUserPostRltId", lUserPostRltId);
				
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setViewName("adminUserPostMapping");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.info("Exception Ocuures...getUserPostMappingData In UserPostMappingServiceImpl");
		}
		return objRes;
	}
	
	private ResultObject getDropdownDetails(Map objServiceArgs, ServiceLocator serv, CmnLanguageMst cmnLanguageMst)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
		
			Map loginDetails=(Map)objServiceArgs.get("baseLoginMap");
    		long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
    		 UserPostDAO userDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
    		//---------take data from dept master all office type-----------------//
			List<OrgDepartmentMst> arOfficeTypeInfo= new ArrayList<OrgDepartmentMst>();
			OrgDepartmentMstDao objDepartmentMstDao = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());
			arOfficeTypeInfo=objDepartmentMstDao.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);
			
			//---------take designatin data-----------------	
			/*List<OrgDesignationMst> arDesignationVO=new ArrayList<OrgDesignationMst>();
			OrgDesignationMstDao orgDesignationMstDao = new OrgDesignationMstDaoImpl(OrgDesignationMst.class, serv.getSessionFactory());
			arDesignationVO = orgDesignationMstDao.getAllDesignation(objServiceArgs, cmnLanguageMst.getLangId());*/
			List arDesignationVO = userDAO.getAllDesgMasterData(langId);            
			//---------take designatin data-----------------
			
			//List userEntryList = userDAO.getAllUserDataFromuserId(langId);
			List userEntryList = new ArrayList();
			userEntryList = userDAO.getAllUserDataNotInUserPost(langId);
        	//List userList = new ArrayList();
        	List userList = new ArrayList();
        	
        	logger.info("The size of userEntryList is-------------->"+userEntryList.size());
        	/*for(int i=0;i<userEntryList.size();i++)
    		 {	    
        		userpostcustomVO=new UserPostCustomVO();
	            Object[] rowList = (Object[]) userEntryList.get(i);
	         
	            
	            long empId =Long.parseLong(rowList[0].toString()) ;
	   		    userpostcustomVO.setEmpId(empId);
	            
	            String empFname = rowList[1].toString();
	           userpostcustomVO.setEmpFname(empFname);
	            
	            String empLname = rowList[3].toString();
	            userpostcustomVO.setEmpLname(empLname);
	            
	            long userId =Long.parseLong(rowList[4].toString()) ;
	       	    userpostcustomVO.setUserId(userId);
	       	    
	       	 String userName = rowList[5].toString();
	            userpostcustomVO.setUserName(userName);

	       	    userList.add(userpostcustomVO);
	            
	            
    		 }*/
        	 
        	UserPostCustomVO userpostCustomVO = new UserPostCustomVO();
	
    		
        	for(int i=0;i<userEntryList.size();i++)
    		 {	    
        		userpostCustomVO=new UserPostCustomVO();
	            Object[] rowList = (Object[]) userEntryList.get(i);
	            long empId =Long.parseLong(rowList[0].toString()) ;
	            userpostCustomVO.setEmpId(empId);
	            String empFname = rowList[1].toString();
	            userpostCustomVO.setEmpFname(empFname);
	            String empLname = rowList[2].toString();
	            userpostCustomVO.setEmpLname(empLname);
	            long userId =Long.parseLong(rowList[3].toString()) ;
	            userpostCustomVO.setUserId(userId);
		       	String userName = rowList[4].toString();
		       	userpostCustomVO.setUserName(userName);
		       	userList.add(userpostCustomVO);
	         }
			
        	//userList.addAll(userList2);			
			logger.info("UserList Size is :----------->>>>"+userList.size());
			objServiceArgs.put("arOrgUserMst", userList);
			objServiceArgs.put("arOfficeTypeInfo", arOfficeTypeInfo);
			objServiceArgs.put("arDesignationVO", arDesignationVO);
			
			resObj.setResultValue(objServiceArgs);
			resObj.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setResultValue(objServiceArgs);
			logger.info("Exception Ocuures...getDropdownDetails");
		}
		return resObj;
	}
	
	public ResultObject submitUserPostMappingData(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{  
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			
			long userID = Long.parseLong(loginDetailsMap.get("userId").toString());			
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); 
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userID);
			
			long empId = Long.parseLong(loginDetailsMap.get("empId").toString());
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			orgEmpMst.setEmpId(1l);
			
			
			
			/*  Get The Person Post */			 			 	    	 
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
			
			/*End of the geting Person Post Code*/	  
			
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);
            CmnLookupMstDAOImpl cmnLookupMstDao  = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
            CmnLookupMst cmnLookupMst = cmnLookupMstDao.getLookUpVOByLookUpNameAndLang("Primary_Post",langId);
            logger.info("cmnlookupmst size is********"+cmnLookupMst);
             
            
			if (objRes != null && objectArgs != null) 
			{
				String strDesgCode=objectArgs.get("selDesg").toString();
				String strDepartmentCode=objectArgs.get("strOfficeType").toString();
				String strOfficeName=objectArgs.get("strOfficeName").toString();
				long lUserPostRltId=Long.parseLong(objectArgs.get("lUserPostRltId").toString());
				
				
				
				logger.info("strDesgCode========="+strDesgCode);
				logger.info("strDepartmentCode========="+strDepartmentCode);
				logger.info("strOfficeName========="+strOfficeName);
				logger.info("lUserPostRltId========="+lUserPostRltId);
				
				
				OrgUserpostRlt orgUserpostRltVoGen= (OrgUserpostRlt)objectArgs.get("orgUserpostRlt");
				//logger.info("Activate Flag Id is --------------->>>>"+orgUserpostRltVoGen.getActivateFlag());
				UserPostDao userPostDao  = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				
				OrgUserpostRlt orgUserpostRlt=new OrgUserpostRlt();
				
				
				if(lUserPostRltId==0)
				{
					logger.info("Inside Insertion Block================= ");
					long orgUserpostRltPk = 0;
					IdGenerator idGen = new IdGenerator();
					orgUserpostRltPk = idGen.PKGenerator("org_userpost_rlt", objectArgs);
					
					//orgUserpostRltPk = IDGenerateDelegate.getNextId("org_userpost_rlt", objectArgs);
					logger.info("Inserting known orgUserpostRltPk PK================= " + orgUserpostRltPk);
					 if(orgUserpostRltPk!=0)
					 {	 
						 orgUserpostRlt.setEmpPostId(orgUserpostRltPk);
						 orgUserpostRlt.setOrgPostMstByPostId(orgUserpostRltVoGen.getOrgPostMstByPostId());
						 orgUserpostRlt.setOrgUserMst(orgUserpostRltVoGen.getOrgUserMst());
						 orgUserpostRlt.setStartDate(orgUserpostRltVoGen.getStartDate());
						 orgUserpostRlt.setEndDate(orgUserpostRltVoGen.getEndDate());
						 orgUserpostRlt.setActivateFlag(1);
						 orgUserpostRlt.setCreatedDate(new java.util.Date());
						// orgUserpostRlt.setOrgEmpMstByCreatedBy(orgUserMst); // Needs to be change
						 orgUserpostRlt.setOrgUserMstByCreatedBy(orgUserMst);
						 orgUserpostRlt.setOrgPostMstByCreatedByPost(orgPostMst);
						 orgUserpostRlt.setCmnLookupUserPostType(cmnLookupMst);
						 msg=1;
						 userPostDao.create(orgUserpostRlt);
						 
						 
							//added by manish khunt
						 
							/* long userIdNew=Long.valueOf(objectArgs.get("userId").toString());
							 logger.info("userId  is:::::::::::::"+userIdNew);
							 OrgUserMstDaoImpl orgUserMstDaoImplNew = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); 
							 OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdNew);
							Set set=orgUserMstNew.getOrgUserpostRlts();
							long postIdNew=0;
						   for(Iterator itr=set.iterator();itr.hasNext();)
						   {
							   Object[] row = (Object[])itr.next();
							    postIdNew=Long.valueOf(row[0].toString());
						   }
						   logger.info("post Id is  manish manish ::::::::"+postIdNew);*/
						    long postIdNew=Long.valueOf(objectArgs.get("postId").toString());
						    logger.info("postId  is:::::::::::::"+postIdNew);
							OrgPostMstDaoImpl orgPostMstDaoImplNew = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
							OrgPostMst orgPostMstNew = orgPostMstDaoImpl.read(postIdNew);	    
							//Set set=orgPostMstNew.getOrgPostDetailsRlt();
							//logger.info("Set is ::::::::::"+set);
							long userIdNew=Long.valueOf(objectArgs.get("userId").toString());
						//	int c=0;
							  
						//	OrgPostDetailsRlt rlt = (OrgPostDetailsRlt)set.iterator().next();
							
							
							   logger.info("userIdNew is  manish manish ::::::::"+userIdNew);
							   
							   OrgUserMstDaoImpl orgUserMstDaoImplNew = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); 
								 OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdNew);
						     	Map mapForChangedRecords=objectArgs;
								mapForChangedRecords.put("changedPostId",postIdNew);
								long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());	
								mapForChangedRecords.put("locId", locId);
								mapForChangedRecords.put("serviceLocator",serv);
								CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
								CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(StringUtility.convertToLong(loginDetailsMap.get("dbId").toString()));
								mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
								mapForChangedRecords.put("OrgPostMst",orgPostMstNew);
			        			mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
								GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
								long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
								logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
							//ended by manish khunt
					
					 }
				}else
				{
					logger.info("Activate Flag is :--->>>"+orgUserpostRltVoGen.getActivateFlag());
					long aFlag = orgUserpostRltVoGen.getActivateFlag();
					orgUserpostRlt = userPostDao.read(lUserPostRltId);
					orgUserpostRlt.setUpdatedDate(new java.util.Date());
					//orgUserpostRlt.setOrgEmpMstByUpdatedBy(orgUserMst); // Needs to be change
					//orgUserpostRlt.setOrgEmpMstByUpdatedBy(orgEmpMst);
					orgUserpostRlt.setOrgUserMstByCreatedBy(orgUserMst);
					orgUserpostRlt.setOrgPostMstByUpdatedByPost(orgPostMst);
					orgUserpostRlt.setOrgPostMstByPostId(orgUserpostRltVoGen.getOrgPostMstByPostId());
					 orgUserpostRlt.setOrgUserMst(orgUserpostRltVoGen.getOrgUserMst());
					 orgUserpostRlt.setStartDate(orgUserpostRltVoGen.getStartDate());
					 orgUserpostRlt.setEndDate(orgUserpostRltVoGen.getEndDate());
					 orgUserpostRlt.setCmnLookupUserPostType(cmnLookupMst);
					 orgUserpostRlt.setActivateFlag(aFlag);
					 userPostDao.update(orgUserpostRlt);
				
					 logger.info("Inside Updation Block================= ");
					 
						//added by manish khunt
					 
						/* long userIdNew=Long.valueOf(objectArgs.get("userId").toString());
						 logger.info("userId  is:::::::::::::"+userIdNew);
						 OrgUserMstDaoImpl orgUserMstDaoImplNew = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); 
						 OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdNew);
						Set set=orgUserMstNew.getOrgUserpostRlts();
						long postIdNew=0;
					   for(Iterator itr=set.iterator();itr.hasNext();)
					   {
						   Object[] row = (Object[])itr.next();
						    postIdNew=Long.valueOf(row[0].toString());
					   }
					   logger.info("post Id is  manish manish ::::::::"+postIdNew);*/
					    long postIdNew=Long.valueOf(objectArgs.get("postId").toString());
					    logger.info("postId  is:::::::::::::"+postIdNew);
						OrgPostMstDaoImpl orgPostMstDaoImplNew = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
						OrgPostMst orgPostMstNew = orgPostMstDaoImpl.read(postIdNew);	    
						//Set set=orgPostMstNew.getOrgPostDetailsRlt();
						//logger.info("Set is ::::::::::"+set);
						long userIdNew=Long.valueOf(objectArgs.get("userId").toString());
					//	int c=0;
						  
					//	OrgPostDetailsRlt rlt = (OrgPostDetailsRlt)set.iterator().next();
						
						
						   logger.info("userIdNew is  manish manish ::::::::"+userIdNew);
						   
						   OrgUserMstDaoImpl orgUserMstDaoImplNew = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory()); 
							 OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdNew);
					     	Map mapForChangedRecords=objectArgs;
							mapForChangedRecords.put("changedPostId",postIdNew);
							long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());	
							mapForChangedRecords.put("locId", locId);
							mapForChangedRecords.put("serviceLocator",serv);
							CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
							CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(StringUtility.convertToLong(loginDetailsMap.get("dbId").toString()));
							mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
							mapForChangedRecords.put("OrgPostMst",orgPostMstNew);
		        			mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
							GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
							long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
							logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
							logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
						//ended by manish khunt
					
				}
			}
			
			
			 if(msg==1)
	    			objectArgs.put("MESSAGECODE",300005);//message code from frm_message_mst 300005 for Update
	    		else
	    			objectArgs.put("MESSAGECODE",300006);//message code from frm_message_mst 300006 for Insert
	    					
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			
			objRes = getDropdownDetails(objectArgs, serv, cmnLanguageMst);
			
			
			objRes.setViewName("adminUserPostMapping");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.info("Exception Ocuures...submitUserPostMappingData In UserPostMappingServiceImpl");
		}
		return objRes;
	}
	
	public ResultObject getOfficeName(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				
				CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);
				
				StringBuffer sbXML= new StringBuffer();
				String strOfficeNameCode = objectArgs.get("strOfficeNameCode").toString();
				logger.info("strOfficeNameCode in servide======="+strOfficeNameCode);
								
				OrgDepartmentMstDao objDepartmentMstDao = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,serv.getSessionFactory());
				OrgDepartmentMst orgDepartmentMst=(OrgDepartmentMst) objDepartmentMstDao.getDepartmentVOByDepartmentCodeAndLangId(strOfficeNameCode, langId);
				
				//EmpPresentPostingDtlsDao objEmpPresentPostingDtlsDaoImpl =new EmpPresentPostingDtlsDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());

				String strFlag = objectArgs.get("strFlag").toString();
				String strRecType = objectArgs.get("strRecType").toString();
				logger.info("In Service strRecType============="+strRecType);
				Date dtStartDate = (Date)objectArgs.get("dtStartDate");
				logger.info("In service dtStartDate============"+dtStartDate);
				
				
				logger.info("strFlag in getOfficeName====="+strFlag);
				
				sbXML.append("<root>");
				
				if(strFlag.equals("location"))
			 	{	
			 		String strLocationFieldName = objectArgs.get("strLocationName").toString();
			 		logger.info("strLocationFieldName in service====="+strLocationFieldName);
			 		
			 		long lngOffNmId=orgDepartmentMst!= null ? orgDepartmentMst.getDepartmentId() : 0;
			 		CmnLocationMstDao cmnLocationMstDao = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				
			 		String[] strLocationColumns = {"cmnLanguageMst","departmentId"};
			 		Object[] locationColumsValues ={cmnLanguageMst, lngOffNmId};
				
			 		List<CmnLocationMst> arOfficeNameInfo=cmnLocationMstDao.getListByColumnAndValue(strLocationColumns, locationColumsValues);
				
			 		logger.info("=======arOfficeNameInfo====="+arOfficeNameInfo);
			 		
			 		sbXML.append("<locationFieldName>"+ strLocationFieldName +"</locationFieldName>");
			 		
			 		for (Iterator i = arOfficeNameInfo.iterator(); i.hasNext();)
			 		{
			 			CmnLocationMst objCmnLocationMst = (CmnLocationMst) i.next();
			 			String strLocationName = objCmnLocationMst.getLocName();
			 			sbXML.append("<option value=\""+ objCmnLocationMst.getLocationCode() +"\" >");
			 			sbXML.append(strLocationName.replaceAll("&", "amp;"));
			 			sbXML.append("</option>");
			 		} 
			 	}
				else if(strFlag.equals("post"))
				{
					String strPostFieldName = objectArgs.get("strPostFieldName").toString();
					String strLocationCode = objectArgs.get("strLocationCode").toString();
					String strDesignationCode = objectArgs.get("strDesignationCode").toString();
			 		
					if(strRecType.equals("H"))
					{
							CmnLocationMstDao cmnLocationMstDao = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
							CmnLocationMst objCmnLocationMst = cmnLocationMstDao.getLocationVOByLocationCodeAndLangId(strLocationCode, langId);
					 		
							OrgDesignationMstDao orgDesignationMstDao = new OrgDesignationMstDaoImpl(OrgDesignationMst.class, serv.getSessionFactory());
							OrgDesignationMst orgDesignationMst = orgDesignationMstDao.getDesignationVOByDsgnCodeAndLangId(strDesignationCode, langId);
							
							OrgPostDetailsRltDao objOrgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
							
							String[] strPostColumns = {"cmnLocationMst","cmnLanguageMst","orgDesignationMst"};
					 		Object[] postColumsValues ={objCmnLocationMst, cmnLanguageMst, orgDesignationMst};
						
					 		List<OrgPostDetailsRlt> arPostNameInfo=objOrgPostDetailsRltDao.getListByColumnAndValue(strPostColumns, postColumsValues);
					 		
					 		logger.info("=======arPostNameInfo====="+arPostNameInfo.size());
					 		
					 		sbXML.append("<postFieldName>"+ strPostFieldName +"</postFieldName>");
					 		
					 		for (Iterator i = arPostNameInfo.iterator(); i.hasNext();)
					 		{
					 			OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt) i.next();
					 			String strPostName = orgPostDetailsRlt.getPostName();
					 			sbXML.append("<option value=\""+ orgPostDetailsRlt.getOrgPostMst().getPostId() +"\" >");
					 			sbXML.append(strPostName);
					 			sbXML.append("</option>");
					 		} 
					}
					else
					{
						/*logger.info("Inside For Current (Vacant Post)==================");
						List<OrgPostDetailsRlt> lstVacantPost= objEmpPresentPostingDtlsDaoImpl.getVacantPostList(dtStartDate, strLocationCode, strDesignationCode, langId);
						
						sbXML.append("<postFieldName>"+ strPostFieldName +"</postFieldName>");
				 		
				 		for (Iterator i = lstVacantPost.iterator(); i.hasNext();)
				 		{
				 			OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt) i.next();
				 			String strPostName = orgPostDetailsRlt.getPostName();
				 			sbXML.append("<option value=\""+ orgPostDetailsRlt.getOrgPostMst().getPostId() +"\" >");
				 			sbXML.append(strPostName);
				 			sbXML.append("</option>");
				 		} */
					}
				}
				sbXML.append("</root>");
				String StrOfficeNm = new AjaxXmlBuilder().addItem("key_ajax", sbXML.toString()).toString();
				logger.info("===key_ajax===="+ StrOfficeNm);
		 		objectArgs.put("ajaxKey", StrOfficeNm);
		 		objRes.setResultValue(objectArgs);
		 		objRes.setResultCode(ErrorConstants.SUCCESS);
		 		objRes.setViewName("ajaxData"); 
			}	
				
		}	
				
		catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while Filling ComboDtls through Ajex Request",e);
			logger.info("Exception while adding a Emp Posting Dtls in Service VO");
		}
				
	return objRes;
	}

	
	
	
	
	
	public ResultObject GetPostfromDesg(Map objectArgs) {
		logger.info("Coming in the service");
		logger
				.info("***************in scale combo population service**************");
		// HttpServletRequest request = (HttpServletRequest)
		// objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try {
			
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			long dsgnId = 0, commissionId;
			logger.info("In try \n " + voToService);
			logger.info(voToService.get("dsgnId").toString());
			String lStrDsgnId = voToService.get("dsgnId").toString();
			logger.info(lStrDsgnId + " is the designation");
			dsgnId = Long.parseLong(lStrDsgnId);

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long loc_id = Long.parseLong(loginDetailsMap.get("locationId")
					.toString());

			StringBuffer propertyList = new StringBuffer();
			HrEisScaleMst hrEisScaleMst = null;
			logger.info("loc id and designation id is " + loc_id + "  "
					+ dsgnId);
			List userpostrltSet = new ArrayList();
			UserPostDAO userPostDAO = new UserPostDAO(UserPostCustomVO.class,
					serv.getSessionFactory());
			userpostrltSet = userPostDAO.getAllUserPostRltDatabyDesgNew(loc_id,dsgnId);
			logger.info("before add second list" + userpostrltSet);

			String post = "";
			String empfname = "";
			long postid = 0;
			long PsrNo = 0;
			long BillNo = 0;
			String postLookupId ="";
			Long postlookupId = 0L;
			Object[] row = null;
			ComboValuesVO lObjComboValuesVO = null;
			List<Object> lLstReturnList = null;
			if (userpostrltSet != null && userpostrltSet.size() > 0) {
				logger.info("in if block userpostrltSet.size() "
						+ userpostrltSet.size());
				lLstReturnList = new ArrayList<Object>();
				for (int k = 0; k < userpostrltSet.size(); k++) {
					logger.info("in for loop userpostrltSet.size() "
							+ userpostrltSet.size());
					row = (Object[]) userpostrltSet.get(k);

					// changed by manoj
					logger.info("row " + row);

					logger.info("row[0] " + row[0]);
					if (row[0] != null) 
					{
						// logger.info("row[0].toString() "+row[0].toString());
						postid = Long.parseLong(row[0].toString());
						logger.info("postid " + postid);
						
					}

					// /logger.info("empfname "+empfname);
					// logger.info("testing by manoj for vacant post "+empfname);
					if (row[1] != null || !row[1].toString().equals("")	|| !row[1].toString().equals(" "))
					{
						if(row[0] != null)
						{
							List postLookupIdList = userPostDAO.getPostLookupIdFromPostId(postid);
							CmnLookupMst cmnLookupMst = null;
							for(int i=0;i<postLookupIdList.size();i++)
							{
								Object[] rowlist = (Object[])postLookupIdList.get(i);
								
								if(rowlist[0].toString()!=null)
								{
									postLookupId =rowlist[0].toString();
								}
								/*postLookupId = rowlist[0].toString();
								cmnLookupMst.setLookupId(Long.parseLong(postLookupId));
								 postlookupId = cmnLookupMst.getLookupId();*/
								
								
							}
							
							logger.info("postlookupId is*****"+postLookupId);
							if(postLookupId.equals("10001198129"))
							{
							post = row[1].toString().concat("P");
							}
							else if(postLookupId.equals("10001198130"))
							{
								post = row[1].toString().concat("T");
							}
							else
							{
								post =row[1].toString();
							}
						}
						else
						{
							post = row[1].toString();
						}
						
					}
					logger.info("post " + post);
					// end by manoj

					propertyList.append("<post-mapping>");
					propertyList.append("<postId>").append(postid).append(
							"</postId>");
					propertyList.append("<post>").append("<![CDATA[").append(
							post).append("]]>").append("</post>");
					propertyList.append("</post-mapping>");
					
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(Long.valueOf(postid).toString());
					lObjComboValuesVO.setDesc(post.toString());
					lLstReturnList.add(lObjComboValuesVO);
					
					

				}
				objectArgs.put("CurrentPostList", lLstReturnList);
				logger.info(lLstReturnList.size());
			}

			logger.info(propertyList.toString());
			Map result = new HashMap();
			String postData = new AjaxXmlBuilder().addItem("ajax_key",
					propertyList.toString()).toString();
			logger.info("Post data is " + postData);
			result.put("ajaxKey", postData);

			resultObject.setResultValue(result);
			String lStrCheck = StringUtility.getParameter("ifAjax",
					(HttpServletRequest) objectArgs.get("requestObj"));
		//	if (lStrCheck.equals("TRUE")) {
				resultObject.setViewName("ajaxData");
		//	}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultObject;
	}
	
	
	
	
	
	public ResultObject GetScalefromDesg(Map objectArgs) {

		logger.info("Coming in Scale the service");
		logger.info("***************in scale combo population service**************");
		// HttpServletRequest request = (HttpServletRequest)
		// objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try {
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			long dsgnId = 0, commissionId;

			commissionId = Long.parseLong((String) voToService.get("commissionId"));
			logger.info(" UserPostMapping + GetScalefromDesg +  commission id is " + commissionId);
			logger.info(" UserPostMapping + GetScalefromDesg +  commission id is " + commissionId);
			
			if (commissionId == 700015) {
				commissionId = 2500340;
			}

			else if (commissionId == 700016){
				commissionId = 2500341;
			}
			else if (commissionId == 700337){
				commissionId = 2500342;
			}
			else if (commissionId == 700338){
				commissionId = 2500343;
			}
			else if (commissionId == 700339){
				commissionId = 2500344;
			}
			else if (commissionId == 700340){
				commissionId = 2500345;
			}
			else if (commissionId == 700345){
				commissionId = 2500346;
			}

			GradDesgScaleMapDAO gdDao = new GradDesgScaleMapDAO(
					HrEisSgdMpg.class, serv.getSessionFactory());	
			
			Object[] row = null;
			long sgdId = 0;

			//logger.info("size of scale list is " + scaleList.size());
//			logger.info("size of scale list is11 " + scaleList1.size());

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long loc_id = Long.parseLong(loginDetailsMap.get("locationId")
					.toString());

			StringBuffer propertyList = new StringBuffer();
			HrEisScaleMst hrEisScaleMst = null;
//			Rlt7PayMatrix rlt7PayMatrix = null; //-----Added By Tejashree
			ComboValuesVO lObjComboValuesVO = null;
			List<Object> lLstReturnList = null;
			logger.info("commissionId$$$$$$$$$$$$$$="+commissionId);
			//Added By Tejashree
			if (commissionId == 700005){
				logger.info("Condition ture$$$$$$$$$$$$$$="+commissionId);
				List<RLTPaybandGPState7PC> scaleList = gdDao.getSvnPayscale();
				String sgdIdSeven = "";
				logger.info("size of scale list is " + scaleList.size());
				if (scaleList != null && scaleList.size() > 0) 
				{
					lLstReturnList = new ArrayList<Object>();
					RLTPaybandGPState7PC rltpaybandGPState7Pc = null;
					logger.info("in if block userpostrltSet.size() "+ scaleList.size());
					for (int k = 0; k < scaleList.size(); k++) 
					{
						logger.info("in for loop userpostrltSet.size() "+scaleList.size());
						rltpaybandGPState7Pc = scaleList.get(k);
						sgdIdSeven = rltpaybandGPState7Pc.getId();
						
						sgdIdSeven = rltpaybandGPState7Pc.getLevelId();
						logger.info("sgdId  " + sgdIdSeven);
						StringBuffer scaleDisp = new StringBuffer("");
						scaleDisp.append(rltpaybandGPState7Pc.getId());
						logger.info("Parvez ID is====  " + rltpaybandGPState7Pc.getId());

						lObjComboValuesVO = new ComboValuesVO();
						lObjComboValuesVO.setDesc(scaleDisp.toString());
						
						lObjComboValuesVO.setId(scaleDisp.toString()); 
						lObjComboValuesVO.setDesc(rltpaybandGPState7Pc.getLevel().toString());
						
						lLstReturnList.add(lObjComboValuesVO);

						propertyList.append("<scale-mapping>");
						propertyList.append("<sgdId>").append(rltpaybandGPState7Pc.getId()).append("</sgdId>");
						propertyList.append("<scale>").append("<![CDATA[").append(rltpaybandGPState7Pc.getLevel().toString()).append("]]>").append("</scale>");
						propertyList.append("</scale-mapping>");

					}
					objectArgs.put("PayScaleList", lLstReturnList);
				}		
				
			}
			else{
				logger.info("Condition false$$$$$$$$$$$$$$="+commissionId);
			List<HrEisScaleMst> scaleList = gdDao
					.getScalefromDsgnComm(commissionId);
			logger.info("size of scale list is " + scaleList.size());
			if (scaleList != null && scaleList.size() > 0) {
				lLstReturnList = new ArrayList<Object>();
				logger.info("in if block userpostrltSet.size() "
						+ scaleList.size());

				for (int k = 0; k < scaleList.size(); k++) {
					logger.info("in for loop userpostrltSet.size() "
							+ scaleList.size());
					hrEisScaleMst = scaleList.get(k);

					// changed by manoj
					if (hrEisScaleMst != null) {

						sgdId = hrEisScaleMst.getScaleId();
						logger.info("sgdId  " + sgdId);

						// /logger.info("empfname "+empfname);
						// logger.info("testing by manoj for vacant post "+empfname);

						StringBuffer scaleDisp = new StringBuffer("");

						if (hrEisScaleMst.getHrPayCommissionMst().getId() == 2500341 || hrEisScaleMst.getHrPayCommissionMst().getId() == 2500342 || hrEisScaleMst.getHrPayCommissionMst().getId() == 2500343
								|| hrEisScaleMst.getHrPayCommissionMst().getId() == 2500344 || hrEisScaleMst.getHrPayCommissionMst().getId() == 2500345) {

							scaleDisp.append(hrEisScaleMst.getScaleStartAmt());
							
							scaleDisp.append("-");
							scaleDisp.append(hrEisScaleMst.getScaleEndAmt());
							scaleDisp.append(" (");
							scaleDisp.append(hrEisScaleMst.getScaleGradePay());
							scaleDisp.append(")");

						} else {
							scaleDisp.append(hrEisScaleMst.getScaleStartAmt());
							scaleDisp.append("-");
							scaleDisp.append(hrEisScaleMst.getScaleIncrAmt());
							scaleDisp.append("-");
							scaleDisp.append(hrEisScaleMst.getScaleEndAmt());
						}
							if(hrEisScaleMst.getScaleHigherIncrAmt()>0 && hrEisScaleMst.getScaleHigherEndAmt()>0)
							{
								scaleDisp.append("-");
								scaleDisp.append(hrEisScaleMst.getScaleHigherIncrAmt());
								scaleDisp.append("-");
								scaleDisp.append(hrEisScaleMst.getScaleHigherEndAmt());
								if(hrEisScaleMst.getScaleSecondHigherIncrAmt()>0 && hrEisScaleMst.getScaleSecondHigherEndAmt()>0)
								{
									scaleDisp.append("-");
									scaleDisp.append(hrEisScaleMst.getScaleSecondHigherIncrAmt());
									scaleDisp.append("-");
									scaleDisp.append(hrEisScaleMst.getScaleSecondHigherEndAmt());
									
									if(hrEisScaleMst.getScaleThirdHigherIncrAmt()>0 && hrEisScaleMst.getScaleThirdHigherEndAmt()>0)
									{
										scaleDisp.append("-");
										scaleDisp.append(hrEisScaleMst.getScaleThirdHigherIncrAmt());
										scaleDisp.append("-");
										scaleDisp.append( hrEisScaleMst.getScaleThirdHigherEndAmt());
									}
								}
							}
						
						lObjComboValuesVO = new ComboValuesVO();
						lObjComboValuesVO.setId(Long.valueOf(
								hrEisScaleMst.getScaleId()).toString());
						lObjComboValuesVO.setDesc(scaleDisp.toString());
						lLstReturnList.add(lObjComboValuesVO);

						propertyList.append("<scale-mapping>");
						propertyList.append("<sgdId>").append(sgdId).append(
								"</sgdId>");
						propertyList.append("<scale>").append("<![CDATA[")
								.append(scaleDisp).append("]]>").append(
										"</scale>");
						propertyList.append("</scale-mapping>");
					}
					}
				}
				objectArgs.put("PayScaleList", lLstReturnList);
			}

			Map result = new HashMap();
			String postData = new AjaxXmlBuilder().addItem("ajax_key",
					propertyList.toString()).toString();
			result.put("ajaxKey", postData);

			resultObject.setResultValue(result);
			String lStrCheck = StringUtility.getParameter("ifAjax",
					(HttpServletRequest) objectArgs.get("requestObj"));

			//if (lStrCheck.equals("TRUE")) {
				resultObject.setViewName("ajaxData");
			//}
			logger.info("After Service Called.........\n" + postData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultObject;
	}	
	
	
	
	
	
	public ResultObject GetSvnPcBasicData(Map objectArgs)
	  {
	    this.logger.info("Coming in the service");
	    this.logger.info("***************in scale combo population service**************");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	    ResultObject resultObject = new ResultObject(0);
	    try
	    {
	      Map voToService = (Map)objectArgs.get("voToServiceMap");
	      this.logger.info("In try \n " + voToService);
	      
	      Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
	      
	      StringBuffer propertyList1 = new StringBuffer();
	      String strCell = voToService.get("cell").toString();
	      System.out.println("Cell Parvez IS ++++++++"+strCell);
	      List userpostrltSet1 = new ArrayList();
	      UserPostDAO userPostDAO = new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
	      userpostrltSet1 = userPostDAO.getSvnPcData(strCell);
	      this.logger.info("before add second list" + userpostrltSet1);
	      
	      String id = "";
	      long cell = 0L;
	      String post = "";
	      long postid = 0L;
	      Object[] row = null;
	      ComboValuesVO lObjComboValuesVO = null;
	      List<Object> lLstReturnList1 = null;
	      if ((userpostrltSet1 != null) && (userpostrltSet1.size() > 0))
	      {
	        this.logger.info("in if block userpostrltSet1.size() " + 
	          userpostrltSet1.size());
	        lLstReturnList1 = new ArrayList();
	        for (int k = 0; k < userpostrltSet1.size(); k++)
	        {
	          this.logger.info("in for loop userpostrltSet1.size() " + 
	            userpostrltSet1.size());
	          row = (Object[])userpostrltSet1.get(k);
	          StringBuffer cell1 = new StringBuffer();
	          if (row[0] != null)
	          {
	            cell1.append(row[0].toString());
	            post = row[1].toString();
	            this.logger.info("row[0] = " + cell1);
	          }
	          this.logger.info("post " + cell);
	          
	          propertyList1.append("<post-mapping>");
	          propertyList1.append("<postId>").append(cell1).append("</postId>");
	          propertyList1.append("<post>").append("<![CDATA[").append(post).append("]]>").append("</post>");
	          propertyList1.append("</post-mapping>");
	          
	          lObjComboValuesVO = new ComboValuesVO();
	          lObjComboValuesVO.setId(Long.valueOf(cell1.toString()).toString());
	          lObjComboValuesVO.setDesc(post.toString());
	          lLstReturnList1.add(lObjComboValuesVO);
	        }
	        objectArgs.put("SvnPcBasic", lLstReturnList1);
	        this.logger.info(Integer.valueOf(lLstReturnList1.size()));
	      }
	      this.logger.info(propertyList1.toString());
	      Map result = new HashMap();
	      String postData = new AjaxXmlBuilder().addItem("ajax_key", 
	        propertyList1.toString()).toString();
	      this.logger.info("Post data is " + postData);
	      result.put("ajaxKey", postData);
	      
	      resultObject.setResultValue(result);
	      String lStrCheck = StringUtility.getParameter("ifAjax", 
	        (HttpServletRequest)objectArgs.get("requestObj"));
	      resultObject.setViewName("ajaxData");
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    return resultObject;
	  }
	
	public ResultObject GetSevenPcBasic(Map objectArgs)
	  {
	    this.logger.info("Coming in the service");
	    this.logger.info("***************in scale combo population service**************");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	    ResultObject resultObject = new ResultObject(0);
	    try
	    {
	      Map voToService = (Map)objectArgs.get("voToServiceMap");
	      this.logger.info("In try \n " + voToService);
	      
	      Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
	      
	      StringBuffer propertyList1 = new StringBuffer();
	      String strCell = voToService.get("cell").toString();
	      System.out.println("Cell Parvez IS ++++++++"+strCell);
	      List userpostrltSet1 = new ArrayList();
	      UserPostDAO userPostDAO = new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
	  	String levelId = userPostDAO.getLevelId(strCell); 
	      userpostrltSet1 = userPostDAO.getSvnPcData(levelId);
	      this.logger.info("before add second list" + userpostrltSet1);
	      
	      String id = "";
	      long cell = 0L;
	      String post = "";
	      long postid = 0L;
	      Object[] row = null;
	      ComboValuesVO lObjComboValuesVO = null;
	      List<Object> lLstReturnList1 = null;
	      if ((userpostrltSet1 != null) && (userpostrltSet1.size() > 0))
	      {
	        this.logger.info("in if block userpostrltSet1.size() " + 
	          userpostrltSet1.size());
	        lLstReturnList1 = new ArrayList();
	        for (int k = 0; k < userpostrltSet1.size(); k++)
	        {
	          this.logger.info("in for loop userpostrltSet1.size() " + 
	            userpostrltSet1.size());
	          row = (Object[])userpostrltSet1.get(k);
	          StringBuffer cell1 = new StringBuffer();
	          if (row[0] != null)
	          {
	            cell1.append(row[0].toString());
	            post = row[1].toString();
	            this.logger.info("row[0] = " + cell1);
	          }
	          this.logger.info("post " + cell);
	          
	          propertyList1.append("<post-mapping>");
	          propertyList1.append("<postId>").append(cell1).append("</postId>");
	          propertyList1.append("<post>").append("<![CDATA[").append(post).append("]]>").append("</post>");
	          propertyList1.append("</post-mapping>");
	          
	          lObjComboValuesVO = new ComboValuesVO();
	          lObjComboValuesVO.setId(Long.valueOf(cell1.toString()).toString());
	          lObjComboValuesVO.setDesc(post.toString());
	          lLstReturnList1.add(lObjComboValuesVO);
	        }
	        objectArgs.put("SvnPcBasic", lLstReturnList1);
	        this.logger.info(Integer.valueOf(lLstReturnList1.size()));
	      }
	      this.logger.info(propertyList1.toString());
	      Map result = new HashMap();
	      String postData = new AjaxXmlBuilder().addItem("ajax_key", 
	        propertyList1.toString()).toString();
	      this.logger.info("Post data is " + postData);
	      result.put("ajaxKey", postData);
	      
	      resultObject.setResultValue(result);
	      String lStrCheck = StringUtility.getParameter("ifAjax", 
	        (HttpServletRequest)objectArgs.get("requestObj"));
	      resultObject.setViewName("ajaxData");
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    return resultObject;
	  }

}

