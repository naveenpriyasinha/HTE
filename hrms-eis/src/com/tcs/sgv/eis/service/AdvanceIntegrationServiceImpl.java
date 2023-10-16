package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import oracle.net.TNSAddress.Description;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.LoanEmpIntRecvDAOImpl;
import com.tcs.sgv.eis.dao.LoanEmpPrinRecvDAOImpl;
import com.tcs.sgv.eis.util.CommomnDataObjectFatch;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class AdvanceIntegrationServiceImpl extends ServiceImpl {
	
	
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	
	/**
	 * <h1>This Method will be called when PF Advances will be approved from PF side </h1>
	 * @author Amish 
	 * @param Map inputMap
	 * @return Map resultvalue
	 */
	
	
	public ResultObject insertIntegratedPFAdvances(Map inputMap) throws Exception {
		
		logger.info("-------------inside insertIntegratedPFAdvances-------------");
		
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		
		
		//Map inputMap = (Map) inputMap.get("inputMap");
		Map payrollServiceMap = (Map) inputMap.get("payrollServiceMap");
		
		Map loginDetailsMap =(Map)inputMap.get("baseLoginMap");
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		long locId = cmnLocationMst.getLocId();
		
		long langId	=0;
		long userId = 0;
		long dbId=0;
		long postId=0;
		
		try
		{
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			
		}
		catch(Exception e)
		{
			logger.info("Catched Exception::::"+e.getMessage());
			
		}
		
		try{
			
			CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
			CmnDatabaseMst cmnDatabaseMst =cmn.getCmnDatabaseMst(dbId);
			OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
			OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
			Date sysdate = new Date();
			
			long orgEmpId = 0;
			long eisEmpId = 0;
			long advTypeId = 0;
			String advDateStr = "";
			long advSancOrderNo = 0;
			String advSancOrderDateStr = "";
			long principalAmount = 0;
			long installmentNo = 0;
			long installmentEMI = 0;
			long recoveredInstallment = 0;
			long recoveredAmount = 0;
			long oddInstallment = 0;
			long oddInstallmentAmount = 0;
			String advExistFlagStr = "";
			int advActivateFlag = 1;
			long advVoucherNo = 0;
			String advVoucherDateStr = "";
			String advAccountNo = "";
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
					
			orgEmpId=(payrollServiceMap.get("orgEmpId")!=null && !(payrollServiceMap.get("orgEmpId").toString().equals(""))?Long.parseLong(payrollServiceMap.get("orgEmpId").toString()):0);
			logger.info("orgEmpId ----->"+orgEmpId);
			advTypeId=(payrollServiceMap.get("advTypeId")!=null && !(payrollServiceMap.get("advTypeId").toString().equals(""))?Long.parseLong(payrollServiceMap.get("advTypeId").toString()):0);
			logger.info("advTypeId----->"+advTypeId);
			advAccountNo=(payrollServiceMap.get("advAccountNo")!=null && !(payrollServiceMap.get("advAccountNo").toString().equals(""))?(payrollServiceMap.get("advAccountNo")):" ").toString();
			logger.info("===> advAccountNo :: "+advAccountNo);
			principalAmount=(payrollServiceMap.get("principalAmount")!=null && !(payrollServiceMap.get("principalAmount").toString().equals(""))?Long.parseLong(payrollServiceMap.get("principalAmount").toString()):0);
			logger.info("===> principalAmount :: "+principalAmount);
			installmentNo=(payrollServiceMap.get("installmentNo")!=null && !(payrollServiceMap.get("installmentNo").toString().equals(""))?Long.parseLong(payrollServiceMap.get("installmentNo").toString()):0);
			logger.info("===> installmentNo :: "+installmentNo);
			installmentEMI=(payrollServiceMap.get("installmentEMI")!=null && !(payrollServiceMap.get("installmentEMI").toString().equals(""))?Long.parseLong(payrollServiceMap.get("installmentEMI").toString()):0);
			logger.info("installmentEMI----->"+installmentEMI);
			recoveredInstallment=(payrollServiceMap.get("recoveredInstallment")!=null && !(payrollServiceMap.get("recoveredInstallment").toString().equals(""))?Long.parseLong(payrollServiceMap.get("recoveredInstallment").toString()):0);
			logger.info("recoveredInstallment----->"+recoveredInstallment);
			recoveredAmount=(payrollServiceMap.get("recoveredAmount")!=null && !(payrollServiceMap.get("recoveredAmount").toString().equals(""))?Long.parseLong(payrollServiceMap.get("recoveredAmount").toString()):0);
			logger.info("recoveredAmount----->"+recoveredAmount);
			oddInstallment=(payrollServiceMap.get("oddInstallment")!=null && !(payrollServiceMap.get("oddInstallment").toString().equals(""))?Long.parseLong(payrollServiceMap.get("oddInstallment").toString()):0);
			logger.info("oddInstallment----->"+oddInstallment);
			oddInstallmentAmount=(payrollServiceMap.get("oddInstallmentAmount")!=null && !(payrollServiceMap.get("oddInstallmentAmount").toString().equals(""))?Long.parseLong(payrollServiceMap.get("oddInstallmentAmount").toString()):0);
			logger.info("oddInstallmentAmount----->"+oddInstallmentAmount);			
			advDateStr=(payrollServiceMap.get("advDate")!=null && !(payrollServiceMap.get("advDate").toString().equals(""))?(payrollServiceMap.get("advDate")):" ").toString();
			logger.info("advDateStr----->"+advDateStr);
			advSancOrderNo=(payrollServiceMap.get("advSancOrderNo")!=null && !(payrollServiceMap.get("advSancOrderNo").toString().equals(""))?Long.parseLong(payrollServiceMap.get("advSancOrderNo").toString()):0);
			logger.info("advSancOrderNo----->"+advSancOrderNo);
			advSancOrderDateStr=(payrollServiceMap.get("advSancOrderDate")!=null && !(payrollServiceMap.get("advSancOrderDate").toString().equals(""))?(payrollServiceMap.get("advSancOrderDate")):" ").toString();
			logger.info("advSancOrderDateStr----->"+advSancOrderDateStr);
			advVoucherNo=(payrollServiceMap.get("advVoucherNo")!=null && !(payrollServiceMap.get("advVoucherNo").toString().equals(""))?Long.parseLong(payrollServiceMap.get("advVoucherNo").toString()):0);
			logger.info("advVoucherNo ----->"+advVoucherNo);
			advVoucherDateStr=(payrollServiceMap.get("advVoucherDate")!=null && !(payrollServiceMap.get("advVoucherDate").toString().equals(""))?(payrollServiceMap.get("advVoucherDate")):" ").toString();
			logger.info("advVoucherDateStr----->"+advVoucherDateStr);
			advExistFlagStr=(payrollServiceMap.get("advExistFlag")!=null && !(payrollServiceMap.get("advExistFlag").toString().equals(""))?(payrollServiceMap.get("advExistFlag")):" ").toString();
			logger.info("advExistFlagStr----->"+advExistFlagStr);
					
			Date advDate=sdf.parse(advDateStr);
			logger.info("advDateStr after Parsing----->"+advDate);
			Date advSancOrderDate=sdf.parse(advSancOrderDateStr);
			logger.info("advSancOrderDateStr after Parsing----->"+advSancOrderDate);
			Date advVoucherDate=sdf.parse(advVoucherDateStr);
			logger.info("advVoucherDate after Parsing----->"+advVoucherDate);
			    	        
	        
	        GenericDaoHibernateImpl gImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
			gImpl.setSessionFactory(serv.getSessionFactory());
			
	        EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
	        EmpDAOImpl empDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
	        EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
	        
	        HrLoanEmpDtls hrLoanEmpDtls = new HrLoanEmpDtls();
			HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls = new HrLoanEmpPrinRecoverDtls();
			HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls = new HrLoanEmpIntRecoverDtls();
			
			
			OrgEmpMst orgEmpMst = null;
			HrEisEmpMst hrEisEmpMst = null;
			
			if(orgEmpId != 0)
			{
				orgEmpMst = new OrgEmpMst();
				orgEmpMst = (OrgEmpMst) empDao.read(orgEmpId);
			
			
			List<HrEisEmpMst> hrEmpList =  gImpl.getListByColumnAndValue("orgEmpMst", orgEmpMst);
			logger.info("Size of Emp List---"+hrEmpList.size());
			hrEisEmpMst = hrEmpList.get(0);
			logger.info("Employee MST Emp Id--"+hrEisEmpMst.getEmpId());
			
			}
			HrLoanAdvMst hrLoanAdvMst=new HrLoanAdvMst();
	        if(advTypeId != 0)
	        {
	        	gImpl = new GenericDaoHibernateImpl(HrLoanAdvMst.class);
	        	gImpl.setSessionFactory(serv.getSessionFactory());
	        	hrLoanAdvMst = (HrLoanAdvMst)gImpl.read(advTypeId);
	        }
	        
	        if(hrEisEmpMst != null && advTypeId != 0 )
			{
	        	advExistFlagStr = "true";
				logger.info("Inside if(hrEisEmpMst != null && advTypeId != 0 )");
				if(advExistFlagStr.equalsIgnoreCase("true") )
				{
					logger.info("It is clubbed so have to deactivate the previous one");
					List<HrLoanEmpDtls> empLoanList = new ArrayList<HrLoanEmpDtls>();
					HrLoanEmpDtls currentActiveLoan = null;
					String advanceStr = advTypeId+"";
					empLoanList = empLoanDAO.getEmpLoanDetail(orgEmpId,advanceStr);
					logger.info("Emp Loan List Size to De Activate"+empLoanList.size());
					logger.info("Emp Loan List Emp Loan Id"+empLoanList.get(0).getEmpLoanId());
					
					
					if(empLoanList != null && empLoanList.size() > 0)
					{
						if(empLoanList.get(0).getEmpLoanId() != 0)
						{
							currentActiveLoan = empLoanDAO.read(empLoanList.get(0).getEmpLoanId());
							currentActiveLoan.setLoanActivateFlag(0);
							empLoanDAO.update(currentActiveLoan);
							logger.info("Loan Deactivated Successfully...");
						}
					}
					advExistFlagStr = "false";
				}
				
				if(advExistFlagStr.equals("false"))
				{
				
					IdGenerator idGen = new IdGenerator();

					Long id= idGen.PKGeneratorWebService("HR_LOAN_EMP_DTLS", serv, userId, langId, locId)	;
					hrLoanEmpDtls.setEmpLoanId(id);
					hrLoanEmpDtls.setHrEisEmpMst(hrEisEmpMst);
					hrLoanEmpDtls.setHrLoanAdvMst(hrLoanAdvMst);
					hrLoanEmpDtls.setLoanPrinAmt(principalAmount);
					hrLoanEmpDtls.setLoanPrinEmiAmt(installmentEMI);
					hrLoanEmpDtls.setLoanPrinInstNo(installmentNo);
					hrLoanEmpDtls.setLoanAccountNo(advAccountNo);
					hrLoanEmpDtls.setIsApproved(0);
					hrLoanEmpDtls.setLoanActivateFlag(advActivateFlag);
					hrLoanEmpDtls.setLoanDate(advDate);
					hrLoanEmpDtls.setLoanOddinstno(oddInstallment);
					hrLoanEmpDtls.setLoanOddinstAmt(oddInstallmentAmount);
					hrLoanEmpDtls.setLoanSancOrderNo(advSancOrderNo+"");
					hrLoanEmpDtls.setLoanSancOrderdate(advSancOrderDate);
					hrLoanEmpDtls.setVoucherDate(advVoucherDate);
					hrLoanEmpDtls.setVoucherNo(advVoucherNo+"");				
					hrLoanEmpDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrLoanEmpDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					hrLoanEmpDtls.setCmnLocationMst(cmnLocationMst);
					hrLoanEmpDtls.setOrgUserMstByCreatedBy(orgUserMst);
					hrLoanEmpDtls.setCreatedDate(sysdate);
					hrLoanEmpDtls.setTrnCounter(new Integer(1));
									

					empLoanDAO.create(hrLoanEmpDtls);
					logger.info("hrLoanEmpDtls INSERTED SUCCESSFULLY******");

					long prinId= idGen.PKGeneratorWebService("HR_LOAN_EMP_PRIN_RECOVER_DTLS", serv, userId, langId, locId);
					LoanEmpPrinRecvDAOImpl loanEmpPrinRecvDAO= new LoanEmpPrinRecvDAOImpl (HrLoanEmpPrinRecoverDtls.class,serv.getSessionFactory());
					hrLoanEmpPrinRecoverDtls.setPrinRecoverId(prinId);
					hrLoanEmpPrinRecoverDtls.setTotalRecoveredAmt(recoveredAmount);
					hrLoanEmpPrinRecoverDtls.setTotalRecoveredInst(recoveredInstallment);
					hrLoanEmpPrinRecoverDtls.setHrLoanEmpDtls(hrLoanEmpDtls);
					hrLoanEmpPrinRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrLoanEmpPrinRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					hrLoanEmpPrinRecoverDtls.setCmnLocationMst(cmnLocationMst);
					hrLoanEmpPrinRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
					hrLoanEmpPrinRecoverDtls.setCreatedDate(sysdate);
					hrLoanEmpPrinRecoverDtls.setTrnCounter(new Integer(1));

					loanEmpPrinRecvDAO.create(hrLoanEmpPrinRecoverDtls);
					logger.info("hrLoanEmpPrinRecoverDtls INSERTED SUCCESSFULLY******");

					long intId= idGen.PKGeneratorWebService("HR_LOAN_EMP_INT_RECOVER_DTLS", serv, userId, langId, locId)	;
					LoanEmpIntRecvDAOImpl loanEmpIntRecvDAO= new LoanEmpIntRecvDAOImpl (HrLoanEmpIntRecoverDtls.class,serv.getSessionFactory());
					hrLoanEmpIntRecoverDtls.setIntRecoverId(intId);
					hrLoanEmpIntRecoverDtls.setHrLoanEmpDtls(hrLoanEmpDtls);
					hrLoanEmpIntRecoverDtls.setTotalRecoveredInt(recoveredAmount);
					hrLoanEmpIntRecoverDtls.setTotalRecoveredIntInst(recoveredInstallment);
					hrLoanEmpIntRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
					hrLoanEmpIntRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					hrLoanEmpIntRecoverDtls.setCmnLocationMst(cmnLocationMst);
					hrLoanEmpIntRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
					hrLoanEmpIntRecoverDtls.setCreatedDate(sysdate);
					hrLoanEmpIntRecoverDtls.setTrnCounter(new Integer(1));

					loanEmpIntRecvDAO.create(hrLoanEmpIntRecoverDtls);
					logger.info("hrLoanEmpIntRecoverDtls INSERTED SUCCESSFULLY******");
					
					msg = 1;
				}
				
			}
			
			   
			resultObject.setResultValue(inputMap);
			logger.info("---------Exit from insertIntegratedPFAdvances ------------ ");
			
			
		}
	     catch(ConstraintViolationException ex)
	     {
	    	 logger.info("TransactionSystemException occurs..."+ex.getMessage());
	     }
		catch(Exception e){
			logger.info("The error is:-"+e.getMessage());
			inputMap.put("MESSAGECODE",300001);
			resultObject.setResultValue(inputMap);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}
	
	/**
	 * @author 366673 Amish
	 * @param inputMap
	 * @return	ResultObject
	 * @throws Exception
	 * @Me
	 */
	
	public ResultObject insertIntegratedPF(Map inputMap) throws Exception
	{
		logger.info("-------------inside insertIntegratedPFSubscription-------------");
				
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		
		//Map inputMap = (Map) inputMap.get("inputMap");
			
		Map loginDetailsMap =(Map)inputMap.get("baseLoginMap");
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		long lLocId = cmnLocationMst.getLocId();
		
		long lLangId	=0;
		long lUserId = 0;
		long lDbId=0;
		long lPostId=0;
		
		try
		{
			lLangId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			lUserId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			lDbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			lPostId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			
		}
		catch(Exception e)
		{
			logger.info("Catched Exception::::"+e.getMessage());
			
		}
		try{
			CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
			CmnDatabaseMst cmnDatabaseMst =cmn.getCmnDatabaseMst(lDbId);
			OrgPostMst orgPostMst = cmn.getOrgPostMst(lPostId);
			OrgUserMst orgUserMst = cmn.getorgUserMst(lUserId);

			IdGenerator generator =new IdGenerator();
			DeductionDtlsDAOImpl deductionDtlsDAOImpl = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			DeducTypeMasterDAOImpl deducTypeMasterDAOImpl=new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			GenericDaoHibernateImpl gImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
			gImpl.setSessionFactory(serv.getSessionFactory());

			EmpDAOImpl empDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());

			Date sysDate = new Date();
			HrPayDeductionDtls oEmpDeducDtls = null;
			OrgEmpMst orgEmpMst = null;
			HrEisEmpMst  hrEisEmpMst = null;
			List<HrEisEmpMst> hrEmpList = null;
			long lDeducCode = 0;
			List lSubscriptionList = null ; 
			long lOrgEmpId = 0;
			long lDeducAmount = 0;
			long lDeducDtlsPK = 0;



			if(inputMap.get("typeId")!=null&&!inputMap.get("typeId").equals(""))
			{     			
				lDeducCode =Long.parseLong(inputMap.get("typeId").toString());
			}
			logger.info("**************deducCode*********"+lDeducCode);
			if(inputMap.get("empList")!=null&&!inputMap.get("empList").equals(""))
			{     			
				lSubscriptionList =(List)inputMap.get("empList");
				logger.info("**************lSubscriptionList size*********"+lSubscriptionList.size());
			}

			if(lSubscriptionList != null && lSubscriptionList.size() > 0 && lDeducCode != 0)
			{
				Iterator lSubListIterator = lSubscriptionList.iterator();
				Object[] oSubscriptionVO  = (Object[]) lSubListIterator.next();

				lOrgEmpId = Long.parseLong(oSubscriptionVO[0].toString());
				lDeducAmount = Long.parseLong(oSubscriptionVO[1].toString());

				if(lOrgEmpId != 0)
				{
					logger.info("Deduction Insertion started for Employee--->"+lOrgEmpId);	
					orgEmpMst = new OrgEmpMst();
					orgEmpMst = (OrgEmpMst) empDao.read(lOrgEmpId);

					hrEmpList =  gImpl.getListByColumnAndValue("orgEmpMst", orgEmpMst);
					logger.info("Size of Emp List---"+hrEmpList.size());
					if(hrEmpList != null && hrEmpList.size() > 0)
						hrEisEmpMst = hrEmpList.get(0);

					if(hrEisEmpMst != null)
					{
						logger.info("Employee MST Emp Id--"+hrEisEmpMst.getEmpId());

						//Checking whether Deduction is already Mapped or Not
						oEmpDeducDtls = deductionDtlsDAOImpl.getHrPayDeductionDtlsByHrEmp(hrEisEmpMst.getEmpId(), lDeducCode, -1, -1);

						if(oEmpDeducDtls != null)
						{
							oEmpDeducDtls.setEmpDeducAmount(lDeducAmount);
							deductionDtlsDAOImpl.update(oEmpDeducDtls);
							logger.info("Subscription Amount Successfully Updated which is --->"+lDeducAmount);
						}
						else
						{
							oEmpDeducDtls = new HrPayDeductionDtls();
							lDeducDtlsPK = generator.PKGenerator("HR_PAY_DEDUCTION_DTLS", inputMap);

							logger.info("Inside Insert Part Primary Key Generated is *******"+lDeducDtlsPK);

							oEmpDeducDtls.setDeducDtlsCode(lDeducDtlsPK);
							oEmpDeducDtls.setEmpDeducAmount(lDeducAmount);
							oEmpDeducDtls.setHrPayDeducTypeMst(deducTypeMasterDAOImpl.read(lDeducCode));
							oEmpDeducDtls.setHrEisEmpMst(hrEisEmpMst);

							oEmpDeducDtls.setEmpCurrentStatus(1);
							oEmpDeducDtls.setCmnDatabaseMst(cmnDatabaseMst);
							oEmpDeducDtls.setCmnLocationMst(cmnLocationMst);
							oEmpDeducDtls.setCreatedDate(new Date());
							oEmpDeducDtls.setUpdatedDate(new Date());
							oEmpDeducDtls.setOrgPostMstByCreatedByPost(orgPostMst);
							oEmpDeducDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
							oEmpDeducDtls.setOrgUserMstByCreatedBy(orgUserMst);
							oEmpDeducDtls.setOrgUserMstByUpdatedBy(orgUserMst);
							oEmpDeducDtls.setTrnCounter(1);
							oEmpDeducDtls.setMonth(-1);
							oEmpDeducDtls.setYear(-1);

							deductionDtlsDAOImpl.create(oEmpDeducDtls);
							logger.info("Deduction Insertion Completed");
						}
					}
				}
			}
		}
		catch(ConstraintViolationException ex)
		{
			logger.info("TransactionSystemException occurs..."+ex.getMessage());
		}
		catch(Exception e){
			logger.info("The error is:-"+e.getMessage());
			inputMap.put("MESSAGECODE",300001);
			resultObject.setResultValue(inputMap);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
			return resultObject;		
	}
}
