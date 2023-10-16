<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.trng.trschLables" var="trschLables" scope="request" />
<fmt:setBundle basename="resources.trng.attendanceLables" var="attendLables" scope="request" />

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/hrms/training/attendance.js"></script>
 
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="selectedEmpLst" value="${resValue.selectedEmpLst}" > </c:set>
<c:set var="editFlag" value="${resValue.editFlag}" />
<c:set var="editEmpList" value="${resValue.editEmpList}" />	
<c:set var="attndDate" value="${resValue.attndDate}" />
<c:set var="isExam" value="${resValue.isExam}" />
<c:set var="defaults" value="${resValue.defaults}" />
<c:set var="today" value="${resValue.today}" />
<c:set var="scheduleId" value="${resValue.schId}" />
<c:set var="trngId" value="${resValue.trainingId}" />
<c:set var="hrTrScheduleDtl" value="${resValue.hrTrScheduleDtl}" />
<c:set var="IsTrngComplete" value="${resValue.IsTrngComplete}" />
<c:set var="Rem" value="${resValue.Rem}" />
<c:set var="remTrngFailed" value="${resValue.remTrngFailed}" />
<c:set var="trngFailed" value="${resValue.trngFailed}" />
<c:set var="locList" value="${resValue.locList}" />
<c:set var="desgList" value="${resValue.desgList}" />
<c:set var="cmnDistrictMstList" value="${resValue.cmnDistrictMstList}" />

<fmt:message key="TR.ATTDT_VAL" bundle="${attendLables}" var="attDtAlert"></fmt:message>

<hdiits:form name="attendance" action="hdiits.htm" validate="true" method="post"  encType="multipart/form-data">

<hdiits:hidden name="actionFlag" default="insertAttendance" />
<hdiits:hidden name="editFlag" default="${editFlag}" />
<hdiits:hidden name="schId" default="${schId}" />
<hdiits:hidden name="scheduleId1" default="${scheduleId}" />
<hdiits:hidden name="trngId1" default="${trngId}" />
<hdiits:hidden name="hrTrScheduleDtldt" default="${hrTrScheduleDtl.endDt}" />
<hdiits:hidden name="trngCode" default="${hrTrScheduleDtl.hrTrTrainingMst.trngCode}" />

<fmt:formatDate value="${hrTrScheduleDtl.startDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="attendanceStartDt" />
<fmt:formatDate value="${hrTrScheduleDtl.endDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="attendanceEndDt" />

<hdiits:hidden name="srNo" />

<script type="text/javascript">

function checkDtRange()
{
	var fromDate='${attendanceStartDt}';
	var toDate='${attendanceEndDt}';
	var clsDate = document.getElementById('attndDate').value;
	var retval=true;
 
 	if(clsDate != "")
	{
		if(fromDate != "")
		{
			 
			if (Date.parse(fromDate) > Date.parse(clsDate)) {
				alert("<fmt:message key="TR.CLSDT_VAL" bundle="${attendLables}" />");
				document.getElementById('attndDate').value="";
				return false;
			}
			else
			{
				retval=true;
			}

		}
			
		if(toDate != null)
		{
 			if (Date.parse(clsDate) > Date.parse(toDate))  
			{
				alert("<fmt:message key="TR.CLSDT_VAL" bundle="${attendLables}" />");
				document.getElementById('attndDate').value="";
				return false;
			}
			else
			{
				retval=true;
			}
		}
	}
	return retval;
}

function checkDuplicate()
{
	var attendanceDate=document.getElementById('attndDate').value;
	var trngCode=document.getElementById('trngCode').value;
	var schId=document.getElementById('scheduleId1').value;
	if(attendanceDate != "")
	{	
		checkAttDate(attendanceDate, trngCode, schId, '${pageContext.request.contextPath}', '${attDtAlert}');
	}	
	else
	{
		return false;
	}
}

</script>
	
<div id="tabmenu">
   <ul id="maintab" class="shadetabs"><li  class="selected"><a href="#"  rel="tcontent1"><hdiits:caption captionid="TR.ATTENDANCE" bundle="${trschLables}"/>
   </a></li>				
   </ul>
</div>

<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="fmkghcf">


