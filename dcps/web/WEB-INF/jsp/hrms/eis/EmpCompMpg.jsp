<%@page
	import="com.tcs.sgv.common.utils.StringUtility, com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst, com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst , java.util.* "%>
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page
	import="com.tcs.sgv.eis.valueobject.HrEisEmpMst, 
				  java.text.SimpleDateFormat"%>


<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="hrEisEmpMst" value="${resValue.hrEisEmpMst}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="DCPSorGPF" value="${resValue.DCPSorGPF}"></c:set>
<!-- developed by pandey   -->
<c:set var="DCG" value="${resValue.DCG}"></c:set>
<!-- Developed by pandey  -->

<c:set var="AllowSize"
	value="${resultObj.resultValue.MappedallowListSize}"></c:set>
<c:set var="DeductionSize"
	value="${resultObj.resultValue.MappedDeduListSize}"></c:set>

<c:set var="deducActionList"
	value="${resultObj.resultValue.MappedDeduList}"></c:set>
<c:set var="allowListMapped"
	value="${resultObj.resultValue.MappedallowList}"></c:set>


<c:set var="AllowListFifthPay"
	value="${resultObj.resultValue.AllowListFifthPay}"></c:set>
<c:set var="DeduListFifthPay"
	value="${resultObj.resultValue.DeduListFifthPay}"></c:set>

<c:set var="AllowListSixthPay"
	value="${resultObj.resultValue.AllowListSixthPay}"></c:set>
<c:set var="DeduListSixthPay"
	value="${resultObj.resultValue.DeduListSixthPay}"></c:set>

<c:set var="flag" value="${resultObj.resultValue.flag}"></c:set>
<c:set var="PayCommissionID"
	value="${resultObj.resultValue.PayCommissionID}"></c:set>

<c:set var="locname" value="${resultObj.resultValue.locname}"></c:set>
<c:set var="locId" value="${resultObj.resultValue.locId}"></c:set>
<c:set var="prevId" value="-2" />

<c:set var="DesgName" value="${resultObj.resultValue.DesgName}"></c:set>
<c:set var="WEFDate" value="${resultObj.resultValue.WEFDate}"></c:set>
<c:set var="Remarks" value="${resultObj.resultValue.remarks}"></c:set>

<fmt:formatDate value="${resultObj.resultValue.current_date}"
	pattern="dd/MM/yyyy" dateStyle="medium" var="currentDateJs" />
<fmt:formatDate value="${resultObj.resultValue.WEFDate}"
	pattern="dd/MM/yyyy" dateStyle="medium" var="WEFDateJs" />
<fmt:formatDate value="${resultObj.resultValue.DOJofEmp}"
	pattern="dd/MM/yyyy" dateStyle="medium" var="DOJofEmpJS" />

<c:set var="DOJofEmp" value="${resultObj.resultValue.DOJofEmp}"></c:set>
<!-- 09 jan 2012  added for default config for new employees-->
<c:set var="isNewConfig" value="${resultObj.resultValue.isNewConfig}"></c:set>
<c:set var="isDCPS" value="${resultObj.resultValue.isDCPS}"></c:set>
<c:set var="DCPSorGPF" value="${resultObj.resultValue.DCPSorGPF}"></c:set>
<!--  added by pandey 08-01-2021 -->
<c:set var="isDCPSStop" value="${resultObj.resultValue.isDCPSStop}"></c:set>
<c:set var="installmentno"
	value="${resultObj.resultValue.installmentno}"></c:set>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>





