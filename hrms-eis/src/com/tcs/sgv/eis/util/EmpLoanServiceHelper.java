package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.LoanEmpIntRecvDAOImpl;
import com.tcs.sgv.eis.dao.LoanEmpPrinRecvDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * Created by Japen Pathak for for inserting / updating data in HR_LOAN_EMP_DTLS,HR_LOAN_EMP_INT_RECOVER_DTLS,HR_LOAN_EMP_PRIN_RECOVER_DTLS tables.
 */

public class EmpLoanServiceHelper{
	
	ServiceLocator serv;
	public final Log logger = LogFactory.getLog(getClass());
	public EmpLoanServiceHelper() {
		// TODO Auto-generated constructor stub
	}
	
	
	public EmpLoanServiceHelper(ServiceLocator serv)
	{
		this.serv = serv;
	}
	
	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will update data in HR_LOAN_EMP_DTLS table.
	 * @param    HrLoanEmpDtls hrPayEmpLoan,long postId,long userId
	 * @return   HrLoanEmpDtls
	 */
	
	public HrLoanEmpDtls updateEmpLoanDtls(HrLoanEmpDtls hrPayEmpLoan,long postId,long userId) throws Exception
	{
		long loanId=hrPayEmpLoan.getEmpLoanId();
		
		EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
		HrLoanEmpDtls loanEmpVO= empLoanDAO.read(loanId);
		
		if(loanEmpVO!=null)
		{
		 
		//	long empID = loanEmpVO.getHrEisEmpMst().getEmpId();
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		OrgPostMst orgPostMst= cmn.getOrgPostMst(postId);
		OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		Date sysdate = new Date();
		logger.info("INSIDE INSERT EmpLoanServiceHelper updateEmpLoanDtls");
		loanEmpVO.setHrLoanEmpIntRecoverDtlses(hrPayEmpLoan.getHrLoanEmpIntRecoverDtlses());
		loanEmpVO.setHrLoanEmpPrinRecoverDtlses(hrPayEmpLoan.getHrLoanEmpPrinRecoverDtlses());
		loanEmpVO.setLoanAccountNo(hrPayEmpLoan.getLoanAccountNo());
		loanEmpVO.setLoanSancOrderNo(hrPayEmpLoan.getLoanSancOrderNo());
		loanEmpVO.setLoanSancOrderdate(hrPayEmpLoan.getLoanSancOrderdate());
		loanEmpVO.setLoanDate(hrPayEmpLoan.getLoanDate());
		//loanEmpVO.setLoanEmiAmt(hrPayEmpLoan.getLoanEmiAmt());
		loanEmpVO.setLoanPrinEmiAmt(hrPayEmpLoan.getLoanPrinEmiAmt());
		loanEmpVO.setLoanIntEmiAmt(hrPayEmpLoan.getLoanIntEmiAmt());
		loanEmpVO.setLoanInterestAmt(hrPayEmpLoan.getLoanInterestAmt());
		loanEmpVO.setLoanIntInstNo(hrPayEmpLoan.getLoanIntInstNo());
		loanEmpVO.setLoanPrinAmt(hrPayEmpLoan.getLoanPrinAmt());
		loanEmpVO.setLoanPrinInstNo(hrPayEmpLoan.getLoanPrinInstNo());
		loanEmpVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		loanEmpVO.setOrgUserMstByUpdatedBy(orgUserMst);
		loanEmpVO.setUpdatedDate(sysdate);
		loanEmpVO.setLoanActivateFlag(hrPayEmpLoan.getLoanActivateFlag());
		loanEmpVO.setVoucherNo(hrPayEmpLoan.getVoucherNo());
		loanEmpVO.setVoucherDate(hrPayEmpLoan.getVoucherDate());
		//4 jan 2011
		loanEmpVO.setLoanOddinstno(hrPayEmpLoan.getLoanOddinstno());
		loanEmpVO.setLoanOddinstAmt(hrPayEmpLoan.getLoanOddinstAmt());
		//end 04 jan 2011
		
		loanEmpVO.setMulLoanAmtRecvd(hrPayEmpLoan.getMulLoanAmtRecvd());
		loanEmpVO.setMulLoanInstRecvd(hrPayEmpLoan.getMulLoanInstRecvd());
		loanEmpVO.setMulLoanRecoveryMode(hrPayEmpLoan.getMulLoanRecoveryMode());
		loanEmpVO.setMulLoanRecRemarks(hrPayEmpLoan.getMulLoanRecRemarks());
		loanEmpVO.setOrderDate(hrPayEmpLoan.getOrderDate());
		loanEmpVO.setOrderno(hrPayEmpLoan.getOrderno());
		empLoanDAO.setSessionFactory(serv.getSessionFactory());
		empLoanDAO.update(loanEmpVO);
		
		}
		return loanEmpVO;
	}
	
	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will update data in HR_LOAN_EMP_INT_RECOVER_DTLS table.
	 * @param    HrLoanEmpDtls loanEmpVO,HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls,long postId,long userId
	 * @return   void
	 */
	
