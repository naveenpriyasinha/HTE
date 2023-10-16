package com.tcs.sgv.eis.valueobject;
//Comment By Maruthi For import Organisation.

import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public class PayrollCustomVO {
	
	private OrgEmpMst orgEmpMst;
	
	private HrEisQtrMst hrEssQtrMst;

	//only for otherdata display
	private long otherId;
	
	private String empName;
	
	private String gradeName;
	
	private String dsgnName;
	
	private long currentBasic;
	
	private String currencycurrentBasic;
	
	private long empId;
	
//	only for otherdata display end
	
	// only for emp allow data and deduc data display 
	
	private long empType;
	
	private String sevarthId;
	
	// Default Constructor.
	
	public long getCurrentBasic() {
		return currentBasic;
	}

	public void setCurrentBasic(long currentBasic) {
		this.currentBasic = currentBasic;
	}

	public PayrollCustomVO(){
		
	}
	
   // Full Constructor
	
	public PayrollCustomVO(OrgEmpMst orgEmpMst,HrEisQtrMst hrEssQtrMst) {
		this.orgEmpMst = orgEmpMst;
		this.hrEssQtrMst = hrEssQtrMst;
		
	}

//	 Getter And Setter.
	
	public HrEisQtrMst getHrEssQtrMst() {
		return hrEssQtrMst;
	}

	public void setHrEssQtrMst(HrEisQtrMst hrEssQtrMst) {
		this.hrEssQtrMst = hrEssQtrMst;
	}

	public OrgEmpMst getOrgEmpMst() {
		return orgEmpMst;
	}

	public void setOrgEmpMst(OrgEmpMst orgEmpMst) {
		this.orgEmpMst = orgEmpMst;
	}

	public String getDsgnName() {
		return dsgnName;
	}

	public void setDsgnName(String dsgnName) {
		this.dsgnName = dsgnName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public long getOtherId() {
		return otherId;
	}

	public void setOtherId(long otherId) {
		this.otherId = otherId;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public long getEmpType() {
		return empType;
	}

	public void setEmpType(long empType) {
		this.empType = empType;
	}

	public String getCurrencycurrentBasic() {
		return currencycurrentBasic;
	}

	public void setCurrencycurrentBasic(String currencycurrentBasic) {
		this.currencycurrentBasic = currencycurrentBasic;
	}

	public String getSevarthId() {
		return sevarthId;
	}

	public void setSevarthId(String sevarthId) {
		this.sevarthId = sevarthId;
	}	
	
	
}
