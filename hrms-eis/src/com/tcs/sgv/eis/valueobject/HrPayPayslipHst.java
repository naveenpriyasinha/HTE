// default package
// Generated Aug 29, 2007 12:41:44 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.eis.valueobject.HrEisQuaterTypeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrPayPayslipHst generated by hbm2java
 */
public class HrPayPayslipHst implements java.io.Serializable {

	// Fields    

	private HrPayPayslipHstId id;

	/*private long paybillId;

	private long empId;*/
	
	private HrPayPaybill hrPayPaybill;

	private HrEisEmpMst hrEisEmpMst;

	private long month;

	private long year;

	private String billNo;

	//private long quaterNo;
	private HrEisQuaterTypeMst hrQuaterTypeMst;

	private long incrementAmt;

	private String itAccNo;

	private String gpfAccNo;

	private String tokenNo;

	private long billAmt;

	private String budgetHead;

	private Date paySlipDate;

	private long basicPay;

	private long leavePay;

	private long splPay;

	private long perPay;

	private long da;

	private long cla;

	private long hra;

	private long wa;

	private long ma;

	private long ir;

	private long trAllow;

	private long ITax;

	private long hrr;

	private long PTax;

	//private long sis; By Urvin shah.

	private long gpf;

	private long gpfAdv;

	private long fesAdv;

	private long foodAdv;
	
	private long payRecovery;

	private long fanAdv;

	private long hba;

	private long vehAdv;

	private long miscRec;
	
	private long dp;
	
	//private long cpf;	By Urvin Shah.
	
	//private long carRent; By Urvin Shah.
	
	private long netTotal;

	//added by manoj for bonus
	private long bonus;
	
	private long DAGPF;
	
	/*private long langId;

	private long locId;

	private long dbId;

	private long createdBy;*/

	public long getDAGPF() {
		return DAGPF;
	}

	public void setDAGPF(long dagpf) {
		DAGPF = dagpf;
	}

	private Date createdDate;

	//private long updatedBy;

	private Date updatedDate;

	/*private long createdByPost;

	private long updatedByPost;*/
	
private CmnLanguageMst cmnLanguageMst;
	
	private CmnLocationMst cmnLocationMst;
	
	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;
	
	// Added By Urvin Shah for Common format of the Payslip.
	
	private int jeepRent;
	private int mcaInt;
	private int hbaInt;
	private int fanInt;
	private int ocaCycleAdv;
	private int ocaCycleInt;
	private int gis1979;
	private int gisIf;
	private int gisSf;
	
    // End Urvin shah.

	// Constructors

	/** default constructor */
	public HrPayPayslipHst() {
	}

	/** minimal constructor */
	public HrPayPayslipHst(HrPayPayslipHstId id) {
		this.id = id;
	}

	/** full constructor */
	public HrPayPayslipHst(HrPayPayslipHstId id,long month, long year, String billNo,
			long quaterNo, long incrementAmt, String itAccNo,
			String gpfAccNo, String tokenNo, long billAmt,
			String budgetHead, Date paySlipDate, long basicPay,
			long leavePay, long splPay, long perPay,
			long da, long cla, long hra, long wa,
			long ma, long ir, long trAllow, long ITax,
			long hrr, long PTax, long sis, long gpf,
			long gpfAdv, long fesAdv, long foodAdv,long payRecovery,
			long fanAdv, long hba, long vehAdv,
			long miscRec,Date createdDate, Date updatedDate,
			HrPayPaybill hrPayPaybill,
			int jeepRent,
			int mcaInt,
			int hbaInt,
			int fanInt,
			int ocaCycleAdv,
			int ocaCycleInt,
			int gis1979,
			int gisIf,
			int gisSf) {
		this.id = id;
		//this.paybillId = paybillId;
		this.hrPayPaybill = hrPayPaybill;
		//this.empId = empId;
		this.month = month;
		this.year = year;
		this.billNo = billNo;
		this.incrementAmt = incrementAmt;
		this.itAccNo = itAccNo;
		this.gpfAccNo = gpfAccNo;
		this.tokenNo = tokenNo;
		this.billAmt = billAmt;
		this.budgetHead = budgetHead;
		this.paySlipDate = paySlipDate;
		this.basicPay = basicPay;
		this.leavePay = leavePay;
		this.splPay = splPay;
		this.perPay = perPay;
		this.da = da;
		this.cla = cla;
		this.hra = hra;
		this.wa = wa;
		this.ma = ma;
		this.ir = ir;
		this.trAllow = trAllow;
		this.ITax = ITax;
		this.hrr = hrr;
		this.PTax = PTax;
		this.gpf = gpf;
		this.gpfAdv = gpfAdv;
		this.fesAdv = fesAdv;
		this.foodAdv = foodAdv;
		this.payRecovery=payRecovery;
		this.fanAdv = fanAdv;
		this.hba = hba;
		this.vehAdv = vehAdv;
		this.miscRec = miscRec;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.jeepRent = jeepRent;
		this.mcaInt = mcaInt;
		this.hbaInt = hbaInt;
		this.fanInt = fanInt;
		this.ocaCycleAdv = ocaCycleAdv;
		this.ocaCycleInt = ocaCycleInt;
		this.gis1979 = gis1979;
		this.gisIf = gisIf;
		this.gisSf = gisSf;		
	}

