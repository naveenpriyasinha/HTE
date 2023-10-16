<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="attachmentNameList" value="${resValue.attachmentNameList}"></c:set>
<c:set var="lurlList" value="${resValue.lurlList}"></c:set>
<c:set var="serviceLocator" value="${resValue.serviceLocator}" scope="session"></c:set>
<c:set var="langId" value="${resValue.langId}" scope="session"></c:set>
<fmt:formatDate value="${resValue.currentDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="currentDateJs"/>
<fmt:formatDate value="${resValue.catclosuredate}" pattern="dd/MM/yyyy"	dateStyle="medium" var="formatedclodate" />
<script>
var urlstyle = 'toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes';
function standardcomm()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
	window.open('hdiits.htm?actionFlag=viewCommuniqueDtls','',urlStyle);
}
function SendCommuniqueDetails()
{
	var str=document.replycommdtlfrm.elements['rte1'].value; 
	var converted_text=encodeBase64(str);
	converted_text = replace(converted_text,'\n',' ');  
	document.getElementById("communiquedetail").value=converted_text;
	var commnumber='${resValue.commno}'
	var replypost='${resValue.replypost}'
	var loginPost='${resValue.loginPost}'
	
	document.replycommdtlfrm.elements['rte1'].value="";
	
	//var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
	//window.open('hdiits.htm?actionFlag=insertCommuniqueDetails&replyflag=yes&commno='+commnumber+'&replypost='+replypost,'',urlStyle);
	var action="${contextPath}/hdiits.htm?actionFlag=FMS_insertCommuniqueDetails";
	document.getElementById("replycommdtlfrm").method="post";
	document.getElementById("replycommdtlfrm").action=action;	
	
	var category='${resValue.category}';
	
	if(fwdflag=='yes')
	{
		if(document.getElementById("ToListtxt").value.trim()=='')
		{
		alert('<fmt:message key="WF.ToListAlertMsg" bundle="${fmsLables}"></fmt:message>');
		document.getElementById("ToListtxt").value='';
		document.getElementById("ToListtxt").focus();
		}
		else if(document.getElementById("subject").value=='')
		{
		alert('<fmt:message key="WF.SubAlertMsg" bundle="${fmsLables}"></fmt:message>');
		document.getElementById("subject").focus();
		}
		else if(document.getElementById("CatRad").value=='')
		{
		alert("Enter the Category");
		document.getElementById("CatRad").focus();
		}
		
		else
		{
		  document.getElementById("replycommdtlfrm").submit();
		} 
	}
	else
	{		
		if(replypost==loginPost)
		{
			if(confirm("Do you want to reply to same user?"))		
			document.getElementById("replycommdtlfrm").submit();
		}
		else
		{
			document.getElementById("replycommdtlfrm").submit();
		}	
	}	
	
	window.opener.close();
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
function showExpDate()
{
	document.getElementById('expdateid').style.display=''
}
function hideExpDate()
{
	document.getElementById('expdateid').style.display="none"
}
function showHistDetail()
{
	
	var commnumber='${resValue.commno}'
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
	window.open('hdiits.htm?actionFlag=FMS_showCommuniqueHistoryDetail&commno='+commnumber,'',urlStyle);
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
			alert(document.getElementById('CCList').value);
		}
	}
}
</script>

<br>
<c:if test="${resValue.fwdflag eq 'yes'}">
			<center><h3><fmt:message key="WF.FwdComm" bundle="${fmsLables}"></fmt:message></h3></center>
</c:if>
<c:if test="${resValue.fwdflag ne 'yes'}">
			<center><h3><fmt:message key="WF.ReplyComm" bundle="${fmsLables}"></fmt:message></h3></center>
</c:if>


<br>

