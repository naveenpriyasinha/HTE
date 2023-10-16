<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<hdiits:form name="mainWorkFlowForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true" >

<%
	String requestedURL = request.getQueryString();
	/**********************	Below is the assumption about the url *****************************/
	//viewName=wf-template&corrId=1000103066
	/**********************	End of assumption about the url *****************************/
	String corr_id = request.getParameter("corrId");
	System.out.println("final result is "+corr_id);
%>

<script>
var urlstyle = 'toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0';
var child=window.open("hdiits.htm?actionFlag=viewCorrespondence&tabcnt=3&corrId=<%=corr_id%>","Show",urlstyle);
child.moveTo( 0, 0 );
child.resizeTo( screen.availWidth, screen.availHeight );
child.focus(); 
</script>
</hdiits:form>