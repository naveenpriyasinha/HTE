<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="customVO" value="${resultValue.customVO}" > </c:set>
<c:set var="loanEmpId" value="${resultValue.loanEmpId}" > </c:set>
<c:set var="msgId" value="${resultValue.msg}" > </c:set>
<c:set var="empAmt" value="${customVO.installmentAmount}"></c:set>
<c:set var="outStandingAmt" value="${customVO.outstadingAmount}"></c:set>
<c:set var="loaDate" value="${resultValue.lloanDate}"></c:set>
<c:set var="loaEndDate" value="${resultValue.lloanEndDate}"></c:set>

<script type="text/javascript"   >

if("${msgId}"=='yes')
{
	alert('Record Updated Successfully.');
	//getData();
	onClose();
}
var minimumAmt = Math.min('${empAmt}','${customVO.oddInstallAmount}');
function getData()
{
	var elid='${loanEmpId}';
	//alert(''+elid);
	//var URL='./hrms.htm?actionFlag=getRecoveryChallanLoanValue&empLoanId='+elid+'&edit=Y';
	var URL='./hrms.htm?actionFlag=getRecoveryChallanLoanValue';
	alert('url '+URL);
	
	 document.recoveryChallan.action = URL;
	 document.recoveryChallan.submit();
}
function getDateObject(dateString,dateSeperator)
{

var curValue=dateString;
var sepChar=dateSeperator;
var curPos=0;
var cDate,cMonth,cYear;

//extract day portion
curPos=dateString.indexOf(sepChar);
cDate=dateString.substring(0,curPos);

//extract month portion 
endPos=dateString.indexOf(sepChar,curPos+1); cMonth=dateString.substring(curPos+1,endPos);

//extract year portion 
curPos=endPos;
endPos=curPos+5; 
cYear=curValue.substring(curPos+1,endPos);

//Create Date Object
dtObject=new Date(cYear,cMonth,cDate); 
return dtObject;
}

function onClose()
{
	
	
	window.location = "hrms.htm?actionFlag=getRecoveryChallanLoanValue";

}

function submitForm()
{
	//var flag= validateFields();
	//alert(''+flag);

	var aPaid= document.getElementById("amtPaid").value;
	var emi= '${empAmt}';
	var totalAmount= '${outStandingAmt}';
	//alert(''+emi);
	//alert(''+aPaid%emi);
	var box="";

	//alert(''+document.recoveryChallan.recoveryOrederDate.value);

	var lnDate= '${loaDate}';
	var loanEndDate= '${loaEndDate}';
	//alert(''+lnDate);
	
	var ldate=getDateObject(lnDate,"-");
	var orderDate= getDateObject(document.recoveryChallan.recoveryOrederDate.value,"/");
	var lnEndDate=getDateObject(loanEndDate,"-");
	//alert(' orderDate'+orderDate);
	//alert('loan date '+ldate);
	//alert(orderDate<ldate);
	
	if(document.getElementById("recoveryOrderNo").value =="")
	{
		alert('Please insert Recovery Order No');
		 box=document.getElementById('recoveryOrderNo');
		box.focus();
		return false;
	}
	
	else if(document.recoveryChallan.recoveryOrederDate.value == "")
	{
		alert('Please select Recovery Order Date');
		 box=document.getElementById('recoveryOrederDate');
		box.focus();
		return false;
	}
	else if(orderDate<ldate)
	{
		
		alert('Recovery Order Date can not be small than loan date i.e.'+lnDate);
		 box=document.getElementById('recoveryOrederDate');
		box.focus();
		return false;
	}
	else if(orderDate>lnEndDate)
	{
		
		alert('Recovery Order Date can not be greater than loan end date i.e.'+loanEndDate);
		 box=document.getElementById('recoveryOrederDate');
		box.focus();
		return false;
	}
	else if(document.recoveryChallan.challanNo.value == "")
	{
		alert('Please insert Challan No');
		 box=document.getElementById('challanNo');
		box.focus();
		return false;
	}
	else if(document.recoveryChallan.challanDate.value == "")
	{
		alert('Please select Challan Date');
		 box=document.getElementById('challanDate');
		box.focus();
		return false;
	}
	
	else if(parseInt(aPaid) == 0)
	{
		alert('Please Enter non-Zero amount');
		box=document.getElementById('amtPaid');
		box.focus();
		return false;
	}
	else if(!checkForOddInst())
	{
		return false;
	}
	else if(parseInt(aPaid)	> parseInt(totalAmount))
	{
		alert('Amount Paid can not be greater than Outstanding Amount. Outstanding Amount is '+totalAmount);
		box=document.getElementById('amtPaid');
		box.focus();
		return false;
	}
	else
	{
		var flag = true;
		if(parseInt(totalAmount) - parseInt(aPaid) == 0){
			if(!confirm("All Installments have been paid. Are you sure to deactivate the loan? "))
				flag = false;
			
		}
		if(flag){
			if (confirm("Are you sure to update the loan data? ")){	
				var orderNo	=document.getElementById("recoveryOrderNo").value;
				var	orederDate	=document.recoveryChallan.recoveryOrederDate.value;
				var challanNo = document.getElementById("challanNo").value;
				var challanDate = document.recoveryChallan.challanDate.value;
				var amountPaid= document.getElementById("amtPaid").value;
			
				
				var URL='./hrms.htm?actionFlag=insertRecoveryChallan&recoveryOrderNo='+orderNo+'&recoveryOrederDate='+orederDate+'&challanNo='+challanNo+'&challanDate='+challanDate+'&amountPaid='+amountPaid;
				
				 document.recoveryChallan.action = URL;
				
				 document.recoveryChallan.submit();
			}
		}
	}
}

