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
<script type="text/javascript" src="script/hrms/ess/gpf/gpfAdv.js"></script>
 
<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="gpffunc" value="${resValue.GPFFunc}"></c:set>
<c:set var="gpfPurpose" value="${resValue.GPFPurpose}"></c:set>
<c:set var="gpfIll" value="${resValue.GPFIll}"></c:set>
<c:set var="gpfPurposeCustom" value="${resValue.GPFPurposeCustom}"></c:set>
<c:set var="gpfTwoWheeler" value="${resValue.GPFTwoWheeler}"></c:set>
<c:set var="gpfHighEdu" value="${resValue.GPFHighEdu}"></c:set>
<c:set var="gpfEduClass" value="${resValue.GPFEduClass}"></c:set>

<c:set var="date" value="${resValue.todayDate}"></c:set>
<c:set var="empPay" value="${resValue.empPay}"></c:set>
<c:set var="phyChallenged" value="${resValue.phyChallenged}"></c:set>

<c:set var="relationIllnessGpf" value="${resValue.relationIllnessGpf}"></c:set>
<c:set var="gpfRelationEdu" value="${resValue.gpfRelationEdu}"></c:set>
<c:set var="advDataElectronicItem" value="${resValue.advDataElectronicItem}"></c:set>
<c:set var="advDataVehicleTax" value="${resValue.advDataVehicleTax}"></c:set> 
<c:set var="advDataPilgrimage" value="${resValue.advDataPilgrimage}"></c:set>
<c:set var="valid" value="${resValue.valid}"></c:set> 
<c:set var="advDtls" value="${resValue.advDtls}"></c:set> 
<c:set var="purposeDtls" value="${resValue.purposeDtls}"></c:set> 
<c:set var="hrGpfBalanceDtls" value="${resValue.hrGpfBalanceDtls}"></c:set>
<c:set var="familyList" value="${resValue.familyList}"></c:set>
<c:set var="familyListEdu" value="${resValue.familyListEdu}"></c:set>
<c:set var="sizeEdu" value="${resValue.sizeEdu}"></c:set>
<c:set var="advHistory" value="${resValue.advHistory}"></c:set>
<c:set var="finYrStart" value="${resValue.finYrStart}"></c:set>
<c:set var="selfEdu" value="${resValue.self}"></c:set>
<c:set var="pendingReqSub" value="${resValue.pendingReqSub}"></c:set>
<c:set var="pendingReqWithFinal" value="${resValue.pendingReqWithFinal}"></c:set>
<c:set var="approveWithReqFinal" value="${resValue.approveWithReqFinal}"></c:set>
<c:set var="pendingAdvRefund" value="${resValue.pendingAdvRefund}"></c:set> 

<script>
var arrMemberId=new Array();
<fmt:formatDate pattern="dd/MM/yyyy" value="${date}" var="today"/>
var todayDate="${today}";
var valid="${valid}";
var pay="${empPay}";
var pendingAdvRefund="${pendingAdvRefund}";
var advDataElectronicItem="${advDataElectronicItem}";
var advDataVehicleTax="${advDataVehicleTax}";
var phyChallenged="${phyChallenged}";
var advDataPilgrimage="${advDataPilgrimage}";
var pendingReqSub="${pendingReqSub}";
var pendingReqWithFinal="${pendingReqWithFinal}";
var approveWithReqFinal="${approveWithReqFinal}";
var isAdvHstEmpty="${empty advHistory}";

function radioDefaultSelected()
{

	if("${advDtls.specialAdvance}"=="<fmt:message key="GPF.Y"/>")
		{
		document.frmAdvGpf.splAdv[0].checked = true;
		}
	else if("${advDtls.specialAdvance}"=="<fmt:message key="GPF.N"/>")
		{
		document.frmAdvGpf.splAdv[1].checked = true;
		}
		
	if("${purposeDtls.purposeAttr5}"=="<fmt:message key="GPF.Y"/>")
		{
		document.frmAdvGpf.expReimbursable[0].checked = true;
		}

	else if("${purposeDtls.purposeAttr5}"=="<fmt:message key="GPF.N"/>")
		{
		document.frmAdvGpf.expReimbursable[1].checked = true;
		}
		
	if("${purposeDtls.purposeAttr4}"=="<fmt:message key="GPF.dayScholar"/>")
		{
		document.frmAdvGpf.stuType[0].checked = true;
		}
	else if("${purposeDtls.purposeAttr4}"=="<fmt:message key="GPF.hostelite"/>")
		{
		document.frmAdvGpf.stuType[1].checked = true;
		}
		
	if("${purposeDtls.purposeAttr4}"=="<fmt:message key="GPF.indoor"/>")
		{
		document.frmAdvGpf.patientType[0].checked = true;
		}
	else if("${purposeDtls.purposeAttr4}"=="<fmt:message key="GPF.outdoor"/>")
		{
		document.frmAdvGpf.patientType[1].checked = true;
		}
}	