<script>
	//alert("in EmpCompMpg........... ....");
	
	
	var allowancesCodes=[];
	
	var deductionCodes=[];

	if ("${msg}" != '')
		alert('${msg}');
	//if("${WEFDate}"!='')
	//	alert("${WEFDate}");

	//alert("doj new ::"+'${DOJofEmpJS}');

	//if('01/07/2010' == '${DOJofEmpJS}')
	//{
	//alert('same value ');
	//}

	getCheckedData();
	function settingvalue() {
		//alert("on load.........");

	}
	function resetForm() {
		if (confirm("All entered values will be cleared, please confirm!") == true) {
			document.forms[0].reset();
		}
	}
	function onBackfn() {
		//alert("in fnback...");
		document.EmpCompMpgForm.action = 'ifms.htm?actionFlag=getEmpDataForMapping&CloseFlag=Y&elementId=9000218';
		http: document.EmpCompMpgForm.submit();
	}

	function submitButton_formSubmitButton() {
		
		allowancesCodes=[];
		deductionCodes=[];
		
		
		getAllowData();
		getDeductData();
		
		
		//validating 
		
		if(allowancesCodes.length>0 && deductionCodes.length>0 ){
			
			
			/* console.log("allowancesCodes.includes('208')>>>"+allowancesCodes.includes('208'));
			
			console.log("deductionCodes.includes('142')>>>"+!deductionCodes.includes('142'));
			
			console.log("if "+allowancesCodes.includes('208') && !deductionCodes.includes('142'));  */  
			
			if(allowancesCodes.includes('208') && !deductionCodes.includes('142')){
				alert('Please select Empr Cont (NPS 14%) deduction componenent');
				return;
			}
			if(deductionCodes.includes('142') && !allowancesCodes.includes('208')){
				alert('Please select Empr Cont (NPS 14%) allowance componenent');
				return;
			}
			if(allowancesCodes.includes('208') && deductionCodes.includes('142')){
				if(!deductionCodes.includes('120') && !deductionCodes.includes('121') && !deductionCodes.includes('122') && !deductionCodes.includes('59')){
					alert('Please select atleast one component from DCPS DA Arrears Recovery OR DCPS Delayed Recovery OR DCPS Pay Arrears Recovery DCPS Regular Recovery');
					return;
				}
			}
		}
		
		
		
		
		document.EmpCompMpgForm.formSubmitButton.disabled = true;

		if (document.getElementById("PayCommission").value == -1) {
			alert('Please select PayCommission Name');
			return;
		}

		//alert("=======> "+document.getElementById("EffectDate").value);
		if (document.getElementById("EffectDate").value == "") {
			alert('Please Enter Effect Date');
			return;
		}

		if (document.getElementById("Remarks").value == "") {
			alert('Please Enter Remarks');
			return;
		}

		//alert(document.getElementById("hdnEmpId").value);
		var answer = confirm("Do you want to submit?");

		if (answer) {
			//alert("in if");
			showProgressbar("Please wait...");
			document.EmpCompMpgForm.action = 'ifms.htm?actionFlag=InsertEmpCompMpg';
			document.EmpCompMpgForm.submit();
		} else
			document.EmpCompMpgForm.formSubmitButton.disabled = false;
	}
	function getAllowData() {
		var allowsizeinget = document.getElementById('hdnAllowSize').value;
		//alert(allowsizeinget);
		var cheked;
		var allow = "";
		var checkedvalueofAllow = 1;

		for (var i = 1; i <= allowsizeinget; i++) {
			cheked = 'selcheckBoxAllow' + i;
			//alert("====> checkbox value :: "+cheked);
			//alert("====> "+document.getElementById(cheked).checked);
			if (document.getElementById(cheked).checked) {
				//alert(checkedvalueofAllow);
				if (i < (allowsizeinget)) {
					allow += document.getElementById('tax_name2' + i).value
							+ ",";
				} else {
					allow += document.getElementById('tax_name2' + i).value;
				}

				checkedvalueofAllow++;
				
				allowancesCodes.push(document.getElementById('tax_name2' + i).value);
				
				
			
			

				console.log("allowance code"+allow);
				
			}
			
			
		}
		//alert(allow);
		document.getElementById('hdnAllowList').value = allow;
		document.getElementById('hdncheckedvalueofAllow').value = (checkedvalueofAllow - 1);
		
		

		//alert("===> allow :: "+allow+"==> checkedvalueofAllow ::"+document.getElementById('hdncheckedvalueofAllow').value);

	}
	function getDeductData() {
		var Deductsizeinget = document.getElementById('hdnDeductionSize').value;
		//alert(Deductsizeinget);
		var cheked;
		var deduct = "";
		var checkedvalueofdeduct = 1;
		//alert("=> Deductsizeinget :: "+Deductsizeinget);
		for (var i = 1; i <= Deductsizeinget; i++) {
			cheked = 'selcheckBoxDedu' + i;
			//alert("====> checkbox value :: "+cheked);

			if (document.getElementById(cheked).checked) {
				//alert(checkedvalueofdeduct);
				if (i < (Deductsizeinget)) {
					deduct += document.getElementById('tax_name1' + i).value
							+ ",";
				} else {
					deduct += document.getElementById('tax_name1' + i).value;
				}
				
				
				deductionCodes.push(document.getElementById('tax_name1' + i).value);
				
				
				checkedvalueofdeduct++;
			}
		}
		//alert(deduct);
		document.getElementById('hdnDeductList').value = deduct;
		document.getElementById('hdncheckedvalueofdeduct').value = (checkedvalueofdeduct - 1);

		//alert("===> deduct :: "+deduct+"==> checkedvalueofdeduct ::"+document.getElementById('hdncheckedvalueofdeduct').value);
	}
	function getCommission() {
		document.getElementById("hdnPayCommission").value = document
				.getElementById("PayCommission").value;
		//alert("===> hdnPayCommission :: "+document.getElementById("hdnPayCommission").value);
		document.forms[0].action = "./ifms.htm?actionFlag=getEmpDataForMapping";
		document.forms[0].submit();
	}
	function vallidate() {
		//alert(validate);
		//compareEffectDateWithPreviousWEF();

		var currentDate = document.getElementById('currentDate').value;
		var date1 = document.getElementById('EffectDate').value;

		//alert("===> in validate function.."+currentDate+"date1 :: "+date1);
		var Currsplitval = currentDate.split("/");
		var CurrMonth = Currsplitval[1];
		var CurrYear = Currsplitval[2];
		var currDate = Currsplitval[0];
		;
		//alert('The valuueof current date is'+ currDate);
		var WEFsplitval = date1.split("/");
		var WEFMonth = WEFsplitval[1];
		var WEFYear = WEFsplitval[2];
		var WEFDate = WEFsplitval[0];
		//alert('The valuue of effect date is'+ WEFDate);
		//alert("===> in CurrMonth ::"+CurrMonth+"==> WEFMonth :: "+WEFMonth);
		//alert("===> in CurrYear ::"+WEFMonth+"==> WEFYear :: "+WEFYear);
		if (document.getElementById('EffectDate').value != "") {
			if (WEFYear > CurrYear) {
				alert('Enter Year les than current year');
				document.forms[0].EffectDate.value = "";
				document.forms[0].EffectDate.focus();
				return false;
			}
			if (WEFYear == CurrYear) {
				if (WEFMonth <= CurrMonth) {
					if (WEFDate > currDate) {
						alert('Please select a date less than current Date');
						document.forms[0].EffectDate.value = "";
						document.forms[0].EffectDate.focus();
						return false;
					}

				} else {
					alert('please Select a Date less than current Date');
					document.forms[0].EffectDate.value = "";
					document.forms[0].EffectDate.focus();
					return false;
				}
			}
		}
		///var result = compareDate(currentDate,date1);
		//if(date1 != null && date1 != '')
		//{
		//	if(result < 0)
		//	{
		//		alert("Please select date which is not earlier then today's date");
		//		document.forms[0].WEFDATE.value="";
		//		document.forms[0].WEFDATE.focus();
		//		return false;
		//	}
		//}
	}
	function getCheckedData() {

		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		var url;
		var urlVariable = '';
		url = urlVariable + '&EmpId='+${hrEisEmpMst.empId}
		var actionf = "AllowDeductIDFromEmpCompMpg"

		urlVariable = './ifms.htm?actionFlag=' + actionf;
		url = urlVariable + url;
		//alert(url);	  
		xmlHttp.onreadystatechange = ForCheckingValue;
		xmlHttp.open("POST", encodeURI(url), true);
		xmlHttp.send(null);
	}
	function ForCheckingValue() {
		if (xmlHttp.readyState == complete_state) {
			var XMLDoc = xmlHttp.responseXML.documentElement;
			//alert('xml doc ' + XMLDoc);			
			var AllowChecked = XMLDoc.getElementsByTagName('AllowHead');
			var DeductChecked = XMLDoc.getElementsByTagName('DeductHead');
			var dcpsFlag = XMLDoc.getElementsByTagName('dcpsFlag');

			//alert(AllowChecked[0].childNodes.length);

			var allowLength = AllowChecked[0].childNodes.length;
			var deducLength = DeductChecked[0].childNodes.length;

			//alert("===> allowLength :: "+allowLength);
			//alert("===> deducLength :: "+deducLength); 	
			var Deductsizeinget = document.getElementById('hdnDeductionSize').value;
			var allowsizeinget = document.getElementById('hdnAllowSize').value;
			var DCPSorGPF = document.getElementById('DCPSorGPF').value;

			for (var i = 0; i < allowLength; i++) {
				for (var j = 1; j <= allowsizeinget; j++) {
					var id = document.getElementById('tax_name2' + j).value;
					if (id == AllowChecked[0].childNodes[i].firstChild.nodeValue) {
						document.getElementById('selcheckBoxAllow' + j).checked = true;
					}
				}

			}

			for (var i = 0; i < deducLength; i++) {
				for (var j = 1; j <= Deductsizeinget; j++) {
					var id = document.getElementById('tax_name1' + j).value;
					if (id == DeductChecked[0].childNodes[i].firstChild.nodeValue) {
						document.getElementById('selcheckBoxDedu' + j).checked = true;
						if (DCPSorGPF == 'Y') {
							//  alert('insde the if');  // validation developed by pandey 	
							if ((id == 76) || (id == 75) || (id == 78)
									|| (id == 77) || (id == 102) || (id == 103)
									|| (id == 72) || (id == 36) || (id == 104)
									|| (id == 105)) {
								document.getElementById('selcheckBoxDedu' + j).checked = false;
							} else {
								document.getElementById('selcheckBoxDedu' + j).checked = true;
							}
						} else {
							//alert('insde the else');	
							if ((id == 59) || (id == 108) || (id == 122)
									|| (id == 120) || (id == 121)) {
								document.getElementById('selcheckBoxDedu' + j).checked = false;
							} else {
								document.getElementById('selcheckBoxDedu' + j).checked = true;
							}
						}

					}
				}

			}
		}
	}
	function OnlyAplha(control) {
		var e1 = control.value;
		control.value = (e1.replace(/^\W+/, '')).replace(/\W+$/, '');
		var iChars = "QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzasdfghjklpoiuytrewq0123456789&/\,.-_`&_$;@*%~{}<>^'|%+#()  ";
		var value = "";
		var valid = true;
		for (var i = 0; i < control.value.length; i++) {
			if (iChars.indexOf(control.value.charAt(i)) != -1) {
				value = value + control.value.charAt(i);
			} else
				valid = false;
		}

		if (!valid) {
			alert('Numbers and special characters are not allowed');
			control.value = "";
			control.focus();
			return false;
		}
		return true;
	}
	function compareEffectDateWithPreviousWEF(pointer) {
		var date5 = document.getElementById('EffectDate').value

		//alert("==> passing value :: "+"${WEFDateJs}"+"22222222222 :: "+date5);
		var diff = compareDate("${WEFDateJs}", date5);
		//alert("====> date5 :: "+date5+"==> diff :: "+diff)
		if (document.getElementById('EffectDate').value != "") {
			if (diff < 0) {
				alert("Effect Date must be Greater then Previous Mapped Date "
						+ "${WEFDateJs}");
				document.EmpCompMpgForm.EffectDate.value = '';
				document.EmpCompMpgForm.EffectDate.focus();

				return false;
			}
		}
	}

	function compareDate(paramFirstDate, paramSecondDate) {
		var DD = paramFirstDate.substr(0, 2);
		var MM = paramFirstDate.substr(3, 2);
		var YY = paramFirstDate.substr(6, 4);

		var dd = paramSecondDate.substr(0, 2);
		var mm = paramSecondDate.substr(3, 2);
		var yy = paramSecondDate.substr(6, 4);

		var toDate = new Date(yy, mm - 1, dd);
		var frmDate = new Date(YY, MM - 1, DD);
		var diff = toDate.getTime() - frmDate.getTime();

		return (diff);
	}

	function selDeselectAllowances(obj) {
		//alert('selDeselectAllowances');
		var selAllChkFlg = obj.checked;
		if (selAllChkFlg == true) {
			for (var j = 1; j <= "${AllowSize}"; j++) {
				document.getElementById('selcheckBoxAllow' + j).checked = true;
			}
		} else {
			for (var j = 1; j <= "${AllowSize}"; j++) {

				//var max=document.EmpCompMpgForm.elements('tax_name2'+j).value;
				//	max = parseInt(max, 10);
				//	if(max!=9)
				//	{
				//	if(max!=145)
				//	{
				document.getElementById('selcheckBoxAllow' + j).checked = false;
				//	}
				// }
			}
		}
	}
	function selDeselectDeduction(obj) {
		//alert('ddct');
		var selAllChkFlg = obj.checked;
		if (selAllChkFlg == true) {
			for (var j = 1; j <= "${DeductionSize}"; j++) {
				//var max=document.EmpCompMpgForm.elements('tax_name1'+j).value;
				//max = parseInt(max, 10);

				//    if(max!=59)
				//	  {
				document.getElementById('selcheckBoxDedu' + j).checked = true;
				//document.EmpCompMpgForm.elements('selcheckBoxDedu'+j).checked=true;
				//  }
				//   if(max==72)
				//	{
				//if(diff>0)
				//{
				//	document.EmpCompMpgForm.elements('selcheckBoxDedu'+j).checked=false;
				//	}
				//}
				//if(max==36)
				//{
				//if(diff>0)
				//{
				//	document.EmpCompMpgForm.elements('selcheckBoxDedu'+j).checked=false;
				//	}
				//}		
			}
		} else {
			for (var j = 1; j <= "${DeductionSize}"; j++) {
				//var max=document.EmpCompMpgForm.elements('tax_name1'+j).value;
				////if(max!=32)
				//{
				//if(max!=35)
				//{
				//	if(max!=59)
				//	{
				document.getElementById('selcheckBoxDedu' + j).checked = false;
				//}
				//}
				// }
			}
		}
	}
