<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/ess/gpf/gpfWith.js"></script>

<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="gpfWithPurpose" value="${resValue.gpfWithPurpose}"></c:set>
<c:set var="gpfTwoWheeler" value="${resValue.gpfTwoWheeler}"></c:set>
<c:set var="hrGpfBalanceDtls" value="${resValue.hrGpfBalanceDtls}"></c:set>
<c:set var="date" value="${resValue.todayDate}"></c:set>
<c:set var="gpfHBA" value="${resValue.gpfHBA}"></c:set>
<c:set var="gpfEduClass" value="${resValue.GPFEduClass}"></c:set>
<c:set var="countFourWheeler" value="${resValue.countFourWheeler}"></c:set>
<c:set var="countRepairMotor" value="${resValue.countRepairMotor}"></c:set>
<c:set var="countPilgrimage" value="${resValue.countPilgrimage}"></c:set>
<c:set var="countBusinessPremises" value="${resValue.countBusinessPremises}"></c:set>
<c:set var="familyList" value="${resValue.familyList}"></c:set>
<c:set var="familyListEdu" value="${resValue.familyListEdu}"></c:set>
<c:set var="valid" value="${resValue.valid}"></c:set> 
<c:set var="validYear" value="${resValue.validYear}"></c:set> 
<c:set var="withDtls" value="${resValue.withDtls}"></c:set> 
<c:set var="purposeDtls" value="${resValue.purposeDtls}"></c:set> 
<c:set var="phyChallenged" value="${resValue.phyChallenged}"></c:set>
<c:set var="sizeEdu" value="${resValue.sizeEdu}"></c:set>
<c:set var="countWith" value="${resValue.countWith}"></c:set>
<c:set var="withHistory" value="${resValue.withHistory}"></c:set>
<c:set var="finYrStart" value="${resValue.finYrStart}"></c:set>
<c:set var="finalWith" value="${resValue.finalWith}"></c:set>
<c:set var="selfEdu" value="${resValue.self}"></c:set>
<c:set var="pendingReqSub" value="${resValue.pendingReqSub}"></c:set>
<c:set var="pendingReqWithFinal" value="${resValue.pendingReqWithFinal}"></c:set>
<c:set var="approveWithReqFinal" value="${resValue.approveWithReqFinal}"></c:set>
<c:set var="pendingAdvRefund" value="${resValue.pendingAdvRefund}"></c:set> 

<script>
var arrMemberId=new Array();
var countPilgrimage="${countPilgrimage}";
var countRepairMotor="${countRepairMotor}";
var countFourWheeler="${countFourWheeler}";
var phyChallenged="${phyChallenged}";
var approveWithReqFinal="${approveWithReqFinal}";
var pendingReqWithFinal="${pendingReqWithFinal}";
var countWithd="${countWith}";
var validYear="${validYear}";
var valid="${valid}";
var netbalance="${hrGpfBalanceDtls.netBalance}";
var isEmptyWithHistory="${empty withHistory}";
var pendingAdvRefund="${pendingAdvRefund}";
var pendingReqSub="${pendingReqSub}";
var isWithHstEmpty="${empty withHistory}";

function Age(birthDate)
{					
	if(birthDate!=null)
	{	
		var splitDate=birthDate.split("-");				
		var bday=parseInt(splitDate[1]);
		var bmo=(parseInt(splitDate[2])-1);
		var byr=parseInt(splitDate[0]);				
		var byr;
		var age;
						
		<fmt:formatDate var="datePrint"  pattern="dd/MM/yyyy" value="${date}" type="date"/>
		var today="${datePrint}";
		var tDate, tMon, tYear;
		tYear=(String("${datePrint}").substring(6,10))*1;
		tMon=(String("${datePrint}").substring(3,5))*1;
		tDate=(String("${datePrint}").substring(0,2))*1;
		
		if((tMon > bmo)||(tMon==bmo & tDate>=bday)) {age=byr}				
		else  {age=byr+1}
				
		return (tYear-age);	
							
	}
}