</script>

<hdiits:form name="frmAdvGpf" validate="true" method="POST" 
	action="hrms.htm?actionFlag=gpfAdvData" encType="multipart/form-data">
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


<table>	
		<tr> 
		<td>
		<p id="case0" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.advAmtLimit" bundle="${gpfLables}"/></font>
		</p>
		<p id="case1" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.motorcycleInvalid" bundle="${gpfLables}"/></font>
		</p>
		<p id="case2" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.scooterInvalid" bundle="${gpfLables}"/></font>
		</p>

		<p id="case3" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.mopedInvalid" bundle="${gpfLables}"/></font>
		</p>

		<p id="case4" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.10thInvalid" bundle="${gpfLables}"/></font>
		</p>

		<p id="case5" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.11thInvalid" bundle="${gpfLables}"/></font>
		</p>

		<p id="case6" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.12thInvalid" bundle="${gpfLables}"/></font>
		</p>

		<p id="case7" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.splAdvInvalid" bundle="${gpfLables}"/></font>
		</p>

		<p id="case8" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.normalAdvInvalid" bundle="${gpfLables}"/></font>
		</p>

		<p id="case9" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.tricycleInvalid" bundle="${gpfLables}"/></font>
		</p>
		
		<p id="case11" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.withSamePurpose" bundle="${gpfLables}"/></font>
		</p>
		
		
		<p id="selectDependent" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selectDependent" bundle="${gpfLables}"/></font>
		</p>
		
		<p id="selAdvSpl" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selAdvSpl" bundle="${gpfLables}"/></font>
		</p>

<p id="enterAdvAmt" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.enterAdvAmt" bundle="${gpfLables}"/></font>
</p>
<p id="advAmtZero" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.advAmtZero" bundle="${gpfLables}"/></font>
</p>
<p id="enterValidNo" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.enterValidNo" bundle="${gpfLables}"/></font>
</p>

<p id="noOfInstall" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.noOfInstall" bundle="${gpfLables}"/></font>
</p>

<p id="selPurpCat" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selPurpCat" bundle="${gpfLables}"/></font>
</p>

<p id="advNoDpndt" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.advNoDpndt" bundle="${gpfLables}"/></font>
</p>

<p id="selPurp" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selPurp" bundle="${gpfLables}"/></font>
</p>

<p id="enterHospital" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.enterHospital" bundle="${gpfLables}"/></font>
</p>

<p id="noSplChar" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.noSplChar" bundle="${gpfLables}"/></font>
</p>

<p id="selPatientType" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selPatientType" bundle="${gpfLables}"/></font>
</p>

<p id="selExpReimb" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selExpReimb" bundle="${gpfLables}"/></font>
</p>

<p id="advNoDpndt" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.advNoDpndt" bundle="${gpfLables}"/>
			</font>
</p>

<p id="selHighEduPurp" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selHighEduPurp" bundle="${gpfLables}"/>
			</font>
</p>

<p id="enterInsti" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.enterInsti" bundle="${gpfLables}"/>
			</font>
</p>

<p id="selClass" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selClass" bundle="${gpfLables}"/>
			</font>
</p>

<p id="selStuType" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selStuType" bundle="${gpfLables}"/>
			</font>
</p>

<p id="pilgrimageInvalid" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.pilgrimageInvalid" bundle="${gpfLables}"/></font>
</p>

<p id="selTwoWheeler" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.selTwoWheeler" bundle="${gpfLables}"/></font>
</p>

