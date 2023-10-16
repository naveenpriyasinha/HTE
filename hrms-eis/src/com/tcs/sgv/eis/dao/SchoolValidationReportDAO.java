package com.tcs.sgv.eis.dao;

import java.util.List;

public interface SchoolValidationReportDAO {

	public List getTotalSchoolsConfig();

	public List getApprovedSchools();

	public List getPendingSchools();

	public List getRejectedSchools();

	public List getDataEntryInitiatedSchools();

}
