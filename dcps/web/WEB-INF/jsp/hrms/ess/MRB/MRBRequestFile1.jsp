<!-- pranav 218914 ASE-T -->
<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.ess.MRB.MRBLables" var="cmnLables"
	scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="script/common/Validation.js" />
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js" />

<script type="text/javascript"
	src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>


<script type="text/javascript">
 
  function table(){
 


  var status=document.getElementById("rem").value;
 
  if(status=='Y'){

  
  document.getElementById("case2").style.display="";
  document.getElementById("case1").style.display="none";
  document.getElementById("case2").disable=true;

 
  }
  else{
  document.getElementById("case1").style.display="";
  document.getElementById("case2").style.display="none";
  
  }
  
  }
 
 function mdcnfrwd(medstatus)
 {

		   var status=document.getElementById("rem").value;
           var amtf=document.getElementById("amtf").value;
           var miscamt =document.getElementById("miscamtot").value; 
           var status=document.getElementById("rem").value; 
           
            if(status==('PendingReq')||status==('ApproveReq'))
            {
            	
            	if(amtf!=0.0)
            	{
            		
            		document.getElementById("appamt").value =eval(amtf).toFixed(2);
            		document.getElementById("appamt1").value =eval(amtf).toFixed(2);
            	}
            	
            }
           
 }
 
 
  function status()
  
  {
   var status=document.getElementById("rem").value;
  
  if (status==('ApproveReq'))
  {
 	 document.getElementById("status").style.display="";
  	//document.getElementById("but").style.display="none";
  	//document.getElementById("case2").disabled=true;
  	document.getElementById("appamt").disabled=true;
  
  }
  else{
 
  document.getElementById("status").style.display="none";
 // document.getElementById("but").style.display="";
  
  }
  }
  

function DeductOrAddAmount(q)
{
	
	if(q.checked==true)
	{
		var AllVal=q.value;
		var SeparVal=AllVal.split("/");
		
		document.MRBFRM.approvedAmt.value=parseFloat(document.MRBFRM.approvedAmt.value)+parseFloat(SeparVal[0]);
		document.MRBFRM.approvedAmt1.value=parseFloat(document.MRBFRM.approvedAmt1.value)+parseFloat(SeparVal[0]);
	}
	else
	{
		var AllVal=q.value;
		var SeparVal=AllVal.split("/");
		
		document.MRBFRM.approvedAmt.value=parseFloat(document.MRBFRM.approvedAmt.value)-parseFloat(SeparVal[0]);
		document.MRBFRM.approvedAmt1.value=parseFloat(document.MRBFRM.approvedAmt1.value)-parseFloat(SeparVal[0]);
	}
}


</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="mrbReqVO" value="${resValue.MRBDtls}"></c:set>
<c:set var="medstatus" value="${resValue.medstatus}"></c:set>
<c:set var="mrbId" value="${mrbReqVO.mrbId}"></c:set>
<c:set var="status" value="${mrbReqVO.status}"></c:set>
<c:set var="amtf" value="${mrbReqVO.sanctionedAmt}"></c:set>
<c:set var="mrbDiseaseSet" value="${resValue.DiseaseMstDtls}"></c:set>
<c:set var="mrbTrtmntSet" value="${resValue.TreatmentDtlList}"></c:set>
<c:set var="mrbMedSet" value="${mrbReqVO.hrEssMrbmedicineDtl}"></c:set>
<c:set var="BillDtlList" value="${resValue.BillDtlList}"></c:set>
<c:set var="mrbMiscSet" value="${mrbReqVO.hrEssMrbmiscDtl}"></c:set>
<c:set var="EmpDet" value="${resValue.EmpDet}"></c:set>
<c:set var="eisEmpdpenDtls" value="${resValue.EmpDet}"></c:set>
<c:set var="agedepen" value="${resValue.agedepen}"></c:set>
<c:set var="relation" value="${resValue.relation}"></c:set>
<c:set var="dob" value="${resValue.dob}"></c:set>
<c:set var="name" value="${resValue.name}"></c:set>
<c:set var="fileId" value="${resValue.fileID}"></c:set>

