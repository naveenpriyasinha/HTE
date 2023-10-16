package com.tcs.sgv.onlinebillprep.valueobject;

import java.util.Date;

public class HstTrnAprvdRqst implements java.io.Serializable {

	// Fields    

	private HstTrnAprvdRqstId id;

	private Long aprvdReqId;

	private Date reqAprvdDt;

	private String empDeptId;

	private String empNameEn;

	private String empNameGu;

	private String empDesgnEn;

	private String empDesgnGu;

	private String isBillCrtd;

	private Long updatedUserId;

	private Long updatedPostId;

	private Date updatedDate;

	private String empType;

	private Long billTypeId;

	private Long attachmentId;

	private String empId;

	// Constructors

	/** default constructor */
	public HstTrnAprvdRqst() {
	}

	/** minimal constructor */
	public HstTrnAprvdRqst(HstTrnAprvdRqstId id, Long aprvdReqId,
			Date reqAprvdDt, String empDeptId, String isBillCrtd) {
		this.id = id;
		this.aprvdReqId = aprvdReqId;
		this.reqAprvdDt = reqAprvdDt;
		this.empDeptId = empDeptId;
		this.isBillCrtd = isBillCrtd;
	}

	/** full constructor */
	public HstTrnAprvdRqst(HstTrnAprvdRqstId id, Long aprvdReqId,
			Date reqAprvdDt, String empDeptId, String empNameEn,
			String empNameGu, String empDesgnEn, String empDesgnGu,
			String isBillCrtd, Long updatedUserId,
			Long updatedPostId, Date updatedDate, String empType,
			Long billTypeId, Long attachmentId, String empId) {
		this.id = id;
		this.aprvdReqId = aprvdReqId;
		this.reqAprvdDt = reqAprvdDt;
		this.empDeptId = empDeptId;
		this.empNameEn = empNameEn;
		this.empNameGu = empNameGu;
		this.empDesgnEn = empDesgnEn;
		this.empDesgnGu = empDesgnGu;
		this.isBillCrtd = isBillCrtd;
		this.updatedUserId = updatedUserId;
		this.updatedPostId = updatedPostId;
		this.updatedDate = updatedDate;
		this.empType = empType;
		this.billTypeId = billTypeId;
		this.attachmentId = attachmentId;
		this.empId = empId;
	}

	// Property accessors
	public HstTrnAprvdRqstId getId() 
	{
		return this.id;
	}

	public void setId(HstTrnAprvdRqstId id) 
	{
		this.id = id;
	}

	public Long getAprvdReqId() 
	{
		return this.aprvdReqId;
	}

	public void setAprvdReqId(Long aprvdReqId) 
	{
		this.aprvdReqId = aprvdReqId;
	}

	public Date getReqAprvdDt() 
	{
		return this.reqAprvdDt;
	}

	public void setReqAprvdDt(Date reqAprvdDt) 
	{
		this.reqAprvdDt = reqAprvdDt;
	}

	public String getEmpDeptId() {
		return this.empDeptId;
	}

	public void setEmpDeptId(String empDeptId) 
	{
		this.empDeptId = empDeptId;
	}

	public String getEmpNameEn() 
	{
		return this.empNameEn;
	}

	public void setEmpNameEn(String empNameEn) 
	{
		this.empNameEn = empNameEn;
	}

	public String getEmpNameGu() 
	{
		return this.empNameGu;
	}

	public void setEmpNameGu(String empNameGu) 
	{
		this.empNameGu = empNameGu;
	}

	public String getEmpDesgnEn()
	{
		return this.empDesgnEn;
	}

	public void setEmpDesgnEn(String empDesgnEn)
	{
		this.empDesgnEn = empDesgnEn;
	}

	public String getEmpDesgnGu()
	{
		return this.empDesgnGu;
	}

	public void setEmpDesgnGu(String empDesgnGu)
	{
		this.empDesgnGu = empDesgnGu;
	}

	public String getIsBillCrtd() 
	{
		return this.isBillCrtd;
	}

	public void setIsBillCrtd(String isBillCrtd)
	{
		this.isBillCrtd = isBillCrtd;
	}

	public Long getUpdatedUserId()
	{
		return this.updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId)
{
		this.updatedUserId = updatedUserId;
	}

	public Long getUpdatedPostId() 
	{
		return this.updatedPostId;
	}

	public void setUpdatedPostId(Long updatedPostId)
{
		this.updatedPostId = updatedPostId;
	}

	public Date getUpdatedDate()
	{
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) 
	{
		this.updatedDate = updatedDate;
	}

	public String getEmpType()
	{
		return this.empType;
	}

	public void setEmpType(String empType) 
	{
		this.empType = empType;
	}

	public Long getBillTypeId() 
	{
		return this.billTypeId;
	}

	public void setBillTypeId(Long billTypeId)
	{
		this.billTypeId = billTypeId;
	}

	public Long getAttachmentId()
	{
		return this.attachmentId;
	}

	public void setAttachmentId(Long attachmentId)
	{
		this.attachmentId = attachmentId;
	}

	public String getEmpId()
	{
		return this.empId;
	}

	public void setEmpId(String empId)
	{
		this.empId = empId;
	}

}
