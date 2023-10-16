<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script>
function validation()
{
		var branchBox=document.getElementsByName("chkBox_b");
		var dsgnBox=document.getElementsByName("chkBox_d");
		var userBox=document.getElementsByName("chkBox_u");
		if(branchBox.length==0 && dsgnBox.length==0 && userBox.length==0)
		{			
			return;
		}		
		else
		{
			submit1();		
		}
}
function submit1()
{
	document.ACRInitiateByAdmin.method="POST";
	document.ACRInitiateByAdmin.action="./hrms.htm?actionFlag=ACRInitByAdminSubmitted";	
	document.ACRInitiateByAdmin.submit();	
}
function closeButtonHandler()
{
	document.ACRInitiateByAdmin.method="POST";
	document.ACRInitiateByAdmin.action="./hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";	
	document.ACRInitiateByAdmin.submit();	
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="allBranch" value="${resValue.allBranch}"></c:set>
<c:set var="allDsgn" value="${resValue.allDsgn}"></c:set>
<c:set var="allUser" value="${resValue.allUser}"></c:set>
<c:set var="allRoleList" value="${resValue.acrRole}"></c:set>
<c:set var="year" value="${resValue.yearList}"></c:set>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> 
			<fmt:message key="HR.ACR.ACR" bundle="${commonLables}"/>
		</b></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
	 <div id="tcontent1"  class="tabcontent" tabno="0">
<table class="tabtable">
	<tr bgcolor="#386CB7">
		<td class="fieldLabel" width="100%"><font
			align="center" color="white"> <u> <b><fmt:message	key="HR.ACR.ACRInitiateByAdmin" bundle="${commonLables}" />
			<c:out value=" : ${year} - ${year+1}"></c:out>
		</b> </u> </font></td>
	</tr>
</table>
<br>
<br>
<hdiits:form name="ACRInitiateByAdmin" method="POST" validate="true"  encType="multipart/form-data" >	
	<hdiits:fieldGroup collapseOnLoad="false" style="text-align:center" titleCaptionId="HR.ACR.Branch" bundle="${commonLables}">							
	<c:if test="${not empty allBranch}">
	<c:forEach items="${allBranch}" var="dsgn">	
		<hdiits:fieldGroup collapseOnLoad="false" id="b_${dsgn.branchName}" titleCaption="${dsgn.branchName}">		
		<table border="1" borderColor="BLACK" style="border-collapse: collapse;" width="100%" class="tabtable">
				<tr bgcolor="#C9DFFF">
					<td width="5%" align="center"><hdiits:checkbox value="${dsgn.branch}" 
							name="chkBox_b" /></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Branch" bundle="${commonLables}"/></b></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.RoleType" bundle="${commonLables}" /></b></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Name" bundle="${commonLables}" /></b></td>	
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Designation" bundle="${commonLables}" /></b></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Time" bundle="${commonLables}" /></b></td>						
				</tr>					
				<c:set var="it" value="${dsgn.hierachyGepDtlVO}"></c:set>
				<c:forEach items="${it}" var="parentObj" varStatus="s">
					<tr>
						<td width="5%" align="left"></td>
						<td width="10%" align="left"><c:out value="${dsgn.branchName}"></c:out></td>
						<td width="10%" align="left"><c:out value="${parentObj.roleDesc}"></c:out></td>
						<td width="10%" align="left"><c:out value="${parentObj.empName}"></c:out></td>
						<td width="10%" align="left"><c:out value="${parentObj.designation}"></c:out></td>
						<td width="10%" align="left"><c:out
								value="${parentObj.startDate} - ${parentObj.endDate}"></c:out></td>
					</tr>	
				</c:forEach>						
					
		</table>
		</hdiits:fieldGroup>
	</c:forEach>
	</c:if>
	<c:if test="${empty allBranch}">
		<table align="center" class="tabtable">
		<tr>
			<td colspan="10" align="center"><b><fmt:message key="HR.ACR.NORECORD" bundle="${commonLables}"/></b></td>
		</tr>
		</table>
	</c:if>
	</hdiits:fieldGroup>
	<BR><BR>
	<hdiits:fieldGroup collapseOnLoad="false" style="text-align:center" titleCaptionId="HR.ACR.Designation" bundle="${commonLables}">			
	<c:if test="${not empty allDsgn}">
	<c:forEach items="${allDsgn}" var="dsgn">
		<hdiits:fieldGroup collapseOnLoad="false" id="a_${dsgn.designation}" titleCaption="${dsgn.branchName} - ${dsgn.designation}">	
		<table class="tabtable" border="1" borderColor="BLACK" style="border-collapse: collapse;" width="100%">				
				<tr bgcolor="#C9DFFF">
					<td width="5%" align="center"><hdiits:checkbox value="${dsgn.branch}^${dsgn.dsgnCode}" 
							name="chkBox_d"/></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Branch" bundle="${commonLables}"/></b></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Designation" bundle="${commonLables}"/></b></td>	
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.RoleType" bundle="${commonLables}" /></b></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Name" bundle="${commonLables}" /></b></td>	
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Designation" bundle="${commonLables}" /></b></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Time" bundle="${commonLables}" /></b></td>						
				</tr>					
					<c:set var="it" value="${dsgn.hierachyGepDtlVO}"></c:set>
				<c:forEach items="${it}" var="parentObj" varStatus="s">
					<tr>
						<td width="5%" align="left"></td>
						<td width="10%" align="left"><c:out value="${dsgn.branchName}"></c:out></td>
						<td width="10%" align="left"><c:out value="${dsgn.designation}"></c:out></td>						
						<td width="10%" align="left"><c:out value="${parentObj.roleDesc}"></c:out></td>
						<td width="10%" align="left"><c:out value="${parentObj.empName}"></c:out></td>
						<td width="10%" align="left"><c:out value="${parentObj.designation}"></c:out></td>
						<td width="10%" align="left"><c:out
								value="${parentObj.startDate} - ${parentObj.endDate}"></c:out></td>
					</tr>	
				</c:forEach>				
		</table>
		</hdiits:fieldGroup>
	</c:forEach>
	</c:if>	
	<c:if test="${empty allDsgn}">
		<table align="center" class="tabtable">
		<tr>
			<td colspan="10" align="center"><b><fmt:message key="HR.ACR.NORECORD" bundle="${commonLables}"/></b></td>
		</tr>
		</table>
	</c:if>
	</hdiits:fieldGroup>	
	<BR><BR>
	<hdiits:fieldGroup collapseOnLoad="false" style="text-align:center" titleCaptionId="HR.ACR.UserInint" bundle="${commonLables}">			
	<c:if test="${not empty allUser}">
	<c:forEach items="${allUser}" var="user">
		<hdiits:fieldGroup collapseOnLoad="false" id="a_${user.userId}" titleCaption="${user.empName}- ${user.userId}">	
		<table class="tabtable" border="1" borderColor="BLACK" style="border-collapse: collapse;" width="100%">				
				<tr bgcolor="#C9DFFF">
					<td width="5%" align="center"><hdiits:checkbox value="${user.userId}" 
							name="chkBox_u"/></td>
					<td width="10%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Branch" bundle="${commonLables}"/></b></td>
					<td width="10%" align="center"><b><hdiits:caption					
						captionid="HR.ACR.Designation" bundle="${commonLables}"/></b></td>	
					<td width="10%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Name" bundle="${commonLables}"/></b></td>
					<td width="10%" align="center"><b><hdiits:caption
						captionid="HR.ACR.RoleType" bundle="${commonLables}" /></b></td>
					<td width="5%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Name" bundle="${commonLables}" /></b></td>	
					<td width="10%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Designation" bundle="${commonLables}" /></b></td>
					<td width="10%" align="center"><b><hdiits:caption
						captionid="HR.ACR.Time" bundle="${commonLables}" /></b></td>						
				</tr>					
				<c:set var="it" value="${user.hierachyGepDtlVO}"></c:set>
				<c:forEach items="${it}" var="parentObj" varStatus="s">
					<tr>
						<td width="5%" align="left"></td>
						<td width="10%" align="left"><c:out value="${user.branchName}"></c:out></td>
						<td width="10%" align="left"><c:out value="${user.designation}"></c:out></td>						
						<td width="10%" align="left"><c:out value="${user.empName}"></c:out></td>						
						<td width="10%" align="left"><c:out value="${parentObj.roleDesc}"></c:out></td>
						<td width="10%" align="left"><c:out value="${parentObj.empName}"></c:out></td>
						<td width="10%" align="left"><c:out value="${parentObj.designation}"></c:out></td>
						<td width="10%" align="left"><c:out
								value="${parentObj.startDate} - ${parentObj.endDate}"></c:out></td>
					</tr>	
				</c:forEach>				
		</table>
		</hdiits:fieldGroup>
	</c:forEach>
	</c:if>
	<c:if test="${empty allUser}">
		<table align="center" class="tabtable">
		<tr>
			<td colspan="10" align="center"><b><fmt:message key="HR.ACR.NORECORD" bundle="${commonLables}"/></b></td>
		</tr>
		</table>
	</c:if>
	</hdiits:fieldGroup>
	<Br><BR>
	<table align="center">
		<tr align="center">
			<td>
				<hdiits:button captionid="HR.ACR.Submit" bundle="${commonLables}" name="Submit" type="button"   onclick="validation();"/>		
			</td>
			<td>
				<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="Close" type="button"  onclick="closeButtonHandler();"/>
			</td>
		</tr>			
	</table>
	<hdiits:hidden name="year" id="year" default="${year}"/>			
</hdiits:form>	 
	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>
	<hdiits:validate locale="${locale}" controlNames="" />
</div>
</div>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>	