<c:set var="admblamt" value="${mrbReqVO.requestedAmt}"></c:set>


<hdiits:form name="MRBFRM" validate="true" method="POST"
	action="" encType="multipart/form-data">
	<br>

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" compact="compact">
		<li class="selected"><a href="#" rel="tcontent1">
		<b><fmt:message key="MRB.DiseaseDtls" bundle="${cmnLables}" /></b></a></li>
		<li class="selected"><a href="#" rel="tcontent2" >
		<b><fmt:message key="MRB.BillDtls" bundle="${cmnLables}" /></b></a></li>
		<li class="selected"><a href="#" rel="tcontent3" >
		<b><fmt:message key="MRB.MISC_DTLS" bundle="${cmnLables}" /></b></a></li>
	</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<hdiits:fieldGroup titleCaptionId="MRB.empdtl" bundle="${cmnLables}" id="MRB.empdtlId" collapseOnLoad="false">	
	<table width="100%" bgcolor="WHITE">

	<!-- 	<tr bgcolor="#386CB7" width="100%" align="center">
			<td colspan="4" class="fieldLabel1"><font color="#ffffff"><STRONG><U><fmt:message
				key="MRB.empdtl" bundle="${cmnLables}" /> </U></STRONG> </FONT></td>
		</tr> -->
		<tr>
			<hdiits:hidden name="rem" default="${status}" id="rem" />
			<hdiits:hidden name="med" default="${medstatus}" id="medcn" />
			<hdiits:hidden name="amtf" default="${amtf}" id="amtf" />

			<td width="25%"><hdiits:caption captionid="MRB.Name"
				bundle="${cmnLables}" /></td>
			<td width="25%">${EmpDet.empName}</td>
			<td width="25%"><hdiits:caption captionid="MRB.ContactAddress"
				bundle="${cmnLables}" /></td>
			<td width="25%">${EmpDet.address}</td>
		</tr>
		<tr>
			<td width="25%"><hdiits:caption captionid="MRB.Dob"
				bundle="${cmnLables}" /></td>
			<td width="25%"><fmt:formatDate value="${EmpDet.dob}"
				pattern="dd/MM/yyyy" /></td>

			<td width="25%"><hdiits:caption captionid="MRB.Doj"
				bundle="${cmnLables}" /></td>
			<td width="25%"><fmt:formatDate value="${EmpDet.doj}"
				pattern="dd/MM/yyyy" /></td>
		</tr>
		<tr>
			<td width="25%"><hdiits:caption captionid="MRB.Designation"
				bundle="${cmnLables}" /></td>
			<td width="25%">${EmpDet.designation}</td>
			<td width="25%"><hdiits:caption captionid="MRB.PostName"
				bundle="${cmnLables}" /></td>
			<td width="25%">${EmpDet.postName}</td>
		</tr>
		<tr>
			<td width="25%"><hdiits:caption captionid="MRB.BasicSalary"
				bundle="${cmnLables}" /></td>
			<td width="25%">${EmpDet.salary}</td>
			<td width="25%"><hdiits:caption captionid="MRB.PayScale"
				bundle="${cmnLables}" /></td>
			<td width="25%">${EmpDet.payscale}</td>
		</tr>

		<tr>

			<td width="25%"><br>
			</td>
			<td width="25%"><br>
			</td>
		</tr>
	</table>
	</hdiits:fieldGroup>

	<table width="100%">
	<hdiits:fieldGroup titleCaptionId="MRB.patdtl" bundle="${cmnLables}" id="MRB.patdtlId" collapseOnLoad="false">	
