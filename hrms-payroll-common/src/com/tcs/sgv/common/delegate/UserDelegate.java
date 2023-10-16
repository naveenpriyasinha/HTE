package com.tcs.sgv.common.delegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.ess.dao.OrgUserMstDao;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


public class UserDelegate {
	
	private static UserDelegate instance = null;
	
	protected UserDelegate()
	{
	      // Exists only to defeat instantiation.
	}
	
	public static UserDelegate getInstance()
	{
	    synchronized (UserDelegate.class)
	    {
		  if(instance == null)
	      {
	         instance = new UserDelegate();
	      }
	      
	      return instance;
	  	} 
	}
	
	public synchronized OrgUserMst createUser(String strFirstName, String strLastName, String strDOB, String strDOJ, Map objectArgs, ServiceLocator serv) {
		
		OrgUserMst orgUserMst = new OrgUserMst();
		
		try
		{
			Map loginMap = (Map)objectArgs.get("baseLoginMap");
			
			long postId=Long.parseLong(loginMap.get("loggedInPost").toString());
			long langId= Long.parseLong(loginMap.get("langId").toString());
			
			Date BDate=StringUtility.convertStringToDate(strDOB);
			
			SimpleDateFormat Dateformat=new SimpleDateFormat("ddMMMyyyy");
			String BirthDate=Dateformat.format(BDate);
			
			Character firstNameLetter= strFirstName.charAt(0);
			Character lastNameLetter= strLastName.charAt(0);
			
			String userName = firstNameLetter.toString()+lastNameLetter.toString()+BirthDate;
			
			String UserName = this.generateUserName(userName,serv);
			
			//System.out.println("UserName*********"+UserName);
			
			String PassWord="a";
			
			long userId=IDGenerateDelegate.getNextId("org_user_mst", objectArgs);
			
			orgUserMst.setUserId(userId);
			orgUserMst.setUserName(UserName);
			orgUserMst.setPassword(PassWord);
			
			Date DateofJoining=StringUtility.convertStringToDate(strDOJ);
			orgUserMst.setStartDate(DateofJoining);
			
			CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			
			CmnLookupMst cmnLookupMst=cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("ACTIVE STATUS", 1);
			orgUserMst.setCmnLookupMst(cmnLookupMst);
			
			orgUserMst.setCreatedDate(new Date());
			
			OrgEmpMst orgEmpMstByCreatedBy=new OrgEmpMst();
			orgEmpMstByCreatedBy.setEmpId(1l);
			orgUserMst.setOrgUserMstByCreatedBy(orgUserMst);
			//setOrgEmpMstByCreatedBy(orgEmpMstByCreatedBy);
		
			OrgPostMst orgPostMstByCreatedByPost=new OrgPostMst();
			orgPostMstByCreatedByPost.setPostId(postId);
			orgUserMst.setOrgPostMstByCreatedByPost(orgPostMstByCreatedByPost);
			/*#########################################################################*/
			//Added By Varun For Dt.04-08-2008
			String firstLogin = "N";
			orgUserMst.setFirstlogin(firstLogin);
			orgUserMst.setSecretAnswer(UserName);
			orgUserMst.setPwdchangedDate(new Date());
			orgUserMst.setSecretQueCode("Secret_Other");
			orgUserMst.setSecretQueOther("Enter your Username");
			//Ended By Varun For Dt.04-08-2008
			/*#########################################################################*/
			OrgUserMstDao orgUserMstDao=new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			orgUserMstDao.create(orgUserMst);
			//User Testing
			
			//System.out.println("User ID******"+userId);
		}
		catch (Exception e) {
			e.printStackTrace();
			// logger.i
		}
		
		return orgUserMst;
	}
	
	public String generateUserName(String UserName, ServiceLocator serv)
	{
		String strGeneratedUserName = UserName;
		
		OrgUserMstDao objOrgUserMstDao = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		
		// List<OrgUserMst> userList =  objAllocationDaoImpl.getSimilarUserNameList(UserName);
		
		List<OrgUserMst> userList =  objOrgUserMstDao.getListByColumnAndValue("userName", UserName+"%");
		
		int iUserCounter = 0;
		
		if (userList.size() > 0)
		{	
			int iTempCounter = 0;
			
			for (OrgUserMst orgUserMst : userList)
			{
				String strUserName = orgUserMst.getUserName();
				
				if (strUserName != null && strUserName.indexOf("#") != 0)
				{
					try
					{
						iTempCounter = 	Integer.parseInt(strUserName.substring(strUserName.indexOf("#")+1));
						
						if (iTempCounter > iUserCounter)
						{
							iUserCounter = iTempCounter;
						}
					}
					catch(NumberFormatException nfe){}
				}
			}
			
			strGeneratedUserName = strGeneratedUserName +"#"+ (iUserCounter+1);
		}
			
		
		return strGeneratedUserName;
	}
}
