<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/allocation/EmpBasicInfo.js"/>"></script>

<fmt:setBundle basename="resources.hr.posting.postingLabels" var="empEditListCommonLables" scope="request" />


<script type="text/javascript">
	var empBasicInfoAlertMsgArr = new Array();
	empBasicInfoAlertMsgArr[0]="<fmt:message  bundle="${empEditListCommonLables}" key="joingDateAlert"/>";
	empBasicInfoAlertMsgArr[1]='<fmt:message  bundle="${empEditListCommonLables}" key="DorAndDojAlert"/>';
	empBasicInfoAlertMsgArr[2]='<fmt:message  bundle="${empEditListCommonLables}" key="DojAndDobAlert"/>';
	empBasicInfoAlertMsgArr[3]='<fmt:message  bundle="${empEditListCommonLables}" key="ValidBaisPayAlert1"/>';
	empBasicInfoAlertMsgArr[4]=' <fmt:message  bundle="${empEditListCommonLables}" key="ValidBaisPayAlert2"/>';
	empBasicInfoAlertMsgArr[5]=' <fmt:message  bundle="${empEditListCommonLables}" key="ValidBaisPayAlert3"/>';
</script>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="empInfoDtlsEng" value="${resValue.empInfoDtlsEng}"/>
<c:set var="empInfoDtlsGuj" value="${resValue.empInfoDtlsGuj}"/>
<c:set var="empInfoDtls" value="${resValue.empInfoDtls}"/>
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>
<c:set var="classDtls" value="${resValue.classDtls}"></c:set>
<c:set var="basicPay" value="${resValue.basicPay}"></c:set>
<c:set var="payScaleValue" value="${resValue.payScaleValue}"></c:set>
<c:set var="dsgnCode" value="${resValue.dsgnCode}"></c:set>
<c:set var="typeOfEmployeementLst" value="${resValue.typeOfEmployeementLst}"></c:set>
<c:set var="empMentName" value="${resValue.empMentName}"></c:set>
<c:set var="gradeCode" value="${resValue.gradeCode}"></c:set>
<c:set var="strAppType" value="SRVC-JOINING"></c:set>

<fmt:formatDate pattern="dd/MM/yyyy" value="${resValue.crnDate}" var="currentDate"/>

