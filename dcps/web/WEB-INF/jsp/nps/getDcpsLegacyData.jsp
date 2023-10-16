 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsChanges.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/login/md5.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels"
	scope="request" />
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts"
	scope="request" />
	
	<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.DESELECTEMPLIST}"></c:set>
<c:set var="ViewList" value="${resValue.VIEWSELECTEMPLIST}"></c:set>
<c:set var="postExpFlag" value="${resValue.postExpFlag}"></c:set>
<c:set var="postEndDate" value="${resValue.postEndDate}"></c:set>
<c:set var="ddoCode" value="${resValue.ddoCode}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="Empsize" value="${resValue.Empsize}"></c:set>
<c:set var="actionEmp" value="${resValue.actionEmp}"></c:set>
<c:set var="lLstLegValidatePeriod" value="${resValue.lLstLegValidatePeriod}"></c:set>
<c:set var="successFlag" value="${resValue.successFlag}"></c:set>

<style>
div#displayData {
	overflow-x: scroll;
}
</style>
<hdiits:form name="DCPSForwardedFormsList" id="DCPSForwardedFormsList" encType="multipart/form-data"
	validate="true" method="post">
	
	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message
		key="CMN.SEARCHEMP" bundle="${dcpsLabels}"></fmt:message></b> </legend>
		
	<table>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.SEVARTHID"
				bundle="${dcpsLabels}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtSevaarthId" style="text-transform: uppercase" size="30"
				name="txtSevaarthId" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><label style="color: red"><fmt:message
				key="MSG.OR" bundle="${dcpsLabels}" /></label></td>
		</tr>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
				bundle="${dcpsLabels}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtEmployeeName" size="30" name="txtEmployeeName" onkeypress="alphaFormatWithSpace(this);" onblur="isName(this,'This field should not contain any special characters or Number');"/> <span
				id="roleIndicatorRegion" style="display: none"> <img
				src="./images/busy-indicator.gif" /></span></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><label style="color: red"><fmt:message
				key="MSG.OR" bundle="${dcpsLabels}" /></label></td>
		</tr>
			<tr align="center">
			<td width="25%" align="left">DCPS ID</td>
			<td width="50%" align="left"><input type="text"
				id="txtDcpsId" size="30" name="txtDcpsId" /> <span
				id="roleIndicatorRegion" style="display: none"> <img
				src="./images/busy-indicator.gif" /></span></td>
		</tr>
		

	</table>
	<div style="width: 50; text-align: center; align: center"><hdiits:button
		name="btnSearch" style="align:center" type="button"
		captionid="CMN.SEARCH" bundle="${dcpsLabels}"
		onclick="getEmp();" /></div>
	</fieldset>
	
	
	<br><div id="displayData">
<c:set var="pranUpdateStatus" value=""></c:set>

<c:if test="${VOList != null}">
	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message key="CMN.SAVE"
		bundle="${dcpsLabels}"></fmt:message></b> </legend> <input type="hidden"
		name="hdnCounter" id="hdnCounter" value="0" /> <input type="hidden"
		name="currDate1" id="currDate1" value="${resValue.gDtCurDate}" />
		
		<div class="scrollablediv" style="width: 100%; overflow: auto; height: 200px;">

	
			 <c:if
		test="${VOList != null}">
		
			<display:table list="${VOList}" id="vo" cellpadding="5" style="width: 100%;" requestURI="">
			
		
<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:12%" sortable="true"
			title="Employee Name">
 <label id="txtEmployeeName1"  >${vo[1]}</label>
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:15%"  sortable="true"
			title="DCPS ID">
	  <label id="txtDcpsId1"  >${vo[0]}</label>
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center; width:8%" sortable="true"
			title="Sevarth ID"   >
<label id="txtSevaa"  >${vo[2]}</label>
			
		</display:column>
		
	<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center; width:8%" sortable="true"
			title="Pran No"   >
<label id="pranNo"  >${vo[7]}</label>
			
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center; width:8%" sortable="true"
			title="Date of Joining"   >
		<%-- <label id = "doj" value = "${vo[3]}">${vo[3]} </label>  --%>
		<input type="text" id = "doj" value = "${vo[3]}" readonly="readonly" size="9"/>	
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center; width:8%" sortable="true"
			title="Super Ann Date"   >
		<input type="text" id = "serend" value = "${vo[4]}" readonly="readonly" size="9"/> <%-- ${vo[4]} <label/> --%>
			
