
<%

try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>





<script type="text/javascript"
	src="<c:url value="/script/hrms/gnl/AuditPara/AuditaddRecord.js"/>"></script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>

<fmt:setBundle basename="resources.gnl.AuditPara.AuditParaLables" var="auditLables"
	scope="request" />
<fmt:setBundle basename="resources.gnl.AuditPara.AuditAlertMessages" var="alertLables"
	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DesignationList" value="${resValue.desigList}"></c:set>
<c:set var="ReplyVOList" value="${resValue.ShowDatafrmAudit}"></c:set>
<c:set var="auditId" value="${resValue.auditId}"></c:set>
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<script type="text/javascript">


function ClosePage(frm)
{
frm.action="hdiits.htm?actionFlag=getHomePage";
frm.method="POST";
frm.submit();
}


function getwindow(PARAID)
{

var ParaID=PARAID;
	

var href=' ${rootUrl}'+'?actionFlag=popupParaDetails&paraID='+ParaID;
window.open(href,'chield', 'width=830,height=400,toolbar=yes,minimize=yes,status=yes,menubar=yes,location=no,scrollbars=yes,top=50,left=200');
}

function getwindowForReply(ParaId)
{
	var ParaID=ParaId;
	var href=' ${rootUrl}'+'?actionFlag=popupParaDetails&paraID='+ParaID;
window.open(href,'chield', 'width=830,height=400,toolbar=yes,minimize=yes,status=yes,menubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}

function addDBDataInTable1(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName,checkMethod,ParaNo,ParaID)
	{
			
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName 	== undefined) 
		{
			editMethodName 	 = '';
		}
		if(viewMethodName 	== undefined) 
		{
			viewMethodName 	 = '';
		}
		if(checkMethod 	== undefined) 
		{
			checkMethod 	 = '';
		}
		document.getElementById(tableId).style.display='';
		
		var ParaNO=ParaNo;
		
		var PARAID=ParaID;
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		trow.insertCell(1).innerHTML ="<a href=javascript:void('ParaNO')  onclick=javascript:getwindow('"+PARAID +"')>"+ParaNO+"</a>";
		var len = dispFieldA.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+2).innerHTML = dispFieldA[i];	
		}	
		counter++;
		
		return trow.id;
	}
	
function addDBDataInTable2(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName,checkMethod,ParaNo,ParaID,Status)
	{
			
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName 	== undefined) 
		{
			editMethodName 	 = '';
		}
		if(viewMethodName 	== undefined) 
		{
			viewMethodName 	 = '';
		}
		if(checkMethod 	== undefined) 
		{
			checkMethod 	 = '';
		}
		document.getElementById(tableId).style.display='';
		
		var ParaNO=ParaNo;
		
		var PARAID=ParaID;
		
		var StatusIs=Status;

		if(StatusIs=='Dropped')
		{	
			StatusIs="<fmt:message bundle="${auditLables}" key="HRMS.Dropped"/>";
		}
		else
		{
			StatusIs="<fmt:message bundle="${auditLables}" key="HRMS.Replied"/>";
		}
		
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		trow.insertCell(1).innerHTML ="<a href=javascript:void('ParaNO')  onclick=javascript:getwindow('"+PARAID +"')>"+ParaNO+"</a>";
		var len = dispFieldA.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+2).innerHTML = dispFieldA[i];	
		}	
		trow.insertCell(len+1).innerHTML ="<a href=javascript:void('Status')  onclick=javascript:getwindowForReply('"+PARAID +"')>"+StatusIs+"</a>";
		counter++;
		
		return trow.id;
	}
	
