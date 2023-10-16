package com.tcs.sgv.eis.valueobject;
import java.io.Serializable;
import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.ess.valueobject.CmnBranchlocMpg;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class HrEisBranchDtls implements Serializable 
{

	private long Id;
	
	private CmnBranchlocMpg cmnBranchlocMpg;
	private CmnLookupMst cmnLookupMst;
	private CmnLanguageMst cmnLanguageMst;
	private CmnLocationMst cmnLocationMst;
	private OrgUserMst orgUserMstByCreatedBy;
	
	private Date createdDate;
	
	private OrgPostMst orgPostMstByCreatedByPost;
	
	private OrgUserMst orgUserMstByUpdatedBy;
	
	private Date updatedDate;
	
	private OrgPostMst orgPostMstByUpdatedByPost;
	
	

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public CmnBranchlocMpg getCmnBranchlocMpg() {
		return cmnBranchlocMpg;
	}

	public void setCmnBranchlocMpg(CmnBranchlocMpg cmnBranchlocMpg) {
		this.cmnBranchlocMpg = cmnBranchlocMpg;
	}

	public CmnLookupMst getCmnLookupMst() {
		return cmnLookupMst;
	}

	public void setCmnLookupMst(CmnLookupMst cmnLookupMst) {
		this.cmnLookupMst = cmnLookupMst;
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

	public OrgUserMst getOrgUserMstByCreatedBy() {
		return orgUserMstByCreatedBy;
	}

	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy) {
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public OrgPostMst getOrgPostMstByCreatedByPost() {
		return orgPostMstByCreatedByPost;
	}

	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost) {
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}

	public OrgUserMst getOrgUserMstByUpdatedBy() {
		return orgUserMstByUpdatedBy;
	}

	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy) {
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public OrgPostMst getOrgPostMstByUpdatedByPost() {
		return orgPostMstByUpdatedByPost;
	}

	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost) {
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}

	
	
}
