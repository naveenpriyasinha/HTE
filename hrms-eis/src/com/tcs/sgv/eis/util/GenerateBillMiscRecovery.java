package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.MiscRecoverDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrMiscRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillMiscDtls;
import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class GenerateBillMiscRecovery {
	
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	
	public Map calculateMiscRecover(Map objectArgs) throws Exception {
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");		
		int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
		int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
		HrPayPaybill payBillVO = (HrPayPaybill)objectArgs.get("payBillVO");
		HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls)objectArgs.get("hrEisOtherDtls");
				
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());

		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

		long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

		long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);		

		IdGenerator idGenerator = new IdGenerator();
		
		
		MiscRecoverDAOImpl miscRecovDao = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
		List<HrMiscRecoverDtls> hrMiscRecoverList = new ArrayList();
		
		hrMiscRecoverList=miscRecovDao.getAllMiscDataByEmpId(hrEisOtherDtls.getHrEisEmpMst());
		
		HrMiscRecoverDtls hrMiscRecoverDtls = new HrMiscRecoverDtls();
		GenericDaoHibernateImpl rltEdpDao = new GenericDaoHibernateImpl(RltBillTypeEdp.class);
		rltEdpDao.setSessionFactory(serv.getSessionFactory());
		
		long pay_recv=0;
		long hrr_recov=0;
		long miscRecov=0;
		Date sysDate = new java.util.Date();
		
		GenericDaoHibernateImpl paybillMiscDao = new GenericDaoHibernateImpl(HrPayPaybillMiscDtls.class);
		paybillMiscDao.setSessionFactory(serv.getSessionFactory());
		long paybillMiscId=0;
		
		if(hrMiscRecoverList!=null && hrMiscRecoverList.size()>0)
		{
			for(int recovCnt=0;recovCnt<hrMiscRecoverList.size();recovCnt++)
			{
				
				hrMiscRecoverDtls=hrMiscRecoverList.get(recovCnt);
				long recoverInstallments=hrMiscRecoverDtls.getInstallment();
				Date recoveryDate=hrMiscRecoverDtls.getRecoverDate();	
				
				long edpId = hrMiscRecoverDtls.getEdpTypeId();
				
				
				RltBillTypeEdp edpVo  =  (RltBillTypeEdp)rltEdpDao.read(edpId);
				
				String edpCode = edpVo.getEdpCode();
				
				Calendar c = Calendar.getInstance();							       
				c.set(Calendar.YEAR, recoveryDate.getYear()+1900);
		        c.set(Calendar.MONTH, recoveryDate.getMonth());
		        c.set(Calendar.DAY_OF_MONTH, recoveryDate.getDate());
		        
		        c.add(Calendar.MONTH,(int)(recoverInstallments)+1);//1 is added to get the last month
		        
		        Date recovEndDate = c.getTime();
		        
		        c.set(Calendar.YEAR, yearGiven);
		        c.set(Calendar.MONTH,monthGiven-1);
		        
		        int maxDayInMonth = c.getActualMaximum(5);
		        c.set(Calendar.DAY_OF_MONTH,maxDayInMonth);
		        
		        
		        logger.info("The recoveryDate Date is :-"+recoveryDate);
		        logger.info("The Loan End Date is :-"+recovEndDate);
		        Date currentDate = c.getTime(); //1st of given month
		        long recoveredMiscAmt = hrMiscRecoverDtls.getTot_recv_amt();
		        long totalMiscAmt = hrMiscRecoverDtls.getTotal_amount();
		        long recoveredMiscInst = hrMiscRecoverDtls.getTot_recv_inst();
		        long totalMiscInst = hrMiscRecoverDtls.getInstallment();
		        
				if(currentDate.compareTo(recoveryDate)==1 && currentDate.compareTo(recovEndDate)<=0 && hrMiscRecoverDtls.getMiscActivateFlag()==1 && recoveredMiscAmt < totalMiscAmt && recoveredMiscInst < recoverInstallments)
				{
					/*if(!"0101".equals(edpCode))
						miscRecov+=miscRecov+hrMiscRecoverDtls.getRecoverAmt();
					else
						pay_recv+=miscRecov+hrMiscRecoverDtls.getRecoverAmt();
					*/
					
					//Varun sharma: for HRR
					if(edpCode.equals("0101"))
					{ 
						//Recovery of pay
						pay_recv+=hrMiscRecoverDtls.getRecoverAmt();
					}
					else if( edpCode.equals("9550"))
					{ 
						//HRR deduction
						hrr_recov=hrMiscRecoverDtls.getRecoverAmt();
					}
					else
					{	
						// For rest of misc recoveries
						miscRecov+=hrMiscRecoverDtls.getRecoverAmt();
					}
					
					//Added By Mrugesh for hr_pay_paybill_misc_dtls
					List paybillMiscList = miscRecovDao.getPaybillMiscDtls(hrMiscRecoverDtls.getHrEisEmpMst().getEmpId(),hrMiscRecoverDtls.getMiscId(),monthGiven,yearGiven);
					
					///Entry should be done in hr_pay_paybill_misc_dtls only at time of paybill generation and NOT in preview bill
					if(payBillVO!=null &&  payBillVO.getId()>0) 
					{
					if(paybillMiscList!=null && paybillMiscList.size()>0) 
					{
						logger.info("*********Misc Data found in hr_pay_paybill_misc_dtls");
						HrPayPaybillMiscDtls hrPayPaybillMiscDtls = (HrPayPaybillMiscDtls)paybillMiscList.get(0);
						
						hrPayPaybillMiscDtls.setTotalAmt(hrMiscRecoverDtls.getTotal_amount());
						hrPayPaybillMiscDtls.setTotalInst(hrMiscRecoverDtls.getInstallment());
						if((hrMiscRecoverDtls.getInstallment() - hrMiscRecoverDtls.getTot_recv_inst())==1)//If last installment then set EMI amount as per remaining recovered amount
						{
							if((hrMiscRecoverDtls.getTot_recv_amt()+hrMiscRecoverDtls.getRecoverAmt()) < hrMiscRecoverDtls.getTotal_amount())
							{
								hrPayPaybillMiscDtls.setRecoveredAmt(hrMiscRecoverDtls.getTotal_amount() - hrMiscRecoverDtls.getTot_recv_amt());
							}
						}
						else
						{
							hrPayPaybillMiscDtls.setRecoveredAmt(hrMiscRecoverDtls.getTot_recv_amt()+hrMiscRecoverDtls.getRecoverAmt());
						}
						hrPayPaybillMiscDtls.setRecoveredInst(hrMiscRecoverDtls.getTot_recv_inst() + 1);
						hrPayPaybillMiscDtls.setEdpTypeId(hrMiscRecoverDtls.getEdpTypeId());
						hrPayPaybillMiscDtls.setPaybillId(payBillVO);
						hrPayPaybillMiscDtls.setUpdatedDate(sysDate);
						hrPayPaybillMiscDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						hrPayPaybillMiscDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						
						logger.info("The Paybill is already generated for the same employee so the record is updated");
						paybillMiscDao.update(hrPayPaybillMiscDtls);
					}
					else
					{
						paybillMiscId  = idGenerator.PKGenerator("hr_pay_paybill_misc_dtls",objectArgs);
						//System.out.println("The generated bill Misc Id is:-"+paybillMiscId);
						HrPayPaybillMiscDtls hrPayPaybillMiscDtls = new HrPayPaybillMiscDtls();
						hrPayPaybillMiscDtls.setId(paybillMiscId);
						hrPayPaybillMiscDtls.setPaybillId(payBillVO);
						hrPayPaybillMiscDtls.setCmnDatabaseMst(cmnDatabaseMst);
						hrPayPaybillMiscDtls.setCmnLocationMst(cmnLocationMst);
						hrPayPaybillMiscDtls.setCreatedDate(sysDate);
						
						hrPayPaybillMiscDtls.setMiscId(hrMiscRecoverDtls.getMiscId());
						hrPayPaybillMiscDtls.setOrgPostMstByCreatedByPost(orgPostMst);
						hrPayPaybillMiscDtls.setOrgUserMstByCreatedBy(orgUserMst);
						hrPayPaybillMiscDtls.setTotalAmt(hrMiscRecoverDtls.getTotal_amount());
						hrPayPaybillMiscDtls.setTotalInst(hrMiscRecoverDtls.getInstallment());
						hrPayPaybillMiscDtls.setRecoveredAmt(hrMiscRecoverDtls.getRecoverAmt());
						hrPayPaybillMiscDtls.setRecoveredInst(1);
						hrPayPaybillMiscDtls.setEdpTypeId(hrMiscRecoverDtls.getEdpTypeId());
						paybillMiscDao.create(hrPayPaybillMiscDtls);
						//System.out.println("The Paybill Misc Details inserted successfully");
						logger.info("The Paybill Misc Details inserted successfully");
					}
				    }
					//Ended By Mrugesh for hr_pay_paybill_misc_dtls
				logger.info("the miscRecov amt "+miscRecov);
			}
			
		}
		}		
		else
		{
			miscRecov=0;
		}
		
		Map returnMap = new HashMap();
		returnMap.put("payRecovery", pay_recv);
		returnMap.put("hrrRecovery", hrr_recov);
		returnMap.put("miscRecov", miscRecov);
		return returnMap;
		
	}
}
