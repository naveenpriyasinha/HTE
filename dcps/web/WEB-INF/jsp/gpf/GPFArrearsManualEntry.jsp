<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EmpArrearsList" value="${resValue.EmpArrearsList}"></c:set>
<c:set var="EmpGroup" value="${resValue.EmpGroup}"></c:set>
<c:set var="Index" value="1"></c:set>
<c:set var="counter" value="1"></c:set>
<c:set var="prvCnt" value="1"></c:set>
<c:forEach var="prevValue" items="${resValue.PrvValue}">
<input type="hidden" id="hidPrevMonId${prvCnt}" name="hidPrevMonId${prvCnt}" value="${prevValue[0]}" />
<input type="hidden" id="hidPrevYearId${prvCnt}" name="hidPrevYearId${prvCnt}" value="${prevValue[1]}" />
<c:set var="prvCnt" value="${prvCnt+1}"></c:set>
</c:forEach>

<script type="text/javascript" src="script/gpf/requestProcessing.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script type="text/javascript" src="script/gpf/arrearsManualEntry.js"></script>
<script language="javascript">
function init(){
	var msg = document.getElementById("txtAlert").value;
	if(msg != "" && msg!=null){
		if(msg == "InvalidSevaarthId")
			alert("Invalid SevaarthId");
		else
			alert("All Installments are paid for this Employee");
	}
}
function showHideEmpGroup(){
	var radio = document.GpfArrearsManualEntryForm.rdoSelectEmp;
	var radioValue;
	for (i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}
	if(radioValue=="A"){
		document.getElementById("trChechbox").style.display = '';
		document.getElementById("trSevaarthId").style.display = 'none';
	}else{
		document.getElementById("trChechbox").style.display = 'none';
		document.getElementById("trSevaarthId").style.display = '';
	}
}
function submitData(){
	var radio = document.GpfArrearsManualEntryForm.rdoSelectEmp;
	var empGroup = document.GpfArrearsManualEntryForm.cbEmpGroup;
	var radioValue = "";
	var url = "";
	for (i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}if(radioValue==""){
		alert('Please Select All Employee Or Single Employee');
		return false;
	}
	else if(radioValue=="A"){
		var flag=0;
		var selectedGroup="";
		for(var i=0; i < empGroup.length; i++){
			if(empGroup[i].checked == true){
				selectedGroup= selectedGroup + empGroup[i].value + "~";
				flag=1;		
			}
		}
		if(flag==0){
			alert('Please select an Employee Group');
			return false;
		}else{
			selectedGroup = selectedGroup.substring(0, selectedGroup.length-1);
			url="ifms.htm?actionFlag=popUpEmpDtlsGroup&selectedGroup="+selectedGroup;
		}
	}else{
		var sevaarthId = document.getElementById("txtSevaarthId").value;
		if(sevaarthId==""){
			alert('Please Enter Sevaarth ID');
			return false;
		}else{
			url="ifms.htm?actionFlag=popUpEmpArrearsDtls&sevaarthId="+sevaarthId;
		}
	}
	document.GpfArrearsManualEntryForm.action = url ;
	document.GpfArrearsManualEntryForm.submit();
}
</script>
<c:forEach var="EmpGroup" items="${EmpGroup}">
	<c:choose>
	<c:when test="${EmpGroup=='A'}">
		<c:set var="checkedA" value="checked='checked'"></c:set>
	</c:when>
	<c:when test="${EmpGroup=='B'}">
		<c:set var="checkedB" value="checked='checked'"></c:set>
	</c:when>
	<c:when test="${EmpGroup=='BnGz'}">
		<c:set var="checkedBnGz" value="checked='checked'"></c:set>
	</c:when>
	<c:when test="${EmpGroup=='C'}">
		<c:set var="checkedC" value="checked='checked'"></c:set>
	</c:when>
	<c:when test="${EmpGroup=='D'}">
		<c:set var="checkedD" value="checked='checked'"></c:set>
	</c:when>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test="${resValue.EntryType=='A'}">
		<c:set var="checkedAll" value="checked='checked'"></c:set>
		<c:set var="displaySingle" value="display:none"></c:set>
		<c:set var="disableForEmp" value="display:none"></c:set>
	</c:when>
	<c:when test="${resValue.EntryType=='S'}">
		<c:set var="checkedSingle" value="checked='checked'"></c:set>
		<c:set var="displayGroups" value="display:none"></c:set>
		<c:set var="disableForGroup" value="display:none"></c:set>
	</c:when>
	<c:when test="${resValue.EntryType=='Offline'}">
		<c:set var="checkedAll" value="checked='checked'"></c:set>
		<c:set var="displaySingle" value="display:none"></c:set>
		<c:set var="disableForEmp" value="display:none"></c:set>
		<c:set var="disableSelection" value="display:none"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="displayGroups" value="display:none"></c:set>
		<c:set var="displaySingle" value="display:none"></c:set>
		<c:set var="disableForGroup" value="display:none"></c:set>
		<c:set var="disableForEmp" value="display:none"></c:set>
	</c:otherwise>
