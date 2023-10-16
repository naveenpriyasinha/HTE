<%@ include file="../../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="DdoHeadDtls" scope="request" value="${result.resultValue.DDOHeadDtls}"></c:set>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
		.tabstyle 
		{
			border-width: 5px 1px 1px 1px; 
			border-color: #2065c0;
			border-style: solid;
		}
		legend 
		{
			padding-left:5px;
			padding-right:5px;
			font-weight:normal; 
			font-family:verdana;
				
			border-width: 0px 0px 1px 0px; 
			border-color: #2065c0;
			border-style: solid;
		}	
	</style>
	<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"> </script>
</head>

<form name="frmDdoHeadDtlsPopup">
	<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> <fmt:message key="DDO.POPUP" bundle="${onlinebillprepLabels}"></fmt:message> </b> </legend>
		<table id="ddoHeadDtls" align="center" width="100%">
			<hdiits:tr>
				<td colspan="6" width="100%">
					<br />
				</td>
			</hdiits:tr>
			<tr class="datatableheader">
				<td align="center" class="HLabel">
					<b> <fmt:message key="CMN.SELECT" bundle="${onlinebillprepLabels}"></fmt:message></b>				
				</td>
				<td align="center" class="HLabel">
					<b> <fmt:message key="CMN.HEADSTR" bundle="${onlinebillprepLabels}"></fmt:message></b>				
				</td>
				<td align="center" class="HLabel">
					<b> <fmt:message key="BUD.SCHNO" bundle="${onlinebillprepLabels}"></fmt:message></b>				
				</td>
				<td align="center" class="HLabel">
					<b> <fmt:message key="CMN.SUBHDDESC" bundle="${onlinebillprepLabels}"></fmt:message></b>				
				</td>
			</tr>
			<c:forEach var="ArrDDoHead" items="${DdoHeadDtls}" varStatus="No">
				<tr class="odd">
					<td align="center" class="Label">
						<input id="RedSelect${No.count}" type="radio" name="Select" value="${ArrDDoHead[0]}~${ArrDDoHead[2]}~${ArrDDoHead[3]}~${ArrDDoHead[4]}~${ArrDDoHead[5]}~${ArrDDoHead[6]}" onclick="setValue(${No.count})"/>
					</td>
					<td align="center" class="Label">
						<c:out value="${ArrDDoHead[2]}:${ArrDDoHead[3]}:${ArrDDoHead[4]}:${ArrDDoHead[5]}:${ArrDDoHead[6]}"></c:out>
					</td>
					<td align="center" class="Label">
						<c:out value="${ArrDDoHead[0]}"></c:out>
					</td>
					<td align="center" class="Label">
						<c:out value="${ArrDDoHead[1]}"></c:out>
					</td>
				</tr>
			</c:forEach>
			<hdiits:tr>
				<hdiits:td align="center" colspan="6">
					<br />
					<hdiits:button type="button" name="butSubmit" value=" Submit " onclick="window_close()"/>				
				</hdiits:td>
			</hdiits:tr>
		</table>
	</fieldset>		
</form>

