<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script type="text/javascript">
function searchReq()
{
	var name = document.getElementById("txtEmpName").value;
	var date = document.getElementById("txtDate").value;
	var criteria;
	var url;
	
	if (document.getElementById("txtEmpName").value =="" && document.getElementById("txtDate").value =="")
	{
		alert("Please Select Name or Enter Date");
		return false;
	}
	else
	{
		if (document.getElementById("txtDate").value =="")
		{
			criteria = "name";
			url = "&name="+name+"&criteria="+criteria;
		}
		else if (document.getElementById("txtEmpName").value =="")
		{
			criteria = "date";
			url = "&date="+date+"&criteria="+criteria;
		}
		else
		{
			criteria = "both";
			url = "&name="+name+"&date="+date+"&criteria="+criteria;			
		}
	}

	var uri = 'ifms.htm?actionFlag=loadDraftRequests'+url;
	document.frmGPFDraftReq.action = uri ;
	enableAjaxSubmit(true);
	document.frmGPFDraftReq.submit();

	/*	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function() {					        	
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );*/
}

function getAllRecords()
{
	var uri = 'ifms.htm?actionFlag=loadDraftRequests';
	document.frmGPFDraftReq.action = uri ;
	enableAjaxSubmit(true);
	document.frmGPFDraftReq.submit();
}
function discardRequest(){
	var totalCount= document.getElementById("vo").rows.length - 1;
	var flag=0;
	var reqTypes = "";
	var reqPKs = "";
	var lArrChkBox = document.getElementsByName("chkRequest");
	var currRecord = "";
	for(var i=0;i<lArrChkBox.length;i++)
	{
		if(lArrChkBox[i].checked == true)
		{
			currRecord = (lArrChkBox[i].id.split("_"))[1];
			reqTypes = reqTypes + document.getElementById("hidReqType"+currRecord).value + "~" ;
			reqPKs = reqPKs + document.getElementById("hidPKValue"+currRecord).value + "~" ;
			flag = 1;
		}
	}
	if(flag == 1)
	{
		var uri = 'ifms.htm?actionFlag=discardGPFRequest';
		var url='&reqTypes='+reqTypes+'&reqPKs='+reqPKs;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getDiscardRequestMsg(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	else
	{
		alert("Please select a Request");
	}
}
function getDiscardRequestMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblSaveFlag) {
		alert('GPF Request Discarded Successfully');
		self.location.reload();		
	}
}
</script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DraftList" value="${resValue.lLstDraftReq}"></c:set>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />

<hdiits:form name="frmGPFDraftReq" encType="multipart/form-data"  validate="true" method="post">
<table width="100%">
<tr>
<td>
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.SEARCHREQ" bundle="${gpfLables}" ></fmt:message></legend>
<table width="100%">
	<tr>
		<td width="20%">
			<b><fmt:message key="CMN.SEARCHREQBY" bundle="${gpfLables}"></fmt:message></b>
		</td>
		<td width="20%">
			<fmt:message key="CMN.NAME" bundle="${gpfLables}"></fmt:message>
			 <input type="text" id="txtEmpName" name="txtEmpName" />
			 <span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
		</td>
		<td width="20%">
			<fmt:message key="CMN.DATE" bundle="${gpfLables}"></fmt:message>
			 <input type="text" id="txtDate" name="txtDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/>
		    <img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtDate",375,570)' style="cursor: pointer;"  />
		</td>
	
		<td width="10%">
			<hdiits:button type="button" captionid="BTN.SEARCH" bundle="${gpfLables}" id="btnGo" name="btnGo" onclick="searchReq();" ></hdiits:button>
		</td>
		
		<td width="30%">
			<hdiits:button type="button" captionid="BTN.GETALL" bundle="${gpfLables}" id="btnGetAll" name="btnGetAll" onclick="getAllRecords();" ></hdiits:button>
		</td>		
		
	</tr>	
</table>
</fieldset>
</td>
</tr>

<tr>
<td>
<table width="100%">
<tr>
<td>
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.DRAFTREQ" bundle="${gpfLables}" ></fmt:message></legend>
    <display:table list="${DraftList}"  id="vo" requestURI="" export="" style="width:100%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" titleKey="CMN.SUBSCRIBER" >		
				<a href = "ifms.htm?actionFlag=loadGPFRequestProcess&txtSevaarthId=${vo[2]}&criteria=1&requestType=${vo[5]}&userType=DEO&TrnsId=${vo[1]}&pkValue=${vo[6]}"><c:out value="${vo[0]}" /></a>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.SEVAARTHID" >		
				<c:out value="${vo[2]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.REQTYPE" >		
				<c:if test="${vo[5] == 'CS'}"><c:out value="Change Subscription"></c:out></c:if>
				<c:if test="${vo[5] == 'RA'}"><c:out value="Refundable Advance"></c:out></c:if>
			    <c:if test="${vo[5] == 'NRA'}"><c:out value="Non-Refundable Advance"></c:out></c:if> 		
			    <c:if test="${vo[5] == 'FW'}"><c:out value="Final Payment"></c:out></c:if> 	
		</display:column>
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="appDate"/>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.APPDATE"  >		
				<c:out value="${appDate}"></c:out> 
		</display:column>	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.STATUS" >
			<c:choose>
			<c:when test="${vo[9] == 'D'}"><c:out value="Draft"></c:out></c:when>		
			<c:when test="${vo[9] == 'R' && (vo[8]=='' || vo[8] == null)}"><c:out value="Sent Back"></c:out></c:when>
			<c:otherwise ><c:out value="Rejected"></c:out></c:otherwise>
			</c:choose>
		</display:column>		
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.VERIFIERREMARKS"  >		
				<c:out value="${vo[7]}"></c:out> 
		</display:column>	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.HOREMARKS"  >		
				<c:out value="${vo[8]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true"  titleKey="CMN.SAVEDON"  >	
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy h:mm a" value="${vo[10]}" />		
		</display:column>
		<display:column style="text-align:center" class="oddcentre" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" headerClass="datatableheader">
	      		<input type="checkbox" name="chkRequest" id="chkRequest_${vo_rowNum}" value="${vo[1]}" />
	      		<input type="hidden" name="hidReqType" id="hidReqType${vo_rowNum}" value="${vo[5]}" />
	      		<input type="hidden" name="hidPKValue" id="hidPKValue${vo_rowNum}" value="${vo[6]}" />
		</display:column>
	</display:table>
	<c:if test="${DraftList != null && DraftList[0] != null}">
	<table>
	<tr>
		<td colspan="2" align="center"><label style="color: red"><fmt:message key="MSG.DRAFTREQ" bundle="${gpfLables}" /></label></td>
	</tr>
	</table>
	</c:if>
	
</fieldset>
</td>
</tr>
</table>
</td>
</tr>
</table>
 <center><hdiits:button type="button" captionid="BTN.DISCARD" bundle="${gpfLables}" id="btnDiscard" name="btnDiscard" onclick="discardRequest();"></hdiits:button></center>
</hdiits:form>

<ajax:autocomplete source="txtEmpName" target="txtEmpName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoComplete"
	parameters="searchKey={txtEmpName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />