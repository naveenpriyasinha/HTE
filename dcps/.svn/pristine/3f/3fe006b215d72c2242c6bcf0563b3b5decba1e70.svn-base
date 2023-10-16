
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.hr.roster.rosterMst" var="caption"
	scope="request" />
<fmt:setBundle basename="resources.hr.roster.RosterAlertMsges"
	var="alertMsges" scope="request" />

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript"
	src="script/hrms/hr/rosterScript/rosterSpecs.js">
</script>
<script type="text/javascript">
function enableDesignation(form){
	if(form.value=='-1'){
		document.getElementById('dsgn').disabled="none";
		document.getElementById('dsgn').selectedIndex=0;
	}else
	{
		document.getElementById('dsgn').disabled="";
	}
	
	
}
function formValidate()
{

	if(document.getElementById('office').value=="-1")
	{
		alert("<fmt:message key="HRMS.deptReq" bundle="${alertMsges}" />");      
	
		return false;
	}
	if(document.getElementById("location").value=="-1")
	{
		alert("<fmt:message key="HRMS.locReq" bundle="${alertMsges}" />");      
		
		return false;
	}
	if(document.getElementById("dsgn").value=="-1")
	{
		alert("<fmt:message key="HRMS.dsgnReq" bundle="${alertMsges}" />");      
		
		return false;
	}else
	{
		if(document.getElementById("cmbSubDsgnCol").style.display==""){
					
			if(document.getElementById("subdsgn").value=="-1")
			{
				alert("<fmt:message key="HRMS.subdsgnReq" bundle="${alertMsges}" />");      
		
				return false;
			}
		}
	}
	if(document.getElementById("scCat").value=="")
	{
		alert("<fmt:message key="HRMS.scPerReq" bundle="${alertMsges}" />");      
		
		return false;
	}
	if(document.getElementById("stCat").value=="")
	{
		alert("<fmt:message key="HRMS.stPerReq" bundle="${alertMsges}" />");      
		
		return false;
	}
	if(document.getElementById("sebcCat").value=="")
	{
		alert("<fmt:message key="HRMS.sebcPerReq" bundle="${alertMsges}" />");      
		
		return false;
	}
	if(document.getElementById("phCat").value=="")
	{
		alert("<fmt:message key="HRMS.phPerReq" bundle="${alertMsges}" />");      
	
		return false;
	}
	if(document.getElementById("vac").value=="")
	{
		alert("<fmt:message key="HRMS.vacReq" bundle="${alertMsges}" />");      
		
		return false;
	}
	var totalSum=document.getElementById("phCat").value*1+document.getElementById("scCat").value*1+document.getElementById("sebcCat").value*1+document.getElementById("stCat").value*1;
	if(totalSum>100)
	{
		alert("<fmt:message key="HRMS.quotaLimit" bundle="${alertMsges}" />");      
	
		return false;
	}
	
	return true;
}
function checkDecimalInBox(form){

	var valOfTxt =form.value;
	var result=valOfTxt.indexOf(".") ;
	if(result=='-1')
	{
		return true;
	}else
	{
		alert("<fmt:message key="HRMS.decimalNotAllow" bundle="${alertMsges}" />");      
		
		form.value="";
		return false;
	}
}

</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="departList" value="${resultValue.departList}">
</c:set>
<c:set var="dsgnList" value="${resultValue.dsgnList}">
</c:set>
<c:set var="locList" value="${resultValue.locList}">
</c:set>

<script type="text/javascript">

</script>

<hdiits:form name="frm1" validate="true" method="POST"
	encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="Hr_newrecruitreq" bundle="${caption}" /> </a></li>
	</ul>
	</div>


	<div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0"><hdiits:hidden
		name="wffileId_hidden" id="wffileId_hidden" /> <br>
	<br>


	<font color="RED" size="3">
	<center><span id="errorId"> </span></center>
	</font> <br>
	<br>
	<table border="0" width="90%" align="center">
		<tr>

			<td><hdiits:caption captionid="Hr_lbl_off" bundle="${caption}" /></td>
			<td>
			<hdiits:hidden name="office" id="office" default="${departList.departmentId}" />
			<strong><c:out value="${departList.depName}" /></strong>
			</td>

			<td><hdiits:caption captionid="Hr_lbl_location"
				bundle="${caption}" /></td>
			<td>
			<hdiits:hidden name="location" id="location" default="${locList.locId}" />
			<strong><c:out value="${locList.locName}" /></strong>
			</td>
			<td><hdiits:caption captionid="Hr_lbl_designation"
				bundle="${caption}" /></td>
			<td><hdiits:select name="dsgn" size="1" caption="dsgn" id="dsgn"
				tabindex="3"  onchange="getDBData()">
				<hdiits:option value="-1">------Select -----</hdiits:option>
				<c:forEach var="dsgnList" items="${dsgnList}">
					<hdiits:option value="${dsgnList.dsgnId}">
							${dsgnList.dsgnName}
						</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td style="display:none" id="lblSubDsgn"><hdiits:caption
				captionid="Hr_lbl_sub_designation" bundle="${caption}" /></td>
			<td style="display:none" id="cmbSubDsgnCol"><hdiits:select
				name="subdsgn" size="1" caption="subdsgn" id="subdsgn" tabindex="3"
				onchange="checkForApprove()">
				<hdiits:option value="-1">------Select -----</hdiits:option>
			</hdiits:select></td>
			<td><hdiits:caption captionid="Hr_lbl_vac" bundle="${caption}" />
			</td>
			<td><hdiits:number name="vac" id="vac" maxlength="3" size="3"
				onblur="checkDecimalInBox(this)" /></td>
		</tr>
		<tr>
			<td>&nbsp</td>
		</tr>

		<tr>
			<td><hdiits:caption captionid="Hr_sclabl" bundle="${caption}" />
			</td>
			<td><hdiits:number name="scCat" maxlength="2" size="3"
				onblur="checkDecimalInBox(this)" /><strong>%</strong></td>
			<td><hdiits:caption captionid="Hr_stlabl" bundle="${caption}" />
			</td>
			<td><hdiits:number name="stCat" maxlength="2" size="3"
				onblur="checkDecimalInBox(this)" /><strong>%</strong></td>
		</tr>
		<tr>
			<td>&nbsp</td>
		</tr>
		<tr>
			<td>&nbsp</td>
		</tr>

		<tr>
			<td><hdiits:caption captionid="Hr_sebclabl" bundle="${caption}" /></td>
			<td><hdiits:number name="sebcCat" maxlength="2" size="3"
				onblur="checkDecimalInBox(this)" /><strong>%</strong></td>
			<td><hdiits:caption captionid="Hr_phlabl" bundle="${caption}" /></td>
			<td><hdiits:number name="phCat" maxlength="2" size="3"
				onblur="checkDecimalInBox(this)" /><strong>%</strong></td>
		</tr>
		<tr>
			<td>&nbsp</td>
		</tr>

	</table>
		
			<center><hdiits:button name="SubmitBt" type="button"
				id="SubmitBt" value="Submit" onclick="submitDtls(this)" /> <hdiits:button
				name="Close" type="button" id="Close" value="Close"
				onclick="closeWindows()" /></center>
		


	</div>
	</div>
	<hdiits:hidden name="reqId" id="reqId" />
	<hdiits:hidden name="noOfVac" id="noOfVac" />
	<hdiits:hidden name="flagOfupdation" id="flagOfupdation" />
	<hdiits:hidden name="rosterType" id="rosterType" default="R" />
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
	<script type="text/javascript">		
		initializetabcontent("maintab");
		</script>

</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


