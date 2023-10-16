package com.tcs.sgv.billproc.counter.valueobject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tcs.sgv.common.valueobject.EmpPostVO;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
/** BillVO
 *  This class is use for cutomize bill vo
 * 	Date of Creation : 7th July 2007
 *  Author : Maulik patel
 *  
 *  Revision History 
 *  =====================
 *  Bhavik    23-Oct-2007   For making changes for code formating
 */
public class BillVO {
	OrgEmpMst emp;
	TrnBillRegister tbr;
	
	private long billNo;
	private Long tokenNum;
	private BigDecimal billGrossAmount;
	private long subjectId;
	private Date inwardDt;
	private String ddoNO;
	private String budmjrHd;
	private String tcBill;
	private String billType;
	private String ddoName;
	private List audList;
	private String auditorName[];
	private long auditorNo[];
	private String employeeName;
	private String subjectDesc;
	private Long cardexNo;
	private EmpPostVO empPostVO = null;
	
	private String audName;
	private long audPostId;
	
	private String cardexVerify;
	private String billCntrlNo; 
	private String lookupName;
	private String ddoCode;
	private int objCount;
	private String exempted;
	private Long phyBill;
	private BigDecimal billNetAmount;
	private BigDecimal audNetAmount;
	private int refNum;
	
	public OrgEmpMst getEmp() {
		return emp;
	}

	public void setEmp(OrgEmpMst emp) {
		this.emp = emp;
	}

	public Long getPhyBill() {
		return phyBill;
	}

	public void setPhyBill(Long phyBill) {
		this.phyBill = phyBill;
	}

	public TrnBillRegister getTbr() {
		return tbr;
	}

	public void setTbr(TrnBillRegister tbr) {
		this.tbr = tbr;
	}

	public String getLookupName() {
		return lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}

	public String getCardexVerify() {
		return cardexVerify;
	}

	public void setCardexVerify(String cardexVerify) {
		this.cardexVerify = cardexVerify;
	}

	

	public String getAudName() {
		return audName;
	}

	public void setAudName(String audName) {
		this.audName = audName;
	}

	public String getSubjectDesc()
	{
		return subjectDesc;
	}
	
	public void setSubjectDesc(String subjectDesc){
		this.subjectDesc=subjectDesc;
	}


	
	

	public Long getCardexNo() {
		return cardexNo;
	}

	public void setCardexNo(Long cardexNo) {
		this.cardexNo = cardexNo;
	}

	public String getEmployeeName()
	{
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName){
		this.employeeName=employeeName;
	}

	
	public String[] getAuditorNo()
	{
		return auditorName;
	}
	
	public void setAuditorNo(long auditorNo[]){
		this.auditorNo=auditorNo;
	}

	public String[] getAuditorName()
	{
		return auditorName;
	}
	
	public void setAuditorName(String auditorName[]){
		this.auditorName=auditorName;
	}
	
	public String getDdoName()
	{
		return ddoName;
	}
	
	public void setDdoName(String ddoName){
		this.ddoName=ddoName;
	}

	
	public String getBillType()
	{
		return billType;
	}
	
	public void setBillType(String billType){
		this.billType=billType;
	}

	public long getBillNo(){
		return billNo;
	}
	public void setBillNo(long billNo){
		this.billNo=billNo;
	}
	
	public long getTokenNum(){
		return tokenNum;
	}
	public void setTokenNum(Long tokenNum){
		this.tokenNum=tokenNum;
	}
	
	public BigDecimal getBillGrossAmount() {
		return this.billGrossAmount;
	}

	public void setBillGrossAmount(BigDecimal billGrossAmount) {
		this.billGrossAmount = billGrossAmount;
	}
	
	public long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public Date getInwardDt() {
		return this.inwardDt;
	}

	public void setInwardDt(Date inwardDt) {
		this.inwardDt = inwardDt;
	}

	
	
	public String getBudmjrHd() {
		return this.budmjrHd;
	}

	public void setBudmjrHd(String budmjrHd) {
		this.budmjrHd = budmjrHd;
	}

	public String getTcBill() {
		return this.tcBill;
	}

	public void setTcBill(String tcBill) {
		this.tcBill = tcBill;
	}

	
	/*public OrgEmpMst getEmp() {
		return emp;
	}

	public void setEmp(OrgEmpMst emp) {
		this.emp = emp;
	}*/

	public List getAudList() {
		return audList;
	}

	public void setAudList(List audList) {
		this.audList = audList;
	}

	public EmpPostVO getEmpPostVO() {
		return empPostVO;
	}

	public void setEmpPostVO(EmpPostVO empPostVO) {
		this.empPostVO = empPostVO;
	}

	
	public long getAudPostId() {
		return audPostId;
	}

	public void setAudPostId(long audPostId) {
		this.audPostId = audPostId;
	}

	public String getBillCntrlNo() {
		return billCntrlNo;
	}

	public void setBillCntrlNo(String billCntrlNo) {
		this.billCntrlNo = billCntrlNo;
	}

	public String getDdoCode() {
		return ddoCode;
	}

	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}

	public String getDdoNO() {
		return ddoNO;
	}

	public void setDdoNO(String ddoNO) {
		this.ddoNO = ddoNO;
	}

	public int getObjCount() {
		return objCount;
	}

	public void setObjCount(int objCount) {
		this.objCount = objCount;
	}

	public String getExempted() {
		return exempted;
	}

	public void setExempted(String exempted) {
		this.exempted = exempted;
	}
	
	public BigDecimal getBillNetAmount() {
		return this.billNetAmount;
	}

	public void setBillNetAmount(BigDecimal billNetAmount) {
		this.billNetAmount = billNetAmount;
	}
	
	public BigDecimal getAudNetAmount() {
		return this.audNetAmount;
	}

	public void setAudNetAmount(BigDecimal audNetAmount) {
		this.audNetAmount = audNetAmount;
	}

	public int getRefNum() {
		return refNum;
	}

	public void setRefNum(int refNum) {
		this.refNum = refNum;
	}
}
