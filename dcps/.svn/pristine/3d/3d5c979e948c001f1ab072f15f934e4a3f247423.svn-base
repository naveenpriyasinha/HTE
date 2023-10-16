<%
try {
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
<fmt:setBundle basename="resources.hod.ps.CrimeDetailFormLables" var="crimefrm" scope="request"/>
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="fmsCorrRecordCustomVoList"	value="${resultMap.fmsCorrRecordCustomVoList}"></c:set> 
<c:set var="fmscorrStatusList"	value="${resultMap.fmscorrStatusList}"></c:set> 
<c:set var="fmsSubjList" value="${resultMap.fmsSubjList}"></c:set> 
<c:set var="flagVal" value="${resultMap.flag}"></c:set>  
<c:set var="langId" value="${resultMap.langId}"></c:set>  
<c:set var="aclElementMstList" value="${resultMap.aclElementMstList}" scope="session"></c:set>
<c:set var="noOfLevelsInMenu" value="${resultMap.noOfLevelsInMenu}" scope="session"></c:set>
<c:set var="hideMenuLookupID" value="${resultMap.hideMenuLookupID}"></c:set>
<c:set var="CorrStage" value="${resultMap.CorrStage}"/>

<script language="javascript">
function openDocument(url)
{
	 docWindow = window.open (url,"Document","location=1,status=1,scrollbars=1,width=1020,height=650"); 
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

<hdiits:form name="frmCorr" id="frmCorr" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<input type="hidden" name="hiddenJObIds" />
<input type="hidden" name="hiddenDocIds" />
<input type="hidden" name="hiddenHierarchyFlag" />
<hdiits:hidden name="CorrStatus" default="${resultMap.CorrStatus}"/>
<hdiits:hidden name="hidden_corrid" id="corr"/>

<hdiits:hidden name="wfWindowName" />	
<div id="tabmenu" style="width: 100%">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="WF.Correspondence" bundle="${wfLables}" />
			</a>
		</li>
	</ul>
</div>
<div class="tabcontentstyle" style="width: 100%"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent" style="width: 100%">
	<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
	</jsp:include>
	<center><b><font size="2" face="arial"><fmt:message key="WF.Correspondence"  bundle="${wfLables}"/></font></b></center>
	<br>
<hdiits:fieldGroup id="fldgrp" bundle="${wfLables}" titleCaptionId="WF.SRCH" expandable="true" collapseOnLoad="true">
	<table align="center" id="table1" border="1" bordercolor="black" width="100%" height="2%">	
	<tr><td class="datatableheader"><b><hdiits:caption captionid="WF.Correspondence" bundle="${wfLables}" /></b>	</td></tr>
	<tr>
		<td style="border:none" align="left">
			<table>
			<tr></tr>
			<tr>				
				<td>
					<b><hdiits:caption captionid="WF.CORRSTATUS" bundle="${wfLables}" /></b>			
				</td>
				<td align="left">
					<hdiits:select name="selCorrStatus" id="selCorrStatus" captionid="WF.CORRSTATUS" bundle="${wfLables}" >
						<option value="0">--Select--</option>
						<c:forEach  items="${fmscorrStatusList}" var="statuslookup">						
									<option value='<c:out value="${statuslookup.lookupName}"/>' selected="true">
									<c:out value="${statuslookup.lookupDesc}" />
								</option>								
						</c:forEach>
					</hdiits:select>
				</td>			
				<td></td>				
 		     	<td align="left">
					<b><hdiits:caption captionid="WF.Subj" bundle="${wfLables}" /></b>
				</td>
				<td align="left">
					<hdiits:text name="txtCorrSubj" captionid="WF.Subj" bundle="${wfLables}"/>									
			 	</td>
			 	<td></td>		 		     	
			 	<td align="left">
						<b><hdiits:caption captionid="WF.CORRNO" bundle="${wfLables}" /></b>
				</td>
			 	<td align="left">
			 			<hdiits:text name="txtCorrNo" captionid="WF.CORRNO" bundle="${wfLables}" />
			 	</td>
			</tr>
			<tr></tr>
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
			     <td align="left">
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
			<hdiits:button type="button" name="srch" captionid="WF.SRCH" bundle="${wfLables}" onclick="getData()"/>		
			<hdiits:button type="button" name="reset" captionid="WF.RESET" bundle="${wfLables}" onclick="clearCmb()"/>
		</td>
	</tr>	
	</table>			
</hdiits:fieldGroup>	
	<c:if test="${CorrStage eq 'fms_Pending'}">
		<table align="center" width="100%">	
		<tr align="left">
			     	 <td style="border:none" align="left">
						<input type="radio" id="job" name="job" onclick="loadMails(0)"/> <hdiits:caption  captionid="WF.MYJOBS" bundle="${wfLables}" />
				     </td>	
				     <td style="border:none" align="left">
						<input type="radio" id="job" name="job" onclick="loadMails(1)"/> <hdiits:caption  captionid="WF.LYINGWITH" bundle="${wfLables}" />
				     </td>
				     <td style="border:none" align="left">
						<input type="radio" id="job" name="job" onclick="loadMails(2)" /> <hdiits:caption  captionid="WF.ALLJOBSCorr" bundle="${wfLables}" />
				     </td>
				   </tr>  
		</table>
		<script language="javascript">
		var flag = "${flagVal}";
		if(flag == 0)
		{
			document.frmCorr.job[0].checked = "checked";
		}
		else if(flag == 1)
		{
			document.frmCorr.job[1].checked = "checked";
		}
		else if(flag == 2)
		{
			document.frmCorr.job[2].checked = "checked";
		}
		</script>
		
	</c:if>
	<c:if test="${not empty fmsCorrRecordCustomVoList}">
		<c:forEach items="${fmsCorrRecordCustomVoList}" var="tempLst"> 
			<script type="text/javascript">
				var flag = 1;
				for(var i=0;i<statusListEng.length;i++)
				{
					if(statusListEng[i] == '${tempLst.corrStatus}')
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
						if(statusListGuj[i] == '${tempLst.corrStatus}')
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
					if(stageListEng[i] == '${tempLst.corrStage}')
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
						if(stageListGuj[i] == '${tempLst.corrStage}')
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
	<c:set var="srNo" value="${1}" />	
	<c:if test="${not empty fmsCorrRecordCustomVoList}">
		<display:table list="${fmsCorrRecordCustomVoList}" id="fmsCorrRecordCustomVoList" style="width:100%" pagesize="10" requestURI="">	
			<!-- fms_Pending_ changed to fms_Pending_test  to disable the checkboxes-->
			<c:if test="${fmsCorrRecordCustomVoList.lookupStage eq 'fms_Pending'}">
			<display:column style="text-align:center" class="tablecelltext"  media="HTML">
				<hdiits:checkbox id="${fmsCorrRecordCustomVoList.corrId}" value="${fmsCorrRecordCustomVoList.corrId}" name="viewCorrId" onclick="show('${fmsCorrRecordCustomVoList.corrId}','${fmsCorrRecordCustomVoList.docId}','${fmsCorrRecordCustomVoList.hierarchyflag}')" readonly="${fmsCorrRecordCustomVoList.checked}" />
			</display:column>
			</c:if>
			<c:if test="${fmsCorrRecordCustomVoList.lookupStage eq 'fms_Approve'}">
			<display:column style="text-align:center" class="tablecelltext"  media="HTML">
				<input type="checkbox" id="${fmsCorrRecordCustomVoList.corrId}" value="${fmsCorrRecordCustomVoList.corrId}" name="viewCorrId" onclick="show_approve('${fmsCorrRecordCustomVoList.corrId}')"/>
			</display:column>
			</c:if>
			<!-- Same for fms_Approve_test above *********End -->
			
			<display:column style="text-align:center" class="tablecelltext" titleKey="WF.SRNO" sortable="false" headerClass="datatableheader"  value="${srNo}"></display:column>
			<c:set var="srNo" value="${srNo+1}" />						
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.CORRNO"     sortable="false" headerClass="datatableheader">			
				<a href= "javascript:openDocument('${fmsCorrRecordCustomVoList.jobUrl}')">
					${fmsCorrRecordCustomVoList.corrNo}
				</a>
			</display:column>  
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.CorrDesc"   sortable="false" headerClass="datatableheader">${fmsCorrRecordCustomVoList.corrDesc}</display:column>  							
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.dept"       sortable="false" headerClass="datatableheader">${fmsCorrRecordCustomVoList.dept}</display:column>  					
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.Subj"       sortable="false" headerClass="datatableheader">${fmsCorrRecordCustomVoList.corrSubj}</display:column>  	
			<fmt:formatDate value="${fmsCorrRecordCustomVoList.recvdDt}" pattern="dd/MM/yyyy hh:mm:ss" dateStyle="medium" var="date"/>
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.RecvdDt"    sortable="false" headerClass="datatableheader">${date}</display:column>  			    
		    <display:column style="text-align:left" class="tablecelltext"    titleKey="WF.RECVDFRM"  sortable="false" headerClass="datatableheader">${fmsCorrRecordCustomVoList.lyingWith}</display:column>  			    		    
		    <display:column style="text-align:center" class="tablecelltext"  titleKey="WF.CORRCAT"    sortable="false" headerClass="datatableheader">${fmsCorrRecordCustomVoList.corrCategory}</display:column>  			    		    
		    <display:column style="text-align:center" class="tablecelltext"  titleKey="WF.CORRSTATUS" sortable="false" headerClass="datatableheader">
			    <script>
					document.write(finalStatus[count]);
				</script>
			</display:column>  	
		    <display:column style="text-align:center" class="tablecelltext"  titleKey="WF.CORRSTAGE"  sortable="false" headerClass="datatableheader">
			    <script>
					document.write(finalStage[count++]);
				</script>
		    </display:column>
		</display:table> 			
	</c:if>	
	<br><br>
	<c:if test="${empty fmsCorrRecordCustomVoList}">
			<center>
				<hdiits:caption  captionid="WF.NoRec" bundle="${wfLables}" />	
			</center>
	</c:if>	
</div>
</div>
<script language="javascript">
document.getElementById('wfWindowName').value=window.parent.name;
if(parent.arr_corr!=null)
{
	for(var i=0;i<parent.arr_corr.length;i++)
	{
		if(document.getElementById(parent.arr_corr[i])!=null)
			document.getElementById(parent.arr_corr[i]).checked=true;
	}
}
function dateValidation()
{
	var startDate=document.getElementById('txtStartDate').value;
	var endDate=document.getElementById('txtEndDate').value;
	var sysDate=new Date();
	var sysdate=document.getElementById('sysDate').value;
	if(startDate!='' && endDate!='')

	{
		if(compareDate(startDate,endDate) < 0) 
		{
			alert("<fmt:message key='CD.DATEMSG' bundle='${crimefrm}'/>");
			selectRequiredTab('txtEndDate');
			setFocusSelection('txtEndDate');
			return false;
		}
		else if(compareDate(endDate,sysdate) < 0)
		{
			alert("<fmt:message key='CD.SYSDATEMSG' bundle='${crimefrm}'/>");
			selectRequiredTab('txtEndDate');
			setFocusSelection('txtEndDate');
			return false;
		}
		else
		{
			return true;
		}
	}
	else
	{
		return true;
	}
	
}
function clearCmb()
{
	if(confirm("The Entered Values will cleared, Please Confirm?")==true)
		{			
			document.forms[0].selCorrStatus.value='0';
			document.forms[0].txtCorrSubj.value='';			
			document.forms[0].txtCorrNo.value=" ";
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
	var url='hdiits.htm?actionFlag=srchCorrStatus&docName='+document.forms[0].txtCorrSubj.value+'&corrStatusId='+document.forms[0].selCorrStatus.value+'&CorrNo='+document.forms[0].txtCorrNo.value+'&lookupstagename='+'${CorrStage}'+'&strtDt='+document.forms[0].txtStartDate.value+'&endDt='+document.forms[0].txtEndDate.value+'&fname='+document.forms[0].empFName.value+'&mname='+document.forms[0].empMName.value+'&lname='+document.forms[0].empLName.value+'&ldesc='+document.forms[0].txtDesc.value;
	if('${CorrStage}' == 'fms_Approve')
		url+="&moduleName=WorkList&menuName=forRecordCommonApprove";
	if('${CorrStage}' == 'fms_Pending')
		url+="&moduleName=RecordRoom&menuName=forRecPending";
	window.location=url;	
}

function loadMails(no)
{
	if(no == 0)
	{
		var url='hdiits.htm?actionFlag=LoadCorrStatus&stage=fms_Pending&moduleName=RecordRoom&menuName=forRecPending&flag=0';
	}
	else if(no == 1)
	{
		var url='hdiits.htm?actionFlag=LoadCorrStatus&stage=fms_Pending&moduleName=RecordRoom&menuName=forRecPending&flag=1';
	}
	else if(no == 2)
	{
		var url='hdiits.htm?actionFlag=LoadCorrStatus&stage=fms_Pending&moduleName=RecordRoom&menuName=forRecPending&flag=2';
	}
	document.forms[0].action=url;
	document.forms[0].submit();
}
function pullJobs()
{		
	if(parent.arr_corr.length != 0)
	{
		document.getElementById('hiddenJObIds').value = parent.arr_corr;
		document.getElementById('hiddenDocIds').value = parent.doc_corr;
		document.getElementById('hiddenHierarchyFlag').value = parent.hierarchy_corr;
		var url='hdiits.htm?actionFlag=pullJobs&docType=2&moduleName=RecordRoom&menuName=forRecPending';
		parent.arr_corr.splice(0, parent.arr_corr.length);
		parent.doc_corr.splice(0, parent.doc_corr.length);
		parent.hierarchy_corr.splice(0, parent.hierarchy_corr.length);
		parent.count_corr = 0;
		parent.arr_file.splice(0, parent.arr_file.length);
		parent.doc_file.splice(0, parent.doc_file.length);
		parent.hierarchy_file.splice(0, parent.hierarchy_file.length);
		parent.count_file = 0;
		document.forms[0].action=url;
		document.forms[0].submit();
	}
	else
	{
		alert("No Job Selected...");
	}	
}

function show(corrid, docid, hierarchy)
{
	if(document.getElementById(corrid).checked==true)
	{
		if(parent.count_corr != 4)
		{
			parent.count_corr++;
			parent.arr_corr.push(corrid);
			parent.doc_corr.push(docid);
			parent.hierarchy_corr.push(hierarchy);
			parent.arr_file.splice(0, parent.arr_file.length);
			parent.doc_file.splice(0, parent.doc_file.length);
			parent.hierarchy_file.splice(0, parent.hierarchy_file.length);
			parent.count_file = 0;
		}
		else
		{
			document.getElementById(corrid).checked = false;
			alert("Five Jobs can be pulled at once...");
		}
		
	}
	else
	{
		for(var i=0;i<parent.arr_corr.length;i++)
		{
			if(parent.arr_corr[i] == corrid)
			{
				parent.count_corr--;
				parent.arr_corr.splice(i,1);
				parent.doc_corr.splice(i,1);
				parent.hierarchy_corr.splice(i,1);
			}
		}
	}
}

function show_approve(corrid)
{
	if(document.getElementById(corrid).checked==true)
	{
		parent.arr_corr_approve.push(corrid);
	}
	else
	{
		for(var i=0;i<parent.arr_corr_approve.length;i++)
		{
			if(parent.arr_corr_approve[i] == corrid)
			{
				parent.arr_corr_approve.splice(i,1);
			}
		}
	}
}

function getCorrList()
{
		if(parent.arr_corr_approve=='')
		{
			alert("Select Atleast One Correspondence");
		}
		else
		{
			if(confirm("Do You want to Resubmit Correspondence?"))
			{
				document.forms[0].action='hdiits.htm?actionFlag=resubmitCorrespondence&corrId='+parent.arr_corr_approve;
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
