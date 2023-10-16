<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="serviceLocator" value="${resValue.serviceLocator}" scope="session"></c:set>
<c:set var="langId" value="${resValue.langId}" scope="session"></c:set>
<script>

var urlstyle = 'toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes';
function showExpDate()
{
	document.getElementById('expdateid').style.display=''
}
function hideExpDate()
{
	document.getElementById('expdateid').style.display="none"
}
function SendCommuniqueDetails()
{



	if(document.getElementById("ToListtxt").value.trim()=='')
	{
		alert('<fmt:message key="WF.ToListAlertMsg" bundle="${fmsLables}"></fmt:message>');
		document.getElementById("ToListtxt").focus();
	}
	else if(document.getElementById("subject").value=='')
	{
		alert('<fmt:message key="WF.SubAlertMsg" bundle="${fmsLables}"></fmt:message>');
		document.getElementById("subject").focus();
	}
	else if(document.newcommuniquefrm.elements['rte1'].value=='')
	{
		alert('<fmt:message key="WF.CommDtlAlertMsg" bundle="${fmsLables}"></fmt:message>');
		
	}
	else if(document.getElementById("CatRad").value=='')
	{
		alert("Enter the Category");
		document.getElementById("CatRad").focus();
	}
	else if(document.getElementById("CatRad").value=='S')
	{
		var t1=new Date();
		t2=document.getElementById('ClosureDate').value;
		var mm=t1.getMonth();
		mm=mm+1;
		if(mm<10)
		{
			mm="0"+mm;
		}
		var currdate=t1.getDate()+"/"+mm+"/"+t1.getYear();
		var result=compareDate(currdate,t2);
		if(result < 0)
		{
				alert('<fmt:message key="WF.ClosureDateAlertMsg" bundle="${fmsLables}"></fmt:message>');
			
		}
		else
		{
				var str=document.newcommuniquefrm.elements['rte1'].value; 
				var converted_text=encodeBase64(str);
				converted_text = replace(converted_text,'\n',' ');  
				document.newcommuniquefrm.elements['rte1'].value=''
				document.getElementById("communiquedetail").value=converted_text;
				
				var action="${contextPath}/hdiits.htm?actionFlag=FMS_insertCommuniqueDetails";
				document.getElementById("newcommuniquefrm").method="post";
				document.getElementById("newcommuniquefrm").action=action;	
				
				document.getElementById("newcommuniquefrm").submit();
		}
	}
	else
	{
				var str=document.newcommuniquefrm.elements['rte1'].value; 
				var converted_text=encodeBase64(str);
				converted_text = replace(converted_text,'\n',' ');  
				document.newcommuniquefrm.elements['rte1'].value=''
				document.getElementById("communiquedetail").value=converted_text;
				
				var action="${contextPath}/hdiits.htm?actionFlag=FMS_insertCommuniqueDetails";
				document.getElementById("newcommuniquefrm").method="post";
				document.getElementById("newcommuniquefrm").action=action;	
				document.getElementById("newcommuniquefrm").submit();
	}
}

function replace(string,text,by) {
// Replaces text with by in string
    var strLength = string.length, txtLength = text.length;
    if ((strLength == 0) || (txtLength == 0)) return string;

    var i = string.indexOf(text);
    if ((!i) && (text != string.substring(0,txtLength))) return string;
    if (i == -1) return string;

    var newstr = string.substring(0,i) + by;

    if (i+txtLength < strLength)
        newstr += replace(string.substring(i+txtLength,strLength),text,by);

    return newstr;
}
function draftbtn_onlclick()
{
	var str=document.newcommuniquefrm.elements['rte1'].value; 
	var converted_text=encodeBase64(str);
	converted_text = replace(converted_text,'\n',' ');  
	document.newcommuniquefrm.elements['rte1'].value=''
	document.getElementById("communiquedetail").value=converted_text;
	
	var action="${contextPath}/hdiits.htm?actionFlag=FMS_insertCommuniqueDetails&sendasdraft=yes";
	document.getElementById("newcommuniquefrm").method="post";
	document.getElementById("newcommuniquefrm").action=action;					
	document.getElementById("newcommuniquefrm").submit();
}

function openSendToGroup(src)
{
	if(src=="to")
	{
		document.getElementById('groupflag').value='to';
	}
	else if(src=="cc")
	{
		document.getElementById('groupflag').value='cc';
	}
	
	win = window.open('${contextPath}/hdiits.htm?actionFlag=showCommuniqueGroup',"SendCommuniqueToGroup",urlstyle);
	win.resizeTo(screen.availWidth,screen.availHeight)
	win.moveTo(0,0);  
}
function openDepartmentSearch(src)
{
	if(src=="to")
	{
		document.getElementById('groupflag').value='to';
	}
	else if(src=="cc")
	{
		document.getElementById('groupflag').value='cc';
	}
	window.open("${contextPath}/hdiits.htm?actionFlag=DeptSearchData&otherSender=yes",'',urlstyle);
}

