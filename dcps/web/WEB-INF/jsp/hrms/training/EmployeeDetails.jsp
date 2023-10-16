<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>

<fmt:setBundle basename="resources.trng.EmployeeDetails" var="EmployeeDetails" scope="request" />
<hdiits:form name="personDetail" validate="true" method="post"  >
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="orgEmpMst" value="${resultMap.orgEmpMst}"></c:set>
<c:set var="orgDesignationMst" value="${resultMap.orgDesignationMst}"></c:set>
<c:set var="HrTrTrngAttendedDtl" value="${resultMap.HrTrTrngAttendedDtl}"></c:set>
<c:set var="qualLst" value="${resultMap.qualLst}"></c:set>
<c:set var="empDetail" value="${resultMap.empDetail}"></c:set>
<c:set var="empAddress" value="${resultMap.empAddress}"></c:set>
<c:set var="birthPlaceAddressMst" value="${resultMap.birthPlaceAddressMst}"></c:set>
<c:set var="trngPlace" value="${resultMap.trngPlace}"></c:set>

<div id="tabmenu">
   <ul id="maintab" class="shadetabs"><li  class="selected">
   		<a href="#"  rel="tcontent1">
   			<hdiits:caption	captionid="TR.EmployeeDtl" bundle="${EmployeeDetails}" /></a></li>				
   </ul>
</div>