<hdiits:form name="frmEmpBasicInfo" validate="true" method="POST" action="" encType="multipart/form-data">
	
	<div id="tabmenu">
				<%@ include file="../../eis/ProfessionalDetailsTab.jsp"%>
			</div>
	
	<div class="tabcontentstyle" style="height: 100%">
	
	<div id="tcontent1" class="tabcontent" tabno="0">
	<table class="tabtable">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="USER_TYPE" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td width="35%">
				<hdiits:radio name="rdoTypeOfUser" id="rdoTypeOfUser" value="C" onclick="checkUserType()" captionid="NEW_USER"  bundle="${empEditListCommonLables}"   />
				<hdiits:radio name="rdoTypeOfUser" id="rdoTypeOfUser" value="H"  onclick="checkUserType()" captionid="EXISTING_USER" bundle="${empEditListCommonLables}"  /></td>
			<td width="15%"></td>
			<td width="25%"></td>
		</tr>
		
		<tr id="divGPFNo" style="display: none;">
			
			<td width="25%"><b><hdiits:caption captionid="GPF_TYPE" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td width="25%">
				<hdiits:radio name="rdoTypeOfGPF" id="rdoTypeOfGPF" value="Y" onclick="checkGPFType()" captionid="YES"  bundle="${empEditListCommonLables}"   />
				<hdiits:radio name="rdoTypeOfGPF" id="rdoTypeOfGPF" value="N"  onclick="checkGPFType()" captionid="NO" bundle="${empEditListCommonLables}"  /></td>
			<td width="25%" style="display: none;" id="GPFLabel"><hdiits:caption	captionid="GPF_NO" bundle="${empEditListCommonLables}"></hdiits:caption></td>
			<td width="25%" style="display: none;" id="GPFText"><hdiits:text name="txt_GPF_No" id="txt_GPF_No" captionid="GPF_NO" bundle="${empEditListCommonLables}" maxlength="18" mandatory="true" validation="txt.isrequired"/></td>
		</tr>
		
		<tr>
			<td class="fieldLabel">
				<b><hdiits:caption captionid="eis.EMP_SALUTATION" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td align="left">
				<hdiits:text name="Salutation" id="Salutation"
				maxlength="6" captionid="eis.EMP_SALUTATION" size="3"
				bundle="${empEditListCommonLables}" validation = "txt.isrequired" default="${empInfoDtls.empPrefix}" mandatory="true"/>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
			</td>
			<td class="fieldLabel" align="left"><b><hdiits:caption captionid="eis.LAST_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" align="left"><b><hdiits:caption captionid="eis.FIRST_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			<td class="fieldLabel" align="left"><b><hdiits:caption captionid="eis.MIDDLE_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
		</tr>
		<tr>
			<td>
				<b><hdiits:caption	captionid="NAME_EN" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<td align="left">
				<hdiits:text name="emp_last_name_eng" id="emp_last_name_eng" captionid="eis.LAST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired,txt.isname" default="${empInfoDtlsEng.empLname}" mandatory="true"/>
			</td>
			
			<td align="left">
				<hdiits:text name="emp_first_name_eng" id="emp_first_name_eng" captionid="eis.FIRST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired,txt.isname" default="${empInfoDtlsEng.empFname}" mandatory="true"/>
			</td>
			<td align="left">
				<hdiits:text name="emp_middle_name_eng" id="emp_middle_name_eng" captionid="eis.MIDDLE_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired,txt.isname" default="${empInfoDtlsEng.empMname}" mandatory="true"/>
			</td>
			
		</tr>
		<tr>
			<td>
				<b><hdiits:caption	captionid="NAME_GU" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<td align="left">
				<hdiits:text name="emp_last_name_guj" id="emp_last_name_guj" captionid="eis.LAST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired,txt.isname" default="${empInfoDtlsGuj.empLname}" mandatory="true"/>
			</td>
			
			<td align="left">
				<hdiits:text name="emp_first_name_guj" id="emp_first_name_guj" captionid="eis.FIRST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired,txt.isname" default="${empInfoDtlsGuj.empFname}" mandatory="true"/>
			</td>
			<td align="left">
				<hdiits:text name="emp_middle_name_guj" id="emp_middle_name_guj" captionid="eis.MIDDLE_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired,txt.isname" default="${empInfoDtlsGuj.empMname}" mandatory="true"/>
			</td>
			
		</tr>
		<tr>
			<td class="fieldLabel">
				<b><hdiits:caption captionid="CLASS" bundle="${empEditListCommonLables}"/></b>
			</td>
			<td align="left">				
					<hdiits:select name="selClass" id="selClass" captionid="CLASS" bundle="${empEditListCommonLables}" mandatory="true" onchange="retdate()" sort="false" validation="sel.isrequired">
						<option value="0"><fmt:message key="SELECT" bundle="${empEditListCommonLables}"/></option>
						<c:forEach var="classes" items="${classDtls}">
							<option value="<c:out value="${classes.gradeCode}"/>">
							<c:out value="${classes.gradeName}" /></option>
						</c:forEach>
					</hdiits:select>				
			</td>
			<td align="left"><b><hdiits:caption captionid="DSGN" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td align="left"><hdiits:select captionid="DSGN" bundle="${empEditListCommonLables}" name="selDesignation" id="selDesignation" sort="false"
						validation="sel.isrequired" mandatory="true">
					<option value="0">
						<fmt:message key="SELECT" bundle="${empEditListCommonLables}" />
					</option>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td align="left"><b><hdiits:caption captionid="PSCALE" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td align="left"><hdiits:select captionid="PSCALE" bundle="${empEditListCommonLables}" name="selPayScale" id="selPayScale" sort="false" onchange="populateBasicPay()">
					<option value="0">
						<fmt:message key="SELECT" bundle="${empEditListCommonLables}" />
					</option>
				</hdiits:select>
			</td>
			<td align="left"><b><hdiits:caption captionid="BASICPAY" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:text name="basicPay" id="basicPay" captionid="BASICPAY" bundle="${empEditListCommonLables}" validation="txt.isrequired" onkeypress="return checkNumberOnly(false)" style="text-align: right"/>
			</td>
		</tr>
		
		<tr>
			<td class="fieldLabel">
				<b><hdiits:caption	captionid="eis.DATE_OF_BIRTH" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td align="left">
				<hdiits:dateTime name="DOB" captionid="eis.DATE_OF_BIRTH" bundle="${empEditListCommonLables}" default="${empInfoDtls.empDob}" mandatory="true" validation="txt.isdt,txt.isrequired" afterDateSelect="retdate()"/>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel">
				<b><hdiits:caption	captionid="eis.DATE_OF_JOINING" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td align="left">
				<hdiits:dateTime name="DOJ" captionid="eis.DATE_OF_JOINING" bundle="${empEditListCommonLables}" afterDateSelect="setRetDate()" default="${empInfoDtls.empDoj}" mandatory="true" validation="txt.isdt,txt.isrequired" maxvalue="31/12/2099"/>
			</td>
			<td class="fieldLabel" align="left">
				<b><hdiits:caption	captionid="eis.LEAVE_DATE" bundle="${empEditListCommonLables}"></hdiits:caption></b> 
			</td>
			<td align="left">
				<hdiits:dateTime name="DOR" captionid="eis.LEAVE_DATE" bundle="${empEditListCommonLables}" default="${empInfoDtls.empSrvcExp}" maxvalue="31/12/2099"/>
			</td>
		</tr>
	</table>
	
	<table align="center">	
		<tr>
			<td align="center">
				<c:if test="${empty empInfoDtls}">
					<br></br><hdiits:button name="Submit" type="button" captionid="SUBMIT" bundle="${empEditListCommonLables}" onclick="saveInfo()"></hdiits:button>
				</c:if>
				<c:if test="${not empty empInfoDtls}">
					<br></br><hdiits:button name="Update" type="button" captionid="UPDATE" bundle="${empEditListCommonLables}" onclick="saveInfo()"></hdiits:button>
				</c:if>
			</td>
			<td align="center">
				<br></br><hdiits:button name="reset" type="button" captionid="RESET" bundle="${empEditListCommonLables}" onclick="resetData()"></hdiits:button>
			</td>
			<td align="center">
				<br></br><hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${empEditListCommonLables}" onclick="closeWindow()"></hdiits:button>
			</td>
		</tr>
	</table>