	public void updateLoanEmpIntRecvDAO(HrLoanEmpDtls loanEmpVO,HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls,long postId,long userId)
	  throws Exception 
	{
		LoanEmpIntRecvDAOImpl loanEmpIntRecvDAO= new LoanEmpIntRecvDAOImpl (HrLoanEmpIntRecoverDtls.class,serv.getSessionFactory());
	
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		OrgPostMst orgPostMst= cmn.getOrgPostMst(postId);
		OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		Date sysdate = new Date();
		
		List lstEmpLoanIntRecverDtls = new ArrayList();
		lstEmpLoanIntRecverDtls = loanEmpIntRecvDAO.getListByColumnAndValue("hrLoanEmpDtls",loanEmpVO);
		if(lstEmpLoanIntRecverDtls!=null && !lstEmpLoanIntRecverDtls.isEmpty())
		{
			logger.info("INSIDE INSERT EmpLoanServiceHelper updateLoanEmpIntRecvDAO");
			HrLoanEmpIntRecoverDtls objHrLoanIntRcvDtls = (HrLoanEmpIntRecoverDtls)lstEmpLoanIntRecverDtls.get(0);
			objHrLoanIntRcvDtls.setTotalRecoveredInt(hrLoanEmpIntRecoverDtls.getTotalRecoveredInt());
			objHrLoanIntRcvDtls.setTotalRecoveredIntInst(hrLoanEmpIntRecoverDtls.getTotalRecoveredIntInst());
			objHrLoanIntRcvDtls.setUpdatedDate(sysdate);
			objHrLoanIntRcvDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			hrLoanEmpIntRecoverDtls.setOrgUserMstByUpdatedBy(orgUserMst);
			loanEmpIntRecvDAO.update(objHrLoanIntRcvDtls);
		
		}

	}
	
	
	

