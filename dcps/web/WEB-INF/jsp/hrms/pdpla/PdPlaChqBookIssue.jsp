<%@ include file="../core/include.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.pdpla.PdPlaLabels" var="pdplaLabels" scope="application"/>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title> Search Request </title>
	 
	<link rel="stylesheet" href="/web/themes/hdiits/hdiits.css" type="text/css" />
	<link rel="stylesheet" href="/web/themes/hdiits/WebGUIStandards.css" type="text/css" />
	 	 
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
	<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
	<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
	<script type="text/javascript" src="script/common/commonfunctions.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>	
	<script type="text/javascript" src="script/onlinebillprep/Common.js"> </script>
</head>

			<hdiits:table align = "center" width = "700px">		
				<hdiits:tr>
					<hdiits:td align = "center" colspan = "4"><h2>PD/PLA Cheque Book Issue</h2></hdiits:td>
				</hdiits:tr>
			</hdiits:table>
		<hdiits:form name = "PdPlaRcpt" method = "post" validate = "false">
			<hdiits:table align = "center" width = "700px">
				<hdiits:tr>
					<hdiits:td><fmt:message key="CHQBOOK.ISSUE_DATE" bundle="${pdplaLabels}"></fmt:message>Issue Date:</hdiits:td>
					<hdiits:td><input type = "text" name = "txtDateRcpt"/><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open('txtDateRcpt',375,570) ></hdiits:td>
				</hdiits:tr>
				<hdiits:tr>
					<hdiits:td><fmt:message key="CMN.PDPLA_ACC_NO" bundle="${pdplaLabels}"></fmt:message>PD/PLA A/c No.:</hdiits:td>
					<hdiits:td><input type = "text" name = "txtPdPlaAccNo"/></hdiits:td>
					<hdiits:td><fmt:message key="CHQBOOK.DESC" bundle="${pdplaLabels}"></fmt:message>Description:</hdiits:td>
					<hdiits:td></hdiits:td>
				</hdiits:tr>
				<hdiits:tr>
					<hdiits:td><fmt:message key="CHQBOOK.START_CHQ_SERIES" bundle="${pdplaLabels}"></fmt:message>Starting Cheque Series:</hdiits:td>
					<hdiits:td><input type = "text" name = "txtStartChqSr"/></hdiits:td>
					<hdiits:td><fmt:message key="CHQBOOK.END_CHQ_SERIES" bundle="${pdplaLabels}"></fmt:message>Ending Cheque Series:</hdiits:td>
					<hdiits:td><input type = "text" name = "txtEndChqSr"/></hdiits:td>
				</hdiits:tr>
				<hdiits:tr>
					<hdiits:td><fmt:message key="CHQBOOK.NO_OF_CHQ" bundle="${pdplaLabels}"></fmt:message>No. of Cheques:</hdiits:td>
					<hdiits:td><input type = "text" name = "txtNoOfChq"/></hdiits:td>
				</hdiits:tr>
			</hdiits:table>
		</hdiits:form>
		<br />
			<hdiits:table align = "center" width = "700px">
				<hdiits:tr>
					<th>&nbsp;</th>
					<th>PD/PLA A/c No.</th>
					<th>Issue Date</th>
					<th>Starting Cheque Series</th>
					<th>Ending Cheque Series</th>
				</hdiits:tr>
				<hdiits:tr>
					<hdiits:td>
						<input type = "checkbox" name = "c1"  id = "c1" onclick = "func1()">
					</hdiits:td>
					<hdiits:td></hdiits:td>
					<hdiits:td></hdiits:td>
					<hdiits:td></hdiits:td>
					<hdiits:td></hdiits:td>
				</hdiits:tr>
			</hdiits:table>
			<hdiits:table>
				<hdiits:tr>
					<hdiits:td align = "right">
						<input type = "submit" name = "submit" value = "Save"/> 
					</hdiits:td>
					<hdiits:td>
						<input type = "submit" name = "submit" value = "Close"/>
					</hdiits:td>
				</hdiits:tr>
			</hdiits:table>
		

	