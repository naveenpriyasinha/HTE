<%
try {
	

%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="PostEmployee" value="${resValue.PostEmployeeList}"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="EmpLocForm" id="EmpLocForm" method="POST" validate="true" action="./hdiits.htm">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	           		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.EmpList" bundle="${fmsLables}"/></a></li>
	</ul>
</div>

<div class="tabcontentstyle"  id="abc">
	<div id="tcontent1" class="tabcontent" tabno="0">
				<center><a  href="javascript:parent.backtoSearchEmp()"><font color="blue" size="3"><fmt:message bundle="${fmsLables}" key="WF.SELOTLOC"></fmt:message></font></a></center>
				<display:table list="${PostEmployee}" pagesize="12" requestURI="" id="row"  defaultsort="1" style="width:100%">
					<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="checkbox" id="${row.postId}" value="${row.postId},${row.empFname} ${row.empMname} ${row.empLname},${row.dsgnName},${row.postName},${row.locPlacename}" name="postEmpSelect" onclick="addEmployee(this)"> </display:column>
					<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader" sortable="true">${row.empFname} ${row.empMname} ${row.empLname}</display:column>
					<display:column  titleKey="WF.LOCNAME" headerClass="datatableheader" sortable="true">${row.locPlacename}</display:column>
					<display:column  titleKey="WF.DESGNAME" headerClass="datatableheader" sortable="true">${row.dsgnName}</display:column>
				</display:table>
				<br>
	
	<CENTER><hdiits:button id="AddEmp_bt" name="AddEmp_bt" captionid="WF.Add" bundle="${fmsLables}" onclick="parent.addEmps()" type="button" style="display:none"/></CENTER>
	</div>
	
</div>
<script type="text/javascript"> 
if("${otherSender}" == "yes")
	document.getElementById('AddEmp_bt').style.display='';

var FramePageHeight = document.getElementById("EmpLocForm").scrollHeight+10;


if(FramePageHeight < 30)
{
	FramePageHeight = 30;
	parent.document.getElementById("location_id").style.height = FramePageHeight+"%";
	parent.document.getElementById("empFrame_id").style.height = (100 - parseInt(FramePageHeight))+"%";
}


function addEmployee(postEmpArrayObj)
{
	var splitArray = new Array();
	var valObj = postEmpArrayObj.value;
	splitArray = valObj.split(',');
	var checkstatus,splicedpost=0;
	if(document.getElementById(splitArray[0]).checked)
	{
		var del = 0;
		var combo2 = window.parent.document.frames['displayEmpFrame'].document.getElementById('dd_choice1_id');
		if(combo2 != null)
		{
			for(var i=0; i<combo2.options.length; i++)
			{
				if(combo2.options[i].value == splitArray[0])
				{
					alert('Employee already exist...');
					document.getElementById(splitArray[0]).checked=false;
					return;
				}
			}
		}
		checkstatus="true";
		parent.postIdArray.push(splitArray[0]);
		parent.empArray.push(splitArray[1]);
		parent.desgnArray.push(splitArray[2]);
		splicedpost=splitArray[0];
	}
	else
	{
		checkstatus="false";
		for(var cnt=0;cnt<parent.postIdArray.length;cnt++)
		{
			if(parent.postIdArray[cnt] == splitArray[0])
			{
				var splicedpost = parent.postIdArray.splice(cnt,1);
			}
		}
		for(var cnt=0;cnt<parent.empArray.length;cnt++)
		{
			if(parent.empArray[cnt] == splitArray[1])
			{
				parent.empArray.splice(cnt,1);
			}
		
		}
		for(var cnt=0;cnt<parent.desgnArray.length;cnt++)
		{
			if(parent.desgnArray[cnt] == splitArray[2])
			{
				parent.desgnArray.splice(cnt,1);
			}
		}
	}
	if(parent.document.getElementById('showTable').value == 'true')
		parent.addEmpinTable(checkstatus,splicedpost);	
	else
		parent.addEmpinCombo(checkstatus,splicedpost);
}


</script>
<script type="text/javascript"> 
				//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
			initializetabcontent("maintab")
	</script> 
			<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>	
</hdiits:form>


<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>

<script language="javascript">
	for(var cnt=0;cnt<parent.postIdArray.length;cnt++)
		{
			if(document.getElementById(parent.postIdArray[cnt]) != null)
				document.getElementById(parent.postIdArray[cnt]).checked = true;
		}
</script>