<table class="tabtable">

   <TR>
    		<TD class=fieldLabel width="25%"><hdiits:caption captionid="TR.TRAININGNAME" bundle="${trschLables}"/></TD>
			
    		<TD width="25%"><c:out value="${hrTrScheduleDtl.hrTrTrainingMst.trainingName}"></c:out>	 
 			</TD>
 			<td><hdiits:caption captionid="TR.TRAININGTYPE" bundle="${trschLables}"/></td>
			<td><c:out value="${hrTrScheduleDtl.hrTrTrainingMst.cmnLookupMstTrainingTypeLookupId.lookupDesc}"></c:out>	 </td>    		
   </TR>
    <TR>

    		<TD class=fieldLabel width="25%"><hdiits:caption captionid="TR.STARTDATE" bundle="${trschLables}"/></TD>
			
    		<TD width="25%"><c:out value="${attendanceStartDt}"></c:out>	
    		
 			</TD>
 			<td><hdiits:caption captionid="TR.ENDDATE" bundle="${trschLables}"/></td>
			<td><c:out value="${attendanceEndDt}"></c:out>	 </td>    		
   </TR>
    <TR>
   		    <c:if test="${editFlag eq 'Y' or editFlag eq 'y' }">
    		<TD class=fieldLabel width="25%"><hdiits:caption captionid="TR.Date" bundle="${trschLables}"/></TD>
			
    		<TD width="25%"><hdiits:dateTime name="attndDate"  captionid="TR.Date" disabled="true" bundle="${trschLables}" maxvalue="01/01/9999" validation="txt.isrequired" mandatory="true" default="${attndDate}" /> 
    		
 			</TD>
 			</c:if> 
 			<c:if test="${editEmpList eq null }">
    		<TD class=fieldLabel width="25%"><hdiits:caption captionid="TR.Date" bundle="${trschLables}"/></TD>
			
    		<TD width="25%"><hdiits:dateTime name="attndDate"  captionid="TR.Date"  bundle="${trschLables}" validation="txt.isrequired" mandatory="true" default="${attndDate}" maxvalue="01/01/9999" onblur="checkDuplicate();checkEndDate();checkDtRange();"/> 
    		
 			</TD>
 			</c:if> 
 			<td></td>
			<td></td>    		
   </TR>
     
  <TR>
		 	
		  <td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.Exam" bundle="${trschLables}"/>
		</td>
			<td class="fieldLabel" width="25%"><hdiits:checkbox name="exam" value="Y" default="${isExam}"   captionid="TR.Exam"  bundle="${trschLables}"/> </td>			
						
						