function radioDefaultSelected()
{
document.frmGPFWithdrawal.withType[0].checked = true;
//Part Withdrawal Selected by Default
	if("${withDtls.special}"=="<fmt:message key="GPF.Y"/>")
	{
		document.frmGPFWithdrawal.splCase[0].checked = true;
	}
	else if("${withDtls.special}"=="<fmt:message key="GPF.N"/>")
	{
		document.frmGPFWithdrawal.splCase[1].checked = true;
	}
	
	if("${withDtls.withdrawalType}"=="Part")
	{
		document.frmGPFWithdrawal.withType[0].checked = true;
	}
	else if("${withDtls.withdrawalType}"=="Final")
	{
		document.frmGPFWithdrawal.withType[1].checked = true;
	}
		
	
	if("${purposeDtls.purposeAttr4}"=="<fmt:message key="GPF.dayScholar"/>")
	{
		document.frmGPFWithdrawal.stuType[0].checked = true;
	}
	else if("${purposeDtls.purposeAttr4}"=="<fmt:message key="GPF.hostelite"/>")
	{
		document.frmGPFWithdrawal.stuType[1].checked = true;
	}
	var memberId="${purposeDtls.purposeAttr3}";
	var rads = document.frmGPFWithdrawal.dpndtEdu; 
	var rads = document.frmGPFWithdrawal.dpndt; 
	
}




</script>

<hdiits:form name="frmGPFWithdrawal" validate="true" method="POST"
	action="hrms.htm?actionFlag=gpfWithData" encType="multipart/form-data">
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
 
 <hdiits:fieldGroup titleCaptionId="GPF.withDet"  bundle="${gpfLables}" > 
<table>		
	<tr> 
		<td>
		<p id="case1" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.with1" bundle="${gpfLables}"/>
			</font>
		</p>
		
		<p id="case2" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.with2" bundle="${gpfLables}"/>
			</font>
		</p>

		<p id="case3" style="display:none">
		<font color="RED">
		<hdiits:caption captionid="GPF.with3" bundle="${gpfLables}"/>
		</font>
		</p>
		
		<p id="case4" style="display:none">
		<font color="RED">
		<hdiits:caption captionid="GPF.with4" bundle="${gpfLables}"/>
		</font>
		</p>
		
		<p id="case5" style="display:none">
		<font color="RED">
		<hdiits:caption captionid="GPF.with5" bundle="${gpfLables}"/>
		</font>
		</p>
		
		
		<p id="reqPending" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.reqPending" bundle="${gpfLables}"/>
			</font>
		</p>

		<p id="withReqPending" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.partFinalReqPending" bundle="${gpfLables}"/>
			</font>
		</p>

		<p id="finalWithApprovd" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.finalWithDone" bundle="${gpfLables}"/>
			</font>
		</p>

		<p id="advRefundPending" style="display:none">
			<font color="RED">
			<hdiits:caption captionid="GPF.prevAdvPending" bundle="${gpfLables}"/>
			</font>
		</p>
		</td>
	
	</tr>
		
	<tr>
		<td>
			<p id="casev0" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.withLimit" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev1" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.withMotorInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev2" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.withScooterInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev3" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.withMopedInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev4" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.with10thInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev5" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.with11thInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev6" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.with12thInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev7" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.withTriInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev8" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.elecInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev11" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.repairInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev9" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.withFourWheelerInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev12" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.withHighEduInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev13" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.withHBA1Invalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev14" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.withHBA2Invalid" bundle="${gpfLables}"/>
				</font>
			</p> 
			<p id="casev15" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.withHBA3Invalid" bundle="${gpfLables}"/>
				</font>
			</p>
			<p id="casev16" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.super" bundle="${gpfLables}"/>
				</font>
			</p>	
			<p id="enterValidNo" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.enterValidNo" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="amtZeroInvalid" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.amtZeroInvalid" bundle="${gpfLables}"/>
				</font>
			</p>
		
			<p id="noSplChar" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.noSplChar" bundle="${gpfLables}"/>
				</font>
			</p>	
		
			<p id="noSplCharnoDigit" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.noSplCharnoDigit" bundle="${gpfLables}"/>
				</font>
			</p>
		
			<p id="selectDependent" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.selectDependent" bundle="${gpfLables}"/>
				</font>
			</p>	
		
			<p id="selectWithSpl" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.selectWithSpl" bundle="${gpfLables}"/>
				</font>
			</p>
	
			<p id="amtWithMsg" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.amtWith" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="selPurp" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.selPurp" bundle="${gpfLables}"/>
				</font>
			</p>
		
			<p id="accOffName" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.accOffName" bundle="${gpfLables}"/>
				</font>
			</p>
		
			<p id="selType" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.selType" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="noDpndt" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.noDpndt" bundle="${gpfLables}"/>
				</font>
			</p>
		
			<p id="selClass" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.selClass" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="enterInsti" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.enterInsti" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="selTwoWheeler" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.selTwoWheeler" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="twoWheelerPrice" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.twoWheelerPrice" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="amtgtPrice" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.amtgtPrice" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="phyChallenged" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.phyChallenged" bundle="${gpfLables}"/>
				</font>
			</p>
	
			<p id="enterPriceTri" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.enterPriceTri" bundle="${gpfLables}"/>
				</font>
			</p>	
	
			<p id="withFouronce" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.withFouronce" bundle="${gpfLables}"/>
				</font>
			</p>
	
			<p id="enterPriceFourWheeler" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.enterPriceFourWheeler" bundle="${gpfLables}"/>
				</font>
			</p>
		
			<p id="withMotorOnce" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.withMotorOnce" bundle="${gpfLables}"/>
				</font>
			</p>

			<p id="enterRepairPrice" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.enterRepairPrice" bundle="${gpfLables}"/>
				</font>
			</p>
	
			<p id="pricegtWith" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.pricegtWith" bundle="${gpfLables}"/>
				</font>
			</p>
	
			<p id="enterElecPrice" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.enterElecPrice" bundle="${gpfLables}"/>
				</font>
			</p>
	
			<p id="ecgtWith" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.ecgtWith" bundle="${gpfLables}"/>
				</font>
			</p>
			
			<p id="enterDtlPurp" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.enterDtlPurp" bundle="${gpfLables}"/>
				</font>
			</p>
	
			<p id="withPilgOnce" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.withPilgOnce" bundle="${gpfLables}"/>
				</font>
			</p>
	
			<p id="withFinal" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.withFinal" bundle="${gpfLables}"/>
				</font>
			</p>	
	
			<p id="splcharInvalid" style="display:none"> 
				<font color="RED">
				<hdiits:caption captionid="GPF.splcharInvalid" bundle="${gpfLables}"/>
				</font>
			</p>	
	
			<p id="eduDependOnce" style="display:none">
				<font color="RED">
				<hdiits:caption captionid="GPF.eduDependOnce" bundle="${gpfLables}"/>
				</font> 
			</p>		
					
		</td>
	</tr>
		
