package com.tcs.sgv.eis.valueobject;

import java.io.Serializable;
import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class OrgSchemeMstVO implements Serializable 
{


	// Fields    

	private long schemeId;

	private SgvaBudsubhdMst sgvaBudsubhdMst;
	
    private long schemeCode;
    
    private String schemeName;
    
	private OrgUserMst orgUserMstByCreatedBy;
	
	private Date createdDate;
	
	private OrgPostMst orgPostMstByCreatedByPost;
	
	private OrgUserMst orgUserMstByUpdatedBy;
	
	private Date updatedDate;
	
	private OrgPostMst orgPostMstByUpdatedByPost;
	
    private CmnLanguageMst cmnLanguageMst;
    
    private CmnLocationMst cmnLocationMst;
    
    private CmnLookupMst cmnLookupMstForCharge;
    
    private CmnLookupMst cmnLookupMstForPlan;

	// Constructors

	/** default constructor */
	public OrgSchemeMstVO()
	{
		
	}

	/** full constructor */
	public OrgSchemeMstVO(final long schemeId, final SgvaBudsubhdMst sgvaBudsubhdMst)
	{
		this.schemeId = schemeId;
		this.sgvaBudsubhdMst = sgvaBudsubhdMst;
		
	}

	
	//Getters and Setters
	
	public long getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(long schemeId) {
		this.schemeId = schemeId;
	}

	public SgvaBudsubhdMst getSgvaBudsubhdMst() {
		return sgvaBudsubhdMst;
	}

	public void setSgvaBudsubhdMst(SgvaBudsubhdMst sgvaBudsubhdMst) {
		this.sgvaBudsubhdMst = sgvaBudsubhdMst;
	}

	public long getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(long schemeCode) {
		this.schemeCode = schemeCode;
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

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public CmnLookupMst getCmnLookupMstForCharge() {
		return cmnLookupMstForCharge;
	}

	public void setCmnLookupMstForCharge(CmnLookupMst cmnLookupMstForCharge) {
		this.cmnLookupMstForCharge = cmnLookupMstForCharge;
	}

	public CmnLookupMst getCmnLookupMstForPlan() {
		return cmnLookupMstForPlan;
	}

	public void setCmnLookupMstForPlan(CmnLookupMst cmnLookupMstForPlan) {
		this.cmnLookupMstForPlan = cmnLookupMstForPlan;
	}

	
	
	
	
	
	
	
	
}
