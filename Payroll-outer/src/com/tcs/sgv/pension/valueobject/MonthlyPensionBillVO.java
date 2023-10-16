package com.tcs.sgv.pension.valueobject;

import java.math.BigDecimal;

public class MonthlyPensionBillVO {

	private String pensionerCode;

	private String ppoNo;
	
	private String pensionerName;
	
	private String accountNo;
	
	private BigDecimal basicPensionAmount;

	private BigDecimal dpPercent;

	private BigDecimal tiPercent;
	
	private BigDecimal dpPercentAmount;

	private BigDecimal tiPercentAmount;
	
	private BigDecimal medicalAllowenceAmount;

	private BigDecimal cvpMonthlyAmount;
	
	private BigDecimal TIArrearsAmount;
	
	private BigDecimal otherArrearsAmount;
	
	private BigDecimal itCutAmount;
	
	private BigDecimal specialCutAmount;
	
	private BigDecimal otherBenefit;
	
	private BigDecimal OMR;
	
	private BigDecimal recoveryAmount;
	
	private BigDecimal pensionCutAmount;
	
	private BigDecimal netPensionAmount;
	
	private BigDecimal allnBf11156;

    private BigDecimal allnAf11156;
    
    private BigDecimal allnAf10560;
    
    private BigDecimal personalPension;

    private BigDecimal ir;
    
    private BigDecimal MOComm;
    
    private Integer forMonth;

	
//	 Constructors

	/** default constructor */
	public MonthlyPensionBillVO() {
	}
	
	/** full constructor */
	public MonthlyPensionBillVO(String pensionerCode,
			String ppoNo, String pensionerName, String accountNo, 
			BigDecimal basicPensionAmount,BigDecimal dpPercent,BigDecimal tiPercent, BigDecimal dpPercentAmount,
			BigDecimal tiPercentAmount, BigDecimal medicalAllowenceAmount,
			BigDecimal cvpMonthlyAmount, BigDecimal TIArrearsAmount, BigDecimal otherArrearsAmount,
			BigDecimal itCutAmount, BigDecimal specialCutAmount, BigDecimal otherBenefit, BigDecimal OMR,
			BigDecimal recoveryAmount, BigDecimal pensionCutAmount, BigDecimal netPensionAmount,
			BigDecimal allnBf11156, BigDecimal allnAf11156, BigDecimal allnAf10560, 
			BigDecimal personalPension, BigDecimal ir, BigDecimal MOComm,Integer forMonth) {
		
		this.pensionerCode = pensionerCode;
		this.ppoNo = ppoNo;
		this.pensionerName = pensionerName;
		this.accountNo = accountNo;
		this.basicPensionAmount = basicPensionAmount;
		this.dpPercent = dpPercent;
		this.tiPercent = tiPercent;
		this.dpPercentAmount = dpPercentAmount;
		this.tiPercentAmount = tiPercentAmount;
		this.cvpMonthlyAmount = cvpMonthlyAmount;
		this.medicalAllowenceAmount = medicalAllowenceAmount;
		this.TIArrearsAmount = TIArrearsAmount;
		this.otherArrearsAmount = otherArrearsAmount;
		this.itCutAmount = itCutAmount;
		this.specialCutAmount = specialCutAmount;
		this.otherBenefit = otherBenefit;
		this.OMR = OMR;
		this.recoveryAmount = recoveryAmount;
		this.pensionCutAmount = pensionCutAmount;
		this.netPensionAmount = netPensionAmount;
		this.allnBf11156 = allnBf11156;
		this.allnAf11156 = allnAf11156;
		this.allnAf10560 = allnAf10560;
		this.personalPension = personalPension;
		this.ir = ir;
		this.MOComm = MOComm;
		this.forMonth = forMonth;
	}
	
	
	
//	 Property accessors
	public String getPensionerCode() {
		return this.pensionerCode;
	}

	public void setPensionerCode(String pensionerCode) {
		this.pensionerCode = pensionerCode;
	}

	public String getPpoNo() {
		return this.ppoNo;
	}

	public void setPpoNo(String ppoNo) {
		this.ppoNo = ppoNo;
	}
	
	public String getPensionerName() {
		return this.pensionerName;
	}

	public void setPensionerName(String pensionerName) {
		this.pensionerName = pensionerName;
	}
	
	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	
	public BigDecimal getBasicPensionAmount() {
		return this.basicPensionAmount;
	}

	public void setBasicPensionAmount(BigDecimal basicPensionAmount) {
		this.basicPensionAmount = basicPensionAmount;
	}

	public BigDecimal getDpPercent() {
		return this.dpPercent;
	}

