<%@ page language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript" src="script/pensionpay/FamilyTab.js"></script>
<script type="text/javascript">
LISTBANKS='';
LISTRELATIONSHIP='';
</script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="mstPensionerFamilyDtlsVO"
	value="${resValue.MstPensionerFamilyDtlsVO}"></c:set>
<c:set var="mstPensionerNomineeDtlsVO"
	value="${resValue.MstPensionerNomineeDtlsVO}"></c:set>

<c:forEach var="BankList" items="${resValue.lLstBanks}">
	<script> LISTBANKS += '<option value="${BankList.id}"> ${BankList.desc}</option>';</script>
</c:forEach>
<c:forEach var="Relation" items="${resValue.lLstRelation}">
	<script> LISTRELATIONSHIP += '<option value="${Relation.lookupShortName}"> ${Relation.lookupName}</option>';</script>
</c:forEach>
<fieldset class="tabstyle" style="width: 100%"><legend>
<b> <fmt:message key="PPMT.FAMILYDTLS" bundle="${pensionLabels}"></fmt:message></b>
</legend>
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 170px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<input type="hidden" id="hidFamilyGridSize" name="hidFamilyGridSize"
	value="0" /> <hdiits:button id="btnAddRowFamily" type="button"
	name="btnAddRowFamily" captionid="PPMT.ADDROW" bundle="${pensionLabels}"
	onclick="familyDtlsTableAddRow();" />
