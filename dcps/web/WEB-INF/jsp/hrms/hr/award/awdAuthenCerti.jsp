<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>

<fmt:setBundle basename="resources.hr.award.award" var="awdlbl" scope="request"/>
<html>
   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript">
	
function submitReq()
{	
	document.forms[0].action = "hrms.htm?actionFlag=AwardCertiAppr&flag=2";
	document.forms[0].submit();
}
		
</script>

</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="SerialNo" value="${resValue.Srno}" ></c:set>
<c:set var="AuthenCer" value="${resValue.AuthenCer}" ></c:set>
<c:set var="UserID" value="${resValue.UserID}" > </c:set>
<c:set var="ReqID" value="${resValue.ReqID}" > </c:set>
<c:set var="EmpName" value="${resValue.EmpName}" > </c:set>
<c:set var="EmpDesig" value="${resValue.EmpDesig}" > </c:set>
<c:set var="EmpAdd" value="${resValue.EmpAdd}" > </c:set>
<c:set var="EmpCity" value="${resValue.EmpCity}" > </c:set>
<c:set var="attchId" value="${resValue.attchId}" > </c:set>
<c:set var="mpgSrNo" value="${resValue.mpgSrNo}" > </c:set>

<body>

<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">

<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1"><b><hdiits:caption captionid="ess.awards" bundle="${awdlbl}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
</div>
<div class="tabcontentstyle" style="height: 100%">
<div id="tcontent1" class="tabcontent" tabno="0">
<br>
	
<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.awthenCerti" id="authenCertiHeader" collapseOnLoad="false">			
<br>
	<table id="ListTab1" name="ListTab1" class="tabtable" width="100%">
        	<tr> 
        	    	     	
			<td id="imgId">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img id="myImage" name="myImage2" src="" border="1" width="20%" height="20%">
			</td>
			<td>${EmpAdd}</td>
			
			</tr>
	</table>

	
<BR>
<BR>
<BR>

	<table id="ListTab" align="center" name="ListTab" class="tabtable">
    <tr>
    	<td></td>
    	<td></td>
		<td align="left"><p><font face="Times" size="3"><b>
		<hdiits:caption captionid="ess.auth1" bundle="${awdlbl}" captionLang="single"></hdiits:caption>
		<hdiits:caption captionid="ess.auth2" bundle="${awdlbl}" captionLang="single"></hdiits:caption>
		 <c:out value="${EmpName}"></c:out><BR><hdiits:caption captionid="ess.auth3" bundle="${awdlbl}" captionLang="single"></hdiits:caption>
		  <c:out value="${EmpDesig}"></c:out>&nbsp;<c:out value="${EmpCity}"></c:out> 
		  <BR><hdiits:caption captionid="ess.auth4" bundle="${awdlbl}" captionLang="single"></hdiits:caption>
		  <hdiits:caption captionid="ess.auth5" bundle="${awdlbl}" captionLang="single"></hdiits:caption>
		  <BR><hdiits:caption captionid="ess.auth6" bundle="${awdlbl}" captionLang="single"></hdiits:caption>
		  <hdiits:caption captionid="ess.auth7" bundle="${awdlbl}" captionLang="single"></hdiits:caption>
		</b></font></p></td>
		<td></td>
	</tr>
	<hdiits:hidden name="SrNo" id="SrNo" default="${SerialNo}"/>
	<hdiits:hidden name="UserID" id="UserID" default="${UserID}"/>
	<hdiits:hidden name="ReqID" id="ReqID" default="${ReqID}"/>
	</table>	

<BR>
<BR>
<BR>
	<center><strong>
	<c:if test="${AuthenCer eq 'Y'}">
	<hdiits:radio captionid="ess.Agree" bundle="${awdlbl}" name="ApprRejec" value="Y"
				mandatory="false" default="Y" />
	<hdiits:radio captionid="ess.Disagree" bundle="${awdlbl}" name="ApprRejec" value="N" 
				mandatory="false" /></strong></center>
	</c:if>
	<c:if test="${AuthenCer eq 'N'}">
	<hdiits:radio captionid="ess.Agree" bundle="${awdlbl}" name="ApprRejec" value="Y"
				mandatory="false" />
	<hdiits:radio captionid="ess.Disagree" bundle="${awdlbl}" name="ApprRejec" value="N" 
				mandatory="false"  default="N" /></strong></center>
	</c:if>
	
	<c:if test="${AuthenCer eq 'Nil'}">
	<hdiits:radio captionid="ess.Agree" bundle="${awdlbl}" name="ApprRejec" value="Y"
				mandatory="false" />
	<hdiits:radio captionid="ess.Disagree" bundle="${awdlbl}" name="ApprRejec" value="N" 
				mandatory="false"  default="N" /></strong></center>
	</c:if>
	
<BR>
<BR>
</hdiits:fieldGroup>
<br>
	<center>
	<hdiits:button type="button"  
		name="subButton" value="SUBMIT" id="subButton" captionid="Submit"
		onclick="submitReq()"  /></center>
	

</div>

</div>
<script type="text/javascript">		
		initializetabcontent("maintab")
		
	if('${attchId}'!='')
	{
		document.getElementById('myImage').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${attchId}'+"&attachmentSerialNumber="+'${mpgSrNo}';
	}
	else
	{
		document.getElementById('myImage').style.display = 'none';
		var obj = document.getElementById('ListTab1');		
		obj.childNodes[0].childNodes[0].childNodes[0].innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="ess.NoImgFound" bundle="${awdlbl}" />";
	}

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
	
