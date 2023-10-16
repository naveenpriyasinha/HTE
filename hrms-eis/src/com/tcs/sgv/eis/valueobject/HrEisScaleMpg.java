package com.tcs.sgv.eis.valueobject;

import java.util.Date;

import com.tcs.sgv.common.valueobject.CmnLookupMst;

public class HrEisScaleMpg {

	private Long id;
	private HrEisEmpMst hrEisEmpMst;
	private CmnLookupMst cmnLookupMst;
	private HrEisSgdMpg hrEisSgdMpg;
	private Date wefDate;
	private HrPayRemarksMst hrPayRemarksMst;
	private String order;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public HrEisEmpMst getHrEisEmpMst() {
		return hrEisEmpMst;
	}
	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst) {
		this.hrEisEmpMst = hrEisEmpMst;
	}
	public CmnLookupMst getCmnLookupMst() {
		return cmnLookupMst;
	}
	public void setCmnLookupMst(CmnLookupMst cmnLookupMst) {
		this.cmnLookupMst = cmnLookupMst;
	}
	public HrEisSgdMpg getHrEisSgdMpg() {
		return hrEisSgdMpg;
	}
	public void setHrEisSgdMpg(HrEisSgdMpg hrEisSgdMpg) {
		this.hrEisSgdMpg = hrEisSgdMpg;
	}
	public Date getWefDate() {
		return wefDate;
	}
	public void setWefDate(Date wefDate) {
		this.wefDate = wefDate;
	}
	
	/**
	 * @return the hrPayRemarksMst
	 */
	public HrPayRemarksMst getHrPayRemarksMst() {
		return hrPayRemarksMst;
	}
	/**
	 * @param hrPayRemarksMst the hrPayRemarksMst to set
	 */
	public void setHrPayRemarksMst(HrPayRemarksMst hrPayRemarksMst) {
		this.hrPayRemarksMst = hrPayRemarksMst;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
