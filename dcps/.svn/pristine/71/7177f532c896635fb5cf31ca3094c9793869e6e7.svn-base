<%@ include file="../../core/include.jsp" %>

<%@ page import="java.text.SimpleDateFormat,java.util.Calendar,com.tcs.sgv.core.valueobject.ResultObject"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.onlinebillprep.CommonLabels" var="onlinebillprepLabels" scope="application"/>
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillprepAlerts" scope="application"/>
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

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
	<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"> </script>
	
	<script type="text/javascript">
	
		var minYear=1900;
		var maxYear=2500;
		var SRCH_DTFORMAT = "<fmt:message key="SRCH.DTFORMAT" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_VALMNTH = "<fmt:message key="SRCH.VALMNTH" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_VALDAY = "<fmt:message key="SRCH.VALDAY" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_VALDIGIT = "<fmt:message key="SRCH.VALDIGIT" bundle="${onlinebillprepAlerts}"></fmt:message>"+minYear+"<fmt:message key="SRCH.AND" bundle="${onlinebillprepAlerts}"></fmt:message>"+maxYear;
		var SRCH_VALDT = "<fmt:message key="SRCH.VALDT" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_PROYR = "<fmt:message key="SRCH.PROYR" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_PROMNTH = "<fmt:message key="SRCH.PROMNTH" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCH_PRODAY = "<fmt:message key="SRCH.PRODAY" bundle="${onlinebillprepAlerts}"></fmt:message>";
		
	 </script> 
	 	
</head>
<hdiits:form name="frmSearch" validate="true" method="post" action="./ifms.htm?actionFlag=getApprovedRequest">
	<br /><br />
	
	<table width="85%" align="center" border=1 RULES=NONE FRAME=BOX>
		<tr class="TableHeaderBG">
			<hdiits:td align="center" colspan="4">
				<b><fmt:message key="SRCH_APRVD_RQST" bundle="${onlinebillprepLabels}"></fmt:message></b>
			</hdiits:td>
		</tr>
		<hdiits:tr>
			<%
				String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
				request.setAttribute("dateTime",dateTime);
			%>
			<hdiits:td align="left" width="50%">
				&nbsp;&nbsp; <fmt:message key="FRM_DT" bundle="${onlinebillprepLabels}"></fmt:message>
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
				 <b> : </b>
				 &nbsp;&nbsp;
				 <input type="text" name="txtFrmDt" value="" maxlength="10" size="10"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtFrmDt",375,570)' >
			</hdiits:td>
			<hdiits:td align="left" width="50%">
				 <fmt:message key="TO_DT" bundle="${onlinebillprepLabels}"></fmt:message>
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <b> : </b>
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				 <input type="text" name="txtToDt" value="" maxlength="10" size="10"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtToDt",375,570)' >
			</hdiits:td>
		</hdiits:tr >
		<hdiits:tr>
			<hdiits:td align="left" colspan="2">
				 &nbsp;&nbsp; <fmt:message key="CMN.BILLTYPE" bundle="${onlinebillprepLabels}"></fmt:message> 
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b> : </b>
				&nbsp;&nbsp;
				<hdiits:select name="cmbBillType" captionid="drop_down" validation="sel.isrequired"  mandatory="false">
					<hdiits:option value="-1"> --Select-- </hdiits:option>
					<c:forEach var="billTypeValue" items="${resValue.ResultList}" varStatus="No"> 
	           	 		<hdiits:option value='${billTypeValue.commonId}'> <c:out value='${billTypeValue.commonDesc}' /> </hdiits:option>
	            	</c:forEach>
	 			</hdiits:select >
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr>
			<hdiits:td align="left" colspan="2">
				&nbsp;&nbsp; <fmt:message key="DEPT" bundle="${onlinebillprepLabels}"></fmt:message>
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
				 <b> : </b>
				 &nbsp;&nbsp;
				<hdiits:select name="cmbDept" captionid="drop_down" validation="sel.isrequired"  mandatory="false">
					<hdiits:option value='-1'> --Select-- </hdiits:option>
					<c:forEach var="deptValue" items="${resValue.DDOLocList}" varStatus="No"> 
						<hdiits:option value='${deptValue.commonId}'> <c:out  value='${deptValue.commonDesc}' /> </hdiits:option>
	            	</c:forEach>
	 			</hdiits:select>
			</hdiits:td >
		</hdiits:tr>
		<hdiits:tr>
			<hdiits:td align = "left" colspan="2">
				&nbsp;&nbsp; <fmt:message key="EMPLOYEE_DESGN" bundle="${onlinebillprepLabels}"></fmt:message>
				&nbsp;&nbsp;&nbsp;
			  	 <b> : </b> 
			   	&nbsp;&nbsp;
				<input type="text" name="txtEmpDsgn" />
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr>
			<hdiits:td align="left" colspan="2">
				&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.EMP.TYPE" bundle="${onlinebillprepLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<b> : </b> &nbsp;&nbsp;
				<hdiits:select name="cmbEmpType" captionid="drop_down" validation="sel.isrequired"  mandatory="false">
					<hdiits:option value="-1"> --Select-- </hdiits:option>
					<c:forEach var="empType" items="${resValue.EmpTypeList}"> 
						<hdiits:option value='${empType.lookupDesc}'> <c:out value='${empType.lookupDesc}'/> </hdiits:option>
					</c:forEach>
					</hdiits:select>
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr>
			<hdiits:td colspan="2" align="center">
				
				<hdiits:button name="btnSearch" type="button" onclick="validateForm_frmSearch()"  value=" Search "/>
			</hdiits:td>
		</hdiits:tr>
	</table>
</hdiits:form>