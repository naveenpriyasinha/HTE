<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script>
 
function SaveNotice()
{
	if(chkEmpty(document.getElementById('txtSubject'),'Subject') &&
			chkEmpty(document.getElementById('txtIssuedBy'),'Issued By') &&
			chkEmpty(document.getElementById('txtIssuedDate'),'Issued Date') &&
			chkEmpty(document.getElementById('txtExpiryDate'),'Expiry Date') &&
			chkEmpty(document.getElementById('cmbFieldDeptCode'),'Field Dept Code') &&
			chkEmpty(document.getElementById('txtUploadFile'),'Upload File')){
		SaveDataUsingAjax();
	}
	return true;

}
function SaveDataUsingAjax()
{

	//saveOrUpdateFlag=1;
	var xmlHttp=null;
	xmlHttp = GetXmlHttpObject();
	
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	
	var subject = document.getElementById('txtSubject').value;
	var issuedBy = document.getElementById('txtIssuedBy').value;
	var issuedDate = document.getElementById('txtIssuedDate').value;
	var expiryDate = document.getElementById('txtExpiryDate').value;
	for(var i = 0;i<2;i++)
	{
		if(document.frmNoticeBoard.radioNoticeType[i].checked)
		{
			var noticeType = document.frmNoticeBoard.radioNoticeType[i].value;
		}
	}
	var w = document.getElementById('cmbFieldDeptCode').selectedIndex;
	var fieldDeptCode = document.getElementById('cmbFieldDeptCode').options[w].text;
	var fileName = document.getElementById('txtUploadFile').value;
	
	var uploadFile = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
	
	var url = "ifms.htm?actionFlag=saveNotice&subject="+subject+"&issuedBy="+issuedBy+"&issuedDate="+issuedDate+"&expiryDate="+expiryDate+"&noticeType="+noticeType+"&fieldDeptCode="+fieldDeptCode+"&uploadFile="+uploadFile;

    xmlHttp.open("POST",url,true);
	xmlHttp.send(url);

	xmlHttp.onreadystatechange = function()
	{
		if(xmlHttp.readyState == 4)
		{
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XMLEntries  = XMLDoc.getElementsByTagName('XMLDOC');
				var successFlag = XMLEntries[0].childNodes[0].text;
				if(successFlag)
				{
					alert("Record Entered Successfully");
					self.location.reload();
				}
			}
		}
	};
}

</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="NoticeList" value="${resValue.NoticeList}" />

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmNoticeBoard" encType="multipart/form-data"validate="true" method="post">
<fieldset class="tabstyle">
	<display:table list="${NoticeList}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="6">	

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.SRNO">
			<c:out value="${vo_rowNum}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.SUBJECT">
			<c:out value="${vo[0]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.FILENAME">
			<c:out value="${vo[1]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.ISSUEDBY">
			<c:out value="${vo[2]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.ISSUEDDATE">
			<c:out value="${vo[3]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.EXPIRYDATE">
			<c:out value="${vo[4]}"></c:out>
		</display:column>
	</display:table>
</fieldset>
<br/>
<fieldset class="tabstyle"><legend>Notice Board</legend>
<table id="tblNoticeEntry" width="100%" align="center" cellpadding="4" cellspacing="4" border="0" class="blue">
	<tr>
		<td align="left"><fmt:message key="CMN.SUBJECT" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td  align="left" colspan="3"><input type="text" name="txtSubject" id="txtSubject" size="50"/></td>
	</tr>
	<tr>
		<td  align="left"><fmt:message key="CMN.ISSUEDBY" bundle="${DCPSLables}"></fmt:message>
		</td>
		<td align="left" colspan="3"><input type="text"	name="txtIssuedBy" id="txtIssuedBy" size="50" /></td>
	</tr>
	<tr>
		<td  align="left" ><fmt:message key="CMN.ISSUEDDATE" bundle="${DCPSLables}"></fmt:message>
		</td>
		<td  align="left" width="25%">
			<input type="text" name="txtIssuedDate" id="txtIssuedDate" maxlength="15" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" /> 
			<img src='images/CalendarImages/ico-calendar.gif' width='15' onClick='window_open("txtIssuedDate",375,570)' style="cursor: pointer;" }/>
		</td>
		<td  align="left"><fmt:message key="CMN.EXPIRYDATE" bundle="${DCPSLables}"></fmt:message>
		</td>
		<td  align="left"> 
			<input type="text" name="txtExpiryDate" id="txtExpiryDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" /> 
			<img src='images/CalendarImages/ico-calendar.gif' width='15' onClick='window_open("txtExpiryDate",375,570)' style="cursor: pointer;" }/>
		</td>
	</tr>
	<tr>
		<td  align="left"><fmt:message key="CMN.NOTICETYPE" bundle="${DCPSLables}"></fmt:message>
		</td>
		<td align="left">
		      <input type="radio"id="radioNoticeType" name="radioNoticeType" value="General/Public" checked="checked"/>
		      <fmt:message key="CMN.GENERALPUBLIC" bundle="${DCPSLables}"></fmt:message>
		      &nbsp;&nbsp;&nbsp;&nbsp;
			  <input type="radio"id="radioNoticeType" name="radioNoticeType" value="Department" />	
		      <fmt:message key="CMN.DEPARTMENT" bundle="${DCPSLables}"></fmt:message>
		</td>
	</tr>
	<tr>
		<td  align="left"><fmt:message key="CMN.FIELDDEPTCODE" bundle="${DCPSLables}"></fmt:message>
		</td>
		<td  align="left">
			<select name="cmbFieldDeptCode" id="cmbFieldDeptCode" style="width: 60%" onfocus="onFocus(this)" onblur="onBlur(this);">
				<option value="-1">---Select---</option>
				<option value="1">Dept1</option>
				<option value="2">Dept2</option>
				<option value="3">Dept3</option>
			</select>
		</td>	
	</tr>
	<tr>
		<td  align="left"><fmt:message key="CMN.UPLOADFILE" bundle="${DCPSLables}"></fmt:message>

		</td>
		<td  align="left">
			<input type="file" name="txtUploadFile" id="txtUploadFile" style="text-align: left" size="50" /> 
		</td>
			
	</tr>
</table>
</fieldset><br/>
<table border="0" align = "center" width="30%">
	<tr>
		<td  align="center">
			<hdiits:button type="button" caption="SAVE" id="btnSave" name="btnSave" style="width:80px" onclick="SaveNotice();"/>
		</td>
	</tr>
</table>

</hdiits:form>