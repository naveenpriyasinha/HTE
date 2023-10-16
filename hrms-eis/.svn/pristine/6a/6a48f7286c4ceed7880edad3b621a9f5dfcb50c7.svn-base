package com.tcs.sgv.filter;


import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ibm.db2.jcc.am.in;
import com.tcs.sgv.acl.acegilogin.exception.RequestSecurityFaliureException;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class CaptchaValidate extends XSSFilterImpl implements Filter{
	protected ApplicationEventPublisher eventPublisher;
	protected MessageSourceAccessor messages;
	private CommonsMultipartResolver multipartResolver;
	private static ResourceBundle skipParamResourceBundle = null;
	private String errorPage;
	private String dashboardPage;
	static final String FILTER_APPLIED = "_dashboard_filter";

	public CaptchaValidate()
	{
		this.messages = SpringSecurityMessageSource.getAccessor();
	}

	public void afterPropertiesSet()throws Exception
	{
		Assert.notNull(this.multipartResolver, "multipartResolver must be specified");
	}
	@SuppressWarnings("null")
	protected void doFilterHttp(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, FilterChain paramFilterChain)
	throws IOException, ServletException
	{

		logger.info("Inside Dashboard");

		if (paramHttpServletRequest.getAttribute("_dashboard_filter") != null)
		{
			paramHttpServletRequest.setAttribute("_dashboard_filter", Boolean.TRUE);
			paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);

			return;
		}
		try {
			Boolean flag = checkForSecurity(paramHttpServletRequest);
			if(flag){
				logger.info("before request dispatcher for dashboard");
				RequestDispatcher localRequestDispatcher = paramHttpServletRequest.getRequestDispatcher("/login.jsp");
				localRequestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
				logger.info("after request dispatcher for dashboard");
			}

			else{
				paramHttpServletResponse.addHeader("X-FRAME-OPTIONS", "DENY" );
				paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);
			}
		}
		catch (Exception e)
		{
			logger.info("Inside catch in filter");

			e.printStackTrace();
			paramHttpServletRequest = null;
			RequestDispatcher localRequestDispatcher = paramHttpServletRequest.getRequestDispatcher(this.errorPage);
			localRequestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
		}
	}

	private boolean checkForSecurity(HttpServletRequest paramHttpServletRequest)
	throws RequestSecurityFaliureException, ParseException	  {
		Boolean flag =false;
		logger.info("Inside our filter for dashboard");
		Enumeration localEnumeration = paramHttpServletRequest.getParameterNames();
		String str=null;
		String str1=null;
		String executeFlag=null;
		//String chartType=null;
		String captcha=(String)paramHttpServletRequest.getSession().getAttribute("captcha123");//from system
		String captcha_code=null;//from user
		String userName = null;
		String pwd = null;
		
		
		while (localEnumeration.hasMoreElements())
		{
			str=(String)localEnumeration.nextElement();
			str1 = paramHttpServletRequest.getParameter(str);
			logger.info("str1 is ::::::"+str1);
			logger.info("str is ::::::"+str);

			if(str!= null && !str.equals("") && str.equals("viewName") && str1.equals("captchaRedirect")){
				flag = true;
			}
			if(str!= null && !str.equals("") && str.equals("executeFlag")){
				executeFlag = str1;
				logger.info("executeFlag"+executeFlag);
			}
			if(str!= null && !str.equals("") && str.equals("captcha_code")){
				captcha_code = str1;
				logger.info("captcha "+captcha_code);
			}	
			if(str!= null && !str.equals("") && str.equals("username")){
				userName = str1;
				logger.info("userName "+userName);
			}	
			if(str!= null && !str.equals("") && str.equals("password")){
				pwd = str1;
				logger.info("pwd "+pwd);
			}	
		}
		logger.info("from system: "+captcha);
		logger.info("from user: "+captcha_code);
		ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();
		
		if(executeFlag!= null && !executeFlag.equals("") && executeFlag.equals("1"))
		{
			//all code goes here
			
			logger.info("captcha code: "+captcha);
			if(captcha==null)
				captcha=" ";
			if(captcha.equals(captcha_code))
			{
				paramHttpServletRequest.setAttribute("captchaValidateFlag", "Y");
				paramHttpServletRequest.setAttribute("userName", userName);
				paramHttpServletRequest.setAttribute("pwd", pwd);
			}
			else
			{
				paramHttpServletRequest.setAttribute("captchaValidateFlag", "N");
				paramHttpServletRequest.setAttribute("userName", userName);
				paramHttpServletRequest.setAttribute("pwd", pwd);
			}
			
			
		}
		
		else
		{
			logger.info("Called from outside!");
		}


		return flag;
	}
	public CommonsMultipartResolver getMultipartResolver()
	{
		return this.multipartResolver;
	}

	public void setMultipartResolver(CommonsMultipartResolver paramCommonsMultipartResolver)
	{
		this.multipartResolver = paramCommonsMultipartResolver; 
	}
	public void setMessageSource(MessageSource paramMessageSource) {
		this.messages = new MessageSourceAccessor(paramMessageSource);
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher paramApplicationEventPublisher)
	{
		this.eventPublisher = paramApplicationEventPublisher;
	}

	public void setErrorPage(String paramString)
	{
		if ((paramString != null) && (!(paramString.startsWith("/")))) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = paramString;
	}

	public String getDashboardPage() {
		return dashboardPage;
	}

	public void setDashboardPage(String dashboardPage) {
		if ((dashboardPage != null) && (!(dashboardPage.startsWith("/")))) {
			throw new IllegalArgumentException("dashboardPage must begin with '/'");
		}
		this.dashboardPage = dashboardPage;
	}






}
