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
<jsp:include page="AGFileAttachment.jsp">
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
				<hdiits:button name="btnReload" id="btnReload" type="button"  value="Refresh" onclick="reloadPage()"/>
			</td>
		</tr>
</table>
<br/>
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.UPLDFILES" bundle="${pnsnLables}"></fmt:message></b>
	</legend>
	<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.DELV_PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
		<display:column headerClass="datatableheader" class="oddcentre" sortable="false" style="width:5%" title="${uploadDate}">
			<div align="center"><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy h:mm a" value="${vo[1]}" /></div> 
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" sortable="false" style="width:5%" title="${uploadStatus}" >
			<div align="center">
				<a href="#" onclick="javascript:viewFileErrorDtls('${vo[0]}');">${vo[2]}</a>
			</div> 
		</display:column>
				<display:column headerClass="datatableheader" class="oddcentre" sortable="false" style="width:5%" title="View Files">
			<div align="center"><input name="btnViewFiles" id="btnViewFiles${vo_rowNum}" title="View Files" value="View Files" type="button" class="buttontag" onclick="viewAttachedFiles('${vo[0]}');" /></div> 
		</display:column>
		<display:setProperty name="paging.banner.placement" value="bottom"/>
	</display:table>
</fieldset>
<table align="center">
<tr>
	<td>
		<hdiits:button id="btnClose" name="btnClose"  type="button" captionid="PPMT.CLOSE" bundle="${pnsnLables}" onclick="winCls();" />
	</td>
</tr>
</table>
</hdiits:form>

<script type="text/javascript">

	function callReadExcel()
	{
		//Get attched file path start
		var attachedFileNames = new Array;
				
		var table = document.getElementById("myTablescan");
		var flag = 0 ;
		var fileCnt = 0;
		for ( var i = 0; row = table.rows[i]; i++ ) 
		{      
			row = table.rows[i];      
		    for ( var j = 1; col = row.cells[j]; j++,j++ ) {    
		    	if(flag == 1)
		    	{
		    		attachedFileNames[fileCnt] = col.innerHTML;
		    		fileCnt++;
		    	}
		    	flag = 1;
		    	   
			} 
		} 

		//Get attched file path end
		if(arrFileNames.length == 0)
		{
			document.getElementById("btnUpload").disabled = true;
			showProgressbar('Please Wait<br>Your request is in progress...');
			document.uploadForm.action='ifms.htm?actionFlag=rdxl&attachedFileNames='+attachedFileNames;
			//document.uploadForm.action='ifms.htm?actionFlag=rdxl';
			document.uploadForm.submit();
			document.getElementById("btnUpload").disabled = false;
			
		}
		else
		{
			alert("please upload "+arrFileNames+" files ");
		}
	}

	function reloadPage()
	{
		winCls();
		showProgressbar('Please Wait<br>Your request is in progress...');
		document.uploadForm.action='ifms.htm?actionFlag=loadAttchAgFile';
		document.uploadForm.submit();

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
		   xmlHttp.open("POST",url,true);
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
		     alert("data inserted successfully");
		 }
		 
	}
	function viewFileErrorDtls(delvId)
	{
		var newWindow;
		var height = screen.height - 100;
		var width = screen.width;
		var url='ifms.htm?actionFlag=viewStgFileErrorDtls&DelvId='+delvId;
		var urlstyle = "height=500,width=600,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		newWindow = window.open(url, "FileErrorDtls", urlstyle);
	}
	function viewAttachedFiles(delvId)
	{
		var newWindow;
		var height = screen.height - 100;
		var width = screen.width;
		var url='ifms.htm?actionFlag=viewAttachedFiles&DelvId='+delvId;
		var urlstyle = "height=500,width=600,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		newWindow = window.open(url, "ViewAttachedFiles", urlstyle);
	}
</script>



