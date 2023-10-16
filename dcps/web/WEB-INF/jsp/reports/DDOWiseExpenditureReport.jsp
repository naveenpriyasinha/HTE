<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="page" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<% pageContext.setAttribute("currentYear", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)); %>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set> 
<c:set var="reportDataList" value="${resValue.reportDataList}"></c:set>
<c:set var="officeList" value="${resValue.officeList}"></c:set>
<c:set var="searchTxt" value="${resValue.searchTxt}"></c:set>
<c:set var="reportDataList" value="${resValue.reportDataList}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>
<c:set var="currYear" value="${resValue.currYear}"></c:set>
<c:set var="month" value="${resValue.month}"></c:set>
<c:set var="monthName" value="${resValue.monthName}"></c:set>
<c:set var="monthList" value="${resValue.monthList}"></c:set>

<hdiits:form name="formDdowiseListSearch" id="formDdowiseListSearch"
	encType="multipart/form-data" validate="true" method="post">
	<fieldset class="tabstyle">
		<legend>Report DDO Wise Form</legend>
		<table width="100%">

			</tr>
			<tr>
				<td width="10%" align="right">Year :</td>
				<td width="15%"><c:set var="yearCounter" value="2007"></c:set> 
				<select
					name="selectYear" id="selectYear" " class="form-control">
						<option value="0">-- Select --</option>
						<c:forEach begin="${yearCounter}" end="${currentYear}" varStatus="loopYear">
							<option value="${loopYear.index}"
								<c:if test="${year eq loopYear.index}">selected</c:if>>${loopYear.index}</option>
						</c:forEach>
				</select></td>
				<td width="10%" align="right">Month :</td>
				<td width="15%"><c:set var="monthCounter" value="1"></c:set> 
				<hdiits:select name="selectMonth" size="1" sort="false" caption="Month" id="selectMonth" >
				 
						<option value="0">-- Select --</option>
						<c:forEach items="${monthList}" var="monthL">
						<c:choose>
							<c:when test="${monthL.lookupShortName==month}">
								<hdiits:option value="${monthL.lookupShortName}" selected="true" > ${monthL.lookupDesc} </hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${monthL.lookupShortName}"> ${monthL.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>
				</c:forEach>
				</hdiits:select></td>
				<td width="15%">DDO Code :</td>
				<td width="35%"><select name="selectDdoCode" id="selectDdoCode"
					  class="form-control" style="width: 100%;">
						<option value="0">-- Select --</option>
						<c:forEach items="${officeList}" var="DdoCode">
							<option value="${DdoCode[1]}"
								<c:if test="${DdoCode[1]==searchTxt}">selected</c:if>>${DdoCode[1]}-${DdoCode[0]}</option>
						</c:forEach>
				</select></td>
				
			</tr>
			<tr><td width="60%" align="right" colspan="4">&nbsp;</td>
			<td width="15%">&nbsp;</td>
			<td width="35%">
			<input type="text" class="form-control" name="selectDdoCodetxt" id="selectDdoCodetxt" value=""/>
					
			</td>
			</tr>
			<tr>
				<td align="center" colspan="6"><input class="buttontag"
					type="button" value="Reset" onclick="resetForm();"> <input
					class="buttontag" id="btnSubmit" type="button" value="Submit"
					onclick="submitForm();" /></td>
			</tr>
		</table>
	</fieldset>
</hdiits:form>
<fieldset class="tabstyle">
	<legend>DDO Wise Expenditure Report <c:if test="${searchTxt!=''}">for ${searchTxt}</c:if> 
	year <c:if test="${month!='' && month!='0' && month<4}"> ${year-1} - ${year} </c:if>
	 <c:if test="${month!='' && month!='0' && month>=4}">${year} - ${year+1} </c:if> 
	 <c:if test="${month=='0'}">${year} - ${year+1} </c:if> 
	<c:if test="${month!='' && month!='0'}">for ${monthName}</c:if>  
	<c:if test="${month=='0' && currYear==year}"> from 1st April to till date</c:if> 
	<c:if test="${month=='0' && currYear>year}"> from 1st April ${year} to 31st March ${year+1}</c:if> 
	</legend>
	<c:set var="grossAmtSum" value="0"></c:set>
	<c:set var="grossNetSum" value="0"></c:set>
	
	<c:set var="srNoAllown" value="1"></c:set>
	<div class="scrollablediv">
		<hdiits:form name="csrfForm" validate="true" method="POST" action="">
			
			
			<display:table list="${reportDataList}" export="false" varTotals="totals" id="vo" style="width:100%" pagesize="30"  requestURIcontext="false" requestURI="" >
			 
			 {4} 
		


		<display:setProperty name="basic.empty.showtable" value="true" />
        <display:setProperty name="paging.banner.placement" value="top" />
        <display:setProperty name="basic.msg.empty_list_row" value=""></display:setProperty>
        <display:setProperty name="paging.banner.group_size" value="2"></display:setProperty>
        <display:setProperty name="paging.banner.no_items_found" value=""></display:setProperty>
        <display:setProperty name="paging.banner.page.separator" value=" of "></display:setProperty>
        <display:setProperty name="paging.banner.first" value='<span class="pagelinks"> |< << | Page {0}<a href="{3}"> | >> </a><a href="{4}">>|</a></span>'></display:setProperty>
        <display:setProperty name="paging.banner.last" value='<span class="pagelinks"> <a href="{1}">|< </a> <a href="{2}"> << | Page </a> {0} | >> >| </span>'></display:setProperty>
        <display:setProperty name="paging.banner.full" value='<span class="pagelinks"> <a href="{1}">|< </a> <a href="{2}"><<</a> {0}<a href="{3}"> | >> </a><a href="{4}">>| </a></span>'></display:setProperty>
        
        
        
				
				<fmt:parseNumber var="intValue" value="${vo[3]}" integerOnly="true"/>
				 
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="DDO CODE " >
					<c:out value="${vo[0]}"></c:out>
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="DDO Name">
					<c:out value="${vo[1]}"></c:out>
				</display:column>

				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true" 
					title="GROSS AMOUNT" >
					<c:out value="${intValue}"/>
				</display:column>
				<display:column headerClass="datatableheader"
					style="text-align:center;" class="oddcentre" sortable="true"
					title="NET AMOUNT" format="{0,number,0.00}">
					<c:out value="${vo[4]}"/>
					
				</display:column>

	<display:footer>
    <tr>
      <td>Total Bill:</td>
      <td>Total Bill:</td>
      
      <td>${totals.column3}</td>
    </tr>
  </display:footer>
			</display:table>
		</hdiits:form>
 <table>
