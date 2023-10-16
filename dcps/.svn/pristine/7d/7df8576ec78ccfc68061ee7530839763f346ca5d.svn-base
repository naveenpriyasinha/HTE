
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript"
	src="script/hrms/hr/incharge/modifyInchargeReqViewApp.js"></script>

<fmt:setBundle basename="resources.hr.incharge.InchargeAlertMessage"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.hr.incharge.InchargeLabels"
	var="CapLabels" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="appDetail" value="${resultValue.ApplicantDetail}"></c:set>
<c:set var="aList" value="${resultValue.InchargePerDetail}"></c:set>
<c:set var="inchrgReqId" value="${resultValue.inchrgReqId}"></c:set>
<c:set var="currentInchrgDetail"
	value="${resultValue.currentInchrgDetail}"></c:set>
<c:set var="fileid" value="${resultValue.fileId}" />
<c:set var="hstryLst" value="${resultValue.hstryLst}"></c:set>




<SCRIPT LANGUAGE="JavaScript">

var postRowId;
var gvindex;
var setVal='$';
var flag=0;
var iIndex;

function checkFormValidation()
{
	var tblobj=document.getElementById("currInchgDetail");
	var tbllen=tblobj.rows.length;
	var newval='newInchg';
	for(i=1;i<tbllen;i++)
	{
		var fromDate='fromdate'+i;
		var toDate='todate'+i;
		var temp=document.getElementById(i).value+','+document.getElementById(fromDate).value+','+document.getElementById(toDate).value+','+document.getElementById(newval+i).value;
		var aList=new Array();
		aList=temp.split(',');	
	
		
		if(getDateDiffInString(aList[2],aList[4]))
		{
			if(getDateDiffInString(aList[4],aList[3])) 	{
				if(getDateDiffInString(aList[5],aList[3]))	{
				    setVal+=temp+'$';
				}
			else
				{
					alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
					return 'false';
				}
			}
			else
			{
				alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
				return 'false';
			}
	  }	
	  else {
	  	
	  		alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	  		return 'false';
	  }
	  	
	if(aList[6]==null || aList[6]=='' ||aList[6]==' ')
	{
	  if(!checkPostId(aList[1],aList[1]) )
	  {
		return 'false';
	  }
			
	}
	else
	{
		if(!checkPostId(aList[1],aList[6]) )
			{
			 return 'false';
			}
		}
	}
}



// for Search Person
function SearchEmpIncharge(postRowId1, index){
	postRowId=postRowId1;
	gvindex='newInchg'+index;
	iIndex=index;
	
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		
	// 	showSearchInchg(form);		
	
}

var empArrayInch = new Array();

function empSearch(from){

	for(var i=0; i<from.length; i++){		
		empArrayInch[i] = from[i].split("~");	
	}
	
	var empRlt = empArrayInch[0];
	flag=1;
	var userID = empRlt[2];
	document.getElementById(postRowId).value="";
	if(document.getElementById("appDetailUserId").value == trimAll(userID)) {
		alert('<fmt:message  bundle="${alertLables}" key="IC.notEligibleInchargePerson"/>');
	}
	else {
	
		 document.getElementById(postRowId).value=trimAll(empRlt[1]) ;
		 document.getElementById(gvindex).value= trimAll(userID)+','+trimAll(empRlt[4]) ;
	}

	var objChk = document.forms[0].inchrgPersonDtl;

}
function trimAll(sString) 
{ 
	var ss=sString.trim();
	return ss; 
} 
function getDateDiffInString(strDate1,strDate2)
{
		strDate1 = strDate1.split("/"); 		
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		//starttime = new Date(starttime.valueOf()); 
		
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		//endtime = new Date(endtime.valueOf()); 												
						
		if(endtime >= starttime) 
		{
			return true;
		}
		else
		{	return false;}
}
// End of Search Person

