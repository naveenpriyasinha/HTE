package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLeaveDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrLeaveEmpDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * Created by Japen Pathak for for inserting / updating data in HR_LEAVE_EMP_DTLS tables.
 */

public class EmpLeaveServiceHelper {
	public final Log logger = LogFactory.getLog(getClass());
	ServiceLocator serv ;
	public EmpLeaveServiceHelper(ServiceLocator serv) {
	
		this.serv = serv;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_LEAVE_EMP_DTLS table.
	 * @param    HrLeaveEmpDtls leaveVO,long userId,long dbId, long postId
	 * @return   void
	 */

	
	public void updateHrEmpLeaveDtls(HrLeaveEmpDtls leaveVO,long userId,long dbId, long postId) throws Exception
	{
		Date sysdate = new Date();
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		
		OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		OrgPostMst orgPostMst=cmn.getOrgPostMst(postId);
		
		EmpLeaveDAOImpl empLeaveDao = new EmpLeaveDAOImpl(HrLeaveEmpDtls.class,serv.getSessionFactory());
		long leaveId= leaveVO.getEmpLeaveId();
		logger.info("INSIDE INSERT EmpLeaveServiceHelper updateHrEmpLeaveDtls");
		HrLeaveEmpDtls hrLeaveEmpDtls=empLeaveDao.read(leaveId);
		if(hrLeaveEmpDtls!=null)
		
		hrLeaveEmpDtls.setLeaveAvailBal(leaveVO.getLeaveAvailBal());
		hrLeaveEmpDtls.setLeaveTaken(leaveVO.getLeaveTaken());
		hrLeaveEmpDtls.setHrEssLeaveMst(leaveVO.getHrEssLeaveMst());
		hrLeaveEmpDtls.setLeaveFromDate(leaveVO.getLeaveFromDate());
		hrLeaveEmpDtls.setLeaveToDate(leaveVO.getLeaveToDate());
		
		//set if is deleted
		hrLeaveEmpDtls.setIsDeleted(leaveVO.getIsDeleted());
		
		hrLeaveEmpDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
		hrLeaveEmpDtls.setOrgUserMstByUpdatedBy(orgUserMst);				
		hrLeaveEmpDtls.setUpdatedDate(sysdate);
		
		//update vo
		empLeaveDao.update(hrLeaveEmpDtls);
	}
	
	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_LEAVE_EMP_DTLS table.
	 * @param    HrLeaveEmpDtls leaveVO,long userId,long dbId, long postId,long locId,long empLeaveId
	 * @return   void
	 */
	
	public void insertHrEmpLeaveDtls(HrLeaveEmpDtls leaveVO,long userId,long dbId, long postId,long locId,long empLeaveId) throws Exception
	{
		
		Date sysdate = new Date();
		 CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		 logger.info("INSIDE INSERT EmpLeaveServiceHelper insertHrEmpLeaveDtls");
		OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		OrgPostMst orgPostMst=cmn.getOrgPostMst(postId);
		CmnDatabaseMst cmnDatabaseMst = cmn.getCmnDatabaseMst(dbId);
		EmpLeaveDAOImpl empLeaveDao = new EmpLeaveDAOImpl(HrLeaveEmpDtls.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst = cmn.getCmnLocationMst(locId);
		
		leaveVO.setEmpLeaveId(empLeaveId);
		leaveVO.setCmnDatabaseMst(cmnDatabaseMst);
		leaveVO.setOrgPostMstByCreatedByPost(orgPostMst);
		leaveVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		leaveVO.setCmnLocationMst(cmnLocationMst);
		leaveVO.setOrgUserMstByCreatedBy(orgUserMst);
		leaveVO.setOrgUserMstByUpdatedBy(orgUserMst);
		leaveVO.setCreatedDate(sysdate);
		leaveVO.setTrnCounter(new Integer(1));
		
		
		empLeaveDao.create(leaveVO);
	

	}
	
	
	

}
