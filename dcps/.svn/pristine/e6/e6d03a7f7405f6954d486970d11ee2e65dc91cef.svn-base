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
				<hdiits:td align = "center" colspan = "4"><h2>PD/PLA Receipt</h2></hdiits:td>
			</hdiits:tr>
		</hdiits:table>
	<hdiits:form name = "PdPlaRcpt" method = "post" validate = "false" action="./ifms.htm?actionFlag=enterRcptDetl">
		<hdiits:table align = "center" width = "700px">
			<hdiits:tr>
				<hdiits:td><fmt:message key="RECEIPT.DATE_RECEIPT" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtDateRcpt" id="id_txtDateRcpt"/><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open('txtDateRcpt',375,570)></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.TRANS_TYPE" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><select type = "text" name = "txtTransType"/>
					<option value="-1" selected>--select--</option>
					<option value="Standard">Standard</option>
					<option value="TC">TC</option>
					<option value="Adjustment">Adjustment</option></select></hdiits:td>
				<hdiits:td><fmt:message key="CMN.INTERNAL_TC" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td>
					<select type = "text" name = "txtIntTc"/>
					<option value="Yes" selected>Yes</option>
					<option value="No">No</option></select></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.DETAIL_HEAD" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtDetHead"/></hdiits:td>
				<hdiits:td><fmt:message key="CMN.FD_AG_CODE" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtFdAgCode"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.PDPLA_ACC_NO" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtPdPlaAccNo"  id="PdPlaAccNo"/>
					
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.MAJOR_HEAD" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td id="MajorHead"><input type = "text" name = "txtMajorHead" id="id_txtMajorHead"/></hdiits:td>
				<hdiits:td><fmt:message key="CMN.MINOR_HEAD" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td id="MinorHead"><input type = "text" name = "txtMinorHead" id="id_txtMinorHead"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.DEPT" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td id="DeptName"><input type = "text" name = "txtDept" id = "id_txtDeptName"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.NARRATION" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtNarr"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="RECEIPT.CHALLAN_NO" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtChallanNo" id = "id_txtChallanNo"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.AMMOUNT" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtAmount"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr><br /><br /><br />
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td></hdiits:td>
				<hdiits:td colspan = "2">
					<input type = "submit" name = "submit" value = "Save" size = "20"/>&nbsp;&nbsp;&nbsp;
					<input type = "submit" name = "reset" value = "Cancel" size = "20"/>&nbsp;&nbsp;&nbsp;
					<input type = "submit" name = "reset" value = "Retrieve" size = "20"/>
				</hdiits:td>
				<hdiits:td></hdiits:td>
			</hdiits:tr>
		</hdiits:table>
</hdiits:form>

<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getHeadDetail" 
	   		action="PdPlaAccNo"  source="PdPlaAccNo"
	   		target="id_txtMajorHead,id_txtMinorHead,id_txtDeptName" 
	   		eventType="blur"
	   		parameters="pdPlaNo={PdPlaAccNo}"></ajax:updateField>	
	   	
<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getChallanSeq" 
	   		action="txtDateRcpt"  source="id_txtDateRcpt"
	   		target="id_txtChallanNo"
	   		eventType="blur"
	   		parameters="dateRcpt={id_txtDateRcpt}"></ajax:updateField>
		   	
