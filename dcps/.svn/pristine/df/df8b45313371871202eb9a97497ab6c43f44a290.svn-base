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
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="enLables" scope="request" />
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
<c:set var="hrEssMrbreqDtl" value="${resValue.hrEssMrbreqDtl}" />
<c:set var="hrEssMrbtreatmentDtl" value="${resValue.hrEssMrbtreatmentDtl}" />
<c:set var="hrEssMrbbillDtl" value="${resValue.hrEssMrbbillDtl}" />
<c:set var="hrEssMrbmiscDtl" value="${resValue.hrEssMrbmiscDtl}" />
<c:set var="MRBId" value="${resValue.MRBId}" />
<c:set var="ReqType" value="${resValue.ReqType}" />
<c:set var="eisEmpdpenDtls" value="${resValue.EmpDet}"></c:set>
<c:set var="AgeDependnt" value="${resValue.AgeDependnt}"></c:set>
<c:set var="Relation" value="${resValue.Relation}"></c:set>
<c:set var="DOB" value="${resValue.DOB}"></c:set>
<c:set var="DepenedentName" value="${resValue.DepenedentName}"></c:set>
<script type="text/javascript">

var IndivdualBillAmnt="";
var BillDataComingFromDB=false;
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
						//alert("OldXMl Path---->"+decXML);
						var xmlDOM = getDOMFromXML(decXML);	
						IndivdualBillAmnt=getXPathValueFromDOM(xmlDOM, 'BillAmnt');
						if(getXPathValueFromDOM(xmlDOM, 'BillAmnt')!=0.0)
						{
							BillDataComingFromDB=true; 
						}
						
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
	
	
	document.MRBFRM.billTotal.value=(parseFloat(document.MRBFRM.billTotal.value)-parseFloat(IndivdualBillAmnt)).toFixed(2);
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
var BillCounterWhenMedAdded=0;
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
		for(var i=0;i<SeparPath.length;i++)
		{
			
			xmlHttp1=GetXmlHttpObject();	
			if(SeparPath[i].substring(SeparPath[i].length-2,SeparPath[i].length)!="_D")
			{
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
			}
			xmlHttp1.open("POST",encodeURI(url),false);
			xmlHttp1.send(null);	
		}
		document.getElementById('h4').value=AllMediAmnt;
		document.getElementById('MedicineAmt').value=AllMediAmnt;
		document.MRBFRM.billTotal.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(AllMediAmnt)).toFixed(2);
		if(document.MRBFRM.billTotal.value!=''&&document.MRBFRM.miscTotal.value!='')
		{
			document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2); 
		}
				
		if(AllMediId.length>1)
		{
			AllMediId=AllMediId.substring(1);
		}
		document.getElementById('h5').value=AllMediId;
		document.getElementById('h6').value=XMLPaths;
		document.getElementById('BillPK').value=getXPathValueFromDOM(xmlDOM, 'BillId');
		document.getElementById('MRBID').value=getXPathValueFromDOM(xmlDOM, 'MRBId');
		
		addOrUpdateRecord('addBillRecordFinal', 'addBillDtls',new Array('h1','h2','h3','h4','h5','h6','BillPK','MRBID'));
		BillCounterWhenMedAdded++;
		SendToJs();
		SendAlertToJS();
	}	
}

