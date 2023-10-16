<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	


<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/gpf/gpfAdv.js"></script>

<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="advInbox" value="${resValue.advInbox}"></c:set> 
<c:set var="purposeDtls" value="${resValue.purposeDtls}"></c:set>
<c:set var="reqid" value="${resValue.reqid}"></c:set>
<c:set var="approveIn" value="${resValue.approveIn}"></c:set>
<c:set var="approveAg" value="${resValue.approveAg}"></c:set>
<c:set var="hrGpfBalanceDtls" value="${resValue.hrGpfBalanceDltsInbox}"></c:set>
<c:set var="RequestId" value="${resValue.RequestId}"></c:set>
<c:set var="date" value="${resValue.reqDate}"></c:set>
<c:set var="advHistory" value="${resValue.advHistory}"></c:set>
<c:set var="finYrStart" value="${resValue.finYrStart}"></c:set>
<c:set var="agResponse" value="${resValue.agResponse}"></c:set>
<c:set var="todayDate" value="${resValue.todayDate}"></c:set>
<c:set var="strRelation" value="${resValue.strRelation}" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script>

<fmt:formatDate pattern="dd/MM/yyyy" value="${todayDate}" var="today"/>
var todayDate="${today}";
var appDate="${advInbox.appDt}";
var lookupNamePurpose="${advInbox.cmnLookupMstByPurpose.lookupName}";
var advInboxAmt="${advInbox.advAmt}";
var approveAg="${approveAg}";
var approveIn="${approveIn}";
var isAdvHstEmpty="${empty advHistory}";
window.onload = approve; 


</script>


<hdiits:form name="frmgpfAdvInbox" validate="true" method="POST" 
	 encType="multipart/form-data" action="hrms.htm?actionFlag=gpfAdvInbox&flag=1"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<fmt:message key="GPF" bundle="${gpfLables}"/>
		</a></li>
	</ul>
</div>
	
	 
<div class="tabcontentstyle">
 <div id="tcontent1" class="tabcontent" tabno="0">

<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<%@ include file="../gpf/gpfBalanceDtls.jsp"%>
<br>

<hdiits:fieldGroup titleCaptionId="GPF.advDet" bundle="${gpfLables}" >

<table id="main" style="display:block" width="100%">

<tr>
	<td>
		<hdiits:hidden name="RequestId" caption="ReqId" default="${RequestId}"/>
		<hdiits:hidden name="fileId" default="${RequestId}"/>
		<hdiits:hidden name="wffileId_hidden" default="${RequestId}"/>
	</td>
<tr>

<tr>
	<td>
	<hdiits:hidden name="gpfaccno" default="${gpfaccno}"/>
	</td>
	<td>
	<hdiits:hidden name="reqid" default="${reqid}"/> 
	</td>
</tr>

 <tr>
 	<td width="25%" style="display: none;"><b><hdiits:caption captionid="GPF.accNo" bundle="${gpfLables}"/></b></td> 
 	<c:set var="gpfaccno" value="${advInbox.gpfAccNo}" />

 	<td width="25%" style="display: none;"> 
 	${advInbox.gpfAccNo}
 

			
 </td>
 	<td width="25%"> <b><hdiits:caption captionid="GPF.appDate" bundle="${gpfLables}"/>	</b></td>
  	<td width="25%"> <fmt:formatDate var="appDate"  pattern="dd/MM/yyyy" value="${advInbox.appDt}" type="date"/>
  	${appDate}
  	 </td>
  	
 </tr>


  <tr>
  <td width="25%"><b>
 		<hdiits:caption captionid="GPF.advamt" bundle="${gpfLables}"/> (<hdiits:caption captionid="GPF.INR" bundle="${gpfLables}"/>)</b>
 	</td>
 	<td width="25%">
 	<script>document.write(decimalPoint(${advInbox.advAmt}))</script>
 	</td>
 	
 	<td width="25%"> <b><hdiits:caption captionid="GPF.noOfIns" bundle="${gpfLables}"/></b></td>
  	<td width="25%"> ${advInbox.noOfInstl}
 	</td>
  	
 
  	
  </tr>
 
  <tr>
 	 <td width="25%"> <b>
  		<hdiits:caption captionid="GPF.splAdv" bundle="${gpfLables}"/> </b>
  	</td>  
  	<td width="25%"> 
  	<c:if test="${advInbox.specialAdvance=='N'}">
  		<hdiits:caption captionid="GPF.yes" bundle="${gpfLables}"/>
  	</c:if>
  	<c:if test="${advInbox.specialAdvance=='Y'}">
  		<hdiits:caption captionid="GPF.no" bundle="${gpfLables}"/>
  	</c:if>
  	
  	</td>
  	
  	<td width="25%"> <b><hdiits:caption captionid="GPF.purpose" bundle="${gpfLables}"/>	</b></td>
  	<td width="25%"> ${advInbox.cmnLookupMstByPurpose.lookupDesc}
  	</td>
  
  	
  </tr>
