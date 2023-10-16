<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>
<fmt:setBundle basename="resources.hr.award.award_AlertMessages" var="awdalert" scope="request"/>
<fmt:setBundle basename="resources.hr.award.award" var="awdlbl" scope="request"/>
<html>
   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>

<script>

function closewindow()
{
	var nxtId = document.getElementById('nextId').value;
	var uId = document.getElementById('userId').value;
	document.forms[0].action = "hrms.htm?actionFlag=getDtlsFromAwdReasonRecommPage&userId"+uId+"&nextId"+nxtId;
	document.forms[0].submit();
		
}

</script>

</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lListlObj" value="${resValue.awdCatList}" > </c:set>	
<body>

<hdiits:form name="frmReason" validate="true" method="POST"	encType="multipart/form-data">

<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1"><b><hdiits:caption captionid="ess.awards" bundle="${awdlbl}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
</div>
<div class="tabcontentstyle" style="height: 25%">
<div id="tcontent1" class="tabcontent" tabno="0">
<br>
		

<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.briefDesc">
	
	<BR>
<table id="" width="80%" align="center" >
	<tr>
	<td align="center">
		<hdiits:textarea
				id="reasonOfRec"  name="reasonOfRec" rows="10" cols="120"
				validation="txt.isrequired" captionid="ess.reasnofrec"
				bundle="${awdlbl}" >
		</hdiits:textarea>
	</td>
	</tr>
	
</table>
</hdiits:fieldGroup>	
	
	<BR>
	
<table  width="80%" align="center" >
	<td colspan = "4">
 			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="awardAttach" />
			<jsp:param name="formName" value="frmReason" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N" />
			<jsp:param name="removeAttachmentFromDB" value="Y" />                
			</jsp:include>
	</td> 
	</tr>
		
</table>

<hdiits:hidden name="userId" id="userId" default='<%=request.getParameter("id")%>'/>
<hdiits:hidden name="nextId" id="nextId" default='<%=request.getParameter("nxtId")%>'/>
<hdiits:hidden name="rsnDtls" id="rsnDtls" default='<%=request.getParameter("rsnDtl")%>'/>
<script type="text/javascript">
var rsnDtls = document.getElementById('rsnDtls').value;

var rsnArray = rsnDtls.split(',');

if(rsnArray[1]!=''){
document.getElementById('reasonOfRec').value = rsnArray[1];
}







</script>
<BR>
<BR>
<BR>
<table align="center">
<tr>
	<center>
	<td></td>
	
	<td></td>
	<td>
	<hdiits:button type="button" 
		name="closeButton" value="CLOSE" id="closeButton" captionid="Close"
		onclick="closewindow()"  style="width:100px"/>
	</td>
	<td></td>
	</tr>
</table>
</div>
 

</div>
<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</body>
</html>
 <%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
  	  %>
	