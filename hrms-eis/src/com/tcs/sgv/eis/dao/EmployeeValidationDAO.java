package com.tcs.sgv.eis.dao;

import java.util.List;

public interface EmployeeValidationDAO {

	public List getTotalEmpConfig();

	public List getDraftForms();

	public List getForwardedForms();

	public List getApprovedForms();

	public List getRejectedForms();

}