</display:column>

		<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center; width:8%" sortable="true"
			title="Service End Date"   >
		<input type="text" id = "servend" value = "${vo[8]}" readonly="readonly" size="9"/> <%-- ${vo[4]} <label/> --%>
			
</display:column>
				<input type="hidden" id="dob" name="dob" value="${vo[3] }" />
				<input type="hidden" id="sed" name="sed" value="${vo[4] }" />

				<input type="hidden" id="limitBeforeStart" name="limitBeforeStart" value ="" ></input>
				<input type="hidden" id="limitEnd" name="limitEnd" value= ""></input>
				
				


		<display:column title="Period" headerClass="datatableheader" style="text-align:center;width:8%" class="oddcentre" sortable="true">
					<!--<select   id="period" name="period" onchange="validatePeriod()">
						
								<option  selected="selected" value="-1">Select</option>
									<option value="Before 31-03-2016">Before 31-03-2016</option>
								<option value="After 31-03-2016" >After 31-03-2016</option>
							
					</select>-->
					<!-- <select name="period"   id="period" style="width: 250px" onchange="validatePeriod(); validateContribStartEndDate();">  -->
					<select name="period"   id="period" style="width: 250px"  onchange="validatePeriod(); " onblur="compareDatess(contriStartDt,servend,'Contribution Start Date cannot be greater than Employee Service End Date','<');compareDatess(contriStartDt,doj,'Contribution Start Date cannot be less than Employee Date of joining','>');compareD(contriStartDt,period,'Contribution Start Date should not be greater than 31/03/2019','<');"> 
						<option value="">-------Selected--------</option>
						<c:forEach items="${lLstLegValidatePeriod}" var="lLstLegValidatePeriod">
									<option value="${lLstLegValidatePeriod.lookupId}"><c:out value="${lLstLegValidatePeriod.lookupDesc}"></c:out></option>		
							</c:forEach>
			</select>
					
		</display:column>

<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center; width:8%" sortable="true"
			title="Contribution Start Date"   >
   <input type="text" name="contriStartDt" id="contriStartDt" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.deathDate}" />"  
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"                    
					onKeyPress="numberFormat(event)" 
					onfocus="onFocus(this)" 
					onblur="compareDatess(contriStartDt,servend,'Contribution Start Date cannot be greater than Employee Service End Date','<');
					compareDatess(contriStartDt,doj,'Contribution Start Date cannot be less than Employee Date of joining','>');
					onBlur(this);chkValidDate(this);
					compareD(contriStartDt,period,'Contribution Start Date should not be greater than 31/03/2021','<');"
					onchange="validatePer();"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgDtOfExpiry"
					onClick='window_open(event,"contriStartDt",375,570)'
					style="cursor: pointer;" /> 
					<!-- <input type="text" name="contriStartDt" id="contriStartDt" readonly="readonly"  /> -->
			
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center; width:8%" sortable="true"
			title="Contribution End Date"   >
 <!-- <input type="text" name="contriEndDt" id="contriEndDt" value="31/03/2016" readonly="readonly"  /> -->
		<input type="text" name="contriEndDt" id="contriEndDt"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" 
					value="${lObjTrnPnsnProcPnsnrDtlsVO.deathDate}" />"  
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" 
					onKeyPress="numberFormat(event)"  
					onblur="compareDatess(contriEndDt,servend,'Contribution End Date cannot be greater than Employee Service End Date','<');
					compareDatess(contriEndDt,doj,'Contribution End Date cannot be less than Employee Date of joining','>');
					compareDExs(contriEndDt,period,'Contribution End Date should not be greater than 31/03/2021','<');
					compareBothdates(contriEndDt,contriStartDt,'Contribution Start Date and End Date cannot be same','=');
					onBlur(this);chkValidDate(this);"
					onchange="validateDates();"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgDtOfExpiry"
					onClick='window_open(event,"contriEndDt",375,570)'
					style="cursor: pointer;"  />	
		
		</display:column>

		
		<display:column headerClass="datatableheader" style="text-align:left;width:20%" class="oddcentre" sortable="true" title="Employee Contribution" >	
				 <%-- <input type="text" id="empContri" name="empContri"  onblur="setEmployerContri(); sum();validatezeros();validateAmount();" onkeypress="return IsNumeric(event);"/><label class="mandatoryindicator"${varLabelDisabled}  >*</label> --%>
				 <input type="text" id="empContri" name="empContri"  onblur="setEmployerContri(); sum();validatezeros();validateAmount();" onkeypress="return isNumberKey(event,this)" onkeyup="checkDec(this);" step="0.01"/><label class="mandatoryindicator"${varLabelDisabled}  >*</label>
		</display:column>
		
		
			<display:column headerClass="datatableheader" style="text-align:left;width:20%" class="oddcentre" sortable="true" title="Employer Contribution" >	
				 <%-- <input type="text" id="employerContri" name="employerContri"   readonly="readonly" onblur="sum();validatezeros();validateAmount();" onkeypress="return IsNumeric(event);" /><label class="mandatoryindicator"${varLabelDisabled}  >*</label>	 --%>
