/********************This service will act as interface between payroll and other applications like
 ********  home or IWDMS for Loans and Advances ****************************************/

package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLeaveDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrLeaveEmpDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
@Param:Entry

empId
leaveType
leaveFromDate
edit
leaveToDate
leaveAvailBal
leaveTaken
isDeleted

 **/


public class PayrollIntegrationLeaveService extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());


	public ResultObject PayrollIntegrationApproveRejectLeave(Map objServiceArgs)
	{
		logger.info("inside PayrollIntegrationApproveRejectLeave---------->");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");

		Map loginDetailsMap=(Map)objServiceArgs.get("baseLoginMap");


		try
		{/*
			ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");

			EmpLeaveDAOImpl empLeaveDao = new EmpLeaveDAOImpl(HrLeaveEmpDtls.class,serv.getSessionFactory());
			long empID = 0;	
			String editFromVO = objServiceArgs.get("edit")!=null?objServiceArgs.get("edit").toString():"N";

			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

			CmnLocationMst cmnLocationMst= (CmnLocationMst) loginDetailsMap.get("locationVO");							       			

			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			empID=objServiceArgs.get("empId")!=null?Long.parseLong(objServiceArgs.get("empId").toString()):0;
			long leaveType = objServiceArgs.get("leaveType")!=null?Long.parseLong(objServiceArgs.get("leaveType").toString()):0;
			
			HrEssLeaveMst hrEssLeaveMst = new HrEssLeaveMst();
			GenericDaoHibernateImpl gImpl = new GenericDaoHibernateImpl(HrEssLeaveMst.class);
			gImpl.setSessionFactory(serv.getSessionFactory());
			String cols1[] = {"leaveTypeid"}; 
			Object values1[] = {leaveType}; 
			
			List<HrEssLeaveMst> HrEssLeaveMstList = gImpl.getListByColumnAndValue(cols1, values1);
			
			if(HrEssLeaveMstList!=null && HrEssLeaveMstList.size()==1)
			{	
				hrEssLeaveMst =  HrEssLeaveMstList.get(0);
			}
			
			Date  leaveFromDate = (Date)(objServiceArgs.get("leaveFromDate")!=null?objServiceArgs.get("leaveFromDate"):new Date());
			int isDeleted = objServiceArgs.get("isDeleted")!=null?Integer.parseInt(objServiceArgs.get("isDeleted").toString()):0;


			Date sysdate = new Date();

			if(editFromVO.equalsIgnoreCase("Y") || isDeleted==1)
			{
				HrLeaveEmpDtls hrLeaveEmpDtls=new HrLeaveEmpDtls();				

				gImpl = new GenericDaoHibernateImpl(HrLeaveEmpDtls.class);
				gImpl.setSessionFactory(serv.getSessionFactory());
				String cols[] = {"hrEisEmpMst.empId","hrEssLeaveMst.leaveTypeid","leaveFromDate"}; 
				Object values[] = {empID,leaveType,leaveFromDate}; 
				
				List<HrLeaveEmpDtls> HrLeaveEmpDtlsList = gImpl.getListByColumnAndValue(cols, values);
				
				logger.info("HrLeaveEmpDtlsList.size()"+HrLeaveEmpDtlsList.size());
				
				if(HrLeaveEmpDtlsList!=null && HrLeaveEmpDtlsList.size()==1)
				{	
				hrLeaveEmpDtls =  HrLeaveEmpDtlsList.get(0);
				
				hrLeaveEmpDtls.setLeaveAvailBal(objServiceArgs.get("leaveAvailBal")!=null?Long.parseLong(objServiceArgs.get("leaveAvailBal").toString()):0);
				hrLeaveEmpDtls.setLeaveTaken(objServiceArgs.get("leaveTaken")!=null?Long.parseLong(objServiceArgs.get("leaveTaken").toString()):0);

				
				hrLeaveEmpDtls.setHrEssLeaveMst(hrEssLeaveMst);
				hrLeaveEmpDtls.setLeaveFromDate(leaveFromDate);
				hrLeaveEmpDtls.setLeaveToDate((Date)(objServiceArgs.get("leaveToDate")!=null?objServiceArgs.get("leaveToDate"):new Date()));				
				hrLeaveEmpDtls.setIsDeleted(isDeleted);				
				hrLeaveEmpDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrLeaveEmpDtls.setOrgUserMstByUpdatedBy(orgUserMst);				
				hrLeaveEmpDtls.setUpdatedDate(sysdate);				
				hrLeaveEmpDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrLeaveEmpDtls.setOrgUserMstByUpdatedBy(orgUserMst);
				hrLeaveEmpDtls.setTrnCounter(new Integer(1));

				try
				{
					empLeaveDao.update(hrLeaveEmpDtls);
				}
				catch(Exception e)
				{
					logger.error("Error is: "+ e.getMessage());
				}
				}
			}
			else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
			{
				HrLeaveEmpDtls hrLeaveEmpDtls=new HrLeaveEmpDtls();

				EmpInfoDAOImpl empInfoDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				HrEisEmpMst hrEisEmpMst = empInfoDAO.read(empID);	

				hrLeaveEmpDtls.setHrEisEmpMst(hrEisEmpMst);
				IdGenerator idgen=new IdGenerator();
				Long empLeaveId=idgen.PKGenerator("hr_leave_emp_dtls", objServiceArgs);
				hrLeaveEmpDtls.setEmpLeaveId(empLeaveId);
				hrLeaveEmpDtls.setLeaveAvailBal(objServiceArgs.get("leaveAvailBal")!=null?Long.parseLong(objServiceArgs.get("leaveAvailBal").toString()):0);
				hrLeaveEmpDtls.setLeaveTaken(objServiceArgs.get("leaveTaken")!=null?Long.parseLong(objServiceArgs.get("leaveTaken").toString()):0);


				hrLeaveEmpDtls.setHrEssLeaveMst(hrEssLeaveMst);
				hrLeaveEmpDtls.setLeaveFromDate((Date)(objServiceArgs.get("leaveFromDate")!=null?objServiceArgs.get("leaveFromDate"):new Date()));
				hrLeaveEmpDtls.setLeaveToDate((Date)(objServiceArgs.get("leaveToDate")!=null?objServiceArgs.get("leaveToDate"):new Date()));				
				hrLeaveEmpDtls.setIsDeleted(0);				
				hrLeaveEmpDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrLeaveEmpDtls.setOrgUserMstByUpdatedBy(orgUserMst);				
				hrLeaveEmpDtls.setUpdatedDate(sysdate);				
				hrLeaveEmpDtls.setOrgPostMstByCreatedByPost(orgPostMst);
				hrLeaveEmpDtls.setCmnDatabaseMst(cmnDatabaseMst);
				hrLeaveEmpDtls.setCmnLocationMst(cmnLocationMst);
				hrLeaveEmpDtls.setOrgUserMstByCreatedBy(orgUserMst);
				hrLeaveEmpDtls.setCreatedDate(sysdate);
				hrLeaveEmpDtls.setTrnCounter(new Integer(1));

				try
				{
					empLeaveDao.create(hrLeaveEmpDtls);
				}
				catch(Exception e)
				{
					logger.error("Error is: "+ e.getMessage());
				}
			}


			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objServiceArgs);

		*/}
		catch(Exception e)
		{			
			logger.error("Error is: "+ e.getMessage());
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");
		}

		return resultObject;
	}
}
