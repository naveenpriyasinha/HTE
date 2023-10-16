<%@page import="com.tcs.sgv.eis.valueobject.HrEisBankDtls"%>
<%@page import="java.util.List"%>
<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="bankList" value="${resultValue.bankList}" > </c:set>
<c:set var="listSize" value="${resultValue.listSize}" > </c:set>
<c:set var="empList" value="${resValue.empList}" />
<c:set var="FromBasicDtlsNew" value="${resultValue.FromBasicDtlsNew}" > </c:set>
<c:set var="empName" value="${resultValue.empName}" />

</head>
<script type="text/javascript" >
function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	
	var url="./hrms.htm?actionFlag=getBankDtlsView";
	
	document.bankMstView.action=url;
	document.bankMstView.submit();
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
		document.bankMstView.submit();
	}
	return true;
}
</script>

<body>
<%
 List<HrEisBankDtls> actionList = (List)pageContext.getAttribute("actionList");
 HrEisBankDtls hrEisBankDtls = null;
 if(actionList!=null && actionList.size()!=0)
 {
  hrEisBankDtls = actionList.get(0);
  //System.out.println("branch name is " + hrEisBankDtls.getHrEisBranchMst().getBranchName());
 }
 
 int size = actionList.size();

 pageContext.setAttribute("listSize",size);
 
%>

<hdiits:form method="POST" name="bankMstView" action="./hrms.htm?actionFlag=getBankDtlsView" validate="true">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="TAB.NAME" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">	  
	<div id="tcontent1" class="tabcontent">
	 <div align="center"> <h1>
	
	 
	   <c:out value="${resultValue.msg}"/> <br> </h1> </div> <br>
		 
		 <c:if test="${FromBasicDtlsNew ne 'YES'}">
		  <a href= "./hrms.htm?actionFlag=getBankDtlsMaster&edit=show">  Add new Entry </a>
	</c:if>
	<c:if test="${FromBasicDtlsNew eq 'YES'}">
	<a href= "./hrms.htm?actionFlag=getBankDtlsMaster&edit=show&FromBasicDtlsNew=YES">  Add new Entry </a>
	</c:if>
	
	<table  width="85%" align="center" name="searchTable" id="searchTable">
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
			  <c:if test="${resultValue.listSize ne 0}">
		  <display:table name="${actionList}" requestURI="" pagesize="${pageSize}" id="row" export="true" style="width:100%">
		  <c:set value="${row.bankDtlId}" var="otherIdForLink"/>
			  <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader"> 			  
			      
			      <c:if test="${FromBasicDtlsNew ne 'YES'}">
			       <a href="./hrms.htm?actionFlag=getBankDtlsIdData&bankDtlsId=${row.bankDtlId}&edit=Y" id="otherLink${row.bankDtlId}">${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname} </a>
             </c:if>
             <c:if test="${FromBasicDtlsNew eq 'YES'}">
             <a href="./hrms.htm?actionFlag=getBankDtlsIdData&bankDtlsId=${row.bankDtlId}&edit=Y&FromBasicDtlsNew=YES" id="otherLink${row.bankDtlId}">${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname} </a>
             </c:if>
              
              </display:column>	
			  <display:column class="tablecelltext" title="Bank Name" headerClass="datatableheader">
			    ${row.hrEisBankMst.bankName}
              </display:column>
              <display:column class="tablecelltext" title="Branch Name" headerClass="datatableheader">
			    ${row.hrEisBranchMst.branchName}
              </display:column>
			 	<display:column property="bankAcctNo" class="tablecelltext" title="Account Number"  headerClass="datatableheader" />    			  		  
		  	  <display:setProperty name="export.pdf" value="true" />
  		  </display:table>
	   </c:if>
	   <br>
	   <c:if test="${listSize eq 0 && empName != null && not empty empName }">
  		 <a href= "./hrms.htm?actionFlag=getBankDtlsMaster&edit=show&empName=${empName}" id="BankLink">  Add new Entry </a>
  	   </c:if>
	   <c:choose>
  		  <%-- <c:when test="${listSize eq 1}">--%>
  		     <c:when test="${listSize eq 1 && empName!=null && not empty empName}">
               <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
		  <c:when test="${listSize eq 1 && FromBasicDtlsNew!=null && not empty FromBasicDtlsNew}">
               <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
		  <c:when test="${listSize eq 0 && empName != null && not empty empName}">
			<script>
				var answer = confirm ("The Employee Is Not Being Added in Bank Details. Do You Want to Add?  ")
				if (answer)
						document.getElementById("BankLink").click();
				else
				submitForm();
			</script>
		</c:when>
		 </c:choose> 
		 <c:if test="${FromBasicDtlsNew ne 'YES'}">
	   <a href= "./hrms.htm?actionFlag=getBankDtlsMaster&edit=show">  Add new Entry </a>
	   
	   </c:if>
	<c:if test="${FromBasicDtlsNew eq 'YES'}">
	<a href= "./hrms.htm?actionFlag=getBankDtlsMaster&edit=show&FromBasicDtlsNew=YES">  Add new Entry </a>
	</c:if>
	   
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
</div>
</hdiits:form>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</html>