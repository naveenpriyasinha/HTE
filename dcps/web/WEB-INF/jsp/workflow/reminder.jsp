
<% try {  %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="reminderMst" value="${resValue.reminderMst}"></c:set>
<c:set var="urlList" value="${resValue.urlList}"></c:set>
<c:set var="attachmentNameList" value="${resValue.attachmentNameList}"></c:set>


<hdiits:form name="createreminder" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">

<br>

<br>
<center><h3><fmt:message key="Create Reminder" ></fmt:message></h3></center>

<table width="100%" border="1" bordercolor="green">
	
	
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CommTo" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none" >
			<hdiits:textarea id="ToListtxt" name="ToListtxt" cols="70" rows="3"  readonly="true"  default="${reminderMst.toPostList}"></hdiits:textarea>
			
			<fmt:message key="WF.SearchEmp" bundle="${fmsLables}"></fmt:message>
		</td>
		
	</tr>
	
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CC" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
			<hdiits:textarea id="CCListtxt" name="CCListtxt" cols="70" rows="3" readonly="true"   default="${reminderMst.ccPostList}"></hdiits:textarea>
				
				<fmt:message key="WF.SearchEmp" bundle="${fmsLables}"></fmt:message>				
		</td>
	</tr>	
	
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="WF.CommSubject" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="70%" style="border: none">
				<hdiits:text name="subject" size="40"  mandatory="true" default="${reminderMst.remSubject}"></hdiits:text>
		</td>
	</tr>
	<tr>
		<td width="10%" style="border: none">
			<hdiits:caption  captionid="" caption="reminder description" bundle="${fmsLables}"  />
		</td>
		<td align="right" style="border: none">
			<hdiits:caption captionid="" caption=":"  />
		</td>
		<td width="50%" style="border: none">
			<jsp:include page="/WEB-INF/jsp/workflow/old_richtext.jsp" flush="true">
				<jsp:param name="richTextName" value="1" />
			</jsp:include>
			
		</td>
		
	</tr>
	
	
	<tr>
	<td>
	<h1>Attachment</h1>
	
	</td>
	</tr>
	
	<tr>
		<td>
			<c:forEach items="attachmentNameList" var="name" varStatus="varCount">
				<a href="${urlList[varCount.count]}"><c:out value="${name}"></c:out></a>
			</c:forEach>
		</td>
	</tr>
</table>



</hdiits:form>
<script>

	var oRTE1= document.getElementById('rte1').contentWindow.document;		               		
	var string='${reminderMst.remDescription}';	
	
	var decoded_text=decodeBase64(string);
oRTE1.body.innerHTML =decoded_text; 
</script>

<% }
catch(Exception ex)  {
	ex.printStackTrace();	
}	
%>