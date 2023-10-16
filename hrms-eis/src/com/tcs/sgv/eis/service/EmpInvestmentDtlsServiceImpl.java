package com.tcs.sgv.eis.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpInvestmentDtlsDAOImpl;
import com.tcs.sgv.eis.dao.InvestmentTypeMstDAOImpl;
import com.tcs.sgv.eis.util.EmpInvestmentDtlsServiceImplHelper;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrInvestEmpDtls;
import com.tcs.sgv.eis.valueobject.HrInvestTypeMst;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmpInvestmentDtlsServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg=0;	
	public ResultObject getEmpInvestmentData(Map objServiceArgs)
	{
		logger.info("---------------inside getEmpInvestmentData-------------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{			
            ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
                  
            EmpInvestmentDtlsDAOImpl empInvestDAO = new EmpInvestmentDtlsDAOImpl(HrInvestEmpDtls.class,serv.getSessionFactory());
            HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
            
            InvestmentTypeMstDAOImpl investmentTypeMstDAOImpl = new InvestmentTypeMstDAOImpl(HrInvestTypeMst.class,serv.getSessionFactory());
            
            HttpSession session=request.getSession();		
    		Map loginDetails=(Map)objServiceArgs.get("baseLoginMap");
    		long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
            CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
            cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
    		Map voToService = (Map)objServiceArgs.get("voToServiceMap");
			String editFlag = voToService.get("edit")!=null?(String)voToService.get("edit"):"";
			String investDtlsId="";
			List lstInvestTypes = new ArrayList();
			long locId=StringUtility.convertToLong(loginDetails.get("locationId").toString());
            if(editFlag != null && editFlag.equals("Y"))
            {
            	if(voToService.get("investDtlsId")!=null && !voToService.get("investDtlsId").equals(""))
	        	{
            		investDtlsId=voToService.get("investDtlsId").toString();
            		logger.info("The sectid inside update mode of getSectionData is--------->"+investDtlsId);
	        	}            	 
            	HrInvestEmpDtls empInvestList = empInvestDAO.getEmpInvestDataByInvestId(investDtlsId);
            	logger.info("To avoid Lazy Initialization Exception---->"+empInvestList.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+empInvestList.getHrInvestTypeMst().getInvestName());       	
            	
            	
            	
            	
            	objServiceArgs.put("empInvestList", empInvestList);         	
            	
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objServiceArgs);
		        resultObject.setViewName("empInvestmentEdit");            	
            }
            else if(editFlag != null && editFlag.equals("N")) {
            	lstInvestTypes = investmentTypeMstDAOImpl.getInvestmentTypes(cmnLanguageMst);
            	objServiceArgs.put("lstInvestTypes", lstInvestTypes); 
            	resultObject.setResultCode(ErrorConstants.SUCCESS);
                resultObject.setResultValue(objServiceArgs);
                resultObject.setViewName("addEmpInvestmentDtls");
            }	
            else {
            	
            	List <HrInvestEmpDtls> empInvestList = empInvestDAO.getAllEmpInvestmentData(locId);
            	HrInvestEmpDtls hrInvestEmpDtls = new HrInvestEmpDtls();
            	logger.info("list size of empInvestList in getEmpInvestmentData is------------>"+empInvestList.size());
            	for(int i=0;i<empInvestList.size();i++){
            		hrInvestEmpDtls = empInvestList.get(i);
            		String tempBuffer="";
					if (hrInvestEmpDtls.getAmount()!=0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
	                    tempBuffer=currencyFormatter.format(hrInvestEmpDtls.getAmount()).replace("$", "");
	                    hrInvestEmpDtls.setCurrencyamount(tempBuffer.replace(".00", ""));
	                    logger.info("the value of the emploan is ::"+hrInvestEmpDtls.getCurrencyamount());
					}
            		logger.info("To avoid Lazy Initialization Exception---->"+hrInvestEmpDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+hrInvestEmpDtls.getHrInvestTypeMst().getInvestName());
            	}
            	            	
            	objServiceArgs.put("empInvestList", empInvestList);
            	resultObject.setResultCode(ErrorConstants.SUCCESS);
                resultObject.setResultValue(objServiceArgs);
                resultObject.setViewName("empInvestmentView");
            }     	   
                
               
           // }
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
	public ResultObject insertUpdateEmpInvestDtls(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		int counter;
		try{
			Date sysDate = new Date();
			String editMode=objectArgs.get("editMode").toString();
			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);			
	       
	        long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
		
			long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			EmpInvestmentDtlsServiceImplHelper helper = new EmpInvestmentDtlsServiceImplHelper(serv);
			       
			HrInvestEmpDtls hrInvestEmpDtls = null;			
			logger.info("Coming into the insert Service method insertInvestmentTypeDataDetail");
			InvestmentTypeMstDAOImpl investmentTypeMstDAOImpl = new InvestmentTypeMstDAOImpl(HrInvestTypeMst.class,serv.getSessionFactory());
			EmpInvestmentDtlsDAOImpl empInvestmentDtlsDAOImpl = new EmpInvestmentDtlsDAOImpl(HrInvestEmpDtls.class,serv.getSessionFactory());
			HrInvestTypeMst hrInvestTypeMst = null;		
			logger.info("The Value of editMode is:-"+editMode);
			if(editMode.equalsIgnoreCase("Y"))
			{
				long empInvestDtlsId = Long.parseLong(objectArgs.get("empInvestDtlsId").toString()); 
				logger.info("empInvestDtlsIdempInvestDtlsIdempInvestDtlsId" + empInvestDtlsId);
				hrInvestEmpDtls = (HrInvestEmpDtls)objectArgs.get("hrInvestEmpDtls");
				
				helper.updateEmpInvestmentDtls(hrInvestEmpDtls, empInvestDtlsId,  postId,  userId);
				msg=1;
			}
			else
				{				
				List lstInvestType = (List)objectArgs.get("lstInvestType");
				counter = Integer.parseInt(objectArgs.get("counter").toString());
				List lstHrInvestEmpDtls = (List)objectArgs.get("lstHrInvestEmpDtls");
				List lstEmpId = (List)objectArgs.get("lstEmpId");				
				helper.insertEmpInvestmentDtls(counter, postId, dbId, lstInvestType, lstEmpId, hrInvestEmpDtls, lstHrInvestEmpDtls, userId, langId, locId);
			}
			
			{
			String[] encInvestXMLStr = StringUtility.getParameterValues("encInvestXML", request);
            List listInvestVo = FileUtility.xmlFilesToVoByXStream(encInvestXMLStr);
            logger.info("size of the list " + listInvestVo.size());

            for (Iterator i = listInvestVo.iterator(); i.hasNext();)
            {
                logger.debug("getting objects one by one from list");
                Object RelationVO = i.next();
                HrInvestEmpDtls hrInvestEmpDtlsVO = (HrInvestEmpDtls) RelationVO;
                hrInvestEmpDtlsVO.setInvestDtlsId(IDGenerateDelegate.getNextId("hr_invest_emp_dtls", objectArgs));
                hrInvestEmpDtlsVO.setCmnDatabaseMst(cmnDatabaseMst);
                hrInvestEmpDtlsVO.setOrgUserMstByCreatedBy(orgUserMst);
                hrInvestEmpDtlsVO.setOrgPostMstByCreatedByPost(orgPostMst);	
                empInvestmentDtlsDAOImpl.create(hrInvestEmpDtlsVO);
            }
			
			}
			
			
			logger.info("The Message Code:-"+msg);
			List <HrInvestTypeMst> lstInvestTypeMst = investmentTypeMstDAOImpl.getInvestmentTypes(cmnLanguageMst);				
			logger.info("size of  list is "+lstInvestTypeMst.size());	
			objectArgs.put("lstInvestTypeMst", lstInvestTypeMst);
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			resultObject.setResultCode(ErrorConstants.SUCCESS);			
			resultObject.setResultValue(objectArgs);			
			if(msg==1)
				resultObject.setViewName("empInvestmentEdit");
			else
				resultObject.setViewName("addEmpInvestmentDtls");			
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
	
public ResultObject checkEmpAvailability(Map objectArgs){
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
        StringBuffer sbEmpId=new StringBuffer();
        String check; 
		long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
            long empId = Long.parseLong(voToService.get("empId").toString());
            OrgEmpMst orgEmpMst = empDAOImpl.read(empId); 
            List lstHrEisEmpMst = empInfoDAOImpl.getHrEmpFromOrgEmp(orgEmpMst);        
            
            if(lstHrEisEmpMst.size()==0){
            	check="false";
            	sbEmpId.append("<empIdMapping>");
            	sbEmpId.append("<availability>").append(check).append("</availability>");
            	sbEmpId.append("</empIdMapping>");            	
            }
            else {
            	check="true";
            	sbEmpId.append("<empIdMapping>");
            	sbEmpId.append("<availability>").append(check).append("</availability>");
            	sbEmpId.append("</empIdMapping>");
            }           
            String empIdMapping = new AjaxXmlBuilder().addItem("ajax_key", sbEmpId.toString()).toString();
	         
            logger.info(" the string buffer is :"+empIdMapping);
            objectArgs.put("ajaxKey", empIdMapping);
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


}
