<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript">
	
</script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DDOCode" value="${resValue.DDOCode}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>



<fieldset class="tabstyle">
	<legend>Employee List</legend>

	<c:set var="srNoAllown" value="1"></c:set>
	<c:set var="tempFc" value=""></c:set>
	<div class="scrollablediv">
		<hdiits:form name="csrfForm" validate="true" method="POST" action="">
			<input type="hidden" id="checkCount" name="checkCount" value="0" />



			<table width="100%" border="1">

				<tr>
					<th align="center" width="5%" class="oddcentre">Select</th>
					<th align="center" width="12%">Sevaarth Id</th>
					<th align="center" width="12%">Employee Name</th>
					<th align="center" width="12%">Date of Birth</th>
					<th align="center" width="12%">Designation</th>
					<th align="center" width="11%">DDO Code</th>
					<th align="center" width="11%">Office Name</th>
					<th align="center" width="11%">DCPS ID</th>
					<th align="center" width="11%">File Creation</th>
					<th align="center" width="11%">verification status</th>
					<th align="center" width="11%">Upload Status</th>
					<th align="center" width="11%">NSDL Status</th>
				</tr>
				<%
					int i = 0;
				%>
				<c:forEach var="vo" items="${empList}">

					<tr>

						<td align="center"><c:if
								test="${!empty fn:trim(vo[7]) && !empty fn:trim(vo[8])}">
								<hdiits:checkbox readonly="true"
									id="selcheckBoxAllow${srNoAllown}"
									name="selcheckBoxAllow${srNoAllown}" value="1"
									onclick="checkApplyCmb('sevarthId${vo_rowNum}','${vo[0]}');" />
							</c:if> <c:if test="${empty fn:trim(vo[7]) && empty fn:trim(vo[8])}">
								<hdiits:checkbox id="selcheckBoxAllow${srNoAllown}"
									name="selcheckBoxAllow${srNoAllown}" value="1"
									onclick="checkApplyCmb(this,'sevarthId${vo_rowNum}','${vo[0]}');" />
							</c:if></td>

						<td headerClass="datatableheader" style="text-align: center;"
							class="oddcentre" sortable="true" title="Sevarth ID"><a
							href="#" onclick="chkAndOpenFormS1Edit('${vo_rowNum}');"><c:out
									value="${vo[0]}"></c:out></a> <input type="hidden"
							id="sevarthId${vo_rowNum}" value="" /></td>

						<td headerClass="datatableheader" style="text-align: left;"
							class="oddcentre" sortable="true" title="Employee Name"><c:out
								value="${vo[1]}"></c:out> <input type="hidden"
							id="empName${vo_rowNum}" value="${vo[1]}" /></td>

						<td headerClass="datatableheader" style="text-align: center;"
							class="oddcentre" sortable="true" title="Date of Joining">
							<c:out value="${vo[2]}"></c:out> <input type="hidden"
							id="DOJ${vo_rowNum}" value="${vo[2]}" />
						</td>

						<td headerClass="datatableheader" style="text-align: left;"
							class="oddcentre" sortable="true" title="Designation Name">
							<c:out value="${vo[3]}"></c:out> <input type="hidden"
							id="dsgnName${vo_rowNum}" value="${vo[3]}" />
						</td>

						<td headerClass="datatableheader" style="text-align: center;"
							class="oddcentre" sortable="true" title="DDO Code"><c:out
								value="${vo[4]}"></c:out> <input type="hidden"
							id="DDOCode${vo_rowNum}" value="${vo[4]}" /></td>

						<td headerClass="datatableheader" style="text-align: left;"
							class="oddcentre" sortable="true" title="Office Name"><c:out
								value="${vo[5]}"></c:out> <input type="hidden"
							id="officeCode${vo_rowNum}" value="${vo[5]}" /></td>

						<td headerClass="datatableheader" style="text-align: center;"
							class="oddcentre" sortable="true" title="DCPS ID"><c:out
								value="${vo[6]}"></c:out> <input type="hidden"
							id="DcpsId${vo_rowNum}" value="${vo[6]}" /></td>
						<c:if test="${!empty fn:trim(vo[7])}">
							<c:set var="tempFc" value="${fn:trim(vo[7])}"></c:set>
						</c:if>

						<td headerClass="datatableheader"><c:if
								test="${!empty fn:trim(vo[7]) && !empty fn:trim(vo[8])}">Text file Created</c:if>

							<c:if test="${empty fn:trim(vo[7]) && empty fn:trim(vo[8])}">Text file not Created</c:if>
						</td>

						<td headerClass="datatableheader" style="text-align: center;"
							class="oddcentre" sortable="true" group="1"
							title="Verification status"><c:if
								test="${empty fn:trim(vo[9]) && !empty fn:trim(vo[7]) && !empty fn:trim(vo[8])}">
								<a href="#" onclick="validateFile('${vo[8]}');">Validate</a>
							</c:if> <c:if test="${!empty fn:trim(vo[9])}">verified</c:if> <c:if
								test="${empty fn:trim(vo[7]) && empty fn:trim(vo[8])}"> Not verified </c:if>

						</td>
						<td headerClass="datatableheader" style="text-align: center;"
							class="oddcentre" sortable="true" group="2"
							title="File upload status"><c:if
								test="${empty fn:trim(vo[10]) && !empty fn:trim(vo[9]) }">
								<a href="#" onclick="sendFrom('${vo[8]}');">Upload File</a>
							</c:if> <c:if test="${empty fn:trim(vo[10]) && empty fn:trim(vo[9])}">File not uploaded </c:if>
							<c:if test="${!empty fn:trim(vo[10])}">${vo[10]} </c:if></td>
						<td headerClass="datatableheader" style="text-align: center;"
							class="oddcentre" sortable="false" title="NSDL STATUS"><c:out
								value="${vo[11]}"></c:out></td>
						<c:set var="srNoAllown" value="${srNoAllown + 1}"></c:set>
					</tr>
					<%
						i++;
					%>
					<c:set var="count" value="${count+1}"></c:set>
				</c:forEach>

			</table>

		</hdiits:form>
		<form name="formTextFileGen" id="formTextFileGen" method="post"
			action="" encType="multipart/form-data">

			<input type="hidden" name="maxcount" id="maxcount"
				value="${vo_rowNum}" />
			<table width="95%" borber="1">
				<tr>
					<td colspan="2">&nbsp;<!-- Number Of Applicants To Generate Text File ::: -->
					</td>
					<td colspan="1"><input type="hidden" name="noOfEmp"
						id="noOfEmp" value="${vo_rowNum}" /></td>
					<td colspan="2" align="right">Enter NSDL status code ::</td>
					<td colspan="1" align="left"><input type="text"
						name="StatusCode" id="StatusCode" value="" /></td>
				</tr>
				<tr>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td colspan="4"><input id="btnGenerate" class="btn orange"
						type="button" value="Generate" onclick="submitFrom();" /></td>
					<!-- <td><input id="btnValidate" class="btn orange" type="button" value="Validate" onclick="validateFile();" /></td>
