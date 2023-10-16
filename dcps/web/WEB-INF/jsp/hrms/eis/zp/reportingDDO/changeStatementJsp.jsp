
<%
    try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="ReportingDDO" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="selMonth" value="${resValue.selMonth}"></c:set>
<c:set var="selYear" value="${resValue.selYear}"></c:set>
<c:set var="selddoCode" value="${resValue.selddoCode}"></c:set>
<c:set var="size" value="${resValue.size}"></c:set>

<c:set var="button" value="${resValue.button}"></c:set>
<c:set var="month" value="${resValue.month}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>
<c:set var="ddoDtls11" value="${resValue.ddoDtls}"></c:set>
<c:set var="MonthlookUpList" value="${resValue.MonthlookUpList}"></c:set>
<c:set var="YearLookUpList" value="${resValue.YearLookUpList}"></c:set>

<c:set var="changeStmtList" value="${resValue.changeStmtList}"></c:set>

<c:set var="msg" value="${resValue.msg}"></c:set>

<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

</head>

<hdiits:form name="frmReportingDDO" validate="true" method="post"
	action="ifms.htm?actionFlag=fwdApprChangeStmt">

	<hdiits:hidden name="seladminDept" id="seladminDept" default="2" />
	<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<li><a href="#" rel="tcontent1" class="selected"><b>Input Data</b></a></li>
</ul>
</div>
	
	<div id="tcontent1"
		style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

	<table width="80%">
		<tr>
			<td style="width: 5%"><b><hdiits:caption captionid="PR.MONTH"
				bundle="${ReportingDDO}" /></b></td>

			<td style="width: 20%"><select name="month" id="month" onchange="">
				<option value="-1"><fmt:message key="EIS.Select"
					bundle="${ReportingDDO}" /></option>
				<c:forEach var="months" items="${MonthlookUpList}">
					<c:choose>
						<c:when test="${selMonth==months.lookupShortName}">

							<option selected="selected"
								value="<c:out value="${months.lookupShortName}"  />"><c:out
								value="${months.lookupDesc}" /></option>
						</c:when>

						<c:otherwise>
							<option value="<c:out value="${months.lookupShortName}"  />">
							<c:out value="${months.lookupDesc}" /></option>

						</c:otherwise>

					</c:choose>
				</c:forEach>

			</select></td>

			<td style="width: 5%"><b><hdiits:caption captionid="PR.YEAR"
				bundle="${ReportingDDO}" /></b></td>
			<td style="width: 20%"><select name="year" id="year" onchange="">
				<option value="-1"><fmt:message key="EIS.Select"
					bundle="${ReportingDDO}" /></option>

				<c:forEach var="years" items="${YearLookUpList}">
					<c:choose>
						<c:when test="${selYear==years.lookupName}">
							<option selected="selected"
								value="<c:out value="${years.lookupName}"/>"><c:out
								value="${years.lookupDesc}" /></option>
						</c:when>
						<c:otherwise>
							<option value="<c:out value="${years.lookupName}"/>"><c:out
								value="${years.lookupDesc}" /></option>

						</c:otherwise>
					</c:choose>
				</c:forEach>

			</select></td>



			<td style="width: 10%"><b>DDO Code</b></td>
			<td style="width: 40%"><select name="ddoCode" id="ddoCode"  style="width: 80%"  >
				<option value="-1">--select----</option>

				<c:forEach var="ddoDtls11" items="${ddoDtls11}">
					<c:choose>
						<c:when test="${selddoCode==ddoDtls11[0]}">
							<option selected="selected" value="<c:out value="${ddoDtls11[0]}" />"><c:out
								value="${ddoDtls11[0]}(${ddoDtls11[1]})" /></option>
						</c:when>
						<c:otherwise>
							<option value="<c:out value="${ddoDtls11[0]}"/>"><c:out
								value="${ddoDtls11[0]}(${ddoDtls11[1]})" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</select></td>


		</tr>

	</table>

	<br>
	<br>
	<table align="center">
		<tr>
			<td align="center"><hdiits:submitbutton value="Search"
				id="search" name="search" />
		</tr>
	</table>
	<br>
	<input type="hidden" value="yes" id="flag" name="flag"> <hdiits:validate
		locale="${locale}" controlNames="" />
</hdiits:form>


<form name="frmDeletePaybill" method="post">
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<li><a href="#" rel="tcontent1" class="selected"><b>Change Statement</b></a></li>
</ul>
</div>

<div id="tcontent1"
	style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

<table width="100%" border="1">

	<tr>
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
		<b>Select</b></td>
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
		<b>Change Statement Bill No</b></td>
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Scheme
		Code</b></td>
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Scheme
		Name</b></td>
		
		<%--Added by saurabh--%>
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Sub Scheme
		Code</b></td>
		
		
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Bill Group Name</b></td>
				
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Gross
		Amount</b></td>
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Net
		Amount</b></td>
		
		<td align="center"
			style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold"><b>Remarks</b></td>
		
		
		
	</tr>
	<%
	    int i = 0;
	%>
	<c:forEach var="entry" items="${changeStmtList}">

		<tr>

			<td><%--
			<c:if test="${entry[4]== '1'}">

				<input type="radio" name="billno" id="billid<%=i%>"
					value="${entry[0]}" onclick="approvedBill(this);"
					disabled="disabled" />
			</c:if> 
			<c:if test="${entry[4]!= '1'}">
				<input type="radio" name="billno" id="billid<%=i%>"
					value="${entry[0]}" onclick="disableEnableBtn(${entry[4]});" />
			</c:if>
			--%> <input type="radio" name="billno" id="billid<%=i%>"
				value="${entry[7]}" /></td>

			<td align="center"><a href="javascript:void(0)" onclick="ShowChangeStatement(${entry[0]})">${entry[0]}</a>

			</td>

			<td align="center"><c:out value='${entry[5]}' /></td>
			<td align="center"><c:out value='${entry[4]}' /></td>
			<td align="center"><c:out value='${entry[3]}' /></td>
			
			<td align="center"><c:out value='${entry[1]}' /></td>
			<td align="center"><c:out value='${entry[2]}' /></td>
			<td align="center"><input type="text" id="remarks<%=i%>" name="remarks" value="${entry[6]}"></td>
			

		</tr>
		<%
		    i++;
		%>
		<c:set var="count" value="${count+1}"></c:set>
	</c:forEach>

