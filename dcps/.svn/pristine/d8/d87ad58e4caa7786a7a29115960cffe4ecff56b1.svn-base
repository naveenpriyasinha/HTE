
<%
	try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true"%>

<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>

<script type="text/javascript" src="common/script/commonfunctions.js"></script>
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
<style>/* Added by pratik 08-08-23  */
div#tcontent1 input, div#tcontent1 select {
	max-width: 210px !important;
}
</style>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="GISDeducDataList" value="${resValue.GISDeducDataList}"></c:set>
<c:set var="GISGradeDataList" value="${resValue.GISGradeDataList}"></c:set>
<c:set var="GISOrderDataList" value="${resValue.GISOrderDataList}"></c:set>

<c:set var="employeeName" value="${resValue.employeeName}"></c:set>
<c:set var="employeeId" value="${resValue.employeeId}"></c:set>
<c:set var="GISempDataList" value="${resValue.GISempDataList}"></c:set>
<c:set var="getEmpData" value="${resValue.getEmpData}"></c:set>
<c:set var="empDOJ" value="${resValue.empDOJ}"></c:set>
<c:set var="sevarthId" value="${resValue.sevarthId}"></c:set>
<fmt:formatDate value="${resValue.empDOJ}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="employeeDOJ" />
<c:set var="msg" value="${resValue.msg}"></c:set>