<%-- 				 <input type="text" id="employerContri" name="employerContri" onblur="sum();validatezeros();validateAmount();" onkeypress="return IsNumeric(event);" /><label class="mandatoryindicator"${varLabelDisabled}  >*</label> --%>
	 <input type="text" id="employerContri" name="employerContri" onblur="sum();validatezeros();validateAmount();" onkeypress="return isNumberKey(event,this);"/><label class="mandatoryindicator"${varLabelDisabled}  >*</label>
		</display:column>
		
			<display:column headerClass="datatableheader" style="text-align:left;width:20%" class="oddcentre" sortable="true" title="Interest on Employee Contribution" >	
				 <%-- <input type="text" id="empInterest" name="empInterest"  onblur="setEmployerInt();validateInterest();sum();validatezeros();validateAmount();" onkeypress="return IsNumeric(event);"/><label class="mandatoryindicator"${varLabelDisabled}  >*</label> --%>
<%-- 				 <input type="text" id="empInterest" name="empInterest"  onblur="setEmployerInt();validateInterest();sum();validatezeros();validateAmount();" onkeyup="checkDec(this);" step="0.01"/><label class="mandatoryindicator"${varLabelDisabled}  >*</label> --%>
		<input type="text" id="empInterest" name="empInterest"  onblur="setEmployerInt();sum();validatezeros();validateAmount();" onkeypress="return isNumberKey(event,this);" onkeyup="checkDec(this);" step="0.01"/><label class="mandatoryindicator"${varLabelDisabled}  >*</label>
		</display:column>
		
		
			<display:column headerClass="datatableheader" style="text-align:left;width:20%" class="oddcentre" sortable="true" title="Interest on Employer Contribution" >	
				 <%-- <input type="text" id="employerInterest" name="employerInterest"  readonly="readonly"  onblur="" onkeypress="return IsNumeric(event);" /><label class="mandatoryindicator"${varLabelDisabled}  >*</label>	 --%>
				 <input type="text" id="employerInterest" name="employerInterest" onblur="sum();validatezeros();validateAmount();" onkeypress="return isNumberKey(event,this);" onkeyup="checkDec(this);"  step="0.01"/><label class="mandatoryindicator"${varLabelDisabled}  >*</label>
		</display:column>
		
		
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Total" >	
				 <input type="text" id="total" name="total"   disabled="disabled" /><label class="mandatoryindicator"${varLabelDisabled}  >*</label>
		</display:column>

	<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Remark" >	
				 <input type="text" id="remark" name="remark"  /><label class="mandatoryindicator"${varLabelDisabled}  >*</label>
		</display:column>

</display:table> 

				<input type="hidden" id="xyz" name="xyz" value="${vo[0] }" />
				<input type="hidden" id="pqr" name="pqr" value="${vo[1] }" />
				<input type="hidden" id="abc" name="abc" value="${vo[2] }" />
				
				
				
				<input type="hidden" id="limitBefore" name="limitBefore" value="${vo[5] }" />
				<input type="hidden" id="limit" name="limit" value="${vo[6] }" />
					
	</c:if>
	
</div>
	
	<div align="center">
<hdiits:button	name="btnSave"  type="button" captionid="BTN.SAVEE"  bundle="${dcpsLabels}"  onclick="validatePeriod();saveData();"/>	
<hdiits:button	name="btnreset"  type="button" captionid="BTN.RESETT"  bundle="${dcpsLabels}" 	onclick="clear_form_elements(this.form);"/>			
								</div>
	</fieldset>
<c:set var="pranUpdateStatus" value="${vo[9]}"></c:set>

</c:if>
	
</div>
	
	<fieldset><b><font color="red">Note : Kindly update the PRAN No. of employee before entering legacy data.</font></b></fieldset>
	</hdiits:form>
	
<script type="text/javascript">