</table>


<table id="flag1" style="display:none" width="100%">
<tr>
  	<td width="25%"> <b><hdiits:caption captionid="GPF.purposeCat" bundle="${gpfLables}"/></b>
  	</td>
  	<td width="25%">
  		${advInbox.cmnLookupMstByPurposeId.lookupDesc}
  	</td>
   
  	<td width="25%"> 
  		<b><hdiits:caption captionid="GPF.dpndtDet" bundle="${gpfLables}"/></b>
  	</td>
  	
  	<td width="25%"> <c:set var="n" value="${purposeDtls.purposeAttr2}, ${strRelation}, ${purposeDtls.purposeAttr10}"/>
  	<c:if test="${purposeDtls.purposeAttr10!=0}">
  	<c:out value='${n}'> </c:out>
  	</c:if>
  	<c:if test="${purposeDtls.purposeAttr10==0}">
  	<hdiits:caption captionid="GPF.Self" bundle="${gpfLables}"/>
  	</c:if>	
  	</td>
  </tr>
  
  <tr>
  	<td width="25%"> 
  		<b><hdiits:caption captionid="GPF.patientType" bundle="${gpfLables}"/></b>
  	</td>
  	<td width="25%">
  		<c:set var="illType" value="${purposeDtls.purposeAttr4}"/>
  ${illType}
  		
  	</td>
  
  	<td width="25%"><b> 
  		<hdiits:caption captionid="GPF.expReimburse" bundle="${gpfLables}"/></b>
  	</td>
  	<td width="25%">
  		<c:set var="expReimb" value="${purposeDtls.purposeAttr5}"/>
  		<c:if test="${expReimb == 'Y'}">
  		<hdiits:caption captionid="GPF.Y" bundle="${gpfLables}"/>	
  		</c:if>
  		<c:if test="${expReimb == 'N'}">
  			<hdiits:caption captionid="GPF.N" bundle="${gpfLables}"/>
  		</c:if>
  	</td>
  </tr>
  
  <tr>
  	<td width="25%"> <b> 
  		<hdiits:caption captionid="GPF.hospitalName" bundle="${gpfLables}"/></b>
  	</td>
  	<td width="25%">
  		<c:set var="doctor" value="${purposeDtls.purposeAttr6}"/>
  ${doctor}
  		
  	</td>
  </tr>
  </table>

<table id="flag2" style="display:none" width="100%">
  <tr>
  	<td width="25%"> <b>
  	<hdiits:caption captionid="GPF.dpndtDet" bundle="${gpfLables}"/>	 </b>
  	</td>
  	<td width="25%"> <c:set var="nEdu" value="${purposeDtls.purposeAttr2}, ${strRelation}, ${purposeDtls.purposeAttr10}"/>
  		
	<c:if test="${purposeDtls.purposeAttr10!=0}">
  	<c:out value='${nEdu}'> </c:out>
  	</c:if>	

	<c:if test="${purposeDtls.purposeAttr10==0}">
  	<hdiits:caption captionid="GPF.Self" bundle="${gpfLables}"/>
  	</c:if>	

  	</td>
 
 	<td width="25%"> <b>
 	<hdiits:caption captionid="GPF.purposeCat" bundle="${gpfLables}"/>	  </b>
  	</td>
  <td width="25%">
  		${advInbox.cmnLookupMstByPurposeId.lookupDesc}
  	</td>
 
  
  </tr>
  
  </table>

<table id="flag3" style="display:none" width="50%">
  <tr>
  	<td width="25%"><b>
  	<hdiits:caption captionid="GPF.purposeCat" bundle="${gpfLables}"/>	</b>
  	</td>
  	<td width="25%">
  		${advInbox.cmnLookupMstByPurposeId.lookupDesc}
  	</td>
 
  </tr>
  </table>
  
