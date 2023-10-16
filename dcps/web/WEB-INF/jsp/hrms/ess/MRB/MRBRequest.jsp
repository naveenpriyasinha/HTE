<!-- Atul 220680 ASE -->
<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}">
</c:set>

	<fmt:setBundle basename="resources.ess.MRB.MRBLables" var="cmnLables" scope="request" />


<%@page session="true"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/hrms/ess/mrb/MRB.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/hrms/ess/mrb/MrbAddRcrd.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript"  
         src="common/script/commonfunctions.js">
</script>

<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="hsptlsList" value="${resValue.AllHsptls}"></c:set>
<c:set var="diseaseList" value="${resValue.AllDisease}"></c:set>
<c:set var="EmpDetVO" value="${resValue.EmpDet}" />
<c:set var="dependentlist" value="${resValue.dependentlist}" />
<c:set var="depdtll" value="${resValue.depdtll}" />

<script type="text/javascript">
var BillCounterWhenMedAdded=0;
function SearchMedicine(RowId,XmlFilePath)
	{
		
		xmlHttp=GetXmlHttpObject();		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + XmlFilePath;	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{
				if (xmlHttp.readyState == 4) 
					{ 				  		
						var decXML = xmlHttp.responseText;
						
						var xmlDOM = getDOMFromXML(decXML);	
						var IndivdualBillAmnt=getXPathValueFromDOM(xmlDOM, 'BillAmnt');
						document.MRBFRM.billTotal.value=(parseFloat(document.MRBFRM.billTotal.value)-parseFloat(IndivdualBillAmnt)).toFixed(2);
					}
			}
		}
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);	
		
		
		var href='hdiits.htm?actionFlag=getMediSearchMainPage&RowId='+RowId+'&XmlFilePath='+XmlFilePath;
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	}


function hidedepnInfo()
{

document.getElementById("depdtl").style.display="none";

}
function MediSearch(getMed,RowId,XMlPath)
{
	
	
	var delRow = document.getElementById(RowId);	
	delRow.parentNode.deleteRow(delRow.rowIndex);
	xmlHttp=GetXmlHttpObject();		
	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + XMlPath;	
	xmlHttp.onreadystatechange = function()
	{
		if(xmlHttp.readyState == 4) 
		{
			eval("GetXMlValues('"+getMed+"')");
		}
	}
	xmlHttp.open("POST",encodeURI(url),false);
	xmlHttp.send(null);	

}
function GetXMlValues(XMLPaths)
{
	
	if (xmlHttp.readyState == 4) 
	{ 				  		
		var decXML = xmlHttp.responseText;
		
		var xmlDOM = getDOMFromXML(decXML);	
		document.getElementById('h1').value=getXPathValueFromDOM(xmlDOM, 'BillNo');
		var date =	getXPathValueFromDOM(xmlDOM, 'BillDate');	
		datearr =date.split(' ');
		newdate=datearr[0].split('-');
		var formattedate=newdate[2]+"/"+newdate[1]+"/"+newdate[0];
		
		document.getElementById('h2').value=formattedate;
		document.getElementById('h3').value=getXPathValueFromDOM(xmlDOM, 'ChemistName');
		var SeparPath=XMLPaths.split(",");
		
		var AllMediAmnt=0;
		var AllMediId="";
		var PathTobeEnterInXML="";
		for(var i=0;i<SeparPath.length;i++)
		{
			
			if(SeparPath[i].substring(SeparPath[i].length-2,SeparPath[i].length)!="_D")
			{
				xmlHttp1=GetXmlHttpObject();		
				var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + SeparPath[i];	
				xmlHttp1.onreadystatechange = function()
				{
					if(xmlHttp1.readyState == 4) 
					{
						var decXMLMedi = xmlHttp1.responseText;
						
						var xmlDOMMedi = getDOMFromXML(decXMLMedi);	
						var getAmnt=getXPathValueFromDOM(xmlDOMMedi, 'MedicineAmnt');
						
						AllMediAmnt=(parseFloat(AllMediAmnt)+parseFloat(getAmnt)).toFixed(2);
						
						var getId=getXPathValueFromDOM(xmlDOMMedi, 'MedicineId');
						
						AllMediId=AllMediId+","+getId;
						
					}
				}
				xmlHttp1.open("POST",encodeURI(url),false);
				xmlHttp1.send(null);
				PathTobeEnterInXML=PathTobeEnterInXML+","+SeparPath[i];	
			}
		}
		document.getElementById('h4').value=AllMediAmnt;
		document.getElementById('MedicineAmt').value=AllMediAmnt;
		document.MRBFRM.billTotal.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(AllMediAmnt)).toFixed(2);
		document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
		if(AllMediId.length>1)
		{
			AllMediId=AllMediId.substring(1);
		}
		if(PathTobeEnterInXML.length>1)
		{
			PathTobeEnterInXML=PathTobeEnterInXML.substring(1);
		}
		document.getElementById('h5').value=AllMediId;
		document.getElementById('h6').value=PathTobeEnterInXML;
		addOrUpdateRecord('addBillRecordFinal', 'addBillDtls',new Array('h1','h2','h3','h4','h5','h6'));
		BillCounterWhenMedAdded++;
		SendToJs();
	}	
}

