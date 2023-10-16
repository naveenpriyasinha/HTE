<% try{%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionConstants" var="pensionConstant" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionCaseLabels" scope="request"/>
<script type="text/javascript">
APPROVESTATUS='<fmt:message key="CHANGSTMNTSTATUS.APPROVED" bundle="${pensionConstant}"></fmt:message>';
REJECTEDSTATUS='<fmt:message key="CHANGSTMNTSTATUS.REJECTED" bundle="${pensionConstant}"></fmt:message>';
PENDINGAPPROVALSTATUS='<fmt:message key="CHANGSTMNTSTATUS.BEFOREAPPROVAL" bundle="${pensionConstant}"></fmt:message>';
</script>
<script type="text/javascript"  src="script/pensionpay/common.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="VOList" value="${resValue.lLstChngStmntDtls}"/>
<c:set var="showWLFor" value="${resValue.showWLFor}" />
<script>
</script>
<script type="text/javascript"  src="script/pensionpay/Common.js"></script>
<hdiits:form name="GeneratedChangeStatement" method="post" validate="">
<jsp:include page="/WEB-INF/jsp/pensionpay/searchChangeStmnt.jsp" />
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
		<c:choose>
		<c:when test="${showWLFor == 'Ato'}">
			<b><fmt:message key="VIEWCHNGSTMT.TITLE" bundle="${pensionLabels}"></fmt:message></b>
		</c:when>
		<c:otherwise>
			<b><fmt:message key="VIEWALLCHANGESTMNTS" bundle="${pensionLabels}"></fmt:message></b>
		</c:otherwise>
		</c:choose>	
	</legend>
<div style="width:100%;overflow:auto;height:400px;" >
	<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >
		<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%">
			<input type="checkbox" align="middle" name="chkbxPesnionerNo"
				id="chkbx_${vo_rowNum}"
				onclick="" value="${vo_rowNum}" />
			<input type="hidden" name="hdnChngRqstId" id="hdnChngRqstId_${vo_rowNum}" value="${vo[0]}"/>
			<input type="hidden" name="hdnBankCode" id="hdnBankCode_${vo_rowNum}" value="${vo[7]}"/>
			<input type="hidden" name="hdnBranchCode" id="hdnBranchCode_${vo_rowNum}" value="${vo[8]}"/>
			<input type="hidden" name="hdnForMonth" id="hdnForMonth_${vo_rowNum}" value="${vo[9]}"/>
		</display:column>
		<display:column titleKey="CHNGSTMNT.BANKNAME" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:left;">
			<label>${vo[1]}</label>
		</display:column>
		<display:column titleKey="CHNGSTMNT.BRANCHNAME" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:left;">
			<label>${vo[2]}</label>
		</display:column>
		<display:column titleKey="CHNGSTMNT.UPTODATE" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:center;">
			<a href="javascript:void(0)" onclick="javascript:viewChangeStatement(${vo[0]});">
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}"/></a>
		</display:column>
		<c:if test="${showWLFor != 'Ato'}">
		<display:column titleKey="CHNGSTMNT.STATUS" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:left;">
			<label id="lblStatus_${vo_rowNum}">${vo[5]}</label>
		</display:column>
		</c:if>
		<display:column titleKey="CHNGSTMNT.FORMONTH" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:left;">
			<label>${vo[6]}</label>
		</display:column>
		<display:column titleKey="CHNGSTMNT.AUDIOR" headerClass="datatableheader"
			 style="width:20%;text-align:left;">
			<label >${vo[3]}</label>
		</display:column>
	</display:table>						 
