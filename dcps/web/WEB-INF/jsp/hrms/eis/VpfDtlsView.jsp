<%@page import="java.util.List"%>
<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="empList" value="${resultValue.empList}" > </c:set>
<c:set var="langId" value="${resultValue.langId}" > </c:set>
<c:set var="empAllRec" value="${resultValue.empAllRec}" > </c:set>
<c:set var="empId" value="${resultValue.empId}" > </c:set>
<c:set var="empName" value="${resultValue.empName}" > </c:set>
<c:set var="FromBasicDtlsNew" value="${resultObj.resultValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resultObj.resultValue.otherId}" ></c:set>	
</head>
<%
 List actionList = (List)pageContext.getAttribute("actionList");
 
 int size = actionList.size();

 pageContext.setAttribute("listSize",size);
 
%>
<script type="text/javascript" >
function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	
	var url="./hrms.htm?actionFlag=getVPFView";
	
	document.vpfDtlsView.action=url;
	document.vpfDtlsView.submit();
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	if(empId=="")
	{
		alert("Please search the employee first");
		return false;
	}
	else
	{
		return true;
	}
	
}
function submitFormAuto()
{
	if(chkValue())
	{
		document.vpfDtlsView.submit();
	}
	return true;
}
</script>
<body>

<form method="POST" name="vpfDtlsView">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="VPF.viewMaster" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">	  
	<div id="tcontent1" class="tabcontent">
	 <div align="center"> <h1>
	   <c:out value="${resultValue.msg}" /> <br> </h1> </div> <br>
	    <c:choose>
		<c:when test="${empId ne null}">
				  <a href= "./hrms.htm?actionFlag=getVPFEmployees&empId=${empId}&empAllRec=Y" id="vpfOneLink">  Add new Entry </a>
		</c:when>
		<c:otherwise>
		  <a href= "./hrms.htm?actionFlag=getVPFEmployees&empName=${empName}" id="vpfLink">  Add new Entry </a>
		  </c:otherwise>
		  </c:choose>
	  <br><br>
	<hdiits:form name="vpfDtlsView" validate="true" method="POST"	action="./hrms.htm?actionFlag=getVPFView" encType="text/form-data">
	<c:if test="${empAllRec eq null}">
	<table  width="85%" align="center">
	
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpLoanSearch"/>
						<jsp:param name="formName" value="empLoandetail"/>
						<jsp:param name="functionName" value="submitFormAuto"/>
					</jsp:include>
			</td>
		</tr>
		<c:if test="${listSize ge 2}">
		<c:set value="display:none" var="displayStyle"/>
		</c:if>
		<tr style="${displayStyle}">
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<!--<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="return chkValue()"/>	
				--><hdiits:button type="button" captionid="viewAll" bundle="${commonLables}"  name="viewAll" onclick="submitForm()"/>				
				</fieldset>
			</td>
		</tr>	
	</table>
	</c:if>
	</hdiits:form>

		  <display:table name="${actionList}" requestURI=""   pagesize="${pageSize}"  id="row" export="true" style="width:100%">
		  <c:set value="${row.payVpfId}" var="otherIdForLink"/>
		  <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader">
		   <c:choose>
            <c:when test="${empAllRec ne null}">
			  <a href="./hrms.htm?actionFlag=getVpfDtlsById&vpfId=${row.payVpfId}&edit=Y&empId=${empId}&empAllRec=Y" id="otherLink${row.payVpfId}">${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname}</a>              
            </c:when>
            <c:otherwise>
			 <a href="./hrms.htm?actionFlag=getVpfDtlsById&vpfId=${row.payVpfId}&edit=Y&FromBasicDtlsNew=${FromBasicDtlsNew}&otherId=${otherId}" id="otherLink${row.payVpfId}">${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname}</a>    
			  </c:otherwise>
			  </c:choose>
		  </display:column>	

			
			  
			  <display:column class="tablecelltext" property="currencyvpfAmt"  style="text-align:right" title="Voluntary Provident Fund Amount" headerClass="datatableheader" />
			  
		  	  <display:setProperty name="export.pdf" value="true" />
  		  </display:table>
  		                  
		 <br>
	   	 <c:choose>
		<c:when test="${(listSize eq 1) && ((empName != null && not empty empName) || (empAllRec eq  'true'))}">
			<script>
				document.getElementById("otherLink${otherIdForLink}").click();
			</script>
		</c:when>
		<c:when test="${(listSize eq 1) && FromBasicDtlsNew != null && not empty FromBasicDtlsNew}">
				<script>
					document.getElementById("otherLink${otherIdForLink}").click();
				</script>
			</c:when>
		<c:when test="${listSize eq 0 && empName != null && not empty empName }">
				<script>
					var answer = confirm ("Employee Is Not Being Added In VPF Details. Do You Want to Add ?")
					if (answer)
							document.getElementById("vpfLink").click();
					else
					submitForm();
				</script>
		</c:when>
		<c:when test="${listSize eq 0 && empAllRec eq  'true'}">
				<script>
					var answer = confirm ("Employee Is Not Being Added In VPF Details. Do You Want to Add ?")
					if (answer)
							document.getElementById("vpfOneLink").click();
					else
					submitForm();
				</script>
		</c:when>
		</c:choose>
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
	</script>
</div>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</html>