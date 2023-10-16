package com.tcs.sgv.eis.valueobject;

public class ChangePostOfficeCustomVO
{
	private String empFullName;
	private long userId;
	private long postId;

	public String getEmpFullName()
	{
		return empFullName;
	}

	public void setEmpFullName(String empFullName)
	{
		this.empFullName = empFullName;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public long getPostId()
	{
		return postId;
	}

	public void setPostId(long postId)
	{
		this.postId = postId;
	}
}