</div>
</fieldset>
<br/>
<div align="center">
	<c:choose>
	<c:when test="${showWLFor == 'Ato'}">
		<hdiits:button type="button" style=""  name="Approve" captionid="CMN.APPROVE"  bundle="${pensionLabels}" onclick="approveRejectChngStmnt('Approve')"/>
		<hdiits:button type="button" style=""  name="Reject" captionid="CMN.REJECT"  bundle="${pensionLabels}" onclick="approveRejectChngStmnt('Reject')"/>
	</c:when>
	<c:otherwise>
		<hdiits:button type="button" style="width:150px;" name="forward" captionid="PPMT.FORWARDTOATO"  bundle="${pensionCaseLabels}" onclick="forwardChangeStatement()"/>
		<!--<hdiits:button type="button" style="width:230px"  name="GenerateMonthlyBill" captionid="BTN.GENMONTHLYBILL"  bundle="${pensionLabels}" onclick="generateBill()"/>-->
		<hdiits:button type="button" style=""  name="Discard" captionid="PPMT.DISCARD"  bundle="${pensionLabels}" onclick="approveRejectChngStmnt('Discard')"/>
		<hdiits:button type="button" style="width:230px"  name="PrintMonthlyBill" captionid="BTN.PRINTMONTHLYBILL"  bundle="${pensionLabels}" onclick="printMonthlyBillReport()"/>
		<hdiits:button type="button" style="width:230px"  name="Print" captionid="GENCHANGSTMNT.PRINT"  bundle="${pensionLabels}" onclick="printChangeStatement()"/>
	</c:otherwise>
	</c:choose>
	<hdiits:button type="button"  name="close" captionid="MNTH.CLOSE"  bundle="${pensionLabels}" onclick="winCls();"/>
</div>
<div id = "chngStmntDtlsDiv">
</div>
</hdiits:form>
<script>
var changeRqstId="";
var lStrAllChangeRqstId = "";	//For generating monthly bill for all selected change statements.
var gIsBillGenerated = "N";
function validateGenerateBill()
{
	var arrChkBox=document.getElementsByName("chkbxPesnionerNo");
	var counter=0;
	lStrAllChangeRqstId = "";
	var lChangeRqstId = "";
	var lChangeRqstVal = "";
	if(arrChkBox.length > 0)
	{
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				counter++;	
				var resultId="lblStatus_"+arrChkBox[i].value;
	 			var status=document.getElementById(resultId).innerHTML;
				if(status == APPROVESTATUS)
				{
					lChangeRqstId="hdnChngRqstId_"+arrChkBox[i].value;
					lChangeRqstVal=document.getElementById(lChangeRqstId).value;
					lStrAllChangeRqstId = lStrAllChangeRqstId + "&changeRqstId="+lChangeRqstVal;
				}
				else
				{
					alert("Status should be Approved to generate Monthly Bill.");
					return false;
				}
			}
		}
	}
	if(counter==0)
	{
		alert("Please check atleast one Change Statement to generate monthly bill.");
		return false;
	}
	return true;
}

function generateBill()
{
	var subId="44";
	if(validateGenerateBill())
	{
		showProgressbar();
		/*var height = screen.height - 100;
		var width = screen.width;
		var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId='+subId+'&changeRqstId='+changeRqstId;
		//alert(urlstring );
		var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		//alert(urlstyle);
		window.open(urlstring,"MonthlyBillGeneration", urlstyle);
		*/
		url = 'ifms.htm?actionFlag=saveMonthlyPension';
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters:lStrAllChangeRqstId,
			        onSuccess: function(myAjax) {
			        	generateBillOnSucees(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
		
		
	}
}

function printMonthlyBillReport()
{
	prepareChangeStmntParamData();
	var lArrChangeStmntData = document.getElementsByName("changeRqstDtls");
	showProgressbar();
	url = 'ifms.htm?actionFlag=getPrintAllBill&billFlag=C';
	window.GeneratedChangeStatement.action = url;
	window.GeneratedChangeStatement.submit();
}

function generateBillOnSucees(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{
		var lArrSuccessStatus = XMLDoc.getElementsByTagName("SUCCESSSTATUS");
		var lSuccessStatus = "N";
		if(lArrSuccessStatus[0] != null)
		{
			lSuccessStatus = lArrSuccessStatus[0].childNodes[0].nodeValue;
			if(lSuccessStatus == "Y")
			{
				alert("Monthly Pension Bills for selected change statements generated successfully.");
				window.self.location.reload();
			}
			else
			{
				alert("Some problem occured during generation of monthly pension bill.");
				hideProgressbar();
			}
		}
	}
	else
	{
		alert("Some problem occured during generation of monthly pension bill.");
		hideProgressbar();
	}
}
function viewChangeStatement(chngRqstId)
{
	var url = "ifms.htm?actionFlag=reportService&reportCode=365454&action=generateReport&asPopup=TRUE";
	url += "&changeRqstId="+chngRqstId;
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "showChangeStatement", urlstyle);
}

function validateChngStmnt(validateFor,eventStatus)
{
	var arrChkBox=document.getElementsByName("chkbxPesnionerNo");
	var counter=0;
	var lArrChngRqstId = new Array();
	var rowNum = 0;
	var status = "";
	var forMonth = "";
	var lArrApprovedChngRqstIds = new Array();
	var lStrApprovedChngRqstIds = "";
	var tmpChangeRqstId = "";
	gIsBillGenerated = "N";
	if(arrChkBox.length > 0)
	{
		
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				rowNum = arrChkBox[i].value;
				lArrChngRqstId[counter]=document.getElementById("hdnChngRqstId_"+rowNum).value;
				tmpChangeRqstId = document.getElementById("hdnChngRqstId_"+rowNum).value;
				forMonth = document.getElementById("hdnForMonth_"+rowNum).value;
				counter++;
				
				if(validateFor == "Forward")
				{
					status=document.getElementById("lblStatus_"+rowNum).innerHTML;
					if(status == APPROVESTATUS)
					{
						alert("Approved Change Statements cannot be forwarded");
						return false;
					}
					if(status == REJECTEDSTATUS)
					{	
						alert("Rejected Change Statements cannot be forwarded.Please discard change statements having status rejected");
						return false;
					}
				}
				if(validateFor == "ApproveReject")
				{
					if(eventStatus == "Discard")
					{
						status=document.getElementById("lblStatus_"+rowNum).innerHTML;
						if(status != REJECTEDSTATUS && status != PENDINGAPPROVALSTATUS)
						{
							if(status == APPROVESTATUS)
							{
								lArrApprovedChngRqstIds[lArrApprovedChngRqstIds.length] = tmpChangeRqstId;
							}
							//alert("Approved change statements can not be discarded");
							//return false;
						}
					}
				}
			}
		}
		changeRqstId = lArrChngRqstId.join("~");	
		lStrApprovedChngRqstIds = lArrApprovedChngRqstIds.join("~");
	}
	if(counter == 0)
	{
		alert("Please select atleast one Change Statement.");
		return false;
	}
	if(lArrApprovedChngRqstIds.length > 0)
	{
		checkIsBillGeneratedForChangeStmnt(lStrApprovedChngRqstIds,forMonth);
	}
	return true;
}