<td><input id="btnSend" class="btn orange" type="button" value="Send" onclick="sendFrom();" /></td>-->
					<td colspan="1" align="right"><input id="btnPran"
						class="btn orange" type="button" value="GetPran"
						onclick="getPranFrom();" /></td>
					<td colspan="1" align="right"><input id="btnReset"
						class="btn orange" type="reset" value="Reset"></td>
				</tr>
			</table>
		</form>


	</div>
</fieldset>

<script type="text/javascript">
	function checkApplyCmb(obj, ID, val) {

		var count = document.getElementById("checkCount").value;
		if (obj.checked == true) {
			document.getElementById("checkCount").value = ++count;
			document.getElementById(ID).value = val;
		} else if (obj.checked == false && count > 0) {
			document.getElementById("checkCount").value = --count;
			document.getElementById(ID).value = '';
		}
	}

	function timedRefresh() {
		//setTimeout("location.reload(true);",timeoutPeriod);
		location.reload(true);
	}

	//window.onload = timedRefresh(5000);

	function listSevaarthId() {
		var nOfEmp = parseInt(document.getElementById("noOfEmp").value);
		var maxCount = document.getElementById("maxcount").value;
		var listSevaId = "";
		for (var i = 1; i <= nOfEmp; i++) {
			if (i <= nOfEmp) {
				listSevaId += document.getElementById("sevarthId" + i).value
						+ "_";
			} else {
				listSevaId += document.getElementById("sevarthId" + i).value;
			}

		}
		return listSevaId;
	}

	function formValidate() {
		var nOfEmp = parseInt(document.getElementById("noOfEmp").value);
		var maxCount = document.getElementById("maxcount").value;
		var uri = 'ifms.htm?actionFlag=validateFormS1ForEdit';
		var listSevaId = "";
		if (nOfEmp <= maxCount) {

			return true;
		} else {
			alert("No of employee text file generated upto to 99 but avaiable only max Count is "
					+ maxCount);
			return false;
		}

	}

	function submitFrom() {
		var nOfEmp = parseInt(document.getElementById("noOfEmp").value);
		var maxCount = document.getElementById("maxcount").value;
		var checkCount = document.getElementById("checkCount").value;
		var listSevaId = "";
		if (checkCount == 0) {
			alert("Please select atleast 1 employee");
			return false;
		} else {
			if (checkCount <= maxCount) {

				for (var i = 1; i <= maxCount; i++) {
					if (i <= maxCount
							&& document.getElementById("sevarthId" + i).value != '') {
						if (i <= maxCount) {
							listSevaId += document.getElementById("sevarthId"
									+ i).value
									+ "_";
						}
					}

				}
			} else {
				alert("No fo employee text file generated upto to 99 but avaiable only max Count is "
						+ maxCount);
				return false;
			}
		}
		//alert(listSevaId);
		generateTexFile(listSevaId);

	}

	function getPranFrom() {
		var StatusCode = document.getElementById("StatusCode").value;
		//var StatusCode=nsdlStatus;
		//alert("getPran");
		if (StatusCode != null && StatusCode != '') {
			var uri = './ifms.htm?actionFlag=getPran';
			var url = '&StatusCode=' + StatusCode;
			var myAjax = new Ajax.Request(uri, {
				method : 'post',
				asynchronous : false,
				parameters : url,
				onSuccess : function(myAjax) {
					checkFormS1Entry(myAjax);
				},
				onFailure : function() {
					alert('Something went wrong...');
				}
			});
		} else {
			alert("Please provide the NSDL status code");
			return false;
		}
		timedRefresh();

	}

	function sendFrom(batchNo) {
		//	var empSevarthId=listSevaarthId();
		alert("as");
		if (formValidate()) {
			var uri = './ifms.htm?actionFlag=sendTextFile';
			//var url = '&empSevarthId='+empSevarthId;
			var url = '&batchNo=' + batchNo;
			var myAjax = new Ajax.Request(uri, {
				method : 'post',
				asynchronous : false,
				parameters : url,
				onSuccess : function(myAjax) {
					checkFormS1Entry(myAjax);
				},
				onFailure : function() {
					alert('Something went wrong...');
				}
			});
			timedRefresh();
		} else {
			return false;
		}

	}

	function validateFile(batchNo) {
		//var empSevarthId=listSevaarthId();
		var batchNo = batchNo;
		// alert("formValidate"+empSevarthId);

		if (formValidate()) {
			var uri = './ifms.htm?actionFlag=validateTextFile';
			var url = '&batchNo=' + batchNo;//+'&empSevarthId='+empSevarthId;
			var myAjax = new Ajax.Request(uri, {
				method : 'post',
				asynchronous : false,
				parameters : url,
				onSuccess : function(myAjax) {
					checkFormS1Entry(myAjax);
				},
				onFailure : function() {
					alert('Something went wrong...');
				}
			});
		} else {
			return false;
		}
		timedRefresh();
	}

	function generateTexFile(empSevarthId) {
		var uri = './ifms.htm?actionFlag=generateTexFile';
		var nOfEmp = document.getElementById("noOfEmp").value;
		var url = '&empSevarthId=' + empSevarthId + "&records=" + nOfEmp;
		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				checkFormS1Entry(myAjax);
			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});

	}

	function checkFormS1Entry(myAjax) {

		var status;
		XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		//alert(XmlHiddenValues[0].childNodes.length+" "+checkFlag);
		//alert(XmlHiddenValues[0].childNodes.length == 2);
		if (XmlHiddenValues[0].childNodes.length == 2) {
			var Msg = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
			if (checkFlag == 'correct') {
				status = true;
				alert(Msg);
			} else if (checkFlag == 'wrong') {
				alert(Msg);
				status = false;
			}

		} else {
			if (checkFlag == 'correct') {
				status = true;
				alert('Selected Sevarth Id\'s file generated succuessfully.');
			} else if (checkFlag == 'wrong') {

				alert('Selected Sevarth Id\'s text file generation is problem.');
				status = false;
			}
		}
		timedRefresh();
		return status;
	}
</script>