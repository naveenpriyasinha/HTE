<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/ess/gpf/gpfSub.js"></script>

<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/><link rel="stylesheet" href="<c:url value="/themes/hdiits/tabcontent.css"/>" type="text/css" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="actionList" value="${resValue.actionList}" > </c:set>

<c:set var="gpffunc" value="${resValue.GPFFunc}"></c:set>
<c:set var="subDtls" value="${resValue.subDtls}"></c:set>
<c:set var="empPay" value="${resValue.empPay}"></c:set>
<c:set var="flagIncrease" value="${resValue.flagIncrease}"></c:set>
<c:set var="flagDecrease" value="${resValue.flagDecrease}"></c:set>
<c:set var="pendingReqSub" value="${resValue.pendingReqSub}"></c:set>
<c:set var="hrGpfBalanceDtls" value="${resValue.hrGpfBalanceDtls}"></c:set>
<c:set var="date" value="${resValue.todayDate}"></c:set>
<c:set var="finYrStart" value="${resValue.finYrStart}"></c:set>
<c:set var="pendingReqWithFinal" value="${resValue.pendingReqWithFinal}"></c:set>
<c:set var="approveWithReqFinal" value="${resValue.approveWithReqFinal}"></c:set>
<c:set var="pendingAdvRefund" value="${resValue.pendingAdvRefund}"></c:set> 
<c:set var="grossPay" value="${resValue.grossPay}"></c:set> 
<c:set var="validateRuleEngine" value="${resValue.validateRuleEngine}"></c:set>  
<c:set var="newSubReq" value="${resValue.newSubReq}"></c:set> 
<c:set var="oldAmt" value="${resValue.oldAmt}"></c:set>

<script>
<fmt:formatDate pattern="dd/MM/yyyy" value="${date}" var="today"/>
var todayDate="${today}";
var pendingReqWithFinal="${pendingReqWithFinal}";
var approveWithReqFinal="${approveWithReqFinal}";
var pendingReqSub="${pendingReqSub}"
var pendingAdvRefund="${pendingAdvRefund}";
var oldAmt="${oldAmt}";
var salary="${empPay}";
var flagIncrease="${flagIncrease}";
var flagDecrease="${flagDecrease}";


  
function setDefaultValues()
{
	if("${validateRuleEngine}"==1)
	{
		document.getElementById("commonNote").style.display='';
		document.getElementById("lessThanTenPercent").style.display='';
		document.getElementById("newAmtgrossPay").style.display='';
	}
	
	document.getElementById("newSubAmt").value="${newSubReq.newSubscrptionAmt}";
	<fmt:formatDate pattern="dd/MM/yyyy" value="${newSubReq.startDate}" var="effDate"/>
	document.getElementById("effDate").value="${effDate}";
	
}
		
function setOldSubsAmt()
{
	var roundOldAmt=decimalPoint(${subDtls[0].subRate});
	document.getElementById("oldSubsAmt").value=roundOldAmt;
	document.getElementById("oldSubsRate").value=decimalPoint(calcCurrRate(${subDtls[0].subRate}));
	document.frmSub.radSubRateAmt[1].checked=true;
}


</script>



<hdiits:form name="frmSub" validate="true" method="POST"
	action="hrms.htm?actionFlag=gpfSubDtls" encType="multipart/form-data">

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
<table width="100%" style="display: none;">

	<tr>
		<td>
			<br>
		</td>
	</tr>

	<tr bgcolor="#386CB7" >
		<td class="fieldLabel" colspan="10" width="100%" align="center">
			<font color="#ffffff">
				<strong>
					<u>
						<hdiits:caption captionid="GPF.gpfReq" bundle="${gpfLables}"/>
					</u>
				</strong>
			</font>
		</td>
	</tr>
</table>
 

<hdiits:fieldGroup titleCaptionId="GPF.changeSubDet" bundle="${gpfLables}" >

