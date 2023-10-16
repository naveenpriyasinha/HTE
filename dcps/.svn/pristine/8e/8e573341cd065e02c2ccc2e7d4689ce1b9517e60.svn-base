 <%
	try
	{
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<hdiits:form name="WFEMPSRCH" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	           		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.EmpSrch" bundle="${fmsLables}"/></a></li>
	</ul>
</div>

<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
								<jsp:param name="SearchEmployee" value="EMPLOYEESEARCH"/>
								<jsp:param name="formName" value="testing"/>
								<jsp:param name="mandatory" value="Yes"/>
								<jsp:param name="searchEmployeeTitle" value="Select Employee to Send"/>
		</jsp:include>
		<br>
		<center><hdiits:button id="bt_id" name="button" type="button" value="OK" onclick="confirmEmployee()"/></center>
	</div>
</div>

<script type="text/javascript"> 
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script> 
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>		
<script type="text/javascript">

	function confirmEmployee()
	{
		if(document.getElementById('Employee_ID_EMPLOYEESEARCH').value != '')
		{
			//var emp_id = document.getElementById('Employee_ID_EMPLOYEESEARCH').value;
			var emp_name = document.getElementById('Employee_Name_EMPLOYEESEARCH').value;
			var user_id = document.getElementById('USER_ID_EMPLOYEESEARCH').value;
			if(window.opener.parent.document.forms[0].loggedInUserId.value != user_id)
			{
				if(confirm("Do you want to forward file to "+emp_name+"?"))
				{
					window.opener.parent.document.forms[0].UserIdFromEmpSrch.value = user_id;
					if(window.opener.parent.document.forms[0].DecisionFlag.value == 'corr')
					{
						window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=forwardCorrespondence";
					}
					else if(window.opener.parent.document.forms[0].DecisionFlag.value == 'file')
					{
						window.opener.parent.document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=forwardFile";
					}
					window.opener.document.getElementById("Converted_text").value = '';
					window.opener.forwardjob();
					//window.opener.parent.document.forms[0].submit();
					window.close();
				}
				else
				{
					return false;
				}
			}
			else
			{
				if(window.opener.parent.document.forms[0].DecisionFlag.value == 'corr')
					alert("Correspondence can't be forwarded to logged in user");
				else
					alert("File can't be forwarded to logged in user");
				return false;
			}
		}
		else
		{
			alert("Please Select Any Employee \nClick 'Search Employee' Button To Search Employee");
			return false;
		}
	}
</script>
</hdiits:form>

<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>