</c:choose>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>
<hdiits:form name="GpfArrearsManualEntryForm" id="GpfArrearsManualEntryForm" encType="multipart/form-data" validate="true" method="post">
<input type="hidden" id="hidPreYearId" name="hidPreYearId" value="${resValue.PreYearId}"/>
<input type="hidden" id="hidPreMonthId" name="hidPreMonthId" value="${resValue.PreMonthId}"/>
<fieldset style="width:100%" class="tabstyle" style="${disableSelection}"><legend><fmt:message key="CMN.ARREARSMANUALENTRY" bundle="${gpfLabels}"></fmt:message></legend>
</br>
<table width="60%" >
	<tr>
		<td width="15%"><input type="radio" id="rdoForAllEmp" name="rdoSelectEmp" value="A" onclick="showHideEmpGroup()" ${checkedAll}/>
		<fmt:message key="CMN.ALLEMPLOYEE" bundle="${gpfLabels}"></fmt:message></td>		
		<td width="15%"><input type="radio" id="rdoForSingleEmp" name="rdoSelectEmp" value="S" onclick="showHideEmpGroup()" ${checkedSingle}/>
		<fmt:message key="CMN.SINGLEEMP" bundle="${gpfLabels}"></fmt:message></td>
	</tr>
	<tr id="trChechbox" style="${displayGroups}">
	
	<td colspan="2">
	<br/>
		<input type="checkbox" name="cbEmpGroup" id="cbGroupA" value="A" onclick="" ${checkedA}>
		<fmt:message key="CMN.A" bundle="${gpfLabels}"></fmt:message>&nbsp;	&nbsp;			
		<input type="checkbox" name="cbEmpGroup" id="cbGroupB" value="B" onclick="" ${checkedB}>
		<fmt:message key="CMN.B" bundle="${gpfLabels}"></fmt:message>&nbsp;&nbsp;
		<input type="checkbox" name="cbEmpGroup" id="cbGroupBnGz" value="BnGz" onclick="" ${checkedBnGz}>	
		<fmt:message key="CMN.BNGZ" bundle="${gpfLabels}"></fmt:message>&nbsp;	&nbsp;	
		<input type="checkbox" name="cbEmpGroup" id="cbGroupC" value="C" onclick="" ${checkedC}>		
		<fmt:message key="CMN.C" bundle="${gpfLabels}"></fmt:message>&nbsp;&nbsp;
		<input type="checkbox" name="cbEmpGroup" id="cbGroupD" value="D" onclick="" ${checkedD}>		
		<fmt:message key="CMN.D" bundle="${gpfLabels}"></fmt:message>
	</td>
	</tr>

	<tr id="trSevaarthId" style="${displaySingle}">
		<td width="15%"><br></td>
		<td width="15%"><br>	
		<fmt:message key="CMN.SEVAARTHID" bundle="${gpfLabels}"></fmt:message>
		<input type="text" id="txtSevaarthId" name="txtSevaarthId" value="${resValue.SevaarthID}"/>
		</td>
	</tr>
	
	<tr >
		<td colspan="2" ><br/><hdiits:button type="button" captionid="BTN.GO" bundle="${gpfLabels}" id="btnGo" name="btnGo" onclick="submitData();"></hdiits:button></td>
		
	</tr>
