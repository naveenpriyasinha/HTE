package com.tcs.sgv.common.helper;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.utils.DBUtility;


public class SessionHelper {
	private static String LOGIN_DETAILS_VO = "baseLoginVO";
	private static String LOCALE = "locale";
	private static String USER_ID = "userId";	
	
	
	/**
	 * This method returns loginDetails attribute from session
	 * @param request HttpServletRequest
	 * @return LoginDetails
	 */
	
//	public static LoginDetails getLoginDetails(HttpServletRequest request) {
//		return (LoginDetails) request.getSession().getAttribute(LOGIN_DETAILS_VO);		
//	}
	
	/**
	 * This method returns locale attribute from session  
	 * @param request HttpServletRequest
	 * @return String 
	 */
	public static String getLocale(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(LOCALE);
	}
	
	/**
	 * This method returns postId from loginDetails attribute in session
	 * @param request HttpServletRequest
	 * @return Long 
	 */
	public  static Long getPostId(Map inputMap){
		
		
		//LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute(LOGIN_DETAILS_VO);
		LoginDetails loginDetails = (LoginDetails)inputMap.get(LOGIN_DETAILS_VO);
		
		//System.out.println("loginDetailsin Session helper :-"+loginDetails);
		//System.out.println("Post id in Session helper :-"+loginDetails.getLoggedInPost().getPostId());
		if (loginDetails!=null)	
		{
			 return loginDetails.getLoggedInPost().getPostId();
		}
		return null;
	}
/*	public  static Long getPostId(HttpServletRequest request){
		
		
		LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");		
		
		//System.out.println("loginDetailsin Session helper :-"+loginDetails);
		//System.out.println("Post id in Session helper :-"+loginDetails.getLoggedInPost().getPostId());
		if (loginDetails!=null)	
		{
			 return loginDetails.getLoggedInPost().getPostId();
		}
		return null;
	}*/
	
	/**
	 * This method returns userId attribute from session
	 * @param request HttpServletRequest
	 * @return Long 
	 */
	public static Long getUserId(HttpServletRequest request) {		
		Long userId = null;
		try {userId = (Long) request.getSession().getAttribute(USER_ID);} catch(Exception e) {}		
		return userId;
	}
	
	public static String getUserName(HttpServletRequest request) {		
		String userName = null;
		try {userName = (String) request.getSession().getAttribute("name");} catch(Exception e) {}		
		return userName;
	}
	
	/**
	 * This method returns database Id from loginDetails attribute in session
	 * @param request HttpServletRequest
	 * @return Long
	 */
	public static  Long getDbId(Map inputMap) {
		//LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute(LOGIN_DETAILS_VO);
		LoginDetails loginDetails = (LoginDetails)inputMap.get(LOGIN_DETAILS_VO);
		if (loginDetails!=null)	
			 return loginDetails.getDbId();
		return null;

	}
	
/*	public static  Long getDbId(HttpServletRequest request) {
		LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		
		if (loginDetails!=null)	
			 return loginDetails.getDbId();
		return null;

	}*/
	/**
	 * This method returns location Id from loginDetails attribute in session
	 * @param request HttpServletRequest
	 * @return Long
	 */
	public  static Long getLocationId(Map inputMap) {
		//LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute(LOGIN_DETAILS_VO);
		LoginDetails loginDetails = (LoginDetails)inputMap.get(LOGIN_DETAILS_VO);
		if (loginDetails!=null)	
			 return loginDetails.getLocation().getLocId();
		return null;
	}
/*	public  static Long getLocationId(HttpServletRequest request) {
		LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		
		if (loginDetails!=null)	
			 return loginDetails.getLocation().getLocId();
		return null;
	}*/
	
	/**
	 * This method returns location code from loginDetails attribute in session
	 * @param request HttpServletRequest
	 * @return Long
	 */
	public static String getLocationCode(Map inputMap) {
		//LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute(LOGIN_DETAILS_VO);
		LoginDetails loginDetails = (LoginDetails)inputMap.get(LOGIN_DETAILS_VO);
		if (loginDetails!=null)	
			 return loginDetails.getLocation().getLocationCode();
		return null;
	}
	
	/**
	 * This method returns employee id  of logged in user
	 * @param request HttpServletRequest
	 * @return Long 
	 */
	public static Long getEmpId(Map inputMap){
		//LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute(LOGIN_DETAILS_VO);
		LoginDetails loginDetails = (LoginDetails)inputMap.get(LOGIN_DETAILS_VO);
		if (loginDetails!=null)	
			 return loginDetails.getEmployee().getEmpId();
		return new Long(5);
	}
	
	/*public static Long getEmpId(HttpServletRequest request){
		LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		
		if (loginDetails!=null)	
			 return loginDetails.getEmployee().getEmpId();
		return new Long(5);
	}*/
	
	/**
	 * This method returns language id of logged in user
	 * @param request
	 * @return Long
	 */
	public static Long getLangId(Map inputMap) {
		//LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute(LOGIN_DETAILS_VO);
		LoginDetails loginDetails = (LoginDetails)inputMap.get(LOGIN_DETAILS_VO);
		
		if (loginDetails!=null)	
			return loginDetails.getLangId();
		return null;
	}
	/*
	public static Long getLangId(HttpServletRequest request) {
		LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		
		
		if (loginDetails!=null)	
			return loginDetails.getLangId();
		return null;
	}*/
	/**
	 * This method returns userId attribute from session
	 * @param request HttpServletRequest
	 * @return Long 
	 */
	public static Long getUserId(Map inputMap) {		
		LoginDetails loginDetails = (LoginDetails)inputMap.get(LOGIN_DETAILS_VO);
		
		if (loginDetails!=null)	
		{
			 return loginDetails.getUser().getUserId();
		}
		return null;
	}
	
	/**
	 * This method returns Current Date from DBUtility(DataBase)
	 * @param Map
	 * @return Integer
	 */
	public static Date getCurDate() {
		//LoginDetails loginDetails = (LoginDetails) request.getSession().getAttribute(LOGIN_DETAILS_VO);
		Date lDtCurDate = DBUtility.getCurrentDateFromDB();		
		if(lDtCurDate!=null)
			return lDtCurDate;
		else
			return null;
	}
}
