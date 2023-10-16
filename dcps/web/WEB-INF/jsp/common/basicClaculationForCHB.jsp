<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels"
	scope="request" />
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts"
	scope="request" />

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js">
	
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"
	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/dcps/srkaMasters.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/dcpsDDO.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.SELECTEMPLIST}"></c:set>
<c:set var="lLstMonthSelect" value="${resValue.lLstMonthSelect}"></c:set>
<c:set var="lLstYearSelect" value="${resValue.lLstYearSelect}"></c:set>
<c:set var="empId" value="${resValue.empId}"></c:set>
<c:set var="basicValue" value="${resValue.basicValue}"></c:set>
<c:set var="DDOCODE" value="${resValue.DDOCODE}"></c:set>

<hdiits:form name="frmCHBCalculationForBasic" id="DDOEmpSelect"
	encType="multipart/form-data" validate="true" method="post">
	<input type="hidden" name="ddoCode" id="ddoCode" value="${DDOCODE}">
	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message
		key="CMN.CHBCALCULATIONFORBASIC" bundle="${dcpsLabels}"></fmt:message></b>
	</legend>
	<table>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.SEVARTHID"
				bundle="${dcpsLabels}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtSevaarthId" style="text-transform: uppercase" size="30"
				name="txtSevaarthId" /></td>
		</tr>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
				bundle="${dcpsLabels}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtEmployeeName" size="30" style="text-transform: uppercase"
				name="txtEmployeeName" /> <span id="roleIndicatorRegion"
				style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><label style="color: red"><fmt:message
				key="MSG.SEARCH" bundle="${dcpsLabels}" /></label></td>
		</tr>
	</table>
	<div style="width: 50; text-align: center; align: center"><hdiits:button
		name="btnSearch" style="align:center" type="button"
		captionid="CMN.SEARCH" bundle="${dcpsLabels}"
		onclick="showEmpSelectionLists();" /> <hdiits:button
		name="btnDisplayAll" style="align:center;display:none" type="button"
		captionid="BTN.DISPLAYALL" bundle="${dcpsLabels}"
		onclick="showAllForSelections();" /></div>
	</fieldset>
	<br></br>

	<c:if test="${VOList != null}">

		<fieldset style="width: 100%" class="tabstyle"><legend
			id="headingMsg"> <b><fmt:message key="CMN.DDOEMPSELECT"
			bundle="${dcpsLabels}"></fmt:message></b> </legend> <c:set
		var="hdnCounter" value="0" />

		<div class="scrollablediv"
			style="width: 100%; overflow: auto; height: 200px;"><display:table
			list="${VOList}" id="vo" cellpadding="5" style="width:100%"
			requestURI="">

			<display:column 
				headerClass="datatableheader" style="text-align:center;"
				class="oddcentre" sortable="true">
				<input type="checkbox" align="left" name="checkbox"
					id="checkbox${vo_rowNum}" value="${vo[0]}" onclick='checkUncheckAlls(this)'/>
			</display:column>

			<display:column titleKey="CMN.SEVARTHID"
				headerClass="datatableheader" sortable="true"
				style="width:20%;text-align: left;">
							${vo[0]}
							<input type="hidden" id="hdnSevarthID${vo_rowNum}"
				name="hdnSevarthID${vo_rowNum}" value="${vo[0]}">
					<c:set var="hdnCounter" value="${hdnCounter+1}" />
			</display:column>

			<display:column headerClass="datatableheader"
				style="text-align:left;width: 40%" class="oddcentre" sortable="true"
				titleKey="CMN.EMPNAME">
				<c:out value="${vo[1]}"></c:out>
				<input type="hidden" id="hdnEmpName${vo_rowNum}" name="hdnEmpName${vo_rowNum}" value="${vo[1]}" />
			</display:column>
			
			<display:column headerClass="datatableheader"
				style="text-align:left;width: 20%" class="oddcentre" sortable="true"
				titleKey="CMN.YEAR">
				<select disabled="disabled" style="width: 120px;" id="yearList" name="yearList">
				<c:forEach var="lLstYearSelect" items="${resValue.lLstYearSelect}">
					<option value="${lLstYearSelect[0]}">${lLstYearSelect[1]}</option>
				</c:forEach>
			</select>
			</display:column>
			
			<display:column headerClass="datatableheader"
				style="text-align:left;width: 40%" class="oddcentre" sortable="true"
				titleKey="CMN.MONTH">
				<select disabled="disabled" style="width: 120px;" id="monthList" name="monthList">
				<c:forEach var="lLstMonthSelect" items="${resValue.lLstMonthSelect}">
					<option value="${lLstMonthSelect[0]}">${lLstMonthSelect[1]}</option>
				</c:forEach>
			</select>
			</display:column>
			
			<display:column style="height:35;text-align: left;width:20%"
				class="tablecelltext" titleKey="CMN.HOURS"
				headerClass="datatableheader" sortable="true">
				<input type="text" name="txtHours" id="txtHours${vo_rowNum}"
					value="" readOnly=readOnly style="width: 250px;" onchange="ClaculateValue();"/>
				
			</display:column>		

			

			<display:column style="height:35;text-align: left;width:20%"
				class="tablecelltext" titleKey="CMN.BASICVALUE"
				headerClass="datatableheader" sortable="true">
				<input type="text" name="txtValue" id="txtValue${vo_rowNum}"
					value="${basicValue}" readOnly=readOnly style="width: 250px;"/>
				
			</display:column>

			<display:setProperty name="paging.banner.placement" value="bottom" />

		</display:table></div>

		</fieldset>
		<input type="hidden" id="hdnCounter" name="hdnCounter"
		value="${hdnCounter}" />
		<input type="hidden" id="hdnRatePerHour${vo_rowNum}" name="hdnRatePerHour" value="${vo[2]}" />
		<input type="hidden" id="empId" name="empId" value="${empId}" />

		<br>

		<div style="width: 50; text-align: center; align: center"><hdiits:button
			name="btnSelect" type="button" classcss="bigbutton"
			id="btnDDOEmpSelect" captionid="BTN.UPDATE"
			bundle="${dcpsLabels}" onclick="updateCHBCalculationValue();" /></div>

	</c:if>

	<input type="hidden" name="hidSearchFromDDOSelection"
		id="hidSearchFromDDOSelection" value="searchFromDDOSelection" />