<table>
<tr>
	<td>
		<p id="commonNote" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.commonNote" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="lessThanTenPercent" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.lessThanTenPercent" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="newAmtgrossPay" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.subsAmtGrossSal" bundle="${gpfLables}"/>
			</font>
		</p>			
		<p id="greaterThanSalary" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.greaterThanSalary" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="newSubZero" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.newSubZero" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="effDateNotNull" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.effDateNotNull" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="validSubAmt" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.validSubAmt" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="newAmtInvalid" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.newAmtInvalid" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="effDateInvalid" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.effDateInvalid" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="currFinYearInvalid" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.currFinYearInvalid" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="reqPending" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.reqPending" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="increaseInvalid" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.increaseInvalid" bundle="${gpfLables}"/>
			</font>
		</p>
		<p id="decreaseInvalid" style="display:none">
			<font color="RED">
				<hdiits:caption captionid="GPF.decreaseInvalid" bundle="${gpfLables}"/>
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
	<hdiits:hidden name="empPay" default="${empPay}"/>
</tr>
</table>


<table width="100%">
	
	<tr style="display: none;"> 
		<td width="25%">
			<hdiits:caption captionid="GPF.subRateAmt" bundle="${gpfLables}"/>
		</td>
		
		<td width="25%">
			<hdiits:radio name="radSubRateAmt" value="Amt" id="radSubRateAmt" onclick="showSub();" bundle="${gpfLables}" captionid="GPF.subAmt"/>
			<hdiits:radio name="radSubRateAmt" value="Rate" id="radSubRateAmt" onclick="showSub();" bundle="${gpfLables}" captionid="GPF.subRt"/>
		</td>
		
		<td width="25%">
		</td>
		
		<td width="25%">
		</td>
		
	</tr>

	<tr>
		<td width="25%">
			<hdiits:caption captionid="GPF.currSubRate" bundle="${gpfLables}"/>
		</td>
	
		<td width="25%">	
			<hdiits:text name="oldSubsAmt" id="oldSubsAmt" readonly="true" maxlength="12" style="color: #000000; font-family: Verdana;font-size: 12px; background-color: #E3E4FA;"/>
		</td>
		
		<td width="25%"  style="display: none;">
			<hdiits:caption captionid="GPF.currRate" bundle="${gpfLables}"/>
		</td>
		
		<td width="25%"  style="display: none;">
			<hdiits:text name="oldSubsRate" id="oldSubsRate" readonly="true" maxlength="12" style="color: #000000; font-family: Verdana;font-size: 12px; background-color: #E3E4FA;"/>
		</td>
	
		<td width="25%">
			<hdiits:caption captionid="GPF.newSubRate" bundle="${gpfLables}"/>
		</td>
	
		<td width="25%">
	    	<hdiits:number name="newSubAmt" mandatory="true" size="15" maxlength="15" onblur="roundAmt();" />
		</td>
	</tr>
	
	<tr>
	   	<td width="25%" id="newSubRtLab" style="display: none;">
			<hdiits:caption captionid="GPF.newSubRt" bundle="${gpfLables}"/>
		</td>
	
		<td width="25%" style="display: none;">	
			<hdiits:number name="newSubsRt" id="newSubsRt" maxlength="12" mandatory="true" onblur="calcAmt(this);roundRate();" />
		</td>
	</tr>
	
    <tr>
		<td width="25%">
			<hdiits:caption captionid="GPF.effFrom" bundle="${gpfLables}"/>
		</td>
	
		<td width="25%">
	    	<hdiits:dateTime  name="effDate"  captionid="GPF.effFrom" bundle="${gpfLables}"  mandatory="true"  maxvalue="31/12/2099 00:00:00"/>
		</td>
	</tr>
	
	<tr>
	</tr>
	
	<tr>
	</tr>

</table>
</hdiits:fieldGroup>


<table width="100%" align="center">
	<tr align="center">
		<td align="center">
	
			<hdiits:jsField jsFunction="chkAmt()" name="chkAmt"/>	
			<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp">	
			<jsp:param name="disableReset" value="true"/> 
			</jsp:include>
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



	</div>
</div>

<script>
initializetabcontent("maintab")
		
setOldSubsAmt();
makeReadOnlyEffDt();
setDefaultValues();
		
</script>


<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>



<%
} catch (Exception e) {
	e.printStackTrace();
	}
%>
