 
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="noOfEmp" value="${resultMap.noOfEmp}"></c:set>
<c:set var="empList" value="${resultMap.empList}"></c:set>
<c:set var="hrtrTrainerMstVO" value="${resultMap.hrtrTrainerMstVO}"></c:set>
<c:set var="empVoWithAttendance" value="${resultMap.empVoWithAttendance}"></c:set>
<c:set var="numberOfEmp" value="${resultMap.numberOfEmp}"></c:set>
<c:set var="hrTrScheduleDtl" value="${resultMap.hrTrScheduleDtl}"></c:set>

<fmt:setBundle basename="resources.trng.SelectedEmpEdit" var="EmployeeDetails" scope="request" />
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="script/hrms/training/SelectedEmpAccount.js"/>"></script>

 <hdiits:form name="findTrainer" validate="true" method="post" action="./hdiits.htm?actionFlag=updateEmpAccount">

<div id="tabmenu">
   <ul id="maintab" class="shadetabs"><li  class="selected"><a href="#"  rel="tcontent1">
   <hdiits:caption captionid="TR.SELEMPEDIT" bundle="${EmployeeDetails}"/>   </a></li>				
   </ul>
</div>
<div class="tabcontentstyle"> 
	<div id="tcontent1" class="tabcontent" tabno="fmkghcf" title='Title'>
		<table>
			<hdiits:hidden name="numberOfEmp" default="${numberOfEmp}" />
			<hdiits:hidden name="SchId" default="${hrTrScheduleDtl.trngScheduleId}"/>
			<tr >
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.TRAININGNAME" bundle="${EmployeeDetails}" /></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${hrtrTrainerMstVO.trainingName}"></c:out></td>
				<td class="fieldLabel" width="25%"></td>
				<td class="fieldLabel" width="25%"></td>
				
			</tr>
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.TRAININGTYPE" bundle="${EmployeeDetails}"/></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${hrtrTrainerMstVO.cmnLookupMstTrainingTypeLookupId.lookupDesc}"></c:out></td>
				<td class="fieldLabel" width="25%"></td>
				<td class="fieldLabel" width="25%"></td>
			</tr>
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.BATCHSIZE" bundle="${EmployeeDetails}"/></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${hrTrScheduleDtl.batchSize}"></c:out></td>
				<td class="fieldLabel" width="25%"></td>
				<td class="fieldLabel" width="25%"></td>
			</tr>
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.BATCHNO" bundle="${EmployeeDetails}"/></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${hrTrScheduleDtl.batchNo}"></c:out></td>
				<td class="fieldLabel" width="25%"></td>
				<td class="fieldLabel" width="25%"></td>
			</tr>
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.STARTDATE" bundle="${EmployeeDetails}"/></b></td>
				<fmt:formatDate value="${hrTrScheduleDtl.startDt}"  pattern="yyyy-MM-dd" dateStyle="medium" var="startDt"/>
				<td class="fieldLabel" width="25%"><c:out value="${startDt}"></c:out></td>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.ENDDATE" bundle="${EmployeeDetails}"/></b></td>
				<fmt:formatDate value="${hrTrScheduleDtl.endDt}"  pattern="yyyy-MM-dd" dateStyle="medium" var="endDt"/>
				<td class="fieldLabel" width="25%"><c:out value="${endDt}"></c:out></td>
			</tr>
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.PARTILEVEL" bundle="${EmployeeDetails}"/></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${hrTrScheduleDtl.participationLevel}"></c:out></td>
				<td class="fieldLabel" width="25%"></td>
				<td class="fieldLabel" width="25%"></td>
			</tr>
			<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.REMARK" bundle="${EmployeeDetails}"/></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${hrTrScheduleDtl.trngScheduleRemarks}"></c:out></td>
				<td class="fieldLabel" width="25%"></td>
				<td class="fieldLabel" width="25%"></td>
			</tr>
			
		</table>
	
	<div id="trngEmpInfo" style="display:none">
	<TABLE  class="datatable"  border="1" width="70%" align="center">
	<tr>			
			<td class="datatableheader" width="5%" align="center"><b><hdiits:caption
				captionid="TR.SRNO" bundle="${EmployeeDetails}" /></b></td>
				
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption
				captionid="TR.EMPNAME" bundle="${EmployeeDetails}" /></b></td>
				
			<td class="datatableheader" width="5%" align="center"><b><hdiits:caption
				captionid="TR.CITYDIST" bundle="${EmployeeDetails}" /></b></td>
	
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption
				captionid="TR.JOININGDATE" bundle="${EmployeeDetails}" /></b></td>
				
			<td class="datatableheader" width="15%" align="center"><b><hdiits:caption
				captionid="TR.ALLOCATIONNO" bundle="${EmployeeDetails}" /></b></td>
				
			<td class="datatableheader" width="20%" align="center"><b><hdiits:caption
				captionid="TR.MESSADVANCE" bundle="${EmployeeDetails}" /></b></td>
				
				
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.MESSDAY" bundle="${EmployeeDetails}" /></b></td>
	
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.PRESENTDAYS" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.TOTALMESS" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.ROOMCHARGES" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.BARBERCHARGE" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.TEACHARGE" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.CLEANINGCHARGE" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.MISSCHARGE" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="20%" align="center"><b><hdiits:caption
				captionid="TR.REMARK" bundle="${EmployeeDetails}" /></b></td>
			<td class="datatableheader" width="25%" align="center"><b><hdiits:caption
				captionid="TR.TOTALCHARGES" bundle="${EmployeeDetails}" /></b></td>
				
		
				
	<c:forEach var="empDetail" items="${empVoWithAttendance}" varStatus="status">	
		<%int seperator=0; %>	
		<c:forEach var="empData" items="${empDetail}" >
			<% if (seperator== 0){  %>
				<c:set var="HrTrSelectedempDtlVo" value="${empData}" ></c:set>
				
			<%} if (seperator==1){%>
				<c:set var="attendanceList" value="${empData}" ></c:set>
			<%} if (seperator==2){%>
				<c:set var="empName" value="${empData}" ></c:set>
			<%} if (seperator==3){%>
				<c:set var="initialCharges" value="${empData}"></c:set>
			<%} if (seperator==4){%>
				<c:set var="totalMessCharges" value="${empData}"></c:set>
			<%} if (seperator==5){%>
				<c:set var="empLoc" value="${empData}"></c:set>
			<%} seperator=seperator+1;%>
			
		</c:forEach>	
	
	    
	<tr>
		<hdiits:hidden name="selectedEmpId_${status.count}" default="${HrTrSelectedempDtlVo.orgEmpMst.empId}"/>
		<td class="fieldLabel" width="25%" align="center">
			<c:out value="${status.count}"></c:out></td>
		<td class="fieldLabel" width="25%" align="center">
			 <hdiits:a href="#" onclick="callFun('${HrTrSelectedempDtlVo.orgEmpMst.empId}')" caption="${empName}"></hdiits:a> </td>
		<td class="fieldLabel" width="25%" align="center">
			<c:out value="${empLoc}"></c:out></td>
		<td class="fieldLabel" width="25%" align="center">
			<fmt:formatDate value="${HrTrSelectedempDtlVo.trainingJoinDt}"  pattern="yyyy-MM-dd" dateStyle="medium" var="joiningDate"/>
			<c:out value="${joiningDate}"></c:out></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="roomNo_${status.count}" id="roomNo${status.count}" default="${HrTrSelectedempDtlVo.roomNo}" /></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="advancePaid_${status.count}" id="advancePaid${status.count}" default="${HrTrSelectedempDtlVo.advanceReceived}" /></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="oneDayMess_${status.count}" id="oneDayMess${status.count}" default="${HrTrSelectedempDtlVo.advanceRequiredPerday}" onblur=" calculateTotalMessFee('${status.count}'),totalChargesOfEmp('${status.count}')"/></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="totalDaysPresent_${status.count}" id="totalDaysPresent${status.count}" readonly="true" default="${attendanceList}"/></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="totalMessFee_${status.count}" id="totalMessFee${status.count}" default="${totalMessCharges}" readonly="true" /></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="roomCharges_${status.count}" id="roomCharges${status.count}" onblur="totalChargesOfEmp('${status.count}')" default="${HrTrSelectedempDtlVo.roomChargesReceived}"/></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="barberCharges_${status.count}" id="barberCharges${status.count}" onblur="totalChargesOfEmp('${status.count}')" default="${HrTrSelectedempDtlVo.barberCharges}"/></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="teaCharges_${status.count}" id="teaCharges${status.count}" onblur="totalChargesOfEmp('${status.count}')" default="${HrTrSelectedempDtlVo.teaCharges}"/></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="cleaningCharges_${status.count}" id="cleaningCharges${status.count}" onblur="totalChargesOfEmp('${status.count}')" default="${HrTrSelectedempDtlVo.cleaningCharges}"/></td>
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="miscCharges_${status.count}" id="miscCharges${status.count}" onblur="totalChargesOfEmp('${status.count}')" default="${HrTrSelectedempDtlVo.miscellaneousCharges}"/></td>
			
			<td class="fieldLabel" width="25%" align="center">
			<hdiits:text size="3" name="remark_${status.count}" id="remark${status.count}" default="${HrTrSelectedempDtlVo.trngRemarks}" /></td>
			
		<td class="fieldLabel" width="25%" align="center">
			<hdiits:number size="3" maxlength="10" name="total_${status.count}" id="total${status.count}" default="${initialCharges}" /></td>
			
	</tr>
	
	</c:forEach>
	</TABLE>
	
	<CENTER>
	
	<hdiits:submitbutton name="submit" value="submit" caption="hello" />
	
	</CENTER>
