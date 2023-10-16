<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="upldFlag" value="${resValue.upldFlag}"></c:set>
<c:set var="VOList" value="${resValue.upldedFiles}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pnsnLables" scope="request"/>

<fmt:message bundle="${pnsnLables}" key="PPMT.FILENAME" var="fileName"/>
<fmt:message bundle="${pnsnLables}" key="PPMT.UPLDDATE" var="uploadDate" />
<fmt:message bundle="${pnsnLables}" key="PPMT.UPLDSTATUS" var="uploadStatus"/>

<hdiits:form name="uploadForm" id="uploadForm" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle">
	<jsp:include page="ContributionAttachment.jsp">
		<jsp:param name="attachmentName" value="scan" />
		<jsp:param name="formName" value="uploadForm" />
		<jsp:param name="attachmentType" value="Document" />
		<jsp:param name="attachmentTitle" value="Excel" />
		<jsp:param name="multiple" value="N" />
	</jsp:include>
	</fieldset>
	<table>
		<tr>
			<td>
				<hdiits:button name="btnUpload" id="btnUpload" type="button"  value="Upload" onclick="callReadExcel()"/>
			</td>
		</tr>
</table>

</hdiits:form>

<script type="text/javascript">
	function callReadExcel()
	{
		var url = "ifms.htm?actionFlag=saveTreasuryNetExcel";
		insertExcelData(url);
		self.location.href="ifms.htm?viewName=UploadTreasuryNetData";
		//document.uploadForm.action='ifms.htm?actionFlag=saveTreasuryNetExcel';
		//document.uploadForm.submit();
			
	}
	function insertExcelData(url)
	{
		xmlHttp=GetXmlHttpObject();

		   if (xmlHttp==null)
		   {
			  // hideProgressbar();
		       return;
		   }  
		   var uri = runForm(0); 
		   url = url+uri;           
		   xmlHttp.onreadystatechange=caseStateChanged;
		   xmlHttp.open("POST",url,false);
		 //  xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   xmlHttp.send(url);
	}
	function caseStateChanged() 
	{
		 if (xmlHttp.readyState==complete_state)
	   	 { 

			// var XMLDoc=xmlHttp.responseXML.documentElement;
		    // var XmlDelvId = XMLDoc.getElementsByTagName('XMLDOC');
		    //  alert("delivery id is :"+ XmlDelvId[0].childNodes[0].text);
		   // hideProgressbar();
		     alert("Data inserted successfully");
		 }
		 
	}
</script>
