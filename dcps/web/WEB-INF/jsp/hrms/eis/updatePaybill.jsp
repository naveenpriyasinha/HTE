<%
try{
%>
<%@page import="java.util.List,java.text.SimpleDateFormat,java.util.Date,com.tcs.sgv.eis.valueobject.EdpDtlsVO,com.tcs.sgv.eis.valueobject.HrPayPaybill"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="dsgnName" value="${resValue.dsgnName}" ></c:set>
<c:set var="dsgnName" value="${resValue.dsgnName}" ></c:set>
<c:set var="gradePay" value="${resValue.gradePay}" ></c:set>
<c:set var="billNo" value="${resValue.billNo}" ></c:set>
<c:set var="billName" value="${resValue.billName}" ></c:set>
<c:set var="scale" value="${resValue.scale}" ></c:set>
<c:set var="paybillList" value="${resValue.paybillList}" ></c:set>
<c:set var="paybillMonth" value="${resValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resValue.paybillYear}" ></c:set>
<c:set var="edpDtlsAllowanceList" value="${resValue.edpDtlsAllowanceList}" ></c:set>
<c:set var="edpDtlsDeductionList" value="${resValue.edpDtlsDeductionList}" ></c:set>
<c:set var="edpDtlsLoansList" value="${resValue.edpDtlsLoansList}" ></c:set>
<c:set var="maxSize" value="${resValue.maxSize}" ></c:set>
<c:set var="AllowanceListSize" value="${resValue.AllowanceListSize}" ></c:set>
<c:set var="DeductionListSize" value="${resValue.DeductionListSize}" ></c:set>
<c:set var="LoansListSize" value="${resValue.LoansListSize}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="gpfNo" value="${resValue.gpfNo}" ></c:set>
<c:set var="basicPay" value="${resValue.basicPay}" ></c:set>



<script type="text/javascript">
if("${msg}" != "")
{
	alert("${msg}");
}



function disabledButton()
{
	var billId = document.updatePaybill.hdnBillNo.value;
	
	if(billId > 0)
		document.updatePaybill.Save.disabled = false;
	else
		document.updatePaybill.Save.disabled = true;

	if("${billNo}" > 0)
	{
		//document.getElementById("billNameTxt").style.display="";
		//document.getElementById("billData").style.display="";
		document.getElementById("basicDtls").style.display="";
		document.getElementById("paybillDtls").style.display="";
		document.getElementById("buttontab").style.display="";
		getBillNo();
	}
}
function searchPaybillData()
{
	showProgressbar("Please wait...");
	var updatePaybillEmpId=document.getElementById("paybillEmployee").value;
	
	if(document.updatePaybill.paybillMonth.value=='-1')
    	alert('Please select month'); 
    else if(document.updatePaybill.paybillYear.value=='-1')
    	alert('Please select Year');
    else if(updatePaybillEmpId != null && updatePaybillEmpId == 0)
        alert("Please select Employee"); 
    else
    {   
        var month = document.updatePaybill.paybillMonth.value;
        var year = document.updatePaybill.paybillYear.value;
		var url="./hdiits.htm?actionFlag=fillPaybillData&updatePaybillEmpId="+updatePaybillEmpId+"&paybillMonth="+month+"&paybillYear="+year+"&empId="+updatePaybillEmpId+"&searchData=Y";
		document.updatePaybill.action=url;
		document.updatePaybill.submit();
    }
}

function submitForm()
{
	var month = document.updatePaybill.paybillMonth.value;
    var year = document.updatePaybill.paybillYear.value;
    var selectedAction = document.updatePaybill.paybillAction.value;
    var billno = document.updatePaybill.hdnBillNo.value;
    if(selectedAction == 1)
    {
    	var voucherNo = document.updatePaybill.txtVoucherNo.value;
    	var voucherDate = document.updatePaybill.voucherDate.value;
    	
    	document.updatePaybill.hdnBillNo.value =  document.updatePaybill.paybillBill.value;
    	if(voucherNo == 0 && voucherNo == '')
    	{
        	alert("Please enter voucher number");
        	return;
    	}
    	else if(voucherDate == '')
    	{
        	alert("Please enter voucher date");
        	return;
    	}
    	else
    	{
    		var url="./hdiits.htm?actionFlag=updateBillData&month="+month+"&year="+year+"&selectedAction="+selectedAction;
        	document.updatePaybill.action=url;
        	document.updatePaybill.submit();
        	showProgressbar("Please wait...");
    	}
    }
    if (selectedAction == -1 && billno > 0)
    {
    	var remarks = document.updatePaybill.txtRemark.value;
    	
        if(remarks == null || remarks == '' )
        {
            alert("Please Enter Remark");
            return;
        }
        url = '';
    	url="./hdiits.htm?actionFlag=updateBillData&allowSize=${AllowanceListSize}&deductSize=${DeductionListSize}&loanSize=${LoansListSize}&month="+month+"&year="+year+"&selectedAction=2";
    	document.updatePaybill.action=url;
    	document.updatePaybill.submit();
    	showProgressbar("Please wait...");
    }
    
}

function onclosefunction()
{
	window.location="hrms.htm?actionFlag=validateLogin";
}

function getBillNo()
{
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 
	var url; 
	var uri='';

	var month = document.updatePaybill.paybillMonth.value;
    var year = document.updatePaybill.paybillYear.value;
    var slectedAction = document.updatePaybill.paybillAction.value;

    if(month == -1)
    {
        alert("Please select Month");
        return;
    }
    else if(year == -1)
    {
    	alert("Please select Year");
        return;
    }
    else
    {
		url= uri+'&month='+month+'&year='+year+'&slectedAction='+slectedAction;
		var actionf="getApprovedBill";
		uri='./hrms.htm?actionFlag='+actionf;
		url=uri+url; 
		xmlHttp.onreadystatechange=fillBillCombo;
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);	
    }
}	