</div>
<br>


<div id="RedirectButton" style="display:none">
<fmt:message bundle="${EmployeeDetails}" key="TR.RECORD" var="recordFound"></fmt:message>
	<table>
	<tr>
		<td width="25%"></td>
		<td width="25%"></td>
		<td width="25%">		
			<center><c:out value="${recordFound}"></c:out> </center>
		</td>
		<td width="25%"></td>
	</tr>
	<tr>
	<br>
	</tr>
	<tr>
		<td width="25%"></td>
		<td width="25%"></td>
		<td width="25%">
		 <center><hdiits:button name="RedirectQuery" type="button" onclick="redirectForm()" value="SubmitButtonQuery" captionid="TR.BACK" bundle="${EmployeeDetails}" /></center>   
		</td>
		
		<td width="25%"></td>
	</tr>
	
	</table>

</div>



</div>
</div>
<script type="text/javascript">
		initializetabcontent("maintab")
		setBackUrl('${pageContext.request.contextPath}'+"/hdiits.htm?viewName=selectTrngScheduleToEditEmp");
</script>

<script>

if(${numberOfEmp}== 0)
{
	document.getElementById("RedirectButton").style.display="";
}
if(${numberOfEmp}> 0)
{
	document.getElementById("trngEmpInfo").style.display="";
}
</script>



<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
 