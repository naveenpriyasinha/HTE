<%@ include file="../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>

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
	<table cellspacing="5" cellpadding="0" align="center" width="70%">
	  	<tr class="TableHeaderBG"> 
			<td  align="center" class="HLabel" colspan = "4"> 
				<b> DATE WISE ACCOUNT SUMMARY </b> 
			</td>
			
		</tr>
		</table>	
		
	<table cellspacing = "0" cellpadding = "0" align = "center" width = "60%">
		<tr>
			<td>
				From Date:
			</td>
			<td>
				<input type = "text" name = "txtForDt"/><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open('txtForDt',375,570)>
			</td>
			<td>
				To Date:
			</td>
			<td>
				<input type = "text" name = "txtToDt"/><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open('txtToDt',375,570)>
			</td>
		</tr>
		<br /><br />
		<tr>
			<td>
				From PD/PLA Acc No.:
			</td>
			<td>
				<input type = "text" name = "txtFrmPdPlaAccNo"/>
			</td>
			<td>
				To PD/PLA Acc No.:
			</td>
			<td>
				<input type = "text" name = "txtToPdPlaAccNo"/>
			</td>
		</tr>
	</table>
</hdiits:form>