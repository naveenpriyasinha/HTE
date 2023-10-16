<%@ include file="../../core/include.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.onlinebillprep.CommonLabels" var="onlinebillprepLabels" scope="application"/>
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillprepAlerts" scope="application"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

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
	
	<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"> </script>
	
	<script language="JavaScript" type="text/javascript">
		
	var SELBILLTYPE = "<fmt:message key="SELBILLTYPE" bundle="${onlinebillprepAlerts}"></fmt:message>";
		
		function window_open()
		{			
			if(document.getElementById("cmbBillType").value == "-1" || document.getElementById("cmbBillType").value == "")
			{
				alert(SELBILLTYPE);
			}
			else
			{
				var newWindow;
		    	var height = screen.height - 100;
		    	var width = screen.width;
		    	var urlstring = "ifms.htm?actionFlag=createBillFrmScratch&cmbBillType=" + document.getElementById("cmbBillType").value;
		    	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		    	newWindow = window.open(urlstring, "frmCreateOnlineBill", urlstyle);
		    }			
	    }	
			
	</script>	
</head>

<hdiits:form method="post" name="frmScratchBill"  validate="true" encType="multipart/form-data">
	
	<table align="center" width="90%" class="TableBorderLTRBN">
		<tr class="TableHeaderBG">
			<td align="center" colspan="2" class="HLabel">
				<fmt:message key="SCR.HEADING" bundle="${onlinebillprepLabels}" />
			</td>
		</tr>
		<hdiits:tr>
			<td align="right" class="Label">
				<br /> <fmt:message key="CMN.BILLTYPE" bundle="${onlinebillprepLabels}" /> :
			</td>
			<td align="left" class="Label">
				<br />
				<hdiits:select name="cmbBillType" style="width:700px" size="17"> 
					<option value="-1" selected="selected"> --Select-- </option>
					<c:forEach var="BillType" items="${resValue.BillType}">
						<option value="${BillType.commonId}"> <c:out value="${BillType.commonDesc}"/> </option>
					</c:forEach>
				</hdiits:select>
			</td>
		</hdiits:tr>
		<hdiits:tr>
			<td align="center" colspan="2" class="Label">
				<br /> 
					<hdiits:button type="button" name="butSubmit" value=" Create Bill " onclick="window_open()"/>
				<br />
			</td>
		</hdiits:tr>
	</table>
	
</hdiits:form>