// Check post Id
function checkPostId(appPostId,newInchgPostId)
{
			return true;
	// Disable Post check function
	/*
			document.getElementById('hiddenResVal').value=trimAll(appPostId)+','+trimAll(newInchgPostId)+',';
		    functionCheckpostLevel('postLevel','checkPostLevel',new Array('hiddenResVal'));	    
	   
		    var xmlKey=document.getElementById('assPostLevel').value;
	    	
		     if(xmlKey == 'true')   {
	    	 	return true; 
		     }
		     else  {
	     		alert('<fmt:message  bundle="${alertLables}" key="IC.postNotSame"/>');
		     	return false;
		     }	
		
		// End of  Check Post Level
	*/
		
}
function functionCheckpostLevel(methodName, actionFlag, fieldArray)
{
	xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		var reqBody = getRequestBody(fieldArray);
	
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange=function() { eval(methodName); }	
	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
}

function postLevel()
{
	
	if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			var tmp=encXML;
			var len=tmp.length;
			var subXml=tmp.substring(0,len);
			document.getElementById('assPostLevel').value=subXml;
		}
}	
// End of check Post Id

// start tab navigation Validation
	function ValidateandGenerateFile()
	{

		if(checkFormValidation()== 'false')
	{
			alert('<fmt:message  bundle="${alertLables}" key="IC.enterValidDate"/>');  
			return ;
	}
	else {
			document.getElementById('setVal').value=setVal;
	         return true;
	 }
	
	}
//end tab navigation Validation

</script>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>

