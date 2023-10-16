<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.hr.roster.rosterMst" var="caption" scope="request" />
<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/hrms/hr/rosterScript/rosterSpecs.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript">
	function closewindow()
	{
		
		var urlstyle="hdiits.htm?actionFlag=getHomePage"
		document.frm1.action=urlstyle;
		document.frm1.submit();
	}
</script>
<html>
	<c:set var="resultObj" value="${result}"></c:set>
	<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
   	<c:set var="msg" value="${resValue.msg}"></c:set>
  
 
   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="Hr_newrecruitreq" bundle="${caption}" /> </a></li>
	</ul>
	</div>


	<div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0"><hdiits:hidden
		name="wffileId_hidden" id="wffileId_hidden" />
<table id="Message" width="40%" align="center" >
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr align="center">
<td>  


<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br><br>
<br>
				<b> 	${msg} </b>
<br>
<br><br>
<br>
<hdiits:button type="button"
		name="closeButton" value="OK" id="closeButton" captionid="Close"
		onclick="closewindow()" style="width:120px" />
</td>
</tr>
</table>
</div>
</div>
<script type="text/javascript">		
		initializetabcontent("maintab");
</script>

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