<table id="tblPnsnrFamilyDtls" border="1">
	<tr style="width: 100%" class="datatableheader">
		<td width="10%" class="datatableheader"><fmt:message
			key="PPMT.FULLNAMEFAMILY" bundle="${pensionLabels}"></fmt:message></td>
		<td width="12%" class="datatableheader"><fmt:message
			key="PPMT.RELATIONSHIP" bundle="${pensionLabels}"></fmt:message></td>
		<td width="3%" class="datatableheader"><fmt:message
			key="PPMT.FAMILYPNSNR" bundle="${pensionLabels}"></fmt:message></td>
		<td width="12%" class="datatableheader"><fmt:message
			key="PPMT.DOB" bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%" class="datatableheader"><fmt:message
			key="PPMT.MINOR" bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%" class="datatableheader"><fmt:message
			key="PPMT.MARRIED" bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%" class="datatableheader"><fmt:message
			key="PPMT.SALARY" bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="datatableheader"><fmt:message
			key="PPMT.PHYHANDICAP" bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" class="datatableheader"><fmt:message
			key="PPMT.GUARDIANNAME" bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" class="datatableheader"><fmt:message
			key="PPMT.DOD" bundle="${pensionLabels}"></fmt:message></td>
		<td width="1%" class="datatableheader"><fmt:message
			key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>

	<c:choose>

		<c:when test="${resValue.MstPensionerFamilyDtlsVO !=null}">

			<c:forEach var="familyDtlsVO"
				items="${resValue.MstPensionerFamilyDtlsVO}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="hidden" name="hdnFamilyMemberId" id="hdnFamilyMemberId${Counter.index}" value="${familyDtlsVO.familyMemberId}">
					<input type="text" name="txtFMName" id="txtFMName${Counter.index}" size="20" value="${familyDtlsVO.name}"  onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)" maxlength="100">
					</td>
					<td class="tds" align="center"><select name="cmbRelation"
						id="cmbRelation${Counter.index}">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="relation" items="${resValue.lLstRelation}">
				         <c:choose>
				            <c:when test="${relation.lookupShortName == familyDtlsVO.relationship}">
				            <option selected="selected" value="${relation.lookupShortName}"><c:out value="${relation.lookupName}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${relation.lookupShortName}">
								<c:out value="${relation.lookupName}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>

					</select></td>
					<td class="tds" align="center">
					<c:choose>
					    <c:when test="${familyDtlsVO.percentage == 100}">
							<input type="checkbox" id="chkFamilyMember${Counter.index}"
								name="chkFamilyMember" checked="checked" value="Y"/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="chkFamilyMember${Counter.index}"
								name="chkFamilyMember" value="Y"/>
						</c:otherwise>
						</c:choose>
					<!-- 	
					<input type="text"
						name="txtPercentage" id="txtPercentage${Counter.index}" size="15"
						style="text-align: right"
						onblur="onBlur(this);chkDigitAmnt(this);if(chkForsum100(this)!=false && totalPercentage(this,'txtPercentage','Total Percentage cannot be greater than 100','hidFamilyGridSize')!=false){}" maxlength="6"
						 onkeypress="amountFormat(this);"
						value="${familyDtlsVO.percentage}"> -->
				    </td>
					<td class="tds" align="center">
						<input type="text" name="txtFMDateOfBirth" id="txtFMDateOfBirth${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${familyDtlsVO.dateOfBirth}"/>"
	      				 onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="10"
							onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" tabindex="18"/>
	      				 <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtFMDateOfBirth${Counter.index}",375,570)'style="cursor: pointer;" />
					</td>
					<td class="tds" align="center"><c:choose>
						<c:when test="${familyDtlsVO.majorFlag=='N'}">
							<input type="checkbox" id="chkMinor${Counter.index}"
								name="chkMinor" checked="checked" value="Y"/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="chkMinor${Counter.index}"
								name="chkMinor"  value="Y"/>
						</c:otherwise>
					</c:choose></td>
					<td class="tds" align="center"><c:choose>
						<c:when test="${familyDtlsVO.marriedFlag=='Y'}">
							<input type="checkbox" id="chkMarried${Counter.index}"
								name="chkMarried" checked="checked" value="Y"/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="chkMarried${Counter.index}"
								name="chkMarried" value="Y"/>
						</c:otherwise>
					</c:choose></td>
					<td class="tds" align="center"><input type="text"
						name="txtFMSalary" id="txtFMSalary${Counter.index}" size="15"
						onkeypress="numberFormat(this);" value="${familyDtlsVO.salary}">
					</td>
					<td class="tds" align="center"><select name="cmbPhyHandicap"
						id="cmbPhyHandicap${Counter.index}" style="width: 100%">
						<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
						<c:choose>
							<c:when test="${familyDtlsVO.handicapeFlag == 'Y'}">
								<option value="Y" selected="selected"><fmt:message
									key="PPMT.YES" bundle="${pensionLabels}" /></option>
									<option value="N"><fmt:message key="PPMT.NO"
									bundle="${pensionLabels}" /></option>
							</c:when>
							<c:when test="${familyDtlsVO.handicapeFlag == 'N'}">
								<option value="Y"><fmt:message key="PPMT.YES"
									bundle="${pensionLabels}" /></option>
								<option value="N" selected="selected"><fmt:message
									key="PPMT.NO" bundle="${pensionLabels}" /></option>
							</c:when>
							<c:otherwise>
								<option value="Y"><fmt:message key="PPMT.YES"
									bundle="${pensionLabels}" /></option>
								<option value="N"><fmt:message key="PPMT.NO"
									bundle="${pensionLabels}" /></option>
							</c:otherwise>
							
						</c:choose>
						
					</select></td>
					<td class="tds" align="center"><input type="text"
						name="txtFMGuardianName" id="txtFMGuardianName${Counter.index}" maxlength="100"
						size="15" value="${familyDtlsVO.guardianName}"  onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)"/></td>
					<td class="tds" align="center"><input type="text"
						name="txtFMDateOfDeath" id="txtFMDateOfDeath${Counter.index}"
						onkeypress="numberFormat(this);" style="width: 90px"
						onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
						class="texttag, textString" tabindex="37"
						value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${familyDtlsVO.dateOfDeath}"/>" ${read} />
					<img src='images/CalendarImages/ico-calendar.gif'
						style="width: 16px"
						onClick='window_open("txtFMDateOfDeath${Counter.index}",375,570)'
						style="cursor: pointer;" ${disabled}/></td>

					<td class="tds" align="center"><img name="imgFamily"
						id="Image${Counter.index}" 
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this,'tblPnsnrFamilyDtls')" /></td>
				</tr>
				<script>
						document.getElementById("hidFamilyGridSize").value=Number('${Counter.index}') + 1;
					</script>
			</c:forEach>
		</c:when>

	</c:choose>

