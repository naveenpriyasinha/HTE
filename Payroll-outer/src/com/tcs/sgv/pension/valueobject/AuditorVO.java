package com.tcs.sgv.pension.valueobject;

import java.util.List;

public class AuditorVO {
	private String ppoNo;
	private String Name;
	private String bank;
	private String branch;
	private String accNo;
	private List auditorList;
	private String pensionerCode;
	private Long rqstHdrId;
	public AuditorVO()
	{}
	
	public AuditorVO(String ppoNo,String Name,String bank,String branch,String accNo,List auditorList,Long rqstHdrId,String pensionerCode)
	{
		this.ppoNo = ppoNo;
		this.Name = Name;
		this.bank = bank;
		this.branch = branch;
		this.accNo = accNo;
		this.auditorList = auditorList;
		this.rqstHdrId = rqstHdrId;
		this.pensionerCode = pensionerCode;
	}
	public void setRqstHdrId(Long rqstHdrId)
	{
		this.rqstHdrId = rqstHdrId;
	}
	public Long getRqstHdrId()
	{
		return this.rqstHdrId;
	}
	public String getPensionerCode()
	{
		return this.pensionerCode;
	}
	public void setPensionerCode(String pensionerCode)
	{
		this.pensionerCode = pensionerCode;
	}
	public void setPpoNo(String ppoNo) {
		this.ppoNo = ppoNo;
	}

	public String getPpoNo()
	{
		return this.ppoNo;
	}
	
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBank()
	{
		return this.bank;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranch()
	{
		return this.branch;
	}
	
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccNo()
	{
		return this.accNo;
	}
	
	public void setAuditorList(List auditorList)
	{
		this.auditorList = auditorList;
	}
	public List getAuditorList()
	{
		return this.auditorList;
	}
}
