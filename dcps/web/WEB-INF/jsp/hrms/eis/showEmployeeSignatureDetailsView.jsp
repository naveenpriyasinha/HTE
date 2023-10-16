<%@page import="com.tcs.sgv.eis.valueobject.HrPaySignatureDtls"%>
<%
try
{
%>
<%@page import="java.util.*"%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="empName" value="${resultValue.empName}" > </c:set>
<c:set var="flg" value="${resultValue.flg}" > </c:set>
<script type="text/javascript" >
function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	var url="./hrms.htm?actionFlag=getEmployeeSgnDetailsView";
	document.frmEmpSgnDetailsView.action=url;
	document.frmEmpSgnDetailsView.submit();
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
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
		document.frmEmpSgnDetailsView.submit();
	}
	return true;
}

</script>
<body>
<%
 List<HrPaySignatureDtls> actionList = (List)pageContext.getAttribute("actionList");
HrPaySignatureDtls employeeSignatureDtls = null;
 if(actionList!=null && actionList.size()!=0)
 {
	 employeeSignatureDtls = actionList.get(0);
 }
 int size = actionList.size();
 pageContext.setAttribute("listSize",size);
%>

<hdiits:form method="POST" name="frmEmpSgnDetailsView" action="./hrms.htm?actionFlag=getEmployeeSgnDetailsView" validate="true">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ESD.emp_sgn_dtls" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">	
	<div id="tcontent1" class="tabcontent">  
	<br>
    <a href= "./hrms.htm?actionFlag=getEmployeeSgnDetailsMaster&edit=show">  Add new Entry </a>

<table  width="85%" align="center" name="searchTable" id="searchTable">
	<tr>
		<td >
			<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
					<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
					<jsp:param name="SearchEmployee" value="EmpSearch"/>
					<jsp:param name="formName" value="frmEmpSgnDetailsView"/>
					<jsp:param name="functionName" value="submitFormAuto"/>
				</jsp:include>
		</td>
  </tr>
</table>
<br>
<table width="10%" border="3" bordercolor=#ccccff bgcolor="white" align="center">
	<tr>
		<td align="center" font size="3" face="VERDANA"	color="navy">
		 	<hdiits:button type="button"  captionid="viewAll" value="  V I E W  A L L   " name="viewAll" onclick="submitForm()"/>
		</td>
	</tr>
</table>
	 <c:if test="${resultValue.listSize ne 0}">
		  <display:table name="${actionList}" requestURI="" pagesize="10" id="row" export="true" style="width:100%">
		<c:set value="${row.id}" var="otherIdForLink"/>
			   <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader"> 			  
			      <a href="./hrms.htm?actionFlag=getEmployeeSgnDtlsData&Id=${row.id}&edit=Y" id="otherLink${row.id}">${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname} </a>  
              </display:column>	
			  <display:column class="tablecelltext" title="Designation" headerClass="datatableheader">
			    ${row.orgDesignationMst.dsgnName}
              </display:column>
              <fmt:formatDate var="startDate" pattern="dd/MM/yyyy" value="${row.startDate}"/>
			    <display:column class="tablecelltext" title="Start Date" value="${startDate}" headerClass="datatableheader" />
              <fmt:formatDate var="endDate" pattern="dd/MM/yyyy" value="${row.endDate}"/>
			    <display:column class="tablecelltext" title="End Date" value="${endDate}" headerClass="datatableheader" />
           	  <display:setProperty name="export.pdf" value="true" />
  		  </display:table>
  		  			    
	 </c:if>
	 <br>
	 <c:if test="${listSize eq 0 && empName != null && not empty empName }">
  		 <a href= "./hrms.htm?actionFlag=getEmployeeSgnDetailsMaster&edit=show&empName=${empName}" id="SgnLink">  Add new Entry </a>
  	   </c:if>
	 <c:choose>
   		     <c:when test="${(listSize eq 1) &&  (flg eq 1)}">
               <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
			 <c:when test="${listSize eq 0 && empName != null && not empty empName}">
			<script>
				var answer = confirm ("The Employee Is Not Being Added in Employee Signature Details. Do You Want to Add?  ")
				if (answer)
						document.getElementById("SgnLink").click();
				else
				submitForm();
			</script>
		</c:when>
	 </c:choose> 
	 <a href= "./hrms.htm?actionFlag=getEmployeeSgnDetailsMaster&edit=show">  Add new Entry </a>
	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")			
	</script>
	</div>
</hdiits:form>
</body>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
