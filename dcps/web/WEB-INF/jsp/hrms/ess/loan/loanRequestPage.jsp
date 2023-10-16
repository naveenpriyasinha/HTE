<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
 
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/loan/loan.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="loanTypeList" value="${resultValue.loanTypeList}"></c:set>
<c:set var="hbaTypeList" value="${resultValue.hbaTypeList}"></c:set>

<c:set var="customComplst" value="${resultValue.customComplst}"></c:set>
<c:set var="loanType" value="${resultValue.loanType}"></c:set>
<c:set var="hbaType" value="${resultValue.hbaType}"></c:set>

<c:set var="sysDt" value="${resultValue.sysDt}"></c:set>
<c:set var="eligAmt" value="${resultValue.eligAmt}"></c:set>
<c:set var="installAdv" value="${resultValue.installAdv}"></c:set>
<c:set var="interestRate" value="${resultValue.interestRate}"></c:set>
<c:set var="loanMstList" value="${resultValue.loanMstList}"></c:set>

<c:set var="vehLookup" value="${resultValue.vehLookup}"></c:set>
<c:set var="vehicleTypeCombo" value="${resultValue.vehicleTypeCombo}"></c:set>

<c:set var="doj" value="${resultValue.doj}"></c:set>
<c:set var="basicSalary" value="${resultValue.basicSalary}"></c:set>
<c:set var="validateEmpRule" value="${resultValue.validateEmpRule}"></c:set>
<c:set var="LabCaptionId" value="${resultValue.LabCaptionId}"></c:set>

<fmt:setBundle basename="resources.ess.loan.loanCaptions"	var="loanCaptions" scope="request" />
<fmt:setBundle basename="resources.ess.loan.loanAlertMessages"	var="loanAlertLables" scope="request" />

<script>

	var installAdv = "${installAdv}";
	var validateEmpRule='${validateEmpRule}';
	var vehicleTypeCombo='${vehicleTypeCombo}';
	var vehLookup='${vehLookup}';
	var messageArray=new Array();
	
	messageArray[0] = "<fmt:message bundle="${loanAlertLables}" key='loan.selLoanType'/>";
	messageArray[1] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFanCost'/>";
	messageArray[2] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroFanCost'/>";
	messageArray[3] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidFanCost'/>";
	messageArray[4] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFoodDtl'/>";
	messageArray[5] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFestType'/>";
	messageArray[6] = "<fmt:message bundle="${loanAlertLables}" key='loan.selVehType'/>";
	messageArray[7] = "<fmt:message bundle="${loanAlertLables}" key='loan.selTwoWheelType'/>";
	messageArray[8] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFourWheelType'/>";
	messageArray[9] = "<fmt:message bundle="${loanAlertLables}" key='loan.selTowWheelCost'/>";
	messageArray[10] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroTwoWheelCost'/>";
	messageArray[11] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidTwoVehCost'/>";
	messageArray[12] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFourWheelCost'/>";
	messageArray[13] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroFourWheelCost'/>";
	messageArray[14] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidFourWheelCost'/>";
	messageArray[15] = "<fmt:message bundle="${loanAlertLables}" key='loan.selHbaType'/>";
	messageArray[16] = "<fmt:message bundle="${loanAlertLables}" key='loan.chkSelLand'/>";
	messageArray[17] = "<fmt:message bundle="${loanAlertLables}" key='loan.selSiteAddr'/>";
	messageArray[18] = "<fmt:message bundle="${loanAlertLables}" key='loan.selHouseCost'/>";
	messageArray[19] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFloorArea'/>";
	messageArray[20] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroHouseCost'/>";
	messageArray[21] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroFloorArea'/>";
	messageArray[22] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidHouseCost'/>";
	messageArray[23] = "<fmt:message bundle="${loanAlertLables}" key='loan.chkSelHouseRepair'/>";
	messageArray[24] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroRepairCost'/>";
	messageArray[25] = "<fmt:message bundle="${loanAlertLables}" key='loan.selRepairCost'/>";
	messageArray[26] = "<fmt:message bundle="${loanAlertLables}" key='loan.selRepairReason'/>";
	messageArray[27] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidRepairCost'/>";
	messageArray[28] = "<fmt:message bundle="${loanAlertLables}" key='loan.selSiteArea'/>";
	messageArray[29] = "<fmt:message bundle="${loanAlertLables}" key='loan.selSiteValue'/>";
	messageArray[30] = "<fmt:message bundle="${loanAlertLables}" key='loan.selSiteAddr'/>";
	messageArray[31] = "<fmt:message bundle="${loanAlertLables}" key='loan.selSiteCost'/>";
	messageArray[32] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroSiteArea'/>";
	messageArray[33] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroSiteValue'/>";
	messageArray[34] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroSiteCost'/>";
	messageArray[35] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidSiteCost'/>";
	messageArray[36] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFlatAddr'/>";
	messageArray[37] = "<fmt:message bundle="${loanAlertLables}" key='loan.selPlinthArea'/>";
	messageArray[38] = "<fmt:message bundle="${loanAlertLables}" key='loan.selInitAmt'/>";
	messageArray[39] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFloorArea'/>";
	messageArray[40] = "<fmt:message bundle="${loanAlertLables}" key='loan.selHouseCost'/>";
	messageArray[41] = "<fmt:message bundle="${loanAlertLables}" key='loan.selDate'/>";
	messageArray[42] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroPlinthArea'/>";
	messageArray[43] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroInitAmt'/>";
	messageArray[44] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroFloorArea'/>";
	messageArray[45] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroHouseCost'/>";
	messageArray[46] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidHouseCost'/>";
	messageArray[47] = "<fmt:message bundle="${loanAlertLables}" key='loan.chkSelLand'/>";
	messageArray[48] = "<fmt:message bundle="${loanAlertLables}" key='loan.selSiteAddr'/>";
	messageArray[49] = "<fmt:message bundle="${loanAlertLables}" key='loan.selCost'/>";
	messageArray[50] = "<fmt:message bundle="${loanAlertLables}" key='loan.selFloorArea'/>";
	messageArray[51] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroCost'/>";
	messageArray[52] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroFloorArea'/>";
	messageArray[53] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidSiteCost'/>";
	messageArray[54] = "<fmt:message bundle="${loanAlertLables}" key='loan.invalidEligibleAmt'/>";
	messageArray[55] = "<fmt:message bundle="${loanAlertLables}" key='loan.selAdvAmt'/>";
	messageArray[56] = "<fmt:message bundle="${loanAlertLables}" key='loan.zeroAdvAmt'/>";
	messageArray[57] = "<fmt:message bundle="${loanAlertLables}" key='loan.chkInstallmentAmt'/>";
	messageArray[58] = "<fmt:message bundle="${loanAlertLables}" key='loan.chkValidNumber'/>";
	