<table id="flag4" style="display:none" width="100%">
  <tr>
  	<td width="25%"><b>
  	<hdiits:caption captionid="GPF.dpndtDet" bundle="${gpfLables}"/>	</b>
  	</td>
  	<td width="25%"> 
  	<c:set var="highSecName" value="${purposeDtls.purposeAttr2},${strRelation}, ${purposeDtls.purposeAttr10}"/>
	 <c:if test="${purposeDtls.purposeAttr10!=0}">
		<c:out value='${highSecName}'> </c:out>
  	</c:if>	
  	
  
  	<c:if test="${purposeDtls.purposeAttr10==0}">
  	<hdiits:caption captionid="GPF.Self" bundle="${gpfLables}"/>
  	</c:if>	
  </td>
  		<td width="25%"> <b>
  	<hdiits:caption captionid="GPF.stuType" bundle="${gpfLables}"/>	</b>
  		</td>
  		<td width="25%">
  			<c:set var="highSecType" value="${purposeDtls.purposeAttr4}"/>
  		
  		<c:if test="${highSecType=='Hostelite'}">
  		<hdiits:caption captionid="GPF.hostelite" bundle="${gpfLables}"/>
  		</c:if>
  		
  		<c:if test="${highSecType=='Day Scholar'}">
  		<hdiits:caption captionid="GPF.dayScholar" bundle="${gpfLables}"/>
  		</c:if>
  		
  		</td>
  </tr>
  <tr>
  		<td width="25%">  <b>
  		<hdiits:caption captionid="GPF.instiName" bundle="${gpfLables}"/></b>
  		</td>
  		<td width="25%">
  			<c:set var="highSecInsti" value="${purposeDtls.purposeAttr6}"/>
  		${highSecInsti}
  		 
  		</td>
  
  		<td width="25%"><b>
  		<hdiits:caption captionid="GPF.class" bundle="${gpfLables}"/></b>
  		</td>
  		<td width="25%">
  			<c:set var="highSecClass" value="${purposeDtls.cmnLookupMstByPurposeAttr8.lookupDesc}"/>
  	${highSecClass}
  		
  		</td>
  </tr>
  </table>

<table id="flag5" style="display:none" width="100%">
  <tr>
  	<td width="25%"> <b>
  	<hdiits:caption captionid="GPF.TwoWheel" bundle="${gpfLables}"/>	 </b>
  	</td>
  	<td width="25%">
  		${advInbox.cmnLookupMstByPurposeId.lookupDesc}
  	</td>
  
  	<td width="25%"> 
  		<b><hdiits:caption captionid="GPF.priceVehicle" bundle="${gpfLables}"/></b>
  	</td>
  	<td width="25%">
	  	<c:if test="${purposeDtls.purposeAttr7 ne null}"> 
  	 		<script>document.write(decimalPoint(${purposeDtls.purposeAttr7}))</script>
 		</c:if>
  	</td>
  </tr>
  </table>

 <table id="flag6" style="display:none" width="50%">
  <tr>
  	<td width="25%"><b>
  		<hdiits:caption captionid="GPF.priceVehicle" bundle="${gpfLables}"/></b>
  	</td>
  	<td width="25%">
  		<c:if test="${purposeDtls.purposeAttr7 ne null}"> 
  	 		<script>document.write(decimalPoint(${purposeDtls.purposeAttr7}))</script>
 		</c:if>
  	</td>
  </tr>
  </table>

 <table id="flag7" style="display:none" width="50%">
  <tr>
  	<td width="25%">  
  		<b><hdiits:caption captionid="GPF.oth" bundle="${gpfLables}"/></b>
  	</td>
  	<td width="25%"> 
  		${advInbox.others}
  	</td>
  </tr>
  </table>
  </hdiits:fieldGroup>
  
  <table width=100%>
  	<tr width=100%>
  	<td width=100%>
<!-- For attachment : Start--> 
<c:if test="${advInbox.cmnAttachmentMst!=null}">
<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="AdvAttatchment" />
				<jsp:param name="formName" value="frmgpfAdvInbox" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="N" />
				<jsp:param name="rowNumber" value="1" />   
				</jsp:include> 
<!-- For attachment : End-->
<script>document.getElementById('target_uploadAdvAttatchment').style.display='none';
				document.getElementById('formTable1AdvAttatchment').firstChild.firstChild.style.display='none';
				</script>
</c:if>
</td></tr></table>

<hdiits:fieldGroup titleCaptionId="GPF.apprDet" bundle="${gpfLables}" id="apprDetFldGrp">

