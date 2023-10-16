package com.tcs.sgv.eis.valueobject;
// Generated Aug 13, 2007 3:20:54 PM by Hibernate Tools 3.1.0.beta5


import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrPayPaybill generated by hbm2java
 */
public class HrPayArrearPaybill implements java.io.Serializable {

	// Fields    

	private long id;

	private HrEisEmpMst hrEisEmpMst;

	//Modified by Mrugesh
	/*private long month;

	private long year;*/
	//Ended By Mrugesh
	private double allow0101;//personal pay
	private double allow0102;//special pay

	private double basic0101;//pay of officer
	private double basic0102;//pay of establishment

	private double gpay0101;//pay of officer
	
	
	
	private double ls;
	private double le;

	private double allow0109;//le
	
	public double getGpay0101() {
		return gpay0101;
	}

	public void setGpay0101(double gpay0101) {
		this.gpay0101 = gpay0101;
	}

	public double getAllow0109() {
		return allow0109;
	}

	public void setAllow0109(double allow0109) {
		this.allow0109 = allow0109;
	}

	private double allow0119;//DP
	private double allow0120;//DP_GAZZETED
	private double allow0103;//DA
	private double allow0110;//HRA
	private double allow0105;//ltc
	private double allow0111;//CLA
	private double allow0104;//other allowance
	private double allow0107;//MA
	private double allow0108;//bonus
	private double allow1301;//WA
	private double allow5006;//other charges
	private double allow0113;//TrA

	private double adv5701;//Fes.Adv
	private double adv5801;//Food Adv
	
	private double adv7057;//Fes.Adv
	public double getDeduc0102() {
		return deduc0102;
	}

	public void setDeduc0102(double deduc0102) {
		this.deduc0102 = deduc0102;
	}

	private double adv7058;//Food Adv
	
	private double adv0101;//PayRecovery
	private double deduc0101;//PayRecovery
	
	private double grossAmt;
	private double slo;
	private double it;
	private double surcharge;
	
	private double deduc9510;//it
	public double getDeduc9510() {
		return deduc9510;
	}

	public void setDeduc9510(double deduc9510) {
		this.deduc9510 = deduc9510;
	}

	public double getDeduc9520() {
		return deduc9520;
	}

	public void setDeduc9520(double deduc9520) {
		this.deduc9520 = deduc9520;
	}

	private double deduc9520;//surchard
	
	private double deduc0102;//leavesalary
	
	
	private double deduc9550;//HRR
	private double deduc9560;//rent building
	private double deduc9530;//PLI
	private double deduc9540;//bli
	private double deduc9570;//PT
	private double deduc9580;//SIS_GIS
	private double deduc9581;//SisIF
	private double deduc9582;//SisSF
	private double deduc9583;//AISIF
	private double deduc9584;//AisSF
	private double deduc9670;//GPF
	private double adv9670;//GPF advance

	private double loan9592;//carSctMopedAdv;
	private double loanInt9592;//carSctMopedInt;

	private double loan9740;//ocaCycleAdv;
	private double loanInt9740;//ocaCycleInt;

	private double loan9591;//hbaHb;
	private double loanInt9591;//hbaInt;

	private double loan9720;//fanA;
	private double loanInt9720;//fanInt
	
	private double deduc9780;//jeepR;
	private double deduc9910;//Misc recovery

	//private double gpfIv;
	private double deduc9531;

	private double totalDed;

	private double netTotal;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;	
	
	private Integer trnCounter;
	
	//Added by Paurav for identifying Paybill entries generated at same time
	private long paybillGrpId;
	//Ended By Paurav
	
	private double advIV9670;
	
	private OrgPostMst orgPostMst;
	
	//Modified By Mrugesh
	//private long approveFlag;
	//Ended By Mrugesh
	private Date approveRejectDate;
	
	private double deduc9534;	// CPF
	
	private double deduc9620; // AIS PF
	
	private  HrPaySalRevMst salRevId;
	
	private HrPayPaybill paybillId;
	
	String billNo;
	
	private long psrNo;		// PSR NO.
	
	private double deduc9999;//da_gpf
	private double deduc9998;//da_gpfiv
	// Constructors
	
	private long otherTrnCntr;
	
	private HrEisOtherDtls hrEisOtherDtls;


	public double getDeduc9999() {
		return deduc9999;
	}

	public void setDeduc9999(double deduc9999) {
		this.deduc9999 = deduc9999;
	}
	
	public double getDeduc9998() {
		return deduc9998;
	}

	public void setDeduc9998(double deduc9998) {
		this.deduc9998 = deduc9998;
	}

