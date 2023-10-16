<%
try {
	

%>
 <script type="text/javascript">
 var msg,action;
 </script>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="HierarchyDetails" value="${resValue.HierarchyCustomList}"></c:set>
<c:set var="MarkedHierarchyCustomList" value="${resValue.MarkedHierarchyCustomList}"></c:set>
<c:set var="action" value="${resValue.action}"></c:set>
<c:set var="roleId" value="${resValue.roleId}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="lhierarchyFlag" value="${resValue.hirFlag}"></c:set>
<c:set var="disposeclasslst" value="${resValue.disposeclasslst}"></c:set>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script>

window.opener=parent.window.opener
if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
} 

function HideHierDtlList()
{
	document.getElementById('HierPostLstId').style.display='none';		
}
function ShowHierDtlList()
{
	document.getElementById('HierPostLstId').style.display='';
}
</script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="HierarchyForm" method="POST" validate="true" action="./hdiits.htm">

<c:if test="${action eq 'forward'}">
 <script type="text/javascript">
 msg='<fmt:message key="WF.FWDJOBMSG" bundle="${fmsLables}"/>';
 action='forward';
 </script>
</c:if>

<c:if test="${action eq 'return'}">
 <script type="text/javascript">
 msg='<fmt:message key="WF.RTNJOBMSG" bundle="${fmsLables}"/>';
 action='return';
 </script>
</c:if>

<c:if test="${action eq 'return'}">
 <script type="text/javascript">
 msg='<fmt:message key="WF.RTNJOBMSG" bundle="${fmsLables}"/>';
 action='return';
 </script>
</c:if>

<c:choose>
<c:when test="${langId eq '1'}">
<script type="text/javascript">
 appendmsg = '';
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
 appendmsg = '<fmt:message key="WF.APPENDMSG" bundle="${fmsLables}"/>';
 </script>
</c:otherwise>
</c:choose>

<table width="100%" border="0">
<tr>
	<td>
	<hdiits:caption captionid="WF.DisposeRem" bundle="${fmsLables}"/>
	</td>
	<td>
	<textarea id="txtdisposeremarks" name="txtdisposeremarks" cols="70" rows="4"   ></textarea>
	</td>
</tr>

<tr>
	<td>
		<hdiits:caption captionid="WF.DisposeType" bundle="${fmsLables}" ></hdiits:caption>
	</td>
	<td>
		<hdiits:radio name="disposetype" id="disposetype1" validation="sel.isradio" value="1" default="1" captionid="WF.Positive" bundle="${fmsLables}" ></hdiits:radio>
		<hdiits:radio name="disposetype" id="disposetype2" validation="sel.isradio" value="2" captionid="WF.Negative" bundle="${fmsLables}" ></hdiits:radio>
	</td>
</tr>
<tr>
	<td>
	<hdiits:caption captionid="WF.DisClass" bundle="${fmsLables}"   ></hdiits:caption>
	</td>
	<td>
	
	<hdiits:select name="classification" captionid="WF.DisClass" bundle="${fmsLables}" mandatory="true" validation="sel.isrequired" >
				<hdiits:option value="-1" selected="true">
				<hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
				<c:forEach items="${disposeclasslst}" var="ClassificationLookup">
					<option value='<c:out value="${ClassificationLookup.lookupId}"/>' >
					<c:out value="${ClassificationLookup.lookupDesc}" /></option>
				</c:forEach>
	</hdiits:select>
	</td>
</tr>
<tr>
	<td>
	<hdiits:caption captionid="WF.DisposeAct" bundle="${fmsLables}" ></hdiits:caption>
	</td>
	<td>
	<hdiits:radio name="disposeaction" id="disposeaction1" validation="sel.isradio"  value="" default="" captionid="WF.MoveToRecRoom" bundle="${fmsLables}" onclick="HideHierDtlList()"></hdiits:radio>
	<hdiits:radio name="disposeaction" id="disposeaction2" validation="sel.isradio"  value="" captionid="WF.SendWithHierchy" bundle="${fmsLables}" onclick="ShowHierDtlList()"></hdiits:radio>
	</td>
</tr>



</table>
<table width="100%" border="0">
<tr>
<td>

<div id="HierPostLstId" style="display: none">
<c:set var="srNo" value="${1}" />
<c:set var="counter" value="${0}" />
<c:if test="${not empty HierarchyDetails}">
<center><font size="3" color="gray"><b><fmt:message bundle="${fmsLables}" key="WF.MSGPOSTHRCHY"></fmt:message></b></font></center>
	<display:table list="${HierarchyDetails}" pagesize="12" requestURI="" id="row"  style="width:100%">
		<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio"  name="postSelect"  value="${row.postId},${row.empName}" onclick="showVal('normal')"> </display:column>
		<display:column  titleKey="WF.Post" headerClass="datatableheader">${row.postName}</display:column>
		<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader">${row.empName}</display:column>
		<display:column  titleKey="WF.DESGNAME" headerClass="datatableheader">${row.designationName}</display:column>
		<display:column  titleKey="WF.LOCNAME" headerClass="datatableheader">${row.locationName}</display:column>
		<c:set var="counter" value="${counter+1}" />
		<c:set var="srNo" value="${srNo+1}" />
	</display:table>
</c:if>
<c:if test="${empty HierarchyDetails}">
	<fmt:message key="WF.NORCRD" bundle="${fmsLables}"/>
</c:if>
<br>
<c:if test="${not empty MarkedHierarchyCustomList}">
 <script type="text/javascript">
 window.opener.document.getElementById("isDocAlreadyMarked").value = 'yes';
 </script>
