<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.trng.TrainingMstLables" var="trngLables" scope="request" />
<fmt:setBundle basename="resources.trng.NominationLables" var="nomLables" scope="request" />

<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="trngVO" value="${resValue.hrTrTrainingMst}" > </c:set>
<c:set var="nominationList" value="${resValue.nominationList}" > </c:set>
<c:set var="nominationDetails" value="${resValue.nominationDetails}" > </c:set>
<c:set var="fileStatus" value="${resValue.fileStatus}" > </c:set>
<c:set var="show3rdTab" value="${resValue.show3rdTab}" > </c:set>

<c:set var="counter" value="0" > </c:set>
<c:set var="size" value="${resValue.size}" > </c:set>
<c:set var="remarksReadonly" value="false"/>
<fmt:message bundle="${trngLables}" key="TR.SRCHEMP" var="title"></fmt:message>
<fmt:message bundle="${nomLables}" key="TR.NMRCV" var="msgred"></fmt:message>
<fmt:message bundle="${nomLables}" key="TR.NMRLT" var="msgdt"></fmt:message>
<fmt:message key="TR.ADDNOMMSG" bundle="${nomLables}" var="msgAddNom" />
<fmt:formatDate value="${resValue.nmrclastdate}" pattern="dd/MM/yyyy" var="nmrcdt"/>
<c:set  var="typefl" value="${resValue.typefl}"/>


