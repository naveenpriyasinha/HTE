<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
	
<fmt:setBundle basename="resources.common.cancellation.CommonCancellation" var="CmnCancel" scope="request"/>	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="i" value="1"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
 <script language="JavaScript1.2">

function disabletext(e){
return false
}

function reEnable(){
return true
}

//if the browser is IE4+
document.onselectstart=new Function ("return false")

//if the browser is NS6
if (window.sidebar){
document.onmousedown=disabletext
document.onclick=reEnable
}

function submitPage(jobId,reason)

{   if(document.frames['displayFrame'].document.getElementById('applycanc')!=null)
   {
    document.frames['displayFrame'].document.getElementById('applycanc').disabled='true';
    }
    document.getElementById('remarks').value=reason;
		document.Cancellation.action="./hrms.htm?actionFlag=applyCancellation&jobId="+jobId;
		document.Cancellation.submit();
}
</script>
    
<hdiits:form name="Cancellation" validate="true" encType="text/form-data" method="POST" action="">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="CAN.Canc" bundle="${CmnCancel}" /> </a></li>
	</ul>
</div>
<hdiits:hidden name="remarks" id="remarks"/>
<div class="tabcontentstyle" style="height: 100%">
<div id="tcontent1" class="tabcontent" tabno="0">
	<iframe frameborder="0" scrolling="no" src="./hrms.htm?viewName=CancellationMainFrame" id="mainFrm" name="mainFrm" align="center" width="100%" height="20%"></iframe>
	<iframe frameborder="0" scrolling="no" id="displayFrame" name="displayFrame" align="center" width="100%" height="280px"></iframe>
</div>
</div>
<script type="text/javascript">		
		initializetabcontent("maintab");
</script>
<hdiits:hidden name="type" id="type"/>
</hdiits:form>








<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
