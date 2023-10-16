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


	<hdiits:form name = "DepositAccMst" method = "post" validate = "false"
					action="./ifms.htm?actionFlag=createAccMst">
		<hdiits:table align = "center" width = "700px">
			<hdiits:tr>
				<hdiits:td align = "center" colspan = "4"><h2>DEPOSIT ACCOUNT MASTER</h2></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.PDPLA_ACC_NO" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtPdPlaAccNo"/></hdiits:td>
				<hdiits:td><fmt:message key="DEPACCMST.CARDEX_NO" bundle="${pdplaLabels}"></fmt:message>.:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtCardexNo"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="DEPACCMST.GROUP" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtGrp" id = "txtGrp"/></hdiits:td>
				<hdiits:td><fmt:message key="CMN.DEPT" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtDeptName"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.MAJOR_HEAD" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtMajorHead"></hdiits:td>
				<hdiits:td><fmt:message key="CMN.SUB_MAJOR_HEAD" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtSubMajorHead" /></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="CMN.MINOR_HEAD" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtMinorHead"></hdiits:td>
				<hdiits:td><fmt:message key="CMN.SUB_MINOR_HEAD" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtSubHead"></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="DEPACCMST.OP_BAL" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtOpBal"/></hdiits:td>
				<hdiits:td><fmt:message key="DEPACCMST.AG_FD_SENCT_NO" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtAgFdSenctNo"/></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="DEPACCMST.ACC_START_DATE" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtAcStartDate"/><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open('txtAcStartDate',375,570) ></hdiits:td>
				<hdiits:td><fmt:message key="DEPACCMST.AG_FD_SENCT_DATE" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtAgFdSenctDate"/><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open('txtAgFdSenctDate',375,570) ></hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td><fmt:message key="DEPACCMST.ACC_END_DATE" bundle="${pdplaLabels}"></fmt:message>:</hdiits:td>
				<hdiits:td><input type = "text" name = "txtAcEndDate"/><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open('txtAcEndDate',375,570) ></hdiits:td>
			</hdiits:tr>
			<hdiits:tr><br /><br /><br />
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td></hdiits:td>
				<hdiits:td colspan = "2"><input type = "submit" name = "submit" value = "Save"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type = "submit" name = "reset" value = "Cancel"/></hdiits:td>
				<hdiits:td></hdiits:td>
			</hdiits:tr>
		</hdiits:table>
	</hdiits:form>
