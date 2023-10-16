

package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrLeaveEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.LoanCustomVO;

public class EmpLoanVOGen extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	Date sysdate = new Date();
	public ResultObject insertEmpLoan(Map objectArgs){
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
				
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		Map mp = objectArgs;
		
		HrLoanEmpDtls empLoanVO = new HrLoanEmpDtls();
		HrLoanEmpPrinRecoverDtls loanRecvVO = new HrLoanEmpPrinRecoverDtls();
		HrLoanEmpIntRecoverDtls intLoanRecvVO = new HrLoanEmpIntRecoverDtls();
		
		long empId;
		long loanTypeId;
		long loanPrinAmt;
		long loanIntAmt;
		long loanPrinInstNo;
		long loanIntInstNo;
		long loanPrinEmiAmt;
		long loanIntEmiAmt;
		String loanAcNo;
		String loanSancOrderNo;
		/*added by khushal*/
	    String sancOrderDate;
	    /*end by khushal*/
		String lnDate;
		
		Integer loanActivateFlag;
		Integer loanRecoveryModeFlag;
		
		long loanPrinRecovAmt=0;
		long loanPrinRecovInt=0;
		long loanIntRecovAmt=0;
		long loanIntRecovInt=0;
		
		long loanOddinstno=0;
		long loanOddinstAmt=0;
		
		//added by Ankit bhatt for Maha Payroll
		String loanVoucherNo=null;
		String strLoanVoucherDate = null;
		Date loanVoucherDate = null;
		//ended
		
		
		empId=(StringUtility.getParameter("Employee_ID_EmpSearch", request)!=null&&!(StringUtility.getParameter("Employee_ID_EmpSearch", request).equals(""))?Long.parseLong(StringUtility.getParameter("Employee_ID_EmpSearch", request)):0);
		loanTypeId=(StringUtility.getParameter("loanName", request)!=null&&!(StringUtility.getParameter("loanName", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanName", request)):0);
		loanPrinAmt=(StringUtility.getParameter("principalAmt", request)!=null&&!(StringUtility.getParameter("principalAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("principalAmt", request)):0);
		loanIntAmt=(StringUtility.getParameter("interestAmt", request)!=null&&!(StringUtility.getParameter("interestAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("interestAmt", request)):0);
		loanPrinInstNo=(StringUtility.getParameter("principalInstNo", request)!=null&&!(StringUtility.getParameter("principalInstNo", request).equals(""))?Long.parseLong(StringUtility.getParameter("principalInstNo", request)):0);
		loanIntInstNo=(StringUtility.getParameter("interestInstNo", request)!=null&&!(StringUtility.getParameter("interestInstNo", request).equals(""))?Long.parseLong(StringUtility.getParameter("interestInstNo", request)):0);
		loanPrinEmiAmt=(StringUtility.getParameter("loanPrinEmiAmt", request)!=null&&!(StringUtility.getParameter("loanPrinEmiAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanPrinEmiAmt", request)):0);
		loanIntEmiAmt=(StringUtility.getParameter("loanIntEmiAmt", request)!=null&&!(StringUtility.getParameter("loanIntEmiAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanIntEmiAmt", request)):0);
		loanAcNo=(StringUtility.getParameter("loanACNo", request)!=null&&!(StringUtility.getParameter("loanACNo", request).equals(""))?(StringUtility.getParameter("loanACNo", request)):" ").toString();
		loanSancOrderNo=(StringUtility.getParameter("loanSancOrderNo", request)!=null&&!(StringUtility.getParameter("loanSancOrderNo", request).equals(""))?(StringUtility.getParameter("loanSancOrderNo", request)):" ").toString();
		sancOrderDate=(StringUtility.getParameter("loanSancOrderDate_Hidden", request)!=null&&!(StringUtility.getParameter("loanSancOrderDate_Hidden", request).equals(""))?(StringUtility.getParameter("loanSancOrderDate_Hidden", request)):" ").toString();
		logger.info("sancOrderDate--------"+sancOrderDate);
		//Added By Ankit
		String ordrNo= null;
		String ordrDat= null;
		if(request.getParameter("ordrNo")!=null)
			ordrNo=request.getParameter("ordrNo").toString();
		
		if(request.getParameter("ordrDat")!=null)
			ordrDat=request.getParameter("ordrDat").toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date ordrDate= sdf.parse(ordrDat);
				
		lnDate=(StringUtility.getParameter("loanDate_Hidden", request)!=null&&!(StringUtility.getParameter("loanDate_Hidden", request).equals(""))?(StringUtility.getParameter("loanDate_Hidden", request)):" ").toString();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date loanDate=sdf.parse(lnDate);
		Date loanSancOrderDate=sdf.parse(sancOrderDate);
		
		loanActivateFlag=(StringUtility.getParameter("loanActivateFlag", request)!=null&&!(StringUtility.getParameter("loanActivateFlag", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanActivateFlag", request)):1);
		//Added/Modified By Varshil
		/*
		loanPrinRecovAmt=(StringUtility.getParameter("loanPrinRecovAmt", request)!=null&&!(StringUtility.getParameter("loanPrinRecovAmt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanPrinRecovAmt", request)):0);
		loanPrinRecovInt=(StringUtility.getParameter("loanPrinRecovInt", request)!=null&&!(StringUtility.getParameter("loanPrinRecovInt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanPrinRecovInt", request)):0);
		loanIntRecovAmt=(StringUtility.getParameter("loanIntRecovAmt", request)!=null&&!(StringUtility.getParameter("loanIntRecovAmt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanIntRecovAmt", request)):0);
		loanIntRecovInt=(StringUtility.getParameter("loanIntRecovInt", request)!=null&&!(StringUtility.getParameter("loanIntRecovInt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanIntRecovInt", request)):0);
		*/
		
		loanPrinRecovAmt=(StringUtility.getParameter("principalRecoveredCalcAmount", request)!=null&&!(StringUtility.getParameter("principalRecoveredCalcAmount", request).equals(""))?Integer.parseInt(StringUtility.getParameter("principalRecoveredCalcAmount", request)):0);
		loanPrinRecovInt=(StringUtility.getParameter("pricipalRecoveredNumber", request)!=null&&!(StringUtility.getParameter("pricipalRecoveredNumber", request).equals(""))?Integer.parseInt(StringUtility.getParameter("pricipalRecoveredNumber", request)):0);
		loanIntRecovAmt=(StringUtility.getParameter("interestRecoveredCalcAmount", request)!=null&&!(StringUtility.getParameter("interestRecoveredCalcAmount", request).equals(""))?Integer.parseInt(StringUtility.getParameter("interestRecoveredCalcAmount", request)):0);
		loanIntRecovInt=(StringUtility.getParameter("interestRecoveredNumber", request)!=null&&!(StringUtility.getParameter("interestRecoveredNumber", request).equals(""))?Integer.parseInt(StringUtility.getParameter("interestRecoveredNumber", request)):0);
		loanRecoveryModeFlag=(StringUtility.getParameter("loanRecoveryModeFlag", request)!=null&&!(StringUtility.getParameter("loanRecoveryModeFlag", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanRecoveryModeFlag", request)):0);
		//End Added/Modified By Varshil for Loan Amount / Inst Updation
		
		//added by ravysh
		String FromBasicDtlsNew=(StringUtility.getParameter("FromBasicDtlsNew", request)!=null?StringUtility.getParameter("FromBasicDtlsNew", request):"");
		String otherId=(StringUtility.getParameter("otherId", request)!=null?StringUtility.getParameter("otherId", request):"");
		
		//Added by Javed
		loanOddinstno=(StringUtility.getParameter("loanOddinstno", request)!=null&&!(StringUtility.getParameter("loanOddinstno", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanOddinstno", request)):0);
		loanOddinstAmt=(StringUtility.getParameter("loanOddinstAmt", request)!=null&&!(StringUtility.getParameter("loanOddinstAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanOddinstAmt", request)):0);
		
		//added by Ankit Bhatt for Maha Payroll
		//Loan Voucher No
		loanVoucherNo=(StringUtility.getParameter("loanVoucherNo", request)!=null&&!(StringUtility.getParameter("loanVoucherNo", request).equals(""))?StringUtility.getParameter("loanVoucherNo", request).toString():null);
		logger.info("Loan Voucher No: " + loanIntRecovInt);		
		
		strLoanVoucherDate=(StringUtility.getParameter("loanVoucherDate_Hidden", request)!=null&&!(StringUtility.getParameter("loanVoucherDate_Hidden", request).equals(""))?(StringUtility.getParameter("loanVoucherDate_Hidden", request)):" ").toString();
	    if(strLoanVoucherDate!=null && !strLoanVoucherDate.trim().equals(""))
		loanVoucherDate=sdf.parse(strLoanVoucherDate);
		  logger.info("Loan Date No is: " +loanVoucherDate);
		//ended
		
		logger.info("Loan loanOddinstno ----->"+loanOddinstno);
        logger.info("Loan loanOddinstAmt Amt----->"+loanOddinstAmt);
        logger.info("===> loanOddinstno :: "+loanOddinstno);
        logger.info("===> loanOddinstAmt :: "+loanOddinstAmt);
		//Added by Javed
		
		logger.info("Loan Principal Amt----->"+loanPrinAmt);
        logger.info("Loan Interest Amt----->"+loanIntAmt);
        logger.info("Loan Prin Installment No----->"+loanPrinInstNo);
        logger.info("Loan Int Installment No----->"+loanIntInstNo);
        logger.info("Loan EMI----->"+loanPrinEmiAmt);
        logger.info("Loan EMI----->"+loanIntEmiAmt);
        logger.info("Loan Account No----->"+loanAcNo);
        
        logger.info("loanPrinRecovAmt----->"+loanPrinRecovAmt);
        logger.info("loanPrinRecovInt----->"+loanPrinRecovInt);
        logger.info("loanIntRecovAmt----->"+loanIntRecovAmt);
        logger.info("loanIntRecovInt----->"+loanIntRecovInt);
        
        String editMode = StringUtility.getParameter("edit",request);
        long empLoanId=0;
        
        objectArgs.put("empId",empId);
        objectArgs.put("loanTypeId",loanTypeId);
        
        objectArgs.put("FromBasicDtlsNew",FromBasicDtlsNew);
        objectArgs.put("otherId",otherId);
     
        objectArgs.put("loanOddinstno",loanOddinstno);
        objectArgs.put("loanOddinstAmt",loanOddinstAmt);
        
        if(editMode.equalsIgnoreCase("N")) 
		{		
        	objectArgs.put("edit","N");
		}
        else
		{
			logger.info("Inside EmpLoanVOGen Edit code");
        	String emploanid=(StringUtility.getParameter("empLoanId", request)!=null&&!(StringUtility.getParameter("empLoanId", request).equals(""))?(StringUtility.getParameter("empLoanId", request)):0).toString();
        	if(emploanid!=null)
        	{
        		empLoanId=Long.parseLong(emploanid);
        	}
        	
        	empLoanVO.setEmpLoanId(empLoanId);
        	objectArgs.put("edit","Y");
        }
        
        if(loanRecoveryModeFlag.intValue() == 1 ){
			String mulRecRemarks=StringUtility.getParameter("mulRecRemarks", request);
			String strIntallmentsToRecover=StringUtility.getParameter("intallmentsToRecover", request);
			String strAmountToRecover=StringUtility.getParameter("amountToRecover", request);
			
			empLoanVO.setMulLoanRecRemarks(mulRecRemarks);
			empLoanVO.setMulLoanRecoveryMode(loanRecoveryModeFlag.intValue());
			empLoanVO.setMulLoanInstRecvd(!"".equals(strIntallmentsToRecover) && !"0".equals(strIntallmentsToRecover)? Integer.parseInt(strIntallmentsToRecover) : 1);
			empLoanVO.setMulLoanAmtRecvd(!"".equals(strAmountToRecover) ? Long.parseLong(strAmountToRecover) : 0L);
		}else{
			empLoanVO.setMulLoanRecRemarks("");
			empLoanVO.setMulLoanRecoveryMode(0);
			empLoanVO.setMulLoanInstRecvd(1);
			empLoanVO.setMulLoanAmtRecvd(0L);
		}		
		
		
        /*empLoanVO.setHrEisEmpMst(hrEisObj);
        empLoanVO.setHrLoanAdvMst(hrLoanObj);*/
        empLoanVO.setLoanPrinAmt(loanPrinAmt);
        empLoanVO.setLoanInterestAmt(loanIntAmt);
        empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
        empLoanVO.setLoanIntInstNo(loanIntInstNo);
        //empLoanVO.setLoanEmiAmt(loanEMI);
        empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
        empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
        empLoanVO.setLoanAccountNo(loanAcNo);
        empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
       //added by khushal
        empLoanVO.setLoanSancOrderdate(loanSancOrderDate);
        // ended by khushal
        empLoanVO.setLoanDate(loanDate);
        empLoanVO.setLoanActivateFlag(loanActivateFlag);
        
        //added by Ankit Bhatt for Maha
        empLoanVO.setVoucherDate(loanVoucherDate);
        empLoanVO.setVoucherNo(loanVoucherNo);
        //ended
       
       // empLoanVO.setIsApproved(new Integer(0));
        
		/*empLoanVO.setCmnDatabaseMst(cmnDatabaseMst);
		empLoanVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		empLoanVO.setOrgPostMstByCreatedByPost(orgPostMst);
		empLoanVO.setCmnLocationMst(cmnLocationMst);
		empLoanVO.setOrgUserMstByUpdatedBy(orgUserMst);
		empLoanVO.setOrgUserMstByCreatedBy(orgUserMst);
		empLoanVO.setCreatedDate(sysdate);
		empLoanVO.setUpdatedDate(sysdate);*/
		
		//loanRecvVO.setHrLoanEmpDtls(hrLoanObj);
		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
		/*loanRecvVO.setCmnDatabaseMst(cmnDatabaseMst);
		loanRecvVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		loanRecvVO.setOrgPostMstByCreatedByPost(orgPostMst);
		loanRecvVO.setCmnLocationMst(cmnLocationMst);
		loanRecvVO.setOrgUserMstByUpdatedBy(orgUserMst);
		loanRecvVO.setOrgUserMstByCreatedBy(orgUserMst);
		loanRecvVO.setCreatedDate(sysdate);
		loanRecvVO.setUpdatedDate(sysdate);*/
		
		
		//intLoanRecvVO.setHrLoanAdvMst(hrLoanObj);
		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
		/*intLoanRecvVO.setCmnDatabaseMst(cmnDatabaseMst);
		intLoanRecvVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		intLoanRecvVO.setOrgPostMstByCreatedByPost(orgPostMst);
		intLoanRecvVO.setCmnLocationMst(cmnLocationMst);
		intLoanRecvVO.setOrgUserMstByUpdatedBy(orgUserMst);
		intLoanRecvVO.setOrgUserMstByCreatedBy(orgUserMst);
		intLoanRecvVO.setCreatedDate(sysdate);
		intLoanRecvVO.setUpdatedDate(sysdate);*/
		
		//04 jan 2011 odd installment update
		
		empLoanVO.setLoanOddinstno(loanOddinstno);
		empLoanVO.setLoanOddinstAmt(loanOddinstAmt);
		empLoanVO.setOrderno(ordrNo);
		empLoanVO.setOrderDate(ordrDate);
		mp.put("empLoan",empLoanVO);
		retObj.setResultValue(mp);
		//retObj.setResultCode(ErrorConstants.SUCCESS);
		
		Map prinMap = objectArgs;
		prinMap.put("loanRecv", loanRecvVO);
		
		Map intMap = objectArgs;
		intMap.put("loanIntRecv", intLoanRecvVO);
		
		retObj.setResultCode(ErrorConstants.SUCCESS);
		logger.info("U are out of VOGEN");		
		}
		catch(Exception e){
			logger.info("U r in Exception + VOGEN");
			Map result = new HashMap();
			result.put("MESSAGECODE",3001);
			retObj.setResultValue(result);
			//resultObject.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			//logger.error("Error is: "+ e.getMessage());
			return retObj;			
		}	
		return retObj;
	}//end method:insertEmpLoan()
	
	
	
	
	/*
	 * @purpose: 	- voToXml.
	 * 				- To insert multiple loan data for single and multiple employee(s).
	 * multipleAddLoan
	 */
	public ResultObject multipleLoanData(Map objectArgs){
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			
			logger.info("varun $harma: VOGEN multipleLoanData() method called....");
			
			logger.info("objectArgs in multipleLoanData: "+objectArgs.toString());
				
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

			
			LoanCustomVO loanCustomVO = new LoanCustomVO();
			

			long empId=0;
			long loanTypeId=0;
			long loanPrinAmt=0;
			long loanIntAmt=0;
			long loanPrinInstNo=0;
			long loanIntInstNo=0;
			long loanPrinEmiAmt=0;
			long loanIntEmiAmt=0;
			String loanAcNo="";
			String loanSancOrderNo="";
			String sancOrderDate="";
			String lnDate="";
			Integer loanActivateFlag;
			long loanPrinRecovAmt=0;
			long loanPrinRecovInt=0;
			long loanIntRecovAmt=0;
			long loanIntRecovInt=0;
			
			//added by Ankit bhatt for Maha Payroll
			String loanVoucherNo=null;
			String strLoanVoucherDate = null;
			Date loanVoucherDate = null;
			//ended
			
			
		    	
			
		    

			
			/*****************************************
			 *   FETCH LOAN INFORMATION FROM JSP	 *
			 *****************************************/
			
			
			//Employee Id
			empId = Long.parseLong(StringUtility.getParameter("Employee_ID_EmpSearch", request).toString());
			logger.info("empId: " +empId);
			
			//loan type Id
			loanTypeId=(StringUtility.getParameter("loanName", request)!=null&&!(StringUtility.getParameter("loanName", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanName", request)):0);
			logger.info("Loan type id is: " +loanTypeId);
			
			//loan principal amount
			loanPrinAmt=(StringUtility.getParameter("principalAmt", request)!=null&&!(StringUtility.getParameter("principalAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("principalAmt", request)):0);
			logger.info("Principal Amt is: " +loanPrinAmt);
			
			//Interest amount
			loanIntAmt=(StringUtility.getParameter("interestAmt", request)!=null&&!(StringUtility.getParameter("interestAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("interestAmt", request)):0);
			logger.info("Interest Amt is: " +loanIntAmt);
			
			//Principal Installment Number
			loanPrinInstNo=(StringUtility.getParameter("principalInstNo", request)!=null&&!(StringUtility.getParameter("principalInstNo", request).equals(""))?Long.parseLong(StringUtility.getParameter("principalInstNo", request)):0);
			logger.info("principal Inst No is: " +loanPrinInstNo);
			
			//Interest Installment Number
			loanIntInstNo=(StringUtility.getParameter("interestInstNo", request)!=null&&!(StringUtility.getParameter("interestInstNo", request).equals(""))?Long.parseLong(StringUtility.getParameter("interestInstNo", request)):0);
			logger.info("Interest Inst No is: " +loanIntInstNo);
			
			//Principal EMI amount
			loanPrinEmiAmt=(StringUtility.getParameter("loanPrinEmiAmt", request)!=null&&!(StringUtility.getParameter("loanPrinEmiAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanPrinEmiAmt", request)):0);
			logger.info("Loan Prin Emi Amt is: " +loanPrinEmiAmt);
			
			//Interest EMI Amount
			loanIntEmiAmt=(StringUtility.getParameter("loanIntEmiAmt", request)!=null&&!(StringUtility.getParameter("loanIntEmiAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanIntEmiAmt", request)):0);
			logger.info("loan Int Emi Amt is: " +loanIntEmiAmt);
			
			//Loan Account Number
			loanAcNo=(StringUtility.getParameter("loanACNo", request)!=null&&!(StringUtility.getParameter("loanACNo", request).equals(""))?(StringUtility.getParameter("loanACNo", request)):" ").toString();
			logger.info("loan Ac No is: " +loanPrinInstNo);
			
			//Loan Sanction Order Number
			loanSancOrderNo=(StringUtility.getParameter("loanSancOrderNo", request)!=null&&!(StringUtility.getParameter("loanSancOrderNo", request).equals(""))?(StringUtility.getParameter("loanSancOrderNo", request)):" ").toString();
			logger.info("loan Sanc Order No is: " +loanSancOrderNo);
			//Loan Sanction Order Date 
		
			sancOrderDate=(StringUtility.getParameter("loanSancOrderDate", request)!=null&&!(StringUtility.getParameter("loanSancOrderDate", request).equals(""))?
					(StringUtility.getParameter("loanSancOrderDate", request)):" ").toString();
		
			logger.info("khusal testing date "+sancOrderDate);
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			Date loanSancOrderDate=sdf1.parse(sancOrderDate);
			logger.info("Loan Date No is: " +sancOrderDate);
			//Loan Date
			lnDate=(StringUtility.getParameter("loanDate", request)!=null&&!(StringUtility.getParameter("loanDate", request).equals(""))?(StringUtility.getParameter("loanDate", request)):" ").toString();
			
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date loanDate=sdf.parse(lnDate);
				logger.info("Loan Date No is: " +lnDate);
	
			//Loan Activate Flag
			loanActivateFlag=(StringUtility.getParameter("loanActivateFlag", request)!=null&&!(StringUtility.getParameter("loanActivateFlag", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanActivateFlag", request)):1);
			logger.info("Loan Activate Flag: " + loanActivateFlag);
			
			//Loan principal recovered amount
			loanPrinRecovAmt=(StringUtility.getParameter("loanPrinRecovAmt", request)!=null&&!(StringUtility.getParameter("loanPrinRecovAmt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanPrinRecovAmt", request)):0);
			logger.info("Loan Prin Recov Amt: " + loanPrinRecovAmt);
			
			//Loan principal Recovered Interest
			loanPrinRecovInt=(StringUtility.getParameter("loanPrinRecovInt", request)!=null&&!(StringUtility.getParameter("loanPrinRecovInt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanPrinRecovInt", request)):0);
			logger.info("Loan Prin Recov Int: " + loanPrinRecovInt);
			
			//Loan Interest Recovered Amount
			loanIntRecovAmt=(StringUtility.getParameter("loanIntRecovAmt", request)!=null&&!(StringUtility.getParameter("loanIntRecovAmt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanIntRecovAmt", request)):0);
			logger.info("loan Int Recov Amt: " + loanIntRecovAmt);
			
			//Loan Interest Recovered Interest
			loanIntRecovInt=(StringUtility.getParameter("loanIntRecovInt", request)!=null&&!(StringUtility.getParameter("loanIntRecovInt", request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanIntRecovInt", request)):0);
			logger.info("Loan Int Recov Int: " + loanIntRecovInt);		
			
			//added by Ankit Bhatt for Maha Payroll
			//Loan Voucher No
			loanVoucherNo=(StringUtility.getParameter("loanVoucherNo", request)!=null&&!(StringUtility.getParameter("loanVoucherNo", request).equals(""))?StringUtility.getParameter("loanVoucherNo", request).toString():null);
			logger.info("Loan Voucher No:111111111 " + loanVoucherNo);		
			
			strLoanVoucherDate=(StringUtility.getParameter("loanVoucherDate", request)!=null&&!(StringUtility.getParameter("loanVoucherDate", request).equals(""))?(StringUtility.getParameter("loanVoucherDate", request)):" ").toString();
			logger.info("strLoanVoucher Date is " + strLoanVoucherDate);
			if(strLoanVoucherDate!=null && !strLoanVoucherDate.trim().equals(""))
			{
			   loanVoucherDate=sdf.parse(strLoanVoucherDate);
			   logger.info("Loan Date No is: " +loanVoucherDate);
			}
			//ended
			
			//Added by Javed
			long loanOddinstno=0;
			long loanOddinstAmt=0;
			
			loanOddinstno=(StringUtility.getParameter("loanOddinstno", request)!=null&&!(StringUtility.getParameter("loanOddinstno", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanOddinstno", request)):0);
			loanOddinstAmt=(StringUtility.getParameter("loanOddinstAmt", request)!=null&&!(StringUtility.getParameter("loanOddinstAmt", request).equals(""))?Long.parseLong(StringUtility.getParameter("loanOddinstAmt", request)):0);
			
			logger.info("Loan loanOddinstno ----->"+loanOddinstno);
	        logger.info("Loan loanOddinstAmt Amt----->"+loanOddinstAmt);
	        logger.info("===> loanOddinstno :: "+loanOddinstno);
	        logger.info("===> loanOddinstAmt :: "+loanOddinstAmt);
			//Added by Javed
			
			/*****************************************
			 *         SET LOAN INFORMATION 		 *
			 *****************************************/
			
			
			/*   SET "HrLoanEmpDtls" RELATED INFORMATION   */
			
			//set employee id
			loanCustomVO.setEmpId(empId); //N.B: This empId is of org_emp_mst type, which will be changed to hrEisEmpType in service

			//set loan type id
			loanCustomVO.setLoanTypeId(loanTypeId);
			
			//set loan principal amount
			loanCustomVO.setLoanPrinAmt(loanPrinAmt);
			
			//set loan interest amount
			loanCustomVO.setLoanIntAmt(loanIntAmt);
			
			//set loan principal installment number
			loanCustomVO.setLoanPrinInstNo(loanPrinInstNo);
			
			//set loan interest installment number
			loanCustomVO.setLoanIntInstNo(loanIntInstNo);	
			
			//set loan account number
			loanCustomVO.setLoanAcNo(loanAcNo);
			
			//set loan date
			loanCustomVO.setLoanDate(loanDate);
			
			//set TRN counter
			loanCustomVO.setTrnCounter(new Integer(1));
			
			//set loan interest EMI amount
			loanCustomVO.setLoanIntEmiAmt(loanIntEmiAmt);
			
			//set loan principal EMI amount
			loanCustomVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
			
			//set loan sanction order number
			loanCustomVO.setLoanSancOrderNo(loanSancOrderNo);
			  loanCustomVO.setLoanSancOrderDate(loanSancOrderDate);
				//loanCustomVO.setLoanSancOrderDate(sancOrderDate);
			//set loan activate flag
			loanCustomVO.setLoanActivateFlag(loanActivateFlag);
			
			//added by Ankit Bhatt for Maha
			loanCustomVO.setLoanVoucherNo(loanVoucherNo);			
			loanCustomVO.setLoanVoucherDate(loanVoucherDate);
			//ended


			
			/* SET "HrLoanEmpPrinRecoverDtls" RELATED INFORMATION */
			
	        logger.info("setting loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt) ");
	        loanCustomVO.setLoanPrinRecovAmt(loanPrinRecovAmt);
			logger.info("setting loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt) ");
			loanCustomVO.setLoanPrinRecovInt(loanPrinRecovInt);

			
			
			/* SET "HrLoanEmpIntRecoverDtls" RELATED INFORMATION */
			
			logger.info("setting intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt) ");
			loanCustomVO.setLoanIntRecovAmt(loanIntRecovAmt);
			logger.info("setting intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt) ");
			loanCustomVO.setLoanIntRecovInt(loanIntRecovInt);

			
			//Added By Javed
			loanCustomVO.setLoanOddinstno(loanOddinstno);
			loanCustomVO.setLoanOddinstAmt(loanOddinstAmt);
			

			//create XML file 
			logger.info("trying to create voToXml");
			
			String xmlFileId = FileUtility.voToXmlFileByXStream(loanCustomVO);
	       	objectArgs.put("ajaxKey", xmlFileId);
	       	logger.info("VO successfully created into XML..");
	       	logger.info("XML file PATH:::"+xmlFileId);


			retObj.setViewName("ajaxData");
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("U are out of VOGEN");
			

		
		}//catch
		catch(Exception e){
			logger.info("U r in Exception + VOGEN");
			logger.info("Exception occured: " +e.toString());
			logger.error("Error is: "+ e.getMessage());
			Map result = new HashMap();
			result.put("MESSAGECODE",3001);
			retObj.setResultValue(result);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;			
		}
		
		return retObj;
	}//end method: multipleAddLoan()
	
	
	public ResultObject addMultipleLoanFoodVogen(Map objectArgs)
	 {
		logger.info("U r in multipleAddLoanFood VOGEN:::::::::::::::::::");
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try {
				
				HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
				LoanCustomVO loanCustomVO = null;

				List lstEmpIdList = new ArrayList();
				List empLoanVOList = new ArrayList();

				long empId=0;
				long loanPrinAmt=0;
				long loanIntAmt=0;
				long loanPrinInstNo=0;
				long loanIntInstNo=0;
				long loanPrinEmiAmt=0;
				long loanIntEmiAmt=0;
				//String loanAcNo="";
				String loanSancOrderNo="";
				String sancOrderDate="";
				String lnDate="";
				Integer loanActivateFlag;
				long loanPrinRecovAmt=0;
				long loanPrinRecovInt=0;
				long loanIntRecovAmt=0;
				long loanIntRecovInt=0;
				long loanTypeId=0;
				int count=0;
				String loanVoucherNo = "";
				String loanVoucherDate = "";
				String loanAcNo = "";
				
				count = Integer.parseInt(StringUtility.getParameter("recCounter",request)!=null?StringUtility.getParameter("recCounter",request).toString():"");
				logger.info("count is:-"+count);
				
				objectArgs.put("count", count);
				logger.info("before loop***********");	
				
			    for(int i=0;i<count;i++)
				{
			    	
			    	/** 
			    	 * FETCHING LOAN INFORMATION OF EMPLOYEES 
			    	 */
			    	
			    	logger.info("inside loop***********");
			    	loanCustomVO = new LoanCustomVO();

				    //Employee Id
				    empId=Long.parseLong(((StringUtility.getParameter("empId"+i,request)!=null&&!(StringUtility.getParameter("empId"+i,request).equals(""))?(StringUtility.getParameter("empId"+i,request)):0).toString()));
				    logger.info("empId:  " +empId+ " for i = "+i);
				    
				    //loan type Id
					loanTypeId=(StringUtility.getParameter("loanName"+i, request)!=null&&!(StringUtility.getParameter("loanName"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("loanName"+i, request)):0);
					logger.info("Loan type id is: " +loanTypeId);
					
				    //Loan Date
					lnDate=(StringUtility.getParameter("loanDate"+i, request)!=null&&!(StringUtility.getParameter("loanDate"+i, request).equals(""))?(StringUtility.getParameter("loanDate"+i, request)):" ").toString();
					logger.info("Loan Date No is: " +lnDate+ " for i = "+i);
						
					//Loan Sanction Order Number
					loanSancOrderNo=(StringUtility.getParameter("loanSancOrderNo"+i, request)!=null&&!(StringUtility.getParameter("loanSancOrderNo"+i, request).equals(""))?(StringUtility.getParameter("loanSancOrderNo"+i, request)):" ").toString();
					logger.info("loan Sanc Order No is: " +loanSancOrderNo+ " for i = "+i);
				
					//Loan Sanction Order Date
					sancOrderDate=(StringUtility.getParameter("loanSancOrderDate"+i, request)!=null&&!(StringUtility.getParameter("loanSancOrderDate"+i, request).equals(""))?(StringUtility.getParameter("loanSancOrderDate"+i, request)):" ").toString();
					
				    //loan principal amount
					loanPrinAmt=(StringUtility.getParameter("principalAmt"+i, request)!=null&&!(StringUtility.getParameter("principalAmt"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("principalAmt"+i, request)):0);
					logger.info("Principal Amt is: " +loanPrinAmt+ " for i = "+i);
					
					//Principal Installment Number
					loanPrinInstNo=(StringUtility.getParameter("principalInstNo"+i, request)!=null&&!(StringUtility.getParameter("principalInstNo"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("principalInstNo"+i, request)):0);
					logger.info("principal Inst No is: " +loanPrinInstNo+ " for i = "+i);
					
					//Principal EMI amount
					loanPrinEmiAmt=(StringUtility.getParameter("loanPrinEmiAmt"+i, request)!=null&&!(StringUtility.getParameter("loanPrinEmiAmt"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("loanPrinEmiAmt"+i, request)):0);
					logger.info("Loan Prin Emi Amt is: " +loanPrinEmiAmt+ " for i = "+i);
					
					//Loan principal recovered amount
					loanPrinRecovAmt=(StringUtility.getParameter("loanPrinRecovAmt"+i, request)!=null&&!(StringUtility.getParameter("loanPrinRecovAmt"+i, request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanPrinRecovAmt"+i, request)):0);
					logger.info("Loan Prin Recov Amt: " + loanPrinRecovAmt+ " for i = "+i);
					
					//Loan Interest Recovered Interest
					loanIntRecovInt=(StringUtility.getParameter("loanIntRecovInt"+i, request)!=null&&!(StringUtility.getParameter("loanIntRecovInt"+i, request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanIntRecovInt"+i, request)):0);
					logger.info("Loan Int Recov Int: " + loanIntRecovInt+ " for i = "+i);	
					
					//Loan Activate Flag
					loanActivateFlag=(StringUtility.getParameter("loanActivateFlag"+i, request)!=null&&!(StringUtility.getParameter("loanActivateFlag"+i, request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanActivateFlag"+i, request)):1);
					logger.info("Loan Activate Flag: " + loanActivateFlag+ " for i = "+i);
					
					//Interest amount
					loanIntAmt=(StringUtility.getParameter("interestAmt"+i, request)!=null&&!(StringUtility.getParameter("interestAmt"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("interestAmt"+i, request)):0);
					logger.info("Interest Amt is: " +loanIntAmt+ "for i = "+i);
									
					//Interest Installment Number
					loanIntInstNo=(StringUtility.getParameter("interestInstNo"+i, request)!=null&&!(StringUtility.getParameter("interestInstNo"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("interestInstNo"+i, request)):0);
					logger.info("Interest Inst No is: " +loanIntInstNo+ " for i = "+i);
									
					//Interest EMI Amount
					loanIntEmiAmt=(StringUtility.getParameter("loanIntEmiAmt"+i, request)!=null&&!(StringUtility.getParameter("loanIntEmiAmt"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("loanIntEmiAmt"+i, request)):0);
					logger.info("loan Int Emi Amt is: " +loanIntEmiAmt+ " for i = "+i);
					
					//Loan principal Recovered Interest
					loanPrinRecovInt=(StringUtility.getParameter("loanPrinRecovInt"+i, request)!=null&&!(StringUtility.getParameter("loanPrinRecovInt"+i, request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanPrinRecovInt"+i, request)):0);
					logger.info("Loan Prin Recov Int: " + loanPrinRecovInt+ " for i = "+i);
					
					//Loan Interest Recovered Amount
					loanIntRecovAmt=(StringUtility.getParameter("loanIntRecovAmt"+i, request)!=null&&!(StringUtility.getParameter("loanIntRecovAmt"+i, request).equals(""))?Integer.parseInt(StringUtility.getParameter("loanIntRecovAmt"+i, request)):0);
					logger.info("loan Int Recov Amt: " + loanIntRecovAmt+ " for i = "+i);
					
					loanAcNo=(StringUtility.getParameter("loanACNo"+i, request)!=null&&!(StringUtility.getParameter("loanACNo"+i, request).equals(""))?(StringUtility.getParameter("loanACNo"+i, request)):" ").toString();
					
					//Added by Javed
					long loanOddinstno=0;
					long loanOddinstAmt=0;
					
					loanOddinstno=(StringUtility.getParameter("loanOddinstno"+i, request)!=null && !(StringUtility.getParameter("loanOddinstno"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("loanOddinstno"+i, request)):0);
					loanOddinstAmt=(StringUtility.getParameter("loanOddinstAmt"+i, request)!=null && !(StringUtility.getParameter("loanOddinstAmt"+i, request).equals(""))?Long.parseLong(StringUtility.getParameter("loanOddinstAmt"+i, request)):0);
					
					loanVoucherNo=(StringUtility.getParameter("loanVoucherNo"+i, request)!=null&&!(StringUtility.getParameter("loanVoucherNo"+i, request).equals(""))?(StringUtility.getParameter("loanVoucherNo"+i, request)):" ").toString();
					loanVoucherDate=(StringUtility.getParameter("loanVoucherDate"+i, request)!=null&&!(StringUtility.getParameter("loanVoucherDate"+i, request).equals(""))?(StringUtility.getParameter("loanVoucherDate"+i, request)):" ").toString();
					
					logger.info("Loan Account No ----->"+loanAcNo);
			        logger.info("===> loanOddinstno :: "+loanOddinstno);
			        logger.info("===> loanOddinstAmt :: "+loanOddinstAmt);
			        logger.info("===> loanVoucherNo :: "+loanVoucherNo);
			        logger.info("===> loanVoucherDate :: "+loanVoucherDate);
					//Added by Javed
			        
					
					/** 
					 * SETTING LOAN INFORMATION FOR EMPLOYEES 
					 */
					
					loanCustomVO = new LoanCustomVO();
					
					
					/*   SET "HrLoanEmpDtls" RELATED INFORMATION   */
					
					lstEmpIdList.add(empId);
					
					
					//set employee id
					loanCustomVO.setEmpId(empId); //N.B: This empId is of org_emp_mst type, which will be changed to hrEisEmpType in service
	
					//set loan type id
					loanCustomVO.setLoanTypeId(loanTypeId);

					//set loan interest amount
					loanCustomVO.setLoanIntAmt(loanIntAmt);
					
					//set loan interest installment number
					loanCustomVO.setLoanIntInstNo(loanIntInstNo);	

					//set TRN counter
					loanCustomVO.setTrnCounter(new Integer(1));
					
					//set loan interest EMI amount
					loanCustomVO.setLoanIntEmiAmt(loanIntEmiAmt);
					
					//set loan type id
					loanCustomVO.setLoanTypeId(loanTypeId);
					
					//set loan date
					loanCustomVO.setLoanDate(StringUtility.convertStringToDate(lnDate));
					
					//set loan sanction order number
					loanCustomVO.setLoanSancOrderNo(loanSancOrderNo);
					
					//set loan sanction order Date
					loanCustomVO.setLoanSancOrderDate(StringUtility.convertStringToDate(sancOrderDate));
					
					//set loan principal amount
					loanCustomVO.setLoanPrinAmt(loanPrinAmt);
					
					//set loan principal installment number
					loanCustomVO.setLoanPrinInstNo(loanPrinInstNo);
					
					//set loan principal EMI amount
					loanCustomVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
					
					//set loan activate flag
					loanCustomVO.setLoanActivateFlag(loanActivateFlag);
	

					/* SET "HrLoanEmpPrinRecoverDtls" RELATED INFORMATION */

			        loanCustomVO.setLoanPrinRecovAmt(loanPrinRecovAmt);
					loanCustomVO.setLoanPrinRecovInt(loanPrinRecovInt);

					
					/* SET "HrLoanEmpIntRecoverDtls" RELATED INFORMATION */
					
					loanCustomVO.setLoanIntRecovAmt(loanIntRecovAmt);
					loanCustomVO.setLoanIntRecovInt(loanIntRecovInt);
					

					//Added By Javed
					loanCustomVO.setLoanOddinstno(loanOddinstno);
					loanCustomVO.setLoanOddinstAmt(loanOddinstAmt);
					//Added By Javed
					
					
					//Added By Amish
					loanCustomVO.setLoanVoucherDate(StringUtility.convertStringToDate(loanVoucherDate));
					loanCustomVO.setLoanVoucherNo(loanVoucherNo);
					loanCustomVO.setLoanAcNo(loanAcNo);
					//Added By Amish
			        empLoanVOList.add(i, loanCustomVO);
					
					logger.info("The size of empLoanVOList in VOGen is---------->>>>"+empLoanVOList.size());
					objectArgs.put("empLoanVOList",empLoanVOList);
					objectArgs.put("lstEmpIdList", lstEmpIdList);
				
				}//end for

				retObj.setResultCode(ErrorConstants.SUCCESS);			
				retObj.setResultValue(objectArgs);
				logger.info("U are going out of VOGEN");

		}
		catch(Exception e){
			logger.info("U r in Exception + empLoanVOGENfood");
			logger.error("Error is: "+ e.getMessage());
			Map result = new HashMap();
			result.put("MESSAGECODE",3001);
			retObj.setResultValue(result);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;			
		}	
		return retObj;
	}//end method
	
	
}//end class