<table width="100%" id="apprDet" align="center" style="display:none">		
<tr>
 	<td>
 	 <p id="requestCancel" style="display:none"> 
				<font color="RED">
				CANCELLED REQUEST
				</font>
			</p>
 	<p id="enterAllFields" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.enterAllFields" bundle="${gpfLables}"/></font></p>
	<p id="enterAppAG" style="display:none">
				<font color="RED"><hdiits:caption captionid="GPF.enterAppAG" bundle="${gpfLables}"/></font></p>
	<p id="orderDateErr" style="display:none">
				<font color="RED"><hdiits:caption captionid="GPF.orderDateErr" bundle="${gpfLables}"/></font></p>
	<p id="orderDateApp" style="display:none">
				<font color="RED"><hdiits:caption captionid="GPF.orderDateApp" bundle="${gpfLables}"/></font></p>
 	<p id="appDateErr" style="display:none">
				<font color="RED"><hdiits:caption captionid="GPF.appDateErr" bundle="${gpfLables}"/></font></p>
	<p id="appDateErr2" style="display:none">
				<font color="RED"><hdiits:caption captionid="GPF.appDateErr2" bundle="${gpfLables}"/></font></p>
	<p id="amtSancError" style="display:none">
				<font color="RED"><hdiits:caption captionid="GPF.amtSancError" bundle="${gpfLables}"/></font></p>
	<p id="amtSancInvalid" style="display:none">
				<font color="RED"><hdiits:caption captionid="GPF.amtSancInvalid" bundle="${gpfLables}"/></font></p>
	<p id="noSplChar" style="display:none"> 
			<font color="RED"><hdiits:caption captionid="GPF.noSplChar" bundle="${gpfLables}"/></font></p>
	
	<p id="CashSectionReject" style="display:none">
		<b><hdiits:caption captionid="GPF.csReject" bundle="${gpfLables}"/>	</b></p>
	
	<p id="CashSectionApprove" style="display:none">
  		<b><hdiits:caption captionid="GPF.csApprove" bundle="${gpfLables}"/> 	</b> </p>
	
 	</td>
 </tr>
</table>

<table id="orderDtls" style="display:none" width="50%">

	<tr>
		<td width="25%" > <b><hdiits:caption captionid="GPF.agResponse" bundle="${gpfLables}"/></b></td>
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
		<td width="25%"> <b><hdiits:caption captionid="GPF.orderNo" bundle="${gpfLables}"/> </b></td>
		<td width="25%"> ${advInbox.orderNo}</td>
	</tr>
	
	<tr>
		<td width="25%" > <b><hdiits:caption captionid="GPF.orderDate" bundle="${gpfLables}"/> </b></td>
		<td width="25%"><fmt:formatDate pattern="dd/MM/yyyy" value="${advInbox.orderDt}" var="orderDate"/>  ${orderDate} </td>
	</tr>
	
	<tr>
		<td width="25%"> <b><hdiits:caption captionid="GPF.amtSanc" bundle="${gpfLables}"/> (<hdiits:caption captionid="GPF.INR" bundle="${gpfLables}"/>)</b></td>
		<td width="25%" >
		<c:if test="${advInbox.advSanctioned!=''}"> 
  	 		<script>document.write(decimalPoint(${advInbox.advSanctioned}))</script>
 		</c:if>
 		<c:if test="${advInbox.advSanctioned==''}"> 
  	 		<script>document.write('-')</script>
 		</c:if>
		</td>
	</tr>
	
	<tr>
		<td width="25%"> <b><hdiits:caption captionid="GPF.approvalDate" bundle="${gpfLables}"/></b></td>
		<td width="25%"><fmt:formatDate pattern="dd/MM/yyyy" value="${advInbox.advSanctionedDt}" var="approvalDate"/>  ${approvalDate} </td>
	</tr>
	
</table>
		
			
<table id="approveAgTable" style="display:none" width="50%">

  <tr>
	<td width="25%"> <b>
   <hdiits:caption captionid="GPF.agResponse" bundle="${gpfLables}"/>  </b>
     </td>
      		
      <td width="25%">
      <hdiits:select name="agResponse" id="agResponse" onchange="showAmtSanctioned();" mandatory="true">
			<option id="a" value="Select"  > <fmt:message key="GPF.select"/></option>
				<c:forEach var="name" items="${agResponse}">						
    			<option id="opt" name="opt" value="<c:out value="${name.lookupShortName}"/>"  >
    				<c:out value="${name.lookupDesc}"/>
    			</option>						
				</c:forEach>
	  </hdiits:select>

	</td>		
</tr>

<tr>
<td width="25%"><b><hdiits:caption captionid="GPF.orderDate" bundle="${gpfLables}"/>  </b>
</td>
<td width="25%">
 		<hdiits:dateTime name="orderDt" captionid="GPF.orderDate" bundle="${gpfLables}" mandatory="true" onblur="validateDate();" maxvalue="31/12/2099 00:00:00"/>
 		</td>	
</tr>

<tr>
<td width="25%"> <b><hdiits:caption captionid="GPF.orderNo" bundle="${gpfLables}"/></b> </td>
	<td>
 		<hdiits:number name="orderNo" mandatory="true" id="orderno" maxlength="10"/>
 	</td>
