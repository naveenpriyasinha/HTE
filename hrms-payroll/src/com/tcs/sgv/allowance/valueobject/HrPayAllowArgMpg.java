package com.tcs.sgv.allowance.valueobject;



import java.util.Date;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
* HrPayAllowTypeMst generated by hbm2java
*/
public class HrPayAllowArgMpg implements java.io.Serializable {

	// Fields    

	private long allowArgueId;

	private HrPayAllowTypeMst hrPayAllowTypeMst;
	
	private HrPayArgumentMst hrPayArgumentMst;
	
	private HrPayArgumentMst hrPayArgumentMst1;

	private String methodName;
	
	private long arguOrder;

	private CmnDatabaseMst cmnDatabaseMst;

	private OrgPostMst orgPostMstByUpdatedByPost;

	private OrgPostMst orgPostMstByCreatedByPost;

	private CmnLocationMst cmnLocationMst;

	private OrgUserMst orgUserMstByUpdatedBy;

	private OrgUserMst orgUserMstByCreatedBy;

	private Date createdDate;
	
	private Date updatedDate;

	private long elementCode;

	// Constructors

	/** default constructor */
	public HrPayAllowArgMpg() {
	}

	/** minimal constructor */
	public HrPayAllowArgMpg(long allowArgueId,String methodName,
			 CmnDatabaseMst cmnDatabaseMst ,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByCreatedBy, OrgPostMst orgPostMstByCreatedByPost,
			Date createdDate,long elementCode ) {
		this.allowArgueId = allowArgueId;
		this.methodName = methodName;
		
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.elementCode = elementCode;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public HrPayAllowArgMpg(long allowArgueId, String methodName,
			String allowDesc, CmnDatabaseMst cmnDatabaseMst ,
			CmnLocationMst cmnLocationMst, OrgUserMst orgUserMstByCreatedBy, OrgPostMst orgPostMstByCreatedByPost,
			Date createdDate, OrgUserMst orgUserMstByUpdatedBy, OrgPostMst orgPostMstByUpdatedByPost,
			Date updatedDate, long elementCode) {
		this.allowArgueId = allowArgueId;
		this.methodName = methodName;
		this.cmnDatabaseMst = cmnDatabaseMst;
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
		this.cmnLocationMst = cmnLocationMst;
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
		this.updatedDate = updatedDate;
		this.elementCode = elementCode;
		this.createdDate = createdDate;
	}

	public long getAllowArgueId() {
		return allowArgueId;
	}

	public void setAllowArgueId(long allowArgueId) {
		this.allowArgueId = allowArgueId;
	}

	public long getArguOrder() {
		return arguOrder;
	}

	public void setArguOrder(long arguOrder) {
		this.arguOrder = arguOrder;
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

	public long getElementCode() {
		return elementCode;
	}

	public void setElementCode(long elementCode) {
		this.elementCode = elementCode;
	}

	public HrPayAllowTypeMst getHrPayAllowTypeMst() {
		return hrPayAllowTypeMst;
	}

	public void setHrPayAllowTypeMst(HrPayAllowTypeMst hrPayAllowTypeMst) {
		this.hrPayAllowTypeMst = hrPayAllowTypeMst;
	}

	public HrPayArgumentMst getHrPayArgumentMst() {
		return hrPayArgumentMst;
	}

	public void setHrPayArgumentMst(HrPayArgumentMst hrPayArgumentMst) {
		this.hrPayArgumentMst = hrPayArgumentMst;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public HrPayArgumentMst getHrPayArgumentMst1() {
		return hrPayArgumentMst1;
	}

	public void setHrPayArgumentMst1(HrPayArgumentMst hrPayArgumentMst1) {
		this.hrPayArgumentMst1 = hrPayArgumentMst1;
	}

	
	// Property accessors
	

}