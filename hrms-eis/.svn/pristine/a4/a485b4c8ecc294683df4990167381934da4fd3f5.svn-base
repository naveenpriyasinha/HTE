package com.tcs.sgv.filter;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;



import com.tcs.sgv.acl.acegilogin.event.RequestFailureCrossSiteEvent;
import com.tcs.sgv.acl.acegilogin.event.RequestSecuritySuccessEvent;
import com.tcs.sgv.acl.acegilogin.exception.RequestSecurityFaliureException;
import com.tcs.sgv.acl.acegilogin.filter.MetaCharFilter;




public class XSSFilterImpl  extends MetaCharFilter implements Filter{


	 protected ApplicationEventPublisher eventPublisher;
	  protected MessageSourceAccessor messages;
	  private CommonsMultipartResolver multipartResolver;
	  private static ResourceBundle skipParamResourceBundle = null;
	  private String errorPage;
	  static final String FILTER_APPLIED = "_meta_char_filter_applied";

	  public XSSFilterImpl()
	  {
	    this.messages = SpringSecurityMessageSource.getAccessor();
	  }

	  public void afterPropertiesSet()
	    throws Exception
	  {
	    Assert.notNull(this.multipartResolver, "multipartResolver must be specified");
	  }

	  protected void doFilterHttp(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, FilterChain paramFilterChain)
	    throws IOException, ServletException
	  {
		  
		  logger.info("Inside XSSFilter");
	    if (this.logger.isDebugEnabled()) {
	      this.logger.debug("Meta character filter checking request for cross site script");
	    }

	    if (paramHttpServletRequest.getAttribute("_meta_char_filter_applied") != null)
	    {
	      paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);

	      return;
	    }
	    String str = null;
	    try {
	      if (this.multipartResolver.isMultipart(paramHttpServletRequest)) {
	        MultipartHttpServletRequest localMultipartHttpServletRequest = this.multipartResolver.resolveMultipart(paramHttpServletRequest);
	        str = checkForSecurity(localMultipartHttpServletRequest);
	        paramHttpServletRequest = localMultipartHttpServletRequest;
	      } else {
	        str = checkForSecurity(paramHttpServletRequest);
	      }

	    }
	    catch (RequestSecurityFaliureException localRequestSecurityFaliureException)
	    {
	    	logger.info("Inside catch in filter");
	      if (this.logger.isDebugEnabled())
	        this.logger.debug("Meta character filter found cross site script in " + localRequestSecurityFaliureException.getExtraInformation() + " Parameter");

	      if (this.eventPublisher != null)
	      {
	        this.eventPublisher.publishEvent(new RequestFailureCrossSiteEvent(paramHttpServletRequest, localRequestSecurityFaliureException));
	      }
	      paramHttpServletRequest = null;
	      RequestDispatcher localRequestDispatcher = paramHttpServletRequest.getRequestDispatcher(this.errorPage);
	      localRequestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
	    }
	    if (this.logger.isDebugEnabled())
	      this.logger.debug("Meta character filter not found cross site script in http request");

	    this.eventPublisher.publishEvent(new RequestSecuritySuccessEvent(paramHttpServletRequest));

	    paramHttpServletRequest.setAttribute("_meta_char_filter_applied", Boolean.TRUE);
	    HttpServletResponse res = (HttpServletResponse)paramHttpServletResponse;
	    paramHttpServletResponse.addHeader("X-FRAME-OPTIONS", "DENY" );
	  
	    paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);
	  }

	  public CommonsMultipartResolver getMultipartResolver()
	  {
	    return this.multipartResolver;
	  }

	  public void setMultipartResolver(CommonsMultipartResolver paramCommonsMultipartResolver)
	  {
	    this.multipartResolver = paramCommonsMultipartResolver; }

	  public void setMessageSource(MessageSource paramMessageSource) {
	    this.messages = new MessageSourceAccessor(paramMessageSource);
	  }

	  public void setApplicationEventPublisher(ApplicationEventPublisher paramApplicationEventPublisher)
	  {
	    this.eventPublisher = paramApplicationEventPublisher;
	  }

	  private String checkForSecurity(HttpServletRequest paramHttpServletRequest)
	    throws RequestSecurityFaliureException	  {
		  
		logger.info("Inside our filter");
	    Enumeration localEnumeration = paramHttpServletRequest.getParameterNames();
	    String str1 = null;
	    while (localEnumeration.hasMoreElements())
	    {
	      String str2 = (String)localEnumeration.nextElement();
	      str1 = paramHttpServletRequest.getParameter(str2);
	      logger.info("str1 is ::::::"+str1);
	      logger.info("str2 is ::::::"+str2);
	      
	      if(str1!=null && (str1.contains("<")|| str1.contains(">"))){
	    	  //try {0............
	    		 // str1 = str1.replaceAll("<", "%ld");
	    		  //str1 = str1.replaceAll(">", "%gd");
	    	 // paramHttpServletRequest.ser(str2, null);
	    	  
	    	  logger.info("paramHttpServletRequest1 "+paramHttpServletRequest.getParameter(str2));
	    		  throw new RequestSecurityFaliureException("Cross site script has been found", str1);
			/*} catch (XSSFoundException e) {
				logger.error("Exception is ::::"+e.getMessage());
				setErrorPage("/WEB-INF/jsp/core/webErrorPage.jsp");
			}*/
	      }
	      
	      else if(str2!=null && (str2.contains("<")|| str2.contains(">"))){
	    	  paramHttpServletRequest.setAttribute(str2, null);
	    	  
	    	  logger.info("paramHttpServletRequest2 "+paramHttpServletRequest.getParameter(str2));
	    	  //str2 = str2.replaceAll("<", "%ld");
    		//  str2 = str2.replaceAll(">", "%gd");
    		  throw new RequestSecurityFaliureException("Cross site script has been found", str2);
	      }
	     /* if ((!(skipParamResourceBundle.containsKey(str2))) && (str1 != null) && 
	        (HtmlEncode.isCrossSiteScript(str1)))
	      {
	        this.logger.warn("Security Check is failed... Pls check request. Parameter Name: " + str2 + " Paramenter Value: " + str1);*/
	      }

	    return null;
	  }

	  public void setErrorPage(String paramString)
	  {
	    if ((paramString != null) && (!(paramString.startsWith("/")))) {
	      throw new IllegalArgumentException("errorPage must begin with '/'");
	    }

	    this.errorPage = paramString;
	  }
	  
	  /*class XSSFoundException extends Exception{
		  XSSFoundException()
			{
				super();
				logger.error("XSS Found");
				
			}
	  }*/
}