<p id="twoWheelerPrice" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.twoWheelerPrice" bundle="${gpfLables}"/></font>
</p>

<p id="advGtPrice" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.advGtPrice" bundle="${gpfLables}"/></font>
</p>

<p id="enterPriceTri" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.enterPriceTri" bundle="${gpfLables}"/></font>
</p>

<p id="phyChallenged" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.phyChallenged" bundle="${gpfLables}"/></font>
</p>

<p id="vehicleTaxInvalid" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.vehicleTaxInvalid" bundle="${gpfLables}"/></font>
</p>

<p id="elecItemInvalid" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.elecItemInvalid" bundle="${gpfLables}"/></font>
</p>
<p id="detPurp" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.detPurp" bundle="${gpfLables}"/></font>
</p>
<p id="eduDependOnce" style="display:none">
			<font color="RED"><hdiits:caption captionid="GPF.eduDependOnce" bundle="${gpfLables}"/></font>
</p>		

	
	<p id="reqPending" style="display:none">
	<font color="RED"><hdiits:caption captionid="GPF.reqPending" bundle="${gpfLables}"/></font></p>
	<p id="withReqPending" style="display:none">
	<font color="RED"><hdiits:caption captionid="GPF.partFinalReqPending" bundle="${gpfLables}"/></font></p>
	<p id="finalWithApprovd" style="display:none">
	<font color="RED"><hdiits:caption captionid="GPF.finalWithDone" bundle="${gpfLables}"/></font></p>
	<p id="advRefundPending" style="display:none">
	<font color="RED"><hdiits:caption captionid="GPF.prevAdvPending" bundle="${gpfLables}"/></font></p>

		</tr>
