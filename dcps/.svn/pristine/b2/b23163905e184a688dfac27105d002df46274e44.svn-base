
<%
try {
%>
<script language="javascript">
	var whichLinkClicked=true;   // variable declared for checking if basic info tab clicked or not
	var allNotings = new Array();
</script>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.tcs.sgv.fms.valueobject.FmsCorrNotings"%>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/treeScript/menu.js"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>"></script>
<script type="text/javascript" src="script/common/mainNavJs.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="filestagelist" value="${resultMap.filestagelist}"></c:set>
<c:set var="filestatuslist" value="${resultMap.filestatuslist}"></c:set>
<c:set var="Prioritylist" value="${resultMap.Prioritylist}"></c:set>
<c:set var="Confidentialitylist" value="${resultMap.Confidentialitylist}"></c:set>
<c:set var="AttachmentNameList" value="${resultMap.AttachmentNames}"></c:set>
<c:set var="users" value="${resultMap.OrgUsers}"></c:set>
<c:set var="Notings" value="${resultMap.Notings}"></c:set>
<c:set var="DocList" value="${resultMap.WfDocMstList}"></c:set>
<c:set var="CorrVO" value="${resultMap.FmsCorrMst}"></c:set>
<c:set var="urlval" value="${resultMap.url}"></c:set>
<c:set var="LoginUserPostName" value="${resultMap.LoginUserPostName}"></c:set>
<c:set var="LoginUserPost" value="${resultMap.PostDetailsList}"></c:set>
<c:set var="LoginUserLoc" value="${resultMap.LoginUserLoc}"></c:set>
<c:set var="CmnLocationMstForCrtLoc" value="${resultMap.CmnLocationMstForCrtLoc}"></c:set>
<c:set var="tabclickcnt" value="${resultMap.tabclickcnt}"></c:set>
<c:set var="viewInfoTableflag" value="${resultMap.viewInfoTableflag }"></c:set>
<c:set var="postIdtoReturn" value="${resultMap.postIdtoReturn }"></c:set>
<c:set var="LoginEmployee" value="${resultMap.LoginEmployee }"></c:set>
<c:set var="aclElementMstList" value="${resultMap.aclElementMstList}" scope="session"></c:set>
<c:set var="noOfLevelsInMenu" value="${resultMap.noOfLevelsInMenu}" scope="session"></c:set>
<c:set var="hideMenuLookupID" value="${resultMap.hideMenuLookupID}"></c:set>
<c:set var="disableflag" value="${resultMap.disableflag}"></c:set>
<c:set var="sendascorr" value="${resultMap.sendascorr}"></c:set>


<fmt:formatDate value="${CorrVO.dueDate}" pattern="dd/MM/yyyy"	dateStyle="medium" var="formateduedate" />
<fmt:formatDate value="${CorrVO.dueDate}" pattern="MM/dd/yyyy"	dateStyle="medium" var="formateddate" />
<fmt:formatDate value="${CorrVO.crtDate}" pattern="MM/dd/yyyy"	dateStyle="medium" var="corrcreateddate" />
<fmt:formatDate value="${resultMap.currentDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="currentDateJs"/>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<%
		ResultObject result = (ResultObject) request.getAttribute("result");
		Map resultMap = (Map) result.getResultValue();
	
		ArrayList users = (ArrayList) resultMap.get("OrgUsers");
		
		int notecnt = users.size() + 1;
		
		List date = new ArrayList((HashSet) resultMap.get("CrtDate"));
		 String langId=resultMap.get("langId").toString();
		 session.setAttribute("langId",langId);
		session.setAttribute("serviceLocator",resultMap.get("serviceLocator"));
		
		long corrId = Long.parseLong(resultMap.get("corrId").toString());
	
		int counter_div = 1;
		int counter = 0;
		int notingCounter = 0;
		int note_type_cnt = 0, usercount = 0, datecounter = 0, richTextCounter = 1, attachmentCounter = 1;
		String newValue = "DocumentAttachment" + attachmentCounter++, user = null;
		boolean flag = false;
		
%>

<hdiits:form name="sendascorrfrm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<input type="hidden" name="conutMn" value="1" />
<input type="hidden" name="RichTextVals" />
<input type="hidden" name="RichTextObj" />
<input type="hidden" name="note_type" />
<input type="hidden" name="Converted_text" />
<input type="hidden" name="note_counter" value="<%=notecnt%>" />
<hdiits:hidden name="corrId" default="${CorrVO.corrId}" />
<hdiits:hidden name="PKval" default="${resultMap.pkval}" />
<hdiits:hidden name="currentDate" id="currentDate" default="${currentDateJs}"/>
<script type="text/javascript">
if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
} 
showProgressbar();
var t1=new Date();
var duedate;
var currdate=t1.getDate()  + "/" + eval(t1.getMonth()+1) + "/" + t1.getFullYear() ;
var urlstyle = 'width=500,height=300,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
var flag="true";
function setinfotable()
{

		if(document.getElementById('infotable').style.display=="none")
		{		
				document.getElementById('infotable').style.display='';
				document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.HideCorrInfo" bundle="${fmsLables}"></fmt:message>';
				
		}
		else
		{
			document.getElementById('infotable').style.display='none';
			document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.ShowCorrInfo" bundle="${fmsLables}"></fmt:message>';
		}
}
function closeParent()
{
	if(confirm('<fmt:message key="WF.SaveMsg" bundle="${fmsLables}"></fmt:message>'))
	{
			if(submitForm(''))
			{
				window.opener.parent.frames['dataFrame'].src="hdiits.htm?actionFlag=getDocListOfWorkList&docType=-1";
			    window.close();
			
			}
	}
	else
	{
		
			window.opener.parent.frames['dataFrame'].src="hdiits.htm?actionFlag=getDocListOfWorkList&docType=-1";
			window.close();
	}			
}
function Expand() 
{
   divs=document.getElementsByTagName("DIV");
   for (i=0;i<divs.length;i++){
     divs[i].style.display="block";
     key=document.getElementById("x" + divs[i].id);

   }
}

