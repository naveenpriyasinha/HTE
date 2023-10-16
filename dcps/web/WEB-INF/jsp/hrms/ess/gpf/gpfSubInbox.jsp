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
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/ess/gpf/gpfSub.js"></script>

<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="subInbox" value="${resValue.subInbox}"></c:set>
<c:set var="reqid" value="${resValue.reqid}"></c:set>
<c:set var="approveIn" value="${resValue.approveIn}"></c:set>
<c:set var="approveAg" value="${resValue.approveAg}"></c:set>
<c:set var="hrGpfBalanceDtls" value="${resValue.hrGpfBalanceDltsInbox}"></c:set> 
<c:set var="date" value="${resValue.reqDate}"></c:set>
<c:set var="RequestId" value="${resValue.RequestId}"></c:set>
<c:set var="finYrStart" value="${resValue.finYrStart}"></c:set>
<c:set var="agResponse" value="${resValue.agResponse}"></c:set>
<c:set var="date" value="${resValue.todayDate}"></c:set>
<c:set var="ordDt" value="${resValue.ordDt}"></c:set>
<c:set var="ordNo" value="${resValue.ordNo}"></c:set>
<c:set var="empPay" value="${resValue.empPay}" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script>

<fmt:formatDate pattern="dd/MM/yyyy" value="${date}" var="today"/>
var todayDate="${today}";
var approveIn="${approveIn}";	
var approveAg="${approveAg}";	
var empPay="${empPay}";	
var appDate="${subInbox.appDt}";

</script>


<hdiits:form name="frmgpfInbox" validate="true" method="POST" encType="multipart/form-data" action="hrms.htm?actionFlag=gpfSubInbox&flag=1">

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

<hdiits:fieldGroup titleCaptionId="GPF.subReq" bundle="${gpfLables}" >

<table width="100%">
	<tr>
		<td>
			<hdiits:hidden name="fileId" default="${RequestId}"/>
			<hdiits:hidden name="wffileId_hidden" default="${RequestId}"/>
		</td>
	</tr>

	<tr>
		<td>
			<hdiits:hidden name="RequestId" caption="ReqId" default="${RequestId}"/>
			<hdiits:hidden name="apprvIn" caption="apprvIn" default="${approveIn}"/>
		</td>
	</tr>

	<tr>
		 <td>
			 <hdiits:hidden name="gpfaccno" default="${gpfaccno}"/>
		</td>
		
		<td> 	
			<hdiits:hidden name="reqid" default="${reqid}"/> 
		</td>
	 </tr>
 
	<tr>
		<td width="25%" style="display: none;">
			<b>
				<hdiits:caption captionid="GPF.accNo" bundle="${gpfLables}"/>
			</b>
		</td> 
 		
 		<c:set var="gpfaccno" value="${subInbox.gpfAccNo}" />

	 	<td width="25%" style="display: none;"> 
	 		${subInbox.gpfAccNo}
	 	</td>
 	
		<td width="25%"> 
			<b>
				<hdiits:caption captionid="GPF.appDate" bundle="${gpfLables}"/>
			</b>
		</td> 
	
	 	<td width="25%">
	 		<fmt:formatDate pattern="dd/MM/yyyy" value="${subInbox.appDt}" var="appDate"/>  
	 		${appDate}  
	 	</td>   
	</tr>
 
 	 <tr>
		<td width="25%">
			<b>
				<hdiits:caption captionid="GPF.currSubRate" bundle="${gpfLables}"/>
			</b>
		</td>
		
		<td width="25%">
			<script>document.write(decimalPoint(${subInbox.oldSubAmt}))</script>
		</td>
		
		<td width=25% style="display:none">
			<b>
			<hdiits:caption captionid="GPF.currRate" bundle="${gpfLables}"/></b>
	 	</td>

		<td width="25%"> 
			<b>
			<hdiits:caption captionid="GPF.newSubRate" bundle="${gpfLables}"/></b>
		</td>  
		
		<td width="25%">
			<script>document.write(decimalPoint(${subInbox.newSubscrptionAmt}))</script>
		</td>
 	
	 	<td width=25% style="display:none">
 			<script>
	 		document.write(decimalPoint(calcCurrRateInbox(${subInbox.oldSubAmt})));
 			</script>
	 	</td>
    </tr>
 		
	<tr>
 		<td width="25%">
 			<b>
 			<hdiits:caption captionid="GPF.effFrom" bundle="${gpfLables}"/></b>
 		</td> 

	  	<td width="25%">  
	  		<fmt:formatDate pattern="dd/MM/yyyy" value="${subInbox.startDate}" type="date" var="effectDate"/>
	  		${effectDate}
 		</td>
	 </tr>
 
	<tr>
 		<td width="25%" style="display:none"> 
 			<b>
 			<hdiits:caption captionid="GPF.newSubRt" bundle="${gpfLables}"/></b>
	 	</td>
 	
 	 	<td width="25%" style="display:none"> 
 	 	</td>
 
 	</tr>
