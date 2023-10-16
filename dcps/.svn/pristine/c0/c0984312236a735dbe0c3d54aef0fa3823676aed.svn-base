<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.eis.empPreEmplLables" var="ExamntnDtlsLables" scope="request" />
<script type="text/javascript" src="script/hrms/eis/eprofile.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EmpServiceExaminationDtls.js"/>"></script>
	
<script>
	var displayFieldArrayInTable = new Array('drodnQualifngExmCmb1','drodnPreServcCmb1','drodnMonthOfPassCmb1','drodnYearFPassCmb1','txtMarksObtCmb1','txtMarksOutOfCmb1','drodnClasDivnCmb1','drodnResultCmb1');
	var empServiceExamAlertMsgArr = new Array();
	empServiceExamAlertMsgArr[0]='<fmt:message bundle="${ExamntnDtlsLables}" key="EIS.MarksAlert"/>';	
</script>	
	
	
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="xmlFileNmExam" value="${resultValue.xmlFileNmExam}" />
<c:set var="ExamVOList" value="${resultValue.ExamVOList}" />
<c:set var="arMonthsInfo" value="${resultValue.arMonthsInfo}"></c:set>
<c:set var="arResultInfo" value="${resultValue.arResultInfo}"></c:set>
<c:set var="arPreSrvcInfo" value="${resultValue.arPreSrvcInfo}"></c:set>
<c:set var="arQualifInfo" value="${resultValue.arQualifInfo}"></c:set>
<c:set var="arClassDivInfo" value="${resultValue.arClassDivInfo}"></c:set>
<c:set var="lArrYrLst" value="${resultValue.lArrYrLst}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="selectedUserId" value="${resultValue.selectedUserId}"></c:set>
<c:set var="strAppType" value="PROF-SERVICE-EXAM"></c:set>

<hdiits:form name="ServiceExaminationDtls" validate="true" method="POST">

<!-- start of employee Service examination Details -->
	<div id="tabmenu">
		<%@ include file="ProfessionalDetailsTab.jsp"%>
	</div>
	<div id="education" name="education">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<%@ include file="empInfo/EmployeeInfo.jsp"%>
