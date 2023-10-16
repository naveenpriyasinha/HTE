<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="outwardNo" value="${resValue.outwardId}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="DisplayMsgOutwardCreate" method="POST" action="./hdiits.htm"  validate="true" >
<hdiits:hidden name="fileId" id="hid_fileId" default="${resValue.fileId}"/>
<hdiits:hidden name="hiddfileNo" id="hid_fileNo" default="${resValue.fileno}"/>
<hdiits:hidden name="hiddcorrno" id="hid_corrno" default="${resValue.corrid}"/>
<hdiits:hidden name="hiddlSender" id="hid_lSender" default="${resValue.lSender}"/>

	<br><br><br>
	<center>
	<h3>
		<fmt:message key="WF.OUTWDCRT" bundle="${fmsLables}"></fmt:message> <c:out value="${outwardNo}"/>
	</h3>
	<br>
	<br>
		<hdiits:button name="Corrsubmit_Cancel" captionid="WF.Ok" bundle="${fmsLables}" type="button" onclick="newFunc()"/>
	</center>
</hdiits:form>
<script type="text/javascript">
function newFunc()
{
	//alert("FileNo::"+document.getElementById('hid_fileNo').value);
	//alert("CorrNo:"+document.getElementById('hid_corrno').value);
	//alert("lfrmOutsideFlag:"+document.getElementById('hid_lSender').value);
	
	if(document.getElementById('hid_fileNo').value!="")
	{
		if(document.getElementById('hid_lSender').value=="Worklist")
		{
			window.close();		
		}
		else
		{
			window.opener.document.getElementById('Target_frame').src="hdiits.htm?actionFlag=dispOutward&txtFileNo="+document.getElementById('hid_fileNo').value;
		}		
	}
	if(document.getElementById('hid_corrno').value!='0')
	{
		if(document.getElementById('hid_lSender').value=="Worklist")
		{
			window.close();		
		}
		else
		{
			window.opener.document.getElementById('Target_frame').src="hdiits.htm?actionFlag=dispOutward&txtCorrId="+document.getElementById('hid_corrno').value;
		}		
	}
	window.close();			
}
</script>