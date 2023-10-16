package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

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
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.empPunishmentDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEmpPunishmentDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class empPunishmentService extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	
	
	public ResultObject getPunishmentData(Map objServiceArgs)
	{
		
		logger.info("---------------inside getPunishmentData-------------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{			
            ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
           
            empPunishmentDAOImpl punishmentDao = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,serv.getSessionFactory());
            HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
            
            
           HttpSession session=request.getSession();		
           Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");	   
           String locId=loginDetailsMap.get("locationId").toString();
           
    		Map voToService = (Map)objServiceArgs.get("voToServiceMap");
			String editFlag = voToService.get("edit")!=null?(String)voToService.get("edit"):"";
			long pmtEmpId=0;
			pmtEmpId=(voToService.get("Employee_ID_punishmentSearch")!=null&&!voToService.get("Employee_ID_punishmentSearch").equals(""))?Long.parseLong((String)voToService.get("Employee_ID_punishmentSearch")):0;
			logger.info("The pmtEmpId is------>>>>>"+pmtEmpId);
			if(editFlag != null && editFlag.equals("Y"))
            {
            	
           		String pmtId=(String)voToService.get("pmtId");
				HrEmpPunishmentDtls actionList = punishmentDao.getPunishmentData(Long.parseLong(pmtId));
            	objServiceArgs.put("actionList", actionList);
            	
            	
	        		logger.info("the emp name is "+actionList.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+actionList.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+actionList.getHrEisEmpMst().getOrgEmpMst().getEmpMname());
	        	
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objServiceArgs);
		        resultObject.setViewName("empPunishmentEdit");
		        Long attach_id = actionList.getAttachment_Id();
		        if(attach_id!=null)
				{
					CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
					
					CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(attach_id);
								
					objServiceArgs.put("orderId",cmnAttachmentMst);
					logger.info("cmnattachmentmst : " + cmnAttachmentMst);
				}
            	
            }
            else
            {
            	List <HrEmpPunishmentDtls> punishmentList = new ArrayList();
            	if(pmtEmpId!=0)
            		punishmentList = punishmentDao.getAllPunishmentDataByEmpId(pmtEmpId);
            	else
            		punishmentList = punishmentDao.getAllPunishmentData(locId);
            	logger.info("list size in getPunishmentData is------------>"+punishmentList.size());
            	
            	for(int i=0;i<punishmentList.size();i++)
	        	{
	        		logger.info("the emp name is "+punishmentList.get(i).getHrEisEmpMst().getOrgEmpMst().getEmpFname()+punishmentList.get(i).getHrEisEmpMst().getOrgEmpMst().getEmpLname()+punishmentList.get(i).getHrEisEmpMst().getOrgEmpMst().getEmpMname());
	        	}
                objServiceArgs.put("punishmentList", punishmentList);
                resultObject.setResultCode(ErrorConstants.SUCCESS);
                resultObject.setResultValue(objServiceArgs);
                resultObject.setViewName("empPunishmentView");	   
                
               
           }
		}
		
		catch(Exception e)
		{		
			logger.error("Error is: "+ e.getMessage());
			resultObject.setViewName("errorInsert");
		}
	
		return resultObject;
	}


