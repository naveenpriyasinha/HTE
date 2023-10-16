package com.tcs.sgv.eis.zp.ReportingDDO.valueobject;

public class ConsolidatedBillLvlMpgMst
{

	private Long id;
	private Long billId;
	private Long lvl2PostId;
	private Long lvl3PostId;
	private Long lvl4PostId;
	private Long status;
	private Long level;
	
	public ConsolidatedBillLvlMpgMst(Long id, Long billId, Long lvl2PostId, Long lvl3PostId, Long lvl4PostId, Long status, Long level)
	{
		super();
		this.id = id;
		this.billId = billId;
		this.lvl2PostId = lvl2PostId;
		this.lvl3PostId = lvl3PostId;
		this.lvl4PostId = lvl4PostId;
		this.status = status;
		this.level = level;
	}
	public ConsolidatedBillLvlMpgMst(){
		
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getBillId()
	{
		return billId;
	}
	public void setBillId(Long billId)
	{
		this.billId = billId;
	}
	public Long getLvl2PostId()
	{
		return lvl2PostId;
	}
	public void setLvl2PostId(Long lvl2PostId)
	{
		this.lvl2PostId = lvl2PostId;
	}
	public Long getLvl3PostId()
	{
		return lvl3PostId;
	}
	public void setLvl3PostId(Long lvl3PostId)
	{
		this.lvl3PostId = lvl3PostId;
	}
	public Long getLvl4PostId()
	{
		return lvl4PostId;
	}
	public void setLvl4PostId(Long lvl4PostId)
	{
		this.lvl4PostId = lvl4PostId;
	}
	public Long getStatus()
	{
		return status;
	}
	public void setStatus(Long status)
	{
		this.status = status;
	}
	public Long getLevel()
	{
		return level;
	}
	public void setLevel(Long level)
	{
		this.level = level;
	}
	
}
