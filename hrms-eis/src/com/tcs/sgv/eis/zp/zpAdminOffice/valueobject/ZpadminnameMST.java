package com.tcs.sgv.eis.zp.zpAdminOffice.valueobject;


public class ZpadminnameMST implements java.io.Serializable {

	private Long Id;
	private String adminCode;
      private String adminName;
	public Long getId()
	{
		return Id;
	}
	public void setId(Long id)
	{
		Id = id;
	}
	public String getAdminCode()
	{
		return adminCode;
	}
	public void setAdminCode(String adminCode)
	{
		this.adminCode = adminCode;
	}
	public String getAdminName()
	{
		return adminName;
	}
	public void setAdminName(String adminName)
	{
		this.adminName = adminName;
	}
	
	

}