	public long getPsrNo() {
		return psrNo;
	}

	public void setPsrNo(long psrNo) {
		this.psrNo = psrNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	/*public long getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(long approveFlag) {
		this.approveFlag = approveFlag;
	}*/

	public Date getApproveRejectDate() {
		return approveRejectDate;
	}

	public void setApproveRejectDate(Date approveRejectDate) {
		this.approveRejectDate = approveRejectDate;
	}

	public OrgPostMst getOrgPostMst() {
		return orgPostMst;
	}

	public void setOrgPostMst(OrgPostMst orgPostMst) {
		this.orgPostMst = orgPostMst;
	}

	
	

	public HrPayPaybill getPaybillId() {
		return paybillId;
	}

	public void setPaybillId(HrPayPaybill paybillId) {
		this.paybillId = paybillId;
	}

	public HrPaySalRevMst getSalRevId() {
		return salRevId;
	}

	public void setSalRevId(HrPaySalRevMst salRevId) {
		this.salRevId = salRevId;
	}

	public HrPayArrearPaybill() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** minimal constructor */
	public HrPayArrearPaybill(long id, HrEisEmpMst hrEisEmpMst
			/*long month, long year*/) {
		this.id = id;
		this.hrEisEmpMst = hrEisEmpMst;
		/*this.month = month;
		this.year = year;*/
	}

	/** full constructor */
	public HrPayArrearPaybill(long id, HrEisEmpMst hrEisEmpMst,
			/*long month, long year,*/ double perSplPay,
			double lsLe, double allow0119,
			double allow0103, double allow0110, double allow0111,
			double allow0107, double allow1301, double allow0113,
			double adv5701, double adv5801, double adv0101,
			double grossAmt, double slo, double itSurcharge,
			double deduc9550, double deduc9530, double deduc9570,
			double deduc9580, double deduc9581, double deduc9582,
			double deduc9583, double deduc9584, double deduc9670,
			double loan9592, double loan9740,
			double loan9591, double loan9720, double deduc9780,
			double deduc9531, double totalDed, double netTotal,			
			double gpay0101 , 
			CmnDatabaseMst cmnDatabaseMst,
			OrgPostMst orgPostMstByCreatedByPost,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByCreatedBy,
			Date createdDate,Integer trnCounter,long paybillGrpId,HrPayPaybill paybillId,HrPaySalRevMst salRevId
			) {
		this.id = id;
		this.hrEisEmpMst = hrEisEmpMst;
		/*this.month = month;
		this.year = year;*/
		this.gpay0101 =gpay0101;		
		this.allow0101 = perSplPay;
		this.le = lsLe;
		this.allow0119 = allow0119;
		this.allow0103 = allow0103;
		this.allow0110 = allow0110;
		this.allow0111 = allow0111;
		this.allow0107 = allow0107;
		this.allow1301 = allow1301;
		this.allow0113 = allow0113;
		this.adv5701 = adv5701;
		this.adv5801 = adv5801;
		this.adv0101 = adv0101;
		this.grossAmt = grossAmt;
		this.slo = slo;
		this.it = itSurcharge;
		this.deduc9550 = deduc9550;
		this.deduc9530 = deduc9530;
		this.deduc9570 = deduc9570;
		this.deduc9580 = deduc9580;
		this.deduc9581 = deduc9581;
		this.deduc9582 = deduc9582;
		this.deduc9583 = deduc9583;
		this.deduc9584 = deduc9584;
		this.deduc9670 = deduc9670;
		this.loan9592 = loan9592;
		this.loan9740 = loan9740;
		this.loan9591 = loan9591;
		this.loan9720 = loan9720;
		this.deduc9780 = deduc9780;
		//this.gpfIv = gpfIv;
		this.deduc9531=deduc9531;
		this.totalDed = totalDed;
		this.netTotal = netTotal;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.createdDate = createdDate;
		this.trnCounter = trnCounter;
		this.paybillGrpId = paybillGrpId;
		this.paybillId = paybillId;
		this.salRevId=salRevId;
	}
//	 Property accessors

	public double getAdv0101() {
		return adv0101;
	}

	public void setAdv0101(double adv0101) {
		this.adv0101 = adv0101;
	}

	public double getAdv5701() {
		return adv5701;
	}

	public void setAdv5701(double adv5701) {
		this.adv5701 = adv5701;
	}

	public double getAdv5801() {
		return adv5801;
	}

	public void setAdv5801(double adv5801) {
		this.adv5801 = adv5801;
	}

	public double getAdv9670() {
		return adv9670;
	}

	public void setAdv9670(double adv9670) {
		this.adv9670 = adv9670;
	}

	public double getAllow0103() {
		return allow0103;
	}

	public void setAllow0103(double allow0103) {
		this.allow0103 = allow0103;
	}

	public double getAllow0104() {
		return allow0104;
	}

	public void setAllow0104(double allow0104) {
		this.allow0104 = allow0104;
	}

	public double getAllow0105() {
		return allow0105;
	}

	public void setAllow0105(double allow0105) {
		this.allow0105 = allow0105;
	}

	public double getAllow0107() {
		return allow0107;
	}

	public void setAllow0107(double allow0107) {
		this.allow0107 = allow0107;
	}

	public double getAllow0108() {
		return allow0108;
	}

	public void setAllow0108(double allow0108) {
		this.allow0108 = allow0108;
	}

	public double getAllow0110() {
		return allow0110;
	}

	public void setAllow0110(double allow0110) {
		this.allow0110 = allow0110;
	}

	public double getAllow0111() {
		return allow0111;
	}

	public void setAllow0111(double allow0111) {
		this.allow0111 = allow0111;
	}

	public double getAllow0113() {
		return allow0113;
	}

	public void setAllow0113(double allow0113) {
		this.allow0113 = allow0113;
	}

	public double getAllow0119() {
		return allow0119;
	}

	public void setAllow0119(double allow0119) {
		this.allow0119 = allow0119;
	}

	public double getAllow1301() {
		return allow1301;
	}

	public void setAllow1301(double allow1301) {
		this.allow1301 = allow1301;
	}

	public double getAllow5006() {
		return allow5006;
	}

	public void setAllow5006(double allow5006) {
		this.allow5006 = allow5006;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public double getDeduc9530() {
		return deduc9530;
	}

	public void setDeduc9530(double deduc9530) {
		this.deduc9530 = deduc9530;
	}

	public double getDeduc9540() {
		return deduc9540;
	}

	public void setDeduc9540(double deduc9540) {
		this.deduc9540 = deduc9540;
	}

	public double getDeduc9550() {
		return deduc9550;
	}

	public void setDeduc9550(double deduc9550) {
		this.deduc9550 = deduc9550;
	}

	public double getDeduc9570() {
		return deduc9570;
	}

	public void setDeduc9570(double deduc9570) {
		this.deduc9570 = deduc9570;
	}

	public double getDeduc9580() {
		return deduc9580;
	}

	public void setDeduc9580(double deduc9580) {
		this.deduc9580 = deduc9580;
	}

	public double getDeduc9581() {
		return deduc9581;
	}

	public void setDeduc9581(double deduc9581) {
		this.deduc9581 = deduc9581;
	}

	public double getDeduc9582() {
		return deduc9582;
	}

	public void setDeduc9582(double deduc9582) {
		this.deduc9582 = deduc9582;
	}

	public double getDeduc9583() {
		return deduc9583;
	}

	public void setDeduc9583(double deduc9583) {
		this.deduc9583 = deduc9583;
	}

	public double getDeduc9584() {
		return deduc9584;
	}

	public void setDeduc9584(double deduc9584) {
		this.deduc9584 = deduc9584;
	}

	public double getDeduc9670() {
		return deduc9670;
	}

	public void setDeduc9670(double deduc9670) {
		this.deduc9670 = deduc9670;
	}

	public double getDeduc9910() {
		return deduc9910;
	}

	public void setDeduc9910(double deduc9910) {
		this.deduc9910 = deduc9910;
	}

	

	/*public double getGpfIv() {
		return gpfIv;
	}

	public void setGpfIv(double gpfIv) {
		this.gpfIv = gpfIv;
	}*/

	public double getGrossAmt() {
		return grossAmt;
	}

	public void setGrossAmt(double grossAmt) {
		this.grossAmt = grossAmt;
	}

	public HrEisEmpMst getHrEisEmpMst() {
		return hrEisEmpMst;
	}

	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst) {
		this.hrEisEmpMst = hrEisEmpMst;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getIt() {
		return it;
	}

	public void setIt(double it) {
		this.it = it;
	}

	public double getLe() {
		return le;
	}

	public void setLe(double le) {
		this.le = le;
	}

	public double getLoan9591() {
		return loan9591;
	}

	public void setLoan9591(double loan9591) {
		this.loan9591 = loan9591;
	}

	public double getLoan9592() {
		return loan9592;
	}

	public void setLoan9592(double loan9592) {
		this.loan9592 = loan9592;
	}

	public double getLoan9720() {
		return loan9720;
	}

	public void setLoan9720(double loan9720) {
		this.loan9720 = loan9720;
	}

	public double getLoan9740() {
		return loan9740;
	}

	public void setLoan9740(double loan9740) {
		this.loan9740 = loan9740;
	}


	public double getLs() {
		return ls;
	}

	public void setLs(double ls) {
		this.ls = ls;
	}

	/*public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
	}*/

	public double getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(double netTotal) {
		this.netTotal = netTotal;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public double getAllow0101() {
		return allow0101;
	}

	public void setAllow0101(double allow0101) {
		this.allow0101 = allow0101;
	}

	public double getAllow0102() {
		return allow0102;
	}

	public void setAllow0102(double allow0102) {
		this.allow0102 = allow0102;
	}

	public double getSlo() {
		return slo;
	}

	public void setSlo(double slo) {
		this.slo = slo;
	}

	public double getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(double surcharge) {
		this.surcharge = surcharge;
	}

	public double getTotalDed() {
		return totalDed;
	}

	public void setTotalDed(double totalDed) {
		this.totalDed = totalDed;
	}

	/*public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}*/

	public double getDeduc9560() {
		return deduc9560;
	}

	public void setDeduc9560(double deduc9560) {
		this.deduc9560 = deduc9560;
	}

	public double getDeduc9531() {
		return deduc9531;
	}

	public void setDeduc9531(double deduc9531) {
		this.deduc9531 = deduc9531;
	}

	public Integer getTrnCounter() {
		return trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {
		this.trnCounter = trnCounter;
	}

	public double getBasic0101() {
		return basic0101;
	}

	public void setBasic0101(double basic0101) {
		this.basic0101 = basic0101;
	}

	public double getBasic0102() {
		return basic0102;
	}

	public void setBasic0102(double basic0102) {
		this.basic0102 = basic0102;
	}

	public double getAllow0120() {
		return allow0120;
	}

	public void setAllow0120(double allow0120) {
		this.allow0120 = allow0120;
	}

	public long getPaybillGrpId() {
		return paybillGrpId;
	}

	public void setPaybillGrpId(long paybillGrpId) {
		this.paybillGrpId = paybillGrpId;
	}

	public double getLoanInt9591() {
		return loanInt9591;
	}

	public void setLoanInt9591(double loanInt9591) {
		this.loanInt9591 = loanInt9591;
	}

	public double getLoanInt9592() {
		return loanInt9592;
	}

	public void setLoanInt9592(double loanInt9592) {
		this.loanInt9592 = loanInt9592;
	}

	public double getLoanInt9720() {
		return loanInt9720;
	}

	public void setLoanInt9720(double loanInt9720) {
		this.loanInt9720 = loanInt9720;
	}

	public double getLoanInt9740() {
		return loanInt9740;
	}

	public void setLoanInt9740(double loanInt9740) {
		this.loanInt9740 = loanInt9740;
	}


	public double getAdvIV9670() {
		return advIV9670;
	}

	public void setAdvIV9670(double advIV9670) {
		this.advIV9670 = advIV9670;
	}

	public double getDeduc9620() {
		return deduc9620;
	}

	public void setDeduc9620(double deduc9620) {
		this.deduc9620 = deduc9620;
	}

	public double getDeduc9534() {
		return deduc9534;
	}

	public void setDeduc9534(double deduc9534) {
		this.deduc9534 = deduc9534;
	}

	public double getDeduc9780() {
		return deduc9780;
	}

	public void setDeduc9780(double deduc9780) {
		this.deduc9780 = deduc9780;
	}

	public double getAdv7057() {
		return adv7057;
	}

	public void setAdv7057(double adv7057) {
		this.adv7057 = adv7057;
	}

	public double getAdv7058() {
		return adv7058;
	}

	public void setAdv7058(double adv7058) {
		this.adv7058 = adv7058;
	}

	public double getDeduc0101() {
		return deduc0101;
	}

	public void setDeduc0101(double deduc0101) {
		this.deduc0101 = deduc0101;
	}
	
	public long getOtherTrnCntr() {
		return otherTrnCntr;
	}

	public void setOtherTrnCntr(long otherTrnCntr) {
		this.otherTrnCntr = otherTrnCntr;
	}

	public HrEisOtherDtls getHrEisOtherDtls() {
		return hrEisOtherDtls;
	}

	public void setHrEisOtherDtls(HrEisOtherDtls hrEisOtherDtls) {
		this.hrEisOtherDtls = hrEisOtherDtls;
	}

	
}
