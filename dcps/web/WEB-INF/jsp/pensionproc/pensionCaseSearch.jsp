<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />
<fmt:setBundle basename="resources.pensionproc.PensionCaseConstants" var="pensionConstants" scope="request" />
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/login/ajaxLoadContentSubmitPage_1.0.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="pensionFrwdCases" value="${resValue.lLstPensionCases}"></c:set>
<c:set var="UpperUserList" value="${resValue.lLstUpperUsers}"></c:set>
<c:set var="LowerUserList" value="${resValue.lLstLowerUsers}" />
<c:set var="VOList" value="${resValue.lLstPensionCases}" />
<c:set var="DraftFlag" value="${resValue.DraftFlag}" />
<c:set var="lStrRole" value="${resValue.lStrRole}" />
<c:set var="lStrCasesFrom" value="${resValue.lStrCasesFrom}" />
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<hdiits:form name="getInwardCases" validate="" method="post">
<input type="hidden" name="pensionFrwdCases" id="pensionFrwdCases" value="${pensionFrwdCases}" ></input>
<input type="hidden" name="UpperUserList"  id="UpperUserList" value="${UpperUserList}" ></input>
<input type="hidden" name="LowerUserList"  id="LowerUserList" value="${LowerUserList}" ></input>
<input type="hidden" name="DraftFlag"  id="DraftFlag" value="${DraftFlag}" ></input>
<input type="hidden" name="CasesFrom"  id="CasesFrom" value="${lStrCasesFrom}" ></input>
<fieldset style="width: 100%" class="tabstyle" id="fsPensionCasesSearch">
<legend id="headingMsg"><b> <fmt:message
	key="PPROC.SEARCH" bundle="${pensionLabels}"></fmt:message></b></legend>
<table width="60%" align="right">
	<tr>
		<td width="10%"><fmt:message key="PPROC.SEARCH" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%">
		<select name="cmbSearchBy" id="cmbSearchBy" style="width: 80%" onchange="showDtPic()" onfocus="onFocus(this)" onblur="onBlur(this);">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<option value="r.Inward_No"> <fmt:message key="PPROC.INWARDNO" bundle="${pensionLabels}"></fmt:message> </option>
			<option value="r.Inward_Date"> <fmt:message key="PPROC.INWARDDATE" bundle="${pensionLabels}"></fmt:message> </option>
			<option value="r.Inward_Type"> <fmt:message key="PPROC.INWARDTYPE" bundle="${pensionLabels}"></fmt:message> </option>
			<option value="r.Retirement_Date"> <fmt:message key="PPROC.RETIREMENTDATE" bundle="${pensionLabels}"></fmt:message> </option>
			<option value="r.Pension_Type"> <fmt:message key="PPROC.PENSIONTYPE" bundle="${pensionLabels}"></fmt:message> </option>
			<option value="r.Bank_Branch_Name"> <fmt:message key="PPROC.BANKBRANCHNAME" bundle="${pensionLabels}"></fmt:message>  </option>
		
			<option value="r.Sevarth_Id"> <fmt:message key="PPROC.SEVAARTHID" bundle="${pensionLabels}"></fmt:message> </option>
			<option value="r.Name"> <fmt:message key="PPROC.NAMEOFPENSIONER" bundle="${pensionLabels}"></fmt:message> </option>
	
		</select>
		</td>
		
		<td width="7%" id="SearchVal" style="">
			<input type="text" id="txtSearchVal" size="15" name="txtSearchVal" onfocus="onFocus(this)" onblur="onBlur(this)" />
		</td>
		<td width="15%" id="dtpicker" style="display:none">
		 	<input type="text" id="txtSearchValDt" size="10" maxlength="10" name="txtSearchValDt" onkeypress="digitFormat(this);dateFormat(this);" onfocus="onFocus(this);" onblur="onBlur(this);chkValidDate(this);" />
			<img src="images/CalendarImages/ico-calendar.gif"  width="20" onClick="window_open(event,'txtSearchValDt',375,570,'','',0)" >
		</td>
		<td width="25%" id="InwardType" style="display:none">
		<select name="cmbCaseType" id="cmbCaseType" style="width:90%" onfocus="onFocus(this)" onblur="onBlur(this);">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<option value="<fmt:message key="CASETYPE.NEW" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="CASETYPE.NEW" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="CASETYPE.REVISION" bundle="${pensionConstants}"/>"><fmt:message key="CASETYPE.REVISION" bundle="${pensionLabels}"/></option>
		</select>				
		</td>
		<td id="PensionType" style="display:none" width="35%">
		<select name="cmbClassOfPnsn" id="cmbClassOfPnsn" style="width:85%" onfocus="onFocus(this)" onblur="onBlur(this);"  >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<option value="<fmt:message key="PPROC.ABSORPTION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPASSIONATE" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPENSATION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPULSORY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.EXTRAORDINARY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.FAMILYPNSN" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.INVALID" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.RETIRING105" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.RETIRING104" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.SUPERANNU" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.VOLUNTARY64" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.VOLUNTARY65" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.INJURY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.GALLANTRY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/></option>
		</select>				
		</td>
		<td id="BankName" style="display:none" width="30%">
		<fmt:message key="PPROC.BANK" bundle="${pensionLabels}"></fmt:message>
		<select name="cmbBankName" id="cmbBankName" style="width:80%" onfocus="onFocus(this)" onblur="onBlur(this);"  >
				 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				      <c:forEach var="BankNames" items="${resValue.lLstBankNames}">				       
				         	<option value="${BankNames.id}"><c:out value="${BankNames.desc}"/></option>				       
				      </c:forEach>
		</select>
		</td>	
		<td id="BranchName" style="display:none" width="30%">
		<fmt:message key="PPROC.BRANCH" bundle="${pensionLabels}"></fmt:message>			
		<select name="cmbTargetBranchName" id="cmbTargetBranchName" style="width:80%" onfocus="onFocus(this)" onblur="onBlur(this);">
				    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				      <c:forEach var="branchList" items="${resValue.lLstBankBranch}">				       
				         	<option value="${branchList.id}"><c:out value="${branchList.desc}"/></option>				         
				      </c:forEach>
		</select>
		
		
		<td width="1%" align="left">
		<a href="#" onclick="viewPensionCaseList(1);"><img src="images/search.gif" align="right"/></a>
		</td>
		<td width="25%" align="left"></td>
	</tr>
