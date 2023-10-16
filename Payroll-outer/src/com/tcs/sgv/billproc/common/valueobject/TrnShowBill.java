/**
 * 
 */
package com.tcs.sgv.billproc.common.valueobject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * This is customize Trn Show Bill vo
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History 
 * ===================== 
 * Bhavik 23-Oct-2007 Made changes for  code formatting
 */

public class TrnShowBill implements java.io.Serializable
{
	private Long billNo;
	private Long cardexNo;
	private String ddoNo;
	private String ddoName;
	private String department;
	
	private String deptCode;
	private String ddoCode;	
	private String billCntrlNo;
	private String referenceNo;
	private Long tokenNum;
	
	private Date billDate;
	private Long billType;
	private BigDecimal billGrossAmount;
	private BigDecimal billNetAmount;
	private BigDecimal auditorNetAmount;
	
	private String budmjrHd;
	private String budSubMjrHd;
	private String dmndNo;
	private String budMinHd;
	private String budSubHd;
	
	private String budDetailHd;
	private String budType;	
	private String grantAmt;	
	private String goNgo;
	private String tcBill;
	
	private String prevBillNo;
	private List listReceipt;
/*	private String challanNo[];
	private Date challanDate[];
	private String challanMjrHd[];
	private BigDecimal challanAmt[];
*/	
	private String exempted;
	private String billCode;
	private String auditorPostId;
	private String remarks[];
	private String userName[];
	private String billTypeCode;
	
	private String lastRemark;
	private String status;
	private Long attachId;
	private String objections;
	private String schemeCode;
	private String budTypeDesc;
	private int objCount;
	
	public long getBillNo()
	{
		return billNo;
	}
	public void setBillNo(long billNo)
	{
		this.billNo=billNo;
	}
	
	public Long getCardexNo() 
	{
		return cardexNo;
	}
	public void setCardexNo(Long cardexNo) 
	{
		this.cardexNo = cardexNo;
	}

	public void setDdoNo(String ddoNo) 
	{
		this.ddoNo = ddoNo;
	}
	public String getDdoNo() 
	{
		return ddoNo;
	}
	
	public void setDdoCode(String ddoCode) 
	{
		this.ddoCode = ddoCode;
	}
	public String getDdoCode() 
	{
		return ddoCode;
	}
	
	public String getDdoName()
	{
		return ddoName;
	}
	public void setDdoName(String ddoName)
	{
		this.ddoName=ddoName;
	}
	
	public String getDepartment()
	{
		return department;
	}
	public void setDepartment(String department)
	{
		this.department=department;
	}
	
	public String getDeptCode()
	{
		return deptCode;
	}
	public void setDeptCode(String deptCode)
	{
		this.deptCode=deptCode;
	}
	
	public String getBillCntrlNo()
	{
		return billCntrlNo;
	}
	public void setBillCntrlNo(String billCntrlNo)
	{
		this.billCntrlNo=billCntrlNo;
	}
	
	public long getTokenNum()	
	{
		return tokenNum;
	}
	public void setTokenNum(Long tokenNum)
	{
		this.tokenNum=tokenNum;
	}
	
	public Date getBillDate() 
	{
		return this.billDate;
	}
	public void setBillDate(Date billDate) 
	{
		this.billDate = billDate;
	}
	
	public Long getBillType()
	{
		return billType;
	}	
	public void setBillType(Long billType)
	{
		this.billType=billType;
	}
	
	public BigDecimal getBillGrossAmount() 
	{
		return this.billGrossAmount;
	}
	public void setBillGrossAmount(BigDecimal billGrossAmount) 
	{
		this.billGrossAmount = billGrossAmount;
	}

	public BigDecimal getBillNetAmount() 
	{
		return this.billNetAmount;
	}
	public void setBillNetAmount(BigDecimal billNetAmount) 
	{
		this.billNetAmount = billNetAmount;
	}
	
	public String getBudmjrHd() 
	{
		return this.budmjrHd;
	}
	public void setBudmjrHd(String budmjrHd) 
	{
		this.budmjrHd = budmjrHd;
	}
	
	public String getBudSubMjrHd() 
	{
		return this.budSubMjrHd;
	}
	public void setBudSubMjrHd(String budSubMjrHd) 
	{
		this.budSubMjrHd = budSubMjrHd;
	}
	
	public String getDmndNo() 
	{
		return this.dmndNo;
	}
	public void setDmndNo(String dmndNo) 
	{
		this.dmndNo = dmndNo;
	}
	
	public String getBudMinHd() 
	{
		return this.budMinHd;
	}
	public void setBudMinHd(String budMinHd) 
	{
		this.budMinHd = budMinHd;
	}
	