</script>

<hdiits:form name="loanReqPage" validate="true" method="POST" encType="multipart/form-data" action="hrms.htm?actionFlag=loanSaveDtl">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="loan.loanAndAdvance" bundle="${loanCaptions}" /> </a></li>
	</ul>
	</div>
	
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	
	<div id="errorDiv">
		
	</div>
	
	<hdiits:fieldGroup bundle="${loanCaptions}" id="loanDtlFldGrp" titleCaptionId="loan.details" expandable="false">	 
	<table width=100%>
		<tr>
			<td width=25%>
				<hdiits:caption captionid="loan.loanType" bundle="${loanCaptions}" />
			</td>
			<td width=25%>
				<hdiits:select	name="loanType" id="loanType" sort="false" mandatory="true" onchange="changeLoadType(this)" default="${loanType}">
					<hdiits:option value="-1"> 
						<fmt:message bundle="${loanCaptions}" key="loan.select"/>
					</hdiits:option> 		
					<c:forEach var="loanTypeList" items="${loanTypeList}">
						<hdiits:option value="${loanTypeList.lookupName}" >
								${loanTypeList.lookupDesc}
							</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			
			<td width=25% id="hbaLabId" align="center">
				<hdiits:caption captionid="loan.advanceForHBA" bundle="${loanCaptions}" />
			</td>
			
			<td width=25% id="hbaTypeId">
				<hdiits:select	name="hbaType" id="hbaType" sort="false" mandatory="true" onchange="changeLoadTypeForHBA(this)" default="${hbaType}">
					<hdiits:option value="-1"> 
						<fmt:message bundle="${loanCaptions}" key="loan.select"/>
					</hdiits:option> 		
					<c:forEach var="hbaTypeList" items="${hbaTypeList}">
						<hdiits:option value="${hbaTypeList.lookupName}" >
								${hbaTypeList.lookupDesc}
							</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			
			<td width=25% id="staticDummy1"></td>
			<td width=25% id="staticDummy2"></td>
						
		</tr>
	</table>	
	</hdiits:fieldGroup>
	
	
	
	<c:if test="${not empty customComplst}">
	<hdiits:fieldGroup bundle="${loanCaptions}" id="loanAttrDtlFldGrp" titleCaptionId="${LabCaptionId}"  expandable="false">
	<table width=100%>
		<c:forEach items="${customComplst}" var="custom"> 
			
			<c:if test="${custom.startTr=='T'}">
				<tr>
			</c:if>

			
			<td width="25%"  style="display:${custom.display}" id="lab${custom.attrKey}" nowrap="nowrap">
			
			<c:if test="${custom.attrType=='Label'}">
				<hdiits:caption  bundle="${loanCaptions}" captionid="${custom.attrKey}"  id="cap${custom.attrKey}"/> 
			</c:if>
			
			<c:if test="${custom.attrType=='Combo'}">
				<c:if test="${custom.cmnLookupMst.lookupId!=300452}">
				<hdiits:select name="${custom.attrName}" id="${custom.attrName}" mandatory="${custom.mandatory}" 
				onchange="${custom.action}">
					<hdiits:option value="-1"> 
						<fmt:message bundle="${loanCaptions}" key="loan.select"/>
					</hdiits:option> 	
					<c:forEach var="childLookup" items="${custom.childLookupLst}">
						<hdiits:option value="${childLookup.lookupName}">
								${childLookup.lookupDesc}
							</hdiits:option>
					</c:forEach>
				</hdiits:select>
				</c:if>

				<c:if test="${custom.cmnLookupMst.lookupId==300452}">
					<select name="${custom.attrName}" id="${custom.attrName}" onchange="${custom.action}" class="mandatory" >
					<option value="-1"> 
						<fmt:message bundle="${loanCaptions}" key="loan.select"/>
					</option> 	
					<c:forEach var="childLookup" items="${custom.cmnHolidayMstLst}">
						<fmt:formatDate pattern="dd/MM/yyyy" value="${childLookup.hldyDt}" var="holidayDt"/>
						<option  value="${childLookup.srNo}"  title="${holidayDt}">
								${childLookup.hldyOccsn}
						</option>
					</c:forEach>
					</select><label class="mandatoryindicator">*</label>
				</c:if>

			</c:if>
			
			<c:if test="${custom.attrType=='Number'}">
				<hdiits:number  maxlength="${custom.maxlength}" name="${custom.attrName}" id="${custom.attrName}" style="display:${custom.display}" mandatory="${custom.mandatory}" onblur="${custom.action}"/>
			</c:if>
			
			<c:if test="${custom.attrType=='DateTime'}">
			<hdiits:dateTime  name="${custom.attrName}"  captionid="${custom.attrKey}" bundle="${loanCaptions}"  mandatory="${custom.mandatory}"  maxvalue="31/12/2099 00:00:00"/>
			</c:if>
			
			<c:if test="${custom.attrType=='TextArea'}">
				<hdiits:textarea maxlength="${custom.maxlength}" rows="3" cols="25" name="${custom.attrName}" id="${custom.attrName}" style="display:${custom.display}" mandatory="${custom.mandatory}" /> 
			</c:if>
			
			<c:if test="${custom.attrType=='Text'}">
				<hdiits:text maxlength="${custom.maxlength}" name="${custom.attrName}" id="${custom.attrName}" style="display:${custom.display}" mandatory="${custom.mandatory}" readonly="${custom.readonly}"/> 
			</c:if>

			<c:if test="${custom.attrType=='Radio'}">
				<input type="radio" value="Y" name="${custom.attrName}"  ><fmt:message bundle="${loanCaptions}" key="${custom.attrKey}"/>
				<input type="radio" value="N" name="${custom.attrName}" ><fmt:message bundle="${loanCaptions}" key="${custom.parentLookupName}"/>
			</c:if>
			
			</td>
		
			<c:if test="${custom.dummyTd!=0}">
				<c:forEach begin="1" end="${custom.dummyTd}" var="dum">
					<td width=25% id="dummy${dum}"></td>
				</c:forEach>
			</c:if>
			
			<c:if test="${custom.endTr=='T'}">
				</tr>
			</c:if>
			
		</c:forEach>
	</table>
	</hdiits:fieldGroup>
	</c:if>	 
	 


	<hdiits:fieldGroup bundle="${loanCaptions}" id="loanAdvDtlFldGrp" titleCaptionId="loan.Advdetails" expandable="false">
		<table width=100%>
			<tr>
				<td width=25%>	
					<hdiits:caption captionid="loan.applyDt" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>	
					<fmt:formatDate pattern="dd/MM/yyyy" value="${sysDt}" var="sysDt"/>
					<hdiits:text name="applyDt" bundle="${loanCaptions}" caption="loan.applyDt" readonly="true" default="${sysDt}"/>
				</td>

				<td width=25%>	
				</td>
				
				<td width=25%>	
				</td>
			</tr>
			
			<tr>
				<td width=25%>	
					<hdiits:caption captionid="loan.eligAmt" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>	
					<hdiits:number name="eligibleAmtTxtNo" id="eligibleAmtTxtNo" onblur="checkNo(this);roundNo(this, 0)" readonly="true" default="${eligAmt}"/>
				</td>

				<td width=25%>	
					<hdiits:caption captionid="loan.advAmt" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>
					<hdiits:number maxlength="7" name="advAmtTxtNo" id="advAmtTxtNo" mandatory="true" onblur="checkNo(this);roundNo(this, 0)" />	
				</td>
			</tr>
			
			<tr>
				<td width=25%>	
					<hdiits:caption captionid="loan.installAdv" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>	
					<hdiits:number maxlength="3" name="installAdvTxtNo" id="installAdvTxtNo" mandatory="true" onblur="checkNo(this);roundNo(this, 0)" default="${installAdv}"/>
				</td>

				<td width=25%>	
					<hdiits:caption captionid="loan.interestRt" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>
					<hdiits:number default="${interestRate}" readonly="true" maxlength="5" maxvalue="99" name="interestRtTxtNo" id="interestRtTxtNo" onblur="checkNo(this);roundNo(this, 2)"/>	
				</td>
			</tr>
			
			<tr>
				<td width=25% style="display: none;">	
					<hdiits:caption captionid="loan.installInterest" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25% style="display: none;">	
					<hdiits:number maxlength="2"  readonly="true" name="installInterestTxtNo" id="installInterestTxtNo" mandatory="true" onblur="checkNo(this);roundNo(this, 0)" default="0"/>
				</td>

				<td width=25%>	
					<hdiits:caption captionid="loan.remarks" bundle="${loanCaptions}"></hdiits:caption>
				</td>
				
				<td width=25%>
					<hdiits:textarea  rows="3" cols="25"  name="remarksTxtAr" id="remarksTxtAr" bundle="${loanCaptions}" captionid="loan.remarks">
					</hdiits:textarea>	
				</td>
			</tr>
		</table>
	</hdiits:fieldGroup>


