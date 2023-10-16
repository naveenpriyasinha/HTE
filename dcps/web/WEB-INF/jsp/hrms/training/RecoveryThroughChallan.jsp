<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables_en_US" var="recoveryThroughChallanLabels" scope="page"/>
<hdiits:form name="form1" validate="true" method="POST" action="">

<hdiits:fieldGroup titleCaptionId="rtc.RecoveryofPrincipal" bundle="${recoveryThroughChallanLabels}" >
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
<tr>
<td>
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">

<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="rtc.LoanAmountDistribution" bundle="${recoveryThroughChallanLabels}"/></b>
			</td>
			
			<td width="22%" align="left"><hdiits:text name="LoanAmountDistribution" maxlength="8"   size="10" /></td>
		
</tr>

<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="rtc.TotalNoofInstallment" bundle="${recoveryThroughChallanLabels}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="TotalNoofInstallment" maxlength="2"   size="5" />
			</td>	
			
			<td width="22%" align="left"><b><fmt:message key="rtc.InstallmentAmount." bundle="${recoveryThroughChallanLabels}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="InstallmentAmount" maxlength="6"   size="6" />
			</td>		
</tr>
		
<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="rtc.Odd.InstallmentNo." bundle="${recoveryThroughChallanLabels}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="OddInstallmentNo" maxlength="2"   size="5" />
			</td>	
			
			<td width="22%" align="left"><b><fmt:message key="rtc.OddInstallmentAmount." bundle="${recoveryThroughChallanLabels}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="OddInstallmentAmount" maxlength="6"   size="6" />
			</td>	
</tr>

<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="rtc.LastInstlNo.Recovered." bundle="${recoveryThroughChallanLabels}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="LastInstlNoRecovered" maxlength="2"   size="5" />
			</td>	
			
			<td width="22%" align="left"><b><fmt:message key="rtc.OutstandingAmount" bundle="${recoveryThroughChallanLabels}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="OutstandingAmount" maxlength="6"   size="6" />
			</td>
			
			<td width="22%" align="left"><b><fmt:message key="rtc.Ason" bundle="${recoveryThroughChallanLabels}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="Ason" maxlength="8"   size="8" />
			</td>	
</tr>

<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="rtc.CurrentPayMonth" bundle="${recoveryThroughChallanLabels}"/></b>
			</td>
			<td width="22%" align="left"><hdiits:text name="CurrentPayMonth" maxlength="10"   size="10" /></td>
		
</tr>

<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="rtc.RecoveryOrderNo." bundle="${recoveryThroughChallanLabels}"/></b>
			</td>
			<td width="22%" align="left"><hdiits:text name="RecoveryOrderNo" maxlength="10"   size="15" /></td>
		
		
			<td width="22%" align="left"><b><fmt:message key="rtc.RecoveryOrderDate." bundle="${recoveryThroughChallanLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:select id="cmbRecdate" name="cmbRecdate" style="width:120px;">
							<hdiits:option value="Select"  selected="true">Select</hdiits:option>
							<hdiits:option value="0"  selected="true"></hdiits:option>
							<hdiits:option value="1"  selected="true"></hdiits:option>
							<hdiits:option value="2"  selected="true"></hdiits:option>
							<hdiits:option value="3"  selected="true"></hdiits:option>
					</hdiits:select></td>
		
</tr>

<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="rtc.ChallanNo." bundle="${recoveryThroughChallanLabels}"/></b>
			</td>
			<td width="22%" align="left"><hdiits:text name="ChallanNo." maxlength="6"   size="6" /></td>
		
		
			<td width="22%" align="left"><b><fmt:message key="rtc.ChallanDate." bundle="${recoveryThroughChallanLabels}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:select id="cmbChallandt" name="cmbChallandt" style="width:120px;">
							<hdiits:option value="Select"  selected="true">Select</hdiits:option>
							<hdiits:option value="0"  selected="true"></hdiits:option>
							<hdiits:option value="1"  selected="true"></hdiits:option>
							<hdiits:option value="2"  selected="true"></hdiits:option>
							<hdiits:option value="3"  selected="true"></hdiits:option>
					</hdiits:select></td>
		
</tr>


<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="rtc.AmountPaid." bundle="${recoveryThroughChallanLabels}"/></b>
			</td>
			<td width="22%" align="left"><hdiits:text name="AmountPaid." maxlength="6"   size="6" /></td>
		
</tr>
</table>

<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
<tr>
<td align="center">
<hdiits:button type="button" name="Save" bundle="${recoveryThroughChallanLabels}" captionid="rtc.Save" style="height: 25px; width: 100px"/>
<hdiits:button type="button" name="Revert" bundle="${recoveryThroughChallanLabels}" captionid="rtc.Revert" style="height: 25px; width: 100px"/>
<hdiits:button type="button" name="Close" bundle="${recoveryThroughChallanLabels}" captionid="rtc.Close" style="height: 25px; width: 100px"/>
</td>
</tr>
</table>
</td>
</tr>
</table>
</hdiits:fieldGroup>
</hdiits:form>
<%
		} catch (Exception e) {
			e.printStackTrace();

}
%>

			