function alphaFormatWithSpace(formfield)
{	
	var val;
	
	if((window.event.keyCode == 32) || (window.event.keyCode>64 && window.event.keyCode<91) || (window.event.keyCode>96 && window.event.keyCode<123)) 
	{
		val=formfield.value;
		if(val[1]!=null)
		{
			if(val[1].length>1)
			{
				window.event.keyCode=0;
			}
		}
	}
	else
	{
		window.event.keyCode=0;
	}
}


</script>


<script type="text/javascript">

	function sum() {
		// alert("hii");
		var empContri = Number(document.getElementById("empContri").value);
		var employerContri = Number(document.getElementById("employerContri").value);
		var empInterest = Number(document.getElementById("empInterest").value);
		var employerInterest = Number(document
				.getElementById("employerInterest").value);

		//   var empContriInt = Number(document.getElementById("empContriInt").value);
		//alert("empContriInt"+empContriInt);
		//  var employerContriInt = Number(document.getElementById("employerContriInt").value);
		//alert("employerContriInt"+employerContriInt);
		var total = empContri + employerContri + empInterest + employerInterest;
		//total=empContriInt + employerContriInt;

		if (isNaN(total)) {
			total = "";
		}

		document.getElementById("total").value = total;
		validTotal();

	}
	

function isNumberKey(evt, element) {
  var charCode = (evt.which) ? evt.which : event.keyCode
  if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46 || charCode == 8))
    return false;
  else {
    var len = $(element).val().length;
    var index = $(element).val().indexOf('.');
    if (index > 0 && charCode == 46) {
      return false;
    }
    if (index > 0) {
      var CharAfterdot = (len + 1) - index;
      if (CharAfterdot > 3) {
        return false;
      }
    }

  }
  return true;
}

</script>


<script type="text/javascript">
	
	
	function validTotal() {
		//alert("hi");
		var total = document.getElementById("total").value;
		//alert("total"+total);
		if (total > 6000000) {

			alert("Total amount should not be greater than 60 Lacs");
			document.getElementById("total").value = "";
			document.getElementById("empContri").value = "";
			document.getElementById("employerContri").value = "";
		}

	}
</script>


<script type="text/javascript">

	function sync1() {

		var empContri = document.getElementById("empContri");

		var employerContri = document.getElementById("employerContri");

		employerContri.value = empContri.value;

	}

	function sync2() {

		var empInterest = document.getElementById("empInterest");
		var employerInterest = document.getElementById("employerInterest");
		employerInterest.value = empInterest.value;

	}