<!-- 
		<tr bgcolor="#386CB7" align="center">
			<td colspan="4"><font color="#ffffff"><STRONG><U><fmt:message
				key="MRB.patdtl" bundle="${cmnLables}" /> </U></STRONG> </FONT></td>
		</tr> -->
		<c:set var="Self" value="Self"></c:set>
		<c:if test="${relation!=Self}">
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.Name"
					bundle="${cmnLables}" /></td>
				<td width="25%">${name}</td>

				<td width="25%"><hdiits:caption captionid="MRB.pattype"
					bundle="${cmnLables}" /></td>
				<td width="25%">${relation}</td>
			</tr>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.Dob"
					bundle="${cmnLables}" /></td>
				<td width="25%"><fmt:formatDate value="${dob}"
					pattern="dd/MM/yyyy" /></td>
				<td width="25%"><hdiits:caption captionid="MRB.age"
					bundle="${cmnLables}" /></td>
				<td width="25%">${agedepen}</td>
			</tr>
		</c:if>
		<c:if test="${relation==Self}">
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.pattype"
					bundle="${cmnLables}" /></td>
				<td width="25%">${relation}</td>
			</tr>
		</c:if>

		<tr>

			<td width="25%"><br>
			</td>
			<td width="25%"><br>
			</td>
		</tr>
		</hdiits:fieldGroup>
			<hdiits:fieldGroup titleCaptionId="MRB.DISEASE_DETAILS" bundle="${cmnLables}" id="MRB.DISEASE_DETAILSlId" collapseOnLoad="false">	
		
		<tr>
			<td colspan="4"><display:table list="${resValue.DiseaseMstDtls}"
				requestURI="" defaultsort="1" defaultorder="ascending" id="row"
				style="width:100%" >
				<display:column class="tablecelltext"  titleKey="MRB.DISEASE_TYPE"
				  headerClass="datatableheader" style="text-align: center">${row.parentDisDesc }
				</display:column>
				<display:column class="tablecelltext" titleKey="MRB.DISEASE_CAT"
					headerClass="datatableheader" style="text-align: center">${row.diseaseDesc }
				</display:column>
				<display:column  class="tablecelltext" titleKey="MRB.REMARKS"
					 headerClass="datatableheader"
					style="text-align: center">${row.diseaseRemarks }
				</display:column>
			</display:table></td>
		</tr>
		<tr>

			<td width="25%"><br>
			</td>
			<td width="25%"><br>
			</td>
		</tr>
		<tr>
			<td colspan="4"></td>
		</tr>
		</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="MRB.TRTMNT_DTLS" bundle="${cmnLables}" id="MRB.TRTMNT_DTLSId" collapseOnLoad="false">	
	<!-- 	<tr bgcolor="#386CB7" width="100%" align="center">
			<td colspan="4" class="fieldLabel1"><font color="#ffffff"><STRONG><U><fmt:message
			key="MRB.TRTMNT_DTLS" bundle="${cmnLables}" /> </U></STRONG> </FONT></td>
		</tr> -->
		
		<tr>
			<td colspan="4"><display:table list="${resValue.TreatmentDtlList}"
				requestURI="" defaultsort="1" defaultorder="ascending" id="row1"
				style="width:100%">
				<display:column 
					class="tablecelltext" titleKey="MRB.HSPTL_NAME"
					headerClass="datatableheader" style="text-align: center" >${row1.hospitalDesc}</display:column>
				<display:column 
					class="tablecelltext" titleKey="MRB.HSPTL_TYPE"
					headerClass="datatableheader" style="text-align: center" >${row1.hospitaltype}</display:column>
				<display:column 
					class="tablecelltext" titleKey="MRB.HSPTL_ADRS"
					headerClass="datatableheader" style="text-align: center" >${row1.hospitalAddr}</display:column>
				<display:column  
				class="tablecelltext" titleKey="MRB.TRTMNT_TYPE"
				headerClass="datatableheader" style="text-align: center">${row1.trtmentType}</display:column>
			</display:table></td>
		</tr>
		</hdiits:fieldGroup>
	</table>

	<script>
		var BlankStr="";
	</script>
	<br>
	<hdiits:fieldGroup titleCaptionId="MRB.APPROVER_DTLS" bundle="${cmnLables}" id="MRB.APPROVER_DTLSId" collapseOnLoad="false">	
	<table width="100%">
		<TBODY>
			
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.APPROVER_NAME" bundle="${cmnLables}"/> </td>
				<td width="25%"> <b><c:out value="${mrbReqVO.approverName}"></c:out> </b></td>
				
				<td width="25%"><hdiits:caption captionid="MRB.APPROVER_DESIG" bundle="${cmnLables}"/> </td>
				<td width="25%"><b><c:out value="${mrbReqVO.approverDesig}"></c:out></b> </td>
			</tr>
			
			
			
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_NAME" bundle="${cmnLables}"/> </td>
				<td width="25%"> <b><c:out value="${mrbReqVO.hospitalName}"></c:out></b></td>
				
				<td width="25%"><hdiits:caption captionid="MRB.HSPTL_ADRS" bundle="${cmnLables}"/> </td>
				<c:if test="${mrbReqVO.hospitalAddress!=BlankStr}">
				<td width="25%"><b> <c:out value="${mrbReqVO.hospitalAddress}"></c:out></b></td>
				</c:if>
				<c:if test="${mrbReqVO.hospitalAddress==BlankStr}">
				<td width="25%"><b> <c:out value="----Nil----"></c:out></b></td>
				</c:if>
				
								
			</tr>
		</TBODY>
	</table>
	</hdiits:fieldGroup>
	<br>