<script language="javascript"><!--
function AddEmployee()
{
	var empIds = document.getElementById('Employee_ID_Array_EmpNominated').value;
	var empNames = document.getElementById('Sel_Employee_NAME_Array_EmpNominated').value;
	var empDesg = document.getElementById('Sel_Employee_DESIG_Array_EmpNominated').value;
	var empIdArr = empIds.split(",");
	var empNameArr = empNames.split(",");
	var empDsgArr = empDesg.split(",");
	
	
	var srcrt=document.getElementById("Employee_srchNameText_EmpNominated").value;

	if(empIds == 'EmpNominated' && srcrt != null ){
									
						var empId =document.getElementById('Employee_ID_EmpNominated').value;
						var empName=document.getElementById('Employee_Name_EmpNominated').value;
						var desi=document.getElementById('Dsgn_NM_EmpNominated').value;
						
							document.getElementById('Employee_ID_Array_EmpNominated').value='EmpNominated';
							document.getElementById('Sel_Employee_NAME_Array_EmpNominated').value='EmpNominated';
							document.getElementById('Sel_Employee_DESIG_Array_EmpNominated').value='EmpNominated';
							document.getElementById('Employee_Name_EmpNominated').value='';
							document.getElementById('Employee_ID_EmpNominated').value='';
							document.getElementById('Dsgn_NM_EmpNominated').value='';
							document.getElementById("Police_ST_NM_EmpNominated").value='';
							document.getElementById("DISTRICT_NM_EmpNominated").value='';
							document.getElementById("GPF_NM_EmpNominated").value='';
							document.getElementById("Employee_srchNameText_EmpNominated").value='';
						
		var addStatus = true;
				
		for(var checkExistance = 1; checkExistance <= counter; checkExistance++)
		{
			if(document.getElementById("empId_"+checkExistance) != null)
			{
				if (document.getElementById("empId_"+checkExistance).value == empId)
				{
						addStatus = false;
						document.getElementById("NomNo"+checkExistance).style.display='';
						document.getElementById("chkbx_"+checkExistance).value="W";
				}
			}
		}
		
		if(addStatus)
		{
			counter = counter + 1;
		
			if(document.getElementById("nomTable") != null)
			{
				var trow=document.getElementById("nomTable").insertRow();
			}
			else
			{
				var trow = document.getElementById("nomTable").insertRow();
			}
			trow.id = "NomNo"+counter;
		
			trow.insertCell(0).innerHTML = "<input type='hidden' name='Id_"+counter+"'  value='0' />";
			trow.cells[0].style.display = 'none';
		
					
					trow.insertCell(1).innerHTML = "<input type='hidden' name='empId_"+counter+"'  value='"+empId+"' />"+empName;
					trow.insertCell(2).innerHTML = "<input type='hidden' name='flag_"+counter+"'  value='A' />"+desi;
					trow.insertCell(3).innerHTML =	"<textarea name='remarks_"+counter+"'  class='textareatag' ></textarea>"
					trow.insertCell(4).innerHTML =	"<input type='checkbox' name='reserved_"+counter+"' value='Y'  class='checkboxtag' />"
					trow.insertCell(5).innerHTML = "<input type='hidden' name='rowId"+counter+"' default='"+counter+"'/> <input type='hidden' id='chkbx_"+counter+"' name='chkbx_"+counter+"' value='W'/><a href='javascript:deleteNomRow("+counter+")'>delete</a> "
			
			if(document.forms[0].counter != null)
			{
				document.forms[0].counter.value = counter;
			}
			else
			{
				document.forms[0].counter.value = counter;
			}
		
		}
	}
	else{
			for(var i=0;i<empIdArr.length;i++)
			{
				var empId = empIdArr[i];
				var empName = empNameArr[i];
				var desi = empDsgArr[i];		
				var addStatus = true;
						
				for(var checkExistance = 1; checkExistance <= counter; checkExistance++)
				{
					if(document.getElementById("empId_"+checkExistance) != null)
					{
						if (document.getElementById("empId_"+checkExistance).value == empId)
						{
								addStatus = false;
								document.getElementById("NomNo"+checkExistance).style.display='';
								document.getElementById("chkbx_"+checkExistance).value="W";
						}
					}
				}
				
				if(addStatus)
				{
					counter = counter + 1;
				
					if(document.getElementById("nomTable") != null)
					{
						var trow=document.getElementById("nomTable").insertRow();
					}
					else
					{
						var trow = document.getElementById("nomTable").insertRow();
					}
					trow.id = "NomNo"+counter;
					trow.insertCell(0).innerHTML = "<input type='hidden' name='Id_"+counter+"'  value='0' />";
					trow.cells[0].style.display = 'none';
				
						
						trow.insertCell(1).innerHTML = "<input type='hidden' name='empId_"+counter+"'  value='"+empId+"' />"+empName;
						trow.insertCell(2).innerHTML = "<input type='hidden' name='flag_"+counter+"'  value='A' />"+desi;
						trow.insertCell(3).innerHTML =	"<textarea name='remarks_"+counter+"'  class='textareatag' ></textarea>"
						trow.insertCell(4).innerHTML =	"<input type='checkbox' name='reserved_"+counter+"' value='Y'  class='checkboxtag' />"
						trow.insertCell(5).innerHTML = "<input type='hidden' name='rowId"+counter+"' default='"+counter+"'/> <input type='hidden' id='chkbx_"+counter+"' name='chkbx_"+counter+"' value='W'/><a href='javascript:deleteNomRow("+counter+")'>delete</a> "
					
				
					if(document.forms[0].counter != null)
					{
						document.forms[0].counter.value = counter;
					}
					else
					{
						document.forms[0].counter.value = counter;
					}
				
				}
			 }
	}
	return true;
}

function noRecords()
{
	var rowLength = document.getElementById('nomTable').rows.length;
	var isPresent=false;
	if(rowLength>1)
	{
		for(var k=1; k<rowLength; k++)
		{
			var recordId = "NomNo"+k;
			if(document.getElementById(recordId).style.display != "none")
			{
				isPresent=true;
				break;
			}
		}
	}

	if(isPresent)
	{
		return true;
	}
	else
	{
		alert('${msgAddNom}');
		return false;
	}
}

--></script>
<fmt:formatDate value="${resValue.currentdate}" pattern="dd/MM/yyyy" var="sysDate"/>
<hdiits:hidden name="sysDateHidd" default="${sysDate}" /> 
<hdiits:hidden name="hidden_employeeSelArray"></hdiits:hidden>
<hdiits:hidden name="trngId" default="${resValue.trngId}"/>
<hdiits:hidden name="schId" default="${resValue.schId}"/>
<hdiits:hidden name="counter" />
<script>
		document.forms[0].counter.value= ${counter};
</script>


