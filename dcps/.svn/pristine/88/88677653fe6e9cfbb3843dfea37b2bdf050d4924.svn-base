<%
try {
	

%>
<script>
	window.moveTo(0,0); 
</script>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="<c:url value="/script/hod/ps/common.js"/>"></script>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DepartmentList" value="${resValue.OrgDepartmentMstList}"></c:set>
<c:set var="BranchList" value="${resValue.CmnBranchList}"></c:set>
<c:set var="DocList" value="${resValue.WfDocMstList}"></c:set>
<c:set var="CorrespondenceList" value="${resValue.CmnLookupMstByCorrespondenceList}"></c:set>
<c:set var="LoginUserLoc" value="${resValue.LoginUserLoc}"></c:set>
<c:set var="LoginUserPost" value="${resValue.PostDetailsList}"></c:set>
<c:set var="docName" value="${resValue.docName}"></c:set>
<c:set var="fileIdFromResReqCorr" value="${param.responcereq}"></c:set>
<c:set var="corrCatDtlsList" value="${resValue.corrCatDtlsList}"></c:set>
<c:set var="fromflag" value="${resValue.fromflag}"></c:set>
<c:set var="typeofcorrlist" value="${resValue.typeofcorrlist}"></c:set>
<c:set var="deptCode" value="${resValue.deptCode}"></c:set>




<c:choose>
<c:when test="${resValue.SubSel eq 'Mandatory'}">
	<c:set var="mandatoryFlag"  value="true"/>
</c:when>
<c:otherwise>
	<c:set var="mandatoryFlag"  value="false"/>
</c:otherwise>
</c:choose>

<hdiits:form name="CorrespondenceForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >

<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
<hdiits:hidden name="locSearchValidation" id="id_locSrchValidate" default="m"/>
<hdiits:hidden name="flagFromWorkList" default="${resValue.fromflag}"/>
<hdiits:hidden name="responcereq" default="${param.responcereq}"/>
<hdiits:hidden name="replacedDesc" id="replacedDesc" />
<hdiits:hidden name="SubSel" id="SubSel" default="${resValue.SubSel}"/>


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	           		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.InitCorr" bundle="${fmsLables}"/></a></li>
	</ul>
</div>

<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">

	<center><font size="3" color="gray"><b> <fmt:message key="WF.CorrBasDtl"  bundle="${fmsLables}"></fmt:message></b></font></center>
		<table border="0">
			<tr>
				<td width="25%" align="left"><hdiits:caption captionid="WF.Post" bundle="${fmsLables}" /></td>
				<td  width="25%" align="left">
					<hdiits:select name="userPost" id="userPost" mandatory="true" validation="sel.isrequired" captionid="WF.Post" bundle="${fmsLables}" onchange="fetchBranchSubject()" sort="false">
						<hdiits:option value="0"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message> </hdiits:option>
							<c:forEach var="LoginUserPost" items="${LoginUserPost}">
							<c:if test="${resValue.postId eq LoginUserPost.orgPostMst.postId}">
								<option value='<c:out value="${LoginUserPost.orgPostMst.postId}"/>' selected="selected">
								<c:out value="${LoginUserPost.postName}"/></option>
							</c:if>
							<c:if test="${resValue.postId ne LoginUserPost.orgPostMst.postId}">
								<option value='<c:out value="${LoginUserPost.orgPostMst.postId}"/>'>
								<c:out value="${LoginUserPost.postName}"/></option>
							</c:if>
				 			</c:forEach>	
					</hdiits:select>
				</td>
				<td width="25%" align="left"><hdiits:caption captionid="WF.LOCNAME" bundle="${fmsLables}"/></td>
				<td  width="25%" align="left">
					<c:if test="${empty resValue.fileId}">			
						<hdiits:hidden name="userLoc" id="userLoc" default="${resValue.loginPostLocId}"/>
						<hdiits:text name="LocName" default="${resValue.loginPostLocName}" mandatory="true" validation="txt.isrequired" captionid="WF.LOCNAME" bundle="${fmsLables}" readonly="true"/>
					</c:if>
					<c:if test="${not empty resValue.fileId}">
						<hdiits:hidden name="fileCrtrLocId" default="${resValue.fileCrtrLocId}"/>
						<hdiits:text name="fileCrtrLocName" default="${resValue.fileCrtrLocName}" />
					</c:if>
				</td>
				  	
			</tr>
			<tr>
				<td width="25%" align="left"><hdiits:caption captionid="WF.Sec" bundle="${fmsLables}"/></td>
				<td  width="25%" align="left">
				<c:if test="${empty resValue.fileId}">	
				
				<hdiits:select id="dd_section" name="section" mandatory="true"  validation="sel.isrequired" captionid="WF.Sec" bundle="${fmsLables}" onchange="getSubjectsFromBranch()" sort="false">
						<hdiits:option value="0"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message></hdiits:option>
						<c:forEach var="totalbranchList" items="${resValue.totalbranchList}">
						<c:if test="${totalbranchList.branchId eq resValue.loginPostBrId }" >
								<option value='<c:out value="${totalbranchList.branchId}"/>' selected="selected">
								<c:out value="${totalbranchList.branchName}"/></option>
						</c:if>
						<c:if test="${totalbranchList.branchId ne resValue.loginPostBrId }" >
								<option value='<c:out value="${totalbranchList.branchId}"/>'>
								<c:out value="${totalbranchList.branchName}"/></option>
						</c:if>
				 		</c:forEach>	
				</hdiits:select>
				
				</c:if>
				<c:if test="${not empty resValue.fileId}">
					<hdiits:text name="fileCrtrBranchName" default="${resValue.fileCrtrBranchName}" />
					<hdiits:hidden name="branchIdFromFile" default="${resValue.fileCrtrBranchId}"/>
				</c:if>
				</td>
				<td  width="25%" align="left"><hdiits:caption captionid="WF.BrSub" bundle="${fmsLables}"/></td>
				<td width="25%" align="left">
				<c:if test="${empty resValue.fileId}">
					<hdiits:select id="dd_brSub" name="brSub" mandatory="${mandatoryFlag}" validation="sel.isrequired" captionid="WF.BrSub" bundle="${fmsLables}" condition="checkLoginUserRole()" onchange="fetchCategories('fromDoc')">
						<hdiits:option value="-1" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message></hdiits:option>
							<c:forEach var="SubListForLoginPost" items="${resValue.SubListForLoginPost}">
								<option value='<c:out value="${SubListForLoginPost.wfDocMst.docId}"/>'>
								<c:out value="${SubListForLoginPost.docName}"/></option>
				 			</c:forEach>	
					</hdiits:select>
				</c:if>
				<c:if test="${not empty resValue.fileId}">
					<hdiits:hidden name="docIdFromFile" default="${resValue.docId}"/>
					<hdiits:text name="docNameFromFile" default="${resValue.docName}" />
				</c:if>
				</td>
			</tr>
			<tr>
				<td width="25%" align="left">	<hdiits:caption captionid="WF.CorrCat" bundle="${fmsLables}"/>
				</td>
				<td  width="25%" align="left">
					<hdiits:select name="crrCat"  id="dd_corrCat"  mandatory="true" validation="sel.isrequired" captionid="WF.CorrCat" bundle="${fmsLables}" onchange="fetchCategories('fromCat')" sort="false">
						<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message></hdiits:option>
						<c:if test="${not empty resValue.fileId}">
						<c:choose>
						<c:when test="${not empty corrCatDtlsList}">
							<c:forEach var="corrCategories" items="${corrCatDtlsList}">
								<option value='<c:out value="${corrCategories.lookupName}"/>'>
								<c:out value="${corrCategories.lookupDesc}"/></option>
				 			</c:forEach>
				 		
				 		</c:when>
				 		<c:otherwise>
				 			<c:forEach var="defcorrCat" items="${resValue.defcorrCatDtlsList}">
								<option value='<c:out value="${defcorrCat.lookupName}"/>'>
								<c:out value="${defcorrCat.lookupDesc}"/></option>
				 			</c:forEach>
				 		</c:otherwise>
				 		</c:choose>
				 		<hdiits:option value="More">More</hdiits:option>
						</c:if>
						
					</hdiits:select>
				</td>
				
				
				<td  width="25%" align="left">
				<div style="display:none" id="moreCat_div">
					<hdiits:select name="moreCat" id="dropdown_moreCat"  mandatory="true" validation="sel.isrequired" captionid="WF.MoreCat" bundle="${fmsLables}">
						<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message></hdiits:option>
						
						
					</hdiits:select>
				</div>
				</td> 
				
				
				
				
			</tr>
			<tr>
				
				<td  width="25%" align="left"><hdiits:caption captionid="WF.Ref" bundle="${fmsLables}"/></td>
				<td  width="25%" align="left">
					<hdiits:text name="refNo" size="31"/>
				</td>
				<td  width="25%" align="left"><hdiits:caption captionid="WF.LtDate" bundle="${fmsLables}"/></td>
				
				<td  width="25%" align="left">
					<fmt:formatDate value="${resValue.SystemDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="SysDate"/>
					<hdiits:dateTime  name="letterDate" captionid="WF.LtDate" bundle="${fmsLables}" validation="txt.isrequired" mandatory="true" showCurrentDate="true"></hdiits:dateTime>
				</td>
				
				
			</tr>
			<tr></tr>
			<tr>
				
				<td width="25%" align="left">
						
									<hdiits:caption captionid="WF.Desc" bundle="${fmsLables}" />
									<hdiits:caption captionid="WF.LtSub" bundle="${fmsLables}" />
									
									<hdiits:caption captionid="WF.Max500" bundle="${fmsLables}"/>
							</td>
				<td  align="left" colspan="6">
					<hdiits:textarea id="lettersubDesc" name="lettersubDesc" captionid="WF.Desc" bundle="${fmsLables}" cols="85" maxlength="128"  />
					<hdiits:a href="#" onclick="openCompDesc('lettersubDesc','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
				</td>	
					
				
				
			</tr>
			
			<tr>
					<td  width="25%" align="left">
									<hdiits:caption captionid="WF.Notings" bundle="${fmsLables}"/>
									<br>
									<hdiits:caption captionid="WF.Max500" bundle="${fmsLables}"/>
					</td>
					<td   align="left" colspan="6" >
									<hdiits:textarea  name="notings" cols="85" maxlength="128"/>
									<hdiits:a href="#" onclick="openCompDesc('notings','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
					</td>	
				
			</tr>
			<tr>
					<td  width="25%" align="left">
								<hdiits:caption captionid="WF.Encl" bundle="${fmsLables}"/>
								<br>
								<hdiits:caption captionid="WF.Max500" bundle="${fmsLables}"/>
					</td>
					<td  align="left" colspan="6">
							<hdiits:textarea  name="enclousure" cols="85" maxlength="128"/>
							<hdiits:a href="#" onclick="openCompDesc('enclousure','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
					</td>
			</tr>
			<tr>
			<td width="25%" align="left">	<hdiits:caption captionid="WF.TypeOfCorr" bundle="${fmsLables}"/>
				</td>
				<td  width="25%" align="left">
			
					<hdiits:select name="type_of_corr" id="type_of_corr"   >
						<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message></hdiits:option>
						
							<c:forEach var="typeofcorrlist" items="${typeofcorrlist}">
								<option value='<c:out value="${typeofcorrlist.lookupId}"/>'>
								<c:out value="${typeofcorrlist.lookupDesc}"/></option>
				 			</c:forEach>
					</hdiits:select>
				
				</td> 
			</tr>
			
			<!--</table>-->
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td align="center" colspan="4"><font size="3" color="gray"><b><fmt:message key="WF.SenderDtl"  bundle="${fmsLables}"></fmt:message></b></font></td></tr>
			<!--  <table border="0">-->
			<tr>
				<td  width="25%" align="left">
					<hdiits:caption captionid="WF.RecFrom" bundle="${fmsLables}"/>
				</td>
				<td colspan="6"  align="left">
					<hdiits:text name="recFrom" size="83" captionid="WF.RecFrom" bundle="${fmsLables}"/>
				</td>
				
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%">
				<tr>
				<td  width="25%" align="left">
					<hdiits:caption captionid="WF.From" bundle="${fmsLables}"/>
					<hdiits:radio name="fromRad" id="fromRadId1" mandatory="true"  validation="sel.isradio" value="I"   errCaption="From"  default="I"  captionid="WF.Internal" bundle="${fmsLables}" onclick="changelocDiv()"></hdiits:radio>
					<hdiits:radio name="fromRad"  id="fromRadId2"  validation="sel.isradio" value="E" captionid="WF.External" bundle="${fmsLables}" onclick="ajaxfunction()" ></hdiits:radio>
				</td>
				
				 <td  width="25%" align="left">
				 <div id="div_ddCorrFrom" style="display:none">
					<hdiits:select name="corrFrm" id="dropdown_corrFrm" mandatory="true" validation="sel.isrequired" captionid="WF.CorrFrom" bundle="${fmsLables}" onchange="corrFrm_onchange()">
						<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message></hdiits:option>
					</hdiits:select>
					<hdiits:text name="txtcorrFrm" id="txtcorrFrm" style="display:none"/>
				</div>
				</td>
				</tr>
				</table>
				</td>
				 
			</tr>
			</table>
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
			
			<div id="corrAdd_id" style="display:none">			
 		 	<fmt:message key="WF.CorrAddress" bundle="${fmsLables}" var="addresstitle"></fmt:message>
				<jsp:include page="../common/address.jsp"> 
					<jsp:param name="addrName" value="CorrespondenceForm"/>
					<jsp:param name="addressTitle" value="${addresstitle}"/>
					<jsp:param name="addrLookupName" value="CorrespondenceForm"/>
				</jsp:include>
		  </div>
			
			
			<br>
			<br>	
			<center><font size="3" color="gray"><b><fmt:message key="WF.ADDTO"  bundle="${fmsLables}"></fmt:message></b></font></center>
			  <table border="0">
			  
				</table>
				<br>
					<table id="empSrch_Id">
					<tr>
						<td>
						<hdiits:checkbox name="empCheck" value="0" onclick="showEmpSearch()"></hdiits:checkbox><fmt:message key="WF.SelEmpToSrch" bundle="${fmsLables}"></fmt:message>
						</td>
					</tr>
					</table>
					<div id="employeeSearchId" style="display:none">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="SearchEmployee" value="EMPLOYEESEARCH"/>
						<jsp:param name="formName" value="testing"/>
						<jsp:param name="mandatory" value="No"/>
						<jsp:param name="searchEmployeeTitle" value="Select Employee to Send"/>
					</jsp:include>
					</div>
					<br>
					
					<fmt:message key="WF.CorrAttach" bundle="${fmsLables}" var="attachmentTitle"></fmt:message>
								<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						                <jsp:param name="attachmentName" value="CorrAttach" />
						                <jsp:param name="formName" value="CorrespondenceForm" />
						                <jsp:param name="attachmentType" value="Document" />   
						                <jsp:param name="attachmentTitle" value="${attachmentTitle}" />              
						          </jsp:include> 	
			  
	          
			
			<br>
			<br>
			<center>
			<c:if test="${empty fileIdFromResReqCorr}">
				<hdiits:button name="Corrsubmit_Save" captionid="WF.Save" bundle="${fmsLables}" id="bt_save" onclick="validatecontrols('Save')" type="button"/>
	      			<hdiits:button name="Corrsubmit_Cancel" captionid="WF.Cancel" bundle="${fmsLables}" type="button" onclick="closeForm()"/>
	      			<hdiits:button name="Corrsubmit_Reset" captionid="WF.Reset" bundle="${fmsLables}" type="button" onclick="checkForReset()"/>
	      		<c:if test="${empty resValue.fileId}">
	      			<hdiits:button name="Corrsubmit_SaveAndCopy" captionid="WF.SaveAndCopy" bundle="${fmsLables}" id="bt_save_copy" onclick="validatecontrols('SaveAndCopy')" type="button" />
	      			<hdiits:button name="Corrsubmit_Send" captionid="WF.Send" bundle="${fmsLables}" id="bt_send" onclick="validatecontrols('Send')" type="button" />
	      			<hdiits:button name="Corrsubmit_SendAndCopy" captionid="WF.SendAndCopy" bundle="${fmsLables}" id="bt_send_copy" onclick="validatecontrols('SendAndCopy')" type="button"/>
	      		</c:if>
	      	
	      	</c:if>
	      	<c:if test="${not empty fileIdFromResReqCorr}">
	      			<hdiits:button name="Corrsubmit_Send" captionid="WF.Send" bundle="${fmsLables}" id="bt_send" onclick="validatecontrols('Send')" type="button" />
	      			<hdiits:button name="Corrsubmit_Cancel" captionid="WF.Cancel" bundle="${fmsLables}" type="button" onclick="closeForm()"/>
	      			<hdiits:button name="Corrsubmit_Reset" captionid="WF.Reset" bundle="${fmsLables}" type="button" onclick="checkForReset()"/>
	      	</c:if>
	      	</center>
	</div>
	
			
	</div>		
	<script type="text/javascript"> 
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script> 
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>		
</hdiits:form>

<script language="javascript">
	function corrFrm_onchange()
	{
		var x=document.getElementById('dropdown_corrFrm')
		var val = x.options[x.selectedIndex].name;
		
		if(val=='Fms_Others')
		{
				document.getElementById('txtcorrFrm').style.display='';
		}
		else
		{
			if(document.getElementById('txtcorrFrm').style.display=='')
				document.getElementById('txtcorrFrm').style.display='none';
		}
		
	}

	function closeForm()
	{
		if(confirm('<fmt:message key="WF.EXMSG"  bundle="${fmsLables}"/>'))
		{
			window.close();
		}
	}
	
	function empSelect()
	{
		if(document.getElementById('radDept').checked)
		{
			document.getElementById('employeeSearchId').style.display = 'none';	
			var radVal = document.getElementById('radDept').value;
			var urlStyle = 'height=600,width=600,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no';
			window.open('${contextPath}/hdiits.htm?actionFlag=loadDepartments','',urlStyle);
				
		}
		else if(document.getElementById('radEmp').checked)
		{
			document.getElementById('employeeSearchId').style.display = '';	
		}
	}
	
	function checkForReset()
	{
		if(confirm('<fmt:message key="WF.RSTMSG"  bundle="${fmsLables}"/>'))
		{
			document.forms[0].reset();
			return true;
		}
		else
		{
			return false;
		}
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
					
					if(XMLDoc!=null)
					{
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
	     					shortname=tableentries[i].childNodes[2].text;
	     					var element=document.createElement('option');
		     				
		     				
		     				element.text=name
		     				element.value=id
		     				element.name=shortname
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
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
	
	
function checkCase()
{
	
		//alert(document.CorrespondenceForm.crrCat(document.CorrespondenceForm.crrCat.selectedIndex).text);
		if(document.CorrespondenceForm.crrCat(document.CorrespondenceForm.crrCat.selectedIndex).text == 'Case A')
		{
			document.getElementById("tab_corr").style.display='';
		}
}

function replace(string,text,by)
{
// Replaces text with by in string
	    var strLength = string.length, txtLength = text.length;
	    if ((strLength == 0) || (txtLength == 0)) return string;
	
	    var i = string.indexOf(text);
	    if ((!i) && (text != string.substring(0,txtLength))) return string;
	    if (i == -1) return string;
	
	    var newstr = string.substring(0,i) + by;
	
	    if (i+txtLength < strLength)
	        newstr += replace(string.substring(i+txtLength,strLength),text,by);
	
	    return newstr;
}

function fetchBranchSubject()
{
	
	if('${resValue.fileId}' == '')
	{
		clearList(document.CorrespondenceForm.brSub);
		clearList(document.CorrespondenceForm.section);
		clearList(document.CorrespondenceForm.crrCat);
		clearList(document.CorrespondenceForm.moreCat);
		document.getElementById('moreCat_div').style.display='none';
		
		document.getElementById('userLoc').value="";
		document.getElementById('LocName').value="";
		var postId = document.getElementById('userPost').value;
		
		if(postId!=0)
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
		    
		   	   var url = "${contextPath}/hdiits.htm?actionFlag=getSubjectsFromBranch&id="+postId;
			   	    	  
		   	  
		   	 
		      try{
		       xmlHttp.onreadystatechange = function()
					{
						if (xmlHttp.readyState == 4) 
						{     
							if (xmlHttp.status == 200) 
							{
								var XMLDoc=xmlHttp.responseXML.documentElement;
								
								if(XMLDoc!=null)
								{
									if(XMLDoc.getElementsByTagName("Location")!=null)
									{
										var table_Loc = XMLDoc.getElementsByTagName("Location");	
										var table_Branch = XMLDoc.getElementsByTagName("Branch");	
										var table_Subject = XMLDoc.getElementsByTagName("SujectsMapped");	
										
										var comboid_branch =  document.getElementById('section');
										var comboid_sub = document.getElementById('brSub');
										
										for ( var i = 0 ; i < table_Loc.length; i++ )
					     				{
					     					document.getElementById('userLoc').value = table_Loc[i].childNodes[0].text;
											document.getElementById('LocName').value = table_Loc[i].childNodes[1].text;
									    }// end of for
										
										for ( var i = 0 ; i < table_Branch.length; i++ )
					     				{
					     				
					     					id_branch=table_Branch[i].childNodes[0].text;
					     					name_branch=table_Branch[i].childNodes[1].text;
					     					var element=document.createElement('option');
						     				
						     				element.text=name_branch;
						     				element.value=id_branch;
						     				
						     				try
										    {
										    	if(element.text!='' && element.value!='')
										    		comboid_branch.add(element,null); // standards compliant
										    }
										    catch(ex)
										    {
										   		comboid_branch.add(element); // IE only
										    }
					     					
									    }// end of for
										
										for ( var i = 0 ; i < table_Subject.length; i++ )
					     				{
					     					id_sub=table_Subject[i].childNodes[0].text;
					     					name_sub=table_Subject[i].childNodes[1].text;
					     					var element=document.createElement('option');
						     				
						     				element.text=name_sub;
						     				element.value=id_sub;
						     				
						     				try
										    {
										    if(element.text!='' && element.value!='')
										    	comboid_sub.add(element,null); // standards compliant
										    }
										    catch(ex)
										    {
										   		comboid_sub.add(element); // IE only
										    }
					     					
									    }// end of for
									    	
									  }//end of if
									}
								}
						}
				    }
			        xmlHttp.open("POST", encodeURI(url) , false);    
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
					xmlHttp.send(encodeURIComponent(null));
			}
			catch(e)
			{
			
			}	
		   }   
	  }
}


function getSubjectsFromBranch(){
	
	if('${resValue.fileId}' == '')
	{
		clearList(document.CorrespondenceForm.brSub);
		clearList(document.CorrespondenceForm.crrCat);
		clearList(document.CorrespondenceForm.moreCat);
		
		document.getElementById('moreCat_div').style.display='none';
		
		var branchId = document.getElementById('dd_section').value;
		
		if(branchId!=0)
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
		    
		   	   var url = "${contextPath}/hdiits.htm?actionFlag=getSubjectsFromBranch&branchid="+branchId;
			   	 xmlHttp.onreadystatechange = function()
					{
						if (xmlHttp.readyState == 4) 
						{     
							if (xmlHttp.status == 200) 
							{
								var XMLDoc=xmlHttp.responseXML.documentElement;
								
								if(XMLDoc!=null)
								{
									if(XMLDoc.getElementsByTagName("SujectsMapped")!=null)
									{
										
										var table_Subject = XMLDoc.getElementsByTagName("SujectsMapped");	
										
										
										var comboid_sub = document.getElementById('brSub');
										
										for ( var i = 0 ; i < table_Subject.length; i++ )
					     				{
					     					id_sub=table_Subject[i].childNodes[0].text;
					     					name_sub=table_Subject[i].childNodes[1].text;
					     					var element=document.createElement('option');
						     				
						     				element.text=name_sub;
						     				element.value=id_sub;
						     				
						     				try
										    {
										    if(element.text!='' && element.value!='')
										    	comboid_sub.add(element,null); // standards compliant
										    }
										    catch(ex)
										    {
										   		comboid_sub.add(element); // IE only
										    }
					     					
									    }// end of for
									    	
									  }//end of if
									}
								}
						}
				    }
			        xmlHttp.open("POST", encodeURI(url) , false);    
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
					xmlHttp.send(encodeURIComponent(null));    	  
		   	  
		}
	}
}





function validatecontrols(buttonParam)
{
		   var controls=null;
		   var count=0;
		  var controlnames=new Array();
		  
		   controlnames.push('userPost');
		   controlnames.push('crrCat');
		   
		   if(document.getElementById('dd_corrCat').value=='More')
			controlnames.push('moreCat'); 
		   
		   controlnames.push('letterDate');
		   controlnames.push('lettersubDesc');
		   controlnames.push('fromRad');
		   if(document.getElementById('fromRadId2').checked)
			   controlnames.push('corrFrm');
		   controlnames.push('userLoc');
		   controlnames.push('section');
		   
		   
		   
		   var subSel = document.getElementById('SubSel').value;
		  if(subSel!="Not Mandatory"){
		  	controlnames.push('brSub');
		  }
		   
		 
		  
		  var actionFlagName="insertCorrespondence";
		  
		  
		
		  if('${resValue.fileId}' == ''){
			  if(document.getElementById('dd_brSub').value == '-1')
			  	 actionFlagName="fms_insertCorrWithoutSub";
		  	 }
		 
			//alert("buttoniud"+document.getElementById('bt_save').value);
			var fileIdFromResReqCorr = '${fileIdFromResReqCorr}';
			if(fileIdFromResReqCorr != '')
			{
				var str=document.getElementById('lettersubDesc').value; 
				var converted_text=encodeBase64(str);
				var converted_text = replace(converted_text,'\n',' ');  
				document.getElementById('replacedDesc').value = converted_text;
				//alert(document.getElementById('replacedDesc').value);
			}
			
			
			var buttonId;
			if(buttonParam == 'Save')
			{
				 buttonId = buttonParam;
			}
			else if(buttonParam == 'SaveAndCopy')
			{
				  buttonId = buttonParam;
			}
			else if(buttonParam == 'Send')
			{
				 buttonId = buttonParam;
			}
			else if(buttonParam == 'SendAndCopy')
			{
				 buttonId = buttonParam;
			}
			
			var returnVal =  validateSpecificFormFields(controlnames);
			
			if(returnVal == true)
			{
			
				if(document.getElementById('id_locSrchValidate').value == "m")
				{
					if(document.getElementById('LOCATION_ID_CorrespondenceLoc').value == '')
					{
						alert("Please Select Location");
						document.getElementById('locSearch_id').focus();
						return false;
					}
					else
					{
						showProgressbar();
						document.forms[0].method='post';
						document.forms[0].action="${contextPath}/hdiits.htm?actionFlag="+actionFlagName+"&buttonId="+buttonId;
						document.forms[0].submit();
					}
				}
				else
				{
					showProgressbar();
					document.forms[0].method='post';
					document.forms[0].action="${contextPath}/hdiits.htm?actionFlag="+actionFlagName+"&buttonId="+buttonId;
					document.forms[0].submit();
				}
			}

}

function changelocDiv()
{
		document.getElementById('id_locSrchValidate').value = "m";
		document.getElementById('corrAdd_id').style.display = 'none';
		document.getElementById('locSearch_id').style.display = '';
		document.getElementById('div_ddCorrFrom').style.display = 'none';
}

function  showEmpSearch()
{
	if(document.forms[0].empCheck.checked)
		document.getElementById('employeeSearchId').style.display = '';
	else
		document.getElementById('employeeSearchId').style.display = 'none';
}

function checkLoginUserRole()
{
	var subSel = document.getElementById('SubSel').value;
		
	if(subSel=="Mandatory")
		return true;
	else
		return false;
	
	
}
if(document.getElementById('SubSel').value!='Mandatory')
{
	document.getElementById('empSrch_Id').style.display='none';	
}

function fetchCategories(decisionFlag){

    if(decisionFlag=='fromDoc')
    {
    	document.getElementById('moreCat_div').style.display='none';
		if(document.getElementById('dd_brSub').value != '-1'){
			document.getElementById('empSrch_Id').style.display='';
		}
		else{
			document.getElementById('empSrch_Id').style.display='none';	
		}
	
		clearList(document.CorrespondenceForm.crrCat);
		clearList(document.CorrespondenceForm.moreCat);
		
	}
	if(decisionFlag=='fromCat')
	{
		clearList(document.CorrespondenceForm.moreCat);
		if(document.getElementById('dd_corrCat').value!='More')
			document.getElementById('moreCat_div').style.display='none';
	}
	var docId;
	if('${resValue.fileId}' == '')
	{
		docId = document.getElementById('dd_brSub').value;
	}
	else{
		docId = '${resValue.docId}';
	}
	
	
		
	if(docId!=-1)
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
	    
	       if(decisionFlag=='fromDoc' || (decisionFlag=='fromCat' && document.getElementById('dd_corrCat').value=='More'))
	       {
	      
	       if(decisionFlag=='fromCat')
	       {
	       
	       	docId= '-1';
	       	document.getElementById('moreCat_div').style.display='';
	       }
	   	   var url = "${contextPath}/hdiits.htm?actionFlag=fms_categoriesBySubject&docId="+docId;
		   	 xmlHttp.onreadystatechange = function()
				{
					if (xmlHttp.readyState == 4) 
					{     
						if (xmlHttp.status == 200) 
						{
							var XMLDoc=xmlHttp.responseXML.documentElement;
							
							if(XMLDoc!=null)
							{
								if(XMLDoc.getElementsByTagName("Category")!=null)
								{
									
									var comboid_cat ;
									
									var table_cat = XMLDoc.getElementsByTagName("Category");	
									
									if(decisionFlag=='fromCat')
										comboid_cat = document.getElementById('dropdown_moreCat');
									else{
										comboid_cat = document.getElementById('dd_corrCat');
										
										//if(table_cat.length==1)
											//alert("There is not any specific category for this subject, Please select More to get more categories");
										
										}
									
										
									for ( var i = 0 ; i < table_cat.length; i++ )
				     				{
				     					id_cat=table_cat[i].childNodes[0].text;
				     					name_cat=table_cat[i].childNodes[1].text;
				     					var element=document.createElement('option');
					     				
					     				element.text=name_cat;
					     				element.value=id_cat;
					     				
					     				try
									    {
									    if(element.text!='' && element.value!='')
									    	comboid_cat.add(element,null); // standards compliant
									    }
									    catch(ex)
									    {
									   		comboid_cat.add(element); // IE only
									    }
				     					
								    }// end of for
								    	
								  }//end of if
								}
							}
					}
			    }
		        xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));    	  
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