<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionToolTip" var="pensionToolTip" scope="request"/>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript">
	keyPreview = 1;
</script>

<c:set var="sno" value="0" />

<hdiits:form method="post" name="frmOtherArrearPopup"  validate="true" encType="multipart/form-data" action="" >
<br>
<fieldset class="tabstyle">
	<legend  id="headingMsg">Previous Month Arrear Details</legend>
	
	<table align="left" width="100%">
		<tr>
			<td align="left" width="15%">
				<b><fmt:message key="PEN.PPONO" bundle="${pensionLabels}"></fmt:message></b>							
			</td>
			<td align="left">
				<c:out value="${resValue.MonthlyPensionList[0].ppoNo}"></c:out>
			</td>
		</tr>
		<tr>
			<td align="left">
				<b><fmt:message key="PEN.NAMEOFPENSIONER" bundle="${pensionLabels}"></fmt:message></b> 
			</td>
			<td align="left">
				<c:out value="${resValue.MonthlyPensionList[0].pensionerName}"></c:out>				
			</td>
		</tr>
		<tr>
			<td align="left">
				<b><fmt:message key="PROVPNSN.ACNO" bundle="${pensionLabels}"></fmt:message></b>
			</td>
			<td align="left">			
				<c:out value="${resValue.MonthlyPensionList[0].accountNo}"></c:out>				
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">
				<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:960px; height:350px; overflow: scroll; overflow-x:scroll; overflow-y:scroll;  " >
				<display:table list="${resValue.MonthlyPensionList}" id="MonthlyPensionList" requestURI="" export="true" style="width:100%" excludedParams="contentUsingAjax">	
					<display:setProperty name="paging.banner.placement" value="bottom"/>
					<c:set var="sno" value="${sno+1}"></c:set>
					<display:column style="text-align: center;" class="tablecelltext" titleKey="MNTH.SRNO" headerClass="datatableheader" >${sno}</display:column>
					<display:column style="text-align: center;" class="tablecelltext" titleKey="MNTH.FORMONTH" headerClass="datatableheader" >
						<c:set var="EffectedPervMonth" value="${(MonthlyPensionList.forMonth) % 100}"></c:set>
						<c:set var="EffectedPervYear" value="${((MonthlyPensionList.forMonth) - EffectedPervMonth) / 100}"></c:set>
							<fmt:formatNumber var="fmtEffectedPervYear" pattern="0" value="${EffectedPervYear}"></fmt:formatNumber>
						<c:out value="${resValue.MonthList[EffectedPervMonth-1].commonDesc}"></c:out><br>
						<c:out value="${fmtEffectedPervYear}"></c:out>
					</display:column>
					
					<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.ALLBF56" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.allnBf11156}"></fmt:formatNumber><input type="hidden" name="hidallnBf11156" value="${MonthlyPensionList.allnBf11156} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.ALLAF56" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.allnAf11156}"></fmt:formatNumber><input type="hidden" name="hidallnAf11156" value="${MonthlyPensionList.allnAf11156} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.ALLAF60" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.allnAf10560}"></fmt:formatNumber><input type="hidden" name="hidallnAf10560" value="${MonthlyPensionList.allnAf10560} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.BASICPENSION" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.basicPensionAmount}"></fmt:formatNumber><input type="hidden" name="hidbasicPensionAmount" value="${MonthlyPensionList.basicPensionAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.DPAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.dpPercentAmount}"></fmt:formatNumber><input type="hidden" name="hiddpPercentAmount" value="${MonthlyPensionList.dpPercentAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" title="ADP Amount" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.adpAmount}"></fmt:formatNumber></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.TIAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.tiPercentAmount}"></fmt:formatNumber><input type="hidden" name="hidtiPercentAmount" value="${MonthlyPensionList.tiPercentAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.MAAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.medicalAllowenceAmount}"></fmt:formatNumber><input type="hidden" name="hidmedicalAllowenceAmount" value="${MonthlyPensionList.medicalAllowenceAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.CVPMONTHLY" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.cvpMonthlyAmount}"></fmt:formatNumber><input type="hidden" name="hidcvpMonthlyAmount" value="${MonthlyPensionList.cvpMonthlyAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.TIARREAR" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.TIArrearsAmount}"></fmt:formatNumber><input type="hidden" name="hidtiarrearsAmount" value="${MonthlyPensionList.TIArrearsAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.OTHERARREAR" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.otherArrearsAmount}"></fmt:formatNumber><input type="hidden" name="hidotherarrearsAmount" value="${MonthlyPensionList.otherArrearsAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.ITCUT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.itCutAmount}"></fmt:formatNumber><input type="hidden" name="hiditCutAmount" value="${MonthlyPensionList.itCutAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.SPECIALCUT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.specialCutAmount}"></fmt:formatNumber><input type="hidden" name="hidspecialCutAmount" value="${MonthlyPensionList.specialCutAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.OTHERBENEFIT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.otherBenefit}"></fmt:formatNumber><input type="hidden" name="hidotherBenefit" value="${MonthlyPensionList.otherBenefit} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.OMR" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.OMR}"></fmt:formatNumber><input type="hidden" name="hidOMR" value="${MonthlyPensionList.OMR} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PEN.RECOVERYAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.recoveryAmount}"></fmt:formatNumber><input type="hidden" name="hidrecoveryAmount" value="${MonthlyPensionList.recoveryAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.PENSIONCUT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.pensionCutAmount}"></fmt:formatNumber><input type="hidden" name="hidpensionCutAmount" value="${MonthlyPensionList.pensionCutAmount} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.PERSONALPENSION" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.personalPension}"></fmt:formatNumber><input type="hidden" name="hidpersonalPension" value="${MonthlyPensionList.personalPension} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.IRAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.ir}"></fmt:formatNumber><input type="hidden" name="hidIRAmount" value="${MonthlyPensionList.ir} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.MOCOMM" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.MOComm}"></fmt:formatNumber><input type="hidden" name="hidMOComm" value="${MonthlyPensionList.MOComm} "/></display:column>
					<display:column style="text-align: left;" class="tablecelltext" titleKey="PEN.NETAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0.00" value="${MonthlyPensionList.netPensionAmount}"></fmt:formatNumber><input type="hidden" name="hidnetPensionAmount" value="${MonthlyPensionList.netPensionAmount} "/></display:column>
					<display:footer media="html"></display:footer>
				</display:table>
				</div>				
			</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<input type="button" class="buttontag"  name="txtCancel" type="button" value="<fmt:message key="MNTH.CLOSE" bundle="${pensionLabels}" ></fmt:message>" onclick="window.close();" title="<fmt:message key="PNSN.Close" bundle="${pensionToolTip}"></fmt:message>" />
			</td>
		</tr>		
	</table>		
</fieldset>

</hdiits:form>

	