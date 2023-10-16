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
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="empName" value="${resValue.empList}"> </c:set>
<c:set var="loanList" value="${resValue.loanList}"> </c:set>
<c:set var="actionList" value="${resValue.actionList}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<c:set var="principalRecoveredAmt" value="${resValue.principalRecoveredAmt}" ></c:set>
<c:set var="pricipalRecoveredInt" value="${resValue.pricipalRecoveredInt}" ></c:set>
<c:set var="intRecoveredAmt" value="${resValue.intRecoveredAmt}" ></c:set>
<c:set var="intRecoveredInt" value="${resValue.intRecoveredInt}" ></c:set>

<!-- added by ravysh -->

<c:set var="FromBasicDtlsNew" value="${resValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resValue.otherId}" ></c:set>

<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>
<c:set var="empAllRecFlag" value="${resValue.empAllRecFlag}" ></c:set>
<c:set var="empId" value="${resValue.empId}" ></c:set>
<c:set var="isPrincipalRecovered" value="0" />

<c:set var="voucherDate" value="${resValue.voucherDate}" ></c:set>
<c:set var="loanSancOrderdate1" value="${resValue.loanSancOrderdate1}" ></c:set>
<c:set var="ordrDate" value="${resValue.ordrDate}" ></c:set>
<c:set var="ordrNo" value="${resValue.ordrNo}" ></c:set>
<c:set var="gpf_dcps" value="${resValue.gpf_dcps}"/>


<c:choose>
	<c:when test="${actionList.loanPrinAmt ne 0  || principalRecoveredAmt ne 0 }">
		<c:set var="isPrincipalRecovered" value="0" />
	</c:when>
	
	<c:otherwise>
		<c:set var="isPrincipalRecovered" value="1" />
		<script>
		
		</script>
		
	</c:otherwise>
</c:choose>
	
<script language="javascript">

//alert('actionList.loanPrinAmt'+'${actionList.loanPrinAmt}');
//alert('principalRecoveredAmt'+'${principalRecoveredAmt}');