function fillBillCombo()
{
	if (xmlHttp.readyState==complete_state)
	{
		clearCombo("paybillBill");
		var paybillBillCmb = document.getElementById("paybillBill");
		var XMLDoc=xmlHttp.responseXML.documentElement;			
		if(XMLDoc==null)
        {
			window.status = 'No Records Found.';
		}
		else
        {
			window.status='';
            var billEntries = XMLDoc.getElementsByTagName('approved-bills');
   			for ( var i = 0 ; i < billEntries.length ; i++ )
 			{
   				val = billEntries[i].childNodes[0].firstChild.nodeValue;    
				text = billEntries[i].childNodes[1].firstChild.nodeValue; 			     				   
				var y = document.createElement('option');
			    y.value=val;
			    y.text=text;
			    try
			    {	      				    					
			    	paybillBillCmb.add(y,null);
   		        }
   				catch(ex)
				{
   					paybillBillCmb.add(y); 
				}
 			}
        }
	} 
}

function getEmployeeList()
{
	document.getElementById("employeeList").style.display="none";
	document.getElementById("voucherEntry").style.display="none";
	document.getElementById("basicDtls").style.display="none";
	document.getElementById("paybillDtls").style.display="none";
	
	var actionSelected = document.updatePaybill.paybillAction.value;
	if(actionSelected == -1)
	{
		alert("Please select action");
		return;
	}
	else if(actionSelected == 2)
	{
		document.getElementById("employeeList").style.display="";
		document.getElementById("voucherEntry").style.display="none";
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
			alert ("Your browser does not support AJAX!");
			return;
		} 
		var url; 
		var uri='';

		var billNo = document.updatePaybill.paybillBill.value;
		var month = document.updatePaybill.paybillMonth.value;
	    var year = document.updatePaybill.paybillYear.value;

	    if(month == -1)
	    {
	        alert("Please select Month");
	        return;
	    }
	    else if(year == -1)
	    {
	        alert("Please select Year");
	        return;
	    }
	    else if(billNo == -1)
	    {
	        alert("Please select Bill");
	        return;
	    }
	    else
	    {
			url= uri+'&billNo='+billNo+'&month='+month+'&year='+year;
			var actionf="getEmployeeByBill";
			uri='./hrms.htm?actionFlag='+actionf;
			url=uri+url; 
			xmlHttp.onreadystatechange=fillEmployeeCombo;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
	    }
	}
	else if(actionSelected == 1)
	{
		document.getElementById("employeeList").style.display="none";
		document.getElementById("voucherEntry").style.display="";

		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
			alert ("Your browser does not support AJAX!");
			return;
		} 
		 
		uri='';
		billNo = document.updatePaybill.paybillBill.value;
		month = document.updatePaybill.paybillMonth.value;
	    year = document.updatePaybill.paybillYear.value;

	    if(month == -1)
	    {
	        alert("Please select Month");
	        return;
	    }
	    else if(year == -1)
	    {
	        alert("Please select Year");
	        return;
	    }
	    else if(billNo == -1)
	    {
	        alert("Please select Bill");
	        return;
	    }
	    else
	    {
			url= uri+'&billNo='+billNo+'&month='+month+'&year='+year;
			actionf="getVoucherEntryByBill";
			uri='./hrms.htm?actionFlag='+actionf;
			url=uri+url; 
			xmlHttp.onreadystatechange=fillVoucherEntry;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
	    }
	}
}