public ResultObject insertPunishmentData(Map objectArgs) {
	
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	try{
		logger.info("************Inside insertPunishmentData*****************");
		HrEmpPunishmentDtls hrEmpPunishmentDtls = (HrEmpPunishmentDtls)objectArgs.get("punishmentDtls");
		
		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObj = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
		Map resultMap = (Map) resultObj.getResultValue();
		

		empPunishmentDAOImpl punishmentDAO = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,serv.getSessionFactory());
		GenericDaoHibernateImpl gImpl = null;
		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		
		String editFromVO = objectArgs.get("edit").toString();
      
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
		 
		long attachment_Id = (Long) resultMap.get("AttachmentId_orderId"); 
		logger.info("after inserting attachment! attach_id : " + attachment_Id);		
        
       if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
		{
        	
        	long pmtID = hrEmpPunishmentDtls.getPunishmentId();
        	logger.info("The PunishmentId inside update mode is------>"+pmtID);
        	HrEmpPunishmentDtls hrempPunishmentDtls = punishmentDAO.read(pmtID);
			logger.info("INSIDE UPDATE insertPunishmentData");	
			
			hrempPunishmentDtls.setReason(hrEmpPunishmentDtls.getReason());
			hrempPunishmentDtls.setStartDate(hrEmpPunishmentDtls.getStartDate());
			hrempPunishmentDtls.setEndDate(hrEmpPunishmentDtls.getEndDate());
			
			Date sysdate = new Date();
			hrempPunishmentDtls.setUpdatedDate(sysdate);
			hrempPunishmentDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			hrempPunishmentDtls.setOrgUserMstByUpdatedBy(orgUserMst);
			punishmentDAO.update(hrempPunishmentDtls);
			msg=1;
			logger.info("Updated successfully................");
		}
        else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
		{
			logger.info("INSIDE CREATE insertPunishmentData");
			IdGenerator idGen = new IdGenerator();
			long punishmentId = idGen.PKGeneratorWODBLOC("hr_emp_punishment_dtls", objectArgs);
			hrEmpPunishmentDtls.setPunishmentId(punishmentId);
	        
			 
			 HrEisEmpMst hrEisObj=new HrEisEmpMst();            
		     gImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
		     gImpl.setSessionFactory(serv.getSessionFactory());
		     String empIdStr="";
		     empIdStr=objectArgs.get("empId").toString();
		     long empId=0;
		     if(empIdStr!=null && !empIdStr.equals(""))
		     {
		    	 empId=Long.parseLong(empIdStr);
		    	 logger.info("the emp id is "+empId);
		     }
		     
		     List<HrEisEmpMst> hrEisMstList = gImpl.getListByColumnAndValue("orgEmpMst.empId", empId);
		     if(hrEisMstList!=null && hrEisMstList.size()>0)
		     {
		    	 hrEisObj = hrEisMstList.get(0);
		     }
		     logger.info("the emp id is "+hrEisObj.getOrgEmpMst().getEmpFname());
		     
			 Date sysdate = new Date();
			 hrEmpPunishmentDtls.setHrEisEmpMst(hrEisObj);
			 hrEmpPunishmentDtls.setOrgPostMstByCreatedByPost(orgPostMst);
			 hrEmpPunishmentDtls.setCmnLocationMst(cmnLocationMst);
			 hrEmpPunishmentDtls.setOrgUserMstByCreatedBy(orgUserMst);
			 hrEmpPunishmentDtls.setCreatedDate(sysdate);				 				 							
			 hrEmpPunishmentDtls.setAttachment_Id(attachment_Id);
			 
			 punishmentDAO.create(hrEmpPunishmentDtls);
			 
			
		}
        
		
        if(msg==1)
			objectArgs.put("MESSAGECODE",300006);
		else
			objectArgs.put("MESSAGECODE",300005);
					
      //added by manish khunt
  
        
       long  changedEmpId=Long.valueOf(objectArgs.get("empId").toString());
       long postIdNew=0;
       empPunishmentDAOImpl punishmentDAONew = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,serv.getSessionFactory());
       List lstPostIdUserId= punishmentDAONew.getPostIdUserId(changedEmpId);
 
	  long userIdNew=0;
	  for(Iterator itr=lstPostIdUserId.iterator();itr.hasNext();)
	  {    
		Object[] row = (Object[])itr.next();
		postIdNew=Long.valueOf(row[0].toString());
		userIdNew=Long.valueOf(row[1].toString());
		break;
	 }
       
       OrgPostMst orgPostMstNew =orgPostMstDaoImpl.read(postIdNew);
       OrgUserMst orgUserMstNew =orgUserMstDaoImpl.read(userIdNew);
    	
		Map mapForChangedRecords=objectArgs;
		logger.info("Setting map to objectArgs");
		mapForChangedRecords.put("changedPostId",postIdNew);
		mapForChangedRecords.put("changedEmpId",changedEmpId);
		mapForChangedRecords.put("locId", locId);
		mapForChangedRecords.put("serviceLocator",serv);
		mapForChangedRecords.put("OrgPostMst",orgPostMstNew);
		mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
		mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
		GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
		long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
		logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
		logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
	//ended by manish khunt
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		
		resultObject.setResultValue(objectArgs);
		if
		(msg==1)
			resultObject.setViewName("empPunishmentEdit");
		else
		resultObject.setViewName("empPunishmentMaster");
		logger.info("INSERTED SUCCESSFULLY insertPunishmentData");
	}
	catch(NullPointerException ne)
	{
		
		logger.error("Error is: "+ ne.getMessage());
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("errorInsert");			
	}
	
	catch(Exception e){
		
		 logger.error("Error is: "+ e.getMessage());
		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		 resultObject.setResultValue(objectArgs);
		 resultObject.setViewName("errorInsert");
	}
	return resultObject;
}


}