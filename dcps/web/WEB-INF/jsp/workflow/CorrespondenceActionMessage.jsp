<%
try {
	

%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<hdiits:form name="CorrespondenceMsg" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >
<hdiits:hidden name="corrId" default="${resValue.corrId}"/>
<hdiits:hidden name="postId_Hidden" default="${resValue.toPost}"/>
<hdiits:hidden name="wfAction" default="${resValue.wfAction}"/>
<hdiits:hidden name="empName" default="${resValue.empName}"/>
<hdiits:hidden name="roleId_Hidden" default="${resValue.roleId_Hidden}"/>
<hdiits:hidden name="isNormalHierachySelected" default="${resValue.isNormalHierachySelected}"/>
<hdiits:hidden name="isDocAlreadyMarked" default="${resValue.isDocAlreadyMarked}"/>
<hdiits:hidden name="wfwindowname" default="${resValue.wfwindowname}"/>
<hdiits:hidden name="isMark" default="${resValue.isMark}"/>
<hdiits:hidden name="markedList" default="${resValue.markedList}"/>
<hdiits:hidden name="SdNotingFlag" default="${resValue.SdNotingFlag}"/>
<hdiits:hidden name="rejectflag" default="${resValue.rejectflag}"/>
<hdiits:hidden name="jobOwnerPostId" default="${resValue.jobOwnerPostId}"/>
<hdiits:hidden name="fromCommonHomePage" default="${resValue.fromCommonHomePage}"/>
<hdiits:hidden name="forwardFromSupportHandler" default="${resValue.forwardFromSupportHandler}"/>
<hdiits:hidden name="fromwithinHiearchy" default="${resValue.fromwithinHiearchy}"/>
</hdiits:form>
<script>
		if(document.getElementById("wfAction").value=="forward")
		{
		
			
			document.getElementById("CorrespondenceMsg").method="post";		
			document.getElementById("CorrespondenceMsg").action="hdiits.htm?actionFlag=forwardCorrespondence&sendBackTo=${resValue.sendBackTo}";
			document.getElementById("CorrespondenceMsg").submit();	
			
		}
		if(document.getElementById("wfAction").value=="return")
		{
			document.getElementById("CorrespondenceMsg").method="post";		
			document.getElementById("CorrespondenceMsg").action="hdiits.htm?actionFlag=returnCorrespondence&sendBackTo=${resValue.sendBackTo}";
			document.getElementById("CorrespondenceMsg").submit();	
			
		}
		if(document.getElementById("wfAction").value=="approve")
		{
				document.getElementById("CorrespondenceMsg").method="post";		
				document.getElementById("CorrespondenceMsg").action="hdiits.htm?actionFlag=approveCorrespondence&sendBackTo=${resValue.sendBackTo}";
			    document.getElementById("CorrespondenceMsg").submit();	
			  
		}
		if(document.getElementById("wfAction").value=="deactiveCorr")
		{
				document.getElementById("CorrespondenceMsg").method="post";		
				document.getElementById("CorrespondenceMsg").action="hdiits.htm?actionFlag=deactiveCorrespondece&sendBackTo=${resValue.sendBackTo}";
			    document.getElementById("CorrespondenceMsg").submit();	
			   
		}
		if(document.getElementById("wfAction").value=="returnDown")
		{
				document.getElementById("CorrespondenceMsg").method="post";		
				document.getElementById("CorrespondenceMsg").action="hdiits.htm?actionFlag=returnCorrespondence&sendBackTo=${resValue.sendBackTo}";
 			    document.getElementById("CorrespondenceMsg").submit();	
 			    
		}
		if(document.getElementById("wfAction").value=="sendToMany")
		{
				
				document.getElementById("CorrespondenceMsg").method="post";		
				document.getElementById("CorrespondenceMsg").action="hdiits.htm?actionFlag=sendToMany&sendBackTo=${resValue.sendBackTo}";
 			    document.getElementById("CorrespondenceMsg").submit();	
 			    
		}



</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>