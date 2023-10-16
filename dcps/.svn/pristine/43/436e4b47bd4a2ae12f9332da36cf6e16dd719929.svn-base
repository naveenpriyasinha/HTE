<%
try{
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<!--  -->
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript" >
//alert('hi.. in other view list');

function submitForm()
{
	
	var empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	
	
	var url="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES";
	
	document.EmpOtherInfo.action=url;
	document.EmpOtherInfo.submit();
}
function chkValue()
{

	var empId="";
	if(document.getElementById("Employee_ID_EmpOtherSearch").value==document.getElementById("Employee_Name_EmpOtherSearch").value)
	{
	
	 empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	}
	else
	{
				
		empId=document.getElementById("Employee_Name_EmpOtherSearch").value;
	}
	

	document.getElementById("Employee_ID_EmpOtherSearch").value=empId;
	
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
		document.EmpOtherInfo.submit();
	}
	return true;
}
</script>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="empAllRec" value="${resultValue.empAllRec}" > </c:set>
<c:set var="empId" value="${resultValue.empId}" > </c:set>
<c:set var="ViewFlag" value="${resultValue.ViewFlag}" ></c:set>
<c:set var="empName" value="${resultValue.empName}" > </c:set>

<%

List actionList = (List) pageContext.getAttribute("actionList");

int size = actionList.size();

pageContext.setAttribute("listSize",size);

%>
<body>
    <div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OT.otherDetailMaster" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin">	  
	
	 		<c:out value="${resultValue.msg}" /> 
		
		<hdiits:form name="EmpOtherInfo" validate="true" method="POST"	action="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES" encType="text/form-data">
		<c:if test="${empId eq 0}">
	<table  width="85%" align="center" border="1">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpOtherSearch"/>
						<jsp:param name="formName" value="EmpOtherInfo"/>
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
<br>
		
		<br>
		  <display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="false" style="width:100%">

<c:set value="${row.otherId}" var="otherIdForLink"/>
		  	 <display:column style="text-align: center;font-size:12px;" class="tablecelltext" title="Employee Name" headerClass="datatableheader">
		  	 <c:choose>
            <c:when test="${empAllRec eq 'true'}">
		  	 <a href="./ifms.htm?actionFlag=getOtherDataMerged&otherId=${row.otherId}&edit=Y&empId=${empId}&empAllRec=true&MergedScreen=YES&PreviewBill=YES&elementId=9000202" id="otherLink${row.otherId}"> ${row.empName}  </a>
		  	 </c:when>
		  	 <c:otherwise>
		  	 		  	 <a href="./ifms.htm?actionFlag=getOtherDataMerged&otherId=${row.otherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES&elementId=9000202"   id="otherLink${row.otherId}"> ${row.empName}  </a>
		  	 </c:otherwise>
		  	 </c:choose>
		  	 
		  	 </display:column>	

			  <display:column class="tablecelltext"  style="text-align: center;font-size:12px;"  title="Designation" headerClass="datatableheader" value="${row.dsgnName}"  > </display:column>	
			  <display:column class="tablecelltext" style="text-align: center;font-size:12px;"  title="Group" headerClass="datatableheader" value="${row.gradeName}"  > </display:column>	
			  <display:column class="tablecelltext" style="text-align: center;font-size:12px;"  title='<span>Current Basic<Font size="3" face="Rupee Foradian"> (<Font size="3" face="Rupee Foradian">`</Font>)</Font> </span>' headerClass="datatableheader"  value="${row.currencycurrentBasic}"  > </display:column>	
		  	  <display:setProperty name="export.pdf" value="false" />
  		  </display:table>
  
		<BR>
	 <c:choose>
  		  <%-- <c:when test="${listSize eq 1}">--%>
             <c:when test="${listSize eq 1 && ((empName!=null && not empty empName) || empAllRec eq 'true') && ViewFlag ne 'True'}">
  		 <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
  		 </script>
			 </c:when>
			  <c:otherwise>	
				<c:choose>
		             <c:when test="${listSize eq 0 && empAllRec ne 'true'&& empName != null && not empty empName}">
	`					<script>
						  var answer = confirm ("The Employee Is Not Being Added in Employee Basic Details. Do You Want to Add?  ")
						  if (answer)
							 document.getElementById("inserLink").click();
						  else
							 submitForm();
						</script>
		             </c:when>
		              <c:when test="${listSize eq 0 || empId ne 0  && empAllRec eq 'true'}">
		                 <script>
		                 var answer = confirm ("The Employee Is Not Being Added in Employee Basic Details. Do You Want to Add?  ")
						 if (answer)
							document.getElementById("insertOneLink").click();
						 else
						    submitForm();
			             </script>
			           </c:when>
		          </c:choose>
			</c:otherwise>
		 </c:choose>
	 
	
 
 	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
//		alert('hi..');
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