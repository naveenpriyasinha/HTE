package com.tcs.sgv.eis.service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.dao.EmpInfoDAO;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.LoanEmpIntRecvDAOImpl;
import com.tcs.sgv.eis.dao.LoanEmpPrinRecvDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.util.EmpLoanServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.eis.valueobject.LoanCustomVO;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.loan.dao.LoanAdvMstDAO;
import com.tcs.sgv.loan.dao.LoanAdvMstDAOImpl;

public class EmpLoanService extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());

	int msg = 0;

	public ResultObject insertEmpLoanDtls(Map objectArgs)
	{

		logger.info("-------------inside insertEmpLoanDtls-------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HrLoanEmpDtls hrPayEmpLoan = (HrLoanEmpDtls) objectArgs.get("empLoan");
		String otherId = objectArgs.get("otherId") != null ? (String) objectArgs.get("otherId") : "";
		String editFromVO = objectArgs.get("edit").toString();
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		String FromBasicDtlsNew = objectArgs.get("FromBasicDtlsNew") != null ? (String) objectArgs.get("FromBasicDtlsNew") : "";
		String empAllRec = "";
		HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls = (HrLoanEmpPrinRecoverDtls) objectArgs.get("loanRecv");
		HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls = (HrLoanEmpIntRecoverDtls) objectArgs.get("loanIntRecv");
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		long locId = cmnLocationMst.getLocId();

		if (request.getParameter("empAllRec") != null)
		{
			empAllRec = request.getParameter("empAllRec").toString();
		}
		long langId = 0;
		long userId = 0;
		long dbId = 0;
		long postId = 0;
		try
		{
			langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());

		}
		catch (Exception e)
		{

		}

		try
		{

			logger.info("added by samir for empAllRec value====>" + empAllRec);
			//added by ravysh

			objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
			objectArgs.put("otherId", otherId);
			long empID = 0;

			logger.info("recv recovered amt---->" + hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt());
			logger.info("recv recovered inst----->" + hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst());
			logger.info("loan Account No is-------->" + hrPayEmpLoan.getLoanAccountNo());

			//EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
			//LoanEmpPrinRecvDAOImpl loanEmpPrinRecvDAO= new LoanEmpPrinRecvDAOImpl (HrLoanEmpPrinRecoverDtls.class,serv.getSessionFactory());
			//LoanEmpIntRecvDAOImpl loanEmpIntRecvDAO= new LoanEmpIntRecvDAOImpl (HrLoanEmpIntRecoverDtls.class,serv.getSessionFactory());

			//OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			//OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

			//CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			//CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

			/*long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	*/

			//CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			//CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			//OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			//OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			//	Date sysdate = new Date();
			//business logic starts here
			logger.info("editFromVO " + editFromVO);

			EmpLoanServiceHelper helper = new EmpLoanServiceHelper(serv);
			//if(request.getParameter("edit")!= null && request.getParameter("edit").equals("Y"))
			if (editFromVO != null && editFromVO.equalsIgnoreCase("Y"))
			{
				logger.info("inside edit mode of EmpLoan--------->");

				HrLoanEmpDtls loanEmpVO = helper.updateEmpLoanDtls(hrPayEmpLoan, postId, userId);

				helper.updateLoanEmpIntRecvDAO(loanEmpVO, hrLoanEmpIntRecoverDtls, postId, userId);
				helper.updateLoanEmpPrinRecvDAO(loanEmpVO, hrLoanEmpPrinRecoverDtls, postId, userId);

				msg = 1;
			}
			else if (editFromVO != null && editFromVO.equalsIgnoreCase("N"))
			{
				long empId = StringUtility.convertToLong(objectArgs.get("empId").toString());
				long loanTypeId = StringUtility.convertToLong(objectArgs.get("loanTypeId").toString());

				logger.info("insert start from here---------->>>>>>>>>>>>>");
				hrPayEmpLoan = helper.insertHrLoanEmpDtls(hrPayEmpLoan, loanTypeId, empId, postId, langId, locId, dbId, userId);

				helper.insertHrLoanEmpIntRecoverDtls(hrPayEmpLoan, hrLoanEmpIntRecoverDtls, dbId, langId, postId, locId, userId);
				helper.insertHrLoanEmpPrinRecoverDtls(hrPayEmpLoan, hrLoanEmpPrinRecoverDtls, dbId, langId, postId, locId, userId);
				/*GenericDaoHibernateImpl genericHibDAO = new GenericDaoHibernateImpl(HrLoanEmpPrinRecoverDtls.class);
				genericHibDAO.create(hrLoanEmpPrinRecoverDtls);*/

			}

			//business logic ends here

			if (msg == 1)
				objectArgs.put("MESSAGECODE", 300006);
			else
				objectArgs.put("MESSAGECODE", 300005);
			resultObject.setResultCode(ErrorConstants.SUCCESS);

			if (request.getParameter("empAllRec") != null)
				empAllRec = request.getParameter("empAllRec").toString();

			if (msg == 1 && empAllRec.equalsIgnoreCase("true") == true)
			{
				resultObject.setViewName("empLoanEditListEmpAllRec");
				objectArgs.put("empAllRec", "added");
				objectArgs.put("empId", empID);
			}
			else if (msg == 1 && empAllRec.equals("false") == true)
			{
				resultObject.setViewName("empLoanEditList");
				objectArgs.put("empAllRec", "false");
			}
			else if (!(msg == 1) && empAllRec.equalsIgnoreCase("true"))
				resultObject.setViewName("newEmpLoanDtlsEmpAllRec");
			else
				resultObject.setViewName("newEmpLoanDtls");

			objectArgs.put("empAllRecFlag", "true");

			//ended by Ankit Bhatt.
			resultObject.setResultValue(objectArgs);
			logger.info("INSERTED SUCCESSFULLY");

		}
		catch (ConstraintViolationException ex)
		{
			logger.info("TransactionSystemException occurs...");
		}
		catch (Exception e)
		{
			logger.info("The error is:-");
			logger.error("Error is: " + e.getMessage());
			logger.info("There is some error at editting or inserting time");
			//Map result = new HashMap();
			objectArgs.put("MESSAGECODE", 300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");

		}
		return resultObject;
	}

	public ResultObject multipleAddLoan(Map objectArgs)
	{

		logger.info("-------------inside multipleAddLoan-------------");
		logger.info("====> inside multipleAddLoan ");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try
		{

			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			long empID = 0;
			String empAllRec = "";
			if (request.getParameter("empAllRec") != null)
			{
				empAllRec = request.getParameter("empAllRec").toString();
				logger.info("added by samir for empAllRec value====>" + empAllRec);
				if (request.getParameter("empId") != null && empAllRec.equalsIgnoreCase("y") == true)
					empID = Long.parseLong(request.getParameter("empId").toString());
				logger.info("added by samir for empID value====>" + empID);
			}

			//added by ravysh
			String FromBasicDtlsNew = "";
			String otherId = "";
			if (request.getParameter("FromBasicDtlsNew") != null)
			{
				FromBasicDtlsNew = request.getParameter("FromBasicDtlsNew").toString();
			}

			if (request.getParameter("otherId") != null)
			{
				otherId = request.getParameter("otherId").toString();
			}

			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");

			langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());

			HrLoanEmpDtls hrLoanEmpDtls = null;
			HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls = null;
			HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls = null;

			LoanCustomVO loanCustomVO = new LoanCustomVO();
			Date sysDate = new java.util.Date();

			/* 
			 * For every entry made in Jsp, there will be corresponding xml file for each entry.
			 * In other words, for each row dynamically created in jsp (i.e table) will have different XML.
			 * We can iterate through each of them one by one by encXML, which contains array of path of all XML.
			 */
			String encXML[] = StringUtility.getParameterValues("encXML", request);

			/*logger.info("encXML.length: " +encXML.length);
			logger.info("encXML: XML file path is: " +encXML[0]);*/

			if (encXML != null && encXML.length > 0)
			{
				ArrayList result = (ArrayList) FileUtility.xmlFilesToVoByXStream(encXML);
				long hrEmpId = 0;
				java.util.Iterator iterator = result.iterator();

				logger.info("result.size(): " + result.size());

				while (iterator.hasNext())
				{

					hrLoanEmpDtls = new HrLoanEmpDtls();
					hrLoanEmpPrinRecoverDtls = new HrLoanEmpPrinRecoverDtls();
					hrLoanEmpIntRecoverDtls = new HrLoanEmpIntRecoverDtls();

					loanCustomVO = (LoanCustomVO) iterator.next();

					/*  ----- SET "HrLoanEmpDtls" RELATED INFORMATION  ----- */

					//set empLoanId
					IdGenerator idGen = new IdGenerator();
					long empLoanId = idGen.PKGenerator("HR_LOAN_EMP_DTLS", objectArgs);
					//long empLoanId = IDGenerateDelegate.getNextId("HR_LOAN_EMP_DTLS", objectArgs);
					hrLoanEmpDtls.setEmpLoanId(empLoanId);
					logger.info("EmpLoanId: " + hrLoanEmpDtls.getEmpLoanId());

					//set empId
					/* N.B: As employee id received from jsp is of OrgEmpMst we'll have to set it to HrEisEmpMst */
					hrEmpId = loanCustomVO.getEmpId();
					logger.info("employee id recvd from VOGEN hrEmpId is: " + hrEmpId);

					List lstEmpList = empInfoDAOImpl.getEmpIdData(hrEmpId);

					if (lstEmpList != null && !lstEmpList.isEmpty())
					{
						HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
						hrEisEmpMst = (HrEisEmpMst) lstEmpList.get(0);
						hrLoanEmpDtls.setHrEisEmpMst(hrEisEmpMst);
						logger.info("EmpId is: " + hrLoanEmpDtls.getHrEisEmpMst().getEmpId());
					}

					//set loanTypeId
					LoanAdvMstDAO loanMstDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class, serv.getSessionFactory());
					GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(HrLoanAdvMst.class);
					genDAO.setSessionFactory(serv.getSessionFactory());
					HrLoanAdvMst hrLoanAdvMst1 = (HrLoanAdvMst) genDAO.read(loanCustomVO.getLoanTypeId());
					HrLoanAdvMst hrLoanAdvMst = loanMstDAO.read(loanCustomVO.getLoanTypeId());
					logger.info("loanCUstom Vo .getLoanTypeId is11111111 " + loanCustomVO.getLoanTypeId());
					if (hrLoanAdvMst1 != null)
						logger.info("hrLoanAdvMst1 is not null");
					else
						logger.info("hrLoanAdvMst1 is null");

					if (hrLoanAdvMst != null)
					{
						logger.info("loanCUstom Vo .getLoanTypeId is " + loanCustomVO.getLoanTypeId());
						logger.info("hrLoanadvMst " + hrLoanAdvMst);
						hrLoanEmpDtls.setHrLoanAdvMst(hrLoanAdvMst);
						logger.info("LoanTypeId: " + hrLoanEmpDtls.getHrLoanAdvMst().getLoanAdvId());
					}

					//set loanPrincipalAmount
					hrLoanEmpDtls.setLoanPrinAmt(loanCustomVO.getLoanPrinAmt());
					logger.info("hrLoanEmpDtls.getLoanPrinAmt is: " + hrLoanEmpDtls.getLoanPrinAmt());

					//set loanInterestAmount
					hrLoanEmpDtls.setLoanInterestAmt(loanCustomVO.getLoanIntAmt());
					logger.info("hrLoanEmpDtls.getLoanInterestAmt() is: " + hrLoanEmpDtls.getLoanInterestAmt());

					//set loanPrinciaplInstallmentNumber
					hrLoanEmpDtls.setLoanPrinInstNo(loanCustomVO.getLoanPrinInstNo());
					logger.info("hrLoanEmpDtls.getLoanPrinInstNo() is: " + hrLoanEmpDtls.getLoanPrinInstNo());

					//set loanInterestInstallmentNumber
					hrLoanEmpDtls.setLoanIntInstNo(loanCustomVO.getLoanIntInstNo());
					logger.info("hrLoanEmpDtls.getLoanIntInstNo() is: " + hrLoanEmpDtls.getLoanIntInstNo());

					//set loanAccountNo
					hrLoanEmpDtls.setLoanAccountNo(loanCustomVO.getLoanAcNo());
					logger.info("hrLoanEmpDtls.getLoanAccountNo() is: " + hrLoanEmpDtls.getLoanAccountNo());

					//set loanDate
					hrLoanEmpDtls.setLoanDate(loanCustomVO.getLoanDate());
					logger.info("hrLoanEmpDtls.getLoanDate() is: " + hrLoanEmpDtls.getLoanDate());

					//set databaseId
					hrLoanEmpDtls.setCmnDatabaseMst(cmnDatabaseMst);
					logger.info("hrLoanEmpDtls.getCmnDatabaseMst().getDbId() is: " + hrLoanEmpDtls.getCmnDatabaseMst().getDbId());

					//set locationId
					hrLoanEmpDtls.setCmnLocationMst(cmnLocationMst);
					logger.info("hrLoanEmpDtls.getCmnLocationMst().getLocId() is: " + hrLoanEmpDtls.getCmnLocationMst().getLocId());

					//set createdBy
					hrLoanEmpDtls.setOrgUserMstByCreatedBy(orgUserMst);
					logger.info("hrLoanEmpDtls.getOrgUserMstByCreatedBy().getUserId() is: " + hrLoanEmpDtls.getOrgUserMstByCreatedBy().getUserId());

					//set createdByPost
					hrLoanEmpDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					logger.info("hrLoanEmpDtls.getOrgPostMstByCreatedByPost().getPostId() is: " + hrLoanEmpDtls.getOrgPostMstByCreatedByPost().getPostId());

					//set createdDate
					hrLoanEmpDtls.setCreatedDate(sysDate);
					logger.info("hrLoanEmpDtls.getCreatedDate() is: " + hrLoanEmpDtls.getCreatedDate());

					//set trnCounter
					hrLoanEmpDtls.setTrnCounter(new Integer(1));
					logger.info("hrLoanEmpDtls.getTrnCounter() is: " + hrLoanEmpDtls.getTrnCounter());

					//set loanInterestEMIAmount
					hrLoanEmpDtls.setLoanIntEmiAmt(loanCustomVO.getLoanIntEmiAmt());
					logger.info("hrLoanEmpDtls.getLoanIntEmiAmt() is: " + hrLoanEmpDtls.getLoanIntEmiAmt());

					//set loanPrincipalEMIAmount
					hrLoanEmpDtls.setLoanPrinEmiAmt(loanCustomVO.getLoanPrinEmiAmt());
					logger.info("hrLoanEmpDtls.getLoanPrinEmiAmt() is: " + hrLoanEmpDtls.getLoanPrinEmiAmt());

					//set loanSanctionOrderNumber
					hrLoanEmpDtls.setLoanSancOrderNo(loanCustomVO.getLoanSancOrderNo());
					logger.info("hrLoanEmpDtls.getLoanSancOrderNo() is: " + hrLoanEmpDtls.getLoanSancOrderNo());
					//added by khushal
					//set loanSanctionOrderDate
					hrLoanEmpDtls.setLoanSancOrderdate(loanCustomVO.getLoanSancOrderDate());
					logger.info("hrLoanEmpDtls.getLoanSancOrderDate() is: " + hrLoanEmpDtls.getLoanSancOrderdate());

					//ended by khushal

					//set loanActivateFlag
					hrLoanEmpDtls.setLoanActivateFlag(loanCustomVO.getLoanActivateFlag());
					logger.info("hrLoanEmpDtls.getLoanActivateFlag() is: " + hrLoanEmpDtls.getLoanActivateFlag());

					//set isApproved
					hrLoanEmpDtls.setIsApproved(new Integer(0));

					//Added By Javed
					logger.info("===> loanCustomVO.getLoanOddinstno() :: " + loanCustomVO.getLoanOddinstno());
					logger.info("===> loanCustomVO.getLoanOddinstAmt() :: " + loanCustomVO.getLoanOddinstAmt());

					hrLoanEmpDtls.setLoanOddinstno(loanCustomVO.getLoanOddinstno());
					hrLoanEmpDtls.setLoanOddinstAmt(loanCustomVO.getLoanOddinstAmt());

					//added by Ankit Bhatt for Maha
					hrLoanEmpDtls.setVoucherNo(loanCustomVO.getLoanVoucherNo());
					hrLoanEmpDtls.setVoucherDate(loanCustomVO.getLoanVoucherDate());
					//ended
					//added by  manish khunt to maintain history

					hrLoanEmpDtls.setTrnCounter(new Integer(1));

					//ended by manish 
					//Added By Javed

					/* ----- SET "HrLoanEmpPrinRecoverDtls" RELATED INFORMATION ----- */

					//set princRecoveredId
					Long prinId = idGen.PKGenerator("HR_LOAN_EMP_PRIN_RECOVER_DTLS", objectArgs);
					//Long prinId=IDGenerateDelegate.getNextId("HR_LOAN_EMP_PRIN_RECOVER_DTLS",objectArgs);
					hrLoanEmpPrinRecoverDtls.setPrinRecoverId(prinId);
					logger.info("Pk Gen for princReoveredId: " + prinId);

					//set hrLoanEmpDtls fk
					hrLoanEmpPrinRecoverDtls.setHrLoanEmpDtls(hrLoanEmpDtls);
					logger.info("loanEmpId is: " + hrLoanEmpPrinRecoverDtls.getHrLoanEmpDtls().getEmpLoanId());

					//set totalRecoveredAmount
					hrLoanEmpPrinRecoverDtls.setTotalRecoveredAmt(loanCustomVO.getLoanPrinRecovAmt());
					logger.info("hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt() is: " + hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt());

					//set totalRecoveredInstallment
					hrLoanEmpPrinRecoverDtls.setTotalRecoveredInst(loanCustomVO.getLoanPrinRecovInt());
					logger.info("hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst() is: " + hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst());

					//set databaseId
					hrLoanEmpPrinRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
					logger.info("hrLoanEmpPrinRecoverDtls.getCmnDatabaseMst().getDbId() is: " + hrLoanEmpPrinRecoverDtls.getCmnDatabaseMst().getDbId());

					//set createdByPost
					hrLoanEmpPrinRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					logger.info("hrLoanEmpPrinRecoverDtls.getOrgPostMstByCreatedByPost().getPostId() is: " + hrLoanEmpPrinRecoverDtls.getOrgPostMstByCreatedByPost().getPostId());

					//set createdBy
					hrLoanEmpPrinRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
					logger.info("hrLoanEmpPrinRecoverDtls.getOrgUserMstByCreatedBy().getUserId() is: " + hrLoanEmpPrinRecoverDtls.getOrgUserMstByCreatedBy().getUserId());

					//set createdDate
					hrLoanEmpPrinRecoverDtls.setCreatedDate(sysDate);
					logger.info("hrLoanEmpPrinRecoverDtls.getCreatedDate() is: " + hrLoanEmpPrinRecoverDtls.getCreatedDate());

					//set locationId
					hrLoanEmpPrinRecoverDtls.setCmnLocationMst(cmnLocationMst);
					logger.info("hrLoanEmpPrinRecoverDtls.getCmnLocationMst().getLocId() is: " + hrLoanEmpPrinRecoverDtls.getCmnLocationMst().getLocId());

					//set trnCounter
					hrLoanEmpPrinRecoverDtls.setTrnCounter(new Integer(1));
					logger.info("hrLoanEmpPrinRecoverDtls.getTrnCounter() is: " + hrLoanEmpPrinRecoverDtls.getTrnCounter());

					/* ----- SET "HrLoanEmpIntRecoverDtls" RELATED INFORMATION ----- */

					//set intRecoverId
					Long intId = idGen.PKGenerator("HR_LOAN_EMP_INT_RECOVER_DTLS", objectArgs);
					//Long intId = IDGenerateDelegate.getNextId("HR_LOAN_EMP_INT_RECOVER_DTLS",objectArgs);
					hrLoanEmpIntRecoverDtls.setIntRecoverId(intId);
					logger.info("Pk Gen: IntRecoverId: " + intId);

					//set empLoanId fk
					hrLoanEmpIntRecoverDtls.setHrLoanEmpDtls(hrLoanEmpDtls);
					logger.info("setting empLoanId: " + hrLoanEmpIntRecoverDtls.getHrLoanEmpDtls().getEmpLoanId());

					//set totalRecoveredInterest
					hrLoanEmpIntRecoverDtls.setTotalRecoveredInt(loanCustomVO.getLoanIntRecovAmt());
					logger.info("hrLoanEmpIntRecoverDtls.getTotalRecoveredInt() is: " + hrLoanEmpIntRecoverDtls.getTotalRecoveredInt());

					//set totalRecoveredInterestInstallments
					hrLoanEmpIntRecoverDtls.setTotalRecoveredIntInst(loanCustomVO.getLoanIntRecovInt());
					logger.info("hrLoanEmpIntRecoverDtls.getTotalRecoveredIntInst() is: " + hrLoanEmpIntRecoverDtls.getTotalRecoveredIntInst());

					//set databaseId
					hrLoanEmpIntRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
					logger.info("hrLoanEmpIntRecoverDtls.getCmnDatabaseMst().getDbId() is: " + hrLoanEmpIntRecoverDtls.getCmnDatabaseMst().getDbId());

					//set locationId
					hrLoanEmpIntRecoverDtls.setCmnLocationMst(cmnLocationMst);
					logger.info("hrLoanEmpIntRecoverDtls.getCmnLocationMst().getLocId() is: " + hrLoanEmpIntRecoverDtls.getCmnLocationMst().getLocId());

					//set createdByPost
					hrLoanEmpIntRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
					logger.info("hrLoanEmpIntRecoverDtls.getOrgPostMstByCreatedByPost().getPostId() is: " + hrLoanEmpIntRecoverDtls.getOrgPostMstByCreatedByPost().getPostId());

					//set created by
					hrLoanEmpIntRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
					logger.info("hrLoanEmpIntRecoverDtls.getOrgUserMstByCreatedBy().getUserId() is: " + hrLoanEmpIntRecoverDtls.getOrgUserMstByCreatedBy().getUserId());

					//set created date
					hrLoanEmpIntRecoverDtls.setCreatedDate(sysDate);
					logger.info("hrLoanEmpIntRecoverDtls.getCreatedDate() is: " + hrLoanEmpIntRecoverDtls.getCreatedDate());

					//set trnCounter
					hrLoanEmpIntRecoverDtls.setTrnCounter(new Integer(1));
					logger.info("hrLoanEmpIntRecoverDtls.getTrnCounter() is: " + hrLoanEmpIntRecoverDtls.getTrnCounter());

					//Initiate DAO's
					EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
					LoanEmpPrinRecvDAOImpl loanEmpPrinRecvDAO = new LoanEmpPrinRecvDAOImpl(HrLoanEmpPrinRecoverDtls.class, serv.getSessionFactory());
					LoanEmpIntRecvDAOImpl loanEmpIntRecvDAO = new LoanEmpIntRecvDAOImpl(HrLoanEmpIntRecoverDtls.class, serv.getSessionFactory());

					//write into database
					try
					{
						empLoanDAO.create(hrLoanEmpDtls);
						loanEmpPrinRecvDAO.create(hrLoanEmpPrinRecoverDtls);
						loanEmpIntRecvDAO.create(hrLoanEmpIntRecoverDtls);

					}
					catch (Exception e)
					{
						logger.info("Exception Occured in writing to database");
						logger.error("Error is: " + e.getMessage());
					}

				}//end while

			}//end if 

			objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
			objectArgs.put("otherId", otherId);
			//Single screen handling...
			if (empAllRec.equalsIgnoreCase("y") == true)
			{
				//fwding to single screen jsp
				logger.info("if emp flag is==============>" + empAllRec);
				objectArgs.put("empAllRec", "added");
				objectArgs.put("empId", empID);
				resultObject.setViewName("newEmpLoanDtlsEmpAllRec");
			}
			else
			{
				logger.info("else emp flag is==============>" + empAllRec);
				objectArgs.put("empAllRec", "false");
				if (FromBasicDtlsNew.equals("YES"))
					resultObject.setViewName("newEmpLoanDtlsEmpAllRec");
				else
					resultObject.setViewName("newEmpLoanDtls");

			}
			logger.info("emp flag is==============>" + empAllRec);
			//message if successfully inserted

			/*			objectArgs.put("empAllRec", "false");
						resultObject.setViewName("newEmpLoanDtls");*/

			objectArgs.put("MESSAGECODE", 300005);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			logger.info("INSERTED SUCCESSFULLY");

		}
		catch (ConstraintViolationException ex)
		{
			logger.info("TransactionSystemException occurs...");
		}
		catch (Exception e)
		{
			logger.info("The error is:-");
			logger.error("Error is: " + e.getMessage());
			logger.info("There is some error at editting or inserting time");
			objectArgs.put("MESSAGECODE", 300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");

		}
		return resultObject;
	}

	public ResultObject getLoanValue(Map objectArgs)
	{

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("inside getLoanValue------------>");
		logger.info("====> inside getLoanValue ");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			LoanAdvMstDAOImpl loanDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class, serv.getSessionFactory());

			//    		added by Ankit Bhatt for merging Screens
			Map voToService = (Map) objectArgs.get("voToServiceMap");

			String empAllRec = "";
			if (voToService.get("empAllRec") != null)
				empAllRec = voToService.get("empAllRec").toString();

			//ended by Ankit Bhatt
			String empName = "";
			if ((voToService.get("Employee_srchNameText_EmpLoanSearch") != null && !voToService.get("Employee_srchNameText_EmpLoanSearch").equals("")))
				empName = (String) voToService.get("Employee_srchNameText_EmpLoanSearch").toString();
			////////// for update pay bill
			String updatePaybillFlg = "";
			int paybillMonth = 0;
			int paybillYear = 0;

			if (voToService.get("updatePaybillFlg") != null)
				updatePaybillFlg = voToService.get("updatePaybillFlg").toString();
			if (voToService.get("paybillMonth") != null)
				paybillMonth = Integer.parseInt(voToService.get("paybillMonth").toString());
			if (voToService.get("paybillYear") != null)
				paybillYear = Integer.parseInt(voToService.get("paybillYear").toString());

			objectArgs.put("updatePaybillFlg", updatePaybillFlg);
			objectArgs.put("paybillMonth", paybillMonth);
			objectArgs.put("paybillYear", paybillYear);

			logger.info("*******************updatePaybillFlg*****************" + updatePaybillFlg);

			//By Varshil for Hiding Paused Loan in Salary Config
			//
			String onlyActiveLoans = objectArgs.get("onlyActiveLoans") != null ? (String) objectArgs.get("onlyActiveLoans") : "no";

			//Added by mrugesh
			Map loginDetails = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetails.get("langId").toString());
			long languageId = 1;
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(languageId);
			//Ended by mrugesh
			// Added by Urvin
			//long locationId=Long.parseLong(loginDetails.get("locationId").toString());
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetails.get("locationVO");
			String locationCode = (cmnLocationMst.getLocationCode() != null && !cmnLocationMst.getLocationCode().trim().equals("")) ? cmnLocationMst.getLocationCode() : "";

			// End   	
			String editFlag = (String) voToService.get("edit");
			//by manoj for employee search
			String empIdStr = (String) voToService.get("Employee_ID_EmpLoanSearch");
			logger.info("the emp id from search employee " + empIdStr);
			//end by manoj for employee search			
			//if(request.getParameter("edit")!= null && request.getParameter("edit").equals("Y"))

			//added by ravysh
			String FromBasicDtlsNew = voToService.get("FromBasicDtlsNew") != null ? (String) voToService.get("FromBasicDtlsNew") : "";
			String otherId = voToService.get("otherId") != null ? (String) voToService.get("otherId") : "";

			if (editFlag != null && editFlag.equals("Y"))
			{
				//String loanId=request.getParameter("empLoanId");
				String loanId = (String) voToService.get("empLoanId");
				long empLoanId = Long.parseLong(loanId);
				HrLoanEmpDtls actionList = empLoanDAO.getEmpLoanDetail(empLoanId);

				GenericDaoHibernateImpl princiGImpl = null;
				princiGImpl = new GenericDaoHibernateImpl(HrLoanEmpPrinRecoverDtls.class);
				princiGImpl.setSessionFactory(serv.getSessionFactory());

				GenericDaoHibernateImpl intGImpl = null;
				intGImpl = new GenericDaoHibernateImpl(HrLoanEmpIntRecoverDtls.class);
				intGImpl.setSessionFactory(serv.getSessionFactory());

				long principalRecoveredAmt = 0;
				long pricipalRecoveredInt = 0;
				long intRecoveredAmt = 0;
				long intRecoveredInt = 0;

				List loanPrinciRecoverList = princiGImpl.getListByColumnAndValue("hrLoanEmpDtls.empLoanId", empLoanId);
				List loanIntRecoverList = intGImpl.getListByColumnAndValue("hrLoanEmpDtls.empLoanId", empLoanId);

				logger.info("loanPrinciRecoverList " + loanPrinciRecoverList);

				if (loanPrinciRecoverList != null && !loanPrinciRecoverList.isEmpty())
				{
					HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls = (HrLoanEmpPrinRecoverDtls) loanPrinciRecoverList.get(0);

					principalRecoveredAmt = hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt();
					pricipalRecoveredInt = hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst();

				}

				if (loanIntRecoverList != null && !loanIntRecoverList.isEmpty())
				{
					HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls = (HrLoanEmpIntRecoverDtls) loanIntRecoverList.get(0);

					intRecoveredAmt = hrLoanEmpIntRecoverDtls.getTotalRecoveredInt();
					intRecoveredInt = hrLoanEmpIntRecoverDtls.getTotalRecoveredIntInst();
				}

				logger.info("principalRecoveredAmt " + principalRecoveredAmt);
				logger.info("pricipalRecoveredInt " + pricipalRecoveredInt);
				logger.info("intRecoveredAmt " + intRecoveredAmt);
				logger.info("intRecoveredInt " + intRecoveredInt);
				//for lazy initialization dont remove this logger
				logger.info("the emp name is " + actionList.getHrEisEmpMst().getOrgEmpMst().getEmpFname());
				logger.info("the emp grade is " + actionList.getHrEisEmpMst().getOrgEmpMst().getOrgGradeMst().getGradeName());
				logger.info("actionList **********" + actionList.getVoucherDate());

				logger.info("actionList by khushal Testing date **********" + actionList.getLoanSancOrderdate());

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

				logger.info("voucher date" + actionList.getVoucherDate());
				//String vd="";
				if (actionList.getVoucherDate() != null)
				{
					String vd = actionList.getVoucherDate().toString();
					String voucherDate = sdf.format(sdfParse.parse(vd));
					objectArgs.put("voucherDate", voucherDate);
				}
				//added by khushal
				if (actionList.getLoanSancOrderdate() != null)
				{
					Date loanSancOrderdate = actionList.getLoanSancOrderdate();
					objectArgs.put("loanSancOrderdate1", loanSancOrderdate);
				}
				if (actionList.getOrderDate() != null)
				{
					Date ordrDate = actionList.getOrderDate();
					objectArgs.put("ordrDate", ordrDate);
				}
				if (actionList.getOrderno() != null && !actionList.getOrderno().equalsIgnoreCase("0"))
				{
					String ordrNo = actionList.getOrderno();
					objectArgs.put("ordrNo", ordrNo);
				}

				//ended by khushal
				objectArgs.put("actionList", actionList);
				objectArgs.put("principalRecoveredAmt", principalRecoveredAmt);
				objectArgs.put("pricipalRecoveredInt", pricipalRecoveredInt);
				objectArgs.put("intRecoveredAmt", intRecoveredAmt);
				objectArgs.put("intRecoveredInt", intRecoveredInt);
				//added by ravysh
				objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
				objectArgs.put("otherId", otherId);

				HrEisEmpMst empMstVO = (HrEisEmpMst) objectArgs.get("empMstVO");
				EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
				List<HrEisEmpMst> empList = new ArrayList();//empinfoDao.getAllEmpData(cmnLanguageMst);

				objectArgs.put("empList", empList);
				logger.info("The size of Employee list is------->" + empList.size());

				/*HrLoanAdvMst loanMstVO = (HrLoanAdvMst) objectArgs.get("loanMstVO");
				LoanAdvMstDAOImpl loanDAO= new LoanAdvMstDAOImpl(HrLoanAdvMst.class,serv.getSessionFactory());*/
				List<HrLoanAdvMst> loanList = loanDAO.getAllLoanAdvMasterData(languageId);

				objectArgs.put("loanList", loanList);
				logger.info("The size of Loan List is------->" + loanList.size());

				resultObject.setResultCode(ErrorConstants.SUCCESS);

				//		      added by Ankit Bhatt for merging screens
				if (empAllRec.equalsIgnoreCase("y"))
				{
					objectArgs.put("empAllRec", "true");
					objectArgs.put("empId", actionList.getHrEisEmpMst().getEmpId());
					resultObject.setViewName("empLoanEditListEmpAllRec");
				}
				else
				{
					//ended by Ankit Bhatt

					//added by ravysh
					if (FromBasicDtlsNew.equals("YES"))
						resultObject.setViewName("empLoanEditListEmpAllRec");
					else
						resultObject.setViewName("empLoanEditList");
				}
				resultObject.setResultValue(objectArgs);

			}//by manoj for employee search
			else if (empIdStr != null && !empIdStr.equals(""))
			{
				long empId = Long.parseLong(empIdStr);
				//HrEisEmpMst hrEisEmpMst =(HrEisEmpMst) empinfodao.read(empId);
				EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
				//OrgEmpMst orgEmpMst=orgEmpDao.getEngGujEmployee(hrEisEmpMst.getOrgEmpMst(), languageId);
				logger.info("empId is:-" + empId);
				OrgEmpMst orgEmpMst = orgEmpDao.read(empId);

				List<HrLoanEmpDtls> actionList = new ArrayList();//empLoanDAO.getEmpLoanData(orgEmpMst);
				List<HrLoanEmpDtls> newActionList = new ArrayList<HrLoanEmpDtls>();

				/*long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/

				/*List userList =empinfodao.getUserListByLogin(langId,userId);
				List <OrgUserMst> orgUserList = new ArrayList();
				for (Iterator iter = userList.iterator(); iter.hasNext();)
				{			 
				Object[] row = (Object[])iter.next();	
				OrgUserMst userMst = new OrgUserMst();
				String id=row[0].toString();
				userMst.setUserId(Long.parseLong(id));
				orgUserList.add(userMst);
				}*/

				//Fetch loan data for showing in basic details screen
				//Number of records found = fetchedLoanList
				List fetchedLoanList = empLoanDAO.getEmployeeLoanData(locationCode, orgEmpMst.getEmpId(), langId, onlyActiveLoans);
				logger.info("basic dtls loan dtls list size is: " + fetchedLoanList.size());

				long recoveredInstallments = 0;
				long totalInstallments = 0;

				ArrayList<LoanCustomVO> customLoanVoList = new ArrayList<LoanCustomVO>();
				int loanListSize = fetchedLoanList != null ? fetchedLoanList.size() : 0;
				for (int i = 0; i < loanListSize; i++)
				{

					LoanCustomVO customVo = new LoanCustomVO();
					//Object[] obj = (Object[])fetchedLoanList.get(i);
					//logger.info("i = " +i +"obj[] size: "+obj.length);

					//There will be three type of record within fetchedLoanList

					HrLoanEmpDtls objEmpLoanDtls = new HrLoanEmpDtls();
					//logger.info("is obj class compatible? : " +  obj[0].getClass().isInstance(objEmpLoanDtls));
					objEmpLoanDtls = (HrLoanEmpDtls) fetchedLoanList.get(i);
					;

					//HrLoanEmpPrinRecoverDtls empPrinRecoverDtls =(HrLoanEmpPrinRecoverDtls)obj[6];
					HrLoanEmpPrinRecoverDtls empPrinRecoverDtls = (HrLoanEmpPrinRecoverDtls) objEmpLoanDtls.getHrLoanEmpPrinRecoverDtlses().iterator().next();
					//HrLoanEmpIntRecoverDtls empIntRecoverDtls = (HrLoanEmpIntRecoverDtls)obj[7];
					HrLoanEmpIntRecoverDtls empIntRecoverDtls = (HrLoanEmpIntRecoverDtls) objEmpLoanDtls.getHrLoanEmpIntRecoverDtlses().iterator().next();

					boolean isMultiRecovery = objEmpLoanDtls.getMulLoanRecoveryMode() == 1 ? true : false;
					//logger.info("EmpLoanId is: " +objEmpLoanDtls.getEmpLoanId());
					customVo.setEmpLoanId(objEmpLoanDtls.getEmpLoanId());

					//logger.info("loan name is: " +objEmpLoanDtls.getHrLoanAdvMst().getLoanAdvName() );
					customVo.setLoanName(objEmpLoanDtls.getHrLoanAdvMst().getLoanAdvName());

					//logger.info("loan EMI amnt: " +objEmpLoanDtls.getLoanPrinEmiAmt() );
					customVo.setLoanPrinEmiAmt(objEmpLoanDtls.getLoanPrinEmiAmt());

					//logger.info("loand int emi amt: " + objEmpLoanDtls.getLoanIntEmiAmt());
					customVo.setLoanIntEmiAmt((objEmpLoanDtls.getLoanIntEmiAmt()));

					//logger.info("outstanding principle amt: " +Long.parseLong(obj[1].toString()));
					customVo.setOutstandingPrincipleAmt(objEmpLoanDtls.getLoanPrinAmt() - empPrinRecoverDtls.getTotalRecoveredAmt());

					//logger.info("outstanding Interest amt: " +Long.parseLong(obj[2].toString()));
					customVo.setOutstandingInterestAmt(objEmpLoanDtls.getLoanInterestAmt() - empIntRecoverDtls.getTotalRecoveredInt());

					customVo.setLoanTypeId(objEmpLoanDtls.getHrLoanAdvMst().getLoanAdvId());

					//added by manish
					recoveredInstallments = 0;
					totalInstallments = 0;

					if (objEmpLoanDtls != null && objEmpLoanDtls.getLoanPrinInstNo() != 0)
					{
						totalInstallments = objEmpLoanDtls.getLoanPrinInstNo();
						recoveredInstallments = isMultiRecovery ? (empPrinRecoverDtls.getTotalRecoveredInst() + objEmpLoanDtls.getMulLoanInstRecvd()) : (empPrinRecoverDtls.getTotalRecoveredInst() + 1);
					}
					else
					{

						totalInstallments = objEmpLoanDtls.getLoanIntInstNo();
						recoveredInstallments = isMultiRecovery ? (empIntRecoverDtls.getTotalRecoveredIntInst() + objEmpLoanDtls.getMulLoanInstRecvd()) : (empIntRecoverDtls.getTotalRecoveredIntInst() + 1);
					}

					logger.info(" totalInstallments " + totalInstallments + " recoveredInstallments " + recoveredInstallments);
					//ended 
					//added by manish 

					String loanPrinEmiAmtString = new Long(recoveredInstallments).toString() + "/" + new Long(totalInstallments).toString();
					String loanIntEmiAmtString = new Long(recoveredInstallments).toString() + "/" + new Long(totalInstallments).toString();

					customVo.setLoanPrinEmiAmtString(loanPrinEmiAmtString);
					customVo.setLoanIntEmiAmtString(loanIntEmiAmtString);
					logger.info("loanPrinEmiAmtString is " + loanPrinEmiAmtString + "setLoanIntEmiAmtString is " + loanIntEmiAmtString);
					//ended by manish 
					//added by manish
					Date currDate = new Date();
					Date loanDate = (Date) objEmpLoanDtls.getLoanDate();

					long priInsNo = objEmpLoanDtls.getLoanPrinInstNo();
					long intInstNo = objEmpLoanDtls.getLoanIntInstNo();

					Calendar c = Calendar.getInstance();

					c.set(Calendar.YEAR, loanDate.getYear() + 1900);
					c.set(Calendar.MONTH, loanDate.getMonth());
					c.set(Calendar.DAY_OF_MONTH, loanDate.getDate() - 1);
					c.add(Calendar.MONTH, (int) (priInsNo + intInstNo));

					Date endDate = c.getTime();

					c.set(Calendar.YEAR, currDate.getYear() + 1900);
					c.set(Calendar.MONTH, currDate.getMonth());

					int maxDayInMonth = c.getActualMaximum(5);
					c.set(Calendar.DAY_OF_MONTH, maxDayInMonth);

					//17 jan 2012
					/*if ( (currDate.compareTo(loanDate) == 1 && currDate.compareTo(endDate) <= 0 )|| recoveredInstallments!= totalInstallments )
										{
												customLoanVoList.add(customVo);
												
										}*/
					//17 jan 2012
					//update the values if the processed installment is the odd installment
					//for loans
					logger.info(" update the values if the processed installment is the odd installment before if");
					if (objEmpLoanDtls.getLoanPrinAmt() != 0)//it is loan
					{
						logger.info(" objEmpLoanDtls.getLoanPrinAmt()!=0");
						if (!isMultiRecovery)
						{
							if (empPrinRecoverDtls != null && (empPrinRecoverDtls.getTotalRecoveredInst() + 1L == objEmpLoanDtls.getLoanOddinstno()))
								customVo.setLoanPrinEmiAmt(objEmpLoanDtls.getLoanOddinstAmt());
						}
						else
						{
							customVo.setLoanPrinEmiAmt(objEmpLoanDtls.getMulLoanAmtRecvd());
							customVo.setOutstandingPrincipleAmt(customVo.getOutstandingPrincipleAmt() - objEmpLoanDtls.getMulLoanAmtRecvd());
						}

					}
					if (objEmpLoanDtls.getLoanIntEmiAmt() != 0)
					{

						logger.info(" objEmpLoanDtls.getLoanIntEmiAmt() !=0");

						if (!isMultiRecovery)
						{
							if (empIntRecoverDtls != null && (empIntRecoverDtls.getTotalRecoveredIntInst() + 1L == objEmpLoanDtls.getLoanOddinstno()))
								customVo.setLoanIntEmiAmt(objEmpLoanDtls.getLoanOddinstAmt());
						}
						else
						{
							customVo.setLoanIntEmiAmt(objEmpLoanDtls.getMulLoanAmtRecvd());
							customVo.setOutstandingInterestAmt(customVo.getOutstandingInterestAmt() - objEmpLoanDtls.getMulLoanAmtRecvd());
						}

					}
					customLoanVoList.add(customVo);
					//end 17 jan 2012
				}//end for

				objectArgs.put("otherDtlsLoan", customLoanVoList);
				objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
				objectArgs.put("otherId", otherId);

				actionList = empLoanDAO.getEmpLoanData(locationCode, orgEmpMst.getEmpId(), langId, onlyActiveLoans);

				logger.info("the size of actionList for the emp " + orgEmpMst.getEmpId() + " " + orgEmpMst.getEmpFname() + " is " + actionList.size());
				HrLoanAdvMst hrLoanAdvMst = new HrLoanAdvMst();
				LoanAdvMstDAOImpl loanMstDao = new LoanAdvMstDAOImpl(HrLoanAdvMst.class, serv.getSessionFactory());

				double loanTotal = 0;

				String x = "";
				for (int i = 0; i < actionList.size(); i++)
				{

					HrLoanEmpDtls empLoan = new HrLoanEmpDtls();
					empLoan = actionList.get(i);
					boolean isMultiRecovery = empLoan.getMulLoanRecoveryMode() == 1 ? true : false;
					//manish
					Date currDate = new Date();
					Date loanDate = empLoan.getLoanDate();

					long priInsNo = empLoan.getLoanPrinInstNo();
					long intInstNo = empLoan.getLoanIntInstNo();

					Calendar c = Calendar.getInstance();

					c.set(Calendar.YEAR, loanDate.getYear() + 1900);
					c.set(Calendar.MONTH, loanDate.getMonth());
					c.set(Calendar.DAY_OF_MONTH, loanDate.getDate() - 1);
					c.add(Calendar.MONTH, (int) (priInsNo + intInstNo));

					Date endDate = c.getTime();
					//if ((currDate.compareTo(loanDate) == 1 && currDate.compareTo(endDate) <= 0)|| recoveredInstallments!= totalInstallments)
					//{				

					if (empLoan != null)
					{ //17 jan 2012
						if (empLoan.getLoanPrinAmt() != 0)//it is loan
						{
							logger.info(" objEmpLoanDtls.getLoanPrinAmt()!=0");
							HrLoanEmpPrinRecoverDtls recovery = (HrLoanEmpPrinRecoverDtls) empLoan.getHrLoanEmpPrinRecoverDtlses().iterator().next();
							if (!isMultiRecovery)
							{
								if (recovery != null && (recovery.getTotalRecoveredInst() + 1L == empLoan.getLoanOddinstno()))
									loanTotal += empLoan.getLoanOddinstAmt();
								else
									loanTotal += empLoan.getLoanPrinEmiAmt();
							}
							else
							{
								loanTotal += empLoan.getMulLoanAmtRecvd();
							}

						}
						if (empLoan.getLoanIntEmiAmt() != 0)
						{

							logger.info(" objEmpLoanDtls.getLoanIntEmiAmt() !=0");
							HrLoanEmpIntRecoverDtls recovery = (HrLoanEmpIntRecoverDtls) empLoan.getHrLoanEmpIntRecoverDtlses().iterator().next();
							if (!isMultiRecovery)
							{
								if (recovery != null && (recovery.getTotalRecoveredIntInst() + 1L == empLoan.getLoanOddinstno()))
									loanTotal += empLoan.getLoanOddinstAmt();
								else
									loanTotal += empLoan.getLoanIntEmiAmt();
							}
							else
							{
								loanTotal += empLoan.getMulLoanAmtRecvd();
							}

						}
						//17 jan 2012 end

					}
					String tempBuffer = "";
					if (empLoan.getLoanPrinAmt() != 0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
						logger.info("Formatted as currency: " + currencyFormatter.format(empLoan.getLoanPrinAmt()));
						tempBuffer = currencyFormatter.format(empLoan.getLoanPrinAmt()).replace("$", "");
						empLoan.setCurrencyloanPrinAmt(tempBuffer.replace(".00", ""));
						logger.info("the value of the emploan is ::" + empLoan.getCurrencyloanPrinAmt());
					}

					if (empLoan.getLoanInterestAmt() != 0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
						logger.info("Formatted as currency: " + currencyFormatter.format(empLoan.getLoanInterestAmt()));
						tempBuffer = currencyFormatter.format(empLoan.getLoanInterestAmt()).replace("$", "");
						empLoan.setCurrencyloanInterestAmt(tempBuffer.replace(".00", ""));
						logger.info("the value of the emploan is ::" + empLoan.getCurrencyloanInterestAmt());
					}
					x = empLoan.getHrEisEmpMst() + " " + empLoan.getHrEisEmpMst().getOrgEmpMst().getEmpFname() + " " + empLoan.getHrLoanAdvMst().getLoanAdvName() + " " + empLoan.getLoanAccountNo() + " " + empLoan.getLoanPrinEmiAmt() + " " + empLoan.getLoanIntEmiAmt() + " " + empLoan.getLoanInterestAmt() + " " + empLoan.getLoanIntInstNo() + " " + empLoan.getLoanPrinAmt() + " " + empLoan.getLoanPrinInstNo();

				}

				//}
				logger.info("one record is " + x);
				//logger.info("The Size of List is:-"+actionList.size());
				//logger.info("size of loan list is "+newActionList.size());

				/*		for(int l=0;l<actionList.size();l++)
						{
							logger.info("to avoid lazy initialization "+actionList.get(l));
							logger.info("the emp name is "+actionList.get(l).getHrEisEmpMst().getOrgEmpMst().getEmpFname());
						}*/
				objectArgs.put("actionList", actionList);
				objectArgs.put("loanList", actionList);
				objectArgs.put("empId", empId);
				objectArgs.put("loanTotal", loanTotal);
				logger.info("loan Total " + loanTotal);
				resultObject.setResultCode(ErrorConstants.SUCCESS);

				//				added by Ankit Bhatt for merging screens
				if (empAllRec.equalsIgnoreCase("Y"))
				{
					objectArgs.put("empId", empId);
					objectArgs.put("empAllRec", "true");
					resultObject.setViewName("empLoanViewListEmpAllRec");
				}
				else
				{
					//ended by Ankit Bhatt
					if (FromBasicDtlsNew.equals("YES"))
						resultObject.setViewName("empLoanViewListEmpAllRec");
					else
						resultObject.setViewName("empLoanViewList");

				}
				resultObject.setResultValue(objectArgs);
			}
			//end by manoj for employee search
			else
			{
				List<HrLoanEmpDtls> actionList = new ArrayList();//empLoanDAO.getLoanData();
				/*Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");	
				long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/

				/*		      	List userList =empinfodao.getUserListByLogin(langId,userId);
								List <OrgUserMst> orgUserList = new ArrayList();
						      	for (Iterator iter = userList.iterator(); iter.hasNext();)
						        {			 
							    Object[] row = (Object[])iter.next();	
						   		OrgUserMst userMst = new OrgUserMst();
						  		String id=row[0].toString();
						  		userMst.setUserId(Long.parseLong(id));
						  		orgUserList.add(userMst);
						        }*/

				actionList = empLoanDAO.getEmpLoanData(locationCode, 0, langId, onlyActiveLoans);
				objectArgs.put("ViewFlag", "True");

				String x = "";

				for (int i = 0; i < actionList.size(); i++)
				{
					HrLoanEmpDtls empLoan = new HrLoanEmpDtls();
					empLoan = actionList.get(i);
					String tempBuffer = "";
					if (empLoan.getLoanPrinAmt() != 0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
						tempBuffer = currencyFormatter.format(empLoan.getLoanPrinAmt()).replace("$", "");
						empLoan.setCurrencyloanPrinAmt(tempBuffer.replace(".00", ""));
						logger.info("the value of the emploan is ::" + empLoan.getCurrencyloanPrinAmt());
					}

					if (empLoan.getLoanInterestAmt() != 0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
						tempBuffer = currencyFormatter.format(empLoan.getLoanInterestAmt()).replace("$", "");
						empLoan.setCurrencyloanInterestAmt(tempBuffer.replace(".00", ""));
						logger.info("the value of the emploan is ::" + empLoan.getCurrencyloanInterestAmt());
					}
					x = empLoan.getHrEisEmpMst() + " " + empLoan.getHrEisEmpMst().getOrgEmpMst().getEmpFname() + " " + empLoan.getHrLoanAdvMst().getLoanAdvName() + " " + empLoan.getLoanAccountNo() + " " + empLoan.getLoanPrinEmiAmt() + " " + empLoan.getLoanIntEmiAmt() + " " + empLoan.getLoanInterestAmt() + " " + empLoan.getLoanIntInstNo() + " " + empLoan.getLoanPrinAmt() + " " + empLoan.getLoanPrinInstNo();
				}
				logger.info("one record is " + x);
				logger.info("The Size of List is:-" + actionList.size());

				objectArgs.put("actionList", actionList);
				objectArgs.put("empId", "0");
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("empLoanViewList");

			}
			objectArgs.put("empName", empName);
			String empLoanId = (String) voToService.get("empLoanId");
			objectArgs.put("gpf_dcps", new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory()).getEmpDCPS_GPS(empLoanId));
			resultObject.setResultValue(objectArgs);
		}
		catch (Exception e)
		{
			logger.info("Inside Catch.Exception occurs------>");
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}

	public ResultObject fillLoanCombo(Map objectArgs)
	{

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		logger.info("inside fillLoanCombo------------>");
		logger.info("====> inside fillLoanCombo ");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			//Added by mrugesh
			Map loginDetails = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetails.get("langId").toString());
			long languageId = 1;

			long locId = StringUtility.convertToLong(loginDetails.get("locationId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(languageId);
			//Ended by mrugesh
			HrEisEmpMst empMstVO = (HrEisEmpMst) objectArgs.get("empMstVO");

			/*EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			List<HrEisEmpMst> empList =  empinfoDao.getAllEmpData(cmnLanguageMst);
			         
			
			
			objectArgs.put("empList", empList);
			logger.info("The size of Employee list is------->"+empList.size());
			*/
			//HrLoanAdvMst loanMstVO = (HrLoanAdvMst) objectArgs.get("loanMstVO");
			LoanAdvMstDAOImpl loanDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class, serv.getSessionFactory());
			/*List <HrLoanAdvMst> loanList = loanDAO.getAllLoanAdvMasterData(languageId);*/

			List<HrPayEdpCompoMpg> loanList = loanDAO.getMappedLoanAdvList(locId);

			//added by ravysh
			String FromBasicDtlsNew = "";
			String otherId = "";
			if (request.getParameter("FromBasicDtlsNew") != null)
			{
				FromBasicDtlsNew = request.getParameter("FromBasicDtlsNew").toString();
			}

			if (request.getParameter("otherId") != null)
			{
				otherId = request.getParameter("otherId").toString();
			}

			objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
			objectArgs.put("otherId", otherId);
			if (loanList != null && !loanList.isEmpty())
			{
				StringBuilder prinLoanTypeId = new StringBuilder();
				StringBuilder intLoanTypeId = new StringBuilder();

				for (HrPayEdpCompoMpg hrPayEdpCompoMpg : loanList)
				{
					logger.info("To avoid lazy initalization " + hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpShortName());
					//Added By Varshil to Map the Loan with its type (Prin / Int)
					String loanTypeId = hrPayEdpCompoMpg.getTypeId();
					if ("REC".equalsIgnoreCase(hrPayEdpCompoMpg.getRltBillTypeEdp().getExpRcpRec()))
					{
						if ("".equals(String.valueOf(prinLoanTypeId)))
						{
							prinLoanTypeId.append(loanTypeId);
						}
						else
						{
							prinLoanTypeId.append(",").append(loanTypeId);
						}
					}
					else if ("INT".equalsIgnoreCase(hrPayEdpCompoMpg.getRltBillTypeEdp().getExpRcpRec()))
					{
						if ("".equals(String.valueOf(intLoanTypeId)))
						{
							intLoanTypeId.append(loanTypeId);
						}
						else
						{
							intLoanTypeId.append(",").append(loanTypeId);
						}
					}

					//End -  Added By Varshil
				}
				objectArgs.put("prinLoanTypeId", String.valueOf(prinLoanTypeId));
				objectArgs.put("intLoanTypeId", String.valueOf(intLoanTypeId));
			}
			objectArgs.put("loanList", loanList);
			logger.info("The size of Loan List is------->" + loanList.size());
			//		List  <HrLoanEmpDtls> actionList = empLoanDAO.getLoanData();

			//		logger.info("The Size of List is:-"+actionList.size());
			Map map = new HashMap();
			//		objectArgs.put("actionList", actionList);
			resultObject.setResultCode(ErrorConstants.SUCCESS);

			//			added by Ankit Bhatt for merging the Screens

			String empAllRec = "";
			if (request.getParameter("empAllRec") != null)
				empAllRec = request.getParameter("empAllRec");
			long empId = 0;
			if (request.getParameter("empId") != null)
				empId = Long.valueOf(request.getParameter("empId").toString());
			logger.info("EmpId in Loan Insert " + empId);
			if (!empAllRec.equals("") && empAllRec.equalsIgnoreCase("y"))
			{
				resultObject.setViewName("newEmpLoanDtlsEmpAllRec");
				objectArgs.put("empAllRec", "true");
				objectArgs.put("empId", empId);
			}
			else
			{
				//ended by Ankit Bhatt 
				objectArgs.put("empAllRec", "false");
				if (FromBasicDtlsNew.equals("YES"))
					resultObject.setViewName("newEmpLoanDtlsEmpAllRec");
				else
					resultObject.setViewName("newEmpLoanDtls");
			}
			String empname = "";
			if ((request.getParameter("empName") != null && !request.getParameter("empName").equals("")))
				empname = request.getParameter("empName").toString();
			objectArgs.put("empName", empname);
			resultObject.setResultValue(objectArgs);

		}
		catch (Exception e)
		{
			logger.info("Inside Catch.Exception occurs------>");
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}

	public ResultObject getloanAdvDataByID(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("*****************inside getloanAdvDataByID******************");
			logger.info("====> inside getloanAdvDataByID ");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			LoanAdvMstDAOImpl loanDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class, serv.getSessionFactory());
			EmpLoanDAOImpl empLoanDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());

			HrLoanAdvMst hrloanAdvMst = new HrLoanAdvMst();
			HrLoanEmpDtls hrloanEmpDtls = new HrLoanEmpDtls();
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			String loanIdStr = voToService.get("loanId").toString();
			logger.info(" loanIdStr " + loanIdStr);
			//by manoj for ajax request
			if (voToService.get("chkEmpLoan") != null && voToService.get("chkEmpLoan").toString().equalsIgnoreCase("y"))
			{
				long languageId = 1;//for eng language
				logger.info(" empId " + voToService.get("empId").toString());
				long orgEmpId = Long.parseLong(voToService.get("empId").toString());

				OrgEmpMst orgEmpMst = orgEmpDao.getEngGujEmployee(orgEmpDao.read(orgEmpId), languageId);
				logger.info(" empId " + orgEmpMst.getEmpId());

				EmpInfoDAOImpl hrEisEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
				List<HrEisEmpMst> hrEisEmpMstList = hrEisEmpDao.getListByColumnAndValue("orgEmpMst", orgEmpMst);
				long hrEmpId = 0;
				if (hrEisEmpMstList != null && !hrEisEmpMstList.isEmpty())
				{
					hrEmpId = hrEisEmpMstList.get(0).getOrgEmpMst().getEmpId();
					logger.info(" hrEmpId " + hrEmpId);
				}

				OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
				HrEisOtherDtls otherDtls = otherDao.getOtherData(hrEmpId);
				StringBuffer loanMst = new StringBuffer();
				logger.info("the size of emp other list is " + otherDtls);

				if (otherDtls.getOtherId() == 0)
				{
					logger.info("the salary of emp other list is " + otherDtls.getotherCurrentBasic());
					loanMst.append("<loanAdvMapping>");
					loanMst.append("<maxAmount>").append("-1").append("</maxAmount>");
					loanMst.append("</loanAdvMapping>");
				}
				else
				{
					long hrOtherEmpId = otherDtls.getHrEisEmpMst().getEmpId();
					List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(hrEmpId, loanIdStr);

					logger.info("the size of empLoan List is " + hrLoanEmpDtlsList.size());
					if (hrLoanEmpDtlsList != null && !hrLoanEmpDtlsList.isEmpty())
					{
						loanMst.append("<loanAdvMapping>");
						loanMst.append("<maxAmount>").append(hrLoanEmpDtlsList.size()).append("</maxAmount>");
						loanMst.append("</loanAdvMapping>");
					}
					else
					{
						loanMst.append("<loanAdvMapping>");
						loanMst.append("<maxAmount>").append("0").append("</maxAmount>");
						loanMst.append("</loanAdvMapping>");
					}

				}

				String loanDetailsMapping = new AjaxXmlBuilder().addItem("ajax_key", loanMst.toString()).toString();

				logger.info(" the string buffer is :" + loanDetailsMapping);

				objectArgs.put("ajaxKey", loanDetailsMapping);

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ajaxData");
			}//end by manoj for ajax request
			else
			{
				long loanId = Long.parseLong(loanIdStr);
				hrloanAdvMst = loanDAO.read(loanId);

				long maxAmt = hrloanAdvMst.getMaxNoInstallAmt();
				long maxInstallment = hrloanAdvMst.getMaxNoInstInterest();
				StringBuffer loanMst = new StringBuffer();

				loanMst.append("<loanAdvMapping>");
				loanMst.append("<maxAmount>").append(maxAmt).append("</maxAmount>");
				loanMst.append("<maxInstallment>").append(maxInstallment).append("</maxInstallment>");
				loanMst.append("</loanAdvMapping>");
				String loanDetailsMapping = new AjaxXmlBuilder().addItem("ajax_key", loanMst.toString()).toString();

				logger.info(" the string buffer is :" + loanDetailsMapping);

				objectArgs.put("ajaxKey", loanDetailsMapping);

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ajaxData");
			}

		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}

	/**
	 * @author  Urvin Shah
	 * @date	03-03-2009
	 * @purpose	This method will fethch the employee related information for which we are going to give advance.
	 * @param objectArgs
	 * @return
	 */

	public ResultObject getMulipleLoansAdvancesDetails(Map objectArgs)
	{
		logger.info("====> inside getMulipleLoansAdvancesDetails ");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		LoanAdvMstDAOImpl loanDAO; // Data Access Object of LoanAdvMstDAOImpl
		EmpLoanDAOImpl empLoanDao; // Data Access Object of EmpLoanDAOImpl
		EmpDAOImpl orgEmpDao; // Data Access Object of the OrgEmpDAO
		HrLoanAdvMst hrloanAdvMst; // Value Object of the Loan Advance Master
		HrLoanEmpDtls hrloanEmpDtls; // Value Object of the Emp Loan Advance Master
		//List lstGrades;					// List of All Grades.
		//List lstEmployee;				// All the Employees of the logged in location.
		//GradeMasterDAO gradeMasterDAO;
		Map loginDetailsMap; // Map which contains the login information.
		long langId;
		String strDsgnCode = ""; // Designation Code Value.  
		String strGradeCode = ""; // Grade Code Value.
		String strLocationCode = ""; // Location Code.
		CmnLanguageMstDaoImpl cmnLanguageDaoImpl;
		CmnLanguageMst cmnLanguageMst;
		CmnLocationMst cmnLocationMst; // Location VO Object.
		EmpInfoDAO empInfoDAO;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		try
		{
			logger.info("*****************inside getMulipleLoanDetails******************");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			loanDAO = new LoanAdvMstDAOImpl(HrLoanAdvMst.class, serv.getSessionFactory());
			empLoanDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			empInfoDAO = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			orgEmpDao = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
			hrloanAdvMst = new HrLoanAdvMst();
			hrloanEmpDtls = new HrLoanEmpDtls();
			//gradeMasterDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());

			loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			//lstGrades = new ArrayList();
			cmnLanguageDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			cmnLanguageMst = cmnLanguageDaoImpl.read(langId);
			//lstGrades = gradeMasterDAO.getAllGradeMasterData(cmnLanguageMst);
			strLocationCode = cmnLocationMst.getLocationCode();
			//lstEmployee = empInfoDAO.getEmpByLocCodeGradeCodeAndDsgCode(strLocationCode, strGradeCode, strDsgnCode);
			//logger.info("The list of Grades are :-"+lstGrades.size());
			//logger.info("The list of Grades are :-"+lstEmployee.size());
			logger.info("strLocationCode" + strLocationCode);
			logger.info("langId" + langId);
			PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			ResourceBundle resourceBundle1 = ResourceBundle.getBundle("resources.Payroll");
			long finYearId = Long.parseLong(resourceBundle1.getString("finYearId"));
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());

			String ddo_code = null;
			//long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());

			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
			if (ddoCodeList != null)
				logger.info("After query execution for DDO Code " + ddoCodeList.size());

			OrgDdoMst ddoMst = null;
			if (ddoCodeList != null && ddoCodeList.size() > 0)
			{
				ddoMst = ddoCodeList.get(0);
			}

			if (ddoMst != null)
			{
				ddo_code = ddoMst.getDdoCode();
			}
			logger.info("DDO Code is " + ddo_code);

			List billNoList = payBillDAO.getBillNo(finYearId, locId, ddo_code);
			logger.info("The size of billNoList is---->" + billNoList.size());
			ArrayList billList = new ArrayList();
			for (Iterator itr = billNoList.iterator(); itr.hasNext();)
			{
				HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
				Object[] row = (Object[]) itr.next();
				String billNo = row[1].toString();
				String billHeadId = row[0].toString();
				billNoCustomVO.setBillId(billNo);
				billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
				billList.add(billNoCustomVO);
			}

			objectArgs.put("lstBills", billList);
			logger.info("size of billList" + billList.size());
			//objectArgs.put("lstGrades", lstGrades);
			//objectArgs.put("lstEmployees", lstEmployee);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("AddMultipleEmpLoanAdvances");
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;
	}

	public ResultObject addMultipleLoanFoodSrvc(Map objectArgs)
	{
		logger.info("inside addMultipleLoanFoodSrvc---------->");
		logger.info("====> inside addMultipleLoanFoodSrvc ");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try
		{

			logger.info("*********1");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long empID = 0;

			HrLoanEmpDtls hrLoanEmpDtls = new HrLoanEmpDtls();
			HrLoanEmpPrinRecoverDtls hrLoanEmpPrinRecoverDtls = null;
			HrLoanEmpIntRecoverDtls hrLoanEmpIntRecoverDtls = null;

			EmpLoanDAOImpl empLoanDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			LoanCustomVO loanCustomVO = new LoanCustomVO();
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
			//LoanEmpIntRecvDAOImpl loanEmpIntRecvDAO = new LoanEmpIntRecvDAOImpl (HrLoanEmpIntRecoverDtls.class,serv.getSessionFactory());

			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			logger.info("*********2**UserId" + userId);
			Date sysdate = new Date();

			int count = 0;
			List lstEmpIdList = new ArrayList();
			List empLoanVOList = new ArrayList();
			Date sysDate = new java.util.Date();

			logger.info("*********3");
			if (objectArgs.get("lstEmpIdList") != null)
				lstEmpIdList = (List) objectArgs.get("lstEmpIdList");
			logger.info("the size of lstEmpIdList is--------" + lstEmpIdList.size());
			logger.info("*********4");
			if (objectArgs.get("empLoanVOList") != null)
				empLoanVOList = (List) objectArgs.get("empLoanVOList");

			logger.info("the size of empLoanVOList is--------" + empLoanVOList.size());
			count = Integer.parseInt(objectArgs.get("count").toString());

			String ctr = objectArgs.get("count") != null ? objectArgs.get("count").toString() : "0";
			count = Integer.parseInt(ctr);
			logger.info("Value of count is--------" + count);
			IdGenerator idGen = new IdGenerator();

			for (int i = 0; i < count; i++)
			{
				long empId = 0;
				empId = Long.parseLong(lstEmpIdList.get(i).toString());
				logger.info("empId:in service " + empId);
				long leaveTypeId = 0;

				hrLoanEmpDtls = new HrLoanEmpDtls();
				loanCustomVO = (LoanCustomVO) empLoanVOList.get(i);

				//set empLoanId

				long empLoanId = idGen.PKGenerator("HR_LOAN_EMP_DTLS", objectArgs);
				//long empLoanId = IDGenerateDelegate.getNextId("HR_LOAN_EMP_DTLS", objectArgs);
				hrLoanEmpDtls.setEmpLoanId(empLoanId);
				logger.info("EmpLoanId: " + hrLoanEmpDtls.getEmpLoanId());

				//set empId
				/* N.B: As employee id received from jsp is of OrgEmpMst we'll have to set it to HrEisEmpMst */
				//hrEmpId = loanCustomVO.getEmpId();
				//logger.info("employee id recvd from VOGEN hrEmpId is: " +hrEmpId);
				List lstEmpList = empInfoDAOImpl.getEmpIdData(empId);
				logger.info("lstEmpList means data of employee" + lstEmpList);
				if (lstEmpList != null && !lstEmpList.isEmpty())
				{
					HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
					hrEisEmpMst = (HrEisEmpMst) lstEmpList.get(0);
					hrLoanEmpDtls.setHrEisEmpMst(hrEisEmpMst);
					logger.info("EmpId is: " + hrLoanEmpDtls.getHrEisEmpMst().getEmpId());
				}

				//set loanDate
				hrLoanEmpDtls.setLoanDate(loanCustomVO.getLoanDate());
				logger.info("hrLoanEmpDtls.getLoanDate() is: " + hrLoanEmpDtls.getLoanDate());

				//set loanSanctionOrderNumber
				hrLoanEmpDtls.setLoanSancOrderNo(loanCustomVO.getLoanSancOrderNo());
				logger.info("hrLoanEmpDtls.getLoanSancOrderNo() is: " + hrLoanEmpDtls.getLoanSancOrderNo());
				//added by khushal
				//set loanSanctionOrder Date
				hrLoanEmpDtls.setLoanSancOrderdate(loanCustomVO.getLoanSancOrderDate());
				logger.info("hrLoanEmpDtls.getLoanSancOrderDate() is: " + hrLoanEmpDtls.getLoanSancOrderdate());
				//ended  by khushal

				//set loanPrincipalAmount

				hrLoanEmpDtls.setLoanPrinAmt(loanCustomVO.getLoanPrinAmt());
				logger.info("hrLoanEmpDtls.getLoanPrinAmt is: " + hrLoanEmpDtls.getLoanPrinAmt());

				//set loanPrinciaplInstallmentNumber
				hrLoanEmpDtls.setLoanPrinInstNo(loanCustomVO.getLoanPrinInstNo());
				logger.info("hrLoanEmpDtls.getLoanPrinInstNo() is: " + hrLoanEmpDtls.getLoanPrinInstNo());

				//set loanPrincipalEMIAmount
				hrLoanEmpDtls.setLoanPrinEmiAmt(loanCustomVO.getLoanPrinEmiAmt());
				logger.info("hrLoanEmpDtls.getLoanPrinEmiAmt() is: " + hrLoanEmpDtls.getLoanPrinEmiAmt());

				//set databaseId
				hrLoanEmpDtls.setCmnDatabaseMst(cmnDatabaseMst);
				//logger.info("hrLoanEmpDtls.getCmnDatabaseMst().getDbId() is: " +hrLoanEmpDtls.getCmnDatabaseMst().getDbId());

				//set locationId
				hrLoanEmpDtls.setCmnLocationMst(cmnLocationMst);
				//logger.info("hrLoanEmpDtls.getCmnLocationMst().getLocId() is: " +hrLoanEmpDtls.getCmnLocationMst().getLocId());

				//set createdBy
				hrLoanEmpDtls.setOrgUserMstByCreatedBy(orgUserMst);
				//logger.info("hrLoanEmpDtls.getOrgUserMstByCreatedBy().getUserId() is: " +hrLoanEmpDtls.getOrgUserMstByCreatedBy().getUserId());

				//set createdByPost
				hrLoanEmpDtls.setOrgPostMstByCreatedByPost(orgPostMst);
				//logger.info("hrLoanEmpDtls.getOrgPostMstByCreatedByPost().getPostId() is: " +hrLoanEmpDtls.getOrgPostMstByCreatedByPost().getPostId());

				//set createdDate
				hrLoanEmpDtls.setCreatedDate(sysDate);
				logger.info("hrLoanEmpDtls.getCreatedDate() is: " + hrLoanEmpDtls.getCreatedDate());

				//set trnCounter
				hrLoanEmpDtls.setTrnCounter(new Integer(1));
				logger.info("hrLoanEmpDtls.getTrnCounter() is: " + hrLoanEmpDtls.getTrnCounter());

				//set loanTypeId
				HrLoanAdvMst hrLoanAdvMst = new HrLoanAdvMst();
				hrLoanAdvMst.setLoanAdvId(loanCustomVO.getLoanTypeId());
				hrLoanEmpDtls.setHrLoanAdvMst(hrLoanAdvMst);
				//logger.info("LoanTypeId: "+hrLoanEmpDtls.getHrLoanAdvMst().getLoanAdvId());

				//set loanActivateFlag
				hrLoanEmpDtls.setLoanActivateFlag(loanCustomVO.getLoanActivateFlag());
				logger.info("hrLoanEmpDtls.getLoanActivateFlag() is: " + hrLoanEmpDtls.getLoanActivateFlag());

				hrLoanEmpDtls.setIsApproved(0);

				hrLoanEmpDtls.setLoanAccountNo(loanCustomVO.getLoanAcNo());
				logger.info("LoanAccountNo: " + hrLoanEmpDtls.getLoanAccountNo());

				hrLoanEmpDtls.setVoucherNo(loanCustomVO.getLoanVoucherNo());
				logger.info("LoanVoucherNo: " + hrLoanEmpDtls.getVoucherNo());

				hrLoanEmpDtls.setVoucherDate(loanCustomVO.getLoanVoucherDate());
				logger.info("LoanVoucherDate: " + hrLoanEmpDtls.getVoucherDate());

				hrLoanEmpDtls.setLoanOddinstno(loanCustomVO.getLoanOddinstno());
				logger.info("LoanOddinstno: " + hrLoanEmpDtls.getLoanOddinstno());

				hrLoanEmpDtls.setLoanOddinstAmt(loanCustomVO.getLoanOddinstAmt());
				logger.info("LoanOddinstAmt: " + hrLoanEmpDtls.getLoanOddinstAmt());

				/* ----- SET "HrLoanEmpPrinRecoverDtls" RELATED INFORMATION ----- */

				hrLoanEmpPrinRecoverDtls = new HrLoanEmpPrinRecoverDtls();

				//set princRecoveredId
				Long prinId = idGen.PKGenerator("HR_LOAN_EMP_PRIN_RECOVER_DTLS", objectArgs);
				//Long prinId=IDGenerateDelegate.getNextId("HR_LOAN_EMP_PRIN_RECOVER_DTLS",objectArgs);
				hrLoanEmpPrinRecoverDtls.setPrinRecoverId(prinId);
				logger.info("Pk Gen for princReoveredId: " + prinId);

				//set hrLoanEmpDtls fk
				hrLoanEmpPrinRecoverDtls.setHrLoanEmpDtls(hrLoanEmpDtls);
				logger.info("loanEmpId is: " + hrLoanEmpPrinRecoverDtls.getHrLoanEmpDtls().getEmpLoanId());

				//set totalRecoveredAmount
				hrLoanEmpPrinRecoverDtls.setTotalRecoveredAmt(loanCustomVO.getLoanPrinRecovAmt());
				logger.info("hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt() is: " + hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt());

				//set totalRecoveredInstallment
				hrLoanEmpPrinRecoverDtls.setTotalRecoveredInst(loanCustomVO.getLoanPrinRecovInt());
				logger.info("hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst() is: " + hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst());

				//set databaseId
				hrLoanEmpPrinRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
				//logger.info("hrLoanEmpPrinRecoverDtls.getCmnDatabaseMst().getDbId() is: " +hrLoanEmpPrinRecoverDtls.getCmnDatabaseMst().getDbId());

				//set createdByPost
				hrLoanEmpPrinRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
				//logger.info("hrLoanEmpPrinRecoverDtls.getOrgPostMstByCreatedByPost().getPostId() is: " +hrLoanEmpPrinRecoverDtls.getOrgPostMstByCreatedByPost().getPostId());

				//set createdBy
				hrLoanEmpPrinRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
				//logger.info("hrLoanEmpPrinRecoverDtls.getOrgUserMstByCreatedBy().getUserId() is: " +hrLoanEmpPrinRecoverDtls.getOrgUserMstByCreatedBy().getUserId());

				//set createdDate
				hrLoanEmpPrinRecoverDtls.setCreatedDate(sysDate);
				logger.info("hrLoanEmpPrinRecoverDtls.getCreatedDate() is: " + hrLoanEmpPrinRecoverDtls.getCreatedDate());

				//set locationId
				hrLoanEmpPrinRecoverDtls.setCmnLocationMst(cmnLocationMst);
				//logger.info("hrLoanEmpPrinRecoverDtls.getCmnLocationMst().getLocId() is: " +hrLoanEmpPrinRecoverDtls.getCmnLocationMst().getLocId());

				//set trnCounter
				hrLoanEmpPrinRecoverDtls.setTrnCounter(new Integer(1));
				logger.info("hrLoanEmpPrinRecoverDtls.getTrnCounter() is: " + hrLoanEmpPrinRecoverDtls.getTrnCounter());

				/* ----- SET "HrLoanEmpIntRecoverDtls" RELATED INFORMATION ----- */

				hrLoanEmpIntRecoverDtls = new HrLoanEmpIntRecoverDtls();

				//set intRecoverId
				long intId = idGen.PKGenerator("HR_LOAN_EMP_INT_RECOVER_DTLS", objectArgs);
				//long intId = IDGenerateDelegate.getNextId("HR_LOAN_EMP_INT_RECOVER_DTLS",objectArgs);
				hrLoanEmpIntRecoverDtls.setIntRecoverId(intId);
				logger.info("Pk Gen: IntRecoverId: " + intId);

				//set empLoanId fk
				hrLoanEmpIntRecoverDtls.setHrLoanEmpDtls(hrLoanEmpDtls);
				logger.info("setting empLoanId: " + hrLoanEmpIntRecoverDtls.getHrLoanEmpDtls().getEmpLoanId());

				//set totalRecoveredInterest
				hrLoanEmpIntRecoverDtls.setTotalRecoveredInt(loanCustomVO.getLoanIntRecovAmt());
				logger.info("hrLoanEmpIntRecoverDtls.getTotalRecoveredInt() is: " + hrLoanEmpIntRecoverDtls.getTotalRecoveredInt());

				//set totalRecoveredInterestInstallments
				hrLoanEmpIntRecoverDtls.setTotalRecoveredIntInst(loanCustomVO.getLoanIntRecovInt());
				logger.info("hrLoanEmpIntRecoverDtls.getTotalRecoveredIntInst() is: " + hrLoanEmpIntRecoverDtls.getTotalRecoveredIntInst());

				//set databaseId
				hrLoanEmpIntRecoverDtls.setCmnDatabaseMst(cmnDatabaseMst);
				//logger.info("hrLoanEmpIntRecoverDtls.getCmnDatabaseMst().getDbId() is: " +hrLoanEmpIntRecoverDtls.getCmnDatabaseMst().getDbId());

				//set locationId
				hrLoanEmpIntRecoverDtls.setCmnLocationMst(cmnLocationMst);
				//logger.info("hrLoanEmpIntRecoverDtls.getCmnLocationMst().getLocId() is: " +hrLoanEmpIntRecoverDtls.getCmnLocationMst().getLocId());

				//set createdByPost
				hrLoanEmpIntRecoverDtls.setOrgPostMstByCreatedByPost(orgPostMst);
				//logger.info("hrLoanEmpIntRecoverDtls.getOrgPostMstByCreatedByPost().getPostId() is: " +hrLoanEmpIntRecoverDtls.getOrgPostMstByCreatedByPost().getPostId());

				//set created by
				hrLoanEmpIntRecoverDtls.setOrgUserMstByCreatedBy(orgUserMst);
				//logger.info("hrLoanEmpIntRecoverDtls.getOrgUserMstByCreatedBy().getUserId() is: " +hrLoanEmpIntRecoverDtls.getOrgUserMstByCreatedBy().getUserId());

				//set created date
				hrLoanEmpIntRecoverDtls.setCreatedDate(sysDate);
				logger.info("hrLoanEmpIntRecoverDtls.getCreatedDate() is: " + hrLoanEmpIntRecoverDtls.getCreatedDate());

				//set trnCounter
				hrLoanEmpIntRecoverDtls.setTrnCounter(new Integer(1));
				logger.info("hrLoanEmpIntRecoverDtls.getTrnCounter() is: " + hrLoanEmpIntRecoverDtls.getTrnCounter());

				//Initiate DAO's
				LoanEmpPrinRecvDAOImpl loanEmpPrinRecvDAO = new LoanEmpPrinRecvDAOImpl(HrLoanEmpPrinRecoverDtls.class, serv.getSessionFactory());
				LoanEmpIntRecvDAOImpl loanEmpIntRecvDAO = new LoanEmpIntRecvDAOImpl(HrLoanEmpIntRecoverDtls.class, serv.getSessionFactory());

				//write into database
				try
				{
					//emp loan dtls
					empLoanDao.create(hrLoanEmpDtls);

					//principle dtls
					loanEmpPrinRecvDAO.create(hrLoanEmpPrinRecoverDtls);

					loanEmpIntRecvDAO.create(hrLoanEmpIntRecoverDtls);//int dtls

				}
				catch (Exception e)
				{
					logger.info("Exception Occured in writing to database" + e.getMessage());
					//logger.error("Error is: "+ e.getMessage());
				}

			}//end for

			//redirect screen
			List<HrLoanEmpDtls> actionList = new ArrayList();//empLoanDAO.getLoanData();

			actionList = empLoanDao.getEmpLoanData(cmnLocationMst.getLocationCode(), 0, langId);
			objectArgs.put("ViewFlag", "True");

			String x = "";
			String loanNameTemp = "";

			for (int i = 0; i < actionList.size(); i++)
			{
				HrLoanEmpDtls empLoan = new HrLoanEmpDtls();
				empLoan = actionList.get(i);
				String tempBuffer = "";
				x = "";

				if (empLoan.getLoanPrinAmt() != 0)
				{
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
					tempBuffer = currencyFormatter.format(empLoan.getLoanPrinAmt()).replace("$", "");
					empLoan.setCurrencyloanPrinAmt(tempBuffer.replace(".00", ""));
					logger.info("the value of the emploan is ::" + empLoan.getCurrencyloanPrinAmt());
				}

				if (empLoan.getLoanInterestAmt() != 0)
				{
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
					tempBuffer = currencyFormatter.format(empLoan.getLoanInterestAmt()).replace("$", "");
					empLoan.setCurrencyloanInterestAmt(tempBuffer.replace(".00", ""));
					logger.info("the value of the emploan is ::" + empLoan.getCurrencyloanInterestAmt());
				}

				x = empLoan.getHrEisEmpMst() + " " + empLoan.getHrEisEmpMst().getOrgEmpMst().getEmpFname() + " " + empLoan.getHrLoanAdvMst().getLoanAdvName() + " " + empLoan.getLoanAccountNo() + " " + empLoan.getLoanPrinEmiAmt() + " " + empLoan.getLoanIntEmiAmt() + " " + empLoan.getLoanInterestAmt() + " " + empLoan.getLoanIntInstNo() + " " + empLoan.getLoanPrinAmt() + " " + empLoan.getLoanPrinInstNo();
				loanNameTemp = empLoan.getHrLoanAdvMst().getLoanAdvName();
			}

			logger.info("one record is " + x);
			logger.info("Advance Name" + loanNameTemp);
			logger.info("The Size of List is:-" + actionList.size());

			objectArgs.put("actionList", actionList);
			objectArgs.put("empId", "0");

			Map redirectMap = objectArgs;
			objectArgs.put("MESSAGECODE", 300005);
			redirectMap.put("actionFlag", "getLoanValue");
			objectArgs.put("empAllRec", "false");
			objectArgs.put("redirectMap", redirectMap);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("empLoanViewList");

		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");
		}

		return resultObject;
	}//end method: addMultipleLoanFoodSrvc()

	//added by praveen

	public ResultObject getEmployeeByBillNo(Map objectArgs)
	{
		logger.info("*********inside getEmployeeByBillNo*********");

		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class, serv.getSessionFactory());

		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		String locationCode = (cmnLocationMst.getLocationCode() != null && !cmnLocationMst.getLocationCode().trim().equals("")) ? cmnLocationMst.getLocationCode() : "";
		long billNo = 0;
		long loanAdvId = 0;

		Map voToService = (Map) objectArgs.get("voToServiceMap");
		logger.info("***************************" + voToService);
		billNo = Long.parseLong((String) voToService.get("billNo"));
		logger.info("gradeId in Service:--->" + billNo);
		loanAdvId = Long.parseLong((String) voToService.get("loanAdvId"));
		logger.info("gradeId in Service:--->" + loanAdvId);
		Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
		logger.info("langId in Service:--->" + langId);
		logger.info("locationCode in Service:--->" + locationCode);
		EmpLoanDAOImpl empLoanDAOImpl = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
		List empList = empLoanDAOImpl.getEmpsByBillNoForBulkFA(langId, billNo, loanAdvId);

		String emptype = "";
		logger.info("the size of emp list is " + empList.size());

		//List<HrEisGdMpg> gdMpgList = new ArrayList();
		List<OrgEmpMst> empMpgList = new ArrayList();
		StringBuffer propertyList = new StringBuffer();
		for (int i = 0; i < empList.size(); i++)
		{
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			//        	orgEmpMst=(OrgEmpMst)empList.get(i);
			Object[] object = (Object[]) empList.get(i);
			logger.info(object[0]);
			logger.info(object[1]);
			logger.info(object[2]);
			logger.info(object[3]);

			propertyList.append("<Employee-mapping>");
			propertyList.append("<Employee-Id>").append(object[0]).append("</Employee-Id>");
			propertyList.append("<Employee-Fname>").append("<![CDATA[").append(object[1]).append("]]>").append("</Employee-Fname>");
			propertyList.append("<Employee-Mname>").append("<![CDATA[").append(object[2]).append("]]>").append("</Employee-Mname>");
			propertyList.append("<Employee-Lname>").append("<![CDATA[").append(object[3]).append("]]>").append("</Employee-Lname>");
			propertyList.append("</Employee-mapping>");

			//HrEisGdMpg HrEisGdMpggu=new HrEisGdMpg();
			//empMpgList.add(resultSet.get(i));

			/*HrEisGdMpggu.setGdMapId(hrEisGdMpg.getGdMapId());
			boolean flag=false;
			if(langId!=1)
			{
				long LangId=2;
				String gradeElementCode= hrEisGdMpg.getOrgDesignationMst().getDsgnCode();
				            			            			
				List<OrgDesignationMst> desig = desigMasterDAO.getDesigName(gradeElementCode, LangId);
				if(desig.size()>0)
				{
					
					HrEisGdMpggu.setOrgDesignationMst(desig.get(0));
					flag = true;
				}
				
				
			}
			else
			{
				HrEisGdMpggu.setOrgDesignationMst(hrEisGdMpg.getOrgDesignationMst());
				flag = true;
			}
			if(flag)*/
			empMpgList.add(orgEmpMst);

		}

		/*StringBuffer propertyList = new StringBuffer();
		 for (Iterator iter = empMpgList.iterator(); iter.hasNext();)
		 {			 
		  OrgEmpMst orgEmpMst=new OrgEmpMst();
		  orgEmpMst=(OrgEmpMst) iter.next();
		  long EmpId = orgEmpMst.getEmpId();
		  String EmpFName = (String) orgEmpMst.getEmpFname();
		  String EmpMName = (String) orgEmpMst.getEmpMname();
		  String EmpLName = (String) orgEmpMst.getEmpLname();
		  propertyList.append("<Employee-mapping>");   	
		  propertyList.append("<Employee-Id>").append(EmpId).append("</Employee-Id>");
		  propertyList.append("<Employee-Fname>").append("<![CDATA[").append(EmpFName).append("]]>").append("</Employee-Fname>");               
		  propertyList.append("<Employee-Mname>").append("<![CDATA[").append(EmpMName).append("]]>").append("</Employee-Mname>");
		  propertyList.append("<Employee-Lname>").append("<![CDATA[").append(EmpLName).append("]]>").append("</Employee-Lname>");
		  propertyList.append("</Employee-mapping>");
		 }*/
		Map result = new HashMap();
		String Desigdata = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
		result.put("ajaxKey", Desigdata);

		resultObject.setResultValue(result);
		resultObject.setViewName("ajaxData");

		logger.info("After Service Called.........\n" + Desigdata);
		return resultObject;

	}
	//added by praveen	

	//added by Amish	
	public ResultObject reduceLoanInstBillwise(Map objectArgs)
	{
		logger.info("*********inside reduceLoanInstBillwise*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.ERROR);
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		logger.info("***************************" + voToService);
		Date todayDate = new Date();
		int month = voToService.get("month") != null ? Integer.parseInt((String) voToService.get("month")) : todayDate.getMonth() + 1;
		int year = voToService.get("year") != null ? Integer.parseInt((String) voToService.get("year")) : todayDate.getYear() + 1900;
		CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
		long langId = 0;
		try
		{
			langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			if (voToService.get("revertFlag") != null && voToService.get("revertFlag").toString().equalsIgnoreCase("true"))
			{
				EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
				long billNo = 0;
				billNo = Long.parseLong((String) voToService.get("billNo"));
				logger.info("langId in Service:--->" + langId);
				boolean successFlag = empLoanDAO.revertInstallments(billNo, month, year);
				if (successFlag == true)
					objectArgs.put("msg", "Loan Installments Successfully Revertd.");
				else
					objectArgs.put("msg", "There is some Problem in reversion.");
				resultObject.setViewName("loanRevert");
			}
			else if (voToService.get("revertFlag") != null && voToService.get("revertFlag").toString().equalsIgnoreCase("false"))
			{
				CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
				long locId = cmnLocationMst.getLocId();
				PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());
				List billList = paybillHeadMpgDAOImpl.getGeneratedBillList(locId, month, year);
				int size = billList.size();
				Object[] row = null;
				StringBuffer propertyList = new StringBuffer();
				for (int i = 0; i < size; i++)
				{
					row = (Object[]) billList.get(i);
					Long billNo = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
					String billDesc = row[1] != null ? row[1].toString() : "";
					propertyList.append("<bill-data>");
					propertyList.append("<billNo>" + billNo + "</billNo>");
					propertyList.append("<billDesc>" + billDesc.trim() + "</billDesc>");
					propertyList.append("</bill-data>");
					String billListStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
					logger.info("final string is " + billListStr);
					objectArgs.put("ajaxKey", billListStr);
					resultObject.setViewName("ajaxData");
				}
			}
			else
			{
				List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
				List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
				objectArgs.put("paybillMonth", monthList);
				objectArgs.put("paybillYear", yearList);
				resultObject.setViewName("loanRevert");
			}
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
		}
		catch (Exception e)
		{
			logger.error("Error is" + e);
			resultObject.setResultCode(ErrorConstants.ERROR);
		}

		return resultObject;
	}

}