</script>

	<script type="text/javascript">

		function clear_form_elements(ele) {

			tags = ele.getElementsByTagName('input');
			for (i = 0; i < tags.length; i++) {
				switch (tags[i].type) {
				case 'password':
				case 'text':
					tags[i].value = '';
					break;
				case 'checkbox':
				case 'radio':
					tags[i].checked = false;
					break;
				}
			}

			tags = ele.getElementsByTagName('select');
			for (i = 0; i < tags.length; i++) {
				if (tags[i].type == 'select-one') {
					tags[i].selectedIndex = 0;
				} else {
					for (j = 0; j < tags[i].options.length; j++) {
						tags[i].options[j].selected = false;
					}
				}
			}

			tags = ele.getElementsByTagName('textarea');
			for (i = 0; i < tags.length; i++) {
				tags[i].value = '';
			}

		}
	</script>
	
	<script type="text/javascript">
        var specialKeys = new Array();
        specialKeys.push(8); //Backspace
        function IsNumeric(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
           // document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
        }
    </script>
	
	
	<script type="text/javascript">
	
	
	function getEmp()
	{
	//alert("hii...");
		var txtSevaarthId =  document.getElementById("txtSevaarthId").value;
		//alert("SevarthID"+txtSevaarthId);
		var txtEmployeeName = document.getElementById("txtEmployeeName").value;
	//	alert("Employee Name"+txtEmployeeName);
		var txtDcpsId = document.getElementById("txtDcpsId").value;
	//	alert("DCPSID"+txtDcpsId);
		//var txtPpanNo = "";//document.getElementById("txtPpanNo").value.trim();
		
		// boolean flag=true;
		if(txtSevaarthId!="" || txtEmployeeName!="" || txtDcpsId!=""  ){
			//alert("SevarthID"+txtSevaarthId);
			var urls ="ifms.htm?actionFlag=getDcpsLegacyData&txtSevaarthId="+txtSevaarthId+"&txtEmployeeName="+txtEmployeeName+"&txtDcpsId="+txtDcpsId+"&fromSearch=Yes";
		//	alert("urls"+urls);
		showProgressbar('Please Wait<br>Your request is in progress...');
		document.DCPSForwardedFormsList.action = urls ;
		//enableAjaxSubmit(true);
		document.DCPSForwardedFormsList.submit();

		}
		else if(txtSevaarthId == "" && txtEmployeeName == ""  && txtDcpsId=="")
		{
			alert('Please enter search criteria');
			return false;
		}

	}
	
	
	</script>
	
		<script type="text/javascript">
		
	
			function saveData() {

				var period = "";
				//var empContri="";
				//var employerContri="";
				//var empInterest="";
				//  var employerInterest="";
				//	   var empContriInt =""; Number(document.getElementById("empContriInt").value);

				// var employerContriInt = "";Number(document.getElementById("employerContriInt").value);

				var total = "";
				var txtSevarth = document.getElementById("abc").value;
				//alert("SevarthID^^^^"+txtSevarth);
				var txtEmployeeName1 = document.getElementById("pqr").value;
				//alert("Employee Name"+txtEmployeeName1);
				var txtDcpsId1 = document.getElementById("xyz").value;
				//alert("txtDcpsId1"+txtDcpsId1);
				period = document.getElementById("period").value;
				// alert("period"+period);
				var empContri = document.getElementById("empContri").value;
				var employerContri = document.getElementById("employerContri").value;
				var empInterest = document.getElementById("empInterest").value;
				var employerInterest = document
						.getElementById("employerInterest").value;
				var contriStartDt = document.getElementById("contriStartDt").value;
				var contriEndDt = document.getElementById("contriEndDt").value;
				var remark = document.getElementById("remark").value;
				//empContriInt = document.getElementById("empContriInt").value;

				//employerContriInt = document.getElementById("employerContriInt").value;

				total = document.getElementById("total").value;

				if (period == null || period == "" || period == '-1'
						|| empContri == null || empContri == ""
						|| employerContri == null || employerContri == ""
						|| empInterest == null || empInterest == ""
						|| employerInterest == null || employerInterest == ""
						|| contriStartDt == null || contriStartDt == ""
						|| contriEndDt == "" || contriEndDt == null || remark == "") {
					alert("Please Enter all the Fields");
					return false;
				}

				else {
					var answer = confirm("Are you sure, you want to save the details?");
					if (answer) {

						url = "ifms.htm?actionFlag=saveDcpsLegacyData&abc="
								+ txtSevarth + "&pqr=" + txtEmployeeName1
								+ "&xyz=" + txtDcpsId1 + "&period=" + period
								+ "&empContri=" + empContri
								+ "&employerContri=" + employerContri
								+ "&empInterest=" + empInterest
								+ "&employerInterest=" + employerInterest
								+ "&contriStartDt=" + contriStartDt
								+ "&contriEndDate=" + contriEndDt + "&remark=" + remark + "&total="
								+ total;

						//alert("Data Saved Successfully");
						document.DCPSForwardedFormsList.action = url;
						document.DCPSForwardedFormsList.submit();

					}

				}
			}

			function validatePeriod() {

				var txtSevarth = document.getElementById("abc").value;
				//alert("txtSevarth"+txtSevarth);
				var period = document.getElementById("period").value;
				//alert("period"+period);

				var uri = 'ifms.htm?actionFlag=validatePeriod';
				var url = 'abc=' + txtSevarth + '&period=' + period;
		 

				var myAjax = new Ajax.Request(uri, {
					method : 'post',
					asynchronous : false,
					parameters : url,
					onSuccess : function(myAjax) {
						validatePeriodAjax(myAjax);

					},
					onFailure : function() {
						alert('Something went wrong...');
					}
				});

			}

			function validatePeriodAjax(myAjax) {
				//alert("hello inside last function");

				XMLDoc = myAjax.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
				if (checkFlag = 'failure') {
					alert("Data for this period is already inserted");
					document.getElementById("period").value = "-1";

				}

			}

			function validatezeros() {
				var emp = parseInt(document.getElementById("empContriInt").value);
				var employer = parseInt(document
						.getElementById("employerContriInt").value);
				if (emp < 0.1 || employer < 0.1) {
					alert("Please Enter Correct value");
					document.getElementById("empContriInt").value = "";
					document.getElementById("employerContriInt").value = "";
					// document.getElementById("empInterest").value="";
					//document.getElementById("employerInterest").value="";
				}

			}
		</script>

	<script>

	var pranUpdateStatus = '${pranUpdateStatus}';

	if (pranUpdateStatus == "Pran updated by NPS utility") {

		alert("For your information The given htesevaarth id pran created manual or outside the htesevaarth system, so first map the pran with NSDL Against the DDO registration number and dto registration number");
		url = "ifms.htm?actionFlag=getDcpsLegacyData";
		self.location.href = url;

	}
	
	
	var actionEmp = '${actionEmp}';

	if (actionEmp == "failure") {

		alert("Searched  employee is not DCPS employee or PRAN No. is not mapped or not belongs to this DDO");
		url = "ifms.htm?actionFlag=getDcpsLegacyData";
		self.location.href = url;

	}/* else{
		
		
	} */