</table>
<table  width="100%">
 <tr><td width=25%>
 <hdiits:caption captionid="GPF.appDate" bundle="${gpfLables}"/>
		
	</td>
	
	<td width=25%>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${date}" var="today"/>
	<input type="text" name="appDateSys" readonly="true" value="${today}"  style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;"/>
	</td>
	
	<td width=25%>
		<hdiits:caption captionid="GPF.splAdv" bundle="${gpfLables}"/>
	</td>

	<td width=25%>
	   	<hdiits:radio name="splAdv"  value="Y" id="splAdv" mandatory="true" captionid="GPF.yes" bundle="${gpfLables}"/>
	   	<hdiits:radio name="splAdv"  value="N" id="splAdv" mandatory="true" captionid="GPF.no" bundle="${gpfLables}"/>
	</td>

	
	
 </tr>

 <tr>
	
	<td width=25%>
		<hdiits:caption captionid="GPF.advamt" bundle="${gpfLables}"/> (<hdiits:caption captionid="GPF.INR" bundle="${gpfLables}"/>)
	</td>
	
	<td width=25%>
		<hdiits:number name="advAmt" id="advAmt" mandatory="true" maxlength="9" caption="Advance Amount" default="${advDtls.advAmt}" onblur="roundAdv();"/>
	</td>
	<td width=25%>
		<hdiits:caption captionid="GPF.noOfIns" bundle="${gpfLables}"/>
	</td>

	<td width=25%>
		<hdiits:number name="noOfIns" id="noOfIns" mandatory="true" caption="No of Installments" default="${advDtls.noOfInstl}" onblur="roundInst();" maxvalue="36" maxlength="2"/>
	</td>
	
 </tr>

 <tr>
	<td width=25%>
		<hdiits:caption captionid="GPF.purposeCat" bundle="${gpfLables}"/>
	</td>  
	
	<td width=25%>
		<hdiits:select  name="gpfCat" caption="gpfCat" sort="false" onchange="setPurpose();" mandatory="true">
	  		 
				<hdiits:option value="Select"><fmt:message key="GPF.select"/></hdiits:option>
				<c:set var="lookup" value="${advDtls.cmnLookupMstByPurpose.lookupName}"> </c:set>
					<c:forEach var="name" items="${gpfPurpose}">
						 <c:choose>
						
						<c:when test="${name.lookupName == lookup}">
						 <option value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
    					 <c:out value="${name.lookupDesc}"/></option>
						</c:when>
						
						<c:otherwise>
    				 	 <option  value="<c:out value="${name.lookupName}"/>">
						 <c:out value="${name.lookupDesc}"/></option>						
						</c:otherwise>
						
						</c:choose>				
					</c:forEach>
	  		  </hdiits:select>
	</td>
	
	<td width=25%>
	<p id="purposeLabel" style="display:none"> 
	<hdiits:caption captionid="GPF.purpose" bundle="${gpfLables}"/> </p>
	
	 <p id="other" style="display:none">
	<hdiits:caption captionid="GPF.oth" bundle="${gpfLables}"/>   
	 </p>
	
	</td>
	
	
	<td width=25%>
        <hdiits:textarea  classcss="mandatory" name="others" id="others" style="display:none" default="${advDtls.others}"/> 
        
		<hdiits:select name="gpfPurposeIll" id="gpfPurposeIll" sort="false"  style="display:none" classcss="mandatory" >
	  		 	<hdiits:option value="Select">------<fmt:message key="GPF.select"/>------</hdiits:option>
				
					<c:set var="lookup" value="${advDtls.cmnLookupMstByPurposeId.lookupName}"> </c:set>
					<c:forEach var="name1" items="${gpfIll}">
						 <c:choose>
						
						<c:when test="${name1.lookupName == lookup}">
						 <option value="<c:out value="${name1.lookupName}"/>"  selected="selected"> 
    					 <c:out value="${name1.lookupDesc}"/></option>
						</c:when>
						
						<c:otherwise>
    				 	 <option  value="<c:out value="${name1.lookupName}"/>">
						 <c:out value="${name1.lookupDesc}"/></option>						
						</c:otherwise>
						
						</c:choose>				
					</c:forEach>
	  		  </hdiits:select>
	  		  
	   <hdiits:select name="gpfPurposeCust" id="gpfPurposeCust" sort="false"  style="display:none" classcss="mandatory" >
	  		 
				<hdiits:option value="Select">--<fmt:message key="GPF.select"/>--</hdiits:option>
				  <c:set var="lookup" value="${advDtls.cmnLookupMstByPurposeId.lookupName}"> </c:set>
					<c:forEach var="name1" items="${gpfPurposeCustom}">
					 <c:choose>
						<c:when test="${name1.lookupName == lookup}">
						 <option value="<c:out value="${name1.lookupName}"/>"  selected="selected"> 
    					 <c:out value="${name1.lookupDesc}"/></option>
					</c:when>
						
					<c:otherwise>
    					 <option  value="<c:out value="${name1.lookupName}"/>">
						 <c:out value="${name1.lookupDesc}"/></option>						
					</c:otherwise>
						
					</c:choose>				
				  </c:forEach>
	    </hdiits:select>
		    
	    <hdiits:select name="gpfTwoWheeler" id="gpfTwoWheeler" sort="false"  style="display:none" classcss="mandatory"  >
	  		 
				<hdiits:option value="Select">-----<fmt:message key="GPF.select"/>-----</hdiits:option>
				<c:set var="lookup" value="${advDtls.cmnLookupMstByPurposeId.lookupName}"> </c:set>
					<c:forEach var="name1" items="${gpfTwoWheeler}">
						 <c:choose>
						
						<c:when test="${name1.lookupName == lookup}">
						 <option value="<c:out value="${name1.lookupName}"/>"  selected="selected"> 
    					 <c:out value="${name1.lookupDesc}"/></option>
						</c:when>
						
						<c:otherwise>
    				 	 <option  value="<c:out value="${name1.lookupName}"/>">
						 <c:out value="${name1.lookupDesc}"/></option>						
						</c:otherwise>
						
						</c:choose>				
					</c:forEach>
			    </hdiits:select>
	    
	     <hdiits:select name="gpfHighEdu"  id="gpfHighEdu" sort="false"  style="display:none" classcss="mandatory" >
	  		 
				<hdiits:option value="Select">---<fmt:message key="GPF.select"/>---</hdiits:option>
				<c:set var="lookup" value="${advDtls.cmnLookupMstByPurposeId.lookupName}"> </c:set>
					<c:forEach var="name1" items="${gpfHighEdu}">
					 <c:choose>
						
					<c:when test="${name1.lookupName == lookup}">
					 <option value="<c:out value="${name1.lookupName}"/>"  selected="selected"> 
    				 <c:out value="${name1.lookupDesc}"/></option>
					</c:when>
						
					<c:otherwise>
    				 <option  value="<c:out value="${name1.lookupName}"/>">
					 <c:out value="${name1.lookupDesc}"/></option>						
					</c:otherwise>
						
					</c:choose>				
				  </c:forEach>
	    </hdiits:select>
	    
	  </td>
