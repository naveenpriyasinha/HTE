
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
	
	if(aList[4]=='Leave' || aList[4]=='Deputation'){
		
			if(getDateDiffInString(aList[2],aList[5]))
			{
					if(getDateDiffInString(aList[5],aList[6])) 	{
				
						if(getDateDiffInString(aList[6],aList[3]))	{
				    			
				    			setVal+=temp+'$';
						}
						else
						{
							alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
							return false;
						}
					}
					else
					{
						alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
						return false;
					}
	  		}	
	  		else {
	  	
	  				alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +aList[2]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +aList[3]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	  				return false;
	  		}
	}
	else{
	  	
	  		if(getDateDiffInString(aList[2],aList[5]))
			{
				
				if(getDateDiffInString(aList[5],aList[6])){
				
					setVal+=temp+'$';
				}
				else{
				
					alert('<fmt:message bundle="${alertLables}" key="IC.fromDtLessThanToDate"/>');
					return;
				}
			
			
			}
			else{
			
				alert('<fmt:message bundle="${alertLables}" key="IC.frmDateGe" /> '+aList[2]);
				return;
			}
	  	
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
function SearchEmpIncharge(textBoxId, index){
	postRowId=textBoxId;
	gvindex='newInchg'+index;
	iIndex=index;
	
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false&searchFieldName='+postRowId;
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		
	// 	showSearchInchg(form);		
	
}

var empArrayInch = new Array();

function empSearch(from, fieldName){

	for(var i=0; i<from.length; i++){		
		empArrayInch[i] = from[i].split("~");	
	}
	
	var empRlt = empArrayInch[0];
	flag=1;
	var userID = empRlt[2];
	document.getElementById(fieldName).value="";
	if(document.getElementById("appDetailUserId").value == trimAll(userID)) {
		alert('<fmt:message  bundle="${alertLables}" key="IC.notEligibleInchargePerson"/>');
	}
	else {
	
		 document.getElementById(fieldName).value=trimAll(empRlt[1]) ;
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
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<hdiits:form name="InchargeReqView" validate="true" method="POST"
	action="./hrms.htm?actionFlag=setModifyInchargeReqApprove"
	encType="text/form-data">

<div id="tabmenu" >
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<hdiits:caption captionid="IC.IncModify" bundle="${CapLabels}" captionLang="single"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
	
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<br>



	<!--<table width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" align="center"><font color="#ffffff">
			<strong><u> <fmt:message key="IC.PerOn"
				bundle="${CapLabels}" /><c:out value=" " /> <c:out
				value="${appDetail.cmnLookDesc}" /> <fmt:message key="IC.PerOn1"
				bundle="${CapLabels}" /></u></strong> </font>
		<tr height="20"></tr>
	</table>
--><hdiits:fieldGroup id="personDtls" titleCaptionId="IC.PerOn" bundle="${CapLabels}" collapseOnLoad="false">
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
</hdiits:fieldGroup>
	<input type="hidden" name="inchrgReqId" value="${inchrgReqId}" />
	<!--<table align="center" width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr bgcolor="#386CB7" align="center">
			<td class="fieldLabel"><font color="#ffffff"> <strong><u>
			<fmt:message key="IC.HstryDtl" bundle="${CapLabels}" /> </u></strong> </font></td>
		</tr>
	</table> 
	-->
<hdiits:fieldGroup id="prevInchargeDtls" titleCaptionId="IC.HstryDtl" bundle="${CapLabels}" collapseOnLoad="false">
	<table id="HstInchgDetail" align="center" border="1" width="100%"
		style="border-collapse: collapse;  background-color:${tableBGColor}" border=1 borderColor="black">

		<tr class="datatableheader"  style="background-color:${tdBGColor}">
			<td width="10%"  style="background-color:${tdBGColor}"><b><hdiits:caption captionid="IC.SrNo"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="30%"><b><hdiits:caption captionid="IC.post"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="30%"><b><hdiits:caption captionid="IC.inchrgName"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="10%"><b><hdiits:caption captionid="IC.frmDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="10%"><b><hdiits:caption captionid="IC.toDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td width="10%"><b><hdiits:caption captionid="IC.Status"
				bundle="${CapLabels}"></hdiits:caption></b></td>
		
			<c:set var="i" value="1" />
		</tr>

		<c:forEach var="cListItr" items="${hstryLst}">
			<tr align="center">
				<td width="10"><c:out value="${i}" /></td>
				<td width="30%"><c:out value="${cListItr.inchgPostName}" /></td>
				<td width="30%" align="center"><input type='text' id='text${i}'
					name='text${i}' style="border:none; text-align:center; background:#F0F4FB;" value="${cListItr.inchgName}"
					readonly="readonly">
				<td width="10%"  align="center"><input type='text' id='frmdat${i}'
					name='frmdat${i}' style="border:none; text-align:center; background:#F0F4FB; " value="${cListItr.frmDate}"
					readonly="readonly"></td>
				<td width="10%" align="center"><input type='text' id='todat${i}'
					name='todat${i}' style="border:none; text-align:center; background:#F0F4FB; " value="${cListItr.toDate}"
					readonly="readonly"></td>
	            <td width="10%" align="center"  >
	            <c:if   test ="${cListItr.inchgStatus  eq 'Active' }"> 
	            <font color="Green"> <c:out  value="${cListItr.inchgStatus}"/>
	            </font>
	            </c:if>
	            
	             <c:if test ="${cListItr.inchgStatus  eq 'Modify' }"> 
	            <font color="red"> <c:out value="${cListItr.inchgStatus}"/>
	            </font>
	            </c:if>
	            
	              <c:if test ="${cListItr.inchgStatus  eq 'Cancel' }"> 
	            <font color="Blue"> <c:out value="${cListItr.inchgStatus}"/>
	            </font>
	            </c:if>
	            
	            
	            </td>
	             
			</tr>
			<c:set var="i" value="${i+1}" />
		</c:forEach>
	</table>
</hdiits:fieldGroup><!--

	<br>
	<br>
	<table align="center" width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr bgcolor="#386CB7" align="center">
			<td class="fieldLabel"><font color="#ffffff"> <strong><u>
			<fmt:message key="IC.currentHeld" bundle="${CapLabels}" /> </u></strong> </font></td>
		</tr>
	</table>
--><hdiits:fieldGroup id="currInchargeDtls" titleCaptionId="IC.currentHeld" bundle="${CapLabels}" collapseOnLoad="false">
	<table id="currInchgDetail" align="center"  width="100%"
		style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black">

		<tr class="datatableheader"  style="background-color:${tdBGColor}">
			<td width="3%"  style="background-color:${tdBGColor}" ><b><hdiits:caption captionid="IC.SrNo"
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
				<td><c:out value="${i}" /></td>
				<input type="hidden" name="${i}" id="${i}"
					value="${aListItr.hrInchgDtlRlt},${aListItr.inchgPostHeld},${appDetail.frmDate},${appDetail.toDate},${appDetail.applicationName}"} />
				<input type="hidden" name="newInchg${i}" id="newInchg${i}" value=" " />
				<td width="15%"><c:out value="${aListItr.inchgPostName}" /></td>
				<td width="15%"><input type='text' id='txt${i}' name='txt${i}'
					value="${aListItr.inchgName}" readonly="readonly"> <img
					src="images/search_icon.gif"
					onclick="SearchEmpIncharge('txt${i}','${i}')"></td>
				<td width="15%"><hdiits:dateTime validation="txt.isrequired"
					maxvalue="31/12/2099" name="fromdate${i}" captionid="IC.frmDate"
					bundle="${CapLabels}"></hdiits:dateTime>
					 <hdiits:hidden
					name="frmdt"  /> <script>
					document.getElementById('frmdt').value="${aListItr.frmDate}" ;
					document.getElementById('fromdate${i}').value=document.getElementById('frmdt').value;
				</script></td>
				<td width="15%"><hdiits:dateTime validation="txt.isrequired"
					maxvalue="31/12/2099" name="todate${i}" captionid="IC.toDate"
					bundle="${CapLabels}"></hdiits:dateTime></td>
				<hdiits:hidden name="todt"   />
				<script>
				    document.getElementById('todt').value="${aListItr.toDate}";
					document.getElementById('todate${i}').value=document.getElementById('todt').value;
				</script>
				</tr>
					<c:set var="i" value="${i+1}" />
					</c:forEach>
				</table>
</hdiits:fieldGroup>
		<br>
		<br>
		<table align="center" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">

		<hdiits:hidden name="assPostLevel" />
		<hdiits:hidden name="hiddenResVal" />
		<hdiits:hidden name="setVal" id="setVal" />
	</table>



	<hdiits:jsField jsFunction="ValidateandGenerateFile()"
		name="validation" />
	

<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</div>
</div>
</hdiits:form>

</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
