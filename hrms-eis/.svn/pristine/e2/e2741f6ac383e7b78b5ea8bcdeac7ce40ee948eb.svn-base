
package com.tcs.sgv.eis.service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmployeeSignatureDtlsDAO;
import com.tcs.sgv.eis.dao.EmployeeSignatureDtlsDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPaySignatureDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmployeeSignatureDtlsServiceImpl extends ServiceImpl implements EmployeeSignatureDtlsService
{
	
	static private final Log logger = LogFactory.getLog(EmployeeSignatureDtlsService.class );

// for create and update by stephen

	public ResultObject insertEmployeeSignatureData(Map objectArgs) 
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Coming into the Service method insertEmployeeSignatureDtls");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			
			long empId=0;
	        long dsgnId=0;
			long locId=0;
			int mesg=0;
			String msg="";
			
			HrPaySignatureDtls empSignatureDtls = (HrPaySignatureDtls)objectArgs.get("empSignatureDtls");
			logger.info( "The EmployeeSignatureDetails VO is " +empSignatureDtls);
			EmployeeSignatureDtlsDAOImpl empSignatureDtlsDAO = new EmployeeSignatureDtlsDAOImpl(HrPaySignatureDtls.class,serv.getSessionFactory());
			
			dsgnId=(Long) objectArgs.get("dsngId");
			GenericDaoHibernateImpl<OrgDesignationMst, Long> gDao = new GenericDaoHibernateImpl<OrgDesignationMst, Long>(OrgDesignationMst.class);
	        gDao.setSessionFactory(serv.getSessionFactory());
	        OrgDesignationMst orgDesignationMst=new OrgDesignationMst();
	        orgDesignationMst = (OrgDesignationMst) gDao.read(dsgnId);
	        empSignatureDtls.setOrgDesignationMst(orgDesignationMst);
	        logger.info("the designation  Id is"+dsgnId);
	        
	        
			locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString()).longValue();
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId); 
			
			String editFromVO = objectArgs.get("edit").toString();
	        logger.info("editFromVO insertEmployeeSignatureDtlsData" + editFromVO);
			logger.info("the emp id is "+empId);
			logger.info("the designation id is "+dsgnId);
			logger.info("the location id is"+ locId);
			OrgPostMst orgPostMst=new OrgPostMst();
			OrgUserMst orgUsrMst= new OrgUserMst();
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			orgPostMst=orgPostMstDaoImpl.read(postId);
			logger.info("the post id:"+postId);
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			logger.info("the user id:"+userId);
			Date sysdate = new Date();
			logger.info("before if");
		if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
	      {	
			logger.info("INSIDE CREATE insertEmployeeSignatureDtls"); 
			empId=(Long) objectArgs.get("empId");
			logger.info("the emp id is "+empId);
			GenericDaoHibernateImpl<OrgEmpMst, Long> genericDao = new GenericDaoHibernateImpl<OrgEmpMst, Long>(OrgEmpMst.class);
			genericDao.setSessionFactory(serv.getSessionFactory());
			orgEmpMst= (OrgEmpMst)genericDao.read(empId);
			EmpInfoDAOImpl empDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			List<HrEisEmpMst> hrEmpMstList = empDao.getListByColumnAndValue("orgEmpMst", orgEmpMst);
			HrEisEmpMst hrEmpMst = new HrEisEmpMst();
			if(hrEmpMstList!=null && hrEmpMstList.size()>0)
			{
				hrEmpMst = hrEmpMstList.get(0);
			}
			orgUsrMst = orgEmpMst.getOrgUserMst();
			empSignatureDtls.setHrEisEmpMst(hrEmpMst);
			
	        empSignatureDtls.setCmnLocationMst(cmnLocationMst);
	        
			IdGenerator idGen = new IdGenerator();
			long Id = idGen.PKGenerator("hr_pay_signature_dtls", objectArgs);
			logger.info("the generated Id is"+Id);
			empSignatureDtls.setId(Id);
			
			empSignatureDtls.setOrgPostMstByCreatedByPost(orgPostMst);
			empSignatureDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			empSignatureDtls.setOrgUserMstByUpdatedBy(orgUserMst);
			empSignatureDtls.setOrgUserMstByCreatedBy(orgUserMst);
			empSignatureDtls.setCreatedDate(sysdate); 
			empSignatureDtlsDAO.create(empSignatureDtls);
			msg = "Record Inserted Successfully";
	       }
		else if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
		   {
			 logger.info("INSIDE UPDATE insertEmployeeSignatureDtls");
			 long id=empSignatureDtls.getId();
		     HrPaySignatureDtls empSignatureDtlsVO = (HrPaySignatureDtls) empSignatureDtlsDAO.read(id);
		     empSignatureDtlsVO.setOrgDesignationMst(empSignatureDtls.getOrgDesignationMst());
		     empSignatureDtlsVO.setStartDate(empSignatureDtls.getStartDate());
		     empSignatureDtlsVO.setEndDate(empSignatureDtls.getEndDate());
		     empSignatureDtlsVO.setUpdatedDate(sysdate);
		     empSignatureDtlsVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		     empSignatureDtlsVO.setOrgUserMstByUpdatedBy(orgUserMst);
		     empSignatureDtlsDAO.update(empSignatureDtlsVO);
		     msg ="Record Updated Successfully";
		     //mesg=1;
		   }
			 List <HrPaySignatureDtls> actionList = empSignatureDtlsDAO.getAllEmployeeSignatureDtlsData(locId);
			 HrPaySignatureDtls hrPaySignatureDtls = new HrPaySignatureDtls();
				if(actionList!=null && actionList.isEmpty())
	            {
				 for(Iterator it=actionList.iterator();it.hasNext();)
				 {
					 hrPaySignatureDtls = (HrPaySignatureDtls)it.next();	            
	              logger.info("To Avoid LazyInitialization in insert Employee Signature Details Data" + hrPaySignatureDtls.getHrEisEmpMst().getEmpId());
	              logger.info("To Avoid LazyInitialization in insert Employee Signature Data" + hrPaySignatureDtls.getOrgDesignationMst().getDsgnId());
	             }
	            }
			 
			EmployeeSignatureDtlsDAO empSgnDtlsDAO=new EmployeeSignatureDtlsDAOImpl(OrgDesignationMst.class,serv.getSessionFactory());
			List dsgnList = empSgnDtlsDAO.getAllDesignationDtlsData(); 
			List empList = new ArrayList();	
			objectArgs.put("actionList", actionList);
			objectArgs.put("DesignationList", dsgnList);
			objectArgs.put("empList", empList);
			/*objectArgs.put("msg", msg);
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getEmployeeSgnDetailsView");
			if(mesg==1)
			{
				objectArgs.put("MESSAGECODE",300006);
			}
			else
			{
				objectArgs.put("MESSAGECODE",300005);
			}
				
			objectArgs.put("redirectMap", redirectMap);	*/
			retObj.setResultCode(ErrorConstants.SUCCESS);
			retObj.setResultValue(objectArgs);
			retObj.setViewName("showEmployeeSignatureDetailsView");
			logger.info("INSERTED SUCCESSFULLY");
		
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Occurs...insertEmployeeSignatureDetails   "+ne);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objectArgs.put("msg", "Null Pointer Exception");
			retObj.setResultValue(objectArgs);
			retObj.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Occurs...insertEmployeeSignatureDetails"+pe);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objectArgs.put("msg", "PropertyValue Exception");
			retObj.setResultValue(objectArgs);
			retObj.setViewName("errorInsert");				
		}
		catch(Exception e){
			
			logger.info("Exception Occurs...insertEmployeeSignatureDetails"+e);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objectArgs.put("msg", " Exception");
			retObj.setResultValue(objectArgs);
			retObj.setViewName("errorInsert");			
		}
		return retObj;
	}


