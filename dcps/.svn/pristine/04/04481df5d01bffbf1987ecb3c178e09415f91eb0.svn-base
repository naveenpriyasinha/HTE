
<%
	try {
%>
<%@page import="java.util.List"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables"
	scope="page" />
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>

<c:set var="fixList" value="${resultValue.fixList}"></c:set>
<c:set var="fixListsize" value="${resultValue.fixListsize}"></c:set>
<c:set var="billList" value="${resultValue.billList}"></c:set>
<c:set var="payCommisionList" value="${resultValue.payCommisionList}"></c:set>

<c:set var="billNo" value="${resultValue.billNo}"></c:set>
<c:set var="payCommId" value="${resultValue.payCommId}"></c:set>
<c:set var="orderNo" value="${resultValue.orderNo}"></c:set>
<c:set var="orderDate" value="${resultValue.orderDate}"></c:set>

<c:set var="fixListWithGoUpdate"
	value="${resultValue.fixListWithGoUpdate}"></c:set>
<c:set var="fixListsizeWithGoUpdate"
	value="${resultValue.fixListsizeWithGoUpdate}"></c:set>

<c:set var="fixListWithGo" value="${resultValue.fixListWithGo}"></c:set>
<c:set var="fixListsizeWithGo" value="${resultValue.fixListsizeWithGo}"></c:set>
<c:set var="msg" value="${resultValue.msg}"></c:set>
<c:set var="save" value="${resultValue.save}"></c:set>


<%
	List dataList = (List) pageContext.getAttribute("fixList");
		int size = 0;
		if (dataList != null) {
			size = dataList.size();
		}
		pageContext.setAttribute("listSize", size);
%>

<script type="text/javascript">
	function showMsg() {
		//if("${allowedOrNot}" == "0")
		//{
		//	alert('Increment Drawal Screen is not available to you today. Please see notice dated 18/07/2014 displayed on login page of Sevaarth,BEAMS,Mahakosh.');
		//	self.location.href="ifms.htm?actionFlag=validateLogin";
		//	}

		if ("${msg}" != "") {
			alert('${msg}');
			document.EmpIncr.action = "./hrms.htm?actionFlag=getIncrementData";
			document.EmpIncr.submit();
		}

		if ("${billNo}" > 0) {
			document.getElementById('tab_new_order').style.display = "";
			document.getElementById('tab_new_bill_no').style.display = "";
			document.getElementById("incrementCertificateOrderNo").value = "${orderNo}";
			document.EmpIncr.orderDate.value = "${orderDate}";
			document.getElementById("billName").value = "${billNo}";
			document.getElementById("payCommisionList").value = "${payCommId}";

			document.getElementById("incrementCertificateOrderNo").disabled = true;
			document.EmpIncr.orderDate.disabled = true;
			document.getElementById("billName").disabled = true;
			document.getElementById("payCommisionList").disabled = true;

			if ("${save}" == 'view') {
				document.getElementById('goButton').style.display = "none";
				document.getElementById("billName").disabled = false;
				document.getElementById("payCommisionList").disabled = false;
			}

		}
	}

	function disableViewList() {
		document.getElementById('tab_new_order').style.display = "";
		document.getElementById('tab_new_bill_no').style.display = "";
		document.getElementById('goButton').style.display = "";
		document.getElementById("btnSearch").disabled = true;
		document.getElementById("VerifyOrder").disabled = true;
		//document.getElementById("btnViewAll").disabled=true;
		//document.EmpIncr.currentYearOrders.disabled=true;
		//document.EmpIncr.previousYearOrders.disabled=true;
		document.getElementById("addFromLeftToRight").disabled = true;
		document.getElementById("addFromRightToLeft").disabled = true;
		document.EmpIncr.savebutton.disabled = true;
		document.EmpIncr.cancel.disabled = true;
		document.getElementById("orderList").disabled = true;
	}

	function getListOfEmployeesWithGo() {

		if (document.getElementById("incrementCertificateOrderNo").value == "") {
			alert("Please Enter Order Number");
			document.EmpIncr.incrementCertificateOrderNo.focus();
			return false;
		}

		if (document.EmpIncr.orderDate.value == "") {
			alert("Order Date Should not be left blank !!!");
			document.EmpIncr.orderDate.focus();
			return false;

		}

		if (document.EmpIncr.orderDate.value != "") {
			//document.EmpIncr.go.disabled=true;
			order_dt_entered = document.EmpIncr.orderDate.value.split("/");
			var date = new Date();
			var curr_date = date.getDate();
			var curr_month = date.getMonth();
			var currYear = date.getFullYear();

			if (parseInt(order_dt_entered[2]) != parseInt(currYear)) {
				alert("Order Date should be of the current year !!!");
				document.EmpIncr.orderDate.value = '';
				document.EmpIncr.orderDate.focus();
				return false;
			}
		}

		if (document.getElementById("billName").value == -1) {
			alert("Please select bill name");
			return false;
		}
		if (document.getElementById("payCommisionList").value == -1) {
			alert("Please select Pay commission");
			return false;
		}

		if (document.EmpIncr.incrementCertificateOrderNo.value != ''
				&& document.EmpIncr.orderDate.value != '') {
			var orderNo = document
					.getElementById("incrementCertificateOrderNo").value;
			var orderdate = document.EmpIncr.orderDate.value;
			var billNo = document.getElementById("billName").value;
			var payCommission = document.getElementById("payCommisionList").value;
			var save = "N";
			document.EmpIncr.save.value = save;
			/* Added By Tejashree */
			if (payCommission == 2500347) {
				//alert("payCommission + >>>" + payCommission);
				//document.EmpIncr.action="hrms.htm?actionFlag=showPayIncDataForNewOrderNoForSevenPc&save="+save+"&orderNo="+orderNo+"&orderdate="+orderdate+"&billNo="+billNo+"&payCommId="+payCommission+"&returnvalue="+returnvalue;
				var url = document.EmpIncr.action = "hrms.htm?actionFlag=showPayIncDataForNewOrderNoForSevenPc&save="
						+ save
						+ "&orderNo="
						+ orderNo
						+ "&orderdate="
						+ orderdate
						+ "&billNo="
						+ billNo
						+ "&payCommId="
						+ payCommission;
				//alert("URL " + url);
				//alert("Done");
			} else {
				/* Ended By Tejashree */
			document.EmpIncr.action = "hrms.htm?actionFlag=showPayIncDataForNewOrderNo&save="
					+ save
					+ "&orderNo="
					+ orderNo
					+ "&orderdate="
					+ orderdate
					+ "&billNo=" + billNo + "&payCommId=" + payCommission;
			}
			document.EmpIncr.submit();
			showProgressbar("Please wait<br>Your Request is in progress...");
			return true;
		} else {
			return true;
		}
	}

	function fun_attach() {
		var chkBoxArr = document.getElementsByName("GroupCheck");
		var chkLength = chkBoxArr.length;

		for ( var i = 0; i < chkLength; i++) {
			if (chkBoxArr[i].checked) {
				AddRowInEmpListOfOrderTable();
				return true;
			}
		}
		alert("Please select an employee to attach to Bill group");
		return false;
	}

	function AddRowInEmpListOfOrderTable() {
		var color1 = "#F0F0F0";
		var color2 = "#7B68EE";

		var counterEmp = document.getElementById("counterEmp").value;
		var counterEmpOrder = document.getElementById("counterEmpOrder").value;
		var empListTable = document.getElementById("empListTable");
		var empListOfOrderTable = document
				.getElementById("empListOfOrderTable");

		var Cell1;
		var Cell2;
		var Cell3;
		var Cell4;
		var Cell5;
		var Cell6;
		var Cell7;
		var Cell8;
		var Cell9;
		var Cell10;
		var Cell11;

		var percent3 = 0;
		var basicTemp = document.createElement('input');
		var newBasic = "";
		var tempScaleEndAmt = document.createElement('input');
		var counter = 1;
		var roundUpSeven = 0;

		var empIdsToBeAddedToOrderTable = new Array();
		var empNamesToBeAddedToOrderTable = new Array();
		var empBasicToBeAddedToOrderTable = new Array();
		var empWEFToBeAddedToOrderTable = new Array();
		var empNextIncDateToBeAddedToOrderTable = new Array();
		var empRemarksToBeAddedToOrderTable = new Array();
		var empScaleEndAmtToBeAddedToOrderTable = new Array();
		var empIncOrderDateBackup = new Array();
		var selectedEmpIds = new Array();
		var totalSelectedEmpIds = 0;
		var empOrigBasicOrder = new Array();
		var empPayCommIdToOrderTable = new Array();
		var nxtIncrAmtToOrderTable = new Array();

		for ( var i = 1; i <= counterEmp; i++) {
			if (document.getElementById("GroupCheck" + i).checked) {
				basicTemp.value = document.getElementById("origBasic" + i).innerText;
				var oldBasic = basicTemp.value;
				tempScaleEndAmt.value = document.getElementById("scaleEndAmt"
						+ i).innerText;
				var scaleEndAmt = tempScaleEndAmt.value;
				var payCommId = document
						.getElementById("employeePayCommId" + i).innerText;

				if (payCommId == 2500341) {
					percent3 = eval((parseInt(oldBasic) / 100) * 3);

					newBasic = "";
					percent3 = parseInt(percent3);

					if ((percent3) % 10 != 0) {
						percent3 = eval(parseInt(percent3) / 10);
						percent3 = eval(parseInt(percent3) + 1);
						percent3 = eval(parseInt(percent3) * 10);

					}
					newBasic = Math
							.round((eval(percent3) + (parseInt(oldBasic))));
			
				} else if (payCommId == 2500347) {
					percent3 = eval((parseInt(oldBasic) / 100) * 3);
					//alert("alerttt " +percent3);
					newBasic = "";
					percent3 = parseInt(percent3);
					if ((percent3) % 100 != 0) {
						//alert("1111 " +percent3);
						var modseven = eval(parseInt(percent3) % 100);
						//alert("2222 "+modseven);
						if(modseven >= 50){
							percent3 = percent3 + 100 - modseven;
							//alert("3333 " +percent3);
							roundUpSeven = Math	.round((eval(percent3) + (parseInt(oldBasic))));
							//alert ("roundUpSeven 111"+ roundUpSeven);
						}
						else
						{
							    percent3 = percent3 - modseven;
							   // alert("4444 " +percent3);
								roundUpSeven = Math	.round((eval(percent3) + (parseInt(oldBasic))));
								//alert ("roundUpSeven 222"+ roundUpSeven);
						}
						/* alert("2222 " +percent3);
						percent3 = eval(parseInt(percent3) * 10);
						alert("3333 " +percent3); */
					}
					else {
						//alert("hjdsghgs");
						roundUpSeven = Math	.round((eval(percent3) + (parseInt(oldBasic))));
					}
					/* newBasic = Math
							.round((eval(percent3) + (parseInt(oldBasic)))); */
						newBasic = roundUpSeven;
					//alert("newBasic "+newBasic); 
				}
				else {
					var incrAmt = document.getElementById("empIncrAmt" + i).innerText;
					newBasic = Math
							.round((eval(incrAmt) + (parseInt(oldBasic))));
				}
				if(payCommId == 2500341){
					//alert("Paycommision " + payCommId);
				if (parseInt(newBasic) > parseInt(scaleEndAmt)) {
					alert('Pay in Pay band has exceeded the Upper limit of the Pay Band for some employees.');
					document.getElementById("GroupCheck" + i).checked = false;

					continue;
				} else {
					empIdsToBeAddedToOrderTable[counter] = document
							.createElement('input');
					empIdsToBeAddedToOrderTable[counter].value = document
							.getElementById("GroupCheck" + i).value;
					empNamesToBeAddedToOrderTable[counter] = document
							.createElement('input');
					empNamesToBeAddedToOrderTable[counter].value = document
							.getElementById("empName" + i).innerText;
					empBasicToBeAddedToOrderTable[counter] = document
							.createElement('input');
					empBasicToBeAddedToOrderTable[counter].value = newBasic;
					empIncOrderDateBackup[counter] = document
							.createElement('input');
					empIncOrderDateBackup[counter].value = document
							.getElementById("incOrderDate" + i).innerText;
					empWEFToBeAddedToOrderTable[counter] = document
							.createElement('input');
					var tempDate = document.getElementById("nextIncrDt" + i).innerText;
					var tempYear = tempDate.split("/")[2];
					tempYear = parseInt(tempYear) - 1;
					if (tempDate == "") {
						tempYear = getCurrentYear();
					}
					tempDate = "" + "01" + "/" + "07" + "/" + tempYear;
					empWEFToBeAddedToOrderTable[counter].value = tempDate;
					empNextIncDateToBeAddedToOrderTable[counter] = document
							.createElement('input');
					empNextIncDateToBeAddedToOrderTable[counter].value = document
							.getElementById("nextIncrDt" + i).innerText;
					if (empNextIncDateToBeAddedToOrderTable[counter].value == "") {
						var currYear = getCurrentYear();
						currYear += 1;
						empNextIncDateToBeAddedToOrderTable[counter].value = "01/07/"+ currYear;
					}
					empScaleEndAmtToBeAddedToOrderTable[counter] = document
							.createElement('input');
					empScaleEndAmtToBeAddedToOrderTable[counter].value = document
							.getElementById("scaleEndAmt" + i).innerText;
					empRemarksToBeAddedToOrderTable[counter] = document
							.createElement('input');
					empRemarksToBeAddedToOrderTable[counter].value = document
							.getElementById("remarks" + i).innerText;
					empOrigBasicOrder[counter] = document
							.createElement('input');
					empOrigBasicOrder[counter].value = document
							.getElementById("origBasic" + i).innerText;

					empPayCommIdToOrderTable[counter] = document
							.createElement('input');
					empPayCommIdToOrderTable[counter].value = document
							.getElementById("employeePayCommId" + i).innerText;

					nxtIncrAmtToOrderTable[counter] = document
							.createElement('input');
					nxtIncrAmtToOrderTable[counter].value = document
							.getElementById("empIncrAmt" + i).innerText;

					totalSelectedEmpIds = totalSelectedEmpIds + 1;
					selectedEmpIds[counter] = i;
					counter++;
				}
			 }
				else{
					empIdsToBeAddedToOrderTable[counter] = document
					.createElement('input');
			empIdsToBeAddedToOrderTable[counter].value = document
					.getElementById("GroupCheck" + i).value;
			empNamesToBeAddedToOrderTable[counter] = document
					.createElement('input');
			empNamesToBeAddedToOrderTable[counter].value = document
					.getElementById("empName" + i).innerText;
			empBasicToBeAddedToOrderTable[counter] = document
					.createElement('input');
			empBasicToBeAddedToOrderTable[counter].value = newBasic;
			empIncOrderDateBackup[counter] = document
					.createElement('input');
			empIncOrderDateBackup[counter].value = document
					.getElementById("incOrderDate" + i).innerText;
			empWEFToBeAddedToOrderTable[counter] = document
					.createElement('input');
			var tempDate = document.getElementById("nextIncrDt" + i).innerText;
			var tempYear = tempDate.split("/")[2];
			tempYear = parseInt(tempYear) - 1;
			if (tempDate == "") {
				tempYear = getCurrentYear();
			}
			tempDate = "" + "01" + "/" + "07" + "/"+tempYear;
			empWEFToBeAddedToOrderTable[counter].value = tempDate;
			empNextIncDateToBeAddedToOrderTable[counter] = document
					.createElement('input');
			empNextIncDateToBeAddedToOrderTable[counter].value = document
					.getElementById("nextIncrDt" + i).innerText;
			if (empNextIncDateToBeAddedToOrderTable[counter].value == "") {  
				var currYear = getCurrentYear();
				currYear += 1;
				empNextIncDateToBeAddedToOrderTable[counter].value = "01/07/"+currYear;
			}
			empScaleEndAmtToBeAddedToOrderTable[counter] = document
					.createElement('input');
			empScaleEndAmtToBeAddedToOrderTable[counter].value = document
					.getElementById("scaleEndAmt" + i).innerText;
			empRemarksToBeAddedToOrderTable[counter] = document
					.createElement('input');
			empRemarksToBeAddedToOrderTable[counter].value = document
					.getElementById("remarks" + i).innerText;
			empOrigBasicOrder[counter] = document
					.createElement('input');
			empOrigBasicOrder[counter].value = document
					.getElementById("origBasic" + i).innerText;
			empPayCommIdToOrderTable[counter] = document
					.createElement('input');
			empPayCommIdToOrderTable[counter].value = document
					.getElementById("employeePayCommId" + i).innerText;
			nxtIncrAmtToOrderTable[counter] = document
					.createElement('input');
			nxtIncrAmtToOrderTable[counter].value = document
					.getElementById("empIncrAmt" + i).innerText;
			totalSelectedEmpIds = totalSelectedEmpIds + 1;
			selectedEmpIds[counter] = i;
			counter++;
				}
				/* Ended By Tejashree */
			}
		}

		for (i = 1; i <= totalSelectedEmpIds; i++) {
			counterEmpOrder = Number(counterEmpOrder) + 1;
			var tempLength = empListOfOrderTable.rows.length;
			var newRow = empListOfOrderTable.insertRow(tempLength);
			newRow.style.backgroundColor = color1;
			newRow.style.borderColor = color2;
			Cell1 = newRow.insertCell(0);
			Cell2 = newRow.insertCell(1);
			Cell3 = newRow.insertCell(2);
			Cell4 = newRow.insertCell(3);
			Cell5 = newRow.insertCell(4);
			Cell6 = newRow.insertCell(5);
			Cell7 = newRow.insertCell(6);
			Cell8 = newRow.insertCell(7);
			Cell9 = newRow.insertCell(8);
			Cell10 = newRow.insertCell(9);
			Cell11 = newRow.insertCell(10);

			Cell1.align = "center";
			Cell2.align = "center";
			Cell3.align = "center";
			Cell4.align = "center";
			Cell5.align = "center";
			Cell6.align = "center";
			Cell7.align = "center";

			Cell1.innerHTML = '<input type="checkbox" name="GroupCheckOrder" id="GroupCheckOrder'
					+ counterEmpOrder
					+ '" value="'
					+ empIdsToBeAddedToOrderTable[i].value + '" />';
			Cell2.innerHTML = '<label id="empNameOrder' + counterEmpOrder
					+ '">' + empNamesToBeAddedToOrderTable[i].value
					+ '</label>';
			Cell3.innerHTML = '<label id="empBasicOrder' + counterEmpOrder
					+ '">' + empBasicToBeAddedToOrderTable[i].value
					+ '</label>';
			var WEF = document.createElement('input');
			WEF.type = 'text';
			WEF.size = '8';
			WEF.id = 'empWEFOrder' + counterEmpOrder;
			WEF.name = 'empWEFOrder' + counterEmpOrder;
			WEF.readOnly = "";
			WEF.value = empWEFToBeAddedToOrderTable[i].value;
			Cell4.appendChild(WEF);
			Cell5.innerHTML = '<label id="nextIncrDtOrder' + counterEmpOrder
					+ '">' + empNextIncDateToBeAddedToOrderTable[i].value
					+ '</label>';
			var remarks = document.createElement('input');
			remarks.type = 'text';
			remarks.id = 'remarksOrder' + counterEmpOrder;
			remarks.name = 'remarksOrder' + counterEmpOrder;
			remarks.size = '8';
			remarks.readOnly = "";
			remarks.value = "Enter Remarks";
			Cell6.appendChild(remarks);
			Cell7.innerHTML = '<label id="scaleEndAmtOrder' + counterEmpOrder
					+ '" style="display:none">'
					+ empScaleEndAmtToBeAddedToOrderTable[i].value + '</label>';
			Cell8.innerHTML = '<label id="IncOrderDateOrder' + counterEmpOrder
					+ '" style="display:none" >'
					+ empIncOrderDateBackup[i].value + '</label>';
			Cell9.innerHTML = '<label id="origBasicOrder' + counterEmpOrder
					+ '" style="display:none" >' + empOrigBasicOrder[i].value
					+ '</label>';

			Cell10.innerHTML = '<label id="employeePayCommIdOrder'
					+ counterEmpOrder + '" style="display:none" >'
					+ empPayCommIdToOrderTable[i].value + '</label>';
			Cell11.innerHTML = '<label id="empIncrAmtOrder' + counterEmpOrder
					+ '" style="display:none" >'
					+ nxtIncrAmtToOrderTable[i].value + '</label>';

			document.getElementById("counterEmpOrder").value = Number(document
					.getElementById("counterEmpOrder").value) + 1;

			document.getElementById("empIdstoBeAttached").value = document
					.getElementById("empIdstoBeAttached").value
					+ empIdsToBeAddedToOrderTable[i].value + "~";

			document.getElementById("empIdstoBeDetached").value = document
					.getElementById("empIdstoBeDetached").value.replace(
					empIdsToBeAddedToOrderTable[i].value + "~", "");
			document.getElementById("empBasicSalarytoBeAttached").value = document
					.getElementById("empBasicSalarytoBeAttached").value
					+ empBasicToBeAddedToOrderTable[i].value + "~";
			document.getElementById("empBasicSalarytoBeDetached").value = document
					.getElementById("empBasicSalarytoBeDetached").value
					.replace(empBasicToBeAddedToOrderTable[i].value + "~", "");
			document.getElementById("empWEFtoBeAttached").value = document
					.getElementById("empWEFtoBeAttached").value
					+ empWEFToBeAddedToOrderTable[i].value + "~";
			document.getElementById("empWEFtoBeDetached").value = document
					.getElementById("empWEFtoBeDetached").value.replace(
					empWEFToBeAddedToOrderTable[i].value + "~", "");
			document.getElementById("empNextIncrDatetoBeAttached").value = document
					.getElementById("empNextIncrDatetoBeAttached").value
					+ empNextIncDateToBeAddedToOrderTable[i].value + "~";
			document.getElementById("empNextIncrDatetoBeDetached").value = document
					.getElementById("empNextIncrDatetoBeDetached").value
					.replace(
							empNextIncDateToBeAddedToOrderTable[i].value + "~",
							"");
			document.getElementById("empRemarkstoBeAttached").value = document
					.getElementById("empRemarkstoBeAttached").value
					+ empRemarksToBeAddedToOrderTable[i].value + "~";
			document.getElementById("empRemarkstoBeDetached").value = document
					.getElementById("empRemarkstoBeDetached").value.replace(
					empRemarksToBeAddedToOrderTable[i].value + "~", "");
			document.getElementById("empIncOrderDatetoBeAttached").value = document
					.getElementById("empIncOrderDatetoBeAttached").value
					+ empIncOrderDateBackup[i].value + "~";
			document.getElementById("empIncOrderDatetoBeDetached").value = document
					.getElementById("empIncOrderDatetoBeDetached").value
					.replace(empIncOrderDateBackup[i].value + "~", "");
			document.getElementById("origBasictoBeAttached").value = document
					.getElementById("origBasictoBeAttached").value
					+ empOrigBasicOrder[i].value + "~";
			document.getElementById("origBasictoBeDetached").value = document
					.getElementById("origBasictoBeDetached").value.replace(
					empOrigBasicOrder[i].value + "~", "");
			document.getElementById("empPayCommissionId").value = document
					.getElementById("empPayCommissionId").value
					+ empIdsToBeAddedToOrderTable[i].value
					+ "~"
					+ empPayCommIdToOrderTable[i].value + "~";
		}
		for (i = counterEmp; i >= 1; i--) {
			if (document.getElementById("GroupCheck" + i).checked) {
				empListTable.rows[eval(i)].style.display = 'none';
				document.getElementById("GroupCheck" + i).checked = false;
			}
		}
	}

	function fun_detach() {

		var chkBoxArr = document.getElementsByName("GroupCheckOrder");
		var chkLength = chkBoxArr.length;
		for ( var i = 0; i < chkLength; i++) {
			if (chkBoxArr[i].checked) {
				AddRowInEmpListTable();
				return true;
			}
		}
		alert("Please select an employee to attach to Bill group");
		return false;

	}

	function AddRowInEmpListTable() {
		var color1 = "#F0F0F0";
		var color2 = "#7B68EE";

		var counterEmp = document.getElementById("counterEmp").value;
		var counterEmpOrder = document.getElementById("counterEmpOrder").value;

		var empIdsToBeAddedToEmpTable = new Array();
		var empNamesToBeAddedToEmpTable = new Array();
		var empBasicToBeAddedToEmpTable = new Array();
		var empWEFToBeAddedToEmpTable = new Array();
		var empScaleEndAmtToBeAddedToEmpTable = new Array();
		var empNextIncDateToBeAddedToEmpTable = new Array();
		var empRemarksToBeAddedToEmpTable = new Array();
		var empIncOrderDateBackupOrder = new Array();
		var empOrigBasic = new Array();
		var empPayCommId = new Array();
		var empNxtIncrAmt = new Array();

		var selectedEmpIds = new Array();
		var totalSelectedEmpIds = 0;
		var newRow;
		var Cell0;
		var Cell1;
		var Cell2;
		var Cell3;
		var Cell4;
		var Cell5;
		var Cell6;
		var Cell7;
		var Cell8;
		var Cell9;
		var Cell10;
		var percent3 = 0;
		var newBasic;
		var tempScaleEndAmt = document.createElement('input');
		var counter = 1;
		var basicTemp = document.createElement('input');
		;

		var empListTable = document.getElementById("empListTable");
		var empListOfOrderTable = document
				.getElementById("empListOfOrderTable");

		for ( var i = 1; i <= counterEmpOrder; i++) {
			if (document.getElementById("GroupCheckOrder" + i).checked) {
				basicTemp.value = document.getElementById("origBasicOrder" + i).innerText;
				var oldBasic = basicTemp.value;
				tempScaleEndAmt.value = document
						.getElementById("scaleEndAmtOrder" + i).innerText;
				var scaleEndAmt = tempScaleEndAmt.value;
				newBasic = parseInt(oldBasic);

				empIdsToBeAddedToEmpTable[counter] = document
						.createElement('input');
				empIdsToBeAddedToEmpTable[counter].value = document
						.getElementById("GroupCheckOrder" + i).value;

				empNamesToBeAddedToEmpTable[counter] = document
						.createElement('input');
				empNamesToBeAddedToEmpTable[counter].value = document
						.getElementById("empNameOrder" + i).innerText;

				empBasicToBeAddedToEmpTable[counter] = document
						.createElement('input');
				empBasicToBeAddedToEmpTable[counter].value = document
						.getElementById("empBasicOrder" + i).innerText;

				empWEFToBeAddedToEmpTable[counter] = document
						.createElement('input');
				empWEFToBeAddedToEmpTable[counter].value = document
						.getElementById("empWEFOrder" + i).value;

				empNextIncDateToBeAddedToEmpTable[counter] = document
						.createElement('input');
				empNextIncDateToBeAddedToEmpTable[counter].value = document
						.getElementById("nextIncrDtOrder" + i).innerText;

				empScaleEndAmtToBeAddedToEmpTable[counter] = document
						.createElement('input');
				empScaleEndAmtToBeAddedToEmpTable[counter].value = document
						.getElementById("scaleEndAmtOrder" + i).innerText;

				empRemarksToBeAddedToEmpTable[counter] = document
						.createElement('input');
				empRemarksToBeAddedToEmpTable[counter].value = document
						.getElementById("remarksOrder" + i).innerText;

				empIncOrderDateBackupOrder[counter] = document
						.createElement('input');
				empIncOrderDateBackupOrder[counter].value = document
						.getElementById("IncOrderDateOrder" + i).innerText;

				empOrigBasic[counter] = document.createElement('input');
				empOrigBasic[counter].value = document
						.getElementById("origBasicOrder" + i).innerText;

				empPayCommId[counter] = document.createElement('input');
				empPayCommId[counter].value = document
						.getElementById("employeePayCommIdOrder" + i).innerText;

				empNxtIncrAmt[counter] = document.createElement('input');
				empNxtIncrAmt[counter].value = document
						.getElementById("empIncrAmtOrder" + i).innerText;

				totalSelectedEmpIds = totalSelectedEmpIds + 1;

				selectedEmpIds[counter] = i;
				counter++;
			}
		}

		for (i = 1; i <= totalSelectedEmpIds; i++) {
			counterEmp = Number(counterEmp) + 1;
			newRow = empListTable.insertRow(empListTable.rows.length);
			newRow.style.backgroundColor = color1;
			newRow.style.borderColor = color2;

			Cell1 = newRow.insertCell(-1);
			Cell7 = newRow.insertCell(-1);
			Cell2 = newRow.insertCell(-1);
			Cell3 = newRow.insertCell(-1);
			Cell4 = newRow.insertCell(-1);
			Cell5 = newRow.insertCell(-1);
			Cell6 = newRow.insertCell(-1);
			Cell0 = newRow.insertCell(-1);
			Cell8 = newRow.insertCell(-1);
			Cell9 = newRow.insertCell(-1);
			Cell10 = newRow.insertCell(-1);

			Cell0.align = "center";
			Cell1.align = "center";
			Cell2.align = "center";
			Cell3.align = "center";
			Cell4.align = "center";
			Cell5.align = "center";
			Cell6.align = "center";
			Cell7.align = "center";
			Cell8.align = "center";
			Cell9.align = "center";
			Cell10.align = "center";

			Cell1.innerHTML = '<input type="checkbox" name="GroupCheck" id="GroupCheck'
					+ counterEmp
					+ '" value="'
					+ empIdsToBeAddedToEmpTable[i].value + '" />';
			Cell7.innerHTML = '<label id="userId' + counterEmp + '">'
					+ empIdsToBeAddedToEmpTable[i].value + '</label>';
			Cell2.innerHTML = '<label id="empName' + counterEmp + '">'
					+ empNamesToBeAddedToEmpTable[i].value + '</label>';
			Cell3.innerHTML = '<label id="empBasic' + counterEmp + '">'
					+ empOrigBasic[i].value + '</label>';
			Cell4.innerHTML = '<label id="incOrderDate' + counterEmp + '">'
					+ empIncOrderDateBackupOrder[i].value + '</label>';
			Cell5.innerHTML = '<label id="nextIncrDt' + counterEmp
					+ '" style="display:none" >'
					+ empNextIncDateToBeAddedToEmpTable[i].value + '</label>';
			Cell6.innerHTML = '<label id="scaleEndAmt' + counterEmp
					+ '" style="display:none" >'
					+ empScaleEndAmtToBeAddedToEmpTable[i].value + '</label>';
			Cell0.innerHTML = '<label id="remarks' + counterEmp + '">'
					+ empRemarksToBeAddedToEmpTable[i].value + '</label>';
			Cell8.innerHTML = '<label id="origBasic' + counterEmp
					+ '" style="display:none">' + empOrigBasic[i].value
					+ '</label>';

			Cell9.innerHTML = '<label id="employeePayCommId' + counterEmp
					+ '" style="display:none">' + empPayCommId[i].value
					+ '</label>';
			Cell10.innerHTML = '<label id="empIncrAmt' + counterEmp
					+ '" style="display:none">' + empNxtIncrAmt[i].value
					+ '</label>';

			document.getElementById("counterEmp").value = Number(document
					.getElementById("counterEmp").value) + 1;

			document.getElementById("empIdstoBeDetached").value = document
					.getElementById("empIdstoBeDetached").value
					+ empIdsToBeAddedToEmpTable[i].value + "~";

			document.getElementById("empIdstoBeAttached").value = document
					.getElementById("empIdstoBeAttached").value.replace(
					empIdsToBeAddedToEmpTable[i].value + "~", "");

			document.getElementById("empBasicSalarytoBeDetached").value = document
					.getElementById("empBasicSalarytoBeDetached").value
					+ empBasicToBeAddedToEmpTable[i].value + "~";

			document.getElementById("empBasicSalarytoBeAttached").value = document
					.getElementById("empBasicSalarytoBeAttached").value
					.replace(empBasicToBeAddedToEmpTable[i].value + "~", "");

			document.getElementById("empWEFtoBeDetached").value = document
					.getElementById("empWEFtoBeDetached").value
					+ empWEFToBeAddedToEmpTable[i].value + "~";
			document.getElementById("empWEFtoBeAttached").value = document
					.getElementById("empWEFtoBeAttached").value.replace(
					empWEFToBeAddedToEmpTable[i].value + "~", "");

			document.getElementById("empNextIncrDatetoBeDetached").value = document
					.getElementById("empNextIncrDatetoBeDetached").value
					+ empNextIncDateToBeAddedToEmpTable[i].value + "~";
			document.getElementById("empNextIncrDatetoBeAttached").value = document
					.getElementById("empNextIncrDatetoBeAttached").value
					.replace(empNextIncDateToBeAddedToEmpTable[i].value + "~",
							"");

			document.getElementById("empRemarkstoBeDetached").value = document
					.getElementById("empRemarkstoBeDetached").value
					+ empRemarksToBeAddedToEmpTable[i].value + "~";
			document.getElementById("empRemarkstoBeAttached").value = document
					.getElementById("empRemarkstoBeAttached").value.replace(
					empRemarksToBeAddedToEmpTable[i].value + "~", "");

			document.getElementById("empIncOrderDatetoBeDetached").value = document
					.getElementById("empIncOrderDatetoBeDetached").value
					+ empIncOrderDateBackupOrder[i].value + "~";
			document.getElementById("empIncOrderDatetoBeAttached").value = document
					.getElementById("empIncOrderDatetoBeAttached").value
					.replace(empIncOrderDateBackupOrder[i].value + "~", "");

			document.getElementById("origBasictoBeDetached").value = document
					.getElementById("origBasictoBeDetached").value
					+ empOrigBasic[i].value + "~";
			document.getElementById("origBasictoBeAttached").value = document
					.getElementById("origBasictoBeAttached").value.replace(
					empOrigBasic[i].value + "~", "");

		}

		for (i = counterEmpOrder; i >= 1; i--) {
			if (document.getElementById("GroupCheckOrder" + i).checked) {
				document.getElementById("GroupCheckOrder" + i).checked = false;

				if (document.getElementById("sizeOfOrderList").value > 0) {
					empListOfOrderTable.rows[eval(i)].style.display = 'none';
				} else {
					empListOfOrderTable.rows[eval(i) + 1].style.display = 'none';
				}
			}
		}
	}

	function saveData() {
		if (document.getElementById("empIdstoBeDetached").value == ""
				&& document.getElementById("empIdstoBeAttached").value == "") {
			alert("No data is Saved, as no change has been made");
			return;
		}

		var empIdstoBeDetached = document.getElementById("empIdstoBeDetached").value;

		var empIdstoBeAttached = document.getElementById("empIdstoBeAttached").value;

		var empBasicSalarytoBeAttached = document
				.getElementById("empBasicSalarytoBeAttached").value;

		var empInputTagElements = new Array();
		var empNextIncrDatetoBeAttached = document
				.getElementById("empNextIncrDatetoBeAttached").value;
		var empRemarkstoBeAttached = document
				.getElementById("empRemarkstoBeAttached").value;
		var empIncOrderDatetoBeAttached = document
				.getElementById("empIncOrderDatetoBeAttached").value;
		var empWEFtoBeAttached = document.getElementById("empWEFtoBeAttached").value;
		var empOrigBasictoBeAttached = document
				.getElementById("origBasictoBeAttached").value;

		var orderDate = document.getElementById('incrementorderDate').value;
		var orderNo = document.getElementById('incrementOrderNo').value;
		var tempDay;
		var tempMonth;
		var tempYear;
		var stdDay = "01";
		var stdMonth = "07";

		var counterEmpOrder1 = document.getElementById("counterEmpOrder").value;
		var empListOfOrderTable = document
				.getElementById("empListOfOrderTable");

		var save = document.getElementById("save").value;
		var WEFFlag = false;
		var currYear;

		var elements = empListOfOrderTable.getElementsByTagName("input");

		var j = 0;
		save = "view";

		if (save == 'N') {
			for ( var i = 0; i < elements.length; i += 3) {
				empInputTagElements[j] = document.createElement('input');
				empInputTagElements[j].value = elements[i].value;
				j += 1;

				empInputTagElements[j] = document.createElement('input');
				empInputTagElements[j].value = elements[i + 1].value;
				j += 1;

				empInputTagElements[j] = document.createElement('input');

				tempDay = elements[i + 1].value.split("/")[0];
				tempMonth = elements[i + 1].value.split("/")[1];
				tempYear = elements[i + 1].value.split("/")[2];
				currYear = getCurrentYear();

				if (tempDay == stdDay && tempMonth == stdMonth
						&& tempYear == currYear) {
					WEFFlag = true;
				} else {
					if (elements[i + 2].value == "") {
						WEFFlag = false;
						break;
					} else
						WEFFlag = true;
				}

				if (elements[i + 2].value != ""
						|| elements[i + 2].value == "Enter Remarks")
					empInputTagElements[j].value = elements[i + 2].value;
				else
					empInputTagElements[j].value = "Enter Remarks";
				j += 1;
			}
		}

		if (save == 'view') {
			j = 0;
			for (i = 0; i < elements.length; i += 3) {
				empInputTagElements[j] = document.createElement('input');
				empInputTagElements[j].value = elements[i].value;
				j += 1;

				empInputTagElements[j] = document.createElement('input');
				empInputTagElements[j].value = elements[i + 1].value;
				j += 1;

				tempDay = elements[i + 1].value.split("/")[0];
				tempMonth = elements[i + 1].value.split("/")[1];
				tempYear = elements[i + 1].value.split("/")[2];
				currYear = getCurrentYear();

				if (tempDay == stdDay && tempMonth == stdMonth
						&& tempYear == currYear) {
					WEFFlag = true;
				} else {
					if (elements[i + 2].value == ""
							|| elements[i + 2].value == "Enter Remarks") {
						WEFFlag = false;
						break;
					} else
						WEFFlag = true;
				}

				empInputTagElements[j] = document.createElement('input');
				if (elements[i + 2].value != "")
					empInputTagElements[j].value = elements[i + 2].value;
				else
					empInputTagElements[j].value = "Enter Remarks";
				j += 1;

			}
		}
		document.getElementById("inputTagElements").value = "";
		for (i = 0; i < j; i++) {
			document.getElementById("inputTagElements").value = document
					.getElementById("inputTagElements").value
					+ empInputTagElements[i].value + "~";
		}
		var inputTagElements = document.getElementById("inputTagElements").value;

		save = "Yes";

		if (WEFFlag == true) {
			document.EmpIncr.savebutton.disabled = true;
			var payCommId = document.getElementById("payCommisionList").value;
			url = "hrms.htm?actionFlag=showPayIncDataForNewOrderNo&orderNo="
					+ orderNo + "&orderdate=" + orderDate + "&save=" + save
					+ "&payCommId=" + payCommId;
			document.EmpIncr.save.value = save;
			document.EmpIncr.action = url;
			document.EmpIncr.submit();
		} else
			alert('WEF Date has been changed, So Kindly Insert WEF in proper format OR Enter Remarks at appropriate Place');

	}

	/* Added By Tejashree */
	function saveDataForSevenPC()
	{
		if (document.getElementById("empIdstoBeDetached").value == ""
			&& document.getElementById("empIdstoBeAttached").value == "") {
		alert("No data is Saved, as no change has been made");
		return;
	}
	var empIdstoBeDetached = document.getElementById("empIdstoBeDetached").value;
	var empIdstoBeAttached = document.getElementById("empIdstoBeAttached").value;
	var empBasicSalarytoBeAttached = document
			.getElementById("empBasicSalarytoBeAttached").value;
	var empInputTagElements = new Array();
	var empNextIncrDatetoBeAttached = document
			.getElementById("empNextIncrDatetoBeAttached").value;
	var empRemarkstoBeAttached = document
			.getElementById("empRemarkstoBeAttached").value;
	var empIncOrderDatetoBeAttached = document
			.getElementById("empIncOrderDatetoBeAttached").value;
	var empWEFtoBeAttached = document.getElementById("empWEFtoBeAttached").value;
	var empOrigBasictoBeAttached = document
			.getElementById("origBasictoBeAttached").value;
	var orderDate = document.getElementById('incrementorderDate').value;
	//alert("orderDate ==== "+orderDate);
	var orderNo = document.getElementById('incrementOrderNo').value;
	var tempDay;
	var tempMonth;
	var tempYear;
	var stdDay = "01";
	var stdMonth = "07";
	var counterEmpOrder1 = document.getElementById("counterEmpOrder").value;
	var empListOfOrderTable = document
			.getElementById("empListOfOrderTable");
	var save = document.getElementById("save").value;
	var WEFFlag = false;
	var currYear;
	var elements = empListOfOrderTable.getElementsByTagName("input");
	var j = 0;
	save = "view";
	if (save == 'N') {
		for (var i = 0; i < elements.length; i += 3) {
			empInputTagElements[j] = document.createElement('input');
			empInputTagElements[j].value = elements[i].value;
			j += 1;
			empInputTagElements[j] = document.createElement('input');
			empInputTagElements[j].value = elements[i + 1].value;
			j += 1;
			empInputTagElements[j] = document.createElement('input');
			tempDay = elements[i + 1].value.split("/")[0];
			tempMonth = elements[i + 1].value.split("/")[1];
			tempYear = elements[i + 1].value.split("/")[2];
			currYear = getCurrentYear();
			if (tempDay == stdDay && tempMonth == stdMonth
					&& tempYear == currYear) {
				WEFFlag = true;
			} else {
				if (elements[i + 2].value == "") {
					WEFFlag = false;
					break;
				} else
					WEFFlag = true;
			}
			if (elements[i + 2].value != ""
					|| elements[i + 2].value == "Enter Remarks")
				empInputTagElements[j].value = elements[i + 2].value;
			else
				empInputTagElements[j].value = "Enter Remarks";
			j += 1;
		}
	}
	if (save == 'view') {
		j = 0;
		for (i = 0; i < elements.length; i += 3) {
			empInputTagElements[j] = document.createElement('input');
			empInputTagElements[j].value = elements[i].value;
			j += 1;
			empInputTagElements[j] = document.createElement('input');
			empInputTagElements[j].value = elements[i + 1].value;
			j += 1;
			tempDay = elements[i + 1].value.split("/")[0];
			tempMonth = elements[i + 1].value.split("/")[1];
			tempYear = elements[i + 1].value.split("/")[2];
			currYear = getCurrentYear();
			if (tempDay == stdDay && tempMonth == stdMonth
					&& tempYear == currYear) {
				WEFFlag = true;
			} else {
				if (elements[i + 2].value == ""
						|| elements[i + 2].value == "Enter Remarks") {
					WEFFlag = false;
					break;
				} else
					WEFFlag = true;
			}
			empInputTagElements[j] = document.createElement('input');
			if (elements[i + 2].value != "")
				empInputTagElements[j].value = elements[i + 2].value;
			else
				empInputTagElements[j].value = "Enter Remarks";
			j += 1;
		}
	}
	document.getElementById("inputTagElements").value = "";
	for (i = 0; i < j; i++) {
		document.getElementById("inputTagElements").value = document
				.getElementById("inputTagElements").value
				+ empInputTagElements[i].value + "~";
	}
	var inputTagElements = document.getElementById("inputTagElements").value;
	save = "Yes";
	if (WEFFlag == true) {
		document.EmpIncr.savebutton.disabled = true;
		var payCommId = document.getElementById("payCommisionList").value;
		url = "hrms.htm?actionFlag=showPayIncDataForNewOrderNoForSevenPc&orderNo="
				+ orderNo + "&orderdate=" + orderDate + "&save=" + save
				+ "&payCommId=" + payCommId;
		document.EmpIncr.save.value = save;
		document.EmpIncr.action = url;
		document.EmpIncr.submit();
	} else
		alert('WEF Date has been changed, So Kindly Insert WEF in proper format OR Enter Remarks at appropriate Place');
	}
	/* Ended By Tejashree */
	function getCurrentYear() {
		var tempDate = new Date();
		var tempYear = tempDate.getFullYear();
		return tempYear;
	}

	function getCurrentMonth() {
		var tempDate = new Date();
		var tempMon = tempDate.getMonth();
		return tempMon;
	}

	function getOrderNo() {
		var length = '${listSize}';
		var orderNo = 0;
		var check = true;
		if (length.value == 0) {
			return false;
		}
		for ( var i = 1; i <= length; i++) {
			if (document.getElementById("radioId" + i).checked) {
				check = false;
				orderNo = document.getElementById("tnrOrderNoId" + i).value;
				break;
			}
		}
		if (check) {
			alert("Please Select The Radio Button");
			return false;
		}
		return orderNo;
	}
	function verifyOrder() {
		var ans = confirm("After Verification, you can not add / remove any employee in / from this order. Are you sure you want to Verify?");
		if (ans) {
			orderNo = getOrderNo();
			document.EmpIncr.save.value = "Verify";
			url = "./hrms.htm?actionFlag=showPayIncDataForNewOrderNo&orderNo="
					+ orderNo + "&save=Verify";
			document.EmpIncr.action = url;
			document.EmpIncr.submit();
			showProgressbar("Please wait...");
		}
	}

	function viewSelectedOrder(orderNo, orderDate) {
		document.getElementById('tab_new_bill_no').style.display = "";
		document.getElementById("addFromLeftToRight").disabled = true;
		document.getElementById("addFromRightToLeft").disabled = true;

		document.getElementById("btnSearch").disabled = true;
		document.getElementById("VerifyOrder").disabled = true;
		document.getElementById("savebutton").disabled = true;
		document.getElementById("cancel").disabled = true;

		document.getElementById("goButton").style.display = "none";
		if (document.getElementById("billName").value == -1) {
			alert("Please select bill name then select order name");
			return;
		} else if (document.getElementById("payCommisionList").value == -1) {
			alert("Please select Pay commission then select order name");
			return;
		} else {
			var billNo = document.getElementById("billName").value;
			var payCommId = document.getElementById("payCommisionList").value;
			document.EmpIncr.save.value = "view";

			if (payCommId == 2500347)
				url = "./hrms.htm?actionFlag=showPayIncDataForNewOrderNoForSevenPc&orderNo="
						+ orderNo
						+ "&orderdate="
						+ orderDate
						+ "&save=view&billNo="
						+ billNo
						+ "&payCommId="
						+ payCommId;
			else
			url = "./hrms.htm?actionFlag=showPayIncDataForNewOrderNo&orderNo="
					+ orderNo + "&orderdate=" + orderDate
					+ "&save=view&billNo=" + billNo + "&payCommId=" + payCommId;
			document.EmpIncr.action = url;
			document.EmpIncr.submit();
			showProgressbar("Please wait...");
		}
	}

	function fun_cancel() {
		window.location = "hrms.htm?actionFlag=validateLogin";
	}

	function getHistory(l) {
		var urlstyle = 'height=1800,width=800,toolbar=no,minimize=no,resizable=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=20,left=200';
		var url = './hdiits.htm?actionFlag=getIncPrintReport&OrderNo=' + l;
		window.open(url, "", urlstyle);
	}
</script>
<body>
	<hdiits:form name="EmpIncr" validate="true" method="POST"
		encType="multipart/form-data">
		<table width="100%">
			<tr>
				<td><input type="hidden" id="empIdstoBeDetached"
					name="empIdstoBeDetached" value="" size="100"></td>
				<td><input type="hidden" id="empIdstoBeAttached"
					name="empIdstoBeAttached" value="" size="100"></td>
				<td><input type="hidden" id="empBasicSalarytoBeAttached"
					name="empBasicSalarytoBeAttached" value="" size="100"></td>
				<td><input type="hidden" id="empBasicSalarytoBeDetached"
					name="empBasicSalarytoBeDetached" value="" size="100"></td>
				<td><input type="hidden" id="empWEFtoBeAttached"
					name="empWEFtoBeAttached" value="" size="100"></td>
				<td><input type="hidden" id="empWEFtoBeDetached"
					name="empWEFtoBeDetached" value="" size="100"></td>
				<td><input type="hidden" id="empNextIncrDatetoBeAttached"
					name="empNextIncrDatetoBeAttached" value="" size="100"></td>
				<td><input type="hidden" id="empNextIncrDatetoBeDetached"
					name="empNextIncrDatetoBeDetached" value="" size="100"></td>
				<td><input type="hidden" id="empRemarkstoBeAttached"
					name="empRemarkstoBeAttached" value="" size="100"></td>
				<td><input type="hidden" id="empRemarkstoBeDetached"
					name="empRemarkstoBeDetached" value="" size="100"></td>
				<td><input type="hidden" id="empIncOrderDatetoBeAttached"
					name="empIncOrderDatetoBeAttached" value="" size="100"></td>
				<td><input type="hidden" id="empIncOrderDatetoBeDetached"
					name="empIncOrderDatetoBeDetached" value="" size="100"></td>
				<td><input type="hidden" id="origBasictoBeAttached"
					name="origBasictoBeAttached" value="" size="100"></td>
				<td><input type="hidden" id="origBasictoBeDetached"
					name="origBasictoBeDetached" value="" size="100"></td>
				<td><input type="hidden" id="sizeOfOrderList"
					name="sizeOfOrderList" value="${fixListsizeWithGoUpdate}"
					size="100"></td>
				<td><input type="hidden" id="inputTagElements"
					name="inputTagElements" value="" size="100"></td>
				<td><input type="hidden" id="empOrigBasictoBeAttached"
					name="empOrigBasictoBeAttached" value="" size="100"></td>
				<td><input type="hidden" id="empPayCommissionId"
					name="empPayCommissionId" size="100"></td>
			</tr>
		</table>
		<table align="right" width="100%">
			<tr>
				<td width='85%'><font size='2' color='red'> Note <b>:</b>
						Annual Increment details entered through this form will be
						reflected in the Service Book
				</font></td>
				<td align="center"><hdiits:button type="button"
						name="btnSearch" id="btnSearch" readonly="false"
						value="Add New Order" style="width:100%"
						onclick="disableViewList();" /></td>

			</tr>
		</table>
		<br />
		<br />
		<table width="85%" cellpadding="0" cellspacing="0" class="annIncretb">
			<tr>
				<td width="100%">
					<table width="100%" id='tab_new_order' align="left"
						style="display: none">
						<tr>
							<td width="20%"><b>Increment Certificate Order No</b></TD>
							<td width="20%"><input type="text" size='25' maxlength='50'
								id='incrementCertificateOrderNo'
								name='incrementCertificateOrderNo' onKeyPress=;
								title="Should be numbers ,alphabates and / only"></td>
							<TD align="left" width="20%"><b>Order Date</b></td>
							<td width="20%" align="left"><hdiits:text name="orderDate"
									mandatory="true" validation="txt.isdt,txt.isrequired"
									maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
									captionid="orderDate" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%" id='tab_new_bill_no' align="left"
						style="display: none">
						<tr>
							<td width="20%"><b>Bill Name</b></td>
							<td width="20%"><hdiits:select name="billName" size="1"
									style="width:220px" sort="false" id="billName" mandatory="true">
									<option value="-1">--Select--</option>
									<c:forEach items="${billList}" var="billList">
										<option value="${billList.dcpsDdoBillGroupId}"
											title="${billList.dcpsDdoBillDescription}"><c:out
												value="${billList.dcpsDdoBillDescription}"></c:out></option>
									</c:forEach>
								</hdiits:select></td>
							<td width="20%"><b>Pay Commission</b></td>
							<td width="20%"><hdiits:select name="payCommisionList"
									size="1" sort="false" id="payCommisionList" mandatory="true">
									<option value="-1">--Select--</option>
									<c:forEach items="${payCommisionList}" var="payCommisionList">
										<option value="${payCommisionList.id}"
											title="${payCommisionList.commissionName}"><c:out
												value="${payCommisionList.commissionName}"></c:out></option>
									</c:forEach>
								</hdiits:select></td>
							
						</tr>
						<tr>
						<td colspan="4" id="goButton" align="center"><br/><hdiits:button name="go"
									type="button" captionid="GO" value="Go"
									onclick="getListOfEmployeesWithGo()" style=" width: 80px !important; margin-bottom: 5px; margin-left: 180px;" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<div style="overflow: scroll;" id="tcontent1" class="halftabcontent">
			<div style="height: 200px;" align="center">
				<h1>
					<c:set var="i" value="1"></c:set>
					<display:table name="${fixList}" requestURI="" pagesize=""
						id="orderList" export="false">
						<display:column title="" headerClass="datatableheader"
							style="text-align: center;">
							<c:choose>
								<c:when test="${orderList.status eq 'Verified'}">
									<input name="radio" id="radioId${i}" type="radio"
										align="middle" align="middle" disabled="true" />
									<hdiits:hidden name="tnrOrderNo${i}" id="tnrOrderNoId${i}"
										default="${orderList.incrementOrderNo}" />
								</c:when>
								<c:otherwise>
									<input name="radio" id="radioId${i}" type="radio"
										align="middle" align="middle" />
									<hdiits:hidden name="tnrOrderNo${i}" id="tnrOrderNoId${i}"
										default="${orderList.incrementOrderNo}" />
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:column class="tablecelltext" title="Increment Order No"
							headerClass="datatableheader" style="text-align: center;">
							<a
								href="javascript:viewSelectedOrder('${orderList.incrementOrderNo}','${orderList.incrementOrderDate}');">${orderList.incrementOrderNo}
							</a>
						</display:column>
						<display:column property="incrementOrderDate"
							class="tablecelltext" title="Increment Order Date"
							headerClass="datatableheader" style="text-align: center;"></display:column>
						<display:column property="countOfEmployees" class="tablecelltext"
							title="No.of employees in order" headerClass="datatableheader"
							style="text-align: center;"></display:column>
						<display:column property="status" title="Status"
							headerClass="datatableheader" style="text-align: center;"></display:column>
						<display:column title="" headerClass="datatableheader"
							style="text-align: center;">
							<a href="javascript:getHistory('${orderList.incrementOrderNo}')"><b>Print
									Report</b></a>
						</display:column>
						<display:column headerClass="datatableheader" style="display:none">
							<input type="text" name="disableFlag" id="disableFlag"
								value="${orderList.status}"></input>
						</display:column>
						<display:setProperty name="export.pdf" value="true" />
						<c:set var="i" value="${i+1}"></c:set>
					</display:table>
				</h1>
			</div>
		</div>
		<input type="hidden" name="counterEmp" id="counterEmp" value="0" />
		<input type="hidden" name="counterEmpOrder" id="counterEmpOrder"
			value="0" />
		<hdiits:hidden name="save" id="save" default="${save}" />
		<hdiits:hidden name="incrementOrderNo" id="incrementOrderNo"
			default="${resultValue.incrementOrderNo}" />
		<hdiits:hidden name="incrementorderDate" id="incrementorderDate"
			default="${resultValue.incrementorderDate}" />
		<c:choose>
			<c:when test="${payCommId == 2500347}">
				<table id="parentOrderList" style="width: 100%" border="0"
					rules="none">
					<tr>
						<td width="45%"><c:choose>
								<c:when test="${fixListsizeWithGo gt 0}">
									<div
										style="height: 200px; width: 100%; overflow: scroll; border: 1px solid #000;">
										<display:table list="${fixListWithGo}" name="${fixListWithGo}"
											class="cllabel" id="empListTable" uid="empListTable"
											requestURI="" pagesize="" export="false" cellpadding='0'
											cellspacing='0' sort="external">
											<display:column class="tablecelltext"
												style="text-align:center" headerClass="datatableheader"
												title="<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>">
												<input type="checkbox" name="GroupCheck"
													id="GroupCheck${empListTable_rowNum}"
													value="${empListTable.userid}" align="middle"
													onclick=disableRemoveButton();enableAddButton();;>
												<script>
	document.getElementById("counterEmp").value = Number(document
			.getElementById("counterEmp").value) + 1;
</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="User Id"
												headerClass="datatableheader">
												<label id="userId${empListTable_rowNum}">${empListTable.userid}</label>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Emp Name"
												headerClass="datatableheader">
												<label id="empName${empListTable_rowNum}">${empListTable.empName}</label>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center"
												title='<span>Basic Salary<Font size="2" face="Rupee Foradian"> (<Font size="2" face="Rupee Foradian">`</Font>)</Font> </span>'
												headerClass="datatableheader">
												<label id="empBasic${empListTable_rowNum}">${empListTable.basic}</label>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Inc Order Date"
												headerClass="datatableheader">
												<label id="incOrderDate${empListTable_rowNum}">${empListTable.incrementOrderDate}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<label id="nextIncrDt${empListTable_rowNum}">${empListTable.nextincrementDate}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<label id="scaleEndAmt${empListTable_rowNum}">${empListTable.scaleEndAmount}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none">
												<label id="remarks${empListTable_rowNum}">${empListTable.remarks}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<input type="hidden" id="nextIncrDt${empListTable_rowNum}"
													value="${empListTable.nextincrementDate}" />
												<label id="nextIncrDt${empListTable_rowNum}">${empListTable.nextincrementDate}</label>
												<input type="hidden" id="nxtIncrDate" name="nxtIncrDate"
													value="${empListTable.nextincrementDate}">
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="origBasic${empListTable_rowNum}">${empListTable.basic}</label>
											</display:column>

											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="employeePayCommId${empListTable_rowNum}">${empListTable.payCommissionId}</label>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="empIncrAmt${empListTable_rowNum}">${empListTable.scaleNxtIncrAmt}</label>
											</display:column>

											<display:caption
												style="text-align:center;color: rgb(200,100,20)">
												<b>List of Employees due for Increment</b>
											</display:caption>
										</display:table>
									</div>
								</c:when>
								<c:otherwise>
									<div
										style="height: 200px; width: 100%; overflow: scroll; border: 1px solid #000;">
										<TABLE border='0' RULES="NONE" align="center" class="cllabel"
											cellpadding='' id="empListTable" cellspacing='0' width="100%"
											style="border-bottom: 1px solid #396DA5;">
											<tr>
												<caption style="text-align: center">
													<b>List of Employees due for Increment</b>
												</caption>
											</tr>
											<TR class="cllabel">
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Check
													Box</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">User
													Id</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold"><font
													size='2'>Emp Name</font></TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Basic
													Salary<br> <FONT SIZE='1'>(Pay in PB+GP)</font><Font
													size="2" face='Rupee Foradian'> (<Font size="2 "
														face='Rupee Foradian'>`</Font>) 
												</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Inc
													Order Date</TD>
											</TR>
										</table>
									</div>
								</c:otherwise>
							</c:choose></td>
						<td width="5">
							<table align="left">
								<tr>
									<td><hdiits:button name="addFromLeftToRight"
											value="Add To List" type="button" captionid="AddToList"
											onclick="fun_attach();"></hdiits:button> <br /> <hdiits:button
											name="addFromRightToLeft" value="Remove" type="button"
											captionid="PREMOVE" onclick="fun_detach()"></hdiits:button> <br />
									</td>
								</tr>
							</table>
						</td>
						<td width="45%"><c:choose>
								<c:when test="${fixListsizeWithGoUpdate gt 0}">
									<div
										style="height: 200px; width: 100%; overflow: scroll; border: 1px solid #000;">
										<display:table list="${fixListWithGoUpdate}" class="cllabel"
											name="${fixListWithGoUpdate}" id="empListOfOrderTable"
											uid="empListOfOrderTable" requestURI="" pagesize=""
											export="false" cellpadding='0' cellspacing='0'>
											<display:column class="tablecelltext"
												style="text-align:center" headerClass="datatableheader">
												<c:choose>
													<c:when test="${empListOfOrderTable.status eq 'Verified'}">
														<input type="checkbox" name="GroupCheckOrder"
															id="GroupCheckOrder${empListOfOrderTable_rowNum}"
															value="${empListOfOrderTable.userid}" disabled="true"
															align="middle">
														<script>
	document.getElementById("addFromLeftToRight").disabled = true;
	document.getElementById("addFromRightToLeft").disabled = true;
</script>
													</c:when>
													<c:otherwise>
														<input type="checkbox" name="GroupCheckOrder"
															id="GroupCheckOrder${empListOfOrderTable_rowNum}"
															value="${empListOfOrderTable.userid}" align="middle"
															onclick=disableAddButton();enableRemoveButton();;>
	disableAddButton();
	enableRemoveButton();;
>
									<script>
	document.getElementById("counterEmpOrder").value = Number(document
			.getElementById("counterEmpOrder").value) + 1;
</script>
													</c:otherwise>
												</c:choose>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Emp Name"
												headerClass="datatableheader">
												<label id="empNameOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.empName}</label>
												<script>
	document.getElementById("empIdstoBeAttached").value = document
			.getElementById("empIdstoBeAttached").value
			+ "${empListOfOrderTable.userid}" + "~";
</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center"
												title='<span>Basic Salary<Font size="2" face="Rupee Foradian"> (<Font size="2" face="Rupee Foradian">`</Font>)</Font> </span>'
												headerClass="datatableheader">
												<label id="empBasicOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.newBasic}</label>
												<script>
	document.getElementById("empBasicSalarytoBeAttached").value = document
			.getElementById("empBasicSalarytoBeAttached").value
			+ "${empListOfOrderTable.newBasic}" + "~";
</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="WEF Date"
												headerClass="datatableheader">
												<input type="text"
													id="empWEFOrder${empListOfOrderTable_rowNum}"
													name="empWEFOrder${empListOfOrderTable_rowNum}" size="8"
													value="${empListOfOrderTable.withEffectiveDate}" />
												<script>
	document.getElementById("empWEFtoBeAttached").value = document
			.getElementById("empWEFtoBeAttached").value
			+ "${empListOfOrderTable.withEffectiveDate}" + "~";
</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Next Increment Date"
												headerClass="datatableheader">
												<label id="nextIncrDtOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.nextincrementDate}</label>
												<script>
	document.getElementById("empNextIncrDatetoBeAttached").value = document
			.getElementById("empNextIncrDatetoBeAttached").value
			+ "${empListOfOrderTable.nextincrementDate}" + "~";
</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Remarks"
												headerClass="datatableheader">
												<input type="text"
													id="remarksOrder${empListOfOrderTable_rowNum}"
													name="remarksOrder${empListOfOrderTable_rowNum}" size="8"
													value="${empListOfOrderTable.remarks}" />
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<label id="scaleEndAmtOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.scaleEndAmount}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<label id="IncOrderDateOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.incrementOrderDate}</label>
											</display:column>

											<c:if test="${empListTable.status eq 'Entered'}">
												<script>
													//alert("in entered********");
													document.getElementById("currentStatus").value="entered";

											</script>
											</c:if>

											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="origBasicOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.basic}</label>
												<script>
	document.getElementById("origBasictoBeAttached").value = document
			.getElementById("origBasictoBeAttached").value
			+ "${empListOfOrderTable.basic}" + "~";
	document.getElementById("inputTagElements").value = document
			.getElementById("inputTagElements").value
			+ "${empListOfOrderTable.userid}"
			+ "~"
			+ "${empListOfOrderTable.withEffectiveDate}"
			+ "~"
			+ "${empListOfOrderTable.remarks}" + "~";
