
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

<c:set var="details" value="${resValue.details}"></c:set>
<c:set var="billid" value="${resValue.billid}"></c:set>

<c:set var="totalSum" value="${resValue.totalSum}"></c:set>

<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

</head>

<form name="frmViewConsBillDtls" method="post">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li ><a href="#" rel="tcontent1" class="selected"><b>Consolidated Paybill Details
		</b></a></li>
	</ul>
	</div>

<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<input type="hidden" id="hdConsBillId" value="${resValue.billid}"/>
	<br><br>
<table width="100%" border="1">

<tr >
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Bill Id</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Bill Name</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Level 1 Office Name</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Gross Amount</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Total Deductions</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Net Amount</b></td>
</tr>

<c:forEach var="detailsList" items="${details}">

<tr>
	<td><c:out value='${detailsList[0]}'/></td>
	<td><c:out value='${detailsList[1]}'/></td>
	<td><c:out value='${detailsList[2]}'/></td>
	<td><c:out value='${detailsList[3]}'/></td>
	<td><c:out value='${detailsList[4]}'/></td>
	<td><c:out value='${detailsList[5]}'/></td>

</tr>
</c:forEach>
<c:forEach var="totalSum" items="${totalSum}">
<tr>
	<td></td>
	<td></td>
	<td>Total</td>
	<td><c:out value='${totalSum[0]}'/></td>
	<td><c:out value='${totalSum[1]}'/></td>
	<td><c:out value='${totalSum[2]}'/></td>

</tr>
</c:forEach>
</table>
<br>
	
	<table align="center">
		<tr>
			<td align="right" style="width:50%">
						
		<hdiits:button name="btnExcel" style="width:100%"
				id="btnExcel" type="button" value="Export To Excel"
				onclick="return generateConsExcelReport();" />
			</td>
			
			<td align="left" style="width:50%">
						
		<hdiits:button name="btnBack"
				id="btnBack" type="button" value="Back" style="width:100%"
				onclick="return returnToViewConsDetails();" />
			</td>
			
		</tr>
	</table>		
</div>
	
<form>



<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	initializetabcontent("maintab");

function returnToViewConsDetails(){
	var url="ifms.htm?actionFlag=viewConsolidatedBill";
	document.frmViewConsBillDtls.action=url;
	document.frmViewConsBillDtls.submit();
}
	
	
</script>

<script type="text/javascript">
function generateConsExcelReport(){
	
	var consBillId = '${billid}';
	var url="ifms.htm?actionFlag=generateExcelForConsBillDetails&billid="+consBillId;
	document.frmViewConsBillDtls.action=url;
	document.frmViewConsBillDtls.submit();
}
</script>