</table>
<br>

<table align="center">
	<tr>

		 

		<td><input type="button" name="approve" class="buttontag"
			style="width: 100%" id="approve" type="button" value="Approve Change Statement"
			onclick="return approveChangeStmnt();" /></td>
			
					<td style="display: none"><input type="button" name="fwd" class="buttontag" 
			style="width: 100%" id="fwd" type="button" value="Forward Change Statement"
			onclick="return fwdChangeStmnt();" /></td>
		
		<td><input type="button" name="Reject" class="buttontag"
			style="width: 100%" id="Reject" type="button"
			value="Reject Change Statement" onclick="return rejectChangeStmnt();" /></td>

	

	</tr>
</table>


</div>

</form>



<%
    } catch (Exception e) {
				e.printStackTrace();
			}
%>

<script>
function approveChangeStmnt(){
	var len = '${size}';
	var billNo="";
	var isChecked = false;
	for (var i = 0; i < len; i++) {
		if (document.getElementById("billid"+i).checked) {
			isChecked = true;
			billNo=document.getElementById("billid"+i).value;
			break;
		}
	}
	if (!isChecked) {
		alert("Please select atleast one change statement.");
		return false;
	}
	else{
			var answer = confirm (" Are You sure You want to Approve selected change statement?");
			if(answer)
			{	
				//document.frmReportingDDO.fwdConsoBill.disabled=true;
				showProgressbar("Please wait...");
				document.frmReportingDDO.action="./hdiits.htm?actionFlag=ChangeStmntAction&action=8&billNo="+billNo;
				document.frmReportingDDO.submit();
			}
		}
}



function rejectChangeStmnt(){
	var len = '${size}';
	var billNo="";
	var isChecked = false;
	var remark;
	for (var i = 0; i < len; i++) {
		if (document.getElementById("billid"+i).checked) {
			isChecked = true;
			billNo=document.getElementById("billid"+i).value;
			remark=document.getElementById("remarks"+i).value;
			//alert(remark);
			break;
		}
	}
	if (!isChecked) {
		alert("Please select atleast one change statement.");
		return false;
	}
	else{
		//alert(remark);
		if(remark ==''){
			alert("Please fill the reason of rejection in remark field.");
			return false;
			}
			var answer = confirm (" Are You sure You want to Reject selected change statement?");
			if(answer)
			{	
				//document.frmReportingDDO.fwdConsoBill.disabled=true;
				showProgressbar("Please wait...");
				document.frmReportingDDO.action="./hdiits.htm?actionFlag=ChangeStmntAction&action=9&billNo="+billNo+"&remark="+remark;
				document.frmReportingDDO.submit();
			}
		}
}


function fwdChangeStmnt(){
	var len = '${size}';
var billNo="";
var isChecked = false;
for (var i = 0; i < len; i++) {
	if (document.getElementById("billid"+i).checked) {
		isChecked = true;
		billNo=document.getElementById("billid"+i).value;
		break;
	}
}
if (!isChecked) {
	alert("Please select atleast one change statement.");
	return false;
}
else{
		var answer = confirm (" Are You sure You want to Forward selected change statement?");
		if(answer)
		{	
			//document.frmReportingDDO.fwdConsoBill.disabled=true;
			showProgressbar("Please wait...");
			document.frmReportingDDO.action="./hdiits.htm?actionFlag=ChangeStmntAction&action=10&billNo="+billNo;
			document.frmReportingDDO.submit();
		}
	}
}


function ShowChangeStatement(billId)
{
 
	showProgressbar("Please wait...");
	var newWindow;
	// alert("hiii");
	var billtype = "2500337";
	//alert("hiii  241");
	var BillNo=billId;
	 
   	var height = screen.height - 100;
   	var width = screen.width;
   	var urlstring ;
//   	alert("hiii2414242");
 
   	urlstring = "hrms.htm?actionFlag=reportService&reportCode=8000199&action=generateReport&FromParaPage=TRUE&backBtn=0&billTypePara="+billtype+"&Month=${selMonth}&Year=${selYear}&Flag=YES&billNo="+BillNo;
 	//alert(urlstring);
 	 	 
   	document.frmReportingDDO.action = urlstring;
	document.frmReportingDDO.submit();
}


</script>


<script type="text/javascript">


var finalMsg = '${msg}';


if(finalMsg != null && finalMsg != '')
{	
	
	
	alert(finalMsg);
	document.frmReportingDDO.action="ifms.htm?actionFlag=fwdApprChangeStmt";
	document.frmReportingDDO.submit();
	//resetForm1();
}

</script>