</tr>

<tr id="amtSanctioned">
	<td width="25%">
		<b><hdiits:caption captionid="GPF.amtSanc" bundle="${gpfLables}"/> </b>
	</td>
	
	<td width="25%">
		<hdiits:number name="amtSanc" mandatory="true" id="amtSanc" onblur="roundAmtSanc();" maxlength="10"/>
	</td>
</tr>

<tr>
	<td width="25%">
		<b><hdiits:caption captionid="GPF.approvalDate" bundle="${gpfLables}"/></b>
	</td>
	
	<td width="25%">
	<hdiits:dateTime name="appDt" captionid="GPF.approvalDate" bundle="${gpfLables}" mandatory="true"  onblur="validApprovalDt();" maxvalue="31/12/2099 00:00:00"/>
 		</td>	
</tr>


</table>
</hdiits:fieldGroup>
 
 <!--<table width="100%" id="advHstBlank" style="display:none">
 <tr bgcolor="#386CB7">
<td class="fieldLabel" colspan="4">
<font color="#ffffff">
<strong><u><hdiits:caption captionid="GPF.advHistory" bundle="${gpfLables}"/></u></strong>
</font>
<a href="javascript:void(0);" onclick="dispAdv();"><img id="collapseTicket" src="images/collapse.gif" /></a>
</td>
</tr>
 <tr>
	<td>
		<p id="noAdvReq" style="display:none"> 
		<hdiits:caption captionid="GPF.noAdvReqHst" bundle="${gpfLables}"/>
		</p>
	</td>
 </tr>
</table>
 
 --><!--<table width="100%" id="advHst">
 <tr bgcolor="#386CB7">
	<td class="fieldLabel" colspan="10">
	<font color="#ffffff">
	<strong><u><hdiits:caption captionid="GPF.clickAdvHst" bundle="${gpfLables}"/></u></strong>
	</font>
	<a href="javascript:void(0);" onclick="dispAdv();"><img id="expandTicket" src="images/expand.gif" /></a>
	</td>
	</tr>
	
	
 </table>
 
 -->

 	
 	<%@ include file="../gpf/gpfPrevAdv.jsp"%>
  
   	<hdiits:jsField jsFunction="setActionSave()" name="setActionSave"/>
	
<table width="100%">
<tr align="right">
	<td>
		<font size="1">
		<hdiits:caption captionid="GPF.commonNote" bundle="${gpfLables}"/><br>
		<hdiits:caption captionid="GPF.amtFormat" bundle="${gpfLables}"/><br>
		<hdiits:caption captionid="GPF.dateFormat" bundle="${gpfLables}"/><br>
		3. <hdiits:caption captionid="GPF.mandatory" bundle="${gpfLables}"/>
		</font>
	</td>
</tr>
</table>		
	
</div>
</div>
 
<script type="text/javascript">
	initializetabcontent("maintab")

function makeReadOnly()
{
	document.frmgpfAdvInbox.orderDt.readOnly=true;			
	document.frmgpfAdvInbox.appDt.readOnly=true;	
}

function dispPurpose()
	{
	var flag=0;	
	if(lookupNamePurpose=="gpf.Illness")
			{
			//Illness
			flag=1; 	
			document.getElementById("flag1").style.display='block';
			}
	else if(lookupNamePurpose=="gpf.Higher Education")
			{
			//Higher Edu
			flag=2; 	
			document.getElementById("flag2").style.display='block';
			}
	else if(lookupNamePurpose=="gpf.Customary Expenses")
			{
			//Customary Expenses
			flag=3; 	
			document.getElementById("flag3").style.display='block';
			}
	else if(lookupNamePurpose=="gpf.Higher Secondary Edu")
			{
			//Higher Sec Edu
			flag=4; 	
			document.getElementById("flag4").style.display='block';
			}
	else if(lookupNamePurpose=="gpf.Two Wheeler")
			{
			//Two Wheeler
			flag=5; 	
			document.getElementById("flag5").style.display='block';
			}
	else if(lookupNamePurpose=="gpf.Tricycle")
			{
			//Tricycle
			flag=6; 	
			document.getElementById("flag6").style.display='block';
			}
	else if(lookupNamePurpose=="gpf.Other")
			{
			//Other
			flag=7; 	
			document.getElementById("flag7").style.display='block';
			}
	}

makeReadOnly();
dispPurpose();

document.getElementById("apprDetFldGrp").style.display='none';

if(approveIn=='C')
{
	document.getElementById("requestCancel").style.display='block';
}

</script>


<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

<%
} catch (Exception e) 
	{
	e.printStackTrace();
	}
%>

