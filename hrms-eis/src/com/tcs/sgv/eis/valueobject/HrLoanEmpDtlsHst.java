// default package
// Generated Aug 24, 2007 4:07:19 PM by Hibernate Tools 3.2.0.beta8
package com.tcs.sgv.eis.valueobject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * HrLoanEmpDtlsHst generated by hbm2java
 */
public class HrLoanEmpDtlsHst implements java.io.Serializable {

	// Fields    

	private HrLoanEmpDtlsHstId id;
	private HrEisEmpMst hrEisEmpMst;
	private HrLoanAdvMst hrLoanAdvMst;
	private long loanPrinAmt;
	private String currencyloanPrinAmt;
	private String currencyloanInterestAmt;
	private long loanInterestAmt;
	private long loanPrinInstNo;
	private long loanIntInstNo;
	private long loanPrinEmiAmt;
	private long loanIntEmiAmt;
	private String loanAccountNo;
	private Date loanDate;
	private String loanSancOrderNo;
	private Date loanSancOrderdate;
	private Date createdDate;
	private Date updatedDate;
	private String voucherNo;
	private Date voucherDate;
	private CmnDatabaseMst cmnDatabaseMst;
	private OrgPostMst orgPostMstByUpdatedByPost;
	private OrgPostMst orgPostMstByCreatedByPost;
	private CmnLocationMst cmnLocationMst;
	private OrgUserMst orgUserMstByUpdatedBy;
	private OrgUserMst orgUserMstByCreatedBy;
	//private Set hrLoanEmpPrinRecoverDtlses = new HashSet(0);
	//private Set hrLoanEmpIntRecoverDtlses = new HashSet(0);
	public HrLoanEmpDtlsHstId getId()
	{
		return id;
	}
	public void setId(HrLoanEmpDtlsHstId id)
	{
		this.id = id;
	}
	public HrEisEmpMst getHrEisEmpMst()
	{
		return hrEisEmpMst;
	}
	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst)
	{
		this.hrEisEmpMst = hrEisEmpMst;
	}
	public HrLoanAdvMst getHrLoanAdvMst()
	{
		return hrLoanAdvMst;
	}
	public void setHrLoanAdvMst(HrLoanAdvMst hrLoanAdvMst)
	{
		this.hrLoanAdvMst = hrLoanAdvMst;
	}
	public long getLoanPrinAmt()
	{
		return loanPrinAmt;
	}
	public void setLoanPrinAmt(long loanPrinAmt)
	{
		this.loanPrinAmt = loanPrinAmt;
	}
	public String getCurrencyloanPrinAmt()
	{
		return currencyloanPrinAmt;
	}
	public void setCurrencyloanPrinAmt(String currencyloanPrinAmt)
	{
		this.currencyloanPrinAmt = currencyloanPrinAmt;
	}
	public String getCurrencyloanInterestAmt()
	{
		return currencyloanInterestAmt;
	}
	public void setCurrencyloanInterestAmt(String currencyloanInterestAmt)
	{
		this.currencyloanInterestAmt = currencyloanInterestAmt;
	}
	public long getLoanInterestAmt()
	{
		return loanInterestAmt;
	}
	public void setLoanInterestAmt(long loanInterestAmt)
	{
		this.loanInterestAmt = loanInterestAmt;
	}
	public long getLoanPrinInstNo()
	{
		return loanPrinInstNo;
	}
	public void setLoanPrinInstNo(long loanPrinInstNo)
	{
		this.loanPrinInstNo = loanPrinInstNo;
	}
	public long getLoanIntInstNo()
	{
		return loanIntInstNo;
	}
	public void setLoanIntInstNo(long loanIntInstNo)
	{
		this.loanIntInstNo = loanIntInstNo;
	}
	public long getLoanPrinEmiAmt()
	{
		return loanPrinEmiAmt;
	}
	public void setLoanPrinEmiAmt(long loanPrinEmiAmt)
	{
		this.loanPrinEmiAmt = loanPrinEmiAmt;
	}
	public long getLoanIntEmiAmt()
	{
		return loanIntEmiAmt;
	}
	public void setLoanIntEmiAmt(long loanIntEmiAmt)
	{
		this.loanIntEmiAmt = loanIntEmiAmt;
	}
	public String getLoanAccountNo()
	{
		return loanAccountNo;
	}
	public void setLoanAccountNo(String loanAccountNo)
	{
		this.loanAccountNo = loanAccountNo;
	}
	public Date getLoanDate()
	{
		return loanDate;
	}
	public void setLoanDate(Date loanDate)
	{
		this.loanDate = loanDate;
	}
	public String getLoanSancOrderNo()
	{
		return loanSancOrderNo;
	}
	public void setLoanSancOrderNo(String loanSancOrderNo)
	{
		this.loanSancOrderNo = loanSancOrderNo;
	}
	public Date getLoanSancOrderdate()
	{
		return loanSancOrderdate;
	}
	public void setLoanSancOrderdate(Date loanSancOrderdate)
	{
		this.loanSancOrderdate = loanSancOrderdate;
	}
	public Date getCreatedDate()
	{
		return createdDate;
	}
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate()
	{
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	public String getVoucherNo()
	{
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo)
	{
		this.voucherNo = voucherNo;
	}
	public Date getVoucherDate()
	{
		return voucherDate;
	}
	public void setVoucherDate(Date voucherDate)
	{
		this.voucherDate = voucherDate;
	}
	public CmnDatabaseMst getCmnDatabaseMst()
	{
		return cmnDatabaseMst;
	}
	public void setCmnDatabaseMst(CmnDatabaseMst cmnDatabaseMst)
	{
		this.cmnDatabaseMst = cmnDatabaseMst;
	}
	public OrgPostMst getOrgPostMstByUpdatedByPost()
	{
		return orgPostMstByUpdatedByPost;
	}
	public void setOrgPostMstByUpdatedByPost(OrgPostMst orgPostMstByUpdatedByPost)
	{
		this.orgPostMstByUpdatedByPost = orgPostMstByUpdatedByPost;
	}
	public OrgPostMst getOrgPostMstByCreatedByPost()
	{
		return orgPostMstByCreatedByPost;
	}
	public void setOrgPostMstByCreatedByPost(OrgPostMst orgPostMstByCreatedByPost)
	{
		this.orgPostMstByCreatedByPost = orgPostMstByCreatedByPost;
	}
	public CmnLocationMst getCmnLocationMst()
	{
		return cmnLocationMst;
	}
	public void setCmnLocationMst(CmnLocationMst cmnLocationMst)
	{
		this.cmnLocationMst = cmnLocationMst;
	}
	public OrgUserMst getOrgUserMstByUpdatedBy()
	{
		return orgUserMstByUpdatedBy;
	}
	public void setOrgUserMstByUpdatedBy(OrgUserMst orgUserMstByUpdatedBy)
	{
		this.orgUserMstByUpdatedBy = orgUserMstByUpdatedBy;
	}
	public OrgUserMst getOrgUserMstByCreatedBy()
	{
		return orgUserMstByCreatedBy;
	}
	public void setOrgUserMstByCreatedBy(OrgUserMst orgUserMstByCreatedBy)
	{
		this.orgUserMstByCreatedBy = orgUserMstByCreatedBy;
	}
	/*public Set getHrLoanEmpPrinRecoverDtlses()
	{
		return hrLoanEmpPrinRecoverDtlses;
	}
	public void setHrLoanEmpPrinRecoverDtlses(Set hrLoanEmpPrinRecoverDtlses)
	{
		this.hrLoanEmpPrinRecoverDtlses = hrLoanEmpPrinRecoverDtlses;
	}
	public Set getHrLoanEmpIntRecoverDtlses()
	{
		return hrLoanEmpIntRecoverDtlses;
	}
	public void setHrLoanEmpIntRecoverDtlses(Set hrLoanEmpIntRecoverDtlses)
	{
		this.hrLoanEmpIntRecoverDtlses = hrLoanEmpIntRecoverDtlses;
	}*/
	public Integer getLoanActivateFlag()
	{
		return loanActivateFlag;
	}
	public void setLoanActivateFlag(Integer loanActivateFlag)
	{
		this.loanActivateFlag = loanActivateFlag;
	}
	public Integer getIsApproved()
	{
		return isApproved;
	}
	public void setIsApproved(Integer isApproved)
	{
		this.isApproved = isApproved;
	}
	public Long getLoanOddinstno()
	{
		return loanOddinstno;
	}
	public void setLoanOddinstno(Long loanOddinstno)
	{
		this.loanOddinstno = loanOddinstno;
	}
	public Long getLoanOddinstAmt()
	{
		return loanOddinstAmt;
	}
	public void setLoanOddinstAmt(Long loanOddinstAmt)
	{
		this.loanOddinstAmt = loanOddinstAmt;
	}
	public int getMulLoanRecoveryMode()
	{
		return mulLoanRecoveryMode;
	}
	public void setMulLoanRecoveryMode(int mulLoanRecoveryMode)
	{
		this.mulLoanRecoveryMode = mulLoanRecoveryMode;
	}
	public String getMulLoanRecRemarks()
	{
		return mulLoanRecRemarks;
	}
	public void setMulLoanRecRemarks(String mulLoanRecRemarks)
	{
		this.mulLoanRecRemarks = mulLoanRecRemarks;
	}
	public int getMulLoanInstRecvd()
	{
		return mulLoanInstRecvd;
	}
	public void setMulLoanInstRecvd(int mulLoanInstRecvd)
	{
		this.mulLoanInstRecvd = mulLoanInstRecvd;
	}
	public Long getMulLoanAmtRecvd()
	{
		return mulLoanAmtRecvd;
	}
	public void setMulLoanAmtRecvd(Long mulLoanAmtRecvd)
	{
		this.mulLoanAmtRecvd = mulLoanAmtRecvd;
	}
	private Integer loanActivateFlag;
	private Integer isApproved;
	private Long loanOddinstno;
	private Long loanOddinstAmt;
	private int mulLoanRecoveryMode;
	private String mulLoanRecRemarks;
	private int mulLoanInstRecvd;
	private Long mulLoanAmtRecvd;

	
}