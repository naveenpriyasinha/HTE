package com.tcs.sgv.exprcpt.valueobject;

import java.math.BigDecimal;
import java.util.Date;

public class ChequeDetailVO implements java.io.Serializable{
	
	
	private Long chequeNo;
	private Date issueDate;
	private Date clearedDate;
	private BigDecimal chequeAmt;
	private BigDecimal scrollAmt;
	
	
	public BigDecimal getScrollAmt() {
		return scrollAmt;
	}
	public void setScrollAmt(BigDecimal scrollAmt) {
		this.scrollAmt = scrollAmt;
	}
	public BigDecimal getChequeAmt() {
		return chequeAmt;
	}
	public void setChequeAmt(BigDecimal chequeAmt) {
		this.chequeAmt = chequeAmt;
	}
	public Long getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(Long chequeNo) {
		this.chequeNo = chequeNo;
	}
	public Date getClearedDate() {
		return clearedDate;
	}
	public void setClearedDate(Date clearedDate) {
		this.clearedDate = clearedDate;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	

	

	

	
	
	
	
	
	
}
