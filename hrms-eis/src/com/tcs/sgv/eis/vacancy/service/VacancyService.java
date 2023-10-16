package com.tcs.sgv.eis.vacancy.service;

import java.util.Map;

import com.tcs.sgv.core.service.Service;
import com.tcs.sgv.core.valueobject.ResultObject;

public interface VacancyService extends Service{

	String LANG_ID="langId";
	String USER_ID="userId";	
	String LOGGEDINPOST = "loggedInPost";
	String DB_ID="dbId";
	String POST_ID = "postId";
	String LOCATION_ID="locationId";
	
	ResultObject getYear(Map objectArgs);
	
	ResultObject getQuarter(Map objectArgs);
	
	ResultObject getMonth(Map objectArgs);
	
	ResultObject getVacancyAdminComboFill(Map objectArgs); 
	
	ResultObject getLocationlist(Map objectArgs);
	
	ResultObject getDsgnTable(Map objectArgs);
	
	ResultObject saveVacancyData(Map objectArgs);
}
