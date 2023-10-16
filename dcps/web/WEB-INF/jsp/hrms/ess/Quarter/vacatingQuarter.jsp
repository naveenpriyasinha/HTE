
<%
try
{%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/vacatingQuarter.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="PrevQtrList" value="${resValue.PrevQtrList}"></c:set>
<c:set var="CurrentQtr" value="${resValue.CurrentQtrDtls}"></c:set>
<c:set var="BillAttachment" value="${resValue.BillAttachment}"></c:set>
<c:set var="PrevFlag" value="${resValue.PrevFlag}"></c:set>
<c:set var="CurrFlag" value="${resValue.CurrFlag}"></c:set>
<c:set var="appliedFinalList" value="${resValue.appliedFinalList}"></c:set>



<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables"
	var="QtrLables" scope="request" />
<script type="text/javascript">
var vacateQuarterArray= new Array();
vacateQuarterArray[0]="<fmt:message key='HRMS.MSG' bundle='${QtrLables}' />";



</script>

<hdiits:form name="form1" validate="true" method="POST"
	encType="multipart/form-data"
	action="./hrms.htm?actionFlag=updateQtrMstBillData">


	<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

	<br>
	<hdiits:fieldGroup titleCaptionId="HRMS.PreviousAllotedQuarters"
		bundle="${QtrLables}" id="prevQtrHdr">
		<!--  <TABLE width="100%" align="CENTER">
<TBODY>
  <TR bgColor=#386cb7>
    <TD align="center" class="fieldLabel" colSpan=5><FONT color=#ffffff><STRONG><U><fmt:message key="HRMS.PreviousAllotedQuarters" bundle="${QtrLables}"/>
      </U></STRONG></FONT></TD></TR></TBODY>
</TABLE>-->
		 
		<TABLE class="tabtable" id="table123" border="1"
			style="border-collapse: collapse;" borderColor="BLACK">
			<TBODY>
				<c:if test="${PrevFlag == 0}">
					<TR>
						<TD class="fieldLabel" align="center" bgcolor="#C9DFFF">
						<hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></TD>
						<TD class="fieldLabel" align="center" bgcolor="#C9DFFF">
						<hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></TD>
						<TD class="fieldLabel" align="center" bgcolor="#C9DFFF">
						<hdiits:caption captionid="HRMS.QtrType" bundle="${QtrLables}" /></TD>
						<TD class="fieldLabel" align="center" bgcolor="#C9DFFF">
						<hdiits:caption captionid="HRMS.NameofQuarter" bundle="${QtrLables}" /></TD>
						<TD class="fieldLabel" align="center" bgcolor="#C9DFFF">
						<hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></TD>
						<TD class="fieldLabel" align="center" bgcolor="#C9DFFF">
						<hdiits:caption captionid="HRMS.TentetiveEndDate" bundle="${QtrLables}" /></TD>
					</TR>
					<c:forEach var="preQtrList" items="${PrevQtrList}">
						<TR>
							<TD><c:out
								value="${preQtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}" /></TD>
							<TD><c:out value="${preQtrList.hrEssQtrMstQtrId.namePoliceLine}" /></TD>
							<TD><c:out value="${preQtrList.hrQuaterTypeMst.quaType}" /></TD>
							<TD><c:out value="${preQtrList.quarterName}" /></TD>
							<TD><fmt:formatDate
								value="${preQtrList.allocationStartDate}" pattern="dd/MM/yyyy" /></TD>
							<TD><fmt:formatDate value="${preQtrList.allocationEndDate}"
								pattern="dd/MM/yyyy" /></TD>
						</TR>
					</c:forEach>
				</c:if>
				<c:if test="${PrevFlag == 1}">
					<TR>
						<TD align="center"><font color="black"><b><fmt:message
							key="HRMS.NotFound" bundle="${QtrLables}" /></b></font></TD>
					</TR>

				</c:if>
			</TBODY>
		</TABLE>
	</hdiits:fieldGroup>
	<br>
	<hdiits:fieldGroup titleCaptionId="HRMS.CurrentQtrDtls"
		bundle="${QtrLables}" id="currentQtr" mandatory="true">
		<!--  <TABLE  WIDTH="100%" align="CENTER" >
	<TBODY>
	<TR bgColor=#386cb7>
		<TD  class="fieldLabel" align="center" colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="HRMS.CurrentQtrDtls" bundle="${QtrLables}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>-->
		<br>
		<TABLE width="100%" align="CENTER" border="1" cellspacing="0"
			style="border-collapse: collapse;" borderColor="BLACK">
			<c:if test="${CurrFlag == 0}">
				<TR>
					<TD width="2%" class="fieldLabel" align="center"
						bgcolor="#C9DFFF">&nbsp;</TD>
					<TD WIDTH="16%" class="fieldLabel" align="center"
						bgcolor="#C9DFFF"><b><hdiits:caption
						captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></TD>
					<TD WIDTH="16%" class="fieldLabel" align="center"
						bgcolor="#C9DFFF"><b><hdiits:caption
						captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
					<TD WIDTH="16%" class="fieldLabel" align="center"
						bgcolor="#C9DFFF"><b><hdiits:caption
						captionid="HRMS.QtrType" bundle="${QtrLables}" /></b></TD>
					<TD WIDTH="16%" class="fieldLabel" align="center"
						bgcolor="#C9DFFF"><b><hdiits:caption
						captionid="HRMS.NameofQuarter" bundle="${QtrLables}" /></b></TD>
					<TD WIDTH="16%" class="fieldLabel" align="center"
						bgcolor="#C9DFFF"><b><hdiits:caption
						captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></TD>
				</TR>
				<c:forEach var="appliedQtr" items="${appliedFinalList}">
					<TR>
					
						<TD><input type="radio" name="Qid" value="${appliedQtr.quarterId}" disabled="disabled"></input></TD>
						<TD WIDTH="16%"><c:out
							value="${appliedQtr.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}" /></TD>
						<TD WIDTH="16%"><c:out value="${appliedQtr.hrEssQtrMstQtrId.namePoliceLine}" /></TD>
						<TD WIDTH="16%"><c:out
							value="${appliedQtr.hrQuaterTypeMst.quaType}" /></TD>
						<TD WIDTH="16%"><c:out value="${appliedQtr.quarterName}" /></TD>
						<TD WIDTH="16%"><fmt:formatDate
							value="${appliedQtr.allocationStartDate}" pattern="dd/MM/yyyy" /></TD>
					</TR>
				</c:forEach>
				<c:forEach var="CurrentQtr" items="${CurrentQtr}">
					<TR>
						<TD><hdiits:radio name="Qid" value="${CurrentQtr.quarterId}"
							onclick=" getData(this)" /></TD>
						<TD WIDTH="16%"><c:out
							value="${CurrentQtr.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}" /></TD>
						<TD WIDTH="16%"><c:out value="${CurrentQtr.hrEssQtrMstQtrId.namePoliceLine}" /></TD>
						<TD WIDTH="16%"><c:out
							value="${CurrentQtr.hrQuaterTypeMst.quaType}" /></TD>
						<TD WIDTH="16%"><c:out value="${CurrentQtr.quarterName}" /></TD>
						<TD WIDTH="16%"><fmt:formatDate
							value="${CurrentQtr.allocationStartDate}" pattern="dd/MM/yyyy" /></TD>
					</TR>
				</c:forEach>
			</c:if>
			<c:if test="${CurrFlag == 1}">
				<TR>
					<TD align="center"><font color="black"><b><fmt:message
						key="HRMS.NotFound" bundle="${QtrLables}" /></b></font></TD>
				</TR>

			</c:if>
		</TABLE>

	</hdiits:fieldGroup>
	<br>
	<div id="billattachment" align="center" style="display:none"><hdiits:fieldGroup
		titleCaptionId="HRMS.Bill" bundle="${QtrLables}"
		id="billAttachmentDtl">
		<!--  <TABLE  id="billattachment" width="100%" align="center" style="display:none">
  <TR bgColor=#386cb7>  
    <TD  class="fieldLabel" align="center" colSpan=5><FONT color=#ffffff><STRONG><U><fmt:message key="HRMS.Bill" bundle="${QtrLables}"/></U></STRONG> 
	</FONT></TD></TR>-->
		<table>
			<tr>
				<td width="100%" id="attachbill1" colspan="4" align="center"
					style="display:none">
				<table border='0' width="100%">
					<tr>
						<td><!-- For attachment : Start--> <jsp:include
							page="/WEB-INF/jsp/common/attachmentPage.jsp" flush="true">
							<jsp:param name="attachmentName" value="BillAttachment" />
							<jsp:param name="formName" value="form1" />
							<jsp:param name="attachmentType" value="Document" />
							<jsp:param name="multiple" value="N" />
						</jsp:include> <!-- For attachment : End--></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
	</hdiits:fieldGroup></div>

	<center><hdiits:submitbutton type="button" id="Submit"
		name="Submit" value="Submit" captionid="HRMS.Submit"
		bundle="${QtrLables}" onclick="" style="display:none" /> <hdiits:button
		name="Close" type="button" value="Close" captionid="HRMS.Close"
		bundle="${QtrLables}" onclick="ClosePage(document.form1);" /></center>

	<input type="hidden" name="h1" />
	<script type="text/javascript">
		//initializetabcontent("maintab");
</script>



	<hdiits:hidden name="QidText" id="QidText" />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

