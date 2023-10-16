	<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables"   var="enLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="empAllRec" value="${resultValue.empAllRec}" > </c:set>
<c:set var="empId" value="${resultValue.empId}" > </c:set>
<c:set var="ViewFlag" value="${resultValue.ViewFlag}" ></c:set>
<c:set var="updatePaybillFlg" value="${resultValue.updatePaybillFlg}" ></c:set>
<c:set var="paybillMonth" value="${resultValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resultValue.paybillYear}" ></c:set>

<!-- added by ravysh -->
<c:set var="FromBasicDtlsNew" value="${resultValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resultValue.otherId}" ></c:set>

<%int i=1;%>
<%

List actionList = (List) pageContext.getAttribute("actionList");

int size = actionList.size();

pageContext.setAttribute("listSize",size);

%>
<c:set var="empName" value="${resultValue.empName}" ></c:set>
<script type="text/javascript" >
	function submitForm()
	{
		var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
		
		var url="./hrms.htm?actionFlag=getRecoveryChallanLoanValue";
		
		document.LoanEmp.action=url;
		document.LoanEmp.submit();
	}
	
	function chkValue()
	{


		if(document.getElementById("Employee_ID_EmpLoanSearch").value==document.getElementById("Employee_Name_EmpLoanSearch").value)
	    {

	       empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	    }
	   else
	   {

			
		empId=document.getElementById("Employee_Name_EmpLoanSearch").value;
	   }

	   document.getElementById("Employee_ID_EmpLoanSearch").value=empId;

	   
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
			document.LoanEmp.submit();
		}
		return true;
	}
	
	function openWindow()
	{
		/* 	this technique will only work for I.E 4 and above, also, its not cross-browser compatible.
			If in future cross-browser compatiblity is required we can 'try' to achieve this by :
			(1) using window.open() instead of window.showMoadlDialgo() and
			(2)	putting:	<BODY onBlur="window.focus()">	: in child window. 
		*/
		window.showModalDialog("./hrms.htm?viewName=UploadLoanExcel","uploadexl","dialogWidth:900px; dialogHeight:300px; center:yes; status:yes;menubar=yes");
		//window.open('./hrms.htm?viewName=UploadLoanExcel','uploadexl','width=900,height=400,resizable=no,status=no,menubar=no');
	}
</script>


<body>
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="RC.Recovery" bundle="${enLables}"/></b></a></li>
	
</ul>
</div>
<br/>
<div class="tabcontentstyle">
 <div id="tcontent1" class="tabcontent">
<tr>
	     <c:if test="${updatePaybillFlg eq 'y'}" >
	     <a href="./hrms.htm?actionFlag=fillPaybillData&paybillYear=${paybillYear}&paybillMonth=${paybillMonth}&updatePaybillEmpId=${empId}&searchData=y">Back to update pay bill</a>
	     </c:if>
</tr>
	   <c:out value="${resultValue.msg}" /> <br><%--
	  	   <c:choose>
		<c:when test="${empAllRec=='true'}">
 	        <a href= "./hrms.htm?actionFlag=fillLoanCombo&empId=${empId}&empAllRec=Y" id="insertOneLink">  Add new Entry </a>
        </c:when>
        
        <c:when test="${FromBasicDtlsNew eq 'YES'}">
        <a href= "./hrms.htm?actionFlag=fillLoanCombo&FromBasicDtlsNew=YES&otherId=${otherId }"  id="inserLink">  Add new Entry </a> <br>
        </c:when>
     	<c:otherwise>
     	<a href= "./hrms.htm?actionFlag=fillLoanCombo&empName=${empName}"  id="inserLink">  Add new Entry </a> <br>
     
     	
     	</c:otherwise>
     	</c:choose>
 	    --%><br>

	<hdiits:form name="LoanEmp" validate="true" method="POST"	action="./hrms.htm?actionFlag=getRecoveryChallanLoanValue" encType="text/form-data">
		<c:set value="display:none" var="displayStyle"/>
	<c:if test="${empAllRec!='true'}">
		<c:set value="display:show" var="displayStyle"/>
	</c:if>

	<table  width="85%" align="center" style="${displayStyle}">
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

	</hdiits:form>
	
	
