
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
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DDOCode" value="${resValue.DDOCode}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="empDesigList" value="${resValue.empDesigList}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<script type="text/javascript">
<c:if test="${!empty(fn:trim(msg))}" >
		alert('${msg}');
</c:if>

function chkAndOpenFormS1Edit(row)
{
	var empSevarthId=document.getElementById("sevarthId"+row).value;
	var uri = 'ifms.htm?actionFlag=validateFormS1ForEdit';
	var url = 'empSevarthId='+empSevarthId;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					checkFormS1Entry(myAjax,row);
					
				},
		        onFailure: function()
		        			{ 
  						alert('Something went wrong...');
  					} 
		          } 
);
}
function checkFormS1Entry(myAjax,row){
	//alert("hiii checdksdsd");
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	//alert("checkFlag"+checkFlag);
	if(checkFlag=='correct')
	{
		status= true;
		//alert('all ok');
		openFormS1Edit(row);
	}
	else if(checkFlag=='wrong')
	{
		
		
		alert('Selected Sevarth Id\'s Form S1 has been already edited.');
		status= false;
	}
	return status;
}



function openFormS1Edit(row)
{
	//alert('openFormS1Edit '+row);
	var empSevarthId=document.getElementById("sevarthId"+row).value;
	var empName=document.getElementById("empName"+row).value;
	var DOJ=document.getElementById("DOJ"+row).value;
	var dsgnName=document.getElementById("dsgnName"+row).value;
	var DDOCode=document.getElementById("DDOCode"+row).value;
	var DcpsId=document.getElementById("DcpsId"+row).value;
	var DcpsEmpId=document.getElementById("DcpsEmpId"+row).value;
	//alert('empSevarthId: '+empSevarthId);
	var url;
	url="./ifms.htm?actionFlag=getFormS1EditForEmp&empSevarthId="+empSevarthId+"&empName="+empName+"&DOJ="+DOJ+"&dsgnName="+dsgnName+"&DDOCode="+DDOCode+"&DcpsId="+DcpsId+"&DcpsEmpId="+DcpsEmpId;
	document.formS1EditListForm.action= url;
	document.formS1EditListForm.submit();
	showProgressbar("Getting Form S1 edit screen...");
}
function displaySearchForm()
{
	if(document.getElementById('seachBySevarthId').checked)
	{
		document.getElementById("trSearchBtnSevarthId").style.display="";
		document.getElementById("trSearchBtnDsgn").style.display="none";
		document.getElementById("trSearchBtnDcpsId").style.display="none";
	}
	if(document.getElementById('seachByDcpsId').checked)
	{
		document.getElementById("trSearchBtnDcpsId").style.display="";
		document.getElementById("trSearchBtnDsgn").style.display="none";
		document.getElementById("trSearchBtnSevarthId").style.display="none";
	}
	if(document.getElementById('seachByDsgn').checked)
	{
		document.getElementById("trSearchBtnSevarthId").style.display="none";
		document.getElementById("trSearchBtnDcpsId").style.display="none";
		document.getElementById("trSearchBtnDsgn").style.display="";
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
		url="./ifms.htm?actionFlag=updateCSRFForm&searchTxt="+searchTxt+"&flag="+flag;
		document.formS1EditListForm.action= url;
		document.formS1EditListForm.submit();
		showProgressbar("Getting Form S1 edit screen with search results...");
	}
	if(document.getElementById('seachByDcpsId').checked)
	{
		flag = "dcpsId";
		searchTxt = document.getElementById("txtDcpsId").value;
		
		if(searchTxt == '')
		{
			alert('Please enter Dcps id.');
			return false;
		}
		url="./ifms.htm?actionFlag=updateCSRFForm&searchTxt="+searchTxt+"&flag="+flag;
		document.formS1EditListForm.action= url;
		document.formS1EditListForm.submit();
		showProgressbar("Getting Form S1 edit screen with search results...");
	}
	if(document.getElementById('seachByDsgn').checked)
	{
		flag = "dsgn";
		var index = document.getElementById("cmbDsgnList").selectedIndex;
		var value = document.getElementById("cmbDsgnList").options[index].value;
		if(value=='-1')
		{
			alert('Please select Designation.');
			return false;
		}
		url="./ifms.htm?actionFlag=updateCSRFForm&searchTxt="+value+"&flag="+flag;
		document.formS1EditListForm.action= url;
		document.formS1EditListForm.submit();
		showProgressbar("Getting Form S1 edit screen with search results...");
	}
	return true;
}