</div>

<div id="tcontent2" class="tabcontent" tabno="1">

<script>
 var GlobFlag=false;
</script>
<hdiits:fieldGroup titleCaptionId="MRB.BILL_DTLS" bundle="${cmnLables}" id="MRB.BILL_DTLSId" collapseOnLoad="false">	
<table width="100%" bgcolor="WHITE">
		<tr>
			<td colspan="4"></td>
		</tr>
		<tr>
		<td>
		<table id="BillDtlsTab"  border="1" width="100%" >
		<TBODY>
			<tr>
				
				<td align="middle" class="datatableheader" ><b><fmt:message key="MRB.BILL_NO" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="datatableheader" ><b><fmt:message key="MRB.BILL_DT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="datatableheader" ><b><fmt:message key="MRB.CHEMIST_NAME" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="datatableheader" ><b><fmt:message key="MRB.BILL_AMT" bundle="${cmnLables}" /></b></td>
				<td align="middle" class="datatableheader" ><b><fmt:message key="MRB.MED_DTLS" bundle="${cmnLables}" /></b></td>	
				
			</tr>
			<c:set var="BillTotal" value="0"/>
			<c:set var="TotalApprovAmnt" value="0"></c:set>
			<c:forEach var="BillDtlList" items="${resValue.BillDtlList}">
				<tr>
 					<td><c:out value="${BillDtlList.billNo}"></c:out> </td>
 					<td><fmt:formatDate value="${BillDtlList.billDate}" pattern="dd/MM/yyyy"/></td>
 					<td><c:out value="${BillDtlList.chemistName}"></c:out></td>
 					<td><c:out value="${BillDtlList.billAmnt}"></c:out></td>
 					
 					<c:set var="BillTotal" value="${BillTotal+BillDtlList.billAmnt}" ></c:set> 					
 					<td>
 						<table id="MediDtl" border="1" width="100%" >
 							<tbody>
 								<tr>
 									<td align="middle" class="datatableheader"></td>
 									<td width='20%' align='middle' class='datatableheader' ><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_NAME"/></b></td>
									<td width='20%' align='middle' class='datatableheader' ><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_TYPE"/></b></td>
									<td align='middle' width='20%' class='datatableheader' ><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_CAT"/></b></td>
									<td align='middle' width='20%' class='datatableheader'><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_WT"/></b></td>
									<td align='middle' width='20%' class='datatableheader' ><b><fmt:message  bundle="${cmnLables}"  key="MRB.MED_AMT"/></b></td>
 								</tr>
 								
 								<c:forEach var="MediList" items="${BillDtlList.MRbVOList}">
 								
 								<hdiits:hidden name="medstatus" default="${MediList.medicineStatus}" id="medstatus" />
								<c:set var="condition" value="checked" />
								<c:choose>
								<c:when test="${MediList.medicineStatus!='Y'}">
								<c:set var="condition" value="" />
								</c:when>
								<c:otherwise>
								<c:set var="condition" value="checked" />
								</c:otherwise>
								</c:choose> 
								
 								<c:if test="${MediList.medicineStatus=='Y'}">
 								<c:set var="TotalApprovAmnt" value="${TotalApprovAmnt+MediList.medicineAmnt}"></c:set>
 								</c:if>
 								<c:if test="${MediList.medicineId==0}">
 								<tr bgcolor="lightblue">
 									<td><input type="checkbox" name="MediId" id="MediId" value="${MediList.medicineAmnt}/${MediList.mediDtlSrNo}" onclick="DeductOrAddAmount(this)" ${condition} readonly="readonly" disabled="disabled"/></td>
 									<td ><c:out value="${MediList.medicineName}"></c:out> </td>
 									<td ><c:out value="${MediList.medicineType}"></c:out></td>
 									<td ><c:out value="${MediList.medicineCat}"></c:out></td>
 									<td ><c:out value="${MediList.medicineWt}"></c:out></td>
 									<td ><c:out value="${MediList.medicineAmnt}"></c:out></td>
 								</tr>
 								</c:if>
 								<c:if test="${MediList.medicineId!=0}">
 								
 								
 								<tr>
 									<td><input type="checkbox" name="MediId" id="MediId" value="${MediList.medicineAmnt}/${MediList.mediDtlSrNo}" onclick="DeductOrAddAmount(this)" ${condition} readonly="readonly" disabled="disabled"/></td>
 									<td ><c:out value="${MediList.medicineName}"></c:out> </td>
 									<td ><c:out value="${MediList.medicineType}"></c:out></td>
 									<td ><c:out value="${MediList.medicineCat}"></c:out></td>
 									<td ><c:out value="${MediList.medicineWt}"></c:out></td>
 									<td ><c:out value="${MediList.medicineAmnt}"></c:out></td>
 								</tr>
 								</c:if>
 								</c:forEach>
 							</tbody>
 						</table>
 					</td>
				</tr>
			</c:forEach>
		</TBODY>
	</table>
	</td>
