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
<c:set var="empInvestList" value="${resValue.empInvestList}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<hdiits:form name="frmEmpInvestDtls" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertUpdateEmpInvestDtls&edit=Y&empInvestDtlsId=${empInvestList.investDtlsId}" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="EMPINVEST.empInvestUpdate" bundle="${enLables}"></hdiits:caption></b></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">
	
	<div id="tcontent1" class="halftabcontent" tabno="0">
	
	    <br>
	    
	    <table align="center">		
		
		<tr>
			<td width=10%>  </td>
			<td width="15%"><b><hdiits:caption captionid="MISC.employee" bundle="${enLables}"/></b></td>
			
			<td width="20%"><hdiits:text name="employee" default="${empInvestList.hrEisEmpMst.orgEmpMst.empPrefix} ${empInvestList.hrEisEmpMst.orgEmpMst.empFname} ${empInvestList.hrEisEmpMst.orgEmpMst.empMname} ${empInvestList.hrEisEmpMst.orgEmpMst.empLname}" captionid="MISC.employee" bundle="${commonLables}"  validation="txt.isrequired"  readonly="true" size="25"/> </td>
			<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        <td width="20%"><font size="2"><b><hdiits:caption captionid="EMPINVEST.investType" bundle="${enLables}"></hdiits:caption> </b></font> </td>
	        
			<td width="20%"><hdiits:text name="investType" size="25" caption="Investment Type" readonly="true" default="${empInvestList.hrInvestTypeMst.investName}"  /></td>	
			<td width=5%>  </td>
		</tr>
		<tr></tr>
		<tr>	 
			<td width=5%> : </td>    
	        <td width="20%"><font size="2"><b><hdiits:caption captionid="NGD.amount" bundle="${enLables}"></hdiits:caption> </b></font> </td>
	       
			<td width="20%"><hdiits:number name="investAmount" size="25" caption="Amount" default="${empInvestList.amount}" maxlength="100" validation="txt.isrequired,txt.isnumber" mandatory="true"/></td>	
			<td width="10%"></td>
			<td width="20%"><font size="2"><b><font size="2"><b><hdiits:caption captionid="EMPINVEST.proofSubmition" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			
			<td width="20%">
			
			    <c:if test="${empInvestList.proofSubmitFlag eq '1'}">
				<input type="checkbox" name="isProofSubmit" checked>
			</c:if>
			<c:if test="${empInvestList.proofSubmitFlag eq '0'}">
				<input type="checkbox" name="isProofSubmit" >
			</c:if>	
			</td>
			<td width=5%> : </td>
			</tr>
			<tr></tr><tr></tr>
		<tr>			
			<td width=5%>  </td>
			<td width="20%"> <font size="2"><b><hdiits:caption captionid="EMPINVEST.investDate" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			
			<td width="20%"> <hdiits:dateTime name="investDate" caption="EMPINVEST.investDate" bundle="${enLables}" default="${empInvestList.investDate}" validation="txt.isrequired,txt.isdt" minvalue=""/> </td>
			<td width="10%"></td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="EMPINVEST.investPolicyNo" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			
			<td width="20%"><hdiits:text name="policyNo" size="25" caption="Policy No" default="${empInvestList.policyNo}" maxlength="100"  /></td>	
			<td width=5%>  </td>
		</tr>
		<tr>
		<tr> </tr> <tr> </tr>
		<tr>		
			<td width=5%>  </td>	
			<td width="20%"><font size="2"><b><font size="2"><b><hdiits:caption captionid="EMPINVEST.proofApproval" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			
			<td width="20%">
			<c:if test="${empInvestList.approvalFlag eq '1'}">
				<input type="checkbox" name="isProofApproved" checked>
			</c:if>
			<c:if test="${empInvestList.approvalFlag eq '0'}">
				<input type="checkbox" name="isProofApproved" >
			</c:if>
			</td>
			
		</tr>
		
		
		</table>
 	</div>  
 	
	

	
	
 <fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>	
 	<jsp:include page="../../core/PayTabnavigation.jsp" />
 	</div>
 		
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getEmpInvestmentData";
			document.frmEmpInvestDtls.action=url;
			document.frmEmpInvestDtls.submit();
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