<c:if test="${editEmpList eq null }">						
<tr >

	
 <td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.IsTrainingExecuted" bundle="${trschLables}"/>
		</td>
			<td class="fieldLabel" width="25%">
									<hdiits:radio name="isExecFlag" id="flag" value='Y' caption="Yes" default="Y" onclick="externClick(this)"/>
									<hdiits:radio name="isExecFlag" value='N' caption="No" onclick="externClick(this)"/>
							</td>
			
    		</tr>
    		</c:if>	
		</table>
		<c:if test="${editEmpList eq null }">		
    		<div id="displaytxtarea" Style="display:none">
				<table class="tabtable">				
					<tr>
							<td class="fieldLabel" width="25%">
			    				<hdiits:caption captionid="TR.reason" bundle="${trschLables}"  />
			    			</td>
							<td class="fieldLabel" width="25%"><hdiits:textarea name="txtreason" 
				                             captionid="TR.reason" rows="4" cols="30"  bundle="${trschLables}"   condition="checkExternFlag()" validation="txt.isrequired" mandatory="true" />
				            </td>
				            
				            <td></td>
			
					</tr>
				</table>
		 		</div>
		 		</c:if>
		 		
		 		
   
    <br>
     <br>
     
      <br>
       <br>
        <br>
         <br>
          <br>
   
   
   <table id="attndScheet"  border="1" align="center" width="80%" class="datatable"> 
 			
            <tr>
    		<td class="datatableheader"><b><hdiits:caption captionid="TR.Srno" bundle="${trschLables}"/></b></td>
    		<td class="datatableheader"><b><hdiits:caption captionid="TR.EmployeeName" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.Designation" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.Office" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.DistrictUnit" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.Presence" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.REMARKS" bundle="${trschLables}"/></b></td>
            <td class="datatableheader" id="displayStatus21" style="display:none" ><b><hdiits:caption captionid="TR.IsTrainingComplete" bundle="${trschLables}"/></b></td>
            <td class="datatableheader" id="displaytxtarea21" style="display:none" ><b><hdiits:caption captionid="TR.reason" bundle="${trschLables}"  /></b></td>
            
            
    	    </tr>
    	     <c:set var="serialNumber1" value="0" />
    	     
    	     <c:if test="${editFlag ne null and editEmpList ne null}" >

    	     	 <c:forEach var="selEditLst" items="${editEmpList}">
    	     <c:set var="serialNumber1" value="${serialNumber1 +1}" />	
    	    	<tr>
    	    	
    	    				<td class="fieldLabel" >
			    				<c:out value="${serialNumber1}" />
			    			</td>
							<td class="fieldLabel">
			    				<c:out value="${selEditLst.orgEmpMst.empPrefix} ${selEditLst.orgEmpMst.empFname} ${selEditLst.orgEmpMst.empLname}" />
			    			</td>
							<td class="fieldLabel" ><c:out value="${desgList[serialNumber1-1].dsgnName}"></c:out>	
				            </td>
				            <td class="fieldLabel"><c:out value="${locList[serialNumber1-1].locName}"></c:out>	
				            </td>
				             <td class="fieldLabel" ><c:out value="${cmnDistrictMstList[serialNumber1-1].districtName}"></c:out>	
				            </td>
							  <td class="fieldLabel" ><hdiits:radio name="isPresentFlag_${serialNumber1}" id="flag" value='P' default="${selEditLst.isEmployeePresent}" caption="P" validation="sel.isradio" />
									<hdiits:radio name="isPresentFlag_${serialNumber1}" value='A' caption="A" default="${selEditLst.isEmployeePresent}" validation="sel.isradio" />
									<hdiits:radio name="isPresentFlag_${serialNumber1}" value='L' caption="L" default="${selEditLst.isEmployeePresent}" validation="sel.isradio" />
									 <hdiits:hidden name="empId_${serialNumber1}" default="${selEditLst.orgEmpMst.empId}"  />
									 <hdiits:hidden name="attndId_${serialNumber1}" default="${selEditLst.attendanceId}"  />
				            </td>
				            
				          
							  <td class="fieldLabel" >
							  	<hdiits:textarea name="txtRem_${serialNumber1}" 
				                             captionid="TR.REMARKS" rows="3" cols="20" default="${selEditLst.attendanceRemarks}" bundle="${trschLables}" />
				            </td>
					</tr>
					
			</c:forEach>
    	     
    	     </c:if>
    	     
    	     <c:if test="${editEmpList eq null }"  >

    	     <c:forEach var="selLst" items="${selectedEmpLst}">
    	     <c:set var="serialNumber1" value="${serialNumber1 +1}" />	
    	    	<tr>
    	    	
    	    				<td class="fieldLabel" >
			    				<c:out value="${serialNumber1}" />
			    			</td>
							<td class="fieldLabel" >
			    				<c:out value="${selLst.orgEmpMst.empPrefix} ${selLst.orgEmpMst.empFname} ${selLst.orgEmpMst.empLname}" />
			    			</td>
							<td class="fieldLabel" ><c:out value="${desgList[serialNumber1-1].dsgnName}"></c:out>	
				            </td>
				            <td class="fieldLabel"><c:out value="${locList[serialNumber1-1].locName}"></c:out>	
				            </td>
				             <td class="fieldLabel" ><c:out value="${cmnDistrictMstList[serialNumber1-1].districtName}"></c:out>
				            </td>
							  <td class="fieldLabel" ><hdiits:radio name="isPresentFlag_${serialNumber1}" id="flag" default="P" value='P' caption="P" validation="sel.isradio" />
									<hdiits:radio name="isPresentFlag_${serialNumber1}" value='A' caption="A"  validation="sel.isradio" />
									<hdiits:radio name="isPresentFlag_${serialNumber1}" value='L' caption="L" validation="sel.isradio" />
									 <hdiits:hidden name="empId_${serialNumber1}" default="${selLst.orgEmpMst.empId}"  />
				            </td>
				            
				          
							  <td class="fieldLabel" >
							  	<hdiits:textarea name="txtRem_${serialNumber1}" 
				                             captionid="TR.REMARKS" rows="3" cols="20"  bundle="${trschLables}"    />
				            </td>
				             <td class="fieldLabel" id="displayStatus_${serialNumber1}" style="display:none" >
									<hdiits:radio name="isCompleteFlag_${serialNumber1}" id="cflag" value='Y' default="Y" caption="Yes" onclick="externClick1(this)"/>
									<hdiits:radio name="isCompleteFlag_${serialNumber1}" value='N' caption="No" default="Y" onclick="externClick1(this)"/>
							</td>
				             <td class="fieldLabel" id="displaytxtarea2_${serialNumber1}" style="display:none" ><hdiits:textarea name="txtreason1_${serialNumber1}" 
				                             captionid="TR.reason" rows="3" cols="20" id="txtRsn_${serialNumber1}"  bundle="${trschLables}" condition="checkExternFlag1()" validation="txt.isrequired" mandatory="true" />
				            </td>
				            
					</tr>
					
			</c:forEach>
			</c:if>
		  <hdiits:hidden name="noOfRecords" default="${serialNumber1}"  />
    	    
            </table>  
            <jsp:include page="../../core/tabnavigation.jsp" ></jsp:include>

</div>
</div>
<script type="text/javascript">
		initializetabcontent("maintab")
        var eflag='${editFlag}'; 
        if(eflag == 'Y' || eflag=='y')
        {
        	setBackUrl('${pageContext.request.contextPath}'+"/hrms.htm?viewName=training-selEditTrngSch");
        }
		else
		{
			setBackUrl('${pageContext.request.contextPath}'+"/hrms.htm?viewName=training-selTrngSch");
		}
		
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>