</table>
</fieldset>
<div>&nbsp;</div>
<div id="fsPensionCaseAdvSearch"  style="display:none"  id="fsPensionCaseAdvSearch" >
<fieldset style="width: 100%" class="tabstyle" ><legend id="headingMsg"><b>
<fmt:message key="PPROC.SEARCHRECEIVECASESFRMDEO"
	bundle="${pensionLabels}"></fmt:message></b></legend>
	<div>&nbsp;</div>
<table width="90%" align="left">
	
	<tr>
		<td width="10%" align="left"><fmt:message key="PPROC.DATEFROM"
			bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="40%" align="left"><input type="text"
			name="txtSearchFromDate" id="txtSearchFromDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);"
			onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
			onblur="onBlur(this);chkValidDate(this);" /> <img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open(event,"txtSearchFromDate",375,570)'
			style="cursor: pointer;" ${disabled}/>
		
			</td>
		<td width="10%" align="left"><fmt:message key="PPROC.TO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="40%" align="left"><input type="text"
			name="txtSearchToDate" id="txtSearchToDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);"
			onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
			onblur="onBlur(this);chkValidDate(this);" /> <img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open(event,"txtSearchToDate",375,570)'
			style="cursor: pointer;" ${disabled}/>
		
			</td>
			
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.SEVAARTHID"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text"
			name='txtSevaarthId' id="txtSevaarthId" maxlength="11" onfocus="onFocus(this)"
			onblur="onBlur(this);" />
			
		</td>
		<td width="20%" align="left"><fmt:message key="PPROC.PPONO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" name='txtPPONo'
			id="txtPPONo" onfocus="onFocus(this)" onblur="onBlur(this);" />
			
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.INWARDNO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text"
			name='txtInwardNo' id="txtInwardNo" onfocus="onFocus(this)"
			onblur="onBlur(this);" />
			
			</td>
		<td width="20%" align="left"><fmt:message
			key="PPROC.DATEOFRETIREMENT" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
		 <input type="text" name="txtDateOfRetiremt" id="txtDateOfRetiremt"
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onKeyPress="numberFormat(event)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open(event,"txtDateOfRetiremt",375,570)'
					style="cursor: pointer;" ${disabled}/>
				
			</td>
	</tr>

	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.NAME"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" id="txtName"
			style="text-transform: uppercase" size="20" name="txtName"
			onfocus="onFocus(this)"
			onblur="onBlur(this);isName(this,'Please enter valid Name');" />
			
		</td>
		<td width="20%" align="left"><fmt:message key="PPROC.DEPT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><select name="cmbDepartment"
			id="cmbDepartment"  onfocus="onFocus(this)"
			onblur="onBlur(this);">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="Department" items="${resValue.lLstDepartment}" >			
						<option value="${Department.id}"><c:out value="${Department.desc}"></c:out> </option>
		  </c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.TYPEOFPNSN"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
			 <select name="cmbTypeOfPnsn" id="cmbTypeOfPnsn" onfocus="onFocus(this)" onblur="onBlur(this);" style="width: 77%"  tabindex="2" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<option value="<fmt:message key="PPROC.ABSORPTION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPASSIONATE" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPENSATION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPULSORY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.EXTRAORDINARY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.FAMILYPNSN" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.INVALID" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.RETIRING105" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.RETIRING104" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.SUPERANNU" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.VOLUNTARY64" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.VOLUNTARY65" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.INJURY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.GALLANTRY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/></option>
				</select>
		
		
	</tr>
