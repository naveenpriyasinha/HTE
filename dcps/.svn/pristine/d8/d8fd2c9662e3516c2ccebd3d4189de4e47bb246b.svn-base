<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <!-- For Taglib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<link href="style/iwasstylesheet.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="WebGUIStandards.css">
<link rel="stylesheet" type="text/css" href="TableBorders.css">
<script type="text/javascript">
function validate() 
{
  	document.frmMajorHead.submit();
}	
</script>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
<c:set var="majorHeadList" value="${resValue.majorHeadList}"> </c:set>
<hdiits:form name="frmMajorHead" validate="true" method="post" action="ifms.htm?actionFlag=getDelAccDtls ">
	<table width="90%"  border="1" cellpadding="0" cellspacing="0">
	<tr class="datatableheader"> 
	    <td  align="center">
	    <fmt:message key="CHQDELIVERYACCVITO.TITLE" bundle="${billprocLabels}"></fmt:message>
	     </td>
	</tr>
	<tr>  			  	
		<td>
			<table align="center" cellspacing="0" cellpadding="0" width="40%">
			    
			    <tr height="20">
					<td colspan="2">
					</td>
				</tr>
				<tr align="center">
				<td  align="center">&nbsp;&nbsp;
					<hdiits:caption captionid="CMN.MAJOR_HEAD" bundle="${billprocLabels}"/>:&nbsp;&nbsp;
				</td>
				<td>
                    <hdiits:select   name="cmblist" multiple="true">
                     		<option value="0"><fmt:message key="CHQCOMMON.SELECT" bundle="${billprocLabels}"></fmt:message></option>
					   		<c:forEach var="mList" items="${majorHeadList}" >
				  			<option value="<c:out value="${mList.id}"/>">
				 	 		<c:out value="${mList.desc}"/>
				  			</option>
							</c:forEach>
					</hdiits:select>
				</td>
				</tr>
				<tr height="20">
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">	
						<hdiits:button name="btnGenVito" type="button" value='<%=buttonBundle.getString("COMMON.GENERATEVITO")%>' onclick="validate()"/>	
						<hdiits:button type="button" name="btnClose"  value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="window.location.href='ifms.htm?viewName=homePage'"></hdiits:button>
					</td>
				</tr>
			</table>
	</td>
	</tr>

	
	
</table>
</hdiits:form>
	






	
	
