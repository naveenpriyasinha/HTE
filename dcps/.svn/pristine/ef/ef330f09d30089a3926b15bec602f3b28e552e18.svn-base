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
<c:set var="displayString" value="${resValue.DisplayString}"></c:set>

<hdiits:form name="uploadForm" id="uploadForm" encType="multipart/form-data" validate="true" method="post">
<br>
<div align="center"><h5>Error Details</h5></div>
	
	
    <div id='maindiv' style="width:100%; overflow:scroll;" align="center"> 
      <textarea  id="lop" rows="20"  cols="55" class="tabstyle" style="font-family:monospace; font-size:14px;padding-left: 25px;text-align: justify"  readonly="readonly" >
        ${displayString}
      </textarea>
    </div>
    <br><br>
    <div>
    <font color="red">Error   - File will not be Uploaded.<br>Warning - Fill will be uploaded with missing data. </font>
    </div>
    <br><br>
	<div align="center">
	
	<hdiits:button type="button" captionid="PPMT.CLOSE"  bundle="${pensionLabels}" id="btnClose" name="btnClose" onclick="winCls();" />
	</div>

</hdiits:form>