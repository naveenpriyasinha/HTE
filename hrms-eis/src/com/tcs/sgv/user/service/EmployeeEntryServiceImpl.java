package com.tcs.sgv.user.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.delegate.UserDelegate;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.user.dao.UserPostDAO;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDao;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDao;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.fms.dao.WfOrgUsrMpgMstDao;
import com.tcs.sgv.fms.dao.WfOrgUsrMpgMstDaoImpl;
import com.tcs.sgv.fms.valueobject.CmnProjectMst;
import com.tcs.sgv.fms.valueobject.WfOrgLocMpgMst;
import com.tcs.sgv.fms.valueobject.WfOrgUsrMpgMst;
import com.tcs.sgv.user.dao.EmployeeEntryDAO;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;

public class EmployeeEntryServiceImpl extends ServiceImpl implements EmployeeEntryService{
	Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	public ResultObject getNewUserEntryData(Map objServiceArgs)
	{
		logger.info("inside getNewUserEntryData ");
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{	
			
			logger.info("inside service1");
            ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
            OrgUserMstDao orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
            UserPostDAO userDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
            Map loginDetails=(Map)objServiceArgs.get("baseLoginMap");
            logger.info("inside service2");
    		long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
    		UserPostDAO userPostDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
    		List userList = new ArrayList();
    		 List usernewList =  new ArrayList();
    		logger.info("Before voToService");
    		 Map voToService = (Map)objServiceArgs.get("voToServiceMap");
    		 logger.info("After voToService");
    		 String edit = "";
			 logger.info("inside service2"+voToService.get("edit"));
			 if(voToService.get("edit")!=null)
				{
				 logger.info("inside if");
				 edit = (String)voToService.get("edit").toString();
				  logger.info("edit "+edit);
				}
    		
    		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
			cmnLanguageMst.setLangId(langId);
			
			
			if(edit.equalsIgnoreCase("Y"))
			{
				String empIdStr = (String)voToService.get("Employee_ID_NewUserSearch");
				logger.info("the emp id from search employee "+empIdStr);
				 UserPostCustomVO userpostcustomvo = new UserPostCustomVO();
				
				 if(empIdStr!=null && !empIdStr.equals(""))
		        	{
					 usernewList = new ArrayList();
		        		long empid = Long.parseLong(empIdStr);
		        		
		        		logger.info("***************EMPLOYEE ID--------->"+empid);
		        		usernewList=userPostDAO.getAllUserDataFromEmpId(empid, langId);
		        		logger.info("Inside Loop***********************"+usernewList.size());
		    			 if(usernewList!=null &&  usernewList.size() >0 )
						 {                		
			 		    	 for(int k=0;k<usernewList.size();k++) 
			 		    	 {
			 		    		logger.info("size is********");
			 		    		Object[] row = (Object[])usernewList.get(k);
			 		    		
			 		    		 long empId =Long.parseLong(row[0].toString()) ;
			 		    		userpostcustomvo.setEmpId(empId);
			 		            
			 		            String empFname = row[1].toString();
			 		           userpostcustomvo.setEmpFname(empFname);
			 		            
			 		            String empLname = row[3].toString();
			 		           userpostcustomvo.setEmpLname(empLname);
			 		            
			 		            long userId =Long.parseLong(row[4].toString()) ;
			 		           userpostcustomvo.setUserId(userId);
			 		       	    
			 		       	 String userName = row[5].toString();
			 		       	userpostcustomvo.setUserName(userName);

			 		    		
			 		    		  
			 		    		
			 			          userList.add(userpostcustomvo);	    	
			 		    	 }    			
						 }

			}
			}
			else{
				
			
            	List userEntryList = userDAO.getAllUserDataFromUserId(langId);
            	
            	
            	logger.info("The size of userEntryList is-------------->"+userEntryList.size());
				
				UserPostCustomVO userpostcustomVO = new UserPostCustomVO();

				
	    		
	    		
	        	for(int i=0;i<userEntryList.size();i++)
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
		            
		            
	    		 }
			}    	 
		 
            	
            	logger.info("list size in userList is------------>"+userList.size());
                objServiceArgs.put("userEntryList", userList);
                resultObject.setResultCode(ErrorConstants.SUCCESS);
                resultObject.setResultValue(objServiceArgs);
                resultObject.setViewName("employeeEntryView");	   
                
               
            
		
		}
		
		catch(Exception e)
		{		
			logger.info("Exception in getNewUserEntryData");
			//e.printStackTrace();
		
            resultObject.setResultValue(objServiceArgs);

           
		}
	
