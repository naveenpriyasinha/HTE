<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels_en_US" var="pensionLabels" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/pensionpay/PensionConfig.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="AuditorNames" value="${resValue.lLstAuditorNames}"></c:set>
<c:set var="BankNames" value="${resValue.lLstBankNames}"></c:set>

<hdiits:form name="auditorMappingInfo" encType="multipart/form-data" id="auditorMappingInfo"
	validate="true" method="post">
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.AUDBNKBRNCHMAPP" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<input type="hidden" name="hidTempFlag" id="hidTempFlag" value="">
	<div id ="radioAction" align="center" style="display: block;">
		<input type="radio" name="rdAction" value="rdAdd" onclick="radioAction(this);" >&nbsp;<fmt:message key="PPMT.ADDMPNG" bundle="${pensionLabels}"></fmt:message>&nbsp;&nbsp;
		<input type="radio" name="rdAction" value="rdRemove" onclick="radioAction(this);" >&nbsp;<fmt:message key="PPMT.REMOVEMPNG" bundle="${pensionLabels}"></fmt:message>
	</div>
	
<div id="divDtls" style="display:none">	
<table width="60%" align="center">
  <tr>
		<td width="20%" align="center" >
			<fmt:message key="PPMT.AUDITORNAME" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="center">
		  <select name="cmbAuditorName" id="cmbAuditorName"   onfocus="onFocus(this)" onblur="onBlur(this);" onchange="getBnkBrnchsFromBnkCode();getMappingDtls();viewAuditorMapping();" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="AuditorNames" items="${AuditorNames}">
						<option value="${AuditorNames.id}"><c:out
							value="${AuditorNames.desc}"></c:out></option>
					</c:forEach>
		  </select>
		  
					
				
		  
		  
		</td>
		<td width="20%" align="center" >
			<fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="center">
		  <select name="cmbBankName" id="cmbBankName"  onchange="getBnkBrnchsFromBnkCode();getMappingDtls();"   >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="BankNames" items="${BankNames}">
						<option value="${BankNames.id}"><c:out
							value="${BankNames.desc}"></c:out></option>
					</c:forEach>
		  </select>
		</td>
  </tr>

</table>
  <br/><br/>
<table width="100%" align="center">
	<tr>
		<td width="5%"></td>
		<td width="20%"><fmt:message key="PPMT.AVAILBRANCHNAME" bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%"></td>
		<td width="5%"></td>
		<td width="30%"></td>
		<td width="5%"></td>
		<td width="30%"><fmt:message key="PPMT.CURRBRANCHNAME" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
   	<tr>
   		<td width="5%"></td>
 		<td  width="20%">
		<select name="cmbTargetBranchName" multiple="multiple" id="cmbTargetBranchName" style="height:100px;width:200px" >
		 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option> 
		 		<c:forEach var="branchList" items="${resValue.lLstBankBranch}">
					<option value="${branchList.id}">
							<c:out value="${branchList.desc}"></c:out>
					</option>	
				</c:forEach>		
		</select>
		</td>
		
		<td width="5%" >
			<hdiits:button name="btnMoveRight"	type="button" captionid="PPMT.MOVERIGHT" bundle="${pensionLabels}" style="width:50px" onclick="SelectBranches();"/>
			<hdiits:button name="btnMoveAllRight"	type="button" captionid="PPMT.MOVEALLRIGHT" bundle="${pensionLabels}" style="width:50px" onclick="SelectAllBranches();" />
			<hdiits:button name="btnMoveLeft"	type="button" captionid="PPMT.MOVELEFT" bundle="${pensionLabels}" style="width:50px" onclick="DeSelectBranches();" />
			<hdiits:button name="btnMoveAllLeft"	type="button" captionid="PPMT.MOVEALLLEFT" bundle="${pensionLabels}" style="width:50px" onclick="DeSelectAllBranches();" />
	    </td>
	    <td width="5%"></td>
	    <td width="30%">
		    <select name="cmbDisplayBranchName"  multiple="multiple" id="cmbDisplayBranchName" style="height:100px;width:200px">
			 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option> 
			</select>
		</td>
		
		<td width="5%"></td>
		<td width="30%">
		<!-- onfocus="temp=this.selectedIndex;" onchange="this.selectedIndex=temp;" -->
			<select name="cmbDisplayBranchNameList"  multiple="multiple" id="cmbDisplayBranchNameList"  style="height:100px;width:200px">
			 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option> 
			 		<c:forEach var="displaybranchList" items="${resValue.lLstBankBranch}">
						<option value="${displaybranchList.id}">
								<c:out value="${displaybranchList.desc}"></c:out>
						</option>	
					</c:forEach>		
			</select>
		</td>
	</tr>
</table>
</div>
</fieldset>
<br>

<table align="center">
	<tr>
		<td  id="addBtnTd" align="center" style="display:none;">
			<hdiits:button name="btnUpdate"	type="button" captionid="PPMT.UPDATE" bundle="${pensionLabels}" onclick="saveMappingData();"  />
		</td>
		<td  id="removeBtnTd" align="center" style="display:none;">
			<hdiits:button name="btnRemove"	type="button" captionid="PPMT.REMOVE" bundle="${pensionLabels}" onclick="removeMappingData();"  />
		</td>
		<td  id="viewBtnTd" align="center" style="display:none;">
			<!--<hdiits:button name="btnViewMapping" type="button" captionid="PPMT.VIEWMAPPING" bundle="${pensionLabels}" onclick="viewAuditorMapping();" />-->
		</td>
		<td>
			<hdiits:button id="btnClose" name="btnClose"  type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
		</td>
	</tr>
</table>
<br>
</hdiits:form>
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.AUDBNKBRNCHMAPPED" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<table align="center">
	<tr>
	<td>
	<table id="tblBankBrnchMpgDtlsHd" align="left" width="95%" cellspacing="0" border="1" cellpadding="0">
		  <tr class="datatableheader" >						   
			<td width="50%" class="HLabel"><fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message></th>
			<td width="50%" class="HLabel"><fmt:message key="PPMT.BANKBRNNAME" bundle="${pensionLabels}"></fmt:message></th>					   						   
		</tr>
	</table>
	</td>
	</tr>
	<tr>
	<td>
	<div style="height:200px;" class="scrollablediv">
	<table id="tblBankBrnchMpgDtls" align="left" width="95%" cellspacing="0" border="1">
	</table>
	</div>
	</td>
	</tr>
	</table>
</fieldset>	