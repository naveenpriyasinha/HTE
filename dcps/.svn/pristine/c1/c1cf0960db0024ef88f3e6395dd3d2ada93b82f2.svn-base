<%
try{
%>
<html>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<body>

<hdiits:form name="dtlsOfRsnRecPage" validate="true" method="POST"	encType="multipart/form-data" >

<!--<hdiits:hidden name="recFlag" id="recFlag" default="${resultValue.recFlag}"/>-->
<hdiits:hidden name="attachId" id="attachId" default="${resultValue.AttachId}"/>
<hdiits:hidden name="rsnOfRec" id="rsnOfRec" default="${resultValue.rsnOfRec}"/>
<hdiits:hidden name="nextId" id="nextId" default='<%=request.getParameter("nextId")%>'/>
<hdiits:hidden name="userId" id="userId" default='<%=request.getParameter("userId")%>'/>

<script type="text/javascript">


//var recFlag = document.getElementById('recFlag').value;
var attachId = document.getElementById('attachId').value;
var rsnOfRec = document.getElementById('rsnOfRec').value;
var nextId = document.getElementById('nextId').value;
var userId = document.getElementById('userId').value;




window.opener.getRsnOfRecDtls(attachId,rsnOfRec,nextId,userId);
window.close();

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
	