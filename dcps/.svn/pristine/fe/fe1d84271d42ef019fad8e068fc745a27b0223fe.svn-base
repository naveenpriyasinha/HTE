<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script>
	document.getElementById("banner").src = "images/HomePageImages/FianlHomePG_1_DCPS.jpg";
</script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="nsdlDeatils" value="${resValue.nsdlDeatils}"></c:set>
<c:set var="selMonth" value="${resValue.selMonth}"></c:set>
<c:set var="selYear" value="${resValue.selYear}"></c:set>
<c:set var="lLstYears" value="${resValue.lLstYears}"></c:set>
<c:set var="lLstMonths" value="${resValue.lLstMonths}"></c:set>
<c:set var="size" value="${resValue.size}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="viewmsg" value="${resValue.viewmsg}"></c:set>
<c:set var="successmsg" value="${resValue.successmsg}"></c:set>
<c:set var="beamsSucessMsg" value="${resValue.beamsSucessMsg}"></c:set>
<c:set var="finalMsg" value="${resValue.finalMsg}"></c:set>
<c:set var="count" value="1"></c:set>
<c:set var="errFilePath" value="resValue.errFilePtah"></c:set>
<c:set var="FvuFilePath" value="resValue.fvuFilePtah"></c:set>
<c:set var="Fileno" value="resValue.Fileno"></c:set>
<c:set var="deputationFlag" value="${resValue.idDeputation}"></c:set>

<!-- For BEAMS Integration starts -->
<c:set var="authNo" value="${resValue.authNo}"></c:set>
<c:set var="statusCode" value="${resValue.statusCode}"></c:set>
<c:set var="finalMsg" value="${resValue.finalMsg}"></c:set>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>


<script type="text/javascript">
	if ('${viewmsg}' != null && '${viewmsg}' != '') {
		//alert("msg is"+'${msg}');
		alert("${viewmsg}");

		var uri = "ifms.htm?actionFlag=loadNPSNSDLUpdate";
		window.location.href = uri;
	}
</script>
<script type="text/javascript">
	if ('${viewmsg}' != null && '${viewmsg}' != '') {
		//alert("msg is"+'${msg}');
		alert("${viewmsg}");

		var uri = "ifms.htm?actionFlag=loadNPSNSDLUpdate";
		window.location.href = uri;
	}
</script>
<script type="text/javascript">
	if ('${successmsg}' != null && '${successmsg}' != '') {
		//alert("msg is"+'${msg}');
		alert("${successmsg}");

		var uri = "ifms.htm?actionFlag=loadNPSNSDLGenFiles";
		window.location.href = uri;
	}
</script>
<script type="text/javascript">
	if ('${finalMsg}' != null && '${finalMsg}' != '') {
		//alert("msg is"+'${msg}');
		alert("${finalMsg}");

		var uri = "ifms.htm?actionFlag=loadNPSNSDLGenFiles";
		window.location.href = uri;
	}
</script>
<script type="text/javascript">
	if ('${beamsSucessMsg}' != null && '${beamsSucessMsg}' != '') {
		//alert("msg is"+'${msg}');
		alert("${beamsSucessMsg}");

		var uri = "ifms.htm?actionFlag=loadNPSNSDLGenFiles";
		window.location.href = uri;
	}
