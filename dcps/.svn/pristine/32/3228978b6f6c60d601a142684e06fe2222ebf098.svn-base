<%
try{
%>

<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@page import="java.util.List"%><fmt:setBundle basename="resources.eis.eisLables" var="commonLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>  
<c:set var="actionList" value="${resultValue.userEntryList}" > </c:set>
<%

List actionList = (List) pageContext.getAttribute("actionList");
List lstNotUpdatedRecords = (List) pageContext.getAttribute("lstNotUpdatedRecords");
int size = actionList.size();

pageContext.setAttribute("listSize",size);

%>

<script type="text/javascript">


	function submitForm()
{


	var empId=document.getElementById('Employee_ID_NewUserSearch').value;

	var url="./hrms.htm?actionFlag=employeeEntryView";

	
	document.EmployeeViewList.action=url;
	document.EmployeeViewList.submit();
}
	
	
	
	
	function chkValue()
{
	var empId=document.getElementById("Employee_ID_NewUserSearch").value;

	if(empId=="")
	{
		alert("Please search the employee first");
		return false;
	}
	else
	{
		//alert('inside else');
		return true;
	}
	
}
	

</script>

	
<hdiits:form  name="EmployeeViewList"  method="POST" validate="true" action="./hrms.htm?actionFlag=employeeEntryView&edit=Y"  encType="text/form-data" >	
	
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"> New User Creation</a></li>
		</ul>
	</div>
	
	<div class="tabcontentstyle">	  
	
	<div id="tcontent1" class="tabcontent">

	<table  width="85%" align="center">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="NewUserSearch"/>
						<jsp:param name="formName" value="EmployeeViewList"/>
					</jsp:include>
			</td>
		</tr>
		<tr >
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="return chkValue()"/>	
				<hdiits:button type="button" captionid="viewAll" bundle="${commonLables}"  name="viewAll" onclick="submitForm()"/>				
				</fieldset>
			</td>
		</tr>	
	</table>
	
	
	</hdiits:form>
	
	
   
	
		<br>
		<a href= "./hrms.htm?actionFlag=showEmployeeEntry" >  Add new Entry </a>
		<br>	
		  
	  	<display:table name="${actionList}" requestURI=""  defaultsort="1" defaultorder="ascending" id="row" export="true" style="width:100%" pagesize="15">
	     <c:set value="${row.userId}" var="otherIdForLink"/>	
		 
		  <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader"> 
		 <a href="./hrms.htm?actionFlag=showEmployeeEntry&userId=${row.userId}" id="otherLink${row.userId}">${row.empFname} ${row.empLname}</a> 
			</display:column>	
			
			<display:column class="tablecelltext" title="User Name" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.userName}</display:column>
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
	 	  
	  	<a href= "./hrms.htm?actionFlag=showEmployeeEntry" id="insertOneLink">  Add new Entry </a>
 
 	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
</div>
<%
	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}
%>
  	  