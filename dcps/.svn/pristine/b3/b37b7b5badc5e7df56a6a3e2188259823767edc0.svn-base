<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<%@page import="java.util.List"%><script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/eis/miscRecoverView.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<script type="text/javascript">
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.miscList}" > </c:set>
<c:set var="empName" value="${resultValue.empName}" > </c:set>
<c:set var="updatePaybillFlg" value="${resultValue.updatePaybillFlg}" ></c:set>
<c:set var="paybillMonth" value="${resultValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resultValue.paybillYear}" ></c:set>
<c:set var="FromBasicDtlsNew" value="${resultObj.resultValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resultObj.resultValue.otherId}" ></c:set>
<%

List actionList = (List) pageContext.getAttribute("actionList");
int size = actionList.size();

pageContext.setAttribute("listSize",size);

if(size>1)
	pageContext.setAttribute("miscVO",actionList.get(0));	


%>

<body>
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="MISC.miscView" bundle="${enLables}"/></b></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">	  

 <div id="tcontent1" class="tabcontent">
<tr>
	     <c:if test="${updatePaybillFlg eq 'y'}" >
	     <a href="./hrms.htm?actionFlag=fillPaybillData&paybillYear=${paybillYear}&paybillMonth=${paybillMonth}&updatePaybillEmpId=${miscVO.hrEisEmpMst.orgEmpMst.empId}&searchData=y">Back to update pay bill</a>
	     </c:if>
</tr>
<br>
	  <a href= "./hrms.htm?actionFlag=fillMiscRecoverCombo" >  Add new Entry </a>
	  <br><br>
	<hdiits:form name="miscRecover" validate="true" method="POST"	action="./hrms.htm?actionFlag=getMiscData" encType="text/form-data">
	<table  width="85%" align="center">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="miscSearch"/>
						<jsp:param name="formName" value="miscRecover"/>
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
	</hdiits:form>
	     <display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">
	     <c:set value="${row.miscId}" var="otherIdForLink"/>
			<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" >
		  	<a href="./hrms.htm?actionFlag=getMiscData&miscId=${row.miscId}&FromBasicDtlsNew=${FromBasicDtlsNew}&otherId=${otherId}&edit=Y&updatePaybillFlg=${updatePaybillFlg}&paybillMonth=${paybillMonth}&paybillYear=${paybillYear}" id="otherLink${row.miscId}">  ${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname}</a>
		  	</display:column>	
		  	 <fmt:formatDate var="miscDate" pattern="dd/MM/yyyy" value="${row.recoverDate}"/>
		  	<display:column class="tablecelltext" title="Date" headerClass="datatableheader"  value="${miscDate}"  > </display:column>	
			<display:column class="tablecelltext" title="Recoverable Amount" headerClass="datatableheader"  value="${row.currencyrecoverAmt}"  > </display:column>
			<c:if test='${row.miscActivateFlag eq 1}'>
				<display:column class="tablecelltext" title="Status" headerClass="datatableheader"  value="Active"  > </display:column>
			</c:if>
			<c:if test='${row.miscActivateFlag ne 1}'>
				<display:column class="tablecelltext" title="Status" headerClass="datatableheader"  value="Inactive"  > </display:column>
			</c:if>
	  	  <display:setProperty name="export.pdf" value="true" />
	  	 </display:table>
	  	  <c:if test="${listSize eq 0 && empName != null && not empty empName}">
  		    <a href= "./hrms.htm?actionFlag=fillMiscRecoverCombo&empName=${empName}" id="MiscRecLink">  Add new Entry </a>
  	     </c:if>
	  	 <c:choose>
  		  <%-- <c:when test="${listSize eq 1}">--%>
  		     <c:when test="${listSize eq 1 && empName != null && not empty empName}">
               <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
			 <c:otherwise>	
				  <c:choose>
				   <c:when test="${listSize eq 0 && empName != null && not empty empName}">
	`					<script>
						  var answer = confirm ("The Employee Is Not Being Added in Miscellaneous Recovery Details. Do You Want to Add?  ")
						  if (answer)
							 document.getElementById("MiscRecLink").click();
						  else
							 submitForm();
						</script>
		             </c:when>
		              <c:when test="${miscId ne 0}">
		                 <script>
			                document.getElementById("insertOneLink").click();
			             </script>
			         </c:when>
		          </c:choose>
			</c:otherwise>
		 </c:choose> 
      <BR>
  	  
  	<a href= "./hrms.htm?actionFlag=fillMiscRecoverCombo" id="insertOneLink">  Add new Entry </a>

  
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
  		  e.printStackTrace();
  	  }
  	  %>
  	  