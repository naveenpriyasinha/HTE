<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="hrEssLoanMst" value="${resultValue.hrEssLoanMst}" />

<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<fmt:setBundle basename="resources.ess.resignation.CessationAlertLables" var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.resignation.ResignationLabels" var="commonLables1" scope="request" />

<script>
var win;

	function openAppWindow(corrId)
	{
		var href = "hrms.htm?actionFlag=loanLoadInboxPage&corrId="+corrId;
		win = window.open(href,"reportLoanAdv","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes,dependent=yes");			
		if(win.opener == null){
			win.opener = self;
		}
		window.close();
	}
</script>

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
		captionid="HRMS.deptInq" bundle="${commonLables1}" captionLang="single"/></b></a></li>
</ul>
</div>

<div class="halftabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">

	<br><br><br>
	<hdiits:form name="reportLoanAdv" validate="true" method="POST">
	
		<table align="center" width="100%" bordercolor="black" style="border-collapse: collapse" border="1" style="height:50%">
			<tr bgcolor="#C6DEFF" align="center">
				<td><hdiits:caption captionid="HRMS.SerialNo" bundle="${commonLables1}"/></td>
				<td><hdiits:caption captionid="HRMS.Corr" bundle="${commonLables1}"/></td>
				<td><hdiits:caption captionid="HRMS.LoanType" bundle="${commonLables1}"/></td>
				<td><hdiits:caption captionid="HRMS.AppliedDate" bundle="${commonLables1}"/></td>
				<td><hdiits:caption captionid="HRMS.ApprovedOrNot" bundle="${commonLables1}"/></td>
			</tr>
			
			<c:set var="i" value="1"></c:set>
			<c:forEach var="hrEssLoanMstList" items="${hrEssLoanMst}">
			<tr align="center">
				<td>${i}</td>
				<td><a href="#" onclick="openAppWindow(${hrEssLoanMstList.corrId})">${hrEssLoanMstList.corrId}</a></td>
				<td>
					<c:if test="${hrEssLoanMstList.cmnLookupMst.lookupId eq '300451'}"><hdiits:caption captionid="HRMS.fan" bundle="${commonLables1}"/></c:if>
					<c:if test="${hrEssLoanMstList.cmnLookupMst.lookupId eq '300452'}"><hdiits:caption captionid="HRMS.festival" bundle="${commonLables1}"/></c:if>
					<c:if test="${hrEssLoanMstList.cmnLookupMst.lookupId eq '300453'}"><hdiits:caption captionid="HRMS.food" bundle="${commonLables1}"/></c:if>
					<c:if test="${hrEssLoanMstList.cmnLookupMst.lookupId eq '300454'}"><hdiits:caption captionid="HRMS.mcaDtl" bundle="${commonLables1}"/></c:if>
					<c:if test="${hrEssLoanMstList.cmnLookupMst.lookupId eq '300490'}"><hdiits:caption captionid="HRMS.houseExtension" bundle="${commonLables1}"/></c:if>
					<c:if test="${hrEssLoanMstList.cmnLookupMst.lookupId eq '300491'}"><hdiits:caption captionid="HRMS.HouseSite" bundle="${commonLables1}"/></c:if>
					<c:if test="${hrEssLoanMstList.cmnLookupMst.lookupId eq '300493'}"><hdiits:caption captionid="HRMS.Sitecont" bundle="${commonLables1}"/></c:if>
				</td>
				<td>
					<fmt:formatDate var="doj" value="${hrEssLoanMstList.appDt}" pattern="dd/MM/yyyy" />
					${doj}
				</td>
				<td>
					<c:if test="${hrEssLoanMstList.approveFlag eq 'P'}"><hdiits:caption captionid="HRMS.Pending" bundle="${commonLables1}"/></c:if>
					<c:if test="${hrEssLoanMstList.approveFlag eq 'Y'}"><hdiits:caption captionid="HRMS.Approved" bundle="${commonLables1}"/></c:if>
				</td>
			</tr>
			<c:set var="i" value="${i+1}"></c:set>
			</c:forEach>
		</table>
		
	</hdiits:form>

</div>
</div>

	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>

<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>