/********************This service will act as interface between payroll and other applications like
 ********  home or IWDMS for Loans and Advances ****************************************/

package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.LoanEmpIntRecvDAOImpl;
import com.tcs.sgv.eis.dao.LoanEmpPrinRecvDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
@Param:Entry

empId	HrEisEmpMst
LoanTypeId	HrLoanAdvMst
LoanPrinAmt	hrLoanEmpDtls
LoanIntAmt	hrLoanEmpDtls
LoanPrinInstNo	hrLoanEmpDtls
LoanIntInstNo	hrLoanEmpDtls
LoanAcNo	hrLoanEmpDtls
LoanDate	hrLoanEmpDtls
LoanIntEmiAmt	hrLoanEmpDtls
LoanPrinEmiAmt	hrLoanEmpDtls
LoanSancOrderNo	hrLoanEmpDtls
LoanActivateFlag	hrLoanEmpDtls
LoanPrinRecovAmt	hrLoanEmpPrinRecoverDtls
LoanPrinRecovInt	hrLoanEmpPrinRecoverDtls
LoanIntRecovAmt	hrLoanEmpIntRecoverDtls
LoanIntRecovInt	hrLoanEmpIntRecoverDtls

@Param:Update

edit	set as "Y"
empLoan	loanVO of HrLoanEmpDtls
loanRecv	hrLoanEmpPrinRecoverDtls
loanIntRecv	hrLoanEmpIntRecoverDtls

 **/


public class PayrollIntegrationLoansAndAdvances extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	public ResultObject PayrollIntegrationLoanEntry(Map objectArgs) {
		{


			logger.info("-------------inside PayrollIntegrationLoanEntry-------------");
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	

			Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");

