/**
 * 		Class Name:-	PayslipCustomVO
 * 		Author Name:-	Urvin shah.
 * 		Date:-			31/03/2008.
 * 		Purpose:-		Club all the Required fields for showing purpose in the Payslip.
 */


package com.tcs.sgv.eis.valueobject;

public class PayslipCustomVO {
	
	private HrPayPayslip hrPayPayslip;	// Payslip Object.
	private int totalGpfAdvAmt;			// Total GPF Advance Amount
	private int recGpfAdvAmt;			// Recovered GPF Advance Amount
	
	private int totalHbaPrinAmt;		// Total HBA Principal Amount.
	private int recHbaPrinAmt;			// Recovered HBA Principal Amount.
	private int totalHbaIntAmt;			// Total HBA Interest Amount.
	private int recHbaIntAmt;			// Recovered HBA Interest Amount.
	private String hbaAccNo;			// HBA loan Account number.
	
	private int totalMcaPrinAmt;		// Total MCA Principal Amount.
	private int recMcaPrinAmt;			// Recovered MCA Principal Amount.
	private int totalMcaIntAmt;			// Total MCA Interest Amount.
	private int recMcaIntAmt;			// Recovered MCA Interest Amount.
	private String mcaAccNo;			// MCA loan Account number.
	
	private int totalFoodAdvAmt;		// Total Food Advance Amount
	private int recFoodAdvAmt;			// Recovered Food Advance Amount
	
	private int totalFestAdvAmt;		// Total Festival Advance Amount
	private int recFestAdvAmt;			// Recovered Festival Advance Amount
	
	private int totalFanAdvAmt;			// Total Fan Advance Amount
	private int recFanAdvAmt;			// Recovered Fan Advance Amount
	
	private int currYearTraAllowAmt;	// Total Transport Allowance Amount of the Current Financial Year.
	private int currYearProffTaxwAmt;	// Total Proffessional Tax Amount of the Current Financial Year.
	private int currYearPfAmt;			// Total Providand Fund Amount of the Current Financial Year.
	private int currYearHbaPrinAmt;		// Total Recovered HBA Principal Amount in the Current Financial Year.
	private int currYearHbaIntAmt;		// Total Recovered HBA Interest Amount in the Current Financial Year.
	private int currYearSgiAmt;			// Total SGI Amount of the current Financial Year.
	private int currYearLicAmt;			// Total LIC Amount of the current Financial Year.
	private int currYearIncomeTaxAmt;	// Total IncomeTax Amount of the current financial Year.
	private int currYearGrossAmt;		// Total Gross Amount of the current financial Year.
	private String quarterName;			// Quarter Name.
	
	private int incrementAmt;			// Increment Amount.
	private String incrementDate;		// Date Of an Normal Increment of an Employee.
	private String bankAccNo;			// Bank Account number.
	
	private String designationName;		// Designation Name of an employee.
	
	// Non Government Deduction Details.
	
	private long oldSocietyAmt;			// Old Society Amount.
	private long newSocietyAmt;			// New Society Amount.
	private long karmBankAmt;			// Karmchary Bank Amount.
	private long nagBankAmt;				// Nagrik Bank Amount.
	private long licAmount;				// LIC Amount.
	private long por;					//post office recurring depostit
	private long cmrf;					//chief minister relief fund
	private long ssf;					//salary saving fund
	private long totNonGovDedcAmt;		// Summation of All Non Government Dedctions.
	
	private long netPayableAmt;			// NetPayableAmount = NetAmount-Total Non Government Dedctions.
	private String strNetPayableAmt;	// NetPayableAmount in String Format.
	
	private String gpfAdvInst;			// This String contains the totalGPfAdvInstallmentNo/recoveredGPFInstallmentNo.		
	private String fesAdvInst;			// This String contains the totalFestivalAdvInstallmentNo/recoveredFestivalAdvInstallmentNo.
	private String foodAdvInst;			// This String contains the totalFoodAdvInstallmentNo/recoveredFoodAdvInstallmentNo.
	private String fanAdvInst;			// This String contains the totalFanAdvInstallmentNo/recoveredFanAdvInstallmentNo.		
	private String hbaInst;				// This String contains the totalHbaLoanInstallmentNo/recoveredHbaLoanInstallmentNo.
	private String vehAdvInst;			// This String contains the totalVehLoanInstallmentNo/recoveredVehLoanInstallmentNo.	
	
	//	 Default Constructor.
	
	public PayslipCustomVO(){
		super();
	}

	// Contructor with All Parameters.
	