</hdiits:form>

<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName" baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteCHB"
 parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromDDOSelection}" className="autocomplete" minimumCharacters="3" 
 indicator="roleIndicatorRegion" />


<script>
	function showEmpSelectionLists() {
		//alert("hiiii");
		var txtSevaarthId = document.getElementById("txtSevaarthId").value;
		var txtEmployeeName = document.getElementById("txtEmployeeName").value;
		if (txtSevaarthId == "" && txtEmployeeName == "") {
			alert('Please enter search criteria');
			return;
		}
		var url = "ifms.htm?actionFlag=basicClaculationForCHB&elementId=90002629&txtSevaarthId="+ txtSevaarthId + "&txtEmployeeName="+ txtEmployeeName + "&requestForSearch=Yes";
		showProgressbar('Please Wait<br>Your request is in progress...');
		document.frmCHBCalculationForBasic.action = url;
		enableAjaxSubmit(true);
		document.frmCHBCalculationForBasic.submit();
	}

	function showAllForSelections() {
		var url = "ifms.htm?actionFlag=loadDdoEmpSelection&elementId=90002629";
		self.location.href = url;
	}

	function ClaculateValue(){
		//alert("HIIIIII"); 
		var totalEmp =document.getElementById("hdnCounter").value;
		//alert("totalEmp: "+totalEmp);
		var txtHours="";
		var ratePerhr="";
		var rstBasicValue="";
		for(k=1;k<=totalEmp;k++)
		{
			if(document.getElementById("checkbox"+k).checked)
			{

				//alert("hello");	
				ratePerhr = ratePerhr + document.getElementById("hdnRatePerHour"+k).value;
				//alert("ratePerhr: "+ratePerhr);
				txtHours = txtHours + document.getElementById("txtHours"+k).value;
				//alert("txtHours: "+txtHours);
				rstBasicValue = ratePerhr * txtHours;
				//alert("rstBasicValue: "+rstBasicValue);
				document.getElementById("txtValue"+k).value = rstBasicValue	;
			}
		}
		
	}


	function checkUncheckAlls(theElement) 
	{
		//alert("hiiiiii");
		var theForm = theElement.form;
		var totalEmp = document.getElementById("hdnCounter").value ;
		//alert("totalEmp: "+totalEmp);
		for(var z = 0; z < theForm.length; z++)
		{
			if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect')
			{
				theForm[z].checked = theElement.checked;
				
			}
		}

		var k;
		for(k=1;k<=totalEmp;k++)
		{
			var txtHours = document.getElementById('txtHours'+k);
			var monthList = document.getElementById('monthList');
			//alert("monthList"+monthList);
			var yearList = document.getElementById('yearList');
			//var txtValue = document.getElementById('txtValue'+k);
			if(document.getElementById("checkbox"+k).checked)
			{
				txtHours.readOnly = false;
				monthList.disabled=false;
				yearList.disabled=false;
				
			}else{
				
				txtHours.readOnly = true;
				monthList.disabled=true;
				yearList.disabled=true;
				
			}
		}
	}

	function claculateBasicValue(){
		//alert("HIii");
		var sevarthID="";
		var txtHours="";
		var totalEmp = document.getElementById("hdnCounter").value ;
		//alert("totalEmp: "+totalEmp);
		var selectedEmp = Number(0);
		var totalSelectedEmp = Number(0);
		var k;
		for(k=1;k<=totalEmp;k++)
		{
			if(document.getElementById("checkbox"+k).checked)
			{
				selectedEmp = k ;	
				totalSelectedEmp++ ;
			}
		}
		//alert("totalSelectedEmp: "+totalSelectedEmp);
		
		if(selectedEmp == 0)
			{
				alert('Please select at least one Employee');
				return false; 
			}

		for(k=1;k<=totalEmp;k++)
		{
			if(document.getElementById("checkbox"+k).checked)
			{
				//if(document.getElementById("dictrictName"+k).value=='-1'){
					//var ddoCode=document.getElementById("hdnSevarthID"+k).value;
					//alert("Please Select the Employee of SevarthID : "+ddoCode);
					//return false;
				//}
				sevarthID = sevarthID + document.getElementById("hdnSevarthID"+k).value.trim() + "~" ;
				
				
				txtHours = txtHours + document.getElementById("txtHours"+k).value.trim() + "~";
				//alert("txtHours: "+txtHours);
				
			}
		}	
		//alert("sevarthID :"+sevarthID+"txtHours :"+txtHours);
		var url;
		url="./hrms.htm?actionFlag=claculateBasicValueForCHB&sevarthID="+sevarthID+"&txtHours="+txtHours;
		document.frmCHBCalculationForBasic.action= url;
		document.frmCHBCalculationForBasic.submit();
		
	}

	function updateCHBCalculationValue(){
		//alert("HIii");
		var sevarthID="";
		var txtValue="";
		var month="";
		var year="";
		var empId="";
		var empName="";
		var hours="";
		var totalEmp = document.getElementById("hdnCounter").value ;
		//alert("totalEmp: "+totalEmp);
		var selectedEmp = Number(0);
		var totalSelectedEmp = Number(0);
		var k;
		for(k=1;k<=totalEmp;k++)
		{
			if(document.getElementById("checkbox"+k).checked)
			{
				selectedEmp = k ;	
				totalSelectedEmp++ ;
			}
		}
		//alert("totalSelectedEmp: "+totalSelectedEmp);
		
		if(selectedEmp == 0)
			{
				alert('Please select at least one Employee');
				return false; 
			}

		for(k=1;k<=totalEmp;k++)
		{
			if(document.getElementById("checkbox"+k).checked)
			{
				sevarthID = sevarthID + document.getElementById("hdnSevarthID"+k).value.trim() + "~" ;
				empName = empName+ document.getElementById("hdnEmpName"+k).value.trim() + "~" ;
				txtValue = txtValue + document.getElementById("txtValue"+k).value.trim() + "~";
				empId = empId + document.getElementById("empId").value.trim() + "~";
				month = month + document.getElementById("monthList").value.trim() + "~";
				year = year + document.getElementById("yearList").value.trim() + "~";
				hours = hours + document.getElementById("txtHours"+k).value.trim() + "~";
			}
		}	
		//alert("sevarthID :"+sevarthID+"empName :"+empName+"month :"+month+"year :"+year+ "empId :"+empId+ "hours :"+hours+"txtValue :"+txtValue);
		var url;
		url="./hrms.htm?actionFlag=updateCHBCalculationValue&sevarthID="+sevarthID+"&empName="+empName+"&empId="+empId+"&month="+month+"&year="+year+"&hours="+hours+"&txtValue="+txtValue;
		document.frmCHBCalculationForBasic.action= url;
		document.frmCHBCalculationForBasic.submit();
	}

	
</script>