function SubmitAsNewRequestInExistREQ()
{
	var saveDraftAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.SAVEASDRAFTCONFM"/>';
	var EnterBillAmntAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.EnterBillAmntAlert"/>';
	var ChangeDateALert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeDateALert"/>';
	var ChangeTrtmntDatesAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeTrtmntDatesAlert"/>';
	SubmitAsNewRequest(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}
function saveAsNewDraftInExistREQ()
{
	var saveDraftAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.SAVEASDRAFTCONFM"/>';
	var EnterBillAmntAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.EnterBillAmntAlert"/>';
	var ChangeDateALert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeDateALert"/>';
	var ChangeTrtmntDatesAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeTrtmntDatesAlert"/>';
	saveAsNewDraft(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}
function SubmitWithChangesInExistREQ()
{
	var saveDraftAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.SAVEASDRAFTCONFM"/>';
	var EnterBillAmntAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.EnterBillAmntAlert"/>';
	var ChangeDateALert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeDateALert"/>';
	var ChangeTrtmntDatesAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeTrtmntDatesAlert"/>';
	SubmitWithChanges(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}

function SaveAgainWithSameIdInExReq()
{
	var saveDraftAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.SAVEASDRAFTCONFM"/>';
	var EnterBillAmntAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.EnterBillAmntAlert"/>';
	var ChangeDateALert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeDateALert"/>';
	var ChangeTrtmntDatesAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeTrtmntDatesAlert"/>';
	SaveAgainWithSameId(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}
function SendAlertToJS()
{
	var abc='<fmt:message  bundle="${cmnLables}"  key="MRB.SAVEASDRAFTCONFM"/>';
	
	gettingValuesInJS(abc);
}

function validateAddBillInExsReq()
{
	var BillAlert='<fmt:message  bundle="${cmnLables}"  key="MRB.ChangeDateALert"/>';
	validateAddBill(BillAlert);
	
	var MediName='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_NAME"/>';
	var MediType='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_TYPE"/>';
	var MediCat='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_CAT"/>';
	var MediWeigth='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_WT"/>';
	var MediAmnt='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_AMT"/>';
	var MediDtl='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_DTLS"/>';
	var NoMediRcrd='<fmt:message  bundle="${cmnLables}"  key="MRB.No_Medi"/>';
	SendingMediCapsInJs(MediName,MediType,MediCat,MediWeigth,MediAmnt,MediDtl,NoMediRcrd);
}
function SendToJs()
{
	FindInJs(BillCounterWhenMedAdded);
}

function addBillRecordFinal()
{
	if (xmlHttp.readyState == 4) 
		{ 
			var decXMl=xmlHttp.responseText;
			
			var displayFieldArray = new Array('h1','h2','h3');
			var DisplayArray=new Array('h4');
			
			if(BillDataComingFromDB==true)
			{
				addDataInTableBillSecnd('BillDtlsTab', 'encXMLBillFrmDB', displayFieldArray,DisplayArray,'editRecordBill','deleteBillRecord','',BillDataComingFromDB);
			}
			else
			{
				addDataInTableBillSecnd('BillDtlsTab', 'encXMLBill', displayFieldArray,DisplayArray,'editRecordBill','deleteBillRecord','',BillDataComingFromDB);
			}
		}
		IndivdualBillAmnt="";
		BillDataComingFromDB=false;
}


function SubmitPendingAlert()
{
	alert("You can not Submit Same Request As Previous one is already Pending,Either Raise A new Request or Change Treatment Dates");
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

function PrintPage()
{
	if(confirm("Do You Want To Print That Page?")==true)
	{
		
		window.print();
	}
	else
	{
		return false;
	}
}


</script>

<!--  <link rel="stylesheet"
	href="<c:url value="/themes/hdiits/tabcontent.css"/>" type="text/css" />-->
<hdiits:form name="MRBFRM" validate="true" method="POST" action=""
	encType="multipart/form-data">
	
	
	
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	
<br><br>
<hdiits:fieldGroup titleCaptionId="MRB.patdtl" bundle="${cmnLables}"  collapseOnLoad="true">
	<table width="100%" id="selpatcat">

			<!-- <tr bgcolor="#386CB7">
				<td class=fieldLabel align=middle colspan="4"><font color="#ffffff"><STRONG><U>
					<fmt:message key="MRB.patdtl" bundle="${cmnLables}" /></U></STRONG></FONT></td>
			</tr> -->
			<tr></tr>
			<hdiits:hidden name="hidn" id="hidn" default=""></hdiits:hidden>
				<hdiits:hidden name="srNo" id="srNo"></hdiits:hidden>
				<hdiits:hidden name="dependent"/>
<!--  		<hdiits:hidden name="dob" default="${EmpDetVO.dob}" id="source" />-->		
				<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
			
			<c:if test="${hrEssMrbreqDtl.dependentId==-1 }">
			<tr>	
				<td width="25%"><hdiits:caption captionid="mrb.pat"
					bundle="${cmnLables}" /></td>
				<td width="25%"><b><fmt:message key="mrb.self" bundle="${cmnLables}" /></b></td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>
			</c:if>
			<c:if test="${hrEssMrbreqDtl.dependentId!=-1 }">
			
			<tr>
			<hdiits:hidden name="idmem" default="" id="idmem"></hdiits:hidden>
			<td width="25%"><hdiits:caption captionid="MRB.Name"
				bundle="${cmnLables}" /></td>
			<td width="25%"><b><c:out value="${DepenedentName }"/></b></td>

			<td width="25%"><hdiits:caption captionid="MRB.Relation"
				bundle="${cmnLables}" /></td>
			<td width="25%"><b><c:out value="${Relation }"/></b></td>
		</tr>
		<tr>


			 <td width="25%"><hdiits:caption captionid="MRB.Dob"
				bundle="${cmnLables}" /></td>
			<td width="25%"><b><fmt:formatDate value="${DOB}" pattern="dd/MM/yyyy"/></b></td>

			<td width="25%"><hdiits:caption captionid="MRB.age"
				bundle="${cmnLables}" /></td>
			<td width="25%"><b><c:out value="${AgeDependnt}"/></b></td>
		</tr>
			
			</c:if>
			<table id="depdtl">
			</table>
				<!--<td width="25%"><hdiits:select captionid="mrb.pat"
					bundle="${cmnLables}"
					name="dependent" id="depntid" sort="decending" validation="sel.isrequired"
					onchange="getdepnInfo(this)" mandatory="true">
					<option value="Selectt"><hdiits:caption
						captionid="mrb.select" bundle="${cmnLables}" /></option>
					<option value="Select"><hdiits:caption
						captionid="mrb.self" bundle="${cmnLables}" /></option>
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
			<script>
					alert("${hrEssMrbreqDtl.dependentId}");
					var IdSelf="-1";
					</script>
					
						<hdiits:caption captionid="mrb.self" bundle="${cmnLables}" />
		</TBODY>
	</table>-->
	<table id="depdtl" width="100%" style=display:none>
		<tr>
			<hdiits:hidden name="idmem" default="" id="idmem"></hdiits:hidden>
			<td width="25%"><hdiits:caption captionid="MRB.Name"
				bundle="${cmnLables}" /></td>
			<td width="25%"><span id="name"></span> <hdiits:text name="name" default=""
				readonly="true" id="name" /></td>

			<td width="25%"><hdiits:caption captionid="MRB.Relation"
				bundle="${cmnLables}" /></td>
			<td width="25%"><span id="rel"></span>   <td width="25%"><hdiits:text name="rel" default=""
				readonly="true" id="rel" /></td>
		</tr>
		<tr>


			 <td width="25%"><hdiits:caption captionid="MRB.Dob"
				bundle="${cmnLables}" /></td>
			<td width="25%"><span id="date"></span> <hdiits:text name="date" default=" "
				readonly="true" id="date" /> </td>

			<td width="25%"><hdiits:caption captionid="MRB.age"
				bundle="${cmnLables}" /></td>
			<td width="25%"><span id="age"></span>    <td width="25%"><hdiits:text name="age" default=""
				readonly="true" id="age" /></td>
		</tr>

	</table>
	
	
	</table>
	</hdiits:fieldGroup>
	<br><br>
	<hdiits:fieldGroup titleCaptionId="MRB.DISEASE_DETAILS" bundle="${cmnLables}" collapseOnLoad="true">
	
	<table id="otherid" style="display: none">
		<tr>
			<td width="29%"><hdiits:caption captionid="othrdses"
				bundle="${cmnLables}" /></td>
			<td width="29%"><hdiits:text name="othrdses" id="otherdses"
				mandatory="true" /></td>
		</tr>

	</table>
	
	<br><br>
			
	<table id="DiseaseDtlsTab" style="border-collapse: collapse;display:none" border="1"
		align="center" width="80%" borderColor="BLACK">
		<TBODY>
			<tr>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.DISEASE_TYPE" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.DISEASE_CAT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.REMARKS" bundle="${cmnLables}" /></b></td>
			</tr>
		</TBODY>
	
	</table>
	</hdiits:fieldGroup>
	<BR><br>
	
	<hdiits:fieldGroup titleCaptionId="MRB.TRTMNT_DTLS" bundle="${cmnLables}" collapseOnLoad="true">
	<table width="100%">
		<TBODY>
			
			<tr></tr>
				<tr></tr>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.TRTMNT.STARTDT"
					bundle="${cmnLables}" /></td>
				<td width="25%"><b><fmt:formatDate value="${hrEssMrbtreatmentDtl.trtmntStrtDate}" pattern="dd/MM/yy"/></b>
				<hdiits:hidden name="TrtmntStart" /></td>
				<td width="25%"><hdiits:caption captionid="MRB.TRTMNT.ENDDATE"
					bundle="${cmnLables}" /></td>
				<td width="25%"><b><fmt:formatDate value="${hrEssMrbtreatmentDtl.trtmntEndDate}" pattern="dd/MM/yy"/></b>
				<hdiits:hidden name="TrtmntEnd" /></td>
			</tr>
		</TBODY>
	</table>

			
	<table id="TrtMntDtlsTab" style="border-collapse: collapse;display:none" border="1"
		align="center" width="80%" borderColor="BLACK">
		<TBODY>
			<tr>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.HSPTL_NAME" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.HSPTL_TYPE" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.HSPTL_ADRS" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.TRTMNT_TYPE" bundle="${cmnLables}" /></b></td>
			</tr>
		</TBODY>
	</table>
	</hdiits:fieldGroup>
	<br><br>
	<hdiits:fieldGroup titleCaptionId="MRB.APPROVER_DTLS" bundle="${cmnLables}" collapseOnLoad="true">
	<table width="100%">
		<TBODY>
			
			<tr></tr>	<tr></tr>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.APPROVER_NAME" bundle="${cmnLables}"/> </td>
				<td width="25%"><b><c:out value="${hrEssMrbreqDtl.approverName}"></c:out></b></td>
				<hdiits:hidden name="ApproverName"/>
				
				<td width="25%"><hdiits:caption captionid="MRB.APPROVER_DESIG" bundle="${cmnLables}"/> </td>
				<td width="25%"><b><c:out value="${hrEssMrbreqDtl.approverDesig}"></c:out></b></td>
				<hdiits:hidden name="ApproverDesig"/>
			</tr>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_NAME" bundle="${cmnLables}"/> </td>
				<td width="25%"><b><c:out value="${hrEssMrbreqDtl.hospitalName}"></c:out></b> </td>
				<hdiits:hidden name="ApproverHsptl"/>
				
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_ADRS" bundle="${cmnLables}"/> </td>
				<td width="25%"><b><c:out value="${hrEssMrbreqDtl.hospitalAddress}"></c:out></b></td>
				<hdiits:hidden name="ApprvrHsptlAddress"/>
			</tr>
		</TBODY>
	</table>
	</hdiits:fieldGroup>
	<br><br>
<hdiits:fieldGroup titleCaptionId="MRB.BILL_DTLS" bundle="${cmnLables}" collapseOnLoad="true">
	<table width="100%">
		<TBODY>
			<tr>
				<td colspan="4"></td>
			</tr>
			
		</TBODY>
	</table>
	<br><br>
			
		<hdiits:hidden name="MedXMlPath" id="MedXMlPath"/>
		
	
	<table id="BillDtlsTab" style="border-collapse: collapse;display:none" border="1"
		align="center" width="80%" borderColor="BLACK">
		<TBODY>
			<tr >
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.BILL_NO" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.BILL_DT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.CHEMIST_NAME" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.MED_DTLS" bundle="${cmnLables}" /></b></td>	
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.BILL_AMT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF">
				<table style="border-collapse: collapse" border="1" align="center" width="100%" borderColor="BLACK">
				<tr >
				<th colspan="5"><b><fmt:message key="MRB.SelectMediDtl" bundle="${cmnLables}" /></b></th>
				</tr>
				<tr>
						<td width='20%' align='middle' class='fieldLabel' bgcolor='#C9DFFF'><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_NAME"/></b></td>
						<td width='20%' align='middle' class='fieldLabel' bgcolor='#C9DFFF'><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_TYPE"/></b></td>
						<td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_CAT"/></b></td>
						<td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_WT"/></b></td>
						<td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_AMT"/></b></td>
				</tr>
				</table>
				</td>
			</tr>
		</TBODY>
	</table>
	<br><br>
	<hdiits:hidden name="MedicineAmt" id="MedicineAmt"/>
	<table width="100%" align="center">
		<TBODY>
			<tr>
				<td width="25%"><b><hdiits:caption captionid="MRB.BILL_TOTAL"
					bundle="${cmnLables}" /></b></td>
				<TD width="75%"><b><c:out value="${hrEssMrbbillDtl.billAmt }"></c:out> </b></TD>
			</tr>
		</TBODY>
	</table>
	</hdiits:fieldGroup>
	
	<br><br>
	<hdiits:fieldGroup titleCaptionId="MRB.MISC_DTLS" bundle="${cmnLables}" collapseOnLoad="true">
	
		<br><br>
	
	<table id="MiscDtlsTab" style="border-collapse: collapse;display:none" border="1"
		align="center" width="80%" borderColor="BLACK">
		
			<tr>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.REMARKS" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.MISC_AMT" bundle="${cmnLables}" /></b></td>
				
			</tr>
		
	</table>
	<br>
	<table align="center" width="100%">
		
			<tr>
				<td width="25%"><b><hdiits:caption
					captionid="MRB.MISC_TOTAL" bundle="${cmnLables}" /></b></td>
				<td width="75%"><b><c:out value="${hrEssMrbmiscDtl.amount }"></c:out></b></td>
			</tr>
	</table>
	<br>
	<table id="amt1" align="center" width="100%">
		
			<tr>
				<td width="25%"><u><b><hdiits:caption
					captionid="MRB.TOTADMSBLE_AMT" bundle="${cmnLables}" /></b></u></td>
				<td width="75%"><b><c:out value="${hrEssMrbreqDtl.requestedAmt}"></c:out> </b></td>
			</tr>
			<tr>
				<td colspan="4"><font color="red"><hdiits:caption
					captionid="MRB.MRB.NOTE_1" bundle="${cmnLables}" /></font></td>
			</tr>
	</table>
	</hdiits:fieldGroup>
	<br>
	<hdiits:hidden name="billTotal"/>
	<hdiits:hidden name="miscTotal"/>
	<hdiits:hidden name="totAdmissible"/>
	<table align="center" width="80%" style="display:none">
		
			<tr>
				<td colspan="3"><!-- For attachment : Start--> <jsp:include
					page="//WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="MRBAttachment" />
					<jsp:param name="formName" value="MRBFRM" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="multiple" value="N" />
				</jsp:include> <!-- For attachment : End--></td>
			</tr>
		
	</table>
	<br><br>
	<br><br>
	<table width="80%" align="center">
	<tbody>
		<tr>
			<td align="left"><b><fmt:message key="MRB.APPRVR_SIGN" bundle="${cmnLables}"/></b></td>
			<td align="right"><b><fmt:message key="MRB.APPLIER_SIGN" bundle="${cmnLables}"/></b></td>
		</tr>
	</tbody>
	</table>
	<script>

				document.getElementById('target_uploadMRBAttachment').style.display='none';
				document.getElementById('formTable1MRBAttachment').firstChild.firstChild.style.display='none';
	</script>
	
		
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
				<td>
					<hdiits:button name="PrintFntn" type="button" value="Print"  onclick="PrintPage()"/>
					<hdiits:button name="Close" type="button" value="Close"  captionid="MRB.Close" bundle="${cmnLables}" onclick="history.go(-1);return false;"/></td>
			</tr>
		</table>
	
	<script>
	
	var MediName='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_NAME"/>';
	var MediType='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_TYPE"/>';
	var MediCat='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_CAT"/>';
	var MediWeigth='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_WT"/>';
	var MediAmnt='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_AMT"/>';
	var MediDtl='<fmt:message  bundle="${cmnLables}"  key="MRB.MED_DTLS"/>';
	var NoMediRcrd='<fmt:message  bundle="${cmnLables}"  key="MRB.No_Medi"/>';
	SendingMediCapsInJs(MediName,MediType,MediCat,MediWeigth,MediAmnt,MediDtl,NoMediRcrd);
	
	var MRbId="${hrEssMrbreqDtl.mrbId}";
	document.getElementById('MRBID').value="${hrEssMrbreqDtl.mrbId}";
	document.getElementById('ReqType').value="${ReqType}";
	var MRbTrtind='<fmt:message  bundle="${cmnLables}"  key="mrb.indr"/>';
	var MrbTrtOut='<fmt:message  bundle="${cmnLables}"  key="mrb.otdr"/>';
	var PrinPage="PrintPage";
	LoadAllinfo(MRbId,MRbTrtind,MrbTrtOut,PrinPage);
	
	
	//alert(document.getElementById('MRBID').value);
	</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="dependent,DiseaseType,DiseaseCat,HosName,ApproverName,ApproverDesig,ApproverHsptl,BillNo,BillDate,MiscAmtRemarks,MiscAmt" />
	
	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' /> 
	

</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

