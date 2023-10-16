<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.*"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%
ResultObject result=(ResultObject)request.getAttribute("result");
Map resultMap=(Map)result.getResultValue();
Object str =resultMap.get("fileId");
String fileId=null;
if(str!=null)
 fileId=str.toString();
String strfileId=request.getParameter("fileId"); 
String receivedDate=request.getParameter("receiveDate");
if(strfileId!=null)
	fileId=strfileId;
%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="createdFromInbox" value="${resValue.createdFromInbox}"></c:set>
<c:set var="sendBackTo" value="${resValue.sendBackTo}"></c:set>
<hdiits:form name="mainmenuform" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true" >
<hdiits:hidden name="receivedDate"/>

<script>

	document.getElementById("receivedDate").value='<%=receivedDate%>';
	
	 </script>
	 
	<c:if test="${createdFromInbox eq 'yes'}">
     <script type="text/javascript">     	
 	 	//window.parent.close();
 	 	
 	 
 	 	window.moveTo( 0, 0 );
 	 	window.resizeTo(screen.availWidth, screen.availHeight);
 	
 	 	
 	 	
     </script>
   </c:if>
	<script type="text/javascript">
	/*var urlstyle = 'toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,addressbar=no';	
	
	var child=window.open("hdiits.htm?actionFlag=showWorkFlow&fileId=<%=fileId%>&receivedDate=<%=receivedDate%>&moduleName=WorkList&menuName=forFile&winName=${resValue.winName}","Show",urlstyle);
	
	child.moveTo( 0, 0 );
	child.resizeTo( screen.availWidth, screen.availHeight );
	child.focus(); */
	
	document.forms[0].action="hdiits.htm?actionFlag=showWorkFlow&fileId=<%=fileId%>&receivedDate=<%=receivedDate%>&moduleName=WorkList&menuName=forFile&winName=${resValue.winName}&sendBackTo=${sendBackTo}";
	
	document.forms[0].submit();
</script>
</hdiits:form>

