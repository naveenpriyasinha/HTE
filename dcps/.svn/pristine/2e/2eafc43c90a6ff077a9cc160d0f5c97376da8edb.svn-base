<%
try 
{
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<c:set var="resultObj"	value="${result}"> </c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="resultJob" value="${resultMap.JobList}"></c:set>
<c:set var="dashBoardVO" value="${resultMap.dashBoardVO}"></c:set>
<c:set var="deptNameList" value="${resultMap.deptNameList}"></c:set>
<c:set var="locNameList" value="${resultMap.locNameList}"></c:set>

<fmt:setBundle basename="resources.WFLables" var="wfLables" scope="request"/>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="workFlowLables" scope="request"/>

<hdiits:form name="tableDashBoard" validate="false" encType="multipart/form-data" method="POST">
<hdiits:hidden name="department"/>
<hdiits:hidden name="location"/>
<hdiits:hidden name="branch"/>
<hdiits:hidden name="deptSel"/>
<hdiits:hidden name="locSel"/>
<hdiits:hidden name="brnchSel"/>
<hdiits:hidden name="prioritySel" />

<script type="text/javascript">

	function loadLocationCombo()
	{
		var locCombo = document.getElementById('comboLoc');
		if(locCombo.options.length>'0')
		{
			for(var i=locCombo.options.length; i>=0; i--)
			{
				locCombo.remove(i);
			}
		}
		locCombo.selectedIndex = '0';

		var deptVal = document.getElementById('comboDept')[document.getElementById('comboDept').selectedIndex].value;
			
		try
	   	{   
	   	// Firefox, Opera 8.0+, Safari    
	   		xmlHttp=new XMLHttpRequest();    
	   	}
		catch(e)
		{    // Internet Explorer    
			try
     		{
     		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
     		}
		    catch(e)
		    {
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
				}
				catch (e)
				{
			        alert("Your browser does not support AJAX!");        
			        return false;        
				}
			 }
	   	}
	
		var url = "hdiits.htm?actionFlag=getLocationForDepartment&deptName="+deptVal;
	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4)
			{     
				if(xmlHttp.status == 200)
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					var Category = XMLDoc.getElementsByTagName('Loc');
					if(Category.length==0)
					{
						alert('<fmt:message key="WF.NOBRANCH" bundle="${wfLables}" />');
						return;
					}
					var comboid = document.getElementById('comboLoc');

					for (var i=0 ; i < Category.length ; i++ )
					{
	  					var LocName = Category[i].childNodes[0].text;	   						
	  					var element = document.createElement('option');
	     				element.text = LocName;
	     				element.value = LocName;
	     				try
					    {
					    	comboid.add(element,null); // standards compliant
					    }
					    catch(ex)
					    {
						    comboid.add(element); // IE only
					    }
					}

					var comboDept = document.getElementById('comboDept');
					for(var l=0; l<comboDept.options.length; l++)
					{
						if(l!=0)
							document.getElementById('department').value+=";;";
						document.getElementById('department').value += comboDept.options[l].text;
					}
					
					var comboLoc = document.getElementById('comboLoc');
					for(var l=0; l<comboLoc.options.length; l++)
					{
						if(l!=0)
							document.getElementById('location').value+=";;";
						document.getElementById('location').value += comboLoc.options[l].text;
					}
			
					document.getElementById('deptSel').value = document.getElementById('comboDept')[document.getElementById('comboDept').selectedIndex].text;
					document.getElementById('locSel').value = document.getElementById('comboLoc')[document.getElementById('comboLoc').selectedIndex].text;

					var url = "hdiits.htm?actionFlag=showTabularDashBoard&moduleName=DashBoard&menuName=forDashBoard&docType="+'${param.docType}'+"&attrib="+'${param.attrib}';
					
					if('${param.isDue}'!='')
						url += "&isDue="+'${param.isDue}';

					if('${param.isChild}'!='')
						url += "&isChild="+'${param.isChild}';
					
//					alert(url);
					document.getElementById('tableDashBoard').action = url;
					document.getElementById('tableDashBoard').submit();
				}
			}
	    }
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}

	function loadTabularDataForBranch()
	{
		if(document.getElementById('locationDetails').style.display!='none')
		{
			var comboDept = document.getElementById('comboDept');
			for(var l=0; l<comboDept.options.length; l++)
			{
				if(l!=0)
					document.getElementById('department').value+=";;";
				document.getElementById('department').value += comboDept.options[l].text;
			}
			
			var comboLoc = document.getElementById('comboLoc');
			for(var l=0; l<comboLoc.options.length; l++)
			{
				if(l!=0)
					document.getElementById('location').value+=";;";
				document.getElementById('location').value += comboLoc.options[l].text;
			}
	
			document.getElementById('deptSel').value = document.getElementById('comboDept')[document.getElementById('comboDept').selectedIndex].text;
			document.getElementById('locSel').value = document.getElementById('comboLoc')[document.getElementById('comboLoc').selectedIndex].text;

/*			alert(document.getElementById('department').value);
			alert(document.getElementById('location').value);
			alert(document.getElementById('branch').value);
			alert(document.getElementById('deptSel').value);
			alert(document.getElementById('locSel').value);
			alert(document.getElementById('brnchSel').value);
			alert(document.getElementById('brnch').value);*/

			var url = "hdiits.htm?actionFlag=showTabularDashBoard&moduleName=DashBoard&menuName=forDashBoard&docType="+'${param.docType}'+"&attrib="+'${param.attrib}';
			
			if('${param.isDue}'!='')
				url += "&isDue="+'${param.isDue}';
			if('${param.isChild}'!='')
				url += "&isChild="+'${param.isChild}';
			
//			alert(url);
			document.getElementById('tableDashBoard').action = url;
			document.getElementById('tableDashBoard').submit();
		}
	}
