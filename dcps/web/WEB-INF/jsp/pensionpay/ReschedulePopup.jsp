<% try{%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/CalendarPopup.js"/>'></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.RESCHEDULAR" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
<hdiits:form name="ReschedulePopup" method="post" validate="" >
	<table align="center">
		<tr>
			<td><fmt:message key="PPMT.PPOID" bundle="${pensionLabels}" /></td>
			<td><input type="text" id="txtPpoId" name="txtPpoId" readOnly />
				<input type="hidden" id="hidPnsrCode" name="hidPnsrCode"  /> </td>
		</tr>
		<tr>
			<td><fmt:message key="PPMT.PENSIONERNAME" bundle="${pensionLabels}" /></td>
			<td><input type="text" id="txtPnsrName" name="txtPnsrName" readOnly /></td>
		</tr>
		<tr>
			<td><fmt:message key="PPMT.CALLDATE" bundle="${pensionLabels}" /></td>
			<td><input type="text" id="txtCallDate" name="txtCallDate" onblur="validateCallDate();" readOnly value='<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.lDtNext}"/>' />
			&nbsp;<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtCallDate",375,570)'style="cursor: pointer;" /></td>
		</tr>
		<tr>
			<td><fmt:message key="PPMT.CALLTIME" bundle="${pensionLabels}" /></td>
			<td>
			<select name="cmbCallTime" id="cmbCallTime" >
					<c:forEach var="slotDtls" items="${resValue.lLstSlotDtls}">
								<c:choose>
										<c:when test="${slotDtls.slotNo == resValue.lIntNextSlot}">
											<option selected="selected" value="<c:out value='${slotDtls.slotNo}'/>"><c:out
												value="${slotDtls.slotTime}" /></option>
										</c:when>
										<c:otherwise>
											<option value="<c:out value='${slotDtls.slotNo}'/>"> <c:out 
												value="${slotDtls.slotTime}" /></option>
										</c:otherwise>
									</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<br/>
<div style="margin-left:170px;">
<hdiits:button name="btnPrint" id="btnPrint" type="button" classcss="bigbutton" captionid="PPMT.PRINT" bundle="${pensionLabels}" onclick="printReschedule()" />
<hdiits:button name="btnSave" id="btnSave" type="button"  classcss="bigbutton" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveReschedule()" />
<hdiits:button name="btnClose" id="btnClose" type="button"  classcss="bigbutton" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="self.close();"/>
</div>
<br/>
</hdiits:form>
</fieldset>
<script type="text/javascript">
var rowNum = '${resValue.lStrRowNum}';
document.getElementById("txtPpoId").value = window.opener.document.getElementById("lblPpoNo"+rowNum).innerHTML;
document.getElementById("txtPnsrName").value = window.opener.document.getElementById("lblPnsrName"+rowNum).innerHTML;
document.getElementById("hidPnsrCode").value = window.opener.document.getElementById("hdnPensionerId"+rowNum).value;
document.getElementById("btnPrint").disabled = "disabled";
</script>
<script type="text/javascript">
function validateCallDate()
{
	validateCallDateUsingAJAX();
/*	var lCallDate = document.getElementById("txtCallDate").value;
	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		return;
	}
	var uri = "ifms.htm?actionFlag=validateCallDate";
	var url = "&callDate="+lCallDate;
	url = uri + url;
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState==complete_state)
		  {
			var XMLDoc = xmlHttp.responseXML.documentElement;
			if (XMLDoc != null) {
				var lArrMessage = XMLDoc.getElementsByTagName('MESSAGE');
				var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
				var lArrSlotTime = XMLDoc.getElementsByTagName('SLOTTIME');
				if(lArrMessage != null && lArrMessage.length > 0)
				{
					alert(lArrMessage[0].text);
					document.getElementById("cmbCallTime").innerHTML = "";
					document.getElementById("txtCallDate").value = "";
					return;
				}
				if(lArrSlotNo != null && lArrSlotNo.length > 0)
				{
					document.getElementById("cmbCallTime").innerHTML = "";
					for(var i=0;i<lArrSlotNo.length;i++)
					{
						var theOption = new Option;
						theOption.value =lArrSlotNo[i].text;;
						theOption.text = lArrSlotTime[i].text;
						document.getElementById("cmbCallTime").options[i] = theOption;
						//document.getElementById("cmbCallTime").options[i].value = 
						//document.getElementById("cmbCallTime").options[i].text = ;
					}
					
				}
		  }
		  }
		
	};
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send(url);*/
}
function validateCallDateUsingAJAX()
{
	var lCallDate = document.getElementById("txtCallDate").value;
	var uri = "ifms.htm?actionFlag=validateCallDate";
	var url = "&callDate="+lCallDate;
//	url = uri + url;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: url,
		        onSuccess: function(myAjax) {
					getStateChangedForValidateCallDate(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getStateChangedForValidateCallDate(myAjax)
{

	var XMLDoc = myAjax.responseXML.documentElement;
	if (XMLDoc != null) {
		var lArrMessage = XMLDoc.getElementsByTagName('MESSAGE');
		var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
		var lArrSlotTime = XMLDoc.getElementsByTagName('SLOTTIME');
		if(lArrMessage != null && lArrMessage.length > 0)
		{
			alert(lArrMessage[0].childNodes[0].nodeValue);
			document.getElementById("cmbCallTime").innerHTML = "";
			document.getElementById("txtCallDate").value = "";
			return;
		}
		if(lArrSlotNo != null && lArrSlotNo.length > 0)
		{
			document.getElementById("cmbCallTime").innerHTML = "";
			for(var i=0;i<lArrSlotNo.length;i++)
			{
				var theOption = new Option;
				theOption.value =lArrSlotNo[i].childNodes[0].nodeValue;;
				theOption.text = lArrSlotTime[i].childNodes[0].nodeValue;
				document.getElementById("cmbCallTime").options[i] = theOption;
				//document.getElementById("cmbCallTime").options[i].value = 
				//document.getElementById("cmbCallTime").options[i].text = ;
			}
			
		}
  }
}
function saveReschedule()
{
	var pnsrCode = document.getElementById("hidPnsrCode").value;
	var callDate = document.getElementById("txtCallDate").value;
	var slotNo = document.getElementById("cmbCallTime").value;
	if(callDate == null || callDate == "")
	{
		alert("Please Select Call Date");
		return false;
	}
	else if(slotNo == null || slotNo == "")
	{
		alert("Please Select Call Time");
		return false;
	}
	else{
		saveRescheduleUsingAjax(pnsrCode,callDate,slotNo);
	}
}

function saveRescheduleUsingAjax(argPnsrCode,argCallDate,argSlotNo)
{
	var url = "pnsrCode="+argPnsrCode+"&callDate="+argCallDate+"&slotNo="+argSlotNo;
	var uri = "ifms.htm?actionFlag=saveReschedule";

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: url,
		        onSuccess: function(myAjax) {
						getDataStateChanedForReschedule(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	/*xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		return;
	}
	var url = "pnsrCode="+argPnsrCode+"&callDate="+argCallDate+"&slotNo="+argSlotNo;
	var uri = "ifms.htm?actionFlag=saveReschedule";
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState==complete_state)
		  {
			var XMLDoc = xmlHttp.responseXML.documentElement;
			if (XMLDoc != null) {
				var lArrCallDate = XMLDoc.getElementsByTagName('CALLDATE');
				var lArrCallTime = XMLDoc.getElementsByTagName('CALLTIME');
				var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
				var lArrSchStatus = XMLDoc.getElementsByTagName('STATUS');
				
				if((lArrCallDate != null && lArrCallDate.length > 0) && (lArrCallTime != null && lArrCallTime.length > 0) && (lArrSlotNo != null && lArrSlotNo.length > 0))
				{
					alert("Identification is reschedule to "+lArrCallDate[0].text+" "+lArrCallTime[0].text+" successfully");
					window.opener.document.getElementById("txtCalledDate"+rowNum).value = lArrCallDate[0].text;
					window.opener.document.getElementById("txtCalledTime"+rowNum).value = lArrCallTime[0].text;
					window.opener.document.getElementById("hidSlotNo"+rowNum).value = lArrSlotNo[0].text;
					window.opener.document.getElementById("txtSchStatus"+rowNum).innerHTML = lArrSchStatus[0].text;
					document.getElementById("btnPrint").disabled = "";
				}
				else{
					alert("Some problem occured during rescheduling.Please try again.");
				}
		  }
	}
	
};
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send(url);*/

}
function getDataStateChanedForReschedule(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	if (XMLDoc != null) {
		var lArrCallDate = XMLDoc.getElementsByTagName('CALLDATE');
		var lArrCallTime = XMLDoc.getElementsByTagName('CALLTIME');
		var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
		var lArrSchStatus = XMLDoc.getElementsByTagName('STATUS');
		
		if((lArrCallDate != null && lArrCallDate.length > 0) && (lArrCallTime != null && lArrCallTime.length > 0) && (lArrSlotNo != null && lArrSlotNo.length > 0))
		{
			alert("Identification is reschedule to "+lArrCallDate[0].childNodes[0].nodeValue+" "+lArrCallTime[0].childNodes[0].nodeValue+" successfully");
			window.opener.document.getElementById("txtCalledDate"+rowNum).value = lArrCallDate[0].childNodes[0].nodeValue;
			window.opener.document.getElementById("txtCalledTime"+rowNum).value = lArrCallTime[0].childNodes[0].nodeValue;
		//	window.opener.document.getElementById("hidSlotNo"+rowNum).value = lArrSlotNo[0].childNodes[0].nodeValue;
			window.opener.document.getElementById("txtSchStatus"+rowNum).innerHTML = lArrSchStatus[0].childNodes[0].nodeValue;
			document.getElementById("btnPrint").disabled = "";
		}
		else{
			alert("Some problem occured during rescheduling.Please try again.");
		}
}
}
function printReschedule()
{
	var pnsrCode = document.getElementById("hidPnsrCode").value;
	showProgressbar();
	var url =  "ifms.htm?actionFlag=genIdentLetter&hdnSelectedPnsrId="+pnsrCode;
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "printIdentLetter", urlstyle);
	hideProgressbar();
}
</script>
<% }catch(Exception e)
{
	e.printStackTrace();
}%>