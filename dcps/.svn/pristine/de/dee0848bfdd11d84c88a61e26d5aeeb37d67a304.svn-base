<%	try{ %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hod/ps/common.js"/>"></script> 

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables"	scope="request" />
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="cmnlookupList" value="${resultMap.cmnlookupList}"></c:set>
<c:set var="cmnlookupOutWardList" value="${resultMap.cmnlookupOutWardList}"></c:set>
<c:set var="branchList" value="${resultMap.branchList}"></c:set>
<c:set var="fmsbranchSubLst" value="${resultMap.fmsbranchSubLst}"></c:set>
<c:set var="fileNumber" value="${resultMap.fileNumber}"></c:set>
<c:set var="corrNumber" value="${resultMap.corrNumber}"></c:set>
<c:set var="sender" value="${resultMap.lSender}"></c:set>
<c:set var="deptCode" value="${resultMap.deptCode}"></c:set>



<script type="text/javascript">
var nameArr=new Array()
</script>
<hdiits:form name="OutwardForm" validate="true"  encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="hiddFileNo" default="${fileNumber}"/>
<hdiits:hidden name="hiddCorrNo" default="${corrNumber}"/>
<hdiits:hidden name="hiddOutwardId" default="0"/>
<hdiits:hidden name="hiddDraftId" default="0"/>
<hdiits:hidden name="hiddFileDesc" default="0"/>
<hdiits:hidden name="hiddName"/>
<hdiits:hidden name="hiddsender" default="${sender}"/>

<hdiits:hidden name="actionFlag" default="insertOutWardDtls"/>
<hdiits:hidden name="locSearchValidation" id="id_locSrchValidate" default="m"/>	
	<table width="100%">
		<tr>
			<td class="datatableheader">
				<hdiits:caption	captionid="FMS.REGISTER" bundle="${fmsLables}" />
			</td>
		</tr>
	</table>
	<table id="OutwardTable1" class="tabtable"> 		
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.OUTWARDNO" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
			   <hdiits:text name="txtOutwardNo" id="outwardNoID" captionid="FMS.OUTWARDNO" bundle="${fmsLables}"  validation="txt.isrequired"/>
			</td>		
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.REFNO" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
				  <hdiits:text id="OutwardRefNum" name="txtOutwardRefNum"  captionid="FMS.REFNO" bundle="${fmsLables}"/>
			</td>	
		</tr>
		<tr>			
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.FILECATEGORY" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">							
				<hdiits:select name="fileCategory" id="fileCategoryid" captionid="FMS.FILECATEGORY" bundle="${fmsLables}" mandatory="true">
					<option value="0"><fmt:message key='FMS.SELECT' bundle='${fmsLables}'/></option>						
					<c:forEach  items="${cmnlookupList}" var="fmslookuplist">
						<option value='<c:out value="${fmslookuplist.lookupId}"/>' selected="true">
							<c:out value="${fmslookuplist.lookupDesc}" />
						</option>								
					</c:forEach>						
				</hdiits:select>
			</td>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.LETTERDT" bundle="${fmsLables}" />
		 	</td>
			<td class="fieldLabel">				
				  <hdiits:dateTime name="LetterDt" captionid="FMS.LETTERDT" bundle="${fmsLables}"/>	
			</td>	 
			</tr>
			<tr>
				<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.REFNO" bundle="${fmsLables}" />
				</td>
				<td>
					 <hdiits:radio name="fileCorrRadio" id="FileRadio" value="File" mandatory="true" captionid="FMS.FILE" bundle="${fmsLables}"/>
					 <hdiits:radio name="fileCorrRadio" id="CorrRadio" value="Correspondence" mandatory="true" captionid="FMS.CORR" bundle="${fmsLables}"/>		   
				</td>		
				<td class="fieldLabel">
					 <hdiits:text id="FileCorrRefNum" name="txtFileCorrRefNum"  captionid="FMS.REFNO" bundle="${fmsLables}" mandatory="true"/>
				</td>	
		 </tr>			
		</table>		
		
		<!--  VIRAL's CODE STARTS -->
		<table class="tabtable">
		<tr>
			<td class="fieldLabel" width="7%">
				<hdiits:caption captionid="WF.From" bundle="${fmsLables}"/>
			</td>				
			<td width="25%" align="left">
				<hdiits:radio name="fromRad" id="fromRadId1" mandatory="true"  validation="sel.isradio" value="I"   errCaption="From"  default="I"  captionid="WF.Internal" bundle="${fmsLables}" onclick="changelocDiv()"></hdiits:radio>
				<hdiits:radio name="fromRad"  id="fromRadId2"  validation="sel.isradio" value="E" captionid="WF.External" bundle="${fmsLables}" onclick="ajaxfunction()" ></hdiits:radio>	
			</td>
			<td  align="left" width="25%" class="fieldLabel">
				<div id="div_ddCorrFrom" style="display:none">
					<hdiits:select name="corrFrm" id="dropdown_corrFrm" mandatory="true" validation="sel.isrequired" captionid="WF.CorrFrom" bundle="${fmsLables}">
						<hdiits:option value="0" selected="true">
							<hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/>
						</hdiits:option>
					</hdiits:select>
				</div>
			</td>
		</tr> 
		<tr>
			<td class="fieldLabel" colspan='4' width="100%">	
				<br>
				<div id="locSearch_id">
					<jsp:include page="/WEB-INF/jsp/common/SearchLocation.jsp">
						<jsp:param name="SearchLocation" value="CorrespondenceLoc"/>
						<jsp:param name="mandatory" value="Yes"/>
						<jsp:param name="searchLocationTitle" value="Location Search"/>
						<jsp:param name="deptCodes" value="${deptCode}" /> 						
					</jsp:include>
				</div>
				<br>
			</td>
		</tr>		
		<tr>		
			<td class="fieldLabel" colspan='4'>
				<div id="corrAdd_id" style="display:none">			
	 		 		<fmt:message key="FMS.ADDRESS" bundle="${fmsLables}" var="addresstitle"></fmt:message>
					<jsp:include page="../common/address.jsp"> 
						<jsp:param name="addrName" value="OutwardCorrAddress"/>
						<jsp:param name="addressTitle" value="${addresstitle}"/>
						<jsp:param name="addrLookupName" value="OutwardCorrAddress"/>
					</jsp:include>
			  	</div>
			</td>
		</tr>				
		</table>
		<!--  VIRAL's CODE Ends -->
		<table class="tabtable">
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.OUTWARDTYPE" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">			  									
				<hdiits:select name="selOutwardType" id="outwardTyp" captionid="FMS.OUTWARDTYPE" bundle="${fmsLables}" mandatory="true">
				<option value="0"><fmt:message key='FMS.SELECT' bundle='${fmsLables}'/></option>						
				<c:forEach  items="${cmnlookupOutWardList}" var="fmslookupOutWardlist">
					<option value='<c:out value="${fmslookupOutWardlist.lookupId}"/>' selected="true">
						<c:out value="${fmslookupOutWardlist.lookupDesc}" />
					</option>								
				</c:forEach>						
				</hdiits:select>
			</td>
			<td class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.ENCLOSURE" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
				<hdiits:checkbox name="chkEnclosure" value="enlcosure" />
			</td>
		</tr>	
		
		<tr>
				<td  class="fieldLabel" style="border:none" >
					<hdiits:caption	captionid="FMS.SENTTO" bundle="${fmsLables}" />
				</td>
				<td class="fieldLabel">
					<hdiits:text id="SentTo" name="txtSentTo" captionid="FMS.SENTTO" bundle="${fmsLables}"/>
					<hdiits:button type="button" name="btnSentTo" value="Add Names" onclick="addNames()"/>
				</td>											
		</tr>
		<tr>
			<td colspan="3" width="100%">									
				<table id="viewTable" border="2" width="50%" style="display:none" align="left">
							<tr>
								<th class="datatableheader">SrNo</th>
								<th class="datatableheader">Name</th>
								<th class="datatableheader">Delete</th>
							</tr>
				</table>
			</td>
		</tr>		
		</table>	
			
	<br><br><br>
	<table id="OutwardTable2" class="tabtable">
	<tr></tr>	
	<tr></tr>
		<tr>
			<td colspan="4" class="datatableheader"><hdiits:caption	captionid="FMS.SENDERDETAILS" bundle="${fmsLables}" /></td>
		</tr>		
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.FORMSECTION" bundle="${fmsLables}" />
			</td>
						
			<c:if test="${resultMap.fileNo ne '' }">
			 <td>
				<hdiits:text name="txtBranchName" id="txtBranchNameid" default="${resultMap.branchName}"></hdiits:text>				
				<hdiits:hidden name="hiddBranchId" default="${resultMap.branchId}" />
			</td>
			</c:if>	
			<c:if test="${resultMap.fileNo eq '' }">		
			<td class="fieldLabel">
				<hdiits:select name="selFormSection" id="selFormSectionid" captionid="FMS.FORMSECTION" bundle="${fmsLables}" mandatory="true" onchange="ajaxFunctionToGetDocId()">
					<option value="0"><fmt:message key='FMS.SELECT' bundle='${fmsLables}'/></option>						
					<c:forEach  items="${branchList}" var="fmsbranchList">
						<option value='<c:out value="${fmsbranchList.branchId}"/>' selected="true">
							<c:out value="${fmsbranchList.branchName}" />
						</option>								
					</c:forEach>						
				</hdiits:select>
			</td>
			</c:if>								
					
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.DISPATCHDT" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">				
				<hdiits:dateTime name="DispatchDt" captionid="FMS.DISPATCHDT" bundle="${fmsLables}"  mandatory="true"/>
			</td>
		</tr>
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.SUBJECT" bundle="${fmsLables}" />
			</td>
			<c:if test="${resultMap.fileNo ne '' }">
			 <td>
				<hdiits:text name="txtSubj" id="txtSubjid" default="${resultMap.docName}"></hdiits:text>
				<hdiits:hidden name="docId" default="${resultMap.docId}" />
			</td>
			</c:if>
			<c:if test="${resultMap.fileNo eq '' }">
			<td class="fieldLabel">
				<hdiits:select name="selSubject" id="selSubjectid" captionid="FMS.SUBJECT" bundle="${fmsLables}" mandatory="true">
					<option value="0"><fmt:message key='FMS.SELECT' bundle='${fmsLables}'/></option>						
					<c:forEach  items="${fmsbranchSubLst}" var="fmsSubjList">
						<option value='<c:out value="${fmsSubjList.wfDocMst.docId}"/>' selected="true">
							<c:out value="${fmsSubjList.wfDocMst.docName}" />
						</option>							
					</c:forEach>						
				</hdiits:select>
			</td>
			</c:if>
			
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.TICKET" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
				<hdiits:text id="Tkt" name="txtTicket"/>
			</td>
		</tr>			
	</table>
	<table class="tabtable">
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.DESC" bundle="${fmsLables}" />
			</td>			
			<td class="fieldLabel">
			<hdiits:textarea name="txtDesc" captionid="FMS.DESC" id="Desc" bundle="${fmsLables}" maxlength="1000" cols="90"></hdiits:textarea>
			<hdiits:a href="#" onclick="openCompDesc('txtDesc','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a> 
			</td>
		</tr>		
		<tr>
			<td class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.REMARKS" bundle="${fmsLables}" />
			</td>			
			<td class="fieldLabel">
			<hdiits:textarea name="txtRemarks" captionid="FMS.REMARKS" id="Rmks" bundle="${fmsLables}" maxlength="500" cols="90"></hdiits:textarea>
			<hdiits:a href="#" onclick="openCompDesc('txtRemarks','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a> 
			</td>
		</tr>	
	</table>
<br><br>		
	<fmt:message key="FMS.OUTWARDATTACH" bundle="${fmsLables}" var="attachmentTitle"></fmt:message>
	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
	    <jsp:param name="attachmentName" value="OutwardAttach" />
	    <jsp:param name="formName" value="OutwardForm" />
	    <jsp:param name="attachmentType" value="Document" />   
		<jsp:param name="attachmentTitle" value="${attachmentTitle}" />  		           
	</jsp:include> 		
<br><br>	
<br/><br/>
<table id="OutwardTable3"   align="center">
		<tr>			
			<td>
				<hdiits:button name="btnSave" type="button" value="Save" onclick="submitData()"/>
			</td>					
			<td>
				<hdiits:button name="btnReset" type="button" value="Reset" onclick="clearAll()"/>
			</td>
			<td>
			<hdiits:button type="button" name="btnOrder" value="AddOrder" onclick="show()"/>
			</td>
			<td>
			<hdiits:button type="button" name="btnClose" value="Close" onclick="javascript:window.close()"/>
			</td>
		</tr>
</table>
<br><br>
	<table id="draftid" align="center" border="2" style="display:none" width="70%">
	<tr>		
			<td align="center" class="datatableheader"><b>SrNo</b></td>
			<td align="center" class="datatableheader"><b>DraftId</b></td>
			<td align="center" class="datatableheader"><b>FileDesc</b></td>
	</tr>
	</table>
</hdiits:form>
<script type="text/javascript">
var length=0;
var srno=0;
var outwardno=0;
var chk_file_no=true;
var buttonValFile=document.getElementById('FileRadio');
var buttonValCorr=document.getElementById('CorrRadio');


function deleteRow(src)
{											
	if(confirm("Are You Sure You Want To Delete the Record?")==true)
	{	
		srno--;	
		var row1 = src.parentElement.parentElement;			
		nameArr.splice(row1.rowIndex-1,1);	
		document.forms[0].hiddName.value=nameArr;
		document.all("viewTable").deleteRow(row1.rowIndex);				
		var tabobj=document.getElementById('viewTable');
		var totrows=tabobj.getElementsByTagName("tr");
		var allTds ;
		var countRows = 1;
		for(var i=1; i<totrows.length; i++)
		{
			allTds = totrows[i].getElementsByTagName("td");
			if(allTds != null)
			if(allTds.length > 0)
			{
				allTds[0].innerHTML = countRows;
				countRows = countRows+1;
			}
		}
	}
		
}	

function addNames()
{	
	if(document.forms[0].txtSentTo.value != "")
	{		
		document.getElementById('viewTable').style.display = ''; 
		srno++;	
		var a=document.getElementById('viewTable').insertRow();
		var col1=a.insertCell(0);
		var col2=a.insertCell(1);	
		var col3=a.insertCell(2);
		col1.align="center";
		col2.align="center";							
		col3.align="center";
		col1.innerHTML=srno;
		col2.innerHTML=document.forms[0].txtSentTo.value;
		var delString = "<a href='#' style=\"cursor:hand\" onClick=\"deleteRow(this)\"><hdiits:caption captionid="WF.DELETE" bundle="${wfLables}"/></a>";											
		col3.innerHTML = delString;	
		nameArr.push(document.forms[0].txtSentTo.value);
		len=nameArr.length;		
		document.forms[0].hiddName.value=nameArr;
		document.forms[0].txtSentTo.value="";	
	}
	else
	{
		alert("Enter Some Value");
	}	
}

function show()
{	
	if(document.forms[0].fileCorrRadio[0].checked==false && document.forms[0].fileCorrRadio[1].checked==false)
	{		
		alert("<fmt:message key='FMS.ALERTFILECORR' bundle='${fmsLables}'/>");	
		document.forms[0].fileCorrRadio[0].checked=true;
		document.getElementById("FileCorrRefNum").focus();		
		return false;
	}
	if(document.forms[0].txtFileCorrRefNum.value=="")
	{			
		alert("<fmt:message key='FMS.ALERTFILECORR' bundle='${fmsLables}'/>");			
		document.getElementById("FileCorrRefNum").focus();		
		return false;		
	}	
	
	if(document.forms[0].hiddCorrNo.value!='')
	{
		var url = "${contextPath}/hdiits.htm?actionFlag=loadDrafts&corrNo="+document.forms[0].hiddCorrNo.value;		
	}		
	else if(document.forms[0].hiddFileNo.value!='')
	{			
		var url = "${contextPath}/hdiits.htm?actionFlag=loadDrafts&fileNo="+document.forms[0].hiddFileNo.value;
	}	
	else
	{		
		if(document.forms[0].fileCorrRadio[0].checked==true)
		{			
			var url = "${contextPath}/hdiits.htm?actionFlag=loadDrafts&fileCorrNum="+document.forms[0].txtFileCorrRefNum.value+"&lrdbtnval="+document.forms[0].fileCorrRadio[0].value;
		}
		else
		{			
			var url = "${contextPath}/hdiits.htm?actionFlag=loadDrafts&fileCorrNum="+document.forms[0].txtFileCorrRefNum.value+"&lrdbtnval="+document.forms[0].fileCorrRadio[1].value;
		}					
	}	
	window.open(url, '', 'status=no,toolbar=no,scrollbars=yes,width=1500,height=700');		
}		

if(document.forms[0].hiddFileNo.value!="")
{	
	document.forms[0].fileCorrRadio[0].checked=true;
	buttonValCorr.disabled=true;
	document.forms[0].txtFileCorrRefNum.value=document.forms[0].hiddFileNo.value;
	document.getElementById('FileCorrRefNum').disabled=true;
}
if(document.forms[0].hiddCorrNo.value!="")
{
	document.forms[0].fileCorrRadio[1].checked=true;	
	buttonValFile.disabled=true;
	document.forms[0].txtFileCorrRefNum.value=document.forms[0].hiddCorrNo.value;
	document.getElementById('FileCorrRefNum').disabled=true;
}

function clearAll()
{	
	document.forms[0].txtOutwardNo.value="";				
	document.forms[0].txtOutwardRefNum.value="";		
	document.forms[0].txtFileCorrRefNum.value="";			
	document.forms[0].fileCategory.value="0";
	document.forms[0].selOutwardType.value="0";
	document.forms[0].LetterDt.value="";	
	//document.forms[0].RcvdDt.value="";
	document.forms[0].DispatchDt.value="";
	document.forms[0].chkEnclosure.checked=false;
	document.forms[0].txtSentTo.value="";
	//document.forms[0].txtRcvdFrm.value="";
	document.forms[0].txtTicket.value="";
	document.forms[0].txtRemarks.value="";
	document.forms[0].txtDesc.value="";
	//document.getElementById('OutwardDetails').style.display = 'none';	
}

var srno1=0;
function displayDraftTab(outwardIdArr, fileDescArr, len)
{	
	var draftId;
	var filedesc;	
		
	document.getElementById('draftid').style.display = '';   	
	var totalRows=document.getElementById("draftid").rows.length;
	for(i=1;i<eval(totalRows);i++)
	{							
		var trow=document.getElementById('draftid');      		
		trow.deleteRow(1);				
	}	
	
	if(outwardIdArr.length=="0")
	{		
		document.getElementById('draftid').style.display = 'none';   	
	}
	for (i = 0 ; i <outwardIdArr.length ; i++ )
    { 	
   		srno1++;	
		var b=document.getElementById('draftid').insertRow();		
	    var col1=b.insertCell(0);	
		var col2=b.insertCell(1);	
		var col3=b.insertCell(2);	
		col1.align="center";
		col2.align="center";
		col3.align="center";	
	    col1.innerHTML=srno1;	 	   
	    col2.innerHTML=outwardIdArr[i]; 
		col3.innerHTML=fileDescArr[i]; 			
		draftId=draftId + "," + outwardIdArr[i];	
        filedesc=filedesc + "," + fileDescArr[i];	
	}
		document.getElementById('hiddDraftId').value=draftId; 
		document.getElementById('hiddFileDesc').value=filedesc; 		
}

function validateFileCorrNo()
	{				
		try
    	{   
    	    // Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{   
			// Internet Explorer    
			try
      		{      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
      		}
		    catch (e)
		    {
		          try
        		  {
                	      //alert("here2");
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}		    
    	if(document.forms[0].fileCorrRadio[0].checked==true)
    	{
    		var url = "${contextPath}/hdiits.htm?actionFlag=fmsValidateFileCorrNo&FileCorrNo="+document.forms[0].txtFileCorrRefNum.value+"&radioBtnVal="+document.forms[0].fileCorrRadio[0].value;
        }	
    	else
    	{
    		var url = "${contextPath}/hdiits.htm?actionFlag=fmsValidateFileCorrNo&FileCorrNo="+document.forms[0].txtFileCorrRefNum.value+"&radioBtnVal="+document.forms[0].fileCorrRadio[1].value;
        }
        xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
			
				if (xmlHttp.status == 200) 
				{			
						
					var XMLDoc=xmlHttp.responseXML.documentElement;						
					var tableentries = XMLDoc.getElementsByTagName('chkNumber');					
					for (i = 0 ; i <tableentries.length ; i++ )
     				{ 	 
						var file_flag=tableentries[i].childNodes[0].text;									
						if(file_flag=='No')
						{  
							chk_file_no=false;
							alert("<fmt:message key='FMS.NONUM' bundle='${fmsLables}'/>");							
							document.forms[0].txtFileCorrRefNum.value="";
							document.forms[0].txtFileCorrRefNum.focus();  						 	
							return false;	
						}
						else
						{
							return true;
						}							
					}									
				}				
			}
	    }		
	    xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
		xmlHttp.send(encodeURIComponent(null));				
}	
	
			

function comboFill()
{		
	if(document.forms[0].fromRad[1].checked==true && document.forms[0].corrFrm.value==0)
	{
		alert("<fmt:message key='FMS.ALERTEXTERNAL' bundle='${fmsLables}'/>");
		document.forms[0].corrFrm.focus();
		return false;
	}
	
	if(document.forms[0].fileCorrRadio[0].checked==false && document.forms[0].fileCorrRadio[1].checked==false)
	{		
		alert("<fmt:message key='FMS.ALERTFILECORR' bundle='${fmsLables}'/>");	
		document.forms[0].fileCorrRadio[0].checked=true;
		document.getElementById("FileCorrRefNum").focus();		
		return false;
	}

	if(document.forms[0].txtFileCorrRefNum.value=="")
	{			
		alert("<fmt:message key='FMS.ALERTFILECORR' bundle='${fmsLables}'/>");			
		document.getElementById("FileCorrRefNum").focus();		
		return false;		
	}	
		
	/*if(document.forms[0].RcvdDt.value=="")
	{	
		alert("<fmt:message key='FMS.ALERTRECVDDATE' bundle='${fmsLables}'/>");		
		document.forms[0].RcvdDt.focus();				
		return false;
	}*/	
	
	if(document.forms[0].DispatchDt.value=="")
	{	
		alert("<fmt:message key='FMS.ALERTDISPATCHDT' bundle='${fmsLables}'/>");	
		document.forms[0].DispatchDt.focus();							
		return false;
	}
	
	/*var txtRcvdDt = document.forms[0].RcvdDt.value;
	var txtDispatchDt = document.forms[0].DispatchDt.value;
	var day = datediff(txtRcvdDt,txtDispatchDt);	
	if(day < 0)
	{	
		alert("<fmt:message key='FMS.ALERTDATE' bundle='${fmsLables}'/>");
		document.forms[0].DispatchDt.focus();
		return false;
	}*/
	
	
	if(document.forms[0].fileCategory.value==0)
	{		
		alert("<fmt:message key='FMS.ALERTFILECATEOGRY' bundle='${fmsLables}'/>");
		document.forms[0].fileCategory.focus();
		return false;
	}	
	
	if(document.forms[0].selOutwardType.value==0)
	{			
		alert("<fmt:message key='FMS.ALERTOUTWARDTYPE' bundle='${fmsLables}'/>");
		document.forms[0].selOutwardType.focus();
		return false;
	}	
	
	if('${resultMap.fileNo}' == '')
	{
		if(document.forms[0].selFormSection.value==0)
		{		
			alert("<fmt:message key='FMS.ALERTFORMSECTION' bundle='${fmsLables}'/>");
			document.forms[0].selFormSection.focus();
			return false;
		}	
	}
	
	
	if('${resultMap.fileNo}' == '')
	{		
		if(document.forms[0].selSubject.value==0)
		{		
			alert("<fmt:message key='FMS.ALERTFILESUBJECT' bundle='${fmsLables}'/>");
			document.forms[0].selSubject.focus();
			return false;
		}
	}		
	
	chk_file_no=true;
	if(document.forms[0].hiddFileNo.value=="" && document.forms[0].hiddCorrNo.value=="")	
	{ 
		
		 validateFileCorrNo();
		 if(chk_file_no==false)
		 {		 	
			 return false;
		 }
		 else
		 {		 	
			return true;
		}	
	}
	else	
		return true;
}

function submitData()
{	
	var file=document.forms[0].fileCorrRadio[0].value
	var corr=document.forms[0].fileCorrRadio[1].value
	var rdFileCorr;
	var enclosure;
	if(document.forms[0].fileCorrRadio[0].checked==true)
	{
		rdFileCorr=file;		
	}
	else
	{
		rdFileCorr=corr;
	}
	
	
	if(document.forms[0].chkEnclosure.checked==true)
	{
		enclosure='Y';
	}
	else
	{
	   enclosure='N';
	}
	
	if(comboFill())
	{	
		/*var url = "${contextPath}/hdiits.htm?actionFlag=insertOutWardDtls&txtOutwardNo="+document.forms[0].txtOutwardNo.value+"&LetterDt="+document.forms[0].LetterDt.value+"&txtRcvdFrm="+document.forms[0].txtRcvdFrm.value+"&RcvdDate="+document.forms[0].RcvdDt.value+"&selOutwardType="+document.forms[0].selOutwardType.value+"&selOutwardType="+document.forms[0].selOutwardType.value+"&rdFileCorr="+rdFileCorr+"&fileCorrRefNo="+document.forms[0].txtFileCorrRefNum.value+"&outwardRefNo="+document.forms[0].txtOutwardRefNum.value+"&enclosure="+enclosure+"&dispatchDt="+document.forms[0].DispatchDt.value;*/	
		var url = "${contextPath}/hdiits.htm?actionFlag=insertOutWardDtls&txtOutwardNo="+document.forms[0].txtOutwardNo.value+"&LetterDt="+document.forms[0].LetterDt.value+"&selOutwardType="+document.forms[0].selOutwardType.value+"&selOutwardType="+document.forms[0].selOutwardType.value+"&rdFileCorr="+rdFileCorr+"&fileCorrRefNo="+document.forms[0].txtFileCorrRefNum.value+"&outwardRefNo="+document.forms[0].txtOutwardRefNum.value+"&enclosure="+enclosure+"&dispatchDt="+document.forms[0].DispatchDt.value+"&hidddraftId="+document.getElementById('hiddDraftId').value;		
		document.forms[0].action=url;		
		document.forms[0].submit();		
	}
}

function deleteComboValues()
{	
	   var UserEntries=document.getElementById("selSubjectid");
	   for(var i=1;i<UserEntries.length;i++)
	   {
				UserEntries.remove(i);
				i = i - 1;
	   }
}
function ajaxFunctionToGetDocId()
{		
		try
    	{   
    	    // Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{   
			// Internet Explorer    
			try
      		{      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
      		}
		    catch (e)
		    {
		          try
        		  {
                	      //alert("here2");
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}		    	
    	
    	//var selcombo = document.forms[0].selFormSection;    	
     	//var url = "${contextPath}/hdiits.htm?actionFlag=loadSubjectDtls&branchId="+selcombo[selcombo.selectedIndex].value;
     	var url = "${contextPath}/hdiits.htm?actionFlag=loadSubjectDtls&branchId="+document.forms[0].selFormSection.value;
  	    xmlHttp.onreadystatechange = function()                                                                                                       
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{	
					deleteComboValues();
					var text;
	            	var z=document.getElementById("selSubjectid");								
					var XMLDoc=xmlHttp.responseXML.documentElement;																				
					var tableentries = XMLDoc.getElementsByTagName('IdMapped');	
					for ( var i = 0 ; i < tableentries.length ; i++ )
   					{	
   						  text=tableentries[i].childNodes[0].text;   
     				      value=tableentries[i].childNodes[1].text;			     				   
     				      value = value.replace(/andand/i,'&');			     				   
     				      var y=document.createElement('option');			     				   		 					
						  y.value=text;						
						  y.text=value;														
						  try
	   					  {
	    					z.add(y,null); 			    			
	   					  }
	 					  catch(ex)
	   					  {			   			 
	   			 			z.add(y); 
	   					  }		
					}           	  
				}				
			}
	    }					
		   			xmlHttp.open("POST", encodeURI(url) , false);    
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
					xmlHttp.send(encodeURIComponent(null));
					return true;
}

function ajaxfunction()
{
		try
    	{   
    		// Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{    // Internet Explorer    
			try
      		{      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
      		   
      		}
		    catch (e)
		    {
		          try
        		  {
                	      //alert("here2");
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}
    	document.getElementById('locSearch_id').style.display = 'none';
    	document.getElementById('corrAdd_id').style.display = '';
    	document.getElementById('div_ddCorrFrom').style.display = '';
    	var fromId;
    	if(document.getElementById('fromRadId1').checked)
    	{
    		
	    	fromId = "I";
	    }
	    else if(document.getElementById('fromRadId2').checked)
	    {
	    	document.getElementById('id_locSrchValidate').value = "n";
    		fromId = "E";
    	}
        
        var url = "${contextPath}/hdiits.htm?actionFlag=getCorrespondenceFrom&fromId="+fromId;         
        xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var tableentries = XMLDoc.getElementsByTagName('CorrespondenceMapped');	
					var comboid = document.getElementById('dropdown_corrFrm')
					if(comboid.length > 1)
				    		  {
				    		     clearList(comboid);
				    		  }
           			for ( var i = 0 ; i < tableentries.length ; i++ )
     				{
     					
     					id=tableentries[i].childNodes[0].text;
     					name=tableentries[i].childNodes[1].text;
     					var element=document.createElement('option');
	     				
	     				
	     				element.text=name
	     				element.value=id
	     				
	     				try
					    {
					    comboid.add(element,null); // standards compliant
					    }
					    catch(ex)
					    {
					    comboid.add(element); // IE only
					    }

				    }// end of for
				    	
				    }//end of if
				
			}
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
	function changelocDiv()
	{
		document.getElementById('id_locSrchValidate').value = "m";
		document.getElementById('corrAdd_id').style.display = 'none';
		document.getElementById('locSearch_id').style.display = '';
		document.getElementById('div_ddCorrFrom').style.display = 'none';
	}
</script>
<% 
}catch(Exception e)
{
	e.printStackTrace();	
}

%>
