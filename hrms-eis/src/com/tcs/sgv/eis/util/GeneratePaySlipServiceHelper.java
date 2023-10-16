package com.tcs.sgv.eis.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAO;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.LoanIntRecoverDAOImpl;
import com.tcs.sgv.eis.dao.LoanPrinRecoverDAOImpl;
import com.tcs.sgv.eis.dao.MiscRecoverDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaySlipDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PaybillRegMpgDAOImpl;
import com.tcs.sgv.eis.dao.QuaterMstDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrEisQuaterTypeMst;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrMiscRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class GeneratePaySlipServiceHelper {
	public final Log logger =LogFactory.getLog( getClass() );
	ServiceLocator serv;
	public GeneratePaySlipServiceHelper(ServiceLocator serv) {
		// TODO Auto-generated constructor stub
		this.serv = serv;
	}
	
	public void updatePayBillHeadMaping(long paybillId,long appFlag)
	{
	 PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());	
	  List <PaybillHeadMpg> paybillHeadMpgList = paybillHeadMpgDAOImpl.getListByColumnAndValue("hrPayPaybill", paybillId);
		Date sysdate = new Date();
	  for(PaybillHeadMpg paybillHeadMapping:paybillHeadMpgList)
	  {
	  		paybillHeadMapping.setApproveFlag(appFlag);
	  		paybillHeadMapping.setUpdatedDate(sysdate);
	  		//paybillHeadMpgDAOImpl.update(paybillHeadMapping);
	  	}
	}
	
	public void updateHrPayPaybill(List<HrPayPaybill> lstPayPayBill,Calendar cal)
	{
		PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		java.util.Date PayBillApproveDate = cal.getTime();
		for(HrPayPaybill hrPayPaybill:lstPayPayBill){
	  		//hrPayPaybill.setApproveFlag(appFlag);
	  		hrPayPaybill.setApproveRejectDate(PayBillApproveDate);
	  		//payBillDAOImpl.update(hrPayPaybill);
	  	}
	}
	
	public void createPaySlipVO(HrPayPaybill payBillVO,PaybillHeadMpg paybillHeadMpg,long paybillId,Calendar cal,long langId,long dbId,long postId,long userId,long locId)
	{
		
		long incomeTax=0;
		long incrementAmt=0;
			HrPayPayslip paySlipVO = new HrPayPayslip();
			 CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
			 
			 HrEisEmpMst  hrEisEmpMst = payBillVO.getHrEisEmpMst();
			 
			 if(hrEisEmpMst!=null)
	 			{
	  //logger.info("Employee name in payslip " + hrEisEmpMst.getOrgEmpMst().getEmpFname());
	 
	 				OtherDetailDAOImpl otherDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
	 				List otherDtlsList = otherDAO.getEmpAvailable(hrEisEmpMst.getEmpId());
	 				
	 				if(otherDtlsList.size() > 0)
	 				{
	 					HrEisOtherDtls otherVO = (HrEisOtherDtls)otherDtlsList.get(0);
	    /*if(otherVO.getHrQuaterTypeMst()!=null)
	    {
	      quaterId = otherVO.getHrQuaterTypeMst().getQuaId();*/
	 					logger.info("Going to fetch Desig Name");
	 					if(otherVO.getHrEisSgdMpg()!=null){
	 						logger.info("Designation name for Employee is" + otherVO.getHrEisSgdMpg().getHrEisGdMpg().getOrgDesignationMst().getDsgnName());
	 						incrementAmt = getIncrementAmount(otherVO.getHrEisSgdMpg().getHrEisScaleMst(),otherVO.getotherCurrentBasic());
	 					}
	 					incomeTax = otherVO.getIncomeTax();
	    /*}
	    logger.info("Quater ID is " + quaterId);*/
	 				}
	 			}
	 			else
	 			{
	 				throw new NullPointerException();
	 			}
			 
			
			 QuaterMstDAOImpl hrEssQuaterDao = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());	
			 long userID = payBillVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
			 List quaterList = hrEssQuaterDao.getQuarterDtls(userID);
			 long quaterId=0;
	  		  
	 			if(quaterList!=null && quaterList.size()>0)
	 			{
	 					quaterId =((HrEisQtrMst)quaterList.get(quaterList.size()-1)).getHrEisQuaterTypeMst().getQuaId();
	 			}
	 			
			 
 			HrEisQuaterTypeMst hrQuaterTypeMst = new HrEisQuaterTypeMst();
 			if(quaterId!=0)
 			{
 				hrQuaterTypeMst.setQuaId(quaterId);
 				//paySlipVO.setHrEisQuaterTypeMst(hrQuaterTypeMst);
 			}
 			else{}
 				//paySlipVO.setHrEisQuaterTypeMst(null);
	 
 			EmpGpfDtlsDAO empGpfDtlsDAO = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
 			
 			HrPayGpfBalanceDtls hrGpfBalanceDtls = empGpfDtlsDAO.read(userID);
 			paySlipVO.setGpfAccNo(hrGpfBalanceDtls!=null?hrGpfBalanceDtls.getGpfAccNo():"");
	 	
 			String billNo="";
 			int billAmt=0;
 			String budMjrHead="";
 			PaybillRegMpgDAOImpl paybillRegMpgDAOImpl = new PaybillRegMpgDAOImpl(PaybillBillregMpg.class,serv.getSessionFactory());
 			List lstPaybillRegMpg = paybillRegMpgDAOImpl.getListByColumnAndValue("hrPayPaybill", paybillId);
 			PaybillBillregMpg paybillBillregMpg=null;
 			if(lstPaybillRegMpg!=null && lstPaybillRegMpg.size()>0)
 			{
 				paybillBillregMpg = (PaybillBillregMpg)lstPaybillRegMpg.get(0);
 				billNo= paybillBillregMpg.getTrnBillRegister().getBillCntrlNo();
 				billAmt = paybillBillregMpg.getTrnBillRegister().getBillGrossAmount().intValue();
 				budMjrHead = paybillBillregMpg.getTrnBillRegister().getBudmjrHd();
 			}
  		 	/*paySlipVO.setBillNo(billNo);
  		 	paySlipVO.setBillAmt(billAmt);
  		 	paySlipVO.setBudgetHead(budMjrHead);*/
  		 	paySlipVO.setIncrementAmt(incrementAmt);
  		 	
	//paySlipVO.set
	 // End By Urvin shah
	 //paySlipVO.setCpf((long)payBillVO.getDeduc9534()); 	
	 	
  		 	paySlipVO.setBasicPay((long)payBillVO.getBasic0101() + (long)payBillVO.getBasic0102()); //Basic Pay.
  		 	//paySlipVO.setLeavePay((long)payBillVO.getLs()); //Leave Pay.
  		 	paySlipVO.setSplPay((long)payBillVO.getAllow0102()); //Spl Pay.
  		 	paySlipVO.setPerPay((long)payBillVO.getAllow0101()); //Per Pay.
  		 	paySlipVO.setDa((long)payBillVO.getAllow0103()); //DA.
  		 	paySlipVO.setDp((long)(payBillVO.getAllow0119()+payBillVO.getAllow0120())); //DP.
  		 	paySlipVO.setCla((long)payBillVO.getAllow0111()); //CLA.
  		 	paySlipVO.setHra((long)payBillVO.getAllow0110());//HRA
  		 	paySlipVO.setWa((long)payBillVO.getAllow1301()); //WA.
  		 	paySlipVO.setMa((long)payBillVO.getAllow0107()); //MA
	 //paySlipVO.setIr(0); //IR.
  		 	//paySlipVO.setTrAllow((long)payBillVO.getAllow0113()); //Tr Allow
	 
	 //logger.info("the bonus from payslip is "+payBillVO.getAllow0108());
	 
	// paySlipVO.setBonus((long)payBillVO.getAllow0108());
	 
	 /*.......................Deductions.....................................*/
	 
	 //paySlipVO.setDAGPF((long)(payBillVO.getDeduc9999()+payBillVO.getDeduc9998()));
	 
	 paySlipVO.setITax(Math.round(payBillVO.getIt())); //I.Tax
	 //paySlipVO.setITax(incomeTax);
	 paySlipVO.setHrr((long)payBillVO.getDeduc9550() + (long)payBillVO.getDeduc9560()); //HRR
	 paySlipVO.setPTax((long)payBillVO.getDeduc9570()); //P.Tax.
	 //paySlipVO.setSis((long)payBillVO.getDeduc9580() + (long)payBillVO.getDeduc9581() + (long)payBillVO.getDeduc9582() + (long)payBillVO.getDeduc9583() + (long)payBillVO.getDeduc9584() ); //SIS = SIS IF + SIS SF + AIS IF + AIS SF
	 /*paySlipVO.setGis1979((int)payBillVO.getDeduc9580() + (int)payBillVO.getDeduc9581() + (int)payBillVO.getDeduc9582() + (int)payBillVO.getDeduc9583() + (int)payBillVO.getDeduc9584() ); //SIS = SIS IF + SIS SF + AIS IF + AIS SF
	 paySlipVO.setGpf((long)payBillVO.getDeduc9670()  + (long)payBillVO.getDeduc9531() + (long) payBillVO.getDeduc9620()+(long)payBillVO.getDeduc9534());// + (long) payBillVO.getDeduc9534()); //GPF = GPF + GPF IV + AIS PF + CPF
	 //paySlipVO.setCpf(0); //CPF		  		 
	 paySlipVO.setGpfAdv((long)(payBillVO.getAdv9670()+payBillVO.getAdvIV9670())); //GPF Adv.		  		 		  		 		  		 		  			  		
	 paySlipVO.setFesAdv((long)payBillVO.getAdv7057()); //Fes Adv.
	 paySlipVO.setFoodAdv((long)payBillVO.getAdv7058()); //Food Adv.
	 paySlipVO.setFanAdv((long)payBillVO.getLoan9720()); //Fan Adv.
	 paySlipVO.setHba((long)payBillVO.getLoan9591()); //HBA Loan HBA Int.		  		 		  		
	 paySlipVO.setVehAdv((long)payBillVO.getLoan9592()); //Veh Loan = carSctMopedAdv.
	 //paySlipVO.setCarRent((long)payBillVO.getDeduc9780()); //Car Rent = Jeep Rent
	 paySlipVO.setJeepRent((int)payBillVO.getDeduc9780()); //Car Rent = Jeep Rent
	 paySlipVO.setMiscRec((long)payBillVO.getDeduc9910()); //Misc Rec.

	 // Added By Urvin Shah.
	paySlipVO.setPayRecovery((long)payBillVO.getDeduc0101()); // Pay Recovery
*/	//End.
	 paySlipVO.setNetTotal((long)payBillVO.getNetTotal());//net total
	 
	 /**************************************************************************************/
	 Date PayBillApproveDate = cal.getTime();
	 paySlipVO.setMonth((long)paybillHeadMpg.getMonth());
	 paySlipVO.setPaySlipDate(PayBillApproveDate);		  		 		  		 		  		 
	 paySlipVO.setUpdatedDate(new Date());		  		 		  		
	 paySlipVO.setYear((long)paybillHeadMpg.getYear());		
	 
	 paySlipVO.setHrEisEmpMst(hrEisEmpMst);
	 
	HrPayPaybill payBillObj = new HrPayPaybill(); //object to set in PaySlip
	payBillObj.setId(payBillVO.getId());
	payBillObj.setNetTotal(payBillVO.getNetTotal());

		
	 paySlipVO.setHrPayPaybill(payBillObj);
	 paySlipVO.setTrnCounter(new Integer(1));
	 
	 // Added By Urvin shah.
	 
		/*paySlipVO.setHbaInt((int)payBillVO.getLoanInt9591());			// HBA Interest
		paySlipVO.setMcaInt((int)payBillVO.getLoanInt9592());			// CarScooterMoped Interest. (MCA Interest)
		paySlipVO.setFanInt((int)payBillVO.getLoanInt9720());			// Fan Interest
		paySlipVO.setGis1979((int)payBillVO.getDeduc9580());			// SIS1979
		paySlipVO.setGisIf((int)(payBillVO.getDeduc9581()+payBillVO.getDeduc9583()));	// GIS Insurance Fund OR AIS Insurance Fund (GIS-IF/AIS-IF)
		paySlipVO.setGisSf((int)(payBillVO.getDeduc9582()+payBillVO.getDeduc9584()));	// GIS Saving Fund OR AIS Saving Fund (AIS-SF/AIS-SF)	
		paySlipVO.setOcaCycleAdv((int)payBillVO.getLoan9740());			// 	OCA Cycle Advance.
		paySlipVO.setOcaCycleInt((int)payBillVO.getLoanInt9740());		// 	OCA Cycle Advance.
		paySlipVO.setJeepRent((int)payBillVO.getDeduc9780()); 			// Jeep Rent.	
*/
		String itAccNo = "";
	
		GenericDaoHibernateImpl hrEisProofDtlsDAO = new GenericDaoHibernateImpl(HrEisProofDtl.class);
		hrEisProofDtlsDAO.setSessionFactory(serv.getSessionFactory());
		String colsLoanEmpDtls[] = {"orgPostMstByUserId.userId"}; 
	Object valsLoanEmpDtls[] = {userID}; 
	List<HrEisProofDtl> hrEisProofDtlList = hrEisProofDtlsDAO.getListByColumnAndValue(colsLoanEmpDtls, valsLoanEmpDtls);
    if(hrEisProofDtlList!=null && hrEisProofDtlList.size()>0)
    	itAccNo=hrEisProofDtlList.get(0).getProofNum();
    	
		paySlipVO.setItAccNo(itAccNo);
	 // End By Urvin Shah.
	Date sysdate = new Date();
	paySlipVO.setCreatedDate(sysdate);
	CmnLanguageMst cmnLanguageMst = cmn.getCmnLanguageMst(langId);
	paySlipVO.setCmnLanguageMst(cmnLanguageMst);
	CmnDatabaseMst cmnDatabaseMst =cmn.getCmnDatabaseMst(dbId);
	paySlipVO.setCmnDatabaseMst(cmnDatabaseMst);
	OrgPostMst orgPostMst =cmn.getOrgPostMst(postId);
	paySlipVO.setOrgPostMstByUpdatedByPost(orgPostMst);
	paySlipVO.setOrgPostMstByCreatedByPost(orgPostMst);
	CmnLocationMst cmnLocationMst = cmn.getCmnLocationMst(locId);
	
	paySlipVO.setCmnLocationMst(cmnLocationMst);
	OrgUserMst orgUserMst =cmn.getorgUserMst(userID);
	
	paySlipVO.setOrgUserMstByUpdatedBy(orgUserMst);
	paySlipVO.setOrgUserMstByCreatedBy(orgUserMst);	
	
	IdGenerator idGen = new IdGenerator();
	
	long paySlipId = idGen.PKGeneratorWebService( "hr_pay_payslip",  serv,  userId,  langId,  locId);

	paySlipVO.setPaySlipId(paySlipId);
	PaySlipDAOImpl paySlipDAO = new PaySlipDAOImpl(HrPayPayslip.class,serv.getSessionFactory());  		
	//paySlipDAO.create(paySlipVO);
	}
	
	public int getIncrementAmount(HrEisScaleMst hrEisScaleMst,long basicAmt){
		int incrementAmount=0;
		if(basicAmt>=hrEisScaleMst.getScaleStartAmt() && basicAmt < hrEisScaleMst.getScaleEndAmt())
			incrementAmount = (int)hrEisScaleMst.getScaleIncrAmt();
		else
			incrementAmount = (int) hrEisScaleMst.getScaleHigherIncrAmt();
		return incrementAmount;
	}
	
	
	public void updateMiscRecoveryDtls(HrPayPaybill payBillVO,long postId,long userId)
	{
		MiscRecoverDAOImpl miscReoverDAO = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
		OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		HrEisEmpMst  hrEisEmpMst = payBillVO.getHrEisEmpMst();
		HrMiscRecoverDtls miscRecoverVO = new HrMiscRecoverDtls();
			List<HrMiscRecoverDtls> miscRecoverList=miscReoverDAO.getAllMiscDataByEmpId(hrEisEmpMst);
			if(miscRecoverList.size() > 0)
			{
				miscRecoverVO=miscRecoverList.get(0);
				int totMiscAmt=(int)miscRecoverVO.getTotal_amount();
				int totMiscInst=(int)miscRecoverVO.getInstallment();
				int recoveredMiscAmt=miscRecoverVO.getTot_recv_amt();
				int recoveredMiscInst=miscRecoverVO.getTot_recv_inst();
				if(recoveredMiscAmt < totMiscAmt && recoveredMiscInst < totMiscInst)
				{
					Date sysdate = new Date();
					int miscEMI=(int)miscRecoverVO.getRecoverAmt();
					recoveredMiscAmt = recoveredMiscAmt + miscEMI;
					recoveredMiscInst++;
					miscRecoverVO.setTot_recv_amt(recoveredMiscAmt);
					miscRecoverVO.setTot_recv_inst(recoveredMiscInst);
					miscRecoverVO.setUpdatedDate(sysdate);
					miscRecoverVO.setOrgPostMstByUpdatedByPost(orgPostMst);
					miscRecoverVO.setOrgUserMstByUpdatedBy(orgUserMst);
					//miscReoverDAO.update(miscRecoverVO);
				}
			}
			
		
	}
	
	public void updateLoanRcvrInt( HrPayEdpCompoMpg edpComp, HrPayPaybill payBillVO,int yearGiven, int monthGiven,long postId,long userId) throws Exception
	{
		
		
			Class pay = payBillVO.getClass();
			double methodValue = 0;
    	 	double methodIntValue = 0;
			String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());	
			String mthdName="getLoan"+edpCode;
			String mthdNameInt = "getLoanInt"+edpCode ;
			Method payMthd = pay.getMethod(mthdName, null);
			Method payIntMthd = pay.getMethod(mthdNameInt, null);
			methodValue = (Double)payMthd.invoke(payBillVO, null);
			methodIntValue = (Double)payIntMthd.invoke(payBillVO, null);
			HrLoanEmpIntRecoverDtls intRecoverVO= new HrLoanEmpIntRecoverDtls();

			//logger.info ("for loan "+mthdName+" and compocode = "+compoCode+" with methodValue " +methodValue+" int "+methodIntValue);

			CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
			OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
			OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
			
			if(methodValue !=0 || methodIntValue!=0)
			{

				logger.info("value is found in paybill");
				
				long advEMI=0;
				long emp_loan_id=0;
				long prin_install_no=0;
				long int_install_no=0;
				long tot_prin_install=0;
				long tot_int_install=0;
				long prinLoanAmt=0;
				long recoveredPrinLoanAmt=0;
				double totLoan=0;
				long loanEMI=0;
				long intLoanAmt=0;
				long recoveredIntLoanAmt=0;
				double totAdv=0;
				int totMiscAmt=0;
				int totMiscInst=0;
				int recoveredMiscAmt=0;
				int recoveredMiscInst=0;
				long loanEmi=0;
				
				HrEisEmpMst hrEisEmpMst = payBillVO.getHrEisEmpMst();
 				//varun
 				long empIdForLoan = hrEisEmpMst.getOrgEmpMst().getEmpId();
 				String compoCode = edpComp.getTypeId();
 				
				//String payBillMthdName = "setLoan"+edpCode;
				HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();
				EmpLoanDAOImpl hrLoanEmpDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
				LoanPrinRecoverDAOImpl hrLoanPrinRecoverDao = new LoanPrinRecoverDAOImpl(HrLoanEmpPrinRecoverDtls.class,serv.getSessionFactory());
				HrLoanEmpPrinRecoverDtls princiRecorerVo=new HrLoanEmpPrinRecoverDtls();
				LoanIntRecoverDAOImpl hrLoanIntRecoverDao = new LoanIntRecoverDAOImpl(HrLoanEmpIntRecoverDtls.class,serv.getSessionFactory());
				List<HrLoanEmpIntRecoverDtls> empLoanIntList;
				List<HrLoanEmpDtls> empLoanList = hrLoanEmpDao.getEmpLoanDetail(empIdForLoan, compoCode);//compocode is loan type id
				Calendar cal = Calendar.getInstance();
		           cal.set(Calendar.YEAR,yearGiven);
		           cal.set(Calendar.MONTH, monthGiven-1);
		            java.util.Date approvalDate = cal.getTime();
				for(int listCnt=0; listCnt<empLoanList.size();listCnt++)
				{
	
					if(empLoanList.size()>0)
					{									
						hrLoanEmp = empLoanList.get(listCnt);
						emp_loan_id=hrLoanEmp.getEmpLoanId();
						tot_prin_install=hrLoanEmp.getLoanPrinInstNo();
						tot_int_install=hrLoanEmp.getLoanIntInstNo();
						List<HrLoanEmpPrinRecoverDtls> empLoanPrinList = hrLoanPrinRecoverDao.getAllData(emp_loan_id);									
						Date loanDate=hrLoanEmp.getLoanDate();
		
						Calendar c = Calendar.getInstance();
       
						c.set(Calendar.YEAR, loanDate.getYear()+1900);
						c.set(Calendar.MONTH, loanDate.getMonth());
						c.set(Calendar.DAY_OF_MONTH, loanDate.getDate());
        
						c.add(Calendar.MONTH,(int)(tot_prin_install+tot_int_install)+1);//1 is added to get the last month
        
						Date endDate = c.getTime();
        
						c.set(Calendar.YEAR, yearGiven);
						c.set(Calendar.MONTH,monthGiven-1);
						int maxDayInMonth = c.getActualMaximum(5);
						c.set(Calendar.DAY_OF_MONTH,maxDayInMonth);
						Date currentDate = c.getTime();
						logger.info("in approval flag currentdate "+currentDate+" loan date "+loanDate+" end Date "+endDate);
        
						if(currentDate.compareTo(loanDate)==1 && currentDate.compareTo(endDate)<=0)
						{
					//deduction will be in the current month and 1 less than the current month
							logger.info("the current date is between loan date and end date");
							if(empLoanPrinList!=null && empLoanPrinList.size()>0)
							{
								logger.info("got the principal list");
				
								princiRecorerVo = new HrLoanEmpPrinRecoverDtls();
								princiRecorerVo=empLoanPrinList.get(0);
				
								prin_install_no = princiRecorerVo.getTotalRecoveredInst();
								recoveredPrinLoanAmt = princiRecorerVo.getTotalRecoveredAmt();
								prinLoanAmt = hrLoanEmp.getLoanPrinAmt();
				
								if(prin_install_no < tot_prin_install && recoveredPrinLoanAmt < prinLoanAmt)//for principal Recovery amt
								{
					//loanEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
									loanEMI=hrLoanEmp.getLoanPrinEmiAmt();//the Principal emi value
									if(loanEMI>(prinLoanAmt-recoveredPrinLoanAmt))
									{
										loanEMI=prinLoanAmt-recoveredPrinLoanAmt;
									}
									 
					//long amt=princiRecorerVo.getTotalRecoveredAmt()+loanEMI;
									long amt=recoveredPrinLoanAmt+loanEMI;
									princiRecorerVo.setTotalRecoveredAmt(amt);
									long new_no=prin_install_no+1;
									princiRecorerVo.setTotalRecoveredInst(new_no);
									princiRecorerVo.setUpdatedDate(approvalDate);
									princiRecorerVo.setOrgPostMstByUpdatedByPost(orgPostMst);
									princiRecorerVo.setOrgUserMstByUpdatedBy(orgUserMst);
									hrLoanPrinRecoverDao.update(princiRecorerVo);	
									loanEmi=loanEmi+loanEMI;

								}
								else//for interest Recovery amt
								{
									
									intRecoverVO = new HrLoanEmpIntRecoverDtls();
									empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);
					
									if(empLoanIntList.size()>0)
										intRecoverVO=empLoanIntList.get(0);
									intLoanAmt = hrLoanEmp.getLoanInterestAmt();
									recoveredIntLoanAmt = intRecoverVO.getTotalRecoveredInt();
					
									int_install_no=intRecoverVO.getTotalRecoveredIntInst();
									if(int_install_no<tot_int_install && recoveredIntLoanAmt < intLoanAmt)
									{
						//loanEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
										loanEMI=hrLoanEmp.getLoanIntEmiAmt();//the Interest emi value
										if(loanEMI>(intLoanAmt-recoveredIntLoanAmt))
										{
											loanEMI=intLoanAmt-recoveredIntLoanAmt;
										}
										long amt=recoveredIntLoanAmt+loanEMI;
										intRecoverVO.setTotalRecoveredInt(amt);
										long new_no=int_install_no+1;
										intRecoverVO.setTotalRecoveredIntInst(new_no);
										intRecoverVO.setUpdatedDate(approvalDate);
										intRecoverVO.setOrgPostMstByUpdatedByPost(orgPostMst);
										intRecoverVO.setOrgUserMstByUpdatedBy(orgUserMst);
										hrLoanIntRecoverDao.update(intRecoverVO);
										loanEmi=loanEmi+loanEMI;
									}
								}
								if(princiRecorerVo.getTotalRecoveredAmt() >= hrLoanEmp.getLoanPrinAmt() && intRecoverVO.getTotalRecoveredInt() >= hrLoanEmp.getLoanInterestAmt())
								{
									int loanStatusFlag=0;
									hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
									hrLoanEmpDao.update(hrLoanEmp);
								}
							}
							else
							{
								logger.info("got the principal list null now going to calculate interest");
								intRecoverVO = new HrLoanEmpIntRecoverDtls();
								empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);
								logger.info("the interest list is "+empLoanIntList);
								if(empLoanIntList.size()>0)
									intRecoverVO=empLoanIntList.get(0);
								intLoanAmt = hrLoanEmp.getLoanInterestAmt();
								recoveredIntLoanAmt = intRecoverVO.getTotalRecoveredInt();
								int_install_no=intRecoverVO.getTotalRecoveredIntInst();
								if(int_install_no<tot_int_install && recoveredIntLoanAmt < intLoanAmt)
								{
						//loanEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
									loanEMI=hrLoanEmp.getLoanIntEmiAmt();//the Interest emi value
									if(loanEMI>(intLoanAmt-recoveredIntLoanAmt))
									{
										loanEMI=intLoanAmt-recoveredIntLoanAmt;
									}
									long amt=recoveredIntLoanAmt+loanEMI;
									intRecoverVO.setTotalRecoveredInt(amt);
									long new_no=int_install_no+1;
									intRecoverVO.setTotalRecoveredIntInst(new_no);
									intRecoverVO.setUpdatedDate(approvalDate);
									intRecoverVO.setOrgPostMstByUpdatedByPost(orgPostMst);
									intRecoverVO.setOrgUserMstByUpdatedBy(orgUserMst);
									hrLoanIntRecoverDao.update(intRecoverVO);
									loanEmi=loanEmi+loanEMI;							
								}
			
								if(princiRecorerVo.getTotalRecoveredAmt() >= hrLoanEmp.getLoanPrinAmt() && intRecoverVO.getTotalRecoveredInt() >= hrLoanEmp.getLoanInterestAmt()){
									int loanStatusFlag=0;
									hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
									hrLoanEmpDao.update(hrLoanEmp);
								}
				
							}
						}	
						else
						{
							logger.info("got the loan is over");
						}
					}
				}
				logger.info("loan EDP is " + edpCode + " with value is " + loanEmi);

		//totLoan = totLoan+loanEmi;
	
		//Class thisClass = this.getClass();

			}//end of if for chking paybill value
		
		}
	
	public void updatePrinRecovery(HrLoanEmpDtls hrLoanEmp,long postId,long userId,int yearGiven,int monthGiven)
	{
		long prinLoanAmt=0;
		long tot_prin_install=hrLoanEmp.getLoanPrinInstNo();
		long tot_int_install=hrLoanEmp.getLoanIntInstNo();
		long emp_loan_id=hrLoanEmp.getEmpLoanId();
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
		
		
		 Calendar cal = Calendar.getInstance();
         SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
         cal.set(Calendar.YEAR,yearGiven);
         cal.set(Calendar.MONTH, monthGiven-1);

         java.util.Date approvalDate = cal.getTime();
		
		LoanPrinRecoverDAOImpl hrLoanPrinRecoverDao = new LoanPrinRecoverDAOImpl(HrLoanEmpPrinRecoverDtls.class,serv.getSessionFactory());
		EmpLoanDAOImpl hrLoanEmpDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
		List<HrLoanEmpPrinRecoverDtls> empLoanPrinList = hrLoanPrinRecoverDao.getAllData(emp_loan_id);	
		HrLoanEmpPrinRecoverDtls princiRecorerVo = new HrLoanEmpPrinRecoverDtls();
		princiRecorerVo  = empLoanPrinList.get(0);
		prinLoanAmt = hrLoanEmp.getLoanPrinAmt();
		int advId = (int)hrLoanEmp.getHrLoanAdvMst().getLoanAdvId();
		long recoveredPrinLoanAmt = princiRecorerVo.getTotalRecoveredAmt();   // Recovered Principal Amount.
		long prin_install_no = princiRecorerVo.getTotalRecoveredInst();
		logger.info("The Principal Advance Amount before :-"+recoveredPrinLoanAmt);
		//loan
		if(prin_install_no < tot_prin_install && recoveredPrinLoanAmt < prinLoanAmt)//for principal Recovery amt as well as Installment Number.
		{											
			//advEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
			long advEMI=hrLoanEmp.getLoanPrinEmiAmt();//the Principal emi value
			if(advEMI>(prinLoanAmt-recoveredPrinLoanAmt))
			{
				advEMI=prinLoanAmt-recoveredPrinLoanAmt;
			}
			logger.info("EMI After Calculation Is:-"+advEMI);
			//long amt=princiRecorerVo.getTotalRecoveredAmt()+advEMI;
			long amt=recoveredPrinLoanAmt+advEMI;
			princiRecorerVo.setTotalRecoveredAmt(amt);
			long new_no=prin_install_no+1;
			logger.info(new_no+" The Recovered Principal Amount after :-"+amt);												
			princiRecorerVo.setTotalRecoveredInst(new_no);
			princiRecorerVo.setUpdatedDate(approvalDate);
			princiRecorerVo.setOrgPostMstByUpdatedByPost(orgPostMst);
			princiRecorerVo.setOrgUserMstByUpdatedBy(orgUserMst);
			//hrLoanPrinRecoverDao.update(princiRecorerVo);
			// Added By Urvin shah.
			if(amt >=prinLoanAmt )
			{								
				int loanStatusFlag=0;
				hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
				//hrLoanEmpDao.update(hrLoanEmp);													
			}
			// End By Urvin shah.
		}
		// Commented By Urvin shah. As such 300136 is for Advances and there must not be Interest amount for the Advaces like gpf,FoodGrain and Festival Advance.

									
	
	}
	
}
