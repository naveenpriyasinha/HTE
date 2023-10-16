<%try{ %>


<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>

<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseFamilyDtls.js"></script>

<script type="text/javascript">
LISTRELATION='';
NAMEOFFAMILYMEMBER ='<fmt:message key="PPROC.NAMEOFFAMILYMEMBER" bundle="${pensionAlerts}"></fmt:message>';
RELATION ='<fmt:message key="PPROC.RELATION" bundle="${pensionAlerts}"></fmt:message>';
DATEOFBIRTH ='<fmt:message key="PPROC.DATEOFBIRTH" bundle="${pensionAlerts}"></fmt:message>';
AGE ='<fmt:message key="PPROC.AGE" bundle="${pensionAlerts}"></fmt:message>';
NAMEOFNOMINEE ='<fmt:message key="PPROC.NAMEOFNOMINEE" bundle="${pensionAlerts}"></fmt:message>';
AMTIFSHAREORGRTY ='<fmt:message key="PPROC.AMTIFSHAREORGRTY" bundle="${pensionAlerts}"></fmt:message>';
BRANCHNAME ='<fmt:message key="PPROC.BRANCHNAME" bundle="${pensionAlerts}"></fmt:message>';
BANKNAME ='<fmt:message key="PPROC.BANKNAME" bundle="${pensionAlerts}"></fmt:message>';
ACCNO ='<fmt:message key="PPROC.ACCNO" bundle="${pensionAlerts}"></fmt:message>';
LISTBANKS='';

</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="RelationShip" value="${resValue.lLstRelation}"></c:set>
<c:set var="counter" value="0"></c:set>

<c:forEach var="RelationList" items="${resValue.lLstRelation}" >
	<script> LISTRELATION += '<option value="${RelationList.lookupShortName}"> ${RelationList.lookupName}</option>';</script>
</c:forEach>
<c:forEach var="BankList" items="${resValue.lLstBanks}">
	<script> LISTBANKS += '<option value="${BankList.id}"> ${BankList.desc}</option>';</script>
</c:forEach>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstPnsnrFamilyDtls" value="${resValue.lLstPnsnrFamilyDtls}"></c:set>
<c:set var="lObjTrnPnsnprocPnsnrpayVO" value="${resValue.lObjTrnPnsnprocPnsnrpayVO}"></c:set>


<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPROC.FAMILYDTLS"
	bundle="${pensionLabels}"></fmt:message></b></legend> 
	<input type="hidden" name="hidFamilyGridSize" id="hidFamilyGridSize" value="0" />
	<input type="hidden" name="hidNoOfRows" id="hidNoOfRows" value="${resValue.lStrRowId}" />
	<input type="hidden" name="hidNomOrFamily" id="hidNomOrFamily" value="${resValue.lStrAddress}" />
	<input type="hidden" name="hidFmlyAddrFlag" id="hidFmlyAddrFlag"  />
	<input type="hidden" name="hidNomAddrFlag" id="hidNomAddrFlag"  />
	
	<table>
		<tr>
		<td width="20%" align="left"><fmt:message
			key="PPROC.FAMILYPENSION" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">		
		<c:choose>
		<c:when test="${lObjTrnPnsnprocPnsnrpayVO.famPenFlag == 89}">
			<input type="radio"	id="radioFamilyPenYes" name="radioFamilyPen" value="Y"	onclick="enableFamilyDetails();" checked="checked"/>
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}" ></fmt:message>
			<input type="radio" id="radioFamilyPenNo" name="radioFamilyPen" value="N" onclick="enableFamilyDetails();" />
		 	<fmt:message	key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		 	<c:set var="varReadOnlyPro" value=""></c:set>		 
		 	<c:set var="varDisplayPro" value=""></c:set>	
		 	<c:set var="varReadOnlyProButton" value="false"></c:set>	 	
		</c:when>
		<c:otherwise>
			<input type="radio"  id="radioFamilyPenYes" name="radioFamilyPen" value="Y" onclick="enableFamilyDetails();" />
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioFamilyPenNo" name="radioFamilyPen" value="N" onclick="enableFamilyDetails();" checked="checked" />
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
			<c:set var="varReadOnlyPro" value="readonly"></c:set>			
			<c:set var="varDisplayPro" value="display: none;"></c:set>
			<c:set var="varReadOnlyProButton" value="true"></c:set>			
		</c:otherwise>
		</c:choose>
		</td>
		<td width="20%" align="left"></td>
		<td width="30%" align="left"></td>
	</tr>
	</table>
	
	
	
	<hdiits:button
	name="familyDtlsAddRow" id="familyDtlsAddRow"  readonly="${varReadOnlyProButton}" type="button" captionid="PPROC.ADDROW"
	bundle="${pensionLabels}" onclick="familyDtlsTableAddRow()" />
