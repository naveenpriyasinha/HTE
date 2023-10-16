<%
try{
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<%@page import="java.util.List"%><script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>

<c:set var="actionList" value="${resultValue.quarterDtlsList}" > </c:set>
<c:set var="FromBasicDtlsNew" value="${resultValue.FromBasicDtlsNew}" > </c:set>
<c:set var="otherId" value="${resultValue.otherId}" > </c:set>
<c:set var="empName" value="${resultValue.empName}" > </c:set>
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<fmt:setBundle basename="resources.Constants" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" >

function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpQtrSearch").value;
	
	var url="./hrms.htm?actionFlag=getQuarterDtls&edit=x";
	
	document.EmpQtrInfo.action=url;
	document.EmpQtrInfo.submit();
}
function chkValue()
{

	var empId="";
	if(document.getElementById("Employee_ID_EmpQtrSearch").value==document.getElementById("Employee_Name_EmpQtrSearch").value)
    {

       empId=document.getElementById("Employee_ID_EmpQtrSearch").value;
    }
   else
   {

		
	empId=document.getElementById("Employee_Name_EmpQtrSearch").value;
   }

   document.getElementById("Employee_ID_EmpQtrSearch").value=empId;


   
	empId=document.getElementById("Employee_ID_EmpQtrSearch").value;

	
	if(empId==""){
		alert("Please search the employee first");
		return false;
	}
	else {
		return true;
	}	
}
function submitFormAuto()
{
	if(chkValue())
	{
		document.EmpQtrInfo.submit();
	}
	return true;
}
</script>

<%

List actionList = (List) pageContext.getAttribute("actionList");

int size = actionList.size();

pageContext.setAttribute("listSize",size);

%>

