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

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script>

window.opener=parent.window.opener
if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
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
<c:set var="srNo" value="${1}" />
<c:set var="counter" value="${0}" />
<div id="posthierdiv">

	<c:if test="${not empty HierarchyDetails}">
			<center ><font size="3" color="gray"><b><fmt:message bundle="${fmsLables}" key="WF.MSGPOSTHRCHY"></fmt:message></b></font>
			</center>
			
			<display:table list="${HierarchyDetails}" pagesize="12" requestURI="" id="row"  style="width:100%">
				<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio"  name="postSelect"  value="${row.postId},${row.empName}" onclick="showVal('normal','${row.empName}','${row.designationName}')"> </display:column>
				<display:column  titleKey="WF.Post" headerClass="datatableheader">${row.postName}</display:column>
				<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader">${row.empName}</display:column>
				<display:column  titleKey="WF.DESGNAME" headerClass="datatableheader">${row.designationName}</display:column>
				<display:column  titleKey="WF.LOCNAME" headerClass="datatableheader">${row.locationName}</display:column>
				<c:set var="counter" value="${counter+1}" />
				<c:set var="srNo" value="${srNo+1}" />
			</display:table>
	</c:if>
</div>	
	<c:if test="${empty HierarchyDetails}">
		<fmt:message key="WF.NORCRD" bundle="${fmsLables}"/>
	</c:if>
<br>
	<div id="markedhierdiv">
	<c:if test="${not empty MarkedHierarchyCustomList}">
	 <script type="text/javascript">
	 window.opener.document.getElementById("isDocAlreadyMarked").value = 'yes';
	 </script>
	 
			<center><font size="3" color="gray"><b><fmt:message bundle="${fmsLables}" key="WF.MSGMARKEDHRCHY"></fmt:message></b></font>
			</center>
			<display:table list="${MarkedHierarchyCustomList}" pagesize="12" requestURI="" id="row"  style="width:100%">
					<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio"  name="postSelectForMarked"  value="${row.postId},${row.empName}" onclick="showVal('marked','${row.empName}','${row.designationName}')"> </display:column>
					<display:column  titleKey="WF.POSTNAME" headerClass="datatableheader">${row.postName}</display:column>
					<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader">${row.empName}</display:column>
					<c:set var="counter" value="${counter+1}" />
					<c:set var="srNo" value="${srNo+1}" />
			</display:table>
			
	</c:if>
	</div>
	<table style="display: none" id="corrMvmntTable">
		<tr>
			<td>
				<br><br>
			
				<IMG src="themes/defaulttheme/images/workflow/pc2.png">
				<br>
			
			
				<b><label>${resValue.fromEmpName}</label></b>
				</br>
				<b><label>${resValue.fromDesgName}</label></b>
			</td>
			
			<td width="300" valign=middle>
			<Marquee id="marq" loop=-1 scrollamount=5 direction=right>
			<IMG align=middle src="themes/defaulttheme/images/workflow/file1.png" border="0">
			</Marquee>
			</td>
			
			<td align=center>
			<br><br>
			<IMG src="themes/defaulttheme/images/workflow/pc2.png">
			<br>
			
			<b><label id="lbltoemp"></label></b>
			</br>
			<b><label id="lbltodesg"></label></b>
			</td>
		</tr>
	</table>
	
	<center>
		<hdiits:button name="cancelbtn"  id="cancelbtn" captionid="WF.Close" bundle="${fmsLables}" type="button" onclick="self.close()"/>
	</center>
</hdiits:form>
<script language="javascript">
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
function showVal(decisionFlag,toempname,todesgname)
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
					
					if(confirm(msg+empName+appendmsg+" ?"))
					{

						
						
						
						//document.getElementById('header').style.display='none';
						
						
						window.opener.parent.document.forms[0].method="post";
						if(window.opener.parent.document.forms[0].DecisionFlag.value == 'corr')
						{

							//window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=forwardCorrespondence";
							
							document.getElementById('lbltoemp').innerText=toempname;
							document.getElementById('lbltodesg').innerText="( "+todesgname+")";
							window.opener.document.getElementById('fromwithinHiearchy').value="yes";
							document.getElementById('corrMvmntTable').style.display='';
							document.getElementById('posthierdiv').style.display='none';
							document.getElementById('markedhierdiv').style.display='none';
							document.getElementById('cancelbtn').style.display='none';
							window.scroll(0,0);
							window.resizeTo(650,300);
							window.opener.forward();
						
						}
						else if(window.opener.parent.document.forms[0].DecisionFlag.value == 'file')
						{
							document.getElementById('lbltoemp').innerText=toempname;
							document.getElementById('lbltodesg').innerText="( "+todesgname+")";
							//window.opener.document.getElementById('fromwithinHiearchy').value="yes";
							document.getElementById('corrMvmntTable').style.display='';
							document.getElementById('posthierdiv').style.display='none';
							document.getElementById('markedhierdiv').style.display='none';
							document.getElementById('cancelbtn').style.display='none';
							window.scroll(0,0);
							window.resizeTo(650,300);
							
							window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=forwardFile";
							//window.opener.document.getElementById("isMark").value="yes";
							window.opener.document.getElementById("Converted_text").value = '';
							if(action=='forward')
								window.opener.forward();
							else
								window.opener.returnDownDoc();
							
						//	window.close();
						}
						
						
						
					}
					else
					{
						return false;
					}				
					
				}
			}
}
 
</script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>