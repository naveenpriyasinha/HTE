<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="hrEnqEmpDtl" value="${resultValue.hrEnqEmpDtl}" />

<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<fmt:setBundle basename="resources.ess.resignation.CessationAlertLables" var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.resignation.ResignationLabels" var="commonLables1" scope="request" />

<script>
var win;

	function openAppWindow(fileId)
	{
		var href = "hrms.htm?actionFlag=viewEmpDtlForEnq&fileId="+fileId;
		win = window.open(href,"reportDeptEnq","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes,dependent=yes");			
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
	<hdiits:form name="reportDeptEnq" validate="true" method="POST">
	
		<table align="center" width="100%" bordercolor="black" style="border-collapse: collapse" border="1" style="height:50%">
			<tr bgcolor="#C6DEFF" align="center">
				<td><hdiits:caption captionid="HRMS.SerialNo" bundle="${commonLables1}"/></td>
				<td><hdiits:caption captionid="HRMS.File" bundle="${commonLables1}"/></td>
				<td><hdiits:caption captionid="HRMS.prePrilim" bundle="${commonLables1}"/></td>
				<td><hdiits:caption captionid="HRMS.prilim" bundle="${commonLables1}"/></td>
				<td><hdiits:caption captionid="HRMS.inquiryStatus" bundle="${commonLables1}"/></td>
			</tr>
			
			<c:set var="i" value="1"></c:set>
			<c:forEach var="hrEnqEmpDtllist" items="${hrEnqEmpDtl}">
			<tr align="center">
				<td>${i}</td>
				<td><a href="#" onclick="openAppWindow(${hrEnqEmpDtllist.fileId})">${hrEnqEmpDtllist.fileId}</a></td>
				<td>
					<c:if test="${hrEnqEmpDtllist.prePrelimStatus eq 'P'}">
						<hdiits:caption captionid="HRMS.Pending" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.prePrelimStatus eq 'A'}">
						<hdiits:caption captionid="HRMS.Approved" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.prePrelimStatus eq 'N'}">
						<hdiits:caption captionid="HRMS.Reject" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.prePrelimStatus eq 'C'}">
						<hdiits:caption captionid="HRMS.Closed" bundle="${commonLables1}"/>
					</c:if>
				</td>
				<td>
					<c:if test="${hrEnqEmpDtllist.prelimStatus eq 'P'}">
						<hdiits:caption captionid="HRMS.Pending" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.prelimStatus eq 'A'}">
						<hdiits:caption captionid="HRMS.Approved" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.prelimStatus eq 'N'}">
						<hdiits:caption captionid="HRMS.Reject" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.prelimStatus eq 'C'}">
						<hdiits:caption captionid="HRMS.Closed" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.prelimStatus eq 'I'}">
						<hdiits:caption captionid="HRMS.Initiated" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.prelimStatus eq 'NI'}">
						<hdiits:caption captionid="HRMS.NotInitiated" bundle="${commonLables1}"/>
					</c:if>
				</td>
				<td>
					<c:if test="${hrEnqEmpDtllist.deptStatus eq 'P'}">
						<hdiits:caption captionid="HRMS.Pending" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.deptStatus eq 'A'}">
						<hdiits:caption captionid="HRMS.Approved" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.deptStatus eq 'N'}">
						<hdiits:caption captionid="HRMS.Reject" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.deptStatus eq 'C'}">
						<hdiits:caption captionid="HRMS.Closed" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.deptStatus eq 'I'}">
						<hdiits:caption captionid="HRMS.Initiated" bundle="${commonLables1}"/>
					</c:if>
					<c:if test="${hrEnqEmpDtllist.deptStatus eq 'NI'}">
						<hdiits:caption captionid="HRMS.NotInitiated" bundle="${commonLables1}"/>
					</c:if>
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