<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="DistrictOfficeLables" scope="request" />
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" >
function viewReport()
{
	var district_id = document.getElementById("distViewLsts").value;
	//alert(district_id);
	//var district_Id=document.getElementById("demoViewLst").value;
	//alert(district_Id);
	showProgressbar();
	var url="./hrms.htm?actionFlag=schoolDataValidation&district_id="+district_id;
	
	//alert(url);
	 window.location="./hrms.htm?actionFlag=schoolDataValidation&district_id="+district_id;
	 showProgressbar();
	//self.location(url);
	//document.DemoView.action = url;
	//document.DemoView.submit();
	 
}	
function generateReports(flag)
{
	var flags = flag.toString();
	alert(flags);
	var url = "./hrms.htm?actionFlag=generateDDOExcelforInstituteReport&flag="+flags;
	alert(url);
	document.EMployeeView.action=url;
	document.EMployeeView.submit();
	
	
}
</script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="district_id" value="${resultValue.district_id}" > </c:set>
<c:set var="saveasdraft" value="${resultValue.saveasdraft}" > </c:set>
<c:set var="forwadedfromdraft" value="${resultValue.forwadedfromdraft}" > </c:set>	  
<c:set var="formapproved" value="${resultValue.formapproved}" > </c:set>	  
<c:set var="totalnumberofemployees" value="${resultValue.totalnumberofemployees}" > </c:set>
<c:set var="balancenumberofemployees" value="${resultValue.balancenumberofemployees}" > </c:set>		  
<c:set var="demoViewLst" value="${resultValue.demoViewLst}"></c:set>
<c:set var="noOfschoolsConfigured" value="${resultValue.noOfschoolsConfigured}"></c:set>
<c:set var="noOfSchoolsApproved" value="${resultValue.noOfSchoolsApproved}"></c:set>
<c:set var="noOfSchoolsPending" value="${resultValue.noOfSchoolsPending}"></c:set>
<c:set var="noOfschoolsRejected" value="${resultValue.noOfschoolsRejected}"></c:set>	
<c:set var="schoolvalidationpendingbyDDO" value="${resultValue.schoolvalidationpendingbyDDO}"></c:set>
<c:set var="schoolvalidationapprovedbyDDO" value="${resultValue.schoolvalidationapprovedbyDDO}"></c:set>  
<!--  <c:set var="actionList" value="${resultValue.actionList}" > </c:set>-->
<c:set var="employeeStatisticsViewVOlst" value="${resultValue.employeeStatisticsViewVOlst}" > </c:set>
</head>
<body>
<form method="POST" name="EMployeeView">
	<!--  <div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b></b></a></li>
		</ul>
	</div>-->
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	
	<fieldset class="tabstyle"><legend>Filter Reports BY District </legend>
	<table >
	<tr >
<td colspan="4"><b>Select District :</b>
<select  name="distViewLsts" id="distViewLsts"  >
<option value="-1">Select</option>
<option value="1">All</option>
<c:forEach items="${demoViewLst}" var="demoViewLst">
 <c:choose>
							<c:when test="${demoViewLst[0]=='01' || demoViewLstv[0]=='1' }">
								<option value='<c:out value="${demoViewLst[0]}"/>' disabled="disabled">
								<c:out value="${demoViewLst[1]}"/>
									</option>
							</c:when>
							<c:otherwise>
						<c:choose>
						<c:when test="${demoViewLst[0]==district_id}">
								<option value='<c:out value="${demoViewLst[0]}"/>' selected="selected">
								<c:out value="${demoViewLst[1]}"/>
									</option>
							</c:when>
							<c:otherwise>
							 <option value='<c:out value="${demoViewLst[0]}"/>'>
	         					   <c:out value="${demoViewLst[1]}"/>
	       						</option>
	       					</c:otherwise>
						</c:choose>
	     						
							</c:otherwise>
						</c:choose>						 
					
</c:forEach>
</select>
</td>
</tr>
<TR>
<td colspan="4">&nbsp;
</td>
</TR>

<tr >

			<td colspan="4" align="center">
				
				<hdiits:button name="report" type="button" captionid="EIS.VIEWREPORTS" bundle="${commonLables}" onclick="viewReport()"></hdiits:button>
			
				
			</td>
		</tr>
