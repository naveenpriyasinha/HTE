<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>

<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script type="text/javascript" src="script/gpf/dataEntryForm.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>

<script>
var LISTMONTHS='';
var LISTYEARS='';
</script>

<script type="text/javascript">
function getEmployeeName()
{
	var EmpCode = document.getElementById("txtEmployeeCode").value;
	var uri = 'ifms.htm?actionFlag=getEmployeeNameFromEmpCode';
	var url = '&EmpCode='+EmpCode;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	getResponseEmpName(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function getResponseEmpName(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var EmpName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var BasicPay = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	if(EmpName == "Invalid")
	{
		alert("Invalid Sevaarth Id");
		document.getElementById("txtEmployeeName").value = "";
		document.getElementById("txtEmployeeCode").focus();
		return;
	}
	document.getElementById("txtEmployeeName").value = EmpName;
	document.getElementById("hidBasicPay").value = BasicPay;
}
</script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />

<c:forEach var="MonthList" items="${resValue.lLstMonths}" >
	<script> LISTMONTHS += '<option value="${MonthList.id}"> ${MonthList.desc}</option>';</script>
</c:forEach>
<c:forEach var="YearList" items="${resValue.lLstYears}" >
	<script> LISTYEARS += '<option value="${YearList.id}"> ${YearList.desc}</option>';</script>
</c:forEach>

<c:if test="${resValue.userType == 'DDO'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
</c:if>	

<hdiits:form name="FrmDataEntry" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle"><legend><fmt:message key="CMN.EMPDETAILS" bundle="${gpfLabels}"></fmt:message></legend>

<input type="hidden" id="hidTrnAccId" name="hidTrnAccId" value="${resValue.TrnAccPk}" />
<input type="hidden" id="hidScheduleSize" name="hidScheduleSize" value="${resValue.scheduleSize}" />
<input type="hidden" id="hidBasicPay" name="hidBasicPay" />
<table width="100%">
	<tr>		
		<td width="25%">
			<fmt:message key="CMN.EMPLOYEECODE" bundle="${gpfLabels}" />			
		</td>
		<td width="25%">
			<input type="text" id="txtEmployeeCode" style="text-transform: uppercase" name="txtEmployeeCode" onBlur="getEmployeeName();" value="${resValue.SevaarthId}" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label></td>			
	
		<td width="15%">
			<fmt:message key="CMN.EMPNAME" bundle="${gpfLabels}" />			
		</td>
		<td width="35%">
			<input type="text" id="txtEmployeeName" size="30" name="txtEmployeeName" style="text-transform: uppercase" value="${resValue.Name}" readonly/>			
			<label class="mandatoryindicator" ${varImageDisabled}>*</label></td>
	</tr>
	<tr>
		<td width="25%">
			<fmt:message key="CMN.OPENBALANCEON" bundle="${gpfLabels}" />
		</td>
		<td width="25%">
			<input type="text"  size="20%" name="txtAmount" id="txtAmount" value="${resValue.OpeningBalc}" maxlength="10" onkeypress="amountFormat(this);" style="text-align: right" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%">
		</td>
		<td width="35%">
		</td>	
	</tr>
</table>
</fieldset>

<br>

<fieldset class="tabstyle"><legend><fmt:message key="CMN.SUBSCRIPTION" bundle="${gpfLabels}"></fmt:message></legend>
<table width="100%">
	<tr>
		<td width="25%">
			<fmt:message key="CMN.SUBSCRIPTIONON" bundle="${gpfLabels}" />
		</td>
		<td width="25%">
			<input type="text"  size="20%" name="txtPrevSubAmt" id="txtPrevSubAmt" value="${resValue.MonthlySub}" maxlength="10" onkeypress="amountFormat(this);" style="text-align: right" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%">
		</td>
		<td width="35%">
		</td>
	</tr>
	<tr id="trSubscription">
		<td width="25%">
			<fmt:message key="CMN.REGULARSUBFROM" bundle="${gpfLabels}" />
		</td>
		<td width="25%">
		
		 <select name="cmbYear" id="cmbYear" onchange="validateSubDate();">		 			
			<option value="24" selected="selected"><c:out value="2011"></c:out></option>
			<option value="25"><c:out value="2012"></c:out></option>			
		  </select>		
		
		 <select name="cmbMonth" id="cmbMonth" onchange="validateSubDate();">
		 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="month" items="${resValue.lLstMonths}">
				<option value="${month.id}"><c:out value="${month.desc}"></c:out></option>
			</c:forEach>
		   </select>
		   <label class="mandatoryindicator">*</label>
		</td>
		 
		<td width="15%">
			<fmt:message key="CMN.AMOUNT" bundle="${gpfLabels}" />
		</td>
		<td width="35%">
			<input type="text"  size="20%" name="txtSubAmount" id="txtSubAmount" maxlength="10" onkeypress="amountFormat(this);" onblur="validateChangeSubAmount();" style="text-align: right" />
			<label class="mandatoryindicator">*</label>
		</td>		
	</tr>
</table>

<br><br>
<center>
<c:if test="${resValue.userType == 'DEO'}">
<hdiits:button name="btnAddRowSub" id="btnAddRowSub" type="button" captionid="BTN.ADDROW" bundle="${gpfLabels}" onclick="addRowSubscription();"/>
</c:if>
</center>
<br><br>
<div style="padding-left:200px;" >
	<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:85%; height:120px; overflow: scroll; overflow-x:scroll; overflow-y:scroll; ">
	<table id="tblSubscriptionDtls" align="center" width="98%" border="1">
	
	<tr>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.SRNO" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.MONTH" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.YEAR" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.AMOUNT" bundle="${gpfLabels}" /></b></td>		
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.DELETE" bundle="${gpfLabels}" /></b></td>
	</tr>
	
	<c:choose>

			<c:when test="${resValue.SubDtls != null}">
	
				<c:forEach var="SubDtls" items="${resValue.SubDtls}" varStatus="Counter">					
					<tr>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="5" style="text-align: center" name="txtSubscriptionSrno" value="${Counter.index + 1}" readonly="readonly" />
							<input type="hidden" id="hidSubDtlsPk${Counter.index}" name="hidSubDtlsPk" value="${SubDtls[3]}" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="20" style="text-align: center" name="txtSubMonth" value="${SubDtls[1]}" readonly="readonly" />
							<input type="hidden" name="hidSubMonth" value="${SubDtls[4]}" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="20" style="text-align: center" name="txtSubYear" value="${SubDtls[2]}" readonly="readonly" />
							<input type="hidden" name="hidSubYear" value="${SubDtls[5]}" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="10" style="text-align: center" name="txtSubscrAmount" value="${SubDtls[0]}" readonly="readonly" />
						</td>	
						<c:if test="${resValue.userType == 'DEO'}">
						<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif"  onclick="deleteRow_Sub()" ${varImageDisabled}/>
						</td>
						</c:if>					
					</tr>
				</c:forEach>
			</c:when>
	</c:choose>
	</table>
	</div>
</div>	
</fieldset>

<br>

<fieldset class="tabstyle"><legend><fmt:message key="CMN.REFADVANCE" bundle="${gpfLabels}"></fmt:message></legend>
<div id="tabmenu">
     <ul id="maintab" class="shadetabs" >
	     	<li class="selected">
		    	<a href="#" rel="tcontent1">
		  			<fmt:message key="CMN.CURRENT" bundle="${gpfLabels}"></fmt:message>
		        </a>
	        </li>
	       	<li>
		        <a href="#" rel="tcontent2" style="width: 150px">
					<fmt:message key="CMN.STTLEDOF" bundle="${gpfLabels}"></fmt:message>
		        </a>
	        </li>
	 </ul>
</div>		   
<div class="tabcontentstyle" style="height:10%;">
	
		<div id="tcontent1" class="tabcontent" align="left"  style="height:80px;">
			<jsp:include page="/WEB-INF/jsp/gpf/CurrentAdvance.jsp" />
		</div>	
	
		<div id="tcontent2" class="tabcontent" align="left"  style="height:80px;">
			<jsp:include page="/WEB-INF/jsp/gpf/AdvanceHistory.jsp" />
		</div>

</div>
		     
</fieldset>

<br>

<fieldset class="tabstyle"><legend><fmt:message key="CMN.NONREFADVANCE" bundle="${gpfLabels}"></fmt:message></legend>
	<table width="100%" id="tblNonRefundable">
	<tr>
		<td width="15%">
			<fmt:message key="CMN.PURPOSECATEGORY" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="20%">
			<select name="cmbPurposeCategoryNRA" id="cmbPurposeCategoryNRA" style="width:230px">
				<option value="-1" selected="selected"><fmt:message	key="COMMON.DROPDOWN.SELECT"/></option>
				<c:forEach var="Purpose" items="${resValue.lstPurposeCatNRA}">					
						<option value="${Purpose.lookupId}">
						<c:out value="${Purpose.lookupDesc}"></c:out></option>
				</c:forEach>
			</select><label class="mandatoryindicator">*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.SANCTIONAMONUT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text"  size="20%" name="txtSancAmtNRA" id="txtSancAmtNRA" maxlength="10" onkeypress="amountFormat(this);" style="text-align: right" />
			<label class="mandatoryindicator">*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.SANCDATE" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" name="txtSancDateNRA" id="txtSancDateNRA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" />
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtSancDateNRA",375,570)' style="cursor: pointer;"/>
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.VOUCHERNO" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text"  size="20%" name="txtVchrNoNRA" id="txtVchrNoNRA"  />
			<label class="mandatoryindicator">*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.VOUCHERDATE" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" name="txtVchrDateNRA" id="txtVchrDateNRA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" />
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtVchrDateNRA",375,570)' style="cursor: pointer;"/>
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.NOOFDISBRMNTINSTLMNT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtNoOfInstlmntNRA" name="txtNoOfInstlmntNRA" onblur="validateInstNoNRA();">
			<label class="mandatoryindicator">*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.INSTLMNTAMTPERDISBRSMNT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtInstlmntAmtPerDisbrNRA" name="txtInstlmntAmtPerDisbrNRA" readonly>
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
</table>

<br><br>
<center>
<c:if test="${resValue.userType == 'DEO'}">
<hdiits:button name="btnAddRowNRA" id="btnAddRowNRA" type="button" captionid="BTN.ADDROW" bundle="${gpfLabels}" onclick="addRowNRA();"/>
</c:if>
</center>
<br><br>
<div style="padding-left:200px;" >
	<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:85%; height:120px; overflow: scroll; overflow-x:scroll; overflow-y:scroll; ">
	<table id="tblNRADtls" align="center" width="98%" border="1">
	
	<tr>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.SRNO" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.PURPOSECATEGORY" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.SANCAMT" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.SANCDATE" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.VOUCHERNO" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.VOUCHERDATE" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.TOTALSANCINSTALL" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.INSTLMNTAMTPERDISBRSMNT" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.DELETE" bundle="${gpfLabels}" /></b></td>
	</tr>
	
	<c:choose>

			<c:when test="${resValue.NRADtls != null}">
	
				<c:forEach var="NRADtls" items="${resValue.NRADtls}" varStatus="Counter">					
					<tr>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="5" style="text-align: center" name="txtNRASrno" value="${Counter.index + 1}" readonly="readonly" />
							<input type="hidden" id="hidNRADtlsPk${Counter.index}" name="hidNRADtlsPk" value="${NRADtls[7]}" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="20" style="text-align: center" name="txtPurposeOfAdvanceNRA" value="${NRADtls[0]}" readonly="readonly" />
							<input type="hidden" name="hidPurposeOfAdvanceNRA" value="${NRADtls[8]}"/>
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="20" style="text-align: center" name="txtSancAmountNRA" value="${NRADtls[1]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<fmt:formatDate value="${NRADtls[2]}" pattern="dd/MM/yyyy" var="NRASancDate"/>
							<input type="text" style="border: none;" size="10" style="text-align: center" name="txtSanctnDateNRA" value="${NRASancDate}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="10" style="text-align: center" name="txtVoucherNoNRA" value="${NRADtls[3]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<fmt:formatDate value="${NRADtls[4]}" pattern="dd/MM/yyyy" var="NRAVchrDate"/>
							<input type="text" style="border: none;" size="10" style="text-align: center" name="txtVoucherDateNRA" value="${NRAVchrDate}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="5" style="text-align: center" name="txtNoOfInstlmntsNRA" value="${NRADtls[5]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="10" style="text-align: center" name="txtInstlmntAmtPerDisbrmnt" value="${NRADtls[6]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif"  onclick="deleteRow_NRA();" ${varImageDisabled}/>
						</td>						
						
					</tr>
				</c:forEach>
			</c:when>
	</c:choose>
	
	</table>
	</div>
</div>
</fieldset>

<br>

<table>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.DEOREMARKS" bundle="${gpfLabels}" ></fmt:message>
		</td>
		<td width="50%">
			<textarea NAME="txtDeoRemarks" id="txtDeoRemarks" ROWS="3" cols="50" ${varDisabled}>${resValue.DeoRemark}</textarea>
		</td>
	</tr>
	<tr id="trDdoRemark" style="display: none">
		<td width="15%">
			<fmt:message key="CMN.DDOREMARKS" bundle="${gpfLabels}" ></fmt:message>
		</td>
		<td width="50%">
			<textarea NAME="txtDdoRemarks" id="txtDdoRemarks" ROWS="3" cols="50"></textarea>
		</td>	
	</tr>
</table>

<center>
<c:if test="${resValue.userType == 'DEO'}">
	<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" bundle="${gpfLabels}" onclick="saveData();"/>
	<hdiits:button name="btnForwad" id="btnForwad" type="button" captionid="BTN.FORWARD" bundle="${gpfLabels}" onclick="forwardData();"/>
</c:if>
<c:if test="${resValue.userType == 'DDO'}">
	<hdiits:button name="btnApproveData" id="btnApproveData" type="button" captionid="BTN.APPROVE" bundle="${gpfLabels}" onclick="approveRequest();"/>	
</c:if>	
	<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${gpfLabels}" onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';"/>
</center>
</hdiits:form>

<script type="text/javascript">
		initializetabcontent("maintab");
</script>	
	
<c:if test="${resValue.userType == 'DDO'}">
<script>
	document.getElementById("trSubscription").style.display='none';
	document.getElementById("tblNonRefundable").style.display='none';
	document.getElementById("tblRefundableHis").style.display='none';
	document.getElementById("trDdoRemark").style.display='';
</script>
</c:if>	