package com.tcs.sgv.exprcpt.valueobject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A POJO for payment and recipt by head struture (formula)
 *  
 * @author 206819
 */
public class BudHdAmtVO {

	private String formula;
	private BigDecimal amount;
	private HashMap  hashMap;
	
	public String getFormula() {
		return formula;
	}
	
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public void setHashMap(HashMap map) {
		this.hashMap = map;
	}

	public Map getHashMap() {
		return this.hashMap;
	}

	public BigDecimal getAmountByDt(Date date) {
		return (BigDecimal)hashMap.get(date);
	}

	public void setAmountByDt(Date date, BigDecimal amount) {
		hashMap.put(date,amount);
	}
}