</table>
</div>
</fieldset>





<fieldset class="tabstyle" style="width: 100%"><legend>
<b> <fmt:message key="PPMT.NOMINEEDTLS" bundle="${pensionLabels}"></fmt:message></b>
</legend>
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 170px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<input type="hidden" id="hidNomGridSize" name="hidNomGridSize" value="0" />
<hdiits:button id="btnNomAddRow" type="button" name="btnNomAddRow" bundle="${pensionLabels}"
	captionid="PPMT.ADDROW" onclick="nomineeDtlsTableAddRow();" />
<table id="tblNomineeDtls" border="1">
	<tr style="width: 100%" class="datatableheader">
		<td width="15%" class="datatableheader"><fmt:message
			key="PPMT.NAME" bundle="${pensionLabels}"></fmt:message></td>
		<td width="8%" class="datatableheader"><fmt:message
			key="PPMT.PERCENTAGE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" class="datatableheader"><fmt:message
			key="PPMT.BANKBRANCHCODE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="25%" class="datatableheader"><fmt:message
			key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" class="datatableheader"><fmt:message
			key="PPMT.BANKBRANCHNAME" bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="datatableheader"><fmt:message
			key="PPMT.ACNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%" class="datatableheader"><fmt:message
		 	key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	<c:choose>

		<c:when test="${resValue.MstPensionerFamilyDtlsVO !=null}">

			<c:forEach var="nomineeDtlsVO"
				items="${resValue.MstPensionerNomineeDtlsVO}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="hidden" name="hdnNomineeId" id="hdnNomineeId${Counter.index}" value="${nomineeDtlsVO[0]}"/>
					<input type="text" name="txtNomineeName" id="txtNomineeName${Counter.index}" size="20" value="${nomineeDtlsVO[1]}"  onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)" maxlength="50">
					</td>
					<td class="tds" align="center"><input type="text"   style="text-align: right"
						name="txtNomPercentage" id="txtNomPercentage${Counter.index}" 
						onblur="onBlur(this);if(isPercentage(this,'Value must be in percentage and cannot be grater than 100.','100')!=false && totalPercentage(this,'txtNomPercentage','Total Percentage cannot be greater than 100','hidNomGridSize')!=false){};" 
						size="15" onkeypress="amountFormat(this);"
						value="${nomineeDtlsVO[2]}"></td>
					<td class="tds" align="center"><input type="text"
						name="txtNomBankBranchCode" id="txtNomBankBranchCode${Counter.index}"  onblur="getNomBnkBrnchNameFrmBnkCode(this)" onKeyPress="numberFormat(this)"
						size="15" value="${nomineeDtlsVO[4]}" /></td>
					<td class="tds" align="center"><select name="cmbNomBank"
						id="cmbNomBank${Counter.index}" style="width: 100%" onchange="getNomBranchNameFromBankCode(this)">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						 <c:forEach var="bank" items="${resValue.lLstBanks}">
				         <c:choose>
				            <c:when test="${bank.id == nomineeDtlsVO[3]}">
				         <option selected="selected" value="${bank.id}"><c:out value="${bank.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${bank.id}">
								<c:out value="${bank.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
						
					</select></td>
					<td class="tds" align="center"><select name="cmbNomBankBranch"
						id="cmbNomBankBranch${Counter.index}" style="width: 100%" onchange="getNomAudNameFromBranchCode(this);">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<option value="${nomineeDtlsVO[4]}" selected="selected"><c:out
							value="${nomineeDtlsVO[6]}" /></option>
					</select></td>
					<td class="tds" align="center"><input type="text"
						name="txtNomAccountNo" id="txtNomAccountNo${Counter.index}" size="25"
						value="${nomineeDtlsVO[5]}" maxlength="20"/></td>
					<td class="tds" align="center"><img name="imgNominee"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblNomineeDtls');"/></td>
				</tr>
				<script>
					document.getElementById("hidNomGridSize").value = Number('${Counter.index}') + 1;
				</script>
			</c:forEach>
		</c:when>

	</c:choose>
</table>

</div>
</fieldset>