<div
<%int i=0; %>
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 200px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblFamilyDtls" align="left" width="95%" cellspacing="0" 
	border="1">

	<tr class="datatableheader" style="width: 100%">
		<td width="15%" class="HLabel" ><fmt:message
			key="PPROC.NAMEOFFAMILYMEMBER" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="12%" class="HLabel" ><fmt:message
			key="PPROC.RELATION" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="3%" class="HLabel" style="display: none;"><fmt:message
			key="PPROC.PERCENTAGE" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel" ><fmt:message
			key="PPROC.PHYSICLYHNDCPED" bundle="${pensionLabels}"></fmt:message></td>
		<td width="7%" class="HLabel" ><fmt:message
			key="PPROC.DATEOFBIRTH" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
	<td width="7%" class="HLabel" ><fmt:message
			key="PPROC.FANILYPNSNR" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<%--<td width="7%" class="HLabel" ><fmt:message
			key="PPROC.NOMINEE" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="7%" class="HLabel" ><fmt:message
			key="PPROC.PERCENTAGE" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>			
		--%><td width="3%" class="HLabel" ><fmt:message
			key="PPROC.MINOR" bundle="${pensionLabels}"></fmt:message></td>
		<td width="3%" class="HLabel" ><fmt:message
			key="PPROC.MARRIED" bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%" class="HLabel" style="display: none;"><fmt:message
			key="PPROC.SALARY" bundle="${pensionLabels}"></fmt:message></td>
		
		<td width="15%" class="HLabel" ><fmt:message
			key="PPROC.GUARDIAN" bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="HLabel" style="display: none;"><fmt:message
			key="PPROC.DATEOFDEATH" bundle="${pensionLabels}"></fmt:message></td>
		<td width="1%" class="HLabel"><fmt:message 
			key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	
		<c:choose>
		<c:when test="${resValue.lLstPnsnrFamilyDtls !=null}">

			<c:forEach var="familyDtlsVO"
				items="${resValue.lLstPnsnrFamilyDtls}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="hidden" name="hdnFamilyMemberId" id="hdnFamilyMemberId${Counter.index}" value="${familyDtlsVO.pnsnrFamilyId}">
					<input type="text" style="text-transform: uppercase" name="txtNameOfFamilyMember" id="txtNameOfFamilyMember${Counter.index}" size="30" value="${familyDtlsVO.name}"  onfocus="onFocus(this)"  onblur="onBlur(this);isName(this,'Please enter valid Name');" onkeypress="upperCase(event)">
					</td>
					<td class="tds" align="center"><select name="cmbFmlyMemRelation"
						id="cmbFmlyMemRelation${Counter.index}">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="relation" items="${resValue.lLstRelation}">
				         <c:choose>
				            <c:when test="${relation.lookupShortName == familyDtlsVO.relation}">
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
					<td class="tds" align="center" style="display: none;"><input type="text"
						name="txtPercentage" id="txtPercentage${Counter.index}" size="10"
						style="text-align: right"
						onblur="onBlur(this);chkDigitAmnt(this);if(chkForsum100(this)!=false && totalPercentage(this,'txtPercentage','Total Percentage cannot be greater than 100','hidFamilyGridSize')!=false){}" maxlength="3"
						 onkeypress="amountFormat(this);"
						value="${familyDtlsVO.percentage}"></td>
						
						<td class="tds" align="center"><select name="cmbPhyHandicap"
						id="cmbPhyHandicap${Counter.index}" style="width: 100%" onchange="enableGuardianName(${Counter.index})">
						<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
						<c:choose>
							<c:when test="${familyDtlsVO.handicapeFlag == 'Y'}">
								<option value="Y" selected="selected"><fmt:message
									key="PPROC.YES" bundle="${pensionLabels}" /></option>
									<option value="N"><fmt:message key="PPROC.NO"
									bundle="${pensionLabels}" /></option>
							</c:when>
							<c:when test="${familyDtlsVO.handicapeFlag == 'N'}">
								<option value="Y"><fmt:message key="PPROC.YES"
									bundle="${pensionLabels}" /></option>
								<option value="N" selected="selected"><fmt:message
									key="PPROC.NO" bundle="${pensionLabels}" /></option>
							</c:when>
							<c:otherwise>
								<option value="Y"><fmt:message key="PPROC.YES"
									bundle="${pensionLabels}" /></option>
								<option value="N"><fmt:message key="PPROC.NO"
									bundle="${pensionLabels}" /></option>
							</c:otherwise>
							
						</c:choose>
						
					</select></td>
						
					<td class="tds" align="center">
						<input type="text" name="txtFmlyMemDateOfBirth" id="txtFmlyMemDateOfBirth${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${familyDtlsVO.birthDate}"/>"
	      				 onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="10"
							onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" tabindex="18"/>
	      				 <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtFmlyMemDateOfBirth${Counter.index}",375,570)'style="cursor: pointer;" />
					</td>
					
					<td class="tds" align="center"><c:choose>
						<c:when test="${familyDtlsVO.famlyPenFlag=='Y'}">
							<input type="checkbox" id="chkFamlyPen${Counter.index}"
								name="chkFamlyPen" checked="checked" value="Y" />
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="chkFamlyPen${Counter.index}"
								name="chkFamlyPen"  value="Y"  />
						</c:otherwise>
					</c:choose></td>
					
				
					<td class="tds" align="center"><c:choose>
						<c:when test="${familyDtlsVO.minorFlag=='Y'}">
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
					<td class="tds" align="center" style="display: none;"><input type="text"
						name="txtFMSalary" id="txtFMSalary${Counter.index}" size="15"
						onkeypress="numberFormat(this);" value="${familyDtlsVO.salary}">
					</td>
					
					<td class="tds" align="center">
					<c:choose>
						<c:when test="${familyDtlsVO.handicapeFlag == 'Y' || familyDtlsVO.minorFlag=='Y'}">
							<c:set var="varReadOnly" value=""></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="varReadOnly" value="readonly='readonly'"></c:set>
						</c:otherwise>
					</c:choose>					
					<input type="text"
						name="txtFMGuardianName" id="txtFMGuardianName${Counter.index}"
						size="30" value="${familyDtlsVO.guardianName}" style="text-transform: uppercase;"  ${varReadOnly} onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)"/></td>
					<td class="tds" align="center" style="display: none;"><input type="text"
						name="txtFMDateOfDeath" id="txtFMDateOfDeath${Counter.index}"
						onkeypress="numberFormat(this);" style="width: 90px"
						onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
						class="texttag, textString" tabindex="37"
						value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${familyDtlsVO.deathDate}"/>" ${read} />
					<img src='images/CalendarImages/ico-calendar.gif'
						style="width: 16px"
						onClick='window_open("txtFMDateOfDeath${Counter.index}",375,570)'
						style="cursor: pointer;" ${disabled}/></td>

					<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this,'tblFamilyDtls')" /></td>
				</tr>
				<script>
						document.getElementById("hidFamilyGridSize").value=Number('${Counter.index}') + 1;
					</script>
			</c:forEach>
		</c:when>
	</c:choose>
