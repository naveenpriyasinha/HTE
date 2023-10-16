package com.tcs.sgv.eis.valueobject;

public class NetMismatchCustomVO
{
	public String empName = "";
	public long empId;
	
	public NetMismatchCustomVO()
	{
	}
	
	public NetMismatchCustomVO(String empName,long empId)
	{
		this.empId = empId;
		this.empName = empName;
	}
	
	
	/**
	 * @return the empName
	 */
	public String getEmpName()
	{
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	/**
	 * @return the empId
	 */
	public long getEmpId()
	{
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(long empId)
	{
		this.empId = empId;
	}

}