</tr>
</table>

<table id="ShowBlue" width="100%" style="display:none">
	<tr>
		<td>
			<font color="red" size="2"><b><fmt:message key="MRB.BlueColor" bundle="${cmnLables}"></fmt:message></b></font>
		</td>
	</tr>
</table>
<script>
 	
 	if(GlobFlag==true)
 	{
 		document.getElementById('ShowBlue').style.display="";
 	}
</script>
<br>
<table align="left">
	<tr>
		<td  colspan="8"><b><hdiits:caption
			captionid="mrb.bilamt" bundle="${cmnLables}" />
			<c:out value="${BillTotal}"></c:out></b></td>
		</tr>
		<tr>
			<td colspan="4"><c:set var="miscAmt" value="0"></c:set> 
			<c:forEach var="resValue13" items="${mrbMiscSet}">
			<c:set var="miscAmt" value="${miscAmt+resValue13.amount}"></c:set>
			<hdiits:hidden name=" miscamt1" id="miscamt2" default="${miscAmt}"/>
			</c:forEach>
			</td>
		</tr>
		<tr>			
			<td colspan="8" id="hi">
			<b><hdiits:caption captionid="mrb.miscamt" bundle="${cmnLables}" />
			<c:out value="${miscAmt}"></c:out></b></td>
			<hdiits:hidden name="micsamttot" default="${miscAmt}" id="miscamtot" />
		</tr>
		<tr>
			<td colspan="4"><b><hdiits:caption
				captionid="MRB.totadamt" bundle="${cmnLables}" id="totadamt" /><c:out
				value="${admblamt}"></c:out> </b></td>
		</tr>
		
		<tr>
			<td colspan="4" ><hdiits:hidden
				name="iniApprovedAmt" default="${TotalApprovAmnt}" id="iniaprvamt" /> <hdiits:hidden
				name="mrbId" default="${mrbId}" /> <b><hdiits:caption
				captionid="MRB.APRVD_AMT" bundle="${cmnLables}" /> <hdiits:number
				name="approvedAmt1" default="${TotalApprovAmnt+miscAmt}" id="appamt1" readonly="true"/></b></td>
		</tr>
		