function getAuditId(auditId,fileId)
{
	
	var FILEid=fileId;
	var audiID=auditId;
	getSaveData(audiID,FILEid);
}
function getSaveData(auditID,FileId)
{
	var AUDITID=auditID;
	var FILEID=FileId;
	try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new 
                        ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
		          		try{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  		}
				      	catch (e){
			           	   	  alert("Your browser does not support AJAX!");        
			            	  return false;        
			      		}
			 		}			 
        	}	
			var url = "hrms.htm?actionFlag=getsavedForReply&auditId="+AUDITID+"&fileId="+FILEID;    
			xmlHttp.open("POST", encodeURI(url) , false);			
			xmlHttp.onreadystatechange = processResponse1;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
}
function processResponse1()
{

	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
		
				var xmlStr = xmlHttp.responseText;
				
				
			    var XMLDoc=getDOMFromXML(xmlStr);
			   
			
				 var paraNo1=XMLDoc.getElementsByTagName('paraNo1');
			    
			    var paraDesc1=XMLDoc.getElementsByTagName('paraDesc1');
			  
			    var XmlFilePath1=XMLDoc.getElementsByTagName('XMLFilePath1');
			    
			    var AssignedTo=XMLDoc.getElementsByTagName('AssignedTOName');
			  
			    var Status=XMLDoc.getElementsByTagName('Status');
			    
			    var Reply=XMLDoc.getElementsByTagName('Reply');
			    
			    var ParaID=XMLDoc.getElementsByTagName('ParaId');
			    if(paraNo1.length>0)
			    {
			    	
			    	
			    	for(var j=0;j<paraNo1.length;j++)
			    	{
			    		var	ParaNO2=paraNo1[j].childNodes[0].text;
			    		
			    		var	ParaDESc2=paraDesc1[j].childNodes[0].text;
			    		
			    		var XMLPath2=XmlFilePath1[j].childNodes[0].text;
			    		
			    		var Assigned=AssignedTo[j].childNodes[0].text;
			    		
			    		var StatusIs=Status[j].childNodes[0].text;
			    		
			    		var ReplyBy=Reply[j].childNodes[0].text;
			    		
			    		var paraID=ParaID[j].childNodes[0].text;
			    		
			    		if(StatusIs=='Replied'|| StatusIs=='Dropped' )
			    		{
			    			addDBDataInTable2('AssignedParas','encXML1',new Array(ParaDESc2,Assigned),XMLPath2,'editRecord','deleteRecord','','showchecked',ParaNO2,paraID,StatusIs);
			    		}
			    		else
			    		{
			    			var StatusIs1="<fmt:message bundle="${auditLables}" key="HRMS.SentStatus"/>";
			    			addDBDataInTable1('AssignedParas','encXML1',new Array(ParaDESc2,Assigned,StatusIs1),XMLPath2,'editRecord','deleteRecord','','showchecked',ParaNO2,paraID);
			    		}
			    	}
			    	//document.getElementById('assign').style.display="";
			    }
		}
	}
}	

</script>
<hdiits:form name="ReplyForm" validate="true" method="POST" action="">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"
			bgColor="#386CB7"><b><fmt:message key="HRMS.AuditPara"
			bundle="${auditLables}" /></b></a></li>

	</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>