</table>
</br>
</fieldset>
<fieldset style="width:100%" class="tabstyle" style="${disableForEmp}"><legend><fmt:message key="CMN.FOREMP" bundle="${gpfLabels}"></fmt:message></legend>
<input type="hidden" id="hidPkValue" name="hidPkValue" value="${resValue.Pkvalue}"/>
<table width="80%" cellspacing = "4" cellpadding = "4">
	<tr>
		<td width="15%">
		<fmt:message key="CMN.SEVAARTHID" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="50%">
		<input type="text" id="txtSevaarthIdForEmp" name="txtSevaarthIdForEmp" readonly="readonly" value="${resValue.SevaarthID}"/>
		</td>

	</tr>
	<tr>
		<td width="15%">
		<fmt:message key="CMN.EMPNAME" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="50%">
		<input type="text" id="txtEmpName" name="txtEmpName" readonly="readonly" value="${resValue.EmpName}" size="35"/>
		</td>

	</tr>
	<tr>
		<td width="15%">
		<fmt:message key="CMN.GPFACCNO" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="50%">
		<input type="text" id="txtGpfAccNo" name="txtGpfAccNo" readonly="readonly" value="${resValue.GpfAccNo}"/>
		</td>

	</tr>
	<tr>
		<td width="15%">
		<fmt:message key="CMN.OFFICE" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="50%">
		<input type="text" id="txtOfficeName" name="txtOfficeName" readonly="readonly" value="${resValue.OfficeName}" size="35"/>
		</td>

	</tr>
	<tr>
		<td width="15%">
		<fmt:message key="CMN.FORYEAR" bundle="${gpfLabels}" ></fmt:message>
		</td>
		<td width="50%">
		<select name="cmbYearForEmp" id="cmbYearForEmp" style="width:100px">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="yearList" items="${resValue.yearList}">
						<option value="${yearList.id}">
							<c:out	value="${yearList.desc}"/>
						</option>
				</c:forEach>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<fmt:message key="CMN.MONTH" bundle="${gpfLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<select name="cmbMonthForEmp" id="cmbMonthForEmp" style="width:100px" >
		<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>				
				<c:forEach var="monthList" items="${resValue.monthList}">
						<option value="${monthList.id}">
							<c:out	value="${monthList.desc}"/>
						</option>
				</c:forEach>
		</select>
		</td>

	</tr>
	<tr>
		<td width="15%">
		<fmt:message key="CMN.TOTALAMOUNT" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="50%">
		<input type="text" id="txtTotalAmountForEmp" name="txtTotalAmountForEmp" style="text-align: right" onkeypress="amountFormat(this);"/>
		</td>

	</tr>
	<tr>
		<td width="15%">
		<fmt:message key="CMN.INSTALLMENTNO" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="50%">
		<input type="text" id="txtInstallmentNo" name="txtInstallmentNo" readonly="readonly" value="${resValue.InstallmentNo}"/>
		</td>

	</tr>
	<tr>
		<td width="15%">
		<fmt:message key="CMN.INSTALLMENTAMOUNT" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="50%">
		<input type="text" id="txtInstallmentAmountForEmp" name="txtInstallmentAmountForEmp" style="text-align: right" onkeypress="amountFormat(this);"/>
		</td>

	</tr>