</script>


	<script>
	var successFlag = '${successFlag}';

	if (successFlag == "success") {

		alert("Data is saved successfully. Please Navigate to NPS > DCPS Legacy > Verify DCPS Legacy Data");
		url = "ifms.htm?actionFlag=getDcpsLegacyData";
		self.location.href = url;

	}
	
	if (successFlag == "successFail") {

		alert("Data is not saved Due to  data is already save or verify status.");
		url = "ifms.htm?actionFlag=getDcpsLegacyData";
		self.location.href = url;

	}
</script>

	
	<script>


	function validateAmount(){

		 var period = document.getElementById("period").value;
		 //alert("period"+period);
		 var contri = parseInt(document.getElementById("empContri").value);
		// alert("Contri"+contri);
		// alert("hii123");
		 var interest = parseInt(document.getElementById("empInterest").value);
		// alert("interest"+interest);
		// if(period==10001198213)
		// {
			 //alert("hii123456789");
			 if(contri>6000000 || interest>6000000 )
			 {
				 //alert("Contri"+contri);
				 alert("Maximum acceptable value is 60 lakhs only");
				 document.getElementById("empContri").value="";
				 document.getElementById("employerContri").value="";
				 document.getElementById("empInterest").value="";
				 document.getElementById("employerInterest").value="";
				 document.getElementById("total").value ="";
				 	}
			 
		// }



		 if(period==10001198212)
		 {
			 if(contri>6000000 || interest>6000000 )
			 {
				 alert("Maximum acceptable value is 60 lakhs only");
				 document.getElementById("empContri").value="";
				 document.getElementById("employerContri").value="";
				 document.getElementById("empInterest").value="";
				 document.getElementById("employerInterest").value="";
				 document.getElementById("total").value ="";
				 	}
			 
		 }
		 



		}
	
	
	
	function validateInterest() {
			var contri = parseInt(document.getElementById("empContri").value);

			var interest = parseInt(document.getElementById("empInterest").value);
			
		if (interest > contri) {
				 alert("Interest should not be greater than contribution");
				document.getElementById("empContri").value = "";
				document.getElementById("employerContri").value = "";
				document.getElementById("empInterest").value = "";
				document.getElementById("employerInterest").value = "";
				document.getElementById("total").value = "";
                  
			}  

		}

		
		function setEmployerContri() {

			var empContri = document.getElementById("empContri").value;
			var period1 = document.getElementById("period").value;
			
			if (period1 == '10001198277') {

				var contri = (Number(empContri * 40)/100).toFixed(2);
			//	alert("contri"+contri);
				var contriemplr = (Number(empContri) + Number(contri));
				//alert("contriemplr"+contriemplr);
				
				
			document.getElementById("employerContri").value = contriemplr;
			
			}
			
			else if(period1 =='10001198258')
				{
				
				document.getElementById("employerContri").value = empContri;
				}

		}

		function setEmployerInt() {
			
			if(document.getElementById("empContri").value == '' || document.getElementById("empContri").value == null)
			{	
				document.getElementById("empInterest").value = '';
				
			}

			var empContri = document.getElementById("empInterest").value;

			document.getElementById("employerInterest").value = empContri;

		}
	</script>
	


<script type="text/javascript">