</table>
		  
	  </fieldset>
		 <br/><br/>
		<!--  /*  <fieldset class="tabstyle"><legend>Employee Configuration Report </legend>
		  
		 <table id="vo" style="align:center;Border:1px" cellpadding="4" class="datatable"  >
				<thead>
					<tr>
						<th class="datatableheader" >Total No. of Employees</th>
						<th class="datatableheader" >Saved as Drafts</th>
						<th class="datatableheader" >Forwaded to DDO</th>
						<th class="datatableheader">Approved by DDO</th>
						
						</tr>
				</thead> 
				
				<tbody>
				<tr style="border:1px">
				<td style="border:1px solid rgb(255, 218, 178);">
				<c:out value="${totalnumberofemployees}">
				</c:out>
				</td>
				
				<td style="border:1px solid rgb(255, 218, 178);">
				<c:out value="${saveasdraft}">
				</c:out>
				</td>
				<td style="border:1px solid rgb(255, 218, 178);">
				<c:out value="${forwadedfromdraft}">
				</c:out>
				</td>
				<td style="border:1px solid rgb(255, 218, 178);">
				<c:out value="${formapproved}">
				</c:out>
				</td>
				</tr>
				</tbody>
				</table>
				
			</fieldset>	*/  -->
			<br/><br/>
			<fieldset class="tabstyle"><legend>School Validation Report </legend> 
				 <table id="vo" style="align:center;Border:1px" cellpadding="4" class="datatable"  >
				<thead>
					<tr>
					
						<th class="datatableheader" >Number of Institutions configured & approved by Level 2 DDO</th>
						
						<th class="datatableheader" >Number of Institutions for which data entered by Level 1 DDO</th>
						<th class="datatableheader">Number of Institutions whose data is approved by Level 2 DDO</th>
						<!--  <th class="datatableheader">Total No. Of Schools Rejected by lvl2 DDO</th> -->
						
						</tr>
				</thead> 
				
				<tbody>
				<tr style="border:1px">
				<td style="border:1px solid rgb(255, 218, 178);">
				<c:out value="${noOfschoolsConfigured}">
				</c:out>
				</td>
				
				
				<td style="border:1px solid rgb(255, 218, 178);">
				<c:out value="${schoolvalidationpendingbyDDO}">
				</c:out>
				</td>
				<td style="border:1px solid rgb(255, 218, 178);">
				<c:out value="${schoolvalidationapprovedbyDDO}">
				</c:out>
				</td>
				<!--   <td style="border:1px solid rgb(255, 218, 178);">
				<c:out value="${noOfschoolsRejected}"> 
				</c:out>
				</td> -->
				
				</tr>
				</tbody>
				</table>
		  <%-- 
		  
		  <c:set var="srno" value="1" > </c:set>
		  <display:table name="${resultValue}"  requestURI="" pagesize="${pageSize}" sort="list" id="row" export="false" style="width:100%" >
			  <display:column class="tablecelltext" title=""  headerClass="datatableheader" style="text-align: center;font-size:12px;" ><c:out value="saveasdraft"></c:out></display:column> 
			  <display:column class="tablecelltext" title="" headerClass="datatableheader" style="text-align: center;font-size:12px;"><c:out value="forwadedfromdraft"></c:out></display:column>	
			  <display:column class="tablecelltext" title="" headerClass="datatableheader" style="text-align: center;font-size:12px;"><c:out value="formapproved"></c:out></display:column>
			  <display:column class="tablecelltext" title="" headerClass="datatableheader" style="text-align: center;font-size:12px;"><c:out value="totalnumberofemployees"></c:out></display:column>
			  <display:column class="tablecelltext" title="Balance Employees for Approval" headerClass="datatableheader" style="text-align: center;font-size:12px;"><c:out value="saveasdraft"></c:out></display:column>
			
		  	  <display:setProperty name="export.pdf" value="false" />
		  	   <c:set var="srno" value="${srno+1}" > </c:set>
  		  </display:table>
  		  
  		  --%>
		 <br>&nbsp;
	  	 <hdiits:button name="ExcelReports" type="button" captionid="EIS.EXPORTOEXCEL" bundle="${commonLables}" onclick="generateReports('${flag}')"></hdiits:button>
	  	 <br/><br/>
	
	<script type="text/javascript">
	
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
</body>
</html>