<%
try {
	

%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="CorrList_incoming" value="${resValue.CorrList_incoming}"></c:set>
<c:set var="CorrList_outgoing" value="${resValue.CorrList_outgoing}"></c:set>
<c:set var="owList" value="${resValue.owCustomList}"></c:set>
<c:set var="AttachmentNames" value="${resValue.AttachmentNames}"></c:set>
<c:set var="AttachmentUrlList" value="${resValue.AttachmentUrl}"></c:set>
<c:set var="PostNameList" value="${resValue.PostNameList}"></c:set>
<c:set var="TabStatusFlag_IC" value="${resValue.TabStatusFlag_IC}"></c:set>
<c:set var="TabStatusFlag_OG" value="${resValue.TabStatusFlag_OG}"></c:set>
<c:set var="CorrCriteria" value="${resValue.CorrCriteria}"></c:set>
<c:set var="whetherFileApproved" value="${resValue.whetherFileApproved}"></c:set>
<c:set var="disableflag" value="${resValue.disableflag}"></c:set>
<c:set var="fileApprovalLookupName" value="${resValue.fileApprovalLookupName}"></c:set>
<c:set var="count_notPublished" value="${0}"></c:set>
<c:set var="dspCorrBt" value="no"/>
<c:set var="dspCorrBtFlag" value="no"/>
<c:set var="outBoxFlag" value="${resValue.outBoxFlag}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="TappalSide" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>

<hdiits:hidden name="fileTabDtlsIds" id="fileTabSrNos_hidden"/>
<hdiits:hidden name="outBoxFlag" default="${resValue.outBoxFlag}"/>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	           		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.TapSide" bundle="${fmsLables}"/></a></li>
	</ul>
</div>
<c:set var="srNo_ic" value="${1}" />
<c:set var="srNo_og" value="${1}" />
<c:set var="srNo_og_ow" value="${1}" />

<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
		<hdiits:radio name="radbut_CorrSel" value="All" default="${CorrCriteria}" captionid="WF.ALLCORR" bundle="${fmsLables}" onclick="showCorrs('All')"></hdiits:radio><br>
		<hdiits:radio name="radbut_CorrSel" value="Incoming" captionid="WF.ICCORR" bundle="${fmsLables}" onclick="showCorrs('Incoming')"></hdiits:radio><br>
		<hdiits:radio name="radbut_CorrSel" value="Outgoing" captionid="WF.OGCORR" bundle="${fmsLables}" onclick="showCorrs('Outgoing')"></hdiits:radio>
	<br>
	<br>
	<c:if test="${not empty CorrList_incoming}">
	<c:set var="cntTabActStatusList" value="${0}"></c:set>
		<center><font size="3" color="gray"><b> <fmt:message key="WF.ICCORR"  bundle="${fmsLables}"></fmt:message></b></font></center>
		<display:table list="${CorrList_incoming}" pagesize="12" requestURI="" id="row"  defaultsort="1" style="width:100%">
			<display:column titleKey="WF.SRNO" headerClass="datatableheader" sortable="true">${srNo_ic}</display:column>
			<display:column titleKey="WF.AddedCorr" headerClass="datatableheader" media="HTML"><a style="cursor:hand;"  onclick="openCorr('${row.corrId}')"><font color="blue">${row.corrNo}</font></a></display:column>
			<display:column titleKey="WF.ViewCORR" headerClass="datatableheader" media="HTML"><a style="cursor:hand;"  onclick="parent.changeFrameSrc('${row.corrId}','${param.fileId}')"><font color="blue"><fmt:message bundle="${fmsLables}" key="WF.ViewCORR"></fmt:message></font></a></display:column>
			<c:if test="${TabStatusFlag_IC eq '1' and whetherFileApproved ne 'file_approve' and outBoxFlag ne 'Yes'}">
				<display:column titleKey="WF.RMCORR" headerClass="datatableheader" media="HTML">
				<c:if test="${resValue.tabActivationStatusList[cntTabActStatusList] eq 1 and outBoxFlag ne 'Yes'}">
					<input  type="checkbox" value="${row.corrId}" name="delCorr" id="delCorr" onclick="disableOnChecked('remove')">
				</c:if>
				</display:column>
			</c:if>
			<c:if test="${outBoxFlag ne 'Yes'}">
			<display:column titleKey="WF.DPCORR" headerClass="datatableheader" media="HTML">
			
			
			<c:if test="${resValue.corrLookupList[cntTabActStatusList] eq 1 and outBoxFlag ne 'Yes'}">
			<c:set var="dspCorrBt" value="yes"/>
					<input  type="checkbox" value="${row.corrId}" name="dspCorr" id="dspCorr" onclick="disableOnChecked('dispose')">
			</c:if>
			<c:if test="${resValue.corrLookupList[cntTabActStatusList] ne 1}">
			<c:set var="dspCorrBt" value="no"/>
					<fmt:message key="WF.DISPOSED" bundle="${fmsLables}"></fmt:message>
			</c:if>
			
			
			</display:column>
			</c:if>
			
			<c:if test="${resValue.corrLookupList[cntTabActStatusList] eq 1}"> 
			<c:set var="dspCorrBtFlag" value="yes"/>
			</c:if>
			
			<c:set var="cntTabActStatusList" value="${cntTabActStatusList+1}"></c:set>
			<c:set var="srNo_ic" value="${srNo_ic+1}" />
		
		</display:table>
		<table width="100%">
		<c:if test="${not empty CorrList_incoming}">
		
		<tr>
		
		<td width="30%"></td>
		<td width="30%"></td>
		
		
		
		</tr>
		</c:if>
		
		</table>
		<br>
		<br>
		<CENTER>
		<c:if test="${not empty CorrList_incoming}">
			<c:if test="${TabStatusFlag_IC eq '1' and whetherFileApproved ne 'file_approve' and disableflag eq 'yes' and outBoxFlag ne 'Yes'}">
				<hdiits:button  name="bt_rmCorr" type="button" captionid="WF.RMCORR" bundle="${fmsLables}" onclick="operateCorrList('remove')"/>
				
			</c:if>
			<c:if test="${dspCorrBtFlag eq 'yes' and outBoxFlag ne 'Yes' }">
					<hdiits:button name="bt_dpCorr" type="button" captionid="WF.Dispose" bundle="${fmsLables}" onclick="operateCorrList('dispose')"/>
			</c:if>
		  </c:if>
		  
		</CENTER>
	</c:if>	
		<br>
		<br>
	<c:if test="${not empty CorrList_outgoing}">
		<center><font size="3" color="gray"><b> <fmt:message key="WF.OGCORR"  bundle="${fmsLables}"></fmt:message></b></font></center>
			<display:table list="${CorrList_outgoing}" pagesize="12" requestURI="" id="row"  defaultsort="1" style="width:100%">
			<display:column titleKey="WF.SRNO" headerClass="datatableheader" sortable="true">${srNo_og}</display:column>
			<display:column titleKey="WF.Drafts" headerClass="datatableheader" media="HTML"><a style="cursor:hand;" href='${row.draftURL}'><font color="blue">${row.draftName}</font></a></display:column>
			<display:column titleKey="WF.APPPOST" headerClass="datatableheader" sortable="true">${row.postNameApprove}</display:column>
			<display:column titleKey="WF.APPDATE" headerClass="datatableheader" sortable="true">${row.dateofApproval}</display:column>
				<c:if test="${row.statusFlag eq 'Y'}">
					<display:column titleKey="WF.PUBTOREFDOC" headerClass="datatableheader" media="HTML"><input  type="checkbox"   disabled="disabled"></display:column>					
				</c:if>
				<c:if test="${outBoxFlag ne 'Yes' and row.statusFlag ne 'Y'}">
					<display:column titleKey="WF.PUBTOREFDOC" headerClass="datatableheader" media="HTML"><input  type="checkbox"  id="refDocsPub_rButton_${row.fileTabDtlsSrNo}" onclick="alterRefDocsArray('${row.fileTabDtlsSrNo}')"></display:column>
					<c:set var="count_notPublished" value="${count_notPublished+1}"></c:set>
				</c:if>
			
			<c:set var="srNo_og" value="${srNo_og+1}" />
		</display:table>
		
		<c:if test="${fileApprovalLookupName eq 'file_approve' and count_notPublished gt 0}">
			<br>
			<center><hdiits:button name="bt_pushToRefDoc"  type="button" captionid="WF.PUBTOREFDOC" bundle="${fmsLables}" onclick="chageRefDocTemplateDetails()"/></center>
		</c:if>
	</c:if>
		<c:if test="${not empty owList}">
		<center><font size="3" color="gray"><b> <fmt:message key="WF.OGCORR"  bundle="${fmsLables}"></fmt:message></b></font></center>
			<display:table list="${owList}" pagesize="12" requestURI="" id="outward"  defaultsort="1" style="width:100%">
				<display:column titleKey="WF.SRNO" headerClass="datatableheader" sortable="true">${srNo_og_ow}</display:column>
				<display:column titleKey="WF.RefNo" headerClass="datatableheader" sortable="true">${outward.refNo}</display:column>
				<display:column titleKey="WF.OWNo" headerClass="datatableheader" sortable="true">${outward.owNo}</display:column>
				<display:column titleKey="WF.Attachment" headerClass="datatableheader" media="HTML"><a style="cursor:hand;" href='${outward.attchUrl}'><font color="blue">${outward.attName}</font></a></display:column>
				<display:column titleKey="WF.LOCNAME" headerClass="datatableheader" sortable="true">${outward.locName}</display:column>
				<c:if test="${outward.corrFileFlag eq '1'}">
					<display:column titleKey="WF.OWType" headerClass="datatableheader" sortable="true"><fmt:message bundle="${fmsLables}" key="WF.Corrpondence"></fmt:message></display:column>					
				</c:if>
				<c:if test="${outward.corrFileFlag eq '2'}">
					<display:column titleKey="WF.OWType" headerClass="datatableheader" sortable="true"><fmt:message bundle="${fmsLables}" key="FMS.FILE"></fmt:message></display:column>					
				</c:if>
				<c:set var="srNo_og" value="${srNo_og_ow+1}" />
			</display:table>
		</c:if>
	</div>
</div>

<script type="text/javascript">

	var fileTabDtlsSrnoArray = new Array();
	var sel_del=0;
	var sel_dis=0;
	function selectAll(chkFlg)
	{
		var chkboxObj ;
		var i;
		if(chkFlg=='select_del')
		{
			if(sel_del==1)
			{
				sel_dis=0;
			}
			else{
			chkboxObj = document.getElementsByName("delCorr");
			for(i=0;i<chkboxObj.length;i++)
			{
				chkboxObj[i].checked=true;
			}
			sel_dis=1;
			disableOnChecked('remove');
			}
		}
		else if(chkFlg=='select_dis')
		{
			if(sel_dis==1){
				sel_del=0;
			}
			else{
			chkboxObj =document.getElementsByName("dspCorr");
			for(i=0;i<chkboxObj.length;i++)
			{
				chkboxObj[i].checked=true;
			}
			disableOnChecked('dispose');
			sel_del=1;
			}
		}
		
	}
	
	function removeAll(chkFlg)
	{
		var chkboxObj ;
		var i;
		if(chkFlg=='remove_del')
		{
			chkboxObj = document.getElementsByName("delCorr");
			for(i=0;i<chkboxObj.length;i++)
			{
				chkboxObj[i].checked=false;
			}
			sel_dis=0;
			disableOnChecked('remove');

		}
		else if(chkFlg=='remove_dis')
		{
			chkboxObj =document.getElementsByName("dspCorr");
			for(i=0;i<chkboxObj.length;i++)
			{
				chkboxObj[i].checked=false;
			}
			sel_del=0;
			disableOnChecked('dispose');
		}
		
	}
	function alterRefDocsArray(fileTabDtlsSrNo)
	{
		//alert(fileTabDtlsSrNo);
		if(document.getElementById('refDocsPub_rButton_'+fileTabDtlsSrNo).checked)
		{
			//alert("in checked");
			fileTabDtlsSrnoArray.push(fileTabDtlsSrNo);
		}
		else{
			for(var cnter=0;cnter<fileTabDtlsSrnoArray.length;cnter++)
			{
				if(fileTabDtlsSrnoArray[cnter]==fileTabDtlsSrNo)
				{
					fileTabDtlsSrnoArray.splice(cnter,1);
				}
			}
		}
		//alert("viral"+fileTabDtlsSrnoArray);
		document.getElementById('fileTabSrNos_hidden').value = fileTabDtlsSrnoArray;	
	}

	function chageRefDocTemplateDetails()
	{
		document.forms[0].action='${contextPath}/hdiits.htm?actionFlag=updateRefDocTemplateDetails';
		document.forms[0].method='post';
		document.forms[0].submit();
	}
	function openCorr(corrId)
	{
		setWindowName(window, document.TappalSide);
		var urlStyle = 'toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen';
		//window.open("${contextPath}/hdiits.htm?actionFlag=viewCorrespondence&corrId="+corrId+"&tabcnt=nill","",urlStyle);
		
		window.open("${contextPath}/hdiits.htm?actionFlag=viewCorrespondence&tabcnt=3&moduleName=WorkList&menuName=forCorr&senderPage=filePage&fromDispose=true&fordisableNoting=yes&corrId="+corrId,"",urlStyle);
		
	}
	function disableOnChecked(disableChkflg)
	{	
		var chkboxObj ;
		var flag_del=0;
		var flag_dis=0;
		var chkboxObj1;
		var chkboxObj2;
		//alert('disableOnChecked');
		
		if(disableChkflg=='remove')
		{
			//alert('in remove');
			chkboxObj = document.getElementsByName("delCorr");
			for(var i=0;i<chkboxObj.length;i++)
			{
				if(chkboxObj[i].checked)
				{
					flag_dis=1;
						
					
					if(document.getElementById("bt_dpCorr"))
					document.getElementById("bt_dpCorr").disabled=true;
					break;
				}
				else
					{
					flag_dis=0;
					if(document.getElementById("bt_dpCorr"))
					document.getElementById("bt_dpCorr").disabled=false;	
					}
			}
			chkboxObj1=document.getElementsByName("dspCorr");
			if(flag_dis==1)
			{
				
				for(j=0;j<chkboxObj1.length;j++)
				{
					chkboxObj1[j].disabled=true;
				}
			}
			else
			{
				for(j=0;j<chkboxObj1.length;j++)
				{
					chkboxObj1[j].disabled=false;
				}
			}
		}
		else
		{
				//alert('in dispose');
				var j;
				
				chkboxObj = document.getElementsByName("dspCorr");
				for(j=0;j<chkboxObj.length;j++)
				{
					if(chkboxObj[j].checked)
					{
						flag_del=1;
						//if(document.getElementById("dspCorr").checked==true){
						//alert('dispose  checked condition');
						document.getElementById("bt_rmCorr").disabled=true;
						break;
					}
					else
					{
						flag_del=0;
						document.getElementById("bt_rmCorr").disabled=false;
					}
				}
				chkboxObj2=document.getElementsByName("delCorr");
				if(flag_del==1)
				{
					
					for(j=0;j<chkboxObj2.length;j++)
					{
						chkboxObj2[j].disabled=true;
					}
				}
				else{
							
					for(j=0;j<chkboxObj2.length;j++)
					{
						chkboxObj2[j].disabled=false;
					}
				}
		}
	}		
	function operateCorrList(corrDesflg)
	{
		var arr_corrIds = new Array();
		var chkboxObj ;
		var count = 0;
		var cofMsg ;
		if(corrDesflg=='remove'){
			
			cofMsg = '<fmt:message key="WF.ASKCORRDELMSG" bundle="${fmsLables}"/>';
			chkboxObj = document.getElementsByName("delCorr");
		}
		else{
			cofMsg = '<fmt:message key="WF.CHKCORRDSPOMSG" bundle="${fmsLables}"/>';
			chkboxObj = document.getElementsByName("dspCorr");
		}
		for(var i=0;i<chkboxObj.length;i++)
		{
			
			if(chkboxObj[i].checked)
			{
				++count;
				arr_corrIds.push(chkboxObj[i].value);
				arr_corrIds.toString();
			}
			
		}
		if(count==0)
		{
			alert('<fmt:message key="WF.CHKCORRDELMSG" bundle="${fmsLables}"/>');
			return false;
		}
		else
		{
			
			if(confirm(cofMsg))
			{
				if(corrDesflg=='remove'){
					document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=removeCorr&tab_id=2&corr_list="+arr_corrIds;
					document.forms[0].method="post";
					document.forms[0].submit();
				}
				else{
					var urlStyle = 'height=300,width=775,toolbar=no,minimize=no,status=yes,left=150,top=100,memubar=no,location=no,scrollbars=no';
					window.open('hdiits.htm?actionFlag=fms-disposeAll&corr_list='+arr_corrIds,'',urlStyle);
					
					//window.open('hdiits.htm?viewName=wf-BasicInfoDemo','',urlStyle);
				}
				
			}
			else
				return false;
		}
	}
	
	
    function showCorrs(corrCriteria)
    {
    var fileId = document.getElementById('fileId').value;
    if(corrCriteria=='All')
    	msgtoInsert = '<fmt:message key="WF.VIEWALLCORRMSG" bundle="${fmsLables}"/>';
    else if(corrCriteria=='Incoming')
    	msgtoInsert = '<fmt:message key="WF.VIEWICRRMSG" bundle="${fmsLables}"/>';
    else if(corrCriteria=='Outgoing')
    	msgtoInsert = '<fmt:message key="WF.VIEWOCRRMSG" bundle="${fmsLables}"/>';
	    if(confirm(msgtoInsert))
	    {
	    	document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=showaddedCorrespondence&fileId="+fileId+"&corrCriteria="+corrCriteria;
	    	document.forms[0].method="post";
			document.forms[0].submit();
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
catch(Exception e)
{
	e.printStackTrace();
}
%>