<tr>
<td>Total Amount </td>
<td>${grossAmtSum} <c:set var="pageNumber" value='<display:setProperty name="paging.banner.some_items_found" value="{4}" />'></c:set>
${pageNumber}</td>
<td>${grossNetSum}</td>
</tr>
</table>
<div align="left">
					<table style="width: 100%;" align="right">
						<tr>
							<td style="font-size: large; color: #FF0000;">Note :->  The Fetched amount as Consolidated bill as Pending and Approved Status.</td>
						</tr>
					</table>

				</div>
	</div>
	<div align="center"><hdiits:button style="width:120px"
	name="ExcelReports" type="button" captionid="EIS.EXPORTOEXCEL"
	bundle="${dcpsLables}" onclick="generateReports()"></hdiits:button></div>
<br />
	<!-- <div><span style="color:#FF0000">Note:</span> The Fetched amount as Consolidated bill as Pending and Approved Status.</div> -->
</fieldset>

<script type="text/javascript">
	function displaySearchForm() {
		if (document.getElementById('seachByDdoCode').checked) {
			document.getElementById("trSearchBtnDdoCode").style.display = "";

		}
		if (document.getElementById('seachByYear').checked) {
			document.getElementById("trSearchBtnYear").style.display = "";

		}

	}
	function submitSearchDdoCode() {
		var flag = "0";
		var searchTxt = "0";
		if (document.getElementById('seachByDdoCode').checked) {
			flag = "txtDdoCode";
			searchTxt = document.getElementById("schtxtDdoCode").value;
			if (searchTxt == '') {
				alert('Please enter DDO COde.');
				return false;
			}

			var url;
			url = "./hrms.htm?actionFlag=ddowisereport&searchTxt=" + searchTxt
					+ "&flag=" + flag;
			document.formDdowiseListSearch.action = url;
			document.formDdowiseListSearch.submit();
			showProgressbar("Getting DDO wise report with search results...");
		}
		return true;
	}
	function submitSearchYear() {
		var flag = "0";
		var searchTxt = "0";
		if (document.getElementById('seachByYear').checked) {
			flag = "txtYear";
			searchTxt = document.getElementById("txtYear").value;
			if (searchTxt == '') {
				alert('Please select year.');
				return false;
			}
			//alert(searchTxt); 
			//return false;
			var url;
			url = "./hrms.htm?actionFlag=ddowisereport&searchTxt=" + searchTxt
					+ "&flag=" + flag;
			document.formDdowiseListSearch.action = url;
			document.formDdowiseListSearch.submit();
			showProgressbar("Getting DDO wise report with search results...");
		}
		return true;
	}

	function submitForm() {

		if (document.getElementById('selectYear').value == '0') {
			alert("Please select Year.");
			return false;
		}

		flag = "report";
		seletSearchTxt = document.getElementById("selectDdoCode").value;
		var year = document.getElementById("selectYear").value;
		var month = document.getElementById("selectMonth").value;
		var searchTxt=document.getElementById("selectDdoCodetxt").value;
		if(seletSearchTxt==0 || seletSearchTxt.trim()==''){
			seletSearchTxt=searchTxt;
		} else if (seletSearchTxt==0 || searchTxt.trim()!=''){
			seletSearchTxt=searchTxt;
			}
		var url;
		url = "./hrms.htm?actionFlag=ddowisereport&year=" + year + "&month="
				+ month + "&searchTxt=" + seletSearchTxt + "&flag=" + flag;
		//alert(url);
		document.formDdowiseListSearch.action = url;
		document.formDdowiseListSearch.submit();
		showProgressbar("Getting DDO wise report with search results...");

		return true;
	}
 
function generateReports()
{
	flag = "report";
	seletSearchTxt = document.getElementById("selectDdoCode").value;
	var year = document.getElementById("selectYear").value;
	var month = document.getElementById("selectMonth").value;
	var searchTxt=document.getElementById("selectDdoCodetxt").value;
	if(seletSearchTxt==0 || seletSearchTxt.trim()==''){
		seletSearchTxt=searchTxt;
	} else if (seletSearchTxt==0 || searchTxt.trim()!=''){
		seletSearchTxt=searchTxt;
		}
	var flags = flag.toString();
	//var url = "./hrms.htm?actionFlag=generateDDOExcelforSchoolValidation&flag="+flags;
	var url = "ifms.htm?actionFlag=generateDdoWiseExcel&year=" + year + "&month="+ month + "&searchTxt=" + seletSearchTxt + "&flag=" + flag;
	document.formDdowiseListSearch.action=url;
	document.formDdowiseListSearch.submit();
	
	
} 

</script>