</script>
<script type="text/javascript">
	function generateFile() {
		var len = '${size}';
		var isChecked = false;
		//	var deputation=document.getElementById("deputationFlag").value;
		var deputation = 2;

		for (var i = 0; i < len; i++) {
			if (document.getElementById("fileNo" + i).checked) {
				isChecked = true;
				var fileNumber = document.getElementById("fileNo" + i).value;
				break;
			}
		}

		if (!isChecked) {
			alert("Please select atleast one File. ");
			return false;
		}

		else {
			var answer = confirm('Do you want to Generate the file?');
			if (answer) {

				var url = "";
				var uri = "ifms.htm?actionFlag=createTextFilesForNSDL&fileNumber="
						+ fileNumber + "&deputationFlag=" + deputation;
				window.location.href = uri;
			} else {
				return false;
			}
			return true;
		}

		showProgressbar("Please Wait ");

	}

	function deleteGenFile() {

		var len = '${size}';
		var isChecked = false;
		for (var i = 0; i < len; i++) {
			if (document.getElementById("fileNo" + i).checked) {
				isChecked = true;
				var fileNumber = document.getElementById("fileNo" + i).value;
				break;
			}
		}

		if (!isChecked) {
			alert("Please select atleast one File.");
			return false;
		}

		else {
			var answer = confirm('Are You Sure, you want to Delete the file?');
			if (answer) {

				var uri = "ifms.htm?actionFlag=deleteTextFilesForNSDL";
				var url = "&fileNumber=" + fileNumber;
				var myAjax = new Ajax.Request(uri, {
					method : 'post',
					asynchronous : false,
					parameters : url,
					onSuccess : function(myAjax) {
						getdelMsg(myAjax);
					},
					onFailure : function() {
						alert('Something went wrong...');
					}
				});

			}
		}
	}

	function getdelMsg(myAjax) {
		XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var lBlFlag = XmlHiddenValues[0].childNodes[0].text;
		if (lBlFlag == "true") {
			alert("Selected file has been deleted successfully.");
			var url = "";
			var uri = "ifms.htm?actionFlag=loadNPSNSDLUpdate";
			window.location.href = uri;
		}
	}

	function viewErrorReport(id, empCont, emplrCont) {
		var url = "";
		var uri = "ifms.htm?actionFlag=createErrorFilesForNSDL&fileNumber="
				+ id;
		window.location.href = uri;

	}

	function openWindow1(Month, Year, fileNumber) {
		//window.showModalDialog("./hrms.htm?viewName=ApproveBillWindow&trnbill="+trnbill+"&Month="+Month+"&Year="+Year,"","dialogWidth:900px; dialogHeight:400px; center:yes; status:yes;menubar=yes");
		//alert("openWindow new window");
		//alert("Month new window"+Month);
		//alert("Year new window"+Year);
		//alert("fileNumber new window"+fileNumber);
		//var urlstyle = 'height=400,width=900,toolbar=no,minimize=no,resizable=no,status=yes,memubar=yes,location=no,scrollbars=yes,top=20,left=200';
		//var url = "ifms.htm?viewName=approveNSDLBill&Month="+Month+"&Year="+Year+"&fileNumber="+fileNumber;
		//window.open(url,"",urlstyle);

		var urlstyle = 'height=400,width=900,toolbar=no,minimize=no,resizable=no,status=yes,memubar=yes,location=no,scrollbars=yes,top=20,left=200';
		var url = "./hrms.htm?viewName=approveNSDLBill&fileNumber="
				+ fileNumber;
		window.open(url, "", urlstyle);

	}

	function validateFile() {
		var len = '${size}';
		//alert(len);
		var isChecked = false;
		var Month = document.getElementById("cmbMonth").value;
		var Year = document.getElementById("cmbYear").value;
		//var deputation=document.getElementById("deputationFlag").value;
		var deputation = 2;

		var index = -1;
		for (var i = 0; i < len; i++) {
			if (document.getElementById("fileNo" + i).checked) {
				isChecked = true;
				var fileNumber = document.getElementById("fileNo" + i).value;
				//alert('fileNumber'+fileNumber);
				index = i;

				break;
			}
		}

		if (!isChecked) {
			alert('Please select atleast one File.');
			return false;
		} else {
			var uri = "ifms.htm?actionFlag=validateFileNSdl&Fileno="
					+ fileNumber + "&deputationFlag=" + deputation;
			//document.uploadLoan.action = "./hrms.htm?actionFlag=approvePayBill&month="+'${Month}'+"&year="+'${Year}'+"&billNo="+'${trnbill}'+"&voucherNo="+vouchenumber+"&voucherDate="+vouchedate;// A= Approve
			window.location.href = uri;
		}
	}

	function openWindow(Month, Year, fileNumber) {

		var filePath = "";
		document.frmDCPSMatchedEntries.action = "./hrms.htm?actionFlag=validateFileNSdl&Fileno="
				+ '${fileNumber}' + "&filePath=" + filePath;
		document.frmDCPSMatchedEntries.submit();

	}
	function getEmployeeReport(fileId) {
		// alert(fileId);
		var fileNo = fileId;
		var year = document.getElementById("cmbYear").options[document
				.getElementById("cmbYear").selectedIndex].text;
		var month = document.getElementById("cmbMonth").options[document
				.getElementById("cmbMonth").selectedIndex].value;

		url = "ifms.htm?actionFlag=reportService&reportCode=9000171&action=generateReport&fileNo="
				+ fileNo + "&year=" + year + "&month=" + month;
		showProgressbar();
		self.location.href = url;

	}

	function ShowTokenDetails() {

		var uri = "ifms.htm?actionFlag=loadNPSNSDLGenFiles";
		window.location.href = uri;
	}

	function getNsdlEntries() {

		showProgressbar();
		var cmbYear = document.getElementById("cmbYear").value;
		var cmbMonth = document.getElementById("cmbMonth").value;
		var trCode = document.getElementById("cmbTr").value;
		//	var cmbDep = document.getElementById("cmbDep").value ;
		var cmbDep = 2;

		if (cmbYear == -1) {
			alert('Please select year to confirm request.');
			hideProgressbar();
			return;
		} else if (cmbMonth == "") {
			alert('Please select month to confirm request.');
			hideProgressbar();
			return;
		} else if (cmbDep == -1) {
			alert('Please select if deputation employees to confirm request.');
			hideProgressbar();
			return;
		}

		else {

			self.location.href = 'ifms.htm?actionFlag=loadNPSNSDLGenFiles&cmbYear='
					+ cmbYear
					+ '&cmbMonth='
					+ cmbMonth
					+ '&trCode='
					+ trCode
					+ '&cmbDep=' + cmbDep;
		}

	}

	function openWindowtoSendFile(Month, Year, fileNumber) {

		var urlstyle = 'height=400,width=900,toolbar=no,minimize=no,resizable=no,status=yes,memubar=yes,location=no,scrollbars=yes,top=20,left=200';
		var url = "./hrms.htm?viewName=SendNSDLFiles&fileNumber=" + fileNumber;
		window.open(url, "", urlstyle);

	}
	function disableEnableBtn() {
		var len = '${size}';
		//alert('disableEnableBtn');
		var isChecked = false;

		var index = -1;
		for (var i = 0; i < len; i++) {
			if (document.getElementById("fileNo" + i).checked) {
				isChecked = true;
				var fileNumber = document.getElementById("fileNo" + i).value;
				//alert('fileNumber'+fileNumber);
				index = i;

				break;
			}
		}
		if (!isChecked) {
			alert('Please select atleast one File.');
			return false;
		}

		var billStatus = document.getElementById("statusBill" + index).value;

		var FileStatus = document.getElementById("FileStatus" + index).value;

		//alert("billStatus is"+billStatus);
		//alert("FileStatus is"+FileStatus);

		//	alert("inside else");

		if (FileStatus == 0) {
			//	alert('hello 3111');
			document.getElementById("delete").disabled = false;
			document.getElementById("validate").disabled = false;
			document.getElementById("generate").disabled = true;
			document.getElementById("Forward").disabled = true;
			document.getElementById("send").disabled = true;
			document.getElementById("save").disabled = false;
			document.getElementById("viewBill").disabled = true;
			document.getElementById("vEnrty").disabled = true;
		}

		if (FileStatus == 2) {
			//	alert('hello 31113423');
			document.getElementById("delete").disabled = false;
			document.getElementById("validate").disabled = false;
			document.getElementById("generate").disabled = true;
			document.getElementById("Forward").disabled = true;
			document.getElementById("send").disabled = true;
			document.getElementById("save").disabled = false;
			document.getElementById("viewBill").disabled = true;
			document.getElementById("vEnrty").disabled = true;
		}

		if (FileStatus == 3) {
			//alert('hello 3111e423423');
			document.getElementById("delete").disabled = false;
			document.getElementById("validate").disabled = false;
			document.getElementById("generate").disabled = true;
			document.getElementById("Forward").disabled = true;
			document.getElementById("send").disabled = true;
			document.getElementById("save").disabled = false;
			document.getElementById("viewBill").disabled = true;
			document.getElementById("vEnrty").disabled = true;
		}

		if (FileStatus == 1) {
			//alert('hello 311wer34441');
			document.getElementById("validate").disabled = true;
			document.getElementById("delete").disabled = true;
			document.getElementById("generate").disabled = true;
			document.getElementById("Forward").disabled = true;
			document.getElementById("send").disabled = false;
			document.getElementById("save").disabled = false;
			document.getElementById("viewBill").disabled = true;
			document.getElementById("vEnrty").disabled = true;
		}
		if (FileStatus == 11) {
			document.getElementById("validate").disabled = true;
			document.getElementById("delete").disabled = true;
			document.getElementById("generate").disabled = true;
			document.getElementById("Forward").disabled = true;
			document.getElementById("send").disabled = true;
			document.getElementById("save").disabled = false;
			document.getElementById("viewBill").disabled = false;
			document.getElementById("vEnrty").disabled = true;
		}
		if (FileStatus == 8) {
			document.getElementById("validate").disabled = true;
			document.getElementById("delete").disabled = true;
			document.getElementById("generate").disabled = true;
			document.getElementById("Forward").disabled = true;
			document.getElementById("send").disabled = false;
			document.getElementById("save").disabled = false;
			document.getElementById("viewBill").disabled = true;
			document.getElementById("vEnrty").disabled = true;
		}
		if (FileStatus == 5) {
			document.getElementById("validate").disabled = true;
			document.getElementById("delete").disabled = true;
			document.getElementById("generate").disabled = true;
			document.getElementById("Forward").disabled = true;
			document.getElementById("send").disabled = true;
			document.getElementById("save").disabled = false;
			document.getElementById("viewBill").disabled = true;
			document.getElementById("vEnrty").disabled = true;
		}
		if (FileStatus == 3) {
			document.getElementById("validate").disabled = true;
			document.getElementById("delete").disabled = true;
			document.getElementById("generate").disabled = true;
			document.getElementById("Forward").disabled = true;
			document.getElementById("send").disabled = true;
			document.getElementById("save").disabled = true;
			document.getElementById("viewBill").disabled = true;
			document.getElementById("vEnrty").disabled = true;
		}

	}

	function openAuthSlipPDF(authNo) {
		var urlstring = "hrms.htm?actionFlag=getAuthSlipForNPS&authNo="
				+ authNo;
		var urlstyle = "height=600,width=1000,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
		window.open(urlstring, "viewAuthSlipPDF", urlstyle);
	}