<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
<hdiits:hidden name="hdnUserType" id="hdnUserType"></hdiits:hidden>
<hdiits:hidden name="hdnGPFType" id="hdnGPFType"></hdiits:hidden>
<hdiits:hidden name="hdnDOR" id="hdnDOR"></hdiits:hidden>

<script type="text/javascript">

	var checkTypeUser=document.frmEmpBasicInfo.rdoTypeOfUser;
		checkTypeUser[0].checked='true';
		userType=checkTypeUser[0].value;
	document.getElementById("hdnUserType").value=userType;
	
	var checkTypeGPF=document.frmEmpBasicInfo.rdoTypeOfGPF;
		checkTypeGPF[1].checked='true';
		GPFType=checkTypeGPF[1].value;
	document.getElementById("hdnGPFType").value=GPFType;
		
	var empInfoDtls = '${empInfoDtls}';
	if(empInfoDtls!='' && empInfoDtls!=null && empInfoDtls!='null')
	{
		checkTypeUser[1].disabled=true;
		checkTypeUser[0].disabled=true;
		document.getElementById("hdnUserType").value='H';
		document.getElementById("hdnGPFType").value='Y';
		
		var TypeOfUser = document.getElementById("rdoTypeOfUser").parentNode.parentNode;
		TypeOfUser.style.display = "none";
	}
		
	var classCode='${gradeCode}';
	var dsgnCode = '${dsgnCode}';
	var payScaleValue = '${payScaleValue}';
	var basic_Pay="${basicPay}";
	
	document.frmEmpBasicInfo.DOB.readOnly=true;
	document.frmEmpBasicInfo.DOJ.readOnly=true;
	document.frmEmpBasicInfo.DOR.readOnly=true;
	
	initializetabcontent("maintab");
	var currentDate = '${currentDate}';
	
</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="txt_GPF_No,Salutation,emp_last_name_eng,emp_first_name_eng,emp_last_name_guj,emp_first_name_guj,selClass,DOB,DOJ" />
</div>
</div>
</hdiits:form>	

<ajax:select
	preFunction="fillClassOnLoad"
	baseUrl="hrms.htm?actionFlag=getDropDownAction"
	source="selClass"
	target="selDesignation"
	executeOnLoad="true"
	parameters="ClassCode={selClass},flag=dsgn"
/>

<ajax:select
	preFunction="fillDsgnOnLoad"
	baseUrl="hrms.htm?actionFlag=getDropDownAction"
	source="selDesignation"
	target="selPayScale"
	parameters="ClassCode={selClass},designationCode={selDesignation},flag=pscale"
	postFunction="fillPayScaleOnLoad"
/>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