function compareBothdates(fieldName1, fieldName2, alrtStr, flag) {
	
	
	var one_day = 1000 * 60 * 60 * 24;

	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;

	var la_Date1 = new Array();
	la_Date1 = Date1.split("/");
	var la_Date2 = new Array();
	la_Date2 = Date2.split("/");

	var date1 = new Date(la_Date1[2], la_Date1[1] - 1, la_Date1[0]);
	var date2 = new Date(la_Date2[2], la_Date2[1] - 1, la_Date2[0]);
	
	var Diff = Math.floor((date2.getTime() - date1.getTime()) / (one_day));
	if (flag == '=' && Diff == 0) {
		alert(alrtStr);
		document.getElementById("contriStartDt").value = "";
		document.getElementById("contriEndDt").value = "";
		return false;
	}
	else if (flag == '=' && Diff > 0) {
		alert("Constribution end date should not be less than start date");
		document.getElementById("contriEndDt").value = "";
		return false;
	}
 else {
		return true;
	}
}
	
	function compareDatess(fieldName1, fieldName2, alrtStr, flag) {
		
		
		var one_day = 1000 * 60 * 60 * 24;

		var Date1 = fieldName1.value;
		var Date2 = fieldName2.value;

		var la_Date1 = new Array();
		la_Date1 = Date1.split("/");
		var la_Date2 = new Array();
		la_Date2 = Date2.split("/");

		var date1 = new Date(la_Date1[2], la_Date1[1] - 1, la_Date1[0]);
		var date2 = new Date(la_Date2[2], la_Date2[1] - 1, la_Date2[0]);
		
		var Diff = Math.floor((date2.getTime() - date1.getTime()) / (one_day));
		if (flag == '=' && Diff == 0) {
			alert(alrtStr);
			document.getElementById("contriStartDt").value = "";
			return false;
		}

		else if ((flag == '<' && Diff < 0) || (flag == '>' && Diff > 0)) {
			alert(alrtStr);
			document.getElementById("contriStartDt").value = '';
			return false;
		} else {
			return true;
		}
	}
	
	
</script>  
<script type="text/javascript">

function compareD( fielname1,fielname2 , alrtStr, flag)
{
	
	var period1 = document.getElementById("period").value;
	
	var contriStartDt = document.getElementById("contriStartDt").value; 
	
		//alert(period1);
		
		 if (period1 == '10001198258') {
			var a ='01/04/2011'
			alrtStr = "Contribution Start Date should not be less than 01/04/2011";
			compareDat(a, contriStartDt, alrtStr, flag);
		}
		if (period1 == '10001198259') {
			var a ='01/04/2021'
			alrtStr = "Contribution Start Date should not be less than 01/04/2021";
			compareDat(a, contriStartDt, alrtStr, flag);
			
		} 		
 		if (period1 == '10001198260') {
			var a ='01/04/2022'
			alrtStr = "Contribution Start Date should not be less than 01/04/2022";
			compareDat(a, contriStartDt, alrtStr, flag);
			
		}
 		if (period1 == '10001198261') {
			var a ='01/04/2006'
			alrtStr = "Contribution Start Date should not be less than 01/04/2006"; //alert(alrtStr+flag);
			compareDat(a, contriStartDt, alrtStr, flag);
			
		}
		
 		/* if (period1 == '10001198260') {
 			var a = '31/03/2023';
 			compareDat(contriStartDt, a, alrtStr, flag)
		} */

	}
</script>
<script type="text/javascript">
	
	function compareDat(fieldName1, fieldName2, alrtStr, flag) {
		
		var one_day = 1000 * 60 * 60 * 24;

		var Date1 = fieldName1;
		var Date2 = fieldName2;
		
		var la_Date1 = new Array();
		la_Date1 = Date1.split("/");
		var la_Date2 = new Array();
		la_Date2 = Date2.split("/");

		var date1 = new Date(la_Date1[2], la_Date1[1] - 1, la_Date1[0]);
		var date2 = new Date(la_Date2[2], la_Date2[1] - 1, la_Date2[0]);
		
		var Diff = Math.floor((date2.getTime() - date1.getTime()) / (one_day));
			 
		if (flag == '=' && Diff == 0) {
			alert(alrtStr);
			document.getElementById("contriStartDt").value = "";
			document.getElementById("contriEndDt").value = "";
			return false;
		}

		else if ((flag == '<' && Diff < 0) || (flag == '>' && Diff > 0)) {
			alert(alrtStr);
			document.getElementById("contriStartDt").value = '';
			document.getElementById("contriEndDt").value = "";
			return false;
		} else {
			return true;
		}
	}
	
	
</script>  


<script type="text/javascript">