</table>
<%i++; %>
</div>
</fieldset>



<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPROC.NOMINEEDTLSPNSN"
	bundle="${pensionLabels}"></fmt:message></b></legend> 
	<input type="hidden" name="hidNomGridSize" id="hidNomGridSize" value="0" />
	<input type="hidden" name="hidNomineeNoOfRows" id="hidNomineeNoOfRows" value="${resValue.lStrRowId}" />
	<%-- <input type="hidden" name="hidNomOrFamily" id="hidNomOrFamily" value="${resValue.lStrAddress}" />--%>
	<input type="hidden" name="hidNomAddrFlag" id="hidNomAddrFlag"  />
	<hdiits:button
	name="NomineeDtlsAddRow" type="button" captionid="PPROC.ADDROW"
	bundle="${pensionLabels}" onclick="nomineeDtlsTableAddRow()" />
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 200px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblNomineeDtls" align="left" width="95%" cellspacing="0"
	border="1">

	<tr class="datatableheader" style="width: 100%">
		<td width="15%" class="HLabel" ><fmt:message
			key="PPROC.NAMEOFFAMILYMEMBER" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="12%" class="HLabel" ><fmt:message
			key="PPROC.RELATION" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="3%" class="HLabel"><fmt:message
			key="PPROC.PERCENTAGE" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="12%" class="HLabel" ><fmt:message
			key="PPROC.ALTERNATENOMINEE" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="1%" class="HLabel"><fmt:message 
			key="PPROC.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	
	
		<c:if test="${resValue.lLstPnsnrNomineeDtls !=null}">

			<c:forEach var="NomineeDtlsVO"
				items="${resValue.lLstPnsnrNomineeDtls}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="hidden" name="hdnNomineeMemberId" id="hdnNomineeMemberId${Counter.index}" value="${NomineeDtlsVO[0]}">
					<input type="text" style="text-transform: uppercase" name="txtNameOfNominee" id="txtNameOfNominee${Counter.index}" size="30" value="${NomineeDtlsVO[1]}"  onfocus="onFocus(this)"  onblur="onBlur(this);isName(this,'Please enter valid Name');" onkeypress="upperCase(event)">
					</td>
				<td class="tds" align="center"><select name="cmbNomineeMemRelation"
						id="cmbNomineeMemRelation${Counter.index}">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="relation" items="${resValue.lLstRelation}">
				         <c:choose>
				            <c:when test="${relation.lookupShortName == NomineeDtlsVO[7]}">
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
					<td class="tds" align="center" ><input type="text"
						name="txtNomPercentage" id="txtNomPercentage${Counter.index}" size="10"
						style="text-align: right"
						onblur="onBlur(this);chkDigitAmnt(this);if(chkForsum100(this)!=false && totalPercentage(this,'txtPercentage','Total Percentage cannot be greater than 100','hidFamilyGridSize')!=false){}" maxlength="3"
						 onkeypress="amountFormat(this);"
						value="${NomineeDtlsVO[2]}"></td>
						<td class="tds" align="center" >
						<input type="text" style="text-transform: uppercase" name="txtNameOfAltrNominee" id="txtNameOfAltrNominee${Counter.index}" size="30" value="${NomineeDtlsVO[8]}"  onfocus="onFocus(this)"  onblur="onBlur(this);isName(this,'Please enter valid Name');" onkeypress="upperCase(event)">
						</td>
				<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this,'tblNomineeDtls')" /></td>
				</tr>
				<script>
						document.getElementById("hidNomGridSize").value=Number('${Counter.index}') + 1;
					</script>
			</c:forEach>
		</c:if>
		<c:if test="${resValue.lLstPnsnrNomineeDtlsDrft !=null}">
		<c:forEach var="NomineeDtlsVO"
				items="${resValue.lLstPnsnrNomineeDtlsDrft}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="hidden" name="hdnNomineeMemberId" id="hdnNomineeMemberId${Counter.index}" value="${NomineeDtlsVO.pnsnrNomineeId}">
					<input type="text" style="text-transform: uppercase" name="txtNameOfNominee" id="txtNameOfNominee${Counter.index}" size="30" value="${NomineeDtlsVO.name}"  onfocus="onFocus(this)"  onblur="onBlur(this);isName(this,'Please enter valid Name');" onkeypress="upperCase(event)">
					</td>
				<td class="tds" align="center"><select name="cmbNomineeMemRelation"
						id="cmbNomineeMemRelation${Counter.index}">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="relation" items="${resValue.lLstRelation}">
				         <c:choose>
				            <c:when test="${relation.lookupShortName == NomineeDtlsVO.relation}">
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
					<td class="tds" align="center" ><input type="text"
						name="txtNomPercentage" id="txtNomPercentage${Counter.index}" size="10"
						style="text-align: right"
						onblur="onBlur(this);chkDigitAmnt(this);if(chkForsum100(this)!=false && totalPercentage(this,'txtPercentage','Total Percentage cannot be greater than 100','hidFamilyGridSize')!=false){}" maxlength="3"
						 onkeypress="amountFormat(this);"
						value="${NomineeDtlsVO.percentage}"></td>
						<td class="tds" align="center" >
						<input type="text" style="text-transform: uppercase" name="txtNameOfAltrNominee" id="txtNameOfAltrNominee${Counter.index}" size="30" value="${NomineeDtlsVO.altrName}"  onfocus="onFocus(this)"  onblur="onBlur(this);isName(this,'Please enter valid Name');" onkeypress="upperCase(event)">
						</td>
				<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this,'tblNomineeDtls')" /></td>
				</tr>
				<script>
						document.getElementById("hidNomGridSize").value=Number('${Counter.index}') + 1;
					</script>
			</c:forEach>
		</c:if>

</table>
</div>
</fieldset>
<%
}
catch (Exception e) {
	e.printStackTrace();
}
%>
