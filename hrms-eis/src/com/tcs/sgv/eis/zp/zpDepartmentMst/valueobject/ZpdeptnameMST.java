package com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject;



public class ZpdeptnameMST implements java.io.Serializable {

	private Long Id;
	private String deptCode;
      private String deptName;
	public Long getId()
	{
		return Id;
	}
	public void setId(Long id)
	{
		Id = id;
	}
	public String getDeptCode()
	{
		return deptCode;
	}
	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
	}
	public String getDeptName()
	{
		return deptName;
	}
	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}
	
	
}