</table>

<table width="100%">
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.splCase" bundle="${gpfLables}"/>
		</td>
		
		<td width=25%>
			<hdiits:radio name="splCase" value="Y" captionid="GPF.yes" mandatory="true" bundle="${gpfLables}"/>
			<hdiits:radio name="splCase" value="N" captionid="GPF.no" mandatory="true" bundle="${gpfLables}"/>
		</td>
		
		<td width=25%>
			<hdiits:caption captionid="GPF.WithType" bundle="${gpfLables}"/>
		</td>
		
		<td width=25%>
			<hdiits:radio name="withType" value="Part" captionid="GPF.part" onclick="partWithSelect();" bundle="${gpfLables}"/>
			<hdiits:radio name="withType" value="Final" captionid="GPF.final" onclick="finalWithSelect();" bundle="${gpfLables}"/>
		</td>
	</tr>
	
	<tr>
		<td width=25%>
			<hdiits:caption captionid="GPF.appDate" bundle="${gpfLables}"/>
		</td>
		
		<td width=25%>
			<fmt:formatDate pattern="dd/MM/yyyy" value="${date}" var="today"/>
			<input type="text" name="appDateSys" readonly="true" value="${today}"  style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;"/>
		</td>
		
		<td width=25%>
			<hdiits:caption captionid="GPF.amtToWith" bundle="${gpfLables}"/>
			(<hdiits:caption captionid="GPF.INR" bundle="${gpfLables}"/>)
		</td>
		
		<td width=25%>
			<hdiits:number name="amtWith" id="amtWith" mandatory="true" caption="Withdrawal Amount" onblur="roundWith();" default="${withDtls.amtWithdrawn}" maxlength="10"/>
		</td>
	</tr>
 
 <tr>
 <td width=25%>
 <hdiits:caption captionid="GPF.purposeWith" bundle="${gpfLables}"/>
 </td>
 
 <td width=25%>
		<hdiits:select name="gpfPurpose" mandatory="true" sort="false" onchange="display();">
	  		 
				<hdiits:option value="Select"><fmt:message key="GPF.select"/></hdiits:option>
				
					<c:forEach var="name" items="${gpfWithPurpose}">
						
						<c:choose>
						 <c:when test="${name.lookupName == withDtls.cmnLookupMstByPurpose.lookupName}">
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

	<td width=25% style="display:none">
 		<hdiits:caption captionid="GPF.accOff" bundle="${gpfLables}"/>
 	</td>
 	
	<td width=25% style="display:none">
		<hdiits:text name="accOff" mandatory="true" id="accOff" default="${withDtls.accountOfficer}" onblur="onlyChars(this);" maxlength="15"/>
	</td>
 </tr>