</script>
<hdiits:form name="formS1EditListForm" id="formS1EditListForm"
	encType="multipart/form-data" validate="true" method="post">
	<div style="text-align: center; color: green; font-weight: 800;">${msg}</div>

	<fieldset class="tabstyle">
		<legend>Search Employee</legend>
		<table width="80%">
			<tr>
				<td width="30%" align="left"><input type="radio"
					name="seachCondition" value="seachBySevarthId"
					id="seachBySevarthId" onclick="displaySearchForm();">Search
					By HTESevarth ID</td>
				<td width="30%" align="left"><input type="radio"
					name="seachCondition" value="seachByDcpsId" id="seachByDcpsId"
					onclick="displaySearchForm();">Search By DCPS ID</td>
				<td width="30%" align="left"><input type="radio"
					name="seachCondition" value="seachByDsgn" id="seachByDsgn"
					onclick="displaySearchForm();">Search By Designation</td>
			</tr>
			<tr id="trSearchBtnSevarthId" style="display: none;">
				<td width="50%" align="right"><input type="text"
					id="txtSevarthId" size="50" maxlength="20" /></td>
				<td width="50%" align="left"><input class="buttontag"
					type="button" value="Search" onclick="submitSearchEmp();" /></td>
			</tr>
			<tr id="trSearchBtnDcpsId" style="display: none;">
				<td width="50%" align="right"><input type="text" id="txtDcpsId"
					size="50" maxlength="23" /></td>
				<td width="50%" align="left"><input class="buttontag"
					type="button" value="Search" onclick="submitSearchEmp();" /></td>
			</tr>
			<tr id="trSearchBtnDsgn" style="display: none;">
				<td width="50%" align="right"><select id="cmbDsgnList">
						<option value="-1">--Select--</option>
						<c:forEach items="${empDesigList}" var="empDesigList">
							<option value="${empDesigList[1]}">${empDesigList[0]}</option>
						</c:forEach>
				</select></td>
				<td width="50%" align="left"><input class="buttontag"
					type="button" value="Search" onclick="submitSearchEmp();" /></td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="tabstyle">
		<legend>Employee List</legend>
		<div class="scrollablediv">
			<display:table list="${empList}" id="vo" style="width:100%"
				pagesize="50" requestURIcontext="false" requestURI="">
				<display:setProperty name="paging.banner.placement" value="top" />

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Sevarth ID">
					<a href="#" onclick="chkAndOpenFormS1Edit('${vo_rowNum}');"><c:out
							value="${vo[0]}"></c:out></a>
					<input type="hidden" id="sevarthId${vo_rowNum}" value="${vo[0]}" />
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
					title="DDO Code">
					<c:out value="${vo[4]}"></c:out>
					<input type="hidden" id="DDOCode${vo_rowNum}" value="${vo[4]}" />
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="Office Name">
					<c:out value="${vo[5]}"></c:out>
					<input type="hidden" id="DDOCode${vo_rowNum}" value="${vo[5]}" />
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="DCPS ID">
					<c:out value="${vo[6]}"></c:out>
					<input type="hidden" id="DcpsId${vo_rowNum}" value="${vo[6]}" />
					<input type="hidden" id="DcpsEmpId${vo_rowNum}" value="${vo[7]}" />
				</display:column>

			</display:table>
		</div>
	</fieldset>
</hdiits:form>