//for Displaying view  page by stephen

public ResultObject getEmployeeSignatureDtlsData(Map objectArgs)
{
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
	try
	{
		long locId=0;
		int flg=0;
		String empName="";
		//String msg="";
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString()).longValue();
        String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
		EmployeeSignatureDtlsDAO employeeSgnDtlsDAO=new EmployeeSignatureDtlsDAOImpl(HrPaySignatureDtls.class,serv.getSessionFactory());
		
		if((voToService.get("Employee_srchNameText_EmpSearch")!=null&&!voToService.get("Employee_srchNameText_EmpSearch").equals("")))
			empName=(String)voToService.get("Employee_srchNameText_EmpSearch").toString();
		long empID = 0;
		empID=(voToService.get("Employee_ID_EmpSearch")!=null&&!voToService.get("Employee_ID_EmpSearch").equals(""))?Long.parseLong((String)voToService.get("Employee_ID_EmpSearch")):0;
		logger.info("the employee id is:"+empID);
		if(voToService.get("Employee_ID_EmpSearch")!=null&&!voToService.get("Employee_ID_EmpSearch").equals(""))
		    flg=1;
		else
	        flg=0;
		List <HrPaySignatureDtls> actionList = new ArrayList();
		actionList = employeeSgnDtlsDAO.getAllEmployeeSignatureDtlsData(locId,empID);
		EmployeeSignatureDtlsDAO empSgnDtlsDAO=new EmployeeSignatureDtlsDAOImpl(OrgDesignationMst.class,serv.getSessionFactory());
		HrPaySignatureDtls hrPaySignatureDtls = new HrPaySignatureDtls();
	    if(actionList!=null && actionList.isEmpty())
	      {
			for(Iterator it=actionList.iterator();it.hasNext();)
			  {
				hrPaySignatureDtls = (HrPaySignatureDtls)it.next();	            
			    logger.info("To Avoid LazyInitialization in the emp name is " + hrPaySignatureDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname());
			    logger.info("To Avoid LazyInitialization in insert Employee Signature Details Data" + hrPaySignatureDtls.getOrgDesignationMst().getDsgnId());
			  }
		   }
		objectArgs.put("actionList", actionList); 
		objectArgs.put("empName", empName); 
		objectArgs.put("flg", flg);
		//objectArgs.put("msg", msg);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		retObj.setResultValue(objectArgs);
		retObj.setViewName("showEmployeeSignatureDetailsView");
		logger.info("Service complete...");
	   
	}
	catch(Exception e)
	{		
		logger.info("Null Pointer Exception Occurs.... viewEmployeeSignatureDtls"+e);
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		retObj.setResultValue(objectArgs);
		retObj.setViewName("errorInsert");		
	}

	return retObj;
}

