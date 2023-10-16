<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />

<hdiits:form method="post" name="TrsryTransferCasefrm"  validate="true" encType="multipart/form-data" action="" >
<fieldset class="tabstyle"><legend><fmt:message key="PPMT.TRANSFERTOOTHERTREASURY" bundle="${pensionLabels}"></fmt:message> </legend>
<table align="center" width="100%">
		
	<tr>
		<td align="left" >
			<fieldset class="tabstyle">
			<legend><fmt:message key="PPMT.TRNSFETRSRYCASE" bundle="${pensionLabels}" ></fmt:message> </legend>
			<table align="center" width="90%">
				<tr>
					<td align="center" colspan="5">&nbsp;</td>				
				</tr>
				<tr>
					<td>
						<fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message>
					</td>
					<td>
						<input type="text" name="txttfrPPONO" id="txttfrPPONO" size="25" value="" onblur="getTranfererDtls(this)" />
					</td>
					<td width="10%"></td>
					<td>
						<fmt:message key="PPMT.PENIONERNAME" bundle="${pensionLabels}"></fmt:message>
					</td>
					<td>
						<input type="text" name="txttfrpensionerName" id="txttfrpensionerName" default="" size="25" />
					</td>
				</tr>
				
				<tr>
					<td>
						<fmt:message key="PPMT.OLDSTATE" bundle="${pensionLabels}"></fmt:message>
					</td>
					<td>
						<input type="text" name="txtoldState" id="txtoldState" default="" size="25"/>
					</td>
					<td width="10%"></td>
					<td>
						<fmt:message key="PPMT.NEWSTATE" bundle="${pensionLabels}"></fmt:message>
					</td>
					<td>
						<select name="cmblstNewState" id="cmblstNewState" onchange="" style="width: 55%">
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						</select>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					</td>
				</tr>
				
				<tr>
					<td>
						<fmt:message key="PPMT.OLDTRSRY" bundle="${pensionLabels}"></fmt:message>
					</td>
					<td>
						<input type="text" name="txtoldTrsryName" id="txtoldTrsryName" default="" size="25"/>
					</td>
					<td width="10%"></td>
					<td>
						<fmt:message key="PPMT.NEWTRSRY" bundle="${pensionLabels}"></fmt:message>
					</td>
					<td>
						<select name="cmblstNewTrsry" id="cmblstNewTrsry" style="width: 75%">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>	
						</select>
						<input type="text" name="txtlstNewTrsry" id="txtlstNewTrsry" value="" size="30" style="display: none" />&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
					</td>
				</tr>
				
				<tr>
					
					<td width="10%"></td>
					<td>
					</td>
					<td>
					</td>
				</tr> 
				
				<tr>
					<td align="center" colspan="5">&nbsp;</td>				
				</tr>
				
			</table>
			</fieldset>
		</td>	
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="center">
			<input type="button" value="Send" class="buttontag" name="btnSend" onclick="" />
			<input type="button" value="Close" class="buttontag" name="btnClose"/>
		</td>				
	</tr>	
</table>
</fieldset>
</hdiits:form>