</script>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="DCPSLables"
	scope="request" />
<hdiits:form name="frmDCPSMatchedEntries" encType="multipart/form-data"
	validate="true" method="post">

	<c:set var="resultObj" value="${result}"></c:set>
	<c:set var="resValue" value="${resultObj.resultValue}" />
	<c:set var="entries" value="${resValue.EntryList}" />

	<input type="hidden" name='txtDdoCode' id="txtDdoCode"
		style="text-align: left" value="${resValue.DDOCODE}" />


	<input type="hidden" name='errFilePth' id="errFilePth"
		style="text-align: left" value="${resValue.errFilePtah}" />

	<input type="hidden" name='FvuFilePth' id="FvuFilePth"
		style="text-align: left" value="${resValue.fvuFilePtah}" />

	<input type="hidden" name='filename' id="filename"
		style="text-align: left" value="${resValue.Fileno}" />





	<br />
	<fieldset class="tabstyle">
		<legend>
			<b>Search NSDL Files</b>
		</legend>
		<table id="table1" align="center">

			<tr>
				<td width="4%" style="padding-left: 5%"><fmt:message
						key="CMN.Year" bundle="${dcpsLables}"></fmt:message></td>

				<td width="20%"><select name="cmbYear" id="cmbYear"
					style="width: 50%;" onChange="">
						<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="year" items="${resValue.lLstYears}">
							<c:choose>
								<c:when test="${resValue.selYear == year.id}">
									<option value="${year.id}" selected="selected"><c:out
											value="${year.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${year.id}"><c:out value="${year.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select> <label class="mandatoryindicator">*</label></td>

				<td width="4%" style="padding-left: 5%"><fmt:message
						key="CMN.Month" bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%"><select name="cmbMonth" id="cmbMonth"
					style="width: 50%;">
						<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="month" items="${resValue.lLstMonths}">
							<c:choose>
								<c:when test="${resValue.selMonth == month.id}">
									<option value="${month.id}" selected="selected"><c:out
											value="${month.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${month.id}"><c:out
											value="${month.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select> <label class="mandatoryindicator">*</label></td>


				<td width="4%" style="padding-left: 5%">Treasury</td>
				<td width="20%"><select name="cmbTr" id="cmbTr"
					style="width: 50%;">
						<c:forEach var="subTr" items="${resValue.subTr}">
							<option value="${subTr.id}" selected="selected"><c:out
									value="${subTr.desc}"></c:out></option>
						</c:forEach>
				</select> <label class="mandatoryindicator">*</label></td>

			</tr>
		</table>
		<center>
			<hdiits:button type="button" caption="Search" id="submit"
				name="Search" onclick="getNsdlEntries();"></hdiits:button>
		</center>
	</fieldset>
	<br />


	<br />





	<table width="100%" border="1">

		<tr>
			<td align="center" width="5%"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
				<b>Select</b>
			</td>
			<td align="center" width="12%"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
				<b>File Id</b>
			</td>
			<!-- Added by Ashish -->
			<td align="center" width="12%"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Amount
					of Employer Contribution</b></td>
			<td align="center" width="12%"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Amount
					of Employee Contribution</b></td>
			<td align="center" width="12%"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Transaction
					ID</b></td>
			<%--Added by roshan --%>
			<td align="center" width="11%"
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>File
					Validation Result</b></td>

		</tr>
		<%
			int i = 0;
		%>
		<c:forEach var="entry" items="${nsdlDeatils}">

			<tr>

				<td align="center"><input type="radio" name="fileNo"
					id="fileNo<%=i%>" value="${entry[0]}" onclick="disableEnableBtn();" /></td>

				<td align="center"><a href=#
					onclick="getEmployeeReport('${entry[0]}');"><c:out
							value="${entry[0]}" /></a></td>
				<!-- Ashish Wasake 10/02/2021-->
				<td align="center"><c:out value='${entry[2]}' /></td>
				<td align="center"><c:out value='${entry[1]}' /></td>
				<td align="center"><c:out value='${entry[3]}' /></td>
				<td align="center"><c:out value='${entry[4]}' /></td>

				<input type="hidden" id="employeeContribution<%=i %>"
					name="employeeContribution<%=i %>" value='${entry[1]}' />
				<input type="hidden" id="employerContribution<%=i %>"
					name="employerContribution<%=i %>" value='${entry[2]}' />

				<input type="hidden" id="statusBill<%=i %>" name="statusBill<%=i %>"
					value='${entry[8]}' />

				<input type="hidden" id="FileStatus<%=i %>" name="FileStatus<%=i %>"
					value='${entry[5]}' />
				<input type="hidden" name='deputationFlag' id="deputationFlag"
					style="text-align: left" value="${resValue.idDeputation}" />

				<input type="hidden" id="billId<%=i %>" name="billId<%=i %>"
					value='${entry[3]}' />





			</tr>
			<%
				i++;
			%>
			<c:set var="count" value="${count+1}"></c:set>
		</c:forEach>

	</table>
	<br /></br>
	<table align="center">
		<tr>


			<td><input type="button" name="save" class="buttontag"
				style="width: 100%" id="save" type="button"
				value="View and Save File" onclick="generateFile();" /></td>


				<td><input type="button" name="validate" class="buttontag"
				id="validate" type="button" value="Validate File"
				onclick="validateFile();" /></td>
				<td><input type="button" name="sendfile" class="buttontag" style="width: 100%"
				id="sendfile" type="button" value="Send Contribution File " onclick="sendContributionFile();" /></td>
				
				<td><input type="button" name="getFile" class="buttontag" style="width: 100%"
				id="getFile" type="button" value="Get Contribution File Status " onclick="getContriButionFile();" /></td>
				
				<td><input type="button" name="delete" class="buttontag"
				id="delete" type="button" value="Delete" onclick="deleteGenFile();" /></td>
 
		</tr>
	</table>
</hdiits:form>

