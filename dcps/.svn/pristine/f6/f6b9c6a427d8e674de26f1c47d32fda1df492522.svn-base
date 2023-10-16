<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<%@page import="java.util.List"%><fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>




<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.punishmentList}" > </c:set>
<%

 List actionList = (List) pageContext.getAttribute("actionList");

int size = actionList.size();

pageContext.setAttribute("listSize",size);

%>
<script type="text/javascript" >
function submitForm()
{
	var empId=document.getElementById("Employee_ID_punishmentSearch").value;
	var url="./hrms.htm?actionFlag=getPunishmentData";
	
	document.PunishmentView.action=url;
	document.PunishmentView.submit();
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_punishmentSearch").value;
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
		document.PunishmentView.submit();
	}
	return true;
}
</script>


<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

	

  
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="PMT.Punishment" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
<div class="tabcontentstyle">	  
	
	<div id="tcontent1" class="tabcontent">   
		
		<br>
		<a href= "./hrms.htm?viewName=empPunishmentMaster">  Add new Entry </a>
		<br>	
		 
	  	  <br><br>
	<hdiits:form name="PunishmentView" validate="true" method="POST"	action="./hrms.htm?actionFlag=getPunishmentData" encType="text/form-data">
	<table  width="85%" align="center">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="punishmentSearch"/>
						<jsp:param name="formName" value="PunishmentView"/>
					<jsp:param name="functionName" value="submitFormAuto"/>
					</jsp:include>
			</td>
		</tr>

		
		<tr>
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<!--<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${enLables}"  name="details" onclick="return chkValue()"/>	
				--><hdiits:button type="button" captionid="viewAll" bundle="${commonLables}"  name="viewAll" onclick="submitForm()"/>				
				</fieldset>
			</td>
		</tr>
	</table>
		</hdiits:form>
		<BR>
		
		 <display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">
            <c:set value="${row.punishmentId}" var="otherIdForLink"/>
			<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" >
           	<a href="./hrms.htm?actionFlag=getPunishmentData&pmtId=${row.punishmentId}&edit=Y" id="otherLink${row.punishmentId}" > ${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname}</a>
		 	</display:column>
		    <fmt:formatDate var="StartDate" pattern="dd/MM/yyyy" value="${row.startDate}"/>
		  	<display:column class="tablecelltext" title="Start Date" headerClass="datatableheader"  value="${StartDate}"  > </display:column>	
	  	    <fmt:formatDate var="EndDate" pattern="dd/MM/yyyy" value="${row.startDate}"/>
		  	<display:column class="tablecelltext" title="End Date" headerClass="datatableheader"  value="${EndDate}"  > </display:column>	
	  	  
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
		              <c:when test="${punishmentId ne 0}">
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
	 	  
	  	<a href= "./hrms.htm?viewName=empPunishmentMaster" id="insertOneLink">  Add new Entry </a>
 
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
  	  