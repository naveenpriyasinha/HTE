<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<style>
p.mynote {
	text-align: center;
	color: red;
	font-weight: 600;
	margin-bottom: 5px;
}
</style>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DDOCode" value="${resValue.DDOCode}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>

<hdiits:form name="formNPSFileListSearch" id="formNPSFileListSearch"
	encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle">
		<legend>Search Employee</legend>
		<table width="80%">
			<tr>
				<td width="30%" align="left"><input type="radio"
					name="seachCondition" value="seachBySevarthId"
					id="seachBySevarthId" onclick="displaySearchForm();">Search
					By HTESevarth ID</td>
			</tr>
			<tr id="trSearchBtnSevarthId" style="display: none;">
				<td width="50%" align="right"><input type="text"
					id="txtSevarthId" size="50" maxlength="20" /></td>
				<td width="50%" align="left"><input class="buttontag"
					type="button" value="Search" onclick="submitSearchEmp();" /></td>
			</tr>
		</table>
	</fieldset>
	</hdiits:form>
<fieldset class="tabstyle">
	<legend>Employee List</legend>
  <p class="mynote">Note : "CSRF form Need to check before Validate and Forward to NSDL & Also Check the DDO reg Number and DTO Number."</p>
	<c:set var="srNoAllown" value="1"></c:set>
	<div class="scrollablediv">
		<hdiits:form name="csrfForm" validate="true" method="POST" action="">
			<input type="hidden" id="checkCount" name="checkCount" value="0" />
			<display:table list="${empList}" id="vo" style="width:100%"
				 requestURIcontext="false" requestURI="">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="false"
					title="">
					<c:if test="${!empty fn:trim(vo[7]) && !empty fn:trim(vo[8])}">
						<hdiits:checkbox readonly="true"
							id="selcheckBoxAllow${srNoAllown}"
							name="selcheckBoxAllow${srNoAllown}" value="1"
							onclick="checkApplyCmb(this,'sevarthId${vo_rowNum}','${vo[0]}');" />
					</c:if>
					<c:if test="${empty fn:trim(vo[7]) && empty fn:trim(vo[8])}">
						<hdiits:checkbox id="selcheckBoxAllow${srNoAllown}"
							name="selcheckBoxAllow${srNoAllown}" value="1"
							onclick="checkApplyCmb(this,'sevarthId${vo_rowNum}','${vo[0]}');" />
					</c:if>
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Sevarth ID">
					<a href="#" onclick="chkAndOpenFormS1Edit('${vo_rowNum}');"><c:out
							value="${vo[0]}"></c:out></a>
					<input type="hidden" id="sevarthId${vo_rowNum}" value="" />
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Employee Name">
					<c:out value="${vo[1]}"></c:out>
					<input type="hidden" id="empName${vo_rowNum}" value="${vo[1]}" />
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Date of Joining">
					<c:out value="${vo[2]}"></c:out>
					<input type="hidden" id="DOJ${vo_rowNum}" value="${vo[2]}" />
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Designation Name">
					<c:out value="${vo[3]}"></c:out>
					<input type="hidden" id="dsgnName${vo_rowNum}" value="${vo[3]}" />
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="DDO Code Level1">
					<c:out value="${vo[4]}"></c:out>
					<input type="hidden" id="DDOCode${vo_rowNum}" value="${vo[4]}" />
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="DTO Reg No">
					<c:out value="${vo[14]}"></c:out>
					<input type="hidden" id="DTO_reg_no${vo_rowNum}" value="${vo[14]}" />
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="DDO Reg No">
					<c:out value="${vo[15]}"></c:out>
					<input type="hidden" id="DDO_reg_no${vo_rowNum}" value="${vo[15]}" />
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Office Name">
					<c:out value="${vo[5]}"></c:out>
					<input type="hidden" id="officeCode${vo_rowNum}" value="${vo[5]}" />
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="DCPS ID">
					<c:out value="${vo[6]}"></c:out>
					<input type="hidden" id="DcpsId${vo_rowNum}" value="${vo[6]}" />

				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Acknowlegment No.">
					<c:if test="${!empty fn:trim(vo[7])}"> <c:out value="${vo[7]}"></c:out>
					</c:if>
					<c:if test="${empty fn:trim(vo[7])}"> --		</c:if>
					
					<input type="hidden" id="AckNo${vo_rowNum}" value="${vo[7]}" />

				</display:column>
				
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="File Creation">
					<c:if test="${!empty fn:trim(vo[7]) && !empty fn:trim(vo[8])}"> Text file Created</c:if>
					<c:if test="${empty fn:trim(vo[7]) && empty fn:trim(vo[8])}">Text file not Created</c:if>
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Verification status">
					<c:if
						test="${empty fn:trim(vo[9]) && !empty fn:trim(vo[7]) && !empty fn:trim(vo[8])}">
						<a href="#" onclick="validateFile('${vo[8]}');">Validate</a>
					</c:if>
					<c:if test="${!empty fn:trim(vo[9])}">verified</c:if>
					<c:if test="${empty fn:trim(vo[7]) && empty fn:trim(vo[8])}"> Not verified </c:if>

				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="File upload status">

					<c:if test="${empty fn:trim(vo[10]) && !empty fn:trim(vo[9]) }">
						<a href="#" onclick="sendFrom('${vo[8]}');">Upload File</a>
					</c:if>
					<c:if test="${empty fn:trim(vo[10]) && empty fn:trim(vo[9])}">File not uploaded </c:if>
					<c:if test="${!empty fn:trim(vo[10])}">${vo[10]} </c:if>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="false"
					title="NSDL STATUS">
					<c:out value="${vo[11]}"></c:out>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="false"
					title="PRAN NO">
					<c:out value="${vo[12]}"></c:out>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="false"
					title="Donwload">
					<a href="javascript:void(0);" onclick="donwloadFile('${vo[8]}')">Download</a>
				</display:column>
			 	<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="false"
					title="Action">
					<c:if test="${(empty fn:trim(vo[13]) || fn:trim(vo[13])=='N') && 
					(!empty fn:trim(vo[7]) || !empty fn:trim(vo[8]))}">
					<a href="javascript:void(0);" onclick="deleteRecord('${vo[0]}')">Delete</a></c:if>
					 
					<a href="javascript:void(0);" onclick="deleteRecord('${vo[0]}')">Delete</a> 
				</display:column>
 
				<c:set var="srNoAllown" value="${srNoAllown + 1}"></c:set>


			</display:table>
		</hdiits:form>
		<form name="formTextFileGen" id="formTextFileGen" method="post"
			action="" encType="multipart/form-data">

			<input type="hidden" name="maxcount" id="maxcount" value="${vo_rowNum}" />
			<table width="95%" borber="1">
				<tr>
					<td colspan="3">&nbsp;<!-- Number Of Applicants To Generate Text File ::: -->
					</td>
					<td colspan="1"><input type="hidden" name="noOfEmp"
						id="noOfEmp" value="${vo_rowNum}" /></td>
					<td colspan="2" align="right">Enter NSDL status code ::</td>
					<td colspan="1" align="left"><input type="text"
						name="StatusCode" id="StatusCode" value="" /></td>
				</tr>
				<tr>
					<td colspan="7"></td>
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
<hdiits:form name="frmEmpFilegenerate" action="" id="frmEmpFilegenerate" encType="multipart/form-data" validate="true" 
method="post">
<hdiits:button id="btnExporttoExcel" name="btnExporttoExcel" value="Export to Excel" classcss="bigbutton" type="button" onclick="generateExcel()"/>
<hdiits:button id="btnprintReport" name="btnprintReport" value="Print Report" classcss="bigbutton" type="button" onclick="printReport()"/>
<hdiits:button id="btnsaveReport" name="btnsaveReport" value="Save Report" classcss="bigbutton" type="button" onclick="saveReport()"/>
</hdiits:form>
<hdiits:form name="npsFileDownload" id="npsFileDownload"
	encType="multipart/form-data" validate="true" method="post">
 <input type="hidden" name="npsDownloadBatchId" id="npsDownloadBatchId" value=""/>
	</hdiits:form>
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
		var counter=0;
		if (checkCount == 0) {
			alert("Please select atleast 1 employee");
			return false;
		} else {
				if (counter <= maxCount) {
				for (var i = 1; i <= nOfEmp; i++) {
				
					if ((i <= nOfEmp && document.getElementById("sevarthId" + i).value != '') ) {
						if (counter <= maxCount) {
							listSevaId += document.getElementById("sevarthId"+ i).value + "_";
							counter++;
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
			showProgressbar("Getting  Pran From for NSDL Status Code "+StatusCode);
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
	
		if (formValidate()) {
			showProgressbar("Uploading  files to NSDL for pran number registration");
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


	function displaySearchForm()
	{
		if(document.getElementById('seachBySevarthId').checked)
		{
			document.getElementById("trSearchBtnSevarthId").style.display="";
			 
		}
		 
	}
	function submitSearchEmp()
	{
		var flag="0";
		var searchTxt="0";
		if(document.getElementById('seachBySevarthId').checked)
		{
			flag = "sevarthId";
			searchTxt = document.getElementById("txtSevarthId").value;
			if(searchTxt == '')
			{
				alert('Please enter sevarth id.');
				return false;
			}
			var url;
			url="./hrms.htm?actionFlag=FileGenerate&searchTxt="+searchTxt+"&flag="+flag;
			document.formNPSFileListSearch.action= url;
			document.formNPSFileListSearch.submit();
			showProgressbar("Getting Form NPS File generation list with search results...");
		}
		return true;
	}

	function donwloadFile(BatchId){
		var flag="0";
	 	document.getElementById("npsDownloadBatchId").value=BatchId;
		if(BatchId!='')
		{
			flag = "BatchId";
			 
			var url;
			url="./hrms.htm?actionFlag=downloadFile&npsDownloadBatchId="+BatchId;
			
			document.npsFileDownload.action= url;
			document.npsFileDownload.submit();
			//showProgressbar("NPS file donwloading ...");
		}else {
		alert("Text file not created yet.");
			}
	}
	function deleteRecord(cnt){
	 
		 
		if(cnt!='')
		{	
			showProgressbar("Data is deleting for Sevaarth ID "+cnt) 
			//url="./hrms.htm?actionFlag=deleteRecord&empId"+cnt;
			var uri = './ifms.htm?actionFlag=deleteRecord';
			var url = '&empId='+cnt;
			var myAjax = new Ajax.Request(uri, {
				method : 'post',
				asynchronous : false,
				parameters : url,
				onSuccess : function(myAjax) {
					checkFormS1Entry(myAjax);
				},
				onFailure : function() {
					alert('Action not found...');
				}
			});
		 
		}else {
				alert("Employee record not created.");
			}
	}


	function generateExcel()
	{
		//alert("generateExcel Calling");
		var url = "ifms.htm?actionFlag=generateNpsFileStatusExcel&FileFlag=Y"; 
 		document.frmEmpFilegenerate.action= url;
		document.frmEmpFilegenerate.submit();
	}

	function saveReport() 
	{
		document.execCommand("SaveAs");
	}
	function printReport() 
	{

		window.print();
		document.getElementById('Print').style.visibility = 'visible'; // show 
		 

		
	}
	/*var uri = './ifms.htm?actionFlag=deleteRecord';
		  
		var url = '&empId"+cnt;
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
		});*/
</script>