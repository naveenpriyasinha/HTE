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
	var urlstring = "ifms.htm?viewName=PensionerPhoto";
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow1 = window.open(urlstring,"PensionerPhoto","'titlebar=no,directories=no,height=355,location=no,resizable=no,scrollbars=no,status=no,titlebar=no,toolbar=no,width=600,height=600,top=0,left=0","false");
	//globArray[9] = newWindow1; 
}
</script>
<input type="text" name="hidPhotoUrl" id="hidPhotoUrl" >
<input type="text" name="hidSignUrl" id="hidSignUrl" >
<input type="text" name="attachmentName1" id="attachmentName1" >



<fieldset  style="width:100%" class="tabstyle">
	<legend id="headingMsg"><b>Test</b></legend>
	<table width="100%"> 	
		<tr>
		
			<td>
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
			</td>
			<td>
				<fieldset class="tabstyle" style="width: 80%;" >
					<legend id="headingMsg"><b> Photo</b></legend>
				
					<div id="prewPhoto" style="width: 180px;height: 150px;">
							
					</div>	
				</fieldset>
			</td>	
			
			</tr>
			<tr>		
     	  	<td>
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
				
			</td>
			<td>
				<fieldset class="tabstyle" style="width: 80%;" >
					<legend id="headingMsg"><b> Signature</b></legend>
					
							<div id="prewSign" style="width: 180px;height: 150px;">
							</div>	
				
     			</fieldset>	
				
			</td>
		</tr>
	</table>
</fieldset>









