package com.tcs.sgv.eis.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.AllowTypeMasterDAOImpl;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
//import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.BulkAllowanceDAOImpl;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDao;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;


public class BulkAllowances extends ServiceImpl {
	
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject genereteDataForBulkAllowances(Map objectArgs)
	{
	
		logger.info("inside the method::::::::::::genereteDataForBulkAllowances");
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			
			
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			//List bulkList=new ArrayList();
			List dsgnList= new ArrayList();
			/*List amtList=new ArrayList();
			long size=0;
			long empId=0;
			long compoId=0;
			double amt=0;
			boolean flag=false;
			
			List empList=new ArrayList();
			long dsgn=0;
			long compoType=0;
			
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			if(voToService != null)
			{
				 dsgn = voToService.get("dsgn") != null ?Long.parseLong(voToService.get("dsgn").toString()):0;
				 compoType=voToService.get("compoType") != null ?Long.parseLong(voToService.get("compoType").toString()):0;
				 compoId=voToService.get("compoId") != null?Long.parseLong(voToService.get("compoId").toString()):0;
			}
			logger.info("compoType is "+compoType);
			logger.info("Designation Id is "+dsgn);
			logger.info("compoId Id is "+compoId);
		*/
			
			BulkAllowanceDAOImpl bulkAllowanceDAOImpl =new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			dsgnList=bulkAllowanceDAOImpl.getDesignationList(langId,locId);
			
			logger.info("Designation List size is"+dsgnList.size());
			
			/*
			if(dsgn != 0)
			{
				empList=bulkAllowanceDAOImpl.getEmployeeListFromDsgn(dsgn,1);
				size=empList.size();
			}
			
			logger.info("size is ::::::::::::"+size);
			if(empList != null && empList.size()>0)
			{
				for(int i=0;i<size;i++)
				{
				flag=false;
				CustomBulkAllowances customBulkAllowances =new CustomBulkAllowances();
				Object[] emp=(Object[])empList.get(i);
				empId=Long.valueOf(emp[0].toString());
				customBulkAllowances.setEmpId(empId);
				customBulkAllowances.setEmployeeName(emp[1].toString());
				if(compoType==2500134)
				{
					amtList=bulkAllowanceDAOImpl.getAllw(empId,compoId);
					
					if(amtList!= null && amtList.size()>0)
					{
						flag=true;
						HrPayEmpallowMpg amtLst=(HrPayEmpallowMpg)amtList.get(0);
						amt=amtLst.getEmpAllowAmount();
						logger.info("allowance amt is "+amt);
						customBulkAllowances.setEdit("Y");
						customBulkAllowances.setExistingAmount(amt);
						customBulkAllowances.setNewAmount(amt+100);
						customBulkAllowances.setDifference(100);
					}
				
				}
				else
				{
					amtList=bulkAllowanceDAOImpl.getDeduc(empId,compoId);
					
					if(amtList!= null && amtList.size()>0)
					{
						flag=true;
						HrPayDeductionDtls amtLst=(HrPayDeductionDtls)amtList.get(0);
						amt=amtLst.getEmpDeducAmount();
						logger.info("deduction amt is "+amt);
						customBulkAllowances.setEdit("Y");
						customBulkAllowances.setExistingAmount(amt);
						customBulkAllowances.setNewAmount(amt+100);
						customBulkAllowances.setDifference(100);
					}
				
				}
				if(flag==false)
				{
				//logger.info("inside insert");
					customBulkAllowances.setExistingAmount(0);
					customBulkAllowances.setNewAmount(10);
					customBulkAllowances.setDifference(10);
					customBulkAllowances.setEdit("N");
					bulkList.add(customBulkAllowances);
				}
				else
				{
					//logger.info("inside update");
					bulkList.add(customBulkAllowances);
				}
				
				
				}
				
			}

			
			*/
			//objectArgs.put("bulkList",bulkList);
			objectArgs.put("dsgnList",dsgnList);
			//objectArgs.put("type",compoType);
			
			//Added by abhilash
			
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			
			//ended by abhilash
			
			//objectArgs.put("size",size);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			logger.info("By By to genereteDataForBulkAllowances");
			resultObject.setViewName("BulkAllowances");
		}
		catch(Exception e)
		{
		logger.error("Error is: "+ e.getMessage());	
		}
		return resultObject;
		
	}
	
	
	public ResultObject getDataForBulkAllowances(Map objectArgs) throws Exception
	{
	
		logger.info("inside the method:::::::::::: getDataForBulkAllowances");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
		
		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
		long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
		long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			List bulkList=new ArrayList();
			List dsgnList= new ArrayList();
			List amtList=new ArrayList();
			List payItemList=new ArrayList();
			long size=0;
			long empId=0;
			long compoId=0;
			double amt=0;
			boolean flag=false;
			
			List empList=new ArrayList();
			long dsgn=0;
			long compoType=0;
			long billGroupid=0;
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			if(voToService != null)
			{
				 dsgn = voToService.get("dsgn") != null ?Long.parseLong(voToService.get("dsgn").toString()):0;
				 compoType=voToService.get("compoType") != null ?Long.parseLong(voToService.get("compoType").toString()):0;
				 compoId=voToService.get("compoId") != null?Long.parseLong(voToService.get("compoId").toString()):0;
				 billGroupid=voToService.get("billGroupid") != null?Long.parseLong(voToService.get("billGroupid").toString()):0;
			}
			logger.info("compoType is "+compoType);
			//logger.info("Designation Id is "+dsgn);
			//logger.info("compoId Id is "+compoId);
		
			
			BulkAllowanceDAOImpl bulkAllowanceDAOImpl =new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			dsgnList=bulkAllowanceDAOImpl.getDesignationList(langId,locId);
			logger.info("dsgn IS:::"+dsgn);
			if(dsgn != 0)
			{
				empList=bulkAllowanceDAOImpl.getEmployeeListFromDsgn(dsgn,langId,locId,compoType,compoId,billGroupid);
				size=empList.size();
			}
			
			//size=size;
			logger.info("size of Emp List is ::::::::::::"+size);
			if(empList != null && empList.size()>0)
			{
				for(int i=0;i<size;i++)
				{
				//flag=false;
				CustomBulkAllowances customBulkAllowances =new CustomBulkAllowances();
				Object[] emp=(Object[])empList.get(i);
				empId=Long.valueOf(emp[0].toString());
				customBulkAllowances.setEmpId(empId);
				customBulkAllowances.setEmployeeName(emp[1].toString());
				customBulkAllowances.setHrEisEmpId(Long.parseLong(emp[2].toString()));
				
				if(emp[4]!=null)
				{
					amt = Double.parseDouble(emp[4].toString());
					customBulkAllowances.setEdit("Y");
					customBulkAllowances.setExistingAmount(amt);
					customBulkAllowances.setNewAmount(amt);
					customBulkAllowances.setDifference(amt);
					bulkList.add(customBulkAllowances);
				}
				else
				{
					customBulkAllowances.setExistingAmount(0);
					customBulkAllowances.setNewAmount(0);
					customBulkAllowances.setDifference(0);
					customBulkAllowances.setEdit("N");
					bulkList.add(customBulkAllowances);
				}
				
			/*	else
					duplicateCount=bulkAllowanceDAOImpl.checkAllowanceDuplicate(empID, compoId);
				if(duplicateCount==0)
					custom.setEdit("N");
				else
					custom.setEdit("Y");
				logger.info("inside insertBulkallowances edit flag is"+custom.getEdit());*/
				
				
				/*if(compoType==2500134)
				{	
					long duplicateCount=0;
					duplicateCount=bulkAllowanceDAOImpl.checkAllowanceDuplicate(empId, compoId);
					
					List mapedList=bulkAllowanceDAOImpl.getAllwMaped(empId, compoId, -1, -1);
					if(mapedList != null && mapedList.size()>0)
					{*/
					/*if(  amtList!= null && amtList.size()>0)
					{
					*/	
					
					/*if(amtList!= null && amtList.size()>0)
					{
						flag=true;
					*/
					/*if(duplicateCount != 0)
					{
						amtList=bulkAllowanceDAOImpl.getAllw(empId,compoId,-1,-1);
						HrPayEmpallowMpg amtLst=(HrPayEmpallowMpg)amtList.get(0);
						amt=amtLst.getEmpAllowAmount();
						//logger.info("allowance amt is "+amt);



						customBulkAllowances.setEdit("Y");
					//	logger.info("edit flag is in service Y");
						customBulkAllowances.setExistingAmount(amt);
						customBulkAllowances.setNewAmount(amt);
						customBulkAllowances.setDifference(amt);
						bulkList.add(customBulkAllowances);
					
					}
					else 
					{
						customBulkAllowances.setExistingAmount(0);
						customBulkAllowances.setNewAmount(0);
						customBulkAllowances.setDifference(0);
						customBulkAllowances.setEdit("N");
						bulkList.add(customBulkAllowances);

					}
					}
					else
					{
						logger.info("Allowance Componenet is not mappede");
					}
					}*/	
					//bulkList.add(customBulkAllowances);
				
				
				/*else
				{
					long duplicateCount=0;
					duplicateCount=bulkAllowanceDAOImpl.checkDeductionDuplicate(empId,compoId);
					List mapedList=bulkAllowanceDAOImpl.getDeducMaped(empId, compoId, -1, -1);
					
					if(mapedList != null && mapedList.size()>0)
					{*/
					/*if(amtList!= null && amtList.size()>0)
					{*/
				/*	if(amtList!= null && amtList.size()>0)
					{
						flag=true;*/
					/*	if(duplicateCount != 0)
						{
							amtList=bulkAllowanceDAOImpl.getDeduc(empId,compoId,-1,-1);
							HrPayDeductionDtls amtLst=(HrPayDeductionDtls)amtList.get(0);
						amt=amtLst.getEmpDeducAmount();
						customBulkAllowances.setEdit("Y");
						customBulkAllowances.setExistingAmount(amt);
						customBulkAllowances.setNewAmount(amt);
						customBulkAllowances.setDifference(amt);
						bulkList.add(customBulkAllowances);
						//bulkList.add(customBulkAllowances);
					}
					
					else 
					{
						customBulkAllowances.setExistingAmount(0);
						customBulkAllowances.setNewAmount(0);
						customBulkAllowances.setDifference(0);
						customBulkAllowances.setEdit("N");
						bulkList.add(customBulkAllowances);
					}
					}
					else
					{
						logger.info("Deduction is not mapped");
					}
					
					
					
				}*/
				logger.info(" edit flag before leaving service is "+customBulkAllowances.getEdit());
				}
			/*	if(flag==false)
				{
				//logger.info("inside insert");
					customBulkAllowances.setExistingAmount(0);
					customBulkAllowances.setNewAmount(0);
					customBulkAllowances.setDifference(0);
					customBulkAllowances.setEdit("N");
					bulkList.add(customBulkAllowances);
				}
				*/
				
				
				}
				
			

			 if(compoType == 2500134)
    		  {
    			 
    			//payItemList=(ArrayList)bulkAllowanceDAOImpl.getPayItemComboForAllowances(2500340, 30022);
				 EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
					List<HrPayLocComMpg> MappedComoId = empAllwMapDAO.getMappedCompoIDfromCompMpg(locId);
					StringBuffer MappedCompoId = new StringBuffer();
				    for(HrPayLocComMpg xyz :MappedComoId)
				    {
				    	if(xyz.getCmnLookupMst()!=null)
				    	{
				    		MappedCompoId.append(String.valueOf(xyz.getCompId())+",");
				    	}
				    }
				    
				    if(MappedCompoId.length()!=0)
				    {
				    	MappedCompoId.deleteCharAt(MappedCompoId.length()-1);
				    }
				    logger.info("====> str :: "+MappedComoId.size()+ "=====> "+MappedCompoId );
				    
				    String lStrMappedComoId =  MappedCompoId.toString(); 
				    if(lStrMappedComoId.equals(null) || lStrMappedComoId.equals(""))
					{
						lStrMappedComoId = "0";
					}

				    logger.info("==> Passing value Allow:: "+lStrMappedComoId);
				    List<HrPayAllowTypeMst> MappedallowList = empAllwMapDAO.getAllowListforBulk(lStrMappedComoId);
				    payItemList = MappedallowList;
 					
    					
    		
    			
    		  }
    		  else 
    		  {	     
    			  DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
      			  List<HrPayLocComMpg> MappedCompoIdForDedu = empDuducDtlsDAO.getMappedCompoIDfromCompMpg(locId);
      			  StringBuffer MappedCompoIdDedu = new StringBuffer();
      			  for(HrPayLocComMpg xyz :MappedCompoIdForDedu)
      			  {
      				  if(xyz.getCmnLookupMst()!=null)
      				  {
      					  MappedCompoIdDedu.append(String.valueOf(xyz.getCompId())+",");
      				  }
      			  }

      			  if(MappedCompoIdDedu.length()!=0)
      			  {
      				  MappedCompoIdDedu.deleteCharAt(MappedCompoIdDedu.length()-1);
      			  }
      			  logger.info("====> str :: "+MappedCompoIdForDedu.size()+ "=====> "+MappedCompoIdDedu );

      			  String lStrMappedComoIdDedu =  MappedCompoIdDedu.toString(); 
      			  if(lStrMappedComoIdDedu.equals(null) || lStrMappedComoIdDedu.equals(""))
      			  {
      				  lStrMappedComoIdDedu = "0";
      			  }

      			  logger.info("==> Passing value Dedu :: "+lStrMappedComoIdDedu);
      			  List<HrPayDeducTypeMst> MappedDeduList = empDuducDtlsDAO.getDeduListfforBulk(lStrMappedComoIdDedu);

      			  logger.info("====> MappedDeduList Size:: "+MappedDeduList.size());
      			  
      			  payItemList = MappedDeduList;
    			//payItemList=(ArrayList)bulkAllowanceDAOImpl.getPayItemComboForDeductions(2500340, 30022);
    		
  				
    		  }
			
			 
			 objectArgs.put("payItemList",payItemList);
			objectArgs.put("bulkList",bulkList);
			if(size==0)
				objectArgs.put("message","No Records found..Matching Entered Criteria");
			objectArgs.put("dsgnList",dsgnList);
			objectArgs.put("type",compoType);
			objectArgs.put("dsgn",dsgn);
			objectArgs.put("compoId",compoId);
			objectArgs.put("billGroupid",billGroupid);
			
			
			objectArgs.put("size",size);
			
			//added by abhilash
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			//ended by abhilash
			
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			
			resultObject.setViewName("BulkAllowances");
		}
		catch(Exception e)
		{
		
		logger.error("Error is: "+ e.getMessage());	
		}
		return resultObject;
		
		
	}
	public ResultObject getPayItems(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			
			logger.info("inside  getPayItems");
			logger.info("inside getPayItems");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			 
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
            CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   
            
            Map voToService = (Map)objectArgs.get("voToServiceMap");
            long compo = 0;
            if(voToService.get("compoType").toString() != null && voToService.get("compoType").toString() != "")
            	compo = Long.parseLong(voToService.get("compoType").toString());
            
            long loggedInpostId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(loggedInpostId);
			
			logger.info("====> loggedInpostId :: "+loggedInpostId);
			DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
			List<OrgPostDetailsRlt> locIds = deptcomoMpgDAOImpl.getLocIds(loggedInpostId);
			long locId = locIds.get(0).getCmnLocationMst().getLocId();
			logger.info("Location Id in Bulk  ::" + locId);
            
            CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
    	
            
            BulkAllowanceDAOImpl bulkAllowanceDAOImpl =new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
            List payItemList=new ArrayList();
            
            StringBuffer propertyList = new StringBuffer();   		       	       	   		    	
      		
      	

      		  if(compo == 2500134)
      		  {
      			EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				List<HrPayLocComMpg> MappedComoId = empAllwMapDAO.getMappedCompoIDfromCompMpg(locId);
				StringBuffer MappedCompoId = new StringBuffer();
			    for(HrPayLocComMpg xyz :MappedComoId)
			    {
			    	if(xyz.getCmnLookupMst()!=null)
			    	{
			    		MappedCompoId.append(String.valueOf(xyz.getCompId())+",");
			    	}
			    }
			    
			    if(MappedCompoId.length()!=0)
			    {
			    	MappedCompoId.deleteCharAt(MappedCompoId.length()-1);
			    }
			    logger.info("====> str :: "+MappedComoId.size()+ "=====> "+MappedCompoId );
			    
			    String lStrMappedComoId =  MappedCompoId.toString(); 
			    if(lStrMappedComoId.equals(null) || lStrMappedComoId.equals(""))
				{
					lStrMappedComoId = "0";
				}

			    logger.info("==> Passing value Allow:: "+lStrMappedComoId);
			    List<HrPayAllowTypeMst> MappedallowList = empAllwMapDAO.getAllowListforBulk(lStrMappedComoId);
      			 
      		//	payItemList=bulkAllowanceDAOImpl.getPayItemComboForAllowances(2500340, 30022);
      					
      			for(Iterator itr=MappedallowList.iterator();itr.hasNext();)
      			{    			
      				HrPayAllowTypeMst allow = (HrPayAllowTypeMst)itr.next();
      			
      				long allowcode = allow.getAllowCode();
      				String allowName = allow.getAllowDisplayName();
      					propertyList.append("<allowDeduc-mapping>");
                        propertyList.append("<allowDeduc-code>").append("<![CDATA[").append(allowcode).append("]]>").append("</allowDeduc-code>");
                        propertyList.append("<allowDeduc-desc>").append("<![CDATA[").append(allowName).append("]]>").append("</allowDeduc-desc>");
                        propertyList.append("</allowDeduc-mapping>");
      			}
      			
      		  }
      		  else 
      		  {	     
      			  DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
      			  List<HrPayLocComMpg> MappedCompoIdForDedu = empDuducDtlsDAO.getMappedCompoIDfromCompMpg(locId);
      			  StringBuffer MappedCompoIdDedu = new StringBuffer();
      			  for(HrPayLocComMpg xyz :MappedCompoIdForDedu)
      			  {
      				  if(xyz.getCmnLookupMst()!=null)
      				  {
      					  MappedCompoIdDedu.append(String.valueOf(xyz.getCompId())+",");
      				  }
      			  }

      			  if(MappedCompoIdDedu.length()!=0)
      			  {
      				  MappedCompoIdDedu.deleteCharAt(MappedCompoIdDedu.length()-1);
      			  }
      			  logger.info("====> str :: "+MappedCompoIdForDedu.size()+ "=====> "+MappedCompoIdDedu );

      			  String lStrMappedComoIdDedu =  MappedCompoIdDedu.toString(); 
      			  if(lStrMappedComoIdDedu.equals(null) || lStrMappedComoIdDedu.equals(""))
      			  {
      				  lStrMappedComoIdDedu = "0";
      			  }

      			  logger.info("==> Passing value Dedu :: "+lStrMappedComoIdDedu);
      			  List<HrPayDeducTypeMst> MappedDeduList = empDuducDtlsDAO.getDeduListfforBulk(lStrMappedComoIdDedu);

      			  logger.info("====> MappedDeduList Size:: "+MappedDeduList.size());
      			  //payItemList=bulkAllowanceDAOImpl.getPayItemComboForDeductions(2500340, 30022);
				    
      			for(Iterator itr=MappedDeduList.iterator();itr.hasNext();)
      			{    			
      				HrPayDeducTypeMst deduc = (HrPayDeducTypeMst)itr.next();
      				if(deduc.getDeducCode() != 80){
    				  propertyList.append("<allowDeduc-mapping>");
                      propertyList.append("<allowDeduc-code>").append("<![CDATA[").append(deduc.getDeducCode()).append("]]>").append("</allowDeduc-code>");
                      propertyList.append("<allowDeduc-desc>").append("<![CDATA[").append(deduc.getDeducDisplayName()).append("]]>").append("</allowDeduc-desc>");
                      propertyList.append("</allowDeduc-mapping>");
      				}
      			}
    				
      		  }
      		      	    
            String payItemListIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
            logger.info("final string is "+payItemListIdStr);
                      
            objectArgs.put("ajaxKey", payItemListIdStr);               
            resultObject.setResultValue(objectArgs);
            resultObject.setViewName("ajaxData");                          
		}
       catch(Exception e){
			
			logger.info("Exception Ocuures...getPayItems");
			 logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("message", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}


	
	
	public ResultObject insertEmpBulkAllowances(Map objectArgs)
	{
		logger.info("inside the insertEmpBulkAllowances method of BulkAllowances");
		logger.info("inside the insertEmpBulkAllowances method of BulkAllowances");
		Log logger = LogFactory.getLog(getClass());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		ServiceLocator serv=null;
		serv = (ServiceLocator)objectArgs.get("serviceLocator");
		try
		{
			
			
			
			boolean flag=true;
			List dsgnList= new ArrayList();
			BulkAllowanceDAOImpl bulkAllowanceDAOImpl =new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			dsgnList=bulkAllowanceDAOImpl.getDesignationList(1,300022);
			CustomBulkAllowances custom=null;
			long size=0;
			long type=0;
			long compoId=0;
			long dsgn=0;
			long eCode=0;
			boolean empFlag=false;
			long empID=0;
			List payItemList=new ArrayList();
			List<HrPayEmpallowMpg> empallowMpg_batch  = new ArrayList<HrPayEmpallowMpg>();
			List<HrPayDeductionDtls> deductionDtls_batch  = new ArrayList<HrPayDeductionDtls>();
			
			size= objectArgs.get("size") != null?Long.valueOf(objectArgs.get("size").toString()):0;
			type= objectArgs.get("type") != null?Long.valueOf(objectArgs.get("type").toString()):0;
			compoId=objectArgs.get("compoId") != null?Long.valueOf(objectArgs.get("compoId").toString()):0;
			dsgn=objectArgs.get("dsgn") != null?Long.valueOf(objectArgs.get("dsgn").toString()):0;
			eCode=objectArgs.get("eCode") != null?Long.valueOf(objectArgs.get("eCode").toString()):0;
			
			empID = objectArgs.get("empID") != null?Long.valueOf(objectArgs.get("empID").toString()):0;
			
			//added by abhilash
			long billGroupid=objectArgs.get("billGroupid") != null?Long.valueOf(objectArgs.get("billGroupid").toString()):0;
			logger.info("while saving billgroupid abhilash here "+billGroupid);
			//ended by abhilash
			
			logger.info("Manish here "+empID);
		
			if(eCode==1000)
				empFlag=true;
			
			EmpAllwMapDAOImpl allwMapDAOImpl =new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			DeductionDtlsDAOImpl deductionDtlsDAOImpl =new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			EmpInfoDAOImpl empInfoDAOImpl=new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			
			
			AllowTypeMasterDAOImpl allowTypeMasterDAOImpl =new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());
			DeducTypeMasterDAOImpl deducTypeMasterDAOImpl=new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			
			
			
			
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			
			
			  
		        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				
				
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
		           	            			
				
				OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	
			    
			    
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
				
				
		        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
				
				Date sysdate = new Date();
				
			IdGenerator generator =new IdGenerator();
		
			if(empID != 0)
			{
				size=1;
			}
			
			int updateCount;
			for(int i=1;i<=size;i++)
			{
				updateCount = 0;
				
				custom=(CustomBulkAllowances)objectArgs.get("bulkAllowances"+i);
				if(custom.getDifference()!=0)
				{
					System.out.println("Difference is:" +custom.getDifference());
			
				if(empID!=0)
				{
					long duplicateCount=0;
					custom=(CustomBulkAllowances)objectArgs.get("bulkAllowances");
					OtherDetailDAOImpl otherDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
					HrEisEmpMst hrEisEmpMst = otherDAO.getHrEisEmpMst(empID);
					custom.setHrEisEmpId(hrEisEmpMst.getEmpId());
					/*if(type==2500135)
						duplicateCount=bulkAllowanceDAOImpl.checkDeductionDuplicate(empID,compoId);
					else
						duplicateCount=bulkAllowanceDAOImpl.checkAllowanceDuplicate(empID, compoId);
					if(duplicateCount==0)
						custom.setEdit("N");
					else
						custom.setEdit("Y");*/
					logger.info("inside insertBulkallowances edit flag is"+custom.getEdit());
				}
				
				
			/*	logger.info(""+custom.getDifference());
				logger.info(""+custom.getEmpId());
				logger.info(""+custom.getEmployeeName());
				logger.info(""+custom.getExistingAmount());
				logger.info(""+custom.getNewAmount());
				logger.info(""+custom.getEdit());*/
				/*if(custom.getEdit().equals("Y"))
				{*/
					logger.info("inside update part");
					if(type==2500134)
					{
						logger.info("Allowance Updation starts here...");
						//HrPayEmpallowMpg empallowMpg =allwMapDAOImpl.read(custom.getEmpId());
						/*HrPayEmpallowMpg empallowMpg =allwMapDAOImpl.getHrPayEmpallowMpgByHremp(custom.getEmpId(), compoId, -1, -1);
					
						if(empallowMpg != null)
						{
						empallowMpg.setEmpAllowAmount(custom.getNewAmount());
						allwMapDAOImpl.update(empallowMpg);
						}*/
				
						updateCount = allwMapDAOImpl.updateEmpAllowResult(custom.getNewAmount(), orgPostMst.getPostId(), orgUserMst.getUserId(), 
								sysdate, compoId, custom.getHrEisEmpId(), -1, -1);
						logger.info("Allowance Updation ends here");
						
					}
					else
					{
						logger.info("Deduction Updation Starts here");
						//HrPayDeductionDtls deductionDtls =deductionDtlsDAOImpl.read(custom.getEmpId());
						/*HrPayDeductionDtls deductionDtls =deductionDtlsDAOImpl.getHrPayDeductionDtlsByHrEmp(custom.getEmpId(), compoId, -1, -1);
					
						if(deductionDtls != null)
						{
						deductionDtls.setEmpDeducAmount(custom.getNewAmount());
						deductionDtlsDAOImpl.update(deductionDtls);
						}*/
						int year = Calendar.getInstance().get(Calendar.YEAR);
						int month = Calendar.getInstance().get(Calendar.MONTH);
						double netAmt = custom.getNewAmount();
						
						Long lngNetAmt = (long) netAmt;
				 //102,103,72,36
					
						if(compoId== 102 || compoId == 103 || compoId== 72 || compoId == 36 || compoId == 104 || compoId == 105 || compoId == 216 || compoId == 217) {
						Long count = allwMapDAOImpl.getgpfSubscritptionsum(custom.getHrEisEmpId(),month,year);
						count = count + lngNetAmt;
						if(count > 500000) {
							objectArgs.put("message", "The GPF subscription Amout exceeds the limit of 500000 for the current financial year");
						}
						else {
							updateCount = deductionDtlsDAOImpl.updateHrPayDeductionDtls(custom.getNewAmount(), orgPostMst.getPostId(), orgUserMst.getUserId(), 
									sysdate, compoId, custom.getHrEisEmpId(), -1, -1);
							logger.info("Deduction UpdationEnds here");
							objectArgs.put("message", "Record Updated Successfully");
						}
					}else {
						updateCount = deductionDtlsDAOImpl.updateHrPayDeductionDtls(custom.getNewAmount(), orgPostMst.getPostId(), orgUserMst.getUserId(), 
								sysdate, compoId, custom.getHrEisEmpId(), -1, -1);
						logger.info("Deduction UpdationEnds here");
						objectArgs.put("message", "Record Updated Successfully");
					}
						
					}
					
				//}
				
				//Add Update Count here
				//else
					int empCount = allwMapDAOImpl.chkEmpAlreadyExists(custom.getHrEisEmpId(),compoId);
				if(empCount < 1)
				{
					double netAmt = custom.getNewAmount();
					
					if(netAmt > 500000) {
						objectArgs.put("message", "The GPF subscription Amout exceeds the limit of 500000 for the current financial year");
					}else {
					
					logger.info("inside insert part");
					long pk=0;
					if(type==2500134)
					{
						
						logger.info("allowance Insertion started");					
						pk=generator.PKGenerator("HR_PAY_EMPALLOW_MPG", objectArgs);
						HrPayEmpallowMpg empallowMpg=new HrPayEmpallowMpg();
						empallowMpg.setAllowCode(pk);
						empallowMpg.setEmpAllowAmount(custom.getNewAmount());
						
					//	empallowMpg.setHrEisEmpMst(empInfoDAOImpl.read(custom.getEmpId()));
						empallowMpg.setHrEisEmpMst(bulkAllowanceDAOImpl.getEisEmpId(custom.getEmpId(),locId));
						empallowMpg.setHrPayAllowTypeMst(allowTypeMasterDAOImpl.read(compoId));
						empallowMpg.setEmpCurrentStatus(1);
						empallowMpg.setCmnDatabaseMst(cmnDatabaseMst);
						empallowMpg.setCmnLocationMst(cmnLocationMst);
						empallowMpg.setCreatedDate(new Date());
						empallowMpg.setUpdatedDate(new Date());
						empallowMpg.setOrgPostMstByCreatedByPost(orgPostMst);
						empallowMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
						empallowMpg.setOrgUserMstByCreatedBy(orgUserMst);
						empallowMpg.setOrgUserMstByUpdatedBy(orgUserMst);
						empallowMpg.setTrnCounter(1);
						empallowMpg.setMonth(-1);
						empallowMpg.setYear(-1);
						empallowMpg_batch.add(empallowMpg);
						//allwMapDAOImpl.create(empallowMpg);
						
						/*allwMapDAOImpl.insertEmpAllowResult(pk, custom.getHrEisEmpId(), compoId, custom.getNewAmount(), 1, 
								cmnLocationMst.getLocId(), cmnDatabaseMst.getDbId(), orgUserMst.getUserId(), sysdate, orgPostMst.getPostId());
						*/logger.info("Allowance Insertion completed");	
						objectArgs.put("message", "Record Updated Successfully");
					}
					else
					{
						logger.info("Deduction Insertion started");					
						pk=generator.PKGenerator("HR_PAY_DEDUCTION_DTLS", objectArgs);
						HrPayDeductionDtls deductionDtls=new HrPayDeductionDtls();
						deductionDtls.setDeducDtlsCode(pk);
						deductionDtls.setEmpDeducAmount(custom.getNewAmount());
					//	deductionDtls.setHrEisEmpMst(empInfoDAOImpl.read(custom.getEmpId()));
						deductionDtls.setHrEisEmpMst(bulkAllowanceDAOImpl.getEisEmpId(custom.getEmpId(),locId));
						deductionDtls.setHrPayDeducTypeMst(deducTypeMasterDAOImpl.read(compoId));
						deductionDtls.setEmpCurrentStatus(1);
						
						deductionDtls.setCmnDatabaseMst(cmnDatabaseMst);
						deductionDtls.setCmnLocationMst(cmnLocationMst);
						deductionDtls.setCreatedDate(new Date());
						deductionDtls.setUpdatedDate(new Date());
						deductionDtls.setOrgPostMstByCreatedByPost(orgPostMst);
						deductionDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						deductionDtls.setOrgUserMstByCreatedBy(orgUserMst);
						deductionDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						deductionDtls.setTrnCounter(1);
						deductionDtls.setMonth(-1);
						deductionDtls.setYear(-1);
						deductionDtls_batch.add(deductionDtls);
						/*deductionDtlsDAOImpl.create(deductionDtls);
						deductionDtlsDAOImpl.insertHrPayDeductionDtls(pk, custom.getHrEisEmpId(), compoId, custom.getNewAmount(), 1, 
								cmnLocationMst.getLocId(), cmnDatabaseMst.getDbId(), orgUserMst.getUserId(), sysdate, orgPostMst.getPostId());
						*/logger.info("Deduction Insertion Completed");	
						objectArgs.put("message", "Record Updated Successfully");
				}
					}
				}
			}
		}	
			if(!empallowMpg_batch.isEmpty() && empallowMpg_batch != null && empallowMpg_batch.size()>0)
			{
				for(HrPayEmpallowMpg  hrPayEmpallowMpg:empallowMpg_batch)
				{
					allwMapDAOImpl.create(hrPayEmpallowMpg);
				}
			}
			if(!deductionDtls_batch.isEmpty() && deductionDtls_batch != null && deductionDtls_batch.size()>0)
			{
				for(HrPayDeductionDtls  hrPayDeductionDtls:deductionDtls_batch)
				{
					deductionDtlsDAOImpl.create(hrPayDeductionDtls);
				}
			}
			if(type == 2500134)
			{
  			 
				//payItemList=(ArrayList)bulkAllowanceDAOImpl.getPayItemComboForAllowances(2500340, 30022);
				EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				List<HrPayLocComMpg> MappedComoId = empAllwMapDAO.getMappedCompoIDfromCompMpg(locId);
				StringBuffer MappedCompoId = new StringBuffer();
				for(HrPayLocComMpg xyz :MappedComoId)
				{
					if(xyz.getCmnLookupMst()!=null)
					{
						MappedCompoId.append(String.valueOf(xyz.getCompId())+",");
					}
				}

				if(MappedCompoId.length()!=0)
				{
					MappedCompoId.deleteCharAt(MappedCompoId.length()-1);
				}
				logger.info("====> str :: "+MappedComoId.size()+ "=====> "+MappedCompoId );

				String lStrMappedComoId =  MappedCompoId.toString(); 
				if(lStrMappedComoId.equals(null) || lStrMappedComoId.equals(""))
				{
					lStrMappedComoId = "0";
				}

				logger.info("==> Passing value Allow:: "+lStrMappedComoId);
				List<HrPayAllowTypeMst> MappedallowList = empAllwMapDAO.getAllowListforBulk(lStrMappedComoId);
				payItemList = MappedallowList;




			}
			else 
			{	     
				DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
				List<HrPayLocComMpg> MappedCompoIdForDedu = empDuducDtlsDAO.getMappedCompoIDfromCompMpg(locId);
				StringBuffer MappedCompoIdDedu = new StringBuffer();
				for(HrPayLocComMpg xyz :MappedCompoIdForDedu)
				{
					if(xyz.getCmnLookupMst()!=null)
					{
						MappedCompoIdDedu.append(String.valueOf(xyz.getCompId())+",");
					}
				}

				if(MappedCompoIdDedu.length()!=0)
				{
					MappedCompoIdDedu.deleteCharAt(MappedCompoIdDedu.length()-1);
				}
				logger.info("====> str :: "+MappedCompoIdForDedu.size()+ "=====> "+MappedCompoIdDedu );

				String lStrMappedComoIdDedu =  MappedCompoIdDedu.toString(); 
				if(lStrMappedComoIdDedu.equals(null) || lStrMappedComoIdDedu.equals(""))
				{
					lStrMappedComoIdDedu = "0";
				}

				logger.info("==> Passing value Dedu :: "+lStrMappedComoIdDedu);
				List<HrPayDeducTypeMst> MappedDeduList = empDuducDtlsDAO.getDeduListfforBulk(lStrMappedComoIdDedu);

				logger.info("====> MappedDeduList Size:: "+MappedDeduList.size());

				payItemList = MappedDeduList;
				//payItemList=(ArrayList)bulkAllowanceDAOImpl.getPayItemComboForDeductions(2500340, 30022);
  		
				
  		  }
			objectArgs.put("dsgnList",dsgnList);
			objectArgs.put("payItemList",payItemList);
			objectArgs.put("flag",flag);
			objectArgs.put("empFlag",empFlag);
			objectArgs.put("empID",empID);
			objectArgs.put("dsgn",dsgn);
			objectArgs.put("count",2);
			
			objectArgs.put("billGroupid",billGroupid);
			logger.info("payItemList size is "+payItemList.size());
			objectArgs.put("MESSAGECODE",300006);
			
			 resultObject.setResultCode(ErrorConstants.SUCCESS);
			 resultObject.setResultValue(objectArgs);
			 
	         resultObject.setViewName("BulkAllowances");  
		}catch(Exception e)
		{
			logger.error("Error in InsertBulkAllowances"+e.getMessage());
		}
		return resultObject;
	}
	
	//new Method
	public ResultObject getDataForBulkAllowancesBasedOnEmpl(Map objectArgs) throws Exception
	{
	
		logger.info("inside the method:::::::::::: getDataForBulkAllowancesBasedOnEmpl");
		logger.info("inside the method:::::::::::: getDataForBulkAllowancesBasedOnEmpl");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
		
		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
		long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
		long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			List bulkList=new ArrayList();
			List dsgnList= new ArrayList();
			List amtList=new ArrayList();
			List payItemList=new ArrayList();
			long size=0;
			long empId=0;
			long compoId=0;
			double amt=0;
			boolean flag=false;
			
			List empList=new ArrayList();
			long dsgn=0;
			long billNo=0;
			long compoType=0;
			StringBuffer name=new StringBuffer();
			
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			if(voToService != null)
			{
				 empId = voToService.get("empId") != null ?Long.parseLong(voToService.get("empId").toString()):0;
				 compoType=voToService.get("compoType") != null ?Long.parseLong(voToService.get("compoType").toString()):0;
				 compoId=voToService.get("compoId") != null?Long.parseLong(voToService.get("compoId").toString()):0;
			}
			logger.info("compoType is "+compoType);
		
		
			logger.info("emp id in service is "+empId);
			
			BulkAllowanceDAOImpl bulkAllowanceDAOImpl =new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			dsgnList=bulkAllowanceDAOImpl.getDesignationList(langId,locId);
			
			
			//OrgDesignationMst designationMst =(OrgDesignationMst)bulkAllowanceDAOImpl.getDesignationFromEmp(empId).get(0);
			//dsgn=Long.parseLong(designationMst.getDsgnCode());
			
			dsgn= Long.valueOf(bulkAllowanceDAOImpl.getDesignationFromEmp(empId).get(0).toString());
			
			billNo= Long.valueOf(bulkAllowanceDAOImpl.getBillgroupDescFromEmp(empId).get(0).toString());
		
		
			if(empId != 0)
			{
				flag=false;
				CustomBulkAllowances customBulkAllowances =new CustomBulkAllowances();
				
				
				customBulkAllowances.setEmpId(empId);
				//concat(concat(eisMst.orgEmpMst.empFname,eisMst.orgEmpMst.empMname),eisMst.orgEmpMst.empLname) 
				//OrgEmpMst orgEmpMst=(OrgEmpMst)bulkAllowanceDAOImpl.getEmployeeName(empId, locId).get(0);
				OrgEmpMst orgEmpMst=(OrgEmpMst)bulkAllowanceDAOImpl.getEmployeeName(empId).get(0);
				name.append(orgEmpMst.getEmpFname());
				name.append(" ");
				name.append(orgEmpMst.getEmpMname());
				name.append(" ");
				name.append(orgEmpMst.getEmpLname());
				
				customBulkAllowances.setEmployeeName(name.toString());
				if(compoType==2500134)
				{
					List mapedList=bulkAllowanceDAOImpl.getAllwMaped(empId, compoId, -1, -1);
					
					long duplicateCount=0;
					duplicateCount=bulkAllowanceDAOImpl.checkAllowanceDuplicate(empId,compoId);
					
					size=mapedList.size();
					
					if(mapedList != null && mapedList.size()>0)
					{
						if(duplicateCount != 0)
						{
							amtList=bulkAllowanceDAOImpl.getAllw(empId,compoId,-1,-1);
					/*if(amtList!= null && amtList.size()>0)
					{*/
						flag=true;
						HrPayEmpallowMpg amtLst=(HrPayEmpallowMpg)amtList.get(0);
						amt=amtLst.getEmpAllowAmount();
						
						logger.info("allowance amt is "+amt);
						customBulkAllowances.setEdit("Y");
						logger.info("edit flag is in service Y");
						customBulkAllowances.setExistingAmount(amt);
						customBulkAllowances.setNewAmount(amt);
						customBulkAllowances.setDifference(amt);
						bulkList.add(customBulkAllowances);
					}
						else 
						{
							customBulkAllowances.setExistingAmount(0);
							customBulkAllowances.setNewAmount(0);
							customBulkAllowances.setDifference(0);
							customBulkAllowances.setEdit("N");
							bulkList.add(customBulkAllowances);
						}
					}
					else
					{
						logger.info(" Allowance component is not mapped");
					}
				
				}
				else
				{
					
					
					/*if(amtList!= null && amtList.size()>0)
					{*/

					List mapedList=bulkAllowanceDAOImpl.getDeducMaped(empId, compoId, -1, -1);
					
					size=mapedList.size();
					
					long duplicateCount=0;
					duplicateCount=bulkAllowanceDAOImpl.checkDeductionDuplicate(empId,compoId);
					
					if(mapedList != null && mapedList.size()>0)
					{
						if(duplicateCount != 0)
						{
						amtList=bulkAllowanceDAOImpl.getDeduc(empId,compoId,-1,-1);
						flag=true;
						HrPayDeductionDtls amtLst=(HrPayDeductionDtls)amtList.get(0);
						amt=amtLst.getEmpDeducAmount();
					//	logger.info("edit flag is in service Y");
						//logger.info("deduction amt is "+amt);
						customBulkAllowances.setEdit("Y");
						customBulkAllowances.setExistingAmount(amt);
						customBulkAllowances.setNewAmount(amt);
						customBulkAllowances.setDifference(amt);
						bulkList.add(customBulkAllowances);
					}
						else 
						{
							customBulkAllowances.setExistingAmount(0);
							customBulkAllowances.setNewAmount(0);
							customBulkAllowances.setDifference(0);
							customBulkAllowances.setEdit("N");
							bulkList.add(customBulkAllowances);
						}
				
				}
					else
					{
					logger.info(" Deduction component is not mapped");	
					}
					
				}
		
				
				
				}
				
			

			if(compoType == 2500134)
			{

				//payItemList=(ArrayList)bulkAllowanceDAOImpl.getPayItemComboForAllowances(2500340, 30022);
				EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				List<HrPayLocComMpg> MappedComoId = empAllwMapDAO.getMappedCompoIDfromCompMpg(locId);
				StringBuffer MappedCompoId = new StringBuffer();
				for(HrPayLocComMpg xyz :MappedComoId)
				{
					if(xyz.getCmnLookupMst()!=null)
					{
						MappedCompoId.append(String.valueOf(xyz.getCompId())+",");
					}
				}

				if(MappedCompoId.length()!=0)
				{
					MappedCompoId.deleteCharAt(MappedCompoId.length()-1);
				}
				logger.info("====> str :: "+MappedComoId.size()+ "=====> "+MappedCompoId );

				String lStrMappedComoId =  MappedCompoId.toString(); 
				if(lStrMappedComoId.equals(null) || lStrMappedComoId.equals(""))
				{
					lStrMappedComoId = "0";
				}

				logger.info("==> Passing value Allow:: "+lStrMappedComoId);
				List<HrPayAllowTypeMst> MappedallowList = empAllwMapDAO.getAllowListforBulk(lStrMappedComoId);
				payItemList = MappedallowList;




			}
			else 
			{	     
				DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
				List<HrPayLocComMpg> MappedCompoIdForDedu = empDuducDtlsDAO.getMappedCompoIDfromCompMpg(locId);
				StringBuffer MappedCompoIdDedu = new StringBuffer();
				for(HrPayLocComMpg xyz :MappedCompoIdForDedu)
				{
					if(xyz.getCmnLookupMst()!=null)
					{
						MappedCompoIdDedu.append(String.valueOf(xyz.getCompId())+",");
					}
				}

				if(MappedCompoIdDedu.length()!=0)
				{
					MappedCompoIdDedu.deleteCharAt(MappedCompoIdDedu.length()-1);
				}
				logger.info("====> str :: "+MappedCompoIdForDedu.size()+ "=====> "+MappedCompoIdDedu );

				String lStrMappedComoIdDedu =  MappedCompoIdDedu.toString(); 
				if(lStrMappedComoIdDedu.equals(null) || lStrMappedComoIdDedu.equals(""))
				{
					lStrMappedComoIdDedu = "0";
				}

				logger.info("==> Passing value Dedu :: "+lStrMappedComoIdDedu);
				List<HrPayDeducTypeMst> MappedDeduList = empDuducDtlsDAO.getDeduListfforBulk(lStrMappedComoIdDedu);

				logger.info("====> MappedDeduList Size:: "+MappedDeduList.size());

				payItemList = MappedDeduList;
				//payItemList=(ArrayList)bulkAllowanceDAOImpl.getPayItemComboForDeductions(2500340, 30022);


			}

			 
			 
			 objectArgs.put("payItemList",payItemList);
			objectArgs.put("bulkList",bulkList);
			if(size==0)
				objectArgs.put("message","No Records found..Matching Entered Criteria");
			objectArgs.put("dsgnList",dsgnList);
			objectArgs.put("type",compoType);
			objectArgs.put("dsgn",dsgn);
			objectArgs.put("billNo",billNo);
			objectArgs.put("compoId",compoId);
			objectArgs.put("empFlag",true);
			objectArgs.put("empID",empId);
			
			long billGroupid=voToService.get("billGroupid") != null ?Long.parseLong(voToService.get("billGroupid").toString()):0;
			logger.info("billGroupid********** ayyooo"+billGroupid);
			objectArgs.put("billGroupid",billGroupid);
			
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
		
			objectArgs.put("size",size);
			//logger.info("the special size is "+objectArgs.get("size"));
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			
			resultObject.setViewName("BulkAllowances");
		}
		catch(Exception e)
		{
		
		logger.error("Error is: "+ e.getMessage());	
		}
		return resultObject;
		
		
	}
	
	//added by abhilash
	public ResultObject getDataForBulkAllowancesFromBill(Map objectArgs) throws Exception
	{
	
		logger.info("inside the method:::::::::::: getDataForBulkAllowances");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
		
		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
		long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
		long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			List bulkList=new ArrayList();
			List dsgnList= new ArrayList();
			List amtList=new ArrayList();
			List payItemList=new ArrayList();
			long size=0;
			long empId=0;
			long compoId=0;
			double amt=0;
			boolean flag=false;
			
			List empList=new ArrayList();
			long billGroupid=0;
			long compoType=0;
			long dsgn=0;
			
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			if(voToService != null)
			{
				billGroupid = voToService.get("billGroupid") != null ?Long.parseLong(voToService.get("billGroupid").toString()):0;
				 compoType=voToService.get("compoType") != null ?Long.parseLong(voToService.get("compoType").toString()):0;
				 compoId=voToService.get("compoId") != null?Long.parseLong(voToService.get("compoId").toString()):0;
				 dsgn=voToService.get("dsgn") != null?Long.parseLong(voToService.get("dsgn").toString()):0;
			}
			logger.info("compoType is "+compoType);
			logger.info("billGroupid Id is "+billGroupid);
			logger.info("compoId Id is "+compoId);
		
			
			BulkAllowanceDAOImpl bulkAllowanceDAOImpl =new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			dsgnList=bulkAllowanceDAOImpl.getDesignationList(langId,locId);
			logger.info("billGroupid::"+billGroupid);
			if(billGroupid != 0)
			{
				//empList=bulkAllowanceDAOImpl.getEmployeeListFromDsgn(dsgn,langId,locId,compoType,compoId);
				empList=bulkAllowanceDAOImpl.getEmployeeListFromBillgroup(billGroupid,langId,locId,compoType,compoId,dsgn);
				
				logger.info("abhilash is trying for empList size from bill groupwise"+empList.size());
				
				size=empList.size();
			}
			
			//size=size;
			logger.info("size of Emp List is ::::::::::::"+size);
			if(empList != null && empList.size()>0)
			{
				for(int i=0;i<size;i++)
				{
				//flag=false;
				CustomBulkAllowances customBulkAllowances =new CustomBulkAllowances();
				Object[] emp=(Object[])empList.get(i);
				empId=Long.valueOf(emp[0].toString());
				customBulkAllowances.setEmpId(empId);
				customBulkAllowances.setEmployeeName(emp[1].toString());
				customBulkAllowances.setHrEisEmpId(Long.parseLong(emp[2].toString()));
				//logger.error("HrEisEmpId Getting set is--->"+Long.parseLong(emp[2].toString()));
				
				if(emp[4]!=null)
				{
					amt = Double.parseDouble(emp[4].toString());
					customBulkAllowances.setEdit("Y");
					customBulkAllowances.setExistingAmount(amt);
					customBulkAllowances.setNewAmount(amt);
					customBulkAllowances.setDifference(amt);
					bulkList.add(customBulkAllowances);
				}
				else
				{
					customBulkAllowances.setExistingAmount(0);
					customBulkAllowances.setNewAmount(0);
					customBulkAllowances.setDifference(0);
					customBulkAllowances.setEdit("N");
					bulkList.add(customBulkAllowances);
				}
				
				
				/*if(compoType==2500134)
				{	
					long duplicateCount=0;
					duplicateCount=bulkAllowanceDAOImpl.checkAllowanceDuplicate(empId, compoId);
					
					List mapedList=bulkAllowanceDAOImpl.getAllwMaped(empId, compoId, -1, -1);
					if(mapedList != null && mapedList.size()>0)
					{
					if(duplicateCount != 0)
					{
						amtList=bulkAllowanceDAOImpl.getAllw(empId,compoId,-1,-1);
						HrPayEmpallowMpg amtLst=(HrPayEmpallowMpg)amtList.get(0);
						amt=amtLst.getEmpAllowAmount();
						//logger.info("allowance amt is "+amt);

						customBulkAllowances.setEdit("Y");
					//	logger.info("edit flag is in service Y");
						customBulkAllowances.setExistingAmount(amt);
						customBulkAllowances.setNewAmount(amt);
						customBulkAllowances.setDifference(amt);
						bulkList.add(customBulkAllowances);
					
					}
					else 
					{
						customBulkAllowances.setExistingAmount(0);
						customBulkAllowances.setNewAmount(0);
						customBulkAllowances.setDifference(0);
						customBulkAllowances.setEdit("N");
						bulkList.add(customBulkAllowances);

					}
					}
					else
					{
						logger.info("Allowance Componenet is not mappede");
					}
					}	
				
				
				else
				{
					long duplicateCount=0;
					duplicateCount=bulkAllowanceDAOImpl.checkDeductionDuplicate(empId,compoId);
					List mapedList=bulkAllowanceDAOImpl.getDeducMaped(empId, compoId, -1, -1);
					
					if(mapedList != null && mapedList.size()>0)
					{
					
						if(duplicateCount != 0)
						{
							amtList=bulkAllowanceDAOImpl.getDeduc(empId,compoId,-1,-1);
							HrPayDeductionDtls amtLst=(HrPayDeductionDtls)amtList.get(0);
						amt=amtLst.getEmpDeducAmount();
						customBulkAllowances.setEdit("Y");
						customBulkAllowances.setExistingAmount(amt);
						customBulkAllowances.setNewAmount(amt);
						customBulkAllowances.setDifference(amt);
						bulkList.add(customBulkAllowances);
					}
					
					else 
					{
						customBulkAllowances.setExistingAmount(0);
						customBulkAllowances.setNewAmount(0);
						customBulkAllowances.setDifference(0);
						customBulkAllowances.setEdit("N");
						bulkList.add(customBulkAllowances);
					}
					}
					else
					{
						logger.info("Deduction is not mapped");
					}
					
					
					
				}*/
				logger.info(" edit flag before leaving service is "+customBulkAllowances.getEdit());
				}
				
				}
			

			 if(compoType == 2500134)
    		  {
    			 
    			//payItemList=(ArrayList)bulkAllowanceDAOImpl.getPayItemComboForAllowances(2500340, 30022);
				 	EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
					List<HrPayLocComMpg> MappedComoId = empAllwMapDAO.getMappedCompoIDfromCompMpg(locId);
					StringBuffer MappedCompoId = new StringBuffer();
				    for(HrPayLocComMpg xyz :MappedComoId)
				    {
				    	if(xyz.getCmnLookupMst()!=null)
				    	{
				    		MappedCompoId.append(String.valueOf(xyz.getCompId())+",");
				    	}
				    }
				    
				    if(MappedCompoId.length()!=0)
				    {
				    	MappedCompoId.deleteCharAt(MappedCompoId.length()-1);
				    }
				    logger.info("====> str :: "+MappedComoId.size()+ "=====> "+MappedCompoId );
				    
				    String lStrMappedComoId =  MappedCompoId.toString(); 
				    if(lStrMappedComoId.equals(null) || lStrMappedComoId.equals(""))
					{
						lStrMappedComoId = "0";
					}

				    logger.info("==> Passing value Allow:: "+lStrMappedComoId);
				    List<HrPayAllowTypeMst> MappedallowList = empAllwMapDAO.getAllowListforBulk(lStrMappedComoId);
				    payItemList = MappedallowList;
    					
    		
    			
    		  }
    		  else 
    		  {	     
    			  DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
      			  List<HrPayLocComMpg> MappedCompoIdForDedu = empDuducDtlsDAO.getMappedCompoIDfromCompMpg(locId);
      			  StringBuffer MappedCompoIdDedu = new StringBuffer();
      			  for(HrPayLocComMpg xyz :MappedCompoIdForDedu)
      			  {
      				  if(xyz.getCmnLookupMst()!=null)
      				  {
      					  MappedCompoIdDedu.append(String.valueOf(xyz.getCompId())+",");
      				  }
      			  }

      			  if(MappedCompoIdDedu.length()!=0)
      			  {
      				  MappedCompoIdDedu.deleteCharAt(MappedCompoIdDedu.length()-1);
      			  }
      			  logger.info("====> str :: "+MappedCompoIdForDedu.size()+ "=====> "+MappedCompoIdDedu );

      			  String lStrMappedComoIdDedu =  MappedCompoIdDedu.toString(); 
      			  if(lStrMappedComoIdDedu.equals(null) || lStrMappedComoIdDedu.equals(""))
      			  {
      				  lStrMappedComoIdDedu = "0";
      			  }

      			  logger.info("==> Passing value Dedu :: "+lStrMappedComoIdDedu);
      			  List<HrPayDeducTypeMst> MappedDeduList = empDuducDtlsDAO.getDeduListfforBulk(lStrMappedComoIdDedu);

      			  logger.info("====> MappedDeduList Size:: "+MappedDeduList.size());
      			  
      			payItemList = MappedDeduList;
    			//payItemList=(ArrayList)*-.getPayItemComboForDeductions(2500340, 30022);
    		
  				
    		  }
			
			 
			 objectArgs.put("payItemList",payItemList);
			objectArgs.put("bulkList",bulkList);
			if(size==0)
				objectArgs.put("message","No Records found..Matching Entered Criteria");
			
			objectArgs.put("dsgnList",dsgnList);
			objectArgs.put("type",compoType);
			objectArgs.put("billGroupid",billGroupid);
			objectArgs.put("dsgn",dsgn);
			objectArgs.put("compoId",compoId);
			
			
			objectArgs.put("size",size);
			
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			
			resultObject.setViewName("BulkAllowances");
		}
		catch(Exception e)
		{
		
		logger.error("Error is: "+ e.getMessage());	
		}
		return resultObject;
		
		
	}
	//ended by abhilash
}