</table>
</fieldset>
</div>
<div>&nbsp;</div>
<c:set var="counter" value="0"></c:set>
<c:set var="hdnCounter" value="0"/>
<table width="98%" id="tblFrwdCasesFromDEO">
			<tr>
				<td>			
				<display:table list="${VOList}" id="vo" requestURI="" cellpadding="5" style="width:100%" pagesize="100" export="false" partialList="true"  offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending"  >
					
				<display:setProperty name="paging.banner.placement" value="bottom" />
					<c:choose>
					<c:when test="${(DraftFlag == 'A' && lStrRole == '700001') || DraftFlag == 'D'}">
					<display:column titleKey="CMN.CHKBXEMPSELECT"	title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>"
							headerClass="datatableheader" style="text-align:center;" class="oddcentre" sortable="true">
					
						<c:choose>
						<c:when test="${vo.caseStatus == 'APRVDBYAG'}">
						
							<input type="checkbox" name="chkRequest" id="chkRequest${counter}" disabled="disabled" value="${vo.inwardPensionId}"/>
						</c:when>	
						<c:otherwise>
							<input type="checkbox" name="chkRequest" id="chkRequest${counter}"  value="${vo.inwardPensionId}"/>
						</c:otherwise>	
						</c:choose>	
					</display:column>
					</c:when>
					<c:otherwise>
					<display:column style="text-align:center" class="oddcentre" headerClass="datatableheader">
									
					</display:column>
					</c:otherwise>
					</c:choose>
					<c:choose>
					<c:when test="${lStrRole == '700002' && DraftFlag == 'A'}">
					
					<display:column titleKey="PPROC.INWARDNO" headerClass="datatableheader" class="oddcentre"
						sortable="true" style="text-align: left;">
					<c:set var="URLLink" value="ifms.htm?actionFlag=loadPensionCaseInwardForm&showReadOnly=Y&inwardId=${vo.inwardPensionId}&pensionCaseType=${vo.pensionCaseType}&pensionFlag=${vo.pensionFlag}&penDdoCode=${vo.penDdoCode}"></c:set>
				   	<a href="javascript:void(0)" id="${vo.inwardPensionId}" onclick="javascript:showCase('${URLLink}')"> 
						${vo.inwardNo}
					 </a>
				    </display:column>
					</c:when>
					<c:otherwise>
						<display:column titleKey="PPROC.INWARDNO" headerClass="datatableheader" class="oddcentre"
						sortable="true" style="text-align: left;">
					<c:set var="URLLink" value="ifms.htm?actionFlag=loadPensionCaseInwardForm&statusLookupId=${vo.caseStatus}&inwardId=${vo.inwardPensionId}&pensionCaseType=${vo.pensionCaseType}&pensionFlag=${vo.pensionFlag}&penDdoCode=${vo.penDdoCode}"></c:set>
				   	<a href="javascript:void(0)" id="${vo.inwardPensionId}" onclick="javascript:showCase('${URLLink}')"> 
						${vo.inwardNo}
					 </a>
					 <input type="hidden" id="hidInwardId${counter}" name="hidInwardId${counter}" value="${vo.inwardPensionId}" />
				    </display:column>
					</c:otherwise>
					</c:choose>
					<display:column style="height:35;text-align: left;"
						class="oddcentre" titleKey="PPROC.SEVAARTHID"
						headerClass="datatableheader" sortable="true" >
						${vo.sevaarthId}
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="oddcentre" titleKey="PPROC.NAMEOFPENSIONER"
						headerClass="datatableheader" sortable="true" >
						${vo.pnsnrName}
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="oddcentre" titleKey="PPROC.TYPEOFPENSION"
						headerClass="datatableheader" sortable="true" >
						<c:if test="${vo.pnsnType == 'ABSORPTION'}">
						<fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo.pnsnType == 'COMPASSIONATE'}">
						<fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo.pnsnType == 'COMPENSATION'}">
						<fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo.pnsnType == 'COMPULSORY'}">
						<fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo.pnsnType == 'EXTRAORDINARY'}">
						<fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo.pnsnType == 'FAMILYPNSN'}">
						<fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo.pnsnType == 'INVALID'}">
						<fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo.pnsnType == 'RETIRING105'}">
						<fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo.pnsnType == 'RETIRING104'}">
						<fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo.pnsnType == 'SUPERANNU'}">
						<fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo.pnsnType == 'VOLUNTARY64'}">
						<fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo.pnsnType == 'VOLUNTARY65'}">
						<fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo.pnsnType == 'INJURY'}">
						<fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo.pnsnType == 'GALLANTRY'}">
						<fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/>
						</c:if>
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="oddcentre" titleKey="PPROC.DEPT"
						headerClass="datatableheader" sortable="true" >
						${vo.deptName}
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="oddcentre" titleKey="PPROC.DATESTARTINGSERVICE"
						headerClass="datatableheader" sortable="true" >
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.joiningDate}"/>
								
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="oddcentre" titleKey="PPROC.DATEOFRETIREMENT"
						headerClass="datatableheader" sortable="true" >
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.retirementDate}"/>
					</display:column>	
						<c:if test="${DraftFlag != 'R'}">				
					<display:column style="height:35;text-align: center;"
						class="oddcentre" titleKey="PPROC.CASESTATUS"
						headerClass="datatableheader" sortable="true" >						
						<c:if test="${vo.caseStatus == 'DRAFT'}">
							<c:out value="Draft"></c:out>
						</c:if>
						<c:if test="${vo.caseStatus == 'FWDBYDEO'}">
							<c:out value="Fowarded to DDO"></c:out>
						</c:if>
						<c:if test="${vo.caseStatus == 'APRVDBYDDO'}">
							<c:out value="Approved By DDO"></c:out>
						</c:if>
						<c:if test="${vo.caseStatus == 'RJCTBYDDO'}">
							<c:out value="Rejected By DDO"></c:out>
						</c:if>
						<c:if test="${vo.caseStatus == 'SENDTOAG'}">
							<c:out value="Send to AG"></c:out>
						</c:if>
						<c:if test="${vo.caseStatus == 'MISBYDDO'}">
							<c:out value="Move for Correction due to approved by DDO mistake"></c:out>
						</c:if>
						<c:if test="${vo.caseStatus == 'AGQUERY'}">
							<c:out value="Move for correction due to AG query"></c:out>
						</c:if>
						<c:if test="${vo.caseStatus == 'APRVDBYAG'}">
							<c:out value="Approved By AG"></c:out>
						</c:if>
						<c:if test="${vo.caseStatus == 'FWDTOPEN'}">
							<c:out value="Forward to Pension Ast by DDO Ast"></c:out>
						</c:if>
						
						<input type="hidden" name="txtCaseStatus${counter}" id="txtCaseStatus${counter}" size="15" value="${vo.caseStatus}" />
					</display:column>
						</c:if>
					<c:if test="${DraftFlag == 'R'}">
					<display:column style="height:35;text-align: center;"
						class="oddcentre" titleKey="PPROC.RJCTBY"
						headerClass="datatableheader" sortable="true" >	
						<c:if test="${vo.caseStatus == 'RJCTBYDDO'}">
							<c:out value="Rejected By DDO"></c:out>
							</c:if>
							<c:if test="${vo.caseStatus == 'RJCTBYVRF'}">
							<c:out value="Rejected By Verifier"></c:out>
							</c:if>
							<c:if test="${vo.caseStatus == 'RJCTBYAG'}">
							<c:out value="Rejected By AG"></c:out>
							</c:if>
							</display:column>
					</c:if>
					<c:if test="${DraftFlag == 'A' && lStrRole == '700001'}">
					<display:column style="text-align:center" class="oddcentre" titleKey="PPROC.OUTWARDNO" headerClass="datatableheader">
						<input type="text" name="txtOutwardNo${counter}" id="txtOutwardNo${counter}" size="15" value="${vo.outwardNo}" maxlength="20"/>			
					</display:column>
					<display:column style="text-align:center" class="oddcentre" titleKey="PPROC.OUTWARDDATE" headerClass="datatableheader">
						<input type="text" name="txtOutwardDate${counter}" maxlength="10" id="txtOutwardDate${counter}" size="12" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${vo.outwardDate}"/>"						
							onKeyPress="numberFormat(event);" onfocus="onFocus(this)"	onblur="onBlur(this);chkValidDate(this);"/><img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open(event,"txtOutwardDate${counter}",375,570)' style="cursor: pointer;" /> 
									
					</display:column>
					</c:if>
					<c:set var="counter" value="${counter+1}"></c:set>
				</display:table>
					<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/>
					<input type="hidden" id="hdnCounter" name="hdnCounter" value="${hdnCounter}"/>
				</td>
			</tr>
		</table>
