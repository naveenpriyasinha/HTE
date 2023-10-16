<%try{%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/>"></script>
<script  type="text/javascript"  src="script/common/behaviour.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>



<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />

<c:set var="CurDate" value="${resValue.CurDate}" />
<hdiits:form name="monthlyPension" validate="false" method="post" >
<center>
<fieldset class="tabstyle" >
	<br>
	<legend> <b>
		<fmt:message key="PPMT.MNTHPENBILL" bundle="${pensionLabels}"></fmt:message></b> </legend>
	<br>
	<table align="center"  width="60%" border="0" align="center" class="Label" >
		<hdiits:tr>
			<hdiits:td align="left" width="10%"><fmt:message key="PPMT.FORMONTH" bundle="${pensionLabels}"></fmt:message>
			</hdiits:td>
			<hdiits:td align="left" width="20%">
				<select name="cmbForMonth" id="cmbForMonth"  tabindex="1" >
					<option value='-1'>---Select---</option>
				</select>&nbsp;<select name="cmbForYear" id="cmbForYear">
					<option value='-1'>---Select---</option>
				</select><label class="mandatoryindicator">*</label>
			</hdiits:td>
			
			</hdiits:tr>
		
		<hdiits:tr id="bnkBrnch">
			<hdiits:td align="left"><fmt:message key="PPMT.BANKCODE" bundle="${pensionLabels}"></fmt:message></hdiits:td>
				<hdiits:td align="left"> 
					<input type="text" name="txtbranchCode" size="10" tabindex="6" onblur="" /> 
			</hdiits:td>
			<hdiits:td align="left"><fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message>
			</hdiits:td>
			<hdiits:td align="left" >
				<select name="cmbBank" id="cmbBank" tabindex="6" >
					<option value='-1'>---Select---</option>
				</select>
			</hdiits:td>
			<hdiits:td align="left"><fmt:message key="PPMT.BRANCH" bundle="${pensionLabels}"></fmt:message>
			</hdiits:td>
			<hdiits:td align="left">
				<select name="cmbBranch" id="cmbBranch" tabindex="7" >
					<option value="-1">--Select--</option>
				</select>
			</hdiits:td>
		</hdiits:tr>
	
	</table>
	<br>
</fieldset>
</center>
<br>
<br>
</hdiits:form>
<table align="center">
<hdiits:button name="btnGenerateBill" id="btnGenerateBill" type="button"  classcss="bigbutton" captionid="PPMT.GENERATEBILL" bundle="${pensionLabels}" onclick=""/>
<hdiits:button name="btnReset" id="btnReset" type="button" captionid="PPMT.RESET" bundle="${pensionLabels}" onclick=""/>
<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick=""/>
</table>

<% }catch(Exception e){
e.printStackTrace();
}%>