</table>
 
 </hdiits:fieldGroup>
 
 <hdiits:fieldGroup titleCaptionId="GPF.apprDet" bundle="${gpfLables}"  id="apprDtFldGrp">
 

<table width="100%" id="apprDet" style="display:none">
	<tr>
 		<td>
		 	<p id="enterAllFields" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.enterAllFields" bundle="${gpfLables}"/></font>
			</p>
			<p id="enterAppAG" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.enterAppAG" bundle="${gpfLables}"/></font>
			</p>
			<p id="orderDateErr" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.orderDateErr" bundle="${gpfLables}"/></font>
			</p>
			<p id="orderDateApp" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.orderDateApp" bundle="${gpfLables}"/></font>
			</p>
			<p id="noSplChar" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.noSplChar" bundle="${gpfLables}"/></font>
			</p>			
 			<p id="agResponseSavedY" style="display:none">
 				<b>
 				<hdiits:caption captionid="GPF.agApprove" bundle="${gpfLables}"/></b>
 			</p> 
 		
 			<p id="agResponseSavedN" style="display:none">
 				<b>
 				<hdiits:caption captionid="GPF.agReject" bundle="${gpfLables}"/></b>	
 			</p>

 			<p id="CashSectionReject" style="display:none">
 				<b>
 				<hdiits:caption captionid="GPF.csReject" bundle="${gpfLables}"/></b>
 			</p> 
			<p id="requestCancel" style="display:none"> 
				<font color="RED">
				CANCELLED REQUEST
				</font>
			</p>	
 			<b> <p id="CashSectionApprove" style="display:none">
 			<hdiits:caption captionid="GPF.csApprove" bundle="${gpfLables}"/>
 				</p> </b>
	 	</td>
	</tr>
</table>

<table id="orderDtls" style="display:none" width="50%">
	<tr>
		<td width="25%" >
		 	<b>
		 	<hdiits:caption captionid="GPF.agResponse" bundle="${gpfLables}"/></b>
		</td>

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
		<td width="25%">
			<b>
			<hdiits:caption captionid="GPF.orderNo" bundle="${gpfLables}"/>
			</b>
		</td>
		<td width="25%"> 
			${subInbox.orderNo}
		</td>
	</tr>
	
	<tr>
		<td width="25%"> 
			<b>
			<hdiits:caption captionid="GPF.orderDate" bundle="${gpfLables}"/>
			</b>
		</td>
		<td width="25%">
			<fmt:formatDate pattern="dd/MM/yyyy" value="${subInbox.orderDt}" var="orderDate"/>  ${orderDate} 
		</td>
	</tr>
	
</table>
			
<table id="approveAgTable" width="50%" style="display:none" >
	<tr>
		<td width="25%"> 
			<b>
			<hdiits:caption captionid="GPF.agResponse" bundle="${gpfLables}"/>
			</b>
		</td>
      	
      	<td width="25%">
		    <hdiits:select name="agResponse" id="agResponse" mandatory="true">
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
		<td width="25%">
			<b>
			<hdiits:caption captionid="GPF.orderDate" bundle="${gpfLables}"/>
			</b> 
		</td> 

	 	 <td width="25%">
		 	 <hdiits:dateTime name="orderDt" captionid="GPF.orderDate" bundle="${gpfLables}" mandatory="true" onblur="validateDate();" maxvalue="31/12/2099 00:00:00"/>
	 	 </td>
	  </tr>
 
	<tr>
		<td width="25%">
			<b>
			<hdiits:caption captionid="GPF.orderNo" bundle="${gpfLables}"/>
			</b> 
		</td>

		<td width="25%">
			<hdiits:number name="orderno" mandatory="true" id="orderno" maxlength="10"/>		
		</td>
	</tr>
  
</table>
 
 </hdiits:fieldGroup>
 
<table width="100%">
	<tr align="right">
		<td>
			<font size="1">
			
			<hdiits:caption captionid="GPF.commonNote" bundle="${gpfLables}"/><br>
			<hdiits:caption captionid="GPF.amtFormat" bundle="${gpfLables}"/><br>
			<hdiits:caption captionid="GPF.dateFormat" bundle="${gpfLables}"/><br>
			3.<hdiits:caption captionid="GPF.mandatory" bundle="${gpfLables}"/>
			</font>
		</td>
	</tr>
</table> 
  
	</div>
</div>
 
<script type="text/javascript">
	initializetabcontent("maintab")
</script>
		
	<hdiits:jsField jsFunction="setActionSave()" name="setActionSave"/>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

</hdiits:form>

<script>
window.onload = approve; 
makeReadOnly();
document.getElementById("apprDtFldGrp").style.display='none';

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

