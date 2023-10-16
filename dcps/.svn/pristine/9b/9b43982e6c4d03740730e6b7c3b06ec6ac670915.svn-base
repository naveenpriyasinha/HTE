<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 

<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/xmldom.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/ess/gpf/gpfTransfer.js"></script>
<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="oldAccNo" value="${resValue.oldAccNo}"></c:set>
<c:set var="newAccNo" value="${resValue.newAccNo}"></c:set>
<c:set var="subsAmt" value="${resValue.subsAmt}"></c:set> 
<c:set var="gpfLookupTransfer" value="${resValue.gpfLookupTransfer}"></c:set>
<c:set var="isAdvPending" value="${resValue.isAdvPending}"></c:set>
<c:set var="amtRecovered" value="${resValue.amtRecovered}"></c:set>
<c:set var="month" value="${resValue.month}"></c:set>
<c:set var="isInbox" value="${resValue.isInbox}"></c:set>
<c:set var="gpfTransferDtl" value="${resValue.gpfTransferDtl}"></c:set>

<c:set var="frmLoc" value="${resValue.frmLoc}"></c:set>
<c:set var="toLoc" value="${resValue.toLoc}"></c:set>
<c:set var="toDsgn" value="${resValue.toDsgn}"></c:set>
<c:set var="transferDt" value="${resValue.transferDt}"></c:set>
<c:set var="transferId" value="${resValue.transferId}"></c:set>
<c:set var="promoId" value="${resValue.promoId}"></c:set>
<c:set var="recruitId" value="${resValue.recruitId}"></c:set>

<c:set var="trnCounter" value="${resValue.trnCounter}"></c:set>
<c:set var="gradeEmp" value="${resValue.gradeEmp}"></c:set>

<c:set var="gradeTo" value="${resValue.gradeTo}"></c:set>
<c:set var="gradeFrm" value="${resValue.gradeFrm}"></c:set>


<script>
var isInbox = "${isInbox}";
var isAdvPending = "${isAdvPending}";
var promoId = "${promoId}";
var recruitId = "${recruitId}";
var transferId = "${transferId}";
var newAccNo = "${newAccNo}";
var oldAccNo = "${oldAccNo}";
var subsAmt = "${subsAmt}";
</script>


<hdiits:form name="frmGpfTranfer" validate="true" method="POST" action="hrms.htm?actionFlag=gpfGetTransferDtl" encType="multipart/form-data">
<div id="tabmenu">

	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<fmt:message key="GPF"/>
		</a></li>
	</ul>
	</div>
	
	
<div class="tabcontentstyle">


	<div id="tcontent1" class="tabcontent" tabno="0">

<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

  
<hdiits:fieldGroup titleCaptionId="GPF.accTransfer" bundle="${gpfLables}">

<p id="reqCancel" style="display: none">
	<font color="RED">
		CANCELLED REQUEST
	</font>
</p>

<table width=50%> 
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.purpose" bundle="${gpfLables}"/>
		</td>
	
		<td width=25%>
			<hdiits:select name="gpfLookupTransfer" id="gpfLookupTransfer" onchange="showTable(this)" mandatory="true" sort="false">
				<option id="select" value="-1" >
					<fmt:message key="GPF.select"/>
				</option>

				<c:forEach var="name" items="${gpfLookupTransfer}">	
					<c:choose>
						<c:when test="${name.lookupName == gpfTransferDtl.cmnLookupMst.lookupName}">
							<option id="opt" name="opt" value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
			    			<c:out value="${name.lookupDesc}"/>
			    			</option>
		    			</c:when>
	   			
	   				 	<c:otherwise>
			   				<option id="opt" name="opt" value="<c:out value="${name.lookupName}"/>"> 
	    					<c:out value="${name.lookupDesc}"/></option>	
				      		</c:otherwise>						
					</c:choose>
				</c:forEach>
			 </hdiits:select>
		</td>
	</tr>
</table>

<table id="tblPromotion" style="display:none" width=100%>	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.promotedFrmClass" bundle="${gpfLables}"/>
		</td>
		
		<td width=25%>
			${gradeFrm}
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.promotedToClass" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			${gradeTo}
		</td>

	</tr>
	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.promotedDsgn" bundle="${gpfLables}"/>
		</td>
		
		<td width=25%>
			${toDsgn}
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.promoDt" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			Auto
		</td>

	</tr>
</table>

<table id="tblTransfer" style="display:none" width=100%>	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.transferFrmDept" bundle="${gpfLables}"/>
		</td>
		
		<td width=25%>
			${frmLoc}
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.transferToDept" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			${toLoc}
		</td>
	</tr>
	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.transferToDsgn" bundle="${gpfLables}"/>
		</td>
		
		<td width=25%>
			${toDsgn}
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.transferDt" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${transferDt}" var="transferDate"/>
			${transferDate}
		</td>

	</tr>