</table> 

<table id="other" style="display:none" width="100%"> 
	<tr>
	<td width="25%">
 		<hdiits:caption captionid="GPF.oth" bundle="${gpfLables}"/>
	</td>
	<td width="25%">
		<hdiits:textarea name="others" default="${withDtls.other}" mandatory="true" maxlength="50"/>
	</td>
	<td width="25%"></td>
	<td width="25%"></td>
	</tr>
</table>
 
 </hdiits:fieldGroup>

<hdiits:fieldGroup titleCaptionId="GPF.ecLabel"  bundle="${gpfLables}" id="ecLabel"> 

 
<table id="electItem" style="display:none" width="100%"> 
 <tr>
 	<td width=25%> 
 		<hdiits:caption captionid="GPF.withCostEc" bundle="${gpfLables}"/>
	</td>
	<td width=25%>
		<hdiits:number name="costOfElect" mandatory="true" onblur="roundCostEc();" default="${purposeDtls.purposeAttr7}" maxlength="10"/>
	</td>
	<td width=25%></td>
	<td width=25%></td>
 </tr>
</table>
</hdiits:fieldGroup>
  
  
<hdiits:fieldGroup titleCaptionId="GPF.repairLabel"  bundle="${gpfLables}" id="repairLabel">


 <table id="repairCar" style="display:none" width="100%"> 
	<tr>
	<td width="25%">
 		<hdiits:caption captionid="GPF.withPriceRepair" bundle="${gpfLables}"/>
	</td>
	<td width="25%">
		<hdiits:number name="priceOfRepair" mandatory="true" onblur="roundPriceRepair();" default="${purposeDtls.purposeAttr7}" maxlength="10"/>
	</td>
	<td width="25%"></td>
	<td width="25%"></td>
 	</tr>
 </table>
</hdiits:fieldGroup>


<hdiits:fieldGroup titleCaptionId="GPF.twoWheelerLabel"  bundle="${gpfLables}" id="twoWheelerLabel"> 

<table id="twoWheelerSelect" style="display:none" width="100%">
	<tr>
		<td width="25%">
			<hdiits:caption captionid="GPF.select2Wheeler" bundle="${gpfLables}"/>
		</td>
	
	<td width="25%">
	 	<hdiits:select name="gpfTwoWheeler" id="gpfTwoWheeler" sort="false" mandatory="true">
	  		 
		<hdiits:option value="Select"><fmt:message key="GPF.select"/></hdiits:option>
		<c:set var="lookup" value="${withDtls.cmnLookupMstByPurposeId.lookupName}"> </c:set>
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
	</td>
	
	<td width="25%"></td>
	<td width="25%"></td>
	
	</tr>
</table>
</hdiits:fieldGroup>


