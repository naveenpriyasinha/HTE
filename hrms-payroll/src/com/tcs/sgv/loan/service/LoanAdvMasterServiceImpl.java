package com.tcs.sgv.loan.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.loan.valueobject.HrEssLoanAdvMst;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.loan.dao.EssLoanAdvMstDAOImpl;
import com.tcs.sgv.loan.dao.LoanAdvMstDAOImpl;

public class LoanAdvMasterServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	public ResultObject insertLoanAdvMasterDtls(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		try{
			logger.info("Coming into the Service method");
			
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			
			HrLoanAdvMst loanMst = (HrLoanAdvMst)objectArgs.get("loanAdvMst");
			logger.info( "The BankMaster VO is " + loanMst);
		
			HrEssLoanAdvMst loanEssMst =(HrEssLoanAdvMst)objectArgs.get("EssLoanAdvMst");
			logger.info( "The BankMaster VO is " + loanEssMst);
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			LoanAdvMstDAOImpl loanMasterDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class,serv.getSessionFactory());
			EssLoanAdvMstDAOImpl essLoanMasterDAO = new EssLoanAdvMstDAOImpl(HrEssLoanAdvMst.class,serv.getSessionFactory());
           
			 long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			 OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			 OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			 
			 long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
		        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				
				long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);		       
		       	        			
				 long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
				 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	
			 
		        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			 
			 String editFromVO = objectArgs.get("edit").toString();
			 
			 CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			long displayGroup = Long.parseLong((objectArgs.get("displayGroup").toString()));
			
			//System.out.println("displayGroup in service is"+displayGroup);
		
			LoanAdvMstDAOImpl loanAdvMstDAOImpl = new LoanAdvMstDAOImpl(HrLoanAdvMst.class, serv.getSessionFactory());
			CmnLookupMst cmnLookupMstId= cmnLookupMstDAOImpl.read((displayGroup));

			//System.out.println("cmnLookupMstId_>>"+cmnLookupMstId);
			
			if(editFromVO!=null && editFromVO.equals("Y"))
			{
				logger.info("INSIDE UPDATE");					            
				HrLoanAdvMst loanMstVO =loanMasterDAO.read(loanMst.getLoanAdvId());
				loanMstVO.setOrgUserMstByUpdatedBy(orgUserMst);
				loanMstVO.setOrgPostMstByUpdatedByPost(orgPostMst);
				loanMstVO.setUpdatedDate(new Date());
				loanMstVO.setElementCode(loanMst.getElementCode());
				loanMstVO.setCmnLanguageMst(cmnLanguageMst);
				loanMstVO.setLoanAdvName(loanMst.getLoanAdvName());
				loanMstVO.setMaxNoInstallAmt(loanMst.getMaxNoInstallAmt());
				loanMstVO.setMaxNoInstInterest(loanMst.getMaxNoInstInterest());	
				loanMstVO.setDisplayGroup(cmnLookupMstId);
				loanMstVO.setMinNoInstInterest(loanMst.getMinNoInstInterest());	
				loanMasterDAO.update(loanMstVO);
				
				//added by khushal for HR_LOAN_ESS_ADV_MST
				HrEssLoanAdvMst essLoanMstVO =essLoanMasterDAO.read(loanEssMst.getLoanAdvId());
				essLoanMstVO.setOrgUserMstByUpdatedBy(orgUserMst);
				essLoanMstVO.setOrgPostMstByUpdatedByPost(orgPostMst);
				essLoanMstVO.setUpdatedDate(new Date());
				essLoanMstVO.setElementCode(loanEssMst.getElementCode());
				essLoanMstVO.setCmnLanguageMst(cmnLanguageMst);
				essLoanMstVO.setLoanAdvName(loanEssMst.getLoanAdvName());
				essLoanMstVO.setMaxNoInstallAmt(loanEssMst.getMaxNoInstallAmt());
				essLoanMstVO.setMaxNoInstInterest(loanEssMst.getMaxNoInstInterest());	
				essLoanMstVO.setDisplayGroup(cmnLookupMstId);
				essLoanMstVO.setMinNoInstInterest(loanEssMst.getMinNoInstInterest());	
				essLoanMasterDAO.update(essLoanMstVO);
				
				
				
				
				//ended by khushal
				
				objectArgs.put("msg", "Record Updated Successfully");
				msg=1;
			}
			else if(editFromVO!=null && editFromVO.equals("N"))
			{
				logger.info("INSIDE CREATE");
				IdGenerator idGen = new IdGenerator();
				long loanId = idGen.PKGeneratorWODBLOC("HR_LOAN_ADV_MST", objectArgs);
				
				loanMst.setLoanAdvId(loanId);
				logger.info("I am here=------"+ loanId);
				
				 
				 if(langId==1)
				 loanMst.setElementCode(loanId);
				 loanMst.setCmnLanguageMst(cmnLanguageMst);
				 loanMst.setCreatedDate(new Date());						
				 loanMst.setCmnDatabaseMst(cmnDatabaseMst);
				 loanMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				 loanMst.setOrgPostMstByCreatedByPost(orgPostMst);
				 loanMst.setCmnLocationMst(cmnLocationMst);		
				 loanMst.setDisplayGroup(cmnLookupMstId);
				 loanMst.setOrgUserMstByCreatedBy(orgUserMst);
				 loanMst.setUpdatedDate(new Date());		       
			     loanMasterDAO.create(loanMst);
			     
			     //added by khushal
			        IdGenerator idGen1 = new IdGenerator();
					long loanEssId = idGen.PKGeneratorWODBLOC("HR_ESS_LOAN_ADV_MST", objectArgs);
					
					loanEssMst.setLoanAdvId(loanEssId);
					logger.info("I am here=------"+ loanEssId);
					
					 
					 if(langId==1)
					 loanEssMst.setElementCode(loanEssId);
					 loanEssMst.setCmnLanguageMst(cmnLanguageMst);
					 loanEssMst.setCreatedDate(new Date());						
					 loanEssMst.setCmnDatabaseMst(cmnDatabaseMst);
					 loanEssMst.setOrgPostMstByUpdatedByPost(orgPostMst);
					 loanEssMst.setOrgPostMstByCreatedByPost(orgPostMst);
					 loanEssMst.setCmnLocationMst(cmnLocationMst);		
					 loanEssMst.setDisplayGroup(cmnLookupMstId);
					 loanEssMst.setOrgUserMstByCreatedBy(orgUserMst);
					 loanEssMst.setUpdatedDate(new Date());		       
					 essLoanMasterDAO.create(loanEssMst);
			     
			     
			     // ended by khushal
				objectArgs.put("msg", "Record Inserted Successfully");
			}
			else
			{
				throw new NullPointerException();
			}
			
			List <HrLoanAdvMst> actionList = loanMasterDAO.getAllLoanAdvMasterData(langId);
			//objectArgs.put("actionList", actionList);
			logger.info("Coming into the Service method" + actionList);
			
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			//resultObject.setViewName("loanMasterView");
			if(msg==1)
				resultObject.setViewName("loanEditList");
			else
				resultObject.setViewName("loanMaster");
			logger.info("INSERTED SUCCESSFULLY");
			
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...");
			 ne.printStackTrace();
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		catch(Exception e){
			logger.info("Null Pointer Exception Ocuures...");
			 e.printStackTrace();
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			e.printStackTrace();
		}
		return resultObject;
	}
	
	
	public ResultObject checkloanName(Map objectArgs)
	{
		
		logger.info("IN checkBankName Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Map voToService = (Map)objectArgs.get("voToServiceMap");
        try{
        	LoanAdvMstDAOImpl loanMasterDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class,serv.getSessionFactory());
        
        String strLoanName = (String)voToService.get("loanName");        
        String oldname = (String)voToService.get("oldname");
        
        if(oldname!=null && (!oldname.equals("")))
		{
			
        	oldname= oldname.trim().toLowerCase();
		}	
        StringBuffer propertyList = new StringBuffer();
        strLoanName = strLoanName.trim().toLowerCase();
        if(!strLoanName.equals(oldname))
        {
           List loanName = loanMasterDAO.checkLoanName(strLoanName);
         
        propertyList.append("<loan-name>");   	
        propertyList.append("<list-size>").append(loanName.size()).append("</list-size>");
        propertyList.append("</loan-name>"); 
        logger.info("Loan Name size " + loanName.size());
        }
        else
        {
        	propertyList.append("<loan-name>");   	
            propertyList.append("<list-size>").append(0).append("</list-size>");
            propertyList.append("</loan-name>");
        }
        
        Map result = new HashMap();
        String bankNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
        
        result.put("ajaxKey", bankNameIdStr);
           
        resultObject.setResultValue(result);
        resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
       logger.info("After Service Called.........\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultObject;
	}
	
		
	public ResultObject getLoanAdvMasterDtls(Map objectArgs)
	{
		logger.info("IN getLoanAdvMasterDtls");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);				
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
		try
		{
			
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            LoanAdvMstDAOImpl loanMasterDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class,serv.getSessionFactory());

	            List <HrLoanAdvMst> actionList = loanMasterDAO.getAllLoanAdvMasterData(langId);	        
	            Map map = new HashMap();
	            map.put("actionList", actionList);
	          
	            
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	            resultObject.setViewName("loanMasterView");
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
	
		return resultObject;
	}

	
	public ResultObject getLoanData(Map objectArgs)
	{
		logger.info("IN getLoanData Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		String editFlag = (String)voToService.get("edit");
		try
		{
			
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				
	            LoanAdvMstDAOImpl loanMasterDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class,serv.getSessionFactory());
				 if(editFlag!= null && editFlag.equals("Y"))
	            {
	            		logger.info("I m in edit mode ");
	            		long loanId = Long.parseLong((String)voToService.get("loanid").toString());
	            		HrLoanAdvMst actionList = (HrLoanAdvMst)loanMasterDAO.getLoanIdData(loanId);
	            	    
	            		logger.info("Loan Name is " + actionList.getLoanAdvName());
	            		logger.info("lookup id is" + actionList.getDisplayGroup().getLookupId());
	            		Map map = new HashMap();  
	            		
	            	   
				        map.put("actionList", actionList);
				       
				        resultObject.setResultCode(ErrorConstants.SUCCESS);
				        resultObject.setResultValue(map);
				        resultObject.setViewName("loanEditList");
	            }
	            else
	            {
			            List <HrLoanAdvMst> actionList = loanMasterDAO.getAllLoanAdvMasterData(langId);
			            Map map = new HashMap();
			            map.put("actionList", actionList);
			            resultObject.setResultCode(ErrorConstants.SUCCESS);
			            resultObject.setResultValue(map);
			            resultObject.setViewName("loanMasterView");
	            }
			            
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
	
		return resultObject;
	}						
}
