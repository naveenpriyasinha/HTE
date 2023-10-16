package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;


public class HrEisEmpCompMpg implements java.io.Serializable{
	
	private long EmpCompId;
	CmnLookupMst cmnLookupMst;
	private long compId;
	private long isactive;
	private Date startDate;
	private Date endDate;
	HrEisEmpCompGrpMst hrEisEmpCompGrpMst;
	private String remarks;
	private long updatedByPost;
	private Date updatedDate; 
	
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public long getUpdatedByPost()
	{
		return updatedByPost;
	}

	public void setUpdatedByPost(long updatedByPost)
	{
		this.updatedByPost = updatedByPost;
	}
	
	/** default constructor */
	public HrEisEmpCompMpg() {
	}
	/** minimal constructor */	
	public HrEisEmpCompMpg(long EmpCompId)
	{
		this.EmpCompId=EmpCompId;
	}
	
	/** full constructor */
	public HrEisEmpCompMpg(long empCompId, CmnLookupMst cmnLookupMst,
			long compId, long isactive, HrEisEmpCompGrpMst hrEisEmpCompGrpMst,String remarks) {
		super();
		EmpCompId = empCompId;
		this.cmnLookupMst = cmnLookupMst;
		this.compId = compId;
		this.isactive = isactive;
		this.hrEisEmpCompGrpMst = hrEisEmpCompGrpMst;
		this.remarks = remarks;
	}
	
	
	
	public long getEmpCompId() {
		return EmpCompId;
	}
	public void setEmpCompId(long empCompId) {
		EmpCompId = empCompId;
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
	public long getIsactive() {
		return isactive;
	}
	public void setIsactive(long isactive) {
		this.isactive = isactive;
	}
	public HrEisEmpCompGrpMst getHrEisEmpCompGrpMst() {
		return hrEisEmpCompGrpMst;
	}
	public void setHrEisEmpCompGrpMst(HrEisEmpCompGrpMst hrEisEmpCompGrpMst) {
		this.hrEisEmpCompGrpMst = hrEisEmpCompGrpMst;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