<hdiits:fieldGroup titleCaptionId="GPF.vehicleLabel"  bundle="${gpfLables}" id="vehicleLabel"> 
<table id="priceOfVehicle" style="display:none" width="100%"> 
	<tr>
	<td width="25%">
 		<hdiits:caption captionid="GPF.priceVehicle" bundle="${gpfLables}"/>
	</td>
	<td width="25%">
		<hdiits:number name="priceVehicle" mandatory="true" id="priceVehicle" caption="priceVehicle" default="${purposeDtls.purposeAttr7}" onblur="roundPriceVehicle();" maxlength="10"/>
	</td>
	
	<td width="25%"> </td>
	<td width="25%"> </td>
	</tr>
</table>
</hdiits:fieldGroup>


<hdiits:fieldGroup titleCaptionId="GPF.hbaLabel"  bundle="${gpfLables}" id="hbaLabel">
 
<table id="gpfHBA" style="display:none" width="100%">
	<tr>
	<td width="25%">
		<hdiits:caption captionid="GPF.purpose" bundle="${gpfLables}"/>
	</td>
	
	<td width="25%">
  	<hdiits:select name="gpfHBASelect" id="gpfHBASelect" sort="false" mandatory="true">
	<hdiits:option value="Select"><fmt:message key="GPF.select"/></hdiits:option>
	<c:set var="lookup" value="${withDtls.cmnLookupMstByPurposeId.lookupName}"> </c:set>
		<c:forEach var="name1" items="${gpfHBA}">
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
</table>
</hdiits:fieldGroup>


<hdiits:fieldGroup titleCaptionId="GPF.studentLabel"  bundle="${gpfLables}" id="studentLabel">

<table id="dpndtAdv" style="display:none" width=100%>
 <tr>
	<td>
	<font color="RED">
		<hdiits:caption captionid="GPF.withDpndtEdu" bundle="${gpfLables}"/>
	</font>
	</td>
 </tr>
</table>

<table id="relationDetailsEdu" style="display:none"  width="100%">
 <tr>
 <td width=25%>
	<hdiits:caption captionid="GPF.relation" bundle="${gpfLables}"/>
 </td>
		
		<td width="25%">
		
			<hdiits:select default="${familyData.memberId}" name="dpndtEdu" id="dpndtEdu" mandatory="true" sort="false">
			<option value="Select"> <fmt:message key="GPF.select"/></option>
			<option value="0">
			<fmt:message key="GPF.Self"/>
			</option>
			<c:forEach var="familyData" items="${familyListEdu}">
			<c:choose>
			<c:when test="${familyData.memberId==purposeDtls.purposeAttr3}">
			<option value="<c:out value="${familyData.memberId}"/>" selected="selected">
			<c:out value="${familyData.fmFirstName}"/> <c:out 	value="${familyData.fmMiddleName}"></c:out> 
			<c:out value="${familyData.fmLastName}"></c:out>,
			<c:out value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>,
			<script>
			var v1=Age("${familyData.fmDateOfBirth}"); document.write(v1);</script>
			</option>
			</c:when>
			
			<c:otherwise>
			<option value="<c:out value="${familyData.memberId}"/>">
			<c:out value="${familyData.fmFirstName}"/> <c:out 	value="${familyData.fmMiddleName}"></c:out> 
			<c:out value="${familyData.fmLastName}"></c:out>,
			<c:out value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>,
			<script>var v1=Age("${familyData.fmDateOfBirth}");document.write(v1);</script>
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

<table id="studentDet" style="display:none" width=100%> 
 <tr>
	<td width=25%> 
		<hdiits:caption captionid="GPF.instiName" bundle="${gpfLables}"/>
	</td>

	<td width="25%">
		<hdiits:text name="instituteName" caption="Institute Name" mandatory="true" default="${purposeDtls.purposeAttr6}" maxlength="15"/>
	</td>

	<td width=25%> 	</td>

	<td width=25%> 	</td>

 </tr>

 <tr>
	<td width=25%>
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
	<td width=25%></td>
	<td width=25%></td>
 </tr>

 <tr>
	<td width=25%>
		<hdiits:caption captionid="GPF.stuType" bundle="${gpfLables}"/>
	</td>
	<td width="25%">
		<hdiits:radio name="stuType" value="Day Scholar" mandatory="true" bundle="${gpfLables}" captionid="GPF.dayScholar"/>
		<hdiits:radio name="stuType" value="Hostelite" mandatory="true" bundle="${gpfLables}" captionid="GPF.hostelite"/>
	</td>
	<td width=25%>	</td>
	<td width=25%>	</td>
 </tr>
