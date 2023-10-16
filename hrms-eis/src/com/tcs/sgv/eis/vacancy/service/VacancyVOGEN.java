package com.tcs.sgv.eis.vacancy.service;

import java.util.Map;

import com.tcs.sgv.core.service.Service;
import com.tcs.sgv.core.valueobject.ResultObject;

public interface VacancyVOGEN extends Service{

	String USER_ID="userId";
	String LOGGEDINPOST="loggedInPost";
	String LANG_ID="langId";
	String DB_ID="dbId";
	String EMP_ID="EmpId";
	String POST_ID="postId";
	String REQUEST_OBJ="requestObj";
	String SERVICE_LOCATOR="serviceLocator";
	
	ResultObject getRequestData(Map objectArgs);
	
	ResultObject getDesignationforVacancy(Map objectArgs);
}