	public String getBudSubHd() 
	{
		return this.budSubHd;
	}
	public void setBudSubHd(String budSubHd) 
	{
		this.budSubHd = budSubHd;
	}
	
	public String getBudType() 
	{
		return this.budType;
	}
	public void setBudType(String budType) 
	{
		this.budType = budType;
	}	
	
	public String getGrantAmt() 
	{
		return this.grantAmt;
	}
	public void setGrantAmt(String grantAmt) 
	{
		this.grantAmt = grantAmt;
	}	
	
	public String getTcBill() 
	{
		return this.tcBill;
	}
	public void setTcBill(String tcBill) 
	{
		this.tcBill = tcBill;
	}

	public String getPrevBillNo() 
	{
		return this.prevBillNo;
	}
	public void setPrevBillNo(String prevBillNo) 
	{
		this.prevBillNo = prevBillNo;
	}
	
/*	public String[] getChallanNo()
	{
		return this.challanNo;
	}
	public void setChallanNo(String challanNo[]) 
	{
		this.challanNo = challanNo;
	}
	
	public String[] getChallanMjrHd()
	{
		return this.challanMjrHd;
	}
	public void setChallanMjrHd(String challanMjrHd[]) 
	{
		this.challanMjrHd = challanMjrHd;
	}
	
	public Date[] getChallanDate()
	{
		return this.challanDate;
	}
	public void setChallanDate(Date challanDate[]) 
	{
		this.challanDate = challanDate;
	}
	
	public BigDecimal[] getChallanAmt() 
	{
		return this.challanAmt;
	}
	public void setChallanAmt(BigDecimal challanAmt[]) 
	{
		this.challanAmt = challanAmt;
	}
*/
	public String getExempted() 
	{
		return this.exempted;
	}	
	public void setExempted(String exempted) 
	{
		this.exempted = exempted;
	}

	public String getBillCode() 
	{
		return this.billCode;
	}
	public void setBillCode(String billCode) 
	{
		this.billCode = billCode;
	}
	
	public String getAuditorPostId()
	{
		return auditorPostId;
	}	
	public void setAuditorPostId(String auditorPostId)
	{
		this.auditorPostId=auditorPostId;
	}
	
	public String[] getRemarks()
	{		
		return remarks;
	}	
	public void setRemarks(String remarks[])
	{		
		this.remarks=remarks;
	}
	
	public String getGoNgo() {
		return this.goNgo;
	}
	public void setGoNgo(String goNgo) {
		this.goNgo = goNgo;
	}
	
	public String[] getUserName()
	{		
		return userName;
	}	
	public void setUserName(String userName[])
	{		
		this.userName=userName;
	}
	public String getLastRemark()
	{
		return lastRemark;
	}	
	public void setLastRemark(String lastRemark)
	{
		this.lastRemark=lastRemark;
	}
	
	public String getStatus() 
	{
		return this.status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public Long getAttachId() 
	{
		return attachId;
	}
	public void setAttachId(Long attachId) 
	{
		this.attachId = attachId;
	}
	
	public String getSchemeCode() 
	{
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) 
	{
		this.schemeCode = schemeCode;
	}
	
	public String getObjections()
	{
		return objections;
	}
	public void setObjections(String objections)
	{
		this.objections = objections;
	}
	
	public String getBudTypeDesc()
	{
		return budTypeDesc;
	}
	public void setBudTypeDesc(String budTypeDesc)
	{
		this.budTypeDesc = budTypeDesc;
	}
	
	public String getBudDetailHd() 
	{
		return budDetailHd;
	}
	public void setBudDetailHd(String budDetailHd) 
	{
		this.budDetailHd = budDetailHd;
	}
	
	public String getReferenceNo() 
	{
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) 
	{
		this.referenceNo = referenceNo;
	}
	
	public BigDecimal getAuditorNetAmount() 
	{
		return this.auditorNetAmount;
	}
	public void setAuditorNetAmount(BigDecimal auditorNetAmount) 
	{
		this.auditorNetAmount = auditorNetAmount;
	}
	
	public List getListReceipt()
	{
		return this.listReceipt;
	}
	public void setListReceipt(List listReceipt)
	{
		this.listReceipt = listReceipt;
		
	}
	public int getObjCount() {
		return objCount;
	}
	public void setObjCount(int objCount) {
		this.objCount = objCount;
	}
/*	For Bill Type code to show on Jsp(bill in editable mode)*/	
	public String getBillTypeCode() 
	{
		return billTypeCode;
	}
	public void setBillTypeCode(String billTypeCode) 
	{
		this.billTypeCode = billTypeCode;
	}
/* Ends :	For Bill Type code to show on Jsp(bill in editable mode)*/	
}