function fillVoucherEntry()
{
	if (xmlHttp.readyState==complete_state)
	{
		var XMLDoc=xmlHttp.responseXML.documentElement;			
		if(XMLDoc==null)
        {
			window.status = 'No Records Found.';
		}
		else
        {
			window.status='';
            var voucherEntries = XMLDoc.getElementsByTagName('voucher-dtls');
   			for ( var i = 0 ; i < voucherEntries.length ; i++ )
 			{
   	 			var status = voucherEntries[i].childNodes[0].firstChild.nodeValue;
   	 			if(status == 'approved')
   	 			{
   					var vochNo = voucherEntries[i].childNodes[1].firstChild.nodeValue;    
					var vochDate = voucherEntries[i].childNodes[2].firstChild.nodeValue; 	
					document.getElementById("txtVoucherNo").value = vochNo;
					document.getElementById("voucherDate").value = vochDate;

					document.getElementById("voucherEntry").style.display="";
					document.getElementById("buttontab").style.display="";
		   			document.updatePaybill.Save.disabled = false;
   	 			}
   	 			else if(status == 'created')
   	 			{
   	   	 			alert("This bill is not approved.");
   	 				document.getElementById("buttontab").style.display="none";
   	 			document.getElementById("voucherEntry").style.display="none";
	   				document.updatePaybill.Save.disabled = true;
   	 			}
 			}
   			
        }
	} 
}

function fillEmployeeCombo()
{
	if (xmlHttp.readyState==complete_state)
	{
		clearCombo("paybillEmployee");
		var paybillEmployeeCmb = document.getElementById("paybillEmployee");
		var XMLDoc=xmlHttp.responseXML.documentElement;			
		if(XMLDoc==null)
        {
			window.status = 'No Records Found.';
		}
		else
        {
			window.status='';
            var employeeEntries = XMLDoc.getElementsByTagName('employee-list');
   			for ( var i = 0 ; i < employeeEntries.length ; i++ )
 			{
   				val = employeeEntries[i].childNodes[0].firstChild.nodeValue;    
				text = employeeEntries[i].childNodes[1].firstChild.nodeValue; 			     				   
				var y = document.createElement('option');
			    y.value=val;
			    y.text=text;
			    try
			    {	      				    					
			    	paybillEmployeeCmb.add(y,null);
   		        }
   				catch(ex)
				{
   					paybillEmployeeCmb.add(y); 
				}
 			}
        }
	} 
}

function clearCombo(id)
{
	var v = document.getElementById(id).length;
	for(var i = 1; i < v; i++)
	{
		lgth = document.getElementById(id).options.length -1;
		document.getElementById(id).options[lgth] = null;
	}		
}
</script>
<%
HrPayPaybill actionList = (HrPayPaybill) pageContext.getAttribute("paybillList");
//int size = actionList.size();
//pageContext.setAttribute("listSize",size);
//if(size==1)
	pageContext.setAttribute("paybillVO",actionList);	

SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
Date dt = new Date();
int curYear=Integer.parseInt(sdfObj.format(dt));
sdfObj = new SimpleDateFormat("MM");
int curMonth=Integer.parseInt(sdfObj.format(dt));