function useSelectedEmployees()
{
	if(document.getElementById('postIdArray').value != '')
	{
		var empNameArrTemp = document.getElementById('empArray').value;
		var empNameArr = empNameArrTemp.split(',');
		var desgnArrTemp = document.getElementById('desgnArray').value;
		var desgnArr = desgnArrTemp.split(',');
		if(document.getElementById('groupflag').value == 'to')
		{
			for(var i=0; i<empNameArr.length; i++)
			{
				if(i==0 && document.getElementById('ToListtxt').value != '')
					document.getElementById('ToListtxt').value += ', ';
				document.getElementById('ToListtxt').value += empNameArr[i] + ' (' + desgnArr[i] + ')';
				if(i != empNameArr.length - 1)
					document.getElementById('ToListtxt').value += ', ';
			}
			if(document.getElementById('ToList').value != '')
					document.getElementById('ToList').value += ',';
			document.getElementById('ToList').value += document.getElementById('postIdArray').value;
			
		}
		else
		{
			for(var i=0; i<empNameArr.length; i++)
			{
				if(i==0 && document.getElementById('CCListtxt').value != '')
					document.getElementById('CCListtxt').value += ', ';
				document.getElementById('CCListtxt').value += empNameArr[i] + ' (' + desgnArr[i] + ')';
				if(i != empNameArr.length - 1)
					document.getElementById('CCListtxt').value += ', ';
			}
			if(document.getElementById('CCList').value != '')
					document.getElementById('CCList').value += ',';
			document.getElementById('CCList').value += document.getElementById('postIdArray').value;
			
		}
	}
}
function standardcomm()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
	window.open('hdiits.htm?actionFlag=viewCommuniqueDtls','NewStandardCommunique',urlStyle);
}
</script>
<hdiits:form name="newcommuniquefrm" method="post" validate="true" encType="multipart/form-data">
<hdiits:hidden name="communiquedetail" />
<hdiits:hidden name="ToList" />
<hdiits:hidden name="CCList" />
<hdiits:hidden name="ToGrpFlag" default="no" />
<hdiits:hidden name="CCGrpFlag" default="no"/>
<hdiits:hidden name="fileId" />

<hdiits:hidden name="groupflag" />
<input type="hidden" name="postIdArray" />
<input type="hidden" name="empArray" />
<input type="hidden" name="desgnArray" />
<input type="hidden" name="locArray" />

<br>

<br>
<center><h3><fmt:message key="WF.NewComm" bundle="${fmsLables}"></fmt:message></h3></center>
<br>
<br>
<table width="100%" border="1" bordercolor="green">
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CommTo" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none" >
			<textarea id="ToListtxt" name="ToListtxt" cols="70" rows="4"  readonly="true"  ></textarea>
			<a  href="javascript:openDepartmentSearch('to')" ><hdiits:image source="images/workflowImages/send-to-user.gif" /></a>
			<fmt:message key="WF.SearchEmp" bundle="${fmsLables}"></fmt:message>
			<a  href="javascript:openSendToGroup('to')"><hdiits:image source="images/workflowImages/send-to-group.gif" /></a>
			<fmt:message key="WF.SearchGrp" bundle="${fmsLables}"></fmt:message>
			
			
		</td>
		
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CommSubject" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
				<hdiits:text name="subject" size="40"  mandatory="true"></hdiits:text>
		</td>
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CommDtl" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="50%" style="border: none">
			<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp" flush="true">
				<jsp:param name="richTextName" value="1" />
			</jsp:include>
			<hdiits:button  name="stdcombtn" value="Standard Comunique" onclick="standardcomm()" type="button"/>
		</td>
		
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CC" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
			<textarea id="CCListtxt" name="CCListtxt" cols="70" rows="4" readonly="true" ></textarea>
				<a href="javascript:openDepartmentSearch('cc')"><hdiits:image source="images/workflowImages/send-to-user.gif" /></a>
				<fmt:message key="WF.SearchEmp" bundle="${fmsLables}"></fmt:message>
				<a href="javascript:openSendToGroup('cc')"><hdiits:image source="images/workflowImages/send-to-group.gif" /></a>
				<fmt:message key="WF.SearchGrp" bundle="${fmsLables}"></fmt:message>
		</td>
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CCRem" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
				<hdiits:textarea name="CCRemark" rows="4" cols="70"  ></hdiits:textarea>
		</td>
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CommCat" bundle="${fmsLables}" />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
			<hdiits:radio name="CatRad" id="CatRadId1"  value="S" caption="Specific" captionid="" onclick="showExpDate()"></hdiits:radio>
			<hdiits:radio name="CatRad"  id="CatRadId2"  value="G" caption="Generic(Information)" captionid="" onclick="hideExpDate()"  ></hdiits:radio>
		</td>
	</tr>
	<tr style="display: none" id="expdateid">
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.ExpCloDate" bundle="${fmsLables}" />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
				<hdiits:dateTime  name="ClosureDate"  captionid="WF.ExpCloDate" bundle="${fmsLables}"  showCurrentDate="true" maxvalue=""></hdiits:dateTime>
		</td>
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption   captionid="WF.Attachment" bundle="${fmsLables}" />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="90%" style="border: none">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				        <jsp:param name="attachmentName" value="CommuniqueAttach" />
					    <jsp:param name="formName" value="newcommuniquefrm" />
						<jsp:param name="attachmentType" value="Document" />   
						<jsp:param name="attachmentTitle" value="CommuniqueAttachment" />   
			           
				</jsp:include> 
		</td>
	</tr>
</table>
<BR>
<BR>
<center>
<hdiits:button name="sendbtn" captionid="WF.CommSend" bundle="${fmsLables}" type="button" onclick="SendCommuniqueDetails()"/>
<hdiits:button name="draftbtn" captionid="WF.SaveDraft" bundle="${fmsLables}" type="button" onclick="draftbtn_onlclick()"/>
<hdiits:button name="cancelbtn" captionid="WF.Close" bundle="${fmsLables}" type="button" onclick="self.close()"/>				
<center>



</hdiits:form>

<script>
document.getElementById('CatRadId2').checked=true;
document.getElementById('fileId').value='${resValue.fileId}';
if(document.getElementById('fileId').value!='')
{
	document.getElementById('draftbtn').style.display='none';
}

</script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>

<script>
var oRTE1= document.getElementById('rte1').contentWindow.document;
</script>