<TABLE class="tabtable">
		<hdiits:jsField name="chkrecords" jsFunction="noRecords()"/>
		<tr> 
		<td class="fieldLabel" width="25%">
		<b><hdiits:caption captionid="TR.TrainingName"	bundle="${trngLables}" /></b>
		<hdiits:hidden name="hdntrngId" id="hdntrngId" default="${trngVO.trainingMstId}"/>	
		<hdiits:hidden name="hdnschId" id="hdnschId" default="${schId}"/>	
		<hdiits:hidden name="editFlag" id="editFlag" default="${editFlag}"/>	
		
		</td>
		<td width="25%"><c:out value="${trngVO.trainingName}" /></td>
		<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.TrainingType" bundle="${trngLables}" /></b></td>
		<td width="25%"><c:out value="${trngVO.cmnLookupMstTrainingTypeLookupId.lookupDesc}" /></td>
		</tr>
		
		<tr id="summ">
		<td class="fieldLabel"><hdiits:caption captionid="TR.Total"	bundle="${trngLables}" /></td>
		<td id="sumTotal" class="fieldLabel">&nbsp;</td>
		</tr>
		<tr id="summ1">
		<td class="fieldLabel"><hdiits:caption captionid="TR.Tent"	bundle="${trngLables}" /></td>
		<td id="sumTent" class="fieldLabel">&nbsp;</td>
		<td class="fieldLabel"><hdiits:caption captionid="TR.Confirmed"	bundle="${trngLables}" /></td>
		<td id="sumConf" class="fieldLabel">&nbsp;</td>
		</tr>
		
		<tr height="10"></tr>
		<tr>
		<td class="datatableheader" colspan="6"><b><hdiits:caption captionid="TR.NOMINATION" bundle="${nmLables}"/></b></td>
		</tr>
		<tr height="10" id="employeeAdd">
		<td class="fieldLabel" colspan="6">	
			<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
				<jsp:param name="SearchEmployee" value="EmpNominated"/>
				<jsp:param name="searchEmployeeTitle" value="${title}"/> 
				<jsp:param name="mandatory" value="No"/> 
				<jsp:param name="functionName" value="AddEmployee"/>
				<jsp:param name="multiEmployeeSel" value="Yes"/>
				<jsp:param name="childLocSearch" value="Yes"/>
			</jsp:include>
		</td>
		</tr>
		
		<tr>
		<c:if test="${show3rdTab eq 'Y'}">
			<script type="text/javascript">
			document.getElementById('employeeAdd').style.display = 'none';
			
			</script>
			<c:set var="remarksReadonly" value="true"/>
		</c:if>
		
		<c:if test="${show3rdTab ne 'Y'}">
			<script type="text/javascript">
			document.getElementById('summ').style.display = 'none';
			document.getElementById('summ1').style.display = 'none';
			
			</script>
		</c:if>
		
		<hdiits:hidden name="fileStatus" default="${fileStatus}" />
		
		<c:if test="${fileStatus eq 'sent'}">
		<script>
			document.getElementById("employeeAdd").style.display='none';
		</script>
		<c:set var="remarksReadonly" value="true"/>	
		<c:set var="hideColumn" value="true"></c:set>	
		<table id="noDataFound">
		<tr>
		<td>
		<hdiits:caption captionid="TR.FILESENDED" bundle="${nmLables}"/>
		</td>
		</tr>
		</table>
		</c:if>		
		
		<c:if test="${size == 0}">
		<c:if test="${fileStatus ne 'sent'}">
		<table id="noDataFound">
		<tr>
		<td>
		<hdiits:caption captionid="TR.NODATAFOUND" bundle="${nmLables}"/>
		</td>
		</tr>
		</table>
		</c:if>
		</c:if>
		</tr>
		<c:if test="${size >= 0}">
			<c:if test="${typefl eq 'fromFileTrngCenter'}">
				 <B><c:out value="${msgred}"/></B>
				 <br>
				 <B><c:out value="${msgdt}"/>&nbsp;&nbsp;<c:out value="${nmrcdt}"/>
		         <br><br>
        	</c:if>
		<tr>
		<td colspan="6" width="100%">
		<table id="nomTable" border="1"  class="datatable"> 
		<tr>
		<td class="datatableheader" style="display: none;"><b><hdiits:caption captionid="TR.SRNO"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.EMPNAME"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.DESIGNATION"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.REMARKS"	bundle="${nmLables}" /></b></td>
		<c:if test="${hideColumn ne 'true'}">
			<td class="datatableheader"><b>
			<c:if test="${show3rdTab ne 'Y'}">
				<hdiits:caption captionid="TR.RESERVED"	bundle="${nmLables}" />
			</c:if>
		</c:if>
		<c:if test="${show3rdTab eq 'Y'}">
		<hdiits:caption captionid="TR.STATUS"	bundle="${nmLables}" />
		</c:if>
		<c:if test="${hideColumn ne 'true'}">
			<td class="datatableheader"><b>Delete</b></td>
		</c:if>
		</tr>
		
		<c:choose>
			<c:when test="${typefl eq 'fromFileTrngCenter'}">
			    <c:forEach var="nm" items="${nominationDetails}">

				<c:if test="${nm.createdDate ne null}">
						<fmt:formatDate value="${nm.createdDate}" pattern="dd/MM/yyyy" var="nmcrdate"/>
				</c:if>
				<c:set var="counter" value="${counter + 1}" > </c:set>
					<script>
					document.forms[0].counter.value= ${counter};
					</script>
				<c:choose>
					<c:when test="${nmcrdate le nmrcdt}">
		
							<tr id="NomNo${counter}">
								<td style="display: none"><hdiits:hidden name="Id_${counter}" default="${nm.nominationId}"/><c:out value="${nm.srNo}"/></td>
								<td><hdiits:hidden name="empId_${counter}" default="${nm.empId}"/><c:out value="${nm.empName}"/></td>
								<td><hdiits:hidden name="flag_${counter}" default="I"/><c:out value="${nm.designation}"/></td>
								<td><hdiits:textarea name="remarks_${counter}" caption="remarks" default="${nm.remarks}" readonly="${remarksReadonly}"/></td>
								<td>
								<c:if test="${hideColumn ne 'true'}">
								<c:if test="${show3rdTab ne 'Y'}">
								<hdiits:checkbox name="reserved_${counter}" caption="" value="Y" default="${nm.reserved}"/>
								</c:if>
								</c:if>
								
								<c:if test="${show3rdTab eq 'Y'}">
								<hdiits:radio id="reservedTent_${counter}" name="reserved_${counter}" caption="Tentative" value="Y" default="${nm.reserved}" onclick="changeSummery()"/><br>
								<hdiits:radio id="reservedConf_${counter}" name="reserved_${counter}" caption="Confirmed" value="N" default="${nm.reserved}"  onclick="changeSummery()"/>
								</c:if>
								<c:set var="reserved" value="${nm.reserved}"/>
								
								</td>
								<c:if test="${hideColumn ne 'true'}">
									<td><hdiits:hidden name="rowId${counter}" default="${counter}"/> <hdiits:hidden id="chkbx_${counter}" name="chkbx_${counter}" default="W"/><a href="javascript:deleteNomRow(${counter})">delete</a></td>
								</c:if>
						  </tr>
						
					</c:when>
					<c:otherwise>
		
							<tr id="NomNo${counter}">
									<td style="display: none"><hdiits:hidden name="Id_${counter}" default="${nm.nominationId}"/><c:out value="${nm.srNo}"/></td>
									<td><hdiits:hidden name="empId_${counter}" default="${nm.empId}"/><font color="red"><c:out value="${nm.empName}"/></font></td>
									<td><hdiits:hidden name="flag_${counter}" default="I"/><font color="red"><c:out value="${nm.designation}"/></font></td>
									<td><hdiits:textarea name="remarks_${counter}" caption="remarks" default="${nm.remarks}" readonly="${remarksReadonly}"/></td>
									<td>
									<c:if test="${hideColumn ne 'true'}">
									<c:if test="${show3rdTab ne 'Y'}">
									<hdiits:checkbox name="reserved_${counter}" caption="" value="Y" default="${nm.reserved}"/>
									</c:if>
									</c:if>
									
									<c:if test="${show3rdTab eq 'Y'}">
									<font color="red"><hdiits:radio id="reservedTent_${counter}" name="reserved_${counter}" caption="Tentative" value="Y" default="${nm.reserved}" onclick="changeSummery()"/></font><br>
									<font color="red"><hdiits:radio id="reservedConf_${counter}" name="reserved_${counter}" caption="Confirmed" value="N" default="${nm.reserved}"  onclick="changeSummery()"/></font>
									</c:if>
									<c:set var="reserved" value="${nm.reserved}"/>
									
									</td>
									<c:if test="${hideColumn ne 'true'}">
										<td><hdiits:hidden name="rowId${counter}" default="${counter}"/> <hdiits:hidden id="chkbx_${counter}" name="chkbx_${counter}" default="W"/><a href="javascript:deleteNomRow(${counter})">delete</a></td>
									</c:if>
							</tr>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
					<c:forEach var="nm" items="${nominationDetails}">

						<c:if test="${nm.createdDate ne null}">
								<fmt:formatDate value="${nm.createdDate}" pattern="dd/MM/yyyy" var="nmcrdate"/>
						</c:if>
						<c:set var="counter" value="${counter + 1}" > </c:set>
							<script>
								document.forms[0].counter.value= ${counter};
							</script>
							<tr id="NomNo${counter}">
							<td style="display: none"><hdiits:hidden name="Id_${counter}" default="${nm.nominationId}"/><c:out value="${nm.srNo}"/></td>
							<td><hdiits:hidden name="empId_${counter}" default="${nm.empId}"/><c:out value="${nm.empName}"/></td>
							<td><hdiits:hidden name="flag_${counter}" default="I"/><c:out value="${nm.designation}"/></td>
							<td><hdiits:textarea name="remarks_${counter}" caption="remarks" default="${nm.remarks}" readonly="${remarksReadonly}"/></td>
							<td>
							<c:if test="${hideColumn ne 'true'}">
							<c:if test="${show3rdTab ne 'Y'}">
							<hdiits:checkbox name="reserved_${counter}" caption="" value="Y" default="${nm.reserved}"/>
							</c:if>
							</c:if>
							
							<c:if test="${show3rdTab eq 'Y'}">
							<hdiits:radio id="reservedTent_${counter}" name="reserved_${counter}" caption="Tentative" value="Y" default="${nm.reserved}" onclick="changeSummery()"/><br>
							<hdiits:radio id="reservedConf_${counter}" name="reserved_${counter}" caption="Confirmed" value="N" default="${nm.reserved}"  onclick="changeSummery()"/>
							</c:if>
							<c:set var="reserved" value="${nm.reserved}"/>
							
							</td>
							<c:if test="${hideColumn ne 'true'}">
								<td><hdiits:hidden name="rowId${counter}" default="${counter}"/> <hdiits:hidden id="chkbx_${counter}" name="chkbx_${counter}" default="W"/><a href="javascript:deleteNomRow(${counter})">delete</a></td>
							</c:if>
					  	</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		</table>
		</td>
		</tr>
		</c:if>