function forwardChangeStatement()
{
	if(validateChngStmnt("Forward",""))
	{
		showProgressbar();
		var uri = "ifms.htm?actionFlag=frwdChngStmnt";
		var url = "&changeRqstId="+changeRqstId;
		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			return;
		}
		xmlHttp.onreadystatechange = responseForwardChngStmnt;
		xmlHttp.open("POST", uri, true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(url);
	}
}

function responseForwardChngStmnt()
{
	if (xmlHttp.readyState == complete_state)
	{
		var XMLDoc = xmlHttp.responseXML.documentElement;
		var lObjFrwdStatus =  XMLDoc.getElementsByTagName('FRWDSTATUS');
		var lFrwdStatus = lObjFrwdStatus[0].text;
		if(lFrwdStatus == "Y")
		{
			alert("Selected change statement forwarded  to Pension ATO successfully");
			window.location.href = "ifms.htm?actionFlag=loadMonthlyList";
		}
		else
		{
			alert("Some problem occurred of forwarding the change statement.Please try again..");
		}
	}
}

	
function approveRejectChngStmnt(eventStatus)
{
	if(validateChngStmnt("ApproveReject",eventStatus))
	{
		if(gIsBillGenerated == "N")
		{
			showProgressbar();
			var uri = "ifms.htm?actionFlag=approveChngStmnt";
			var url = "&changeRqstId="+changeRqstId+"&approveRejectFlag="+eventStatus;
			xmlHttp = GetXmlHttpObject();
			if (xmlHttp == null) {
				return;
			}
			xmlHttp.onreadystatechange = responseApproveRejectChngStmnt;
			xmlHttp.open("POST", uri, true);
			xmlHttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xmlHttp.send(url);
		}
	}
}

