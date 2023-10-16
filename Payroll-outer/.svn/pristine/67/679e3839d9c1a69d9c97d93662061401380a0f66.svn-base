package com.tcs.sgv.billproc.cheque.valueobject;

import java.math.BigDecimal;
import java.util.Date;
/**
 *  
 *  com.tcs.sgv.billproc.cheque.valueobject.LogicChequeVO
 *  
 *  This is Java bean  to use for single and multiple cheque preparation logic
 *  
 * 	Date of Creation : 10th July 2007
 * 
 *  Author : Vidhya Mashru 
 *  
 *  Revision History 
 *  =====================
 *  Vidhya M    23-Oct-2007  Made changes for code formatting
 */
public class LogicChequeVO implements java.io.Serializable
{
	private BigDecimal chequeAmt;

	private String fromDt;

	private Date toDt;

	private String partyName;

	private String status;
	
	private String chequeType;
	
	private String groupId;
	
	private long billNo;
	
	private long tokenNo;
	
	private int finYrId;
	
	private String cheqType;
	
	private String partyAddr;

	private String accntNum;
	
	private String partyCode;

	public int getFinYrId() {
		return finYrId;
	}

	public void setFinYrId(int finYrId) {
		this.finYrId = finYrId;
	}

	public long getBillNo() {
		return billNo;
	}

	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}

	public BigDecimal getChequeAmt() {
		return chequeAmt;
	}

	public void setChequeAmt(BigDecimal chequeAmt) {
		this.chequeAmt = chequeAmt;
	}

	public String getChequeType() {
		return chequeType;
	}

	public void setChequeType(String chequeType) {
		this.chequeType = chequeType;
	}

	public String getFromDt() {
		return fromDt;
	}

	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getToDt() {
		return toDt;
	}

	public void setToDt(Date toDt) {
		this.toDt = toDt;
	}

	public long getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(long tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getCheqType() {
		return cheqType;
	}

	public void setCheqType(String cheqType) {
		this.cheqType = cheqType;
	}
	
	public LogicChequeVO retNewVO(LogicChequeVO oldLogic)
	{
		LogicChequeVO logic =  new LogicChequeVO();
		logic.setBillNo(oldLogic.getBillNo());
		logic.setCheqType(oldLogic.getCheqType());
		logic.setChequeAmt(oldLogic.getChequeAmt());
		logic.setChequeType(oldLogic.getChequeType());
		logic.setFinYrId(oldLogic.getFinYrId());
		logic.setFromDt(oldLogic.getFromDt());
		logic.setGroupId(oldLogic.getGroupId());
		logic.setPartyName(oldLogic.getPartyName());
		logic.setStatus(oldLogic.getStatus());
		logic.setToDt(oldLogic.getToDt());
		logic.setTokenNo(oldLogic.getTokenNo());
		
		return logic;
	}

	public String getPartyAddr() {
		return partyAddr;
	}

	public void setPartyAddr(String partyAddr) {
		this.partyAddr = partyAddr;
	}

	public String getAccntNum() {
		return accntNum;
	}

	public void setAccntNum(String accntNum) {
		this.accntNum = accntNum;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
		
}
