
<%
	try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />

<script type="text/javascript" src="common/script/tagLibValidation.js">
	
</script>

<script type="text/javascript" src="script/eis/commonUtils.js">
	
</script>

<script type="text/javascript" src="common/script/commonfunctions.js">
	
</script>
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

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="payBandList" value="${resValue.payBandList}"></c:set>
<c:set var="payCommissionList" value="${resValue.payCommissionList}"></c:set>
<c:set var="payScaleTypeList" value="${resValue.payScaleTypeList}"></c:set>
<c:set var="inctDate" value="${resValue.inctDate}"></c:set>

<c:set var="flag" value="${resValue.flag}" />
<c:set var="scaleMstVO" value="${resValue.scaleMstVO}" />
<c:set var="msg" value="${resValue.msg}"></c:set>



<script type="text/javascript" language="JavaScript">
	var retValue = true;

	function validForm() {

		if (chkval() == true) {
			return true;
		} else {
			return false;
		}
		//return true;
	}

	function compareDateEffect(paramFirstDate, paramSecondDate) {
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

	function chkDuplicateScale() {

		var incAmt = document.frmScaleMaster.scaleIncrAmt.value;
		var payCommission = document.getElementById("payCommissionCmbBx").value;

		if (incAmt == '' || incAmt == null) {
			incAmt = 0;
		}

		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer    
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser does not support AJAX!");
					retValue = false;
				}
			}
		}

		//check for duplicate scale entry

		var url = "./hrms.htm?actionFlag=checkScaleAvailability&startAmt="
				+ document.frmScaleMaster.scaleStartAmt.value + "&incAmt="
				+ incAmt + "&endAmt="
				+ document.frmScaleMaster.scaleEndAmt.value + "&higherIncrAmt="
				+ document.frmScaleMaster.scaleHigherIncrementAmt.value
				+ "&higherEndAmt="
				+ document.frmScaleMaster.scaleHigherEndAmt.value
				+ "&payCommission=" + payCommission + "&gradePay="
				+ document.frmScaleMaster.gradePay.value;

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {

					var XMLDocForAjax = xmlHttp.responseXML.documentElement;

					var scaleList = XMLDocForAjax
							.getElementsByTagName('scaleNameMapping');

					if (scaleList.length != 0) {
						var flag = scaleList[0].childNodes[0].textContent;
						//alert(flag);
						if (flag == 'true') {
							alert('Duplicate Scale. Please Enter Another.');
							retValue = false;
						} else if (flag == 'false') {
							retValue = true;
						}
					}
				}
			}
		}

		xmlHttp.open("POST", encodeURI(url), false);
		xmlHttp.setRequestHeader("Content-Type",
				"text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));

		return retValue;
	}

	function chkDate() {
		sDate = document.frmScaleMaster.scaleEFD.value;
		eDate = document.frmScaleMaster.scaleETD.value;
		diff = compareDate(sDate, eDate);
		if (document.frmScaleMaster.scaleETD.value != null
				&& document.frmScaleMaster.scaleETD.value != '') {
			if (diff < 0) {
				alert('Effected To Date must be greater than Effected From Date');
				document.frmScaleMaster.scaleETD.value = '';
				document.frmScaleMaster.scaleETD.focus();
				return false;
			}
		}
	}

	function chkKey(e) {

		if (e.keyCode == 13) {
			return false;
		} else {

			return true;
		}
	}

	function chkval() {
		//alert(document.getElementById("payCommissionCmbBx").value);
		if (document.getElementById("payCommissionCmbBx").value == 300388) {
			alert("Please select Pay commission for the new scale");
			document.getElementById("payCommissionCmbBx").focus();
			return false;
		}

		/*if(document.frmScaleMaster.monthsForIncrement.value !='' )
		{
			if(document.frmScaleMaster.monthsForIncrement.value <= 0 )
			{
				alert(" Months for Increment must be positive");
				document.frmScaleMaster.monthsForIncrement.value="";
				document.frmScaleMaster.monthsForIncrement.focus();
			 return false;
			}

			var Length = document.getElementById("monthsForIncrement").value.length

			for (i=0; i<Length; i++)
			{
		 		if (document.getElementById("monthsForIncrement").value.charAt(i) == ".")    
		 		{
		            alert("Please Enter Integer value for months");
					document.frmScaleMaster.monthsForIncrement.focus();
		         return false;
		 		}
			}
		}*/

		if (document.frmScaleMaster.scaleStartAmt.value != ''
				&& document.frmScaleMaster.scaleEndAmt.value != '') {

			var Length = document.getElementById("scaleStartAmt").value.length;

			for (i = 0; i < Length; i++) {
				if (document.getElementById("scaleStartAmt").value.charAt(i) == ".") {
					alert("Please Enter Integer value for Start Amount");
					document.frmScaleMaster.scaleStartAmt.focus();
					return false;
				}
			}

			var Length1 = document.getElementById("scaleStartAmt").value.length;

			for (i = 0; i < Length1; i++) {
				if (document.getElementById("scaleIncrAmt").value.charAt(i) == ".") {
					alert("Please Enter Integer value for Increment Amount");
					document.frmScaleMaster.scaleIncrAmt.focus();
					return false;
				}
			}

			var Length2 = document.getElementById("scaleEndAmt").value.length;

			for (i = 0; i < Length2; i++) {
				//alert('comingsss');
				if (document.getElementById("scaleEndAmt").value.charAt(i) == ".") {
					alert("Please Enter Integer value for End Amount");
					document.frmScaleMaster.scaleEndAmt.focus();
					return false;
				}
			}
			//Added by Mrugesh for Bigger Scale Value

			if (document.getElementById('scaleHigherIncrAmt') != null
					&& document.getElementById('scaleHigherIncrAmt') != '') {
				var Length3 = document.getElementById("scaleHigherIncrAmt").value.length
				for (i = 0; i < Length3; i++) {
					if (document.getElementById("scaleHigherIncrAmt").value
							.charAt(i) == ".") {
						alert("Please Enter Integer value for Higher Increment Amount");
						document.frmScaleMaster.scaleHigherIncrAmt.focus();
						return false;
					}
				}
			}

			if (document.getElementById('scaleHigherEndAmt') != null
					&& document.getElementById('scaleHigherEndAmt') != '') {

				var Length4 = document.getElementById("scaleHigherEndAmt").value.length
				for (i = 0; i < Length4; i++) {
					if (document.getElementById("scaleHigherIncrAmt").value
							.charAt(i) == ".") {
						alert("Please Enter Integer value for Higher End Amount");
						document.frmScaleMaster.scaleHigherEndAmt.focus();
						return false;
					}
				}
			}
			//Ended by Mrugesh

			if (chkDuplicateScale()) {

				if (document.frmScaleMaster.scaleStartAmt.value < 1) {
					alert("Starting Amount must be positive");
					document.frmScaleMaster.scaleStartAmt.value = "";
					document.frmScaleMaster.scaleStartAmt.focus();
					return false;
				}
				if (document.frmScaleMaster.scaleIncrAmt.value < 0) {
					alert("Increment Amount must be positive");
					document.frmScaleMaster.scaleIncrAmt.value = "";
					document.frmScaleMaster.scaleIncrAmt.focus();
					return false;
				}
				if (document.frmScaleMaster.scaleEndAmt.value < 1) {
					alert("End Amount must be positive");
					document.frmScaleMaster.scaleEndAmt.value = "";
					document.frmScaleMaster.scaleEndAmt.focus();
					return false;
				}
				//Added by Mrugesh for Bigger Scale Value
				var higherIncrAmt = document.frmScaleMaster.scaleHigherIncrAmt.value;
				var higherEndAmt = document.frmScaleMaster.scaleHigherEndAmt.value;

				if (higherIncrAmt != null && higherIncrAmt != '') {
					if (document.frmScaleMaster.scaleHigherIncrAmt.value < 1) {
						alert("Higher Incr Amount must be positive");
						document.frmScaleMaster.scaleHigherIncrAmt.value = "";
						document.frmScaleMaster.scaleHigherIncrAmt.focus();
						return false;
					}
				}

				if (higherEndAmt != null && higherEndAmt != '') {
					if (document.frmScaleMaster.scaleHigherEndAmt.value < 1) {
						alert("Higher End Amount must be positive");
						document.frmScaleMaster.scaleHigherEndAmt.value = "";
						document.frmScaleMaster.scaleHigherEndAmt.focus();
						return false;
					}
				}

				//Ended by Mrugesh

				var StartAmt = parseFloat(document.frmScaleMaster.scaleStartAmt.value);
				if (IncrAmt != '' && IncrAmt != null) {
					var IncrAmt = parseFloat(document.frmScaleMaster.scaleIncrAmt.value);
				}
				var EndAmt = parseFloat(document.frmScaleMaster.scaleEndAmt.value);

				//Added by Mrugesh for Bigger Scale Value
				var HigherIncrAmt = parseFloat(document.frmScaleMaster.scaleHigherIncrAmt.value);
				var HigherEndAmt = parseFloat(document.frmScaleMaster.scaleHigherEndAmt.value);

				//Ended
				if (EndAmt < StartAmt) {
					alert("Please Enter Higher value of End Amount than Start Amount");
					document.frmScaleMaster.scaleEndAmt.value = "";
					document.frmScaleMaster.scaleEndAmt.focus();
					return false;
				}

				if (IncrAmt > (EndAmt - StartAmt)) {
					alert("Please Enter Lower value of Increment Amount than the difference of Start and End Amount");
					document.frmScaleMaster.scaleIncrAmt.value = "";
					document.frmScaleMaster.scaleIncrAmt.focus();
					return false;
				}

				var tempValue = EndAmt - StartAmt;
				/*
				 if( IncrAmt!=0 && tempValue%IncrAmt != 0){
				 alert("Please Enter value By Considering the Increment Value");
				 document.frmScaleMaster.scaleEndAmt.value="";
				 document.frmScaleMaster.scaleEndAmt.focus();
				 return false;
				 }
				 */
				//Added by Mrugesh for Bigger Scale Value
				if (higherIncrAmt != null && higherIncrAmt != '') {

					if (HigherIncrAmt < IncrAmt || HigherIncrAmt == IncrAmt) {
						alert('Higher Incr Amount must be greater than Incr Amount');
						document.frmScaleMaster.scaleHigherIncrAmt.value = '';
						document.frmScaleMaster.scaleHigherIncrAmt.focus();
						return false;
					}
					if (higherEndAmt == null || higherEndAmt == '') {
						alert('Please enter Higher End Amount');
						document.frmScaleMaster.scaleHigherEndAmt.focus();
						return false;
					}
					if (HigherIncrAmt > HigherEndAmt) {
						alert('Higher Incr Amount must be less than  to the Higher End Amount');
						document.frmScaleMaster.scaleHigherEndAmt.value = '';
						document.frmScaleMaster.scaleHigherEndAmt.focus();
						return false;
					}

					var properIncAmt = HigherEndAmt - EndAmt;

					if (properIncAmt % HigherIncrAmt != 0) {
						alert("Please Enter value By Considering the Higher Increment Value");
						document.frmScaleMaster.scaleHigherEndAmt.value = '';
						document.frmScaleMaster.scaleHigherEndAmt.focus();
						return false;
					}

				}

				//added by khushal

				var startSecondIncrAmt = parseFloat(document.frmScaleMaster.scaleSecondHigherIncrAmt.value);
				var endSecondIncrAmt = parseFloat(document.frmScaleMaster.scaleSecondHigherEndAmt.value);
				var startThirdIncrAmt = parseFloat(document.frmScaleMaster.scaleThirdHigherIncrAmt.value);
				var endThirdIncrAmt = parseFloat(document.frmScaleMaster.scaleThirdHigherEndAmt.value);

				if (startSecondIncrAmt != null && startSecondIncrAmt != '') {

					if (startSecondIncrAmt > endSecondIncrAmt
							|| startSecondIncrAmt == endSecondIncrAmt) {
						alert('Second end amount must be greater than Second Increment amount');
						document.frmScaleMaster.scaleSecondHigherEndAmt.value = '';
						document.frmScaleMaster.scaleSecondHigherEndAmt.focus();
						return false;
					}
					if (endSecondIncrAmt == null || endSecondIncrAmt == '') {
						alert('Please enter Second End Amount');
						document.frmScaleMaster.scaleSecondHigherEndAmt.focus();
						return false;
					}

				}

				if (startThirdIncrAmt != null && startThirdIncrAmt != '') {

					if (startThirdIncrAmt > endThirdIncrAmt
							|| startThirdIncrAmt == endThirdIncrAmt) {
						alert('Third end amount must be greater than Third Increment amount');
						document.frmScaleMaster.scaleThirdHigherEndAmt.value = '';
						document.frmScaleMaster.scaleThirdHigherEndAmt.focus();
						return false;
					}
					if (endThirdIncrAmt == null || endThirdIncrAmt == '') {
						alert('Please enter Third End Amount');
						document.frmScaleMaster.scaleThirdHigherEndAmt.focus();
						return false;
					}

				}
				if (document.getElementById('scaleSecondHigherIncrAmt') != null
						&& document.getElementById('scaleSecondHigherIncrAmt') != '') {
					var Length3 = document
							.getElementById("scaleSecondHigherIncrAmt").value.length
					for (i = 0; i < Length3; i++) {
						if (document.getElementById("scaleSecondHigherIncrAmt").value
								.charAt(i) == ".") {
							alert("Please Enter Integer value for Second Higher Increment Amount");
							document.frmScaleMaster.scaleHigherIncrAmt.focus();
							return false;
						}
					}
				}
				if (document.getElementById('scaleSecondHigherEndAmt') != null
						&& document.getElementById('scaleSecondHigherEndAmt') != '') {
					var Length3 = document
							.getElementById("scaleSecondHigherEndAmt").value.length
					for (i = 0; i < Length3; i++) {
						if (document.getElementById("scaleSecondHigherEndAmt").value
								.charAt(i) == ".") {
							alert("Please Enter Integer value for Second Higher Increment End Amount");
							document.frmScaleMaster.scaleHigherIncrAmt.focus();
							return false;
						}
					}
				}

				if (document.getElementById('scaleThirdHigherIncrAmt') != null
						&& document.getElementById('scaleThirdHigherIncrAmt') != '') {
					var Length3 = document
							.getElementById("scaleThirdHigherIncrAmt").value.length
					for (i = 0; i < Length3; i++) {
						if (document.getElementById("scaleThirdHigherIncrAmt").value
								.charAt(i) == ".") {
							alert("Please Enter Integer value for Third Higher Increment Amount");
							document.frmScaleMaster.scaleHigherIncrAmt.focus();
							return false;
						}
					}
				}
				if (document.getElementById('scaleThirdHigherEndAmt') != null
						&& document.getElementById('scaleThirdHigherEndAmt') != '') {
					var Length3 = document
							.getElementById("scaleThirdHigherEndAmt").value.length
					for (i = 0; i < Length3; i++) {
						if (document.getElementById("scaleThirdHigherEndAmt").value
								.charAt(i) == ".") {
							alert("Please Enter Integer value for Third Higher End Amount");
							document.frmScaleMaster.scaleHigherIncrAmt.focus();
							return false;
						}
					}
				}
				//end by khushal

				//if(higherEndAmt!=null && higherEndAmt!='')
				//{

				//	if(higherIncrAmt==null || higherIncrAmt=='')
				//{
				//alert('Please enter Higher Incr Amount');
				//document.frmScaleMaster.scaleHigherIncrAmt.focus();
				// return false;
				//}
				//}

			} else {
				document.frmScaleMaster.scaleStartAmt.value = '';

				document.frmScaleMaster.scaleEndAmt.value = '';
				document.frmScaleMaster.scaleHigherIncrementAmt.value = '';
				document.frmScaleMaster.scaleHigherEndAmt.value = '';
				document.frmScaleMaster.scaleStartAmt.focus();
				return false;
			}
		} else {
			//alert("enter the value for the all the amounts");

			return true;
		}

		return true;
	}

	function fieldDisplay() {

		var commId = document.frmScaleMaster.payCommissionCmbBx.value;

		var fifthPayComm = 2500340;
		var sixthPayComm = 2500341;
		var consoPayCom = 2500342;
		var nonGovPayComm = 2500343;
		var padmPayComm = 2500344;
		var fourthPayComm = 2500345;
		var shettyPayComm = 2500346;
		if (commId == fifthPayComm || commId == shettyPayComm
				|| commId == consoPayCom || commId == nonGovPayComm
				|| commId == padmPayComm || commId == fourthPayComm) {

			document.getElementById("scaleIncrAmt").readOnly = false;
			document.getElementById("gpRow").style.display = "none";
			document.getElementById("HighIncr").style.display = "block";
			document.getElementById("secondHighIncr").style.display = "block";
			document.getElementById("thirdHighIncr").style.display = "block";

		} else if (commId == sixthPayComm) {
			document.getElementById("scaleIncrAmt").readOnly = true;
			document.getElementById("gpRow").style.display = "block";
			document.getElementById("HighIncr").style.display = "none";
			document.getElementById("secondHighIncr").style.display = "none";
			document.getElementById("thirdHighIncr").style.display = "none";
		}
		function checkPayScaleType() {
			var payScaleType = document.frmScaleMaster.payScaleTypeCmbBx.value;
			if (payScaleType == null)
				alert("Please Select Pay Scale Type");
		}
	}

	function validateEndAmount() {
		var scaleStartAmt = eval(document.frmScaleMaster.scaleStartAmt.value);
		var scaleEndAmt = eval(document.frmScaleMaster.scaleEndAmt.value);

		//alert('scaleStartAmt is '+scaleStartAmt);
		//alert('scaleEndAmt is '+scaleEndAmt);
		if (eval(scaleEndAmt) < eval(scaleStartAmt)) {
			//alert('Please Enter End Amount greater than Start Amount');
			alert('End Amount should Not be Less than Start Amount');
			document.frmScaleMaster.scaleEndAmt.value = '';
			//document.frmScaleMaster.scaleEndAmt.focus();
			return false;
		}
	}