<div>&nbsp;</div>


<table width="80%" align="center">

	<tr>
	<td>
	</td>
		<td width="50%" align="center">
			
			<hdiits:button name="btnFind" id="btnFind" type="button" style="display: none"	captionid="PPROC.SEARCH" bundle="${pensionLabels}" onclick="viewPensionCaseList(2)" />
		    <hdiits:button name="btnReset" id="btnReset" type="button"	style="display: none" captionid="PPROC.RESET" bundle="${pensionLabels}"	onclick="" /> 
		    
		    <c:choose>
			<c:when test="${DraftFlag == 'A' && lStrRole == '700002'}">
		    	<hdiits:button name="btnBack" id="btnBack" type="button"	style="display: none" captionid="PPROC.BACK" bundle="${pensionLabels}"	onclick="back(2)" />
		 	</c:when>
		 	<c:otherwise>
		    	<hdiits:button name="btnBack" id="btnBack" type="button"	style="display: none" captionid="PPROC.BACK" bundle="${pensionLabels}"	onclick="back(1)" />
		 	</c:otherwise> 
		 	</c:choose>
		 	<c:if test="${DraftFlag == 'D'}">
		 	<hdiits:button name="btnDelete"	type="button" captionid="PPROC.DELETE" bundle="${pensionLabels}" onclick="deleteDraft();" />
		 	</c:if>
		 	
		 	
		 	<hdiits:button name="btnClose"	type="button" captionid="PPROC.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
		 	
		 </td>
		<td>
		</td>
	</tr>

