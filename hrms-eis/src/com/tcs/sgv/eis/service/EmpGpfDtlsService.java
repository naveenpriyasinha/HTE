package com.tcs.sgv.eis.service;

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

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.valueobject.GpfAcctDtlsVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.eis.util.EmpGpfDtlsServiceHelper;

public class EmpGpfDtlsService extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	
	int msg=0;
	public ResultObject insertGpfDtlsData(Map objServiceArgs)
	{
		HrPayGpfBalanceDtls hrGpfBalanceDtls=new HrPayGpfBalanceDtls();
		logger.info("inside insertGpfDtlsData---------->");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
		//HttpSession session=request.getSession();
		//Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
		Map loginDetailsMap=(Map)objServiceArgs.get("baseLoginMap");
		
//		long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
		
		try
		{
			HrPayGpfBalanceDtls GpfDtls= (HrPayGpfBalanceDtls)objServiceArgs.get("GpfDtls");
			ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
			String editFromVO = "";
			editFromVO=objServiceArgs.get("edit")!=null?objServiceArgs.get("edit").toString():"";
			
			
			
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());	
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());


			EmpGpfDtlsDAOImpl gpfDtls = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
	       
	        langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
		
			//added by Ankit Bhatt
			OrgGradeMstDaoImpl OrgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
			//ended
			Date sysdate = new Date();
			EmpGpfDtlsServiceHelper helper = new EmpGpfDtlsServiceHelper(serv);
			if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))
			{
				logger.info("Inside Update Mode");
				logger.info("Inside Update Mode");
				long gpfId= GpfDtls.getUserId();
				logger.info("The gpfId id is---->"+gpfId);
				HrPayGpfBalanceDtls gpfDetails=gpfDtls.read(gpfId);
				
				long gradeId = objServiceArgs.get("gradeId")!=null?Long.parseLong(objServiceArgs.get("gradeId").toString()):0;
				//this.updateGpfDtlsData(serv, gpfDetails, gradeId, postId, userId);
				helper.updateGpfDtlsData(gpfDetails, gradeId, postId, userId);
				objServiceArgs.put("msg", "Record Updated Successfully");
				msg=1;
				
				objServiceArgs.put("MESSAGECODE",300006);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setViewName("GpfAcctScreenUpdate");
				
					
				
			}
			else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
			{
				logger.info("Inside Insert Mode");
				//added by Ankit Bhatt
				long gradeId = objServiceArgs.get("gradeId")!=null?Long.parseLong(objServiceArgs.get("gradeId").toString()):0;
				OrgGradeMst orgGradeMst = new OrgGradeMst();
				orgGradeMst = OrgGradeMstDaoImpl.read(gradeId);
				//this.insertGpfDtlsData(serv, GpfDtls, langId, dbId, postId, userId, locId, orgGradeMst);
				helper.insertGpfDtlsData(GpfDtls, langId, dbId, postId, userId, locId, orgGradeMst);
				objServiceArgs.put("msg", "Record Inserted Successfully");
				objServiceArgs.put("MESSAGECODE",300005);
				resultObject.setViewName("GpfAcctScreenAdd");
			
			}
			
			else
			{
				//view Entry Screen
				
				resultObject.setViewName("GpfAcctScreenAdd");
			}
			logger.info("editFromVO " + editFromVO+" testing ");
			if(editFromVO!=null && !editFromVO.equalsIgnoreCase("") && !editFromVO.equalsIgnoreCase(" "))
			{	
		    long gpfUserId = GpfDtls.getUserId();
		    
		    EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
		       
		    
		    OrgEmpMst orgEmpMst = orgEmpDao.getEmpFromUser(orgUserMstDaoImpl.read(gpfUserId), langId);
			
		    orgEmpMst.setEmpGPFnumber(GpfDtls.getGpfAccNo());
		    
		    logger.info("going to update emp gpf account");
		    
		    orgEmpDao.update(orgEmpMst);
			}
			resultObject.setResultValue(objServiceArgs);			
		
			
		}
		catch(Exception e)
		{			
			logger.info("exception occurs in insertGpfDtlsData of EmpGpfDtlsService "+e);
			
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");
		}
		
		return resultObject;
	}
	
	//Added by Abhilash
	/*public void insertGpfDtlsData(ServiceLocator serv,HrPayGpfBalanceDtls GpfDtls,long langId,long dbId,long postId,long userId,long locId,OrgGradeMst orgGradeMst)
	{
		
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
		
		Date sysdate= new Date();
		
		
		
		GpfDtls.setOrgGradeMst(orgGradeMst);
		//ended
		GpfDtls.setCmnDatabaseMst(cmnDatabaseMst);
		GpfDtls.setOrgPostMstByCreatedByPost(orgPostMst);
		GpfDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
		GpfDtls.setCmnLocationMst(cmnLocationMst);
		GpfDtls.setOrgUserMstByCreatedByUser(orgUserMst);
		GpfDtls.setOrgUserMstByUpdatedByUser(orgUserMst);
		GpfDtls.setCreatedDate(sysdate);
		GpfDtls.setTrnCounter(Integer.valueOf(1));
			
		EmpGpfDtlsDAOImpl gpfDtls = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
		
		try
		{
			gpfDtls.create(GpfDtls);
		}
		catch(Exception e)
		{
			logger.info("Inside Exception Mode"+e);
		}
		
		
	}
	
	public void updateGpfDtlsData(ServiceLocator serv,HrPayGpfBalanceDtls gpfDetails,long gradeId,long postId,long userId)
	{
		Date sysdate = new Date();
		OrgGradeMstDaoImpl OrgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		EmpGpfDtlsDAOImpl gpfDtls = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
		
		OrgGradeMst orgGradeMst = new OrgGradeMst();
		orgGradeMst = OrgGradeMstDaoImpl.read(gradeId);
		gpfDetails.setOrgGradeMst(orgGradeMst);
		//ended
		gpfDetails.setOrgPostMstByUpdatedByPost(orgPostMst);
		gpfDetails.setOrgUserMstByCreatedByUser(orgUserMst);				
		gpfDetails.setUpdatedDate(sysdate);
		gpfDtls.update(gpfDetails);
	}
	//Ended by Abhilash
*/	
	
	
//added by Ankit Bhatt - Called on Add New Entry from GPF Details Screen
	public ResultObject getGpfNewEntryPage(Map objServiceArgs)
	{
		logger.info("---------------inside getGpfNewEntryPage-------------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
            ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
            Map loginDetailsMap=(Map)objServiceArgs.get("baseLoginMap");
            long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
            OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
            List gradeList = orgGradeMstDaoImpl.getListByColumnAndValue("cmnLanguageMst.langId", langId);
            logger.info("GradeList size is " + gradeList.size());
            objServiceArgs.put("gradeList",gradeList);
            resultObject.setResultValue(objServiceArgs);
            resultObject.setViewName("GpfAcctScreenAdd");
                        
		}
		catch(ClassCastException c)
		{
			logger.info("Inside Exception Mode"+c);
		}
		catch(Exception e)
		{		
			logger.info("Inside Exception Mode"+e);
			logger.error("Error is: "+ e.getMessage());
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");
			
		}
	
		return resultObject;
	}
	