</TABLE>

<script type="text/javascript">
var counter=${counter};
changeSummery();
function changeSummery()
{
	var tent = 0;
	var conf = 0;
	for(var i = 0; i<${counter}; i++)
	{
		if(document.getElementById("reservedTent_"+(i+1)) != null)
		{
			if(document.getElementById("reservedTent_"+(i+1)).checked)
			{
				tent = tent + 1;
			}
		}
	
		if(document.getElementById("reservedConf_"+(i+1)) != null)
		{
			if(document.getElementById("reservedConf_"+(i+1)).checked)
			{
				conf = conf + 1;
			}
		}
	}
	var sumTentCell = document.getElementById("sumTent");
	var sumConfCell = document.getElementById("sumConf");
	var sumTotalCell = document.getElementById("sumTotal");
	
	var sumTentCelltext =sumTentCell.childNodes.item(0);
	var sumConfCelltext =sumConfCell.childNodes.item(0);
	var sumTotalCelltext =sumTotalCell.childNodes.item(0);
	
	sumTentCell.removeChild(sumTentCelltext);
	sumConfCell.removeChild(sumConfCelltext);
	sumTotalCell.removeChild(sumTotalCelltext);
	
	sumTentCell.appendChild(document.createTextNode(tent));
	sumConfCell.appendChild(document.createTextNode(conf));
	sumTotalCell.appendChild(document.createTextNode(tent+conf));

}

function deleteNomRow(rowId)
{
	var rowName = "NomNo"+rowId;
	var hiddenName ="chkbx_"+rowId;
	document.getElementById(rowName).style.display="none";
	document.getElementById(hiddenName).value="";
}

</script>		