<hdiits:form name="InchargeReqView" validate="true" method="POST"
	action="./hrms.htm?actionFlag=setModifyInchargeReqApprove"
	encType="text/form-data">



	<table width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" align="center"><font color="#ffffff">
			<strong><u> <fmt:message key="IC.PerOn"
				bundle="${CapLabels}" /><c:out value=" " /> <c:out
				value="${appDetail.cmnLookDesc}" /> <fmt:message key="IC.PerOn1"
				bundle="${CapLabels}" /></u></strong> </font>
		<tr height="20"></tr>
	</table>

	<table id="assDetail" align="center" width="100%" cellpadding="1"
		cellspacing="1" style="border-collapse: collapse;">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="IC.name"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="25%"><c:out value="${appDetail.appName}" /></td>
			<td width="25%"><b><hdiits:caption captionid="IC.post"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="25%"><c:out value="${appDetail.appPostName}" /></td>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption
				captionid="IC.Designation" bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="25%"><c:out value="${appDetail.appDegnName}" /></td>
			<td width="25%"><b><hdiits:caption captionid="IC.location"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="25%"><c:out value="${appDetail.location}" /></td>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption captionid="IC.frmDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="25%"><c:out value="${appDetail.frmDate}" /></td>
			<td width="25%"><b><hdiits:caption captionid="IC.toDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="25%"><c:out value="${appDetail.toDate}" /></td>
			<input type="hidden" name="appDetailUserId" id="appDetailUserId"
				value="${appDetail.appUserId}" />

			<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
			<hdiits:hidden name="fileId" id="fileId" default="${fileid}" />
		</tr>
	</table>
	<input type="hidden" name="inchrgReqId" value="${inchrgReqId}" />
	<table align="center" width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr bgcolor="#386CB7" align="center">
			<td class="fieldLabel"><font color="#ffffff"> <strong><u>
			<fmt:message key="IC.HstryDtl" bundle="${CapLabels}" /> </u></strong> </font></td>
		</tr>
	</table>

	<table id="HstInchgDetail" align="center" border="1" width="100%"
		style="border-collapse: collapse;  background-color:${tableBGColor}" border=1 borderColor="black">

		<tr class="datatableheader"  style="background-color:${tdBGColor}">
			<td width="3%" style="background-color:${tdBGColor}"><b><hdiits:caption captionid="IC.SrNo"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="15%"><b><hdiits:caption captionid="IC.post"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="27%"><b><hdiits:caption captionid="IC.inchrgName"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="15%"><b><hdiits:caption captionid="IC.frmDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="15%"><b><hdiits:caption captionid="IC.toDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<c:set var="i" value="1" />
		</tr>

		<c:forEach var="cListItr" items="${hstryLst}">
			<tr align="center">
				<td><c:out value="${i}" /></td>
				<td width="15%"><c:out value="${cListItr.inchgPostName}" /></td>
				<td width="15%"><input type='text' id='text${i}'
					name='text${i}' style="border:none; background:#F0F4FB; " value="${cListItr.inchgName}"
					readonly="readonly">
				<td width="15%"><input type='text' id='frmdat${i}'
					name='frmdat${i}' style="border:none; background:#F0F4FB; " value="${cListItr.frmDate}"
					readonly="readonly"></td>
				<td width="15%"><input type='text' id='todat${i}'
					name='todat${i}' style="border:none;background:#F0F4FB; " value="${cListItr.toDate}"
					readonly="readonly"></td>

			</tr>
			<c:set var="i" value="${i+1}" />
		</c:forEach>
	</table>


	<br>
	<br>
	<table align="center" width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr bgcolor="#386CB7" align="center">
			<td class="fieldLabel"><font color="#ffffff"> <strong><u>
			<fmt:message key="IC.currentHeld" bundle="${CapLabels}" /> </u></strong> </font></td>
		</tr>
	</table>
	<table id="currInchgDetail" align="center" border="1" width="100%"
		style="border-collapse: collapse;  background-color:${tableBGColor}" border=1 borderColor="black">

		<tr class="datatableheader" style="background-color:${tdBGColor}">
			<td width="3%" style="background-color:${tdBGColor}"><b><hdiits:caption captionid="IC.SrNo"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="15%"><b><hdiits:caption captionid="IC.post"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="27%"><b><hdiits:caption captionid="IC.inchrgName"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="15%"><b><hdiits:caption captionid="IC.frmDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="15%"><b><hdiits:caption captionid="IC.toDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<c:set var="i" value="1" />
		</tr>

		<c:forEach var="aListItr" items="${currentInchrgDetail}">
			<tr align="center">
				<td align ="center" ><c:out value="${i}" /></td>
				<input type="hidden" name="${i}" id="${i}"
					value="${aListItr.hrInchgDtlRlt},${aListItr.inchgPostHeld},${appDetail.frmDate},${appDetail.toDate}" />
				<input type="hidden" name="newInchg${i}" id="newInchg${i}" value=" " />
				<td width="15%" align ="center"><c:out value="${aListItr.inchgPostName}" /></td>
				<td width="15%" align ="center"><input type='text' id='txt${i}' name='txt${i}'
					value="${aListItr.inchgName}" readonly="readonly" style="border:none" >  
					 </td>
				<td width="15%" align ="center"><hdiits:text validation="txt.isrequired"
					 name="fromdate${i}" captionid="IC.frmDate"
					bundle="${CapLabels}" style="border:none"></hdiits:text> <hdiits:hidden
					name="frmdt" default="${aListItr.frmDate}" /> <script>
					document.getElementById('fromdate${i}').value=document.getElementById('frmdt').value;
				</script></td>
				<td width="15%" align ="center"><hdiits:text  style="border:none" validation="txt.isrequired"
					  name="todate${i}" captionid="IC.toDate" bundle="${CapLabels}"></hdiits:text></td>
				<hdiits:hidden name="todt" default="${aListItr.toDate}" />
				<script>
					document.getElementById('todate${i}').value=document.getElementById('todt').value;
				</script>
				</tr>
					<c:set var="i" value="${i+1}" />
					</c:forEach>
				</table>
		<br>
		<br>
		<table align="center" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">

		<hdiits:hidden name="assPostLevel" />
		<hdiits:hidden name="hiddenResVal" />
		<hdiits:hidden name="setVal" id="setVal" />
	</table>



	

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
