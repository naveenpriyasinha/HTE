/**
 * 
 */
package com.tcs.sgv.pension.valueobject;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 239519
 *
 */
public class TrnHeadVouchers {
	private BigDecimal headCode;
	private String headCodeDescription;
	private BigDecimal amount;
	private List monthYears;
	public BigDecimal getHeadCode() {
		return headCode;
	}
	public void setHeadCode(BigDecimal headCode) {
		this.headCode = headCode;
	}
	public String getHeadCodeDescription() {
		return headCodeDescription;
	}
	public void setHeadCodeDescription(String headCodeDescription) {
		this.headCodeDescription = headCodeDescription;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public List getMonthYears() {
		return monthYears;
	}
	public void setMonthYears(List monthYears) {
		this.monthYears = monthYears;
	}
	
}