</script>

<fmt:formatDate var="startDate" pattern="dd/MM/yyyy"
	value="${scaleMstVO.scaleEffToDt}" />
<fmt:formatDate var="endDate" pattern="dd/MM/yyyy"
	value="${scaleMstVO.scaleEffFromDt}" />
<hdiits:form name="frmScaleMaster" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertScaleData&edit=N">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
							key="SM.insertScaleMaster" bundle="${commonLables}" /></b></a></li>
			<!--  <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li>-->
		</ul>
	</div>


	<%-- <div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0"><br>--%>

	<div id="tcontent1"
		style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

		<br /> <br />


		<TABLE width="75%" align="center" onkeypress="return chkKey(event)">
			<TR>
				<TD>
					<!-- NEW PAY COMMISSION  --> <hdiits:caption
						captionid="SM.PayCommission" bundle="${commonLables}" /> &nbsp;
				</TD>
				<TD><hdiits:select name="payCommissionCmbBx" mandatory="true"
						onchange="fieldDisplay();" caption="payCommission"
						id="payCommissionCmbBx" validation="sel.isrequired">
						<hdiits:option value="300388"> -- Select -- </hdiits:option>
						<c:forEach items="${payCommissionList}" var="payCommissionList">
							<option value="${payCommissionList.id}"><c:out
									value="${payCommissionList.commissionName}"></c:out></option>
						</c:forEach>
					</hdiits:select></TD>

				<TD>
					<!-- NEW PAY COMMISSION  --> <hdiits:caption
						captionid="SM.PayScaleType" bundle="${commonLables}" /> &nbsp;
				</TD>
				<TD><hdiits:select name="payScaleTypeCmbBx" mandatory="false"
						caption="payScaleType" id="payScaleType" validation="">
						<hdiits:option value="300388"> -- Select -- </hdiits:option>
						<c:forEach items="${payScaleTypeList}" var="payScaleTypeList">
							<option value="${payScaleTypeList.lookupId}"><c:out
									value="${payScaleTypeList.lookupName}"></c:out></option>
						</c:forEach>
					</hdiits:select></TD>

			</TR>
			<TR>
				<td></td>
				<td><hdiits:hidden name="scaleName" default=" "
						caption="scalename" />
				<TD colspan=2><hdiits:hidden name="scaleDesc" default=""
						caption="scaledesc" /></TD>
			</tr>

			<TR>
				<td><b><hdiits:caption captionid="OT.empScale"
							bundle="${commonLables}" /></b></td>



				<!-- Added by Mrugesh for Bigger Scale Value -->


				<!-- Ended by Mrugesh -->
			</tr>
			<tr></tr>
			<tr></tr>
			<TR>
				<TD><b><hdiits:caption captionid="SM.scaleStrAmt"
							bundle="${commonLables}" /></b></TD>
				<TD><hdiits:number mandatory="true" id="scaleStartAmt"
						name="scaleStartAmt" caption="Start Amount" default=""
						style="text-align:right" size="10" maxlength="10"
						validation="txt.isnumber,txt.isrequired" /></TD>
				<td><b><hdiits:caption captionid="SM.scaleIncAmt"
							bundle="${commonLables}" /></b></td>
				<td><hdiits:number id="scaleIncrAmt" name="scaleIncrAmt"
						caption="Increment Amount" default="" style="text-align:right"
						size="10" maxlength="10" /></td>
				<td><b><hdiits:caption captionid="SM.scaleEndAmt"
							bundle="${commonLables}" /></b></td>
				<TD><hdiits:number mandatory="true" id="scaleEndAmt"
						name="scaleEndAmt" default="" style="text-align:right"
						caption="End Amount" size="10" maxlength="10"
						validation="txt.isrequired,txt.isnumber"
						onblur="validateEndAmount()" /></TD>
			</tr>

			<TR id="gpRow" style="display: none;">
				<td colspan="1"><b><hdiits:caption captionid="eis.GradePay"
							bundle="${commonLables}" /></b></td>
				<TD colspan="3"><hdiits:text mandatory="true" id="gradePay"
						name="gradePay" caption="Grade Pay" captionid="eis.GradePay"
						bundle="${commonLables}" size="10" maxlength="10"
						validation="txt.isnumber" onkeypress="return chkKey(event)" /></TD>
			</TR>

			<tr id="HighIncr">
				<!-- Added by Mrugesh for Bigger Scale Value -->
				<td><b><hdiits:caption captionid="SM.scaleHigherIncAmt"
							bundle="${commonLables}" /></b></td>

				<TD><hdiits:number id="scaleHigherIncrAmt"
						style="text-align:right" name="scaleHigherIncrementAmt" default=""
						caption="Higher Increment Amount" size="10" maxlength="10"
						validation="txt.isnumber" /></TD>
				<td><b><hdiits:caption captionid="SM.scaleHigherEndAmt"
							bundle="${commonLables}" /></b></td>
				<TD><hdiits:number id="scaleHigherEndAmt"
						style="text-align:right" name="scaleHigherEndAmt" default=""
						caption="Higher End Amount" size="10" maxlength="10"
						validation="txt.isnumber" /></TD>
				<!-- Ended by Mrugesh -->
				<td></td>
			</TR>

			<tr id="secondHighIncr">
				<!-- Added by Mrugesh for Bigger Scale Value -->
				<td><b><hdiits:caption
							captionid="SM.scaleSecondHigherIncAmt" bundle="${commonLables}" /></b></td>

				<TD><hdiits:number id="scaleSecondHigherIncrAmt"
						style="text-align:right" name="scaleSecondHigherIncrementAmt"
						default="" caption="Higher Increment Amount" size="10"
						maxlength="10" validation="txt.isnumber" /></TD>
				<td><b><hdiits:caption
							captionid="SM.scaleSecondHigherEndAmt" bundle="${commonLables}" /></b></td>
				<TD><hdiits:number id="scaleSecondHigherEndAmt"
						style="text-align:right" name="scaleSecondHigherEndAmt" default=""
						caption="Higher End Amount" size="10" maxlength="10"
						validation="txt.isnumber" /></TD>
				<!-- Ended by Mrugesh -->
				<td></td>
			</TR>

			<tr id="thirdHighIncr">
				<!-- Added by Mrugesh for Bigger Scale Value -->
				<td><b><hdiits:caption
							captionid="SM.scaleThirdHigherIncAmt" bundle="${commonLables}" /></b></td>

				<TD><hdiits:number id="scaleThirdHigherIncrAmt"
						style="text-align:right" name="scaleThirdHigherIncrementAmt"
						default="" caption="Higher Increment Amount" size="10"
						maxlength="10" validation="txt.isnumber" /></TD>
				<td><b><hdiits:caption
							captionid="SM.scaleThirdHigherEndAmt" bundle="${commonLables}" /></b></td>
				<TD><hdiits:number id="scaleThirdHigherEndAmt"
						style="text-align:right" name="scalethirdHigherEndAmt" default=""
						caption="Higher End Amount" size="10" maxlength="10"
						validation="txt.isnumber" /></TD>
				<!-- Ended by Mrugesh -->
				<td></td>
			</TR>

			<TR>
			</TR>

			<TR>
				<td><b><hdiits:caption captionid="SM.scaleEFFD"
							bundle="${commonLables}" /></b></td>
				<TD><hdiits:dateTime mandatory="true" captionid="SM.scaleEFFD"
						bundle="${commonLables}" name="scaleEFD"
						validation="txt.isrequired,txt.isdt" minvalue=""
						onblur="chkDate()" /></TD>
			</TR>
			<TR>
				<td><b><hdiits:caption captionid="SM.scaleEFTD"
							bundle="${commonLables}" /></b></td>
				<TD><hdiits:dateTime captionid="SM.scaleEFTD"
						bundle="${commonLables}" name="scaleETD" validation="txt.isdt"
						minvalue="" onblur="chkDate()" /></TD>
			</TR>

			<TR>
				<td><b><hdiits:caption captionid="SM.IncrementDate"
							bundle="${commonLables}" /></b></td>
				<td><script>
					// alert ("inctDate:::::::::::ok " + "${resValue.inctDate}");
				</script> <fmt:formatDate var="scaleIncDat" value="${resValue.inctDate}"
						pattern="yyyy-MM-dd HH:mm:ss.s" dateStyle="medium" /> <!--    <fmt:formatDate var="loanSancOrderdate1" value="${loanSancOrderdate1}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />-->
					<script>
						// alert ("inctDate::::::::::: "+"${scaleIncDat}");
					</script> <hdiits:dateTime captionid="SM.IncrementDate"
						bundle="${commonLables}" name="scaleIncDat"
						validation="txt.isrequired,txt.isdt" mandatory=""
						default="${scaleIncDat}" /></TD>

			</TR>
		</TABLE>

		<br /> <br />
	</div>
	<%-- <div id="tcontent2" class="tabcontent" tabno="1"></div>
	--%>
	<br />
	<br />
	<div>
		<hdiits:jsField name="validateForm" jsFunction="validForm()" />
		<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables"
			scope="request" />
		<hdiits:hidden default="getScaleData" name="givenurl" />
		<jsp:include page="../../core/PayTabnavigation.jsp" /></div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if ("${msg}" != null && "${msg}" != '') {
			alert("${msg}");
			var url = "./hrms.htm?actionFlag=getScaleData";
			document.frmScaleMaster.action = url;
			document.frmScaleMaster.submit();
		}
	</script>

	<hdiits:validate controlNames="tesxt"
		locale='<%=(String) session.getAttribute("locale")%>' />

	<script type="text/javascript" language="javascript">
		var uniqueVal = "${flag}";

		if (uniqueVal == 1) {
			document.frmScaleMaster.scaleETD.value = "${startDate}";
			document.frmScaleMaster.scaleEFD.value = "${endDate}";
			document.frmScaleMaster.scaleIncDat.value = "${scaleMstVO.incrementDate}";
			alert("Duplicate Scale");
		}
		document.forms[0].elements['scaleStartAmt'].focus();
	</script>

</hdiits:form>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