</table>
</fieldset>
<c:if test="${resValue.EntryType!='Offline'}">
<fieldset style="width:100%" class="tabstyle" style="${disableForGroup}"><legend><fmt:message key="CMN.FORGROUP" bundle="${gpfLabels}"></fmt:message></legend>
<br/>
<display:table list="${EmpArrearsList}" size="${resValue.totalRecords}" pagesize="10"  id="vo" style="width:100%" requestURI="">
<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.SRNO" headerClass="datatableheader" value="${Index}" > 						           	           	         
	    </display:column>		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.SEVAARTHID" headerClass="datatableheader">
			<c:out value="${vo[0]}"></c:out> 
			<input type="hidden" id="hidSevaarthId${counter}" name="hidSevaarthId" value="${vo[0]}"/>
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.EMPNAME" headerClass="datatableheader">
			<c:out value="${vo[1]}"></c:out> 
			<input type="hidden" id="hidEmpName${counter}" name="hidEmpName" value="${vo[1]}"/> 
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.GPFACCNO" headerClass="datatableheader">
			<c:out value="${vo[2]}"></c:out> 
			<input type="hidden" id="hidGpfAccNo${counter}" name="hidGpfAccNo" value="${vo[2]}"/>
		</display:column>
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.INSTALLMENTNO" headerClass="datatableheader">
			<c:out value="${vo[3]}"></c:out> 
			<input type="hidden" id="hidInstNo${counter}" name="hidInstNo${counter}" value="${vo[3]}"/>
		</display:column>
		<display:column style="text-align: center;width:40px;" class="oddcentre" titleKey="CMN.TOTALAMOUNT" headerClass="datatableheader" >
			<input type = "text" id="txtTotalAmount${counter}" name="txtTotalAmount" size="15" style="text-align: right" onkeypress="amountFormat(this);"/>
		</display:column>
		<display:column style="text-align: center;width:40px;" class="oddcentre" titleKey="CMN.INSTALLMENTAMOUNT" headerClass="datatableheader"> 
			<input type = "text" id="txtInstAmount${counter}" name="txtInstAmount" size="15" style="text-align: right" onkeypress="amountFormat(this);"/>
		</display:column>		
		<display:column style="text-align: center;" class="oddcentre" titleKey="CMN.FINYEAR" headerClass="datatableheader">
		<select name="cmbYear" id="cmbYear${counter}" style="width:100px" ${varDisabled} onchange="validateSchdlMonYear('${Index}');">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="yearList" items="${resValue.yearList}">
						<option value="${yearList.id}">
							<c:out	value="${yearList.desc}"/>
						</option>
				</c:forEach>
		</select>
		</display:column>
		<display:column style="text-align: center;" class="oddcentre" titleKey="CMN.MONTH" headerClass="datatableheader">
		<select name="cmbMonth" id="cmbMonth${counter}" style="width:100px" ${varDisabled} onchange="validateSchdlMonYear('${Index}');">
		<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>				
				<c:forEach var="monthList" items="${resValue.monthList}">
						<option value="${monthList.id}">
							<c:out	value="${monthList.desc}"/>
						</option>
				</c:forEach>
		</select>
		</display:column>
		
		<display:column class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"cbEmpGroup\");'/>" headerClass="datatableheader">
		   		<input type="checkbox" id="chkReq_${counter}" name="chkReq" value="${vo[0]}"/>
		</display:column>		
		<c:set var="Index" value="${Index+1}"> </c:set>		
		<c:set var="counter" value="${counter+1}"></c:set>		
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/>
</fieldset>
</c:if>
<c:if test="${resValue.EntryType=='Offline'}">
<fieldset style="width:100%" class="tabstyle" style="${disableForGroup}"><legend><fmt:message key="CMN.OFFLINEARREARDATA" bundle="${gpfLabels}"></fmt:message></legend>
<display:table list="${EmpArrearsList}" size="${resValue.totalRecords}" pagesize="10"  id="vo" style="width:100%" requestURI="">
<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.SRNO" headerClass="datatableheader" value="${Index}" > 	
		           <c:set var="Index" value="${Index+1}"> </c:set>	           	          
	    </display:column>		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.SEVAARTHID" headerClass="datatableheader" value="${vo[0]}">
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.EMPNAME" headerClass="datatableheader" value="${vo[1]}"> 
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.GPFACCNO" headerClass="datatableheader">
			<c:out value="${vo[2]}"></c:out> 
			<input type="hidden" id="hidGpfAccNo${counter}" name="hidGpfAccNo" value="${vo[2]}"/>
		</display:column>
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.INSTALLMENTNO" headerClass="datatableheader">
			<c:out value="${vo[3]}"></c:out> 
			<input type="hidden" id="hidInstNo${counter}" name="hidInstNo${counter}" value="${vo[3]}"/>
		</display:column>
		<display:column style="text-align: center;width:40px;" class="oddcentre" titleKey="CMN.TOTALAMOUNT" headerClass="datatableheader" >
			<input type = "text" id="txtTotalAmount${counter}" name="txtTotalAmount" size="15" style="text-align: right" value="${vo[5]}" readOnly="readOnly" onkeypress="amountFormat(this);"/>
		</display:column>
		<display:column style="text-align: center;width:40px;" class="oddcentre" titleKey="CMN.INSTALLMENTAMOUNT" headerClass="datatableheader"> 
			<input type = "text" id="txtInstAmount${counter}" name="txtInstAmount" size="15" style="text-align: right" value="${vo[6]}" readOnly="readOnly" onkeypress="amountFormat(this);"/>
		</display:column>		
		<display:column style="text-align: center;" class="oddcentre" titleKey="CMN.FINYEAR" headerClass="datatableheader">
		<select name="cmbYear" id="cmbYear${counter}" style="width:100px" ${varDisabled} disabled="disabled">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="yearList" items="${resValue.yearList}">
				<c:choose>
					<c:when test="${vo[7] == yearList.id}">
						<option value="${yearList.id}" selected="selected"><c:out
							value="${yearList.desc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${yearList.id}">
							<c:out	value="${yearList.desc}"/>
						</option>
						</c:otherwise>
				</c:choose>
				</c:forEach>
				
		</select>
		</display:column>
		<display:column style="text-align: center;" class="oddcentre" titleKey="CMN.MONTH" headerClass="datatableheader">
		<select name="cmbMonth" id="cmbMonth${counter}" style="width:100px" ${varDisabled} disabled="disabled">
		<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>				
				<c:forEach var="monthList" items="${resValue.monthList}">
				<c:choose>
					<c:when test="${vo[8] == monthList.id}">
						<option value="${monthList.id}" selected="selected"><c:out
							value="${monthList.desc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${monthList.id}">
							<c:out	value="${monthList.desc}"/>
						</option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
		</select>
		</display:column>
		
		<display:column class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"cbEmpGroup\");'/>" headerClass="datatableheader">
		   		<input type="checkbox" id="chkReq_${counter}" name="chkReq" value="${vo[0]}"/>
		</display:column>	
		   <c:set var="counter" value="${counter+1}"></c:set>		
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/>
</fieldset>
</c:if>