<body>
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="qtr.quarterMasterView" bundle="${enLables}"/></b></a></li>
		</ul>
	</div>
		  
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	   <br> 
	   <hdiits:form name="EmpQtrInfo" validate="true" method="POST"	action="./hrms.htm?actionFlag=getQuarterDtls&edit=x" encType="text/form-data">
	<table  width="85%" align="center" border="1">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpQtrSearch"/>
						<jsp:param name="formName" value="EmpQtrInfo"/>
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
				<!--<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${enLables}"  name="details" onclick="return chkValue()"/>	
				--><hdiits:button type="button" captionid="viewAll" bundle="${enLables}"  name="viewAll" onclick="submitForm()"/>				
				</fieldset>
			</td>
			
		</tr>		

	</table>
	</hdiits:form>
		  <br>
		
		
	<c:if test="${FromBasicDtlsNew ne 'YES'}"> &nbsp;
	<a href= "./hrms.htm?actionFlag=getQuarterDtls&edit=N&elementId=9000225" style="font-size: 2">  Add new Entry </a><br>
	</c:if>
       
       <c:if test="${FromBasicDtlsNew eq 'YES'}"> &nbsp;
	<a href= "./hrms.htm?actionFlag=getQuarterDtls&edit=N&FromBasicDtlsNew=YES&otherId=${otherId}&elementId=9000225" style="font-size: 2">  Add new Entry </a><br>
	</c:if>
	<display:table name="${actionList}" requestURI="" pagesize="15" id="row" export="false" style="width:100%">

 
		     
	<%--   <display:column class="tablecelltext" title="qtr.quaterType" headerClass="datatableheader" >
	  ${row.hrQuaterTypeMst.quaType}
	   </display:column>  --%>
	   
	   <display:column class="tablecelltext" title="Quarter Id" headerClass="datatableheader"  style="text-align:center;font-size:12px;"> 
	  ${row.hrEssQtrMst.quarterId}  
	  </display:column>	
	  
	   <c:set value="${row.hrEssQtrMst.quarterId}" var="otherIdForLink"/>
	   <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" style="text-align:center;font-size:12px;"> 
      
      <!-- added by ravysh -->
      <c:if test="${FromBasicDtlsNew ne 'YES'}">
       <a href= "./hrms.htm?actionFlag=getQuarterDtls&quarterId=${row.hrEssQtrMst.quarterId}&edit=Y&elementId=9000225" id="otherLink${row.hrEssQtrMst.quarterId}">${row.orgEmpMst.empPrefix} 
				 <c:if test="${row.orgEmpMst.empLname != null}"> ${row.orgEmpMst.empLname} </c:if>
				 <c:if test="${row.orgEmpMst.empFname != null}"> ${row.orgEmpMst.empFname} </c:if>				
				 <c:if test="${row.orgEmpMst.empMname != null}"> ${row.orgEmpMst.empMname} </c:if></a> <!--12 jan 2012 -->
       </c:if>
       
       <c:if test="${FromBasicDtlsNew eq 'YES'}">
       <a href= "./hrms.htm?actionFlag=getQuarterDtls&quarterId=${row.hrEssQtrMst.quarterId}&edit=Y&FromBasicDtlsNew=YES&otherId=${otherId }&elementId=9000225" id="otherLink${row.hrEssQtrMst.quarterId}">${row.orgEmpMst.empPrefix} 
				 <c:if test="${row.orgEmpMst.empLname != null}"> ${row.orgEmpMst.empLname} </c:if>
				 <c:if test="${row.orgEmpMst.empFname != null}"> ${row.orgEmpMst.empFname} </c:if>				
				 <c:if test="${row.orgEmpMst.empMname != null}"> ${row.orgEmpMst.empMname} </c:if></a> <!--12 jan 2012 --> 
       </c:if>
       
       	  </display:column>
	  
	  
	 
	   <display:column class="tablecelltext" title="Quarter Type" headerClass="datatableheader" style="text-align:center;font-size:12px;"> 
	  ${row.hrEssQtrMst.hrQuaterTypeMst.quaType}  
	  </display:column>	
	  
	   <display:column class="tablecelltext" title="Type of Accomodation" headerClass="datatableheader" style="text-align:center;font-size:12px;"> 
	  ${row.hrEssQtrMst.quarterGovtType.lookupName}  
	  </display:column>	
	  
	  
	   <display:column class="tablecelltext" title="Quarter Alloted To" headerClass="datatableheader" style="text-align:center;font-size:12px;"> 
	  ${row.hrEssQtrMst.quarterAllocatedToType.lookupName}  
	  </display:column>	
	 
 	   
	  <fmt:formatDate var="stDate" pattern="dd/MM/yyyy" value="${row.hrEssQtrMst.possessionDate}"/>
	  
   <fmt:formatDate var="endDate" pattern="dd/MM/yyyy" value="${row.hrEssQtrMst.allocationEndDate}"/>
	<%--   <display:column class="qtr.allocationDate" title="Possession Date" value="${stDate}" headerClass="datatableheader" /> 
  	  <display:column  class="qtr.vacatDate" title="Vacat Date" value="${endDate}" headerClass="datatableheader" /> 
     --%>
  	  <display:setProperty name="export.pdf" value="false" />
  	  </display:table>
		 <br>
		 <c:if test="${listSize eq 0 && empName != null && not empty empName}">
  		    <a href= "./hrms.htm?actionFlag=getQuarterDtls&edit=N&empName=${empName}&elementId=9000225" id="QuarterDtlsLink">  Add new Entry </a>
  	     </c:if>
		 <c:choose>
		
  		  <%-- <c:when test="${listSize eq 1}">--%>
  		     <c:when test="${listSize eq 1  && empName != null && not empty empName }">
               <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
			<c:when test="${listSize eq 1  && FromBasicDtlsNew != null && not empty FromBasicDtlsNew }">
               <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
			 <c:otherwise>	
				  <c:choose>
				  <c:when test="${listSize eq 0 && empName != null && not empty empName}">
	`					<script>
						  var answer = confirm ("The Employee Is Not Being Added in Quarter Details. Do You Want to Add?  ")
						  if (answer)
							 document.getElementById("QuarterDtlsLink").click();
						  else
							 submitForm();
						</script>
		             </c:when>
				  
		              <c:when test="${(hrEssQtrMst.quarterId) ne 0}">
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
	
		<c:if test="${FromBasicDtlsNew ne 'YES'}"> 
	<a href= "./hrms.htm?actionFlag=getQuarterDtls&edit=N&elementId=9000225" id="insertOneLink">  Add new Entry </a>	
	</c:if>
       
       <c:if test="${FromBasicDtlsNew eq 'YES'}">
	 <a href= "./hrms.htm?actionFlag=getQuarterDtls&edit=N&FromBasicDtlsNew=YES&otherId=${otherId }&elementId=9000225" id="insertOneLink">  Add new Entry </a>
	 
	 </c:if>
	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
</div>

</body>
<c:if test="${resultValue.status =='1'}">

<script>
alert("Record Successfully Added");

</script>
</c:if>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
  	  