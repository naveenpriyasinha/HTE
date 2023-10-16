<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	

<c:set var="lstSection" value="${resValue.lstSection}" > </c:set>
<c:set var="lstStatus" value="${resValue.lstStatus}" > </c:set>
<c:set var="hrInvestTypeMst" value="${resValue.hrInvestTypeMst}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<script>
function chkKey(e){	
	if(e.keyCode=="13") {
		return false;
	}
	else {		
		return true;
	}
}


</script>


<hdiits:form name="frmBF" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertUpdateInvestType&investTypeId=${hrInvestTypeMst.investTypeId}&edit=Y" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="investTypeMasterUpdate" bundle="${enLables}"></hdiits:caption></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table><br>
		<tr><td width="10%">&nbsp;</td>
			<td width="15%"><font size="2"><b><hdiits:caption captionid="sectionCode" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td width="20%"><hdiits:select name="sectId"
			    captionid="sectionCode"
				mandatory="true"
				sort="false"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    <c:forEach items="${lstSection}" var="list">
			    <c:choose>
			    	<c:when test="${hrInvestTypeMst.hrItSectionMst.sectId  == list.sectId}">
			    		<hdiits:option value="${list.sectId}" selected="true">${list.sectCode}</hdiits:option>
			    	</c:when>
			    	<c:otherwise>
			    		<hdiits:option value="${list.sectId}">${list.sectCode}</hdiits:option>
			    	</c:otherwise>
			    </c:choose>	
			    </c:forEach>
			    </hdiits:select></td>
			<td width="10%">&nbsp;</td>
			<td width="15%"><font size="2"><b><hdiits:caption captionid="investName" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td width="20%"><hdiits:text name="investName" default="${hrInvestTypeMst.investName}" size="25" caption="investName" maxlength="100" mandatory="true"/></td>
			<td width="10%">&nbsp;</td>			
		</tr>
		
		<tr><td width="10%">&nbsp;</td>
			<td width="15%"> <font size="2"><b><hdiits:caption captionid="investDesc" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:textarea name="investDesc" default="${hrInvestTypeMst.investDesc}" caption="investDesc" rows="3" cols="28" > </hdiits:textarea> </td>
			<td width="10%"></td>
			<td width="15%"> <font size="2"><b><hdiits:caption captionid="STATUS" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:select name="statusFlag"
			    captionid="STATUS"
				mandatory="true"
				sort="false"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    <c:forEach items="${lstStatus}" var="list">
			    <c:choose>
			    <c:when test="${hrInvestTypeMst.activateFlag  == list.lookupId}">
			    	<hdiits:option value="${list.lookupId}" selected="true">${list.lookupDesc}</hdiits:option>
			    </c:when>
			    <c:otherwise>
			    	<hdiits:option value="${list.lookupId}">${list.lookupDesc}</hdiits:option>
			    </c:otherwise>	
			    </c:choose>
			    </c:forEach>
			    </hdiits:select></td>
			    <td width="10%">&nbsp;</td>	
		</tr>
		<tr><td width="10%"></td>
		<td width="15%"> <font size="2"><b><hdiits:caption captionid="NGD.startDate" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:dateTime name="startDate" caption="NGD.startDate" validation="txt.isdt" default="${hrInvestTypeMst.startDate}" bundle="${enLables}" mandatory="true" /> </td>
			<td></td>
			<td width="15%"> <font size="2"><b><hdiits:caption captionid="NGD.endDate" bundle="${enLables}" ></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:dateTime captionid="NGD.endDate" bundle="${enLables}" name="endDate" validation="txt.isdt" default="${hrInvestTypeMst.endDate}"/> </td>
			<td width="10%">&nbsp;</td>	
		</tr>
	</table>
 	</div>
 	<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getInvestmentTypes";
			document.frmBF.action=url;
			document.frmBF.submit();
		}
		
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>	