<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<%@page import="java.util.List"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>

<c:set var="notAdminUser" value="${resultValue.notAdminUser}" ></c:set>
<c:set var="loggedInUser" value="${resultValue.loggedInUser}" ></c:set>

<c:set var="empAllRec" value="${resultValue.empAllRec}" > </c:set>
<c:set var="empId" value="${resultValue.empId}" > </c:set>
<c:set var="empName" value="${resultValue.empName}" > </c:set>
<%

List actionList = (List) pageContext.getAttribute("actionList");

int size = actionList.size();

pageContext.setAttribute("listSize",size);

%>
<script type="text/javascript" >
function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	
	var url="./hrms.htm?actionFlag=getEmpLeaveData";
	
	document.Leaveview.action=url;
	document.Leaveview.submit();
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
		document.Leaveview.submit();
	}
	return true;
}
</script>
<body>
<form name="Leaveview" method="POST" >
<div id="tabmenu">
<ul id="maintab" class="shadetabs">              
<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="LEV.view" bundle="${enLables}"/></b></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">	  

 <div id="tcontent1" class="tabcontent">
   <c:choose>
		<c:when test="${empId ne null}">
	   
	  <a href= "./hrms.htm?actionFlag=fillLeaveCombo&empId=${empId}&empAllRec=Y"  id="insertOneLink">  Add new Entry </a>
	  </c:when>
			<c:otherwise>
		<a href= "./hrms.htm?actionFlag=fillLeaveCombo&empName=${empName}" id="inserLink" >  Add new Entry </a>
		</c:otherwise>
		</c:choose>
	  <br>
	  <br><br>
	<hdiits:form name="Leaveview" validate="true" method="POST"	action="./hrms.htm?actionFlag=getEmpLeaveData" encType="text/form-data">
	<c:if test="${empAllRec eq null and notAdminUser!=1}">
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
		<tr>
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<!--<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${enLables}"  name="details" onclick="return chkValue()"/>	
				--><hdiits:button type="button" captionid="viewAll" bundle="${enLables}"  name="viewAll" onclick="submitForm()"/>				
				</fieldset>
			</td>
		</tr>		
	</table>
	</c:if>
	</hdiits:form>
	  <display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">
	   <c:set value="${row.empLeaveId}" var="otherIdForLink"/>	
			<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" >
			<c:choose>
            <c:when test="${empAllRec ne null}"> 
			<a href="./hrms.htm?actionFlag=getEmpLeaveData&empLeaveId=${row.empLeaveId}&edit=Y&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" id="otherLink${row.empLeaveId}"> ${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname}</a>
			</c:when>
			<c:otherwise>
			<a href="./hrms.htm?actionFlag=getEmpLeaveData&empLeaveId=${row.empLeaveId}&edit=Y" id="otherLink${row.empLeaveId}"> ${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname}</a>
			</c:otherwise>
			</c:choose>
			</display:column>	
		   <fmt:formatDate var="stDate" pattern="dd/MM/yyyy" value="${row.leaveFromDate}"/>
		   <fmt:formatDate var="endDate" pattern="dd/MM/yyyy" value="${row.leaveToDate}"/>
		   <display:column class="tablecelltext" title="Start Date" value="${stDate}" headerClass="datatableheader" />    
		   <display:column class="tablecelltext" title="End Date" value="${endDate}" headerClass="datatableheader" />       
		  	<display:column class="tablecelltext" title="Leave Taken" headerClass="datatableheader"  value="${row.leaveTaken}"></display:column>	
			<display:setProperty name="export.pdf" value="true" />
	  </display:table>
	  

		
      
		<c:choose>  		 
  		     <c:when test="${listSize eq 1 && ((empName!=null && not empty empName) || empAllRec eq 'true') && ViewFlag ne 'True'}">
               <script>
                   document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
			 <c:otherwise>	
				<c:choose>
		             <c:when test="${listSize eq 0 && empAllRec ne 'true'&& empName != null && not empty empName}">
	`					<script>
						  var answer = confirm ("The Employee Is Not Being Added in Employee Leave Details. Do You Want to Add?  ")
						  if (answer)
							 document.getElementById("inserLink").click();
						  else
							 submitForm();
						</script>
		             </c:when>
		              <c:when test="${listSize eq 0 && empAllRec eq 'true'}">
		                 <script>
		                 var answer = confirm ("The Employee Is Not Being Added in Employee Leave Details. Do You Want to Add?  ")
						 if (answer)
							document.getElementById("insertOneLink").click();
						 else
						    submitForm();
			             </script>
			           </c:when>
		          </c:choose>
			</c:otherwise>
		 </c:choose>

      <BR>
  	  
  	<c:choose>
		<c:when test="${empId ne null}">
	   
	  <a href= "./hrms.htm?actionFlag=fillLeaveCombo&empId=${empId}&empAllRec=Y" id="insertOneLink">  Add new Entry </a>
	  </c:when>
			<c:otherwise>
				<a href= "./hrms.htm?actionFlag=fillLeaveCombo&empName=${empName}" id="inserLink" >Add new Entry </a>
			</c:otherwise>
		</c:choose>

  
  </div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
</div>
</body>
  
  	  <%
}
  	  catch(Exception e)
  	  {
  		  //System.out.println("There is some error:-");
  		  e.printStackTrace();
  	  }
  	  %>
  	  