</table>

</hdiits:fieldGroup>


<hdiits:fieldGroup titleCaptionId="GPF.relationLabel"  bundle="${gpfLables}" id="relationLabel">


<table id="relationDetails" style="display:none" width=100%>
 <tr>
	 <td width="25%">
		<hdiits:caption captionid="GPF.relation" bundle="${gpfLables}"/>
	</td>
	
	<c:set var="i" value="1"></c:set>
		<td width="25%">
		
			<hdiits:select default="${familyData.memberId}" name="dpndt" id="dpndt" mandatory="true" sort="false">
			<option value="Select"> <fmt:message key="GPF.select"/></option>
			
			<c:choose>
			<c:when test="${familyData.memberId==purposeDtls.purposeAttr3}">
			<option value="0" selected="selected"><fmt:message key="GPF.Self"/></option>			
			</c:when>
			
			<c:otherwise>
			<option value="0"><fmt:message key="GPF.Self"/></option>
			</c:otherwise>
			</c:choose>
			
			<c:forEach var="familyData" items="${familyList}">
			<c:choose>
			<c:when test="${familyData.memberId==purposeDtls.purposeAttr3}">
			<option value="<c:out value="${familyData.memberId}"/>" selected="selected">
			<c:out value="${familyData.fmFirstName}"/> <c:out 	value="${familyData.fmMiddleName}"></c:out> 
			<c:out value="${familyData.fmLastName}"></c:out>,
			<c:out value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>,
			<script>var v1=Age("${familyData.fmDateOfBirth}"); </script>
			<script>document.write(v1);</script>
			</option>
			</c:when>
			
			<c:otherwise>
			<option value="<c:out value="${familyData.memberId}"/>">
			<c:out value="${familyData.fmFirstName}"/> <c:out 	value="${familyData.fmMiddleName}"></c:out> 
			<c:out value="${familyData.fmLastName}"></c:out>,
			<c:out value="${familyData.cmnLookupMstByFmRelation.lookupDesc}"></c:out>,
			<script>var v1=Age("${familyData.fmDateOfBirth}"); document.write(v1);</script>
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
 </hdiits:fieldGroup>

<table align="center" width="100%"><tr align="center"><td align="center">
<!-- For attachment : Start-->	
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="WithAttatchment" />
			<jsp:param name="formName"  value="frmGPFWithdrawal" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N"/>    
			</jsp:include>
<!-- For attachment : End--> 
</td></tr></table>


<!--  Declaration Removed 25th March 08 -->

<table width="100%" id="withsubmit"> 

<tr>
	<td>
		<center>
		<hdiits:radio name="agree" value="Yes" id="agree" onclick="activateSave();" style="display:none"/>
		<hdiits:radio name="agree" value="No" id="agree" onclick="deactivateSave();" style="display:none"/>
		</center>
	</td>
</tr>

<tr><td></td></tr>

</table>

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
		<hdiits:caption captionid="GPF.commonNote" bundle="${gpfLables}"/>
		<br>
		<hdiits:caption captionid="GPF.amtFormat" bundle="${gpfLables}"/>
		<br>
		<hdiits:caption captionid="GPF.dateFormat" bundle="${gpfLables}"/>
		</font>
	</td>
</tr>
</table>


 <%@ include file="../gpf/gpfPrevWith.jsp"%>
 
 </div>
 
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
 

<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
		
</hdiits:form>


<script>
	Age("${date}");
	display();
	radioDefaultSelected();
	ruleOutput();
	chkFinalWith();
	
	

	function chkFinalWith()
	{

	var finalWith2="${finalWith}";
	if((finalWith2*1)>0)
		{
		flag=-1;
		document.getElementById("withsubmit").style.display='none';
		document.getElementById("withFinal").style.display='';		
		}
	
	}
	
	if("${ empty familyListEdu}"=="true")
	{
		if("${selfEdu}"=="self")
		{
			document.frmGPFWithdrawal.dpndtEdu.readonly=true;
			document.frmGPFWithdrawal.dpndtEdu.disabled="true";
		}
	}

activateSave();
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