	//
	
	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_LOAN_EMP_DTLS table.
	 * @param    HrLoanEmpDtls hrPayEmpLoan,long loanTypeId,long empId,long postId,long langId,long locId,long dbId,long userId
	 * @return   HrLoanEmpDtls
	 */

	
	
	
	
	
	public HrLoanEmpDtls insertHrLoanEmpDtls(HrLoanEmpDtls hrPayEmpLoan,long loanTypeId,long empId,long postId,long langId,long locId,long dbId,long userId)
	  throws Exception 
	{
		
		EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
		 GenericDaoHibernateImpl gImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
		 gImpl.setSessionFactory(serv.getSessionFactory());
		 HrEisEmpMst hrEmp = new HrEisEmpMst();
		 CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		 CmnDatabaseMst cmnDatabaseMst =cmn.getCmnDatabaseMst(dbId);
		 OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
		 CmnLocationMst cmnLocationMst =cmn.getCmnLocationMst(locId);
		 OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		 Date sysdate = new Date();
		 
		 
		 
		 
		 EmpDAOImpl empDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
		 OrgEmpMst orgEmpMst = (OrgEmpMst) empDao.read(empId);
		 orgEmpMst = empDao.getEngGujEmployee(orgEmpMst, langId)	;
			
			
		 
	    List<HrEisEmpMst> hrEmpList =  gImpl.getListByColumnAndValue("orgEmpMst", orgEmpMst);
	    hrEmp = hrEmpList.get(0);
		
	    IdGenerator idGen = new IdGenerator();
		Long id= idGen.PKGeneratorWebService("hr_loan_emp_dtls", serv, userId, langId, locId)	;
	     
		HrLoanAdvMst hrLoanObj=new HrLoanAdvMst();
        
        gImpl = new GenericDaoHibernateImpl(HrLoanAdvMst.class);
        gImpl.setSessionFactory(serv.getSessionFactory());
        hrLoanObj = (HrLoanAdvMst)gImpl.read(loanTypeId); 
    	logger.info("INSIDE INSERT EmpLoanServiceHelper insertHrLoanEmpDtls");
        
        hrPayEmpLoan.setEmpLoanId(id);
		hrPayEmpLoan.setHrEisEmpMst(hrEmp);
		hrPayEmpLoan.setHrLoanAdvMst(hrLoanObj);
		hrPayEmpLoan.setCmnDatabaseMst(cmnDatabaseMst);
		hrPayEmpLoan.setOrgPostMstByUpdatedByPost(orgPostMst);
		hrPayEmpLoan.setOrgPostMstByCreatedByPost(orgPostMst);
		hrPayEmpLoan.setCmnLocationMst(cmnLocationMst);
		hrPayEmpLoan.setOrgUserMstByUpdatedBy(orgUserMst);
		hrPayEmpLoan.setOrgUserMstByCreatedBy(orgUserMst);
		hrPayEmpLoan.setCreatedDate(sysdate);
		hrPayEmpLoan.setTrnCounter(new Integer(1));
		hrPayEmpLoan.setLoanActivateFlag(new Integer(1));
		empLoanDAO.create(hrPayEmpLoan);
		return hrPayEmpLoan;
		
	}


	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_LOAN_EMP_PRIN_RECOVER_DTLS table.
	 * @param    HrLoanEmpDtls hrPayEmpLoan,HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls,long dbId, long langId,long postId, long locId, long userId
	 * @return   void
	 */
	
	public void insertHrLoanEmpPrinRecoverDtls(HrLoanEmpDtls hrPayEmpLoan,HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls,long dbId, long langId,long postId, long locId, long userId)
	  throws Exception 
	{
		 CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		 CmnDatabaseMst cmnDatabaseMst =cmn.getCmnDatabaseMst(dbId);
		 OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
		 CmnLocationMst cmnLocationMst =cmn.getCmnLocationMst(locId);
		 OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		 Date sysdate = new Date();
		
		 IdGenerator idGen = new IdGenerator();
		long prinId= idGen.PKGeneratorWebService("hr_loan_emp_prin_recover_dtls", serv, userId, langId, locId);
		logger.info("INSIDE INSERT EmpLoanServiceHelper insertHrLoanEmpPrinRecoverDtls");
		LoanEmpPrinRecvDAOImpl loanEmpPrinRecvDAO= new LoanEmpPrinRecvDAOImpl (HrLoanEmpPrinRecoverDtls.class,serv.getSessionFactory());
		hrLoanEmpPrinRecoverDtls.setPrinRecoverId(prinId);
		hrLoanEmpPrinRecoverDtls.setHrLoanEmpDtls(hrPayEmpLoan);
		hrLoanEmpPrinRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
		hrLoanEmpPrinRecoverDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
		hrLoanEmpPrinRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
		hrLoanEmpPrinRecoverDtls.setCmnLocationMst(cmnLocationMst);
		hrLoanEmpPrinRecoverDtls.setOrgUserMstByUpdatedBy(orgUserMst);
		hrLoanEmpPrinRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
		hrLoanEmpPrinRecoverDtls.setCreatedDate(sysdate);
		hrLoanEmpPrinRecoverDtls.setTrnCounter(new Integer(1));
		loanEmpPrinRecvDAO.create(hrLoanEmpPrinRecoverDtls);
	}

	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_LOAN_EMP_INT_RECOVER_DTLS table.
	 * @param    HrLoanEmpDtls hrPayEmpLoan,HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls,long dbId, long langId,long postId, long locId, long userId
	 * @return   void
	 */
	
