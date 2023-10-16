<%
try{
%>
<%@page import="java.util.List"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page" />
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/OuterPaybillPara.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="billList" value="${resultValue.billList}"></c:set>
<c:set var="nonGovDeducTypes" value="${resultValue.nonGovDeducTypes}" > </c:set>
<c:set var="dsgnList" value="${resultValue.dsgnList}"></c:set>
<c:set var="deducList" value="${resultValue.deducList}"></c:set>
<c:set var="empListSize" value="${resultValue.empListSize}"></c:set>
<c:set var="counter" value="${0}"></c:set>
<c:set var="deducType" value="${resultValue.deducType}"></c:set>
<c:set var="empIDBySearch" value="${resultValue.empIDBySearch}"></c:set>
<c:set var="dsgnId" value="${resultValue.dsgnId}"></c:set>
<c:set var="billNo" value="${resultValue.billNo}"></c:set>
<c:set var="msg" value="${resultValue.msg}"></c:set>
<c:set var="yearList" value="${resultValue.yearList}" ></c:set>
<c:set var="monthList" value="${resultValue.monthList}" ></c:set>
<c:set var="curYear" value="${resultValue.curYear}" ></c:set>
<c:set var="curMonth" value="${resultValue.curMonth}" ></c:set><!--
added by vaibhav tyagi : start
--><c:set var="deducListSize" value="${resultValue.deducListSize}" ></c:set><!--
added by vaibhav tyagi : end
--><fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request" />
<script type="text/javascript">
function selectionForm()
{
	if("${msg}"!='')
	{
		alert('${msg}');		
		url='./hrms.htm?actionFlag=getNonGovDeductionMaster&billNo='+'${billNo}'+'&selMonth='+'${curMonth}'+'&selYear='+'${curYear}'+'&deducType='+'${deducType}';
		document.NonGovDeductionMaster.action=url;
		document.NonGovDeductionMaster.submit();
		showProgressbar("Please wait...");
	}
	if('${deducType}' > 0)
	{
		document.NonGovDeductionMaster.payItemCombo.value = '${deducType}';
	}
	if('${dsgnId}' > 0)
	{
		document.NonGovDeductionMaster.EmpDsgnCombo.value = '${dsgnId}';
	}
	if('${billNo}' > 0)
	{
		document.NonGovDeductionMaster.BillGrpCombo.value = '${billNo}';
	}
	if('${curYear}' > 0)
	{
		document.NonGovDeductionMaster.cmbFromYear.value = '${curYear}';
	}
	if('${curMonth}' > 0)
	{
		document.NonGovDeductionMaster.cmbFromMonth.value = '${curMonth}';
	}
}
function selDeselectAllowances(obj)
{
	var chkBoxArr= document.getElementsByName("selcheckBoxAllow");
	var chkLength=chkBoxArr.length;
	var chkAllParent=document.getElementsByName("selcheckBoxAllowSelectAll");
	var amount=document.getElementById("amount").value;
	if(amount >= 0)
	{
		if(chkAllParent[0].checked)
  		{
			for(var i=0;i<chkLength;i++)
			{
				document.getElementById("newAmount"+(i+1)).value = amount;
				chkBoxArr[i].checked = true;
			}
		}
		else
		{
			for(var j=0;j<chkLength;j++)
			{
				var ExistingAmount  = document.getElementById('exisAmount'+(j+1)).value;
				document.getElementById("amount").value='';
				document.getElementById('newAmount'+(j+1)).value=ExistingAmount;
				chkBoxArr[j].checked = false;
			}
		}
	}
	else
	{
		alert("Please enter Amount");
		document.getElementById("amount").focus();
		chkAllParent[0].checked = false;
	}
}
function deselect()
{
	var chkBoxArr= document.getElementsByName("selcheckBoxAllow");
	var chkLength=chkBoxArr.length;
	var deducType='${deducType}';
	for(var i=0;i<chkLength;i++)
	{
		if( chkBoxArr[i].checked == true)
	 	{
			var amount=document.getElementById("amount").value;
			if(amount>=0)
			{
				var newAmountHidden= document.getElementById("newAmountHdn"+(i+1)).value;
			 	document.getElementById("newAmount"+(i+1)).value = amount;
			 	if((parseInt(deducType)==96)&&(newAmountHidden!=amount)){
			 		alert('Please provide the division of Other Recovery in a proper format.\nExample: LIC-500;Co Society-500');
					document.getElementById("nonGovTypeValueForHdn"+(i+1)).disabled=false;
					document.getElementById("nonGovTypeValueForHdn"+(i+1)).focus();
					document.getElementById("nonGovTypeValueForHdn"+(i+1)).select();
				}
			}
			else
			{
				alert("Please enter Amount");
				document.getElementById("amount").focus();
				chkBoxArr[i].checked = false;
			}
	 	}
		if(chkBoxArr[i].checked == false)
		{
			if((document.getElementById("newAmount"+(i+1)).value ==  document.getElementById('amount').value))
			{
				document.getElementById("newAmount"+(i+1)).value=document.getElementById("exisAmount"+(i+1)).value;
				document.getElementById("nonGovTypeValueForHdn"+(i+1)).value=document.getElementById("nonGovTypeValueOld"+(i+1)).value;
				document.getElementById("nonGovTypeValueForHdn"+(i+1)).disabled=true;
			}
		} 
	}
}
function submitForm()
{
	////var empId=document.getElementById("Employee_ID_NonGovEmpSearch").value;
	if(!confirm('Are you sure,you want to update Details ?'))
		return false;
	var empListSize = '${empListSize}';
	
	var index = document.NonGovDeductionMaster.payItemCombo.selectedIndex;
	var deducType = document.NonGovDeductionMaster.payItemCombo.options[index].value;

	var dsgn_index = document.NonGovDeductionMaster.EmpDsgnCombo.selectedIndex;
	var dsgnId = document.NonGovDeductionMaster.EmpDsgnCombo.options[dsgn_index].value;

	var billNo_index = document.NonGovDeductionMaster.BillGrpCombo.selectedIndex;
	var billNo = document.NonGovDeductionMaster.BillGrpCombo.options[billNo_index].value;

	var selMonth_index = document.NonGovDeductionMaster.cmbFromMonth.selectedIndex;
	var selMonth = document.NonGovDeductionMaster.cmbFromMonth.options[selMonth_index].value; 

	var selYear_index = document.NonGovDeductionMaster.cmbFromYear.selectedIndex;
	var selYear = document.NonGovDeductionMaster.cmbFromYear.options[selYear_index].value;
	
	if(billNo == -1)
	{
		alert("Please select Bill No");
		document.getElementById("BillGrpCombo").focus();
		return false;
	}
	else if(deducType == -1)
	{
		alert("Please select Deduction Type");
		document.getElementById("payItemCombo").focus();
		return false;
	}
	else if(selYear == -1)
	{
		alert("Please select For Year");
		document.getElementById("cmbFromYear").focus();
		return false;
	}
	else if(selMonth == -1)
	{
		alert("Please select For Month");
		document.getElementById("cmbFromMonth").focus();
		return false;
	}
	else
	{
		if(document.getElementById("Employee_ID_EmpOtherSearch").value==document.getElementById("Employee_Name_EmpOtherSearch").value)
		{	
	    	empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	    }
	   	else
	   	{
			empId=document.getElementById("Employee_Name_EmpOtherSearch").value;
	   	}
		url='./hrms.htm?actionFlag=getNonGovDeductionMaster&deducType='+deducType+'&dsgnId='+dsgnId+'&billNo='+billNo+'&InsertOrUpdate=Y&empListSize='+empListSize+'&empId='+'${empIDBySearch}'+'&FromJSP=Y&Month='+selMonth+'&Year='+selYear;
		//url = './hrms.htm?actionFlag=PayBillGeneratScheduler';
		//alert(url);
	}
	document.NonGovDeductionMaster.Save.disabled = true;
	document.NonGovDeductionMaster.action=url;
	document.NonGovDeductionMaster.submit();
	showProgressbar("Please wait...");
}

function getEmployeeFromType()
{
	var index = document.NonGovDeductionMaster.payItemCombo.selectedIndex;
	var deducType = document.NonGovDeductionMaster.payItemCombo.options[index].value;

	var dsgn_index = document.NonGovDeductionMaster.EmpDsgnCombo.selectedIndex;
	var dsgnId = document.NonGovDeductionMaster.EmpDsgnCombo.options[dsgn_index].value;

	var billNo_index = document.NonGovDeductionMaster.BillGrpCombo.selectedIndex;
	var billNo = document.NonGovDeductionMaster.BillGrpCombo.options[billNo_index].value;

	var selMonth_index = document.NonGovDeductionMaster.cmbFromMonth.selectedIndex;
	var selMonth = document.NonGovDeductionMaster.cmbFromMonth.options[selMonth_index].value; 

	var selYear_index = document.NonGovDeductionMaster.cmbFromYear.selectedIndex;
	var selYear = document.NonGovDeductionMaster.cmbFromYear.options[selYear_index].value;
	
	var url = "";
	if(billNo == -1 && document.getElementById("Employee_ID_EmpOtherSearch").value == "")
	{
		alert("Please select Bill No");
		document.getElementById("BillGrpCombo").focus();
		return false;
	}
	else if(deducType == -1)
	{
		alert("Please select Deduction Type");
		document.getElementById("payItemCombo").focus();
		return false;
	}
	else if(selYear == -1)
	{
		alert("Please select For Year");
		document.getElementById("cmbFromYear").focus();
		return false;
	}
	else if(selMonth == -1)
	{
		alert("Please select For Month");
		document.getElementById("cmbFromMonth").focus();
		return false;
	}
	else
	{
		if(document.getElementById("Employee_ID_EmpOtherSearch").value==document.getElementById("Employee_Name_EmpOtherSearch").value)
		{	
	    	empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	    }
	   	else
	   	{
			empId=document.getElementById("Employee_Name_EmpOtherSearch").value;
	   	}
		url='./hrms.htm?actionFlag=getNonGovDeductionMaster&deducType='+deducType+'&dsgnId='+dsgnId+'&billNo='+billNo+'&empId='+empId+'&FromJSP=Y&Month='+selMonth+'&Year='+selYear;
	}
	/*document.NonGovDeductionMaster.actionFlag.value = "getNonGovDeductionMaster";
	document.NonGovDeductionMaster.deducType.value = deducType;
	document.NonGovDeductionMaster.dsgnId.value = dsgnId;
	document.NonGovDeductionMaster.billNo.value = billNo;
	document.NonGovDeductionMaster.empId.value = empId;
	document.NonGovDeductionMaster.FromJSP.value = "Y";
	document.NonGovDeductionMaster.Month.value = selMonth;
	document.NonGovDeductionMaster.Year.value = selYear;
	document.NonGovDeductionMaster.action = "hrms.htm?";*/
	document.NonGovDeductionMaster.action = url;
	document.NonGovDeductionMaster.submit();
	showProgressbar("Please wait...");
}
function chkValue()
{
	
	var index = document.NonGovDeductionMaster.payItemCombo.selectedIndex;
	var deducType = document.NonGovDeductionMaster.payItemCombo.options[index].value;

	var dsgn_index = document.NonGovDeductionMaster.EmpDsgnCombo.selectedIndex;
	var dsgnId = document.NonGovDeductionMaster.EmpDsgnCombo.options[dsgn_index].value;

	var billNo_index = document.NonGovDeductionMaster.BillGrpCombo.selectedIndex;
	var billNo = document.NonGovDeductionMaster.BillGrpCombo.options[billNo_index].value;

	var selMonth_index = document.NonGovDeductionMaster.cmbFromMonth.selectedIndex;
	var selMonth = document.NonGovDeductionMaster.cmbFromMonth.options[selMonth_index].value; 

	var selYear_index = document.NonGovDeductionMaster.cmbFromYear.selectedIndex;
	var selYear = document.NonGovDeductionMaster.cmbFromYear.options[selYear_index].value;
	
	var url = "";
	var empId = "";
	if(deducType == -1)
	{
		alert("Please select Deduction Type");
		document.getElementById("payItemCombo").focus();
		return false;
	}
	else if(selYear == -1)
	{
		alert("Please select For Year");
		document.getElementById("cmbFromYear").focus();
		return false;
	}
	else if(selMonth == -1)
	{
		alert("Please select For Month");
		document.getElementById("cmbFromMonth").focus();
		return false;
	}
	else
	{
		if(document.getElementById("Employee_ID_EmpOtherSearch").value==document.getElementById("Employee_Name_EmpOtherSearch").value)
		{	
	    	empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	    }
	   	else
	   	{
			empId=document.getElementById("Employee_Name_EmpOtherSearch").value;
	   	}
		url='./hrms.htm?actionFlag=getNonGovDeductionMaster&deducType='+deducType+'&dsgnId='+dsgnId+'&billNo='+billNo+'&empId='+empId+'&FromJSP=Y&Month='+selMonth+'&Year='+selYear;
	}
	document.NonGovDeductionMaster.action = url;
	document.NonGovDeductionMaster.submit();
	showProgressbar("Please wait...");
}

//added by vaibhav tyagi: start
function activateOtherRecoveryType(id){
	var deducType='${deducType}';
	var newAmountHidden= document.getElementById("newAmountHdn"+id).value;
	var newAmount= document.getElementById("newAmount"+id).value;
	if((parseInt(deducType)==96)&&(newAmountHidden!=newAmount)){
		alert('Please provide the division of Other Recovery in a proper format.\nExample: LIC-500;Co Society-500');
		document.getElementById("nonGovTypeValueForHdn"+id).disabled=false;
		document.getElementById("nonGovTypeValueForHdn"+id).focus();
		document.getElementById("nonGovTypeValueForHdn"+id).select();
	}
	else{
		document.getElementById("nonGovTypeValueForHdn"+id).disabled=true;
	}
}

function setOtherRecoveryType(id){
	var nonGovTypeValue=document.getElementById("nonGovTypeValueForHdn"+id).value.trim();
	var nonGovArray = new Array();
	var nonGovSubArray = new Array();
	var nonGovArrayString;
	var nonGovSubArrayLength;
	var money;
	var newAmount= document.getElementById("newAmount"+id).value;

	if(nonGovTypeValue!=null&&nonGovTypeValue!=""){
		nonGovArray = nonGovTypeValue.split(";");
		for(var i=0;i<nonGovArray.length;i++){
			nonGovSubArray= nonGovArray[i].split("-");
			if(i==0){
				nonGovSubArrayLength=0;
				money=0;
				money=parseInt(nonGovSubArray[1]);
				nonGovSubArrayLength=parseInt(nonGovSubArray.length);
			}
			else{
				nonGovSubArrayLength=parseInt(nonGovSubArrayLength)+parseInt(nonGovSubArray.length);
				money=parseInt(money)+parseInt(nonGovSubArray[1]);
			}
		}

		if(isNaN(money)){
			alert('Please provide the division of Other Recovery in a proper format.\nExample: LIC-500;Co Society-500');
			document.getElementById("nonGovTypeValueForHdn"+id).focus();
			document.getElementById("nonGovTypeValueForHdn"+id).select();
		}

		else if(parseInt(money)!==parseInt(newAmount)){
			alert('Sum of the amount in division must be equal to the New Amount');
			document.getElementById("nonGovTypeValueForHdn"+id).focus();
			document.getElementById("nonGovTypeValueForHdn"+id).select();
		}
		else{
			document.getElementById("nonGovTypeValue"+id).value=nonGovTypeValue;
		}
	}

	else{
		alert('Please provide the division of Other Recovery in a proper format.\nExample: LIC-500;Co Society-500');
		document.getElementById("nonGovTypeValueForHdn"+id).focus();
		document.getElementById("nonGovTypeValueForHdn"+id).select();
	}
}
//added by vaibhav tyagi: end

function onclosefunction()
{
		window.location="hrms.htm?actionFlag=validateLogin";
}
</script>

<body>
<hdiits:form name="NonGovDeductionMaster" validate="true" method="POST"	encType="multipart/form-data">
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	 <BR>
	 <table width="85%" align="center" border="1">
		<tr>
			<td>
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
					<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
					<jsp:param name="SearchEmployee" value="EmpOtherSearch"/>
					<jsp:param name="formName" value="EmpOtherInfo"/>
					<jsp:param name="functionName" value="chkValue"/>
				</jsp:include>
			</td>
		</tr>
	</table>
	 <table width="85%" align="center" border="0">
<!--		 <tr align="center">-->
<!--			<fmt:formatDate value="${nonGovDeducEfftStartDt}" pattern="dd/MM/yyyy" var="startDate" />-->
<!--			<fmt:formatDate value="${nonGovDeducEfftEndDt}" pattern="dd/MM/yyyy" var="endDate" />-->
<!--			-->
<!--			<td class="fieldLabel" colspan="9"><b><hdiits:caption captionid="NGD.startDate" bundle="${enLables}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!--			<hdiits:dateTime name="startDate" captionid="NGD.startDate" bundle="${enLables}" validation="txt.isdt,txt.isrequired" minvalue=""></hdiits:dateTime>-->
<!--			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--			<b><hdiits:caption captionid="NGD.endDate" bundle="${enLables}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!--			<hdiits:dateTime name="endDate" captionid="NGD.endDate" bundle="${enLables}" minvalue=""></hdiits:dateTime></td>-->
<!---->
<!--		</tr>-->
		<tr>
			<td align="center" colspan="9" width="100%">
				<font color="red">Note : Existing Amount is always shows latest saved amount.</font>
			</td>
		</tr>
		<tr/>
		<tr/>
		<tr/>
		<tr/>
		<tr>
			<td align="left" width="25%"  id="FromYear1">
				For Year
			</td>
			<td align="left" width="25%" id="FromYear2">
				<hdiits:select name="selFromYear" size="1" sort="false" id="cmbFromYear" onchange="GetFromMonths()" mandatory="true"> 
	   				<hdiits:option value="-1"> --Select-- </hdiits:option>
	 				<c:forEach items="${yearList}" var="FromyearList">
 	     				<hdiits:option value="${FromyearList.lookupShortName}"> ${FromyearList.lookupDesc} </hdiits:option>
	    			</c:forEach>
	   			</hdiits:select>
			</td>
			<td  align="left" width="25%" id="FromMonth1">
				For Month
			</td>
			<td  align="left" width="25%" id="FromMonth2">
				<hdiits:select name="selFromMonth" size="1" sort="false" id="cmbFromMonth" mandatory="true"> 
	   				<hdiits:option value="-1"> --Select-- </hdiits:option>
	 				<c:forEach items="${monthList}" var="FrommonthList">
 	     				<hdiits:option value="${FrommonthList.lookupShortName}"> ${FrommonthList.lookupDesc} </hdiits:option>
	    			</c:forEach>
	   			</hdiits:select>
			</td>
		</tr>
		<tr>
	 		<td class="Label" width="10%">
	 			Bill / Employee Group
			</td>
			<td colspan="8" class="Label" width="90%">
				<select style="width:450px" name="BillGrpCombo" id="BillGrpCombo" mandatory="true" validation="sel.isrequired" onchange="getEmployeeFromType()">
					<option value="-1" selected="selected">----------------------Select----------------------</option>
					<option value="1">----------------------All Employee--------------------</option>
					<c:forEach items="${billList}" var="billList">
						<option value="${billList.dcpsDdoBillGroupId}" title="${billList.dcpsDdoBillDescription}"><c:out value="${billList.dcpsDdoBillDescription}"> </c:out></option>
					</c:forEach>
				</select>
			</td>
		</tr>
	 	<tr>
	 		<td class="Label" width="10%">
	 			<b><hdiits:caption captionid="NG.PayItem" bundle="${enLables}"></hdiits:caption></b>
			</td>
			<td colspan="8"  class="Label" width="90%">
				<hdiits:select name="payItemCombo" id="payItemCombo" mandatory="true" style="width:210px" onchange="getEmployeeFromType();">
					<hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    	<c:forEach items="${nonGovDeducTypes}" var="deducTypes">
			    		<hdiits:option value="${deducTypes.deducCode}">${deducTypes.deducDisplayName}</hdiits:option>
			    	</c:forEach>
			    </hdiits:select>
			</td>
		</tr>
		<tr>
	 		<td class="Label" width="40%">
	 			<b><hdiits:caption captionid="NG.EmpDsgn" bundle="${enLables}"></hdiits:caption></b>
			</td>
			<td colspan="8" class="Label" width="60%">
				<hdiits:select name="EmpDsgnCombo" id="EmpDsgnCombo" style="width:210px" onchange="getEmployeeFromType()">
					<hdiits:option value="-1">-------Select------</hdiits:option>
						<c:forEach items="${dsgnList}" var="dsgnList">
							<hdiits:option value="${dsgnList.dsgnCode}">${dsgnList.dsgnName}</hdiits:option>
						</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		<tr>
	 		<td class="Label" >
	 			<b><hdiits:caption captionid="NG.AmtForManyEmp" bundle="${enLables}"></hdiits:caption></b>
	 			in <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)
			</td>
			<td >
				<hdiits:number name="amount" id="amount" caption="amount" maxlength="10" validation="txt.isrequired,txt.isnumber" 
				mandatory="false" />
			</td>
		</tr>
	 </table>
	 
	 <table width="80%" height="30%" align="center" cellpadding="0" cellspacing="0" style="border-collapse: separate;" >
	 <tr>
	 	<td height="30%" width="80%"  valign="top">
	 		<div style="height: 300px;width: 100%;overflow: auto;margin: 0px;padding: 0px;">
	 		<table style="width: 98%;border:single;overflow-y: auto;overflow-x: hidden; " border="1" bordercolor="black" align="center">
				<tr>
					<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold" width="35%" align="center"><b>Employee Name<b></th>
					<c:if test="${deducType==96}">
					<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold" width="35%" align="center"><b>Other Recovery Type<b></th>
					</c:if>
					<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold" width="20%" align="center"><b>Existing Amount <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></b></th>
					<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold" width="20%"  align="center"><b>New Amount <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></b></th>
		<!--			<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold" width="15%"  align="center"><b>Difference(Rs)</b></th>-->
					<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold;" width="5%" align="center">
							<b><font color="black">
							<hdiits:checkbox id="selcheckBoxAllowSelectAll"	name="selcheckBoxAllowSelectAll" value="1" onclick="selDeselectAllowances(this)"/> 
							</font></b>
					</th>
				</tr>
				<c:set var="srNoDeduction" value="0"></c:set>
				<c:set var="exstTotal" value="0"></c:set>
				<c:forEach var="row" items="${deducList}">
					<c:set var="counter" value="${counter+1}"></c:set>
					<tr>
						<td title="Employee Name">
							<input type="hidden" name="employeeId${counter}" id="employeeId${counter}" value="${row.employeeId}" />
							<c:out value="${row.employeeName}"></c:out>
						</td>
						<c:choose>
						<c:when test="${deducType==96}">
						<td title="Other Recovery Type">
							<input size="25%" type="text" name="nonGovTypeValueForHdn${counter}" id="nonGovTypeValueForHdn${counter}" value="${row.nonGovTypeValue}" style="text-align: right;" disabled="disabled" onblur="setOtherRecoveryType('${counter}');"/>
							<input type="hidden" name="nonGovTypeValue${counter}" id="nonGovTypeValue${counter}" value="${row.nonGovTypeValue}" />
							<input type="hidden" name="nonGovTypeValueOld${counter}" id="nonGovTypeValueOld${counter}" value="${row.nonGovTypeValue}" />
						</td>
						</c:when>
						<c:otherwise>
							<td title="Other Recovery Type" style="display: none">
							<input size="25%" type="text" name="nonGovTypeValue${counter}" id="nonGovTypeValue${counter}" value="${row.nonGovTypeValue}" style="text-align: right;"/>
						</td>
						</c:otherwise>
						</c:choose>
						<td title="Existing Amount">
							<input type="hidden" name="exisAmount${counter}" id="exisAmount${counter}" value="${row.amount}" />
							<input type="text" name="exisAmountTxt${counter}" style="text-align: right;" id="exisAmountTxt${counter}" value="${row.amount}" disabled="disabled"/>
							<c:set var="exstTotal" value="${exstTotal+row.amount}"></c:set>
	<!--					<c:out value="${row.amount}"></c:out>-->
						</td>
						<td title="New Amount">
							<input type="text" id="newAmount${counter}" style="text-align: right;" name="newAmount${counter}" value="${row.amount}" onblur="activateOtherRecoveryType('${counter}');" />
							<input type="hidden" name="InsertFlag${counter}" id="InsertFlag${counter}" value="${row.insertFlag}" />
							<hdiits:hidden default="${row.amount}" name="newAmountHdn${counter}" id="newAmountHdn${counter}" /> 
							<c:set var="newAmt" value="${row.amount}"></c:set>
						</td>
	<!--				<td title="Difference(Rs)">-->
	<!--					<input type="text" id="difference${counter}" style="text-align: right;"	name="difference${counter}" value="Diff" /> -->
	<!--				</td>-->
						<td title="Select" align="center">
							<hdiits:checkbox id="selcheckBoxAllow${counter}" name="selcheckBoxAllow" value="1" default="" readonly="" onclick="deselect();" />
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td title = "Total">
						<c:out value="Total"></c:out><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font>
					</td>
					<c:if test="${deducType==96}">
					<td>
						
					</td>
					</c:if>
					<td>
						<input type="text" id="exisAmountTotal" style="text-align: right;" name="exisAmountTotal" value="${exstTotal}" disabled="disabled" >
	<!--				<c:out value="${exstTotal}"></c:out>-->
					</td>
					<td>
						<input type="text" id="newAmountTotal" style="text-align: right;" name="newAmountTotal" value="${exstTotal}" disabled="disabled" >
	<!--				<c:out value="${exstTotal}"></c:out>-->
					</td>
				</tr>
			</table>
	 		</div>
	 	</td>
	 </tr>
	 </table>
	 	</div>
	<div align="center">
		<hdiits:button name="Save" style="text-align:center;" id="Save"	type="button" caption="Save" onclick="submitForm()" /> 
		<hdiits:button name="btnClose1" style="text-align:center;" type="button" caption="Close" onclick="onclosefunction()" />
	</div>

	<br/><br/>
</hdiits:form>
<script type="text/javascript">
selectionForm();
</script>
</body>

<%
}
  	  catch(Exception e)
  	  {
  		  //System.out.println("There is some error:-");
  		  e.printStackTrace();
  	  }
  	  %>