//ended

	
public ResultObject getGpfDtls(Map objServiceArgs)
	{
		logger.info("---------------inside getGpfDtls-------------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			
            ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
                
            
            EmpGpfDtlsDAOImpl gpfDtlsdao = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
            //HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
            
            //HttpSession session=request.getSession();		
            Map loginDetails=(Map)objServiceArgs.get("baseLoginMap");
    		long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
    		long locationId=StringUtility.convertToLong(loginDetails.get("locationId").toString());
    		//long languageId=1;
    		//CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			//CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(languageId);
            
    		Map voToService = (Map)objServiceArgs.get("voToServiceMap");
            String chk="";
            
            //added by Ankit Bhatt           
            OrgGradeMstDaoImpl orgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
            List gradeList = orgGradeMstDaoImpl.getListByColumnAndValue("cmnLanguageMst.langId", langId);
            logger.info("GradeList size is " + gradeList.size());
           
            //ended
			if(voToService!=null&&voToService.get("chk")!=null)
                chk=(String) voToService.get("chk");
			if(chk!=null && chk.equals("1"))
			{
			String strEmpId =voToService.get("empId")!=null? voToService.get("empId").toString():"";
			StringBuffer empNameBf=new StringBuffer();
			long empId=0;			
			if(strEmpId!=null && !strEmpId.equals(""))
				empId=Long.parseLong(strEmpId);		
			logger.info("The Employee Id is:-"+empId);
			long empType=0;
			if(empId!=0)
			{
            EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
            List<HrEisEmpMst> empList =  empinfoDao.getEmpIdData(empId);
            HrEisEmpMst hreisEmpMst =new HrEisEmpMst();

            for(int j=0;j<empList.size();j++)
            {
            	
            	hreisEmpMst =new HrEisEmpMst();
            	HrEisEmpMst hrEisempMstgu =new HrEisEmpMst();            	
            	hreisEmpMst=empList.get(j);
            	if(hreisEmpMst.getEmpId()==empId)
            	{
            	empType=hreisEmpMst.getEmpType();
            	break;
            	}
            	//hrEisempMstgu.setEmpId(hreisEmpMst.getEmpId());

            }	
			}
			List actionList=gpfDtlsdao.getAllGpfDetails(empId);
			
			if(actionList!=null && actionList.size()>0){
            	empNameBf.append("<empNameMapping>");
            	empNameBf.append("<availability>").append("-1").append("</availability>");
            	empNameBf.append("<empType>").append(empType).append("</empType>");
            	empNameBf.append("</empNameMapping>");   
			}else{
	            	empNameBf.append("<empNameMapping>");
	            	empNameBf.append("<availability>").append("1").append("</availability>");
	            	empNameBf.append("<empType>").append(empType).append("</empType>");
	            	empNameBf.append("</empNameMapping>");   
				}
			Map map = new HashMap();
            String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();
	         
            logger.info(" the string buffer is :"+empNameMapping);
            map.put("ajaxKey", empNameMapping);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(map);
			resultObject.setViewName("ajaxData");
			logger.info(" SERVICE COMPLETE :");
		   }
			else if((chk!=null && chk.equals("2")) || (chk!=null && chk.equals("3")))
		    {
			String gpfAcctNo =voToService.get("gpfAcctNo")!=null? voToService.get("gpfAcctNo").toString():"";
			String userID =voToService.get("userID")!=null? voToService.get("userID").toString():"";
			StringBuffer empNameBf=new StringBuffer();
			long userGpfId=0;			
			if(userID!=null && !userID.equals(""))
				userGpfId=Long.parseLong(userID);		
			List actionList=gpfDtlsdao.chkUniqueGPFAcct(gpfAcctNo ,chk,userGpfId);		
			
			
			if(actionList!=null && actionList.size()>0){
            	empNameBf.append("<gpfAccNo>");
            	empNameBf.append("<availability>").append("-1").append("</availability>");
            	empNameBf.append("</gpfAccNo>");   
			}else{
	            	empNameBf.append("<gpfAccNo>");
	            	empNameBf.append("<availability>").append("1").append("</availability>");
	            	empNameBf.append("</gpfAccNo>");   
				}
			Map map = new HashMap();
            String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();
	         
            logger.info(" the string buffer is :"+empNameMapping);
            map.put("ajaxKey", empNameMapping);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(map);
			resultObject.setViewName("ajaxData");
			logger.info(" SERVICE COMPLETE :");
		}
			
			else
			{
			String editFlag = (String)voToService.get("edit");
			long empId = 0;
			empId=(voToService.get("Employee_ID_EmpLoanSearch")!=null&&!voToService.get("Employee_ID_EmpLoanSearch").equals(""))?Long.parseLong((String)voToService.get("Employee_ID_EmpLoanSearch")):0;
			if(editFlag != null && editFlag.equals("Y"))
            {            	
				String userId=(String)voToService.get("userID");
				long userID=Long.parseLong(userId);
				List <HrPayGpfBalanceDtls> actionList = gpfDtlsdao.getAllGpfDetailsbyUserId(userID);            	            	
				Iterator itr = actionList.iterator();  
            	GpfAcctDtlsVO gpfAcctDtlsVO=new GpfAcctDtlsVO();
                if (itr.hasNext())
                {
                	Object[] rowList = (Object[]) itr.next();
		            //userId
                	gpfAcctDtlsVO.setUserId(Long.parseLong((rowList[1]!=null?rowList[1].toString():"").toString()));
		            //GpfAcctNo
                	gpfAcctDtlsVO.setGpfAcctNo((rowList[0]!=null?rowList[0]:"").toString());
		            //Name
                	gpfAcctDtlsVO.setEmpName((rowList[2]!=null?rowList[2]:"").toString());
                	//added by Ankit Bhatt
                	OrgGradeMst orgGradeMst = new OrgGradeMst();
        			OrgGradeMstDaoImpl OrgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
                	long gradeId =0;
                	if(rowList[3]!=null && rowList[3]!="")
                	{
                		gradeId = Long.parseLong(rowList[3].toString());
                		orgGradeMst = OrgGradeMstDaoImpl.read(gradeId);
                	 gpfAcctDtlsVO.setOrgGradeMst(orgGradeMst);
                	}
                	//ended
                }				
            	Map map = new HashMap();
            	
            	//added by Ankit Bhatt
            	map.put("gradeList",gradeList);
            	 //ended
        	    map.put("actionList", gpfAcctDtlsVO);            	
            	
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(map);
		        resultObject.setViewName("GpfAcctScreenUpdate");
            	
            }
            else
            {
            	//Map loginDetailsMap=(Map)objServiceArgs.get("baseLoginMap");
            	
    			String userIdStr = (String)voToService.get("USER_ID_EmpLoanSearch");
    			
    			long userId = 0;
    			if(userIdStr!=null&&!userIdStr.equals(""))
    				userId=Long.parseLong(userIdStr);
    			
                /*long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
    			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
    			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/    			
    			//location sepecific
    			logger.info("The user id is----->>>>"+userIdStr);
    			List actionList=gpfDtlsdao.getAllGpfDetailsbyUserId(userId,locationId,langId);
    			List gpfDtlsList=new ArrayList();
    			Iterator itr = actionList.iterator(); 
    			GpfAcctDtlsVO gpfAcctDtlsVO=new GpfAcctDtlsVO();
    			OrgGradeMst orgGradeMst = new OrgGradeMst();
                while (itr.hasNext())
                {
                	gpfAcctDtlsVO=new GpfAcctDtlsVO();
                	Object[] rowList = (Object[]) itr.next();
		            //userId
                	gpfAcctDtlsVO.setUserId(Long.parseLong((rowList[1]!=null?rowList[1].toString():"").toString()));
		            //GpfAcctNo
                	gpfAcctDtlsVO.setGpfAcctNo((rowList[0]!=null?rowList[0]:"").toString());
		            //Name
                	gpfAcctDtlsVO.setEmpName((rowList[2]!=null?rowList[2]:"").toString());
                	
//                	added by Ankit Bhatt
                	orgGradeMst = new OrgGradeMst();
        			OrgGradeMstDaoImpl OrgGradeMstDaoImpl = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
                	long gradeId =0;
                	if(rowList[3]!=null && rowList[3]!="")
                	{
                		gradeId = Long.parseLong(rowList[3].toString());
                		orgGradeMst = OrgGradeMstDaoImpl.read(gradeId);
                	 gpfAcctDtlsVO.setOrgGradeMst(orgGradeMst);
                	}
                	//ended
                	gpfDtlsList.add(gpfAcctDtlsVO);
		            
                }
            	
                Map map = new HashMap();
//              added by Ankit Bhatt
            	map.put("gradeList",gradeList);
            	 //ended
                map.put("actionList", gpfDtlsList);
                resultObject.setResultCode(ErrorConstants.SUCCESS);
                resultObject.setResultValue(map);
                resultObject.setViewName("GpfAcctScreenView");	   
                
            }   
            }
		}
		catch(ClassCastException c)
		{
			logger.info(" the Exception is :"+c);
		}
		catch(Exception e)
		{		
			logger.info(" the Exception is :"+e);
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");
			
		}
	
		return resultObject;
	}
	

}
