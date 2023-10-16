
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

<c:set var="fwdList" value="${resValue.fwdList}"></c:set>



<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
</head>

<hdiits:form name="frmPaybill" validate="true" method="post" action="ifms.htm?actionFlag=forwardConsolidatedBill">
<div id="tabmenu" style="width:150%">
		<ul id="maintab" class="shadetabs">
			<li class="selected" ><a href="#" rel="tcontent1"><b>Forward consolidated Pay Bills</b></a></li>
		</ul>
	</div><br/></br/>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" ><br>
<table width="100%" border="1">

<tr >
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold">
<b>Select</b>
</td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold">
<b>Consolidated Bill Id</b>
</td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Scheme Code</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"<b>Gross Amount</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Net Amount</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold""><b>Status</b></td>
</tr>

<c:forEach var="entry" items="${fwdList}">

<tr>

<td>
<hdiits:radio name="billid" id="billid" mandatory="true" value="${entry[0]}"></hdiits:radio>
</td>
<td>
<a href="ifms.htm?actionFlag=getConsolidatedReport&billId=${entry[0]}">${entry[0]}</a>

</td>

<td>
<c:out value='${entry[1]}'/>
</td>
<td>
<c:out value='${entry[2]}'/>
</td>
<td>
<c:out value='${entry[3]}'/>
</td>
<td>
<input type="hidden" value="<c:out value="${entry[4]}"/>" name="status" id="status"/>
<c:if test="${entry[4]== '0' || entry[4]== '2' || entry[4]== '3'}">
	Pending
</c:if>

<c:if test="${entry[4]== '4' }">
	Rejected
</c:if>

<c:if test="${entry[4]== '1' }">
	Approved
</c:if>
</td>

</tr>
</c:forEach>

<tr><td colspan="3" align="right">
	<hdiits:button name="delete"
				id="delete" type="button" value="Delete"
				onclick="deleteConsPayBill();" /></td>
				<td colspan="3">
				<hdiits:submitbutton name="fwd"
				id="fwd"  value="Forward"
				onclick="approveConsPayBill();" /></td>
				</tr>
</table>
</div>
</hdiits:form>




<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	initializetabcontent("maintab");

	function ConsolidateSummaryPage() {

		var len = document.frmReportingDDO.ddoList.length;
		var isChecked = false;
		for (i = 0; i < len; i++) {
			if (document.frmReportingDDO.ddoList[i].checked) {
				isChecked = true;
				break;

			}

		}
		if (!isChecked) {
			alert("Please select atleast one DDO Code");
			return false;
		}
		if (document.frmReportingDDO.selmonth.value == '-1') {
			alert('Please select Month.');
			document.frmReportingDDO.selmonth.focus();
			return false;
		}
		if (document.frmReportingDDO.selyear.value == '-1') {
			alert('Please select Year.');
			document.frmReportingDDO.selyear.focus();
			return false;
		}

		if (document.frmReportingDDO.schemecode.value == '-1') {
			alert('Please select Scheme.');
			document.frmReportingDDO.schemecode.focus();
			return false;
		}

		if (document.frmReportingDDO.ddoList.checked == false) {
			alert('Please select DDO CODE.');
			document.frmReportingDDO.ddoList.focus();
			return false;
		}

		var urlstyle = "ifms.htm?actionFlag=ShowSummaryPageDtls";
		document.forms[0].action = urlstyle;
		document.forms[0].submit();
	}

	function CloseScreen() {
		var urlstyle = "ifms.htm?actionFlag=validateLogin";
		document.forms[0].action = urlstyle;
		document.forms[0].submit();
	}

	function searchConsPayBill(){
		var url="ifms.htm?actionFlag=viewConsolidatedBill&flag='yes'";
		document.frmReportingDDO.action="";
		document.frmReportingDDO.submit();
	}
	
	function deleteConsPayBill(){
		
		if(document.getElementById("billid").checked=="")
		{
			alert("Please select biill group to delete");
			return false;
		}
		var url="fms.htm?actionFlag=deleteconsBillGenerated";
		document.forms[0].actiom=url;
		document.forms[0].submit();
	}
	function approveConsPayBill(){
		alert(document.getElementById("billid").value);
		alert(document.getElementById("status").value);
		if(document.getElementById("billid").checked=="")
		{
			alert("Please select biill group to approve");
			return false;
		}
		
		document.forms[0].submit();
		//return true;
	}
</script>
