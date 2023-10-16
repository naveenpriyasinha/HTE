<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EmpChangeName.js"/>"></script>

<fmt:setBundle basename="resources.eis.eisLables" var="empChangeNameCommonLables" scope="request" />

<script type="text/javascript">
	var empChangeNameAlertArray = new Array();
	empChangeNameAlertArray[0]='<fmt:message  bundle="${empChangeNameCommonLables}" key="eis.GazetteDateAlert"/>';
	empChangeNameAlertArray[1]='<fmt:message  bundle="${empChangeNameCommonLables}" key="eis.EffectiveDateAlert"/>';
	empChangeNameAlertArray[2]='<fmt:message  bundle="${empChangeNameCommonLables}" key="eis.empChangeNameAlert"/>';
</script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="empMstEng" value="${resValue.empMstEng}" />
<c:set var="empMstGuj" value="${resValue.empMstGuj}" />
<c:set var="eisEmpChgnameTxnEng" value="${resValue.eisEmpChgnameTxnEng}" />
<c:set var="eisEmpChgnameTxnGuj" value="${resValue.eisEmpChgnameTxnGuj}" />
<c:set var="flag" value="${resValue.flag}" />

<fmt:formatDate pattern="dd/MM/yyyy" value="${eisEmpChgnameTxnEng.gazetteDate}" var="gazetteDateFmt"/>
<fmt:formatDate pattern="dd/MM/yyyy" value="${eisEmpChgnameTxnEng.gazetteEstartDate}" var="gazetteEstartDateFmt"/>

<hdiits:form name="empChangeNamefrm" validate="true" method="POST" encType="multipart/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1">
				<b><fmt:message key="eis.EMP_CHANGE_NAME" bundle="${empChangeNameCommonLables}"></fmt:message></b>
			</a></li>
		</ul>
	</div>


	<div class="tabcontentstyle">
    <div id="tcontent1" class="tabcontent" tabno="0">