</script>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label
													id="employeePayCommIdOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.payCommissionId}</label>
												<script>
	document.getElementById("empPayCommissionId").value = document
			.getElementById("empPayCommissionId").value
			+ "${empListOfOrderTable.userid}"
			+ "~"
			+ "${empListOfOrderTable.payCommissionId}" + "~";
</script>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="empIncrAmtOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.scaleNxtIncrAmt}</label>
											</display:column>
											<display:caption
												style="text-align:center;color: rgb(200,100,20)">
												<b>List of Employees Release for Increment</b>
											</display:caption>
										</display:table>
									</div>
								</c:when>
								<c:otherwise>
									<div
										style="height: 200px; width: 100%; overflow: scroll; border: 1px solid #000;">
										<TABLE border='0' rules="none" align="center"
											id="empListOfOrderTable" class="cllabel" cellpadding='0'
											cellspacing='0' width="100%"
											style="border-bottom: 1px solid #396DA5;">
											<tr>
												<caption style="text-align: center">
													<b>List of Employees Release for Increment</b>
												</caption>
											</tr>
											<TR class="cllabel">
												<TD width='10%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Check
													Box</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Emp
													Name</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold"><font
													size='2'>Basic Salary</font><br> <FONT SIZE='1'>(Pay
														in PB+GP)</font><Font size="2" face='Rupee Foradian'> (<Font
														size="2 " face='Rupee Foradian'>`</Font>)</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">
													WEF Date</TD>
												<TD width='15%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">
													Next Incr Date</TD>
												<TD width='15%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Remarks</TD>
											</TR>
										</table>
									</div>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
				<!-- Added By Tejashree -->
				<table align="center">
					<tr>
						<td><hdiits:button name="savebutton" id="savebutton"
								type="button" captionid="PC.SAVE" readonly="false" value="Save"
								onclick="saveDataForSevenPC()" /></td>
						<td><hdiits:button name="cancel" id="cancel" type="button"
								captionid="PC.CANCEL" readonly="false" value="Cancel"
								onclick="fun_cancel();" /></td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<c:out value="${payCommId}">${payCommId}</c:out>
				<table id="parentOrderList" style="width: 100%" border="0"
					rules="none">
					<tr>
						<td width="45%"><c:choose>
								<c:when test="${fixListsizeWithGo gt 0}">
									<div
										style="height: 200px; width: 100%; overflow: scroll; border: 1px solid #000;">
										<display:table list="${fixListWithGo}" name="${fixListWithGo}"
											class="cllabel" id="empListTable" uid="empListTable"
											requestURI="" pagesize="" export="false" cellpadding='0'
											cellspacing='0' sort="external">
											<display:column class="tablecelltext"
												style="text-align:center" headerClass="datatableheader"
												title="<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>">
												<input type="checkbox" name="GroupCheck"
													id="GroupCheck${empListTable_rowNum}"
													value="${empListTable.userid}" align="middle"
													onclick=disableRemoveButton();enableAddButton();;>
												<script>
													document
															.getElementById("counterEmp").value = Number(document
															.getElementById("counterEmp").value) + 1;
												</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="User Id"
												headerClass="datatableheader">
												<label id="userId${empListTable_rowNum}">${empListTable.userid}</label>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Emp Name"
												headerClass="datatableheader">
												<label id="empName${empListTable_rowNum}">${empListTable.empName}</label>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center"
												title='<span>Basic Salary<Font size="2" face="Rupee Foradian"> (<Font size="2" face="Rupee Foradian">`</Font>)</Font> </span>'
												headerClass="datatableheader">
												<label id="empBasic${empListTable_rowNum}">${empListTable.basic}</label>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Inc Order Date"
												headerClass="datatableheader">
												<label id="incOrderDate${empListTable_rowNum}">${empListTable.incrementOrderDate}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<label id="nextIncrDt${empListTable_rowNum}">${empListTable.nextincrementDate}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<label id="scaleEndAmt${empListTable_rowNum}">${empListTable.scaleEndAmount}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none">
												<label id="remarks${empListTable_rowNum}">${empListTable.remarks}</label>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="origBasic${empListTable_rowNum}">${empListTable.basic}</label>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="employeePayCommId${empListTable_rowNum}">${empListTable.payCommissionId}</label>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="empIncrAmt${empListTable_rowNum}">${empListTable.scaleNxtIncrAmt}</label>
											</display:column>
											<display:caption
												style="text-align:center;color: rgb(200,100,20)">
												<b>List of Employees due for Increment</b>
											</display:caption>
										</display:table>
									</div>
								</c:when>
								<c:otherwise>
									<div
										style="height: 200px; width: 100%; overflow: scroll; border: 1px solid #000;">
										<TABLE border='0' RULES="NONE" align="center" class="cllabel"
											cellpadding='' id="empListTable" cellspacing='0' width="100%"
											style="border-bottom: 1px solid #396DA5;">
											<tr>
												<caption style="text-align: center">
													<b>List of Employees due for Increment</b>
												</caption>
											</tr>
											<TR class="cllabel">
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Check
													Box</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">User
													Id</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold"><font
													size='2'>Emp Name</font></TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Basic
													Salary<br> <FONT SIZE='1'>(Pay in PB+GP)</font><Font
													size="2" face='Rupee Foradian'> (<Font size="2 "
														face='Rupee Foradian'>`</Font>) 
												</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Inc
													Order Date</TD>
											</TR>
										</table>
									</div>
								</c:otherwise>
							</c:choose></td>
						<td width="5">
							<table align="left">
								<tr>
									<td><hdiits:button name="addFromLeftToRight"
											value="Add To List" type="button" captionid="AddToList"
											onclick="fun_attach();"></hdiits:button> <br /> <hdiits:button
											name="addFromRightToLeft" value="Remove" type="button"
											captionid="PREMOVE" onclick="fun_detach()"></hdiits:button> <br />
									</td>
								</tr>
							</table>
						</td>
						<td width="45%"><c:choose>
								<c:when test="${fixListsizeWithGoUpdate gt 0}">
									<div
										style="height: 200px; width: 100%; overflow: scroll; border: 1px solid #000;">
										<display:table list="${fixListWithGoUpdate}" class="cllabel"
											name="${fixListWithGoUpdate}" id="empListOfOrderTable"
											uid="empListOfOrderTable" requestURI="" pagesize=""
											export="false" cellpadding='0' cellspacing='0'>
											<display:column class="tablecelltext"
												style="text-align:center" headerClass="datatableheader">
												<c:choose>
													<c:when test="${empListOfOrderTable.status eq 'Verified'}">
														<input type="checkbox" name="GroupCheckOrder"
															id="GroupCheckOrder${empListOfOrderTable_rowNum}"
															value="${empListOfOrderTable.userid}" disabled="true"
															align="middle">
														<script>
															document
																	.getElementById("addFromLeftToRight").disabled = true;
															document
																	.getElementById("addFromRightToLeft").disabled = true;
														</script>
													</c:when>
													<c:otherwise>
														<input type="checkbox" name="GroupCheckOrder"
															id="GroupCheckOrder${empListOfOrderTable_rowNum}"
															value="${empListOfOrderTable.userid}" align="middle"
															onclick=disableAddButton();enableRemoveButton();;>
														<script>
															document
																	.getElementById("counterEmpOrder").value = Number(document
																	.getElementById("counterEmpOrder").value) + 1;
														</script>
													</c:otherwise>
												</c:choose>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Emp Name"
												headerClass="datatableheader">
												<label id="empNameOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.empName}</label>
												<script>
													document
															.getElementById("empIdstoBeAttached").value = document
															.getElementById("empIdstoBeAttached").value
															+ "${empListOfOrderTable.userid}"
															+ "~";
												</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center"
												title='<span>Basic Salary<Font size="2" face="Rupee Foradian"> (<Font size="2" face="Rupee Foradian">`</Font>)</Font> </span>'
												headerClass="datatableheader">
												<label id="empBasicOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.newBasic}</label>
												<script>
													document
															.getElementById("empBasicSalarytoBeAttached").value = document
															.getElementById("empBasicSalarytoBeAttached").value
															+ "${empListOfOrderTable.newBasic}"
															+ "~";
												</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="WEF Date"
												headerClass="datatableheader">
												<input type="text"
													id="empWEFOrder${empListOfOrderTable_rowNum}"
													name="empWEFOrder${empListOfOrderTable_rowNum}" size="8"
													value="${empListOfOrderTable.withEffectiveDate}" />
												<script>
													document
															.getElementById("empWEFtoBeAttached").value = document
															.getElementById("empWEFtoBeAttached").value
															+ "${empListOfOrderTable.withEffectiveDate}"
															+ "~";
												</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Next Increment Date"
												headerClass="datatableheader">
												<label id="nextIncrDtOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.nextincrementDate}</label>
												<script>
													document
															.getElementById("empNextIncrDatetoBeAttached").value = document
															.getElementById("empNextIncrDatetoBeAttached").value
															+ "${empListOfOrderTable.nextincrementDate}"
															+ "~";
												</script>
											</display:column>
											<display:column class="tablecelltext"
												style="text-align:center" title="Remarks"
												headerClass="datatableheader">
												<input type="text"
													id="remarksOrder${empListOfOrderTable_rowNum}"
													name="remarksOrder${empListOfOrderTable_rowNum}" size="8"
													value="${empListOfOrderTable.remarks}" />
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<label id="scaleEndAmtOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.scaleEndAmount}</label>
											</display:column>
											<display:column class="tablecelltext" style="display:none"
												headerClass="datatableheader">
												<label id="IncOrderDateOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.incrementOrderDate}</label>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="origBasicOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.basic}</label>
												<script>
													document
															.getElementById("origBasictoBeAttached").value = document
															.getElementById("origBasictoBeAttached").value
															+ "${empListOfOrderTable.basic}"
															+ "~";
													document
															.getElementById("inputTagElements").value = document
															.getElementById("inputTagElements").value
															+ "${empListOfOrderTable.userid}"
															+ "~"
															+ "${empListOfOrderTable.withEffectiveDate}"
															+ "~"
															+ "${empListOfOrderTable.remarks}"
															+ "~";
												</script>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label
													id="employeePayCommIdOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.payCommissionId}</label>
												<script>
													document
															.getElementById("empPayCommissionId").value = document
															.getElementById("empPayCommissionId").value
															+ "${empListOfOrderTable.userid}"
															+ "~"
															+ "${empListOfOrderTable.payCommissionId}"
															+ "~";
												</script>
											</display:column>
											<display:column headerClass="datatableheader"
												style="display:none">
												<label id="empIncrAmtOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.scaleNxtIncrAmt}</label>
											</display:column>
											<display:caption
												style="text-align:center;color: rgb(200,100,20)">
												<b>List of Employees Release for Increment</b>
											</display:caption>
										</display:table>
									</div>
								</c:when>
								<c:otherwise>
									<div
										style="height: 200px; width: 100%; overflow: scroll; border: 1px solid #000;">
										<TABLE border='0' rules="none" align="center"
											id="empListOfOrderTable" class="cllabel" cellpadding='0'
											cellspacing='0' width="100%"
											style="border-bottom: 1px solid #396DA5;">
											<tr>
												<caption style="text-align: center">
													<b>List of Employees Release for Increment</b>
												</caption>
											</tr>
											<TR class="cllabel">
												<TD width='10%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Check
													Box</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Emp
													Name</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold"><font
													size='2'>Basic Salary</font><br> <FONT SIZE='1'>(Pay
														in PB+GP)</font><Font size="2" face='Rupee Foradian'> (<Font
														size="2 " face='Rupee Foradian'>`</Font>)</TD>
												<TD width='20%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">
													WEF Date</TD>
												<TD width='15%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">
													Next Incr Date</TD>
												<TD width='15%' align='center' class="cllabel"
													style="color: rgb(200, 100, 20); font-size: small; font-style: normal; font-weight: bold">Remarks</TD>
											</TR>
										</table>
									</div>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</table>

				<table align="right">
					<tr>
						<td><hdiits:button name="savebutton" id="savebutton"
								type="button" captionid="PC.SAVE" readonly="false" value="Save"
								onclick="saveData()" /></td>
						<td><hdiits:button name="cancel" id="cancel" type="button"
								captionid="PC.CANCEL" readonly="false" value="Cancel"
								onclick="fun_cancel();" /></td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
		<br />
		<br />
		<br />
		<br />
		<table width="100%" align="right">
			<tr>
				<td width="55%" align="right">&nbsp;&nbsp; <font size='2'
					color='red' face='arial'> Note : Remarks must be given in
						case if WEF date is not 1st day of month </font></td>
			</tr>
		</table>
	</hdiits:form>
	<script type="text/javascript">
	showMsg();
</script>
</body>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
