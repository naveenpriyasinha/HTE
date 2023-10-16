/**
 * 
 */
package com.tcs.sgv.billproc.common.valueobject;

import java.util.List;

/**
 * 
 * This is Previous auditor's objection customize vo
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History 
 * ===================== 
 * Bhavik 23-Oct-2007 Made changes for  code formatting
 */
public class PrevAdtObjections implements java.io.Serializable
{
	private String empName;
	private List remarks;
	private List objections;
	
	public String getEmpName()
	{		
		return empName;
	}	
	public void setEmpName(String empName)
	{		
		this.empName=empName;
	}
	
	public List getRemarks()
	{		
		return remarks;
	}	
	public void setRemarks(List remarks)
	{		
		this.remarks=remarks;
	}
	
	public List getObjections()
	{		
		return objections;
	}	
	public void setObjections(List objections)
	{		
		this.objections=objections;
	}
	
	
}
