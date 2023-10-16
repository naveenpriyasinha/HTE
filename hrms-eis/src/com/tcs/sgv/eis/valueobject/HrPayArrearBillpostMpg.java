package com.tcs.sgv.eis.valueobject;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;

public class HrPayArrearBillpostMpg implements java.io.Serializable {
	// Fields    

	private long arrearBillpostId;
	
	private OrgPostMst orgPostMst;

	private HrPaySalRevMst hrPaySalRevMst;
	
	private HrPayBillHeadMpg hrPayBillHeadMpg;
	
	private CmnLocationMst cmnLocationMst;
	
	private HrEisEmpMst hrEisEmpMst;

	public long getArrearBillpostId() {
		return arrearBillpostId;
	}

	public void setArrearBillpostId(long arrearBillpostId) {
		this.arrearBillpostId = arrearBillpostId;
	}

	public OrgPostMst getOrgPostMst() {
		return orgPostMst;
	}

	public void setOrgPostMst(OrgPostMst orgPostMst) {
		this.orgPostMst = orgPostMst;
	}

	public HrPaySalRevMst getHrPaySalRevMst() {
		return hrPaySalRevMst;
	}

	public void setHrPaySalRevMst(HrPaySalRevMst hrPaySalRevMst) {
		this.hrPaySalRevMst = hrPaySalRevMst;
	}

	public HrPayBillHeadMpg getHrPayBillHeadMpg() {
		return hrPayBillHeadMpg;
	}

	public void setHrPayBillHeadMpg(HrPayBillHeadMpg hrPayBillHeadMpg) {
		this.hrPayBillHeadMpg = hrPayBillHeadMpg;
	}

	public CmnLocationMst getCmnLocationMst() {
		return cmnLocationMst;
	}

	public void setCmnLocationMst(CmnLocationMst cmnLocationMst) {
		this.cmnLocationMst = cmnLocationMst;
	}
	
	public HrEisEmpMst getHrEisEmpMst() {
		return hrEisEmpMst;
	}

	public void setHrEisEmpMst(HrEisEmpMst hrEisEmpMst) {
		this.hrEisEmpMst = hrEisEmpMst;
	}

	
	
}
