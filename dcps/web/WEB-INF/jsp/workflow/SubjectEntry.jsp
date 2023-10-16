<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="LocationList" value="${resValue.cmnLocationMstList}"></c:set>
<c:set var="Message" value="${resValue.Message}"></c:set>

<script language="javascript">
if(('${Message}'!='')&&('${Message}'!=null))
{
	alert('${Message}');
}
</script>
<hdiits:form name="subjectForm" validate="true" method="POST"
	action="./hdiits.htm" encType="multipart/form-data">

	<br />
	<br />


	<table class="tabtable">

		<tr>
			<td class="datatableheader" colspan="4">Add Subject Details</td>

		</tr>
		<tr>
			<td class="fieldLabel">Subject Name(English):</td>
			<td class="fieldLabel"><hdiits:text name="subNameEng"
				id="subNameEng" mandatory="true" /></td>

			<td class="fieldLabel">Subject Name(Gujarati):</td>
			<td class="fieldLabel"><hdiits:text name="subNameGuj"
				id="subNameGuj" mandatory="true" /></td>
		</tr>
		<tr>
			<td class="fieldLabel">Subject Description(English):</td>
			<td class="fieldLabel"><hdiits:text name="subDescEng"
				id="subDescEng" mandatory="true" /></td>

			<td class="fieldLabel">Subject Description(Gujarati):</td>
			<td class="fieldLabel"><hdiits:text name="subDescGuj"
				id="subDescGuj" mandatory="true" /></td>
		</tr>
		<tr>
			<td class="fieldLabel">Select Location:</td>
			<td class="fieldLabel" colspan="3"><hdiits:select id="loc"
				name="loc">
				<hdiits:option value="Select"> Select </hdiits:option>
				<c:forEach items="${LocationList}" var="LocationList">
					<hdiits:option value="${LocationList.locationCode}"> ${LocationList.locName}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		<tr style="visibility: hidden">
			<td class="fieldLabel">Select Priority:</td>
			<td class="fieldLabel"><select id="prior" name="prior">
				<option value="Select">Select</option>

				<option value="1">Immediate</option>
				<option value="2">Urgent</option>
				<option value="3">Today</option>
				<option value="4">Dateset</option>
				<option value="5" selected="selected">Routine</option>
			</select></td>

			<td class="fieldLabel">Select Workflow Type:</td>
			<td class="fieldLabel"><select id="wfTyp" name="wfTyp">
				<option value="Select">Select</option>
				<option value="0" selected="selected">Ordinary</option>
				<option value="1">Alternate</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="4" style="text-align: center"><hdiits:button
				name="submit1" type="button" value="Submit"
				onclick="submitSubject()" /></td>
		</tr>



	</table>

	<hdiits:hidden id="hieTyp" name="hieTyp" default="1" />
	<hdiits:hidden id="subTyp" name="subTyp" default="4" />
	<hdiits:hidden id="restrict" name="restrict" default="0" />
	<hdiits:hidden id="tricode" name="tricode" default="000" />
	<hdiits:hidden id="selfInit" name="selfInit" default="0" />
	<hdiits:hidden name="Wfrunenvironment" id="Wfrunenvironment"
		default="${resValue.Wfrunenvironment}" />
	<hdiits:hidden name="loc1" id="loc1"
		default="${resValue.WfModuleList[0].moduleId}" />
	<br />





</hdiits:form>
<script type="text/javascript">
	
		
	function submitSubject()
	{	
		var Wfrunenvironment='${Wfrunenvironment}';
		if(document.getElementById('subNameEng').value=='')
		{
			alert('Please enter Subject name');
			document.getElementById('subNameEng').focus();
		}
		else if(document.getElementById('subNameGuj').value=='')
		{
			alert('Please enter Subject name');
			document.getElementById('subNameGuj').focus();
		}

		else if(document.getElementById('subDescEng').value=='')
		{
			alert('Please enter Subject description');
			document.getElementById('subDescEng').focus();
		}
		else if(document.getElementById('subDescGuj').value=='')
		{
			alert('Please enter Subject description');
			document.getElementById('subDescGuj').focus();
		}
		else
		{
			var url = 'hdiits.htm?actionFlag=enterDocumentDetails&Wfrunenvironment='+Wfrunenvironment; 
			document.forms[0].action=url;
			document.forms[0].submit();
		}
	}
</script>

