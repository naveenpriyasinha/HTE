<%
try{
	

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="wfLables"	scope="request" />


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="attachmentList" value="${resultMap.AttachmentNames}"></c:set>
<c:set var="AttachmentUrl" value="${resultMap.AttachmentUrl}"></c:set>
<c:set var="fmsFileTabDtls" value="${resultMap.fmsFileTabDtls}"></c:set>
<c:set var="fileId" value="${resultMap.fileId}"></c:set>
<c:set scope="session" var="ServiceObj" value="${resultMap.ServiceObj}"></c:set>
<c:set var="locId" value="${resultMap.locId}"></c:set>
<c:set var="branchId" value="${resultMap.branchId}"></c:set>
<c:set var="deptId" value="${resultMap.deptId}"></c:set>
<c:set var="ctgryId" value="${resultMap.ctgryId}"></c:set>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

	<hdiits:form name="filedraftForm" validate="true" action="./hdiits.htm" encType="multipart/form-data">
	<hdiits:hidden name="type"  />
	
	<fmt:message key="WF.Select" bundle="${wfLables}" var="select"></fmt:message>
	
	<table width="100%">
	<tr>					
					<td width="10%">
						<hdiits:radio name="radio" id="Subject" value="Subject" captionid="WF.RADIOSUBJECT" bundle="${wfLables}" default="Subject" onclick="ajaxfunction('subject')" />
					</td>					
					<td width="10%">	
						<hdiits:radio name="radio" id="Common" value="Common" captionid="WF.RADIOCOMMON" bundle="${wfLables}"  onclick="ajaxfunction('common')" />
					</td>					
					<td></td>
		
					<td class="fieldLabel"  align="right">
						<hdiits:caption  captionid="WF.TEMPLATETYPE" bundle="${wfLables}"/>
					</td>
					<td></td>
					<td >
						<hdiits:select name="cmbTemplate" mandatory="true" validation="sel.isrequired" captionid="WF.TEMPTYPE" bundle="${memoLables}">
							<hdiits:option value="-1">${select}</hdiits:option>
							<c:forEach items="${resultMap.templateList}" var="template">
									<hdiits:option value="${template.srNo}">${template.templateType}</hdiits:option>
							</c:forEach>	
						</hdiits:select>
					</td>	
					<td >
						<hdiits:button type="button" name="DownLoadButton" id="DownLoadButton" captionid="WF.DOWNLOAD" bundle="${wfLables}" onclick="downloadAttachment()" />
					</td>	
					
	</tr>	
	
	</table>
	
	<table>
	    <tr> </tr>
	    <tr>  
	    
	     
	     <td width="10%"> <hdiits:a href="javascript:createDraft();" captionid="WF.CREATE" bundle="${wfLables}"></hdiits:a> </td>
	     
	     <td width="4%"></td>	     
		 
		 <td width="10%"> <hdiits:a href="javascript:update();" captionid="WF.UPDATE" bundle="${wfLables}"></hdiits:a> </td>
		 <td width="4%"></td>		
		 
		  <td width="10%"> <hdiits:a href="javascript:approveDraftAction();" captionid="WF.APPROVE" bundle="${wfLables}"></hdiits:a> </td>
		 <td width="4%"></td>
		 <td></td> <td></td> <td></td>
    	<c:if test="${appChkCount ne 0}"> 	
    		
    		<td width="10%"> <hdiits:a href="javascript:doNotIssueDraftAction();" captionid="WF.DONOTISSUE" bundle="${wfLables}"></hdiits:a> </td>	    		
    	</c:if>     	
		<td width="4%"></td>	
		<td width="10%"> <hdiits:a href="javascript:referenceDoc();" captionid="WF.METADATA" bundle="${wfLables}"></hdiits:a> </td>					
	   
	    </tr>
	    		       
   </table>
	
	<hdiits:hidden name="pkval"/>	
	<hdiits:hidden name="pkvalForApprove"/>	
	<hdiits:hidden name="pkvalForDoNotIssue"/>	
	<hdiits:hidden name="formname" default="filedraftForm"/>
	<hdiits:hidden name="fileId" default="${fileId}"/>
	<hdiits:hidden name="attachmentUniqeName" default="DocumentAttachmentDraft" />
	<hdiits:hidden name="rowIndex" default="1" />
	<hdiits:hidden name="rowNumber" default="1" />
	<hdiits:hidden name="refCtgry"/>
	<hdiits:hidden name="draftRefDocFlag"/>
	<hdiits:hidden name="ComOrSubjectDraftFlag" default="subject"/>
	<hdiits:hidden name="templateType"/>
	
	<br><br>
	<table width="100%">	
	<c:set var="pkcounter" value="0"> </c:set>	
	<c:set var="counter" value="0"> </c:set>
	<c:set var="appChkCount" value="0"> </c:set>
		<display:table list="${fmsFileTabDtls}" pagesize="12" requestURI="" id="row"  style="width:100%"  >
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML">
			
			
			<c:if test="${row.status eq 'PENDING'}">
				<c:if test="${resultMap.selectedSrNo eq row.srNo}">
					<input type="radio" name="approveRadio"  value="${row.srNo}" checked="checked" onclick="setDefaultValue('${row.refCtgryMpgId} ','${row.metaDataExist}')"/>					
					
					<script>					
					document.getElementById('pkval').value='${row.srNo}';
					document.getElementById('draftRefDocFlag').value='${row.metaDataExist}';
					document.getElementById('refCtgry').value='${row.refCtgryMpgId}';			
					</script>
					
				</c:if>
				<c:if test="${resultMap.selectedSrNo ne row.srNo}">
					<input type="radio" name="approveRadio"  value="${row.srNo}" onclick="setDefaultValue('${row.refCtgryMpgId} ','${row.metaDataExist}')"/>
				</c:if>				
				
				
				</c:if>
				<c:if test="${row.status eq 'APPROVED'}">
				
					<c:if test="${resultMap.selectedSrNo eq row.srNo}">
						<hdiits:checkbox name="doNotIssueChkBox" id="doNotIssueChkBox${row.count}"  value="1" default="1" onclick="doNotIssueDraft('${row.srNo}',this,'${row.refCtgryMpgId}')"/>					
						
					<script>	
					document.getElementById('pkvalForDoNotIssue').value='${row.srNo}';
					document.getElementById('refCtgry').value='${row.refCtgryMpgId}';									
					</script>										
					</c:if>
					<c:if test="${resultMap.selectedSrNo ne row.srNo}">
						<hdiits:checkbox name="doNotIssueChkBox" id="doNotIssueChkBox${row.count}"  value="1"  onclick="doNotIssueDraft('${row.srNo}',this,'${row.refCtgryMpgId}')"/>					
					</c:if>											
					
				</c:if>				
				
				</display:column>
			
			
			
			<display:column  titleKey="WF.DraftName" headerClass="datatableheader"><a href="javascript:getDraftForAttachment('${row.url}')" >${row.draftName} </a></display:column>
		
			<display:column titleKey="WF.Date" headerClass="datatableheader">${row.date}</display:column>
			
			<display:column titleKey="WF.user" headerClass="datatableheader" >${row.userName}</display:column>			
			
			<display:column titleKey="WF.LstUpdatedUsr" headerClass="datatableheader" >${row.postName}</display:column>			
			
			<display:column titleKey="WF.Description" headerClass="datatableheader">${row.status}</display:column>
		
		</display:table>		
		<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp" >
			 <jsp:param name="attachmentName" value="DocumentAttachmentDraft" />
	         <jsp:param name="formName" value="filedraftForm" />
	         <jsp:param name="attachmentType" value="Document" />
	         <jsp:param name="attachmentTitle" value="Dratf" />  
	         <jsp:param name="multiple" value="Y" />
             <jsp:param name="rowNumber" value="1" />
	    </jsp:include>    		    
	</table>
	 
	<br><br><br><br><br>
	
	  
	  <br><br>
	  <table border="1" width="100%">
		  <tr>	<!--    
			  <iframe src="./servlet/FileOpenServlet?docId=10001030881&attachmentId=<%=request.getParameter("attachmentId")%>&attachmentSerialNumber=<%=request.getParameter("attachmentSerialNumber")%>" name="frameNameDyn" id="frame_id"  width="100%" marginwidth="0" marginheight="0" scrolling="auto">
			  </iframe>-->	   		 
		  </tr>
	  </table>
	  </hdiits:form>	

<script>

function createDraft()
{
	var action;	
	if(document.forms[0].chkbox!=undefined)
		var arrLength = document.forms[0].chkbox.length;	
	var flag = false;
	var counter = 0;
	if(document.getElementById('cntDocumentAttachmentDraft').value==0)
	{
		alert("Please specify atleast one draft to create");
		return;
	}
	
	
	if(document.getElementById('cmbTemplate').value==-1)
	{
		alert('please select at least one type of Draft category');
		return;
	}		

	
	var radioObj = document.getElementsByName('approveRadio');
	var radLength = document.getElementsByName('approveRadio').length;
	var flag=true;
	for(var cnt=0;cnt<radLength && flag==true ;cnt++)
	{
		radioObj[cnt].checked = false;
		
	}
	
	var draftId=document.getElementById('cmbTemplate').value;
	//alert(draftId);
	var index=draftId.lastIndexOf("=") ;	
	draftId=draftId.substr(index+1);
	document.getElementById('templateType').value=draftId;
	//alert('draft Id is'+draftId);
	
	
		action="${contextPath}/hdiits.htm?actionFlag=Inserttabdetail";
		document.getElementById("filedraftForm").method="post";
		document.getElementById("filedraftForm").action=action;	
		document.getElementById("filedraftForm").submit();
	
	
}

function approveDraftAction()
{
		//alert(document.getElementById('draftRefDocFlag').value);
		var radioObj = document.getElementsByName('approveRadio');
		var radLength = document.getElementsByName('approveRadio').length;
		var flag=true;
		
		if(document.getElementById('draftRefDocFlag').value==1){
			
			for(var cnt=0;cnt<radLength && flag==true ;cnt++)
			{
				if(radioObj[cnt].checked == true)
				{
					flag=false;
					var id=radioObj[cnt].value;
					
					///alert('approval id is :::'+id);
					document.getElementById('pkval').value=id
					
					action="${contextPath}/hdiits.htm?actionFlag=approveDraft";
					document.getElementById("filedraftForm").method="post";
					document.getElementById("filedraftForm").action=action;	
					document.getElementById("filedraftForm").submit();
					
				}
			}
			
			if(flag==true)
			{
				alert('please select atleast one draft to approve');
				return;
			}
		}else if(document.getElementById('draftRefDocFlag').value==0)
		{
		
			for(var cnt=0;cnt<radLength && flag==true ;cnt++)
			{
				if(radioObj[cnt].checked == true)
				{
					flag=false;
					var id=radioObj[cnt].value;	
					
					//alert('id of approve::'+id);
					
					var urlStyle ='width=600,height=750,toolbar=no,menubar=no,location=no,top=150,left=200'; 
					var ctgryId=document.getElementById('refCtgry').value;	
						
					var url='hdiits.htm?actionFlag=fms_checkMetaData&fromdraftFlag=Y&pkval='+id+'&locCode='+'${locId}'+'&branch='+'${branchId}'+'&departmentId='+'${deptId}'+'&CategoryTemplateMpgCode='+ctgryId;
					//alert(url);
					window.open(url,'test',urlStyle);
					
				}
			}
		}
		else
		{	
			alert('this file is approved');
			return;
		}
		

}

function doNotIssueDraft(srNo,src,refDocId){
		clearAllRadio();
		document.getElementById('refCtgry').value=refDocId;	
		if(src.checked == true)
		{			
			if(document.getElementById("pkvalForDoNotIssue").value=='')
				document.getElementById("pkvalForDoNotIssue").value=srNo
			else
				document.getElementById("pkvalForDoNotIssue").value=document.getElementById("pkvalForDoNotIssue").value+','+srNo;
		}else
		{			
			var value=document.getElementById("pkvalForDoNotIssue").value;				
			var index=value.lastIndexOf(srNo);		
			value1=value.substring(0,index-1);			
			if(value1==''){
			var index2=value.indexOf(',');
			//alert('second index'+index2);
			
			if(index2==-1)	
				value2='';
			else
				value2=value.substring(index2+1,value.length);
				
			//alert('value2'+value2);
			}
			else{					
					var index1=value.indexOf(',',index);
					if(index1==-1)
						value2='';
					else
						value2=value.substring(index1,value.length);					
				}
			
			value=value1+value2;
			
			document.getElementById("pkvalForDoNotIssue").value=value;		
		}	
}


function clearAllRadio()
{
	var radioObj = document.getElementsByName('approveRadio');
	var radLength = document.getElementsByName('approveRadio').length;
	document.getElementById('pkval').value='';
	for(var cnt=0;cnt<radLength ;cnt++)
	{
		if(radioObj[cnt].checked == true)
		{
				radioObj[cnt].checked = false;
		}
	}

}


function doNotIssueDraftAction()
{
	var arrLength=0;
	var action="${contextPath}/hdiits.htm?actionFlag=doNotIssueDraft";
	
	//alert('Do Not Issue Id :::'+document.getElementById('pkvalForDoNotIssue').value);
	
	if(document.getElementById('pkvalForDoNotIssue').value!=''){
		if(confirm('Are You Sure To Do Not Issue'))
		{
			document.getElementById("filedraftForm").method="post";
			document.getElementById("filedraftForm").action=action	
			document.getElementById("filedraftForm").submit();
		}
	}else{
			alert('please select atleast one draft to do no issue');
			return;
	}
}
function update(){

		var radioObj = document.getElementsByName('approveRadio');
		var radLength = document.getElementsByName('approveRadio').length;
		var flag=true;
		for(var cnt=0;cnt<radLength && flag==true ;cnt++)
		{
			if(radioObj[cnt].checked == true)
			{
				flag=false;
				var id=radioObj[cnt].value;
				
				document.getElementById('pkval').value=id
				
				action="${contextPath}/hdiits.htm?actionFlag=updateDraft";
				document.getElementById("filedraftForm").method="post";
				document.getElementById("filedraftForm").action=action;	
				document.getElementById("filedraftForm").submit();
				
			}
		}
		
		if(flag==true)
		{
			alert('please select atleast one draft to update');
			return;
		}

}

function ajaxfunction(src){			
			try
   			{   
   			xmlHttp=new XMLHttpRequest();    
   			}
			catch (e)
			{// Internet Explorer    
				try
     			{
     			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
     			}
	    		catch (e)
	   			{
	    			try
       					{
            			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
       					}
		    		catch (e)
		    			{
		        		
		        		return false;        
		   				}
				}
			}	   
		//var url = "${contextPath}/hdiits.htm?actionFlag=showTemplates"
		var url;	
		if(src=='common'){
			url = "${contextPath}/hdiits.htm?actionFlag=showTemplates&templateType=common&fileId=${fileId}";
			document.getElementById('ComOrSubjectDraftFlag').value='common'
			}
		else{
			url = "${contextPath}/hdiits.htm?actionFlag=showTemplates&templateType=subject&fileId=${fileId}";
			document.getElementById('ComOrSubjectDraftFlag').value='subject'
			}
			
	    xmlHttp.onreadystatechange = function()
	     {
			if (xmlHttp.readyState == 4) 
			{ 
				if (xmlHttp.status == 200) 
					{
			 			try{
			 			var XMLDoc=xmlHttp.responseXML.documentElement;					
						var Subject = XMLDoc.getElementsByTagName('AttachmentList');						
						var comboid = document.getElementById('cmbTemplate');				
											
						if(comboid.length > 1)
				    		  {
				    		     clearList(comboid);
				    		  }
						for ( var i= 0 ; i < Subject.length ; i++ )
						{
							Subjectid = Subject[i].childNodes[0].text;							
	   						Subjectname = Subject[i].childNodes[1].text;	   						
	   						var element=document.createElement('option');   				
	     				
		     				element.text=Subjectid	     				
		     				element.value=Subjectname	
		     				
		     				try
						    {
						    comboid.add(element,null); // standards compliant
						    }
						    catch(ex)
						    {
						    comboid.add(element); // IE only
						    }										
						} }catch(e){
									
								} 
				
					} 
			}
		}
			
	xmlHttp.open("POST",encodeURI(url), false);
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
   	xmlHttp.send(encodeURIComponent(null));
   return true;
}





function downloadAttachment()
{
	
	
	var selectedVal=document.forms[0].cmbTemplate.value;
	
	
	
	
	/*var index=selectedVal.indexOf("templateId") ;	
	var subString1=selectedVal.substr(index)
	var subString2=selectedVal.substr(0,index)
	subString2=subString2.concat("&");
	subString2=subString2.concat(subString1);
	
	var index=subString2.indexOf("tableName") ;	
	var subString1=subString2.substr(index)
	var subString2=subString2.substr(0,index)
	subString2=subString2.concat("?");
	subString2=subString2.concat(subString1);*/
	
	if(selectedVal==-1){
		alert("please select at least one ctgry");
		return;
	
	}
	document.getElementById("filedraftForm").method="get";
	
	window.open(selectedVal);
}
		
	
function referenceDoc(){	
		var urlStyle ='width=600,height=750,toolbar=no,menubar=no,location=no,top=150,left=200'; 
		var ctgryId=document.getElementById('refCtgry').value;	
		
		if(ctgryId=='')
		{
			alert('please select at least one draft');
			return;
		}
		
		///alert(document.getElementById('refCtgry').value);		
		var url='hdiits.htm?actionFlag=fms_displayRefDocs&fromdraftFlag=Y&locCode='+'${locId}'+'&branch='+'${branchId}'+'&departmentId='+'${deptId}'+'&CategoryTemplateMpgCode='+ctgryId;
		//alert(url);
		window.open(url,'test',urlStyle);
	}


function setDefaultValue(refDocId,metadataflag){	
	document.getElementById('refCtgry').value=refDocId;		
	document.getElementById('draftRefDocFlag').value=metadataflag;
	unCheckAllCheckBox('doNotIssueChkBox');	
}

function unCheckAllCheckBox(chkObj)
{				
	var total = document.getElementsByName(chkObj).length;		
	for(var i=0;i<eval(total);i++)
	{									
		document.getElementsByName(chkObj)[i].checked=false;		
	}
	document.getElementById('pkvalForDoNotIssue').value='';
}


function getDraftForAttachment(url)
{
	
	window.open(url);
}
</script>

<%
}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>