</tr>

 </table>  
 </hdiits:fieldGroup>
 


<hdiits:fieldGroup id="patientDet" titleCaptionId="GPF.patientDet" bundle="${gpfLables}" >


<table id="relationDtlsDep" style="display:none">
<tr>
	<td> 
		<font color="RED">
		<hdiits:caption captionid="GPF.noDpndt" bundle="${gpfLables}"/>
		</font>
	</td>
</tr>
</table>

<table id="relationDetails" style="display:none" width="100%">
 <tr>
 	<td width="25%">
		<hdiits:caption captionid="GPF.relation" bundle="${gpfLables}"/>
	</td>
		
	
	<c:set var="i" value="1"></c:set>
	<td width="25%">
			
			<hdiits:select default="${familyData.memberId}" name="dpndt" id="dpndt" mandatory="true" sort="false">
			<option value="Select"> <fmt:message key="GPF.select"/></option>
			
			<c:choose>
			
			<c:when test="${familyData.memberId == purposeDtls.purposeAttr3}">
			<option value="0" selected="selected"><fmt:message key="GPF.Self"/></option>
			</c:when>
			
			<c:otherwise>
			<option value="0"><fmt:message key="GPF.Self"/></option>
			</c:otherwise>
			</c:choose>
			
			<c:forEach var="familyData" items="${familyList}">
			<c:choose>
			<c:when test="${familyData.memberId == purposeDtls.purposeAttr3}">
			<option value="<c:out value="${familyData.memberId}"/>" selected="selected">
			<c:out value="${familyData.fmFirstName}"/> <c:out 	value="${familyData.fmMiddleName}"></c:out> 
			<c:out value="${familyData.fmLastName}"></c:out>,
			<c:out value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>,
			<script>var v1=Age("${familyData.fmDateOfBirth}");</script>
			<script>document.write(v1);</script>
			</option>
			</c:when>
			
			<c:otherwise>
			<option value="<c:out value="${familyData.memberId}"/>">
			<c:out value="${familyData.fmFirstName}"/> <c:out 	value="${familyData.fmMiddleName}"></c:out> 
			<c:out value="${familyData.fmLastName}"></c:out>,
			<c:out value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>,
			<script>var v1=Age("${familyData.fmDateOfBirth}");</script>
			<script>document.write(v1);</script>
			</option>
			</c:otherwise>
			
			</c:choose>
			</c:forEach>
			</hdiits:select>
	</td>
	<td width="25%"></td>
	<td width="25%"></td>
	<c:set var="i" value="${i+1}"></c:set>

	</tr>

   
</table>

<table id="hospitalDel" style="display:none" width="100%">
 
 <tr>
	<td width="25%">
		<hdiits:caption captionid="GPF.patientType" bundle="${gpfLables}"/>
	</td>

	<td width="25%"> <hdiits:radio name="patientType" id="patientType" value="Indoor" mandatory="true" bundle="${gpfLables}" captionid="GPF.indoor"/>
	     <hdiits:radio name="patientType" id="patientType" value="Outdoor" mandatory="true" bundle="${gpfLables}" captionid="GPF.outdoor"/>
	</td>
	<td width="25%"></td>
	<td width="25%"></td>
	
	
 </tr>
 
 <tr>
	<td  width="25%">
		<hdiits:caption captionid="GPF.expReimburse" bundle="${gpfLables}"/>
	</td>

	<td width="25%">
		<hdiits:radio name="expReimbursable" value="Y" mandatory="true" captionid="GPF.yes" bundle="${gpfLables}"/>
		<hdiits:radio name="expReimbursable" value="N" mandatory="true" captionid="GPF.no" bundle="${gpfLables}"/>
	</td>
	
	<td width="25%"></td>
	<td width="25%"></td>
	
 </tr>
 
 <tr>
	<td width="25%">
		<hdiits:caption captionid="GPF.hospitalName" bundle="${gpfLables}"/>
	</td>
	<td width="25%">
		<hdiits:text name="hospitalName" mandatory="true" id="hospitalName" caption="Hospital Name" default="${purposeDtls.purposeAttr6}"/>
	</td>
	<td width="25%"></td>
	<td width="25%"></td>
 </tr>
	