			try{
				
				EmpInfoDAOImpl empInfoDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
				LoanEmpPrinRecvDAOImpl loanEmpPrinRecvDAO= new LoanEmpPrinRecvDAOImpl (HrLoanEmpPrinRecoverDtls.class,serv.getSessionFactory());
				LoanEmpIntRecvDAOImpl loanEmpIntRecvDAO= new LoanEmpIntRecvDAOImpl (HrLoanEmpIntRecoverDtls.class,serv.getSessionFactory());
                int loanActivateFlag = Integer.parseInt((objectArgs.get("LoanActivateFlag")!=null?objectArgs.get("LoanActivateFlag").toString():"0"));

				Date sysdate = new Date();

				String editFromVO = objectArgs.get("edit")!=null?objectArgs.get("edit").toString():"N";
				logger.info("editFromVO " + editFromVO);

				if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y")||loanActivateFlag==0)
				{
					long loanTypeId=StringUtility.convertToLong(objectArgs.get("LoanTypeId").toString());
					GenericDaoHibernateImpl gImpl = null;

					HrLoanAdvMst hrLoanObj=new HrLoanAdvMst();
					gImpl = new GenericDaoHibernateImpl(HrLoanAdvMst.class);
					gImpl.setSessionFactory(serv.getSessionFactory());
					hrLoanObj = (HrLoanAdvMst)gImpl.read(loanTypeId); 

					long empId = Long.parseLong((objectArgs.get("empId")!=null?objectArgs.get("empId").toString():"0"));
					
					HrEisEmpMst hrEisEmpMst = empInfoDAO.read(empId);	
					
					gImpl = new GenericDaoHibernateImpl(HrLoanEmpDtls.class);
					gImpl.setSessionFactory(serv.getSessionFactory());
					
					String cols[] = {"hrEisEmpMst.empId","hrLoanAdvMst.loanAdvId","loanAccountNo"}; 
					Object values[] = {empId,hrLoanObj.getLoanAdvId(),(objectArgs.get("LoanAcNo")!=null?objectArgs.get("LoanAcNo").toString():"")}; 
					
					List<HrLoanEmpDtls> HrLoanEmpDtlsList = gImpl.getListByColumnAndValue(cols, values);
					
					if(HrLoanEmpDtlsList!=null && HrLoanEmpDtlsList.size()==1)
					{	
					HrLoanEmpDtls hrPayEmpLoan = HrLoanEmpDtlsList.get(0);
					hrPayEmpLoan.setLoanActivateFlag(0);
					
					empLoanDAO.update(hrPayEmpLoan);
					}

				}
				else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
				{
					
					HrLoanEmpDtls hrPayEmpLoan = new HrLoanEmpDtls();
					HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls = new HrLoanEmpPrinRecoverDtls();
					HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls = new HrLoanEmpIntRecoverDtls();

					hrPayEmpLoan.setLoanPrinAmt(Long.parseLong((objectArgs.get("LoanPrinAmt")!=null?objectArgs.get("LoanPrinAmt").toString():"0")));
					hrPayEmpLoan.setLoanInterestAmt(Long.parseLong((objectArgs.get("LoanIntAmt")!=null?objectArgs.get("LoanIntAmt").toString():"0")));
					hrPayEmpLoan.setLoanPrinInstNo(Long.parseLong((objectArgs.get("LoanPrinInstNo")!=null?objectArgs.get("LoanPrinInstNo").toString():"0")));
					hrPayEmpLoan.setLoanIntInstNo(Long.parseLong((objectArgs.get("LoanIntInstNo")!=null?objectArgs.get("LoanIntInstNo").toString():"0")));
					hrPayEmpLoan.setLoanPrinEmiAmt(Long.parseLong((objectArgs.get("LoanPrinEmiAmt")!=null?objectArgs.get("LoanPrinEmiAmt").toString():"0")));
					hrPayEmpLoan.setLoanIntEmiAmt(Long.parseLong((objectArgs.get("LoanPrinEmiAmt")!=null?objectArgs.get("LoanPrinEmiAmt").toString():"0")));
					hrPayEmpLoan.setLoanAccountNo((objectArgs.get("LoanAcNo")!=null?objectArgs.get("LoanAcNo").toString():""));
					hrPayEmpLoan.setLoanSancOrderNo((objectArgs.get("LoanSancOrderNo")!=null?objectArgs.get("LoanSancOrderNo").toString():"0"));
					hrPayEmpLoan.setLoanDate((Date)(objectArgs.get("LoanDate")!=null?objectArgs.get("LoanDate"):new Date()));
					
					hrPayEmpLoan.setLoanActivateFlag(loanActivateFlag);
					
					hrLoanEmpPrinRecoverDtls.setTotalRecoveredAmt(Long.parseLong((objectArgs.get("LoanPrinRecovAmt")!=null?objectArgs.get("LoanPrinRecovAmt").toString():"0")));
					hrLoanEmpPrinRecoverDtls.setTotalRecoveredInst(Long.parseLong((objectArgs.get("LoanPrinRecovInt")!=null?objectArgs.get("LoanPrinRecovInt").toString():"0")));
					hrLoanEmpIntRecoverDtls.setTotalRecoveredInt(Long.parseLong((objectArgs.get("LoanIntRecovAmt")!=null?objectArgs.get("LoanIntRecovAmt").toString():"0")));
					hrLoanEmpIntRecoverDtls.setTotalRecoveredIntInst(Long.parseLong((objectArgs.get("LoanIntRecovInt")!=null?objectArgs.get("LoanIntRecovInt").toString():"0")));

					long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

					
					logger.info("recv recovered amt---->"+hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt());
					logger.info("recv recovered inst----->"+hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst());
					logger.info("loan Account No is-------->"+hrPayEmpLoan.getLoanAccountNo());



					long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
					OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
					OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

					long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
					CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
					CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

					CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
					langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
					CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
					CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);

					long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
					OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
					OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
					
					long loanTypeId=StringUtility.convertToLong(objectArgs.get("LoanTypeId").toString());
					GenericDaoHibernateImpl gImpl = null;

					HrLoanAdvMst hrLoanObj=new HrLoanAdvMst();
					gImpl = new GenericDaoHibernateImpl(HrLoanAdvMst.class);
					gImpl.setSessionFactory(serv.getSessionFactory());
					hrLoanObj = (HrLoanAdvMst)gImpl.read(loanTypeId); 

					long empId = Long.parseLong((objectArgs.get("empId")!=null?objectArgs.get("empId").toString():"0"));
					
					HrEisEmpMst hrEisEmpMst = empInfoDAO.read(empId);	
					
					hrPayEmpLoan.setHrEisEmpMst(hrEisEmpMst);
					hrPayEmpLoan.setHrLoanAdvMst(hrLoanObj);

					IdGenerator idGen = new IdGenerator();
					Long id= idGen.PKGenerator("hr_loan_emp_dtls",objectArgs);				
					hrPayEmpLoan.setEmpLoanId(id);

					Long prinId=idGen.PKGenerator("hr_loan_emp_prin_recover_dtls",objectArgs);
					hrLoanEmpPrinRecoverDtls.setPrinRecoverId(prinId);
					hrLoanEmpPrinRecoverDtls.setHrLoanEmpDtls(hrPayEmpLoan);

					Long intId=idGen.PKGenerator("hr_loan_emp_int_recover_dtls",objectArgs);
					hrLoanEmpIntRecoverDtls.setIntRecoverId(intId);
					hrLoanEmpIntRecoverDtls.setHrLoanEmpDtls(hrPayEmpLoan);

					hrPayEmpLoan.setCmnDatabaseMst(cmnDatabaseMst);
					hrPayEmpLoan.setOrgPostMstByUpdatedByPost(orgPostMst);
					hrPayEmpLoan.setOrgPostMstByCreatedByPost(orgPostMst);
					hrPayEmpLoan.setCmnLocationMst(cmnLocationMst);
					hrPayEmpLoan.setOrgUserMstByUpdatedBy(orgUserMst);
					hrPayEmpLoan.setOrgUserMstByCreatedBy(orgUserMst);
					hrPayEmpLoan.setCreatedDate(sysdate);
					hrPayEmpLoan.setTrnCounter(new Integer(1));
					hrPayEmpLoan.setLoanActivateFlag(new Integer(1));

					hrLoanEmpPrinRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrLoanEmpPrinRecoverDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
					hrLoanEmpPrinRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					hrLoanEmpPrinRecoverDtls.setCmnLocationMst(cmnLocationMst);
					hrLoanEmpPrinRecoverDtls.setOrgUserMstByUpdatedBy(orgUserMst);
					hrLoanEmpPrinRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
					hrLoanEmpPrinRecoverDtls.setCreatedDate(sysdate);
					hrLoanEmpPrinRecoverDtls.setTrnCounter(new Integer(1));

					hrLoanEmpIntRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrLoanEmpIntRecoverDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
					hrLoanEmpIntRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					hrLoanEmpIntRecoverDtls.setCmnLocationMst(cmnLocationMst);
					hrLoanEmpIntRecoverDtls.setOrgUserMstByUpdatedBy(orgUserMst);
					hrLoanEmpIntRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
					hrLoanEmpIntRecoverDtls.setCreatedDate(sysdate);
					hrLoanEmpIntRecoverDtls.setTrnCounter(new Integer(1));

					empLoanDAO.create(hrPayEmpLoan);
					loanEmpPrinRecvDAO.create(hrLoanEmpPrinRecoverDtls);
					loanEmpIntRecvDAO.create(hrLoanEmpIntRecoverDtls);

				}	

				resultObject.setResultValue(objectArgs);
				logger.info("INSERTED SUCCESSFULLY");


			}
			catch(ConstraintViolationException ex)
			{
				logger.info("TransactionSystemException occurs...");
			}
			catch(Exception e){
				logger.info("The error is:-");
				logger.error("Error is: "+ e.getMessage());
				logger.info("There is some error at editting or inserting time");
				objectArgs.put("MESSAGECODE",300001);
				resultObject.setResultValue(objectArgs);
				resultObject.setThrowable(e);
				resultObject.setResultCode(-1);
				resultObject.setViewName("errorPage");

			}
			return resultObject;

		}

	}
}
