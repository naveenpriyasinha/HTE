<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%> 
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<script type="text/javascript">
function hidImg()
{
	newWindow1.close()
}
function showImg(lStr)
{
	document.getElementById("attachmentName1").value = lStr.name;
	var height = 600;
	var width = 600;
	var urlstring = "ifms.htm?viewName=dcpsPhoto&PhotoType=DCPS Applicant "+ lStr.name;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow1 = window.open(urlstring,"PensionerPhoto","'titlebar=no,directories=no,height=355,location=no,resizable=no,scrollbars=no,status=no,titlebar=no,toolbar=no,width=600,height=600,top=0,left=0","false");
	//globArray[9] = newWindow1; 
}
</script>
<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varLabelDisabled" scope="page" value="style='display: none;' "></c:set>
</c:if>
<input type="hidden" name="hidPhotoUrl" id="hidPhotoUrl" >
<input type="hidden" name="hidSignUrl" id="hidSignUrl" >
<input type="hidden" name="attachmentName1" id="attachmentName1" >

<fieldset  style="width:100%" class="tabstyle">
	<legend id="headingMsg"><b>Photo/Signature</b></legend>
	<table width="100%"> 	
		<tr>
		
			<td ${varDisabled }>
				<div id="divPhoto">
						<jsp:include page="/WEB-INF/jsp/dcps/attachment.jsp">
							<jsp:param name="attachmentName" value="Photo"/>
						    <jsp:param name="formName" value="DCPSForm"/>
						   	<jsp:param name="attachmentType" value="Document"/>
						   	<jsp:param name="attachmentTitle" value="Photo"/>
						   	<jsp:param name="multiple" value="N" />
						   	<jsp:param name="removeAttachmentFromDB" value="Y" />
						</jsp:include>
						</div>
						<script type="text/javascript">
						  document.getElementById('descPhoto').value="Photo";
						  document.getElementById('descPhoto').readOnly =true;
						</script>
			</td>
			<td>
				<fieldset class="tabstyle" style="width: 80%;" >
					<legend id="headingMsg"><b>Photo </b></legend>
				
				<c:choose>
						<c:when test="${resValue.PhotoId != null}" >
							<div id="prewPhoto" style="width: 180px;height: 150px;">
								<a href="#" id="Photo" name="Photo" onmouseout="hidImg()"  onmouseover="showImg(this)"  >	
									<img style="width: 180px;height: 150px;" id="Photo"  src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId}&attachmentSerialNumber=${resValue.PhotosrNo}">
								</a>
								<script type="text/javascript">
									document.getElementById("hidPhotoUrl").value = "ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.PhotoId}&attachmentSerialNumber=${resValue.PhotosrNo}"
								</script>
							</div>
						</c:when>
						<c:otherwise>
							<div id="prewPhoto" style="width: 180px;height: 150px;">
							
							</div>	
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;&nbsp;
					<c:set value="Upload?attachmentNameHidden=Photo&attachmentMpgSrNo=${resValue.srNo}" var="url"></c:set>			
				</fieldset>
			</td>	
			
			</tr>
			<tr>		
     	  	<td ${varDisabled }>
				<div id="divSign">
					<jsp:include page="/WEB-INF/jsp/dcps/attachment.jsp">
						<jsp:param name="attachmentName" value="Sign"/>
					    <jsp:param name="formName" value="DCPSForm"/>
					   	<jsp:param name="attachmentType" value="Document"/>
					   	<jsp:param name="attachmentTitle" value="Signature"/>
					   	<jsp:param name="multiple" value="N" />
					   	<jsp:param name="removeAttachmentFromDB" value="Y" />
					</jsp:include>
				</div>
				<script type="text/javascript">
						  document.getElementById('descSign').value="Signature";
						  document.getElementById('descSign').readOnly =true;
						</script>
			</td>
			<td>
				<fieldset class="tabstyle" style="width: 80%;" >
					<legend id="headingMsg"><b> Signature</b></legend>
					
						<c:choose>
						<c:when test="${resValue.SignId != null}" >
							<div id="prewSign" style="width: 180px;height: 150px;">
								<a href="#" name="Sign" id="Sign" onmouseout="hidImg()"  onmouseover="showImg(this)"  >	
									<img  style="width: 180px;height: 150px;" src="ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId}&attachmentSerialNumber=${resValue.SignsrNo}">
								</a>
								<script type="text/javascript">
									document.getElementById("hidSignUrl").value = "ifms.htm?actionFlag=viewAttachment&attachmentId=${resValue.SignId}&attachmentSerialNumber=${resValue.SignsrNo}"
								</script>
							</div>
						</c:when>
						<c:otherwise>
							<div id="prewSign" style="width: 180px;height: 150px;">
							</div>	
						</c:otherwise>
					</c:choose>
						
					<c:set value="Upload?attachmentNameHidden=Sign&attachmentMpgSrNo=${resValue.srNo}" var="url"></c:set>	
				
     			</fieldset>	
				
			</td>
		</tr>
	</table>
</fieldset>









