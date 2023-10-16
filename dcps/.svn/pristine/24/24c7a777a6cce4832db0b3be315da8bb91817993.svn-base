<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import="com.tcs.sgv.acl.service.UserElement"%>
<%@page import=" org.apache.commons.logging.Log"%>
<%@ page import="org.apache.commons.logging.LogFactory"%>			
<%@page import="java.util.ArrayList"%>

<!--[if gte IE 6]>
	<link rel="stylesheet" href="<c:url value="/themes/${themename}/ie7fix.css"/>">
<![endif]-->
<%
Log logger = LogFactory.getLog(getClass());
try
{
		ResultObject result=(ResultObject)request.getAttribute("result");
   		Map resultMap=(Map)result.getResultValue();
	   	logger.debug("In mainNavigation.jsp");
	   	
	   	boolean preferencesRequired = true;
	  	if(resultMap!=null)
	   	{ 
	    	UserElements userElements= (UserElements)resultMap.get("navigationUserElements");
	        if(userElements!=null)
	        {
	        	logger.debug("User elements object in homepage ---> "+userElements);
	    		String menuString = userElements.getMenuString();
	    		
	    		List firstLevelModuleIndex = userElements.getRootChildElements();
	    		List elementList = userElements.getAllElements();
	    		
	    		List<UserElement> firstLevelModules = new ArrayList();	    		
				int totalMainMenus=userElements.getMainMenusCount();
				UserElement userElement= null;	
		 		logger.debug("Total main menus in mainNavigation.jsp ---> "+totalMainMenus);
		 		UserElement tempElement;
		 		Integer moduleIndex;
		 		
		 		StringBuffer finalUlLiString = new StringBuffer();
				StringBuffer testString = new StringBuffer();
		 		Integer elementIndex;
				
					finalUlLiString.append("<div  id=\"mlmenu\" class=\"mlmenu horizontal themeclrHorizontal arrow inaccesible\">");
					finalUlLiString.append("<ul>");
					
					for(int k=0;k< firstLevelModuleIndex.size();k++)
					{
						elementIndex = (Integer)firstLevelModuleIndex.get(k);
						finalUlLiString.append(userElements.getUlLiStringForElement(elementIndex,preferencesRequired));
					}										
					finalUlLiString.append("</ul></div>");
					out.print(finalUlLiString);						
			}	
	   		else
	   		{
		       logger.debug("user element object is null  ");
	   		}
	   }
	   else
	   {
		   logger.debug("result object is null");
	   }
	logger.debug("end of mainNavigation.jsp");
}catch(Exception e){
	logger.error("error in mainNavigation.jsp",e);
	e.printStackTrace();
}
%>

