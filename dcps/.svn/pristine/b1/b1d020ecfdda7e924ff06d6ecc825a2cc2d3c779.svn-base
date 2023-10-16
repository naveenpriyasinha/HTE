<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="selectedUserId" value="${resultValue.userId}" ></c:set>
<c:set var="flag" value="${resultValue.flag}" ></c:set>
<c:set var="strDepartmentName" value="${resultValue.strDepartmentName}" />

<script type="text/javascript">
	var flag = '${flag}';
	function submitForm()
	{   
		var userId = document.getElementById("userId").value;
		var deptName='${strDepartmentName}';
		if(deptName=='HOMEGUARD')
		{
			document.responseForm.action="hrms.htm?actionFlag=addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false&userId="+userId+"&departmentName=HOMEGUARD";
		}
		else
		{
			document.responseForm.action="hrms.htm?actionFlag=addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false&userId="+userId;
		}
		document.responseForm.submit();
	}

	function closeWindow()
	{
		if (flag == 'true')
		{
			window.close();
		}
		else
		{
			document.responseForm.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		   	document.responseForm.submit();
		}
	}
</script>
<hdiits:form name="responseForm" validate="true" method="POST" >
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.MessagePage" bundle="${empEditListCommonLables}"/></b></a></li>
	</ul>
	</div>
	<div id="CoCurricularDtls" name="CoCurricularDtls">
	<div id="tcontent1" class="tabcontent" tabno="0">
<br>
<br>
<br>
<br>
<br>
      <hr align="center" width=" 50%">
			<center><b><hdiits:caption captionid="eis.OnDataSubmit" bundle="${empEditListCommonLables}"/></b></center>
	  <hr align="center" width="50%">
	  
	  <table width="100%">
	  <tr align="center">
	  	<td>
		  <c:if test="${flag eq 'true'}">
		    <hdiits:button name="btnSubmit" type="button" captionid="eis.back" bundle="${empEditListCommonLables}"  onclick="submitForm()"></hdiits:button>
		  </c:if>
		 <hdiits:button name="cancel" type="button" captionid="EIS.CloseButton" bundle="${empEditListCommonLables}" onclick="closeWindow()"></hdiits:button>
		</td>
		</tr>
		</table>		  
	  
	  </div>
	  </div>
	  <hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
	
	  <script type="text/javascript">	
		initializetabcontent("maintab")
	</script>
	</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

