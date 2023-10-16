<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript"  src="script/common/common.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<table width="100%">
	<tr class="datatableheader">
		<td>
			<b> 
				PPO Number
			</b>
		</td>
		<td colspan="2" class="tablecelltext">
			<b> 
				${resValue.PpoNo}
			</b>
		</td>
	</tr>
	<tr class="datatableheader">
		<td align="center">
			Field
		</td>
		<td align="center">
			Current Value
		</td>
		<td align="center">
			Previous Value
		</td>
	</tr>
	<c:forEach var="CorrectionVo" items="${resValue.CorrectionDtls}">
		<tr>	
			<td class="tablecelltext">
				${CorrectionVo.fieldType}
			</td>
			<td class="tablecelltext">
				<c:set value="${CorrectionVo.crntFieldValue}" var="currentValue"/>
					<c:choose>
						<c:when test="${currentValue == 'N' || currentValue == 'Y' || currentValue == 'M' || currentValue == 'T' || currentValue == 'F'|| currentValue == 'A' || currentValue == 'C' || currentValue == 'R'|| currentValue == 'P' || currentValue == ''}">
							<c:if test="${currentValue == 'N'}">
								No
							</c:if>
							<c:if test="${currentValue == 'Y'}">
								Yes
							</c:if>
							<c:if test="${currentValue == 'M' and CorrectionVo.fieldType== 'Pensioner Gender'}">
								Male
							</c:if>
							<c:if test="${currentValue == 'M' and CorrectionVo.fieldType== 'Calculation Type'}">
								Manual
							</c:if>
							<c:if test="${currentValue == 'F'}">
								Female
							</c:if>
							<c:if test="${currentValue == 'T' and CorrectionVo.fieldType=='Pensioner Gender'}">  
								Transgender
							</c:if>
							<c:if test="${currentValue == 'T' and CorrectionVo.fieldType=='Pension Class'}">  
								Transferred 
							</c:if>
							<c:if test="${currentValue == 'A'}">
								Auto
							</c:if>
							<c:if test="${currentValue == 'C' and CorrectionVo.fieldType=='Pension Class'}">
								Revised
							</c:if>
							<c:if test="${currentValue == 'R'}">
								Regular
							</c:if>
							<!--<c:if test="${currentValue == 'P'}">
								Provisional
							</c:if>-->
							<c:if test="${currentValue == 'P' and CorrectionVo.fieldType=='DCRG Paid Flag'}">
								Partly Paid
							</c:if>
							<c:if test="${currentValue == ''}">
								N/A
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${currentValue != -1}">
								${currentValue}							
							</c:if>
						</c:otherwise>
					</c:choose>
			</td>
			<td class="tablecelltext">
				<c:set value="${CorrectionVo.prvsFieldValue}" var="PreviousValue" />
					<c:choose>
						<c:when test="${PreviousValue == 'N' || PreviousValue == 'Y' || PreviousValue == 'M' || PreviousValue == 'T' || PreviousValue == 'F' || PreviousValue == 'A' || PreviousValue == 'C' || PreviousValue == 'R'|| PreviousValue == 'P' || PreviousValue == ''}">
							<c:if test="${PreviousValue == 'N'}">
								No
							</c:if>
							<c:if test="${PreviousValue == 'Y'}">
								Yes
							</c:if>
							<c:if test="${PreviousValue == 'M' and CorrectionVo.fieldType== 'Pensioner Gender'}">
								Male
							</c:if>
							<c:if test="${PreviousValue == 'M' and CorrectionVo.fieldType== 'Calculation Type'}">
								Manual
							</c:if>
							<c:if test="${PreviousValue == 'F'}">
								Female
							</c:if>
							<c:if test="${PreviousValue == 'T' and CorrectionVo.fieldType=='Pensioner Gender'}">
								Transgender
							</c:if>
							<c:if test="${PreviousValue == 'T' and CorrectionVo.fieldType=='Pension Class'}">
								Transferred
							</c:if>
							<c:if test="${PreviousValue == 'A'}">
								Auto
							</c:if>
							<c:if test="${PreviousValue == 'C' and CorrectionVo.fieldType=='Pension Class'}">
								Revised
							</c:if>
							<c:if test="${PreviousValue == 'R'}">
								Regular
							</c:if>
							<!--<c:if test="${PreviousValue == 'P'}">
								Provisional
							</c:if>-->
							<c:if test="${PreviousValue == 'P' and CorrectionVo.fieldType=='DCRG Paid Flag'}">
								Partly Paid
							</c:if>
							<c:if test="${PreviousValue == ''}">
								N/A
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${PreviousValue != -1}">
								${PreviousValue}
							</c:if>
						</c:otherwise>
					</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>
<table align="center">
<tr>
<td>
<hdiits:button id="btnClose" name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>