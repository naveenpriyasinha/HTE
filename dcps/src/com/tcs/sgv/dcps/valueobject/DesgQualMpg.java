package com.tcs.sgv.dcps.valueobject;

import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;
import com.tcs.sgv.ess.valueobject.OrgPostMst;

public class DesgQualMpg {

	private long desQualMpgId;
	
	private String desgName;

	private String qualiName;

	public long getDesQualMpgId() {
		return desQualMpgId;
	}

	public void setDesQualMpgId(long desQualMpgId) {
		this.desQualMpgId = desQualMpgId;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getQualiName() {
		return qualiName;
	}

	public void setQualiName(String qualiName) {
		this.qualiName = qualiName;
	}



	
}
