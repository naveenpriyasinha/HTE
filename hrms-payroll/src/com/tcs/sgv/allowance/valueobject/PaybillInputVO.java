package com.tcs.sgv.allowance.valueobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PaybillInputVO {

	private double Basic=18900;
	private String city="A1";
	private long cityId;
	private long branchId;
	private int grade=35;
	private double scale_start_amt=18400;
	private double old_scale_start_amt=18400;
	private int quater_id=1;
	private double food_adv=0;
	private double fest_Adv=0;
	private double pay_recv=0;
	private boolean isHandicapped = false;
	private boolean isVechAvailed = false;
	double taDistance = 0;
	private double CLLeaveBal = 10;
	private double CLLeaveTaken = 0;
	private long vpf=0;
	//Added by Paurav for Medical Allowance and Uniform Availed
	private long medAllowance = 0;
	private long uniformAvailed = 0;
	//Ended by Paurav
	//private long gpf_adv=0;
	// Added for checking individual leave balance
	private double LeaveExcess = 0;
	private int month;
	private int year;
	private int isOwnHouse=0;
	//by manoj for gpf_account Details
	private int gpfAccountFlag=0;
	//end by manoj for gpf_account details
	//added by manoj for 6th pay 
	private double gradePay = 0;
	//end by manoj for 6th pay 
	
	
	private List quaterIdList = new ArrayList();
	private List quaterDaysList = new ArrayList();
	private List lstQuarterRate = new ArrayList();

	private int isSis=0;
	
	private double TRAExcess=0;
	private double HRAExcess=0;
	private double CLAExcess=0;
	private double MAExcess=0;
	private double WAExcess=0;
	
	private double LWPExcess=0;
	private double LWPInMonth=0;
	private int LWPFlag = 0;
	
	private long leaveType=0;
    //by manoj for misc recovery in outer
	double miscRecovery=0;
	private long designation=0; 
	
	//Added by Paurav for passing Employee Type
	private long empType = 0;
	//Ended By Paurav 
	
	private Date EmpSrvcExp= new Date();
	private int ZerovpfMonths=3;
	
	private Date doj= new Date();
	
	private String sis1979Flag="n";
	
	private double persExcess=0;
	
	private double splExcess=0;
	
	private long gpfGradeValue=0;
	
	private int isAvailedHRA=0;
	
	private long  empId = 0;
	
	private long groupCode=0;
	
	
	
	//to send this emp Id to give fixed cla 
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	//added by manoj for 6th pay
	public double getGradePay() {
		return gradePay;
	}
	public void setGradePay(double gradePay) {
		this.gradePay = gradePay;
	}
	
	//end by manoj for 6th pay
	
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	
	public long getGpfGradeValue() {
		return gpfGradeValue;
	}
	public void setGpfGradeValue(long gpfGradeValue) {
		this.gpfGradeValue = gpfGradeValue;
	}
	public long getDesignation() {
		return designation;
	}
	public void setDesignation(long designation) {
		this.designation = designation;
	}
	//getter setter
//	by manoj for misc recovery in outer
	public double getMiscRecovery() {
		return miscRecovery;
	}
	public void setMiscRecovery(double miscRecovery) {
		this.miscRecovery = miscRecovery;
	}
//end	by manoj for misc recovery in outer
	public double getCLAExcess() {
		return CLAExcess;
	}
	public void setCLAExcess(double excess) {
		CLAExcess = excess;
	}
	public double getHRAExcess() {
		return HRAExcess;
	}
	public void setHRAExcess(double excess) {
		HRAExcess = excess;
	}
	public long getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(long leaveType) {
		this.leaveType = leaveType;
	}
	public double getMAExcess() {
		return MAExcess;
	}
	public void setMAExcess(double excess) {
		MAExcess = excess;
	}
	public double getTRAExcess() {
		return TRAExcess;
	}
	public void setTRAExcess(double excess) {
		TRAExcess = excess;
	}
	/*public long getGpf_adv() {
		return gpf_adv;
	}
	public void setGpf_adv(long gpf_adv) {
		this.gpf_adv = gpf_adv;
	}*/
	public long getVpf() {
		return vpf;
	}
	public void setVpf(long vpf) {
		this.vpf = vpf;
	}
	public double getBasic() {
		return Basic;
	}
	public void setBasic(double basic) {
		Basic = basic;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getFest_Adv() {
		return fest_Adv;
	}
	public void setFest_Adv(double fest_Adv) {
		this.fest_Adv = fest_Adv;
	}
	public double getFood_adv() {
		return food_adv;
	}
	public void setFood_adv(double food_adv) {
		this.food_adv = food_adv;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public double getPay_recv() {
		return pay_recv;
	}
	public void setPay_recv(double pay_recv) {
		this.pay_recv = pay_recv;
	}
	public int getQuater_id() {
		return quater_id;
	}
	public void setQuater_id(int quater_id) {
		this.quater_id = quater_id;
	}
	public double getScale_start_amt() {
		return scale_start_amt;
	}
	public void setScale_start_amt(double scale_start_amt) {
		this.scale_start_amt = scale_start_amt;
		
	}
	public boolean isHandicapped()
	{
		return isHandicapped;
	}
	public void setIsHandicapped(boolean newIsHandicapped)
	{
		isHandicapped = newIsHandicapped;
	}
	
	public double getCLLeaveBal()
	{
		return CLLeaveBal;
	}
	public void setCLLeaveBal(double newCLLeaveBal)
	{
		CLLeaveBal = newCLLeaveBal;
	}
	
	public double getCLLeaveTaken()
	{
		return CLLeaveTaken;
	}
	public void setCLLeaveTaken(double newCLLeaveTaken)
	{
		CLLeaveTaken = newCLLeaveTaken;
	}
	
	public long getUniformAvailed() {
		return uniformAvailed;
	}
	public void setUniformAvailed(long uniformAvailed) {
		this.uniformAvailed = uniformAvailed;
	}
	public void setHandicapped(boolean isHandicapped) {
		this.isHandicapped = isHandicapped;
	}
	public long getMedAllowance() {
		return medAllowance;
	}
	public void setMedAllowance(long medAllowance) {
		this.medAllowance = medAllowance;
	}
	public double getLeaveExcess() {
		return LeaveExcess;
	}
	public void setLeaveExcess(double leaveExcess) {
		LeaveExcess = leaveExcess;
	}
	public double getWAExcess() {
		return WAExcess;
	}
	public void setWAExcess(double excess) {
		WAExcess = excess;
	}
	public long getEmpType() {
		return empType;
	}
	public void setEmpType(long empType) {
		this.empType = empType;
	}
	public List getQuaterDaysList() {
		return quaterDaysList;
	}
	public void setQuaterDaysList(List quaterDaysList) {
		this.quaterDaysList = quaterDaysList;
	}
	public List getQuaterIdList() {
		return quaterIdList;
	}
	public void setQuaterIdList(List quaterIdList) {
		this.quaterIdList = quaterIdList;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getGpfAccountFlag() {
		return gpfAccountFlag;
	}
	public void setGpfAccountFlag(int gpfAccountFlag) {
		this.gpfAccountFlag = gpfAccountFlag;
	}
	public boolean isVechAvailed() {
		return isVechAvailed;
	}
	public void setVechAvailed(boolean isVechAvailed) {
		this.isVechAvailed = isVechAvailed;
	}
	public double getTaDistance() {
		return taDistance;
	}
	public void setTaDistance(double taDistance) {
		this.taDistance = taDistance;
	}
	public List getLstQuarterRate() {
		return lstQuarterRate;
	}
	public void setLstQuarterRate(List lstQuarterRate) {
		this.lstQuarterRate = lstQuarterRate;
	}
	public Date getEmpSrvcExp() {
		return EmpSrvcExp;
	}
	public void setEmpSrvcExp(Date empSrvcExp) {
		EmpSrvcExp = empSrvcExp;
	}
	public int getZerovpfMonths() {
		return ZerovpfMonths;
	}
	public void setZerovpfMonths(int zerovpfMonths) {
		ZerovpfMonths = zerovpfMonths;
	}
	public String getSis1979Flag() {
		return sis1979Flag;
	}
	public void setSis1979Flag(String sis1979Flag) {
		this.sis1979Flag = sis1979Flag;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public double getPersExcess() {
		return persExcess;
	}
	public void setPersExcess(double persExcess) {
		this.persExcess = persExcess;
	}
	public double getSplExcess() {
		return splExcess;
	}
	public void setSplExcess(double splExcess) {
		this.splExcess = splExcess;
	}
	public double getOld_scale_start_amt() {
		return old_scale_start_amt;
	}
	public void setOld_scale_start_amt(double old_scale_start_amt) {
		this.old_scale_start_amt = old_scale_start_amt;
	}
	public int getIsOwnHouse() {
		return isOwnHouse;
	}
	public void setIsOwnHouse(int isOwnHouse) {
		this.isOwnHouse = isOwnHouse;
	}
	public double getLWPExcess() {
		return LWPExcess;
	}
	public void setLWPExcess(double excess) {
		LWPExcess = excess;
	}

	public int getLWPFlag() {
		return LWPFlag;
	}
	public void setLWPFlag(int flag) {
		LWPFlag = flag;
	}
	public double getLWPInMonth() {
		return LWPInMonth;
	}
	public void setLWPInMonth(double inMonth) {
		LWPInMonth = inMonth;
	}
	public int getIsSis() {
		return isSis;
	}
	public void setIsSis(int isSis) {
		this.isSis = isSis;
	}
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	
	public void setIsAvailedHRA( int isAvailedHRA ){
		this.isAvailedHRA = isAvailedHRA;
	}
	
	public int getIsAvailedHRA(){
		return isAvailedHRA;
	}
	/**
	 * @return the groupCode
	 */
	public long getGroupCode() {
		return groupCode;
	}
	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(long groupCode) {
		this.groupCode = groupCode;
	}
	
	
}