<hdiits:fieldGroup titleCaptionId="eis.Emp_Old_Name" bundle="${empChangeNameCommonLables}">
	<table width="100%">
		<tr>
			<td width="22%">
			</td>
			<td width="18%" align="center">
				<b><hdiits:caption captionid="eis.EMP_SALUTATION" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td width="20%" align="center">
				<b><hdiits:caption captionid="eis.LAST_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td width="20%" align="center">
				<b><hdiits:caption captionid="eis.FIRST_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td width="20%" align="center">
				<b><hdiits:caption captionid="eis.MIDDLE_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>									
		</tr>
		<tr>
			<td width="22%">
				<b><hdiits:caption captionid="eis.OLD_NAME_EN" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td width="18%" align="center">
				${empMstEng.empPrefix}
			</td>
			<td width="20%" align="center">
				${empMstEng.empLname}
			</td>
			<td width="20%" align="center">
				${empMstEng.empFname}
			</td>
			<td width="20%" align="center">
				${empMstEng.empMname}
			</td>									
		</tr>
		<tr>
			<td width="22%">
				<b><hdiits:caption captionid="eis.OLD_NAME_GU" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td width="18%" align="center">
				${empMstGuj.empPrefix}
			</td>
			<td width="20%" align="center">
				${empMstGuj.empLname}
			</td>
			<td width="20%" align="center">
				${empMstGuj.empFname}
			</td>
			<td width="20%" align="center">
				${empMstGuj.empMname}
			</td>									
		</tr>
	</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="eis.Emp_New_Name" bundle="${empChangeNameCommonLables}">
	<table width="100%">
		<tr>
			<td>
				<b><hdiits:caption captionid="eis.EMP_SALUTATION" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="SalutationTxt" id="SalutationTxt" maxlength="6" captionid="eis.EMP_SALUTATION" size="3" bundle="${empChangeNameCommonLables}" validation="txt.isrequired" default="${eisEmpChgnameTxnEng.empPrefix}"/>
			</td>
		</tr>

		<tr>
			<td>
				<b><hdiits:caption captionid="eis.EN_LAST_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="empLastNameTxt" id="empLastNameTxt" captionid="eis.EN_LAST_NAME" bundle="${empChangeNameCommonLables}" maxlength="20" validation="txt.isrequired" mandatory="true" default="${eisEmpChgnameTxnEng.empLname}"/>
			</td>
			<td>
				<b><hdiits:caption captionid="eis.EN_FIRST_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
			   <hdiits:text name="empFirstNameTxt" id="empFirstNameTxt" captionid="eis.EN_FIRST_NAME" bundle="${empChangeNameCommonLables}" maxlength="20" validation="txt.isrequired" mandatory="true" default="${eisEmpChgnameTxnEng.empFname}"/>
			</td>
			
			<td>
				<b><hdiits:caption captionid="eis.EN_MIDDLE_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="empMiddleNameTxt" id="empMiddleNameTxt" captionid="eis.EN_MIDDLE_NAME" bundle="${empChangeNameCommonLables}" maxlength="20" default="${eisEmpChgnameTxnEng.empMname}"/>
			</td>
		</tr>
		
		
		<tr>
			<td>
				<b><hdiits:caption captionid="eis.GU_LAST_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="empLastNameGujTxt" id="empLastNameGujTxt" captionid="eis.GU_LAST_NAME" bundle="${empChangeNameCommonLables}" maxlength="20" validation="txt.isrequired" mandatory="true" default="${eisEmpChgnameTxnGuj.empLname}"/>
			</td>
			<td>
				<b><hdiits:caption captionid="eis.GU_FIRST_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
			   <hdiits:text name="empFirstNameGujTxt" id="empFirstNameGujTxt" captionid="eis.GU_FIRST_NAME" bundle="${empChangeNameCommonLables}" maxlength="20" validation="txt.isrequired" mandatory="true" default="${eisEmpChgnameTxnGuj.empFname}"/>
			</td>
			<td>
				<b><hdiits:caption captionid="eis.GU_MIDDLE_NAME" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="empMiddleNameGujTxt" id="empMiddleNameGujTxt" captionid="eis.GU_MIDDLE_NAME" bundle="${empChangeNameCommonLables}" maxlength="20" default="${eisEmpChgnameTxnGuj.empMname}"/>
			</td>
		</tr>
		
		<tr>
			<td>
				<b><hdiits:caption captionid="eis.GAZETTE_NUM" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="GazetteNum" captionid="eis.GAZETTE_NUM" bundle="${empChangeNameCommonLables}" validation="txt.isrequired" mandatory="true" default="${eisEmpChgnameTxnEng.gazetteNo}"/>
			</td>
		</tr>
		<tr>
			<td>
				<b><hdiits:caption captionid="eis.GAZETTE_DATE" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<c:if test="${not flag}">
					<hdiits:dateTime name="gazetteDate" captionid="eis.GAZETTE_DATE" bundle="${empChangeNameCommonLables}" validation="txt.isdt,txt.isrequired" mandatory="true" maxvalue="30/12/2099" afterDateSelect="checkDate('G')"/>
				</c:if>
				<c:if test="${flag eq true}">
					<hdiits:text name="gazetteDateTxt" id="gazetteDateTxt" captionid="eis.GAZETTE_DATE" bundle="${empChangeNameCommonLables}" mandatory="true" default="${gazetteDateFmt}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>
				<b><hdiits:caption captionid="eis.GAZETTE_EFE_START_DATE" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<c:if test="${not flag}">
					<hdiits:dateTime name="gazetteEfeStartDate" captionid="eis.GAZETTE_EFE_START_DATE" bundle="${empChangeNameCommonLables}" validation="txt.isdt,txt.isrequired" mandatory="true" maxvalue="30/12/2099" afterDateSelect="checkDate('E')"/>
				</c:if>
				<c:if test="${flag eq true}">
					<hdiits:text name="gazetteEfeStartDateTxt" id="gazetteEfeStartDateTxt" captionid="eis.GAZETTE_EFE_START_DATE" bundle="${empChangeNameCommonLables}" mandatory="true" default="${gazetteEstartDateFmt}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>
				<b><hdiits:caption captionid="eis.ISSUING_AUTHO" bundle="${empChangeNameCommonLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="issuingAuthoTxt" id="issuingAuthoTxt" captionid="eis.ISSUING_AUTHO" bundle="${empChangeNameCommonLables}" mandatory="true" default="${eisEmpChgnameTxnEng.issuingAutho}" validation="txt.isrequired"/>
			</td>
		</tr>
		
		<tr>
			<td class="fieldLabel" colspan="10">
			<table class="tabtable" align="center" width="100%">
				<tr>
					<td class="fieldLabel">
						<hdiits:fmtMessage key="eis.atchmntTitle" bundle="${empChangeNameCommonLables}" var="AttachmentTitle" />
						<c:if test="${not flag}">
							<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
							<jsp:param name="attachmentName" value="attachmentLink" />
							<jsp:param name="formName" value="empChangeNamefrm" />
							<jsp:param name="attachmentType" value="Document" />
							<jsp:param name="attachmentTitle" value="${AttachmentTitle}" />
							<jsp:param name="multiple" value="N" />
							<jsp:param name="removeAttachmentFromDB" value="Y" />
							<jsp:param name="attachmentSize" value="1" />
							</jsp:include>
						</c:if>
						<c:if test="${flag}">
							<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
							<jsp:param name="attachmentName" value="attachmentLink" />
							<jsp:param name="formName" value="empChangeNamefrm" />
							<jsp:param name="attachmentType" value="Document" />
							<jsp:param name="attachmentTitle" value="${AttachmentTitle}" />
							<jsp:param name="multiple" value="N" />
							<jsp:param name="removeAttachmentFromDB" value="N" />
							<jsp:param name="attachmentSize" value="1" />
							</jsp:include>
						</c:if>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<c:if test="${not flag}">
			<tr>
				<td colspan="10" align="center">
					<hdiits:button name="submitBtn" type="button" captionid="EIS.Submit" bundle="${empChangeNameCommonLables}" onclick="saveEmpChangeNameReq()"></hdiits:button>
					<hdiits:button name="closeBtn" type="button" captionid="EIS.CloseButton" bundle="${empChangeNameCommonLables}" onclick="closeWindow();"></hdiits:button>
				</td>
		   </tr>
	   </c:if>
	</table>
</hdiits:fieldGroup>
	</div>
</div>
<hdiits:validate locale="${locale}" controlNames="empLastNameTxt,empFirstNameTxt,empLastNameGujTxt,empFirstNameGujTxt,GazetteNum,gazetteDate,gazetteEfeStartDate,issuingAuthoTxt" /> 
   
</hdiits:form>

<script type="text/javascript">
	var empChangeNameFlag='${flag}';
	var empOldNameArr = new Array();
	empOldNameArr[0]='${empMstEng.empPrefix}';
	empOldNameArr[1]='${empMstEng.empLname}';
	empOldNameArr[2]='${empMstEng.empFname}';
	empOldNameArr[3]='${empMstEng.empMname}';
	empOldNameArr[4]='${empMstGuj.empPrefix}';
	empOldNameArr[5]='${empMstGuj.empLname}';
	empOldNameArr[6]='${empMstGuj.empFname}';
	empOldNameArr[7]='${empMstGuj.empMname}';
	empChangeOnLoadFun();
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