function validateFields()
{
	var aPaid= document.getElementById("amtPaid").value;
	var emi= '${empAmt}';
	
	alert(''+emi);
	alert(''+aPaid%emi);
	if(aPaid == "")
	{
		alert('Please insert amount');
		var box=document.getElementById('amtPaid');
		box.focus();
		return false;
	}
	
	if(!checkForOddInst())
	{
			return false;
	}
	
	return true;
}
function checkForOddInst(){
	
	var crtl= document.getElementById("amtPaid");
	var amt= crtl.value;
	if(parseInt(amt) > 0){
		if(parseInt(amt) < parseInt(minimumAmt)){
			alert('Minimum amount to be entered is ' + minimumAmt) ;
			crtl.focus();
			crtl.value = 0;
			return false;
		}else{
			var emi= '${empAmt}';
			var flag = true;
			if(amt%emi != 0){
				if (( parseInt(amt) >= parseInt('${customVO.oddInstallAmount}') &&  ( (parseInt(amt) - (parseInt('${customVO.oddInstallAmount}' ))) % parseInt(emi) != 0 ) )
					||		
					( parseInt(amt) < parseInt('${customVO.oddInstallAmount}') ) ){
						alert("Amount to be paid should be either in multiple of EMI Only or in multiple of EMI + Odd Installment Amount value. ");
						crtl.focus();
						crtl.value = 0;
						flag = false;
						return false;
					}else{
						var tempAmt = amt;
						var totalInstallments = 0;
						while (parseInt(tempAmt) > parseInt(minimumAmt)){
							totalInstallments = totalInstallments + 1;
							tempAmt = parseInt(tempAmt) - parseInt(minimumAmt) ;
						}
						var oddInstNumber = '${customVO.oddInstallNo}';
						var installmentsRecvd = '${customVO.lastInstallRecoverd}';
						if(parseInt(oddInstNumber) > parseInt(parseInt(installmentsRecvd) + parseInt(totalInstallments)) ||  parseInt(installmentsRecvd) > parseInt(oddInstNumber) ){
							alert("Amount entered contains " + totalInstallments + " installments. Out of which none is an odd installment. So please enter the amount in multiple of EMI Only.");
							crtl.focus();
							crtl.value = 0;
							return false;
						}
					}
					
			}else {
				var instPaid = amt/emi;
				var oddInstNumber = '${customVO.oddInstallNo}';
				var installmentsRecvd = '${customVO.lastInstallRecoverd}'; 
				if( parseInt(installmentsRecvd) < parseInt(oddInstNumber)  && parseInt(oddInstNumber) <= parseInt(parseInt(installmentsRecvd) + parseInt(instPaid))){
					if(  parseInt(amt) <  parseInt('${customVO.oddInstallAmount}')  ){
						alert("Amount entered contains " + instPaid + " installments. Out of which odd installment number is " + oddInstNumber + ". So please enter the amount in multiple of EMI + Odd Installment Amount value.");
						crtl.focus();
						crtl.value = 0;
						return false;
					}
				} 
			}
		}
		return true;
	}else{
		return false;
	}
}
</script>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="request"/>

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" >
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><fmt:message key="RC.rcvry" bundle="${commonLables}"/></a></li>
		</ul>
	</div>
	<br/>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<hdiits:form name="recoveryChallan" validate="true" method="POST" action="" encType="text/form-data">
	



	<table width="100%" align="center">
	<tr></tr><tr></tr><tr></tr>
	<tr align="left">  
		
		 <td width="10%" >
		 <label id="empName">Employee Name:</label>
		 <b><c:out value="${customVO.empName}"/></b></td>
		
		 <td width="10%" colspan="4" >
		 <label id="designation">Designation:</label>
		 <b><c:out value="${customVO.designation}"/></b></td>   
	</tr>
	<tr bgcolor="#BD6C03">  
		
		 <td width="20%"><b>Loan Type</b></td>
		 <td width="20%"><b>Sanction Order Date</b></td>
		 <td width="20%"><b>Amount Disbursed</b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
		 <td width="20%"><b>Current Recovery Of</b></td>
		 <td width="20%"><b>Total No Of Installments</b></td>  
	</tr>
	<tr style="background-color:#bebebe">  
		
		 <td width="20%"><b><c:out value="${customVO.loanName}"/></b></td>
		 <td width="20%"><b><c:out value="${customVO.sanctionOrderDate}"/></b></td>
		 
		 <td width="20%"><b><c:out value="${customVO.loanAmount}"/></b></td>
		 <td width="20%"><b><c:out value="${customVO.recoveryType}"/></b></td>
		 <td width="20%"><b><c:out value="${customVO.totalInstallments}"/></b></td>  
	</tr>
