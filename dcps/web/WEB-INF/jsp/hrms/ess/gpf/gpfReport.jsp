
<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables"
	scope="request" />
	
<fmt:setBundle basename="resources.ess.gpf.GPFAlertLabels" var="gpfAlertLables"
	scope="request" />	



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="langId" value="${resValue.locale}"></c:set>


<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<script type="text/javascript">

function generateReport(){
	
	var cmbVal=document.getElementById('cmbRep').value;
	if(cmbVal=='-1')
	{
		alert("<fmt:message bundle="${gpfAlertLables}" key="reportSelect" />");
		return true;
	}

	document.frmReport.action="hrms.htm?actionFlag=reportService&reportCode="+cmbVal+"&action=generateReport&FromParaPage=TRUE";
	document.frmReport.submit();
}
</script>

<hdiits:form name="frmReport" validate="true" method="POST">
	<div id="tabmenu">

	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message
			key="GPF" /></a></li>
	</ul>
	</div>


	<div class="tabcontentstyle">


	<div id="tcontent1" class="tabcontent" tabno="0">

	<table width="100%">

		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="2" align="center"><font
				color="#fffff"> <strong><u><b>Reports</u></strong> </font></td>
		</tr>

		<tr>
			<td></td>
		</tr>

		<tr>
			<td></td>
		</tr>

		<tr>
			<td></td>
		</tr>

		<tr>
			<td align="center" style="color:blue"><b>GPF Reports</b></td>
			<td><c:if test="${resultObj.resultValue.locale == 'en_US'}">
				<hdiits:select name="cmbRep" id="cmbRep">
					<hdiits:option value="-1">
						<fmt:message bundle="${gpfLables}" key="COMMON.DROPDOWN.SELECT" />
					</hdiits:option>
					<hdiits:option value="300236">
						<fmt:message bundle="${gpfLables}" key="HRMS.SubscriptionReport" />
					</hdiits:option>
					<hdiits:option value="300237">
						<fmt:message bundle="${gpfLables}" key="HRMS.AdvReport" />
					</hdiits:option>
					<hdiits:option value="300238">
						<fmt:message bundle="${gpfLables}" key="HRMS.Withdraw" />
					</hdiits:option>
				</hdiits:select>
			</c:if> <c:if test="${resultObj.resultValue.locale ne 'en_US'}">
				<hdiits:select name="cmbRep" id="cmbRep">
					<hdiits:option  value="-1">
						<fmt:message bundle="${gpfLables}" key="COMMON.DROPDOWN.SELECT" />
					</hdiits:option>
					<hdiits:option value="300242">
						<fmt:message bundle="${gpfLables}" key="HRMS.SubscriptionReport" />
					</hdiits:option>
					<hdiits:option value="300243">
						<fmt:message bundle="${gpfLables}" key="HRMS.AdvReport" />
					</hdiits:option>
					<hdiits:option value="300244">
						<fmt:message bundle="${gpfLables}" key="HRMS.Withdraw" />
					</hdiits:option>
				</hdiits:select>
			</c:if></td>
		</tr>

		<tr>
			<td></td>
			<td></td>
		</tr>

		<tr>
			<td></td>
			<td><hdiits:button type="button" name="Submit"
				value="Generate Report" onclick="generateReport()" /></td>
		</tr>
	</table>

	</div>
	</div>

	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