//for displaying Edit  and insert Page by stephen

public ResultObject getEmployeeSignatureDtlsIdData(Map objectArgs)
{
	logger.info("getEmployeeSignatureData IN Employee Signature Details Master Data");
	ResultObject retObject = new ResultObject(ErrorConstants.SUCCESS);
	try
	{
		 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	     long locId=0;
	     String empName = "";
		 Map voToService = (Map)objectArgs.get("voToServiceMap");
		 logger.info("voToService"+voToService);
		 String editFlag = (String)voToService.get("edit");
		 logger.info("editFlag"+editFlag);
		 Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		 locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString()).longValue();
		 logger.info("location id="+locId);
		 EmployeeSignatureDtlsDAO empSgnDtlsDAO=new EmployeeSignatureDtlsDAOImpl(OrgDesignationMst.class,serv.getSessionFactory());
		 List dsgnList = empSgnDtlsDAO.getAllDesignationDtlsData(); 
		 if((voToService.get("empName")!=null&&!voToService.get("empName").equals("")))
			empName=voToService.get("empName").toString();

		 if(editFlag != null && editFlag.equals("Y"))
	      {
			logger.info("editFromVO in EmployeeSignatureDetailsServiceImpl getEmployeeSignatureDtlsIdData" + voToService.get("edit"));
			long Id=0;
	        if(voToService.get("Id")!=null && !voToService.get("Id").equals(""))
	         {	
	        	Id = Long.parseLong((String)voToService.get("Id"));
	         }	
    	 logger.info("for update screen...EmployeeSignatureDetailsId is " + Id);
    	 EmployeeSignatureDtlsDAO empSignatureDtlsDAO = new EmployeeSignatureDtlsDAOImpl(HrPaySignatureDtls.class,serv.getSessionFactory());
    	 HrPaySignatureDtls empSignatureDtls =(HrPaySignatureDtls)empSignatureDtlsDAO.getHrPaySignatureDtlsIdData(Id);
    	 Date DBdate = DBUtility.getCurrentDateFromDB();
    	 long dsgnId=0;
    	 if(empSignatureDtls!=null )
    	 {
    			dsgnId =empSignatureDtls.getOrgDesignationMst().getDsgnId();
	      } 
    	 logger.info("designation id is:"+dsgnId);
    	 List signatureDtlsEmpIdList = empSignatureDtlsDAO.getAllEmployeeSignatureEmpId();
    	 List signatureEmpIdList = new ArrayList();
    	 for(int listCount=0;listCount<signatureDtlsEmpIdList.size();listCount++)
			{
				logger.info("the size is from service is "+signatureDtlsEmpIdList.size()+" and the value is "+signatureDtlsEmpIdList.get(listCount));
				signatureEmpIdList.add(listCount,signatureDtlsEmpIdList.get(listCount));
			}
    	 EmpInfoDAOImpl empInfoDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
    	 HrEisEmpMst hrEisEmpMst= new HrEisEmpMst();
    	 logger.info("getEmployeeSignatureData I m in edit mode ");
         HrPaySignatureDtls newEmpSignatureDtls= new HrPaySignatureDtls();
         newEmpSignatureDtls.setOrgDesignationMst(empSignatureDtls.getOrgDesignationMst());
         newEmpSignatureDtls.setStartDate(empSignatureDtls.getStartDate());
         newEmpSignatureDtls.setEndDate(empSignatureDtls.getEndDate());
         newEmpSignatureDtls.setId(empSignatureDtls.getId());
         hrEisEmpMst.setOrgEmpMst(empSignatureDtls.getHrEisEmpMst().getOrgEmpMst());
         newEmpSignatureDtls.setHrEisEmpMst(hrEisEmpMst);         		
         if(newEmpSignatureDtls!=null)
           {
         	 logger.info("To Avoid LazyInitialization getEmployeeSignatureDtlsIdData empID " + newEmpSignatureDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());
   	         logger.info("To Avoid LazyInitialization getEmployeeSignatureDtlsIdData dsgnID "+ newEmpSignatureDtls.getOrgDesignationMst().getDsgnId());
	            
	       }
         objectArgs.put("actionList", newEmpSignatureDtls);
         objectArgs.put("DesignationList", dsgnList);
         objectArgs.put("flag","edit");
         retObject.setResultCode(ErrorConstants.SUCCESS);
         retObject.setResultValue(objectArgs);
         retObject.setViewName("showEmployeeSignatureDetailsMaster");
	      }
	    else if(editFlag != null && editFlag.equalsIgnoreCase("show"))
	      {
		   Date DBdate = DBUtility.getCurrentDateFromDB();
		   objectArgs.put("DesignationList", dsgnList);
		   objectArgs.put("empName", empName);
		   objectArgs.put("date",DBdate);
		   objectArgs.put("flag","insert");
		   retObject.setResultCode(ErrorConstants.SUCCESS);
		   retObject.setResultValue(objectArgs);
		   retObject.setViewName("showEmployeeSignatureDetailsMaster");
	       }
	    else
	       {
			List <HrPaySignatureDtls> actionList =empSgnDtlsDAO.getAllEmployeeSignatureDtlsData(locId);
			objectArgs.put("actionList", actionList);
		    retObject.setResultCode(ErrorConstants.SUCCESS);
			retObject.setResultValue(objectArgs);
			retObject.setViewName("showEmployeeSignatureDetailsView");
	        }
	}
	catch(PropertyValueException pe)
	{
		logger.info("Null Pointer Exception Occurs...insertEmployeeSignatureDtls"+pe);
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		retObject.setResultValue(objectArgs);
		retObject.setViewName("errorInsert");		
	}
	catch(NullPointerException ne)
	{	
		logger.info("Null Pointer Exception Occurs...insertEmployeeSignatureDtls"+ne);
		objectArgs.put("msg", "null pointer exception");
		retObject.setResultValue(objectArgs);
		retObject.setViewName("errorInsert");				
	}	
	catch(Exception e)
	{
		logger.info("Exception Occurs...insertEmployeeSignatureDtls"+e);
		objectArgs.put("msg", " exception");
		retObject.setResultValue(objectArgs);
		retObject.setViewName("errorInsert");
	}
	return retObject;

}
//check start date and end date by stephen