	public PayslipCustomVO(HrPayPayslip hrPayPayslip, int totalGpfAdvAmt, int recGpfAdvAmt, int totalHbaPrinAmt, int recHbaPrinAmt, int totalHbaIntAmt, int recHbaIntAmt, String hbaAccNo, int totalMcaPrinAmt, int recMcaPrinAmt, int totalMcaIntAmt, int recMcaIntAmt, String mcaAccNo, int totalFoodAdvAmt, int recFoodAdvAmt, int totalFestAdvAmt, int recFestAdvAmt, int totalFanAdvAmt, int recFanAdvAmt, int currYearTraAllowAmt, int currYearProffTaxwAmt, int currYearPfAmt, int currYearHbaPrinAmt, int currYearHbaIntAmt, int currYearSgiAmt, int currYearLicAmt, int currYearIncomeTaxAmt, String quarterName, int incrementAmt, String incrementDate, String bankAccNo, String designationName, long oldSocietyAmt, long newSocietyAmt, long karmBankAmt, long nagBankAmt, long licAmount, long totNonGovDedcAmt, long netPayableAmt, String strNetPayableAmt, String gpfAdvInst, String fesAdvInst, String foodAdvInst, String fanAdvInst, String hbaInst, String vehAdvInst,int currYearGrossAmt) {
		super();
		this.hrPayPayslip = hrPayPayslip;
		this.totalGpfAdvAmt = totalGpfAdvAmt;
		this.recGpfAdvAmt = recGpfAdvAmt;
		this.totalHbaPrinAmt = totalHbaPrinAmt;
		this.recHbaPrinAmt = recHbaPrinAmt;
		this.totalHbaIntAmt = totalHbaIntAmt;
		this.recHbaIntAmt = recHbaIntAmt;
		this.hbaAccNo = hbaAccNo;
		this.totalMcaPrinAmt = totalMcaPrinAmt;
		this.recMcaPrinAmt = recMcaPrinAmt;
		this.totalMcaIntAmt = totalMcaIntAmt;
		this.recMcaIntAmt = recMcaIntAmt;
		this.mcaAccNo = mcaAccNo;
		this.totalFoodAdvAmt = totalFoodAdvAmt;
		this.recFoodAdvAmt = recFoodAdvAmt;
		this.totalFestAdvAmt = totalFestAdvAmt;
		this.recFestAdvAmt = recFestAdvAmt;
		this.totalFanAdvAmt = totalFanAdvAmt;
		this.recFanAdvAmt = recFanAdvAmt;
		this.currYearTraAllowAmt = currYearTraAllowAmt;
		this.currYearProffTaxwAmt = currYearProffTaxwAmt;
		this.currYearPfAmt = currYearPfAmt;
		this.currYearHbaPrinAmt = currYearHbaPrinAmt;
		this.currYearHbaIntAmt = currYearHbaIntAmt;
		this.currYearSgiAmt = currYearSgiAmt;
		this.currYearLicAmt = currYearLicAmt;
		this.currYearIncomeTaxAmt = currYearIncomeTaxAmt;
		this.quarterName = quarterName;
		this.incrementAmt = incrementAmt;
		this.incrementDate = incrementDate;
		this.bankAccNo = bankAccNo;
		this.designationName = designationName;
		this.oldSocietyAmt = oldSocietyAmt;
		this.newSocietyAmt = newSocietyAmt;
		this.karmBankAmt = karmBankAmt;
		this.nagBankAmt = nagBankAmt;
		this.licAmount = licAmount;
		this.totNonGovDedcAmt = totNonGovDedcAmt;
		this.netPayableAmt = netPayableAmt;
		this.strNetPayableAmt = strNetPayableAmt;
		this.gpfAdvInst = gpfAdvInst;
		this.fesAdvInst = fesAdvInst;
		this.foodAdvInst = foodAdvInst;
		this.fanAdvInst = fanAdvInst;
		this.hbaInst = hbaInst;
		this.vehAdvInst = vehAdvInst;
		this.currYearGrossAmt=currYearGrossAmt;
	}

	

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
	// Getters-Setters.
	
