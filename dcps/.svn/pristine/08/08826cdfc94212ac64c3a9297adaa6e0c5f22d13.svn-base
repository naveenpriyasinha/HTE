<%@ include file="../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.pdpla.PdPlaReports" var="pdplaReports" scope="application"/>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
	<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
	<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
	<script type="text/javascript" src="script/common/commonfunctions.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>	
	
</head>

<hdiits:form name="rqstForm" validate="true" method="post">
		<br /><br /><br />
	<table cellspacing="5" cellpadding="0" align="center" width="60%">
	  	<tr class="TableHeaderBG"> 
			<td  align="center" class="HLabel" colspan = "4"> 
				<b> R/D HEAD WISE SUMMARY REPORT</b> 
			</td>
			
		</tr>
		</table>	
		
	<table cellspacing = "0" cellpadding = "0" align = "center" width = "60%">
		<tr>
			<td>
				<fmt:message key="CMN.FOR_DATE" bundle="${pdplaReports}"></fmt:message>:
			</td>
			<td>
				<input type = "text" name = "txtForDt"/><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open('txtForDt',375,570)>
			</td>
		</tr>
		<br /><br />
		<tr>
			<td>
				<fmt:message key="CMN.FROM_MAJOR_HEAD" bundle="${pdplaReports}"></fmt:message>:
			</td>
			<td>
				<input type = "text" name = "txtFrmPdPlaAccNo"/>
			</td>
			<td>
				<fmt:message key="CMN.TO_MAJOR_HEAD" bundle="${pdplaReports}"></fmt:message>:
			</td>
			<td>
				<input type = "text" name = "txtToPdPlaAccNo"/>
			</td>
		</tr>
		<tr>
			<td>
				<fmt:message key="CMN.PAYMENT_RECEIPT" bundle="${pdplaReports}"></fmt:message>:
			</td>
			<td>
				<select name = "txtPayRcpt">
					<option value = "Deposits">Deposits</option>
					<option value = "Refunds">Refunds</option>
				</select>
			</td>
		</tr>
	</table>
</hdiits:form>