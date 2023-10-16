package com.tcs.sgv.eis.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.AlwDdctBulkEmplDAOImpl;
import com.tcs.sgv.eis.dao.BulkAllowanceDAOImpl;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompGrpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDao;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;

@SuppressWarnings({ "unchecked", "deprecation" })
public class AllDdctBulkEmp extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	String EmpCHeckd= null;
	String EmpUnCHeckd= null;
	public ResultObject getBulkAllwDdct(Map objectArgs)
	{
		logger.info("inside the method::::::::::::genereteDataForBulkAllowances");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			
			
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			List dsgnList= new ArrayList();
			BulkAllowanceDAOImpl bulkAllowanceDAOImpl =new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			dsgnList=bulkAllowanceDAOImpl.getDesignationList(langId,locId);
			
			logger.info("Designation List size is"+dsgnList.size());
			
			objectArgs.put("dsgnList",dsgnList);
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			logger.info("By By to genereteDataForBulkAllowances");
			resultObject.setViewName("BulkEmpl");
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());	
		}
		return resultObject;
		
	}
	
	/*	public ResultObject getDataForBulkAllowances(Map objectArgs) throws Exception
	{
	
		logger.info("inside the method:::::::::::: getDataForBulkAllowances");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
		
		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
//		long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
//		long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
//		long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
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
			
			BulkAllowanceDAOImpl bulkAllowanceDAOImpl =new BulkAllowanceDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			dsgnList=bulkAllowanceDAOImpl.getDesignationList(langId,locId);
			logger.info("dsgn IS:::"+dsgn);
			if(dsgn != 0)
			{
				empList=bulkAllowanceDAOImpl.getEmployeeListFromDsgn(dsgn,langId,locId,compoType,compoId,billGroupid);
				size=empList.size();
			}
			
			logger.info("size of Emp List is ::::::::::::"+size);
			if(empList != null && empList.size()>0)
			{
				for(int i=0;i<size;i++)
				{
				CustomBulkAllowances customBulkAllowances =new CustomBulkAllowances();
				Object[] emp=(Object[])empList.get(i);
				empId=Long.valueOf(emp[0].toString());
				customBulkAllowances.setEmpId(empId);
				customBulkAllowances.setEmployeeName(emp[1].toString());
				if(compoType==2500134)
				{	
					long duplicateCount=0;
					duplicateCount=bulkAllowanceDAOImpl.checkAllowanceDuplicate(empId, compoId);
					
					List mapedList=bulkAllowanceDAOImpl.getAllwMaped(empId, compoId, -1, -1);
					if(mapedList != null && mapedList.size()>0)
					{
					if(  amtList!= null && amtList.size()>0)
					{
						
					
					if(amtList!= null && amtList.size()>0)
					{
						flag=true;
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
					//bulkList.add(customBulkAllowances);
				
				
				else
				{
					long duplicateCount=0;
					duplicateCount=bulkAllowanceDAOImpl.checkDeductionDuplicate(empId,compoId);
					List mapedList=bulkAllowanceDAOImpl.getDeducMaped(empId, compoId, -1, -1);
					
					if(mapedList != null && mapedList.size()>0)
					{
					if(amtList!= null && amtList.size()>0)
					{
					if(amtList!= null && amtList.size()>0)
					{
						flag=true;
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
				}
				logger.info(" edit flag before leaving service is "+customBulkAllowances.getEdit());
				}
				if(flag==false)
				{
				//logger.info("inside insert");
					customBulkAllowances.setExistingAmount(0);
					customBulkAllowances.setNewAmount(0);
					customBulkAllowances.setDifference(0);
					customBulkAllowances.setEdit("N");
					bulkList.add(customBulkAllowances);
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
				    List<HrPayAllowTypeMst> MappedallowList = empAllwMapDAO.getAllowListforBulk1(lStrMappedComoId);
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
      			  List<HrPayDeducTypeMst> MappedDeduList = empDuducDtlsDAO.getDeduListfforBulk1(lStrMappedComoIdDedu);

      			  logger.info("====> MappedDeduList Size:: "+MappedDeduList.size());
      			  
      			  payItemList = MappedDeduList;
    			//payItemList=(ArrayList)bulkAllowanceDAOImpl.getPayItemComboForDeductions(2500340, 30022);
    		
  				
    		  }
			
			 
			 objectArgs.put("payItemList",payItemList);
			objectArgs.put("bulkList",bulkList);
			if(size==0)
				objectArgs.put("msg","No Records found..Matching Entered Criteria");
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
			
			resultObject.setViewName("BulkEmpl");
		}
		catch(Exception e)
		{
		
		e.printStackTrace();	
		}
		return resultObject;
		
		
	}*/
	public ResultObject getPayItems(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("inside getPayItems");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
            Map voToService = (Map)objectArgs.get("voToServiceMap");
            long compoType = 0;
            if(voToService.get("compoType").toString() != null && voToService.get("compoType").toString() != "")
            	compoType = Long.parseLong(voToService.get("compoType").toString());
            long loggedInpostId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());		
			logger.info("====> loggedInpostId :: "+loggedInpostId);
			DeptCompMPGDAOImpl deptcomoMpgDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
			AlwDdctBulkEmplDAOImpl alwDdctBulkEmplDAOImpl = new AlwDdctBulkEmplDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			List<OrgPostDetailsRlt> locIds = deptcomoMpgDAOImpl.getLocIds(loggedInpostId);
			long locId = locIds.get(0).getCmnLocationMst().getLocId();
			logger.info("Location Id in Bulk  ::" + locId);
			List payItemList = alwDdctBulkEmplDAOImpl.getMappedCompoMst(locId,compoType);

			StringBuffer propertyList = new StringBuffer();
			if(compoType == 2500134){
				for(Iterator itr=payItemList.iterator();itr.hasNext();)
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
			if(compoType == 2500135){
				for(Iterator itr=payItemList.iterator();itr.hasNext();)
				{    			
					HrPayDeducTypeMst deduc = (HrPayDeducTypeMst)itr.next();

					propertyList.append("<allowDeduc-mapping>");
					propertyList.append("<allowDeduc-code>").append("<![CDATA[").append(deduc.getDeducCode()).append("]]>").append("</allowDeduc-code>");
					propertyList.append("<allowDeduc-desc>").append("<![CDATA[").append(deduc.getDeducDisplayName()).append("]]>").append("</allowDeduc-desc>");
					propertyList.append("</allowDeduc-mapping>");
				}
			}
			String payItemListIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("final string is "+payItemListIdStr);
			objectArgs.put("ajaxKey", payItemListIdStr);               
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");                          
		}
       catch(Exception e){
    	   logger.error("Exception Ocuures...getPayItems"+e.getMessage());
    	   objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    	   resultObject.setResultValue(objectArgs);
    	   resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}

	public ResultObject insertEmpBulkAllowances(Map objectArgs)
	{
		logger.info("inside the insertEmpBulkAllowances method of BulkAllowances");
		Log logger = LogFactory.getLog(getClass());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		ServiceLocator serv=null;
		serv = (ServiceLocator)objectArgs.get("serviceLocator");
		try
		{
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			
			String lStrCheckedEmpIds = null;
			String lStrUncheckedEmpIds = null;
			long compoId=0;
			long compoType = 0;
			boolean updateFlag = true;
			AlwDdctBulkEmplDAOImpl bulkAlloDAOImpl= new AlwDdctBulkEmplDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			EmpCompMpgDAOImpl empCompMpgDAOImpl = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class, serv.getSessionFactory());
			int updateCount= 0;
			HrEisEmpCompMpg hrEisEmpCompMpg= null;
			HrEisEmpCompGrpMst hrEisEmpCompGrpMst = null;
			List hrEisGrpMstList = null;
			//long empCompoIdPk=0;
			IdGenerator idGenerator = new IdGenerator();
			List hrEisCompoMpgList_batch = new ArrayList();
			GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(CmnLookupMst.class);
			genDAO.setSessionFactory(serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			String lStrArrCheckedEmpIds[] = null;
			String lStrArrUncheckedEmpIds[] = null;
			long billGroupid=0;
			if(voToService != null)
			{
				lStrCheckedEmpIds = voToService.get("hiddenEmpIdsChecked") != null ?voToService.get("hiddenEmpIdsChecked").toString():"";
				lStrUncheckedEmpIds = voToService.get("hiddenEmpIdsUnChecked") != null ?voToService.get("hiddenEmpIdsUnChecked").toString():"";
				compoId = voToService.get("compoId") != null?Long.parseLong(voToService.get("compoId").toString()):0;
				compoType = voToService.get("compoType") != null?Long.parseLong(voToService.get("compoType").toString()):0;
				billGroupid = voToService.get("billGroupid") != null ?Long.parseLong(voToService.get("billGroupid").toString()):0;
			}
			if(lStrCheckedEmpIds != null && lStrCheckedEmpIds.length() >0){
				logger.info(" lStrCheckedEmpIds are"+lStrCheckedEmpIds);
				lStrArrCheckedEmpIds= lStrCheckedEmpIds.split(",");
				logger.info(" lStrArrCheckedEmpIds are"+lStrArrCheckedEmpIds.length);
			}
			if(lStrUncheckedEmpIds != null && lStrUncheckedEmpIds.length() >0){
				logger.info(" lStrUncheckedEmpIds are"+lStrUncheckedEmpIds);
				lStrArrUncheckedEmpIds= lStrUncheckedEmpIds.split(",");
				logger.info(" lStrArrUncheckedEmpIds are"+lStrArrUncheckedEmpIds.length);
			}
			
			CmnLookupMst cmnLookupMst = (CmnLookupMst)genDAO.read(compoType);
			//Data Updation Starts here
			if(lStrArrCheckedEmpIds != null){
				for(int i=0; i<lStrArrCheckedEmpIds.length; i++)
				{
					updateCount=0;
					updateCount = bulkAlloDAOImpl.updateHrEisEmpCompoMpg(Long.valueOf(lStrArrCheckedEmpIds[i]),compoType,compoId,updateFlag);

					if(updateCount < 1){
						hrEisEmpCompGrpMst = new HrEisEmpCompGrpMst();
						hrEisGrpMstList = empCompMpgDAOImpl.getMstDataFromEmpID(Long.valueOf(lStrArrCheckedEmpIds[i]));
						if(!hrEisGrpMstList.isEmpty() && hrEisGrpMstList != null && hrEisGrpMstList.size() > 0){
							//empCompoIdPk = idGenerator.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", objectArgs);
							hrEisEmpCompGrpMst = (HrEisEmpCompGrpMst) hrEisGrpMstList.get(0);
							hrEisEmpCompMpg = new HrEisEmpCompMpg();
							//hrEisEmpCompMpg.setEmpCompId(empCompoIdPk);
							hrEisEmpCompMpg.setCompId(compoId);
							hrEisEmpCompMpg.setIsactive(1);
							hrEisEmpCompMpg.setCmnLookupMst(cmnLookupMst);
							hrEisEmpCompMpg.setHrEisEmpCompGrpMst(hrEisEmpCompGrpMst);
							hrEisEmpCompMpg.setStartDate(new Date());
							hrEisEmpCompMpg.setRemarks("Data Entry Through Screen");
							hrEisCompoMpgList_batch.add(hrEisEmpCompMpg);

						}
					}
				}
			}
			if(lStrArrUncheckedEmpIds != null){
				for(int i=0; i<lStrArrUncheckedEmpIds.length; i++)
				{
					updateFlag = false;
					updateCount = bulkAlloDAOImpl.updateHrEisEmpCompoMpg(Long.valueOf(lStrArrUncheckedEmpIds[i]),compoType,compoId,updateFlag);
				}
			}
			if(!hrEisCompoMpgList_batch.isEmpty() && hrEisCompoMpgList_batch != null && hrEisCompoMpgList_batch.size()>0){
				long listSize = hrEisCompoMpgList_batch.size();
				GenericDaoHibernateImpl genDAOToInsertBatch = new GenericDaoHibernateImpl(HrEisEmpCompMpg.class);
				genDAOToInsertBatch.setSessionFactory(serv.getSessionFactory());
				objectArgs.put("counter", hrEisCompoMpgList_batch.size());
				long pkSeqEmpCompId = idGenerator.PKGenerator("HR_EIS_EMP_COMPONENT_MPG", objectArgs);
				for(int i =0 ; i< listSize;i++){
					hrEisEmpCompMpg = (HrEisEmpCompMpg)hrEisCompoMpgList_batch.get(i);
					hrEisEmpCompMpg.setEmpCompId(++pkSeqEmpCompId);
					genDAOToInsertBatch.create(hrEisEmpCompMpg);
					hrEisEmpCompMpg=null;
				}
			}
			
			//Data Updation Ends here
			List payItemList = bulkAlloDAOImpl.getMappedCompoMst(locId,compoType);
			logger.info("payItemList size is "+payItemList.size());
			//Code to re fetch the list of Emps mapped to Bill and required params at JSP
			List bulkList=new ArrayList();
			long size=0;
			long empId=0;
			long empCompId=0;
		//	long duplicateCount = 0;			
			List empList=new ArrayList();
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long dsgn=0;
			int countInCustomVO=0;
			logger.info("billGroupid::"+billGroupid);
			List dsgnList= new ArrayList();
			//dsgnList=bulkAlloDAOImpl.getDesignationList(langId,locId);
			if(billGroupid != 0)
			{
				empList=bulkAlloDAOImpl.getEmployeeListFromBillgroup(billGroupid, langId, locId, dsgn, compoType,compoId );
				logger.info("empList size from bill groupwise"+empList.size());
				size=empList.size();
			}
			logger.info("size of Emp List is ::::::::::::"+size);
			if(empList != null && empList.size()>0)
			{
				for(int i=0;i<size;i++)
				{
					CustomBulkAllowances customBulkAllowances =new CustomBulkAllowances();
					Object[] emp=(Object[])empList.get(i);
					empId=Long.valueOf(emp[0].toString());
					empCompId=emp[2]!=null ? Long.valueOf(emp[2].toString()) : 0;
					customBulkAllowances.setEmpId(empId);
					customBulkAllowances.setEmployeeName(emp[1].toString());
			//		duplicateCount=0;
					//duplicateCount=bulkAlloDAOImpl.checkAllowanceDuplicate(empId, compoId,compoType);
					if(empCompId != 0)
					{
						countInCustomVO++;
						customBulkAllowances.setEdit("1");
						bulkList.add(customBulkAllowances);
						EmpCHeckd = EmpCHeckd!=null ? EmpCHeckd+emp[1].toString() : emp[1].toString();

					}
					else 
					{
						customBulkAllowances.setEdit("0");
						bulkList.add(customBulkAllowances);
						EmpUnCHeckd = EmpUnCHeckd!=null ? EmpUnCHeckd+emp[1].toString() : emp[1].toString();
					}
					logger.info(" edit flag before leaving service is "+customBulkAllowances.getEdit());
				}
			}
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			objectArgs.put("bulkList", bulkList);
			objectArgs.put("dsgnList",dsgnList);
			objectArgs.put("type",compoType);
			objectArgs.put("dsgn",dsgn);
			objectArgs.put("compoId",compoId);
			objectArgs.put("billGroupid",billGroupid);
			objectArgs.put("size",size);
			objectArgs.put("payItemList",payItemList);
			objectArgs.put("msg", "Record Updated Successfully");
			objectArgs.put("MESSAGECODE",300006);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
	        resultObject.setViewName("BulkEmpl");  
		}catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	
	public ResultObject getBulkAllDdcBasedonBilDesg(Map objectArgs) throws Exception
	{
	
		logger.info("inside the method:::::::::::: getDataForBulkAllowances");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);
		
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			List bulkList=new ArrayList();
			List dsgnList= new ArrayList();
			List payItemList=new ArrayList();
			long size=0;
			long empId=0;
			long empCompId=0;
			long compoId=0;
		//	long duplicateCount = 0;			
			List empList=new ArrayList();
			long billGroupid=0;
			long compoType=0;
			long dsgn=0;
			int countInCustomVO=0;
			AdminOrgPostDtlDao adminOrgPostDtlDao = new AdminOrgPostDtlDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
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
		
			
			AlwDdctBulkEmplDAOImpl bulkAlloDAOImpl= new AlwDdctBulkEmplDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			//dsgnList=bulkAllowanceDAOImpl.getDesignationList(langId,locId);
			logger.info("billGroupid::"+billGroupid);
			if(billGroupid != 0)
			{
				empList=bulkAlloDAOImpl.getEmployeeListFromBillgroup(billGroupid, langId, locId, dsgn, compoType,compoId );
				//empList=bulkAlloDAOImpl.getEmployeeListFromBillgroup(billGroupid, langId, locId, dsgn);
				logger.info("empList size from bill groupwise"+empList.size());
				size=empList.size();
			}
			logger.info("size of Emp List is ::::::::::::"+size);
			if(empList != null && empList.size()>0)
			{
				for(int i=0;i<size;i++)
				{
					CustomBulkAllowances customBulkAllowances =new CustomBulkAllowances();
					Object[] emp=(Object[])empList.get(i);
					empId=Long.valueOf(emp[0].toString());
					empCompId=emp[2]!=null ? Long.valueOf(emp[2].toString()) : 0;
					customBulkAllowances.setEmpId(empId);
					customBulkAllowances.setEmployeeName(emp[1].toString());
					//duplicateCount=0;
					//duplicateCount=bulkAlloDAOImpl.checkAllowanceDuplicate(empId, compoId,compoType);
					if(empCompId != 0)
					{
						countInCustomVO++;
						customBulkAllowances.setEdit("1");
						bulkList.add(customBulkAllowances);
						//EmpCHeckd = EmpCHeckd!=null ? EmpCHeckd+emp[1].toString() : emp[1].toString();
					}
					else 
					{
						customBulkAllowances.setEdit("0");
						bulkList.add(customBulkAllowances);
						//EmpUnCHeckd = EmpUnCHeckd!=null ? EmpUnCHeckd+emp[1].toString() : emp[1].toString();
					}
					logger.info(" edit flag before leaving service is "+customBulkAllowances.getEdit());
				}
			}	
			logger.info(" Count with Edit 1 is "+countInCustomVO);
			
		    payItemList = bulkAlloDAOImpl.getMappedCompoMst(locId,compoType);	
				    
			objectArgs.put("payItemList",payItemList);
			objectArgs.put("bulkList",bulkList);
			if(size==0)
				objectArgs.put("msg","No Records found..");
			
			List billList = adminOrgPostDtlDao.getAllBillsFromLocation(locId);
			objectArgs.put("billList", billList);
			objectArgs.put("dsgnList",dsgnList);
			objectArgs.put("type",compoType);
			objectArgs.put("billGroupid",billGroupid);
			objectArgs.put("dsgn",dsgn);
			objectArgs.put("compoId",compoId);
			objectArgs.put("EmpCHeckd",EmpCHeckd);
			objectArgs.put("EmpUnCHeckd",EmpUnCHeckd);
			objectArgs.put("empList",empList);
			objectArgs.put("size",size);
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("BulkEmpl");
		}
		catch(Exception e)
		{
			logger.error("Error in Mass Update of Emp Compp"+e.getMessage());
		}
		return resultObject;
	}
}
