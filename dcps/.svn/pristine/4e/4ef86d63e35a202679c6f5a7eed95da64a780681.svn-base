<%
try {
	

%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<c:set var="Action" value="${resValue.wfAction}"></c:set>
<c:set var="postId_Hidden" value="${resValue.toPost}"></c:set>
<c:set var="sendBackTo" value="${resValue.sendBackTo}"></c:set>

<hdiits:form name="CorrespondenceMsg" method="POST" action="./hdiits.htm"  validate="true" >
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
<hdiits:hidden name="postId_Hidden" default="${resValue.toPost}"/>
<hdiits:hidden name="actionType" default="${resValue.wfAction}"/>
<hdiits:hidden name="isMark" default="${resValue.isMark}"/>
<hdiits:hidden name="markedList" default="${resValue.postList}"/>
<hdiits:hidden name="postListForPool" default="${resValue.postListForPool}"/>
<hdiits:hidden name="commonPoolItemFlag" default="${resValue.commonPoolItemFlag}"/>
<hdiits:hidden name="roleId_Hidden" default="${resValue.roleId_Hidden}"/>
<hdiits:hidden name="isNormalHierachySelected" default="${resValue.isNormalHierachySelected}"/>
<hdiits:hidden name="isDocAlreadyMarked" default="${resValue.isDocAlreadyMarked}"/>
<hdiits:hidden name="onApproveForwardFlag" default="${resValue.onApproveForwardFlag}"/>
<hdiits:hidden name="approveDraftFlag" default="${resValue.approveDraftFlag}"/>
<hdiits:hidden name="forwardFromSupportHandler" default="${resValue.forwardFromSupportHandler}"/>
<hdiits:hidden name="winName" default="${resValue.winName}"/>
<hdiits:hidden name="rejectflag" default="${resValue.rejectflag}"/>
<hdiits:hidden name="jobOwnerPostId" default="${resValue.jobOwnerPostId}"/>
<hdiits:hidden name="fromCommonHomePage" default="${resValue.fromCommonHomePage}"/>


</hdiits:form>
<script>
var intervalid;

		var action;
		var substring = document.getElementById("actionType").value;	
		if(document.getElementById("actionType").value=="forward")
			action="${contextPath}/hdiits.htm?actionFlag=forwardFile&sendBackTo=${sendBackTo}";
		else if (document.getElementById("actionType").value=="Return")
			action="${contextPath}/hdiits.htm?actionFlag=returnFile&sendBackTo=${sendBackTo}"
		else if (document.getElementById("actionType").value=="Approve")
			action="${contextPath}/hdiits.htm?actionFlag=approveFile&sendBackTo=${sendBackTo}"
		else if (document.getElementById("actionType").value=="ReturnDown")
			action="${contextPath}/hdiits.htm?actionFlag=returnDownFile&sendBackTo=${sendBackTo}";
		else if (document.getElementById("actionType").value=="releaseGroupJob")
			action="${contextPath}/hdiits.htm?actionFlag=releaseGroupJob&sendBackTo=${sendBackTo}";
		else if (document.getElementById("actionType").value=="holdGroupJob")
			action="${contextPath}/hdiits.htm?actionFlag=holdGroupJob&sendBackTo=${sendBackTo}";
		else if (document.getElementById("actionType").value=="AssignItemToGroup")
			action="${contextPath}/hdiits.htm?actionFlag=AssignItemToGroup&sendBackTo=${sendBackTo}";

		if (substring=="Updated Successfully")
		{
			action=parent.document.getElementById("myFrame").src;
				
		}
				
		document.getElementById("CorrespondenceMsg").method="post";	
		document.getElementById("CorrespondenceMsg").action=action
		document.getElementById("CorrespondenceMsg").submit();	
		
		
	
	

	
	
		
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>