<table width="100%" align="center">	
	<tr align="center">
		<td align="center">
			<a onclick="openEMICalc()" href="#"><fmt:message key="loan.emiCalClk" bundle="${loanCaptions}" /></a>	
		</td>
	</tr>
</table>

<!-- For attachment : Start-->	
<table align="center" width="100%">
	<tr align="center">
		<td align="center">
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="loanAttachment" />
			<jsp:param name="formName"  value="loanReqPage" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N"/>    
			</jsp:include>
		</td>
	</tr>
</table>
<!-- For attachment : End--> 


<hdiits:fieldGroup bundle="${loanCaptions}" titleCaptionId="loan.prevAdvDtl" expandable="true" id="prevLoanDtl">
	<c:set var="srNo" value="${1}" />
	<display:table list="${loanMstList}" id="prevLoanLst" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1">	
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.SrNo" headerClass="datatableheader" sortable="true" >${srNo}</display:column>
			<c:set var="srNo" value="${srNo+1}" />
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.loanType" headerClass="datatableheader" sortable="true" >${prevLoanLst.cmnLookupMst.lookupDesc}</display:column>	    
			<fmt:formatDate pattern="dd/MM/yyyy" value="${prevLoanLst.createdDate}" var="createDt"/>
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.applDt" headerClass="datatableheader" sortable="true" >${createDt}</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.reqAmt" headerClass="datatableheader" sortable="true" >${prevLoanLst.advAmt}</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.sancAmt" headerClass="datatableheader" sortable="true" >
				
				<c:if test="${prevLoanLst.approveFlag!='Y'}">
					N/A
				</c:if>
				<c:if test="${prevLoanLst.approveFlag=='Y'}">
					${prevLoanLst.amtSanc}
				</c:if>
			
				
			</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.totAmtRepaid" headerClass="datatableheader" sortable="true" >N/A</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.amtDue" headerClass="datatableheader" sortable="true" >N/A</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.dtLastInst" headerClass="datatableheader" sortable="true" >N/A</display:column>	    					
			<display:column style="text-align: center;" class="tablecelltext" titleKey="loan.appStatus" headerClass="datatableheader" sortable="true" >
				
				<c:if test="${prevLoanLst.approveFlag=='P'}">
				<fmt:message key="loan.pending" bundle="${loanCaptions}"></fmt:message>
				</c:if>
				
				<c:if test="${prevLoanLst.approveFlag=='Y'}">
				<fmt:message key="loan.approve" bundle="${loanCaptions}"></fmt:message>
				</c:if>
				
				<c:if test="${prevLoanLst.approveFlag=='N'}">
				<fmt:message key="loan.reject" bundle="${loanCaptions}"></fmt:message>
				</c:if>
				
				<c:if test="${prevLoanLst.approveFlag=='C'}">
				<fmt:message key="loan.cancel" bundle="${loanCaptions}"></fmt:message>
				</c:if>
			</display:column>	    					
																		
		<display:footer media="html"></display:footer>		
	</display:table>			