function responseApproveRejectChngStmnt()
{
	if (xmlHttp.readyState == complete_state)
	{
		var XMLDoc = xmlHttp.responseXML.documentElement;
		var lObjApproveStatus =  XMLDoc.getElementsByTagName('APPROVESTATUS');
		var lApproveStatus = lObjApproveStatus[0].text;
		if(lApproveStatus != "ERROR")
		{
			if(lApproveStatus == "Approve")
			{
				alert("Selected change statement approved successfully and forwarded to Pension Auditor");
				window.location.href = "ifms.htm?actionFlag=loadMonthlyList&showWLFor=Ato";
			}
			else if(lApproveStatus == "Reject")
			{
				alert("Selected change statement rejected successfully and forwarded to Pension Auditor");
				window.location.href = "ifms.htm?actionFlag=loadMonthlyList&showWLFor=Ato";
			}
			else if(lApproveStatus == "Discard")
			{
				alert("Selected change statement discarded successfully");
				window.location.href = "ifms.htm?actionFlag=loadMonthlyList";
			}
			else
			{
				alert("Some problem occurred during approve or reject");
			}
			
		}
		else{
			alert("Some problem occurred during approve or reject");
		}
	}
}
function printChangeStatement()
{
	/*var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters:params,
		        onSuccess: function(myAjax) {
		        	printChangeStatementUsingAjax(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		        } 
	);*/
	prepareChangeStmntParamData();
	var url = "ifms.htm?actionFlag=printChngStmnt";
	document.GeneratedChangeStatement.action =  url;
	document.GeneratedChangeStatement.submit();
	
	/*var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "printChangeStatement", urlstyle);*/
}
function prepareChangeStmntParamData()
{
	var arrChkBox=document.getElementsByName("chkbxPesnionerNo");
	var lArrStrChngRqstId = new Array();
	var lArrStrBankCode = new Array();
	var lArrStrBranchCode = new Array();
	var lArrStrForMonth = new Array();
	var counter = 0;
	var lChangeRqstId = "";
	var lChangeRqstVal = "";
	var lBankCode = "";
	var lBranchCode = "";
	var lForMonth = "";
	var params = "";
	var chngStmtnDtlsData = "";
	if(arrChkBox.length > 0)
	{
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				lChangeRqstId="hdnChngRqstId_"+arrChkBox[i].value;
				lChangeRqstVal=document.getElementById(lChangeRqstId).value;
				lBankCode = document.getElementById("hdnBankCode_"+arrChkBox[i].value).value;
				lBranchCode = document.getElementById("hdnBranchCode_"+arrChkBox[i].value).value;
				lForMonth = document.getElementById("hdnForMonth_"+arrChkBox[i].value).value;
				lArrStrChngRqstId[counter] = lChangeRqstVal;
				lArrStrBankCode[counter] = lBankCode;
				lArrStrBranchCode[counter] = lBranchCode;
				lArrStrForMonth[counter] = lForMonth;
				counter++;
			}
		}
	}
	for(var i = 0 ; i <lArrStrChngRqstId.length;i++)
	{
		var params = lArrStrChngRqstId[i]+"~"+lArrStrBankCode[i]+"~"+lArrStrBranchCode[i]+"~"+lArrStrForMonth[i];
		chngStmtnDtlsData = chngStmtnDtlsData +
							"<input type='hidden' value="+params+" name='changeRqstDtls' />";
	}
	document.getElementById("chngStmntDtlsDiv").innerHTML = "";
	document.getElementById("chngStmntDtlsDiv").innerHTML = chngStmtnDtlsData;
}
function checkIsBillGeneratedForChangeStmnt(changeRqstId,forMonth)
{
	var params = "changeRqstId="+changeRqstId+"&forMonth="+forMonth;
	var url = "ifms.htm?actionFlag=checkBillForChngStmnt";
	var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:params,
		        onSuccess: function(myAjax) {
		        	respCheckIsBillGeneratedForChangeStmnt(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function respCheckIsBillGeneratedForChangeStmnt(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{
		var lArrIsBillGenerated = XMLDoc.getElementsByTagName("ISBILLGENERATED");
		var lArrMonthYear = XMLDoc.getElementsByTagName("MONTHYEAR");
		var isBillGenerated;
		var monthYearDesc = "";
		if(lArrIsBillGenerated[0] != null)
		{
			isBillGenerated = lArrIsBillGenerated[0].childNodes[0].nodeValue;
			if(isBillGenerated == "Y")
			{
				gIsBillGenerated = "Y";
				if(lArrMonthYear[0] != null)
				{
					monthYearDesc = lArrMonthYear[0].childNodes[0].nodeValue;
				}
				alert("Selected change statement cannot be discarded because Monthly Pension Bills from selected change statement have already been generated.Please reject monthly pension bills for "+monthYearDesc+" first.")
			}
		}
	}
	else
	{
		alert("Some problem occurred.Please try again later.");	
	}
}
</script>
<% }catch(Exception e)
{
	e.printStackTrace();
}%>