<hdiits:form name="replycommdtlfrm" method="post" validate="true" encType="multipart/form-data">
<hdiits:hidden name="ToList" />
<hdiits:hidden name="CCList" />
<hdiits:hidden name="currentDate" id="currentDate" default="${currentDateJs}"/>
<hdiits:hidden name="communiquedetail" default="" />
<hdiits:hidden name="communicat"  default=""/>
<hdiits:hidden name="replyflag"  default="yes"/>
<hdiits:hidden name="fwdflag"  default="no"/>
<hdiits:hidden name="commno"  default="${resValue.commno}"/>
<hdiits:hidden name="parentattachmentId"  default="${resValue.attachmentId}"/>
<hdiits:hidden name="replypost"  default="${resValue.replypost}"/>
<hdiits:hidden name="intiatorcommno" default="${resValue.intiatorcommno}" />
<hdiits:hidden name="ToGrpFlag" default="no" />
<hdiits:hidden name="CCGrpFlag" default="no"/>
<hdiits:hidden name="groupflag" />
<input type="hidden" name="postIdArray" />
<input type="hidden" name="empArray" />
<input type="hidden" name="desgnArray" />
<input type="hidden" name="locArray" />

	<table width="100%" border="1" bordercolor="green" >
			<tr>
					<td width="10%"  style="border: none">
							<hdiits:caption  captionid="WF.CommTo" bundle="${fmsLables}"  />
					</td>
					<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
					</td>
					<td width="70%" style="border: none;display: none" id="fwdlist">
							<textarea id="ToListtxct" name="ToListtxt" cols="60" rows="4" readonly="true"  ></textarea>
							<a  href="javascript:openDepartmentSearch('to')"><hdiits:image source="images/workflowImages/send-to-user.gif" /></a>
							<fmt:message key="WF.SearchEmp" bundle="${fmsLables}"></fmt:message>
							<a  href="javascript:openSendToGroup('to')"><hdiits:image source="images/workflowImages/send-to-group.gif" /></a>
							<fmt:message key="WF.SearchGrp" bundle="${fmsLables}"></fmt:message>
			
			
					</td>
					<td width="70%" style="border: none" id="replist">
							<hdiits:text name="txtrep" default="${resValue.replyempName}" readonly="true"/>						
					</td>
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
					<td width="10%"  style="border: none">
						<hdiits:caption  captionid="WF.CommSubject" bundle="${fmsLables}"  />
					</td>
					<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
					</td>
					<td width="70%" style="border: none">
						<hdiits:text name="subject" default="${resValue.subject}"  size="60"/>
						
					</td>
			</tr>		
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
					
					<td width="10%" style="border: none">
						<hdiits:caption captionid="WF.CommDtl" bundle="${fmsLables}"  />
					</td>
					<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
					</td>
					<td width="50%" style="border: none" colspan="4">
							<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp" flush="true">
									<jsp:param name="richTextName" value="1" />
							</jsp:include>
							<hdiits:button name="stdcombtn" value="Standard Comunique" onclick="standardcomm()" type="button"/>						
					</td>
					<td width="20%"  style="border: none;display: none">
							
					</td>
			</tr>	
			<tr>
			</tr>
			<tr>
			</tr>
			<tr style="display: none" id="ccdtl">
						<td width="10%" style="border: none">
							<hdiits:caption captionid="WF.CC" bundle="${fmsLables}"  />
						</td>
						<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
						</td>
						<td width="70%" style="border: none">
								<textarea name="CCListtxt" rows="4" cols="60"  readonly="true" ></textarea>
								<a href="javascript:openDepartmentSearch('cc')"><hdiits:image source="images/workflowImages/send-to-user.gif" /></a>
								<fmt:message key="WF.SearchEmp" bundle="${fmsLables}"></fmt:message>
								<a href="javascript:openSendToGroup('cc')"><hdiits:image source="images/workflowImages/send-to-group.gif" /></a>
								<fmt:message key="WF.SearchGrp" bundle="${fmsLables}"></fmt:message>
						</td>
			</tr>
			<tr style="display: none" id="ccremark">
						<td width="10%" style="border: none">
							<hdiits:caption captionid="WF.CCRem" bundle="${fmsLables}"  />
						</td>
						<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
						</td>
						<td width="70%" style="border: none">
								<hdiits:text name="CCRemark" size="70"  ></hdiits:text>
						</td>
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
					<td width="10%"style="border: none">
						<hdiits:caption  captionid="WF.CommCat" bundle="${fmsLables}"  />
					</td>
					<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
					</td>
					<td width="70%" style="border: none" id="cattext">
						<hdiits:text name="txtcat" default="${resValue.category}" readonly="true" size="30"/>
					</td>
					<td width="70%" style="border: none;display: none" id="catradio">
							<hdiits:radio name="CatRad" id="CatRadId1"   validation="sel.isradio" value="S" caption="Specific" captionid="" onclick="showExpDate()" ></hdiits:radio>
							<hdiits:radio name="CatRad"  id="CatRadId2"  validation="sel.isradio"  value="G" caption="Generic(Information)" captionid="" onclick="hideExpDate()"  ></hdiits:radio>
					</td>
					
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
			
			<tr style="display: none" id="expdateid">
						<td width="10%" style="border: none">
							<hdiits:caption  captionid="WF.ExpCloDate" bundle="${fmsLables}" />
						</td>
						<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
						</td>
						<td width="70%" style="border: none">
								<hdiits:dateTime  name="ClosureDate"   captionid="WF.ExpCloDate" bundle="${fmsLables}"   maxvalue="" default="${resValue.catclosuredate}" ></hdiits:dateTime>
						</td>
			</tr>
			
			<tr>
			</tr>
			<tr>
			</tr> 
			
			<tr>
					<td width="10%"  style="border: none">
						<hdiits:caption captionid="WF.Attachment" bundle="${fmsLables}"  />
					</td>
					<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
					</td>
					<td width="70%" style="border: none" colspan="4">
							<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
							        <jsp:param name="attachmentName" value="CommuniqueAttach" />
								    <jsp:param name="formName" value="replycommdtlfrm" />
									<jsp:param name="attachmentType" value="Document" />   
									<jsp:param name="attachmentTitle" value="CommuniqueAttachment" />   
			           
							</jsp:include> 
							<c:forEach var="attachmentcnt" items="${attachmentNameList}" varStatus="status" >
								<a href="${lurlList[status.index]}"  > <c:out value="${attachmentNameList[status.index]}"> </c:out></a>
								<br>
							</c:forEach>
					</td>
			</tr>	
			
	</table>		
