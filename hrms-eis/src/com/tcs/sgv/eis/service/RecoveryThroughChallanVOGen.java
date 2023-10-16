package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.RecoveryChallanCustomVO;

public class RecoveryThroughChallanVOGen extends ServiceImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
	public ResultObject generateMapForRecoveryThroughChallan(Map objectArgs) 
	{
	
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			logger.info("inside generateMapForRecoveryThroughChallan method ");
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
			RecoveryChallanCustomVO customVo = new RecoveryChallanCustomVO();
			
			long loanEmpId=0;
			if(request.getParameter("loanAmtDis")!= null)
			{
				customVo.setLoanAmount(Long.parseLong((request.getParameter("loanAmtDis"))));
			}
			if(request.getParameter("totalInst")!= null)
			{
				customVo.setTotalInstallments(Long.parseLong(request.getParameter("totalInst")));
			}
			   
			if(request.getParameter("intstallAmt")!= null)
			{
				customVo.setInstallmentAmount(Long.parseLong(request.getParameter("intstallAmt")));
			}
			if(request.getParameter("oddInstallNo")!= null)
			{
				customVo.setOddInstallNo(Long.parseLong(request.getParameter("oddInstallNo")));
			}   
			if(request.getParameter("oddInstallAmt")!= null)
			{
				customVo.setOddInstallAmount(Long.parseLong(request.getParameter("oddInstallAmt")));
			}
			if(request.getParameter("lastInstallRecoverd")!= null)
			{
				customVo.setLastInstallRecoverd(Long.parseLong(request.getParameter("lastInstallRecoverd")));
			}   
			if(request.getParameter("txtOutstandingPrinAmt")!= null)
			{
				customVo.setOutstadingAmount(Long.parseLong(request.getParameter("txtOutstandingPrinAmt")));
			}   
			if(request.getParameter("asOnDate")!= null)
			{
				customVo.setAsOnDate(request.getParameter("asOnDate"));
			}
			if(request.getParameter("currPayMonth")!= null)
			{
				customVo.setCurrPayMonth(request.getParameter("currPayMonth"));
			}  
		
			if (request.getParameter("recoveryOrderNo") != null)
			{
				customVo.setRecoveryOrderNo(request.getParameter("recoveryOrderNo"));
			}
			
			if (request.getParameter("recoveryOrederDate") != null)
			{
				customVo.setRecoveryDate(request.getParameter("recoveryOrederDate"));
			}
			if (request.getParameter("rcOrDtHidden") != null)
			{
				customVo.setRecoveryDate(request.getParameter("rcOrDtHidden"));
			}
			if (request.getParameter("challanNo") != null)
			{
				customVo.setChallnNo(request.getParameter("challanNo") );
			}
			if (request.getParameter("challanDate") != null)
			{
				customVo.setChallanDate(request.getParameter("challanDate"));
			}
			if (request.getParameter("amountPaid") != null)
			{
				customVo.setAmount(Long.parseLong(request.getParameter("amountPaid")));
			}
			
			if (request.getParameter("recoveryType") != null)
			{
				customVo.setRecoveryType(request.getParameter("recoveryType") );
			}
			if (request.getParameter("sanctionOrderDate") != null)
			{
				customVo.setSanctionOrderDate(request.getParameter("sanctionOrderDate"));
			}
			if (request.getParameter("loanType") != null)
			{
				customVo.setLoanName(request.getParameter("loanType"));
			}
			
			if (request.getParameter("empNamehidden") != null)
			{
				customVo.setEmpName(request.getParameter("empNamehidden"));
			}
			if (request.getParameter("designationHidden") != null)
			{
				customVo.setDesignation(request.getParameter("designationHidden"));
			}
			if (request.getParameter("lnEmpId") != null)
			{
				loanEmpId = Long.parseLong(request.getParameter("lnEmpId"));
			}
			
			/*
			 * 
			 * Logic for Number of Installments
			 */
			
			long amt = customVo.getAmount();
			long emiAmt = customVo.getInstallmentAmount();
			long oddEmiAmt = customVo.getOddInstallAmount();
			long oddEmiNumber = customVo.getOddInstallNo();
			long instReceived = customVo.getLastInstallRecoverd();
			
			int totalInstallments = 0; 
			if(amt % emiAmt == 0){
				totalInstallments = Long.valueOf(amt / emiAmt).intValue();
				if(instReceived < oddEmiNumber && oddEmiNumber <= (instReceived + totalInstallments)){
					totalInstallments =  Long.valueOf(( amt - oddEmiAmt ) / emiAmt).intValue();
					totalInstallments++; // its odd amt
				}
			}
			else{
				totalInstallments =  Long.valueOf(( amt - oddEmiAmt ) / emiAmt).intValue();
				totalInstallments++; // its odd amt
			}
			
			
			logger.info("recovery order no "+customVo.getRecoveryOrderNo());
			logger.info("getRecoveryDate order no "+customVo.getRecoveryDate());
			logger.info("getAmount order no "+customVo.getAmount());
			logger.info("getChallanDate order no "+customVo.getChallanDate());
			logger.info("getChallnNo order no "+customVo.getChallnNo());
			logger.info("totalInstallments "+totalInstallments);
			
			objectArgs.put("totalInstallments", totalInstallments);
			objectArgs.put("challanVO", customVo);
			objectArgs.put("loanEmpId", loanEmpId);
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			
		}catch (Exception e) {
			
			logger.error("Error is: "+ e.getMessage());
		}

		return retObj;
	}

}
