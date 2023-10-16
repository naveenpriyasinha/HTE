package com.tcs.sgv.eis.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpExemptDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.ExemptTypeMstDAOImpl;
import com.tcs.sgv.eis.util.EmpExemptDtlsServiceImplHelper;
import com.tcs.sgv.eis.util.EmpInvestmentDtlsServiceImplHelper;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrItExemptEmpDtls;
import com.tcs.sgv.eis.valueobject.HrItExemptTypeMst;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmpExemptDtlsServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg=0;	
	public ResultObject getEmpExemptData(Map objServiceArgs)
	{
		logger.info("---------------inside getEmpInvestmentData-------------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{			
            ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");                  
            EmpExemptDtlsDAOImpl empExemptDtlsDAOImpl = new EmpExemptDtlsDAOImpl(HrItExemptEmpDtls.class,serv.getSessionFactory());
            HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
            ExemptTypeMstDAOImpl exemptTypeMstDAOImpl = new ExemptTypeMstDAOImpl(HrItExemptTypeMst.class,serv.getSessionFactory()); 
            HttpSession session=request.getSession();		
    		Map loginDetails=(Map)objServiceArgs.get("baseLoginMap");
    		long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
            CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
            cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
    		Map voToService = (Map)objServiceArgs.get("voToServiceMap");
			String editFlag = voToService.get("edit")!=null?(String)voToService.get("edit"):"";
			logger.info("Edit Flag is:-"+editFlag);
			long empExemptDtlsId=0;
			HrItExemptEmpDtls hrItExemptEmpDtls = new HrItExemptEmpDtls();
			long locId=StringUtility.convertToLong(loginDetails.get("locationId").toString());
            if(editFlag != null && editFlag.equals("Y")) {
            	String strEmpExemptDtlsId = voToService.get("empExemptDtlsId").toString();
            	if(strEmpExemptDtlsId!=null && !strEmpExemptDtlsId.equals("")){
            		empExemptDtlsId=Long.parseLong(strEmpExemptDtlsId);
            		logger.info("The itExemptEmpDtlsId inside update mode is--------->"+empExemptDtlsId);
	        	}
            	hrItExemptEmpDtls = empExemptDtlsDAOImpl.read(empExemptDtlsId);
            	logger.info("To avoid Lazy Initialization Exception---->"+hrItExemptEmpDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+hrItExemptEmpDtls.getHrItExemptTypeMst().getItexemptName());       	
            	objServiceArgs.put("hrItExemptEmpDtls", hrItExemptEmpDtls);         	
            	
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objServiceArgs);
		        resultObject.setViewName("editEmpExemptionDtls");            	
            }
            else if(editFlag != null && editFlag.equals("N")) {
            	 //lstExemptTypes = new ArrayList();
            	 List lstExemptTypes  = exemptTypeMstDAOImpl.getAllExemptTypeData(cmnLanguageMst);
            	logger.info("The List of Exemption is:-"+lstExemptTypes.size());
            	objServiceArgs.put("lstExemptTypes", lstExemptTypes); 
            	resultObject.setResultCode(ErrorConstants.SUCCESS);
                resultObject.setResultValue(objServiceArgs);
                resultObject.setViewName("addEmpExemptionDtls");
            }
            else {
            	List <HrItExemptEmpDtls> lstEmpExemptDtls = empExemptDtlsDAOImpl.getAllEmpExemptionData(locId);
            	String tempBuffer="";
            	logger.info("list size of empInvestList in getEmpInvestmentData is------------>"+lstEmpExemptDtls.size());
            	for(int i=0;i<lstEmpExemptDtls.size();i++){
            		hrItExemptEmpDtls = lstEmpExemptDtls.get(i);
            		
            		if(hrItExemptEmpDtls.getAmount()!=0)
            		{
            		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
                    tempBuffer=currencyFormatter.format(hrItExemptEmpDtls.getAmount()).replace("$", "");
                    hrItExemptEmpDtls.setCurrencyamount(tempBuffer.replace(".00", ""));
            		}
            		
            		
            		logger.info("To avoid Lazy Initialization Exception---->"+hrItExemptEmpDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+hrItExemptEmpDtls.getHrItExemptTypeMst().getItexemptName());
            	}
            	objServiceArgs.put("lstEmpExemptDtls", lstEmpExemptDtls);
            	resultObject.setResultCode(ErrorConstants.SUCCESS);
                resultObject.setResultValue(objServiceArgs);
                resultObject.setViewName("empExemptionView");
            }                 
		}
		catch(Exception e){			
			 logger.info("Exception Ocuures...getEmpInvestmentData");
			 logger.error("Error is: "+ e.getMessage());
			 objServiceArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objServiceArgs);
			 resultObject.setViewName("errorInsert");
		}	
		return resultObject;
	}
	
	public ResultObject insertUpdateEmpExemptData(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		int counter;
		try{
			long languageId;
			Date sysDate = new Date();
			String editMode=objectArgs.get("editMode").toString();
			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);			
	       
	        long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			CmnLanguageMst gujCmnLanguageMst=cmnLanguageMstDaoImpl.read(1L);
			CmnLanguageMst engCmnLanguageMst=cmnLanguageMstDaoImpl.read(2L);
			
			long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			HrItExemptEmpDtls hrItExemptEmpDtls = null;			
			logger.info("Coming into the insert Service method insertUpdateExemptionDetail");
		    ExemptTypeMstDAOImpl exemptTypeMstDAOImpl = new ExemptTypeMstDAOImpl(HrItExemptTypeMst.class,serv.getSessionFactory());
			EmpExemptDtlsDAOImpl empExemptDtlsDAOImpl = new EmpExemptDtlsDAOImpl(HrItExemptEmpDtls.class,serv.getSessionFactory());
			HrItExemptTypeMst hrItExemptTypeMst = null;
			HrItExemptEmpDtls objHrItExemptEmpDtls = new HrItExemptEmpDtls();	
			
			
			List lstExemptType = (List)objectArgs.get("lstExemptType");
			
			//logger.info("countercountercountercounter" + counter);
			
			List lstHrItExemptEmpDtls = (List)objectArgs.get("lstHrExemptEmpDtls");
			List lstEmpId = (List)objectArgs.get("lstEmpId");
			hrItExemptEmpDtls = (HrItExemptEmpDtls)objectArgs.get("exemptEmpDtls");
			
			EmpExemptDtlsServiceImplHelper helper = new EmpExemptDtlsServiceImplHelper(serv);
			
			if(editMode.equalsIgnoreCase("Y")) 
			{
				long empExemptDtlsId = Long.parseLong(objectArgs.get("empExemptDtlsId").toString()); 
				helper.updateEmpExemptionDtls(postId, userId, empExemptDtlsId, hrItExemptEmpDtls);
				
				msg=1;
			}
			else 
			{				
				counter = Integer.parseInt(objectArgs.get("counter").toString());
				helper.insertEmpExemptionDtls(lstEmpId, postId, dbId, counter, lstHrItExemptEmpDtls, userId, langId, locId, lstExemptType);		
				
			}
			
			logger.info("The Message Code:-"+msg);			
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			resultObject.setResultCode(ErrorConstants.SUCCESS);			
			resultObject.setResultValue(objectArgs);			
			if(msg==1)
				resultObject.setViewName("editEmpExemptionDtls");
			else
				resultObject.setViewName("addEmpExemptionDtls");			
			logger.info("INSERTED SUCCESSFULLY");
			logger.info("The insert operation is successfully completed");
		}
	     catch(ConstraintViolationException ex)  {
	    	 logger.info("TransactionSystemException occurs...");
	     }
		catch(Exception e){			
			logger.info("There is some error at editting or inserting time");			
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;		
	}

	
	

}