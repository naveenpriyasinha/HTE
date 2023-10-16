<%@page import="com.tcs.sgv.core.service.ServiceLocator"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.tcs.sgv.acl.valueobject.AclUserRoleRlt"%>
<%@page import="com.tcs.sgv.acl.dao.AclUserRoleRltDaoImpl"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import="com.tcs.sgv.acl.service.UserElement"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<c:set var="resultObj"	value="${result}"/>
<c:set var="resultMap" value="${resultObj.resultValue}"/>
<c:set var="userElementsForMenuOnPage" value="${resultMap.userElementsForMenuOnPage}"/>

<hdiits:form name="menuOnPageForm" method="POST" action="hdiits.htm?" encType="multipart/form-data" validate="true">
	
	
	<%
		UserElements userElmntForMenu = (UserElements)pageContext.getAttribute("userElementsForMenuOnPage");
		request.setAttribute("userElementsForMenuOnPage",userElmntForMenu);
		%>
			<table width = "100%">
				<tr>
					<td>
			  			<jsp:include page="/WEB-INF/jsp/core/menuOnPage.jsp">
			  				<jsp:param name="expandCollapseUtilReq" value="true"/>
			  				<jsp:param name="initExpandCollpsStatus"  value=" "/>
			  				<jsp:param name="preferencesRequired" value="false"/>	
			  				<jsp:param name="numberOfColumns" value="4"/>			  						  				
			  			</jsp:include>			  	    
					</td>
				</tr>
			</table>					  		
  		<% 
	%>

</hdiits:form>



	

