<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>

<fmt:setBundle basename="resources.trng.SelectedEmpAdd" var="SelectedEmpAdd" />
<fmt:setBundle basename="resources.trng.TrngAttendedLables" var="trngAttendedLables" scope="request" /> 
<fmt:setBundle basename="resources.trng.trschLables" var="trschLables"	scope="request" />
<fmt:setBundle basename="resources.trng.EmployeeDetails" var="EmployeeDetails"	scope="request" />

<hdiits:form name="DispAllEmpCorrSch" validate="true" method="post" action="./hdiits.htm?actionFlag=DispAllEmpCorrSch_trngExtAttended" >

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="nominatedEmpLst" value="${resultMap.nominationListCheck}"></c:set>
<c:set var="trainingId" value="${resultMap.trainingId}"></c:set>
<c:set var="schId" value="${resultMap.schId}"></c:set>
<c:set var="noOfEmpAttnded" value="${resultMap.noOfEmpAttnded}"></c:set>
<c:set var="trainingMst" value="${resultMap.trainingMst}"></c:set>
<c:set var="scheduleDtl" value="${resultMap.scheduleDtl}"></c:set>
<c:set var="empDetails" value="${resultMap.empDetails}"></c:set>

<div id="tabmenu">
   <ul id="maintab" class="shadetabs">
   	<li  class="selected">
	   	<a href="#"  rel="tcontent1"><hdiits:caption captionid="TR.caption" bundle="${SelectedEmpAdd}" /></a></li>				
   </ul>
</div>