</table>
</hdiits:form>
<script type="text/javascript">
function updateStatus(updateFlag)
{
	var flag = 0;
	var InwardId = "";
	var url = "";
	var outwardNoForAg="";
	var outwardDateForAg="";
	
	var totalRequests= document.getElementById("totalCount").value ;

	var arrChkBox=document.getElementsByName("chkRequest");

	if(arrChkBox.length > 0)
	{
		for(var k=0;k<totalRequests;k++)
		{
			if(document.getElementById("chkRequest"+k).checked )
			{				
				InwardId = InwardId + document.getElementById("hidInwardId"+k).value + ",";
				flag++;
			}
		}
	}else{
		alert('No records to update');
		return;
	}
	if(flag == 0)
	{
		alert('Please select at least one request');
		return;
	}else{
		if(updateFlag == 'S'){			

			if(arrChkBox!=null)
			{
				if(arrChkBox.length > 0)
				{						
					for(var i=0;i<arrChkBox.length;i++)
					{
						if(arrChkBox[i].checked == true)
						{	
							var OutwardNo = document.getElementById('txtOutwardNo'+i).value.trim();
							var OutwardDate =  document.getElementById('txtOutwardDate'+i).value.trim();

							if(OutwardNo == ""){
								alert("Please enter Outward No");
								document.getElementById('txtOutwardNo'+i).focus();
								return false;
							 }
							if(OutwardDate == ""){
								alert("Please enter Outward Date");
								document.getElementById('txtOutwardDate'+i).focus();
								return false;
							 }

							outwardNoForAg=outwardNoForAg + document.getElementById("txtOutwardNo"+i).value.trim()+",";
							outwardDateForAg=outwardDateForAg + document.getElementById("txtOutwardDate"+i).value.trim()+",";

							url = "&InwardId="+InwardId+"&updateFlag="+updateFlag+"&outwardNoForAg="+outwardNoForAg+"&outwardDateForAg="+outwardDateForAg;
						}
					}
				}
			}
		}else if(updateFlag == 'M'){
			url = "&InwardId="+InwardId+"&updateFlag="+updateFlag;
		}else{
			url = "&InwardId="+InwardId+"&updateFlag="+updateFlag;
		}

		var uri = "ifms.htm?actionFlag=updateCaseStatus";
	
		var myAjax = new Ajax.Request(uri,
					{
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {CaseStateChanged(myAjax);},
				        onFailure: function(){ alert('Something went wrong...');} 
					});
	}
}
function CaseStateChanged(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;	
	if (lblSaveFlag=="true") {
		
		alert('Request updated successfully');		
		self.location.href="ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=A&elementId=376404";
	}

}