function compareDExs( fielname1,fielname2 , alrtStr, flag)
{
	
	var period1 = document.getElementById("period").value;
	var contriStartDt = document.getElementById("contriStartDt").value; 
	//alert("contriStartDt"+contriStartDt); 
	
	var contriEndDt = document.getElementById("contriEndDt").value; 
	
	/*   if(contriStartDt == null || contriStartDt == "")
	{
		alert("Please enter Contributuion start date first");
		document.getElementById("contriEndDt").value = '';
		return false;
	}	 */
   
	 if (period1 == '10001198277') {
			var a = '31/03/2023';
			compareDatExs(contriEndDt, a, alrtStr, flag);
			
			
		}

		else if (period1 == '10001198258') {
			var a = '31/03/2021';
			alrtStr = "Contribution End Date should not be greater than 31/03/2021";
			compareDatExs(contriEndDt,a, alrtStr, flag)
			

		}else if (period1 == '10001198259') {
			var a = '31/03/2022';
			alrtStr = "Contribution End Date should not be greater than 31/03/2022";
			compareDatExs(contriEndDt,a, alrtStr, flag)
			

		}else if (period1 == '10001198260') {
			var a = '31/03/2023';
			alrtStr = "Contribution End Date should not be greater than 31/03/2023";
			compareDatExs(contriEndDt,a, alrtStr, flag)
			

		}else if (period1 == '10001198261') {
		var a = '31/03/2011';
		alrtStr = "Contribution End Date should not be greater than 31/03/2011";
		compareDatExs(contriEndDt,a, alrtStr, flag)
		

	}
		

	}
</script>
<script type="text/javascript">
	
	function compareDatExs(fieldName1, fieldName2, alrtStr, flag) {
		
		var one_day = 1000 * 60 * 60 * 24;

		var Date1 = fieldName1;
		var Date2 = fieldName2;
		
		var la_Date1 = new Array();
		la_Date1 = Date1.split("/");
		var la_Date2 = new Array();
		la_Date2 = Date2.split("/");

		var date1 = new Date(la_Date1[2], la_Date1[1] - 1, la_Date1[0]);
		var date2 = new Date(la_Date2[2], la_Date2[1] - 1, la_Date2[0]);
		
		var Diff = Math.floor((date2.getTime() - date1.getTime()) / (one_day));
				
		if (flag == '=' && Diff == 0) {
			alert(alrtStr);
			document.getElementById("contriEndDt").value = "";
			return false;
		}

		else if ((flag == '<' && Diff < 0) || (flag == '>' && Diff > 0)) {
			alert(alrtStr);
			document.getElementById("contriEndDt").value = '';
			return false;
		} else {
			return true;
		}
	}
	
	
</script>  

<script>

	function validateDates() {

		 var txtSevarth = document.getElementById("abc").value;
		//alert("txtSevarth"+txtSevarth);
		var period = document.getElementById("period").value;
		//alert("period" + period); 
		
		var contriStartDt = document.getElementById("contriStartDt").value;
		var contriEndDt = document.getElementById("contriEndDt").value;
		
		var uri = 'ifms.htm?actionFlag=validateDates';
		var url = 'abc=' + txtSevarth + '&period=' + period + '&contriStartDt=' + contriStartDt + '&contriEndDt=' + contriEndDt;
		//alert("url"+url); 

		 var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				validateDatesAjax(myAjax);

			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		}); 

	}

	function validateDatesAjax(myAjax) {
		/* alert("hello inside last function"); */

		XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		if (checkFlag = 'failure') {
			alert("Legacy Data is already submitted for entered Contribution start date and End Date ");
			//document.getElementById("period").value = "-1";
			document.getElementById("contriStartDt").value = '';
			document.getElementById("contriEndDt").value = '';

		}
 
	}
	
	
	function validatePer() {
		
		if(document.getElementById("period").value == '' || document.getElementById("period").value == '-1')
			{
				alert('Please select Period first');
				document.getElementById("contriStartDt").value = '';
				document.getElementById("contriEndDt").value = '';
			}
	}
	
	
	
	function digitFormat(formfield)
	{	
		var val;
		if(window.event.keyCode>47 && window.event.keyCode<58)
		{
			val=formfield.value;
			if(val[1]!=null)
			{
				if(val[1].length>1)
				{
					window.event.keyCode=0;
				}
			}
		}
		else
		{
			window.event.keyCode=0;
		}
	}
	
</script>

<script type="text/Javascript">
function checkDec(el){
 var ex = /^[0-9]+\.?[0-9]*$/;
 if(ex.test(el.value)==false){
   el.value = el.value.substring(0,el.value.length - 1);
  }
}

</script>

	
	
	
<input type="hidden" name="hidSearchFromDDOAsst" id="hidSearchFromDDOAsst" value="searchByDDO" />
<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS"
	parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromDDOAsst}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />