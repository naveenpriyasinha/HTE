<%
try {
%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"	scope="request" />
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="hrDeputreqmtDtl" value="${resultValue.hrDeputreqmtDtl}"></c:set>
<c:set var="cmnLocationMst" value="${resultValue.cmnLocationMst}"></c:set>
<c:set var="orgGradeMst" value="${resultValue.orgGradeMst}"></c:set>
<c:set var="orgPostDetailsRlt" value="${resultValue.orgPostDetailsRlt}"></c:set>
<c:set var="orgPostMst" value="${resultValue.orgPostMst}"></c:set>
<c:set var="setOfEligiblity" value="${resultValue.setOfEligiblity}"></c:set>
<c:set var="hrDeputreqmtdistDtl" value="${resultValue.hrDeputreqmtdistDtl}"></c:set>
<c:set var="HrDeputEmpList" value="${resultValue.HrDeputEmpList}" />
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>
<style type="text/css">

/* All form elements are within the definition list for this example */
.tablecell{
   border-bottom: solid 1px #DEDEDE;
	border:1;
	border-color:black;
	font-size: 11px;
	font-style: normal;
	font-weight: normal;
	background: white;
	text-align: left;
	padding: 1px;
	
}
.tablerow{
  border-top: solid 1px #333333;
	border-left: solid 1px #666666;
	border-right: solid 0px #888888;
	border-bottom: solid 1px #666666; 
	font-size: 11px;
	font-style: normal;
	font-weight: bold;
	text-align: center;
	background: #C9DFFF;
	color: black;
	padding: 1px;
	
	
}
</style>
<script type="text/javascript">
var emparray = new Array();
var empcount = 0 ;
function checkclick(form){
	if(form.checked == true)
	{
	
		emparray[empcount]=form.id;
		empcount++;
		
		
	}
	else{
	for(var i=0; i<emparray.length; i++)
  	{  
		
  
 		 if(emparray[i]== form.id){
  			emparray.splice(i,1);
		    empcount--;
  
  }
	}
}
document.getElementById('emparray').value=emparray;
}
function validCheckBOx()
{
	if(emparray.length==0)
	{
	//	alert("You must select atleast one CheckBox");
	
		return false;
		
	}
		
	return true;
}
</script>
<hdiits:form name="bcNoEdit" validate="true" method="POST" action="./hrms.htm?actionFlag=approveEmp">
	<hdiits:hidden name="emparray" id="emparray" />	
	<hdiits:hidden name="BranchHeadPostID" id="BranchHeadPostID" />	
	<hdiits:hidden name="distributionId" default="${hrDeputreqmtdistDtl.distributionId}" />
	<hdiits:hidden name="fileId" default="${hrDeputreqmtDtl.deputationreqmtId}" />
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
	<hdiits:fieldGroup titleCaptionId="deputationRequest" bundle="${comLable}" id="deputationId"  collapseOnLoad="false">
	<table align="center" width="100%">
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u><fmt:message key="deputationRequest" bundle="${comLable}"></fmt:message>
			</u></strong> </font></td>
		</tr>-->
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption
						captionid="deputedplace" bundle="${comLable}" /></b></td>
					<td width="25%">${cmnLocationMst.locName}</td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table align="center" width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="address"
						bundle="${comLable}" /></b></td>
					<td width="25%">${cmnLocationMst.locAddr1}</td>
					<td width="25%"><b><hdiits:caption captionid="noofperson"
						bundle="${comLable}" /></b></td>
					<td width="25%">${hrDeputreqmtDtl.noOfPersons}</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table align="center" width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="reqforclass"
						bundle="${comLable}" /></b></td>
					<td width="25%">${orgGradeMst.gradeName}</td>
					<td width="25%"><b><hdiits:caption captionid="reqforpost"
						bundle="${comLable}" /></b></td>

					<td width="25%">${orgPostDetailsRlt.postName}</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="fromdate"
						bundle="${comLable}" /></b></td>
					<td width="25%"><fmt:formatDate
						value="${hrDeputreqmtDtl.deputfromDate}" pattern="dd/MM/yyyy" /></td>
					<td width="25%"><b><hdiits:caption captionid="todate"
						bundle="${comLable}" /></b></td>

					<td width="25%"><fmt:formatDate
						value="${hrDeputreqmtDtl.deputtoDate}" pattern="dd/MM/yyyy" /></td>
				</tr>
			</table>
			</td>
		</tr>


	
		<tr>	
			<td>
			<hdiits:fieldGroup titleCaptionId="eligibilitycriteria" bundle="${comLable}" id="eligibilitycriteriaId"  collapseOnLoad="false">
				<table align="center" width="80%" border="1" id="criteria" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
				<tr style="background-color:${tdBGColor}">
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="attribute" bundle="${comLable}" /></b></td>
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="condition" bundle="${comLable}" /></b></td>
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="description" bundle="${comLable}" /></b></td>
				</tr>
				<c:forEach var="eligiblity" items="${setOfEligiblity}">
					<tr>
						<td align="left" colspan="50">
						${eligiblity.cmnLookupMstByAttributeId.lookupDesc}</td>
						<td align="left" colspan="50">
						${eligiblity.cmnLookupMstByAttributeConditionId.lookupDesc}</td>
						<td align="left" colspan="50">${eligiblity.attributeValue}</td>
					</tr>
				</c:forEach>
			</table>
			</hdiits:fieldGroup>
			</td>
		</tr>
		
		<tr>
			<td>
			</td>
			</tr>
			
			
	</table>
	</hdiits:fieldGroup>
	<hdiits:fieldGroup titleCaptionId="broadcastdeputDetails" bundle="${comLable}"  collapseOnLoad="true">
			<table align="center" width="30%">
								<tr>

								

								</tr>
								
							</table>
				<c:set var="i" value="0" /> <% int a=0; %>
	
		
		<display:table list="${resultValue.hrDepuDistList}"  id="row" requestURI=""  export="false"  offset="1"  >
		
		
		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column  class="tablecell"  titleKey="SrNo" headerClass="tablerow" value="<%=a=a+1 %>" style="text-align: center" >
		</display:column>

		
		
		<display:column  class="tablecell" titleKey="locationName"
			 headerClass="tablerow" style="text-align: center">
			
			${row.locName}
		</display:column>
		
		<display:column  class="tablecell" titleKey="branchNm"
			headerClass="tablerow" style="text-align: center">
			
			${row.branchName}
		</display:column>
		<display:column  class="tablecell" titleKey="receiveBy"
			headerClass="tablerow" style="text-align: center">
			
			${row.empName}
		</display:column>

		<display:column  class="tablecell" titleKey="requestStatus"
			headerClass="tablerow" style="text-align: center">

		
			<c:if test="${row.statusFlage eq \"PENDING\"}">
				<font color="red">
				<fmt:message key="Dep.pending" bundle="${comLable}"></fmt:message>  </font>
			</c:if>
			<c:if test="${row.statusFlage eq \"ACTIVE\"}">
				<font color="red">
				<fmt:message key="Dep.active" bundle="${comLable}"></fmt:message>  </font>
			</c:if>
			<c:if test="${row.statusFlage eq \"COMPLETED\"}">
				<font color="red">
				<fmt:message key="Dep.completed" bundle="${comLable}"></fmt:message>  </font>
			</c:if>

			
		</display:column>
		
		<c:set var="i" value="${i+1}" />
		
			
		
	</display:table>
</hdiits:fieldGroup>
	
	<c:if test="${not empty resultValue.deputEmpDetailsObj}">
	<hdiits:fieldGroup titleCaptionId="deputedEmpList" bundle="${comLable}"  collapseOnLoad="true">
	<table width="100%" id="deputedEmpList">
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="deputedEmpList" bundle="${comLable}"></fmt:message>
				</u></strong> </font></td>
		</tr>-->

	</table>

				
		<c:set var="k" value="0" /> <% int c=0; %>
	<input type="hidden" id="userId" name="userid">
	
		<display:table list="${resultValue.deputEmpDetailsObj}"  id="row" requestURI=""  export="false" style="width:100%" offset="1" >
		

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column  class="tablecell"  	titleKey="SrNo" headerClass="tablerow" value="<%=c=c+1 %>" style="text-align: center" >
		</display:column>

		
		<display:column  class="tablecell" titleKey="employeeName"
			headerClass="tablerow" style="text-align: center">
			
			${row.empfName}
		</display:column>
		
		<display:column  class="tablecell" titleKey="post"
			headerClass="tablerow" style="text-align: center">
			
			${row.postName}
		</display:column>
	
			<display:column  class="tablecell" titleKey="currentLocation"
			headerClass="tablerow" style="text-align: center">
			
			${row.locName}
		</display:column>
	
			<display:column  class="tablecell" titleKey="dateOfBirth"
			headerClass="tablerow" style="text-align: center">
			<fmt:formatDate value="${row.dateB}" pattern="dd/MM/yyyy"/>

		</display:column>

			<display:column  class="tablecell" titleKey="dateOfJoin"
			headerClass="tablerow" style="text-align: center">
						<fmt:formatDate value="${row.datej}" pattern="dd/MM/yyyy"/>
			
		</display:column>
					<display:column  class="tablecell" titleKey="dateOfRetire"
			headerClass="tablerow" style="text-align: center">
						<fmt:formatDate value="${row.dateR}" pattern="dd/MM/yyyy"/>
			
		</display:column>
				<display:column  class="tablecell" titleKey="locationName"
			headerClass="tablerow" style="text-align: center">
			
			${row.locationName}
		</display:column>
		
		<c:set var="k" value="${k+1}" />
		
	</display:table>
	</hdiits:fieldGroup>
			</c:if>	
<c:if test="${not empty resultValue.HrDeputEmpList}">
	<hdiits:fieldGroup titleCaptionId="listOfEmpReceive" bundle="${comLable}"  collapseOnLoad="true">
	<table width="100%" id="listOfEmpReceive" >
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="listOfEmpReceive" bundle="${comLable}"></fmt:message></u></strong> </font></td>
		</tr>-->
	</table>

		<c:set var="j" value="0" /> <% int b=0; %>
	<input type="hidden" id="userId" name="userid">
	
		<display:table list="${resultValue.HrDeputEmpList}"  id="row" requestURI=""  export="false" style="width:100%" offset="1" >
		

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column  class="tablecell"  	titleKey="SrNo" 	headerClass="tablerow" value="<%=b=b+1 %>" style="text-align: center" >
		</display:column>

			<display:column  class="tablecell" titleKey="Dep.select"
			headerClass="tablerow" style="text-align: center" >
			<hdiits:checkbox id="${row.userId}" name="check" value="${row.userId}"  
			onclick="checkclick(this);" />
			
		</display:column>
		
	
		<display:column  class="tablecell" titleKey="employeeName"
			headerClass="tablerow" style="text-align: center">
			
			${row.empfName}
		</display:column>
		
		<display:column  class="tablecell" titleKey="post"
			headerClass="tablerow" style="text-align: center">
			
			${row.postName}
		</display:column>
	
			<display:column  class="tablecell" titleKey="currentLocation"
			headerClass="tablerow" style="text-align: center">
			
			${row.locName}
		</display:column>
	
			<display:column  class="tablecell"  titleKey="dateOfBirth"
			headerClass="tablerow" style="text-align: center">
			<fmt:formatDate value="${row.dateB}" pattern="dd/MM/yyyy"/>

		</display:column>

			<display:column  class="tablecell" titleKey="dateOfJoin"
			headerClass="tablerow" style="text-align: center">
						<fmt:formatDate value="${row.datej}" pattern="dd/MM/yyyy"/>
			
		</display:column>
					<display:column  class="tablecell" titleKey="dateOfRetire"
			headerClass="tablerow" style="text-align: center">
						<fmt:formatDate value="${row.dateR}" pattern="dd/MM/yyyy"/>
			
		</display:column>
				<display:column  class="tablecell" titleKey="locationName"
			headerClass="tablerow" style="text-align: center">
			
			${row.locationName}
		</display:column>
		
		<c:set var="j" value="${j+1}" />
		
	</display:table>
	<c:if test="${not empty resultValue.HrDeputEmpList}">
<jsp:include page="../../../core/tabnavigation.jsp" />
</c:if>
	</hdiits:fieldGroup>
	</c:if>


<c:if test="${empty resultValue.hrDepuDistList}">
<jsp:include page="../../../core/tabnavigation.jsp" />
</c:if>

<hdiits:validate controlNames="text"
	locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
