<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	


<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/hrms/ess/gpf/gpfWith.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="reqid" value="${resValue.reqid}"></c:set>
<c:set var="approveIn" value="${resValue.approveIn}"></c:set>
<c:set var="approveAg" value="${resValue.approveAg}"></c:set>
<c:set var="withInbox" value="${resValue.withInbox}"></c:set>
<c:set var="purposeDtls" value="${resValue.purposeDtls}"></c:set>
<c:set var="date" value="${resValue.reqDate}"></c:set>
<c:set var="hrGpfBalanceDtls" value="${resValue.hrGpfBalanceDltsInbox}"></c:set> 
<c:set var="RequestId" value="${resValue.RequestId}"></c:set> 
<c:set var="withHistory" value="${resValue.withHistory}"></c:set>
<c:set var="finYrStart" value="${resValue.finYrStart}"></c:set>
<c:set var="agResponse" value="${resValue.agResponse}"></c:set>
<c:set var="todayDate" value="${resValue.todayDate}"></c:set>
<c:set var="relationStr" value="${resValue.relationStr}" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script>

<fmt:formatDate pattern="dd/MM/yyyy" value="${todayDate}" var="today"/>
var todayDate="${today}";
var appDate="${withInbox.appDt}";
var lookupPurposeWith="${withInbox.cmnLookupMstByPurpose.lookupName}";
var approveAg="${approveAg}";
var approveIn="${approveIn}";
var amtWithInbox="${withInbox.amtWithdrawn}"*1;
var isWithHstEmpty="${empty withHistory}";



</script>


<hdiits:form name="frmgpfWithInbox" validate="true" method="POST" 
	 encType="multipart/form-data" action="hrms.htm?actionFlag=gpfWithInbox&flag=1">

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
<%@ include file="../gpf/gpfBalanceDtls.jsp"%>
<br>
<hdiits:fieldGroup titleCaptionId="GPF.withDet" bundle="${gpfLables}" >


<table width="100%">

<tr>
	<td>
		<hdiits:hidden name="RequestId" caption="ReqId" default="${RequestId}"/>
		<hdiits:hidden name="fileId" default="${RequestId}"/>
		<hdiits:hidden name="wffileId_hidden" default="${RequestId}"/>
	</td>
<tr>

<tr>
 	<td width="25%" style="display: none"> 
 		<b>
 		<hdiits:caption captionid="GPF.accNo" bundle="${gpfLables}"/>
 		</b>
 	</td> 
 	
 	<c:set var="gpfaccno" value="${withInbox.gpfAccNo}" />
	
	<td width="25%" style="display: none"> 
 		${withInbox.gpfAccNo}
 	</td>
 	
 	<td width="25%"> 
 		<b>
 		<hdiits:caption captionid="GPF.appDate" bundle="${gpfLables}"/>
 		</b>	
 	</td>

  	<td width="25%"> 
  		<fmt:formatDate var="appDate"  pattern="dd/MM/yyyy" value="${withInbox.appDt}" type="date"/>
	  	${appDate}
  	</td>