function deleteDraft()
{
	var finalSelectedEmployee = 0;
	var totalEmployees = document.getElementById("totalCount").value ;
	//alert('totalEmployees'+totalEmployees);
	var totalSelectedDrafts = 0;
	var empInwardIds = "";
	var hidDraftOrRejected ;

	var arrChkBox=document.getElementsByName("chkRequest");

	if(arrChkBox.length > 0)
	{
		//alert('Checkbox is checked');
		for(var k=0;k<totalEmployees;k++)
		{
			//alert('Inside for');
			if(document.getElementById("chkRequest"+k).checked )
				
			{		
				//alert('Inside if');		
				empInwardIds = empInwardIds + document.getElementById("hidInwardId"+k).value + "~";
				//alert('Inside if empInwardIds'+empInwardIds);	
				totalSelectedDrafts++;	
				////alert('Inside if totalSelectedDrafts'+totalSelectedDrafts);	
			}
		}
	}else{
		alert('Please select at least one draft');
		return false; 
	}
	
	
	
	//if(totalSelectedDrafts > 1)
	//	{
	//		alert('Please select only one draft');
	//		return false; 
	//	}
	
	//for(var m=1;m<=totalEmployees;m++)
	//{
	//	if(document.getElementById("chkRequest"+k).checked)
	//	{
	//		empInwardIds = empInwardIds + document.getElementById("hidInwardId"+k).value.trim() + "~" ;
	//	}
	//}
	
	var uri = "ifms.htm?actionFlag=deletePensionCaseDraft";
	var url = "empInwardIds="+empInwardIds;
	
	var answer = confirm("Are you sure you want to delete this draft?");
	if(answer)
	{
		 //showProgressbar();
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						dataStateChangedForDelDraft(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
	

}

function dataStateChangedForDelDraft(myAjax)
{
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
		var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
								
		if (successFlag=='true') {
			alert('Selected drafts are deleted');
			//hideProgressbar();
			self.location.href = "ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=D";
			//self.location.href = "ifms.htm?actionFlag=viewDraftForms&User=ZPDDOAsst&elementId=700233";
		}
}

function checkUncheckAll(theElement,name) 
{
	var theForm = theElement.form;
	
	for(var z = 0; z < theForm.length; z++)
	{
		if(theForm[z].type == 'checkbox' && theForm[z].name != name)
		{
			theForm[z].checked = theElement.checked;
  		}
    }
}
</script>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchFormBankCode" source="cmbBankName" target="cmbTargetBranchName" parameters="bank={cmbBankName}"></ajax:select>