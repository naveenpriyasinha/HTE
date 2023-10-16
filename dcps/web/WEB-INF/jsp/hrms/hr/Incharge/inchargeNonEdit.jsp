
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
<script type="text/javascript" src="common/script/tabcontent.js"></script>


<fmt:setBundle basename="resources.hr.incharge.InchargeAlertMessage"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.hr.incharge.InchargeLabels"
	var="CapLabels" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="appDetail" value="${resultValue.ApplicantDetail}"></c:set>
<c:set var="aList" value="${resultValue.InchargePerDetail}"></c:set>
<c:set var="inchrgReqId" value="${resultValue.inchrgReqId}"></c:set>
<c:set var="langId" value="${resultValue.langId}"></c:set>
<c:set var="fileid" value="${resultValue.fileId}" />

<SCRIPT LANGUAGE="JavaScript">


var postRowId;
var gvindex;
var setVal='$';
var flag=0;

function checkFormValidation()
{
	var tblobj=document.getElementById("inchrgPersonDtl");
	var tbllen=tblobj.rows.length;	
	
	setVal='$';
		
	for(i=1;i<tbllen;i++)
	{
	//	alert('Hidden'+i+'--'+ document.getElementById(i).value);
		var newval='newInchg'+i;
		var fmDt='fromdate'+i;
		var toDt='todate'+i;		
		var temp=document.getElementById(i).value+','+document.getElementById(fmDt).value+','+document.getElementById(toDt).value+','+document.getElementById(newval).value;
		
		var aList=new Array();
		aList=temp.split(',');
		
		if(getDateDiffInString(aList[2],aList[4]))
		{
			if(getDateDiffInString(aList[4],aList[3])) 	{
				if(getDateDiffInString(aList[5],aList[3]))	{
				setVal+=temp+'$';
			}
				else
				{alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
				return 'false';
				}
			}
			else
			{alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
			return 'false';
			}
	  }	
	  else {
	  	  alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	  	  return 'false';
	  }	
	}	
	//alert('In validation'+setVal);
}

// Check post Id
function checkPostId(sval)
{
	//alert('sval :: '+sval);
	var tmp=new Array();
	tmp=sval.split('$');
	return 'true';
	//alert('tmp length :: '+tmp.length+ '   tmp[0] :: '+tmp[0]+'tmp[1] ::'+tmp[1]);
	
	// Disable check post Id
	/*
	for(i=1;i<tmp.length-1;i++)
	{
		// Check Post Level		
		var temp=new Array();
		temp=tmp[i].split(',');
		
		if(temp[7] =='undefined' || temp[7]=='' || temp[7]==null)
		{
			document.getElementById('hiddenResVal').value=temp[1]+','+temp[1]+',';
		    functionCheckpostLevel('postLevel','checkPostLevel',new Array('hiddenResVal'));	    
	   
		    var xmlKey=document.getElementById('assPostLevel').value;
	    	
		     if(xmlKey == 'true')   {
	    	 	return 'true'; 
		     }
		     else  {
	     		alert('<fmt:message  bundle="${alertLables}" key="IC.postNotSame"/>');	   
		     	return 'false';
		     }	
		}
		else
		{
		
			document.getElementById('hiddenResVal').value=temp[1]+','+temp[7]+',';
		    functionCheckpostLevel('postLevel','checkPostLevel',new Array('hiddenResVal'));	    
	   
		    var xmlKey=document.getElementById('assPostLevel').value;
	    	
		     if(xmlKey == 'true')
		     {
	    	 	return 'true'; 
		     }
		     else
	    	 {
	     		alert('<fmt:message  bundle="${alertLables}" key="IC.postNotSame"/>');	   
		     	return 'false';
		     }	
	     }	
		// End of  Check Post Level
	}
	*/
	
}
function functionCheckpostLevel(methodName, actionFlag, fieldArray)
{
	xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');
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
		//	alert('encXML-->>'+encXML);
			var tmp=encXML;
			var len=tmp.length;
			// var subXml=tmp.substring(1,len);
			var subXml=tmp.substring(0,len);
			document.getElementById('assPostLevel').value=subXml;
		}
}	
// End of check Post Id

function approveForm()
{	
	if(checkFormValidation()== 'false')
	{
		//alert('<fmt:message  bundle="${alertLables}" key="IC.enterValidDate"/>');  
			return ;
	}
	else
	{
	//	alert('Approve-->> '+setVal);
		if(checkPostId(setVal)=='true') {	
		document.getElementById('approve').disabled=true;
		document.getElementById('forward').disabled=true;
			
		document.InchargeReqView.action="hdiits.htm?actionFlag=setInchargeReqApprove&setVal="+setVal;
		document.InchargeReqView.submit();
		}
		else
		{
			return;
		}
	}
}

function forwardForm()
{
	if(checkFormValidation()== 'false')
	{
		  //   alert('<fmt:message  bundle="${alertLables}" key="IC.enterValidDate"/>');  
			return ;
	}
	else
	{
	//	alert('Forward-->> '+setVal);
		if(checkPostId(setVal)=='true') {
		document.getElementById('approve').disabled=true;
		document.getElementById('forward').disabled=true;
		
		document.InchargeReqView.action="hdiits.htm?actionFlag=setInchargeReqForward&setVal="+setVal;
		document.InchargeReqView.submit();
		}
		else
		{
			return;
		}
	}
}

// for Search Person
function SearchEmpIncharge(postRowId1, index){
	postRowId=postRowId1;
	gvindex='newInchg'+index;
	
	//alert('passed Row Id-- '+postRowId1);
	var href='hdiits.htm?actionFlag=inchgEmpSearch';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		
	// 	showSearchInchg(form);		
	
}