		return resultObject;
	}
	
	
	
	
	
	
	
	
	
	
	
	public ResultObject checkUserid(Map objectArgs)
	{
		
		//System.out.println("In side Check UserId=======");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long userId = (objectArgs.get("userId") != null && !objectArgs.get("userId").toString().equals("")) ? Long.parseLong(objectArgs.get("userId").toString()) : 0;
				//System.out.println("1");
				OrgUserMstDao orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				//System.out.println("2");
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
				
				EmpDAO objEmpDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				
				CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
				cmnLanguageMst.setLangId(langId);
				
				OrgGradeMstDao orgGradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
				List classDtls = orgGradeMstDao.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);
				EmployeeEntryDAO employeeEntryDAO = new EmployeeEntryDAO(OrgUserMst.class,serv.getSessionFactory());
				List desigList = employeeEntryDAO.getAlldsgnData(langId);
				//System.out.println("desigList"+desigList.size());
			
				
			
				if(orgUserMst == null)
				{
					//System.out.println("inside if");
					userId=0;
					objectArgs.put("userId", userId);
					objRes.setResultValue(objectArgs);
					objRes.setResultCode(ErrorConstants.SUCCESS);
					objRes.setViewName("employeeEntry");
					
				}
				else
				{
					//System.out.println("inside else");
					List<OrgEmpMst> empInfoDtls = objEmpDAO.getListByColumnAndValue("orgUserMst", orgUserMst);
					
					if(empInfoDtls != null && empInfoDtls.isEmpty())
					{
						//System.out.println("inside else if");
						objRes.setViewName("employeeEntry");
						objRes.setResultValue(objectArgs);
						objRes.setResultCode(ErrorConstants.SUCCESS);
					}
					else
					{
						//System.out.println("inside else else");
						ListIterator<OrgEmpMst> empInfoListIntr = empInfoDtls.listIterator();
						
						OrgEmpMst orgEmpMst = null;
						
						while(empInfoListIntr.hasNext())
						{
							orgEmpMst = empInfoListIntr.next();
							if(orgEmpMst != null && orgEmpMst.getCmnLanguageMst().getLangId()==langId)
							{
								if(orgEmpMst.getCmnLanguageMst()!= null && orgEmpMst.getCmnLanguageMst().getLangId()==1)
								{
									objectArgs.put("empInfoDtlsEng", orgEmpMst);
									
								}
								else if(orgEmpMst.getCmnLanguageMst() != null && orgEmpMst.getCmnLanguageMst().getLangId()==2)
								{
									objectArgs.put("empInfoDtlsGuj", orgEmpMst);
								}
								objectArgs.put("empInfoDtls", orgEmpMst);
								String gradeCode=orgEmpMst.getOrgGradeMst().getGradeCode();
								//System.out.println("gradeCode===="+gradeCode);
								objectArgs.put("gradeCode", gradeCode);
							}
						}
						objRes.setResultValue(objectArgs);
						objRes.setResultCode(ErrorConstants.SUCCESS);
						objRes.setViewName("employeeEntry");
					}
					objectArgs.put("userId", userId);
				}
				objectArgs.put("classDtls", classDtls);
				objectArgs.put("desigList", desigList);
				
			}
		}
		catch (Exception e) 
		{
			objRes.setResultCode(ErrorConstants.ERROR);
			e.printStackTrace();
		}
		return objRes;
	}


	
	public ResultObject saveEmpBasicInfo(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		int msg=0;
		try 
		{ 
			
			if (objRes != null && objectArgs != null) 
			{
				
				logger.info("In side saveEmpBasicInfo ");
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
				
				/* Getting a DB ID*/								 
				long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/
				

				/* Getting a Loc ID Code*/
				long locId =  Long.parseLong(loginDetailsMap.get("locationId").toString());
				/* End of Loc ID*/	
				
				//Getting code for created By
				long crId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMstcrId=orgUserMstDaoImpl.read(crId);
				
				/* Get The Person Post */
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
				/* End of the geting Person Post Code */
				
				/* Getting a Lang Id Code for English*/
				long langIdEng=1;
				CmnLanguageMst cmnLangMstEng = cmnLanguageMstDaoImpl.read(langIdEng);
				/* End of Lang ID Code */
				
				//Getting a Lang Id Code for Gujarati
				long langIdGuj=2;
				CmnLanguageMst cmnLangMstGuj = cmnLanguageMstDaoImpl.read(langIdGuj);
				
				
				//Getting a Grade Id code
				OrgGradeMst obGradeMst = null;
				
				
				long srFlag = 1;
				
				OrgEmpMst orgEmpMstEng = (OrgEmpMst)objectArgs.get("empInfoEng");
				OrgEmpMst orgEmpMstGuj = (OrgEmpMst)objectArgs.get("empInfoGuj");


				OrgUserMst orgUserMst = orgEmpMstEng.getOrgUserMst();
				
				// Creation of User - Starts
				
				if (orgUserMst != null && orgUserMst.getUserId() == 0)
				{
					//System.out.println("Create User=====");
					UserDelegate userDelegate = UserDelegate.getInstance();
					
					String dateOfBirth = objectArgs.get("dateOfBirth").toString();
					String dateOfJoin = objectArgs.get("dateOfJoin").toString();
					//System.out.println("The date of birth is------>"+dateOfBirth);
					//System.out.println("The date of join is------>"+dateOfJoin);
					
					orgUserMst = userDelegate.createUser(orgEmpMstEng.getEmpFname(), orgEmpMstEng.getEmpLname(),dateOfBirth , dateOfJoin, objectArgs, serv);
				
					WfOrgUsrMpgMst wfUsermst=new WfOrgUsrMpgMst();
					wfUsermst.setUserId(orgUserMst.getUserId()+"");
					wfUsermst.setCmnDatabaseMst(cmnDatabaseMst);
					CmnProjectMst cmnProjectMst=new CmnProjectMst();
					cmnProjectMst.setProjectId(102);
					wfUsermst.setCmnProjectMst(cmnProjectMst);
					WfOrgLocMpgMst wfOrgLocMpgMst=new WfOrgLocMpgMst();
					wfOrgLocMpgMst.setLocId(locId+"");
					//wfUsermst.setWfOrgLocMpgMst(wfOrgLocMpgMst);
					WfOrgUsrMpgMstDao wfOrgUsrMpgMstDao=new WfOrgUsrMpgMstDaoImpl(WfOrgUsrMpgMst.class,serv.getSessionFactory());
					wfOrgUsrMpgMstDao.create(wfUsermst);
				}
				// Creation of User - Ends
				
				String prefix = orgEmpMstEng.getEmpPrefix();
				String empFName = orgEmpMstEng.getEmpFname();
				String empMName = orgEmpMstEng.getEmpMname();
				String empLName = orgEmpMstEng.getEmpLname();
				Date dob = orgEmpMstEng.getEmpDob();
				Date doj = orgEmpMstEng.getEmpDoj();
				Date dor = orgEmpMstEng.getEmpSrvcExp();
				String gradeCode = orgEmpMstEng.getOrgGradeMst().getGradeCode();
				
				String empFNameGuj = orgEmpMstGuj.getEmpFname();
				String empMNameGuj = orgEmpMstGuj.getEmpMname();
				String empLNameGuj = orgEmpMstGuj.getEmpLname();
				
				
				OrgEmpMst orgEmpMstEngSr = new OrgEmpMst();
				OrgEmpMst orgEmpMstGujSr = new OrgEmpMst();
				
				
				
				
				OrgGradeMstDao gradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
				obGradeMst = gradeMstDao.getGradeVOByGradeCodeAndLangId(gradeCode, langId);
				
				logger.info("the orgGrademst is "+obGradeMst);
				
				if(prefix!=null&& !prefix.equals("") )
				{
					orgEmpMstEngSr.setEmpPrefix(prefix);
					orgEmpMstGujSr.setEmpPrefix(prefix);
				}
				if(empFName!=null  && !empFName.equals(""))
					orgEmpMstEngSr.setEmpFname(empFName);
				
				if(empMName!=null && !empMName.equals(""))	
					orgEmpMstEngSr.setEmpMname(empMName);
				if(empMName!=null && !empLName.equals(""))
					orgEmpMstEngSr.setEmpLname(empLName);
				if(empFNameGuj!=null && !empFNameGuj.equals(""))
					orgEmpMstGujSr.setEmpFname(empFNameGuj);
				if(empFNameGuj!=null && !empMNameGuj.equals(""))
					orgEmpMstGujSr.setEmpMname(empMNameGuj);
				if(empLNameGuj!=null && !empLNameGuj.equals(""))
					orgEmpMstGujSr.setEmpLname(empLNameGuj);
				if(dob != null)
				{
					orgEmpMstEngSr.setEmpDob(dob);
					orgEmpMstGujSr.setEmpDob(dob);
				}
				if(doj != null)	
				{
					orgEmpMstEngSr.setEmpDoj(doj);
					orgEmpMstGujSr.setEmpDoj(doj);
					
				}
				if(dor != null)
				{
					orgEmpMstEngSr.setEmpSrvcExp(dor);
					orgEmpMstGujSr.setEmpSrvcExp(dor);
					
				}
				if(cmnLangMstEng != null)
					orgEmpMstEngSr.setCmnLanguageMst(cmnLangMstEng);
				if(cmnLangMstGuj != null)
					orgEmpMstGujSr.setCmnLanguageMst(cmnLangMstGuj);
				
				orgEmpMstEngSr.setCreatedDate(new Date());
				orgEmpMstEngSr.setStartDate(new Date());
				
				orgEmpMstGujSr.setCreatedDate(new Date());
				orgEmpMstGujSr.setStartDate(new Date());
				
				
				
			
				if(orgUserMstcrId != null)
				{
					orgEmpMstEngSr.setOrgUserMstByCreatedBy(orgUserMstcrId);
					orgEmpMstGujSr.setOrgUserMstByCreatedBy(orgUserMstcrId);
					
				}
				if(orgPostMst != null)
				{
					orgEmpMstEngSr.setOrgPostMstByCreatedByPost(orgPostMst);
					orgEmpMstGujSr.setOrgPostMstByCreatedByPost(orgPostMst);
					
				}
				if(srFlag != 0)
				{
					orgEmpMstEngSr.setEmpSrvcFlag(srFlag);
					orgEmpMstGujSr.setEmpSrvcFlag(srFlag);
				}
			
				// THIS IS IMPORTANT PART
				if(orgUserMst != null)
				{
					orgEmpMstEngSr.setOrgUserMst(orgUserMst);
					orgEmpMstGujSr.setOrgUserMst(orgUserMst);
				}
				// END
				
				if(obGradeMst != null)
				{
					orgEmpMstEngSr.setOrgGradeMst(obGradeMst);
					orgEmpMstGujSr.setOrgGradeMst(obGradeMst);
				}
				
					
				String[] strOrgEmpMstColumns = {"cmnLanguageMst","orgUserMst"};
				Object[] orgEmpMstValuesEng ={cmnLangMstEng, orgUserMst};
				Object[] orgEmpMstValuesGuj ={cmnLangMstGuj, orgUserMst};
								
				EmpDAO orgEmpDAO = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
				
				List empInfoDtlsEng = orgEmpDAO.getListByColumnAndValue(strOrgEmpMstColumns, orgEmpMstValuesEng);
				List empInfoDtlsGuj = orgEmpDAO.getListByColumnAndValue(strOrgEmpMstColumns, orgEmpMstValuesGuj);
				
				if(empInfoDtlsEng != null && empInfoDtlsEng.isEmpty())
				{
					//ID Genration for English
					long reqIdEng = 0;
					reqIdEng = IDGenerateDelegate.getNextId("org_emp_mst", objectArgs);
					//System.out.println("reqIdEng============="+reqIdEng);
					if(reqIdEng != 0){
						orgEmpMstEngSr.setEmpId(reqIdEng);
						orgEmpMstEngSr.setTrnCounter(1);
					}
					if(orgEmpMstEngSr != null)
						orgEmpDAO.create(orgEmpMstEngSr);
				}
				else
				{
					OrgEmpMst orgEmpMstEngpk = null;
					if(empInfoDtlsEng!=null && !empInfoDtlsEng.isEmpty()){
						orgEmpMstEngpk = (OrgEmpMst)empInfoDtlsEng.get(0);
					}
					OrgEmpMst objOrgempMstEng = null;
					if(orgEmpMstEngpk != null && orgEmpMstEngpk.getEmpId() != 0)
					{
						long reqIdEng = orgEmpMstEngpk.getEmpId();
						objOrgempMstEng =(OrgEmpMst)orgEmpDAO.read(reqIdEng);
					}
					if(objOrgempMstEng!=null)
					{
						if(prefix!=null &&!prefix.equals(""))
							objOrgempMstEng.setEmpPrefix(prefix);
						if(empFName!=null && !empFName.equals(""))
							objOrgempMstEng.setEmpFname(empFName);
						if(empMName!=null && !empMName.equals(""))
							objOrgempMstEng.setEmpMname(empMName);
						if(empLName!=null && !empLName.equals(""))
							objOrgempMstEng.setEmpLname(empLName);
						if(dob != null)
							objOrgempMstEng.setEmpDob(dob);
						if(doj != null)
							objOrgempMstEng.setEmpDoj(doj);
						if(dor != null)
							objOrgempMstEng.setEmpSrvcExp(dor);
						if(obGradeMst != null)
							objOrgempMstEng.setOrgGradeMst(obGradeMst);
						if(orgPostMst != null)
							objOrgempMstEng.setOrgPostMstByUpdatedByPost(orgPostMst);
						if(orgUserMstcrId != null)
							objOrgempMstEng.setOrgUserMstByUpdatedBy(orgUserMstcrId);
						
							objOrgempMstEng.setUpdatedDate(new Date());
						
						orgEmpDAO.update(objOrgempMstEng);
						msg=1;
					}
				}
				
				if(empInfoDtlsGuj != null && empInfoDtlsGuj.isEmpty())
				{
					//ID Genration for Gujarati
					long reqIdGuj = 0;
					reqIdGuj = IDGenerateDelegate.getNextId("org_emp_mst", objectArgs);
					//System.out.println("reqIdGuj================="+reqIdGuj);
					if(reqIdGuj != 0){
						orgEmpMstGujSr.setEmpId(reqIdGuj);
						orgEmpMstGujSr.setTrnCounter(1);
					}
					if(orgEmpMstGujSr != null)
						orgEmpDAO.create(orgEmpMstGujSr);
				}
				else
				{
					OrgEmpMst orgEmpMstGujpk=null;
					if(empInfoDtlsGuj!=null && !empInfoDtlsGuj.isEmpty())
					  orgEmpMstGujpk = (OrgEmpMst)empInfoDtlsGuj.get(0);
					OrgEmpMst objOrgempMstGuj = null;
					if(orgEmpMstGujpk != null && orgEmpMstGujpk.getEmpId() != 0)
					{
						long reqIdGuj = orgEmpMstGujpk.getEmpId();
						objOrgempMstGuj =(OrgEmpMst)orgEmpDAO.read(reqIdGuj);
					}
					if(objOrgempMstGuj!=null)
					{	
						if(prefix!=null && !prefix.equals(""))
							objOrgempMstGuj.setEmpPrefix(prefix);
						if(empFNameGuj!=null && !empFNameGuj.equals(""))
							objOrgempMstGuj.setEmpFname(empFNameGuj);
						if(empMNameGuj!=null && !empMNameGuj.equals(""))
							objOrgempMstGuj.setEmpMname(empMNameGuj);
						if(empLNameGuj!=null && !empLNameGuj.equals(""))
							objOrgempMstGuj.setEmpLname(empLNameGuj);
						if(dob != null)
							objOrgempMstGuj.setEmpDob(dob);
						if(doj != null)
							objOrgempMstGuj.setEmpDoj(doj);
						if(dor != null)
							objOrgempMstGuj.setEmpSrvcExp(dor);
						if(obGradeMst != null)
							objOrgempMstGuj.setOrgGradeMst(obGradeMst);
						if(orgPostMst != null)
							objOrgempMstGuj.setOrgPostMstByUpdatedByPost(orgPostMst);
						if(orgUserMstcrId != null)
							objOrgempMstGuj.setOrgUserMstByUpdatedBy(orgUserMstcrId);
						
							objOrgempMstGuj.setUpdatedDate(new Date());
						
						orgEmpDAO.update(objOrgempMstGuj);
					}
					
				}
			/*	List userList = new ArrayList();
				UserPostDAO userDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
            	List userEntryList = userDAO.getAllUserDataFromUserId(langId);
            	
            	
            	logger.info("The size of userEntryList is-------------->"+userEntryList.size());
				
				UserPostCustomVO userpostcustomVO = new UserPostCustomVO();

				
	    		
	    		
	        	for(int i=0;i<userEntryList.size();i++)
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
			    	 
				
				
	        	if(msg==1)
					objectArgs.put("MESSAGECODE",300006);
				else
					objectArgs.put("MESSAGECODE",300005);
				
				
				/*CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
				cmnLanguageMst.setLangId(langId);
				OrgGradeMstDao orgGradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
				List classDtls = orgGradeMstDao.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);*/

				//objectArgs.put("classDtls", classDtls);				
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
				/*logger.info("list size in userList is------------>"+userList.size());
				objectArgs.put("userEntryList", userList);*/
				//objRes.setViewName("employeeEntryView");
				objRes.setViewName("employeeEntry");
			}
		}
		catch(Exception e)
		{
			objRes.setResultCode(ErrorConstants.ERROR);
			e.printStackTrace();
		}
		
		return objRes;
	}


	}
