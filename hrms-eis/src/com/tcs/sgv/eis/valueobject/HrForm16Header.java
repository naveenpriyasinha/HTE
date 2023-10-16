package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class HrForm16Header  implements java.io.Serializable {
	
	private long id;
	
	private long year;
	
	private CmnLocationMst deptId;

	private long ddoId;

	private String tan;

	private String ito;

	private String quarter1;

	private String quarter2;
	
	private String quarter3;
	
	private String quarter4;

	private Date createdDate;

	private OrgPostMst orgPostMstByCreatedByPost;

	private OrgUserMst orgUserMstByCreatedBy;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getDdoId() {
		return ddoId;
	}

	public void setDdoId(long ddoId) {
		this.ddoId = ddoId;
	}

	public CmnLocationMst getDeptId() {
		return deptId;
	}

	public void setDeptId(CmnLocationMst deptId) {
		this.deptId = deptId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIto() {
		return ito;
	}

	public void setIto(String ito) {
		this.ito = ito;
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

	public String getQuarter1() {
		return quarter1;
	}

	public void setQuarter1(String quarter1) {
		this.quarter1 = quarter1;
	}

	public String getQuarter2() {
		return quarter2;
	}

	public void setQuarter2(String quarter2) {
		this.quarter2 = quarter2;
	}

	public String getQuarter3() {
		return quarter3;
	}

	public void setQuarter3(String quarter3) {
		this.quarter3 = quarter3;
	}

	public String getQuarter4() {
		return quarter4;
	}

	public void setQuarter4(String quarter4) {
		this.quarter4 = quarter4;
	}

	public String getTan() {
		return tan;
	}

	public void setTan(String tan) {
		this.tan = tan;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}
	
	

}