</tr>

 <tr>
 	<td width="25%"><b>
 	<hdiits:caption captionid="GPF.AmtWithn" bundle="${gpfLables}"/>
 	(
 	<hdiits:caption captionid="GPF.INR" bundle="${gpfLables}"/>
 	)
 	</b></td>
 	<td width="25%"><script>document.write(decimalPoint(${withInbox.amtWithdrawn}))</script>	</td>
 	
 	<td width="25%"> <b>
 	<hdiits:caption captionid="GPF.WithType" bundle="${gpfLables}"/>
 	</b>	</td>
  	<td width="25%"> 
  	
	<c:if test="${withInbox.withdrawalType=='Part'}">
		<hdiits:caption captionid="GPF.part" bundle="${gpfLables}"/>
	</c:if>
	<c:if test="${withInbox.withdrawalType=='Final'}">
		<hdiits:caption captionid="GPF.final" bundle="${gpfLables}"/>
	</c:if>
  	</td> 
  	
  </tr>

 <tr>
	<td width="25%"> <b>
	<hdiits:caption captionid="GPF.purpose" bundle="${gpfLables}"/>
	
	</b>	</td>
	<td width="25%"> 
	 	<c:if test="${withInbox.cmnLookupMstByPurpose.lookupDesc!= ''}">
	  		${withInbox.cmnLookupMstByPurpose.lookupDesc}
	  	</c:if>
  	</td>
  		
  	<td width="25%">
  		
  		<p id="flag11" style="display:none"> <b>
  		<hdiits:caption captionid="GPF.price" bundle="${gpfLables}"/>
  		</b></p> 
  		<p id="flag21" style="display:none"> <b>
  		<hdiits:caption captionid="GPF.dpndtDet" bundle="${gpfLables}"/>
  		</b></p> 	
  		<p id="flag41" style="display:none"> <b> 
  		<hdiits:caption captionid="GPF.purpose" bundle="${gpfLables}"/>
  		</b></p> 
  		<p id="flag51" style="display:none"> <b> 
  		<hdiits:caption captionid="GPF.oth" bundle="${gpfLables}"/>
  		</b></p> 
  		</td> 
  		
		<td width="25%">
		
		<p id="flag12" style="display:none">
  			<c:if test="${purposeDtls.purposeAttr7 ne null}"> 
  		 		<script>document.write(decimalPoint(${purposeDtls.purposeAttr7}))</script>
	 		</c:if>
	 	</p>
		  
  		<p id="flag22" style="display:none">
	  		<c:if test="${purposeDtls.purposeAttr10!=0}">
	 		 	<c:set var="dpName" value="${relationStr},${purposeDtls.purposeAttr2}, ${purposeDtls.purposeAttr10}" />
	  			<c:out value="${dpName}"> </c:out>
	 	 	</c:if>
	  	
	  		<c:if test="${purposeDtls.purposeAttr10==0}">
				<hdiits:caption captionid="GPF.Self" bundle="${gpfLables}"/>
	  		</c:if>	
  		</p>
  		<p id="flag42" style="display:none"> ${withInbox.cmnLookupMstByPurposeId.lookupDesc}</p>
  		<p id="flag52" style="display:none"> ${withInbox.other}</p>
  		</td>
	</tr>
  
   </table>
  
   
 <hdiits:hidden name="gpfaccno" default="${gpfaccno}"/>
 <hdiits:hidden name="reqid" default="${reqid}"/>
  
  
  
  <table id="flag3" style="display:none" width="100%">
  <tr>
  	<td width="25%"> <b> 
  	<hdiits:caption captionid="GPF.dpndtDet" bundle="${gpfLables}"/>
  	</b> </td>
  	<td width="25%"> 
  	<c:if test="${purposeDtls.purposeAttr10!=0}">
  		<c:set var="dpNameEdu" value="${relationStr},${purposeDtls.purposeAttr2}, ${purposeDtls.purposeAttr10}"/>
  	<c:out value="${dpNameEdu}"> </c:out>
  	</c:if>
  	
  	<c:if test="${purposeDtls.purposeAttr10==0}">
  		<hdiits:caption captionid="GPF.Self" bundle="${gpfLables}"/>
  	</c:if>
  	</td>
 
 	<td width="25%"> <b> 
 	<hdiits:caption captionid="GPF.stuType" bundle="${gpfLables}"/>
 	</b></td>
	<td width="25%">
 		<c:set var="dpTypeEdu" value="${purposeDtls.purposeAttr4}"/>
  		
  		<c:if test="${dpTypeEdu=='Hostelite'}">
  			<hdiits:caption captionid="GPF.hostelite" bundle="${gpfLables}"/>
  		</c:if>
  		
  		<c:if test="${dpTypeEdu=='Day Scholar'}">
  			<hdiits:caption captionid="GPF.dayScholar" bundle="${gpfLables}"/>
  		</c:if>
	</td>
  </tr>
 
  <tr>
  		<td width="25%">  <b>
  		<hdiits:caption captionid="GPF.instiName" bundle="${gpfLables}"/>
  		</b></td>
  		<td width="25%">
  		<c:set var="dpInstiEdu" value="${purposeDtls.purposeAttr6}"/>
  		${dpInstiEdu}
  		</td>

  		<td width="25%"> <b>
  		<hdiits:caption captionid="GPF.class" bundle="${gpfLables}"/>
  		</b> </td>
  		<td width="25%">
  		<c:set var="dpClassEdu" value="${purposeDtls.cmnLookupMstByPurposeAttr8.lookupDesc}"/>
  		${dpClassEdu}
  		</td>
  </tr>
  </table>
  
  <table id="flag6" style="display:none" width="100%">
  <tr> 
  	<td width="25%"> <b> 
  	<hdiits:caption captionid="GPF.TwoWheel" bundle="${gpfLables}"/>
  	 </b>	</td>
  	<td width="25%"> ${withInbox.cmnLookupMstByPurposeId.lookupDesc} </td>
   
  	<td width="25%"> <b> 
  	<hdiits:caption captionid="GPF.priceVehicle" bundle="${gpfLables}"/>
  	 </b> 	</td>
  	<td width="25%"> 	
  	<c:if test="${purposeDtls.purposeAttr7 ne null}">
  	 <script>document.write(decimalPoint(${purposeDtls.purposeAttr7}));</script>
  	</c:if>	
  	</td>
  </tr>
  </table>
  
 </hdiits:fieldGroup>
 
  <table width=100%>
  	<tr width=100%>
  	<td width=100%>
  	<c:if test="${withInbox.cmnAttachmentMst!=null}">
	<!-- For attachment : Start--> 
	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="WithAttatchment"/>
			<jsp:param name="formName" value="frmgpfWithInbox"/>
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N" />
			<jsp:param name="rowNumber" value="1" />
			</jsp:include> 
	<!-- For attachment : End-->
	<script>
			document.getElementById('target_uploadWithAttatchment').style.display='none';
			document.getElementById('formTable1WithAttatchment').firstChild.firstChild.style.display='none';
	</script> 
	</c:if>
