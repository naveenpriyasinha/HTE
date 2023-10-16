<%
try {
	

%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>

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
<c:set var="typeofcorrlist" value="${resValue.typeofcorrlist}"></c:set>
<hdiits:form name="CorrespondenceForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >

<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
<hdiits:hidden name="locSearchValidation" id="id_locSrchValidate" default="m"/>
<hdiits:hidden name="flagFromWorkList" default="${resValue.fromflag}"/>

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
				<td width="25%" align="left">	<hdiits:caption captionid="WF.Post" bundle="${fmsLables}"/>
				</td>
				<td  width="25%" align="left">
					<hdiits:select name="userPost" id="userPost" captionid="WF.Post" bundle="${fmsLables}"  sort="false">
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
				<td  width="25%" align="left"><hdiits:caption captionid="WF.Ref" bundle="${fmsLables}"/></td>
				<td  width="25%" align="left">
					<hdiits:text id="rfn" name="refNo" size="31"/>
				</td>
			</tr>
			
			<tr>
			<td  width="25%" align="left"><hdiits:caption captionid="WF.RecvDateFrom" bundle="${fmsLables}"/></td>
				
				<td  width="25%" align="left">
					<fmt:formatDate value="${resValue.SystemDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="SysDate"/>
					<hdiits:dateTime   name="recvDateFrom" captionid="WF.RecvDateFrom" bundle="${fmsLables}" ></hdiits:dateTime>
				</td>
				
			<td  width="25%" align="left"><hdiits:caption captionid="WF.RecvDateTo" bundle="${fmsLables}"/></td>
				<td  width="25%" align="left">
					<fmt:formatDate value="${resValue.SystemDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="SysDate"/>
					<hdiits:dateTime   name="recvDateTo" captionid="WF.RecvDateTo" bundle="${fmsLables}" ></hdiits:dateTime>
				</td>
					
			</tr>
			
			<tr>
				<td  width="25%" align="left">
					<hdiits:caption captionid="WF.RecFrom" bundle="${fmsLables}"/>
				</td>
				<td  width="25%" align="left">
					<hdiits:text id="rf" name="recFrom"  size="31" captionid="WF.RecFrom" bundle="${fmsLables}"/>
				</td>
				<td>
				<hdiits:caption captionid="WF.TypeOfCorr" bundle="${fmsLables}"/>
				</td>
				<td>
					<hdiits:select name="type_of_corr" id="type_of_corr"   >
						<hdiits:option value="0" selected="true"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message></hdiits:option>
						
							<c:forEach var="typeofcorrlist" items="${typeofcorrlist}">
								<option value='<c:out value="${typeofcorrlist.lookupId}"/>'>
								<c:out value="${typeofcorrlist.lookupDesc}"/></option>
				 			</c:forEach>
					</hdiits:select>
				</td>
			</tr>
			
			</table>
			<br>

			
			<center>
			
	      			<hdiits:button name="GenRep" value="Genrate Report" id="bt_save" onclick="validatecontrols('Save')" type="button"/>
	      			
	      			<hdiits:button name="Corrsubmit_Reset" value="Reset" type="button" onclick="checkForReset()"/>
	      		
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
	


function validatecontrols(buttonParam)
{
		   
		var corrNo = document.getElementById('rfn').value;
		var rcvdFrm = document.getElementById('rf').value;
		var rcvdDtTo = document.getElementById('recvDateTo').value;
		var rcvdDtFrm = document.getElementById('recvDateFrom').value;
		var PostId = document.forms[0].userPost.value;
		var type_of_corr = document.getElementById('type_of_corr').value;
		//alert(PostId);
		showProgressbar();
		document.forms[0].method='post';
      	document.forms[0].action='${contextPath}/hdiits.htm?actionFlag=reportService&reportCode=3000000&action=generateReport&CorrNo='+corrNo+'&CrtDateTo='+rcvdDtTo+'&CrtDateFrm='+rcvdDtFrm+'&RcvdFrm='+rcvdFrm+'&PostId='+PostId+'&corrTypeId='+type_of_corr;
      //document.forms[0].action='${contextPath}/hdiits.htm?actionFlag=reportService&reportCode=1152&action=generateReport';
		
		document.forms[0].submit();
		

}





</script>


<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>