</table>

<table id="tblRetire" style="display:none" width=100%>	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.classEmp" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			${gradeEmp}
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.doj" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${transferDt}" var="transferDate"/>
			${transferDate}
		</td>
	</tr>
</table>



<table>	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.oldPFNo" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			<hdiits:text name="oldGpfAccNo" default="${oldAccNo}" readonly="readOnly"/>
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.newPFNo" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			${newAccNo}
		</td>

	</tr>
	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.transferTreasury" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			<hdiits:text  name="txtTreasuryTrans" maxlength="50" default="${gpfTransferDtl.treasuryTransfer}"/>
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.lastSub" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			<hdiits:number default="${subsAmt}" name="subsAmt" onblur="checkNo(this);"/> 
		</td>

	</tr>
</table>

</hdiits:fieldGroup>

<hdiits:fieldGroup titleCaptionId="GPF.recoveryAmt" bundle="${gpfLables}" id="tblRecoverLab">

<table id="tblRecover">
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.payBillMnth" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			${month}
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.amtRecoverd" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			${amtRecovered}
		</td>

	</tr>
	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.amtEncashed" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			-
		</td>

		<td width=25%>
			<hdiits:caption captionid="GPF.amtEncashTreasury" bundle="${gpfLables}"/>
		</td>

		<td width=25%>
			<hdiits:text  name="txtTreasuryEncash" maxlength="50" default="${gpfTransferDtl.treasuryEncash}"/>
		</td>
	</tr>
</table>

</hdiits:fieldGroup>

<c:if test="${gpfTransferDtl.cmnAttachmentMst!=null || isInbox=='0'}">
	<table align="center" width="100%">
		<tr align="center">
			<td align="center">
				<!-- For attachment : Start-->	
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="TransferAttatchment" />
					<jsp:param name="formName"  value="frmGpfTranfer" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="multiple" value="N"/>    
					<jsp:param name="rowNumber" value="1" />   
				</jsp:include>
				
				<script>
					if(isInbox=='1')
					{
						document.getElementById('target_uploadTransferAttatchment').style.display='none';
						document.getElementById('formTable1TransferAttatchment').firstChild.firstChild.style.display='none';
					}
				</script>
				<!-- For attachment : End--> 
			</td>
		</tr>
	</table>
</c:if>

<table width="100%" align="center">
	<tr align="center">
		<td align="center">

			<c:if test="${isInbox==0}"> 	
				<hdiits:jsField jsFunction="validateReq()" name="validateReq"/>
				<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp"/>	
			</c:if>
		
		</td>
	</tr>
</table>    

<hdiits:hidden name="transferId" default="${transferId}"/>
<hdiits:hidden name="promoId" default="${promoId}"/>
<hdiits:hidden name="recruitId" default="${recruitId}"/>
<hdiits:hidden name="trnCounter" default="${trnCounter}"/>
 
</div>

</div>

<script type="text/javascript">
initializetabcontent("maintab")

function transferLoad()
{
	if(isAdvPending==1)
	{
		document.getElementById("tblRecoverLab").style.display='';
		document.getElementById("tblRecover").style.display='';
	}
	else if(isAdvPending==0)
	{
		document.getElementById("tblRecoverLab").style.display='none';
		document.getElementById("tblRecover").style.display='none';
	}
	
	if(isInbox==1)
	{
		document.getElementById("gpfLookupTransfer").value="${gpfTransferDtl.cmnLookupMst.lookupName}"
		document.getElementById("gpfLookupTransfer").disabled=true;
		document.getElementById("txtTreasuryTrans").disabled=true;
		document.getElementById("txtTreasuryEncash").disabled=true;
		showTable(document.getElementById("gpfLookupTransfer"));
	}
	
	document.frmGpfTranfer.oldGpfAccNo.readonly=true;
	document.frmGpfTranfer.oldGpfAccNo.disabled=true;  
	document.frmGpfTranfer.subsAmt.readonly=true;
	document.frmGpfTranfer.subsAmt.disabled=true;
	if(isInbox==0)
	{
		document.getElementById("Next").disabled=true;
		document.getElementById("Prev").disabled=true;
	}
	
	if("${gpfTransferDtl.approvalFlag}" == 'C')
	{
		document.getElementById("reqCancel").style.display='block';
	}
}

transferLoad();
</script>


<hdiits:validate controlNames="" locale='<%=(String)session.getAttribute("locale")%>' />
		
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