<hdiits:fieldGroup bundle="${auditLables}" expandable="false" id="ReportDtlsGrp"  titleCaptionId="HRMS.ReportDtls">
	<script>
	var commDate="";
	</script>

	<table width="100%" align="center">
		<tr>
			<td><b><hdiits:caption captionid="HRMS.Designation1" bundle="${auditLables}" /></b></td>
			<td><hdiits:select caption="Designation" id="designation" name="designation" size="1" sort="false" >
			<hdiits:option value="${ReplyVOList.cmnLookupMst.lookupId}">
				${ReplyVOList.cmnLookupMst.lookupDesc}</hdiits:option>
				
	
			</hdiits:select></td>
		</tr>
		<tr>
				<td width="25%"></td>
				<td  width="25%"><b><hdiits:caption captionid="HRMS.LAST_NAME" bundle="${auditLables}"></hdiits:caption></b></td>				
				<td  width="25%"><b><hdiits:caption captionid="HRMS.FIRST_NAME" bundle="${auditLables}"></hdiits:caption></b></td>
				<td  width="25%"><b><hdiits:caption captionid="HRMS.MIDDLE_NAME" bundle="${auditLables}"></hdiits:caption></b></td>
				
			</tr>
			<tr>
				<td  width="25%"><b><hdiits:caption captionid="HRMS.NAME" bundle="${auditLables}"></hdiits:caption></b></td>				
				<td width="25%">
					<hdiits:text name="lastName" id="lastName" captionid="HRMS.LAST_NAME" bundle="${auditLables}" maxlength="25"   readonly="true" default="${ReplyVOList.lnameOfficer}"/>					
				</td>
				<td  width="25%">
					<hdiits:text name="firstName" id="firstName" captionid="HRMS.FIRST_NAME" bundle="${auditLables}"  maxlength="25"   readonly="true" default="${ReplyVOList.fnameOfficer}"/>
				</td>				
				<td width="25%">
					<hdiits:text name="middleName" id="middleName" captionid="HRMS.MIDDLE_NAME" bundle="${auditLables}" maxlength="25" readonly="true" default="${ReplyVOList.mnameOfficer}"/>
				</td>
				
			</tr>
				 <tr>
				<td><b><hdiits:caption captionid="HRMS.OfficerDetails" bundle="${auditLables}" /></b></td>
				 <td><hdiits:text name="OfficerDtls" id="OfficerDtls"  readonly="true" default="${ReplyVOList.otherDtls }" /></td>
			</tr>
		
		
		<tr>
			<td><b><hdiits:caption captionid="HRMS.CommunicationDate" bundle="${auditLables}" /></b></td>
			
			<c:set var="CommuDate" value="${ReplyVOList.dateCommunication }"></c:set>
			<c:if test="${CommuDate!=commDate }">
			<td><b><fmt:formatDate value="${ReplyVOList.dateCommunication}" pattern="dd/MM/yy"/></b></td>
			</c:if>
			<c:if test="${CommuDate==commDate }">
			<td><b><c:out value="------NA------"></c:out></b></td>
			</c:if>
			
			
			<td><b><hdiits:caption captionid="HRMS.ParasCommunicated" bundle="${auditLables}" /></b></td>
			<td><hdiits:text name="parasCommnctd" id="parasCommnctd" default="${ReplyVOList.totalParas }" readonly="true"/></td>
		</tr>
	
		<tr>
			<td>
				<b><hdiits:caption captionid="HRMS.AuditPeriodFrm" bundle="${auditLables}"/></b>
			</td>
			<c:set var="AuditPerdFor" value="${ReplyVOList.auditPeriodFor}"></c:set>
			<c:if test="${AuditPerdFor!=commDate }">
			<td><b><fmt:formatDate value="${ReplyVOList.auditPeriodFor}" pattern="dd/MM/yy"/></b></td>
			</c:if>
			<c:if test="${AuditPerdFor==commDate }">
			<td><b><c:out value="------NA------"></c:out></b></td>
			</c:if>
			<td>
				<b><hdiits:caption captionid="HRMS.AuditPeriodTo" bundle="${auditLables}"/></b>
			</td>
			<c:set var="AuditPerdTo" value="${ReplyVOList.auditPeriodTo}"></c:set>
			<c:if test="${AuditPerdTo!=commDate }">
			<td><b><fmt:formatDate value="${ReplyVOList.auditPeriodTo}" pattern="dd/MM/yy"/></td>
			</c:if>
			<c:if test="${AuditPerdTo==commDate }">
			<td width="30%"><b><c:out value="------NA------"></c:out></b></td>
			</c:if>
		</tr> 
		<tr>
			<td>
				<b><hdiits:caption captionid="HRMS.AuditCndctdFrom"  bundle="${auditLables}"/></b>
			</td>
			<c:set var="AuditCndctdFrm" value="${ReplyVOList.auditConductedFr}"></c:set>
			<c:if test="${AuditCndctdFrm!=commDate }">
			<td><b><fmt:formatDate value="${ReplyVOList.auditConductedFr}" pattern="dd/MM/yy"/></td>
			</c:if>
			<c:if test="${AuditCndctdFrm==commDate }">
			<td width="30%"><b><c:out value="------NA------"></c:out></b></td>
			</c:if>
			<td>
				<b><hdiits:caption captionid="HRMS.AuditCndctdTo" bundle="${auditLables}"/></b>
			</td>
			<c:set var="AuditCndctdTo" value="${ReplyVOList.auditConductedTo}"></c:set>
			<c:if test="${AuditCndctdTo!=commDate }">
			<td><b><fmt:formatDate value="${ReplyVOList.auditConductedTo}" pattern="dd/MM/yy"/></td>
			</c:if>
			<c:if test="${AuditCndctdTo==commDate }">
			<td width="30%"><b><c:out value="------NA------"></c:out></b></td>
			</c:if>
		</tr>
		
		<tr>
			<td>
				<b><hdiits:caption captionid="HRMS.Remarks1" bundle="${auditLables}"/></b>
			</td>
			<td>
				<hdiits:textarea name="remarks" caption="Remarks" cols="35" rows="3"  readonly="true"/>
			</td>
		</tr>
</table>
</hdiits:fieldGroup>
<script>

//gettingDates("${ReplyVOList.dateCommunication }","${ReplyVOList.auditPeriodFor }","${ReplyVOList.auditPeriodTo }","${ReplyVOList.auditConductedFr }","${ReplyVOList.auditConductedTo }");
var remrks="${ReplyVOList.remarks }";
document.getElementById('remarks').value=remrks;
</script>


<hdiits:fieldGroup bundle="${auditLables}" expandable="true" id="AssgndParasGrp"  titleCaptionId="HRMS.assgndParas">
	
<table width="100%" border="1" id="AssignedParas" style="border-collapse: collapse;display:none" borderColor="BLACK">
	<tr>
		<!--<td class="fieldLabel" bgcolor="lightblue" width="1%"></td>-->
		<!--  <td class="fieldLabel" bgcolor="lightblue" width="10%"><b><hdiits:caption captionid="HRMS.SrNo" bundle="${auditLables}" /></b></td>-->
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.ParaNo" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.Descriptions" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.AssignedTo" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.Status" bundle="${auditLables}" /></b></td>
	</tr>
</table>
</hdiits:fieldGroup>
<center>
<!--   <hdiits:button name="Close" type="button" value="Close" captionid="HRMS.Close" bundle="${auditLables}" onclick="ClosePage(document.ReplyForm);"/>  -->
</center>
<script>
	getAuditId('${auditId}','${fileId}');
	

</script>

<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
</div>
</div>
<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
		</hdiits:form>
		<script type="text/javascript">
	initializetabcontent("maintab")
	</script>

		<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>