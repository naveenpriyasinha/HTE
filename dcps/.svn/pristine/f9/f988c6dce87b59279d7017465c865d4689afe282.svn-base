
<%
	try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="ReportingDDO" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="approveList" value="${resValue.approveList}"></c:set>
<c:set var="size" value="${resValue.size}"></c:set>



<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

<script type="text/javascript">
function approveConsPayBill() {

	var len = '${size}';
	var isChecked = false;
	for (var i = 0; i < len; i++) {
		if (document.getElementById("billid"+i).checked) {
			var billNo=document.getElementById("billid"+i).value;
			isChecked = true;
			break;

		}

	}
	
	if (!isChecked) {
		alert("Please select atleast one Consolidated Paybill to Approve.");
		return false;
	}

	else {
		var answer = confirm('Do you want to approve the Consolidated Bill?');
		if (answer) {
			document.frmPaybill.action="ifms.htm?actionFlag=approveConsolidatedBill&billNo="+billNo;
			document.frmPaybill.submit();
			showProgressbar("Please wait<br>While approving Consolidated Bill ...");
		} else {
			return false;
		}return true;
	}
	//return true;
}
</script>
</head>
<div id="tabmenu" style="width: 150%">
<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b>Approve
	consolidated Pay Bills</b></a></li>
</ul>
</div>
<br />
<div id="tcontent1"
	style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

<hdiits:form name="frmPaybill" validate="true" method="post">

	<table width="100%" border="1">

		<tr>
			<td align="center"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
			<b>Select</b></td>
			<td align="center"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
			<b>Consolidated Bill Id</b></td>
			<td align="center"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Scheme
			Code</b></td>
			
			<td align="center"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Sub Scheme
			Code</b></td>
			<td align="center"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Gross
			Amount</b></td>
			<td align="center"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Net
			Amount</b></td>
			<td align="center"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Status</b></td>
		</tr>
			<%int i=0; %>
		<c:forEach var="entry" items="${approveList}">
		
			<tr>

				<td><input type="radio" name="billid" id="billid<%=i%>"
					value="${entry[0]}"/></td>
				<td><a
					href="ifms.htm?actionFlag=getConsolidatedReport&billId=${entry[0]}">${entry[0]}</a>

				</td>

				<td><c:out value='${entry[1]}' /></td>
				<td><c:out value='${entry[5]}' /></td>
				<td><c:out value='${entry[2]}' /></td>
				<td><c:out value='${entry[3]}' /></td>
				<td><c:if
					test="${entry[4]== '0' || entry[4]== '2' || entry[4]== '3'}">
	Pending
</c:if> <c:if test="${entry[4]== '4' }">
	Rejected
</c:if> <c:if test="${entry[4]== '1' }">
	Approved
</c:if>
<c:if test="${entry[4]== '5' }">
	Approved from BEAMS
</c:if>
</td>


			</tr>
			<%i++; %>
		</c:forEach>


	</table>
	<table align="center">
		<tr align="center">
			<%--<td colspan="3" align="right">
	<hdiits:button name="delete"
				id="delete" type="button" value="Delete"
				onclick="deleteConsPayBill();" /></td>--%>
			<td align="center"><hdiits:button type="button" name="approve"
				id="approve" value="Approve" onclick="approveConsPayBill();" /></td>
		</tr>
	</table>
</hdiits:form></div>



<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	initializetabcontent("maintab");

	function CloseScreen() {
		var urlstyle = "ifms.htm?actionFlag=validateLogin";
		document.forms[0].action = urlstyle;
		document.forms[0].submit();
	}

	function searchConsPayBill() {
		var url = "ifms.htm?actionFlag=viewConsolidatedBill&flag='yes'";
		document.frmPaybill.action = "";
		document.frmPaybill.submit();
	}

	function deleteConsPayBill() {

		if (document.getElementById("billid").checked == "") {
			alert("Please select biill group to delete");
			return false;
		}

		var answer = confirm('Do you want to delete the Consolidated Bill?');
		if (answer) {
			var url = "fms.htm?actionFlag=deleteconsBillGenerated";
			document.forms[0].actiom = url;
			document.forms[0].submit();
			showProgressbar("Please wait<br>While deleting Consolidated Bill ...");
		} else {
			return false;
		}
	}
	
</script>
