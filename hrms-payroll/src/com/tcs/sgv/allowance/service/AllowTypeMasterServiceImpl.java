/**
 * This is the AllowTypeMasterServiceImpl class which implements the AllowTypeMasterService interface which contains the
 * service for Allowance Type Master.
 * Made By:- Urvin shah
 * Date:- 14/07/2007
 */

package com.tcs.sgv.allowance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.allowance.dao.AllowTypeMasterDAO;
import com.tcs.sgv.allowance.dao.AllowTypeMasterDAOImpl;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class AllowTypeMasterServiceImpl extends ServiceImpl implements AllowTypeMasterService{
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	/**
	 * This method will collect all Data of hr_pay_allow_Type_mst and prepared a list and return
	 * to the AllowTypeMasterView.jsp
	 */
	
	public ResultObject getAllowTypeMasterDtls(Map objectArgs){
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);	
		
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			AllowTypeMasterDAO allowTypeMasterDAO = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());            
            List <HrPayAllowTypeMst> actionList = allowTypeMasterDAO.getAllowTypeMasterData(langId);            
            logger.info("The Size of List is:-"+actionList.size());            
            objectArgs.put("actionList", actionList);
            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);
            resultObject.setViewName("allowTypeMasterView");			
		}
		catch(Exception e){
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			e.printStackTrace();
		}
		return resultObject;
	}
	
	/**
	 * This method will check the Availability of Newer Allowance Name.
	 */
	
	public ResultObject checkAllowAvailability(Map objectArgs){
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
        StringBuffer allowNameBf=new StringBuffer();
        String check; 
		long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			AllowTypeMasterDAO allowTypeMasterDAO = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());
            String newAllowName = voToService.get("newAllowName").toString();  
            //System.out.println("Before The Changed AllowName is:-"+newAllowName);
            newAllowName=newAllowName.toLowerCase();
            //System.out.println("After The Changed AllowName is:-"+newAllowName);
            List <HrPayAllowTypeMst> actionList = allowTypeMasterDAO.checkAllowNameAvailability(newAllowName,langId);
            
            logger.info("The Size of List is:-"+actionList.size());
            
            if(actionList.size()==0){
            	check="false";
            	allowNameBf.append("<allowNameMapping>");
            	allowNameBf.append("<availability>").append(check).append("</availability>");
            	allowNameBf.append("</allowNameMapping>");            	
            }
            else {
            	check="true";
            	allowNameBf.append("<allowNameMapping>");
            	allowNameBf.append("<availability>").append(check).append("</availability>");
            	allowNameBf.append("</allowNameMapping>");
            }           
            String allowNameMapping = new AjaxXmlBuilder().addItem("ajax_key", allowNameBf.toString()).toString();
	         
            logger.info(" the string buffer is :"+allowNameMapping);
            objectArgs.put("ajaxKey", allowNameMapping);
	        resultObject.setResultCode(ErrorConstants.SUCCESS); 
	        resultObject.setResultValue(objectArgs);
	        resultObject.setViewName("ajaxData");
		}
		catch(Exception e){
			logger.error("Exception while generating VO to XML ", e);
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			retObj.setViewName("ajaxError");
			objectArgs.put("ajaxKey", "ERROR");
			retObj.setResultValue(objectArgs);
			retObj.setThrowable(e);
			return retObj;

		}
		return resultObject;
	}
	
	/**
	 * This method will collect all Data from the AllowTypeMasterVOGen and insert/update the hr_pay_allow_Type_mst.	 * 
	 */
	
	
	public ResultObject insertAllowTypeMasterDtls(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");				
		try{		
			String editMode=objectArgs.get("editMode").toString();
			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        //System.out.println("Loc Id is:-->" + dbId + " " + locId);
	       
	        long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			       
			HrPayAllowTypeMst allowTypeMst = (HrPayAllowTypeMst)objectArgs.get("allowTypeMst");
			HrPayAllowTypeMst hrPayAllowTypeMst = null;
			logger.info("Coming into the insert Service method insertGradeDataDetail");
			
			AllowTypeMasterDAOImpl allowTypeMasterDAO = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());
			
			logger.info("The Value of editMode is:-"+editMode);
			
			if(editMode.equalsIgnoreCase("Y")) {							
				logger.info(" Update Allowance Type Master inside service----------------------");				
				hrPayAllowTypeMst = allowTypeMasterDAO.read(allowTypeMst.getAllowCode());
				hrPayAllowTypeMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrPayAllowTypeMst.setOrgUserMstByUpdatedBy(orgUserMst);
				hrPayAllowTypeMst.setAllowName(allowTypeMst.getAllowName());
				hrPayAllowTypeMst.setAllowDesc(allowTypeMst.getAllowDesc());
				hrPayAllowTypeMst.setAllowDisplayName(allowTypeMst.getAllowDisplayName());
				allowTypeMasterDAO.update(hrPayAllowTypeMst);
				msg=1;
			}
			else {				
				logger.info(" insert Allowance Master inside else service----------------------");									
				IdGenerator idGen = new IdGenerator();
				Long id= idGen.PKGeneratorWODBLOC("hr_pay_allow_type_mst",objectArgs);				
				logger.info("****************************the id generated is "+id);			
				allowTypeMst.setAllowCode(id);				
				allowTypeMst.setCmnDatabaseMst(cmnDatabaseMst);
				allowTypeMst.setCmnLanguageMst(cmnLanguageMst);
				allowTypeMst.setCmnLocationMst(cmnLocationMst);
				allowTypeMst.setOrgPostMstByCreatedByPost(orgPostMst);
				allowTypeMst.setOrgUserMstByCreatedBy(orgUserMst);				
				logger.info("inside trial else service----------------------");						
				allowTypeMasterDAO.create(allowTypeMst);
			}	
			
			//change by Ankit Bhatt for "redirectMap"
			/*AllowTypeMasterDAOImpl allowTypeDAO = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class, serv.getSessionFactory());
			List <HrPayAllowTypeMst> actionList = allowTypeDAO.getAllowTypeMasterData(langId);			
			objectArgs.put("actionList",actionList);
			logger.info(" insertGradeDataDetail Coming into the Service method" + actionList);		
			System.out.println("size of  list is "+actionList.size());		
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("allowTypeMasterView");*/
			
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getAllowTypeView");
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			objectArgs.put("redirectMap", redirectMap);			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			//resultObject.setViewName("redirect view");
			if(msg==1)
				resultObject.setViewName("allowTypeEditMaster");
			else
				resultObject.setViewName("allowTypeMaster");
			//ended by Ankit Bhatt.
			logger.info("INSERTED SUCCESSFULLY");
			//System.out.println("The insert operation is successfully completed");
		}
	     catch(ConstraintViolationException ex)  {
	    	 //System.out.println("TransactionSystemException occurs...");
	    	 logger.error(ex);
	     }
		catch(Exception e){			
			logger.info("There is some error at editting or inserting time");			
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			e.printStackTrace();
		}
		return resultObject;
	}
	
	/**
	 * This method will collect all Data of hr_pay_allow_Type_mst of a perticular allowance code and prepared a list and return
	 * to the AllowTypeEditMaster.jsp
	 */
	
	public ResultObject getAllowTypeDataByCode(Map objectArgs){
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
		logger.info("u r in Editing Mode");
		try{
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			long allowCode = Long.parseLong(voToService.get("allowCode").toString());
			
			AllowTypeMasterDAO allowTypeMasterDAO = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());            
            HrPayAllowTypeMst actionList = allowTypeMasterDAO.getAllowTypeMasterDataByCode(allowCode, langId);                      
            objectArgs.put("actionList", actionList);
            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);
            logger.info("u have fetched data for Editing Mode");
            resultObject.setViewName("allowTypeEditMaster");            
		}
		catch(Exception e){
			logger.info("There is some error at editting or inserting time");			
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			e.printStackTrace();
		}
		return resultObject;
	}
	
}