	public void insertHrLoanEmpIntRecoverDtls(HrLoanEmpDtls hrPayEmpLoan,HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls,long dbId, long langId,long postId, long locId, long userId )
	  throws Exception 
	{
		 IdGenerator idGen = new IdGenerator();
			long intId= idGen.PKGeneratorWebService("hr_loan_emp_int_recover_dtls", serv, userId, langId, locId)	;
			
			CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
			 CmnDatabaseMst cmnDatabaseMst =cmn.getCmnDatabaseMst(dbId);
			 OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
			 CmnLocationMst cmnLocationMst =cmn.getCmnLocationMst(locId);
			 OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
			 Date sysdate = new Date();
			 logger.info("INSIDE INSERT EmpLoanServiceHelper insertHrLoanEmpIntRecoverDtls");
		LoanEmpIntRecvDAOImpl loanEmpIntRecvDAO= new LoanEmpIntRecvDAOImpl (HrLoanEmpIntRecoverDtls.class,serv.getSessionFactory());
		hrLoanEmpIntRecoverDtls.setIntRecoverId(intId);
		hrLoanEmpIntRecoverDtls.setHrLoanEmpDtls(hrPayEmpLoan);
		hrLoanEmpIntRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
		hrLoanEmpIntRecoverDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
		hrLoanEmpIntRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
		hrLoanEmpIntRecoverDtls.setCmnLocationMst(cmnLocationMst);
		hrLoanEmpIntRecoverDtls.setOrgUserMstByUpdatedBy(orgUserMst);
		hrLoanEmpIntRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
		hrLoanEmpIntRecoverDtls.setCreatedDate(sysdate);
		hrLoanEmpIntRecoverDtls.setTrnCounter(new Integer(1));
		
		loanEmpIntRecvDAO.create(hrLoanEmpIntRecoverDtls);

	}
	/**
	 * @Author	: Varshil
	 * @Date	: 14/03/2012 
	 * Function	: This method will update data in HR_LOAN_EMP_PRIN_RECOVER_DTLS table.
	 * @param    HrLoanEmpDtls hrPayEmpLoan,HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls,long postId, long userId
	 * @return   void
	 */
	
	public void updateLoanEmpPrinRecvDAO(HrLoanEmpDtls loanEmpVO,HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls,long postId,long userId)
	  throws Exception 
	{
		LoanEmpPrinRecvDAOImpl loanEmpPrinRecvDAO= new LoanEmpPrinRecvDAOImpl (HrLoanEmpPrinRecoverDtls.class,serv.getSessionFactory());
	
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		OrgPostMst orgPostMst= cmn.getOrgPostMst(postId);
		OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		Date sysdate = new Date();
		
		List lstEmpLoanIntRecverDtls = new ArrayList();
		lstEmpLoanIntRecverDtls = loanEmpPrinRecvDAO.getListByColumnAndValue("hrLoanEmpDtls",loanEmpVO);
		if(lstEmpLoanIntRecverDtls!=null && !lstEmpLoanIntRecverDtls.isEmpty())
		{
			logger.info("INSIDE INSERT EmpLoanServiceHelper updateLoanEmpPrinRecvDAO");
			HrLoanEmpPrinRecoverDtls objHrLoanPrinRcvDtls = (HrLoanEmpPrinRecoverDtls)lstEmpLoanIntRecverDtls.get(0);
			objHrLoanPrinRcvDtls.setTotalRecoveredAmt(hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt());
			objHrLoanPrinRcvDtls.setTotalRecoveredInst(hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst());
			objHrLoanPrinRcvDtls.setUpdatedDate(sysdate);
			objHrLoanPrinRcvDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			hrLoanEmpPrinRecoverDtls.setOrgUserMstByUpdatedBy(orgUserMst);
			loanEmpPrinRecvDAO.update(objHrLoanPrinRcvDtls);
		
		}

	}

	/**
	 * @return the serv
	 */
	public ServiceLocator getServ() {
		return serv;
	}

	/**
	 * @param serv the serv to set
	 */
	public void setServ(ServiceLocator serv) {
		this.serv = serv;
	}

}