<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="resultSet" value="${resultValue.resultSet}" > </c:set>
<c:set var="msg" value="${resultValue.msg}" ></c:set>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<script>
function deactiveOrder(salRevId)
{
var salrevId = salRevId;


//alert('Are Your Sure Want To Deactivate Order');

	     var answer = confirm("Do you want to Deactive Order???")
 			if(answer) {
 	
 			 
  		     document.viewSalRevMst.action = "hrms.htm?actionFlag=deactivateOrder&salrevId="+salrevId;
  		     document.viewSalRevMst.submit();
						}
			else
						{
							return;				
						}
			
}
	
	



function deactivatingOrder(salRevId,flag)
{
var salrevId = salRevId;

var count = flag;

if(document.viewSalRevMst.chkbox[count].checked)
{
document.getElementById("linkForDeactive"+salrevId).style.display = "";
}
else
{
document.getElementById("linkForDeactive"+salrevId).style.display = "none";
}
}
function updateSalrevMst(salRevId)
{
	document.viewSalRevMst.action = "hrms.htm?actionFlag=editSalRevData&lSalRevId="+salRevId;
	document.viewSalRevMst.submit();
}
function mapEmployee(salRevId)
{
	document.viewSalRevMst.action = "hrms.htm?actionFlag=fillMapEmpWithArrearScreen&lOrderId="+salRevId+"&flag=99";
	document.viewSalRevMst.submit();
}
</script>
</head>
<body>
<c:set value="1" var="i"></c:set>
<form method="POST" name="viewSalRevMst" action="./hrms.htm?viewName=salRevMstView">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="SRM.salRevView" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">	  
	<div id="tcontent1" class="halftabcontent">
	 <div align="center"> <h1>
 </h1> </div> <br>
		 <a href="./hrms.htm?actionFlag=addSalRevData&edit=N">Add new Entry</a>  
		
		  <display:table name="${resultSet}" requestURI=""  pagesize="${pageSize}"   id="row" export="true" style="width:100%">
			  <%--<display:column  property="orderId" class="tablecelltext" title="Order Id"  headerClass="datatableheader"  />    --%>
			    <fmt:formatDate var="salRevOrderDate" pattern="dd/MM/yyyy" value="${row.revOrderDate}"/>
			  <display:column   class="tablecelltext" title="Salary Revision Order No. " headerClass="datatableheader"  >
			 <a id="linkForeditSalRev${row.salRevId}"
					href="javaScript:updateSalrevMst('${row.salRevId }');">
					<b>${row.revOrderNo} &nbsp;&nbsp;Dt.${salRevOrderDate}   </b></a>
			  </display:column>	

<fmt:formatDate var="revEffcFrmDate" pattern="dd/MM/yyyy" value="${row.revEffcFrmDate}"/>
<fmt:formatDate var="revEffcToDate" pattern="dd/MM/yyyy" value="${row.revEffcToDate}"/>
			    <display:column class="tablecelltext" title="Rev.Eff.FromDate" value="${revEffcFrmDate}" headerClass="datatableheader" />    
			     <display:column   class="tablecelltext" title="Rev.Eff.ToDate" style="text-align:center"  value="${revEffcToDate}" headerClass="datatableheader" />
			    <fmt:formatDate var="revPayOutDate" pattern="dd/MM/yyyy" value="${row.revPayOutDate}"/>
			    <display:column class="tablecelltext" title="PayOut Date" value="${revPayOutDate}" headerClass="datatableheader" />    
		  	  <display:column  class="tablecelltext"  title="Revision Type" headerClass="datatableheader" style="text-align:left" >

  	
			<b>${row.rltBillTypeEdp.edpShortName}</b>
			 
			 </display:column>
			 <display:column   class="tablecelltext" title="Frequency Of Months" style="text-align:center"  value="${row.revFreqMthPaid}" headerClass="datatableheader" />
			 			 <display:column   class="tablecelltext" title="Installments" style="text-align:center" value="${row.revInstallments}" headerClass="datatableheader" />

			<display:column class="tablecelltext" title="Map Employees"
				style="text-align:center" headerClass="datatableheader">
		
				<a id="linkForMapEmp${row.salRevId}"
					href="javaScript:mapEmployee('${row.salRevId }');">Map
				Employees</a>
			</display:column>

	<display:column   class="tablecelltext" title="Revision Order Status"  style="text-align:center"  headerClass="datatableheader" >
			 
			<input  align="center" style="display:none" name="chkbox" type="checkbox" onclick="deactivatingOrder(${row.salRevId},${i});">
			<c:if test="${row.revStatus ne 0 }">
				<fmt:message key="SRM.Active" bundle="${commonLables}"/>				
			</c:if>
			<c:if test="${row.revStatus eq 0 }">
				<fmt:message key="SRM.Deactive" bundle="${commonLables}"/>				
			</c:if>			
		</display:column>
		
	 
		  	  <display:setProperty name="export.pdf" value="true" />
		  	  <c:set var="i" value="${i+1}"></c:set>
  		  </display:table>
  		
		 <br>
	  	 <a href="./hrms.htm?actionFlag=addSalRevData&edit=N">Add new Entry</a>  
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getSalRevData";
			document.forms[0].action=url;
			document.forms[0].submit();
		}
	</script>
</div>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</html>