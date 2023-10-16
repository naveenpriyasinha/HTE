/********************This service will act as interface between payroll and other applications like
 ********  home or IWDMS for Loans and Advances ****************************************/

package com.tcs.sgv.eis.webService.service;

import java.util.Date;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.eis.valueobject.HrPayLeaveSalaryDtl;
import com.tcs.sgv.eis.valueobject.HrPayLeaveEncashDtl;
import com.tcs.sgv.eis.dao.EmpLeaveSalaryDAOImpl;
import com.tcs.sgv.eis.dao.EmpLeaveEncashDtlDAOImpl;

public class PayrollIntegrationLSAndLEService extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("unchecked")
	public ResultObject PayrollIntegrationLeaveSalaryEntry(Map objectArgs) {
		{
			


			logger.info("-------------inside PayrollIntegrationLeaveSalaryEntry-------------");
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	

			Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");

			try{
				
				
				int dutyType = Integer.parseInt((objectArgs.get("dutyType")!=null?objectArgs.get("dutyType").toString():"0"));
				Date todayDate = new Date();
				Date leaveDate=((Date)(objectArgs.get("LeaveDate")!=null?objectArgs.get("LeaveDate"):new Date()));
				long applicantUserId=StringUtility.convertToLong(objectArgs.get("UserId").toString());
				String action=(objectArgs.get("Action")!=null?objectArgs.get("Action").toString():"");
				
				Date updatedLeaveDate=((Date)(objectArgs.get("UpdatedLeaveDate")!=null?objectArgs.get("UpdatedLeaveDate"):new Date()));
				
				long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
				OrgUserMst applicantOrgUserMst=orgUserMstDaoImpl.read(applicantUserId);
				
				long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

				CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
				
				long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
				
				
				HrPayLeaveSalaryDtl leaveSalaryDtl = new HrPayLeaveSalaryDtl();
				EmpLeaveSalaryDAOImpl empLSDAO = new EmpLeaveSalaryDAOImpl(HrPayLeaveSalaryDtl.class,serv.getSessionFactory());
				double LS=0;
				if(action.equalsIgnoreCase("ADDNEW") || action.equalsIgnoreCase(""))
				{
				LS=empLSDAO.getOneDayLS(applicantUserId, dutyType,leaveDate);
					
				IdGenerator idGen = new IdGenerator();
				Long lsId=idGen.PKGenerator("hr_pay_leavesalary_dtl",objectArgs);
				
				leaveSalaryDtl.setLsId(lsId);
				leaveSalaryDtl.setOrgUserMstByUserId(applicantOrgUserMst);
				leaveSalaryDtl.setDutyType(dutyType);
				leaveSalaryDtl.setLeaveDate(leaveDate);
				leaveSalaryDtl.setOrgUserMstByCreatedBy(orgUserMst);
				leaveSalaryDtl.setOrgUserMstByUpdatedBy(orgUserMst);
				leaveSalaryDtl.setCmnDatabaseMst(cmnDatabaseMst);
				leaveSalaryDtl.setCmnLocationMst(cmnLocationMst);
				leaveSalaryDtl.setOrgPostMstByCreatedByPost(orgPostMst);
				leaveSalaryDtl.setOrgPostMstByUpdatedByPost(orgPostMst);
				leaveSalaryDtl.setCreatedDate(todayDate);
				leaveSalaryDtl.setUpdatedDate(todayDate);
				leaveSalaryDtl.setStatus(1);
				leaveSalaryDtl.setLSAmount(LS);
				
				empLSDAO.create(leaveSalaryDtl);
				}
				
				else if(action.equalsIgnoreCase("UPDATE"))
				{
					//to be removed by ravysh
					LS=empLSDAO.getOneDayLS(applicantUserId, dutyType,updatedLeaveDate);
					
					leaveSalaryDtl=empLSDAO.getEmpLeaveSalaryDtlByLeaveDateAndUSerId(applicantUserId, leaveDate);
					leaveSalaryDtl.setLeaveDate(updatedLeaveDate);
					leaveSalaryDtl.setDutyType(dutyType);
					leaveSalaryDtl.setOrgUserMstByUpdatedBy(orgUserMst);
					leaveSalaryDtl.setOrgPostMstByUpdatedByPost(orgPostMst);
					leaveSalaryDtl.setUpdatedDate(todayDate);
					leaveSalaryDtl.setLSAmount(LS);
					if(leaveSalaryDtl.getOrgUserMstByUserId()!=null)
					empLSDAO.update(leaveSalaryDtl);
				}
				
				else if(action.equalsIgnoreCase("DELETE"))
				{
					leaveSalaryDtl=empLSDAO.getEmpLeaveSalaryDtlByLeaveDateAndUSerId(applicantUserId, leaveDate);
					leaveSalaryDtl.setOrgUserMstByUpdatedBy(orgUserMst);
					leaveSalaryDtl.setOrgPostMstByUpdatedByPost(orgPostMst);
					leaveSalaryDtl.setUpdatedDate(todayDate);
					leaveSalaryDtl.setStatus(0);
					if(leaveSalaryDtl.getOrgUserMstByUserId()!=null)
					empLSDAO.update(leaveSalaryDtl);
				}
				
				resultObject.setResultValue(objectArgs);
				logger.info("Inserted Successfully In PayrollIntegrationLeaveSalaryEntry");


			}
			catch(ConstraintViolationException ex)
			{
				logger.info("TransactionSystemException occurs...");
			}
			catch(Exception e){
				logger.info("The error is:-");
				logger.error("Error is: "+ e.getMessage());
				logger.info("There is some error while inserting time");
				objectArgs.put("MESSAGECODE",300001);
				resultObject.setResultValue(objectArgs);
				resultObject.setThrowable(e);
				resultObject.setResultCode(-1);
				resultObject.setViewName("errorPage");

			}
			return resultObject;

		}

	}
	@SuppressWarnings("unchecked")
	public ResultObject PayrollIntegrationLeaveEncashEntry(Map objectArgs) {
		
		logger.info("-------------inside PayrollIntegrationLeaveEncashEntry-------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	

		Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");

		try{
			Date todayDate = new Date();
			
			double elAmount = Double.parseDouble((objectArgs.get("Amount")!=null?objectArgs.get("Amount").toString():"0"));
			
			double elCount=Double.parseDouble((objectArgs.get("ELCount")!=null?objectArgs.get("ELCount").toString():"0"));
			
			long applicantUserId=((OrgUserMst)(objectArgs.get("UserId"))).getUserId();
			
			int status = Integer.parseInt((objectArgs.get("Status")!=null?objectArgs.get("Status").toString():"0"));
			
			HrPayLeaveEncashDtl leaveEncashDtl = new HrPayLeaveEncashDtl();
			
			IdGenerator idGen = new IdGenerator();
			Long leId=idGen.PKGenerator("hr_pay_leaveencash_dtl",objectArgs);
			
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			OrgUserMst applicantOrgUserMst=orgUserMstDaoImpl.read(applicantUserId);
			
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			leaveEncashDtl.setEncashId(leId);
			leaveEncashDtl.setCmnDatabaseMst(cmnDatabaseMst);
			leaveEncashDtl.setCmnLocationMst(cmnLocationMst);
			leaveEncashDtl.setOrgUserMstByCreatedBy(orgUserMst);
			leaveEncashDtl.setOrgUserMstByUpdatedBy(orgUserMst);
			leaveEncashDtl.setOrgPostMstByCreatedByPost(orgPostMst);
			leaveEncashDtl.setOrgPostMstByUpdatedByPost(orgPostMst);
			leaveEncashDtl.setOrgUserMstByUserId(applicantOrgUserMst);
			leaveEncashDtl.setElAmount(elAmount);
			leaveEncashDtl.setNoOfEl(elCount);
			leaveEncashDtl.setCreatedDate(todayDate);
			leaveEncashDtl.setUpdatedDate(todayDate);
			leaveEncashDtl.setStatus(status);
			
			EmpLeaveEncashDtlDAOImpl empLEDAO = new EmpLeaveEncashDtlDAOImpl(HrPayLeaveEncashDtl.class,serv.getSessionFactory());
			empLEDAO.create(leaveEncashDtl);

			resultObject.setResultValue(objectArgs);
			logger.info("Inserted Successfully In PayrollIntegrationLeaveEncashEntry");


		}
		catch(ConstraintViolationException ex)
		{
			logger.info("TransactionSystemException occurs...");
		}
		catch(Exception e){
			logger.info("The error is:-");
			logger.error("Error is: "+ e.getMessage());
			logger.info("There is some error while inserting time");
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");

		}
		return resultObject;

	
	}
	
}
