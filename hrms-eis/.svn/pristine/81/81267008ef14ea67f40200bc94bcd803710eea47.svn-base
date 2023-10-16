package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;


public class HrEisEmpCompMpgHst implements java.io.Serializable{
	
	private long EmpCompId;
	OrgEmpMst orgEmpMst;
	CmnLookupMst cmnLookupMst;
	private long compId;
	private long month;
	private long year;
	private Date effectDate;
	private long isactive;
	private long grpId;
	
	
	/** default constructor */
	public HrEisEmpCompMpgHst() {
	}
	/** minimal constructor */	
	public HrEisEmpCompMpgHst(long EmpCompId)
	{
		this.EmpCompId=EmpCompId;
	}
	
	/** full constructor */
	public HrEisEmpCompMpgHst(long empCompId, OrgEmpMst orgEmpMst,
			CmnLookupMst cmnLookupMst, long compId, long month, long year,
			Date effectDate, long isactive,long grpId) {
		super();
		EmpCompId = empCompId;
		this.orgEmpMst = orgEmpMst;
		this.cmnLookupMst = cmnLookupMst;
		this.compId = compId;
		this.month = month;
		this.year = year;
		this.effectDate = effectDate;
		this.isactive = isactive;
		this.grpId = grpId;
	}
	
	public long getEmpCompId() {
		return EmpCompId;
	}
	public void setEmpCompId(long empCompId) {
		EmpCompId = empCompId;
	}
	public OrgEmpMst getOrgEmpMst() {
		return orgEmpMst;
	}
	public void setOrgEmpMst(OrgEmpMst orgEmpMst) {
		this.orgEmpMst = orgEmpMst;
	}
	public CmnLookupMst getCmnLookupMst() {
		return cmnLookupMst;
	}
	public void setCmnLookupMst(CmnLookupMst cmnLookupMst) {
		this.cmnLookupMst = cmnLookupMst;
	}
	public long getCompId() {
		return compId;
	}
	public void setCompId(long compId) {
		this.compId = compId;
	}
	public long getMonth() {
		return month;
	}
	public void setMonth(long month) {
		this.month = month;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	public long getIsactive() {
		return isactive;
	}
	public void setIsactive(long isactive) {
		this.isactive = isactive;
	}
	
	public long getGrpId() {
		return grpId;
	}
	public void setGrpId(long grpId) {
		this.grpId = grpId;
	}
	

}
