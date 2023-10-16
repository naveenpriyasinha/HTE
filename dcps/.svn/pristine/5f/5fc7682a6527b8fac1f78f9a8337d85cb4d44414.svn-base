<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="hrItExemptEmpDtls" value="${resValue.hrItExemptEmpDtls}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page" />

<hdiits:form name="frmEmpExemptDtls" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertUpdateEmpExemptData&edit=Y&empExemptDtlsId=${hrItExemptEmpDtls.itexemptDtlsId}" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="empExemptUpdate" bundle="${enLables}"></hdiits:caption></b></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">
	
	<div id="tcontent1" class="halftabcontent" tabno="0">
	
	    <br>	    
	    <table align="center">				
		<tr>
			<td width=10%>  </td>
			<td width="15%"><b><hdiits:caption captionid="MISC.employee" bundle="${enLables}"/></b></td>			
			<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td width="20%"><hdiits:text name="employee" default="${hrItExemptEmpDtls.hrEisEmpMst.orgEmpMst.empPrefix} ${hrItExemptEmpDtls.hrEisEmpMst.orgEmpMst.empFname} ${hrItExemptEmpDtls.hrEisEmpMst.orgEmpMst.empMname} ${hrItExemptEmpDtls.hrEisEmpMst.orgEmpMst.empLname}" captionid="MISC.employee" bundle="${commonLables}"  validation="txt.isrequired"  readonly="true" size="25"/> </td>
			<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr> </tr>
		<tr>	
			<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        <td width="20%"><font size="2"><b><hdiits:caption captionid="exemptType" bundle="${enLables}"></hdiits:caption> </b></font> </td>
	        <td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td width="20%"><hdiits:text name="exemptType" size="25" caption="Exemption Type" readonly="true" default="${hrItExemptEmpDtls.hrItExemptTypeMst.itexemptName}"  /></td>	
			<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr></tr>
		<tr>	 
			<td width=5%> : </td>    
	        <td width="20%"><font size="2"><b><hdiits:caption captionid="NGD.amount" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>	       
			<td width="20%"><hdiits:number name="exemptAmount" size="25" caption="Amount" default="${hrItExemptEmpDtls.amount}" maxlength="100" validation="txt.isrequired,txt.isnumber" mandatory="true"/></td>	
			<td width="10%"></td>
		
		</table>
 	</div>  
 
 <fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables" scope="request"/>	
 	<jsp:include page="../../core/PayTabnavigation.jsp" />
 	</div>
 		
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='') {
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getEmpExemptData";
			document.frmEmpExemptDtls.action=url;
			document.frmEmpExemptDtls.submit();
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