<script>
function onClickClose()
{
	window.close();
}
</script>
<div class="tabcontentstyle">  
	<div id="tcontent1" class="tabcontent" tabno="fmkghcf">
	 <!-- table for group and point -->
	<table class="tabtable">
	 	<tr>
	 		<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.Name"  bundle="${EmployeeDetails}"/></b></td>
	 		<td class="fieldLabel" width="25%"> <c:out value=" ${orgEmpMst.empPrefix} ${orgEmpMst.empFname} ${orgEmpMst.empMname} ${orgEmpMst.empLname} "> </c:out></td>
	 		<td width="25%"></td>
	 		<td width="25%"></td>
	 	</tr>
	 	<tr>
	 		<fmt:formatDate value="${orgEmpMst.empDoj}" pattern="dd-MM-yyyy" dateStyle="medium" var="dateOfJoining"/>
	 		<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.DateofJoining"  bundle="${EmployeeDetails}"/></b></td>
	 		<td class="fieldLabel" width="25%"> <c:out value="${dateOfJoining}"></c:out></td>
	 		<td class="fieldLabel" width="25%"></td>
	 		<td class="fieldLabel" width="25%"></td>
	 	</tr>
	 	<tr>
	 		<td class="fieldLabel" width="25%"><b> <hdiits:caption captionid="TR.Designation"  bundle="${EmployeeDetails}"/></b></td>
	 		<td class="fieldLabel" width="25%"> <c:out value="${orgDesignationMst.dsgnName}"></c:out></td>
	 		<td class="fieldLabel" width="25%"></td>
	 		<td class="fieldLabel" width="25%"></td>
	 	</tr>
	 	<tr>
	 		<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.MaritalStatus"  bundle="${EmployeeDetails}" /></b></td>
	 		<td class="fieldLabel" width="25%"> <c:out value="${empDetail.cmnLookupMstByEmpMaritalStatusId.lookupDesc}"></c:out></td>
	 		<td width="25%"></td>
	 		<td width="25%"></td>
	 	</tr>
	 	<tr>
	 		<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.ServiceExperience"  bundle="${EmployeeDetails}" /></b></td>
	 		<fmt:formatDate value="${orgEmpMst.empSrvcExp}" pattern="dd-MM-yyyy" dateStyle="medium" var="serviceExp"/>
	 		<td class="fieldLabel" width="25%"> <c:out value="${serviceExp}"></c:out></td>
	 		<td width="25%"></td>
	 		<td width="25%"></td>
	 	</tr>
	 	<tr>
	 		<td class="fieldLabel" width="25%"><b><hdiits:caption  captionid="TR.DOB"  bundle="${EmployeeDetails}"/></b></td>
	 		<fmt:formatDate value="${orgEmpMst.empDob}" pattern="dd-MM-yyyy" dateStyle="medium" var="birthDate"/>
	 		<td class="fieldLabel" width="25%"> <c:out value="${birthDate}"></c:out></td>
	 		<td width="25%"></td>
	 		<td width="25%"></td>
	 		
	 	<tr>
	 		<td class="fieldLabel" width="25%"> <b><hdiits:caption captionid="TR.POB"  bundle="${EmployeeDetails}"/></b></td>
	 		<td class="fieldLabel" width="25%"> <c:out value="${birthPlaceAddressMst}"></c:out></td>
	 		<td width="25%"></td>
	 		<td width="25%"></td>
	 	</tr>
	 	<tr>
	 		<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.ContactAddress"  bundle="${EmployeeDetails}"/></b></td>
	 		<td class="fieldLabel" width="25%"> <c:out value="${empAddress}"></c:out></td>
	 	</tr>
	 </table>
	 
	 <c:choose>
	 <c:when test="${qualLst != '[]'}">
	 <table>
	 	<tr>
	 		<td width="10%"> <b><hdiits:caption  captionid="TR.Qualification"  bundle="${EmployeeDetails}"/></b></td>
	 	</tr>
	 </table>
	 
	 <table class="datatable"  border="1" width="70%" align="center">
	 	<tr>
	 		<td class="datatableheader" width="5%" align="center"><b><hdiits:caption
				captionid="TR.SRNO"  bundle="${EmployeeDetails}"/></b></td>
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption
				captionid="TR.COURSEATTENDED" bundle="${EmployeeDetails}"   /></b></td>
			<td class="datatableheader" width="5%" align="center"><b><hdiits:caption
				captionid="TR.YEAR" bundle="${EmployeeDetails}"/></b></td>
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption
				captionid="TR.PLACE" bundle="${EmployeeDetails}" /></b></td>
		</tr>
		<c:forEach var="qual" items="${qualLst}" varStatus="status">
			<tr>
				<td class="fieldLabel" width="25%" align="center">
				<c:out value="${status.count}"></c:out></td>
				<td class="fieldLabel" width="25%" align="center">
				<c:out value="${qual.cmnLookupMstBySubQualificationId.lookupDesc}"></c:out></td>
				<td class="fieldLabel" width="25%" align="center">
				<c:out value="${qual.cmnLookupMstByYearOfPassing.lookupDesc}"></c:out>    </td>
				<td class="fieldLabel" width="25%" align="center">
				<c:out value="${qual.cmnLookupMstByYearOfPassing.lookupDesc}"></c:out>    </td>
			</tr>
		</c:forEach>
	 </table>
	 </c:when>
	</c:choose>
	 
	 <br>
	 <br>
	 <br>
	 <c:choose>
	 <c:when test="${HrTrTrngAttendedDtl != '[]'}">
	 <table>
	 	<tr>
	 		<td width="10%"><u> <b><hdiits:caption  captionid="TR.TRNGATTEN"  bundle="${EmployeeDetails}"/></b></u></td>
	 	</tr>
	 </table>
	 <TABLE class="datatable"  border="1" width="70%" align="center">
	<tr>			
			<td class="datatableheader" width="5%" align="center"><b><hdiits:caption
				captionid="TR.SRNO"  bundle="${EmployeeDetails}"/></b></td>
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption
				captionid="TR.COURSEATTENDED" bundle="${EmployeeDetails}"   /></b></td>
			<td class="datatableheader" width="5%" align="center"><b><hdiits:caption
				captionid="TR.YEAR" bundle="${EmployeeDetails}"/></b></td>
	 
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption
				captionid="TR.PLACE" bundle="${EmployeeDetails}" /></b></td>
	 
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption
				captionid="TR.DURATION" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="20%" align="center"><b><hdiits:caption
				captionid="TR.TRAININGCOMPL" bundle="${EmployeeDetails}"  /></b></td>
	</tr>
	<c:set var="serialNumber" value="0" />
	<c:forEach var="TrainingLst" items="${HrTrTrngAttendedDtl}" varStatus="status">
	
	<tr>
		<td class="fieldLabel" width="25%" align="center">
			<c:out value="${status.count}"></c:out></td> 
			
		<td class="fieldLabel" width="25%" align="center">
			<c:out value="${TrainingLst.hrTrScheduleDtl.hrTrTrainingMst.trainingName}"> </c:out></td>
		<c:if test="${not empty TrainingLst.hrTrScheduleDtl}">
			<c:set var="trngDate" value="${TrainingLst.hrTrScheduleDtl.startDt}"></c:set>
		</c:if>
		<td class="fieldLabel" width="25%" align="center">
			<fmt:formatDate value="${trngDate}" pattern="dd-MM-yyyy" dateStyle="medium" var="trainingDate"/>
			<c:out value="${trainingDate}"></c:out></td>
			
	 	<td class="fieldLabel" width="25%" align="center">
			<c:out value="${trngPlace[serialNumber]}"></c:out></td>
				
		<td class="fieldLabel" width="25%" align="center">
			<c:out value="${TrainingLst.hrTrScheduleDtl.trngDuration}"></c:out></td>
			
		<td class="fieldLabel" width="25%" align="center">
			<c:out value="${TrainingLst.isTrainingComplete}"></c:out>    </td>
			<c:set var="serialNumber" value="${serialNumber + 1}" />	
	</tr>
	</c:forEach>
	</TABLE>
	</c:when>
	<c:otherwise>
	<fmt:message bundle="${EmployeeDetails}" key="TR.NOTFOUND" var="notFound"></fmt:message>
		<center><b><c:out value="${notFound}"></c:out> </b>  </center>
	</c:otherwise>
	</c:choose>
	<br>
	<CENTER><hdiits:submitbutton name="submit" value="  OK  " onclick="onClickClose()" caption="hello" /></CENTER>
	
</div>
</div>
<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>