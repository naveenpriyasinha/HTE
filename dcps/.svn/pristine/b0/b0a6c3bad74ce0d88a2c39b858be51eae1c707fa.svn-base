
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

<c:set var="rejectList" value="${resValue.rejectList}"></c:set>



<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
</head>
<fieldset class="tabstyle"><legend> 
	                  <fmt:message key="Forward consolidated Pay Bills" ></fmt:message></legend>
<hdiits:form name="frmPaybill" validate="true" method="post" action="ifms.htm?actionFlag=rejectConsolidatedBill">

<table width="100%" border="1">

<tr >
<td style="align:center">
<b>Select</b>
</td>
<td style="align:center">
<b>Consolidated Bill Id</b>
</td>
<td style="align:center"><b>Scheme Code</b></td>
<td style="align:center"><b>Gross Amount</b></td>
<td style="align:center"><b>Net Amount</b></td>
<td style="align:center"><b>Status</b></td>
</tr>

<c:forEach var="entry" items="${rejectList}">

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
<c:if test="${entry[4]== '0'}">
	Pending
</c:if>
</td>

</tr>
</c:forEach>

<tr><td>
	<hdiits:button name="delete"
				id="delete" type="button" value="Delete"
				onclick="deleteConsPayBill();" /></td>
				<td>
				<hdiits:submitbutton name="approve"
				id="approve" type="" value="Delete"
				onclick="rejectConsPayBill();" /></td>
				</tr>
</table>
</hdiits:form>
</fieldset>


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
	function rejectConsPayBill(){
		
		if(document.getElementById("billid").checked=="")
		{
			alert("Please select biill group to approve");
			return false;
		}
		
		document.forms[0].submit();
		//return true;
	}
</script>