</td></tr></table>
 		   
	
	 
	 <hdiits:fieldGroup titleCaptionId="GPF.apprDet" bundle="${gpfLables}" id="apprDetFldGrp">
	 
<table width="100%" id="apprDet" style="display:none">
<tr>
 	<td>
 	
 	<p id="requestCancel" style="display:none"> 
				<font color="RED">
				CANCELLED REQUEST
				</font>
			</p>
			
 	<p id="enterAllFields" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.enterAllFields" bundle="${gpfLables}"/>
			</font>
	</p>
	<p id="enterAppAG" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.enterAppAG" bundle="${gpfLables}"/>
			</font>
	</p>
	<p id="orderDateErr" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.orderDateErr" bundle="${gpfLables}"/>
			</font>
	</p>
	<p id="orderDateApp" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.orderDateApp" bundle="${gpfLables}"/>
			</font>
	</p>
	<p id="appDateErr" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.appDateErr" bundle="${gpfLables}"/>
			</font>
	</p>
	<p id="appDateErr2" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.appDateErr2" bundle="${gpfLables}"/>
			</font>
	</p>
	<p id="amtSancError" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.amtSancError" bundle="${gpfLables}"/>
			</font>
	</p>
	<p id="amtSancInvalid" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.amtSancInvalid" bundle="${gpfLables}"/>
			</font>
	</p>
	<p id="noSplChar" style="display:none"> 
			<font color="RED">
			<hdiits:caption captionid="GPF.noSplChar" bundle="${gpfLables}"/>
			</font>
	</p>						
 	<p id="agResponseSavedY" style="display:none">
 		<b>
 		<hdiits:caption captionid="GPF.agApprove" bundle="${gpfLables}"/>
	</b>
 	</p>
 		
 	<p id="agResponseSavedN" style="display:none">
 		<b>
 		<hdiits:caption captionid="GPF.agReject" bundle="${gpfLables}"/>
 		</b>	
 	</p>

 	<p id="CashSectionReject" style="display:none">
		<b>
		<hdiits:caption captionid="GPF.csReject" bundle="${gpfLables}"/>
			</b>
	</p>
	
	<p id="CashSectionApprove" style="display:none">
  		<b> 
  		<hdiits:caption captionid="GPF.csApprove" bundle="${gpfLables}"/>
  		</b> 
  	</p>

 	</td>
 </tr>
</table>
			
<table id="orderDtls" style="display:none" width="50%">
	
	<tr>
		<td width="25%" > <b>
		<hdiits:caption captionid="GPF.agResponse" bundle="${gpfLables}"/>
		</b></td>
		<td width="25%" > 
		
		<c:if test="${approveAg=='Y'}">
			<hdiits:caption captionid="GPF.agApprove" bundle="${gpfLables}"/>
		</c:if>
		<c:if test="${approveAg=='N'}">
			<hdiits:caption captionid="GPF.agReject" bundle="${gpfLables}"/>
		</c:if>
		</td>
	</tr>
	
	<tr>
		<td width="25%"> <b>
		<hdiits:caption captionid="GPF.orderNo" bundle="${gpfLables}"/>
		 </b></td>
		<td width="25%"> ${withInbox.orderNo}</td>
	</tr>
	
	<tr>
		<td width="25%"> <b>
		<hdiits:caption captionid="GPF.orderDate" bundle="${gpfLables}"/>
		</b></td>
		<td width="25%"><fmt:formatDate pattern="dd/MM/yyyy" value="${withInbox.orderDt}" var="orderDate"/>  ${orderDate} </td>
	</tr>
	
	<tr>
		<td width="25%"> <b>
		<hdiits:caption captionid="GPF.amtSanc" bundle="${gpfLables}"/>
		</b></td>
		<td width="25%">
		<c:if test="${approveAg=='Y'}">
			${withInbox.amountSanctioned}
		</c:if>
		
		<c:if test="${approveAg=='N'}">
			<script>document.write('-');</script>
		</c:if>
		</td>
	</tr>
	
	<tr>
		<td width="25%"> <b>
		<hdiits:caption captionid="GPF.WithType" bundle="${gpfLables}"/>
		 </b></td>
		<td width="25%">${withInbox.withdrawalType}</td>
	</tr>
	