</hdiits:fieldGroup>				
	</div>

<table width="100%" align="center">
	<tr align="center">
		<td align="center">
			<hdiits:jsField jsFunction="frmValidate()" name="frmValidate"/>
			<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp"/>	
		</td>
	</tr>
</table> 
	
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab");
		callOnLoadReq();
	</script>
	
<!--Hidden Fields-->
<fmt:formatDate value="${doj}" var="dojFormat" pattern="dd/MM/yyyy"/>
<hdiits:hidden id="basicSalary" name="basicSalary" default="${basicSalary}"/>
<hdiits:hidden id="doj" name="doj" default="${dojFormat}"/>
	
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="" />

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script type="text/javascript">

var loanTypeDef='';
var hbaTypeDef='';

loanTypeDef=document.getElementById("loanType").value;
hbaTypeDef=document.getElementById("hbaType").value;

if(document.getElementById('loanType').value=='hrms_hbaLoan')
	{	
		document.getElementById('hbaTypeId').style.display='block';
		document.getElementById('hbaLabId').style.display='block';
		document.getElementById('staticDummy1').style.display='none';
		document.getElementById('staticDummy2').style.display='none';
			
	}

	else if(document.getElementById('loanType').value=='hrms_mca')
	{	
		if(validateEmpRule==1)			
		{
			document.getElementById('errorDiv').innerHTML="<font color='RED'>You are not eligible to take loan for Car</font>";
			document.getElementById("formSubmitButton").disabled=true;
		}
		else if(validateEmpRule==2)			
		{
			document.getElementById('errorDiv').innerHTML="<font color='RED'>You are not eligible to take loan for Jeep</font>";		
			document.getElementById("formSubmitButton").disabled=true;
		}
		else if(validateEmpRule==3)			
		{
			document.getElementById('errorDiv').innerHTML="<font color='RED'>You are not eligible to take loan for Scooter</font>";		
			document.getElementById("formSubmitButton").disabled=true;
		}
		else if(validateEmpRule==4)			
		{
			document.getElementById('errorDiv').innerHTML="<font color='RED'>You are not eligible to take loan for Moped</font>";		
			document.getElementById("formSubmitButton").disabled=true;
		}
		else if(validateEmpRule==5)			
		{
			document.getElementById('errorDiv').innerHTML="<font color='RED'>You are not eligible to take loan for Cycle</font>";	
			document.getElementById("formSubmitButton").disabled=true;
		}
		else if(validateEmpRule==10)			
		{
			document.getElementById('errorDiv').innerHTML='';	
			document.getElementById("formSubmitButton").disabled=false;		
		}
		if(document.getElementById('vehicleTypeCombo')!=null)
		{
			if(vehicleTypeCombo!='')
			{
				document.getElementById('vehicleTypeCombo').value=vehicleTypeCombo;
			} 	
		}
		if(document.getElementById('fourWheelCombo')!=null)
		{
			if(vehLookup!='')
			{
				document.getElementById('fourWheelCombo').value=vehLookup;
			} 
		}
		
		if(document.getElementById('twoWheelCombo')!=null)
		{
			if(vehLookup!='')
			{
				document.getElementById('twoWheelCombo').value=vehLookup;
			} 
		}
			
		document.getElementById('hbaTypeId').style.display='none';
		document.getElementById('hbaLabId').style.display='none';
		document.getElementById('staticDummy1').style.display='block';
		document.getElementById('staticDummy2').style.display='block';
	}
	else
	{
		document.getElementById('hbaTypeId').style.display='none';
		document.getElementById('hbaLabId').style.display='none';
		document.getElementById('staticDummy1').style.display='block';
		document.getElementById('staticDummy2').style.display='block';
	}	
		
</script>
	