function Collapse()
{
   divs=document.getElementsByTagName("DIV");
   for (i=0;i<divs.length;i++) {
     divs[i].style.display="none";
     key=document.getElementById("x" + divs[i].id);

   }
}
function toggle(item,imgobj)
{	
		var imglast=imgobj.src.substr(imgobj.src.lastIndexOf("/")+1,imgobj.length);	
		if(imglast!="collapse.gif"){	
			imgobj.src="themes/defaulttheme/images/workflow/collapse.gif"
			flag="false";
		}
		else{		
			imgobj.src="themes/defaulttheme/images/workflow/expand.gif" 		
	 		flag="true";
		}	
		arguments.length=1
		if(arguments.length == 1){
	    	
	   		obj=document.getElementById(item);
	   		visible=(obj.style.display!="none");   
	   		key=document.getElementById("x" + item);   
		  		 if (visible){
		    	 obj.style.display="none";
		   		 } 
		   		 else{  
		      		obj.style.display="block";
		  		 }
	    }
	    else{
	    
		   		for (var i = 1; i < arguments.length; i ++)
		   		{
		   			obj=document.getElementById(arguments[i]);
		    		visible=(obj.style.display!="none");
		    		if (!visible)
		   			toggle(arguments[i]);
		  		 }
		}
  
}
function replace(string,text,by)
{
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
function edit(src1,src2)
{
		if(document.sendascorrfrm.elements['PKval'].value==""){
			document.sendascorrfrm.elements['PKval'].value=src2
		}
		else{
				if(confirm("Click ok to save changes else click cancel")){
					submitForm(document.sendascorrfrm.elements['nextButtonid']);
				}
				else{
					var oRTE = document.getElementById('rte1').contentWindow.document;		
					var decoded_text=decodeBase64(src1);
					var output = escape(decoded_text);	
					output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
					output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");					
					oRTE.body.innerHTML = unescape(output);
					return
				}
		}
		
		var oRTE = document.getElementById('rte1').contentWindow.document;		
		var decoded_text=decodeBase64(src1);
		var output = escape(decoded_text);	
		output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
		output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");					
		oRTE.body.innerHTML = unescape(output);
}
function submitForm(src)
{	
	
	
	document.forms[0].RichTextVals.value = ''
	
	
	var action;
		//var currentDate=document.getElementById('currentDate').value);
		var date1=document.getElementById('txtsetDate').value
		
		if(date1 != null && date1 != '')
		{
						var currentDate = document.getElementById('currentDate').value ;
						var result = compareDate(currentDate,date1);
						if(result < 0)
						{
							alert("Please select date which is not earlier then today's date");
							document.forms[0].txtsetDate.focus();
							return false;
						}
						
		}
	
		if(src=='next')	{

				action="${contextPath}/hdiits.htm?actionFlag=FMS_inserCorrNextNotingDetail"
				document.sendascorrfrm.elements['note_type'].value="MN"
				
				
				
				var tillcount=document.sendascorrfrm.elements['conutMn'].value
				
				
				var counter=0;	
				while(counter<tillcount){
					//alert('rte'+counter);
					var str=document.sendascorrfrm.elements['rte'+eval(counter+1)].value 
					//alert(str);
					var converted_text=encodeBase64(str);
					//alert(converted_text);	
					var converted_text = replace(converted_text,'\n',' ');  
				  	document.sendascorrfrm.elements['rte'+eval(counter+1)].value=converted_text;
				  	counter++;
				}		
			  	document.getElementById("sendascorrfrm").method="post";
				document.getElementById("sendascorrfrm").action=action;
				document.getElementById("Converted_text").value = '';
				document.getElementById("sendascorrfrm").submit();
		
	}			
	
}

function priority_onchange()
{
	var x = document.getElementById('PriorityId');
	var days
	var lookupId = x.options[x.selectedIndex].title;
	days = lookupId.substring(0,lookupId.indexOf("#"));
	var priorityvalue = lookupId.substring(parseInt(lookupId.indexOf("#")) + 1);
	
	
	if(priorityvalue=="DateSet"){

			document.getElementById('duedateid2').style.display='';
			document.getElementById('duedateid1').style.display="none";
	}
	else{
			
			
			document.getElementById('duedateid1').style.display='';
			document.getElementById('duedateid2').style.display="none";
	
	}
		
	
	if(priorityvalue!="DateSet")
	{

			var t2=new Date('${corrcreateddate}');
			t2.setDate(t2.getDate()+parseInt(days));
			if(parseInt(t2.getDate()) < 10)
			{
				duedate="0"+t2.getDate();
			}
			else
				duedate=t2.getDate();
			duedate = duedate+ "/";
			if((parseInt(t2.getMonth())+1) < 10)
			{
				duedate=duedate+"0"+eval(t2.getMonth()+1);
			}  
			else
				duedate=duedate+eval(t2.getMonth()+1);
				
			duedate = duedate+ "/" + t2.getFullYear() ;
			 		
			var date1=duedate;
			var currentDate = document.getElementById('currentDate').value ;
			var result = compareDate(currentDate,date1);
			
			
			if(priorityvalue=="Today")
			{			
					document.getElementById('txtduedateid').value=currdate;
					return true;
			}
			else
			{
			
					if(result < 0)
					{
							alert("Duedate must be greater than current date");
							//document.forms[0].txtsetDate.focus();
							return false;
					}
					else
					{
							document.getElementById('txtduedateid').value=duedate;
							return true;
					}	
				
			}		
	}	
	else
	{
		document.getElementById('txtsetDate').value='${formateduedate}';
	}	
	
}
function createCorrReplica(){
	
	var corrID = '${CorrVO.corrId}';
	document.sendascorrfrm.elements['note_type'].value="MN"
	var tillcount=document.sendascorrfrm.elements['conutMn'].value
	//alert(tillcount)
	var counter=0;	
	while(counter<tillcount){
		//alert('rte'+counter);
		var str=document.sendascorrfrm.elements['rte'+eval(counter+1)].value 
		//alert(str);
		var converted_text=encodeBase64(str);
		//alert(converted_text);	
		var converted_text = replace(converted_text,'\n',' ');  
	  	document.sendascorrfrm.elements['rte'+eval(counter+1)].value=converted_text;
	  	counter++;
	}
	document.forms[0].action='${contextPath}/hdiits.htm?actionFlag=fms_replicateCorrVO&corrId='+corrID+"&fromCommonHomePage=${resultMap.fromCommonHomePage}&sendBackTo=${resultMap.sendBackTo}";
	document.forms[0].method='post';
	document.forms[0].submit();
} 
</script>

<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
</jsp:include>
<hdiits:fieldGroup titleCaption="CorrespondenceInformation"  >
<div>
	<center><a href="#" onclick="setinfotable()" id="showFileInfoLink"><u> <fmt:message key="WF.ShowCorrInfo" bundle="${fmsLables}"></fmt:message></u></a></center>
</div>


<!-- <hdiits:button name="button" type="button" value="Reply" onclick="createCorrReplica()"/> -->
<table  style="display:none" id="infotable">

		<tr>
			<td style="border: none"><hdiits:caption captionid="WF.Ref"	bundle="${fmsLables}" /></td>
			<td style="border: none"><hdiits:text name="refNo" size="31" default="${CorrVO.corrNo}" readonly="true" captionid="WF.Ref" bundle="${fmsLables}" /></td>
			<td style="border: none"><hdiits:caption captionid="WF.CorrCat"	bundle="${fmsLables}" /></td>

			<td style="border: none">
			<hdiits:select name="crrCat"mandatory="true" validation="sel.isrequired" captionid="WF.CorrCat" bundle="${fmsLables}" sort="false">
				<option value='<c:out value="${CorrVO.cmnLookupMstByCorrCtgry.lookupId}"/>' selected="true">
				<c:out	value="${CorrVO.cmnLookupMstByCorrCtgry.lookupDesc}" /></option>
			</hdiits:select></td>

			
			<td style="border: none">
			<hdiits:caption captionid="WF.BrSub" bundle="${fmsLables}" />
			</td>
			<c:if test="${sendascorr ne  'yes'}">
			<td style="border: none;"  >
				<hdiits:text name="subtxt" readonly="true" default="${CorrVO.wfDocMst.docName}"/>
			</td>
			</c:if>
			
			<c:if test="${sendascorr eq  'yes'}">
			<td style="border: none;"  >
			<c:choose>
				<c:when test="${not empty  DocList}">
								<hdiits:text name="brsub" readonly="true" default="${CorrVO.wfDocMst.docName}"/>
				</c:when>
			</c:choose>	
			</td>
			</c:if>
		</tr>
		<tr>


			<td style="border: none"><hdiits:caption captionid="WF.Desc" bundle="${fmsLables}" />
			 <hdiits:caption captionid="WF.LtSub" bundle="${fmsLables}" /></td>
			<td style="border: none"><hdiits:textarea name="lettersubDesc"
				cols="35" maxlength="128" default="${CorrVO.corrDesc}" /></td>

			<td><hdiits:caption captionid="WF.Post" bundle="${fmsLables}" />
			</td>
			<td><hdiits:text name="userPost" size="31"
				default="${LoginUserPostName}" readonly="true" captionid="WF.Post"
				bundle="${fmsLables}" /></td>

			<td align="left"><hdiits:caption captionid="WF.LOCNAME"
				bundle="${fmsLables}" /></td>
			<td><hdiits:text name="userLoc" size="31"
				default="${CmnLocationMstForCrtLoc.locName}" readonly="true"
				captionid="WF.LOCNAME" bundle="${fmsLables}" /></td>
		</tr>
		<tr>


	   </tr>
		<tr>
			<td><b><hdiits:caption captionid="WF.Status" bundle="${fmsLables}" /></b></td>
			<td><hdiits:select size="1" name="status" sort="false" id="status">
				<c:forEach items="${filestatuslist}" var="filestatusLookup">
					<c:if test="${filestatusLookup.lookupName eq (CorrVO.cmnLookupMstByCorrStatus.lookupName)}">
						<option value='<c:out value="${filestatusLookup.lookupId}"/>'selected="true" title="${filestatusLookup.lookupName}">
						<c:out	value="${filestatusLookup.lookupDesc}" /></option>
					</c:if>
					<c:if test="${filestatusLookup.lookupName ne (CorrVO.cmnLookupMstByCorrStatus.lookupName)}">
						<option value='<c:out value="${filestatusLookup.lookupId}"/>' title="${filestatusLookup.lookupName}">
						<c:out value="${filestatusLookup.lookupDesc}" /></option>
					</c:if>
				</c:forEach>
			</hdiits:select></td>
			<td style="border: none"><b><hdiits:caption
				captionid="WF.Confidentiality" bundle="${fmsLables}" /></b></td>
			<td><hdiits:select name="Confidentiality" sort="false">
				<c:forEach items="${Confidentialitylist}"
					var="ConfidentialityLookup">
					<c:if
						test="${ConfidentialityLookup.lookupId ne (CorrVO.cmnLookupMstByConfidentiality.lookupId)}">
						<option value='<c:out value="${ConfidentialityLookup.lookupId}"/>'>
						<c:out value="${ConfidentialityLookup.lookupDesc}" /></option>
					</c:if>
					<c:out value="${fmsFileVo.cmnLookupMstByConfidentiality.lookupId}" />
					<c:if
						test="${ConfidentialityLookup.lookupId eq (CorrVO.cmnLookupMstByConfidentiality.lookupId)}">
						<option value='<c:out value="${ConfidentialityLookup.lookupId}"/>'
							selected="true"><c:out
							value="${ConfidentialityLookup.lookupDesc}" /></option>
					</c:if>
				</c:forEach>
			</hdiits:select></td>

			<!-- <td><hdiits:caption captionid="WF.Stage" bundle="${fmsLables}" />
			</td>
			<td><hdiits:select id="stageid" name="stage" sort="false">
				<c:forEach items="${filestagelist}" var="filestagelistLookup">
					<c:if
						test="${filestagelistLookup.lookupId ne (CorrVO.cmnLookupMstByCorrStage.lookupId)}">
						<option value='<c:out value="${filestagelistLookup.lookupId}"/>'>
						<c:out value="${filestagelistLookup.lookupDesc}" /></option>
					</c:if>
					<c:if
						test="${filestagelistLookup.lookupId eq (CorrVO.cmnLookupMstByCorrStage.lookupId)}">
						<option value='<c:out value="${filestagelistLookup.lookupId}"/>'
							selected="true"><c:out
							value="${filestagelistLookup.lookupDesc}" /></option>
					</c:if>
				</c:forEach>

			</hdiits:select></td> -->
			<td width="10%"><hdiits:caption captionid="WF.LtDate" bundle="${fmsLables}" /></td>
			<td width="10%">
			<hdiits:dateTime name="letterDate" captionid="WF.LtDate" bundle="${fmsLables}" validation="txt.isrequired" mandatory="true" default="${CorrVO.corrDated}"></hdiits:dateTime>
			</td>

		</tr>
		<tr>
			<td><b><hdiits:caption captionid="WF.Priority" bundle="${fmsLables}" /></b>
			</td>
				
			<td>
		
				<c:if test="${CorrVO.cmnLookupMstByPriority.lookupShortName ne '5#DateSet'}">
				
						<hdiits:select id="priorityId" name="priority" sort="false"	onchange="priority_onchange()">
						<c:forEach items="${Prioritylist}" var="PriorityLookup">
						
							
						<c:if test="${PriorityLookup.lookupName ne (CorrVO.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>' title="${PriorityLookup.lookupShortName}">
						<c:out value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>
						<c:if test="${PriorityLookup.lookupName eq (CorrVO.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>'title="${PriorityLookup.lookupShortName}" selected="true">
						<c:out	value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>		
							
							
						</c:forEach>
						</hdiits:select>
				</c:if>
				<c:if test="${CorrVO.cmnLookupMstByPriority.lookupShortName eq '5#DateSet'}">
				<hdiits:select id="priorityId" name="priority" sort="false" >
						<c:forEach items="${Prioritylist}" var="PriorityLookup">
						
							
						
						<c:if test="${PriorityLookup.lookupName eq (CorrVO.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>'title="${PriorityLookup.lookupShortName}" selected="true">
						<c:out	value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>		
							
							
						</c:forEach>
				</hdiits:select>		
				</c:if>
				
				</td>
			<td><hdiits:caption captionid="WF.DueDate" bundle="${fmsLables}" />
			</td>

			<td style="border: none" id="duedateid1">
				<hdiits:text id="txtduedateid" name="txtduedate" size="10" readonly="true" captionid="WF.DueDate" bundle="${fmsLables}" /></td>
			
			<td style="border: none;display: none;" id="duedateid2">
				<hdiits:dateTime name="txtsetDate" captionid="WF.DueDate" bundle="${fmsLables}" maxvalue=""/>
			</td>
				
			
		</tr>

</table>
</hdiits:fieldGroup>

<table border="0" width="100%">
				<tr>
					<td>
					<hdiits:button readonly="${disableflag}" type="button" name="nextButton"	id="nextButtonid" captionid="WF.NextNoting" bundle="${fmsLables}" onclick="submitForm('next');" /> 
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td valign="top" width="10%">
					<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp" >
						<jsp:param name="richTextName" value="<%=richTextCounter++%>" />
					</jsp:include>
					</td>
				</tr>
				<tr>
						<td valign="top" width="10%">
						<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp" flush="true">
						<jsp:param name="attachmentName" value="<%=newValue%>" />
						<jsp:param name="formName" value="sendascorrfrm" />
						<jsp:param name="attachmentType" value="Document" />
						
						<jsp:param name="attachmentTitle" value="${Attach}" />
						</jsp:include>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="1" bordercolor="green" cellpadding="2" cellspacing="0">
						<%
						int i = 1, k = 0;
						%>

						<c:forEach var="Noting" items="${Notings}">
						
							<c:set var="note" value="${Noting}"></c:set>
								<%
								System.out.println("test 111111111111111111111111111111");
								if (user == null)
								{
										user = (String) users.get(usercount);
								%>
										<tr>
										<td>
										<img name="noteImage" style="cursor: hand" src="themes/defaulttheme/images/workflow/collapse.gif" onclick="toggle('Firstdiv<%=i++%>',this)"></img> 
												<b> <% out.println((String) users.get(usercount++));
			 										   out.println((String)date.get(datecounter++));
													%>
												</b>
										<div id="Firstdiv<%=counter_div++%>" style="border:thin;"  >
								<%
								 } 
								 else if(!(user.equalsIgnoreCase(users.get(usercount).toString()))) 
								 {
										user = (String) users.get(usercount);
								%>
										</div>
										</td>
										</tr>
																			
						   				<tr>
										<td>
										<img name="noteImage" style="cursor: hand" src="themes/defaulttheme/images/workflow/collapse.gif" onclick="toggle('div<%=i++%>',this)"></img>
												<b><% out.println((String) users.get(usercount++));
 										  			  out.println((String) date.get(datecounter++));
 									   				%>
 								   				</b>
												<div id="div<%=counter_div++%>" style="border:thin ;">
								<%
								}
								else
										usercount++;
								%>


								<table border="0" width="100%" cellspacing="0" cellpadding="2" >
									<tr>
										<td width="10%">
										<script type="text/javascript">	
											var text='${note}';							
											var decoded_text=decodeBase64(text);								
											document.sendascorrfrm.elements['Converted_text'].value=decoded_text								
											var text=document.sendascorrfrm.elements['Converted_text'].value											
										</script> 
								<%
								try
								{
						 			ResultObject resObj;
						 			resObj = (ResultObject) request.getAttribute("result");
						 			Map resValueMap = (HashMap) resObj.getResultValue();
						 			String[] noteTypeList;
						 			String pkval,disableflagvar;
						 			List noteing = new ArrayList();
						 			noteTypeList = (String[]) resValueMap.get("NoteTypeList");
						 			pkval = (String) resValueMap.get("pkval").toString();
						 			List<FmsCorrNotings> corrNotingData = (List<FmsCorrNotings>) resValueMap.get("FmsCorrNotings");
						 			noteing = (List) resValueMap.get("Notings");
						 			disableflagvar=resValueMap.get("disableflag").toString();
						 			request.setAttribute("FmsCorrNotings",corrNotingData);
						 			if (noteTypeList[note_type_cnt++] == "MN" && (disableflagvar.equalsIgnoreCase("false")))
		 							{
		 								flag = true;
 								%> 
						 				<script type="text/javascript">
												document.sendascorrfrm.elements['note_counter'].value=<%=noteing.size()+1%>																		
										</script> 
									<%
 											newValue = "DocumentAttachment"+ attachmentCounter++;
	 								%> 
	 								
				 			 			<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp" flush="true">
										<jsp:param name="richTextName" value="<%=richTextCounter%>" />
										</jsp:include>
										<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp">
												<jsp:param name="attachmentName" value="<%=newValue%>" />
												<jsp:param name="formName" value="sendascorrfrm" />
												<jsp:param name="attachmentType" value="Document" />
												
												<jsp:param name="attachmentTitle" value="${Attach}" />
										</jsp:include>
										
									<%
					 				}
 					 				else
 					 				{
 									%> 
								 			<script type="text/javascript">
														document.writeln(text);
											</script>
 									<%
 									}
 								} 						
								catch (Exception e) 
								{
 										e.printStackTrace();
 								}
 								%>
										</td>
									</tr>
									<tr>
										<td>
										<%if(!flag){%>
										
										<h3>Attachments</h3>
										
										<%}%>
										<%
										if(!flag){										
										
													try
												{
													Hashtable attachmentTable = new Hashtable();
													ResultObject resObj;
													resObj = (ResultObject) request.getAttribute("result");
													Map resValueMap = (HashMap) resObj.getResultValue();
													attachmentTable = (Hashtable) resValueMap.get("AttachmentTable");
													List objAttachmentList = (List) attachmentTable.get("AttachmentUrlList" + k);
													List attachmentNamesList = (List) resValueMap.get("AttachmentNames");
													List<FmsCorrNotings> corrNotingsList = (List) resValueMap.get("FmsCorrNotings");
													if(corrNotingsList.get(notingCounter++).getCmnAttachmentMst() != null) 
													{
														for (int m = 0; m < objAttachmentList.size(); m++)
														{
										%> 
														<hdiits:a href="<%=(String)objAttachmentList.get(m)%>" caption="<%=(String)attachmentNamesList.get(counter++)%>"></hdiits:a>
														<br>
										<%
														}
														k++;
													}
												}
										
												catch(Exception e)
												{
													e.printStackTrace();
												}
										}
 												if(flag)
 												{
 													flag = false;
										%>
													<script>
													
													document.sendascorrfrm.elements['conutMn'].value=<%=richTextCounter%>;
													
													allNotings.push(text);
													</script>
										<%
													richTextCounter++;
												}
												else
												{
										%>
												<script>
														allNotings.push("continue");
												</script>
										<%
												}
										%>													
										</td>
									</tr>
								</table>
						</c:forEach>
					</table>

					</td>
				</tr>
</table>				
</hdiits:form>			
<script>	

var notingintervalid;
function loadNotingContent()
{		

		<%
					System.out.println(" loadNotingContent called ");
				%>	
		var cntForAllNotings = 0;
		var totalnotings='<%=richTextCounter%>';
		totalnotings=totalnotings-1;
		if(document.getElementById('rte'+totalnotings) != null)
		{	
			<%
					System.out.println(" innner if loadNotingContent called ");
				%>	
			for(var iCnt=1; iCnt<parseInt("<%=richTextCounter%>");iCnt++)
			{
				//alert(document.getElementById('rte'+iCnt) + " : " + iCnt + " : " + allNotings[iCnt]);
				if(document.getElementById('rte'+iCnt) != null && iCnt != 1)
				{
					var oRTE1= document.getElementById('rte'+iCnt).contentWindow.document;
					//alert(" in for " + iCnt + " : " + oRTE1 + " : " + allNotings[iCnt]);
					if((allNotings[cntForAllNotings] == undefined) || (allNotings[cntForAllNotings] == "continue") )
					{
					//	alert(" in bried");
						continue;
					}
					var output = escape(allNotings[cntForAllNotings]);
					cntForAllNotings = parseInt(cntForAllNotings)+1;
					output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
					output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");	
						
					if(document.forms[0].RichTextVals.value == '')
					{
						document.forms[0].RichTextVals.value = unescape(output);
						document.forms[0].RichTextObj.value = oRTE1;
					}
					else
					{
						document.forms[0].RichTextVals.value = document.forms[0].RichTextVals.value + "," + unescape(output);
						document.forms[0].RichTextObj.value = document.forms[0].RichTextObj.value + "," + oRTE1;
					}
					
					oRTE1.body.innerHTML = unescape(output);	
					document.getElementById('hdnrte'+iCnt).value=oRTE1.body.innerHTML ;
				}
										
			}
			window.clearInterval(notingintervalid);
		 }	
		 
}
		
		notingintervalid=window.setInterval("loadNotingContent();",2000);
			document.forms[0].RichTextVals.value = '';
			document.forms[0].RichTextObj.value = '';	
		
	document.getElementById('txtduedateid').value= '${formateduedate}';	
</script>		
				
				
				
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>