var empArrayInch = new Array();

function showSearchInchg(from){

	for(var i=0; i<from.length; i++){
	//	alert(from[i]);
		empArrayInch[i] = from[i].split("~"); 
	//	alert(empArrayInch[i]);
	}
	var empRlt = empArrayInch[0];
//	alert(empRlt[0]);
	flag=1;
	var userID = empRlt[2];
	document.getElementById(postRowId).value="";
//	alert('userID-------'+userID);
//	alert('in function ::-- '+postRowId);
	if(document.getElementById("appDetailUserId").value== trimAll(userID) ) {
		alert('<fmt:message  bundle="${alertLables}" key="IC.notEligibleInchargePerson"/>');
	}
	else {
			 document.getElementById(postRowId).value=empRlt[1];
			 document.getElementById(gvindex).value=trimAll(userID)+','+empRlt[4]+',' ;
	}	
	// alert('document.getElementById(gvindex).value-->>'+document.getElementById(gvindex).value);
	
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
		starttime = new Date(starttime.valueOf()); 
		
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 												
						
		if(endtime >= starttime) 
		{
			return true;
			}
		else
		{	return false;}
}

// End of Search Person

// start tab navigation Validation
	function ValidateandGenerateFile()
	{
	
	  
		if(checkFormValidation()== 'false')
	{
		//alert('<fmt:message  bundle="${alertLables}" key="IC.enterValidDate"/>');  
			return ;
	}
	else {
	 if(checkPostId(setVal)=='true') {	
		
			document.getElementById('setVal').value=setVal;
	         return true;
		}
	 }
	
	}
//end tab navigation Validation

</SCRIPT>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<hdiits:form name="InchargeReqView" validate="true" method="POST"
	action="./hrms.htm?actionFlag=saveInchrgDtl" encType="text/form-data">
	<div id="tabmenu" style="height: 70%">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="IC.IncReq" bundle="${CapLabels}" captionLang="single"/></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 70%">
	<div id="tcontent1" class="tabcontent" tabno="0" style="height: 70%">
	<br>

	

	<!--<table width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" align="center"><font color="#ffffff">
			<strong><u> <fmt:message key="IC.PerOn"
				bundle="${CapLabels}" /><c:out value=" " /> <c:out
				value="${appDetail.cmnLookDesc}" /><c:out value=" " /><fmt:message
				key="IC.PerOn1" bundle="${CapLabels}" /> </u></strong> </font>
		<tr height="20"></tr>
	</table>
	-->
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" /> <hdiits:hidden
		name="fileId" id="fileId" default="${fileid}" />
<hdiits:fieldGroup titleCaptionId="IC.PerOn" id="PersonOnLeave" bundle="${CapLabels}">
	<table id="assDetail" align="center" width="100%" cellpadding="1"
		cellspacing="1">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="IC.name"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="25%"><c:out value="${appDetail.appName}" /></td>
			<td width="25%"><b><hdiits:caption captionid="IC.post"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="25%"><c:out value="${appDetail.appPostName}" /></td>
			<input type="hidden" name="appDetailUserId" id="appDetailUserId"
				value="${appDetail.appUserId}" />
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
		</tr>
	</table>
</hdiits:fieldGroup>
	<table width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" align="center"><font color="#ffffff">
			<strong><u> <fmt:message key="IC.personDtl"
				bundle="${CapLabels}" /></u></strong> </font></td>
		<tr height="20"></tr>

		<input type="hidden" name="inchrgReqId" value="${inchrgReqId}" />
	</table>

	<table id="inchrgPersonDtl" align="center" width="100%" cellpadding="1"
		style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" >
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

		<c:forEach var="aListItr" items="${aList}">
			<tr>
				<td  width="10%"align="center"><c:out value="${i}" /></td>
				<input type="hidden" name="${i}" id="${i}"
					value="${aListItr.hrInchgDtlRlt},${aListItr.inchgPostHeld},${appDetail.frmDate},${appDetail.toDate}" />
				<input type="hidden" name="newInchg${i}" id="newInchg${i}" value=" " />
				<td width="30%" align="center"><c:out
					value="${aListItr.inchgPostName}" /></td>
				<td width="30%" align="center"><input type='text' id='txt${i}'
					name='txt${i}'  style="border:none" value="${aListItr.inchgName}"   readonly="readonly">
				</td>
				<td width="15%" align="center"><hdiits:text size="10" 
					validation="txt.isrequired" name="fromdate${i}"
					captionid="IC.frmDate" bundle="${CapLabels}"  style="border:none"></hdiits:text>
				 <hdiits:hidden
					name="frmdt" default="${aListItr.frmDate}" /> <script>
document.getElementById('fromdate${i}').value=document.getElementById('frmdt').value;
</script></td>
				<td width="15%" align="center"><hdiits:text size="10" 
					validation="txt.isrequired"  name="todate${i}"
					captionid="IC.toDate" bundle="${CapLabels}" style="border:none"></hdiits:text>
</td>

				<hdiits:hidden name="todt" default="${aListItr.toDate}" />
				<script>
				document.getElementById('todate${i}').value=document.getElementById('todt').value;
</script>
			</tr>
			<c:set var="i" value="${i+1}" />
		</c:forEach>
		<hdiits:hidden name="assPostLevel" />
		<hdiits:hidden name="hiddenResVal" />
		<hdiits:hidden name="setVal" id="setVal" />
	</table>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	</div>
	</div>


	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />


</hdiits:form>

</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