<center><font size="3" color="gray"><b><fmt:message bundle="${fmsLables}" key="WF.MSGMARKEDHRCHY"></fmt:message></b></font></center>
	<display:table list="${MarkedHierarchyCustomList}" pagesize="12" requestURI="" id="row"  style="width:100%">
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio"  name="postSelectForMarked"  value="${row.postId},${row.empName}" onclick="showVal('marked')"> </display:column>
			<display:column  titleKey="WF.POSTNAME" headerClass="datatableheader">${row.postName}</display:column>
			<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader">${row.empName}</display:column>
			<c:set var="counter" value="${counter+1}" />
			<c:set var="srNo" value="${srNo+1}" />
	</display:table>
</c:if>
</div>
</td>
</tr>
</table>
<center>
<hdiits:button name="OKbtn" captionid="WF.Ok" bundle="${fmsLables}"  type="button"  onclick="onclick_OKbtn()"/>
<hdiits:button name="closebtn" captionid="WF.Close" bundle="${fmsLables}"  type="button"  onclick="window.close()"/>
</center>


<script language="javascript">
function onclick_OKbtn()
{

	if(document.getElementById('txtdisposeremarks').value=='')
	{
		alert('<fmt:message key="WF.DisRemAlert" bundle="${fmsLables}"></fmt:message>');
	}
	else if(document.getElementById('classification').value==-1)
	{
		alert('<fmt:message key="WF.SelClassAlert" bundle="${fmsLables}"></fmt:message>');
	}
	else
	{
		window.opener.document.getElementById('disposeremarks').value=document.getElementById('txtdisposeremarks').value;
		window.opener.document.getElementById('disposeclass').value=document.getElementById('classification').value;						
		if(document.getElementById('disposetype1').checked==true)
		{
			window.opener.document.getElementById('disposetype').value=1;
		}
		else
		{
			window.opener.document.getElementById('disposetype').value=2;
		}
		if(document.getElementById('disposeaction1').checked==true)
		{
			window.opener.document.getElementById('disposedFlag').value='yes';
			window.opener.submitForm('')
			window.close();
		}
		else
		{
			alert("Plase select Employee from the list");
		}
	}

}

function createMarkedPostList()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=no'; 	
	window.open('${contextPath}/hdiits.htm?viewName=wf-departmentSrch','',urlStyle);
	window.close();
}
 function selectAll()
		{
			var len = '${srNo}';
			for(var counter=0; counter<eval(len)-1; counter++)
			{
				document.forms[0].postSelect[counter].checked=true;
			}
		}
 
  function deselectAll()
		{
			var len = '${srNo}';
			for(var counter=0; counter<eval(len)-1; counter++)
			{
				document.forms[0].postSelect[counter].checked=false;
			}
		}
	function showVal(decisionFlag)
		{
			var radbutName;
			if(decisionFlag == 'normal'){
				radbutName = 'postSelect';
				window.opener.document.getElementById("isNormalHierachySelected").value = 'yes';
			}
			else{
				radbutName = 'postSelectForMarked';
				window.opener.document.getElementById("isNormalHierachySelected").value = 'no';
			}
			var radioObj = document.getElementsByName(radbutName);
			var radLength = document.getElementsByName(radbutName).length;
			for(var cnt=0;cnt<radLength;cnt++)
			{
				if(radioObj[cnt].checked == true)
				{					
					window.opener.parent.document.forms[0].postId_Hidden.value =radioObj[cnt].value.substring(0,parseInt(radioObj[cnt].value.indexOf(",")));
					

					
					window.opener.parent.document.forms[0].roleId_Hidden.value="${roleId}";
					var empName=radioObj[cnt].value.substring(parseInt((radioObj[cnt].value.indexOf(","))+1),radioObj[cnt].value.length);
					
					if(document.getElementById('txtdisposeremarks').value=='')
					{
							alert('<fmt:message key="WF.DisRemAlert" bundle="${fmsLables}"></fmt:message>');
							return false;
					}
					if(document.getElementById('classification').value==-1)
					{
							alert('<fmt:message key="WF.SelClassAlert" bundle="${fmsLables}"></fmt:message>');
							return false;
					}
					else 
					{
					
						if(confirm(msg+empName+appendmsg+" ?"))
						{
							window.opener.parent.document.forms[0].method="post";
							window.opener.document.getElementById('disposeremarks').value=document.getElementById('txtdisposeremarks').value;
							
							window.opener.document.getElementById('disposeclass').value=document.getElementById('classification').value;
							
							if(document.getElementById('disposetype1').checked==true)
							{
								window.opener.document.getElementById('disposetype').value=1;
							}
							else
							{
								window.opener.document.getElementById('disposetype').value=2;
							}
							if(window.opener.parent.document.forms[0].DecisionFlag.value == 'corr')
							{
								window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=forwardCorrespondence";
								window.opener.forward();
							
							}
							else if(window.opener.parent.document.forms[0].DecisionFlag.value == 'file')
							{
								window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=forwardFile";
								//window.opener.document.getElementById("isMark").value="yes";
								window.opener.document.getElementById("Converted_text").value = '';
								
									
								if(action=='forward')
								{
									
									window.opener.forward();
								}	
								else
								{
									window.opener.returnDownDoc();
								}
							}
							
							
							window.close();
						}
						else
						{
							return false;
						}
					}				
					
				}
			}
		}
 
</script>
</hdiits:form>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>