	public void setDpPercent(BigDecimal dpPercent) {
		this.dpPercent = dpPercent;
	}
	
	public BigDecimal getTiPercent() {
		return this.tiPercent;
	}

	public void setTiPercent(BigDecimal tiPercent) {
		this.tiPercent = tiPercent;
	}
	
	public BigDecimal getDpPercentAmount() {
		return this.dpPercentAmount;
	}

	public void setDpPercentAmount(BigDecimal dpPercentAmount) {
		this.dpPercentAmount = dpPercentAmount;
	}

	public BigDecimal getTiPercentAmount() {
		return this.tiPercentAmount;
	}

	public void setTiPercentAmount(BigDecimal tiPercentAmount) {
		this.tiPercentAmount = tiPercentAmount;
	}

	public BigDecimal getCvpMonthlyAmount() {
		return this.cvpMonthlyAmount;
	}

	public void setCvpMonthlyAmount(BigDecimal cvpMonthlyAmount) {
		this.cvpMonthlyAmount = cvpMonthlyAmount;
	}

	public BigDecimal getMedicalAllowenceAmount() {
		return this.medicalAllowenceAmount;
	}

	public void setMedicalAllowenceAmount(BigDecimal medicalAllowenceAmount) {
		this.medicalAllowenceAmount = medicalAllowenceAmount;
	}

	public BigDecimal getTIArrearsAmount() {
		return this.TIArrearsAmount;
	}

	public void setTIArrearsAmount(BigDecimal TIArrearsAmount) {
		this.TIArrearsAmount = TIArrearsAmount;
	}
	
	public BigDecimal getOtherArrearsAmount() {
		return this.otherArrearsAmount;
	}

	public void setOtherArrearsAmount(BigDecimal otherArrearsAmount) {
		this.otherArrearsAmount = otherArrearsAmount;
	}

	public BigDecimal getItCutAmount() {
		return this.itCutAmount;
	}

	public void setItCutAmount(BigDecimal itCutAmount) {
		this.itCutAmount = itCutAmount;
	}

	public BigDecimal getSpecialCutAmount() {
		return this.specialCutAmount;
	}

	public void setSpecialCutAmount(BigDecimal specialCutAmount) {
		this.specialCutAmount = specialCutAmount;
	}
	
	public BigDecimal getOtherBenefit() {
		return this.otherBenefit;
	}

	public void setOtherBenefit(BigDecimal otherBenefit) {
		this.otherBenefit = otherBenefit;
	}

	public BigDecimal getOMR() {
		return this.OMR;
	}

	public void setOMR(BigDecimal OMR) {
		this.OMR = OMR;
	}
	
	public BigDecimal getRecoveryAmount() {
		return this.recoveryAmount;
	}

	public void setRecoveryAmount(BigDecimal recoveryAmount) {
		this.recoveryAmount = recoveryAmount;
	}
	
	public BigDecimal getPensionCutAmount() {
		return this.pensionCutAmount;
	}

	public void setPensionCutAmount(BigDecimal pensionCutAmount) {
		this.pensionCutAmount = pensionCutAmount;
	}
	
	public BigDecimal getNetPensionAmount() {
		return this.netPensionAmount;
	}

	public void setNetPensionAmount(BigDecimal netPensionAmount) {
		this.netPensionAmount = netPensionAmount;
	}
	
	public BigDecimal getAllnBf11156() {
		return this.allnBf11156;
	}

	public void setAllnBf11156(BigDecimal allnBf11156) {
		this.allnBf11156 = allnBf11156;
	}
	
	public BigDecimal getAllnAf11156() {
		return this.allnAf11156;
	}

	public void setAllnAf11156(BigDecimal allnAf11156) {
		this.allnAf11156 = allnAf11156;
	}
	
	public BigDecimal getAllnAf10560() {
		return this.allnAf10560;
	}

	public void setAllnAf10560(BigDecimal allnAf10560) {
		this.allnAf10560 = allnAf10560;
	}
	
	public BigDecimal getPersonalPension()
    {
        return this.personalPension;
    }

    public void setPersonalPension(BigDecimal personalPension)
    {
        this.personalPension = personalPension;
    }

    public BigDecimal getIr()
    {
        return this.ir;
    }

    public void setIr(BigDecimal ir)
    {
        this.ir = ir;
    }
    
    public BigDecimal getMOComm()
    {
        return this.MOComm;
    }

    public void setMOComm(BigDecimal MOComm)
    {
        this.MOComm = MOComm;
    }

	public Integer getForMonth() {
		return forMonth;
	}

	public void setForMonth(Integer forMonth) {
		this.forMonth = forMonth;
	}
}
