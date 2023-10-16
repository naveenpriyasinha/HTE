<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import=" org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<style>
	#currentApplication{display:none;}
</style>
<% 
	Log logger = LogFactory.getLog(getClass());
	ResultObject result=(ResultObject)request.getAttribute("result");
 	Map resultMap=(Map)result.getResultValue();

	UserElements userElements = (UserElements)resultMap.get("homePageUserElements");
	if(userElements!=null)
   	{
   		//For managing user menu preferences		
		List divStatusLt = (List) resultMap.get("divStatusLt");	        			
   			
		request.setAttribute("divStatusLt",divStatusLt);							  	    
	  	request.setAttribute("userElementsForMenuOnPage",userElements);
 %>
	<jsp:include page="/WEB-INF/jsp/core/menuOnPage.jsp">
		<jsp:param name="preferencesRequired" value="true" />
		<jsp:param name="expandCollapseUtilReq" value="true" />
		<jsp:param name="initExpandCollpsStatus" value="" />
		<jsp:param name="numberOfColumns" value="4" />
	</jsp:include>
<%
	}
	else
	{
		logger.debug("user element object is null for menu on page ");
	}
%>