<center>
<center>
<hdiits:button name="sendbtn" captionid="WF.CommSend" bundle="${fmsLables}" type="button" onclick="SendCommuniqueDetails()"/>
<hdiits:button name="histbtn" type="button" captionid="WF.ShowHistory" bundle="${fmsLables}" onclick="showHistDetail()"/>
<hdiits:button name="cancelbtn"  captionid="WF.CommCancel" bundle="${fmsLables}"  type="button"  onclick="self.close()"/></center>				
<center>
</hdiits:form>
<script>
var comm_dtl='${resValue.commdtl}';
var decoded_text=decodeBase64(comm_dtl);
var oRTE1= document.getElementById('rte1').contentWindow.document;
var output = escape(decoded_text);
output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");	
oRTE1.body.innerHTML = unescape(output);	
document.getElementById('hdnrte1').value=oRTE1.body.innerHTML ;
document.getElementById("communicat").value='${resValue.category}'
if(document.getElementById("communicat").value!="Generic")
{
	document.getElementById('expdateid').style.display=''
}

fwdflag='${resValue.fwdflag}';
if(fwdflag=='yes')
{
	document.getElementById('ToList').value="";
	document.getElementById('ccdtl').style.display='';
	document.getElementById('ccremark').style.display='';
	document.getElementById('catradio').style.display='';
	document.getElementById('cattext').style.display='none';
	document.getElementById('replyflag').value="no";
	document.getElementById('fwdflag').value="yes";
	document.getElementById('fwdlist').style.display='';
	document.getElementById('replist').style.display='none';
	var commcat='${resValue.category}';
	if(commcat=="Generic")
	{
		document.getElementById('CatRadId2').checked=true;
	}
	else
	{
	
		document.getElementById('ClosureDate').value='${resValue.catclosuredate}'
		
		document.getElementById('CatRadId1').checked=true;
		document.getElementById('expdateid').style.display=''
	}
	
}
var forwardasnew='${resValue.forwardasnew}';
if(forwardasnew=='yes')
{
	document.getElementById('replyflag').value="no";
	document.getElementById('fwdflag').value="no";
}



</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>