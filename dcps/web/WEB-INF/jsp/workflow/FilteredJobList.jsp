<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="JobList" value="${resultMap.JobList}"></c:set>
<c:set var="dashBoardVO" value="${resultMap.dashBoardVO}"></c:set>

<%
try
{
%>

<script language="javascript">

	function openDocument(url)
	{
		var urlstyle="location=0,status=0,scrollbars=1,width=screen.availWidth,height=screen.availHeight"
		docWindow = window.open (url,"Document",urlstyle); 
		docWindow.resizeTo(screen.availWidth,screen.availHeight)
		docWindow.moveTo(0,0);
	}
	
</script>

<hdiits:form name="dashBoardInbox" validate="true" method="post" action="./hdiits.htm">
<input type="hidden" name="hiddenJObIds" />
<input type="hidden" name="hiddenDocIds" />
<input type="hidden" name="hiddenHierarchyFlag" />
<hdiits:hidden name="prioritySel" />

	<table width="100%">
		<tr>
			<td align="center">
				<jsp:include flush="true" page="../workflow/DashBoardSearch.jsp">
					<jsp:param name="priority" value="${resultMap.priority}" />
				</jsp:include>
			</td>
		</tr>
	</table>
<br>
<c:set var="serialNumber" value="${1}" />
	<display:table list="${JobList}" id="row" style="width:100%" pagesize="10" requestURI="" defaultsort="2"  defaultorder="ascending">
		<display:setProperty name="paging.banner.placement" value="bottom" />    
		<display:column style="text-align:center" class="tablecelltext"  media="HTML">
			<hdiits:checkbox id="${row.jobRefId}" value="${row.jobRefId}" name="viewJobId" onclick="show('${row.jobRefId}','${row.docId}','${row.hierarchyflag}')" readonly="${row.checked}" />
		</display:column>
		<display:column class="tablecelltext" titleKey="Sr_No" headerClass="datatableheader" sortable="true" value="${serialNumber}" style="text-align: center"></display:column>
		<c:set var="serialNumber" value="${serialNumber+1}" />
		<display:column class="tablecelltext" titleKey="Document_Name" headerClass="datatableheader" >${row.docName}</display:column>
		<display:column class="tablecelltext" titleKey="Ref_Id" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><a href= "javascript:openDocument('${row.docUrl}')" ><b>${row.transactionNumber}</b></a></display:column> 
		<display:column class="tablecelltext" titleKey="Description" sortable="false" headerClass="datatableheader" >${row.docDesc}</display:column>  	
		<fmt:formatDate value="${row.createdDate}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="stmtDate"/>
		<display:column class="tablecelltext" titleKey="From_Usr" sortable="false" headerClass="datatableheader" >${row.fromPostId}</display:column>  	
		<display:column class="tablecelltext" titleKey="WF.RECVDATE" sortable="false" headerClass="datatableheader" >${stmtDate} </display:column>  	
		<display:column class="tablecelltext" titleKey="Location" sortable="false" headerClass="datatableheader" >${row.location}</display:column>  	
		<display:column class="tablecelltext" titleKey="Priority" sortable="false" headerClass="datatableheader" >${row.priority}</display:column>  	
	</display:table>

</hdiits:form>

<script language="javascript">
if(parent.jobId!=null)
{
	for(var i=0;i<parent.jobId.length;i++)
	{
		if(document.getElementById(parent.jobId[i])!=null)
			document.getElementById(parent.jobId[i]).checked=true;
	}
}

function getSearchData()
{
	var url = "hdiits.htm?actionFlag=inboxForDashBoard&moduleName=DashBoard&menuName=forDashBoard&docType="+'${param.docType}'+"&isDue="+'${param.isDue}'+"&dueType="+'${param.dueType}';

	if('${param.attrib}'!='')
	{
		url += "&attrib="+'${param.attrib}';
	}
	
	if('${param.isChild}'!='')
	{
		url += "&isChild="+'${param.isChild}';
	}

	if('${param.brnch}'!='')
		url += "&brnch="+'${param.brnch}';

	if('${param.loc}'!='')
		url += "&loc="+'${param.loc}';

	if('${param.locName}'!='')
		url += "&locName="+'${param.locName}';
	
	if(document.getElementById('comboPriority').selectedIndex!=0)
	{
		var priority = document.getElementById('comboPriority')[document.getElementById('comboPriority').selectedIndex].value;
		document.getElementById('prioritySel').value = priority;
	}
//	alert(url);
	document.getElementById('dashBoardInbox').action = url;
	document.getElementById('dashBoardInbox').submit();
}

function pullJobs()
{		
	if(parent.jobId.length != 0)
	{
		document.getElementById('hiddenJObIds').value = parent.jobId;
		document.getElementById('hiddenDocIds').value = parent.docId;
		document.getElementById('hiddenHierarchyFlag').value = parent.hierarchyflag;
		var url='hdiits.htm?actionFlag=pullJobs&docType=' + '${documentType}';
		parent.jobId.splice(0, parent.jobId.length);
		parent.docId.splice(0, parent.docId.length);
		parent.hierarchyflag.splice(0, parent.hierarchyflag.length);
		parent.count = 0;
		document.forms[0].action=url;
		document.forms[0].submit();
		
	}
	else
	{
		alert("No Job Selected...");
	}

}

function show(fileid, docid, hierarchy)
{	
	if(document.getElementById(fileid).checked==true)
	{
		if(parent.count != 5)
		{
			parent.count++;
			parent.jobId.push(fileid);
			parent.docId.push(docid);
			parent.hierarchyflag.push(hierarchy);
		}
		else
		{
			document.getElementById(fileid).checked = false;
			alert("Five Jobs can be pulled at once...");
		}
	}
	else
	{
		for(var i=0;i<parent.jobId.length;i++)
		{
			if(parent.jobId[i] == fileid)
			{
				parent.count--;
				parent.jobId.splice(i,1);
				parent.docId.splice(i,1);
				parent.hierarchyflag.splice(i,1);
			}
		}
	}
}

document.forms[0].docnames.value='${dashBoardVO.subjectName}';
document.forms[0].fileDesc.value='${dashBoardVO.description}';
document.forms[0].transnotxt.value='${dashBoardVO.transactionNo}';
document.forms[0].RecieveDateFrom.value='${resultMap.fromDate}';
document.forms[0].RecieveDateTo.value='${resultMap.toDate}';
var comboObj = document.getElementById('comboPriority');
for(var i=1; i<comboObj.length; i++)
{
	if(comboObj[i].value=='${dashBoardVO.priority}')
	{
		document.forms[0].comboPriority.selectedIndex=i;
		break;		
	}
}
document.forms[0].empFName.value='${dashBoardVO.senderFirstName}';
document.forms[0].empMName.value='${dashBoardVO.senderMiddleName}';
document.forms[0].empLName.value='${dashBoardVO.senderLastName}';
document.forms[0].agingFrom.value='${dashBoardVO.agingFrom}';
document.forms[0].agingTo.value='${dashBoardVO.agingTo}';

document.getElementById('agerow').style.display='none';
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>