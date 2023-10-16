<%@ include file="include.jsp" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import="com.tcs.sgv.acl.service.UserElement"%>
<%@page import=" org.apache.commons.logging.Log"%>
<%@ page import="org.apache.commons.logging.LogFactory"%>

<%@page import="com.tcs.sgv.acl.constants.Constant"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" href='<c:url value="/themes/${themename}/rightmenu.css"/>' type="text/css" />

<%
Log logger = LogFactory.getLog(getClass());
try
{
    	
    	ResultObject result=(ResultObject)request.getAttribute("result");
   		Map resultMap=(Map)result.getResultValue();
	   	logger.info("In navigation.jsp");
	  	if(resultMap!=null)
	   	{ 
	    	UserElements userElements= (UserElements)resultMap.get("navigationUserElements");
	        if(userElements!=null)
	        {
	%>
	        	<script type="text/javascript" >
	          	var mainMenu=new Array();
	          	mainMenuLink=new Array();
	          	toolTipMsg = new Array();
	          	hrefForModule = new Array();
	         	</script> 
	          	<script type="text/javascript" >
	        
	       		<% 	
	       		logger.info("User elements object in navigation"+userElements);
	    		String menuString = userElements.getMenuString();
	    		
				int totalMainMenus=userElements.getMainMenusCount();
				UserElement userElement= null;	
				out.println("var totalMainMenus ="+totalMainMenus);		 	 	
		 		logger.info("Total main menus in navigation---> "+totalMainMenus);
				out.println(menuString);
					
		 		%>
		 		</script> 
	    		<script type="text/javascript" src='<c:url value="/script/common/rightMenu.js"/>'></script>
<!-- 	   			<script type="text/javascript" src="<c:url value="/script/common/printRightMenu.js"/>"></script> -->
	
	<table id="mainmenutable" class="menutable">
		<script language="JavaScript">
			displayRightMenuValue(firstLevelChildIndex,mainMenu,mainMenuLink,toolTipMsg,hrefForModule);
		</script> 	
	</table>

	      <%}
	   		else
	   		{
		       logger.info("user element object is null navigation jsp");
	   		}
	   }
	   else
	   {
		   logger.info("result object is null in navigation jsp ");
	   }
	logger.info("End of navigation jsp");
}catch(Exception e)
{
	logger.error("Error in navigation jsp",e);
	e.printStackTrace();
}
%>
	