<c:if test="${resValue.EntryType=='A' || resValue.EntryType=='S'}">
</br>
<table width="80%">
  <tr >
    <td align="center"><hdiits:button type="button" captionid="BTN.APPROVE" bundle="${gpfLabels}" id="btnApprove" name="btnApprove" onclick="approveGPFArrearsCase();"></hdiits:button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <c:if test="${resValue.EntryType!='S'}"><hdiits:button type="button" captionid="BTN.DOWNLOADTOFILE" bundle="${gpfLabels}" id="btnDownloadToFile" name="btnDownloadToFile" onclick="downloadFile();" style="width:150px"></hdiits:button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
    <hdiits:button type="button" captionid="BTN.BACK" bundle="${gpfLabels}" id="btnBack" name="btnBack" onclick="backToArrEntryForm();"></hdiits:button></td>
  </tr>
</table>
</c:if>
<c:if test="${resValue.EntryType=='Offline'}">
</br>
<table width="80%">
  <tr >
    <td align="center"><hdiits:button type="button" captionid="BTN.APPROVE" bundle="${gpfLabels}" id="btnApprove" name="btnApprove" onclick="approveGPFArrearsCase();"></hdiits:button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <hdiits:button type="button" captionid="BTN.BACK" bundle="${gpfLabels}" id="btnBack" name="btnBack" onclick="backToOfflineEntryForm();"></hdiits:button></td>
  </tr>
</table>
</c:if>
</hdiits:form>
<script>
    init();
</script>