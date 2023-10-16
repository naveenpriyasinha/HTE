<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<script>
function cancel()
{
	self.location.href = 'ifms.htm?actionFlag=revertAcceptedContri&elementId=700157';
}
function getApprovedBillGroups()
{
	if(document.getElementById("cmbDDOCode").value != -1 && document.getElementById("cmbMonth").value != -1 && document.getElementById("cmbYear").value != -1)
	{
		
		var cmbDDOCode  = document.getElementById("cmbDDOCode").value;
		var cmbMonth = document.getElementById("cmbMonth").value;
		var cmbYear = document.getElementById("cmbYear").value;
		var uri ="ifms.htm?actionFlag=getApprovedBillGroupsForDdo";
		var url = "cmbDDOCode="+cmbDDOCode+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear;
		
	  	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForGettingApprovedBillGroups(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	}
}

function dataStateChangedForGettingApprovedBillGroups(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('item');
	document.getElementById('cmbBillGroup').options.length = 0;
	
	for(var j = 0;j<XmlHiddenValues.length;j++)
	{
		var billGroupId =  XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
		var billGroupDesc =  XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
		var optn = document.createElement("OPTION");
		optn.value = billGroupId;
		optn.text = billGroupDesc;
		document.getElementById("cmbBillGroup").options.add(optn);
	}
}
function OpenContriEntryPage(selectedRowIndex)
{
	var selectedRow = selectedRowIndex ;
	var Use = "ViewReverted" ;
	var User = "TO" ;
	var treasuryCode = document.getElementById("hdnTreasuryCode").value;
	var cmbDDOCode = document.getElementById("hdnDDOCode"+selectedRow).value;
	var cmbBillGroup = document.getElementById("hdnBillGroup"+selectedRow).value;
	var cmbBillGroupDesc = document.getElementById("hdnBillGroupDesc"+selectedRow).value;
	var cmbMonth = document.getElementById("hdnMonth"+selectedRow).value;
	var cmbYear = document.getElementById("hdnYear"+selectedRow).value;
	var txtSchemeName = document.getElementById("hdnSchemeName"+selectedRow).value;
	var schemeCode = document.getElementById("hdnSchemeCode"+selectedRow).value;
	self.location.href = "ifms.htm?actionFlag=loadOfflineDCPSForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup+
			"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&schemeCode="+schemeCode+"&treasuryCode="+treasuryCode+"&User="+User+"&Use="+Use+"&cmbBillGroupDesc="+cmbBillGroupDesc ;
}
function revertRequest()
{
	var voucherNo = document.getElementById("txtVoucherNo").value ;
	var cmbTreasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	var txtReasonForRevert = document.getElementById("txtReasonForRevert").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;

	if(cmbDDOCode == -1)
	{
		alert('Please Select DDO');
		return false ;
	}

	if(cmbMonth == -1)
	{
		alert('Please Select Month');
		return false ;
	}

	if(cmbYear == -1)
	{
		alert('Please Select Year');
		return false ;
	}
	
	if(cmbBillGroup == -1)
	{
		alert('Please Select Bill Group');
		return false ;
	}

	if(txtReasonForRevert == "")
	{
		alert('Please Enter Reason for Reversion');
		return false ;
	}

	var uri="ifms.htm?actionFlag=revertAcceptedContri";
	var url="revertRequest=yes&voucherNo="+voucherNo+"&cmbTreasuryCode="+cmbTreasuryCode+
		"&cmbBillGroup="+cmbBillGroup+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear + "&txtReasonForRevert="+txtReasonForRevert;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForRevertRequest(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function dataStateChangedForRevertRequest(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(lBlFlag=='true')
	{
		alert('Your revert request is sent to System Admin');
		self.location.href = 'ifms.htm?actionFlag=revertAcceptedContri&elementId=700157';
	}
}
function popUpVoucherDtls()
{
	var cmbTreasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	
	if(cmbTreasuryCode!="" && cmbDDOCode!="" && cmbMonth!=-1 && cmbYear!=-1 && cmbBillGroup!=-1)
	{
		var uri="ifms.htm?actionFlag=popUpVoucherDtls";
		var url="cmbTreasuryCode="+cmbTreasuryCode+"&cmbDDOCode="+cmbDDOCode+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&cmbBillGroup="+cmbBillGroup;

		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						dataStateChangedForPopupVoucherDtls(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
}

function dataStateChangedForPopupVoucherDtls(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	document.getElementById("txtSchemeName").value = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	document.getElementById("txtVoucherNo").value = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	document.getElementById("txtVoucherDate").value = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	if(XmlHiddenValues[0].childNodes[3].firstChild == null)
	{
		document.getElementById("txtReasonForRevert").value = "";
	}
	else
	{
		document.getElementById("txtReasonForRevert").value = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	}
	document.getElementById("txtSchemeName").readOnly = "readOnly" ;
	document.getElementById("txtVoucherNo").readOnly = "readOnly" ;
	document.getElementById("txtVoucherDate").readOnly = "readOnly" ;
}

</script>

<hdiits:form name="frmRevertAcceptedData" encType="multipart/form-data" validate="true" method="post">

<table id="tblMain" width="100%" align="center" cellpadding="4px" cellspacing="4px">	
<tr>
	<td>	
		<fieldset class="tabstyle"><legend> <fmt:message key="CMN.REVERTDATA" bundle="${dcpsLables}"></fmt:message></legend>
	
		<table id="tblInfo" width="100%" align="center" cellpadding="4px" cellspacing="4px">
		
				<tr>
					<td width="25%">
						<fmt:message key="CMN.TREASURYNAME" bundle="${dcpsLables}"></fmt:message>
				</td>	
			<td width="25%">
				<select name="cmbTreasuryCode" id="cmbTreasuryCode" style="width: 100%" onChange="popUpVoucherDtls();">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="Treasuries" items="${resValue.TREASURIES}" >
								<option value="${Treasuries.id}" selected="selected"><c:out value="${Treasuries.desc}"></c:out></option>
					</c:forEach>	
				</select>
			</td>
			<td width="15%">
				<fmt:message key="CMN.DDONAME" bundle="${dcpsLables}"></fmt:message>
			</td>	
			<td width="35%">
					<select name="cmbDDOCode" id="cmbDDOCode" style="width:100%" onChange="getApprovedBillGroups();popUpVoucherDtls();" >
					<c:forEach var="ddoname" items="${resValue.DDONAMES}" >
								<option value="${ddoname.id}" title="${ddoname.desc}" ><c:out value="${ddoname.desc}"></c:out></option>
					</c:forEach>	
					</select>
			</td>
			
		</tr>
		
		<tr>	
			<td width="25%">
				<fmt:message key="CMN.PAYMONTH" bundle="${dcpsLables}"></fmt:message>
			</td>	
			<td width="25%">
				<select name="cmbMonth" id="cmbMonth" style="width:40%"  onChange="getApprovedBillGroups();popUpVoucherDtls();" >
													<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
														<c:forEach var="month" items="${resValue.MONTHS}" >
																		<option value="${month.id}"><c:out value="${month.desc}"></c:out></option>
														</c:forEach>
				</select>
			</td>
			<td width="15%">
				<fmt:message key="CMN.PAYYEAR" bundle="${dcpsLables}"></fmt:message>
			</td>	
			<td width="35%">
				<select name="cmbYear" id="cmbYear" style="width:40%"  onChange="getApprovedBillGroups();popUpVoucherDtls();" >
												<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
													<c:forEach var="year" items="${resValue.YEARS}" >
																<option value="${year.id}"><c:out 
																		value="${year.desc}"></c:out></option>
																		
													</c:forEach>
											    </select>
			</td>
		</tr>
	
		<tr>	
			<td width="25%"> 
				<fmt:message key="CMN.BILLGROUP" bundle="${dcpsLables}"></fmt:message>
			</td>	
			<td width="25%">
				<select name="cmbBillGroup" id="cmbBillGroup" style="width:240px"  onChange="popUpVoucherDtls();">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
				</select>
			</td>
			<td width="15%">
				<fmt:message key="CMN.SCHEMEDESCRIPTION" bundle="${dcpsLables}"></fmt:message>
			</td>	
			<td width="35%">	
				<input type="text" name="txtSchemeName" id="txtSchemeName"  />
			</td>
		</tr>
	
		<tr>
			<td width="25%">
				<fmt:message key="CMN.VOUCHERNO" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="25%">
				<input type="text" name="txtVoucherNo" id="txtVoucherNo"  />
			</td>	
			<td width="15%">
				<fmt:message key="CMN.VOUCHERDATE" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="35%" align="left"><input type="text"
						name="txtVoucherDate" id="txtVoucherDate" maxlength="10"
						onkeypress="digitFormat(this);dateFormat(this);"
						onBlur="" value="" /> 
			</td>
		</tr>
		<tr>
			<td width="25%">
				<fmt:message key="CMN.REASONFORREVERT" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="25%">
				<input type="text" name="txtReasonForRevert" id="txtReasonForRevert" style="width:100%" />
			</td>	
			
		</tr>
	
	</table>	
	</fieldset>
	<br/>
	<div align="center">
		<hdiits:button type="button" captionid="BTN.REVERTREQUEST" bundle="${dcpsLables}" name="btnRevertRequest" id="btnRevertRequest" style="width:200px" onclick="revertRequest();"/>
		<hdiits:button type="button" captionid="BTN.CANCEL" bundle="${dcpsLables}" name="btnCanel" id="btnCanel" onclick="cancel();"/>
	</div>
	<br/>
		
</td>
</tr>
</table>

<fieldset class="tabstyle"><legend> <b><fmt:message
				key="CMN.ALLREVERTREQUESTS" bundle="${dcpsLables}"></fmt:message></b> </legend>
	
	<br/>
	<label style="color: red;">Click on the Voucher Number to Change Contribution details.</label>	
	<br/>
	<br/>
	
	<input type="hidden" id="hdnTreasuryCode" value="${resValue.treasuryCode}"/>
	<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
	
	<c:if test="${resValue.listReversionRequests != null}">
		  			<div style="width:100%;overflow:auto;height:400px;" >	
						<display:table list="${resValue.listReversionRequests}" size="10"  id="VO" pagesize="10" cellpadding="5" style="width:100%" requestURI="" >
						<display:setProperty name="paging.banner.placement" value="bottom" />
						<c:choose>
						
							<c:when test="${VO[9] == -2}">
								<display:column style="text-align: left;"   titleKey="CMN.EMPSRNO" headerClass="datatableheader" sortable="true" ><font style="color:red"><c:out value="${VO_rowNum}"/></font></display:column>
								<display:column style="text-align: left;"   titleKey="CMN.DDOCODE" headerClass="datatableheader"  sortable="true" ><font style="color:red"><c:out value="${VO[1]} "/></font></display:column>
								<display:column style="text-align: left;"   titleKey="CMN.MONTH" headerClass="datatableheader"  sortable="true" ><font style="color:red"><c:out value="${VO[2]} "/></font></display:column>
								<display:column style="text-align: left;"   titleKey="CMN.YEAR" headerClass="datatableheader"  sortable="true" ><font style="color:red"><c:out value="${VO[15]}"/></font></display:column>
								<display:column style="text-align: left;"   titleKey="CMN.BillGroup" headerClass="datatableheader"  sortable="true" ><font style="color:red"><c:out value="${VO[4]} "/></font></display:column>
								<display:column style="text-align: left;"   titleKey="CMN.Scheme" headerClass="datatableheader"  sortable="true" ><font style="color:red"><c:out value="${VO[5]} "/></font></display:column>
							</c:when>
							
							<c:when test="${VO[9] == 2}">
								<display:column style="text-align: left;"  titleKey="CMN.EMPSRNO" headerClass="datatableheader" sortable="true" ><font style="color:green;"><c:out value="${VO_rowNum}"/></font></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.DDOCODE" headerClass="datatableheader"  sortable="true" ><font style="color:green"><c:out value="${VO[1]} "/></font></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.MONTH" headerClass="datatableheader"  sortable="true" ><font style="color:green"><c:out value="${VO[2]} "/></font></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.YEAR" headerClass="datatableheader"  sortable="true" ><font style="color:green"><c:out value="${VO[15]}"/></font></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.BillGroup" headerClass="datatableheader"  sortable="true" ><font style="color:green"><c:out value="${VO[4]} "/></font></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.Scheme" headerClass="datatableheader"  sortable="true" ><font style="color:green"><c:out value="${VO[5]} "/></font></display:column>
							</c:when>	
							
							<c:otherwise>
								<display:column style="text-align: left;"  titleKey="CMN.EMPSRNO" headerClass="datatableheader" sortable="true" ><c:out value="${VO_rowNum}"/></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.DDOCODE" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[1]} "/></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.MONTH" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[2]} "/></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.YEAR" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[15]} "/></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.BillGroup" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[4]} "/></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.Scheme" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[5]} "/></display:column>
							</c:otherwise>
							
						</c:choose>
						
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.VOUCHERNO" headerClass="datatableheader"  sortable="true" >
							<c:choose>
								<c:when test="${VO[9] == 2}">
									<a href=# onclick="OpenContriEntryPage('${VO_rowNum}');"><font style="color:green"><c:out value="${VO[6]}"/></font></a>
								</c:when>
								<c:when test="${VO[9] == -2}">
									<font style="color:red"><c:out value="${VO[6]}"/></font>
								</c:when>
								<c:otherwise>
									<font style="color:black;"><c:out value="${VO[6]}"/></font>
								</c:otherwise>
							</c:choose>
							
						<input type="hidden" id="hdnDDOCode${VO_rowNum}" value="${VO[1]}"/>
						<input type="hidden" id="hdnBillGroup${VO_rowNum}" value="${VO[13]}"/>
						<input type="hidden" id="hdnBillGroupDesc${VO_rowNum}" value="${VO[4]}"/>
						<input type="hidden" id="hdnMonth${VO_rowNum}" value="${VO[11]}"/>
						<input type="hidden" id="hdnYear${VO_rowNum}" value="${VO[12]}"/>
						<input type="hidden" id="hdnSchemeName${VO_rowNum}" value="${VO[10]}"/>
						<input type="hidden" id="hdnSchemeCode${VO_rowNum}" value="${VO[14]}"/>
							
						</display:column>
						
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${VO[7]}" var="voucherDate"/>
						
						<c:choose>
							<c:when test="${VO[9] == -2}">
								<display:column style="text-align: left;"  titleKey="CMN.VOUCHERDATE" headerClass="datatableheader" sortable="true" ><font style="color:red"><c:out value="${voucherDate}"/></font></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.REASONFORREVERT" headerClass="datatableheader" sortable="true" ><font style="color:red"><c:out value="${VO[8]}"/></font></display:column>
							</c:when>
							
							<c:when test="${VO[9] == 2}">
								<display:column style="text-align: left;"  titleKey="CMN.VOUCHERDATE" headerClass="datatableheader" sortable="true" ><font style="color:green"><c:out value="${voucherDate}"/></font></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.REASONFORREVERT" headerClass="datatableheader" sortable="true" ><font style="color:green"><c:out value="${VO[8]}"/></font></display:column>
							</c:when>
							<c:otherwise>
								<display:column style="text-align: left;"  titleKey="CMN.VOUCHERDATE" headerClass="datatableheader" sortable="true" ><c:out value="${voucherDate}"/></display:column>
								<display:column style="text-align: left;"  titleKey="CMN.REASONFORREVERT" headerClass="datatableheader" sortable="true" ><c:out value="${VO[8]}"/></display:column>
							</c:otherwise>
						</c:choose>
						
						<display:column headerClass="datatableheader" style="text-align:center;"  sortable="true"  titleKey="CMN.STATUS" >
						
							<c:if test="${VO[9] == -2}">
							    <label style="color: red;">Rejected</label>
							</c:if>
							<c:if test="${VO[9] == -1}">
								<label style="color: black;">Pending for Approval</label>
							</c:if>
							<c:if test="${VO[9] == 2}">
								<label style="color: green;">Approved</label>
							</c:if>
							
							<script>
										document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
							</script>
						</display:column>
						</display:table>
					</div>
	</c:if>
</fieldset>
</hdiits:form>

<ajax:select source="cmbTreasuryCode" target="cmbDDOCode"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOsForTreasury"
	parameters="cmbTreasuryCode={cmbTreasuryCode}">
</ajax:select>