public ResultObject checkDate(Map objectArgs)
{
	logger.info("IN Service method Check Date");
	ResultObject retObject = new ResultObject(ErrorConstants.SUCCESS);
	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	
	try
	{
		long locId=0;
		long id=0;
		EmployeeSignatureDtlsDAO employeeSgnDtlsDAO=new EmployeeSignatureDtlsDAOImpl(HrPaySignatureDtls.class,serv.getSessionFactory());
		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString()).longValue();
		logger.info("location id="+locId);
		
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		logger.info("voToService"+voToService);
		StringBuffer propertyList = new StringBuffer();
		if(voToService.get("startdate")!= null && (!voToService.get("startdate").equals("")))        
  	    {
		String Startdate = (String)voToService.get("startdate");
		 if(voToService.get("Id")!= null && (!voToService.get("Id").equals("")))
		   {
		     id=Long.parseLong((String)voToService.get("Id"));
		   }
		logger.info("id is:"+id);
		Date startdate=StringUtility.convertStringToDate(Startdate);
		DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String startDate=sdf.format(startdate);
		List startDateList=employeeSgnDtlsDAO.checkStartDate(startDate,locId,id);
		propertyList.append("<start-date>");   	
        propertyList.append("<list-size>").append(startDateList.size()).append("</list-size>");
        propertyList.append("</start-date>"); 
        logger.info("start date list size " + startDateList.size());
  	    }
		else if(voToService.get("enddate")!= null && (!voToService.get("enddate").equals("")))        
  	    {
		String Enddate = (String)voToService.get("enddate");
		 if(voToService.get("Id")!= null && (!voToService.get("Id").equals("")))
		  {
		    id=Long.parseLong((String)voToService.get("Id"));
		  }
		logger.info("id is:"+id);
		Date enddate=StringUtility.convertStringToDate(Enddate);
		DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String endDate=sdf.format(enddate);
		List endDateList=employeeSgnDtlsDAO.checkEndDate(endDate,locId,id);
		propertyList.append("<end-date>");   	
        propertyList.append("<list-size>").append(endDateList.size()).append("</list-size>");
        propertyList.append("</end-date>"); 
        logger.info("start date list size " + endDateList.size());
  	    }
	    else
	    {
		logger.info("no start date and end date in action flag");
	    }
		Map result = new HashMap();
        String dateStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
        result.put("ajaxKey", dateStr);
        retObject.setResultCode(ErrorConstants.SUCCESS);
        retObject.setResultValue(result);
        retObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
        logger.info("END OF CHECK DATE METHOD...\n");
	}
	catch(NullPointerException ne)
	{
		logger.info("Null Pointer Exception Occurs...check date"+ne);
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		objectArgs.put("msg", "Null Pointer Exception");
		retObject.setResultValue(objectArgs);
		retObject.setViewName("errorInsert");			
	}
	catch(PropertyValueException pe)
	{
		logger.info("PropertyValueException Occurs...check date"+pe);
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		objectArgs.put("msg", "PropertyValue Exception");
		retObject.setResultValue(objectArgs);
		retObject.setViewName("errorInsert");				
	}
	catch(Exception e)
	{
		logger.info("Exception Occurs...check date"+e);
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		objectArgs.put("msg", " Exception");
		retObject.setResultValue(objectArgs);
		retObject.setViewName("errorInsert");			
	}
	return retObject;
}
}



	