</script>

	<table width="100%">
		<tr id="locationDetails">
			<td width="10%" align="left">
				<hdiits:caption captionid="WF.DEPARTMENT" bundle="${workFlowLables}" /> 
			</td>
			<td>
				<select id="comboDept" onchange="loadLocationCombo()">
					<c:forEach items="${deptNameList}" var="dept">
						<option value="${dept}">
							<c:out value="${dept}" />
						</option>
					</c:forEach>
				</select>
			</td>
			<td width="10%" align="left">
				<hdiits:caption captionid="WF.LOCATION" bundle="${workFlowLables}" />
			</td>
			<td> 
				<select id="comboLoc" onchange="loadTabularDataForBranch()">
					<c:forEach items="${locNameList}" var="loc">
						<option value="${loc}">
							<c:out value="${loc}" />
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="4">
				<jsp:include flush="true" page="../workflow/DashBoardSearch.jsp">
					<jsp:param name="priority" value="${resultMap.priority}" />
				</jsp:include>
			</td>
		</tr>
	</table>
	<table id="tab_new" border="1" align="center" class="tabtable" style="display: none;">
		<thead>
			<tr>
				<th class="datatableheader">
					<c:if test="${param.attrib eq 'subject'}">
						<hdiits:caption captionid="WF.SUBJECT" bundle="${wfLables}" />
					</c:if>
					<c:if test="${param.attrib eq 'branch'}">
						<hdiits:caption captionid="WF.BRANCH" bundle="${wfLables}" />
					</c:if>
					<c:if test="${param.attrib eq 'dueDate'}">
						<hdiits:caption captionid="WF.DUEDATE" bundle="${wfLables}" />
					</c:if>
				</th>
				<th class="datatableheader"><hdiits:caption captionid="WF.ROUTINE" bundle="${wfLables}" /></th>
				<th class="datatableheader"><hdiits:caption captionid="WF.IMMEDIATE" bundle="${wfLables}" /></th>
				<th class="datatableheader"><hdiits:caption captionid="WF.URGENT" bundle="${wfLables}" /></th>
				<th class="datatableheader"><hdiits:caption captionid="WF.TODAY" bundle="${wfLables}" /></th>
				<th class="datatableheader"><hdiits:caption captionid="WF.DATESET" bundle="${wfLables}" /></th>
			</tr>
		</thead>
	</table>

