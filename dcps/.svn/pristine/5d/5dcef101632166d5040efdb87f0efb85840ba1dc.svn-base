<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lurlList" value="${resValue.lurlList}"></c:set>
<c:set var="serviceLocator" value="${resValue.serviceLocator}" scope="session"></c:set>
<c:set var="langId" value="${resValue.langId}" scope="session"></c:set>
<c:set var="attachmentNameList" value="${resValue.attachmentNameList}"></c:set>

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
	var str=document.draftdtlfrm.elements['rte1'].value; 
	var converted_text=encodeBase64(str);
	converted_text = replace(converted_text,'\n',' ');  
	document.getElementById("communiquedetail").value=converted_text;
	document.draftdtlfrm.elements['rte1'].value="";
	
	var action="${contextPath}/hdiits.htm?actionFlag=FMS_insertDraftDetail";
	document.getElementById("draftdtlfrm").method="post";
	document.getElementById("draftdtlfrm").action=action;					
	document.getElementById("draftdtlfrm").submit();
	
	//window.close();
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
function openSendToGroup(src)
{
var urlstyle = 'toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes';
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
</script>

<br>
<center><h3><fmt:message key="WF.DftDetail" bundle="${fmsLables}"></fmt:message></h3></center>
<br>

<hdiits:form name="draftdtlfrm" method="post" validate="true" encType="multipart/form-data">
<hdiits:hidden name="communiquedetail" default="" />
<hdiits:hidden name="commno"  default="${resValue.commno}"/>
<hdiits:hidden name="parentattachmentId"  default="${resValue.attachmentId}"/>
<hdiits:hidden name="ToList"  default="${resValue.to_list}" />
<hdiits:hidden name="CCList"  default="${resValue.cc_post_list}"/>
<hdiits:hidden name="ToGrpFlag" default="no" />
<hdiits:hidden name="CCGrpFlag" default="no"/>
<hdiits:hidden name="groupflag" />

	<table width="100%" border="1" bordercolor="green" >
			<tr>
			
					<td width="10%" style="border: none">
							<hdiits:caption  captionid="WF.CommTo" bundle="${fmsLables}"  />
					</td>
					<td align="right" style="border: none">
							<hdiits:caption captionid="" caption=":"  />
					</td>
					<td width="70%" style="border: none">
							<textarea id="ToListtxt" name="ToListtxt" cols="70" rows="4"  readonly="true"  ></textarea>
							<a  href="javascript:openDepartmentSearch('to')"><hdiits:image source="images/workflowImages/send-to-user.gif" /></a>
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
								<hdiits:text name="subject" default="${resValue.subject}" readonly="true" size="60"/>
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
							<hdiits:button name="stdcombtn" value="Standard Comunique" onclick="standardcomm()" type="button"/>	
						</td>
						<td width="20%"  style="border: none" >
						
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
							<textarea id="CCListtxt" name="CCListtxt" cols="70" rows="4" default="${resValue.cc_post_list}" ></textarea>
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
							<hdiits:textarea name="CCRemark"  rows="4" cols="70" default="${resValue.cc_remark}" ></hdiits:textarea>
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
						<hdiits:radio name="CatRad" id="CatRadId1"   validation="sel.isradio" value="S" caption="Specific" captionid="" onclick="showExpDate()" ></hdiits:radio>
							<hdiits:radio name="CatRad"  id="CatRadId2"  validation="sel.isradio" value="G" caption="Generic(Information)" captionid="" onclick="hideExpDate()"  ></hdiits:radio>
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
							<hdiits:dateTime  name="ClosureDate"  captionid="WF.ExpCloDate" bundle="${fmsLables}" validation="txt.isrequired" mandatory="true" showCurrentDate="true" maxvalue=""></hdiits:dateTime>
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
							    <jsp:param name="formName" value="draftdtlfrm" />
								<jsp:param name="attachmentType" value="Document" />   
								<jsp:param name="attachmentTitle" value="CommuniqueAttachment" />   
			            	</jsp:include>  
			            	<br>
							<c:forEach var="attachmentcnt" items="${attachmentNameList}" varStatus="status" >
								<a href="${lurlList[status.index]}"  > <c:out value="${attachmentNameList[status.index]}"> </c:out></a>
								<br>
							</c:forEach>
					</td>
			</tr>
			
			
			
	</table>		
<center>
<hdiits:button name="sendbtn" type="button" captionid="WF.CommSend" bundle="${fmsLables}" onclick="SendCommuniqueDetails()"/>
<hdiits:button name="cancelbtn" type="button" captionid="WF.CommCancel" bundle="${fmsLables}" onclick="self.close()"/>				
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


var comm_cat='${resValue.category}';
if(comm_cat=='G')
{
	document.getElementById('CatRadId2').checked=true;
}
else
{
	document.getElementById('CatRadId1').checked=true;	
}

document.getElementById('ToListtxt').value='${resValue.to_emp_list}';
document.getElementById('CCListtxt').value='${resValue.cc_emp_list}';
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>