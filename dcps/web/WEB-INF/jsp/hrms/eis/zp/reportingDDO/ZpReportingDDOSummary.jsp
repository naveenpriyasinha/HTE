<html>
<head>
<%
	try {
%>

<%@ include file="../../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />
<fmt:setBundle basename="resources.Payroll" var="constantVariables"
	scope="request" />
<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="Summarypage" scope="request" />
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript">
function ShowPayBill(ddoCode, id, value){
	var ddocode= ddoCode.innerHTML;
	var Month= document.getElementById("hdMonth").value;
	var Year= document.getElementById("hdYear").value;
	var billNo= id.innerHTML;
   	var urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000008&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month="+Month+"&Year="+Year+"&billNo="+billNo+"&Flag="+ddocode;
    var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
    window.open(urlstring,"",urlstyle);
}
</script>


<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="DDOLST" value="${resultValue.DDOLST}">
</c:set>
<c:set var="Month" value="${resultValue.Month}">
</c:set>
<c:set var="Year" value="${resultValue.Year}">
</c:set>
<c:set var="schemecode" value="${resultValue.schemecode}">
</c:set>

<c:set var="subSchemecode" value="${resultValue.subSchemecode}">
</c:set>
<c:set var="paybillId" value="${resultValue.paybillId}">
</c:set>
<c:set var="ddoCodeArray" value="${resultValue.ddoCode}">
</c:set>
</head>
<body>
<hdiits:form method="POST" name="frmSummaryPage" validate="true">
	<input type="hidden" value='<c:out value="${schemecode}"/>'
		name="schemeCode" id="schemeCode">
	<input type="hidden" value='<c:out value="${paybillId}"/>'
		name="paybillId" id="paybillId">
	<input type="hidden" value='<c:out value="${Month}"/>' name="selyear"
		id="selyear">
	<input type="hidden" value='<c:out value="${Year}"/>' name="selmonth"
		id="selmonth">
		
		<input type="hidden" value='<c:out value="${subSchemecode}"/>' name="subSchemecode"
		id="subSchemecode">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
			key="ZP.summary" bundle="${Summarypage}" /></b></a></li>
	</ul>
	</div>
	<br />
	<br />
	<div id="tcontent1"
		style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">
	<div align="center">
	<h1><c:out value="${resultValue.msg}" /></h1>
	</div>
	<br>
	&nbsp; <br>
	<font color="red"><b>Note: Please Update the Head of Account Codes of all GPF Employees before Consolidating Paybill</b></font>
	<table width="100%">
		<tr>
			<th><u>Month :-</u></th>
			<td width="20%"><b><c:out value="${Month}"></c:out></b> <input
				type="hidden" id="hdMonth" name="hdMonth" value="${Month}"></td>
			<th><b><u>Year :-</u></b></th>
			<td width="20%"><b><c:out value="${Year}">
			</c:out></b> <input type="hidden" id="hdYear" name="hdYear" value="${Year}"></td>
			<th><b><u>Scheme Code :-</u></b></th>
			<td width="20%"><b><input type="hidden" id="hdSch"
				name="hdSch" value="${schemecode}"><c:out
				value="${schemecode}">
			</c:out></b></td>
			
			<c:if test="${subSchemecode!=-1}">
			<th><b><u>Sub Scheme Code :-</u></b></th>
			<td width="20%"><b><input type="hidden" id="hdSubSch"	name="hdSubSch" value="${subSchemecode}"><c:out
				value="${subSchemecode}">
			</c:out></b></td>
			</c:if>
			<td width="20%"></td>

		</tr>

	</table>
	<c:set var="srno" value="1">
	</c:set> <display:table name="${DDOLST}" requestURI="" pagesize="100"
		sort="list" id="DDOLST123" export="false" style="width:100%">
		<c:if test="${DDOLST123[0]!=null}">
			<%--<display:column class="tablecelltex" title="<b>Select</b>"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;">
				<div><hdiits:checkbox id="billList" name="billList"
					value="${DDOLST123[1]}" /></div>
			</display:column>
			--%><display:column class="tablecelltext" title="DDO Code"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;">
				<label id="lbl${srno}">${DDOLST123[0]}</label>
				<input type="hidden" id="ddoList" name="ddoList" value="${DDOLST123[0]}"/>
			</display:column>
			<display:column class="tablecelltext" title="<b>Bill
		No</b>"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;">
				<a href="javascript:void(0)" id='ah${srno}'
					onclick='ShowPayBill(lbl${srno}, this, ${"5000008"})'><c:out
					value="${DDOLST123[1]}" /></a>
					<input type="hidden" id="hd${srno}" value="${DDOLST123[5]}"/>
			</display:column>
			<display:column class="tablecelltext" title="Bill
		Description"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;">
			  ${DDOLST123[2]}
		  </display:column>
			<display:column class="tablecelltext" title="Gross
		Bill Amount"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;">
			   ${DDOLST123[3]}
		  </display:column>
			<display:column class="tablecelltext" title="Net Bill
		Amount"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;">
			   ${DDOLST123[4]}
		  </display:column>
			<display:column class="tablecelltext" title="Reject"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;">
				<input type="hidden" id="${srno}" value="${srno}" />
				<input type="button" id="btnReject${srno}" name="btnReject${srno}" value="Reject"
					onclick='rejectPayBill(lbl${srno}, hd${srno})' />
			</display:column>

			<c:set var="srno" value="${srno+1}">
			</c:set>
		</c:if>
	</display:table> <!-- commented by vaibhav tyagi --> <%--
<table width="100%">
	<tr>
		<td width="20%" align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Select</b></td>
		<td width="20%" align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>DDO
		Code</b></td>
		<td width="20%" align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Bill
		No</b></td>
		<td width="20%" align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Bill
		Description</b></td>
		<td width="20%" align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Gross
		Bill Amount</b></td>
		<td width="20%" align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Bill
		Net Amount</b></td>
		<td width="20%" align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Reject</b></td>


	</tr>
	<c:forEach var="DDOLST123" items="${DDOLST}">
		<c:if test="${DDOLST123[0]!=null}">
			<tr>
				<td width="25%"><div id="${ddo[0]}"><hdiits:checkbox id="ddoList"
						name="ddoList" value="${DDOLST123[1]}" /></div></td>
				<td width="25%"><label id="lblDDOCode${DDOLST123[0]}"><c:out
					value="${DDOLST123[0]}" /></label></td>
				<td width="25%"><a href="javascript:void(0)"
					id='${DDOLST123[1]}'
					onclick="ShowPayBill(lblDDOCode${DDOLST123[0]} ,this, ${"5000008"})"><c:out
					value="${DDOLST123[1]}" /></a></td>
				<td width="25%"><c:out value="${DDOLST123[2]}" /></td>
				<td width="25%"><c:out value="${DDOLST123[3]}" /></td>
				<td width="25%"><c:out value="${DDOLST123[4]}" /></td>
				<td width="25%"><input type="hidden" id="hd${DDOLST123[1]}"
					value="${DDOLST123[1]}" /> <input type="button" id="btnReject"
					name="btnReject" value="Reject"
					onclick='rejectPayBill(hd${DDOLST123[1]});' /></td>

			</tr>
		</c:if>
	</c:forEach>

</table>
--%> <br>
	&nbsp;</div>
	<table align="center">
		<tr>
			<c:if test="${DDOLST[0]!=null}">
				<td align="center"><hdiits:button name="btnconsolidate" id="btnconsolidate" type="button" value="Consolidate" onclick="ConsolidateSummaryPage();"/></td>
			</c:if>
			<c:if test="${DDOLST[0]==null}">
				<td align="center"><input type="button" type="button"
					value="Consolidate" disabled="disabled" /></td>
			</c:if>
			<td><input name="Back" type="button" Value="Back"
				onclick="GoToPage();"></input></td>
		</tr>
	</table>

</hdiits:form>

<script type="text/javascript">
function rejectPayBill(ddoCode, billno){
	
	var billId= billno.value;
	var ddocode= ddoCode.innerHTML;
	
	var month= document.getElementById("hdMonth").value;
	var year= document.getElementById("hdYear").value;
	var schemeCode= document.getElementById("hdSch").value;
	
	if(document.getElementById("hdSubSch") !=null)
		{
	var subScheme = document.getElementById("hdSubSch").value;

		}
	var answer= confirm('Do you realy want to reject Paybill?');
	
	if(answer){
		var url = "ifms.htm?actionFlag=rejectPaybillByLevelTwo&paybillId="+billId+"&ddocode="+ddocode+"&hdMonth="+month+"&hdYear="+year+"&schemeCode="+schemeCode+"&subSchemeCode="+subScheme;
		
		document.frmSummaryPage.action = url;
		document.frmSummaryPage.submit();
		showProgressbar("Please wait<br>While Rejecting Bill ...");
		alert('Paybill rejected successfully.');
	}
	else{
		return false;
	}
}
</script>
<script type="text/javascript">

function GoToPage(){
	document.forms[0].action = "./ifms.htm?actionFlag=ViewReportingJsp";
	document.forms[0].submit();
}
</script>
<script>
function ConsolidateSummaryPage(){
	
	
	 var url = document.getElementById("paybillId").value;
	 var uri = 'ifms.htm?actionFlag=isconsBillGenerated&paybillId='+url;

	 showProgressbar("Please wait<br>Your Request is in progress ...");
	 
	 var myAjax = new Ajax.Request(uri,
		      {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
		consolidate(myAjax);
				},
		        onFailure: function(){ document.getElementById("btnconsolidate").disabled="disabled";
			        alert('Something went wrong...');} 
		          } );
}
function consolidate(myAjax){
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var CreatedDDOCode= XmlHiddenValues[0].childNodes[0].firstChild.nodeValue; 
	if (CreatedDDOCode=="yes") {
		alert('Some of the  bills already consolidated.');		
		document.getElementById("btnconsolidate").disabled="disabled";
		//document.getElementById("txtDDOCode").disabled="disabled";
		
	} 
	else{
		
	var urlstyle="ifms.htm?actionFlag=consolidateBillGrp";
	     document.forms[0].action=urlstyle;
    document.forms[0].submit();
    showProgressbar("Please wait<br>Your Request is in progress ...");
	}
}
</script>


<script type="text/javascript">
initializetabcontent("maintab")
</script>
<%
	} catch (Exception e) {

		e.printStackTrace();
	}
%>
</body>
</html>