function isNegative(interestAmt)
{
	var loanId=document.empLoan.loanName.value;
	if(loanId=="choose")
	{
		alert("Please select the loan type first");
		document.empLoan.loanName.focus();
		return false;
	}
	else
	{
		if(eval(interestAmt.value)<0 || interestAmt.value=="")
		{
			alert(interestAmt.name+" should not be negative or zero");
			interestAmt.value='';
			interestAmt.focus();
			return false;
		}
		else
		{
			return true;
		}
	}
}
function comparePrincipal(interestAmt)
{
		
		principalAmt= document.empLoan.principalAmt.value;
		if(eval(interestAmt.value) > eval(principalAmt))
			{
				alert("The interest Amount should be less than or equal to principal Amount");
				interestAmt.value='';
				interestAmt.focus();
				return false;
			}
			else
			{
				return true;
			}
	
}
function checkAmount(newLoan)
{
	var loanAmt=newLoan.value;	
	var loanId=document.empLoan.loanName.value;
	var retValue=true;
	if(loanId=="choose")
	{
		alert("Please select the loan type first");
		document.empLoan.loanName.focus();
		retValue=false;
	}
	else
	{
		if(loanAmt!="" && eval(loanAmt)>0)
		{
			
			try {   
					xmlHttp=new XMLHttpRequest();
	   		}
			catch(e){    // Internet Explorer    
				try {
		     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	    	 	}
		    	catch (e) {
			    	try {
		            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	    	   		}
				    catch (e) {
				        alert("Your browser does not support AJAX!");        
				        retValue=false;        
				    }
				}
			}
			var url = "hrms.htm?actionFlag=getloanAdvDataByID&loanId="+loanId;  	
	        
	    xmlHttp.onreadystatechange = function() {
	   	
			if (xmlHttp.readyState == 4) { 
			 			
				if (xmlHttp.status == 200) {	
						
				    var principalAmt;
	  
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('loanAdvMapping');	
					
					if(loanAdvMapping.length != 0) {	
						principalAmt=loanAdvMapping[0].childNodes[0].text;
	
							principalAmt = parseFloat(principalAmt)	;
							loanAmt = parseFloat(loanAmt);
				
							if(eval(loanAmt) > eval(principalAmt)){
								alert("Loan Amount must not be more than "+principalAmt);
								document.empLoan.principalAmt.value='';
								document.empLoan.principalAmt.focus();
								retValue=false;
							}
							
							else{
							
							    if(fillEMI(newLoan)) 
								 {   
									retValue=true;
									}
									else
									{
										newLoan.value='';
										newLoan.focus();							
										retValue=false;
									}
							}
					}
					
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
		}
	}
}




function checkInstallment(newInstallment)
{

	var loanInstallment=newInstallment.value;	
	var loanId=document.empLoan.loanName.value;
	var retValue=true;
	if(loanId=="choose")
	{
		alert("Please select the loan type first");
		document.empLoan.loanName.focus();
		retValue=false;
	}
	else
	{
		if(loanInstallment!="" && eval(loanInstallment)>0)
		{
			try {   
					xmlHttp=new XMLHttpRequest();
	   		}
			catch(e){    // Internet Explorer    
				try {
		     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	    	 	}
		    	catch (e) {
			    	try {
		            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	    	   		}
				    catch (e) {
				        alert("Your browser does not support AJAX!");        
				        retValue=false;      
				    }
				}
			}
			var url = "hrms.htm?actionFlag=getloanAdvDataByID&loanId="+loanId;  	
	        
	    xmlHttp.onreadystatechange = function() {		
			if (xmlHttp.readyState == 4) {     			
				if (xmlHttp.status == 200) {	
	
				    
				    var installment;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('loanAdvMapping');	
					
					if(loanAdvMapping.length != 0) {	
						
						installment = loanAdvMapping[0].childNodes[1].text;	
		
							installment = parseFloat(installment);
							loanInstallment = parseFloat(loanInstallment);

							if(eval(loanInstallment) > eval(installment)){
								alert("Principal Installment  must not more then "+installment);
								document.empLoan.principalInstNo.value='';
								document.empLoan.principalInstNo.focus();
								retValue=false;
							}
							
							else{		
								 if(fillEMI(newInstallment)) 
								 {   
									retValue=true;
								}
								else
								{
									newInstallment.value='';
									newInstallment.focus();							
									retValue=false;	
								}
							}
					}
					
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
		}
		else
			{
				alert("Loan Installment number should not be negative or zero");
				newInstallment.value='';
				newInstallment.focus();
				return false;
			}
		
	}
}

function chkFunc()
{
	var empId=document.empLoan.empName.value;

	var newLoanType=document.getElementById("loanName");
	var loanId=newLoanType.value;
	var retValue=true;
	if(empId=="" || loanId=="choose")
	{
		if(empId=="")
		{
			alert("Please search the employee first");
			return false;
		}
		if(loanId=="choose")
		{
			alert("Please select the loan type first");
			newLoanType.options[0].selected="selected";
			newLoanType.focus();
			return false;
		}
	}
	else
	{
		try 
		{   
			xmlHttp=new XMLHttpRequest();
	   	}
		catch(e)
		{    
			// Internet Explorer    
			try 
			{
		    	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	    	} 
		    catch (e) 
		    {
			 	try 
			 	{
		        	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	    	   	}
				catch (e) 
				{
					alert("Your browser does not support AJAX!");        
				    return false;        
				}
			}
		}
		var url = "hrms.htm?actionFlag=getloanAdvDataByID&loanId="+loanId+"&empId="+empId+"&chkEmpLoan=y";  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('loanAdvMapping');	
					
					if(loanAdvMapping.length != 0) {	
						LoanNo = loanAdvMapping[0].childNodes[0].text;	
						if(LoanNo==0){
							alert("Loan is already sanctioned to the Employee selected");
							newLoanType.options[0].selected="selected";
							newLoanType.focus();
							retValue=false;
						}
						else
						{
							retValue=true;
						}
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	}
}

function chkPrinEmiAmt() {
	
	var loanPrinEmiAmt = document.empLoan.loanPrinEmiAmt.value;
	//alert("The Value of Principal EMI Amount is:-"+loanPrinEmiAmt);
	var loanPrinAmt = document.empLoan.principalAmt.value;
	//alert("The Value of Principal Amount is:-"+loanPrinAmt);	
	var loanPrinInstNo = document.empLoan.principalInstNo.value;
	//alert("The Value of Principal Installment No is:-"+loanPrinInstNo);	
	
	if(eval(loanPrinAmt) <= 0 || eval(loanPrinAmt)==null){
		alert("Enter Positive Principal Amount");
		
		document.empLoan.loanPrinEmiAmt.value='';
		document.empLoan.principalAmt.value='';
		document.empLoan.principalAmt.focus();
		
		return false;
	}
	if(eval(loanPrinInstNo) <= 0 || loanPrinInstNo==null){
		alert("Enter Positive Principal Installment Number");
		document.empLoan.loanPrinEmiAmt.value='';
		document.empLoan.principalInstNo.focus();
		return false;
	}
	var newLoanPrinAmt = loanPrinEmiAmt * loanPrinInstNo;		
	if(eval(newLoanPrinAmt) != eval(loanPrinAmt)) {
		alert('Enter Principal EMI Amount compatible with the Priclipal Amount & Principal Installment Number');
		document.empLoan.loanPrinEmiAmt.value='';
		document.empLoan.loanPrinEmiAmt.focus();
		return false;
	}
	return true;
}

function chkIntEmiAmt() {  
	var loanIntEmiAmt = document.empLoan.loanIntEmiAmt.value;
	var loanIntAmt = document.empLoan.interestAmt.value;
	var loanIntInstNo = document.empLoan.interestInstNo.value;
	if(eval(loanIntAmt) <= 0 || loanIntAmt==null){
		alert("Enter Positive Interest Amount");
		document.empLoan.loanIntEmiAmt.value='';
		document.empLoan.interestAmt.focus();
		return false;
	}
	if(eval(loanIntInstNo) <= 0 || loanIntInstNo==null){
		alert("Enter Positive Interest Installment Number");
		document.empLoan.loanIntEmiAmt.value='';
		document.empLoan.interestInstNo.focus();
		return false;
	}	
	var newLoanIntAmt = loanIntEmiAmt * loanIntInstNo;	
	if(eval(newLoanIntAmt) != eval(loanIntAmt)) {
		alert('Enter Interest EMI Amount compatible with the Interest Amount & Interest Installment Number');
		document.empLoan.loanIntEmiAmt.value='';
		document.empLoan.loanIntEmiAmt.focus();
		return false;
	}
}



function updateActivate(activateFlag,previousRadio)
{

	var msg = "Do u really want to change?";
	if( parseInt(activateFlag.value) == 0 )
		msg = "Are you sure you want to deactivate the loan? ";
	else if( parseInt(activateFlag.value) == 1 )
		msg = "Are you sure you want to activate the loan? ";
	else if( parseInt(activateFlag.value) == 2 )
		msg = "Are you sure you want to temporary stop the loan? ";
	
	if( confirm(msg) )
	{
		document.empLoan.loanActivateFlag.value=activateFlag.value;
	}
	else{
		document.forms[0].radActivateFlag[previousRadio].checked=true;
	}

}

function changeLoanRecoveryMode(control)
{
	document.empLoan.loanRecoveryModeFlag.value=control.value;
	var loanRecModeFromDB = '${actionList.mulLoanRecoveryMode}';
	
	if(control.value == 1){
		
		document.getElementById('mulRecRemarksTD').style.display = "";
		document.getElementById('mulRecRemarksCaptionTD').style.display = "";

		
		document.getElementById('intallmentsToRecoverTD').style.display = "";
		document.getElementById('intallmentsToRecoverCapTD').style.display = "";
		
		document.getElementById('amountToRecoverCapTD').style.display = "";
		document.getElementById('amountToRecoverTD').style.display = "";
		
		if(document.getElementById('pricipalRecoveredNumber'))
			document.getElementById('pricipalRecoveredNumber').readOnly = true;
		if(document.getElementById('interestRecoveredNumber'))
			document.getElementById('interestRecoveredNumber').readOnly = true;
				
		
		if(document.getElementById('txtOutstandingPrinAmt'))
			document.getElementById('txtOutstandingPrinAmt').value = document.getElementById('principalAmt').value - document.getElementById('principalRecoveredCalcAmount').value - document.getElementById('amountToRecover').value;
		if(document.getElementById('txtOutstandingIntAmt'))
			document.getElementById('txtOutstandingIntAmt').value = document.getElementById('interestAmt').value - document.getElementById('interestRecoveredCalcAmount').value - document.getElementById('amountToRecover').value;
		
		
	}else{
		
		alert("Any multiple loans entered for the current employee will get erased if loan recovery mode is set to Normal Recovery. ");
		
		document.getElementById('mulRecRemarksTD').style.display = "none";
		document.getElementById('mulRecRemarksCaptionTD').style.display = "none";
		
		document.getElementById('intallmentsToRecoverTD').style.display = "none";
		document.getElementById('intallmentsToRecoverCapTD').style.display = "none";
		
		document.getElementById('amountToRecoverCapTD').style.display = "none";
		document.getElementById('amountToRecoverTD').style.display = "none";
		
		/*document.getElementById('mulRecRemarks').value = "";
		document.getElementById('intallmentsToRecover').value = "";
		document.getElementById('amountToRecover').value = "";*/
		
		
		
		
		if(document.getElementById('pricipalRecoveredNumber'))
			document.getElementById('pricipalRecoveredNumber').readOnly = false;
		if(document.getElementById('interestRecoveredNumber'))
			document.getElementById('interestRecoveredNumber').readOnly = false;
		
		if(document.getElementById('txtOutstandingPrinAmt'))
			document.getElementById('txtOutstandingPrinAmt').value = document.getElementById('principalAmt').value - document.getElementById('principalRecoveredCalcAmount').value;
		if(document.getElementById('txtOutstandingIntAmt'))
			document.getElementById('txtOutstandingIntAmt').value = document.getElementById('interestAmt').value - document.getElementById('interestRecoveredCalcAmount').value;
		
	}
	
	if(loanRecModeFromDB == 0){
		document.getElementById('mulRecRemarks').value = "";
		document.getElementById('intallmentsToRecover').value = "0";
		document.getElementById('amountToRecover').value = "0";
	}
}


function submitForm()
{
	var flag = true;
	if ( document.empLoan.UpdateOrderNo.value == '')
	{
		alert('Please enter update order no.');
		flag = false;
	} 
	else if ( document.empLoan.updateOrderDate.value == '')
	{
		alert('Please enter update date.');
		flag = false;
	} 
	if(deactivateLoan){
		if(confirm("All installments have been recovered. Loan will now be deactivated. Click OK to continue. "))
		{
			alert("Deactivating Loan");
			document.empLoan.loanActivateFlag.value = 0;
			flag = true;
		}else{
			flag = false;
		}
	}

	//alert("===> flag :: "+flag);
	
  if(flag){
	  var ordrNo = document.empLoan.UpdateOrderNo.value;
	  var ordrDat= document.empLoan.updateOrderDate.value;
	  //alert(ordrNo +" "+ordrDat);
  	var uri = "./hrms.htm?actionFlag=";
  	if("${empAllRec}"=='true')
    	var url = uri + "insertEmpLoanDtls&empLoanId=${actionList.empLoanId}&edit=Y&empAllRec=true&ordrNo="+ordrNo+"&ordrDat="+ordrDat;
  	else
   		var url = uri + "insertEmpLoanDtls&empLoanId=${actionList.empLoanId}&edit=Y&empAllRec=false&ordrNo="+ordrNo+"&ordrDat="+ordrDat;
 	document.empLoan.action = url;
 	document.empLoan.submit();
	return true;
  }

}



function empLoanFromBasicDtls()
{

	
if(chekcAmonuntValue())
{
	//alert("in action flag........... ");
	var uri = "./hrms.htm?actionFlag=";
	 var url = uri + "insertEmpLoanDtls&empLoanId=${actionList.empLoanId}&edit=Y&empAllRec=false&FromBasicDtlsNew=YES&otherId=${otherId}";
	 document.empLoan.action = url;
	 document.empLoan.method = "post";
	 document.empLoan.submit();

}
}


function chekcAmonuntValue()
{
	
	var principalAmt=eval(document.empLoan.principalAmt.value);
	var principalInstNo=eval(document.empLoan.principalInstNo.value);
	var interestAmt=eval(document.empLoan.interestAmt.value);
	var interestInstNo=eval(document.empLoan.interestInstNo.value);
	var loanPrinEmiAmt=eval(document.empLoan.loanPrinEmiAmt.value);
	var loanIntEmiAmt=eval(document.empLoan.loanIntEmiAmt.value);
	//var radActivateFlag = document.forms[0].radActivateFlag.value;

	var radActivateFlagval=-1;
	
	//var radActivateFlag0=  document.forms[0].radActivateFlag[0].checked;
	//var radActivateFlag1=  document.forms[0].radActivateFlag[1].checked;
	//var radActivateFlag2=  document.forms[0].radActivateFlag[2].checked;
	
	//alert("====> principalAmt :: "+principalAmt+"::principalInstNo :: "+principalInstNo+"::radActivateFlag :: "+radActivateFlag);
	//alert("====> radActivateFlag0 :: "+radActivateFlag0+"::radActivateFlag1 :: "+radActivateFlag1+"::radActivateFlag2 :: "+radActivateFlag2);

	if(document.forms[0].radActivateFlag[0].checked)
	{
		 radActivateFlagval=  document.forms[0].radActivateFlag[0].value;
	}
	else if(document.forms[0].radActivateFlag[1].checked)
	{
		 radActivateFlagval=  document.forms[0].radActivateFlag[1].value;
	}
	else if(document.forms[0].radActivateFlag[2].checked)
	{
		 radActivateFlagval=  document.forms[0].radActivateFlag[2].value;
	}

	
	if(((principalAmt==0 || principalInstNo ==0 || loanPrinEmiAmt ==0) && (interestAmt ==0 || interestInstNo==0 || loanIntEmiAmt ==0) && (radActivateFlagval!= 0)) )
	{
	 alert('Please Enter Proper Loan Value');
	 return false;
	}
	
	else if((${isPrincipalRecovered}==0) && ((principalAmt!='' && principalInstNo !='' && loanPrinEmiAmt !='') && (interestAmt !='' && interestInstNo!='' && loanIntEmiAmt !='')))
	{
	 alert('Please Enter Principal Or Interest not Both Value');
	 return false;
	}
	else if(eval(loanIntEmiAmt)>eval(interestAmt) || eval(loanPrinEmiAmt)>eval(principalAmt))
	{
		 alert('Emi-Amount should Not be Greater than Amount');
	 	return false
	}
	else
		return true;
}

function disableComponent()
{
//alert(("${actionList.loanPrinAmt==0  || actionList.loanPrinInstNo==0  ||  actionList.loanPrinEmiAmt==0}"));

	if((("${actionList.loanPrinAmt==0  || actionList.loanPrinInstNo==0  ||  actionList.loanPrinEmiAmt==0}")=='true'))
	{
	
		document.empLoan.principalAmt.readOnly=true;
		document.empLoan.loanPrinEmiAmt.readOnly=true;
		document.empLoan.principalInstNo.readOnly=true;
	
	}
	else
	{
		document.empLoan.loanIntEmiAmt.readOnly=true;
		document.empLoan.interestAmt.readOnly=true;
		document.empLoan.interestInstNo.readOnly=true;
	
	}

}
var deactivateLoan = false;
function popDataFrmPrinInstToRecover(crtl){
	var installmentsToRecover = crtl.value;
	var pricipalRecoveredNumber = document.getElementById('pricipalRecoveredNumber').value;
	var principalInstNo = document.getElementById('principalInstNo').value;
	var flag = true;
	if ( (parseInt(installmentsToRecover) + parseInt(pricipalRecoveredNumber)) > parseInt(principalInstNo)){
		alert('Multiple Installments to recover should not be greater then total Installments. Total installments are : ' + principalInstNo + " and total installments recovered are : " + pricipalRecoveredNumber);
		crtl.value=0;
		crtl.focus();
		flag = false;
	}
	if(flag){
		var emiAmt = document.getElementById('loanPrinEmiAmt').value;
		var oddInstNumber = document.getElementById('loanOddinstno').value;
		var oddInstAmt = document.getElementById('loanOddinstAmt').value;
		var principalAmt = document.getElementById('principalAmt').value;
		
		
		var tempAmt = 0;
		var finalAmt = tempAmt ;
		var outStandingAmt = parseInt(principalAmt) - parseInt(finalAmt);
		tempAmt = parseInt(tempAmt) + ( parseInt(installmentsToRecover) * parseInt(emiAmt));
		
		if( parseInt(pricipalRecoveredNumber) < parseInt(oddInstNumber) && parseInt(oddInstNumber) <= ( parseInt(pricipalRecoveredNumber) + parseInt(installmentsToRecover) ) ){
			tempAmt = parseInt(tempAmt) - parseInt(emiAmt) + parseInt(oddInstAmt);
		}
		
		finalAmt = tempAmt;
		outStandingAmt = parseInt(principalAmt) - parseInt(finalAmt) - parseInt(document.getElementById('principalRecoveredCalcAmount').value);
		
		document.getElementById('amountToRecover').value = finalAmt;
		document.getElementById('txtOutstandingPrinAmt').value = outStandingAmt;
		
	}

}
function popDataFrmInterestInstToRecover(crtl){
	var installmentsToRecover = crtl.value;
	var interestRecoveredNumber = document.getElementById('interestRecoveredNumber').value;
	var interestInstNo = document.getElementById('interestInstNo').value;
	var flag = true;
	if ( (parseInt(installmentsToRecover) + parseInt(interestRecoveredNumber)) > parseInt(interestInstNo)){
		alert('Multiple Installments to recover should not be greater then total Installments. Total installments are : ' + interestInstNo + " and total installments recovered are : " + interestRecoveredNumber);
		crtl.value=0;
		crtl.focus();
		flag = false;
	}
	if(flag){
		var emiAmt = document.getElementById('loanIntEmiAmt').value;
		var oddInstNumber = document.getElementById('loanOddinstno').value;
		var oddInstAmt = document.getElementById('loanOddinstAmt').value;
		var interestAmt = document.getElementById('interestAmt').value;
		
		
		var tempAmt = 0;
		var finalAmt = tempAmt ;
		var outStandingAmt = parseInt(interestAmt) - parseInt(finalAmt);
		tempAmt = parseInt(tempAmt) + ( parseInt(installmentsToRecover) * parseInt(emiAmt));
		
		if( parseInt(interestRecoveredNumber) < parseInt(oddInstNumber) && parseInt(oddInstNumber) <= ( parseInt(interestRecoveredNumber) + parseInt(installmentsToRecover) ) ){
			tempAmt = parseInt(tempAmt) - parseInt(emiAmt) + parseInt(oddInstAmt);
		}
		
		finalAmt = tempAmt;
		outStandingAmt = parseInt(interestAmt) - parseInt(finalAmt) - parseInt(document.getElementById('interestRecoveredCalcAmount').value);
		
		document.getElementById('amountToRecover').value = finalAmt;
		document.getElementById('txtOutstandingIntAmt').value = outStandingAmt;
	}

}
function popDataFrmPrinRecNumber(crtl){
	var principalRecInstallments = crtl.value;
	var principalInstNo = document.getElementById('principalInstNo').value;

	var flag = true;
	/*if(parseInt(principalRecInstallments) < parseInt('${pricipalRecoveredInt}')){
		alert('Recovered Installments should not be less then current recovered Installments (current is ' + '${pricipalRecoveredInt}' + ' )');
		crtl.value='${pricipalRecoveredInt}';
		if(!crtl.readOnly)
			crtl.focus();
		flag = false;
	}else*/ 
	if (parseInt(principalRecInstallments) > parseInt(principalInstNo)){
		alert('Recovered Installments should not be greater then total Installments to recover. Total installments are : ' + principalInstNo );
		crtl.value='${pricipalRecoveredInt}';
		if(!crtl.readOnly)
			crtl.focus();
		flag = false;
	}
	
	if(flag){
		var emiAmt = document.getElementById('loanPrinEmiAmt').value;
		var oddInstNumber = document.getElementById('loanOddinstno').value;
		var oddInstAmt = document.getElementById('loanOddinstAmt').value;
		var principalAmt = document.getElementById('principalAmt').value;
		if(principalRecInstallments == 0)
		{
			
			document.getElementById('principalRecoveredCalcAmount').value = 0;
			document.getElementById('txtOutstandingPrinAmt').value = principalAmt;
		}
		else
		{
		//Logic Goes Here
		//1 . ( principalRecInstallments - '${pricipalRecoveredInt}' ) * EMI = temp recovered (if no Odd Comes in between )
		//2 . principalRecInstallments >= Odd Inst Number > '${pricipalRecoveredInt}' then temp amt recovered = temp - emi + odd inst amt
		//3 . final = temp
		//4 . outstanding = total - final;
		
		
		
		
		var tempAmt = '${principalRecoveredAmt}';
		var finalAmt = tempAmt ;
		var outStandingAmt = parseInt(principalAmt) - parseInt(finalAmt);
		tempAmt = parseInt(tempAmt) + (( parseInt(principalRecInstallments) - parseInt('${pricipalRecoveredInt}') ) * (parseInt(emiAmt)));
		
		if(parseInt(principalRecInstallments) >= parseInt(oddInstNumber) &&  parseInt(oddInstNumber) > parseInt('${pricipalRecoveredInt}')){
			tempAmt = parseInt(tempAmt) - parseInt(emiAmt) + parseInt(oddInstAmt);
		}
		
		finalAmt = tempAmt;
		outStandingAmt = parseInt(principalAmt) - parseInt(finalAmt);
		
		document.getElementById('principalRecoveredCalcAmount').value = finalAmt;
		document.getElementById('txtOutstandingPrinAmt').value = outStandingAmt;
		
		if(parseInt(principalInstNo) - parseInt(principalRecInstallments) == 0 ){
			deactivateLoan = true;
		}else{
			deactivateLoan = false;
		}
		}
	}
}

function popDataFrmInteRecNumber(crtl){
	var interestRecInstallments = crtl.value;
	var interestInstNo = document.getElementById('interestInstNo').value;
	var flag = true;
	/*if(parseInt(interestRecInstallments) < parseInt('${intRecoveredInt}')){
		alert('Recovered Installments should not be less then current recovered Installments (current is ' + '${intRecoveredInt}' + ' )');
		crtl.value='${intRecoveredInt}';
		if(!crtl.readOnly)
			crtl.focus();
		flag = false;
	}else*/ 
	if (parseInt(interestRecInstallments) > parseInt(interestInstNo)){
		alert('Recovered Installments should not be greater then total Installments to recover. Total installments are : ' + interestInstNo );
		crtl.value='${intRecoveredInt}';
		if(!crtl.readOnly)
			crtl.focus();
		flag = false;
	}
	
	if(flag){
		//Logic Goes Here
		//same as of previous Function
		
		var emiAmt = document.getElementById('loanIntEmiAmt').value;
		var oddInstNumber = document.getElementById('loanOddinstno').value;
		var oddInstAmt = document.getElementById('loanOddinstAmt').value;
		var interestAmt = document.getElementById('interestAmt').value;
		
		if(interestRecInstallments == 0)
		{
			document.getElementById('interestRecoveredCalcAmount').value = 0;
			document.getElementById('txtOutstandingIntAmt').value = interestAmt;
		}
		else
		{
		var tempAmt = '${intRecoveredAmt}';
		var finalAmt = tempAmt ;
		var outStandingAmt = parseInt(interestAmt) - parseInt(finalAmt);
		tempAmt = parseInt(tempAmt) + (( parseInt(interestRecInstallments) - parseInt('${intRecoveredInt}') ) * (parseInt(emiAmt)));
		if(parseInt(interestRecInstallments) >= parseInt(oddInstNumber) &&  parseInt(oddInstNumber) > parseInt('${intRecoveredInt}')){
			tempAmt = parseInt(tempAmt) - parseInt(emiAmt) + parseInt(oddInstAmt);
		}
		
		finalAmt = tempAmt;
		outStandingAmt = parseInt(interestAmt) - parseInt(finalAmt);
		document.getElementById('interestRecoveredCalcAmount').value = finalAmt;
		document.getElementById('txtOutstandingIntAmt').value = outStandingAmt;
		
		if(parseInt(interestInstNo) - parseInt(interestRecInstallments) == 0 ){
			deactivateLoan = true;
		}else{
			deactivateLoan = false;
		}
		}
	}
}

</script>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="request"/>

<%--
<hdiits:form name="empLoan" validate="true" method="POST"
	action="javascript:beforeSubmit()" encType="text/form-data">
 --%>
<hdiits:form name="empLoan" validate="true" method="POST"
	 action="javascript:submitForm()" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><fmt:message key="eis.updateloandtls" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin">
	
	<table width="100%" align="center">
	<tr><td></td></tr>
	<tr><td></td></tr>
	<tr bgcolor="#BD6C03" >
<td class="fieldLabel" width="100%" colspan="2" align="center" >

 <font color="#ffffff" size="2">
<strong>Employee Details</strong></font></td></tr> 
<tr><td></td></tr></table>
<font size="2" face="bold">

<table bgcolor="#dddddd" width="100%" align="center">
<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
	<tr> <td width="5%"></td> 
		<td width="20%"><b><fmt:message key="EL.empName"  bundle="${commonLables}" /></b></td>
		<td width="5%"></td>
		 <td width="20%"><b>${actionList.hrEisEmpMst.orgEmpMst.empFname} ${actionList.hrEisEmpMst.orgEmpMst.empMname} ${actionList.hrEisEmpMst.orgEmpMst.empLname}</b></td>
		 <td width="5%"></td>
		 <td width="20%"><b><fmt:message key="GPF.ACC_NO" bundle="${commonLables}"/></b></td>
		 <td width="5%"></td>
		 <td width="20%"><b>${gpf_dcps.value}</b></td>  
	</tr>
	<tr>
		<td width="5%"></td>
		<td width="20%"><b><fmt:message key="eis.grade_lst" bundle="${commonLables}" /></b></td>
		<td width="5%"></td>
		<td width="20%"><b>${actionList.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName}</b></td>
		<td width="5%"></td>
		<td width="20%"><b><fmt:message key="EL.loanName" bundle="${commonLables}" /></b></td>
		<td width="5%"></td>
		<td width="20%"><b>${actionList.hrLoanAdvMst.loanAdvName}</b></td>
	</tr>
	
	

	<table width="100%" align="center">
<tr><td></td></tr>
	<tr bgcolor="#BD6C03" >
<td class="fieldLabel" width="100%" colspan="2" align="center" >

 <font color="#ffffff" size="2">
<strong>Loan Details</strong></font></td></tr> </table>
	</table></font>
	<br>
	
	
	<c:choose>
	<c:when test="${isPrincipalRecovered eq 0}">
	
	
	<table width="60%" align="center">
		<tr>
			<td colspan="4">
				<font style="color: red;" >Note: Please re-generate the bill after loan data has been updated. Failing to do so would cause data discrepency.</font>
			</td>
		</tr>
	   <tr>
	   		<td><hdiits:caption captionid="OT.LoanRecoveryMode" bundle="${commonLables}" /></td>
			<td>
				<hdiits:radio captionid="OT.Normal"  caption="Normal" bundle="${commonLables}"  value="0" onclick="changeLoanRecoveryMode(this)" default="0" name="radLoanRecoveryMode" id="radLoanRecoveryMode" />
				<hdiits:radio captionid="OT.Multiple" caption="Multiple" bundle="${commonLables}"  value="1"  onclick="changeLoanRecoveryMode(this)"  name="radLoanRecoveryMode"  id="radLoanRecoveryMode"/>
				<hdiits:hidden name="loanRecoveryModeFlag" id="loanRecoveryModeFlag" />
			</TD>	
			<td colspan="2"></td>
	   </tr>
	    <tr>
			<td><hdiits:caption captionid="EL.lpAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="principalAmt" default="${actionList.loanPrinAmt}"  style="text-align:right;" readonly="true" caption="principalAmt"  validation="txt.isnumber"  maxlength="10"   size="20" /> </td> 
			<td> Recovered Amount <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font> </td>
			<td><hdiits:number name="principalRecoveredCalcAmount"  id="principalRecoveredCalcAmount" style="text-align:right;" default="${principalRecoveredAmt}" caption="principalRecoveredCalcAmount"  validation="txt.isnumber"     size="20"  readonly="true" />  </td>
	    </tr>
	    
	    <tr >
			<td><hdiits:caption captionid="EL.lpiNo" bundle="${commonLables}" /></td>
			<td><hdiits:number name="principalInstNo" default="${actionList.loanPrinInstNo}" style="text-align:right;" readonly="true" caption="principalInstNo"  validation="txt.isnumber"  maxlength="3"   size="20" /> </td>
			<td>Recovered Installments </td>
			<td><hdiits:number name="pricipalRecoveredNumber"  id="pricipalRecoveredNumber" style="text-align:right;" default="${pricipalRecoveredInt}" caption="pricipalRecoveredNumber"  validation="txt.isnumber"  maxlength="3"   size="20" onblur="javascript:popDataFrmPrinRecNumber(this);" />  </td>
			<script type="text/javascript">
				if(parseInt('${pricipalRecoveredInt}') >= parseInt('${actionList.loanPrinInstNo}')){
					document.getElementById('pricipalRecoveredNumber').readOnly = true;
					deactivateLoan = true;
				}
			</script> 
	    </tr>
	    
	    <tr>
			<td><hdiits:caption captionid="EL.loanPrincipalEmiAmt" bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanPrinEmiAmt" default="${actionList.loanPrinEmiAmt}" style="text-align:right" readonly="true" caption="EL.loanPrincipalEmiAmt" validation="txt.isnumber"  maxlength="10"  size="20" /> </td>
			<td colspan="2"></td>
	    </tr>
	    
	    <tr>
			<td><hdiits:caption captionid="EL.liAmt" bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="interestAmt" default="${actionList.loanInterestAmt}"  style="text-align:right" readonly="true" caption="interestAmt"  validation="txt.isnumber"  maxlength="10"   size="20" />  </td>
			<td>Recovered Amount <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font> </td>
			<td><hdiits:number name="intRecoveredAmtStatic" default="${intRecoveredAmt}"  style="text-align:right" readonly="true" caption="intRecoveredAmtStatic"  validation="txt.isnumber"  maxlength="10"   size="20" /></td>
	    </tr>	   
	    
	    <tr>
			<td><hdiits:caption captionid="EL.liiNo" bundle="${commonLables}" /></td>
			<td><hdiits:number name="interestInstNo" default="${actionList.loanIntInstNo}" style="text-align:right;" caption="interestInstNo"  readonly="true" validation="txt.isnumber"  maxlength="3"   size="20" />  </td>
			<td>Recovered Installments </td>
			<td><hdiits:number name="intRecoveredIntStatic" default="${intRecoveredInt}" style="text-align:right;" caption="intRecoveredIntStatic"  readonly="true" validation="txt.isnumber"  maxlength="3"   size="20" /> </td>
	    </tr>	
	    	    
	    <tr>
			<td><hdiits:caption captionid="EL.loanInterestEmiAmt" bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanIntEmiAmt" default="${actionList.loanIntEmiAmt}"  style="text-align:right" readonly="true" caption="EL.loanInterestEmiAmt" validation="txt.isnumber"  maxlength="10" size="20" /> </td>
			<td colspan="2"></td>
	    </tr>
	    
	    <tr>
			<td><hdiits:caption captionid="EL.outstandingAmt" bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="txtOutstandingPrinAmt" default="${actionList.loanPrinAmt - principalRecoveredAmt - actionList.mulLoanAmtRecvd}"  style="text-align:right; " caption="EL.outstandingAmt"  maxlength="20"  size="20" readonly="true" /> </td>
			<td style="display: none" id="mulRecRemarksCaptionTD" name="mulRecRemarksCaptionTD">Remarks</td>
			<td style="display: none" id="mulRecRemarksTD" name="mulRecRemarksTD" ><hdiits:text name="mulRecRemarks" tabindex="9" caption="mulRecRemarks" size="20"   /></td>
	    </tr>
    
	    <tr>
			<td><hdiits:caption captionid="EL.loanACNo" bundle="${commonLables}" /></td>
			<td><hdiits:text name="loanACNo" default="${actionList.loanAccountNo}" readonly="true" caption="loanACNo"  maxlength="20"  size="20"/> </td>
			<td style="display: none" id="intallmentsToRecoverCapTD" name="intallmentsToRecoverCapTD">Installments to recover</td>
			<td style="display: none" id="intallmentsToRecoverTD" name="intallmentsToRecoverTD"><hdiits:number name="intallmentsToRecover" default="0" style="text-align:right;" caption="intallmentsToRecover"  validation="txt.isnumber"  maxlength="3"   size="20" onblur="javascript:popDataFrmPrinInstToRecover(this);" /> </td>
	    </tr>
	    
	    <tr>
			<td><hdiits:caption captionid="EL.loanSancOrderNo" bundle="${commonLables}" /></td>
			<td><hdiits:text name="loanSancOrderNo" default="${actionList.loanSancOrderNo}" readonly="true" caption="Loan Sanction Order No"    size="25" maxlength="25" /> </td>
			<td style="display: none" id="amountToRecoverCapTD" name="amountToRecoverCapTD">Amount to recover <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td style="display: none" id="amountToRecoverTD" name="amountToRecoverTD"><hdiits:number name="amountToRecover" default="0" style="text-align:right;" caption="amountToRecover"  readonly="true" validation="txt.isnumber"    size="20" /> </td>
		
			</tr>
	   
	    <tr>
		<td><hdiits:caption captionid="EL.loanSancOrderDate" bundle="${commonLables}" /></td>
	   <td>
	   	<fmt:formatDate var="loanSancOrderdate2" value="${loanSancOrderdate1}" pattern="dd/MM/yyyy"  dateStyle="medium" />
	   	<fmt:formatDate var="loanSancOrderdate1" value="${loanSancOrderdate1}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium"  />
	  	<hdiits:dateTime caption="Sanction order date" bundle="${commonLables}" name="loanSancOrderDate" disabled="true"  validation="txt.isrequired,txt.isdt" default="${loanSancOrderdate1}" mandatory="true"/>
	  	<hdiits:hidden name="loanSancOrderDate_Hidden" id="loanSancOrderDate_Hidden" default="${loanSancOrderdate2}" />
	   </td>
		<td><hdiits:caption captionid="EL.UpdateOrderNo" bundle="${commonLables}" /></td>
			<td><hdiits:text name="UpdateOrderNo" default="${resValue.ordrNo}" readonly="false" caption="Update Order No"  validation="txt.isnumber"  size="25" maxlength="25" mandatory="true" /> </td>
	    
	   <td colspan="2"></td>
	    </tr>
	   <tr>
			<td><hdiits:caption captionid="EL.loanStartDate" bundle="${commonLables}" /></td>
			<td>
			<fmt:formatDate var="loadDate_Hidden_format" value="${actionList.loanDate}" pattern="dd/MM/yyyy"  dateStyle="medium" />
			<hdiits:dateTime captionid="EL.loanStartDate" bundle="${commonLables}" name="loanDate" disabled="true"  validation="txt.isrequired,txt.isdt" mandatory="true" default="${actionList.loanDate}"/>
			<hdiits:hidden name="loanDate_Hidden" id="loanDate_Hidden" default="${loadDate_Hidden_format}" />
			</TD>
			<td>
			<fmt:formatDate var="ordrDate" value="${ordrDate}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium"  /><hdiits:caption captionid="EL.UpdateOrderDate" bundle="${commonLables}" />
			</td>
	  		<td> <hdiits:dateTime caption="Update order date" bundle="${commonLables}" name="updateOrderDate" validation="txt.isrequired,txt.isdt" default="${ordrDate}" mandatory="true" /></td>
	  
			<td colspan="2"></td>	
		</tr> 
		 <tr>
			<td><hdiits:caption captionid="EL.activateFlag" bundle="${commonLables}" /></td>
			<td>
			
				<c:if test="${actionList.loanActivateFlag eq 1}" >
					<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}"  value="1"  default="1" name="radActivateFlag" id="radActivateFlag" />
					<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}"  value="0"  onclick="updateActivate(this,'0')" name="radActivateFlag"  id="radActivateFlag"/>
					<hdiits:radio captionid="OT.Paused" caption="Paused" bundle="${commonLables}"  value="2"  onclick="updateActivate(this,'0')" name="radActivateFlag"  id="radActivateFlag"/>
				</c:if> 
				<c:if  test="${actionList.loanActivateFlag eq 0}" >
					<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}"  value="1" readonly="true" name="radActivateFlag" id="radActivateFlag" />
					<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}"  value="0"  readonly="true" default="0"  name="radActivateFlag"  id="radActivateFlag"/>
					<hdiits:radio captionid="OT.Paused" caption="Paused" bundle="${commonLables}"  readonly="true" value="2" name="radActivateFlag"  id="radActivateFlag"/>
				</c:if>
				<c:if  test="${actionList.loanActivateFlag eq 2}" >
					<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}"  value="1" onclick="updateActivate(this,'2')"  name="radActivateFlag" id="radActivateFlag" />
					<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}"  value="0"  onclick="updateActivate(this,'2')" name="radActivateFlag"  id="radActivateFlag"/>
					<hdiits:radio captionid="OT.Paused" caption="Paused" bundle="${commonLables}"  value="2"  default="2" name="radActivateFlag"  id="radActivateFlag"/>
				</c:if> 
			
			<hdiits:hidden name="loanActivateFlag" default="${actionList.loanActivateFlag}"/>
			
			
			
			</TD>	
			<td colspan="2"></td>
		</tr> 
		
		<!-- Added by ankit bhatt for Maha -->
	    <tr>
	    	<td><hdiits:caption captionid="VN.VoucherNo"  bundle="${commonLables}" /></td>
			<td><hdiits:text name="loanVoucherNo" tabindex="9" caption="loanVoucherNo"  default="${actionList.voucherNo}" size="20"  readonly="true"/> </td>
			<td colspan="2"></td>
	    	
	    </tr>
	    <tr>	
	    	<td><hdiits:caption captionid="VN.VoucherDate" bundle="${commonLables}" /></td>
			<td>
			<hdiits:dateTime captionid="VN.VoucherDate"  bundle="${commonLables}"  name="loanVoucherDate"   disabled="true" />
			<hdiits:hidden name="loanVoucherDate_Hidden" id="loanVoucherDate_Hidden" />
			</td>
			<td colspan="2"></td>
			
	    </tr>	
	    <!-- new 04 jan 2011 -->
	    <tr>
	    	<td><hdiits:caption captionid="EL.addintno"	bundle="${commonLables}"  /></td>
			<td><hdiits:number name="loanOddinstno" tabindex="10" default="${actionList.loanOddinstno}" style="text-align:right;" readonly="true" caption="loanOddinstno" validation="txt.isnumber" maxlength="8" size="20" /></td>
			<td colspan="2"></td>
	    </tr>  
	    <tr>
	    <td><hdiits:caption captionid="EL.addintamt" bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanOddinstAmt" tabindex="11"	default="${actionList.loanOddinstAmt}" style="text-align:right;" readonly="true" caption="loanOddinstAmt" validation="txt.isnumber"	maxlength="8" size="20" /></td>
			<td colspan="2"></td>
	    </tr>
	    <br/>
	    <!-- ended -->
		
		<!-- added by ravysh -->
		
		<c:if test="${FromBasicDtlsNew eq 'YES'}">	
	<tr>
	<td colspan="4"><br></td></tr>
	
	<tr>
	<td colspan="4" align="center" >
		<hdiits:button name="btnsubmit1" id="btnsubmit1" type="button" onclick="empLoanFromBasicDtls()" value="Submit"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:button name="btnClose1" type="button" value="Close" onclick="window.close();" />
	</td>
</tr>
	<tr>
	<td colspan="4"><br></td></tr>
	</c:if>	
		
		
	</table>
	</c:when>
	<c:otherwise>
	<table    width="60%" align="center">
		<tr>
			<td colspan="4">
				<font style="color: red;" >Note: Please re-generate the bill after loan data has been updated. Failing to do so would cause data discrepency.</font>
			</td>
		</tr>
		<tr>
	   		<td><hdiits:caption captionid="OT.LoanRecoveryMode" bundle="${commonLables}" /></td>
			<td>
				<hdiits:radio captionid="OT.Normal"  caption="Normal" bundle="${commonLables}"  value="0"  default="0" onclick="changeLoanRecoveryMode(this)" name="radLoanRecoveryMode" id="radLoanRecoveryMode" />
				<hdiits:radio captionid="OT.Multiple" caption="Multiple" bundle="${commonLables}"  value="1"  onclick="changeLoanRecoveryMode(this)" name="radLoanRecoveryMode"  id="radLoanRecoveryMode"/>
				<hdiits:hidden name="loanRecoveryModeFlag" id="loanRecoveryModeFlag" />
			</TD>	
			<td colspan="2"></td>
	   </tr>
	    <tr>
			<td><hdiits:caption captionid="EL.lpAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="principalAmt"  default="${actionList.loanPrinAmt}" readonly="true"  style="text-align:right" caption="principalAmt"  validation="txt.isnumber"  maxlength="10"   size="20" />  </td>
			<td>Recovered Amount <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="principalRecoveredAmtStatic"  default="${principalRecoveredAmt}" readonly="true"  style="text-align:right" caption="principalRecoveredAmtStatic"  validation="txt.isnumber"  maxlength="10"   size="20" />  </td>
	    </tr>
	    
	     <tr>
			<td><hdiits:caption captionid="EL.lpiNo" bundle="${commonLables}"/></td>
			<td><hdiits:number name="principalInstNo"  default="${actionList.loanPrinInstNo}" style="text-align:right;" readonly="true" caption="principalInstNo"  validation="txt.isnumber"  maxlength="3"   size="20" />  </td>
			<td>Recovered Installments </td>
			<td><hdiits:number name="pricipalRecoveredIntStatic"  default="${pricipalRecoveredInt}" style="text-align:right;" readonly="true" caption="pricipalRecoveredIntStatic"  validation="txt.isnumber"  maxlength="3"   size="20" /></td>
	    </tr>
	    
	    <tr>
			<td><hdiits:caption captionid="EL.loanPrincipalEmiAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanPrinEmiAmt"  default="${actionList.loanPrinEmiAmt}" readonly="true" style="text-align:right" caption="EL.loanPrincipalEmiAmt" validation="txt.isnumber"  maxlength="10"  size="20" /> </td>
			<td colspan="2"></td>
	    </tr>
	    
	    <tr>
			<td><hdiits:caption captionid="EL.liAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="interestAmt"  tabindex="1" default="${actionList.loanInterestAmt}" readonly="true" style="text-align:right" caption="interestAmt"  validation="txt.isnumber"  maxlength="10"   size="20" /> </td>
			<td>Recovered Amount <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td> <hdiits:number name="interestRecoveredCalcAmount"  id="interestRecoveredCalcAmount" style="text-align:right;" default="${intRecoveredAmt}" caption="interestRecoveredCalcAmount"  validation="txt.isnumber"  maxlength="3"   size="20"  readonly="true" />  </td>
	    </tr>
	   
	    
	     <tr>
			<td><hdiits:caption captionid="EL.liiNo" bundle="${commonLables}"/></td>
			<td><hdiits:number name="interestInstNo" tabindex="2" default="${actionList.loanIntInstNo}" style="text-align:right;" readonly="true" caption="interestInstNo"  validation="txt.isnumber"  maxlength="3"   size="20" /> </td> 
			<td > Recovered Installments </td> 
			<td><hdiits:number name="interestRecoveredNumber"  id="interestRecoveredNumber" style="text-align:right;" default="${intRecoveredInt}" caption="interestRecoveredNumber"  validation="txt.isnumber"  maxlength="3"   size="20" onblur="javascript:popDataFrmInteRecNumber(this);" /> </td>
			<script type="text/javascript">
				if(parseInt('${intRecoveredInt}') >= parseInt('${actionList.loanIntInstNo}')){
					document.getElementById('interestRecoveredNumber').readOnly = true;
					deactivateLoan = true;
				}
			</script> 
	    </tr>
	    
	    
	     <tr>
			<td><hdiits:caption captionid="EL.loanInterestEmiAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanIntEmiAmt" tabindex="3" default="${actionList.loanIntEmiAmt}" readonly="true" style="text-align:right" caption="EL.loanInterestEmiAmt" validation="txt.isnumber"  maxlength="10" size="20" /> </td>
			<td colspan="2"></td>
	    </tr>
	    
	    <tr>
	    	    <td><hdiits:caption captionid="EL.outstandingAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
				<td><hdiits:number name="txtOutstandingIntAmt" default="${actionList.loanInterestAmt - intRecoveredAmt - actionList.mulLoanAmtRecvd}"  style="text-align:right; color:red" caption="EL.outstandingAmt"  maxlength="20"  size="20" readonly="true" /> </td>
				<td style="display: none" id="mulRecRemarksCaptionTD" name="mulRecRemarksCaptionTD">Remarks</td>
				<td style="display: none" id="mulRecRemarksTD" name="mulRecRemarksTD" ><hdiits:text name="mulRecRemarks" tabindex="9" caption="mulRecRemarks" size="20"   /></td>
	    </tr>
	    
	    <tr style="display: none">
			<td><hdiits:caption captionid="EL.loanPrinRecovAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanPrinRecovAmt"  style="text-align:right" caption="loanPrinRecovAmt" readonly="true" validation="txt.isnumber" maxlength="8" size="20" default="${principalRecoveredAmt}" /> </td>
			<td><hdiits:caption captionid="EL.loanIntRecovAmt" bundle="${commonLables}"/><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanIntRecovAmt" tabindex="4"  style="text-align:right" caption="loanIntRecovAmt" readonly="true" validation="txt.isnumber"  maxlength="8" size="20" default="${intRecoveredAmt}"/> </td>
	    </tr>
	    <tr style="display: none">
	    	<td><hdiits:caption captionid="EL.loanPrinRecovInt"  bundle="${commonLables}"/></td>
			<td><hdiits:number name="loanPrinRecovInt"    style="text-align:right" caption="loanPrinRecovInt" readonly="true" validation="txt.isnumber" maxlength="8" size="20" default="${pricipalRecoveredInt}"/> </td>
	    	<td><hdiits:caption captionid="EL.loanIntRecovInt" bundle="${commonLables}"/></td>
			<td><hdiits:number name="loanIntRecovInt" tabindex="5" caption="loanIntRecovInt" readonly="true" validation="txt.isnumber" maxlength="8" size="20" default="${intRecoveredInt}"/> </td>
	    </tr>
	    
		<tr> 
				
            <td><hdiits:caption captionid="EL.loanACNo" bundle="${commonLables}"/></td>
		    <td><hdiits:text name="loanACNo" tabindex="6" default="${actionList.loanAccountNo}" readonly="true" caption="loanACNo"  maxlength="20"  size="20"/> </td>
    		<td style="display: none" id="intallmentsToRecoverCapTD" name="intallmentsToRecoverCapTD">Installments to recover</td>
			<td style="display: none" id="intallmentsToRecoverTD" name="intallmentsToRecoverTD"><hdiits:number name="intallmentsToRecover" default="0" style="text-align:right;" caption="intallmentsToRecover"  validation="txt.isnumber"  maxlength="3"   size="20" onblur="javascript:popDataFrmInterestInstToRecover(this);" /> </td>
	    </tr>	   
	    <tr>
			
	   		<td><hdiits:caption captionid="EL.loanSancOrderNo" bundle="${commonLables}"/></td>
			<td><hdiits:text name="loanSancOrderNo" tabindex="7" default="${actionList.loanSancOrderNo}" readonly="true" caption="Loan Sanction Order No"    size="25" maxlength="25" /> </td>
   			<td style="display: none" id="amountToRecoverCapTD" name="amountToRecoverCapTD">Amount to recover</td>
			<td style="display: none" id="amountToRecoverTD" name="amountToRecoverTD"><hdiits:number name="amountToRecover" default="0" style="text-align:right;" caption="amountToRecover"  readonly="true" validation="txt.isnumber"    size="20" /> </td>
	    	</tr>
	    
	     <tr>
			<td><hdiits:caption captionid="EL.loanSancOrderDate" bundle="${commonLables}"/></td>
	  
	        <td>
	        <fmt:formatDate var="loanSancOrderdate2" value="${loanSancOrderdate1}" pattern="dd/MM/yyyy"  dateStyle="medium" /> 
	        <fmt:formatDate var="loanSancOrderdate1" value="${loanSancOrderdate1}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />
			
	  		<hdiits:dateTime caption="Sanction order date" bundle="${commonLables}" disabled="true" name="loanSancOrderDate"  validation="txt.isrequired,txt.isdt" default="${loanSancOrderdate1}" mandatory="true"/>
	  		<hdiits:hidden name="loanSancOrderDate_Hidden" id="loanSancOrderDate_Hidden" default="${loanSancOrderdate2}" /></td>
	   		<td><hdiits:caption captionid="EL.UpdateOrderNo" bundle="${commonLables}" /></td>
			<td><hdiits:text name="UpdateOrderNo" default="${resValue.ordrNo}" readonly="false" caption="Update Order No"  validation="txt.isnumber"  size="25" maxlength="25" mandatory="true" /> </td>
	    
	   		<td colspan="2"></td>
	     </tr>
	    
	    
	     <tr>
			<td><hdiits:caption captionid="EL.loanStartDate" bundle="${commonLables}"/></td>
			<td>
			<fmt:formatDate var="loadDate_Hidden_format" value="${actionList.loanDate}" pattern="dd/MM/yyyy"  dateStyle="medium" />
			<hdiits:dateTime captionid="EL.loanStartDate" tabindex="8" bundle="${commonLables}" disabled="true" name="loanDate" validation="txt.isrequired,txt.isdt" mandatory="true" default="${actionList.loanDate}"/>
			<hdiits:hidden name="loanDate_Hidden" id="loanDate_Hidden" default="${loadDate_Hidden_format}" />
			</TD>
			<td>
			<fmt:formatDate var="ordrDate" value="${ordrDate}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium"  /><hdiits:caption captionid="EL.UpdateOrderDate" bundle="${commonLables}" />
			</td>
	  		<td> <hdiits:dateTime caption="Update order date" bundle="${commonLables}" name="updateOrderDate" validation="txt.isrequired,txt.isdt" default="${ordrDate}" mandatory="true" /></td>
	   			
			<td colspan="2"></td>
		</tr> 
		
		 <tr>
			<td><hdiits:caption captionid="EL.activateFlag" bundle="${commonLables}"/></td>
			<td>
			
			<c:if test="${actionList.loanActivateFlag eq 1}" >
					<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}"  value="1"  default="1" name="radActivateFlag" id="radActivateFlag" />
					<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}"  value="0"  onclick="updateActivate(this,'0')" name="radActivateFlag"  id="radActivateFlag"/>
					<hdiits:radio captionid="OT.Paused" caption="Paused" bundle="${commonLables}"  value="2"  onclick="updateActivate(this,'0')" name="radActivateFlag"  id="radActivateFlag"/>
				</c:if> 
				<c:if  test="${actionList.loanActivateFlag eq 0}" >
					<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}"  value="1" readonly="true" name="radActivateFlag" id="radActivateFlag" />
					<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}"  value="0"  readonly="true" default="0"  name="radActivateFlag"  id="radActivateFlag"/>
					<hdiits:radio captionid="OT.Paused" caption="Paused" bundle="${commonLables}"  readonly="true" value="2" name="radActivateFlag"  id="radActivateFlag"/>
				</c:if>
				<c:if  test="${actionList.loanActivateFlag eq 2}" >
					<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}"  value="1" onclick="updateActivate(this,'2')"  name="radActivateFlag" id="radActivateFlag" />
					<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}"  value="0"  onclick="updateActivate(this,'2')" name="radActivateFlag"  id="radActivateFlag"/>
					<hdiits:radio captionid="OT.Paused" caption="Paused" bundle="${commonLables}"  value="2"  default="2" name="radActivateFlag"  id="radActivateFlag"/>
			</c:if> 
			
			<hdiits:hidden name="loanActivateFlag" default="${actionList.loanActivateFlag}"/>
			
			</TD>	
			<td colspan="2"></td>
	    	</tr> 
		
	    
	    <tr>
	    	<td><hdiits:caption captionid="VN.VoucherNo"  bundle="${commonLables}"/></td>
			<td><hdiits:text name="loanVoucherNo" tabindex="9" caption="loanVoucherNo" default="${actionList.voucherNo}" size="20"  readonly="true"/> </td>
	   		<td colspan="2"></td>
	     </tr>	
	   
	    <tr>
	    	<td><hdiits:caption captionid="VN.VoucherDate" bundle="${commonLables}"/></td>
			<td>
			
			<hdiits:dateTime captionid="VN.VoucherDate"  bundle="${commonLables}" disabled="true" name="loanVoucherDate"   />
			<hdiits:hidden name="loanVoucherDate_Hidden" id="loanVoucherDate_Hidden" />
			</td>
			<td colspan="2"></td>
	    	
	    </tr>	
	    
	    
	    
	   
		 <!-- new 04 jan 2011 -->
	    <tr>
	    	<td><hdiits:caption captionid="EL.addintno"	bundle="${commonLables}"  /></td>
			<td><hdiits:number name="loanOddinstno" tabindex="10" readonly="true" style="text-align:right;" default="${actionList.loanOddinstno}" caption="loanOddinstno" validation="txt.isnumber" maxlength="8" size="20" /></td>
	   <td colspan="2"></td>
	     </tr>
	    
	     <tr>
	    <td><hdiits:caption captionid="EL.addintamt" bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanOddinstAmt" tabindex="11" readonly="true"	style="text-align:right;" default="${actionList.loanOddinstAmt}" caption="loanOddinstAmt" validation="txt.isnumber"	maxlength="8" size="20" /></td>
	    <td colspan="2"></td>
	    </tr>
	    
	    
	    <br/>
	    <!-- ended -->
		<!-- added by ravysh -->
		
		<c:if test="${FromBasicDtlsNew eq 'YES'}">	
	<tr>
	<td colspan="4"><br></td></tr>
	
	<tr>
	<td colspan="4" align="center" >
		<hdiits:button name="btnsubmit" id="btnsubmit" type="button" onclick="empLoanFromBasicDtls()" value="Submit"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:button name="btnClose" type="button" value="Close" onclick="window.close();" />
	</td>