<script type="text/javascript">
	function submitFormAuto() {
		//alert("Inside submitFormAuto");

		var empId = "";
		if (document.getElementById("Employee_ID_EmpOtherSearch").value == document
				.getElementById("Employee_Name_EmpOtherSearch").value) {

			empId = document.getElementById("Employee_ID_EmpOtherSearch").value;
		} else {

			empId = document.getElementById("Employee_Name_EmpOtherSearch").value;
		}

		document.getElementById("Employee_ID_EmpOtherSearch").value = empId;

		empId = document.getElementById("Employee_ID_EmpOtherSearch").value;

		var url = "./ifms.htm?actionFlag=fillEmpChangeGISDtls&empId=" + empId;
		//alert("url::::::::::::::::::::::::::: "+url);
		document.frmEmpGISDtls.action = url;
		document.frmEmpGISDtls.submit();
		return true;
	}

	function DisplayOrderDate() {
		var xmlHttp = null;
		var orderId = document.getElementById("cmbOrder").value;

		var retValue = true;
		if (orderId == "") {
			alert("Please search the Order Name");
			retValue = false;
		} else {

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
			var url = "hrms.htm?actionFlag=getGISOrderDate&orderId=" + orderId
					+ "";
			xmlHttp.onreadystatechange = function() {

				if (xmlHttp.readyState == 4) {

					if (xmlHttp.status == 200) {
						//alert("inside 200");

						var oderDate;

						var XMLDocForAjax = xmlHttp.responseXML.documentElement;

						var orderDate = XMLDocForAjax
								.getElementsByTagName('getOldOrderDate');

						if (orderDate.length != 0) {

							orderGISDate = orderDate[0].childNodes[0].text;
							//orderGISDate = orderDate[0].childNodes[1].text;

							//alert("---> orderGISDate :: "+orderGISDate);
							//document.getElementById("orderDate").value=	orderGISDate;
							document.getElementById("orderDate").value = orderGISDate;
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
	}

	function resetForm() {
		if (confirm("All entered values will be cleared, please confirm!") == true) {
			document.frmEmpGISDtls.reset();
			document.getElementById("empName").value = '';
			document.getElementById("empId").value = '';
			document.getElementById("cmbCurrGisApplicable").value = -1;
			document.getElementById("cmbGisGroup").value = -1;
			document.getElementById("membershipDate").value = '';
		}
	}

	function onBackfn() {
		document.frmEmpGISDtls.action = './ifms.htm?actionFlag=getHomePage';

		document.frmEmpGISDtls.submit();
	}

	function saveFunction() {
		showProgressbar();
		if (document.getElementById("empIdforSearch").value == null
				|| document.getElementById("empIdforSearch").value == '') {
			alert("Please select Employee first");
			document.getElementById("Employee_ID_EmpOtherSearch").focus();
			return false;

		}
		document.frmEmpGISDtls.empId.readOnly = true;

		if (document.getElementById("cmbGisApplicable").value == -1) {
			alert("Please select GIS Applicable");
			document.getElementById("cmbGisApplicable").focus();
			return false;
		}

		if (document.getElementById("cmbGisGroupRev").value == -1) {
			alert("Please select Revised GIS Group");
			document.getElementById("cmbGisGroupRev").focus();
			return false;
		}

		var cmbGisGroup = document.getElementById("cmbGisGroup").value;
		var cmbGisGroupRev = document.getElementById("cmbGisGroupRev").value;

		/*	if(cmbGisGroup < cmbGisGroupRev)
			{
				alert("Please select Revised GIS Group higher than Current GIS Group");
				document.getElementById("cmbGisGroupRev").value= -1;
				document.getElementById("cmbGisGroupRev").focus();
			 return false;

			}*/

		if (document.frmEmpGISDtls.RevMemshipdate.value == null
				|| document.frmEmpGISDtls.RevMemshipdate.value == '') {

			alert("Please enter Membership Revised Date");
			document.getElementById("RevMemshipdate").value = "";
			document.getElementById("RevMemshipdate").focus();
			return false;

		}

		if (document.getElementById("txtOrder").value == null
				|| document.getElementById("txtOrder").value == "") {
			alert("Please select Order");
			document.getElementById("txtOrder").focus();//orderDate
			return false;
		}
		if (document.getElementById("orderDate").value == null
				|| document.getElementById("orderDate").value == "") {
			alert("Please select Order Date");
			document.getElementById("orderDate").focus();//orderDate
			return false;
		}
		if (document.getElementById("remarks").value == null
				|| document.getElementById("remarks").value == '') {
			alert("Please enter Remarks");
			document.getElementById("remarks").value = "";
			document.getElementById("remarks").focus();
			return false;

		}

		document.frmEmpGISDtls.Save.disabled = true;
		document.frmEmpGISDtls.action = 'hrms.htm?actionFlag=updateEmpChangedGISData&edit=N';
		document.frmEmpGISDtls.submit();
		alert("Revised GIS Details Updated");

	}

	/*function chkGISGroup()
	 {
	 var cmbGisGroup = document.getElementById("cmbGisGroup").value;
	 var cmbGisGroupRev = document.getElementById("cmbGisGroupRev").value;

	 if(cmbGisGroup < cmbGisGroupRev)
	 {
	 alert("Please select Revised GIS Group higher than Current GIS Group");
	 document.getElementById("cmbGisGroupRev").value= -1;
	 document.getElementById("cmbGisGroupRev").focus();
	 return false;

	 }
	 }*/

	function chkRevMemDate() {
		var revMemDate = document.frmEmpGISDtls.RevMemshipdate.value;
		var day = parseInt((revMemDate.substring(0, 2)), 10);
		var month = parseInt((revMemDate.substring(3, 5)), 10);

		if (month > 1 || day > 1) {
			alert("Revised Membership date should be first day of the year");
			document.getElementById("RevMemshipdate").value = "";
			document.getElementById("RevMemshipdate").focus();
			return false;

		}

		var empDOJ = "${employeeDOJ}";

		diff = compareDate(empDOJ, revMemDate);
		//alert("diff" + diff);

		if (diff < 0) {
			alert("Membership date should not be less than Employee Joining date  "
					+ empDOJ);
			document.getElementById("RevMemshipdate").value = "";
			document.getElementById("RevMemshipdate").focus();
			return false;
		}

	}

	function isSpclChar() {
		var iChars = "~`!@#$%^&*()+=-_[]\\\';,./{}|\":<>?";
		for (var i = 0; i < document.frmEmpGISDtls.remarks.value.length; i++) {
			if (iChars.indexOf(document.frmEmpGISDtls.remarks.value.charAt(i)) != -1) {
				alert("Special characters are not allowed in Remarks.");
				document.getElementById("remarks").value = "";
				// alert("----> " +document.getElementById("remarks").value);
				document.getElementById("remarks").focus();

				return false;
			}
		}
	}
</script>

<body>
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="resources.eis.eis_common_lables"
		var="commonLables" scope="page" />


	<hdiits:form name="frmEmpGISDtls" validate="true" method="POST"
		action="" encType="text/form-data">
		<%-- <hdiits:form name="empLoan" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertEmpLoanDtls&edit=N" encType="text/form-data">	--%>


		<div id="tabmenu">
			<ul id="maintab" class="shadetabs" compact="compact">
				<li class="selected"><a href="#" rel="tcontent1"><font
						size="2"><b><fmt:message key="CG.ChangeGISDetails"
									bundle="${commonLables}" /></b></a></li>
			</ul>
		</div>
		<div id="tcontent1"
			style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">
			<c:choose>
				<c:when test="${empAllRec!='true'}">
					<c:set value="display:show" var="displayStyle" />
				</c:when>
				<c:otherwise>
					<c:set value="display:none" var="displayStyle" />
					<hdiits:hidden name="Employee_ID_EmpSearch" default="${empId}" />

				</c:otherwise>
			</c:choose>
			<br>


			<table width="85%" align="center" style="${displayStyle}" border="1">
				<tr>
					<TD class="fieldLabel" colspan="4" align="center"><jsp:include
							page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
							<jsp:param name="searchEmployeeTitle" value="Search Employee" />
							<jsp:param name="SearchEmployee" value="EmpOtherSearch" />
							<jsp:param name="formName" value="frmEmpGISDtls" />
							<jsp:param name="functionName" value="submitFormAuto" />
						</jsp:include></td>
				</tr>
			</table>


			<table align="center" width="85%">

				<tr>
					<!-- <td><hdiits:caption captionid="OT.otherId" bundle="${commonLables}"/></td>
		<td><hdiits:text name="otherId" default="" caption="otherId"  maxlength="10" readonly="true" style="background:gray;color:white" size="20"/> 
		</td> -->
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>

					<TD width="12%" align="left" class="fieldLabel"><b><hdiits:caption
								captionid="OT.empName" bundle="${commonLables}"></hdiits:caption></b>
					</TD>
					<td width="30%" align="left"><hdiits:text name="empName"
							default="${employeeName}" caption="empName" readonly="true"
							size="25" /></td>

					<!--<td width="12%" align="center"  class="fieldLabel" >
-->
					<hdiits:hidden name="empIdforSearch" default="${employeeId}" />
					<td width="8%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<TD width="16%" align="left" class="fieldLabel"
						style="display: none;"><b><hdiits:caption
								captionid="OT.empId" bundle="${commonLables}"></hdiits:caption></b>
					</td>
					<td width="30%" align="left" style="display: none;"><hdiits:text
							name="empId" default="${employeeId}" caption="empId"
							readonly="true" size="25" /></td>

					<TD width="16%" align="left" class="fieldLabel"><b><hdiits:caption
								captionid="OT.sevarthId" bundle="${commonLables}"></hdiits:caption></b>
					</td>
					<td width="30%" align="left"><hdiits:text name="sevarthId"
							default="${sevarthId}" caption="sevarthId" readonly="true"
							size="25" /></td>
				</tr>

			</table>


			<hdiits:hidden name="empGISId" default="${getEmpData.empGISId}" />
			<!--<hdiits:hidden name="empDOJ" default="${empDOJ}" />
	
	-->
			<hdiits:fieldGroup titleCaptionId="CG.CurrentDetails"
				bundle="${commonLables}">
				<table width="80%" align="center" cellpadding="4" cellspacing="4">

					<tr>
						<td width="15%" align="left"><fmt:message
								key="CG.GISAPPLICABLE" bundle="${commonLables}"></fmt:message></td>
						<td width="20%" align="left"><select
							name="cmbCurrGisApplicable" id="cmbCurrGisApplicable"
							style="width: 240px" disabled="disabled">
								<option value="-1"><fmt:message
										key="COMMON.DROPDOWN.SELECT" /></option>
								<option value="${getEmpData.hrPayDeducTypeMst.deducCode}"
									selected="selected">
									<c:out value="${getEmpData.hrPayDeducTypeMst.deducName}"></c:out></option>
						</select></td>

						<td width="15%" align="left"><fmt:message key="CG.GISGROUP"
								bundle="${commonLables}"></fmt:message></td>
						<td width="20%" align="left"><select name="cmbGisGroup"
							id="cmbGisGroup" style="width: 240px" disabled="disabled">
								<option value="-1"><fmt:message
										key="COMMON.DROPDOWN.SELECT" /></option>
								<option value="${getEmpData.orgGradeMst.gradeId}"
									selected="selected">
									<c:out value="${getEmpData.orgGradeMst.gradeName}"></c:out></option>
						</select>
					</tr>

					<TR>
						<td width="20%"><b><hdiits:caption
									captionid="CG.MEMBERSHIPDATE" bundle="${commonLables}" /></b></td>
						<td width="20%"><script>
							//alert ("inctDate:::::::::::ok " + "${resValue.inctDate}");
						</script> <fmt:formatDate var="scaleIncDat"
								value="${getEmpData.membershipDate}"
								pattern="yyyy-MM-dd HH:mm:ss.s" dateStyle="medium" /> <!--    <fmt:formatDate var="loanSancOrderdate1" value="${loanSancOrderdate1}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />-->
							<script>
								//alert ("inctDate::::::::::: "+"${scaleIncDat}");
							</script> <hdiits:dateTime captionid="CG.MEMBERSHIPDATE"
								bundle="${commonLables}" name="membershipDate"
								validation="txt.isrequired,txt.isdt" mandatory=""
								default="${getEmpData.membershipDate}" disabled="true" /></TD>


					</TR>

				</table>
			</hdiits:fieldGroup>

			<hdiits:fieldGroup titleCaptionId="CG.ReviseedDetails"
				bundle="${commonLables}">
				<table width="80%" align="center" cellpadding="4" cellspacing="4">

					<tr>
						<td width="15%" align="left"><fmt:message
								key="CG.GISAPPLICABLE" bundle="${commonLables}"></fmt:message></td>
						<td width="20%" align="left"><select name="cmbGisApplicable"
							id="cmbGisApplicable" style="width: 240px">
								<option value="-1"><fmt:message
										key="COMMON.DROPDOWN.SELECT" /></option>
								<c:forEach items="${GISDeducDataList}" var="GISDeducDataList">
									<option value="${GISDeducDataList.deducCode}"><c:out
											value="${GISDeducDataList.deducName}"></c:out></option>
								</c:forEach>
						</select> <label class="mandatoryindicator" ${varLabelDisabled}>*</label></td>

						<td width="15%" align="left"><fmt:message
								key="CG.RevicedGISGROUP" bundle="${commonLables}"></fmt:message></td>
						<td width="20%" align="left"><select name="cmbGisGroupRev"
							id="cmbGisGroupRev" style="width: 240px">
								<option value="-1"><fmt:message
										key="COMMON.DROPDOWN.SELECT" /></option>
								<c:forEach items="${GISGradeDataList}" var="GISGradeDataList">
									<option value="${GISGradeDataList.gradeId}"><c:out
											value="${GISGradeDataList.gradeName}"></c:out></option>
								</c:forEach>

						</select> <label class="mandatoryindicator" ${varLabelDisabled}>*</label></td>
					</tr>

					<TR>
						<td width="20%"><b><hdiits:caption
									captionid="CG.RevicedMEMBERSHIPDATE" bundle="${commonLables}" /></b></td>
						<td width="20%"><fmt:formatDate var="scaleIncDat"
								value="${resValue.inctDate}" pattern="yyyy-MM-dd HH:mm:ss.s"
								dateStyle="medium" /> <hdiits:dateTime
								captionid="CG.RevicedMEMBERSHIPDATE" bundle="${commonLables}"
								name="RevMemshipdate" validation="txt.isrequired,txt.isdt"
								mandatory="" default="${scaleIncDat}"
								afterDateSelect="chkRevMemDate()" /> <label
							class="mandatoryindicator" ${varLabelDisabled}>*</label></td>
						</TD>

					</TR>

				</table>
			</hdiits:fieldGroup>



			<hdiits:fieldGroup titleCaptionId="CG.VideOrder/Ref.LetterDetails"
				bundle="${commonLables}">
				<table width="80%" align="center" cellpadding="4" cellspacing="4">

					<tr>
						<td width="15%" align="left"><fmt:message
								key="CG.Order/Ref.LetterNo" bundle="${commonLables}"></fmt:message></td>
						<!-- <td width="20%" align="left"><select name="cmbOrder"id="cmbOrder" style="width: 240px"  onchange="DisplayOrderDate()">
						<option value="-1" ><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach items="${GISOrderDataList}" var="GISOrderDataList">
							<option value="${GISOrderDataList.orderId}"><c:out
							value="${GISOrderDataList.orderName}"></c:out></option>
						</c:forEach>
					</select>
					<label class="mandatoryindicator" ${varLabelDisabled}>*</label></td> -->
						<td width="20%" align="left"><hdiits:text name="txtOrder"
								id="txtOrder" style="width: 240px" default="" mandatory="true" />
						<td width="15%"><b><hdiits:caption
									captionid="CG.OrderDate" bundle="${commonLables}" /></b></td>
						<td width="20%">
							<!--  <fmt:formatDate var="loanSancOrderdate1" value="${loanSancOrderdate1}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />-->
							<hdiits:dateTime captionid="CG.OrderDate"
								bundle="${commonLables}" name="orderDate"
								validation="txt.isrequired,txt.isdt" mandatory="true" default="" />
							<!--<label class="mandatoryindicator" ${varLabelDisabled}>*</label>-->
						</td>

					</tr>

					<TR>

						<td width="20%" align="left"><fmt:message key="CG.Remarks"
								bundle="${commonLables}"></fmt:message></td>
						<td width="20%" align="left"><hdiits:text id="remarks"
								name="remarks" captionid="CG.Remarks" bundle="${commonLables}"
								size="30" maxlength="100" validation="txt.isrequired"
								onchange="isSpclChar()" /> <label class="mandatoryindicator"
							${varLabelDisabled}>*</label></td>

					</TR>

				</table>
			</hdiits:fieldGroup>



		</div>
		<div>
			<hdiits:jsField name="validateForm" jsFunction="validForm()" />
			<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables"
				scope="request" />
			<hdiits:hidden default="getScaleData" name="givenurl" />
			<br />
			<table class="tabNavigationBar">
				<tr>

					<td class="tabnavtd" id="tabnavtd" align="justify" width="35%">
					</td>
					<td align="justify" width="2%"><hdiits:button type="button"
							name="Save" id="Save" onclick="saveFunction()" value="Save" /></td>
					<td align="justify" width="2%"><hdiits:button
							name="backButton" type="button" value="Close"
							onclick="onBackfn()" /></td>
					<td align="justify" width="2%"><hdiits:button type="button"
							value="Reset" name="resetButton" onclick="resetForm()" /></td>
					<td width="30%"></td>

				</tr>
			</table>


		</div>

		<script type="text/javascript">
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
			initializetabcontent("maintab")
			if ("${msg}" != null && "${msg}" != '') {
				alert("${msg}");
				//var url="./hrms.htm?actionFlag=getScaleData";
				//document.frmScaleMaster.action=url;
				//document.frmScaleMaster.submit();
			}
		</script>


		<hdiits:validate controlNames="tesxt"
			locale='<%=(String) session.getAttribute("locale")%>' />
	</hdiits:form>
</body>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>