</table>
			</hdiits:fieldGroup>
</div>

<div id="tcontent3" class="tabcontent" tabno="2">
<hdiits:fieldGroup titleCaptionId="MRB.MISC_DTLS" bundle="${cmnLables}" id="MRB.MISC_DTLSId" collapseOnLoad="false">
	<table width="100%" bgcolor="WHITE">

		<tr>
			<td colspan="4"></td>
		</tr>
		
		
		<tr>
			<td colspan="4"><c:set var="miscAmt" value="0"></c:set> <c:forEach
				var="resValue13" items="${mrbMiscSet}">

				<c:set var="miscAmt" value="${miscAmt+resValue13.amount}"></c:set>
				<hdiits:hidden name=" miscamt1" id="miscamt2" default="${miscAmt}" />

			</c:forEach> <display:table name="${mrbMiscSet}" requestURI="" defaultsort="1"
				defaultorder="ascending" id="row" style="width:100%">
				<display:column property="remarks" class="tablecelltext" titleKey="MRB.REMARKS"
					headerClass="datatableheader"
					style="text-align: center">
				</display:column>
				<display:column property="amount" class="tablecelltext" titleKey="MRB.MISC_AMT"
					headerClass="datatableheader"
					style="text-align: center">

				</display:column>

			</display:table>
			<br><br>
			<table align="left">
				<tr>
					<td colspan="8"><b><hdiits:caption
					captionid="mrb.bilamt" bundle="${cmnLables}" />
					<c:out value="${BillTotal}"></c:out></b></td>
				</tr>
				
				<tr>
					<td align="left" colspan="8" id="hi"><b><hdiits:caption
						captionid="mrb.miscamt" bundle="${cmnLables}" /><c:out
						value="${miscAmt}"></c:out></b></td>
					<hdiits:hidden name="micsamttot" default="${miscAmt}"
						id="miscamtot" />
				</tr>

			</table>

			</td>
		</tr>
		<tr>
			<td colspan="4" align="left"><b><hdiits:caption
				captionid="MRB.totadamt" bundle="${cmnLables}" id="totadamt" /><c:out
				value="${admblamt}"></c:out> </b></td>
		</tr>
		
		<tr>
			<td colspan="4" align="left">
			<hdiits:hidden name="iniApprovedAmt" default="${TotalApprovAmnt}" id="iniaprvamt" /> 
			<hdiits:hidden name="mrbId" default="${mrbId}" /> 
			<b><hdiits:caption captionid="MRB.APRVD_AMT" bundle="${cmnLables}"/>
			<hdiits:number name="approvedAmt" default="${TotalApprovAmnt+miscAmt}" id="appamt" readonly="true"/></b></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	<br>
	<table id=status align="center">
		<tr>
			<td colspan="4"><font color="red"><fmt:message key="MRB.status" bundle="${cmnLables}" /></font></td>
		</tr>
	</table>
	<script>	
	status();
	</script>
<br>
	<!-- For attachment : Start-->
	<hdiits:table id="MRBAttachment" width="100%">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="MRBAttachment" />
				<jsp:param name="formName" value="MRBFRM" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="N" />
			</jsp:include></td>
		</tr>
	</hdiits:table>
	<script>
				document.getElementById('target_uploadMRBAttachment').style.display='none';
				document.getElementById('formTable1MRBAttachment').firstChild.firstChild.style.display='none';
				
				
	</script>

	<!-- For attachment : End-->

<script>		
		mdcnfrwd();
</script>
</div>
</div>
	<input type="hidden" name="fileId" id="fileId" value="${fileId}" />

<script type="text/javascript">
	initializetabcontent("maintab");
	</script> 
	
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
