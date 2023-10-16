
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

<c:set var="MonthlookUpList" value="${resValue.MonthlookUpList}"></c:set>
<c:set var="YearLookUpList" value="${resValue.YearLookUpList}"></c:set>
<c:set var="schemeList" value="${resValue.schemedatalstone}"></c:set>
<c:set var="ddoDtls" value="${resValue.ddoDtls}"></c:set>



<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
</head>

<hdiits:form name="frmFinalDDO" validate="true" method="post">

	<hdiits:hidden name="seladminDept" id="seladminDept" default="2" />
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>Approve
		Pay Bill</b></a></li>
	</ul>
	</div>
	<br />
	<br />
	<div id="tcontent1"
		style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">
	<br />
	<table width="100%">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="PR.MONTH"
				bundle="${ReportingDDO}" /></b></td>

			<td width="25%"><select name="selmonth" id="selmonth"
				onchange="">
				<option value="-1"><fmt:message key="EIS.Select"
					bundle="${ReportingDDO}" /></option>
				<c:forEach var="MonthlookUpList" items="${MonthlookUpList}">

					<option value="<c:out value="${MonthlookUpList.lookupShortName}"/>">
					<c:out value="${MonthlookUpList.lookupDesc}" /></option>
				</c:forEach>

			</select></td>

			<td width="25%" colspan="2"><b><hdiits:caption
				captionid="PR.YEAR" bundle="${ReportingDDO}" /></b></td>
			<td width="25%"><select name="selyear" id="selyear" onchange="">
				<option value="-1"><fmt:message key="EIS.Select"
					bundle="${ReportingDDO}" /></option>

				<c:forEach var="YearLookUpList" items="${YearLookUpList}">

					<option value="<c:out value="${YearLookUpList.lookupName}"/>">
					<c:out value="${YearLookUpList.lookupDesc}" /></option>
				</c:forEach>

			</select></td>


		</tr>
		<tr>
			<td width="25%"><b>Scheme Code</b></td>
			<td width="25%"><select name="schemecode" id="schemecode"
				onchange="">
				<option value="-1">--Select----</option>

				<c:forEach var="schemeList" items="${schemeList}">

					<option value="<c:out value="${schemeList[1]}"/>"
						title="${schemeList[2]}"><c:out value="${schemeList[2]}" /></option>
				</c:forEach>

			</select></td>

			<td width="25%"></td>
			<td width="25%"></td>
		</tr>

		<tr>
			<td width="25%"><!--//commented by vaibhav tyagi
					
					<b>DDO Code</b>
	    		--> <%//added by vaibhav tyagi:start%> <b>DDO Details</b> <%//added by vaibhav tyagi:end%>
			</td>
			<td width="100%">
			<%//added by vaibhav tyagi:start%> 
			<c:set var="srno" value="1" > </c:set>
			<display:table name="${ddoDtls}"
				requestURI="" pagesize="1000" sort="list" id="ddo" export="false" 
				style="width:100%">
				<display:column class="tablecelltext" title="<b>Select</b>"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
					<div id="${ddo[0]}"><hdiits:checkbox id="ddoList"
						name="ddoList" value="${ddo[0]}" /></div>
				</display:column>
				<display:column class="tablecelltext" title="DDO Code"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
			   ${ddo[0]}
		  </display:column>
				<display:column class="tablecelltext" title="<b>DDO Office Name</b>"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
			   ${ddo[1]}
		  </display:column>
		  <c:set var="srno" value="${srno+1}" > </c:set>
			</display:table> <!--<table width="400px">
				<tr>
					<td><b>Select</b></td>
					<td><b>DDO Code</b></td>
					<td><b>DDO Office Name</b></td>
				<tr>
					<c:forEach var="ddo" items="${ddoDtls}">
						<tr>
							<td><hdiits:checkbox id="ddoList" name="ddoList"
								value="${ddo[0]}" /></td>
							<td><c:out value="${ddo[0]}" /></td>
							<td><c:out value="${ddo[1]}" /></td>
						<tr>
					</c:forEach>
			</table>
			--> <%//added by vaibhav tyagi:end%> <%//commented by vaibhav tyagi
					/*
						<c:forEach var="ddo"  items="${ddoDtls}">
								<hdiits:checkbox id="ddoList" name="ddoList" value="${ddo[0]}"/>&nbsp;<c:out value="${ddo[0]} [${ddo[1]}] " />&nbsp;
								
					
					 */%>
			</td>
			<%//commented by vaibhav tyagi
					/*
							<td width="25%"></td>
							<td width="25%"></td>
					 */%>
		</tr>
	</table>
	<br>
	<br>

	<table align="center">
		<tr>
			<td align="center"><hdiits:button name="btnconsolidate"
				id="btnconsolidate" type="button" value="Search"
				onclick="ConsolidateSummaryPage();" /></td>
		</tr>
	</table>
	</div>
	<br>

	<hdiits:validate locale="${locale}" controlNames="" />


</hdiits:form>





<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	initializetabcontent("maintab");

	function ConsolidateSummaryPage() {

		var len = document.frmFinalDDO.ddoList.length;
		var isChecked = false;
		for ( var i = 0; i < len; i++) {

			if (document.frmFinalDDO.ddoList[i].checked) {
				isChecked = true;
				break;

			}

		}
		if (document.frmFinalDDO.ddoList.checked)
			isChecked = true;
		if (!isChecked) {
			alert("Please select atleast one DDO Code");
			return false;
		}

		if (document.frmFinalDDO.selmonth.value == '-1') {
			alert('Please select Month.');
			document.frmFinalDDO.selmonth.focus();
			return false;
		}
		if (document.frmFinalDDO.selyear.value == '-1') {
			alert('Please select Year.');
			document.frmFinalDDO.selyear.focus();
			return false;
		}

		if (document.frmFinalDDO.schemecode.value == '-1') {
			alert('Please select Scheme.');
			document.frmFinalDDO.schemecode.focus();
			return false;
		}

		if (document.frmFinalDDO.ddoList.checked == false) {
			alert('Please select DDO CODE.');
			document.frmFinalDDO.ddoList.focus();
			return false;
		}

		var urlstyle = "ifms.htm?actionFlag=ShowSummaryPageDtlsForFinalDDO";
		document.frmFinalDDO.action = urlstyle;
		document.frmFinalDDO.submit();
		showProgressbar("Please wait<br>Your Request is in progress ...");
	}

	function CloseScreen() {
		var urlstyle = "ifms.htm?actionFlag=validateLogin";
		document.forms[0].action = urlstyle;
		document.forms[0].submit();
	}

	function popUpBillDetails() {

	}
</script>