</script>
<body>
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
							key="EmpCompMpg.Head" bundle="${commonLables}" /></b></a></li>
		</ul>
	</div>
	<br />
	<br />

	<hdiits:form name="EmpCompMpgForm" validate="true" method="POST"
		onload="settingvalue();" encType="multipart/form-data">

		<input type="hidden" id="DCPSorGPF" name="DCPSorGPF"
			value="${DCPSorGPF}">
		<!-- added by pandey 08-01-2021  -->


		<div id="tcontent1"
			style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

			<table class="tabtable" border="0" bordercolor="black">

				<TR>
					<td width="22%"><input type="hidden" value="" name="dcpsFlag"
						id="dcpsFlagval"></td>
					<TD class="fieldLabel"><b><hdiits:caption
								captionid="FULL_NAME" bundle="${commonLables}"></hdiits:caption>:</b>
					</TD>
					<TD>
						<!-- 11 jan 2012 --> ${hrEisEmpMst.orgEmpMst.empPrefix} <c:if
							test="${hrEisEmpMst.orgEmpMst.empLname != null}"> ${hrEisEmpMst.orgEmpMst.empLname} </c:if>
						<c:if test="${hrEisEmpMst.orgEmpMst.empFname != null}"> ${hrEisEmpMst.orgEmpMst.empFname} </c:if>
						<c:if test="${hrEisEmpMst.orgEmpMst.empMname != null}"> ${hrEisEmpMst.orgEmpMst.empMname} </c:if>
						<c:if test="${DesgName != null}"> ( ${DesgName} ) </c:if>

					</TD>
					<td width="18%"></td>
				</TR>

				<TR>
					<td width="22%"></td>
					<TD class="fieldLabel"><b><hdiits:caption
								captionid="DeptComMpg.DeptName" bundle="${commonLables}"></hdiits:caption>:</b>
					</TD>
					<TD>${locname}</TD>
					<td width="18%">
				</TR>

				<TR>
					<td width="22%"></td>
					<TD class="fieldLabel">DCPS / GPF :</TD>
					<TD>${DCG}</TD>
					<td width="18%">
				</TR>

				<TR>
					<td width="22%"></td>
					<td class="fieldLabel"><b><hdiits:caption
								captionid="EmpCompMpg.PayComName" bundle="${commonLables}"></hdiits:caption>:</b></td>
					<td class="fieldLabel"><hdiits:select style="width:50%"
							name="PayCommission" size="1" sort="false"
							caption="PayCommission" captionid="PayCommission"
							default="${PayCommissionID}" onchange="getCommission()"
							mandatory="true" readonly="true">
							<hdiits:option value="-1">--Select--</hdiits:option>
							<hdiits:option value="2500340"> 5th Pay Commission </hdiits:option>
							<hdiits:option value="2500341"> 6th Pay Commission </hdiits:option>
							<hdiits:option value="2500342"> Consolidated Pay </hdiits:option>
							<hdiits:option value="2500343"> Non Govt. Scales </hdiits:option>
							<hdiits:option value="2500344"> Padmanabhan Comm. </hdiits:option>
							<hdiits:option value="2500345"> Fourth ( IV ) </hdiits:option>
							<hdiits:option value="2500346"> Shetty Commission </hdiits:option>
							<hdiits:option value="2500347"> 7th Pay Commission </hdiits:option>
						</hdiits:select></td>
					<hdiits:hidden name="PayCommission" id="PayCommission"
						default="${PayCommissionID}"></hdiits:hidden>
					<td width="18%">
				</TR>

				<TR>
					<td width="22%"></td>
					<td class="fieldLabel"><b><hdiits:caption
								captionid="EmpCompMpg.Date" bundle="${commonLables}" /></b></td>
					<td class="fieldLabel"><hdiits:dateTime name="EffectDate"
							captionid="EmpCompMpg.Date" bundle="${commonLables}"
							mandatory="true" validation="txt.isdt,txt.isrequired"
							minvalue="${WEFDate}" default="${WEFDate}" onblur="vallidate();"></hdiits:dateTime></td>
					<td width="18%">
				</TR>
				<TR>
					<td width="22%"></td>
					<td class="fieldLabel"><b><hdiits:caption
								captionid="eis.Remarks" bundle="${commonLables}" /></b></td>
					<td class="fieldLabel"><hdiits:textarea rows="3" cols="30"
							name="Remarks" id="Remarks" captionid="eis.Remarks"
							bundle="${commonLables}" mandatory="true" onblur=""
							default="${Remarks}">:</hdiits:textarea></td>
					<td width="18%">
				</TR>
			</table>

			<center>

				<table width="80%" border=0 id="deduction">
					<tr>
						<td width="15%" align="left"></td>
						<td colspan="2" align="left"><b><font color="98560A"
								size="2"><c:if test="${AllowSize ne 0}">

										<hdiits:checkbox name='selectAllowanceChkBox'
											id='selectAllowanceChkBox' value=""
											onclick="selDeselectAllowances(this)" readonly="true" />
										<b>Allowances</b>
										<b>Allowances</b>
									</c:if> </font></b></td>
						<td colspan="2" align="left"><b><font color="98560A"
								size="2"><c:if test="${DeductionSize ne 0}">
										<hdiits:checkbox name='selectDeductionChkBox'
											id='selectDeductionChkBox' value=""
											onclick="selDeselectDeduction(this)" readonly="true" />/>
										<b>Deduction </b>
									</c:if> </font></b></td>
					</tr>
					<tr />
					<tr />
					<tr />

					<c:choose>
						<c:when test="${DeductionSize ge AllowSize}">
							<c:set var="count" value="${DeductionSize}">
							</c:set>
						</c:when>
						<c:otherwise>
							<c:set var="count" value="${AllowSize}"></c:set>
						</c:otherwise>
					</c:choose>

					<c:set var="allowccode" value="${0}" />
					<c:set var="deduccode" value="${0}" />
					<c:set var="srNoDeduction" value="1"></c:set>
					<c:set var="srNoAllown" value="1"></c:set>

					<%
					int count = Integer.parseInt(pageContext.getAttribute("count").toString());
					int allowCount = Integer.parseInt(pageContext.getAttribute("AllowSize").toString());
					int deducCount = Integer.parseInt(pageContext.getAttribute("DeductionSize").toString());
					List<HrPayAllowTypeMst> allowListMapped = (List<HrPayAllowTypeMst>) pageContext.getAttribute("allowListMapped");
					List<HrPayDeducTypeMst> deducList = (List<HrPayDeducTypeMst>) pageContext.getAttribute("deducActionList");
					for (int i = 0; i < count; i++) {
						if (i < allowCount) {
							//System.out.println("allowListMapped "+allowListMapped.size());
							//System.out.println("deducList "+deducList.size());
							HrPayAllowTypeMst allowType = (HrPayAllowTypeMst) allowListMapped.get(i);
							pageContext.setAttribute("allowType", allowType);
					%>

					<TR>

						<td width="15%" align="left"></td>
						<TD colspan="2" align="left"><hdiits:hidden
								default="${allowType.allowCode}" name="tax_name2${srNoAllown}"
								id="tax_name2${srNoAllown}" /> <c:choose>
								<c:when
									test="${allowType.allowCode eq 145 or allowType.allowCode eq 9 }"><!-- or allowType.allowCode eq 208 -->
									<hdiits:checkbox id="selcheckBoxAllow${srNoAllown}"
										name="selcheckBoxAllow${srNoAllown}" value="1" default=""
										readonly="" />
								</c:when>
					<%-- 			<c:when
									test="${isDCPSStop eq true and (allowType.allowCode eq 208)}">
									<hdiits:checkbox id="selcheckBoxAllow${srNoAllown}"
										name="selcheckBoxAllow${srNoAllown}" value="1" default=""
										readonly="true" />
								</c:when> --%>



								<%-- Added by ashish 208--%>
								<c:when test="${allowType.allowCode eq 208}">

									<c:choose>

										<%-- <c:when test="${isDCPS eq true}">

											<input type="checkbox" id="selcheckBoxAllow${srNoAllown}"
												name="selcheckBoxAllow${srNoAllown}" checked="checked"
												 value="1">
										</c:when>  --%>


										<c:when test="${isDCPS eq false}">

											<input type="checkbox" id="selcheckBoxAllow${srNoAllown}"
												name="selcheckBoxAllow${srNoAllown}" disabled="disabled" readonly="true"
												value="1">
										</c:when>

										<c:otherwise>
											<input type="checkbox" id="selcheckBoxAllow${srNoAllown}"
												name="selcheckBoxAllow${srNoAllown}"
												value="1">
										</c:otherwise>

									</c:choose>
								</c:when>
								<%-- Ended by ashish 208--%>
								<%-- Added by  561--%>
								<c:when test="${allowType.allowCode eq 561}">

									<c:choose>

										<c:when
											test="${installmentno == 1 || installmentno == 2 || installmentno == 3 || installmentno == 4 || installmentno == 5}">

											<input type="checkbox" id="selcheckBoxAllow${srNoAllown}"
												name="selcheckBoxAllow${srNoAllown}" checked="checked"
												disabled="disabled" value="0">
										</c:when>
										<c:otherwise>
											<input type="checkbox" id="selcheckBoxAllow${srNoAllown}"
												name="selcheckBoxAllow${srNoAllown}" disabled="disabled"
												value="1">
										</c:otherwise>

									</c:choose>

								</c:when>
								<%-- Ended by  561--%>

								<c:otherwise>
									<hdiits:checkbox id="selcheckBoxAllow${srNoAllown}"
										name="selcheckBoxAllow${srNoAllown}" value="" />
								</c:otherwise>
							</c:choose> <b><c:out value="${allowType.allowDisplayName}" /></b> <span
							id="span${allowType.allowCode}"></span> <c:set var="allowccode"
								value="${allowccode+1}" /> <c:set var="srNoAllown"
								value="${srNoAllown + 1}"></c:set></TD>


						<!-- 			<td><c:out value="selcheckBoxAllow${srNoAllown}" /></td> -->

						<%
						} else {
						%>
					
					<tr>
						<td width="15%" align="left"></td>
						<TD colspan="2" align="left"></TD>
						<%
						}
						if (i < deducCount) {

						HrPayDeducTypeMst deducType = (HrPayDeducTypeMst) deducList.get(i);
						pageContext.setAttribute("deducType", deducType);
						%>


						<TD colspan="2" align="left"><hdiits:hidden
								default="${deducType.deducCode}"
								name="tax_name1${srNoDeduction}" id="tax_name1${srNoDeduction}" />
							<c:choose>

								<c:when
									test="${isNewConfig eq true and deducType.deducCode eq 35}">
									<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}"
										name="selcheckBoxDedu${srNoDeduction}" readonly="" default="1"
										value="1"
										onclick="checkUncheckUEPF(${deducType.deducCode},${srNoDeduction});" />
								</c:when>
								<c:when
									test="${isNewConfig eq true and isDCPS eq true and isDCPSStop eq false and(deducType.deducCode eq 108 or deducType.deducCode eq 121 or deducType.deducCode eq 122)}">
									<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}"
										name="selcheckBoxDedu${srNoDeduction}" readonly="" default="1"
										value="1" />
								</c:when>
								<c:when
									test="${isNewConfig eq true and isDCPS eq true and (deducType.deducCode eq 108 or deducType.deducCode eq 121 or deducType.deducCode eq 122)}">
									<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}"
										name="selcheckBoxDedu${srNoDeduction}" readonly="" default="1"
										value="1" />
								</c:when>
								<%-- <c:otherwise>
									<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}"
										name="selcheckBoxDedu${srNoDeduction}" value="1" /> --%>
								<c:when test="${ deducType.deducCode eq 123 }">
									<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}"
										name="selcheckBoxDedu${srNoDeduction}" readonly="true"
										default="" value="1" />
								</c:when>

								<c:when
									test="${(isDCPS eq true || isDCPSStop eq true) and ( deducType.deducCode eq 102 || deducType.deducCode eq 103 || deducType.deducCode eq 72 || 	
																deducType.deducCode eq 36 || deducType.deducCode eq 104 || deducType.deducCode eq 105 	
																|| deducType.deducCode eq 76 || deducType.deducCode eq 77 || deducType.deducCode eq 78	
																|| deducType.deducCode eq 75 ||  deducType.deducCode eq 177) }">
									<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}"
										name="selcheckBoxDedu${srNoDeduction}" readonly="true"
										default="" value="1" />
								</c:when>

								<%-- 	<c:when test="${isDCPSStop eq true and (deducType.deducCode eq 666 || deducType.deducCode eq 59 ||	
													 deducType.deducCode eq 108 || deducType.deducCode eq 122 ||	
												    deducType.deducCode eq 120 || deducType.deducCode eq 121)}">	
											
													<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}" name="selcheckBoxDedu${srNoDeduction}" readonly="true" default="" value="1" />	
												</c:when>	
												
											
										    	<c:when test="${isDCPSStop eq false and isDCPS eq false and (deducType.deducCode eq 666 || deducType.deducCode eq 59 ||	
													 deducType.deducCode eq 108 || deducType.deducCode eq 122 ||	
												    deducType.deducCode eq 120 || deducType.deducCode eq 121)}">		
												<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}" name="selcheckBoxDedu${srNoDeduction}" readonly="true" default="" value="1" />	
													
												</c:when> --%>



								<c:when
									test="${ deducType.deducCode eq 59 ||	
													 deducType.deducCode eq 108 || deducType.deducCode eq 122 ||	
												    deducType.deducCode eq 120 || deducType.deducCode eq 121 || deducType.deducCode eq 142}">
									<c:choose>


										<c:when test="${isDCPSStop eq true}">
											<input type="checkbox" id="selcheckBoxDedu${srNoDeduction}"
												name="selcheckBoxDedu${srNoDeduction}"
												value="1">
										</c:when>
										<c:when test="${isDCPSStop eq false and isDCPS eq true}">
											<input type="checkbox" id="selcheckBoxDedu${srNoDeduction}"
												name="selcheckBoxDedu${srNoDeduction}" value="1">

										</c:when>

										<c:otherwise>
											<input type="checkbox" id="selcheckBoxDedu${srNoDeduction}"
												name="selcheckBoxDedu${srNoDeduction}" disabled="disabled"
												value="1">

										</c:otherwise>

									</c:choose>

								</c:when>


								<%-- Added by ashish 142--%>
								<c:when test="${deducType.deducCode eq 142}">
									<c:choose>


										<c:when test="${isDCPS eq true and isDCPSStop eq false}">

											<input type="checkbox" id="selcheckBoxDedu${srNoDeduction}"
												name="selcheckBoxDedu${srNoDeduction}" checked="checked"
												 value="1">
										</c:when>
										<c:when test="${isDCPS eq false}">
											<input type="checkbox" id="selcheckBoxDedu${srNoDeduction}"
												name="selcheckBoxDedu${srNoDeduction}" disabled="disabled"
												value="1">

										</c:when>

										<c:otherwise>
											<input type="checkbox" id="selcheckBoxDedu${srNoDeduction}"
												name="selcheckBoxDedu${srNoDeduction}" disabled="disabled"
												value="1">

										</c:otherwise>

									</c:choose>

								</c:when>







								<c:when
									test="${deducType.deducCode eq 588 || deducType.deducCode eq 559}">
									<c:choose>


										<c:when
											test="${installmentno == 1 || installmentno == 2 || installmentno == 3 || installmentno == 4 || installmentno == 5}">
											<input type="checkbox" id="selcheckBoxDedu${srNoDeduction}"
												name="selcheckBoxDedu${srNoDeduction}" checked="checked"
												disabled="disabled" value="0">
										</c:when>

										<c:otherwise>
											<input type="checkbox" id="selcheckBoxDedu${srNoDeduction}"
												name="selcheckBoxDedu${srNoDeduction}" disabled="disabled"
												value="1">

										</c:otherwise>

									</c:choose>
								</c:when>




								<c:when
									test="${isDCPS eq false and isDCPSStop eq false and ( deducType.deducCode eq 59 ||  deducType.deducCode eq  108 ||  deducType.deducCode eq 122 ||  deducType.deducCode eq 120 || deducType.deducCode eq 121)}">
									<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}"
										name="selcheckBoxDedu${srNoDeduction}" readonly="true"
										default="" value="1" />
								</c:when>
								<c:otherwise>
									<hdiits:checkbox id="selcheckBoxDedu${srNoDeduction}"
										name="selcheckBoxDedu${srNoDeduction}" value="1" />
								</c:otherwise>
							</c:choose> <span id="span${deducType.deducCode}"></span> <b><c:out
									value="${deducType.deducDisplayName}" /></b> <c:set
								var="deduccode" value="${deduccode+1}" /> <c:set
								var="srNoDeduction" value="${srNoDeduction + 1}"></c:set></TD>



					</TR>
					<hdiits:hidden default="${srNoDeduction}" name="srNoDeduction"
						id="srNoDeduction" />
					<hdiits:hidden default="${srNoAllown}" name="srNoAllown"
						id="srNoAllown" />

					<%
					}
					%>

					<%
					}
					%>

				</table>
			</center>






			<br /> <br />
			<!--<hdiits:button id="ForCheck" name="ForCheck" type="button" value="ForCheck" title="ForCheck" onclick="getAllowDeductdata()"/> -->
		</div>

		<div>
			<br /> <br />
			<table class="tabNavigationBar">
				<tr>

					<td align="justify" width="35%"></td>
					<td align="justify" width="2%"><hdiits:button type="button"
							id="formSubmitButton" name="formSubmitButton"
							onclick="submitButton_formSubmitButton()" value="Save" /></td>
					<td align="justify" width="2%"><hdiits:button
							name="backButton" type="button" value="Close"
							onclick="onBackfn()" /></td>
					<td align="justify" width="2%"><hdiits:button
							name="resetButton" type="button" value="Reset"
							onclick="resetForm()" /></td>
					<td width="30%"></td>

				</tr>
			</table>

			<!--<hdiits:jsField jsFunction="getAllowDeductdata()" name="getAllowDeductdata()" />-->
		</div>

		<hdiits:hidden default="${DeductionSize}" id="hdnDeductionSize"
			name="hdnDeductionSize"></hdiits:hidden>
		<hdiits:hidden default="${AllowSize}" id="hdnAllowSize"
			name="hdnAllowSize"></hdiits:hidden>
		<hdiits:hidden default="${hrEisEmpMst.empId}" id="hdnEmpId"
			name="hdnEmpId"></hdiits:hidden>
		<hdiits:hidden id="hdnAllowList" name="hdnAllowList"></hdiits:hidden>
		<hdiits:hidden id="hdnDeductList" name="hdnDeductList"></hdiits:hidden>
		<hdiits:hidden id="hdncheckedvalueofAllow"
			name="hdncheckedvalueofAllow"></hdiits:hidden>
		<hdiits:hidden id="hdncheckedvalueofdeduct"
			name="hdncheckedvalueofdeduct"></hdiits:hidden>
		<hdiits:hidden id="hdnPayCommission" name="hdnPayCommission"></hdiits:hidden>
		<hdiits:hidden default="Y" id="viewflag" name="viewflag"></hdiits:hidden>
		<hdiits:hidden name="currentDate" id="currentDate"
			default="${currentDateJs}" />

		<script type="text/javascript">
		<!--
			var date1 = '01/11/2005';//DCPS DATE
			//var date2='01/10/2005';

			//var diff = compareDate("${DOJofEmpJS}",date5);
			var diff = compareDate(date1, "${DOJofEmpJS}");
			//alert("====> date1 :: "+date1+":: date2 ::"+date2+"==> diff :: "+diff);

			/*	var Deductsizeinget = document.getElementById('hdnDeductionSize').value;
				//alert(Deductsizeinget);
				for(var i=1;i<=Deductsizeinget;i++)
				{
					cheked = 'selcheckBoxDedu'+i;
					//alert("====> checkbox value :: "+cheked);
					//alert("===> in value :: "+document.getElementById('tax_name1'+i).value);
					if(document.getElementById('tax_name1'+i).value==59)
					{
						//alert("====> trueeeeeeeeeeeee checkbox value :: "+cheked);
						if(diff>0)
						{
							//alert("for false");
							document.getElementById(cheked).checked = true;
							document.getElementById(cheked).disabled  = true;
							//document.getElementById(cheked).
							
						}
						else
						{
							document.getElementById(cheked).checked = false;
							document.getElementById(cheked).disabled  = true;
						}
					}
					if(document.getElementById('tax_name1'+i).value==72)
					{
						//alert("====> trueeeeeeeeeeeee checkbox value :: "+cheked);
						if(diff>0)
						{
							document.getElementById(cheked).disabled  = true;
						}
					}
					if(document.getElementById('tax_name1'+i).value==36)
					{
						//alert("====> trueeeeeeeeeeeee checkbox value :: "+cheked);
						if(diff>0)
						{
							//alert("for false");
							document.getElementById(cheked).disabled  = true;
							//document.getElementById(cheked).
							
						}
					}		
									
				}*/

			-->
		</script>

		<script type="text/javascript">
			initializetabcontent("maintab");
		</script>

	</hdiits:form>


	<%
	} catch (Exception e) {
	e.printStackTrace();
	}
	%>