<script type="text/javascript">
	var tempLst;

	if('${resultMap.errorMsg}'!='')
	{
		if('${resultMap.errorMsg}'=='1')
		{
			document.write('<br /><b><center><fmt:message key="WF.NOMAPFORLOC" bundle="${workFlowLables}" /></center></b>');
		}
		else if('${resultMap.errorMsg}'=='2')
		{
			document.write('<br /><b><center><fmt:message key="WF.NODEPT" bundle="${workFlowLables}" /></center></b>');
		}
	}
	else
	{
		if('${resultJob}'!='[]')
		{
			document.getElementById('tab_new').style.display = '';
			var tempLst1 = '${resultJob}';
			new1 = tempLst1.split('[');
		    new2 = new1[1].split(']');
		    tempLst = new2[0].split(', ');
	
		    var count = 0;
		    var j = 0;
			for(var i=0;i<tempLst.length;)
			{
				var row = document.getElementById("tab_new").insertRow();
				row.setAttribute("id",count++);
				row.setAttribute("align","center");
				var cell1=row.insertCell(0);
				var cell2=row.insertCell(1);
				var cell3=row.insertCell(2);
				var cell4=row.insertCell(3);
				var cell5=row.insertCell(4);
				var cell6=row.insertCell(5);
	
				j = i;
				if(tempLst[i] != 0)
					cell1.innerHTML='<a href=javascript:openInbox('+(i+1)+','+j+',0)><b>' + tempLst[i] + '</b></a>';
				else
					cell1.innerHTML=tempLst[i];
				i = i + 2;
				if(tempLst[i] != 0)
					cell2.innerHTML='<a href=javascript:openInbox('+(i+1)+','+j+',"wf_Routine")><b>' + tempLst[i] + '</b></a><br /><img src="images/workflowImages/greenblink.gif" />';
				else
					cell2.innerHTML=tempLst[i];
				i = i + 2;
				if(tempLst[i] != 0)
					cell3.innerHTML='<a href=javascript:openInbox('+(i+1)+','+j+',"wf_Immediate")><b>' + tempLst[i] + '</b></a><br /><img src="images/workflowImages/greenblink.gif" />';
				else
					cell3.innerHTML=tempLst[i];
				i = i + 2;
				if(tempLst[i] != 0)
					cell4.innerHTML='<a href=javascript:openInbox('+(i+1)+','+j+',"wf_Urgent")><b>' + tempLst[i] + '</b></a><br /><img src="images/workflowImages/greenblink.gif" />';
				else
					cell4.innerHTML=tempLst[i];
				i = i + 2;
				if(tempLst[i] != 0)
					cell5.innerHTML='<a href=javascript:openInbox('+(i+1)+','+j+',"wf_Today")><b>' + tempLst[i] + '</b></a><br /><img src="images/workflowImages/greenblink.gif" />';
				else
					cell5.innerHTML=tempLst[i];
				i = i + 2;
				if(tempLst[i] != 0)
					cell6.innerHTML='<a href=javascript:openInbox('+(i+1)+','+j+',"wf_DateSet")><b>' + tempLst[i] + '</b></a><br /><img src="images/workflowImages/greenblink.gif" />';
				else
					cell6.innerHTML=tempLst[i];
				i = i + 2;
			}
		}
		else
		{
			document.getElementById('tab_new').style.display = 'none';
			document.write('<br />'+'<fmt:message key="WF.NORECFOUND" bundle="${workFlowLables}" />');
		}
	}

	function openInbox(urlIndex, subIndex, priority)
	{
		url = tempLst[urlIndex];
		url += "&isDue="+'${param.isDue}';
		if('${param.attrib}'!='')
		{
			url += "&attrib="+'${param.attrib}';
		}
		
		if('${param.isChild}'!='')
		{
			url += "&isChild="+'${param.isChild}';
		}
			
		if('${param.attrib}'=='subject')
		{
			document.getElementById('docnames').value=tempLst[subIndex];
		}

		var comboObj = document.getElementById('comboPriority');
		for(var i=1; i<comboObj.length; i++)
		{
			if(comboObj[i].value==priority)
			{
				document.forms[0].comboPriority.selectedIndex=i;
				break;		
			}
		}
		
		if('${param.attrib}'=='dueDate')
		{
			if(document.getElementById('agingFrom').value!='')
			{
				if(document.getElementById('agingTo').value!='')
				{
					if(document.getElementById('agingFrom').value == document.getElementById('agingTo').value)
					{
						alert('<fmt:message key="WF.ULDIFF" bundle="${wfLables}" />');
						document.getElementById('department').value='';
						document.getElementById('location').value='';
						return false;
					}
					else if(parseInt(document.getElementById('agingFrom').value) > parseInt(document.getElementById('agingTo').value))
					{
						alert('<fmt:message key="WF.UGREATER" bundle="${wfLables}" />');
						document.getElementById('department').value='';
						document.getElementById('location').value='';
						return false;
					}
//					url = url + "&fromAge=" + document.getElementById('agingFrom').value + "&toAge=" + document.getElementById('agingTo').value;
				}
				else
				{
					alert('<fmt:message key="WF.UPRANGE" bundle="${wfLables}" />');
					document.getElementById('department').value='';
					document.getElementById('location').value='';
					return false;
				}
			}
			else
			{
				alert('<fmt:message key="WF.LORANGE" bundle="${wfLables}" />');
				document.getElementById('department').value='';
				document.getElementById('location').value='';
				return false;
			}
		}

		if(document.getElementById('comboPriority').selectedIndex!=0)
		{
			var priority = document.getElementById('comboPriority')[document.getElementById('comboPriority').selectedIndex].value;
			document.getElementById('prioritySel').value = priority;
		}
//		alert(url);
		document.getElementById('tableDashBoard').action = url;
		document.getElementById('tableDashBoard').submit();
	}
	
	function getSearchData()
	{
		if(document.getElementById('locationDetails').style.display!='none')
		{
			var comboDept = document.getElementById('comboDept');
			for(var l=0; l<comboDept.options.length; l++)
			{
				if(l!=0)
					document.getElementById('department').value+=";;";
				document.getElementById('department').value += comboDept.options[l].text;
			}

			var comboLoc = document.getElementById('comboLoc');
			for(var l=0; l<comboLoc.options.length; l++)
			{
				if(l!=0)
					document.getElementById('location').value+=";;";
				document.getElementById('location').value += comboLoc.options[l].text;
			}
	
			document.getElementById('deptSel').value = document.getElementById('comboDept')[document.getElementById('comboDept').selectedIndex].text;
			document.getElementById('locSel').value = document.getElementById('comboLoc')[document.getElementById('comboLoc').selectedIndex].text;
		}
		
		var url = "hdiits.htm?actionFlag=showTabularDashBoard&moduleName=DashBoard&menuName=forDashBoard&docType="+'${param.docType}'+"&attrib="+'${param.attrib}';
		
		if('${param.isDue}'!='')
			url += "&isDue="+'${param.isDue}';

		if('${param.isChild}'!='')
			url += "&isChild="+'${param.isChild}';

		if('${param.brnch}'!='')
			url += "&brnch="+'${param.brnch}';
		
		if('${param.loc}'!='')
			url += "&loc="+'${param.loc}';

		if('${param.attrib}'=='dueDate')
		{
			if(document.getElementById('agingFrom').value!='')
			{
				if(document.getElementById('agingTo').value!='')
				{
					if(document.getElementById('agingFrom').value == document.getElementById('agingTo').value)
					{
						alert('<fmt:message key="WF.ULDIFF" bundle="${wfLables}" />');
						document.getElementById('department').value='';
						document.getElementById('location').value='';
						return false;
					}
					else if(parseInt(document.getElementById('agingFrom').value) > parseInt(document.getElementById('agingTo').value))
					{
						alert('<fmt:message key="WF.UGREATER" bundle="${wfLables}" />');
						document.getElementById('department').value='';
						document.getElementById('location').value='';
						return false;
					}
					url = url + "&fromAge=" + document.getElementById('agingFrom').value + "&toAge=" + document.getElementById('agingTo').value;
				}
				else
				{
					alert('<fmt:message key="WF.UPRANGE" bundle="${wfLables}" />');
					document.getElementById('department').value='';
					document.getElementById('location').value='';
					return false;
				}
			}
			else
			{
				alert('<fmt:message key="WF.LORANGE" bundle="${wfLables}" />');
				document.getElementById('department').value='';
				document.getElementById('location').value='';
				return false;
			}
		}

		if(document.getElementById('comboPriority').selectedIndex!=0)
		{
			var priority = document.getElementById('comboPriority')[document.getElementById('comboPriority').selectedIndex].value;
			document.getElementById('prioritySel').value = priority;
		}

		document.getElementById('tableDashBoard').action = url;
		document.getElementById('tableDashBoard').submit();
	}

	var deptNameLst = '${resultMap.department1}';
	if(deptNameLst!='')
	{
		var tempDeptNameLst = deptNameLst.split(';;');
		var comboid1 = document.getElementById('comboDept');
		if(comboid1.options.length>='0')
		{
			for(var i=comboid1.options.length; i>=0; i--)
			{
				comboid1.remove(i);
			}
		}
		for (var i=0 ; i < tempDeptNameLst.length ; i++)
		{
			var element = document.createElement('option');
			element.text = tempDeptNameLst[i];
			element.value = tempDeptNameLst[i];
			
			try
		    {
		    	comboid1.add(element,null); // standards compliant
		    }
		    catch(ex)
		    {
			    comboid1.add(element); // IE only
		    }
			if('${resultMap.deptSel1}'==tempDeptNameLst[i])
			{
				comboid1.selectedIndex = i;
			}
		}
		
		var locNameLst = '${resultMap.location1}';
		if(locNameLst!='')
		{
			var tempLocNameLst = locNameLst.split(';;');
			var comboid2 = document.getElementById('comboLoc');
			if(comboid2.options.length>='0')
			{
				for(var i=comboid2.options.length; i>=0; i--)
				{
					comboid2.remove(i);
				}
			}
			for (var i=0 ; i < tempLocNameLst.length ; i++)
			{
				var element = document.createElement('option');
				element.text = tempLocNameLst[i];
				element.value = tempLocNameLst[i];

				try
			    {
			    	comboid2.add(element,null); // standards compliant
			    }
			    catch(ex)
			    {
				    comboid2.add(element); // IE only
			    }
				if('${resultMap.locSel1}'==tempLocNameLst[i])
				{
					comboid2.selectedIndex = i;
				}
			}
		}
	}

	if('${deptNameList}'=='[]' && '${resultMap.department1}'=='')
	{
		document.getElementById('locationDetails').style.display='none';
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

	if('${param.attrib}'!='dueDate')
	{
		document.getElementById('agerow').style.display='none';
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