function addBillRecordFinal()
{
	if (xmlHttp.readyState == 4) 
		{ 
			var decXMl=xmlHttp.responseText;
		
			var displayFieldArray = new Array('h1','h2','h3');
			var DisplayArray=new Array('h4');
			addDataInTableBillSecnd('BillDtlsTab', 'encXMLBill', displayFieldArray,DisplayArray,'editRecordBill','deleteBillRecord','');
		}
}

function validateSubmitInNewREQ()
{
	var saveDraftAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.SAVEASDRAFTCONFM"/>';
	var EnterBillAmntAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.EnterBillAmntAlert"/>';
	var ChangeDateALert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeDateALert"/>';
	var ChangeTrtmntDatesAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeTrtmntDatesAlert"/>';
	validateSubmit(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}
function saveAsDraftInNewREQ()
{
	var saveDraftAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.SAVEASDRAFTCONFM"/>';
	var EnterBillAmntAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.EnterBillAmntAlert"/>';
	var ChangeDateALert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeDateALert"/>';
	var ChangeTrtmntDatesAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeTrtmntDatesAlert"/>';
	saveAsDraft(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}
function validateAddBillInExsReq()
{
	
	var BillAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeDateALert"/>';
	var MediName='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_NAME"/>';
	var MediType='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_TYPE"/>';
	var MediCat='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_CAT"/>';
	var MediWeigth='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_WT"/>';
	var MediAmnt='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_AMT"/>';
	var MediDtl='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_DTLS"/>';
	var NoMediRcrd='<fmt:message  bundle="${cmnLables}"  key="MRB.No_Medi"/>';
	
	SendingMediCapsInJs(MediName,MediType,MediCat,MediWeigth,MediAmnt,MediDtl,NoMediRcrd);
	validateAddBill(BillAlert);
	
	
}

var SpChar='<fmt:message bundle="${cmnLables}" key="MRB.SpclChars"/>';

function SendToJs()
{
	FindInJs(BillCounterWhenMedAdded);
}
function CompareStrtEnd(q)
{
	if(q.value!="")
	{
		if(compareDate(document.MRBFRM.TrtmntStart.value,q.value)<=0 )
		{
			alert('<fmt:message  bundle="${cmnLables}"  key="MRB.CompareStrtEndDt"/>');
			document.MRBFRM.TrtmntEnd.value="";
			document.MRBFRM.TrtmntEnd.focus();
		}
	}
}
</script>

<!--  <link rel="stylesheet"
	href="<c:url value="/themes/hdiits/tabcontent.css"/>" type="text/css" />-->
<hdiits:form name="MRBFRM" validate="true" method="POST" action=""
	encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" compact="compact">
		<li class="selected"><a href="#" rel="tcontent1">
		<b><fmt:message key="MRB.DiseaseDtls" bundle="${cmnLables}" /></b></a></li>
		<li class="selected"><a href="#" rel="tcontent2" >
		<b><fmt:message key="MRB.BillDtls" bundle="${cmnLables}" /></b></a></li>
		<li class="selected"><a href="#" rel="tcontent3" >
		<b><fmt:message key="MRB.MISC_DTLS" bundle="${cmnLables}" /></b></a></li>
		<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
	</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	
<br>
<hdiits:fieldGroup titleCaptionId="MRB.patdtl" bundle="${cmnLables}" id="MRB.patdtlId" collapseOnLoad="false">	
	<table width="100%" id="selpatcat">
		<TBODY>
		<!-- 	<tr bgcolor="#386CB7">
				<td class=fieldLabel align=middle colspan="4"><font color="#ffffff"><STRONG><U>
					<hdiits:caption captionid="MRB.patdtl" bundle="${cmnLables}" /></U></STRONG></FONT></td>
			</tr> -->	
			<tr>
				<hdiits:hidden name="hidn" id="hidn" default=""></hdiits:hidden>
				<hdiits:hidden name="srNo" id="srNo"></hdiits:hidden>
				<hdiits:hidden name="dob" default="${EmpDetVO.dob}" id="source" />
				<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
				<td width="25%"><hdiits:caption captionid="mrb.pat"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:select captionid="mrb.pat"
					bundle="${cmnLables}"
					name="dependent" id="depntid" sort="decending" validation="sel.isrequired"
					onchange="getdepnInfo(this)" mandatory="true">
					<option value="Selectt">
						<fmt:message key="mrb.select" bundle="${cmnLables}" />
					</option>
					<option value="Select">
						<fmt:message key="mrb.self" bundle="${cmnLables}" />
					</option>
					<c:forEach var="resValue12" items="${dependentlist}">
						<option value="<c:out value="${resValue12.memberId}" />"><c:out
							value="${resValue12.fmFirstName}"></c:out></option>

					</c:forEach>
					<hdiits:hidden name="memid" default="${resValue12.memberId}"
						id="memid"></hdiits:hidden>
				</hdiits:select></td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>

		</TBODY>
	</table>
	<table id="depdtl" width="100%" >
		<tr>
			<hdiits:hidden name="idmem" default="" id="idmem"></hdiits:hidden>
			<td width="25%"><hdiits:caption captionid="MRB.Name"
				bundle="${cmnLables}" /></td>
			<td width="25%"><span id="name"></span> <!--<hdiits:text name="name" default=""
				readonly="true" id="name" />--></td>

			<td width="25%"><hdiits:caption captionid="MRB.Relation"
				bundle="${cmnLables}" /></td>
			<td width="25%"><span id="rel"></span> <!--  <td width="25%"><hdiits:text name="rel" default=""
				readonly="true" id="rel" /></td>-->
		</tr>
		<tr>


			 <td width="25%"><hdiits:caption captionid="MRB.Dob"
				bundle="${cmnLables}" /></td>
			<td width="25%"><span id="date"></span> <!--<hdiits:text name="date" default=" "
				readonly="true" id="date" />--> </td>

			<td width="25%"><hdiits:caption captionid="MRB.age"
				bundle="${cmnLables}" /></td>
			<td width="25%"><span id="age"></span> <!--   <td width="25%"><hdiits:text name="age" default=""
				readonly="true" id="age" /></td>-->
		</tr>

	</table>
	</hdiits:fieldGroup>
	<script> hidedepnInfo();</script>
	
	<br><br>
	<hdiits:fieldGroup titleCaptionId="MRB.DISEASE_DETAILS" bundle="${cmnLables}" id="MRB.DISEASE_DETAILSId" collapseOnLoad="false">	
	<table width="100%">
		<TBODY>
			<tr>
				<td colspan="4"></td>
			</tr>
		<!-- 	<tr bgcolor="#386CB7">
				<td colspan="4" class=fieldLabel align=middle><font color="#ffffff">
				<strong><U><hdiits:caption captionid="MRB.DISEASE_DETAILS" bundle="${cmnLables}" /> </U></strong></font></td>
			</tr> -->
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.DISEASE_TYPE"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:select captionid="MRB.DISEASE_TYPE" bundle="${cmnLables}" 
					name="DiseaseType" id="dstpe" mandatory="true"
					validation="sel.isrequired" sort="true" onchange="getInfo(this)">
					<option value="Select">
						<fmt:message key="mrb.select" bundle="${cmnLables}" />
</option>
					<c:forEach var="resValue12" items="${diseaseList}">
						<option value="<c:out value="${resValue12.diseaseId}" />"><c:out
							value="${resValue12.diseaseDesc}"></c:out></option>
					</c:forEach>
				</hdiits:select></td>
				<td width="25%"><hdiits:caption captionid="MRB.DISEASE_CAT"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:select name="DiseaseCat" id="disCat" captionid="MRB.DISEASE_CAT"
					bundle="${cmnLables}" mandatory="true" validation="sel.isrequired">
					<hdiits:option value="Select">
							<fmt:message key="mrb.select" bundle="${cmnLables}" />

					</hdiits:option>
				</hdiits:select></td>
			</tr>
		</TBODY>
	</table>
	<table id="otherid" style="display: none">
		<tr>
			<td width="29%"><hdiits:caption captionid="othrdses"
				bundle="${cmnLables}" /></td>
			<td width="29%"><hdiits:text name="othrdses" id="otherdses"
				mandatory="true" /></td>
		</tr>

	</table>
	<br>
	<table width="100%">
		<tbody>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.REMARKS"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:textarea name="DiseaseRemarks"
					id="textarea" cols="36" rows="7" maxlength="2000"></hdiits:textarea></td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>
		</TBODY>
	</table>

	<br>
	<center>			<hdiits:button name="AddDesease" type="button" value="Add" captionid="MRB.Add" bundle="${cmnLables}"
					onclick="validateAddDisease()" />
				<hdiits:button type="button" name="Savee" value="Save" captionid="MRB.Update" bundle="${cmnLables}"
					readonly="true" onclick="validateAddDisease()"></hdiits:button>
	</center>
			
	<table id="DiseaseDtlsTab" style="border-collapse: collapse;display:none" border="1"
		align="center" width="75%" borderColor="BLACK">
		<TBODY>
			<tr>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.DISEASE_TYPE" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.DISEASE_CAT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.REMARKS" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="mrb.action" bundle="${cmnLables}" /></b></td>
			</tr>
		</TBODY>
	</table>
	</hdiits:fieldGroup>
	<BR>
		<hdiits:fieldGroup titleCaptionId="MRB.TRTMNT_DTLS" bundle="${cmnLables}" id="MRB.TRTMNT_DTLSId" collapseOnLoad="false">	
	<table width="100%">
		<TBODY>
			
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_NAME"
					bundle="${cmnLables}" /></td>
				<td width="25%"><c:forEach var="resValue12"
					items="${hsptlsList}">
					<hdiits:hidden name="HosType${resValue12.hosptlId}"
						default="${resValue12.hosptlType}" />
					<hdiits:hidden name="HosAdress${resValue12.hosptlId}"
						default="${resValue12.hosptlAddr}" />
				</c:forEach> <hdiits:select captionid="MRB.HSPTL_NAME"
					bundle="${cmnLables}" name="HosName"
					id="hosnam" mandatory="true" validation="sel.isrequired"
					sort="true" onchange="getHsptlDtls()">
					<option value="Select">
						<fmt:message key="mrb.select" bundle="${cmnLables}" /></option>
					<c:set var="cntr" value="0" />
					<c:forEach var="resValue12" items="${hsptlsList}">
						<option value="<c:out value="${resValue12.hosptlId}"/>">
						<c:out value="${resValue12.hosptlName}" /></option>
					</c:forEach>
				</hdiits:select></td>
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_TYPE"
					bundle="${cmnLables}" /></td>

				<td width="25%"><hdiits:text name="HosType" default=""
					readonly="true" id="HosType"
					style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: aliceblue;" /></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_ADRS"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:textarea name="HosAdress"
					readonly="true" id="HosAdress"
					style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: aliceblue;"></hdiits:textarea></td>
				<td width="25%"><hdiits:caption captionid="MRB.TRTMNT_TYPE"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:select name="TrtMntType">
					<hdiits:option value="Select">
						<fmt:message key="mrb.select" bundle="${cmnLables}" /></hdiits:option>
					<hdiits:option value="Indoor">
						<fmt:message key="mrb.indr" bundle="${cmnLables}" />
					
					</hdiits:option>
					<hdiits:option value="OutDoor">
						<fmt:message key="mrb.otdr" bundle="${cmnLables}" />
						
					</hdiits:option>
				</hdiits:select></td>
			</tr>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.TRTMNT.STARTDT"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:dateTime name="TrtmntStart" captionid="MRB.TRTMNT.STARTDT" bundle="${cmnLables}" validation="txt.isrequired" mandatory="true"/></td>
				<td width="25%"><hdiits:caption captionid="MRB.TRTMNT.ENDDATE"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:dateTime name="TrtmntEnd" captionid="MRB.TRTMNT.ENDDATE" bundle="${cmnLables}" validation="txt.isrequired" mandatory="true" onblur="CompareStrtEnd(this)"/></td>
			</tr>
		</TBODY>
	</table>
	<br>
			<center>	<hdiits:button name="Addhsptl" type="button" value="Add" captionid="MRB.Add" bundle="${cmnLables}"
					onclick="validateAddTreatment()" />
				<hdiits:button type="button" name="Savehsptl" value="Save" captionid="MRB.Update" bundle="${cmnLables}"
					readonly="true" onclick="validateAddTreatment()"></hdiits:button>
			</center>
			
	<table id="TrtMntDtlsTab" style="border-collapse: collapse;display:none" border="1"
		align="center" width="75%" borderColor="BLACK">
		<TBODY>
			<tr>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.HSPTL_NAME" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.HSPTL_TYPE" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.HSPTL_ADRS" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.TRTMNT_TYPE" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="mrb.action" bundle="${cmnLables}" /></b></td>
			</tr>
		</TBODY>
	</table>
	</hdiits:fieldGroup>
	<br>
			<hdiits:fieldGroup titleCaptionId="MRB.APPROVER_DTLS" bundle="${cmnLables}" id="MRB.APPROVER_DTLSId" collapseOnLoad="false">	
	<table width="100%">
		<TBODY>
			
			<br>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.APPROVER_NAME" bundle="${cmnLables}"/> </td>
				<td width="25%"><hdiits:text name="ApproverName" id="ApproverName"  mandatory="true" validation="txt.isrequired"  captionid="MRB.APPROVER_NAME" bundle="${cmnLables}" /> </td>
				
				<td width="25%"><hdiits:caption captionid="MRB.APPROVER_DESIG" bundle="${cmnLables}"/> </td>
				<td width="25%"><hdiits:text name="ApproverDesig" id="ApproverDesig" mandatory="true" validation="txt.isrequired"  captionid="MRB.APPROVER_DESIG" bundle="${cmnLables}"  /> </td>
			</tr>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_NAME" bundle="${cmnLables}"/> </td>
				<td width="25%"><hdiits:text name="ApproverHsptl" id="ApproverHsptl" mandatory="true" validation="txt.isrequired"  captionid="MRB.HSPTL_NAME" bundle="${cmnLables}"  /> </td>
				
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_ADRS" bundle="${cmnLables}"/> </td>
				<td width="25%"><hdiits:text name="ApprvrHsptlAddress" id="ApprvrHsptlAddress"  /> </td>
			</tr>
		</TBODY>
	</table>
	</hdiits:fieldGroup>
	<br>
</div>
<div id="tcontent2" class="tabcontent" tabno="1" >
	<hdiits:fieldGroup titleCaptionId="MRB.BILL_DTLS" bundle="${cmnLables}" id="MRB.BILL_DTLSId" collapseOnLoad="false">	
	<table width="100%">
		<TBODY>
			<tr>
				<td colspan="4"></td>
			</tr>
			<!--  <tr bgcolor="#386CB7">
				<td colspan="4" class=fieldLabel align=middle><font color="#ffffff"> <STRONG><U>
					<hdiits:caption captionid="MRB.BILL_DTLS" bundle="${cmnLables}" /></U></STRONG> </FONT></td>
			</tr>-->
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.BILL_NO"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:text name="BillNo"
					onclick="changebg(this);" mandatory="true" validation="txt.isrequired" maxlength="12" captionid="MRB.BILL_NO"
					bundle="${cmnLables}"/></td>
				<td width="25%"><hdiits:caption captionid="MRB.BILL_DT"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:dateTime name="BillDate"
					captionid="MRB.BILL_DT" bundle="${cmnLables}" validation="txt.isrequired" mandatory="true"
					onblur="changebg(this);" /></td>
			</tr>
			
			<tr>
				<!--  <td width="25%"><hdiits:caption captionid="MRB.BILL_AMT"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:number name="BillAmt" mandatory="true" captionid="MRB.BILL_AMT"
					bundle="${cmnLables}"
					onblur="changebg(this);" validation="txt.isrequired" maxlength="10" /></td>-->
				<td width="25%"><hdiits:caption captionid="MRB.CHEMIST_NAME" bundle="${cmnLables}"/></td>
				<td width="25%"><hdiits:text name="ChemistName" id="ChemistName" mandatory="true" validation="txt.isrequired" captionid="MRB.CHEMIST_NAME" bundle="${cmnLables}"/> </td>
			</tr>
		</TBODY>
	</table>
	<br>
		<center>		
			<hdiits:button name="AddBill" type="button" value="Add" captionid="MRB.Add" bundle="${cmnLables}"
					onclick="validateAddBillInExsReq()" />
			<hdiits:button type="button" name="SaveBill" value="Save" captionid="MRB.Update" bundle="${cmnLables}"
					readonly="true" onclick="validateAddBillInExsReq()"></hdiits:button>
		</center>	
		<hdiits:hidden name="MedXMlPath" id="MedXMlPath"/>
		
	<br>
	<table id="BillDtlsTab" style="border-collapse: collapse;display:none" border="1"
		align="center" width="100%" borderColor="BLACK">
		<TBODY>
			<tr >
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.BILL_NO" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.BILL_DT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.CHEMIST_NAME" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.MED_DTLS" bundle="${cmnLables}" /></b></td>	
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.BILL_AMT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF">
				<table style="border-collapse: collapse" border="1" align="center" width="100%" borderColor="BLACK">
				<tr >
				<th colspan="5"><b><hdiits:caption captionid="MRB.SelectMediDtl" bundle="${cmnLables}" /></b></th>
				</tr>
				<tr>
						<td width='20%' align='middle' class='fieldLabel' bgcolor='#C9DFFF'><b><hdiits:caption bundle="${cmnLables}"  captionid="MRB.MED_NAME"/></b></td>
						<td width='20%' align='middle' class='fieldLabel' bgcolor='#C9DFFF'><b><hdiits:caption  bundle="${cmnLables}"  captionid="MRB.MED_TYPE"/></b></td>
						<td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b><hdiits:caption  bundle="${cmnLables}"  captionid="MRB.MED_CAT"/></b></td>
						<td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b><hdiits:caption  bundle="${cmnLables}"  captionid="MRB.MED_WT"/></b></td>
						<td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b><hdiits:caption  bundle="${cmnLables}"  captionid="MRB.MED_AMT"/></b></td>
				</tr>
				</table>
				</td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="mrb.action" bundle="${cmnLables}" /></b></td>
			</tr>
		</TBODY>
	</table>
	<hdiits:hidden name="MedicineAmt" id="MedicineAmt"/>
<br><br>
	<table width="100%" align="center">
		<TBODY>
			<tr>
				<td width="25%"><b><hdiits:caption captionid="MRB.BILL_TOTAL"
					bundle="${cmnLables}" /></b></td>
				<TD width="75%"><hdiits:text name="billTotal" readonly="true"
					default="0"
					style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: aliceblue;" /></TD>
			</tr>
		</TBODY>
	</table>
	</hdiits:fieldGroup>
</div>
	
<div id="tcontent3" class="tabcontent" tabno="2" >
<hdiits:fieldGroup titleCaptionId="MRB.MISC_DTLS" bundle="${cmnLables}" id="MRB.MISC_DTLSId" collapseOnLoad="false">	
	<table width="100%" align="center">
		
			
		</table>
		<br>
	<table>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.REMARKS"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:textarea name="MiscAmtRemarks"
					cols="36" rows="7" mandatory="true" validation="txt.isrequired" maxlength="2000" captionid="MRB.REMARKS"
					bundle="${cmnLables}" onfocus=""></hdiits:textarea>
				</td>
				<td width="25%"><hdiits:caption captionid="MRB.MISC_AMT"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:number name="MiscAmt" mandatory="true" validation="txt.isrequired" captionid="MRB.MISC_AMT"
					bundle="${cmnLables}" onblur="AddTotalAdmmsbl()"
					maxlength="10" style="text-align: right"/></td>
			</tr>
	</table>
	<br>
	<center>
				<hdiits:button name="AddMisc" type="button" value="Add" captionid="MRB.Add" bundle="${cmnLables}"
					onclick="validateAddMisc()" />
				<hdiits:button type="button" name="SaveMisc" value="Save" captionid="MRB.Update" bundle="${cmnLables}"
					readonly="true" onclick="validateAddMisc()"></hdiits:button>
	</center>
	<br>
	<table id="MiscDtlsTab" style="border-collapse: collapse;display:none" border="1"
		align="center" width="75%" borderColor="BLACK">
		
			<tr>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.REMARKS" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.MISC_AMT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="mrb.action" bundle="${cmnLables}" /></b></td>
			</tr>
		
	</table>
	<br><br>
	<table align="center" width="100%">
		
			<tr>
				<td width="25%"><b><hdiits:caption
					captionid="MRB.MISC_TOTAL" bundle="${cmnLables}" /></b></td>
				<td width="75%"><hdiits:text name="miscTotal" readonly="true"
					default="0"
					style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: aliceblue;"
					id="misctotamt" onclick="changebg(this);" maxlength="12" /></td>
			</tr>
	</table>
	<br><br>
	<table id="amt1" align="center" width="100%">
		
			<tr>
				<td width="25%"><u><b><hdiits:caption
					captionid="MRB.TOTADMSBLE_AMT" bundle="${cmnLables}" /></b></u></td>
				<td width="75%"><hdiits:text name="totAdmissible"
					readonly="true" default="0"
					style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: aliceblue;"
					id="totadminsble" /></td>
			</tr>
			<tr>
				<td colspan="4"><font color="red"><hdiits:caption
					captionid="MRB.MRB.NOTE_1" bundle="${cmnLables}" /></font></td>
			</tr>
	</table>
	<br><br>
	<table align="center" width="100%">
		
			<tr>
				<td colspan="4"><!-- For attachment : Start--> <jsp:include
					page="//WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="MRBAttachment" />
					<jsp:param name="formName" value="MRBFRM" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="multiple" value="N" />
					<jsp:param name="removeAttachmentFromDB" value="Y" />
				</jsp:include> <!-- For attachment : End--></td>
			</tr>
		
	</table>
	</hdiits:fieldGroup>
	
</div>
	
	
	
	
	</div>		
	<hdiits:hidden name="h1" id="h1"/>
	<hdiits:hidden name="h2" id="h2"/>
	<hdiits:hidden name="h3" id="h3"/>
	<hdiits:hidden name="h4" id="h4"/>
	<hdiits:hidden name="h5" id="h5"/>
	<hdiits:hidden name="h6" id="h6"/>
	<hdiits:hidden name="MRBID" id="MRBID"/>
	<hdiits:hidden name="DiseasePK" id="DiseasePK"/>
	<hdiits:hidden name="TrtmntPK" id="TrtmntPK"/>
	<hdiits:hidden name="BillPK" id="BillPK"/>
	<hdiits:hidden name="MiscPK" id="MiscPK"/>
	<hdiits:hidden name="TrtmntStrdt" id="TrtmntStrdt"/>
	<hdiits:hidden name="TrtmntEnddt" id="TrtmntEnddt"/>
	<hdiits:hidden name="ReqType" id="ReqType"/>
	<table align="center" width="100%">
	<tr align="center">
	<td><hdiits:button 	type="button" name="Submit" id="Submit" value="Submit" 	captionid="MRB.Submit" bundle="${cmnLables}" onclick="validateSubmitInNewREQ()"></hdiits:button>
	<hdiits:button type="button" name="SaveDraft" id="SaveDraft" value="Save as Draft" onclick="saveAsDraftInNewREQ()" captionid="MRB.SaveAsDraft" bundle="${cmnLables}"/>
	<hdiits:button name="Close" type="button" value="Close"  captionid="MRB.Close" bundle="${cmnLables}" onclick="history.go(-1);return false;"/></td>
	</tr>
	</table>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="dependent,DiseaseType,DiseaseCat,HosName,TrtmntStart,TrtmntEnd,ApproverName,ApproverDesig,ApproverHsptl,BillNo,BillDate,ChemistName,MiscAmtRemarks,MiscAmt" />
	
	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' /> 
	<script type="text/javascript">
	initializetabcontent("maintab");
	</script> 

</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