</table>

</hdiits:fieldGroup>


<hdiits:fieldGroup id="vehicleDet" titleCaptionId="GPF.vehicleDet" bundle="${gpfLables}" >


 
<table width=100%>
<tr>
	<td width="25%">
		<hdiits:caption captionid="GPF.priceVehicle" bundle="${gpfLables}"/>
	</td>
	<td width="25%">
		<hdiits:number maxlength="10" name="priceOfVehicle1" mandatory="true" caption="Price of vehicle" default="${purposeDtls.purposeAttr7}" onblur="roundPriceVehicle();"/>
	</td>
	<td width=25%></td>
	<td width=25%></td>

</tr>
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup id="tricycleDet" titleCaptionId="GPF.tricycleDet" bundle="${gpfLables}" >


<table width=100%>
<tr>
	<td width="25%">
		<hdiits:caption captionid="GPF.priceVehicle" bundle="${gpfLables}"/> 
	</td>
	<td width="25%">
		<hdiits:number  maxlength="10" name="priceOfTricycle" mandatory="true" id="priceOfTricycle" caption="Price of tricycle" default="${purposeDtls.purposeAttr7}" onblur="roundPriceTricycle();"/>
	</td>
	
	<td width="25%"></td>
	<td width="25%"></td>

</tr>
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup id="tabRel" titleCaptionId="GPF.stuDet" bundle="${gpfLables}" >


<table id="dpndtAdv" style="display:none">
<tr><td>
	<font color="RED"> 
	<hdiits:caption captionid="GPF.advDpndtEdu" bundle="${gpfLables}"/>
	</font>
</td></tr>
</table>

<table id="relationDetailsEdu" style="display:none" width=100% >
 <tr>
 <td width="25%">
	<hdiits:caption captionid="GPF.relation" bundle="${gpfLables}"/></td>
	<c:set var="i" value="1"></c:set>
 
	
	<td width="25%">
			
			<hdiits:select default="${familyData.memberId}" name="dpndtEdu" sort="false" id="dpndtEdu" mandatory="true">
			
			<option value="Select">
			<fmt:message key="GPF.select" bundle="${gpfLables}"/>	
			</option>
			
			<c:choose>
			<c:when test="${familyData.memberId == purposeDtls.purposeAttr3}">
			<option value="0" selected="selected"><fmt:message key="GPF.Self"/></option>
			</c:when>
			
			<c:otherwise>
			<option value="0"><fmt:message key="GPF.Self"/></option>
			</c:otherwise>
			</c:choose>
			<c:forEach var="familyData" items="${familyListEdu}">
			<c:choose>
			<c:when test="${familyData.memberId == purposeDtls.purposeAttr3}">
			<option value="<c:out value="${familyData.memberId}"/>" selected="selected">
			<c:out value="${familyData.fmFirstName}"/> <c:out 	value="${familyData.fmMiddleName}"></c:out> 
			<c:out value="${familyData.fmLastName}"></c:out>,
			<c:out value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>,
			<script>var v1=Age("${familyData.fmDateOfBirth}");</script>
			<script>document.write(v1);</script>
			</option>
			
			</c:when>
			<c:otherwise>
			<option value="<c:out value="${familyData.memberId}"/>">
			<c:out value="${familyData.fmFirstName}"/> <c:out 	value="${familyData.fmMiddleName}"></c:out> 
			<c:out value="${familyData.fmLastName}"></c:out>,
			<c:out value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>,
			<script>document.write(v1);</script>
			</option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			
			</hdiits:select>
		</td>
		<c:set var="i" value="${i+1}"></c:set>
		<td width="25%"></td>
		<td width="25%"></td>
	</tr>

   
</table>

