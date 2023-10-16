<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="ddoCode" value="${resValue.DDOCODE}"></c:set>
<c:set var="draftFlag" value="${resValue.DraftFlag}"></c:set>
<c:set var="parentDeptByDefault" value="${resValue.listParentDept[0]}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<c:set var="empList" value="${resValue.empList}"></c:set>
<script type="text/javascript">
function hidImg()
{
	newWindow1.close();
}
function showImg(lStr)
{
	document.getElementById("attachmentName1").value = lStr.name;
	var height = 600;
	var width = 600;
	var urlstring = "ifms.htm?viewName=dcpsPhoto";
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow1 = window.open(urlstring,"PensionerPhoto","'titlebar=no,directories=no,height=355,location=no,resizable=no,scrollbars=no,status=no,titlebar=no,toolbar=no,width=600,height=600,top=0,left=0","false");
	//globArray[9] = newWindow1; 
}
</script>
<input type="hidden" name="hidPhotoUrl" id="hidPhotoUrl" >
<input type="hidden" name="hidSignUrl" id="hidSignUrl" >
<input type="hidden" name="attachmentName1" id="attachmentName1" >

<fieldset  style="width:100%" class="tabstyle">
	<legend id="headingMsg"><b>Photo/Signature</b></legend>
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

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />









