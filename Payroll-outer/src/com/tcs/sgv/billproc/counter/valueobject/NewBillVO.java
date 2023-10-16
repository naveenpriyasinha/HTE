package com.tcs.sgv.billproc.counter.valueobject;

import java.util.List;
/** NewBillVO
 *  This class is use for cutomize bill vo for save and new
 * 	Date of Creation : 7th July 2007
 *  Author : hiral
 *  
 *  Revision History 
 *  =====================
 *  Bhavik    23-Oct-2007   For making changes for code formating
 */
public class NewBillVO implements java.io.Serializable
{
		private String cardexNo;
		private String ddoNo;
		private String ddoName;
		private String department;
		private String deptId;
		private String ddoCode;
		
		private String billDate;
		private String billType;
		private String billGrossAmount;
		private String billNetAmount;
		private String goNgo;
		private String tcBill;
		private String exempted;
		private String billCode;
		
		private String dmndNo;
		private String budmjrHd;
		private String budSubMjrHd;		
		private String budMinHd;
		private String budSubHd;
		private String budDetailHd;
		private String budType;
		private String grantAmt;
		private String schemeCode;
		
		private String prevBillNo;
		private List listReceipt;
/*		private String challanNo[];
		private String challanDate[];
		private String challanMjrHd[];
		private String challanAmt[];
*/
		private String auditorPostId;
		private String remarks;
		private String attachId;	
		private String budTypeDesc;
		private String tokenNum;
		private List audList;
		
    private String locationCode;
		
		public String getCardexNo() 
		{
			return cardexNo;
		}
		public void setCardexNo(String cardexNo) 
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
		
		public String getDeptId()
		{
			return deptId;
		}
		public void setDeptId(String deptId)
		{
			this.deptId=deptId;
		}
		
		public String getBillDate() 
		{
			return this.billDate;
		}
		public void setBillDate(String billDate) 
		{
			this.billDate = billDate;
		}
		
		public String getBillType()
		{
			return billType;
		}	
		public void setBillType(String billType)
		{
			this.billType=billType;
		}
		
		public String getBillGrossAmount() 
		{
			return this.billGrossAmount;
		}
		public void setBillGrossAmount(String billGrossAmount) 
		{
			this.billGrossAmount = billGrossAmount;
		}

		public String getBillNetAmount() 
		{
			return this.billNetAmount;
		}
		public void setBillNetAmount(String billNetAmount) 
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
		
/*		public String[] getChallanNo()
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
		
		public String[] getChallanDate()
		{
			return this.challanDate;
		}
		public void setChallanDate(String challanDate[]) 
		{
			this.challanDate = challanDate;
		}

		public String[] getChallanAmt() 
		{
			return challanAmt;
		}
		public void setChallanAmt(String challanAmt[]) 
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
		
		public String getRemarks()
		{		
			return remarks;
		}	
		public void setRemarks(String remarks)
		{		
			this.remarks=remarks;
		}
		
		public String getGoNgo()
		{
			return this.goNgo;
		}
		public void setGoNgo(String goNgo) {
			this.goNgo = goNgo;
		}
				
		public String getAttachId() 
		{
			return attachId;
		}
		public void setAttachId(String attachId) 
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
		
		public String getTokenNum() 
		{
			return tokenNum;
		}
		public void setTokenNum(String tokenNum) 
		{
			this.tokenNum = tokenNum;
		}
		
		public List getListReceipt()
		{
			return this.listReceipt;
		}
		public void setListReceipt(List listReceipt)
		{
			this.listReceipt = listReceipt;
			
		}
	    public String getLocationCode()
	    {
	      return locationCode;
	    }
	    public void setLocationCode(String locationCode)
	    {
	      this.locationCode = locationCode;
	    }
	    
	    public List getAudList()
	    {
	      return audList;
	    }
	    public void setAudList(List audList)
	    {
	      this.audList = audList;
	    }
}