<!-- <table width="100%">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="50" align="center">	
		 	<font color="#ffffff">
				<strong><fmt:message key="EIS.ExamnatnDetl" bundle="${ExamntnDtlsLables}"></fmt:message></strong>
			</font></td>
		</tr>
	</table>-->
	
	<hdiits:fieldGroup id="ExamnatnDetlId" titleCaptionId="EIS.ExamnatnDetl" bundle="${ExamntnDtlsLables}">
	<table class="tabtable">
		<tr><td><br></td></tr>
		<tr>
			<td><b><hdiits:caption captionid="EIS.QualifngExm" bundle="${ExamntnDtlsLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="EIS.QualifngExm" bundle="${ExamntnDtlsLables}" name="drodnQualifngExm"  sort="false"
						id="drodnQualifngExm" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${ExamntnDtlsLables}" />
					</hdiits:option>
					<c:forEach var="arQualifInfoVar" items="${arQualifInfo}">
						<hdiits:option value="${arQualifInfoVar.lookupName}">${arQualifInfoVar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			
			<td><b><hdiits:caption captionid="EIS.PreServc" bundle="${ExamntnDtlsLables}"></hdiits:caption></b></td>
		    <td><hdiits:select captionid="EIS.PreServc" bundle="${ExamntnDtlsLables}" name="drodnPreServc"  sort="false"
						id="drodnPreServc" validation="sel.isrequired"  mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${ExamntnDtlsLables}" />
					</hdiits:option>
					<c:forEach var="arPreSrvcInfoVar" items="${arPreSrvcInfo}">
						<hdiits:option value="${arPreSrvcInfoVar.lookupName}">${arPreSrvcInfoVar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td><b><hdiits:caption captionid="EIS.MonthOfPass" bundle="${ExamntnDtlsLables}"></hdiits:caption></b></td>
		    <td><hdiits:select captionid="EIS.MonthOfPass" bundle="${ExamntnDtlsLables}" name="drodnMonthOfPass"  sort="false"
						validation="sel.isrequired" id="drodnMonthOfPass">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${ExamntnDtlsLables}" />
					</hdiits:option>
					<c:forEach var="arMonthsInfoVar" items="${arMonthsInfo}">
						<hdiits:option value="${arMonthsInfoVar.lookupName}">${arMonthsInfoVar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
		
		
			<td><b><hdiits:caption captionid="EIS.YearOfPass" bundle="${ExamntnDtlsLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="EIS.YearOfPass" bundle="${ExamntnDtlsLables}" name="drodnYearFPass" id="drodnYearFPass"  sort="false"
							 validation="sel.isrequired"  mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${ExamntnDtlsLables}" />
					</hdiits:option>
					<c:forEach var="lArrYrLst" items="${lArrYrLst}">
						<hdiits:option value="${lArrYrLst}">${lArrYrLst}</hdiits:option>
					</c:forEach>
				</hdiits:select>
				
			</td>
		</tr>
		<tr>
		    <td><b><hdiits:caption captionid="EIS.MarksObtnd" bundle="${ExamntnDtlsLables}"></hdiits:caption></b></td>
		    <td><hdiits:text captionid="EIS.MarksObtnd" bundle="${ExamntnDtlsLables}" name="txtMarksObt" caption="Marks_Obtnd" onblur="marksComparison()" onkeypress="return checkNumberForOnlyOneDot(this.value);"/></td>
		    
		    <td><b><hdiits:caption captionid="EIS.OutOf" bundle="${ExamntnDtlsLables}"></hdiits:caption></b></td>
		    <td><hdiits:text captionid="EIS.OutOf" bundle="${ExamntnDtlsLables}" name="txtMarksOutOf" caption="Out_Of" onblur="marksComparison()" onkeypress="return checkNumberForOnlyOneDot(this.value);" /></td>
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="EIS.ClasDivn" bundle="${ExamntnDtlsLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="EIS.ClasDivn" bundle="${ExamntnDtlsLables}" name="drodnClasDivn"  sort="false"
						id="drodnClasDivn">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${ExamntnDtlsLables}" />
					</hdiits:option>
					<c:forEach var="arClassDivInfoVar" items="${arClassDivInfo}">
						<hdiits:option value="${arClassDivInfoVar.lookupName}">${arClassDivInfoVar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			
			<td><b><hdiits:caption captionid="EIS.Result" bundle="${ExamntnDtlsLables}"></hdiits:caption></b></td>
		    <td><hdiits:select captionid="EIS.Result" bundle="${ExamntnDtlsLables}" name="drodnResult"  sort="false"
						id="drodnResult" validation="sel.isrequired">
						<c:forEach var="arResultInfoVar" items="${arResultInfo}">
						<hdiits:option value="${arResultInfoVar.lookupName}">${arResultInfoVar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>	
		</tr>
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr>
			<td align="center" colspan="4">
				<hdiits:button name="btnServExamDtlAdd" type="button" captionid="EIS.Add" bundle="${ExamntnDtlsLables}" onclick="javascript:addOrUpdateExamDtls('Add')"/>
				<hdiits:button name="btnServExamDtlUpdate" type="button" captionid="EIS.Update" bundle="${ExamntnDtlsLables}" onclick="javascript:addOrUpdateExamDtls('Update')" style="display:NONE"/>
			    <hdiits:button name="btnServExamDtlReset" type="button" captionid="EIS.Reset" bundle="${ExamntnDtlsLables}" onclick="javascript:addOrUpdateExamDtls('Reset')"/>
				
			</td>
		</tr>
		</table>
		
		<br>
		
		<hdiits:fieldGroup id="currentRequestFieldGroupId" titleCaptionId="EIS.ExamnatnDetl" bundle="${ExamntnDtlsLables}" collapseOnLoad="false">
	    <table id='txnAddEmpServcExamDtl' name="EmpServcExam" border="1" borderColor="black"
		align="center" width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.QualifngExm" bundle="${ExamntnDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.PreServc" bundle="${ExamntnDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.MonthOfPass" bundle="${ExamntnDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.YearOfPass" bundle="${ExamntnDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.MarksObtnd" bundle="${ExamntnDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OutOf" bundle="${ExamntnDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.ClasDivn" bundle="${ExamntnDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.Result" bundle="${ExamntnDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.edtdel" bundle="${ExamntnDtlsLables}" /></label></b></td>
		</tr>
	</table>  
	
	<c:forEach items="${ExamVOList}" var="ExamVO" varStatus="x">
			<c:set var="currentXMLFileE" value="${xmlFileNmExam[x.index]}"></c:set>
			<c:set var="ExamId" value="${ExamVO.cmnLookupMstByExamLookupId.lookupDesc}" />
			<c:set var="PrevSrvc" value="${ExamVO.cmnLookupMstByPreserviceLookupId.lookupDesc}" />
			<c:set var="MonthOfPassing" value="${ExamVO.cmnLookupMstByPassingMonthLookupId.lookupDesc}" />
			<c:set var="YearOfPassing" value="${ExamVO.passingYear}" />
			<c:set var="MarksObtained" value="${ExamVO.marksObtainted}" />
			<c:set var="OutOf" value="${ExamVO.marksOutOf}" />
			<c:set var="ClassDiv" value="${ExamVO.cmnLookupMstByClassDivLookupId.lookupDesc}" />
			<c:set var="Result" value="${ExamVO.cmnLookupMstByResultLookupId.lookupDesc}" />
			<script type="text/javascript">
				var monthPass='${MonthOfPassing}';
				var classDiv='${ClassDiv}';
				
				if(monthPass=='' || monthPass==null)
					{monthPass='-';}
				if(classDiv=='' || classDiv==null)
					{classDiv='-';}
				
				var xmlFileNameE = '${currentXMLFileE}';
				var displayFieldArray1  = new Array('${ExamId}','${PrevSrvc}',monthPass,'${YearOfPassing}','${MarksObtained}','${OutOf}',classDiv,'${Result}');
				addDBDataInTable('txnAddEmpServcExamDtl','encXMLSrvcExam',displayFieldArray1,xmlFileNameE, 'editEmpExamRecord','deleteDBEmpExamRecord');
			</script>
    </c:forEach>		
	</hdiits:fieldGroup>	
	</hdiits:fieldGroup>
	<br><br>
	
	<table align="center">
		<hdiits:button name="btnServExamDtlSubmitInDB" type="button" captionid="EIS.Submit" bundle="${ExamntnDtlsLables}" onclick="submitInDb()"></hdiits:button>
		<hdiits:button name="btnServExamDtlAddCancel" type="button" captionid="EIS.Cancel" bundle="${ExamntnDtlsLables}" onclick="window.close();"></hdiits:button>
	</table>	
	<hdiits:text style="display:none" name="drodnQualifngExmCmb1" id="drodnQualifngExmCmb1" />
	<hdiits:text style="display:none" name="drodnPreServcCmb1" id="drodnPreServcCmb1" />
	<hdiits:text style="display:none" name="drodnMonthOfPassCmb1" id="drodnMonthOfPassCmb1" />
	<hdiits:text style="display:none" name="drodnYearFPassCmb1" id="drodnYearFPassCmb1" />
	<hdiits:text style="display:none" name="txtMarksObtCmb1" id="txtMarksObtCmb1" />
	<hdiits:text style="display:none" name="txtMarksOutOfCmb1" id="txtMarksOutOfCmb1" />
	<hdiits:text style="display:none" name="drodnClasDivnCmb1" id="drodnClasDivnCmb1" />
	<hdiits:text style="display:none" name="drodnResultCmb1" id="drodnResultCmb1" />
		
		
		
	<!-- end of employee service examination Details -->	
	</div>
	</div></div>
	<hdiits:validate locale="${locale}" controlNames="drodnQualifngExm,drodnPreServc,drodnYearFPass"/> 
	
	<script type="text/javascript">
		initializetabcontent("maintab");
	</script>
	
	<hdiits:hidden name="hdnExamId" id="hdnExamId" />
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"/>
	
	</hdiits:form>
 <%
		} 
     catch (Exception e)
     {
	 e.printStackTrace();
	}
%>