<table id="studentDet" style="display:none" width="100%"> 
 <tr>
	<td width="25%">
		<hdiits:caption captionid="GPF.instiName" bundle="${gpfLables}"/>
	</td>

	<td width="25%">
		<hdiits:text name="instituteName" caption="Institute Name" mandatory="true"  default="${purposeDtls.purposeAttr6}"/>
	</td>
	
	<td width="25%"></td>
	<td width="25%"></td>
 </tr>

 <tr>
	<td width="25%">
		<hdiits:caption captionid="GPF.class" bundle="${gpfLables}"/>
	</td>

	<td width="25%">
		 <hdiits:select name="gpfEduClass"  id="gpfEduClass" sort="false" mandatory="true">
	  		 <hdiits:option value="Select">--<fmt:message key="GPF.select"/>--</hdiits:option>
				<c:set var="lookup" value="${purposeDtls.cmnLookupMstByPurposeAttr8.lookupName}"> </c:set>
					<c:forEach var="name1" items="${gpfEduClass}">
					 <c:choose>
						
					<c:when test="${name1.lookupName == lookup}">
					 <option value="<c:out value="${name1.lookupName}"/>"  selected="selected"> 
    				 <c:out value="${name1.lookupDesc}"/></option>
					</c:when>
						
					<c:otherwise>
    				 <option  value="<c:out value="${name1.lookupName}"/>">
					 <c:out value="${name1.lookupDesc}"/></option>						
					</c:otherwise>
						
					</c:choose>				
				  </c:forEach>
	    </hdiits:select>
	</td>
	
	<td width="25%"></td>
	<td width="25%"></td>
	
 </tr>

 <tr>
	<td width="25%">
		<hdiits:caption captionid="GPF.stuType" bundle="${gpfLables}"/>
	</td>
	<td  width="25%">
		<hdiits:radio name="stuType" value="Day Scholar" mandatory="true" bundle="${gpfLables}" captionid="GPF.dayScholar"/>
		<hdiits:radio name="stuType" value="Hostelite"  mandatory="true" bundle="${gpfLables}" captionid="GPF.hostelite"/>
	</td>
	<td width="25%"></td>
	<td width="25%"></td>
 </tr>
</table>

</hdiits:fieldGroup>

<table align="center" width="100%"><tr align="center"><td align="center">
<!-- For attachment : Start-->	
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="AdvAttatchment" />
			<jsp:param name="formName"  value="frmAdvGpf" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N"/>    
			</jsp:include>
<!-- For attachment : End--> 
</td></tr></table>


 	
 	<hdiits:fieldGroup titleCaptionId="GPF.dec" bundle="${gpfLables}" >
	<table>
		<tr><td>
			<jsp:include page="/WEB-INF/jsp/hrms/common/declaration.jsp"/>
		</td></tr>
	</table>
	</hdiits:fieldGroup>
	
<table width="100%" align="center">
<tr align="center">
<td align="center">
	
	<hdiits:jsField jsFunction="chkAmt()" name="chkAmt"/>
	<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp"/>	
	
	
</td>
</tr>
</table>    

<table width="100%">
<tr align="right">
	<td>
		<font size="1">
		<hdiits:caption captionid="GPF.commonNote" bundle="${gpfLables}"/><br>
		<hdiits:caption captionid="GPF.amtFormat" bundle="${gpfLables}"/><br>
		<hdiits:caption captionid="GPF.dateFormat" bundle="${gpfLables}"/>
		</font>
	</td>
</tr>
</table>

 
	 
		 <%@ include file="../gpf/gpfPrevAdv.jsp"%>

</div>

</div>

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>



<c:forEach var="sizeEdu" items="${sizeEdu}">
<c:set var="i" value="0"></c:set>
	<script>arrMemberId["${i}"]="${sizeEdu}"</script>
<c:set var="i" value="${i+1}"></c:set>
</c:forEach>

<script>

radioDefaultSelected();
alertDisplay();
setPurpose();
document.getElementById("formSubmitButton").disabled=true;
if("${ empty familyListEdu}"=="true")
{
if("${selfEdu}"=="self")
	{
	document.frmAdvGpf.dpndtEdu.readonly=true;
	document.frmAdvGpf.dpndtEdu.disabled="true";
	}
}
</script>

<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
		
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

