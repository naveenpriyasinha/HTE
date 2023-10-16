<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<fmt:setBundle basename="resources.dcps.dcpsAlerts" var="dcpsAlerts" scope="request" />
<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript"  src="script/dppf/InwardPension.js"></script>
<script type="text/javascript"  src="script/dppf/pensionerDet.js"></script>
<script type="text/javascript"  src="script/dppf/pensionerDtls.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/dcps/dcpsArrearsTierI.js"></script>
<script type="text/javascript">
ARREARTYPE='<fmt:message key="DCPS.ARREARTYPE" bundle="${dcpsAlerts}"></fmt:message>';
DEPT='<fmt:message key="DCPS.DEPT" bundle="${dcpsAlerts}"></fmt:message>';
DESG='<fmt:message key="DCPS.DESG" bundle="${dcpsAlerts}"></fmt:message>';
FROMDATE='<fmt:message key="DCPS.FROMDATE" bundle="${dcpsAlerts}"></fmt:message>';
TODATE='<fmt:message key="DCPS.TODATE" bundle="${dcpsAlerts}"></fmt:message>';
TOTALAMT='<fmt:message key="DCPS.TOTALAMT" bundle="${dcpsAlerts}"></fmt:message>';
NOOFINSTLMNT='<fmt:message key="DCPS.NOOFINSTLMNT" bundle="${dcpsAlerts}"></fmt:message>';
TOTALMONTH='<fmt:message key="DCPS.TOTALMONTH" bundle="${dcpsAlerts}"></fmt:message>';
FSTODD='<fmt:message key="DCPS.FSTODD" bundle="${dcpsAlerts}"></fmt:message>';
LASTODD='<fmt:message key="DCPS.LASTODD" bundle="${dcpsAlerts}"></fmt:message>';
EMPID='<fmt:message key="DCPS.EMPID" bundle="${dcpsAlerts}"></fmt:message>';
</script>
<hdiits:form name="frmTierICntrnbtn" encType="multipart/form-data" validate="true" method="post">
<input type="hidden" name="empIDs" id="empIDs" ></input>
<input type="hidden" name="empNames" id="empNames"></input>
<input type="hidden" name="empDtlsSize" id="empDtlsSize" ></input>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TypeOfArrear" value="${resValue.lLstTypeOfArrear}"></c:set>
<c:set var="TierDraftList" value="${resValue.TierDraftList}"></c:set>
<fieldset class="tabstyle">
<legend id="tierHeader"><b>Tier - I Contribution </b></legend>
	<table cellpadding = "4" cellspacing="4" align = "center" width="80%">
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.TYPESOFARREARS"
				bundle="${dcpsLables}">
			</fmt:message></td>
			<td width="20%" align="left">
			<select name="cmbTypeOfArrear" id="cmbTypeOfArrear"  onfocus="onFocus(this)" onblur="onBlur(this);" colspan="3">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>   
				<c:forEach var="TypeOfArrear" items="${resValue.lLstTypeOfArrear}">
				       	<option value="${TypeOfArrear.lookupId}"><c:out value="${TypeOfArrear.lookupName}"></c:out>
						</option>
					</c:forEach>     
			</select>
			</td>		
			
			
		</tr>
		
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.PARENTFIELDDEPARTMENT"
				bundle="${dcpsLables}">
			</fmt:message></td>
			<td width="20%" align="left" colspan="3">
			<select name="cmbFieldDept" id="cmbFieldDept"  onfocus="onFocus(this)" onblur="onBlur(this);" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>  
				<c:forEach var="dept" items="${resValue.lLstDepartment}">
							<option  value="${dept.id}">
								<c:out value="${dept.desc}"></c:out>
							</option>
					</c:forEach>      
			</select>
			<td width="20%" align="left">
			</td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.DESIGNATION"
				bundle="${dcpsLables}">
			</fmt:message></td>
			<td width="20%" align="left" colspan="3">
			<select name="cmbDesignation" id="cmbDesignation" onfocus="onFocus(this)" onblur="onBlur(this);" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>     
				<c:forEach var="desg" items="${resValue.lLstDesignation}">					
							<option value="${desg.id}">
								<c:out value="${desg.desc}"></c:out>									
							</option>							
				</c:forEach>			   
			</select>
			<td width="20%" align="left">
			</td>
			<td width="20%" align="left">
			</td>
		</tr>
		<tr>
			<td width="20%" align="left">
			<fmt:message key="CMN.DATE"	bundle="${dcpsLables}"></fmt:message>
			<fmt:message key="CMN.FROM"	bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left">
			<input type="text"
			name="txtFromDate" id="txtFromDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);"
			onKeyPress="numberFormat(this)" onfocus="onFocus(this)"
			onblur="onBlur(this);validateDate(this);" /> <img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtFromDate",375,570)'
			style="cursor: pointer;" ${disabled}/>
			</td>			
			
			<td width="20%" align="left">
			<fmt:message key="CMN.DATE"	bundle="${dcpsLables}"></fmt:message>
			<fmt:message key="CMN.TO"	bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left">
			<input type="text"
			name="txtToDate" id="txtToDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);"
			onKeyPress="numberFormat(this)" onfocus="onFocus(this)"
			onblur="onBlur(this);validateDate(this);" /> <img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtToDate",375,570)'
			style="cursor: pointer;" ${disabled}/>
			
			</td>
		</tr>
		
		<tr>
			<td width="20%" align="left">
			<fmt:message key="CMN.FORALLEMPLOYEES"	bundle="${dcpsLables}"></fmt:message>
			
			</td>
			<td width="20%" align="left">
			<input type="checkbox" name="chkEmployee" id="chkEmployee" />		
		
				
				
		</tr>
		
		<tr>
			<td width="20%" align="left">
				<fmt:message key="CMN.EMPLOYEEID"	bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name='txtEmployeeId' id='txtEmployeeId' onblur="showEmpNameFromId();" /></td>	
			<td width="20%" align="left">
				<fmt:message key="CMN.EMPLOYEENAME"	bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name='txtEmployeeName' id='txtEmployeeName' readonly="readonly" />
			</td>
				
		</tr>
		
		<tr>
			<td width="20%" align="left">
			<fmt:message key="CMN.TOTALAMOUNT"	bundle="${dcpsLables}"></fmt:message>
			
			</td>
			<td width="20%" align="left">
			<input type="text" name='txtTotalAmt' id='txtTotalAmt' onKeyPress="numberFormat(this)"  /></td>
		
			<td width="20%" align="left">
			<fmt:message key="CMN.NOOFINSTALLMENTS"	bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left">
			<input type="text" name='txtNoOfInstallment' id='txtNoOfInstallment' onKeyPress="numberFormat(this)"  /></td>
				
		</tr>
		<tr>
			<td width="20%" align="left">
			<fmt:message key="CMN.WHOLEMONTHINSTALLMENTS"	bundle="${dcpsLables}"></fmt:message>
			
			</td>
			<td width="20%" align="left" colspan="3">
			<input type="text" name='txtMonthInstlmnt' id='txtMonthInstlmnt' onKeyPress="numberFormat(this)"  /></td>
		
			
				
		</tr>
		<tr>
			<td width="20%" align="left">
			<fmt:message key="CMN.FIRSTODDINSTALLMENTAMOUNT" bundle="${dcpsLables}"></fmt:message>
			
			</td>
			<td width="20%" align="left">
			<input type="text" name='txtFirstOddInstlmnt' id='txtFirstOddInstlmnt' onKeyPress="numberFormat(this)"  /></td>
		
			<td width="20%" align="left">
			<fmt:message key="CMN.LASTODDINSTALLMENTAMOUNT"	bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="20%" align="left">
			<input type="text" name='txtLastOddInstlmnt' id='txtLastOddInstlmnt' onKeyPress="numberFormat(this)"  /></td>
				
		</tr>
		
		
		<tr>
			<td colspan = "4" align ="center">
			<hdiits:button	name="TierICntrnbtnAddRow" type="button" captionid="CMN.ADDROW"
	bundle="${dcpsLables}" onclick="TierICntrnbtnTableAddRow();getEmpOfDDODesig();" />
			</td>
		</tr>
