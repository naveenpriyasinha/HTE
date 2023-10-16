<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script type="text/javascript"  src="script/common/common.js"></script>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>


<hdiits:form name="uploadForm" id="uploadForm" encType="multipart/form-data" validate="true" method="post">
<br>

<fieldset class="tabstyle">
<jsp:include page="AGFileAttachment.jsp">
	<jsp:param name="attachmentName" value="scan" />
	<jsp:param name="formName" value="uploadForm" />
	<jsp:param name="attachmentType" value="Document" />
	<jsp:param name="attachmentTitle" value="Uploaded Files" />
	<jsp:param name="multiple" value="N" />
	<jsp:param name="readOnly" value="Y" />
</jsp:include>
</fieldset>
<br>
<br>
<div align="center">
	
	<hdiits:button type="button" captionid="PPMT.CLOSE"  bundle="${pensionLabels}" id="btnClose" name="btnClose" onclick="winCls();" />
	</div>
</hdiits:form>