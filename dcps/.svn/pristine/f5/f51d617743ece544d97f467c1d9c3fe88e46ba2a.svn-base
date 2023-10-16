<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%
try
{%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<fmt:setBundle basename="resources.gnl.LaqApplication.LaqApplicationLables" var="Lables" scope="request"/>
<fmt:setBundle basename="resources.gnl.LaqApplication.LaqApplicationAlerts" var="Alerts" scope="request"/>


<script type="text/javascript" src="<c:url value="/script/hrms/gnl/LAQ/LaqBasicInfo.js"/>"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fileId" value="${resultValue.fileId}"></c:set>
<c:set var="laqNo" value="${resultValue.laqNo}"></c:set>
<c:set var="letterDated" value="${resultValue.letterDated}"></c:set>
<c:set var="subSubjectCode" value="${resultValue.subSubjectCode}"></c:set>
<c:set var="subSubjectName" value="${resultValue.subSubjectName}"></c:set>
<c:set var="selectedYear" value="${resultValue.selectedYear}"></c:set>
<c:set var="vidhansabha" value="${resultValue.vidhansabha}"></c:set>
<c:set var="session" value="${resultValue.session}"></c:set>
<c:set var="mainSubjectName" value="${resultValue.mainSubjectName}"></c:set>
<c:set var="queRaisedByMemName" value="${resultValue.queRaisedByMemName}"></c:set>
<c:set var="queRaisedByConstituency" value="${resultValue.queRaisedByConstituency}"></c:set>
<c:set var="toBeRepliedByMemName" value="${resultValue.toBeRepliedByMemName}"></c:set>
<c:set var="toBeRepliedByConstituency" value="${resultValue.toBeRepliedByConstituency}"></c:set>
<c:set var="laqTypeLookupDesc" value="${resultValue.laqTypeLookupDesc}"></c:set>
<c:set var="departmentName" value="${resultValue.departmentName}"></c:set>
<c:set var="nameOfOffice" value="${resultValue.nameOfOffice}"></c:set>
<c:set var="nameOfSubOffice" value="${resultValue.nameOfSubOffice}"></c:set>
<c:set var="region" value="${resultValue.region}"></c:set>
<c:set var="district" value="${resultValue.district}"></c:set>
<c:set var="taluka" value="${resultValue.taluka}"></c:set>
<c:set var="city" value="${resultValue.city}"></c:set>
<c:set var="village" value="${resultValue.village}"></c:set>
<c:set var="questionNo" value="${resultValue.questionNo}"></c:set>
<c:set var="prvRelatedQueNo" value="${resultValue.prvRelatedQueNo}"></c:set>
<c:set var="dueDateOfReply" value="${resultValue.dueDateOfReply}"></c:set>
<c:set var="replyDateInAssbly" value="${resultValue.replyDateInAssbly}"></c:set>
<c:set var="priorityNo" value="${resultValue.priorityNo}"></c:set>
<c:set var="noOfCopies" value="${resultValue.noOfCopies}"></c:set>
<c:set var="laqStatusLookupDesc" value="${resultValue.laqStatusLookupDesc}"></c:set>
<c:set var="revisedDateInAssbly" value="${resultValue.revisedDateInAssbly}"></c:set>
<c:set var="ifDiscussedInAssbly" value="${resultValue.ifDiscussedInAssbly}"></c:set>
<c:set var="replyDate" value="${resultValue.replyDate}"></c:set>
<c:set var="hrGnlLaqreplyDtl" value="${resultValue.hrGnlLaqreplyDtl}"></c:set>
<c:set var="mainQuestion" value="${resultValue.mainQuestion}"></c:set>
<c:set var="mainAnswer" value="${resultValue.mainAnswer}"></c:set>
<c:set var="mainAssurance" value="${resultValue.mainAssurance}"></c:set>
<c:set var="ifSupplementryQueFlag" value="${resultValue.ifSupplementryQueFlag}"></c:set>
<c:set var="hrGnlLaqactionDtlList" value="${resultValue.hrGnlLaqactionDtlList}"></c:set>
<c:set var="hrGnlLaqsubitemDtlList" value="${resultValue.hrGnlLaqsubitemDtlList}"></c:set>
<c:set var="xmlFilePathNameForMulAddForSubIteam" value="${resultValue.xmlFilePathNameForMulAddForSubIteam}"></c:set>


<script language="javascript">
var LAQAlertMsg=new Array();
LAQAlertMsg[0]='<fmt:message  bundle="${Lables}" key="SelectNameOfHodFirst"/>';
LAQAlertMsg[1]='<fmt:message  bundle="${Lables}" key="SelectNameOfOfficeFirst"/>';

</script>

<hdiits:form name="LAQForm" validate="true" method="post" encType="multipart/form-data"  action="javascript:submitSavedLaqRequest()"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="Leg_Ass_Ans__Que_Det" bundle="${Lables}"/></b></a></li>
	<li><a href="#" rel="tcontent2"><b><fmt:message key="Que_And_Ans" bundle="${Lables}"/></b></a></li>
	<li><a href="#" rel="tcontent3"><b><fmt:message key="Status_dtls" bundle="${Lables}"/></b></a></li>
	<li><a href="#" rel="tcontent4"><b><fmt:message key="Action_Taken" bundle="${Lables}"/></b></a></li>
	</ul>
</div>
<div id="tcontent1" class="tabcontent" tabno="0">


<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Leg_Ass_Que_Det" titleCaptionId="Leg_Ass_Que_Det">
<TABLE class=tabtable align="center">  
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Laq_No" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${laqNo}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Letter_dated" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${letterDated}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Subject" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${mainSubjectName}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Sub_Subject" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${subSubjectName}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Department" bundle="${Lables}"/></b></td>
	<td width="25%">: HOME</td>
	
	<td width="25%"><b><hdiits:caption captionid="Year" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${selectedYear}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Vidhan_Sabha" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${vidhansabha}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Session" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${session}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Question_Raised_By" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${queRaisedByMemName}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Constituency" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${queRaisedByConstituency}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="To_Be_Rep_By" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${toBeRepliedByMemName}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Constituency" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${toBeRepliedByConstituency}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="LAQ_Type" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${laqTypeLookupDesc}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Name_of_HOD" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${departmentName}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Name_of_Office" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${nameOfOffice}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Sub_Office" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${nameOfSubOffice}"/></td>
	</tr>
</TABLE>
</hdiits:fieldGroup>


<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Related_to" titleCaptionId="Related_to">
<TABLE class=tabtable align="center">  
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Region" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${region}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="District" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${district}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Taluka" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${taluka}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="City" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${city}"/></td>
	</tr>
	
	<tr>  
	<td width="25%"><b><hdiits:caption captionid="Village" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${village}"/></td>
	
	<td width="25%"></td>
	<td width="25%"></td>
	</tr>
  
</TABLE>
</hdiits:fieldGroup>

</div>

<div id="tcontent2" class="tabcontent" tabno="1">


<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Que_Ans_Detail" titleCaptionId="Que_Ans_Detail">
<TABLE class=tabtable align="center">  
<tr>  
	<td width="25%"><b><hdiits:caption captionid="Que_No" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${questionNo}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Pre_Que_NO" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${prvRelatedQueNo}"/></td>
</tr>
</TABLE>
<br>
<table id="Que_Ans_Dtls" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 

<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Question" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Answer" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Assurance" bundle="${Lables}" /></b></td>
</tr>

<tr align="center">
<td align="center">
<jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="QuestionRichText"/>
<jsp:param name="displayButtons" value="N"/>
<jsp:param name="ifReadOnly" value="Y"/>
</jsp:include>
<script>
		var decoded_que_text = replace("${mainQuestion}",' ','+');  
	 	decoded_que_text=decodeBase64(decoded_que_text);
	 	enableDesignMode('rte'+'QuestionRichText', decoded_que_text, true);	
	 	</script>	
</td>
		
<td align="center">
<jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="AnswerRichText" />
</jsp:include>
		<script>
		var decoded_ans_text = replace("${mainAnswer}",' ','+');  
	 	decoded_ans_text=decodeBase64(decoded_ans_text);
	 	enableDesignMode('rte'+'AnswerRichText', decoded_ans_text, true);		
	 	document.LAQForm.elements['rte'+'AnswerRichText'].value = decoded_ans_text;
		</script>	</td>
<td align="center">
<jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="AssuranceRichText" />
</jsp:include>
		<script>
		var decoded_assurance_text = replace("${mainAssurance}",' ','+');  
	 	decoded_assurance_text=decodeBase64(decoded_assurance_text);
	 	enableDesignMode('rte'+'AssuranceRichText', decoded_assurance_text, false);	
	 	document.LAQForm.elements['rte'+'AssuranceRichText'].value = decoded_assurance_text;	
		</script>	</td>
		
</tr>
</table>

<table border='0' width="100%">
	<tr>
		<td>
			
			<!-- For attachment : Start-->	
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="LAQQueAnsAttachment" />
			<jsp:param name="formName" value="LAQForm" />
			<jsp:param name="attachmentTitle" value="Attachment" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N" />     
			<jsp:param name="rowNumber" value="1" />                 
			</jsp:include>
		<!-- For attachment : End-->
		</td>
	</tr>
</table>
</hdiits:fieldGroup>

<br>
<table width="100%">
<TR bgColor=#386cb7>
    <TD class=fieldLabel align=middle colSpan=5><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    </table>
<TABLE class=tabtable align="center">  
<tr>  
	
</tr>
</TABLE>

<c:if test="${ifSupplementryQueFlag eq 'Y'}">
<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Sub_Item_Detail" titleCaptionId="Sub_Item_Detail">

<table id="Sub_Item_Detail" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 

<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><hdiits:checkbox name="sub_check" value="0"  /></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Sub_Item_Question" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Sub_Item_Answer" bundle="${Lables}" /></b></td>
</tr>
<tr align="center">
<td align="center"><hdiits:checkbox name="sub_check" value="1"  /></td>
<td align="center"><jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="Sub_Item_Question_RichText" />
</jsp:include></td>
<td align="center"><jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="Sub_Item_Answer_RichText" />
</jsp:include></td>
</tr>
</table>

<table border='0' width="100%" id="Laq_Sub_Que_Ans_Attachment_Display">
	<tr>
		<td>
			
			<!-- For attachment : Start-->	
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="LAQSubQueAnsAttachment" />
			<jsp:param name="formName" value="LAQForm" />
			<jsp:param name="attachmentTitle" value="Attachment" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="Y" />
			<jsp:param name="rowNumber" value="1" />                
			</jsp:include>
		<!-- For attachment : End-->
		</td>
	</tr>
</table>
<br>
<br>

<table style="Display:none" align="center" id="AddDeleteButton">
<tr align="center">
<td><hdiits:button name="Add" type="button"  captionid="Add" bundle="${Lables}" onclick="AddUpdateSubItemData('Add',2)"/></td>
</tr>
</table>
<table style="Display:none" align="center" id="UpdateButton">
<tr align="center">
<td><hdiits:button name="Update" type="button"  captionid="Update" bundle="${Lables}" onclick="AddUpdateSubItemData('Update',2)"/></td>
</tr>
</table>
<table style="Display:none" align="center" id="ResetButton">
<tr align="center">
<td><hdiits:button name="Reset" type="button" value="Reset" captionid="Reset" bundle="${Lables}" onclick="ResetSubItemData()"/></td>
</tr>
</table>
<table id="subItemsViewTable" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 
<br></br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Sub_Item_Question" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Sub_Item_Answer" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Action" bundle="${Lables}" /></b></td>
</tr>
</table>

<c:if test="${hrGnlLaqsubitemDtlList ne null}">
<c:forEach var="hrGnlLaqsubitemDtl" items="${hrGnlLaqsubitemDtlList}" varStatus="x">
<c:set var="curXMLFileName" value="${xmlFilePathNameForMulAddForSubIteam[x.index]}"></c:set>
<c:set var="subQuestion" value="Sub Question ${x.index+1}" />
<c:set var="subAnswer" value="Sub Answer ${x.index+1}" />
<c:set var="attachmentId" value="${hrGnlLaqsubitemDtl.cmnAttachmentMst.attachmentId}" />
<script type="text/javascript">					
				var xmlFileName = '${curXMLFileName}';
				var displayFieldA  = new Array('${subQuestion}','${subAnswer}');
				var attachmentId = "";
				if ('${attachmentId}' != 'null' && '${attachmentId}' != 'NULL' && '${attachmentId}' != '0')
				attachmentId = '${attachmentId}';
				addDBDataInTableAttachment('subItemsViewTable','addedEncXML',displayFieldA,xmlFileName,attachmentId,'editAddSubItemsDBDtls', '','');			
</script>
</c:forEach>
</c:if>   
</hdiits:fieldGroup>  
</c:if>
</div>


<div id="tcontent3" class="tabcontent" tabno="2">

<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Status_dtls" titleCaptionId="Status_dtls">
<TABLE class=tabtable align="center">  
<tr>  
	<td width="25%"><b><hdiits:caption captionid="Due_date_of_reply" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${dueDateOfReply}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Reply_date_in_assembly" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${replyDateInAssbly}"/></td>
</tr>

<tr>  
	<td width="25%"><b><hdiits:caption captionid="Priority_no" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${priorityNo}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="No_of_copies" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${noOfCopies}"/></td>
</tr>

<tr>  
	<td width="25%"><b><hdiits:caption captionid="LAQ_Status" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${laqStatusLookupDesc}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Revised_date_in_assembly" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${revisedDateInAssbly}"/></td>
</tr>
</TABLE>
</hdiits:fieldGroup>

</div>

<div id="tcontent4" class="tabcontent" tabno="3">

<TABLE class=tabtable align="center">  
<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Discussed_In_Assembly" bundle="${Lables}"/></b></TD>
  	<TD><hdiits:radio name="Discussed_In_Assembly" value="Yes" captionid="YES" bundle="${Lables}"/>
    <hdiits:radio name="Discussed_In_Assembly" value="No" default="No" captionid="NO" bundle="${Lables}"/></TD>
    <c:if test="${ifDiscussedInAssbly eq 'Y'}">
	<script>
		document.LAQForm.Discussed_In_Assembly[0].checked = true;
	</script>	
	</c:if>
	<script>
	document.LAQForm.Discussed_In_Assembly[0].disabled = true;
	document.LAQForm.Discussed_In_Assembly[1].disabled = true;
	</script>
</tr>
</TABLE>

<c:if test="${ifDiscussedInAssbly eq 'Y'}">

<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Reply_dtls" titleCaptionId="Reply_dtls">
<TABLE class=tabtable align="center" id="ReplyDtls">  
<tr>  
	<td width="25%"><b><hdiits:caption captionid="Reply_Date" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${replyDate}"/></td>
	
	<td width="25%"><b><hdiits:caption captionid="Reply_given_in_assembly" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${hrGnlLaqreplyDtl.replyGivenInAssbly}"/></td>
</tr>

<tr>  
	<td width="25%"><b><hdiits:caption captionid="Reply_given_by" bundle="${Lables}"/></b></td>
	<td width="25%">: <c:out value="${hrGnlLaqreplyDtl.replyGivenBy}"/></td>
	
	<td width="25%"></td>
	<td width="25%"></td>
</tr>
</TABLE>
</hdiits:fieldGroup>
<!-- 
<br>
<table width="100%" id="ActionTakenLine">
<TR bgColor=#386cb7>
    <TD class=fieldLabel align=middle colSpan=5><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    </table>
<TABLE class=tabtable align="center">  
 <tr>  
	<TD width="25%"><b><hdiits:caption captionid="Action_To_Be_Taken" bundle="${Lables}"/></b></TD>
  	<TD><hdiits:radio name="Action_To_Be_Taken" value="Yes" captionid="YES" bundle="${Lables}"/>
    <hdiits:radio name="Action_To_Be_Taken" value="No" default="No" captionid="NO" bundle="${Lables}"/></TD>
    <script>
    	var ifActionTaken = "${hrGnlLaqreplyDtl.ifActionTaken}";
    	if(ifActionTaken == 'Y') {
		document.LAQForm.Action_To_Be_Taken[0].checked = true;
		}
		document.LAQForm.Action_To_Be_Taken[0].disabled = true;
		document.LAQForm.Action_To_Be_Taken[1].disabled = true;
    </script>
</tr>
</TABLE>     -->
</c:if>


<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Action_To_Be_Taken_By" titleCaptionId="Action_To_Be_Taken_By">
<TABLE class=tabtable align="center" id="ActionDtls">  
<tr>
	<td width="25%"><b><hdiits:caption captionid="Assurance_Action_To_Be_Taken"bundle="${Lables}"/></b></td>
  	<td width="92%"><hdiits:textarea name="Assurance_Action_To_Be_Taken" cols="60"></hdiits:textarea></td>
</tr>
</table>
<TABLE class=tabtable align="center" id="ActionOtherDtls">  
<tr>
<TD width="25%"><b><hdiits:caption captionid="Name_of_HOD" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Action_Name_of_HOD" onchange="getNameOfOfficeFromHodName(this,2)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="nameOfHodList" items="${resultValue.nameOfHodList}">
				<hdiits:option  value="${nameOfHodList.departmentId}">${nameOfHodList.depName}</hdiits:option>
				</c:forEach>
		</hdiits:select></td>
			 
<TD width="25%"><b><hdiits:caption captionid="Office" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Office">      
		<hdiits:option value="0"><fmt:message key="SelectNameOfHodFirst" bundle="${Lables}" /></hdiits:option>
		</hdiits:select></td>	
</tr>

<tr>
<TD width="25%"><b><hdiits:caption captionid="Time_Limit" bundle="${Lables}"/></b></td>
<td width="25%"><hdiits:dateTime name="action_timeLimit" caption="dateTime" captionid="Time_Limit" bundle="${Lables}" maxvalue="31/12/2999"></hdiits:dateTime></td>

<TD width="25%"><b><hdiits:caption captionid="Status" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Status" sort="false">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="LaqStatus" items="${resultValue.LaqStatus}">
		<hdiits:option value="${LaqStatus.lookupName}">${LaqStatus.lookupDesc}</hdiits:option>
	</c:forEach>
		</hdiits:select></td>	
</tr>
</TABLE>


<hdiits:fieldGroup bundle="${Lables}" id="Action_Taken_Report" titleCaptionId="Action_Taken_Report" expandable="false">
<TABLE class=tabtable align="center"  id="ActionTakenReport">
<tr>
<td width="25%"><b><hdiits:caption captionid="Description"bundle="${Lables}"/></b></td>
 <td width="25%"><hdiits:textarea name="Description" cols="30"></hdiits:textarea></td>
 
<TD width="25%"><b><hdiits:caption captionid="Date_Of_Completion" bundle="${Lables}"/></b></td>
<td width="25%"><hdiits:dateTime name="Date_Of_Completion" caption="dateTime" captionid="Date_Of_Completion" bundle="${Lables}" maxvalue="31/12/2999"></hdiits:dateTime></td>

</tr>
</TABLE>
</hdiits:fieldGroup>
<table align="center" id="ActionAddButton">
<br>
<tr align="center">
<td><hdiits:button name="AddAction" type="button"  captionid="Add"  bundle="${Lables}" onclick="AddActionTakenDtls('Add')"/></td>
</tr>
</table>
<table style="Display:none" align="center" id="ActionUpdateButton">
<br>
<tr align="center">
<td><hdiits:button name="UpdateAction" type="button" captionid="Update" bundle="${Lables}" onclick="AddActionTakenDtls('Update')"/></td>
</tr>
</table>



<table id="ActionAddDataTable"  align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 
<br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Assurance_Action_To_Be_Taken" bundle="${Lables}"/></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Name_of_HOD" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Office" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Time_Limit" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Status" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Description" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Date_Of_Completion" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Action" bundle="${Lables}" /></b></td>
</tr>
<c:if test="${hrGnlLaqactionDtlList ne null}">
<c:forEach items="${hrGnlLaqactionDtlList}" var="hrGnlLaqactionDtlList" varStatus="x">
<tr id='${hrGnlLaqactionDtlList.rowId}'>
<td><c:out value="${hrGnlLaqactionDtlList.assuranceAction}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.orgDepartmentMstDepName}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.cmnLocationMstByHodOfficeLocName}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.timeLimit}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.statusLookupName}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.discription}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.dateOfCompletion}"/></td>
<td><hdiits:a href="javascript:deleteSelectedAction('${hrGnlLaqactionDtlList.actionId}','${hrGnlLaqactionDtlList.rowId}')" bundle="${Lables}" captionid="Delete" ></hdiits:a></td>
</tr>
</c:forEach>
</c:if> 
</table>
</hdiits:fieldGroup>



</div>

<hdiits:hidden name="Question" />
<hdiits:hidden name="Answer"/>
<hdiits:hidden name="flag"/>
<hdiits:hidden name="rowId"/>
<hdiits:hidden name="srNo" id="srNo"></hdiits:hidden>
<hdiits:hidden name="defaultValue" id="srNo" default="-"></hdiits:hidden>
<hdiits:hidden name="actionDtlFlag" id="actionDtlFlag"></hdiits:hidden>
<hdiits:hidden name="fileId" id="fileId" default="${fileId}"></hdiits:hidden>
<hdiits:hidden name="actionRowId" id="actionRowId"></hdiits:hidden>
<hdiits:hidden name="hiddenSubIteamId" id="hiddenSubIteamId"></hdiits:hidden>



<hdiits:hidden name="Converted_text" id="Converted_text"/>
<jsp:include page="../../../core/tabnavigation.jsp" />
		<script type="text/javascript">
		initializetabcontent("maintab")
		
		</script>
<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>	

</hdiits:form>


<%}catch(Exception e)
  {
  e.printStackTrace();
  }
%>