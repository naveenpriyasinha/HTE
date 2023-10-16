package com.tcs.sgv.eis.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.RltBillTypeEdp;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.SalRevMstDAO;
import com.tcs.sgv.eis.dao.SalRevMstDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class SalRevMasterServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog( getClass() );
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");
	int msg=0;
	public ResultObject getSalRevData(Map objectArgs)
	{
		logger.info("Inside getSalRevData Service");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
		    		
			   ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		       Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		       long revStatus=Long.parseLong(constantsBundle.getString("activate"));
		       logger.info("Activate Flag is:-->>"+revStatus);
		       long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

		      
			SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());//daoimpl object
		    List<HrPaySalRevMst> resultSet=new ArrayList();
		    	
	            		
		    			resultSet = salRevMstDAO.getAllSalRevMstData(locId);
	            		for(int i=0;i<resultSet.size();i++)
	    	        	{
	    	        		logger.info("the getRltBillTypeEdp is "+resultSet.get(i).getRltBillTypeEdp().getEdpShortName());
	    	        	}
	            		
	            		objectArgs.put("resultSet", resultSet);
				        resultObject.setResultCode(ErrorConstants.SUCCESS);
				        resultObject.setResultValue(objectArgs);//passing map to result value for get data in jsp
				        resultObject.setViewName("salRevMstView");
	        	        
			          
	       			            			
		}
		
		catch(Exception e)
		{	
			logger.info("Null Pointer Exception Ocuures...getSalRevData");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			
		}	
		return resultObject;
	}	
	
	public ResultObject addSalRevData(Map objectArgs)
	{
		logger.info("Inside addSalRevData");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try {
		   ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	       Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		   Map voToService = (Map)objectArgs.get("voToServiceMap");
		   String editFlag = (String)voToService.get("edit");//edit flag passing
		   logger.info("Flag Name "+editFlag );
			   
		   SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl (HrPaySalRevMst.class,serv.getSessionFactory());//daoimpl object
	       
	     // Start Added By Urvin shah for fetching the Location List In the Insert mode of Order Master.
		   if(editFlag != null && editFlag.equals("N")) {		    	 
			   List <RltBillTypeEdp> rltBillList = salRevMstDAO.getRltBillTypeEdpData();	
			   objectArgs.put("rltBillList", rltBillList);
			  
			   objectArgs.put("mandatory", "Y");
			   resultObject.setResultCode(ErrorConstants.SUCCESS);
	           resultObject.setResultValue(objectArgs);
			   resultObject.setViewName("addsalRevMst");//view Add Order Mode jsp
		   }
		   // Ended By Urvin Shah.
		}
		catch(PropertyValueException pe) {
			logger.info("Null Pointer Exception Ocuures...addSalRevData");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
		}
		catch(Exception e)
		{	
			logger.info("Null Pointer Exception Ocuures...addSalRevData");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
		}	
		return resultObject;
	}	
	
	
	
	public ResultObject insertSalRevMst(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside insertSalRevMstData*****************");
			HrPaySalRevMst hrPaySalRevMst = (HrPaySalRevMst)objectArgs.get("hrPaySalRevMst");
			logger.info( "The SalRevOrderName  is " + hrPaySalRevMst.getRevOrderNo());
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			ResultObject resultObj = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
			Map resultMap = (Map) resultObj.getResultValue();
			

			SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
			
			
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			
	        String editFromVO = objectArgs.get("edit").toString();
	        logger.info("editFromVO insertSalRevMstData" + editFromVO);
	        Date sysdate = new Date();
	        
	        
	        long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        
	       
	        langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			 long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			 
			long attachment_Id = (Long) resultMap.get("AttachmentId_salRevId"); 
			logger.info("after inserting attachment! attach_id : " + attachment_Id);		
	        
	        if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
			{
				logger.info("INSIDE CREATE insertSalRevMstData");
				IdGenerator idGen = new IdGenerator();
				long salRevId = idGen.PKGenerator("hr_pay_sal_rev_mst", objectArgs);
				hrPaySalRevMst.setSalRevId(salRevId);		
				hrPaySalRevMst.setRevAttachmentId(attachment_Id);
				hrPaySalRevMst.setCreatedDate(sysdate);
				hrPaySalRevMst.setOrgUserMstByCreatedBy(orgUserMst);
				hrPaySalRevMst.setOrgPostMstByCreatedByPost(orgPostMst);
				hrPaySalRevMst.setCmnLocationMst(cmnLocationMst);
				salRevMstDAO.create(hrPaySalRevMst);
				 
				}
	        
			
	        if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
						
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			if(msg==1)
				resultObject.setViewName("addsalRevMst");
			else
				resultObject.setViewName("addsalRevMst");
			logger.info("INSERTED SUCCESSFULLY insertSalRevMstData");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertSalRevMstData");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		
		catch(Exception e){
			
			logger.info("Exception Ocuures...insertMiscData");
			 logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		return resultObject;
	}
	
	public ResultObject deactivateOrder(Map objectArgs)
	{
		logger.info("*********deactivateOrder*********");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try{

			Map voToService = (Map)objectArgs.get("voToServiceMap");

			SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl (HrPaySalRevMst.class,serv.getSessionFactory());//daoimpl object
			
			long revStatusdeactive=Long.parseLong(constantsBundle.getString("deactivate"));
			
			logger.info("deactivate Flag is:-->>"+revStatusdeactive);

			long salrevId= Long.parseLong(voToService.get("salrevId").toString());
			logger.info("SalrRevId is "+salrevId);


			HrPaySalRevMst salrevList = salRevMstDAO.getSalRevMst(salrevId);

			salrevList.setRevStatus(revStatusdeactive);
			salRevMstDAO.update(salrevList);
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			List<HrPaySalRevMst> resultSet = salRevMstDAO.getAllSalRevMstData(locId);

			for(int i=0;i<resultSet.size();i++)
			{
				logger.info("the getRltBillTypeEdp is "+resultSet.get(i).getRltBillTypeEdp().getEdpShortName());
			}

			objectArgs.put("resultSet", resultSet);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);//passing map to result value for get data in jsp
			resultObject.setViewName("salRevMstView");

		}

		catch(Exception e)
		{	
			logger.info("Null Pointer Exception Ocuures...getSalRevData");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		

		}	
		return resultObject;

	}

	public ResultObject checkrevSalOrderAvailability(Map objectArgs){
	 	
		
		logger.info("Inside AJAX Service");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
        StringBuffer orderNameBf=new StringBuffer();
        String check; 
		//long langId=Long.parseLong(loginDetailsMap.get("langId").toString());//For Language Independent
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
            String newOrderName = voToService.get("newOrderName").toString();
            String ordDate=voToService.get("date").toString();
            
            long revStatus=Long.parseLong(constantsBundle.getString("activate"));
		       logger.info("Activate Flag is:-->>"+revStatus);
           
        	Date dtTmp = new SimpleDateFormat("dd.MM.yyyy").parse(ordDate.replace("/","."));
        	String strOutDt = new SimpleDateFormat("dd.MMM.yyyy").format(dtTmp);
        	
            
            logger.info("The parsed date is---------->>>>>>>>>"+dtTmp);
            logger.info("Before The Changed OrderName is:-"+newOrderName);
            newOrderName=newOrderName.toLowerCase();
            logger.info("After The Changed OrderName is:-"+newOrderName);
            List <HrPaySalRevMst> actionList = salRevMstDAO.getOrderDataFromPara(newOrderName,strOutDt,revStatus);
            logger.info("The Size of List is:-"+actionList.size());
            
            if(actionList.size()==0){
            	check="false";
            	orderNameBf.append("<orderNameMapping>");
            	orderNameBf.append("<availability>").append(check).append("</availability>");
            	orderNameBf.append("</orderNameMapping>");            	
            }
            else {
            	check="true";
            	orderNameBf.append("<orderNameMapping>");
            	orderNameBf.append("<availability>").append(check).append("</availability>");
            	orderNameBf.append("</orderNameMapping>");
            }           
            String orderNameMapping = new AjaxXmlBuilder().addItem("ajax_key", orderNameBf.toString()).toString();
	         
            logger.info(" the string buffer is :"+orderNameMapping);
            objectArgs.put("ajaxKey", orderNameMapping);
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
	
	public ResultObject updateSalRevMst(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside updateSalRevMst*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
								
				HrPaySalRevMst hrPaySalRevMst=(HrPaySalRevMst)objServiceArgs.get("hrPaySalRevMst");				
				SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
				HrPaySalRevMst salRevMstVO = new HrPaySalRevMst();
								
				long salRevId = Long.parseLong(objServiceArgs.get("salRevId").toString());
				logger.info("salRevId::"+salRevId);
				salRevMstVO = salRevMstDAO.read(salRevId);
				salRevMstVO.setRevOrderNo(hrPaySalRevMst.getRevOrderNo());
				salRevMstVO.setRevOrderDate(hrPaySalRevMst.getRevOrderDate());
				salRevMstVO.setRevEffcFrmDate(hrPaySalRevMst.getRevEffcFrmDate());
				salRevMstVO.setRevEffcToDate(hrPaySalRevMst.getRevEffcToDate());
				salRevMstVO.setRevFreqMthPaid(hrPaySalRevMst.getRevFreqMthPaid());
				salRevMstVO.setRevInstallments(hrPaySalRevMst.getRevInstallments());
				salRevMstVO.setRltBillTypeEdp(hrPaySalRevMst.getRltBillTypeEdp());
				salRevMstVO.setRevPayOutDate(hrPaySalRevMst.getRevPayOutDate());
				salRevMstVO.setRevStatus(hrPaySalRevMst.getRevStatus());
				salRevMstVO.setRevReason(hrPaySalRevMst.getRevReason());
				salRevMstVO.setCashPercentage(hrPaySalRevMst.getCashPercentage());
				salRevMstVO.setOrgPostMstByUpdatedByPost(hrPaySalRevMst.getOrgPostMstByUpdatedByPost());
				salRevMstVO.setOrgUserMstByUpdatedBy(hrPaySalRevMst.getOrgUserMstByUpdatedBy());				
				salRevMstVO.setUpdatedDate(hrPaySalRevMst.getUpdatedDate());
				salRevMstVO.setPblIndependentFlg(hrPaySalRevMst.getPblIndependentFlg());
				salRevMstDAO.update(salRevMstVO);
				objServiceArgs.put("MESSAGECODE",300006);				
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("salRevMstView");    
				
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in updateSalRevMst service******");
			logger.error("Error In updateSalRevMst service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}
		logger.info("updateSalRevMst ended***********************");
		return resultObject;
	}
	public ResultObject editSalRevData(Map objectArgs)
	{
		logger.info("Inside editSalRevData Service");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{		    		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
			
					
			HrPaySalRevMst salRevMstVO = new HrPaySalRevMst();

			Map voToService = (Map)objectArgs.get("voToServiceMap");			
			long lSalRevId=0;		    	
			if(voToService != null && voToService.get("lSalRevId")!=null)					
				lSalRevId=Long.parseLong(voToService.get("lSalRevId").toString());
			
			salRevMstVO = salRevMstDAO.getSalRevMst(lSalRevId);
			logger.info("salRevMstVO.getRevAttachmentId():::"+salRevMstVO.getRevAttachmentId());
			CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl
			(CmnAttachmentMst.class,serv.getSessionFactory());
							
			CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(salRevMstVO.getRevAttachmentId());
							
			objectArgs.put("attachmentsalRevId",cmnAttachmentMst);

			
			List <RltBillTypeEdp> rltBillList = salRevMstDAO.getRltBillTypeEdpData();	
			objectArgs.put("rltBillList", rltBillList);
			objectArgs.put("salRevId", lSalRevId);
			objectArgs.put("salRevMstVO", salRevMstVO);
			objectArgs.put("edit", "Yes");
			objectArgs.put("mandatory", "N");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);//passing map to result value for get data in jsp
			resultObject.setViewName("addsalRevMst");

		}
		
		catch(Exception e)
		{	
			logger.info("Null Pointer Exception Ocuures...editSalRevData");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
			
		}	
		return resultObject;
	}	
	
}