	public int getCurrYearHbaIntAmt() {
		return currYearHbaIntAmt;
	}
	public void setCurrYearHbaIntAmt(int currYearHbaIntAmt) {
		this.currYearHbaIntAmt = currYearHbaIntAmt;
	}
	public int getCurrYearHbaPrinAmt() {
		return currYearHbaPrinAmt;
	}
	public void setCurrYearHbaPrinAmt(int currYearHbaPrinAmt) {
		this.currYearHbaPrinAmt = currYearHbaPrinAmt;
	}
	public int getCurrYearIncomeTaxAmt() {
		return currYearIncomeTaxAmt;
	}
	public void setCurrYearIncomeTaxAmt(int currYearIncomeTaxAmt) {
		this.currYearIncomeTaxAmt = currYearIncomeTaxAmt;
	}
	public int getCurrYearLicAmt() {
		return currYearLicAmt;
	}
	public void setCurrYearLicAmt(int currYearLicAmt) {
		this.currYearLicAmt = currYearLicAmt;
	}
	public int getCurrYearPfAmt() {
		return currYearPfAmt;
	}
	public void setCurrYearPfAmt(int currYearPfAmt) {
		this.currYearPfAmt = currYearPfAmt;
	}
	public int getCurrYearProffTaxwAmt() {
		return currYearProffTaxwAmt;
	}
	public void setCurrYearProffTaxwAmt(int currYearProffTaxwAmt) {
		this.currYearProffTaxwAmt = currYearProffTaxwAmt;
	}
	public int getCurrYearSgiAmt() {
		return currYearSgiAmt;
	}
	public void setCurrYearSgiAmt(int currYearSgiAmt) {
		this.currYearSgiAmt = currYearSgiAmt;
	}
	public int getCurrYearTraAllowAmt() {
		return currYearTraAllowAmt;
	}
	public void setCurrYearTraAllowAmt(int currYearTraAllowAmt) {
		this.currYearTraAllowAmt = currYearTraAllowAmt;
	}
	public String getHbaAccNo() {
		return hbaAccNo;
	}
	public void setHbaAccNo(String hbaAccNo) {
		this.hbaAccNo = hbaAccNo;
	}
	public HrPayPayslip getHrPayPayslip() {
		return hrPayPayslip;
	}
	public void setHrPayPayslip(HrPayPayslip hrPayPayslip) {
		this.hrPayPayslip = hrPayPayslip;
	}
	public String getMcaAccNo() {
		return mcaAccNo;
	}
	public void setMcaAccNo(String mcaAccNo) {
		this.mcaAccNo = mcaAccNo;
	}
	public String getQuarterName() {
		return quarterName;
	}
	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}
	public int getRecFanAdvAmt() {
		return recFanAdvAmt;
	}
	public void setRecFanAdvAmt(int recFanAdvAmt) {
		this.recFanAdvAmt = recFanAdvAmt;
	}
	public int getRecFestAdvAmt() {
		return recFestAdvAmt;
	}
	public void setRecFestAdvAmt(int recFestAdvAmt) {
		this.recFestAdvAmt = recFestAdvAmt;
	}
	public int getRecFoodAdvAmt() {
		return recFoodAdvAmt;
	}
	public void setRecFoodAdvAmt(int recFoodAdvAmt) {
		this.recFoodAdvAmt = recFoodAdvAmt;
	}
	public int getRecGpfAdvAmt() {
		return recGpfAdvAmt;
	}
	public void setRecGpfAdvAmt(int recGpfAdvAmt) {
		this.recGpfAdvAmt = recGpfAdvAmt;
	}
	public int getRecHbaPrinAmt() {
		return recHbaPrinAmt;
	}
	public void setRecHbaPrinAmt(int recHbaPrinAmt) {
		this.recHbaPrinAmt = recHbaPrinAmt;
	}
	public int getRecHbaIntAmt() {
		return recHbaIntAmt;
	}
	public void setRecHbaIntAmt(int recHbaIntAmt) {
		this.recHbaIntAmt = recHbaIntAmt;
	}
	public int getRecMcaPrinAmt() {
		return recMcaPrinAmt;
	}
	public void setRecMcaPrinAmt(int recMcaPrinAmt) {
		this.recMcaPrinAmt = recMcaPrinAmt;
	}
	public int getRecMcaIntAmt() {
		return recMcaIntAmt;
	}
	public void setRecMcaIntAmt(int recMcaIntAmt) {
		this.recMcaIntAmt = recMcaIntAmt;
	}
	public int getTotalFanAdvAmt() {
		return totalFanAdvAmt;
	}
	public void setTotalFanAdvAmt(int totalFanAdvAmt) {
		this.totalFanAdvAmt = totalFanAdvAmt;
	}
	public int getTotalFestAdvAmt() {
		return totalFestAdvAmt;
	}
	public void setTotalFestAdvAmt(int totalFestAdvAmt) {
		this.totalFestAdvAmt = totalFestAdvAmt;
	}
	public int getTotalFoodAdvAmt() {
		return totalFoodAdvAmt;
	}
	public void setTotalFoodAdvAmt(int totalFoodAdvAmt) {
		this.totalFoodAdvAmt = totalFoodAdvAmt;
	}
	public int getTotalGpfAdvAmt() {
		return totalGpfAdvAmt;
	}
	public void setTotalGpfAdvAmt(int totalGpfAdvAmt) {
		this.totalGpfAdvAmt = totalGpfAdvAmt;
	}
	public int getTotalHbaIntAmt() {
		return totalHbaIntAmt;
	}
	public void setTotalHbaIntAmt(int totalHbaIntAmt) {
		this.totalHbaIntAmt = totalHbaIntAmt;
	}
	public int getTotalHbaPrinAmt() {
		return totalHbaPrinAmt;
	}
	public void setTotalHbaPrinAmt(int totalHbaPrinAmt) {
		this.totalHbaPrinAmt = totalHbaPrinAmt;
	}
	public int getTotalMcaIntAmt() {
		return totalMcaIntAmt;
	}
	public void setTotalMcaIntAmt(int totalMcaIntAmt) {
		this.totalMcaIntAmt = totalMcaIntAmt;
	}
	public int getTotalMcaPrinAmt() {
		return totalMcaPrinAmt;
	}
	public void setTotalMcaPrinAmt(int totalMcaPrinAmt) {
		this.totalMcaPrinAmt = totalMcaPrinAmt;
	}

	public String getIncrementDate() {
		return incrementDate;
	}

	public void setIncrementDate(String incrementDate) {
		this.incrementDate = incrementDate;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getStrNetPayableAmt() {
		return strNetPayableAmt;
	}

	public void setStrNetPayableAmt(String strNetPayableAmt) {
		this.strNetPayableAmt = strNetPayableAmt;
	}

	public int getIncrementAmt() {
		return incrementAmt;
	}

	public void setIncrementAmt(int incrementAmt) {
		this.incrementAmt = incrementAmt;
	}

	public String getFanAdvInst() {
		return fanAdvInst;
	}

	public void setFanAdvInst(String fanAdvInst) {
		this.fanAdvInst = fanAdvInst;
	}

	public String getFesAdvInst() {
		return fesAdvInst;
	}

	public void setFesAdvInst(String fesAdvInst) {
		this.fesAdvInst = fesAdvInst;
	}

	public String getFoodAdvInst() {
		return foodAdvInst;
	}

	public void setFoodAdvInst(String foodAdvInst) {
		this.foodAdvInst = foodAdvInst;
	}

	public String getGpfAdvInst() {
		return gpfAdvInst;
	}

	public void setGpfAdvInst(String gpfAdvInst) {
		this.gpfAdvInst = gpfAdvInst;
	}

	public String getHbaInst() {
		return hbaInst;
	}

	public void setHbaInst(String hbaInst) {
		this.hbaInst = hbaInst;
	}

	public String getVehAdvInst() {
		return vehAdvInst;
	}

	public void setVehAdvInst(String vehAdvInst) {
		this.vehAdvInst = vehAdvInst;
	}

	public int getCurrYearGrossAmt() {
		return currYearGrossAmt;
	}

	public void setCurrYearGrossAmt(int currYearGrossAmt) {
		this.currYearGrossAmt = currYearGrossAmt;
	}

	public long getCmrf() {
		return cmrf;
	}

	public void setCmrf(long cmrf) {
		this.cmrf = cmrf;
	}

	public long getPor() {
		return por;
	}

	public void setPor(long por) {
		this.por = por;
	}

	public long getSsf() {
		return ssf;
	}

	public void setSsf(long ssf) {
		this.ssf = ssf;
	}

	public long getTotNonGovDedcAmt() {
		return totNonGovDedcAmt;
	}

	public void setTotNonGovDedcAmt(long totNonGovDedcAmt) {
		this.totNonGovDedcAmt = totNonGovDedcAmt;
	}

	public long getNetPayableAmt() {
		return netPayableAmt;
	}

	public void setNetPayableAmt(long netPayableAmt) {
		this.netPayableAmt = netPayableAmt;
	}

	public long getKarmBankAmt() {
		return karmBankAmt;
	}

	public void setKarmBankAmt(long karmBankAmt) {
		this.karmBankAmt = karmBankAmt;
	}

	public long getLicAmount() {
		return licAmount;
	}

	public void setLicAmount(long licAmount) {
		this.licAmount = licAmount;
	}

	public long getNagBankAmt() {
		return nagBankAmt;
	}

	public void setNagBankAmt(long nagBankAmt) {
		this.nagBankAmt = nagBankAmt;
	}

	public long getNewSocietyAmt() {
		return newSocietyAmt;
	}

	public void setNewSocietyAmt(long newSocietyAmt) {
		this.newSocietyAmt = newSocietyAmt;
	}

	public long getOldSocietyAmt() {
		return oldSocietyAmt;
	}

	public void setOldSocietyAmt(long oldSocietyAmt) {
		this.oldSocietyAmt = oldSocietyAmt;
	}
}
