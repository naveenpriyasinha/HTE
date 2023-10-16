package com.tcs.sgv.common.dao;
import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.valueobject.EmpPostVO;

public interface PostConfigurationDAO {
	
	public EmpPostVO getSelectedEmp(String office_code, String branch_code, Map mpArg);
	public List getEmpsList(String office_code, String branch_code);
}