	// Property accessors
	public HrPayPayslipHstId getId() {
		return this.id;
	}

	public void setId(HrPayPayslipHstId id) {
		this.id = id;
	}

	/*public long getPaybillId() {
		return this.paybillId;
	}

	public void setPaybillId(long paybillId) {
		this.paybillId = paybillId;
	}*/

	/*public long getEmpId() {
		return this.empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}*/

	public long getMonth() {
		return this.month;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	public long getYear() {
		return this.year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public long getIncrementAmt() {
		return this.incrementAmt;
	}

	public void setIncrementAmt(long incrementAmt) {
		this.incrementAmt = incrementAmt;
	}

	public String getItAccNo() {
		return this.itAccNo;
	}

	public void setItAccNo(String itAccNo) {
		this.itAccNo = itAccNo;
	}

	public String getGpfAccNo() {
		return this.gpfAccNo;
	}

	public void setGpfAccNo(String gpfAccNo) {
		this.gpfAccNo = gpfAccNo;
	}

	public String getTokenNo() {
		return this.tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public long getBillAmt() {
		return this.billAmt;
	}

	public void setBillAmt(long billAmt) {
		this.billAmt = billAmt;
	}

	public String getBudgetHead() {
		return this.budgetHead;
	}

	public void setBudgetHead(String budgetHead) {
		this.budgetHead = budgetHead;
	}

	public Date getPaySlipDate() {
		return this.paySlipDate;
	}

	public void setPaySlipDate(Date paySlipDate) {
		this.paySlipDate = paySlipDate;
	}

	public long getBasicPay() {
		return this.basicPay;
	}

	public void setBasicPay(long basicPay) {
		this.basicPay = basicPay;
	}

	public long getLeavePay() {
		return this.leavePay;
	}

	public void setLeavePay(long leavePay) {
		this.leavePay = leavePay;
	}

	public long getSplPay() {
		return this.splPay;
	}

	public void setSplPay(long splPay) {
		this.splPay = splPay;
	}

	public long getPerPay() {
		return this.perPay;
	}

	public void setPerPay(long perPay) {
		this.perPay = perPay;
	}

	public long getDa() {
		return this.da;
	}

	public void setDa(long da) {
		this.da = da;
	}

	public long getCla() {
		return this.cla;
	}

	public void setCla(long cla) {
		this.cla = cla;
	}

	public long getHra() {
		return this.hra;
	}

	public void setHra(long hra) {
		this.hra = hra;
	}

	public long getWa() {
		return this.wa;
	}

	public void setWa(long wa) {
		this.wa = wa;
	}

	public long getMa() {
		return this.ma;
	}

	public void setMa(long ma) {
		this.ma = ma;
	}

	public long getIr() {
		return this.ir;
	}

	public void setIr(long ir) {
		this.ir = ir;
	}

	public long getTrAllow() {
		return this.trAllow;
	}

	public void setTrAllow(long trAllow) {
		this.trAllow = trAllow;
	}

	public long getITax() {
		return this.ITax;
	}

	public void setITax(long ITax) {
		this.ITax = ITax;
	}

	public long getHrr() {
		return this.hrr;
	}

	public void setHrr(long hrr) {
		this.hrr = hrr;
	}

	public long getPTax() {
		return this.PTax;
	}

	public void setPTax(long PTax) {
		this.PTax = PTax;
	}

	public void setGpf(long gpf) {
		this.gpf = gpf;
	}

	public long getGpfAdv() {
		return this.gpfAdv;
	}

	public void setGpfAdv(long gpfAdv) {
		this.gpfAdv = gpfAdv;
	}

	public long getFesAdv() {
		return this.fesAdv;
	}

	public void setFesAdv(long fesAdv) {
		this.fesAdv = fesAdv;
	}

	public long getFoodAdv() {
		return this.foodAdv;
	}

	public void setFoodAdv(long foodAdv) {
		this.foodAdv = foodAdv;
	}

	public long getFanAdv() {
		return this.fanAdv;
	}

	public void setFanAdv(long fanAdv) {
		this.fanAdv = fanAdv;
	}

	public long getHba() {
		return this.hba;
	}

	public void setHba(long hba) {
		this.hba = hba;
	}

	public long getVehAdv() {
		return this.vehAdv;
	}

	public void setVehAdv(long vehAdv) {
		this.vehAdv = vehAdv;
	}

	public long getMiscRec() {
		return this.miscRec;
	}

	public void setMiscRec(long miscRec) {
		this.miscRec = miscRec;
	}

	/*public long getLangId() {
		return this.langId;
	}

	public void setLangId(long langId) {
		this.langId = langId;
	}

	public long getLocId() {
		return this.locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public long getDbId() {
		return this.dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}*/

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/*public long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}*/

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public CmnDatabaseMst getCmnDatabaseMst() {
		return cmnDatabaseMst;
	}

	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst) {
		this.cmnDatabaseMst = cmnDatabaseMst;
	}

	public CmnLanguageMst getCmnLanguageMst() {
		return cmnLanguageMst;
	}

	public void setCmnLanguageMst(CmnLanguageMst cmnLanguageMst) {
		this.cmnLanguageMst = cmnLanguageMst;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}

	public HrEisEmpMst getHrEisEmpMst() {
		return hrEisEmpMst;
	}

	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst) {
		this.hrEisEmpMst = hrEisEmpMst;
	}

	public HrPayPaybill getHrPayPaybill() {
		return hrPayPaybill;
	}

	public void setHrPayPaybill(HrPayPaybill hrPayPaybill) {
		this.hrPayPaybill = hrPayPaybill;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	public long getDp() {
		return dp;
	}

	public void setDp(long dp) {
		this.dp = dp;
	}

	public HrEisQuaterTypeMst getHrQuaterTypeMst() {
		return hrQuaterTypeMst;
	}

	public void setHrQuaterTypeMst(HrEisQuaterTypeMst hrQuaterTypeMst) {
		this.hrQuaterTypeMst = hrQuaterTypeMst;
	}

	public long getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(long netTotal) {
		this.netTotal = netTotal;
	}

	public int getFanInt() {
		return fanInt;
	}

	public void setFanInt(int fanInt) {
		this.fanInt = fanInt;
	}

	public int getGis1979() {
		return gis1979;
	}

	public void setGis1979(int gis1979) {
		this.gis1979 = gis1979;
	}

	public int getGisIf() {
		return gisIf;
	}

	public void setGisIf(int gisIf) {
		this.gisIf = gisIf;
	}

	public int getGisSf() {
		return gisSf;
	}

	public void setGisSf(int gisSf) {
		this.gisSf = gisSf;
	}

	public int getHbaInt() {
		return hbaInt;
	}

	public void setHbaInt(int hbaInt) {
		this.hbaInt = hbaInt;
	}

	public int getJeepRent() {
		return jeepRent;
	}

	public void setJeepRent(int jeepRent) {
		this.jeepRent = jeepRent;
	}

	public int getMcaInt() {
		return mcaInt;
	}

	public void setMcaInt(int mcaInt) {
		this.mcaInt = mcaInt;
	}

	public int getOcaCycleAdv() {
		return ocaCycleAdv;
	}

	public void setOcaCycleAdv(int ocaCycleAdv) {
		this.ocaCycleAdv = ocaCycleAdv;
	}

	public int getOcaCycleInt() {
		return ocaCycleInt;
	}

	public void setOcaCycleInt(int ocaCycleInt) {
		this.ocaCycleInt = ocaCycleInt;
	}

	public long getGpf() {
		return gpf;
	}

	public long getPayRecovery() {
		return payRecovery;
	}

	public void setPayRecovery(long payRecovery) {
		this.payRecovery = payRecovery;
	}

	public long getBonus() {
		return bonus;
	}

	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	/*public long getCreatedByPost() {
		return this.createdByPost;
	}

	public void setCreatedByPost(long createdByPost) {
		this.createdByPost = createdByPost;
	}

	public long getUpdatedByPost() {
		return this.updatedByPost;
	}

	public void setUpdatedByPost(long updatedByPost) {
		this.updatedByPost = updatedByPost;
	}*/
	

}