<tr></tr>
	<tr bgcolor="#BD6C03" >
<td class="fieldLabel" width="100%" colspan="5" align="center" >

 <font color="#ffffff" size="2">
<strong>Recovery of <c:out value="${customVO.recoveryType}"/></strong></font></td></tr> </table>
	<br>
	
	

	
	<table  width="60%" align="center" border="1" cellpadding="1" cellspacing="1">
		<tr>
			<td colspan="6">
				<font style="color: red;" >Note: Please re-generate the bill after loan data has been updated. Failing to do so would cause data discrepency.</font>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<font style="color: red;" >Note: Multiple installmens scheduled to be recovered for the employee would get reset if the loan data has been updated.</font>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<font style="color: red;" >Note: Loan would get deactivated if all the installments have been paid.</font>
			</td>
		</tr>	
	    <tr style="background-color:#bebebe">
			<td><hdiits:caption captionid="RC.loanAmtDis" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td width="1%">:</td>
			<td><hdiits:number name="loanAmtDis"   style="text-align:right;background-color:#fffafa"  default="${customVO.loanAmount}" caption="loanAmtDis"  validation="txt.isnumber"  maxlength="10"   size="10" readonly="true"/>  </td>
			<td></td>
			<td></td><td></td>
	    </tr>
	    
	    <tr style="background-color:#bebebe">
			<td><hdiits:caption captionid="RC.totalInstall" bundle="${commonLables}"/></td>
			<td width="1%">:</td>
			<td><hdiits:number name="totalInst"  caption="totalInst"  style="text-align:right;background-color:#fffafa" default="${customVO.totalInstallments}" validation="txt.isnumber"  maxlength="3"   size="7" readonly="true"/> </td>
			
			<td><hdiits:caption captionid="RC.installAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td width="1%">:</td>
			<td><hdiits:number name="intstallAmt"  style="text-align:right;background-color:#fffafa" caption="intstallAmt" default="${customVO.installmentAmount}"  validation="txt.isnumber"  maxlength="10"  size="10" readonly="true"/> </td>
	    </tr>
	    
	   
	    
	    <tr style="background-color:#bebebe">
			<td><hdiits:caption captionid="RC.oddInstallNo" bundle="${commonLables}"/></td>
			<td width="1%">:</td>
			<td><hdiits:number name="oddInstallNo"  style="text-align:right;background-color:#fffafa" caption="oddInstallNo" default="${customVO.oddInstallNo}" validation="txt.isnumber"  maxlength="10"   size="7" readonly="true" />  </td>
			
			<td><hdiits:caption captionid="RC.oddInstallAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td width="1%">:</td>
			<td><hdiits:number name="oddInstallAmt"  caption="oddInstallAmt" style="text-align:right;background-color:#fffafa" default="${customVO.oddInstallAmount}" validation="txt.isnumber"  maxlength="10"   size="10"  readonly="true" />  </td>
	    </tr>	   
	    
	  
	    <tr style="background-color:#bebebe">
			<td><hdiits:caption captionid="RC.lastInstNoRecoverd" bundle="${commonLables}"/></td>
			<td width="1%">:</td>
			<td><hdiits:number name="lastInstallRecoverd"   style="text-align:right;background-color:#fffafa" default="${customVO.lastInstallRecoverd}" caption="lastInstallRecoverd" validation="txt.isnumber"  maxlength="10" size="7" readonly="true" /> </td>
	   
				<td><hdiits:caption captionid="RC.outstandingAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
				<td width="1%">:</td>
				<td><hdiits:number name="txtOutstandingPrinAmt"  style="text-align:right;background-color:#fffafa" default="${customVO.outstadingAmount}" caption="EL.outstandingAmt"  maxlength="10"  size="10" readonly="true" />
				<label>as on</label>
				<hdiits:number name="asOnDate"  style="text-align:right;background-color:#fffafa" default="${customVO.asOnDate}" caption="asOnDate"  maxlength="10"  size="10" readonly="true" />  </td>
				
				
				
				
	    </tr>
    
	    <tr style="background-color:#bebebe">
			<td><hdiits:caption captionid="RC.currPayMonth" bundle="${commonLables}"/></td>
			<td width="1%">:</td>
			<td colspan="4"><hdiits:text style="background-color:#fffafa"  name="currPayMonth"  default="${customVO.currPayMonth}" caption="currPayMonth"  maxlength="20"  size="15" readonly="true"/> </td>
			
	    </tr>
	    
	    <tr>
			<td><hdiits:caption captionid="RC.recryOrderNo" bundle="${commonLables}"/></td>
			<td width="1%">:</td>
			<td><hdiits:text name="recoveryOrderNo" id="recoveryOrderNo" style="text-align:right"  caption="recoveryOrderNo"    size="20" maxlength="20" mandatory="true"/> </td>
	  
			<td><hdiits:caption captionid="RC.recryOrderDate" bundle="${commonLables}"/></td>
			<td width="1%">:</td>
			<td><hdiits:dateTime captionid="recoveryOrederDate"  name="recoveryOrederDate"   validation="txt.isrequired,txt.isdt" mandatory="true" /></TD>	
		</tr> 
	
		
		
	    <tr>
	    	<td><hdiits:caption captionid="RC.challanNo"  bundle="${commonLables}"/></td>
	    	<td width=1%">:</td>
			<td><hdiits:text name="challanNo" id="challanNo"  tabindex="9" style="text-align:right" caption="challanNo"    size="10" mandatory="true" /> </td>
	    	
	    
	    	<td><hdiits:caption captionid="RC.challanDate" bundle="${commonLables}"/></td>
	    	<td width="1%">:</td>
			<td><hdiits:dateTime captionid="challanDate"  name="challanDate" validation="txt.isrequired,txt.isdt" mandatory="true"  /></TD>
			
	    </tr>	    
	   <tr>
	   <td><hdiits:caption captionid="RC.amountPaid" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
	   <td width="1%">:</td>
			<td><hdiits:text name="amtPaid" id="amtPaid"   style="text-align:right" caption="amtPaid"  maxlength="10"  size="10" mandatory="true"/> </td>
	   </tr>
		  <tr>
	  	 <td>
	  	 <hdiits:hidden name="recoveryType" default="${customVO.recoveryType}"/>
	  	 <hdiits:hidden name="lnEmpId" default="${loanEmpId}"/>
	  	 </td>
	  	 <td>
	  	 <hdiits:hidden name="sanctionOrderDate" default="${customVO.sanctionOrderDate}"/>
	  	 <hdiits:hidden name="empNamehidden" default="${customVO.empName}"/>
	  	 
	  	 </td>
	  	 <td>
	  	 <hdiits:hidden name="loanType" default="${customVO.loanName}"/>
	  	 <hdiits:hidden name="designationHidden" default="${customVO.designation}"/>
	  	 
	  	 </td>
	   </tr>
		
		<c:if test="true">	
	<tr>
	<td colspan="6"><br></td></tr>
	
	<tr>
	<td colspan="6" align="center" >
		<hdiits:button name="btnsubmit1" id="btnsubmit1" type="button" onclick="submitForm()" value="Submit"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:button name="btnClose1" type="button" value="Close" onclick="onClose()" />
	</td>
</tr>
	
	</c:if>	
		
		
	</table>
	</hdiits:form>
 	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
		if(parseInt('${outStandingAmt}') == 0){
			document.getElementById('recoveryOrderNo').readOnly = true;
			document.getElementById('recoveryOrederDate').readOnly = true;
			document.getElementById('challanNo').readOnly = true;
			document.getElementById('challanDate').readOnly = true;
			document.getElementById('amtPaid').readOnly = true;
			document.getElementById('btnsubmit1').style.display = "none";
		}
	</script>

	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

 