<div class="tabcontentstyle">  
	<div id="tcontent1" class="tabcontent" tabno="fmkghcf">
 	 
	 <table>
	 	<tr>
		 	<td class="fieldLabel" width="25%"><b><hdiits:caption	captionid="TR.trainingName"  bundle="${SelectedEmpAdd}"/></b></td>
		 	<td class="fieldLabel" width="25%"><c:out value="${trainingMst.trainingName}"></c:out></td>
		 	<td class="fieldLabel" width="25%"><b><hdiits:caption	captionid="TR.TRAININGTYPE"  bundle="${trschLables}"/></b></td>	 	
		 	<td class="fieldLabel" width="25%"><c:out value="${trainingMst.cmnLookupMstTrainingTypeLookupId.lookupDesc}"></c:out></td>
	 	</tr>
	 	<tr>
	 		<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.startdt"   bundle="${SelectedEmpAdd}"/></b></td>
	 		<fmt:formatDate value="${scheduleDtl.startDt}" pattern="dd-MM-yyyy" dateStyle="medium" var="startDate"/>
	 		<td class="fieldLabel" width="25%"><c:out value="${startDate}"></c:out></td>
	 		<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.enddt"  bundle="${SelectedEmpAdd}"/></b></td>
	 		<fmt:formatDate value="${scheduleDtl.endDt}" pattern="dd-MM-yyyy" dateStyle="medium" var="endDate"/>
	 		<td class="fieldLabel" width="25%"><c:out value="${endDate}"></c:out></td>
	 	</tr>
	 	<tr>
	 		<td class="fieldLabel" ><b><hdiits:caption	captionid="TR.organiser"   bundle="${SelectedEmpAdd}"/></b></td>
	 		<td class="fieldLabel" ><c:out value="${scheduleDtl.organizer}"></c:out></td>	  
	 		<td class="fieldLabel" ><b><hdiits:caption captionid="TR.batchNo"   bundle="${SelectedEmpAdd}"/></b></td>
	 		<td class="fieldLabel" ><c:out value="${scheduleDtl.batchNo}"></c:out></td> 
	 	</tr>
	 </table>
	 
	 <br>
	 <br>
	 
	<c:if test="${noOfEmpAttnded ne 0}">
		  
		<TABLE class="datatable"  id="caseIPC" border="1" width="70%"  align="center">
		<tr>			
			<td class="datatableheader" width="5%" align="center"><b><hdiits:caption captionid="TR.SRNO"  bundle="${SelectedEmpAdd}"  /></b></td>
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption captionid="TR.EMPNAME"  bundle="${SelectedEmpAdd}"   /></b></td>
			<td class="datatableheader" width="5%" align="center"><b><hdiits:caption  captionid="TR.LOCATION"    bundle="${SelectedEmpAdd}" /></b></td>
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption captionid="TR.POST"     bundle="${SelectedEmpAdd}"/></b></td>				
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption captionid="TR.ispassed" bundle="${trngAttendedLables}"/></b></td>           
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption captionid="TR.REMARKS" bundle="${trschLables}"/></b></td>
		</tr>
		
		
		
		<c:forEach var="empinfo" items="${empDetails}" varStatus="status">
		<%int seperator=1; %>
			
		<c:forEach var="employee" items="${empinfo}" >
		<%if(seperator==1){ %>
			<c:set var="empName" value="${employee}"></c:set>
		<%}if(seperator==2){ %>
			<c:set var="empDesg" value="${employee}"></c:set>
		<%}if(seperator==3){ %>
			<c:set var="orgEmpMst" value="${employee}"></c:set>
		<%}if(seperator==4){ %>
			<c:set var="empAddr" value="${employee}"></c:set>
		<%} %>
		<%seperator++; %>
		</c:forEach>
		
		<tr>
			<td class="fieldLabel" align="center">
				<c:out value="${status.count}"></c:out>
				<hdiits:checkbox name="empSelected_${status.count}" value="Y" default="N" id="empSelected${status.count}"/>   
				<hdiits:hidden name="empId_${status.count}" default="${orgEmpMst.empId}"/>
			</td>
			<td class="fieldLabel" align="center">
				<c:out value="${empName}"> </c:out>
			</td>
			<td class="fieldLabel" align="center">
				<c:out value="${empAddr}"></c:out>
			</td>
			<td class="fieldLabel" align="center">
				<c:out value="${empDesg}"></c:out>
			</td>		 	
			<td class="fieldLabel" id="isTrngPass_${status.count}" align="center">
				<hdiits:radio name="isTrngPass_${status.count}" id="cflag" value='P' default="P" bundle="${trngAttendedLables}" captionid="TR.pass" mandatory="true"/>
				<hdiits:radio name="isTrngPass_${status.count}" value='F' bundle="${trngAttendedLables}" captionid="TR.fail" default="P"/>
			</td>
		 	<td class="fieldLabel" align="center">
			  	<hdiits:textarea name="txtRem_${status.count}" id="txtRem_${status.count}"  captionid="TR.REMARKS" rows="3" cols="20"  bundle="${trschLables}"    />
			</td>	
		</tr>
		
		</c:forEach>
		</TABLE>
	</c:if>
	
	<c:if test="${noOfEmpAttnded eq 0}">
	<fmt:message bundle="${SelectedEmpAdd}" key="TR.EMPLOYEELEFT" var="EmployeeLeft"></fmt:message>
	 
	<table>
		<tr>
			<td>
				<center><b> <c:out value="${EmployeeLeft}"></c:out></b></center>
			</td>
		</tr>
	</table>
	</c:if>
	
	<table>
	<tr> 
		<hdiits:hidden name="hdnschId" default="${scheduleDtl.trngScheduleId}"/>
		<hdiits:hidden name="hdntrngId" default="${trainingMst.trainingMstId}"/>
		<hdiits:hidden name="empArrivalLst" default="${noOfEmpAttnded}"/>						
	</tr>
	</table>
		
	<br><br>	
	 
	
</div>
</div>
<script type="text/javascript">
var navDisplay = false;
</script> 
	<jsp:include page="../../core/tabnavigation.jsp"/> 
	


<script type="text/javascript">
		initializetabcontent("maintab")
   		setBackUrl('${pageContext.request.contextPath}'+"/hdiits.htm?viewName=externalTrngAttendedModified");
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
 