</tr>
	
	</c:if>	
		
		
	</table>
		
	</c:otherwise>
	</c:choose>
 	</div>
 	<div>
 	<c:choose>
 	<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getLoanValue&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getLoanValue" name="givenurl"/>
</c:otherwise>
</c:choose>

 	<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>
 		 	<hdiits:jsField  name="validate" jsFunction="chekcAmonuntValue()" /> 
 		 	
 		<!-- added by ravysh --> 	
 	<c:if test="${FromBasicDtlsNew ne 'YES'}">	
 	<br/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</c:if>	 
	
	
	
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
//		disableComponent();
		if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='false')
		{
			alert("${msg}");
			
		if("${FromBasicDtlsNew}"=="YES")
		{
			
			window.opener.location.href="./hrms.htm?actionFlag=getOtherDataMerged&otherId=${otherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES";
			 window.close();
		}else{
			var url="./hrms.htm?actionFlag=getLoanValue";
			document.empLoan.action=url;
			document.empLoan.submit();
		}
		}
		else if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='added')
		{
            alert("${msg}");
			var url="./hrms.htm?actionFlag=getLoanValue&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y";

			document.empLoan.action=url;
			document.empLoan.submit();
		}

		document.getElementById('loanVoucherDate').value='${voucherDate}';
		
		document.getElementById('loanVoucherDate_Hidden').value='${voucherDate}';
		
	</script>
	<c:if test="${actionList.mulLoanRecoveryMode eq 1 }">
		<script type="text/javascript">
			document.getElementById('mulRecRemarksTD').style.display = "";
			document.getElementById('mulRecRemarksCaptionTD').style.display = "";
	
			
			document.getElementById('intallmentsToRecoverTD').style.display = "";
			document.getElementById('intallmentsToRecoverCapTD').style.display = "";
			
			document.getElementById('amountToRecoverCapTD').style.display = "";
			document.getElementById('amountToRecoverTD').style.display = "";
			
			var mulRemarks = '';
			if('${actionList.mulLoanRecRemarks}' != null)
				mulRemarks = '${actionList.mulLoanRecRemarks}';
			document.getElementById('mulRecRemarks').value = mulRemarks;
			document.getElementById('intallmentsToRecover').value = '${actionList.mulLoanInstRecvd}';
			document.getElementById('amountToRecover').value = '${actionList.mulLoanAmtRecvd}';
			
			if(document.getElementById('pricipalRecoveredNumber'))
				document.getElementById('pricipalRecoveredNumber').readOnly = true;
			if(document.getElementById('interestRecoveredNumber'))
				document.getElementById('interestRecoveredNumber').readOnly = true;
			
			document.forms[0].radLoanRecoveryMode[1].checked = true;
			document.getElementById('loanRecoveryModeFlag').value  = 1;

			
		</script>
	</c:if>
	
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

 