<display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">
  <c:set value="${row.empLoanId}" var="otherIdForLink"/>
	<display:column class="tablecelltext"  style="text-align: center;" title="Employee Name" headerClass="datatableheader" >
	  <c:choose>
		<c:when test="${empAllRec=='true'}">
		 <a href="./hrms.htm?actionFlag=getRecoveryChallanLoanValue&empLoanId=${row.empLoanId}&edit=Y&empId=${empId}&empAllRec=Y" id="otherLink${row.empLoanId}" > ${row.hrEisEmpMst.orgEmpMst.empPrefix} 
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empLname != null}"> ${row.hrEisEmpMst.orgEmpMst.empLname} </c:if>
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empFname != null}"> ${row.hrEisEmpMst.orgEmpMst.empFname} </c:if>				
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empMname != null}"> ${row.hrEisEmpMst.orgEmpMst.empMname} </c:if></a>
		</c:when>
		
		<c:when test="${FromBasicDtlsNew eq 'YES'}">
		<a href="./hrms.htm?actionFlag=getRecoveryChallanLoanValue&empLoanId=${row.empLoanId}&edit=Y&FromBasicDtlsNew=YES&otherId=${otherId }"  id="otherLink${row.empLoanId}" > ${row.hrEisEmpMst.orgEmpMst.empPrefix} 
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empLname != null}"> ${row.hrEisEmpMst.orgEmpMst.empLname} </c:if>
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empFname != null}"> ${row.hrEisEmpMst.orgEmpMst.empFname} </c:if>				
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empMname != null}"> ${row.hrEisEmpMst.orgEmpMst.empMname} </c:if> </a>
	  </c:when>
		
	  <c:otherwise>
		<a href="./hrms.htm?actionFlag=getRecoveryChallanLoanValue&empLoanId=${row.empLoanId}&edit=Y"  id="otherLink${row.empLoanId}" > ${row.hrEisEmpMst.orgEmpMst.empPrefix} 
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empLname != null}"> ${row.hrEisEmpMst.orgEmpMst.empLname} </c:if>
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empFname != null}"> ${row.hrEisEmpMst.orgEmpMst.empFname} </c:if>				
				 <c:if test="${row.hrEisEmpMst.orgEmpMst.empMname != null}"> ${row.hrEisEmpMst.orgEmpMst.empMname} </c:if></a>
	  </c:otherwise>
	 </c:choose>
   </display:column>
   <display:column class="tablecelltext" style="text-align: center;"  title="Loan Name" headerClass="datatableheader"  value="${row.hrLoanAdvMst.loanAdvName}"></display:column>
   <display:column class="tablecelltext" style="text-align: center;"  title='<span>Loan Principal Amount<Font size="3" face="Rupee Foradian"> (<Font size="3" face="Rupee Foradian">`</Font>)</Font> </span>' headerClass="datatableheader"  value="${row.loanPrinAmt}"  > </display:column>	
   <display:column class="tablecelltext" style="text-align: center;" title='<span>Loan Interest Amount<Font size="3" face="Rupee Foradian"> (<Font size="3" face="Rupee Foradian">`</Font>)</Font> </span>' headerClass="datatableheader"  value="${row.loanInterestAmt}"  > </display:column>
   <display:column class="tablecelltext" style="text-align: center;" title='<span>Loan EMI Amount<Font size="3" face="Rupee Foradian"> (<Font size="3" face="Rupee Foradian">`</Font>)</Font> </span>' headerClass="datatableheader"  value="${row.loanPrinEmiAmt+row.loanIntEmiAmt}"  > </display:column>
<display:setProperty name="export.pdf" value="true"/>
</display:table>


	  <c:choose>
		<c:when test="${empId ne 0 and empAllRec eq 'true'  and listSize eq 0}">
			<script>
				document.getElementById("insertOneLink").click();
			</script>
		</c:when>
		<c:when test="${empId ne 0 and empAllRec eq 'true'  and listSize ne 0}">
		<script>
		
			</script>
			</c:when>
		<c:otherwise>
			<script>
		
		</script>
		</c:otherwise>
		</c:choose>
		<c:choose>
  		  <%-- <c:when test="${listSize eq 1}">--%>
  		 
  		     <c:when test="${listSize eq 1 and ViewFlag ne 'true' && empName != null && not empty empName}">
               <script>
                    document.getElementById("otherLink${otherIdForLink}").click();
               </script>
			 </c:when>
			 <c:otherwise>	
				  <c:choose>
		              <c:when test="${listSize eq 0 and empAllRec eq 'true' && empName != null && not empty empName}">
		                 <script>
								document.getElementById("insertOneLink").click();
			             </script>
			         </c:when>
		              <c:when test="${listSize eq 0 and empAllRec ne 'true' && empName != null && not empty empName}">
			             <script>
							var answer = confirm (" Employee is Not Being Added in Loan Details. Do You Want to Add?  ")
							if (answer)
									document.getElementById("inserLink").click();
							else
							submitForm();
						</script>
		             </c:when>
		          </c:choose>
			</c:otherwise>
		 </c:choose>

      <BR>
  	  <%--
  	<c:choose>
		<c:when test="${empId ne 0}">
 	        <a href= "./hrms.htm?actionFlag=fillLoanCombo&empId=${empId}&empAllRec=Y" id="insertOneLink">  Add new Entry </a>
        </c:when>
        <c:when test="${FromBasicDtlsNew eq 'YES'}">
        <a href= "./hrms.htm?actionFlag=fillLoanCombo&FromBasicDtlsNew=YES&otherId=${otherId }"  id="inserLink">  Add new Entry </a> <br>
        </c:when>
     	<c:otherwise>
		<a href= "./hrms.htm?actionFlag=fillLoanCombo&empName=${empName}"  id="inserLink">  Add new Entry </a> <br>     	</c:otherwise>
     	</c:choose>
	--%>
  
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
  	  