<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="empAllRec" value="${resultValue.empAllRec}" ></c:set>
<c:set var="empId" value="${resultValue.empId}" ></c:set>
<c:set var="ViewFlag" value="${resultValue.ViewFlag}" ></c:set>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="contract" key="contract" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="fixed" key="fixed" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="permanent" key="permanent" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="probation" key="probation" bundle="${constantVariables}" scope="request"> </fmt:message>
<c:set var="empName" value="${resultValue.empName}" > </c:set>
<c:set var="FromBasicDtlsNew" value="${resultValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resultValue.otherId}" ></c:set>
<c:if test="${resultValue.msg =='1'}">
<script>
	alert("record successfully added");
</script>
</c:if>
<c:if test="${resultValue.msg =='2'}">
<script>
	alert("record updated successfully ");
</script>
</c:if>

<%

List actionList = (List) pageContext.getAttribute("actionList");

int size = actionList.size();

pageContext.setAttribute("listSize",size);

%>
<script type="text/javascript" >
function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpInfoSearch").value;
	
	var url="./hrms.htm?actionFlag=getEmpData";
	
	document.EmpInfo.action=url;
	document.EmpInfo.submit();
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_EmpInfoSearch").value;
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
		document.EmpInfo.submit();
	}
	return true;
}


</script>
	<%int i=1;%>
<body>	
<div id="childLock">
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="employeeMaster" bundle="${commonLables}"/></b></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">	  

 <div id="tcontent1" class="tabcontent"> 
	  	  	   <c:choose>
		<c:when test="${empAllRec=='true'}">
 	        <a href= "./hrms.htm?actionFlag=newEmpData&empId=${empId}&empAllRec=Y&newEntryEmpId=${empId}" id="insertOneLink">  Add new Entry </a>
        </c:when>
     	<c:otherwise>
     	<a href= "./hrms.htm?actionFlag=newEmpData&empName=${empName}"  id="inserLink">  Add new Entry </a>
     	</c:otherwise>
     	</c:choose>

	  <br>
	<hdiits:form name="EmpInfo" validate="true" method="POST"	action="./hrms.htm?actionFlag=getEmpData" encType="text/form-data">
		<c:set value="display:none" var="displayStyle"/>
	<c:if test="${empAllRec!='true'}">
		<c:set value="display:show" var="displayStyle"/>
		</c:if>
		
	<table  width="85%" align="center" style="${displayStyle}">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpInfoSearch"/>
						<jsp:param name="formName" value="EmpInfo"/>
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
	</hdiits:form>  
	
	
	
  <display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">

 <c:set value="${row.empId}" var="otherIdForLink"/>
	  <%--<display:column property="empId" class="tablecelltext" title="Employee Id"  headerClass="datatableheader" style="text-align:center"/>--%>    
	  <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" > 
	  		  	 <c:choose>
            <c:when test="${empAllRec eq 'true'}">
		  	 <a href="./hrms.htm?actionFlag=getEmpData&empId=${row.empId}&edit=Y&Employee_ID_EmpInfoSearch=${empId}&empAllRec=Y" id="otherLink${row.empId}"> ${row.orgEmpMst.empFname}  </a>
		  	 </c:when>
		  	 <c:when test="${FromBasicDtlsNew eq 'YES'}">
		  	  <a href="./hrms.htm?actionFlag=getEmpData&empId=${row.empId}&edit=Y&FromBasicDtlsNew=YES&otherId=${otherId}" id="otherLink${row.empId}">${row.orgEmpMst.empPrefix} ${row.orgEmpMst.empFname} ${row.orgEmpMst.empMname} ${row.orgEmpMst.empLname}</a>
		  	 </c:when>
		  	 
		  	 <c:otherwise>
	  <a href="./hrms.htm?actionFlag=getEmpData&empId=${row.empId}&edit=Y" id="otherLink${row.empId}">${row.orgEmpMst.empPrefix} ${row.orgEmpMst.empFname} ${row.orgEmpMst.empMname} ${row.orgEmpMst.empLname}</a>	  
		  	 </c:otherwise>
		  	 </c:choose>
	</display:column>	
	  <display:column property="email" class="tablecelltext" title="Email Id"  headerClass="datatableheader" />
	  <display:column property="contactNo" class="tablecelltext" title="Contact Number"  headerClass="datatableheader" />
	  <c:choose>
	  	<c:when test="${row.empType eq contract }">
	  		<c:set var="empType" value="Contract"/>
	  	</c:when>
	  	<c:when test="${row.empType eq fixed }">
	  		<c:set var="empType" value=" Fixed Pay "/>
	  
	  	</c:when>
	  	<c:when test="${row.empType eq permanent }">
	  		<c:set var="empType" value=" Permanent "/>
	  	</c:when>
	  	<c:when test="${row.empType eq probation }">
	  		<c:set var="empType" value=" Probation "/>
	  	</c:when>
	  </c:choose>
  	  <display:column class="tablecelltext" title="Employee Type"  headerClass="datatableheader" ><c:out value="${empType}"/></display:column>

	
      
     
     
  
  	  <display:setProperty name="export.pdf" value="true" />
  	  </display:table>
 	<c:choose>
  		 <c:when test="${listSize eq 1 and ViewFlag ne 'true' && empName != null && not empty empName }">
				<script>
						document.getElementById("otherLink${otherIdForLink}").click();
				</script>
		 </c:when>
	<c:when test="${listSize eq 1 && FromBasicDtlsNew != null && not empty FromBasicDtlsNew }">
				<script>
						document.getElementById("otherLink${otherIdForLink}").click();
				</script>
		 </c:when>
	
		 <c:otherwise>	
			 <c:choose>
				<c:when test="${empId ne 0 and empAllRec eq 'true'}">
					<script>
						document.getElementById("insertOneLink").click();
					</script>
				</c:when>
				<c:when test="${listSize eq 0 && empName != null && not empty empName }">
					<script>
						var answer = confirm ("Employee Details Is Not Being Added. Do You Want to Add?  ")
						if (answer)
							document.getElementById("inserLink").click();
						else
							submitForm();
					</script>
				</c:when>
				<c:when test="${listSize eq 0 && empAllRec eq  'true'}">
				<script>
					var answer = confirm ("Employee Details Is Not Being Added. Do You Want to Add?  ")
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
		<c:when test="${empAllRec=='true'}">
 	        <a href= "./hrms.htm?actionFlag=newEmpData&empId=${empId}&empAllRec=Y" id="insertOneLink">  Add new Entry </a>
        </c:when>
     	<c:otherwise>
     	<a href= "./hrms.htm?actionFlag=newEmpData&empName=${empName}"  id="inserLink">  Add new Entry </a>
     	</c:otherwise>
     	</c:choose>

  
  </div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
</div>
</div>
  </body>
  	  <%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
  	  %>
  	  