package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class HrForm16Dtls implements java.io.Serializable{

	private long form16DtlId ;
	private HrEisEmpMst hrEisEmpMst;
	private String finYrId;
	
	
	private double otherAllow;
	private double foreignAllow;
	private double challanTax;
	private double arrearTax;
	private double profTax;
	private double travelAllow;
	private double hbaIntrest;
	private double gpfCpf;
	private double govtInsurance;
	private double repayHba;
	
	private String challanNumber;
	
	private int hbaInterestClaimed;
	private int hbaRepayClaimed;
	
	private OrgUserMst createdBy;
	private Date createdDate;
	private OrgUserMst updatedBy;
	private Date updatedDate;
	
	//private Set<HrForm16TaxDeducDtls> set = new HashSet<HrForm16TaxDeducDtls>(0);
	
	public HrForm16Dtls(){
		
	}

	public long getForm16DtlId() {
		return form16DtlId;
	}

	public void setForm16DtlId(long form16DtlId) {
		this.form16DtlId = form16DtlId;
	}

	public HrEisEmpMst getHrEisEmpMst() {
		return hrEisEmpMst;
	}

	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst) {
		this.hrEisEmpMst = hrEisEmpMst;
	}

	public String getFinYrId() {
		return finYrId;
	}

	public void setFinYrId(String finYrId) {
		this.finYrId = finYrId;
	}

	public double getOtherAllow() {
		return otherAllow;
	}

	public void setOtherAllow(double otherAllow) {
		this.otherAllow = otherAllow;
	}

	public double getForeignAllow() {
		return foreignAllow;
	}

	public void setForeignAllow(double foreignAllow) {
		this.foreignAllow = foreignAllow;
	}

	public double getChallanTax() {
		return challanTax;
	}

	public void setChallanTax(double challanTax) {
		this.challanTax = challanTax;
	}

	public double getArrearTax() {
		return arrearTax;
	}

	public void setArrearTax(double arrearTax) {
		this.arrearTax = arrearTax;
	}

	public OrgUserMst getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(OrgUserMst createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public OrgUserMst getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(OrgUserMst updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public double getProfTax() {
		return profTax;
	}

	public double getTravelAllow() {
		return travelAllow;
	}

	public double getHbaIntrest() {
		return hbaIntrest;
	}

	public double getGpfCpf() {
		return gpfCpf;
	}

	public double getGovtInsurance() {
		return govtInsurance;
	}

	public double getRepayHba() {
		return repayHba;
	}

	public void setProfTax(double profTax) {
		this.profTax = profTax;
	}

	public void setTravelAllow(double travelAllow) {
		this.travelAllow = travelAllow;
	}

	public void setHbaIntrest(double hbaIntrest) {
		this.hbaIntrest = hbaIntrest;
	}

	public void setGpfCpf(double gpfCpf) {
		this.gpfCpf = gpfCpf;
	}

	public void setGovtInsurance(double govtInsurance) {
		this.govtInsurance = govtInsurance;
	}

	public void setRepayHba(double repayHba) {
		this.repayHba = repayHba;
	}

	public int getHbaInterestClaimed() {
		return hbaInterestClaimed;
	}

	public void setHbaInterestClaimed(int hbaInterestClaimed) {
		this.hbaInterestClaimed = hbaInterestClaimed;
	}

	public int getHbaRepayClaimed() {
		return hbaRepayClaimed;
	}

	public void setHbaRepayClaimed(int hbaRepayClaimed) {
		this.hbaRepayClaimed = hbaRepayClaimed;
	}

	public String getChallanNumber() {
		return challanNumber;
	}

	public void setChallanNumber(String challanNumber) {
		this.challanNumber = challanNumber;
	}

	/*public void setSet(Set<HrForm16TaxDeducDtls> set) {
		this.set = set;
	}

	public Set<HrForm16TaxDeducDtls> getSet() {
		return set;
	}
*/
	
}