int yr=Integer.parseInt(sdfObj.format(dt));

int resMonth=  Integer.parseInt(pageContext.getAttribute("paybillMonth").toString());

if(resMonth > 0)
	curMonth=(resMonth);

int resYear=  Integer.parseInt(pageContext.getAttribute("paybillYear").toString());

if(resYear > 0)
	curYear=(resYear);


pageContext.setAttribute("curMonth",curMonth);
pageContext.setAttribute("curYear",curYear);

%>
<body>
<hdiits:form name="updatePaybill" validate="true" method="POST"	encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>Update Paybill</b></a></li>
	</ul>
</div>
<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
<table width="100%">
	<tr>
		<td width="10%">
				Month
		</td>
		<td width="40%">
			<hdiits:select name="paybillMonth" size="1" sort="false" caption="Month" id="selMonth" mandatory="true" validation="sel.isrequired" onchange="">
				<hdiits:option value="-1"> --Select-- </hdiits:option>
				<c:forEach items="${monthList}" var="month">
					<c:choose>
           				<c:when test="${paybillMonth == month.lookupShortName}">
			 					<hdiits:option selected="true"  value="${month.lookupShortName}"> ${month.lookupDesc}</hdiits:option>
						</c:when>
						<c:otherwise>
			 					<hdiits:option  value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
						</c:otherwise>
					</c:choose>		
				</c:forEach>
			</hdiits:select>
		</td>
		<td width="10%">
			Year
		</td>
		<td width="40%">
			<hdiits:select name="paybillYear" size="1" sort="false" caption="Year" id="selYear" mandatory="true" validation="sel.isrequired" onchange="">
				<hdiits:option value="-1"> --Select-- </hdiits:option>
					<c:forEach items="${yearList}" var="year">
						<c:choose>
           					<c:when test="${paybillYear == year.lookupShortName}">
			 						<hdiits:option selected="true"  value="${year.lookupShortName}"> ${year.lookupDesc}</hdiits:option>
							</c:when>
							<c:otherwise>
			 						<hdiits:option  value="${year.lookupShortName}"> ${year.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>		
					</c:forEach>
			</hdiits:select>
		</td>
	</tr>
	<tr>
		<td>
			Action
		</td>
		<td colspan="3">
			<hdiits:select name="paybillAction" size="1" sort="false" caption="paybillAction" id="paybillAction" mandatory="true" validation="sel.isrequired" onchange="getBillNo();">
				<hdiits:option value="-1"> --Select-- </hdiits:option>
				<hdiits:option value="1">Change Voucher Entry</hdiits:option>
				<hdiits:option value="2">Change Employee Data</hdiits:option>
			</hdiits:select>
		</td>
	</tr>
	<tr>
		<td>
			Approved Bill
		</td>
		<td colspan="3">
			<hdiits:select name="paybillBill" size="1" sort="false" caption="paybillBill" id="paybillBill" mandatory="true" validation="sel.isrequired" onchange="getEmployeeList();">
				<hdiits:option value="-1"> --Select-- </hdiits:option>
			</hdiits:select>
		</td>
	</tr>
	
	<tr id="employeeList" style="display: none;" >
		<td>
			Employee List
		</td>
		<td colspan="3">
			<hdiits:select name="paybillEmployee" size="1" sort="false" caption="paybillEmployee" id="paybillEmployee" mandatory="true" validation="sel.isrequired" onchange="searchPaybillData();">
					<hdiits:option value="-1"> --Select-- </hdiits:option>
			</hdiits:select>
		</td>
	</tr>
	<tr id="voucherEntry" style="display: none;" >
		<td>
			Voucher No.
		</td>
		<td>
			<hdiits:number name="txtVoucherNo" captionid="txtVoucherNo"  caption ="txtVoucherNo" maxlength="20" size="20"   />
		</td>
		<td>
			Voucher Date
		</td>
		<td>
			<hdiits:dateTime captionid="voucherDate" name="voucherDate"	mandatory="true" validation="txt.isdt" />
		</td>
	</tr>
</table>
<br />
<br />
<br />
	<TABLE width="100%" border="0" align="center" id="basicDtls" style="display: none;">
		<tr>
			<td width="10%" >
				Bill Name
			</td>
			<td colspan="5">
				<input type="hidden" name = "hdnBillNo" name = "hdnBillNo" value="${billNo}"/>
				<hdiits:text readonly="true" size="50" id="txtbillNo" name="txtbillNo"  caption ="txtbillNo" maxlength="100"  default="${billName}"/>
			</td>
		</tr>
		<tr>
			<TD width="10%">
   				Employee Name :
   			</TD> 
   			<td width="20%">
   				<input type="hidden" name = "employeeId" id="employeeId" value="${paybillVO.hrEisEmpMst.empId}">  
   				<hdiits:text readonly="true" size="30" id="txtEmpName" name="txtEmpName"  caption ="txtEmpName" maxlength="100"  default="${paybillVO.hrEisEmpMst.orgEmpMst.empFname} ${paybillVO.hrEisEmpMst.orgEmpMst.empMname} ${paybillVO.hrEisEmpMst.orgEmpMst.empLname}"/>
			</TD>
			<TD width="10%">
	   			Designation :
	   		</TD> 
   			<td width="20%">
       			<hdiits:text  readonly="true"  default ="${dsgnName}" name="txtdesg" captionid="txtdesg"  caption ="txtdesg" maxlength="20" size="20"  />
 			</TD>	
			<TD width="10%">
	   			Scale :
	   		</TD> 
   			<td width="30%">
       			<hdiits:number readonly="true"  default ="${scale}"  name="txtScale" captionid="txtScale"  caption ="txtScale" maxlength="20" size="20"   />
 			</TD>	
		</tr>
		<tr>
			<td>
				Basic Pay
			</td>
			<td>
				<hdiits:text  readonly="true" default="${basicPay}"  name="txtBasicPay" captionid="txtBasicPay"  caption ="txtBasicPay" maxlength="20" size="20"   />
			</td>
			<TD>
				Grade Pay : 
			</TD> 
   			<td>  
   				<hdiits:text  readonly="true" default="${gradePay}"  name="txtGradePay" captionid="txtGradePay"  caption ="txtGradePay" maxlength="20" size="20"   />
			</TD>
			<TD>
				GPF Account :
			</TD> 
   			<td>   
   				<hdiits:text  readonly="true" default ="${gpfNo}" name="txtgpfAcct" captionid="txtgpfAcct"  caption ="txtgpfAcct" maxlength="20" size="20"   />
			</TD>
		</tr>
		<tr>
			<td>
				Remarks
			</td>
			<td colspan="5">
				<hdiits:textarea mandatory="true" name="txtRemark" captionid="txtRemark"  caption ="txtRemark" maxlength="100" cols="30" rows="3"  />
			</td>
		</tr>
	</TABLE>
	<table width="100%" id="paybillDtls" style="display: none;">
	<c:set var="srNoAllown" value="1"></c:set>
	<c:set var="srNoDeduct" value="1"></c:set>
	<c:set var="srNoLoan" value="1"></c:set>
	
	<tr>
		<td colspan="4">
			&nbsp;	
		</td>
	</tr>
	<tr>
		<td colspan="4">
			&nbsp;	
		</td>
	</tr>
	<tr>
		<td colspan="4">
			&nbsp;	
		</td>
	</tr>
	<tr style="display: none;" id="headRow">
		<td colspan="2" align="center">
			<b>Allowances</b>
		</td>
		<td colspan="2" align="center">
			<b>Deductions</b>
		</td>
	</tr>
	<%
		int count = Integer.parseInt(pageContext.getAttribute("maxSize").toString()) ;
 		int allowCount = Integer.parseInt( pageContext.getAttribute("AllowanceListSize").toString());
 		int deducCount = Integer.parseInt(pageContext.getAttribute("DeductionListSize").toString());
 		//int LoanCount = Integer.parseInt( pageContext.getAttribute("LoansListSize").toString());
 		List<EdpDtlsVO> edpDtlsAllowanceList = (List<EdpDtlsVO> )pageContext.getAttribute("edpDtlsAllowanceList") ;
		List<EdpDtlsVO> edpDtlsDeductionList = (List<EdpDtlsVO> )pageContext.getAttribute("edpDtlsDeductionList") ;
		//List<EdpDtlsVO> edpDtlsLoansList = (List<EdpDtlsVO> )pageContext.getAttribute("edpDtlsLoansList") ;
		
		for(int i = 0; i < count; i++)
	 	{
	%>
			<tr>
	<%
			if(i < allowCount )
			{
				EdpDtlsVO edpDtlsVO = edpDtlsAllowanceList.get(i);
				pageContext.setAttribute("allowEdp",edpDtlsVO);
	%>
				<td width="17%">
					<c:out value="${allowEdp.displayName}" />
				</td>
				<td width="17%">
					<input type="hidden" name="allowEdp${srNoAllown}" id="allowEdp${srNoAllown}" value="${allowEdp.edpCode}~${allowEdp.expRcpRec}"> 
					<input type="hidden" name="hdnallowEdpOldValue${srNoAllown}" id="hdnallowEdpOldValue${srNoAllown}" value="${allowEdp.amount}">
					<input type="text" style="text-align: right" value="${allowEdp.amount}"  name = "allowValue${srNoAllown}" />
				<%-- 	<hdiits:number default="${allowEdp.amount}" name="allowValue${srNoAllown}" id="allowValue${srNoAllown}" caption="allowValue${srNoAllown}" maxlength="10" validation="txt.isrequired,txt.isnumber" mandatory="false" />--%>
					<c:set var="srNoAllown" value="${srNoAllown + 1}"></c:set>
				</td>
	<%
			}
			else
			{
	%>
				<td colspan="2">&nbsp;
				</td>
	<%
			}
			if(i < deducCount )
			{
				EdpDtlsVO edpDtlsVO = edpDtlsDeductionList.get(i);
				pageContext.setAttribute("deductEdp",edpDtlsVO);
	%>
				<td width="17%">
					<c:out value="${deductEdp.displayName}" />
				</td>
				<td width="17%">
					<input type="hidden" name="deduct${srNoDeduct}" id="deduct${srNoDeduct}" value="${deductEdp.edpCode}~${deductEdp.expRcpRec}"> 
					<input type="hidden" name="hdndeductOldValue${srNoAllown}" id="hdndeductOldValue${srNoAllown}" value="${deductEdp.amount}">
					<input type="text" style="text-align: right" value="${deductEdp.amount}"  name = "deductValue${srNoDeduct}" />
				<%-- 	<hdiits:number default="${deductEdp.amount}" name="deductValue${srNoDeduct}" id="deductValue${srNoDeduct}" caption="deductValue${srNoDeduct}" maxlength="10" validation="txt.isrequired,txt.isnumber" mandatory="false" />--%>
					<c:set var="srNoDeduct" value="${srNoDeduct + 1}"></c:set>
				</td>
	<%
			}
			/*if(i < LoanCount )
			{
				EdpDtlsVO edpDtlsVO = edpDtlsLoansList.get(i);
				pageContext.setAttribute("loanEdp",edpDtlsVO);*/
	%>
<!--				<td width="15%">-->
<!--					<c:out value="${loanEdp.displayName}" />-->
<!--				</td>-->
<!--				<td width="17%">-->
<!--					<input type="hidden" name="loan${srNoLoan}" id="loan${srNoLoan}" value="${loanEdp.edpCode}~${loanEdp.expRcpRec}"> -->
<!--					<input type="text" style="text-align: right" value="${loanEdp.amount}"  name = "loanValue${srNoLoan}" />-->
					<%-- <c:set var="srNoLoan" value="${srNoLoan + 1}"></c:set>--%>
<!--				</td>-->
	<%
			//}
	%>
			</tr>
	<%
	 	}
	%>
	</table>
	<table align="center" style="display: none;" id="buttontab">
		<tr>
			<td>
				<hdiits:button name="Save" style="text-align:center;" id="Save"	type="button" caption="Save" onclick="submitForm()" /> 
				<hdiits:button name="btnClose1" style="text-align:center;" type="button" caption="Close" onclick="onclosefunction()" />
			</td>
		</tr>
	</table>
</div>
</hdiits:form>
<script type="text/javascript">
initializetabcontent("maintab");
disabledButton();
</script>
</body>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>