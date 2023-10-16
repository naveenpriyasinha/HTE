
<% try {  %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="reminderMst" value="${resValue.reminderMst}"></c:set>
<c:set var="postId" value="${resValue.postId}"></c:set>
<c:set var="srtFireDate" value="${resValue.srtFireDate}"></c:set>
<fmt:formatDate value="${reminderMst.startDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="startDate"/>
<fmt:formatDate value="${reminderMst.endDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="endDate"/>

<c:set var="UserList" value="${resValue.User}"></c:set>

<script>
var urlstyle = 'toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes';
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
function insertreminder()
{
	
	var str=document.createreminder.elements['rte1'].value;
	
	var converted_text=encodeBase64(str);
	
	converted_text = replace(converted_text,'\n',' ');
	
	document.createreminder.elements['rte1'].value=''
	
	document.getElementById("reminderdetail").value=converted_text;
	
	var action="${contextPath}/hdiits.htm?actionFlag=FMS_insertReminderDetails";
	document.getElementById("createreminder").method="post";
	document.getElementById("createreminder").action=action;	
	document.getElementById("createreminder").submit();
}
function updatereminder()
{
	var str=document.createreminder.elements['rte1'].value;
	
	var converted_text=encodeBase64(str);
	
	converted_text = replace(converted_text,'\n',' ');
	
	document.createreminder.elements['rte1'].value=''
	
	document.getElementById("reminderdetail").value=converted_text;
	var action="${contextPath}/hdiits.htm?actionFlag=FMS_updateReminderDetails";
	document.getElementById("createreminder").method="post";
	document.getElementById("createreminder").action=action;	
	document.getElementById("createreminder").submit();
}

function checkEndDate()
{
	
	var stDt=document.getElementById('startDate').value;
	var endDt=document.getElementById('endDate').value;
	
	if(endDt<stDt){
		alert('start date is more than end date');
		document.getElementById('endDate').focus();
	}
}

</script>


<hdiits:form name="createreminder" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="jobSeqId" default="${resValue.JobSeqId}" />
<hdiits:hidden name="groupflag" />
<hdiits:hidden name="reminderId" default="${resValue.reminderMst.srNo}" />

<hdiits:hidden name="ToList" default="${resValue.toPostIdLst}"/>
<hdiits:hidden name="CCList"  default="${resValue.ccPostIdLst}" />
<hdiits:hidden name="ToGrpFlag" default="no" />
<hdiits:hidden name="CCGrpFlag" default="no"/>
<hdiits:hidden name="reminderdetail"/>

<input type="hidden" name="postIdArray" />
<input type="hidden" name="empArray" />
<input type="hidden" name="desgnArray" />
<input type="hidden" name="locArray" />
<br>

<br>
<center><h3><fmt:message key="Create Reminder" ></fmt:message></h3></center>

<table width="100%" border="1" bordercolor="green">
	
	<tr>
		<td >
		<hdiits:caption captionid="WF.Post"  bundle="${fmsLables}"/>
		</td>
		<td></td>
		<td>
		<hdiits:select name="Post" caption="Post" id="Post" mandatory="true" validation="sel.isrequired">
			<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"/></hdiits:option>
					<c:forEach var="User" items="${UserList}">
						
						<c:if test="${postId.postId eq User.orgPostMst.postId}">
								<option value='<c:out value="${User.orgPostMst.postId}"/>' selected="selected"> 
								<c:out value="${User.postShortName}"/></option>
						</c:if>	
						<c:if test="${postId.postId ne User.orgPostMst.postId}">
								<option value='<c:out value="${User.orgPostMst.postId}"/>' > 
								<c:out value="${User.postShortName}"/></option>
						</c:if>						
					</c:forEach>							
		</hdiits:select>
		</td>
		
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CommTo" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none" >
			<hdiits:textarea id="ToListtxt" name="ToListtxt" cols="70" rows="3"  readonly="true"  default="${reminderMst.toPostList}"></hdiits:textarea>
			<a  href="javascript:openDepartmentSearch('to')" ><hdiits:image source="images/workflowImages/send-to-user.gif" /></a>
			<fmt:message key="WF.SearchEmp" bundle="${fmsLables}"></fmt:message>
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
			<hdiits:textarea id="CCListtxt" name="CCListtxt" cols="70" rows="3" readonly="true"   default="${reminderMst.ccPostList}"></hdiits:textarea>
				<a href="javascript:openDepartmentSearch('cc')"><hdiits:image source="images/workflowImages/send-to-user.gif" /></a>
				<fmt:message key="WF.SearchEmp" bundle="${fmsLables}"></fmt:message>				
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
				<hdiits:text name="subject" size="40"  mandatory="true" default="${reminderMst.remSubject}"></hdiits:text>
		</td>
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="" caption="reminder description" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="50%" style="border: none">
			<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp" flush="true">
				<jsp:param name="richTextName" value="1" />
			</jsp:include>
			
		</td>
		
	</tr>
	
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  caption="TimeBound" captionid="" bundle="${fmsLables}" />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
		<c:if test="${reminderMst ne null}">
			<hdiits:radio name="CatRad" id="CatRadId1"  value="Y" caption="Yes" captionid="" default="${reminderMst.strTimeBound}"></hdiits:radio>
		</c:if>	
		<c:if test="${reminderMst eq null}">
			<hdiits:radio name="CatRad" id="CatRadId1"  value="Y" caption="Yes" captionid="" default="Y"></hdiits:radio>
		</c:if>	
			<hdiits:radio name="CatRad"  id="CatRadId2"  value="N" caption="No" captionid=""  ></hdiits:radio>
		</td>
	</tr>
	<tr>
		<td>
			<hdiits:caption  caption="StartDate:" captionid="" bundle="${fmsLables}" />
		</td>
		<td></td>		
		<td width="15%">
				<hdiits:dateTime  name="startDate"  caption="StartDate" captionid="" bundle="${fmsLables}"  maxvalue=""  default="${reminderMst.startDate}"></hdiits:dateTime>
		</td>
		
		
	</tr>
	<tr>
		<td >
			<hdiits:caption  caption="EndDate:" captionid="" bundle="${fmsLables}" />
		</td>	
		<td></td>	
		<td width="15%">
				<hdiits:dateTime  name="endDate"  caption="EndDate"  captionid="" bundle="${fmsLables}"  maxvalue="" onblur="javascript:checkEndDate();" default="${reminderMst.endDate}"></hdiits:dateTime>
		</td>
	</tr>
	
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="" caption="Fire Time" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
				<hdiits:time  name="FireTime"  caption="Fire Time"  captionid=""  bundle="${fmsLables}" default="${srtFireDate}" ></hdiits:time>
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
				<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp" >
				        <jsp:param name="attachmentName" value="reminderAttach" />
					    <jsp:param name="formName" value="createreminder" />
						<jsp:param name="attachmentType" value="Document" />   
						<jsp:param name="attachmentTitle" value="Reminder Attachment" />   
			           
				</jsp:include> 
		</td>
	</tr>
</table>

<center>
<c:if test="${reminderMst eq null}">
<hdiits:button name="sendbtn" captionid=""  caption="Creste Reminder" bundle="${fmsLables}" type="button" onclick="insertreminder()"/>
</c:if>

<c:if test="${reminderMst ne null}">
<hdiits:button name="updatebtn" captionid=""  caption="Update Reminder" bundle="${fmsLables}" type="button" onclick="updatereminder()"/>
</c:if>
<hdiits:button name="cancelbtn" captionid="WF.Close" bundle="${fmsLables}" type="button" onclick="self.close()"/>				
</center>


</hdiits:form>

<script>

	var oRTE1= document.getElementById('rte1').contentWindow.document;		               		
	var string='${reminderMst.remDescription}';	
	var decoded_text=decodeBase64(string);
oRTE1.body.innerHTML =decoded_text; 
</script>
<% }
catch(Exception ex)  {
	ex.printStackTrace();	
}	
%>