</table>
			


<table id="approveAgTable" style="display:none" width="50%">

  <tr>
	<td width="25%"> <b>
    	<hdiits:caption captionid="GPF.agResponse" bundle="${gpfLables}"/>
    	</b>
    </td>
      		
      <td width="25%">
	      <hdiits:select name="agResponse" id="agResponse" onchange="showAmtSanctioned();" mandatory="true">
			<option id="a" value="Select"> 
			<fmt:message key="GPF.select"/>
			</option>
				<c:forEach var="name" items="${agResponse}">						
    			<option id="opt" name="opt" value="<c:out value="${name.lookupShortName}"/>"  >
    				<c:out value="${name.lookupDesc}"/>
    			</option>						
				</c:forEach>
	  </hdiits:select>

	</td>		
</tr>


<tr>
	<td width="25%"><b>
	<hdiits:caption captionid="GPF.orderDate" bundle="${gpfLables}"/>
	 </b> </td>
	<td width="25%"> <hdiits:dateTime name="orderDt" captionid="GPF.orderDate" bundle="${gpfLables}" mandatory="true"  onblur="validateDate();" maxvalue="31/12/2099 00:00:00"/></td>	
</tr>

<tr>
	<td width="25%"> <b>
	<hdiits:caption captionid="GPF.orderNo" bundle="${gpfLables}"/>
	</b> </td>
	<td width="25%"> <hdiits:number name="orderNo" mandatory="true" id="orderno" maxlength="10"/> </td>
</tr>

<tr id="amtSanctioned">
	<td width="25%"> <b>
	<hdiits:caption captionid="GPF.amtSanc" bundle="${gpfLables}"/>
	(
	<hdiits:caption captionid="GPF.INR" bundle="${gpfLables}"/>) 
	</b> </td>
	<td width="25%"> <hdiits:number name="amtSanc" mandatory="true" id="amtSanc" onblur="roundAmtSanc();" maxlength="10"/> </td>
</tr>

<tr>
	<td width="25%"> <b>
	<hdiits:caption captionid="GPF.approvalDate" bundle="${gpfLables}"/>
	</b> </td>
	<td width="25%"> <hdiits:dateTime name="appDt" captionid="GPF.approvalDate" bundle="${gpfLables}" mandatory="true" onblur="validApprovalDt();" maxvalue="31/12/2099 00:00:00"/>	</td>	
</tr>


</table>
</hdiits:fieldGroup>

 <%@ include file="../gpf/gpfPrevWith.jsp"%>
 
 
<table width="100%">
<tr align="right">
	<td>
		<font size="1">
		<hdiits:caption captionid="GPF.commonNote" bundle="${gpfLables}"/>
		<br>
		<hdiits:caption captionid="GPF.amtFormat" bundle="${gpfLables}"/>
		<br>
		<hdiits:caption captionid="GPF.dateFormat" bundle="${gpfLables}"/>
		<br>
		3. 
		<hdiits:caption captionid="GPF.mandatory" bundle="${gpfLables}"/>
		</font>
	</td>
</tr>
</table> 
 
 
</div>
</div>
 
 	
<hdiits:jsField jsFunction="setActionSave()" name="setActionSave"/>

	
<script type="text/javascript">
	initializetabcontent("maintab")
</script>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

<script>

makeReadOnly();
dispPurpose();
window.onload = approve; 
document.getElementById("apprDetFldGrp").style.display='none';
if(approveIn=='C')
{
	document.getElementById("requestCancel").style.display='';
}

</script>


<%
} catch (Exception e) 
	{
	e.printStackTrace();
	}
%>

