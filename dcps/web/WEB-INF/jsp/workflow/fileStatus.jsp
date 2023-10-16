<%
try 
{
	

%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables"	scope="request" />
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="cmnlookuList"	value="${resultMap.cmnlookuList}"></c:set> 
<c:set var="fmsbranchList"	value="${resultMap.fmsbranchList}"></c:set>
<c:set var="flagVal" value="${resultMap.flag}"></c:set>  
<c:set var="langId" value="${resultMap.langId}"></c:set>  
<c:set var="fmsFileRecordCustomVOLst" value="${resultMap.fmsFileRecordCustomVOLst}"></c:set>
<c:set var="aclElementMstList" value="${resultMap.aclElementMstList}" scope="session"></c:set>
<c:set var="hideMenuLookupID" value="${resultMap.hideMenuLookupID}"></c:set>
<c:set var="FileStage" value="${resultMap.FileStage}"/>

<script language="javascript">
/*****************Testing Start*************
var jobrefid = new Array();
var docid = new Array();
*****************Testing End***************/
function openDocument(url)
{
		 docWindow = window.open (url, "Document", "location=1,status=1,scrollbars=1,width=1020,height=650");
  		 docWindow.moveTo(0,0);
}
parent.arr_file_approve = new Array();
parent.arr_corr_approve = new Array();

var statusListEng = new Array();
statusListEng.push('Open');
statusListEng.push('Await');
statusListEng.push('Dispose');

var stageListEng = new Array();
stageListEng.push('Pending');
stageListEng.push('Reject');
stageListEng.push('Approve');

var statusListGuj = new Array();
statusListGuj.push('ઓપન');
statusListGuj.push('રાહમા');
statusListGuj.push('ડિસ્પોસ્ડ');

var stageListGuj = new Array();
stageListGuj.push('પેન્ડિંગ');
stageListGuj.push('નામંજૂર');
stageListGuj.push('મંજૂર');

var finalStatus = new Array();
var finalStage = new Array();
var count = 0;

</script>

<hdiits:form name="frmFileStatus" id="frmFileStatus" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="FileStatus" default="${resultMap.FileStatus}"/>
<hdiits:hidden name="wfWindowName" />
<input type="hidden" name="sender" value="RecordRoom">
<input type="hidden" name="hiddenJObIds" />
<input type="hidden" name="hiddenDocIds" />
<input type="hidden" name="hiddenHierarchyFlag" />
<div id="tabmenu" style="width: 100%">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="WF.FILESTATUSDETAILS" bundle="${wfLables}" />
			</a>
		</li>
	</ul>
</div>
<div class="tabcontentstyle" style="width: 100%"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent" style="width: 100%">
	<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param name="hideMenuLookupID" value="${hideMenuLookupID}" />
	</jsp:include>
	<center><b><font size="2" face="arial"><fmt:message key="WF.FILESTATUSDETAILS"  bundle="${wfLables}"/></font></b></center>
	<br>
<hdiits:fieldGroup id="fldgrp" bundle="${wfLables}" titleCaptionId="WF.SRCH" expandable="true" collapseOnLoad="true">
	<table align="center" id="table1" border="1" width="100%" bordercolor="black" height="2%">	
	<tr><td class="datatableheader"><b><hdiits:caption captionid="WF.FILESTATUSDETAILS" bundle="${wfLables}" /></b></td></tr>
	<tr>
		<td style="border:none" align="left">
			<table>
			<tr></tr>
			<tr>				
				<td>
					<b><hdiits:caption captionid="WF.FILESTATUS" bundle="${wfLables}" /></b>			
				</td>
				<td align="left">
					<hdiits:select name="selStatus" id="selStatus" captionid="WF.FILESTATUS" bundle="${wfLables}" >
						<option value="0">--Select--</option>
						<c:forEach  items="${cmnlookuList}" var="fmslookup">							
								<option value='<c:out value="${fmslookup.lookupName}"/>' selected="true">
									<c:out value="${fmslookup.lookupDesc}" />
								</option>								
						</c:forEach>
					</hdiits:select>
				</td>	
				<td></td>		
				<td align="left">
					  <b><hdiits:caption captionid="WF.FILESUBJECT" bundle="${wfLables}" /></b>
				</td>
				<td align="left">
					  <hdiits:text name="txtSubj"  captionid="WF.FILESUBJECT" bundle="${wfLables}"/>
				</td>	
				 <td></td>
				<td align="left">
					  <b><hdiits:caption captionid="WF.FILENO" bundle="${wfLables}" /></b>
				</td>
			 	<td  align="left">
			 		  <hdiits:text name="txtfileno" captionid="WF.FILENO" bundle="${wfLables}" />
			 	</td>		
			</tr>			
			<tr>
				 <td>
				 	<hdiits:caption  captionid="WF.STARTDATE" bundle="${wfLables}"></hdiits:caption> 
				 </td>
				 <td align="left">
				 	<hdiits:dateTime name="txtStartDate" captionid="WF.START" bundle="${wfLables}"/>				   
				 </td>	
				 <td></td>
				 <td align="left">
				 	<hdiits:caption  captionid="WF.ENDDATE" bundle="${wfLables}"/>
				 </td>
				 <td align="left">
				 	<hdiits:dateTime name="txtEndDate" captionid="WF.END"  bundle="${wfLables}"/>					
			     </td>	
			      <td></td>
			 	 <td align="left">
					 	<hdiits:caption  captionid="WF.DESC" bundle="${wfLables}"/>
				 </td>
				 <td align="left">
					 	<hdiits:text name="txtDesc" captionid="WF.DESC"  bundle="${wfLables}" />					
			     </td>
		 	 </tr>		 	
			 <tr>			 
		     	 <td>
			     	<hdiits:caption  captionid="WF.FNAME" bundle="${wfLables}" />			     	
			     </td> 	
			     <td align="left">
			     	<hdiits:text name="empFName" captionid="WF.FNAME" bundle="${wfLables}" />
			     </td>	
			     <td></td>
			     <td>
			     	<hdiits:caption  captionid="WF.MNAME" bundle="${wfLables}" />			     	
			     </td>
			     <td align="left">
			     	<hdiits:text name="empMName" captionid="WF.MNAME" bundle="${wfLables}" />
			     </td>
			     <td></td>
			     <td align="left">
			     	<hdiits:caption  captionid="WF.LNAME" bundle="${wfLables}" />			     	
			     </td>
			     <td align="left">
			     	<hdiits:text name="empLName" captionid="WF.LNAME" bundle="${wfLables}" />
			     </td>			     
			</tr>	
	</table>
	</td>
	</tr>
	<tr></tr>
	<tr></tr>
	<tr></tr>
	<tr></tr>
	<tr>
		<td style="border:none" align="center" width="100%">			
			<hdiits:button type="button" name="srch" captionid="WF.SRCH" bundle="${wfLables}"  onclick="getData()"/>		
			<hdiits:button type="button" name="reset" captionid="WF.RESET" bundle="${wfLables}" onclick="clearCmb()"/>
			<!-- *************Testing Start**************** 
				<hdiits:button type="button" name="selectAll" value="Select All" onclick="selectId()"/>	
			 *************Testing End**************** -->
		</td>
	</tr>	
	</table>
</hdiits:fieldGroup>	
		<c:if test="${FileStage eq 'fms_Pending'}">
			<table align="center" width="100%">	
			<tr>
		     	 <td>
					<input type="radio" id="job" name="job" onclick="loadMails(0)"/> <hdiits:caption  captionid="WF.MYJOBS" bundle="${wfLables}" />
			     </td>	
			     <td>
					<input type="radio" id="job" name="job" onclick="loadMails(1)"/> <hdiits:caption  captionid="WF.LYINGWITH" bundle="${wfLables}" />
			     </td>
			     <td>
					<input type="radio" id="job" name="job" onclick="loadMails(2)" /> <hdiits:caption  captionid="WF.ALLJOBSFile" bundle="${wfLables}" />
			     </td>
			</tr>
			</table>
			<script language="javascript">
			var flag = "${flagVal}";
			if(flag == 0)
			{
				document.frmFileStatus.job[0].checked = "checked";
			}
			else if(flag == 1)
			{
				document.frmFileStatus.job[1].checked = "checked";
			}
			else if(flag == 2)
			{
				document.frmFileStatus.job[2].checked = "checked";
			}
			</script>
		</c:if>
			
	<c:if test="${not empty fmsFileRecordCustomVOLst}">
		<c:forEach items="${fmsFileRecordCustomVOLst}" var="tempLst"> 
			<script type="text/javascript">
				var flag = 1;
				for(var i=0;i<statusListEng.length;i++)
				{
					if(statusListEng[i] == '${tempLst.fileStatus}')
					{
						if('${langId}' == 1)
						{
							finalStatus.push(statusListEng[i]);
						}
						else
						{
							finalStatus.push(statusListGuj[i]);
						}
						flag = 0;
						break;
					}
				}
				if(flag != 0)
				{
					for(var i=0;i<statusListGuj.length;i++)
					{
						if(statusListGuj[i] == '${tempLst.fileStatus}')
						{
							if('${langId}' == 1)
							{
								finalStatus.push(statusListEng[i]);
							}
							else
							{
								finalStatus.push(statusListGuj[i]);
							}
							break;
						}
					}				
				}
				var flag = 1;
				for(var i=0;i<stageListEng.length;i++)
				{
					if(stageListEng[i] == '${tempLst.filestage}')
					{
						if('${langId}' == 1)
						{
							finalStage.push(stageListEng[i]);
						}
						else
						{
							finalStage.push(stageListGuj[i]);
						}
						flag = 0;
						break;
					}
				}
				if(flag != 0)
				{
					for(var i=0;i<stageListGuj.length;i++)
					{
						if(stageListGuj[i] == '${tempLst.filestage}')
						{
							if('${langId}' == 1)
							{
								finalStage.push(stageListEng[i]);
							}
							else
							{
								finalStage.push(stageListGuj[i]);
							}
							break;
						}
					}				
				}
			</script>
		</c:forEach>	
	</c:if>
	<!-- ***********Testing Start********************* 
	<c:if test="${not empty fmsFileRecordCustomVOLst}">
		<c:forEach items="${fmsFileRecordCustomVOLst}" var="tempLst"> 
			<script type="text/javascript">
				jobrefid.push('${tempLst.jobrefid}');
				docid.push('${tempLst.docId}');
			</script>
		</c:forEach>	
	</c:if>
	***********Testing End********************** -->

	<c:set var="srNo" value="${1}" />		
	<c:if test="${not empty fmsFileRecordCustomVOLst}">
		<display:table list="${fmsFileRecordCustomVOLst}" id="fmsFileRecordCustomVOLst" style="width:100%" pagesize="10" requestURI="">	
			<!-- fms_Pending_ changed to fms_Pending_test  to disable the checkboxes-->
			<c:if test="${fmsFileRecordCustomVOLst.lookupStage eq 'fms_Pending'}">
				<display:column style="text-align:center" class="tablecelltext"  media="HTML">
					<hdiits:checkbox id="${fmsFileRecordCustomVOLst.fileId}" value="${fmsFileRecordCustomVOLst.fileId}" name="viewCorrId" onclick="show('${fmsFileRecordCustomVOLst.fileId}','${fmsFileRecordCustomVOLst.docId}','${fmsFileRecordCustomVOLst.hierarchyflag}')" readonly="${fmsFileRecordCustomVOLst.checked}" />
				</display:column>
			</c:if> 
			<c:if test="${fmsFileRecordCustomVOLst.lookupStage eq 'fms_Approve'}">
			<display:column style="text-align:center" class="tablecelltext"  media="HTML" >
				<input type="checkbox" id="${fmsFileRecordCustomVOLst.fileId}" value="${fmsFileRecordCustomVOLst.fileId}" name="viewCorrId" onclick="show_approve('${fmsFileRecordCustomVOLst.fileId}')"/>
			</display:column>
			</c:if>
			<!-- Same for fms_Approve_test above *********End************ -->
			
			<display:column style="text-align:center" class="tablecelltext" titleKey="WF.SRNO" sortable="false" headerClass="datatableheader"  value="${srNo}"></display:column>
			<c:set var="srNo" value="${srNo+1}" />						
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.FileNo"     sortable="false" headerClass="datatableheader">
				<a href= "javascript:openDocument('${fmsFileRecordCustomVOLst.joburl}')" >
					${fmsFileRecordCustomVOLst.fileNo}
				</a>
			</display:column>  
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.FileDesc"  sortable="false" headerClass="datatableheader">${fmsFileRecordCustomVOLst.fileDesc}</display:column>  							
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.LOC"        sortable="false" headerClass="datatableheader">${fmsFileRecordCustomVOLst.locName}</display:column>  		
		    <display:column style="text-align:center" class="tablecelltext"  titleKey="WF.Subj"       sortable="false" headerClass="datatableheader">${fmsFileRecordCustomVOLst.fileSubj}</display:column>  	
		    <fmt:formatDate value="${fmsFileRecordCustomVOLst.recvdDt}" pattern="dd/MM/yyyy hh:mm:ss" dateStyle="medium" var="date"/>
		    <display:column style="text-align:center" class="tablecelltext"  titleKey="WF.RecvdDt"    sortable="false" headerClass="datatableheader">${date}</display:column>  	
		    <display:column style="text-align:left"   class="tablecelltext"  titleKey="WF.RECVDFRM"  sortable="false" headerClass="datatableheader">${fmsFileRecordCustomVOLst.lyingWith}</display:column>  	
		    <display:column style="text-align:center" class="tablecelltext"  titleKey="WF.FILESTATUS" sortable="false" headerClass="datatableheader">
			    <script>
					document.write(finalStatus[count]);
				</script>
			</display:column>  	
		    <display:column style="text-align:center" class="tablecelltext"  titleKey="WF.FileStage"  sortable="false" headerClass="datatableheader">
			    <script>
					document.write(finalStage[count++]);
				</script>
		    </display:column>  			   

		</display:table> 			
	</c:if>			
	<br><br>
	<c:if test="${empty fmsFileRecordCustomVOLst}">
			<center>
				<hdiits:caption  captionid="WF.NoRec" bundle="${wfLables}" />	
			</center>		
	</c:if>		
</div>
</div>
<script language="javascript">
/*****************Testing Start*************
function selectId()
{
	var url='hdiits.htm?actionFlag=testFunction&docType=3';
	document.forms[0].action=url;
	document.getElementById('hiddenJObIds').value = jobrefid;
	document.forms[0].submit();
	//alert(url);
	//window.location=url;	
}
*****************Testing End*****************/
document.getElementById('wfWindowName').value=window.parent.name;
if(parent.arr_file!=null)
{
	for(var i=0;i<parent.arr_file.length;i++)
	{
		if(document.getElementById(parent.arr_file[i])!=null)
			document.getElementById(parent.arr_file[i]).checked=true;
	}
}

function loadMails(no)
{
	if(no == 0)
	{
		var url='hdiits.htm?actionFlag=LoadStatus&stage=fms_Pending&moduleName=RecordRoom&menuName=forRecPending&flag=0&fromApprove=true';
	}
	else if(no == 1)
	{
		var url='hdiits.htm?actionFlag=LoadStatus&stage=fms_Pending&moduleName=RecordRoom&menuName=forRecPending&flag=1&fromApprove=true';
	}
	else if(no == 2)
	{
		var url='hdiits.htm?actionFlag=LoadStatus&stage=fms_Pending&moduleName=RecordRoom&menuName=forRecPending&flag=2&fromApprove=true';
	}
	document.forms[0].action=url;
	document.forms[0].submit();
}

function clearCmb()
{
	if(confirm("The Entered Values will cleared, Please Confirm?")==true)
		{			
			document.forms[0].selStatus.value='0';
			document.forms[0].txtSubj.value='';			
			document.forms[0].txtfileno.value=" ";
			document.forms[0].txtStartDate.value=" ";
			document.forms[0].txtEndDate.value=" ";
			document.forms[0].empFName.value=" ";
			document.forms[0].empMName.value=" ";	
			document.forms[0].empLName.value=" ";	
			document.forms[0].txtDesc.value=" ";			
		}
}

function getData()
{		
	var url='hdiits.htm?actionFlag=srchFileStatus&docName='+document.forms[0].txtSubj.value+'&lookUpId='+document.forms[0].selStatus.value+'&fileNo='+document.forms[0].txtfileno.value+'&lookupstagename='+'${FileStage}'+'&strtDt='+document.forms[0].txtStartDate.value+'&endDt='+document.forms[0].txtEndDate.value+'&fname='+document.forms[0].empFName.value+'&mname='+document.forms[0].empMName.value+'&lname='+document.forms[0].empLName.value+'&ldesc='+document.forms[0].txtDesc.value;
	if('${FileStage}' == 'fms_Approve')
		url+="&moduleName=WorkList&menuName=forRecordCommonApprove";
	else if('${FileStage}' == 'fms_Pending')
		url+="&moduleName=RecordRoom&menuName=forRecPending";
	window.location=url;
}

/* Activated by jaspal.....*/
function pullJobs()
{		
	if(parent.arr_file.length != 0)
	{
		document.getElementById('hiddenJObIds').value = parent.arr_file;
		document.getElementById('hiddenDocIds').value = parent.doc_file;
		document.getElementById('hiddenHierarchyFlag').value = parent.hierarchy_file;
		var url='hdiits.htm?actionFlag=pullJobs&docType=3&moduleName=RecordRoom&menuName=forRecPending';
		parent.arr_file.splice(0, parent.arr_file.length);
		parent.doc_file.splice(0, parent.doc_file.length);
		parent.hierarchy_file.splice(0, parent.hierarchy_file.length);
		parent.count_file = 0;
		parent.arr_corr.splice(0, parent.arr_corr.length);
		parent.doc_corr.splice(0, parent.doc_corr.length);
		parent.hierarchy_corr.splice(0, parent.hierarchy_corr.length);
		parent.count_corr = 0;
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
		if(parent.count_file != 4)
		{
			parent.count_file++;
			parent.arr_file.push(fileid);
			parent.doc_file.push(docid);
			parent.hierarchy_file.push(hierarchy);
			parent.arr_corr.splice(0, parent.arr_corr.length);
			parent.doc_corr.splice(0, parent.doc_corr.length);
			parent.hierarchy_corr.splice(0, parent.hierarchy_corr.length);
			parent.count_corr = 0;
		}
		else
		{
			document.getElementById(fileid).checked = false;
			alert("Five Jobs can be pulled at once...");
		}
	}
	else
	{
		for(var i=0;i<parent.arr_file.length;i++)
		{
			if(parent.arr_file[i] == fileid)
			{
				parent.count_file--;
				parent.arr_file.splice(i,1);
				parent.doc_file.splice(i,1);
				parent.hierarchy_file.splice(i,1);
			}
		}
	}
}
/* Activated by jaspal.....*/
function show_approve(fileid)
{
	if(document.getElementById(fileid).checked==true)
	{
		parent.arr_file_approve.push(fileid);
	}
	else
	{
		for(var i=0;i<parent.arr_file_approve.length;i++)
		{
			if(parent.arr_file_approve[i] == fileid)
			{
				parent.arr_file_approve.splice(i,1);
			}
		}
	}
}

function getCorrList()
{
		if(parent.arr_file_approve=='')
		{
			alert("Select Atleast One File");
		}
		else
		{
			if(confirm("Do You want to Resubmit File?"))
			{
				document.forms[0].action='hdiits.htm?actionFlag=resubmitCorrespondence&fileId='+parent.arr_file_approve;
				document.forms[0].submit();
			}				
		}			
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
catch (Exception e) 
{
		e.printStackTrace();
}
%>