</table>

<br></br>
<fieldset style="width: 100%" class="tabstyle">
<legend id="tableHeader"><b>Tier - I Details</b></legend>

 <input type="hidden"
	name="hidGridSize" id="hidGridSize" value="0" /> 
	<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
	<c:choose>
	<c:when test="${TierDraftList != null}">
	<display:table list="${TierDraftList}"  id="tblTierICntrnbtn"   requestURI="" export="" style="width:100%;border=1;"  pagesize="5">
	<display:setProperty name="paging.banner.placement" value="none" />	
		<display:column  headerClass="datatableheader"  title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>" >
			<input type="checkbox" name="chkbxArr" id="chkbxArr" value="${tblTierICntrnbtn[0]}"/>
		</display:column>
		
		<display:column headerClass="datatableheader"   sortable="true" titleKey="CMN.FINANCIALYR"  >		
			<input type="text" size="12" name="txtFinancialYr" id="txtFinancialYr" value="2011"  readonly="readonly" ></input> 
		</display:column>
		
		<display:column headerClass="datatableheader" sortable="true"  titleKey="CMN.EMPPNSNID" >
			<input type="text" name="txtEmpPnsnId" size="12" id="txtEmpPnsnId" value="${tblTierICntrnbtn[2]}"	 readonly="readonly" />		
		</display:column>
		
		<display:column headerClass="datatableheader"   sortable="true" titleKey="CMN.EMPNAME" >		
			<input type="text" name="txtEmpName" size="19" id="txtEmpName" value="${tblTierICntrnbtn[3]}" readonly="readonly"	/>
		</display:column>
	
		<display:column headerClass="datatableheader"  sortable="true"  titleKey="CMN.AMOUNT"  >	
			<input type="text" name="txtAmount" id="txtAmount" size="12" value="${tblTierICntrnbtn[4]}"  readonly="readonly"/> 
		</display:column>	
		
		<display:column headerClass="datatableheader"   sortable="true" titleKey="CMN.FROM" >
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${tblTierICntrnbtn[5]}" var="TierICntrbtnFromDate"/>		
			<input type="text" name="TierICntrnbtnFromDate"  size="20" id="TierICntrnbtnFromDate" value="${TierICntrbtnFromDate}" readonly="readonly" />
		</display:column>
		<display:column headerClass="datatableheader"  sortable="true" titleKey="CMN.TO" >
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${tblTierICntrnbtn[6]}" var="TierICntrbtnToDate"/>		
			<input type="text" name="TierICntrnbtnToDate" size="20" id="TierICntrnbtnToDate" value="${TierICntrbtnToDate}" readonly="readonly" />		
		</display:column>
		<display:column headerClass="datatableheader"  sortable="true" titleKey="CMN.DELETE" >
			<img name="Image" id="Image" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblTierICntrnbtn')"  />
		</display:column>
	</display:table>
	</c:when>
	<c:otherwise>
		<table id="tblTierICntrnbtn" align="left" width="98%" cellspacing="0"border="1">
			<tr class="datatableheader" style="width: 90px">
				<td width="5%" class="HLabel"><input name='chkbxArr'  type='checkbox' onclick='checkUncheckAll(this)'/></td>
				<td width="10%" class="HLabel"><fmt:message key="CMN.FINANCIALYR"
					bundle="${dcpsLables}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
				<td width="10%" class="HLabel"><fmt:message key="CMN.EMPPNSNID"
					bundle="${dcpsLables}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
				<td width="20%" class="HLabel"><fmt:message key="CMN.EMPNAME"
					bundle="${dcpsLables}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
				<td width="10%" class="HLabel"><fmt:message key="CMN.AMOUNT"
					bundle="${dcpsLables}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
				<td width="20%" class="HLabel"><fmt:message key="CMN.FROM"
					bundle="${dcpsLables}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
					<td width="20%" class="HLabel"><fmt:message key="CMN.TO"
					bundle="${dcpsLables}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
				<td width="10%" class="HLabel">&nbsp;</td>
			</tr>
		</table>
	</c:otherwise>
	</c:choose>


</div>
<table width="40%" align="center">
		<tr>

			<td width="40%" align="left">
			  <hdiits:button name="btnSave"	type="button" captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="SaveData();" />
			  <hdiits:button name="btnForwardToDdo" classcss="bigbutton" type="button" captionid="BTN.FORWARD"	bundle="${dcpsLables}" /> </td>
		</tr>
	</table>

</fieldset>

</fieldset>
</hdiits:form>