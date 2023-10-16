<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@page import="java.util.List"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

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


<%

List actionList = (List) pageContext.getAttribute("actionList");

int size = actionList.size();

pageContext.setAttribute("listSize",size);

%>	
<script type="text/javascript" >
function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	
	var url="./hrms.htm?actionFlag=getGpfDtls";
	
	document.GpfAcctView.action=url;
	document.GpfAcctView.submit();
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
		document.GpfAcctView.submit();
	}
	return true;
}

</script>
<body   onmousemove="checkChildWindow()">
<form name="GpfAcctView" method="POST" >
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="GPF.GPFACCTDTLS" bundle="${enLables}"/></b></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">	  

 <div id="tcontent1" class="tabcontent">
   
	   
	  <a href= "./hrms.htm?actionFlag=getGpfNewPage">  Add new Entry </a>
	  <br>
	  <br><br>
	<hdiits:form name="GpfAcctView" validate="true" method="POST"	action="./hrms.htm?actionFlag=getGpfDtls" encType="text/form-data">
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
				--><hdiits:button type="button" captionid="viewAll" bundle="${enLables}"  name="viewAll" onclick="submitForm()"/>				
				</fieldset>
			</td>
		</tr>	
	</table>
	</hdiits:form>
	  <display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">
	  <c:set value="${row.userId}" var="otherIdForLink"/>
			<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" >
			<a href="./hrms.htm?actionFlag=getGpfDtls&userID=${row.userId}&edit=Y" id="otherLink${row.userId}"> ${row.empName}</a>
			</display:column>	
		  	<display:column class="tablecelltext" title="GPF Account No" headerClass="datatableheader"  value="${row.gpfAcctNo}"></display:column>	
			<display:setProperty name="export.pdf" value="true" />
	  </display:table>
	  <c:choose>
  		  <%-- <c:when test="${listSize eq 1}">--%>
  		 
  		     <c:when test="${listSize eq 1}">
               <script>
                   
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
			 <c:otherwise>	
				  <c:choose>
		              <c:when test="${userId ne 0}">
		                 <script>
			                document.getElementById("insertOneLink").click();
			             </script>
			         </c:when>
		             <c:otherwise>
			             <script>
                              //		document.getElementById("inserLink").click();
		                 </script>
		             </c:otherwise>
		          </c:choose>
			</c:otherwise>
		 </c:choose> 
      
      <BR>
  	  
  	  <a href= "./hrms.htm?actionFlag=getGpfNewPage" id="insertOneLink">  Add new Entry </a>

  
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
  	  